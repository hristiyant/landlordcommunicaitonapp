package venkov.vladimir.thebeginning.diconfig;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import venkov.vladimir.thebeginning.ApplicationStart;

import android.app.Application;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityBindingModule.class,
        ApplicationModule.class,
        ParsersModule.class,
        HttpModule.class,
        RepositoriesModule.class,
        ServicesModule.class,
        ValidatorModule.class,
        AsyncModule.class,
        ViewsModule.class,
        LoginModule.class,
        AccommodationsListModule.class
})
public interface AppComponent extends AndroidInjector<ApplicationStart> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
