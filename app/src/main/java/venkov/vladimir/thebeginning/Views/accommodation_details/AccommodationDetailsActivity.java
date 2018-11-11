package venkov.vladimir.thebeginning.Views.accommodation_details;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;
import venkov.vladimir.thebeginning.MainActivity;
import venkov.vladimir.thebeginning.R;
import venkov.vladimir.thebeginning.Views.chat.ChatActivity;
import venkov.vladimir.thebeginning.Views.chat.select_user.SelectUserActivity;
import venkov.vladimir.thebeginning.models.Accommodation;
import venkov.vladimir.thebeginning.models.User;

public class AccommodationDetailsActivity extends DaggerAppCompatActivity implements AccommodationDetailsContracts.Navigator{

    public static final String ACCOMMODATION_EXTRA_KEY = "AccommodationExtraKey";

    @Inject
    AccommodationDetailsFragment mAccommodationDetailsFragment;

    @Inject
    AccommodationDetailsContracts.Presenter mAccommodationDetailsPresenter;

    private User mLoggedUser;

    private Accommodation mCurrentAccommodation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accommodation_details);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);

        getSupportActionBar().setTitle("Accommodation details");

        ButterKnife.bind(this);

        mAccommodationDetailsFragment.setNavigator(this);
        mAccommodationDetailsFragment.setPresenter(mAccommodationDetailsPresenter);

        Intent intent = getIntent();
        mLoggedUser = (User) intent.getSerializableExtra(MainActivity.EXTRA_KEY);
        mCurrentAccommodation =
                (Accommodation) intent.getSerializableExtra
                        (AccommodationDetailsActivity.ACCOMMODATION_EXTRA_KEY);
        mAccommodationDetailsPresenter.setLoggedUser(mLoggedUser);
        mAccommodationDetailsPresenter.setCurrentAccommodation(mCurrentAccommodation);

        FragmentTransaction ft = getFragmentManager().beginTransaction()
                .replace(R.id.content, mAccommodationDetailsFragment);
        ft.commit();
    }

    @Override
    public void navigateWith(Accommodation currentAccommodation, User loggedUser) {
//        Intent intent = new Intent(this, ChatActivity.class);
//        intent.putExtra(MainActivity.EXTRA_KEY, loggedUser);
//        intent.putExtra(AccommodationDetailsActivity.ACCOMMODATION_EXTRA_KEY, currentAccommodation);
//        startActivity(intent);
            User targetUser;
        if (loggedUser.getLandlord()) {
            targetUser = currentAccommodation.getTenant();
        } else {
            targetUser = currentAccommodation.getLandlord();
        }
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra(SelectUserActivity.EXTRA_TARGET_USER, targetUser);
        intent.putExtra(SelectUserActivity.EXTRA_LOGGED_USER, loggedUser);
        startActivity(intent);
    }
}
