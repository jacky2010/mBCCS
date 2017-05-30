package com.viettel.mbccs.screen.information;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.Customer;
import com.viettel.mbccs.data.source.QLKhachHangRepository;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.screen.information.adapter.InformationCustomerAdapter;
import java.util.ArrayList;
import java.util.List;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by HuyQuyet on 5/29/17.
 */

public class CreateUpdateInformationPresenter implements CreateUpdateInformationContract.Presenter {
    private Context context;
    private CreateUpdateInformationContract.View view;
    private QLKhachHangRepository qlKhachHangtRepository;
    private CompositeSubscription subscriptions;
    private boolean typeCreate;
    private List<Customer> customerList;

    public ObservableField<InformationCustomerAdapter> informationCustomerAdapter;
    public ObservableField<String> title;
    public ObservableBoolean isHideData;
    public ObservableBoolean isHideBtnCreate;

    public CreateUpdateInformationPresenter(Context context,
            CreateUpdateInformationContract.View view) {
        this.context = context;
        this.view = view;
        qlKhachHangtRepository = QLKhachHangRepository.getInstance();
        subscriptions = new CompositeSubscription();
        informationCustomerAdapter = new ObservableField<>();
        title = new ObservableField<>();
        isHideData = new ObservableBoolean();
        isHideBtnCreate = new ObservableBoolean();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        subscriptions.clear();
    }

    public void onCancel() {
        view.onCancel();
    }

    public void onSearch() {
        if (customerList == null) {
            customerList = new ArrayList<>();
        }

        if (true) {
            if (customerList.size() == 0) {
                if (typeCreate) {
                    isHideData.set(true);
                    isHideBtnCreate.set(false);
                } else {
                    isHideData.set(true);
                    isHideBtnCreate.set(false);
                }
            }
            view.onSearchSuccess(customerList);
        } else {
            view.onSearchError(BaseException.toUnexpectedError(new Exception()));
        }
    }

    public void onRegisterNewPayment() {
        view.onRegisterNewPayment();
    }

    public void setInformationCustomerAdapter(InformationCustomerAdapter adapter) {
        informationCustomerAdapter.set(adapter);
    }

    public void setTypeCreate(boolean typeCreate) {
        this.typeCreate = typeCreate;
        title.set(typeCreate ? context.getString(R.string.create_update_information_create_title)
                : context.getString(R.string.create_update_information_update_title));
    }
}
