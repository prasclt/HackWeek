package com.microsoft.hack.buspasswallet.fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.microsoft.hack.buspasswallet.Controller;
import com.microsoft.hack.buspasswallet.Helper;
import com.microsoft.hack.buspasswallet.R;
import com.microsoft.hack.buspasswallet.database.User;

import java.io.FileNotFoundException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by prmeno on 7/24/2016.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener {

    private EditText mEditTextName;
    private EditText mEditTextPhone;
    private EditText mEditTextPassword;
    private NumberPicker mNumberPickerAge;
    private Button mRegisteButton;
    private CircleImageView mCircleImageViewUserImage;

    private Controller mController;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_register_user, container, false);

        mEditTextName = (EditText) rootView.findViewById(R.id.editTextName);
        mEditTextPhone = (EditText) rootView.findViewById(R.id.editTextPhone);
        mEditTextPassword = (EditText) rootView.findViewById(R.id.editTextPassword);
        mNumberPickerAge = (NumberPicker) rootView.findViewById(R.id.numberPickerAge);
        mRegisteButton = (Button) rootView.findViewById(R.id.buttonRegister);
        mCircleImageViewUserImage = (CircleImageView) rootView.findViewById(R.id.circleImageViewUserImage);

        mRegisteButton.setOnClickListener(this);
        mNumberPickerAge.setMaxValue(90);
        mNumberPickerAge.setMinValue(8);
        mNumberPickerAge.setValue(18);
        mCircleImageViewUserImage.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.circleImageViewUserImage:
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, Helper.SELECT_PHOTO);
                break;
            case R.id.buttonRegister:
                User newUser = validateInputs();
                if (newUser == null) {
                    return;
                }
                mController.onNewUserRegistered(newUser);
        }
    }

    private User validateInputs() {
        String name = mEditTextName.getText() != null ? mEditTextName.getText().toString() : null;
        if (name == null || name.isEmpty()) {
            Helper.showToast(getContext(), "Please enter Name");
            return null;
        }

        String phone = mEditTextPhone.getText() != null ? mEditTextPhone.getText().toString() : null;
        if (phone == null || phone.isEmpty()) {
            Helper.showToast(getContext(), "Please enter Phone number");
            return null;
        }

        String password = mEditTextPassword.getText() != null ? mEditTextPassword.getText().toString() : null;
        if (password == null || password.isEmpty()) {
            Helper.showToast(getContext(), "Please enter password");
            return null;
        }

        String imageUri = (String) mCircleImageViewUserImage.getTag();
        int age = mNumberPickerAge.getValue();

        return new User(null, name, age, imageUri, phone, password);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Helper.SELECT_PHOTO:
                if (resultCode == Activity.RESULT_OK) {
                    try {
                        final Uri imageUri = data.getData();
                        mCircleImageViewUserImage.setImageURI(imageUri);
                        Helper.profilePicUri = imageUri;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
        }
    }

    public static RegisterFragment instantiate(Controller controller) {
        RegisterFragment registerFragment = new RegisterFragment();
        registerFragment.mController = controller;
        return registerFragment;
    }
}
