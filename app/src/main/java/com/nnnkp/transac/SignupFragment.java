package com.nnnkp.transac;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by npraj1 on 5/23/2017.
 */

public class SignupFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
       // return super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.layout_fragment_signup,viewGroup,false);
    }
}
