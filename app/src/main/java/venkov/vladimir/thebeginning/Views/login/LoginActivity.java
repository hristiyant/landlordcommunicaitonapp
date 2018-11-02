package venkov.vladimir.thebeginning.Views.login;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

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

    private User mLoggedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLoginFragment.setNavigator(this);
        mLoginFragment.setPresenter(mLoginPresenter);
        Intent intent = getIntent();
        mLoggedUser = (User) intent.getSerializableExtra(MainActivity.EXTRA_KEY);
        mLoginPresenter.setLoggedUser(mLoggedUser);

        FragmentTransaction ft = getFragmentManager().beginTransaction()
                .replace(R.id.contentLogin, mLoginFragment);

        ft.commit();
    }


    @Override
    public void navigateWith(User user) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.EXTRA_KEY, user);
        startActivity(intent);
    }
}
