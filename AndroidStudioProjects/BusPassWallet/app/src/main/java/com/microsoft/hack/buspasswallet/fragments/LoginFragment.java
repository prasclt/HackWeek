package com.microsoft.hack.buspasswallet.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.microsoft.hack.buspasswallet.Controller;
import com.microsoft.hack.buspasswallet.Helper;
import com.microsoft.hack.buspasswallet.R;

/**
 * Created by prmeno on 7/6/2016.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    private EditText mTextViewID;
    private EditText mTextViewPassword;
    private Button mButtonLogin;

    private Controller mController;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        mTextViewID = (EditText) rootView.findViewById(R.id.editTextEmailID);
        mTextViewPassword = (EditText) rootView.findViewById(R.id.editTextPassword);
        mButtonLogin = (Button) rootView.findViewById(R.id.buttonLogin);

        mButtonLogin.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        //TODO - login logic & animation
        mController.onLoginSuccess(Helper.dummyUser());
    }

    public static LoginFragment instantiate(Controller controller) {
        LoginFragment loginFragment = new LoginFragment();
        loginFragment.mController = controller;

        return loginFragment;
    }
}
