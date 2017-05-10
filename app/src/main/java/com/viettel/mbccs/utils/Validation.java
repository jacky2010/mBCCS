package com.viettel.mbccs.utils;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.IntDef;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseModel;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by FRAMGIA\bui.dinh.viet on 08/03/2017.
 */

public class Validation {
    private static final String TAG = Validation.class.getName();
    private static final String BREAK_LINE = "\n";

    private SparseIntArray mErrorTrace; // string res as key, valid type as value
    private SparseArray<Method> mValidatedMethod;
    private List<Integer> mNewestInvalid; // list error string res
    private BaseModel mModelCache;
    private Context mContext;
    private boolean mIsValid;

    public enum Type {
        NICKNAME, EMAIL, PASSWORD, INQUIRY_NAME, INQUIRY_CONTENT, PRICE
    }

    @IntDef({
            ValidType.SPECIAL_CHARS, ValidType.NON_EMPTY, ValidType.AI_FULL_NAME,
            ValidType.VALID_PASSWORD, ValidType.VALID_EMAIL, ValidType.VALID_USERNAME,
            ValidType.VALID_PASSWORD_CONFIRMATION, ValidType.VALID_INQUIRY_CONTENT
    })

    public @interface ValidType {
        int SPECIAL_CHARS = 0x02;
        int NON_EMPTY = 0x03;
        int AI_FULL_NAME = 0x0C;
        int VALID_PASSWORD = 0x13;
        int VALID_USERNAME = 0x14;
        int VALID_EMAIL = 0x15;
        int VALID_PASSWORD_CONFIRMATION = 0x16;
        int VALID_INQUIRY_CONTENT = 0x17;
    }

    @Documented
    @Target({ ElementType.FIELD, ElementType.METHOD })
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Validate {
        Rule[] value();
    }

