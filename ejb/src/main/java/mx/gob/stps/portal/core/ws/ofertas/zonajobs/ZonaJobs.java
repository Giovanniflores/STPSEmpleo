package mx.gob.stps.portal.core.ws.ofertas.zonajobs;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mx.gob.stps.portal.core.infra.utils.Utils;

import org.apache.log4j.Logger;

public class ZonaJobs {

	private static Logger logger = Logger.getLogger(ZonaJobs.class);
		
	public byte[] obtenerXml(String xmlCompleto){
		StringBuilder xml = new StringBuilder();
		byte[] bytes = null;
		int posIni = xmlCompleto.indexOf("<jobs>");
		int posFin = xmlCompleto.indexOf("</jobs>");
		int i = 0;
		xml.append(xmlCompleto.substring(posIni, posFin+10));
		try{
			bytes = xml.toString().getBytes();
			i = 0;
			for (byte b : bytes){
				if (b<0){
					bytes[i] = 32;
				}
				i++;
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return bytes;
	}	
	
   public String consultar(String palabraClave, String ubicacion, String fecha){
        String ret = null;
        try {

            boolean isAnd = false;
            StringBuilder sb = new StringBuilder("http://www.zonajobs.com.mx/empleos/");	            
            //EJEMPLO: http://www.zonajobs.com.mx/empleos/java_ubicacion=distrito-federal_dias=15?format=xml
            //ADVERTENCIA: Cuando palabraClave cuando tiene espacios, el WS no lo procesa correctamente
            if (palabraClave != null && palabraClave.length() > 0) {
                sb.append(palabraClave);
                isAnd = true;
            }
            if (ubicacion != null && ubicacion.length() > 0) {
            	if(isAnd){
            		sb.append("_ubicacion=" + ubicacion);
            	} else {
            		sb.append("ubicacion=" + ubicacion);
            	}                
            }
            if (fecha != null) {
            	if(isAnd){
            		sb.append("_dias=" + fecha);
            	} else {
            		sb.append("dias=" + fecha);
            	}	                
            }
            sb.append("?format=xml");
            URL url = new URL(sb.toString());
            ret = invoke(url);
            ret = Utils.quitaAcentos(ret);
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
            logger.info("Error al obtener InputStream del ZonaJobs");
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
		}
		catch (Exception e) {
			e.toString();
		}
		return ret.toString();
	}	
	
}
