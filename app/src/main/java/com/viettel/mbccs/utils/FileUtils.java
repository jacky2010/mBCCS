package com.viettel.mbccs.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.OpenableColumns;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by eo_cuong on 6/17/17.
 */

public class FileUtils {

    private static final int EOF = -1;
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

    public static final String DIR_NAME = "image_resouce";

    /**
     * get path cache directory of app, where save image
     */
    public static String getInternalImageFolderPath(Context context) {
        String path = context.getCacheDir().getPath() + File.separator + DIR_NAME;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return path;
    }

    public static String getCacheFolderPath(Context context) {
        return context.getCacheDir().getPath();
    }

    public static String getCacheExternalFolder() throws IOException {
        File file = new File(Environment.getExternalStorageDirectory() + "/" + ".mbccs");
        if (!file.exists()) {
            file.mkdirs();
        }
        File file1 = new File(file + "/.nomedia");
        if (!file1.exists()) {
            file1.createNewFile();
        }
        return file.getAbsolutePath();
    }

    /***
     * get image path stock by id
     * @param context
     * @param id:
     * @return path file image
     */
    public static String getImagePathByIdName(Context context, String id) {
        return getInternalImageFolderPath(context) + File.separator + id + ".png";
    }

    /***
     * get image file stock by id
     * @param context
     * @param id
     * @return
     */
    public static File getImageFileByIdName(Context context, String id) {
        return new File(getImagePathByIdName(context, id));
    }

    public static boolean isExistImageById(Context context, String id) {
        return getImageFileByIdName(context, id).exists();
    }

    public static File from(Context context, Uri uri) throws IOException {
        InputStream inputStream = context.getContentResolver().openInputStream(uri);
        String fileName = getFileName(context, uri);
        String[] splitName = splitFileName(fileName);
        File tempFile = File.createTempFile(splitName[0], splitName[1]);
        tempFile = rename(tempFile, fileName);
        tempFile.deleteOnExit();
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(tempFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (inputStream != null) {
            copy(inputStream, out);
            inputStream.close();
        }

        if (out != null) {
            out.close();
        }
        return tempFile;
    }

    private static String[] splitFileName(String fileName) {
        String name = fileName;
        String extension = "";
        int i = fileName.lastIndexOf(".");
        if (i != -1) {
            name = fileName.substring(0, i);
            extension = fileName.substring(i);
        }

        return new String[] { name, extension };
    }

    private static String getFileName(Context context, Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf(File.separator);
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    private static File rename(File file, String newName) {
        File newFile = new File(file.getParent(), newName);
        if (!newFile.equals(file)) {
            if (newFile.exists() && newFile.delete()) {
                Log.d("FileUtil", "Delete old " + newName + " file");
            }
            if (file.renameTo(newFile)) {
                Log.d("FileUtil", "Rename file to " + newName);
            }
        }
        return newFile;
    }

    private static long copy(InputStream input, OutputStream output) throws IOException {
        long count = 0;
        int n;
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        while (EOF != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }
}
