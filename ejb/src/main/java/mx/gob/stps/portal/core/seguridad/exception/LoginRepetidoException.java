package mx.gob.stps.portal.core.seguridad.exception;

/**
 * Lanzada durante la validacion de registro de usuario en caso que el dato para el Login ya se encuentre registrado en el sitio
 */
public class LoginRepetidoException extends Exception {
	private static final long serialVersionUID = -509673771600800330L;

    public LoginRepetidoException() {
        super();
    }
	
	/**
     * Contructor.
     * @param message Mensaje de error.
     */
    public LoginRepetidoException(final String message) {
        super(message);
    }

    /**
     * Contructor.
     * @param cause Causa de error.
     */
    public LoginRepetidoException(final Throwable cause) {
        super(cause);
    }

    /**
     * Contructor.
     * @param message Mensaje de error.
     * @param cause Causa de error.
     */
    public LoginRepetidoException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
