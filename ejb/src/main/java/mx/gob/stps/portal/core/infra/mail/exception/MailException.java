package mx.gob.stps.portal.core.infra.mail.exception;

/**
 * La clase MailException tiene por objetivo el crear un objeto Excepción para cualquier eventualidad en el envío de correo.
 */
public class MailException extends Exception {

	private static final long serialVersionUID = 8278559735375601111L;

	/**
	 * Una instancia nueva de mail exception.
	 */
	public MailException() {
		super();
	}

	/**
	 * Una instancia nueva de mail exception.
	 * 
	 * @param message el message
	 */
	public MailException(String message) {
		super(message);
	}

	/**
	 * Una instancia nueva de mail exception.
	 * 
	 * @param message el message
	 * @param cause el cause
	 */
	public MailException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Una instancia nueva de mail exception.
	 * 
	 * @param cause el cause
	 */
	public MailException(Throwable cause) {
		super(cause);
	}

}