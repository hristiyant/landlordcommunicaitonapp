package venkov.vladimir.thebeginning.Views.login;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import dagger.android.support.DaggerAppCompatActivity;
import venkov.vladimir.thebeginning.R;
import venkov.vladimir.thebeginning.Views.accommodations_list.AccommodationsListActivity;
import venkov.vladimir.thebeginning.models.User;
import venkov.vladimir.thebeginning.repositories.HttpRepository;
import venkov.vladimir.thebeginning.services.HttpUsersService;

public class LoginActivity extends DaggerAppCompatActivity{

    HttpRepository<User> mUsersRepository;

    @BindView(R.id.btn_login)
    Button mBtnLogin;

    @BindView(R.id.et_phone_number)
    TextInputEditText mEtPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
    }

    @OnItemClick(R.id.btn_login)
    public void onBtnLoginClick() {
        List<User> usersList = null;
        try {
            usersList = mUsersRepository.getAll().stream()
                     .filter(user -> user.getPhoneNumber().toString().equals(mEtPhoneNumber.getText().toString()))
                     .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!usersList.isEmpty()) {
           Intent intent = new Intent(this, AccommodationsListActivity.class);
           startActivity(intent);
       } else {
           Toast.makeText(this, "Invalid phone number!!!!!!!!", Toast.LENGTH_LONG);
       }

    }


}
