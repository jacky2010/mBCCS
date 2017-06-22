package com.viettel.mbccs.screen.hotnewscskpp.fragments;

import android.content.Context;
import android.databinding.ObservableField;

import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.ApiCode;
import com.viettel.mbccs.data.model.HotNewsCSKPPItem;
import com.viettel.mbccs.data.source.HotNewsCSKPPRepository;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetHotNewsInfoCSKPPRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetHotNewsInfoCSKPPResponse;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by minhnx on 5/19/17.
 */

public class ViewHotNewsCSKPPPresenter implements ViewHotNewsCSKPPContract.Presenter {

    private Context context;
    private ViewHotNewsCSKPPContract.ViewModel viewModel;

    public ObservableField<String> content;

    private HotNewsCSKPPRepository hotNewsRepository;
    private UserRepository userRepository;
    private CompositeSubscription mSubscriptions;

    public ViewHotNewsCSKPPPresenter(Context context, final ViewHotNewsCSKPPContract.ViewModel viewModel) {
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

            hotNewsRepository = HotNewsCSKPPRepository.getInstance();
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
    public void onNewsContentLoaded(final HotNewsCSKPPItem item) {
        try {

            viewModel.showLoading();

            DataRequest<GetHotNewsInfoCSKPPRequest> baseRequest = new DataRequest<>();
            baseRequest.setApiCode(ApiCode.GetInfoHotNews);
            GetHotNewsInfoCSKPPRequest request = new GetHotNewsInfoCSKPPRequest();
            request.setUsername(userRepository.getUser() != null ? userRepository.getUser().getUserName() : null);
            request.setLanguage(userRepository.getLanguageFromSharePrefs());
            request.setHotNewsId(item.getId());
            baseRequest.setParameterApi(request);

            Subscription subscription =
                    hotNewsRepository.getHotNewsInfo(baseRequest)
                            .subscribe(new MBCCSSubscribe<GetHotNewsInfoCSKPPResponse>() {
                                @Override
                                public void onSuccess(GetHotNewsInfoCSKPPResponse object) {
                                    try {
//                                        if (Constants.Service.RESPONSE_OK.equals(object.getErrorCode())) {

                                        item.setContent(object.getContent());

                                        String content = "<h4>" + item.getTitle() + "</h4>" + item.getContent();
                                        viewModel.showNewsContent(content);
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
}
