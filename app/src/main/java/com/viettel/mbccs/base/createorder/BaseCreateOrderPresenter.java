package com.viettel.mbccs.base.createorder;

import android.content.Context;

/**
 * Created by Anh Vu Viet on 6/26/2017.
 */

public abstract class BaseCreateOrderPresenter<T> extends BaseCreateOrderSuccessPresenter<T>
        implements BaseCreateOrderContract.Presenter {

    public BaseCreateOrderPresenter(Context context, BaseCreateOrderContract.ViewModel viewModel) {
        super(context, viewModel);
    }

    public abstract void reject();

    public abstract void create();

    public abstract boolean getShowButton();
}
