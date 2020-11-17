package mx.gob.stps.portal.core.infra.mail.service;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * La clase SMTPAuthenticator tiene por objetivo establecer y obtener los valores de autenticación para el SMTP.
 */
public class SMTPAuthenticator extends Authenticator {
	
	/** El username. */
	private String username;
	
	/** El password. */
	private String password;
	
	/**
	 * Una instancia nueva de sMTP authenticator.
	 */
	public SMTPAuthenticator(){
	}
	
	/**
	 * Una instancia nueva de sMTP authenticator.
	 * 
	 * @param username el username
	 * @param password el password
	 */
	public SMTPAuthenticator(String username, String password){
		this.username = username;
		this.password = password;
	}

	/* (non-Javadoc)
	 * @see javax.mail.Authenticator#getPasswordAuthentication()
	 */
	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	}
	
	/**
	 * Obtiene un objeto del tipo: password.
	 * 
	 * @return Un objeto del tipo: password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Establece el valor: password.
	 * 
	 * @param password, el nuevo password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Obtiene un objeto del tipo: username.
	 * 
	 * @return Un objeto del tipo: username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Establece el valor: username.
	 * 
	 * @param username, el nuevo username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
}
