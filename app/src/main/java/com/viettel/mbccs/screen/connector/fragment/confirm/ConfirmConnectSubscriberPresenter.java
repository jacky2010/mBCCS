package com.viettel.mbccs.screen.connector.fragment.confirm;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.Bitmap;
import com.viettel.mbccs.BR;
import com.viettel.mbccs.constance.Data;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.UserInfo;
import com.viettel.mbccs.data.source.QLKhachHangRepository;
import com.viettel.mbccs.data.source.remote.request.ConnectSubscriberRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.ConnectSubscriberResponse;
import com.viettel.mbccs.service.service.UploadImageService;
import com.viettel.mbccs.utils.DatabaseUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import java.util.ArrayList;
import java.util.List;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by HuyQuyet on 7/3/17.
 */

public class ConfirmConnectSubscriberPresenter extends BaseObservable
        implements ConfirmConnectSubscriberContract.Presenter {
    private Context context;
    private ConfirmConnectSubscriberContract.View view;
    private QLKhachHangRepository qlKhachHangRepository;
    private CompositeSubscription subscriptions;
    private ConnectSubscriberRequest data;
    private UserInfo userInfo;
    private List<Data.DataField> dataSpinner2DichVu;
    private List<String> dataImage;

    private String dichVu;
    private String nameCustomer;
    private String phoneNumberCustomer;
    private String sumMoney;
    private String nameStaff;
    private String phoneNumberStaff;

    private Bitmap imageFront;
    private Bitmap imageBackside;
    private Bitmap imagePortrait;

    public ConfirmConnectSubscriberPresenter(Context context,
            ConfirmConnectSubscriberContract.View view, ConnectSubscriberRequest data,
            UserInfo userInfo, Bitmap customerCurrentImageFront,
            Bitmap customerCurrentImageBackside, Bitmap customerCurrentImagePortrait) {
        this.context = context;
        this.view = view;
        view.setPresenter(this);
        this.data = data;
        this.userInfo = userInfo;
        this.imageFront = customerCurrentImageFront;
        this.imageBackside = customerCurrentImageBackside;
        this.imagePortrait = customerCurrentImagePortrait;

        qlKhachHangRepository = QLKhachHangRepository.getInstance();
        subscriptions = new CompositeSubscription();
    }

    @Override
    public void subscribe() {
        dataSpinner2DichVu = Data.connectorTelServiceType();
        setData();
    }

    @Override
    public void unSubscribe() {
        subscriptions.clear();
    }

    public void onClose() {
        view.onClose();
    }

    public void onConnectSubscriber() {
        clickSendData();
    }

    public void clickSendData() {
        view.showLoading();

        DataRequest<ConnectSubscriberRequest> request = new DataRequest<>();
        request.setWsCode(WsCode.ConnectSubscriber);
        request.setWsRequest(data);
        Subscription subscription = qlKhachHangRepository.connectSubscriber(request)
                .subscribe(new MBCCSSubscribe<ConnectSubscriberResponse>() {
                    @Override
                    public void onSuccess(ConnectSubscriberResponse object) {
                        dataImage = DatabaseUtils.getBitmapAndSaveDatabase(object.getNameImage(),
                                imageFront, imageBackside, imagePortrait);
                        view.connectSubscriberSuccess();
                    }

                    @Override
                    public void onError(BaseException error) {
                        view.connectSubscriberError(error);
                    }

                    @Override
                    public void onRequestFinish() {
                        super.onRequestFinish();
                        view.hideLoading();
                    }
                });
        subscriptions.add(subscription);
    }

    public void clickSendImage(boolean isSend) {
        if (isSend) {
            Intent intent = new Intent(context, UploadImageService.class);
            intent.putStringArrayListExtra(UploadImageService.ARG_DATA_INTENT,
                    (ArrayList<String>) dataImage);
            context.startService(intent);
        }
    }


    private void setData() {
        for (Data.DataField d : dataSpinner2DichVu) {
            if (d.getId() == data.getSubscriber().getTelecomServiceId()) {
                setDichVu(d.getName());
            }
            break;
        }
        setNameCustomer(data.getCustomer().getName());
        setNameStaff(userInfo.getStaffInfo().getStaffName());
        setPhoneNumberCustomer(data.getSubscriber().getIsdn());
        setPhoneNumberStaff(userInfo.getStaffInfo().getTel());
        setSumMoney("");
    }

    @Bindable
    public String getDichVu() {
        return dichVu;
    }

    public void setDichVu(String dichVu) {
        this.dichVu = dichVu;
        notifyPropertyChanged(BR.dichVu);
    }

    @Bindable
    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
        notifyPropertyChanged(BR.nameCustomer);
    }

    @Bindable
    public String getPhoneNumberCustomer() {
        return phoneNumberCustomer;
    }

    public void setPhoneNumberCustomer(String phoneNumberCustomer) {
        this.phoneNumberCustomer = phoneNumberCustomer;
        notifyPropertyChanged(BR.phoneNumberCustomer);
    }

    @Bindable
    public String getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(String sumMoney) {
        this.sumMoney = sumMoney;
        notifyPropertyChanged(BR.sumMoney);
    }

    @Bindable
    public String getNameStaff() {
        return nameStaff;
    }

    public void setNameStaff(String nameStaff) {
        this.nameStaff = nameStaff;
        notifyPropertyChanged(BR.nameStaff);
    }

    @Bindable
    public String getPhoneNumberStaff() {
        return phoneNumberStaff;
    }

    public void setPhoneNumberStaff(String phoneNumberStaff) {
        this.phoneNumberStaff = phoneNumberStaff;
        notifyPropertyChanged(BR.phoneNumberStaff);
    }
}
