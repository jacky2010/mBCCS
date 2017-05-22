package com.viettel.mbccs.screen.changesim.fragments;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.ChangeSimInfo;
import com.viettel.mbccs.data.model.ChangeSimItem;
import com.viettel.mbccs.screen.changesim.adapter.ChangeSimListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minhnx on 5/19/17.
 */

public class SearchChangeSimPresenter implements SearchChangeSimContract.Presenter {

    private Context context;
    private SearchChangeSimContract.ViewModel viewModel;
    private ChangeSimListAdapter changeSimAdapter;

    public ObservableField<String> documentType;
    public ObservableField<String> documentId;
    public ObservableField<String> isdn;
    public ObservableBoolean searchFound;
    public ObservableField<ChangeSimListAdapter> changeSimListAdapter;

    public SearchChangeSimPresenter(Context context, final SearchChangeSimContract.ViewModel viewModel){
        this.context = context;
        this.viewModel = viewModel;

        documentId = new ObservableField<>();
        isdn = new ObservableField<>();
        changeSimListAdapter = new ObservableField<>();
        searchFound = new ObservableBoolean(true);
        changeSimAdapter = new ChangeSimListAdapter(context, new ArrayList<ChangeSimItem>());
        changeSimAdapter.setOnItemClickListener(new ChangeSimListAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, ChangeSimItem item) {
                viewModel.onPrepareChangeSim(item);
            }
        });

        initListeners();
    }

    private void initListeners(){
        try{

        }catch (Exception e){
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
    public void searchSim() {

        try {

            if((documentId.get() == null || "".equals(documentId.get().trim()))
                    && (isdn.get() == null || "".equals(isdn.get().trim()))){

                viewModel.showError(context.getString(R.string.change_sim_error_search_key_required));

                return;
            }

            List<ChangeSimItem> items = new ArrayList<>();

            ChangeSimItem item = new ChangeSimItem();
            item.setChangeSimInfo(new ChangeSimInfo("123456789", "987654321"));

            items.add(item);

            item = new ChangeSimItem();
            item.setChangeSimInfo(new ChangeSimInfo("1234567899", "9876543211"));

            items.add(item);

            changeSimAdapter.setBranchItems(items);

            changeSimListAdapter.set(changeSimAdapter);

//            searchFound.set(false); //TODO minhnx
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
