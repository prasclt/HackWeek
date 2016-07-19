package com.microsoft.hack.buspasswallet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.microsoft.hack.buspasswallet.database.DaoSession;
import com.microsoft.hack.buspasswallet.database.Pass;
import com.microsoft.hack.buspasswallet.database.PassDao;
import com.microsoft.hack.buspasswallet.database.User;

import java.util.List;

/**
 * Created by prmeno on 7/19/2016.
 */
public class DBHelper {

    public static final String EVENT_PASS_INSERTED_TO_DB = "pass_inserted";
    public static final String EVENT_USER_INSERTED_TO_DB = "user_inserted";

    public static void insertIntoDB(Pass pass, Context context) {
        DaoSession daoSession = Helper.getDaoSession(context);
        PassDao passDao = daoSession.getPassDao();
        passDao.insert(pass);

        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(EVENT_PASS_INSERTED_TO_DB));
    }

    public static void insertIntoDB(User user, Context context) {

    }

    /*public static List<Pass> fetchPasses() {

    }

    public static List<User> fetchUsers() {

    }*/

}
