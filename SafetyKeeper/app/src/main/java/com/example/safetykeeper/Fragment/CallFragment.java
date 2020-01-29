package com.example.safetykeeper.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.safetykeeper.R;

public class CallFragment extends Fragment {


    private Activity mActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivity = getActivity();
        setHasOptionsMenu(true);

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_call,container,false);


        return rootView;
    }

}
