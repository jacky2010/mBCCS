package com.viettel.mbccs.variable;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class Constants {

    public static final int NUM_VISIBLE_THRESOLD = 5;
    public static final String DEFAULT_NUMBER_INPUT = "0";

    public class SharePref {
        public static final String PREF_NAME = "mbccs_db";

        /**
         * key put get language in app
         * put when user login in app
         */
        public static final String STAFF_INFO = "staff_info";

        /**
         * key put get language in app
         * put when language have set in config
         */
        public static final String LANGUAGE = "language";

        /**
         * key put get country in app
         * put when language have set in config
         */
        public static final String COUNTRY = "country";

        /**
         * key put get DISPLAY DASHBOARD in app (true or false)
         * put when set in config
         */
        public static final String DISPLAY_DASHBOARD = "DISPLAY_DASHBOARD";

        /**
         * key put get notification in app (true or false)
         * put when set in config
         */
        public static final String NOTIFICATION = "NOTIFICATION";

        /**
         * key put get SYNC BCCS in app (true or false)
         * put when set in config
         */
        public static final String SYNC_BCCS = "SYNC_BCCS";

        /**
         * key put get time SYNC BCCS in app (value instanceOf {@link Integer})
         * put when set in config
         */
        public static final String TIME_SYNC_BCCS = "TIME_SYNC_BCCS";
    }

    public class BundleConstant {
        public static final String SCAN_SERIAL = "scan_serial";
        public static final String LIST_SERIAL = "list_serial";
        public static final String SERIAL_PICKER_MODEL = "serial_picker_model";
        public static final String STOCK_SERIAL_LIST = "goods_list";
        public static final String SALE_PROGRAM = "sell_program";
        public static final String SALE_PROGRAM_LIST = "sale_program_list";
        public static final String TELECOMSERIVE = "telecomservice";
        public static final String INFOR_SALE_REQUEST = "infor_sale_request";
        public static final String SALE_TRANS = "sale_trans";
        public static final String CHANNEL = "channel";
        public static final String CHANNEL_LIST = "channel_list";
    }
}
