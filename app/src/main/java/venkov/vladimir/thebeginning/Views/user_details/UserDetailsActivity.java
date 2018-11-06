package venkov.vladimir.thebeginning.Views.user_details;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;
import venkov.vladimir.thebeginning.R;
import venkov.vladimir.thebeginning.models.User;

public class UserDetailsActivity extends DaggerAppCompatActivity{

    public static final String EXTRA_KEY = "USER_EXTRA_KEY";

    @Inject
    UserDetailsFragment mUserDetailsFragment;

    @Inject
    UserDetailsContracts.Presenter mUserDetailsPresenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra(UserDetailsActivity.EXTRA_KEY);

        int a = 1;
        mUserDetailsPresenter.setUserId(user.getId());
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
