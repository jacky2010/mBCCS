package com.viettel.mbccs.base;

import com.viettel.mbccs.MBCCSApplication;
import com.viettel.mbccs.utils.GsonUtils;

public class BaseModel<T> implements Cloneable {
    public T data;

    public BaseModel cloneData() {
        return GsonUtils.String2Object(this.toJson(), this.getClass());
    }

    public String toJson() {
        return GsonUtils.Object2String(this);
    }
}
