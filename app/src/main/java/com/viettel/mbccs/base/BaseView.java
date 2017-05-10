package com.viettel.mbccs.base;

public interface BaseView<T> {

    void setPresenter(T t);

    void showLoading();

    void hideLoading();
}
