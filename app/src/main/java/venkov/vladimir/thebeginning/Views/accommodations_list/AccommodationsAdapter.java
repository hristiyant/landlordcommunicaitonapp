package venkov.vladimir.thebeginning.Views.accommodations_list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import venkov.vladimir.thebeginning.R;
import venkov.vladimir.thebeginning.models.Accommodation;

public class AccommodationsAdapter extends RecyclerView.Adapter<AccommodationsAdapter.AccommodationViewHolder> {

    private List<Accommodation> mAccommodations;
    private OnAccommodationClickListener mOnAccommodationClickListener;

    @Inject
    public AccommodationsAdapter() {
        mAccommodations = new ArrayList<>();
    }

    @NonNull
    @Override
    public AccommodationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout., parent, false);
        int height = parent.getMeasuredHeight() / 3;
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.height = height;
        view.setLayoutParams(lp);
        view.setMinimumHeight(height);
        return new AccommodationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccommodationViewHolder holder, int position) {
        holder.setOnClickListener(mOnAccommodationClickListener);
        holder.bind(mAccommodations.get(position));
    }

    @Override
    public int getItemCount() {
        return mAccommodations.size();
    }

    public Accommodation getItem(int position) {
        return mAccommodations.get(position);
    }

    public void clear() {
        mAccommodations.clear();
    }

    public void addAll(List<Accommodation> accommodations) {
        mAccommodations.addAll(accommodations);
    }

    public void setOnAccommodationClickListener(OnAccommodationClickListener onAccommodationClickListener) {
        this.mOnAccommodationClickListener = onAccommodationClickListener;
    }


    public static class AccommodationViewHolder extends RecyclerView.ViewHolder {

        @BindView()
        TextView mAddress;

        @BindView()
        TextView mLandlordName;

        @BindView()
        TextView mLandlordPhoneNumber;

        private OnAccommodationClickListener mOnClickListener;
        private Accommodation mAccommodation;

        AccommodationViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void bind(Accommodation accommodation) {
            mAddress.setText(accommodation.getAddress());
            mLandlordName.setText(accommodation.getLandlord().getFirstName());
            mLandlordPhoneNumber.setText(accommodation.getLandlord().getPhoneNumber());

            //If we are going to use an ImageView and add image to the accommodation(needs ImageView)
       /* Picasso.get()
                .load(accommodation.getImageUrl())
                .into(mAccommodationImageView);*/

            mAccommodation = accommodation;
        }

        @OnClick
        public void onClick() {
            mOnClickListener.onClick(mAccommodation);
        }

        public void setOnClickListener(OnAccommodationClickListener onClickListener) {
            mOnClickListener = onClickListener;
        }

    }

    interface OnAccommodationClickListener {
        void onClick(Accommodation accommodation);
    }

}



