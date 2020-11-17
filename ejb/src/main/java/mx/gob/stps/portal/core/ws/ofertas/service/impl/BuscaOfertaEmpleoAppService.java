package mx.gob.stps.portal.core.ws.ofertas.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import mx.gob.stps.portal.core.infra.utils.Constantes.BOLSAS_TRABAJO_EXTERNAS;
import mx.gob.stps.portal.core.infra.utils.Constantes.ENTIDADES_FEDERATIVAS;
import mx.gob.stps.portal.core.infra.utils.Constantes.ETIQUETAS;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.ws.ofertas.adecco.WsOfertasStub;
import mx.gob.stps.portal.core.ws.ofertas.adecco.WsOfertasStub.ArrayOfVacante;
import mx.gob.stps.portal.core.ws.ofertas.adecco.WsOfertasStub.ObtenVacantes;
import mx.gob.stps.portal.core.ws.ofertas.adecco.WsOfertasStub.ObtenVacantesResponse;
import mx.gob.stps.portal.core.ws.ofertas.adecco.WsOfertasStub.ParametroEntrada;
import mx.gob.stps.portal.core.ws.ofertas.adecco.WsOfertasStub.Vacante;
import mx.gob.stps.portal.core.ws.ofertas.bumeran.AvisosProviderStub;
import mx.gob.stps.portal.core.ws.ofertas.bumeran.AvisosProviderStub.Aviso;
import mx.gob.stps.portal.core.ws.ofertas.bumeran.AvisosProviderStub.GetAvisos;
import mx.gob.stps.portal.core.ws.ofertas.bumeran.AvisosProviderStub.GetAvisosResponse;
import mx.gob.stps.portal.core.ws.ofertas.manpower.ManPower;
import mx.gob.stps.portal.core.ws.ofertas.occ.OCCMServiceStub;
import mx.gob.stps.portal.core.ws.ofertas.occ.OCCMServiceStub.Job;
import mx.gob.stps.portal.core.ws.ofertas.occ.OCCMServiceStub.JobSearch;
import mx.gob.stps.portal.core.ws.ofertas.occ.OCCMServiceStub.JobSearchResponse;
import mx.gob.stps.portal.core.ws.ofertas.parser.OfertasEmpleoParser;
import mx.gob.stps.portal.core.ws.ofertas.service.BuscaOfertaEmpleoAppServiceRemote;
import mx.gob.stps.portal.core.ws.ofertas.superchamba.SuperChamba;
import mx.gob.stps.portal.core.ws.ofertas.turijobs.TuriJobs;
import mx.gob.stps.portal.core.ws.ofertas.vo.OfertaEmpleoInVO;
import mx.gob.stps.portal.core.ws.ofertas.vo.OfertaEmpleoOutVO;
import mx.gob.stps.portal.core.ws.ofertas.zonajobs.ZonaJobs;

import org.apache.log4j.Logger;

@Stateless(name = "BuscaOfertaEmpleoAppService", mappedName = "BuscaOfertaEmpleoAppService")
public class BuscaOfertaEmpleoAppService implements BuscaOfertaEmpleoAppServiceRemote {
	
	private static Logger logger = Logger.getLogger(BuscaOfertaEmpleoAppService.class);
	
