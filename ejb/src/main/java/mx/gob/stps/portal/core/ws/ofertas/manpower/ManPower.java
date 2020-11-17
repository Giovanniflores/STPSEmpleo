package mx.gob.stps.portal.core.ws.ofertas.manpower;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class ManPower {
	private URL url;
    private String content_type = "text/xml; charset=UTF-8";
    private String soapaction = "\"\"";
    private String host = null;
    
	public byte[] obtenerXml(String xmlCompleto){
		String cadena = "";
		byte[] bytes = null;
		StringBuilder xml = new StringBuilder();
		int posIni = xmlCompleto.indexOf("%%");
		int posFin = xmlCompleto.indexOf("@@</return>");
		int i = 0;
		xml.append("<Vacantes>");
		if (posIni == -1 || posFin == -1) {
			cadena = "No se encontraron datos";
		} else {
			cadena = xmlCompleto.substring(posIni, posFin).substring(2);
		}
		String [] arreglo = cadena.split("%%");
		String [] tempo;
		for (String a : arreglo){
			xml.append("<Registro>");
			tempo = a.split("@@");
			i = 1;
			for (String t : tempo) {
				//System.out.println("---t:" + t);
				xml.append("<Dato" + i + ">");
				xml.append(t);				
				xml.append("</Dato" + i + ">");
				i ++;
			}
			xml.append("</Registro>");
		}
		xml.append("</Vacantes>");
		try{
			bytes = xml.toString().getBytes("ISO-8859-1");
			//bytes = xml.toString().getBytes("UTF-8");
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
	
	public String consultar (String clave, short entidad, String fecha){
		String query="";
		String resultado="";
		try {
		this.url= new URL("http://vacantes.manpower.com.mx/webservice_ST.php");
        this.soapaction="http://vacantes.manpower.com.mx/webservice_ST.php/busqueda";
        query ="<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\" " +
                "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n"+
                "<SOAP-ENV:Body>\n"+
                "<m:busqueda xmlns:m=\"http://localhost/nusoap\" SOAP-ENV:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">\n"+
                "<palcve xsi:type=\"xsd:string\">"+URLEncoder.encode(clave,"UTF-8")+"</palcve>\n"+
                "<entidad xsi:type=\"xsd:string\">"+ URLEncoder.encode(String.valueOf(entidad),"UTF-8") +"</entidad>\n"+
                "<fechalta xsi:type=\"xsd:string\">"+fecha+"</fechalta>\n"+
                "</m:busqueda>\n"+
                "</SOAP-ENV:Body>\n"+
                "</SOAP-ENV:Envelope>";
        resultado = this.invoke(query);
        //System.out.println("-------ManPower.consultar resultado:" + resultado);
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultado;
	}
	
    private String invoke(String xml)
    throws Exception
    {
	    HttpURLConnection con = (HttpURLConnection)this.url.openConnection();
	    con.setRequestMethod("POST");
	    con.setRequestProperty("Content-Type", this.content_type);
	    con.setRequestProperty("SOAPAction", this.soapaction);
	    if (this.host != null) con.setRequestProperty("Host", this.host);
	    con.setDoOutput(true);
	    OutputStream out = con.getOutputStream();
	    out.write(xml.getBytes()); 
	    //out.write(xml.getBytes("ISO-8859-1"));	    
	    //out.write(xml.getBytes("UTF-8"));
	    out.flush();
	    out.close();
	    int resc = con.getResponseCode();
	    String resm = con.getResponseMessage();
	    //System.out.println("ResponseMessage:" + resm);
	    InputStream in = null;
	    try
	    {
	    	in = con.getInputStream();
	    }
	    catch (IOException e) {
	    	in = con.getErrorStream();
	    }
	    return this.readInputStream(in);
    }
    
    private String readInputStream(InputStream in)
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
