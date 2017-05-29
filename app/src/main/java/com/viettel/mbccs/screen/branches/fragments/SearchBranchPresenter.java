package com.viettel.mbccs.screen.branches.fragments;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.BranchItem;
import com.viettel.mbccs.screen.branches.adapter.BranchesListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minhnx on 5/19/17.
 */

public class SearchBranchPresenter implements SearchBranchContract.Presenter {

    private Context context;
    private SearchBranchContract.ViewModel viewModel;
    private BranchesListAdapter brachesAdapter;

    public ObservableField<String> documentId;
    public ObservableField<String> isdn;
    public ObservableBoolean searchFound;
    public ObservableField<BranchesListAdapter> branchesListAdapter;

    public SearchBranchPresenter(Context context, final SearchBranchContract.ViewModel viewModel){
        this.context = context;
        this.viewModel = viewModel;

        documentId = new ObservableField<>();
        isdn = new ObservableField<>();
        branchesListAdapter = new ObservableField<>();
        searchFound = new ObservableBoolean(true);
        brachesAdapter = new BranchesListAdapter(context, new ArrayList<BranchItem>());
        brachesAdapter.setOnItemClickListener(new BranchesListAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, BranchItem item) {
                viewModel.onPrepareUpdateBranch(item);
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
    public void searchDocument() {

        try {

            if((documentId.get() == null || "".equals(documentId.get().trim()))
                    && (isdn.get() == null || "".equals(isdn.get().trim()))){

                viewModel.showError(context.getString(R.string.branches_error_search_key_required));

                return;
            }

            List<BranchItem> items = new ArrayList<>();

//            BranchItem branch = new BranchItem();
//            branch.setDocumentNo("1111111");
//            branch.setChannelCode("CH11");
//
//            items.add(branch);
//
//            branch = new BranchItem();
//            branch.setDocumentNo("1111112");
//            branch.setChannelCode("CH12");
//
//            items.add(branch);
//
//            branch = new BranchItem();
//            branch.setDocumentNo("1111113");
//            branch.setChannelCode("CH13");
//
//            items.add(branch);
//
//            branch = new BranchItem();
//            branch.setDocumentNo("1111114");
//            branch.setChannelCode("CH14");
//
//            items.add(branch);

            brachesAdapter.setBranchItems(items);

            branchesListAdapter.set(brachesAdapter);

            if(brachesAdapter.getItemCount() > 0){
                searchFound.set(true);

                viewModel.onDocumentFound(documentId.get());
            }else{
                searchFound.set(false);

                viewModel.onDocumentNotFound(documentId.get());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addNewDocument(){
        try{
            viewModel.onAddNewDocument();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
