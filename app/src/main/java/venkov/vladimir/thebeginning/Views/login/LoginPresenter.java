package venkov.vladimir.thebeginning.Views.login;

import android.content.Intent;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import venkov.vladimir.thebeginning.MainActivity;
import venkov.vladimir.thebeginning.async.base.SchedulerProvider;
import venkov.vladimir.thebeginning.models.User;
import venkov.vladimir.thebeginning.services.UserService;

public class LoginPresenter implements LoginContracts.Presenter {

    private final UserService mUserService;
    private final SchedulerProvider mSchedulerProvider;
    private LoginContracts.View mView;
    private User mLoggedUser;

    @Inject
    public LoginPresenter(UserService mUserService, SchedulerProvider mSchedulerProvider) {
        this.mUserService = mUserService;
        this.mSchedulerProvider = mSchedulerProvider;
    }

    @Override
    public void subscribe(LoginContracts.View view) {
        mView = view;
    }

    @Override
    public void loginUser(String phone) {
        mView.showLoading();
        Disposable observable = Observable.create((ObservableOnSubscribe<User>) emitter -> {
            User userToLogin = mUserService.getUserByPhoneNumber(phone);
            emitter.onNext(userToLogin);
            emitter.onComplete();
        }).subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doFinally(mView::hideLoading)
                .subscribe(this::loginThisUser,
                        error -> mView.showError(error));
    }

    private void loginThisUser(User user) {
        if (user == null) {
            mView.showNoUser();
        } else {
            mView.showLoggedUser(user);
        }
    }

    @Override
    public void selectUser(User user) {

    }

    @Override
    public void setLoggedUser(User loggedUser) {
    }
}
