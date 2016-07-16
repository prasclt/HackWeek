package com.microsoft.hack.buspasswallet.interfaces;

import android.support.v4.app.Fragment;

/**
 * Created by prmeno on 7/7/2016.
 */
public interface FragmentLoaderActivity {

    void loadFragment(Fragment fragment, boolean addPreviousFragmentToBackstack);
}
