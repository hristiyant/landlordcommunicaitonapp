package venkov.vladimir.thebeginning.diconfig;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import venkov.vladimir.thebeginning.Views.accommodation_details.AccommodationDetailsPresenter;
import venkov.vladimir.thebeginning.Views.accommodation_details.AccommodationDetailsContracts;
import venkov.vladimir.thebeginning.Views.accommodation_details.AccommodationDetailsFragment;

@Module
public abstract class AccommodationDetailsModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract AccommodationDetailsFragment accommodationDetailsFragment();

    @ActivityScoped
    @Binds
    abstract AccommodationDetailsContracts.Presenter accommodationDetailsPresenter(AccommodationDetailsPresenter presenter);

}