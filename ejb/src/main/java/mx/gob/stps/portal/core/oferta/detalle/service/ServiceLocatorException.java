package mx.gob.stps.portal.core.oferta.detalle.service;

public class ServiceLocatorException extends Exception {
	private static final long serialVersionUID = -8840607056295489681L;

	public ServiceLocatorException() {
	}

	public ServiceLocatorException(String msg) {
		super(msg);
	}

	public ServiceLocatorException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ServiceLocatorException(Throwable cause) {
		super("Error al obtener la referencia del servicio", cause);
	}
}
