package mx.gob.stps.portal.core.persistencia.facade;

import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.persistencia.entity.Candidato;
import mx.gob.stps.portal.persistencia.entity.Solicitante;
import mx.gob.stps.portal.persistencia.vo.SolicitanteVO;
import mx.gob.stps.portal.utils.Catalogos.ESTATUS;

@Stateless
public class SolicitanteFacade implements SolicitanteFacadeLocal {
	
	private static Logger logger = Logger.getLogger(SolicitanteFacade.class);

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Long save(SolicitanteVO solicitanteVO) throws PersistenceException {
		Long idSolicitante = null;
		Candidato candidato = null;
		try{
			Solicitante entitySolicitante = getSolicitante(solicitanteVO);
			
			if(solicitanteVO.getIdCandidato() != null)
				 candidato = entityManager.find(Candidato.class, solicitanteVO.getIdCandidato());
			if(candidato != null)
				entitySolicitante.setCandidato(candidato);
			else{
				throw new Exception("Error al persistir solicitante, el candidato es nulo");
			}
	
			entityManager.persist(entitySolicitante);
			entityManager.flush();
			idSolicitante = entitySolicitante.getIdSolicitante();
				
			
		} catch (Exception e) {
			logger.error("Error al save : SolicitanteFacade");
			throw new PersistenceException(e);
		}
		
		return idSolicitante;
	}
	
	@Override
	public void actualizarCorreoElectronico(Solicitante entity) throws PersistenceException {
	
		try{
			entityManager.merge(entity);
		}catch(PersistenceException pe){
			logger.error("Error al actualizar correo solicitante: SolicitanteFacade");
			throw pe;
		}
		
	}
	
	@Override
	public Solicitante findByIdCandidato(Long idCandidato) throws PersistenceException {
		Solicitante solicitante = null;
		String query = "select s from Solicitante s where s.candidato.idCandidato=:idCandidato";
		try{
			Query consulta  = entityManager.createQuery(query);
			consulta.setParameter("idCandidato", idCandidato);
			solicitante = (Solicitante)consulta.getSingleResult();
		}catch(NoResultException nex){
			solicitante = new Solicitante();
			logger.error("No resultado al buscar solicitante por idCandidato: SolicitanteFacade");
		}catch(NonUniqueResultException nuex){
			solicitante = null;
			logger.error("No resultado unico al buscar solicitante por idCandidato: SolicitanteFacade");
			nuex.printStackTrace();
		}
		catch(PersistenceException pex){
			logger.error("Error al buscar solicitante por idCandidato: SolicitanteFacade");
			throw pex;
		}
		
		return solicitante;
	}
	
	public Solicitante getSolicitante(SolicitanteVO solicitanteVO) {
		Solicitante solicitante = new Solicitante();
		
		if(!solicitanteVO.getApellido1().equals(""))
			solicitante.setApellido1(solicitanteVO.getApellido1().toUpperCase());
		if(solicitanteVO.getApellido2() != null)
			solicitante.setApellido2(solicitanteVO.getApellido2().toUpperCase());
		
		solicitante.setCorreoElectronico(solicitanteVO.getCorreoElectronico());
		solicitante.setCurp(solicitanteVO.getCurp().toUpperCase());
		if(solicitanteVO.getFechaNacimiento() != null)
			solicitante.setEdad(Utils.obtenEdad(solicitanteVO.getFechaNacimiento()));
		else
			solicitante.setEdad(new Integer(0));
		
		solicitante.setEstatus(ESTATUS.ACTIVO.getIdOpcion());
		solicitante.setFechaNacimiento(solicitanteVO.getFechaNacimiento());
		solicitante.setFechaRegistro(new Date());
		solicitante.setFechaUltimaModificacion(new Date());
		solicitante.setGenero(solicitanteVO.getGenero());
		solicitante.setIdEmpresa(solicitanteVO.getIdEmpresa());
		solicitante.setIdEntidadNacimiento(solicitanteVO.getIdEntidadNacimiento());
		solicitante.setIdFuente(solicitanteVO.getIdFuente());
		solicitante.setIdMotivoNoValidacion(solicitanteVO.getIdMotivoNoValidacion());
		solicitante.setIdOficina(solicitanteVO.getIdOficina());
		if(!solicitanteVO.getNombre().equals(""))
			solicitante.setNombre(solicitanteVO.getNombre().toUpperCase());
		solicitante.setPersonaUltimaModificacion(solicitanteVO.getPersonaUltimaModificacion());
		solicitante.setValidadoRenapo(solicitanteVO.getValidadoRenapo());
		
		
		return solicitante;
		
	}
}
