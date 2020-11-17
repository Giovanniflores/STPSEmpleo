package mx.gob.stps.portal.core.infra.mail.exception;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import mx.gob.stps.portal.core.infra.mail.service.SMTPAuthenticator;
import mx.gob.stps.portal.core.infra.mail.template.util.BufferedDataSource;
import mx.gob.stps.portal.core.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.core.infra.utils.Utils;

import org.apache.log4j.Logger;

/**
 * La clase MailService apoya a generar instancias necesarias para el servico de correo, además de establecer
 * las operaciones necesarias de envío de correo.
 */
public final class MailServiceImpl {	
	private static Logger logger = Logger.getLogger(MailServiceImpl.class);

	/** El MIM e_ html. */
	private static final String MIME_HTML = "text/html";

	public static final String AUTH = "true"; // TODO Verificar configuracion para Autenticacion
	
	/** El mail service. */
	private static MailServiceImpl mailService = new MailServiceImpl();
		
	// contructor privado
	/**
	 * Una instancia nueva de mail service.
	 */
	private MailServiceImpl(){}
	
	/**
	 * Obtiene una instancia de  MailService.
	 * 
	 * @return instancia singleton de MailService
	 */
	public static MailServiceImpl getInstance(){
		return mailService;
	}

	public synchronized void enviarCorreo(String remitente, String[] tos, String subject, String mensaje) throws MailException {
		enviarCorreo(remitente, tos, null, null, subject, mensaje);
	}
	
	/**
	 * Realiza el envio del correo.
	 * @param remitente
	 * @param tos
	 * @param subject
	 * @param mensaje
	 * @throws MailException
	 */
	public synchronized void enviarCorreo(String remitente, String[] tos, String[] toscc, String[] tocco, String subject, String mensaje) throws MailException {
		try {
			byte[] data = null;
			enviarCorreo(remitente, tos, toscc, tocco, subject, mensaje, null, data);
		} catch (IOException e) {
			logger.error(e); e.printStackTrace();
			throw new MailException(e);
		}
	}

	public synchronized void enviarCorreo(String remitente, String[] tos, String subject, String mensaje, 
                                          String attacheName, byte[] archivoAttachment) throws MailException, IOException {
		enviarCorreo(remitente, tos, null, null, subject, mensaje, attacheName, archivoAttachment);
	}

	public void enviarCorreo(String remitente, String[] tos, String subject, String mensaje, String attacheName, File attachment) throws MailException, IOException {
		enviarCorreo(remitente, tos, null, null, subject, mensaje, attacheName, attachment);	
	}
	
	public void enviarCorreo(String remitente, String[] tos, String[] toscc, String[] tocco, String subject, String mensaje, String attacheName, File attachment) throws MailException, IOException {
		byte[] archivoAttachment = Utils.getBytes(new FileInputStream(attachment));
		enviarCorreo(remitente, tos, toscc, tocco, subject, mensaje, attacheName, archivoAttachment);
	}
	

