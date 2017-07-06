package com.viettel.mbccs.screen.connector.fragment.postpaid;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.Data;
import com.viettel.mbccs.constance.Gender;
import com.viettel.mbccs.constance.MobileType;
import com.viettel.mbccs.constance.TelServiceId;
import com.viettel.mbccs.constance.TelServiceType;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.ApDomainByType;
import com.viettel.mbccs.data.model.BusType;
import com.viettel.mbccs.data.model.Contract;
import com.viettel.mbccs.data.model.Customer;
import com.viettel.mbccs.data.model.Product;
import com.viettel.mbccs.data.model.RegType;
import com.viettel.mbccs.data.model.SubType;
import com.viettel.mbccs.data.model.Subscriber;
import com.viettel.mbccs.data.model.UserInfo;
import com.viettel.mbccs.data.source.QLKhachHangRepository;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.CheckIdNoRequest;
import com.viettel.mbccs.data.source.remote.request.ConnectSubscriberRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetApDomainByTypeRequest;
import com.viettel.mbccs.data.source.remote.request.GetListProductRequest;
import com.viettel.mbccs.data.source.remote.request.GetListRegTypeRequest;
import com.viettel.mbccs.data.source.remote.request.GetListSubTypeRequest;
import com.viettel.mbccs.data.source.remote.response.BaseErrorResponse;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetApDomainByTypeResponse;
import com.viettel.mbccs.data.source.remote.response.GetListProductResponse;
import com.viettel.mbccs.data.source.remote.response.GetListRegTypeResponse;
import com.viettel.mbccs.data.source.remote.response.GetListSubTypeResponse;
import com.viettel.mbccs.screen.connector.listener.OnPageChange;
import com.viettel.mbccs.utils.DatabaseUtils;
import com.viettel.mbccs.utils.DateUtils;
import com.viettel.mbccs.utils.PatternUtils;
import com.viettel.mbccs.utils.SpinnerAdapter;
import com.viettel.mbccs.utils.StringUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import com.viettel.mbccs.widget.model.AddressApp;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.Subscription;
import rx.functions.Func1;
import rx.functions.Func9;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by HuyQuyet on 6/4/17.
 */

