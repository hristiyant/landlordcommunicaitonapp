package venkov.vladimir.thebeginning.Views.chat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;
import venkov.vladimir.thebeginning.MainActivity;
import venkov.vladimir.thebeginning.R;
import venkov.vladimir.thebeginning.Views.accommodation_details.AccommodationDetailsActivity;
import venkov.vladimir.thebeginning.models.Accommodation;
import venkov.vladimir.thebeginning.models.User;

public class ChatActivity extends AppCompatActivity {

    private User mLoggedUser;

    private Accommodation mCurrentAccommodation;

    @BindView(R.id.tv_chat)
    TextView mChatText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        mLoggedUser = (User) intent.getSerializableExtra(MainActivity.EXTRA_KEY);
        mCurrentAccommodation =
                (Accommodation) intent.getSerializableExtra
                        (AccommodationDetailsActivity.ACCOMMODATION_EXTRA_KEY);
        int b = 5;
    }
}
