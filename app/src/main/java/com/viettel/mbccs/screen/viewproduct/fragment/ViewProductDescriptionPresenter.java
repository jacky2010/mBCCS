package com.viettel.mbccs.screen.viewproduct.fragment;

import android.content.Context;
import android.databinding.ObservableField;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.KeyValue;
import com.viettel.mbccs.data.model.KeyValueIcon;
import com.viettel.mbccs.screen.viewproduct.adapter.ProductCommentAdapter;
import com.viettel.mbccs.screen.viewproduct.adapter.ProductSpecificationAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by minhnx on 5/19/17.
 */

public class ViewProductDescriptionPresenter implements ViewProductDescriptionContract.Presenter {

    private DecimalFormat nf = new DecimalFormat("###");
    public static final String TAB_INDEX_1 = "1";
    public static final String TAB_INDEX_2 = "2";
    public static final String TAB_INDEX_3 = "3";

    public ObservableField<String> tabIndex;
    public ObservableField<String> productDescription;

    public ObservableField<ProductSpecificationAdapter> specificationListAdapter;
    public ObservableField<ProductCommentAdapter> commentListAdapter;

    private Context mContext;
    private ProductSpecificationAdapter specAdapter;
    private ProductCommentAdapter commentAdapter;

    private String description;
    private List<KeyValueIcon> lstSpecs;
    private List<KeyValueIcon> lstComments;

    public ViewProductDescriptionPresenter(Context context, final ViewProductDescriptionContract.ViewModel viewModel) {
        mContext = context;

        initListeners();
        initData();
    }

    private void initData() {
        try {

            lstSpecs = new ArrayList<>();
            lstComments = new ArrayList<>();

            tabIndex = new ObservableField<>(TAB_INDEX_1);
            productDescription = new ObservableField<>();

            specAdapter = new ProductSpecificationAdapter(mContext, lstSpecs);
            specificationListAdapter = new ObservableField<>(specAdapter);

            commentAdapter = new ProductCommentAdapter(mContext, lstComments);
            commentListAdapter = new ObservableField<>(commentAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public void showDetailTabs(String description, List<KeyValue> features, List<KeyValue> comments) {
        this.description = description;

        lstSpecs.clear();
        lstComments.clear();

        if (features != null) {
            for (KeyValue item : features) {
                lstSpecs.add(new KeyValueIcon(R.drawable.ic_add, item.getKey(), item.getValue()));
            }
        }

        if (comments != null) {
            for (KeyValue item : comments) {
                lstComments.add(new KeyValueIcon(0, item.getKey(), item.getValue()));
            }
        }

        if (description != null) {
            tabIndex.set(TAB_INDEX_1);
            productDescription.set(description);
        } else if (features != null)
            tabIndex.set(TAB_INDEX_2);
        else if (comments != null)
            tabIndex.set(TAB_INDEX_3);

        specAdapter.notifyDataSetChanged();
        commentAdapter.notifyDataSetChanged();
    }
}
