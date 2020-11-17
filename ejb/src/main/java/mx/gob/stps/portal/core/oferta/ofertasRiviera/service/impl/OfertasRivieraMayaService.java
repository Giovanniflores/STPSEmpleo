//package mx.gob.stps.portal.core.oferta.ofertasRiviera.service.impl;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.UnsupportedEncodingException;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import javax.ejb.EJB;
//import javax.ejb.Stateless;
//
//import mx.gob.stps.portal.core.infra.utils.Constantes.ETIQUETAS;
//import mx.gob.stps.portal.core.oferta.dao.OfertasSecturDAO;
//import mx.gob.stps.portal.core.oferta.ofertasRiviera.service.OfertasRivieraMayaServiceRemote;
//import mx.gob.stps.portal.core.oferta.vo.OfertaRivieraMayaVO;
//import mx.gob.stps.portal.core.persistencia.facade.OfertaPreguntaFacadeLocal;
//import mx.gob.stps.portal.core.ws.ofertas.parser.OfertasEmpleoParser;
//import mx.gob.stps.portal.core.ws.ofertas.vo.OfertaEmpleoOutVO;
//
//
//@Stateless(name = "OfertasRivieraMayaService", mappedName = "OfertasRivieraMayaService")
////@TransactionManagement(TransactionManagementType.BEAN)
//public class OfertasRivieraMayaService implements OfertasRivieraMayaServiceRemote {
//	
//	//@EJB private OfertaRivieraMayaFacadeLocal ofertaRivieraFacade;
////	private static final String TIMER_NAME = "OfertasRivieraMayaTimer";
////	
////	@Resource
////	private TimerService timerService;
////
////	@Override
////	public void initTimer() {
////		
////		shutDownTimer();
////		
////		Timer timer = timerService.createTimer(TimeUnit.SECONDS.toMillis(30), null);
////		 
////	}
////
////	@Override
////	public void destroyInitTimer() {
////		@SuppressWarnings("unchecked")
////		Collection<Timer> timersCollection = timerService.getTimers();
////		Iterator<Timer> iterator = timersCollection.iterator();
////
////		while (iterator.hasNext()) {
////			Timer timer = iterator.next();
////			if (timer.getInfo().equals(TIMER_NAME)) {
////				timer.cancel();
////			}
////		}
////	}
////	
////	@Timeout
////	public void timeout(Timer timer) {
////		logger.info("TimeOut!!");
////		
////		try {
////			OfertasRivieraMayaWSImpl ofertasService = new OfertasRivieraMayaWSImpl();
////	        OfertasRivieraMayaWS ofertas = ofertasService.getOfertasRivieraMayaWSImplPort();
////	        String resultado = Utils.decodeBase64(ofertas.getOfertas("2013/07/24"));
////	        
////	        ParseXMLRivieraMaya parseXMLRivieraMaya = new ParseXMLRivieraMaya();
////        
////      
////			List<OfertaRivieraMayaVO> ofertasList =  parseXMLRivieraMaya.obtenerOfertas(resultado);
////			
////			
////			
////		} catch (ParserConfigurationException e) {
////			logger.error(e);
////			e.printStackTrace();
////		} catch (SAXException e) {
////			logger.error(e);
////			e.printStackTrace();
////		} catch (IOException e) {
////			logger.error(e);
////			e.printStackTrace();
////		}
////       
////	}
//
//	
//	/**
//	 * Apagan los timers existentes
//	 * @throws SQLException 
//	 */
////	private void shutDownTimer() {
////		@SuppressWarnings("unchecked")
////		Collection<Timer> timers = timerService.getTimers();
////		logger.info("Timers existentes " + timers);
////		if (timers != null) {
////			for (Iterator<Timer> iterator = timers.iterator(); iterator.hasNext();) {
////				Timer t = iterator.next();
////				t.cancel();
////				logger.info("Timer: " + t + " canceled");
////			}
////		}
////	}
//
//	@Override
//	public List<OfertaRivieraMayaVO> obtenerOfertasRiveraMaya() {
//		OfertasSecturDAO	dao	=	new OfertasSecturDAO();
//		List<OfertaRivieraMayaVO> list	=	 new ArrayList<>();
//		try{
//			list = dao.obtenerOfertas();
//		}catch(SQLException e){
//			e.printStackTrace();
//		}
//		return	list;
//		
//	}
//	
//	/*@Override
//	public List<OfertaRivieraMayaVO> obtenerOfertasRiveraMaya() {
//
//		String xmlCompleto;
//		OfertaRivieraMayaVO mayaVO = null;
//		List<OfertaRivieraMayaVO> listaOfertasRivieraMaya = new ArrayList<OfertaRivieraMayaVO>();
//		try {
//			byte[] xml;
//			xmlCompleto = ofertasRivieraMaya();
//			xml = obtenerXml(xmlCompleto);
//			
//			
//			OfertasEmpleoParser parser = new OfertasEmpleoParser(xml, ETIQUETAS.NODOSRIVIERAMAYA);
//			List<OfertaEmpleoOutVO> vacantes = parser.cargaVacantes();
//			if (vacantes!=null && vacantes.size()!=0){
//				
//				for (int ofertaRM = 0; ofertaRM < vacantes.size(); ofertaRM++) {
//					mayaVO = new OfertaRivieraMayaVO();
//					mayaVO.setGradoescolaridad(vacantes.get(ofertaRM).getDescripcion());
//					mayaVO.setIdOferta(Long.parseLong(vacantes.get(ofertaRM).getUrl()));
//					mayaVO.setNombre(vacantes.get(ofertaRM).getEmpresa());
//					mayaVO.setNombrepuesto(vacantes.get(ofertaRM).getPuesto());
//					mayaVO.setUbicacion(vacantes.get(ofertaRM).getEstado());
//					mayaVO.setRazonSocial(vacantes.get(ofertaRM).getEmpresa());					
//
//					listaOfertasRivieraMaya.add(mayaVO);
//				}
//			} else{
//				mayaVO = new OfertaRivieraMayaVO();
//				mayaVO.setNombrepuesto("No se encontraron datos");
//				listaOfertasRivieraMaya.add(mayaVO);
//			}
//		} catch(Exception e){e.printStackTrace();}
//		
//		
//		 /* List<OfertaRivieraMayaVO> ofertasList = null;
//		    try {
//			 String fecha = Utils.getFechaFormatoYYYYMMDD(new Date());
//			OfertasRivieraMayaWSImpl ofertasService = new OfertasRivieraMayaWSImpl();
//	        OfertasRivieraMayaWS ofertas = ofertasService.getOfertasRivieraMayaWSImplPort();
//	        String resultado = Utils.decodeBase64(ofertas.getOfertas("2013/07/24"));
//	        (ofertas.getOfertas("2013/07/24")); (ofertas.getOfertas(fecha));
//	      
//	        
//	        Ws_sne_ofertas;
//	        Ws_sne_ofertasLocal ofertasRiviera;
//	        Ws_sne_ofertas oferrs = new Ws_sne_ofertas();
//	        try {
//	        oferrs.obtieneOfertasRivieraMaya();
//	        } catch(Exception e){e.printStackTrace();}*/
////	        System.out.println("Antes de UTF-8");
////	        	System.out.print(resultado);
////	        System.out.println();
////	        System.out.println();
////	        System.out.println("----------");
////	        
////	        
////	        FILEWRITER FICHERO = NEW FILEWRITER("C:\\USERS\\USER\\DOCUMENTS\\OFERTAS.XML");
////	        //STRING FG = NEW STRING(RESULTADO.GETBYTES("UTF-8"), "ISO-8859-1");
////	        FICHERO.WRITE(RESULTADO);
////	        FICHERO.FLUSH();
////	        FICHERO.CLOSE();
//	        
////	        System.out.println("Despues de UTF-8");
////	        	System.out.print(fg);
////	        System.out.println();
////	        System.out.println();
////	        System.out.println("----------");
////	        resultado = resultado.replace("&", "&amp;");
////	        resultado.replaceAll( "&([^;]+(?!(?:\\w|;)))", "&amp;$1" );
//			 
//			 /*File file = new File(pathServidor);
//			 InputStream inputStream = new FileInputStream(file);
//
//			   byte[] bytes = Utils.getBytes(inputStream);   
//			   String xml = new String(bytes);
//			   xml = xml.replaceAll("[^\\x20-\\x7e]", "");
//			   xml = xml.replaceAll("&", "&amp;");
//			   
//			   inputStream = new ByteArrayInputStream(xml.getBytes());
//
//			   Reader reader = new InputStreamReader(inputStream,"UTF-8");    
//			   InputSource is = new InputSource(reader);
//			   is.setEncoding("UTF-8");
//	        
//	        ParseXMLRivieraMaya parseXMLRivieraMaya = new ParseXMLRivieraMaya();
//	    
//	        ofertasList =  parseXMLRivieraMaya.obtenerOfertas(is);*/
//
//        
//		/*} catch (ParserConfigurationException e) {
//			
//			e.printStackTrace();
//		} catch (SAXException e) {
//			
//			e.printStackTrace();
//		} catch (IOException e) {
//			
//			e.printStackTrace();
//		} catch (TransformerConfigurationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (TransformerFactoryConfigurationError e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//		
//		return listaOfertasRivieraMaya;
//	}*/
//	
//	public String ofertasRivieraMaya(){
//        String ret = null;
//		try{
//			StringBuffer buf = new StringBuffer("http://www.empleosenrivieramaya.com/WS/SNE/clientes-ws.php");
//	        URL url = new URL(buf.toString());
//	        ret = invoke(url);
//	    }catch (Exception ex) {
//	        ex.printStackTrace();
//	    }
//        Pattern r = Pattern.compile("&");
//        Matcher m = r.matcher(ret);
//        
//        return m.replaceAll("&amp;");
//	}
//
//	private static String invoke(URL url) throws Exception
//    {
//		HttpURLConnection con = (HttpURLConnection) url.openConnection();
//        con.setRequestMethod("POST");
//        con.connect();
//        con.getResponseMessage();
//        con.getResponseCode();
//
//        InputStream in = null;
//
//        try {
//           in = con.getInputStream();
//        } catch (IOException e) {
//            in = con.getErrorStream();
//            //AFUtils.log(e, "Error al obtener InputStream del Universal");
//        }
//
//        
//	    return readInputStream(in);
//    }
//	
//	private static String readInputStream(InputStream in) {
//	    if (in == null) 
//	    	return null;
//	    
//	    StringBuffer ret = new StringBuffer();
//	    try {
//	    	byte[] bfile = new byte[4096];
//		    int x;
//		    
//		    while ((x = in.read(bfile, 0, 4096)) > -1) {
//		    	
//		    	ret.append(new String(bfile, 0, x));
//		    }
//		    
//		    in.close();
//	    } catch (Exception e) {
//	    	e.toString();
//	    }
//	    
//	    return ret.toString();
//    }
//	
//	public byte[] obtenerXml(String xmlCompleto){
//		StringBuilder xml = new StringBuilder();
//		byte[] bytes = null;
//		int i = 0;
//		
//		char[] xmlDocument = xmlCompleto.toCharArray();
//		for (char character : xmlDocument) {
//			if(character == 'á')
//				character = 'a'; 
//			if(character == 'é')
//				character = 'e';
//			if(character == 'í')
//				character = 'i';
//			if(character == 'ó')
//				character = 'o';
//			if(character == 'ú')
//				character = 'u';			
//			xml.append(character);
//		}
//		
////		xml.append(xmlCompleto);
//		try{
////			bytes = xml.toString().getBytes("ISO-8859-1");
//			bytes = xml.toString().getBytes("UTF-8");
//			i = 0;
//			for (byte b : bytes){
//				if (b<0){
//					bytes[i] = 32;
//				}
//				i++;
//			}
//		} catch(UnsupportedEncodingException e){
//			e.printStackTrace();
//		}
//		return bytes;
//	}	
//}	

