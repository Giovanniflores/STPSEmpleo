package mx.gob.stps.portal.core.infra.utils;

import java.security.MessageDigest;

import mx.gob.stps.portal.core.infra.exception.EncodingException;

import org.apache.commons.codec.binary.Base64;

/**
 * La clase GeneraPasswordAleatorio es la encargada de construir contraseñas aleatorias.
 */
public final class Password {
	
	
	public static String NUMEROS = "0123456789";
	 
	public static String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
 
	public static String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
 
	//public static String ESPECIALES = "$%&/()=?¡";
	
	private Password(){}

	public static String getPinNumber() {
		return getPassword(NUMEROS, 4);
	}
 
	public static String getPassword() {
		return getPassword(8);
	}
 
	public static String getPassword(int length) {
		return getPassword(NUMEROS + MAYUSCULAS + MINUSCULAS, length);
	}
 
	public static String getPassword(String key, int length) {
		String pswd = "";
 
		for (int i = 0; i < length; i++) {
			pswd+=(key.charAt((int)(Math.random() * key.length())));
		}
 
		return pswd;
	}
	
	/**
	 * Codifica la contraseña del usuario. 
	 * @param password Contraseña no codificada
	 * @return Contraseña codificada
	 * @throws Exception
	 */
	public static String codificaPassword(String password) throws EncodingException {
		String sPasswordCodific		=	null;
		MessageDigest 	msgDig		= 	null;

		if (password==null || password.isEmpty()) throw new IllegalArgumentException("Contraseña requerida");

		byte[] pwdAsBytes 			=	null;
		byte[] pwdAsBytesCodificado	=	null;

		try{
			msgDig 					= 	MessageDigest.getInstance("SHA-256");
			pwdAsBytes 				= 	password.getBytes("UTF-8");
			msgDig.update(pwdAsBytes);
		    pwdAsBytesCodificado 	= 	msgDig.digest();

		    byte[] encoded = Base64.encodeBase64(pwdAsBytesCodificado);			
			
		    sPasswordCodific = new String(encoded);			
		}catch(Exception e){
			e.printStackTrace();
			throw new EncodingException(e);
		}
	    
		return sPasswordCodific;
	}
	
	/*COMENTAR EN PRODUCCION Y QA, MANTENER MIENTRAS HAYA GENERACION DE PUBLICADORES MANUAL
	public static void main(String [] args)
	{
		String[] arrPasswr ={"ZUjz68UV","nqzbSjBm","Ls75bBfR","S8vTcUCU","vgH8g6DQ"};	
		String codificada ="";
		if(null!=arrPasswr){
			for(int i=0;i<arrPasswr.length; i++){
				String strOrigen = arrPasswr[i].toString();
				System.out.println("-----strOrigen:" + strOrigen);			
				try {
					codificada = codificaPassword(strOrigen);
				} catch (EncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("-----codificada:" + codificada);				
			}

		}
	}		
	 */
	
}
