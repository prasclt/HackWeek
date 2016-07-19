package com.microsoft.hack.buspasswallet.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.microsoft.hack.buspasswallet.DBHelper;
import com.microsoft.hack.buspasswallet.R;
import com.microsoft.hack.buspasswallet.database.Pass;
import com.microsoft.hack.buspasswallet.views.PassListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prmeno on 7/19/2016.
 */

public class BusPassAdapter extends RecyclerView.Adapter<BusPassAdapter.PassViewHolder> {

    List<Pass> passes;

    public BusPassAdapter(Context context) {
        passes = DBHelper.fetchPasses(context);
    }

    @Override
    public PassViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View passListItem = layoutInflater.inflate(R.layout.list_item_pass, parent, false);
        return new PassViewHolder(passListItem);
    }

    @Override
    public void onBindViewHolder(PassViewHolder holder, int position) {
        holder.bind(passes.get(position));
    }

    @Override
    public int getItemCount() {
        return passes.size();
    }

    public void refresh(Context context) {
        passes = DBHelper.fetchPasses(context);
        notifyDataSetChanged();
    }

    public class PassViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewPassType;
        private TextView mTextViewUserName;
        private TextView mTextViewValidity;

        public PassViewHolder(View itemView) {
            super(itemView);

            mTextViewPassType = (TextView) itemView.findViewById(R.id.textViewPassType);
            mTextViewUserName = (TextView) itemView.findViewById(R.id.textViewUserName);
            mTextViewValidity = (TextView) itemView.findViewById(R.id.textViewValidity);
        }

        public void bind(Pass pass) {
            mTextViewPassType.setText(pass.getTypeString());
            mTextViewUserName.setText("Name: " + pass.getUser().getName());
            mTextViewValidity.setText("Valid To:" + pass.getValidTo().getDay() + "/" + pass.getValidTo().getMonth());
        }
    }
}

