package com.viettel.mbccs.screen.kpp.order;

import android.app.Activity;
import android.content.Context;
import android.databinding.ObservableField;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.OrderStatus;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.SaleOrders;
import com.viettel.mbccs.data.source.BanHangKhoTaiChinhRepository;
import com.viettel.mbccs.data.source.remote.request.BaseRequest;
import com.viettel.mbccs.data.source.remote.request.GetListOrderRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.screen.kpp.order.adaper.KPPOrderAdapter;
import com.viettel.mbccs.utils.Common;
import com.viettel.mbccs.utils.ValidateUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import java.util.ArrayList;
import java.util.List;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by eo_cuong on 5/21/17.
 */

public class KPPOrderPresenter implements KPPOrderContract.Presenter {

    public ObservableField<String> filterText;
    public ObservableField<Boolean> isCollapse;
    public ObservableField<String> titleOrderList;
    public ObservableField<String> amountOrderList;
    public ObservableField<Integer> adapterPosition;
    private Context mContext;
    private KPPOrderContract.ViewModel mViewModel;
    private ArrayAdapter<String> adapterStatus;
    private KPPOrderAdapter mKPPOrderAdapter;
    private List<SaleOrders> mSaleOrderses = new ArrayList<>();
    private BanHangKhoTaiChinhRepository mBanHangKhoTaiChinhRepository;
    private CompositeSubscription mSubscriptions;
    private long status = OrderStatus.PENDING;

    private BaseRequest<GetListOrderRequest> mGetListOrderRequestBaseRequest;

    public KPPOrderPresenter(Context context, KPPOrderContract.ViewModel viewModel) {
        mContext = context;
        mViewModel = viewModel;
        mBanHangKhoTaiChinhRepository = BanHangKhoTaiChinhRepository.getInstance();
        mSubscriptions = new CompositeSubscription();
        init();
    }

    private void init() {
        filterText = new ObservableField<>();
        isCollapse = new ObservableField<>();
        isCollapse.set(false);
        titleOrderList = new ObservableField<>();
        amountOrderList = new ObservableField<>();
        adapterPosition = new ObservableField<>();
        adapterPosition.set(1);

        adapterStatus = new ArrayAdapter<String>(mContext, R.layout.item_spinner, R.id.text,
                mContext.getResources().getStringArray(R.array.order_status_name));

        mKPPOrderAdapter = new KPPOrderAdapter(mContext, mSaleOrderses);
    }

    private boolean validate() {

        long fromDate = mViewModel.getFromDate();
        long toDate = mViewModel.getToDate();
        if (!ValidateUtils.isTimeFromToValid(fromDate, toDate)) {
            Toast.makeText(mContext, mContext.getString(R.string.time_invalid), Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
        if (!ValidateUtils.isTimeForDay(fromDate, toDate, 30)) {
            Toast.makeText(mContext, mContext.getString(R.string.time_out_of_month),
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void find() {
        if (!validate()) {
            return;
        }
        mViewModel.showLoading();
        mGetListOrderRequestBaseRequest = new BaseRequest<>();
        mGetListOrderRequestBaseRequest.setWsCode(WsCode.GetListOrder);
        GetListOrderRequest request = new GetListOrderRequest();
        request.setOrderStatus(status);
        request.setFromDate("");
        request.setToDate("");
        Subscription subscription = mBanHangKhoTaiChinhRepository.searchSellOrders(null)
                .subscribe(new MBCCSSubscribe<List<SaleOrders>>() {
                    @Override
                    public void onSuccess(List<SaleOrders> object) {

                        mSaleOrderses.clear();
                        mSaleOrderses.addAll(object);
                        mKPPOrderAdapter.notifyDataSetChanged();

                        titleOrderList.set(
                                String.format(mContext.getString(R.string.order_title_list),
                                        String.valueOf(mSaleOrderses.size())));
                        amountOrderList.set(
                                String.format(mContext.getString(R.string.order_amout_list),
                                        String.valueOf(1000000)));
                    }

                    @Override
                    public void onError(BaseException error) {

                        //DialogUtils.showDialogError(mContext, null, error.getMessage(), null);
                        fakeOrder();
                    }

                    @Override
                    public void onRequestFinish() {
                        super.onRequestFinish();
                        mViewModel.hideLoading();
                    }
                });

        mSubscriptions.add(subscription);
    }

    private void fakeOrder() {
        mSaleOrderses.clear();
        SaleOrders sale1 = new SaleOrders();
        sale1.setChannelCode("2132423");
        sale1.setOrderDate("12/12/2012");
        sale1.setOrderStatus(OrderStatus.APPROVALS);

        SaleOrders sale2 = new SaleOrders();
        sale2.setChannelCode("23423423");
        sale2.setOrderDate("12/12/2012");
        sale2.setOrderStatus(OrderStatus.PENDING);

        SaleOrders sale3 = new SaleOrders();
        sale3.setChannelCode("4545645645");
        sale3.setOrderDate("12/12/2012");
        sale3.setOrderStatus(OrderStatus.REJECT);

        mSaleOrderses.add(sale1);
        mSaleOrderses.add(sale2);
        mSaleOrderses.add(sale3);

        mKPPOrderAdapter.notifyDataSetChanged();

        titleOrderList.set(String.format(mContext.getString(R.string.order_title_list),
                String.valueOf(mSaleOrderses.size())));
        amountOrderList.set(String.format(mContext.getString(R.string.order_amout_list),
                String.valueOf(1000000)));
    }

    public void clickSearch() {

        find();
    }

    public void onCancel() {
        ((Activity) mContext).finish();
    }

    public void addNew() {
        mViewModel.gotoAddNewOrder();
    }

    public void toogleExpand() {
        isCollapse.set(!isCollapse.get());
        if (isCollapse.get()) {
            getFilterText();
        }
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

        mSubscriptions.clear();
    }

    public ArrayAdapter getAdapterStatus() {
        return adapterStatus;
    }

    public KPPOrderAdapter getKPPOrderAdapter() {
        return mKPPOrderAdapter;
    }

    @Override
    public void onChannelSelectedChagne(int position) {
        adapterPosition.set(position);
        switch (position) {
            case 0:
                status = OrderStatus.APPROVALS;
                break;

            case 1:
                status = OrderStatus.PENDING;
                break;
            case 2:
                status = OrderStatus.REJECT;
            default:
                status = OrderStatus.APPROVALS;
        }
    }

    @Override
    public void refreshData() {
        find();
    }

    public void getFilterText() {
        String text = Common.getDayByLong(mViewModel.getFromDate()) + " - " + Common.getDayByLong(
                mViewModel.getToDate());
        String[] stringArray = mContext.getResources().getStringArray(R.array.order_status_name);
        switch ((int) status) {
            case OrderStatus.APPROVALS:
                text += " - " + stringArray[0];
                break;
            case OrderStatus.PENDING:
                text += " - " + stringArray[1];
                break;
            case OrderStatus.REJECT:
                text += " - " + stringArray[2];
                return;
        }

        filterText.set(text);
    }
}
