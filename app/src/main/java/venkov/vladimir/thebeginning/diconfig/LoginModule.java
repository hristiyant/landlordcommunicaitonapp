package venkov.vladimir.thebeginning.diconfig;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import venkov.vladimir.thebeginning.Views.login.LoginActivity;
import venkov.vladimir.thebeginning.Views.login.LoginContracts;
import venkov.vladimir.thebeginning.Views.login.LoginFragment;
import venkov.vladimir.thebeginning.Views.login.LoginPresenter;

@Module
public abstract class LoginModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract LoginFragment loginFragment();

    @ActivityScoped
    @Binds
    abstract LoginContracts.Presenter loginPresenter(LoginPresenter presenter);
}
