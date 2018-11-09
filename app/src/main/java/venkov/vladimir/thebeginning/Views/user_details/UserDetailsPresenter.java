package venkov.vladimir.thebeginning.Views.user_details;

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
    public UserDetailsPresenter(UserService userService, SchedulerProvider schedulerProvider,
                                RatingService ratingService) {
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
        mView.setLoggedUser(mLoggedUser);
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
    public void rateUserByTakerIdAndGiverId(int takerId, int giverId, double rating) {
        mView.showLoading();

        Disposable observable = Observable.create((ObservableOnSubscribe<Double>) emitter -> {
            Double ratingReceived = mRatingService.rateTakerUserByIdAndGiverUserId(
                    takerId, giverId, rating);

            emitter.onNext(ratingReceived);
            emitter.onComplete();
        })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doOnError(mView::showError)
                .subscribe();
        loadUser();
    }

    @Override
    public void setUserToShow(User userToShow) {
        mUserToShow = userToShow;
    }

    @Override
    public void setLoggedUser(User loggedUser) {
        mLoggedUser = loggedUser;
    }
}

