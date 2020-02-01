package com.example.safetykeeper.Fragment;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safetykeeper.Adapter.PersonAdapter;
import com.example.safetykeeper.Model.Person;
import com.example.safetykeeper.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import java.util.ArrayList;


public class MapFragment extends Fragment implements AutoPermissionsListener {

    private Activity mActivity;
    private SupportMapFragment mapDisplayFragment;
    private Button locationRequestButton;
    private RecyclerView friendListRecyclerView;
    private MarkerOptions myLocationMarker;
    private GoogleMap map;

    private Animation translateBottomAnimation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivity = getActivity();
        setHasOptionsMenu(true);

        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_map,container,false);

        // Fragment안에 Fragment를 넣을 때 getChildFragmentManager()
        mapDisplayFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        locationRequestButton = rootView.findViewById(R.id.locationRequestButton);
        locationRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLocationService();
            }
        });

        ArrayList<Person> items = new ArrayList<>();
        for (int i=0; i<30; ++i) {
            items.add(new Person("친구 "+i));
        }


        friendListRecyclerView = rootView.findViewById(R.id.friendListRecyclerView);

        friendListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // 리사이클러뷰에 어댑터로 PersonAdapter 지정.
        PersonAdapter adapter = new PersonAdapter(getContext(), items);
        friendListRecyclerView.setAdapter(adapter);




        mapDisplayFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                map.setMyLocationEnabled(true);

            }
        });

        try {
            MapsInitializer.initialize(getContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        locationRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLocationService();
            }
        });


        // 친구 목록 페이지 슬라이딩
      //  translateBottomAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.)


        return rootView;
    }

    private void startLocationService() {
        LocationManager manager = (LocationManager) mActivity.getSystemService(Context.LOCATION_SERVICE);

        try {
            Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();

            }

            GPSListener gpsListener = new GPSListener();
            long minTime = 1000;
            float minDistance = 0;

            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, gpsListener);
            Toast.makeText(getContext(), "내 위치확인 요청함", Toast.LENGTH_SHORT).show();

        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if(map != null) {
            map.setMyLocationEnabled(true);
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if(map != null) {
            map.setMyLocationEnabled(false);
        }
    }


    class GPSListener implements LocationListener {
        public void onLocationChanged(Location location) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            showCurrentLocation(latitude, longitude);
        }

        private void showCurrentLocation(Double latitude, Double longitude) {
            LatLng curPoint = new LatLng(latitude, longitude);
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 17));

            showMyLocationMarker(curPoint);
        }

        private void showMyLocationMarker(LatLng curPoint) {
            if(myLocationMarker == null) {
                myLocationMarker = new MarkerOptions();
                myLocationMarker.position(curPoint);
                myLocationMarker.title("내 위치\n");
                myLocationMarker.snippet("GPS로 확인한 위치");
                //myLocationMarker.icon();
                map.addMarker(myLocationMarker);
            } else {
                myLocationMarker.position(curPoint);
            }
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        AutoPermissions.Companion.loadAllPermissions(mActivity, 101);
    }

    @Override
    public void onDenied(int i, @NonNull String[] strings){
        Toast.makeText(getContext(),"permissions denied : "+strings.length, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGranted(int i, String[] strings) {
        Toast.makeText(getContext(),"permissions granted : "+strings.length, Toast.LENGTH_LONG).show();
    }

}
