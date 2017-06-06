package com.viettel.mbccs.widget;

import android.content.Context;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.District;
import com.viettel.mbccs.data.model.Precinct;
import com.viettel.mbccs.data.model.Province;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.databinding.LayoutSelectAddressBinding;
import com.viettel.mbccs.utils.StringUtils;
import com.viettel.mbccs.widget.fragment.DialogInputAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuyQuyet on 5/30/17.
 */
@BindingMethods({
        @BindingMethod(type = CustomSelectAddress.class, attribute = "idProvince", method = "setCurrentProvince"),
        @BindingMethod(type = CustomSelectAddress.class, attribute = "idDistrict", method = "setCurrentDistrict"),
        @BindingMethod(type = CustomSelectAddress.class, attribute = "idPrecinct", method = "setCurrentPrecinct"),
        @BindingMethod(type = CustomSelectAddress.class, attribute = "address", method = "setCurrentAddress")
})
public class CustomSelectAddress extends LinearLayout
        implements AdapterView.OnItemSelectedListener {
    private Context context;
    private LayoutSelectAddressBinding binding;
    private UserRepository repository;
    private List<Province> provinceList;
    private List<District> districtList;
    private List<Precinct> precinctList;

    private List<String> dataProvince;
    private List<String> dataDistrict;
    private List<String> dataPrecinct;

    private String setProvinceId;
    private String setDistrictId;
    private String setPrecinctId;
    private String setAddress;
    private String txtAddress;

    private int positionProvince;
    private int positionDistrict;
    private int positionPrecinct;

    public ObservableField<ArrayAdapter<String>> adapterProvince;
    public ObservableField<ArrayAdapter<String>> adapterDistrict;
    public ObservableField<ArrayAdapter<String>> adapterPrecinct;
    public ObservableField<String> txtAddressDetail;

    public CustomSelectAddress(@NonNull Context context) {
        this(context, null);
    }

    public CustomSelectAddress(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomSelectAddress(@NonNull Context context, @Nullable AttributeSet attrs,
            @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        repository = UserRepository.getInstance();
        this.context = context;
        initView(context, attrs);
    }

    private void initView(final Context context, AttributeSet attrs) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.layout_select_address, this, true);
        binding.setPresenter(this);

        binding.spinnerLayoutSelectProvince.setOnItemSelectedListener(this);
        binding.spinnerLayoutSelectDistrict.setOnItemSelectedListener(this);
        binding.spinnerLayoutSelectPrecinct.setOnItemSelectedListener(this);

        adapterProvince = new ObservableField<>();
        adapterDistrict = new ObservableField<>();
        adapterPrecinct = new ObservableField<>();
        txtAddressDetail = new ObservableField<>();

        dataProvince = new ArrayList<>();
        dataDistrict = new ArrayList<>();
        dataPrecinct = new ArrayList<>();

        provinceList = new ArrayList<>();
        districtList = new ArrayList<>();
        precinctList = new ArrayList<>();

        adapterProvince.set(
                new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, dataProvince));
        adapterProvince.get()
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapterDistrict.set(
                new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, dataDistrict));
        adapterDistrict.get()
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapterPrecinct.set(
                new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, dataPrecinct));
        adapterPrecinct.get()
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        getDataProvince();
    }

    private void getDataProvince() {
        provinceList = repository.getListProvince();
        for (Province p : provinceList) {
            dataProvince.add(p.getName());
        }
        adapterProvince.get().notifyDataSetChanged();
        if (StringUtils.isEmpty(setProvinceId)) {
            binding.spinnerLayoutSelectProvince.setSelection(0);
            return;
        }

        int position = 0;
        for (int i = 0; i < provinceList.size(); i++) {
            if (!setProvinceId.equals(provinceList.get(i).getProvinceId())) continue;
            position = i;
            break;
        }
        binding.spinnerLayoutSelectProvince.setSelection(position);
    }

    private void getDataDistrict(String idProvince) {
        districtList = repository.getListDistrictByProvinceId(idProvince);
        for (District d : districtList) {
            dataDistrict.add(d.getName());
        }
        adapterDistrict.get().notifyDataSetChanged();

        if (StringUtils.isEmpty(setDistrictId)) {
            binding.spinnerLayoutSelectDistrict.setSelection(0);
            return;
        }

        int position = 0;
        for (int i = 0; i < districtList.size(); i++) {
            if (!setDistrictId.equals(districtList.get(i).getProvinceId())) continue;
            position = i;
            break;
        }
        binding.spinnerLayoutSelectDistrict.setSelection(position);
    }

    private void getDataPrecinct(String idDistrict) {
        precinctList = repository.getListPrecinctByDistrictId(idDistrict);
        for (Precinct p : precinctList) {
            dataPrecinct.add(p.getName());
        }
        adapterPrecinct.get().notifyDataSetChanged();

        int position = 0;
        if (!StringUtils.isEmpty(setPrecinctId)) {
            for (int i = 0; i < precinctList.size(); i++) {
                if (!setPrecinctId.equals(precinctList.get(i).getProvinceId())) continue;
                position = i;
                break;
            }
            binding.spinnerLayoutSelectPrecinct.setSelection(position);
        }
        binding.spinnerLayoutSelectPrecinct.setSelection(position);

        if (!StringUtils.isEmpty(setAddress)) {
            txtAddressDetail.set(setAddress);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String address = StringUtils.isEmpty(txtAddress) ? "" : txtAddress + ", ";
        switch (parent.getId()) {
            case R.id.spinner_layout_select_province:
                if (positionProvince == position && positionProvince != 0) return;

                positionProvince = position;
                txtAddressDetail.set(address + provinceList.get(position).getName());

                districtList.clear();
                adapterDistrict.get().notifyDataSetChanged();
                precinctList.clear();
                adapterPrecinct.get().notifyDataSetChanged();

                getDataDistrict(provinceList.get(position).getProvinceId());

                break;
            case R.id.spinner_layout_select_district:
                if (positionDistrict == position && positionDistrict != 0) return;

                positionDistrict = position;
                txtAddressDetail.set(address
                        + ", "
                        + districtList.get(position).getName()
                        + ", "
                        + provinceList.get(positionProvince).getName());

                precinctList.clear();
                adapterPrecinct.get().notifyDataSetChanged();

                getDataPrecinct(districtList.get(position).getDistrictId());
                break;
            case R.id.spinner_layout_select_precinct:
                if (positionPrecinct == position && positionPrecinct != 0) return;
                positionPrecinct = position;
                txtAddressDetail.set(address
                        + ", "
                        + precinctList.get(position).getName()
                        + ", "
                        + districtList.get(positionDistrict).getName()
                        + ", "
                        + provinceList.get(positionProvince).getName());

                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void setCurrentProvince(String idProvince) {
        setProvinceId = idProvince;
    }

    public void setCurrentDistrict(String idDistrict) {
        setDistrictId = idDistrict;
    }

    public void setCurrentPrecinct(String idPrecinct) {
        setPrecinctId = idPrecinct;
    }

    public void setCurrentAddress(String address) {
        setAddress = address;
        if (StringUtils.isEmpty(setAddress)) return;
        String[] arr = setAddress.trim().split(",");
        if (arr.length < 3) return;
        for (int i = 0; i < arr.length - 3; i++) {
            txtAddress = txtAddress + arr[i] + ", ";
        }
    }

    public Province getProvince() {
        return provinceList.size() != 0 ? provinceList.get(positionProvince) : null;
    }

    public District getDistrict() {
        return districtList.size() != 0 ? districtList.get(positionDistrict) : null;
    }

    public Precinct getPrecinct() {
        return precinctList.size() != 0 ? precinctList.get(positionPrecinct) : null;
    }

    public String getAddress() {
        return txtAddressDetail.get();
    }

    public void enterAddress() {
        String province = getProvince() == null ? "" : (", " + getProvince().getName());
        String district = getDistrict() == null ? "" : (", " + getDistrict().getName());
        String precinct = getPrecinct() == null ? "" : (", " + getPrecinct().getName());

        final DialogInputAddress dialog =
                DialogInputAddress.newInstance(txtAddress, precinct, district, province);
        dialog.setDialogInputAddressCallback(new DialogInputAddress.DialogInputAddressCallback() {
            @Override
            public void onResultCallback(String address, String precinct, String district,
                    String province) {
                txtAddress = address;
                txtAddressDetail.set(
                        address + (StringUtils.isEmpty(precinct) ? "" : precinct) + (
                                StringUtils.isEmpty(district) ? "" : district) + (
                                StringUtils.isEmpty(province) ? "" : province));
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.show(((AppCompatActivity) context).getSupportFragmentManager(), "");
    }
}
