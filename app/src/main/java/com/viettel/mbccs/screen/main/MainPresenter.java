package com.viettel.mbccs.screen.main;

import android.content.Context;
import android.databinding.ObservableField;

/**
 * Created by eo_cuong on 5/11/17.
 */

public class MainPresenter implements MainContract.Presenter {

    private Context mContext;

    private MainContract.ViewModel mViewModel;

    public ObservableField<String> text;

    public MainPresenter(Context context, MainContract.ViewModel viewModel) {
        mContext = context;
        mViewModel = viewModel;
        text = new ObservableField<>();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    public void gotoLogin() {
        //new DialogFullScreen.Builder(mContext).setNegativeButton("cancel")
        //       // .setTitle("thong bao")
        //        .setContent("<b>thanh cong</b></br>ok")
        //        //.setIcon(R.drawable.ic_done)
        //        .setShowToolbar(true)
        //        .setListener(new DialogFullScreen.SuccessDialogListener() {
        //            @Override
        //            public void onPosotiveButtonClick(Dialog d) {
        //                Toast.makeText(mContext, "ok click", Toast.LENGTH_SHORT).show();
        //            }
        //
        //            @Override
        //            public void onNegativeButtonClick(Dialog d) {
        //                Toast.makeText(mContext, "cancel click", Toast.LENGTH_SHORT).show();
        //            }
        //        })
        //        .setTitleToolbar("demo")
        //        .build()
        //        .show();
        //Toast.makeText(mContext, text.get(), Toast.LENGTH_SHORT).show();

        //
        //GoodItem mGoodItem = new GoodItem();
        //mGoodItem.setName("Iphone 7");
        //mGoodItem.setHasSerial(true);
        //mGoodItem.setChoiceCount(10);
        //
        //List<Integer> list = new ArrayList<>();
        //list.addAll(Arrays.asList(new Integer[] {
        //        11111, 11112, 11114,
        //}));
        //mGoodItem.setSerials(list);
        //
        //Intent intent = new Intent(mContext, SerialPickerActivity.class);
        //intent.putExtra(Constants.BundleConstant.GOOD_ITEM, GsonUtils.Object2String(mGoodItem));
        //mContext.startActivity(intent);

        //List<GoodItem> goodItems = new ArrayList<>();
        //goodItems.add(mGoodItem);
        //goodItems.add(mGoodItem);
        //Intent intent1 = new Intent(mContext, GoodsConfirmActivity.class);
        //intent1.putExtra(Constants.BundleConstant.GOODS_LIST, GsonUtils.Object2String(goodItems));
        //mContext.startActivity(intent1);
    }
}
