package mx.gob.stps.portal.core.persistencia.facade;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_CARRERA_MAESTRIA_DOCTORADO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_CARRERA_PROFESIONAL;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_CARRERA_TECNICA_COMERCIAL_BASICO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_CARRERA_TECNICA_COMERCIAL_SUPERIOR;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_EXPERIENCIA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_GRADO_ESTUDIOS;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_OCUPACION;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_PROVINCIAS_CANADA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_SIN_ESPECIALIDAD;
import static mx.gob.stps.portal.core.infra.utils.Constantes.DIA_ACTIVO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.DIA_INACTIVO;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import mx.gob.stps.portal.core.candidate.vo.NotificacionCandidatoVO;
import mx.gob.stps.portal.core.domicilio.vo.CodigoPostalVO;
import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.domicilio.vo.MunicipioVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_FUENTE_QUE_REGISTRA_OFERTA;
import mx.gob.stps.portal.core.infra.utils.Constantes.ENTIDADES_FEDERATIVAS;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.MULTIREGISTRO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_REGISTRO;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.persistencia.vo.OfertaFuncionVO;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaRequisitoVO;
import mx.gob.stps.portal.core.oferta.registro.vo.EventoVO;
import mx.gob.stps.portal.core.oferta.registro.vo.OfertaCarreraEspecialidadVO;
import mx.gob.stps.portal.core.oferta.registro.vo.OfertaIdiomaVO;
import mx.gob.stps.portal.core.oferta.registro.vo.RegistroContactoVO;
import mx.gob.stps.portal.core.oferta.registro.vo.RegistroEntidadesVO;
import mx.gob.stps.portal.core.oferta.registro.vo.RegistroIdiomaVO;
import mx.gob.stps.portal.core.oferta.registro.vo.RegistroRequisitosVO;
import mx.gob.stps.portal.core.oferta.registro.vo.RegistroUbicacionVO;
import mx.gob.stps.portal.core.oferta.registro.vo.RequisitoVO;
import mx.gob.stps.portal.core.oferta.reporte.vo.ReporteOfertasEmpresaVO;
import mx.gob.stps.portal.core.oferta.vo.BusquedaOfertasVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.core.oferta.vo.ResultadoBusquedaOfertasVO;
import mx.gob.stps.portal.core.oferta.vo.UbicacionCanadaVO;
import mx.gob.stps.portal.core.persistencia.entity.OfertaSector;
import mx.gob.stps.portal.core.seguridad.service.impl.NotificacionAppServices;
import mx.gob.stps.portal.core.ws.vo.OfertaTotalesVO;
import mx.gob.stps.portal.persistencia.entity.BitacoraPortal;
import mx.gob.stps.portal.persistencia.entity.CatalogoOpcion;
import mx.gob.stps.portal.persistencia.entity.CodigoPostal;
import mx.gob.stps.portal.persistencia.entity.Domicilio;
import mx.gob.stps.portal.persistencia.entity.Municipio;
import mx.gob.stps.portal.persistencia.entity.NotificacionCandidato;
import mx.gob.stps.portal.persistencia.entity.OfertaCarreraEspec;
import mx.gob.stps.portal.persistencia.entity.OfertaEmpleo;
import mx.gob.stps.portal.persistencia.entity.OfertaFuncion;
import mx.gob.stps.portal.persistencia.entity.OfertaHabilidad;
import mx.gob.stps.portal.persistencia.entity.OfertaHabilidadPK;
import mx.gob.stps.portal.persistencia.entity.OfertaIdioma;
import mx.gob.stps.portal.persistencia.entity.OfertaPrestacion;
import mx.gob.stps.portal.persistencia.entity.OfertaRequisito;
import mx.gob.stps.portal.persistencia.entity.OfertaUbicacion;
import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.utils.ConstantesGenerales;
import oracle.spatial.geometry.JGeometry;

import org.apache.log4j.Logger;

@Stateless
public class OfertaFacade implements OfertaFacadeLocal {

	private static Logger logger = Logger.getLogger(OfertaFacade.class);
	
	private static String findDomicilioByPropietario = "SELECT c FROM Domicilio c WHERE c.idPropietario =:propietario AND c.idTipoPropietario =:tipoPropietario";
	

	@PersistenceContext
	private EntityManager entityManager;
	
	@EJB
	private OfertaUbicacionFacadeLocal ofertaUbicacion;
	
	public long save(OfertaEmpleoVO oferta) throws PersistenceException{
		
				
		OfertaEmpleo entity = new OfertaEmpleo();
		String mapaUbicacion[] = null;
		entity.setDiscapacidades(oferta.getDiscapacidades());
		entity.setIdOficinaRegistro(ConstantesGenerales.PORTAL_ID_OFICINA);
		entity.setIdUsuario(oferta.getIdUsuario());
		entity.setPlazasCubiertas(oferta.getPlazasCubiertas());
		entity.setPublicarOfertas(oferta.getPublicarOfertas());
		//entity.setIdOfertaEmpleo(oferta.getidOfertaEmpleo);
		entity.setIdEmpresa(oferta.getIdEmpresa());
		entity.setTituloOferta(oferta.getTituloOferta());
		entity.setIdAreaLaboral(oferta.getIdAreaLaboral());
		entity.setIdOcupacion(oferta.getIdOcupacion());
		entity.setFunciones(oferta.getFunciones());
		entity.setDiasLaborales(oferta.getDiasLaborales());
		entity.setHoraEntrada(oferta.getHoraEntrada());
		entity.setHoraSalida(oferta.getHoraSalida());
		entity.setRolarTurno(oferta.getRolarTurno());
		entity.setEmpresaOfrece(oferta.getEmpresaOfrece());
		entity.setSalario(oferta.getSalario());
		entity.setIdTipoContrato(oferta.getIdTipoContrato());
		entity.setIdJerarquia(oferta.getIdJerarquia());
		entity.setNumeroPlazas(oferta.getNumeroPlazas());
		entity.setLimitePostulantes(oferta.getLimitePostulantes());
		entity.setIdDiscapacidad(oferta.getIdDiscapacidad());
		entity.setIdCausaVacante(oferta.getIdCausaVacante());
		entity.setFechaInicio(oferta.getFechaInicio());
		entity.setFechaFin(oferta.getFechaFin());
		entity.setDisponibilidadViajar(oferta.getDisponibilidadViajar());
		entity.setDisponibilidadRadicar(oferta.getDisponibilidadRadicar());
		entity.setIdNivelEstudio(oferta.getIdNivelEstudio());
		entity.setIdSituacionAcademica(oferta.getIdSituacionAcademica());
//		entity.setHabilidadGeneral(oferta.getHabilidadGeneral());
		entity.setExperienciaAnios(oferta.getExperienciaAnios());
		entity.setEdadRequisito(oferta.getEdadRequisito());
		entity.setEdadMinima(oferta.getEdadMinima());
		entity.setEdadMaxima(oferta.getEdadMaxima());
		entity.setGenero(oferta.getGenero());
		entity.setMapaUbicacion(oferta.getMapaUbicacion());
		
		/*IntegraciÃ³n con GeolocaliaciÃ³n*/
		if(oferta.getMapaUbicacion()!=null && oferta.getMapaUbicacion().contains(",")){
			mapaUbicacion = oferta.getMapaUbicacion().split(",");
			if(mapaUbicacion.length==2){
				JGeometry geoReferencia = 
						new JGeometry(Double.parseDouble(mapaUbicacion[0]), 
								      Double.parseDouble(mapaUbicacion[1]), 
								      4326);
//				entity.setGeoReferencia(geoReferencia);
			}
		}
		entity.setObservaciones(oferta.getObservaciones());
//		entity.setIdTerceraEmpresa(oferta.getIdTerceraEmpresa());
//		entity.setIdContacto(oferta.getIdContacto());
		entity.setIdHorarioDe(oferta.getIdHorarioDe());
		entity.setIdHorarioA(oferta.getIdHorarioA());
		entity.setIdDuracionAproximada(oferta.getIdDuracionAproximada());
		entity.setDiasEntrevista(oferta.getDiasEntrevista());
		entity.setContactoTel(oferta.getContactoTel());
		entity.setContactoCorreo(oferta.getContactoCorreo());
		entity.setFechaModificacion(oferta.getFechaModificacion());
		entity.setIdTipoEmpleo(oferta.getIdTipoEmpleo());
		entity.setFuente(oferta.getFuente());
		entity.setFechaAlta(oferta.getFechaAlta());
		entity.setEstatus(oferta.getEstatus());
		entity.setNombreEmpresa(oferta.getNombreEmpresa());
		entity.setIdActividadEconomica(oferta.getIdActividadEconomica());
		entity.setNombreContacto(oferta.getNombreContacto());
		entity.setCargoContacto(oferta.getCargoContacto());
		entity.setCorreoElectronicoContacto(oferta.getCorreoElectronicoContacto());
		entity.setContactoDomicilio(oferta.getContactoDomicilio());
		entity.setIdVigenciaOferta(oferta.getIdVigenciaOferta());		
		entity.setCodigo_universal_de_puesto_sfp(oferta.getCodigo_universal_de_puesto_sfp());
		entityManager.persist(entity);

		long idOfertaEmpleo = entity.getIdOfertaEmpleo();
		logger.info("idOferta "+idOfertaEmpleo);
		
		return idOfertaEmpleo;
	}
	
	public void validaOferta(long idOfertaEmpleo) throws PersistenceException{
		if(idOfertaEmpleo >0){
			System.out.println("JGLC ---> sp_valida_ofertas: "+idOfertaEmpleo);
			logger.info("JGLC ---> sp_valida_ofertas: "+idOfertaEmpleo);
			Query q = entityManager.createNativeQuery("{call sp_valida_ofertas("+idOfertaEmpleo+")}");
			q.executeUpdate();
		}
	}
	
	public void update(OfertaEmpleoVO oferta) {
		try {
			long idOfertaEmpleo = oferta.getIdOfertaEmpleo();
			String mapaUbicacion[]=null;
			OfertaEmpleo entity = entityManager.find(OfertaEmpleo.class, idOfertaEmpleo);
			
			//entity.setIdOfertaEmpleo(oferta.getidOfertaEmpleo);
			entity.setIdEmpresa(oferta.getIdEmpresa());
			entity.setTituloOferta(oferta.getTituloOferta());
			entity.setIdAreaLaboral(oferta.getIdAreaLaboral());
			entity.setIdOcupacion(oferta.getIdOcupacion());
			entity.setFunciones(oferta.getFunciones());
			entity.setDiasLaborales(oferta.getDiasLaborales());
			entity.setHoraEntrada(oferta.getHoraEntrada());
			entity.setHoraSalida(oferta.getHoraSalida());
			entity.setRolarTurno(oferta.getRolarTurno());
			entity.setEmpresaOfrece(oferta.getEmpresaOfrece());
			entity.setSalario(oferta.getSalario());
			entity.setIdTipoContrato(oferta.getIdTipoContrato());
			entity.setIdJerarquia(oferta.getIdJerarquia());
			entity.setNumeroPlazas(oferta.getNumeroPlazas());
			entity.setLimitePostulantes(oferta.getLimitePostulantes());
			entity.setIdDiscapacidad(oferta.getIdDiscapacidad());
			entity.setIdCausaVacante(oferta.getIdCausaVacante());
			entity.setFechaInicio(oferta.getFechaInicio());
			entity.setFechaFin(oferta.getFechaFin());
			entity.setDisponibilidadViajar(oferta.getDisponibilidadViajar());
			entity.setDisponibilidadRadicar(oferta.getDisponibilidadRadicar());
			entity.setIdNivelEstudio(oferta.getIdNivelEstudio());
			entity.setIdSituacionAcademica(oferta.getIdSituacionAcademica());
//			entity.setHabilidadGeneral(oferta.getHabilidadGeneral());
			entity.setExperienciaAnios(oferta.getExperienciaAnios());
			entity.setEdadRequisito(oferta.getEdadRequisito());
			entity.setEdadMinima(oferta.getEdadMinima());
			entity.setEdadMaxima(oferta.getEdadMaxima());
			entity.setGenero(oferta.getGenero());
			entity.setMapaUbicacion(oferta.getMapaUbicacion());
			/*IntegraciÃ³n con GeolocaliaciÃ³n*/
			if(oferta.getMapaUbicacion()!=null && oferta.getMapaUbicacion().contains(",")){
				mapaUbicacion = oferta.getMapaUbicacion().split(",");
				if(mapaUbicacion.length==2){
					JGeometry geoReferencia = 
							new JGeometry(Double.parseDouble(mapaUbicacion[0]), 
									      Double.parseDouble(mapaUbicacion[1]), 
									      4326);
//					entity.setGeoReferencia(geoReferencia);
				}
			}
			entity.setObservaciones(oferta.getObservaciones());
//			entity.setIdTerceraEmpresa(oferta.getIdTerceraEmpresa());
//			entity.setIdContacto(oferta.getIdContacto());
			entity.setIdHorarioDe(oferta.getIdHorarioDe());
			entity.setIdHorarioA(oferta.getIdHorarioA());
			entity.setIdDuracionAproximada(oferta.getIdDuracionAproximada());
			entity.setDiasEntrevista(oferta.getDiasEntrevista());
			entity.setContactoTel(oferta.getContactoTel());
			entity.setContactoCorreo(oferta.getContactoCorreo());
			entity.setFechaModificacion(oferta.getFechaModificacion());
			entity.setIdTipoEmpleo(oferta.getIdTipoEmpleo());
			entity.setFuente(oferta.getFuente());
			//entity.setFechaAlta(oferta.getFechaAlta());
			entity.setEstatus(oferta.getEstatus());
			entity.setNombreEmpresa(oferta.getNombreEmpresa());
			entity.setIdActividadEconomica(oferta.getIdActividadEconomica());
			entity.setNombreContacto(oferta.getNombreContacto());
			entity.setCargoContacto(oferta.getCargoContacto());
			entity.setCorreoElectronicoContacto(oferta.getCorreoElectronicoContacto());
			entity.setContactoDomicilio(oferta.getContactoDomicilio());
			entity.setIdVigenciaOferta(oferta.getIdVigenciaOferta());
			entity.setDiscapacidades(oferta.getDiscapacidades());
			entityManager.merge(entity);

		} catch (Exception re) {
			re.printStackTrace(); logger.error(re.toString());
			throw new PersistenceException(re);
		}
	}

	public long save(RegistroUbicacionVO vo) throws PersistenceException {
		long idParametro = -1;

		try {
			OfertaEmpleo entity = getEntityToSave(vo);
			entityManager.persist(entity);

			idParametro = entity.getIdOfertaEmpleo();
			Domicilio domicilio = nuevoDomicilio(vo, idParametro);
			entityManager.persist(domicilio);

		} catch (RuntimeException re) {
			logger.error(re.toString());
			re.printStackTrace();
			throw new PersistenceException(re);
		}

		return idParametro;
	}

	Domicilio nuevoDomicilio(RegistroUbicacionVO D, long idOferta) {

		Domicilio d = new Domicilio();
		d.setIdPropietario(idOferta);
		d.setCalle(D.getCalle());
		d.setCodigoPostal(D.getCodigoPostal());
		d.setEntreCalle(D.getEntreCalle());
		d.setFechaAlta(new Date());
		d.setIdColonia(D.getIdColonia());
		d.setIdEntidad(D.getIdEntidad());
		d.setIdMunicipio(D.getIdMunicipio());
		d.setIdTipoPropietario(Utils.toLong(TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario()));
		d.setNumeroExterior(D.getNumeroExterior());
		d.setNumeroInterior(D.getNumeroInterior());
		d.setYCalle(D.getyCalle());

		return d;
	}

	public Domicilio obtenerDomicilioExistente(long idOferta) {
		Domicilio domicilio = new Domicilio();
		try {
			Query query = entityManager.createQuery(findDomicilioByPropietario);

			query.setParameter("propietario", idOferta);
			query.setParameter("tipoPropietario", TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());

			@SuppressWarnings("unchecked")
			List<Object> result1 = query.getResultList();
			for (Object resultElement : result1) {

				domicilio = (Domicilio) resultElement;

			}
		} catch (NoResultException re) {
			re.printStackTrace(); // No se localizaron registros
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}
		return domicilio;
	}

	public DomicilioVO getLocation(long idOfertaEmpleo) {
		DomicilioVO location = null;

		try{
			location = new DomicilioVO();
			String select = "SELECT c.id_catalogo_opcion as entidad, m.id_municipio as municipio " +
			          "FROM OFERTA_UBICACION o, " +
			               "CATALOGO_OPCION c, " +
			               "MUNICIPIO m " +
			         "WHERE o.id_oferta_empleo = ? " +
			           "AND c.id_catalogo = ? " +
			           "AND c.id_catalogo_opcion = o.id_entidad " +
			           "AND m.id_entidad=o.id_entidad " +
			           "AND m.id_municipio=o.id_municipio";
	
			Query query = entityManager.createNativeQuery(select);
			query.setParameter(1, idOfertaEmpleo);
			query.setParameter(2, CATALOGO_OPCION_ENTIDAD_FEDERATIVA);
		
			@SuppressWarnings("unchecked")
			List<Object[]> rowSet = query.getResultList();
			for (Object[] rs : rowSet) {
				location.setIdEntidad(Utils.toLong(rs[0]));
				location.setIdMunicipio(Utils.toLong(rs[1]));
			}
		} catch (NoResultException re) {
			// No se localizaron registros
		} catch (RuntimeException re) {
			re.printStackTrace();
		}

		return location;
	}	
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public DomicilioVO obtenerDomicilio(long idOferta) {
		DomicilioVO d = new DomicilioVO();
		
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT d FROM Domicilio d WHERE d.idPropietario=:propietario AND d.idTipoPropietario=:tipoPropietario ORDER BY d.idDomicilio ");
			Query query = entityManager.createQuery(sb.toString());
			query.setParameter("propietario", idOferta);
			query.setParameter("tipoPropietario", TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());

			@SuppressWarnings("unchecked")
			List<Object> result1 = query.getResultList();
			for (Object resultElement : result1) {
				Domicilio domicilio = (Domicilio)resultElement;
				d.setIdDomicilio(domicilio.getIdDomicilio());
				d.setIdPropietario(domicilio.getIdPropietario());
				d.setCalle(domicilio.getCalle());
				d.setCodigoPostal(domicilio.getCodigoPostal());
				d.setEntreCalle(domicilio.getEntreCalle());
				d.setFechaAlta(new Date());
				d.setIdColonia(domicilio.getIdColonia());
				d.setIdEntidad(domicilio.getIdEntidad());
				d.setIdMunicipio(domicilio.getIdMunicipio());
				d.setIdTipoPropietario(TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
				d.setNumeroExterior(domicilio.getNumeroExterior());
				d.setNumeroInterior(domicilio.getNumeroInterior());
				d.setyCalle(domicilio.getYCalle());

				break;
			}
		} catch (NoResultException re) {
			// No se localizaron registros
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}

		return d;
	}
	
	public CodigoPostalVO obtenerCodigoPostal(long idColonia) {
		String findCodigoPostal = "SELECT c FROM CodigoPostal c WHERE c.idColonia=:idColonia";
		CodigoPostal CP = new CodigoPostal();
		CodigoPostalVO cp = new CodigoPostalVO();
		try {
			Query query = entityManager.createQuery(findCodigoPostal);

			query.setParameter("idColonia", idColonia);

			@SuppressWarnings("unchecked")
			List<Object> result1 = query.getResultList();
			for (Object resultElement : result1) {

				CP = (CodigoPostal) resultElement;

			}
		} catch (NoResultException re) {
			// No se localizaron registros
			re.printStackTrace();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}

		cp.setColoniaDescripcion(CP.getColonia());
		cp.setEntidadDescripcion(CP.getEntidad());
		cp.setMunicipioDescripcion(CP.getMunicipio());

		return cp;

	}

	public long update(RegistroUbicacionVO vo) throws PersistenceException {
		long idOfertaEmpleo = -1;

		try {
			OfertaEmpleo entity = getEntityEdicionUbicacion(vo);
			entityManager.merge(entity);
			
			idOfertaEmpleo = entity.getIdOfertaEmpleo();

			Domicilio domicilio = obtenerDomicilioExistente(idOfertaEmpleo);
			domicilio.setCalle(vo.getCalle());
			domicilio.setCodigoPostal(vo.getCodigoPostal());
			domicilio.setEntreCalle(vo.getEntreCalle());
			domicilio.setIdColonia(vo.getIdColonia());
			domicilio.setIdEntidad(vo.getIdEntidad());
			domicilio.setIdMunicipio(vo.getIdMunicipio());
			domicilio.setNumeroExterior(vo.getNumeroExterior());
			domicilio.setNumeroInterior(vo.getNumeroInterior());
			domicilio.setYCalle(vo.getyCalle());

		} catch (RuntimeException re) {
			logger.error(re.toString());
			re.printStackTrace();
			throw new PersistenceException(re);
		}

		return idOfertaEmpleo;
	}

	/*public void updateStatusOferta(long idOferta, int estatus)
			throws PersistenceException {

		try {
			OfertaEmpleo entity = findById(idOferta);
			entity.setEstatus(estatus);
			entity.setFechaModificacion(new Date());
			entityManager.merge(entity);
			entityManager.flush();
		} catch (RuntimeException re) {
			logger.error(re.toString());
			re.printStackTrace();
			throw new PersistenceException(re);
		}
	}*/

	public void updateFechaOfertaCanceladaActivada(long idOferta)
			throws PersistenceException {

		try {
			OfertaEmpleo entity = findById(idOferta);
			Date hoy = new Date();
			entity.setFechaModificacion(hoy);
			Date d1=entity.getFechaInicio();
			Date d2=entity.getFechaFin();
			long dif=d2.getTime()-d1.getTime();
			double dias = Math.floor(dif / (1000 * 60 * 60 * 24));

		    entity.setFechaInicio(hoy);
              
		    Calendar calendar=Calendar.getInstance();
		    calendar.add(Calendar.DATE,(int) dias);
		    entity.setFechaFin(calendar.getTime());
			
		} catch (RuntimeException re) {
			logger.error(re.toString());
			re.printStackTrace();
			throw new PersistenceException(re);
		}

	}

