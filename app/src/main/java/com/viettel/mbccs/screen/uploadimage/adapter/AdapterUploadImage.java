package com.viettel.mbccs.screen.uploadimage.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import com.viettel.mbccs.data.model.UploadImage;
import java.util.List;

/**
 * Created by HuyQuyet on 6/8/17.
 */

public class AdapterUploadImage extends RecyclerView.Adapter<AdapterUploadImage.ViewHolder> {
    public AdapterUploadImage(List<UploadImage> uploadImageList) {

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

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
