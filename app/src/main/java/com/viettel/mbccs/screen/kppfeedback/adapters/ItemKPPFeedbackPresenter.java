package com.viettel.mbccs.screen.kppfeedback.adapters;

import android.content.Context;

import com.viettel.mbccs.data.model.KPPFeedback;

/**
 * Created by eo_cuong on 5/15/17.
 */

public class ItemKPPFeedbackPresenter {

    private KPPFeedback mItem;
    private Context mContext;

    public ItemKPPFeedbackPresenter(Context context, KPPFeedback item) {
        mItem = item;
        mContext = context;
    }

    public KPPFeedback getItem() {
        return mItem;
    }
}
