package com.microsoft.hack.buspasswallet.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.microsoft.hack.buspasswallet.Controller;
import com.microsoft.hack.buspasswallet.R;
import com.microsoft.hack.buspasswallet.database.Pass;

import net.glxn.qrgen.android.QRCode;

/**
 * Created by prmeno on 7/21/2016.
 */
public class PassQRCodeFragment extends Fragment {

    public static final String DELIMITER = "|";
    public static final String PASS_HEADER = "BMTC";

    private ImageView mImageViewQR;

    private Controller mController;
    private Pass pass;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pass_qr, container, false);

        mImageViewQR = (ImageView) rootView.findViewById(R.id.imageViewQR);
        showQR();

        getActivity().setTitle(getActivity().getString(R.string.title_fragment_passQR));

        return rootView;
    }

    private void showQR() {
        StringBuilder passQRString = new StringBuilder("");

        passQRString.append(PASS_HEADER);
        passQRString.append(DELIMITER);
        passQRString.append(pass.getUser().getName());
        passQRString.append(DELIMITER);

        mImageViewQR.setImageBitmap(QRCode.from(passQRString.toString()).bitmap());
    }

    public static PassQRCodeFragment instantiate(Controller controller, Pass pass) {
        PassQRCodeFragment passQRCodeFragment = new PassQRCodeFragment();
        passQRCodeFragment.mController = controller;
        passQRCodeFragment.pass = pass;

        return passQRCodeFragment;
    }
}
