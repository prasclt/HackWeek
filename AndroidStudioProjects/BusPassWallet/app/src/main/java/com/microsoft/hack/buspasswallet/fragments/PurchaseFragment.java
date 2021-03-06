package com.microsoft.hack.buspasswallet.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.microsoft.hack.buspasswallet.Controller;
import com.microsoft.hack.buspasswallet.PassHelper;
import com.microsoft.hack.buspasswallet.R;
import com.microsoft.hack.buspasswallet.database.Pass;

/**
 * Created by prmeno on 7/15/2016.
 */
public class PurchaseFragment extends Fragment {

    private Controller mController;
    private RadioGroup mRadioGroupPassType;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_purchase, container, false);

        mRadioGroupPassType = (RadioGroup) rootView.findViewById(R.id.radioGroupPasses);
        mRadioGroupPassType.check(R.id.radioButtonAcMonthly);

        getActivity().setTitle(getActivity().getString(R.string.title_fragment_purchase_pass));

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        menu.add(R.string.menu_item_buy).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mController.onBackPressed();
                break;
            default:
                mController.purchaseSuccess(passType(mRadioGroupPassType.getCheckedRadioButtonId()));
        }

        return true;
    }

    private int passType(int radioButtonId) {
        switch (radioButtonId) {
            case R.id.radioButtonAcMonthly:
                return PassHelper.AC_MONTHLY;
            case R.id.radioButtonNormalMonthly:
                return PassHelper.NORMAL_MONTHLY;
            case R.id.radioButtonAcDaily:
                return PassHelper.AC_DAILY;
            case R.id.radioButtonNormalDaily:
                return PassHelper.NORMAL_DAILY;
        }

        return PassHelper.AC_MONTHLY;
    }

    public static PurchaseFragment instantiate(Controller controller) {
        PurchaseFragment purchaseFragment = new PurchaseFragment();
        purchaseFragment.mController = controller;

        return purchaseFragment;
    }
}
