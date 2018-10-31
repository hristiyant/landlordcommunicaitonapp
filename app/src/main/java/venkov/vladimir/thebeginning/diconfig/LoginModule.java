package venkov.vladimir.thebeginning.diconfig;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import venkov.vladimir.thebeginning.Views.login.LoginActivity;

@Module
public abstract class LoginModule {
    @ActivityScoped
    @FragmentScoped
    @ContributesAndroidInjector
    abstract LoginActivity loginActivity();

}
