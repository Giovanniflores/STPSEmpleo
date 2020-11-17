package mx.gob.stps.portal.movil.app.glasses.exception;

public class CookieException extends Exception {

    private String error;

    public CookieException() {
        super();    // call superclass constructor
        error = "unknown";
    }

    public CookieException(String err) {
        super(err);     // call super class constructor
        error = err;    // save message
    }

    public String getError() {
        return error;
    }

    @Override
    public String  toString() {
        return getError();
    }
}