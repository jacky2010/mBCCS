package com.viettel.mbccs.data.source.remote.response;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import retrofit2.Response;

public class BaseException extends RuntimeException {

    private Type type;
    @Nullable
    private BaseErrorResponse errorResponse;
    @Nullable
    private Response response;

    private BaseException(Type type, Throwable cause) {
        super(cause.getMessage(), cause);
        this.type = type;
    }

    private BaseException(Type type, @Nullable BaseErrorResponse errorResponse) {
        this.type = type;
        this.errorResponse = errorResponse;
    }

    private BaseException(Type type, @Nullable Response response) {
        this.type = type;
        this.response = response;
    }

    public static BaseException toHttpError(Response response) {
        return new BaseException(Type.HTTP, response);
    }

    public static BaseException toNetworkError(Throwable cause) {
        return new BaseException(Type.NETWORK, cause);
    }

    public static BaseException toServerError(BaseErrorResponse errorResponse) {
        return new BaseException(Type.SERVER, errorResponse);
    }

    public static BaseException toUnexpectedError(Throwable cause) {
        return new BaseException(Type.UNEXPECTED, cause);
    }

    public Type getErrorType() {
        return type;
    }

    public String getMessage(@NonNull Context context) {
        switch (type) {
            case SERVER:
                if (errorResponse != null) {
                    return errorResponse.getErrorMessage();
                }
                return "";
            case NETWORK:
                return getNetworkErrorMessage(context, getCause());
            case HTTP:
                if (response != null) {
                    return getHttpErrorMessage(context, response.code());
                }
                return "";
            default:
                return "";
        }
    }

    public int getServerErrorCode() {
        return errorResponse != null ? errorResponse.getErrorCode() : -1;
    }

    private String getNetworkErrorMessage(Context context, Throwable throwable) {
        // TODO update later with Japanese
        if (throwable instanceof SocketTimeoutException) {
            return throwable.getMessage();
        }

        if (throwable instanceof UnknownHostException) {
            return throwable.getMessage();
        }

        if (throwable instanceof IOException) {
            return throwable.getMessage();
        }

        return throwable.getMessage();
    }

    private String getHttpErrorMessage(Context context, int httpCode) {
        // TODO update later with Japanese
        if (httpCode >= 300 && httpCode <= 308) {
            // Redirection
            return "Redirection operation has occurred";
        }
        if (httpCode >= 400 && httpCode <= 451) {
            // Client error
            return "Client error has occurred";
        }
        if (httpCode >= 500 && httpCode <= 511) {
            // Server error
            return "Server error has occurred";
        }

        // Unofficial error
        return "Unofficial error has occurred";
    }

    public enum Type {
        /**
         * An {@link IOException} occurred while communicating to the server.
         */
        NETWORK,
        /**
         * A non-2xx HTTP status code was received from the server.
         */
        HTTP,
        /**
         * A error server with code & message
         */
        SERVER,
        /**
         * An internal error occurred while attempting to execute a request. It is best practice to
         * re-throw this exception so your application crashes.
         */
        UNEXPECTED
    }
}
