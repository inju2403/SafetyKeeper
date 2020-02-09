package com.example.safetykeeper.Activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.safetykeeper.Fragment.CallFragment;
import com.example.safetykeeper.Fragment.MainFragment;
import com.example.safetykeeper.Fragment.MyMapFragment;
import com.example.safetykeeper.Fragment.SettingFragment;
import com.example.safetykeeper.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pedro.library.AutoPermissions;

public class MainActivity extends AppCompatActivity {

    private static final int FRAG_MAIN = 0;
    private static final int FRAG_MAP = 1;
    private static final int FRAG_CALL = 2;
    private static final int FRAG_SETTING = 3;

    private FragmentManager fragmentManager;
    private Fragment mainFragment, myMapFragment, callFragment, settingFragment;
    private BottomNavigationView bottom_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFragment();
        setBottomNavigation();

        AutoPermissions.Companion.loadAllPermissions(this, 101);
    }


    private void setFragment() {
        fragmentManager = getSupportFragmentManager();

        mainFragment = new MainFragment();
        myMapFragment = new MyMapFragment(this);
        callFragment = new CallFragment();
        settingFragment = new SettingFragment();

        fragmentManager.beginTransaction().replace(R.id.fragment, mainFragment).commit();

        fragmentManager.beginTransaction().add(R.id.fragment, myMapFragment).commit();
        fragmentManager.beginTransaction().hide(myMapFragment).commit();

        fragmentManager.beginTransaction().add(R.id.fragment, callFragment).commit();
        fragmentManager.beginTransaction().hide(callFragment).commit();

        fragmentManager.beginTransaction().add(R.id.fragment, settingFragment).commit();
        fragmentManager.beginTransaction().hide(settingFragment).commit();
    }

    private void setBottomNavigation() {
        bottom_bar = findViewById(R.id.bottom_bar);
        bottom_bar.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.main_tab:
                                switchFragement(FRAG_MAIN);
                                return true;
                            case R.id.map_tab:
                                switchFragement(FRAG_MAP);
                                return true;
                            case R.id.call_tab:
                                switchFragement(FRAG_CALL);
                                return true;
                            case R.id.setting_tab:
                                switchFragement(FRAG_SETTING);
                                return true;
                            default:
                                return false;
                        }
                    }
                }
        );
    }

    private void switchFragement(final int next) {
        switch(next) {
            case FRAG_MAIN:
                fragmentManager.beginTransaction().show(mainFragment).commit();
                fragmentManager.beginTransaction().hide(myMapFragment).commit();
                fragmentManager.beginTransaction().hide(callFragment).commit();
                fragmentManager.beginTransaction().hide(settingFragment).commit();
                break;

            case FRAG_MAP:
                fragmentManager.beginTransaction().hide(mainFragment).commit();
                fragmentManager.beginTransaction().show(myMapFragment).commit();
                fragmentManager.beginTransaction().hide(callFragment).commit();
                fragmentManager.beginTransaction().hide(settingFragment).commit();
                break;

            case FRAG_CALL:
                fragmentManager.beginTransaction().hide(mainFragment).commit();
                fragmentManager.beginTransaction().hide(myMapFragment).commit();
                fragmentManager.beginTransaction().show(callFragment).commit();
                fragmentManager.beginTransaction().hide(settingFragment).commit();
                break;

            case FRAG_SETTING:
                fragmentManager.beginTransaction().hide(mainFragment).commit();
                fragmentManager.beginTransaction().hide(myMapFragment).commit();
                fragmentManager.beginTransaction().hide(callFragment).commit();
                fragmentManager.beginTransaction().show(settingFragment).commit();
                break;
        }
    }
}
