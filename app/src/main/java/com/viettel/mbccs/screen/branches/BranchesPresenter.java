package com.viettel.mbccs.screen.branches;

import android.content.Context;
import android.databinding.ObservableField;

import com.viettel.mbccs.data.model.BranchItem;
import com.viettel.mbccs.screen.branches.adapter.BranchesListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minhnx on 5/19/17.
 */

public class BranchesPresenter implements BranchesContract.Presenter {

    private Context context;
    private BranchesContract.ViewModel viewModel;
    private BranchesListAdapter brachesAdapter;

    public ObservableField<String> documentId;
    public ObservableField<BranchesListAdapter> branchesListAdapter;

    public BranchesPresenter(Context context, BranchesContract.ViewModel viewModel){
        this.context = context;
        this.viewModel = viewModel;

        documentId = new ObservableField<>();
        branchesListAdapter = new ObservableField<>();
        brachesAdapter = new BranchesListAdapter(context, new ArrayList<BranchItem>());
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void searchDocument() {

        List<BranchItem> items = new ArrayList<>();

        BranchItem branch = new BranchItem();
        branch.setDocumentNo("1111111");
        branch.setChannelCode("CH11");

        items.add(branch);

        branch = new BranchItem();
        branch.setDocumentNo("1111112");
        branch.setChannelCode("CH12");

        items.add(branch);

        branch = new BranchItem();
        branch.setDocumentNo("1111113");
        branch.setChannelCode("CH13");

        items.add(branch);

        branch = new BranchItem();
        branch.setDocumentNo("1111114");
        branch.setChannelCode("CH14");

        items.add(branch);

        brachesAdapter.setBranchItems(items);

        branchesListAdapter.set(brachesAdapter);
    }
}
