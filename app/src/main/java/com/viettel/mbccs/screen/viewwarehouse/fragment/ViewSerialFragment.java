package com.viettel.mbccs.screen.viewwarehouse.fragment;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.viettel.mbccs.base.BaseFragment;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.SerialBO;
import com.viettel.mbccs.data.model.Session;
import com.viettel.mbccs.data.model.StockSerial;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.data.source.BanHangKhoTaiChinhRepository;
import com.viettel.mbccs.data.source.remote.request.BaseRequest;
import com.viettel.mbccs.data.source.remote.request.ViewInfoSerialRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.databinding.FragmentWareHouseViewSerialBinding;
import com.viettel.mbccs.screen.viewwarehouse.adapter.ViewWarehouseViewSerialAdapter;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import java.util.ArrayList;
import java.util.List;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by HuyQuyet on 5/22/17.
 */

public class ViewSerialFragment extends BaseFragment {
    private static final String ARG_STOCK_TOTAL = "STOCK_TOTAL";
    private FragmentWareHouseViewSerialBinding binding;
    private BanHangKhoTaiChinhRepository banHangKhoTaiChinhRepository;
    private CompositeSubscription subscriptions;
    private StockTotal stockTotal;
    private List<StockSerial> stockSerialList;

    public ObservableField<String> codeStock;
    public ObservableInt totalSerial;
    public ObservableField<ViewWarehouseViewSerialAdapter> adapterViewSerial;

    public static ViewSerialFragment newInstance(StockTotal stockTotal) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_STOCK_TOTAL, stockTotal);
        ViewSerialFragment fragment = new ViewSerialFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        banHangKhoTaiChinhRepository = BanHangKhoTaiChinhRepository.getInstance();
        subscriptions = new CompositeSubscription();
        stockTotal = getArguments().getParcelable(ARG_STOCK_TOTAL);

        codeStock = new ObservableField<>();
        totalSerial = new ObservableInt();
        adapterViewSerial = new ObservableField<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        binding = FragmentWareHouseViewSerialBinding.inflate(inflater, container, false);
        binding.setPresenter(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showLoadingDialog();
        ViewInfoSerialRequest v = new ViewInfoSerialRequest();
        v.setOwnerId(stockTotal.getOwnerId());
        v.setOwnerType(stockTotal.getOwnerType());
        v.setStockModelId(stockTotal.getStockModelId());
        v.setStateId(stockTotal.getStateId());

        BaseRequest<ViewInfoSerialRequest> request = new BaseRequest<>();
        request.setWsCode(WsCode.ViewInfoSerial);
        request.setRequest(v);
        request.setApiKey("demo");
        request.setSession(new Session());

        Subscription subscription = banHangKhoTaiChinhRepository.viewInfoSerial(request)
                .subscribe(new MBCCSSubscribe<List<StockSerial>>() {
                    @Override
                    public void onSuccess(List<StockSerial> object) {
                        setData(object);
                    }

                    @Override
                    public void onError(BaseException error) {
                        hideLoadingDialog();
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT)
                                .show();
                    }
                });
        subscriptions.add(subscription);
    }

    @Override
    public void onStop() {
        subscriptions.clear();
        super.onStop();
    }

    public void onCancel() {
        getActivity().getSupportFragmentManager().popBackStackImmediate();
    }

    public void setData(List<StockSerial> data) {
        stockSerialList = data;
        // TODO: 5/22/17 data error
        List<SerialBO> serialBOList = new ArrayList<>();
        adapterViewSerial.set(new ViewWarehouseViewSerialAdapter(serialBOList));
        totalSerial.set(stockSerialList.size());
        codeStock.set(stockTotal.getStockModelCode());
        hideLoadingDialog();
    }
}
