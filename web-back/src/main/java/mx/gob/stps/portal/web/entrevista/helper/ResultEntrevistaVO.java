package mx.gob.stps.portal.web.entrevista.helper;

import java.io.Serializable;

public class ResultEntrevistaVO implements Serializable {
	private static final long serialVersionUID = 7670237590575042516L;
	private String message;
	private String type;
	
	public static final String TYPE_SUCCESS 		= "ext";
	public static final String TYPE_ERROR 			= "err";
	public static final String TYPE_ERROR_CORREO 	= "errCorreo";
	
	public ResultEntrevistaVO(String message, String type){
		this.message = message;
		this.type = type;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
