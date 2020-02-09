package com.example.safetykeeper.CallFragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.safetykeeper.R;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class CallFragment extends Fragment {
    private RecyclerView recyclerView;
    private ImageButton imageButton_Call, timePick_button;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1000;
    CenterZoomLayoutManager layoutManager;
    private String currentPhoneNumber;
    private ArrayList<Friend> friends;

    private final static int REQUEST_TIME = 111;

    public CallFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_call, container, false);
        recyclerView = view.findViewById(R.id.CallFragment_Friend_RecyclerView);

        layoutManager = new CenterZoomLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        friends = new ArrayList<>();
        friends.add(new Friend("wonsik1","wonsik","wonsik", "1234"));
        friends.add(new Friend("wonsik2","wonsik","wonsik", "2345"));
        friends.add(new Friend("wonsik3","wonsik","wonsik", "3456"));
        friends.add(new Friend("wonsik4","wonsik","wonsik", "4567"));
        friends.add(new Friend("wonsik5","wonsik","wonsik", "5678"));

        CallListAdapter callListAdapter = new CallListAdapter(getActivity(), getContext(),friends);
        recyclerView.setAdapter(callListAdapter);
        recyclerView.addOnScrollListener(recyclerViewOnScrollListener);

        imageButton_Call = view.findViewById(R.id.CallFragment_Call_ImageButton);
        imageButton_Call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
                    }
                    else {
                        //You already have permission
                        try {
                            Intent mIntent = new Intent(Intent.ACTION_CALL);
                            mIntent.setData(Uri.parse("tel:"+currentPhoneNumber));
                            startActivity(mIntent);
                        } catch(SecurityException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        );

        timePick_button = view.findViewById(R.id.CallFragment_TimePick_ImageButton);
        timePick_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), TimePickActivity.class);
                startActivityForResult(intent, REQUEST_TIME);
            }
        });

        recyclerView.getLayoutManager().scrollToPosition(Integer.MAX_VALUE / 2);


        return view;
    }

    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int currentIndex = layoutManager.findFirstVisibleItemPosition() + 1;
            currentPhoneNumber = friends.get(currentIndex % friends.size()).getPhoneNumber();
            System.err.println(currentPhoneNumber);
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the phone call
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_TIME){
            if(resultCode==RESULT_OK) {
                int hour = data.getIntExtra("hour", 0);
                int minute = data.getIntExtra("minute", 0);
                Toast.makeText(getContext(), "Set alarm call at " + hour +" : " + minute, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
