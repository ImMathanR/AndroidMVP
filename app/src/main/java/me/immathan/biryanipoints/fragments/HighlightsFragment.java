package me.immathan.biryanipoints.fragments;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.immathan.biryanipoints.R;
import me.immathan.biryanipoints.presenter.HighLightsPresenter;
import me.immathan.biryanipoints.presenter.HighLightsPresenterImpl;

public class HighlightsFragment extends Fragment implements LifecycleRegistryOwner {

    private final LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);

    public HighlightsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.highlight_list, container, false);
        HighLightsPresenter highLightsPresenter = new HighLightsPresenterImpl(view, getLifecycle());

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return mLifecycleRegistry;
    }
}