package mx.gob.stps.portal.core.oferta.ofertasRiviera.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import mx.gob.stps.portal.core.infra.utils.Constantes.ETIQUETAS;
import mx.gob.stps.portal.core.oferta.dao.OfertasSecturDAO;
import mx.gob.stps.portal.core.oferta.ofertasRiviera.service.OfertasRivieraMayaServiceRemote;
import mx.gob.stps.portal.core.oferta.vo.OfertaRivieraMayaVO;
import mx.gob.stps.portal.core.persistencia.facade.OfertaPreguntaFacadeLocal;
import mx.gob.stps.portal.core.ws.ofertas.parser.OfertasEmpleoParser;
import mx.gob.stps.portal.core.ws.ofertas.vo.OfertaEmpleoOutVO;


@Stateless(name = "OfertasRivieraMayaService", mappedName = "OfertasRivieraMayaService")
//@TransactionManagement(TransactionManagementType.BEAN)
public class OfertasRivieraMayaService implements OfertasRivieraMayaServiceRemote {
	
	//@EJB private OfertaRivieraMayaFacadeLocal ofertaRivieraFacade;
//	private static final String TIMER_NAME = "OfertasRivieraMayaTimer";
//	
//	@Resource
//	private TimerService timerService;
//
//	@Override
//	public void initTimer() {
//		
//		shutDownTimer();
//		
//		Timer timer = timerService.createTimer(TimeUnit.SECONDS.toMillis(30), null);
//		 
//	}
//
//	@Override
//	public void destroyInitTimer() {
//		@SuppressWarnings("unchecked")
//		Collection<Timer> timersCollection = timerService.getTimers();
//		Iterator<Timer> iterator = timersCollection.iterator();
//
//		while (iterator.hasNext()) {
//			Timer timer = iterator.next();
//			if (timer.getInfo().equals(TIMER_NAME)) {
//				timer.cancel();
//			}
//		}
//	}
//	
//	@Timeout
//	public void timeout(Timer timer) {
//		logger.info("TimeOut!!");
//		
//		try {
//			OfertasRivieraMayaWSImpl ofertasService = new OfertasRivieraMayaWSImpl();
//	        OfertasRivieraMayaWS ofertas = ofertasService.getOfertasRivieraMayaWSImplPort();
//	        String resultado = Utils.decodeBase64(ofertas.getOfertas("2013/07/24"));
//	        
//	        ParseXMLRivieraMaya parseXMLRivieraMaya = new ParseXMLRivieraMaya();
//        
//      
//			List<OfertaRivieraMayaVO> ofertasList =  parseXMLRivieraMaya.obtenerOfertas(resultado);
//			
//			
//			
//		} catch (ParserConfigurationException e) {
//			logger.error(e);
//			e.printStackTrace();
//		} catch (SAXException e) {
//			logger.error(e);
//			e.printStackTrace();
//		} catch (IOException e) {
//			logger.error(e);
//			e.printStackTrace();
//		}
//       
//	}

	
	/**
	 * Apagan los timers existentes
	 * @throws SQLException 
	 */
//	private void shutDownTimer() {
//		@SuppressWarnings("unchecked")
//		Collection<Timer> timers = timerService.getTimers();
//		logger.info("Timers existentes " + timers);
//		if (timers != null) {
//			for (Iterator<Timer> iterator = timers.iterator(); iterator.hasNext();) {
//				Timer t = iterator.next();
//				t.cancel();
//				logger.info("Timer: " + t + " canceled");
//			}
//		}
//	}

