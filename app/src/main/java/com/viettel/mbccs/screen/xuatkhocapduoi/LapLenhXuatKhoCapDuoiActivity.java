package com.viettel.mbccs.screen.xuatkhocapduoi;

import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.OwnerType;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.Shop;
import com.viettel.mbccs.data.source.BanHangKhoTaiChinhRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetListShopRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetListShopResponse;
import com.viettel.mbccs.screen.warehousecommon.basecreatecmdnote.BaseCreateCommandNoteActivity;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.src;

public class LapLenhXuatKhoCapDuoiActivity extends BaseCreateCommandNoteActivity<Shop> {

    private List<Shop> mListShop = new ArrayList<>();

    @Override
    public void init() {
        loadStaffList();
    }

    private void loadStaffList() {
        DataRequest<GetListShopRequest> dataRequest = new DataRequest<>();
        GetListShopRequest request = new GetListShopRequest();
        request.setParentShopId((mUserRepository.getUserInfo().getShop().getShopId()));
        dataRequest.setWsCode(WsCode.GetListShop);
        dataRequest.setWsRequest(request);
        BanHangKhoTaiChinhRepository.getInstance()
                .getListShop(dataRequest)
                .subscribe(new MBCCSSubscribe<GetListShopResponse>() {
                    @Override
                    public void onSuccess(GetListShopResponse object) {
                        if (object != null && object.getShopList() != null) {
                            mListShop.addAll(object.getShopList());
                            setListReceiverWareHouser(mListShop);
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
        return mListShop.get(getPositionReceicerWareHouse()).getShopId();
    }

    @Override
    public long getToOwnerType() {
        return OwnerType.SHOP;
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
