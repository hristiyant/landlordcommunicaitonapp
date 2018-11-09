package venkov.vladimir.thebeginning.Views.users_list;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import venkov.vladimir.thebeginning.R;
import venkov.vladimir.thebeginning.models.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsersListFragment extends Fragment implements UsersListContracts.View, UsersAdapter.OnUserClickListener {

    private UsersListContracts.Navigator mNavigator;

    @BindView(R.id.lv_users)
    RecyclerView mUsersView;

    @BindView(R.id.loading)
    ProgressBar mProgressBar;

    @BindView(R.id.et_filter)
    EditText mFilteredText;

    @Inject
    UsersAdapter mUsersAdapter;

    private UsersListContracts.Presenter mPresenter;
    private GridLayoutManager mUsersViewLayoutManager;

    @Inject
    public UsersListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_users_list, container, false);

        ButterKnife.bind(this, view);

        mUsersAdapter.setOnUserClickListener(this);
        mUsersView.setAdapter(mUsersAdapter);
        mUsersViewLayoutManager = new GridLayoutManager(getContext(), 2);
        mUsersView.setLayoutManager(mUsersViewLayoutManager);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mPresenter.loadUsers();
    }

    @Override
    public void onClick(User user) {
        mPresenter.selectUser(user);
        Log.d("hrs", "userToBeRated: " + user.toString());
    }

    @Override
    public void setPresenter(UsersListContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showUsers(List<User> users) {
        mUsersAdapter.clear();
        mUsersAdapter.addAll(users);
        mUsersAdapter.notifyDataSetChanged();
    }

    @Override
    public void showEmptyUsersList() {
        Toast.makeText(getContext(), "No users to show ...", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError(Throwable e) {
        Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
        mUsersView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mUsersView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showUserDetails(User user) {
        mNavigator.navigateWith(user);
    }

    void setNavigator(UsersListContracts.Navigator navigator) {
        mNavigator = navigator;
    }

    @OnTextChanged(R.id.et_filter)
    public void onTextChanged() {
        String pattern = mFilteredText.getText().toString();
        mPresenter.filterUsers(pattern);
    }
}
