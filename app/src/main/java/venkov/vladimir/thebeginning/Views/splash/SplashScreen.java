package venkov.vladimir.thebeginning.Views.splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import butterknife.BindView;
import venkov.vladimir.thebeginning.R;
import venkov.vladimir.thebeginning.Views.login.LoginActivity;

public class SplashScreen extends AppCompatActivity {


    @BindView(R.id.splash_image)
    ImageView mSplashImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().setTitle("Welcome to LandCom!");



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);



    }
}
