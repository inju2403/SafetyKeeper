package com.example.safetykeeper.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.safetykeeper.Fragment.CallFragment;
import com.example.safetykeeper.Fragment.MainFragment;
import com.example.safetykeeper.Fragment.MapFragment;
import com.example.safetykeeper.Fragment.SettingFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private int tabCount;
    private Fragment mainFragment, mapFragment, callFragment, settingFragment;
    private FragmentManager fragmentManager;

    public ViewPagerAdapter(FragmentManager fragmentManager, int tabCount) {
        super(fragmentManager, tabCount);
        this.tabCount = tabCount;

        mainFragment = new MainFragment();
        mapFragment = new MapFragment();
        callFragment = new CallFragment();
        settingFragment = new SettingFragment();

        this.fragmentManager = fragmentManager;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return mainFragment;
            case 1:
                return mapFragment;
            case 2:
                return callFragment;
            case 3:
                return settingFragment;
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}