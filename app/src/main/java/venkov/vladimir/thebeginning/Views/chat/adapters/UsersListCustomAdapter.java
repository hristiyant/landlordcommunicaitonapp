package venkov.vladimir.thebeginning.Views.chat.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import venkov.vladimir.thebeginning.R;
import venkov.vladimir.thebeginning.models.User;

public class UsersListCustomAdapter extends RecyclerView.Adapter<UsersListCustomAdapter.ViewHolder> {

    private ArrayList<User> mUsersList;
    private ItemClickListener mClickListener;
    private LayoutInflater mInflater;

    public UsersListCustomAdapter(Context context, ArrayList<User> mUsersList) {
        this.mUsersList = new ArrayList<>(mUsersList);
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.select_user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User mUser = mUsersList.get(position);
        String name = mUser.getFirstName() + " " + mUser.getLastName();
        holder.mUserName.setText(name);

        String img_url = mUser.getImageOfTheUser();

        Picasso.get().load(img_url).into(holder.mUserPhoto);
    }

    @Override
    public int getItemCount() {
        return mUsersList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mUserName;
        CircleImageView mUserPhoto;

        ViewHolder(View itemView) {
            super(itemView);
            mUserName = itemView.findViewById(R.id.tv_user_name);
            mUserPhoto = itemView.findViewById(R.id.iv_user_photo);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public User getItem(int position) {
        return mUsersList.get(position);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
