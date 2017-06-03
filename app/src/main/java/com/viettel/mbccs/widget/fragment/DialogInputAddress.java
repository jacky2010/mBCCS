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

/**
 * Created by HuyQuyet on 6/3/17.
 */

public class DialogInputAddress extends DialogFragment {
    private static final String ARG_DATA = "DATA";
    private DialogInputAddressBinding binding;
    private String data;
    private DialogInputAddressCallback callback;

    public ObservableField<String> textAddress;
    public ObservableField<String> textAddressFull;

    public static DialogInputAddress newInstance(String data) {
        Bundle bundle = new Bundle();
        bundle.putString(ARG_DATA, data);
        DialogInputAddress fragment = new DialogInputAddress();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = getArguments().getString(ARG_DATA);
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
        textAddressFull.set(s + data);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setPresenter(this);
        textAddressFull.set(data);
    }

    public void onBackPressed() {
        dismiss();
    }

    public void onClickTextFull() {
        if (callback != null) {
            callback.onResultCallback(textAddressFull.get());
        }
    }

    public interface DialogInputAddressCallback {
        void onResultCallback(String address);
    }

    public void setDialogInputAddressCallback(DialogInputAddressCallback callback) {
        this.callback = callback;
    }
}
