package com.viettel.mbccs.screen.kppfeedback.fragments;

import android.content.Context;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.text.TextUtils;

import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.ApiCode;
import com.viettel.mbccs.data.source.KPPFeedbackRepository;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.KPPFeedbackRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.KPPFeedbackResponse;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import com.viettel.mbccs.variable.Constants;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by minhnx on 5/19/17.
 */

public class SendKPPFeedbackPresenter implements SendKPPFeedbackContract.Presenter {

    private Context context;
    private SendKPPFeedbackContract.ViewModel viewModel;

    public ObservableField<String> content;
    public ObservableField<String> contentError;

    private KPPFeedbackRepository kppFeedbackRepository;
    private UserRepository userRepository;
    private CompositeSubscription mSubscriptions;

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

            viewModel.showLoading();

            DataRequest<KPPFeedbackRequest> baseRequest = new DataRequest<>();
            baseRequest.setApiCode(ApiCode.CreateFeedback);
            KPPFeedbackRequest request = new KPPFeedbackRequest();
            request.setUsername(userRepository.getUser() != null ? userRepository.getUser().getUserName() : null);
            request.setLanguage(userRepository.getLanguageFromSharePrefs());
            request.setDescription(context.getString(R.string.kpp_feedback_label_title, userRepository.getUser() != null ? userRepository.getUser().getUserName() : null));
            request.setContent(content.get());
            request.setOwnerCode((userRepository.getUserInfo() != null && userRepository.getUserInfo().getChannelInfo() != null) ? userRepository.getUserInfo().getChannelInfo().getChannelCode() : null);
            baseRequest.setParameterApi(request);

            Subscription subscription =
                    kppFeedbackRepository.feedback(baseRequest)
                            .subscribe(new MBCCSSubscribe<KPPFeedbackResponse>() {
                                @Override
                                public void onSuccess(KPPFeedbackResponse object) {
                                    try {
                                        if (Constants.Service.RESPONSE_OK.equals(object.getErrorCode())) {

                                            Bundle args = new Bundle();
                                            args.putString(Constants.BundleConstant.MESSAGE, context.getString(R.string.kpp_feedback_msg_info_sent_successful));

                                            viewModel.goToSuccessDialog(args);
                                        } else {
                                            DialogUtils.showDialogError(context, null, context.getString(R.string.change_sim_error_recent_calls_not_valid),
                                                    null);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onError(BaseException error) {
                                    DialogUtils.showDialogError(context, null, error.getMessage(),
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
}
