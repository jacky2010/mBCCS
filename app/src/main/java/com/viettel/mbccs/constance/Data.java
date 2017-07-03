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

    public static List<DataField> connectorTelServiceType() {
        List<DataField> result = new ArrayList<>();
        result.add(new DataField(1, "M", "Mobile"));
        result.add(new DataField(2, "H", "Homephone"));
        result.add(new DataField(4, "A", "ADSL"));
        result.add(new DataField(5, "L", "Leasedline"));
        result.add(new DataField(9, "P", "PSTN"));
        result.add(new DataField(12, "W", "White leaseline "));
        result.add(new DataField(15, "F", "FTTH"));
        return result;
    }

    public static class DataField {
        String name;
        String code;
        int id;

        DataField(int id, String code, String name) {
            this.id = id;
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
