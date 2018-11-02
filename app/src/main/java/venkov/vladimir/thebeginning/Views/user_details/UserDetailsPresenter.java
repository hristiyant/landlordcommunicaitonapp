package venkov.vladimir.thebeginning.Views.user_details;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import venkov.vladimir.thebeginning.async.base.SchedulerProvider;
import venkov.vladimir.thebeginning.models.User;
import venkov.vladimir.thebeginning.services.UserService;

public class UserDetailsPresenter implements UserDetailsContracts.Presenter {

    private final UserService mUserService;
    private final SchedulerProvider mSchedulerProvider;

    private UserDetailsContracts.View mView;
    private int mUserId;

    @Inject
    public UserDetailsPresenter(UserService userService, SchedulerProvider schedulerProvider) {
        this.mUserService = userService;
        this.mSchedulerProvider = schedulerProvider;
    }


    @Override
    public void subscribe(UserDetailsContracts.View view) {
        mView = view;
    }

    @Override
    public void loadUser() {
        mView.showLoading();

        Disposable observable = Observable.create((ObservableOnSubscribe<User>) emitter -> {
            User user = mUserService.getDetailsById(mUserId);
            emitter.onNext(user);
            emitter.onComplete();
        })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doOnError(mView::showError)
                .subscribe(mView::showUser);
    }

    @Override
    public void setUserId(int id) {
        mUserId = id;
    }
}

