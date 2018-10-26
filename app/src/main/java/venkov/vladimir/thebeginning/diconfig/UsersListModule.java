package venkov.vladimir.thebeginning.diconfig;


import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import venkov.vladimir.thebeginning.Views.UsersListContracts;
import venkov.vladimir.thebeginning.Views.UsersListFragment;
import venkov.vladimir.thebeginning.Views.UsersListPresenter;

@Module
public abstract class UsersListModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract UsersListFragment usersListFragment();

    @ActivityScoped
    @Binds
    abstract UsersListContracts.Presenter usersListPresenter(UsersListPresenter presenter);
}
