package venkov.vladimir.thebeginning.Views.chat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import butterknife.ButterKnife;
import venkov.vladimir.thebeginning.MainActivity;
import venkov.vladimir.thebeginning.R;
import venkov.vladimir.thebeginning.Views.chat.select_user.SelectUserActivity;
import venkov.vladimir.thebeginning.models.Accommodation;
import venkov.vladimir.thebeginning.models.User;

public class ChatActivity extends AppCompatActivity {

    private User mLoggedUser;
    private User mTargetUser;

    //private Accommodation mCurrentAccommodation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        //Hide keyboard at start
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        ButterKnife.bind(this);

        Intent intent = getIntent();
        mLoggedUser = (User) intent.getSerializableExtra(MainActivity.EXTRA_KEY);
        mTargetUser = (User) intent.getSerializableExtra(SelectUserActivity.EXTRA_KEY);

        getSupportActionBar().setTitle(mTargetUser.getFirstName() + " " + mTargetUser.getLastName());

        /*
        mCurrentAccommodation =
                (Accommodation) intent.getSerializableExtra
                        (AccommodationDetailsActivity.ACCOMMODATION_EXTRA_KEY);
        int b = 5;*/
    }
}
