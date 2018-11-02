package venkov.vladimir.thebeginning.Views.accommodation_details;

import javax.inject.Inject;

import venkov.vladimir.thebeginning.models.Accommodation;
import venkov.vladimir.thebeginning.models.User;

public class AccommodationDetailsPresenter implements AccommodationDetailsContracts.Presenter {

    private AccommodationDetailsContracts.View mView;

    private Accommodation mCurrentAccommodation;

    private User mLoggedUser;

    @Inject
    public AccommodationDetailsPresenter() {
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
    public void setDetails() {
        mView.setCurrentAccommodation(mCurrentAccommodation);
    }

    @Override
    public void setCurrentAccommodation(Accommodation currentAccommodation) {
        mCurrentAccommodation = currentAccommodation;
    }
}
