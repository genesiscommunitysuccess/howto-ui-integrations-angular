import type { AppMetadata } from '@genesislcap/foundation-shell/app';

/**
 * @public
 */
export const metadata: AppMetadata = {
  name: '@genesislcap/pbc-notify-ui',
  description: 'Genesis Notify PBC',
  version: '1.4.0',
  prerequisites: {
    '@genesislcap/foundation-ui': '14.*',
    gsf: '8.*',
  },
  dependencies: {
    '@genesislcap/pbc-notify-ui': '1.0.40',
    '@genesislcap/foundation-notifications': '14.*',
    serverDepId: '8.3.0',
  },
};
