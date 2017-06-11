package me.immathan.biryanipoints.presenter;


import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import me.immathan.biryanipoints.bus.Bus;
import me.immathan.biryanipoints.view.MainView;

public class MainPresenterImpl implements MainPresenter, LifecycleObserver {

    private static final String TAG = MainPresenterImpl.class.getSimpleName();

    private final MainView mMainView;
    private final Bus mBus = Bus.getDefault();

    public MainPresenterImpl(AppCompatActivity activity) {
        mMainView = new MainView(activity);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    @Override
    public void register() {
        Log.d(TAG, "onRegister");
        mBus.register(this);
    }

    @Override
    public void unregister() {
        Log.d(TAG, "onUnRegister");
        mBus.unregister(this);
    }
}