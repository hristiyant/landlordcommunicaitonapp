package venkov.vladimir.thebeginning.Views.user_details;

import venkov.vladimir.thebeginning.models.User;

public interface UserDetailsContracts {
    interface View {
        void showUser(User user);

        void setPresenter(Presenter presenter);

        void showError(Throwable e);

        void showLoading();

        void hideLoading();

        void setLoggedUser(User loggedUser);

        void setUserToBeRated(User userToBeRated);

        void showChangedUser(User user);
    }

    interface Presenter {
        void subscribe(View view);

        void loadUser();

        void setUserToShow(User userToShow);

        void setDetails();

        void setLoggedUser(User loggedUser);

        void createOrUpdateUserRating(User ratedUser, double ratingValue);
    }

    interface Navigator {
        void navigateWith(User userToBeRated, User loggedUser);
    }
}
