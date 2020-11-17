package mx.gob.stps.portal.core.persistencia.facade;

import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.persistencia.entity.Telefono;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;

/**
 * Implementacion de los metodos de persistencia para registros de la entidad "Telefono"
 *
 * @author haydee.vertti
 *
 */
@Stateless
public class TelefonoFacade implements TelefonoFacadeLocal {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Registra un número telefónico
	 * @param vo TelefonoVO
	 * @return identificador del telefono
	 * @throws PersistenceException Lanzada en caso de error durante el proceso de persistencia
	 */
	public long save(TelefonoVO vo) throws PersistenceException{
		try {
			Telefono entity = getEntity(vo);
			entityManager.persist(entity);
			vo.setIdTelefono(entity.getIdTelefono());
			return entity.getIdTelefono();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}
	}

	/**
	 * Elimina un número telefónico
	 * @param vo TelefonoVO
	 * @throws PersistenceException Lanzada en caso de error durante el proceso de persistencia
	 */	
	public void delete(TelefonoVO vo) throws PersistenceException{
		try {
			Telefono entity = entityManager.find(Telefono.class, vo.getIdTelefono());
			entityManager.remove(entity);
			entityManager.flush();
		} catch (RuntimeException re) {
			throw new PersistenceException(re);
		}
	}

	public void borrarTelefonos(long idPropietario, long idTipoPropietario){
		String jpql = "delete from Telefono t where t.idPropietario = :idPropietario and t.idTipoPropietario = :idTipoPropietario";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("idPropietario", idPropietario);
		query.setParameter("idTipoPropietario", idTipoPropietario);

		query.executeUpdate();
	}

	/**
	 * Method 'findById'
	 * Regresa un objeto TelefonoVO con los datos correspondientes al teléfono
	 * cuyo identificador se proporciona
	 *
	 * @param id
	 * @return TelefonoVO
	 */		
	public TelefonoVO findById(long id) throws PersistenceException{
		try {
			Telefono instance = entityManager.find(Telefono.class, id);
			return getTelefonoVO(instance);
		} catch (RuntimeException re) {
			throw new PersistenceException(re);
		}		
	}
	
	/**
	 * Method 'update'
	 *
	 * @param vo
	 */
	public void update(TelefonoVO vo) throws PersistenceException{
		try {
			String telefono = Utils.cut(vo.getTelefono(), 8);
			Telefono entity = entityManager.find(Telefono.class, vo.getIdTelefono());
			entity.setAcceso(vo.getAcceso());
			entity.setClave(vo.getClave());
			entity.setExtension(vo.getExtension());
			entity.setIdTipoTelefono(Utils.toInt(vo.getIdTipoTelefono()));
			entity.setPrincipal(vo.getPrincipal());
			entity.setTelefono(telefono);
			entityManager.merge(entity);
		} catch (RuntimeException re) {
			re.printStackTrace(); throw new PersistenceException(re);
		}
	}


	/**
	 * Method 'getTelefonosPropietario'
	 * Obtiene un listado de Objetos de tipo TelefonoVO pertenecientes a un propietario
	 *
	 * @param idPropietario
	 * @param idTipoPropietario
	 * @return List<TelefonoVO>
	 */
	public List<TelefonoVO> getTelefonosPropietario(long idPropietario, long idTipoPropietario) throws PersistenceException{
		List<TelefonoVO> lstTelefonos = new ArrayList<TelefonoVO>();
		try{
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT c FROM Telefono c WHERE c.idPropietario=:idProp AND c.idTipoPropietario=:idTipoProp ORDER BY c.idTelefono");
			Query query =  entityManager.createQuery(sb.toString());
			query.setParameter("idProp", idPropietario);
			query.setParameter("idTipoProp", idTipoPropietario);
			@SuppressWarnings("unchecked")
			List<Object> result1 = query.getResultList();
			for(Object resultElement : result1){
				TelefonoVO vo = getTelefonoVO((Telefono)resultElement);
				lstTelefonos.add(vo);
			}

		} catch (NoResultException re) {
			// No se localizaron registros
			re.printStackTrace();
		} catch (RuntimeException re) {
			re.printStackTrace(); throw new PersistenceException(re);
		}
		return lstTelefonos;
	}

	/**
	 * Method 'getTelefonosPropietario'
	 * Obtiene un listado de Objetos de tipo TelefonoVO pertenecientes a un propietario y a un tipo de telefono especÃ­fico
	 *
	 * @param idPropietario
	 * @param idTipoPropietario
	 * @param idTipoTelefono
	 * @return List<TelefonoVO>
	 */
	public List<TelefonoVO> getTelefonosPropietario(long idPropietario, long idTipoPropietario, long idTipoTelefono) throws PersistenceException{
		List<TelefonoVO> lstTelefonos = new ArrayList<TelefonoVO>();
		String findAllByPhoneType = "SELECT c FROM Telefono c WHERE c.idPropietario=:idProp AND c.idTipoPropietario=:idTipoProp AND c.idTipoTelefono=:idTipoTel ORDER BY c.idTelefono";
		try{
			Query query =  entityManager.createQuery(findAllByPhoneType);
			query.setParameter("idProp", idPropietario);
			query.setParameter("idTipoProp", idTipoPropietario);
			query.setParameter("idTipoTel", idTipoTelefono);
			@SuppressWarnings("unchecked")
			List<Object> result1 = query.getResultList();
			for(Object resultElement : result1){
				TelefonoVO vo = getTelefonoVO((Telefono)resultElement);
				lstTelefonos.add(vo);
			}

		} catch (NoResultException re) {
			// No se localizaron registros
			re.printStackTrace();
		} catch (RuntimeException re) {
			re.printStackTrace(); throw new PersistenceException(re);
		}
		return lstTelefonos;
	}

