package mx.gob.stps.portal.core.ws.renapo.exception;

/**
 * La clase ConsutlaWsPorCurpException denota un evento 
 * inesperado durante el proceso de consulta de ws por curp 
 */
public class ConsultaWsPorCurpException extends Exception {
	private static final long serialVersionUID = -6441516559929642826L;

	/**
	 * Una instancia nueva de consulta ws por curp exception.
	 */
	public ConsultaWsPorCurpException() {
		super();
	}

	/**
	 * Una instancia nueva de consulta ws por curp exception.
	 * 
	 * @param message, el message
	 */
	public ConsultaWsPorCurpException(String message) {
		super(message);
	}

	/**
	 * Una instancia nueva de consulta ws por curp exception.
	 * 
	 * @param message, el message
	 * @param cause, la causa
	 */
	public ConsultaWsPorCurpException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Una instancia nueva de consulta ws por curp exception.
	 * 
	 * @param cause, la cause
	 */
	public ConsultaWsPorCurpException(Throwable cause) {
		super(cause);
	}

}