package com.microsoft.hack.buspasswallet;

import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.microsoft.hack.buspasswallet.fragments.HomeScreenFragment;
import com.microsoft.hack.buspasswallet.fragments.InitialLoadingFragment;
import com.microsoft.hack.buspasswallet.fragments.LoginFragment;
import com.microsoft.hack.buspasswallet.interfaces.FragmentLoaderActivity;
import com.microsoft.hack.buspasswallet.models.User;

/**
 * Created by prmeno on 7/7/2016.
 */
public class Controller {

    private FragmentLoaderActivity mFragmentLoaderActivity;

    public Controller(FragmentLoaderActivity fragmentLoaderActivity) {
        this.mFragmentLoaderActivity = fragmentLoaderActivity;
        displayInitialFragments();
    }

    public void onLoginSuccess(User user) {
        mFragmentLoaderActivity.loadFragment(HomeScreenFragment.instantiate(this));
    }

    private void displayInitialFragments() {
        mFragmentLoaderActivity.loadFragment(new InitialLoadingFragment());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mFragmentLoaderActivity.loadFragment(LoginFragment.instantiate(Controller.this));
            }
        }, InitialLoadingFragment.DURATION_IN_MILLIS);
    }

    public void purchasePass() {
        Toast.makeText((Activity) mFragmentLoaderActivity, "Lets buy a pass", Toast.LENGTH_LONG).show();
    }
}