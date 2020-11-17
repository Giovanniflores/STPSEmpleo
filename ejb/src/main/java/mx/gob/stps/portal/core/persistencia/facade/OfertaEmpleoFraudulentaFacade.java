package mx.gob.stps.portal.core.persistencia.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaIdiomaVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaRequisitoVO;
import mx.gob.stps.portal.core.oferta.registro.vo.OfertaCarreraEspecialidadVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoFraudulentaVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaPrestacionVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaUbicacionVO;
import mx.gob.stps.portal.core.persistencia.entity.OfertaCarreraFraudulenta;
import mx.gob.stps.portal.core.persistencia.entity.OfertaEmpleoFraudulenta;
import mx.gob.stps.portal.core.persistencia.entity.OfertaIdiomaFraudulenta;
import mx.gob.stps.portal.core.persistencia.entity.OfertaPrestacionFraudulenta;
import mx.gob.stps.portal.core.persistencia.entity.OfertaRequisitoFraudulenta;
import mx.gob.stps.portal.core.persistencia.entity.OfertaUbicacionFraudulenta;

@Stateless
public class OfertaEmpleoFraudulentaFacade implements OfertaEmpleoFraudulentaFacadeLocal {

	@PersistenceContext
	private EntityManager entityManager;

	public long save(OfertaEmpleoFraudulentaVO vo) throws PersistenceException {
		try{
			OfertaEmpleoFraudulenta entity = createOfertaEmpleoFraudulenta(vo);
			entityManager.persist(entity);

			return entity.getIdEmpresa();
		} catch (RuntimeException re) {throw new PersistenceException(re);}
	}

	public long save(OfertaEmpleoVO oferta) throws PersistenceException {
		try{
			OfertaEmpleoFraudulenta entity = createOfertaEmpleoFraudulenta(oferta);
			entityManager.persist(entity);

			return entity.getIdEmpresa();
		} catch (RuntimeException re) {throw new PersistenceException(re);}
	}
	
	private OfertaEmpleoFraudulenta createOfertaEmpleoFraudulenta(OfertaEmpleoVO vo){
		OfertaEmpleoFraudulenta entity = new OfertaEmpleoFraudulenta();

		entity.setIdOfertaEmpleo(vo.getIdOfertaEmpleo());
		entity.setIdEmpresa(vo.getIdEmpresa());
		entity.setTituloOferta(vo.getTituloOferta());
		entity.setIdAreaLaboral(vo.getIdAreaLaboral());
		entity.setIdOcupacion(vo.getIdOcupacion());
		entity.setFunciones(vo.getFunciones());
		entity.setDiasLaborales(vo.getDiasLaborales());
		entity.setHoraEntrada(vo.getHoraEntrada());
		entity.setHoraSalida(vo.getHoraSalida());
		entity.setRolarTurno(vo.getRolarTurno());
		entity.setEmpresaOfrece(vo.getEmpresaOfrece());
		entity.setSalario(vo.getSalario());
		entity.setIdTipoContrato(vo.getIdTipoContrato());
		entity.setIdJerarquia(vo.getIdJerarquia());
		entity.setNumeroPlazas(vo.getNumeroPlazas());
		entity.setLimitePostulantes(vo.getLimitePostulantes());
		entity.setIdDiscapacidad(vo.getIdDiscapacidad());
		entity.setIdCausaVacante(vo.getIdCausaVacante());
		entity.setFechaInicio(vo.getFechaInicio());
		entity.setFechaFin(vo.getFechaFin());
		entity.setDisponibilidadViajar(vo.getDisponibilidadViajar());
		entity.setDisponibilidadRadicar(vo.getDisponibilidadRadicar());
		entity.setIdNivelEstudio(vo.getIdNivelEstudio());
		entity.setIdSituacionAcademica(vo.getIdSituacionAcademica());
//		entity.setHabilidadGeneral(vo.getHabilidadGeneral());
		entity.setExperienciaAnios(vo.getExperienciaAnios());
		entity.setEdadRequisito(vo.getEdadRequisito());
		entity.setEdadMinima(vo.getEdadMinima());
		entity.setEdadMaxima(vo.getEdadMaxima());
		entity.setGenero(vo.getGenero());
		entity.setMapaUbicacion(vo.getMapaUbicacion());
		entity.setObservaciones(vo.getObservaciones());
		entity.setIdTerceraEmpresa(vo.getIdTerceraEmpresa());
		entity.setIdContacto(vo.getIdContacto());
		entity.setIdHorarioDe(vo.getIdHorarioDe());
		entity.setIdHorarioA(vo.getIdHorarioA());
		entity.setIdDuracionAproximada(vo.getIdDuracionAproximada());
		entity.setDiasEntrevista(vo.getDiasEntrevista());
		entity.setFuente(vo.getFuente());
		entity.setContactoTel(vo.getContactoTel());
		entity.setContactoCorreo(vo.getContactoCorreo());
		entity.setFechaAlta(vo.getFechaAlta());
		entity.setFechaModificacion(vo.getFechaModificacion());
		entity.setIdTipoEmpleo(vo.getIdTipoEmpleo());
		entity.setEstatus(vo.getEstatus());
		
		return entity;
	}
	
