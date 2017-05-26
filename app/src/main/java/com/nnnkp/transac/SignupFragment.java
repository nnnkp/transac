package com.nnnkp.transac;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by npraj1 on 5/23/2017.
 */

public class SignupFragment extends Fragment implements View.OnClickListener{

    EditText etUsername,etEmail, etPassword, etMobile;
    Button btnSubmit;
    private UserDataSource userDB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
       // return super.onCreateView(inflater, container, savedInstanceState);

        View view  = inflater.inflate(R.layout.layout_fragment_signup,viewGroup,false);

        etUsername = (EditText) view.findViewById(R.id.et_username);
        etEmail = (EditText) view.findViewById(R.id.et_email);
        etPassword = (EditText) view.findViewById(R.id.et_password);
        etMobile = (EditText) view.findViewById(R.id.et_mobile);

        btnSubmit = (Button) view.findViewById(R.id.button_submit);


        btnSubmit.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {

        addUserToDB();
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.login_main_container, new ProfileFragment())
                .addToBackStack(null)
                .commit();
    }

    private void addUserToDB(){
        String username = etUsername.getText().toString();
        String email = etUsername.getText().toString();
        String password = etUsername.getText().toString();
        String mobile = etUsername.getText().toString();

        //context
        userDB = new UserDataSource(getActivity());
        userDB.open();

        //isActive = false (Unused in method called)
        userDB.createUser(username,email,password,mobile,false);
    }

  /*  @Override
    public void onResume() {
        userDB.open();
        super.onResume();
    }

    @Override
    public void onPause() {
        userDB.close();
        super.onPause();
    }*/
}
