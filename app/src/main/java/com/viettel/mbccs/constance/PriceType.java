package com.viettel.mbccs.constance;

import android.support.annotation.StringDef;

/**
 * Created by eo_cuong on 6/13/17.
 */
@StringDef({ PriceType.PRICE_RETAIL, PriceType.PRICE_CHANNEL })
public @interface PriceType {
    String PRICE_RETAIL = "1";
    String PRICE_CHANNEL = "9";
}
