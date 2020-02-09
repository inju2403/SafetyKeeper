package com.example.safetykeeper.Fragment;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safetykeeper.Adapter.PersonAdapter;
import com.example.safetykeeper.Model.Person;
import com.example.safetykeeper.R;
<<<<<<< HEAD
import com.naver.maps.geometry.LatLng;
=======
>>>>>>> aa377bfac9a7c2633fe7c5f39a4d8cbf4e440602
import com.naver.maps.map.LocationSource;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
<<<<<<< HEAD
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.OverlayImage;
=======
>>>>>>> aa377bfac9a7c2633fe7c5f39a4d8cbf4e440602
import com.naver.maps.map.util.FusedLocationSource;

import java.util.ArrayList;


public class MyMapFragment extends Fragment implements OnMapReadyCallback {

    private Activity mActivity;
    private MapFragment mapDisplayFragment;
    private Button locationRequestButton;
    private RecyclerView friendListRecyclerView;

<<<<<<< HEAD

=======
>>>>>>> aa377bfac9a7c2633fe7c5f39a4d8cbf4e440602
    // private MarkerOptions myLocationMarker;
    // private GoogleMap map;
    private MapView mapView;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    private FusedLocationSource locationSource;
    @NonNull
    private final Context context;
    @Nullable
    private final LocationManager locationManager;
    @Nullable
    private LocationSource.OnLocationChangedListener listener;

    public MyMapFragment(@NonNull Context context) {
        this.context = context;
        locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivity = getActivity();
        setHasOptionsMenu(true);

        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_map,container,false);

        // Fragment안에 Fragment를 넣을 때 getChildFragmentManager()
      //  mapDisplayFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

<<<<<<< HEAD
        /*Marker marker = new Marker();
        marker.setPosition(new LatLng(37.5670135, 126.9783740));
        marker.setMap(naverMap);

        marker.setIcon(OverlayImage.fromResource(R.drawable.marker_icon));*/
=======
>>>>>>> aa377bfac9a7c2633fe7c5f39a4d8cbf4e440602

        FragmentManager fm = getChildFragmentManager();
        mapDisplayFragment = (MapFragment)fm.findFragmentById(R.id.map);
        if (mapDisplayFragment == null) {
            mapDisplayFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.map, mapDisplayFragment).commit();
        }

        mapDisplayFragment.getMapAsync(this);

        locationRequestButton = rootView.findViewById(R.id.locationRequestButton);

        locationSource = new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);




        // 리사이클러뷰 코드
        ArrayList<Person> items = new ArrayList<>();
        for (int i=0; i<30; ++i) {
            items.add(new Person("친구 "+i));
        }

        friendListRecyclerView = rootView.findViewById(R.id.friendListRecyclerView);

        friendListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // 리사이클러뷰에 어댑터로 PersonAdapter 지정.
        PersonAdapter adapter = new PersonAdapter(getContext(), items);
        friendListRecyclerView.setAdapter(adapter);

        /* 구글 맵
        locationRequestButton = rootView.findViewById(R.id.locationRequestButton);
        locationRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLocationService();
            }
        });

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

         */


        return rootView;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,  @NonNull int[] grantResults) {
        if (locationSource.onRequestPermissionsResult(
                requestCode, permissions, grantResults)) {
            return;
        }
        super.onRequestPermissionsResult(
                requestCode, permissions, grantResults);
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        naverMap.setLocationSource(locationSource);
        naverMap.setLocationTrackingMode(LocationTrackingMode.Follow);
        /*
        UiSettings uiSettings = naverMap.getUiSettings();
        uiSettings.setLocationButtonEnabled(true);
        
         */
    }


 /* 구글 맵
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
<<<<<<< HEAD
            
=======
>>>>>>> aa377bfac9a7c2633fe7c5f39a4d8cbf4e440602
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


     */
}
