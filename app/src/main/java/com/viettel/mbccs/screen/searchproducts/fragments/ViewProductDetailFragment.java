package com.viettel.mbccs.screen.searchproducts.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindFragment;
import com.viettel.mbccs.data.model.KeyValue;
import com.viettel.mbccs.databinding.FragmentListProductsDetailBinding;
import com.viettel.mbccs.screen.common.picker.KeyValuePickerActivity;
import com.viettel.mbccs.screen.sellanypay.dialogs.DialogConfirmSellAnyPayFragment;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.variable.Constants;

import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by minhnx on 5/20/17.
 */

public class ViewProductDetailFragment extends BaseDataBindFragment<FragmentListProductsDetailBinding, ListProductsDetailPresenter>
        implements ListProductsDetailContract.ViewModel {

    private static final int GET_MANUFACTURER = 1001;
    private static final int GET_SCREEN_SIZE = GET_MANUFACTURER + 1;
    private static final int GET_CAMERA = GET_MANUFACTURER + 2;
    private static final int GET_PRICE_RANGE = GET_MANUFACTURER + 3;
    private static final int GET_MODEL = GET_MANUFACTURER + 4;
    private static final int GET_FEATURE = GET_MANUFACTURER + 5;

    private AppCompatActivity mActivity;

    public static ViewProductDetailFragment newInstance() {
        return new ViewProductDetailFragment();
    }

    @Override
    public void setPresenter(ListProductsDetailContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void initData() {
        mPresenter = new ListProductsDetailPresenter(getContext(), this);
        mBinding.setPresenter(mPresenter);

        initListeners();
    }

    @Override
    protected int getIdLayoutRes() {
        return R.layout.fragment_list_products_detail;
    }

    @Override
    protected void initView() {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initListeners() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public void goToDialogFragment(Bundle args) {
        getBaseActivity().goToDialogFragment(new DialogConfirmSellAnyPayFragment(), args);
    }

    @Override
    public void onChooseManufacturer(List<KeyValue> items) {
        try {
            Intent intent = new Intent(getActivity(), KeyValuePickerActivity.class);
            intent.putExtra(Constants.BundleConstant.ITEM_LIST,
                    GsonUtils.Object2String(items));
            intent.putExtra(Constants.BundleConstant.FORM_TYPE, getString(R.string.search_products_detail_title_manufacturer));
            startActivityForResult(intent, GET_MANUFACTURER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onChooseScreenSize(List<KeyValue> items) {
        try {
            Intent intent = new Intent(getActivity(), KeyValuePickerActivity.class);
            intent.putExtra(Constants.BundleConstant.ITEM_LIST,
                    GsonUtils.Object2String(items));
            intent.putExtra(Constants.BundleConstant.FORM_TYPE, getString(R.string.search_products_detail_title_screen_size));
            startActivityForResult(intent, GET_SCREEN_SIZE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onChooseCamera(List<KeyValue> items) {
        try {
            Intent intent = new Intent(getActivity(), KeyValuePickerActivity.class);
            intent.putExtra(Constants.BundleConstant.ITEM_LIST,
                    GsonUtils.Object2String(items));
            intent.putExtra(Constants.BundleConstant.FORM_TYPE, getString(R.string.search_products_detail_title_camera));
            startActivityForResult(intent, GET_CAMERA);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onChoosePriceRange(List<KeyValue> items) {
        try {
            Intent intent = new Intent(getActivity(), KeyValuePickerActivity.class);
            intent.putExtra(Constants.BundleConstant.ITEM_LIST,
                    GsonUtils.Object2String(items));
            intent.putExtra(Constants.BundleConstant.FORM_TYPE, getString(R.string.search_products_detail_title_price_range));
            startActivityForResult(intent, GET_PRICE_RANGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onChooseModel(List<KeyValue> items) {
        try {
            Intent intent = new Intent(getActivity(), KeyValuePickerActivity.class);
            intent.putExtra(Constants.BundleConstant.ITEM_LIST,
                    GsonUtils.Object2String(items));
            intent.putExtra(Constants.BundleConstant.FORM_TYPE, getString(R.string.search_products_detail_title_model));
            startActivityForResult(intent, GET_MODEL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onChooseFeature(List<KeyValue> items) {
        try {
            Intent intent = new Intent(getActivity(), KeyValuePickerActivity.class);
            intent.putExtra(Constants.BundleConstant.ITEM_LIST,
                    GsonUtils.Object2String(items));
            intent.putExtra(Constants.BundleConstant.FORM_TYPE, getString(R.string.search_products_detail_title_feature));
            startActivityForResult(intent, GET_FEATURE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (requestCode == GET_MANUFACTURER && resultCode == RESULT_OK) {
                KeyValue item = (KeyValue) data.getExtras()
                        .getSerializable(Constants.BundleConstant.RESULT);
                mPresenter.onGetManufacturerSuccess(item);
            } else if (requestCode == GET_SCREEN_SIZE && resultCode == RESULT_OK) {
                KeyValue item = (KeyValue) data.getExtras()
                        .getSerializable(Constants.BundleConstant.RESULT);
                mPresenter.onGetScreenSizeSuccess(item);
            } else if (requestCode == GET_CAMERA && resultCode == RESULT_OK) {
                KeyValue item = (KeyValue) data.getExtras()
                        .getSerializable(Constants.BundleConstant.RESULT);
                mPresenter.onGetCameraSuccess(item);
            } else if (requestCode == GET_PRICE_RANGE && resultCode == RESULT_OK) {
                KeyValue item = (KeyValue) data.getExtras()
                        .getSerializable(Constants.BundleConstant.RESULT);
                mPresenter.onGetPriceRangeSuccess(item);
            } else if (requestCode == GET_MODEL && resultCode == RESULT_OK) {
                KeyValue item = (KeyValue) data.getExtras()
                        .getSerializable(Constants.BundleConstant.RESULT);
                mPresenter.onGetModelSuccess(item);
            } else if (requestCode == GET_FEATURE && resultCode == RESULT_OK) {
                KeyValue item = (KeyValue) data.getExtras()
                        .getSerializable(Constants.BundleConstant.RESULT);
                mPresenter.onGetFeatureSuccess(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changeToDetailScreen(Bundle args) {

    }
}
