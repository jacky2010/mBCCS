package com.viettel.mbccs.screen.kppfeedback.fragments;

import android.content.Context;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.text.TextUtils;

import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.ApiCode;
import com.viettel.mbccs.data.model.KPPFeedback;
import com.viettel.mbccs.data.source.KPPFeedbackRepository;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetKPPFeedbackInfoRequest;
import com.viettel.mbccs.data.source.remote.request.KPPRespondFeedbackRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetKPPFeedbackInfoResponse;
import com.viettel.mbccs.data.source.remote.response.KPPRespondFeedbackResponse;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import com.viettel.mbccs.variable.Constants;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by minhnx on 5/19/17.
 */

public class RespondKPPFeedbackPresenter implements RespondKPPFeedbackContract.Presenter {

    private Context context;
    private RespondKPPFeedbackContract.ViewModel viewModel;

    public ObservableField<String> feedback;
    public ObservableField<String> response;
    public ObservableField<String> responseError;

    private KPPFeedbackRepository kppFeedbackRepository;
    private UserRepository userRepository;
    private CompositeSubscription mSubscriptions;
    private KPPFeedback feedbackItem;

    public RespondKPPFeedbackPresenter(Context context, final RespondKPPFeedbackContract.ViewModel viewModel) {
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

            kppFeedbackRepository = KPPFeedbackRepository.getInstance();
            userRepository = UserRepository.getInstance();
            mSubscriptions = new CompositeSubscription();

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
    public void fillFeedbackContent(final KPPFeedback item) {
        try {
            viewModel.showLoading();
            feedbackItem = item;

            DataRequest<GetKPPFeedbackInfoRequest> baseRequest = new DataRequest<>();
            baseRequest.setApiCode(ApiCode.GetKppFeedbackInfo);
            GetKPPFeedbackInfoRequest request = new GetKPPFeedbackInfoRequest();
            request.setUsername(userRepository.getUser() != null ? userRepository.getUser().getUserName() : null);
            request.setLanguage(userRepository.getLanguageFromSharePrefs());
            request.setFeedbackId(item.getId());
            baseRequest.setParameterApi(request);

            Subscription subscription =
                    kppFeedbackRepository.getFeedbackInfo(baseRequest)
                            .subscribe(new MBCCSSubscribe<GetKPPFeedbackInfoResponse>() {
                                @Override
                                public void onSuccess(GetKPPFeedbackInfoResponse object) {
                                    try {
//                                        if (Constants.Service.RESPONSE_OK.equals(object.getErrorCode())) {

                                        item.setContent(object.getContent());

                                        feedback.set(item.getContent());
//                                        } else {
//                                            DialogUtils.showDialogError(context, null, context.getString(R.string.common_msg_error_general),
//                                                    null);
//                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onError(BaseException error) {
//                                    DialogUtils.showDialogError(context, null, error.getMessage(),
//                                            null);
                                    DialogUtils.showDialogError(context, null, context.getString(R.string.common_msg_error_general),
                                            null);
                                }

                                @Override
                                public void onRequestFinish() {
                                    super.onRequestFinish();
                                    viewModel.hideLoading();
                                }
                            });

            mSubscriptions.add(subscription);

        } catch (Exception e) {
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

            viewModel.showLoading();

            DataRequest<KPPRespondFeedbackRequest> baseRequest = new DataRequest<>();
            baseRequest.setApiCode(ApiCode.SendFeedbackKPP);
            KPPRespondFeedbackRequest request = new KPPRespondFeedbackRequest();

            request.setUsername(userRepository.getUser() != null ? userRepository.getUser().getUserName() : null);
            request.setLanguage(userRepository.getLanguageFromSharePrefs());
            request.setFeedbackId(feedbackItem.getId());
            request.setContent(response.get());
            baseRequest.setParameterApi(request);

            Subscription subscription =
                    kppFeedbackRepository.responseFeedback(baseRequest)
                            .subscribe(new MBCCSSubscribe<KPPRespondFeedbackResponse>() {
                                @Override
                                public void onSuccess(KPPRespondFeedbackResponse object) {
                                    try {
//                                        if (Constants.Service.RESPONSE_OK.equals(object.getErrorCode())) {

                                        Bundle args = new Bundle();
                                        args.putString(Constants.BundleConstant.MESSAGE, context.getString(R.string.kpp_feedback_msg_info_sent_response_feedback));

                                        viewModel.goToSuccessDialog(args);
//                                        } else {
//                                        DialogUtils.showDialogError(context, null, context.getString(R.string.change_sim_error_recent_calls_not_valid),
//                                                null);
//                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onError(BaseException error) {
//                                    DialogUtils.showDialogError(context, null, error.getMessage(),
//                                            null);
                                    DialogUtils.showDialogError(context, null, context.getString(R.string.common_msg_error_general),
                                            null);
                                }

                                @Override
                                public void onRequestFinish() {
                                    super.onRequestFinish();
                                    viewModel.hideLoading();
                                }
                            });

            mSubscriptions.add(subscription);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        viewModel.onBackPressed();
    }
}
