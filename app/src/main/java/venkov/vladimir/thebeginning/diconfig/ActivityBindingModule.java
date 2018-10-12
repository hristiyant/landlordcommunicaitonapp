package venkov.vladimir.thebeginning.diconfig;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import venkov.vladimir.thebeginning.Views.ListAllActivity;

@Module
public abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(
//            modules = ListAllModule.class
    )
    abstract ListAllActivity listAllActivity();
//
//    @ActivityScoped
//    @ContributesAndroidInjector(
//            modules = QuoteDetailsModule.class
//    )
//    abstract QuoteDetailsActivity quoteDetailsActivity();
//
//    @ActivityScoped
//    @ContributesAndroidInjector(
//            modules = QuoteCreateModule.class
//    )
//    abstract QuoteCreateActivity quoteCreateActivity();
}
