package mx.gob.stps.portal.core.persistencia.facade;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_AREA_LABORAL;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_DOMINIO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_ESTATUS;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_EXPERIENCIA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_GRADO_ESTUDIOS;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_IDIOMAS;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_OCUPACION;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_SUBSECTOR;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_TIPO_CONTRATO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_TIPO_EMPLEO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.OFERTARECIENTE;
import static mx.gob.stps.portal.core.infra.utils.Constantes.OFERTADESTACADA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.OFERTAPET;
import static mx.gob.stps.portal.core.infra.utils.Constantes.OFERTAGENDARMERIA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.SALARIO_MINIMO_DOCTORADO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.SALARIO_MINIMO_MAESTRIA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.SALARIO_MINIMO_LICENCIATURA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.GRADO_ESTUDIOS_LICENCIATURA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.GRADO_ESTUDIOS_MAESTRIA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.GRADO_ESTUDIOS_DOCTORADO;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import mx.gob.stps.portal.core.candidate.vo.ConocimientoHabilidadVO;
import mx.gob.stps.portal.core.candidate.vo.ExpectativaLaboralVO;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.CONTACTO_CORREO;
import mx.gob.stps.portal.core.infra.utils.Constantes.CONTACTO_TELEFONO;
import mx.gob.stps.portal.core.infra.utils.Constantes.EDAD_REQUISITO;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.MULTIREGISTRO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorPerfilVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaEmpleoJB;
import mx.gob.stps.portal.core.oferta.vo.CurrentOfferAreaOcupacionVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaDetalleCortoVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.core.ws.ofertas.vo.OfertaEmpleoOutVO;
import mx.gob.stps.portal.core.ws.vo.OfertaTotalesVO;
import mx.gob.stps.portal.persistencia.entity.OfertaEmpleo;
import mx.gob.stps.portal.persistencia.entity.OfertaFuncion;
import mx.gob.stps.portal.persistencia.vo.OfertaFuncionVO;
import mx.gob.stps.portal.persistencia.vo.OfertasRecientesVO;
import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.utils.ConstantesGenerales;

import org.apache.log4j.Logger;

@Stateless
public class OfertaEmpleoFacade implements OfertaEmpleoFacadeLocal {

	@PersistenceContext
	private EntityManager entityManager;
	
	private static Logger logger = Logger.getLogger(OfertaEmpleoFacade.class);
	
	private static String findOffersByCompany = "SELECT c FROM OfertaEmpleo c WHERE c.idEmpresa=:idEmpresa";
	private static String findOffersByTerceraEmpresaEstatus = "SELECT o FROM OfertaEmpleo o WHERE o.idTerceraEmpresa = :idTerEmpresa AND o.estatus = :estatus";
	private static String findOffersByEmpresaEstatus = "SELECT o FROM OfertaEmpleo o WHERE o.idEmpresa = :idEmpresa AND o.estatus = :estatus";
	private static String sumaPlazasPorOfertas = "select sum(o.numeroPlazas) from OfertaEmpleo o where o.estatus = :estatus";

	@Override
	public Long save(OfertaEmpleoVO vo) throws PersistenceException {
		Long result;
		try {
			OfertaEmpleo entity = getNewEntity(vo);

			entityManager.persist(entity);
			result = entity.getIdOfertaEmpleo();
		}catch (RuntimeException re) {
			logger.error(re.toString());
			throw new PersistenceException(re);
		}
		
		return result;
	}
	public Long saveAndFlush(OfertaEmpleoVO vo) throws PersistenceException {
		try{
			Long result = save(vo);
			entityManager.flush();
			return result;
		}catch (PersistenceException re) {
			throw re;
		}catch (RuntimeException re) {
			logger.error(re.toString());
			throw new PersistenceException(re);
		}
	}

	@Override
	public mx.gob.stps.portal.persistencia.vo.OfertaEmpleoVO findOfertaEmpleoById(long idOfertaEmpleo) {
		mx.gob.stps.portal.persistencia.vo.OfertaEmpleoVO vo = null;
		try {
			OfertaEmpleo entity = entityManager.find(OfertaEmpleo.class, idOfertaEmpleo);
			if (entity!=null) {
				vo = convertOfertaEmpleoVO(entity);
			}
		} catch (NoResultException re) {
			logger.error("Registro de Oferta no localizado, idOfertaEmpleo : "+ idOfertaEmpleo);
		} catch (RuntimeException re) {
			throw new PersistenceException(re);
		}
		return vo;
	}
	
	@Override
	public OfertaEmpleoVO findById(long idOfertaEmpleo) throws PersistenceException {
		OfertaEmpleoVO vo = null;
		try {
			OfertaEmpleo entity = entityManager.find(OfertaEmpleo.class, idOfertaEmpleo);
			if (null != entity)
				vo = getOfertaEmpleoVO(entity);
		} catch (NoResultException re) {
			logger.error("Registro de Oferta no localizado, idOfertaEmpleo : "+ idOfertaEmpleo);
		} catch (RuntimeException re) {
			throw new PersistenceException(re);
		}
		return vo;
	}

	@Override
	public void update(OfertaEmpleoVO vo) throws PersistenceException {
		try {
			OfertaEmpleo entity = entityManager.find(OfertaEmpleo.class, vo.getIdOfertaEmpleo());
			updateEntity(entity, vo);

			entityManager.merge(entity);

		} catch (NoResultException ne) {
			logger.error("Registro de Oferta no localizado, idOfertaEmpleo : "+ vo.getIdOfertaEmpleo());
		} catch (RuntimeException re) {
			logger.error(re.toString());
			throw new PersistenceException(re);
		}
	}

	@Override
	public List<OfertaEmpleoVO> ofertaEmpleosList(long idEmpresa) throws PersistenceException {
		List<OfertaEmpleoVO> list = new ArrayList<OfertaEmpleoVO>();
		Query query = entityManager.createQuery(findOffersByCompany);
		query.setParameter("idEmpresa", idEmpresa);
		
		try{
			@SuppressWarnings("unchecked")
			List<Object> result = query.getResultList();
			for(Object resultElement : result){
				OfertaEmpleoVO vo = getOfertaEmpleoVO((OfertaEmpleo)resultElement);			
				list.add(vo);
			}
		} catch (NoResultException ne) {
			logger.error("Ofertas de la empresa no localizadas, idEmpresa : "+ idEmpresa);
		}
		return list;
	}
	
	@Override
	public void remove(long idOfertaEmpleo) throws PersistenceException {
		try {
			OfertaEmpleo entity = entityManager.find(OfertaEmpleo.class, idOfertaEmpleo);
			entityManager.remove(entity);
		}catch (RuntimeException re) {
			logger.error(re.toString());
			throw new PersistenceException(re);
		}
	}
	
	@Override
	public List<OfertaEmpleoVO> consultaOfertasEmpleoTerceraEmpresa(long idTerceraEmpresa, int estatus) throws PersistenceException {
		List<OfertaEmpleoVO> ofertas = new ArrayList<OfertaEmpleoVO>();

		try{
			Query query = entityManager.createQuery(findOffersByTerceraEmpresaEstatus);
			query.setParameter("idTerEmpresa", idTerceraEmpresa);
			query.setParameter("estatus", estatus);
			
			@SuppressWarnings("unchecked")
			List<Object> results = query.getResultList();
			for(Object row : results){
				OfertaEmpleoVO oferta = getOfertaEmpleoVO((OfertaEmpleo)row);
				ofertas.add(oferta);
			}

		} catch (NoResultException e) {
			logger.error("No se localizaron ofertas para la Tercera Empresa : "+ idTerceraEmpresa);
		} catch (Exception e) {
			throw new PersistenceException(e);
		}

		return ofertas;
	}	

	public List<OfertaEmpleoVO> findAllOfertasEmpleoTerceraEmpresa(long idTerceraEmpresa) throws PersistenceException {
		List<OfertaEmpleoVO> ofertas = new ArrayList<OfertaEmpleoVO>();

		try{
			Query query = entityManager.createQuery("SELECT o FROM OfertaEmpleo o WHERE o.idTerceraEmpresa = :idTerEmpresa");
			query.setParameter("idTerEmpresa", idTerceraEmpresa);

			@SuppressWarnings("unchecked")
			List<OfertaEmpleo> results = query.getResultList();

			for(OfertaEmpleo row : results){
				OfertaEmpleoVO oferta = getOfertaEmpleoVO(row);
				ofertas.add(oferta);
			}

		} catch (NoResultException e) {
			logger.error("No se localizaron ofertas para la Tercera Empresa : "+ idTerceraEmpresa);
		} catch (Exception e) {
			throw new PersistenceException(e);
		}

		return ofertas;
	}	

	public List<OfertaEmpleoVO> consultaOfertasEmpleo(long idEmpresa, int estatus) throws PersistenceException {
		List<OfertaEmpleoVO> ofertas = new ArrayList<OfertaEmpleoVO>();
		
		Query query = entityManager.createQuery(findOffersByEmpresaEstatus);
		query.setParameter("idEmpresa", idEmpresa);
		query.setParameter("estatus", estatus);
		
		@SuppressWarnings("unchecked")
		List<Object> results = query.getResultList();
		for(Object row : results){
			
			OfertaEmpleoVO oferta = getOfertaEmpleoVO((OfertaEmpleo)row);			
			
			ofertas.add(oferta);
		}

		return ofertas;
	}

	public List<OfertaEmpleoVO> consultaOfertasEmpleo(long idEmpresa, int estatus1, int estatus2, int estatus3, int estatus4) {
		List<OfertaEmpleoVO> ofertas = new ArrayList<OfertaEmpleoVO>();
		
		String select = "SELECT o FROM OfertaEmpleo o " +
				         "WHERE o.idEmpresa = :idEmpresa " +
		                  " AND (o.estatus = :estatus1 OR o.estatus = :estatus2 OR o.estatus = :estatus3 OR o.estatus = :estatus4)";

		Query query = entityManager.createQuery(select);
		
		query.setParameter("idEmpresa", idEmpresa);
		query.setParameter("estatus1", estatus1);
		query.setParameter("estatus2", estatus2);
		query.setParameter("estatus3", estatus3);
		query.setParameter("estatus4", estatus4);
		
		@SuppressWarnings("unchecked")
		List<OfertaEmpleo> results = query.getResultList();
		for(OfertaEmpleo row : results){
			OfertaEmpleoVO oferta = getOfertaEmpleoVO(row);
			ofertas.add(oferta);
		}

		return ofertas;
	}
	

	
	@Override
	public void actualizaEstatus(long idOfertaEmpleo, int estatus) throws PersistenceException {
		try {
	        String jpql = "UPDATE OfertaEmpleo AS o SET o.estatus = :estatus, o.fechaModificacion = :fechaModificacion WHERE o.idOfertaEmpleo = :idOfertaEmpleo";

	        Query query = entityManager.createQuery(jpql);
	        query.setParameter("estatus", estatus);
	        query.setParameter("fechaModificacion", new Date(), TemporalType.TIMESTAMP);
	        query.setParameter("idOfertaEmpleo", idOfertaEmpleo);

	        query.executeUpdate();

		} catch (NoResultException re) {
			logger.error("No se localizo el registro de la oferta de empleo : "+ idOfertaEmpleo);
		} catch (Exception re) {
			throw new PersistenceException(re);
		}
	}
	
	/**
	 * Consulta el total de ofertas activas en la base de STPS
	 * @return
	 * @throws PersistenceException
	 */
	public long consultaTotalPlazasOfertasActivas() throws PersistenceException {
		long total = 0;

		try{
			Query query = entityManager.createQuery(sumaPlazasPorOfertas);
			query.setParameter("estatus", ESTATUS.ACTIVO.getIdOpcion());

			Number count = (Number)query.getSingleResult();

			if (count!=null)
				total = count.longValue();
		}catch(Exception e){
			throw new PersistenceException(e);
		}

		return total;
	}
	
	private OfertaEmpleo getNewEntity(OfertaEmpleoVO vo) {
		OfertaEmpleo entity = new OfertaEmpleo();

		//entity.setIdOfertaEmpleo(vo.getIdOfertaEmpleo());
		entity.setIdEmpresa(vo.getIdEmpresa());
		
		entity.setContactoCorreo(vo.getContactoCorreo());
		entity.setContactoTel(vo.getContactoTel());
		entity.setContactoDomicilio(vo.getContactoDomicilio());
		entity.setDiasEntrevista(vo.getDiasEntrevista());
		entity.setDiasLaborales(vo.getDiasLaborales());
		entity.setDisponibilidadRadicar(vo.getDisponibilidadRadicar());
		entity.setDisponibilidadViajar(vo.getDisponibilidadViajar());
		entity.setEdadMaxima(vo.getEdadMaxima());
		entity.setEdadMinima(vo.getEdadMinima());
		entity.setEdadRequisito(vo.getEdadRequisito());
		entity.setEmpresaOfrece(vo.getEmpresaOfrece());
		entity.setEstatus(vo.getEstatus());
		entity.setExperienciaAnios(vo.getExperienciaAnios());
		entity.setFechaAlta(vo.getFechaAlta());
		entity.setFechaFin(vo.getFechaFin());
		entity.setFechaInicio(vo.getFechaInicio());
		entity.setFechaModificacion(vo.getFechaModificacion());
		entity.setFuente(vo.getFuente());
		entity.setFunciones(vo.getFunciones());
		entity.setGenero(vo.getGenero());
//		entity.setHabilidadGeneral(vo.getHabilidadGeneral());
		entity.setHoraEntrada(vo.getHoraEntrada());
		entity.setHoraSalida(vo.getHoraSalida());
		entity.setIdAreaLaboral(vo.getIdAreaLaboral());
		entity.setIdCausaVacante(vo.getIdCausaVacante());
		//entity.setIdContacto(vo.getIdContacto());
		entity.setIdDiscapacidad(vo.getIdDiscapacidad());
		entity.setIdDuracionAproximada(vo.getIdDuracionAproximada());
		//27/02/13
		//Se agrego la actividad economica 
		entity.setIdActividadEconomica(vo.getIdActividadEconomica());
		entity.setIdHorarioA(vo.getIdHorarioA());
		entity.setIdHorarioDe(vo.getIdHorarioDe());
		entity.setIdJerarquia(vo.getIdJerarquia());
		entity.setIdNivelEstudio(vo.getIdNivelEstudio());
		entity.setIdOcupacion(vo.getIdOcupacion());
		
		entity.setIdSituacionAcademica(vo.getIdSituacionAcademica());
		//entity.setIdTerceraEmpresa(vo.getIdTerceraEmpresa());
		entity.setIdTipoContrato(vo.getIdTipoContrato());
		entity.setIdTipoEmpleo(vo.getIdTipoEmpleo());
		entity.setLimitePostulantes(vo.getLimitePostulantes());
		entity.setMapaUbicacion(vo.getMapaUbicacion());
		entity.setNumeroPlazas(vo.getNumeroPlazas());
		if (vo.getObservaciones().length()<=2000)
			entity.setObservaciones(vo.getObservaciones());
		else
			entity.setObservaciones(vo.getObservaciones().substring(0,2000));
		entity.setRolarTurno(vo.getRolarTurno());
		entity.setSalario(vo.getSalario());
		entity.setTituloOferta(vo.getTituloOferta());
		entity.setNombreEmpresa(vo.getNombreEmpresa());
		
		if (vo.getNombreContacto() != null && !vo.getNombreContacto().isEmpty())
			entity.setNombreContacto(vo.getNombreContacto());
		
		if (vo.getCargoContacto() != null && !vo.getCargoContacto().isEmpty())
			entity.setCargoContacto(vo.getCargoContacto());
		
		if (vo.getCorreoElectronicoContacto() != null && !vo.getCorreoElectronicoContacto().isEmpty())
			entity.setCorreoElectronicoContacto(vo.getCorreoElectronicoContacto());	
		
		entity.setIdVigenciaOferta(vo.getIdVigenciaOferta());
		
		entity.setDiscapacidades(vo.getDiscapacidades());
		
		if(vo.getPlazasCubiertas() != null)
			entity.setPlazasCubiertas(vo.getPlazasCubiertas());
		if(vo.getPublicarOfertas() != null)
			entity.setPublicarOfertas(vo.getPublicarOfertas());
		if(vo.getIdUsuario() != null)
			entity.setIdUsuario(vo.getIdUsuario());
		if(vo.getIdOficinaRegistro() != null)
			entity.setIdOficinaRegistro(vo.getIdOficinaRegistro());
		
		return entity;
	}
	
	private void updateEntity(OfertaEmpleo entity, OfertaEmpleoVO vo) {
		//entity.setIdOfertaEmpleo(vo.getIdOfertaEmpleo());
		//entity.setIdEmpresa(vo.getIdEmpresa());
		
		entity.setContactoCorreo(vo.getContactoCorreo());
		entity.setContactoTel(vo.getContactoTel());
		entity.setDiasEntrevista(vo.getDiasEntrevista());
		entity.setDiasLaborales(vo.getDiasLaborales());
		entity.setDisponibilidadRadicar(vo.getDisponibilidadRadicar());
		entity.setDisponibilidadViajar(vo.getDisponibilidadViajar());
		entity.setEdadMaxima(vo.getEdadMaxima());
		entity.setEdadMinima(vo.getEdadMinima());
		entity.setEdadRequisito(vo.getEdadRequisito());
		entity.setEmpresaOfrece(vo.getEmpresaOfrece());
		entity.setEstatus(vo.getEstatus());
		entity.setExperienciaAnios(vo.getExperienciaAnios());
		entity.setFechaAlta(vo.getFechaAlta());
		entity.setFechaFin(vo.getFechaFin());
		entity.setFechaInicio(vo.getFechaInicio());
		entity.setFechaModificacion(vo.getFechaModificacion());
		entity.setFuente(vo.getFuente());
		entity.setFunciones(vo.getFunciones());
		entity.setGenero(vo.getGenero());
//		entity.setHabilidadGeneral(vo.getHabilidadGeneral());
		entity.setHoraEntrada(vo.getHoraEntrada());
		entity.setHoraSalida(vo.getHoraSalida());
		entity.setIdAreaLaboral(vo.getIdAreaLaboral());
		entity.setIdCausaVacante(vo.getIdCausaVacante());
		//entity.setIdContacto(vo.getIdContacto());
		entity.setIdDiscapacidad(vo.getIdDiscapacidad());
		entity.setIdDuracionAproximada(vo.getIdDuracionAproximada());

		entity.setIdHorarioA(vo.getIdHorarioA());
		entity.setIdHorarioDe(vo.getIdHorarioDe());
		entity.setIdJerarquia(vo.getIdJerarquia());
		entity.setIdNivelEstudio(vo.getIdNivelEstudio());
		entity.setIdOcupacion(vo.getIdOcupacion());
		
		entity.setIdSituacionAcademica(vo.getIdSituacionAcademica());
		//entity.setIdTerceraEmpresa(vo.getIdTerceraEmpresa());
		entity.setIdTipoContrato(vo.getIdTipoContrato());
		entity.setIdTipoEmpleo(vo.getIdTipoEmpleo());
		entity.setIdVigenciaOferta(vo.getIdVigenciaOferta());
		entity.setLimitePostulantes(vo.getLimitePostulantes());
		entity.setMapaUbicacion(vo.getMapaUbicacion());
		entity.setNumeroPlazas(vo.getNumeroPlazas());
		if (null != vo.getPlazasCubiertas())
			entity.setPlazasCubiertas(vo.getPlazasCubiertas());
		if (null != vo.getObservaciones()) {
			if (vo.getObservaciones().length()<=2000)
				entity.setObservaciones(vo.getObservaciones());
			else
				entity.setObservaciones(vo.getObservaciones().substring(0,2000));
		}
		entity.setRolarTurno(vo.getRolarTurno());
		entity.setSalario(vo.getSalario());
		entity.setTituloOferta(vo.getTituloOferta());
		entity.setDiscapacidades(vo.getDiscapacidades());

	}
	
	private OfertaEmpleoVO getOfertaEmpleoVO(OfertaEmpleo entity) {
		OfertaEmpleoVO vo = new OfertaEmpleoVO();
		if(entity.getContactoCorreo() != null)
			vo.setContactoCorreo(entity.getContactoCorreo());
		if(entity.getContactoTel() != null)
			vo.setContactoTel(entity.getContactoTel());
		if(entity.getDiasEntrevista() != null)
			vo.setDiasEntrevista(entity.getDiasEntrevista());
		vo.setDiasLaborales(entity.getDiasLaborales());
		if(entity.getDisponibilidadRadicar() != null)
			vo.setDisponibilidadRadicar(entity.getDisponibilidadRadicar());
		if(entity.getDisponibilidadViajar() != null)
			vo.setDisponibilidadViajar(entity.getDisponibilidadViajar());
		if(entity.getEdadMaxima() != null)
			vo.setEdadMaxima(entity.getEdadMaxima());
		if(entity.getEdadMinima() != null)
			vo.setEdadMinima(entity.getEdadMinima());
		if(entity.getEdadRequisito() != null)
			vo.setEdadRequisito(entity.getEdadRequisito());
		if(entity.getExperienciaAnios() != null)
			vo.setExperienciaAnios(entity.getExperienciaAnios());
		if(entity.getGenero() != null)
			vo.setGenero(entity.getGenero());
		if(entity.getIdDuracionAproximada() != null)
			vo.setIdDuracionAproximada(entity.getIdDuracionAproximada());
		if(entity.getIdHorarioA() != null)
			vo.setIdHorarioA(entity.getIdHorarioA());
		if(entity.getIdHorarioDe() != null)
			vo.setIdHorarioDe(entity.getIdHorarioDe());
		if(entity.getIdNivelEstudio() != null)
			vo.setIdNivelEstudio(entity.getIdNivelEstudio());
		if(entity.getIdSituacionAcademica() != null)
			vo.setIdSituacionAcademica(entity.getIdSituacionAcademica());
		if(entity.getMapaUbicacion() != null)
			vo.setMapaUbicacion(entity.getMapaUbicacion());
//		if(entity.getGeoReferencia()!=null){
//			vo.setGeoReferencia(entity.getGeoReferencia());
//		}
		if(entity.getObservaciones() != null)
			vo.setObservaciones(entity.getObservaciones());
		if(entity.getIdVigenciaOferta() != null)
			vo.setIdVigenciaOferta(entity.getIdVigenciaOferta());	
		if(entity.getNombreEmpresa() != null)
			vo.setNombreEmpresa(entity.getNombreEmpresa());
		if(entity.getIdActividadEconomica() != null)
			vo.setIdActividadEconomica((int)entity.getIdActividadEconomica());
		if(entity.getNombreContacto() != null)
			vo.setNombreContacto(entity.getNombreContacto());
		if(entity.getCargoContacto() != null)
			vo.setCargoContacto(entity.getCargoContacto());
		if(entity.getCorreoElectronicoContacto() != null)
			vo.setCorreoElectronicoContacto(entity.getCorreoElectronicoContacto());
		if(entity.getContactoDomicilio() != null)
			vo.setContactoDomicilio(entity.getContactoDomicilio());
		if (null != entity.getIdVigenciaOferta())
			vo.setIdVigenciaOferta(entity.getIdVigenciaOferta()); 
		if (null != entity.getIdArea())
			vo.setIdArea(entity.getIdArea());
		if (null != entity.getIdSubarea())
			vo.setIdSubArea(entity.getIdSubarea());
		vo.setEmpresaOfrece(entity.getEmpresaOfrece());
		vo.setEstatus(entity.getEstatus());
		vo.setFechaAlta(entity.getFechaAlta());
		vo.setFechaFin(entity.getFechaFin());
		vo.setFechaInicio(entity.getFechaInicio());
		vo.setFechaModificacion(entity.getFechaModificacion());
		vo.setFuente(entity.getFuente());
		vo.setFunciones(entity.getFunciones());
		vo.setHabilidadGeneral(entity.getHabilidadGeneral());
		vo.setHoraEntrada(entity.getHoraEntrada());
		vo.setHoraSalida(entity.getHoraSalida());
		vo.setIdAreaLaboral(entity.getIdAreaLaboral());
		vo.setIdCausaVacante(entity.getIdCausaVacante());
		//vo.setIdContacto(entity.getIdContacto());
		vo.setIdDiscapacidad(entity.getIdDiscapacidad());
		vo.setIdEmpresa(entity.getIdEmpresa());
		vo.setIdJerarquia(entity.getIdJerarquia());
		vo.setIdOcupacion(entity.getIdOcupacion());
		vo.setIdOfertaEmpleo(entity.getIdOfertaEmpleo());
		//vo.setIdTerceraEmpresa(entity.getIdTerceraEmpresa());
		vo.setIdTipoContrato(entity.getIdTipoContrato());
		vo.setIdTipoEmpleo(entity.getIdTipoEmpleo());
		vo.setLimitePostulantes(entity.getLimitePostulantes());
		vo.setNumeroPlazas(entity.getNumeroPlazas());
		vo.setRolarTurno(entity.getRolarTurno());
		vo.setSalario(entity.getSalario());
		vo.setTituloOferta(entity.getTituloOferta());
		vo.setDiscapacidades(entity.getDiscapacidades());
		
		return vo;
	}
	
	private mx.gob.stps.portal.persistencia.vo.OfertaEmpleoVO convertOfertaEmpleoVO(OfertaEmpleo entity) {
		mx.gob.stps.portal.persistencia.vo.OfertaEmpleoVO vo = new mx.gob.stps.portal.persistencia.vo.OfertaEmpleoVO();
		if(entity.getContactoCorreo() != null)
			vo.setContactoCorreo(entity.getContactoCorreo());
		if(entity.getContactoTel() != null)
			vo.setContactoTel(entity.getContactoTel());
		if(entity.getDiasEntrevista() != null)
			vo.setDiasEntrevista(entity.getDiasEntrevista());
		vo.setDiasLaborales(entity.getDiasLaborales());
		if(entity.getDisponibilidadRadicar() != null)
			vo.setDisponibilidadRadicar(entity.getDisponibilidadRadicar());
		if(entity.getDisponibilidadViajar() != null)
			vo.setDisponibilidadViajar(entity.getDisponibilidadViajar());
		if(entity.getEdadMaxima() != null)
			vo.setEdadMaxima(entity.getEdadMaxima());
		if(entity.getEdadMinima() != null)
			vo.setEdadMinima(entity.getEdadMinima());
		if(entity.getEdadRequisito() != null)
			vo.setEdadRequisito(entity.getEdadRequisito());
		if(entity.getExperienciaAnios() != null)
			vo.setExperienciaAnios(entity.getExperienciaAnios());
		if(entity.getGenero() != null)
			vo.setGenero(entity.getGenero());
		if(entity.getIdDuracionAproximada() != null)
			vo.setIdDuracionAproximada(entity.getIdDuracionAproximada());
		if(entity.getIdHorarioA() != null)
			vo.setIdHorarioA(entity.getIdHorarioA());
		if(entity.getIdHorarioDe() != null)
			vo.setIdHorarioDe(entity.getIdHorarioDe());
		if(entity.getIdNivelEstudio() != null)
			vo.setIdNivelEstudio(entity.getIdNivelEstudio());
		if(entity.getIdSituacionAcademica() != null)
			vo.setIdSituacionAcademica(entity.getIdSituacionAcademica());
		if(entity.getMapaUbicacion() != null)
			vo.setMapaUbicacion(entity.getMapaUbicacion());
		if(entity.getObservaciones() != null)
			vo.setObservaciones(entity.getObservaciones());
		if(entity.getIdVigenciaOferta() != null)
			vo.setIdVigenciaOferta(entity.getIdVigenciaOferta());	
		if(entity.getNombreEmpresa() != null)
			vo.setNombreEmpresa(entity.getNombreEmpresa());
		if(entity.getIdActividadEconomica() != null)
			vo.setIdActividadEconomica((int)entity.getIdActividadEconomica());
		if(entity.getNombreContacto() != null)
			vo.setNombreContacto(entity.getNombreContacto());
		if(entity.getCargoContacto() != null)
			vo.setCargoContacto(entity.getCargoContacto());
		if(entity.getCorreoElectronicoContacto() != null)
			vo.setCorreoElectronicoContacto(entity.getCorreoElectronicoContacto());
		if(entity.getContactoDomicilio() != null)
			vo.setContactoDomicilio(entity.getContactoDomicilio());
		if(entity.getIdVigenciaOferta() != null)
			vo.setIdVigenciaOferta(entity.getIdVigenciaOferta()); 
		
		vo.setEmpresaOfrece(entity.getEmpresaOfrece());
		vo.setEstatus(entity.getEstatus());
		vo.setFechaAlta(entity.getFechaAlta());
		vo.setFechaFin(entity.getFechaFin());
		vo.setFechaInicio(entity.getFechaInicio());
		vo.setFechaModificacion(entity.getFechaModificacion());
		vo.setFuente(entity.getFuente());
		vo.setFunciones(entity.getFunciones());
//		vo.setHabilidadGeneral(entity.getHabilidadGeneral());
		vo.setHoraEntrada(entity.getHoraEntrada());
		vo.setHoraSalida(entity.getHoraSalida());
		vo.setIdAreaLaboral(entity.getIdAreaLaboral());
		vo.setIdCausaVacante(entity.getIdCausaVacante());
		//vo.setIdContacto(entity.getIdContacto());
		vo.setIdDiscapacidad(entity.getIdDiscapacidad());
		vo.setIdEmpresa(entity.getIdEmpresa());
		vo.setIdJerarquia(entity.getIdJerarquia());
		vo.setIdOcupacion(entity.getIdOcupacion());
		vo.setIdOfertaEmpleo(entity.getIdOfertaEmpleo());
		//vo.setIdTerceraEmpresa(entity.getIdTerceraEmpresa());
		vo.setIdTipoContrato(entity.getIdTipoContrato());
		vo.setIdTipoEmpleo(entity.getIdTipoEmpleo());
		vo.setLimitePostulantes(entity.getLimitePostulantes());
		vo.setNumeroPlazas(entity.getNumeroPlazas());
		vo.setRolarTurno(entity.getRolarTurno());
		vo.setSalario(entity.getSalario());
		vo.setTituloOferta(entity.getTituloOferta());
		vo.setDiscapacidades(entity.getDiscapacidades());
		
		return vo;
	}
	
