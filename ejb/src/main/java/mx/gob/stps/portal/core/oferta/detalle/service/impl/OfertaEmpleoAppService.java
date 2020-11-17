package mx.gob.stps.portal.core.oferta.detalle.service.impl;

import static mx.gob.stps.portal.core.infra.utils.Constantes.ID_PARAMETRO_NUM_VACANTES_RSS;
import static mx.gob.stps.portal.core.infra.utils.Constantes.ID_PARAMETRO_TOTAL_VACANTES_STPS;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.autorizacion.vo.ParametroVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.oferta.detalle.service.OfertaEmpleoAppServiceLocal;
import mx.gob.stps.portal.core.oferta.detalle.service.OfertaEmpleoAppServiceRemote;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.core.oferta.vo.CurrentOfferAreaOcupacionVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaDetalleCortoVO;
import mx.gob.stps.portal.core.persistencia.facade.OfertaEmpleoFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.ParametroFacadeLocal;
import mx.gob.stps.portal.persistencia.vo.OfertasRecientesVO;

import org.apache.log4j.Logger;

@Stateless(name = "OfertaEmpleoAppService", mappedName = "OfertaEmpleoAppService")
public class OfertaEmpleoAppService implements OfertaEmpleoAppServiceRemote, OfertaEmpleoAppServiceLocal {
	
	private static Logger logger = Logger.getLogger(OfertaEmpleoAppService.class);
	
	@EJB private OfertaEmpleoFacadeLocal ofertaEmpleoFacade;
	
	@EJB private ParametroFacadeLocal parametroFacade;
	
	/*@Override
	public int registrarOfertaEmpleo(OfertaEmpleoVO vo) throws BusinessException {
		int result = -1;
		try {
			if (null != vo)
				result = OfertaEmpleoFacade.create(vo);
		}catch(PersistenceException e) {
    		logger.error(e);
    		throw new BusinessException("No se realizó el registro de oferta", e);
    	}
		return result;
	}*/

	@Override
	public OfertaEmpleoVO buscarOfertaEmpleo(long idOfertaEmpleo) throws BusinessException {
		OfertaEmpleoVO vo = new OfertaEmpleoVO();
		try {
			vo = ofertaEmpleoFacade.findById(idOfertaEmpleo);
		}catch(PersistenceException e) {
    		logger.error(e);
    		throw new BusinessException("No se concretó la búsqueda de oferta", e);
    	}
		return vo;
	}

	/*@Override
	public void actualizarOfertaEmpleo(OfertaEmpleoVO vo) throws BusinessException {
		try {
			if (null != vo)
				OfertaEmpleoFacade.update(vo);
		}catch(PersistenceException e) {
    		logger.error(e);
    		throw new BusinessException("No se actualizó la oferta", e);
    	}
	}*/

	@Override
	public void eliminarOfertaEmpleo(long idOfertaEmpleo) throws BusinessException {
		try {
			ofertaEmpleoFacade.remove(idOfertaEmpleo);
		}catch(PersistenceException e) {
    		logger.error(e);
    		throw new BusinessException("No se eliminó la oferta", e);
    	}
	}

	@Override
	public List<OfertaEmpleoVO> listaOfertaEmpleos(long idEmpresa) throws BusinessException {
		List<OfertaEmpleoVO> list = new ArrayList<OfertaEmpleoVO>();
		try {
			list = ofertaEmpleoFacade.ofertaEmpleosList(idEmpresa);
		}catch(PersistenceException e) {
    		logger.error(e);
    		throw new BusinessException("No se pudo obtener la lista de ofertas", e);
    	}
		return list;
	}	
	
	//Son las ofertas de otras bolsas separadas por origen
	public List<OfertaEmpleoVO> consultaOfertasEntidad(int idEntidad) throws SQLException {
    	
		int numVacantes = 100; // valor por defecto
		
		ParametroVO parametro = parametroFacade.findById(ID_PARAMETRO_NUM_VACANTES_RSS);
		if (parametro!=null) numVacantes = Utils.parseInt(parametro.getValor());

		List<OfertaEmpleoVO> ofertas = ofertaEmpleoFacade.obtenerOfertas(idEntidad, numVacantes); 

    	return ofertas;
    }

