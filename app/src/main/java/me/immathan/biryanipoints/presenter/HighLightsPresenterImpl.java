package me.immathan.biryanipoints.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;
import android.view.View;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import me.immathan.biryanipoints.bus.Bus;
import me.immathan.biryanipoints.events.EventModel;
import me.immathan.biryanipoints.events.EventType;
import me.immathan.biryanipoints.model.HotelListModel;
import me.immathan.biryanipoints.model.HotelModel;
import me.immathan.biryanipoints.view.HighLightsView;

/**
 * Created by Mathan-GG on 10-Jun-17.
 */

public class HighLightsPresenterImpl implements HighLightsPresenter, LifecycleObserver {

    private static final String TAG = HighLightsPresenterImpl.class.getSimpleName();

    private final Bus mBus = Bus.getDefault();
    private final HighLightsView mHighLightsView;

    public HighLightsPresenterImpl(View view, Lifecycle lifecycle) {
        lifecycle.addObserver(this);
        mHighLightsView = new HighLightsView(view, lifecycle);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    @Override
    public void register() {
        Log.d(TAG, "onRegister");
        mBus.register(this);
        HotelListModel.getInstance().fetchHotelList();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    @Override
    public void unregister() {
        Log.d(TAG, "onUnRegister");
        mBus.unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventModel eventModel) {
        if (eventModel.getEventType() == EventType.HOTEL_DETAILS_RECEIVED) {
            mHighLightsView.setHotelList((List<HotelModel>) eventModel.getData());
        } else if (eventModel.getEventType() == EventType.HOTEL_DETAILS_NOT_RECEIVED) {
            mHighLightsView.onError();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onViewTypeChanged(EventModel eventModel) {
        if (eventModel.getEventType() == EventType.VIEW_BY_LIST) {
            mHighLightsView.changeViewType(true);
        } else if (eventModel.getEventType() == EventType.VIEW_BY_GRID) {
            mHighLightsView.changeViewType(false);
        }
    }

}
