package mx.gob.stps.portal.movil.app.model.base;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class AuthenticationPostDTO {

	String deviceId;
	String userNameOrEmail;
	String password;
	String tipo;
	
	public AuthenticationPostDTO(){
		
	}
	
	public AuthenticationPostDTO(String deviceId, String userNameOrEmail, String password ){
		this.deviceId = deviceId;
		this.userNameOrEmail = userNameOrEmail;
		this.password = password;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getUserNameOrEmail() {
		return userNameOrEmail;
	}
	public void setUserNameOrEmail(String userNameOrEmail) {
		this.userNameOrEmail = userNameOrEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
}
