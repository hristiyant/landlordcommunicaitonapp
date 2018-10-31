package venkov.vladimir.thebeginning.Views.accommodations_list;

import java.util.List;

import venkov.vladimir.thebeginning.models.Accommodation;

public interface AccommodationsListContracts {
    interface View {
        void setPresenter(Presenter presenter);

        void showAccommodations(List<Accommodation> accommodations);

        void showEmptyAccommodationsList();

        void showError(Throwable e);

        void showLoading();

        void hideLoading();

        void showAccommodationDetails(Accommodation accommodation);
    }

    interface Presenter {
        void subscribe(View view);

        void loadAccommodations();

        void filterAccommodations(String pattern);

        void selectAccommodation(Accommodation accommodation);

        void setAccommodation(Accommodation accommodation);
    }

    interface Navigator {
        void navigateWith(Accommodation accommodation);
    }
}
