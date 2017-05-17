package com.viettel.mbccs.screen.main.fragments.menu;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.viettel.mbccs.base.BaseFragment;

/**
 * Created by FRAMGIA\vu.viet.anh on 16/05/2017.
 */

public class MenuFragment extends BaseFragment {

    public static MenuFragment newInstance() {
        return new MenuFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View frame = new View(getActivity());
        frame.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        frame.setBackgroundColor(Color.RED);
        return frame;
    }
}
