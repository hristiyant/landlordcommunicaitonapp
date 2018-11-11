package venkov.vladimir.thebeginning.Views.accommodation_details;


import android.Manifest;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import venkov.vladimir.thebeginning.R;
import venkov.vladimir.thebeginning.Views.notifications.ScheduleNotificationActivity;
import venkov.vladimir.thebeginning.models.Accommodation;
import venkov.vladimir.thebeginning.models.User;


public class AccommodationDetailsFragment extends Fragment implements AccommodationDetailsContracts.View{

    private AccommodationDetailsContracts.Navigator mNavigator;

    private AccommodationDetailsContracts.Presenter mPresenter;

    private Accommodation mCurrentAccommodation;

    @BindView(R.id.tv_address)
    TextView mAddress;

    @BindView(R.id.tv_rent)
    TextView mRent;

    @BindView(R.id.tv_due_date)
    TextView mDueDate;

    @BindView(R.id.btn_pay_rent)
    Button mPayRentButton;

    @BindView(R.id.et_pay_rent)
    EditText mChangeRent;

    @BindView(R.id.btn_edit_rent)
    Button mEditRentButton;

    @BindView(R.id.loading)
    ProgressBar mProgressBar;

    private GoogleMap mGoogleMap;

    @Inject
    public AccommodationDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_accommodation_details, container, false);

        ButterKnife.bind(this, view);

        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.SET_ALARM)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.SET_ALARM}, 1);
        }

        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.REQUEST_COMPANION_RUN_IN_BACKGROUND)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.REQUEST_COMPANION_RUN_IN_BACKGROUND}, 1);
        }
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.RECEIVE_WAP_PUSH)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.RECEIVE_WAP_PUSH}, 1);
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        refreshView();
    }

    private void refreshView() {
        mPresenter.setDetails();
        String address = mCurrentAccommodation.getAddress();
        String price = "" + mCurrentAccommodation.getPrice();

        Date date = new Date(mCurrentAccommodation.getDueDate().getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateFormatted = formatter.format(date);
        setAddress(address);
        setRent("$ " + price);
        setDueDate("Due date: " + dateFormatted);

        LatLng coordinates = new LatLng(
                mCurrentAccommodation.getLatitude(), mCurrentAccommodation.getLongitude());

        try {
            initializeMap(coordinates);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showPayRentButton() {
        mPayRentButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void noChangeWasMade() {
        Toast.makeText(getContext(), "No Change was made!", Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void showChangedAccommodation(Accommodation accommodation) {
        mCurrentAccommodation = accommodation;
        onResume();
        Intent intent = new Intent(getContext(), ScheduleNotificationActivity.class);
        startActivity(intent);

    }

    @OnClick(R.id.btn_edit_rent)
    public void onEditRentClick() {
        if (mChangeRent.getVisibility() == View.GONE) {
            mChangeRent.setVisibility(View.VISIBLE);
        } else {
            double newRent = Double.parseDouble(mChangeRent.getText().toString());
            mPresenter.changeRent(mCurrentAccommodation, newRent);
            mChangeRent.setVisibility(View.GONE);
        }
    }



    @OnClick(R.id.btn_pay_rent)
    public void onPayRentButtonClick() {
        mPresenter.payRent(mCurrentAccommodation);
    }

    @OnClick(R.id.btn_start_chat_activity)
    public void onStartChatButtonClick() {
        mPresenter.startChat();
    }

    public void setAddress(String address) {
        mAddress.setText(address);
    }

    public void setRent(String rent) {
        mRent.setText(rent);
    }

    public void setDueDate(String duedate) {
        mDueDate.setText(duedate);
    }

    @Override
    public void setPresenter(AccommodationDetailsContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showError(Throwable e) {
        Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void showChat(Accommodation accommodation, User user) {
        mNavigator.navigateWith(accommodation, user);
    }

    @Override
    public void setCurrentAccommodation(Accommodation currentAccommodation) {
        mCurrentAccommodation = currentAccommodation;
    }

    void setNavigator(AccommodationDetailsContracts.Navigator navigator) {
        mNavigator = navigator;
    }

    private void initializeMap(LatLng coordinates) {
        if (mGoogleMap == null) {
//            ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
            ((MapFragment) getChildFragmentManager().findFragmentById(R.id.map))
                    .getMapAsync(googleMap1 -> {
                        mGoogleMap = googleMap1;
                        if (mGoogleMap == null) {
                            Toast.makeText(getContext(),
                                    "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                                    .show();
                        } else {
                            MarkerOptions marker = new MarkerOptions().position(coordinates)
                                    .title(mCurrentAccommodation.getAddress());
                            mGoogleMap.addMarker(marker);

                            CameraPosition cameraPosition = new CameraPosition.Builder().target(
                                    coordinates).zoom(16).build();

                            mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                        }
                    });
        }
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }
}
