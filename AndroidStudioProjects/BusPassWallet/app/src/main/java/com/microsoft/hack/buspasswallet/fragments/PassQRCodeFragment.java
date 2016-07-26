package com.microsoft.hack.buspasswallet.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.microsoft.hack.buspasswallet.Controller;
import com.microsoft.hack.buspasswallet.Helper;
import com.microsoft.hack.buspasswallet.R;
import com.microsoft.hack.buspasswallet.database.Pass;
import com.microsoft.hack.buspasswallet.database.User;

import net.glxn.qrgen.android.QRCode;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by prmeno on 7/21/2016.
 */
public class PassQRCodeFragment extends Fragment {

    public static final String DELIMITER = "|";
    public static final String PASS_HEADER = "BMTC";

    private ImageView mImageViewQR;
    private TextView mTextViewName;
    private TextView mTextViewAge;
    private TextView mTextViewPhone;
    private CircleImageView mCircleImageViewProfilePic;

    private Controller mController;
    private Pass pass;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pass_qr, container, false);

        mImageViewQR = (ImageView) rootView.findViewById(R.id.imageViewQR);
        mTextViewName = (TextView) rootView.findViewById(R.id.textViewName);
        mTextViewAge = (TextView) rootView.findViewById(R.id.textViewAge);
        mTextViewPhone = (TextView) rootView.findViewById(R.id.textViewPhone);
        mCircleImageViewProfilePic = (CircleImageView) rootView.findViewById(R.id.circleImageViewProfilePic);

        //Todo remove hardcoded profile pic
        mCircleImageViewProfilePic.setImageURI(Helper.profilePicUri);
        generateUserCard();
        showQR();

        getActivity().setTitle(getActivity().getString(R.string.title_fragment_passQR));

        return rootView;
    }

    private void generateUserCard() {
        User user = pass.getUser();

        mTextViewName.setText("Name : " + user.getName());
        mTextViewAge.setText("Age : " + user.getAge());
        mTextViewPhone.setText("Phone : " + user.getPhone());

        if (user.getPhotoUri() != null) {
            mCircleImageViewProfilePic.setImageURI(Uri.parse(user.getPhotoUri()));
        }
    }

    private void showQR() {
        StringBuilder passQRString = new StringBuilder("");

        passQRString.append(PASS_HEADER);
        passQRString.append(DELIMITER);
        passQRString.append(pass.getUser().getName());
        passQRString.append(DELIMITER);
        passQRString.append(pass.getUser().getAge());
        passQRString.append(DELIMITER);
        passQRString.append(pass.getUser().getPhone());
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
