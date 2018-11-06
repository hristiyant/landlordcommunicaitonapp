package venkov.vladimir.thebeginning.Views.accommodation_details;

import venkov.vladimir.thebeginning.models.Accommodation;
import venkov.vladimir.thebeginning.models.User;

public interface AccommodationDetailsContracts {

    interface View {
        void setPresenter(AccommodationDetailsContracts.Presenter presenter);

        void showError(Throwable e);

        void showChat(Accommodation accommodation, User user);

        void setCurrentAccommodation(Accommodation currentAccommodation);

        void noChangeWasMade();

        void showChangedAccommodation(Accommodation accommodation);

        void showPayRentButton();

        void showLoading();

        void hideLoading();
    }

    interface Presenter {
        void subscribe(AccommodationDetailsContracts.View view);

        void startChat();

        void setLoggedUser(User loggedUser);

        void setDetails();

        void setCurrentAccommodation(Accommodation currentAccommodation);

        void payRent(Accommodation accommodation);

        void changeRent(Accommodation currentAccommodation, double newRent);
    }

    interface Navigator {
        void navigateWith(Accommodation currentAccommodation, User loggedUser);
    }
}
