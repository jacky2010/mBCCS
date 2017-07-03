package com.viettel.mbccs.screen.config;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import com.google.gson.Gson;
import com.lap.languagepicker.Language;
import com.lap.languagepicker.LanguagePicker;
import com.lap.languagepicker.LanguagePickerListener;
import com.mukesh.countrypicker.Country;
import com.mukesh.countrypicker.CountryPicker;
import com.mukesh.countrypicker.CountryPickerListener;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.databinding.ActivityConfigBinding;
import com.viettel.mbccs.screen.splash.SplashActivity;
import java.util.List;
import java.util.Locale;

public class ConfigActivity extends BaseDataBindActivity<ActivityConfigBinding, ConfigPresenter>
        implements ConfigContract.View {
    private static final String COUNTRY_PICKER = "COUNTRY_PICKER";
    private static final String LANGUAGE_PICKER = "LANGUAGE_PICKER";

    private List<Country> countries;
    private List<Language> languageList;

    @Override
    public void onBackPressed() {
        onFinish();
    }

    @Override
    protected int getIdLayout() {
        return R.layout.activity_config;
    }

    @Override
    protected void initData() {
        mPresenter = new ConfigPresenter(this, this);
        mBinding.setPresenter(mPresenter);
        mPresenter.subscribe();
        mPresenter.setPositionSpinner(mPresenter.getPositionDefaultSpinnerSync());

        mBinding.spinnerTimeSyncBccs.setOnItemSelectedListener(mPresenter);
    }


    @Override
    public void showLoading() {
        showLoadingDialog();
    }

    @Override
    public void hideLoading() {
        hideLoadingDialog();
    }

    @Override
    public void onFinish() {
        finish();
    }

    @Override
    public void selectCountry() {
        countries = mPresenter.getListCountry();
        final CountryPicker picker =
                CountryPicker.newInstance(getString(R.string.config_select_country),
                        new Gson().toJson(countries));  // dialog title
        picker.setListener(new CountryPickerListener() {
            @Override
            public void onSelectCountry(Country country) {
                mPresenter.setCounty(country);
                picker.dismiss();
            }
        });
        picker.show(getSupportFragmentManager(), COUNTRY_PICKER);
    }

    @Override
    public void selectLanguage() {
        languageList = mPresenter.getListLanguage();
        final LanguagePicker picker =
                LanguagePicker.newInstance(getString(R.string.config_select_language),
                        new Gson().toJson(languageList));  // dialog title
        picker.setListener(new LanguagePickerListener() {
            @Override
            public void onSelectLanguage(Language language) {
                mPresenter.setLanguage(language);
                picker.dismiss();
            }
        });
        picker.show(getSupportFragmentManager(), LANGUAGE_PICKER);
    }

    @Override
    public void changeLanguage(String language) {
        Locale myLocale = new Locale(language);
        Resources res = getResources();
        //        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.setLocale(myLocale);
        //        res.updateConfiguration(conf, dm);
        createConfigurationContext(conf);
        Intent refresh = new Intent(this, SplashActivity.class);
        startActivity(refresh);
        finishAffinity();
    }
}