public class CreateNewConnectorInformationPostpaidFragmentPresenter
        implements CreateNewConnectorInformationPostpaidFragmentContract.Presenter {

    private Context context;
    private CreateNewConnectorInformationPostpaidViewModel viewModel;
    private CreateNewConnectorInformationPostpaidFragmentContract.ViewFragment1 createNewView1;
    private CreateNewConnectorInformationPostpaidFragmentContract.ViewFragment2 createNewView2;
    private CreateNewConnectorInformationPostpaidFragmentContract.ViewFragment3 createNewView3;
    private QLKhachHangRepository qlKhachHangRepository;
    private UserRepository userRepository;
    private CompositeSubscription subscriptions;
    private OnPageChange onPageChange;
    private String stringError;
    private String stringLoadDataError;

    private Customer customer;
    private Contract contract;
    private CheckIdNoRequest checkIdNoRequest;

    private Customer customerCurrent;
    private Subscriber subscriberCurrent;
    private Contract contractCurrent;
    private int contractLoaiKhachHang;

    //region Fragment 1
    /**
     * Fragment 1
     */
    // Customer
    private SpinnerAdapter<BusType> customerAdapterSpinnerCustomerType;
    private List<BusType> customerDataAdapterSpinnerCustomerType;
    private int customerPositionAdapterSpinnerCustomerType;

    private AddressApp customerArea1;

    private SpinnerAdapter<ApDomainByType> registerAdapterSpinnerPassportType;
    private List<ApDomainByType> registerDataSpinnerPassportType;
    private int registerPositionSpinnerPassportType;

    private Bitmap customerCurrentImageFront;
    private Bitmap customerCurrentImageBackside;
    private Bitmap customerCurrentImagePortrait;

    //endregion Fragment 1

    //region Fragment 2
    /**
     * Fragment 2
     */

    private SpinnerAdapter<Data.DataField> subscriberAdapterSpinner2DichVu;
    private SpinnerAdapter<Product> subscriberAdapterSpinner2GoiCuoc;
    private SpinnerAdapter<SubType> subscriberAdapterSpinner2LoaiThueBao;
    private SpinnerAdapter<RegType> subscriberAdapterSpinner2HTHoaMang;
    private SpinnerAdapter<RegType> subscriberAdapterSpinner2KhuyenMai;
    private SpinnerAdapter<RegType> subscriberAdapterSpinner2DatCoc;
    private SpinnerAdapter<RegType> subscriberAdapterSpinner2HanMuc;

    private List<Data.DataField> subscriberDataSpinner2DichVu;
    private List<Product> subscriberDataSpinner2GoiCuoc;
    private List<SubType> subscriberDataSpinner2LoaiThueBao;
    private List<RegType> subscriberDataSpinner2HTHoaMang;
    private List<RegType> subscriberDataSpinner2KhuyenMai;
    private List<RegType> subscriberDataSpinner2DatCoc;
    private List<RegType> subscriberDataSpinner2HanMuc;

    private int subscriberPositionSpinner2DichVu;
    private int subscriberPositionSpinner2GoiCuoc;
    private int subscriberPositionSpinner2LoaiThueBao;
    private int subscriberPositionSpinner2HTHoaMang;
    private int subscriberPositionSpinner2KhuyenMai;
    private int subscriberPositionSpinner2DatCoc;
    private int subscriberPositionSpinner2HanMuc;

    private AddressApp subscriberArea2;
    //endregion Fragment 2

    //region Fragment 3
    /**
     * Fragment 3
     */

    private SpinnerAdapter<RegType> contractAdapterSpinner3TypeContract;
    private SpinnerAdapter<RegType> contractAdapterSpinner3Payer;
    private SpinnerAdapter<RegType> contractAdapterSpinner3HinhThucThanhToan;
    private SpinnerAdapter<RegType> contractAdapterSpinner3ChuKyCuoc;
    private SpinnerAdapter<RegType> contractAdapterSpinner3ChiTietIn;
    private SpinnerAdapter<RegType> contractAdapterSpinner3TenNganHang;
    private SpinnerAdapter<RegType> contractAdapterSpinner3HinhThucThongBaoCuoc;

    private List<RegType> contractDataSpinner3TypeContract;
    private List<RegType> contractDataSpinner3Payer;
    private List<RegType> contractDataSpinner3HinhThucThanhToan;
    private List<RegType> contractDataSpinner3ChuKyCuoc;
    private List<RegType> contractDataSpinner3ChiTietIn;
    private List<RegType> contractDataSpinner3TenNganHang;
    private List<RegType> contractDataSpinner3HinhThucThongBaoCuoc;

    private int contractPositionSpinner3TypeContract;
    private int contractPositionSpinner3Payer;
    private int contractPositionSpinner3HinhThucThanhToan;
    private int contractPositionSpinner3ChuKyCuoc;
    private int contractPositionSpinner3ChiTietIn;
    private int contractPositionSpinner3TenNganHang;
    private int contractPositionSpinner3HinhThucThongBaoCuoc;

    private AddressApp area3;
    //endregion Fragment 3

    //region Constructor - set View
    public CreateNewConnectorInformationPostpaidFragmentPresenter(Context context,
            CreateNewConnectorInformationPostpaidViewModel viewModel, Customer customer,
            Contract contract, CheckIdNoRequest checkIdNoRequest) {
        this.context = context;
        this.viewModel = viewModel;
        this.customer = customer;
        this.contract = contract;
        this.checkIdNoRequest = checkIdNoRequest;
        qlKhachHangRepository = QLKhachHangRepository.getInstance();
        userRepository = UserRepository.getInstance();
        subscriptions = new CompositeSubscription();
        stringError = context.getString(R.string.input_empty);
        stringLoadDataError = context.getString(R.string.error_load_data);
    }

    public void setView1(CreateNewConnectorInformationPostpaidFragmentContract.ViewFragment1 view) {
        this.createNewView1 = view;
        this.createNewView1.setPresenter(this);
        this.createNewView1.setViewModel(viewModel);
    }

    public void setView2(CreateNewConnectorInformationPostpaidFragmentContract.ViewFragment2 view) {
        this.createNewView2 = view;
        this.createNewView2.setPresenter(this);
        this.createNewView2.setViewModel(viewModel);
    }

    public void setView3(CreateNewConnectorInformationPostpaidFragmentContract.ViewFragment3 view) {
        this.createNewView3 = view;
        this.createNewView3.setPresenter(this);
        this.createNewView3.setViewModel(viewModel);
    }

    @Override
    public void subscribe() {
    }

    @Override
    public void unSubscribe() {
        subscriptions.clear();
    }

    //endregion Constructor - set View

    void loadDataCreateBaseView() {
        createNewView1.showLoading();
        Subscription subscription = observableDataSpinnerBaseView().subscribe(
                new MBCCSSubscribe<DataSpinnerBaseView>() {
                    @Override
                    public void onSuccess(DataSpinnerBaseView object) {
                        subscriberDataSpinner2GoiCuoc =
                                object.getSubscriberDataSpinner2GoiCuoc().getListProduct();
                        subscriberDataSpinner2LoaiThueBao =
                                object.getSubscriberDataSpinner2LoaiThueBao().getSubTypeList();
                        subscriberDataSpinner2KhuyenMai =
                                object.getSubscriberDataSpinner2KhuyenMai().getRegTypeList();

                        subscriberDataSpinner2DichVu = Data.connectorTelServiceType();

                        contractDataSpinner3TypeContract =
                                object.getContractDataSpinner3TypeContract().getRegTypeList();
                        contractDataSpinner3Payer =
                                object.getContractDataSpinner3Payer().getRegTypeList();
                        contractDataSpinner3HinhThucThanhToan =
                                object.getContractDataSpinner3HinhThucThanhToan().getRegTypeList();
                        contractDataSpinner3ChuKyCuoc =
                                object.getContractDataSpinner3ChuKyCuoc().getRegTypeList();
                        contractDataSpinner3ChiTietIn =
                                object.getContractDataSpinner3ChiTietIn().getRegTypeList();
                        contractDataSpinner3HinhThucThongBaoCuoc =
                                object.getContractDataSpinner3HinhThucThongBaoCuoc()
                                        .getRegTypeList();
                        setDataCreateView();
                    }

                    @Override
                    public void onError(BaseException error) {
                        createNewView1.hideLoading();
                        createNewView1.loadDataSpinnerError(error);
                    }
                });
        subscriptions.add(subscription);
    }

    private void setDataCreateView() {
        setDataCreateView1();
        setDataCreateView2();
        setDataCreateView3();
        createNewView1.hideLoading();
    }

    private void setDataCreateView1() {
        customerDataAdapterSpinnerCustomerType = new ArrayList<>();
        BusType busTypeCN = new BusType();
        busTypeCN.setBusType("0");
        busTypeCN.setBusTypeId(0);
        busTypeCN.setName("Cá Nhân");
        BusType busTypeDN = new BusType();
        busTypeDN.setBusType("1");
        busTypeDN.setBusTypeId(1);
        busTypeDN.setName("Doanh Nghiệp");
        customerDataAdapterSpinnerCustomerType.add(busTypeCN);
        customerDataAdapterSpinnerCustomerType.add(busTypeDN);

        customerAdapterSpinnerCustomerType =
                new SpinnerAdapter<>(context, customerDataAdapterSpinnerCustomerType);
        customerAdapterSpinnerCustomerType.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position,
                            long id) {
                        customerPositionAdapterSpinnerCustomerType = position;
                        viewModel.setContractLoaiKhachHang(
                                customerDataAdapterSpinnerCustomerType.get(position)
                                        .getBusTypeId());
                        if (customerDataAdapterSpinnerCustomerType.get(position).getBusTypeId()
                                == 1) {
                            getDataKhachHangDN();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
        viewModel.setCustomerAdapterSpinnerCustomerType(customerAdapterSpinnerCustomerType);

        setDataCustomer();

        viewModel.setCustomerMaxDateBirthday(DateUtils.maxDateBirthday());
        viewModel.setCustomerMinDateCreate(DateUtils.maxDateBirthday());
        viewModel.setRegisterDateCreate(
                DateUtils.convertToString(DateUtils.maxDateBirthday(), DateUtils.TIMEZONE_FORMAT,
                        null));
        viewModel.setRegisterMaxDateCreate(DateUtils.maxDateBirthday());
    }

    private void setDataCreateView2() {
        subscriberAdapterSpinner2DichVu =
                new SpinnerAdapter<>(context, subscriberDataSpinner2DichVu);
        subscriberAdapterSpinner2DichVu.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                subscriberPositionSpinner2DichVu = position;
                if (subscriberDataSpinner2HTHoaMang != null) {
                    subscriberDataSpinner2HTHoaMang.clear();
                    subscriberAdapterSpinner2HTHoaMang.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
                });
        viewModel.setSubscriberAdapterSpinner2DichVu(subscriberAdapterSpinner2DichVu);

        subscriberAdapterSpinner2GoiCuoc =
                new SpinnerAdapter<>(context, subscriberDataSpinner2GoiCuoc);
        subscriberAdapterSpinner2GoiCuoc.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                subscriberPositionSpinner2GoiCuoc = position;
                if (subscriberDataSpinner2HTHoaMang != null) {
                    subscriberDataSpinner2HTHoaMang.clear();
                    subscriberAdapterSpinner2HTHoaMang.notifyDataSetChanged();
                }
            }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
        });
        viewModel.setSubscriberAdapterSpinner2GoiCuoc(subscriberAdapterSpinner2GoiCuoc);

        subscriberAdapterSpinner2LoaiThueBao =
                new SpinnerAdapter<>(context, subscriberDataSpinner2LoaiThueBao);
        subscriberAdapterSpinner2LoaiThueBao.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position,
                            long id) {
                        subscriberPositionSpinner2LoaiThueBao = position;
                        getDataHTHoaMang();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
        viewModel.setSubscriberAdapterSpinner2LoaiThueBao(subscriberAdapterSpinner2LoaiThueBao);

        subscriberAdapterSpinner2KhuyenMai =
                new SpinnerAdapter<>(context, subscriberDataSpinner2KhuyenMai);
        subscriberAdapterSpinner2KhuyenMai.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position,
                            long id) {
                        subscriberPositionSpinner2KhuyenMai = position;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
        viewModel.setSubscriberAdapterSpinner2KhuyenMai(subscriberAdapterSpinner2KhuyenMai);
    }

    private void setDataCreateView3() {
        contractAdapterSpinner3TypeContract =
                new SpinnerAdapter<>(context, contractDataSpinner3TypeContract);
        contractAdapterSpinner3TypeContract.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position,
                            long id) {
                        contractPositionSpinner3TypeContract = position;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
        viewModel.setContractAdapterSpinner3TypeContract(contractAdapterSpinner3TypeContract);

        contractAdapterSpinner3Payer = new SpinnerAdapter<>(context, contractDataSpinner3Payer);
        contractAdapterSpinner3Payer.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position,
                            long id) {
                        contractPositionSpinner3Payer = position;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
        viewModel.setContractAdapterSpinner3Payer(contractAdapterSpinner3Payer);

        contractAdapterSpinner3HinhThucThanhToan =
                new SpinnerAdapter<>(context, contractDataSpinner3HinhThucThanhToan);
        contractAdapterSpinner3HinhThucThanhToan.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position,
                            long id) {
                        contractPositionSpinner3HinhThucThanhToan = position;
                        // TODO: 7/8/17  
                        if (contractDataSpinner3HinhThucThanhToan.get(position)
                                .getCode()
                                .equals("thuchi")) {
                            getDataTenNganHang();
                            viewModel.setViewThongTinNganHang(true);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
        viewModel.setContractAdapterSpinner3HinhThucThanhToan(
                contractAdapterSpinner3HinhThucThanhToan);

        contractAdapterSpinner3ChuKyCuoc =
                new SpinnerAdapter<>(context, contractDataSpinner3ChuKyCuoc);
        contractAdapterSpinner3ChuKyCuoc.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position,
                            long id) {
                        contractPositionSpinner3ChuKyCuoc = position;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
        viewModel.setContractAdapterSpinner3ChuKyCuoc(contractAdapterSpinner3ChuKyCuoc);

        contractAdapterSpinner3ChiTietIn =
                new SpinnerAdapter<>(context, contractDataSpinner3ChiTietIn);
        contractAdapterSpinner3ChiTietIn.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position,
                            long id) {
                        contractPositionSpinner3ChiTietIn = position;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
        viewModel.setContractAdapterSpinner3ChiTietIn(contractAdapterSpinner3ChiTietIn);

        contractAdapterSpinner3HinhThucThongBaoCuoc =
                new SpinnerAdapter<>(context, contractDataSpinner3HinhThucThongBaoCuoc);
        contractAdapterSpinner3HinhThucThongBaoCuoc.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position,
                            long id) {
                        contractPositionSpinner3HinhThucThongBaoCuoc = position;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
        viewModel.setContractAdapterSpinner3HinhThucThongBaoCuoc(
                contractAdapterSpinner3HinhThucThongBaoCuoc);
    }

    private void getDataKhachHangDN() {
        //        createNewView1.showLoading();
        //        Subscription subscription =
        //                observableDataSpinnerBaseView().subscribe(new MBCCSSubscribe<DataSpinnerBaseView>() {
        //                    @Override
        //                    public void onSuccess(DataSpinnerBaseView object) {
        //                        subscriberDataSpinner2GoiCuoc =
        //                                object.getSpinner2GoiCuoc().getListProduct();
        //                        subscriberDataSpinner2LoaiThueBao =
        //                                object.getSpinner2LoaiThueBao().getSubTypeList();
        //                        subscriberDataSpinner2DichVu = Data.connectorTelServiceType();
        //                        setDataCreateView();
        //                    }
        //
        //                    @Override
        //                    public void onError(BaseException error) {
        //                        createNewView1.hideLoading();
        //                        createNewView1.loadDataSpinnerError(error);
        //                    }
        //                });
        //        subscriptions.add(subscription);
    }

    private void getDataHTHoaMang() {
        if (createNewView2.getFragmentVisible()) createNewView2.showLoading();

        Subscription subscription =
                getDataSpinner2HTHoaMang().subscribe(new MBCCSSubscribe<GetListRegTypeResponse>() {
                    @Override
                    public void onSuccess(GetListRegTypeResponse object) {
                        if (object == null
                                || object.getRegTypeList() == null
                                || object.getRegTypeList().size() == 0) {
                            BaseErrorResponse baseErrorResponse = new BaseErrorResponse();
                            baseErrorResponse.setError(201, stringLoadDataError);
                            onError(BaseException.toServerError(baseErrorResponse));
                        }
                        subscriberDataSpinner2HTHoaMang = object.getRegTypeList();
                        setViewSpinner2HTHoaMang();
                    }

                    @Override
                    public void onError(BaseException error) {
                        if (createNewView2.getFragmentVisible()) {
                            createNewView2.loadDataSpinnerError(error);
                        }
                    }

                    @Override
                    public void onRequestFinish() {
                        super.onRequestFinish();
                        if (createNewView2.getFragmentVisible()) createNewView2.hideLoading();
                    }
                });
        subscriptions.add(subscription);
    }

    private void setViewSpinner2HTHoaMang() {
        if (subscriberDataSpinner2HTHoaMang == null) return;
        subscriberAdapterSpinner2HTHoaMang =
                new SpinnerAdapter<>(context, subscriberDataSpinner2HTHoaMang);
        subscriberAdapterSpinner2HTHoaMang.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position,
                            long id) {
                        subscriberPositionSpinner2HTHoaMang = position;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
        viewModel.setSubscriberAdapterSpinner2HTHoaMang(subscriberAdapterSpinner2HTHoaMang);
    }

    private void getDataTenNganHang() {
        if (createNewView3.getFragmentVisible()) createNewView3.showLoading();
        Subscription subscription = getDataSpinner3TenNganHang().subscribe(
                new MBCCSSubscribe<GetListRegTypeResponse>() {
                    @Override
                    public void onSuccess(GetListRegTypeResponse object) {
                        if (object == null
                                || object.getRegTypeList() == null
                                || object.getRegTypeList().size() == 0) {
                            BaseErrorResponse baseErrorResponse = new BaseErrorResponse();
                            baseErrorResponse.setError(201, stringLoadDataError);
                            onError(BaseException.toServerError(baseErrorResponse));
                        }
                        contractDataSpinner3TenNganHang = object.getRegTypeList();
                        setViewSpinner3TenNganHang();
                    }

                    @Override
                    public void onError(BaseException error) {
                        if (createNewView2.getFragmentVisible()) {
                            createNewView3.loadDataSpinner3TenNganHang(error);
                        }
                    }

                    @Override
                    public void onRequestFinish() {
                        super.onRequestFinish();
                        if (createNewView3.getFragmentVisible()) createNewView3.hideLoading();
                    }
                });
        subscriptions.add(subscription);
    }

    private void setViewSpinner3TenNganHang() {
        if (contractDataSpinner3TenNganHang == null) return;
        contractAdapterSpinner3TenNganHang =
                new SpinnerAdapter<>(context, contractDataSpinner3TenNganHang);
        contractAdapterSpinner3TenNganHang.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position,
                            long id) {
                        contractPositionSpinner3TenNganHang = position;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
        viewModel.setContractAdapterSpinner3TenNganHang(contractAdapterSpinner3TenNganHang);
    }

    private Observable<GetApDomainByTypeResponse> getDataRegisterSpinnerPassportType() {
        DataRequest<GetApDomainByTypeRequest> request = new DataRequest<>();
        GetApDomainByTypeRequest apDomainRequest = new GetApDomainByTypeRequest();
        apDomainRequest.setType(ApDomainByType.Type.LOAI_GIAY_TO);
        apDomainRequest.setSubType(MobileType.TRA_TRUOC);

        request.setWsRequest(apDomainRequest);
        request.setWsCode(WsCode.GetApDomainByType);

        return qlKhachHangRepository.getApDomainByType(request);
    }

    private Observable<GetListProductResponse> getDataSpinner2GoiCuoc() {
        GetListProductRequest getListProductRequest = new GetListProductRequest();
        getListProductRequest.setSubType(MobileType.TRA_TRUOC);
        getListProductRequest.setTelServiceId(TelServiceId.Mobile);

        DataRequest<GetListProductRequest> request = new DataRequest<>();
        request.setWsRequest(getListProductRequest);
        request.setWsCode(WsCode.GetListProduct);
        return qlKhachHangRepository.getListProduct(request);
    }

    private Observable<GetListSubTypeResponse> getDataSpinner2LoaiThueBao() {
        GetListSubTypeRequest getListSubTypeRequest = new GetListSubTypeRequest();
        getListSubTypeRequest.setServiceType(TelServiceType.Mobile);

        DataRequest<GetListSubTypeRequest> request = new DataRequest<>();
        request.setWsRequest(getListSubTypeRequest);
        request.setWsCode(WsCode.GetListSubType);

        return qlKhachHangRepository.getListSubType(request);
    }

    private Observable<GetListRegTypeResponse> getDataSpinner2KhuyenMai() {
        GetListRegTypeRequest getListRegTypeRequest = new GetListRegTypeRequest();

        DataRequest<GetListRegTypeRequest> request = new DataRequest<>();
        request.setWsRequest(getListRegTypeRequest);
        request.setWsCode(WsCode.GetListRegType);

        return qlKhachHangRepository.getListRegType(request);
    }

    private Observable<GetListRegTypeResponse> getDataSpinner3TypeContract() {
        GetListRegTypeRequest getListRegTypeRequest = new GetListRegTypeRequest();

        DataRequest<GetListRegTypeRequest> request = new DataRequest<>();
        request.setWsRequest(getListRegTypeRequest);
        request.setWsCode(WsCode.GetListRegType);

        return qlKhachHangRepository.getListRegType(request);
    }

    private Observable<GetListRegTypeResponse> getDataSpinner3Payer() {
        GetListRegTypeRequest getListRegTypeRequest = new GetListRegTypeRequest();

        DataRequest<GetListRegTypeRequest> request = new DataRequest<>();
        request.setWsRequest(getListRegTypeRequest);
        request.setWsCode(WsCode.GetListRegType);

        return qlKhachHangRepository.getListRegType(request);
    }

    private Observable<GetListRegTypeResponse> getDataSpinner3HinhThucThanhToan() {
        GetListRegTypeRequest getListRegTypeRequest = new GetListRegTypeRequest();

        DataRequest<GetListRegTypeRequest> request = new DataRequest<>();
        request.setWsRequest(getListRegTypeRequest);
        request.setWsCode(WsCode.GetListRegType);

        return qlKhachHangRepository.getListRegType(request);
    }

    private Observable<GetListRegTypeResponse> getDataSpinner3ChuKyCuoc() {
        GetListRegTypeRequest getListRegTypeRequest = new GetListRegTypeRequest();

        DataRequest<GetListRegTypeRequest> request = new DataRequest<>();
        request.setWsRequest(getListRegTypeRequest);
        request.setWsCode(WsCode.GetListRegType);

        return qlKhachHangRepository.getListRegType(request);
    }

    private Observable<GetListRegTypeResponse> getDataSpinner3ChiTietIn() {
        GetListRegTypeRequest getListRegTypeRequest = new GetListRegTypeRequest();

        DataRequest<GetListRegTypeRequest> request = new DataRequest<>();
        request.setWsRequest(getListRegTypeRequest);
        request.setWsCode(WsCode.GetListRegType);

        return qlKhachHangRepository.getListRegType(request);
    }

    private Observable<GetListRegTypeResponse> getDataSpinner3HinhThucThongBaoCuoc() {
        GetListRegTypeRequest getListRegTypeRequest = new GetListRegTypeRequest();

        DataRequest<GetListRegTypeRequest> request = new DataRequest<>();
        request.setWsRequest(getListRegTypeRequest);
        request.setWsCode(WsCode.GetListRegType);

        return qlKhachHangRepository.getListRegType(request);
    }

    private Observable<GetListRegTypeResponse> getDataSpinner2HTHoaMang() {
        GetListRegTypeRequest getListRegTypeRequest = new GetListRegTypeRequest();
        getListRegTypeRequest.setSubType(
                subscriberDataSpinner2LoaiThueBao.get(subscriberPositionSpinner2LoaiThueBao)
                        .getSubType());
        getListRegTypeRequest.setTelServiceId(
                subscriberDataSpinner2DichVu.get(subscriberPositionSpinner2DichVu).getId());
        getListRegTypeRequest.setProductCode(
                subscriberDataSpinner2GoiCuoc.get(subscriberPositionSpinner2GoiCuoc)
                        .getOfferCode());
        getListRegTypeRequest.setChannelTypeId("");

        DataRequest<GetListRegTypeRequest> request = new DataRequest<>();
        request.setWsRequest(getListRegTypeRequest);
        request.setWsCode(WsCode.GetListRegType);
        return qlKhachHangRepository.getListRegType(request);
    }

    private Observable<GetListRegTypeResponse> getDataSpinner3TenNganHang() {
        GetListRegTypeRequest getListRegTypeRequest = new GetListRegTypeRequest();
        getListRegTypeRequest.setSubType(
                subscriberDataSpinner2LoaiThueBao.get(subscriberPositionSpinner2LoaiThueBao)
                        .getSubType());
        getListRegTypeRequest.setTelServiceId(
                subscriberDataSpinner2DichVu.get(subscriberPositionSpinner2DichVu).getId());
        getListRegTypeRequest.setProductCode(
                subscriberDataSpinner2GoiCuoc.get(subscriberPositionSpinner2GoiCuoc)
                        .getOfferCode());
        getListRegTypeRequest.setChannelTypeId("");

        DataRequest<GetListRegTypeRequest> request = new DataRequest<>();
        request.setWsRequest(getListRegTypeRequest);
        request.setWsCode(WsCode.GetListRegType);
        return qlKhachHangRepository.getListRegType(request);
    }

    /*---------------------------------- onClick View ---------------------------------------*/
    public void onCancelView1() {
        if (createNewView1 != null) createNewView1.onCancel();
    }

    public void onCancelView2() {
        if (createNewView2 != null && onPageChange != null) {
            onPageChange.onPageChange(0);
        }
    }

    public void onCancelView3() {
        if (createNewView3 != null && onPageChange != null) {
            onPageChange.onPageChange(1);
        }
    }

    public void onNextView1() {
        if (!validateCustomer()) {
            return;
        }

        if (onPageChange != null) {
            onPageChange.onPageChange(1);
        }
    }

    public void onNextView2() {
        if (!validateSubscriber()) {
            return;
        }

        if (onPageChange != null) {
            onPageChange.onPageChange(2);
        }
    }

    public void onConnect() {
        if (!validateCustomer()) {
            if (onPageChange != null) {
                this.onPageChange.onPageChange(0);
            }
            return;
        }

        if (!validateSubscriber()) {
            if (onPageChange != null) {
                this.onPageChange.onPageChange(1);
            }
            return;
        }

        if (!validateContract()) {
            return;
        }

        createNewView3.showLoading();
        UserInfo userInfo = userRepository.getUserInfo();

        ConnectSubscriberRequest connectSubscriberRequest = new ConnectSubscriberRequest();
        connectSubscriberRequest.setContract(contractCurrent);
        connectSubscriberRequest.setCustomer(customerCurrent);
        connectSubscriberRequest.setSubscriber(subscriberCurrent);
        connectSubscriberRequest.setStaffCode(userInfo.getStaffInfo().getStaffCode());
        connectSubscriberRequest.setShopCode(userInfo.getShop().getShopCode());

        List<String> dataImage =
                DatabaseUtils.getBitmapAndSaveDatabase(customer, customerCurrentImageFront,
                        customerCurrentImageBackside, customerCurrentImagePortrait);

        createNewView3.connectSubscriber(connectSubscriberRequest, userInfo, dataImage);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (createNewView3 != null) createNewView3.hideLoading();
            }
        }, 300);
    }

    public void onSelectImage(View view) {

    }

    private void getCustomer() {
        if (customerCurrent == null) customerCurrent = new Customer();
        AddressApp address = createNewView1.getAddress();
        customerCurrent.setAddress(address.getAddress());
        customerCurrent.setAreaCode(address.getAreaPrecinct().getAreaCode());
        customerCurrent.setBirthDate(createNewView1.getBirthDate());
        customerCurrent.setBusType(customerDataAdapterSpinnerCustomerType.get(
                customerPositionAdapterSpinnerCustomerType).getBusType());
        customerCurrent.setCustId(customer.getCustId());
        customerCurrent.setDistrict(address.getAreaDistrict().getDistrict());
        customerCurrent.setIdIssueDate(createNewView1.getDateCreatePassport());
        customerCurrent.setIdIssuePlace(viewModel.getCustomerPlaceCreatePassport());
        customerCurrent.setIdNo(viewModel.getCustomerNumberGPKH());
        //        customerCurrent.setIdType(
        //                dataSpinnerPassportType.get(positionSpinnerPassportType).getCode());
        customerCurrent.setName(viewModel.getCustomerNameCustomer());
        customerCurrent.setNationality(customer.getNationality());
        customerCurrent.setPrecinct(address.getAreaPrecinct().getPrecinct());
        customerCurrent.setProvince(address.getAreaProvince().getProvince());
        customerCurrent.setSex(viewModel.isRegisterCheckMale() ? Gender.MALE : Gender.FEMALE);
        customerCurrent.setStatus(customer.getStatus());

        customerCurrent.setIdExpireDate(null);

        customerCurrentImageFront = createNewView1.imageFront();
        customerCurrentImageBackside = createNewView1.imageBackside();
        customerCurrentImagePortrait = createNewView1.imagePortrait();
    }

    private void getSubscriber() {
        if (subscriberCurrent == null) subscriberCurrent = new Subscriber();
        AddressApp address = createNewView2.getAddress();

        // TODO: 7/7/17
        subscriberCurrent.setActStatus(null);
        subscriberCurrent.setContractId(null);
        subscriberCurrent.setCreateDate(DateUtils.getCurrentDate());
        //        subscriberCurrent.setCustId(null);
        subscriberCurrent.setDeposit(null);
        subscriberCurrent.setImsi(null);
        subscriberCurrent.setIsdn(viewModel.getSubscriberIsdn());
        subscriberCurrent.setNumResetZone(null);
        subscriberCurrent.setPayType(null);
        subscriberCurrent.setProductCode(
                subscriberDataSpinner2GoiCuoc.get(subscriberPositionSpinner2GoiCuoc)
                        .getOfferCode());
        subscriberCurrent.setPromotionCode(null);
        subscriberCurrent.setQuota(null);
        subscriberCurrent.setReasonDepositId(null);
        subscriberCurrent.setRegType(
                subscriberDataSpinner2HTHoaMang.get(subscriberPositionSpinner2HTHoaMang).getType());
        subscriberCurrent.setSerial(viewModel.getSubscriberSerial());
        subscriberCurrent.setServiceType(
                subscriberDataSpinner2DichVu.get(subscriberPositionSpinner2DichVu).getCode());
        subscriberCurrent.setTelecomServiceId(
                subscriberDataSpinner2DichVu.get(subscriberPositionSpinner2DichVu).getId());
        subscriberCurrent.setShopCode(null);
        subscriberCurrent.setStaDatetime(DateUtils.getCurrentDate());
        subscriberCurrent.setStaffId(null);
        subscriberCurrent.setStatus(null);
        subscriberCurrent.setSubID(null);
        subscriberCurrent.setUserCreated(null);
        subscriberCurrent.setUserUsing(null);
        subscriberCurrent.setSubType(
                subscriberDataSpinner2LoaiThueBao.get(subscriberPositionSpinner2LoaiThueBao)
                        .getSubType());

        subscriberCurrent.setAddress(address.getAddress());
        subscriberCurrent.setProvince(address.getAreaProvince().getProvince());
        subscriberCurrent.setDistrict(address.getAreaDistrict().getDistrict());
        subscriberCurrent.setPrecinct(address.getAreaPrecinct().getPrecinct());
    }

    private void getContract() {
        if (contractCurrent == null) contractCurrent = new Contract();
    }

    boolean validateCustomer() {
        getCustomer();
        boolean validate = true;

        //        if (StringUtils.isEmpty(customerCurrent.getIdIssuePlace())) {
        //            setPlaceCreatePassportError(stringError);
        //            validate = false;
        //        }
        //
        //        if (StringUtils.isEmpty(customerCurrent.getIdNo())) {
        //            setTxtNumberPassportError(stringError);
        //            validate = false;
        //        }

        if (!PatternUtils.validateString(customerCurrent.getName(),
                PatternUtils.PATTERN_TEXT_LENGTH_100)) {
            viewModel.setCustomerNameCustomerError(
                    context.getString(R.string.create_new_connector_information_validate_name));
            validate = false;
        }
        return validate;
    }

    boolean validateSubscriber() {
        getSubscriber();
        boolean validate = true;
        if (StringUtils.isEmpty(subscriberCurrent.getSerial())) {
            viewModel.setSubscriberSerialError(stringError);
            validate = false;
        }

        if (StringUtils.isEmpty(subscriberCurrent.getIsdn())) {
            viewModel.setSubscriberIsdnError(stringError);
            validate = false;
        }

        return validate;
    }

    private boolean validateContract() {
        getContract();
        boolean validate = true;

        return validate;
    }


    /*---------------------------------- End onClick View ---------------------------------------*/

    private void setDataCustomer() {
        if (customer == null) return;
        viewModel.setCustomerNameCustomer(customer.getCustomerName());
        viewModel.setCustomerDateBirthday(customer.getBirthDate() != null ? customer.getBirthDate()
                : DateUtils.convertToString(DateUtils.maxDateBirthday(), DateUtils.TIMEZONE_FORMAT,
                        null));
        viewModel.setCustomerDateCreate(customer.getIdIssueDate());
        viewModel.setCustomerPlaceCreatePassport(customer.getIdIssuePlace());
        viewModel.setCustomerNumberGPKH(customer.getIdNo());
        // TODO: 7/8/17
        viewModel.setCustomerNumberMaSoThue(customer.getTin());

        //        if (customer.getSex() != null && customer.getSex().equals(Gender.MALE)) {
        //            viewModel.setRegisterCheckMale(true);
        //            viewModel.setRegisterCheckFemale(false);
        //        } else {
        //            viewModel.setRegisterCheckMale(false);
        //            viewModel.setRegisterCheckFemale(true);
        //        }

        AddressApp address = new AddressApp();
        address.setIdProvince(customer.getProvince());
        address.setIdDistrict(customer.getDistrict());
        address.setIdPrecinct(customer.getPrecinct());
        address.setAddress(customer.getAddress());
        viewModel.setCustomerArea1(address);

    }

    private Observable<DataSpinnerBaseView> observableDataSpinnerBaseView() {
        return Observable.zip(getDataSpinner2GoiCuoc(), getDataSpinner2LoaiThueBao(),
                getDataSpinner2KhuyenMai(), getDataSpinner3TypeContract(), getDataSpinner3Payer(),
                getDataSpinner3HinhThucThanhToan(), getDataSpinner3ChuKyCuoc(),
                getDataSpinner3ChiTietIn(), getDataSpinner3HinhThucThongBaoCuoc(),
                new Func9<GetListProductResponse, GetListSubTypeResponse, GetListRegTypeResponse, GetListRegTypeResponse, GetListRegTypeResponse, GetListRegTypeResponse, GetListRegTypeResponse, GetListRegTypeResponse, GetListRegTypeResponse, DataSpinnerBaseView>() {
                    @Override
                    public DataSpinnerBaseView call(GetListProductResponse dataSpinner2GoiCuoc,
                            GetListSubTypeResponse dataSpinner2LoaiThueBao,
                            GetListRegTypeResponse dataSpinner2KhuyenMai,
                            GetListRegTypeResponse dataSpinner3TypeContract,
                            GetListRegTypeResponse dataSpinner3Payer,
                            GetListRegTypeResponse dataSpinner3HinhThucThanhToan,
                            GetListRegTypeResponse dataSpinner3ChuKyCuoc,
                            GetListRegTypeResponse dataSpinner3ChiTietIn,
                            GetListRegTypeResponse dataSpinner3HinhThucThongBaoCuoc) {
                        return new DataSpinnerBaseView(dataSpinner2GoiCuoc, dataSpinner2LoaiThueBao,
                                dataSpinner2KhuyenMai, dataSpinner3TypeContract, dataSpinner3Payer,
                                dataSpinner3HinhThucThanhToan, dataSpinner3ChuKyCuoc,
                                dataSpinner3ChiTietIn, dataSpinner3HinhThucThongBaoCuoc);
                    }
                }).flatMap(new Func1<DataSpinnerBaseView, Observable<DataSpinnerBaseView>>() {
            @Override
            public Observable<DataSpinnerBaseView> call(DataSpinnerBaseView spinner) {
                GetListProductResponse dataSpinner2GoiCuoc =
                        spinner.getSubscriberDataSpinner2GoiCuoc();
                GetListSubTypeResponse dataSpinner2LoaiThueBao =
                        spinner.getSubscriberDataSpinner2LoaiThueBao();
                GetListRegTypeResponse dataSpinner2KhuyenMai =
                        spinner.getSubscriberDataSpinner2KhuyenMai();
                GetListRegTypeResponse dataSpinner3TypeContract =
                        spinner.getContractDataSpinner3TypeContract();
                GetListRegTypeResponse dataSpinner3Payer = spinner.getContractDataSpinner3Payer();
                GetListRegTypeResponse dataSpinner3HinhThucThanhToan =
                        spinner.getContractDataSpinner3HinhThucThanhToan();
                GetListRegTypeResponse dataSpinner3ChuKyCuoc =
                        spinner.getContractDataSpinner3ChuKyCuoc();
                GetListRegTypeResponse dataSpinner3ChiTietIn =
                        spinner.getContractDataSpinner3ChiTietIn();
                GetListRegTypeResponse dataSpinner3HinhThucThongBaoCuoc =
                        spinner.getContractDataSpinner3HinhThucThongBaoCuoc();

                if (dataSpinner2GoiCuoc == null
                        || dataSpinner2GoiCuoc.getListProduct() == null
                        || dataSpinner2GoiCuoc.getListProduct().size() == 0

                        || dataSpinner2LoaiThueBao == null
                        || dataSpinner2LoaiThueBao.getSubTypeList() == null
                        || dataSpinner2LoaiThueBao.getSubTypeList().size() == 0

                        || dataSpinner2KhuyenMai == null
                        || dataSpinner2KhuyenMai.getRegTypeList() == null
                        || dataSpinner2KhuyenMai.getRegTypeList().size() == 0

                        || dataSpinner3TypeContract == null
                        || dataSpinner3TypeContract.getRegTypeList() == null
                        || dataSpinner3TypeContract.getRegTypeList().size() == 0

                        || dataSpinner3Payer == null
                        || dataSpinner3Payer.getRegTypeList() == null
                        || dataSpinner3Payer.getRegTypeList().size() == 0

                        || dataSpinner3HinhThucThanhToan == null
                        || dataSpinner3HinhThucThanhToan.getRegTypeList() == null
                        || dataSpinner3HinhThucThanhToan.getRegTypeList().size() == 0

                        || dataSpinner3ChuKyCuoc == null
                        || dataSpinner3ChuKyCuoc.getRegTypeList() == null
                        || dataSpinner3ChuKyCuoc.getRegTypeList().size() == 0

                        || dataSpinner3ChiTietIn == null
                        || dataSpinner3ChiTietIn.getRegTypeList() == null
                        || dataSpinner3ChiTietIn.getRegTypeList().size() == 0

                        || dataSpinner3HinhThucThongBaoCuoc == null
                        || dataSpinner3HinhThucThongBaoCuoc.getRegTypeList() == null
                        || dataSpinner3HinhThucThongBaoCuoc.getRegTypeList().size() == 0

                        ) {
                    BaseErrorResponse baseErrorResponse = new BaseErrorResponse();
                    baseErrorResponse.setError(201, stringLoadDataError);
                    return Observable.error(BaseException.toServerError(baseErrorResponse));
                } else {
                    return Observable.just(spinner);
                }
            }
        });
    }

    void setAddressView2() {
        AddressApp address = new AddressApp();
        address.setIdProvince(customerCurrent.getProvince());
        address.setIdDistrict(customerCurrent.getDistrict());
        address.setIdPrecinct(customerCurrent.getPrecinct());
        address.setAddress(customerCurrent.getAddress());
        viewModel.setSubscriberArea2(address);
    }

    private class DataSpinnerBaseView {
        private GetListProductResponse subscriberDataSpinner2GoiCuoc;
        private GetListSubTypeResponse subscriberDataSpinner2LoaiThueBao;
        private GetListRegTypeResponse subscriberDataSpinner2KhuyenMai;
        private GetListRegTypeResponse contractDataSpinner3TypeContract;
        private GetListRegTypeResponse contractDataSpinner3Payer;
        private GetListRegTypeResponse contractDataSpinner3HinhThucThanhToan;
        private GetListRegTypeResponse contractDataSpinner3ChuKyCuoc;
        private GetListRegTypeResponse contractDataSpinner3ChiTietIn;
        private GetListRegTypeResponse contractDataSpinner3HinhThucThongBaoCuoc;

        public DataSpinnerBaseView() {
        }

        DataSpinnerBaseView(GetListProductResponse subscriberDataSpinner2GoiCuoc,
                GetListSubTypeResponse subscriberDataSpinner2LoaiThueBao,
                GetListRegTypeResponse subscriberDataSpinner2KhuyenMai,
                GetListRegTypeResponse contractDataSpinner3TypeContract,
                GetListRegTypeResponse contractDataSpinner3Payer,
                GetListRegTypeResponse contractDataSpinner3HinhThucThanhToan,
                GetListRegTypeResponse contractDataSpinner3ChuKyCuoc,
                GetListRegTypeResponse contractDataSpinner3ChiTietIn,
                GetListRegTypeResponse contractDataSpinner3HinhThucThongBaoCuoc) {

            this.subscriberDataSpinner2GoiCuoc = subscriberDataSpinner2GoiCuoc;
            this.subscriberDataSpinner2LoaiThueBao = subscriberDataSpinner2LoaiThueBao;
            this.subscriberDataSpinner2KhuyenMai = subscriberDataSpinner2KhuyenMai;
            this.contractDataSpinner3TypeContract = contractDataSpinner3TypeContract;
            this.contractDataSpinner3Payer = contractDataSpinner3Payer;
            this.contractDataSpinner3HinhThucThanhToan = contractDataSpinner3HinhThucThanhToan;
            this.contractDataSpinner3ChuKyCuoc = contractDataSpinner3ChuKyCuoc;
            this.contractDataSpinner3ChiTietIn = contractDataSpinner3ChiTietIn;
            this.contractDataSpinner3HinhThucThongBaoCuoc =
                    contractDataSpinner3HinhThucThongBaoCuoc;
        }

        public GetListProductResponse getSubscriberDataSpinner2GoiCuoc() {
            return subscriberDataSpinner2GoiCuoc;
        }

        public void setSubscriberDataSpinner2GoiCuoc(
                GetListProductResponse subscriberDataSpinner2GoiCuoc) {
            this.subscriberDataSpinner2GoiCuoc = subscriberDataSpinner2GoiCuoc;
        }

        public GetListSubTypeResponse getSubscriberDataSpinner2LoaiThueBao() {
            return subscriberDataSpinner2LoaiThueBao;
        }

        public void setSubscriberDataSpinner2LoaiThueBao(
                GetListSubTypeResponse subscriberDataSpinner2LoaiThueBao) {
            this.subscriberDataSpinner2LoaiThueBao = subscriberDataSpinner2LoaiThueBao;
        }

        public GetListRegTypeResponse getSubscriberDataSpinner2KhuyenMai() {
            return subscriberDataSpinner2KhuyenMai;
        }

        public void setSubscriberDataSpinner2KhuyenMai(
                GetListRegTypeResponse subscriberDataSpinner2KhuyenMai) {
            this.subscriberDataSpinner2KhuyenMai = subscriberDataSpinner2KhuyenMai;
        }

        public GetListRegTypeResponse getContractDataSpinner3TypeContract() {
            return contractDataSpinner3TypeContract;
        }

        public void setContractDataSpinner3TypeContract(
                GetListRegTypeResponse contractDataSpinner3TypeContract) {
            this.contractDataSpinner3TypeContract = contractDataSpinner3TypeContract;
        }

        public GetListRegTypeResponse getContractDataSpinner3Payer() {
            return contractDataSpinner3Payer;
        }

        public void setContractDataSpinner3Payer(GetListRegTypeResponse contractDataSpinner3Payer) {
            this.contractDataSpinner3Payer = contractDataSpinner3Payer;
        }

        public GetListRegTypeResponse getContractDataSpinner3HinhThucThanhToan() {
            return contractDataSpinner3HinhThucThanhToan;
        }

        public void setContractDataSpinner3HinhThucThanhToan(
                GetListRegTypeResponse contractDataSpinner3HinhThucThanhToan) {
            this.contractDataSpinner3HinhThucThanhToan = contractDataSpinner3HinhThucThanhToan;
        }

        public GetListRegTypeResponse getContractDataSpinner3ChuKyCuoc() {
            return contractDataSpinner3ChuKyCuoc;
        }

        public void setContractDataSpinner3ChuKyCuoc(
                GetListRegTypeResponse contractDataSpinner3ChuKyCuoc) {
            this.contractDataSpinner3ChuKyCuoc = contractDataSpinner3ChuKyCuoc;
        }

        public GetListRegTypeResponse getContractDataSpinner3ChiTietIn() {
            return contractDataSpinner3ChiTietIn;
        }

        public void setContractDataSpinner3ChiTietIn(
                GetListRegTypeResponse contractDataSpinner3ChiTietIn) {
            this.contractDataSpinner3ChiTietIn = contractDataSpinner3ChiTietIn;
        }

        public GetListRegTypeResponse getContractDataSpinner3HinhThucThongBaoCuoc() {
            return contractDataSpinner3HinhThucThongBaoCuoc;
        }

        public void setContractDataSpinner3HinhThucThongBaoCuoc(
                GetListRegTypeResponse contractDataSpinner3HinhThucThongBaoCuoc) {
            this.contractDataSpinner3HinhThucThongBaoCuoc =
                    contractDataSpinner3HinhThucThongBaoCuoc;
        }
    }

    public void setOnPageChange(OnPageChange listener) {
        this.onPageChange = listener;
    }

    public void onPageChange(int position) {
        if (onPageChange != null) {
            this.onPageChange.onPageChange(position);
        }
    }
}
