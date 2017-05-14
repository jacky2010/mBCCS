package com.viettel.mbccs.constance;

import android.support.annotation.StringDef;

/**
 * Created by buidinhviet on 5/14/17.
 */

@StringDef({ ButtonTyper.DEFAULT, ButtonTyper.GREEN, ButtonTyper.RED, ButtonTyper.WHITE })
public @interface ButtonTyper {
    String DEFAULT = "default";
    String GREEN = "green";
    String RED = "red";
    String WHITE = "white";
}
