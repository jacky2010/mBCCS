package com.viettel.mbccs.screen.config;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import com.lap.languagepicker.Language;
import com.mukesh.countrypicker.Country;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.utils.Common;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by HuyQuyet on 5/19/17.
 */

public class ConfigPresenter
        implements ConfigContract.Presenter, AdapterView.OnItemSelectedListener {
    private Context context;
    private ConfigContract.View configView;
    private UserRepository userRepository;
    private String codeCurrentCountry;
    private String codeCurrentLanguage;
    private List<String> dataSpinnerTimeSyncBCCS;

    public ObservableInt imageCurrentCountry;
    public ObservableField<String> nameCurrentCountry;
    public ObservableField<String> nameCurrentLanguage;
    public ObservableBoolean statusNotification;
    public ObservableBoolean statusDisplayDashBoard;
    public ObservableBoolean statusSyncBCCS;
    public ObservableField<ArrayAdapter<String>> spinnerAdapterTimeSyncBCCS;
    public ObservableInt positionSpinner;

    public ConfigPresenter(Context context, ConfigContract.View configView) {
        this.context = context;
        this.configView = configView;
        userRepository = UserRepository.getInstance();

        imageCurrentCountry = new ObservableInt();
        nameCurrentCountry = new ObservableField<>();
        nameCurrentLanguage = new ObservableField<>();
        statusNotification = new ObservableBoolean();
        statusDisplayDashBoard = new ObservableBoolean();
        statusSyncBCCS = new ObservableBoolean();
        spinnerAdapterTimeSyncBCCS = new ObservableField<>();
        positionSpinner = new ObservableInt();

        dataSpinnerTimeSyncBCCS = new ArrayList<>();
    }

    public void onCancel() {
        configView.onFinish();
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.switch_notification:
                userRepository.saveStatusNotification(isChecked);
                statusNotification.set(isChecked);
                break;
            case R.id.switch_display_dash:
                userRepository.saveDisplayDashBoard(isChecked);
                statusDisplayDashBoard.set(isChecked);
                break;
            case R.id.switch_sync_bccs:
                userRepository.saveSyncBCCS(isChecked);
                statusSyncBCCS.set(isChecked);
                break;
        }
    }

    public void logout() {
        Common.logout(context);
    }

    public void clickSelectLanguage() {
        configView.selectLanguage();
    }

    public void clickSelectCountry() {
        configView.selectCountry();
    }

    @Override
    public void subscribe() {
        codeCurrentCountry = userRepository.getCountryFromSharePrefs();
        codeCurrentLanguage = userRepository.getLanguageFromSharePrefs();

        imageCurrentCountry.set(Country.getCountryByISO(codeCurrentCountry).getFlag());
        nameCurrentCountry.set(String.format(context.getString(R.string.config_name_country),
                Country.getCountryByISO(codeCurrentCountry).getName()));

        nameCurrentLanguage.set(Language.getLanguageByCode(codeCurrentLanguage).getName());
        statusNotification.set(userRepository.getStatusNotification());
        statusDisplayDashBoard.set(userRepository.getDisplayDashBoard());
        statusSyncBCCS.set(userRepository.getSyncBCCS());

        for (int i = 60; i > 0; i--) {
            dataSpinnerTimeSyncBCCS.add(i + "s");
        }
        spinnerAdapterTimeSyncBCCS.set(
                new ArrayAdapter<>(context, R.layout.item_spinner_time_sync_bccs,
                        dataSpinnerTimeSyncBCCS));
        spinnerAdapterTimeSyncBCCS.get()
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String timeSync = dataSpinnerTimeSyncBCCS.get(position);
        String time = timeSync.substring(0, timeSync.length() - 1);
        userRepository.saveTimeSyncBCCS(Integer.parseInt(time));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void setCounty(Country country) {
        codeCurrentCountry = country.getCode();
        nameCurrentCountry.set(context.getString(R.string.config_name_country,
                Country.getCountryByISO(codeCurrentCountry).getName()));
        imageCurrentCountry.set(country.getFlag());
        userRepository.saveCountryToSharePrefs(codeCurrentCountry);
    }

    public void setLanguage(Language language) {
        codeCurrentLanguage = language.getCode();
        nameCurrentLanguage.set(language.getName());
        userRepository.saveLanguageToSharePrefs(codeCurrentLanguage);
        configView.changeLanguage(language.getCode());
    }

    public List<Country> getListCountry() {
        List<Country> countryList = new ArrayList<>();
        Country countryVN = Country.getCountryByISO("VN");
        Country countryUS = Country.getCountryByISO("US");
        countryList.add(countryUS);
        countryList.add(countryVN);
        Country country = Country.getCountryFromSIM(context);
        if (country != null && !country.getCode().equals(countryUS.getCode()) && !country.getCode()
                .equals(countryVN.getCode())) {
            countryList.add(country);
        }
        return countryList;
    }

    public List<Language> getListLanguage() {
        List<Language> languageList = new ArrayList<>();
        Language languageVn = Language.getLanguageByCode("vi");
        Language languageEn = Language.getLanguageByCode("en");
        languageList.add(languageVn);
        languageList.add(languageEn);

        Locale locale = Locale.getDefault();

        if (locale != null
                && !locale.getLanguage().equals(languageVn.getCode())
                && !locale.getLanguage().equals(languageEn.getCode())) {

            languageList.add(Language.getLanguageByCode(locale.getLanguage()));
        }
        return languageList;
    }

    public int getPositionDefaultSpinnerSync() {
        String dataDefault = String.valueOf(userRepository.getTimeSyncBCCS());
        for (int i = 0; i < dataSpinnerTimeSyncBCCS.size(); i++) {
            if (dataSpinnerTimeSyncBCCS.get(i).equals(dataDefault + "s")) return i;
        }
        return 0;
    }

    public void setPositionSpinner(int position) {
        positionSpinner.set(position);
    }
}
