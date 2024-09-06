import type { ToastButton } from '@genesislcap/foundation-notifications';
import type { AppElement } from '@genesislcap/foundation-shell/app';
import { html } from '@genesislcap/web-core';
import { getNotifyStore } from './stores';

const store = getNotifyStore();

/**
 * @public
 */
export const notificationsButton: AppElement = {
  targetId: 'nav-end',
  elements: html`
    <div
      class="icon-container notifications-button"
      part="notifications-button"
      data-test-id="notifications-button"
      data-pbc-asset-id="notifications-button"
      @click=${(x, c) => (c.parent ?? x).$emit('notification-icon-clicked')}
    >
      <rapid-icon variant="regular" name="bell" part="notifications-icon"></rapid-icon>
      <foundation-inbox-counter part="notifications-inbox-counter"></foundation-inbox-counter>
    </div>
  `,
};

/**
 * @public
 */
export const inboxFlyout: AppElement = {
  targetId: 'layout',
  elements: html`
    <rapid-flyout
      position="right"
      @closed=${(x, c) => (c.parent ?? x).$emit('change-inbox-display', false)}
      ?closed=${() => !store.inboxDisplayState}
      data-pbc-asset-id="inbox-flyout"
    >
      <div slot="title">Alerts Center</div>
      <foundation-inbox></foundation-inbox>
    </rapid-flyout>
  `,
};

/**
 * @public
 */
export const notificationListener: AppElement = {
  targetId: 'layout',
  elements: html`
    <rapid-notification-listener
      resource-name="ALL_NOTIFY_ALERT_RECORDS"
      :toastButtons="${(): ToastButton[] => [
        // {
        //   condition: ({ details }) => details.NOTIFY_SEVERITY !== 'Warning',
        //   action: ({ details, buttonElement, toastElement }) => {
        //     console.log({ details, buttonElement, toastElement });
        //     toastElement.close();
        //   },
        //   appearance: 'secondary-orange',
        //   placeholder: 'button for warnings',
        // },
      ]}"
      data-pbc-asset-id="notification-listener"
    ></rapid-notification-listener>
  `,
};

/**
 * @public
 */
export const bannerAnchor: AppElement = {
  targetId: 'content-start',
  elements: html`
    <div id="banner-anchor" data-pbc-asset-id="banner-anchor"></div>
  `,
};
