package com.viettel.mbccs.screen.manager.area;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseActivity;
import com.viettel.mbccs.callback.OnListenerItemRecyclerView;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.constance.IconType;
import com.viettel.mbccs.data.model.Area;
import com.viettel.mbccs.data.source.QLDiaBanRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetListProvinceRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetListProvinceResponse;
import com.viettel.mbccs.screen.manager.area.adapter.AreaAdapter;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import com.viettel.mbccs.widget.ToolBarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by jacky on 6/5/17.
 */

public class AreaChanelActivity extends BaseActivity {

    @BindView(R.id.list) RecyclerView mRecyclerView;
    @BindView(R.id.toolbar) ToolBarView mToolBar;

    private CompositeSubscription subscriptions;
    private QLDiaBanRepository mQlDiaBanRepository;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected int getIdLayout() {
        return R.layout.activity_location_channel;
    }

    @Override
    protected void initData() {
        initDataBinder();
        subscriptions = new CompositeSubscription();
        mQlDiaBanRepository = QLDiaBanRepository.getInstance();
        // Test ui
        showData(fakeList());
        // call when API run
        // getDataProvince();
    }

    @Override
    protected void initDataBinder() {
        super.initDataBinder();
        initView();
        mToolBar.setOnClickIconListener(new ToolBarView.OnClickIconListener() {
            @Override
            public void onClickIconLeft(int mIconType) {
                switch (mIconType) {
                    case IconType.LEFT:
                        finish();
                        break;
                    case IconType.RIGHT:
                        break;
                }
            }
        });
        mToolBar.setTitle(R.string.title_area);
    }

    private void initView() {
        mLinearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL);

        mRecyclerView.addItemDecoration(dividerItemDecoration);
    }

    private void showData(List<Area> mAreaList) {
        AreaAdapter mAreaAdapter = new AreaAdapter(mAreaList, this);
        mAreaAdapter.setOnClickItemRecycleView(new OnListenerItemRecyclerView<Area>() {
            @Override
            public void onClickItem(Area mArea, int position) {
                if (mArea != null) {
                    Bundle mBundle = new Bundle();
                    mBundle.putParcelable(Area.class.getName(), mArea);
                    switch (position){
                        case 0:
                            openActivity(AdministrativeAreaDetailActivity.class, mBundle);
                            break;
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        default:
                            break;
                    }
                }
            }
        });
        mRecyclerView.setAdapter(mAreaAdapter);
    }

    private List<Area> fakeList() {
        List<Area> mAreaList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Area area = new Area();
            area.setFullName(mListNameFullFake().get(i));
            mAreaList.add(area);
        }
        return mAreaList;
    }

    private List<String> mListNameFullFake() {
        List<String> mList = new ArrayList<>();
        mList.add("Dia ban hanh chinh");
        mList.add("Thong tin Kenh phan phoi");
        mList.add("Cập nhật tọa đô\n" +
                "*BTS/KPP*");
        mList.add("Tạo mới Kenh phan phoi");
        return mList;
    }

    private void getDataProvince() {
        DataRequest<GetListProvinceRequest> request = new DataRequest<>();
        request.setWsRequest(new GetListProvinceRequest());
        request.setWsCode(WsCode.GetListProvince);
        Subscription subscription = mQlDiaBanRepository.getListProvince(request)
                .subscribe(new MBCCSSubscribe<GetListProvinceResponse>() {
                    @Override
                    public void onSuccess(GetListProvinceResponse object) {
                        showData(object.getAreaList());
                    }

                    @Override
                    public void onError(BaseException error) {

                    }
                });

        subscriptions.add(subscription);
    }
}
