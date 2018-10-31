package venkov.vladimir.thebeginning.Views;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import java.io.Serializable;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;
import venkov.vladimir.thebeginning.MainActivity;
import venkov.vladimir.thebeginning.R;
import venkov.vladimir.thebeginning.Views.detailUser.DetailUserActivity;
import venkov.vladimir.thebeginning.models.User;

public class ListAllActivity extends DaggerAppCompatActivity implements UsersListContracts.Navigator {

    @Inject
    UsersListFragment mUsersListFragment;

    @Inject
    UsersListContracts.Presenter mUsersListPresenter;

    private User mLoggedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all);

        ButterKnife.bind(this);

        mUsersListFragment.setNavigator(this);
        mUsersListFragment.setPresenter(mUsersListPresenter);
        Intent intent = getIntent();
        mLoggedUser = (User) intent.getSerializableExtra(MainActivity.EXTRA_KEY);
        mUsersListPresenter.setLoggedUser(mLoggedUser);

        FragmentTransaction ft = getFragmentManager().beginTransaction()
                .replace(R.id.content, mUsersListFragment);

        ft.commit();
    }

    @Override
    public void navigateWith(User user) {
//        Intent intent = new Intent(this, DetailUserActivity.class);
//        intent.putExtra(DetailUserActivity.EXTRA_KEY, user);
//        startActivity(intent);
    }

}
