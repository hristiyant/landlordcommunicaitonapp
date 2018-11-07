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

    }

    interface Presenter {
        void subscribe(View view);

        void loadUser();

        void setUserToShow(User userToShow);

        void setDetails();

        void setLoggedUser(User loggedUser);
    }
}
