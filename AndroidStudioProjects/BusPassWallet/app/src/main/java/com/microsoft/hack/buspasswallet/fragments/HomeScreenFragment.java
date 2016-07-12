package com.microsoft.hack.buspasswallet.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.microsoft.hack.buspasswallet.Controller;
import com.microsoft.hack.buspasswallet.R;

/**
 * Created by prmeno on 7/13/2016.
 */
public class HomeScreenFragment extends Fragment implements View.OnClickListener {

    private Controller mController;

    private RecyclerView mRecyclerView;
    private FloatingActionButton mFab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home_screen, container, false);

        mFab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewPasses);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        mFab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mController.purchasePass();
    }

    public static HomeScreenFragment instantiate(Controller controller) {
        HomeScreenFragment homeScreenFragment = new HomeScreenFragment();
        homeScreenFragment.mController = controller;

        return homeScreenFragment;
    }
}
