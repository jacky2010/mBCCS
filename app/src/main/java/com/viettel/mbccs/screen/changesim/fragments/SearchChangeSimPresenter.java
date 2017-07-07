package com.viettel.mbccs.screen.changesim.fragments;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.ApiCode;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.ApDomainByType;
import com.viettel.mbccs.data.model.ChangeSimInfo;
import com.viettel.mbccs.data.model.ChangeSimItem;
import com.viettel.mbccs.data.model.KeyValue;
import com.viettel.mbccs.data.model.Subscriber;
import com.viettel.mbccs.data.source.ApDomainRepository;
import com.viettel.mbccs.data.source.ChangeSimRepository;
import com.viettel.mbccs.data.source.remote.request.CheckDebitChangeSimRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetApDomainByTypeRequest;
import com.viettel.mbccs.data.source.remote.request.GetRegisterSubRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.DataResponse;
import com.viettel.mbccs.data.source.remote.response.GetApDomainByTypeResponse;
import com.viettel.mbccs.data.source.remote.response.GetRegisterSubResponse;
import com.viettel.mbccs.screen.changesim.adapter.ChangeSimListAdapter;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.utils.SpinnerAdapter;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import com.viettel.mbccs.variable.Constants;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by minhnx on 5/19/17.
 */

public class SearchChangeSimPresenter implements SearchChangeSimContract.Presenter {

    public static final String ACTION_PAY_DEBIT = "1";
    public static final String ACTION_UNBAR_ONE_WAY = "2";
    public static final String ACTION_UNBAR_TWO_WAY = "3";

    private Context context;
    private SearchChangeSimContract.ViewModel viewModel;
    private ChangeSimListAdapter changeSimAdapter;

    public ObservableField<String> documentType;
    public ObservableField<String> documentId;
    public ObservableField<String> documentIdError;
    public ObservableField<String> isdn;
    public ObservableField<String> isdnError;
    public ObservableBoolean searchFound;
    public ObservableBoolean hideSearch;
    public ObservableField<ChangeSimListAdapter> changeSimListAdapter;

    private SpinnerAdapter<String> documentTypeAdapter;

    private List<String> documentTypesList;
    private List<KeyValue> documentTypes;

    private ChangeSimRepository changeSimRepository;
    private ApDomainRepository apDomainRepository;
    private CompositeSubscription mSubscriptions;

    public SearchChangeSimPresenter(final Context context, final SearchChangeSimContract.ViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;

        documentId = new ObservableField<>();
        documentIdError = new ObservableField<>();
        documentType = new ObservableField<>();
        isdn = new ObservableField<>();
        isdnError = new ObservableField<>();
        changeSimListAdapter = new ObservableField<>();

        searchFound = new ObservableBoolean(true);
        hideSearch = new ObservableBoolean(false);

        changeSimAdapter = new ChangeSimListAdapter(context, new ArrayList<ChangeSimItem>());
        changeSimAdapter.setOnItemClickListener(new ChangeSimListAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, final ChangeSimItem item) {

                viewModel.showLoading();

                DataRequest<CheckDebitChangeSimRequest> baseRequest = new DataRequest<>();
                baseRequest.setWsCode(WsCode.CheckDebit);
                CheckDebitChangeSimRequest request = new CheckDebitChangeSimRequest();
                request.setIsdn(item.getSubscriber().getIsdn());
                baseRequest.setWsRequest(request);

                Subscription subscription =
                        changeSimRepository.checkDebit(baseRequest)
                                .subscribe(new MBCCSSubscribe<DataResponse>() {
                                    @Override
                                    public void onSuccess(DataResponse object) {
                                        try {
//                                            if (Constants.Service.RESPONSE_OK.equals(object.getErrorCode())) {
                                            viewModel.onPrepareChangeSim(item);
//                                            } else {
//                                                goToPreAction(ACTION_PAY_DEBIT, item);
//                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onError(BaseException error) {
                                        if (error.getMessage().contains("Subscriber has debit")) {
                                            goToPreAction(ACTION_PAY_DEBIT, item);
                                        } else
                                            DialogUtils.showDialog(context, null, error.getMessage(),
                                                    null);

                                    }

                                    @Override
                                    public void onRequestFinish() {
                                        super.onRequestFinish();
                                        viewModel.hideLoading();
                                    }
                                });

                mSubscriptions.add(subscription);
            }
        });

        documentTypesList = new ArrayList<>();
        documentTypeAdapter = new SpinnerAdapter<>(context, documentTypesList);
        documentTypeAdapter.setTextHint(
                context.getString(R.string.change_sim_hint_document_type));
        documentTypeAdapter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                onDocumentTypeChanged(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        initListeners();
        initData();

        //TODO minhnx test
        isdn.set("620103022");
        documentType.set("0");
        documentId.set("145079102");
    }

