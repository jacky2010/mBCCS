package com.viettel.mbccs.screen.manager.area.holder;

import android.view.View;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseViewHolder;
import com.viettel.mbccs.data.model.Area;
import com.viettel.mbccs.widget.ItemTextIcon;

import butterknife.BindView;

/**
 * Created by jacky on 6/5/17.
 */

public class AreaChanelHolder extends BaseViewHolder {

    @BindView(R.id.item_area) ItemTextIcon mItemArea;

    public AreaChanelHolder(View itemView) {
        super(itemView);
    }

    public void render(Area item) {
        mItemArea.setTitle(item.getFullName());
    }
}
