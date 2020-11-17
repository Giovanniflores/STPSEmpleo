package mx.gob.stps.portal.core.ws.ofertas.universal;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ElUniversal {
	
	public byte[] obtenerXml(String xmlCompleto){
		StringBuilder xml = new StringBuilder();
		byte[] bytes = null;
		int posIni = xmlCompleto.indexOf("<ns1:out>");
		int posFin = xmlCompleto.indexOf("</ns1:out>");
		int i = 0;
		xml.append(xmlCompleto);
		try{
			bytes = xml.toString().getBytes("ISO-8859-1");
			i = 0;
			for (byte b : bytes){
				if (b<0){
					bytes[i] = 32;
				}
				i++;
			}
		} catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		return bytes;
	}
	
    public String consultar (String palabraClave, String fecha){
        String ret = null;
        try {

            boolean isAnd = false;
            StringBuilder sb = new StringBuilder("http://avisooportuno.mx/stps/stps_resul.php");
            if (palabraClave != null && palabraClave.length() > 0) {
                sb.append("?EH_palcve=" + URLEncoder.encode(palabraClave, "UTF-8"));
                isAnd = true;
            }
            if (fecha != null && fecha.length() > 0) {
                if (isAnd) {
                    sb.append("&");
                } else {
                    sb.append("?");
                }
                sb.append("EH_fecha=" + URLEncoder.encode(fecha, "UTF-8"));
            }

            URL url = new URL(sb.toString());
            ret = invoke(url);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        Pattern r = Pattern.compile("&");
        Matcher m = r.matcher(ret);
        return m.replaceAll("&amp;");
        
	}

	private static String invoke(URL url) throws Exception
    {
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.connect();
        con.getResponseMessage();
        con.getResponseCode();

        InputStream in = null;

        try {
           in = con.getInputStream();
        } catch (IOException e) {
            in = con.getErrorStream();
            //AFUtils.log(e, "Error al obtener InputStream del Universal");
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
    	ret.append(new String(bfile, 0, x));
    }
    in.close();
    }
    catch (Exception e) {
    	e.toString();
    }
    return ret.toString();
    }
}
