package mx.gob.stps.portal.core.infra.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.sql.Timestamp;

import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.utils.Catalogos.TRABAJA_ACTUALMENTE;

import org.apache.commons.codec.binary.Base64;

public final class Utils {

	private static final SimpleDateFormat dateDDMMYYYY = new SimpleDateFormat("dd/MM/yyyy");
	private static final SimpleDateFormat dateYYYYMMDD = new SimpleDateFormat("yyyy/MM/dd/");
	private static final SimpleDateFormat timehmmssS = new SimpleDateFormat("h:mm:ss:S");
	
	private Utils() {
	}

	public static int parseInt(String numero){
	    int valor = -1;

	    if (esEntero(numero)){
	        valor = Integer.parseInt(numero);
	    }
	        
	    return valor;
	}

	public static long parseLong(String numero){
	    long valor = -1;
	    
	    if (esEntero(numero)){
	        valor = Long.parseLong(numero);
	    }
	                
	    return valor;
	}

	public static double parseDouble(String numero){
		double valor = -1;
	    
	    if (esNumero(numero)){
	        valor = Double.parseDouble(numero);
	    }
	                
	    return valor;
	}
	
	/**
	 * Method <b>validaIP</b>
	 * 
	 * @param valor
	 * @return boolean
	 */
	public static boolean validaIP(String valor) {
		return validaPatron(
				"[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3})\\s-\\s-\\s\\[([^\\]]+)\\]",
				valor);
	}

	/**
	 * Method <b>validaMail</b>
	 * 
	 * @param valor
	 * @return boolean
	 */
	public static boolean validaMail(String valor) {
		return validaPatron(
				"/^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$/",
				valor);
	}

	/**
	 * Method <b>validaPatron</b>
	 * 
	 * @param patron
	 *            , valor
	 * @return boolean
	 */
	public static boolean validaPatron(String patron, String valor) {
		Pattern p = Pattern.compile(patron);
		Matcher m = p.matcher(valor);
		boolean b = m.find();
		// return m.matches();
		return b;
	}

	/**
	 * Method <b>esAlfabetico</b>
	 * 
	 * @param cadena
	 *            cadena a evaluar
	 * 
	 * @return boolean true, en caso de Èxito
	 */
	public static boolean esAlfabetico(String cadena) {
		if (cadena != null && cadena.length() > 0) {
			String er = "^[a-zA-ZÒ—\\s]+$";
			Pattern pattern = Pattern.compile(er);
			Matcher matcher = pattern.matcher(cadena);
			return matcher.find();
		} else {
			return false;
		}
	}

	/**
	 * Method <b>esAlfanumerico</b>
	 * 
	 * @param cadena
	 *            cadena a evaluar
	 * 
	 * @return boolean true, en caso de Èxito
	 */
	public static boolean esAlfanumerico(String cadena) {
		if (cadena != null && cadena.length() > 0) {
			String er = "^[a-zA-ZÒ—0-9]+$";
			Pattern pattern = Pattern.compile(er);
			Matcher matcher = pattern.matcher(cadena);
			return matcher.find();
		} else {
			return false;
		}
	}

	/**
	 * Method <b>esEntero</b>
	 * 
	 * @param numero
	 *            numero a evaluar
	 * 
	 * @return boolean true, en caso de Èxito
	 */
	public static boolean esEntero(String numero) {
		if (numero != null && numero.length() > 0) {
			Pattern pattern = Pattern.compile("^[0-9]+$");
			Matcher matcher = pattern.matcher(numero);
			return matcher.find();
		} else {
			return false;
		}
	}

	/**
	 * Method <b>esDireccionIP</b> Verifica si el valor tiene un formato
	 * correcto para una direcciÛn IP
	 * 
	 * @param cadena
	 *            cadena a evaluar
	 * @return boolean Indicador si es una direcciÛn IP valida.
	 */
	public static boolean esDireccionIP(String cadena) {
		if (cadena != null && cadena.length() > 0) {
			String er = "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$";
			Pattern pattern = Pattern.compile(er);
			Matcher matcher = pattern.matcher(cadena);
			return matcher.find();
		} else {
			return false;
		}
	}

	/**
	 * Method <b>esNumero</b>
	 * 
	 * @param numero
	 *            n˙mero
	 * 
	 * @return true, en caso se que sea un n˙mero v·lido.
	 */
	public static boolean esNumero(String numero) {
		if (numero != null && numero.length() > 0) {
			Pattern pattern = Pattern.compile("^\\-{0,1}([0-9]*|\\d*\\.\\d{1}?\\d*)$");
			Matcher matcher = pattern.matcher(numero);
			return matcher.find();
		}
		return false;
	}
	
