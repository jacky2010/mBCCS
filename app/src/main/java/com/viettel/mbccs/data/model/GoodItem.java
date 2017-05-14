package com.viettel.mbccs.data.model;

import com.google.gson.annotations.Expose;
import com.viettel.mbccs.utils.Common;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eo_cuong on 4/14/17.
 */

public class GoodItem implements Serializable {

    @Expose
    private boolean hasSerial;
    @Expose
    private String name;
    @Expose
    private String imageUrl;
    @Expose
    private float goodPrice;
    @Expose
    private int remainGoodCount;
    @Expose
    private int choiceCount;
    @Expose
    private int serialCount;
    @Expose
    private List<SerialBlock> mSerialBlocks = new ArrayList<>();
    @Expose
    private List<Integer> mSerials = new ArrayList<>();

    public List<SerialBlock> getSerialBlocks() {
        if (mSerialBlocks.size() == 0) {
            if (mSerials.size() > 0) {
                return Common.getSerialBlockBySerials(mSerials);
            }
        }
        return mSerialBlocks;
    }

    public void setSerialBlocks(List<SerialBlock> serialBlocks) {

        mSerialBlocks = serialBlocks;
    }

    public boolean isHasSerial() {
        return hasSerial;
    }

    public int getSerialCount() {
        if (getSerialBlocks() == null) {
            return 0;
        }
        return Common.getSerialCountByListSerialBlock(getSerialBlocks());
    }

    public void setSerialCount(int serialCount) {
        this.serialCount = serialCount;
    }

    public int getRemainGoodCount() {
        return remainGoodCount;
    }

    public void setRemainGoodCount(int remainGoodCount) {
        this.remainGoodCount = remainGoodCount;
    }

    public int getChoiceCount() {
        return choiceCount;
    }

    public void setChoiceCount(int choiceCount) {
        this.choiceCount = choiceCount;
    }

    public boolean hasSerial() {
        return hasSerial;
    }

    public void setHasSerial(boolean hasSerial) {
        this.hasSerial = hasSerial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public float getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(float goodPrice) {
        this.goodPrice = goodPrice;
    }

    public List<Integer> getSerials() {
        return mSerials;
    }

    public void setSerials(List<Integer> serials) {
        mSerials = serials;
    }
}
