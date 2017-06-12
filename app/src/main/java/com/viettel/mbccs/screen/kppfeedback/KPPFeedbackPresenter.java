package com.viettel.mbccs.screen.kppfeedback;

import android.content.Context;
import android.databinding.ObservableField;

import com.viettel.mbccs.R;

/**
 * Created by minhnx on 5/19/17.
 */

public class KPPFeedbackPresenter implements KPPFeedbackContract.Presenter {

    private Context context;
    private KPPFeedbackContract.ViewModel viewModel;
    public ObservableField<String> title;

    public KPPFeedbackPresenter(Context context, KPPFeedbackContract.ViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;

        initData();
    }

    private void initData() {
        try {
            title = new ObservableField<>(context.getString(R.string.kpp_feedback_title));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void subscribe() {
    }

    @Override
    public void unSubscribe() {
    }

    public void onCancel() {
        viewModel.onCancel();
    }
}