    private void goToPreAction(String action, ChangeSimItem item) {
        try {

            Bundle args = new Bundle();

            switch (action) {
                case ACTION_PAY_DEBIT:
                    args.putString(Constants.BundleConstant.FORM_TYPE, ACTION_PAY_DEBIT);
                    break;
                case ACTION_UNBAR_ONE_WAY:
                    args.putString(Constants.BundleConstant.FORM_TYPE, ACTION_UNBAR_ONE_WAY);
                    break;
                case ACTION_UNBAR_TWO_WAY:
                    args.putString(Constants.BundleConstant.FORM_TYPE, ACTION_UNBAR_TWO_WAY);
                    break;
            }

            args.putString(Constants.BundleConstant.CUSTOMER_ITEM, GsonUtils.Object2String(item));

            viewModel.goToPreAction(args);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initListeners() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initData() {
        try {
            changeSimRepository = ChangeSimRepository.getInstance();
            apDomainRepository = ApDomainRepository.getInstance();
            mSubscriptions = new CompositeSubscription();

            documentTypes = new ArrayList<>();

            //
            viewModel.showLoading();

            DataRequest<GetApDomainByTypeRequest> baseRequest = new DataRequest<>();
            baseRequest.setWsCode(ApiCode.GetApDomainByType);
            GetApDomainByTypeRequest request = new GetApDomainByTypeRequest();
            request.setType(ApDomainByType.Type.LOAI_GIAY_TO);
            baseRequest.setWsRequest(request);

            Subscription subscription =
                    apDomainRepository.getApDomainByType(baseRequest)
                            .subscribe(new MBCCSSubscribe<GetApDomainByTypeResponse>() {
                                @Override
                                public void onSuccess(GetApDomainByTypeResponse object) {
                                    try {
                                        if (object != null && object.getApDomainByTypeList() != null) {

                                            for (ApDomainByType item : object.getApDomainByTypeList()) {
                                                documentTypes.add(new KeyValue(item.getCode(), item.getName()));
                                                documentTypesList.add(item.getName());
                                            }

                                            documentTypeAdapter.notifyDataSetChanged();

                                        } else {
                                            DialogUtils.showDialog(context, null, context.getString(R.string.common_msg_error_general),
                                                    null);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onError(BaseException error) {
                                    DialogUtils.showDialog(context, null, error.getMessage(),
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

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void searchSim() {

        try {

            boolean isValid = true;
            isdnError.set(null);
            documentIdError.set(null);

            if ((documentId.get() == null || "".equals(documentId.get().trim())) && TextUtils.isEmpty(isdn.get())) {
                isdnError.set(context.getString(R.string.input_empty));
                isValid = false;
            }

            if (documentId.get() != null && !"".equals(documentId.get()) && (documentType.get() == null || "".equals(documentType.get().trim()))) {
                viewModel.showError(context.getString(R.string.change_sim_error_search_documentType_required));
                isValid = false;
            }

            if ((isdn.get() == null || TextUtils.isEmpty(isdn.get())) && TextUtils.isEmpty(documentId.get())) {
                documentIdError.set(context.getString(R.string.input_empty));
                isValid = false;
            }

            if (!isValid)
                return;

            final List<ChangeSimItem> items = new ArrayList<>();
            viewModel.showLoading();

            DataRequest<GetRegisterSubRequest> baseRequest = new DataRequest<>();
            baseRequest.setWsCode(ApiCode.GetRegisterSub);
            GetRegisterSubRequest request = new GetRegisterSubRequest();
            request.setIsdn(isdn.get());
            request.setIdNo(documentId.get());
            request.setIdType(Long.parseLong(documentType.get()));
            baseRequest.setWsRequest(request);

            Subscription subscription =
                    changeSimRepository.getRegisterSub(baseRequest)
                            .subscribe(new MBCCSSubscribe<GetRegisterSubResponse>() {
                                @Override
                                public void onSuccess(GetRegisterSubResponse object) {
                                    try {
//                                        if  (Constants.Service.RESPONSE_OK.equals(object.getErrorCode())) {

                                        items.clear();

                                        if (object.getListSubscriber() != null) {
                                            for (Subscriber sub : object.getListSubscriber()) {
                                                ChangeSimItem item = new ChangeSimItem();
                                                item.setSubscriber(sub);
                                                item.setCustomer(sub.getCustomer());
                                                item.setChangeSimInfo(new ChangeSimInfo(item.getSubscriber().getSerial(), null));

                                                items.add(item);
                                            }
                                        }

                                        changeSimAdapter.setItems(items);

                                        changeSimListAdapter.set(changeSimAdapter);

                                        if (changeSimAdapter.getItemCount() > 0) {
                                            searchFound.set(true);

                                            viewModel.onSimFound(isdn.get(), documentType.get(), documentId.get());
                                        } else {
                                            searchFound.set(false);

                                            viewModel.onSimNotFound(isdn.get(), documentType.get(), documentId.get());
                                        }

//                                        } else {
//                                        DialogUtils.showDialog(context, null, context.getString(R.string.change_sim_error_recent_calls_not_valid),
//                                                null);
//                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onError(BaseException error) {
//                                    DialogUtils.showDialog(context, null, error.getMessage(),
//                                            null);
                                    DialogUtils.showDialog(context, null, context.getString(R.string.common_msg_error_no_data),

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

    @Override
    public void onDocumentTypeChanged(int index) {
        try {
            documentType.set(documentTypes.get(index).getKey());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onCollapse() {
        hideSearch.set(true);
    }

    public void onExpand() {
        hideSearch.set(false);
    }

    public SpinnerAdapter<String> getDocumentTypeAdapter() {
        return documentTypeAdapter;
    }
}
//TEST