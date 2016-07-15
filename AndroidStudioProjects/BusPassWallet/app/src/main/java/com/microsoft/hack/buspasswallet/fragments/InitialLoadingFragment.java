package com.microsoft.hack.buspasswallet.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.microsoft.hack.buspasswallet.R;

/**
 * Created by prmeno on 7/4/2016.
 */
public class InitialLoadingFragment extends Fragment {

    public static final int DURATION_IN_MILLIS = 3000;

    private AppCompatActivity mActivity;
    private ContentLoadingProgressBar mProgressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_initial_load, container, false);

        mProgressBar = (ContentLoadingProgressBar) rootView.findViewById(R.id.progressBar);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        mProgressBar.setIndeterminate(true);
    }
}
