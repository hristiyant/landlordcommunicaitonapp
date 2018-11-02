package venkov.vladimir.thebeginning.Views.accommodations_list;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import venkov.vladimir.thebeginning.models.Accommodation;

public class AccommodationsListFragment
        extends Fragment
        implements AccommodationsListContracts.View, AccommodationsAdapter.OnAccommodationClickListener {

    private AccommodationsListContracts.Navigator mNavigator;

    @BindView(R.id.lv_accommodations)
    RecyclerView mAccommodationsView;

    @BindView(R.id.loading)
    ProgressBar mLoadingView;

    @BindView(R.id.et_filter)
    EditText mFilterEditText;

    @Inject
    AccommodationsAdapter mAccommodationsAdapter;

    private AccommodationsListContracts.Presenter mPresenter;
    private GridLayoutManager mAccommodationsViewLayoutManager;

    @Inject
    public AccommodationsListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_accommodations_list, container, false);

        // ButterKnife is applied
        ButterKnife.bind(this, view);

        mAccommodationsAdapter.setOnAccommodationClickListener(this);

        mAccommodationsView.setAdapter(mAccommodationsAdapter);
        mAccommodationsViewLayoutManager = new GridLayoutManager(getContext(), 1);
        mAccommodationsView.setLayoutManager(mAccommodationsViewLayoutManager);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mPresenter.loadAccommodations();
    }

    @Override
    public void setPresenter(AccommodationsListContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showAccommodations(List<Accommodation> superheroes) {
        mAccommodationsAdapter.clear();
        mAccommodationsAdapter.addAll(superheroes);
        mAccommodationsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showEmptyAccommodationsList() {
        Toast.makeText(getContext(),
                "No Accommodations",
                Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void showError(Throwable e) {
        Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void showLoading() {
        mAccommodationsView.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mAccommodationsView.setVisibility(View.VISIBLE);
        mLoadingView.setVisibility(View.GONE);
    }

    @Override
    public void showAccommodationDetails(Accommodation accommodation) {
        mNavigator.navigateWith(accommodation);
    }

    void setNavigator(AccommodationsListContracts.Navigator navigator) {
        mNavigator = navigator;
    }

    @OnTextChanged(R.id.et_filter)
    public void onTextChanged() {
        String pattern = mFilterEditText.getText().toString();
        mPresenter.filterAccommodations(pattern);
    }

    @Override
    public void onClick(Accommodation accommodation) {
        mPresenter.selectAccommodation(accommodation);
    }
}

