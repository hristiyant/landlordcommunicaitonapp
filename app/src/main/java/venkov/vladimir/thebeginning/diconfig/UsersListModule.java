package venkov.vladimir.thebeginning.diconfig;


import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import venkov.vladimir.thebeginning.Views.users_list.UsersListContracts;
import venkov.vladimir.thebeginning.Views.users_list.UsersListFragment;
import venkov.vladimir.thebeginning.Views.users_list.UsersListPresenter;

@Module
public abstract class UsersListModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract UsersListFragment usersListFragment();

    @ActivityScoped
    @Binds
    abstract UsersListContracts.Presenter usersListPresenter(UsersListPresenter presenter);
}
