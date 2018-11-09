package venkov.vladimir.thebeginning.Views.user_details;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ratingdialog.simple.RatingDialog;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import venkov.vladimir.thebeginning.R;
import venkov.vladimir.thebeginning.Views.notifications.ScheduleNotificationActivity;
import venkov.vladimir.thebeginning.models.User;


public class UserDetailsFragment extends Fragment implements UserDetailsContracts.View {

    private UserDetailsContracts.Presenter mPresenter;
    private UserDetailsContracts.Navigator mNavigator;

    private User mUserToBeRated;

    private User mLoggedUser;

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

    @BindView(R.id.btn_rate_user)
    Button mRateUser;




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
        refreshView();
       // mPresenter.loadUser();
    }

    private void refreshView() {
        mPresenter.setDetails();
        String firstName = mUserToBeRated.getFirstName();
        String lastName = mUserToBeRated.getLastName();
        String phoneNumber = mUserToBeRated.getPhoneNumber();
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNUmber(phoneNumber);

        String userPhotoUrl = mUserToBeRated.getImageOfTheUser();
        Picasso.get().load(userPhotoUrl).into(mUserDetailsPhoto);

        float userRating = (float) mUserToBeRated.getRating();
        ObjectAnimator anim = ObjectAnimator.ofFloat(mShowRating, "rating", 0f, userRating);
        anim.setDuration(1000);
        anim.start();
    }

    public void setFirstName(String firstName) {
        mFirstNameTextView.setText(firstName);
    }

    public void setLastName(String lastName) {
        mLastNameTextView.setText(lastName);
    }

    public void setPhoneNUmber(String phoneNumber) {
        mPhoneNumberTextView.setText(phoneNumber);
    }

    @Override
    public void setLoggedUser(User loggedUser) {
        mLoggedUser = loggedUser;
    }

    @Override
    public void showChangedUser(User user) {
        mUserToBeRated = user;
        onResume();
    }



    @Override
    public void showUser(User user) {

        setUserToBeRated(user);
        mFirstNameTextView.setText(user.getFirstName());
        mLastNameTextView.setText(user.getLastName());
        mPhoneNumberTextView.setText(user.getPhoneNumber());
        String userPhotoUrl = user.getImageOfTheUser();
        Picasso.get().load(userPhotoUrl).into(mUserDetailsPhoto);

        float userRating = (float) user.getRating();
        //mShowRating.setRating((float) user.getRating());
        //Animation for our rating bar
        ObjectAnimator anim = ObjectAnimator.ofFloat(mShowRating, "rating", 0f, userRating);
        anim.setDuration(1000);
        anim.start();
    }

    @Override
    public void setUserToBeRated(User userToBeRated) {
        mUserToBeRated = userToBeRated;
    }

    @OnClick(R.id.btn_rate_user)
    public void onBtnRateUserClicked() {
        RatingDialog mRatingDialog = new RatingDialog(getContext());
        mRatingDialog.setRatingDialogListener(new RatingDialog.RatingDialogInterFace() {
            @Override
            public void onDismiss() {
                Log.v("RATELISTERNER", "onDismiss ");
            }

            @Override
            public void onSubmit(float rating) {
                Log.v("RATELISTERNER", "onSubmit " + rating);
                mPresenter.createOrUpdateUserRating(mUserToBeRated, (double) rating);
                mRatingDialog.closeDialog();
            }

            @Override
            public void onRatingChanged(float rating) {
                Log.v("RATELISTERNER", "onRatingChanged " + rating);
            }
        });

        mRatingDialog.showDialog();
    }

    @Override
    public void setPresenter(UserDetailsContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    public void setNavigator(UserDetailsContracts.Navigator navigator) {
        mNavigator = navigator;
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
