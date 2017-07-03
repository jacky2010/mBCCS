package com.viettel.mbccs.screen.kpp.order.adaper;

import android.content.Context;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.OrderStatus;
import com.viettel.mbccs.data.model.SaleOrders;
import com.viettel.mbccs.utils.Common;

/**
 * Created by eo_cuong on 5/21/17.
 */

public class ItemOrderSalePresenter {

    public Context mContext;
    private SaleOrders mSaleOrders;
    private String status;

    public ItemOrderSalePresenter(Context context, SaleOrders saleOrders) {
        mContext = context;
        mSaleOrders = saleOrders;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public SaleOrders getSaleOrders() {
        return mSaleOrders;
    }

    public String getAmount() {
        return String.format(mContext.getString(R.string.kpp_order_label_amount),
                Common.formatDouble(mSaleOrders.getAmount()));
    }

    public void setSaleOrders(SaleOrders saleOrders) {
        mSaleOrders = saleOrders;
    }

    public String getStatus() {
        if (mSaleOrders.getOrderStatus().equals(OrderStatus.APPROVALS)) {
            return mContext.getResources().getString(R.string.order_approval);
        } else if (mSaleOrders.getOrderStatus().equals(OrderStatus.PENDING)) {
            return mContext.getResources().getString(R.string.order_pending);
        } else {
            return mContext.getResources().getString(R.string.order_reject);
        }
    }

    public boolean isShowAmount() {
        if (mSaleOrders.getOrderStatus().equals(OrderStatus.APPROVALS)) {
            return true;
        }
        return false;
    }

    public int getColorText() {
        if (mSaleOrders.getOrderStatus().equals(OrderStatus.APPROVALS)) {
            return mContext.getResources().getColor(R.color.green);
        } else if (mSaleOrders.getOrderStatus().equals(OrderStatus.PENDING)) {
            return mContext.getResources().getColor(R.color.yellow);
        } else {
            return mContext.getResources().getColor(R.color.red);
        }
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
