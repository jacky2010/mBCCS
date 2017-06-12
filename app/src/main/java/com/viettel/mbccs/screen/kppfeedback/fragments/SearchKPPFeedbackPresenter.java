package com.viettel.mbccs.screen.kppfeedback.fragments;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;

import com.viettel.mbccs.data.model.KPPFeedback;
import com.viettel.mbccs.data.source.KPPFeedbackRepository;
import com.viettel.mbccs.screen.kppfeedback.adapters.KPPFeedbackListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minhnx on 5/19/17.
 */

public class SearchKPPFeedbackPresenter implements SearchKPPFeedbackContract.Presenter {

    private Context context;
    private SearchKPPFeedbackContract.ViewModel viewModel;
    private KPPFeedbackListAdapter feedbackAdapter;

    public ObservableBoolean searchFound;
    public ObservableField<KPPFeedbackListAdapter> feedbacksListAdapter;
    private KPPFeedbackRepository feedbackRepository;

    public SearchKPPFeedbackPresenter(Context context, final SearchKPPFeedbackContract.ViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;

        feedbacksListAdapter = new ObservableField<>();
        searchFound = new ObservableBoolean(true);
        feedbackAdapter = new KPPFeedbackListAdapter(context, new ArrayList<KPPFeedback>());
        feedbackAdapter.setOnItemClickListener(new KPPFeedbackListAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, KPPFeedback item) {
                viewModel.openFeedbackDetail(item);
            }
        });

        feedbackRepository = KPPFeedbackRepository.getInstance();

        initListeners();
        searchFeedbacks();
    }

    private void initListeners() {
        try {

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

    @Override
    public void searchFeedbacks() {
        try {

            List<KPPFeedback> items = new ArrayList<>();
            KPPFeedback item = new KPPFeedback();
            item.setTitle("[Video] Honda sẽ có xe tự hành hoàn toàn vào năm 2025");
            item.setContent("Trong kế hoạch trung hạn Vision 2030, Honda cho biết họ sẽ thúc đẩy sự phối hợp giữa các khâu Nghiên cứu & Phát triển, Thu mua và Sản xuất, vươn xa hơn những loại động cơ truyền thống và những chiếc xe vận hành theo cách thông thường.\n" +
                    "Theo CEO Honda, ông Takahiro Hachigo, Honda sẽ đặt ưu tiên cao nhất cho việc thúc đẩy phát triển nhanh công nghệ an toàn tiến tiến và xe điện.\n");
            item.setCreateDate("1/1/2016");

            items.add(item);

            item = new KPPFeedback();
            item.setTitle("Demo 2");
            item.setContent("Survey 2");
            item.setCreateDate("1/2/2017");

            items.add(item);

            feedbackAdapter.setFeedbackItems(items);

            feedbacksListAdapter.set(feedbackAdapter);

            if (feedbackAdapter.getItemCount() > 0) {
                searchFound.set(true);
            } else {
                searchFound.set(false);
            }

            viewModel.onSearchCompleted();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
