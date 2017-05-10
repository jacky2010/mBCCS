package com.viettel.mbccs.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import com.google.common.base.Preconditions;
import com.viettel.mbccs.R;

/**
 * This provides methods to help Activities load their UI.
 */
public class ActivityUtils {

    public static final int GALLERY_REQUEST_CODE = 100;

    /**
     * The {@code fragment} is added to the container view with id {@code frameId}. The operation
     * is
     * performed by the {@code fragmentManager}.
     */
    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
            @NonNull Fragment fragment, int frameId) {
        Preconditions.checkNotNull(fragmentManager);
        Preconditions.checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    //public static void replaceFragment(@NonNull Activity activity, @NonNull Fragment fragment) {
    //    Preconditions.checkNotNull(activity);
    //    Preconditions.checkNotNull(fragment);
    //    FragmentManager fragmentManager = ((FragmentActivity) activity).getSupportFragmentManager();
    //    FragmentTransaction transaction = fragmentManager.beginTransaction();
    //    transaction.replace(R.id.fragment_container, fragment);
    //    transaction.commit();
    //}
    //
    //public static void replaceFragment(@NonNull Activity activity, @NonNull Fragment fragment,
    //        boolean isBackStack) {
    //    Preconditions.checkNotNull(activity);
    //    Preconditions.checkNotNull(fragment);
    //    FragmentManager fragmentManager = ((FragmentActivity) activity).getSupportFragmentManager();
    //    FragmentTransaction transaction = fragmentManager.beginTransaction();
    //    if (isBackStack) {
    //        transaction.addToBackStack(fragment.getClass().getSimpleName());
    //    }
    //    transaction.replace(R.id.fragment_container, fragment, fragment.getClass().getName())
    //            .commitAllowingStateLoss();
    //}
    //
    //public static void nextChildFragment(@Nullable Fragment parentFragment,
    //        @Nullable Fragment childFragment, boolean isBackStack) {
    //    Preconditions.checkNotNull(parentFragment);
    //    Preconditions.checkNotNull(childFragment);
    //    FragmentTransaction transaction =
    //            parentFragment.getChildFragmentManager().beginTransaction();
    //    if (isBackStack) {
    //        transaction.addToBackStack(childFragment.getClass().getSimpleName());
    //    }
    //    transaction.replace(R.id.frame_container, childFragment,
    //            childFragment.getClass().getSimpleName());
    //    transaction.commitAllowingStateLoss();
    //    parentFragment.getChildFragmentManager().executePendingTransactions();
    //}

    public static void hideKeyboard(@NonNull Activity activity) {
        View view = activity.getCurrentFocus();
        if (view == null) {
            return;
        }
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static boolean isAccessGalleryRequest(@NonNull Activity activity) {
        int permission = ContextCompat.checkSelfPermission(activity,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[] { Manifest.permission.READ_EXTERNAL_STORAGE },
                    GALLERY_REQUEST_CODE);
        }
        return permission == PackageManager.PERMISSION_GRANTED;
    }

    public static void removeFragmentByTag(@NonNull Activity activity, @NonNull String tag) {
        if (activity.isFinishing()) return;
        FragmentManager fragmentManager = ((FragmentActivity) activity).getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment != null) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.remove(fragment).commitAllowingStateLoss();
            fragmentManager.popBackStack();
        }
    }

    //public static void supportStatusBarUnderKitkat(@NonNull Activity activity,
    //        @NonNull Toolbar toolbar) {
    //    toolbar.setPadding(0, getStatusBarHeight(activity), 0, 0);
    //    tintColorSupport(activity);
    //}

    //private static void tintColorSupport(@NonNull Activity activity) {
    //    // create our manager instance after the content view is set
    //    SystemBarTintManager tintManager = new SystemBarTintManager(activity);
    //    // enable status bar tint
    //    tintManager.setStatusBarTintEnabled(true);
    //    // set the transparent color of the status bar, 20% darker
    //    tintManager.setTintColor(activity.getResources().getColor(R.color.status_tint_color));
    //}

    //public static void setStatusBarColor(@NonNull Activity activity, @NonNull Toolbar toolbar,
    //        @ColorRes int color) {
    //    Window window = activity.getWindow();
    //    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
    //        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
    //                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    //        supportStatusBarUnderKitkat(activity, toolbar);
    //    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    //        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    //        window.setStatusBarColor(ContextCompat.getColor(activity, color));
    //    }
    //}
    //
    //public static void setGreyStatusBar(@NonNull Activity activity, @NonNull Toolbar toolbar) {
    //    setStatusBarColor(activity, toolbar, R.color.black_10);
    //}

    public static int getStatusBarHeight(@NonNull Activity activity) {
        int result = 0;
        int resourceId =
                activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = activity.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int getNavigationBarHeight(@NonNull Activity activity) {
        int result = 0;
        int resourceId =
                activity.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = activity.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static void showSystemUI(Activity activity) {
        activity.getWindow()
                .getDecorView()
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    public static void hideSystemUI(Activity activity) {
        activity.getWindow()
                .getDecorView()
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
}
