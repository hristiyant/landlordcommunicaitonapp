package venkov.vladimir.thebeginning.Views.accommodation_details;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import venkov.vladimir.thebeginning.R;
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

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mPresenter.setDetails();
        String address = mCurrentAccommodation.getAddress();
        String price = "" + mCurrentAccommodation.getPrice();
        String dueDate = "" + mCurrentAccommodation.getDueDate();
        setAddress(address);
        setRent(price);
        setDueDate(dueDate);
    }

    @Override
    public void showRentIsAlreadyPayed() {
        Toast.makeText(getContext(), "Rent is already payed for this month!", Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void showPayedRent(Accommodation accommodation) {
        mCurrentAccommodation = accommodation;
        onResume();

    }

    @OnClick(R.id.btn_pay_rent)
    public void onPayRentButtonClick() {
        mPresenter.payRent();
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
}
