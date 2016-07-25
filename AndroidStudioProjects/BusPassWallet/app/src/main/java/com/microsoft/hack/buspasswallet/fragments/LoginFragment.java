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
import com.microsoft.hack.buspasswallet.DBHelper;
import com.microsoft.hack.buspasswallet.Helper;
import com.microsoft.hack.buspasswallet.R;
import com.microsoft.hack.buspasswallet.database.User;

import java.util.List;

/**
 * Created by prmeno on 7/6/2016.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    private EditText mTextViewID;
    private EditText mTextViewPassword;
    private Button mButtonLogin;
    private TextView mTextViewRegister;

    private Controller mController;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        mTextViewID = (EditText) rootView.findViewById(R.id.editTextEmailID);
        mTextViewPassword = (EditText) rootView.findViewById(R.id.editTextPassword);
        mButtonLogin = (Button) rootView.findViewById(R.id.buttonLogin);
        mTextViewRegister = (TextView) rootView.findViewById(R.id.textViewRegister);

        mButtonLogin.setOnClickListener(this);
        mTextViewRegister.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        //TODO - login logic & animation
        switch (v.getId()) {
            case R.id.buttonLogin:
                validateLogin();
                break;
            case R.id.textViewRegister:
                mController.onRegisterClicked();
                break;
        }

    }

    private void validateLogin() {

        String username = mTextViewID.getText() != null ? mTextViewID.getText().toString() : null;
        if (username == null || username.isEmpty()) {
            Helper.showToast(getContext(), "Please enter valid ID");
            return;
        }

        String password = mTextViewPassword.getText() != null ? mTextViewPassword.getText().toString() : null;
        if (password == null || password.isEmpty()) {
            Helper.showToast(getContext(), "Please enter password");
            return;
        }

        User loggedInUser;
        if ((loggedInUser = matchIDPassword(username, password)) == null) {
            Helper.showToast(getContext(), "LoginID password does not match");
            return;
        }

        mController.onLoginSuccess(loggedInUser);
    }

    private User matchIDPassword(String phone, String password) {
        List<User> userList = DBHelper.fetchUsers(getContext());

        for (User user : userList) {
            if (user.getPhone().equals(phone) && user.getPassword().equals(password))
                return user;
        }

        return null;
    }

    public static LoginFragment instantiate(Controller controller) {
        LoginFragment loginFragment = new LoginFragment();
        loginFragment.mController = controller;

        return loginFragment;
    }
}
