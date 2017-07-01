package com.viettel.mbccs.utils;

import java.util.regex.Pattern;

/**
 * Created by HuyQuyet on 6/28/17.
 */

public class PatternUtils {
    public static final String FORMAT_EMAIL_PATTERN =
            "^[A-Z0-9a-z._-]+@[A-Za-z0-9.-]+\\.[a-z][A-Za-z0-9._-]+$";
    public static final String EMAIL_PATTERN = "^[A-Z0-9a-z][A-Z0-9a-z._-]+[A-Za-z0-9]@";
    public static final String EMAIL_VALID_LENGTH_PATTERN =
            "^([A-Z0-9a-z._-]{1,64}@[A-Z0-9a-z._-]+){1,255}";
    public static final String ALPHANUMERIC_PATTERN = "^[a-zA-Z0-9]*$";
    public static final String DUPLICATE_SPECIAL_CHARACTERS_PATTERN = "[\\.\\-\\_][\\.\\-\\_]";

    public static final String PATTERN_TEXT_LENGTH_100 = "^[a-zA-Z]{1,100}$";

    public static boolean validateString(String data, String pattern) {
        return !StringUtils.isEmpty(data) && Pattern.matches(pattern, data);
    }
}
