package mx.gob.stps.portal.web.infra.vo;

import java.io.Serializable;

public class ResultVO implements Serializable {
	private static final long serialVersionUID = -2350522818588009495L;
	private String message;
	private String type;
	private String value;
	
	public static final String TYPE_SUCCESS = "ext";
	public static final String TYPE_ERROR = "err";
	
	public ResultVO(String message, String type){
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
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
