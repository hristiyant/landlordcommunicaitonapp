package venkov.vladimir.thebeginning.Views.users_list;

import java.util.List;

import venkov.vladimir.thebeginning.models.User;

public interface UsersListContracts {
    interface View {
        void setPresenter(Presenter presenter);
        void showUsers(List<User> users);
        void showEmptyUsersList();
        void showError(Throwable e);
        void showLoading();
        void hideLoading();
        void showUserDetails(User user);
    }

    interface Presenter {
        void subscribe(View view);
        void loadUsers();
        void filterUsers(String pattern);
        void selectUser(User user);
        void setLoggedUser(User loggedUser);
    }

    interface Navigator {
        void navigateWith(User user);
    }
}
