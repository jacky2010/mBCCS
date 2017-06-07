package com.viettel.mbccs.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EditTextUtil {

    public static void fixFontPasswordType(EditText edt) {
        edt.setTypeface(Typeface.DEFAULT);
    }

    private static final int MAX_PASSWORD_LENGTH = 8;

    private static final String PASSWORD_PATTERN = "^[A-Za-z\\d!$%@#*?&]{8,}$";


    public static void hideSoftKeyboard(EditText edt, Context ctx) {
        if (edt == null || ctx == null) {
            return;
        }

        InputMethodManager imm = (InputMethodManager) ctx
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edt.getWindowToken(), 0);
    }

    public static void showKeyboard(EditText edt, Context ctx) {
        if (edt == null || ctx == null) {
            return;
        }

        InputMethodManager imm = (InputMethodManager) ctx
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(edt, InputMethodManager.SHOW_IMPLICIT);
    }

    public static void showKeyBoardRun(final EditText edt, final Activity ctx) {
        edt.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager keyboard = (InputMethodManager) ctx
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                keyboard.showSoftInput(edt, InputMethodManager.SHOW_IMPLICIT);

            }
        }, 200);
    }

    public static void hideKeyboard(Activity activity) {
        if (activity == null) {
            return;
        }
        activity.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }// end method

    public static void hideSoftKeyboard(Activity activity) {
        if (activity == null) {
            return;
        }
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

    }

    public interface EnterListener {
        void onEnterPress();
    }

    public static void setOnEnterListener(EditText edt,
                                          final EnterListener enterListener) {
        edt.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN
                        && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    if (enterListener != null) {
                        enterListener.onEnterPress();
                    }
                }
                return false;
            }
        });
    }

    public static boolean isCheckShow(Context context) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        if (imm.isAcceptingText()) {
            return true;
        } else {
            return false;
        }
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public static boolean isPasswordValid(CharSequence password) {
        // TODO add more password validation
        if (TextUtils.isEmpty(password)) {
            return false;
        }

        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean isPasswordMatch(CharSequence password, CharSequence repeat) {
        if (android.text.TextUtils.isEmpty(password) || TextUtils.isEmpty(repeat)) {
            return false;
        }

        return password.toString().equals(repeat.toString());
    }
}
