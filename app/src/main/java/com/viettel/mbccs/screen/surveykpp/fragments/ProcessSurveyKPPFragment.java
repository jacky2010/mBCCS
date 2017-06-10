package com.viettel.mbccs.screen.surveykpp.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.androidadvance.androidsurvey.SurveyActivity;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindFragment;
import com.viettel.mbccs.data.model.SurveyItem;
import com.viettel.mbccs.databinding.FragmentProcessSurveyKppBinding;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.variable.Constants;

import java.io.IOException;
import java.io.InputStream;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * Created by minhnx on 5/20/17.
 */

public class ProcessSurveyKPPFragment extends BaseDataBindFragment<FragmentProcessSurveyKppBinding, ProcessSurveyKPPPresenter>
        implements ProcessSurveyKPPContract.ViewModel {

    private static final int SURVEY_REQUEST = 1001;

    private AppCompatActivity mActivity;

    public static ProcessSurveyKPPFragment newInstance() {
        return new ProcessSurveyKPPFragment();
    }

    @Override
    public void setPresenter(ProcessSurveyKPPContract.Presenter presenter) {

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
    protected void initData() {
        mPresenter = new ProcessSurveyKPPPresenter(getContext(), this);
        mBinding.setPresenter(mPresenter);

        initListeners();
    }

    private void loadSurveyItem() {
        try {

            Bundle args = getArguments();

            if (args != null) {

                SurveyItem surveyItem = GsonUtils.String2Object(args.getString(Constants.BundleConstant.ITEM_LIST), SurveyItem.class);
                if (surveyItem != null) {
                    Intent surveyIntent = new Intent(getContext(), SurveyActivity.class);
                    //you have to pass as an extra the json string.
                    surveyIntent.putExtra(SurveyActivity.DATA_SURVEY_JSON, loadSurveyJson("example_survey_1.json"));
                    surveyIntent.putExtra(SurveyActivity.DATA_IS_READ_ONLY, true);
                    startActivityForResult(surveyIntent, SURVEY_REQUEST);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String loadSurveyJson(String filename) {
        try {
            InputStream is = getContext().getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            return new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    protected int getIdLayoutRes() {
        return R.layout.fragment_process_survey_kpp;
    }

    @Override
    protected void initView() {
        loadSurveyItem();
    }

    private void initListeners() {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (AppCompatActivity) activity;
    }

    @Override
    public void showError(String message) {
        Toast.makeText(mActivity, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (requestCode == SURVEY_REQUEST) {
                if (resultCode == RESULT_OK) {
                    String answers_json = data.getExtras().getString(SurveyActivity.DATA_ANSWERS);

                    boolean isReadOnly = data.getExtras().getBoolean(SurveyActivity.DATA_IS_READ_ONLY);

                    if (!isReadOnly)
                        mPresenter.onSurveyCompleted(answers_json);
                    else
                        onBackPressed();

                } else if (resultCode == RESULT_CANCELED) {
                    mPresenter.onSurveyTerminated();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        getActivity().onBackPressed();
    }
}
