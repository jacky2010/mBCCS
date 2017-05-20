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

public class BillingAdapter extends GenericRecycleAdapter<BillingModel, BillingAdapter.BillingViewHolder> {

    public BillingAdapter(List<BillingModel> mList, Context context) {
        super(mList, context);
    }

    @Override
    public int getLayout(int typeView) {
        return R.layout.item_billing;
    }

    @Override
    protected RecyclerView.ViewHolder getHolderViewHolder(View v, int typeView) {
        return new BillingViewHolder(v) {
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
    public void onSet(BillingModel item, BillingViewHolder holder, int position) {
        System.out.println("BillingModel" + item);
        if (item != null) {
            holder.mTrans.setText(String.valueOf(item.getSaleTransId()));
            holder.mTotal.setText("cxcxcx");
            holder.mDate.setText("cxcxcx");

        }
    }

    public class BillingViewHolder extends BaseViewHolder {

        @BindView(R.id.tv_trans) TextView mTrans;
        @BindView(R.id.tv_total)  TextView mTotal;
        @BindView(R.id.tv_date)  TextView mDate;

        public BillingViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
