package com.viettel.mbccs.screen.common.success;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.SerialBO;
import com.viettel.mbccs.data.model.StockSerial;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.data.source.BanHangKhoTaiChinhRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetListSerialRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetListSerialResponse;
import com.viettel.mbccs.databinding.DialogViewSerialBinding;
import com.viettel.mbccs.databinding.ItemViewSerialBinding;
import com.viettel.mbccs.dialog.LoadingDialog;
import com.viettel.mbccs.utils.Common;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import java.util.List;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by HuyQuyet on 6/7/17.
 */

public class DialogViewSerial extends DialogFragment {
    private DialogViewSerialBinding binding;
    private BanHangKhoTaiChinhRepository banHangKhoTaiChinhRepository;
    private CompositeSubscription subscriptions;
    private StockTotal stockTotal;
    private StockSerial stockSerial;
    private LoadingDialog loadingDialog;

    public ObservableField<String> codeStock;
    public ObservableField<ViewViewSerialAdapter> adapterViewSerial;
    public ObservableInt totalSerial;

    public static DialogViewSerial newInstance() {
        Bundle bundle = new Bundle();
        DialogViewSerial fragment = new DialogViewSerial();
        fragment.setArguments(bundle);
        return fragment;
    }

    public void setStockTotal(StockTotal stockTotal) {
        this.stockTotal = stockTotal;
    }

    public void setStockSerial(StockSerial stockSerial) {
        this.stockSerial = stockSerial;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        banHangKhoTaiChinhRepository = BanHangKhoTaiChinhRepository.getInstance();
        subscriptions = new CompositeSubscription();

        codeStock = new ObservableField<>();
        totalSerial = new ObservableInt();
        adapterViewSerial = new ObservableField<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        binding = DialogViewSerialBinding.inflate(inflater, container, false);
        binding.setPresenter(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (stockSerial != null) {
            setData(stockSerial);
            return;
        }

        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog();
            loadingDialog.setCancelable(false);
        }
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        loadingDialog.show(fragmentManager, "loading");

        GetListSerialRequest v = new GetListSerialRequest();
        v.setOwnerId(stockTotal.getOwnerId());
        v.setOwnerType(stockTotal.getOwnerType());
        v.setStockModelId(stockTotal.getStockModelId());
        v.setStateId(stockTotal.getStateId());

        DataRequest<GetListSerialRequest> request = new DataRequest<>();
        request.setWsCode(WsCode.GetListSerial);
        request.setWsRequest(v);

        Subscription subscription = banHangKhoTaiChinhRepository.getListSerial(request)
                .subscribe(new MBCCSSubscribe<GetListSerialResponse>() {
                    @Override
                    public void onSuccess(GetListSerialResponse object) {
                        setData(object.getSerialInStock());
                        if (loadingDialog != null) loadingDialog.dismiss();
                    }

                    @Override
                    public void onError(BaseException error) {
                        if (loadingDialog != null) loadingDialog.dismiss();
                        DialogUtils.showDialogError(getActivity(), error,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dismiss();
                                    }
                                }, false);
                    }
                });
        subscriptions.add(subscription);
    }

    public void getViewInforSerial() {

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow()
                    .setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Override
    public void onStop() {
        subscriptions.clear();
        super.onStop();
    }

    public void onCancel() {
        dismiss();
    }

    public void setData(StockSerial data) {
        stockSerial = data;
        adapterViewSerial.set(new ViewViewSerialAdapter(data.getSerialBOs(), getActivity()));
        totalSerial.set(Common.getSerialCountByListSerialBlock(stockSerial.getSerialBOs()));
        codeStock.set(stockSerial.getStockModelName());
    }

    public class ViewViewSerialAdapter
            extends RecyclerView.Adapter<ViewViewSerialAdapter.ViewHolder> {
        private ItemViewSerialBinding binding;
        private List<SerialBO> serialBOList;
        private Context context;

        public ViewViewSerialAdapter(List<SerialBO> serialBOList, Context context) {
            this.serialBOList = serialBOList;
            this.context = context;
        }

        @Override
        public ViewViewSerialAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            binding =
                    ItemViewSerialBinding.inflate(LayoutInflater.from(parent.getContext()), parent,
                            false);
            return new ViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(ViewViewSerialAdapter.ViewHolder holder, int position) {
            holder.bind(serialBOList.get(position), position);
        }

        @Override
        public int getItemCount() {
            return serialBOList == null ? 0 : serialBOList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ItemViewSerialBinding view;
            public ObservableInt background;
            public ObservableField<String> fromSerial;
            public ObservableField<String> toSerial;
            public ObservableField<String> quantity;

            public ViewHolder(ItemViewSerialBinding itemView) {
                super(itemView.getRoot());
                view = itemView;
                background = new ObservableInt();
                fromSerial = new ObservableField<>();
                toSerial = new ObservableField<>();
                quantity = new ObservableField<>();
            }

            public void bind(SerialBO serialBO, int position) {
                if (view.getPresenter() == null) {
                    view.setPresenter(this);
                }
                fromSerial.set(serialBO.getFromSerial());
                toSerial.set(serialBO.getToSerial());
                quantity.set(serialBO.getQuantityString());
                if (position % 2 == 0) {
                    background.set(context.getResources().getColor(R.color.white));
                } else {
                    background.set(context.getResources().getColor(R.color.grey_six));
                }
            }
        }
    }
}
