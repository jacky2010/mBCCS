package com.viettel.mbccs.variable;

import com.viettel.mbccs.data.model.LoginInfo;
import com.viettel.mbccs.data.model.UserInfo;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class Constants {

    public static final int NUM_VISIBLE_THRESOLD = 5;
    public static final int NUM_IMAGE_DOWNLOAD = 10;
    public static final int BOTTOM_MENU_COUNT = 5;
    public static final String DEFAULT_NUMBER_INPUT = "0";

    public class SharePref {
        public static final String PREF_NAME = "mbccs_db";

        /**
         * key put get language in app
         * put when language have set in config
         */
        public static final String LANGUAGE = "LANGUAGE";

        /**
         * key put get country in app
         * put when language have set in config
         */
        public static final String COUNTRY = "COUNTRY";

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

        /**
         * key put get LOGIN INFO in app (value instanceOf {@link LoginInfo})
         * put when log in
         * remove when logout
         */
        public static final String LOGIN_INFO = "LOGIN_INFO";

        /**
         * key put get LOGIN USER NAME in app
         * put when log in
         * remove when logout
         */
        public static final String LOGIN_USER_NAME = "LOGIN_USER_NAME";

        /**
         * key put get USER INFO in app  (value instanceOf {@link UserInfo})
         * put when log in
         * remove when logout
         */
        public static final String USER_INFO = "USER_INFO";

        /**
         * key put get is create data base area
         * put true if create success
         */
        public static final String CREATE_DATA_AREA = "create_data_area";

        /**
         * key put get is download image "first use the app"
         * put true if download success
         */
        public static final String DOWNLOAD_IMAGE = "download_data";

        /**
         * key put get is save list id image to database"
         * put true if save success
         */
        public static final String SAVE_ID_IMAGE = "save_id_image";

        public static final String API_KEY = "api_key";
        public static final String SESSION_VTG = "session_vtg";
        public static final String API_KEY_VTG = "api_key_vtg";
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
        public static final String FORM_TYPE = "form_type";
        public static final String CHANGE_SIM_ITEM = "change_sim_item";
        public static final String ITEM_LIST = "item_list";
        public static final String RESULT = "result";
        public static final String STAFF_INFO = "staff_info";
        public static final String STAFF_LIST = "staff_list";
        public static final String TASK_INFO = "task_info";
        public static final String LIST_STOCK_TOTAL = "list_stock_total";
        public static final String CUSTOMER_ITEM = "customer_item";
        public static final String PRE_TAX = "pre_tax";
        public static final String TAX = "tax";
        public static final String DISCOUNT = "discount";
        public static final String TOTAL = "total";
        public static final String TRANS_TYPE = "trans_type";
        public static final String ISDN = "isdn";
        public static final String PAY_METHOD = "pay_method";
        public static final String STAFF = "staff";
        public static final String ISDN_WALLET = "isdn_wallet";
        public static final String FROM_CHANNEL = "from_channel";
        public static final String TO_CHANNEL = "to_channel";
        public static final String SERVICE_FEE = "service_fee";
        public static final String SIM_FEE = "sim_fee";
        public static final String SURVEY_QUESTION = "survey_question";
        public static final String MESSAGE = "message";
        public static final String SALE_ORDER_ID = "sale_order_id";
        public static final String CHANNEL_NAME = "channel_name";

        /*import importStock warehouse*/
        public static final String STOCK_TRANS_ID = "stock_trans_id";
        public static final String STOCK_TRANS = "stock_trans";
        public static final String STOCK_TRANS_DETAIL_LIST = "stock_trans_detail_list";
        public static final String CMD_CODE_TITLE = "cmd_code_title";
        public static final String CMD_RECEIVE_TITLE = "cmd_receive_title";
        public static final String CMD_SENDER_TITLE = "cmd_sender_title";
        public static final String ACTION_TYPE = "action_type";

        /*survey*/
        public static final String SURVEY = "survey";
    }

    public class Tax {
        public static final int DEFAULT_TAX_RATE = 10;
        public static final int DEFAULT_TAX_CALC_BACK_RATE = 11;
    }

    public class View {
        public static final String HINT = "hint";
    }

    public class Service {
        public static final String RESPONSE_OK = "0";
        public static final String SURVEY_QUESTION = "survey_question";
    }
}