	private HashMap<String, String> clavesIso = null;
	private final static int TOTAL_OFERTAS = 30;
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<OfertaEmpleoOutVO> buscaOfertaEmpleo(OfertaEmpleoInVO vo) {
		List<OfertaEmpleoOutVO> listaOfertas = new ArrayList<OfertaEmpleoOutVO>();
		
		if (!validaVO(vo))throw new IllegalArgumentException("Identificador de registro invalido");
        short buscarEn = (short)vo.getBusquedaen();
        if (buscarEn == BOLSAS_TRABAJO_EXTERNAS.OCC.getValor()) { 
        	listaOfertas = getVacantesOCC(vo);
        } else if (buscarEn == BOLSAS_TRABAJO_EXTERNAS.BUMERAN.getValor()) {
        	listaOfertas = getVacantesBumeran(vo);
        } else if (buscarEn == BOLSAS_TRABAJO_EXTERNAS.MANPOWER.getValor()) { 
        	listaOfertas = getVacantesManPower(vo);
        } else if (buscarEn == BOLSAS_TRABAJO_EXTERNAS.ADECCO.getValor()) { 
        	listaOfertas = getVacantesAdecco(vo);
        } else if (buscarEn == BOLSAS_TRABAJO_EXTERNAS.HISPAVISTA.getValor()){
        	listaOfertas = getVacantesHispavista(vo);
        } else if (buscarEn == BOLSAS_TRABAJO_EXTERNAS.ZONAJOBS.getValor()){
        	listaOfertas = getVacantesZonaJobs(vo);
        } else if (buscarEn == BOLSAS_TRABAJO_EXTERNAS.TURIJOBS.getValor()){	
        	listaOfertas = getVacantesTuriJobs(vo);
        } else if (buscarEn == BOLSAS_TRABAJO_EXTERNAS.SUPERCHAMBA.getValor()) {
			listaOfertas = getVacantesSuperChamba(vo);
		}

        return listaOfertas;
    }
	
