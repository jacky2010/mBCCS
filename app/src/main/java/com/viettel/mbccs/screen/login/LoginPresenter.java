package com.viettel.mbccs.screen.login;

import android.content.Context;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.widget.Toast;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.LoginRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.LoginResponse;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class LoginPresenter implements LoginContract.Presenter {

    public ObservableField<String> userName;
    public ObservableField<String> password;

    private LoginContract.ViewModel mViewModel;
    private Context mContext;

    public LoginPresenter(Context context, LoginContract.ViewModel viewModel) {
        mViewModel = viewModel;
        mContext = context;
        initdata();
    }

    private void initdata() {
        userName = new ObservableField<>();
        password = new ObservableField<>();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void login(String username, String password) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);
        UserRepository.getInstance()
                .login(loginRequest)
                .subscribe(new MBCCSSubscribe<LoginResponse>() {
                    @Override
                    public void onSuccess(LoginResponse object) {
                        mViewModel.onLoginSuccess();
                    }

                    @Override
                    public void onError(BaseException error) {
                        Toast.makeText(mContext, "Login fail", Toast.LENGTH_SHORT).show();
                        //TODO
                    }
                });
    }

    public void login() {
        if (!TextUtils.isEmpty(userName.get()) && !TextUtils.isEmpty(password.get())) {
            login(userName.get(), password.get());
        }
    }
}
