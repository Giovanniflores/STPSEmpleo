package mx.gob.stps.portal.core.ws.ofertas.turijobs;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.ws.ofertas.vo.OfertaEmpleoOutVO;
import org.apache.log4j.Logger;

public class TuriJobs {

	private static Logger logger = Logger.getLogger(TuriJobs.class);
	
	public String consultar(){
		String ret = null;		
		StringBuilder sb = new StringBuilder("http://turijobs.com.mx/rss/simplyhired.xml");	
		try{
	        URL url = new URL(sb.toString());
	        ret = invoke(url);
			ret=Utils.quitaAcentos(ret);	
		    Pattern r = Pattern.compile("&");
		    Matcher m = r.matcher(ret);
		    return m.replaceAll("&amp;");
	        
	    }catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    return ret;
	}
	
	private static String invoke(URL url) throws Exception
    {
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.connect();
        con.getResponseMessage();
        con.getResponseCode();

        InputStream in = null;

        try {
           in = con.getInputStream();
        } catch (IOException e) {
            in = con.getErrorStream();
            logger.info("Error al obtener InputStream del TuriJobs");
            logger.error(e);
        }        
	    return readInputStream(in);
    }	
	
	private static String readInputStream(InputStream in)
    {
	    if (in == null) return null;
	    	StringBuffer ret = new StringBuffer();
	    try
	    {
	    	byte[] bfile = new byte[4096];
	    	int x;
		    while ((x = in.read(bfile, 0, 4096)) > -1)
		    {
		    	ret.append(new String(bfile, 0, x, "UTF-8"));
		    }
	    	in.close();
	    } catch (Exception e) {
	    	e.toString();
	    }
	    return ret.toString();
    }		
	
	public byte[] obtenerXml(String xmlCompleto){
		StringBuilder xml = new StringBuilder();
		byte[] bytes = null;
		int i = 0;
		xml.append(xmlCompleto);
		bytes = xml.toString().getBytes();
		i = 0;
		for (byte b : bytes){
			if (b<0){
				bytes[i] = 32;
			}
			i++;
		}
		return bytes;
	}
	
	public static boolean containsCriteria(OfertaEmpleoOutVO vacante, String keyWord, String entidad, 
			Date rangeInitialDate, Date rangeFinalDate){
		boolean foundVacante = false;
		foundVacante = (containsKeyWord(vacante.getPuesto(), keyWord) 
				&& containsKeyWord(vacante.getEstado(), entidad) 
				&& isOfferInDateRange(rangeInitialDate, rangeFinalDate, Utils.convert(vacante.getFecha())));
		return foundVacante;
	}
	
	public static boolean containsKeyWord(String strField, String keyWord){
		return strField.matches("(?i).*" + keyWord + ".*");
	}
	
	public static boolean isOfferInDateRange(Date rangeInitialDate, Date rangeFinalDate, Date offerDate){		
		return (!offerDate.before(rangeInitialDate) && !offerDate.after(rangeFinalDate));
	}
	
	public static Date findRangeInitialDate(int rangeId){
		Date firstDate = new Date(); 
		if(rangeId>1){
			rangeId++;
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -rangeId);
			firstDate = cal.getTime(); 
		}	
		return firstDate;
	}
	
	public static Date findRangeFinalDate(){
		Date finalDate = new Date(); 
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		finalDate = cal.getTime(); 
		return finalDate;
	}		
	
}
