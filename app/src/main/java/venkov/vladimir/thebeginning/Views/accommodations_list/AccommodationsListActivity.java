package venkov.vladimir.thebeginning.Views.accommodations_list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dagger.android.support.DaggerAppCompatActivity;
import venkov.vladimir.thebeginning.R;

public class AccommodationsListActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accommodations_list);
    }
}
