package com.viettel.mbccs.screen.uploadimage;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.UploadImage;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import java.util.List;

/**
 * Created by HuyQuyet on 6/8/17.
 */

public interface UploadImageContract {

    interface Presenter extends BasePresenter {
    }

    interface View extends BaseView<Presenter> {
        void setData(List<UploadImage> list);

        void uploadCompleted();

        void uploadError(BaseException error);

        void onCancel();

        void onStartUpload();
    }
}
