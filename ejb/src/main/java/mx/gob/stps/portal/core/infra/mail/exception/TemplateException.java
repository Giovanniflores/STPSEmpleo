package mx.gob.stps.portal.core.infra.mail.exception;

/**
 * La clase TemplatelException tiene por objetivo el crear un objeto Excepción
 * para cualquier eventualidad durante la aplicacion de una plantilla al cuero del correo. 
 */
public class TemplateException extends Exception {

	private static final long serialVersionUID = 8278559735375601111L;

	/**
	 * Una instancia nueva de mail exception.
	 */
	public TemplateException() {
		super();
	}

	/**
	 * Una instancia nueva de mail exception.
	 * 
	 * @param message el message
	 */
	public TemplateException(String message) {
		super(message);
	}

	/**
	 * Una instancia nueva de mail exception.
	 * 
	 * @param message el message
	 * @param cause el cause
	 */
	public TemplateException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Una instancia nueva de mail exception.
	 * 
	 * @param cause el cause
	 */
	public TemplateException(Throwable cause) {
		super(cause);
	}

}