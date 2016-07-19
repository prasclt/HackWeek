package com.microsoft.hack.buspasswallet.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.microsoft.hack.buspasswallet.R;

/**
 * Created by prmeno on 7/19/2016.
 */
public class PassListItem extends LinearLayout {

    public PassListItem(Context context, ViewGroup parent) {
        super(context);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        layoutInflater.inflate(R.layout.list_item_pass, parent, true);
    }

}
