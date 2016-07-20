package com.microsoft.hack.buspasswallet.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.qrcode.encoder.QRCode;
import com.microsoft.hack.buspasswallet.Controller;
import com.microsoft.hack.buspasswallet.DBHelper;
import com.microsoft.hack.buspasswallet.R;
import com.microsoft.hack.buspasswallet.database.Pass;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by prmeno on 7/19/2016.
 */

public class BusPassAdapter extends RecyclerView.Adapter<BusPassAdapter.PassViewHolder> {

    public static final int EXPIRED = 0;
    public static final int ACTIVE = 1;

    private List<Pass> passes;
    private List<Pass> activePasses;
    private List<Pass> expiredPasses;

    private Controller mController;

    private int showList;

    public BusPassAdapter(Context context, Controller controller) {
        mController = controller;
        showList = ACTIVE;
        passes = DBHelper.fetchPasses(context);
        splitUpPassList();
    }

    @Override
    public PassViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View passListItem = layoutInflater.inflate(R.layout.list_item_pass, parent, false);
        return new PassViewHolder(passListItem);
    }

    @Override
    public void onBindViewHolder(PassViewHolder holder, int position) {
        holder.bind(currentList().get(position));
    }

    @Override
    public int getItemCount() {
        return currentList().size();
    }

    public void refresh(Context context) {
        passes = DBHelper.fetchPasses(context);
        splitUpPassList();
        notifyDataSetChanged();
    }

    public void switchList(int validity) {
        showList = validity;
        notifyDataSetChanged();
    }

    private void splitUpPassList() {
        Date today = Calendar.getInstance().getTime();

        expiredPasses = new ArrayList<Pass>();
        activePasses = new ArrayList<Pass>();
        for (Pass pass : passes) {
            if (!pass.expired()) {
                activePasses.add(pass);
            } else {
                expiredPasses.add(pass);
            }
        }
    }

    private List<Pass> currentList() {
        return showList == EXPIRED ? expiredPasses : activePasses;
    }

    public class PassViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTextViewPassType;
        private TextView mTextViewUserName;
        private TextView mTextViewValidity;

        private Pass pass;

        public PassViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            mTextViewPassType = (TextView) itemView.findViewById(R.id.textViewPassType);
            mTextViewUserName = (TextView) itemView.findViewById(R.id.textViewUserName);
            mTextViewValidity = (TextView) itemView.findViewById(R.id.textViewValidity);
        }

        public void bind(Pass pass) {
            mTextViewPassType.setText(pass.getTypeString());
            mTextViewUserName.setText("Name: " + pass.getUser().getName());
            mTextViewValidity.setText("Valid To:" + pass.getValidTo().toString());
            this.pass = pass;
        }

        @Override
        public void onClick(View v) {
            if (pass.expired()) {
                Toast.makeText(v.getContext(), "This pass has expired", Toast.LENGTH_SHORT).show();
                return;
            }

            mController.passOpened(pass);
        }
    }
}

