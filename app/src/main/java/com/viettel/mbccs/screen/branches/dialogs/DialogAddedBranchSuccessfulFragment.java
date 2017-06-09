package com.viettel.mbccs.screen.branches.dialogs;

import android.os.Bundle;
import android.widget.TextView;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDialog;
import com.viettel.mbccs.constance.IconType;
import com.viettel.mbccs.data.model.BranchItem;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.variable.Constants;
import com.viettel.mbccs.widget.CustomTextView;
import com.viettel.mbccs.widget.ToolBarView;

import butterknife.BindView;
import butterknife.OnClick;

public class DialogAddedBranchSuccessfulFragment extends BaseDialog {

    @BindView(R.id.toolbar)
    ToolBarView mToolBar;

    @BindView(R.id.txtMessageDetail)
    TextView txtMessageDetail;
    @BindView(R.id.tv_channel_type)
    CustomTextView tv_channel_type;
    @BindView(R.id.tv_phone_no)
    CustomTextView tv_phone_no;
    @BindView(R.id.tv_document_type)
    CustomTextView tv_document_type;
    @BindView(R.id.tv_address)
    CustomTextView tv_address;
    @BindView(R.id.tv_location)
    CustomTextView tv_location;
    @BindView(R.id.tv_manager)
    CustomTextView tv_manager;
    @BindView(R.id.tv_bts)
    CustomTextView tv_bts;

    @OnClick(R.id.biv_done)
    public void onDone() {
        dismiss();
    }

    @Override
    protected void initData() {
        try {

            Bundle args = getArguments();

            if (args != null) {
                txtMessageDetail.setText(getString(R.string.branches_msg_added_branch));

                BranchItem branchItem = GsonUtils.String2Object(args.getString(Constants.BundleConstant.ITEM_LIST), BranchItem.class);

                if(branchItem != null){
                    tv_address.setText(branchItem.getAddress());
                    tv_bts.setText(branchItem.getBtsName());
                    tv_channel_type.setText(branchItem.getChannelTypeName());
                    tv_document_type.setText(branchItem.getDocumentTypeName());
                    tv_location.setText(branchItem.getLatitude() + ", " + branchItem.getLongitude());
                    tv_manager.setText(branchItem.getStaffName());
                    tv_phone_no.setText(branchItem.getPhoneNo());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected int getStyleDialog() {
        return 0;
    }

    @Override
    protected void initView() {
        mToolBar.setOnClickIconListener(new ToolBarView.OnClickIconListener() {
            @Override
            public void onClickIconLeft(int mIconType) {
                switch (mIconType) {
                    case IconType.LEFT:
                        dismiss();
                        break;
                    case IconType.RIGHT:
                        break;
                }
            }
        });
    }

    @Override
    protected int idLayoutRes() {
        return R.layout.fragment_added_branch_success;
    }

    @Override
    public void onResume() {
        super.onResume();
        getBaseActivity().setTitleToolbar(R.string.billing_info);
    }
}
