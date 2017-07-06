package com.viettel.mbccs.screen.billing.adapter;

import android.content.Context;
import android.databinding.generated.callback.OnCheckedChangeListener;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseViewHolder;
import com.viettel.mbccs.base.GenericRecycleAdapter;
import com.viettel.mbccs.data.model.BillingModel;
import com.viettel.mbccs.data.model.SaleTrans;
import com.viettel.mbccs.data.source.UserRepository;

import java.util.List;

import butterknife.BindView;

public class BillingAdapter extends GenericRecycleAdapter<SaleTrans, BillingAdapter.BillingViewHolder> {

    private String mPhone;

    public BillingAdapter(List<SaleTrans> mList, Context context) {
        super(mList, context);
        mPhone = UserRepository.getInstance().getUserInfo().getStaffInfo().getTel();
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
    public void onItem(SaleTrans item, int position) {
        if (mOnClickItemRecycleView != null) {
            mOnClickItemRecycleView.onClickItem(item, position);
        }
    }

    @Override
    public void onSet(final SaleTrans item, BillingViewHolder holder, int position) {
        System.out.println("BillingModel" + item);
        if (item != null) {
            holder.mTrans.setText(mPhone);
            holder.mTotal.setText("Tổng tiền:" + item.getAmountTax());
            holder.mDate.setText("Nạp/ chuyển anypay");
            holder.mCheckItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                             @Override
                                                             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                                 item.setCheck(isChecked);
                                                             }
                                                         }
            );
            holder.mCheckItem.setChecked(item.isCheck());
        }
    }

    public class BillingViewHolder extends BaseViewHolder {

        @BindView(R.id.tv_trans)
        TextView mTrans;
        @BindView(R.id.tv_total)
        TextView mTotal;
        @BindView(R.id.tv_date)
        TextView mDate;
        @BindView(R.id.cb_item)
        CheckBox mCheckItem;


        public BillingViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
