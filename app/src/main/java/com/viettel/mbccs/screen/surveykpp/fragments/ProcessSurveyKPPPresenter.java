package com.viettel.mbccs.screen.surveykpp.fragments;

import android.content.Context;

import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.ApiCode;
import com.viettel.mbccs.data.source.SurveyKPPRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.SendSurveyKPPRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.SendSurveyKPPResponse;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import com.viettel.mbccs.variable.Constants;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by minhnx on 5/19/17.
 */

public class ProcessSurveyKPPPresenter implements ProcessSurveyKPPContract.Presenter {

    private Context context;
    private ProcessSurveyKPPContract.ViewModel viewModel;
    private SurveyKPPRepository surveyKPPRepository;
    private DataRequest<SendSurveyKPPRequest> baseRequest;
    private CompositeSubscription mSubscriptions;

    public ProcessSurveyKPPPresenter(Context context, final ProcessSurveyKPPContract.ViewModel viewModel) {
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
            surveyKPPRepository = SurveyKPPRepository.getInstance();
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
    public void onSurveyCompleted(String answersJson) {

        try {
            //TODO fill results
            viewModel.showLoading();

            baseRequest = new DataRequest<>();
            baseRequest.setApiCode(ApiCode.SendSurveyKPP);
            SendSurveyKPPRequest request = new SendSurveyKPPRequest();
            baseRequest.setParameterApi(request);

            Subscription subscription =
                    surveyKPPRepository.sendSurveyKPP(baseRequest)
                            .subscribe(new MBCCSSubscribe<SendSurveyKPPResponse>() {
                                @Override
                                public void onSuccess(SendSurveyKPPResponse object) {
                                    try {
                                        if (Constants.Service.RESPONSE_OK.equals(object.getErrorCode())) {
                                            viewModel.onBackPressed();
                                        } else {
                                            DialogUtils.showDialogError(context, null, context.getString(R.string.common_msg_error_dont_have_permission),
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
            viewModel.hideLoading();
        }
    }

    @Override
    public void onSurveyTerminated() {
        viewModel.onBackPressed();
    }
}
