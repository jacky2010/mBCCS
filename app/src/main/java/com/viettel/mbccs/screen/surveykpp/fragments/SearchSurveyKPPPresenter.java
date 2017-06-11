package com.viettel.mbccs.screen.surveykpp.fragments;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;

import com.viettel.mbccs.data.model.SurveyItem;
import com.viettel.mbccs.screen.surveykpp.adapters.SurveysKPPListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minhnx on 5/19/17.
 */

public class SearchSurveyKPPPresenter implements SearchSurveyKPPContract.Presenter {

    private Context context;
    private SearchSurveyKPPContract.ViewModel viewModel;
    private SurveysKPPListAdapter surveysAdapter;

    public ObservableBoolean searchFound;
    public ObservableField<SurveysKPPListAdapter> surveysListAdapter;

    public SearchSurveyKPPPresenter(Context context, final SearchSurveyKPPContract.ViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;

        surveysListAdapter = new ObservableField<>();
        searchFound = new ObservableBoolean(true);
        surveysAdapter = new SurveysKPPListAdapter(context, new ArrayList<SurveyItem>());
        surveysAdapter.setOnItemClickListener(new SurveysKPPListAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, SurveyItem item) {
                viewModel.onPrepareSurvey(item);
            }
        });

        initListeners();
        searchSurveys();
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
    public void searchSurveys() {
        try {

            List<SurveyItem> items = new ArrayList<>();
            SurveyItem item = new SurveyItem();
            item.setCreator("Demo 1");
            item.setSurveyName("Survey 1");

            items.add(item);

            item = new SurveyItem();
            item.setCreator("Demo 2");
            item.setSurveyName("Survey 2");

            items.add(item);

            surveysAdapter.setSurveyItems(items);

            surveysListAdapter.set(surveysAdapter);

            if (surveysAdapter.getItemCount() > 0) {
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
