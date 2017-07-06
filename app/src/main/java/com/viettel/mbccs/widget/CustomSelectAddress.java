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
import com.viettel.mbccs.data.model.Area;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.databinding.LayoutSelectAddressBinding;
import com.viettel.mbccs.utils.StringUtils;
import com.viettel.mbccs.widget.fragment.DialogInputAddress;
import com.viettel.mbccs.widget.model.AddressApp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuyQuyet on 5/30/17.
 */
@BindingMethods({
        @BindingMethod(type = CustomSelectAddress.class, attribute = "area", method = "setProvideAddress")
})
public class CustomSelectAddress extends LinearLayout {
    private Context context;
    private LayoutSelectAddressBinding binding;
    private UserRepository repository;
    private List<Area> areaProvinceList;
    private List<Area> areaDistrictList;
    private List<Area> areaPrecinctList;

    private List<String> dataProvince;
    private List<String> dataDistrict;
    private List<String> dataPrecinct;

    private String txtAddress;

    private int positionProvince;
    private int positionDistrict;
    private int positionPrecinct;
    private String address;

    public ObservableField<ArrayAdapter<String>> adapterProvince;
    public ObservableField<ArrayAdapter<String>> adapterDistrict;
    public ObservableField<ArrayAdapter<String>> adapterPrecinct;
    public ObservableField<String> txtAddressDetail;

