package com.viettel.mbccs.data.source.remote.response;

import java.util.List;

public class BaseErrorResponse {

    List<Error> errors;

    public int getErrorCode() {
        return errors.get(0).code;
    }

    public String getErrorMessage() {
        return errors.get(0).message;
    }

    public void setError(int code, String message) {
        Error error = new Error();
        error.code = code;
        error.message = message;
        errors.add(error);
    }

    public boolean isError() {
        return errors != null;
    }

    private static final class Error {

        int code;

        String message;
    }
}
