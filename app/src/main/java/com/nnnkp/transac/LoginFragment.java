package com.nnnkp.transac;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by npraj1 on 5/23/2017.
 */

public class LoginFragment extends Fragment implements View.OnClickListener{

    private Button signupButton;
    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      //  return super.onCreateView(inflater, container, savedInstanceState);
        View view  = inflater.inflate(R.layout.layout_fragment_login, container, false);
        signupButton = (Button) view.findViewById(R.id.button_signup);
        signupButton.setOnClickListener(this);
        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onClick(View view) {

        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.login_main_container, new SignupFragment())
                .addToBackStack(null)
                .commit();
    }
}
