package venkov.vladimir.thebeginning.Views;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;
import venkov.vladimir.thebeginning.R;
import venkov.vladimir.thebeginning.Views.detailUser.DetailUserActivity;
import venkov.vladimir.thebeginning.models.User;

public class ListAllActivity extends DaggerAppCompatActivity implements UsersListContracts.Navigator {

    @Inject
    UsersListFragment mUsersListFragment;

    @Inject
    UsersListContracts.Presenter mUsersListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all);

        ButterKnife.bind(this);

        int b = 5;
        mUsersListFragment.setNavigator(this);
        mUsersListFragment.setPresenter(mUsersListPresenter);

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
