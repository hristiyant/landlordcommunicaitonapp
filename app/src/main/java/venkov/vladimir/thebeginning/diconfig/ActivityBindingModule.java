package venkov.vladimir.thebeginning.diconfig;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import venkov.vladimir.thebeginning.Views.accommodation_details.AccommodationDetailsActivity;
import venkov.vladimir.thebeginning.Views.users_list.ListAllActivity;
import venkov.vladimir.thebeginning.Views.login.LoginActivity;
import venkov.vladimir.thebeginning.Views.accommodations_list.AccommodationsListActivity;

@Module
public abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(
            modules = UsersListModule.class
    )
    abstract ListAllActivity listAllActivity();

    @ActivityScoped
    @ContributesAndroidInjector(
            modules = LoginModule.class
    )
    abstract LoginActivity loginActivity();

    @ActivityScoped
    @ContributesAndroidInjector(
            modules = AccommodationsListModule.class
    )
    abstract AccommodationsListActivity accommodationsListActivity();

    @ActivityScoped
    @ContributesAndroidInjector(
            modules = AccommodationDetailsModule.class
    )
    abstract AccommodationDetailsActivity accommodationDetailsActivity();
}