	public long getIdTelefonoPrincipal(long idPropietario, long idTipoPropietario) throws PersistenceException{
		long idTelefono = -1;
		String findAllByOwner = "SELECT c FROM Telefono c WHERE c.idPropietario=:idProp AND c.idTipoPropietario=:idTipoProp ORDER BY c.idTelefono";
		try{
			Query query =  entityManager.createQuery(findAllByOwner);
			query.setParameter("idProp", idPropietario);
			query.setParameter("idTipoProp", idTipoPropietario);
			@SuppressWarnings("unchecked")
			List<Object> result1 = query.getResultList();
			for(Object resultElement : result1){
				TelefonoVO vo = getTelefonoVO((Telefono)resultElement);
				if(vo.getPrincipal()==Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion()){
					idTelefono = vo.getIdTelefono();
					break;
				}
			}

		} catch (NoResultException re) {
			// No se localizaron registros
			re.printStackTrace();
		} catch (RuntimeException re) {
			re.printStackTrace(); throw new PersistenceException(re);
		}
		return idTelefono;
	}
	
	public TelefonoVO getTelefonoPrincipal(long idPropietario, long idTipoPropietario) throws PersistenceException {
		TelefonoVO phone = null;
		String findAllByOwner = "SELECT c FROM Telefono c WHERE c.idPropietario=:idProp AND c.idTipoPropietario=:idTipoProp ORDER BY c.idTelefono";
		try{
			Query query =  entityManager.createQuery(findAllByOwner);
			query.setParameter("idProp", idPropietario);
			query.setParameter("idTipoProp", idTipoPropietario);
			@SuppressWarnings("unchecked")
			List<Object> result1 = query.getResultList();
			for(Object resultElement : result1) {
				TelefonoVO vo = getTelefonoVO((Telefono)resultElement);
				if(vo.getPrincipal()==Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion()){
					phone = vo;
					break;
				}
			}
		} catch (NoResultException re) {
			// No se localizaron registros
			re.printStackTrace();
		} catch (RuntimeException re) {
			re.printStackTrace(); throw new PersistenceException(re);
		}
		return phone;
	}

	/**
	 * Method 'getTelefonoVO'
	 * Coloca los datos de un objeto Telefono en un VisualObject correspondiente
	 * @param telefono
	 * @return TelefonoVO
	 */
	private TelefonoVO getTelefonoVO(Telefono telefono){
		TelefonoVO vo = new TelefonoVO();
		vo.setAcceso(telefono.getAcceso());
		vo.setClave(telefono.getClave());	
		vo.setExtension(Utils.validarExtensionTelefonica(telefono.getExtension()));
		vo.setFechaAlta(telefono.getFechaAlta());
		vo.setIdPropietario(telefono.getIdPropietario());
		vo.setIdTelefono(telefono.getIdTelefono());
		vo.setIdTipoPropietario(telefono.getIdTipoPropietario());
		vo.setIdTipoTelefono(telefono.getIdTipoTelefono());
		vo.setTelefono(telefono.getTelefono());
		vo.setPrincipal(telefono.getPrincipal());
		return vo;
	}
	
	/**
	 * Method 'getEntity'
	 * Coloca los datos de un VisualObject en un objeto de tipo Telefono
	 * @param vo
	 * @return Telefono
	 */
	private Telefono getEntity(TelefonoVO vo){
		String telefono = Utils.cut(vo.getTelefono(), 8);

		Telefono entity = new Telefono();
		entity.setAcceso(vo.getAcceso());
		entity.setClave(vo.getClave());
		entity.setExtension(vo.getExtension());
		entity.setFechaAlta(vo.getFechaAlta());
		entity.setIdPropietario(vo.getIdPropietario());
		//entity.setIdTelefono(vo.getIdTelefono());
		entity.setIdTipoPropietario(Utils.toInt(vo.getIdTipoPropietario()));
		entity.setIdTipoTelefono(Utils.toInt(vo.getIdTipoTelefono()));
		entity.setTelefono(telefono);
		entity.setPrincipal(vo.getPrincipal());
		return entity;
	}



	public void deleteAll(long idPropietario, long idTipoPropietario,long principal) throws PersistenceException{

		StringBuilder consulta = new StringBuilder();
			consulta.append( " delete from Telefono t " );
			consulta.append( " where t.idPropietario = :idPropietario " );
			consulta.append( " and t.idTipoPropietario = :idTipoPropietario " );
			consulta.append( " and t.principal = :principal" );

		Query query = entityManager.createQuery(consulta.toString());
			query.setParameter("idPropietario", idPropietario);
			query.setParameter("idTipoPropietario", idTipoPropietario);
			query.setParameter("principal", principal);

	        query.executeUpdate();
	}

}
