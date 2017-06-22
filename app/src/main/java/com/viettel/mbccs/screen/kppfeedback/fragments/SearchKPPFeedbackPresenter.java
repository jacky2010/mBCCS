package com.viettel.mbccs.screen.kppfeedback.fragments;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;

import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.ApiCode;
import com.viettel.mbccs.data.model.KPPFeedback;
import com.viettel.mbccs.data.source.KPPFeedbackRepository;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetKPPFeedbackRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetKPPFeedbackResponse;
import com.viettel.mbccs.screen.kppfeedback.adapters.KPPFeedbackListAdapter;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import com.viettel.mbccs.variable.Constants;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by minhnx on 5/19/17.
 */

public class SearchKPPFeedbackPresenter implements SearchKPPFeedbackContract.Presenter {

    private Context context;
    private SearchKPPFeedbackContract.ViewModel viewModel;
    private KPPFeedbackListAdapter feedbackAdapter;

    public ObservableBoolean searchFound;
    public ObservableField<KPPFeedbackListAdapter> feedbacksListAdapter;
    private KPPFeedbackRepository feedbackRepository;
    private UserRepository userRepository;
    private CompositeSubscription mSubscriptions;

    public SearchKPPFeedbackPresenter(Context context, final SearchKPPFeedbackContract.ViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;

        feedbacksListAdapter = new ObservableField<>();
        searchFound = new ObservableBoolean(true);
        feedbackAdapter = new KPPFeedbackListAdapter(context, new ArrayList<KPPFeedback>());
        feedbackAdapter.setOnItemClickListener(new KPPFeedbackListAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, KPPFeedback item) {
                viewModel.openFeedbackDetail(item);
            }
        });

        initListeners();
        initData();
        searchFeedbacks();
    }

    private void initListeners() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initData() {
        feedbackRepository = KPPFeedbackRepository.getInstance();
        mSubscriptions = new CompositeSubscription();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void searchFeedbacks() {
        try {

            final List<KPPFeedback> items = new ArrayList<>();
            viewModel.showLoading();

            DataRequest<GetKPPFeedbackRequest> baseRequest = new DataRequest<>();
            baseRequest.setApiCode(ApiCode.GetKppFeedback);
            GetKPPFeedbackRequest request = new GetKPPFeedbackRequest();
            request.setUsername(userRepository.getUser() != null ? userRepository.getUser().getUserName() : null);
            request.setLanguage(userRepository.getLanguageFromSharePrefs());
            request.setOwnerCode((userRepository.getUserInfo() != null && userRepository.getUserInfo().getChannelInfo() != null) ? userRepository.getUserInfo().getChannelInfo().getChannelCode() : null);
            baseRequest.setParameterApi(request);

            Subscription subscription =
                    feedbackRepository.getFeedback(baseRequest)
                            .subscribe(new MBCCSSubscribe<GetKPPFeedbackResponse>() {
                                @Override
                                public void onSuccess(GetKPPFeedbackResponse object) {
                                    try {
                                        if (Constants.Service.RESPONSE_OK.equals(object.getErrorCode())) {

                                            items.addAll(object.getListFeedback());

                                            feedbackAdapter.setFeedbackItems(items);

                                            feedbacksListAdapter.set(feedbackAdapter);

                                            if (feedbackAdapter.getItemCount() > 0) {
                                                searchFound.set(true);
                                            } else {
                                                searchFound.set(false);
                                            }

                                            viewModel.onSearchCompleted();
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
