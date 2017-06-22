package com.viettel.mbccs.screen.hotnewscskpp.fragments;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;

import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.ApiCode;
import com.viettel.mbccs.data.model.HotNewsCSKPPItem;
import com.viettel.mbccs.data.source.HotNewsCSKPPRepository;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetHotNewsCSKPPRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetHotNewsCSKPPResponse;
import com.viettel.mbccs.screen.hotnewscskpp.adapters.HotNewsCSKPPListAdapter;
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

public class SearchHotNewsCSKPPPresenter implements SearchHotNewsCSKPPContract.Presenter {

    private Context context;
    private SearchHotNewsCSKPPContract.ViewModel viewModel;
    private HotNewsCSKPPListAdapter newsAdapter;

    public ObservableBoolean searchFound;
    public ObservableField<HotNewsCSKPPListAdapter> newsListAdapter;

    private HotNewsCSKPPRepository hotNewsRepository;
    private UserRepository userRepository;
    private CompositeSubscription mSubscriptions;

    public SearchHotNewsCSKPPPresenter(Context context, final SearchHotNewsCSKPPContract.ViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;

        newsListAdapter = new ObservableField<>();
        searchFound = new ObservableBoolean(true);
        newsAdapter = new HotNewsCSKPPListAdapter(context, new ArrayList<HotNewsCSKPPItem>());
        newsAdapter.setOnItemClickListener(new HotNewsCSKPPListAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, HotNewsCSKPPItem item) {
                viewModel.openHotNewsDetail(item);
            }
        });

        initListeners();
        initData();
        searchNews();
    }

    private void initListeners() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initData() {
        hotNewsRepository = HotNewsCSKPPRepository.getInstance();
        userRepository = UserRepository.getInstance();
        mSubscriptions = new CompositeSubscription();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void searchNews() {
        try {

            final List<HotNewsCSKPPItem> items = new ArrayList<>();
            viewModel.showLoading();

            DataRequest<GetHotNewsCSKPPRequest> baseRequest = new DataRequest<>();
            baseRequest.setApiCode(ApiCode.GetListHotNews);
            GetHotNewsCSKPPRequest request = new GetHotNewsCSKPPRequest();
            request.setUsername(userRepository.getUser() != null ? userRepository.getUser().getUserName() : null);
            request.setLanguage(userRepository.getLanguageFromSharePrefs());
            request.setOwnerCode((userRepository.getUserInfo() != null && userRepository.getUserInfo().getChannelInfo() != null) ? userRepository.getUserInfo().getChannelInfo().getChannelCode() : null);
            baseRequest.setParameterApi(request);

            Subscription subscription =
                    hotNewsRepository.getHotNews(baseRequest)
                            .subscribe(new MBCCSSubscribe<GetHotNewsCSKPPResponse>() {
                                @Override
                                public void onSuccess(GetHotNewsCSKPPResponse object) {
                                    try {
                                        if (Constants.Service.RESPONSE_OK.equals(object.getErrorCode())) {

                                            items.addAll(object.getListHotNews());

                                            newsAdapter.setHotNewsItems(items);

                                            newsListAdapter.set(newsAdapter);

                                            if (newsAdapter.getItemCount() > 0) {
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
