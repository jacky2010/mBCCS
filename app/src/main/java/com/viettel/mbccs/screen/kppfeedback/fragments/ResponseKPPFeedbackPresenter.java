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

public class ResponseKPPFeedbackPresenter implements ResponseKPPFeedbackContract.Presenter {

    private Context context;
    private ResponseKPPFeedbackContract.ViewModel viewModel;

    public ObservableField<String> feedback;
    public ObservableField<String> response;
    public ObservableField<String> responseError;

    public ResponseKPPFeedbackPresenter(Context context, final ResponseKPPFeedbackContract.ViewModel viewModel) {
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

            feedback = new ObservableField<>();
            response = new ObservableField<>();
            responseError = new ObservableField<>();



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

    @Override
    public void fillFeedbackContent(KPPFeedback item) {
        try{
            feedback.set(item.getContent());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void sendResponse() {
        try {

            boolean isValid = true;

            responseError.set(null);

            if (TextUtils.isEmpty(response.get())) {
                viewModel.showError(context.getString(R.string.common_msg_error_required_field, context.getString(R.string.kpp_feedback_label_response_feedback)));
                isValid = false;
            }

            if (!isValid)
                return;

            KPPFeedback item = new KPPFeedback();

            Bundle args = new Bundle();
            args.putString(Constants.BundleConstant.MESSAGE, context.getString(R.string.kpp_feedback_msg_info_sent_response_feedback));

            viewModel.goToSuccessDialog(args);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close(){
        viewModel.onBackPressed();
    }
}