	public static int validarCandidatoEmpleadoActualmente(int empleadoActualmente){		
		if(empleadoActualmente != Catalogos.TRABAJA_ACTUALMENTE.SI.getIdOpcion() && 
				empleadoActualmente != Catalogos.TRABAJA_ACTUALMENTE.NO.getIdOpcion()){
			return -1;
		} else {
			return empleadoActualmente;
		}
	}	

	public static boolean validarCandidatoRazonBusquedaTrabajaActualmente(long razonBusqueda){
		boolean valido = false;
		if (razonBusqueda== Constantes.RAZON_BUSQUEDA.MAS_EMPLEO.getIdOpcion() ||
				razonBusqueda==  Constantes.RAZON_BUSQUEDA.CAMBIO_TRABAJO.getIdOpcion()){
					valido = true;
		}
		return valido;
	}
	
	public static boolean validarCandidatoRazonBusquedaNoTrabajaActualmente(long razonBusqueda){
		boolean valido = false;
		if(Constantes.RAZON_BUSQUEDA.isMember(razonBusqueda)){
			if (razonBusqueda!= Constantes.RAZON_BUSQUEDA.MAS_EMPLEO.getIdOpcion() ||
					razonBusqueda!=  Constantes.RAZON_BUSQUEDA.CAMBIO_TRABAJO.getIdOpcion()){
						valido = true;
			}			
		} 
		return valido;
	}	
	
	public static long validarCandidatoRazonBusqueda(int empleadoActualmente, long razonBusqueda){	
		
		if(empleadoActualmente == TRABAJA_ACTUALMENTE.SI.getIdOpcion() && 
				!validarCandidatoRazonBusquedaTrabajaActualmente(razonBusqueda)){
			return Constantes.RAZON_BUSQUEDA.MAS_EMPLEO.getIdOpcion();			
		} else if(empleadoActualmente == TRABAJA_ACTUALMENTE.NO.getIdOpcion() &&
				!validarCandidatoRazonBusquedaNoTrabajaActualmente(razonBusqueda)){
			return Constantes.RAZON_BUSQUEDA.OTRO.getIdOpcion();
		} else {
			return razonBusqueda;
		}						
	}	

	/**
	 * LEE EL INPUT Y VACIA LOS DATOS AL OUPUT, PARA OBTENER LOS DATOS COMO ARREGLO DE BYTES.
	 * 
	 * @param datosEncInput  
	 * 
	 * @return El arreglo de bytes
	 * 
	 * @throws IOException SeÒal de que una I/O exception ha ocurrido.
	 */
	public static byte[] getBytes(InputStream datosEncInput) throws IOException {
		BufferedOutputStream dest = null;
		ByteArrayOutputStream bout = null;
		int BUFFER_SIZE = 8192;
		int count;
		byte data[] = new byte[ BUFFER_SIZE ];
				
		bout = new ByteArrayOutputStream();
		dest = new BufferedOutputStream( bout, BUFFER_SIZE );

		while( (count = datosEncInput.read( data, 0, BUFFER_SIZE ) ) != -1 ){
			dest.write( data, 0, count );
		}
		
		dest.flush();
		dest.close();

		return bout.toByteArray();
	}
	
	/**
	 * Codifica una cadena a Base64
	 * @param data cadena original
	 * @return cadena codificada
	 */
	public static String codifica(String data){

		byte[] encoded = Base64.encodeBase64(data.getBytes());
		String dataEncoded = new String(encoded);

		return dataEncoded;
	}

	/**
	 * Decodifica una cadena de Base64
	 * @param dataEncoded cadena codificada
	 * @return cadena original
	 */
	public static String decodifica(String dataEncoded){

		byte[] bytesDecoded = Base64.decodeBase64(dataEncoded.getBytes());
		String dataDecoded = new String(bytesDecoded);

		return dataDecoded;
	}
	
	/** Formatea un date a String dd/MM/yyyy
	 * @param fecha
	 * @return String dd/MM/yyyy
	 */
	public static String getFechaFormatoYYYYMMDD(Date fecha){
		if (fecha==null) return null;
		return dateYYYYMMDD.format(fecha);
	}
	
