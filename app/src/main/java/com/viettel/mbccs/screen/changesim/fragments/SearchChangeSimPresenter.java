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
import com.viettel.mbccs.data.model.ChangeSimInfo;
import com.viettel.mbccs.data.model.ChangeSimItem;
import com.viettel.mbccs.data.model.KeyValue;
import com.viettel.mbccs.data.source.ChangeSimRepository;
import com.viettel.mbccs.data.source.remote.request.CheckDebitChangeSimRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetRegiterSubInfoRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.DataResponse;
import com.viettel.mbccs.data.source.remote.response.GetRegiterSubInfoResponse;
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
                baseRequest.setApiCode(ApiCode.CheckDebit);
                CheckDebitChangeSimRequest request = new CheckDebitChangeSimRequest();
                request.setIsdn(item.getSubscriber().getIsdn());
                baseRequest.setParameterApi(request);

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
            mSubscriptions = new CompositeSubscription();

            documentTypes = changeSimRepository.getDocumentTypes();

            documentTypes.add(0, new KeyValue(null, context.getString(R.string.branches_add_hint_document_type)));
            for (KeyValue item : documentTypes) {
                documentTypesList.add(item.getValue());
            }

            documentTypeAdapter.notifyDataSetChanged();

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

            if ((documentType.get() == null || "".equals(documentType.get().trim())) && TextUtils.isEmpty(isdn.get())) {
                isdnError.set(context.getString(R.string.input_empty));
                isValid = false;
            }

            if (documentType.get() == null || "".equals(documentType.get().trim())) {
                viewModel.showError(context.getString(R.string.change_sim_error_search_documentType_required));
                isValid = false;
            }

            if (TextUtils.isEmpty(documentId.get())) {
                documentIdError.set(context.getString(R.string.input_empty));
                isValid = false;
            }

            if (!isValid)
                return;

            final List<ChangeSimItem> items = new ArrayList<>();
            viewModel.showLoading();

            DataRequest<GetRegiterSubInfoRequest> baseRequest = new DataRequest<>();
            baseRequest.setApiCode(ApiCode.GetRegisterSubInfo);
            GetRegiterSubInfoRequest request = new GetRegiterSubInfoRequest();
            request.setIsdn(isdn.get());
            request.setIdNo(documentId.get());
            request.setIdType(documentType.get());
            request.setCheckRegisterStatus("0");//ignore check register status
            baseRequest.setParameterApi(request);

            Subscription subscription =
                    changeSimRepository.getRegiterSubInfo(baseRequest)
                            .subscribe(new MBCCSSubscribe<GetRegiterSubInfoResponse>() {
                                @Override
                                public void onSuccess(GetRegiterSubInfoResponse object) {
                                    try {
//                                        if (Constants.Service.RESPONSE_OK.equals(object.getErrorCode())) {

                                        ChangeSimItem item = new ChangeSimItem();
                                        item.setSubscriber(object.getSubscriber());
                                        item.setCustomer(object.getCustomer());
                                        item.setChangeSimInfo(new ChangeSimInfo(item.getSubscriber().getSerial(), null));

                                        items.add(item);

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
                                    DialogUtils.showDialogError(context, null, context.getString(R.string.common_msg_error_no_data),
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
