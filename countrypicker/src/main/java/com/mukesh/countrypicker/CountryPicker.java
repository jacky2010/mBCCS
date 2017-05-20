package com.mukesh.countrypicker;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mukesh on 25/04/16.
 */
public class CountryPicker extends DialogFragment {
    private static final String ARG_DATA = "DATA";
    //  private EditText searchEditText;
    private ListView countryListView;
    private CountryListAdapter adapter;
    private List<Country> countriesList = new ArrayList<>();
    private List<Country> selectedCountriesList = new ArrayList<>();
    private CountryPickerListener listener;
    private Context context;

    /**
     * To support show as dialog
     */
    public static CountryPicker newInstance(String dialogTitle, String data) {
        CountryPicker picker = new CountryPicker();
        Bundle bundle = new Bundle();
        bundle.putString("dialogTitle", dialogTitle);
        bundle.putString(ARG_DATA, data);
        picker.setArguments(bundle);
        return picker;
    }

    public CountryPicker() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.country_picker, null);
        Bundle args = getArguments();
        if (args != null) {
            String dialogTitle = args.getString("dialogTitle");
            getDialog().setTitle(dialogTitle);

            int width = getResources().getDimensionPixelSize(R.dimen.cp_dialog_width);
            int height = getResources().getDimensionPixelSize(R.dimen.cp_dialog_height);
            getDialog().getWindow().setLayout(width, height);
        }
        Type t = new TypeToken<List<Country>>() {
        }.getType();
        selectedCountriesList = new Gson().fromJson(getArguments().getString(ARG_DATA), t);
        countryListView = (ListView) view.findViewById(R.id.country_code_picker_listview);

        adapter = new CountryListAdapter(getActivity(), selectedCountriesList);
        countryListView.setAdapter(adapter);

        countryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listener != null) {
                    Country country = selectedCountriesList.get(position);
                    listener.onSelectCountry(country);
                }
            }
        });

        return view;
    }

    public void setListener(CountryPickerListener listener) {
        this.listener = listener;
    }
}
