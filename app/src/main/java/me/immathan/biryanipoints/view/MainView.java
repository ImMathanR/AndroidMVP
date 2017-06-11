package me.immathan.biryanipoints.view;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.immathan.biryanipoints.R;
import me.immathan.biryanipoints.bus.Bus;
import me.immathan.biryanipoints.events.EventModel;
import me.immathan.biryanipoints.events.EventType;
import me.immathan.biryanipoints.fragments.HighlightsFragment;
import me.immathan.biryanipoints.fragments.MapFragment;
import me.immathan.biryanipoints.view.adapters.ViewPagerAdapter;

/**
 * Created by Mathan-GG on 10-Jun-17.
 */

public class MainView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tabs)
    TabLayout mTabs;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.view_type)
    ImageView mViewType;
    private WeakReference<AppCompatActivity> mActivityRef;

    public MainView(AppCompatActivity activity) {
        ButterKnife.bind(this, activity);
        mActivityRef = new WeakReference<>(activity);
        activity.setSupportActionBar(mToolbar);
        setupViewPager(mViewpager);
        mTabs.setupWithViewPager(mViewpager);
    }

    private void setupViewPager(ViewPager viewPager) {
        if (getActivity() != null) {
            ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
            adapter.addFragment(new HighlightsFragment(), "Top 10");
            adapter.addFragment(new MapFragment(), "Map");
            viewPager.setAdapter(adapter);
        }
    }

    @Nullable
    public AppCompatActivity getActivity() {
        return mActivityRef.get();
    }

    @OnClick(R.id.view_type)
    public void changeViewShowType() {
        mViewType.setActivated(!mViewType.isActivated());

        if (mViewType.isActivated()) {
            mViewType.setImageResource(R.drawable.ic_view_list);
            EventModel eventModel = new EventModel(EventType.VIEW_BY_GRID);
            Bus.getDefault().post(eventModel);
        } else {
            mViewType.setImageResource(R.drawable.ic_grid_on);
            EventModel eventModel = new EventModel(EventType.VIEW_BY_LIST);
            Bus.getDefault().post(eventModel);
        }
    }
}
