package venkov.vladimir.thebeginning.Views.login;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Window;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import venkov.vladimir.thebeginning.MainActivity;
import venkov.vladimir.thebeginning.R;
import venkov.vladimir.thebeginning.models.User;

public class LoginActivity extends DaggerAppCompatActivity implements LoginContracts.Navigator{

    @Inject
    LoginFragment mLoginFragment;

    @Inject
    LoginContracts.Presenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        mLoginFragment.setNavigator(this);
        mLoginFragment.setPresenter(mLoginPresenter);
        Intent intent = getIntent();

        //Set Icon in title bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);

        getSupportActionBar().setTitle("Login");

        FragmentTransaction ft = getFragmentManager().beginTransaction()
                .replace(R.id.contentLogin, mLoginFragment);

        ft.commit();
    }


    @Override
    public void navigateWith(User user) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.EXTRA_KEY, user);
        startActivity(intent);
       // finish();

//        Intent intent = new Intent(".action.bla");
//        sendBroadcast(intent);
//        int b = 7;
    }
}
