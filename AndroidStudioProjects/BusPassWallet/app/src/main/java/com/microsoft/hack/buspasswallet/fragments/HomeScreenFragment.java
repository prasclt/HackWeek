package com.microsoft.hack.buspasswallet.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.microsoft.hack.buspasswallet.Controller;
import com.microsoft.hack.buspasswallet.DBHelper;
import com.microsoft.hack.buspasswallet.R;
import com.microsoft.hack.buspasswallet.adapters.BusPassAdapter;

/**
 * Created by prmeno on 7/13/2016.
 */
public class HomeScreenFragment extends Fragment implements View.OnClickListener {

    private Controller mController;

    private RecyclerView mRecyclerView;
    private FloatingActionButton mFab;
    private BusPassAdapter mBusPassAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBusPassAdapter = new BusPassAdapter(getContext());
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mDBInsertionListener, new IntentFilter(DBHelper.EVENT_PASS_INSERTED_TO_DB));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home_screen, container, false);

        mFab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewPasses);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mBusPassAdapter);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        mFab.setOnClickListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(mDBInsertionListener);
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

    private BroadcastReceiver mDBInsertionListener = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(getContext(), "DB Insertion done", Toast.LENGTH_LONG).show();
            mBusPassAdapter.refresh(getContext());
        }
    };
}
