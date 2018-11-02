package venkov.vladimir.thebeginning.Views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;
import venkov.vladimir.thebeginning.R;
import venkov.vladimir.thebeginning.models.User;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

    private List<User> mUsers;
    private OnUserClickListener mOnUserClickListener;

    @Inject
    public UsersAdapter() {
        mUsers = new ArrayList<>();
    }

    @NonNull
    @Override
    public UsersAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);
        int height = parent.getMeasuredHeight() / 3;
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.height = height;
        view.setLayoutParams(lp);
        view.setMinimumHeight(height);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.UserViewHolder holder, int position) {
        holder.setOnClickListener(mOnUserClickListener);
        holder.bind(mUsers.get(position));
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public User getItem(int position) {
        return mUsers.get(position);
    }

    public void clear() {
        mUsers.clear();
    }

    public void addAll(List<User> users) {
        mUsers.addAll(users);
    }

    public void setOnQuoteClickListener(OnUserClickListener onUserClickListener) {
        this.mOnUserClickListener = onUserClickListener;
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_name)
        TextView mUserNameTextView;

        @BindView(R.id.tv_phone_number)
        TextView mPhoneNumber;

//        @BindView(R.id.tv_content)
//        TextView mContentTextView;

        @BindView(R.id.iv_user_photo)
        ImageView mUserPhoto;


        private OnUserClickListener mOnUserClickListener;
        private User mUser;

        public UserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(User user) {
            String text = user.getFirstName() + " " + user.getLastName();
            mUserNameTextView.setText(text);
            mPhoneNumber.setText(user.getPhoneNumber());
            String userPhotoUrl = user.getImageOfTheUser();
            Picasso.get().load(userPhotoUrl).into(mUserPhoto);
            mUser = user;
        }

        @OnClick
        public void onClick() {
            mOnUserClickListener.onClick(mUser);
        }

        public void setOnClickListener(OnUserClickListener onUserClickListener) {
            mOnUserClickListener = onUserClickListener;
        }
    }

    interface OnUserClickListener {
        void onClick(User user);
    }

}