	private OfertaEmpleoFraudulenta createOfertaEmpleoFraudulenta(OfertaEmpleoFraudulentaVO vo){
		OfertaEmpleoFraudulenta entity = new OfertaEmpleoFraudulenta();

		entity.setIdOfertaEmpleo(vo.getIdOfertaEmpleo());
		entity.setIdEmpresa(vo.getIdEmpresa());
		entity.setTituloOferta(vo.getTituloOferta());
		entity.setIdAreaLaboral(vo.getIdAreaLaboral());
		entity.setIdOcupacion(vo.getIdOcupacion());
		entity.setFunciones(vo.getFunciones());
		entity.setDiasLaborales(vo.getDiasLaborales());
		entity.setHoraEntrada(vo.getHoraEntrada());
		entity.setHoraSalida(vo.getHoraSalida());
		entity.setRolarTurno(vo.getRolarTurno());
		entity.setEmpresaOfrece(vo.getEmpresaOfrece());
		entity.setSalario(vo.getSalario());
		entity.setIdTipoContrato(vo.getIdTipoContrato());
		entity.setIdJerarquia(vo.getIdJerarquia());
		entity.setNumeroPlazas(vo.getNumeroPlazas());
		entity.setLimitePostulantes(vo.getLimitePostulantes());
		entity.setIdDiscapacidad(vo.getIdDiscapacidad());
		entity.setIdCausaVacante(vo.getIdCausaVacante());
		entity.setFechaInicio(vo.getFechaInicio());
		entity.setFechaFin(vo.getFechaFin());
		entity.setDisponibilidadViajar(vo.getDisponibilidadViajar());
		entity.setDisponibilidadRadicar(vo.getDisponibilidadRadicar());
		entity.setIdNivelEstudio(vo.getIdNivelEstudio());
		entity.setIdSituacionAcademica(vo.getIdSituacionAcademica());
		entity.setHabilidadGeneral(vo.getHabilidadGeneral());
		entity.setExperienciaAnios(vo.getExperienciaAnios());
		entity.setEdadRequisito(vo.getEdadRequisito());
		entity.setEdadMinima(vo.getEdadMinima());
		entity.setEdadMaxima(vo.getEdadMaxima());
		entity.setGenero(vo.getGenero());
		entity.setMapaUbicacion(vo.getMapaUbicacion());
		entity.setObservaciones(vo.getObservaciones());
		entity.setIdTerceraEmpresa(vo.getIdTerceraEmpresa());
		entity.setIdContacto(vo.getIdContacto());
		entity.setIdHorarioDe(vo.getIdHorarioDe());
		entity.setIdHorarioA(vo.getIdHorarioA());
		entity.setIdDuracionAproximada(vo.getIdDuracionAproximada());
		entity.setDiasEntrevista(vo.getDiasEntrevista());
		entity.setFuente(vo.getFuente());
		entity.setContactoTel(vo.getContactoTel());
		entity.setContactoCorreo(vo.getContactoCorreo());
		entity.setFechaAlta(vo.getFechaAlta());
		entity.setFechaModificacion(vo.getFechaModificacion());
		entity.setIdTipoEmpleo(vo.getIdTipoEmpleo());
		entity.setEstatus(vo.getEstatus());
		
		return entity;
	}
	
	public void saveOfertaCarreraFraudulenta(OfertaCarreraEspecialidadVO vo, long idOfertaEmpleo) throws PersistenceException{
		
		OfertaCarreraFraudulenta entity = createOfertaCarreraFraudulenta(vo, idOfertaEmpleo);
		entityManager.persist(entity);
		
	}
	
