package com.viettel.mbccs.screen.serialpicker.adapter;

import com.viettel.mbccs.data.model.SerialBlock;

/**
 * Created by eo_cuong on 5/14/17.
 */

public class ItemSerialPresenter {
    private boolean isSelected;
    private SerialBlock mSerialBlock;
    private int type;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public SerialBlock getSerialBlock() {
        return mSerialBlock;
    }

    public void setSerialBlock(SerialBlock serialBlock) {
        mSerialBlock = serialBlock;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
