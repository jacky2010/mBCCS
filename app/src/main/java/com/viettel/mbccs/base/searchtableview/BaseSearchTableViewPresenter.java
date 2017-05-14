package com.viettel.mbccs.base.searchtableview;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import de.codecrafters.tableview.TableDataAdapter;
import de.codecrafters.tableview.TableHeaderAdapter;
import de.codecrafters.tableview.model.TableColumnModel;

/**
 * Created by Anh Vu Viet on 5/13/2017.
 */

public class BaseSearchTableViewPresenter implements BaseSearchTableViewContract.Presenter {

    public ObservableInt columnCount = new ObservableInt();

    public ObservableField<TableColumnModel> columnModel = new ObservableField<>();

    public ObservableField<TableDataAdapter> dataAdapter = new ObservableField<>();

    public ObservableField<TableHeaderAdapter> headerAdapter = new ObservableField<>();

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }
}
