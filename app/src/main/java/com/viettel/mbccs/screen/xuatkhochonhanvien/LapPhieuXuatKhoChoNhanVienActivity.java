package com.viettel.mbccs.screen.xuatkhochonhanvien;

import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.OwnerType;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.OwnerCode;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetListOwnerCodeRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetListOwneCodeReponse;
import com.viettel.mbccs.screen.warehousecommon.basecreatecmdnote.BaseCreateCommandNoteActivity;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import com.viettel.mbccs.variable.Constants;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eo_cuong on 7/9/17.
 */

public class LapPhieuXuatKhoChoNhanVienActivity extends BaseCreateCommandNoteActivity<OwnerCode> {

    private List<OwnerCode> ownerCodes = new ArrayList<>();

    @Override
    public void init() {
        loadStaffList();
    }

    private void loadStaffList() {
        GetListOwnerCodeRequest request = new GetListOwnerCodeRequest();
        request.setShopId(String.valueOf(mUserRepository.getUserInfo().getShop().getShopId()));

        DataRequest<GetListOwnerCodeRequest> dataRequest = new DataRequest<>();
        dataRequest.setWsCode(WsCode.GetListOwnerCode);
        dataRequest.setWsRequest(request);
        mUserRepository.getListOwnerCode(dataRequest)
                .subscribe(new MBCCSSubscribe<GetListOwneCodeReponse>() {
                    @Override
                    public void onSuccess(GetListOwneCodeReponse object) {
                        if (object != null && object.getOwnerCodes() != null) {
                            if (ownerCodes != null && ownerCodes.size() != 0) ownerCodes.clear();
                            ownerCodes.addAll(object.getOwnerCodes());
                            setListReceiverWareHouser(ownerCodes);
                        }
                    }

                    @Override
                    public void onError(BaseException error) {

                    }

                    @Override
                    public void onRequestFinish() {
                        super.onRequestFinish();
                    }
                });
    }

    @Override
    public int getActionType() {
        return BaseCreateCommandNoteActivity.ACTION_CREATE_NOTE;
    }

    @Override
    public String getTitleName() {
        return getString(R.string.export_staff_label_create_cmd_staff);
    }

    @Override
    public long getOwnerIdStock() {
        return mUserRepository.getUserInfo().getShop().getShopId();
    }

    @Override
    public long getOwnerTypeStock() {
        return OwnerType.SHOP;
    }

    @Override
    public long getFromOwnerId() {
        return mUserRepository.getUserInfo().getShop().getShopId();
    }

    @Override
    public long getFromOwnerType() {
        return OwnerType.SHOP;
    }

    @Override
    public long getToOwnerId() {
        return ownerCodes.get(getPositionReceicerWareHouse()).getStaffId();
    }

    @Override
    public long getToOwnerType() {
        return OwnerType.STAFF;
    }

    @Override
    public int getStepType() {
        return BaseCreateCommandNoteActivity.STEP_2;
    }

    @Override
    public void onRejectSuccess() {

    }

    @Override
    public void onCreateSuccess() {

    }

    @Override
    public long getReasonId() {
        return Constants.FuntionConstant.STAFF_EXPORT_SHOP_REASON_ID;
    }
}
