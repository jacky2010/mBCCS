package com.viettel.mbccs.screen.serialpicker.adapter;

import com.viettel.mbccs.data.model.SerialBO;

/**
 * Created by eo_cuong on 5/14/17.
 */

public class ItemSerialPresenter {
    private boolean isSelected;
    private SerialBO mSerialBlock;
    private int type;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public SerialBO getSerialBlock() {
        return mSerialBlock;
    }

    public void setSerialBlock(SerialBO serialBlock) {
        mSerialBlock = serialBlock;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
