package mx.gob.stps.portal.core.seguridad.exception;

public class CurpRepetidoException extends Exception {
	private static final long serialVersionUID = -2154740284093123375L;

	public CurpRepetidoException() {super();}
	
    public CurpRepetidoException(final String message) {super(message);}

    public CurpRepetidoException(final Throwable cause) {super(cause);}

    public CurpRepetidoException(final String message, final Throwable cause) {super(message, cause);}
}