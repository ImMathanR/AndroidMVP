package me.immathan.biryanipoints.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import me.immathan.biryanipoints.bus.Bus;
import me.immathan.biryanipoints.events.EventModel;
import me.immathan.biryanipoints.events.EventType;
import me.immathan.biryanipoints.model.HotelModel;
import me.immathan.biryanipoints.view.MapView;

/**
 * Created by Mathan-GG on 11-Jun-17.
 */

public class MapViewPresenterImpl implements MapViewPresenter, LifecycleObserver {

    private static final String TAG = MapViewPresenterImpl.class.getSimpleName();

    private MapView mMapView;
    private final Bus mBus = Bus.getDefault();

    public MapViewPresenterImpl(View view, Lifecycle lifecycle, Bundle savedInstanceState) {
        lifecycle.addObserver(this);
        mMapView = new MapView(view, lifecycle, savedInstanceState);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    @Override
    public void register() {
        Log.d(TAG, "onRegister");
        mBus.register(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    @Override
    public void unregister() {
        Log.d(TAG, "onUnRegister");
        mBus.unregister(this);
    }

    @Subscribe
    public void onEvent(EventModel eventModel) {
        if (eventModel.getEventType() == EventType.HOTEL_DETAILS_RECEIVED) {
            mMapView.setHotelList((List<HotelModel>) eventModel.getData());
        } else if (eventModel.getEventType() == EventType.HOTEL_DETAILS_NOT_RECEIVED) {
            // Do nothing
        }
    }

}
