package venkov.vladimir.thebeginning.Views.accommodations_list;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import venkov.vladimir.thebeginning.async.base.SchedulerProvider;
import venkov.vladimir.thebeginning.models.Accommodation;
import venkov.vladimir.thebeginning.services.AccommodationService;

public class AccommodationsListPresenter implements AccommodationsListContracts.Presenter {

    private final AccommodationService mAccommodationService;
    private final SchedulerProvider mSchedulerProvider;
    private AccommodationsListContracts.View mView;


    @Inject
    public AccommodationsListPresenter(
            AccommodationService accommodationsService,
            SchedulerProvider schedulerProvider) {

        mAccommodationService = accommodationsService;
        mSchedulerProvider = schedulerProvider;

    }
    )

    @Override
    public void subscribe(AccommodationsListContracts.View view) {
        mView = view;
    }

    @Override
    public void loadAccommodations() {
        mView.showLoading();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<List<Accommodation>>) emitter -> {
                    List<Accommodation> accommodations = mAccommodationService.getAllAccommodations();
                    emitter.onNext(accommodations);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doFinally(mView::hideLoading)
                .subscribe(this::presentAccommodationsToView,
                        error -> mView.showError(error));

    }

    @Override
    public void filterAccommodations(String pattern) {
        mView.showLoading();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<List<Accommodation>>) emitter -> {
                    List<Accommodation> accommodations = mAccommodationService.getFilteredAccommodations(pattern);
                    emitter.onNext(accommodations);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doFinally(mView::hideLoading)
                .subscribe(this::presentAccommodationsToView,
                        error -> mView.showError(error));
    }

    @Override
    public void selectAccommodation(Accommodation accommodation) {
        mView.showAccommodationDetails(accommodation);
    }

    private void presentAccommodationsToView(List<Accommodation> accommodations) {
        if (accommodations.isEmpty()) {
            mView.showEmptyAccommodationsList();
        } else {
            mView.showAccommodations(accommodations);
        }
    }

}
