import type { AppRoute } from '@genesislcap/foundation-shell/app';

/**
 * Document Management route
 * @public
 */
export const document: AppRoute = {
  title: 'Document Management',
  path: 'document-management',
  name: 'document-management',
  element: async () => (await import('@genesislcap/pbc-documents-ui')).RapidDocumentManager,
  // @ts-ignore
  elementTag: 'rapid-document-manager',
  settings: { autoAuth: true, maxRows: 500 },
  navItems: [
    {
      navId: 'header',
      title: 'Document Management',
      icon: {
        name: 'file-csv',
        variant: 'solid',
      },
    },
  ],
};
