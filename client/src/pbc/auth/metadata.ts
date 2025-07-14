import type { AppMetadata } from '@genesislcap/foundation-shell/app';

/**
 * @public
 */
export const metadata: AppMetadata = {
  name: '@genesislcap/pbc-auth-ui',
  description: 'Genesis Auth PBC',
  version: '1.6.0',
  prerequisites: {
    '@genesislcap/foundation-ui': '14.*',
    gsf: '8.*',
  },
  dependencies: {
    '@genesislcap/pbc-auth-ui': '1.0.9',
    serverDepId: '8.3.0',
  },
};
