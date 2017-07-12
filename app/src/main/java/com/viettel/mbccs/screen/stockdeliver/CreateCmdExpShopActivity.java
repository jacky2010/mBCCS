package com.viettel.mbccs.screen.stockdeliver;

import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.OwnerType;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.OwnerCode;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetListOwnerCodeRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetListOwneCodeReponse;
import com.viettel.mbccs.screen.stockdeliver.createcommand.BaseCreateCommandNoteActivity;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import java.util.ArrayList;
import java.util.List;

public class CreateCmdExpShopActivity extends BaseCreateCommandNoteActivity<OwnerCode> {

    private List<OwnerCode> mOwnerCodes = new ArrayList<>();

    @Override
    public void init() {
        loadStaffList();
    }

    private void loadStaffList() {
        DataRequest<GetListOwnerCodeRequest> dataRequest = new DataRequest<>();
        GetListOwnerCodeRequest request = new GetListOwnerCodeRequest();
        request.setShopId(String.valueOf(mUserRepository.getUserInfo().getShop().getShopId()));
        dataRequest.setWsCode(WsCode.GetListOwnerCode);
        dataRequest.setWsRequest(request);
        mUserRepository.getListOwnerCode(dataRequest)
                .subscribe(new MBCCSSubscribe<GetListOwneCodeReponse>() {
                    @Override
                    public void onSuccess(GetListOwneCodeReponse object) {
                        if (object != null && object.getOwnerCodes() != null) {
                            mOwnerCodes.addAll(object.getOwnerCodes());
                            setListReceiverWareHouser(mOwnerCodes);
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
        return BaseCreateCommandNoteActivity.ACTION_CREATE_CMD;
    }

    @Override
    public String getTitleName() {
        return getString(R.string.xuatkhocapduoi_title_create_command);
    }

    @Override
    public long getOwnerIdStock() {
        return mUserRepository.getUserInfo().getStaffInfo().getStaffId();
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
        return mOwnerCodes.get(getPositionReceicerWareHouse()).getStaffId();
    }

    @Override
    public long getToOwnerType() {
        return OwnerType.SHOP;
    }
}
