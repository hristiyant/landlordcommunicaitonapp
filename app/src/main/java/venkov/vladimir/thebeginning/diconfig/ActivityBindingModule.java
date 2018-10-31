package venkov.vladimir.thebeginning.diconfig;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import venkov.vladimir.thebeginning.Views.ListAllActivity;
import venkov.vladimir.thebeginning.Views.login.LoginActivity;

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
//
//    @ActivityScoped
//    @ContributesAndroidInjector(
//            modules = QuoteCreateModule.class
//    )
//    abstract QuoteCreateActivity quoteCreateActivity();
}
