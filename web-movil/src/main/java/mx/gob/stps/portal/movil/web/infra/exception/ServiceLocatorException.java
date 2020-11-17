package mx.gob.stps.portal.movil.web.infra.exception;

public class ServiceLocatorException extends Exception{
	private static final long serialVersionUID = 519603548633572719L; 
	
	public ServiceLocatorException() {}
	public ServiceLocatorException(String msg) {super(msg);}
	public ServiceLocatorException(String msg, Throwable cause) {super(msg, cause);}
	public ServiceLocatorException(Throwable cause) {super("Error al obtener la referencia del servicio", cause);}
}