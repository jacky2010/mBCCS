package com.viettel.mbccs.screen.resetpass;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Handler;
import android.util.Log;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.EmptyObject;
import com.viettel.mbccs.data.model.UserInfo;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.PassResetRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.PassResetResponse;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.StringUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by FRAMGIA\bui.dinh.viet on 16/05/2017.
 */

public class ResetPasswordPresenter implements ResetPasswordContract.Presenter {
    private ResetPasswordContract.ViewModel mViewModel;
    private Context mContext;
    public ObservableField<String> description;
    public ObservableBoolean isCodeSent;
    public ObservableBoolean isNeedVerify;
    private CompositeSubscription mSubscription;
    public ObservableField<String> phone;
    public ObservableField<String> password;
    public ObservableField<String> codeVerify;
    public ObservableField<String> titleActivity;
    public ObservableBoolean isPassStateHide;
    private PassResetRequest mPassResetRequest;
    private UserInfo mUserInfo;

    public ResetPasswordPresenter(ResetPasswordContract.ViewModel viewModel, Context context) {
        mViewModel = viewModel;
        mContext = context;
        initFields();
    }

    private void initFields() {
        mSubscription = new CompositeSubscription();
        description = new ObservableField<>();
        codeVerify = new ObservableField<>();
        titleActivity = new ObservableField<>();
        isCodeSent = new ObservableBoolean();
        isPassStateHide = new ObservableBoolean();
        isNeedVerify = new ObservableBoolean();
        phone = new ObservableField<>();
        password = new ObservableField<>();
        description.set(mContext.getString(R.string.description_reset_password));
        titleActivity.set(mContext.getString(R.string.title_reset_password));
        isPassStateHide.set(true);
        mPassResetRequest = new PassResetRequest();
        mUserInfo = UserRepository.getInstance().getUserInfo();
    }

    @Override
    public void subscribe() {
    }

    @Override
    public void unSubscribe() {
        mSubscription.clear();
    }

    @Override
    public void sendCodeClick() {

        Subscription subscription = UserRepository.getInstance()
                .sendCodeChangePass(phone.get())
                .subscribe(new MBCCSSubscribe<Object>() {
                    @Override
                    public void onSuccess(Object object) {
                        isCodeSent.set(true);
                        titleActivity.set(mContext.getString(R.string.title_input_password));
                        description.set(String.format(mContext.getString(R.string.we_sent_code),
                                StringUtils.changeTextToBold(phone.get())));
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                isNeedVerify.set(true);
                            }
                        }, 3000);
                    }

                    @Override
                    public void onError(BaseException error) {
                        //TODO: handle when have error
                        // This is demo flow spec because we dont have api ready.
                        //It was be deleted if apply api
                        isCodeSent.set(true);
                        description.set(String.format(mContext.getString(R.string.we_sent_code),
                                StringUtils.changeTextToBold(phone.get())));
                        titleActivity.set(mContext.getString(R.string.title_input_password));
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                isNeedVerify.set(true);
                            }
                        }, 3000);
                    }
                });
        mSubscription.add(subscription);
    }

    @Override
    public void changePassword(String password) {

    }

    @Override
    public void createNewPass() {
        mViewModel.showLoading();
        mPassResetRequest.setPassnew(this.password.get());
        mPassResetRequest.setPassold(codeVerify.get());
        mPassResetRequest.setUsername(mUserInfo.getStaffInfo().getStaffName());
        Subscription subscription = UserRepository.getInstance()
                .resetPassword(mPassResetRequest)
                .subscribe(new MBCCSSubscribe<EmptyObject>() {
                    @Override
                    public void onSuccess(EmptyObject object) {
                        Log.i("ResetPasswordPresenter", "onSuccess: ---------> ");
                        mViewModel.onBackClick();
                    }

                    @Override
                    public void onError(BaseException error) {
                        DialogUtils.showDialogError(mContext, null, error.getMessage(), null);
                    }

                    @Override
                    public void onRequestFinish() {
                        super.onRequestFinish();
                        mViewModel.hideLoading();
                    }
                });
        mSubscription.add(subscription);
    }

    public void onBackClick() {
        mViewModel.onBackClick();
    }

    public void clearInputedPass() {
        password.set("");
    }

    public void eyeClick() {
        isPassStateHide.set(!isPassStateHide.get());
    }
}