	public long saveRequisitosOferta(RegistroRequisitosVO vo) throws PersistenceException {
		long idOfertaEmpleo = -1;
		
		try {
			if (vo != null){
				this.eliminaRequisitos(vo.getIdOferta());
				
				OfertaEmpleo entity = getEntityRequisitos(vo);
				// entityManager.merge(entity);
				idOfertaEmpleo = entity.getIdOfertaEmpleo();

				//logger.info("Se registran las Secciones");
				saveListaSectores(vo);

				//logger.info("Se registran Lista de conocimientos");
				saveListaConocimientosOferta(vo);

				//logger.info("Se registran las habilidades");
				// TODO ESTE METODO SERA ELIMINADO YA QUE SE CAMBIO LA FORMA DE CAPTURA DE HABILIDADES
				saveListaHabilidadesOferta(vo);

				//logger.info("Se registran las competencias");
				saveListaCompetenciasOferta(vo);

				//logger.info("Se registran las idiomas");
				saveListaIdiomasOferta(vo);

				//logger.info("Se registran las carreras");
				saveListaCarreras(vo);

				//logger.info("Se registran las Entidades");
				saveListaEntidadesOferta(vo);

				//logger.info("Se registran las Prestaciones");
				saveListaPrestacionesOferta(vo);				
			}

		} catch (RuntimeException re) {
			logger.error(re.toString());
			throw new PersistenceException(re);
		}

		return idOfertaEmpleo;
	}

	public void saveListaPrestacionesOferta(RegistroRequisitosVO vo)throws PersistenceException {

		String[] prestaciones = vo.getListaPrestaciones();
		
		this.eliminaPrestaciones(vo.getIdOferta());
		
		if (prestaciones != null) {
			try {
				for (int i = 0; i < prestaciones.length; i++) {
					if (Long.parseLong(prestaciones[i]) > 0) {
						OfertaPrestacion entity = new OfertaPrestacion();
						entity.setFechaAlta(new Date());
						entity.setIdOfertaEmpleo(vo.getIdOferta());
						entity.setIdPrestacion(Long.parseLong(prestaciones[i]));
						entityManager.persist(entity);
					}
				}
			} catch (RuntimeException re) {
				logger.error(re.toString());
				re.printStackTrace();
				throw new PersistenceException(re);
			}
		}

	}

	public long registraOfertaPrestacion(long idOfertaEmpleo, long idPrestacion, Date fechaAlta){
		OfertaPrestacion entity = new OfertaPrestacion();

		entity.setIdOfertaEmpleo(idOfertaEmpleo);
		entity.setIdPrestacion(idPrestacion);
		entity.setFechaAlta(fechaAlta);

		entityManager.persist(entity);
		
		long idOfertaPrestacion = entity.getIdOfertaPrestacion();

		return idOfertaPrestacion;
	}
	
	public void saveListaConocimientosOferta(RegistroRequisitosVO vo) throws PersistenceException {

		ArrayList<RequisitoVO> listaConocimiemtos = vo.getListaConocimientos();
		RequisitoVO conocimientoP = new RequisitoVO();
		conocimientoP.setDescripcion(vo.getConocimiento1());
		conocimientoP.setDominio(vo.getDominioConoc());
		conocimientoP.setExperiencia(vo.getAniosExperienciaConoc());

		try {
			if (listaConocimiemtos != null)
				for (int i = 0; i < listaConocimiemtos.size(); i++) {

					OfertaRequisito entity = getOfertaRequisito(vo, listaConocimiemtos.get(i), 0, 1);
					entityManager.persist(entity);
				}

			if (vo.getConocimiento1() != null) {
				OfertaRequisito entity = getOfertaRequisito(vo, conocimientoP, 1, 1);
				entityManager.persist(entity);
			}

		} catch (RuntimeException re) {
			logger.error(re.toString());
			re.printStackTrace();
			throw new PersistenceException(re);
		}
	}

