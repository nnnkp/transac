package com.nnnkp.transac;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    private static final long DISPLAY_TIME = 0;
    private Runnable mRunnable;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_login);
        displayLoginPage();

    }


    private void displayView(Fragment view) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.login_main_container, view)
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .commit();
    }

    private void displayLoginPage() {
        mRunnable = new Runnable() {
            @Override
            public void run() {
               Fragment loginFragment = new LoginFragment();
//                Display login page fragment.
                displayView(loginFragment );
            }
        };
        mHandler = new Handler();
        mHandler.postDelayed(mRunnable, DISPLAY_TIME);
    }
}
