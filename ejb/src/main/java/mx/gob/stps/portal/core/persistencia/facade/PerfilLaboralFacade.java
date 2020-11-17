package mx.gob.stps.portal.core.persistencia.facade;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import mx.gob.stps.portal.core.candidate.vo.CurriculumVo;
import mx.gob.stps.portal.core.candidate.vo.PerfilLaboralVo;
import mx.gob.stps.portal.core.infra.utils.Constantes.ACEPTACION_TERMINOS_CURRICULUM;
import mx.gob.stps.portal.persistencia.entity.OtroMedio;
import mx.gob.stps.portal.persistencia.entity.PerfilLaboral;

/**
 * Concentra los accesos a la persistencia de PerfilLaboral
 * @author jose.jimenez
 */
@Stateless
public class PerfilLaboralFacade implements PerfilLaboralFacadeLocal {
	private static Logger logger = Logger.getLogger(PerfilLaboralFacade.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/* (non JavaDoc)
	 * @see mx.gob.stps.portal.core.persistencia.facade.PerfilLaboralFacadeLocal#save(mx.gob.stps.portal.core.candidate.vo.PerfilLaboralVo)
	 */
	@Override
	public long save(PerfilLaboralVo vo) throws PersistenceException {
		
		long idCandidato = 0;
		PerfilLaboral entity = getPerfilLaboral(vo);

		try {
			entityManager.persist(entity);
			
			idCandidato = entity.getIdCandidato();

		} catch (Exception e) {
			throw new PersistenceException(e);
		}
		
		return idCandidato;
	}

	public boolean tienePerfilLaboral(long idCandidato) {
		boolean tiene = false;
		try {
			PerfilLaboral entity = entityManager.find(PerfilLaboral.class, idCandidato);

			tiene = entity!=null;
			
		} catch (NoResultException re) {	
			// No se recupera el registro
		} catch (Exception e) {
			e.printStackTrace(); logger.error(e);
		}
		
		return tiene;
	}
	
	public void actualizaRegistroPerfilLaboral(PerfilLaboralVo vo) throws PersistenceException {
		
		try {
			PerfilLaboral entity = entityManager.find(PerfilLaboral.class, vo.getIdCandidato());

			if (entity==null) return;
			
			//entity.setIdCandidato(vo.getIdCandidato());
			entity.setContactoCorreo(vo.getContactoCorreo());
			entity.setContactoTelefono(vo.getContactoTelefono());
			entity.setHorarioContactoDe(vo.getHorarioContactoDe());
			entity.setHorarioContactoA(vo.getHorarioContactoA());
			entity.setIdRecibeOferta(vo.getIdRecibeOferta());
			entity.setEmpleadoActualmente(vo.getEmpleadoActualmente());
			entity.setIdRazonBusqueda(vo.getIdRazonBusqueda());
			entity.setDescripcionOtroMotivoBusq(vo.getDescripcionOtroMotivoBusq());
			entity.setInicioBusqueda(vo.getInicioBusqueda());
			entity.setIdExperienciaCompu(vo.getIdExperienciaCompu());
			entity.setIdDominioCompu(vo.getIdDominioCompu());
			entity.setIdExperienciaOffice(vo.getIdExperienciaOffice());
			entity.setIdDominioOffice(vo.getIdDominioOffice());
			entity.setIdExperienciaInternet(vo.getIdExperienciaInternet());
			entity.setIdDominioInternet(vo.getIdDominioInternet());
			entity.setIdExperienciaTotal(vo.getIdExperienciaTotal());
			entity.setExperiencia(vo.getExperiencia());
			entity.setDisponibilidadViajar(vo.getDisponibilidadViajar());
			entity.setDisponibilidadRadicar(vo.getDisponibilidadRadicar());
			entity.setComputacionBasica(vo.getComputacionBasica());
			entity.setComputacionAvanzada(vo.getComputacionAvanzada());
			entity.setSinEstudios(vo.getSinEstudios());
			entity.setSinExperiencia(vo.getSinExperiencia());
			//entity.setFechaAlta(vo.getFechaAlta());

			/** Datos que se limpian **/
			entity.setIdSectorMayorExpr(new Long(0));
			entity.setPuestoMayorExpr(null);
			entity.setIdAreaLaboralMayorExpr(new Long(0));
			entity.setIdOcupacionMayorExpr(new Long(0));
			entity.setFotografia(null);
			entity.setUrlVideoc(null);
			entity.setTerminosVideoc(0);
			entity.setDescripcionVideoc(null);
			entity.setEstatusVideoc(0);
			
			entityManager.merge(entity);

		} catch (NoResultException re) {	
			re.printStackTrace(); logger.error(re);
		} catch (Exception e) {
			e.printStackTrace(); logger.error(e);
			throw new PersistenceException(e);
		}
	}

	/* (non JavaDoc)
	 * @see mx.gob.stps.portal.core.persistencia.facade.PerfilLaboralFacadeLocal#find(long)
	 */
	@Override
	public PerfilLaboralVo find(long idCandidato) throws PersistenceException {
		
		PerfilLaboralVo vo = null;
		
		try {
			PerfilLaboral entity = entityManager.find(PerfilLaboral.class, idCandidato);
			vo = getPerfilLaboralVo(entity);
		} catch (NoResultException re) {	
			// Sin registro
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
		
		return vo;
	}
	
	/* (non JavaDoc)
	 * @see mx.gob.stps.portal.core.persistencia.facade.PerfilLaboralFacadeLocal#actualizaCurriculum(long, CurriculumVo)
	 */
	@Override
	public void actualizarCurriculum(long idCandidato, CurriculumVo vo) {
		
		try {
			PerfilLaboral entity = entityManager.find(PerfilLaboral.class, idCandidato);
			
			if (entity == null || (entity != null && entity.getIdCandidato() == 0)) {
				throw new NullPointerException();
			} else {
				entity.setUrlVideoc(vo.getVideoURL());
				entity.setDescripcionVideoc(vo.getVideoDescription());
				entity.setTerminosVideoc(ACEPTACION_TERMINOS_CURRICULUM.SI.getIdOpcion());
				entity.setEstatusVideoc(vo.getEstatusVideoc());
				
				entityManager.merge(entity);
			}
			
		} catch (NullPointerException npe) {
			throw new PersistenceException("No existe registro", npe);
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException("Al guardar info del curriculum en PerfilLaboral", re);
		}
	}

	/* (non JavaDoc)
	 * @see mx.gob.stps.portal.core.persistencia.facade.PerfilLaboralFacadeLocal#guardarFoto(long, byte[])
	 */
	@Override
	public void guardarFoto(long idCandidato, byte[] fotografia) {
		
		PerfilLaboral entity = null;
		try {
			entity = entityManager.find(PerfilLaboral.class, idCandidato);
			
			if (entity == null || (entity != null && entity.getIdCandidato() == 0)) {
				throw new NullPointerException();
			} else {
				entity.setFotografia(fotografia);
			}
			
		} catch (NullPointerException npe) {
			throw new PersistenceException("No existe registro", npe);
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException("Al almacenar la foto de PerfilLaboral", re);
		}
		
	}
	
	@Override
	public void actualizarEstatus(long idCandidato, int estatusVideoc) throws PersistenceException {		
		try {
			PerfilLaboral entity = entityManager.find(PerfilLaboral.class, idCandidato);
			entity.setEstatusVideoc(estatusVideoc);
		} catch (RuntimeException re) {
			throw new PersistenceException(re);
		}
	}
	
	/**
	 * Genera un PerfilLaboral a partir de un PerfilLaboralVo
	 * @param vo contiene los datos a transferir al objeto entidad
	 * @return
	 */
	private PerfilLaboral getPerfilLaboral(PerfilLaboralVo vo) {
		
		PerfilLaboral entity = new PerfilLaboral();
		entity.setIdCandidato(vo.getIdCandidato());
		entity.setContactoCorreo(vo.getContactoCorreo());
		entity.setContactoTelefono(vo.getContactoTelefono());
		entity.setHorarioContactoDe(vo.getHorarioContactoDe());
		entity.setHorarioContactoA(vo.getHorarioContactoA());
		entity.setEmpleadoActualmente(vo.getEmpleadoActualmente());
		entity.setIdRazonBusqueda(vo.getIdRazonBusqueda());
		entity.setDescripcionOtroMotivoBusq(vo.getDescripcionOtroMotivoBusq());
		entity.setInicioBusqueda(vo.getInicioBusqueda());
		entity.setIdExperienciaCompu(vo.getIdExperienciaCompu());
		entity.setIdDominioCompu(vo.getIdDominioCompu());
		entity.setIdExperienciaOffice(vo.getIdExperienciaOffice());
		entity.setIdDominioOffice(vo.getIdDominioOffice());
		entity.setIdExperienciaInternet(vo.getIdExperienciaInternet());
		entity.setIdDominioInternet(vo.getIdDominioInternet());
		entity.setIdExperienciaTotal(vo.getIdExperienciaTotal());
		entity.setExperiencia(vo.getExperiencia());
		entity.setIdRecibeOferta(vo.getIdRecibeOferta());
		entity.setIdSectorMayorExpr(vo.getIdSectorMayorExpr());
		entity.setIdAreaLaboralMayorExpr(vo.getIdAreaLaboralMayorExpr());
		entity.setIdOcupacionMayorExpr(vo.getIdOcupacionMayorExpr());
		entity.setDisponibilidadViajar(vo.getDisponibilidadViajar());
		entity.setDisponibilidadRadicar(vo.getDisponibilidadRadicar());
		entity.setComputacionBasica(vo.getComputacionBasica());
		entity.setComputacionAvanzada(vo.getComputacionAvanzada());
		entity.setFotografia(vo.getFotografia());
		entity.setUrlVideoc(vo.getUrlVideoc());
		entity.setPuestoMayorExpr(vo.getPuestoMayorExpr());
		entity.setTerminosVideoc(vo.getTerminosVideoc());
		entity.setDescripcionVideoc(vo.getDescripcionVideoc());
		entity.setFechaAlta(vo.getFechaAlta());

		entity.setSinEstudios(vo.getSinEstudios());
		entity.setSinExperiencia(vo.getSinExperiencia());
		
		entity.setEstatusVideoc(vo.getEstatusVideoc());

		return entity;
	}
	
	/**
	 * Genera un {@code PerfilLaboralVo} a partir de los datos de un {@code PerfilLaboral}
	 * @param entity objeto entidad del que se extraer&aacute; la informaci&oacute;n
	 * @return un objeto {@code PerfilLaboralVo} con los datos del objeto entidad recibido
	 */
	private PerfilLaboralVo getPerfilLaboralVo(PerfilLaboral entity) {
		PerfilLaboralVo vo = new PerfilLaboralVo();
		if (null != entity) {
			vo.setIdRazonBusqueda(entity.getIdRazonBusqueda());
			vo.setDescripcionOtroMotivoBusq(entity.getDescripcionOtroMotivoBusq());
			vo.setEmpleadoActualmente(entity.getEmpleadoActualmente());
			vo.setIdCandidato(entity.getIdCandidato());
			vo.setInicioBusqueda(entity.getInicioBusqueda());
			vo.setFechaAlta(entity.getFechaAlta());
			if(entity.getContactoCorreo() != null)
				vo.setContactoCorreo(entity.getContactoCorreo());
			if(entity.getContactoTelefono() != null)
				vo.setContactoTelefono(entity.getContactoTelefono());
			if(entity.getHorarioContactoDe() != null)
				vo.setHorarioContactoDe(entity.getHorarioContactoDe());
			if(entity.getHorarioContactoA() != null)
				vo.setHorarioContactoA(entity.getHorarioContactoA());
			if(entity.getIdRecibeOferta() != null)
				vo.setIdRecibeOferta(entity.getIdRecibeOferta());
			if(entity.getIdExperienciaCompu() != null)
				vo.setIdExperienciaCompu(entity.getIdExperienciaCompu());
			if(entity.getIdDominioCompu() != null)
				vo.setIdDominioCompu(entity.getIdDominioCompu());
			if(entity.getIdExperienciaOffice() != null)
				entity.getIdExperienciaOffice();
			if(entity.getIdDominioOffice() != null)
				vo.setIdDominioOffice(entity.getIdDominioOffice());
			if(entity.getIdExperienciaInternet() != null)
				vo.setIdExperienciaInternet(entity.getIdExperienciaInternet());
			if(entity.getIdDominioInternet() != null)
				vo.setIdDominioInternet(entity.getIdDominioInternet());
			if(entity.getIdExperienciaTotal() != null)
				vo.setIdExperienciaTotal(entity.getIdExperienciaTotal());
			if(entity.getIdSectorMayorExpr() != null)
				vo.setIdSectorMayorExpr(entity.getIdSectorMayorExpr());
			if(entity.getPuestoMayorExpr() != null)
				vo.setPuestoMayorExpr(entity.getPuestoMayorExpr()); 
			if(entity.getIdAreaLaboralMayorExpr() != null)
				vo.setIdAreaLaboralMayorExpr(entity.getIdAreaLaboralMayorExpr());
			if(entity.getIdOcupacionMayorExpr() != null)
				vo.setIdOcupacionMayorExpr(entity.getIdOcupacionMayorExpr());
			if(entity.getDisponibilidadViajar() != null)
				vo.setDisponibilidadViajar(entity.getDisponibilidadViajar());
			if(entity.getDisponibilidadRadicar() != null)
				vo.setDisponibilidadRadicar(entity.getDisponibilidadRadicar());
			if(entity.getComputacionBasica() != null)
				vo.setComputacionBasica(entity.getComputacionBasica());
			if(entity.getComputacionAvanzada() != null)
				vo.setComputacionAvanzada(entity.getComputacionAvanzada());
			if(entity.getFotografia() != null)
				vo.setFotografia(entity.getFotografia());
			if(entity.getUrlVideoc() != null)
				vo.setUrlVideoc(entity.getUrlVideoc());
			if(entity.getTerminosVideoc() != null)
				vo.setTerminosVideoc(entity.getTerminosVideoc());
			if(entity.getDescripcionVideoc() != null)
				vo.setDescripcionVideoc(entity.getDescripcionVideoc());
			if(entity.getSinEstudios() != null)
				vo.setSinEstudios(entity.getSinEstudios());
			if(entity.getEstatusVideoc() != null)
				vo.setEstatusVideoc(entity.getEstatusVideoc());
			if(entity.getExperiencia() != null)
				vo.setExperiencia(entity.getExperiencia());
			if(entity.getLicenciaId() != null)
				vo.setLicenciaId(entity.getLicenciaId());
		}
		return vo;
	}

	@Override
	public void actualizarIdRecibeOferta(long idCandidato,long idRecibeOferta) {
		PerfilLaboral entity = entityManager.find(PerfilLaboral.class,idCandidato);
		entity.setIdRecibeOferta(idRecibeOferta);
		entityManager.merge(entity);
	}

	public long registraOtroMedio(long idCandidato, long idMedioBusqueda, Date fechaAlta){
		OtroMedio otroMedio = new OtroMedio();
		otroMedio.setIdCandidato(idCandidato);
		otroMedio.setIdMedioBusqueda(idMedioBusqueda);
		otroMedio.setFechaAlta(fechaAlta);

		entityManager.persist(otroMedio);
		
		long idOtroMedio = otroMedio.getIdOtroMedio();
		return idOtroMedio;
	}

	public void eliminaOtrosMedios(long idCandidato){
		String jpql = "delete from OtroMedio o where o.idCandidato = :idCandidato";
		
		Query query = entityManager.createQuery(jpql);
		query.setParameter("idCandidato", idCandidato);
		
		query.executeUpdate();
	}

	@Override
	public byte[] consultaFotografia(int idPropietario) {
		byte[] foto = null;
	        StringBuilder queryString = new StringBuilder();
	        queryString.append("SELECT FOTOGRAFIA FROM PERFIL_LABORAL ");
	        queryString.append(" WHERE ID_CANDIDATO = "+ idPropietario);

	        Query query = entityManager.createNativeQuery(queryString.toString());
	        
			Object result = query.getSingleResult();			

			if (result!=null)
				foto = (byte[])result;

			return foto;
	}
	
}
