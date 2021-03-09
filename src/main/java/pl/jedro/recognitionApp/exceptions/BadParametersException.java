package pl.jedro.recognitionApp.exceptions;

public class BadParametersException extends RuntimeException {
    public BadParametersException() {
    }

    public BadParametersException(String message) {
        super(message);
    }

    public BadParametersException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadParametersException(Throwable cause) {
        super(cause);
    }

    public BadParametersException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
