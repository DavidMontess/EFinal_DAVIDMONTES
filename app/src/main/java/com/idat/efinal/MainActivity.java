package com.idat.efinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements Navigationhost{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState== null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container,new LoginFragment())
                    .commit();
        }



    }

    @Override
    public void navigateTo(Fragment fragment, boolean addToBackstack) {
        FragmentTransaction transaction=
                getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container,fragment);

        if(addToBackstack){
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
}