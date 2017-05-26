package com.nnnkp.transac;


import android.os.Bundle;
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

public class LoginFragment extends Fragment implements View.OnClickListener{

    private EditText etUsername, etPassword;

    private UserDataSource userDB;

    private Button signupButton, loginButton;
    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      //  return super.onCreateView(inflater, container, savedInstanceState);
        View view  = inflater.inflate(R.layout.layout_fragment_login, container, false);

        etUsername = (EditText) view.findViewById(R.id.et_username);
        etPassword = (EditText) view.findViewById(R.id.et_password);

        signupButton = (Button) view.findViewById(R.id.button_signup);
        signupButton.setOnClickListener(this);

        loginButton = (Button) view.findViewById(R.id.button_login);
        loginButton.setOnClickListener(this);
        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onClick(View view) {

        switch(view.getId()) {
            case R.id.button_signup :
                 getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.login_main_container, new SignupFragment())
                    .addToBackStack(null)
                    .commit();
                break;

            case R.id.button_login :

                if(isAuthenticated()){
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.login_main_container, new ProfileFragment())
                        .addToBackStack(null)
                        .commit();
                break;
                }else{
                    Toast.makeText(getActivity(), "Invalid Credentials",
                            Toast.LENGTH_LONG).show();
                    break;
                }


        }
    }

    public Boolean isAuthenticated(){
      String usernameEntered = etUsername.getText().toString();
      String passwordEntered = etPassword.getText().toString();
        String passwordInDB = "";
        //context
        userDB = new UserDataSource(getActivity());
        userDB.open();
        //isActive = false (Unused in method called)
        List<User> users = userDB.getAllUsers();

        for(User user : users){
         //   String log = "Username: "+ user.getUsername() +"Password: "+ user.getPassword();
         //   Log.d("USER:",log);
            if(user.getUsername() == usernameEntered) {
                passwordInDB = user.getPassword();
            }
        }
        if(passwordInDB == passwordEntered) {
            return true;
        }

        return false;
    }
}
