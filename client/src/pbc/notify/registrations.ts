import { FoundationInbox } from '@genesislcap/pbc-notify-ui';
import type { AppRegistrationContext } from '@genesislcap/foundation-shell/app';
import { FoundationNotificationDashboard } from '@genesislcap/pbc-notify-ui';

/**
 * @privateRemarks
 * The try catch here is just to demo design system interaction.
 *
 * @public
 */
export async function register(context: AppRegistrationContext) {
  try {
    /**
     * const { provideDesignSystem } = context.designSystem;
     * provideDesignSystem().register(foundationAlerts);
     */
    FoundationNotificationDashboard;
    FoundationInbox;
  } catch (e) {
    console.error('Error registering notify', e);
  }
}