	/**
	 * Formatea un date a String yyyy/MM/dd
	 * @param fecha
	 * @return String yyyy/MM/dd
	 */
	public static String getFechaFormato(Date fecha){
		if (fecha==null) return null;
		return dateDDMMYYYY.format(fecha);
	}
	
	/**
	 * Da formato a la hora h:mm:ss ms
	 * @param time
	 * @return
	 */
	public static String formatTime(Date time){
		if (time==null) return null;
		return timehmmssS.format(time);
	}	
	
	/** Devuelve la edad que tiene la persona 
	 * @param fecha
	 * @return String dd/MM/yyyy
	 */
	public static int obtenEdad(Date fecha){
		
		if (fecha==null) return 0;
		
		Calendar cal=new GregorianCalendar();
		Calendar fecha1 =new GregorianCalendar();	
		fecha1.setTime(fecha);	
		//System.out.println("la fecha actual "+cal);
		
		int resul=cal.get(Calendar.YEAR)-fecha1.get(Calendar.YEAR);
		if(cal.get(Calendar.MONTH)<fecha1.get(Calendar.MONTH)){
			resul --;
			//System.out.println("aun no cumple");		
		}
		
		if(cal.get(Calendar.MONTH)==fecha1.get(Calendar.MONTH)&& cal.get(Calendar.DAY_OF_MONTH)<fecha1.get(Calendar.DAY_OF_MONTH)){
			//System.out.println("aun no cumple 2 ");	
			resul --;
		}		
			
		return resul;
	}
	/** Corvierte una cadena en formato numerico
	 * @param cadena
	 * @return
	 */
	public static String formatMoney(Double value){
	try{		
		DecimalFormat myFormatter = new DecimalFormat("$###,###.##");
		return myFormatter.format(value);
	}catch (NumberFormatException e) {
		return "$0.00";
		}
	}
	
	/** Realizando el cambio de carecteris no permitidos por entidades en html
	 * @param cadena
	 * @return
	 */
	public static String sustituirPorEntidad(String cadena){
    	String aux = cadena;
    		aux = aux.replace("&", "&amp;");
    		aux = aux.replace("<", "&lt;");
    		aux = aux.replace(">", "&gt;");
    		aux = aux.replace("Ä", "&euro;");
    		aux = aux.replace("|", "&brvbar;");
    		aux = aux.replace("^", "&circ;");
    		aux = aux.replace("©", "&copy;");
    	return aux;
	}

	public static String quitaAcentos(String cadena){	
		cadena = cadena.replace("·", "a");
		cadena = cadena.replace("È", "e");
		cadena = cadena.replace("Ì", "i");
		cadena = cadena.replace("Û", "o");
		cadena = cadena.replace("˙", "u");
		cadena = cadena.replace("Ò", "n");	
		cadena = cadena.replace("¸", "u");	

		cadena = cadena.replace("¡", "A");
		cadena = cadena.replace("…", "E");
		cadena = cadena.replace("Õ", "I");
		cadena = cadena.replace("”", "O");
		cadena = cadena.replace("⁄", "U");
		cadena = cadena.replace("—", "N");	
		cadena = cadena.replace("‹", "U");	

		return cadena;
	}

	
	public static String toProperCase(String name) {	
		StringReader in = new StringReader(name.toLowerCase());  
		boolean precededBySpace = true;  
		StringBuffer properCase = new StringBuffer();      
		try{
			while(true) {        
				int i;
				i = in.read();	
				if (i == -1)  break;        
				char c = (char)i;  
				if (c == ' ' || c == '"' || c == '(' || c == '.' || c == '/' || c == '\\' || c == ',') {  
					properCase.append(c);  
					precededBySpace = true;  
				} else {  
					if (precededBySpace) {   
						properCase.append(Character.toUpperCase(c));  
					} else {   
						properCase.append(c);   
					}  
					precededBySpace = false;  
				}  
			}  
		} catch (IOException e) {
			e.printStackTrace();
		}  			
		return properCase.toString();     
	}	
	
