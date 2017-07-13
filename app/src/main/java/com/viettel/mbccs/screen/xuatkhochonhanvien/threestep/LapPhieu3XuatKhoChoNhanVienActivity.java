package com.viettel.mbccs.screen.xuatkhochonhanvien.threestep;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.OwnerCode;
import com.viettel.mbccs.screen.warehousecommon.basecreatecmdnote.BaseCreateCommandNoteActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eo_cuong on 7/9/17.
 */

public class LapPhieu3XuatKhoChoNhanVienActivity extends BaseCreateCommandNoteActivity<OwnerCode> {

    private List<OwnerCode> ownerCodes = new ArrayList<>();

    @Override
    public void init() {
//        loadStaffList();
    }

//    private void loadStaffList() {
//        GetListOwnerCodeRequest request = new GetListOwnerCodeRequest();
//        request.setShopId(String.valueOf(mUserRepository.getUserInfo().getShop().getShopId()));
//
//        DataRequest<GetListOwnerCodeRequest> dataRequest = new DataRequest<>();
//        dataRequest.setWsCode(WsCode.GetListOwnerCode);
//        dataRequest.setWsRequest(request);
//        mUserRepository.getListOwnerCode(dataRequest)
//                .subscribe(new MBCCSSubscribe<GetListOwneCodeReponse>() {
//                    @Override
//                    public void onSuccess(GetListOwneCodeReponse object) {
//                        if (object != null && object.getOwnerCodes() != null) {
//                            if (ownerCodes != null && ownerCodes.size() != 0) ownerCodes.clear();
//                            ownerCodes.addAll(object.getOwnerCodes());
//                            setListReceiverWareHouser(ownerCodes);
//                        }
//                    }
//
//                    @Override
//                    public void onError(BaseException error) {
//
//                    }
//
//                    @Override
//                    public void onRequestFinish() {
//                        super.onRequestFinish();
//                    }
//                });
//    }

    @Override
    public int getActionType() {
        return BaseCreateCommandNoteActivity.ACTION_CREATE_NOTE;
    }

    @Override
    public String getTitleName() {
        return getString(R.string.xuat_kho_cho_nhan_vien_lap_phieu_xuat_kho_cho_nhan_vien);
    }

    @Override
    public long getOwnerIdStock() {
        return 0;
    }

    @Override
    public long getOwnerTypeStock() {
        return 0;
    }

    @Override
    public long getFromOwnerId() {
        return 0;
    }

    @Override
    public long getFromOwnerType() {
        return 0;
    }

    @Override
    public long getToOwnerId() {
        return 0;
    }

    @Override
    public long getToOwnerType() {
        return 0;
    }

    @Override
    public int getStepType() {
        return BaseCreateCommandNoteActivity.STEP_3;
    }

    @Override
    public void onRejectSuccess() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onCreateSuccess() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public long getReasonId() {
        return 0;
    }
}
