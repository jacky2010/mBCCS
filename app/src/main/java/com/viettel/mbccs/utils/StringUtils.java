package com.viettel.mbccs.utils;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

public class StringUtils {

    public static final String NICKNAME_PATTERN = "^[a-zA-Z一-龯ぁ-んァ-ン ]+$";
    public static final String FORMAT_EMAIL_PATTERN =
            "^[A-Z0-9a-z._-]+@[A-Za-z0-9.-]+\\.[a-z][A-Za-z0-9._-]+$";
    public static final String EMAIL_PATTERN = "^[A-Z0-9a-z][A-Z0-9a-z._-]+[A-Za-z0-9]@";
    public static final String EMAIL_VALID_LENGTH_PATTERN =
            "^([A-Z0-9a-z._-]{1,64}@[A-Z0-9a-z._-]+){1,255}";
    public static final String ALPHANUMERIC_PATTERN = "^[a-zA-Z0-9]*$";
    public static final String DUPLICATE_SPECIAL_CHARACTERS_PATTERN = "[\\.\\-\\_][\\.\\-\\_]";

    public static boolean isHalfWidthString(String text) {
        boolean isHalfWidthString = true;
        char[] chars = text.toCharArray();
        for (char c : chars) {
            isHalfWidthString = isHalfWidthString && isHalfWidthChar(c);
        }
        return isHalfWidthString;
    }

    public static boolean isHalfWidthChar(char c) {
        return c <= '\u00FF' || '\uFF61' <= c && c <= '\uFFDC' || '\uFFE8' <= c && c <= '\uFFEE';
    }

    public static boolean isAlphanumericString(String text) {
        return Pattern.compile(ALPHANUMERIC_PATTERN, Pattern.CASE_INSENSITIVE).matcher(text).find();
    }

    public static boolean isEmailString(String text) {
        return getPatternEmail().matcher(text).find();
    }

    public static boolean isFormatEmailString(String text) {
        return Pattern.compile(FORMAT_EMAIL_PATTERN, Pattern.CASE_INSENSITIVE).matcher(text).find();
    }

    public static boolean isEmailValidLength(String text) {
        return Pattern.compile(EMAIL_VALID_LENGTH_PATTERN, Pattern.CASE_INSENSITIVE)
                .matcher(text)
                .find();
    }

    public static boolean isNicknameString(String text) {
        return Pattern.compile(NICKNAME_PATTERN, Pattern.CASE_INSENSITIVE).matcher(text).find();
    }

    public static Pattern getPatternEmail() {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.UNICODE_CASE);
        return pattern;
    }

    public static Pattern getPatternNickName() {
        Pattern pattern = Pattern.compile(NICKNAME_PATTERN, Pattern.CASE_INSENSITIVE);
        return pattern;
    }

    public static Pattern getPatternAlphanumeric() {
        return Pattern.compile(ALPHANUMERIC_PATTERN, Pattern.CASE_INSENSITIVE);
    }

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String source) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(source);
        }
    }

    public static boolean hasContinuousDuplicateSpecialCharacters(String text) {
        return Pattern.compile(DUPLICATE_SPECIAL_CHARACTERS_PATTERN, Pattern.CASE_INSENSITIVE)
                .matcher(text)
                .find();
    }

    public static String loadJSONFromAsset(Context context, String filename) throws IOException {
        String json = null;
        InputStream is = context.getResources().getAssets().open(filename);
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        json = new String(buffer, "UTF-8");
        return json;
    }

    public static int formatPrice(String price) {
        return Integer.parseInt(price.replaceAll("[\\.\\,\\D]", ""));
    }

    public static String changeTextToBold(String word) {
        return "<b>"+word+"</b>";
    }
}
