package venkov.vladimir.thebeginning.Views.login;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ratingdialog.simple.RatingDialog;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import venkov.vladimir.thebeginning.R;
import venkov.vladimir.thebeginning.models.User;

public class LoginFragment extends Fragment implements LoginContracts.View {

    @BindView(R.id.loading)
    ProgressBar mProgressBar;

    @BindView(R.id.et_phone_number)
    EditText mPhoneText;

    private LoginContracts.Navigator mNavigator;

    private LoginContracts.Presenter mPresenter;

    @Inject
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
    }

    @OnClick(R.id.btn_login)
    public void onLoginButtonClick() {
        String phoneNumber = mPhoneText.getText().toString();
        mPresenter.loginUser(phoneNumber);
    }


    void setNavigator(LoginContracts.Navigator navigator) {
        mNavigator = navigator;
    }

    @Override
    public void setPresenter(LoginContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showNoUser() {
        Toast.makeText(getContext(), "There is no such user!", Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void showError(Throwable e) {
        Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showLoggedUser(User user) {
        mNavigator.navigateWith(user);
    }
}
