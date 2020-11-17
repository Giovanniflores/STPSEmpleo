package mx.gob.stps.portal.core.persistencia.facade;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_NINGUNA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.DIAS_REPORTE_INFONAVIT;
import static mx.gob.stps.portal.core.infra.utils.Utils.obtenEdad;

import java.sql.SQLException;
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

import mx.gob.stps.portal.core.candidate.vo.BusquedaCandidatosVO;
import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.candidate.vo.ComputacionAvanzadaVO;
import mx.gob.stps.portal.core.candidate.vo.ConocimientoHabilidadVO;
import mx.gob.stps.portal.core.candidate.vo.EscolaridadVO;
import mx.gob.stps.portal.core.candidate.vo.ExpectativaLaboralVO;
import mx.gob.stps.portal.core.candidate.vo.ExpectativaLugarVO;
import mx.gob.stps.portal.core.candidate.vo.ExpectativaVO;
import mx.gob.stps.portal.core.candidate.vo.ExperienciaVO;
import mx.gob.stps.portal.core.candidate.vo.GradoAcademicoVO;
import mx.gob.stps.portal.core.candidate.vo.HistoriaLaboralVO;
import mx.gob.stps.portal.core.candidate.vo.IdiomaVO;
import mx.gob.stps.portal.core.candidate.vo.PerfilVO;
import mx.gob.stps.portal.core.candidate.vo.ResultadoBusquedaCandidatosVO;
import mx.gob.stps.portal.core.candidate.vo.TelefonoVo;
import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.CAMBIO_CORREO;
import mx.gob.stps.portal.core.infra.utils.Constantes.CONOC_HAB;
import mx.gob.stps.portal.core.infra.utils.Constantes.DISPONIBILIDAD_RADICAR;
import mx.gob.stps.portal.core.infra.utils.Constantes.DISPONIBILIDAD_VIAJAR;
import mx.gob.stps.portal.core.infra.utils.Constantes.DOMINIO;
import mx.gob.stps.portal.core.infra.utils.Constantes.ENTIDADES_FEDERATIVAS;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESCOLARIDAD;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.EXPERIENCIA;
import mx.gob.stps.portal.core.infra.utils.Constantes.EXPERIENCIA_LABORAL;
import mx.gob.stps.portal.core.infra.utils.Constantes.IDIOMAS;
import mx.gob.stps.portal.core.infra.utils.Constantes.MULTIREGISTRO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_EMPLEO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.infra.vo.CandidatoVerDetalleVO;
import mx.gob.stps.portal.core.search.Candidate;
import mx.gob.stps.portal.persistencia.entity.Candidato;
import mx.gob.stps.portal.persistencia.entity.CandidatoConocimHabilidad;
import mx.gob.stps.portal.persistencia.entity.CandidatoGradoAcademico;
import mx.gob.stps.portal.persistencia.entity.CandidatoHabilidad;
import mx.gob.stps.portal.persistencia.entity.CandidatoHabilidadPK;
import mx.gob.stps.portal.persistencia.entity.CandidatoIdioma;
import mx.gob.stps.portal.persistencia.entity.CandidatoVerDetalle;
import mx.gob.stps.portal.persistencia.entity.Domicilio;
import mx.gob.stps.portal.persistencia.entity.ExpectativaLaboral;
import mx.gob.stps.portal.persistencia.entity.ExpectativaLugar;
import mx.gob.stps.portal.persistencia.entity.HistoriaLaboral;
import mx.gob.stps.portal.persistencia.entity.OtroMedio;
import mx.gob.stps.portal.persistencia.entity.PerfilLaboral;
import mx.gob.stps.portal.persistencia.entity.RegistroPublicidad;
import mx.gob.stps.portal.persistencia.entity.Telefono;
import mx.gob.stps.portal.persistencia.vo.CatSubareaVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.utils.Catalogos;
import oracle.spatial.geometry.JGeometry;

import org.apache.log4j.Logger;


@Stateless
public class CandidatoFacade implements CandidatoFacadeLocal {

	private static Logger logger = Logger.getLogger(CandidatoFacade.class);

	@PersistenceContext
	private EntityManager entityManager;

	@EJB
	private DomicilioFacadeLocal domicilioFacade;

