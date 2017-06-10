package com.viettel.mbccs.screen.hotnewscskpp.fragments;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;

import com.viettel.mbccs.data.model.HotNewsCSKPPItem;
import com.viettel.mbccs.screen.hotnewscskpp.adapters.HotNewsCSKPPListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minhnx on 5/19/17.
 */

public class SearchHotNewsCSKPPPresenter implements SearchHotNewsCSKPPContract.Presenter {

    private Context context;
    private SearchHotNewsCSKPPContract.ViewModel viewModel;
    private HotNewsCSKPPListAdapter newsAdapter;

    public ObservableBoolean searchFound;
    public ObservableField<HotNewsCSKPPListAdapter> newsListAdapter;

    public SearchHotNewsCSKPPPresenter(Context context, final SearchHotNewsCSKPPContract.ViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;

        newsListAdapter = new ObservableField<>();
        searchFound = new ObservableBoolean(true);
        newsAdapter = new HotNewsCSKPPListAdapter(context, new ArrayList<HotNewsCSKPPItem>());
        newsAdapter.setOnItemClickListener(new HotNewsCSKPPListAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, HotNewsCSKPPItem item) {
                viewModel.openHotNewsDetail(item);
            }
        });

        initListeners();
        searchNews();
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
    public void searchNews() {
        try {

            List<HotNewsCSKPPItem> items = new ArrayList<>();
            HotNewsCSKPPItem item = new HotNewsCSKPPItem();
            item.setTitle("[Video] Honda sẽ có xe tự hành hoàn toàn vào năm 2025");
            item.setContent("Trong kế hoạch trung hạn Vision 2030, Honda cho biết họ sẽ thúc đẩy sự phối hợp giữa các khâu Nghiên cứu & Phát triển, Thu mua và Sản xuất, vươn xa hơn những loại động cơ truyền thống và những chiếc xe vận hành theo cách thông thường.\n" +
                    "Theo CEO Honda, ông Takahiro Hachigo, Honda sẽ đặt ưu tiên cao nhất cho việc thúc đẩy phát triển nhanh công nghệ an toàn tiến tiến và xe điện.\n");
            item.setCreateDate("1/1/2016");

            items.add(item);

            item = new HotNewsCSKPPItem();
            item.setTitle("Demo 2");
            item.setContent("Survey 2");
            item.setCreateDate("1/2/2017");

            items.add(item);

            newsAdapter.setHotNewsItems(items);

            newsListAdapter.set(newsAdapter);

            if (newsAdapter.getItemCount() > 0) {
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
