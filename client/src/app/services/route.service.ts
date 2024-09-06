import { Injectable } from '@angular/core';
import { Route, Routes } from '@angular/router';
import { getApp } from '@genesislcap/foundation-shell/app';
import { FoundationRoute, FoundationRouteNavItem, getNavItems } from '@genesislcap/foundation-ui';
import { PBCContainer } from '../../pbc/container';
import { AUTH_PATH, NOT_PERMITTED_PATH } from '../app.config';
import { ChainedGuard } from '../guards/chained.guard';
import { AuthLoginComponent } from '../pages/auth-login/auth-login.component';
import { NotPermittedComponent } from '../pages/not-permitted/not-permitted.component';
import { OrderMgmtComponent } from '../pages/order-mgmt/order-mgmt.component';
import { TradeMgmtComponent } from '../pages/trade-mgmt/trade-mgmt.component';
import { RefDataComponent } from '../pages/ref-data/ref-data.component';

@Injectable({
    providedIn: 'root'
})
export class RouteService {
    static routes: Routes = [
        {
            path: '',
            redirectTo: `${AUTH_PATH}`,
            pathMatch: 'full',
        },
        {
            path: `${AUTH_PATH}`,
            component: AuthLoginComponent,
        },
        {
            path: `${NOT_PERMITTED_PATH}`,
            component: NotPermittedComponent,
        },
        {
            path: 'order-mgmt',
            canActivate: [ChainedGuard],
            component: OrderMgmtComponent,
            data: {
                permissionCode: '',
                navItems: [
                    {
                        navId: 'header',
                        title: 'Order Mgmt',
                        icon: {
                            name: '',
                            variant: 'solid',
                        },
                    },
                ],
            },
        },
        {
            path: 'trade-mgmt',
            canActivate: [ChainedGuard],
            component: TradeMgmtComponent,
            data: {
                permissionCode: '',
                navItems: [
                    {
                        navId: 'header',
                        title: 'Trade Mgmt',
                        icon: {
                            name: '',
                            variant: 'solid',
                        },
                    },
                ],
            },
        },
        {
            path: 'ref-data',
            canActivate: [ChainedGuard],
            component: RefDataComponent,
            data: {
                permissionCode: '',
                navItems: [
                    {
                        navId: 'header',
                        title: 'Ref Data',
                        icon: {
                            name: '',
                            variant: 'solid',
                        },
                    },
                ],
            },
        },
    ];

    /**
     * @privateRemarks
     * The shell has access to context, so it's possible for it to return a pre-mapped framework variant of routes. 
     * In this iteration we're doing it inline, given the angular version may move and here we know the shape we need.
     */
    pbcRoutes(): Routes {
        return getApp().routes.map((route) => {
            return <Route>{
                title: route.title,
                path: route.path,
                /**
                 * Ask about permissions.viewRight in PBC context, as we may need to apply a data.permissionCode here.
                 * Not sure if they are added to the filesystem prior to handlebars template processing across the files.
                 */
                canActivate: [ChainedGuard],
                component: PBCContainer,
                data: {
                    ...route.settings,
                    pbcElement: route.element,
                    // @ts-ignore
                    pbcElementTag: route.elementTag,
                    navItems: route.navItems
                },
            };
        })
    }

    allRoutes(): Routes {
        return [
            ...RouteService.routes,
            ...this.pbcRoutes(),
        ];
    }

    getNavItems(): FoundationRouteNavItem[] {
        const allNavItems = this.allRoutes().flatMap(route => (<FoundationRoute>{
            path: route.path as string,
            navItems: route.data?.['navItems'],
        }));
        return getNavItems(allNavItems);
    }
}
