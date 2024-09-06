import { AfterViewInit, Component, ElementRef, OnDestroy, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import getLayoutNameByRoute from './utils/getLayoutNameByRoute';
import type { LayoutComponentName } from './types/layout';
import { configureFoundationLogin } from './share/foundation-login';
import { registerComponents } from './share/genesis-components';
import { getStore } from './store';
import { customEventFactory, registerStylesTarget } from '../pbc/utils';

@Component({
  selector: 'ordermgmt-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent implements OnInit, OnDestroy, AfterViewInit {
  layoutName?: LayoutComponentName;
  title = 'Order Mgmt';
  store = getStore();

  constructor(
    private el: ElementRef,
    router: Router,
  ) {
      configureFoundationLogin({ router });
      
    // Set layout componet based on route
    router.events.subscribe((event: any) => {
      if (event instanceof NavigationEnd) {
        this.layoutName = getLayoutNameByRoute(event.urlAfterRedirects);
      }
    });
  }

  ngOnInit() {
    this.addEventListeners();
    this.readyStore();
    registerStylesTarget(this.el.nativeElement, 'main');
    this.loadRemotes();
  }

  ngOnDestroy() {
    this.removeEventListeners();
    this.disconnectStore();
  }

  async loadRemotes() {
    await registerComponents();
  }

  addEventListeners() {
    this.el.nativeElement.addEventListener('store-connected', this.store.onConnected);
  }

  removeEventListeners() {
    this.el.nativeElement.removeEventListener('store-connected', this.store.onConnected);
  }

  readyStore() {
    this.dispatchCustomEvent('store-connected', this.el.nativeElement);
    this.dispatchCustomEvent('store-ready', true);
  }

  disconnectStore() {
    this.dispatchCustomEvent('store-disconnected');
  }

  dispatchCustomEvent(type: string, detail?: any) {
    this.el.nativeElement.dispatchEvent(customEventFactory(type, detail));
  }

  ngAfterViewInit() {
  }

}
