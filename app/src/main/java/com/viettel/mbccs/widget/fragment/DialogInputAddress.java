package com.viettel.mbccs.widget.fragment;

import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.viettel.mbccs.databinding.DialogInputAddressBinding;
import com.viettel.mbccs.utils.StringUtils;

/**
 * Created by HuyQuyet on 6/3/17.
 */

public class DialogInputAddress extends DialogFragment {

    private static final String ARG_ADDRESS = "ADDRESS";
    private static final String ARG_PRECINCT = "PRECINCT";
    private static final String ARG_DISTRICT = "DISTRICT";
    private static final String ARG_PROVINCE = "PROVINCE";

    private DialogInputAddressBinding binding;
    private String address;
    private String precinct;
    private String district;
    private String province;

    private DialogInputAddressCallback callback;

    public ObservableField<String> textAddress;
    public ObservableField<String> textAddressFull;

    public static DialogInputAddress newInstance(String address, String precinct, String district,
            String province) {
        Bundle bundle = new Bundle();
        bundle.putString(ARG_ADDRESS, address);
        bundle.putString(ARG_PRECINCT, precinct);
        bundle.putString(ARG_DISTRICT, district);
        bundle.putString(ARG_PROVINCE, province);
        DialogInputAddress fragment = new DialogInputAddress();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, 0);
        address = getArguments().getString(ARG_ADDRESS);
        precinct = getArguments().getString(ARG_PRECINCT);
        district = getArguments().getString(ARG_DISTRICT);
        province = getArguments().getString(ARG_PROVINCE);
        textAddress = new ObservableField<>();
        textAddressFull = new ObservableField<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        binding = DialogInputAddressBinding.inflate(inflater, container, false);
        binding.editTextAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setFullTextAddress(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return binding.getRoot();
    }

    private void setFullTextAddress(CharSequence s) {
        String data =
                s + (StringUtils.isEmpty(precinct) ? "" : precinct) + (StringUtils.isEmpty(district)
                        ? "" : district) + (StringUtils.isEmpty(province) ? "" : province);

        textAddressFull.set(data);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setPresenter(this);
        textAddress.set(address);
    }

    public void onBackPressed() {
        dismiss();
    }

    public void onClickTextFull() {
        if (callback != null) {
            callback.onResultCallback(textAddress.get(), precinct, district, province);
        }
    }

    public interface DialogInputAddressCallback {
        void onResultCallback(String address, String precinct, String district, String province);
    }

    public void setDialogInputAddressCallback(DialogInputAddressCallback callback) {
        this.callback = callback;
    }
}
