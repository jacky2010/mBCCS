package com.viettel.mbccs.service.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.Area;
import com.viettel.mbccs.data.model.database.AreaDataBase;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.utils.ObjectUtils;
import com.viettel.mbccs.utils.StringUtils;
import java.util.List;

/**
 * Created by HuyQuyet on 6/17/17.
 */

public class CreateDataBaseService extends IntentService {

    public static final String ACTION_CREATE_AREA_SUCCESS = "action_success";
    public static final String ACTION_CREATE_AREA_FAIL = "action_fail";
    public static final String ACTION_CREATE_AREA_COMPLETED = "action_completed";
    public static final String DATA_CREATE_AREA_SUCCESS = "data_success";
    public static final String DATA_CREATE_AREA_COMPLETED = "data_completed";

    private UserRepository userRepository;

    public CreateDataBaseService() {
        super(CreateDataBaseService.class.getName());
        setIntentRedelivery(true);
        userRepository = UserRepository.getInstance();
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public CreateDataBaseService(String name) {
        super(CreateDataBaseService.class.getName());
        setIntentRedelivery(true);
        userRepository = UserRepository.getInstance();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (userRepository.isCreateDataBaseArea()) {
            intent.setAction(ACTION_CREATE_AREA_COMPLETED);
            intent.putExtra(DATA_CREATE_AREA_COMPLETED, 100);
            LocalBroadcastManager.getInstance(CreateDataBaseService.this).sendBroadcast(intent);
            return;
        }
        ListArea listArea =
                new Gson().fromJson(StringUtils.loadJson(this, "db_area.json"), ListArea.class);
        List<Area> areaList = listArea.getList();

        ActiveAndroid.beginTransaction();
        int size = areaList.size();
        try {
            for (int i = 0; i < size; i++) {
                List<AreaDataBase> areaDataBaseResult = null;
                try {
                    areaDataBaseResult = new Select().from(AreaDataBase.class)
                            .where(AreaDataBase.Columns.AREA_CODE + " = ?",
                                    areaList.get(i).getAreaCode())
                            .execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (areaDataBaseResult != null && areaDataBaseResult.size() != 0) {
                    continue;
                }

                AreaDataBase areaDataBase =
                        ObjectUtils.convertObject(areaList.get(i), AreaDataBase.class);
                areaDataBase.save();

                intent.setAction(ACTION_CREATE_AREA_SUCCESS);
                intent.putExtra(DATA_CREATE_AREA_SUCCESS, (int) (((float) i / size) * 100));
                LocalBroadcastManager.getInstance(CreateDataBaseService.this).sendBroadcast(intent);
            }

            intent.setAction(ACTION_CREATE_AREA_COMPLETED);
            intent.putExtra(DATA_CREATE_AREA_COMPLETED, 100);
            LocalBroadcastManager.getInstance(CreateDataBaseService.this).sendBroadcast(intent);

            ActiveAndroid.setTransactionSuccessful();

            userRepository.setCreateDataBaseArea(true);
        } catch (Exception e) {
            e.printStackTrace();
            intent.setAction(ACTION_CREATE_AREA_FAIL);
            LocalBroadcastManager.getInstance(CreateDataBaseService.this).sendBroadcast(intent);
            userRepository.setCreateDataBaseArea(false);
        } finally {
            ActiveAndroid.endTransaction();
        }
    }

    private class ListArea {
        @Expose
        @SerializedName("items")
        private List<Area> list;

        public List<Area> getList() {
            return list;
        }

        public void setList(List<Area> list) {
            this.list = list;
        }
    }
}
