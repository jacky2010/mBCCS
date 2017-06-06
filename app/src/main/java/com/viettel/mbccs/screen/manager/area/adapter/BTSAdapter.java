package com.viettel.mbccs.screen.manager.area.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.GenericRecycleAdapter;
import com.viettel.mbccs.data.model.Area;
import com.viettel.mbccs.screen.manager.area.holder.BtsSearchHolder;

import java.util.List;

/**
 * Created by jacky on 6/6/17.
 */

public class BTSAdapter extends GenericRecycleAdapter<Area, BtsSearchHolder> {

    public BTSAdapter(List<Area> mList, Context context) {
        super(mList, context);
    }

    @Override
    public int getLayout(int viewType) {
        return R.layout.item_bts;
    }

    @Override
    protected RecyclerView.ViewHolder getHolderViewHolder(View v, int viewType) {
        return new BtsSearchHolder(v) {
            @Override
            public void onClick(View view) {
                super.onClick(view);
                mSelectedItem = this.getAdapterPosition();
                onItem(mList.get(this.getAdapterPosition()), this.getAdapterPosition());
            }
        };
    }

    @Override
    protected void onItem(Area item, int position) {
        if (mOnClickItemRecycleView != null) {
            mOnClickItemRecycleView.onClickItem(item, position);
        }
    }

    @Override
    protected void onSet(Area item, BtsSearchHolder holder, int position) {
        if (item != null) {
            holder.render(item);
        }
    }
}