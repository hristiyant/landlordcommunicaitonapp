package venkov.vladimir.thebeginning.Views.accommodation_details;

import java.util.List;

import venkov.vladimir.thebeginning.Views.accommodations_list.AccommodationsListContracts;
import venkov.vladimir.thebeginning.models.Accommodation;
import venkov.vladimir.thebeginning.models.User;

public interface AccommodationDetailsContracts {

    interface View {
        void setPresenter(AccommodationDetailsContracts.Presenter presenter);

        void showError(Throwable e);

        void showChat(Accommodation accommodation, User user);

        void setCurrentAccommodation(Accommodation currentAccommodation);
    }

    interface Presenter {
        void subscribe(AccommodationDetailsContracts.View view);

        void startChat();

        void setLoggedUser(User loggedUser);

        void setDetails();

        void setCurrentAccommodation(Accommodation currentAccommodation);
    }

    interface Navigator {
        void navigateWith(Accommodation currentAccommodation, User loggedUser);
    }
}