	@Override
	public List<OfertaRivieraMayaVO> obtenerOfertasRiveraMaya() {
		OfertasSecturDAO	dao	=	new OfertasSecturDAO();
		List<OfertaRivieraMayaVO> list	=	 new ArrayList<>();
		try{
			list = dao.obtenerOfertas();
			System.out.println(Arrays.asList(list));
		}catch(SQLException e){
			e.printStackTrace();
		}
		return	list;
		
	}
	
	@Override
	public List<OfertaRivieraMayaVO> obtenerOfertasSectur(int edo,String busqueda) {
		OfertasSecturDAO	dao	=	new OfertasSecturDAO();
		List<OfertaRivieraMayaVO> list	=	 new ArrayList<>();
		try{
			list = dao.obtenerOfertasFiltro(edo,busqueda);
			System.out.println(Arrays.asList(list));
		}catch(SQLException e){
			e.printStackTrace();
		}
		return	list;
		
	}
	
	/*@Override
	public List<OfertaRivieraMayaVO> obtenerOfertasRiveraMaya() {

		String xmlCompleto;
		OfertaRivieraMayaVO mayaVO = null;
		List<OfertaRivieraMayaVO> listaOfertasRivieraMaya = new ArrayList<OfertaRivieraMayaVO>();
		try {
			byte[] xml;
			xmlCompleto = ofertasRivieraMaya();
			xml = obtenerXml(xmlCompleto);
			
			
			OfertasEmpleoParser parser = new OfertasEmpleoParser(xml, ETIQUETAS.NODOSRIVIERAMAYA);
			List<OfertaEmpleoOutVO> vacantes = parser.cargaVacantes();
			if (vacantes!=null && vacantes.size()!=0){
				
				for (int ofertaRM = 0; ofertaRM < vacantes.size(); ofertaRM++) {
					mayaVO = new OfertaRivieraMayaVO();
					mayaVO.setGradoescolaridad(vacantes.get(ofertaRM).getDescripcion());
					mayaVO.setIdOferta(Long.parseLong(vacantes.get(ofertaRM).getUrl()));
					mayaVO.setNombre(vacantes.get(ofertaRM).getEmpresa());
					mayaVO.setNombrepuesto(vacantes.get(ofertaRM).getPuesto());
					mayaVO.setUbicacion(vacantes.get(ofertaRM).getEstado());
					mayaVO.setRazonSocial(vacantes.get(ofertaRM).getEmpresa());					

					listaOfertasRivieraMaya.add(mayaVO);
				}
			} else{
				mayaVO = new OfertaRivieraMayaVO();
				mayaVO.setNombrepuesto("No se encontraron datos");
				listaOfertasRivieraMaya.add(mayaVO);
			}
		} catch(Exception e){e.printStackTrace();}
		
		
		 /* List<OfertaRivieraMayaVO> ofertasList = null;
		    try {
			 String fecha = Utils.getFechaFormatoYYYYMMDD(new Date());
			OfertasRivieraMayaWSImpl ofertasService = new OfertasRivieraMayaWSImpl();
	        OfertasRivieraMayaWS ofertas = ofertasService.getOfertasRivieraMayaWSImplPort();
	        String resultado = Utils.decodeBase64(ofertas.getOfertas("2013/07/24"));
	        (ofertas.getOfertas("2013/07/24")); (ofertas.getOfertas(fecha));
	      
	        
	        Ws_sne_ofertas;
	        Ws_sne_ofertasLocal ofertasRiviera;
	        Ws_sne_ofertas oferrs = new Ws_sne_ofertas();
	        try {
	        oferrs.obtieneOfertasRivieraMaya();
	        } catch(Exception e){e.printStackTrace();}*/
//	        System.out.println("Antes de UTF-8");
//	        	System.out.print(resultado);
//	        System.out.println();
//	        System.out.println();
//	        System.out.println("----------");
//	        
//	        
//	        FILEWRITER FICHERO = NEW FILEWRITER("C:\\USERS\\USER\\DOCUMENTS\\OFERTAS.XML");
//	        //STRING FG = NEW STRING(RESULTADO.GETBYTES("UTF-8"), "ISO-8859-1");
//	        FICHERO.WRITE(RESULTADO);
//	        FICHERO.FLUSH();
//	        FICHERO.CLOSE();
	        
//	        System.out.println("Despues de UTF-8");
//	        	System.out.print(fg);
//	        System.out.println();
//	        System.out.println();
//	        System.out.println("----------");
//	        resultado = resultado.replace("&", "&amp;");
//	        resultado.replaceAll( "&([^;]+(?!(?:\\w|;)))", "&amp;$1" );
			 
