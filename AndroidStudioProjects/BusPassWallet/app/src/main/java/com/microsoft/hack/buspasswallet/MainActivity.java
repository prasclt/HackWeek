package com.microsoft.hack.buspasswallet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.microsoft.hack.buspasswallet.interfaces.FragmentLoaderActivity;

public class MainActivity extends AppCompatActivity implements FragmentLoaderActivity {

    private FragmentManager mFragmentManager;
    private Controller mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFragmentManager = getSupportFragmentManager();
        mController = new Controller(this);
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

    @Override
    public void loadFragment(Fragment fragment) {
        Helper.loadFragment(R.id.fragmentHolder, mFragmentManager, fragment, null);
    }

    public interface LaunchableFragment {
        void launch(FragmentManager fragmentManager, @Nullable Bundle bundle);
    }

}
