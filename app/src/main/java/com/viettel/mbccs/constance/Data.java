package com.viettel.mbccs.constance;

import android.support.annotation.StringDef;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuyQuyet on 6/6/17.
 */

public class Data {
    @StringDef({ Gender.MALE, Gender.FEMALE })
    public @interface Gender {
        String MALE = "M";
        String FEMALE = "N";
    }

    public static List<DataField> connectorMobileService() {
        List<DataField> result = new ArrayList<>();
        result.add(new DataField("M", "Mobile"));
        result.add(new DataField("H", "Homephone"));
        result.add(new DataField("A", "ADSL"));
        result.add(new DataField("L", "Leasedline"));
        result.add(new DataField("P", "PSTN"));
        result.add(new DataField("W", "White leaseline "));
        result.add(new DataField("F", "FTTH"));
        return result;
    }

    public static class DataField {
        String name;
        String code;

        DataField(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