    @Documented
    @Target({ ElementType.ANNOTATION_TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Rule {
        @ValidType int[] types();

        @StringRes int message() default -1;
    }

    @Documented
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface ValidMethod {
        @ValidType int[] type();
    }

    public Validation(Context context) {
        mContext = context;
        mErrorTrace = new SparseIntArray();
        mValidatedMethod = cacheValidatedMethod();
        mNewestInvalid = new ArrayList<>();
    }

    /**
     * Call this method before execute validation with a model to reset trace and cache as well as
     * eliminate unexpected behaviors
     */
    public <T extends BaseModel> Validation prepare(T model) {
        mModelCache = model.cloneData();
        mErrorTrace.clear();
        mNewestInvalid.clear();
        return this;
    }

    /**
     * Check whether validation contains valid type or not.
     */
    public boolean containsErrors(@ValidType int... validTypes) {
        for (int value : validTypes) {
            if (mErrorTrace.indexOfValue(value) != -1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Validate a field and log the invalid in {@link Validation#mErrorTrace}.
     * Beside it, log the new invalids which occur in {@link Validation#mNewestInvalid}.
     */
    private void validate(Object factor, Rule[] rules, boolean isOptional) {
        mNewestInvalid.clear();
        for (Rule rule : rules) {
            for (int type : rule.types()) {
                try {
                    Method method = mValidatedMethod.get(type);
                    if (method == null) {
                        continue;
                    }
                    boolean isValid =
                            isOptional && isValidOptional(factor) || (boolean) method.invoke(this,
                                    factor);
                    if (isValid) {
                        mErrorTrace.delete(rule.message());
                    } else {
                        mErrorTrace.put(rule.message(), type);
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    Log.e(TAG, "validate: ", e);
                }
                if (mErrorTrace.indexOfValue(type) != -1) {
                    mNewestInvalid.add(rule.message());
                }
            }
        }
    }

    /**
     * Check if an object in Validator Model with annotation {@link Optional} is valid
     */
    private boolean isValidOptional(Object factor) {
        if (factor instanceof Integer) {
            return (Integer) factor == 0;
        }
        if (factor instanceof Long) {
            return (Long) factor == 0;
        }
        if (factor instanceof Float) {
            return (Float) factor == 0.0f;
        }
        if (factor instanceof Double) {
            return (Double) factor == 0.0f;
        }
        if (factor instanceof String) {
            return TextUtils.isEmpty((String) factor);
        }
        return factor instanceof Boolean && !((Boolean) factor) || factor == null;
    }

    /**
     * Base on annotation of fields in class to validate model
     * Cache model in order to detect modified field which need validation
     */
    public <T extends BaseModel> void validate(T model, boolean onlyValidateTheChange) {
        if (model == null || mModelCache == null) {
            return;
        }
        for (Field field : model.getClass().getDeclaredFields()) {
            Validate annotation = field.getAnnotation(Validate.class);
            Optional optional = field.getAnnotation(Optional.class);
            boolean isOptional = optional != null;
            if (annotation == null) {
                continue;
            }
            field.setAccessible(true);
            try {
                Object cache = field.get(mModelCache);
                Object real = field.get(model);
                // detect when data has changed only if onlyValidateTheChange is true.
                // Otherwise, always run validation on all of fields .
                if (!onlyValidateTheChange
                        || cache == null && real != null
                        || cache != null && !cache.equals(real)) {
                    validate(real, annotation.value(), isOptional);
                }
            } catch (IllegalAccessException e) {
                Log.e(TAG, "validate: ", e);
            }
        }
        mModelCache = model.cloneData();
    }

    /**
     * Base on {@link Validate}, build and cache method inside {@link Validation} to
     * speed up performance
     */
    private SparseArray<Method> cacheValidatedMethod() {
        SparseArray<Method> methods = new SparseArray<>();
        for (Method method : this.getClass().getDeclaredMethods()) {
            if (!method.isAnnotationPresent(ValidMethod.class)) {
                continue;
            }
            ValidMethod validMethod = method.getAnnotation(ValidMethod.class);
            for (int type : validMethod.type()) {
                methods.put(type, method);
            }
        }
        return methods;
    }

    /**
     * Because we only care about the newest invalid that occurred to show on UI
     *
     * @return error message to display
     */
    public String getNewestErrors() {
        if (mNewestInvalid.size() == 0) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0, size = mNewestInvalid.size(); i < size; i++) {
            if (mNewestInvalid.get(i) == -1) {
                continue;
            }
            builder.append(mContext.getString(mNewestInvalid.get(i)));
            if (i + 1 < size) {
                builder.append(BREAK_LINE);
            }
        }
        return builder.toString();
    }

    /**
     * call this method before start checking
     */
    public void prepare() {
        mIsValid = true;
    }

    /**
     * @return The model is valid or invalid
     */
    public boolean isValid() {
        return mIsValid;
    }

    @ValidMethod(type = ValidType.VALID_EMAIL)
    public boolean isErrorEmail(String str) {
        return TextUtils.isEmpty(str) || !StringUtils.isEmailString(str);
    }

    @ValidMethod(type = ValidType.VALID_USERNAME)
    public boolean isErrorUsername(String str) {
        return TextUtils.isEmpty(str) || !StringUtils.getPatternNickName().matcher(str).find();
    }

    @ValidMethod(type = ValidType.VALID_PASSWORD)
    public boolean isErrorPassword(String str) {
        return TextUtils.isEmpty(str) || str.length() < mContext.getResources()
                .getInteger(R.integer.min_length_password) || !StringUtils.getPatternAlphanumeric()
                .matcher(str)
                .find() || !StringUtils.isHalfWidthString(str);
    }

    @ValidMethod(type = ValidType.VALID_PASSWORD_CONFIRMATION)
    public boolean isErrorRePassword(String password, String passwordConfirmation) {
        return TextUtils.isEmpty(password) || !password.equals(passwordConfirmation);
    }

    /**
     * validate nickname
     *
     * @return error message
     * return null if valid
     */
    public String getErrorNickname(String nickname) {
        if (TextUtils.isEmpty(nickname)) {
            return mContext.getString(R.string.error_required_field);
        }
        if (!StringUtils.isNicknameString(nickname)) {
            return mContext.getString(R.string.error_invalid_character);
        }
        return null;
    }

    /**
     * validate email
     *
     * @return error message
     * return null if valid
     */
    public String getErrorEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            return mContext.getString(R.string.error_required_field);
        }
        if (!StringUtils.isHalfWidthString(email)) {
            return mContext.getString(R.string.error_full_width_character);
        }
        if (!StringUtils.isFormatEmailString(email)) {
            return mContext.getString(R.string.error_invalid_format);
        }
        // first local part is not special character,
        // last local part is not special character,
        // not contain continuous duplicate special characters
        if (!StringUtils.isEmailString(email)
                || StringUtils.hasContinuousDuplicateSpecialCharacters(email)) {
            return mContext.getString(R.string.error_invalid_character);
        }
        if (!StringUtils.isEmailValidLength(email)) {
            return mContext.getString(R.string.error_invalid_length);
        }

        return null;
    }

    /**
     * validate password
     *
     * @return error message
     * return null if valid
     */
    public String getErrorPassword(String password) {
        if (TextUtils.isEmpty(password)) {
            return mContext.getString(R.string.error_required_field);
        }
        if (password.length() < mContext.getResources().getInteger(R.integer.min_length_password)) {
            return mContext.getString(R.string.error_invalid_length);
        }
        if (!StringUtils.isHalfWidthString(password)) {
            return mContext.getString(R.string.error_full_width_character);
        }
        if (!StringUtils.isAlphanumericString(password)) {
            return mContext.getString(R.string.error_invalid_character);
        }
        return null;
    }

    /**
     * validate password confirmation
     *
     * @return error message
     * return null if valid
     */
    public String getErrorPasswordConfirmation(String password, String passwordConfirmation) {
        if (TextUtils.isEmpty(passwordConfirmation)) {
            return mContext.getString(R.string.error_required_field);
        }
        if (TextUtils.isEmpty(password) || !password.equals(passwordConfirmation)) {
            return mContext.getString(R.string.error_password_confirmation);
        }
        return null;
    }

    /**
     * validate inquiry name
     *
     * @return error message
     * return null if valid
     */
    public String getErrorInquiryName(String name) {
        if (null == name || TextUtils.isEmpty(name.trim())) {
            return mContext.getString(R.string.error_required_field);
        }
        return null;
    }

    public String getErorrInquiryContent(String content) {
        if (null == content || TextUtils.isEmpty(content.trim())) {
            return mContext.getString(R.string.error_required_field);
        }
        return null;
    }

    public void validate(Type type, String text, ObservableField<String> errorField) {
        String errorMessage = null;
        switch (type) {
            case NICKNAME:
                errorMessage = getErrorNickname(text);
                break;
            case EMAIL:
                errorMessage = getErrorEmail(text);
                break;
            case PASSWORD:
                errorMessage = getErrorPassword(text);
                break;
            case INQUIRY_NAME:
                errorMessage = getErrorInquiryName(text);
                break;
            case INQUIRY_CONTENT:
                errorMessage = getErorrInquiryContent(text);
                break;
            default:
                break;
        }
        errorField.set(errorMessage);
        updateValidationState(errorMessage);
    }

    public void validatePasswordConfirmation(String password, String passwordConfirmation,
            ObservableField<String> errorField) {
        String errorMessage = getErrorPasswordConfirmation(password, passwordConfirmation);
        errorField.set(errorMessage);
        updateValidationState(errorMessage);
    }

    private void updateValidationState(String errorMessage) {
        if (errorMessage != null) {
            mIsValid = false;
        }
    }
}