	public OfertaTotalesVO consultaOfertasEmpleoPorEntidad(int idEntidad) {

		if (idEntidad<0) throw new IllegalArgumentException("Identificador de Entidad requerido");

		PropertiesLoader properties = PropertiesLoader.getInstance();
		int limiteListado = properties.getPropertyInt("ws.portal.consulta.ofertas.limite");
		if (limiteListado<=0) limiteListado = 5;
		
		OfertaTotalesVO totales = new OfertaTotalesVO();
		List<Long> idsOferta = new ArrayList<Long>();
		
		StringBuilder sqlCount = new StringBuilder();
		sqlCount.append(" WITH OFERTAS AS ( "); 

		sqlCount.append(" SELECT OFER.* "); // Consulta principal
		sqlCount.append("   FROM OFERTA_EMPLEO OFER, ");
		sqlCount.append("        (SELECT * FROM OFERTA_UBICACION) DOM ");
		sqlCount.append("  WHERE DOM.ID_OFERTA_EMPLEO = OFER.ID_OFERTA_EMPLEO ");
		sqlCount.append("    AND DOM.ID_ENTIDAD = ?1 ");
		sqlCount.append("    AND OFER.ESTATUS = ?2 ORDER BY ID_OFERTA_EMPLEO DESC");
		
		sqlCount.append(" ) ");
		sqlCount.append(" SELECT TOTAL, ");
		sqlCount.append("        SALARIO_MINIMO, ");
		sqlCount.append("        SALARIO_MAXIMO, ");
		sqlCount.append("        PROMEDIO, ");
		sqlCount.append("        (SELECT EXPERIENCIA_ANIOS "); // Agrupa por experiencia
		sqlCount.append("           FROM (SELECT EXPERIENCIA_ANIOS, COUNT(1) AS TOTAL ");
		sqlCount.append("                   FROM OFERTAS ");
		sqlCount.append("                  GROUP BY EXPERIENCIA_ANIOS ");
		sqlCount.append("                  ORDER BY TOTAL DESC) ");
		sqlCount.append("           WHERE ROWNUM = 1) AS EXPERIENCIA_ANIOS, ");
		sqlCount.append("           (SELECT ID_NIVEL_ESTUDIO "); // Agrupa por Nivel de Estudios
		sqlCount.append("           FROM (SELECT ID_NIVEL_ESTUDIO, COUNT(1) AS TOTAL ");
		sqlCount.append("                   FROM OFERTAS ");
		sqlCount.append("                  GROUP BY ID_NIVEL_ESTUDIO ");
		sqlCount.append("                  ORDER BY TOTAL DESC) ");
		sqlCount.append("           WHERE ROWNUM = 1) AS ID_NIVEL_ESTUDIO ");
		sqlCount.append("   FROM (SELECT COUNT(1) AS TOTAL, "); // Obtiene los totales
		sqlCount.append("                MIN(SALARIO) AS SALARIO_MINIMO, ");
		sqlCount.append("                MAX(SALARIO) AS SALARIO_MAXIMO, ");
		sqlCount.append("                AVG(SALARIO) AS PROMEDIO ");
		sqlCount.append("           FROM OFERTAS) ");

		Query queryCount = entityManager.createNativeQuery(sqlCount.toString());
		//queryCount.setParameter(1, TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
		queryCount.setParameter(1, idEntidad);
		queryCount.setParameter(2, ESTATUS.ACTIVO.getIdOpcion());

		Object[] results = (Object[])queryCount.getSingleResult();

		totales.setTotalOfertas    (Utils.toLong  (results[0]));
		totales.setSalarioMinimo   (Utils.toDouble(results[1]));
		totales.setSalarioMaximo   (Utils.toDouble(results[2]));
		totales.setSalarioPromedio (Utils.toDouble(results[3]));
		totales.setExperienciaAnios(Utils.toInt   (results[4]));
		totales.setIdNivelEstudio  (Utils.toInt   (results[5]));

		StringBuilder sqlList = new StringBuilder();
		sqlList.append(" SELECT * ");
		sqlList.append("   FROM ( ");
		sqlList.append(" SELECT OFER.ID_OFERTA_EMPLEO ");
		sqlList.append("   FROM OFERTA_EMPLEO OFER, ");
		sqlList.append("        (SELECT * FROM OFERTA_UBICACION WHERE) DOM ");
		sqlList.append("  WHERE DOM.ID_OFERTA_EMPLEO = OFER.ID_OFERTA_EMPLEO ");
		sqlList.append("    AND DOM.ID_ENTIDAD = ?1 ");
		sqlList.append("    AND OFER.ESTATUS = ?2 ");
		sqlList.append("  ORDER BY OFER.ID_OFERTA_EMPLEO DESC ");
		sqlList.append("       ) OFERTAS ");
		sqlList.append(" WHERE ROWNUM <= ?3 ");

		Query queryList = entityManager.createNativeQuery(sqlList.toString());
		//queryList.setParameter(1, TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
		queryList.setParameter(1, idEntidad);
		queryList.setParameter(2, ESTATUS.ACTIVO.getIdOpcion());
		queryList.setParameter(3, limiteListado);

		@SuppressWarnings("unchecked")
		List<Object> rowSet = queryList.getResultList();
		
		for (Object rs : rowSet) idsOferta.add(Utils.toLong(rs));

		totales.setIdsOferta(idsOferta);
		
		return totales;
	}
	
	public OfertaTotalesVO consultaOfertasEmpleoPorOcupacion(List<Integer> idsOcupacion, int idOcupacionPortal) {

		if (idsOcupacion==null || idsOcupacion.isEmpty()) throw new IllegalArgumentException("Identificador de Carrera requerido");
		
		PropertiesLoader properties = PropertiesLoader.getInstance();
		int limiteListado = properties.getPropertyInt("ws.portal.consulta.ofertas.limite");
		if (limiteListado<=0) limiteListado = 5;

		String in = "";
		for (Integer idOcupacion : idsOcupacion) in += idOcupacion.intValue() +",";
		if (in.endsWith(",")) in = in.substring(0, in.length()-1);

		OfertaTotalesVO totales = new OfertaTotalesVO();
		
		StringBuilder sqlCount = new StringBuilder();
		sqlCount.append(" WITH OFERTAS AS ( ");
		
		sqlCount.append(" SELECT OFER.* "); // Consulta principal
		sqlCount.append("   FROM OFERTA_EMPLEO OFER ");
		sqlCount.append("  WHERE OFER.ESTATUS = ?1 ");
		sqlCount.append("    AND OFER.ID_OCUPACION IN ("+ in +") ");
		//sqlCount.append("    AND OFER.ID_OCUPACION IN ("+ idOcupacionPortal +") ");				
		sqlCount.append(" ) ");
		sqlCount.append(" SELECT TOTAL, ");
		sqlCount.append("        SALARIO_MINIMO, ");
		sqlCount.append("        SALARIO_MAXIMO, ");
		sqlCount.append("        PROMEDIO, ");
		sqlCount.append("        (SELECT EXPERIENCIA_ANIOS "); // Agrupa por experiencia
		sqlCount.append("           FROM (SELECT EXPERIENCIA_ANIOS, COUNT(1) AS TOTAL ");
		sqlCount.append("                   FROM OFERTAS ");
		sqlCount.append("                  GROUP BY EXPERIENCIA_ANIOS ");
		sqlCount.append("                  ORDER BY TOTAL DESC) ");
		sqlCount.append("           WHERE ROWNUM = 1) AS EXPERIENCIA_ANIOS, ");
		sqlCount.append("           (SELECT ID_NIVEL_ESTUDIO "); // Agrupa por Nivel de Estudios
		sqlCount.append("           FROM (SELECT ID_NIVEL_ESTUDIO, COUNT(1) AS TOTAL ");
		sqlCount.append("                   FROM OFERTAS ");
		sqlCount.append("                  GROUP BY ID_NIVEL_ESTUDIO ");
		sqlCount.append("                  ORDER BY TOTAL DESC) ");
		sqlCount.append("           WHERE ROWNUM = 1) AS ID_NIVEL_ESTUDIO ");
		sqlCount.append("   FROM (SELECT COUNT(1) AS TOTAL, "); // Obtiene los totales
		sqlCount.append("                MIN(SALARIO) AS SALARIO_MINIMO, ");
		sqlCount.append("                MAX(SALARIO) AS SALARIO_MAXIMO, ");
		sqlCount.append("                AVG(SALARIO) AS PROMEDIO ");
		sqlCount.append("           FROM OFERTAS) ");

		Query queryCount = entityManager.createNativeQuery(sqlCount.toString());
		queryCount.setParameter(1, ESTATUS.ACTIVO.getIdOpcion());

		Object[] results = (Object[])queryCount.getSingleResult();

		totales.setTotalOfertas    (Utils.toLong  (results[0]));
		totales.setSalarioMinimo   (Utils.toDouble(results[1]));
		totales.setSalarioMaximo   (Utils.toDouble(results[2]));
		totales.setSalarioPromedio (Utils.toDouble(results[3]));
		totales.setExperienciaAnios(Utils.toInt   (results[4]));
		totales.setIdNivelEstudio  (Utils.toInt   (results[5]));
		
		//System.out.println("------idOcupacionPortal:" + idOcupacionPortal);
		StringBuilder sqlList = new StringBuilder();
		sqlList.append(" SELECT * ");
	    sqlList.append("   FROM ( ");
	    sqlList.append(" SELECT OFER.ID_OFERTA_EMPLEO ");
	    sqlList.append("   FROM OFERTA_EMPLEO OFER ");
	    sqlList.append("  WHERE OFER.ESTATUS = ?1 ");
	    sqlList.append("    AND OFER.ID_OCUPACION IN ("+ in +") ");
	    //sqlList.append("    AND OFER.ID_OCUPACION IN ("+ idOcupacionPortal +") ");	    
	    sqlList.append("  ORDER BY OFER.FECHA_MODIFICACION DESC, OFER.FECHA_ALTA DESC ");
	    sqlList.append("       ) OFERTAS ");
	    sqlList.append(" WHERE ROWNUM <= ?2 ");

	    Query queryList = entityManager.createNativeQuery(sqlList.toString());
		queryList.setParameter(1, ESTATUS.ACTIVO.getIdOpcion());
		queryList.setParameter(2, limiteListado);
		
		@SuppressWarnings("unchecked")
		List<Object> rowSet = queryList.getResultList();

		List<Long> idsOferta = new ArrayList<Long>();

		for (Object rs : rowSet) idsOferta.add(Utils.toLong(rs));

		totales.setIdsOferta(idsOferta);
		
		return totales;
	}	
	
	public OfertaTotalesVO consultaOfertasEmpleoPorOcupacion(List<Integer> idsOcupacion) {

		if (idsOcupacion==null || idsOcupacion.isEmpty()) throw new IllegalArgumentException("Identificador de Carrera requerido");
		
		PropertiesLoader properties = PropertiesLoader.getInstance();
		int limiteListado = properties.getPropertyInt("ws.portal.consulta.ofertas.limite");
		if (limiteListado<=0) limiteListado = 5;

		String in = "";
		for (Integer idOcupacion : idsOcupacion) in += idOcupacion.intValue() +",";
		if (in.endsWith(",")) in = in.substring(0, in.length()-1);

		OfertaTotalesVO totales = new OfertaTotalesVO();
		
		StringBuilder sqlCount = new StringBuilder();
		sqlCount.append(" WITH OFERTAS AS ( ");
		
		sqlCount.append(" SELECT OFER.* "); // Consulta principal
		sqlCount.append("   FROM OFERTA_EMPLEO OFER ");
		sqlCount.append("  WHERE OFER.ESTATUS = ?1 ");
		sqlCount.append("    AND OFER.ID_OCUPACION IN ("+ in +") ");
		
		sqlCount.append(" ) ");
		sqlCount.append(" SELECT TOTAL, ");
		sqlCount.append("        SALARIO_MINIMO, ");
		sqlCount.append("        SALARIO_MAXIMO, ");
		sqlCount.append("        PROMEDIO, ");
		sqlCount.append("        (SELECT EXPERIENCIA_ANIOS "); // Agrupa por experiencia
		sqlCount.append("           FROM (SELECT EXPERIENCIA_ANIOS, COUNT(1) AS TOTAL ");
		sqlCount.append("                   FROM OFERTAS ");
		sqlCount.append("                  GROUP BY EXPERIENCIA_ANIOS ");
		sqlCount.append("                  ORDER BY TOTAL DESC) ");
		sqlCount.append("           WHERE ROWNUM = 1) AS EXPERIENCIA_ANIOS, ");
		sqlCount.append("           (SELECT ID_NIVEL_ESTUDIO "); // Agrupa por Nivel de Estudios
		sqlCount.append("           FROM (SELECT ID_NIVEL_ESTUDIO, COUNT(1) AS TOTAL ");
		sqlCount.append("                   FROM OFERTAS ");
		sqlCount.append("                  GROUP BY ID_NIVEL_ESTUDIO ");
		sqlCount.append("                  ORDER BY TOTAL DESC) ");
		sqlCount.append("           WHERE ROWNUM = 1) AS ID_NIVEL_ESTUDIO ");
		sqlCount.append("   FROM (SELECT COUNT(1) AS TOTAL, "); // Obtiene los totales
		sqlCount.append("                MIN(SALARIO) AS SALARIO_MINIMO, ");
		sqlCount.append("                MAX(SALARIO) AS SALARIO_MAXIMO, ");
		sqlCount.append("                AVG(SALARIO) AS PROMEDIO ");
		sqlCount.append("           FROM OFERTAS) ");

		Query queryCount = entityManager.createNativeQuery(sqlCount.toString());
		queryCount.setParameter(1, ESTATUS.ACTIVO.getIdOpcion());

		Object[] results = (Object[])queryCount.getSingleResult();

		totales.setTotalOfertas    (Utils.toLong  (results[0]));
		totales.setSalarioMinimo   (Utils.toDouble(results[1]));
		totales.setSalarioMaximo   (Utils.toDouble(results[2]));
		totales.setSalarioPromedio (Utils.toDouble(results[3]));
		totales.setExperienciaAnios(Utils.toInt   (results[4]));
		totales.setIdNivelEstudio  (Utils.toInt   (results[5]));
		
		StringBuilder sqlList = new StringBuilder();
		sqlList.append(" SELECT * ");
	    sqlList.append("   FROM ( ");
	    sqlList.append(" SELECT OFER.ID_OFERTA_EMPLEO ");
	    sqlList.append("   FROM OFERTA_EMPLEO OFER ");
	    sqlList.append("  WHERE OFER.ESTATUS = ?1 ");
	    sqlList.append("    AND OFER.ID_OCUPACION IN ("+ in +") ");
	    sqlList.append("  ORDER BY OFER.FECHA_ALTA DESC ");
	    sqlList.append("       ) OFERTAS ");
	    sqlList.append(" WHERE ROWNUM <= ?2 ");

	    Query queryList = entityManager.createNativeQuery(sqlList.toString());
		queryList.setParameter(1, ESTATUS.ACTIVO.getIdOpcion());
		queryList.setParameter(2, limiteListado);
		
		@SuppressWarnings("unchecked")
		List<Object> rowSet = queryList.getResultList();

		List<Long> idsOferta = new ArrayList<Long>();

		for (Object rs : rowSet) idsOferta.add(Utils.toLong(rs));

		totales.setIdsOferta(idsOferta);
		
		return totales;
	}
	
	public OfertaTotalesVO consultaOfertasEmpleoPorEntidadOcupacion(int idEntidad, List<Integer> idsOcupacion, int idOcupacionPortal) {

		if (idEntidad<0) throw new IllegalArgumentException("Identificador de Entidad requerido");
		if (idsOcupacion==null || idsOcupacion.isEmpty()) throw new IllegalArgumentException("Identificador de Ocupacion requerido");

		PropertiesLoader properties = PropertiesLoader.getInstance();
		int limiteListado = properties.getPropertyInt("ws.portal.consulta.ofertas.limite");
		if (limiteListado<=0) limiteListado = 5;

		String in = "";
		for (Integer idOcupacion : idsOcupacion) in += idOcupacion.intValue() +",";
		if (in.endsWith(",")) in = in.substring(0, in.length()-1);

		OfertaTotalesVO totales = new OfertaTotalesVO();
		List<Long> idsOferta = new ArrayList<Long>();
		
		StringBuilder sqlCount = new StringBuilder();

		sqlCount.append(" WITH OFERTAS AS ( "); 

		sqlCount.append(" SELECT OFER.* "); // Consulta principal
		sqlCount.append("   FROM OFERTA_EMPLEO OFER, ");
		sqlCount.append("        (SELECT * FROM OFERTA_UBICACION) DOM ");
		sqlCount.append("  WHERE DOM.ID_OFERTA_EMPLEO = OFER.ID_OFERTA_EMPLEO ");
		sqlCount.append("    AND DOM.ID_ENTIDAD = ?1 ");
		sqlCount.append("    AND OFER.ID_OCUPACION IN ("+ in +") ");
		//sqlCount.append("    AND OFER.ID_OCUPACION IN ("+ idOcupacionPortal +") ");		
		sqlCount.append("    AND OFER.ESTATUS = ?2 ");
		
		sqlCount.append(" ) ");
		sqlCount.append(" SELECT TOTAL, ");
		sqlCount.append("        SALARIO_MINIMO, ");
		sqlCount.append("        SALARIO_MAXIMO, ");
		sqlCount.append("        PROMEDIO, ");
		sqlCount.append("        (SELECT EXPERIENCIA_ANIOS "); // Agrupa por experiencia
		sqlCount.append("           FROM (SELECT EXPERIENCIA_ANIOS, COUNT(1) AS TOTAL ");
		sqlCount.append("                   FROM OFERTAS ");
		sqlCount.append("                  GROUP BY EXPERIENCIA_ANIOS ");
		sqlCount.append("                  ORDER BY TOTAL DESC) ");
		sqlCount.append("           WHERE ROWNUM = 1) AS EXPERIENCIA_ANIOS, ");
		sqlCount.append("           (SELECT ID_NIVEL_ESTUDIO "); // Agrupa por Nivel de Estudios
		sqlCount.append("           FROM (SELECT ID_NIVEL_ESTUDIO, COUNT(1) AS TOTAL ");
		sqlCount.append("                   FROM OFERTAS ");
		sqlCount.append("                  GROUP BY ID_NIVEL_ESTUDIO ");
		sqlCount.append("                  ORDER BY TOTAL DESC) ");
		sqlCount.append("           WHERE ROWNUM = 1) AS ID_NIVEL_ESTUDIO ");
		sqlCount.append("   FROM (SELECT COUNT(1) AS TOTAL, "); // Obtiene los totales
		sqlCount.append("                MIN(SALARIO) AS SALARIO_MINIMO, ");
		sqlCount.append("                MAX(SALARIO) AS SALARIO_MAXIMO, ");
		sqlCount.append("                AVG(SALARIO) AS PROMEDIO ");
		sqlCount.append("           FROM OFERTAS) ");
		
		Query queryCount = entityManager.createNativeQuery(sqlCount.toString());
		//queryCount.setParameter(1, TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
		queryCount.setParameter(1, idEntidad);
		queryCount.setParameter(2, ESTATUS.ACTIVO.getIdOpcion());

		Object[] results = (Object[])queryCount.getSingleResult();

		totales.setTotalOfertas    (Utils.toLong  (results[0]));
		totales.setSalarioMinimo   (Utils.toDouble(results[1]));
		totales.setSalarioMaximo   (Utils.toDouble(results[2]));
		totales.setSalarioPromedio (Utils.toDouble(results[3]));
		totales.setExperienciaAnios(Utils.toInt   (results[4]));
		totales.setIdNivelEstudio  (Utils.toInt   (results[5]));

		StringBuilder sqlList = new StringBuilder();
		sqlList.append(" SELECT DISTINCT * ");
		sqlList.append("   FROM ( ");
		sqlList.append(" SELECT OFER.ID_OFERTA_EMPLEO ");
		sqlList.append("   FROM OFERTA_EMPLEO OFER, ");
		sqlList.append("        (SELECT * FROM OFERTA_UBICACION) DOM ");
		sqlList.append("  WHERE DOM.ID_OFERTA_EMPLEO = OFER.ID_OFERTA_EMPLEO ");
		sqlList.append("    AND DOM.ID_ENTIDAD = ?1 ");
		sqlList.append("    AND OFER.ID_OCUPACION IN ("+ in +") ");
		//sqlList.append("    AND OFER.ID_OCUPACION IN ("+ idOcupacionPortal +") ");		
		sqlList.append("    AND OFER.ESTATUS = ?2 ");
		sqlList.append("  ORDER BY OFER.FECHA_MODIFICACION DESC, OFER.FECHA_ALTA DESC ");
		sqlList.append("       ) OFERTAS ");
		sqlList.append(" WHERE ROWNUM <= ?3 ");
		
		Query queryList = entityManager.createNativeQuery(sqlList.toString());
		//queryList.setParameter(1, TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
		queryList.setParameter(1, idEntidad);
		queryList.setParameter(2, ESTATUS.ACTIVO.getIdOpcion());
		queryList.setParameter(3, limiteListado);

		@SuppressWarnings("unchecked")
		List<Object> rowSet = queryList.getResultList();
		
		for (Object rs : rowSet) idsOferta.add(Utils.toLong(rs));

		totales.setIdsOferta(idsOferta);
		
		return totales;
	}	
	