	@EJB
	private CatalogoOpcionFacadeLocal catalogoOpcionFacadeLocal;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.gob.stps.portal.core.persistencia.facade.CandidatoFacadeLocal#
	 * registrarPerfil(mx.gob.stps.portal.core.candidate.vo.PerfilVO)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PerfilVO registrarPerfil(PerfilVO perfil) throws PersistenceException {
		//Si cambio el correoElectronico se actualiza el correo del usuario.
		Candidato candidato = entityManager.find(Candidato.class, perfil.getIdCandidato());
		
		/**if (perfil.getCambioCorreo() == CAMBIO_CORREO.SI.getIdOpcion()) {
			Usuario usuario = entityManager.find(Usuario.class, candidato.getIdUsuario());
			if (usuario != null) {
				usuario.setCorreoElectronico(perfil.getCorreoElectronico());
			}
		}*/
		
		Date fechaAlta = new Date();
		if (candidato != null) {//Si el candidato existe
			candidato.setIdEstadoCivil(Utils.toInt(perfil.getIdEstadoCivil()));
			candidato.setConfidencialidadDatos(perfil.getConfidencialidad());
			candidato.setFechaUltimaActualizacion(fechaAlta);
			//candidato.setIdMedioPortal(perfil.getIdMedioPortal());
			//Aplica reglas de negocio de estatus
			setEstatusCandidato(candidato, perfil);
			//Si se actualizo correoElectronico cambia el estatus del candidato
			if (perfil.getCambioCorreo() == CAMBIO_CORREO.SI.getIdOpcion()) {
				candidato.setCorreoElectronico(perfil.getCorreoElectronico());
				//candidato.setEstatus(ESTATUS.INACTIVO.getIdOpcion());
			}
			entityManager.persist(candidato);
			PerfilLaboral pl = entityManager.find(PerfilLaboral.class,
					perfil.getIdCandidato());
			if (pl == null) {//Crea el perfil laboral
				pl = new PerfilLaboral();
				setPerfil(pl, perfil);
				pl.setFechaAlta(fechaAlta);
				entityManager.persist(pl);
			} else {//Actualiza el perfil laboral
				setPerfil(pl, perfil);
			}

			//Guarda el domicilio
			Domicilio domicilio = entityManager.find(Domicilio.class, perfil.getIdDomicilio());

			if (domicilio==null){
				domicilio = domicilioFacade.getDomicilio(perfil.getIdCandidato(), TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
			}

			if (domicilio == null) {//Crea el domicilio
				domicilio = new Domicilio();
				domicilio.setIdPropietario(perfil.getIdCandidato());
				setDomicilio(domicilio, perfil);
				domicilio.setFechaAlta(fechaAlta);
				entityManager.persist(domicilio);
				
				//Obtiene id generado
				perfil.setIdDomicilio(domicilio.getIdDomicilio());
			} else {//Actualiza el domicilio
				setDomicilio(domicilio, perfil);
			}
			
			/* Agrega o actualiza el Telefono "Principal" */
			Telefono tel = entityManager.find(Telefono.class, perfil
					.getPrincipal().getIdTelefono());
			if (tel == null) {//Crea telefono principal
				tel = new Telefono();
				tel.setIdPropietario(perfil.getIdCandidato());
				setTelefono(tel, perfil.getPrincipal());
				tel.setFechaAlta(fechaAlta);
				entityManager.persist(tel);
				//Obtiene id generado
				perfil.getPrincipal().setIdTelefono(tel.getIdTelefono());
			} else {//Actualiza telefono principal
				setTelefono(tel, perfil.getPrincipal());
			}
			//guardar los telefones adicionales
			
			
			
			//Borra los otrosMedios anteriores
			Query query = entityManager.createQuery("SELECT om.idOtroMedio FROM OtroMedio om WHERE om.idCandidato=:idCandidato");
			query.setParameter("idCandidato", perfil.getIdCandidato());
			List<Long> otrosMedios = query.getResultList();
			for (long om : otrosMedios) {
				OtroMedio otroMedio = entityManager.find(OtroMedio.class, om);
				if (otroMedio != null) {
					entityManager.remove(otroMedio);
				}
			}
			//Genera los nuevos otrosMedios
			OtroMedio otroMedio = null;
			for (long otroMedioVO : perfil.getIdOtrosMedios()) {
				otroMedio = new OtroMedio();
				otroMedio.setIdCandidato(perfil.getIdCandidato());
				otroMedio.setIdMedioBusqueda(otroMedioVO);
				otroMedio.setFechaAlta(fechaAlta);
				entityManager.persist(otroMedio);
			}
		}
		
		return perfil;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.gob.stps.portal.core.persistencia.facade.CandidatoFacadeLocal#
	 * registrarEscolaridad(mx.gob.stps.portal.core.candidate.vo.EscolaridadVO)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EscolaridadVO registrarEscolaridad(EscolaridadVO escolaridad) throws PersistenceException {
		/* PERFIL_LABORAL */		
		actualizaFechaUltimaActualizacion(escolaridad.getIdCandidato());
		if(escolaridad.getApoyoProspera() > 0){
			actualizaApoyoProspera(escolaridad.getIdCandidato(), escolaridad.getApoyoProspera(), escolaridad.getFolioProspera(), escolaridad.getFolioIntegranteProspera());
		}
		PerfilLaboral perfil = entityManager.find(PerfilLaboral.class,	escolaridad.getIdCandidato());
		if (perfil != null) {// En este paso del proceso el PerfilLaboral debe existir
			setPerfilLaboral(perfil, escolaridad);
			
		}
		
		Date fechaAlta = new Date();
		
		//No confirma "No tener estudios"
		if (escolaridad.getSinEstudios() == ESCOLARIDAD.CON_ESTUDIOS.getIdOpcion()) {
			/* GRADO ACADEMICO PRINCIPAL*/
			GradoAcademicoVO gradoVO = escolaridad.getGrado();
			CandidatoGradoAcademico grado = null;
			if (gradoVO.getIdCandidatoGradoAcademico() != 0) { // Grado academico tiene consecutivo
				grado = entityManager.find(CandidatoGradoAcademico.class, gradoVO.getIdCandidatoGradoAcademico());
				if (grado != null) { // Si existe el grado academico
					this.setGradoAcademico(grado, gradoVO);
				}
			} else { // Sin consecutivo, se crea grado academico
				grado = new CandidatoGradoAcademico();
				grado.setIdCandidato(escolaridad.getIdCandidato());
				this.setGradoAcademico(grado, gradoVO);
				grado.setFechaAlta(fechaAlta);
				entityManager.persist(grado);
				//Obtiene id generado
				gradoVO.setIdCandidatoGradoAcademico(grado.getIdCandidatoGradoAcademico());
				escolaridad.setGrado(gradoVO);
			}
			/* IDIOMA PRINCIPAL */
			IdiomaVO idiomaVO = escolaridad.getIdioma();
			CandidatoIdioma idioma = null;			
			if (idiomaVO.getIdCandidatoIdioma() != 0) {// El idioma existe		
				idioma = entityManager.find(CandidatoIdioma.class, idiomaVO.getIdCandidatoIdioma());				
				if (idioma != null && idioma.getIdIdioma() > 0)
					this.setCandidatoIdioma(idioma, idiomaVO);		
			} else {// El idioma no existe
				if (null != idiomaVO && idiomaVO.getIdIdioma() > 0) {		
					idioma = new CandidatoIdioma();
					idioma.setIdCandidato(escolaridad.getIdCandidato());
					idioma.setFechaAlta(fechaAlta);
					this.setCandidatoIdioma(idioma, idiomaVO);
					entityManager.persist(idioma);
					//Obtiene id generado
					idiomaVO.setIdCandidatoIdioma(idioma.getIdCandidatoIdioma());
					escolaridad.setIdioma(idiomaVO);
				}
			}
			/** CONOCIMIENTO PRINCIPAL **/
			ConocimientoHabilidadVO conocimientoVO = escolaridad.getConocimiento();
			CandidatoConocimHabilidad conocimiento = null;
			// Si ya tiene un Id asignado se actualiza.
			if (null != conocimientoVO && conocimientoVO.getIdCandidatoConocimHabilidad() != 0) {
				conocimiento = entityManager.find(CandidatoConocimHabilidad.class, conocimientoVO.getIdCandidatoConocimHabilidad());
				if (null != conocimiento && conocimiento.getConocimientoHabilidad()!=null && !conocimientoVO.getConocimientoHabilidad().isEmpty()) {
					this.setConocimientoHabilidad(conocimiento, conocimientoVO);
				}
			} else {// Si no tiene un Id se crea.
				if (null != conocimientoVO && conocimientoVO.getConocimientoHabilidad()!=null && !conocimientoVO.getConocimientoHabilidad().isEmpty()) {
					conocimiento = new CandidatoConocimHabilidad();
					conocimiento.setIdCandidato(escolaridad.getIdCandidato());
					conocimiento.setFechaAlta(fechaAlta);
					this.setConocimientoHabilidad(conocimiento,conocimientoVO);
					entityManager.persist(conocimiento);
					//Obtiene id generado
					conocimientoVO.setIdCandidatoConocimHabilidad(conocimiento.getIdCandidatoConocimHabilidad());
					escolaridad.setConocimiento(conocimientoVO);
				}
			}
			/** HABILIDAD PRINCIPAL **/
			/*ConocimientoHabilidadVO habilidadVO = escolaridad.getHabilidad();
			CandidatoConocimHabilidad habilidad = null;
			// Si ya tiene un Id asignado se actualiza.
			if (habilidadVO.getIdCandidatoConocimHabilidad() != 0) {
				habilidad = entityManager.find(CandidatoConocimHabilidad.class, habilidadVO.getIdCandidatoConocimHabilidad());
				
				if (habilidad != null && habilidadVO.getConocimientoHabilidad()!=null && !habilidadVO.getConocimientoHabilidad().isEmpty()) {
					this.setConocimientoHabilidad(habilidad, habilidadVO);
				}
			} else {// Si no tiene un Id se crea.
				if (null != habilidadVO && habilidadVO.getConocimientoHabilidad()!=null && !habilidadVO.getConocimientoHabilidad().isEmpty()) {
					habilidad = new CandidatoConocimHabilidad();
					habilidad.setIdCandidato(escolaridad.getIdCandidato());
					habilidad.setFechaAlta(fechaAlta);
					this.setConocimientoHabilidad(habilidad, habilidadVO);
					entityManager.persist(habilidad);
					//Obtiene id generado
					habilidadVO.setIdCandidatoConocimHabilidad(habilidad.getIdCandidatoConocimHabilidad());
					escolaridad.setHabilidad(habilidadVO);
				}
			}*/
			
			/* COMPUTACION_AVANZADA PRINCIPAL
			// Si el candidato tiene computacion avanzada
			if (escolaridad.getComputacionAvanzada() == COMPU_AVANZADA.SI.getIdOpcion()) {
				ComputacionAvanzadaVO compuAvanzadaVO = escolaridad.getCompAvanzada();
				CandidatoCompuAvanzada compuAvanzada = null;
				// Si ya tiene Id asignado solo se actualiza.
				if (compuAvanzadaVO.getIdCandidatoCompuAvanzada() != 0) {
					compuAvanzada = entityManager.find(
							CandidatoCompuAvanzada.class,
							compuAvanzadaVO.getIdCandidatoCompuAvanzada());
					if (compuAvanzada != null) {
						this.setCandidatoCompuAvanzada(compuAvanzada, 
								compuAvanzadaVO);
					}
				} else {// Si no tiene Id asignado solo se crea.
					compuAvanzada = new CandidatoCompuAvanzada();
					compuAvanzada.setIdCandidato(escolaridad.getIdCandidato());
					compuAvanzada.setFechaAlta(fechaAlta);
					this.setCandidatoCompuAvanzada(compuAvanzada,
							compuAvanzadaVO);
					entityManager.persist(compuAvanzada);
					//Obtiene id generado
					compuAvanzadaVO.setIdCandidatoCompuAvanzada(compuAvanzada.
							getIdCandidatoCompuAvanzada());
					escolaridad.setCompAvanzada(compuAvanzadaVO);
				}
			} else {//Si el candidato afirma no tener computacion avanzada
				this.borrarCompuAvanzadas(escolaridad.getIdCandidato());
			}*/
		} else { //Afirma "No tener estudios"
			/*GRADOS ACADEMICOS - Eliminar*/
			//Busca los grados academicos existentes
			Query query = entityManager.createQuery("SELECT cga.idCandidatoGradoAcademico " 
					+ "FROM CandidatoGradoAcademico cga WHERE cga.idCandidato=:idCandidato");
			query.setParameter("idCandidato", escolaridad.getIdCandidato());
			List<Long> ids = query.getResultList();
			//Borra los grados academicos
			for (long grd : ids) {
				CandidatoGradoAcademico grado = entityManager.find(CandidatoGradoAcademico.class, grd);
				if (grado != null) {
					entityManager.remove(grado);
				}
			}
			/*IDIOMAS - Eliminar*/
			//Busca los idiomas existentes
			query = entityManager.createQuery("SELECT ci.idCandidatoIdioma FROM CandidatoIdioma ci WHERE ci.idCandidato=:idCandidato");
			query.setParameter("idCandidato", escolaridad.getIdCandidato());
			ids = query.getResultList();
			
			//Borra los idiomas
			for (long idm : ids) {
				CandidatoIdioma idioma = entityManager.find(CandidatoIdioma.class, idm);
				if (idioma != null) {
					entityManager.remove(idioma);
				}
			}
			
			/* CONOCIMIENTOS Y HABILIDADES - Eliminar*/ 
			//Busca los conocimientos y habilidades existentes
			query = entityManager.createQuery("SELECT cch.idCandidatoConocimHabilidad FROM CandidatoConocimHabilidad cch WHERE cch.idCandidato=:idCandidato");
			query.setParameter("idCandidato", escolaridad.getIdCandidato());
			ids = query.getResultList();

			//Borra los conocimientos y habilidades
			for (long ch : ids) {
				CandidatoConocimHabilidad cncHab = entityManager.find(
						CandidatoConocimHabilidad.class, ch);
				if (cncHab != null) {
					entityManager.remove(cncHab);
				}
			}
			
			/* COMPUTACION AVANZADA - Eliminar*/
			this.borrarCompuAvanzadas(escolaridad.getIdCandidato());
		}

		long[] idHabilidades = escolaridad.getIdHabilidad();
		
		guardarCandidatoHabilidades(escolaridad.getIdCandidato(), idHabilidades);
		
		entityManager.flush();
		logger.info("entityManager: " + escolaridad);
		return escolaridad;
	}
	
	public int guardarCandidatoHabilidades(long idCandidato,
			long[] idHabilidades) {
		if (idHabilidades!=null && idCandidato > 0){

			Query query = entityManager.createQuery("DELETE FROM CandidatoHabilidad ch WHERE ch.id.idCandidato = :idCandidato");
			query.setParameter("idCandidato", idCandidato);
			query.executeUpdate();

			for (long idHabilidad : idHabilidades){
				try{
					CandidatoHabilidadPK candidatoHabilidadPK = new CandidatoHabilidadPK();
					candidatoHabilidadPK.setIdCandidato(idCandidato);
					candidatoHabilidadPK.setIdHabilidad(idHabilidad);

					CandidatoHabilidad candidatoHabilidad = new CandidatoHabilidad();
					candidatoHabilidad.setId(candidatoHabilidadPK);

					entityManager.persist(candidatoHabilidad);
				}catch(Exception e){
					logger.error(e);
				}
			}
			return 1;
		}
		else {
			return 0;
		}
	}
	
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<Long> consultaHabilidades(long idCandidato){
		List<Long> idHabilidades = new ArrayList<Long>();
		
		try{
			Query query = entityManager.createQuery("SELECT ch FROM CandidatoHabilidad ch WHERE ch.id.idCandidato = :idCandidato");
			query.setParameter("idCandidato", idCandidato);

			List<CandidatoHabilidad> habilidades = (List<CandidatoHabilidad>)query.getResultList();
			
			if (habilidades!=null){
				for (CandidatoHabilidad habilidad : habilidades){
					long idHabilidad = habilidad.getId().getIdHabilidad();
					idHabilidades.add(idHabilidad);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e);
		}
		
		return idHabilidades;
	}
	
	/*
	 * (non-Javadoc)
	 * @see
	 * mx.gob.stps.portal.core.persistencia.facade.CandidatoFacadeLocal#agregarGrado
	 * (mx.gob.stps.portal.core.candidate.vo.GradoAcademicoVO)
	 */
	@Override
	public void agregarGrado(long idCandidato, GradoAcademicoVO gradoVO) throws PersistenceException {
		CandidatoGradoAcademico grado;

		if (gradoVO.getIdCandidatoGradoAcademico() != 0) { // Grado academico tiene consecutivo
			grado = entityManager.find(CandidatoGradoAcademico.class,
					gradoVO.getIdCandidatoGradoAcademico());
			if (grado != null) { // Si existe el grado academico
				setGradoAcademico(grado, gradoVO);
			}
		} else { // Sin consecutivo, se crea grado academico
			grado = new CandidatoGradoAcademico();
			Date fechaAlta = new Date();
			grado.setIdCandidato(idCandidato);
			setGradoAcademico(grado, gradoVO);
			grado.setFechaAlta(fechaAlta);
			entityManager.persist(grado);
			gradoVO.setIdCandidatoGradoAcademico(grado
					.getIdCandidatoGradoAcademico());
			//logger.info("idGenerado: " + gradoVO.getIdCandidatoGradoAcademico());
		}
	}
	/*
	 * (non-Javadoc)
	 * @see
	 * mx.gob.stps.portal.core.persistencia.facade.CandidatoFacadeLocal#borrarGrados
	 * (long)
	 */
	
	@Override
	public void borrarGrado(long idCandidatoGradoAcademico) throws PersistenceException {
		CandidatoGradoAcademico grado = entityManager.find(CandidatoGradoAcademico.class, idCandidatoGradoAcademico);
		if (grado != null)
			entityManager.remove(grado);
	}
	
	public void borrarGradosAcademicos(long idCandidato) {
		String jpql = "delete from CandidatoGradoAcademico g where g.idCandidato = :idCandidato";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("idCandidato", idCandidato);
		
		query.executeUpdate();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.gob.stps.portal.core.persistencia.facade.CandidatoFacadeLocal#
	 * agregarIdioma(long, IdiomaVO)
	 */
	@Override
	public void agregarIdioma(long idCandidato, IdiomaVO idiomaVO) throws PersistenceException {
		
		if (idCandidato <= 0) throw new IllegalArgumentException("Identificador del candidato requerido");
		if (idiomaVO==null) throw new IllegalArgumentException("Los datos del idioma son requeridos");
		
		if (idiomaVO.getIdIdioma() == IDIOMAS.NINGUNO.getIdOpcion()){
			idiomaVO.setIdCertificacion(CATALOGO_OPCION_NINGUNA);
			idiomaVO.setIdDominio(DOMINIO.NINGUNO.getIdOpcion());
		}

		try{
			if (idiomaVO.getIdCandidatoIdioma() != 0) {
				CandidatoIdioma idioma = entityManager.find(CandidatoIdioma.class, idiomaVO.getIdCandidatoIdioma());
				
				if (idioma != null) {
					setCandidatoIdioma(idioma, idiomaVO);
				}
			} else {
				CandidatoIdioma idioma = new CandidatoIdioma();
				
				Date fechaAlta = new Date();
				idioma.setIdCandidato(idCandidato);
				setCandidatoIdioma(idioma, idiomaVO);
				idioma.setFechaAlta(fechaAlta);
				
				entityManager.persist(idioma);
				idiomaVO.setIdCandidatoIdioma(idioma.getIdCandidatoIdioma());
			}
		} catch (NoResultException re) {
			// No se localiza el idima para su edicion
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.stps.portal.core.persistencia.facade.CandidatoFacadeLocal#borrarIdioma
	 * (long)
	 */
	@Override
	public void borrarIdioma(long idCandidatoIdioma) throws PersistenceException {
		CandidatoIdioma idioma = entityManager.find(CandidatoIdioma.class, idCandidatoIdioma);
		if (idioma != null)
			entityManager.remove(idioma);
	}
	
	public void borrarIdiomas(long idCandidato) {

		String jpql = "delete from CandidatoIdioma c where c.idCandidato = :idCandidato";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("idCandidato", idCandidato);

		query.executeUpdate();
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.gob.stps.portal.core.persistencia.facade.CandidatoFacadeLocal#
	 * agregarConocHab(long)
	 */
	@Override
	public void agregarConocHab(long idCandidato, ConocimientoHabilidadVO conocHabVO) throws PersistenceException {

		CandidatoConocimHabilidad conocHab = null;
		try{
			System.out.println("-----CandidatoFacade.agregarConocHab idCandidato:" + idCandidato);
			if (idCandidato <= 0) {
				throw new IllegalArgumentException("Identificador del candidato requerido");
			} else {
				if (conocHabVO!=null && conocHabVO.getIdExperiencia() == EXPERIENCIA.NINGUNA.getIdOpcion()){
					conocHabVO.setIdDominio(DOMINIO.NINGUNO.getIdOpcion());
				}
	
				if (conocHabVO.getIdCandidatoConocimHabilidad() != 0) { // Conocimiento o Habilidad tiene consecutivo
					conocHab = entityManager.find(CandidatoConocimHabilidad.class, conocHabVO.getIdCandidatoConocimHabilidad());
					if (conocHab != null) { // Si existe el grado academico
						setConocimientoHabilidad(conocHab, conocHabVO);
					}
				} else { // Sin consecutivo, se crea grado academico
					conocHab = new CandidatoConocimHabilidad();
					Date fechaAlta = new Date();
					conocHab.setIdCandidato(idCandidato);
					setConocimientoHabilidad(conocHab, conocHabVO);
					conocHab.setFechaAlta(fechaAlta);
					entityManager.persist(conocHab);
					conocHabVO.setIdCandidatoConocimHabilidad(conocHab.getIdCandidatoConocimHabilidad());
					//logger.info("idGenerado: "+ conocHabVO.getIdCandidatoConocimHabilidad());
				}			
			}		
		} catch (Exception re) {
			logger.error(re);
			throw new PersistenceException(re);
		}		

	}
	
	@Override
	public int actualizarConocHab(ConocimientoHabilidadVO conocHabVO) throws PersistenceException {
		int result = -1;
		CandidatoConocimHabilidad conocHab;
		if (conocHabVO.getIdCandidatoConocimHabilidad() != 0) {
			conocHab = entityManager.find(CandidatoConocimHabilidad.class, conocHabVO.getIdCandidatoConocimHabilidad());
			if (conocHab != null) {
				conocHab.setConocimientoHabilidad(conocHabVO.getConocimientoHabilidad());
				conocHab.setDescripcion(conocHabVO.getDescripcion());
				conocHab.setIdExperiencia(conocHabVO.getIdExperiencia());
				entityManager.merge(conocHab);
				result = 1;
			}
		}
		return result;
	}	
	
	/*
	 * (non-Javadoc) 
	 * @see mx.gob.stps.portal.core.persistencia.facade.CandidatoFacadeLocal#
	 * borrarConocHab(long)
	 */
	@Override
	public void borrarConocHab(long idCandidatoConocimHabilidad)throws PersistenceException {
		CandidatoConocimHabilidad conocHab = entityManager.find(CandidatoConocimHabilidad.class, idCandidatoConocimHabilidad);
		if (conocHab != null){
			System.out.println("-----CandidatoFacade.borrarConocHab conocHab:" + conocHab.getIdCandidatoConocimHabilidad());
			entityManager.remove(conocHab);	
		}				
	}
	
	public void borrarCandidatoConocimHabilidad(long idCandidato) {
		String jpql = "delete from CandidatoConocimHabilidad e where e.idCandidato = :idCandidato";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("idCandidato", idCandidato);

		query.executeUpdate();
	}
	
	/*
	 * (non-Javadoc) 
	 * @see mx.gob.stps.portal.core.persistencia.facade.CandidatoFacadeLocal#
	 * agregarCompuAvanzada(long, ComputacionAvanzadaVO)
	 */
	@Override
	public void agregarCompuAvanzada(long idCandidato, ComputacionAvanzadaVO compAvanVO) throws PersistenceException {
		//CandidatoCompuAvanzada compAvan = null;
		//System.out.println("-----CandidatoFacade.agregarCompuAvanzada idCandidato:" + idCandidato);
		if (idCandidato <= 0) {
			throw new IllegalArgumentException("Identificador del candidato requerido");
		} else {
			if (compAvanVO!=null && compAvanVO.getIdExperiencia()== EXPERIENCIA.NINGUNA.getIdOpcion()){
				compAvanVO.setIdDominio(DOMINIO.NINGUNO.getIdOpcion());
			}
			
//			if (compAvanVO.getIdCandidatoCompuAvanzada() != 0) { // Conocimiento o Habilidad tiene consecutivo
//				compAvan = entityManager.find(CandidatoCompuAvanzada.class, compAvanVO.getIdCandidatoCompuAvanzada());
//				if (compAvan != null) { // Si existe el grado academico
//					setCandidatoCompuAvanzada(compAvan, compAvanVO);
//				}
//			} else { // Sin consecutivo, se crea grado academico
//				compAvan = new CandidatoCompuAvanzada();
//				Date fechaAlta = new Date();
//				compAvan.setIdCandidato(idCandidato);
//				setCandidatoCompuAvanzada(compAvan, compAvanVO);
//				compAvan.setFechaAlta(fechaAlta);
//				entityManager.persist(compAvan);
//				compAvanVO.setIdCandidatoCompuAvanzada(compAvan.getIdCandidatoCompuAvanzada());
//				//logger.info("idGenerado: "+ compAvanVO.getIdCandidatoCompuAvanzada());
//			}
		}

	}
	/*
	 * (non-Javadoc) 
	 * @see mx.gob.stps.portal.core.persistencia.facade.CandidatoFacadeLocal#
	 * borrarCompuAvanzada(long)
	 */
	@Override
	public void borrarCompuAvanzada(long idCandidatoCompuAvanzada)throws PersistenceException {
//		CandidatoCompuAvanzada compuAvan = entityManager.find(CandidatoCompuAvanzada.class, idCandidatoCompuAvanzada);
//		if (compuAvan != null)
//			entityManager.remove(compuAvan);
		
	}
	@SuppressWarnings({ "unchecked", "unused" })
	private void borrarCompuAvanzadas(long idCandidato) throws PersistenceException {
		//Busca computacion avanzada existente
		Query query = entityManager.createQuery("SELECT cca.idCandidatoCompuAvanzada " 
				+ "FROM CandidatoCompuAvanzada cca WHERE cca.idCandidato=:idCandidato");
		query.setParameter("idCandidato", idCandidato);
		List<Long> ids = query.getResultList();
		//Borra los conocimientos y habilidades
//		for (long ca : ids) {
//			CandidatoCompuAvanzada compuAvanzada = entityManager.find(
//					CandidatoCompuAvanzada.class, ca);
//			if (compuAvanzada != null) {
//				entityManager.remove(compuAvanzada);
//			}
//		}
	}
	@Override
	public ExperienciaVO registrarExperiencia(ExperienciaVO experiencia)
			throws PersistenceException {
		/* PERFIL_LABORAL */
		PerfilLaboral perfil = entityManager.find(PerfilLaboral.class,	experiencia.getIdCandidato());
		
		if(experiencia.getIdCandidato()>0){
			actualizaFechaUltimaActualizacion(experiencia.getIdCandidato());
		}
		
		if (perfil != null) {// En este paso del proceso el PerfilLaboral debe existir
			setPerfilLaboral(perfil, experiencia);
			entityManager.merge(perfil);
		}
		
		/* HISTORIA_LABORAL */
		Date fechaAlta = new Date();
		HistoriaLaboralVO histLaboralVO = experiencia.getHistLaboral();
		
		/** Solo se registra el historial su se cuenta con el nombre de la empresa o el puesto **/
		if (histLaboralVO!=null && (histLaboralVO.getEmpresa()!=null || histLaboralVO.getPuesto()!=null)){
			HistoriaLaboral histLaboral = null;
			//
			//TODO: revisar si es trabajo actual o no regla a especificar
			//regla aplicado si tenemos fecha inicio y no tenemos fecha fin es trabajo actual
			if(histLaboralVO.getFechaIni() != null && histLaboralVO.getFechaFin() == null){
				histLaboralVO.setTrabajoActual(Constantes.TRABAJO_ACTUAL.SI.getIdOpcion());
			}else
			{
				histLaboralVO.setTrabajoActual(Constantes.TRABAJO_ACTUAL.NO.getIdOpcion());
			}
			// Si ya tiene Id asignado solo se actualiza
			if (histLaboralVO.getIdHistorialLaboral() != 0) {
				histLaboral = entityManager.find(HistoriaLaboral.class,	histLaboralVO.getIdHistorialLaboral());
				setHistoriaLaboral(histLaboral, histLaboralVO);
			} else {
				histLaboral = new HistoriaLaboral();
				histLaboral.setIdCandidato(experiencia.getIdCandidato());
				histLaboral.setFechaAlta(fechaAlta);
				setHistoriaLaboral(histLaboral, histLaboralVO);
				entityManager.persist(histLaboral);
				//Obtiene id generado
				histLaboralVO.setIdHistorialLaboral(histLaboral.getIdHistorialLaboral());
				experiencia.setHistLaboral(histLaboralVO);
			}
			
			/*if(!experiencia.isNuncaHeTrabajado()){
				CandidatoDAO dao =  new CandidatoDAO();			
				dao.limpiarHistorialLaboral(experiencia.getIdCandidato());			
				//logger.info("Numero de Registro eliminador :-> " + numeroRegistros);			
			}*/
		}
		List<HistoriaLaboralVO> histLaboralesVO = experiencia.getHistLaborales();
		
		if(histLaboralesVO != null && histLaboralesVO.size()>0){
			borrarHistoriaLaboral(experiencia.getIdCandidato());
			HistoriaLaboralVO historiaLaboral2VO = histLaboralesVO.get(0);
			historiaLaboral2VO.setIdHistorialLaboral(0L);
			agregarHistLaboral(experiencia.getIdCandidato(),historiaLaboral2VO);
			if(histLaboralesVO.size()>1){
				HistoriaLaboralVO historiaLaboral2VOb = histLaboralesVO.get(1);
				
				agregarHistLaboral(experiencia.getIdCandidato(),historiaLaboral2VOb);
				
				
			}
			
		}
				
		return experiencia;
	}	
	
	@Override
	public void agregarHistLaboral(long idCandidato,
			HistoriaLaboralVO histLaboralVO) throws PersistenceException {
		HistoriaLaboral histLaboral;
		if (histLaboralVO.getIdHistorialLaboral() != 0) { // Conocimiento o Habilidad tiene consecutivo
			histLaboral = entityManager.find(HistoriaLaboral.class,
					histLaboralVO.getIdHistorialLaboral());
			if (histLaboral != null) { // Si existe el grado academico
				setHistoriaLaboral(histLaboral, histLaboralVO);
			}
		} else { // Sin consecutivo, se crea grado academico
			histLaboral = new HistoriaLaboral();
			Date fechaAlta = new Date();
			histLaboral.setIdCandidato(idCandidato);
			setHistoriaLaboral(histLaboral, histLaboralVO);
			histLaboral.setFechaAlta(fechaAlta);
			entityManager.persist(histLaboral);
			histLaboralVO.setIdHistorialLaboral(histLaboral
					.getIdHistorialLaboral());
			//logger.info("idGenerado: "+ histLaboralVO.getIdHistorialLaboral());
		}
	}

	@Override
	public void borrarHistLaboral(long idHistorialLaboral) throws PersistenceException {
		HistoriaLaboral histLaboral = entityManager.find(HistoriaLaboral.class, idHistorialLaboral);
		if (histLaboral != null)
			entityManager.remove(histLaboral);
	}

	public void borrarHistoriaLaboral(long idCandidato) {
		String jpql = "delete from HistoriaLaboral e where e.idCandidato = :idCandidato";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("idCandidato", idCandidato);

		query.executeUpdate();
	}
	
	public long registrarExpectativaLaboral(long idCandidato, ExpectativaLaboralVO expLaboralVO) {
		ExpectativaLaboral expLaboral = new ExpectativaLaboral();

		actualizaFechaUltimaActualizacion(idCandidato);
		
		expLaboral.setIdCandidato(idCandidato);
		expLaboral.setFechaAlta(Calendar.getInstance().getTime());
		

		setExpLaboral(expLaboral, expLaboralVO);
		
		entityManager.persist(expLaboral);
		
		long idExpectativaLaboral = expLaboral.getIdExpectativaLaboral();
		return idExpectativaLaboral;
	}
	
	public void borrarExpectativaLaboral(long idCandidato) {
		String jpql = "delete from ExpectativaLaboral e where e.idCandidato = :idCandidato";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("idCandidato", idCandidato);

		query.executeUpdate();
	}
	
	@Override
	public ExpectativaVO registrarExpectativa(ExpectativaVO expectativa) throws PersistenceException {
		/* PERFIL_LABORAL */
		
		PerfilLaboral perfil = entityManager.find(PerfilLaboral.class,
				expectativa.getIdCandidato());
		if (perfil != null) {// En este paso del proceso el PerfilLaboral debe existir
			setPerfilLaboral(perfil, expectativa);
		}
		
		/* EXPECTATIVA_LABORAL */
		Date fechaAlta = new Date();
		
		ExpectativaLaboral expLaboral = null;
		ExpectativaLaboralVO expLaboralVO = expectativa.getExpLaboral();
		
		// Si ya tiene Id asignado solo se actualiza
		if (expLaboralVO.getIdExpectativaLaboral() != 0) {
			expLaboral = entityManager.find(ExpectativaLaboral.class, expLaboralVO.getIdExpectativaLaboral());
			setExpLaboral(expLaboral, expLaboralVO);
			entityManager.persist(expLaboral);
		} else {
			expLaboral = new ExpectativaLaboral();
			expLaboral.setIdCandidato(expectativa.getIdCandidato());
			expLaboral.setFechaAlta(fechaAlta);
			setExpLaboral(expLaboral, expLaboralVO);
			entityManager.persist(expLaboral);
			
			//Obtiene id generado
			expLaboralVO.setIdExpectativaLaboral(expLaboral.getIdExpectativaLaboral());
			expectativa.setExpLaboral(expLaboralVO);
		}
		if(expLaboralVO.getIdExpectativaLaboral2() != 0){
			ExpectativaLaboral expLaboral2 = entityManager.find(ExpectativaLaboral.class, expLaboralVO.getIdExpectativaLaboral2());
			setExpLaboral2(expLaboral2, expLaboralVO);
			entityManager.persist(expLaboral2);
		}
		else
		{
			ExpectativaLaboral expLaboral2 = new ExpectativaLaboral();;
			expLaboral2.setIdCandidato(expectativa.getIdCandidato());
			expLaboral2.setFechaAlta(fechaAlta);
			setExpLaboral2(expLaboral2, expLaboralVO);
			entityManager.persist(expLaboral2);
			
			//Obtiene id generado
			expLaboralVO.setIdExpectativaLaboral2(expLaboral2.getIdExpectativaLaboral());
			expectativa.setExpLaboral(expLaboralVO);
		}
		
		/* EXPECTATIVA_LUGAR 
		ExpectativaLugarVO expLugarVO = expectativa.getExpLugar();
		ExpectativaLugar expLugar = null;
		
		// Si ya tiene Id asignado solo se actualiza
		if (expLugarVO.getIdExpectativaLugar() != 0) {
			expLugar = entityManager.find(ExpectativaLugar.class, expLugarVO.getIdExpectativaLugar());
			setExpLugar(expLugar, expLugarVO);
		} else {
			expLugar = new ExpectativaLugar();
			expLugar.setIdCandidato(expectativa.getIdCandidato());
			expLugar.setFechaAlta(fechaAlta);
			
			setExpLugar(expLugar, expLugarVO);
			entityManager.persist(expLugar);
			
			//Obtiene id generado
			expLugarVO.setIdExpectativaLugar(expLugar.getIdExpectativaLugar());
			expectativa.setExpLugar(expLugarVO);
		}
		*/
		return expectativa;
	}
	
	@Override
	public void agregarExpecLaboral(long idCandidato,
			ExpectativaLaboralVO expecLaboralVO) throws PersistenceException {
		ExpectativaLaboral expecLaboral;
		if (expecLaboralVO.getIdExpectativaLaboral() != 0) { // Conocimiento o Habilidad tiene consecutivo
			expecLaboral = entityManager.find(ExpectativaLaboral.class,
					expecLaboralVO.getIdExpectativaLaboral());
			if (expecLaboral != null) { // Si existe el grado academico
				setExpLaboral(expecLaboral, expecLaboralVO);
			}
		} else { // Sin consecutivo, se crea grado academico
			expecLaboral = new ExpectativaLaboral();
			Date fechaAlta = new Date();
			expecLaboral.setIdCandidato(idCandidato);
			setExpLaboral(expecLaboral, expecLaboralVO);
			expecLaboral.setFechaAlta(fechaAlta);
			entityManager.persist(expecLaboral);
			expecLaboralVO.setIdExpectativaLaboral(expecLaboral
					.getIdExpectativaLaboral());
			//logger.info("idGenerado: "+ expecLaboralVO.getIdExpectativaLaboral());
		}
	}
	
	@Override
	public void borrarExpecLaboral(long idExpectativaLaboral)
			throws PersistenceException {
		
		ExpectativaLaboral expectativaLaboral =  null;
		expectativaLaboral = entityManager.find(ExpectativaLaboral.class, idExpectativaLaboral);		
		
		if (expectativaLaboral != null){
			entityManager.remove(expectativaLaboral);			
		}
	}
	
	@Override
	public void agregarExpecLugar(long idCandidato,
			ExpectativaLugarVO expecLugarVO) throws PersistenceException {
		ExpectativaLugar expecLugar;
		if (expecLugarVO.getIdExpectativaLugar() != 0) { // Conocimiento o Habilidad tiene consecutivo
			expecLugar = entityManager.find(ExpectativaLugar.class,
					expecLugarVO.getIdExpectativaLugar());
			if (expecLugar != null) { // Si existe el grado academico
				setExpLugar(expecLugar, expecLugarVO);
			}
		} else { // Sin consecutivo, se crea grado academico
			expecLugar = new ExpectativaLugar();
			Date fechaAlta = new Date();
			expecLugar.setIdCandidato(idCandidato);
			setExpLugar(expecLugar, expecLugarVO);
			expecLugar.setFechaAlta(fechaAlta);
			entityManager.persist(expecLugar);
			expecLugarVO.setIdExpectativaLugar(expecLugar
					.getIdExpectativaLugar());
			//logger.info("idGenerado: "+ expecLugarVO.getIdExpectativaLugar());
		}
	}

	@Override
	public void borrarExpecLugar(long idExpectativaLugar) throws PersistenceException {
		ExpectativaLugar expecLugar = entityManager.find(ExpectativaLugar.class, idExpectativaLugar);
		
		if (expecLugar != null)
			entityManager.remove(expecLugar);
	}

//	public CandidatoVo save(CandidatoVo candidatoVo) throws PersistenceException {
//		Candidato candidato = null;
//		try {
//			 candidato = getCandidatoVo(candidatoVo);			 
//			 entityManager.persist(candidato);
//			 entityManager.flush();
//
//		} catch (Exception re) {
//			re.printStackTrace();
//			throw new PersistenceException(re);
//		}
//
//		return getCandidatoVo(candidato);
//	}
//
	public long save(Candidato candidato) throws PersistenceException {
		
		try {
			  
			 entityManager.persist(candidato);
			 entityManager.flush();

		} catch (Exception re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}

		return candidato.getIdCandidato();
	}

	
	public void actualizaRegistroCandidato(CandidatoVo vo) throws PersistenceException {	

		try {
			Candidato entity = entityManager.find(Candidato.class, vo.getIdCandidato());

			//entity.setIdCandidato(vo.getIdCandidato());
			//entity.setIdUsuario(vo.getIdUsuario());
			//entity.setCurp(vo.getCurp());
			//entity.setNombre(vo.getNombre());
			//entity.setApellido1(vo.getApellido1());
			//entity.setApellido2(vo.getApellido2());
			//entity.setGenero(vo.getGenero());
			//entity.setFechaNacimiento(vo.getFechaNacimiento());
			//entity.setIdEntidadNacimiento(vo.getIdEntidadNacimiento());
			//entity.setFechaAlta(vo.getFechaAlta());
			//entity.setAvisoOportuno(vo.getavisoOportuno);
			//entity.setNss(vo.getNss());
			//entity.setCreditoInfonavit(vo.getCreditoInfonavit());
			//entity.setRegistroBumeran(vo.getregistroBumeran);
			//entity.setFechaConfirma(vo.getFechaConfirma());
			
			if(null==entity.getIdTipoDiscapacidad()){
				entity.setIdTipoDiscapacidad((int)Constantes.TIPO_DISCAPACIDAD.NINGUNA.getIdOpcion());
			}			
			//entity.setEdad(vo.getEdad());
			entity.setEstatus(vo.getEstatus());
			entity.setCorreoElectronico(vo.getCorreoElectronico());
			entity.setIdMedioPortal(vo.getIdMedioPortal());
			entity.setConfidencialidadDatos(vo.getConfidencialidadDatos());
			entity.setVeracidadDatos(vo.getVeracidadDatos());
			entity.setAceptacionTerminos(vo.getAceptacionTerminos());			
			entity.setFechaUltimaActualizacion(vo.getFechaUltimaActualizacion());
			entity.setEstiloCv(vo.getEstiloCv());
			entity.setEvaluaCv(vo.getEvaluaCv());
			entity.setApoyoProspera(vo.getApoyoProspera());
			entity.setFolioProspera(vo.getFolioProspera());
			entity.setFolioIntegranteProspera(vo.getFolioIntegranteProspera());

			/** Datos que se limpian **/
			entity.setIdEstadoCivil(0);
			
			entityManager.merge(entity);

		} catch (Exception re) {
			re.printStackTrace(); logger.error(re);
			throw new PersistenceException(re);
		}
	}

	private CandidatoVo getCandidatoVo(Candidato candidato){
		CandidatoVo candidatoVo = new CandidatoVo();		

		candidatoVo.setIdCandidato(candidato.getIdCandidato());
		candidatoVo.setIdUsuario(candidato.getIdUsuario());
		if(candidato.getSolicitante() != null){
			candidatoVo.setCurp(candidato.getSolicitante().getCurp());
			candidatoVo.setNombre(candidato.getSolicitante().getNombre());
			candidatoVo.setApellido1(candidato.getSolicitante().getApellido1());
			candidatoVo.setApellido2(candidato.getSolicitante().getApellido2());
			candidatoVo.setGenero(candidato.getSolicitante().getGenero());
			candidatoVo.setFechaNacimiento(candidato.getSolicitante().getFechaNacimiento());
			candidatoVo.setEdad(candidato.getSolicitante().getEdad());
		}
//		candidatoVo.setOportunidades(candidato.getApoyoOrtunidades());
		candidatoVo.setFechaAlta(Utils.toCalendar(candidato.getFechaAlta()));
		candidatoVo.setIdEntidadNacimiento((int)candidato.getIdEntidadNacimiento());
//		candidatoVo.setIdEstadoCivil((int)candidato.getIdEstadoCivil());
		candidatoVo.setIdTipoDiscapacidad((int)candidato.getIdTipoDiscapacidad());
		candidatoVo.setIdMedioPortal((int)candidato.getIdMedioPortal());
		candidatoVo.setConfidencialidadDatos(candidato.getConfidencialidadDatos());
		candidatoVo.setVeracidadDatos(candidato.getVeracidadDatos());
		candidatoVo.setAceptacionTerminos(candidato.getAceptacionTerminos());
		candidatoVo.setEstatus(candidato.getEstatus());
		candidatoVo.setFechaUltimaActualizacion(candidato.getFechaUltimaActualizacion());
		candidatoVo.setCorreoElectronico(candidato.getCorreoElectronico());
		candidatoVo.setEstiloCv(candidato.getEstiloCv());
		if(candidato.getEvaluaCv() != null) {
            candidatoVo.setEvaluaCv(candidato.getEvaluaCv());
        }
		candidatoVo.setFechaConfirma(candidato.getFechaConfirma());
		candidatoVo.setDiscapacidades(candidato.getDiscapacidades());
		
		candidatoVo.setPpcAceptacionTerminos((candidato.getPpcAceptacionTerminos()!=null)?candidato.getPpcAceptacionTerminos():0);
		candidatoVo.setPpcEstatus		    ((candidato.getPpcEstatus()!=null)?candidato.getPpcEstatus():0);
		candidatoVo.setPpcFechaInscripcion(candidato.getPpcFechaInscripcion());
		
		candidatoVo.setPpcEstatusIMSS((candidato.getPpcEstatusIMSS()!=null)?candidato.getPpcEstatusIMSS():0);
		candidatoVo.setPpcTipoContratoIMSS(candidato.getPpcTipoContratoIMSS());
		candidatoVo.setPpcFechaBajaIMSS(candidato.getPpcFechaBajaIMSS());
		//candidatoVo.setavisoOportuno;
		candidatoVo.setNss(candidato.getNss());
		//candidatoVo.setcreditoInfonavit;
		//candidatoVo.setregistroBumeran;
		PerfilLaboral pl = entityManager.find(PerfilLaboral.class,
				candidato.getIdCandidato());

		candidatoVo.setIdEstatusLaboral((pl.getEmpleadoActualmente() != null ? pl.getEmpleadoActualmente() : new Integer(0)));
		
		return candidatoVo;
	}
	
	@SuppressWarnings("unchecked")
	public Boolean repetidaCurp(String curp) {
		Boolean bandera = false;

//		Query query = entityManager.createNamedQuery("findCandidatoCurp");
//		query.setParameter("curpCadena", curp);

        StringBuilder queryString = new StringBuilder();
        queryString.append("SELECT c.* ");
        queryString.append("FROM CANDIDATO c ");
        queryString.append("WHERE ");
        queryString.append("c.ID_CANDIDATO = (SELECT ID_CANDIDATO FROM SOLICITANTE WHERE CURP = '").append(curp).append("')");

        Query query = entityManager.createNativeQuery(queryString.toString());
		
		try{
			List<Candidato> resultList = (List<Candidato>)query.getResultList();

			if(resultList!= null && resultList.size()!=0)
				bandera =  true;
			
		} catch (NoResultException re) {	
			re.printStackTrace(); logger.error(re);
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}

		return bandera;
	}

	
	@Override
	public long activarCandidato(long idCandidato) throws PersistenceException {
		long idUsuario = 0;
		Calendar date = Calendar.getInstance();
		date.set(2011, Calendar.DECEMBER, 12);		
		try {
			Candidato candidato = entityManager.find(Candidato.class, idCandidato);
			candidato.setEstatus(ESTATUS.ACTIVO.getIdOpcion());
			candidato.setFechaUltimaActualizacion(new Date());
			candidato.setAceptacionTerminos(ESTATUS.ACTIVO.getIdOpcion());
			if (null == candidato.getFechaConfirma()){
				//if(candidato.getFechaAlta().compareTo(date.getTime()) < 0)
				if(candidato.getFechaAlta().compareTo(date.getTime()) < 0)
					candidato.setFechaConfirma(new Date());
				else
					candidato.setFechaConfirma(candidato.getFechaAlta());
					//candidato.setFechaConfirma(candidato.getFechaAlta());
			}  
			idUsuario = candidato.getIdUsuario();			
			entityManager.merge(candidato);
			entityManager.flush();
		}catch (RuntimeException re) {				
			re.printStackTrace();
			throw new PersistenceException(re);
		}
		
		return idUsuario;
	}
	
	public void actualizaEstatusCandidato(long idCandidato, int estatus) {
		try {
			Candidato candidato = entityManager.find(Candidato.class, idCandidato);
			candidato.setEstatus(estatus);
			candidato.setFechaUltimaActualizacion(new Date());			
			entityManager.merge(candidato);
			entityManager.flush();

		} catch (NoResultException re) {
			logger.error("Candidato no localizado mediante el idCandidato : "+ idCandidato);
		} catch (Exception re) {				
			re.printStackTrace(); logger.error(re);
			throw new PersistenceException(re);
		}
	}
	
	public void eliminarDetalleDesactivacion(long idCandidato){
		try {
			Candidato candidato = entityManager.find(Candidato.class, idCandidato);
//			candidato.setIdMotivoDesactivacion((Integer) null);
//			candidato.setDetalleDesactivacion(null);			
			candidato.setFechaUltimaActualizacion(new Date());			
			entityManager.merge(candidato);
			entityManager.flush();

		} catch (NoResultException re) {
			logger.error("Candidato no localizado mediante el idCandidato : "+ idCandidato);
		} catch (Exception re) {				
			re.printStackTrace(); logger.error(re);
			throw new PersistenceException(re);
		}		
	}
	
	@Override
	public long inactivarCandidato(long idCandidato) throws PersistenceException {
		long idUsuario = 0;
		
		try {
			Candidato candidato = entityManager.find(Candidato.class, idCandidato);
			candidato.setEstatus(ESTATUS.INACTIVO.getIdOpcion());
			candidato.setFechaUltimaActualizacion(new Date());				
			entityManager.merge(candidato);
			entityManager.flush();

			idUsuario = candidato.getIdUsuario();
			
		} catch (RuntimeException re) {				
			re.printStackTrace();
			throw new PersistenceException(re);
		}

		return idUsuario;
	}
	
	public long inactivarCandidato(long idCandidato, int idMotivoDesactivacion, String detalleDesactivacion) throws PersistenceException {
		long idUsuario = 0;
		
		try {
			Candidato candidato = entityManager.find(Candidato.class, idCandidato);
			
			if(idMotivoDesactivacion == Constantes.MOTIVO_DESACTIVACION_CANDIDATO.A_PETICION_DEL_USUARIO.getIdMotivo()){
				candidato.setEstatus(ESTATUS.INACTIVO_A_PETICION_DEL_CANDIDATO.getIdOpcion());
				candidato.setFechaUltimaActualizacion(new Date());	
				
			} else if(idMotivoDesactivacion == Constantes.MOTIVO_DESACTIVACION_CANDIDATO.POR_MAL_USO_SERVICIOS_SNE.getIdMotivo()) {
				candidato.setEstatus(ESTATUS.INACTIVO_POR_BAJA_ADMINISTRADOR.getIdOpcion());
				
			} else if(idMotivoDesactivacion == Constantes.MOTIVO_DESACTIVACION_CANDIDATO.POR_VIGENCIA.getIdMotivo()){
				candidato.setEstatus(ESTATUS.INACTIVO_POR_VIGENCIA.getIdOpcion());
			}			
			
					
//			candidato.setIdMotivoDesactivacion(idMotivoDesactivacion);
//			if(null != detalleDesactivacion){
//				//candidato.setDetalleDesactivacion(detalleDesactivacion);
//			}						
			entityManager.merge(candidato);
			entityManager.flush();

			idUsuario = candidato.getIdUsuario();
			
		} catch (RuntimeException re) {				
			re.printStackTrace();
			throw new PersistenceException(re);
		}

		return idUsuario;
	}	
	
	private Candidato getCandidatoVo(CandidatoVo candidatoVo) {

		Candidato candidatoEntity = new Candidato();

		candidatoEntity.setIdCandidato(candidatoVo.getIdCandidato());
		candidatoEntity.setIdUsuario(candidatoVo.getIdUsuario());
		candidatoEntity.setFechaAlta(candidatoVo.getFechaAlta().getTime());
		candidatoEntity.setIdEntidadNacimiento(candidatoVo.getIdEntidadNacimiento());
		candidatoEntity.setIdEstadoCivil(candidatoVo.getIdEstadoCivil());
		candidatoEntity.setIdTipoDiscapacidad(candidatoVo.getIdTipoDiscapacidad());
		candidatoEntity.setIdMedioPortal(candidatoVo.getIdMedioPortal());
		candidatoEntity.setConfidencialidadDatos(candidatoVo.getConfidencialidadDatos());
		candidatoEntity.setVeracidadDatos(candidatoVo.getVeracidadDatos());
		candidatoEntity.setAceptacionTerminos(candidatoVo.getAceptacionTerminos());
		candidatoEntity.setEstatus(candidatoVo.getEstatus());
		candidatoEntity.setFechaUltimaActualizacion(candidatoVo.getFechaUltimaActualizacion());
		candidatoEntity.setCorreoElectronico(candidatoVo.getCorreoElectronico());
		candidatoEntity.setEstiloCv(candidatoVo.getEstiloCv());
		candidatoEntity.setFechaConfirma(candidatoVo.getFechaConfirma());
		candidatoEntity.setIdFuente(candidatoVo.getIdFuente());
		candidatoEntity.setDiscapacidades(candidatoVo.getDiscapacidades());
		candidatoEntity.setIdOficina(candidatoVo.getIdOficina());

		if (candidatoVo.getNss() != null && !candidatoVo.getNss().trim().isEmpty())
				candidatoEntity.setNss(candidatoVo.getNss());

		if (candidatoVo.getCreditoInfonavit() != null)
			candidatoEntity.setCreditoInfonavit(candidatoVo.getCreditoInfonavit().longValue());
		
		candidatoEntity.setApoyoProspera(candidatoVo.getApoyoProspera());
		
		return candidatoEntity;
	}

	private void setPerfil(PerfilLaboral pl, PerfilVO perfil) {
		pl.setIdCandidato(perfil.getIdCandidato());
		pl.setContactoCorreo(perfil.getContactoCorreo());
		pl.setContactoTelefono(perfil.getContactoTelefono());
		pl.setHorarioContactoDe(perfil.getHoraContactoIni());
		pl.setHorarioContactoA(perfil.getHoraContactoFin());
		pl.setIdRecibeOferta(perfil.getIdRecibeOferta());
		pl.setEmpleadoActualmente(perfil.getIdTrabaja());
		pl.setIdRazonBusqueda(perfil.getIdRazonBusqueda());
		pl.setDescripcionOtroMotivoBusq(perfil.getDescripcionOtroMotivoBusq());
		pl.setInicioBusqueda(perfil.getInicioBusqueda());
	}
	
	private void setDomicilio(Domicilio dom, PerfilVO perfilVO) {
		dom.setIdTipoPropietario(perfilVO.getIdTipoPropietario());
		dom.setIdEntidad(perfilVO.getIdEntidad());
		dom.setIdMunicipio(perfilVO.getIdMunicipio());
		dom.setIdColonia(perfilVO.getIdColonia());
		dom.setCalle(perfilVO.getCalle());
		dom.setNumeroExterior(perfilVO.getNumeroExterior());
		dom.setNumeroInterior(perfilVO.getNumeroInterior());
		dom.setEntreCalle(perfilVO.getEntreCalle());
		dom.setYCalle(perfilVO.getyCalle());
		dom.setCodigoPostal(perfilVO.getCodigoPostal());
		if(perfilVO.getLatitud()!=null && perfilVO.getLongitud()!=null){
			JGeometry geoReferencia = new JGeometry(perfilVO.getLatitud(), perfilVO.getLongitud(), 4326);
//			dom.setGeoReferencia(geoReferencia);
		}
	}
	
	private void setTelefono(Telefono tel, TelefonoVO telVO) {
		String telefono = Utils.cut(telVO.getTelefono(), 8);
		
		tel.setIdTipoPropietario(Utils.toInt(telVO.getIdTipoPropietario()));
		tel.setIdTipoTelefono(Utils.toInt(telVO.getIdTipoTelefono()));
		tel.setAcceso(telVO.getAcceso());
		tel.setClave(telVO.getClave());
		tel.setTelefono(telefono);
		tel.setExtension(telVO.getExtension());
		tel.setPrincipal(telVO.getPrincipal());
	}

	private void setGradoAcademico(CandidatoGradoAcademico grado,
			GradoAcademicoVO gradoVO) {
		grado.setIdNivelEstudio(gradoVO.getIdNivelEstudio());
		grado.setIdSituacionAcademica(gradoVO.getIdSituacionAcademica());
		grado.setIdCarreraEspecialidad(gradoVO.getIdCarreraEspecialidad());
		grado.setEscuela(gradoVO.getEscuela());
		grado.setInicio(Utils.toLong(gradoVO.getInicio()));
		grado.setFin(Utils.toLong(gradoVO.getFin()));
		grado.setPrincipal(Utils.toLong(gradoVO.getPrincipal()));
	}

	private void setCandidatoIdioma(CandidatoIdioma idioma, IdiomaVO idiomaVO) {
		idioma.setIdIdioma(idiomaVO.getIdIdioma());
		idioma.setIdCertificacion(idiomaVO.getIdCertificacion());
		idioma.setIdDominio(idiomaVO.getIdDominio());
		idioma.setPrincipal(Utils.toLong(idiomaVO.getPrincipal()));
	}

	private void setConocimientoHabilidad(CandidatoConocimHabilidad conocimHabilidad,	ConocimientoHabilidadVO conocimHabilidadVO) {
		conocimHabilidad.setIdTipoConocimHabilidad(conocimHabilidadVO.getIdTipoConocimHabilidad());
		conocimHabilidad.setConocimientoHabilidad(conocimHabilidadVO.getConocimientoHabilidad());
		conocimHabilidad.setIdExperiencia(conocimHabilidadVO.getIdExperiencia());
		if (conocimHabilidadVO.getIdDominio() > 0)
			conocimHabilidad.setIdDominio(conocimHabilidadVO.getIdDominio());
		conocimHabilidad.setDescripcion(conocimHabilidadVO.getDescripcion());
		conocimHabilidad.setPrincipal(Utils.toLong(conocimHabilidadVO.getPrincipal()));
	}

	private void setPerfilLaboral(PerfilLaboral perfil,
			EscolaridadVO escolaridad) {
		perfil.setSinEstudios(escolaridad.getSinEstudios());
		perfil.setComputacionBasica(escolaridad.getComputacionBasica());
		perfil.setComputacionAvanzada(escolaridad.getComputacionAvanzada());
		//No afirma "No tener estudios"
		if (perfil.getSinEstudios() == ESCOLARIDAD.CON_ESTUDIOS.getIdOpcion()) {
			perfil.setIdExperienciaCompu(escolaridad.getIdExperienciaCompu());
			perfil.setIdDominioCompu(escolaridad.getIdDominioCompu());
			perfil.setIdExperienciaOffice(escolaridad.getIdExperienciaOffice());
			perfil.setIdDominioOffice(escolaridad.getIdDominioOffice());
			perfil.setIdExperienciaInternet(escolaridad.getIdExperienciaInternet());
			perfil.setIdDominioInternet(escolaridad.getIdDominioInternet());
		} else  {//Afirma "No tener estudios"
			perfil.setIdExperienciaCompu((long)0);
			perfil.setIdDominioCompu((long)0);
			perfil.setIdExperienciaOffice((long)0);
			perfil.setIdDominioOffice((long)0);
			perfil.setIdExperienciaInternet((long)0);
			perfil.setIdDominioInternet((long)0);
		}
	}

	//private void setCandidatoCompuAvanzada(
//			//CandidatoCompuAvanzada compuAvanzada,
//			ComputacionAvanzadaVO compuAvanzadaVO) {
//		compuAvanzada
//				.setSoftwareHardware(compuAvanzadaVO.getSoftwareHardware());
//		compuAvanzada.setIdExperiencia(compuAvanzadaVO.getIdExperiencia());
//		compuAvanzada.setIdDominio(compuAvanzadaVO.getIdDominio());
//		compuAvanzada.setDescripcion(compuAvanzadaVO.getDescripcion());
//		compuAvanzada.setPrincipal(compuAvanzadaVO.getPrincipal());
	//}

	private void setPerfilLaboral(PerfilLaboral perfil,ExperienciaVO experiencia) {
		
		perfil.setIdExperienciaTotal(experiencia.getIdExperienciaTotal());
		perfil.setExperiencia(experiencia.getExperiencia());
		perfil.setIdSectorMayorExpr(experiencia.getIdSectorMayorExpr());
		perfil.setPuestoMayorExpr(experiencia.getPuestoMayorExpr());
		perfil.setIdAreaLaboralMayorExpr(experiencia.getIdAreaLaboralMayorExpr());
		perfil.setIdOcupacionMayorExpr(experiencia.getIdOcupacionMayorExpr());
		
		if(experiencia.isNuncaHeTrabajado()){
			perfil.setSinExperiencia(EXPERIENCIA_LABORAL.CON_EXPERIENCIA.getIdOpcion());
		}else{
			perfil.setSinExperiencia(EXPERIENCIA_LABORAL.SIN_EXPERIENCIA.getIdOpcion());
		}
	}

	private void setHistoriaLaboral(HistoriaLaboral histLaboral,
			HistoriaLaboralVO histLaboralVO) {
		histLaboral.setTrabajoActual(Utils.toLong(histLaboralVO.getTrabajoActual()));
		histLaboral.setIdSector(histLaboralVO.getIdSector());
		histLaboral.setPuesto(histLaboralVO.getPuesto());
		histLaboral.setIdAreaLaboral(histLaboralVO.getIdAreaLaboral());
		histLaboral.setIdOcupacion(histLaboralVO.getIdOcupacion());
		histLaboral.setEmpresa(histLaboralVO.getEmpresa());
		histLaboral.setConfidencialidadEmpresa(Utils.toLong(histLaboralVO.getConfidencialidadEmpresa()));
		histLaboral.setLaboresInicial(histLaboralVO.getLaboresInicial());
		histLaboral.setLaboresFinal(histLaboralVO.getLaboresFinal());
		histLaboral.setAniosLaborados(Utils.toLong(histLaboralVO.getAniosLaborados()));
		histLaboral.setIdJerarquia(histLaboralVO.getIdJerarquia());
		histLaboral.setPersonasCargo(Utils.toLong(histLaboralVO.getPersonasCargo()));
		histLaboral.setSalarioMensual(histLaboralVO.getSalarioMensual());
		histLaboral.setFuncion(histLaboralVO.getFuncion());
		histLaboral.setLogro(histLaboralVO.getLogro());
		histLaboral.setPrincipal(Utils.toLong(histLaboralVO.getPrincipal()));
	}

	private void setPerfilLaboral(PerfilLaboral perfil,
			ExpectativaVO expectativa) {
		perfil.setDisponibilidadViajar(expectativa.getDisponibilidadViajar());
		perfil.setDisponibilidadRadicar(expectativa.getDisponibilidadRadicar());
	}

	private void setExpLaboral(ExpectativaLaboral expLaboral,
			ExpectativaLaboralVO expLaboralVO) {
		//expLaboral.setIdSectorDeseado(expLaboralVO.getIdSectorDeseado());
		expLaboral.setPuestoDeseado(expLaboralVO.getPuestoDeseado());
		
		expLaboral.setIdAreaLaboralDeseada(getTwoFirst(expLaboralVO.getIdOcupacionDeseada()));
		expLaboral.setIdOcupacionDeseada(expLaboralVO.getIdOcupacionDeseada());
		expLaboral.setSalarioPretendido(expLaboralVO.getSalarioPretendido());
		expLaboral
				.setIdTipoEmpleoDeseado(expLaboralVO.getIdTipoEmpleoDeseado());
		expLaboral.setIdTipoContrato(expLaboralVO.getIdTipoContrato());
		expLaboral.setPrincipal(Utils.toLong(expLaboralVO.getPrincipal()));
		expLaboral.setIdExperiencia(expLaboralVO.getIdExperiencia());
	}
	
	private Long getTwoFirst(Long in){
		String inString = in.toString();
		if(inString.length()>=2){
			String out = inString.substring(0,2);
			return Long.valueOf(out);
		}
		return in;
	}
	
	private void setExpLaboral2(ExpectativaLaboral expLaboral,
			ExpectativaLaboralVO expLaboralVO) {
		//expLaboral.setIdSectorDeseado(expLaboralVO.getIdSectorDeseado());
		expLaboral.setPuestoDeseado(expLaboralVO.getPuestoDeseado());
		expLaboral.setIdAreaLaboralDeseada(getTwoFirst(expLaboralVO.getIdOcupacionDeseada2()));
		expLaboral.setIdOcupacionDeseada(expLaboralVO.getIdOcupacionDeseada2());
		expLaboral.setSalarioPretendido(expLaboralVO.getSalarioPretendido());
		expLaboral
				.setIdTipoEmpleoDeseado(expLaboralVO.getIdTipoEmpleoDeseado());
		expLaboral.setIdTipoContrato(expLaboralVO.getIdTipoContrato());
		expLaboral.setPrincipal(Utils.toLong(MULTIREGISTRO.ADICIONAL.getIdOpcion()));
		expLaboral.setIdExperiencia(expLaboralVO.getIdExperiencia2());
	}

	private void setExpLugar(ExpectativaLugar expLugar,
			ExpectativaLugarVO expLugarVO) {
		expLugar.setIdEntidadDeseada(expLugarVO.getIdEntidadDeseada());
		expLugar.setIdMunicipioDeseado(expLugarVO.getIdMunicipioDeseado());
		expLugar.setPrincipal(Utils.toLong(expLugarVO.getPrincipal()));
	}

	/*@Override
	public Boolean usuarioInactivo(ConfirmacionRegistroVo confirmacionRegistroVo) throws PersistenceException {		
		Candidato candidato = entityManager.find(Candidato.class, confirmacionRegistroVo.getId());	
		entityManager.refresh(candidato);
		
		//logger.info("usuarioInactivo.idCandidato = " + confirmacionRegistroVo.getId());
		//logger.info("usuarioInactivo.Estatus = " + candidato.getEstatus());
	
		return (ESTATUS.INACTIVO.getIdOpcion() 	== candidato.getEstatus() ||
				ESTATUS.MODIFICADA.getIdOpcion() == candidato.getEstatus());	
	}*/

	@Override
	public void updateIdUsuario(CandidatoVo candidatoVo, int idUsuario) throws PersistenceException{
		try{
			Candidato candidato = entityManager.find(Candidato.class, candidatoVo.getIdCandidato());
			candidato.setIdUsuario(Utils.toLong(idUsuario));					
			entityManager.merge(candidato);			
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}
		
	}
	
	@Override
	public void updateCV(long idCandidato, int estatusCV) throws PersistenceException{
		try{
			Candidato candidato = entityManager.find(Candidato.class, idCandidato);
			candidato.setEvaluaCv(estatusCV);					
			entityManager.merge(candidato);			
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}
		
	}
	
	public void updateCVStyle(long idCandidato, int estiloCv) 
		throws PersistenceException {
		try{
			Date fechaActualizacion = new Date();
			Candidato candidato = entityManager.find(Candidato.class, idCandidato);
			candidato.setEstiloCv(estiloCv);
			candidato.setFechaUltimaActualizacion(fechaActualizacion);
			entityManager.merge(candidato);	
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}						
	}			
	
	public CandidatoVo find(long idCandidato) throws PersistenceException {
		CandidatoVo vo = null;
		
		try{
			Candidato entity = entityManager.find(Candidato.class, idCandidato);
			if (entity!=null){
				entityManager.refresh(entity);
				vo = getCandidatoVO(entity);
			}
		} catch(NoResultException e){
			logger.error("Candidato no localizado: "+ idCandidato);
		} catch(Exception e){
			throw new PersistenceException(e);
		}
		
		return vo;
	}

	private CandidatoVo getCandidatoVO(Candidato entity){
		CandidatoVo vo = new CandidatoVo();
		
		try {
			vo.setIdCandidato			(entity.getIdCandidato());
			vo.setIdUsuario				(entity.getIdUsuario()); // TODO CAMBIAR PROPIEDAD
			
			if (entity.getSolicitante()!=null){
				vo.setCurp					(entity.getSolicitante().getCurp());
				vo.setNombre				(entity.getSolicitante().getNombre());
				vo.setApellido1				(entity.getSolicitante().getApellido1());
				vo.setApellido2				(entity.getSolicitante().getApellido2());
				vo.setGenero				(entity.getSolicitante().getGenero());
				vo.setFechaNacimiento		(entity.getSolicitante().getFechaNacimiento());
				vo.setEdad					(entity.getSolicitante().getEdad());				
			}

			vo.setIdEntidadNacimiento	(entity.getIdEntidadNacimiento()!=null?entity.getIdEntidadNacimiento():0);
			vo.setIdEstadoCivil			(entity.getIdEstadoCivil()!=null?entity.getIdEstadoCivil():0);
			vo.setIdTipoDiscapacidad	(entity.getIdTipoDiscapacidad()!=null?entity.getIdTipoDiscapacidad():0);
			vo.setIdMedioPortal			(entity.getIdMedioPortal()!=null?entity.getIdMedioPortal():0);
			vo.setConfidencialidadDatos	(entity.getConfidencialidadDatos()!=null?entity.getConfidencialidadDatos():0);
			vo.setVeracidadDatos		(entity.getVeracidadDatos()!=null?entity.getVeracidadDatos():0);
			vo.setAceptacionTerminos	(entity.getAceptacionTerminos()!=null?entity.getAceptacionTerminos():0);
			vo.setFechaAlta				(Utils.toCalendar(entity.getFechaAlta()));
			vo.setEstatus				(entity.getEstatus()!=null?entity.getEstatus():1);
			vo.setFechaUltimaActualizacion(entity.getFechaUltimaActualizacion());
			vo.setCorreoElectronico		(entity.getCorreoElectronico());
			vo.setEstiloCv				(entity.getEstiloCv()!=null?entity.getEstiloCv():0);
			vo.setDiscapacidades		(entity.getDiscapacidades());

			vo.setPpcEstatus((entity.getPpcEstatus() != null ? entity.getPpcEstatus().intValue() : 0));
			vo.setPpcFechaInscripcion(entity.getPpcFechaInscripcion());
			vo.setPpcAceptacionTerminos((entity.getPpcAceptacionTerminos() != null ? entity.getPpcAceptacionTerminos().intValue() : 0));			
			vo.setPpcEstatusIMSS((entity.getPpcEstatusIMSS() != null ? entity.getPpcEstatusIMSS().intValue() : 0));			
			vo.setPpcFechaBajaIMSS(entity.getPpcFechaBajaIMSS());
			vo.setPpcTipoContratoIMSS(entity.getPpcTipoContratoIMSS());
			vo.setNss(entity.getNss());
			
		} catch(Exception e){
			e.printStackTrace();
		}

		return vo;
	}
	
	@Override
	public long idCandidatoPorUsuario(long idUsuario) {
		long idCandidato = 0;
		
//		Query query = entityManager.createNamedQuery("findCandidatoByIdUsuario");
        Query query = entityManager.createQuery("SELECT c FROM Candidato c WHERE c.idUsuario = :idUsuario");
		query.setParameter("idUsuario", idUsuario);
		
		try {
			Candidato candidato = (Candidato)query.getSingleResult();
			
			if (candidato!=null) idCandidato = candidato.getIdCandidato();
			
		} catch (NoResultException e) {
			logger.error(e);
		}

		return idCandidato;
	}
	
	public CandidatoVo consultaCandidato(long idUsuario) {
		CandidatoVo candidatoVO = null;

        Query query = entityManager.createQuery("SELECT c FROM Candidato c WHERE c.idUsuario = :idUsuario");
        query.setParameter("idUsuario", idUsuario);

        try {
            Candidato candidato = (Candidato)query.getSingleResult();

            if (candidato != null) {
                candidatoVO = getCandidatoVo(candidato);
            }
        } catch (NoResultException e) {
            logger.error("Candidato no localizado por el idUsuario"+ idUsuario);
        }

		return candidatoVO;
	}
	
	private void setEstatusCandidato(Candidato candidato, PerfilVO perfil) {
		if (candidato.getEstatus() == ESTATUS.INACTIVO.getIdOpcion()) {
			candidato.setEstatus(ESTATUS.ACTIVO.getIdOpcion());
		} else if (candidato.getEstatus() == ESTATUS.ACTIVO.getIdOpcion()) {
			candidato.setEstatus(ESTATUS.ACTIVO.getIdOpcion());
		} else if (candidato.getEstatus() == ESTATUS.MODIFICADA.getIdOpcion()) {
			candidato.setEstatus(ESTATUS.ACTIVO.getIdOpcion());
		}
	}
	
	
	/**
	 * Metodo que obtiene el contador del candidato
	 * @param idCandidato
	 * @return CandidatoVerDetalleVO
	 * @throws SQLException 
	 */
	public CandidatoVerDetalleVO obtenerCandidatoVerDetalle(long idCandidato, int anio, int mes) throws PersistenceException{
		CandidatoVerDetalleVO vo = null;
		StringBuilder sb = new StringBuilder();
		try {
			sb.append("SELECT c FROM CandidatoVerDetalle c WHERE c.idCandidato=:idCandidato AND c.anio=:anio AND c.mes=:mes");
			Query query = entityManager.createQuery(sb.toString());
			query.setParameter("idCandidato", idCandidato);
			query.setParameter("anio", anio);
			query.setParameter("mes", (mes + 1));
			CandidatoVerDetalle entity = null;
			entity = (CandidatoVerDetalle) query.getSingleResult();
			if(entity != null)
				vo = getCandidatoPostulacion(entity);
		} catch (NoResultException e) {
			logger.error("Candidato no localizado idCandidato:"+ idCandidato +"  anio:"+ anio +" mes:"+ mes);
			//e.printStackTrace();
		}
		
		return vo;
	}
	
	/**
	 * Metodo que nos permite asignar valores de una entity a un VO
	 * @param entity
	 * @return CandidatoVerDetalleVO
	 */
	private CandidatoVerDetalleVO getCandidatoPostulacion(CandidatoVerDetalle entity){
		CandidatoVerDetalleVO vo = new CandidatoVerDetalleVO();
		vo.setIdCandidato(entity.getIdCandidato());
		vo.setAnio(Utils.toInt(entity.getAnio()));
		vo.setMes(Utils.toInt(entity.getMes()));
		vo.setContador(Utils.toInt(entity.getContador()));
		vo.setIdCandidatoVerDetalle(entity.getIdCandidatoVerDetalle());
		return vo;
	}
	
	/**
	 * Metodo para actualizar el contador del candidato
	 * @param vo
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 * @throws SQLException
	 */
	public int actualizarCandidatoVerDetalle(CandidatoVerDetalleVO vo) throws SQLException{
		int resul = -1;
		try {
			CandidatoVerDetalle detalle = getEntity(vo);
			entityManager.merge(detalle);
			resul = 1;
		} catch (RuntimeException e) {
			logger.error(e);
		}
		return resul;
	}
	
	/**
	 * Metodo que crea un contador para el candidato
	 * @param vo
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 * @throws SQLException
	 */
	public int crearCandidatoVerDetalle(CandidatoVerDetalleVO vo) throws SQLException{
		int resul = -1;
		try {
			CandidatoVerDetalle detalle = getEntity(vo);
			entityManager.persist(detalle);
			resul = 1;
		} catch (RuntimeException e) {
			logger.error(e);
		}
		return resul;
	}

	public CandidatoVo consultaPorCURP(String curp) {
		CandidatoVo candidato = null;

		Query query = entityManager.createQuery("select c from Candidato c where c.solicitante.curp = :curp");
		query.setParameter("curp", curp);

		try{
			@SuppressWarnings("unchecked")
			List<Candidato> results = (List<Candidato>)query.getResultList();
		
			if(results!= null){
				for (Candidato entity : results){
					candidato = getCandidatoVO(entity); // Toma al ultimo registro
				}
			}
		} catch (NoResultException re) {
			logger.error("Candidato no localizado mediante la CURP : "+ curp);
		} catch (Exception re) {
			throw new PersistenceException(re);
		}

		return candidato;
	}
	
	
	
	private CandidatoVerDetalle getEntity(CandidatoVerDetalleVO vo){
		CandidatoVerDetalle detalle = new CandidatoVerDetalle();
		detalle.setIdCandidato(vo.getIdCandidato());
		detalle.setIdCandidatoVerDetalle(vo.getIdCandidatoVerDetalle());
		detalle.setContador(Utils.toLong(vo.getContador()));
		detalle.setAnio(Utils.toLong(vo.getAnio()));
		detalle.setMes(Utils.toLong(vo.getMes()));
		return detalle;
	}

	
	public void actualizaCorreoElectronico(long idCandidato, String correoElectronico) throws PersistenceException {		
		try{
			Candidato entity = entityManager.find(Candidato.class, idCandidato);
			entity.setCorreoElectronico(correoElectronico);
			entity.setFechaUltimaActualizacion(new Date());
			entityManager.merge(entity);
			entityManager.flush();
		} catch(Exception e){
			throw new PersistenceException(e);
		}

	}
	@Override
	public Boolean idCorreo(long idCandidato,String correo) throws PersistenceException {
		Candidato candidato = entityManager.find(Candidato.class, idCandidato);
		return candidato.getCorreoElectronico().equals(correo);
	}

	public CandidatoVo consultaCandidatoPorCorreo(String correoElectronico) throws PersistenceException {
		CandidatoVo vo = null;

		try{
			Query query = entityManager.createQuery("SELECT c FROM Candidato c WHERE LOWER(c.correoElectronico) = LOWER(:correo)");
			query.setParameter("correo", correoElectronico);

			Candidato entity = (Candidato)query.getSingleResult();
			vo = getCandidatoVo(entity);

		} catch (NoResultException re) {
			logger.error("Candidato no localizado mediante el correo : "+ correoElectronico);
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenceException(e);
		}
		
		return vo;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<CandidatoVo> consultaCandidatoRegByInfonavit() throws PersistenceException {
		List<CandidatoVo> candidatoVoList = new ArrayList<CandidatoVo>();
		
		try{
			StringBuilder queryString = new StringBuilder();

			queryString.append("WITH CANDIDATO_INF AS (SELECT * FROM CANDIDATO WHERE (nss IS NOT NULL OR NVL(credito_infonavit,0) > 0)), ");

			queryString.append("GRADO AS (SELECT c.id_candidato, MAX(ga.id_candidato_grado_academico) as id_candidato_grado_academico ");
			queryString.append("FROM CANDIDATO_INF c, ");
			queryString.append("candidato_grado_academico ga ");
			queryString.append("WHERE ga.id_candidato = c.id_candidato ");
			queryString.append("AND ga.principal = 1 ");
			queryString.append("GROUP BY c.id_candidato ) ");

			queryString.append("SELECT c.nss,c.credito_infonavit,c.nombre,c.apellido1,c.apellido2,c.correo_electronico, ");
			queryString.append("CASE WHEN TRIM(d.calle) IS NULL THEN NULL ELSE 'CALLE: '||TRIM(d.calle) END|| ");
			queryString.append("CASE WHEN TRIM(d.numero_interior) IS NULL THEN NULL ELSE ' ,No EXT: '||TRIM(d.numero_interior) END|| ");
			queryString.append("CASE WHEN TRIM(d.numero_exterior) IS NULL THEN NULL ELSE ' ,No INT: '||TRIM(d.numero_exterior) END|| ");
			queryString.append("CASE WHEN TRIM(d.entre_calle) IS NULL THEN NULL ELSE ' ,ENTRE CALLE: '||TRIM(d.entre_calle) END|| ");
			queryString.append("CASE WHEN TRIM(d.y_calle) IS NULL THEN NULL ELSE ' ,Y CALLE: '||TRIM(d.y_calle) END AS domicilio, ");
			queryString.append("cp.colonia,d.codigo_postal,F_DESC_CATALOGO(25, d.id_entidad) entidad_federativa,m.municipio,F_DESC_CATALOGO(41, t.id_tipo_telefono) tipo_telefono, ");
			queryString.append("CASE WHEN TRIM(t.acceso) IS NULL THEN NULL ELSE ' '||TRIM(t.acceso) END|| ");
			queryString.append("CASE WHEN TRIM(t.clave) IS NULL THEN NULL ELSE ' '||TRIM(t.clave) END|| ");
			queryString.append("CASE WHEN TRIM(t.telefono) IS NULL THEN NULL ELSE ' '||TRIM(t.telefono) END AS telefono,F_DESC_CATALOGO(4, c.id_medio_portal) medio_contacto, ");
			queryString.append("F_DESC_CATALOGO(25, c.id_entidad_nacimiento) entidad_nacimiento,c.fecha_nacimiento, ");
			queryString.append("c.confidencialidad_datos,F_DESC_CATALOGO(8, ga.id_nivel_estudio) grado_academico, ");
			queryString.append("(SELECT op.opcion FROM catalogo_opcion op WHERE op.id_catalogo_opcion = ga.id_carrera_especialidad) AS especialidad, ");
			queryString.append("F_DESC_CATALOGO(10, ga.id_situacion_academica) situacion_academica, ");
			queryString.append("F_DESC_CATALOGO(14, pl.id_experiencia_total) experiencia, ");
			queryString.append("F_DESC_CATALOGO(20, ex.id_area_laboral_deseada) area_laboral_deseada, ");
			queryString.append("F_DESC_CATALOGO(15, ex.id_tipo_empleo_deseado) tipo_empleo, ");
			queryString.append("ex.salario_pretendido,F_DESC_CATALOGO(18, pl.id_razon_busqueda) por_que_busca_empleo ");

			queryString.append("FROM CANDIDATO_INF c, ");
			queryString.append("perfil_laboral pl, ");
			queryString.append("Domicilio d, ");
			queryString.append("codigo_postal cp, ");
			queryString.append("municipio m, ");
			queryString.append("telefono t, ");
			queryString.append("GRADO, ");
			queryString.append("candidato_grado_academico ga, ");
			queryString.append("expectativa_laboral ex ");
			queryString.append("WHERE (C.nss IS NOT NULL OR NVL(C.credito_infonavit,0) > 0) ");
			queryString.append("AND C.fecha_alta BETWEEN TO_DATE(SYSDATE-" + DIAS_REPORTE_INFONAVIT +") AND TO_DATE(SYSDATE) ");
			queryString.append("AND pl.id_candidato = c.id_candidato ");
			queryString.append("AND d.id_propietario(+)      = c.id_candidato ");
			queryString.append("AND d.id_tipo_propietario(+) = 2 ");
   			queryString.append("AND cp.ID_ENTIDAD (+)=   d.ID_ENTIDAD ");
			queryString.append("AND cp.ID_MUNICIPIO(+) = d.ID_MUNICIPIO ");
			queryString.append("AND cp.ID_COLONIA(+) =   d.ID_COLONIA ");
   			queryString.append("AND m.id_municipio(+) = d.id_municipio ");
			queryString.append("AND m.ID_ENTIDAD(+)   = d.ID_ENTIDAD ");
			queryString.append("AND t.id_propietario(+) = c.id_candidato ");
   
			queryString.append("AND t.id_tipo_propietario(+) = 2 ");
			queryString.append("AND t.principal(+) = 1 ");

			queryString.append("AND GRADO.id_candidato(+) = c.id_candidato ");
			queryString.append("AND ga.id_candidato_grado_academico(+) = GRADO.id_candidato_grado_academico ");
			queryString.append("AND ga.id_candidato(+)     = GRADO.id_candidato ");
			queryString.append("AND ga.principal(+) = 1 ");
      
			queryString.append("and ex.id_candidato(+) = c.id_candidato ");
			queryString.append("AND ex.principal(+) = 1 ");
				
			
			Query query = entityManager.createNativeQuery(queryString.toString());
			
			List<Object[]> objetosList = query.getResultList();
			
			for(Object[] result : objetosList){
				CandidatoVo vo = getCandidatoVoINFONAVIT(result);
				candidatoVoList.add(vo);
			}
			
		}catch(NoResultException re){
			logger.error("No se encontraron candidatos con numero de seguro social o credito infonavit");
			candidatoVoList = null;
		}catch(NullPointerException enull){
			logger.error("Error de NullPointerException");
			logger.error(enull);
			candidatoVoList = null;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new PersistenceException(e);
		}
		
		return candidatoVoList;
	}
	
	private CandidatoVo getCandidatoVoINFONAVIT(Object[] objectArray){
		CandidatoVo candidatoVo = new CandidatoVo();
		DomicilioVO domicilioVo = new DomicilioVO();
		TelefonoVo telefonoVo = new TelefonoVo();
		GradoAcademicoVO gradoAcademicoVO = new GradoAcademicoVO();
		
		candidatoVo.setNss(Utils.toString(objectArray[0]) );
		candidatoVo.setCreditoInfonavit(Utils.toLong(objectArray[1]));
		candidatoVo.setNombre(Utils.toString(objectArray[2]));
		candidatoVo.setApellido1(Utils.toString(objectArray[3]));
		candidatoVo.setApellido2(Utils.toString(objectArray[4]));
		candidatoVo.setCorreoElectronico((String)objectArray[5]);
		
		domicilioVo.setDomiciloCompletoString(Utils.toString(objectArray[6]));
		domicilioVo.setColonia(Utils.toString(objectArray[7]));
		domicilioVo.setCodigoPostal(Utils.toString(objectArray[8]));
		domicilioVo.setEntidadFederativaString(Utils.toString(objectArray[9]));
		domicilioVo.setMunicipio(Utils.toString(objectArray[10]));	
		candidatoVo.setDomicilioVo(domicilioVo);
		
		telefonoVo.setTipoTelefonoString(Utils.toString(objectArray[11]));
		telefonoVo.setTelefono(Utils.toString(objectArray[12]));
		candidatoVo.setTelefonoVo(telefonoVo);
		
		candidatoVo.setMedioContacto(Utils.toString(objectArray[13]));
		candidatoVo.setEntidadNacimiento(Utils.toString(objectArray[14]));
		candidatoVo.setFechaNacimientoString(Utils.getFechaFormato((Date) objectArray[15]));
		candidatoVo.setConfidencialidadDatos(Utils.toInt(objectArray[16]));
		
		gradoAcademicoVO.setGradoAcademico(Utils.toString(objectArray[17]));
		gradoAcademicoVO.setEspecialidad(Utils.toString(objectArray[18]));
		gradoAcademicoVO.setSituacionAcademicaString(Utils.toString(objectArray[19]));
		gradoAcademicoVO.setExperienciaString(Utils.toString(objectArray[20]));
		gradoAcademicoVO.setAreaLaboralString(Utils.toString(objectArray[21]));
		gradoAcademicoVO.setTipoEmpleoString(Utils.toString(objectArray[22]));
		gradoAcademicoVO.setSalarioPretendido(Utils.toLong(objectArray[23]));
		gradoAcademicoVO.setPorqueBuscaEmpleo(Utils.toString(objectArray[24]));
		
		candidatoVo.setGradoacademicoVO(gradoAcademicoVO);
		
		return candidatoVo;
		
	}
	
	public CandidatoVo findById(long idCandidato) throws PersistenceException{		
		CandidatoVo vo = null;
		
		try {
			Candidato entity = entityManager.find(Candidato.class, idCandidato);
			if (null != entity)
				vo = getCandidatoVo(entity);
		}catch (RuntimeException re) {
			logger.error(re.toString());
			throw new PersistenceException(re);
		}
		return vo;		
	}

	public long registraCandidatoPublicidad(String curp, String cookie, String parametro, int idTipoIngreso){
		Date fechaAlta = Calendar.getInstance().getTime();		

		RegistroPublicidad entity = new RegistroPublicidad();
		entity.setCurp(curp);
		entity.setCookie(cookie);
		entity.setParametro(parametro);
		entity.setFechaAlta(fechaAlta);
		entity.setIdTipoIngreso(Utils.toLong(idTipoIngreso));
		
		entityManager.persist(entity);
		
		long idRegistroPublicidad = entity.getIdRegistroPublicidad();
		
		return idRegistroPublicidad;
	}
	
	public void actualizaFechaConfirma(long idCandidato, Date fechaConfirma)
			throws PersistenceException {
		try{
			Candidato candidato = entityManager.find(Candidato.class, idCandidato);
			if (candidato!=null){
				candidato.setFechaConfirma(fechaConfirma);
			    entityManager.merge(candidato);
				entityManager.flush();				
			}
		} catch (NoResultException re) {
			logger.error("No se encontraron candidatos con numero de id:" + idCandidato);
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}				
	}
	
	public void actualizaFechaUltimaActualizacion(long idCandidato) throws PersistenceException {
		try{
			Candidato candidato = entityManager.find(Candidato.class, idCandidato);
			if (candidato!=null){
				Calendar fecha = Calendar.getInstance();
				candidato.setFechaUltimaActualizacion(fecha.getTime());
			    entityManager.merge(candidato);
				entityManager.flush();				
			}
		} catch (NoResultException re) {
			// Sin registro
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}			
	}
	
	public void actualizaFechaUltimoAcceso(long idCandidato) throws PersistenceException {
		try{
			Candidato candidato = entityManager.find(Candidato.class, idCandidato);
			if (candidato!=null){
				Calendar fecha = Calendar.getInstance();
				candidato.setFechaUltimoAcceso(fecha.getTime());
			    entityManager.merge(candidato);
				entityManager.flush();				
			}
		} catch (NoResultException re) {
			// Sin registro
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}			
	}	

	public void actualizaApoyoProspera(long idCandidato, int apoyoProspera, String folioProspera, String folioIntegranteProspera) {
		try{
			Candidato candidato = entityManager.find(Candidato.class, idCandidato);
			if (candidato!=null){
				candidato.setApoyoProspera(apoyoProspera);
				candidato.setFolioProspera(folioProspera);
				candidato.setFolioIntegranteProspera(folioIntegranteProspera);
			    entityManager.merge(candidato);
				entityManager.flush();				
			}
		} catch (NoResultException re) {
			// Sin registro
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}
		
	}
	/* para borrar
	public void actualizaOportunidades(long idCandidato, int oportunidades) {
		try{
			Candidato candidato = entityManager.find(Candidato.class, idCandidato);
			if (candidato!=null){
				//candidato.setApoyoOrtunidades(oportunidades);
			    entityManager.merge(candidato);
				entityManager.flush();				
			}
		} catch (NoResultException re) {
			// Sin registro
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}
		
	}		
*/
	public List<Candidate> consultaCandidatoAIndexar(long idCandidato) throws SQLException {
		List<Long> idsCandidato = new ArrayList<Long>();
		idsCandidato.add(idCandidato);
		return consultaCandidatoAIndexar(idsCandidato);
	}
	
	public List<Candidate> consultaCandidatoAIndexar(List<Long> idsCandidato) throws SQLException {
		List<Candidate> candidatos = new ArrayList<Candidate>();
		
		StringBuilder buf = new StringBuilder();
		for (long idCandidato : idsCandidato){
			buf.append(idCandidato +",");
		}

		String ins = buf.toString();

		if (ins.endsWith(","))
			ins = ins.substring(0, ins.length()-1);

		String select = createQuery(ins);
		
		Query query = entityManager.createNativeQuery(select);

		@SuppressWarnings("unchecked")
		List<Object[]> cachedRowSet = query.getResultList();

        try {
        	long idCandidatoAnterior = 0;
        	long idCandidatoActual = 0;

        	Candidate candidato = null;
        	List<Long> conocimientosHab = null;
        	
        	for(Object[] result : cachedRowSet){

            	idCandidatoActual 			         = Utils.toLong		(result[0]); //cachedRowSet.getLong  ("ID_CANDIDATO");

            	long idCandidatoConocimHabilidad 	 = Utils.toLong		(result[14]); //cachedRowSet.getLong  ("ID_CANDIDATO_CONOCIM_HABILIDAD");
            	String conocimientoHabilidad 	     = Utils.toString	(result[15]); //cachedRowSet.getString("CONOCIMIENTO_HABILIDAD");
            	long idExperiencia 				     = Utils.toLong		(result[16]); //cachedRowSet.getLong  ("ID_EXPERIENCIA");
                int idTipoConocimientoHabilidad      = Utils.toInt		(result[17]); //cachedRowSet.getInt   ("ID_TIPO_CONOCIM_HABILIDAD");

                if (idCandidatoActual != idCandidatoAnterior){
            		candidato = new Candidate();
            		candidatos.add(candidato);
            		
            		conocimientosHab = new ArrayList<Long>();
            		
                	Date fechaNacimiento 			 = Utils.toDate		(result[1]); //cachedRowSet.getDate  ("FECHA_NACIMIENTO");
                    long disponibilidadViajar 		 = Utils.toLong		(result[2]); //cachedRowSet.getLong  ("DISPONIBILIDAD_VIAJAR");
                    long disponibilidadRadicar 		 = Utils.toLong		(result[3]); //cachedRowSet.getLong  ("DISPONIBILIDAD_RADICAR");
                    int experiencia 				 = Utils.toInt		(result[4]); //cachedRowSet.getInt   ("EXPERIENCIA");
                    int indicadorEstudios 			 = Utils.toInt		(result[5]); //cachedRowSet.getInt   ("INDICADOR_ESTUDIOS");
                    int ocupacion 					 = Utils.toInt		(result[6]); //cachedRowSet.getInt   ("OCUPACION");
                    long salario 					 = Utils.toLong		(result[7]); //cachedRowSet.getLong  ("SALARIO");
                    int tipoEmpleo 				 	 = Utils.toInt		(result[8]); //cachedRowSet.getInt   ("TIPO_EMPLEO");
                    int idEntidad 					 = Utils.toInt		(result[9]); //cachedRowSet.getInt   ("ID_ENTIDAD");
                    String municipio 				 = Utils.toString	(result[10]); //cachedRowSet.getString("MUNICIPIO");
                    long idCarreraEspecialidad 		 = Utils.toLong		(result[11]); //cachedRowSet.getLong  ("ID_CARRERA_ESPECIALIDAD");
                    long idNivelEstudio 			 = Utils.toLong		(result[12]); //cachedRowSet.getLong  ("ID_NIVEL_ESTUDIO");
                    long idSituacionAcademica 		 = Utils.toLong		(result[13]); //cachedRowSet.getLong  ("ID_SITUACION_ACADEMICA");

                    if (experiencia<=0) experiencia = EXPERIENCIA_LABORAL.CON_EXPERIENCIA.getIdOpcion(); // valor por defecto
                    if (tipoEmpleo <=0) tipoEmpleo  = (int)TIPO_EMPLEO.TIEMPO_COMPLETO.getIdOpcion();
                    
                    String entidadDesc = "";
                    ENTIDADES_FEDERATIVAS entidad = ENTIDADES_FEDERATIVAS.getEntidad(idEntidad);
                    if (entidad!=null) entidadDesc = entidad.getDescripcion();
                    if (municipio==null) municipio = "";

                    String palabras = (entidadDesc + " "+ municipio).trim();

                    candidato.setIdCandidato                  (idCandidatoActual);
                    candidato.setEdad						  (obtenEdad(fechaNacimiento));
                    candidato.setDisponibilidadRadicar		  (disponibilidadRadicar == DISPONIBILIDAD_RADICAR.SI.getIdOpcion());
                    candidato.setDisponibilidad_viajar_ciudad (disponibilidadViajar  == DISPONIBILIDAD_VIAJAR.SI.getIdOpcion());
                    candidato.setExperiencia				  (experiencia);
                    candidato.setIndicador_estudios			  (indicadorEstudios == ESCOLARIDAD.CON_ESTUDIOS.getIdOpcion());
                    candidato.setOcupacion					  (ocupacion);
                    candidato.setSalario					  (salario);
                    candidato.setTipoEmpleo					  (tipoEmpleo);
                    candidato.setPalabras					  (palabras);

                    if (idCarreraEspecialidad>0){
                        GradoAcademicoVO gradoAcademico = new GradoAcademicoVO();
                        gradoAcademico.setIdCarreraEspecialidad(idCarreraEspecialidad);
                        gradoAcademico.setIdNivelEstudio(idNivelEstudio);
                        gradoAcademico.setIdSituacionAcademica(idSituacionAcademica);

                        candidato.setGradoAcademico(gradoAcademico);
                    }
            	}

                if (candidato!=null && conocimientosHab!=null && conocimientoHabilidad!=null 
                		&& !conocimientoHabilidad.isEmpty() && !"[Indica el conocimiento]".equals(conocimientoHabilidad)){
                	
                    ConocimientoHabilidadVO conocimiento = new ConocimientoHabilidadVO();
                    conocimiento.setIdCandidatoConocimHabilidad(idCandidatoConocimHabilidad);
                    conocimiento.setConocimientoHabilidad(conocimientoHabilidad);
                    conocimiento.setIdExperiencia(idExperiencia);

                    // Se verifica que no se dupliquen los conocimientos o habilidades
                    if (!conocimientosHab.contains(idCandidatoConocimHabilidad)){
                    	conocimientosHab.add(idCandidatoConocimHabilidad);

                        if (idTipoConocimientoHabilidad == CONOC_HAB.CONOCIMIENTO.getIdOpcion()){
                        	candidato.addConocimiento(conocimiento);
                        } else if (idTipoConocimientoHabilidad == CONOC_HAB.HABILIDAD.getIdOpcion()){
                        	candidato.addHabilidade(conocimiento);
                        }
                    }
                }
                
                idCandidatoAnterior = idCandidatoActual;
            }

        } catch (Exception e) {
        	e.printStackTrace(); logger.error(e);
            throw new SQLException(e);
        }

        return candidatos;
    }

	/** Consulta obtenida de la clase CandidatoIndexadorDAO **/
	public String createQuery(String ins) {
		StringBuilder query = new StringBuilder();

		query.append("SELECT CAN.ID_CANDIDATO, ");
		query.append("       SOL.FECHA_NACIMIENTO, ");
		query.append("       PERF.DISPONIBILIDAD_VIAJAR, ");
		query.append("       PERF.DISPONIBILIDAD_RADICAR, ");
		query.append("       PERF.ID_EXPERIENCIA_TOTAL AS EXPERIENCIA, ");
		query.append("       PERF.SIN_ESTUDIOS AS INDICADOR_ESTUDIOS, ");
		query.append("       EX.ID_OCUPACION_DESEADA AS OCUPACION, ");
		query.append("       EX.SALARIO_PRETENDIDO AS SALARIO, ");
		query.append("       EX.ID_TIPO_EMPLEO_DESEADO AS TIPO_EMPLEO, ");
		query.append("       DOM.ID_ENTIDAD, ");
		query.append("       MUN.MUNICIPIO, ");
		query.append("       GRAD.ID_CARRERA_ESPECIALIDAD, ");
		query.append("       GRAD.ID_NIVEL_ESTUDIO, ");
		query.append("       GRAD.ID_SITUACION_ACADEMICA, ");
		query.append("       HAB.ID_CANDIDATO_CONOCIM_HABILIDAD, ");
		query.append("       HAB.CONOCIMIENTO_HABILIDAD, ");
		query.append("       HAB.ID_EXPERIENCIA, ");
		query.append("       HAB.ID_TIPO_CONOCIM_HABILIDAD ");
		query.append("  FROM CANDIDATO CAN, ");
        query.append("       SOLICITANTE SOL, ");
		query.append("       PERFIL_LABORAL PERF, ");
		query.append("       EXPECTATIVA_LABORAL EX, ");
		query.append("       DOMICILIO DOM, ");
		query.append("       MUNICIPIO MUN, ");
		query.append("       CANDIDATO_GRADO_ACADEMICO GRAD, ");
		query.append("       CANDIDATO_CONOCIM_HABILIDAD HAB ");
		query.append(" WHERE CAN.ID_CANDIDATO   IN ("+ ins +") ");
        query.append("   AND SOL.ID_CANDIDATO = CAN.ID_CANDIDATO");
		query.append("   AND PERF.ID_CANDIDATO  = CAN.ID_CANDIDATO ");
		query.append("   AND EX.ID_CANDIDATO(+) = CAN.ID_CANDIDATO ");
		query.append("   AND EX.PRINCIPAL   (+) = "+ MULTIREGISTRO.PRINCIPAL.getIdOpcion() +" ");
		query.append("   AND DOM.ID_PROPIETARIO     (+) = CAN.ID_CANDIDATO ");
		query.append("   AND DOM.ID_TIPO_PROPIETARIO(+) = "+ TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario() +" ");
		query.append("   AND MUN.ID_ENTIDAD   (+) = DOM.ID_ENTIDAD ");
		query.append("   AND MUN.ID_MUNICIPIO (+) = DOM.ID_MUNICIPIO ");
		query.append("   AND GRAD.ID_CANDIDATO(+) = CAN.ID_CANDIDATO ");
		query.append("   AND GRAD.PRINCIPAL   (+) = "+ MULTIREGISTRO.PRINCIPAL.getIdOpcion() +" ");
		query.append("   AND HAB.ID_CANDIDATO (+) = CAN.ID_CANDIDATO ");
		query.append(" ORDER BY CAN.ID_CANDIDATO ");
		
		return query.toString();
	}

	public int candidatesToIndex() {
		int candidates = 0;

		try {
			Query query = entityManager.createNativeQuery("SELECT count(id_candidato) FROM CANDIDATO WHERE estatus=1");
			Object result = query.getSingleResult();			

			if (result!=null)
				candidates = Utils.toInt(result);

		} catch (NoResultException re) {
			// No se localizaron registros
		} catch(Exception e){
			e.printStackTrace();
			logger.error(e);
		}

		return candidates;
	}

	@SuppressWarnings("unchecked")
	public List<Long> candidates(int lowerLimit, int upperLimit) {
		List<Long> ids = new ArrayList<Long>();

		try {
			String select = "SELECT id_candidato " +
					          "FROM (SELECT ROWNUM r, id_candidato FROM CANDIDATO WHERE estatus=1) " +
					         "WHERE r > "+ lowerLimit +" AND r <= "+ upperLimit;
			
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
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ResultadoBusquedaCandidatosVO> busquedaEspecificaCandidatos(BusquedaCandidatosVO vo) throws SQLException {
		StringBuilder select = null;
		StringBuilder from = null;
		StringBuilder where = null;
		String idOcupaciones = "";
		String idMunicipios = "";
		String idCarreras = "";
		String idIdiomas = "";
		String idHabilidades = "";
		List<ResultadoBusquedaCandidatosVO> resultado = null;
		try{
			select = new StringBuilder();
			from = new StringBuilder();
			where = new StringBuilder();
			
//			select.append("WITH canditato_index as (select id_candidato, (nombre || ' ' || apellido1 || ' ' || apellido2) as nombre, genero, edad from candidato where estatus = 1),");
//			select.append("domicilio_index as(select * from domicilio where domicilio.id_tipo_propietario = 2)");
			
			select.append("select c.id_candidato, (s.nombre || ' ' || s.apellido1 || ' ' || s.apellido2) as nombre");
			select.append(",(f_desc_catalogo(8,id_nivel_estudio) || ' - ' || f_desc_catalogo(43,id_carrera_especialidad)) as estudios");
			select.append(",s.edad ,(f_desc_catalogo(25,d.id_entidad)||' - '||m.municipio) as entidad_federativa");
			select.append(",f_desc_catalogo(17,e.id_sector_deseado) as ocuapacion_deseada ,e.salario_pretendido");
			select.append(",f_desc_catalogo(24,e.id_tipo_contrato) as tipo_contrato ,f_desc_catalogo(15,e.id_tipo_empleo_deseado) as tipo_empleo");
			select.append(",CASE p.disponibilidad_viajar WHEN 2 THEN 'Si' ELSE 'No' END as viajar");
			select.append(",CASE p.disponibilidad_radicar WHEN 2 THEN 'Si' ELSE 'No' END as radicar  ");
			
			from.append("from candidato c, solicitante s, candidato_grado_academico g, domicilio d, municipio m, expectativa_laboral e, perfil_laboral p");
			
			if (vo.getIdiomaUno() != 0 || vo.getIdiomaDos() != 0 || vo.getIdiomaTres() != 0)
				from.append(",candidato_idioma i  ");
			
			if (vo.getHabilidadUno() != 0 || vo.getHabilidadDos() != 0 || vo.getHabilidadTres() != 0)
				from.append(",candidato_conocim_habilidad h  ");
			if (null != vo.getIdSubprograma() && vo.getIdSubprograma() > 0)
				from.append(", modalidad_candidato mc  ");

			where.append(" where  c.id_candidato = s.id_candidato ");
			where.append(" and c.id_candidato = g.id_candidato ");
			where.append(" and g.principal = 1 ");
			where.append(" and c.id_candidato = d.id_propietario ");
			where.append(" and d.id_tipo_propietario = 2 ");
			where.append(" and d.id_entidad = m.id_entidad ");
			where.append(" and d.id_municipio = m.id_municipio ");
			where.append(" and c.id_candidato = e.id_candidato ");
			where.append(" and e.principal = 1 ");
			where.append(" and c.id_candidato = p.id_candidato ");
			
			if(vo.getIdOcupacionDeseadaUno() != 0){
				idOcupaciones += vo.getIdOcupacionDeseadaUno().toString();
				if(vo.getIdOcupacionDeseadaDos() != 0 || vo.getIdOcupacionDeseadaTres() != 0)
					idOcupaciones += ",";
				
			}
			
			if(vo.getIdOcupacionDeseadaDos() != 0){
				idOcupaciones += vo.getIdOcupacionDeseadaDos().toString();
				if(vo.getIdOcupacionDeseadaTres() != 0)
					idOcupaciones += ",";
			}
			
			if(vo.getIdOcupacionDeseadaTres() != 0){
				idOcupaciones += vo.getIdOcupacionDeseadaTres().toString();
			}
			
			if(vo.getIdOcupacionDeseadaUno() != 0 || vo.getIdOcupacionDeseadaDos() != 0 || vo.getIdOcupacionDeseadaTres() != 0)
				where.append(" and e.id_ocupacion_deseada in ("+ idOcupaciones +")");
			
			if(vo.getIdEntidadSelect() != 0)
				where.append(" and d.id_entidad = " + vo.getIdEntidadSelect());
			
			if(vo.getMunicipioUno() != 0){
				idMunicipios += vo.getMunicipioUno().toString();
				if(vo.getMunicipioDos() != 0 || vo.getMunicipioTres() != 0)
					idMunicipios += ",";
			}
			
			if(vo.getMunicipioDos() != 0){
				idMunicipios += vo.getMunicipioDos().toString();
				if(vo.getMunicipioTres() != 0)
					idMunicipios += ",";
			}
			
			if(vo.getMunicipioTres() != 0){
				idMunicipios += vo.getMunicipioTres().toString();
			}
			
			if(vo.getMunicipioUno() != 0 || vo.getMunicipioDos() != 0 || vo.getMunicipioTres() != 0)
				where.append(" and d.id_municipio in ("+ idMunicipios + ")");
			if (null != vo.getGenero()) {
				if (vo.getGenero() != 0)
					where.append(" and s.genero = " + vo.getGenero());
				else if(vo.getGenero() == 0)
					where.append(" and s.genero in (1,2) ");
			}
			if (null != vo.getEdadMinima() && !vo.getEdadMinima().trim().isEmpty() && !vo.getEdadMaxima().trim().isEmpty())
				where.append(" and s.edad between " + vo.getEdadMinima() + "  and " + vo.getEdadMaxima());
			
			if (null != vo.getEdadDe() && !vo.getEdadDe().trim().isEmpty() && null != vo.getEdadA() && !vo.getEdadA().trim().isEmpty())
				where.append(" and e.salario_pretendido between " + vo.getEdadDe() + " and " + vo.getEdadA());
			
			if(vo.getIdGradoEstudioSelect() != 0)
				where.append(" and g.id_nivel_estudio = " + vo.getIdGradoEstudioSelect());
			
			if(vo.getCarreraUno() != 0){
				idCarreras += vo.getCarreraUno().toString();
				if(vo.getCarreraDos() != 0 || vo.getCarreraTres() != 0)
					idCarreras += ",";
			}
			
			if(vo.getCarreraDos() != 0){
				idCarreras += vo.getCarreraUno().toString();
				if(vo.getCarreraTres() != 0)
					idCarreras += ",";
			}
			
			if(vo.getCarreraTres() != 0){
				idCarreras += vo.getCarreraTres().toString();
			}
			
			if(vo.getCarreraUno() != 0 || vo.getCarreraDos() != 0 || vo.getCarreraTres() != 0)
				where.append(" and g.id_carrera_especialidad in ("+ idCarreras +")");
			
			if(vo.getIdiomaUno() != 0 || vo.getIdiomaDos() != 0 || vo.getIdiomaTres() != 0){
				where.append(" and c.id_candidato = i.id_candidato");
				
				if(vo.getIdiomaUno() != 0){
					idIdiomas += vo.getIdiomaUno().toString();
					if(vo.getIdiomaDos() != 0 || vo.getIdiomaTres() != 0)
						idIdiomas += ",";
				}
				
				if(vo.getIdiomaDos() != 0){
					idIdiomas += vo.getIdiomaDos().toString();
					if(vo.getIdiomaTres() != 0)
						idIdiomas += ",";
				}
				
				if(vo.getIdiomaTres() != 0){
					idIdiomas += vo.getIdiomaTres().toString();
				}
				
				where.append(" and i.id_idioma in ("+ idIdiomas +")");
			}
			
			if(vo.getDisponibilidadViajar() != 0)
				where.append(" and p.disponibilidad_viajar = " + vo.getDisponibilidadViajar());
			
			if(vo.getCambiarResidencia() != 0)
				where.append(" and p.disponibilidad_radicar = " + vo.getCambiarResidencia());
			
			if(vo.getHabilidadUno() != 0 || vo.getHabilidadDos() != 0 || vo.getHabilidadTres() != 0){
				where.append(" and c.id_candidato = h.id_candidato");
				
				if(vo.getHabilidadUno() != 0){
					idHabilidades += vo.getHabilidadUno().toString();
					if(vo.getHabilidadDos() != 0 || vo.getHabilidadTres() != 0)
						idHabilidades += ",";
				}
				
				if(vo.getHabilidadDos() != 0){
					idHabilidades += vo.getHabilidadDos().toString();
					if(vo.getHabilidadTres() != 0)
						idHabilidades += ",";
				}
				
				if(vo.getHabilidadTres() != 0){
					idHabilidades += vo.getHabilidadTres().toString();
				}
				where.append(" and h.id_tipo_conocim_habilidad in ("+ idHabilidades +")");
			}
			
			if (null != vo.getIdSubprograma() && vo.getIdSubprograma() > 0)
				where.append(" and c.id_candidato = mc.id_candidato and mc.id_subprograma = "+ vo.getIdSubprograma());
			if (null != vo.getIdAreaLaboral() && vo.getIdAreaLaboral() > 0)
				where.append(" and e.id_area_laboral_deseada = " + vo.getIdAreaLaboral());
			if (null != vo.getIdSubAreaLaboral() && vo.getIdSubAreaLaboral() > 0)
				where.append(" and e.id_sub_area_laboral_deseada = " + vo.getIdSubAreaLaboral());
			
			//Ordenamiento de columnas
			if(vo.getTipoOrdenamiento() != null){
				if(vo.getTipoOrdenamiento().equals("asc")){
					if(vo.getNumeroColumna() == 1)
						where.append(" order by nombre asc ");
					else if(vo.getNumeroColumna() == 2)
						where.append(" order by estudios asc ");
					else if(vo.getNumeroColumna() == 3)
						where.append(" order by edad asc ");
					else if(vo.getNumeroColumna() == 4)
						where.append(" order by entidad_federativa asc ");
				}else if(vo.getTipoOrdenamiento().equals("desc")){
					if(vo.getNumeroColumna() == 1)
						where.append(" order by nombre desc ");
					else if(vo.getNumeroColumna() == 2)
						where.append(" order by estudios desc ");
					else if(vo.getNumeroColumna() == 3)
						where.append(" order by edad desc ");
					else if(vo.getNumeroColumna() == 4)
						where.append(" order by entidad_federativa desc ");
				}
			}
			select.append(from);
			select.append(where);
			Query query = entityManager.createNativeQuery(select.toString());
			@SuppressWarnings("unchecked")
			List<Object[]> rowSet = query.getResultList();
			
			logger.info("Candidatos encontrados: " + rowSet.size());
			
			logger.info("Inicio: " + new Date());
			resultado = new ArrayList<ResultadoBusquedaCandidatosVO>();
			ResultadoBusquedaCandidatosVO candidatoVo = null;
			for(Object[] arreglo : rowSet){
				candidatoVo = new ResultadoBusquedaCandidatosVO();
				candidatoVo.setIdCandidato(Utils.toLong(arreglo[0]));
				candidatoVo.setNombreCompleto(Utils.toString(arreglo[1]));
				candidatoVo.setEstudios(Utils.toString(arreglo[2]));
				candidatoVo.setEdad(Utils.toString(arreglo[3]));
				candidatoVo.setEntidadFederativa(Utils.toString(arreglo[4]));
				candidatoVo.setOcupacionDeseada(Utils.toString(arreglo[5]));
				candidatoVo.setSalarioPretendido(Utils.toString(arreglo[6]));
				candidatoVo.setTipoContrato(Utils.toString(arreglo[7]));
				candidatoVo.setTipoEmpleo(Utils.toString(arreglo[8]));
				candidatoVo.setViajar(Utils.toString(arreglo[9]));
				candidatoVo.setRadicar(Utils.toString(arreglo[10]));
				
				resultado.add(candidatoVo);
			}
			logger.info("Final: " + new Date());
			
			
		}catch(NoResultException re){
			logger.error(re);
		}catch(Exception e){
			logger.error(e);
		}
		return resultado;
	}
	
	@Override
	public List<CandidatoVo> buscarCandidatosQuery(List<Long> index) {
	
		
		if (index==null || index.isEmpty()) return new ArrayList<CandidatoVo>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select can.id_candidato, dom.id_entidad, sol.nombre, sol.apellido1, sol.apellido2, "); 
		sql.append(" DOM.ID_ENTIDAD, MUN.MUNICIPIO, CATOPC.OPCION OCUPACION_CANDIDATO ");
		sql.append(" from candidato can ");
		sql.append(" inner join solicitante  sol  ");
		sql.append(" on sol.id_candidato = can.id_candidato and sol.estatus = 1 ");
		sql.append(" inner join domicilio dom ");
		sql.append(" on dom.id_propietario = can.id_candidato  and dom.id_tipo_propietario = 2 ");
		sql.append(" inner join MUNICIPIO MUN  ");
		sql.append(" on  DOM.ID_ENTIDAD = MUN.ID_ENTIDAD and DOM.ID_MUNICIPIO = MUN.ID_MUNICIPIO ");
		sql.append(" inner join expectativa_laboral exl ");
		sql.append(" on exl.principal = 1 and exl.id_Candidato = can.id_candidato ");
		sql.append(" inner join CATALOGO_OPCION CATOPC ");
		sql.append(" on CATOPC.id_catalogo = 21 and  CATOPC.ID_CATALOGO_OPCION = EXL.ID_OCUPACION_DESEADA ");
		sql.append(" and can.id_candidato in (");
		StringBuilder str = new StringBuilder();
		for(Long par :index){
			if(str.length()>0){
				str.append(", ");
			}
			
			str.append("?");
		}
		sql.append(str.toString());
		sql.append(") ");
		sql.append(" order by nombre,apellido1,apellido2 ");
		
		javax.persistence.Query query = entityManager.createNativeQuery(sql.toString());
		for(int i = 1; i<= index.size();i++)
		{
			query.setParameter(i, index.get(i-1));
		}
		
		@SuppressWarnings("unchecked")
		List<Object[]> rows = query.getResultList();
		List<CandidatoVo> listCandidatos = new ArrayList<CandidatoVo>();
		for (Object[] rowElement : rows) {
			CandidatoVo vo = getCandidatoVo(rowElement);
			listCandidatos.add(vo);
		}
		
		
		return listCandidatos; 
	}


	private CandidatoVo getCandidatoVo(Object[] row) {
		CandidatoVo vo = new CandidatoVo();
		
		/*
		 * can.id_candidato, dom.id_entidad, sol.nombre, sol.apellido1, sol.apellido2,
DOM.ID_ENTIDAD, MUN.MUNICIPIO, CATOPC.OPCION OCUPACION_CANDIDATO
		 */

		vo.setIdCandidato(Utils.toInt(row[0]));
		vo.setEstadoEntidadString(Catalogos.ENTIDADES_FEDERATIVAS.getDescripcion((Utils.toInt(row[1])))+ ", " +Utils.toString(row[6]));
		DomicilioVO domicilioVo = new DomicilioVO();
		domicilioVo.setEntidadFederativaString(vo.getEstadoEntidadString());
		domicilioVo.setEntidad(Catalogos.ENTIDADES_FEDERATIVAS.getDescripcion((Utils.toInt(row[1]))));
		domicilioVo.setMunicipio(Utils.toString(row[6]));
		vo.setDomicilioVo(domicilioVo);
		vo.setNombre(Utils.toString(row[2]));
		vo.setApellido1(Utils.toString(row[3]));
		vo.setApellido2(Utils.toString(row[4]));
		vo.setOcupacion(Utils.toString(row[7]));
		
		return vo;
	}

	@Override
    public CandidatoVo findPpcSdTermsAndConditionsData(final long idUsuario) {
        CandidatoVo candidatoVo = new CandidatoVo();
        DomicilioVO domicilioVo = new DomicilioVO();
        candidatoVo.setDomicilioVo(domicilioVo);

        // ===== BEGIN SQL COMMAND ===== //

        StringBuilder sqlCommand = new StringBuilder();
        StringBuilder select = new StringBuilder();
        StringBuilder from = new StringBuilder();
        StringBuilder where = new StringBuilder();

        sqlCommand.append("WITH Entidades AS (SELECT * FROM Catalogo_Opcion WHERE id_catalogo = ").append(CATALOGO_OPCION_ENTIDAD_FEDERATIVA).append(")");

        select.append("SELECT ");
        select.append("     can.id_candidato, "); // [0] ID Candidato
        select.append("     sol.nombre, "); // [1] Nombre
        select.append("     sol.apellido1, "); // [2] Apellido1
        select.append("     sol.apellido2, "); // [3] Apellido2
        select.append("     sol.curp, "); // [4] CURP
        select.append("     can.nss, "); // [5] NSS
        select.append("     can.ppc_fecha_inscripcion, "); // [6] Fecha Inscripcion PPC
        select.append("     mun.municipio, "); // [7] Municipio
        select.append("     ent.opcion as entidad "); // [8] Entidad Federativa

        from.append("FROM ");
        from.append("   Usuario u");    // Table: USUARIO
        from.append("   INNER JOIN Candidato can ON u.id_usuario = can.id_usuario "); // Table: CANDIDATO
        from.append("   INNER JOIN Solicitante sol ON sol.id_candidato = can.id_candidato "); // Table: SOLICITANTE
        from.append("   INNER JOIN Domicilio dom ON dom.id_propietario = can.id_candidato AND dom.id_tipo_propietario = 2 "); // Table: DOMICILIO
        from.append("   INNER JOIN Municipio mun ON dom.id_entidad      = mun.id_entidad AND dom.id_municipio   = mun.id_municipio "); // Table: MUNICIPIO
        from.append("   INNER JOIN Entidades ent ON dom.id_entidad = ent.id_catalogo_opcion "); // Temporal Table: ENTIDADES

        where.append("WHERE ");
        where.append("  u.id_usuario = ").append(idUsuario);

        sqlCommand.append(select).append(from).append(where);
        //logger.info(sqlCommand.toString());

        // ===== END SQL COMMAND ===== //

        try {
            Query query = entityManager.createNativeQuery(sqlCommand.toString());

            @SuppressWarnings("unchecked")
            List<Object[]> rowSet = query.getResultList();

            for(Object[] objects : rowSet){
                candidatoVo.setIdCandidato(Utils.toInt(objects[0]));            // ID Candidato
                candidatoVo.setNombre(Utils.toString(objects[1]));              // Nombre
                candidatoVo.setApellido1(Utils.toString(objects[2]));           // Apellido1
                candidatoVo.setApellido2(Utils.toString(objects[3]));           // Apellido2
                candidatoVo.setCurp(Utils.toString(objects[4]));                // CURP
                candidatoVo.setNss(Utils.toString(objects[5]));                 // NSS
                candidatoVo.setPpcFechaInscripcion(Utils.toDate(objects[6]));   // Fecha Inscripcion PPC
                domicilioVo.setMunicipio(Utils.toString(objects[7]));            // Municipio
                domicilioVo.setEntidad(Utils.toString(objects[8]));              // Entidad Federativa
            }

        } catch(NoResultException re){
            logger.error(re);
        }catch(Exception e){
            logger.error(e);
        }

        return candidatoVo;
    }
	
	public void actualizaRegistroPPC(long idCandidato, Integer ppcEstatus, Date ppcFechaInscripcion, Integer ppcAceptacionTerminos, Integer ppcEstatusIMSS, Date ppcFechaBajaIMSS, String ppcTipoContratoIMSS, String nss) throws PersistenceException{
		
		try{

			Candidato entity = entityManager.find(Candidato.class, idCandidato);
			
			entity.setPpcEstatus((ppcEstatus != null ? ppcEstatus : Integer.valueOf(0)));		
			entity.setPpcFechaInscripcion(ppcFechaInscripcion);		
			entity.setPpcAceptacionTerminos((ppcAceptacionTerminos != null ? ppcAceptacionTerminos : 0));		
			entity.setPpcEstatusIMSS((ppcEstatusIMSS != null ? ppcEstatusIMSS : 0));		
			entity.setPpcFechaBajaIMSS(ppcFechaBajaIMSS);
			entity.setPpcTipoContratoIMSS(ppcTipoContratoIMSS);
			entity.setNss(nss);
			
			entityManager.merge(entity);
			
		} catch(PersistenceException e){
			throw e;
			
		} catch(Exception e){
			e.printStackTrace();
			logger.error("Error en CandidatoFacade.actualizaRegistroPPC"+e.getMessage());
		}
		
	}
	@Override
	public boolean esNSSUnico(String nss) throws PersistenceException {
		CandidatoVo candidato = null;

		Query query = entityManager.createQuery("select c from Candidato c where c.nss = :nss");
		query.setParameter("nss", nss);

		try{
			@SuppressWarnings("unchecked")
			List<Candidato> results = (List<Candidato>)query.getResultList();
		
			if(results!= null){
				if(results.size()>0)
					return false;
				else
					return true;
			}
			else
			{
				return true;
			}
		} catch (NoResultException re) {
			logger.error("Candidato no localizado mediante la CURP : "+ nss);
			
		} catch (Exception re) {
			throw new PersistenceException(re);
		}
		return false;
	
	}
	
	@Override
	public int actualizaEstatusPPC(long idCandidato, Integer ppcEstatus, Integer ppcIdMotivoFuera) throws PersistenceException {
		int result = -1;
		try {
			Candidato entity = entityManager.find(Candidato.class, idCandidato);
			entity.setPpcEstatus((ppcEstatus != null ? ppcEstatus : Integer.valueOf(0)));
			if (null != ppcIdMotivoFuera)
				entity.setPpcIdMotivoFuera(ppcIdMotivoFuera);
			entityManager.merge(entity);
			result = 1;
		} catch(PersistenceException e){ throw e;
		} catch(Exception e) {
			e.printStackTrace();
			logger.error("Error CandidatoFacade updateEstatusPPC " + e.getMessage());
		}
		return result;
	}
	
	@Override
	public boolean consultarPermisoGeolocalizacion(long idCandidato) throws BusinessException {
		Candidato entity = entityManager.find(Candidato.class, idCandidato);
		if (null != entity && null != entity.getEstatusGeoreferencia())
			return entity.getEstatusGeoreferencia();
		return false;
	}

	@Override
	public void actualizaEstatusGeoreferencia(long idCandidato, boolean estatus) throws PersistenceException {
		try {
			Candidato candidato = entityManager.find(Candidato.class, idCandidato);
			candidato.setEstatusGeoreferencia(estatus);
			candidato.setFechaUltimaActualizacion(new Date());
			entityManager.merge(candidato);
			entityManager.flush();

		} catch (NoResultException re) {
			logger.error("Candidato no localizado mediante el idCandidato : "+ idCandidato);
		} catch (Exception re) {
			re.printStackTrace(); logger.error(re);
			throw new PersistenceException(re);
		}
	}
	
	public int consultaEstatusCandidato(long idCandidato) {
		int estatusC = 0;

        Query query = entityManager.createQuery("SELECT c.estatus FROM Candidato c WHERE c.idCandidato = :idCandidato");
        query.setParameter("idCandidato", idCandidato);

        try {
            estatusC = (int)query.getSingleResult();

        } catch (NoResultException e) {
            logger.error("Candidato no localizado por el idUsuario"+ idCandidato);
        }

		return estatusC;
	}
	
	@Override
	public List<ExpectativaLaboralVO> expectativaLaboralList(long idCandidato, int principal) {
		List<ExpectativaLaboralVO> list = new ArrayList<ExpectativaLaboralVO>();
		Query query = entityManager.createQuery("SELECT el FROM ExpectativaLaboral el WHERE el.idCandidato = :idCandidato AND el.principal = :principal");
        query.setParameter("idCandidato", idCandidato);
        query.setParameter("principal", principal);
        try {
        	@SuppressWarnings("unchecked")
			List<ExpectativaLaboral> results = (List<ExpectativaLaboral>)query.getResultList();
        	for (ExpectativaLaboral entity : results) {
        		ExpectativaLaboralVO expectativa = getExpectativaLaboralVO(entity);
        		CatSubareaVO subarea = catalogoOpcionFacadeLocal.getSubAreaVOByIdAreaIdSubArea(expectativa.getIdAreaLaboralDeseada(),
        				expectativa.getIdSubAreaLaboralDeseada());
        		if (null != subarea) expectativa.setPuestoDeseado(subarea.getDescripcion());
        		list.add(expectativa);
        	}
        } catch (NoResultException e) {
            logger.error("Candidato no localizado por el idUsuario"+ idCandidato);
        }
		return list;
	}
	
	private ExpectativaLaboralVO getExpectativaLaboralVO(ExpectativaLaboral entity) {
		if (null == entity) return null;
		ExpectativaLaboralVO vo = new ExpectativaLaboralVO();
		vo.setIdAreaLaboralDeseada(entity.getIdAreaLaboralDeseada());
		vo.setSalarioPretendido(entity.getSalarioPretendido());
		if (null != entity.getPrincipal())
			vo.setPrincipal(entity.getPrincipal().intValue());
		vo.setIdSubAreaLaboralDeseada(entity.getIdSubAreaLaboralDeseada());
		return vo;
	}
}
