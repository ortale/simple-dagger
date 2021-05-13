package com.joseortale.ortalesoft.simpledagger.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.joseortale.ortalesoft.simpledagger.BaseApplication;
import com.joseortale.ortalesoft.simpledagger.R;
import com.joseortale.ortalesoft.simpledagger.viewmodel.UserViewModel;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UsersFragment usersFragment = UsersFragment.newInstance();
        setFragment(usersFragment);
    }

    public void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.rootFragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        int fragments = getSupportFragmentManager().getBackStackEntryCount();
        if (fragments == 0) {
            super.onBackPressed();
        } else  {
            getSupportFragmentManager().popBackStackImmediate();
        }
    }
}