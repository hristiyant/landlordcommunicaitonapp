package venkov.vladimir.thebeginning.Views.user_details;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import venkov.vladimir.thebeginning.R;
import venkov.vladimir.thebeginning.models.User;


public class UserDetailsFragment extends Fragment implements UserDetailsContracts.View{

    private UserDetailsContracts.Presenter mPresenter;

    @BindView(R.id.iv_user_details_photo)
    ImageView mUserDetailsPhoto;

    @BindView(R.id.tv_first_name)
    TextView mFirstNameTextView;

    @BindView(R.id.tv_last_name)
    TextView mLastNameTextView;

    @BindView(R.id.tv_phone_number)
    TextView mPhoneNumberTextView;

    @BindView(R.id.rb_show_rating)
    RatingBar mShowRating;





    @Inject
    public UserDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user_details, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mPresenter.loadUser();
    }

    @Override
    public void showUser(User user) {
        mFirstNameTextView.setText(user.getFirstName());
        mLastNameTextView.setText(user.getLastName());
        mPhoneNumberTextView.setText(user.getPhoneNumber());
        String userPhotoUrl = user.getImageOfTheUser();
        Picasso.get().load(userPhotoUrl).into(mUserDetailsPhoto);

        double a = user.getRating();
        mShowRating.setRating((float) user.getRating());
    }


    @Override
    public void setPresenter(UserDetailsContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showError(Throwable e) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

}