	public OfertaTotalesVO consultaOfertasEmpleoPorEntidadOcupacion(int idEntidad, List<Integer> idsOcupacion) {

		if (idEntidad<0) throw new IllegalArgumentException("Identificador de Entidad requerido");
		if (idsOcupacion==null || idsOcupacion.isEmpty()) throw new IllegalArgumentException("Identificador de Ocupacion requerido");

		PropertiesLoader properties = PropertiesLoader.getInstance();
		int limiteListado = properties.getPropertyInt("ws.portal.consulta.ofertas.limite");
		if (limiteListado<=0) limiteListado = 5;

		String in = "";
		for (Integer idOcupacion : idsOcupacion) in += idOcupacion.intValue() +",";
		if (in.endsWith(",")) in = in.substring(0, in.length()-1);

		OfertaTotalesVO totales = new OfertaTotalesVO();
		List<Long> idsOferta = new ArrayList<Long>();
		
		StringBuilder sqlCount = new StringBuilder();

		sqlCount.append(" WITH OFERTAS AS ( "); 

		sqlCount.append(" SELECT OFER.* "); // Consulta principal
		sqlCount.append("   FROM OFERTA_EMPLEO OFER, ");
		sqlCount.append("        (SELECT * FROM OFERTA_UBICACION) DOM ");
		sqlCount.append("  WHERE DOM.ID_OFERTA_EMPLEO = OFER.ID_OFERTA_EMPLEO ");
		sqlCount.append("    AND DOM.ID_ENTIDAD = ?1 ");
		sqlCount.append("    AND OFER.ID_OCUPACION IN ("+ in +") ");
		sqlCount.append("    AND OFER.ESTATUS = ?2 ");
		
		sqlCount.append(" ) ");
		sqlCount.append(" SELECT TOTAL, ");
		sqlCount.append("        SALARIO_MINIMO, ");
		sqlCount.append("        SALARIO_MAXIMO, ");
		sqlCount.append("        PROMEDIO, ");
		sqlCount.append("        (SELECT EXPERIENCIA_ANIOS "); // Agrupa por experiencia
		sqlCount.append("           FROM (SELECT EXPERIENCIA_ANIOS, COUNT(1) AS TOTAL ");
		sqlCount.append("                   FROM OFERTAS ");
		sqlCount.append("                  GROUP BY EXPERIENCIA_ANIOS ");
		sqlCount.append("                  ORDER BY TOTAL DESC) ");
		sqlCount.append("           WHERE ROWNUM = 1) AS EXPERIENCIA_ANIOS, ");
		sqlCount.append("           (SELECT ID_NIVEL_ESTUDIO "); // Agrupa por Nivel de Estudios
		sqlCount.append("           FROM (SELECT ID_NIVEL_ESTUDIO, COUNT(1) AS TOTAL ");
		sqlCount.append("                   FROM OFERTAS ");
		sqlCount.append("                  GROUP BY ID_NIVEL_ESTUDIO ");
		sqlCount.append("                  ORDER BY TOTAL DESC) ");
		sqlCount.append("           WHERE ROWNUM = 1) AS ID_NIVEL_ESTUDIO ");
		sqlCount.append("   FROM (SELECT COUNT(1) AS TOTAL, "); // Obtiene los totales
		sqlCount.append("                MIN(SALARIO) AS SALARIO_MINIMO, ");
		sqlCount.append("                MAX(SALARIO) AS SALARIO_MAXIMO, ");
		sqlCount.append("                AVG(SALARIO) AS PROMEDIO ");
		sqlCount.append("           FROM OFERTAS) ");
		
		Query queryCount = entityManager.createNativeQuery(sqlCount.toString());
		//queryCount.setParameter(1, TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
		queryCount.setParameter(1, idEntidad);
		queryCount.setParameter(2, ESTATUS.ACTIVO.getIdOpcion());

		Object[] results = (Object[])queryCount.getSingleResult();

		totales.setTotalOfertas    (Utils.toLong  (results[0]));
		totales.setSalarioMinimo   (Utils.toDouble(results[1]));
		totales.setSalarioMaximo   (Utils.toDouble(results[2]));
		totales.setSalarioPromedio (Utils.toDouble(results[3]));
		totales.setExperienciaAnios(Utils.toInt   (results[4]));
		totales.setIdNivelEstudio  (Utils.toInt   (results[5]));

		StringBuilder sqlList = new StringBuilder();
		sqlList.append(" SELECT * ");
		sqlList.append("   FROM ( ");
		sqlList.append(" SELECT OFER.ID_OFERTA_EMPLEO ");
		sqlList.append("   FROM OFERTA_EMPLEO OFER, ");
		sqlList.append("        (SELECT * FROM OFERTA_UBICACION) DOM ");
		sqlList.append("  WHERE DOM.ID_OFERTA_EMPLEO = OFER.ID_OFERTA_EMPLEO ");
		sqlList.append("    AND DOM.ID_ENTIDAD = ?1 ");
		sqlList.append("    AND OFER.ID_OCUPACION IN ("+ in +") ");
		sqlList.append("    AND OFER.ESTATUS = ?2 ");
		sqlList.append("  ORDER BY OFER.FECHA_ALTA DESC ");
		sqlList.append("       ) OFERTAS ");
		sqlList.append(" WHERE ROWNUM <= ?3 ");
		
		Query queryList = entityManager.createNativeQuery(sqlList.toString());
		//queryList.setParameter(1, TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
		queryList.setParameter(1, idEntidad);
		queryList.setParameter(2, ESTATUS.ACTIVO.getIdOpcion());
		queryList.setParameter(3, limiteListado);

		@SuppressWarnings("unchecked")
		List<Object> rowSet = queryList.getResultList();
		
		for (Object rs : rowSet) idsOferta.add(Utils.toLong(rs));

		totales.setIdsOferta(idsOferta);
		
		return totales;
	}	
	
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List consultaTotalOfertasActivasPortalPorEntidad() {
		ArrayList results = new ArrayList();
		StringBuilder sqlList = new StringBuilder();
		
		sqlList.append("SELECT ");
		sqlList.append("NVL(O.CUENTA, 0) CUENTA, ");
		sqlList.append("F_DESC_CATALOGO(?1, CO.ID_CATALOGO_OPCION) AS ENTIDAD, ");
		sqlList.append("NVL(O.TOTAL_MASCULINO, 0) TOTAL_MASCULINO, ");
		sqlList.append("NVL(O.TOTAL_FEMENINO, 0) TOTAL_FEMENINO, ");
		sqlList.append("NVL(O.TOTAL_NOREQUERIDO, 0) TOTAL_NOREQUERIDO, ");
		sqlList.append("NVL(O.total_noespeficado, 0) total_noespeficado ");
		sqlList.append("FROM( ");
		sqlList.append("SELECT SUM(OE.NUMERO_PLAZAS) AS CUENTA, ");		
		sqlList.append("OU.ID_ENTIDAD, ");
		sqlList.append("SUM(DECODE(OE.GENERO, 1, OE.NUMERO_PLAZAS, 0)) AS total_masculino, ");				
		sqlList.append("SUM(DECODE(OE.GENERO, 2, OE.NUMERO_PLAZAS, 0)) AS total_femenino, ");		
		sqlList.append("SUM(DECODE(OE.GENERO, 3, OE.NUMERO_PLAZAS, 0)) AS total_norequerido, ");
		sqlList.append("SUM( ");
		sqlList.append("CASE ");
		sqlList.append("WHEN OE.GENERO IN (1, 2, 3) ");
		sqlList.append("THEN 0 ");
		sqlList.append("ELSE OE.NUMERO_PLAZAS ");
		sqlList.append("END ) total_noespeficado ");
		sqlList.append("FROM OFERTA_EMPLEO OE, OFERTA_UBICACION ou ");		
		sqlList.append("WHERE OE.ID_OFERTA_EMPLEO =OU.ID_OFERTA_EMPLEO ");
		//sqlList.append("AND OU.ID_TIPO_PROPIETARIO = ?2 ");		
		sqlList.append("AND (OE.ESTATUS = ?2 ");		
		sqlList.append("AND OE.FECHA_FIN         >= TRUNC(SYSDATE)) ");		
		sqlList.append("GROUP BY OU.ID_ENTIDAD ) O, CATALOGO_OPCION CO ");		
		sqlList.append("WHERE CO.ID_CATALOGO       = ?3 ");
		sqlList.append("AND CO.ID_CATALOGO_OPCION  = O.ID_ENTIDAD(+) ");
		sqlList.append("AND CO.ID_CATALOGO_OPCION != ?4 ");
		sqlList.append("ORDER BY CO.OPCION ");

		Query queryList = entityManager.createNativeQuery(sqlList.toString());
		
		queryList.setParameter(1, Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA);
		//queryList.setParameter(2, Constantes.TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());			
		queryList.setParameter(2, ESTATUS.ACTIVO.getIdOpcion());	
		queryList.setParameter(3, Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA);
		queryList.setParameter(4, Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA_NACIDO_EXTRANJERO);	
	
	
		List<Object> rowSet = queryList.getResultList();
		Iterator<Object> itRowSet = rowSet.iterator();
		int contador = 0;
		while(itRowSet.hasNext()){
			Object[] rs = (Object[])itRowSet.next();			
			long total = Utils.toLong(rs[0]);			
			long totalMasculino = Utils.toLong(rs[2]);
			long totalFemenino = Utils.toLong(rs[3]);
			long totalNoRequerido = Utils.toLong(rs[4]);
			long totalNoEspecificado = Utils.toLong(rs[5]);
			
			String entidad = Utils.toString(rs[1]);
			ArrayList arrTemp = new ArrayList();
			arrTemp.add(entidad);
			arrTemp.add(totalMasculino);
			arrTemp.add(totalFemenino);
			arrTemp.add(totalNoRequerido);
			arrTemp.add(totalNoEspecificado);
			arrTemp.add(total);			
			results.add(contador, arrTemp);
			contador++;
		}		
		return results;
	}
	

@SuppressWarnings("unchecked")
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public List<OfertasRecientesVO> obtieneOfertasRecientes(int tipoConsulta) throws PersistenceException {
		
		//Lista a devolver al action
		List<OfertasRecientesVO> ofertas= new ArrayList<OfertasRecientesVO>();
		
		//Lista de ofertas recientes
if(tipoConsulta==OFERTARECIENTE){
	String sqlString ="SELECT ID_OFERTA_EMPLEO, TITULO_OFERTA,UBICACION,VIGENCIA,FUENTE, SALARIO, FECHA_INICIO FROM ( SELECT OE.ID_OFERTA_EMPLEO as ID_OFERTA_EMPLEO, OE.TITULO_OFERTA as TITULO_OFERTA,F_DESC_CATALOGO(25, ou.id_entidad) || ',' || Mun.MUNICIPIO AS UBICACION,to_char(OE.FECHA_INICIO, 'DD') || ' de ' || trim(to_char(OE.FECHA_INICIO, 'Month')) || ' ' || trim(to_char(OE.FECHA_INICIO, 'yyyy')) || ' - ' || to_char(OE.FECHA_FIN, 'DD') || ' de ' || trim(to_char(OE.FECHA_FIN, 'Month')) || ' ' ||trim(to_char(OE.FECHA_FIN, 'YYYY')) as VIGENCIA, OE.FUENTE, OE.SALARIO, OE.FECHA_INICIO FROM OFERTA_EMPLEO OE LEFT JOIN OFERTA_UBICACION ou ON oe.ID_OFERTA_EMPLEO = ou.ID_OFERTA_EMPLEO LEFT JOIN MUNICIPIO mun ON ou.id_municipio = mun.id_municipio AND ou.id_entidad = mun.id_entidad where OE.ESTATUS = 1 and ou.ID_OFERTA_EMPLEO=oe.ID_OFERTA_EMPLEO and OE.FUENTE < 3 AND OE.ESTATUS NOT IN (2,15) AND OE.FECHA_FIN > SYSDATE ORDER BY oe.FECHA_INICIO DESC, oe.id_OFERTA_EMPLEO DESC ) WHERE ROWNUM <= 6 ORDER BY FECHA_INICIO DESC";
	Query query = entityManager.createNativeQuery(sqlString.toString());
		
	List<Object[]> rowSet = query.getResultList();
	
	for (Object[] rs : rowSet) {
		OfertasRecientesVO vo = new OfertasRecientesVO();
		vo.setIdOfertaEmpleo(Utils.toInt(rs[0]));
		vo.setTituloOferta(Utils.toString(rs[1]));
		vo.setUbicacion(Utils.toString(rs[2]));
		vo.setVigencia(Utils.toString(rs[3]));
		ofertas.add(vo);
		}  
	//Lista de ofertas destacadas
}else if(tipoConsulta==OFERTADESTACADA){
	String sqlString="SELECT ID_OFERTA_EMPLEO ,TITULO_OFERTA,UBICACION,FINI || ' - ' || FFIN AS VIGENCIA FROM(SELECT OE.ID_NIVEL_ESTUDIO,OE.SALARIO,OE.ID_OFERTA_EMPLEO,OE.TITULO_OFERTA,CO.OPCION || ', ' || M.MUNICIPIO AS UBICACION,to_char(OE.FECHA_INICIO, 'DD') || ' de ' || rtrim(to_char(OE.FECHA_INICIO, 'Month')) || ' ' || trim(to_char(OE.FECHA_INICIO, 'yyyy')) FINI ,to_char(OE.FECHA_FIN, 'DD') || ' de ' ||  rtrim(to_char(OE.FECHA_FIN, 'Month')) || ' ' || trim(to_char(OE.FECHA_FIN, 'yyyy')) FFIN, CASE WHEN ((ID_NIVEL_ESTUDIO=" + GRADO_ESTUDIOS_DOCTORADO + ") AND (SALARIO>"+SALARIO_MINIMO_DOCTORADO +")) THEN 'SI'  WHEN ((ID_NIVEL_ESTUDIO=" + GRADO_ESTUDIOS_MAESTRIA + ") AND (SALARIO>"+SALARIO_MINIMO_MAESTRIA +")) THEN 'SI' WHEN ((ID_NIVEL_ESTUDIO=" + GRADO_ESTUDIOS_LICENCIATURA + ") AND (SALARIO>"+SALARIO_MINIMO_LICENCIATURA +")) THEN 'SI' ELSE 'NO' END AS MOSTRAR FROM OFERTA_EMPLEO OE,OFERTA_UBICACION OU,CATALOGO_OPCION CO,MUNICIPIO M WHERE OU.ID_OFERTA_EMPLEO = OE.ID_OFERTA_EMPLEO AND OU.ID_ENTIDAD = M.ID_ENTIDAD AND OU.ID_MUNICIPIO = M.ID_MUNICIPIO AND OU.ID_ENTIDAD = CO.ID_CATALOGO_OPCION AND CO.ID_CATALOGO = 25 AND OE.ESTATUS = 1 ORDER BY oe.FECHA_INICIO DESC, OE.ID_NIVEL_ESTUDIO DESC, OE.SALARIO DESC, OE.ID_OFERTA_EMPLEO DESC) WHERE ROWNUM <= 6 AND MOSTRAR='SI'";

	Query query = entityManager.createNativeQuery(sqlString.toString());
	List<Object[]> rowSet = query.getResultList();
	
	for (Object[] rs : rowSet) {
		OfertasRecientesVO vo = new OfertasRecientesVO();
		vo.setIdOfertaEmpleo(Utils.toInt(rs[0]));
		vo.setTituloOferta(Utils.toString(rs[1]));
		vo.setUbicacion(Utils.toString(rs[2]));
		vo.setVigencia(Utils.toString(rs[3]));
		ofertas.add(vo);
       }
	//Lista de ofertas gendarmeria
}else if(tipoConsulta==OFERTAGENDARMERIA){
	String sqlString="SELECT ID_OFERTA_EMPLEO,TITULO_OFERTA,UBICACION,RAZON_SOCIAL,VIGENCIA,SALARIO,FECHA_INICIO FROM(SELECT OE.ID_OFERTA_EMPLEO as ID_OFERTA_EMPLEO,OE.TITULO_OFERTA as TITULO_OFERTA,e.RAZON_SOCIAL as RAZON_SOCIAL,F_DESC_CATALOGO(25, ou.id_entidad) || ', ' || Mun.MUNICIPIO AS UBICACION,to_char(OE.FECHA_INICIO, 'DD') || ' de ' || trim(to_char(OE.FECHA_INICIO, 'Month')) || ' ' || trim(to_char(OE.FECHA_INICIO, 'yyyy')) || ' - ' || to_char(OE.FECHA_FIN, 'DD') || ' de ' || trim(to_char(OE.FECHA_FIN, 'Month')) || ' ' ||trim(to_char(OE.FECHA_FIN, 'YYYY')) as VIGENCIA,OE.SALARIO,OE.FECHA_INICIO FROM OFERTA_EMPLEO OE LEFT JOIN EMPRESA e ON oe.id_empresa = e.id_empresa AND e.razon_social is not null LEFT JOIN OFERTA_UBICACION ou ON oe.ID_OFERTA_EMPLEO = ou.ID_OFERTA_EMPLEO LEFT JOIN MUNICIPIO mun ON ou.id_municipio 	 = mun.id_municipio AND ou.id_entidad = mun.id_entidad WHERE CATSEARCH(oe.TITULO_OFERTA, 'GENDARME*', null) > 0 AND OE.ESTATUS IN(1, 52, 50) AND OE.FECHA_FIN > SYSDATE ORDER BY oe.FECHA_INICIO DESC, oe.ID_OFERTA_EMPLEO DESC)WHERE ROWNUM <= 6 ORDER BY FECHA_INICIO DESC";
	Query query = entityManager.createNativeQuery(sqlString.toString());
	List<Object[]> rowSet = query.getResultList();
	
	for(Object[] rs : rowSet){
		OfertasRecientesVO vo = new OfertasRecientesVO();
		vo.setIdOfertaEmpleo(Utils.toInt(rs[0]));
		vo.setTituloOferta(Utils.toString(rs[1]));
		vo.setUbicacion(Utils.toString(rs[2]));
		vo.setEmpresa(Utils.toString(rs[3]));
		vo.setVigencia(Utils.toString(rs[4]));
		vo.setSalario(Utils.toInt(rs[5]));
		ofertas.add(vo);
	}
	//Lista de ofertas PET
}else if(tipoConsulta==OFERTAPET){
	String sqlString = "select id_oferta_empleo,titulo_oferta,ubicacion from(select oe.id_oferta_empleo as id_oferta_empleo,oe.titulo_oferta as titulo_oferta,f_desc_catalogo(25, ou.id_entidad)||','|| Mun.municipio as ubicacion from oferta_empleo oe left join oferta_ubicacion ou on oe.id_oferta_empleo = ou.id_oferta_empleo left join municipio mun on ou.id_municipio = mun.id_municipio and ou.id_entidad = mun.id_entidad where oe.id_tipo_contrato = 2) where rownum<=6";
	Query query = entityManager.createNativeQuery(sqlString.toString());
	List<Object[]> rowSet = query.getResultList();
	
	for(Object[] rs : rowSet){
		OfertasRecientesVO vo = new OfertasRecientesVO();
		vo.setIdOfertaEmpleo(Utils.toInt(rs[0]));
		vo.setTituloOferta(Utils.toString(rs[1]));
		vo.setUbicacion(Utils.toString(rs[2]));
		
		ofertas.add(vo);
	}
}
return ofertas;	
	}
	
	
	public List<OfertaEmpleoVO> obtenerOfertasVigentesEliminadasFecha(long idEmpresa, int estatus1, Date fecha1, Date fecha2) throws PersistenceException {
		
		List<OfertaEmpleoVO> ofertas = new ArrayList<OfertaEmpleoVO>();
		
		StringBuffer sqlString = new StringBuffer();
		sqlString.append("SELECT titulo_oferta, id_oferta_empleo FROM Oferta_Empleo ");
		sqlString.append(" WHERE id_Empresa=?1 ");
		sqlString.append(" AND (estatus =?2) ");
		sqlString.append(" AND (TRUNC(fecha_Fin) >= TRUNC(?3)) ");
		sqlString.append(" AND TRUNC(fecha_Modificacion) = TRUNC(?4) ");
		
		Query query = entityManager.createNativeQuery(sqlString.toString());
		query.setParameter(1, idEmpresa);
		query.setParameter(2, estatus1);
		query.setParameter(3, new Date());
		query.setParameter(4, fecha1);
		
		@SuppressWarnings("unchecked")
		List<Object[]> rowSet = query.getResultList();

		for (Object[] rs : rowSet) {
			OfertaEmpleoVO oferta = new OfertaEmpleoVO();
			
			oferta.setTituloOferta  (Utils.toString(rs[0]));
			oferta.setIdOfertaEmpleo(Utils.toLong(rs[1]));
			
			ofertas.add(oferta);
		}
	
		return ofertas;		
	}
	
