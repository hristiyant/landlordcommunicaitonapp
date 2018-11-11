package venkov.vladimir.thebeginning;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import venkov.vladimir.thebeginning.Views.accommodations_list.AccommodationsListActivity;
import venkov.vladimir.thebeginning.Views.chat.select_user.SelectUserActivity;
import venkov.vladimir.thebeginning.Views.login.LoginActivity;
import venkov.vladimir.thebeginning.Views.users_list.ListAllActivity;
import venkov.vladimir.thebeginning.models.User;

public class MainActivity extends AppCompatActivity {


    public static final String EXTRA_KEY = "LoggedUser";

    private User mLoggedUser;

    @BindView(R.id.btn_start_login_activity)
    Button mStartLogin;

    @BindView(R.id.btn_start_accommodations_list_activity)
    Button mStartAccommodations;

    @BindView(R.id.btn_start_chat_activity)
    Button mStartChat;

    @BindView(R.id.btn_start_users_list_activity)
    Button mStartUsers;

    private DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*//-----------------------FULLSCREEN
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //-----------------------------------------
*/

        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        mLoggedUser = (User) intent.getSerializableExtra(MainActivity.EXTRA_KEY);


        //init device token
        String deviceToken = FirebaseInstanceId.getInstance().getToken();


        //init db ref
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("usersNew")
                .child(String.valueOf(mLoggedUser))
                .child("token").setValue(deviceToken);


/*

        mDatabase.child("xperiachat");
        mDatabase.child("0888111964").setValue("hristiqneeeeee")
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Write was successful!
                        // ...
                        Toast.makeText(MainActivity.this, "KUREC RABOTI", Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Write failed
                        // ...

                        Toast.makeText(MainActivity.this, "PEDAL NE RABOTI", Toast.LENGTH_SHORT).show();
                        Log.d("proba", "onFailure: " + e.getMessage());

                    }
                });*/
    }

    @OnClick(R.id.btn_start_login_activity)
    public void onBtnLoginClick() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_start_accommodations_list_activity)
    public void onBtnAccommodationsClick() {
        Intent intent = new Intent(this, AccommodationsListActivity.class);
        intent.putExtra(MainActivity.EXTRA_KEY, mLoggedUser);
        startActivity(intent);
    }

    @OnClick(R.id.btn_start_chat_activity)
    public void onChatClick() {
        Intent intent = new Intent(this, SelectUserActivity.class);
        intent.putExtra(MainActivity.EXTRA_KEY, mLoggedUser);
        startActivity(intent);
    }

    @OnClick(R.id.btn_start_users_list_activity)
    public void onBtnUsersClick() {
        Intent intent = new Intent(this, ListAllActivity.class);
        intent.putExtra(MainActivity.EXTRA_KEY, mLoggedUser);
        startActivity(intent);
    }

}
