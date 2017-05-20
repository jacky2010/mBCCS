package com.viettel.mbccs.callback;

/**
 * Created by jacky on 5/19/17.
 */

public interface OnListenerItemRecyclerView<T> {
    void onClickItem(T t, int position);
}
