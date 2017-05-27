package com.lap.languagepicker;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuyQuyet on 5/25/17.
 */

public class LanguagePicker extends DialogFragment {

    private static final String ARG_DATA = "DATA";
    //  private EditText searchEditText;
    private RecyclerView languageRecyclerView;
    private LanguageListAdapter adapter;
    private List<Language> selectedLanguageList = new ArrayList<>();
    private LanguagePickerListener listener;

    /**
     * To support show as dialog
     */
    public static LanguagePicker newInstance(String dialogTitle, String data) {
        LanguagePicker picker = new LanguagePicker();
        Bundle bundle = new Bundle();
        bundle.putString("dialogTitle", dialogTitle);
        bundle.putString(ARG_DATA, data);
        picker.setArguments(bundle);
        return picker;
    }

    public LanguagePicker() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.language_picker, null);
        Bundle args = getArguments();
        if (args != null) {
            String dialogTitle = args.getString("dialogTitle");
            getDialog().setTitle(dialogTitle);

            int width = getResources().getDimensionPixelSize(R.dimen.cp_dialog_width);
            int height = getResources().getDimensionPixelSize(R.dimen.cp_dialog_height);
            getDialog().getWindow().setLayout(width, height);
        }
        Type t = new TypeToken<List<Language>>() {
        }.getType();
        selectedLanguageList = new Gson().fromJson(getArguments().getString(ARG_DATA), t);
        languageRecyclerView = (RecyclerView) view.findViewById(R.id.language_picker);

        adapter = new LanguageListAdapter(selectedLanguageList);
        adapter.setLanguageListAdapterCallback(
                new LanguageListAdapter.LanguageListAdapterCallback() {
                    @Override
                    public void onItemClick(Language language) {
                        if (listener != null) {
                            listener.onSelectLanguage(language);
                        }
                    }
                });
        languageRecyclerView.setLayoutManager(
                new LinearLayoutManager(languageRecyclerView.getContext()));
        languageRecyclerView.setHasFixedSize(true);
        languageRecyclerView.setAdapter(adapter);
        return view;
    }

    public void setListener(LanguagePickerListener listener) {
        this.listener = listener;
    }
}
