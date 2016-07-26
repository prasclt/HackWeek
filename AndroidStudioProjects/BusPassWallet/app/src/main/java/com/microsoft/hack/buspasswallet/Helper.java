package com.microsoft.hack.buspasswallet;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.microsoft.hack.buspasswallet.database.DaoMaster;
import com.microsoft.hack.buspasswallet.database.DaoSession;
import com.microsoft.hack.buspasswallet.database.Pass;
import com.microsoft.hack.buspasswallet.database.User;
import com.microsoft.hack.buspasswallet.database.UserDao;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by prmeno on 7/5/2016.
 */
public final class Helper {

    public static final String DB_NAME = "buspasswallet-db";
    public static final int SELECT_PHOTO = 101;

    public static Uri profilePicUri;

    public static void loadFragment(AppCompatActivity activity, int containerID, FragmentManager fragmentManager, Fragment fragment, @Nullable Bundle bundle, boolean addPreviousFragmentToBackstack) {
        if (bundle != null) {
            fragment.setArguments(bundle);
        }

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerID, fragment);
        if (addPreviousFragmentToBackstack) {
            fragmentTransaction.addToBackStack(null);
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        fragmentTransaction.commit();


    }

    /*public static User dummyUser(Context context) {
        User dummyuser;
        DaoSession daoSession = getDaoSession(context);
        UserDao userDao = daoSession.getUserDao();

        if (isFirstrun(context)) {
            //TODO make use of DBHelper here
            dummyuser = new User(null, "Roger", 27, null, "8050609404");
            userDao.insert(dummyuser);
        } else {
            dummyuser = userDao.loadAll().get(0);
        }
        return dummyuser;
    }*/

    public static DaoSession getDaoSession(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        return daoMaster.newSession();
    }

    public static void generateExpiredDummyPass(Context context, Controller controller) {
        if (!isFirstrun(context))
            return;

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -2);
        Pass dummyExpiredPass = new Pass(null, c.getTime(), c.getTime(), PassHelper.AC_DAILY, controller.getLoggedInUser().getId());
        DBHelper.insertIntoDB(dummyExpiredPass, context);

    }

    private static boolean isFirstrun(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        if (!prefs.getBoolean("firstTime", false)) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();

            return true;
        }

        return false;
    }

    public static void showToast(Context context, String messgae) {
        Toast.makeText(context, messgae, Toast.LENGTH_LONG).show();
    }

    public static String readableDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        return c.get(Calendar.DAY_OF_MONTH) + " / " + (c.get(Calendar.MONTH) + 1);
    }
}
