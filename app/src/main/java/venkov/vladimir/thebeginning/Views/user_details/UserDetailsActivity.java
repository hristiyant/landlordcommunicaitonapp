package venkov.vladimir.thebeginning.Views.user_details;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;
import venkov.vladimir.thebeginning.MainActivity;
import venkov.vladimir.thebeginning.R;
import venkov.vladimir.thebeginning.Views.chat.ChatActivity;
import venkov.vladimir.thebeginning.models.User;

public class UserDetailsActivity extends DaggerAppCompatActivity implements UserDetailsContracts.Navigator{

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

        mUserDetailsFragment.setNavigator(this);
        mUserDetailsFragment.setPresenter(mUserDetailsPresenter);

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra(UserDetailsActivity.EXTRA_KEY);
        mLoggedUser = (User) intent.getSerializableExtra(UserDetailsActivity.EXTRA_KEY_LOGGED_USER);
        Log.d("proba-activity", "userToBeRated: " + user + ", mLoggedUser: " + mLoggedUser);

        int a = 1;
        mUserDetailsPresenter.setUserToShow(user);
        mUserDetailsPresenter.setLoggedUser(mLoggedUser);


        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content,mUserDetailsFragment)
                .commit();
    }

    @Override
    public void navigateWith(User userToBeRated, User loggedUser) {
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra(UserDetailsActivity.EXTRA_KEY_LOGGED_USER, loggedUser);
        intent.putExtra(UserDetailsActivity.EXTRA_KEY, userToBeRated);
        startActivity(intent);
    }

    /*@Override
    protected long getIdentifier() {
        return 0;
    }*/

}
