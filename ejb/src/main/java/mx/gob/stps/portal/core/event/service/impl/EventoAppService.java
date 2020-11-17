package mx.gob.stps.portal.core.event.service.impl;

import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.NoResultException;
import javax.servlet.ServletOutputStream;

import mx.gob.stps.portal.core.event.service.EventoAppServiceRemote;
import mx.gob.stps.portal.core.persistencia.facade.BitacoraFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.EventoFacadeLocal;
import mx.gob.stps.portal.core.search.service.IndexerServiceLocator;
import mx.gob.stps.portal.exception.BusinessException;
import mx.gob.stps.portal.exception.TechnicalException;
import mx.gob.stps.portal.persistencia.entity.CandidatoEvento;
import mx.gob.stps.portal.persistencia.entity.EventoFolio;
import mx.gob.stps.portal.persistencia.vo.ComprobanteRegistroEventoCandidatoVO;
import mx.gob.stps.portal.persistencia.vo.EmpresaEventoVO;
import mx.gob.stps.portal.persistencia.vo.EventoVO;
import mx.gob.stps.portal.persistencia.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.utils.Catalogos;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.log4j.Logger;



@Stateless(name="EventoAppService", mappedName="EventoAppService")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class EventoAppService implements EventoAppServiceRemote {
	
	private static final Logger logger = Logger.getLogger(EventoAppService.class);
	
	@EJB
	private EventoFacadeLocal eventoFacade;
	
	@EJB
	private BitacoraFacadeLocal bitacoraFacade;

	public static final int BAREVTO = 5;//valor maximo para integrar el idevento al codigo de barras
	public static final int BARFOL = 7;//valor maximo par integrar el folio al codigo de barras
	private List<OfertaEmpleoVO> ofertas;
	
	@Override
	public List<EventoVO> obtieneEventosFerias(Integer idEntidad) {
		List<EventoVO> eventos = new ArrayList<EventoVO>();
		try {
				eventos = eventoFacade.obtieneEventosFerias(idEntidad);
			} catch (NoResultException e) {
				e.printStackTrace();
			} catch (TechnicalException e) {
				e.printStackTrace();
			}
			
		
		return eventos;
	}

	@Override
	public List<EmpresaEventoVO> consultarEmpresasEvento(Long idEvento) {
		List<EmpresaEventoVO> empresasEvento = new ArrayList<EmpresaEventoVO>();
		try {
			empresasEvento = eventoFacade.consultarEmpresasEvento(idEvento);
		} catch (NoResultException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TechnicalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return empresasEvento;
	}

	@Override
	public CandidatoEvento obtieneEventosCandidato(Long idCandidato,
			Long idEvento) {
		CandidatoEvento candidatoEvento = new CandidatoEvento();
		try {
			candidatoEvento = eventoFacade.obtieneEventosCandidato(idCandidato, idEvento);
		} catch (NoResultException e) {
			e.printStackTrace();
		} catch (TechnicalException e) {
			e.printStackTrace();
		}
		return candidatoEvento;
	}

	@Override
	public EventoFolio findEventoFolio(long idEvento) {
		EventoFolio evFol= null;
		
		try {
			evFol = eventoFacade.findEventoFolio(idEvento);
		} catch (NoResultException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TechnicalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	return evFol;
	}

	@Override
	public Long registrarCandidatoEvento(Long idCandidato, int idEvento,
			int folioReg, Long idUsuario) {
		Long idRegistro = null;
		try {
			idRegistro = eventoFacade.registrarCandidatoEvento(idCandidato, idEvento, folioReg,idUsuario);
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return idRegistro;
	}

	@Override
	public boolean actualizaFolio(EventoFolio eventoFol) {
		boolean actualiza= false;
		try {
			actualiza = eventoFacade.actualizaFolio(eventoFol);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return actualiza;
	}

	@Override
	public EventoVO findEventoById(Long idEvento) {
		EventoVO eventoVO = new EventoVO();
		SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
		
		try {
			eventoVO = eventoFacade.findEventoById(idEvento);
			eventoVO.setEntidad(Catalogos.ENTIDADES_FEDERATIVAS.getDescripcion(eventoVO.getIdEntidad()));
			eventoVO.setAmbiente(Catalogos.TIPO_AMBIENTE.getDescripcion(eventoVO.getIdAmbiente()));
			eventoVO.setFechaE(sd.format(eventoVO.getFechaInicio()));
			if(eventoVO.getIdTipoAsentamiento() == 7)
				eventoVO = eventoFacade.asignaMunicipioColonia(eventoVO);
			else
				eventoVO = eventoFacade.asignaSoloMunicipio(eventoVO);
				
		} catch (NoResultException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (TechnicalException e) {
			e.printStackTrace();
		}
		return eventoVO;
	}

	@Override
	public void registraBitacora(Long idUsuario, long idRegistro,int folioReg, Long idCandidato, int idEvento) {
		String detalle = "folioReg="+folioReg+"|idEvento="+idEvento+"|idCandidato="+idCandidato;
		long now = System.currentTimeMillis();
    	Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(now);
		bitacoraFacade.save(Catalogos.EVENTO.REGISTRA_CANDIDATO_EVENTO.getIdEvento(), idUsuario, Catalogos.EVENTO.REGISTRA_CANDIDATO_EVENTO.getEvento(), calendar, detalle, idRegistro, Catalogos.TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
		
	}

	@Override
	public ServletOutputStream imprimeComprobantePDF(
			ServletOutputStream servletOutputStream,
			List<OfertaEmpleoVO> ofertasCompat, String rutaHeader,
			String rutaFooter, Long idCandidato, Long idEvento) {
		SimpleDateFormat sd = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy', Hora: ' H:mm", new Locale("es","ES"));
		ComprobanteRegistroEventoCandidatoVO comprobante;
		List<ComprobanteRegistroEventoCandidatoVO> lista = new ArrayList<ComprobanteRegistroEventoCandidatoVO>();
		try {
			comprobante = eventoFacade.consultaCandidatoEventoImprimir(idCandidato, idEvento);
			long now = System.currentTimeMillis();
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(now);
			Date fechaHoy = calendar.getTime();
			comprobante.setFechaComprobante(sd.format(fechaHoy));
			String idevto = idEvento.toString();
			StringBuilder s = new StringBuilder();
			
			int tam = idevto.length();
			int agreg = BAREVTO-tam;
			for(int j = 0; j <agreg; j++){
				s.append("0");
			}
			s.append(idevto);
			Long folR = comprobante.getFolioRegCandi();
			String folre = folR.toString();
			tam = folre.length();
			agreg = BARFOL - tam;
			for(int k = 0; k < agreg; k++){
				s.append("0");
			}
			s.append(folre);
			String codigo = s.toString();
			comprobante.setCodigoBarras(codigo);
			comprobante.setOfertasCompatibles(ofertasCompat);
			lista.add(comprobante);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		HashMap parametros = new HashMap();
		InputStream istr = null;
		
		parametros.put("logo", this.getClass().getResourceAsStream(rutaHeader));
		parametros.put("logoFooter",this.getClass().getResourceAsStream(rutaFooter));
		try {
			JRBeanCollectionDataSource jrb = new JRBeanCollectionDataSource(lista);
			logger.info("el jrb es "+jrb.toString());
			if(ofertasCompat.isEmpty()){
				istr = this.getClass().getResourceAsStream("comprobanteRegistroCandidatoNoCompat.jrxml");
			
			}else{
				istr = this.getClass().getResourceAsStream("comprobanteRegistroCandidato.jrxml");
			}
			logger.info("el istr sí está "+istr.toString());
			JasperDesign jasperDes = JRXmlLoader.load(istr);
			JasperReport reporJ = JasperCompileManager.compileReport(jasperDes);
			JasperPrint impRep = JasperFillManager.fillReport(reporJ, parametros,jrb);
			JasperExportManager.exportReportToPdfStream(impRep, servletOutputStream);
			logger.info("el servlet output "+servletOutputStream.toString());
			return servletOutputStream;
		} catch (JRException e) {
			//System.out.println(e.getStackTrace());
			e.printStackTrace();
			
			return null;
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e);
			return null;
		}
	}
	
	public List<Long[]> consultaIndexador(Long idCandidato, Integer estatusPPC){
		List<Long[]> compatible = new ArrayList<Long[]>();
		if(estatusPPC == null){
			compatible = IndexerServiceLocator.getIndexerServiceRemote().buscaOfertasRecomendadas(idCandidato, 60,Catalogos.CATALOGO_FUENTE_QUE_REGISTRA_OFERTA.FERIAS.getIdOpcion());
		}else{
			if(estatusPPC.equals(Catalogos.ESTATUS.ACTIVO_PPC.getIdOpcion()) || estatusPPC.equals(Catalogos.ESTATUS.INACTIVO_PPC.getIdOpcion()))
				compatible = IndexerServiceLocator.getIndexerServiceRemote().buscaOfertasRecomendadas(idCandidato, 70,Catalogos.CATALOGO_FUENTE_QUE_REGISTRA_OFERTA.FERIAS.getIdOpcion());
			else
				compatible = IndexerServiceLocator.getIndexerServiceRemote().buscaOfertasRecomendadas(idCandidato, 60,Catalogos.CATALOGO_FUENTE_QUE_REGISTRA_OFERTA.FERIAS.getIdOpcion());
		}
		return compatible;
	}
	
	private void ordenaCompatibilidad(List<OfertaEmpleoVO> lista){
		Comparator<OfertaEmpleoVO> comparator = new Comparator<OfertaEmpleoVO>(){
			@Override 
			public int compare(OfertaEmpleoVO oferta1,OfertaEmpleoVO oferta2){
				int comparaCompat = (int) (oferta2.getCompatibilidad()-oferta1.getCompatibilidad());
				if(comparaCompat == 0){
					return oferta1.getFechaAlta().compareTo(oferta2.getFechaAlta());
				}else{
					return comparaCompat;
				}
			}
		};
		Collections.sort(lista,comparator);
	}
	
	public List<OfertaEmpleoVO> consultaOfertasCompatiblesEvento(Integer idEvento, Long idCandidato, Integer estatusPPC){
		List<Long[]> compatibles = (List<Long[]>) consultaIndexador(idCandidato, estatusPPC);
		ofertas = new ArrayList<OfertaEmpleoVO>();
		List<OfertaEmpleoVO> ofertasAux = new ArrayList<OfertaEmpleoVO>();
		StringBuilder parametro = new StringBuilder();
		Long[] idOferta;
		if(!compatibles.isEmpty()){
			HashMap<Long, Long> compatibilidades = new HashMap<Long, Long>();
			for(int i = 0; i < compatibles.size();i++){
				idOferta = compatibles.get(i);
				Long idOf;
				idOf = idOferta[0];
				parametro.append(idOf).append(",");
				compatibilidades.put(idOf, idOferta[1]);
			}
			String cadena = parametro.toString();
			String param = cadena.substring(0, (cadena.length()-1));
			ofertasAux = eventoFacade.findOfertaEmpleoEvento(param,idEvento);
			if(ofertasAux.size()>0 && !ofertasAux.isEmpty()){
				for(OfertaEmpleoVO ofe: ofertasAux){
					Long idoferta = ofe.getIdOfertaEmpleo();
					Long compatibilidad = (Long) compatibilidades.get(idoferta);
					ofe.setCompatibilidad(compatibilidad);
				}
				ordenaCompatibilidad(ofertasAux);
				if(ofertasAux.size() > 3)
					ofertas = ofertasAux.subList(0, 3);
				else
					ofertas = ofertasAux;
			}
		}
		return ofertas;
		
	}
	
	public List<EventoVO> eventosRecientes(int mes, int anio, int estatus) throws Exception  {
		List<EventoVO> eventos = new ArrayList<EventoVO>();
		try {
			eventos = (List<EventoVO>) eventoFacade.eventosRecientes(mes,anio,estatus);
		} catch (NoResultException e) {
			e.printStackTrace();
		} catch (TechnicalException e) {
			e.printStackTrace();
		}
		return eventos;
	}
	
	public List<EventoVO> previousEventList(int month, int year, int status) throws Exception  {
		List<EventoVO> eventList = new ArrayList<EventoVO>();
		try {
			eventList = (List<EventoVO>) eventoFacade.previousEventList(month, year ,status);
		} catch (NoResultException e) {
			e.printStackTrace();
		} catch (TechnicalException e) {
			e.printStackTrace();
		}
		return eventList;
	}
}