	public int consultaEstatus(long idOfertaEmpleo) throws PersistenceException {
		int estatus = 0;
		try {
			OfertaEmpleo entity = entityManager.find(OfertaEmpleo.class, idOfertaEmpleo);
			
			if (entity!=null)
				estatus = entity.getEstatus();
			
		} catch (NoResultException re) {
			logger.error("No se localizo el registro de la oferta de empleo : "+ idOfertaEmpleo);
		} catch (Exception re) {
			throw new PersistenceException(re);
		}
		
		return estatus;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List consultaTotalOfertasActivasPortalPorEntidadEscolaridad() {
		

	ArrayList ofertasActivasPortalEntidadEscolaridad = new ArrayList();
	
	StringBuffer sqlString = new StringBuffer();

	sqlString = sqlString.append("SELECT ");
	sqlString = sqlString.append("F_DESC_CATALOGO(?1, CO.ID_CATALOGO_OPCION) AS ENTIDAD, ");
	sqlString = sqlString.append("NVL(O.TOTAL_SININSTRUCCION, 0) TOTAL_SININSTRUCCION, ");
	sqlString = sqlString.append("NVL(O.TOTAL_LEERESCRIBIR, 0) TOTAL_LEERESCRIBIR, ");
	sqlString = sqlString.append("NVL(O.TOTAL_PRIMARIA, 0) TOTAL_PRIMARIA, ");		
	sqlString = sqlString.append("NVL(O.TOTAL_SECUNDARIA, 0) TOTAL_SECUNDARIA, ");
	sqlString = sqlString.append("NVL(O.TOTAL_COMERCIAL, 0) TOTAL_COMERCIAL, ");
	sqlString = sqlString.append("NVL(O.TOTAL_TECNICA, 0) TOTAL_TECNICA, ");
	sqlString = sqlString.append("NVL(O.TOTAL_PROFESIONAL, 0) TOTAL_PROFESIONAL, ");
	sqlString = sqlString.append("NVL(O.TOTAL_PREPA, 0) TOTAL_PREPA, ");
	sqlString = sqlString.append("NVL(O.TOTAL_UNIVERSITARIO, 0) TOTAL_UNIVERSITARIO, ");
	sqlString = sqlString.append("NVL(O.TOTAL_LICENCIATURA, 0) TOTAL_LICENCIATURA, ");
	sqlString = sqlString.append("NVL(O.TOTAL_MAESTRIA, 0) TOTAL_MAESTRIA, ");	
	sqlString = sqlString.append("NVL(O.TOTAL_DOCTORADO, 0) TOTAL_DOCTORADO, ");
	sqlString = sqlString.append("NVL(O.TOTAL_ENTIDAD, 0) TOTAL_ENTIDAD ");
	sqlString = sqlString.append("FROM( ");
	sqlString = sqlString.append("SELECT OU.ID_ENTIDAD,");
	sqlString = sqlString.append("SUM(DECODE(OE.ID_NIVEL_ESTUDIO , 1, OE.NUMERO_PLAZAS, 0))  AS TOTAL_SININSTRUCCION, ");
	sqlString = sqlString.append("SUM(DECODE(OE.ID_NIVEL_ESTUDIO , 2, OE.NUMERO_PLAZAS, 0))  AS TOTAL_LEERESCRIBIR, ");
	sqlString = sqlString.append("SUM(DECODE(OE.ID_NIVEL_ESTUDIO , 3, OE.NUMERO_PLAZAS, 0))  AS TOTAL_PRIMARIA, ");
	sqlString = sqlString.append("SUM(DECODE(OE.ID_NIVEL_ESTUDIO , 4, OE.NUMERO_PLAZAS, 0))  AS TOTAL_SECUNDARIA, ");	
	sqlString = sqlString.append("SUM(DECODE(OE.ID_NIVEL_ESTUDIO , 5, OE.NUMERO_PLAZAS, 0))  AS TOTAL_COMERCIAL, ");
	sqlString = sqlString.append("SUM(DECODE(OE.ID_NIVEL_ESTUDIO , 6, OE.NUMERO_PLAZAS, 0))  AS TOTAL_TECNICA, ");
	sqlString = sqlString.append("SUM(DECODE(OE.ID_NIVEL_ESTUDIO , 7, OE.NUMERO_PLAZAS, 0))  AS TOTAL_PROFESIONAL, ");
	sqlString = sqlString.append("SUM(DECODE(OE.ID_NIVEL_ESTUDIO , 8, OE.NUMERO_PLAZAS, 0))  AS TOTAL_PREPA, ");
	sqlString = sqlString.append("SUM(DECODE(OE.ID_NIVEL_ESTUDIO , 9, OE.NUMERO_PLAZAS, 0))  AS TOTAL_UNIVERSITARIO, ");
	sqlString = sqlString.append("SUM(DECODE(OE.ID_NIVEL_ESTUDIO , 10, OE.NUMERO_PLAZAS, 0)) AS TOTAL_LICENCIATURA, ");
	sqlString = sqlString.append("SUM(DECODE(OE.ID_NIVEL_ESTUDIO , 11, OE.NUMERO_PLAZAS, 0)) AS TOTAL_MAESTRIA, ");
	sqlString = sqlString.append("SUM(DECODE(OE.ID_NIVEL_ESTUDIO , 12, OE.NUMERO_PLAZAS, 0)) AS TOTAL_DOCTORADO, ");
	sqlString = sqlString.append("SUM(OE.NUMERO_PLAZAS)                                      AS TOTAL_ENTIDAD ");			
	sqlString = sqlString.append("FROM OFERTA_EMPLEO OE, OFERTA_UBICACION OU ");
	sqlString = sqlString.append("WHERE OE.ID_OFERTA_EMPLEO =OU.ID_OFERTA_EMPLEO ");
	//sqlString = sqlString.append("AND OU.ID_TIPO_PROPIETARIO = ?3 ");
	sqlString = sqlString.append("AND (OE.ESTATUS =?2 ");
	sqlString = sqlString.append("AND OE.FECHA_FIN >= TRUNC(SYSDATE)) ");
	sqlString = sqlString.append("GROUP BY OU.ID_ENTIDAD ");
	sqlString = sqlString.append(") O, ");
	sqlString = sqlString.append("CATALOGO_OPCION CO ");
	sqlString = sqlString.append("WHERE CO.ID_CATALOGO = ?3 ");
	sqlString = sqlString.append("AND CO.ID_CATALOGO_OPCION = O.ID_ENTIDAD(+) ");
	sqlString = sqlString.append("AND CO.ID_CATALOGO_OPCION != ?4 ");	
	sqlString = sqlString.append("ORDER BY CO.OPCION");	
		
	Query query = entityManager.createNativeQuery(sqlString.toString());
	query.setParameter(1, Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA);
	query.setParameter(2, ESTATUS.ACTIVO.getIdOpcion());
	//query.setParameter(3, Constantes.TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
	query.setParameter(3, Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA);
	query.setParameter(4, Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA_NACIDO_EXTRANJERO);
	
	List<Object> rowSet = query.getResultList();
    
    for (Object row: rowSet) {
    	
    	Object[] rowElement = (Object[])row;
    	    	
    	ArrayList<Object> ofertasEntidad = new ArrayList<Object>();
    	ofertasEntidad.add(Utils.toString(rowElement[0])); //entidad
    	ofertasEntidad.add(Utils.toString(rowElement[1]));
    	ofertasEntidad.add(Utils.toString(rowElement[2]));
    	ofertasEntidad.add(Utils.toString(rowElement[3]));
		ofertasEntidad.add(Utils.toString(rowElement[4]));
		ofertasEntidad.add(Utils.toString(rowElement[5])); 
		ofertasEntidad.add(Utils.toString(rowElement[6]));
		ofertasEntidad.add(Utils.toString(rowElement[7])); 
		ofertasEntidad.add(Utils.toString(rowElement[8]));
		ofertasEntidad.add(Utils.toString(rowElement[9])); 
		ofertasEntidad.add(Utils.toString(rowElement[10]));
		ofertasEntidad.add(Utils.toString(rowElement[11]));		
		ofertasEntidad.add(Utils.toString(rowElement[12]));
		ofertasEntidad.add(Utils.toString(rowElement[13])); //total ofertas entidad 		
    	
		ofertasActivasPortalEntidadEscolaridad.add(ofertasEntidad);		
    }

	return ofertasActivasPortalEntidadEscolaridad;		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List consultaTotalOfertasActivasPortalPorEntidadExperiencia() {
		
	ArrayList ofertasActivasPortalEntidadExperiencia = new ArrayList();	
	
	StringBuffer sqlString = new StringBuffer();
	
	sqlString.append("SELECT ");
	sqlString.append("F_DESC_CATALOGO(?1, CO.ID_CATALOGO_OPCION) AS ENTIDAD, ");
	sqlString.append("NVL(O.TOTAL_NINGUNA, 0) TOTAL_NINGUNA, ");
	sqlString.append("NVL(O.TOTAL_1ANIO, 0) TOTAL_1ANIO, ");
	sqlString.append("NVL(O.TOTAL_1A2ANIO2, 0) TOTAL_1A2ANIO2, ");	
	sqlString.append("NVL(O.TOTAL_2A3ANIO2, 0) TOTAL_2A3ANIO2, ");
	sqlString.append("NVL(O.TOTAL_3A4ANIO2, 0) TOTAL_3A4ANIO2, ");
	sqlString.append("NVL(O.TOTAL_4A5ANIO2, 0) TOTAL_4A5ANIO2, ");
	sqlString.append("NVL(O.TOTAL_MAS5ANIO2, 0) TOTAL_MAS5ANIO2, ");
	sqlString.append("NVL(O.TOTAL_NOREQUISITO, 0) TOTAL_NOREQUISITO, ");
	sqlString.append("NVL(O.TOTAL_ENTIDAD, 0) TOTAL_ENTIDAD ");	
	sqlString.append("FROM( ");
	sqlString.append("SELECT OU.ID_ENTIDAD, ");
	sqlString.append("SUM(DECODE(OE.EXPERIENCIA_ANIOS, 1, OE.NUMERO_PLAZAS, 0)) AS TOTAL_NINGUNA, ");
	sqlString.append("SUM(DECODE(OE.EXPERIENCIA_ANIOS, 2, OE.NUMERO_PLAZAS, 0)) AS TOTAL_1ANIO, ");
	sqlString.append("SUM(DECODE(OE.EXPERIENCIA_ANIOS, 3, OE.NUMERO_PLAZAS, 0)) AS TOTAL_1A2ANIO2, ");
	sqlString.append("SUM(DECODE(OE.EXPERIENCIA_ANIOS, 4, OE.NUMERO_PLAZAS, 0)) AS TOTAL_2A3ANIO2, ");	
	sqlString.append("SUM(DECODE(OE.EXPERIENCIA_ANIOS, 5, OE.NUMERO_PLAZAS, 0)) AS TOTAL_3A4ANIO2, ");
	sqlString.append("SUM(DECODE(OE.EXPERIENCIA_ANIOS, 6, OE.NUMERO_PLAZAS, 0)) AS TOTAL_4A5ANIO2, ");
	sqlString.append("SUM(DECODE(OE.EXPERIENCIA_ANIOS, 7, OE.NUMERO_PLAZAS, 0)) AS TOTAL_MAS5ANIO2, ");
	sqlString.append("SUM(CASE ");
	sqlString.append("WHEN OE.EXPERIENCIA_ANIOS IN (1, 2, 3, 4, 5, 6, 7) ");
	sqlString.append("THEN 0 ");	
	sqlString.append("ELSE OE.NUMERO_PLAZAS ");
	sqlString.append("END) TOTAL_NOREQUISITO, ");
	sqlString.append("SUM(OE.NUMERO_PLAZAS) AS TOTAL_ENTIDAD ");
	sqlString.append("FROM OFERTA_EMPLEO OE, OFERTA_UBICACION OU ");
	sqlString.append("WHERE OE.ID_OFERTA_EMPLEO(+) =OU.ID_OFERTA_EMPLEO ");
	//sqlString.append("AND OU.ID_TIPO_PROPIETARIO = ?3 ");
	sqlString.append("AND (OE.ESTATUS = ?2 ");
	sqlString.append("AND OE.FECHA_FIN >= TRUNC(SYSDATE)) ");
	sqlString.append("GROUP BY OU.ID_ENTIDAD ");
	sqlString.append(") O, CATALOGO_OPCION CO ");
	sqlString.append("WHERE CO.ID_CATALOGO = ?3 ");
	sqlString.append("AND CO.ID_CATALOGO_OPCION = O.ID_ENTIDAD(+) ");
	sqlString.append("AND CO.ID_CATALOGO_OPCION != ?4 ");	
	sqlString.append("ORDER BY CO.OPCION ");
	
	Query query = entityManager.createNativeQuery(sqlString.toString());
	query.setParameter(1, Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA);
	query.setParameter(2, ESTATUS.ACTIVO.getIdOpcion());
	//query.setParameter(3, Constantes.TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
	query.setParameter(3, Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA);
	query.setParameter(4, Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA_NACIDO_EXTRANJERO);
	
	List<Object> rowSet = query.getResultList();
	
	 for (Object row: rowSet) {
	    	
	    	Object[] rowElement = (Object[])row;
	    	    	
	    	ArrayList<Object> ofertasEntidad = new ArrayList<Object>();
	    	ofertasEntidad.add(Utils.toString(rowElement[0])); //entidad
	    	ofertasEntidad.add(Utils.toString(rowElement[1]));
	    	ofertasEntidad.add(Utils.toString(rowElement[2]));
	    	ofertasEntidad.add(Utils.toString(rowElement[3]));
			ofertasEntidad.add(Utils.toString(rowElement[4]));
			ofertasEntidad.add(Utils.toString(rowElement[5])); 
			ofertasEntidad.add(Utils.toString(rowElement[6]));
			ofertasEntidad.add(Utils.toString(rowElement[7]));
			ofertasEntidad.add(Utils.toString(rowElement[8]));			
			ofertasEntidad.add(Utils.toString(rowElement[9])); //total ofertas entidad
	    	
			ofertasActivasPortalEntidadExperiencia.add(ofertasEntidad);
	    }
	 
	return ofertasActivasPortalEntidadExperiencia;
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public OfertaPorPerfilVO consultaOferta(long idOfertaEmpleo)throws SQLException {
		
		String jql = getQueryDetalleOfertaSMS();

		Query queryList = entityManager.createNativeQuery(jql);
		queryList.setParameter(1, idOfertaEmpleo);
		
		@SuppressWarnings("unchecked")
		List<Object[]> cachedRowSet = queryList.getResultList();
		
		for (Object[] rs : cachedRowSet) {
			
			OfertaPorPerfilVO oferta = new OfertaPorPerfilVO();
			oferta.setIdEmpresa(Utils.toLong(rs[0]));
			oferta.setIdOfertaEmpleo(Utils.toLong(rs[1]));
			oferta.setTituloOferta(Utils.toString(rs[2]));
			oferta.setUbicacion(Utils.toString(rs[3]));
			oferta.setEmpresa(Utils.toString(rs[4]));
			oferta.setSalario(Utils.toDouble(rs[5]));
			oferta.setFuente(Utils.toInt(rs[6]));
			oferta.setFunciones(Utils.toString(rs[7]));
			if (Utils.toInt(rs[8]) == EDAD_REQUISITO.SI.getIdOpcion())
				oferta.setEdad("De " + Utils.toInt(rs[9]) + " a " + Utils.toInt(rs[10]));
			
			oferta.setHorario(mx.gob.stps.portal.core.oferta.detalle.helper.Utils.getTipoEmpleo(Utils.toInt(rs[11])));
			oferta.setNumeroPlazas(Utils.toInt(rs[12]));
			
			String medioContacto = null;

			if (rs[19]!=null){
				medioContacto = "Telefono: "+Utils.toString(rs[19]);
			}

			if (rs[20]!=null&&!Utils.toString(rs[20]).equals("-")) {
				if (medioContacto!=null)
					medioContacto += " "+ "Correo: "+Utils.toString(rs[20]);
				else
					medioContacto = "Correo: "+Utils.toString(rs[20]);
			}

			oferta.setMedioContacto(medioContacto);
			
			oferta.setGradoEstudio(Utils.toString(rs[15]));
			oferta.setCarrera(Utils.toString(rs[16]));
			oferta.setOcupacion(Utils.toString(rs[17]));
			//logger.info(Utils.toString(rs[3]));
			//logger.info(oferta.toString());
			return oferta;
		}

		return null;
	}

	private String getQueryDetalleOfertaSMS() {
		StringBuilder query = new StringBuilder();
		query.append("SELECT H.ID_EMPRESA, H.ID_OFERTA_EMPLEO, H.TITULO_OFERTA, G.OPCION || ', ' || F.MUNICIPIO AS UBICACION,");
		//query.append(" CASE WHEN I.ID_TIPO_PERSONA = " + TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() + " THEN I.NOMBRE || ' ' || I.APELLIDO1 || ' ' || I.APELLIDO2");
		//query.append(" WHEN I.ID_TIPO_PERSONA = " + TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() + " THEN I.RAZON_SOCIAL");
		//query.append(" WHEN ((NVL (H.ID_TERCERA_EMPRESA,0) != 0) AND (I.ID_TIPO_PERSONA = " + TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() + ")) THEN J.NOMBRE || ' ' || J.APELLIDO1 || ' ' || J.APELLIDO2");
		//query.append(" WHEN ((NVL (H.ID_TERCERA_EMPRESA,0) != 0 ) AND (I.ID_TIPO_PERSONA = " + TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() + ")) THEN J.RAZON_SOCIAL ");
		query.append(" H.NOMBRE_EMPRESA  AS EMPRESA,");
		query.append(" H.SALARIO, H.FUENTE, H.FUNCIONES, H.EDAD_REQUISITO, H.EDAD_MINIMA, H.EDAD_MAXIMA, H.ID_TIPO_EMPLEO, H.NUMERO_PLAZAS, H.CONTACTO_TEL, H.CONTACTO_CORREO, DESCCATALOGO(1, " + CATALOGO_OPCION_GRADO_ESTUDIOS + ", H.ID_NIVEL_ESTUDIO) AS GRADO_ESTUDIO, DESCCATALOGO(2, " + CATALOGO_OPCION_GRADO_ESTUDIOS + ", H.ID_NIVEL_ESTUDIO,");
		query.append(" K.ID_CARRERA_ESPECIALIDAD), DESCCATALOGO(1, " + CATALOGO_OPCION_OCUPACION + ", H.ID_OCUPACION), I.CORREO_ELECTRONICO, ");

		//query.append(" DECODE(H.CONTACTO_TEL, "+ CONTACTO_TELEFONO.SI.getIdContactoTelefono() +" ,'Telefono:'||( ");
		query.append("        (SELECT NVL(TELEFONO.ACCESO,'') || NVL(TELEFONO.CLAVE,'') || TELEFONO.TELEFONO AS TELEFONO ");
		query.append("        FROM TELEFONO ");
		query.append("        WHERE TELEFONO.ID_TIPO_PROPIETARIO =  "+ TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
		query.append("        AND TELEFONO.ID_PROPIETARIO = H.ID_OFERTA_EMPLEO ");
		query.append("        AND TELEFONO.PRINCIPAL = "+ MULTIREGISTRO.PRINCIPAL.getIdOpcion() +" ");
		query.append("        AND ROWNUM = 1 )OFE_CONTACTO_TELEFONO, ");
		//query.append("        ), NULL ) OFE_CONTACTO_TELEFONO, ");

		//query.append(" 	      DECODE(H.CONTACTO_CORREO, "+ CONTACTO_CORREO.SI.getIdContactoCorreo()  +" ,'Correo:'|| H.CORREO_ELECTRONICO_CONTACTO , NULL) ");
		query.append(" 	      H.CORREO_ELECTRONICO_CONTACTO ");

		query.append(" FROM EMPRESA I, ");
		query.append("      OFERTA_EMPLEO H");
		//query.append(" LEFT JOIN TERCERA_EMPRESA J ON H.ID_TERCERA_EMPRESA = J.ID_TERCERA_EMPRESA");
		query.append(" LEFT JOIN OFERTA_CARRERA_ESPEC K ON H.ID_OFERTA_EMPLEO = K.ID_OFERTA_EMPLEO");
		query.append(" LEFT JOIN OFERTA_UBICACION D ON H.ID_OFERTA_EMPLEO = D.ID_OFERTA_EMPLEO ");
		query.append(" LEFT JOIN MUNICIPIO F ON D.ID_ENTIDAD = F.ID_ENTIDAD AND D.ID_MUNICIPIO = F.ID_MUNICIPIO");
		query.append(" LEFT JOIN CATALOGO_OPCION G ON D.ID_ENTIDAD = G.ID_CATALOGO_OPCION AND G.ID_CATALOGO = " + CATALOGO_OPCION_ENTIDAD_FEDERATIVA);
		query.append(" WHERE H.ID_OFERTA_EMPLEO = ? AND H.ESTATUS = " + ESTATUS.ACTIVO.getIdOpcion() + " AND K.PRINCIPAL = " + MULTIREGISTRO.PRINCIPAL.getIdOpcion() + " AND H.ID_EMPRESA = I.ID_EMPRESA");
		
		return query.toString();
	}

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ConocimientoHabilidadVO> buscarConocimientosHabilidades(long idCandidato, long idTipoConocimHabilidad) throws SQLException {
		List<ConocimientoHabilidadVO> conocHabsVO = new ArrayList<ConocimientoHabilidadVO>();

		String sql = setQueryConocimientosHabilidadesCandidato();

		Query queryList = entityManager.createNativeQuery(sql);
		queryList.setParameter(1, idCandidato);
		queryList.setParameter(2, idTipoConocimHabilidad);

		List<Object[]> cachedRowSet = queryList.getResultList();
		
		ConocimientoHabilidadVO conocHabVO = null;

		try {
			for (Object[] rs : cachedRowSet) {
				conocHabVO = new ConocimientoHabilidadVO();
				conocHabVO.setIdCandidatoConocimHabilidad (Utils.toInt(rs[0]));
				conocHabVO.setIdTipoConocimHabilidad      (Utils.toInt(rs[1]));
				conocHabVO.setConocimientoHabilidad       (Utils.toString(rs[2]));
				conocHabVO.setIdExperiencia               (Utils.toInt(rs[3]));
				conocHabVO.setExperiencia                 (Utils.toString(rs[4]));
				conocHabVO.setIdDominio                   (Utils.toInt(rs[5]));
				conocHabVO.setDominio                     (Utils.toString(rs[6]));
				conocHabVO.setDescripcion                 (Utils.toString(rs[7]));

				conocHabsVO.add(conocHabVO);
			}
		} catch (Exception e) {
			logger.error(e);
			throw new SQLException(e);
		}
		return conocHabsVO;
	}

	private String setQueryConocimientosHabilidadesCandidato() {
		StringBuffer sqlString = new StringBuffer();
		sqlString.append("SELECT cch.id_candidato_conocim_habilidad, ");
		sqlString.append("cch.id_tipo_conocim_habilidad, ");
		sqlString.append("cch.conocimiento_habilidad, ");
		sqlString.append("cch.id_experiencia, ");
		sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_EXPERIENCIA + ", cch.id_experiencia), ");
		sqlString.append("cch.id_dominio, ");
		sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_DOMINIO + ", cch.id_dominio), ");
		sqlString.append("cch.descripcion ");
		sqlString.append("FROM CANDIDATO_CONOCIM_HABILIDAD cch ");
		sqlString.append("WHERE cch.id_candidato = ? ");
		sqlString.append("AND cch.id_tipo_conocim_habilidad = ? ");
		sqlString.append("ORDER BY 1");
		return sqlString.toString();
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<OfertaFuncionVO> getOfertaFuncionList(long idOfertaEmpleo) {
		List<OfertaFuncionVO> list = new ArrayList<OfertaFuncionVO>();
		String sql = "SELECT o FROM OfertaFuncion o WHERE o.idOfertaEmpleo=:idOfertaEmpleo";
		Query query = entityManager.createQuery(sql);
		query.setParameter("idOfertaEmpleo", idOfertaEmpleo);
		@SuppressWarnings("unchecked")
		List<Object> result = query.getResultList();
		for (Object resultElement : result) {
			String function = "";
			OfertaFuncion entity = (OfertaFuncion)resultElement;
			String sb = "SELECT f.ID_FUNCION, f.FUNCION FROM FUNCIONES f WHERE f.ID_FUNCION = " + entity.getIdFuncion();
			Query queryList = entityManager.createNativeQuery(sb);
			@SuppressWarnings("unchecked")
			List<Object[]> rowSet = queryList.getResultList();
			for (Object[] rs : rowSet) {
				function = Utils.toString(rs[1]);
			}
			OfertaFuncionVO vo = new OfertaFuncionVO(entity.getIdFuncion(), function);
			vo.setFechaAlta(entity.getFechaAlta());
			vo.setIdOfertaEmpleo(entity.getIdOfertaEmpleo());
			vo.setIdOfertaFuncion(entity.getIdOfertaFuncion());
			list.add(vo);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ExpectativaLaboralVO> buscarExpecLaboral(long idCandidato) throws SQLException {
		String sql = setQueryExpecLaboralCandidato();		
		Query queryList = entityManager.createNativeQuery(sql);
		queryList.setParameter(1, idCandidato);

		List<Object[]> cachedRowSet = queryList.getResultList();

		List<ExpectativaLaboralVO> expecLaboralesVO = new ArrayList<ExpectativaLaboralVO>();
		ExpectativaLaboralVO expecLaboralVO = null;
		try {
			for (Object[] rs : cachedRowSet) {
				expecLaboralVO = new ExpectativaLaboralVO();
				expecLaboralVO.setIdExpectativaLaboral(Utils.toLong(rs[0]));
				expecLaboralVO.setIdSectorDeseado     (Utils.toLong(rs[1]));
				expecLaboralVO.setSectorDeseado       (Utils.toString(rs[2]));
				expecLaboralVO.setPuestoDeseado       (Utils.toString(rs[3]));
				expecLaboralVO.setIdAreaLaboralDeseada(Utils.toLong(rs[4]));
				expecLaboralVO.setAreaLaboralDeseada  (Utils.toString(rs[5]));
				expecLaboralVO.setIdOcupacionDeseada  (Utils.toLong(rs[6]));
				expecLaboralVO.setOcupacionDeseada    (Utils.toString(rs[7]));
				expecLaboralVO.setSalarioPretendido   (Utils.toDouble(rs[8]));
				expecLaboralVO.setIdTipoEmpleoDeseado (Utils.toLong(rs[9]));
				expecLaboralVO.setTipoEmpleoDeseado   (Utils.toString(rs[10]));
				expecLaboralVO.setIdTipoContrato      (Utils.toLong(rs[11]));
				expecLaboralVO.setTipoContrato        (Utils.toString(rs[12]));
				expecLaboralVO.setPrincipal           (Utils.toInt(rs[13]));
				
				expecLaboralesVO.add(expecLaboralVO);
			}
		} catch (Exception e) {
			logger.error(e);
			throw new SQLException(e);
		}
		return expecLaboralesVO;
	}

	private String setQueryExpecLaboralCandidato() {
		StringBuffer sqlString = new StringBuffer();
		sqlString.append("SELECT el.id_expectativa_laboral, ");
		sqlString.append("el.id_sector_deseado, ");
		sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_SUBSECTOR +", el.id_sector_deseado), ");
		sqlString.append("el.puesto_deseado, el.id_area_laboral_deseada, ");
		sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_AREA_LABORAL +", el.id_area_laboral_deseada), ");
		sqlString.append("el.id_ocupacion_deseada, ");
		sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_OCUPACION +", el.id_ocupacion_deseada), ");
		sqlString.append("el.salario_pretendido, ");
		sqlString.append("el.id_tipo_empleo_deseado, ");
		sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_TIPO_EMPLEO +", el.id_tipo_empleo_deseado), ");
		sqlString.append("el.id_tipo_contrato, ");
		sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_TIPO_CONTRATO +", el.id_tipo_contrato), el.principal ");
		sqlString.append("FROM EXPECTATIVA_LABORAL el ");
		sqlString.append("WHERE el.id_candidato = ? ");
		sqlString.append("ORDER BY 1");
		return sqlString.toString();
	}	
		
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List consultaOfertasVigentesActividadEconomica() {
		
		StringBuffer sqlString = new StringBuffer();
			
		sqlString.append("SELECT F_DESC_CATALOGO("+Constantes.CATALOGO_OPCION_TIPO_ACTIVIDAD_ECONOMICA+", E.ID_ACTIVIDAD_ECONOMICA) AS ACTIVIDAD_ECONOMICA, ");
		sqlString.append("SUM(OE.NUMERO_PLAZAS) NUM_PLAZAS ");
		sqlString.append("FROM OFERTA_EMPLEO OE, EMPRESA E ");
		sqlString.append("WHERE OE.ESTATUS = ?1 ");
		sqlString.append("AND OE.ID_EMPRESA = E.ID_EMPRESA ");
		sqlString.append("GROUP BY F_DESC_CATALOGO("+Constantes.CATALOGO_OPCION_TIPO_ACTIVIDAD_ECONOMICA+", E.ID_ACTIVIDAD_ECONOMICA) ");
		sqlString.append("ORDER BY ACTIVIDAD_ECONOMICA ");
		
		Query query = entityManager.createNativeQuery(sqlString.toString());
		query.setParameter(1, ESTATUS.ACTIVO.getIdOpcion());
		
		List ofertas = new ArrayList();		
		List rowSet = query.getResultList();
		
		for(Object row: rowSet){
			Object[] rowElement = (Object[])row;
			ArrayList<Object> ofertasActividad = new ArrayList<Object>();

			ofertasActividad.add(rowElement[0]); // actividad económica
			ofertasActividad.add(rowElement[1]); // num. ofertas
			
			ofertas.add(ofertasActividad);
		}
		return ofertas;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List consultaOfertasVigentesAreaOcupacion() {
		
		StringBuffer sqlString = new StringBuffer();
		
		sqlString.append("SELECT ");
		sqlString.append("ID_AREA_LABORAL, AREA, OCUPACION, SUM(NUMERO_PLAZAS) ");		
		sqlString.append("FROM ( ");
		sqlString.append("SELECT ");		
		sqlString.append("OE.ID_AREA_LABORAL, ");		
		sqlString.append("NVL(F_DESC_CATALOGO("+Constantes.CATALOGO_OPCION_AREA_LABORAL+", OE.ID_AREA_LABORAL), 'Otros') AS AREA, ");
		sqlString.append("NVL(F_DESC_CATALOGO("+Constantes.CATALOGO_OPCION_OCUPACION+", OE.ID_OCUPACION), 'Otros') AS OCUPACION, ");
		sqlString.append("OE.NUMERO_PLAZAS ");
		sqlString.append("FROM OFERTA_EMPLEO OE, EMPRESA E ");		
		sqlString.append("WHERE OE.ESTATUS = 1 ");
		sqlString.append("AND OE.ID_EMPRESA = E.ID_EMPRESA ");
		sqlString.append(") ");
		sqlString.append("GROUP BY ID_AREA_LABORAL, AREA, ROLLUP(OCUPACION) ");
		sqlString.append("ORDER BY 2, 3 NULLS FIRST ");

		Query query = entityManager.createNativeQuery(sqlString.toString());
		List<Object[]> rowSet = query.getResultList();

		// Recuperamos el resultadod de la query y procedemos a almacenar los registros  en un objeto de tipo CurrentOfferAreaOcupacionVO
		// de tal manera que la información esté agregada según el área laboral. Cada objeto de tipo CurrentOfferAreaOcupacionVO
		// tiene una estructura:
		// String area  					nombre del área laboral
		// int areaTotalPlazas  			número de ofertas para el área laboral
		// int areaNumOcupaciones  			número de ocupaciones que existen agrupadas bajo el área laboral
		// List<Object[]> listaOcupaciones  array de ocupaciones (pertenecientes al área laboral) y número de ofertas de cada ocupación
		
		ArrayList<CurrentOfferAreaOcupacionVO> listaOfertasAreaOcupacion = new ArrayList<CurrentOfferAreaOcupacionVO>();
		long idAreaAnterior = 0;
		long idAreaActual = 0;
		CurrentOfferAreaOcupacionVO ofertaAreaOcupacion = new CurrentOfferAreaOcupacionVO();
		int numOcupaciones = new Integer(0);
		
		for(Object[] rowElement: rowSet){

			idAreaActual = Utils.toLong(rowElement[0]);
			
			if (idAreaAnterior != idAreaActual){
				ofertaAreaOcupacion = new CurrentOfferAreaOcupacionVO();
				numOcupaciones = 0;
				listaOfertasAreaOcupacion.add(ofertaAreaOcupacion);

				ofertaAreaOcupacion.setArea(Utils.toString(rowElement[1]));									
			}
				
				String ocupacion;
				
				if (rowElement[2] == null) {
					ocupacion = "";
					ofertaAreaOcupacion.setAreaTotalPlazas(Utils.toInt(rowElement[3]));					
				} else {
					ocupacion = rowElement[2].toString();
				}
				
				if (!ocupacion.equals("")) {
					Object[] ocupacionDeUnArea = new Object[2];
					
					ocupacionDeUnArea[0] = Utils.toString(rowElement[2]);				
					ocupacionDeUnArea[1] = Utils.toInt(rowElement[3]);

					ofertaAreaOcupacion.addListaOcupaciones(ocupacionDeUnArea);

					ofertaAreaOcupacion.getListaOcupaciones().size();
					numOcupaciones++;
					ofertaAreaOcupacion.setAreaNumOcupaciones(numOcupaciones);
				}
				idAreaAnterior = idAreaActual;
		}		
		return listaOfertasAreaOcupacion;
	}	
	
	public List<OfertaEmpleoVO> obtenerOfertas(int idEntidad, int numVacantes) {

		StringBuffer select = new StringBuffer();
		select.append(" SELECT TITULO_OFERTA, ");
		select.append(" ID_OFERTA_EMPLEO, ");
		select.append(" MUNICIPIO, ");
		select.append(" FUNCIONES, ");
		select.append(" OPCION, ");
		select.append(" EDAD_MINIMA, ");
		select.append(" EDAD_MAXIMA, ");
		select.append(" SALARIO ");
		select.append(" FROM ( ");
		select.append(" 	SELECT OE.TITULO_OFERTA, OE.FUNCIONES, ");
		select.append("	CO.OPCION, M.MUNICIPIO,OE.EDAD_MINIMA, OE.EDAD_MAXIMA, ");
		select.append("	OE.SALARIO, TO_CHAR(OE.FECHA_MODIFICACION,'dd/mm/rrrr') AS VAC_FECMOD,");
		select.append(" 	OE.ID_OFERTA_EMPLEO");
		select.append("	FROM OFERTA_EMPLEO OE, CATALOGO_OPCION CO, ");
		select.append("	OFERTA_UBICACION DOM, MUNICIPIO M ");
		select.append("	WHERE OE.ID_TIPO_EMPLEO = CO.ID_CATALOGO_OPCION ");
		select.append("	AND CO.ID_CATALOGO = " + CATALOGO_OPCION_TIPO_EMPLEO);
		select.append("	AND OE.ID_OFERTA_EMPLEO = DOM.ID_OFERTA_EMPLEO");
		//select.append("	AND DOM.ID_TIPO_PROPIETARIO = " + TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
		select.append("	AND DOM.ID_ENTIDAD = M.ID_ENTIDAD");
		select.append("	AND DOM.ID_MUNICIPIO = M.ID_MUNICIPIO");
		select.append("	AND OE.ESTATUS = " + ESTATUS.ACTIVO.getIdOpcion());
		select.append(" 	AND DOM.ID_ENTIDAD = ? ");
		select.append(" 	AND OE.FECHA_MODIFICACION <= (SYSDATE - 5)");
		select.append("	ORDER BY OE.FECHA_MODIFICACION DESC, OE.ID_OFERTA_EMPLEO DESC");
		select.append("	) WHERE ROWNUM <= ?");
	
		Query query = entityManager.createNativeQuery(select.toString());
		query.setParameter(1, idEntidad);
		query.setParameter(2, numVacantes);
		
		@SuppressWarnings("unchecked")
		List<Object[]> rowSet = query.getResultList();
		
		List<OfertaEmpleoVO> ofertas = new ArrayList<OfertaEmpleoVO>();

		for (Object[] rs : rowSet) {
			OfertaEmpleoVO oferta = new OfertaEmpleoVO();
			
			oferta.setTituloOferta  (Utils.toString(rs[0]));
			oferta.setIdOfertaEmpleo(Utils.toLong(rs[1]));
			oferta.setMunicipio     (Utils.toString(rs[2]));			
			oferta.setFunciones     (Utils.toString(rs[3]));
			oferta.setTipoEmpleo    (Utils.toString(rs[4]));
			oferta.setEdadMinima    (Utils.toInt(rs[5]));
			oferta.setEdadMaxima    (Utils.toInt(rs[6]));
			oferta.setSalario	    (Utils.toDouble(rs[7]));

			ofertas.add(oferta);
		}
		
		return ofertas;
	}
	
	public List<OfertaDetalleCortoVO> consultaOfertasDescripcionCorta(List<Long> idsOfertas) {
		List<OfertaDetalleCortoVO> ofertas = new ArrayList<OfertaDetalleCortoVO>();

		if (idsOfertas==null || idsOfertas.isEmpty()) return ofertas;
		
		String ids = "";
		String orderStatement = new String(" DECODE(OE.ID_OFERTA_EMPLEO, ");
		int contador = 1;
		for (Long id : idsOfertas) {
			ids += id.longValue() +",";
			orderStatement = orderStatement + Utils.toString(id) + ", "+ contador + ", ";
			contador++;
		}
		
		orderStatement = orderStatement + "99)";

		ids = ids.substring(0, ids.length()-1);
		
		StringBuffer select = new StringBuffer();
		select.append("WITH ENTIDADES AS ( ");
		select.append("     SELECT ID_CATALOGO_OPCION AS ID_ENTIDAD, OPCION AS ENTIDAD ");
		select.append("       FROM CATALOGO_OPCION WHERE ID_CATALOGO = ?1 ");
		select.append(") ");
		select.append("SELECT OE.ID_OFERTA_EMPLEO, ");
		select.append("       OE.TITULO_OFERTA, ");
		select.append("       ENT.ID_ENTIDAD, ");
		select.append("       ENT.ENTIDAD, ");
		select.append("       MUN.ID_MUNICIPIO, ");
		select.append("       MUN.MUNICIPIO, ");
		select.append("       OE.NOMBRE_EMPRESA, CORREO_ELECTRONICO_CONTACTO ");
		select.append("  FROM OFERTA_EMPLEO OE ");
		select.append("       LEFT JOIN OFERTA_UBICACION DOM ON DOM.ID_OFERTA_EMPLEO = OE.ID_OFERTA_EMPLEO  ");
		select.append("       LEFT JOIN ENTIDADES ENT ON DOM.ID_ENTIDAD = ENT.ID_ENTIDAD ");
		select.append("       LEFT JOIN MUNICIPIO MUN ON DOM.ID_ENTIDAD = MUN.ID_ENTIDAD ");
		select.append("       AND MUN.ID_MUNICIPIO = DOM.ID_MUNICIPIO ");
		select.append(" WHERE OE.ID_OFERTA_EMPLEO IN ("+ ids +") ");
		select.append("   AND OE.ESTATUS = "+Constantes.ESTATUS.ACTIVO.getIdOpcion());
		select.append(" ORDER BY "+orderStatement+", OE.FECHA_INICIO DESC ");		

		
		Query query = entityManager.createNativeQuery(select.toString());
		query.setParameter(1, CATALOGO_OPCION_ENTIDAD_FEDERATIVA);
		//query.setParameter(2, TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
		
		@SuppressWarnings("unchecked")
		List<Object[]> rowSet = query.getResultList();

		if (rowSet!=null){
			for (Object[] rs : rowSet) {
				long idOfertaEmpleo = Utils.toLong(rs[0]);//SELECT OE.ID_OFERTA_EMPLEO,
				String tituloOferta = Utils.toString(rs[1]);//OE.TITULO_OFERTA,
				//long idEntidad = Utils.toLong(rs[2]);
				String entidad = Utils.toString(rs[3]);
				//long idMunicipio = Utils.toLong(rs[4]);
				String municipio = Utils.toString(rs[5]);
				String empNombre = Utils.toString(rs[6]);
				String correoElectronicoContacto = Utils.toString(rs[7]);

				String ubicacion = (entidad!=null?entidad:"") +" "+ (municipio!=null?","+ municipio:"");

				OfertaDetalleCortoVO oferta = new OfertaDetalleCortoVO();
				oferta.setIdOfertaEmpleo(idOfertaEmpleo);
				oferta.setTituloOferta(tituloOferta);
				oferta.setEmpresaNombre(empNombre);
				oferta.setUbicacion(ubicacion.trim());
				oferta.setCorreoElectronicoContacto(correoElectronicoContacto);
				
				ofertas.add(oferta);
			}
		}
		
		return ofertas;
	}
	/*TODO: Si no protesta la app, eliminar
	public List<OfertaDetalleCortoVO> consultaOfertasDescripcionCorta(List<Long> idsOfertas) {
		List<OfertaDetalleCortoVO> ofertas = new ArrayList<OfertaDetalleCortoVO>();

		if (idsOfertas==null || idsOfertas.isEmpty()) return ofertas;
		
		String ids = "";
		String orderStatement = new String(" DECODE(OE.ID_OFERTA_EMPLEO, ");
		int contador = 1;
		for (Long id : idsOfertas) {
			ids += id.longValue() +",";
			orderStatement = orderStatement + Utils.toString(id) + ", "+ contador + ", ";
			contador++;
		}
		
		orderStatement = orderStatement + "99)";

		ids = ids.substring(0, ids.length()-1);
		
		StringBuffer select = new StringBuffer();
		select.append("WITH ENTIDADES AS ( ");
		select.append("     SELECT ID_CATALOGO_OPCION, OPCION ");
		select.append("       FROM CATALOGO_OPCION WHERE ID_CATALOGO = ?1 ");
		select.append(") ");
		select.append("SELECT OE.ID_OFERTA_EMPLEO, ");
		select.append("       OE.TITULO_OFERTA, ");
		select.append("       ENT.ID_CATALOGO_OPCION AS ID_ENTIDAD, ");
		select.append("       ENT.OPCION AS ENTIDAD, ");
		select.append("       MUN.ID_MUNICIPIO, ");
		select.append("       MUN.MUNICIPIO, ");
		select.append("       EMP.NOMBRE, ");
		select.append("       EMP.APELLIDO1, ");
		select.append("       EMP.APELLIDO2, ");
		select.append("       NVL(OE.NOMBRE_EMPRESA, EMP.RAZON_SOCIAL) RAZON_SOCIAL, ");
		select.append("       EMP.ID_TIPO_PERSONA ");
		select.append("  FROM OFERTA_EMPLEO OE, ");
		select.append("       EMPRESA EMP, ");
		select.append("       OFERTA_UBICACION DOM, ");
		select.append("       ENTIDADES ENT, ");
		select.append("       MUNICIPIO MUN ");
		select.append(" WHERE OE.ID_OFERTA_EMPLEO IN ("+ ids +") ");
		select.append("   AND EMP.ID_EMPRESA = OE.ID_EMPRESA ");
		select.append("   AND OE.ESTATUS = "+Constantes.ESTATUS.ACTIVO.getIdOpcion());
		select.append("   AND DOM.ID_OFERTA_EMPLEO = OE.ID_OFERTA_EMPLEO ");
		//select.append("   AND DOM.ID_TIPO_PROPIETARIO = ?2 ");
		select.append("   AND ENT.ID_CATALOGO_OPCION = DOM.ID_ENTIDAD ");
		select.append("   AND MUN.ID_ENTIDAD = DOM.ID_ENTIDAD ");
		select.append("   AND MUN.ID_MUNICIPIO = DOM.ID_MUNICIPIO ");
		select.append(" ORDER BY "+orderStatement+", OE.FECHA_INICIO DESC ");		

		
		Query query = entityManager.createNativeQuery(select.toString());
		query.setParameter(1, CATALOGO_OPCION_ENTIDAD_FEDERATIVA);
		//query.setParameter(2, TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
		
		@SuppressWarnings("unchecked")
		List<Object[]> rowSet = query.getResultList();

		if (rowSet!=null){
			for (Object[] rs : rowSet) {
				long idOfertaEmpleo = Utils.toLong(rs[0]);//SELECT OE.ID_OFERTA_EMPLEO,
				String tituloOferta = Utils.toString(rs[1]);//OE.TITULO_OFERTA,
				//long idEntidad = Utils.toLong(rs[2]);
				String entidad = Utils.toString(rs[3]);
				//long idMunicipio = Utils.toLong(rs[4]);
				String municipio = Utils.toString(rs[5]);
				String empNombre = Utils.toString(rs[6]);
				String empApellido1 = Utils.toString(rs[7]);
				String empApellido2 = Utils.toString(rs[8]);
				String empRazonSocial = Utils.toString(rs[9]);
				int idTipoPersona = Utils.toInt(rs[10]);

				String empresaNombre = "";
				if (idTipoPersona == TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()){
					empresaNombre = empNombre + (empApellido1!=null? " "+ empApellido1 :"") + (empApellido2!=null? " "+ empApellido2 :"");
				} else if (idTipoPersona == TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona()){
					empresaNombre = empRazonSocial;
				}

				String ubicacion = (entidad!=null?entidad:"") +" "+ (municipio!=null?","+ municipio:"");

				OfertaDetalleCortoVO oferta = new OfertaDetalleCortoVO();
				oferta.setIdOfertaEmpleo(idOfertaEmpleo);
				oferta.setTituloOferta(tituloOferta);
				oferta.setEmpresaNombre(empresaNombre);
				oferta.setUbicacion(ubicacion.trim());
				
				ofertas.add(oferta);
			}
		}
		
		return ofertas;
	}
	*/

	@Override
	public void autorizaOfertaEmpleo(long idOfertaEmpleo, int estatus,
			Date fechaFin) {
		try {
		String jpql = "UPDATE OfertaEmpleo AS o SET o.estatus = :estatus, o.fechaModificacion = :fechaModificacion,o.fechaInicio = :fechaInicio,o.fechaFin = :fechaFin WHERE o.idOfertaEmpleo = :idOfertaEmpleo";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("estatus", estatus);
        query.setParameter("fechaModificacion", new Date(), TemporalType.TIMESTAMP);
        query.setParameter("fechaInicio", new Date(), TemporalType.TIMESTAMP);
        query.setParameter("fechaFin", fechaFin);
        query.setParameter("idOfertaEmpleo", idOfertaEmpleo);
        

        query.executeUpdate();

	} catch (NoResultException re) {
		logger.error("No se localizo el registro de la oferta de empleo : "+ idOfertaEmpleo);
	} catch (Exception re) {
		throw new PersistenceException(re);
	}
		
	}
	

	@Override
	public Long consultaNumeroOfertasActivas(long idEmpresa) {
		Long resultado = null;
		
		StringBuffer select = new StringBuffer();
		select.append("SELECT count(id_oferta_empleo) ");
		select.append(" FROM Oferta_Empleo ");
		select.append(" WHERE id_Empresa=?1 AND (estatus=?2 OR estatus=?3) ");
		
		Query query = entityManager.createNativeQuery(select.toString());
		query.setParameter(1, idEmpresa);
		query.setParameter(2, Constantes.ESTATUS.ACTIVO.getIdOpcion());
		query.setParameter(3, Constantes.ESTATUS.ABRIENDO_ESPACIOS_ACTIVO.getIdOpcion());
		
		BigDecimal resultadoTemporal = (BigDecimal)query.getSingleResult();
		
		resultado = Utils.toLong(resultadoTemporal);
		
		return resultado;
	}
	// Start Cambio movil
	/*
	@SuppressWarnings("unused")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<OfertaEmpleoJB> consultaOfertasEmpleoAsignadas(long idCandidato, ESTATUS estatus01, ESTATUS estatus02){
		List<OfertaEmpleoJB> ofertas = new ArrayList<OfertaEmpleoJB>();

		try{
			StringBuilder sql = new StringBuilder();

			sql.append("WITH ENTIDADES AS (SELECT ID_CATALOGO_OPCION AS ID_ENTIDAD, OPCION AS ENTIDAD ");
			sql.append("                     FROM CATALOGO_OPCION ");
			sql.append("                    WHERE ID_CATALOGO = "+ CATALOGO_OPCION_ENTIDAD_FEDERATIVA +"), ");

			sql.append("IDIOMAS AS (SELECT ID_CATALOGO_OPCION AS ID_IDIOMA, OPCION AS IDIOMA ");
			sql.append("              FROM CATALOGO_OPCION ");
			sql.append("             WHERE ID_CATALOGO = "+ CATALOGO_OPCION_IDIOMAS +"), ");

			sql.append("DOMINIOS AS (SELECT ID_CATALOGO_OPCION AS ID_DOMINIO, OPCION AS DOMINIO ");
			sql.append("               FROM CATALOGO_OPCION ");
			sql.append("              WHERE ID_CATALOGO = "+ CATALOGO_OPCION_DOMINIO +"), ");

			sql.append("GRADOS AS (SELECT ID_CATALOGO_OPCION AS ID_GRADO, OPCION AS GRADO_ESTUDIOS ");
			sql.append("             FROM CATALOGO_OPCION ");
			sql.append("            WHERE ID_CATALOGO = "+ CATALOGO_OPCION_GRADO_ESTUDIOS +"), ");

			sql.append("TIPO_EMPLEO AS (SELECT ID_CATALOGO_OPCION AS ID_TIPO_EMPLEO, OPCION AS TIPO_EMPLEO ");
			sql.append("                  FROM CATALOGO_OPCION ");
			sql.append("                 WHERE ID_CATALOGO = "+ CATALOGO_OPCION_TIPO_EMPLEO +"), ");

			sql.append("CARRERAS AS (SELECT ID_CATALOGO_OPCION AS ID_CARRERA, OPCION AS CARRERA ");
			sql.append("               FROM CATALOGO_OPCION ");
			sql.append("              WHERE ID_CATALOGO IN (SELECT DISTINCT ID_CATALAGO_ASOCIADO ");
			sql.append("                                      FROM CATALOGO_OPCION ");
			sql.append("                                     WHERE ID_CATALOGO = "+ CATALOGO_OPCION_GRADO_ESTUDIOS +")), ");

			sql.append("ESTATUS AS (SELECT ID_CATALOGO_OPCION AS ID_ESTATUS, OPCION AS ESTATUS ");
			sql.append("              FROM CATALOGO_OPCION ");
			sql.append("             WHERE ID_CATALOGO = "+ CATALOGO_OPCION_ESTATUS +") ");

			sql.append("SELECT OE.ID_OFERTA_EMPLEO, ");
			sql.append("       OC.ID_OFERTA_CANDIDATO, ");
			sql.append("       OE.ESTATUS, ");
			sql.append("       EST_OE.ESTATUS AS ESTATUS_OFERTA, ");
			sql.append("       OE.TITULO_OFERTA, ");
			sql.append("       ENT.ENTIDAD, ");
			sql.append("       MUN.MUNICIPIO, ");
			sql.append("       OE.NOMBRE_EMPRESA, ");
			sql.append("       OE.FECHA_FIN, ");
			sql.append("       EST_OC.ESTATUS AS ESTATUS_OFERTA_CAND, ");
			sql.append("       OE.ID_NIVEL_ESTUDIO, ");
			sql.append("       GRADOS.GRADO_ESTUDIOS, ");
			sql.append("       CARR.CARRERA, ");
			sql.append("       OE.FUNCIONES, ");
			sql.append("       OE.EDAD_REQUISITO, ");
			sql.append("       OE.EDAD_MINIMA, ");
			sql.append("       OE.EDAD_MAXIMA, ");
			sql.append("       IDIO.IDIOMA, ");
			sql.append("       D.DOMINIO, ");
			sql.append("       OE.ID_TIPO_EMPLEO, ");
			sql.append("       TIPOEMP.TIPO_EMPLEO, ");
			sql.append("       OE.NUMERO_PLAZAS, ");
			sql.append("       OE.CONTACTO_TEL, ");
			sql.append("       OE.CONTACTO_CORREO, ");
			sql.append("       OE.CORREO_ELECTRONICO_CONTACTO, ");
			sql.append("       TEL.ACCESO, ");
			sql.append("       TEL.CLAVE, ");
			sql.append("       TEL.TELEFONO, ");
			sql.append("       TEL.EXTENSION ");

			sql.append("  FROM OFERTA_CANDIDATO OC, ");
			sql.append("       OFERTA_EMPLEO OE, ");
			sql.append("       OFERTA_UBICACION DOM, ");
			sql.append("       TELEFONO TEL, ");
			sql.append("       ENTIDADES ENT, ");
			sql.append("       MUNICIPIO MUN, ");
			sql.append("       OFERTA_IDIOMA OI, ");
			sql.append("       IDIOMAS IDIO, ");
			sql.append("       DOMINIOS D, ");
			sql.append("       OFERTA_CARRERA_ESPEC OESP, ");
			sql.append("       CARRERAS CARR, ");
			sql.append("       GRADOS, ");
			sql.append("       TIPO_EMPLEO TIPOEMP, ");
			sql.append("       ESTATUS EST_OE, ");
			sql.append("       ESTATUS EST_OC ");

			sql.append(" WHERE OC.ID_CANDIDATO = "+ idCandidato +" ");
			sql.append("   AND (OC.ESTATUS = "+ estatus01.getIdOpcion() +" OR OC.ESTATUS = "+ estatus02.getIdOpcion() +") ");
			sql.append("   AND OE.ID_OFERTA_EMPLEO = OC.ID_OFERTA_EMPLEO ");

			sql.append("   AND OE.ID_OFERTA_EMPLEO = DOM.ID_OFERTA_EMPLEO(+) ");
			//sql.append("   AND DOM.ID_TIPO_PROPIETARIO(+) = "+ TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario() +" ");
			sql.append("   AND ENT.ID_ENTIDAD = DOM.ID_ENTIDAD ");
			sql.append("   AND MUN.ID_ENTIDAD = DOM.ID_ENTIDAD ");
			sql.append("   AND MUN.ID_MUNICIPIO = DOM.ID_MUNICIPIO ");

			sql.append("   AND OE.ID_OFERTA_EMPLEO = TEL.ID_PROPIETARIO(+) ");
			sql.append("   AND TEL.ID_TIPO_PROPIETARIO(+) = "+ TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario() +" ");
			sql.append("   AND TEL.PRINCIPAL(+) = "+ MULTIREGISTRO.PRINCIPAL.getIdOpcion() +" ");

			sql.append("   AND OE.ID_OFERTA_EMPLEO = OI.ID_OFERTA_EMPLEO(+) ");
			sql.append("   AND OI.PRINCIPAL(+) = "+ MULTIREGISTRO.PRINCIPAL.getIdOpcion() +" ");
			sql.append("   AND IDIO.ID_IDIOMA = OI.ID_IDIOMA ");
			sql.append("   AND OI.ID_DOMINIO = D.ID_DOMINIO(+) ");

			sql.append("   AND OE.ID_OFERTA_EMPLEO = OESP.ID_OFERTA_EMPLEO(+) ");
			sql.append("   AND OESP.PRINCIPAL(+) = "+ MULTIREGISTRO.PRINCIPAL.getIdOpcion() +" ");
			sql.append("   AND OESP.ID_CARRERA_ESPECIALIDAD = CARR.ID_CARRERA(+) ");

			sql.append("   AND OE.ID_NIVEL_ESTUDIO = GRADOS.ID_GRADO(+) ");
			sql.append("   AND OE.ID_TIPO_EMPLEO = TIPOEMP.ID_TIPO_EMPLEO(+) ");

			sql.append("   AND EST_OE.ID_ESTATUS = OE.ESTATUS ");
			sql.append("   AND EST_OC.ID_ESTATUS = OC.ESTATUS ");

			sql.append(" ORDER BY OC.FECHA_ALTA DESC ");

		    Query queryList = entityManager.createNativeQuery(sql.toString());	    

			@SuppressWarnings("unchecked")
			List<Object> rowSet = queryList.getResultList();

			if (rowSet!=null){

				for (Object rs : rowSet){
					int index = 0;
					
					OfertaEmpleoJB oferta = new OfertaEmpleoJB();
					Object[] row = (Object[])rs;

					long idOfertaEmpleo 	= Utils.toLong(row[index++]); //ID_OFERTA_EMPLEO,
					long idOfertaCandidato 	= Utils.toLong(row[index++]); //ID_OFERTA_CANDIDATO,
					long estatus        	= Utils.toLong(row[index++]); //ID_OFERTA_CANDIDATO,
					String estatusOferta 	= Utils.toString(row[index++]); //ESTATUS_OFERTA,
					String tituloOferta 	= Utils.toString(row[index++]); //TITULO_OFERTA,       
					String entidad 			= Utils.toString(row[index++]); //ENTIDAD, 
					String municipio 		= Utils.toString(row[index++]); //MUNICIPIO,
					String empresaNombre 	= Utils.toString(row[index++]); //NOMBRE_EMPRESA,
					Date fechaFin 			= Utils.toDate(row[index++]); //FECHA_FIN,
					String estatusOfertaCandidato = Utils.toString(row[index++]); //ESTATUS_OFERTA_CAND,
					long idNivelEstudio 	= Utils.toLong(row[index++]); //ID_NIVEL_ESTUDIO,
					String gradoEstudios 	= Utils.toString(row[index++]); //GRADO_ESTUDIOS,
					String carrera 			= Utils.toString(row[index++]); //CARRERA,
					String funciones 		= Utils.toString(row[index++]); //FUNCIONES,
					int edadRequisito 		= Utils.toInt(row[index++]); //EDAD_REQUISITO,
					int edadMinima 			= Utils.toInt(row[index++]); //EDAD_MINIMA,
					int edadMaxima 			= Utils.toInt(row[index++]); //EDAD_MAXIMA,
					String idiomas 			= Utils.toString(row[index++]); //IDIOMA,
					String dominio 			= Utils.toString(row[index++]); //DOMINIO,       
					long idtipoEmpleo 		= Utils.toLong(row[index++]); //ID_TIPO_EMPLEO,
					String tipoEmpleo 		= Utils.toString(row[index++]); //TIPO_EMPLEO,
					int numeroPlazas 		= Utils.toInt(row[index++]); //NUMERO_PLAZAS,
					int contactoTel 		= Utils.toInt(row[index++]); //CONTACTO_TEL,
					int contactoCorreo 		= Utils.toInt(row[index++]); //CONTACTO_CORREO,
					String correoContacto 	= Utils.toString(row[index++]); //CORREO_ELECTRONICO_CONTACTO,
					String acceso 			= Utils.toString(row[index++]); //ACCESO,
					String clave 			= Utils.toString(row[index++]); //CLAVE,
					String telefono 		= Utils.toString(row[index++]); //TELEFONO,
					String extension 		= Utils.toString(row[index++]); //EXTENSION
					
					String medioContacto = "";

					if (contactoTel==CONTACTO_TELEFONO.SI.getIdContactoTelefono() && telefono!=null && !telefono.isEmpty()){
						medioContacto += "Teléfono: "+ cadenaVacia(acceso) +" "+ cadenaVacia(clave) +" "+ cadenaVacia(telefono) + (extension==null || !extension.isEmpty()?" ":" Ext. "+ cadenaVacia(extension) +" ");
					}

					if (contactoCorreo==CONTACTO_CORREO.SI.getIdContactoCorreo() && correoContacto!=null && !correoContacto.isEmpty()){
						medioContacto += "Correo electronico :"+ cadenaVacia(correoContacto);
					}
					
					String idioma = cadenaVacia(idiomas) +" : "+ cadenaVacia(dominio);
					String ubicacion = cadenaVacia(entidad) +", "+ cadenaVacia(municipio);

					oferta.setIdOfertaEmpleo        (idOfertaEmpleo);
					oferta.setIdOfertaCandidato		(idOfertaCandidato);
					oferta.setEstatusOferta			(estatusOferta);
					oferta.setEstatus				(String.valueOf(estatus));
					oferta.setTituloOferta			(tituloOferta);
					oferta.setUbicacion				(ubicacion);
					oferta.setEmpresaNombre			(empresaNombre);
					oferta.setFechaFin				(fechaFin);
					oferta.setEstatusOfertaCandidato(estatusOfertaCandidato);
					oferta.setGradoEstudios			(gradoEstudios);
					//oferta.setespecialidades
					oferta.setCarrera				(carrera);
					oferta.setFunciones				(funciones);
					oferta.setEdadRequisito			(edadRequisito);
					oferta.setEdadMinima			(edadMinima);
					oferta.setEdadMaxima			(edadMaxima);
					oferta.setIdiomas				(idioma);
					oferta.setTipoEmpleo			(tipoEmpleo);
					oferta.setNumeroPlazas			(numeroPlazas);
					oferta.setMedioContacto			(medioContacto);
					
					ofertas.add(oferta);
				}				
			}

		} catch (NoResultException re) {
			logger.error("No se localizaron ofertas para el candidato : "+ idCandidato);
		} catch (Exception re) {
			re.printStackTrace(); logger.error(re);
			throw new PersistenceException(re);
		}

		return ofertas;
	}
	*/
	
	@SuppressWarnings("unused")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<OfertaEmpleoJB> consultaOfertasEmpleoAsignadas(long idCandidato, List<Catalogos.ESTATUS> listEstatus,int diasDifferencia){
		List<OfertaEmpleoJB> ofertas = new ArrayList<OfertaEmpleoJB>();

		try{
			StringBuilder sql = new StringBuilder();

			sql.append("WITH ENTIDADES AS (SELECT ID_CATALOGO_OPCION AS ID_ENTIDAD, OPCION AS ENTIDAD ");
			sql.append("                     FROM CATALOGO_OPCION ");
			sql.append("                    WHERE ID_CATALOGO = "+ CATALOGO_OPCION_ENTIDAD_FEDERATIVA +"), ");

			sql.append("IDIOMAS AS (SELECT ID_CATALOGO_OPCION AS ID_IDIOMA, OPCION AS IDIOMA ");
			sql.append("              FROM CATALOGO_OPCION ");
			sql.append("             WHERE ID_CATALOGO = "+ CATALOGO_OPCION_IDIOMAS +"), ");

			sql.append("DOMINIOS AS (SELECT ID_CATALOGO_OPCION AS ID_DOMINIO, OPCION AS DOMINIO ");
			sql.append("               FROM CATALOGO_OPCION ");
			sql.append("              WHERE ID_CATALOGO = "+ CATALOGO_OPCION_DOMINIO +"), ");

			sql.append("GRADOS AS (SELECT ID_CATALOGO_OPCION AS ID_GRADO, OPCION AS GRADO_ESTUDIOS ");
			sql.append("             FROM CATALOGO_OPCION ");
			sql.append("            WHERE ID_CATALOGO = "+ CATALOGO_OPCION_GRADO_ESTUDIOS +"), ");

			sql.append("TIPO_EMPLEO AS (SELECT ID_CATALOGO_OPCION AS ID_TIPO_EMPLEO, OPCION AS TIPO_EMPLEO ");
			sql.append("                  FROM CATALOGO_OPCION ");
			sql.append("                 WHERE ID_CATALOGO = "+ CATALOGO_OPCION_TIPO_EMPLEO +"), ");

			sql.append("CARRERAS AS (SELECT ID_CATALOGO_OPCION AS ID_CARRERA, OPCION AS CARRERA ");
			sql.append("               FROM CATALOGO_OPCION ");
			sql.append("              WHERE ID_CATALOGO IN (SELECT DISTINCT ID_CATALAGO_ASOCIADO ");
			sql.append("                                      FROM CATALOGO_OPCION ");
			sql.append("                                     WHERE ID_CATALOGO = "+ CATALOGO_OPCION_GRADO_ESTUDIOS +")), ");

			sql.append("ESTATUS AS (SELECT ID_CATALOGO_OPCION AS ID_ESTATUS, OPCION AS ESTATUS ");
			sql.append("              FROM CATALOGO_OPCION ");
			sql.append("             WHERE ID_CATALOGO = "+ CATALOGO_OPCION_ESTATUS +") ");

			sql.append("SELECT OE.ID_OFERTA_EMPLEO, ");
			sql.append("       OC.ID_OFERTA_CANDIDATO, ");
			sql.append("       OE.ESTATUS, ");
			sql.append("       EST_OE.ESTATUS AS ESTATUS_OFERTA, ");
			sql.append("       OE.TITULO_OFERTA, ");
			sql.append("       ENT.ENTIDAD, ");
			sql.append("       MUN.MUNICIPIO, ");
			sql.append("       OE.NOMBRE_EMPRESA, ");
			sql.append("       OE.FECHA_FIN, ");
			sql.append("       EST_OC.ESTATUS AS ESTATUS_OFERTA_CAND, ");
			sql.append("       OE.ID_NIVEL_ESTUDIO, ");
			sql.append("       GRADOS.GRADO_ESTUDIOS, ");
			sql.append("       CARR.CARRERA, ");
			sql.append("       OE.FUNCIONES, ");
			sql.append("       OE.EDAD_REQUISITO, ");
			sql.append("       OE.EDAD_MINIMA, ");
			sql.append("       OE.EDAD_MAXIMA, ");
			sql.append("       IDIO.IDIOMA, ");
			sql.append("       D.DOMINIO, ");
			sql.append("       OE.ID_TIPO_EMPLEO, ");
			sql.append("       TIPOEMP.TIPO_EMPLEO, ");
			sql.append("       OE.NUMERO_PLAZAS, ");
			sql.append("       OE.CONTACTO_TEL, ");
			sql.append("       OE.CONTACTO_CORREO, ");
			sql.append("       OE.CORREO_ELECTRONICO_CONTACTO, ");
			sql.append("       TEL.ACCESO, ");
			sql.append("       TEL.CLAVE, ");
			sql.append("       TEL.TELEFONO, ");
			sql.append("       TEL.EXTENSION, ");
			sql.append("       OC.FECHA_MODIFICACION, ");
			sql.append("       CASE WHEN SYSDATE - OC.FECHA_MODIFICACION <= "+ diasDifferencia+ " THEN 1 ELSE 0 END as DIFF ");

			sql.append("  FROM OFERTA_CANDIDATO OC, ");
			sql.append("       OFERTA_EMPLEO OE, ");
			sql.append("       OFERTA_UBICACION DOM, ");
			sql.append("       TELEFONO TEL, ");
			sql.append("       ENTIDADES ENT, ");
			sql.append("       MUNICIPIO MUN, ");
			sql.append("       OFERTA_IDIOMA OI, ");
			sql.append("       IDIOMAS IDIO, ");
			sql.append("       DOMINIOS D, ");
			sql.append("       OFERTA_CARRERA_ESPEC OESP, ");
			sql.append("       CARRERAS CARR, ");
			sql.append("       GRADOS, ");
			sql.append("       TIPO_EMPLEO TIPOEMP, ");
			sql.append("       ESTATUS EST_OE, ");
			sql.append("       ESTATUS EST_OC ");

			sql.append(" WHERE OC.ID_CANDIDATO = "+ idCandidato +" ");
			
			//sql.append("   AND (OC.ESTATUS = "+ estatus01.getIdOpcion() +" OR OC.ESTATUS = "+ estatus02.getIdOpcion() +") ");
			if(!listEstatus.isEmpty()){
			sql.append(" AND ( ");
			for(int i = 0 ; i < listEstatus.size(); i++){
				Catalogos.ESTATUS estatus = listEstatus.get(i);
				if(i>0){
					sql.append(" OR ");
				}
				sql.append(" OC.ESTATUS  = ");
				sql.append(estatus.getIdOpcion());
			}
			sql.append(" ) ");
			}
			sql.append("   AND OE.ID_OFERTA_EMPLEO = OC.ID_OFERTA_EMPLEO ");

			sql.append("   AND OE.ID_OFERTA_EMPLEO = DOM.ID_OFERTA_EMPLEO(+) ");
			//sql.append("   AND DOM.ID_TIPO_PROPIETARIO(+) = "+ TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario() +" ");
			sql.append("   AND ENT.ID_ENTIDAD = DOM.ID_ENTIDAD ");
			sql.append("   AND MUN.ID_ENTIDAD = DOM.ID_ENTIDAD ");
			sql.append("   AND MUN.ID_MUNICIPIO = DOM.ID_MUNICIPIO ");

			sql.append("   AND OE.ID_OFERTA_EMPLEO = TEL.ID_PROPIETARIO(+) ");
			sql.append("   AND TEL.ID_TIPO_PROPIETARIO(+) = "+ TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario() +" ");
			sql.append("   AND TEL.PRINCIPAL(+) = "+ MULTIREGISTRO.PRINCIPAL.getIdOpcion() +" ");

			sql.append("   AND OE.ID_OFERTA_EMPLEO = OI.ID_OFERTA_EMPLEO(+) ");
			sql.append("   AND OI.PRINCIPAL(+) = "+ MULTIREGISTRO.PRINCIPAL.getIdOpcion() +" ");
			sql.append("   AND IDIO.ID_IDIOMA = OI.ID_IDIOMA ");
			sql.append("   AND OI.ID_DOMINIO = D.ID_DOMINIO(+) ");

			sql.append("   AND OE.ID_OFERTA_EMPLEO = OESP.ID_OFERTA_EMPLEO(+) ");
			sql.append("   AND OESP.PRINCIPAL(+) = "+ MULTIREGISTRO.PRINCIPAL.getIdOpcion() +" ");
			sql.append("   AND OESP.ID_CARRERA_ESPECIALIDAD = CARR.ID_CARRERA(+) ");

			sql.append("   AND OE.ID_NIVEL_ESTUDIO = GRADOS.ID_GRADO(+) ");
			sql.append("   AND OE.ID_TIPO_EMPLEO = TIPOEMP.ID_TIPO_EMPLEO(+) ");

			sql.append("   AND EST_OE.ID_ESTATUS = OE.ESTATUS ");
			sql.append("   AND EST_OC.ID_ESTATUS = OC.ESTATUS ");

			sql.append(" ORDER BY OC.FECHA_ALTA DESC ");

		    Query queryList = entityManager.createNativeQuery(sql.toString());	    

			@SuppressWarnings("unchecked")
			List<Object> rowSet = queryList.getResultList();

			if (rowSet!=null){

				for (Object rs : rowSet){
					int index = 0;
					
					OfertaEmpleoJB oferta = new OfertaEmpleoJB();
					Object[] row = (Object[])rs;

					long idOfertaEmpleo 	= Utils.toLong(row[index++]); //ID_OFERTA_EMPLEO,
					long idOfertaCandidato 	= Utils.toLong(row[index++]); //ID_OFERTA_CANDIDATO,
					long estatus        	= Utils.toLong(row[index++]); //ID_OFERTA_CANDIDATO,
					String estatusOferta 	= Utils.toString(row[index++]); //ESTATUS_OFERTA,
					String tituloOferta 	= Utils.toString(row[index++]); //TITULO_OFERTA,       
					String entidad 			= Utils.toString(row[index++]); //ENTIDAD, 
					String municipio 		= Utils.toString(row[index++]); //MUNICIPIO,
					String empresaNombre 	= Utils.toString(row[index++]); //NOMBRE_EMPRESA,
					Date fechaFin 			= Utils.toDate(row[index++]); //FECHA_FIN,
					String estatusOfertaCandidato = Utils.toString(row[index++]); //ESTATUS_OFERTA_CAND,
					long idNivelEstudio 	= Utils.toLong(row[index++]); //ID_NIVEL_ESTUDIO,
					String gradoEstudios 	= Utils.toString(row[index++]); //GRADO_ESTUDIOS,
					String carrera 			= Utils.toString(row[index++]); //CARRERA,
					String funciones 		= Utils.toString(row[index++]); //FUNCIONES,
					int edadRequisito 		= Utils.toInt(row[index++]); //EDAD_REQUISITO,
					int edadMinima 			= Utils.toInt(row[index++]); //EDAD_MINIMA,
					int edadMaxima 			= Utils.toInt(row[index++]); //EDAD_MAXIMA,
					String idiomas 			= Utils.toString(row[index++]); //IDIOMA,
					String dominio 			= Utils.toString(row[index++]); //DOMINIO,       
					long idtipoEmpleo 		= Utils.toLong(row[index++]); //ID_TIPO_EMPLEO,
					String tipoEmpleo 		= Utils.toString(row[index++]); //TIPO_EMPLEO,
					int numeroPlazas 		= Utils.toInt(row[index++]); //NUMERO_PLAZAS,
					int contactoTel 		= Utils.toInt(row[index++]); //CONTACTO_TEL,
					int contactoCorreo 		= Utils.toInt(row[index++]); //CONTACTO_CORREO,
					String correoContacto 	= Utils.toString(row[index++]); //CORREO_ELECTRONICO_CONTACTO,
					String acceso 			= Utils.toString(row[index++]); //ACCESO,
					String clave 			= Utils.toString(row[index++]); //CLAVE,
					String telefono 		= Utils.toString(row[index++]); //TELEFONO,
					String extension 		= Utils.toString(row[index++]); //EXTENSION
					Date fechaModificacion	= Utils.toDate(row[index++]);   //FECHA_MODIFICACION DE OFERTA_CANDIDATO
					int differenciaFecha     = Utils.toInt(row[index++]);    //DIFFERNCIA DE LA FECHA_MODIFICACION 
					
					String medioContacto = "";

					if (contactoTel==CONTACTO_TELEFONO.SI.getIdContactoTelefono() && telefono!=null && !telefono.isEmpty()){
						medioContacto += "Teléfono: "+ cadenaVacia(acceso) +" "+ cadenaVacia(clave) +" "+ cadenaVacia(telefono) + (extension==null || !extension.isEmpty()?" ":" Ext. "+ cadenaVacia(extension) +" ");
					}

					if (contactoCorreo==CONTACTO_CORREO.SI.getIdContactoCorreo() && correoContacto!=null && !correoContacto.isEmpty()){
						medioContacto += "Correo electronico :"+ cadenaVacia(correoContacto);
					}
					
					String idioma = cadenaVacia(idiomas) +" : "+ cadenaVacia(dominio);
					String ubicacion = cadenaVacia(entidad) +", "+ cadenaVacia(municipio);

					oferta.setIdOfertaEmpleo        (idOfertaEmpleo);
					oferta.setIdOfertaCandidato		(idOfertaCandidato);
					oferta.setEstatusOferta			(estatusOferta);
					oferta.setEstatus				(String.valueOf(estatus));
					oferta.setEstatusEnum(Catalogos.ESTATUS.getEstatus(Integer.parseInt(String.valueOf(estatus))));
					oferta.setTituloOferta			(tituloOferta);
					oferta.setUbicacion				(ubicacion);
					oferta.setEmpresaNombre			(empresaNombre);
					oferta.setFechaFin				(fechaFin);
					oferta.setEstatusOfertaCandidato(estatusOfertaCandidato);
					oferta.setGradoEstudios			(gradoEstudios);
					//oferta.setespecialidades
					oferta.setCarrera				(carrera);
					oferta.setFunciones				(funciones);
					oferta.setEdadRequisito			(edadRequisito);
					oferta.setEdadMinima			(edadMinima);
					oferta.setEdadMaxima			(edadMaxima);
					oferta.setIdiomas				(idioma);
					oferta.setTipoEmpleo			(tipoEmpleo);
					oferta.setNumeroPlazas			(numeroPlazas);
					oferta.setMedioContacto			(medioContacto);
					oferta.setFechaModificacion		(fechaModificacion);
					oferta.setDifferenciaFecha		(differenciaFecha);
					ofertas.add(oferta);
				}				
			}

		} catch (NoResultException re) {
			logger.error("No se localizaron ofertas para el candidato : "+ idCandidato);
		} catch (Exception re) {
			re.printStackTrace(); logger.error(re);
			throw new PersistenceException(re);
		}

		return ofertas;
	}

	
	@SuppressWarnings("unused")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<OfertaEmpleoJB> consultaOfertasEmpleoAsignadas(long idCandidato, Catalogos.ESTATUS estatus01, Catalogos.ESTATUS estatus02){
		List<Catalogos.ESTATUS> listEstatus = new ArrayList<Catalogos.ESTATUS>();
		listEstatus.add(estatus01);
		listEstatus.add(estatus02);
		return consultaOfertasEmpleoAsignadas(idCandidato,listEstatus,0);

	}

	@SuppressWarnings("unused")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<OfertaEmpleoJB> consultaOfertasEmpleoAsignadas(long idCandidato, Catalogos.ESTATUS estatus) {
	
		List<Catalogos.ESTATUS> listEstatus = new ArrayList<Catalogos.ESTATUS>();
	    listEstatus.add(estatus);
	    return consultaOfertasEmpleoAsignadas(idCandidato,listEstatus,0);
	}

    @SuppressWarnings("unused")
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<OfertaEmpleoJB> misPostulaciones(long idCandidato, List<Catalogos.ESTATUS> estatusOfertaCandidatoList, List<Catalogos.ESTATUS> estatusOfertaEmpleoList, int daysAfterExpires) {

        List<OfertaEmpleoJB> ofertas = new ArrayList<OfertaEmpleoJB>();
        ofertas.add(new OfertaEmpleoJB(new HashMap<String, String>()));
        logger.info("INIT QUERY");
        try {
            StringBuilder sql = new StringBuilder();

            sql.append("WITH ENTIDADES AS (SELECT ID_CATALOGO_OPCION AS ID_ENTIDAD, OPCION AS ENTIDAD ");
            sql.append("                     FROM CATALOGO_OPCION ");
            sql.append("                    WHERE ID_CATALOGO = ").append(ConstantesGenerales.CATALOGO_OPCION_ENTIDAD_FEDERATIVA).append("), ");

            sql.append("IDIOMAS AS (SELECT ID_CATALOGO_OPCION AS ID_IDIOMA, OPCION AS IDIOMA ");
            sql.append("              FROM CATALOGO_OPCION ");
            sql.append("             WHERE ID_CATALOGO = ").append(ConstantesGenerales.CATALOGO_OPCION_IDIOMAS).append("), ");

            sql.append("DOMINIOS AS (SELECT ID_CATALOGO_OPCION AS ID_DOMINIO, OPCION AS DOMINIO ");
            sql.append("               FROM CATALOGO_OPCION ");
            sql.append("              WHERE ID_CATALOGO = ").append(ConstantesGenerales.CATALOGO_OPCION_DOMINIO).append("), ");

            sql.append("GRADOS AS (SELECT ID_CATALOGO_OPCION AS ID_GRADO, OPCION AS GRADO_ESTUDIOS ");
            sql.append("             FROM CATALOGO_OPCION ");
            sql.append("            WHERE ID_CATALOGO = ").append(ConstantesGenerales.CATALOGO_OPCION_GRADO_ESTUDIOS).append("), ");

            sql.append("TIPO_EMPLEO AS (SELECT ID_CATALOGO_OPCION AS ID_TIPO_EMPLEO, OPCION AS TIPO_EMPLEO ");
            sql.append("                  FROM CATALOGO_OPCION ");
            sql.append("                 WHERE ID_CATALOGO = ").append(ConstantesGenerales.CATALOGO_OPCION_TIPO_EMPLEO).append("), ");

            sql.append("CARRERAS AS (SELECT ID_CATALOGO_OPCION AS ID_CARRERA, OPCION AS CARRERA ");
            sql.append("               FROM CATALOGO_OPCION ");
            sql.append("              WHERE ID_CATALOGO IN (SELECT DISTINCT ID_CATALAGO_ASOCIADO ");
            sql.append("                                      FROM CATALOGO_OPCION ");
            sql.append("                                     WHERE ID_CATALOGO = ").append(ConstantesGenerales.CATALOGO_OPCION_GRADO_ESTUDIOS).append(")), ");

            sql.append("ESTATUS AS (SELECT ID_CATALOGO_OPCION AS ID_ESTATUS, OPCION AS ESTATUS ");
            sql.append("              FROM CATALOGO_OPCION ");
            sql.append("             WHERE ID_CATALOGO = ").append(ConstantesGenerales.CATALOGO_OPCION_ESTATUS).append("), ");

            sql.append("HABILIDADES AS (SELECT ID_CATALOGO_OPCION AS ID_HABILIDAD, OPCION AS HABILIDAD ");
            sql.append("              FROM CATALOGO_OPCION ");
            sql.append("             WHERE ID_CATALOGO = ").append(ConstantesGenerales.CATALOGO_OPCION_HABILIDADES).append(") ");
            ofertas.get(0).getUbicaciones().put("query", "PARTIAL QUERY: " + sql.toString());
            sql.append("SELECT OE.ID_OFERTA_EMPLEO, ");
            sql.append("       OC.ID_OFERTA_CANDIDATO, ");
            sql.append("       OE.ESTATUS, ");
            sql.append("       EST_OE.ESTATUS AS ESTATUS_OFERTA, ");
            sql.append("       OE.TITULO_OFERTA, ");
            sql.append("       ENT.ENTIDAD, ");
            sql.append("       MUN.MUNICIPIO, ");
            sql.append("       OE.NOMBRE_EMPRESA, ");
            sql.append("       OE.FECHA_FIN, ");
            sql.append("       EST_OC.ESTATUS AS ESTATUS_OFERTA_CAND, ");
            sql.append("       OE.ID_NIVEL_ESTUDIO, ");
            sql.append("       GRADOS.GRADO_ESTUDIOS, ");
            sql.append("       CARR.CARRERA, ");
            sql.append("       OE.FUNCIONES, ");
            sql.append("       OE.EDAD_REQUISITO, ");
            sql.append("       OE.EDAD_MINIMA, ");
            sql.append("       OE.EDAD_MAXIMA, ");
            sql.append("       IDIO.IDIOMA, ");
            sql.append("       D.DOMINIO, ");
            sql.append("       OE.ID_TIPO_EMPLEO, ");
            sql.append("       TIPOEMP.TIPO_EMPLEO, ");
            sql.append("       OE.NUMERO_PLAZAS, ");
            sql.append("       OE.CONTACTO_TEL, ");
            sql.append("       OE.CONTACTO_CORREO, ");
            sql.append("       OE.CONTACTO_DOMICILIO, ");
            sql.append("       OE.CORREO_ELECTRONICO_CONTACTO, ");
            sql.append("       TEL.ACCESO, ");
            sql.append("       TEL.CLAVE, ");
            sql.append("       TEL.TELEFONO, ");
            sql.append("       TEL.EXTENSION, ");
            sql.append("       OC.FECHA_MODIFICACION, ");
            ofertas.get(0).getUbicaciones().put("query", "PARTIAL QUERY: " + sql.toString());
            sql.append("       DOMICILIO.CALLE, ");
            sql.append("       DOMICILIO.NUMERO_EXTERIOR, ");
            sql.append("       DOMICILIO.NUMERO_INTERIOR, ");
            sql.append("       DOMICILIO.ENTRE_CALLE, ");
            sql.append("       DOMICILIO.Y_CALLE, ");
            sql.append("       CP.COLONIA, ");
            sql.append("       DOMICILIO.CODIGO_POSTAL, ");
            sql.append("       ENT_DOM.ENTIDAD, ");
            sql.append("       MUN_DOM.MUNICIPIO, ");
            ofertas.get(0).getUbicaciones().put("query", "PARTIAL QUERY: " + sql.toString());
            sql.append("       (SELECT LISTAGG(HB.HABILIDAD, ', ') WITHIN GROUP (ORDER BY HB.ID_HABILIDAD)");
            sql.append("       FROM OFERTA_HABILIDAD OH, HABILIDADES HB ");
            sql.append("       WHERE OH.ID_OFERTA_EMPLEO = OE.ID_OFERTA_EMPLEO ");
            sql.append("       AND OH.ID_HABILIDAD = HB.ID_HABILIDAD) AS HABILIDADES_CONCATENADAS ");
            ofertas.get(0).getUbicaciones().put("query", "PARTIAL QUERY: " + sql.toString());
            sql.append("  FROM OFERTA_CANDIDATO OC, ");
            sql.append("       OFERTA_EMPLEO OE, ");
            sql.append("       OFERTA_UBICACION, ");
            sql.append("       TELEFONO TEL, ");
            sql.append("       ENTIDADES ENT, ");
            sql.append("       MUNICIPIO MUN, ");
            sql.append("       ENTIDADES ENT_DOM, ");
            sql.append("       MUNICIPIO MUN_DOM, ");
            sql.append("       OFERTA_IDIOMA OI, ");
            sql.append("       IDIOMAS IDIO, ");
            sql.append("       DOMINIOS D, ");
            sql.append("       OFERTA_CARRERA_ESPEC OESP, ");
            sql.append("       CARRERAS CARR, ");
            sql.append("       GRADOS, ");
            sql.append("       TIPO_EMPLEO TIPOEMP, ");
            sql.append("       ESTATUS EST_OE, ");
            sql.append("       ESTATUS EST_OC, ");
            sql.append("       DOMICILIO, ");
            sql.append("       CODIGO_POSTAL CP ");
            
            sql.append(" WHERE OC.ID_CANDIDATO = "+ idCandidato +" ");
            ofertas.get(0).getUbicaciones().put("query", "PARTIAL QUERY: " + sql.toString());
            if (!estatusOfertaCandidatoList.isEmpty()) {
                sql.append(" AND ( ");
                for(int i = 0 ; i < estatusOfertaCandidatoList.size(); i++){
                    Catalogos.ESTATUS estatus = estatusOfertaCandidatoList.get(i);
                    if(i>0){
                        sql.append(" OR ");
                    }
                    sql.append(" OC.ESTATUS  = ");
                    sql.append(estatus.getIdOpcion());
                }
                sql.append(" ) ");
            }
            ofertas.get(0).getUbicaciones().put("query", "PARTIAL QUERY: " + sql.toString());
            if (!estatusOfertaEmpleoList.isEmpty()){
                sql.append(" AND ( ");
                for(int i = 0 ; i < estatusOfertaEmpleoList.size(); i++){
                    Catalogos.ESTATUS estatus = estatusOfertaEmpleoList.get(i);
                    if(i>0){
                        sql.append(" OR ");
                    }
                    sql.append(" OE.ESTATUS  = ");
                    sql.append(estatus.getIdOpcion());
                }
                sql.append(" ) ");
            }
            sql.append("   AND OE.ID_OFERTA_EMPLEO = OC.ID_OFERTA_EMPLEO ");
            ofertas.get(0).getUbicaciones().put("query", "PARTIAL QUERY: " + sql.toString());
            sql.append("   AND OE.ID_OFERTA_EMPLEO = OFERTA_UBICACION.ID_OFERTA_EMPLEO(+) ");
            sql.append("   AND ENT.ID_ENTIDAD = OFERTA_UBICACION.ID_ENTIDAD ");
            sql.append("   AND MUN.ID_ENTIDAD = OFERTA_UBICACION.ID_ENTIDAD ");
            sql.append("   AND MUN.ID_MUNICIPIO = OFERTA_UBICACION.ID_MUNICIPIO ");
            ofertas.get(0).getUbicaciones().put("query", "PARTIAL QUERY: " + sql.toString());
            sql.append("   AND OE.ID_OFERTA_EMPLEO = TEL.ID_PROPIETARIO(+) ");
            sql.append("   AND TEL.ID_TIPO_PROPIETARIO(+) = "+ TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario() +" ");
            sql.append("   AND TEL.PRINCIPAL(+) = "+ MULTIREGISTRO.PRINCIPAL.getIdOpcion() +" ");
            ofertas.get(0).getUbicaciones().put("query", "PARTIAL QUERY: " + sql.toString());
            sql.append("   AND OE.ID_OFERTA_EMPLEO = OI.ID_OFERTA_EMPLEO(+) ");
            sql.append("   AND OI.PRINCIPAL(+) = "+ MULTIREGISTRO.PRINCIPAL.getIdOpcion() +" ");
            sql.append("   AND IDIO.ID_IDIOMA = OI.ID_IDIOMA ");
            sql.append("   AND OI.ID_DOMINIO = D.ID_DOMINIO(+) ");
            ofertas.get(0).getUbicaciones().put("query", "PARTIAL QUERY: " + sql.toString());
            sql.append("   AND OE.ID_OFERTA_EMPLEO = OESP.ID_OFERTA_EMPLEO(+) ");
            sql.append("   AND OESP.PRINCIPAL(+) = "+ MULTIREGISTRO.PRINCIPAL.getIdOpcion() +" ");
            sql.append("   AND OESP.ID_CARRERA_ESPECIALIDAD = CARR.ID_CARRERA(+) ");
            ofertas.get(0).getUbicaciones().put("query", "PARTIAL QUERY: " + sql.toString());
            sql.append("   AND OE.ID_NIVEL_ESTUDIO = GRADOS.ID_GRADO(+) ");
            sql.append("   AND OE.ID_TIPO_EMPLEO = TIPOEMP.ID_TIPO_EMPLEO(+) ");
            ofertas.get(0).getUbicaciones().put("query", "PARTIAL QUERY: " + sql.toString());
            sql.append("   AND EST_OE.ID_ESTATUS = OE.ESTATUS ");
            sql.append("   AND EST_OC.ID_ESTATUS = OC.ESTATUS ");
            ofertas.get(0).getUbicaciones().put("query", "PARTIAL QUERY: " + sql.toString());
            sql.append("   AND DOMICILIO.ID_TIPO_PROPIETARIO = ").append(Catalogos.TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
            sql.append("   AND DOMICILIO.ID_PROPIETARIO = OE.ID_OFERTA_EMPLEO ");
            sql.append("   AND DOMICILIO.ID_ENTIDAD = CP.ID_ENTIDAD ");
            sql.append("   AND DOMICILIO.ID_MUNICIPIO = CP.ID_MUNICIPIO ");
            sql.append("   AND DOMICILIO.ID_COLONIA = CP.ID_COLONIA ");
            sql.append("   AND ENT_DOM.ID_ENTIDAD = DOMICILIO.ID_ENTIDAD ");
            sql.append("   AND MUN_DOM.ID_ENTIDAD = DOMICILIO.ID_ENTIDAD ");
            sql.append("   AND MUN_DOM.ID_MUNICIPIO = DOMICILIO.ID_MUNICIPIO ");
            sql.append("   AND SYSDATE - ").append(daysAfterExpires).append(" <= OE.FECHA_FIN ");
            ofertas.get(0).getUbicaciones().put("query", "PARTIAL QUERY: " + sql.toString());
            sql.append(" ORDER BY OE.FECHA_FIN DESC ");
            Query queryList = entityManager.createNativeQuery(sql.toString());
            ofertas.get(0).getUbicaciones().put("query", "CREATE QUERY: " + sql.toString());
            @SuppressWarnings("unchecked")
            List<Object> rowSet = queryList.getResultList();
            ofertas.get(0).getUbicaciones().put("query", "ROWSET QUERY: " + sql.toString());
            if (null != rowSet) {
                for (Object rs : rowSet) {
                    int index = 0;
                    OfertaEmpleoJB oferta = new OfertaEmpleoJB();
                    Object[] row = (Object[])rs;
                    ofertas.get(0).getUbicaciones().put("query", "OBJECT QUERY: " + sql.toString());
                    long idOfertaEmpleo 	= Utils.toLong(row[index++]); //ID_OFERTA_EMPLEO
                    long idOfertaCandidato 	= Utils.toLong(row[index++]); //ID_OFERTA_CANDIDATO
                    long estatus        	= Utils.toLong(row[index++]); //ESTATUS
                    String estatusOferta 	= Utils.toString(row[index++]); //ESTATUS_OFERTA
                    String tituloOferta 	= Utils.toString(row[index++]); //TITULO_OFERTA (Puesto)<-
                    String entidad 			= Utils.toString(row[index++]); //ENTIDAD (Ubicacion, entidad federativa) <-
                    String municipio 		= Utils.toString(row[index++]); //MUNICIPIO (Ubicacion, municipio o delegacion) <-
                    String empresaNombre 	= Utils.toString(row[index++]); //NOMBRE_EMPRESA (Empresa) <-
                    Date fechaFin 			= Utils.toDate(row[index++]); //FECHA_FIN (Vigencia de la oferta) <-
                    String estatusOfertaCandidato = Utils.toString(row[index++]); //ESTATUS_OFERTA_CAND (Estatus) <-
                    long idNivelEstudio 	= Utils.toLong(row[index++]); //ID_NIVEL_ESTUDIO,
                    String gradoEstudios 	= Utils.toString(row[index++]); //GRADO_ESTUDIOS,
                    String carrera 			= Utils.toString(row[index++]); //CARRERA,
                    String funciones 		= Utils.toString(row[index++]); //FUNCIONES,
                    int edadRequisito 		= Utils.toInt(row[index++]); //EDAD_REQUISITO,
                    int edadMinima 			= Utils.toInt(row[index++]); //EDAD_MINIMA,
                    int edadMaxima 			= Utils.toInt(row[index++]); //EDAD_MAXIMA,
                    String idiomas 			= Utils.toString(row[index++]); //IDIOMA,
                    String dominio 			= Utils.toString(row[index++]); //DOMINIO,
                    long idtipoEmpleo 		= Utils.toLong(row[index++]); //ID_TIPO_EMPLEO,
                    String tipoEmpleo 		= Utils.toString(row[index++]); //TIPO_EMPLEO,
                    int numeroPlazas 		= Utils.toInt(row[index++]); //NUMERO_PLAZAS,
                    int contactoTel 		= Utils.toInt(row[index++]); //CONTACTO_TEL,
                    int contactoCorreo 		= Utils.toInt(row[index++]); //CONTACTO_CORREO,
                    int contactoDomicilio   = Utils.toInt(row[index++]); //CONTACTO_DOMICILIO
                    String correoContacto 	= Utils.toString(row[index++]); //CORREO_ELECTRONICO_CONTACTO,
                    String acceso 			= Utils.toString(row[index++]); //ACCESO,
                    String clave 			= Utils.toString(row[index++]); //CLAVE,
                    String telefono 		= Utils.toString(row[index++]); //TELEFONO,
                    String extension 		= Utils.toString(row[index++]); //EXTENSION
                    Date fechaModificacion	= Utils.toDate(row[index++]);   //FECHA_MODIFICACION DE OFERTA_CANDIDATO
                    ofertas.get(0).getUbicaciones().put("query", "DOM QUERY: " + sql.toString());
                    String domicilioContactoCalle = Utils.toString(row[index++]); // DOMICILIO.CALLE
                    String domicilioContactoNumeroExterior = Utils.toString(row[index++]); // DOMICILIO.NUMERO_EXTERIOR
                    String domicilioContactoNumeroInterior = Utils.toString(row[index++]); // DOMICILIO.NUMERO_INTERIOR
                    String domicilioContactoEntreCalle = Utils.toString(row[index++]); // DOMICILIO.ENTRE_CALLE
                    String domicilioContactoEtCalle = Utils.toString(row[index++]); // DOMICILIO.Y_CALLE
                    String domicilioContactoColonia = Utils.toString(row[index++]); // CP.COLONIA
                    String domicilioContactoCodigoPostal = Utils.toString(row[index++]); // DOMICILIO.CODIGO_POSTAL
                    String domicilioContactoEntidadFederativa = Utils.toString(row[index++]); // ENT_DOM.ENTIDAD
                    String domicilioContactoMunicipio = Utils.toString(row[index++]); // MUN_DOM.MUNICIPIO
                    // Job offer skills
                    String habilidadesConcatenadas = Utils.toString(row[index++]); // HABILIDADES_CONCATENADAS
                    logger.info("SKILLS QUERY: " + sql.toString());
                    StringBuilder medioContacto = new StringBuilder("");
                    if (contactoTel == Catalogos.CONTACTO_TELEFONO.SI.getIdContactoTelefono() && telefono != null && !telefono.isEmpty()){
                        medioContacto.append(cadenaVacia(acceso));
                        medioContacto.append(cadenaVacia(clave));
                        medioContacto.append(cadenaVacia(telefono));
                        medioContacto.append(cadenaVacia(extension));
                        medioContacto.append("; ");
                    }
                    if (contactoCorreo == Catalogos.CONTACTO_CORREO.SI.getIdContactoCorreo() && correoContacto != null && !correoContacto.isEmpty()){
                        medioContacto.append(cadenaVacia(correoContacto));
                        medioContacto.append("; ");
                    }
                    if (contactoDomicilio == Catalogos.CONTACTO_DOMICILIO.SI.getIdContactoDomicilio()){
                        medioContacto.append(cadenaVacia(domicilioContactoCalle));
                        medioContacto.append((domicilioContactoCalle != null && !domicilioContactoCalle.isEmpty()) ? ", " : "");
                        medioContacto.append("número ext. ").append(cadenaVacia(domicilioContactoNumeroExterior));
                        medioContacto.append((domicilioContactoNumeroExterior != null && !domicilioContactoNumeroExterior.isEmpty()) ? ", " : "");
                        if (domicilioContactoNumeroInterior != null && !domicilioContactoNumeroInterior.isEmpty()) {
                            medioContacto.append("número int. ").append(cadenaVacia(domicilioContactoNumeroInterior));
                            medioContacto.append(", ");
                        }
                        if ((domicilioContactoEntreCalle != null && !domicilioContactoEntreCalle.isEmpty()) && (domicilioContactoEtCalle != null && !domicilioContactoEtCalle.isEmpty())) {
                            medioContacto.append("entre ").append(cadenaVacia(domicilioContactoEntreCalle));
                            medioContacto.append(" y ").append(cadenaVacia(domicilioContactoEtCalle));
                            medioContacto.append(", ");
                        }
                        medioContacto.append(cadenaVacia(domicilioContactoEntidadFederativa));
                        medioContacto.append((domicilioContactoEntidadFederativa != null && !domicilioContactoEntidadFederativa.isEmpty()) ? ", " : "");
                        medioContacto.append(cadenaVacia(domicilioContactoMunicipio));
                        medioContacto.append((domicilioContactoEntidadFederativa != null && !domicilioContactoEntidadFederativa.isEmpty()) ? ", " : "");
                        medioContacto.append(cadenaVacia(domicilioContactoColonia));
                        medioContacto.append((domicilioContactoColonia != null && !domicilioContactoColonia.isEmpty()) ? ", " : "");
                        medioContacto.append((domicilioContactoCodigoPostal != null && !domicilioContactoCodigoPostal.isEmpty()) ? "CP " : "");
                        medioContacto.append(cadenaVacia(domicilioContactoCodigoPostal));
                        medioContacto.append((domicilioContactoCodigoPostal != null && !domicilioContactoCodigoPostal.isEmpty()) ? ", " : "");
                    }
                    if (medioContacto.length() > 0)
                        medioContacto.setLength(medioContacto.length() - 2); // Remove last two chars
                    ofertas.get(0).getUbicaciones().put("query", "LANG QUERY: " + sql.toString());
                    String idioma = null;
                    if (idiomas!=null && !idiomas.equals(Catalogos.IDIOMAS.NO_REQUISITO.getOpcion()) && !idiomas.equals(Catalogos.IDIOMAS.NINGUNO.getOpcion()))
                    	idioma = String.format("%s%s%s", cadenaVacia(idiomas), (dominio != null && !dominio.isEmpty()) ? "-" : "", cadenaVacia(dominio));
                    String ubicacion = String.format("%s, %s", cadenaVacia(entidad), cadenaVacia(municipio));
                    ofertas.get(0).getUbicaciones().put("query", "LOC QUERY: " + sql.toString());
                    oferta.setIdOfertaEmpleo        (idOfertaEmpleo);
                    oferta.setIdOfertaCandidato		(idOfertaCandidato);
                    oferta.setEstatusOferta			(estatusOferta);
                    oferta.setEstatus				(String.valueOf(estatus));
                    oferta.setTituloOferta			(tituloOferta);
                    oferta.setUbicacion				(ubicacion);
                    oferta.setEmpresaNombre			(empresaNombre);
                    oferta.setFechaFin				(fechaFin);
                    oferta.setFechaVigencia         (fechaFin);
                    oferta.setEstatusOfertaCandidato(estatusOfertaCandidato);
                    oferta.setGradoEstudios			(gradoEstudios);
                    oferta.setCarrera				(carrera);
                    oferta.setFunciones				(funciones);
                    oferta.setEdadRequisito			(edadRequisito);
                    oferta.setEdadMinima			(edadMinima);
                    oferta.setEdadMaxima			(edadMaxima);
                    oferta.setIdiomas				(idioma);
                    oferta.setTipoEmpleo			(tipoEmpleo);
                    oferta.setNumeroPlazas			(numeroPlazas);
                    oferta.setMedioContacto			(medioContacto.toString());
                    oferta.setFechaModificacion		(fechaModificacion);

                    if (null != habilidadesConcatenadas && !habilidadesConcatenadas.isEmpty())
                        oferta.setHabilidadesConcatenadas(habilidadesConcatenadas);
                    ofertas.add(oferta);
                }
            }
            ofertas.get(0).getUbicaciones().put("query", sql.toString());
        } catch (NoResultException re) {
        	ofertas.get(0).getUbicaciones().put("error", re.getMessage());
            logger.error("No se localizaron ofertas para el candidato : "+ idCandidato);
        } catch (Exception re) {;
            re.printStackTrace(); logger.error(re);
            ofertas.get(0).getUbicaciones().put("error", re.getMessage());
            //throw new PersistenceException(re);
        }
        return ofertas;
    }

    
    @SuppressWarnings("unused")
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<OfertaEmpleoJB> miContratacionPpc(long idOfertaCandidato) {

        List<OfertaEmpleoJB> ofertas = new ArrayList<OfertaEmpleoJB>();

        try{
            StringBuilder sql = new StringBuilder();

            sql.append("WITH ENTIDADES AS (SELECT ID_CATALOGO_OPCION AS ID_ENTIDAD, OPCION AS ENTIDAD ");
            sql.append("                     FROM CATALOGO_OPCION ");
            sql.append("                    WHERE ID_CATALOGO = ").append(ConstantesGenerales.CATALOGO_OPCION_ENTIDAD_FEDERATIVA).append("), ");

            sql.append("IDIOMAS AS (SELECT ID_CATALOGO_OPCION AS ID_IDIOMA, OPCION AS IDIOMA ");
            sql.append("              FROM CATALOGO_OPCION ");
            sql.append("             WHERE ID_CATALOGO = ").append(ConstantesGenerales.CATALOGO_OPCION_IDIOMAS).append("), ");

            sql.append("DOMINIOS AS (SELECT ID_CATALOGO_OPCION AS ID_DOMINIO, OPCION AS DOMINIO ");
            sql.append("               FROM CATALOGO_OPCION ");
            sql.append("              WHERE ID_CATALOGO = ").append(ConstantesGenerales.CATALOGO_OPCION_DOMINIO).append("), ");

            sql.append("GRADOS AS (SELECT ID_CATALOGO_OPCION AS ID_GRADO, OPCION AS GRADO_ESTUDIOS ");
            sql.append("             FROM CATALOGO_OPCION ");
            sql.append("            WHERE ID_CATALOGO = ").append(ConstantesGenerales.CATALOGO_OPCION_GRADO_ESTUDIOS).append("), ");

            sql.append("TIPO_EMPLEO AS (SELECT ID_CATALOGO_OPCION AS ID_TIPO_EMPLEO, OPCION AS TIPO_EMPLEO ");
            sql.append("                  FROM CATALOGO_OPCION ");
            sql.append("                 WHERE ID_CATALOGO = ").append(ConstantesGenerales.CATALOGO_OPCION_TIPO_EMPLEO).append("), ");

            sql.append("CARRERAS AS (SELECT ID_CATALOGO_OPCION AS ID_CARRERA, OPCION AS CARRERA ");
            sql.append("               FROM CATALOGO_OPCION ");
            sql.append("              WHERE ID_CATALOGO IN (SELECT DISTINCT ID_CATALAGO_ASOCIADO ");
            sql.append("                                      FROM CATALOGO_OPCION ");
            sql.append("                                     WHERE ID_CATALOGO = ").append(ConstantesGenerales.CATALOGO_OPCION_GRADO_ESTUDIOS).append(")), ");

            sql.append("ESTATUS AS (SELECT ID_CATALOGO_OPCION AS ID_ESTATUS, OPCION AS ESTATUS ");
            sql.append("              FROM CATALOGO_OPCION ");
            sql.append("             WHERE ID_CATALOGO = ").append(ConstantesGenerales.CATALOGO_OPCION_ESTATUS).append("), ");

            sql.append("HABILIDADES AS (SELECT ID_CATALOGO_OPCION AS ID_HABILIDAD, OPCION AS HABILIDAD ");
            sql.append("              FROM CATALOGO_OPCION ");
            sql.append("             WHERE ID_CATALOGO = ").append(ConstantesGenerales.CATALOGO_OPCION_HABILIDADES).append(") ");

            sql.append("SELECT OE.ID_OFERTA_EMPLEO, ");
            sql.append("       OE.ESTATUS, ");
            sql.append("       EST_OE.ESTATUS AS ESTATUS_OFERTA, ");
            sql.append("       OE.TITULO_OFERTA, ");
            sql.append("       ENT.ENTIDAD, ");
            sql.append("       MUN.MUNICIPIO, ");
            sql.append("       OE.NOMBRE_EMPRESA, ");
            sql.append("       OE.FECHA_FIN, ");
            sql.append("       EST_OC.ESTATUS AS ESTATUS_OFERTA_CAND, ");
            sql.append("       OE.ID_NIVEL_ESTUDIO, ");
            sql.append("       GRADOS.GRADO_ESTUDIOS, ");
            sql.append("       CARR.CARRERA, ");
            sql.append("       OE.FUNCIONES, ");
            sql.append("       OE.EDAD_REQUISITO, ");
            sql.append("       OE.EDAD_MINIMA, ");
            sql.append("       OE.EDAD_MAXIMA, ");
            sql.append("       IDIO.IDIOMA, ");
            sql.append("       D.DOMINIO, ");
            sql.append("       OE.ID_TIPO_EMPLEO, ");
            sql.append("       TIPOEMP.TIPO_EMPLEO, ");
            sql.append("       OE.NUMERO_PLAZAS, ");
            sql.append("       OE.CONTACTO_TEL, ");
            sql.append("       OE.CONTACTO_CORREO, ");
            sql.append("       OE.CONTACTO_DOMICILIO, ");
            sql.append("       OE.CORREO_ELECTRONICO_CONTACTO, ");
            sql.append("       TEL.ACCESO, ");
            sql.append("       TEL.CLAVE, ");
            sql.append("       TEL.TELEFONO, ");
            sql.append("       TEL.EXTENSION, ");
            sql.append("       OC.FECHA_MODIFICACION, ");
            // Job offer address
            sql.append("       DOMICILIO.CALLE, ");
            sql.append("       DOMICILIO.NUMERO_EXTERIOR, ");
            sql.append("       DOMICILIO.NUMERO_INTERIOR, ");
            sql.append("       DOMICILIO.ENTRE_CALLE, ");
            sql.append("       DOMICILIO.Y_CALLE, ");
            sql.append("       CP.COLONIA, ");
            sql.append("       DOMICILIO.CODIGO_POSTAL, ");
            sql.append("       ENT_DOM.ENTIDAD, ");
            sql.append("       MUN_DOM.MUNICIPIO, ");
            // Job offer skills
            sql.append("       (SELECT LISTAGG(HB.HABILIDAD, ', ') WITHIN GROUP (ORDER BY HB.ID_HABILIDAD)");
            sql.append("       FROM OFERTA_HABILIDAD OH, HABILIDADES HB ");
            sql.append("       WHERE OH.ID_OFERTA_EMPLEO = OE.ID_OFERTA_EMPLEO ");
            sql.append("       AND OH.ID_HABILIDAD = HB.ID_HABILIDAD) AS HABILIDADES_CONCATENADAS ");

            sql.append("  FROM OFERTA_CANDIDATO OC, ");
            sql.append("       OFERTA_EMPLEO OE, ");
            sql.append("       OFERTA_UBICACION, ");
            sql.append("       TELEFONO TEL, ");
            sql.append("       ENTIDADES ENT, ");
            sql.append("       MUNICIPIO MUN, ");
            sql.append("       ENTIDADES ENT_DOM, ");
            sql.append("       MUNICIPIO MUN_DOM, ");
            sql.append("       OFERTA_IDIOMA OI, ");
            sql.append("       IDIOMAS IDIO, ");
            sql.append("       DOMINIOS D, ");
            sql.append("       OFERTA_CARRERA_ESPEC OESP, ");
            sql.append("       CARRERAS CARR, ");
            sql.append("       GRADOS, ");
            sql.append("       TIPO_EMPLEO TIPOEMP, ");
            sql.append("       ESTATUS EST_OE, ");
            sql.append("       ESTATUS EST_OC, ");
            sql.append("       DOMICILIO, ");
            sql.append("       CODIGO_POSTAL CP ");

            sql.append(" WHERE OC.ID_OFERTA_CANDIDATO = "+ idOfertaCandidato +" ");

//            if(!estatusOfertaCandidatoList.isEmpty()){
//                sql.append(" AND ( ");
//                for(int i = 0 ; i < estatusOfertaCandidatoList.size(); i++){
//                    Catalogos.ESTATUS estatus = estatusOfertaCandidatoList.get(i);
//                    if(i>0){
//                        sql.append(" OR ");
//                    }
//                    sql.append(" OC.ESTATUS  = ");
//                    sql.append(estatus.getIdOpcion());
//                }
//                sql.append(" ) ");
//            }
//            if(!estatusOfertaEmpleoList.isEmpty()){
//                sql.append(" AND ( ");
//                for(int i = 0 ; i < estatusOfertaEmpleoList.size(); i++){
//                    Catalogos.ESTATUS estatus = estatusOfertaEmpleoList.get(i);
//                    if(i>0){
//                        sql.append(" OR ");
//                    }
//                    sql.append(" OE.ESTATUS  = ");
//                    sql.append(estatus.getIdOpcion());
//                }
//                sql.append(" ) ");
//            }
            sql.append("   AND OE.ID_OFERTA_EMPLEO = OC.ID_OFERTA_EMPLEO ");

            sql.append("   AND OE.ID_OFERTA_EMPLEO = OFERTA_UBICACION.ID_OFERTA_EMPLEO(+) ");
            sql.append("   AND ENT.ID_ENTIDAD = OFERTA_UBICACION.ID_ENTIDAD ");
            sql.append("   AND MUN.ID_ENTIDAD = OFERTA_UBICACION.ID_ENTIDAD ");
            sql.append("   AND MUN.ID_MUNICIPIO = OFERTA_UBICACION.ID_MUNICIPIO ");

            sql.append("   AND OE.ID_OFERTA_EMPLEO = TEL.ID_PROPIETARIO(+) ");
            sql.append("   AND TEL.ID_TIPO_PROPIETARIO(+) = "+ TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario() +" ");
            sql.append("   AND TEL.PRINCIPAL(+) = "+ MULTIREGISTRO.PRINCIPAL.getIdOpcion() +" ");

            sql.append("   AND OE.ID_OFERTA_EMPLEO = OI.ID_OFERTA_EMPLEO(+) ");
            sql.append("   AND OI.PRINCIPAL(+) = "+ MULTIREGISTRO.PRINCIPAL.getIdOpcion() +" ");
            sql.append("   AND IDIO.ID_IDIOMA = OI.ID_IDIOMA ");
            sql.append("   AND OI.ID_DOMINIO = D.ID_DOMINIO(+) ");

            sql.append("   AND OE.ID_OFERTA_EMPLEO = OESP.ID_OFERTA_EMPLEO(+) ");
            sql.append("   AND OESP.PRINCIPAL(+) = "+ MULTIREGISTRO.PRINCIPAL.getIdOpcion() +" ");
            sql.append("   AND OESP.ID_CARRERA_ESPECIALIDAD = CARR.ID_CARRERA(+) ");

            sql.append("   AND OE.ID_NIVEL_ESTUDIO = GRADOS.ID_GRADO(+) ");
            sql.append("   AND OE.ID_TIPO_EMPLEO = TIPOEMP.ID_TIPO_EMPLEO(+) ");

            sql.append("   AND EST_OE.ID_ESTATUS = OE.ESTATUS ");
            sql.append("   AND EST_OC.ID_ESTATUS = OC.ESTATUS ");

            sql.append("   AND DOMICILIO.ID_TIPO_PROPIETARIO = ").append(Catalogos.TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
            sql.append("   AND DOMICILIO.ID_PROPIETARIO = OE.ID_OFERTA_EMPLEO ");
            sql.append("   AND DOMICILIO.ID_ENTIDAD = CP.ID_ENTIDAD ");
            sql.append("   AND DOMICILIO.ID_MUNICIPIO = CP.ID_MUNICIPIO ");
            sql.append("   AND DOMICILIO.ID_COLONIA = CP.ID_COLONIA ");
            sql.append("   AND ENT_DOM.ID_ENTIDAD = DOMICILIO.ID_ENTIDAD ");
            sql.append("   AND MUN_DOM.ID_ENTIDAD = DOMICILIO.ID_ENTIDAD ");
            sql.append("   AND MUN_DOM.ID_MUNICIPIO = DOMICILIO.ID_MUNICIPIO ");

//            sql.append("   AND SYSDATE - ").append(daysAfterExpires).append(" <= OE.FECHA_FIN ");

            sql.append(" ORDER BY OE.FECHA_FIN DESC ");

            //logger.info(sql.toString());
           

            Query queryList = entityManager.createNativeQuery(sql.toString());

            @SuppressWarnings("unchecked")
            List<Object> rowSet = queryList.getResultList();

            if (rowSet!=null){

                for (Object rs : rowSet){
                    int index = 0;

                    OfertaEmpleoJB oferta = new OfertaEmpleoJB();
                    Object[] row = (Object[])rs;

                    long idOfertaEmpleo    = Utils.toLong(row[index++]); //ID_OFERTA_EMPLEO
                    long estatus           = Utils.toLong(row[index++]); //ESTATUS
                    String estatusOferta   = Utils.toString(row[index++]); //ESTATUS_OFERTA
                    String tituloOferta    = Utils.toString(row[index++]); //TITULO_OFERTA (Puesto)<-
                    String entidad           = Utils.toString(row[index++]); //ENTIDAD (Ubicacion, entidad federativa) <-
                    String municipio      = Utils.toString(row[index++]); //MUNICIPIO (Ubicacion, municipio o delegacion) <-
                    String empresaNombre   = Utils.toString(row[index++]); //NOMBRE_EMPRESA (Empresa) <-
                    Date fechaFin        = Utils.toDate(row[index++]); //FECHA_FIN (Vigencia de la oferta) <-
                    String estatusOfertaCandidato = Utils.toString(row[index++]); //ESTATUS_OFERTA_CAND (Estatus) <-
                    long idNivelEstudio    = Utils.toLong(row[index++]); //ID_NIVEL_ESTUDIO,
                    String gradoEstudios   = Utils.toString(row[index++]); //GRADO_ESTUDIOS,
                    String carrera           = Utils.toString(row[index++]); //CARRERA,
                    String funciones      = Utils.toString(row[index++]); //FUNCIONES,
                    int edadRequisito     = Utils.toInt(row[index++]); //EDAD_REQUISITO,
                    int edadMinima           = Utils.toInt(row[index++]); //EDAD_MINIMA,
                    int edadMaxima           = Utils.toInt(row[index++]); //EDAD_MAXIMA,
                    String idiomas           = Utils.toString(row[index++]); //IDIOMA,
                    String dominio           = Utils.toString(row[index++]); //DOMINIO,
                    long idtipoEmpleo     = Utils.toLong(row[index++]); //ID_TIPO_EMPLEO,
                    String tipoEmpleo     = Utils.toString(row[index++]); //TIPO_EMPLEO,
                    int numeroPlazas      = Utils.toInt(row[index++]); //NUMERO_PLAZAS,
                    int contactoTel       = Utils.toInt(row[index++]); //CONTACTO_TEL,
                    int contactoCorreo        = Utils.toInt(row[index++]); //CONTACTO_CORREO,
                    int contactoDomicilio   = Utils.toInt(row[index++]); //CONTACTO_DOMICILIO
                    String correoContacto  = Utils.toString(row[index++]); //CORREO_ELECTRONICO_CONTACTO,
                    String acceso        = Utils.toString(row[index++]); //ACCESO,
                    String clave         = Utils.toString(row[index++]); //CLAVE,
                    String telefono       = Utils.toString(row[index++]); //TELEFONO,
                    String extension      = Utils.toString(row[index++]); //EXTENSION
                    Date fechaModificacion = Utils.toDate(row[index++]);   //FECHA_MODIFICACION DE OFERTA_CANDIDATO
                    // Job offer address
                    String domicilioContactoCalle = Utils.toString(row[index++]); // DOMICILIO.CALLE
                    String domicilioContactoNumeroExterior = Utils.toString(row[index++]); // DOMICILIO.NUMERO_EXTERIOR
                    String domicilioContactoNumeroInterior = Utils.toString(row[index++]); // DOMICILIO.NUMERO_INTERIOR
                    String domicilioContactoEntreCalle = Utils.toString(row[index++]); // DOMICILIO.ENTRE_CALLE
                    String domicilioContactoEtCalle = Utils.toString(row[index++]); // DOMICILIO.Y_CALLE
                    String domicilioContactoColonia = Utils.toString(row[index++]); // CP.COLONIA
                    String domicilioContactoCodigoPostal = Utils.toString(row[index++]); // DOMICILIO.CODIGO_POSTAL
                    String domicilioContactoEntidadFederativa = Utils.toString(row[index++]); // ENT_DOM.ENTIDAD
                    String domicilioContactoMunicipio = Utils.toString(row[index++]); // MUN_DOM.MUNICIPIO
                    // Job offer skills
                    String habilidadesConcatenadas = Utils.toString(row[index++]); // HABILIDADES_CONCATENADAS

                    StringBuilder medioContacto = new StringBuilder("");
                    if (contactoTel == Catalogos.CONTACTO_TELEFONO.SI.getIdContactoTelefono() && telefono != null && !telefono.isEmpty()){
                        medioContacto.append(cadenaVacia(acceso));
                        medioContacto.append(cadenaVacia(clave));
                        medioContacto.append(cadenaVacia(telefono));
                        medioContacto.append(cadenaVacia(extension));
                        medioContacto.append("; ");
                    }
                    if (contactoCorreo == Catalogos.CONTACTO_CORREO.SI.getIdContactoCorreo() && correoContacto != null && !correoContacto.isEmpty()){
                        medioContacto.append(cadenaVacia(correoContacto));
                        medioContacto.append("; ");
                    }
                    if (contactoDomicilio == Catalogos.CONTACTO_DOMICILIO.SI.getIdContactoDomicilio()){
                        medioContacto.append(cadenaVacia(domicilioContactoCalle));
                        medioContacto.append((domicilioContactoCalle != null && !domicilioContactoCalle.isEmpty()) ? ", " : "");
                        medioContacto.append("número ext. ").append(cadenaVacia(domicilioContactoNumeroExterior));
                        medioContacto.append((domicilioContactoNumeroExterior != null && !domicilioContactoNumeroExterior.isEmpty()) ? ", " : "");
                        if (domicilioContactoNumeroInterior != null && !domicilioContactoNumeroInterior.isEmpty()) {
                            medioContacto.append("número int. ").append(cadenaVacia(domicilioContactoNumeroInterior));
                            medioContacto.append(", ");
                        }
                        if ((domicilioContactoEntreCalle != null && !domicilioContactoEntreCalle.isEmpty()) && (domicilioContactoEtCalle != null && !domicilioContactoEtCalle.isEmpty())) {
                            medioContacto.append("entre ").append(cadenaVacia(domicilioContactoEntreCalle));
                            medioContacto.append(" y ").append(cadenaVacia(domicilioContactoEtCalle));
                            medioContacto.append(", ");
                        }
                        medioContacto.append(cadenaVacia(domicilioContactoEntidadFederativa));
                        medioContacto.append((domicilioContactoEntidadFederativa != null && !domicilioContactoEntidadFederativa.isEmpty()) ? ", " : "");
                        medioContacto.append(cadenaVacia(domicilioContactoMunicipio));
                        medioContacto.append((domicilioContactoEntidadFederativa != null && !domicilioContactoEntidadFederativa.isEmpty()) ? ", " : "");
                        medioContacto.append(cadenaVacia(domicilioContactoColonia));
                        medioContacto.append((domicilioContactoColonia != null && !domicilioContactoColonia.isEmpty()) ? ", " : "");
                        medioContacto.append((domicilioContactoCodigoPostal != null && !domicilioContactoCodigoPostal.isEmpty()) ? "CP " : "");
                        medioContacto.append(cadenaVacia(domicilioContactoCodigoPostal));
                        medioContacto.append((domicilioContactoCodigoPostal != null && !domicilioContactoCodigoPostal.isEmpty()) ? ", " : "");
                    }
                    if (medioContacto.length() > 0) {
                        medioContacto.setLength(medioContacto.length() - 2); // Remove last two chars
                    }

                    String idioma = null;
                    if (idiomas!=null && !idiomas.equals(Catalogos.IDIOMAS.NO_REQUISITO.getOpcion()) && !idiomas.equals(Catalogos.IDIOMAS.NINGUNO.getOpcion())) {
                        idioma = String.format("%s%s%s", cadenaVacia(idiomas), (dominio != null && !dominio.isEmpty()) ? "-" : "", cadenaVacia(dominio));
                    }

                    String ubicacion = String.format("%s, %s", cadenaVacia(entidad), cadenaVacia(municipio));

                    oferta.setIdOfertaEmpleo        (idOfertaEmpleo);
                    oferta.setIdOfertaCandidato       (idOfertaCandidato);
                    oferta.setEstatusOferta          (estatusOferta);
                    oferta.setEstatus           (String.valueOf(estatus));
                    oferta.setTituloOferta       (tituloOferta);
                    oferta.setUbicacion             (ubicacion);
                    oferta.setEmpresaNombre          (empresaNombre);
                    oferta.setFechaFin          (fechaFin);
                    oferta.setFechaVigencia         (fechaFin);
                    oferta.setEstatusOfertaCandidato(estatusOfertaCandidato);
                    oferta.setGradoEstudios          (gradoEstudios);
                    //oferta.setespecialidades
                    oferta.setCarrera           (carrera);
                    oferta.setFunciones             (funciones);
                    oferta.setEdadRequisito          (edadRequisito);
                    oferta.setEdadMinima         (edadMinima);
                    oferta.setEdadMaxima         (edadMaxima);
                    oferta.setIdiomas           (idioma);
                    oferta.setTipoEmpleo         (tipoEmpleo);
                    oferta.setNumeroPlazas       (numeroPlazas);
                    oferta.setMedioContacto          (medioContacto.toString());
                    oferta.setFechaModificacion       (fechaModificacion);

                    if (habilidadesConcatenadas != null && !habilidadesConcatenadas.isEmpty()) {
                        oferta.setHabilidadesConcatenadas(habilidadesConcatenadas);
                    }

                    ofertas.add(oferta);
                }
            }

        } catch (NoResultException re) {
            logger.error("No se localizaron ofertas para la OfertaCandidato : "+ idOfertaCandidato);
        } catch (Exception re) {
            re.printStackTrace(); logger.error(re);
            throw new PersistenceException(re);
        }

        return ofertas;
    }
    
    
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public int consultarOfertasContratadoPpc(long idCandidato) {

        // ===== BEGIN SQL COMMAND ===== //

        StringBuilder sqlCommand = new StringBuilder();
        StringBuilder select = new StringBuilder();
        StringBuilder from = new StringBuilder();
        StringBuilder where = new StringBuilder();

        select.append("SELECT");
        select.append("	   count(1) ");

        from.append("FROM ");
        from.append("	 CANDIDATO C, OFERTA_CANDIDATO OC, OFERTA_EMPLEO O ");

        where.append("WHERE ");
        where.append("  O.ESTATUS IN (").append(Catalogos.ESTATUS.ACTIVO.getIdOpcion()).append(",").append(Catalogos.ESTATUS.PUBLICADA_PARA_SISNE.getIdOpcion()).append(") ");
        where.append("  AND O.ID_OFERTA_EMPLEO = OC.ID_OFERTA_EMPLEO ");
        where.append("  AND C.ID_CANDIDATO = OC.ID_CANDIDATO ");
        where.append("  AND C.PPC_ESTATUS IN (").append(Catalogos.ESTATUS.ACTIVO_PPC.getIdOpcion()).append(",").append(Catalogos.ESTATUS.INACTIVO_PPC.getIdOpcion()).append(") ");
        where.append("  AND OC.ESTATUS = ").append(Catalogos.ESTATUS.CONTRATADO.getIdOpcion());
        where.append("  AND C.ID_CANDIDATO = ").append(idCandidato);

        sqlCommand.append(select).append(from).append(where);

        //logger.info(sqlCommand.toString());

        int result = 0;
        try{
            Query query = entityManager.createNativeQuery(sqlCommand.toString());
//            List<Object[]> rowSet = query.getResultList();
            result = Utils.toInt(query.getSingleResult());
        }catch(NoResultException re){
            logger.error(re);
        }catch(Exception e){
            e.printStackTrace();
            logger.error(e);
        }

        // ===== END SQL COMMAND ===== //

        return result;
    }

    
    
    
	private String cadenaVacia(String cadena){
		return cadena==null?"":cadena;
	}

	public List<Long> consultaOfertasFueraVigencia() {
		List<Long> ids = new ArrayList<Long>();
		
		try {
			StringBuilder select = new StringBuilder();
			select.append("SELECT ID_OFERTA_EMPLEO FROM oferta_empleo ");
			select.append(" WHERE TRUNC(fecha_fin) <  TO_DATE(?1) AND (estatus = ?2 or estatus = ?3 or estatus = ?4)");
 
			Query query = entityManager.createNativeQuery(select.toString());
			query.setParameter(1, Utils.getFechaFormato(new Date()));
			query.setParameter(2, ESTATUS.ACTIVO.getIdOpcion());
			query.setParameter(3, ESTATUS.ABRIENDO_ESPACIOS_ACTIVO.getIdOpcion());
			query.setParameter(4, ESTATUS.PUBLICADA_PARA_SISNE.getIdOpcion());
			
			@SuppressWarnings("unchecked")
			List<Object> rowSet = query.getResultList();
			
			for (Object rs : rowSet) {
				ids.add(Utils.toLong(rs));
			}
		} catch (NoResultException re) {
			logger.error("Ofertas fuera de vigencia no localizadas"); // No se localizaron registros
		} catch (RuntimeException re) {
			re.printStackTrace(); logger.error(re);
			throw new PersistenceException(re);
		}
		
		return ids;
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<OfertaEmpleoOutVO> consultaOfertasEmpleoDyE(String palabra,String idDiscapacidad,String idEntidad,String genero,String idNivelEst){
		
		List<OfertaEmpleoOutVO> listaVacantesEmpleo = new ArrayList<OfertaEmpleoOutVO>();
		StringBuilder queryString = new StringBuilder();
		
		queryString.append("SELECT OE.ID_OFERTA_EMPLEO,TITULO_OFERTA,FUNCIONES,OE.ID_EMPRESA,"); 
		queryString.append("OE.NOMBRE_EMPRESA,OE.ID_DISCAPACIDAD,COP.OPCION DISCAPACIDAD,");
		queryString.append("CO.OPCION ENTIDAD,MU.MUNICIPIO,OU.ID_ENTIDAD,OU.ID_MUNICIPIO,OE.GENERO,OE.ID_NIVEL_ESTUDIO,OE.ESTATUS");
		queryString.append(" FROM  OFERTA_EMPLEO OE");
		queryString.append(" INNER JOIN OFERTA_UBICACION OU ON OE.ID_OFERTA_EMPLEO = OU.ID_OFERTA_EMPLEO");
		queryString.append(" INNER JOIN CATALOGO_OPCION CO  ON OU.ID_ENTIDAD = CO.ID_CATALOGO_OPCION AND CO.ID_CATALOGO = 25");
		queryString.append(" INNER JOIN MUNICIPIO MU ON OU.ID_MUNICIPIO = MU.ID_MUNICIPIO AND OU.ID_ENTIDAD = MU.ID_ENTIDAD");
		queryString.append(" INNER JOIN CATALOGO_OPCION COP ON  OE.ID_DISCAPACIDAD = COP.ID_CATALOGO_OPCION AND COP.ID_CATALOGO = 3");
		queryString.append(" WHERE  OE.ESTATUS = 1");
		queryString.append(" AND OE.ID_DISCAPACIDAD <> 1 AND OE.ID_DISCAPACIDAD <> 4");
		if(palabra != null && !palabra.isEmpty()){
			queryString.append(" AND TRANSLATE(UPPER(TITULO_OFERTA),'ÁÉÍÓÚ','AEIOU') LIKE  TRANSLATE(UPPER('%").append(palabra).append("%'),'ÁÉÍÓÚ','AEIOU')");}
		if(idDiscapacidad != null && !idDiscapacidad.isEmpty()){
			queryString.append(" AND OE.ID_DISCAPACIDAD =").append(idDiscapacidad);}
		if(idEntidad != null && !idEntidad.isEmpty()){
			queryString.append(" AND OU.ID_ENTIDAD =").append(idEntidad);}
		if(genero != null && !genero.isEmpty()){
			queryString.append(" AND OE.GENERO =").append(genero);}
		if(idNivelEst != null && !idNivelEst.isEmpty()){
			queryString.append(" AND OE.ID_NIVEL_ESTUDIO IN (").append(idNivelEst).append(")");}
		queryString.append(" ORDER BY ID_OFERTA_EMPLEO DESC");
		
		Query query = entityManager.createNativeQuery(queryString.toString());
		
			
		Iterator<?> listaOfertasEmpleo = query.getResultList().iterator();
		
		while(listaOfertasEmpleo.hasNext()){
			OfertaEmpleoOutVO ofertaEmpleoOutVO = new OfertaEmpleoOutVO();
			Object[] ofertaEmpleo = (Object[]) listaOfertasEmpleo.next();
			ofertaEmpleoOutVO.setIdOferta(Utils.toLong(ofertaEmpleo[0]));
			ofertaEmpleoOutVO.setPuesto(ofertaEmpleo[1].toString());
			ofertaEmpleoOutVO.setDescripcion(ofertaEmpleo[2].toString());
			ofertaEmpleoOutVO.setIdEmpresa(ofertaEmpleo[3].toString());
			ofertaEmpleoOutVO.setEmpresa(ofertaEmpleo[4].toString());
			ofertaEmpleoOutVO.setIdDiscapacidad(ofertaEmpleo[5].toString());
			ofertaEmpleoOutVO.setDiscapacidad(ofertaEmpleo[6].toString());
			ofertaEmpleoOutVO.setEstado(ofertaEmpleo[7].toString());
			ofertaEmpleoOutVO.setCiudad(ofertaEmpleo[8].toString());
		
			listaVacantesEmpleo.add(ofertaEmpleoOutVO);
		}
		
		
		return listaVacantesEmpleo;
		
	}
	
	public int offersToIndex() {
		int offers = 0;

		try {
			Query query = entityManager.createNativeQuery("SELECT count(id_oferta_empleo) FROM OFERTA_EMPLEO WHERE estatus=1");
			Object result = query.getSingleResult();

			if (result!=null)
				offers = Utils.toInt(result);

		} catch (NoResultException re) {
			// No se localizaron registros
		} catch(Exception e){
			e.printStackTrace();
			logger.error(e);
		}
		
		return offers;
	}

	@SuppressWarnings("unchecked")
	public List<Long> offers(int lowerLimit, int upperLimit) {
		List<Long> ids = new ArrayList<Long>();

		try {
			String select = "SELECT id_oferta_empleo " +
					          "FROM (SELECT ROWNUM r, id_oferta_empleo FROM OFERTA_EMPLEO WHERE estatus=1) " +
					         "WHERE r > "+ lowerLimit +" AND r <= "+ upperLimit +"";
			
			Query query = entityManager.createNativeQuery(select);

			List<Object> rowSet = query.getResultList();
			
			for (Object idObj : rowSet){
				ids.add(Utils.toLong(idObj));
			}
		
		} catch (NoResultException re) {
			// No se localizaron registros
		} catch(Exception e){
			e.printStackTrace();
			logger.error(e);
		}
		
		return ids;
	}
	
	public Integer contarNumeroPlazasResultados(List<Long> indicesOfertas){
		int limiteElementosEnClausulaIN = 1000;
		Integer totalNumeroPlazas = 0;
		StringBuilder base = new StringBuilder();			
		try{
			if(null != indicesOfertas && !indicesOfertas.isEmpty()){
				if(indicesOfertas.size()<limiteElementosEnClausulaIN+1){
					base.append(" SELECT SUM(numero_plazas) as total FROM oferta_empleo WHERE id_oferta_empleo IN(");
					for(int i=0; i<indicesOfertas.size(); i++){
						if(i<indicesOfertas.size()-1){
							base.append(indicesOfertas.get(i) + ",");	
						} else {
							base.append(indicesOfertas.get(i));
						}
					}
					base.append(") ");
				} else {
					int i = 0;
					int totalCiclos = (indicesOfertas.size() + limiteElementosEnClausulaIN - 1) / limiteElementosEnClausulaIN; 				
					base.append(" SELECT SUM(total) FROM(");				
					for(int cicloActual=1; cicloActual<=totalCiclos; cicloActual++){
						base.append(" SELECT SUM(numero_plazas) as total FROM oferta_empleo WHERE id_oferta_empleo IN(");
						StringBuilder idList = new StringBuilder();	
						for(int j=0; j<limiteElementosEnClausulaIN && i<indicesOfertas.size(); j++,i++){
							idList.append(indicesOfertas.get(i) + ",");	
						}
						idList.deleteCharAt(idList.lastIndexOf(","));
						base.append(idList);
						base.append(") ");
						if(cicloActual < totalCiclos){
							base.append(" UNION ALL ");
						}
					}
					base.append(") ");					
				}			
				Query query = entityManager.createNativeQuery(base.toString());
				totalNumeroPlazas = Utils.toInt(query.getSingleResult());
			}								
		}catch(Exception e){
			logger.error(e);
		}		
		return totalNumeroPlazas;
	}

	//Inicio Cambio Movil
	
	//ordenada por fecha fin
	@SuppressWarnings("unused")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<OfertaEmpleoJB> consultaOfertasEmpleosAsignadas(long idCandidato, List<Catalogos.ESTATUS> listEstatus,int diasDifferencia){
		List<OfertaEmpleoJB> ofertas = new ArrayList<OfertaEmpleoJB>();

		try{
			StringBuilder sql = new StringBuilder();

			sql.append("WITH ENTIDADES AS (SELECT ID_CATALOGO_OPCION AS ID_ENTIDAD, OPCION AS ENTIDAD ");
			sql.append("                     FROM CATALOGO_OPCION ");
			sql.append("                    WHERE ID_CATALOGO = "+ CATALOGO_OPCION_ENTIDAD_FEDERATIVA +"), ");

			sql.append("IDIOMAS AS (SELECT ID_CATALOGO_OPCION AS ID_IDIOMA, OPCION AS IDIOMA ");
			sql.append("              FROM CATALOGO_OPCION ");
			sql.append("             WHERE ID_CATALOGO = "+ CATALOGO_OPCION_IDIOMAS +"), ");

			sql.append("DOMINIOS AS (SELECT ID_CATALOGO_OPCION AS ID_DOMINIO, OPCION AS DOMINIO ");
			sql.append("               FROM CATALOGO_OPCION ");
			sql.append("              WHERE ID_CATALOGO = "+ CATALOGO_OPCION_DOMINIO +"), ");

			sql.append("GRADOS AS (SELECT ID_CATALOGO_OPCION AS ID_GRADO, OPCION AS GRADO_ESTUDIOS ");
			sql.append("             FROM CATALOGO_OPCION ");
			sql.append("            WHERE ID_CATALOGO = "+ CATALOGO_OPCION_GRADO_ESTUDIOS +"), ");

			sql.append("TIPO_EMPLEO AS (SELECT ID_CATALOGO_OPCION AS ID_TIPO_EMPLEO, OPCION AS TIPO_EMPLEO ");
			sql.append("                  FROM CATALOGO_OPCION ");
			sql.append("                 WHERE ID_CATALOGO = "+ CATALOGO_OPCION_TIPO_EMPLEO +"), ");

			sql.append("CARRERAS AS (SELECT ID_CATALOGO_OPCION AS ID_CARRERA, OPCION AS CARRERA ");
			sql.append("               FROM CATALOGO_OPCION ");
			sql.append("              WHERE ID_CATALOGO IN (SELECT DISTINCT ID_CATALAGO_ASOCIADO ");
			sql.append("                                      FROM CATALOGO_OPCION ");
			sql.append("                                     WHERE ID_CATALOGO = "+ CATALOGO_OPCION_GRADO_ESTUDIOS +")), ");

			sql.append("ESTATUS AS (SELECT ID_CATALOGO_OPCION AS ID_ESTATUS, OPCION AS ESTATUS ");
			sql.append("              FROM CATALOGO_OPCION ");
			sql.append("             WHERE ID_CATALOGO = "+ CATALOGO_OPCION_ESTATUS +") ");

			sql.append("SELECT OE.ID_OFERTA_EMPLEO, ");
			sql.append("       OC.ID_OFERTA_CANDIDATO, ");
			sql.append("       OE.ESTATUS, ");
			sql.append("       EST_OE.ESTATUS AS ESTATUS_OFERTA, ");
			sql.append("       OE.TITULO_OFERTA, ");
			sql.append("       ENT.ENTIDAD, ");
			sql.append("       MUN.MUNICIPIO, ");
			sql.append("       OE.NOMBRE_EMPRESA, ");
			sql.append("       OE.FECHA_FIN, ");
			sql.append("       EST_OC.ESTATUS AS ESTATUS_OFERTA_CAND, ");
			sql.append("       OE.ID_NIVEL_ESTUDIO, ");
			sql.append("       GRADOS.GRADO_ESTUDIOS, ");
			sql.append("       CARR.CARRERA, ");
			sql.append("       OE.FUNCIONES, ");
			sql.append("       OE.EDAD_REQUISITO, ");
			sql.append("       OE.EDAD_MINIMA, ");
			sql.append("       OE.EDAD_MAXIMA, ");
			sql.append("       IDIO.IDIOMA, ");
			sql.append("       D.DOMINIO, ");
			sql.append("       OE.ID_TIPO_EMPLEO, ");
			sql.append("       TIPOEMP.TIPO_EMPLEO, ");
			sql.append("       OE.NUMERO_PLAZAS, ");
			sql.append("       OE.CONTACTO_TEL, ");
			sql.append("       OE.CONTACTO_CORREO, ");
			sql.append("       OE.CORREO_ELECTRONICO_CONTACTO, ");
			sql.append("       TEL.ACCESO, ");
			sql.append("       TEL.CLAVE, ");
			sql.append("       TEL.TELEFONO, ");
			sql.append("       TEL.EXTENSION, ");
			sql.append("       OC.FECHA_MODIFICACION, ");
			sql.append("       CASE WHEN SYSDATE - OC.FECHA_MODIFICACION <= "+ diasDifferencia+ " THEN 1 ELSE 0 END as DIFF ");

			sql.append("  FROM OFERTA_CANDIDATO OC, ");
			sql.append("       OFERTA_EMPLEO OE, ");
			sql.append("       OFERTA_UBICACION DOM, ");
			sql.append("       TELEFONO TEL, ");
			sql.append("       ENTIDADES ENT, ");
			sql.append("       MUNICIPIO MUN, ");
			sql.append("       OFERTA_IDIOMA OI, ");
			sql.append("       IDIOMAS IDIO, ");
			sql.append("       DOMINIOS D, ");
			sql.append("       OFERTA_CARRERA_ESPEC OESP, ");
			sql.append("       CARRERAS CARR, ");
			sql.append("       GRADOS, ");
			sql.append("       TIPO_EMPLEO TIPOEMP, ");
			sql.append("       ESTATUS EST_OE, ");
			sql.append("       ESTATUS EST_OC ");

			sql.append(" WHERE OC.ID_CANDIDATO = "+ idCandidato +" ");
			
			//sql.append("   AND (OC.ESTATUS = "+ estatus01.getIdOpcion() +" OR OC.ESTATUS = "+ estatus02.getIdOpcion() +") ");
			if(!listEstatus.isEmpty()){
			sql.append(" AND ( ");
			for(int i = 0 ; i < listEstatus.size(); i++){
				Catalogos.ESTATUS estatus = listEstatus.get(i);
				if(i>0){
					sql.append(" OR ");
				}
				sql.append(" OC.ESTATUS  = ");
				sql.append(estatus.getIdOpcion());
			}
			sql.append(" ) ");
			}
			sql.append("   AND OE.ID_OFERTA_EMPLEO = OC.ID_OFERTA_EMPLEO ");

			sql.append("   AND OE.ID_OFERTA_EMPLEO = DOM.ID_OFERTA_EMPLEO(+) ");
			//sql.append("   AND DOM.ID_TIPO_PROPIETARIO(+) = "+ TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario() +" ");
			sql.append("   AND ENT.ID_ENTIDAD = DOM.ID_ENTIDAD ");
			sql.append("   AND MUN.ID_ENTIDAD = DOM.ID_ENTIDAD ");
			sql.append("   AND MUN.ID_MUNICIPIO = DOM.ID_MUNICIPIO ");

			sql.append("   AND OE.ID_OFERTA_EMPLEO = TEL.ID_PROPIETARIO(+) ");
			sql.append("   AND TEL.ID_TIPO_PROPIETARIO(+) = "+ TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario() +" ");
			sql.append("   AND TEL.PRINCIPAL(+) = "+ MULTIREGISTRO.PRINCIPAL.getIdOpcion() +" ");

			sql.append("   AND OE.ID_OFERTA_EMPLEO = OI.ID_OFERTA_EMPLEO(+) ");
			sql.append("   AND OI.PRINCIPAL(+) = "+ MULTIREGISTRO.PRINCIPAL.getIdOpcion() +" ");
			sql.append("   AND IDIO.ID_IDIOMA = OI.ID_IDIOMA ");
			sql.append("   AND OI.ID_DOMINIO = D.ID_DOMINIO(+) ");

			sql.append("   AND OE.ID_OFERTA_EMPLEO = OESP.ID_OFERTA_EMPLEO(+) ");
			sql.append("   AND OESP.PRINCIPAL(+) = "+ MULTIREGISTRO.PRINCIPAL.getIdOpcion() +" ");
			sql.append("   AND OESP.ID_CARRERA_ESPECIALIDAD = CARR.ID_CARRERA(+) ");

			sql.append("   AND OE.ID_NIVEL_ESTUDIO = GRADOS.ID_GRADO(+) ");
			sql.append("   AND OE.ID_TIPO_EMPLEO = TIPOEMP.ID_TIPO_EMPLEO(+) ");

			sql.append("   AND EST_OE.ID_ESTATUS = OE.ESTATUS ");
			sql.append("   AND EST_OC.ID_ESTATUS = OC.ESTATUS ");

			sql.append(" ORDER BY OE.FECHA_FIN DESC ");

		    Query queryList = entityManager.createNativeQuery(sql.toString());	    

			@SuppressWarnings("unchecked")
			List<Object> rowSet = queryList.getResultList();

			if (rowSet!=null){

				for (Object rs : rowSet){
					int index = 0;
					
					OfertaEmpleoJB oferta = new OfertaEmpleoJB();
					Object[] row = (Object[])rs;

					long idOfertaEmpleo 	= Utils.toLong(row[index++]); //ID_OFERTA_EMPLEO,
					long idOfertaCandidato 	= Utils.toLong(row[index++]); //ID_OFERTA_CANDIDATO,
					long estatus        	= Utils.toLong(row[index++]); //ID_OFERTA_CANDIDATO,
					String estatusOferta 	= Utils.toString(row[index++]); //ESTATUS_OFERTA,
					String tituloOferta 	= Utils.toString(row[index++]); //TITULO_OFERTA,       
					String entidad 			= Utils.toString(row[index++]); //ENTIDAD, 
					String municipio 		= Utils.toString(row[index++]); //MUNICIPIO,
					String empresaNombre 	= Utils.toString(row[index++]); //NOMBRE_EMPRESA,
					Date fechaFin 			= Utils.toDate(row[index++]); //FECHA_FIN,
					String estatusOfertaCandidato = Utils.toString(row[index++]); //ESTATUS_OFERTA_CAND,
					long idNivelEstudio 	= Utils.toLong(row[index++]); //ID_NIVEL_ESTUDIO,
					String gradoEstudios 	= Utils.toString(row[index++]); //GRADO_ESTUDIOS,
					String carrera 			= Utils.toString(row[index++]); //CARRERA,
					String funciones 		= Utils.toString(row[index++]); //FUNCIONES,
					int edadRequisito 		= Utils.toInt(row[index++]); //EDAD_REQUISITO,
					int edadMinima 			= Utils.toInt(row[index++]); //EDAD_MINIMA,
					int edadMaxima 			= Utils.toInt(row[index++]); //EDAD_MAXIMA,
					String idiomas 			= Utils.toString(row[index++]); //IDIOMA,
					String dominio 			= Utils.toString(row[index++]); //DOMINIO,       
					long idtipoEmpleo 		= Utils.toLong(row[index++]); //ID_TIPO_EMPLEO,
					String tipoEmpleo 		= Utils.toString(row[index++]); //TIPO_EMPLEO,
					int numeroPlazas 		= Utils.toInt(row[index++]); //NUMERO_PLAZAS,
					int contactoTel 		= Utils.toInt(row[index++]); //CONTACTO_TEL,
					int contactoCorreo 		= Utils.toInt(row[index++]); //CONTACTO_CORREO,
					String correoContacto 	= Utils.toString(row[index++]); //CORREO_ELECTRONICO_CONTACTO,
					String acceso 			= Utils.toString(row[index++]); //ACCESO,
					String clave 			= Utils.toString(row[index++]); //CLAVE,
					String telefono 		= Utils.toString(row[index++]); //TELEFONO,
					String extension 		= Utils.toString(row[index++]); //EXTENSION
					Date fechaModificacion	= Utils.toDate(row[index++]);   //FECHA_MODIFICACION DE OFERTA_CANDIDATO
					int differenciaFecha     = Utils.toInt(row[index++]);    //DIFFERNCIA DE LA FECHA_MODIFICACION 
					
					String medioContacto = "";

					if (contactoTel==CONTACTO_TELEFONO.SI.getIdContactoTelefono() && telefono!=null && !telefono.isEmpty()){
						medioContacto += "Teléfono: "+ cadenaVacia(acceso) +" "+ cadenaVacia(clave) +" "+ cadenaVacia(telefono) + (extension==null || !extension.isEmpty()?" ":" Ext. "+ cadenaVacia(extension) +" ");
					}

					if (contactoCorreo==CONTACTO_CORREO.SI.getIdContactoCorreo() && correoContacto!=null && !correoContacto.isEmpty()){
						medioContacto += "Correo electronico :"+ cadenaVacia(correoContacto);
					}
					
					String idioma = cadenaVacia(idiomas) +" : "+ cadenaVacia(dominio);
					String ubicacion = cadenaVacia(entidad) +", "+ cadenaVacia(municipio);

					oferta.setIdOfertaEmpleo        (idOfertaEmpleo);
					oferta.setIdOfertaCandidato		(idOfertaCandidato);
					oferta.setEstatusOferta			(estatusOferta);
					oferta.setEstatus				(String.valueOf(estatus));
					oferta.setTituloOferta			(tituloOferta);
					oferta.setUbicacion				(ubicacion);
					oferta.setEmpresaNombre			(empresaNombre);
					oferta.setFechaFin				(fechaFin);
					oferta.setEstatusOfertaCandidato(estatusOfertaCandidato);
					oferta.setGradoEstudios			(gradoEstudios);
					//oferta.setespecialidades
					oferta.setCarrera				(carrera);
					oferta.setFunciones				(funciones);
					oferta.setEdadRequisito			(edadRequisito);
					oferta.setEdadMinima			(edadMinima);
					oferta.setEdadMaxima			(edadMaxima);
					oferta.setIdiomas				(idioma);
					oferta.setTipoEmpleo			(tipoEmpleo);
					oferta.setNumeroPlazas			(numeroPlazas);
					oferta.setMedioContacto			(medioContacto);
					oferta.setFechaModificacion		(fechaModificacion);
					oferta.setDifferenciaFecha		(differenciaFecha);
					ofertas.add(oferta);
				}				
			}

		} catch (NoResultException re) {
			logger.error("No se localizaron ofertas para el candidato : "+ idCandidato);
		} catch (Exception re) {
			re.printStackTrace(); logger.error(re);
			throw new PersistenceException(re);
		}

		return ofertas;
	}

	//Fin cambio Movil
}

