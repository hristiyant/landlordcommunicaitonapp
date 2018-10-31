package venkov.vladimir.thebeginning.Views.login;

import java.util.List;

import venkov.vladimir.thebeginning.Views.UsersListContracts;
import venkov.vladimir.thebeginning.models.User;

public interface LoginContracts {
    interface View {
        void setPresenter(UsersListContracts.Presenter presenter);
        void showNoUser();
        void showError(Throwable e);
        void showLoading();
        void hideLoading();
        void showUserDetails(User user);
    }

    interface Presenter {
        void subscribe(UsersListContracts.View view);
        void loadUsers();
        void selectUser(User user);
        void setLoggedUser(User loggedUser);
    }

    interface Navigator {
        void navigateWith(User user);
    }
}
