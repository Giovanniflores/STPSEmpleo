package mx.gob.stps.portal.core.infra.exception;

import javax.ejb.ApplicationException;

/**
 * Clase para el manejo de Excepciones de Infraestructura.
 */
@ApplicationException(rollback=true)
public class TechnicalException extends Exception {
    private static final long serialVersionUID = -1425132300971609586L;

    /** Contructor. */
    public TechnicalException() {}

    /**
     * Contructor.
     * @param message Mensaje de error.
     */
    public TechnicalException(final String message) {
        super(message);
    }

    /**
     * Contructor.
     * @param cause Causa de error.
     */
    public TechnicalException(final Throwable cause) {
        super(cause);
    }

    /**
     * Contructor.
     * @param message Mensaje de error.
     * @param cause Causa de error.
     */
    public TechnicalException(final String message, final Throwable cause) {
        super(message, cause);
    }
}