    private AddressApp provideAddress;

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
        provideAddress = new AddressApp();
        initView(context, attrs);
    }

    private void initView(final Context context, AttributeSet attrs) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.layout_select_address, this, true);
        binding.setPresenter(this);
        address = StringUtils.isEmpty(txtAddress) ? "" : txtAddress + ", ";

        adapterProvince = new ObservableField<>();
        adapterDistrict = new ObservableField<>();
        adapterPrecinct = new ObservableField<>();
        txtAddressDetail = new ObservableField<>();

        dataProvince = new ArrayList<>();
        dataDistrict = new ArrayList<>();
        dataPrecinct = new ArrayList<>();

        areaProvinceList = new ArrayList<>();
        areaDistrictList = new ArrayList<>();
        areaPrecinctList = new ArrayList<>();

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


        binding.spinnerLayoutSelectProvince.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position,
                            long id) {
                        if (positionProvince == position && positionProvince != 0) return;

                        positionProvince = position;

                        areaDistrictList.clear();
                        dataDistrict.clear();
                        adapterDistrict.get().notifyDataSetChanged();
                        areaPrecinctList.clear();
                        dataPrecinct.clear();
                        adapterPrecinct.get().notifyDataSetChanged();

                        getDataAreaDistrict(areaProvinceList.get(position).getProvince());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

        binding.spinnerLayoutSelectDistrict.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position,
                            long id) {
                        if (positionDistrict == position && positionDistrict != 0) return;
                        positionDistrict = position;

                        areaPrecinctList.clear();
                        dataPrecinct.clear();
                        adapterPrecinct.get().notifyDataSetChanged();

                        getDataAreaPrecinct(
                                areaDistrictList.get(position).getProvince() + areaDistrictList.get(
                                        position).getDistrict());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

        binding.spinnerLayoutSelectPrecinct.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position,
                            long id) {
                        if (positionPrecinct == position && positionPrecinct != 0) return;
                        positionPrecinct = position;
                        txtAddressDetail.set(address
                                + ", "
                                + areaPrecinctList.get(position).getName()
                                + ", "
                                + areaDistrictList.get(positionDistrict).getName()
                                + ", "
                                + areaProvinceList.get(positionProvince).getName());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
    }

    private void getDataAreaProvince() {
        if (areaProvinceList != null && areaProvinceList.size() != 0) areaProvinceList.clear();
        areaProvinceList = repository.getListAreaProvince();
        if (dataProvince.size() != 0) dataProvince.clear();
        for (Area p : areaProvinceList) {
            dataProvince.add(p.getName());
        }
        adapterProvince.get().notifyDataSetChanged();

        if (StringUtils.isEmpty(provideAddress.getIdProvince())) {
            binding.spinnerLayoutSelectProvince.setSelection(0);
            return;
        }

        int position = 0;
        for (int i = 0; i < areaProvinceList.size(); i++) {
            if (!provideAddress.getIdProvince().equals(areaProvinceList.get(i).getProvince())) {
                continue;
            }
            position = i;
            provideAddress.setIdProvince("");
            break;
        }
        binding.spinnerLayoutSelectProvince.setSelection(position);
    }

    private void getDataAreaDistrict(String parentCode) {
        areaDistrictList = repository.getListDistrictByProvinceId(parentCode);
        if (dataDistrict.size() != 0) dataDistrict.clear();
        for (Area d : areaDistrictList) {
            dataDistrict.add(d.getName());
        }
        adapterDistrict.get().notifyDataSetChanged();
        positionDistrict = 0;

        if (StringUtils.isEmpty(provideAddress.getIdDistrict())) {
            binding.spinnerLayoutSelectDistrict.setSelection(positionDistrict);
            areaPrecinctList.clear();
            dataPrecinct.clear();
            adapterPrecinct.get().notifyDataSetChanged();

            getDataAreaPrecinct(
                    areaDistrictList.get(positionDistrict).getProvince() + areaDistrictList.get(
                            positionDistrict).getDistrict());

            return;
        }

        int position = 0;
        for (int i = 0; i < areaDistrictList.size(); i++) {
            if (!provideAddress.getIdDistrict().equals(areaDistrictList.get(i).getDistrict())) {
                continue;
            }
            position = i;
            provideAddress.setIdDistrict("");
            break;
        }
        if (position == 0) {
            areaPrecinctList.clear();
            dataPrecinct.clear();
            adapterPrecinct.get().notifyDataSetChanged();

            getDataAreaPrecinct(
                    areaDistrictList.get(position).getProvince() + areaDistrictList.get(position)
                            .getDistrict());
        }
        binding.spinnerLayoutSelectDistrict.setSelection(position);
    }

    private void getDataAreaPrecinct(String parentCode) {
        areaPrecinctList = repository.getListPrecinctByDistrictId(parentCode);
        for (Area p : areaPrecinctList) {
            dataPrecinct.add(p.getName());
        }
        adapterPrecinct.get().notifyDataSetChanged();

        int position = 0;
        if (!StringUtils.isEmpty(provideAddress.getIdPrecinct())) {
            for (int i = 0; i < areaPrecinctList.size(); i++) {
                if (!provideAddress.getIdPrecinct().equals(areaPrecinctList.get(i).getPrecinct())) {
                    continue;
                }
                position = i;
                provideAddress.setIdPrecinct("");
                break;
            }
        }
        if (positionPrecinct == 0) {
            txtAddressDetail.set(address
                    + ", "
                    + areaPrecinctList.get(position).getName()
                    + ", "
                    + areaDistrictList.get(positionDistrict).getName()
                    + ", "
                    + areaProvinceList.get(positionProvince).getName());
        }
        binding.spinnerLayoutSelectPrecinct.setSelection(position);
    }

    public void setProvideAddress(AddressApp address) {
        if (address == null) {
            provideAddress = new AddressApp();
        } else {
            provideAddress = address;
        }
        getDataAreaProvince();
        if (address == null || StringUtils.isEmpty(address.getAddress())) return;
        String[] arr = address.getAddress().trim().split(",");
        if (arr.length < 3) return;
        for (int i = 0; i < arr.length - 3; i++) {
            txtAddress = txtAddress + arr[i] + ", ";
        }
    }

    private Area getAreaProvince() {
        return areaProvinceList.size() != 0 ? areaProvinceList.get(positionProvince) : null;
    }

    private Area getAreaDistrict() {
        return areaDistrictList.size() != 0 ? areaDistrictList.get(positionDistrict) : null;
    }

    private Area getAreaPrecinct() {
        return areaPrecinctList.size() != 0 ? areaPrecinctList.get(positionPrecinct) : null;
    }

    public AddressApp getAddress() {
        AddressApp result = new AddressApp();
        result.setAreaProvince(getAreaProvince());
        result.setAreaDistrict(getAreaDistrict());
        result.setAreaPrecinct(getAreaPrecinct());
        result.setAddress(txtAddressDetail.get());
        return  result;
    }

    public void enterAddress() {
        String province = getAreaProvince() == null ? "" : (", " + getAreaProvince().getName());
        String district = getAreaDistrict() == null ? "" : (", " + getAreaDistrict().getName());
        String precinct = getAreaPrecinct() == null ? "" : (", " + getAreaPrecinct().getName());

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