	private OfertaCarreraFraudulenta createOfertaCarreraFraudulenta(OfertaCarreraEspecialidadVO vo, long idOfertaEmpleo){
		
		OfertaCarreraFraudulenta entity = new OfertaCarreraFraudulenta();
		
		if (vo != null){
			entity.setIdOfertaEmpleo(idOfertaEmpleo);
			entity.setIdOfertaCarrera(vo.getIdRegistro());
			entity.setIdCarreraEspecialidad(vo.getId());
			if (vo.getFechaAlta() != null)
				entity.setFechaAlta(vo.getFechaAlta());
			entity.setPrincipal(vo.getPrincipal());
		}
		
		return entity;
	}
	
	public void saveOfertaOfertaIdiomaFraudulenta(OfertaIdiomaVO vo) throws PersistenceException{
		
		OfertaIdiomaFraudulenta entity = createOfertaIdiomaFraudulenta(vo);
		entityManager.persist(entity);
		
	}
	
	private OfertaIdiomaFraudulenta createOfertaIdiomaFraudulenta(OfertaIdiomaVO vo){		
		OfertaIdiomaFraudulenta entity = new OfertaIdiomaFraudulenta();		
		if (vo != null){
			entity.setIdOfertaIdioma(vo.getIdOfertaIdioma());
			entity.setIdOfertaEmpleo(vo.getIdOfertaEmpleo());			
			entity.setIdIdioma(vo.getIdIdioma());
			entity.setIdCertificacion(vo.getIdCertificacion());
			entity.setIdDominio(vo.getIdDominio());
			entity.setFechaAlta(vo.getFechaAlta());
			entity.setPrincipal(vo.getPrincipal());
		}
		return entity;
	}
	
	public void saveOfertaPrestacionFraudulenta(OfertaPrestacionVO vo) throws PersistenceException {
		OfertaPrestacionFraudulenta entity = createOfertaPrestacionFraudulenta(vo);
		entityManager.persist(entity);
	}

	private OfertaPrestacionFraudulenta createOfertaPrestacionFraudulenta(OfertaPrestacionVO vo){		
		OfertaPrestacionFraudulenta entity = new OfertaPrestacionFraudulenta();		
		if (vo != null){
			entity.setIdOfertaPrestacion(vo.getIdOfertaPrestacion());
			entity.setIdOfertaEmpleo(vo.getIdOfertaEmpleo());
			entity.setIdPrestacion(vo.getIdPrestacion());
			if (vo.getFechaAlta() != null)
				entity.setFechaAlta(vo.getFechaAlta());	
		}
		return entity;
	}
	
	public void saveOfertaRequisitoFraudulenta(OfertaRequisitoVO vo) throws PersistenceException {
		OfertaRequisitoFraudulenta entity = createOfertaRequisitosFraudulenta(vo);
		entityManager.persist(entity);
	}
	
	private OfertaRequisitoFraudulenta createOfertaRequisitosFraudulenta(OfertaRequisitoVO vo){		
		OfertaRequisitoFraudulenta entity = new OfertaRequisitoFraudulenta();		
		if (vo != null){
			entity.setDescripcion(vo.getDescripcion());
			if (vo.getFechaAlta() != null)
				entity.setFechaAlta(vo.getFechaAlta());
			entity.setIdDominio(vo.getIdDominio());
			entity.setIdExperiencia(vo.getIdExperiencia());
			entity.setIdOfertaEmpleo(vo.getIdOfertaEmpleo());
			entity.setIdOfertaRequisito(vo.getIdOfertaRequisito());
			entity.setIdTipoRequisito(vo.getIdTipoRequisito());
			entity.setPrincipal(vo.getPrincipal());
		}
		return entity;
	}

	public void saveOfertaUbicacionFraudulenta(OfertaUbicacionVO vo) throws PersistenceException {
		OfertaUbicacionFraudulenta entity = createOfertaUbicacionFraudulenta(vo);
		entityManager.persist(entity);
	}
	
	private OfertaUbicacionFraudulenta createOfertaUbicacionFraudulenta(OfertaUbicacionVO vo){		
		OfertaUbicacionFraudulenta entity = new OfertaUbicacionFraudulenta();
		if (vo != null){
			entity.setIdOfertaUbicacion(vo.getIdOfertaUbicacion());				
			entity.setIdOfertaEmpleo(vo.getIdOfertaEmpleo());				
			entity.setIdEntidad(vo.getIdEntidad());				
			entity.setIdMunicipio(vo.getIdMunicipio());				
			entity.setFechaAlta(vo.getFechaAlta());
		}
		return entity;
	}
	
}
