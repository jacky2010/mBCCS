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

    public boolean isError() {
        return errors != null;
    }

    private static final class Error {

        int code;

        String message;
    }
}
