package com.viettel.mbccs.utils;

import android.content.Context;
import android.graphics.Bitmap;
import com.viettel.mbccs.utils.rx.SchedulerUtils;
import java.io.File;
import java.io.IOException;
import rx.Observable;
import rx.functions.Func0;

/**
 * Created by eo_cuong on 6/17/17.
 */

public class ImageCompressor {
    //max width and height values of the compressed image is taken as 640x480
    private int maxWidth = 640;
    private int maxHeight = 480;
    private Bitmap.CompressFormat compressFormat = Bitmap.CompressFormat.JPEG;
    private int quality = 75;
    private String destinationDirectoryPath;

    public ImageCompressor(Context context) {
        try {
            destinationDirectoryPath = FileUtils.getCacheExternalFolder();
        } catch (IOException e) {
            destinationDirectoryPath = FileUtils.getCacheFolderPath(context);
            e.printStackTrace();
        }
    }

    public ImageCompressor setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
        return this;
    }

    public ImageCompressor setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
        return this;
    }

    public ImageCompressor setCompressFormat(Bitmap.CompressFormat compressFormat) {
        this.compressFormat = compressFormat;
        return this;
    }

    public ImageCompressor setQuality(int quality) {
        this.quality = quality;
        return this;
    }

    public ImageCompressor setDestinationDirectoryPath(String destinationDirectoryPath) {
        this.destinationDirectoryPath = destinationDirectoryPath;
        return this;
    }

    public File compressToFile(File imageFile) throws IOException {
        return compressToFile(imageFile, imageFile.getName());
    }

    public File compressToFile(File imageFile, String compressedFileName) throws IOException {
        return ImageUtils.compressImage(imageFile, maxWidth, maxHeight, compressFormat, quality,
                destinationDirectoryPath + File.separator + compressedFileName);
    }

    public Bitmap compressToBitmap(File imageFile) {
        return ImageUtils.decodeSampledBitmapFromFile(imageFile, maxWidth, maxHeight);
    }

    public String compressFileToBase64(File imageFile) throws IOException {
        return ImageUtils.encodeBitmapToBase64(compressToBitmap(imageFile), compressFormat,
                quality);
    }

    /***
     * Compress File To base64 subscrisebr in Observable
     * @param imageFile
     * @return
     */
    public Observable<String> compressFileToBase64AsObservable(final File imageFile) {
        return Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                try {
                    return Observable.just(compressFileToBase64(imageFile));
                } catch (Exception e) {
                    return Observable.error(e);
                }
            }
        }).compose(SchedulerUtils.<String>applyAsyncSchedulers());
    }

    public Observable<Bitmap> compressFileToBitmapAsObservable(final File imageFile) {
        return Observable.defer(new Func0<Observable<Bitmap>>() {
            @Override
            public Observable<Bitmap> call() {
                try {
                    return Observable.just(compressToBitmap(imageFile));
                } catch (Exception e) {
                    return Observable.error(e);
                }
            }
        }).compose(SchedulerUtils.<Bitmap>applyAsyncSchedulers());
    }

    public Observable<File> compressToFileAsObservable(final File imageFile) {
        return compressToFileAsObservable(imageFile, imageFile.getName());
    }

    public Observable<File> compressToFileAsObservable(final File imageFile,
            final String compressedFileName) {
        return Observable.defer(new Func0<Observable<File>>() {
            @Override
            public Observable<File> call() {
                try {
                    return Observable.just(compressToFile(imageFile, compressedFileName));
                } catch (Exception e) {
                    return Observable.error(e);
                }
            }
        }).compose(SchedulerUtils.<File>applyAsyncSchedulers());
    }
}
