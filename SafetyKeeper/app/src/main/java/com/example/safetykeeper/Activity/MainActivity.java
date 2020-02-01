package com.example.safetykeeper.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.safetykeeper.Adapter.ViewPagerAdapter;
import com.example.safetykeeper.Fragment.CallFragment;
import com.example.safetykeeper.Fragment.MainFragment;
import com.example.safetykeeper.Fragment.MapFragment;
import com.example.safetykeeper.Fragment.SettingFragment;
import com.example.safetykeeper.R;
import com.pedro.library.AutoPermissions;

public class MainActivity extends AppCompatActivity {

    private static final int FRAG_MAIN = 0;
    private static final int FRAG_MAP = 1;
    private static final int FRAG_CALL = 2;
    private static final int FRAG_SETTING = 3;

    private FragmentManager fragmentManager;
    private Fragment mainFragment, mapFragment, callFragment, settingFragment;

    private Button mainFragment_button, mapFragment_button, callFragment_button, settingFragment_button;

    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFragment();
        setButton();

        AutoPermissions.Companion.loadAllPermissions(this, 101);
    }


    private void setFragment() {
        fragmentManager = getSupportFragmentManager();
        /*
        viewPagerAdapter = new ViewPagerAdapter(fragmentManager,4);
        viewPager = findViewById(R.id.viewPager);

        viewPager.setAdapter(viewPagerAdapter);
         */

        mainFragment = new MainFragment();
        mapFragment = new MapFragment();
        callFragment = new CallFragment();
        settingFragment = new SettingFragment();

        fragmentManager.beginTransaction().replace(R.id.fragment, mainFragment).commit();

        fragmentManager.beginTransaction().add(R.id.fragment, mapFragment).commit();
        fragmentManager.beginTransaction().hide(mapFragment).commit();

        fragmentManager.beginTransaction().add(R.id.fragment, callFragment).commit();
        fragmentManager.beginTransaction().hide(callFragment).commit();

        fragmentManager.beginTransaction().add(R.id.fragment, settingFragment).commit();
        fragmentManager.beginTransaction().hide(settingFragment).commit();
    }

    private void setButton() {
        mainFragment_button = findViewById(R.id.mainFragment_button);
        mapFragment_button = findViewById(R.id.mapFragment_button);
        callFragment_button = findViewById(R.id.callFragment_button);
        settingFragment_button = findViewById(R.id.settingFragment_button);

        mainFragment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragement(FRAG_MAIN);
            }
        });
        mapFragment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragement(FRAG_MAP);
            }
        });
        callFragment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragement(FRAG_CALL);
            }
        });
        settingFragment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragement(FRAG_SETTING);
            }
        });
    }

    private void switchFragement(final int next) {
        switch(next) {
            case FRAG_MAIN:
                fragmentManager.beginTransaction().show(mainFragment).commit();
                fragmentManager.beginTransaction().hide(mapFragment).commit();
                fragmentManager.beginTransaction().hide(callFragment).commit();
                fragmentManager.beginTransaction().hide(settingFragment).commit();
                break;

            case FRAG_MAP:
                fragmentManager.beginTransaction().hide(mainFragment).commit();
                fragmentManager.beginTransaction().show(mapFragment).commit();
                fragmentManager.beginTransaction().hide(callFragment).commit();
                fragmentManager.beginTransaction().hide(settingFragment).commit();
                break;

            case FRAG_CALL:
                fragmentManager.beginTransaction().hide(mainFragment).commit();
                fragmentManager.beginTransaction().hide(mapFragment).commit();
                fragmentManager.beginTransaction().show(callFragment).commit();
                fragmentManager.beginTransaction().hide(settingFragment).commit();
                break;

            case FRAG_SETTING:
                fragmentManager.beginTransaction().hide(mainFragment).commit();
                fragmentManager.beginTransaction().hide(mapFragment).commit();
                fragmentManager.beginTransaction().hide(callFragment).commit();
                fragmentManager.beginTransaction().show(settingFragment).commit();
                break;
        }
    }
}
