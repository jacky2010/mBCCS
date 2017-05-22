package com.viettel.mbccs.screen.viewwarehouse.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import com.viettel.mbccs.data.model.StockSerial;
import java.util.List;

/**
 * Created by HuyQuyet on 5/22/17.
 */

public class ViewSerialAdapter extends RecyclerView.Adapter<ViewSerialAdapter.ViewHolder> {
    private List<StockSerial> stockSerialList;

    public ViewSerialAdapter(List<StockSerial> stockSerialList) {
        this.stockSerialList = stockSerialList;
    }

    @Override
    public ViewSerialAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewSerialAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
