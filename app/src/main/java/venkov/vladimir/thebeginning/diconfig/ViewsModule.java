package venkov.vladimir.thebeginning.diconfig;


import android.content.Context;
import android.support.v7.widget.RecyclerView;

import dagger.Module;
import dagger.Provides;
import venkov.vladimir.thebeginning.Views.UsersAdapter;

@Module
public class ViewsModule {
    @Provides
    public RecyclerView.Adapter<UsersAdapter.UserViewHolder> usersAdapter(Context context) {
        return new UsersAdapter();
    }
}