package venkov.vladimir.thebeginning.diconfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import venkov.vladimir.thebeginning.async.AsyncSchedulerProvider;
import venkov.vladimir.thebeginning.async.base.SchedulerProvider;

@Module
public class AsyncModule {
    @Provides
    @Singleton
    public SchedulerProvider schedulerProvider() {
        return AsyncSchedulerProvider.getInstance();
    }
}