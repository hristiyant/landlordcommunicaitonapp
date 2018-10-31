package venkov.vladimir.thebeginning;

import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import dagger.android.support.DaggerAppCompatActivity;
import venkov.vladimir.thebeginning.Views.ListAllActivity;
import venkov.vladimir.thebeginning.Views.accommodations_list.AccommodationsListActivity;
import venkov.vladimir.thebeginning.Views.chat.ChatActivity;
import venkov.vladimir.thebeginning.Views.login.LoginActivity;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.btn_start_login_activity)
    Button mStartLogin;

    @BindView(R.id.btn_start_accommodations_list_activity)
    Button mStartAccommodations;

    @BindView(R.id.btn_start_chat_activity)
    Button mStartChat;

    @BindView(R.id.btn_start_users_list_activity)
    Button mStartUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_start_login_activity)
    public void onBtnLoginClick() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_start_accommodations_list_activity)
    public void onBtnAccommodationsClick() {
        Intent intent = new Intent(this, AccommodationsListActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_start_chat_activity)
    public void onChatClick() {
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_start_users_list_activity)
    public void onBtnUsersClick() {
        Intent intent = new Intent(this, ListAllActivity.class);
        startActivity(intent);
    }


}
