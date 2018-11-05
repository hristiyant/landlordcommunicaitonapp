package venkov.vladimir.thebeginning.Views.accommodation_details;

import venkov.vladimir.thebeginning.models.Accommodation;
import venkov.vladimir.thebeginning.models.User;

public interface AccommodationDetailsContracts {

    interface View {
        void setPresenter(AccommodationDetailsContracts.Presenter presenter);

        void showError(Throwable e);

        void showChat(Accommodation accommodation, User user);

        void setCurrentAccommodation(Accommodation currentAccommodation);

        void showRentIsAlreadyPayed();

        void showPayedRent(Accommodation accommodation);
    }

    interface Presenter {
        void subscribe(AccommodationDetailsContracts.View view);

        void startChat();

        void setLoggedUser(User loggedUser);

        void setDetails();

        void setCurrentAccommodation(Accommodation currentAccommodation);

        void payRent(Accommodation accommodation);
    }

    interface Navigator {
        void navigateWith(Accommodation currentAccommodation, User loggedUser);
    }
}
