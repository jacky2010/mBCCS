package com.viettel.mbccs.base.createorder;

import android.content.Context;
import android.databinding.ObservableField;

/**
 * Created by Anh Vu Viet on 6/26/2017.
 */

public abstract class BaseCreateOrderPresenter<T> extends BaseCreateOrderSuccessPresenter<T>
        implements BaseCreateOrderContract.Presenter {

    public ObservableField<String> buttonTitle = new ObservableField<>();

    public ObservableField<Boolean> showRejectButton = new ObservableField<>(false);

    public BaseCreateOrderPresenter(Context context, BaseCreateOrderContract.ViewModel viewModel) {
        super(context, viewModel);
    }

    public void setButtonTitle(String st) {
        buttonTitle.set(st);
    }

    public void setShowRejectButton(boolean isShow) {
        showRejectButton.set(isShow);
    }

    public abstract void reject();

    public abstract void create();

    public abstract void rejectCmd(String input);

    public abstract void approveCmd();

    public abstract boolean getShowButton();
}
