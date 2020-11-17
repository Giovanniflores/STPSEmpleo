package mx.gob.stps.portal.core.infra.exception;

public class EncodingException extends Exception {
	private static final long serialVersionUID = 7420408420629137943L;
	public EncodingException() {super();}
	public EncodingException(String message) {super(message);}
	public EncodingException(String message, Throwable cause) {super(message, cause);}
	public EncodingException(Throwable cause) {super(cause);}
}
