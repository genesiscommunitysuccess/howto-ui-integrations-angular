import { configure } from '@genesislcap/foundation-auth/config';
import { Connect } from '@genesislcap/foundation-comms';
import { API_HOST } from '@genesislcap/foundation-utils';
import type { Router } from '@angular/router';
import { getUser } from '@genesislcap/foundation-user';
import { css, DI } from '@genesislcap/web-core';
import { AUTH_PATH } from '../app.config';
import logo from '../../assets/logo.svg';

// eslint-disable-next-line
declare var ENABLE_SSO: boolean;

const ssoSettings =
  typeof ENABLE_SSO !== 'undefined' && ENABLE_SSO === true
    ? {
        autoAuth: true,
        sso: {
          toggled: true,
          identityProvidersPath: 'gwf/sso/list',
        },
      }
    : {};

/**
 * Configure the micro frontend
 */
export const configureFoundationLogin = ({
  router,
}: {
  router: Router;
}) => {
  const connect = DI.getOrCreateDOMContainer().get(Connect);

  return configure({
    name: 'client-app-login',
    omitRoutes: ['request-account', 'forgot-password'],
    hostPath: AUTH_PATH,
    // The router accepts the WebSocket only once the HTTP login has set its session cookie
    postLoginRedirect: async () => {
      await connect.connect(API_HOST);
      router.navigate([getUser().lastPath() ?? 'order-mgmt']);
    },
    ...ssoSettings,
    logo: css `
      content: url("${logo}");
    `,
  });
}