	public static Date convert(String cadenaFecha){
		
		if (cadenaFecha==null || cadenaFecha.isEmpty()) return null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return sdf.parse(cadenaFecha);
		} catch (ParseException e) {
			return convertWebDate(cadenaFecha);
		}			
	
	}

	public static Date convertWebDate(String cadenaFecha){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(cadenaFecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}			
		return new Date();
	}
	
	public static long toLong(Object obj){
		if (obj==null) return 0;
		
		long result = 0;
		
		if (obj instanceof Long){
			result = (Long)obj;
		} else if (obj instanceof Integer){
			result = ((Integer)obj).longValue();
		} else if (obj instanceof BigDecimal){
			result = ((BigDecimal)obj).longValue();
		} else if (obj instanceof BigInteger){
			result = ((BigInteger)obj).longValue();
		} else if (obj instanceof Double){
			result = ((Double)obj).longValue();
		} else if (obj instanceof String){
			result = parseLong((String)obj);
		} else if (obj instanceof String){
			result = parseLong((String)obj);
		} else if (obj instanceof Number){
			result = ((Number)obj).longValue();
		}
		
		return result;
	}
	
	public static int toInt(Object obj){
		if (obj==null || obj.equals(null)) return 0;
		
		int result = 0;
		
		if (obj instanceof Long){
			result = ((Long)obj).intValue();
		} else if (obj instanceof Integer){
			result = ((Integer)obj).intValue();
		} else if (obj instanceof BigDecimal){
			result = ((BigDecimal)obj).intValue();
		} else if (obj instanceof BigInteger){
			result = ((BigInteger)obj).intValue();
		} else if (obj instanceof Double){
			result = ((Double)obj).intValue();
		} else if (obj instanceof String){
			result = parseInt((String)obj);
		} else if (obj instanceof Number){
			result = ((Number)obj).intValue();
		}
		
		return result;
	}

	public static double toDouble(Object obj){
		if (obj==null) return 0;
		
		double result = 0;
		
		if (obj instanceof Long){
			result = ((Long)obj).doubleValue();
		} else if (obj instanceof Integer){
			result = ((Integer)obj).doubleValue();
		} else if (obj instanceof BigDecimal){
			result = ((BigDecimal)obj).doubleValue();
		} else if (obj instanceof BigInteger){
			result = ((BigInteger)obj).doubleValue();
		} else if (obj instanceof Double){
			result = ((Double)obj).doubleValue();
		} else if (obj instanceof String){
			result = parseDouble((String)obj);
		} else if (obj instanceof Number){
			result = ((Number)obj).doubleValue();
		}
		
		return result;
	}
	
	public static String toString(Object obj){
		if (obj==null) return null;
		
		String result = null;

		if (obj instanceof String){
			result = (String)obj;
		} else if (obj instanceof Long){
			result = ((Long)obj).toString();
		} else if (obj instanceof Integer){
			result = ((Integer)obj).toString();
		} else if (obj instanceof BigDecimal){
			result = ((BigDecimal)obj).toString();
		} else if (obj instanceof BigInteger){
			result = ((BigInteger)obj).toString();
		} else if (obj instanceof Double){
			result = ((Double)obj).toString();
		} else if (obj instanceof Number){
			result = ((Number)obj).toString();
		} else if (obj instanceof Timestamp){
			result = ((Timestamp)obj).toString();
		
		}
		
		return result;
	}
	
	public static Date toDate(Object obj){
		if (obj==null) return null;
		
		Date result = null;
		
		if (obj instanceof Date){
			result = (Date)obj;
		} else if (obj instanceof java.sql.Date){
			result = new Date(((java.sql.Date)obj).getTime());
		} else if (obj instanceof String){
			result = convert((String)obj);
		}
		
		return result;
	}
	
	public static Calendar toCalendar(Object obj){
		Calendar calendar = null;
		
		Date date = toDate(obj);
		
		if (date!=null){
			calendar = Calendar.getInstance();
			calendar.setTime(date);
		}

		return calendar;
	}
	
	/**
	 * Metodo encargado que elmina las "," de una cadena String
	 * o un Long
	 * 
	 * @param cadena
	 * @return
	 */
	public static String quitarComas(Object objeto){
		
		String nuevaCadena = null;
		
		if(objeto instanceof String){
			nuevaCadena = (String)objeto;
		}else if(objeto instanceof Long){
			nuevaCadena = ((Long)objeto).toString();
		}
		
		if(nuevaCadena != null){
			nuevaCadena = nuevaCadena.replaceAll("[,]", " ");
		} 			
		return nuevaCadena;
	}

	public static int calculaEdad(Date fechaNacimiento){		
		if (fechaNacimiento==null) return 0;
		
		Calendar fhNac = Calendar.getInstance();
		fhNac.setTime(fechaNacimiento);
		return calculaEdad(fhNac);
	}
	
	public static int calculaEdad(int dia, int mes, int anio){
		Calendar fhNac = Calendar.getInstance();
		fhNac.set(anio, --mes, dia);
		return calculaEdad(fhNac);
	}
	
	public static int calculaEdad(Calendar fhNac){
		if (fhNac==null) return 0;
		
		Calendar fhHoy = Calendar.getInstance();

		int anios = fhHoy.get(Calendar.YEAR) - fhNac.get(Calendar.YEAR);
		--anios;
		
		if ((fhHoy.get(Calendar.MONTH) - fhNac.get(Calendar.MONTH))>=0){
			++anios;
		}

		return anios;
	}
	
	/**
	 * Catalogo de situacion academica de acuerdo a la escolaridad seleccionada
	 * @param idEscolaridad
	 * @return arreglo lond con ids pa el filtro de situaciones academicas
	 */
	public static long[] getFiltroSituacionAcademica(long idEscolaridad) {
		
		if(idEscolaridad==Constantes.GRADO_ESTUDIOS.SIN_INSTRUCCION.getIdOpcion())return Constantes.SIN_INSTRUCCION;
		if(idEscolaridad==Constantes.GRADO_ESTUDIOS.LEER_ESCRIBIR.getIdOpcion())return Constantes.LEER_Y_ESCRIBIR;
		if(idEscolaridad==Constantes.GRADO_ESTUDIOS.PRIMARIA.getIdOpcion())return Constantes.PRIMARIA;
		if(idEscolaridad==Constantes.GRADO_ESTUDIOS.SECUNDARIA.getIdOpcion())return Constantes.SECUNDARIA_SEC_TECNICA;
		if(idEscolaridad==Constantes.GRADO_ESTUDIOS.CARRERA_COMERCIAL.getIdOpcion())return Constantes.CARRERA_COMERCIAL;
		if(idEscolaridad==Constantes.GRADO_ESTUDIOS.CARRERA_TECNICA.getIdOpcion())return Constantes.CARRERA_TECNICA;
		if(idEscolaridad==Constantes.GRADO_ESTUDIOS.PROFESIONAL_TECNICO.getIdOpcion())return Constantes.PROFESIONAL_TECNICO;
		if(idEscolaridad==Constantes.GRADO_ESTUDIOS.PREPA_VOCACIONAL.getIdOpcion())return Constantes.PREPA_VOCACIONAL;
		if(idEscolaridad==Constantes.GRADO_ESTUDIOS.SUPERIOR_UNIVERSITARIO.getIdOpcion())return Constantes.T_SUPERIOR_UNIVERSITARIO;
		if(idEscolaridad==Constantes.GRADO_ESTUDIOS.LICENCIATURA.getIdOpcion())return Constantes.LICENCIATURA;
		if(idEscolaridad==Constantes.GRADO_ESTUDIOS.MAESTRIA.getIdOpcion())return Constantes.MAESTRIA;
		if(idEscolaridad==Constantes.GRADO_ESTUDIOS.DOCTORADO.getIdOpcion())return Constantes.DOCTORADO;
		else return new long[0];
		
		
		
		
	}
	
	public static String obtenerIdCatHora(String hora){
		String catalogoHora = null;
		String dosDigitos = null;
		String primerDigito = null;
			
		dosDigitos = hora.substring(0, 2);
		
		primerDigito = hora.substring(0, 1);
			
		if(dosDigitos.equals("00"))
			catalogoHora = "24";
		else if(primerDigito.equals("0"))
			catalogoHora = dosDigitos.substring(1);
		else if(!primerDigito.equals("0"))
			catalogoHora = dosDigitos;
		
		
		return catalogoHora;
	}

	public static String cut(String value, int length){
		if (value==null) return value;
		if (value.length()<=length) return value;

		value = value.substring(value.length() - length, value.length());
		return value;
	}
	public static String decodeBase64(String cadena) {
		String xmlResultado = null;
		byte[] arreglo;
		arreglo = Base64.decodeBase64(cadena.getBytes());
		xmlResultado = new String(arreglo);
	
        return  xmlResultado;
	}

	public static String validarExtensionTelefonica(String extensionTelefonica){         
        if(Utils.esNumero(extensionTelefonica)){
              return extensionTelefonica;
        } else {
              return "";
        }                    
   }    
	
	/**
	 * Corvierte un numero a 5 digitos
	 * 
	 * @param cadena
	 * @return
	 */
	public static String formatNumber(int value) {
		try {
            DecimalFormat formateador = new DecimalFormat("00000");
			return formateador.format(value);
		} catch (NumberFormatException e) {
			return "00000";
		}
	}
	
}
