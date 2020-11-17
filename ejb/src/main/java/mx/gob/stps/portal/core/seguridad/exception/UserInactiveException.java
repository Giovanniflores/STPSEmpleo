package mx.gob.stps.portal.core.seguridad.exception;

/**
 * Lanzada cuando se consulta el propietario y no se encuentra activo
 * @author oscar.manzo
 *
 */
public class UserInactiveException extends Exception {
	private static final long serialVersionUID = 3920920365235834650L;

	public UserInactiveException() {
        super();
    }
	
	/**
     * Contructor.
     * @param message Mensaje de error.
     */
    public UserInactiveException(final String message) {
        super(message);
    }

    /**
     * Contructor.
     * @param cause Causa de error.
     */
    public UserInactiveException(final Throwable cause) {
        super(cause);
    }

    /**
     * Contructor.
     * @param message Mensaje de error.
     * @param cause Causa de error.
     */
    public UserInactiveException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
