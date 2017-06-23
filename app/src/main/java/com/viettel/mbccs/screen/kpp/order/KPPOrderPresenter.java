package com.viettel.mbccs.screen.kpp.order;

import android.app.Activity;
import android.content.Context;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.OrderStatus;
import com.viettel.mbccs.constance.ApiCode;
import com.viettel.mbccs.data.model.SaleOrders;
import com.viettel.mbccs.data.source.BanHangKhoTaiChinhRepository;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetListOrderRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetListOrderResponse;
import com.viettel.mbccs.screen.kpp.order.adaper.KPPOrderAdapter;
import com.viettel.mbccs.utils.Common;
import com.viettel.mbccs.utils.DateUtils;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.SpinnerAdapter;
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
    public ObservableField<Integer> adapterPosition;
    private Context mContext;
    private KPPOrderContract.ViewModel mViewModel;
    private SpinnerAdapter<String> adapterStatus;
    private KPPOrderAdapter mKPPOrderAdapter;
    private List<SaleOrders> mSaleOrderses = new ArrayList<>();
    private BanHangKhoTaiChinhRepository mBanHangKhoTaiChinhRepository;
    private UserRepository mUserRepository;
    private CompositeSubscription mSubscriptions;
    private String status = OrderStatus.PENDING;

    private DataRequest<GetListOrderRequest> mGetListOrderRequestBaseRequest;

    public KPPOrderPresenter(Context context, KPPOrderContract.ViewModel viewModel) {
        mContext = context;
        mViewModel = viewModel;
        mBanHangKhoTaiChinhRepository = BanHangKhoTaiChinhRepository.getInstance();
        mUserRepository = UserRepository.getInstance();
        mSubscriptions = new CompositeSubscription();
        init();
    }

    private void init() {
        filterText = new ObservableField<>();
        isCollapse = new ObservableField<>();
        isCollapse.set(false);
        titleOrderList = new ObservableField<>();
        adapterPosition = new ObservableField<>();
        adapterPosition.set(1);

        adapterStatus = new SpinnerAdapter<String>(mContext,
                mContext.getResources().getStringArray(R.array.order_status_name));
        adapterStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                onChannelSelectedChagne(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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
        mGetListOrderRequestBaseRequest = new DataRequest<>();
        mGetListOrderRequestBaseRequest.setApiCode(ApiCode.GetListOrder);
        GetListOrderRequest request = new GetListOrderRequest();
        request.setOrderStatus(status);
        request.setShopId(Long.parseLong(mUserRepository.getUserInfo().getShop().getShopId()));
        request.setStaffId(mUserRepository.getUserInfo().getStaffInfo().getStaffId());
        request.setIsdnChannel(mUserRepository.getUserInfo().getChannelInfo().getTel());
        request.setFromDate(DateUtils.convertDateToString(mViewModel.getFromDate(),
                DateUtils.DATE_TIME_FORMAT));
        request.setToDate(DateUtils.convertDateToString(mViewModel.getFromDate(),
                DateUtils.DATE_TIME_FORMAT));
        mGetListOrderRequestBaseRequest.setParameterApi(request);
        Subscription subscription =
                mBanHangKhoTaiChinhRepository.getListOrder(mGetListOrderRequestBaseRequest)
                        .subscribe(new MBCCSSubscribe<GetListOrderResponse>() {
                            @Override
                            public void onSuccess(GetListOrderResponse object) {
                                if (object != null && object.getSaleOrdersList() != null) {
                                    if (object.getSaleOrdersList().size() == 0) {
                                        DialogUtils.showDialog(mContext,
                                                R.string.common_msg_no_data);
                                        return;
                                    }
                                    mSaleOrderses.clear();
                                    mSaleOrderses.addAll(object.getSaleOrdersList());
                                    mKPPOrderAdapter.notifyDataSetChanged();
                                    titleOrderList.set(String.format(
                                            mContext.getString(R.string.order_title_list),
                                            String.valueOf(mSaleOrderses.size())));
                                    mViewModel.collapseForm();
                                    return;
                                }
                                onError(new Throwable());
                            }

                            @Override
                            public void onError(BaseException error) {
                                DialogUtils.showDialog(mContext, null, error.getMessage(),
                                        null);
                                //fakeOrder();
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
                break;
            default:
                status = OrderStatus.APPROVALS;
        }
    }

    @Override
    public void refreshData() {
        find();
    }

    public void getFilterText() {
        String text = Common.getDayByLong(mViewModel.getFromDate()) + mContext.getString(
                R.string.common_label_dot) + Common.getDayByLong(mViewModel.getToDate());
        String[] stringArray = mContext.getResources().getStringArray(R.array.order_status_name);
        if (status.equals(OrderStatus.APPROVALS)) {
            text += mContext.getString(R.string.common_label_dot) + stringArray[0];
        } else if (status.equals(OrderStatus.PENDING)) {
            text += mContext.getString(R.string.common_label_dot) + stringArray[1];
        } else {
            text += mContext.getString(R.string.common_label_dot) + stringArray[2];
            return;
        }

        filterText.set(text);
    }
}