	public synchronized void enviarCorreo(String remitente, String[] tos, String[] toscc, String[] tocco, String subject, String mensaje, 
			                              String attacheName, byte[] archivoAttachment) throws MailException, IOException {
		InternetAddress[] destinatarios = null;

		try{
			//Create a mail session
			PropertiesLoader properties = PropertiesLoader.getInstance();

			Properties props = new Properties();
			props.put("mail.smtp.host", properties.getProperty("email.host"));
			props.put("mail.smtp.port", properties.getProperty("email.port"));
			props.put("mail.transport.protocol", properties.getProperty("email.protocol"));
			
			// Verifica si se crea la session con autenticacion
			Session session = null;
			//Transport transport = null;
			if ("true".equalsIgnoreCase(AUTH)){

				String login = properties.getProperty("email.login");
				String passw = properties.getProperty("email.passw");

				Authenticator auth = new SMTPAuthenticator(login, passw);
				//Session session = Session.getDefaultInstance(props, auth);

				session = Session.getInstance(props, auth);
				 /*transport = session.getTransport();
				transport.connect(properties.getProperty("email.host") ,
				                  Integer.parseInt(properties.getProperty("email.port")),  
			    		          properties.getProperty("email.login"), "@stps.gob.mx");
			*/
				
			}else{
				session = Session.getDefaultInstance(props);
			}

			boolean indebug = Boolean.valueOf(properties.getProperty("email.debug"));
			session.setDebug(indebug);

			Message message = new MimeMessage(session);//Define message
			message.setFrom(new InternetAddress(remitente));
			
			if (tos != null && tos.length > 0) {
				destinatarios = new InternetAddress[tos.length]; 
				int i = 0;
				for (String destinatario : tos) {
					destinatarios[i] = new InternetAddress(destinatario);
					i++;
				}// for
			
				message.addRecipients(Message.RecipientType.TO, destinatarios);
			} else {
				throw new MailException("Detinatarios no indicados");
			}

			// CON COPIA A...
			if (toscc != null && toscc.length > 0) {
				InternetAddress[] addressCc = new InternetAddress[toscc.length]; 
				int i = 0;
				for (String to : toscc) {
					addressCc[i] = new InternetAddress(to);
					i++;
				}// for
				
				message.addRecipients(Message.RecipientType.CC, addressCc);
			}			

			// CON COPIA OCULTA A...
			if (tocco!= null && tocco.length > 0) {
				InternetAddress[] addressCco = new InternetAddress[tocco.length]; 
				int i = 0;
				for (String to : tocco) {
					addressCco[i] = new InternetAddress(to);
					i++;
				}// for
				
				message.addRecipients(Message.RecipientType.BCC, addressCco);
			}			
			
			message.setSubject(subject);

			boolean isHtml = isHtml(mensaje);
			
			if (attacheName!=null && archivoAttachment!=null){
				BodyPart messageBodyPart = new MimeBodyPart();//Create the message part

				if (isHtml){
					messageBodyPart.setContent(mensaje, MIME_HTML);
				}else{
					messageBodyPart.setText(mensaje);				
				}

				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(messageBodyPart);

				//Part two is attachment
				messageBodyPart = new MimeBodyPart();
				BufferedDataSource source = new BufferedDataSource(archivoAttachment, attacheName);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(attacheName);
				multipart.addBodyPart(messageBodyPart);

				message.setContent(multipart);//Put parts in message
			}else{
				if (isHtml){
					message.setContent(mensaje, MIME_HTML);
				}else{
					message.setText(mensaje);				
				}
			}

			 /*
			    transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			    transport.close();
			*/
			Transport.send(message);// Send the message
		
		}catch(Exception e){
			logger.error(e); e.printStackTrace();
			throw new MailException(e);
		}
	}

	
	
	
	
