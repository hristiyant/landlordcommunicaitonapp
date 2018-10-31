package venkov.vladimir.thebeginning.diconfig;


import android.content.Context;
import android.support.v7.widget.RecyclerView;

import dagger.Module;
import dagger.Provides;
import venkov.vladimir.thebeginning.Views.UsersAdapter;
import venkov.vladimir.thebeginning.Views.accommodations_list.AccommodationsAdapter;

@Module
public class ViewsModule {
    @Provides
    public RecyclerView.Adapter<UsersAdapter.UserViewHolder> usersAdapter(Context context) {
        return new UsersAdapter();
    }

    @Provides
    public RecyclerView.Adapter<AccommodationsAdapter.AccommodationViewHolder> accommodationsAdapter(Context context) {
        return new AccommodationsAdapter();
    }
}