package mx.gob.stps.portal.core.persistencia.facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import mx.gob.stps.portal.core.candidate.vo.ConocimientoComputacionVO;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.persistencia.entity.ConocimientoComputacion;

import org.apache.log4j.Logger;

@Stateless
public class ConocimientoComputacionFacade implements ConocimientoComputacionFacadeLocal {

	private static Logger logger = Logger.getLogger(ConocimientoComputacionFacade.class);

	@PersistenceContext
	private EntityManager entityManager;

	public long registraConocimientosComputacion(long idPropietario,
												 int idTipoPropietario, int procesadorTxt, int hojaCalculo,
												 int internet, int redesSociales, String otros) {

		ConocimientoComputacion entity = new ConocimientoComputacion();
		entity.setIdPropietario(idPropietario);
		entity.setIdTipoPropietario(Utils.toLong(idTipoPropietario));
		entity.setProcesadorTxt(Utils.toLong(procesadorTxt));
		entity.setHojaCalculo(Utils.toLong(hojaCalculo));
		entity.setInternet(Utils.toLong(internet));
		entity.setRedesSociales(Utils.toLong(redesSociales));
		entity.setOtros(otros);

		entityManager.persist(entity);

		long idConocimientoComputacion = entity.getIdConocimientoComputacion();
		return idConocimientoComputacion;
	}

	public ConocimientoComputacionVO consultaConocimientosComputacion(long idPropietario, int idTipoPropietario) {
		ConocimientoComputacionVO conocimientos = null;

		try {
			String jpql = "select c from ConocimientoComputacion c where c.idPropietario = :idPropietario and c.idTipoPropietario = :idTipoPropietario";

			Query query = entityManager.createQuery(jpql);
			query.setParameter("idPropietario", idPropietario);
			query.setParameter("idTipoPropietario", idTipoPropietario);

			ConocimientoComputacion entity = (ConocimientoComputacion) query.getSingleResult();

			if (entity != null) {
				conocimientos = new ConocimientoComputacionVO();
				conocimientos.setIdConocimientoComputacion(entity.getIdConocimientoComputacion());
				conocimientos.setIdPropietario(entity.getIdPropietario());
				conocimientos.setIdTipoPropietario(entity.getIdTipoPropietario());
				conocimientos.setProcesadorTxt(Utils.toInt(entity.getProcesadorTxt()));
				conocimientos.setHojaCalculo(Utils.toInt(entity.getHojaCalculo()));
				conocimientos.setInternet(Utils.toInt(entity.getInternet()));
				conocimientos.setRedesSociales(Utils.toInt(entity.getRedesSociales()));
				conocimientos.setOtros(entity.getOtros());
			}
		} catch (NoResultException re) {
			// No se obtubieron registros
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenceException(e);
		}

		return conocimientos;
	}

	public void borrarConocimientosComputacion(long idPropietario, int idTipoPropietario) {
		String jpql = "delete from ConocimientoComputacion c where c.idPropietario = :idPropietario and c.idTipoPropietario = :idTipoPropietario";

		Query query = entityManager.createQuery(jpql);
		query.setParameter("idPropietario", idPropietario);
		query.setParameter("idTipoPropietario", idTipoPropietario);
		
		query.executeUpdate();
	}
	
	@Override
	public long actualizarConocimientosComputacion(long idConocimientoComputacion, long idPropietario, int idTipoPropietario,
												   int procesadorTxt, int hojaCalculo, int internet,
												   int redesSociales, String otros) throws PersistenceException {
		long result = -1;
		
		try {
			if (idConocimientoComputacion > 0) {
				ConocimientoComputacion entity = entityManager.find(ConocimientoComputacion.class, idConocimientoComputacion);
				
				if (null != entity) {
					entity.setHojaCalculo(Utils.toLong(hojaCalculo));
					entity.setInternet(Utils.toLong(internet));
					entity.setOtros(otros);
					entity.setProcesadorTxt(Utils.toLong(procesadorTxt));
					entity.setRedesSociales(Utils.toLong(redesSociales));
					entityManager.merge(entity);
					result = entity.getIdConocimientoComputacion();
				}
			} else {
				result = registraConocimientosComputacion(idPropietario, idTipoPropietario, procesadorTxt, hojaCalculo, internet, redesSociales, otros);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result = -1;
		}

		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public ConocimientoComputacionVO findConocimientosComputacion(long idPropietario, int idTipoPropietario) throws PersistenceException {
		ConocimientoComputacionVO vo = null;

		String jpql = "SELECT c FROM ConocimientoComputacion c WHERE c.idPropietario = :idPropietario and c.idTipoPropietario = :idTipoPropietario";

		try {
			Query query = entityManager.createQuery(jpql);
			query.setParameter("idPropietario", idPropietario);
			query.setParameter("idTipoPropietario", idTipoPropietario);

			List<Object> object = query.getResultList();

			if (object!=null && !object.isEmpty()){
				ConocimientoComputacion conocimientos = (ConocimientoComputacion)object.get(0);
				vo = getConocimientoComputacionVO(conocimientos);
			}

		} catch (NoResultException re) {
			logger.info("No se localizaron conocimientos en computacion para el propietario :"+ idPropietario +" idTipoPropietario : "+ idTipoPropietario);
		} catch (Exception e) {
			e.printStackTrace(); logger.error(e);
		}

		return vo;
	}

	private ConocimientoComputacionVO getConocimientoComputacionVO(ConocimientoComputacion entity) {
		ConocimientoComputacionVO vo = new ConocimientoComputacionVO();
		if (null != entity) {
			vo.setIdConocimientoComputacion(entity.getIdConocimientoComputacion());
			vo.setIdPropietario(entity.getIdPropietario());
			vo.setIdTipoPropietario(entity.getIdTipoPropietario());
			vo.setHojaCalculo(Utils.toInt(entity.getHojaCalculo()));
			vo.setInternet(Utils.toInt(entity.getInternet()));
			vo.setOtros(entity.getOtros());
			vo.setProcesadorTxt(Utils.toInt(entity.getProcesadorTxt()));
			vo.setRedesSociales(Utils.toInt(entity.getRedesSociales()));
		}
		return vo;
	}
}
