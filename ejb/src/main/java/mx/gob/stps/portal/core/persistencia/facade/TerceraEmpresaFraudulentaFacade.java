package mx.gob.stps.portal.core.persistencia.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.empresa.vo.TerceraEmpresaVO;
import mx.gob.stps.portal.core.persistencia.entity.TerceraEmpresaFraudulenta;

import org.apache.log4j.Logger;

//@Stateless
public class TerceraEmpresaFraudulentaFacade implements TerceraEmpresaFraudulentaFacadeLocal {

	private static Logger logger = Logger.getLogger(TerceraEmpresaFraudulentaFacade.class);
	
	//@PersistenceContext
	//private EntityManager entityManager;
	
	/*public long save(TerceraEmpresaVO vo) throws PersistenceException {
		try{
			TerceraEmpresaFraudulenta entity = getEntity(vo);
			entityManager.persist(entity);
			return entity.getIdTerceraEmpresa();
		} catch (Exception e) {
			logger.error(e); e.printStackTrace();
			throw new PersistenceException(e);
		}			
	}*/
	
	/*private TerceraEmpresaFraudulenta getEntity(TerceraEmpresaVO vo){
		TerceraEmpresaFraudulenta entity = new TerceraEmpresaFraudulenta();
		
		entity.setIdTerceraEmpresa(vo.getIdTerceraEmpresa());
		entity.setIdEmpresa(vo.getIdEmpresa());
		entity.setRfc(vo.getRfc());
		entity.setIdTipoPersona(vo.getIdTipoPersona());
		entity.setNombre(vo.getNombre());
		entity.setApellido1(vo.getApellido1());
		entity.setApellido2(vo.getApellido2());
		entity.setRazonSocial(vo.getRazonSocial());
		entity.setDescripcion(vo.getDescripcion());
		entity.setContactoEmpresa(vo.getContactoEmpresa());
		entity.setIdTipoEmpresa(vo.getIdTipoEmpresa());
		entity.setIdActividadEconomica(vo.getIdActividadEconomica());
		entity.setNumeroEmpleados(vo.getNumeroEmpleados());
		entity.setFechaAlta(vo.getFechaAlta());
		entity.setEstatus(vo.getEstatus());
		entity.setFechaUltimaActualizacion(vo.getFechaUltimaActualizacion());
		entity.setCorreoElectronico(vo.getCorreoElectronico());
		//COMENTAR EN PRODUCCION 
		entity.setNombreComercial(vo.getNombreComercial());
		
		return entity;
	}*/
	
}
