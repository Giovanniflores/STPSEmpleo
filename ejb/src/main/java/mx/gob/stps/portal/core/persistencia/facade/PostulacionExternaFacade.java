package mx.gob.stps.portal.core.persistencia.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import mx.gob.stps.portal.persistencia.entity.Candidato;
import mx.gob.stps.portal.persistencia.entity.OfertaCandidato;
import mx.gob.stps.portal.persistencia.entity.PostulacionExterna;
import mx.gob.stps.portal.persistencia.vo.PostulacionExternaVO;
import mx.gob.stps.portal.utils.Catalogos;

import org.apache.log4j.Logger;

@Stateless
public class PostulacionExternaFacade implements PostulacionExternaFacadeLocal{
	
	private static Logger logger = Logger.getLogger(PostulacionExternaFacade.class);
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public int guardarRegistro(PostulacionExternaVO postulacionExternaVo) {
		int idPostulacionExterna = -1;
		
		try{
			PostulacionExterna entity = getEntity(postulacionExternaVo);
			
			entityManager.persist(entity);
			idPostulacionExterna = (int) entity.getIdPostulacionExterna();
			
		} catch (RuntimeException re) {
			logger.error(re.toString());
			throw new PersistenceException(re);
		}catch(Exception e){
			logger.error(e);
			e.printStackTrace();
		}
		
		return idPostulacionExterna;
	}
	
	private PostulacionExterna getEntity(PostulacionExternaVO postulacionExternaVo){
		PostulacionExterna entity = new PostulacionExterna();
		entity.setIdPostulacionExterna(postulacionExternaVo.getIdPostulacionExterna());
		entity.setTituloOferta(postulacionExternaVo.getTituloOferta());
		entity.setSalario(postulacionExternaVo.getSalario());
		entity.setNombreEmpresa(postulacionExternaVo.getNombreEmpresa());
		entity.setContactoCorreoElectronico(postulacionExternaVo.getContactoCorreoElectronico());
		entity.setContactoCargo(postulacionExternaVo.getContactoCargo());
		entity.setEstatus(postulacionExternaVo.getEstatus());
		entity.setFuente(postulacionExternaVo.getFuente());
		entity.setFechaAlta(postulacionExternaVo.getFechaAlta());
		entity.setFechaContacto(postulacionExternaVo.getFechaContacto());
		entity.setFechaEntrevista(postulacionExternaVo.getFechaEntrevista());
		entity.setIdTipoTelefono(postulacionExternaVo.getIdTipoTelefono());
		entity.setAcceso(postulacionExternaVo.getAcceso());
		entity.setClave(postulacionExternaVo.getClave());
		entity.setTelefono(postulacionExternaVo.getTelefono());
		entity.setExtension(postulacionExternaVo.getExtension());
		entity.setIdCandidato(postulacionExternaVo.getIdCandidato());
		entity.setIdOficina(postulacionExternaVo.getIdOficina());
		entity.setMedioPortal(postulacionExternaVo.getMedioPortal());
		entity.setContactoEmpresa(postulacionExternaVo.getContactoEmpresa());
		entity.setFechaInicioColocacion(postulacionExternaVo.getFechaInicioColocacion());
		entity.setFechaInicioContratacion(postulacionExternaVo.getFechaInicioContratacion());
		entity.setFechaSeguimiento(postulacionExternaVo.getFechaSeguimiento());
		entity.setEstatusEntrevista(postulacionExternaVo.getEstatusEntrevista());
		entity.setIdMotivoNoContratacion(postulacionExternaVo.getIdMotivoNoContratacion());
		entity.setOtroMotivo(postulacionExternaVo.getOtroMotivo());
		entity.setIdUsuario(postulacionExternaVo.getIdUsuario());
		return entity;
	}
	
	private PostulacionExternaVO getPostulacionExternaVO(PostulacionExterna entity){
		PostulacionExternaVO vo = new PostulacionExternaVO();
		vo.setIdPostulacionExterna(entity.getIdPostulacionExterna());
		vo.setIdTipoTelefono(entity.getIdTipoTelefono());
    	vo.setAcceso(entity.getAcceso());
    	vo.setClave(entity.getClave());
    	vo.setContactoCargo(entity.getContactoCargo());
    	vo.setContactoCorreoElectronico(entity.getContactoCorreoElectronico());
    	vo.setContactoEmpresa(entity.getContactoEmpresa());
    	vo.setEstatus(entity.getEstatus());
    	vo.setExtension(entity.getExtension());
    	vo.setFechaAlta(entity.getFechaAlta());
    	vo.setFechaContacto(entity.getFechaContacto());
    	vo.setFechaEntrevista(entity.getFechaEntrevista());
    	vo.setFuente(entity.getFuente());
    	vo.setIdCandidato(entity.getIdCandidato());
    	vo.setIdOficina(entity.getIdOficina());
    	vo.setMedioPortal(entity.getMedioPortal());
    	vo.setNombreEmpresa(entity.getNombreEmpresa());
    	vo.setSalario(entity.getSalario());
    	vo.setTelefono(entity.getTelefono());
    	vo.setTituloOferta(entity.getTituloOferta());
    	vo.setEstatusEntrevista(entity.getEstatusEntrevista());
    	vo.setFechaInicioColocacion(entity.getFechaInicioColocacion());
    	vo.setFechaInicioContratacion(entity.getFechaInicioContratacion());
    	vo.setFechaSeguimiento(entity.getFechaSeguimiento());
    	vo.setIdMotivoNoContratacion(entity.getIdMotivoNoContratacion());
    	vo.setOtroMotivo(entity.getOtroMotivo());
    	vo.setIdPostulacionExterna(entity.getIdPostulacionExterna());
    	vo.setIdUsuario(entity.getIdUsuario());
		return vo;
	}

