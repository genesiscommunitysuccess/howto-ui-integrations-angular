import type { AppRoute } from '@genesislcap/foundation-shell/app';

/**
 * Reporting route
 * @public
 */
export const reporting: AppRoute = {
  title: 'Reporting',
  path: 'reporting',
  name: 'reporting',
  element: async () => (await import('@genesislcap/pbc-reporting-ui')).RapidReporting,
  // @ts-ignore
  elementTag: 'rapid-reporting',
  settings: { autoAuth: true, maxRows: 500 },
  navItems: [
    {
      navId: 'header',
      title: 'Reporting',
      icon: {
        name: 'file-csv',
        variant: 'solid',
      },
    },
  ],
};
