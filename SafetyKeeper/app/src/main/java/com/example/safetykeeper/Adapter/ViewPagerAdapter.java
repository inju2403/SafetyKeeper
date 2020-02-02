package com.example.safetykeeper.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.safetykeeper.Fragment.CallFragment;
import com.example.safetykeeper.Fragment.MainFragment;
import com.example.safetykeeper.Fragment.SettingFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private int tabCount;
    private Fragment mainFragment, myMapFragment, callFragment, settingFragment;
    private FragmentManager fragmentManager;
    private Context context;

    public ViewPagerAdapter(FragmentManager fragmentManager, int tabCount) {
        super(fragmentManager, tabCount);
        this.tabCount = tabCount;

        mainFragment = new MainFragment();
  //      myMapFragment = new MyMapFragment();
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
                return myMapFragment;
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