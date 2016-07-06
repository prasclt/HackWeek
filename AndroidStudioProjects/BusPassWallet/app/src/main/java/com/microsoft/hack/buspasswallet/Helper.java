package com.microsoft.hack.buspasswallet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.microsoft.hack.buspasswallet.models.User;

/**
 * Created by prmeno on 7/5/2016.
 */
public final class Helper {

    public static void loadFragment(int containerID, FragmentManager fragmentManager, Fragment fragment, @Nullable Bundle bundle) {
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        fragmentManager.beginTransaction().add(containerID, fragment).commit();
    }

    public static User dummyUser() {
        return new User("Pras", 27);
    }
}
