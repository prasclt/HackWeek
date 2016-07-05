package com.microsoft.hack.buspasswallet;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.microsoft.hack.buspasswallet.fragments.InitialLoadingFragment;
import com.microsoft.hack.buspasswallet.fragments.LoginFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFragmentManager = getSupportFragmentManager();

        displayInitialFragments();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void displayInitialFragments() {
        Helper.loadFragment(R.id.fragmentHolder, mFragmentManager, new InitialLoadingFragment(), null);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Helper.loadFragment(R.id.fragmentHolder, mFragmentManager, new LoginFragment(), null);
            }
        }, InitialLoadingFragment.DURATION_IN_MILLIS);
    }

    public interface LaunchableFragment {
        void launch(FragmentManager fragmentManager, @Nullable Bundle bundle);
    }

}
