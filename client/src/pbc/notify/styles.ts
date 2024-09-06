import type { AppStyle } from '@genesislcap/foundation-shell/app';
import { css } from '@genesislcap/web-core';

/**
 * @public
 */
export const inbox: AppStyle = {
  targetId: 'layout',
  styles: css`
    rapid-flyout::part(flyout) {
      display: flex;
      flex-direction: column;
      width: 40%;
      min-width: 320px;
      padding: 0;
    }

    rapid-flyout::part(header) {
      border-bottom: calc(var(--stroke-width) * 1px) solid var(--neutral-stroke-divider-rest);
    }

    rapid-flyout::part(content) {
      height: 100%;
      display: flex;
      flex-direction: column;
    }

    foundation-inbox {
      display: flex;
      flex: 1;
      height: 100%;
    }
  `,
};

/**
 * @public
 */
export const notificationsButtonStyle: AppStyle = {
  targetId: 'header',
  styles: css`
    foundation-inbox-counter {
      z-index: 999;
      position: absolute;
      top: 10px;
      right: -3px;
      pointer-events: none;
    }
  `,
};
