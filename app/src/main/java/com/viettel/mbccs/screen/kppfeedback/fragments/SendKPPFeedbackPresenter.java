package com.viettel.mbccs.screen.kppfeedback.fragments;

import android.content.Context;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.text.TextUtils;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.KPPFeedback;
import com.viettel.mbccs.variable.Constants;

/**
 * Created by minhnx on 5/19/17.
 */

public class SendKPPFeedbackPresenter implements SendKPPFeedbackContract.Presenter {

    private Context context;
    private SendKPPFeedbackContract.ViewModel viewModel;

    public ObservableField<String> content;
    public ObservableField<String> contentError;

    public SendKPPFeedbackPresenter(Context context, final SendKPPFeedbackContract.ViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;

        initListeners();
        initData();
    }

    private void initListeners() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initData() {
        try {

            content = new ObservableField<>();
            contentError = new ObservableField<>();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    public void sendFeedback() {
        try {

            boolean isValid = true;

            contentError.set(null);

            if (TextUtils.isEmpty(content.get())) {
                viewModel.showError(context.getString(R.string.common_msg_error_required_field, context.getString(R.string.kpp_feedback_label_content_desc2)));
                isValid = false;
            }

            if (!isValid)
                return;

            KPPFeedback item = new KPPFeedback();

            Bundle args = new Bundle();
            args.putString(Constants.BundleConstant.MESSAGE, context.getString(R.string.kpp_feedback_msg_info_sent_successful));

            viewModel.goToSuccessDialog(args);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
