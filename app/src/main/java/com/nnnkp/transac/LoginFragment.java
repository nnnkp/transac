package com.nnnkp.transac;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

/**
 * Created by npraj1 on 5/23/2017.
 */

public class LoginFragment extends Fragment implements View.OnClickListener {

    private EditText etUsername, etPassword;

    private int TRANSACTIONS_TIME_OUT = 0;

    private UserDataSource userDB;

    private Button signupButton, loginButton, dbButton;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //  return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.layout_fragment_login, container, false);

        etUsername = (EditText) view.findViewById(R.id.et_username);
        etPassword = (EditText) view.findViewById(R.id.et_password);

        signupButton = (Button) view.findViewById(R.id.button_signup);
        signupButton.setOnClickListener(this);

        loginButton = (Button) view.findViewById(R.id.button_login);
        loginButton.setOnClickListener(this);

        dbButton = (Button) view.findViewById(R.id.button_database);
        dbButton.setOnClickListener(this);
        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button_signup:
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.login_main_container, new SignupFragment())
                        .addToBackStack(null)
                        .commit();
                break;

            case R.id.button_login:

                Boolean isAuthenticated = isAuthenticated();
                Log.d("PASS", "*isAuth*" + isAuthenticated + "**");

                if (isAuthenticated) {

                  //  transactionsScreen();
                    Intent intent = new Intent(getActivity(), TransactionsActivity.class);
                    startActivity(intent);


                /*    getActivity()
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.login_main_container, new TransactionsFragment())
                            .addToBackStack(null)
                            .commit();*/
                    break;
                } else {
                    Toast.makeText(getActivity(), "Invalid Credentials",
                            Toast.LENGTH_LONG).show();
                    break;
                }
            case R.id.button_database:

                Intent dbmanager = new Intent(getActivity(), AndroidDatabaseManager.class);
                startActivity(dbmanager);
                break;


        }
    }

    public Boolean isAuthenticated() {
        String usernameEntered = etUsername.getText().toString().trim();
        String passwordEntered = etPassword.getText().toString().trim();
        String passwordInDB = "";
        //context
        userDB = new UserDataSource(getActivity());
        userDB.open();
        //isActive = false (Unused in method called)
        List<User> users = userDB.getAllUsers();

        for (User user : users) {

            if (usernameEntered.equalsIgnoreCase(user.getUsername())) {
                passwordInDB = user.getPassword();
                break;
            }
        }

        return passwordInDB.equalsIgnoreCase(passwordEntered) ? true : false;

    }

    private void transactionsScreen() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getActivity(), TransactionsActivity.class);
                startActivity(i);
                getActivity().finish();
            }
        }, TRANSACTIONS_TIME_OUT);
    }
}
