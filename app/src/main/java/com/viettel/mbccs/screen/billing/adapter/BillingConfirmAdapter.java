package com.viettel.mbccs.screen.billing.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseViewHolder;
import com.viettel.mbccs.base.GenericRecycleAdapter;
import com.viettel.mbccs.data.model.BillingModel;

import java.util.List;
import butterknife.BindView;

public class BillingConfirmAdapter extends GenericRecycleAdapter<BillingModel, BillingConfirmAdapter.BillingConfirmViewHolder> {

    public BillingConfirmAdapter(List<BillingModel> mList, Context context) {
        super(mList, context);
    }

    @Override
    public int getLayout(int typeView) {
        return R.layout.item_confirm_billing;
    }

    @Override
    protected RecyclerView.ViewHolder getHolderViewHolder(View v, int typeView) {
        return new BillingConfirmViewHolder(v) {
            @Override
            public void onClick(View view) {
                super.onClick(view);
                mSelectedItem = this.getAdapterPosition();
                onItem(mList.get(this.getAdapterPosition()), this.getAdapterPosition());
            }
        };
    }

    @Override
    public void onItem(BillingModel appInfo, int position) {
        if (mOnClickItemRecycleView != null) {
            mOnClickItemRecycleView.onClickItem(appInfo,position);
        }
    }

    @Override
    public void onSet(BillingModel item, BillingConfirmViewHolder holder, int position) {
        if (item != null) {
            holder.mTrans.setText(String.valueOf(item.getSaleTransId()));
            holder.mTotalAmountPayment.setText(String.format(mContext.getString(R.string.msg_total_amount_payable),item.getAmountTax()));
            holder.mDiscount.setText(String.format(mContext.getString(R.string.msg_discount),item.getAmountTax()));
            holder.mBeforeTax.setText(String.format(mContext.getString(R.string.msg_before_tax),item.getAmountTax()));
            holder.mRent.setText(String.format(mContext.getString(R.string.msg_rent),item.getAmountTax()));
        }
    }

    public class BillingConfirmViewHolder extends BaseViewHolder {

        @BindView(R.id.tv_trans) TextView mTrans;
        @BindView(R.id.tv_total_amount_payable)  TextView mTotalAmountPayment;
        @BindView(R.id.tv_discount)  TextView mDiscount;
        @BindView(R.id.tv_before_tax)  TextView mBeforeTax;
        @BindView(R.id.tv_rent)  TextView mRent;

        public BillingConfirmViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
