package com.viettel.mbccs.screen.config;

import android.databinding.DataBindingUtil;
import com.google.gson.Gson;
import com.mukesh.countrypicker.Country;
import com.mukesh.countrypicker.CountryPicker;
import com.mukesh.countrypicker.CountryPickerListener;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.databinding.ActivityConfigBinding;
import java.util.List;

public class ConfigActivity extends BaseDataBindActivity<ActivityConfigBinding, ConfigPresenter>
        implements ConfigContract.View {
    private static final String COUNTRY_PICKER = "COUNTRY_PICKER";
    private static final String LANGUAGE_PICKER = "LANGUAGE_PICKER";

    private List<Country> countries;
    private List<Country> languageList;

    @Override
    public void onBackPressed() {
        onFinish();
    }

    @Override
    protected ActivityConfigBinding initBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_config);
    }

    @Override
    protected void initData() {
        mPresenter = new ConfigPresenter(this, this);
        mBinding.setPresenter(mPresenter);
        mPresenter.subscribe();
        mBinding.spinnerTimeSyncBccs.setOnItemSelectedListener(mPresenter);
        mBinding.spinnerTimeSyncBccs.setSelection(mPresenter.getPositionDefaultSpinnerSync());
    }

    @Override
    public void setPresenter(ConfigContract.Presenter presenter) {
        // TODO: 5/19/17
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
        final CountryPicker picker =
                CountryPicker.newInstance(getString(R.string.config_select_language),
                        new Gson().toJson(languageList));  // dialog title
        picker.setListener(new CountryPickerListener() {
            @Override
            public void onSelectCountry(Country country) {
                mPresenter.setLanguage(country);
                picker.dismiss();
            }
        });
        picker.show(getSupportFragmentManager(), LANGUAGE_PICKER);
    }
}
