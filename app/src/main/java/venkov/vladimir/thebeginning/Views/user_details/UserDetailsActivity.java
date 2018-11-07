package venkov.vladimir.thebeginning.Views.user_details;

import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;
import venkov.vladimir.thebeginning.R;
import venkov.vladimir.thebeginning.models.User;

public class UserDetailsActivity extends DaggerAppCompatActivity{

    public static final String EXTRA_KEY = "USER_EXTRA_KEY";
    public static final String EXTRA_KEY_LOGGED_USER = "LOGGED_USER_EXTRA_KEY";

    @Inject
    UserDetailsFragment mUserDetailsFragment;

    @Inject
    UserDetailsContracts.Presenter mUserDetailsPresenter;

    private User mLoggedUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra(UserDetailsActivity.EXTRA_KEY);
        mLoggedUser = (User) intent.getSerializableExtra(UserDetailsActivity.EXTRA_KEY_LOGGED_USER);

        int a = 1;
        mUserDetailsPresenter.setUserToShow(user);
        mUserDetailsPresenter.setLoggedUser(mLoggedUser);
        mUserDetailsFragment.setPresenter(mUserDetailsPresenter);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content,mUserDetailsFragment)
                .commit();
    }

    /*@Override
    protected long getIdentifier() {
        return 0;
    }*/

}
