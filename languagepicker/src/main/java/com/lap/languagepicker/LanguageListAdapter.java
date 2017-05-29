package com.lap.languagepicker;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

/**
 * Created by HuyQuyet on 5/25/17.
 */

public class LanguageListAdapter extends RecyclerView.Adapter<LanguageListAdapter.ViewHolder> {
    private List<Language> languageList;
    private LanguageListAdapterCallback listener;

    public LanguageListAdapter(List<Language> list) {
        this.languageList = list;
    }

    @Override
    public LanguageListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_language, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LanguageListAdapter.ViewHolder holder, final int position) {
        holder.title.setText(languageList.get(position).getName());
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(languageList.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return languageList == null ? 0 : languageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.row_language_title);
        }
    }

    public interface LanguageListAdapterCallback {
        void onItemClick(Language language);
    }

    public void setLanguageListAdapterCallback(LanguageListAdapterCallback listener) {
        this.listener = listener;
    }
}
