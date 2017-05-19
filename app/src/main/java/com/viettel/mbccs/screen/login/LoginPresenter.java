package com.viettel.mbccs.screen.login;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Toast;
import com.viettel.mbccs.R;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class LoginPresenter implements LoginContract.Presenter {

    public static final int TYPE_ERROR_USERNAME = 0;
    public static final int TYPE_ERROR_PASSWORD = 1;
    public static final String CUSTOMER_SERVICE_PHONE = "+84 XXXXXXXXXX";

    public ObservableField<String> userName;
    public ObservableField<String> password;
    public ObservableBoolean loading;

    private LoginContract.ViewModel mViewModel;
    private Context mContext;

    public LoginPresenter(Context context, LoginContract.ViewModel viewModel) {
        mViewModel = viewModel;
        mContext = context;
        initData();
    }

    private void initData() {
        userName = new ObservableField<>();
        password = new ObservableField<>();
        loading = new ObservableBoolean();
        loading.set(false);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void login(String username, String password) {
        mViewModel.onLoginSuccess();
        //        LoginRequest loginRequest = new LoginRequest();
        //        loginRequest.setUsername(username);
        //        loginRequest.setPassword(password);
        //        UserRepository.getInstance()
        //                .login(loginRequest)
        //                .subscribe(new MBCCSSubscribe<LoginResponse>() {
        //                    @Override
        //                    public void onSuccess(LoginResponse object) {
        //                        mViewModel.onLoginSuccess();
        //                    }
        //
        //                    @Override
        //                    public void onError(BaseException error) {
        //                        Toast.makeText(mContext, "Login fail", Toast.LENGTH_SHORT).show();
        //                        //TODO
        //                    }
        //                });
    }

    public void login() {
        // TODO: Validate
        loading.set(true);
        if (TextUtils.isEmpty(userName.get())) {
            mViewModel.showError(TYPE_ERROR_USERNAME,
                    mContext.getString(R.string.phone_number_cannot_be_empty));
            loading.set(false);
            return;
        }
        if (TextUtils.isEmpty(password.get())) {
            mViewModel.showError(TYPE_ERROR_PASSWORD,
                    mContext.getString(R.string.password_cannot_be_empty));
            loading.set(false);
            return;
        }
        login(userName.get(), password.get());
    }

    public Spannable getForgotPassword() {
        SpannableStringBuilder content =
                new SpannableStringBuilder(mContext.getString(R.string.forgot_password));
        content.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                mViewModel.onForgotPassword(userName.get());
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    ds.setColor(mContext.getResources()
                            .getColor(android.R.color.holo_blue_light, null));
                } else {
                    ds.setColor(mContext.getResources().getColor(android.R.color.holo_blue_light));
                }
            }
        }, 0, content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return content;
    }

    public MovementMethod getMovementMethod() {
        return LinkMovementMethod.getInstance();
    }

    public Spannable getCallText() {
        SpannableStringBuilder content = new SpannableStringBuilder(
                mContext.getString(R.string.customer_service, CUSTOMER_SERVICE_PHONE));
        content.setSpan(new ClickableSpan() {
                            @Override
                            public void onClick(View widget) {
                                // TODO: Call phone
                                Toast.makeText(mContext, "Call to " + CUSTOMER_SERVICE_PHONE, Toast.LENGTH_SHORT)
                                        .show();
                            }

                            @Override
                            public void updateDrawState(TextPaint ds) {
                                super.updateDrawState(ds);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    ds.setColor(mContext.getResources()
                                            .getColor(android.R.color.holo_blue_light, null));
                                } else {
                                    ds.setColor(mContext.getResources().getColor(android.R.color.holo_blue_light));
                                }
                            }
                        }, content.length() - CUSTOMER_SERVICE_PHONE.length(), content.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return content;
    }
}
