package mx.gob.stps.portal.core.ws.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.PersistenceException;

import java.util.ArrayList;
import java.util.List;

import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;

import mx.gob.stps.portal.core.persistencia.facade.CatalogoOpcionFacadeLocal;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import org.apache.log4j.Logger;

@WebService
@Stateless(name = "ConsultaCatalogosPortalWS", mappedName = "ConsultaCatalogosPortalWS")
public class ConsultaCatalogosPortalWS {

	Logger logger = Logger.getLogger(ConsultaCatalogosPortalWS.class);
	
	@EJB
	private CatalogoOpcionFacadeLocal catalogoOpcionFacade;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@WebMethod
	public List<CatalogoOpcionVO> getOpcionesCatalogo(long idCatalogo) throws BusinessException{
		
		logger.info("Se ha invocado el servicio web getOpcionesCatalogo");
		logger.info("idCatalogo="+idCatalogo);
		
		if (idCatalogo == 0){
			logger.error("El parámetro idCatalogo no está informado");
			throw new IllegalArgumentException("Parámetro idCatalogo es requerido para realizar la consulta");
		}
		
		List<CatalogoOpcionVO> listaOpciones;
		
		try {
			listaOpciones = catalogoOpcionFacade.getOpcionesCatalogo(idCatalogo);
			
			if (listaOpciones == null || listaOpciones.isEmpty()){
				logger.info("La consulta al catálogo idCatalogo="+idCatalogo+" no ha devuelto opciones");
				listaOpciones = new ArrayList<CatalogoOpcionVO>();
			}
			
		} catch (PersistenceException pe){
			logger.error("Error al invocar getOpcionesCatalogo(idCatalogo="+idCatalogo+")");
			pe.printStackTrace();
			throw new BusinessException("Se ha producido un error al consultar las opciones del catálogo");
		}

		return listaOpciones;		
	} 
}
