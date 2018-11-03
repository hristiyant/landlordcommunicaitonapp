package venkov.vladimir.thebeginning.async;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import venkov.vladimir.thebeginning.async.base.SchedulerProvider;

public class AsyncSchedulerProvider implements SchedulerProvider {

    private static SchedulerProvider instance;

    public AsyncSchedulerProvider() {
        //Required empty private constructor for Singleton
    }

    public static SchedulerProvider getInstance() {
        if (instance == null) {
            instance = new AsyncSchedulerProvider();
        }
        return instance;
    }

    @Override
    public Scheduler background() {
        return Schedulers.io();
    }

    @Override
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }
}
