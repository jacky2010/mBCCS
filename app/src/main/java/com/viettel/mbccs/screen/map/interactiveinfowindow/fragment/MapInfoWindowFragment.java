package com.viettel.mbccs.screen.map.interactiveinfowindow.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.viettel.mbccs.R;
import com.viettel.mbccs.screen.map.interactiveinfowindow.InfoWindowManager;
import com.viettel.mbccs.screen.map.interactiveinfowindow.customview.TouchInterceptFrameLayout;

public class MapInfoWindowFragment extends Fragment {

    private static final String TAG = "MapInfoWindowFragment";

    private GoogleMap googleMap;
    private InfoWindowManager infoWindowManager;

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_map_infowindow, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        infoWindowManager = new InfoWindowManager(getChildFragmentManager());
        infoWindowManager.onParentViewCreated((TouchInterceptFrameLayout) view, savedInstanceState);
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (googleMap == null) {
            // Try to obtain the map from the SupportMapFragment.

            final SupportMapFragment mapFragment =
                    (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapFragment);

            mapFragment
                    .getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            MapInfoWindowFragment.this.googleMap = googleMap;
                            setUpMap();
                        }
                    });
        }
    }

    private void setUpMap() {
        infoWindowManager.onMapReady(googleMap);
    }

    public InfoWindowManager infoWindowManager() {
        return infoWindowManager;
    }

    @Override
    public void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        infoWindowManager.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        infoWindowManager.onDestroy();
    }

    public void getMapAsync(OnMapReadyCallback onMapReadyCallback) {
        final SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapFragment);

        mapFragment.getMapAsync(onMapReadyCallback);
    }
}
