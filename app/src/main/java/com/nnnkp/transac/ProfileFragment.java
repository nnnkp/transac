package com.nnnkp.transac;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

/**
 * Created by npraj1 on 5/23/2017.
 */

public class ProfileFragment extends Fragment /*implements View.OnClickListener*/{

    TextView tvUsername,tvEmail, tvPassword, tvMobile;
  private UserDataSource userDB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.layout_fragment_profile,viewGroup,false);

        tvUsername = (TextView) view.findViewById(R.id.tv_username);
        tvEmail = (TextView) view.findViewById(R.id.tv_email);
        tvPassword = (TextView) view.findViewById(R.id.tv_password);
        tvMobile = (TextView) view.findViewById(R.id.tv_mobile);

        //context
        userDB = new UserDataSource(getActivity());
        userDB.open();
        //isActive = false (Unused in method called)
        List<User> users = userDB.getAllUsers();

        for(User user : users){
            String log = "Username: "+ user.getUsername() +"Password: "+ user.getPassword();
            Log.d("USER:",log);
            tvUsername.setText(user.getUsername());
            tvMobile.setText(user.getMobile());
            tvEmail.setText(user.getEmail());
        }

      //  tvEmail.setText();

        return view;
    }

 /*   @Override
    public void onClick(View view) {

        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.login_main_container, new ProfileFragment())
                .addToBackStack(null)
                .commit();
    }*/


   /* @Override
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
