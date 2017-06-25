package com.viettel.mbccs.screen.change.installation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseViewHolder;
import com.viettel.mbccs.base.GenericRecycleAdapter;
import com.viettel.mbccs.data.model.Customer;

import java.util.List;

import butterknife.BindView;

public class InstallationAddressAdapter extends GenericRecycleAdapter<Customer,
        InstallationAddressAdapter.InstallationAddressViewHolder> {

    public InstallationAddressAdapter(List<Customer> mList, Context context) {
        super(mList, context);
    }

    @Override
    public int getLayout(int typeView) {
        return R.layout.item_info_customer;
    }

    @Override
    protected RecyclerView.ViewHolder getHolderViewHolder(View v, int typeView) {
        return new InstallationAddressViewHolder(v) {
            @Override
            public void onClick(View view) {
                super.onClick(view);
                mSelectedItem = this.getAdapterPosition();
                onItem(mList.get(this.getAdapterPosition()), this.getAdapterPosition());
            }
        };
    }

    @Override
    public void onItem(Customer appInfo, int position) {
        if (mOnClickItemRecycleView != null) {
            mOnClickItemRecycleView.onClickItem(appInfo, position);
        }
    }

    @Override
    public void onSet(Customer mCustomer, InstallationAddressViewHolder holder, int position) {
        if (mCustomer != null) {
            holder.mNameCustomer.setText(mCustomer.getName());
            holder.mPhoneNumberCustomer.setText(mCustomer.getIdNo());
            holder.mService.setText(mCustomer.getName());
        }
    }

    public class InstallationAddressViewHolder extends BaseViewHolder {

        @BindView(R.id.tv_name_customer) TextView mNameCustomer;
        @BindView(R.id.tv_phone_number) TextView mPhoneNumberCustomer;
        @BindView(R.id.tv_service) TextView mService;

        public InstallationAddressViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
