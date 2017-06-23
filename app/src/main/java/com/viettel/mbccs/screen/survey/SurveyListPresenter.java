package com.viettel.mbccs.screen.survey;

import android.content.Context;
import com.viettel.mbccs.constance.ApiCode;
import com.viettel.mbccs.data.model.Survey;
import com.viettel.mbccs.data.source.SurveyKPPRepository;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetSurveyKPPRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetSurveyKPPResponse;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import com.viettel.mbccs.widget.viewholderbinding.BaseRecyclerViewAdapterBinding;
import java.util.ArrayList;
import java.util.List;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by FRAMGIA\hoang.van.cuong on 23/06/2017.
 */

public class SurveyListPresenter implements SurveyListContract.Presenter,
        BaseRecyclerViewAdapterBinding.OnRecyclerItemListener<Survey> {

    private Context mContext;
    private SurveyListContract.ViewModel mViewModel;
    private SurveyAdapter mAdapter;
    private List<Survey> mSurveys = new ArrayList<>();
    private SurveyKPPRepository mSurveyKPPRepository;
    private UserRepository mUserRepository;
    private CompositeSubscription mSubcriptions;

    public SurveyListPresenter(Context context, SurveyListContract.ViewModel viewModel) {
        mContext = context;
        mViewModel = viewModel;
        mSurveyKPPRepository = SurveyKPPRepository.getInstance();
        mUserRepository = UserRepository.getInstance();
        mSubcriptions = new CompositeSubscription();
        init();
        loadData();
    }

    private void loadData() {
        mViewModel.showLoading();
        GetSurveyKPPRequest request = new GetSurveyKPPRequest();
        request.setLanguage(mUserRepository.getLanguageFromSharePrefs());
        request.setUserName(mUserRepository.getLoginUserName());
        DataRequest<GetSurveyKPPRequest> dataRequest = new DataRequest<>();
        dataRequest.setApiCode(ApiCode.GetSurveyKPP);
        dataRequest.setParameterApi(request);
        Subscription subscription = mSurveyKPPRepository.getSurveyKPP(dataRequest)
                .subscribe(new MBCCSSubscribe<GetSurveyKPPResponse>() {
                    @Override
                    public void onSuccess(GetSurveyKPPResponse object) {
                        if (object != null && object.getSurveys() != null) {
                            bindData(object.getSurveys());
                        }
                    }

                    @Override
                    public void onError(BaseException error) {
                        DialogUtils.showDialog(mContext, error.getMessage());
                    }

                    @Override
                    public void onRequestFinish() {
                        super.onRequestFinish();
                        mViewModel.hideLoading();
                    }
                });
        mSubcriptions.add(subscription);
    }

    private void bindData(List<Survey> surveys) {
        mSurveys.clear();
        mSurveys.addAll(surveys);
        mAdapter.notifyDataSetChanged();
    }

    private void init() {
        mAdapter = new SurveyAdapter(mContext, mSurveys);
        mAdapter.setSurveyOnRecyclerItemListener(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    public SurveyAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(SurveyAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public void onItemClick(int postition, Survey data) {
        mViewModel.onSurveyClick(data);
    }

    public void onCancel() {
        mViewModel.close();
    }

    @Override
    public void reloadData() {
        loadData();
    }
}