	public long obtenerIdPostulacionExterna(){
		
		long idPostulacion = -1;
		
		StringBuffer sqlString = new StringBuffer();
		
		sqlString.append("SELECT SEQ_POSTULACION_EXTERNA.NEXTVAL from DUAL");
		
		Query query = entityManager.createNativeQuery(sqlString.toString());
		
		try{
			Object result = query.getSingleResult();

			if(result!= null)				
				idPostulacion = Long.parseLong(String.valueOf(result));
			
		} catch (NoResultException re) {	
			re.printStackTrace(); logger.error(re);
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}		
		
		return idPostulacion;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PostulacionExterna> obtenerPostulacionesCandidato(Long idCandidato) {
		List<PostulacionExterna> postulaciones = new ArrayList<PostulacionExterna>();
		List<Object> data = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();
		query.append(" select p from PostulacionExterna p where p.idCandidato = :idCandidato ");
		query.append(" and p.estatus in (").append(Catalogos.ESTATUS_POSTULACION_EXTERNA.EN_PROCESO.getIdOpcion()).append(",").append(Catalogos.ESTATUS_POSTULACION_EXTERNA.REGISTRADO.getIdOpcion()).append(") ");
		query.append(" order by p.idPostulacionExterna desc");
		TypedQuery<PostulacionExterna> q  = entityManager.createQuery(query.toString(), PostulacionExterna.class);
		q.setParameter("idCandidato", idCandidato);
		try{
			postulaciones = q.getResultList();
		}catch(RuntimeException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return postulaciones;
	}

	@Override
	public boolean darSeguimientoPostulacionExternaContratado(PostulacionExterna postulacion, List<PostulacionExterna> postulaciones) {
		boolean actualizado = false;
		try{
			entityManager.merge(postulacion);
			actualizado = true;
			for(PostulacionExterna p : postulaciones){
				entityManager.merge(p);
			}
		}catch(RuntimeException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return actualizado;
	}

	@Override
	public boolean darSeguimientoPostulacion(PostulacionExterna postulacion) {
		boolean actualizado = false;
		try{
			entityManager.merge(postulacion);
			actualizado = true;
		}catch(RuntimeException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return actualizado;
	}

	@Override
	public boolean actualizaEstatusPPCCandidato(Long idCandidato) {
		boolean actualizado = false;
		Candidato entity = entityManager.find(Candidato.class, idCandidato);
		entity.setPpcEstatus(Catalogos.ESTATUS.FUERA_PPC.getIdOpcion());
		entity.setPpcIdMotivoFuera(Catalogos.MOTIVOS_FUERA_PPC.CANDIDATO_COLOCADO_EN_OFERTA_EXTERNA_AL_SNE.getIdOpcion());
		try{
			entityManager.merge(entity);
			actualizado = true;
		}catch(RuntimeException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return actualizado;
	}

	@Override
	public boolean actualizaEstatusOfertasSNE(OfertaCandidato oferta ) {
		boolean actualizadas = false;
		try{
			entityManager.merge(oferta);
			entityManager.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
		return actualizadas;
	}

	@Override
	public List<OfertaCandidato> encuentraPostulacionesSNE(Long idCandidato) {
		List<OfertaCandidato> ofertas = new ArrayList<OfertaCandidato>();
		StringBuilder query = new StringBuilder();
		query.append(" select o from OfertaCandidato o where o.idCandidato = :idCandidato");
		query.append(" and o.estatus in(").append(Catalogos.ESTATUS.POSTULADO.getIdOpcion()).append(",").append(Catalogos.ESTATUS.EN_PROCESO.getIdOpcion()).append(") ");
		TypedQuery<OfertaCandidato> q = entityManager.createQuery(query.toString(), OfertaCandidato.class);
		q.setParameter("idCandidato", idCandidato);
		try{
			ofertas = q.getResultList();
		}catch(RuntimeException e){
			e.printStackTrace();
		}
		return ofertas;
	}

	@Override
	public List<PostulacionExterna> obtenerPostulacionContratado(
			Long idCandidato) {
		List<PostulacionExterna> postulaciones = new ArrayList<PostulacionExterna>();
		List<Object> data = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();
		query.append(" select p from PostulacionExterna p where p.idCandidato = :idCandidato ");
		query.append(" and p.estatus =").append(Catalogos.ESTATUS_POSTULACION_EXTERNA.CONTRATADO.getIdOpcion());
		TypedQuery<PostulacionExterna> q  = entityManager.createQuery(query.toString(), PostulacionExterna.class);
		q.setParameter("idCandidato", idCandidato);
		try{
			postulaciones = q.getResultList();
		}catch(RuntimeException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return postulaciones;
	}
	
	

}
