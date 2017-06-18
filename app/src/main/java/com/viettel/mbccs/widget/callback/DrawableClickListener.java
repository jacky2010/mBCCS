package com.viettel.mbccs.widget.callback;

import android.support.annotation.IntDef;
import android.view.View;

/**
 * Created by HuyQuyet on 6/16/17.
 */

public interface DrawableClickListener {

    @IntDef({
            DrawablePosition.TOP, DrawablePosition.BOTTOM, DrawablePosition.LEFT,
            DrawablePosition.RIGHT
    })
    public @interface DrawablePosition {
        int TOP = 1;
        int BOTTOM = 2;
        int LEFT = 3;
        int RIGHT = 4;
    }

    public void onClick(View view, @DrawablePosition int target);
}
