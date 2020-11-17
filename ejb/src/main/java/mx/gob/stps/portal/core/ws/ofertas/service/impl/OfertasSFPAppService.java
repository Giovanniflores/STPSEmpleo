package mx.gob.stps.portal.core.ws.ofertas.service.impl;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.SimpleTimeZone;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.naming.Context;

import mx.gob.stps.portal.core.domicilio.dao.MunicipioSFPDAO;
import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.data.ConexionFactory;
import mx.gob.stps.portal.core.infra.data.ConnectionWraper;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.MULTIREGISTRO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.infra.utils.Constantes.VALORES_SFP;
import mx.gob.stps.portal.core.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.core.oferta.registro.vo.OfertaCarreraEspecialidadVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaUbicacionVO;
import mx.gob.stps.portal.core.persistencia.facade.DomicilioFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.EmpresaFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.OfertaCarreraEspecFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.OfertaEmpleoFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.OfertaExternaFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.OfertaFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.OfertaUbicacionFacadeLocal;
//fixme import mx.gob.stps.portal.core.search.service.IndexerServiceLocator;
//import mx.gob.stps.portal.core.search.service.PortalEmpleoBuscadorServiceLocal;
import mx.gob.stps.portal.core.ws.dao.OfertasSFPDAO;
import mx.gob.stps.portal.core.ws.ofertas.service.OfertasSFPAppServiceRemote;
import mx.gob.stps.portal.core.ws.ofertas.service.OfertasSFPAppServiceLocal;
import mx.gob.stps.portal.core.ws.ofertas.sfp.SFPServiceStub;
import mx.gob.stps.portal.core.ws.ofertas.sfp.SFPServiceStub.ListaVacantes;
import mx.gob.stps.portal.core.ws.ofertas.sfp.SFPServiceStub.ListaVacantesResponse;
import mx.gob.stps.portal.core.ws.ofertas.sfp.SFPServiceStub.Vacante;
import mx.gob.stps.portal.core.ws.ofertas.vo.OfertaEmpleoOutVO;
import mx.gob.stps.portal.core.ws.ofertas.vo.OfertaEmpleoSFPVO;
import mx.gob.stps.portal.core.ws.ofertas.vo.OfertaExternaTotalVO;
import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.utils.Catalogos.EDAD_REQUISITO;
import mx.gob.stps.portal.utils.ConstantesGenerales;

import org.apache.axis2.AxisFault;
import org.apache.log4j.Logger;

@Stateless(name = "OfertasSFPAppService", mappedName = "OfertasSFPAppService")
@TransactionManagement(TransactionManagementType.BEAN)
public class OfertasSFPAppService implements OfertasSFPAppServiceRemote, OfertasSFPAppServiceLocal{	

	private static Logger logger = Logger.getLogger(OfertasSFPAppService.class);
	private static final String NO_APLICA = "N/A"; 
	private static final String URL_OBSERVACIONES = "<a href=\"http://www.trabajaen.gob.mx\" target=\"_blank\" >Más información en trabajaen.gob.mx</a>";
	private static final int CERO = 0;
	private static final int OFERTA_NO_EXISTE = 1;
	private static final int OFERTA_EXISTE_Y_TIENE_CARRERAS_RELACIONADAS = 2;
	private static final int OFERTA_EXISTE_Y_NO_TIENE_CARRERAS_RELACIONADAS = 3; 
	
	@EJB private OfertaEmpleoFacadeLocal ofertaEmpleoFacade;
	@EJB private OfertaExternaFacadeLocal ofertaExternaFacade;
	@EJB private OfertaCarreraEspecFacadeLocal ofertaCarreraEspecFacade;
	@EJB private OfertaFacadeLocal ofertaFacade;
	@EJB private EmpresaFacadeLocal empresaFacade;
	@EJB private OfertaUbicacionFacadeLocal ofertaUbicacionFacade;

	@Resource private TimerService timerService;
	@EJB private DomicilioFacadeLocal domicilioFacade;

	//@EJB private PortalEmpleoBuscadorServiceLocal portalEmpleoBuscadorService;

	private static final int INTERVAL_IN_MINUTES = 1440;
	
	private long idSector = 0;

	public void inicializaTimerSFP() { 
		shutDownTimer();

		PropertiesLoader properties = PropertiesLoader.getInstance();						

		SimpleTimeZone tz = new SimpleTimeZone(-21600000, "America/Mexico_City", 3, 1, -1, 7200000, 9, -1, 1, 7200000, 3600000);
		GregorianCalendar cal = new GregorianCalendar(tz);
		int dia = cal.get(Calendar.DAY_OF_MONTH);
		int hora = cal.get(Calendar.HOUR_OF_DAY);		
		
		if (hora >= 1) {
			dia++;
			cal.set(Calendar.DAY_OF_MONTH, dia);
		}
		cal.set(Calendar.HOUR_OF_DAY, properties.getPropertyInt("hora.sfp"));				
		cal.set(Calendar.MINUTE, properties.getPropertyInt("min.sfp"));		
		cal.set(Calendar.SECOND, properties.getPropertyInt("min.sfp"));
		cal.set(Calendar.MILLISECOND, properties.getPropertyInt("min.sfp"));

		long intervalDuration = INTERVAL_IN_MINUTES*60*1000;  
		Timer timer = timerService.createTimer(cal.getTime(),intervalDuration,"nuevo");

		logger.info("El proceso de generacion de ofertas de SFP se iniciará el: " + timer.getNextTimeout());
		logger.info("Saliendo al timerinit .........................." + cal.getTime());
	}

