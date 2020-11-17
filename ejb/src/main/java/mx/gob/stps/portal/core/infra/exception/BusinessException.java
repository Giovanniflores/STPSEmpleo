package mx.gob.stps.portal.core.infra.exception;

import javax.ejb.ApplicationException;

/**
 * Clase para el manejo de Excepciones de Negocio.
 */
@ApplicationException(rollback=true)
public class BusinessException extends Exception {

    /** Serial VErsion. */
    private static final long serialVersionUID = 1240341569552243194L;

    /** Contructor. */
    public BusinessException() {

    }

    /**
     * Contructor.
     * @param message Mensaje de error.
     */
    public BusinessException(final String message) {
        super(message);
    }

    /**
     * Contructor.
     * @param cause Causa de error.
     */
    public BusinessException(final Throwable cause) {
        super(cause);
    }

    /**
     * Contructor.
     * @param message Mensaje de error.
     * @param cause Causa de error.
     */
    public BusinessException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
