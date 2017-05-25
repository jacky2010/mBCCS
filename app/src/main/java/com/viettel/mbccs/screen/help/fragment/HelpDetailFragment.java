package com.viettel.mbccs.screen.help.fragment;

import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseFragment;
import com.viettel.mbccs.databinding.FragmentHelpDetailBinding;

/**
 * Created by HuyQuyet on 5/23/17.
 */

public class HelpDetailFragment extends BaseFragment {
    private static final String ARG_TYPE = "TYPE";
    private FragmentHelpDetailBinding binding;
    @HelpType
    private int type;

    public ObservableField<String> textButton;
    public ObservableField<String> dataWebView;

    public static HelpDetailFragment newInstance(@HelpType int type) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_TYPE, type);
        HelpDetailFragment fragment = new HelpDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getInt(ARG_TYPE);
        textButton = new ObservableField<>();
        dataWebView = new ObservableField<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        binding = FragmentHelpDetailBinding.inflate(inflater, container, false);
        binding.setPresenter(this);
        // TODO: 5/23/17 fake data
        switch (type) {
            case HelpType.QLBH:
                dataWebView.set("https://www.google.com.vn");
                textButton.set(getString(R.string.help_vao_chuc_nang_quan_ly_ban_hang));
                break;
            case HelpType.DNTBM:
                dataWebView.set("https://www.google.com.vn");
                textButton.set(getString(R.string.help_vao_chuc_nang_dau_noi_thue_bao_moi));
                break;
            case HelpType.KHYCBH:
                dataWebView.set("https://www.google.com.vn");
                textButton.set(getString(R.string.help_vao_chuc_nang_khach_hang_yeu_cau_bao_hanh));
                break;
            case HelpType.CSM:
                dataWebView.set("https://www.google.com.vn");
                textButton.set(getString(R.string.help_vao_chuc_nang_chinh_sach_moi));
                break;
            case HelpType.CVDG:
                dataWebView.set("https://www.google.com.vn");
                textButton.set(getString(R.string.help_vao_chuc_nang_cong_viec_duoc_giao));
                break;
            case HelpType.TDKPIBH:
                dataWebView.set("https://www.google.com.vn");
                textButton.set(getString(R.string.help_vao_chuc_nang_theo_doi_kpi_ban_hang));
                break;
            default:
                dataWebView.set("https://www.google.com.vn");
                textButton.set(getString(R.string.help_vao_chuc_nang_quan_ly_ban_hang));
        }
        return binding.getRoot();
    }

    public void onCancel() {
        getActivity().getSupportFragmentManager().popBackStack();
    }

    public void onButtonClick() {
        switch (type) {
            case HelpType.QLBH:
                break;
            case HelpType.DNTBM:
                break;
            case HelpType.KHYCBH:
                break;
            case HelpType.CSM:
                break;
            case HelpType.CVDG:
                break;
            case HelpType.TDKPIBH:
                break;
        }
    }

    @IntDef({
            HelpType.QLBH, HelpType.DNTBM, HelpType.KHYCBH, HelpType.CSM, HelpType.CVDG,
            HelpType.TDKPIBH
    })
    public @interface HelpType {
        int QLBH = 1;
        int DNTBM = 2;
        int KHYCBH = 3;
        int CSM = 4;
        int CVDG = 5;
        int TDKPIBH = 6;
    }
}
