package venkov.vladimir.thebeginning.Views.accommodations_list;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;
import venkov.vladimir.thebeginning.MainActivity;
import venkov.vladimir.thebeginning.R;
import venkov.vladimir.thebeginning.Views.accommodation_details.AccommodationDetailsActivity;
import venkov.vladimir.thebeginning.models.Accommodation;
import venkov.vladimir.thebeginning.models.User;

public class AccommodationsListActivity extends DaggerAppCompatActivity
        implements AccommodationsListContracts.Navigator{

    @Inject
    AccommodationsListFragment mAccommodationsListFragment;

    @Inject
    AccommodationsListContracts.Presenter mAccommodationsListPresenter;

    private User mLoggedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accommodations_list);

        ButterKnife.bind(this);

        mAccommodationsListFragment.setNavigator(this);
        mAccommodationsListFragment.setPresenter(mAccommodationsListPresenter);

        Intent intent = getIntent();
        mLoggedUser = (User) intent.getSerializableExtra(MainActivity.EXTRA_KEY);
        mAccommodationsListPresenter.setLoggedUser(mLoggedUser);

        FragmentTransaction ft = getFragmentManager().beginTransaction()
                .replace(R.id.content,mAccommodationsListFragment);
        ft.commit();
    }


    @Override
    public void navigateWith(Accommodation accommodation) {
        Intent intent = new Intent(this, AccommodationDetailsActivity.class);
        intent.putExtra(MainActivity.EXTRA_KEY, mLoggedUser);
        intent.putExtra(AccommodationDetailsActivity.ACCOMMODATION_EXTRA_KEY, accommodation);
        startActivity(intent);
    }
}
