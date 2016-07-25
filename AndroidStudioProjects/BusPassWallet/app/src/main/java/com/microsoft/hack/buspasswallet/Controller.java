package com.microsoft.hack.buspasswallet;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.microsoft.hack.buspasswallet.database.DaoSession;
import com.microsoft.hack.buspasswallet.database.Pass;
import com.microsoft.hack.buspasswallet.database.PassDao;
import com.microsoft.hack.buspasswallet.database.User;
import com.microsoft.hack.buspasswallet.fragments.HomeScreenFragment;
import com.microsoft.hack.buspasswallet.fragments.InitialLoadingFragment;
import com.microsoft.hack.buspasswallet.fragments.LoginFragment;
import com.microsoft.hack.buspasswallet.fragments.PassQRCodeFragment;
import com.microsoft.hack.buspasswallet.fragments.PurchaseFragment;
import com.microsoft.hack.buspasswallet.fragments.RegisterFragment;
import com.microsoft.hack.buspasswallet.interfaces.FragmentLoaderActivity;

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
        Pass newPass = PassHelper.generatePassFromNow(loggedInUser, passType);
        DBHelper.insertIntoDB(newPass, (Activity) mFragmentLoaderActivity);
        ((Activity) mFragmentLoaderActivity).onBackPressed();
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void passOpened(Pass pass) {
        mFragmentLoaderActivity.loadFragment(PassQRCodeFragment.instantiate(this, pass), true);
    }

    public void onBackPressed() {
        ((Activity) mFragmentLoaderActivity).onBackPressed();
    }

    public void onNewUserRegistered(User user) {
        DBHelper.insertIntoDB(user, (Activity) mFragmentLoaderActivity);
        onLoginSuccess(user);
    }

    public void onRegisterClicked() {
        mFragmentLoaderActivity.loadFragment(RegisterFragment.instantiate(this), false);
    }
}
