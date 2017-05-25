package com.viettel.mbccs.screen.map;


import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.viettel.mbccs.R;
import com.viettel.mbccs.permission.PermissionListener;
import com.viettel.mbccs.permission.PermissionsUtil;

/**
 * Created by jacky on 5/25/17.
 */

public class DialogMapStationFragment extends BaseDialogMap implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener,
        MapFragment.OnListenerChangeLayout,
        MapWrapperLayout.OnListenerUpdateMarker {

    private GoogleApiClient mGoogleApiClient;
    private GoogleMap mMap;
    private Location mLocation;
    private LatLng mCenterLatLong;
    private static final String TAG = MapFragment.class.getSimpleName();


    @Override
    protected void initView() {

    }

    @Override
    protected int idLayoutRes() {
        return R.layout.dialog_map_station_fragment;
    }

    @Override
    protected void initData() {
        requestLocation();
    }

    @Override
    protected int getStyleDialog() {
        return 0;
    }

    private void init() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getContext());
        if (status != ConnectionResult.SUCCESS) {
            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status,
                    getActivity(), requestCode);
            dialog.show();
        } else {
            MapFragment mMapCustomFragment = ((MapFragment) getActivity()
                    .getSupportFragmentManager().findFragmentById(R.id.map_view_fragment));
            mMapCustomFragment.getMapAsync(this);
            mMapCustomFragment.setOnListenerUpdateMarker(this);
            mMapCustomFragment.setOnListenerChangeLayout(this);

            fetchLocation();
        }
    }

    private void requestLocation() {
        if (PermissionsUtil.hasPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)) {
            init();
        } else {
            PermissionsUtil.requestPermission(getActivity(), new PermissionListener() {
                @Override
                public void permissionGranted(@NonNull String[] permissions) {
                    init();
                }

                @Override
                public void permissionDenied(@NonNull String[] permissions) {
                    getActivity().finish();
                }
            }, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION});
        }
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        /*
        try {
            boolean success = mMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            getActivity(), R.raw.style_json));

            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }*/
        //setting map
        mMap.getUiSettings().setZoomControlsEnabled(false);
        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setRotateGesturesEnabled(false);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);

        //update camera map
        /*
        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
                                           @Override
                                           public void onCameraChange(CameraPosition cameraPosition) {
                                               if (mMapIsTouched) {
                                                   mMap.clear();
                                                   if (!isDirections) {
                                                       showMapCurrent();
                                                   } else {
                                                       showMapDirections();
                                                   }
                                                   mMapIsTouched = false;
                                               }
                                           }

                                       }

        );*/
    }

    private void showMapCurrent() {
        if (mMap != null && mCenterLatLong != null) {
            mMap.clear();
            CircleOptions circleOptions = new CircleOptions()
                    .center(mCenterLatLong)
                    .radius(500)
                    .fillColor(0x6EDC7415)
                    .strokeColor(Color.TRANSPARENT)
                    .strokeWidth(0);
            mMap.addCircle(circleOptions);

            CircleOptions circleOptions1 = new CircleOptions()
                    .center(mCenterLatLong)
                    .radius(600)
                    .fillColor(Color.TRANSPARENT)
                    .strokeColor(0x6EDC7415)
                    .strokeWidth(4);
            mMap.addCircle(circleOptions1);

            mMap.addMarker(new MarkerOptions()
                    .flat(true)
                    .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("ic_location_current", 100, 100)))
                    .anchor(0.5f, 0.5f)
                    .position(mCenterLatLong));
        }
    }

    public Bitmap resizeMapIcons(String iconName, int width, int height) {
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), getResources().getIdentifier(iconName, "drawable", getActivity().getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }

    public void fetchLocation() {
        buildGoogleApiClient();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        try {
            Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);
            if (mLastLocation != null) {
                changeMap(mLastLocation);
                Log.d(TAG, "ON connected");

            } else
                try {
                    LocationServices.FusedLocationApi.removeLocationUpdates(
                            mGoogleApiClient, this);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            try {
                LocationRequest mLocationRequest = new LocationRequest();
                mLocationRequest.setInterval(10000);
                mLocationRequest.setFastestInterval(5000);
                mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                LocationServices.FusedLocationApi.requestLocationUpdates(
                        mGoogleApiClient, mLocationRequest, this);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        try {
            if (location != null) {
                mLocation = location;
                changeMap(location);
                LocationServices.FusedLocationApi.removeLocationUpdates(
                        mGoogleApiClient, this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void changeMap(Location location) {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;
        }
        // check if map is created successfully or not
        if (mMap != null) {
            LatLng latLong = new LatLng(location.getLatitude(), location.getLongitude());
            mLocation = location;
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(latLong).build();//.tilt(70)
            mCenterLatLong = cameraPosition.target;
            mMap.moveCamera(CameraUpdateFactory.zoomTo(13.0f));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(
                    mCenterLatLong),
                    2000,
                    null);

            showMapCurrent();

            mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                @Override
                public void onMyLocationChange(Location location) {

                }
            });
            //mLocationMarkerText.setText("Lat : " + location.getLatitude() + "," + "Long : " + location.getLongitude());
            //startIntentService(location);
        } else {
            Toast.makeText(getActivity(),
                    "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                    .show();
        }

    }


    @Override
    public void onChangeLayout(int w, int h) {

    }

    @Override
    public void onUpdateMarker(boolean mMapIsTouched) {
    }

    @Override
    public void onDestroyView() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
        if (getActivity() != null) {
            try {
                Fragment fragment = (getActivity().getSupportFragmentManager()
                        .findFragmentById(R.id.map_view_fragment));
                FragmentTransaction ft = getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction();
                ft.remove(fragment);
                ft.commitAllowingStateLoss();
            } catch (Exception e) {
            }
        }
        super.onDestroyView();
    }
}