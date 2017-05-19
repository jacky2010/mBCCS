package com.viettel.mbccs.widget;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Anh Vu Viet on 5/19/2017.
 */

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    private RecyclerView.LayoutManager layoutManager;

    public SpacesItemDecoration(int space, RecyclerView.LayoutManager layoutManager) {
        this.space = space;
        this.layoutManager = layoutManager;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;
        outRect.top = 0;

        if (layoutManager instanceof GridLayoutManager) {
            for (int i = 0; i < ((GridLayoutManager) layoutManager).getSpanCount(); i++) {
                if (parent.getChildLayoutPosition(view) == i) {
                    outRect.top = space;
                }
            }
        } else {
            if (parent.getChildLayoutPosition(view) == 0) {
                outRect.top = space;
            }
        }
    }
}
