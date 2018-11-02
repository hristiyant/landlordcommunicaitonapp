package venkov.vladimir.thebeginning.Views.user_details;

import venkov.vladimir.thebeginning.models.User;

public interface UserDetailsContracts {
    interface View {
        void showUser(User user);

        void setPresenter(Presenter presenter);

        void showError(Throwable e);

        void showLoading();

        void hideLoading();

    }

    interface Presenter {
        void subscribe(View view);

        void loadUser();

        void setUserId(int id);

    }
}