	public long registraOfertaRequisito(long idOfertaEmpleo, long idTipoRequisito,  String descripcion,
			                            long idExperiencia, long idDominio, int principal, Date fechaAlta){
		OfertaRequisito entity = new OfertaRequisito();
		entity.setIdOfertaEmpleo(idOfertaEmpleo);
		entity.setIdTipoRequisito(idTipoRequisito);
		entity.setDescripcion(descripcion);
		entity.setIdExperiencia(idExperiencia);
		entity.setIdDominio(idDominio);
		entity.setPrincipal(principal);
		entity.setFechaAlta(fechaAlta);

		entityManager.persist(entity);
		
		return entity.getIdOfertaRequisito();
	}

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<Long> consultaHabilidades(long idOfertaEmpleo){
		List<Long> idHabilidades = new ArrayList<Long>();
		
		try{
			Query query = entityManager.createQuery("SELECT oh FROM OfertaHabilidad oh WHERE oh.id.idOfertaEmpleo = :idOfertaEmpleo");
			query.setParameter("idOfertaEmpleo", idOfertaEmpleo);

			List<OfertaHabilidad> habilidades = (List<OfertaHabilidad>)query.getResultList();
			
			if (habilidades!=null){
				for (OfertaHabilidad habilidad : habilidades){
					long idHabilidad = habilidad.getId().getIdHabilidad();
					idHabilidades.add(idHabilidad);
				}
			}
		} catch(Exception e){
			e.printStackTrace();
			logger.error(e);
		}

		return idHabilidades;
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<CatalogoOpcionVO> consultaHabilidades(long idOfertaEmpleo, long idCatalogo){
		List<CatalogoOpcionVO> habilidadesList = new ArrayList<CatalogoOpcionVO>();
		try{
			String select = "SELECT c FROM OfertaHabilidad oh, CatalogoOpcion c " +
					         "WHERE oh.id.idOfertaEmpleo = :idOfertaEmpleo " +
					           "AND c.idCatalogo = :idCatalogo "+  
					           "AND c.idCatalogoOpcion = oh.id.idHabilidad";
			
			Query query = entityManager.createQuery(select);
			query.setParameter("idOfertaEmpleo", idOfertaEmpleo);
			query.setParameter("idCatalogo", idCatalogo);

			@SuppressWarnings("unchecked")
			List<CatalogoOpcion> habilidades = query.getResultList();

			if (habilidades!=null){
				for (CatalogoOpcion habilidad : habilidades){
					CatalogoOpcionVO opcion = getCatalogoOpcionVO(habilidad);

					habilidadesList.add(opcion);
				}
			}
		}catch(Exception e){
			logger.error(e);
		}

		return habilidadesList;
	}
	
	private CatalogoOpcionVO getCatalogoOpcionVO(CatalogoOpcion entity){
		CatalogoOpcionVO vo = new CatalogoOpcionVO();
		
		vo.setIdCatalogoOpcion(entity.getIdCatalogoOpcion());
		vo.setIdCatalogo(entity.getIdCatalogo());
		
		if(entity.getOpcion() != null)
			vo.setOpcion(entity.getOpcion());
		if(entity.getFechaAlta() != null)
			vo.setFechaAlta(entity.getFechaAlta());
		if(entity.getEstatus() != null)
			vo.setEstatus(entity.getEstatus());
		if(entity.getFechaModificacion() != null)
			vo.setFechaModificacion(entity.getFechaModificacion());
		if(entity.getIdCatalagoAsociado() != null)
			vo.setIdCatalagoAsociado(entity.getIdCatalagoAsociado());
		if(entity.getIdCorto() != null)
			vo.setIdCorto(entity.getIdCorto());
		
		
		return vo;
	}
	
	public void eliminaHabilidades(long idOfertaEmpleo){
		Query query = entityManager.createQuery("DELETE FROM OfertaHabilidad oh WHERE oh.id.idOfertaEmpleo = :idOfertaEmpleo");
		query.setParameter("idOfertaEmpleo", idOfertaEmpleo);
		query.executeUpdate();
	}
	
	public void registraHabilidad(long idOfertaEmpleo, long idHabilidad){

		try{
			OfertaHabilidadPK ofertaHabilidadPK = new OfertaHabilidadPK();
			ofertaHabilidadPK.setIdOfertaEmpleo(idOfertaEmpleo);
			ofertaHabilidadPK.setIdHabilidad(idHabilidad);

			OfertaHabilidad ofertaHabilidad = new OfertaHabilidad();
			ofertaHabilidad.setId(ofertaHabilidadPK);

			entityManager.persist(ofertaHabilidad);
		}catch(Exception e){
			logger.error(e);
		}
	}
	
	// TODO ESTE METODO SERA ELIMINADO YA QUE SE CAMBIO LA FORMA DE CAPTURA DE HABILIDADES
	public void saveListaHabilidadesOferta(RegistroRequisitosVO vo) throws PersistenceException {

		ArrayList<RequisitoVO> listaHabilidades = vo.getListaHabilidades();
		RequisitoVO habilidadP = new RequisitoVO();

		habilidadP.setDescripcion(vo.getHabilidad1());
		habilidadP.setExperiencia(vo.getAniosExperienciaHabilidad());
		habilidadP.setDominio(vo.getDominioHabilidad());

		try {
			if (listaHabilidades != null)
				for (int i = 0; i < listaHabilidades.size(); i++) {

					OfertaRequisito entity = getOfertaRequisito(vo, listaHabilidades.get(i), 0, 2);
					entityManager.persist(entity);

				}

			if (vo.getHabilidad1() != null) {
				OfertaRequisito entity = getOfertaRequisito(vo, habilidadP, 1, 2);
				entityManager.persist(entity);
			}

		} catch (RuntimeException re) {
			logger.error(re.toString());
			re.printStackTrace();
			throw new PersistenceException(re);
		}

	}

	public void saveListaEntidadesOferta(RegistroRequisitosVO vo) throws PersistenceException {

		ArrayList<RegistroEntidadesVO> listaEntidades = vo.getListaEntidadesSeleccionados();
		
		this.eliminaUbicaciones(vo.getIdOferta());
		
		if (listaEntidades != null) {
			try {
				for (int i = 0; i < listaEntidades.size(); i++) {

					OfertaUbicacion entity = getOfertaEntidad(vo, listaEntidades.get(i));
					entityManager.persist(entity);

				}
			} catch (RuntimeException re) {
				logger.error(re.toString());
				re.printStackTrace();
				throw new PersistenceException(re);
			}

		}

	}

	public long registraOfertaUbicacion(long idOfertaEmpleo, long idEntidad, long idMunicipio, Date fechaAlta){
		
		OfertaUbicacion entity = new OfertaUbicacion();
		
		entity.setIdOfertaEmpleo(idOfertaEmpleo);
		entity.setIdEntidad(idEntidad);
		entity.setIdMunicipio(idMunicipio);
		entity.setFechaAlta(fechaAlta);
		
		entityManager.persist(entity);
		
		long idOfertaUbicacion = entity.getIdOfertaUbicacion();
		
		return idOfertaUbicacion;
	}
	
	public void saveListaCompetenciasOferta(RegistroRequisitosVO vo) throws PersistenceException {

		ArrayList<RequisitoVO> listaCompetencias = vo.getListaCompetencias();
		RequisitoVO competenciaP = new RequisitoVO();
		competenciaP.setDescripcion(vo.getCompetencia1());
		competenciaP.setExperiencia(vo.getAniosExperienciaComp());
		competenciaP.setDominio(vo.getDominioComp());

		try {
			if (listaCompetencias != null)
				for (int i = 0; i < listaCompetencias.size(); i++) {

					OfertaRequisito entity = getOfertaRequisito(vo, listaCompetencias.get(i), 0, 3);
					entityManager.persist(entity);
				}

			if (vo.getCompetencia1() != null) {
				OfertaRequisito entity = getOfertaRequisito(vo, competenciaP, 1, 3);
				entityManager.persist(entity);
			}

		} catch (RuntimeException re) {
			logger.error(re.toString());
			re.printStackTrace();
			throw new PersistenceException(re);
		}

	}

	public void saveListaIdiomasOferta(RegistroRequisitosVO vo) throws PersistenceException {

		ArrayList<RegistroIdiomaVO> listaIdiomas = vo.getListaIdiomas();
		
		RegistroIdiomaVO competenciaP = new RegistroIdiomaVO();
		competenciaP.setIdioma(vo.getIdioma1());
		competenciaP.setCertificacion(vo.getCertificacionIdioma());
		competenciaP.setDominio(vo.getDominioIdioma());
		
		this.eliminaIdiomas(vo.getIdOferta());
		
		try {
			if (listaIdiomas != null)
				for (int i = 0; i < listaIdiomas.size(); i++) {

					OfertaIdioma entity = getOfertaIdioma(vo, listaIdiomas.get(i));
					entity.setPrincipal(Utils.toLong(MULTIREGISTRO.ADICIONAL.getIdOpcion()));
					entityManager.persist(entity);
				}

			if (vo.getIdioma1() != 0 && !validarIdioma(competenciaP.getCertificacion(),competenciaP.getDominio())) {
				OfertaIdioma entity = getOfertaIdioma(vo, competenciaP);
				entity.setPrincipal(Utils.toLong(MULTIREGISTRO.PRINCIPAL.getIdOpcion()));
				entityManager.persist(entity);
			}else{
				OfertaIdioma entity = getOfertaIdiomaNinguno(vo, competenciaP);
				entity.setPrincipal(Utils.toLong(MULTIREGISTRO.PRINCIPAL.getIdOpcion()));
				entityManager.persist(entity);
			}
			
		} catch (RuntimeException re) {
			logger.error(re.toString());
			re.printStackTrace();
			throw new PersistenceException(re);
		}

	}

	public long registraOfertaIdioma(long idOfertaEmpleo, long idIdioma, long idCertificacion, long idDominio, Date fechaAlta, int principal){

		OfertaIdioma idioma = new OfertaIdioma();

		idioma.setIdOfertaEmpleo(idOfertaEmpleo);
		idioma.setIdIdioma(idIdioma);
		idioma.setIdCertificacion(idCertificacion);
		idioma.setIdDominio(idDominio);
		idioma.setFechaAlta(fechaAlta);
		idioma.setPrincipal(Utils.toLong(principal));
		
		entityManager.persist(idioma);

		long idOfertaIdioma = idioma.getIdOfertaIdioma();

		return idOfertaIdioma;
	}
	
	public void saveListaCarreras(RegistroRequisitosVO vo)throws PersistenceException {
		
		ArrayList<String> listaCarreras = vo.getListaCarrerasSeleccionadas();
		String carreraP;
		
		this.eliminaCarreras(vo.getIdOferta());
		
		try {
			if (listaCarreras != null)
				for (int i = 0; i < listaCarreras.size(); i++) {

					OfertaCarreraEspec entity = getOfertaCarreraEspec(vo, listaCarreras.get(i), 2);
					entityManager.persist(entity);
				}

			if (vo.getCarrera() != 0) {
				carreraP = vo.getCarrera() + "";
				OfertaCarreraEspec entity = getOfertaCarreraEspec(vo, carreraP, 1);
				entityManager.persist(entity);
			}

		} catch (RuntimeException re) {
			logger.error(re.toString());
			re.printStackTrace();
			throw new PersistenceException(re);
		}

	}

	public long registraOfertaCarrera(long idOfertaEmpleo, long idCarreraEspecialidad, int principal, Date fechaAlta){
		OfertaCarreraEspec carrera = new OfertaCarreraEspec();
		carrera.setIdOfertaEmpleo(idOfertaEmpleo);
		carrera.setIdCarreraEspecialidad(idCarreraEspecialidad);
		carrera.setPrincipal(Utils.toLong(principal));
		carrera.setFechaAlta(fechaAlta);
		
		entityManager.persist(carrera);
		
		long idOfertaCarrera = carrera.getIdOfertaCarrera();
		return idOfertaCarrera;
	}
	
	public void saveListaSectores(RegistroRequisitosVO vo) throws PersistenceException {

		ArrayList<String> listaSectores = vo.getListaSectoresSeleccionados();

		this.eliminaSectores(vo.getIdOferta());

		if (listaSectores == null)
			return;

		try {
			Date fechaAlta = new Date();

			for (String idSector : listaSectores) {

				OfertaSector sector = new OfertaSector();
				sector.setFechaAlta(fechaAlta);
				sector.setIdOfertaEmpleo(vo.getIdOferta());
				sector.setIdSector(Utils.parseLong(idSector));

				entityManager.persist(sector);
			}

		} catch (RuntimeException re) {
			logger.error(re);
			throw new PersistenceException(re);
		}
	}

	public long registraSector(long idOfertaEmpleo, long idSector){
		long idOfertaSector = 0;
		
		try {
			Date fechaAlta = new Date();
			
			OfertaSector sector = new OfertaSector();
			sector.setFechaAlta(fechaAlta);
			sector.setIdOfertaEmpleo(idOfertaEmpleo);
			sector.setIdSector(idSector);

			entityManager.persist(sector);

			idOfertaSector = sector.getIdOfertaSector();
			
		} catch(Exception e){
			logger.error(e);
		}
		
		return idOfertaSector;
	}
	
	private OfertaCarreraEspec getOfertaCarreraEspec(RegistroRequisitosVO vo,
			String c, int principal) {
		OfertaCarreraEspec carrera = new OfertaCarreraEspec();
		carrera.setFechaAlta(new Date());
		carrera.setIdCarreraEspecialidad(Long.parseLong(c));
		// carrera.setIdOfertaCarrera(Long.parseLong(c.get("id").toString()));
		carrera.setIdOfertaEmpleo(vo.getIdOferta());
		carrera.setPrincipal(Utils.toLong(principal));

		return carrera;
	}

	private OfertaRequisito getOfertaRequisito(RegistroRequisitosVO vo, RequisitoVO c, int principal, int tipoRequisito) {
		OfertaRequisito conoc = new OfertaRequisito();
		conoc.setDescripcion(c.getDescripcion());
		// conoc.setEstatus(1);
		conoc.setFechaAlta(new Date());
		// conoc.setFechaModificacion(new Date());
		conoc.setIdDominio(c.getDominio());
		conoc.setIdExperiencia(c.getExperiencia());
		conoc.setIdOfertaEmpleo(vo.getIdOferta());
		conoc.setIdTipoRequisito(Utils.toLong(tipoRequisito));
		conoc.setPrincipal(principal);
		return conoc;

	}

	private OfertaUbicacion getOfertaEntidad(RegistroRequisitosVO vo,
			RegistroEntidadesVO c) {
		OfertaUbicacion conoc = new OfertaUbicacion();

		conoc.setFechaAlta(new Date());
		conoc.setIdEntidad(c.getEntidad());
		conoc.setIdMunicipio(c.getMunicipio());
		conoc.setIdOfertaEmpleo(vo.getIdOferta());
		return conoc;

	}

	private OfertaIdioma getOfertaIdioma(RegistroRequisitosVO vo,RegistroIdiomaVO c) {
		OfertaIdioma conoc = new OfertaIdioma();
			conoc.setIdIdioma(c.getIdioma());
			conoc.setFechaAlta(new Date());
			// conoc.setFechaModificacion(new Date());
			conoc.setIdCertificacion(c.getCertificacion());
			conoc.setIdDominio(c.getDominio());
			conoc.setIdOfertaEmpleo(vo.getIdOferta());
		return conoc;
	}
	
	private  boolean validarIdioma(long certificacion, long dominio) {
		return certificacion == 0 || dominio == 0;
	}
	
	
	
	private OfertaIdioma getOfertaIdiomaNinguno(RegistroRequisitosVO vo,RegistroIdiomaVO c) {
		int NINGUNO = 1;
		int NINGUNA = 2;
		OfertaIdioma conoc = new OfertaIdioma();
			conoc.setIdIdioma(Utils.toLong(NINGUNO));
			conoc.setFechaAlta(new Date());		
			conoc.setIdCertificacion(Utils.toLong(NINGUNA));
			conoc.setIdDominio(new Long(0));
			conoc.setIdOfertaEmpleo(vo.getIdOferta());
		return conoc;

	}

	/*
	 * public long saveContactoOferta(RegistroContactoVO vo) throws
	 * PersistenceException { long idParametro = -1;
	 * 
	 * try { OfertaEmpleo entity = getEntityContacto(vo);
	 * entity.setEstatus(ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion());
	 * entityManager.merge(entity);
	 * 
	 * idParametro = entity.getIdOfertaEmpleo();
	 * 
	 * } catch (RuntimeException re) { logger.error(re.toString());
	 * re.printStackTrace(); throw new PersistenceException(re); }
	 * 
	 * return idParametro; }
	 */

	public long updateContactoOferta(RegistroContactoVO vo) throws PersistenceException {
		long idOfertaEmpleo = -1;

		try {
			OfertaEmpleo entity = getEntityContacto(vo);
			entityManager.merge(entity);
			entityManager.flush();
			
			idOfertaEmpleo = entity.getIdOfertaEmpleo();

		} catch (RuntimeException re) {
			logger.error(re);
			throw new PersistenceException(re);
		}

		return idOfertaEmpleo;
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<OfertaEmpleoVO> obtenerOfertasEmpresa(long idEmpresa) {
		String obtenerOfertasEmpresa = "SELECT c FROM OfertaEmpleo c WHERE c.idEmpresa=:idEmpresa  AND ( c.estatus =:estatus1 OR c.estatus =:estatus2) ORDER BY c.estatus ASC, c.fechaModificacion DESC";
		ArrayList<OfertaEmpleoVO> ofertas = new ArrayList<OfertaEmpleoVO>();
		OfertaCarreraEspecialidadVO carreraPrincipal = null;

		try {
			Query query = entityManager.createQuery(obtenerOfertasEmpresa);

			query.setParameter("idEmpresa", idEmpresa);
			query.setParameter("estatus1", ESTATUS.CANCELADA.getIdOpcion());
			query.setParameter("estatus2", ESTATUS.ACTIVO.getIdOpcion());

			@SuppressWarnings("unchecked")
			List<Object> result1 = query.getResultList();
			for (Object entity : result1) {
				//entityManager.refresh(entity);

				OfertaEmpleo elemento = (OfertaEmpleo) entity;
				OfertaEmpleoVO oferta = generarVOOferta(elemento);

				carreraPrincipal = getCarreraEspecialidadPrincipal(oferta.getIdOfertaEmpleo());
				oferta.setCarreraPrincipal(carreraPrincipal);

				oferta.setDomicilio(obtenerDomicilio(oferta.getIdOfertaEmpleo()));

				ofertas.add(oferta);
			}
		} catch (NoResultException re) {
			// No se localizaron registros
			// re.printStackTrace();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}

		return ofertas;
	}

	public OfertaEmpleoVO generarVOOferta(OfertaEmpleo entity) {

		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		OfertaEmpleoVO vo = new OfertaEmpleoVO();
		if(entity.getContactoCorreo() != null)
			vo.setContactoCorreo(entity.getContactoCorreo());
		if(entity.getContactoTel() != null)
			vo.setContactoTel(entity.getContactoTel());
		if(entity.getContactoDomicilio() != null)
			vo.setContactoDomicilio(entity.getContactoDomicilio());		
		
		if(entity.getDiasEntrevista() != null)
			vo.setDiasEntrevista(entity.getDiasEntrevista());
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
		
		vo.setFechaInicioString(formatter.format(entity.getFechaInicio()));
		vo.setFechaFinString(formatter.format(entity.getFechaFin()));
		vo.setDiasLaborales(entity.getDiasLaborales());
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
		vo.setLimitePostulantes(entity.getLimitePostulantes());
		vo.setNumeroPlazas(entity.getNumeroPlazas());
		vo.setRolarTurno(entity.getRolarTurno());
		vo.setSalario(entity.getSalario());
		vo.setTituloOferta(entity.getTituloOferta());
		vo.setIdTipoEmpleo(entity.getIdTipoEmpleo());

		return vo;
	}

	public OfertaEmpleoVO obtenerOferta(long id) throws BusinessException {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		OfertaEmpleoVO vo = new OfertaEmpleoVO();
		OfertaEmpleo entity = findById(id);
		
		if(entity.getContactoCorreo() != null)
			vo.setContactoCorreo(entity.getContactoCorreo());
		if(entity.getContactoTel() != null)
			vo.setContactoTel(entity.getContactoTel());
		if(entity.getContactoDomicilio() != null)
			vo.setContactoDomicilio(entity.getContactoDomicilio());
		
		if(entity.getDiasEntrevista() != null)
			vo.setDiasEntrevista(entity.getDiasEntrevista());
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
		vo.setMapaUbicacion(entity.getMapaUbicacion() != null ? entity.getMapaUbicacion() : "");
		vo.setObservaciones(entity.getObservaciones() != null ? entity.getObservaciones() : "");
		
		vo.setFechaInicioString(formatter.format(entity.getFechaInicio()));
		vo.setFechaFinString(formatter.format(entity.getFechaFin()));
		vo.setDiasLaborales(entity.getDiasLaborales());
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
		vo.setIdDiscapacidad(entity.getIdDiscapacidad());
		vo.setIdEmpresa(entity.getIdEmpresa());
		vo.setIdJerarquia(entity.getIdJerarquia());
		vo.setIdOcupacion(entity.getIdOcupacion());
		vo.setIdOfertaEmpleo(entity.getIdOfertaEmpleo());
		vo.setIdTipoContrato(entity.getIdTipoContrato());
		vo.setLimitePostulantes(entity.getLimitePostulantes());
		vo.setNumeroPlazas(entity.getNumeroPlazas());
		vo.setRolarTurno(entity.getRolarTurno());
		vo.setSalario(entity.getSalario());
		vo.setTituloOferta(entity.getTituloOferta());
		vo.setIdTipoEmpleo(entity.getIdTipoEmpleo());
		vo.setNombreEmpresa(entity.getNombreEmpresa());
		vo.setIdActividadEconomica((int) entity.getIdActividadEconomica());
		vo.setNombreContacto(entity.getNombreContacto());
		vo.setCorreoElectronicoContacto(entity.getCorreoElectronicoContacto());
		vo.setCargoContacto(entity.getCargoContacto());		
		vo.setIdVigenciaOferta(entity.getIdVigenciaOferta());
		vo.setDiscapacidades(entity.getDiscapacidades());

		return vo;
	}

	/*
	 * public void updateParametro(long idParametro, String descripcion, String
	 * valor){ Parametro entity = getEntity(idParametro, descripcion, valor);
	 * entityManager.merge(entity); }
	 */
	/*
	 * public void updateOrSaveParametro(long idParametro, String valor){
	 * 
	 * Parametro entity = findById(idParametro);
	 * 
	 * if (entity!=null){ entity.setValor(valor); } else{ save(null, valor); } }
	 */

	private OfertaEmpleo findById(long idOfertaEmpleo) {
		OfertaEmpleo instance = null;

		instance = entityManager.find(OfertaEmpleo.class, idOfertaEmpleo);

		return instance;
	}

	/*
	 * private OfertaEmpleo getEntity(registroUbicacionForm form){ return
	 * getEntity(form); }
	 */

	private OfertaEmpleo getEntityToSave(RegistroUbicacionVO vo) {
		String dias;

		OfertaEmpleo entity = new OfertaEmpleo();
		// entity.setIdOfertaEmpleo(-1);

		entity.setTituloOferta(vo.getTituloOferta());
		entity.setIdAreaLaboral(vo.getArea());
		entity.setIdOcupacion(vo.getOcupacion());
		// sector, sectores relacionados
		entity.setFunciones(vo.getFunciones());
		// entity.setIdHorarioEmpleo(vo.getHorario());
		
		entity.setIdTipoEmpleo(vo.getHorario());

		dias = vo.getDomingo() != null ? "1" : "0";
		dias += vo.getLunes() != null ? "1" : "0";
		dias += vo.getMartes() != null ? "1" : "0";
		dias += vo.getMiercoles() != null ? "1" : "0";
		dias += vo.getJueves() != null ? "1" : "0";
		dias += vo.getViernes() != null ? "1" : "0";
		dias += vo.getSabado() != null ? "1" : "0";

		entity.setDiasLaborales(dias);
		entity.setHoraEntrada(vo.getHEntrada());
		entity.setHoraSalida(vo.getHSalida());
		entity.setRolarTurno(vo.getRolarTurnos());
		entity.setEmpresaOfrece(vo.getEmpresaOfrece());
		entity.setSalario(vo.getSalario());
		entity.setIdTipoContrato(vo.getTipoContrato());
		entity.setIdJerarquia(vo.getJerarquia());
		// prestaciones
		entity.setNumeroPlazas(vo.getNumeroPlazas());
		entity.setLimitePostulantes(vo.getLimitePostulantes());
		// entity.setFechaInicio(new Date());
		// entity.setFechaFin(new Date());
		entity.setFechaAlta(new Date());
		entity.setFechaInicio(vo.getVigenciaInicio());
		entity.setFechaFin(vo.getVigenciaFin());
		entity.setFechaModificacion(new Date());
		entity.setIdDiscapacidad(vo.getDicapacidad());
		entity.setIdCausaVacante(vo.getOrigenOferta());
		entity.setMapaUbicacion(vo.getMapaUbicacion());
		entity.setEmpresaOfrece(vo.getEmpresaOfrece());
		
		entity.setIdEmpresa(vo.getIdEmpresa()); // En el registro se establece el id de la empresa a la que pertenece
		
		entity.setEstatus(ESTATUS.EN_EDICION.getIdOpcion());
		entity.setFuente(1);

		entity.setDisponibilidadViajar(0);
		entity.setDisponibilidadViajar(0);
		entity.setIdNivelEstudio(new Long(0));
		entity.setIdSituacionAcademica(new Long(0));
		entity.setHabilidadGeneral("0");
		entity.setExperienciaAnios(0);
		entity.setEdadRequisito(0);
		// entity.setEdadMinima(0);
		// entity.setEdadMaxima(0);
		entity.setGenero(0);

		return entity;
	}

	private OfertaEmpleo getEntityEdicionUbicacion(RegistroUbicacionVO vo) {
		String dias;
		OfertaEmpleo entity = findById(vo.getIdOferta());
		// OfertaEmpleo entity = new OfertaEmpleo();
		// entity.setIdOfertaEmpleo(-1);

		entity.setTituloOferta(vo.getTituloOferta());
		entity.setIdAreaLaboral(vo.getArea());
		entity.setIdOcupacion(vo.getOcupacion());
		// sector, sectores relacionados
		entity.setFunciones(vo.getFunciones());
		// entity.setIdHorarioEmpleo(vo.getHorario());
		entity.setIdTipoEmpleo(vo.getHorario());

		dias = vo.getDomingo() != null ? "1" : "0";
		dias += vo.getLunes() != null ? "1" : "0";
		dias += vo.getMartes() != null ? "1" : "0";
		dias += vo.getMiercoles() != null ? "1" : "0";
		dias += vo.getJueves() != null ? "1" : "0";
		dias += vo.getViernes() != null ? "1" : "0";
		dias += vo.getSabado() != null ? "1" : "0";

		entity.setDiasLaborales(dias);
		entity.setHoraEntrada(vo.getHEntrada());
		entity.setHoraSalida(vo.getHSalida());
		entity.setRolarTurno(vo.getRolarTurnos());
		entity.setEmpresaOfrece(vo.getEmpresaOfrece());
		entity.setSalario(vo.getSalario());
		entity.setIdTipoContrato(vo.getTipoContrato());
		entity.setIdJerarquia(vo.getJerarquia());
		// prestaciones
		entity.setNumeroPlazas(vo.getNumeroPlazas());
		entity.setLimitePostulantes(vo.getLimitePostulantes());
		//entity.setFechaAlta(new Date());
		entity.setFechaInicio(vo.getVigenciaInicio());
		entity.setFechaFin(vo.getVigenciaFin());
		entity.setFechaModificacion(new Date());
		entity.setIdDiscapacidad(vo.getDicapacidad());
		entity.setIdCausaVacante(vo.getOrigenOferta());
		entity.setMapaUbicacion(vo.getMapaUbicacion());
		entity.setEmpresaOfrece(vo.getEmpresaOfrece());
		
		//entity.setIdEmpresa(vo.getIdEmpresa()); // En la edicion no se permite modificar la empresa
		
		// entity.setEstatus(11);
		entity.setFuente((int) Constantes.BOLSA_TRABAJO.PORTAL_EMPLEO.getIdOpcion());

		entity.setDisponibilidadViajar(0);
		entity.setDisponibilidadViajar(0);
		entity.setIdNivelEstudio(new Long(0));
		entity.setIdSituacionAcademica(new Long(0));
		entity.setHabilidadGeneral("0");
		entity.setExperienciaAnios(0);
		entity.setEdadRequisito(0);
		// entity.setEdadMinima(0);
		// entity.setEdadMaxima(0);
		entity.setGenero(0);

		return entity;
	}

	private OfertaEmpleo getEntityRequisitos(RegistroRequisitosVO vo) {

		OfertaEmpleo e = findById(vo.getIdOferta());

		e.setIdNivelEstudio(vo.getEstudios());
		// carrera
		// conocimientos
		// habilidades
		// competencias
		// idiomas
		e.setIdSituacionAcademica(vo.getSituacionAcademica());
		e.setHabilidadGeneral(vo.getConocimientosGenerales());
		e.setExperienciaAnios(vo.getAniosExperiencia());
		e.setDisponibilidadViajar(vo.getDisponibilidadViajar());
		e.setDisponibilidadRadicar(vo.getDisponibilidadRadicar());
		e.setEdadRequisito(vo.getEdadRequerida());
		e.setEdadMinima(vo.getEdadDe());
		e.setEdadMaxima(vo.getEdadA());
		e.setGenero(vo.getGenero());
		e.setObservaciones(vo.getObservaciones());

		return e;
	}

	private OfertaEmpleo getEntityContacto(RegistroContactoVO vo) {

		OfertaEmpleo e = findById(vo.getIdOferta());

//		e.setIdTerceraEmpresa(vo.getNombreEmpresa());
//		e.setIdContacto(vo.getNombreContacto());
		
		e.setContactoTel(vo.getTelefonoContacto());
		e.setContactoCorreo(vo.getCorreoContacto());
		e.setContactoDomicilio(vo.getDomicilioContacto());
		String dias = vo.getDomingo() != null ? DIA_ACTIVO : DIA_INACTIVO;
		dias += vo.getLunes() != null ? DIA_ACTIVO : DIA_INACTIVO;
		dias += vo.getMartes() != null ? DIA_ACTIVO : DIA_INACTIVO;
		dias += vo.getMiercoles() != null ? DIA_ACTIVO : DIA_INACTIVO;
		dias += vo.getJueves() != null ? DIA_ACTIVO : DIA_INACTIVO;
		dias += vo.getViernes() != null ? DIA_ACTIVO : DIA_INACTIVO;
		dias += vo.getSabado() != null ? DIA_ACTIVO : DIA_INACTIVO;
		e.setDiasEntrevista(dias);
		e.setIdHorarioDe(vo.getHoraAtencionInicio());
		e.setIdHorarioA(vo.getHoraAtencionFin());
		e.setIdDuracionAproximada(vo.getDuracionAtencion());
		e.setEstatus(vo.getEstatus());
		return e;
	}

	/**
	 * Method 'getSectoresOferta' Obtiene los sectores de una oferta.
	 * 
	 * 
	 * @param idOferta
	 * @return List<String>
	 */
	public ArrayList<String> getSectoresOferta(long idOferta) throws PersistenceException {
		ArrayList<String> lista = new ArrayList<String>();
		String buscaSectorOferta = "SELECT c FROM OfertaSector c WHERE c.idOfertaEmpleo=:idOferta";
		try {
			Query query = entityManager.createQuery(buscaSectorOferta);

			OfertaSector elemento;
			query.setParameter("idOferta", idOferta);
			@SuppressWarnings("unchecked")
			List<Object> result1 = query.getResultList();
			for (Object resultElement : result1) {
				// OfertaSectorVO vo =
				// getOfertaSectorVO((OfertaSector)resultElement);
				elemento = (OfertaSector) resultElement;
				entityManager.refresh(elemento);
				
				lista.add(elemento.getIdSector() + "");

			}
		} catch (NoResultException re) {
			// No se localizaron registros
			re.printStackTrace();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}
		return lista;
	}

	public ArrayList<String> getIDSSectoresOferta(long idOferta) throws PersistenceException {
		ArrayList<String> lista = new ArrayList<String>();
		String buscaSectorOferta = "SELECT c FROM OfertaSector c WHERE c.idOfertaEmpleo=:idOferta";
		try {
			Query query = entityManager.createQuery(buscaSectorOferta);

			OfertaSector elemento;
			query.setParameter("idOferta", idOferta);
			@SuppressWarnings("unchecked")
			List<Object> result1 = query.getResultList();
			for (Object resultElement : result1) {
				// OfertaSectorVO vo =
				// getOfertaSectorVO((OfertaSector)resultElement);
				elemento = (OfertaSector) resultElement;
				lista.add(elemento.getIdOfertaSector() + "");

			}
		} catch (NoResultException re) {
			// No se localizaron registros
			re.printStackTrace();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}
		return lista;
	}

	public ArrayList<String> getPrestacionesOferta(long idOferta)
			throws PersistenceException {
		ArrayList<String> lista = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		try {
			sb.append("SELECT c FROM OfertaPrestacion c WHERE c.idOfertaEmpleo=:idOferta");
			Query query = entityManager.createQuery(sb.toString());

			OfertaPrestacion elemento;
			query.setParameter("idOferta", idOferta);
			@SuppressWarnings("unchecked")
			List<Object> result1 = query.getResultList();
			for (Object resultElement : result1) {
				// OfertaSectorVO vo =
				// getOfertaSectorVO((OfertaSector)resultElement);
				elemento = (OfertaPrestacion) resultElement;
				lista.add(elemento.getIdPrestacion() + "");

			}
		} catch (NoResultException re) {
			// No se localizaron registros
			re.printStackTrace();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}
		return lista;
	}

	public ArrayList<String> getIDSPrestacionesOferta(long idOferta) throws PersistenceException {
		ArrayList<String> lista = new ArrayList<String>();
		String buscaPrestacionOferta = "SELECT c FROM OfertaPrestacion c WHERE c.idOfertaEmpleo=:idOferta";
		try {
			Query query = entityManager.createQuery(buscaPrestacionOferta);

			OfertaPrestacion elemento;
			query.setParameter("idOferta", idOferta);
			@SuppressWarnings("unchecked")
			List<Object> result1 = query.getResultList();
			for (Object resultElement : result1) {
				// OfertaSectorVO vo =
				// getOfertaSectorVO((OfertaSector)resultElement);
				elemento = (OfertaPrestacion) resultElement;
				lista.add(elemento.getIdOfertaPrestacion() + "");

			}
		} catch (NoResultException re) {
			// No se localizaron registros
			re.printStackTrace();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}
		return lista;
	}

	public ArrayList<RegistroEntidadesVO> getEntidadesOferta(long idOferta)
			throws PersistenceException {
		ArrayList<RegistroEntidadesVO> lista = new ArrayList<RegistroEntidadesVO>();
		StringBuilder sb = new StringBuilder();
		try {
			sb.append("SELECT c FROM OfertaUbicacion c WHERE c.idOfertaEmpleo=:idOferta");
			Query query = entityManager.createQuery(sb.toString());
			RegistroEntidadesVO item;
			OfertaUbicacion elemento;
			query.setParameter("idOferta", idOferta);
			@SuppressWarnings("unchecked")
			List<Object> result1 = query.getResultList();
			for (Object resultElement : result1) {

				item = new RegistroEntidadesVO();
				elemento = (OfertaUbicacion) resultElement;
				item.setEntidad(elemento.getIdEntidad());
				item.setMunicipio(elemento.getIdMunicipio());
				item.setIdRegistro(elemento.getIdOfertaUbicacion());
				lista.add(item);

			}
		} catch (NoResultException re) {
			// No se localizaron registros
			re.printStackTrace();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}
		return lista;
	}

	public ArrayList<OfertaCarreraEspecialidadVO> getCarrerasEspecialidades(
			long idOferta) throws PersistenceException {
		ArrayList<OfertaCarreraEspecialidadVO> lista = new ArrayList<OfertaCarreraEspecialidadVO>();
		StringBuilder sb = new StringBuilder();
		try {
			sb.append("SELECT c FROM OfertaCarreraEspec c WHERE c.idOfertaEmpleo=:idOferta  ORDER BY c.principal");
			Query query = entityManager.createQuery(sb.toString());
			OfertaCarreraEspecialidadVO item;
			OfertaCarreraEspec elemento;
			query.setParameter("idOferta", idOferta);
			@SuppressWarnings("unchecked")
			List<Object> result1 = query.getResultList();
			for (Object resultElement : result1) {

				item = new OfertaCarreraEspecialidadVO();
				elemento = (OfertaCarreraEspec) resultElement;
				item.setId(elemento.getIdCarreraEspecialidad());
				item.setPrincipal(Utils.toInt(elemento.getPrincipal()));
				item.setIdRegistro(elemento.getIdOfertaCarrera());
				item.setFechaAlta(elemento.getFechaAlta());				
				lista.add(item);

			}
		} catch (NoResultException re) {
			// No se localizaron registros
			re.printStackTrace();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}
		return lista;
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public OfertaCarreraEspecialidadVO getCarreraEspecialidadPrincipal(long idOferta) throws PersistenceException {
		ArrayList<OfertaCarreraEspecialidadVO> lista = new ArrayList<OfertaCarreraEspecialidadVO>();
		OfertaCarreraEspecialidadVO item = new OfertaCarreraEspecialidadVO();
		StringBuilder sb = new StringBuilder();
		try {
			sb.append("SELECT c FROM OfertaCarreraEspec c WHERE c.idOfertaEmpleo=:idOferta AND c.principal=:idPrincipal ORDER BY c.principal");
			Query query = entityManager.createQuery(sb.toString());

			OfertaCarreraEspec elemento;
			query.setParameter("idOferta", idOferta);
			query.setParameter("idPrincipal", MULTIREGISTRO.PRINCIPAL.getIdOpcion());
			@SuppressWarnings("unchecked")
			List<Object> result1 = query.getResultList();
			for (Object resultElement : result1) {

				item = new OfertaCarreraEspecialidadVO();
				elemento = (OfertaCarreraEspec) resultElement;
				item.setId(elemento.getIdCarreraEspecialidad());
				item.setPrincipal(Utils.toInt(elemento.getPrincipal()));
				item.setIdRegistro(elemento.getIdOfertaCarrera());
				lista.add(item);

			}
		} catch (NoResultException re) {
			// No se localizaron registros
			re.printStackTrace();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}

		if (item.getId() == 0)
			item.setId(-1);

		return item;

	}

	public ArrayList<RequisitoVO> getRequisitos(long idOferta)
			throws PersistenceException {
		ArrayList<RequisitoVO> lista = new ArrayList<RequisitoVO>();
		String consulta = "SELECT c FROM OfertaRequisito c WHERE c.idOfertaEmpleo=:idOferta";
		try {
			Query query = entityManager.createQuery(consulta);
			RequisitoVO item;
			OfertaRequisito elemento;
			query.setParameter("idOferta", idOferta);
			@SuppressWarnings("unchecked")
			List<Object> result1 = query.getResultList();
			for (Object resultElement : result1) {

				item = new RequisitoVO();
				elemento = (OfertaRequisito) resultElement;

				item.setDescripcion(elemento.getDescripcion());
				item.setDominio(elemento.getIdDominio());
				item.setExperiencia(elemento.getIdExperiencia());
				item.setPrincipal(elemento.getPrincipal());
				item.setTipo(elemento.getIdTipoRequisito());
				item.setIdRegistro(elemento.getIdOfertaRequisito());

				lista.add(item);

			}
		} catch (NoResultException re) {
			// No se localizaron registros
			re.printStackTrace();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}
		return lista;
	}

	public ArrayList<OfertaIdiomaVO> getIdiomas(long idOferta) throws PersistenceException {
		ArrayList<OfertaIdiomaVO> lista = new ArrayList<OfertaIdiomaVO>();
		StringBuilder sb = new StringBuilder();
		try {
			sb.append("SELECT c FROM OfertaIdioma c WHERE c.idOfertaEmpleo=:idOferta");
			Query query = entityManager.createQuery(sb.toString());
			OfertaIdiomaVO item;
			OfertaIdioma elemento;
			query.setParameter("idOferta", idOferta);
			@SuppressWarnings("unchecked")
			List<Object> result1 = query.getResultList();
			for (Object resultElement : result1) {

				item = new OfertaIdiomaVO();
				elemento = (OfertaIdioma) resultElement;

				item.setIdCertificacion(elemento.getIdCertificacion());
				item.setIdDominio(elemento.getIdDominio());
				item.setIdIdioma(elemento.getIdIdioma());
				item.setIdOfertaEmpleo(elemento.getIdOfertaEmpleo());
				item.setIdRegistro(elemento.getIdOfertaIdioma());
				item.setPrincipal(Utils.toInt(elemento.getPrincipal()));
				lista.add(item);

			}
		} catch (NoResultException re) {
			// No se localizaron registros
			re.printStackTrace();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}
		return lista;
	}

	/*public ArrayList<TerceraEmpresaVO> getTercerasEmpresas(long idEmpresa)throws PersistenceException {
		ArrayList<TerceraEmpresaVO> lista = new ArrayList<TerceraEmpresaVO>();
		try {
			Query query = entityManager.createNamedQuery("buscaTercerasEmpresas");
			query.setParameter("idEmpresa", idEmpresa);
			query.setParameter("estatus",Constantes.ESTATUS.ACTIVO.getIdOpcion());

			@SuppressWarnings("unchecked")
			List<Object> result1 = query.getResultList();
			for (Object resultElement : result1) {

				TerceraEmpresaVO item = new TerceraEmpresaVO();
				TerceraEmpresa elemento = (TerceraEmpresa) resultElement;

				item.setDescripcion(elemento.getDescripcion());
				item.setEstatus(elemento.getEstatus());
				item.setIdEmpresa(elemento.getIdEmpresa());
				item.setIdTerceraEmpresa(elemento.getIdTerceraEmpresa());
				item.setRazonSocial(elemento.getRazonSocial());
				item.setNombre(elemento.getNombre()
						+ " "
						+ elemento.getApellido1()
						+ " "
						+ (elemento.getApellido2() != null ? elemento
								.getApellido2() : ""));
				item.setTipo(elemento.getIdTipoPersona());

				lista.add(item);

			}
		} catch (NoResultException re) {
			// No se localizaron registros
			re.printStackTrace();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}
		return lista;
	}*/

	/*public ArrayList<ContactoVO> getContactos(long idEmpresa) throws PersistenceException {
		ArrayList<ContactoVO> lista = new ArrayList<ContactoVO>();
		try {
			Query query = entityManager.createNamedQuery("buscaContacto");

			query.setParameter("idEmpresa", idEmpresa);
			query.setParameter("estatus", ESTATUS.ACTIVO.getIdOpcion());
			@SuppressWarnings("unchecked")
			List<Object> result1 = query.getResultList();
			for (Object resultElement : result1) {

				ContactoVO item = new ContactoVO();
				Contacto elemento = (Contacto) resultElement;

				item.setEstatus(elemento.getEstatus());
				item.setIdContacto(elemento.getIdContacto());
				item.setIdEmpresa(elemento.getIdEmpresa());
				item.setIdTerceraEmpresa(elemento.getIdTerceraEmpresa());
				item.setNombreContacto(elemento.getNombreContacto());

				lista.add(item);

			}
		} catch (NoResultException re) {
			// No se localizaron registros
			re.printStackTrace();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}
		return lista;
	}*/

	/*public ArrayList<TerceraEmpresaVO> getEmpresas(long idEmpresa)
			throws PersistenceException {
		ArrayList<TerceraEmpresaVO> lista = new ArrayList<TerceraEmpresaVO>();
		try {
			Query query = entityManager.createNamedQuery("buscarEmpresas");
			query.setParameter("idEmpresa", idEmpresa);
			
			@SuppressWarnings("unchecked")
			List<Object> result1 = query.getResultList();
			for (Object resultElement : result1) {

				TerceraEmpresaVO item = new TerceraEmpresaVO();
				Empresa elemento = (Empresa) resultElement;

				item.setDescripcion(elemento.getDescripcion());
				item.setEstatus(elemento.getEstatus());
				item.setIdEmpresa(elemento.getIdEmpresa());
				item.setRazonSocial(elemento.getRazonSocial());
				item.setNombre(elemento.getNombre() + " "
						+ elemento.getApellido1() + " "
						+ elemento.getApellido2());
				item.setTipo(elemento.getIdTipoPersona());

				lista.add(item);

			}
		} catch (NoResultException re) {
			// No se localizaron registros
			re.printStackTrace();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}
		return lista;
	}*/

	public void eliminaSectores(long idOfertaEmpleo) {
		String borraSectorOferta = "SELECT c FROM OfertaSector c WHERE c.idOfertaEmpleo=:idOferta";
		try {
			Query query = entityManager.createQuery(borraSectorOferta);

			query.setParameter("idOferta", idOfertaEmpleo);
			query.executeUpdate();

		} catch (NoResultException re) {
			// No se localizaron registros
			re.printStackTrace();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}

		/*
		 * for (String sector : sectores) { OfertaSector entity =
		 * entityManager.find(OfertaSector.class, Long.parseLong(sector)); if
		 * (entity != null) entityManager.remove(entity);
		 * 
		 * }
		 */

	}

	public void eliminaPrestaciones(long idOfertaEmpleo) {
		String consulta = "SELECT c FROM OfertaPrestacion c WHERE c.idOfertaEmpleo=:idOferta";
		Query query = entityManager.createQuery(consulta);
		query.setParameter("idOferta", idOfertaEmpleo);

		@SuppressWarnings("unchecked")
		List<OfertaPrestacion> result1 = query.getResultList();
		for (OfertaPrestacion elemento : result1) {
			entityManager.remove(elemento);
		}
	}

	public void eliminaUbicaciones(long idOfertaEmpleo) {
		String consulta = "SELECT c FROM OfertaUbicacion c WHERE c.idOfertaEmpleo=:idOferta";
		Query query = entityManager.createQuery(consulta);
		query.setParameter("idOferta", idOfertaEmpleo);
		
		@SuppressWarnings("unchecked")
		List<OfertaUbicacion> result1 = query.getResultList();
		
		for (OfertaUbicacion elemento : result1) {
			entityManager.remove(elemento);
			entityManager.flush();
		}		
	}

	public void eliminaCarreras(long idOfertaEmpleo) {
		String consulta = "SELECT c FROM OfertaCarreraEspec c WHERE c.idOfertaEmpleo=:idOferta  ORDER BY c.principal";
		Query query = entityManager.createQuery(consulta);
		query.setParameter("idOferta", idOfertaEmpleo);

		@SuppressWarnings("unchecked")
		List<OfertaCarreraEspec> result1 = query.getResultList();
		
		for (OfertaCarreraEspec resultElement : result1) {
			entityManager.remove(resultElement);
		}
	}

	public void eliminaRequisitos(long idOfertaEmpleo) {
		String consulta = "SELECT c FROM OfertaRequisito c WHERE c.idOfertaEmpleo=:idOferta";
		Query query = entityManager.createQuery(consulta);
		query.setParameter("idOferta", idOfertaEmpleo);

		@SuppressWarnings("unchecked")
		List<OfertaRequisito> requisitos = query.getResultList();

		for (OfertaRequisito elemento : requisitos) {
			entityManager.remove(elemento);
		}
	}

	public void eliminaIdiomas(long idOfertaEmpleo) {
		String busqueda = "SELECT c FROM OfertaIdioma c WHERE c.idOfertaEmpleo=:idOferta";
		Query query = entityManager.createQuery(busqueda);
		query.setParameter("idOferta", idOfertaEmpleo);

		@SuppressWarnings("unchecked")
		List<OfertaIdioma> result1 = query.getResultList();
		
		for (OfertaIdioma idioma : result1) {
			entityManager.remove(idioma);
		}

		
	}

	public List<MunicipioVO> obtenerMunicipio(long idEntidad)
			throws PersistenceException {

		List<MunicipioVO> listaMunicipios = new ArrayList<MunicipioVO>();
		String getMunicipios = "SELECT c FROM Municipio c WHERE c.idEntidad=:idEnt order by c.municipio";
		try {

			Query query = entityManager.createQuery(getMunicipios);
			query.setParameter("idEnt", idEntidad);
			@SuppressWarnings("unchecked")
			List<Object> result = query.getResultList();

			for (Object nElemento : result) {

				MunicipioVO vo = getMunicipioVO((Municipio) nElemento);
				listaMunicipios.add(vo);
			}

		} catch (NoResultException re) {
			re.printStackTrace();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}

		return listaMunicipios;

	}

	private MunicipioVO getMunicipioVO(Municipio entity) {

		MunicipioVO vo = new MunicipioVO();

		vo.setIdEntidad(entity.getIdEntidad());
		vo.setIdMunicipio(entity.getIdMunicipio());
		vo.setMunicipio(entity.getMunicipio());

		return vo;
	}

	DomicilioVO getDomicilio(long idOferta) {
		DomicilioVO d = new DomicilioVO();
		Domicilio D = entityManager.find(Domicilio.class, idOferta);
		if (D != null) {
			d.setCalle(D.getCalle());
			d.setCodigoPostal(D.getCodigoPostal());
			d.setEntreCalle(D.getEntreCalle());
			d.setIdColonia(D.getIdColonia());
			d.setIdEntidad(D.getIdEntidad());
			d.setIdMunicipio(D.getIdMunicipio());
			d.setNumeroExterior(D.getNumeroExterior());
			d.setNumeroInterior(D.getNumeroInterior());
			d.setyCalle(D.getYCalle());

		}

		return d;
	}

	public List<OfertaEmpleoVO> obtenerOfertasEliminadasTitulo(long idEmpresa,int estatus1, int estatus2, String titulo) {
		
		ArrayList<OfertaEmpleoVO> ofertas = new ArrayList<OfertaEmpleoVO>();
		OfertaEmpleoVO oferta;

		try {
			StringBuilder sbQuery = new StringBuilder();
			sbQuery.append("SELECT c FROM OfertaEmpleo c WHERE c.idEmpresa=:idEmpresa  AND ( c.estatus =:estatus1 OR c.estatus =:estatus2) AND UPPER(c.tituloOferta) LIKE :titulo ");
			Query query = entityManager.createQuery(sbQuery.toString());
			query.setParameter("idEmpresa", idEmpresa);
			query.setParameter("estatus1", estatus1);
			query.setParameter("estatus2", estatus2);
			query.setParameter("titulo", "%" + titulo.toUpperCase() + "%");

			OfertaEmpleo elemento;

			@SuppressWarnings("unchecked")
			List<Object> result1 = query.getResultList();
			for (Object resultElement : result1) {

				elemento = (OfertaEmpleo) resultElement;
				oferta = generarVOOferta(elemento);

				ofertas.add(oferta);

			}
		} catch (NoResultException re) {
			// No se localizaron registros
			re.printStackTrace();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}

		return ofertas;
	}

	public List<OfertaEmpleoVO> obtenerOfertasEliminadasFecha(long idEmpresa,int estatus1, int estatus2, Date fecha1, Date fecha2) {
		
		ArrayList<OfertaEmpleoVO> ofertas = new ArrayList<OfertaEmpleoVO>();
		OfertaEmpleoVO oferta;

		try {
			StringBuilder sbQuery = new StringBuilder();
			sbQuery.append("SELECT c FROM OfertaEmpleo c WHERE c.idEmpresa=:idEmpresa  AND ( c.estatus =:estatus1 OR c.estatus =:estatus2) AND c.fechaModificacion BETWEEN :fecha1 and :fecha2 ");
			Query query = entityManager.createQuery(sbQuery.toString());
			query.setParameter("idEmpresa", idEmpresa);
			query.setParameter("estatus1", estatus1);
			query.setParameter("estatus2", estatus2);
			query.setParameter("fecha1", fecha1);
			query.setParameter("fecha2", fecha2);
			
			OfertaEmpleo elemento;

			@SuppressWarnings("unchecked")
			List<Object> result1 = query.getResultList();
			for (Object resultElement : result1) {
				elemento = (OfertaEmpleo) resultElement;
				oferta = generarVOOferta(elemento);
				ofertas.add(oferta);

			}
		} catch (NoResultException re) {
			// No se localizaron registros
			re.printStackTrace();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}

		return ofertas;
	}

	public List<OfertaEmpleoVO> obtenerOfertasEliminadasFolio(long idEmpresa,int estatus1, int estatus2, long folio) {
		ArrayList<OfertaEmpleoVO> ofertas = new ArrayList<OfertaEmpleoVO>();
		OfertaEmpleoVO oferta;

		try {
			StringBuilder sbQuery = new StringBuilder();
			sbQuery.append("SELECT c FROM OfertaEmpleo c WHERE c.idEmpresa=:idEmpresa  AND ( c.estatus =:estatus1 OR c.estatus =:estatus2) AND c.idOfertaEmpleo=:folio ");
			Query query = entityManager.createQuery(sbQuery.toString());			
			query.setParameter("idEmpresa", idEmpresa);
			query.setParameter("estatus1", estatus1);
			query.setParameter("estatus2", estatus2);
			query.setParameter("folio", folio);
			OfertaEmpleo elemento;

			@SuppressWarnings("unchecked")
			List<Object> result1 = query.getResultList();
			for (Object resultElement : result1) {

				elemento = (OfertaEmpleo) resultElement;
				oferta = generarVOOferta(elemento);

				ofertas.add(oferta);

			}
		} catch (NoResultException re) {
			// No se localizaron registros
			re.printStackTrace();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}

		return ofertas;
	}

	public void insertarEventoBitacora(EventoVO evento) throws PersistenceException {

		try {
			BitacoraPortal bitacora = new BitacoraPortal();
			bitacora.setDescripcion(evento.getDescripcion());
			bitacora.setDetalle(evento.getDetalle());
			bitacora.setFechaEvento(evento.getFecha());
			bitacora.setIdEvento(evento.getId());
			bitacora.setIdUsuario(evento.getUsuario());
			bitacora.setIdRegistro(evento.getIdRegistro());
			bitacora.setIdTipoPropietario(evento.getIdTipoPropietario());
			entityManager.persist(bitacora);

		} catch (RuntimeException re) {
			logger.error(re.toString());
			re.printStackTrace();
			throw new PersistenceException(re);
		}

	}
	public Long findCandidatoNotificacion(long idCandidato) {
		Long lista =0L;
		Long candidato = idCandidato;
		String buscaSectorOferta = "SELECT id_Candidato FROM NOTIFICACION_CANDIDATO  WHERE id_Candidato= ? ";
		try {
			System.out.print("entro al select");
			Query query = entityManager.createNativeQuery(buscaSectorOferta);
			query.setParameter(1, candidato);
			//query.setParameter("candidatoNotificacion", candidato);
			@SuppressWarnings("unchecked")
			List<Object> result1 = query.getResultList();
			if (result1.size() > 0 ){
				System.out.print("entro al if");
				lista=1L;
			}
			
		} catch (NoResultException re) {
			// No se localizaron registros
			re.printStackTrace();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}
		return lista;
	}
	
	@Override
	public void updateEstatusNotificacion(NotificacionCandidato notificacion) throws PersistenceException {
		  try {
		         
		         Long estatus=0L;
		         String jpql = "UPDATE NotificacionCandidato AS t SET t.estatusNotificacion ="+estatus+" WHERE t.idCandidato ="+notificacion.getIdCandidato();
		 		 //System.out.print("entro al update");
		         Query query = entityManager.createQuery(jpql);
		         //query.setParameter("idDevice", device);
		         //query.setParameter("idCandidato", notificacion.getIdCandidato());
		         query.executeUpdate();
		         
		         
		  } catch (NoResultException re) {
		   logger.error("No se localizo el registro del candidato : "+ notificacion.getIdCandidato());
		  } catch (Exception re) {
		   throw new PersistenceException(re);
		  }
		 }
	
	@Override
	public void updateCandidatoNotificacion2(NotificacionCandidato notificacion) throws PersistenceException {
		  try {
		         
		         String device=notificacion.getIdDevice();
		         String jpql = "UPDATE NotificacionCandidato AS t SET t.idDevice ='"+device+"' WHERE t.idCandidato ="+notificacion.getIdCandidato();
		 		 //System.out.print("entro al update");
		         Query query = entityManager.createQuery(jpql);
		         //query.setParameter("idDevice", device);
		         //query.setParameter("idCandidato", notificacion.getIdCandidato());
		         query.executeUpdate();
		         
		  } catch (NoResultException re) {
		   logger.error("No se localizo el registro del candidato : "+ notificacion.getIdCandidato());
		  } catch (Exception re) {
		   throw new PersistenceException(re);
		  }
		 }
	
	
	
	public void updateCandidatoNotificacion(long idCandidato, String deviceId) {
		Long lista =0L;
		String device="ya funciono";
		System.out.print("entro al update");
		String buscaSectorOferta = "update NOTIFICACION_CANDIDATO set id_device='"+device+"' WHERE id_Candidato=0";
		try {
			System.out.println(buscaSectorOferta);
			Query query = entityManager.createNativeQuery(buscaSectorOferta);
			//query.setParameter("deviceId", "'" + device + "'");
			//query.setParameter(1, device);
			@SuppressWarnings("unchecked")
			List<Object[]> rowSet = query.getResultList();
			
		} catch (NoResultException re) {
			// No se localizaron registros
			re.printStackTrace();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}
		
	}
	public void notificacionCandidato(NotificacionCandidato notificacion)throws PersistenceException{
		
		long idCandidato = notificacion.getIdCandidato();
		String deviceId= notificacion.getIdDevice();
		long find = 0L;
		
		
		try {
			
					entityManager.persist(notificacion);
				
			
			}catch (RuntimeException re) {
				logger.error(re.toString());
				re.printStackTrace();
				throw new PersistenceException(re);
			}
		
		
	}
	
	public List<MunicipioVO> obtenerMunicipios() {
		String getMunicipiosAll = "SELECT c FROM Municipio c order by c.municipio";
		ArrayList<MunicipioVO> municipios = new ArrayList<MunicipioVO>();
		MunicipioVO municipio;

		try {
			Query query = entityManager.createQuery(getMunicipiosAll);

			Municipio elemento;

			@SuppressWarnings("unchecked")
			List<Object> result1 = query.getResultList();
			for (Object resultElement : result1) {

				elemento = (Municipio) resultElement;
				municipio = new MunicipioVO();
				municipio.setIdEntidad(elemento.getIdEntidad());
				municipio.setIdMunicipio(elemento.getIdMunicipio());
				municipio.setMunicipio(elemento.getMunicipio());

				municipios.add(municipio);

			}
		} catch (NoResultException re) {
			// No se localizaron registros
			re.printStackTrace();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}

		return municipios;
	}
	
	public List<OfertaPorCanalVO> getOffersPerFilter(long idoffer, int district, String dateInitAdd, String dateFinalAdd,
			String dateInitUpd, String dateFinalUpd, String idPortal, String email, int status, int deleteRazon,
			String idEmpresa, String contacto, String telefono, String idEntidadSelect, String idMunicipio, String salarioRango, String salario, String titulo		
			) throws PersistenceException {
		StringBuilder filter = new StringBuilder();
		StringBuilder builder = new StringBuilder();
		List<OfertaPorCanalVO> offersList = new ArrayList<OfertaPorCanalVO>();
		StringBuilder base = new StringBuilder();
				
		base.append("WITH REG AS (SELECT R.ESTATUS, R.ID_REGISTRO, R.ID_REG_VALIDAR, RANK()OVER( PARTITION BY R.ID_REGISTRO ORDER BY R.ID_REG_VALIDAR DESC) ORDEN "+
		                          ", V.DESCRIPCION, R.DETALLE_RECHAZO "+
		                         "FROM REGISTRO_POR_VALIDAR R, VALIDACION_MOTIVO V "+
				                 "WHERE R.ID_TIPO_REGISTRO = "+TIPO_REGISTRO.OFERTA.getIdTipoRegistro()+
				                 " AND R.ID_TIPO_PROPIETARIO = "+TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario()+
				                 " AND R.ID_MOTIVO_RECHAZO = V.ID_VALIDACION_MOTIVO(+)"+
				                 ") ");
		base.append("SELECT H.id_oferta_empleo, H.titulo_oferta, H.fecha_alta, H.fecha_modificacion, H.estatus, ");
		base.append(" CASE WHEN I.id_tipo_persona = 1 THEN I.nombre || ' ' || I.apellido1 || ' ' || I.apellido2");
		base.append(" WHEN I.id_tipo_persona = 2 THEN I.razon_social");
		//base.append(" WHEN ((NVL (H.id_tercera_empresa,0) != 0) AND (I.id_tipo_persona = 1)) THEN J.nombre || ' ' || J.apellido1 || ' ' || J.apellido2");
		//base.append(" WHEN ((NVL (H.id_tercera_empresa,0) != 0 ) AND (I.id_tipo_persona = 2)) THEN J.razon_social ");
		base.append(" END AS empresa");
		base.append(", REG.ID_REG_VALIDAR");		
		base.append(", h.id_empresa");		
		base.append(", REG.DESCRIPCION MOTIVO_RECHAZO");
		base.append(", REG.DETALLE_RECHAZO");
		base.append(" FROM oferta_empleo H, empresa I");
		//base.append(" tercera_empresa J, ");
		base.append(", REG");
		filter.append(" AND H.id_oferta_empleo=REG.ID_REGISTRO(+)");		
		filter.append(" AND 1 = REG.ORDEN(+)"); // seleccionamos el registro a validar correspondiente al Ã¯Â¿Â½ltimo estado
		if (idoffer > 0) {
			filter.append(" AND H.id_oferta_empleo=" + idoffer);
		}
		
		if ((idEntidadSelect != null && !idEntidadSelect.isEmpty()) || 
			(idMunicipio     != null && !idMunicipio.isEmpty())     ||
			(district > 0)){
				base.append(", OFERTA_UBICACION D");
				filter.append(" AND H.id_oferta_empleo = D.id_oferta_empleo(+) ");						
		}
		
		if (district > 0) {
			filter.append("AND h.id_oferta_empleo = REG.ID_REGISTRO ");
			filter.append("AND D.id_entidad=" + district);
		}
		if (null != dateInitAdd && null != dateFinalAdd)
			filter.append(" AND H.fecha_alta BETWEEN '" + dateInitAdd + "' AND '" + dateFinalAdd + "'");
		if (null != dateInitUpd && null != dateFinalUpd)
			filter.append(" AND H.fecha_modificacion BETWEEN '" + dateInitUpd + "' AND '" + dateFinalUpd + "'");
		if (!email.isEmpty())
			filter.append(" AND LOWER(I.correo_electronico) = lower(" + email + ")");
		if (!idPortal.isEmpty())
			filter.append(" AND H.id_empresa=I.id_empresa AND I.id_portal_empleo='" + idPortal + "'");
		if (status > 0)
			filter.append(" AND H.estatus=" + status);
		if (deleteRazon > 0) {
			base.append(", registro_por_validar R");
			filter.append(" AND I.id_empresa=R.id_propietario AND R.id_tipo_propietario=1 AND R.id_tipo_registro=2 AND R.id_motivo_rechazo=" + deleteRazon);
		}

		builder.append(base);
		builder.append(" WHERE H.id_empresa = I.id_empresa ");
		if (idEmpresa != null && !idEmpresa.isEmpty())
			builder.append(" AND I.id_empresa = "+idEmpresa);

		if (contacto != null && !contacto.isEmpty())
			builder.append(" AND LOWER(H.NOMBRE_CONTACTO) LIKE LOWER('%"+contacto+"%')");
		
		if (telefono != null && !telefono.isEmpty())
			builder.append(" AND EXISTS (SELECT 1 FROM TELEFONO WHERE TELEFONO.ID_TIPO_PROPIETARIO = "+TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario()+
					                                                " AND TELEFONO.ID_PROPIETARIO = H.ID_OFERTA_EMPLEO AND TELEFONO.TELEFONO LIKE '%"+telefono+"%') ");

		if (idEntidadSelect != null && !idEntidadSelect.isEmpty())
			filter.append(" AND D.ID_ENTIDAD = "+idEntidadSelect); 		
					
		if (idMunicipio!= null && !idMunicipio.isEmpty())
			filter.append(" AND D.ID_MUNICIPIO = "+idMunicipio);		 
		
		if (salario != null && !salario.isEmpty() && salarioRango != null && !salarioRango.isEmpty()){
			
			if (Utils.parseLong(salarioRango)  == Catalogos.RANGO_COMPARA.MAYOR.getIdOpcion())
					filter.append(" AND H.SALARIO > "+salario);
			
			if (Utils.parseLong(salarioRango)  == Catalogos.RANGO_COMPARA.IGUAL.getIdOpcion())
				filter.append(" AND H.SALARIO = "+salario);
			
			if (Utils.parseLong(salarioRango)  == Catalogos.RANGO_COMPARA.MENOR.getIdOpcion())
				filter.append(" AND H.SALARIO < "+salario);
		}

		if (titulo != null && !titulo.isEmpty())
			builder.append(" AND LOWER(H.TITULO_OFERTA) LIKE LOWER('%"+titulo+"%')");			
		
		//builder.append(" AND H.id_tercera_empresa = J.id_tercera_empresa(+)");
		builder.append((filter.length()>2 ? filter.toString() : ""));

		Query query = entityManager.createNativeQuery(builder.toString());
		@SuppressWarnings("unchecked")
		List<Object[]> rowSet = query.getResultList();
		for (Object[] rs : rowSet) {
			OfertaPorCanalVO offer = retrieveOffer(rs);
			offersList.add(offer);
		}
		return offersList;
	}

	private OfertaPorCanalVO retrieveOffer(Object[] rowSet) {
		OfertaPorCanalVO offer = new OfertaPorCanalVO();
		offer.setIdOfertaEmpleo(Utils.toLong(rowSet[0]));
		offer.setTituloOferta(Utils.toString(rowSet[1]));
		offer.setFechaInicio(Utils.toDate(rowSet[2]));
		offer.setFechaFin(Utils.toDate(rowSet[3]));
		offer.setFunciones(mx.gob.stps.portal.core.oferta.detalle.helper.Utils.setEstatusLbl(Utils.toInt(rowSet[4])));
		offer.setEmpresa(Utils.toString(rowSet[5]));
		offer.setEstatus(Utils.toLong(rowSet[4]));
		offer.setIdRegistroPorValidar(Utils.toLong(rowSet[6]));
		offer.setIdEmpresa(Utils.toLong(rowSet[7]));		
		offer.setMotivoRechazo(Utils.toString(rowSet[8]));
		offer.setDetalleMotivoRechazo(Utils.toString(rowSet[9]));
		return offer;
	}
	
	public OfertaTotalesVO obtenerEstadisticasDeOfertasEncontradasPorOcupacion(List<Integer> idsOcupaciones, int idEntidad){
		OfertaTotalesVO estadisticasDeOfertasEncontradas = new OfertaTotalesVO();
		StringBuilder sqlCount = new StringBuilder();
		
		String cadenaDeIdsParametros = "";
		for (Integer id : idsOcupaciones) cadenaDeIdsParametros += id.intValue() +",";
		if (cadenaDeIdsParametros.endsWith(",")) cadenaDeIdsParametros = cadenaDeIdsParametros.substring(0, cadenaDeIdsParametros.length()-1);	
		
		sqlCount.append(" WITH OFERTAS AS ( ");		
		sqlCount.append(" SELECT OFER.* "); // Consulta principal
		sqlCount.append("   FROM OFERTA_EMPLEO OFER ");
		if(idEntidad>0){
			sqlCount.append("        ,(SELECT * FROM OFERTA_UBICACION) DOM ");
			sqlCount.append("  WHERE DOM.ID_OFERTA_EMPLEO = OFER.ID_OFERTA_EMPLEO ");
			sqlCount.append("    AND DOM.ID_ENTIDAD = " + idEntidad);
			sqlCount.append("    AND OFER.ESTATUS = ?1 ");
			sqlCount.append("    AND OFER.ID_OCUPACION IN ("+ cadenaDeIdsParametros +") ");							
		} else {
			sqlCount.append("  WHERE OFER.ESTATUS = ?1 ");
			sqlCount.append("    AND OFER.ID_OCUPACION IN ("+ cadenaDeIdsParametros +") ");			
		}
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

		estadisticasDeOfertasEncontradas.setTotalOfertas    (Utils.toLong  (results[0]));
		estadisticasDeOfertasEncontradas.setSalarioMinimo   (Utils.toDouble(results[1]));
		estadisticasDeOfertasEncontradas.setSalarioMaximo   (Utils.toDouble(results[2]));
		estadisticasDeOfertasEncontradas.setSalarioPromedio (Utils.toDouble(results[3]));
		estadisticasDeOfertasEncontradas.setExperienciaAnios(Utils.toInt   (results[4]));
		estadisticasDeOfertasEncontradas.setIdNivelEstudio  (Utils.toInt   (results[5]));
				
		return estadisticasDeOfertasEncontradas;		
	}
	
	public OfertaTotalesVO obtenerEstadisticasDeOfertasEncontradasPorCarrera(List<Integer> idsCarrerasUOcupaciones, int idEscolaridad, int idEntidad){
		OfertaTotalesVO estadisticasDeOfertasEncontradas = new OfertaTotalesVO();
		StringBuilder sqlCount = new StringBuilder();
		
		String cadenaDeIdsParametros = "";
		for (Integer id : idsCarrerasUOcupaciones) cadenaDeIdsParametros += id.intValue() +",";
		if (cadenaDeIdsParametros.endsWith(",")) cadenaDeIdsParametros = cadenaDeIdsParametros.substring(0, cadenaDeIdsParametros.length()-1);		
		
		sqlCount.append(" WITH OFERTAS AS ( ");
		sqlCount.append(" SELECT OFER.* ");
		sqlCount.append("   FROM OFERTA_EMPLEO OFER, ");
		if(idEntidad>0){
			sqlCount.append("        (SELECT * FROM OFERTA_UBICACION) DOM, ");
		}		
		sqlCount.append("        OFERTA_CARRERA_ESPEC CARR ");
		if(idEntidad>0){
			sqlCount.append("        WHERE DOM.ID_OFERTA_EMPLEO = OFER.ID_OFERTA_EMPLEO ");
			sqlCount.append("        AND DOM.ID_ENTIDAD = " + idEntidad);
			sqlCount.append("    AND OFER.ID_OFERTA_EMPLEO = CARR.ID_OFERTA_EMPLEO ");
			sqlCount.append("    AND OFER.ID_NIVEL_ESTUDIO = " + idEscolaridad);
			sqlCount.append("    AND OFER.ESTATUS = ?1 ");
			sqlCount.append("    AND CARR.ID_CARRERA_ESPECIALIDAD IN ("+ cadenaDeIdsParametros +") ");		
			sqlCount.append("    AND CARR.PRINCIPAL=" + MULTIREGISTRO.PRINCIPAL.getIdOpcion() + " ) ");						
		} else {
			sqlCount.append("  WHERE OFER.ID_OFERTA_EMPLEO = CARR.ID_OFERTA_EMPLEO ");
			sqlCount.append("    AND OFER.ID_NIVEL_ESTUDIO = " + idEscolaridad);
			sqlCount.append("    AND OFER.ESTATUS = ?1 ");
			sqlCount.append("    AND CARR.ID_CARRERA_ESPECIALIDAD IN ("+ cadenaDeIdsParametros +") ");		
			sqlCount.append("    AND CARR.PRINCIPAL=" + MULTIREGISTRO.PRINCIPAL.getIdOpcion() + " ) ");
			
		}
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

		estadisticasDeOfertasEncontradas.setTotalOfertas    (Utils.toLong  (results[0]));
		estadisticasDeOfertasEncontradas.setSalarioMinimo   (Utils.toDouble(results[1]));
		estadisticasDeOfertasEncontradas.setSalarioMaximo   (Utils.toDouble(results[2]));
		estadisticasDeOfertasEncontradas.setSalarioPromedio (Utils.toDouble(results[3]));
		estadisticasDeOfertasEncontradas.setExperienciaAnios(Utils.toInt   (results[4]));
		estadisticasDeOfertasEncontradas.setIdNivelEstudio  (Utils.toInt   (results[5]));
				
		return estadisticasDeOfertasEncontradas;
	}
	
	/*Busqueda especifica para n carreras o n ocupaciones, se usa para el WS del OLA */
	public List<Long> busquedaEspecificaMultiple(int entidad, int area, int escolaridad, int salario, int idMunicipio, List<Integer> idsOcupaciones,
			List<Integer> idsCarreras, int edad, int region) throws PersistenceException {	
		List<Long> ids = new ArrayList<Long>();
		StringBuilder filter = new StringBuilder();
		StringBuilder builderSelect = new StringBuilder();
		StringBuilder builderFrom = new StringBuilder();
		StringBuilder builder = new StringBuilder();
		builderSelect.append("SELECT ID_OFERTA_EMPLEO FROM( ");
		builderSelect.append("SELECT  H.ID_OFERTA_EMPLEO, H.FECHA_INICIO FROM oferta_empleo H ");
		if (entidad > 0) {
			builderFrom.append(", OFERTA_UBICACION D");
			filter.append(" AND D.id_entidad=" + entidad + " AND H.id_oferta_empleo = D.id_oferta_empleo(+)");
			if (idMunicipio > 0)
				filter.append(" AND D.id_municipio = " + idMunicipio);
		}
		if (area > 0) {
			if (null!=idsOcupaciones && !idsOcupaciones.isEmpty()){
				String in = "";
				for (Integer idOcupacion : idsOcupaciones) in += idOcupacion.intValue() +",";
				if (in.endsWith(",")) in = in.substring(0, in.length()-1);		
				filter.append(" AND H.id_ocupacion in(" + in + ") ");
			}				
		}
		if (escolaridad > 0) {
			filter.append(" AND H.id_nivel_estudio=" + escolaridad);
			if (null!=idsCarreras && !idsCarreras.isEmpty()) {
				String in = "";
				for (Integer idCarrera : idsCarreras) in += idCarrera.intValue() +",";
				if (in.endsWith(",")) in = in.substring(0, in.length()-1);						
					builderFrom.append(", oferta_carrera_espec E");
				filter.append(" AND E.id_carrera_especialidad in(" + in + ") " );
				filter.append(" AND H.id_oferta_empleo=E.id_oferta_empleo AND E.principal=" + MULTIREGISTRO.PRINCIPAL.getIdOpcion());
			}
		}
		if (salario > 0) {
			switch (salario) {
				case 1 : filter.append(" AND H.salario < 5000"); break;
				case 2 : filter.append(" AND H.salario > 5001 AND H.salario <= 10000"); break;
				case 3 : filter.append(" AND H.salario > 10001 AND H.salario <= 15000"); break;
				case 4 : filter.append(" AND H.salario > 15001 AND H.salario <= 25000"); break;
				case 5 : filter.append(" AND H.salario > 25001 AND H.salario <= 40000"); break;
				case 6 : filter.append(" AND H.salario > 40000"); break;
				default : filter.append("");
			}
		}
		if (edad > 0) {
			switch (edad) {
				case 1 : filter.append(" AND H.edad_requisito = 2 AND H.edad_minima <= 20"); break;
				case 2 : filter.append(" AND H.edad_requisito = 2 AND H.edad_minima < 30 AND 20 < H.edad_maxima"); break;
				case 3 : filter.append(" AND H.edad_requisito = 2 AND H.edad_minima < 40 AND 29 < H.edad_maxima"); break;
				case 4 : filter.append(" AND H.edad_requisito = 2 AND H.edad_minima < 50 AND 39 < H.edad_maxima"); break;
				case 5 : filter.append(" AND H.edad_requisito = 2 AND H.edad_maxima >= 50"); break;
				default : filter.append("");
			}
		}
		if (region > 0) {
			builderFrom.append(", OFERTA_UBICACION D");
			filter.append(" AND (" + getEntitiesFromRegion(region) + ") AND H.id_oferta_empleo = D.id_oferta_empleo(+)"); 
			
		}

		builder.append(builderSelect).append(builderFrom).append(" WHERE H.estatus=1").append(filter.toString());
		builder.append(" ORDER BY H.FECHA_INICIO DESC, H.ID_OFERTA_EMPLEO DESC ");
		builder.append(")");
//		logger.info("-----OfertaFacade.busquedaEspecificaMultiple builder.toString():" + builder.toString());
		Query query = entityManager.createNativeQuery(builder.toString());
		@SuppressWarnings("unchecked")
		List<Object> rowSet = query.getResultList();
		for (Object rs : rowSet) {
			ids.add(Utils.toLong(rs));
		}		
		return ids;
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<Long> busquedaEspecifica(int entidad, int area, int escolaridad, int salario, int idMunicipio, int ocupacion,
			int carrera, int edad, int region, String tipoOrden, int columna, int fuente) throws PersistenceException {
		List<Long> ids = new ArrayList<Long>();
		StringBuilder filter = new StringBuilder();
		StringBuilder builder = new StringBuilder();
		StringBuilder base = new StringBuilder("SELECT H.id_oferta_empleo FROM oferta_empleo H");
		
		if(columna == 2)
			base.append(", CATALOGO_OPCION G, MUNICIPIO F ");
		
		if (entidad > 0) {
			base.append(", OFERTA_UBICACION D");
			filter.append(" AND D.id_entidad=" + entidad + " AND H.id_oferta_empleo = D.id_oferta_empleo(+)");
			if (idMunicipio > 0)
				filter.append(" AND D.id_municipio = " + idMunicipio);
		}
		if (area > 0) {
			filter.append(" AND H.id_area_laboral=" + area);
			if (ocupacion > 0)
				filter.append(" AND H.id_ocupacion=" + ocupacion);
		}
		if (escolaridad > 0) {
			filter.append(" AND H.id_nivel_estudio=" + escolaridad);
			if (carrera > 0) {
				base.append(", oferta_carrera_espec E");
				filter.append(" AND E.id_carrera_especialidad=" + carrera + " AND H.id_oferta_empleo=E.id_oferta_empleo");
			}
		}
		if (salario > 0) {
			switch (salario) {
				case 1 : filter.append(" AND H.salario < 5000"); break;
				case 2 : filter.append(" AND H.salario > 5001 AND H.salario <= 10000"); break;
				case 3 : filter.append(" AND H.salario > 10001 AND H.salario <= 15000"); break;
				case 4 : filter.append(" AND H.salario > 15001 AND H.salario <= 25000"); break;
				case 5 : filter.append(" AND H.salario > 25001 AND H.salario <= 40000"); break;
				case 6 : filter.append(" AND H.salario > 40000"); break;
				default : filter.append("");
			}
		}
		if (edad > 0) {
			switch (edad) {
				case 1 : filter.append(" AND H.edad_requisito = 2 AND H.edad_minima <= 20"); break;
				case 2 : filter.append(" AND H.edad_requisito = 2 AND H.edad_minima < 30 AND 20 < H.edad_maxima"); break;
				case 3 : filter.append(" AND H.edad_requisito = 2 AND H.edad_minima < 40 AND 29 < H.edad_maxima"); break;
				case 4 : filter.append(" AND H.edad_requisito = 2 AND H.edad_minima < 50 AND 39 < H.edad_maxima"); break;
				case 5 : filter.append(" AND H.edad_requisito = 2 AND H.edad_maxima >= 50"); break;
				default : filter.append("");
			}
		}
		if (region > 0) {
			base.append(", OFERTA_UBICACION D");
			filter.append(" AND (" + getEntitiesFromRegion(region) + ") AND H.id_oferta_empleo = D.id_oferta_empleo(+)"); 
			
		}
		
		if(fuente == 3){
			
			filter.append(" AND H.FUENTE = "+fuente);
			
		}else if(fuente == 1){
			filter.append(" AND H.FUENTE IN (" +CATALOGO_FUENTE_QUE_REGISTRA_OFERTA.PORTAL.getIdOpcion()+","+CATALOGO_FUENTE_QUE_REGISTRA_OFERTA.SFP.getIdOpcion()+","+CATALOGO_FUENTE_QUE_REGISTRA_OFERTA.SISNE.getIdOpcion()+") ");
		}
		
		if(columna == 2)
			filter.append("AND D.id_entidad = F.id_entidad(+) AND D.id_municipio = F.id_municipio AND D.id_entidad = G.id_catalogo_opcion(+) AND G.ID_CATALOGO = 25");
		
			
		builder.append(base).append(" WHERE H.estatus=1").append(filter.toString());
		if(tipoOrden == null)
			builder.append(" ORDER BY H.FECHA_INICIO DESC, H.ID_OFERTA_EMPLEO DESC ");
		else if(tipoOrden.equals("asc")){
			if(columna == 1)
				builder.append(" ORDER BY H.TITULO_OFERTA ASC, H.ID_OFERTA_EMPLEO DESC ");
			else if(columna == 2)
				builder.append(" ORDER BY G.OPCION ASC, F.MUNICIPIO ASC, H.ID_OFERTA_EMPLEO DESC ");
			else if(columna == 3)
				builder.append(" ORDER BY H.NOMBRE_EMPRESA ASC, H.ID_OFERTA_EMPLEO DESC ");
			else if(columna == 4)
				builder.append(" ORDER BY H.SALARIO ASC, H.ID_OFERTA_EMPLEO DESC ");
			else if(columna == 5)
				builder.append(" ORDER BY H.FECHA_INICIO ASC, H.ID_OFERTA_EMPLEO DESC ");
		}else if(tipoOrden.equals("desc")){
			if(columna == 1)
				builder.append(" ORDER BY H.TITULO_OFERTA DESC, H.ID_OFERTA_EMPLEO DESC ");
			else if(columna == 2)
				builder.append(" ORDER BY G.OPCION DESC, F.MUNICIPIO DESC, H.ID_OFERTA_EMPLEO DESC ");
			else if(columna == 3)
				builder.append(" ORDER BY H.NOMBRE_EMPRESA DESC, H.ID_OFERTA_EMPLEO DESC ");
			else if(columna == 4)
				builder.append(" ORDER BY H.SALARIO DESC, H.ID_OFERTA_EMPLEO DESC ");
			else if(columna == 5)
				builder.append(" ORDER BY H.FECHA_INICIO DESC, H.ID_OFERTA_EMPLEO DESC ");
		}
		
		Query query = entityManager.createNativeQuery(builder.toString());
		@SuppressWarnings("unchecked")
		List<Object> rowSet = query.getResultList();
		for (Object rs : rowSet) {
			ids.add(Utils.toLong(rs));
		}
		return ids;
	}

	private String getEntitiesFromRegion(int region) {
		StringBuilder entities = new StringBuilder();
		switch (region) {
			case 1 : entities.append(" D.id_entidad = " + ENTIDADES_FEDERATIVAS.BAJA_CALIFORNIA.getIdEntidad() + " OR D.id_entidad = " + Constantes.ENTIDADES_FEDERATIVAS.BAJA_CALIFORNIA_SUR.getIdEntidad());
					entities.append(" OR D.id_entidad = " + ENTIDADES_FEDERATIVAS.SONORA.getIdEntidad() + " OR D.id_entidad = " + ENTIDADES_FEDERATIVAS.SINALOA.getIdEntidad());
					entities.append(" OR D.id_entidad = " + ENTIDADES_FEDERATIVAS.CHIHUAHUA.getIdEntidad() + " OR D.id_entidad = " + ENTIDADES_FEDERATIVAS.DURANGO.getIdEntidad()); break;
			case 2 : entities.append(" D.id_entidad = " + ENTIDADES_FEDERATIVAS.COAHUILA.getIdEntidad() + " OR D.id_entidad = " + ENTIDADES_FEDERATIVAS.NUEVO_LEON.getIdEntidad());
					entities.append(" OR D.id_entidad = " + ENTIDADES_FEDERATIVAS.TAMAULIPAS.getIdEntidad() + " OR D.id_entidad = " + ENTIDADES_FEDERATIVAS.ZACATECAS.getIdEntidad());
					entities.append(" OR D.id_entidad = " + ENTIDADES_FEDERATIVAS.SAN_LUIS_POTOSI.getIdEntidad()); break;
			case 3 : entities.append(" D.id_entidad = " + ENTIDADES_FEDERATIVAS.NAYARIT.getIdEntidad() + " OR D.id_entidad = " + ENTIDADES_FEDERATIVAS.AGUASCALIENTES.getIdEntidad());
					entities.append(" OR D.id_entidad = " + ENTIDADES_FEDERATIVAS.JALISCO.getIdEntidad() + " OR D.id_entidad = " + ENTIDADES_FEDERATIVAS.GUANAJUATO.getIdEntidad());
					entities.append(" OR D.id_entidad = " + ENTIDADES_FEDERATIVAS.COLIMA.getIdEntidad() + " OR D.id_entidad = " + ENTIDADES_FEDERATIVAS.MICHOACAN.getIdEntidad()); break;
			case 4 : entities.append(" D.id_entidad = " + ENTIDADES_FEDERATIVAS.GUERRERO.getIdEntidad() + " OR D.id_entidad = " + ENTIDADES_FEDERATIVAS.QUERETARO.getIdEntidad());
					entities.append(" OR D.id_entidad = " + ENTIDADES_FEDERATIVAS.HIDALGO.getIdEntidad() + " OR D.id_entidad = " + ENTIDADES_FEDERATIVAS.MEXICO.getIdEntidad());
					entities.append(" OR D.id_entidad = " + ENTIDADES_FEDERATIVAS.DISTRITO_FEDERAL.getIdEntidad() + " OR D.id_entidad = " + ENTIDADES_FEDERATIVAS.MORELOS.getIdEntidad());
					entities.append(" OR D.id_entidad = " + ENTIDADES_FEDERATIVAS.PUEBLA.getIdEntidad() + " OR D.id_entidad = " + ENTIDADES_FEDERATIVAS.TLAXCALA.getIdEntidad());
					entities.append(" OR D.id_entidad = " + ENTIDADES_FEDERATIVAS.VERACRUZ.getIdEntidad()); break;
			case 5 : entities.append(" D.id_entidad = " + ENTIDADES_FEDERATIVAS.OAXACA.getIdEntidad() + " OR D.id_entidad = " + ENTIDADES_FEDERATIVAS.CHIAPAS.getIdEntidad());
					entities.append(" OR D.id_entidad = " + ENTIDADES_FEDERATIVAS.TABASCO.getIdEntidad() + " OR D.id_entidad = " + ENTIDADES_FEDERATIVAS.CAMPECHE.getIdEntidad());
					entities.append(" OR D.id_entidad = " + ENTIDADES_FEDERATIVAS.YUCATAN.getIdEntidad() + " OR D.id_entidad = " + ENTIDADES_FEDERATIVAS.QUINTANA_ROO.getIdEntidad()); break;
			default : entities.append("");
		}
		return entities.toString();
	}

	public List<OfertaEmpleoVO> consultaMisOfertas(long idEmpresa){
		List<OfertaEmpleoVO> ofertas = new ArrayList<OfertaEmpleoVO>();
		
		StringBuilder select = new StringBuilder();

		select.append("WITH OCUPACIONES AS (SELECT ID_CATALOGO_OPCION AS ID_OCUPACION, ");
		select.append("                OPCION AS OCUPACION ");
		select.append("           FROM CATALOGO_OPCION ");
		select.append("          WHERE ID_CATALOGO = "+ CATALOGO_OPCION_OCUPACION +"), ");
		
		select.append("NIVEL_ESTUDIO AS (SELECT ID_CATALOGO_OPCION AS ID_NIVEL_ESTUDIO, ");
		select.append("                OPCION AS NIVEL_ESTUDIO ");
		select.append("           FROM CATALOGO_OPCION ");
		select.append("          WHERE ID_CATALOGO = "+ CATALOGO_OPCION_GRADO_ESTUDIOS +"), ");
		
		select.append("ENTIDADES     AS (SELECT ID_CATALOGO_OPCION AS ID_ENTIDAD, ");
		select.append("                OPCION AS ENTIDAD ");
		select.append("           FROM CATALOGO_OPCION ");
		select.append("          WHERE ID_CATALOGO = "+ CATALOGO_OPCION_ENTIDAD_FEDERATIVA +" OR ID_CATALOGO = "+ CATALOGO_OPCION_PROVINCIAS_CANADA +" ), ");
		
		select.append("MUNICIPIO_CIUDAD     AS (SELECT ID_ENTIDAD, ID_MUNICIPIO, MUNICIPIO FROM MUNICIPIO ");
		select.append("UNION ");
		select.append("SELECT ID_PROVINCIA, ID_CIUDAD, CIUDAD FROM CIUDAD_CANADA ),");
		
		
		select.append("CARRERAS      AS (SELECT DISTINCT ID_CATALOGO_OPCION AS ID_CARRERA, ");
		select.append("                OPCION AS CARRERA ");
		select.append("           FROM CATALOGO_OPCION ");
		select.append("          WHERE (ID_CATALOGO = "+ CATALOGO_OPCION_CARRERA_TECNICA_COMERCIAL_BASICO +" OR ");
		select.append("                 ID_CATALOGO = "+ CATALOGO_OPCION_CARRERA_TECNICA_COMERCIAL_SUPERIOR +" OR ");
		select.append("                 ID_CATALOGO = "+ CATALOGO_OPCION_CARRERA_PROFESIONAL +" OR ");
		select.append("                 ID_CATALOGO = "+ CATALOGO_OPCION_CARRERA_MAESTRIA_DOCTORADO +" OR ");
		select.append("                 ID_CATALOGO = "+ CATALOGO_OPCION_SIN_ESPECIALIDAD +")), ");
		
		select.append("CANDIDATOS    AS (SELECT OC.ID_OFERTA_EMPLEO, ");
		select.append("                COUNT(ID_CANDIDATO) AS TOTAL_CANDIDATOS ");
		select.append("           FROM OFERTA_EMPLEO OE, ");
		select.append("                OFERTA_CANDIDATO OC ");
		select.append("          WHERE OE.ID_EMPRESA = "+ idEmpresa +" ");
		select.append("            AND (OE.ESTATUS = "+ ESTATUS.ACTIVO.getIdOpcion() +" OR OE.ESTATUS = "+ ESTATUS.ABRIENDO_ESPACIOS_ACTIVO.getIdOpcion() +" OR OE.ESTATUS = "+ ESTATUS.CANCELADA.getIdOpcion() +") ");
		select.append("            AND OC.ID_OFERTA_EMPLEO = OE.ID_OFERTA_EMPLEO ");
		select.append("            AND (OC.ESTATUS = "+ ESTATUS.VINCULADO.getIdOpcion()    +" OR ");
		select.append("                 OC.ESTATUS = "+ ESTATUS.SELECCIONADO.getIdOpcion() +" OR ");
		select.append("                 OC.ESTATUS = "+ ESTATUS.SELECCIONADA.getIdOpcion() +") ");
		select.append("       GROUP BY OC.ID_OFERTA_EMPLEO), ");

		select.append("POSTULADOS    AS (SELECT OC.ID_OFERTA_EMPLEO, ");
		select.append("                COUNT(ID_CANDIDATO) AS TOTAL_POSTULADOS ");
		select.append("           FROM OFERTA_EMPLEO OE, ");
		select.append("                OFERTA_CANDIDATO OC ");
		select.append("          WHERE OE.ID_EMPRESA = "+ idEmpresa +" ");
		select.append("            AND (OE.ESTATUS = "+ ESTATUS.ACTIVO.getIdOpcion() +" OR OE.ESTATUS = "+ ESTATUS.ABRIENDO_ESPACIOS_ACTIVO.getIdOpcion() +" OR OE.ESTATUS = "+ ESTATUS.CANCELADA.getIdOpcion() +") ");
		select.append("            AND OC.ID_OFERTA_EMPLEO = OE.ID_OFERTA_EMPLEO ");
		select.append("            AND (OC.ESTATUS = "+ ESTATUS.POSTULADO.getIdOpcion()  +" OR ");
		select.append("                 OC.ESTATUS = "+ ESTATUS.EN_PROCESO.getIdOpcion() +") ");
		select.append("       GROUP BY OC.ID_OFERTA_EMPLEO), ");
		
		select.append("PREGUNTAS    AS (SELECT PRE.ID_OFERTA_EMPLEO, ");
		select.append("                COUNT(ID_OFERTA_PREGUNTA) AS TOTAL_PREGUNTAS ");
		select.append("           FROM OFERTA_EMPLEO OE, ");
		select.append("                OFERTA_PREGUNTA PRE ");
		select.append("          WHERE OE.ID_EMPRESA = "+ idEmpresa +" ");
		select.append("            AND (OE.ESTATUS = "+ ESTATUS.ACTIVO.getIdOpcion() +" OR OE.ESTATUS = "+ ESTATUS.ABRIENDO_ESPACIOS_ACTIVO.getIdOpcion() +" OR OE.ESTATUS = "+ ESTATUS.CANCELADA.getIdOpcion() +") ");
		select.append("            AND PRE.ID_OFERTA_EMPLEO = OE.ID_OFERTA_EMPLEO ");
		select.append("          GROUP BY PRE.ID_OFERTA_EMPLEO) ");
		       
		select.append("SELECT NVL(CAN.TOTAL_CANDIDATOS,0) AS TOTAL_CANDIDATOS, ");
		select.append("       NVL(POS.TOTAL_POSTULADOS,0) AS TOTAL_POSTULADOS, ");
		select.append("       NVL(PRE.TOTAL_PREGUNTAS,0) AS TOTAL_PREGUNTAS, ");
		select.append("       OCU.ID_OCUPACION, ");
		select.append("       OCU.OCUPACION, ");
		select.append("       NIV.ID_NIVEL_ESTUDIO, ");
		select.append("       NIV.NIVEL_ESTUDIO, ");
		select.append("       ENT.ID_ENTIDAD, ");
		select.append("       ENT.ENTIDAD, ");
		select.append("       MUN.ID_MUNICIPIO, ");
		select.append("       MUN.MUNICIPIO, ");
		select.append("       OCE.ID_CARRERA_ESPECIALIDAD, ");
		select.append("       CARR.CARRERA, ");
		select.append("       OE.ID_OFERTA_EMPLEO, ");
		select.append("       OE.TITULO_OFERTA, ");
		select.append("       OE.ESTATUS,  ");
		select.append("	      OE.FECHA_ALTA, ");
		select.append("	      OE.FUENTE, ");
		select.append("	      OE.FECHA_INICIO, ");
		select.append("	      OE.DISCAPACIDADES, ");
		select.append("	      AREA.DESCRIPCION, ");
		select.append("	      SUBAREA.DESCRIPCION ");
		select.append("  FROM OFERTA_EMPLEO OE, ");
		select.append("       OFERTA_CARRERA_ESPEC OCE, ");
		select.append("       OFERTA_UBICACION DOM, ");
		select.append("       OCUPACIONES OCU, ");
		select.append("       NIVEL_ESTUDIO NIV, ");
		select.append("       POSTULADOS POS, ");
		select.append("       CANDIDATOS CAN, ");
		select.append("       PREGUNTAS PRE, ");
		select.append("       ENTIDADES ENT, ");
		select.append("       MUNICIPIO_CIUDAD MUN, ");
		select.append("       CARRERAS CARR, ");
		select.append("       CAT_AREA AREA, ");
		select.append("       CAT_SUBAREA SUBAREA ");
		select.append("WHERE OE.ID_EMPRESA = "+ idEmpresa +" ");
		select.append("  AND (OE.ESTATUS = "+ ESTATUS.ACTIVO.getIdOpcion() +" OR ");
		select.append("       OE.ESTATUS = "+ ESTATUS.ABRIENDO_ESPACIOS_ACTIVO.getIdOpcion() +" OR ");
		select.append("       OE.ESTATUS = "+ ESTATUS.CANCELADA.getIdOpcion() +") ");
		select.append("  AND OE.ID_OFERTA_EMPLEO     = OCE.ID_OFERTA_EMPLEO(+) ");
		select.append("  AND OCE.PRINCIPAL(+)        = "+ MULTIREGISTRO.PRINCIPAL.getIdOpcion() +" ");
		select.append("  AND DOM.ID_OFERTA_EMPLEO      = OE.ID_OFERTA_EMPLEO ");
		select.append("  AND OE.ID_OCUPACION         = OCU.ID_OCUPACION(+) ");
		select.append("  AND OE.ID_NIVEL_ESTUDIO     = NIV.ID_NIVEL_ESTUDIO(+) ");
		select.append("  AND OE.ID_OFERTA_EMPLEO     = CAN.ID_OFERTA_EMPLEO(+) ");
		select.append("  AND OE.ID_OFERTA_EMPLEO     = POS.ID_OFERTA_EMPLEO(+) ");
		select.append("  AND OE.ID_OFERTA_EMPLEO     = PRE.ID_OFERTA_EMPLEO(+) ");
		select.append("  AND ENT.ID_ENTIDAD          = DOM.ID_ENTIDAD ");
		select.append("  AND MUN.ID_ENTIDAD          = DOM.ID_ENTIDAD ");
		select.append("  AND MUN.ID_MUNICIPIO        = DOM.ID_MUNICIPIO ");
		select.append("  AND OCE.ID_CARRERA_ESPECIALIDAD = CARR.ID_CARRERA(+) ");
		select.append("  AND AREA.ID_AREA(+) = OE.ID_AREA ");
		select.append("  AND SUBAREA.ID_SUBAREA(+) = OE.ID_SUBAREA ");
		
		select.append("ORDER BY OE.ESTATUS ASC, OE.FECHA_MODIFICACION DESC ");
		
		Query query = entityManager.createNativeQuery(select.toString());

		@SuppressWarnings("unchecked")
		List<Object[]> rowSet = query.getResultList();

		for (Object[] rs : rowSet) {
			OfertaEmpleoVO oferta = new OfertaEmpleoVO();
			oferta.setTotalCandidatos          (Utils.toInt(   rs[0]));//TOTAL_CANDIDATOS
			oferta.setTotalPostulados          (Utils.toInt(   rs[1]));//TOTAL_POSTULADOSS
			oferta.setNumeroPreguntas          (Utils.toInt(   rs[2]));//TOTAL_PREGUNTAS,
			                                  //Utils.toLong(  rs[3]);//ID_OCUPACION
			oferta.setOcupacionDescripcion     (Utils.toString(rs[4]));//OCUPACION
			                                  //Utils.toLong(  rs[5]);//ID_NIVEL_ESTUDIO
			oferta.setNivelEstudiosDescripcion(Utils.toString( rs[6]));//NIVEL_ESTUDIO
			                                  //Utils.toLong(  rs[7]);//ID_ENTIDAD
			oferta.setEntidadDescripcion       (Utils.toString(rs[8]));//ENTIDAD
			                                  //Utils.toLong(  rs[9]);//ID_MUNICIPIO
			oferta.setMunicipioDescripcion     (Utils.toString(rs[10]));//MUNICIPIO
			                                  //Utils.toLong(  rs[11]);//ID_CARRERA_ESPECIALIDAD
			oferta.setCarreraDescripcion       (Utils.toString(rs[12]));//CARR.CARRERA
			oferta.setIdOfertaEmpleo           (Utils.toLong(  rs[13]));//ID_OFERTA_EMPLEO
			oferta.setTituloOferta             (Utils.toString(rs[14]));//TITULO_OFERTA
			oferta.setEstatus                  (Utils.toInt(   rs[15])); //ESTATUS
			oferta.setFechaAlta				   (Utils.toDate(rs[16]));//FECHA ALTA
			oferta.setFechaInicio              (Utils.toDate(rs[18]));//FECHA INICIO
			oferta.setDiscapacidades		   (Utils.toString(rs[19])); //DISCAPACIDADES
			oferta.setAreaLaboralDescripcion   (Utils.toString(rs[20]));//AREA LABORAL
			oferta.setSubAreaLaboralDescripcion(Utils.toString(rs[21]));//SUBAREA LABORAL
			if (null != oferta.getDiscapacidades())
				oferta.setDescripcionesDiscapacidades(oferta.getDescripcionDiscapacidades(oferta.getDiscapacidades()));
			ofertas.add(oferta);
		}
		
		return ofertas;
	}

	@SuppressWarnings("unused")
	private UbicacionCanadaVO getUbicacionCanada(long idOfertaEmpleo) {
		return ofertaUbicacion.getUbicacionOfertaCanada(idOfertaEmpleo);
	}

	/**
	 *  Busca una oferta de la empresa por Folio o TÃ¯Â¿Â½tulo de la Oferta
	 *  con Estatus Activo Ã¯Â¿Â½ Cancelado
	 *   
	 * @param idEmpresa
	 * @param folioOferta
	 * @param tituloOferta
	 * @return List<OfertaEmpleoVO> ofertas
	 */
	public List<OfertaEmpleoVO> consultaOfertaByFolioTitulo(long idEmpresa, Long folioOferta, String tituloOferta) throws SQLException {
		List<OfertaEmpleoVO> ofertas = new ArrayList<OfertaEmpleoVO>();
		
		StringBuilder select = new StringBuilder();

		select.append("WITH OCUPACIONES AS (SELECT ID_CATALOGO_OPCION AS ID_OCUPACION, ");
		select.append("                OPCION AS OCUPACION ");
		select.append("           FROM CATALOGO_OPCION ");
		select.append("          WHERE ID_CATALOGO = "+ CATALOGO_OPCION_OCUPACION +"), ");
		
		select.append("NIVEL_ESTUDIO AS (SELECT ID_CATALOGO_OPCION AS ID_NIVEL_ESTUDIO, ");
		select.append("                OPCION AS NIVEL_ESTUDIO ");
		select.append("           FROM CATALOGO_OPCION ");
		select.append("          WHERE ID_CATALOGO = "+ CATALOGO_OPCION_GRADO_ESTUDIOS +"), ");
		
		select.append("ENTIDADES     AS (SELECT ID_CATALOGO_OPCION AS ID_ENTIDAD, ");
		select.append("                OPCION AS ENTIDAD ");
		select.append("           FROM CATALOGO_OPCION ");
		select.append("          WHERE ID_CATALOGO = "+ CATALOGO_OPCION_ENTIDAD_FEDERATIVA +"), ");
		
		select.append("CARRERAS      AS (SELECT DISTINCT ID_CATALOGO_OPCION AS ID_CARRERA, ");
		select.append("                OPCION AS CARRERA ");
		select.append("           FROM CATALOGO_OPCION ");
		select.append("          WHERE (ID_CATALOGO = "+ CATALOGO_OPCION_CARRERA_TECNICA_COMERCIAL_BASICO +" OR ");
		select.append("                 ID_CATALOGO = "+ CATALOGO_OPCION_CARRERA_TECNICA_COMERCIAL_SUPERIOR +" OR ");
		select.append("                 ID_CATALOGO = "+ CATALOGO_OPCION_CARRERA_PROFESIONAL +" OR ");
		select.append("                 ID_CATALOGO = "+ CATALOGO_OPCION_CARRERA_MAESTRIA_DOCTORADO +")), ");
		
		select.append("CANDIDATOS    AS (SELECT OC.ID_OFERTA_EMPLEO, ");
		select.append("                COUNT(ID_CANDIDATO) AS TOTAL_CANDIDATOS ");
		select.append("           FROM OFERTA_EMPLEO OE, ");
		select.append("                OFERTA_CANDIDATO OC ");
		select.append("          WHERE OE.ID_EMPRESA = "+ idEmpresa +" ");
		select.append("            AND (OE.ESTATUS = "+ ESTATUS.ACTIVO.getIdOpcion());
		select.append("                 OR OE.ESTATUS = "+ ESTATUS.ABRIENDO_ESPACIOS_ACTIVO.getIdOpcion() );
		select.append("                 OR OE.ESTATUS = "+ ESTATUS.CANCELADA.getIdOpcion() +") ");
		select.append("            AND OC.ID_OFERTA_EMPLEO = OE.ID_OFERTA_EMPLEO ");
		select.append("            AND (OC.ESTATUS = "+ ESTATUS.VINCULADO.getIdOpcion()    +" OR ");
		select.append("                 OC.ESTATUS = "+ ESTATUS.SELECCIONADO.getIdOpcion() +" OR ");
		select.append("                 OC.ESTATUS = "+ ESTATUS.SELECCIONADA.getIdOpcion() +") ");
		select.append("       GROUP BY OC.ID_OFERTA_EMPLEO), ");

		select.append("POSTULADOS    AS (SELECT OC.ID_OFERTA_EMPLEO, ");
		select.append("                COUNT(ID_CANDIDATO) AS TOTAL_POSTULADOS ");
		select.append("           FROM OFERTA_EMPLEO OE, ");
		select.append("                OFERTA_CANDIDATO OC ");
		select.append("          WHERE OE.ID_EMPRESA = "+ idEmpresa +" ");
		select.append("            AND (OE.ESTATUS = "+ ESTATUS.ACTIVO.getIdOpcion());
		select.append("                 OR OE.ESTATUS = "+ ESTATUS.ABRIENDO_ESPACIOS_ACTIVO.getIdOpcion() );
		select.append("                 OR OE.ESTATUS = "+ ESTATUS.CANCELADA.getIdOpcion() +") ");		
		select.append("            AND OC.ID_OFERTA_EMPLEO = OE.ID_OFERTA_EMPLEO ");
		select.append("            AND (OC.ESTATUS = "+ ESTATUS.POSTULADO.getIdOpcion()  +" OR ");
		select.append("                 OC.ESTATUS = "+ ESTATUS.EN_PROCESO.getIdOpcion() +") ");
		select.append("       GROUP BY OC.ID_OFERTA_EMPLEO), ");
		
		select.append("PREGUNTAS    AS (SELECT PRE.ID_OFERTA_EMPLEO, ");
		select.append("                COUNT(ID_OFERTA_PREGUNTA) AS TOTAL_PREGUNTAS ");
		select.append("           FROM OFERTA_EMPLEO OE, ");
		select.append("                OFERTA_PREGUNTA PRE ");
		select.append("          WHERE OE.ID_EMPRESA = "+ idEmpresa +" ");
		select.append("            AND (OE.ESTATUS = "+ ESTATUS.ACTIVO.getIdOpcion());
		select.append("                 OR OE.ESTATUS = "+ ESTATUS.ABRIENDO_ESPACIOS_ACTIVO.getIdOpcion() );
		select.append("                 OR OE.ESTATUS = "+ ESTATUS.CANCELADA.getIdOpcion() +") ");
		select.append("            AND PRE.ID_OFERTA_EMPLEO = OE.ID_OFERTA_EMPLEO ");
		select.append("          GROUP BY PRE.ID_OFERTA_EMPLEO) ");
		       
		select.append("SELECT NVL(CAN.TOTAL_CANDIDATOS,0) AS TOTAL_CANDIDATOS, ");
		select.append("       NVL(POS.TOTAL_POSTULADOS,0) AS TOTAL_POSTULADOS, ");
		select.append("       NVL(PRE.TOTAL_PREGUNTAS,0) AS TOTAL_PREGUNTAS, ");
		select.append("       OCU.ID_OCUPACION, ");
		select.append("       OCU.OCUPACION, ");
		select.append("       NIV.ID_NIVEL_ESTUDIO, ");
		select.append("       NIV.NIVEL_ESTUDIO, ");
		select.append("       ENT.ID_ENTIDAD, ");
		select.append("       ENT.ENTIDAD, ");
		select.append("       MUN.ID_MUNICIPIO, ");
		select.append("       MUN.MUNICIPIO, ");
		select.append("       OCE.ID_CARRERA_ESPECIALIDAD, ");
		select.append("       CARR.CARRERA, ");
		select.append("       OE.ID_OFERTA_EMPLEO, ");
		select.append("       OE.TITULO_OFERTA, ");
		select.append("       OE.ESTATUS, ");
		select.append("	      OE.FECHA_ALTA, ");
		select.append("	      OE.DISCAPACIDADES ");
		select.append("  FROM OFERTA_EMPLEO OE, ");
		select.append("       OFERTA_CARRERA_ESPEC OCE, ");
		select.append("       OFERTA_UBICACION DOM, ");
		select.append("       OCUPACIONES OCU, ");
		select.append("       NIVEL_ESTUDIO NIV, ");
		select.append("       POSTULADOS POS, ");
		select.append("       CANDIDATOS CAN, ");
		select.append("       PREGUNTAS PRE, ");
		select.append("       ENTIDADES ENT, ");
		select.append("       MUNICIPIO MUN, ");
		select.append("       CARRERAS CARR ");
		select.append("WHERE OE.ID_EMPRESA = "+ idEmpresa +" ");
		if(folioOferta != null)
			select.append(" AND OE.ID_OFERTA_EMPLEO = " + folioOferta);
		if(tituloOferta != null)
			select.append(" AND UPPER(OE.TITULO_OFERTA) LIKE '%" + tituloOferta.toUpperCase() + "%'");
		select.append("            AND (OE.ESTATUS = "+ ESTATUS.ACTIVO.getIdOpcion());
		select.append("                 OR OE.ESTATUS = "+ ESTATUS.ABRIENDO_ESPACIOS_ACTIVO.getIdOpcion() );
		select.append("                 OR OE.ESTATUS = "+ ESTATUS.CANCELADA.getIdOpcion() +") ");
		select.append("  AND OE.ID_OFERTA_EMPLEO     = OCE.ID_OFERTA_EMPLEO(+) ");
		select.append("  AND OCE.PRINCIPAL(+)        = "+ MULTIREGISTRO.PRINCIPAL.getIdOpcion() +" ");
		select.append("  AND DOM.ID_OFERTA_EMPLEO      = OE.ID_OFERTA_EMPLEO ");
		//select.append("  AND DOM.ID_TIPO_PROPIETARIO = "+ TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario() +" ");
		select.append("  AND OE.ID_OCUPACION         = OCU.ID_OCUPACION(+) ");
		select.append("  AND OE.ID_NIVEL_ESTUDIO     = NIV.ID_NIVEL_ESTUDIO(+) ");
		select.append("  AND OE.ID_OFERTA_EMPLEO     = CAN.ID_OFERTA_EMPLEO(+) ");
		select.append("  AND OE.ID_OFERTA_EMPLEO     = POS.ID_OFERTA_EMPLEO(+) ");
		select.append("  AND OE.ID_OFERTA_EMPLEO     = PRE.ID_OFERTA_EMPLEO(+) ");
		select.append("  AND ENT.ID_ENTIDAD          = DOM.ID_ENTIDAD ");
		select.append("  AND MUN.ID_ENTIDAD          = DOM.ID_ENTIDAD ");
		select.append("  AND MUN.ID_MUNICIPIO        = DOM.ID_MUNICIPIO ");
		select.append("  AND OCE.ID_CARRERA_ESPECIALIDAD = CARR.ID_CARRERA(+) ");
		
		select.append("ORDER BY OE.ESTATUS ASC, OE.FECHA_MODIFICACION DESC ");

		Query query = entityManager.createNativeQuery(select.toString());

		@SuppressWarnings("unchecked")
		List<Object[]> rowSet = query.getResultList();

		for (Object[] rs : rowSet) {
			OfertaEmpleoVO oferta = new OfertaEmpleoVO();
			oferta.setTotalCandidatos          (Utils.toInt(   rs[0]));//TOTAL_CANDIDATOS
			oferta.setTotalPostulados          (Utils.toInt(   rs[1]));//TOTAL_POSTULADOSS
			oferta.setNumeroPreguntas          (Utils.toInt(   rs[2]));//TOTAL_PREGUNTAS,
			                                  //Utils.toLong(  rs[3]);//ID_OCUPACION
			oferta.setOcupacionDescripcion     (Utils.toString(rs[4]));//OCUPACION
			                                  //Utils.toLong(  rs[5]);//ID_NIVEL_ESTUDIO
			oferta.setNivelEstudiosDescripcion(Utils.toString( rs[6]));//NIVEL_ESTUDIO
			                                  //Utils.toLong(  rs[7]);//ID_ENTIDAD
			oferta.setEntidadDescripcion       (Utils.toString(rs[8]));//ENTIDAD
			                                  //Utils.toLong(  rs[9]);//ID_MUNICIPIO
			oferta.setMunicipioDescripcion     (Utils.toString(rs[10]));//MUNICIPIO
			                                  //Utils.toLong(  rs[11]);//ID_CARRERA_ESPECIALIDAD
			oferta.setCarreraDescripcion       (Utils.toString(rs[12]));//CARR.CARRERA
			oferta.setIdOfertaEmpleo           (Utils.toLong(  rs[13]));//ID_OFERTA_EMPLEO
			oferta.setTituloOferta             (Utils.toString(rs[14]));//TITULO_OFERTA
			oferta.setEstatus                  (Utils.toInt(   rs[15])); //ESTATUS
			oferta.setFechaAlta				   (Utils.toDate(rs[16]));//FECHA ALTA
			oferta.setDiscapacidades		   (Utils.toString(rs[17])); //DISCAPACIDADES
			oferta.setDescripcionesDiscapacidades(oferta.getDescripcionDiscapacidades(oferta.getDiscapacidades()));
			
			ofertas.add(oferta);
		}
		
		return ofertas;
	}
	
	/*Obtener ofertas para el reporte de ofertas de una empresa dada */
	public List<ReporteOfertasEmpresaVO> getOfertasEmpresaReporte(long idEmpresa, int selectedStatus, 
			String selectedInitial, String selectedFinal) {
		List<ReporteOfertasEmpresaVO> lista = new ArrayList<ReporteOfertasEmpresaVO>();
		StringBuilder select = new StringBuilder();
		select.append("WITH ESTATI AS (SELECT ID_CATALOGO_OPCION AS ID_ESTATUS, OPCION AS ESTATUS_OFERTA ");
		select.append("			FROM CATALOGO_OPCION ");
		select.append("			WHERE ID_CATALOGO = " + Constantes.CATALOGO_OPCION_ESTATUS + "), ");
		select.append("		OCUPACIONES AS (SELECT ID_CATALOGO_OPCION AS ID_OCUPACION, OPCION AS OCUPACION ");
		select.append("			FROM CATALOGO_OPCION ");
		select.append("			WHERE ID_CATALOGO = " + CATALOGO_OPCION_OCUPACION + "), ");
		select.append("		NIVEL_ESTUDIO AS (SELECT ID_CATALOGO_OPCION AS ID_NIVEL_ESTUDIO, OPCION AS NIVEL_ESTUDIO ");
		select.append("			FROM CATALOGO_OPCION ");
		select.append("			WHERE ID_CATALOGO = " + CATALOGO_OPCION_GRADO_ESTUDIOS + "), ");
		select.append("		ENTIDADES AS (SELECT ID_CATALOGO_OPCION AS ID_ENTIDAD, OPCION AS ENTIDAD ");
		select.append("			FROM CATALOGO_OPCION ");
		select.append("			WHERE ID_CATALOGO = " + CATALOGO_OPCION_ENTIDAD_FEDERATIVA + "), ");
		select.append("		EXPERIENCIAS AS (SELECT ID_CATALOGO_OPCION AS ID_EXPERIENCIA_ANIOS, OPCION AS EXPER_DESC ");
		select.append("			FROM CATALOGO_OPCION ");
		select.append("			WHERE ID_CATALOGO = " + CATALOGO_OPCION_EXPERIENCIA + "), ");		
		select.append("		CARRERAS AS (SELECT DISTINCT ID_CATALOGO_OPCION AS ID_CARRERA, OPCION AS CARRERA ");		
		select.append("			FROM CATALOGO_OPCION ");
		select.append("			WHERE (ID_CATALOGO = " + CATALOGO_OPCION_CARRERA_TECNICA_COMERCIAL_BASICO   + " OR ");
		select.append("				   ID_CATALOGO = " + CATALOGO_OPCION_CARRERA_TECNICA_COMERCIAL_SUPERIOR + " OR ");
		select.append("				   ID_CATALOGO = " + CATALOGO_OPCION_CARRERA_PROFESIONAL + " OR ");
		select.append("				   ID_CATALOGO = " + CATALOGO_OPCION_CARRERA_MAESTRIA_DOCTORADO + " )), ");
		
		select.append("		TELEFONOS AS (SELECT OE.ID_OFERTA_EMPLEO, NVL(TEL.ACCESO,'') || NVL(TEL.CLAVE,'') || TEL.TELEFONO || NVL(TEL.EXTENSION,'') AS TELEFONO  ");
		select.append("		FROM OFERTA_EMPLEO OE LEFT JOIN TELEFONO TEL  ON TEL.ID_PROPIETARIO = OE.ID_OFERTA_EMPLEO AND TEL.ID_TIPO_PROPIETARIO=" + 
				TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
		select.append("		AND TEL.PRINCIPAL = " + MULTIREGISTRO.PRINCIPAL.getIdOpcion() + " WHERE OE.ID_EMPRESA = ?1 ) ");		
		
		select.append("SELECT OCU.ID_OCUPACION, OCU.OCUPACION, NIV.ID_NIVEL_ESTUDIO, NIV.NIVEL_ESTUDIO, ");
		select.append("		ENT.ID_ENTIDAD, ENT.ENTIDAD, MUN.ID_MUNICIPIO, MUN.MUNICIPIO, OCE.ID_CARRERA_ESPECIALIDAD, CARR.CARRERA, ");		
		select.append("		OE.ID_OFERTA_EMPLEO AS FOLIO, TITULO_OFERTA, EST.ID_ESTATUS, EST.ESTATUS_OFERTA, NUMERO_PLAZAS, GENERO, ");
		select.append("		DECODE(GENERO, 1, 'Masculino', 2, 'Femenino',  3, 'No es requisito') as DES_GENERO, ");
		select.append("		EXPER.EXPER_DESC, NOMBRE_CONTACTO, CORREO_ELECTRONICO_CONTACTO, NOMBRE_EMPRESA, OE.ID_VIGENCIA_OFERTA, ");
		select.append("		OE.FECHA_INICIO, OE.FECHA_FIN, OE.FECHA_ALTA, OE.SALARIO, TEL.TELEFONO, OE.ID_AREA, OE.ID_SUBAREA ");		
		select.append(" FROM OFERTA_EMPLEO OE ");
		select.append(" INNER JOIN ESTATI EST ON EST.ID_ESTATUS = OE.ESTATUS ");
		select.append(" LEFT JOIN OCUPACIONES OCU ON OCU.ID_OCUPACION = OE.ID_OCUPACION");	
		select.append(" LEFT JOIN NIVEL_ESTUDIO NIV ON NIV.ID_NIVEL_ESTUDIO = OE.ID_NIVEL_ESTUDIO ");
		select.append(" LEFT JOIN OFERTA_CARRERA_ESPEC OCE ON OCE.ID_OFERTA_EMPLEO = OE.ID_OFERTA_EMPLEO ");
		select.append(" AND OCE.PRINCIPAL = " + MULTIREGISTRO.PRINCIPAL.getIdOpcion() + " ");
		select.append(" LEFT JOIN OFERTA_UBICACION OU ON OU.ID_OFERTA_EMPLEO = OE.ID_OFERTA_EMPLEO ");
		select.append(" LEFT JOIN ENTIDADES ENT ON OU.ID_ENTIDAD = ENT.ID_ENTIDAD ");
		select.append(" LEFT JOIN MUNICIPIO MUN ON OU.ID_ENTIDAD = MUN.ID_ENTIDAD  ");
		select.append(" AND MUN.ID_MUNICIPIO = OU.ID_MUNICIPIO ");			
		select.append(" LEFT JOIN EXPERIENCIAS EXPER ON OE.EXPERIENCIA_ANIOS = EXPER.ID_EXPERIENCIA_ANIOS ");		
		select.append(" LEFT JOIN CARRERAS CARR ON OCE.ID_CARRERA_ESPECIALIDAD = CARR.ID_CARRERA ");				
		select.append(" INNER JOIN TELEFONOS TEL ON OE.ID_OFERTA_EMPLEO=TEL.ID_OFERTA_EMPLEO ");			
		select.append(" WHERE OE.ID_EMPRESA = ?2 ");		
		if (selectedStatus>0) {
			select.append("		AND OE.ESTATUS = ?3 ");
			if (null!=selectedInitial && null!=selectedFinal) {
				select.append("		AND OE.FECHA_ALTA BETWEEN '" + selectedInitial + "' AND '" + selectedFinal + "' ");
			}
		}else {
			//UNA OFERTA SI PUEDE ESTAR EN 1,2,10,12,13,14,15,28,50
			String filtroEstatusNoPermitidos = "3,4,5,6,7,8,9,11,16,17,18,19,20,21,22,23,24,25,26,27,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,51,52,53,54,55,56,57,58,59,60,61,62,63";
			select.append("		AND OE.ESTATUS NOT IN(" + filtroEstatusNoPermitidos +  ") ");
			
			if (null!=selectedInitial && null!=selectedFinal) {
				select.append("		AND OE.FECHA_ALTA BETWEEN '" + selectedInitial + "' AND '" + selectedFinal + "' ");
			}
		}
		select.append(" ORDER BY FECHA_ALTA");

		Query query = entityManager.createNativeQuery(select.toString());
		query.setParameter(1, idEmpresa);
		query.setParameter(2, idEmpresa);
		if (selectedStatus>0) {
			query.setParameter(3, selectedStatus);
		}
		@SuppressWarnings("unchecked")
		List<Object[]> rowSet = query.getResultList();
		for (Object[] rs : rowSet) {
			ReporteOfertasEmpresaVO oferta = new ReporteOfertasEmpresaVO();
			oferta.setIdOcupacion(Utils.toLong(rs[0]));
			oferta.setOcupacion(Utils.toString(rs[1]));
			oferta.setIdGradoEstudios(Utils.toLong(rs[2]));
			oferta.setGradoEstudios(Utils.toString(rs[3]));
			oferta.setIdEntidad(Utils.toInt(rs[4]));
			oferta.setEntidad(Utils.toString(rs[5]));
			oferta.setIdMunicipio(Utils.toLong(rs[6]));
			oferta.setMunicipio(Utils.toString(rs[7]));
			oferta.setIdCarreraEspecialidad(Utils.toLong(rs[8]));
			oferta.setCarreraEspecialidad(Utils.toString(rs[9]));
			oferta.setIdOfertaEmpleo(Utils.toLong(rs[10]));
			oferta.setTituloOferta(Utils.toString(rs[11]));
			oferta.setIdEstatus(Utils.toInt(rs[12]));
			oferta.setEstatus(Utils.toString(rs[13]));
			oferta.setNumeroPlazas(Utils.toLong(rs[14]));
			oferta.setIdGenero(Utils.toInt(rs[15]));
			oferta.setGenero(Utils.toString(rs[16]));
			//oferta.setExperienciaAnios(Utils.toInt(rs[17]));
			oferta.setDescripcionExperienciaAnios(Utils.toString(rs[17]));
			oferta.setNombreContacto(Utils.toString(rs[18]));
			oferta.setCorreoElectronicoContacto(Utils.toString(rs[19]));
			oferta.setEmpresaNombre(Utils.toString(rs[20]));
			oferta.setIdVigenciaOferta(Utils.toInt(rs[21]));
			oferta.setFechaInicio(Utils.toDate(rs[22]));
			oferta.setFechaFin(Utils.toDate(rs[23]));
			oferta.setFechaAlta(Utils.toDate(rs[24]));
			oferta.setSalario(Utils.toDouble(rs[25]));
			oferta.setTelefonoPrincipal(Utils.toString(rs[26]));
			oferta.setIdArea(Utils.toLong(rs[27]));
			oferta.setIdSubArea(Utils.toLong(rs[28]));
			lista.add(oferta);
		}
		return lista;
	}
	
	@Override
	public List<OfertaRequisitoVO> getRequisitosOferta(long idOfertaEmpleo, long idTipoRequisito) {
		List<OfertaRequisitoVO> lista = new ArrayList<OfertaRequisitoVO>();
		StringBuilder sb = new StringBuilder();
		try {
			sb.append("SELECT c FROM OfertaRequisito c WHERE c.idOfertaEmpleo=:idOferta");
			Query query = entityManager.createQuery(sb.toString());
			OfertaRequisitoVO item;
			OfertaRequisito elemento;
			query.setParameter("idOferta", idOfertaEmpleo);
			@SuppressWarnings("unchecked")
			List<Object> result1 = query.getResultList();
			for (Object resultElement : result1) {

				item = new OfertaRequisitoVO();
				elemento = (OfertaRequisito) resultElement;
				if(elemento.getIdTipoRequisito()==idTipoRequisito){
				item.setDescripcion(elemento.getDescripcion());
				item.setIdDominio(elemento.getIdDominio());
				item.setIdExperiencia(elemento.getIdExperiencia());
				item.setPrincipal(elemento.getPrincipal());
				item.setIdTipoRequisito(elemento.getIdTipoRequisito());
				item.setIdOfertaRequisito(elemento.getIdOfertaRequisito());
					
				lista.add(item);
				}
			}
		} catch (NoResultException re) {
			// No se localizaron registros
			re.printStackTrace();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}
		return lista;
	}

	public OfertaEmpleoVO find(long idOfertaEmpleo){
		OfertaEmpleoVO oferta = null;
		
		try{
			OfertaEmpleo entity = entityManager.find(OfertaEmpleo.class, idOfertaEmpleo);

			if (entity!=null)
				oferta = getOfertaEmpleoVO(entity);				

		} catch (NoResultException re) {
			// No se localizaron registros
		} catch (RuntimeException re) {
			re.printStackTrace(); logger.error(re);
			throw new PersistenceException(re);
		}
		
		return oferta;
	}

	private OfertaEmpleoVO getOfertaEmpleoVO(OfertaEmpleo entity){
		OfertaEmpleoVO vo = new OfertaEmpleoVO();

		if(entity.getIdOfertaEmpleo() != null)
			vo.setIdOfertaEmpleo(entity.getIdOfertaEmpleo());
		if(entity.getIdEmpresa() != null)
			vo.setIdEmpresa(entity.getIdEmpresa());
		if(entity.getTituloOferta() != null)
			vo.setTituloOferta(entity.getTituloOferta());
		if(entity.getIdAreaLaboral() != null)
			vo.setIdAreaLaboral(entity.getIdAreaLaboral());
		if(entity.getIdOcupacion() != null)
			vo.setIdOcupacion(entity.getIdOcupacion());
		if(entity.getFunciones() != null)
			vo.setFunciones(entity.getFunciones());
		if(entity.getDiasLaborales() != null)
			vo.setDiasLaborales(entity.getDiasLaborales());
		if(entity.getHoraEntrada() != null)
			vo.setHoraEntrada(entity.getHoraEntrada());
		if(entity.getHoraSalida() != null)
			vo.setHoraSalida(entity.getHoraSalida());
		if(entity.getRolarTurno() != null)
			vo.setRolarTurno(entity.getRolarTurno());
		if(entity.getEmpresaOfrece() != null)
			vo.setEmpresaOfrece(entity.getEmpresaOfrece());
		if(entity.getSalario() != 0)
			vo.setSalario(entity.getSalario());
		if(entity.getIdTipoContrato() != null)
			vo.setIdTipoContrato(entity.getIdTipoContrato());
		if(entity.getIdJerarquia() != null)
			vo.setIdJerarquia(entity.getIdJerarquia());
		if(entity.getNumeroPlazas() != null)
			vo.setNumeroPlazas(entity.getNumeroPlazas());
		if(entity.getLimitePostulantes() != null)
			vo.setLimitePostulantes(entity.getLimitePostulantes());
		if(entity.getIdDiscapacidad() != null)
			vo.setIdDiscapacidad(entity.getIdDiscapacidad());
		if(entity.getIdCausaVacante() != null)
			vo.setIdCausaVacante(entity.getIdCausaVacante());
		if(entity.getFechaInicio() != null)
			vo.setFechaInicio(entity.getFechaInicio());
		if(entity.getFechaFin() != null)
			vo.setFechaFin(entity.getFechaFin());
		if(entity.getFuente() != null)
			vo.setFuente(entity.getFuente());
		if(entity.getDisponibilidadViajar() != null)
			vo.setDisponibilidadViajar(entity.getDisponibilidadViajar());
		if(entity.getDisponibilidadRadicar() != null)
			vo.setDisponibilidadRadicar(entity.getDisponibilidadRadicar());
		if(entity.getIdNivelEstudio() != null)
			vo.setIdNivelEstudio(entity.getIdNivelEstudio());
		if(entity.getIdSituacionAcademica() != null)
			vo.setIdSituacionAcademica(entity.getIdSituacionAcademica());
//		vo.setHabilidadGeneral(entity.getHabilidadGeneral());
		if(entity.getExperienciaAnios() != null)
			vo.setExperienciaAnios(entity.getExperienciaAnios());
		if(entity.getEdadRequisito() != null)
			vo.setEdadRequisito(entity.getEdadRequisito());
		if(entity.getEdadMinima() != null)
			vo.setEdadMinima(entity.getEdadMinima());
		if(entity.getEdadMaxima() != null)
			vo.setEdadMaxima(entity.getEdadMaxima());
		if(entity.getGenero() != null)
			vo.setGenero(entity.getGenero());
		if(entity.getMapaUbicacion() != null)
			vo.setMapaUbicacion(entity.getMapaUbicacion());
		if(entity.getObservaciones() != null)
			vo.setObservaciones(entity.getObservaciones());
//		vo.setIdTerceraEmpresa(entity.getIdTerceraEmpresa());
//		vo.setIdContacto(entity.getIdContacto());
		if(entity.getIdHorarioDe() != null)
			vo.setIdHorarioDe(entity.getIdHorarioDe());
		if(entity.getIdHorarioA() != null)
			vo.setIdHorarioA(entity.getIdHorarioA());
		if(entity.getIdDuracionAproximada() != null)
			vo.setIdDuracionAproximada(entity.getIdDuracionAproximada());
		if(entity.getDiasEntrevista() != null)
			vo.setDiasEntrevista(entity.getDiasEntrevista());
		if(entity.getContactoTel() != null)
			vo.setContactoTel(entity.getContactoTel());
		if(entity.getContactoCorreo() != null)
			vo.setContactoCorreo(entity.getContactoCorreo());
		if(entity.getFechaAlta() != null)
			vo.setFechaAlta(entity.getFechaAlta());
		if(entity.getFechaModificacion() != null)
			vo.setFechaModificacion(entity.getFechaModificacion());
		if(entity.getIdTipoEmpleo() != null)
			vo.setIdTipoEmpleo(entity.getIdTipoEmpleo());
		if(entity.getEstatus() != null)
			vo.setEstatus(entity.getEstatus());
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
		if (null != entity.getContactoDomicilio() )
			vo.setContactoDomicilio(entity.getContactoDomicilio());
		if (null != entity.getIdArea())
			vo.setIdArea(entity.getIdArea());
		if (null != entity.getIdSubarea())
			vo.setIdSubArea(entity.getIdSubarea());
		return vo;
	}
	
	@Override
	public String obtenerOcupacion(long idOferta) throws PersistenceException {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT opcion FROM oferta_empleo, catalogo_opcion WHERE id_oferta_empleo = ");
		builder.append(idOferta);
		builder.append(" AND id_catalogo = 21 AND id_catalogo_opcion = id_ocupacion");
		Query query = entityManager.createNativeQuery(builder.toString());
		return (String)query.getSingleResult();
	}

	@Override
	public List<OfertaPorCanalVO> getOffersPerFilterAdminPublisher(
			long idoffer, String idPortal, String nombreEmpresa) {
		StringBuilder filter = new StringBuilder();
		StringBuilder builder = new StringBuilder();
		List<OfertaPorCanalVO> offersList = new ArrayList<OfertaPorCanalVO>();
		StringBuilder base = new StringBuilder();		
		
		base.append("WITH REG AS (SELECT R.ESTATUS, R.ID_REGISTRO, R.ID_REG_VALIDAR, RANK()OVER( PARTITION BY R.ID_REGISTRO ORDER BY R.ID_REG_VALIDAR DESC) ORDEN "+
		                          ", V.DESCRIPCION, R.DETALLE_RECHAZO "+
		                         "FROM REGISTRO_POR_VALIDAR R, VALIDACION_MOTIVO V "+
				                 "WHERE R.ID_TIPO_REGISTRO = "+TIPO_REGISTRO.OFERTA.getIdTipoRegistro()+
				                 " AND R.ID_TIPO_PROPIETARIO = "+TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario()+
				                 " AND R.ID_MOTIVO_RECHAZO = V.ID_VALIDACION_MOTIVO(+)"+
				                 ") ");
		base.append("SELECT H.id_oferta_empleo, H.titulo_oferta, H.fecha_alta, H.fecha_modificacion, H.estatus, ");
		base.append(" CASE WHEN I.id_tipo_persona = 1 THEN I.nombre || ' ' || I.apellido1 || ' ' || I.apellido2");
		base.append(" WHEN I.id_tipo_persona = 2 THEN I.razon_social");
		//base.append(" WHEN ((NVL (H.id_tercera_empresa,0) != 0) AND (I.id_tipo_persona = 1)) THEN J.nombre || ' ' || J.apellido1 || ' ' || J.apellido2");
		//base.append(" WHEN ((NVL (H.id_tercera_empresa,0) != 0 ) AND (I.id_tipo_persona = 2)) THEN J.razon_social ");
		base.append(" END AS empresa");
		base.append(", REG.ID_REG_VALIDAR");		
		base.append(", h.id_empresa");		
		base.append(", REG.DESCRIPCION MOTIVO_RECHAZO");
		base.append(", REG.DETALLE_RECHAZO");
		base.append(" FROM oferta_empleo H, empresa I");
		//base.append(" tercera_empresa J, ");
		base.append(", REG");
		filter.append(" AND H.id_oferta_empleo=REG.ID_REGISTRO(+)");		
		filter.append(" AND 1 = REG.ORDEN(+)"); // seleccionamos el registro a validar correspondiente al Ã¯Â¿Â½ltimo estado
		if (idoffer > 0) {
			filter.append(" AND H.id_oferta_empleo=" + idoffer);
		}
		if (!nombreEmpresa.isEmpty())
			filter.append(" AND H.nombre_empresa='" + nombreEmpresa + "'");
		if (!idPortal.isEmpty())
			filter.append(" AND H.id_empresa=I.id_empresa AND I.id_portal_empleo='" + idPortal + "'");
		
		builder.append(base);
		builder.append(" WHERE H.id_empresa = I.id_empresa ");
		if (idoffer<1)builder.append(" AND H.estatus =  "+ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion() );
		//builder.append(" AND H.id_tercera_empresa = J.id_tercera_empresa(+)");
		builder.append((filter.length()>2 ? filter.toString() : ""));

		Query query = entityManager.createNativeQuery(builder.toString());
		@SuppressWarnings("unchecked")
		List<Object[]> rowSet = query.getResultList();
		for (Object[] rs : rowSet) {
			OfertaPorCanalVO offer = retrieveOffer(rs);
			offersList.add(offer);
		}
		return offersList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ResultadoBusquedaOfertasVO> buscarOfertasEspecificas(BusquedaOfertasVO vo) throws SQLException {
	//	int entidad=0;
		// ===== BEGIN SQL COMMAND ===== //
		
		StringBuilder sqlCommand = new StringBuilder();
		StringBuilder select = new StringBuilder();
		StringBuilder from = new StringBuilder();
		StringBuilder where = new StringBuilder();
		
		sqlCommand.append("WITH ENTIDADES AS (SELECT * FROM catalogo_opcion WHERE id_catalogo = 25),");
		sqlCommand.append("     TIPO_EMPLEO AS (SELECT * FROM CATALOGO_OPCION WHERE ID_CATALOGO = 15),");
		sqlCommand.append("     TIPO_CONTRATO AS (SELECT * FROM CATALOGO_OPCION WHERE ID_CATALOGO = 24), ");
		sqlCommand.append("     IDIOMAS AS (SELECT * FROM CATALOGO_OPCION WHERE ID_CATALOGO = 11) ");
		
		select.append("SELECT");
		select.append("	   oe.id_oferta_empleo, "); // [0] IdOfertaEmpleo
		select.append("	   oe.titulo_oferta, "); // [1] TituloOferta
		select.append("    oe.habilidad_general, "); // [2] Description: HabilidadGeneral
		select.append("    oe.experiencia_anios, "); // [3] Description: ExperienciaAnios
		select.append("    i.opcion as idioma, "); // [4] Description: Idiomas
		select.append("    oe.edad_minima, "); // [5] Description: EdadMinima
		select.append("    oe.edad_maxima, "); // [6] Description: EdadMaxima
		select.append("    ent.opcion as entidad, "); // [7] Ubicacion: EntidadFederativa
		select.append("    m.municipio, "); // [8] Ubicacion: Municipio
		select.append("	   e.id_tipo_persona, "); // [9] Enterprise: IdTipoPersona
		select.append("	   e.razon_social, "); // [10] Enterprise: RegisteredName
		select.append("	   e.nombre, "); // [11] Enterprise: Person Firstname
		select.append("	   e.apellido1, "); // [12] Enterprise: Person Lastname
		select.append("	   e.apellido2, "); // [13] Enterprise: Person Surname
		select.append("	   oe.salario, "); // [14] Salario
		select.append("	   oe.fecha_inicio, "); // [15] Fecha Publicacion
		select.append("	   oe.funciones, "); // [16] Funciones
		select.append("	   do.calle, ");
		select.append("	   do.NUMERO_EXTERIOR, ");
		select.append("	   do.ID_PROPIETARIO,  ");
		select.append("	   do.codigo_postal  ");
		from.append(" FROM ");
		from.append("	 oferta_empleo oe,"); // Table: OFERTA_EMPLEO
		from.append("    oferta_ubicacion ou,"); // Table: OFERTA_UBICACION
		from.append("    entidades ent,"); // Temporal Table: ENTIDADES
		from.append("	 municipio m,"); // Table: MUNICIPIO
		from.append("	 empresa e,"); // Table: EMPRESA
		from.append("    oferta_carrera_espec oc,"); // Table: OFERTA_CARRERA_ESPEC 
		from.append("    tipo_empleo te,"); // Temporal Table: TIPO_EMPLEO
		from.append("    tipo_contrato tc, "); // Temporal Table: TIPO_CONTRATO
		from.append("    idiomas i, "); // Temporal Table: IDIOMAS
		from.append("    oferta_idioma oi, "); 
		from.append("    modalidad_oferta mo, ");
		from.append("    domicilio do  ");
		
		where.append("WHERE ");
		where.append("    oe.estatus = ").append(ESTATUS.ACTIVO.getIdOpcion()); 
		where.append("    AND oe.id_empresa = e.id_empresa");
		where.append("    AND oe.id_oferta_empleo = ou.id_oferta_empleo");
		where.append("    AND ent.id_catalogo_opcion = ou.id_entidad");
		where.append("    AND m.id_entidad = ou.id_entidad");
		where.append("    AND m.id_municipio = ou.id_municipio");
		where.append("    AND oe.id_oferta_empleo = oc.id_oferta_empleo(+)");
		where.append("    AND oe.id_tipo_empleo   = te.id_catalogo_opcion(+)");
		where.append("    AND oe.id_tipo_contrato = tc.id_catalogo_opcion(+)");
		where.append("    AND oe.id_oferta_empleo = oi.id_oferta_empleo(+)");
		where.append("    AND do.id_propietario = oe.id_oferta_empleo(+)");
		where.append("    AND oi.id_idioma = i.id_catalogo_opcion(+)");
		where.append("    AND do.id_tipo_propietario = 3 ");
		where.append("    AND oe.id_oferta_empleo = mo.id_oferta_empleo(+)");
		where.append("    AND oi.principal(+) = ").append(MULTIREGISTRO.PRINCIPAL.getIdOpcion());
		where.append("    AND oc.principal(+) = ").append(MULTIREGISTRO.PRINCIPAL.getIdOpcion());
		
		// Work area
		if (vo.getIdArea() > 0)
			where.append(String.format("    AND oe.id_area  = %d ", vo.getIdArea()));
		if (vo.getIdSubArea() > 0)
			where.append(String.format("    AND oe.id_subarea  = %d ", vo.getIdSubArea()));
		// Jobs
		StringBuilder jobIds = new StringBuilder();
		if (vo.getJobIds().size() > 0) { // There are indeed selected jobs
			for (Integer id : vo.getJobIds()) {
				jobIds.append(id).append(",");
			}
			jobIds.setLength(jobIds.length() - 1); // remove last character (comma)

			if (!jobIds.toString().equals("")) {
				where.append(String.format("    AND oe.id_ocupacion IN (%s) ", jobIds.toString()));
			}
		}

		// Subprogram
		if (vo.getSubprogram() > 0) {
			where.append(String.format("    AND mo.id_subprograma  = %d ", vo.getSubprogram())); 
		}

		// Location Entity
		StringBuilder locationEntitiesIds = new StringBuilder();
		if (vo.getLocationEntityId() > 0) { // LocationEntity was selected
			locationEntitiesIds.append(vo.getLocationEntityId());
			
			
		} else if (vo.getLocationRegionId() > 0) { // LocationRegion was selected
			List<ENTIDADES_FEDERATIVAS> entidadesFederativasList = Constantes.REGION.getRegion(vo.getLocationRegionId()).getEntidades();
			for (Constantes.ENTIDADES_FEDERATIVAS entidadFederativa : entidadesFederativasList) {
				locationEntitiesIds.append(entidadFederativa.getIdEntidad()).append(",");
			}
			locationEntitiesIds.setLength(locationEntitiesIds.length() - 1); // remove last character (comma)
		}
		if (!locationEntitiesIds.toString().equals("")) {
			where.append(String.format("    AND ou.id_entidad IN (%s) ", locationEntitiesIds.toString())); // -- MULTIREGISTRO SOLO EN BUSQUEDA POR REGION
		}
		
		// Location Municipalities
		StringBuilder locationMunicipalityIds = new StringBuilder();
		if (vo.getLocationMunicipalityIds().size() > 0) { // There are indeed selected municipalities
			for (Integer id :  vo.getLocationMunicipalityIds()) {
				locationMunicipalityIds.append(id).append(",");
			}
			locationMunicipalityIds.setLength(locationMunicipalityIds.length() - 1); // remove last character (comma)
			
			if (!locationMunicipalityIds.toString().equals("")) {
				where.append(String.format("    AND ou.id_municipio IN (%s) ", locationMunicipalityIds.toString()));
			}
		}
		
		// Age
		if (vo.getAge() != null && vo.getAge() > 0) {
			where.append(String.format("    AND %d BETWEEN oe.edad_minima AND oe.edad_maxima ", vo.getAge()));
		}
		
		//CodigoPostal 
		if (vo.getCodigoPostal() != null && vo.getCodigoPostal()>0) {
			if (vo.getCodigoPostal().toString().length()<=4){
				
				where.append(String.format("    AND  do.codigo_postal = '0%s'", vo.getCodigoPostal()));
			}
			else{
			where.append(String.format("    AND  do.codigo_postal = '%s'", vo.getCodigoPostal()));
		}
		}
		// Salary
		if ((vo.getMinSalary() != null && vo.getMinSalary() > 0) && (vo.getMaxSalary() != null && vo.getMaxSalary() > 0)) {
			where.append(String.format("    AND oe.salario BETWEEN %f AND %f ", vo.getMinSalary(), vo.getMaxSalary()));
		}
		
		// Employment
		if (vo.getEmploymentTypeId() > 0) {
			where.append(String.format("    AND oe.id_tipo_empleo  = %d ", vo.getEmploymentTypeId())); 
		}
		
		// Contrat Type
		if (vo.getContractTypeId() > 0 ) {
			where.append(String.format("    AND oe.id_tipo_contrato = %d ", vo.getContractTypeId()));
		}
		
		// Education Grade
		if (vo.getEducationGradeId() > 0) {
			where.append(String.format("    AND oe.id_nivel_estudio = %d ", vo.getEducationGradeId()));
		}
		
		// Education Careers
		StringBuilder educationCareerIds = new StringBuilder();
		if (vo.getEducationCareerIds().size() > 0) { // There are indeed selected multiple careers
			for (Integer id : vo.getEducationCareerIds()) {
				educationCareerIds.append(id).append(",");
			}
			educationCareerIds.setLength(educationCareerIds.length() - 1); // remove last character (comma)
			
			if (!educationCareerIds.toString().equals("")) {
				where.append(String.format("    AND oc.id_carrera_especialidad IN (%s) ", educationCareerIds.toString()));
			}
		}
		
		// Enterprise Activity
		StringBuilder enterpriseActivityIds = new StringBuilder();
		if (vo.getEnterpriseActivityIds().size() > 0) { // There are indeed selected multiple enterpriseActivities
			for(Integer id : vo.getEnterpriseActivityIds()) {
				enterpriseActivityIds.append(id).append(",");
			}
			enterpriseActivityIds.setLength(enterpriseActivityIds.length() - 1); // remove last character (comma)
			
			if (!enterpriseActivityIds.toString().equals("")) {
				where.append(String.format("    AND e.id_actividad_economica IN (%s) ", enterpriseActivityIds.toString()));
			}
		}
			
		sqlCommand.append(select).append(from).append(where);
//		logger.info(sqlCommand.toString());
		
		// ===== END SQL COMMAND ===== //
		
			
		List<ResultadoBusquedaOfertasVO> result = null;
		try {
			Query query = entityManager.createNativeQuery(sqlCommand.toString());
			List<Object[]> rowSet = query.getResultList();
			logger.info("Ofertas encontradas: " + rowSet.size());
			result = new ArrayList<ResultadoBusquedaOfertasVO>();
			StringBuilder codigos = new StringBuilder();
			for (Object[] arreglo : rowSet) {
				ResultadoBusquedaOfertasVO resultadoBusquedaOfertasVO = new ResultadoBusquedaOfertasVO();
				resultadoBusquedaOfertasVO.setJobId(Utils.toLong(arreglo[0])); // OfertaEmpleoId
				resultadoBusquedaOfertasVO.setJobOfferName(Utils.toString(arreglo[1])); // TituloOferta
				resultadoBusquedaOfertasVO.setJobOfferDescSkills(Utils.toString(arreglo[2])); // Description: HabilidadGeneral
				resultadoBusquedaOfertasVO.setJobOfferDescYearsExp(Utils.toString(arreglo[3])); // Description: ExperienciaAnios
				resultadoBusquedaOfertasVO.setJobOfferDescLangs(Utils.toString(arreglo[4])); // Description: Idiomas 
				resultadoBusquedaOfertasVO.setJobOfferDescMinAge(Utils.toString(arreglo[5])); // Description: EdadMinima
				resultadoBusquedaOfertasVO.setJobOfferDescMaxAge(Utils.toString(arreglo[6])); // Description: EdadMaxima
				resultadoBusquedaOfertasVO.setLocationEntity(Utils.toString(arreglo[7])); // Ubicacion: EntidadFederativa
				resultadoBusquedaOfertasVO.setLocationMunicipality(Utils.toString(arreglo[8])); // Ubicacion: Municipio
				resultadoBusquedaOfertasVO.setPersonTypeId(Utils.toLong(arreglo[9])); // Enterprise: IdTipoPersona
				resultadoBusquedaOfertasVO.setEnterpriseRegisteredName(Utils.toString(arreglo[10])); // Enterprise: RegisteredName
				resultadoBusquedaOfertasVO.setPersonFirstname(Utils.toString(arreglo[11])); // Enterprise: Person Firstname
				resultadoBusquedaOfertasVO.setPersonLastname(Utils.toString(arreglo[12])); // Enterprise: Person Lastname
				resultadoBusquedaOfertasVO.setPersonSurname(Utils.toString(arreglo[13])); // Enterprise: Person Surname
				resultadoBusquedaOfertasVO.setNetSalary(Utils.toDouble(arreglo[14])); // Salario
				resultadoBusquedaOfertasVO.setPublicationDate(Utils.toDate(arreglo[15])); // Fecha Publicacion
				resultadoBusquedaOfertasVO.setJobOfferDescFunctions(Utils.toString(arreglo[16])); // Funciones
				resultadoBusquedaOfertasVO.setCalle(Utils.toString(arreglo[17]));
				resultadoBusquedaOfertasVO.setNumeroExterior(Utils.toString(arreglo[18]));
				resultadoBusquedaOfertasVO.setIdPropietario(Utils.toLong(arreglo[19]));
				resultadoBusquedaOfertasVO.setCodigoPostal(Utils.toString(arreglo[20]));
				result.add(resultadoBusquedaOfertasVO);
				/**if (vo.getIdArea() > 0 && vo.getIdSubArea() > 0)
					resultadoBusquedaOfertasVO.setJobOfferDescFunctions(getOfertaFuncionList(Utils.toLong(arreglo[0])));**/
			}
		}catch(NoResultException re){
			logger.error(re);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e);
		}
		return result;
	}

	@Override
	public long findCandidatoNotificacion(Long idCantidato) {
		Long lista =0L;
		Long candidato = idCantidato;
		String buscaSectorOferta = "SELECT id_Candidato FROM NOTIFICACION_CANDIDATO  WHERE id_Candidato= ? ";
		try {
			//System.out.print("entro al select");
			Query query = entityManager.createNativeQuery(buscaSectorOferta);
			query.setParameter(1, candidato);
			//query.setParameter("candidatoNotificacion", candidato);
			@SuppressWarnings("unchecked")
			List<Object> result1 = query.getResultList();
			if (result1.size() > 0 ){
				//System.out.print("entro al if");
				lista=1L;
			}
			
		} catch (NoResultException re) {
			// No se localizaron registros
			re.printStackTrace();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}
		return lista;
	}
	
	private String getOfertaFuncionList(long idOfertaEmpleo) {
		StringBuilder list = new StringBuilder();
		if (idOfertaEmpleo <=0) return "";
		String sql = "SELECT o FROM OfertaFuncion o WHERE o.idOfertaEmpleo=:idOfertaEmpleo";
		Query query = entityManager.createQuery(sql);
		query.setParameter("idOfertaEmpleo", idOfertaEmpleo);
		try {
			@SuppressWarnings("unchecked")
			List<Object> result = query.getResultList();
			for (Object resultElement : result) {
				OfertaFuncion entity = (OfertaFuncion)resultElement;
				String sb = "SELECT f.ID_FUNCION, f.FUNCION FROM FUNCIONES f WHERE f.ID_FUNCION = " + entity.getIdFuncion();
				Query queryList = entityManager.createNativeQuery(sb);
				@SuppressWarnings("unchecked")
				List<Object[]> rowSet = queryList.getResultList();
				for (Object[] rs : rowSet) {
					list.append(" ").append(Utils.toString(rs[1]));
				}
			}
		}catch (NoResultException re) {
			list = new StringBuilder();
		}
		return list.toString().trim();
	}
}