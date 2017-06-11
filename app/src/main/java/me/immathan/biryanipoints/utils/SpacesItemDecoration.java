package me.immathan.biryanipoints.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private boolean isList;

    public SpacesItemDecoration(int space, boolean isList) {
        this.space = space;
        this.isList = isList;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = 0;
        outRect.right = space;
        outRect.bottom = space;

        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = space;
        }
        if (!isList && parent.getChildAdapterPosition(view) == 1) {
            outRect.top = space;
        }
    }
}