package venkov.vladimir.thebeginning.Views.users_list;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;
import venkov.vladimir.thebeginning.MainActivity;
import venkov.vladimir.thebeginning.R;
import venkov.vladimir.thebeginning.Views.user_details.UserDetailsActivity;
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

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);

        getSupportActionBar().setTitle("Select user");

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
        Intent intent = new Intent(this, UserDetailsActivity.class);
        intent.putExtra(UserDetailsActivity.EXTRA_KEY, user);
        intent.putExtra(UserDetailsActivity.EXTRA_KEY_LOGGED_USER, mLoggedUser);
        startActivity(intent);
    }

}
