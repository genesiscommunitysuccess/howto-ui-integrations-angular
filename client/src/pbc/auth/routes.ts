import type { AppRoute } from '@genesislcap/foundation-shell/app';

/**
 * Main route
 * @public
 */
export const users: AppRoute = {
    title: 'users',
    path: 'users',
    name: 'users',
    element: async () => (await import('@genesislcap/pbc-auth-ui')).Users,
    // @ts-ignore
    elementTag: 'user-management-pbc',
    settings: { autoAuth: true, maxRows: 500 },
    navItems: [
        {
            navId: 'header',
            title: 'User Management',
            placementIndex: 200,
        },
    ],
};

export const profiles: AppRoute = {
    title: 'profiles',
    path: 'profiles',
    name: 'profiles',
    element: async () => (await import('@genesislcap/pbc-auth-ui')).Profiles,
    // @ts-ignore
    elementTag: 'profile-management-pbc',
    settings: { autoAuth: true, maxRows: 500 },
    navItems: [
        {
            navId: 'header',
            title: 'Profiles',
            placementIndex: 201,
        },
    ],
};
