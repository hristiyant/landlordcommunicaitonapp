package venkov.vladimir.thebeginning.Views.detailUser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;
import venkov.vladimir.thebeginning.R;
import venkov.vladimir.thebeginning.models.User;

public class DetailUserActivity extends DaggerAppCompatActivity {

    public static final String EXTRA_KEY = "USER_EXTRA_KEY";

    @BindView(R.id.tv_text)
     TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra(DetailUserActivity.EXTRA_KEY);

        mTextView.setText(user.getPhoneNumber());
    }
}
