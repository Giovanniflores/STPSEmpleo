package mx.gob.stps.portal.core.seguridad.exception;

public class CorreoRepetidoException extends Exception {
	private static final long serialVersionUID = -8269598882462570635L;

	public CorreoRepetidoException() {super();}
	
    public CorreoRepetidoException(final String message) {super(message);}

    public CorreoRepetidoException(final Throwable cause) {super(cause);}

    public CorreoRepetidoException(final String message, final Throwable cause) {super(message, cause);}
}