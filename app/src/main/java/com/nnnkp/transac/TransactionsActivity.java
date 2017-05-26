package com.nnnkp.transac;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class TransactionsActivity extends AppCompatActivity {

    private static final long DISPLAY_TIME = 0;
    private Runnable mRunnable;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_transactions);
        displayTransactionsPage();

    }


    private void displayView(Fragment view) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.transactions_main_container, view)
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .commit();
    }

    private void displayTransactionsPage() {
        mRunnable = new Runnable() {
            @Override
            public void run() {
               Fragment transactionsFragment = new TransactionsFragment();
//                Display login page fragment.
                displayView(transactionsFragment);
            }
        };
        mHandler = new Handler();
        mHandler.postDelayed(mRunnable, DISPLAY_TIME);
    }
}
