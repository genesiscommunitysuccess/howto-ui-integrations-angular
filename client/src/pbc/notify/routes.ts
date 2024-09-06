import type { AppRoute } from '@genesislcap/foundation-shell/app';
import { css, customElement, GenesisElement, html } from '@genesislcap/web-core';

/**
 * @internal
 */
const notificationsDashboardTemplate = html`
  <foundation-notification-dashboard></foundation-notification-dashboard>
`;

/**
 * @internal
 */
const notificationsDashboardStyles = css`
  :host,
  section {
    display: flex;
    flex-wrap: wrap;
    width: 100%;
    height: 100%;
  }

  foundation-notification-dashboard {
    width: 100%;
  }

  header {
    width: 100%;
    text-align: center;
  }

  .wrapper {
    overflow: hidden;
    flex-grow: 1;
    width: 400px;
  }

  .wrapper-scroll {
    max-height: 400px;
    overflow-y: auto;
  }

  rapid-modal::part(dialog) {
    padding: 0;
  }

  rapid-modal.edit .wrapper,
  rapid-modal.add .wrapper {
    padding: 32px 12px;
  }
`;

/**
 * @internal
 */
@customElement({
  name: 'notifications-dashboard',
  template: notificationsDashboardTemplate,
  styles: notificationsDashboardStyles,
})
class Notifications extends GenesisElement {}

/**
 * @public
 */
export const notificationsDashboard: AppRoute = {
  title: 'Notifications Dashboard',
  path: 'notifications-dashboard',
  name: 'notifications-dashboard',
  element: Notifications,
  // @ts-ignore
  elementTag: 'notifications-dashboard',
  settings: { autoAuth: true },
  navItems: [
    {
      navId: 'header',
      title: 'Notifications',
      icon: {
        name: 'bell',
        variant: 'solid',
      },
    },
  ],
};
