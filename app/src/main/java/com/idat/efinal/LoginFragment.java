package com.idat.efinal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
            ){
        View view = inflater.inflate(R.layout.login_fragment,container,false);
        MaterialButton nextButton= view.findViewById(R.id.next_button);

        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ((Navigationhost) getActivity()).navigateTo(new JugadoresFragment(),false);
            }
        });

        return view;
    }
}
