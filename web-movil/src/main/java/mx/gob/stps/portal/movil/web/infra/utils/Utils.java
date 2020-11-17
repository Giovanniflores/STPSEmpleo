package mx.gob.stps.portal.movil.web.infra.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Base64;

public final class Utils {

	private Utils(){}

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
		//return validaPatron("/^[a-zA-Z0-9_-]+(?:\.[a-zA-Z0-9_-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-_]*[a-zA-Z0-9])?\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$/", valor);
		if (valor != null && valor.length() > 0) {
			String er = "\\b[a-zA-Z0-9_-]+(?:\\.[a-zA-Z0-9_-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-_]*[a-zA-Z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\b";
			Pattern pattern = Pattern.compile(er);
			Matcher matcher = pattern.matcher(valor);
			return matcher.find();
		} else {
			return false;
		}		
	}
	
	/**
	 * Method <b>validaFecha</b>
	 * 
	 * @param valor
	 * @return boolean
	 */
	public static boolean validaFecha(String valor) {
		return validaPatron(
				"([0-9]{4})-([0-9]{2})-([0-9]{2})",
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
			String er = "^[a-zA-Z·ÈÌÛ˙¡…Õ”⁄Ò—\\s]+$";
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
			String er = "^[a-zA-Z·ÈÌÛ˙¡…Õ”⁄Ò—0-9\\s]+$";
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
			Pattern pattern = Pattern
					.compile("^\\-{0,1}([0-9]*|\\d*\\.\\d{1}?\\d*)$");
			Matcher matcher = pattern.matcher(numero);
			return matcher.find();
		}
		return false;
	}
	
	private static final SimpleDateFormat dateFormatDDMMYYYY = new SimpleDateFormat("dd/MM/yyyy");
	
	public static String formatDDMMYYYY(Date date){
		if (date==null) return null;

		String dateFormatted = dateFormatDDMMYYYY.format(date);
		return dateFormatted;
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

	/**
	 * Transforma una lista de objetos a su equivalente JSON.
	 *
	 * @param list the list
	 * @return the string
	 */
	public static String toJson(List<?> list){

		if (list==null) throw new IllegalArgumentException("Lista a transformar requerida");
		
		JSONArray jsonArray = JSONArray.fromObject(list);
		String json = jsonArray.toString();
		return json;
	}

	/**
	 * Transforma objeto a su equivalente JSON.
	 *
	 * @param obj the obj
	 * @return the string
	 */
	public static String toJson(Object obj){
		
		if (obj==null) throw new IllegalArgumentException("Objecto a transformar es requerido");

		JSONObject jsonObject = JSONObject.fromObject(obj);
		String json = jsonObject.toString();
		return json;
	}

	private static final DecimalFormat decimalFormat = new DecimalFormat("$###,###.00");
	
	private static final DecimalFormat decimalFormatNumeric = new DecimalFormat("###,###,###");
	
	private static final DecimalFormat decimalFormatDecimales = new DecimalFormat("######.00");
	
	public static String formatMoney(String cadena){
		return format(decimalFormat, cadena);
	}
	
	public static String formatMoneyComas(String cadena){
		return format(decimalFormatDecimales, cadena);
	}
	public static String formatMoney(double valor){
		return format(decimalFormat, valor);
	}

	public static String formatComa(long valor){
		return format(decimalFormatNumeric, valor);
	}

	public static String formatDecimal(String cadena){
		if(cadena == null || "".equals(cadena))
			return "";
		return format(decimalFormatDecimales, cadena);
	}

	private static String format(DecimalFormat formater, String cadena){
		double value = 0;
		
		
		if (cadena!=null && !cadena.isEmpty()){
			value = parseDouble(cadena);
			
			if (value<0) value = 0;
		}

		String result = formater.format(value);

		return result;
	}

	private static String format(DecimalFormat formater, double value){
		if (value<0) value = 0;
		String result = formater.format(value);
		return result;
	}

	/**
	 * Capitaliza la cadena
	 * Coloca la primer letra en mayuscula y el resto de la cadena en minusculas 
	 * @param cadena
	 * @return
	 */
	public static String capitalize(String cadena) {
		if (cadena==null) return cadena;

		cadena = cadena.trim();

		if (cadena.isEmpty() || cadena.length() < 2) return cadena;
		
		cadena = cadena.toLowerCase();
		cadena = Character.toUpperCase(cadena.charAt(0)) + cadena.substring(1);

		return cadena;
	}

	public static boolean validaCadena(int longMin, int longMax, String cadena, boolean requerido){

		if (requerido){
			if (cadena==null || cadena.trim().isEmpty()) return false;
		} else {
			if (cadena==null || cadena.trim().isEmpty()) return true;
		}

		if (cadena.length()<longMin || cadena.length()>longMax) return false;
		
		if (!Utils.esAlfabetico(cadena)) return false;
		
		return true;
	}

	public static boolean validaCorreo(int longMin, int longMax, String cadena, boolean requerido){

		if (requerido){
			if (cadena==null || cadena.trim().isEmpty()) return false;
		} else {
			if (cadena==null || cadena.trim().isEmpty()) return true;
		}

		if (cadena.length()<longMin || cadena.length()>longMax) return false;
		
		if (!Utils.validaMail(cadena)) return false;
		
		return true;
	}

	public static boolean validaNumerico(int longMin, int longMax, long numero, boolean requerido){		
		
		if (requerido){
			if (numero<=0) return false;
		} else {
			if (numero<=0) return true;
		}

		String cadena = String.valueOf(numero);
		
		if (cadena.length()<longMin || cadena.length()>longMax) return false;

		return true;
	}
	
	public static boolean validaNumerico(int longMin, int longMax, String numero, boolean requerido){		
		
		String cadena = String.valueOf(numero);

		if (requerido){
			if (cadena==null || cadena.trim().isEmpty()) return false;
		} else {
			if (cadena==null || cadena.trim().isEmpty()) return true;
		}

		if (cadena.length()<longMin || cadena.length()>longMax) return false;
		
		if (!Utils.esEntero(cadena)) return false;
		
		return true;
	}

	public static int calculaEdad(Date fechaNacimiento){
		Calendar fhNac = Calendar.getInstance();
		fhNac.setTime(fechaNacimiento);
		return calculaEdad(fhNac);
	}
	
	public static int calculaEdad(Calendar fhNac){
		Calendar fhHoy = Calendar.getInstance();

		int anios = fhHoy.get(Calendar.YEAR) - fhNac.get(Calendar.YEAR);
		--anios;
		
		if ((fhHoy.get(Calendar.MONTH) - fhNac.get(Calendar.MONTH))>=0){
			++anios;
		}

		return anios;
	}
	
	/**
	 * @param bandera
	 * @return
	 */
	public static int getNumeroBoolena(boolean bandera){
		if(bandera)
			return 1;
		else
			return 0;		
	}
	
	
	public static String getSetCurrencyFromString(String numeroSinFormato ){
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		return nf.format(new BigDecimal(numeroSinFormato));
	}
}