	/**
	 * Consulta el total de ofertas publicadas en el Portal del empleo, incluyendo las del portal y las de bolsas externas
	 * @return
	 */
	public long consultaTotalOfertasPublicadas(){
		long total = 0;
		
		try{
			total = parametroFacade.consultaSumaParametrosOfertas();	
		}catch(Exception e){logger.error(e);}
		
		return total;
	}
	
	/**
	 * Consulta de la base de datos el total de ofertas activas y actualiza el parametro correspondiente,
	 * Los parametros de totales son sumados para obtener el total de todas las ofertas de las bolsas integrardas al Portal
	 */
	@Override
	public void actualizaTotalOfertasPublicadasSTPS(){
		try{
			long total = ofertaEmpleoFacade.consultaTotalPlazasOfertasActivas();

			parametroFacade.updateOrSaveParametro(ID_PARAMETRO_TOTAL_VACANTES_STPS, String.valueOf(total));			

		}catch(Exception e){
			logger.error(e);
		}
	}
	

	public List getCurrentPortalOffersCountByEntity(){
		ArrayList ofertas = new ArrayList();
		try{
			ofertas = (ArrayList) ofertaEmpleoFacade.consultaTotalOfertasActivasPortalPorEntidad();
		}catch(Exception e){
			logger.error(e);
		}				
		return ofertas;
	}
	
	public List<OfertasRecientesVO> getOfertasRecientes(int tipoConsulta){
		return ofertaEmpleoFacade.obtieneOfertasRecientes(tipoConsulta);
	}
	
	/*Utilizada en el reporte de Ofertas Vigentes Externas */
	public List<ParametroVO> getCurrentExternalOffersCountByEntity(){
		List<ParametroVO> ofertas = new ArrayList<ParametroVO>();
		try{
			//ofertas = parametroFacade.consultaSumaParametrosOfertasExternas();
			ofertas = parametroFacade.consultaParametrosOfertasExternas();
		}catch(Exception e){
			logger.error(e);
		}		
		return ofertas;
	}	

	/*Utilizada en el reporte de Ofertas Vigentes del Portal cruzando datos de entidad administrativa y grado de estudios */
	public List getCurrentPortalOffersCountByEntityStudy() {
		
		@SuppressWarnings("rawtypes")
		List ofertas = new ArrayList();
		try {
			ofertas = ofertaEmpleoFacade.consultaTotalOfertasActivasPortalPorEntidadEscolaridad();
		}
		catch(Exception e){
			logger.error(e);
		}		
		return ofertas;
	}

	/*Utilizada en el reporte de Ofertas Vigentes del Portal cruzando datos de entidad administrativa y grado de estudios */
	public List getCurrentPortalOffersCountByEntityExperience() {
		
		@SuppressWarnings("rawtypes")
		List ofertas = new ArrayList();
		try {
			ofertas = ofertaEmpleoFacade.consultaTotalOfertasActivasPortalPorEntidadExperiencia();
		}
		catch(Exception e){
			logger.error(e);
		}		
		return ofertas;
	}	
	
	public List getCurrentOfferEconomicActivity(){
		
		@SuppressWarnings("rawtypes")
		List ofertas = new ArrayList();
		try {
			ofertas = ofertaEmpleoFacade.consultaOfertasVigentesActividadEconomica();
		}
		catch(Exception e){
			logger.error(e);
		}		
		return ofertas;
	}
	
	
	public List getCurrentPortalOffersCountByAreaOcupacion(){
		
		@SuppressWarnings("rawtypes")
		List<CurrentOfferAreaOcupacionVO> ofertas = new ArrayList<CurrentOfferAreaOcupacionVO>();
		try {
			ofertas = ofertaEmpleoFacade.consultaOfertasVigentesAreaOcupacion();
		}
		catch(Exception e){
			logger.error(e);
		}		
		return ofertas;
	}

	public List<OfertaDetalleCortoVO> consultaOfertasDescripcionCorta(List<Long> idsOfertas){
		return ofertaEmpleoFacade.consultaOfertasDescripcionCorta(idsOfertas);
	}
	
	public Integer contarNumeroPlazasResultados(List<Long> indicesOfertas){
		return ofertaEmpleoFacade.contarNumeroPlazasResultados(indicesOfertas);
	}
	
}
