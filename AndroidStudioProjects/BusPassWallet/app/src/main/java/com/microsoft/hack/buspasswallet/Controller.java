package com.microsoft.hack.buspasswallet;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.microsoft.hack.buspasswallet.fragments.HomeScreenFragment;
import com.microsoft.hack.buspasswallet.fragments.InitialLoadingFragment;
import com.microsoft.hack.buspasswallet.fragments.LoginFragment;
import com.microsoft.hack.buspasswallet.fragments.PurchaseFragment;
import com.microsoft.hack.buspasswallet.interfaces.FragmentLoaderActivity;
import com.microsoft.hack.buspasswallet.models.Pass;
import com.microsoft.hack.buspasswallet.models.User;

/**
 * Created by prmeno on 7/7/2016.
 */
public class Controller {

    private FragmentLoaderActivity mFragmentLoaderActivity;
    private User loggedInUser;

    public Controller(FragmentLoaderActivity fragmentLoaderActivity) {
        this.mFragmentLoaderActivity = fragmentLoaderActivity;
        displayInitialFragments();
    }

    public void onLoginSuccess(User user) {
        loggedInUser = user;
        mFragmentLoaderActivity.loadFragment(HomeScreenFragment.instantiate(this), false);
    }

    private void displayInitialFragments() {
        ((AppCompatActivity) mFragmentLoaderActivity).getSupportActionBar().hide();
        mFragmentLoaderActivity.loadFragment(new InitialLoadingFragment(), false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ((AppCompatActivity) mFragmentLoaderActivity).getSupportActionBar().show();
                mFragmentLoaderActivity.loadFragment(LoginFragment.instantiate(Controller.this), false);
            }
        }, InitialLoadingFragment.DURATION_IN_MILLIS);
    }

    public void purchasePass() {
        mFragmentLoaderActivity.loadFragment(PurchaseFragment.instantiate(Controller.this), true);
    }

    public void purchaseSuccess(int passType) {
        //TODO pras insert to DB
        Pass newPass = Pass.generatePassFromNow(loggedInUser, passType);
        Toast.makeText((Activity) mFragmentLoaderActivity, "Pass valid to : " + newPass.getValidTo().toString(), Toast.LENGTH_LONG).show();
        ((Activity) mFragmentLoaderActivity).onBackPressed();
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}