			 /*File file = new File(pathServidor);
			 InputStream inputStream = new FileInputStream(file);

			   byte[] bytes = Utils.getBytes(inputStream);   
			   String xml = new String(bytes);
			   xml = xml.replaceAll("[^\\x20-\\x7e]", "");
			   xml = xml.replaceAll("&", "&amp;");
			   
			   inputStream = new ByteArrayInputStream(xml.getBytes());

			   Reader reader = new InputStreamReader(inputStream,"UTF-8");    
			   InputSource is = new InputSource(reader);
			   is.setEncoding("UTF-8");
	        
	        ParseXMLRivieraMaya parseXMLRivieraMaya = new ParseXMLRivieraMaya();
	    
	        ofertasList =  parseXMLRivieraMaya.obtenerOfertas(is);*/

        
		/*} catch (ParserConfigurationException e) {
			
			e.printStackTrace();
		} catch (SAXException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return listaOfertasRivieraMaya;
	}*/
	
	public String ofertasRivieraMaya(){
        String ret = null;
		try{
			StringBuffer buf = new StringBuffer("http://www.empleosenrivieramaya.com/WS/SNE/clientes-ws.php");
	        URL url = new URL(buf.toString());
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
	
	private static String readInputStream(InputStream in) {
	    if (in == null) 
	    	return null;
	    
	    StringBuffer ret = new StringBuffer();
	    try {
	    	byte[] bfile = new byte[4096];
		    int x;
		    
		    while ((x = in.read(bfile, 0, 4096)) > -1) {
		    	
		    	ret.append(new String(bfile, 0, x));
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
		
		char[] xmlDocument = xmlCompleto.toCharArray();
		for (char character : xmlDocument) {
			if(character == 'á')
				character = 'a'; 
			if(character == 'é')
				character = 'e';
			if(character == 'í')
				character = 'i';
			if(character == 'ó')
				character = 'o';
			if(character == 'ú')
				character = 'u';			
			xml.append(character);
		}
		
//		xml.append(xmlCompleto);
		try{
//			bytes = xml.toString().getBytes("ISO-8859-1");
			bytes = xml.toString().getBytes("UTF-8");
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
}	