	@Timeout
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void timeout(Timer timer) { 
		logger.info("Tamaño al iniciar el proceso de la SFP: " + timerService.getTimers().size());
		iniciarProcesoSFP();
	}

	public void shutDownTimer() {  
		@SuppressWarnings("unchecked")
		Collection<Timer> timers = timerService.getTimers();  
		logger.info("Timers existentes " + timers);  
		if (timers != null) {
			for (Iterator<Timer> iterator = timers.iterator(); iterator.hasNext();) {  
				Timer t = iterator.next();  
				t.cancel();  
				logger.info("Timer: "+t+" canceled");  
			}  
		}  
	}  

	/**
	 * Calcula la fecha en la que se iniciara el proceso para obtener ofertas de empleo de la SFP
	 */
	public void iniciarProcesoSFP() {
		try {
			SimpleTimeZone tz = new SimpleTimeZone(-21600000, "America/Mexico_City", 3, 1, -1, 7200000, 9, -1, 1, 7200000, 3600000);
			GregorianCalendar cal = new GregorianCalendar(tz);
			cal.add(Calendar.DATE, -1); // Se cargan las ofertas del dia anterior

			transfiereOfertasExternasSFP(cal.getTime());

		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}

	/**
	 * Procesa el resultado del WS para convertilo en una lista de objetos con las ofertas de SFP
	 * @param fechaAlta valor de la fecha en la cual se quieren obtener las ofertas
	 */
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public OfertaExternaTotalVO transfiereOfertasExternasSFP(Date fecha) {

		String fechaAlta = Utils.getFechaFormato(fecha);

		logger.info("Inicia carga de ofertas de SFP para el dia: " + fechaAlta);

		List<OfertaEmpleoSFPVO> ofertas = null;

		try{
			ofertas = consultaOfertasSFP(fechaAlta);	
		}catch(TechnicalException e){
			logger.error("Error al invocar el servicio web"); 
			logger.error(e);
			System.out.println("e.getMessage(): "+e.getMessage());
			e.printStackTrace();
		}			

		OfertaExternaTotalVO totales = cargaOfertasExternasSFP(ofertas);
		return totales;
	}

	/**
	 * Se crea un objeto OfertaEmpleoVO valido a partir de los datos de la OfertaEmpleoSFPVO que se obtiene del WS 
	 * @param oferta recibida del WS de la SFP
	 * @return una oferta valida y lista para ser insertada o actualizada en el PE
	 */
	private OfertaEmpleoVO crearOfertaEmpleoVO(OfertaEmpleoSFPVO ofertaSFP) {		
		OfertaEmpleoVO ofertaEmpleoVO = new OfertaEmpleoVO();

		ofertaEmpleoVO.setIdEmpresa(ofertaSFP.getIdEmpresa());

		ofertaEmpleoVO.setNombreEmpresa(ofertaSFP.getNombreEmpresa());	

		if(ofertaSFP.getIdOfertaEmpleo() != null){
			ofertaEmpleoVO.setIdOfertaEmpleo(ofertaSFP.getIdOfertaEmpleo());
		}

		if (ofertaSFP.getTituloOferta().length() <= 150)
			ofertaEmpleoVO.setTituloOferta(ofertaSFP.getTituloOferta());
		else
			ofertaEmpleoVO.setTituloOferta(ofertaSFP.getTituloOferta().substring(0, 150));

		ofertaEmpleoVO.setIdAreaLaboral(ofertaSFP.getIdAreaLaboral());
		ofertaEmpleoVO.setIdOcupacion(ofertaSFP.getIdOcupacion());

		if(null==ofertaSFP.getFunciones() || ofertaSFP.getFunciones().contentEquals("")){
			ofertaEmpleoVO.setFunciones("NA");
		} else {
			if(ofertaSFP.getFunciones().length() <= 2000)
				ofertaEmpleoVO.setFunciones(ofertaSFP.getFunciones());
			else
				ofertaEmpleoVO.setFunciones(ofertaSFP.getFunciones().substring(0, 2000));				
		}

		ofertaEmpleoVO.setSalario(ofertaSFP.getSalario());
		ofertaEmpleoVO.setIdNivelEstudio(ofertaSFP.getIdNivelEstudio());
		ofertaEmpleoVO.setIdSituacionAcademica(ofertaSFP.getIdSituacionAcademica());

//		if (ofertaSFP.getHabilidadGeneral().length() <= 2000)
//			ofertaEmpleoVO.setHabilidadGeneral(ofertaSFP.getHabilidadGeneral());
//		else
//			ofertaEmpleoVO.setHabilidadGeneral(ofertaSFP.getHabilidadGeneral().substring(0, 2000));

		ofertaEmpleoVO.setExperienciaAnios(ofertaSFP.getExperienciaAnios());
		ofertaEmpleoVO.setNumeroPlazas(VALORES_SFP.NUMERO_PLAZAS.getValorNumerico());	     
		ofertaEmpleoVO.setObservaciones(URL_OBSERVACIONES);
		ofertaEmpleoVO.setFechaAlta(ofertaSFP.getFechaAlta());
		ofertaEmpleoVO.setFechaInicio(ofertaSFP.getFechaAlta());
		ofertaEmpleoVO.setFechaModificacion(ofertaSFP.getFechaAlta());
		ofertaEmpleoVO.setFechaFin(ofertaSFP.getFechaFin());

		ofertaEmpleoVO.setIdTipoEmpleo(VALORES_SFP.TIPO_EMPLEO.getValorNumerico());
		ofertaEmpleoVO.setDiasLaborales(VALORES_SFP.DIAS_LABORALES.getValorCadena());
		ofertaEmpleoVO.setHoraEntrada(VALORES_SFP.HORA_INI.getValorCadena());
		ofertaEmpleoVO.setHoraSalida(VALORES_SFP.HORA_FIN.getValorCadena());
		ofertaEmpleoVO.setRolarTurno(VALORES_SFP.ROLAR_TURNO.getValorNumerico());
		ofertaEmpleoVO.setEmpresaOfrece(VALORES_SFP.EMPRESA_OFRECE.getValorCadena());
		ofertaEmpleoVO.setIdTipoContrato(VALORES_SFP.ID_TIPO_CONTRATO.getValorNumerico());
		ofertaEmpleoVO.setIdJerarquia(VALORES_SFP.ID_JERARQUIA.getValorNumerico());
		ofertaEmpleoVO.setLimitePostulantes(VALORES_SFP.LIMITE_POSTULANTES.getValorNumerico());
		ofertaEmpleoVO.setIdDiscapacidad(VALORES_SFP.ID_DISCAPACIDAD.getValorNumerico());
		ofertaEmpleoVO.setIdCausaVacante(VALORES_SFP.ID_DISCAPACIDAD.getValorNumerico());
		ofertaEmpleoVO.setDisponibilidadViajar(VALORES_SFP.DISPONIBILIDAD_VIAJAR.getValorNumerico());
		ofertaEmpleoVO.setDisponibilidadRadicar(VALORES_SFP.DISPONIBILIDAD_RADICAR.getValorNumerico());
		ofertaEmpleoVO.setEdadRequisito(VALORES_SFP.EDAD_REQUISITO.getValorNumerico());
		ofertaEmpleoVO.setGenero(VALORES_SFP.EDAD_REQUISITO.getValorNumerico());
		ofertaEmpleoVO.setMapaUbicacion(VALORES_SFP.MAPA_UBICACION.getValorCadena());
		ofertaEmpleoVO.setIdDuracionAproximada(VALORES_SFP.ID_DURACION_APROX.getValorNumerico());
		ofertaEmpleoVO.setFuente(VALORES_SFP.FUENTE.getValorNumerico());
		ofertaEmpleoVO.setContactoTel(VALORES_SFP.CONTACTO_TEL.getValorNumerico());
		ofertaEmpleoVO.setContactoCorreo(VALORES_SFP.CONTACTO_CORREO.getValorNumerico());
		ofertaEmpleoVO.setEstatus(ESTATUS.ACTIVO.getIdOpcion());
		
		if(null!=ofertaSFP.getCarreras() && !ofertaSFP.getCarreras().isEmpty()){
			ofertaEmpleoVO.setCarreras(ofertaSFP.getCarreras());
		}

		// añadimos los valores por defecto de los nuevos campos creados en la fusión Portal-SIISNE-AE		
		ofertaEmpleoVO.setIdOficinaRegistro(ConstantesGenerales.PORTAL_ID_OFICINA);
		
		// obtenemos el idUsuario de la oferta consultando la empresa
		EmpresaVO empresaVO = empresaFacade.findById(ofertaEmpleoVO.getIdEmpresa());
		ofertaEmpleoVO.setIdUsuario(empresaVO.getIdUsuario());
		ofertaEmpleoVO.setLimitePostulantes(ConstantesGenerales.MAXIMO_POSTULACIONES);
		ofertaEmpleoVO.setPublicarOfertas(Utils.toInt(Catalogos.DECISION.SI.getIdOpcion()));				
		ofertaEmpleoVO.setPlazasCubiertas(0);
		ofertaEmpleoVO.setDiscapacidades(ConstantesGenerales.TODAS_DISCAPACIDADES);
		ofertaEmpleoVO.setEdadRequisito(EDAD_REQUISITO.NO.getIdOpcion());
		
		return ofertaEmpleoVO;
	}

	/**
	 * Realiza la conexion al WS para obtener las ofertas de trabajo disponibles en la SF
	 * P
	 * @param fecha la fecha por la cual se filtran las ofertas de trabajo 
	 * @return una cadena con todas las ofertas encontradas en formato xml
	 */		
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	private List<OfertaEmpleoSFPVO> consultaOfertasSFP(String fecha) throws TechnicalException {
		List<OfertaEmpleoSFPVO> ofertas = null;

		try {
			mx.gob.stps.portal.core.ws.ofertas.sfp.SFPServiceStub reqWS = new mx.gob.stps.portal.core.ws.ofertas.sfp.SFPServiceStub();
			ListaVacantes listaVacantes = new ListaVacantes();	
			OfertaEmpleoSFPVO voOut = null;				
			listaVacantes.setFecha(fecha);
			ListaVacantesResponse respuesta = reqWS.listaVacantes(listaVacantes);	
			Vacante[] vacante = respuesta.get_return();
			if (vacante!=null){
				ofertas = new ArrayList<OfertaEmpleoSFPVO>();	
				for(SFPServiceStub.Vacante v : vacante) {					
					voOut = convertirVacanteStubAOfertaSFPVO(v);
					if(null!=voOut){
						ofertas.add(voOut);
					}					
				}					
			}
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ofertas;		
	}

	private OfertaEmpleoSFPVO convertirVacanteStubAOfertaSFPVO(SFPServiceStub.Vacante v){
		OfertaEmpleoSFPVO voOut = new OfertaEmpleoSFPVO();

		try{
			
			voOut.setIdOfertaBolsaSFP((long)v.getIdVacante());	
			
			DomicilioVO domicilio = crearDomicilioVORelacionadoConVacante(v);
			if(null==domicilio){
				logger.info("---Vacante sin domicilio:" + v.getIdVacante());
				return null;			
			} else {
				voOut.setDomicilio(domicilio);
			}					
			
			OfertaUbicacionVO ofertaUbicacion = crearOfertaUbicacionParaVacante(v);
			if(null==ofertaUbicacion){
				logger.info("---Vacante sin ubicacion:" + v.getIdVacante());
			} else {
				voOut.setOfertaUbicacion(ofertaUbicacion);
			}
			
			voOut.setTituloOferta(v.getPuesto());
			voOut.setIdAreaLaboral(v.getIdAreaLaboral());
			voOut.setIdOcupacion(v.getIdOcupacion());
			voOut.setIdSituacionAcademica(v.getIdSituacion());	
			voOut.setIdEmpresa(v.getIdEmpresa());
			voOut.setSalario(v.getSalario());		
			voOut.setFunciones(v.getFunciones());
			voOut.setHabilidadGeneral(v.getHabilidad());
			voOut.setFechaAlta(v.getFechaAlta());				
			voOut.setFechaFin(v.getFechaVencimiento());					
			voOut.setObservaciones(v.getObservaciones());
			voOut.setIdNivelEstudio(v.getIdEscolaridad());

			int[] intCarreras = v.getIdEspecialidad();
			
			List<OfertaCarreraEspecialidadVO> carreras = setCarreraEspecialidadVORelacionadasConVacante(intCarreras);	
			
			voOut.setCarreras(carreras);				
			
		}catch(Exception e){
			logger.error(e);
		}
				
		return voOut;
	}

	
	private DomicilioVO crearDomicilioVORelacionadoConVacante(SFPServiceStub.Vacante v){
		DomicilioVO domicilio = new DomicilioVO();
		logger.info("Domicilio idVacante " + v.getIdVacante() + " v.getIdMunicipio():" + v.getIdMunicipio()
				+ " v.getIdEntidad():" + v.getIdEntidad() +  " v.getCalle():" + v.getCalle() 
				+ " v.getCodigoPostal():" + v.getCodigoPostal() + " v.getNumeroExt():" + v.getNumeroExt());		
		try{
		
			if(v.getIdMunicipio()<1){
				logger.info("v.setIdMunicipio=NA for idVacante " + v.getIdVacante());
			} else {
				domicilio.setIdMunicipio(v.getIdMunicipio());	
			}		
			
			if(v.getIdEntidad()<1){
				logger.info("v.setIdEntidad=NA for idVacante " + v.getIdVacante());
				return null;
			} else {
				domicilio.setIdEntidad(v.getIdEntidad());	
			}
			
			if(null!=v.getCalle()){
				domicilio.setCalle(v.getCalle());
			} else {
				domicilio.setCalle("NA");
				//logger.info("voOut.v.setCalle=NA for idVacante " + v.getIdVacante());
			}
			if(null!=v.getCodigoPostal()){
				domicilio.setCodigoPostal(v.getCodigoPostal());
			} else {
				domicilio.setCodigoPostal("0");
				//logger.info("v.setCodigoPostal=0 for idVacante " + v.getIdVacante());
			}										
			if(null!=v.getNumeroExt()){
				domicilio.setNumeroExterior(v.getNumeroExt());
			} else {
				domicilio.setNumeroExterior("NA");
				//logger.info("voOut.v.setNumeroExterior=NA for idVacante " + v.getIdVacante());
			}				
			
		}catch(Exception e){
			logger.error(e);
		}		
			
		return domicilio;
	}
	
	private OfertaUbicacionVO crearOfertaUbicacionParaVacante(SFPServiceStub.Vacante v){
		OfertaUbicacionVO ofertaUbicacion = new OfertaUbicacionVO();
		
		try{
			if(v.getIdEntidad()>=1 && v.getIdMunicipio()>=1){
				ofertaUbicacion.setIdMunicipio(v.getIdMunicipio());	
				ofertaUbicacion.setIdEntidad(v.getIdEntidad());		
			} else{
				return null;
			}
			
		}catch(Exception e){
			logger.error(e);
		}		
		
		return ofertaUbicacion;
	}
	

	private List<OfertaCarreraEspecialidadVO> setCarreraEspecialidadVORelacionadasConVacante(int[] carrerasVacante){
		List<OfertaCarreraEspecialidadVO> carrerasRelacionadas = new ArrayList<OfertaCarreraEspecialidadVO>();		
		
		try{

			if (null!=carrerasVacante) {
				int principal = 0;
				for(int i=0; i<carrerasVacante.length; i++){		
					OfertaCarreraEspecialidadVO carreraRelacionada = new OfertaCarreraEspecialidadVO();
					carreraRelacionada.setId(carrerasVacante[i]);
					if(principal==0){
						carreraRelacionada.setPrincipal(MULTIREGISTRO.PRINCIPAL.getIdOpcion());		
						principal++;
					} else{
						carreraRelacionada.setPrincipal(MULTIREGISTRO.ADICIONAL.getIdOpcion());
					}				
					carrerasRelacionadas.add(i, carreraRelacionada);
				}			
			}			
			
		}catch(Exception e){
			logger.error(e);
		}			
		
		return carrerasRelacionadas;
	}


	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	private OfertaExternaTotalVO cargaOfertasExternasSFP(List<OfertaEmpleoSFPVO> ofertasSFP){
		OfertaExternaTotalVO totales = new OfertaExternaTotalVO();

		if (ofertasSFP==null) {
			logger.info("El WebService de FP no genero objetos ofertas" );
			return totales;
		}

		int numeroDeOfertasInsertadas = 0;
		int numeroDeOfertasActualizadas = 0;

		Connection conn = null;
		Context context = null;
		try {
			setIdSector();

    		ConnectionWraper wraper = ConexionFactory.getInstance().getConnection();
    		conn = wraper.getConnection();
    		context = wraper.getContext();

			MunicipioSFPDAO municipioSFPDAO = new MunicipioSFPDAO(conn);
			OfertasSFPDAO ofertasDAO = new OfertasSFPDAO(conn);

			logger.info("Se procesan "+ ofertasSFP.size() +" ofertas. ["+ getFechaYHoraActualesFormateadas() +"]");

			for (OfertaEmpleoSFPVO ofertaSFP : ofertasSFP) {
							
				try {
					logger.info("Procesando la oferta "+ ofertaSFP.getTituloOferta() +" ["+ getFechaYHoraActualesFormateadas() +"]");

					int ofertaExisteYTieneCarrerasRelacionadas = ofertasDAO.existeVacante(ofertaSFP); 
					//logger.info("ofertaExisteYTieneCarrerasRelacionadas:"+ ofertaExisteYTieneCarrerasRelacionadas + " para idOfertaEmpleo: " + ofertaSFP.getIdOfertaEmpleo());

					long idMunicipio = municipioSFPDAO.obtenerMunicipioSFP(ofertaSFP.getDomicilio().getIdEntidad(), ofertaSFP.getDomicilio().getIdMunicipio());
					
					if(idMunicipio <= 0){
						logger.error("Municipio no localizado, entidad ["+ ofertaSFP.getDomicilio().getIdEntidad() +"] y Municipio ["+ ofertaSFP.getDomicilio().getIdMunicipio() +"] ");
						continue;
					}

					OfertaEmpleoVO ofertaEmpleo = null;
					//logger.info("queHacer:"+ queHacer);
					if(ofertaExisteYTieneCarrerasRelacionadas == OFERTA_EXISTE_Y_NO_TIENE_CARRERAS_RELACIONADAS){
						logger.info("Ya existe la oferta ["+ ofertaSFP.getTituloOferta() +"]: SFP, " + ofertaSFP.getIdOfertaBolsaSFP());
					} else {
						long idEmpresa = ofertaSFP.getIdEmpresa();							
						EmpresaVO tmpEmpresa = empresaFacade.findById(idEmpresa);
						String tmpNombreEmpresa = tmpEmpresa.getNombreEmpresa();
						ofertaSFP.setNombreEmpresa(tmpNombreEmpresa);
						tmpEmpresa = null;

						ofertaEmpleo = crearOfertaEmpleoVO(ofertaSFP);						
					}
					
					insertarOActualizarOferta(ofertaExisteYTieneCarrerasRelacionadas, ofertaSFP, ofertaEmpleo, idMunicipio);
					
					switch (ofertaExisteYTieneCarrerasRelacionadas) {
					case OFERTA_NO_EXISTE: numeroDeOfertasInsertadas++; break;
					case OFERTA_EXISTE_Y_TIENE_CARRERAS_RELACIONADAS: numeroDeOfertasActualizadas++; break;
					}

				} catch (SQLException e) {
					logger.error("OfertasSFPAppService.cargaOfertasExternasSFP: ha ocurrido un error con la oferta de SFP "+(ofertaSFP.getIdOfertaEmpleo() != null ? ofertaSFP.getIdOfertaEmpleo() : ""));
					logger.error(e.getMessage());
					logger.error(e);
				}				
			} 

			logger.info("Terminado el proceso de creacion de Ofertas de SFP, con [" + ofertasSFP.size() + "] consultadas,  con ["+ 
					numeroDeOfertasInsertadas +"] registradas y ["+ numeroDeOfertasActualizadas +"] actualizadas. ["+ getFechaYHoraActualesFormateadas() +"]");

			totales.setTotalOfertasConsultadas(ofertasSFP.size());
			totales.setTotalOfertasAgregadas(numeroDeOfertasInsertadas);
			totales.setTotalOfertasModificadas(numeroDeOfertasActualizadas);

		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1);
		} finally {
			try{if (conn!=null) conn.close();}catch(Exception e){e.printStackTrace();}
			
			if (context!=null){
				logger.info("Cerrando context");
				try {
					context.close();
				} catch (Exception e) {e.printStackTrace();}
			}
		}

		return totales;
	}

	private String getFechaYHoraActualesFormateadas(){
		return Utils.formatTime(Calendar.getInstance().getTime());
	}

	private void insertarOActualizarOferta(int ofertaExisteYTieneCarrerasRelacionadas, OfertaEmpleoSFPVO oferta, OfertaEmpleoVO ofertaEmpleo, long idMunicipio){
		
		switch (ofertaExisteYTieneCarrerasRelacionadas) {
		case OFERTA_NO_EXISTE:				
			insertaOfertaSFP(oferta, ofertaEmpleo, getIdSector(), idMunicipio);
			break;
		case OFERTA_EXISTE_Y_TIENE_CARRERAS_RELACIONADAS:
			actualizaOfertaSFP(oferta, ofertaEmpleo, idMunicipio);
			break;
		}		
	}

	private void insertaOfertaSFP(OfertaEmpleoSFPVO ofertaSFP, OfertaEmpleoVO ofertaEmpleo, long idSector, long idMunicipio){

		try{
			
			if (ofertaEmpleo != null) {
				
				long idOfertaEmpleo = ofertaEmpleoFacade.saveAndFlush(ofertaEmpleo);
				insertaCarrerasEspecRelacionadasConOfertaEmpleo(ofertaSFP.getCarreras(), ofertaEmpleo);
				ofertaExternaFacade.save(idOfertaEmpleo, ofertaSFP.getIdOfertaBolsaSFP());
				insertaDomicilioRelacionadoConOfertaEmpleo(ofertaSFP.getDomicilio(), ofertaEmpleo, idMunicipio);

				ofertaSFP.getOfertaUbicacion().setIdMunicipio(Utils.toInt(idMunicipio));
				insertaOfertaUbicacion(idOfertaEmpleo, ofertaSFP.getOfertaUbicacion());
				
				ofertaEmpleo = null;

				ofertaFacade.registraSector(idOfertaEmpleo, idSector);
//FIXME OracleText
//				indexaOfertaEmpleo(idOfertaEmpleo);
			}						
			
		}catch(Exception e){
			logger.error(e);
		}						
	}

	private void actualizaOfertaSFP(OfertaEmpleoSFPVO ofertaSFP, OfertaEmpleoVO ofertaEmpleo, long idMunicipio){
		
		try{
			
			if (ofertaEmpleo != null) {
				long idOfertaEmpleo = ofertaEmpleo.getIdOfertaEmpleo();			
				ofertaEmpleoFacade.update(ofertaEmpleo);				
				
				
				List <OfertaCarreraEspecialidadVO> carreras = ofertaFacade.getCarrerasEspecialidades(idOfertaEmpleo);
				if(null==carreras || carreras.isEmpty()){				
					carreras = ofertaSFP.getCarreras();
					//logger.info("-----actualizaOfertaSFP obtener carreras del ofertaSFP carreras.size: " + carreras.size());
				}			
											
				actualizaCarrerasEspecRelacionadasConOfertaEmpleo(carreras,ofertaEmpleo);
				//logger.info("-----actualizaCarrerasEspecRelacionadasConOfertaEmpleo oferta empleo");
				
				actualizaDomicilioRelacionadoConOfertaEmpleo(ofertaSFP.getDomicilio(), idOfertaEmpleo, idMunicipio);				
				ofertaSFP.getOfertaUbicacion().setIdMunicipio(Utils.toInt(idMunicipio));
				actualizaDomicilioRelacionadoConOfertaEmpleo(idOfertaEmpleo, ofertaSFP.getOfertaUbicacion());	
				
				ofertaEmpleo = null;
//FIXME OracleText
//				indexaOfertaEmpleo(idOfertaEmpleo);
			}else {
				logger.info("Se nulifico la Oferta de SFP: " + ofertaSFP.getIdOfertaBolsaSFP() );
			}						
			
		}catch(Exception e){
			logger.error(e);
		}					
	}

//FIXME OracleText
/*  private void indexaOfertaEmpleo(long idOfertaEmpleo) {

 		try {

			IndexerServiceLocator.getIndexerServiceRemote().indexaOfertaEmpleo(idOfertaEmpleo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ERROR al indexar la oferta ["+ idOfertaEmpleo +"] proveniente de SFP");
			logger.error(e);
		}			
    }		
 */

	private void insertaDomicilioRelacionadoConOfertaEmpleo(DomicilioVO domicilioOfertaSFP, OfertaEmpleoVO ofertaEmpleo, long idMunicipio){
		domicilioOfertaSFP.setIdMunicipio(idMunicipio);
		domicilioOfertaSFP.setIdPropietario(ofertaEmpleo.getIdOfertaEmpleo());
		domicilioOfertaSFP.setIdTipoPropietario(TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
		domicilioOfertaSFP.setEntreCalle(NO_APLICA);
		domicilioOfertaSFP.setFechaAlta(obtenerFechaAltaValida(ofertaEmpleo.getFechaAlta())); 						
		domicilioOfertaSFP.setIdColonia(CERO);
		domicilioOfertaSFP.setyCalle(NO_APLICA);
		domicilioFacade.saveSFP(domicilioOfertaSFP);			
	}				
	
	private void insertaOfertaUbicacion(long idOfertaEmpleo, OfertaUbicacionVO ofertaUbicacionVo){
		ofertaUbicacionVo.setFechaAlta(new Date());
		ofertaUbicacionVo.setIdOfertaEmpleo(idOfertaEmpleo);
		ofertaUbicacionFacade.save(ofertaUbicacionVo);
	}

	private void actualizaDomicilioRelacionadoConOfertaEmpleo(DomicilioVO domicilioOfertaSFP, long idOfertaEmpleo, long idMunicipio){
		DomicilioVO domicilio = domicilioFacade.buscarDomicilioIdPropietario(idOfertaEmpleo, TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
		if (domicilio==null || domicilio.getIdDomicilio() < 1){
			domicilio = new DomicilioVO();			
			domicilioOfertaSFP.setIdPropietario(idOfertaEmpleo);
			domicilioOfertaSFP.setIdTipoPropietario(TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());	
			domicilioOfertaSFP.setEntreCalle(NO_APLICA);
			domicilioOfertaSFP.setFechaAlta(obtenerFechaAltaValida(new Date())); 	
			domicilioOfertaSFP.setIdColonia(CERO);
			domicilioOfertaSFP.setyCalle(NO_APLICA);			
			//logger.info("----Domicilio null o idDomicilio=0 para idOfertaEmpleo:" +idOfertaEmpleo );
			domicilio.setEntidad(domicilioOfertaSFP.getEntidad());
			domicilio.setIdMunicipio(idMunicipio);
			domicilio.setCalle(domicilioOfertaSFP.getCalle());
			domicilio.setCodigoPostal(domicilioOfertaSFP.getCodigoPostal());
			domicilio.setNumeroExterior(domicilioOfertaSFP.getNumeroExterior());
			domicilioOfertaSFP.setIdMunicipio(idMunicipio);
			domicilioFacade.saveSFP(domicilioOfertaSFP);		
		} else {
			domicilio.setEntidad(domicilioOfertaSFP.getEntidad());
			domicilio.setIdMunicipio(idMunicipio);
			domicilio.setCalle(domicilioOfertaSFP.getCalle());
			domicilio.setCodigoPostal(domicilioOfertaSFP.getCodigoPostal());
			domicilio.setNumeroExterior(domicilioOfertaSFP.getNumeroExterior());
			domicilioFacade.update(domicilio);			
		}					
	}
	
	private void actualizaDomicilioRelacionadoConOfertaEmpleo(long idOfertaEmpleo, OfertaUbicacionVO ofertaUbicacionVo){
		OfertaUbicacionVO ofertaUbicacion = ofertaUbicacionFacade.finfByIdOfertaEmpleo(idOfertaEmpleo);
		if (ofertaUbicacion==null || ofertaUbicacion.getIdOfertaUbicacion() < 1){
			ofertaUbicacionVo.setFechaAlta(new Date());
			ofertaUbicacionVo.setIdOfertaEmpleo(idOfertaEmpleo);
			ofertaUbicacionFacade.save(ofertaUbicacionVo);				
		} else {
			ofertaUbicacion.setIdEntidad(ofertaUbicacionVo.getIdEntidad());
			ofertaUbicacion.setIdMunicipio(ofertaUbicacionVo.getIdMunicipio());
			ofertaUbicacionFacade.update(ofertaUbicacion, idOfertaEmpleo);
		}
		
	}

	private void insertaCarrerasEspecRelacionadasConOfertaEmpleo(List<OfertaCarreraEspecialidadVO> carrerasRelacionadas, OfertaEmpleoVO ofertaEmpleo){	
		
		try{
		
			if (null!=carrerasRelacionadas && !carrerasRelacionadas.isEmpty()) {
				for(OfertaCarreraEspecialidadVO carreraRelacionada: carrerasRelacionadas){
					carreraRelacionada.setFechaAlta(obtenerFechaAltaValida(ofertaEmpleo.getFechaAlta())); 
					carreraRelacionada.setIdRegistro(ofertaEmpleo.getIdOfertaEmpleo());
					//logger.info("Procesando la oferta "+ ofertaEmpleo.getIdOfertaEmpleo() +" [idReg:" + carreraRelacionada.getIdRegistro() + " id:" + carreraRelacionada.getId()  + "]");
					ofertaCarreraEspecFacade.save(carreraRelacionada);
				}				
			} else {
				logger.error("Procesando la oferta "+ ofertaEmpleo.getIdOfertaEmpleo() +" [no tiene carreras]");
			}						
			
		}catch(Exception e){
			logger.error(e);
		}					
	}

	private void actualizaCarrerasEspecRelacionadasConOfertaEmpleo(List <OfertaCarreraEspecialidadVO> carrerasRelacionadas, OfertaEmpleoVO ofertaEmpleo){	
		
		try{
		
			if(existeAlMenosUnaCarreraEspecPrincipal(carrerasRelacionadas)){
				for(OfertaCarreraEspecialidadVO carreraRelacionada: carrerasRelacionadas){
					if(carreraRelacionada.getId()>0){
						carreraRelacionada.setFechaAlta(obtenerFechaAltaValida(ofertaEmpleo.getFechaAlta())); 						
						ofertaCarreraEspecFacade.update(carreraRelacionada, ofertaEmpleo.getIdOfertaEmpleo());						
					}
				}					
			} else {
				int principal = 0;
				for(OfertaCarreraEspecialidadVO carreraRelacionada: carrerasRelacionadas){
					if(carreraRelacionada.getId()>0){
						if(principal==0){
							carreraRelacionada.setPrincipal(MULTIREGISTRO.PRINCIPAL.getIdOpcion());
							principal++;
						}
						carreraRelacionada.setFechaAlta(obtenerFechaAltaValida(ofertaEmpleo.getFechaAlta())); 				
						ofertaCarreraEspecFacade.update(carreraRelacionada, ofertaEmpleo.getIdOfertaEmpleo());									
					}
				}							
			}			
			
		}catch(Exception e){
			logger.error(e);
		}					
	}		
	
	private Date obtenerFechaAltaValida(Date fechaAlta){
		return fechaAlta==null ? new Date() : fechaAlta;
	}

	private boolean existeAlMenosUnaCarreraEspecPrincipal(List <OfertaCarreraEspecialidadVO> carrerasRelacionadas){
		if (null!=carrerasRelacionadas && !carrerasRelacionadas.isEmpty()) {
			for(OfertaCarreraEspecialidadVO carreraRelacionada: carrerasRelacionadas){
				if((int)carreraRelacionada.getPrincipal()==MULTIREGISTRO.PRINCIPAL.getIdOpcion())
					return true;
			}
		}
		return false;
	}
	
	private void setIdSector(){
		PropertiesLoader properties = PropertiesLoader.getInstance();		
		String propiedadIdSector = properties.getProperty("oferta.externa.sfp.idsector");
		idSector = Utils.parseLong(propiedadIdSector);					
	}
	
	private long getIdSector(){
		return idSector;
	}		

	public List<OfertaPorCanalVO> buscaTodasOfertasSFP() throws SQLException{
		OfertasSFPDAO ofertasDAO = new OfertasSFPDAO();
		return ofertasDAO.buscaTodasOfertasSFP();
	}	
	
	public List<OfertaEmpleoOutVO> buscarOfertasSFP() throws SQLException{
		OfertasSFPDAO ofertasDAO = new OfertasSFPDAO();
		return ofertasDAO.buscaOfertasSFP();
	}	
	
	public List<OfertaEmpleoOutVO> buscarOfertasSFP(int idEntidad, Long idOcupacion) throws SQLException{
		OfertasSFPDAO ofertasDAO = new OfertasSFPDAO();
		return ofertasDAO.buscaOfertasSFP(idEntidad, idOcupacion);
	}

	public OfertaEmpleoSFPVO buscarOfertaSFP(Long idOferta) throws SQLException, ParseException{
		OfertasSFPDAO ofertasDAO = new OfertasSFPDAO();
		return ofertasDAO.buscaOfertaSFP(idOferta);
	}

}
