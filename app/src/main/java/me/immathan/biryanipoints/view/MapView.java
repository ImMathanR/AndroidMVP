package me.immathan.biryanipoints.view;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.immathan.biryanipoints.R;
import me.immathan.biryanipoints.model.HotelModel;

/**
 * Created by Mathan-GG on 11-Jun-17.
 */

public class MapView implements LifecycleObserver, OnMapReadyCallback {

    private static final LatLng BANGALORE_LAT_LNG = new LatLng(12.9716, 77.5946);

    @BindView(R.id.map_view)
    com.google.android.gms.maps.MapView mMapView;
    Unbinder unbinder;
    private GoogleMap mMap;
    private static final String TAG = MapView.class.getSimpleName();
    private List<HotelModel> mHotelModels;

    public MapView(View view, Lifecycle lifecycle, Bundle savedInstanceState) {
        lifecycle.addObserver(this);
        ButterKnife.bind(this, view);
        unbinder = ButterKnife.bind(this, view);
        mMapView.getMapAsync(this);
        mMapView.onCreate(savedInstanceState);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        mMapView.onStart();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        mMapView.onResume();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        mMapView.onPause();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        mMapView.onStop();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        mMapView.onDestroy();
        unbinder.unbind();
    }

    public void setHotelList(List<HotelModel> hotelList) {
        Log.d(TAG, "Received Hotels list");
        mHotelModels = hotelList;
        loadMarker();
    }

    private void loadMarker() {
        if (mMap == null || mHotelModels == null) {
            return;
        }

        List<Marker> markers = new ArrayList<>();
        for (int i = 0; i < mHotelModels.size(); i++) {
            HotelModel hotelModel = mHotelModels.get(i);
            LatLng latLng = new LatLng(hotelModel.getLat(), hotelModel.getLon());
            MarkerOptions markerOptions = new MarkerOptions()
                    .position(latLng)
                    .title(hotelModel.getName());
            Marker marker = mMap.addMarker(markerOptions);
            markers.add(marker);
        }

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Marker marker : markers) {
            builder.include(marker.getPosition());
        }
        LatLngBounds bounds = builder.build();
        int padding = 100; // offset from edges of the map in pixels
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        mMap.animateCamera(cu);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(markers.get(0).getPosition(), 11f));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (mMap != null) {
            return;
        }
        mMap = googleMap;
        loadMarker();
    }

    protected GoogleMap getMap() {
        return mMap;
    }
}