	/**
	 * Realiza el envio del correo.
	 * @param remitente
	 * @param tos
	 * @param subject
	 * @param mensaje
	 * @param attacheName
	 * @param archivoAttachment
	 * @throws MailException
	 * @throws IOException
	 */
	public synchronized void enviarCorreo(String remitente, String[] tos, String[] toscc, String[] tocco, String subject, String mensaje, 
			                              String[] attacheNames, List<byte[]> archivosAttachment) throws MailException, IOException {
		InternetAddress[] destinatarios = null;

		try{
			//Create a mail session
			PropertiesLoader properties = PropertiesLoader.getInstance();

			Properties props = new Properties();
			props.put("mail.smtp.host", properties.getProperty("email.host"));
			props.put("mail.smtp.port", properties.getProperty("email.port"));
			props.put("mail.transport.protocol", properties.getProperty("email.protocol"));
			
			
			//TODO solo para prueba local
			//props.put("mail.smtp.port", "587");
			//props.put("mail.smtp.auth", "true");	
			//props.put("mail.smtp.starttls.enable", "true");		
			
			// Verifica si se crea la session con autenticacion
			Session session = null;
			//Transport transport = null;
			if ("true".equalsIgnoreCase(AUTH)){

				String login = properties.getProperty("email.login");
				String passw = properties.getProperty("email.passw");

				Authenticator auth = new SMTPAuthenticator(login, passw);
				//Session session = Session.getDefaultInstance(props, auth);

				session = Session.getInstance(props, auth);
				 /*transport = session.getTransport();
				transport.connect(properties.getProperty("email.host") ,
				                  Integer.parseInt(properties.getProperty("email.port")),  
			    		          properties.getProperty("email.login"), "@stps.gob.mx");
			*/
				
			}else{
				session = Session.getDefaultInstance(props);
			}

			boolean indebug = Boolean.valueOf(properties.getProperty("email.debug"));
			session.setDebug(indebug);

			Message message = new MimeMessage(session);//Define message
			message.setFrom(new InternetAddress(remitente));
			
			if (tos != null && tos.length > 0) {
				destinatarios = new InternetAddress[tos.length]; 
				int i = 0;
				for (String destinatario : tos) {
					destinatarios[i] = new InternetAddress(destinatario);
					i++;
				}// for
			
				message.addRecipients(Message.RecipientType.TO, destinatarios);
			} else {
				throw new MailException("Detinatarios no indicados");
			}

			// CON COPIA A...
			if (toscc != null && toscc.length > 0) {
				InternetAddress[] addressCc = new InternetAddress[toscc.length]; 
				int i = 0;
				for (String to : toscc) {
					addressCc[i] = new InternetAddress(to);
					i++;
				}// for
				
				message.addRecipients(Message.RecipientType.CC, addressCc);
			}			

			// CON COPIA OCULTA A...
			if (tocco!= null && tocco.length > 0) {
				InternetAddress[] addressCco = new InternetAddress[tocco.length]; 
				int i = 0;
				for (String to : tocco) {
					addressCco[i] = new InternetAddress(to);
					i++;
				}// for
				
				message.addRecipients(Message.RecipientType.BCC, addressCco);
			}			
			
			message.setSubject(subject);

			boolean isHtml = isHtml(mensaje);
			
			if (attacheNames!=null && attacheNames.length>0 && archivosAttachment!=null && !archivosAttachment.isEmpty()){
				
				BodyPart messageBodyPart = new MimeBodyPart();//Create the message part

				if (isHtml){
					messageBodyPart.setContent(mensaje, MIME_HTML);
				}else{
					messageBodyPart.setText(mensaje);				
				}

				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(messageBodyPart);

				int contador = 0;
				
				for(String attacheName : attacheNames){
					
					if(archivosAttachment.size()> contador){
						byte[] archivoAttachment = archivosAttachment.get(contador);
						
						messageBodyPart = new MimeBodyPart();
						
						ByteArrayDataSource source = new ByteArrayDataSource(archivoAttachment, "application/pdf"); 

						messageBodyPart.setFileName(attacheName);
						
						messageBodyPart.setDataHandler(new DataHandler(source));
						
						multipart.addBodyPart(messageBodyPart);
						
						contador++;
						
					} else {
						break;
					}					
					
				}				
				
				message.setContent(multipart);//Put parts in message
				
			}else{
				if (isHtml){
					message.setContent(mensaje, MIME_HTML);
				}else{
					message.setText(mensaje);				
				}
			}

			 /*
			    transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			    transport.close();
			*/
			Transport.send(message);// Send the message
		
		}catch(Exception e){
			logger.error(e); e.printStackTrace();
			throw new MailException(e);
		}
	}

	
	/**
	 * Verifica si es html.
	 * 
	 * @param msg  
	 * 
	 * @return true, si es html
	 */
	private boolean isHtml(String msg){
		boolean html = false;
		if (msg!=null){html = msg.toLowerCase().indexOf("<html")>=0;}
		return html;
	}

}
