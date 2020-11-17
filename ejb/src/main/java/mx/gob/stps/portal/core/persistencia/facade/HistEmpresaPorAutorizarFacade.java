package mx.gob.stps.portal.core.persistencia.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.empresa.vo.HistEmpresaPorAutorizarVO;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.persistencia.entity.HistEmpresaPorAutorizar;

import org.apache.log4j.Logger;

/**
 * Implementacion de los metodos de persistencia para registros de la entidad "HistEmpresaPorAutorizar"
 * 
 * @author haydee.vertti
 *
 */
@Stateless
public class HistEmpresaPorAutorizarFacade implements HistEmpresaPorAutorizarFacadeLocal{

	private static Logger logger = Logger.getLogger(HistEmpresaPorAutorizarFacade.class);

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Respalda una empresa por autorizar en el historico
	 * @param vo HistEmpresaPorAutorizarVO
	 * @return Identificador de la empresa por autorizar en el historico
	 * @throws PersistenceException Lanzada en caso de error durante el proceso de persistencia
	 */	
	public long save(HistEmpresaPorAutorizarVO vo) throws PersistenceException {
		try {
			HistEmpresaPorAutorizar entity = getEntity(vo);
			entityManager.persist(entity);
			return entity.getIdEmpresa();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}			
	}

	/**
	 * Method 'findById'
	 * Regresa un objeto HistEmpresaPorAutorizarVO con los datos correspondientes a
	 * la empresa por  autorizar en el historico,cuyo identificador se proporciona
	 * 
	 * @param id
	 * @return EmpresaPorAutorizarVO
	 */	
	public HistEmpresaPorAutorizarVO findById(long id)
			throws PersistenceException {
		try {
			HistEmpresaPorAutorizar instance = entityManager.find(HistEmpresaPorAutorizar.class, id);
			return getHistEmpresaPorAutorizarVO(instance);
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}		
	}
	
	/**
	 * Method 'getHistEmpresaPorAutorizarVO'
	 * Coloca los datos de un objeto HistEmpresaPorAutorizar en un VisualObject correspondiente
	 * @param empresa
	 * @return HistEmpresaPorAutorizarVO
	 */		
	private HistEmpresaPorAutorizarVO getHistEmpresaPorAutorizarVO(HistEmpresaPorAutorizar empresa){
		HistEmpresaPorAutorizarVO vo = new HistEmpresaPorAutorizarVO();
		vo.setAceptacionTerminos(Utils.toInt(empresa.getAceptacionTerminos()));
		vo.setApellido1(empresa.getApellido1());
		vo.setApellido2(empresa.getApellido2());
		vo.setAseguraDatos(Utils.toInt(empresa.getAseguraDatos()));
		vo.setConfidencial(Utils.toInt(empresa.getConfidencial()));
		vo.setContactoEmpresa(empresa.getContactoEmpresa());
		vo.setCorreoElectronico(empresa.getCorreoElectronico());
		vo.setDescripcion(empresa.getDescripcion());
		vo.setEstatus(Utils.toInt(empresa.getEstatus()));
		vo.setFechaActa(empresa.getFechaActa());
		vo.setFechaAlta(empresa.getFechaAlta());
		vo.setFechaNacimiento(empresa.getFechaNacimiento());
		vo.setFechaUltimaActualizacion(empresa.getFechaUltimaActualizacion());
		vo.setIdActividadEconomica(empresa.getIdActividadEconomica());
		vo.setIdEmpresa(empresa.getIdEmpresa());
		vo.setIdMedio(empresa.getIdMedio());
		vo.setIdPortalEmpleo(empresa.getIdPortalEmpleo());
		vo.setIdTipoEmpresa(empresa.getIdTipoEmpresa());
		vo.setIdTipoPersona(empresa.getIdTipoPersona());
		//vo.setIdUsuario(empresa.getIdUsuario());
		vo.setNombre(empresa.getNombre());
		vo.setNumeroEmpleados(Utils.toInt(empresa.getNumeroEmpleados()));
		vo.setPaginaWeb(empresa.getPaginaWeb());
		vo.setRazonSocial(empresa.getRazonSocial());
		vo.setRfc(empresa.getRfc());
		return vo;
	}

	/**
	 * Method 'getEntity'
	 * Coloca los datos de un VisualObject en un objeto de tipo HistEmpresaPorAutorizar
	 * @param vo
	 * @return HistEmpresaPorAutorizar
	 */		
	private HistEmpresaPorAutorizar getEntity(HistEmpresaPorAutorizarVO vo){
		HistEmpresaPorAutorizar entity = new HistEmpresaPorAutorizar();
		entity.setAceptacionTerminos(Utils.toLong(vo.getAceptacionTerminos()));
		entity.setApellido1(vo.getApellido1());
		entity.setApellido2(vo.getApellido2());
		entity.setAseguraDatos(Utils.toLong(vo.getAseguraDatos()));
		entity.setConfidencial(Utils.toLong(vo.getConfidencial()));
		entity.setContactoEmpresa(vo.getContactoEmpresa());
		entity.setCorreoElectronico(vo.getCorreoElectronico());
		entity.setDescripcion(vo.getDescripcion());
		entity.setEstatus(Utils.toLong(vo.getEstatus()));
		entity.setFechaActa(vo.getFechaActa());
		entity.setFechaAlta(vo.getFechaAlta());
		entity.setFechaNacimiento(vo.getFechaNacimiento());
		entity.setFechaUltimaActualizacion(vo.getFechaUltimaActualizacion());
		entity.setIdActividadEconomica(vo.getIdActividadEconomica());
		entity.setIdEmpresa(vo.getIdEmpresa());
		entity.setIdMedio(vo.getIdMedio());
		entity.setIdPortalEmpleo(vo.getIdPortalEmpleo());
		entity.setIdTipoEmpresa(vo.getIdTipoEmpresa());
		entity.setIdTipoPersona(vo.getIdTipoPersona());
		//entity.setIdUsuario(vo.getIdUsuario());
		entity.setNombre(vo.getNombre());
		entity.setNumeroEmpleados(Utils.toLong(vo.getNumeroEmpleados()));
		entity.setPaginaWeb(vo.getPaginaWeb());
		entity.setRazonSocial(vo.getRazonSocial());
		entity.setRfc(vo.getRfc());		
		return entity;
	}
	
}
