package venkov.vladimir.thebeginning;


import android.os.Bundle;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import venkov.vladimir.thebeginning.diconfig.DaggerAppComponent;

public class ApplicationStart extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
//        return  null;
    }
}
