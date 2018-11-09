package venkov.vladimir.thebeginning.Views.user_details;

import android.util.Log;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import venkov.vladimir.thebeginning.async.base.SchedulerProvider;
import venkov.vladimir.thebeginning.models.User;
import venkov.vladimir.thebeginning.services.RatingService;
import venkov.vladimir.thebeginning.services.UserService;

public class UserDetailsPresenter implements UserDetailsContracts.Presenter {

    private final UserService mUserService;
    private final SchedulerProvider mSchedulerProvider;
    private final RatingService mRatingService;

    private UserDetailsContracts.View mView;
    private User mUserToShow;
    private User mLoggedUser;

    @Inject
    public UserDetailsPresenter(UserService userService, SchedulerProvider schedulerProvider, RatingService ratingService) {
        this.mUserService = userService;
        this.mSchedulerProvider = schedulerProvider;
        this.mRatingService = ratingService;
    }


    @Override
    public void subscribe(UserDetailsContracts.View view) {
        mView = view;
    }

    @Override
    public void setDetails() {
        mView.setUserToBeRated(mUserToShow);
    }

    @Override
    public void loadUser() {
        mView.showLoading();

        Disposable observable = Observable.create((ObservableOnSubscribe<User>) emitter -> {
            User user = mUserService.getDetailsById(mUserToShow.getId());
            emitter.onNext(user);
            emitter.onComplete();
        })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doOnError(mView::showError)
                .subscribe(mView::showUser);
    }

    @Override
    public void createOrUpdateUserRating(User ratedUser, double ratingValue) {
        mView.showLoading();

        Disposable observable = Observable.create((ObservableOnSubscribe<User>) emitter -> {
            mRatingService.createOrUpdateUserRating(ratedUser.getId(), mLoggedUser.getId(),
                    ratingValue);
            User user = mUserService.getDetailsById(mUserToShow.getId());
            int bp = 6;
            emitter.onNext(user);
            emitter.onComplete();
        })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doFinally(mView::hideLoading)
                .doOnError(mView::showError)
                .subscribe(this::presentChangedUser,
                        error -> mView.showError(error));
    }

    private void presentChangedUser(User user) {

        setUserToShow(user);
        mView.showChangedUser(user);
        int b = 5;
    }

    @Override
    public void setUserToShow(User userToShow) {
        //mView.setUserToBeRated(userToShow);
        mUserToShow = userToShow;
        Log.d("hrs1", mUserToShow.toString());
    }

    @Override
    public void setLoggedUser(User loggedUser) {
        mLoggedUser = loggedUser;
        Log.d("hrs1", "mLoggedUser: " + mLoggedUser);
    }


}

