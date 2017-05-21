package com.viettel.mbccs.screen.kpp.order.adaper;

import android.content.Context;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.OrderStatus;
import com.viettel.mbccs.data.model.SaleOrders;

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

    public void setSaleOrders(SaleOrders saleOrders) {
        mSaleOrders = saleOrders;
    }

    public String getStatus() {
        switch ((int) mSaleOrders.getOrderStatus()) {
            case OrderStatus.APPROVALS:
                return mContext.getResources().getString(R.string.order_approval);
            case OrderStatus.REJECT:
                return mContext.getResources().getString(R.string.order_reject);
            case OrderStatus.PENDING:
                return mContext.getResources().getString(R.string.order_pending);
        }
        return "";
    }

    public int getColorText() {
        switch ((int) mSaleOrders.getOrderStatus()) {
            case OrderStatus.APPROVALS:
                return mContext.getResources().getColor(R.color.green);
            case OrderStatus.REJECT:
                return mContext.getResources().getColor(R.color.red);
            case OrderStatus.PENDING:
                return mContext.getResources().getColor(R.color.yellow);
            default:
                return mContext.getResources().getColor(R.color.black);
        }
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
