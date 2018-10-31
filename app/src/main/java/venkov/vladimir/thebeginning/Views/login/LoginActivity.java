package venkov.vladimir.thebeginning.Views.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerAppCompatActivity;
import venkov.vladimir.thebeginning.Constants;
import venkov.vladimir.thebeginning.MainActivity;
import venkov.vladimir.thebeginning.R;
import venkov.vladimir.thebeginning.http.OkHttpHttpRequester;
import venkov.vladimir.thebeginning.models.User;
import venkov.vladimir.thebeginning.parsers.GsonJsonParser;
import venkov.vladimir.thebeginning.repositories.HttpRepository;
import venkov.vladimir.thebeginning.repositories.base.Repository;
import venkov.vladimir.thebeginning.services.HttpUsersService;
import venkov.vladimir.thebeginning.services.UserService;
import venkov.vladimir.thebeginning.validatiors.base.Validator;

public class LoginActivity extends DaggerAppCompatActivity {



    private User mLoggedUser;

    @BindView(R.id.et_phone_number)
    TextInputEditText mEtPhoneNumber;

    @Inject
    public LoginActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login)
    public void onBtnLoginClick() {
        String phoneNumber = mEtPhoneNumber.getText().toString();
        int b = 5;
        try {
//            mLoggedUser = mUserService.getUserByPhoneNumber(phoneNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mLoggedUser == null) {
            Toast.makeText(this, "There is no such user!", Toast.LENGTH_LONG)
            .show();
            return;
        }
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.EXTRA_KEY, mLoggedUser);
        startActivity(intent);

//        List<User> usersList = null;
//        try {
//            usersList = mUsersRepository.getAll().stream()
//                     .filter(user -> user.getPhoneNumber().equals(mEtPhoneNumber.getText().toString()))
//                     .collect(Collectors.toList());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        if(!usersList.isEmpty()) {
//           Intent intent = new Intent(this, AccommodationsListActivity.class);
//           startActivity(intent);
//       } else {
//           Toast.makeText(this, "Invalid phone number!!!!!!!!", Toast.LENGTH_LONG).show();
//       }
    }


}
