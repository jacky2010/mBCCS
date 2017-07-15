package com.viettel.mbccs.screen.uploadimage;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.viettel.mbccs.BR;
import com.viettel.mbccs.data.model.database.UploadImage;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.screen.uploadimage.adapter.AdapterUploadImage;
import java.util.List;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by HuyQuyet on 6/8/17.
 */

public class UploadImagePresenter extends BaseObservable implements UploadImageContract.Presenter {

    private Context context;
    private UploadImageContract.View view;
    private UserRepository userRepository;
    private CompositeSubscription subscriptions;

//    private List<Observable<?>> observableList;
    private List<UploadImage> uploadImageList;
    private AdapterUploadImage recyclerAdapter;
    private int progressValue;
    private int total;
    private int current;

    public UploadImagePresenter(Context context, UploadImageContract.View view) {
        this.context = context;
        this.view = view;
        userRepository = UserRepository.getInstance();
        subscriptions = new CompositeSubscription();
    }

    @Override
    public void subscribe() {
        view.showLoading();
        uploadImageList = userRepository.getUploadImage();
//        observableList = new ArrayList<>();
//        for (UploadImage uploadImage : uploadImageList) {
//            observableList.add(createUploadImage(uploadImage));
//        }
        setTotal(uploadImageList.size());
        setCurrent(0);
        setProgressValue(0);
        view.hideLoading();
        view.setData(uploadImageList);
    }

    @Override
    public void unSubscribe() {
        subscriptions.clear();
        for (int i = 0; i < current; i++) {
            uploadImageList.get(i).delete();
        }
    }

    @Bindable
    public AdapterUploadImage getRecyclerAdapter() {
        return recyclerAdapter;
    }

    public void setRecyclerAdapter(AdapterUploadImage recyclerAdapter) {
        this.recyclerAdapter = recyclerAdapter;
        notifyPropertyChanged(BR.recyclerAdapter);
    }

    @Bindable
    public int getProgressValue() {
        return progressValue;
    }

    public void setProgressValue(int progressValue) {
        this.progressValue = progressValue;
        notifyPropertyChanged(BR.progressValue);
    }

    @Bindable
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
        notifyPropertyChanged(BR.total);
    }

    @Bindable
    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
        notifyPropertyChanged(BR.current);
    }

    public void onCancel() {
        view.onCancel();
    }

    public void onCancelUpload() {
        subscriptions.clear();
        for (int i = 0; i < current; i++) {
            uploadImageList.get(i).delete();
        }
        for (UploadImage uploadImage : uploadImageList) {
            uploadImageList.remove(uploadImage);
        }
        recyclerAdapter.notifyDataSetChanged();
    }

    public void onStartUpload() {
//        startUploadImage();
        view.onStartUpload();
    }

//    public void startUploadImage() {
//        Subscription subscription = Observable.create(new Observable.OnSubscribe<Integer>() {
//            int progressValue = 0;
//
//            @Override
//            public void call(final Subscriber<? super Integer> subscriber) {
//                Observable.merge(observableList).subscribe(new Action1<Object>() {
//                    @Override
//                    public void call(Object integer) {
//                        if (++progressValue <= observableList.size()) {
//                            subscriber.onNext(progressValue);
//                            if (progressValue == observableList.size()) {
//                                subscriber.onCompleted();
//                            }
//                        }
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//                        subscriber.onError(throwable);
//                    }
//                });
//            }
//        }).subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//
//                setProgressValue((integer / observableList.size()) * 100);
//                setCurrent(integer);
//            }
//        }, new Action1<Throwable>() {
//            @Override
//            public void call(Throwable throwable) {
//                if (throwable instanceof BaseException) {
//                    view.uploadError((BaseException) throwable);
//                } else {
//                    BaseErrorResponse baseErrorResponse = new BaseErrorResponse();
//                    baseErrorResponse.setError(100, "upload Image Error");
//                    view.uploadError(BaseException.toServerError(baseErrorResponse));
//                }
//            }
//        }, new Action0() {
//            @Override
//            public void call() {
//                view.uploadCompleted();
//            }
//        });
//        subscriptions.add(subscription);
//    }
//
//    private Observable<?> createUploadImage(UploadImage data) {
//        UploadImageRequest uploadImageRequest = new UploadImageRequest();
//        DataRequest<UploadImageRequest> request = new DataRequest<>();
//        request.setParameterApi(uploadImageRequest);
//        return userRepository.uploadImage(request);
//    }
}