	/**
	 * Consulta el WS de OCC y obtiene una lista de objetos que contienen el resultado
	 * de la consulta segun los filtros recibidos
	 * @param vo filtros de busqueda
	 * @return la lista de objetos OfertaEmpleoOutVO que contiene el resultado de la busqueda
	 */
	private List<OfertaEmpleoOutVO> getVacantesOCC(OfertaEmpleoInVO vo){
		List<OfertaEmpleoOutVO> listaOfertas = new ArrayList<OfertaEmpleoOutVO>();
		OfertaEmpleoOutVO voOut = null;
		
		try {
			mx.gob.stps.portal.core.ws.ofertas.occ.OCCMServiceStub reqWS = new mx.gob.stps.portal.core.ws.ofertas.occ.OCCMServiceStub();
			JobSearch jobSearch = new JobSearch();
			jobSearch.setNMaxJobsToReturn(TOTAL_OFERTAS);
			jobSearch.setNTimeFrame(Integer.parseInt(vo.getFecha()));
			jobSearch.setSCountry("MX");
			jobSearch.setSQuery(vo.getPalabra());
			jobSearch.setSState(getIdEntidadSFP(vo.getEntidad()));
			JobSearchResponse respuesta = reqWS.jobSearch(jobSearch);
			Job[] job = respuesta.getJobs().getAnyType();
			listaOfertas = new ArrayList<OfertaEmpleoOutVO>();
			if (job!=null){
				for (OCCMServiceStub.Job j : job){
					voOut = new OfertaEmpleoOutVO();
					voOut.setEmpresa(j.getCompanyName());
					//System.out.println("---------getVacantesOCC.j.getLocation():" + j.getLocation());					
					voOut.setEstado(j.getLocation());
					voOut.setFecha(j.getDatePosted());
					voOut.setPuesto(j.getJobTitle());
					voOut.setUrl(j.getUrl());
					listaOfertas.add(voOut);
				}
			}else{ 
				voOut = new OfertaEmpleoOutVO();
				voOut.setFecha("No se encontraron datos");
				listaOfertas.add(voOut);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return listaOfertas;
	}
	
	/**
	 * Consulta el WS de Bumeran y obtiene una lista de objetos que contienen el resultado
	 * de la consulta segun los filtros recibidos
	 * @param vo filtros de busqueda
	 * @return la lista de objetos OfertaEmpleoOutVO que contiene el resultado de la busqueda
	 */
	private List<OfertaEmpleoOutVO> getVacantesBumeran(OfertaEmpleoInVO vo){
		List<OfertaEmpleoOutVO> listaOfertas = new ArrayList<OfertaEmpleoOutVO>();
		OfertaEmpleoOutVO voOut = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar hoy = new GregorianCalendar();
        hoy.add(Calendar.DAY_OF_YEAR,-Integer.parseInt(vo.getFecha()));
       String otraFecha = sdf.format(hoy.getTime());
       
       try {
       	this.llenaMapa();
       	String [] entidad = new String[1];
       	entidad[0]= clavesIso.get(String.valueOf(vo.getEntidad()));
			AvisosProviderStub reqWS = new AvisosProviderStub();
			GetAvisos busqueda = new GetAvisos();
			busqueda.setZona(entidad);
			busqueda.setKeyword(vo.getPalabra());
			busqueda.setFechaInicioDesde(otraFecha);
			busqueda.setRegistroDesde(0);
			busqueda.setCantidad(TOTAL_OFERTAS);

			GetAvisosResponse respuesta = reqWS.getAvisos(busqueda);
			
			Aviso[] vacantes = respuesta.getOut().getAviso();
			if (vacantes!=null){
   			listaOfertas = new ArrayList<OfertaEmpleoOutVO>();
   			for(Aviso aviso: vacantes){
   				voOut = new OfertaEmpleoOutVO();
   				voOut.setEmpresa(aviso.getEmpresa());
   				voOut.setEstado(aviso.getZonaDescr());
   				voOut.setFecha(aviso.getFechaInicio());
   				voOut.setPuesto(aviso.getPuesto());
   				voOut.setUrl(aviso.getUrlAviso());
   				listaOfertas.add(voOut);
   			}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return listaOfertas;
	}
	
	/**
	 * Consulta el WS de ManPower y obtiene una lista de objetos que contienen el resultado
	 * de la consulta segun los filtros recibidos
	 * @param vo filtros de busqueda
	 * @return la lista de objetos OfertaEmpleoOutVO que contiene el resultado de la busqueda
	 */
	private List<OfertaEmpleoOutVO> getVacantesManPower(OfertaEmpleoInVO vo){
		List<OfertaEmpleoOutVO> listaOfertas = new ArrayList<OfertaEmpleoOutVO>();
		OfertaEmpleoOutVO voOut = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Calendar hoy = new GregorianCalendar();
        hoy.add(Calendar.DAY_OF_YEAR,-Integer.parseInt(vo.getFecha()));
       String otraFecha = sdf.format(hoy.getTime());
       try {
			String xmlCompleto;
			byte[] xml;
			ManPower servicio = new ManPower();
			xmlCompleto = servicio.consultar(vo.getPalabra(), (short)vo.getEntidad(),otraFecha);
			xml = servicio.obtenerXml(xmlCompleto);
			/*
			String strXml = null;
			try {
				//strXml = new String(xml, "ISO-8859-1");
				strXml = new String(xml, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			OfertasEmpleoParser parser = new OfertasEmpleoParser(strXml.getBytes(), ETIQUETAS.NODOSMANPOWER);
			*/
			OfertasEmpleoParser parser = new OfertasEmpleoParser(xml, ETIQUETAS.NODOSMANPOWER);
			List<OfertaEmpleoOutVO> vacantes = parser.cargaVacantes();
			
			if (vacantes!=null){
   			listaOfertas = new ArrayList<OfertaEmpleoOutVO>();
   			for (OfertaEmpleoOutVO vacante : vacantes){
   				//System.out.println("---getVacantesManPower.vacante.getEstado():" +  vacante.getEstado());
   				//System.out.println("---getVacantesManPower.vacante.getPuesto():" +  vacante.getPuesto());
   				
   				voOut = new OfertaEmpleoOutVO();
   				voOut.setEmpresa(vacante.getEmpresa());
   				voOut.setEstado(vacante.getEstado());   				
   				voOut.setFecha(vacante.getFecha());
   				voOut.setPuesto(vacante.getPuesto());   				
   				voOut.setUrl(vacante.getUrl());
   				listaOfertas.add(voOut);
   			}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return listaOfertas;
	}
	
	/**
	 * Consulta el WS de Adecco y obtiene una lista de objetos que contienen el resultado
	 * de la consulta segun los filtros recibidos
	 * @param vo filtros de busqueda
	 * @return la lista de objetos OfertaEmpleoOutVO que contiene el resultado de la busqueda
	 */
	private List<OfertaEmpleoOutVO> getVacantesAdecco(OfertaEmpleoInVO vo){
		List<OfertaEmpleoOutVO> listaOfertas = new ArrayList<OfertaEmpleoOutVO>();

        Calendar hoy = new GregorianCalendar();
        hoy.add(Calendar.DAY_OF_YEAR,-Integer.parseInt(vo.getFecha()));
       
        try {
    	   	WsOfertasStub reqWS = new WsOfertasStub();

    	   	ParametroEntrada param = new ParametroEntrada();
			param.setPuesto(vo.getPalabra());
			param.setEstadoID(vo.getEntidad());
			param.setFiltro3(Utils.getFechaFormato(hoy.getTime()));

			ObtenVacantes obtenVacantes = new ObtenVacantes();
			obtenVacantes.setOEntrada(param);
			
			ObtenVacantesResponse result = reqWS.obtenVacantes(obtenVacantes);
			ArrayOfVacante arrayOfVacante = result.getObtenVacantesResult();

			if (arrayOfVacante!=null && arrayOfVacante.getVacante()!=null){
				listaOfertas = new ArrayList<OfertaEmpleoOutVO>();
				
				Vacante[] vacantes = arrayOfVacante.getVacante();

				for (Vacante vacante : vacantes){
					OfertaEmpleoOutVO voOut = new OfertaEmpleoOutVO();
	   				voOut.setEmpresa(vacante.getReclutador());
	   				voOut.setEstado(vacante.getEstado());
	   				voOut.setPuesto(vacante.getPuestoOfrecido());
	   				voOut.setUrl(vacante.getURL());
	   				voOut.setFecha(""); // No cuenta con el dato de fecha
	   				listaOfertas.add(voOut);
				}
			} else {
				listaOfertas = new ArrayList<OfertaEmpleoOutVO>();
				OfertaEmpleoOutVO voOut = new OfertaEmpleoOutVO();
				voOut.setFecha("No se encontraron datos");
				listaOfertas.add(voOut);				
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return listaOfertas;
	}
	
	private List<OfertaEmpleoOutVO> getVacantesTuriJobs(OfertaEmpleoInVO vo){
		List<OfertaEmpleoOutVO> listaOfertas = new ArrayList<OfertaEmpleoOutVO>();
		try {			
			TuriJobs servicio =  new TuriJobs();
			String xmlCompleto;
			byte[] xml;
			String ubicacion = null;
			int claveDias = 1;
			Date fechaIni = new Date();
			Date fechaFin = new Date();		
			
			if(null!=vo.getFecha()){
				claveDias = Integer.parseInt(vo.getFecha());
				if(claveDias>30)
					claveDias=30;				
			}		
			fechaIni = TuriJobs.findRangeInitialDate(Utils.toInt(claveDias));
			fechaFin = TuriJobs.findRangeFinalDate();		
			if(vo.getEntidad()>0){
				ubicacion = Utils.quitaAcentos(ENTIDADES_FEDERATIVAS.getEntidad(vo.getEntidad()).getDescripcion());	
			} 
			String palabraClave = Utils.quitaAcentos(vo.getPalabra());
			xmlCompleto = servicio.consultar();
			//System.out.println("TuriJobs xmlCompleto:" + xmlCompleto.toString());	
			xml = servicio.obtenerXml(xmlCompleto);
			//System.out.println("TuriJobs xml obtenido OK:" + xml.toString());
			OfertasEmpleoParser parser = new OfertasEmpleoParser(xml, ETIQUETAS.NODOSTURIJOBS);
		
			List<OfertaEmpleoOutVO> vacantes = parser.cargaVacantes();
			OfertaEmpleoOutVO voOut = null;
			if (vacantes!=null){	
				//System.out.println("TuriJobs vacantes no son nulas hurra!");
				//System.out.println("TuriJobs consultar palabraClave:" + vo.getPalabra() + " ubicacion:" + ubicacion + " claveDias:" + claveDias);
				listaOfertas = new ArrayList<OfertaEmpleoOutVO>();
				for (OfertaEmpleoOutVO vacante : vacantes){					
					if(TuriJobs.containsCriteria(vacante, palabraClave, ubicacion, fechaIni, fechaFin)){
						voOut = new OfertaEmpleoOutVO(); 						
	    				voOut.setEstado(Utils.toProperCase(vacante.getEstado()));
	    				//System.out.println("TuriJobs voOut.getEstado(): " + voOut.getEstado());
	    				voOut.setFecha(vacante.getFecha());
	    				//System.out.println("TuriJobs voOut.getFecha(): " + voOut.getFecha());
	    				voOut.setPuesto(vacante.getPuesto());
	    				//System.out.println("TuriJobs voOut.getPuesto(): " + voOut.getPuesto());
    					voOut.setCiudad(Utils.toProperCase(vacante.getCiudad()));
    					//System.out.println("TuriJobs voOut.getCiudad(): " + voOut.getCiudad());
    					voOut.setEmpresa(vacante.getEmpresa());
    					//System.out.println("TuriJobs voOut.getEmpresa(): " + voOut.getEmpresa());
	    				voOut.setUrl(vacante.getUrl());
	    				//System.out.println("TuriJobs voOut.getUrl(): " + voOut.getUrl());
	    				listaOfertas.add(voOut);    											
					}
				}		
			} else {
				voOut = new OfertaEmpleoOutVO();
				voOut.setFecha("No se encontraron ofertas.");
				listaOfertas.add(voOut);				
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return listaOfertas;
		
	}
	
	private List<OfertaEmpleoOutVO> getVacantesZonaJobs(OfertaEmpleoInVO vo){
		List<OfertaEmpleoOutVO> listaOfertas = new ArrayList<OfertaEmpleoOutVO>();
		OfertaEmpleoOutVO voOut = null;
		
		try{
        	ZonaJobs servicio = new ZonaJobs();
			String xmlCompleto;
			byte[] xml;
			String ubicacion = null;
			int claveDias = 1;
			if(null!=vo.getFecha()){
				claveDias = Integer.parseInt(vo.getFecha());
				if(claveDias>30)
					claveDias=30;				
			}			
			if(vo.getEntidad()>0){
				ubicacion = Utils.quitaAcentos(ENTIDADES_FEDERATIVAS.getEntidad(vo.getEntidad()).getAbreviaturaZonaJobs());
			} 
			xmlCompleto = servicio.consultar(Utils.quitaAcentos(vo.getPalabra()), ubicacion, String.valueOf(claveDias));
			xml = servicio.obtenerXml(xmlCompleto);
			OfertasEmpleoParser parser = new OfertasEmpleoParser(xml, ETIQUETAS.NODOSZONAJOBS);
			List<OfertaEmpleoOutVO> vacantes = parser.cargaVacantes();
			if(null!=vacantes && vacantes.size()!=0){
				listaOfertas = new ArrayList<OfertaEmpleoOutVO>();
				for (OfertaEmpleoOutVO vacante : vacantes){
    				voOut = new OfertaEmpleoOutVO();    				
    				voOut.setEmpresa(vacante.getEmpresa());
    				voOut.setEstado(vacante.getEstado());
    				voOut.setFecha(vacante.getFecha());
    				voOut.setPuesto(vacante.getPuesto());
    				voOut.setUrl(vacante.getUrl());
    				listaOfertas.add(voOut);					
				}				
			} else {
				listaOfertas = new ArrayList<OfertaEmpleoOutVO>();
				voOut = new OfertaEmpleoOutVO();
				voOut.setFecha("No se encontraron datos");
				listaOfertas.add(voOut);				
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}	
		return listaOfertas;
	}

	/**
	 * Consulta el WS de Universal y obtiene una lista de objetos que contienen el resultado
	 * de la consulta segun los filtros recibidos
	 * @param vo filtros de busqueda
	 * @return la lista de objetos OfertaEmpleoOutVO que contiene el resultado de la busqueda
	 */
//	private List<OfertaEmpleoOutVO> getVacantesUniversal(OfertaEmpleoInVO vo){
//		List<OfertaEmpleoOutVO> listaOfertas = new ArrayList<OfertaEmpleoOutVO>();
//		OfertaEmpleoOutVO voOut = null;
//
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Calendar hoy = new GregorianCalendar();
//        hoy.add(Calendar.DAY_OF_YEAR,-Integer.parseInt(vo.getFecha()));
//        String otraFecha = sdf.format(hoy.getTime());
//        try {
//        	ElUniversal servicio = new ElUniversal();
//			String xmlCompleto;
//			byte[] xml;
//			xmlCompleto = servicio.consultar(vo.getPalabra(),otraFecha);
//			xml = servicio.obtenerXml(xmlCompleto);
//			OfertasEmpleoParser parser = new OfertasEmpleoParser(xml, ETIQUETAS.NODOSUNIVERSAL);
//			List<OfertaEmpleoOutVO> vacantes = parser.cargaVacantes();
//			listaOfertas = new ArrayList<OfertaEmpleoOutVO>();
//			if (vacantes!=null && vacantes.size()!=0){
//    			for (OfertaEmpleoOutVO vacante : vacantes){
//    				voOut = new OfertaEmpleoOutVO();
//    				voOut.setEmpresa(vacante.getEmpresa());
//    				voOut.setEstado(vacante.getEstado());
//    				voOut.setFecha(vacante.getFecha());
//    				voOut.setPuesto(vacante.getPuesto());
//    				voOut.setUrl(vacante.getUrl());
//    				listaOfertas.add(voOut);
//    			}
//			}else{
//				voOut = new OfertaEmpleoOutVO();
//				voOut.setFecha("No se encontraron datos");
//				listaOfertas.add(voOut);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error(e);
//		}
//		return listaOfertas;
//	}

	private List<OfertaEmpleoOutVO> getVacantesHispavista(OfertaEmpleoInVO vo){
		return null;
	}


	private List<OfertaEmpleoOutVO> getVacantesSuperChamba(OfertaEmpleoInVO vo){
		List<OfertaEmpleoOutVO> listaOfertas = new ArrayList<OfertaEmpleoOutVO>();
		OfertaEmpleoOutVO voOut = null;

		String keyword = vo.getPalabra();
		int federalEntityId = vo.getEntidad();
		int publishedId = Integer.valueOf(vo.getFecha());

		try {
			String xml = SuperChamba.findVacancies(keyword, federalEntityId, publishedId);
//			byte[] xmlBytes = xml.getBytes("UTF-8");
			byte[] xmlBytes = xml.getBytes("ISO-8859-1");
			OfertasEmpleoParser parser = new OfertasEmpleoParser(xmlBytes, ETIQUETAS.NODOS_SUPERCHAMBA);
			List<OfertaEmpleoOutVO> vacantes = parser.cargaVacantes();

			if (vacantes!=null){
				listaOfertas = new ArrayList<OfertaEmpleoOutVO>();
				for (OfertaEmpleoOutVO vacante : vacantes){
					voOut = new OfertaEmpleoOutVO();
					voOut.setEmpresa(vacante.getEmpresa());
					voOut.setEstado(vacante.getEstado());
					voOut.setFecha(vacante.getFecha());
					voOut.setPuesto(vacante.getPuesto());
					voOut.setUrl(vacante.getUrl());
					listaOfertas.add(voOut);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return listaOfertas;
	}
	
	/**
	 * Realiza las validaciones de los parametros de entrada para los metodos de testimonio
	 * @param vo parametros de entrada dependen del metodo
	 * @param opcion tipo de validacion
	 * @return true si los parametros son correctos de lo contrario false
	 */
	private boolean validaVO(OfertaEmpleoInVO vo){
		boolean result = true;
		if (vo.getEntidad() == 0) {
            result = false;
        }
        if (vo.getFecha() == null) {
                result = false;
        }
        if (vo.getPalabra() == null 
                && !"".equalsIgnoreCase(vo.getPalabra())) {
            result = false;
        }
        if (vo.getBusquedaen() == 0) {
            result = false;
        }
		return result;
	}
	
	/**
	 * Llena un mapa con las claves y abreviaturas de los estados
	 */
	private void llenaMapa(){
		clavesIso = new HashMap<String, String>();
        clavesIso.put("1", "MX-AGU");
        clavesIso.put("2", "MX-BCN");
        clavesIso.put("3", "MX-BCS");
        clavesIso.put("4", "MX-CAM");
        clavesIso.put("5", "MX-COA");
        clavesIso.put("6", "MX-COL");
        clavesIso.put("7", "MX-CHP");
        clavesIso.put("8", "MX-CHH");
        clavesIso.put("9", "MX-DIF");
        clavesIso.put("10", "MX-DUR");
        clavesIso.put("13", "MX-HID");
        clavesIso.put("14", "MX-JAL");
        clavesIso.put("15", "MX-MEX");
        clavesIso.put("16", "MX-MIC");
        clavesIso.put("17", "MX-MOR");
        clavesIso.put("18", "MX-NAY");
        clavesIso.put("19", "MX-NLE");
        clavesIso.put("20", "MX-OAX");
        clavesIso.put("21", "MX-PUE");
        clavesIso.put("22", "MX-QUE");
        clavesIso.put("23", "MX-ROO");
        clavesIso.put("24", "MX-SLP");
        clavesIso.put("25", "MX-SIN");
        clavesIso.put("26", "MX-SON");
        clavesIso.put("27", "MX-TAB");
        clavesIso.put("28", "MX-TAM");
        clavesIso.put("29", "MX-TLA");
        clavesIso.put("30", "MX-VER");
        clavesIso.put("31", "MX-YUC");
        clavesIso.put("32", "MX-ZAC");
	}
	
	private String getIdEntidadSFP(int identidad){
		String cadena = "";
		switch (identidad) {
				case 1 :  cadena = "AGS"; break;
				case 2 :  cadena = "BCN"; break;
				case 3 :  cadena = "BCS"; break;
				case 4 :  cadena = "CAM"; break;
				case 5 :  cadena = "COA"; break;
				case 6 :  cadena = "COL"; break;
				case 7 :  cadena = "CHP"; break;
				case 8 :  cadena = "CHI"; break;
				case 9 :  cadena = "DF"; break;
				case 10 :  cadena = "DGO"; break;
				case 11 :  cadena = "GTO"; break;
				case 12 :  cadena = "GRO"; break;
				case 13 :  cadena = "HGO"; break;
				case 14 :  cadena = "JAL"; break;
				case 15 :  cadena = "MEX"; break;
				case 16 :  cadena = "MCH"; break;
				case 17 :  cadena = "MOR"; break;
				case 18 :  cadena = "NAY"; break;
				case 19 :  cadena = "NL"; break;
				case 20 :  cadena = "OAX"; break;
				case 21 :  cadena = "PUE"; break;
				case 22 :  cadena = "QRO"; break;
				case 23 :  cadena = "QR"; break;
				case 24 :  cadena = "SLP"; break;
				case 25 :  cadena = "SINA"; break;
				case 26 :  cadena = "SONO"; break;
				case 27 :  cadena = "TAB"; break;
				case 28 :  cadena = "TAM"; break;
				case 29 :  cadena = "TLX"; break;
				case 30 :  cadena = "VER"; break;
				case 31 :  cadena = "YUC"; break;
				case 32 :  cadena = "ZAC"; break;
			}
			return cadena;
		}
	
}
