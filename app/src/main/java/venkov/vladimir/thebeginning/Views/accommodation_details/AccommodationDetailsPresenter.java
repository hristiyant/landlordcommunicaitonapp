package venkov.vladimir.thebeginning.Views.accommodation_details;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import venkov.vladimir.thebeginning.async.base.SchedulerProvider;
import venkov.vladimir.thebeginning.models.Accommodation;
import venkov.vladimir.thebeginning.models.User;
import venkov.vladimir.thebeginning.services.AccommodationService;

public class AccommodationDetailsPresenter implements AccommodationDetailsContracts.Presenter {

    private AccommodationDetailsContracts.View mView;

    private Accommodation mCurrentAccommodation;

    private User mLoggedUser;

    private final AccommodationService mAccommodationService;
    private final SchedulerProvider mSchedulerProvider;

    @Inject
    public AccommodationDetailsPresenter(AccommodationService accommodationService, SchedulerProvider schedulerProvider) {
        mAccommodationService = accommodationService;
        mSchedulerProvider = schedulerProvider;
    }

    @Override
    public void subscribe(AccommodationDetailsContracts.View view) {
        mView = view;
    }

    @Override
    public void startChat() {
        mView.showChat(mCurrentAccommodation, mLoggedUser);
    }

    @Override
    public void setLoggedUser(User loggedUser) {
        mLoggedUser = loggedUser;
    }

    @Override
    public void setCurrentAccommodation(Accommodation currentAccommodation) {
        mCurrentAccommodation = currentAccommodation;
    }

    @Override
    public void setDetails() {
        if (!mLoggedUser.getLandlord()) {
            mView.showPayRentButton();
        }
        mView.setCurrentAccommodation(mCurrentAccommodation);

    }

    @Override
    public void payRent(Accommodation accommodation) {
        mView.showLoading();
        mCurrentAccommodation = accommodation;
        Disposable observable = Observable
                .create((ObservableOnSubscribe<Accommodation>) emitter -> {
                    Accommodation accommodationAfterPayment =
                            mAccommodationService.payRentForAccommodation(accommodation.getId(),
                                    accommodation);
                    emitter.onNext(accommodationAfterPayment);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doFinally(mView::hideLoading)
                .subscribe(this::presentChangedAccommodation,
                        error -> mView.showError(error));
    }

    private void presentChangedAccommodation(Accommodation accommodation) {
        if (accommodation.equals(mCurrentAccommodation)) {
            int b = 5;
            mView.noChangeWasMade();
        } else {
            mView.showChangedAccommodation(accommodation);
            setCurrentAccommodation(accommodation);
        }
    }

    @Override
    public void changeRent(Accommodation accommodation, double newRent) {
        mView.showLoading();
        mCurrentAccommodation = accommodation;
        Disposable observable = Observable
                .create((ObservableOnSubscribe<Accommodation>) emitter -> {
                    Accommodation accommodationAfterChange =
                            mAccommodationService
                                    .editRent(accommodation.getId(), accommodation, newRent);
                    emitter.onNext(accommodationAfterChange);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doFinally(mView::hideLoading)
                .subscribe(this::presentChangedAccommodation,
                        error -> mView.showError(error));
    }
}
