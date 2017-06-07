package com.viettel.mbccs.screen.manager.area.popup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.AreaType;

public class PopupDialogFragment extends Fragment {

    private OnClickItemViewFragment mOnClickItemViewFragment;
    private int mAreaType;

    public static PopupDialogFragment getInstance(int mAreaType, OnClickItemViewFragment mOnClickItemViewFragment) {
        PopupDialogFragment popupDialogFragment = new PopupDialogFragment();
        popupDialogFragment.mOnClickItemViewFragment = mOnClickItemViewFragment;
        popupDialogFragment.mAreaType = mAreaType;
        return popupDialogFragment;
    }

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(getIdWithType(), container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        TextView mInfo;
        switch (this.mAreaType) {
            case AreaType.TAB_TH:
                mInfo = (TextView) view.findViewById(R.id.tv_info);
                final View.OnClickListener onClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnClickItemViewFragment != null) {
                            mOnClickItemViewFragment.onClose();
                        }
                    }
                };

                view.findViewById(R.id.iv_close).setOnClickListener(onClickListener);
                mInfo.setText("Tỉnh Vĩnh phúc: \n" +
                        "1. Diện tích: 2000km2\n" +
                        "2. Dân số: 1.000.000 (SL năm 2010).\n" +
                        "3. Số huyện: 14\n" +
                        "4. Tổng số BTS: 20; 2G: 10; 3G: 15, 4G: 50\n" +
                        "5. Trường cấp 1/cấp 2/cấp 3: 500/550/400; \n" +
                        "6. Cao đẳng, nghề/đại học: 10/01\n" +
                        "7. Khu CN: 7; Bệnh viện 02.\n" +
                        "8. Khí hậu: Nhiệt đới rõ 2 mùa. Khô (T5-t12), mưa (T1-T4)\n" +
                        "9. Số kênh 800 (POS 510, SS 12, AG 15, COL 201)");
                break;
            case AreaType.TAB_BTS:
                break;
            case AreaType.TAB_TTKD:
                mInfo = (TextView) view.findViewById(R.id.tv_info);
                mInfo.setText("OUE2CE02_Carre \n" +
                        "1. Diện tích: 200km2\n" +
                        "2. Dân số: 200.000 (SL năm 2010).\n" +
                        "3. Tổng số BTS: 20; 2G: 05; 3G: 5, 4G: 20\n" +
                        "4. Trường cấp 1/cấp 2/cấp 3: 2/1/1; \n" +
                        "5. Cao đẳng, nghề/đại học: 0/00\n" +
                        "6. Khu CN: 1; Bệnh viện 00\n" +
                        "7.  Số kênh 250 (POS 25, SS 50, AG 5, COL 56.)");
                break;
            case AreaType.TAB_CLH:
                break;
            default:
                break;
        }

    }

    private int getIdWithType() {
        switch (this.mAreaType) {
            case AreaType.TAB_TH:
                return R.layout.info_window_th_fragment;
            case AreaType.TAB_BTS:
                return R.layout.info_window_ttkd_fragment;
            case AreaType.TAB_TTKD:
                return R.layout.info_window_ttkd_fragment;
            case AreaType.TAB_CLH:
                return R.layout.info_window_th_fragment;
            default:
                return 0;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public interface OnClickItemViewFragment {
        void onClose();
    }
}
