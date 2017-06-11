package me.immathan.biryanipoints.view;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.immathan.biryanipoints.R;
import me.immathan.biryanipoints.model.HotelModel;
import me.immathan.biryanipoints.utils.DisplayUtils;
import me.immathan.biryanipoints.utils.SpacesItemDecoration;
import me.immathan.biryanipoints.view.adapters.HotelsAdapter;

/**
 * Created by Mathan-GG on 10-Jun-17.
 */

public class HighLightsView implements LifecycleObserver {

    private static final String TAG = HighLightsView.class.getSimpleName();

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    Unbinder unbinder;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    private List<HotelModel> mHotelModels;
    private SpacesItemDecoration mSpacesItemDecoration;

    public HighLightsView(View view, Lifecycle lifecycle) {
        lifecycle.addObserver(this);
        unbinder = ButterKnife.bind(this, view);
    }

    public void setHotelList(List<HotelModel> hotelList) {
        Log.d(TAG, "Received Hotels list");
        mHotelModels = hotelList;
        mProgressBar.setVisibility(View.GONE);
        changeViewType(true);
    }

    public void changeViewType(boolean isList) {
        HotelsAdapter hotelsAdapter = new HotelsAdapter(mRecyclerView.getContext());
        hotelsAdapter.changeViewType(isList);
        hotelsAdapter.setData(mHotelModels);
        mRecyclerView.setAdapter(hotelsAdapter);
        if (isList) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mRecyclerView.getContext());
            mRecyclerView.removeItemDecoration(mSpacesItemDecoration);
            mSpacesItemDecoration = new SpacesItemDecoration((int) DisplayUtils.pxFromDp(mRecyclerView.getContext(), 5), isList);
            mRecyclerView.addItemDecoration(mSpacesItemDecoration);
            mRecyclerView.setLayoutManager(linearLayoutManager);
        } else {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(mRecyclerView.getContext(), 2);
            mRecyclerView.removeItemDecoration(mSpacesItemDecoration);
            mSpacesItemDecoration = new SpacesItemDecoration((int) DisplayUtils.pxFromDp(mRecyclerView.getContext(), 5), isList);
            mRecyclerView.addItemDecoration(mSpacesItemDecoration);
            mRecyclerView.setLayoutManager(gridLayoutManager);
        }
        //hotelsAdapter.notifyDataSetChanged();
    }

    public void onError() {
        Snackbar.make(mRecyclerView, "Error response", Snackbar.LENGTH_SHORT).show();
        mProgressBar.setVisibility(View.GONE);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        unbinder.unbind();
    }

}
