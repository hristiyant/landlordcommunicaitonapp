package venkov.vladimir.thebeginning.diconfig;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import venkov.vladimir.thebeginning.Views.accommodations_list.AccommodationsListContracts;
import venkov.vladimir.thebeginning.Views.accommodations_list.AccommodationsListFragment;
import venkov.vladimir.thebeginning.Views.accommodations_list.AccommodationsListPresenter;

@Module
public abstract class AccommodationsListModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract AccommodationsListFragment accommodationsListFragment();

    @ActivityScoped
    @Binds
    abstract AccommodationsListContracts.Presenter accommodationsListPresenter(AccommodationsListPresenter presenter);

}
