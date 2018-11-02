package venkov.vladimir.thebeginning.diconfig;

import dagger.Binds;
import dagger.android.ContributesAndroidInjector;
import venkov.vladimir.thebeginning.Views.user_details.UserDetailsContracts;
import venkov.vladimir.thebeginning.Views.user_details.UserDetailsFragment;
import venkov.vladimir.thebeginning.Views.user_details.UserDetailsPresenter;

public abstract class UserDetailsModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract UserDetailsFragment userDetailsFragment();

    @ActivityScoped
    @Binds
    abstract UserDetailsContracts.Presenter userListPresenter(UserDetailsPresenter presenter);
}
