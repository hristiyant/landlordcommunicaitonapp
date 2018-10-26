package venkov.vladimir.thebeginning.Views;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;

import butterknife.ButterKnife;
import venkov.vladimir.thebeginning.R;
import venkov.vladimir.thebeginning.Views.detailUser.DetailUserActivity;
import venkov.vladimir.thebeginning.models.User;

public class ListAllActivity extends Activity implements UsersListContracts.Navigator {

    @Inject
    UsersListFragment mUsersListFragment;

    @Inject
    UsersListContracts.Presenter mUsersListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all);

        ButterKnife.bind(this);

        mUsersListFragment.setNavigator(this);
        mUsersListFragment.setPresenter(mUsersListPresenter);

        FragmentTransaction ft = getFragmentManager().beginTransaction()
                .replace(R.id.content, mUsersListFragment);

        ft.commit();
    }

    @Override
    public void navigateWith(User user) {
        Intent intent = new Intent(this, DetailUserActivity.class);
        intent.putExtra(DetailUserActivity.EXTRA_KEY, user);
        startActivity(intent);
    }

}
