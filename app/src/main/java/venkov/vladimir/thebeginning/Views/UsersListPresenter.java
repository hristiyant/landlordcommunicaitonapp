package venkov.vladimir.thebeginning.Views;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import venkov.vladimir.thebeginning.async.base.SchedulerProvider;
import venkov.vladimir.thebeginning.models.User;
import venkov.vladimir.thebeginning.services.UserService;


public class UsersListPresenter implements UsersListContracts.Presenter {

    private final UserService mUserService;
    private final SchedulerProvider mSchedulerProvider;
    private UsersListContracts.View mView;

    @Inject
    public UsersListPresenter(SchedulerProvider schedulerProvider, UserService userService) {
        mSchedulerProvider = schedulerProvider;
        mUserService = userService;
    }

    @Override
    public void subscribe(UsersListContracts.View view) {
        mView = view;
    }

    @Override
    public void loadUsers() {
        mView.showLoading();
        Disposable observable = Observable.create((ObservableOnSubscribe<List<User>>) emitter -> {
            List<User> users = mUserService.getAllUsers();
            emitter.onNext(users);
            emitter.onComplete();
        }).subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doFinally(mView::hideLoading)
                .subscribe(this::presentUsersToView,
                        error -> mView.showError(error));
    }

    @Override
    public void filterUsers(String pattern) {
        mView.showLoading();
        Disposable observable = Observable.create((ObservableOnSubscribe<List<User>>) emitter -> {
            List<User> users = mUserService.getFilteredUsers(pattern);
            emitter.onNext(users);
            emitter.onComplete();
        }).subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doFinally(mView::hideLoading)
                .subscribe(this::presentUsersToView,
                        error -> mView.showError(error));
    }

    public void selectUser(User user) {
        mView.showUserDetails(user);
    }

    private void presentUsersToView(List<User> users) {
        if (users.isEmpty()) {
            mView.showEmptyUsersList();
        } else {
            mView.showUsers(users);
        }
    }
}
