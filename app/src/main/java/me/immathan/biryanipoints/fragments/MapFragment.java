package me.immathan.biryanipoints.fragments;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.immathan.biryanipoints.R;
import me.immathan.biryanipoints.presenter.MapViewPresenter;
import me.immathan.biryanipoints.presenter.MapViewPresenterImpl;

/**
 * Created by Mathan-GG on 11-Jun-17.
 */

public class MapFragment extends Fragment implements LifecycleRegistryOwner {

    private final LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);

    private MapViewPresenter mMapViewPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_fragment, container, false);
        mMapViewPresenter = new MapViewPresenterImpl(view, getLifecycle(), savedInstanceState);
        return view;
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return mLifecycleRegistry;
    }
}
