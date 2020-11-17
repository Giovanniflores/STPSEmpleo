package mx.gob.stps.portal.core.candidate.dao;


import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_AREA_LABORAL;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_DOMICILIO_CANDIDATO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_DOMINIO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_ESTATUS;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_ESTATUS_GRADO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_EXPERIENCIA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_GRADO_ESTUDIOS;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_IDIOMAS;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_JERARQUIA_PUESTO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_OCUPACION;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_PERSONAS_CARGO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_SUBSECTOR;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_TIPO_CONTRATO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_TIPO_EMPLEO;
import static mx.gob.stps.portal.core.infra.utils.Utils.obtenEdad;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.rowset.CachedRowSet;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.candidate.vo.ComputacionAvanzadaVO;
import mx.gob.stps.portal.core.candidate.vo.ConocimientoHabilidadVO;
import mx.gob.stps.portal.core.candidate.vo.DatosCurriculumVO;
import mx.gob.stps.portal.core.candidate.vo.ExpectativaLaboralVO;
import mx.gob.stps.portal.core.candidate.vo.ExpectativaLugarVO;
import mx.gob.stps.portal.core.candidate.vo.GradoAcademicoVO;
import mx.gob.stps.portal.core.candidate.vo.HistoriaLaboralVO;
import mx.gob.stps.portal.core.candidate.vo.IdiomaVO;
import mx.gob.stps.portal.core.candidate.vo.InformacionGeneralVO;
import mx.gob.stps.portal.core.candidate.vo.OfertaPostulacionVO;
import mx.gob.stps.portal.core.candidate.vo.OtroMedioVO;
import mx.gob.stps.portal.core.candidate.vo.PerfilVO;
import mx.gob.stps.portal.core.candidate.vo.RedesSocialesVO;
import mx.gob.stps.portal.core.infra.data.ConnectionWraper;
import mx.gob.stps.portal.core.infra.data.TemplateDAO;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.DISPONIBILIDAD_RADICAR;
import mx.gob.stps.portal.core.infra.utils.Constantes.DISPONIBILIDAD_VIAJAR;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.MOSTRAR_EMPRESA;
import mx.gob.stps.portal.core.infra.utils.Constantes.MULTIREGISTRO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.search.Candidate;
import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.utils.Catalogos.TRABAJA_ACTUALMENTE;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import org.apache.log4j.Logger;

/**
 * @author Felipe Juarez Ramirez
 * @since 07/03/2011
 * 
 * Modificaciones: Ana Mu%oz Ramos - Mayo 2017 
 **/
public class CandidatoDAO extends TemplateDAO {

	private static Logger logger = Logger.getLogger(CandidatoDAO.class);

	private String CONSULTA_PERFIL_CANDIDATO;
	
	@PersistenceContext
	private EntityManager entityManager;

	public CandidatoDAO(){}
	
	public CandidatoDAO(Connection connectionGlobal){
		super(connectionGlobal);
	} 
	
	/**
	 * Busca los datos del perfil de un candidato
	 * 
	 * @author Felipe Juarez Ramirez
	 * @since 03/03/2011
	 * @param long
	 * @throws SQLException
	 * @return PerfilVO
	 **/
	public PerfilVO buscarPerfil(long idCandidato) throws SQLException {
		logger.debug("Inicia metodo - buscarPerfil");
		CONSULTA_PERFIL_CANDIDATO = "BUSCAR_PERFIL";
		Long[] parametros = { idCandidato };
		CachedRowSet cachedRowSet = executeQuery(parametros);

		PerfilVO perfil = null;
		try {
			if (cachedRowSet.next()) {
				perfil = new PerfilVO();
				perfil.setIdCandidato(idCandidato);
				int i = 0;
				perfil.setIdUsuario(cachedRowSet.getLong(++i));
				perfil.setIdOficina(cachedRowSet.getLong(++i));
				perfil.setCurp(cachedRowSet.getString(++i));
				perfil.setNombre(cachedRowSet.getString(++i));
				perfil.setApellido1(cachedRowSet.getString(++i));
				perfil.setApellido2(cachedRowSet.getString(++i));
				perfil.setIdGenero(cachedRowSet.getInt(++i));
				perfil.setFechaNacimiento(cachedRowSet.getDate(++i));
				perfil.setEdad(obtenEdad(perfil.getFechaNacimiento()));	// Calcula edad con el campo fechaNacimiento
				perfil.setIdEntidadNacimiento(cachedRowSet.getLong(++i));
				perfil.setEntidadNacimiento(cachedRowSet.getString(++i));
				perfil.setIdEstadoCivil(cachedRowSet.getLong(++i));
				perfil.setIdTipoDiscapacidad(cachedRowSet.getLong(++i));
				perfil.setConfidencialidad(cachedRowSet.getInt(++i));
				perfil.setContactoCorreo(cachedRowSet.getInt(++i));
				perfil.setContactoTelefono(cachedRowSet.getInt(++i));
				perfil.setHoraContactoIni(cachedRowSet.getLong(++i));
				perfil.setHoraContactoFin(cachedRowSet.getLong(++i));
				perfil.setIdRecibeOferta(cachedRowSet.getInt(++i));
				
				perfil.setIdTrabaja(Utils.validarCandidatoEmpleadoActualmente(cachedRowSet.getInt(++i)));
				perfil.setIdRazonBusqueda(Utils.validarCandidatoRazonBusqueda(perfil.getIdTrabaja(), cachedRowSet.getLong(++i)));
				perfil.setDescripcionOtroMotivoBusq(cachedRowSet.getString(++i));
				perfil.setInicioBusqueda(cachedRowSet.getDate(++i));
				perfil.setCorreoElectronico(cachedRowSet.getString(++i));
				perfil.setEstiloCV(cachedRowSet.getInt(++i));
				perfil.setIdMedioPortal(cachedRowSet.getLong(++i));
				perfil.setIdEstatusPPC(cachedRowSet.getInt(++i));
				perfil.setApoyoProspera(cachedRowSet.getInt(++i));
				perfil.setDiscapacidades(cachedRowSet.getString(++i));
				perfil.setIdLicencia(cachedRowSet.getInt(++i));
				perfil.setFolioProspera(cachedRowSet.getString(++i));
				perfil.setFolioIntegranteProspera(cachedRowSet.getString(++i));
				perfil.setDisponibilidadRadicarPais(cachedRowSet.getInt(++i));
				perfil.setUltimaActualizacion(cachedRowSet.getString("fUltAct"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e);
			throw new SQLException(e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return perfil;
	}
	
	public List<CandidatoVo> consultaPostulantesEmpresaDesactivada(long idOfertaEmpleo) throws SQLException{
		List<CandidatoVo> postulantesEmpresaDesactivada = new ArrayList<CandidatoVo>();
		CandidatoVo candidatoVo = null;		
		CONSULTA_PERFIL_CANDIDATO = "BUSCAR_POSTULANTE_EMPRESA_DESACTIVADA";
		String []params = {String.valueOf(idOfertaEmpleo), String.valueOf(ESTATUS.POSTULADO.getIdOpcion())};
		
		CachedRowSet cachedRowSet = executeQuery(params);
		
		try {
			while (cachedRowSet.next()) {
				candidatoVo = new CandidatoVo();
				
				candidatoVo.setIdCandidato(Utils.toLong(cachedRowSet.getLong(1)));	
				candidatoVo.setNombre(Utils.toString(cachedRowSet.getString(2)));
				candidatoVo.setApellido1(Utils.toString(cachedRowSet.getString(3)));
				candidatoVo.setApellido2(Utils.toString(cachedRowSet.getString(4)));
				candidatoVo.setCorreoElectronico(Utils.toString(cachedRowSet.getString(5)));										
				
				postulantesEmpresaDesactivada.add(candidatoVo);						
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new SQLException(e);
		}
		
		return postulantesEmpresaDesactivada;		
	}
	
	public List<CandidatoVo> consultaCandidatosAvisoDeVigencia() throws SQLException{
		List<CandidatoVo> candidatosFueraDeVigencia = new ArrayList<CandidatoVo>();
		CandidatoVo candidatoVo = null;
		CONSULTA_PERFIL_CANDIDATO = "BUSCAR_CANDIDATO_AVISO_FUERA_DE_VIGENCIA";
		String []params = {String.valueOf(ESTATUS.ACTIVO.getIdOpcion())};
		CachedRowSet cachedRowSet = executeQuery(params);
		
		try {
			while (cachedRowSet.next()) {
				candidatoVo = new CandidatoVo();
				candidatoVo.setIdCandidato(Utils.toLong(cachedRowSet.getLong(1)));		
				candidatoVo.setCorreoElectronico(Utils.toString(cachedRowSet.getString(2)));										
				candidatoVo.setNombre(Utils.toString(cachedRowSet.getString(3)));
				candidatoVo.setApellido1(Utils.toString(cachedRowSet.getString(4)));
				candidatoVo.setApellido2(Utils.toString(cachedRowSet.getString(5)));
				candidatosFueraDeVigencia.add(candidatoVo);						
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new SQLException(e);
		}
		
		return candidatosFueraDeVigencia;
	}		
	
	public List<Long> consultaIdCandidatosFueraDeVigencia() throws SQLException{
		List<Long>  idsCandidatosFueraDeVigencia = new ArrayList<Long>();
		CONSULTA_PERFIL_CANDIDATO = "BUSCAR_CANDIDATO_FUERA_DE_VIGENCIA";
		String []params = {String.valueOf(ESTATUS.ACTIVO.getIdOpcion())};
		CachedRowSet cachedRowSet = executeQuery(params);
		
		try {
			while (cachedRowSet.next()) {
				Long idCandidato = Utils.toLong(cachedRowSet.getLong(1));
				idsCandidatosFueraDeVigencia.add(idCandidato);						
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new SQLException(e);
		}
		
		return idsCandidatosFueraDeVigencia;		
	}
	
	/**
	 * Busca los datos del perfil de un candidato
	 * 
	 * @author Felipe Ju?rez Ram?rez
	 * @since 03/03/2011
	 * @param long
	 * @throws SQLException
	 * @return PerfilVO
	 **/
	public List<OtroMedioVO> buscarOtrosMedios(long idCandidato) throws SQLException {
		CONSULTA_PERFIL_CANDIDATO = "BUSCAR_OTROS_MEDIOS";
		Long[] parametros = { idCandidato };
		CachedRowSet cachedRowSet = executeQuery(parametros);

		List<OtroMedioVO> otrosMediosVO = new ArrayList<OtroMedioVO>();
		OtroMedioVO otroMedioVO = null;
		try {
			while (cachedRowSet.next()) {
				otroMedioVO = new OtroMedioVO();
				otroMedioVO.setIdOtroMedio(cachedRowSet.getLong(1));
				otroMedioVO.setIdMedioBusqueda(cachedRowSet.getLong(2));
				otrosMediosVO.add(otroMedioVO);
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new SQLException(e);
		}
		return otrosMediosVO;
	}
	
	
	public int buscarEstiloCV(long idCandidato) throws SQLException {
		int estiloCv = 0;
		CONSULTA_PERFIL_CANDIDATO = "DETALLE_CANDIDATO_ESTILO_CV";
		Long[] parametros = { idCandidato };
		CachedRowSet cachedRowSet = executeQuery(parametros);
		
		try {
			if (cachedRowSet.next()) {
				estiloCv = cachedRowSet.getInt(1);
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new SQLException(e);
		}
		return estiloCv;
	}	
	
	public int actualizaEstiloCV(int estiloCv, long idCandidato) throws SQLException {
		int result = 0;
		CONSULTA_PERFIL_CANDIDATO = "ACTUALIZA_CANDIDATO_ESTILO_CV";
		Long[] parametros = { (long)estiloCv, idCandidato };
		
		try {			
			result = executeUpdate(parametros);
		} catch (SQLException e) {
			logger.error(e);
			throw new SQLException(e);
		}
		return result;
	}		

	
	/**
	 * Busca los datos del perfil de un candidato
	 * 
	 * @author Felipe Ju?rez Ram?rez
	 * @since 28/03/2011
	 * @param long
	 * @throws SQLException
	 * @return PerfilVO
	 **/
	public PerfilVO buscarPerfilCandidato(long idCandidato) throws SQLException {
		logger.debug("Inicia metodo - buscarPerfilCandidato");
		CONSULTA_PERFIL_CANDIDATO = "BUSCAR_PERFIL_CANDIDATO";
		Long[] parametros = { idCandidato };
		CachedRowSet cachedRowSet = executeQuery(parametros);

		PerfilVO perfil = null;
		try {
			if (cachedRowSet.next()) {
				perfil = new PerfilVO();
				perfil.setIdCandidato(idCandidato);
				perfil.setDisponibilidadViajar(cachedRowSet.getInt(1));
				perfil.setDisponibilidadRadicar(cachedRowSet.getInt(2));
				perfil.setIdExperienciaTotal(cachedRowSet.getLong(3));
				perfil.setIdSectorMayorExpr(cachedRowSet.getLong(4));
				perfil.setPuestoMayorExpr(cachedRowSet.getString(5));
				perfil.setIdAreaLaboralMayorExpr(cachedRowSet.getLong(6));
				perfil.setIdOcupacionMayorExpr(cachedRowSet.getLong(7));
				perfil.setComputacionBasica(cachedRowSet.getInt(8));
				perfil.setIdExperienciaCompu(cachedRowSet.getLong(9));
				perfil.setIdDominioCompu(cachedRowSet.getLong(10));
				perfil.setIdExperienciaOffice(cachedRowSet.getLong(11));
				perfil.setIdDominioOffice(cachedRowSet.getLong(12));
				perfil.setIdExperienciaInternet(cachedRowSet.getLong(13));
				perfil.setIdDominioInternet(cachedRowSet.getLong(14));
				perfil.setComputacionAvanzada(cachedRowSet.getInt(15));
				perfil.setSinEstudios(cachedRowSet.getInt(16));
				perfil.setSinExperiencia(cachedRowSet.getInt(17));
				perfil.setExperiencia(cachedRowSet.getString(18));
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new SQLException(e);
		}
		return perfil;
	}

	public List<GradoAcademicoVO> buscarGrados(long idCandidato, int principal) throws SQLException {
		CONSULTA_PERFIL_CANDIDATO = "BUSCAR_GRADOS";
		Object[] parametros = { idCandidato, principal };
		CachedRowSet cachedRowSet = executeQuery(parametros);

		List<GradoAcademicoVO> gradosVO = new ArrayList<GradoAcademicoVO>();
		GradoAcademicoVO gradoVO = null;
		try {
			while (cachedRowSet.next()) {
				gradoVO = new GradoAcademicoVO();
				gradoVO.setIdCandidatoGradoAcademico(cachedRowSet.getLong(1));
				gradoVO.setIdNivelEstudio(cachedRowSet.getLong(2));
				gradoVO.setNivel(cachedRowSet.getString(3));
				gradoVO.setIdSituacionAcademica(cachedRowSet.getLong(4));
				gradoVO.setSituacion(cachedRowSet.getString(5));
				gradoVO.setIdCarreraEspecialidad(cachedRowSet.getLong(6));
				gradoVO.setCarrera(cachedRowSet.getString(7));
				gradoVO.setEscuela(cachedRowSet.getString(8));
				gradoVO.setInicio(cachedRowSet.getInt(9));
				gradoVO.setFin(cachedRowSet.getInt(10));
				gradoVO.setFechaInicio(cachedRowSet.getDate(11));
				gradoVO.setFechaFin(cachedRowSet.getDate(12));
				gradoVO.setLugar(cachedRowSet.getString(13));
				gradoVO.setPrincipal(principal);
				gradosVO.add(gradoVO);
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new SQLException(e);
		}
		return gradosVO;
	}
	
	public List<GradoAcademicoVO> buscarGrados(long idCandidato) throws SQLException {
		CONSULTA_PERFIL_CANDIDATO = "BUSCAR_GRADOS_CAND";
		Object[] parametros = { idCandidato };
		CachedRowSet cachedRowSet = executeQuery(parametros);
		
		List<GradoAcademicoVO> gradosVO = new ArrayList<GradoAcademicoVO>();
		GradoAcademicoVO gradoVO = null;
		try {
			while (cachedRowSet.next()) {
				gradoVO = new GradoAcademicoVO();
				gradoVO.setIdCandidatoGradoAcademico(cachedRowSet.getLong(1));
				gradoVO.setIdNivelEstudio(cachedRowSet.getLong(2));
				gradoVO.setNivel(cachedRowSet.getString(3));
				gradoVO.setIdSituacionAcademica(cachedRowSet.getLong(4));
				gradoVO.setSituacion(cachedRowSet.getString(5));
				gradoVO.setIdCarreraEspecialidad(cachedRowSet.getLong(6));
				gradoVO.setCarrera(cachedRowSet.getString(7));
				gradoVO.setEscuela(cachedRowSet.getString(8));
				gradoVO.setInicio(cachedRowSet.getInt(9));
				gradoVO.setFin(cachedRowSet.getInt(10));
				gradosVO.add(gradoVO);
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new SQLException(e);
		}
		return gradosVO;
	}
	/**
	 * Metodo que realiza la busqueda de datos del curriculum para la nueva imagen.
	 */
	public DatosCurriculumVO buscarDatosCurriculum(long idCandidato)throws SQLException {
		CONSULTA_PERFIL_CANDIDATO = "DATOS_CURRICULUM";
		Object[] parametros = { idCandidato };
		CachedRowSet cachedRowSet = executeQuery(parametros);

		DatosCurriculumVO datosVO = new DatosCurriculumVO();
		try {
			while (cachedRowSet.next()) {
				datosVO.setTituloCV(cachedRowSet.getString("TITULO_CV"));
				datosVO.setObjetivoProfesional(cachedRowSet.getString("OBJETIVOS"));
				datosVO.setResumenProfesional(cachedRowSet.getString("RESUMEN_PROFESIONAL"));
				datosVO.setRedesSociales(new RedesSocialesVO());
				datosVO.getRedesSociales().setFacebook(cachedRowSet.getString("FACEBOOK"));
				datosVO.getRedesSociales().setTwitter(cachedRowSet.getString("TWITTER"));
				datosVO.getRedesSociales().setLinkedin(cachedRowSet.getString("LINKEDIN"));
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new SQLException(e);
		}
		return datosVO;
	}

	public List<IdiomaVO> buscarIdiomas(long idCandidato, int principal)throws SQLException {
		CONSULTA_PERFIL_CANDIDATO = "BUSCAR_IDIOMAS";
		Object[] parametros = { idCandidato, principal };
		CachedRowSet cachedRowSet = executeQuery(parametros);

		List<IdiomaVO> idiomasVO = new ArrayList<IdiomaVO>(0);
		IdiomaVO idiomaVO = null;
		try {
			while (cachedRowSet.next()) {
				idiomaVO = new IdiomaVO();
				idiomaVO.setIdCandidatoIdioma(cachedRowSet.getLong(1));
				idiomaVO.setIdIdioma(cachedRowSet.getLong(2));
				idiomaVO.setIdioma(cachedRowSet.getString(3));
				idiomaVO.setIdCertificacion(cachedRowSet.getLong(4));
				idiomaVO.setCertificacion(cachedRowSet.getString(5));
				idiomaVO.setIdDominio(cachedRowSet.getLong(6));
				idiomaVO.setDominio(cachedRowSet.getString(7));
				idiomaVO.setPrincipal(principal);
				idiomaVO.setIdCandidato(idCandidato);
				idiomasVO.add(idiomaVO);
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new SQLException(e);
		}
		return idiomasVO;
	}
	
	public List<IdiomaVO> buscarIdiomas(long idCandidato)throws SQLException {
		CONSULTA_PERFIL_CANDIDATO = "BUSCAR_IDIOMAS_CAND";
		Object[] parametros = { idCandidato };
		CachedRowSet cachedRowSet = executeQuery(parametros);
		
		List<IdiomaVO> idiomasVO = new ArrayList<IdiomaVO>(0);
		IdiomaVO idiomaVO = null;
		try {
			while (cachedRowSet.next()) {
				idiomaVO = new IdiomaVO();
				idiomaVO.setIdCandidatoIdioma(cachedRowSet.getLong(1));
				idiomaVO.setIdIdioma(cachedRowSet.getLong(2));
				idiomaVO.setIdioma(cachedRowSet.getString(3));
				idiomaVO.setIdCertificacion(cachedRowSet.getLong(4));
				idiomaVO.setCertificacion(cachedRowSet.getString(5));
				idiomaVO.setIdDominio(cachedRowSet.getLong(6));
				idiomaVO.setDominio(cachedRowSet.getString(7));
				idiomaVO.setIdDominio(cachedRowSet.getInt(8));
				idiomaVO.setIdDominioHabla(cachedRowSet.getInt(9));
				idiomaVO.setIdDominioEscrito(cachedRowSet.getInt(10));
				idiomaVO.setIdDominioLectura(cachedRowSet.getInt(11));
				idiomaVO.setIdDominioComprension(cachedRowSet.getInt(12));
				idiomasVO.add(idiomaVO);
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new SQLException(e);
		}
		return idiomasVO;
	}

	public List<ConocimientoHabilidadVO> buscarConocimientosHabilidades(
			long idCandidato, long idTipoConocimHabilidad, int principal) throws SQLException {
		CONSULTA_PERFIL_CANDIDATO = "BUSCAR_CONOCHABS";
		Object[] parametros = { idCandidato, idTipoConocimHabilidad, principal };
		CachedRowSet cachedRowSet = executeQuery(parametros);

		List<ConocimientoHabilidadVO> conocHabsVO = new ArrayList<ConocimientoHabilidadVO>();
		ConocimientoHabilidadVO conocHabVO = null;
		try {
			while (cachedRowSet.next()) {
				conocHabVO = new ConocimientoHabilidadVO();
				conocHabVO.setIdCandidatoConocimHabilidad(cachedRowSet.getLong(1));
				conocHabVO.setIdTipoConocimHabilidad(cachedRowSet.getLong(2));
				conocHabVO.setConocimientoHabilidad(cachedRowSet.getString(3));
				conocHabVO.setIdExperiencia(cachedRowSet.getLong(4));
				conocHabVO.setExperiencia(cachedRowSet.getString(5));
				conocHabVO.setIdDominio(cachedRowSet.getLong(6));
				conocHabVO.setDominio(cachedRowSet.getString(7));
				conocHabVO.setDescripcion(cachedRowSet.getString(8));
				conocHabVO.setPrincipal(cachedRowSet.getInt(9));
				conocHabsVO.add(conocHabVO);
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new SQLException(e);
		}
		return conocHabsVO;
	}
	
	public List<ConocimientoHabilidadVO> buscarConocimientosHabilidades(long idCandidato, long idTipoConocimHabilidad) throws SQLException {
		CONSULTA_PERFIL_CANDIDATO = "BUSCAR_CONOCHABS_CAND";
		Object[] parametros = { idCandidato, idTipoConocimHabilidad };
		CachedRowSet cachedRowSet = executeQuery(parametros);

		List<ConocimientoHabilidadVO> conocHabsVO = new ArrayList<ConocimientoHabilidadVO>();
		ConocimientoHabilidadVO conocHabVO = null;
		try {
			while (cachedRowSet.next()) {
				conocHabVO = new ConocimientoHabilidadVO();
				conocHabVO.setIdCandidatoConocimHabilidad(cachedRowSet.getLong(1));
				conocHabVO.setIdTipoConocimHabilidad(cachedRowSet.getLong(2));
				conocHabVO.setConocimientoHabilidad(cachedRowSet.getString(3));
				conocHabVO.setIdExperiencia(cachedRowSet.getLong(4));
				conocHabVO.setExperiencia(cachedRowSet.getString(5));
				conocHabVO.setIdDominio(cachedRowSet.getLong(6));
				conocHabVO.setDominio(cachedRowSet.getString(7));
				conocHabVO.setDescripcion(cachedRowSet.getString(8));
				conocHabVO.setPrincipal(cachedRowSet.getInt(9));
				conocHabsVO.add(conocHabVO);
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new SQLException(e);
		}
		return conocHabsVO;
	}
	
	
	public List<CandidatoVo> buscarCandidatos(long idEmpresa, long idOferta) throws SQLException {
		List<CandidatoVo> candidatosVO = null;
		CONSULTA_PERFIL_CANDIDATO = "CARPETA_EMPRESARIAL";
		//Object[] parametros = { idEmpresa, idOferta, ESTATUS.VINCULADO.getIdOpcion(),  ESTATUS.SELECCIONADA.getIdOpcion() };
		Context context = null;
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet cachedRowSet = null;
		
		try{
			ConnectionWraper wraper = getConnection();
			conn = wraper.getConnection();
			context = wraper.getContext();

			stmt = conn.prepareCall(getQuery());
			stmt.setLong(1, idEmpresa);
			stmt.setLong(2, idOferta);
			stmt.setInt(3, ESTATUS.VINCULADO.getIdOpcion());
			stmt.setInt(4, ESTATUS.SELECCIONADA.getIdOpcion());
			stmt.setInt(5, ESTATUS.SELECCIONADO.getIdOpcion());
			stmt.registerOutParameter(6, OracleTypes.CURSOR); //REF CURSOR
			stmt.execute();
			cachedRowSet = ((OracleCallableStatement)stmt).getCursor(6);
			
			//CachedRowSet cachedRowSet = executeQuery(parametros);
			candidatosVO = setMisCandidatos(cachedRowSet);
		} catch (SQLException e) {
			logger.error(e);
			throw new SQLException(e);
		} finally{
			try{if (cachedRowSet!=null) cachedRowSet.close();}catch(Exception e){e.printStackTrace();}
			try{if (stmt!=null) stmt.close();}catch(Exception e){e.printStackTrace();}
			
			/** Se esta utilizando una sola conexion para diversas consultas, por lo tanto no se cierra **/
			if (!isGlobalConnection()){
				try{if (conn!=null) conn.close();}catch(Exception e){e.printStackTrace();}	

				if (context!=null){
					try {
						context.close();
					} catch (Exception e) {e.printStackTrace();}
				}
			}
		}
		
		return candidatosVO;
	}

	public List<CandidatoVo> buscarPostulados(long idEmpresa, long idOferta) throws SQLException {
		List<CandidatoVo> candidatosVO = null;
		
		CONSULTA_PERFIL_CANDIDATO = "MIS_CANDIDATOS";
		
		Context context = null;
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet cachedRowSet = null;
		
		try{
			ConnectionWraper wraper = getConnection();
			conn = wraper.getConnection();
			context = wraper.getContext();

			stmt = conn.prepareCall(getQuery());
			stmt.setLong(1, idEmpresa);
			stmt.setLong(2, idOferta);
			stmt.setInt(3, ESTATUS.POSTULADO.getIdOpcion());
			stmt.setInt(4, ESTATUS.EN_PROCESO.getIdOpcion());		
			stmt.registerOutParameter(5, OracleTypes.CURSOR); //REF CURSOR
			stmt.execute();
			cachedRowSet = ((OracleCallableStatement)stmt).getCursor(5);
			
			//CachedRowSet cachedRowSet = executeQuery(parametros);
			candidatosVO = setMisCandidatos(cachedRowSet);
			
		} catch (SQLException e) {
			logger.error(e);
			throw new SQLException(e);
		}finally{
			try{if (cachedRowSet!=null) cachedRowSet.close();}catch(Exception e){e.printStackTrace();}
			try{if (stmt!=null) stmt.close();}catch(Exception e){e.printStackTrace();}
			
			/** Se esta utilizando una sola conexion para diversas consultas, por lo tanto no se cierra **/
			if (!isGlobalConnection()){
				try{if (conn!=null) conn.close();}catch(Exception e){e.printStackTrace();}
				
				if (context!=null){
					try {
						context.close();
					} catch (Exception e) {e.printStackTrace();}
				}
			}
		}
		
		return candidatosVO;
	}

	private List<CandidatoVo> setMisCandidatos(ResultSet cachedRowSet) throws SQLException{
		
		List<CandidatoVo> candidatos = new ArrayList<CandidatoVo>(0);
		
		CandidatoVo candidatoVO = null;
		while (cachedRowSet.next()) {
			candidatoVO = new CandidatoVo();
	
			candidatoVO.setIdOferta(cachedRowSet.getLong("ID_OFERTA_CANDIDATO"));
			candidatoVO.setEstatus(cachedRowSet.getInt("ID_ESTATUS"));
			candidatoVO.setIdCandidato(cachedRowSet.getLong("ID_CANDIDATO"));
			candidatoVO.setNombre(cachedRowSet.getString("NOMBRE"));
			candidatoVO.setApellido1(cachedRowSet.getString("APELLIDO1"));
			candidatoVO.setApellido2(cachedRowSet.getString("APELLIDO2"));
			candidatoVO.setCarrera(cachedRowSet.getString("DESC_NIVEL_ESTUDIO"));
			candidatoVO.setFechaNacimiento(cachedRowSet.getDate("FECHA_NACIMIENTO"));
			candidatoVO.setOcupacion(cachedRowSet.getString("DESC_OCUPACION_DESEADA"));
			candidatoVO.setSalario(cachedRowSet.getDouble("SALARIO_PRETENDIDO"));
			candidatoVO.setMunicipioEntidad(cachedRowSet.getString("DESC_MUNICIPIO") + ", " + cachedRowSet.getString("DESC_ENTIDAD"));
			candidatoVO.setEdad(obtenEdad(cachedRowSet.getDate("FECHA_NACIMIENTO")));
			candidatoVO.setDescEstatus(cachedRowSet.getString("DESC_ESTATUS"));
			//detalles
			candidatoVO.setDescTipoContrato(cachedRowSet.getString("DESC_TIPO_CONTRATO"));
			candidatoVO.setHorarioEmpleo(cachedRowSet.getString("DESC_TIPO_EMPLEO_DESEADO"));
			
			if (DISPONIBILIDAD_VIAJAR.SI.getIdOpcion() == cachedRowSet.getLong("DISPONIBILIDAD_VIAJAR")){
				candidatoVO.setDispViajarFuera("Si" );
			}else {
				candidatoVO.setDispViajarFuera("No" );
			}
			if (DISPONIBILIDAD_RADICAR.SI.getIdOpcion() ==cachedRowSet.getLong("DISPONIBILIDAD_RADICAR")){
				candidatoVO.setDispRadicarFuera("Si");
			}else{
				candidatoVO.setDispRadicarFuera("No");
			}
			candidatoVO.setConfidencialidadDatos(cachedRowSet.getInt("CONFIDENCIALIDAD_DATOS"));
			
			candidatoVO.setCompatibilidad(cachedRowSet.getInt("COMPATIBILIDAD"));
			
			candidatos.add(candidatoVO);
		}
			
		
		return candidatos;
	}

	
	public long buscarEstatusOfertaCandidato(
			long idCandidato, long idOferta) throws SQLException {
		CONSULTA_PERFIL_CANDIDATO = "BUSCAR_ESTATUS_OFERTA_CANDIDATO";
		Object[] parametros = { idCandidato, idOferta };
		CachedRowSet cachedRowSet = executeQuery(parametros);

		try {
			if (cachedRowSet.next()) {
				return cachedRowSet.getLong(1);
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new SQLException(e);
		}
		return 0;
	}
	
	public List<ComputacionAvanzadaVO> buscarCompuAvanzadas(long idCandidato, int principal) throws SQLException {
		CONSULTA_PERFIL_CANDIDATO = "BUSCAR_COMPU_AVANZADA";
		Object[] parametros = { idCandidato, principal };
		CachedRowSet cachedRowSet = executeQuery(parametros);

		List<ComputacionAvanzadaVO> compuAvanzadasVO = new ArrayList<ComputacionAvanzadaVO>();
		ComputacionAvanzadaVO compuAvanzadaVO = null;
		try {
			while (cachedRowSet.next()) {
				compuAvanzadaVO = new ComputacionAvanzadaVO();
				compuAvanzadaVO.setIdCandidatoCompuAvanzada(cachedRowSet.getLong(1));
				compuAvanzadaVO.setSoftwareHardware(cachedRowSet.getString(2));
				compuAvanzadaVO.setIdExperiencia(cachedRowSet.getLong(3));
				compuAvanzadaVO.setExperiencia(cachedRowSet.getString(4));
				compuAvanzadaVO.setIdDominio(cachedRowSet.getLong(5));
				compuAvanzadaVO.setDominio(cachedRowSet.getString(6));
				compuAvanzadaVO.setDescripcion(cachedRowSet.getString(7));
				compuAvanzadasVO.add(compuAvanzadaVO);
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new SQLException(e);
		}
		return compuAvanzadasVO;
	}
	
	public List<ComputacionAvanzadaVO> buscarCompuAvanzadas(
			long idCandidato) throws SQLException {
		CONSULTA_PERFIL_CANDIDATO = "BUSCAR_COMPU_AVANZADA_CAND";
		Object[] parametros = { idCandidato };
		CachedRowSet cachedRowSet = executeQuery(parametros);

		List<ComputacionAvanzadaVO> compuAvanzadasVO = new ArrayList<ComputacionAvanzadaVO>();
		ComputacionAvanzadaVO compuAvanzadaVO = null;
		try {
			while (cachedRowSet.next()) {
				compuAvanzadaVO = new ComputacionAvanzadaVO();
				compuAvanzadaVO.setIdCandidatoCompuAvanzada(cachedRowSet.getLong(1));
				compuAvanzadaVO.setSoftwareHardware(cachedRowSet.getString(2));
				compuAvanzadaVO.setIdExperiencia(cachedRowSet.getLong(3));
				compuAvanzadaVO.setExperiencia(cachedRowSet.getString(4));
				compuAvanzadaVO.setIdDominio(cachedRowSet.getLong(5));
				compuAvanzadaVO.setDominio(cachedRowSet.getString(6));
				compuAvanzadaVO.setDescripcion(cachedRowSet.getString(7));
				compuAvanzadasVO.add(compuAvanzadaVO);
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new SQLException(e);
		}
		return compuAvanzadasVO;
	}
	
	public List<HistoriaLaboralVO> buscarHistLaboral(
			long idCandidato, int principal) throws SQLException {

		CONSULTA_PERFIL_CANDIDATO = "BUSCAR_HIST_LABORAL";
		Object[] parametros = { idCandidato, principal };
		CachedRowSet cachedRowSet = executeQuery(parametros);

		List<HistoriaLaboralVO> histLaboralesVO = new ArrayList<HistoriaLaboralVO>();
		HistoriaLaboralVO histLaboralVO = null;
		try {
			while (cachedRowSet.next()) {
				histLaboralVO = new HistoriaLaboralVO();
				histLaboralVO.setIdCandidato(idCandidato);
				histLaboralVO.setIdHistorialLaboral(cachedRowSet.getLong(1));
				histLaboralVO.setTrabajoActual(cachedRowSet.getInt(2));
				histLaboralVO.setIdSector(cachedRowSet.getLong(3));
				histLaboralVO.setSector(cachedRowSet.getString(4));
				histLaboralVO.setPuesto(cachedRowSet.getString(5));
				histLaboralVO.setIdAreaLaboral(cachedRowSet.getLong(6));
				histLaboralVO.setAreaLaboral(cachedRowSet.getString(7));
				histLaboralVO.setIdOcupacion(cachedRowSet.getLong(8));
				histLaboralVO.setOcupacion(cachedRowSet.getString(9));
				histLaboralVO.setEmpresa(cachedRowSet.getString(10));
				histLaboralVO.setConfidencialidadEmpresa(cachedRowSet.getInt(11));
				histLaboralVO.setLaboresInicial(cachedRowSet.getDate(12));
				histLaboralVO.setLaboresFinal(cachedRowSet.getDate(13));
				histLaboralVO.setAniosLaborados(cachedRowSet.getInt(14));
				histLaboralVO.setAnios(cachedRowSet.getString(15));
				histLaboralVO.setIdJerarquia(cachedRowSet.getLong(16));
				histLaboralVO.setJerarquia(cachedRowSet.getString(17));
				histLaboralVO.setPersonasCargo(cachedRowSet.getInt(18));
				histLaboralVO.setPersonas(cachedRowSet.getString(19));
				histLaboralVO.setSalarioMensual(cachedRowSet.getDouble(20));
				histLaboralVO.setFuncion(cachedRowSet.getString(21));
				histLaboralVO.setLogro(cachedRowSet.getString(22));
				histLaboralesVO.add(histLaboralVO);
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new SQLException(e);
		}
		return histLaboralesVO;
	}
	
	public List<HistoriaLaboralVO> buscarHistLaboral(
			long idCandidato) throws SQLException {
		CONSULTA_PERFIL_CANDIDATO = "BUSCAR_HIST_LABORAL_CAND";
		Object[] parametros = { idCandidato };
		CachedRowSet cachedRowSet = executeQuery(parametros);

		List<HistoriaLaboralVO> histLaboralesVO = new ArrayList<HistoriaLaboralVO>();
		HistoriaLaboralVO histLaboralVO = null;
		try {
			while (cachedRowSet.next()) {
				histLaboralVO = new HistoriaLaboralVO();
				histLaboralVO.setIdHistorialLaboral(cachedRowSet.getLong(1));
				histLaboralVO.setTrabajoActual(cachedRowSet.getInt(2));
				histLaboralVO.setIdSector(cachedRowSet.getLong(3));
				histLaboralVO.setSector(cachedRowSet.getString(4));
				histLaboralVO.setPuesto(cachedRowSet.getString(5));
				histLaboralVO.setIdAreaLaboral(cachedRowSet.getLong(6));
				histLaboralVO.setAreaLaboral(cachedRowSet.getString(7));
				histLaboralVO.setIdOcupacion(cachedRowSet.getLong(8));
				histLaboralVO.setOcupacion(cachedRowSet.getString(9));
				histLaboralVO.setEmpresa(cachedRowSet.getString(10));
				histLaboralVO.setConfidencialidadEmpresa(cachedRowSet.getInt(11));
				histLaboralVO.setLaboresInicial(cachedRowSet.getDate(12));
				histLaboralVO.setLaboresFinal(cachedRowSet.getDate(13));
				histLaboralVO.setAniosLaborados(cachedRowSet.getInt(14));
				histLaboralVO.setAnios(cachedRowSet.getString(15));
				histLaboralVO.setIdJerarquia(cachedRowSet.getLong(16));
				histLaboralVO.setJerarquia(cachedRowSet.getString(17));
				histLaboralVO.setPersonasCargo(cachedRowSet.getInt(18));
				histLaboralVO.setPersonas(cachedRowSet.getString(19));
				histLaboralVO.setSalarioMensual(cachedRowSet.getDouble(20));
				histLaboralVO.setFuncion(cachedRowSet.getString(21));
				histLaboralVO.setLogro(cachedRowSet.getString(22));
				histLaboralesVO.add(histLaboralVO);
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new SQLException(e);
		}
		return histLaboralesVO;
	}
	
	/**
	 * Extrae de la base de datos, los datos correspondientes a la secci&oacute;n
     * &quot;Informaci&oacute;n del Candidato&quot; mostrados en las pantallas 
     * de detalle del candidato.
	 * @param idCandidato representa el identificador del candidato del cual se
	 *        quiere obtener la informaci&oacute;n
	 * @return un objeto {@code InformacionGeneralVO} que contiene la informaci&oacute;n
	 *         a mostrar
	 * @throws SQLException en caso de ocurrir alg&uacute;n problema al consultar en la BD
	 */
	public InformacionGeneralVO buscarInformacionGeneral(long idCandidato) throws SQLException {
		logger.debug("Inicia metodo - buscarInformacionGeneral");
		InformacionGeneralVO info = null;
		CONSULTA_PERFIL_CANDIDATO = "BUSCAR_DETALLE";
		Context context = null;		
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet cachedRowSet = null;

		ConnectionWraper wraper = getConnection();
		conn = wraper.getConnection();
		context = wraper.getContext();

		if(null!=conn){
			stmt = conn.prepareCall(getQuery());			
			if(null!=stmt){				
				stmt.setLong(1, idCandidato);
				stmt.registerOutParameter(2, OracleTypes.CURSOR); 
				stmt.execute();
				cachedRowSet = ((OracleCallableStatement)stmt).getCursor(2);

				if (cachedRowSet.next()) {
					info = new InformacionGeneralVO();
					info.setId_candidato(cachedRowSet.getLong(1));
					info.setNombre(cachedRowSet.getString(2));
					info.setApellido1(cachedRowSet.getString(3));
					info.setApellido2(cachedRowSet.getString(4));
					info.setFechaNacimiento(cachedRowSet.getDate(5)); 
					info.setCorreoElectronico(cachedRowSet.getString(6));
					info.setContactoCorreo(cachedRowSet.getInt(7));
					info.setDisponibilidadViajar(cachedRowSet.getInt(8));
					info.setDisponibilidadRadicar(cachedRowSet.getInt(9));
					info.setAcceso(cachedRowSet.getString(10));
					info.setClave(cachedRowSet.getString(11));
					info.setTelefono(cachedRowSet.getString(12));
					info.setExtension(cachedRowSet.getString(13));
					info.setNombreEntidad(cachedRowSet.getString(14));
					info.setNombreMunicipio(cachedRowSet.getString(15));
					info.setNombreColonia(cachedRowSet.getString(16));
					info.setCalle(cachedRowSet.getString(17));
					info.setNumeroExterior(cachedRowSet.getString(18));
					info.setNumeroInterior(cachedRowSet.getString(19));
					info.setCodigoPostal(cachedRowSet.getString(20));
					info.setGradoEstudios(cachedRowSet.getString(21));
					info.setEscuela(cachedRowSet.getString(25));
					info.setIdioma(cachedRowSet.getString(26));
					info.setPuestoMayorExpr(cachedRowSet.getString(27));
					info.setAreaLaboralMayorExpr(cachedRowSet.getString(28));
					info.setOcupacionMayorExpr(cachedRowSet.getString(29));
					info.setPuestoActual(cachedRowSet.getString(30));
					info.setAreaLaboralActual(cachedRowSet.getString(31));
					info.setOcupacionActual(cachedRowSet.getString(32));
					info.setEmpresaActual(cachedRowSet.getString(33));
					//Se usa logica inversa
					info.setEmpresaConfidencial(
							cachedRowSet.getInt(34) == MOSTRAR_EMPRESA.NO.getIdOpcion()
							? true : false);
					info.setAniosLaborados(cachedRowSet.getInt(35));
					info.setFunciones(cachedRowSet.getString(36));
					info.setCarreraEspecialidad(cachedRowSet.getString(37));
					info.setEdadActual(obtenEdad(cachedRowSet.getDate(5)));
					
					info.setIdEstatus(cachedRowSet.getInt("ESTATUS"))	;
					info.setDescEstatus(cachedRowSet.getString("DESC_ESTATUS"))	;
					info.setUrlVideoCV(cachedRowSet.getString(40));
					info.setUrlVideoEstatus(cachedRowSet.getInt(41));
					info.setContactoTelefono(cachedRowSet.getInt(42));
					info.setConfidencialidadCandidato(cachedRowSet.getInt(43));		
					
				}	
				
				info.setDatosCurriculum(buscarDatosCurriculum(idCandidato));
				info.getDatosCurriculum().setHistoriaLaboral(buscarHistLaboral(idCandidato));
				info.getDatosCurriculum().setGradoAcademico(buscarGrados(idCandidato));
								
				try{if (cachedRowSet!=null) cachedRowSet.close();}catch(Exception e){e.printStackTrace();}
				try{if (stmt!=null) stmt.close();}catch(Exception e){e.printStackTrace();}

				/** Se esta utilizando una sola conexion para diversas consultas, por lo tanto no se cierra **/
				if (!isGlobalConnection()){
					try{if (conn!=null) conn.close();}catch(Exception e){e.printStackTrace();}

					if (context!=null){
						try {
							context.close();
						} catch (Exception e) {e.printStackTrace();}
					}
				}		
			}
		}

		return info;
	}
		
	public List<ExpectativaLaboralVO> buscarExpecLaboral(long idCandidato, int principal) throws SQLException {
		CONSULTA_PERFIL_CANDIDATO = "BUSCAR_EXPEC_LABORAL";
		Object[] parametros = { idCandidato, principal };
		CachedRowSet cachedRowSet = executeQuery(parametros);

		List<ExpectativaLaboralVO> expecLaboralesVO = new ArrayList<ExpectativaLaboralVO>();
		ExpectativaLaboralVO expecLaboralVO = null;
		try {
			while (cachedRowSet.next()) {
				expecLaboralVO = new ExpectativaLaboralVO();
				expecLaboralVO.setIdExpectativaLaboral(cachedRowSet.getLong(1));
				expecLaboralVO.setIdSectorDeseado(cachedRowSet.getLong(2));
				expecLaboralVO.setSectorDeseado(cachedRowSet.getString(3));
				expecLaboralVO.setPuestoDeseado(cachedRowSet.getString(4));
				expecLaboralVO.setIdAreaLaboralDeseada(cachedRowSet.getLong(5));
				expecLaboralVO.setAreaLaboralDeseada(cachedRowSet.getString(6));
				expecLaboralVO.setIdOcupacionDeseada(cachedRowSet.getLong(7));
				expecLaboralVO.setOcupacionDeseada(cachedRowSet.getString(8));
				expecLaboralVO.setSalarioPretendido(cachedRowSet.getDouble(9));
				expecLaboralVO.setIdTipoEmpleoDeseado(cachedRowSet.getLong(10));
				expecLaboralVO.setTipoEmpleoDeseado(cachedRowSet.getString(11));
				expecLaboralVO.setIdTipoContrato(cachedRowSet.getLong(12));
				expecLaboralVO.setTipoContrato(cachedRowSet.getString(13));
				expecLaboralVO.setIdSubAreaLaboralDeseada(cachedRowSet.getLong(14));
				expecLaboralesVO.add(expecLaboralVO);
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new SQLException(e);
		}
		return expecLaboralesVO;
	}
	
	public List<ExpectativaLaboralVO> buscarExpecLaboral(
			long idCandidato) throws SQLException {
		CONSULTA_PERFIL_CANDIDATO = "BUSCAR_EXPEC_LABORAL_CAND";
		Object[] parametros = { idCandidato };
		CachedRowSet cachedRowSet = executeQuery(parametros);
		List<ExpectativaLaboralVO> expecLaboralesVO = new ArrayList<ExpectativaLaboralVO>();
		ExpectativaLaboralVO expecLaboralVO = null;
		try {
			while (cachedRowSet.next()) {
				expecLaboralVO = new ExpectativaLaboralVO();
				expecLaboralVO.setIdExpectativaLaboral(cachedRowSet.getLong(1));
				expecLaboralVO.setIdSectorDeseado(cachedRowSet.getLong(2));
				expecLaboralVO.setSectorDeseado(cachedRowSet.getString(3));
				expecLaboralVO.setPuestoDeseado(cachedRowSet.getString(4));
				expecLaboralVO.setIdAreaLaboralDeseada(cachedRowSet.getLong(5));
				expecLaboralVO.setAreaLaboralDeseada(cachedRowSet.getString(6));
				expecLaboralVO.setIdOcupacionDeseada(cachedRowSet.getLong(7));
				expecLaboralVO.setOcupacionDeseada(cachedRowSet.getString(8));
				expecLaboralVO.setSalarioPretendido(cachedRowSet.getDouble(9));
				expecLaboralVO.setIdTipoEmpleoDeseado(cachedRowSet.getLong(10));
				expecLaboralVO.setTipoEmpleoDeseado(cachedRowSet.getString(11));
				expecLaboralVO.setIdTipoContrato(cachedRowSet.getLong(12));
				expecLaboralVO.setTipoContrato(cachedRowSet.getString(13));
				expecLaboralVO.setPrincipal(cachedRowSet.getInt(14));
				
				expecLaboralVO.setIdExperiencia(cachedRowSet.getInt(15));
				expecLaboralesVO.add(expecLaboralVO);
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new SQLException(e);
		}
		return expecLaboralesVO;
	}
	
	public List<ExpectativaLugarVO> buscarExpecLugar(
			long idCandidato, int principal) throws SQLException {
		CONSULTA_PERFIL_CANDIDATO = "BUSCAR_EXPEC_LUGAR";
		Object[] parametros = { idCandidato, principal };
		CachedRowSet cachedRowSet = executeQuery(parametros);

		List<ExpectativaLugarVO> expecLugaresVO = new ArrayList<ExpectativaLugarVO>();
		ExpectativaLugarVO expecLugarVO = null;
		try {
			while (cachedRowSet.next()) {
				expecLugarVO = new ExpectativaLugarVO();
				expecLugarVO.setIdExpectativaLugar(cachedRowSet.getLong(1));
				expecLugarVO.setIdEntidadDeseada(cachedRowSet.getLong(2));
				expecLugarVO.setEntidadDeseada(cachedRowSet.getString(3));
				expecLugarVO.setIdMunicipioDeseado(cachedRowSet.getLong(4));
				expecLugarVO.setMunicipioDeseado(cachedRowSet.getString(5));
				expecLugaresVO.add(expecLugarVO);
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new SQLException(e);
		}
		return expecLugaresVO;
	}
	
	public List<ExpectativaLugarVO> buscarExpecLugar(long idCandidato) throws SQLException {
		CONSULTA_PERFIL_CANDIDATO = "BUSCAR_EXPEC_LUGAR_CAND";
		Object[] parametros = { idCandidato };
		CachedRowSet cachedRowSet = executeQuery(parametros);

		List<ExpectativaLugarVO> expecLugaresVO = new ArrayList<ExpectativaLugarVO>();
		ExpectativaLugarVO expecLugarVO = null;
		try {
			while (cachedRowSet.next()) {
				expecLugarVO = new ExpectativaLugarVO();
				expecLugarVO.setIdExpectativaLugar(cachedRowSet.getLong(1));
				expecLugarVO.setIdEntidadDeseada(cachedRowSet.getLong(2));
				expecLugarVO.setEntidadDeseada(cachedRowSet.getString(3));
				expecLugarVO.setIdMunicipioDeseado(cachedRowSet.getLong(4));
				expecLugarVO.setMunicipioDeseado(cachedRowSet.getString(5));
				expecLugaresVO.add(expecLugarVO);
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new SQLException(e);
		}
		return expecLugaresVO;
	}

	
	public Candidate buscarCandidateIndex(
            long idCandidato) throws SQLException {
        CONSULTA_PERFIL_CANDIDATO = "CANDIDATE_INDEX";
        Object[] parametros = { idCandidato };
        CachedRowSet cachedRowSet = executeQuery(parametros);
        Candidate candidato = new Candidate();
        try {
            if (cachedRowSet.next()) {               
				 candidato.setIdCandidato(cachedRowSet.getLong("ID_CANDIDATO"));				               
				 candidato.setEdad(obtenEdad(cachedRowSet.getDate("FECHA_NACIMIENTO")));
				 candidato.setDisponibilidadRadicar(cachedRowSet.getLong("DISPONIBILIDAD_RADICAR") == Constantes.DISPONIBILIDAD_RADICAR.SI.getIdOpcion());
				 candidato.setDisponibilidad_viajar_ciudad(cachedRowSet.getLong("DISPONIBILIDAD_VIAJAR") == Constantes.DISPONIBILIDAD_VIAJAR.SI.getIdOpcion());
				               
				 candidato.setExperiencia(cachedRowSet.getInt("EXPERIENCIA"));
                candidato.setIndicador_estudios(cachedRowSet.getInt("INDICADOR_ESTUDIOS") == Constantes.ESCOLARIDAD.CON_ESTUDIOS.getIdOpcion());
                candidato.setOcupacion(cachedRowSet.getInt("OCUPACION"));
                candidato.setSalario(cachedRowSet.getLong("SALARIO"));
				               
				 candidato.setTipoEmpleo(cachedRowSet.getInt("TIPO_EMPLEO"));
				 candidato.setPalabras(((null != cachedRowSet.getString("ENTIDAD") ? cachedRowSet.getString("ENTIDAD") + " " : "") + (null != cachedRowSet.getString("MUNICIPIO") ? 
				cachedRowSet.getString("MUNICIPIO") : "")).trim());
                return candidato;
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new SQLException(e);
        }
        return candidato;
    }

	
	public int getEstatusCVcandidato(long idCandidato) throws SQLException {
		CONSULTA_PERFIL_CANDIDATO = "EVALUA_CV";
		
		Object[] parametros = { idCandidato };
		CachedRowSet cachedRowSet = executeQuery(parametros);
		try {
			if (cachedRowSet.next()) {
				int estatusCV = cachedRowSet.getInt("CV");
				return estatusCV;
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new SQLException(e);
		}
		
		return 0;
	}
	
	
	@Override
	protected String getQuery() {
		if (CONSULTA_PERFIL_CANDIDATO.equals("BUSCAR_PERFIL"))
			return this.setQueryPerfil();
		else if (CONSULTA_PERFIL_CANDIDATO.equals("BUSCAR_OTROS_MEDIOS"))
			return this.setQueryOtrosMedios();
		else if (CONSULTA_PERFIL_CANDIDATO.equals("BUSCAR_PERFIL_CANDIDATO"))
			return this.setQueryPerfilCandidato();
		else if (CONSULTA_PERFIL_CANDIDATO.equals("BUSCAR_GRADOS"))
			return this.setQueryGrados();
		else if (CONSULTA_PERFIL_CANDIDATO.equals("BUSCAR_GRADOS_CAND"))
			return this.setQueryGradosCandidato();
		else if (CONSULTA_PERFIL_CANDIDATO.equals("BUSCAR_IDIOMAS"))
			return this.setQueryIdiomas();
		else if (CONSULTA_PERFIL_CANDIDATO.equals("BUSCAR_IDIOMAS_CAND"))
			return this.setQueryIdiomasCandidato();
		else if (CONSULTA_PERFIL_CANDIDATO.equals("BUSCAR_CONOCHABS"))
			return this.setQueryConocimientosHabilidades();
		else if (CONSULTA_PERFIL_CANDIDATO.equals("BUSCAR_CONOCHABS_CAND"))
			return this.setQueryConocimientosHabilidadesCandidato();
		else if (CONSULTA_PERFIL_CANDIDATO.equals("MIS_CANDIDATOS"))
			return this.setQueryMisCandidatos();
		else if (CONSULTA_PERFIL_CANDIDATO.equals("CARPETA_EMPRESARIAL"))
			return this.setQueryCarpetaEmpresarial();		
		else if (CONSULTA_PERFIL_CANDIDATO.equals("BUSCAR_COMPU_AVANZADA"))
			return this.setQueryCompuAvanzada();
		else if (CONSULTA_PERFIL_CANDIDATO.equals("BUSCAR_COMPU_AVANZADA_CAND"))
			return this.setQueryCompuAvanzadaCandidato();
		else if (CONSULTA_PERFIL_CANDIDATO.equals("BUSCAR_HIST_LABORAL"))
			return this.setQueryDetallePuesto();
		else if (CONSULTA_PERFIL_CANDIDATO.equals("BUSCAR_HIST_LABORAL_CAND"))
			return this.setQueryDetallePuestoCandidato();
		else if (CONSULTA_PERFIL_CANDIDATO.equals("BUSCAR_DETALLE")) {
			return this.setQueryInformacion();
		}
		else if (CONSULTA_PERFIL_CANDIDATO.equals("BUSCAR_EXPEC_LABORAL"))
			return this.setQueryExpecLaboral();
		else if (CONSULTA_PERFIL_CANDIDATO.equals("BUSCAR_EXPEC_LABORAL_CAND"))
			return this.setQueryExpecLaboralCandidato();
		else if (CONSULTA_PERFIL_CANDIDATO.equals("BUSCAR_EXPEC_LUGAR"))
			return this.setQueryExpecLugar();
		else if (CONSULTA_PERFIL_CANDIDATO.equals("BUSCAR_EXPEC_LUGAR_CAND"))
			return this.setQueryExpecLugarCandidato();
		else if (CONSULTA_PERFIL_CANDIDATO.equals("BUSCAR_ESTATUS_OFERTA_CANDIDATO"))
			return this.setQueryBuscarEstatusOfertaCandidato();
		else if (CONSULTA_PERFIL_CANDIDATO.equals("BUSCAR_PERFIL_CANDIDATO"))
			return this.setQueryPerfilCandidato();
		else if (CONSULTA_PERFIL_CANDIDATO.equals("BUSCAR_CANDIDATO_FECHA_ALTA"))
			return this.setQueryBuscarCandidatoFechaAlta();
		else if (CONSULTA_PERFIL_CANDIDATO.equals("BUSCAR_CANDIDATO_FECHA_NACIMIENTO"))
			return this.setQueryBuscarCandidatoFechaNacimiento();
		else if (CONSULTA_PERFIL_CANDIDATO.equals("BUSCAR_CANDIDATO_NOMBRE_FECHA"))
			return this.setQueryBuscarCandidatoNombreFechaNacimiento();
		else if (CONSULTA_PERFIL_CANDIDATO.equals("BUSCAR_CANDIDATO_APELLIDO_FECHA"))
			return this.setQueryBuscarCandidatoApellidoFechaNacimiento();		
		else if (CONSULTA_PERFIL_CANDIDATO.equals("BUSCAR_CANDIDATO_APELLIDO"))
			return this.setQueryBuscarCandidatoApellido();
		else if (CONSULTA_PERFIL_CANDIDATO.equals("BUSCAR_CANDIDATO_NOMBRE"))
			return this.setQueryBuscarCandidatoNombre();
		else if (CONSULTA_PERFIL_CANDIDATO.equals("BUSCAR_CANDIDATO_NOMBRE_APELLIDO"))
			return this.setQueryBuscarCandidatoNombreApellido();
		else if (CONSULTA_PERFIL_CANDIDATO.equals("BUSCAR_CANDIDATO_NOMBRE_APELLIDO_FECHA_ALTA"))
			return this.setQueryBuscarCandidatoNombreApellidoFechaAlta();
		else if (CONSULTA_PERFIL_CANDIDATO.equals("BUSCAR_CANDIDATO_NOMBRE_APELLIDO_FECHA_NACIMIENTO"))
			return this.setQueryBuscarCandidatoNombreApellidoFechaNacimiento();
		else if (CONSULTA_PERFIL_CANDIDATO.equals("BUSCAR_CANDIDATO_NOMBRE_APELLIDO_FECHA_NACIMIENTO_ALTA"))
			return this.setQueryBuscarCandidatoNombreApellidoFechaNacimientoAlta();
		else if (CONSULTA_PERFIL_CANDIDATO.equals("BUSCAR_EMPLEO_ACTUAL"))
			return this.setQueryTrabajoActual();
		else if (CONSULTA_PERFIL_CANDIDATO.equals("BUSCAR_EMPLEO_ACTUAL_O_ULTIMO"))
			return this.setQueryTrabajoActualOUltimo();
		else if (CONSULTA_PERFIL_CANDIDATO.equals("MEDIOS_BUSQUEDA"))
			return this.setQueryMediosBusqueda();
		else if (CONSULTA_PERFIL_CANDIDATO.equals("DETALLE_CANDIDATO"))
			return this.setQueryCandidatos();
		else if (CONSULTA_PERFIL_CANDIDATO.equals("CANDIDATE_INDEX"))
			return this.setQueryCandidate();
		else if (CONSULTA_PERFIL_CANDIDATO.equals("EVALUA_CV"))
			return this.setQueryEvaluaCV();
		else if (CONSULTA_PERFIL_CANDIDATO.equals("DETALLE_CANDIDATO_OFERTA"))
			return this.setQueryOfertaCandidato();
		else if (CONSULTA_PERFIL_CANDIDATO.equals("DETALLE_CANDIDATO_ESTILO_CV"))
			return this.setQueryPerfilEstiloCv();
		else if (CONSULTA_PERFIL_CANDIDATO.equals("ACTUALIZA_CANDIDATO_ESTILO_CV"))
			return this.setQueryActualizaPerfilEstiloCv();		
		else if (CONSULTA_PERFIL_CANDIDATO.equals("ELIMINANDO_HISTORIA_LABORAL"))
			return this.setQueryHistoriaLaboral();				
		else if (CONSULTA_PERFIL_CANDIDATO.equals("BUSCAR_CANDIDATO_FUERA_DE_VIGENCIA"))
			return this.setQueryIdCandidatosFueraDeVigencia();
		else if (CONSULTA_PERFIL_CANDIDATO.equals("BUSCAR_CANDIDATO_AVISO_FUERA_DE_VIGENCIA"))
			return this.setQueryCandidatosAvisoDeVigencia();			
		else if (CONSULTA_PERFIL_CANDIDATO.equals("BUSCAR_POSTULANTE_EMPRESA_DESACTIVADA"))
			return this.setQueryPostulantesEmpresaDesactivada();	
		else if (CONSULTA_PERFIL_CANDIDATO.equals("CONSULTA_ESTATUS_OFERTA_CANDIDATO"))
			return this.setQueryEstatusOfertaCandidato();	
		else if (CONSULTA_PERFIL_CANDIDATO.equals("DATOS_CURRICULUM"))
			return this.getDatosCurriculum();	
		
		return null;
	}

	private String setQueryPerfil() {
		StringBuffer sqlString = new StringBuffer();
        sqlString.append("SELECT c.id_usuario, c.id_oficina, s.curp, s.nombre, ");
        sqlString.append("s.apellido1, s.apellido2, s.genero, ");
        sqlString.append("s.fecha_nacimiento, ");
        sqlString.append("c.id_entidad_nacimiento, ");
        sqlString.append("F_DESC_CATALOGO(").append(CATALOGO_OPCION_ENTIDAD_FEDERATIVA).append(", c.id_entidad_nacimiento), ");
        sqlString.append("c.id_estado_civil, c.id_tipo_discapacidad , ");
        sqlString.append("c.confidencialidad_datos, ");
        sqlString.append("p.contacto_correo, p.contacto_telefono, ");
        sqlString.append("p.horario_contacto_de, p.horario_contacto_a, ");
        sqlString.append("p.id_recibe_oferta, p.empleado_actualmente, ");
        sqlString.append("p.id_razon_busqueda, p.descripcion_otro_motivo_busq,  p.inicio_busqueda, ");
        sqlString.append("s.correo_electronico, c.estilo_cv, c.id_medio_portal ");
        //PPC-SD
        sqlString.append(",c.ppc_estatus,c.apoyo_prospera, c.discapacidades, p.licencia_id, c.folio_prospera, ");
        sqlString.append("c.folio_integrante_prospera, p.disponibilidad_radicar_pais, to_char(FECHA_ULTIMA_ACTUALIZACION,'DD-MM-YYYY') as fUltAct ");
        sqlString.append("FROM ((CANDIDATO c LEFT JOIN PERFIL_LABORAL p ");
        sqlString.append("ON c.id_candidato = p.id_candidato) LEFT JOIN SOLICITANTE s ");
        sqlString.append("ON c.id_candidato = s.id_candidato)" );
        sqlString.append("WHERE c.id_candidato = ?");

        logger.debug(sqlString.toString());
		return sqlString.toString();
	}
	
	private String setQueryPerfilEstiloCv() {
		StringBuffer sqlString = new StringBuffer();
		sqlString.append("SELECT estilo_cv ");
		sqlString.append("FROM CANDIDATO ");
		sqlString.append("WHERE id_candidato = ? ");
		logger.debug(sqlString.toString());
		return sqlString.toString();
	}	
	
	private String setQueryActualizaPerfilEstiloCv() {
		StringBuffer sqlString = new StringBuffer();
		sqlString.append("UPDATE CANDIDATO ");
		sqlString.append("SET estilo_cv = ? ");
		sqlString.append("WHERE id_candidato = ? ");
		logger.debug(sqlString.toString());
		return sqlString.toString();
	}		
	
	private String setQueryOtrosMedios() {
		StringBuffer sqlString = new StringBuffer();
		sqlString.append("SELECT om.id_otro_medio, ");
		sqlString.append("om.id_medio_busqueda ");
		sqlString.append("FROM OTRO_MEDIO om ");
		sqlString.append("WHERE om.id_candidato = ? ");
		sqlString.append("ORDER BY 1");
		logger.debug(sqlString.toString());
		return sqlString.toString();
	}
	
	private String setQueryPerfilCandidato() {
		StringBuffer sqlString = new StringBuffer();
		sqlString.append("SELECT p.disponibilidad_viajar, ");
		sqlString.append("p.disponibilidad_radicar, ");
		sqlString.append("p.id_experiencia_total, ");
		sqlString.append("p.id_sector_mayor_expr, ");
		sqlString.append("p.puesto_mayor_expr, ");
		sqlString.append("p.id_area_laboral_mayor_expr, ");
		sqlString.append("p.id_ocupacion_mayor_expr, ");
		sqlString.append("p.computacion_basica, ");
		sqlString.append("p.id_experiencia_compu, ");
		sqlString.append("p.id_dominio_compu, ");
		sqlString.append("p.id_experiencia_office, ");
		sqlString.append("p.id_dominio_office, ");
		sqlString.append("p.id_experiencia_internet, ");
		sqlString.append("p.id_dominio_internet, ");
		sqlString.append("p.computacion_avanzada, ");
		sqlString.append("p.sin_estudios, ");
		sqlString.append("p.sin_experiencia, ");
		sqlString.append("p.experiencia ");
		sqlString.append("FROM PERFIL_LABORAL p ");
		sqlString.append("WHERE p.id_candidato = ?");	
		logger.debug(sqlString.toString());
		return sqlString.toString();
	}

	private String setQueryGrados() {
		StringBuffer sqlString = new StringBuffer();
		sqlString.append("SELECT cga.id_candidato_grado_academico, ");
		sqlString.append("cga.id_nivel_estudio, ");
		sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_GRADO_ESTUDIOS
				+ ", cga.id_nivel_estudio), ");
		sqlString.append("cga.id_situacion_academica, ");
		sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_ESTATUS_GRADO
				+ ", cga.id_situacion_academica), ");
		sqlString.append("cga.id_carrera_especialidad, ");
		sqlString.append("F_DESC_CATASOCIADO(" + CATALOGO_OPCION_GRADO_ESTUDIOS
				+ ", cga.id_nivel_estudio, cga.id_carrera_especialidad), ");
		sqlString.append("cga.escuela, cga.inicio, cga.fin, cga.fecha_inicio, cga.fecha_fin, cga.lugar ");
		sqlString.append("FROM CANDIDATO_GRADO_ACADEMICO cga ");
		sqlString.append("WHERE cga.id_candidato = ? ");
		sqlString.append("AND cga.principal = ? ");
		sqlString.append("ORDER BY cga.inicio DESC");
		logger.debug(sqlString.toString());
		return sqlString.toString();
	}
	
	private String setQueryGradosCandidato() {
		StringBuffer sqlString = new StringBuffer();
		sqlString.append("SELECT cga.id_candidato_grado_academico, ");
		sqlString.append("cga.id_nivel_estudio, ");
		sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_GRADO_ESTUDIOS
				+ ", cga.id_nivel_estudio), ");
		sqlString.append("cga.id_situacion_academica, ");
		sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_ESTATUS_GRADO
				+ ", cga.id_situacion_academica), ");
		sqlString.append("cga.id_carrera_especialidad, ");
		sqlString.append("F_DESC_CATASOCIADO(" + CATALOGO_OPCION_GRADO_ESTUDIOS
				+ ", cga.id_nivel_estudio, cga.id_carrera_especialidad), ");
		sqlString.append("cga.escuela, cga.inicio, cga.fin ");
		sqlString.append("FROM CANDIDATO_GRADO_ACADEMICO cga ");
		sqlString.append("WHERE cga.id_candidato = ? ");
		sqlString.append("ORDER BY cga.inicio DESC");
		logger.debug(sqlString.toString());
		return sqlString.toString();
	}
	
	private String setQueryMisCandidatos() {   
		StringBuffer sqlString = new StringBuffer();

		sqlString.append("BEGIN ");
		sqlString.append(" SP_MIS_CANDIDATOS ");
		sqlString.append(" ("+ CATALOGO_OPCION_GRADO_ESTUDIOS +", ");
		sqlString.append(" "+ CATALOGO_OPCION_OCUPACION +", ");
		sqlString.append(" "+ CATALOGO_OPCION_ENTIDAD_FEDERATIVA +", ");
		sqlString.append(" "+ CATALOGO_OPCION_ESTATUS +", ");
		sqlString.append(" "+ CATALOGO_OPCION_TIPO_CONTRATO +", ");
		sqlString.append(" "+ CATALOGO_OPCION_TIPO_EMPLEO +", ");
		sqlString.append(" ?, ");
		sqlString.append(" ?, ");
		sqlString.append(" "+ MULTIREGISTRO.PRINCIPAL.getIdOpcion() +", ");
		sqlString.append(" "+ CATALOGO_OPCION_DOMICILIO_CANDIDATO +", ");
		sqlString.append(" ?, ");
		sqlString.append(" ?, ");
		sqlString.append(" ? ); ");
		sqlString.append("END;");

		return sqlString.toString();
	}
	
	private String setQueryCarpetaEmpresarial() {   
		StringBuilder sqlString = new StringBuilder();
		sqlString.append("BEGIN ");
		sqlString.append(" SP_CARPETA_EMPRESARIAL ");
		sqlString.append(" ("+ CATALOGO_OPCION_GRADO_ESTUDIOS +", ");
		sqlString.append(" "+ CATALOGO_OPCION_OCUPACION +", ");
		sqlString.append(" "+ CATALOGO_OPCION_ENTIDAD_FEDERATIVA +", ");
		sqlString.append(" "+ CATALOGO_OPCION_ESTATUS +", ");
		sqlString.append(" "+ CATALOGO_OPCION_TIPO_CONTRATO +", ");
		sqlString.append(" "+ CATALOGO_OPCION_TIPO_EMPLEO +", ");
		sqlString.append(" ?, ");
		sqlString.append(" ?, ");
		sqlString.append(" "+ MULTIREGISTRO.PRINCIPAL.getIdOpcion() +", ");
		sqlString.append(" "+ CATALOGO_OPCION_DOMICILIO_CANDIDATO +", ");
		sqlString.append(" ?, ");
		sqlString.append(" ?, ");
		sqlString.append(" ?, ");
		sqlString.append(" ? ); ");
		sqlString.append("END;");
		return sqlString.toString();
	}
	
	
	private String setQueryIdiomas() {
		StringBuffer sqlString = new StringBuffer();
		sqlString.append("SELECT ci.id_candidato_idioma, ");
		sqlString.append("ci.id_idioma, ");
		sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_IDIOMAS
				+ ", ci.id_idioma), ");
		sqlString.append("ci.id_certificacion, ");
		sqlString.append("F_DESC_CATASOCIADO(" + CATALOGO_OPCION_IDIOMAS
				+ ", ci.id_idioma, ci.id_certificacion), ");
		sqlString.append("ci.id_dominio, ");
		sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_DOMINIO
				+ ", ci.id_dominio)");
		sqlString.append(" FROM CANDIDATO_IDIOMA ci ");
		sqlString.append("WHERE ci.id_candidato = ? ");
		sqlString.append("AND ci.principal = ? ");
		sqlString.append("ORDER BY 1");
		logger.debug(sqlString.toString());
		return sqlString.toString();
		
	}
	
	private String setQueryIdiomasCandidato() {
		StringBuffer sqlString = new StringBuffer();
		sqlString.append("SELECT ci.id_candidato_idioma, ");
		sqlString.append("ci.id_idioma, ");
		sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_IDIOMAS
				+ ", ci.id_idioma), ");
		sqlString.append("ci.id_certificacion, ");
		sqlString.append("F_DESC_CATASOCIADO(" + CATALOGO_OPCION_IDIOMAS
				+ ", ci.id_idioma, ci.id_certificacion), ");
		sqlString.append("ci.id_dominio, ");
		sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_DOMINIO
				+ ", ci.id_dominio), ");
		sqlString.append("ci.principal ");
		sqlString.append(",nvl(id_Dominio_Habla,0) as  hablar");
		sqlString.append(",nvl(id_Dominio_Escrito,0) as  escribir");
		sqlString.append(",nvl(id_Dominio_Lectura,0) as  leer");
		sqlString.append(",nvl(id_Dominio_Comprension,0) as  comprender ");
		sqlString.append("FROM CANDIDATO_IDIOMA ci ");
		sqlString.append("WHERE ci.id_candidato = ? ");
		sqlString.append("ORDER BY 1");
		logger.debug(sqlString.toString());
		return sqlString.toString();
	}

	private String setQueryConocimientosHabilidades() {
		StringBuffer sqlString = new StringBuffer();
		sqlString.append("SELECT cch.id_candidato_conocim_habilidad, ");
		sqlString.append("cch.id_tipo_conocim_habilidad, ");
		sqlString.append("cch.conocimiento_habilidad, ");
		sqlString.append("cch.id_experiencia, ");
		sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_EXPERIENCIA
				+ ", cch.id_experiencia), ");
		sqlString.append("cch.id_dominio, ");
		sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_DOMINIO
				+ ", cch.id_dominio), ");
		sqlString.append("cch.descripcion, cch.principal ");
		sqlString.append("FROM CANDIDATO_CONOCIM_HABILIDAD cch ");
		sqlString.append("WHERE cch.id_candidato = ? ");
		sqlString.append("AND cch.id_tipo_conocim_habilidad = ? ");
		sqlString.append("AND cch.principal = ? ");
		sqlString.append("ORDER BY 1");
		logger.debug(sqlString.toString());
		return sqlString.toString();
	}
	
	private String setQueryConocimientosHabilidadesCandidato() {
		StringBuffer sqlString = new StringBuffer();
		sqlString.append("SELECT cch.id_candidato_conocim_habilidad, ");
		sqlString.append("cch.id_tipo_conocim_habilidad, ");
		sqlString.append("cch.conocimiento_habilidad, ");
		sqlString.append("cch.id_experiencia, ");
		sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_EXPERIENCIA
				+ ", cch.id_experiencia), ");
		sqlString.append("cch.id_dominio, ");
		sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_DOMINIO
				+ ", cch.id_dominio), ");
		sqlString.append("cch.descripcion, cch.principal ");
		sqlString.append("FROM CANDIDATO_CONOCIM_HABILIDAD cch ");
		sqlString.append("WHERE cch.id_candidato = ? ");
		sqlString.append("AND cch.id_tipo_conocim_habilidad = ? ");
		sqlString.append("ORDER BY 1");
		logger.debug(sqlString.toString());
		return sqlString.toString();
	}
	
	private String setQueryCompuAvanzada() {
		StringBuffer sqlString = new StringBuffer();
		sqlString.append("SELECT cca.id_candidato_compu_avanzada, ");
		sqlString.append("cca.software_hardware, ");
		sqlString.append("cca.id_experiencia, ");
		sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_EXPERIENCIA
				+ ", cca.id_experiencia), ");
		sqlString.append("cca.id_dominio, ");
		sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_DOMINIO
				+ ", cca.id_dominio), ");
		sqlString.append("cca.descripcion ");
		sqlString.append("FROM CANDIDATO_COMPU_AVANZADA cca ");
		sqlString.append("WHERE cca.id_candidato = ? ");
		sqlString.append("AND cca.principal = ? ");
		sqlString.append("ORDER BY 1");
		logger.debug(sqlString.toString());
		return sqlString.toString();
	}
	
	private String setQueryCompuAvanzadaCandidato() {
		StringBuffer sqlString = new StringBuffer();
		sqlString.append("SELECT cca.id_candidato_compu_avanzada, ");
		sqlString.append("cca.software_hardware, ");
		sqlString.append("cca.id_experiencia, ");
		sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_EXPERIENCIA
				+ ", cca.id_experiencia), ");
		sqlString.append("cca.id_dominio, ");
		sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_DOMINIO
				+ ", cca.id_dominio), ");
		sqlString.append("cca.descripcion ");
		sqlString.append("FROM CANDIDATO_COMPU_AVANZADA cca ");
		sqlString.append("WHERE cca.id_candidato = ? ");
		sqlString.append("ORDER BY 1");
		logger.debug(sqlString.toString());
		return sqlString.toString();
	}
	
	private String setQueryDetallePuesto() {
		StringBuffer sqlString = new StringBuffer();
		sqlString.append("SELECT hl.id_historial_laboral, ");
		sqlString.append("hl.trabajo_actual, ");
		sqlString.append("hl.id_sector, ");
		sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_SUBSECTOR + 
				", hl.id_sector), ");
		sqlString.append("hl.puesto, hl.id_area_laboral, ");
		sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_AREA_LABORAL + 
				", hl.id_area_laboral), ");
		sqlString.append("hl.id_ocupacion, ");
		sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_OCUPACION + 
				", hl.id_ocupacion), ");
		sqlString.append("hl.empresa, ");
		sqlString.append("hl.confidencialidad_empresa, ");
		sqlString.append("hl.labores_inicial, ");
		sqlString.append("hl.labores_final, ");
		sqlString.append("hl.anios_laborados, ");
		sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_EXPERIENCIA + 
				", hl.anios_laborados), ");
		sqlString.append("hl.id_jerarquia, ");
		sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_JERARQUIA_PUESTO + 
				", hl.id_jerarquia), ");
		sqlString.append("hl.personas_cargo, ");
		sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_PERSONAS_CARGO + 
				", hl.personas_cargo), ");
		sqlString.append("hl.salario_mensual, ");
		sqlString.append("hl.funcion, ");
		sqlString.append("hl.logro ");
		sqlString.append("FROM HISTORIA_LABORAL hl ");
		sqlString.append("WHERE hl.id_candidato = ? ");
		sqlString.append("AND hl.principal = ? ");
		sqlString.append("ORDER BY 1");
		logger.debug(sqlString.toString());
		return sqlString.toString();
	}
	
	private String setQueryDetallePuestoCandidato() {
		StringBuffer sqlString = new StringBuffer();
		sqlString.append("SELECT hl.id_historial_laboral, ");
		sqlString.append("hl.trabajo_actual, ");
		sqlString.append("hl.id_sector, ");
		sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_SUBSECTOR + 
				", hl.id_sector), ");
		sqlString.append("hl.puesto, hl.id_area_laboral, ");
		sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_AREA_LABORAL + 
				", hl.id_area_laboral), ");
		sqlString.append("hl.id_ocupacion, ");
		sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_OCUPACION + 
				", hl.id_ocupacion), ");
		sqlString.append("hl.empresa, ");
		sqlString.append("hl.confidencialidad_empresa, ");
		sqlString.append("hl.labores_inicial, ");
		sqlString.append("hl.labores_final, ");
		sqlString.append("hl.anios_laborados, ");
		sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_EXPERIENCIA + 
				", hl.anios_laborados), ");
		sqlString.append("hl.id_jerarquia, ");
		sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_JERARQUIA_PUESTO + 
				", hl.id_jerarquia), ");
		sqlString.append("hl.personas_cargo, ");
		sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_PERSONAS_CARGO + 
				", hl.personas_cargo), ");
		sqlString.append("hl.salario_mensual, ");
		sqlString.append("hl.funcion, ");
		sqlString.append("hl.logro ");
		sqlString.append("FROM HISTORIA_LABORAL hl ");
		sqlString.append("WHERE hl.id_candidato = ? ");
		sqlString.append("ORDER BY 12 DESC");
		logger.debug(sqlString.toString());
		return sqlString.toString();
	}
	
	private String setQueryExpecLaboral() {
		StringBuffer sqlString = new StringBuffer();
			sqlString.append("SELECT el.id_expectativa_laboral, ");
			sqlString.append("el.id_sector_deseado, ");
			sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_SUBSECTOR + 
					", el.id_sector_deseado), ");
			sqlString.append("el.puesto_deseado, el.id_area_laboral_deseada, ");
			sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_AREA_LABORAL + 
					", el.id_area_laboral_deseada), ");
			sqlString.append("el.id_ocupacion_deseada, ");
			sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_OCUPACION + 
					", el.id_ocupacion_deseada), ");
			sqlString.append("el.salario_pretendido, ");
			sqlString.append("el.id_tipo_empleo_deseado, ");
			sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_TIPO_EMPLEO + 
					", el.id_tipo_empleo_deseado), ");
			sqlString.append("el.id_tipo_contrato, ");
			sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_TIPO_CONTRATO + 
					", el.id_tipo_contrato), ");
			sqlString.append("el.id_sub_area_laboral_deseada ");
			sqlString.append("FROM EXPECTATIVA_LABORAL el ");
			sqlString.append("WHERE el.id_candidato = ? ");
			sqlString.append("AND el.principal = ? ");
			sqlString.append("ORDER BY 1");
			logger.debug(sqlString.toString());
		return sqlString.toString();
	}
	
	// Regresando los expectativos laboral ordenado por principal, id_expectativa_laboral
	private String setQueryExpecLaboralCandidato() {
		StringBuffer sqlString = new StringBuffer();
		sqlString.append("SELECT el.id_expectativa_laboral, ");
		sqlString.append("el.id_sector_deseado, ");
		sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_SUBSECTOR + 
				", el.id_sector_deseado), ");
		sqlString.append("el.puesto_deseado, el.id_area_laboral_deseada, ");
		sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_AREA_LABORAL + 
				", el.id_area_laboral_deseada), ");
		sqlString.append("el.id_ocupacion_deseada, ");
		sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_OCUPACION + 
				", el.id_ocupacion_deseada), ");
		sqlString.append("el.salario_pretendido, ");
		sqlString.append("el.id_tipo_empleo_deseado, ");
		sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_TIPO_EMPLEO + 
				", el.id_tipo_empleo_deseado), ");
		sqlString.append("el.id_tipo_contrato, ");
		sqlString.append("F_DESC_CATALOGO(" + CATALOGO_OPCION_TIPO_CONTRATO + 
				", el.id_tipo_contrato), el.principal, el.id_experiencia ");
		sqlString.append("FROM EXPECTATIVA_LABORAL el ");
		sqlString.append("WHERE el.id_candidato = ? ");
		sqlString.append("ORDER BY 14,1");
		logger.debug(sqlString.toString());
		return sqlString.toString();
	}
	
	private String setQueryExpecLugar() {
		StringBuffer sqlString = new StringBuffer();
		sqlString.append("SELECT el.id_expectativa_lugar, ");
		sqlString.append("el.id_entidad_deseada, ");
		sqlString.append("F_DESC_CATALOGO("
				+ CATALOGO_OPCION_ENTIDAD_FEDERATIVA
				+ ", el.id_entidad_deseada), ");
		sqlString.append("el.id_municipio_deseado, ");
		sqlString.append("m.municipio ");
		sqlString.append("FROM EXPECTATIVA_LUGAR el INNER JOIN MUNICIPIO m ");
		sqlString.append("ON el.id_candidato = ? ");
		sqlString.append("AND el.principal = ? ");
		sqlString.append("AND el.id_entidad_deseada = m.id_entidad ");
		sqlString.append("AND el.id_municipio_deseado = m.id_municipio ");
		sqlString.append("ORDER BY 1");
		logger.debug(sqlString.toString());
		return sqlString.toString();
	}
	
	private String setQueryExpecLugarCandidato() {
		StringBuffer sqlString = new StringBuffer();
		sqlString.append("SELECT el.id_expectativa_lugar, ");
		sqlString.append("el.id_entidad_deseada, ");
		sqlString.append("F_DESC_CATALOGO("
				+ CATALOGO_OPCION_ENTIDAD_FEDERATIVA
				+ ", el.id_entidad_deseada), ");
		sqlString.append("el.id_municipio_deseado, ");
		sqlString.append("m.municipio ");
		sqlString.append("FROM EXPECTATIVA_LUGAR el INNER JOIN MUNICIPIO m ");
		sqlString.append("ON el.id_candidato = ? ");
		sqlString.append("AND el.id_entidad_deseada = m.id_entidad ");
		sqlString.append("AND el.id_municipio_deseado = m.id_municipio ");
		sqlString.append("ORDER BY 1");
		logger.debug(sqlString.toString());
		return sqlString.toString();
	}
	
	/**
	 * Genera la cadena que representa la consulta a base de datos para obtener
	 * la secci&oacute;n &quot;Informaci&oacute;n del Candidato&quot; del detalle
	 * del candidato.
	 * @return un objeto {@code String} que representa la consulta parametrizada 
	 * a base de datos para obtener la informaci&oacute;n especificada.
	 */
	private String setQueryInformacion() {
		
		StringBuilder query = new StringBuilder();
		query.append("BEGIN ");
		query.append(" SP_CANDIDATO_INFORMACION ");
		query.append(" ("+ CATALOGO_OPCION_ENTIDAD_FEDERATIVA +", ");
		query.append(" "+ CATALOGO_OPCION_GRADO_ESTUDIOS +", ");
		query.append(" "+ CATALOGO_OPCION_ESTATUS_GRADO +", ");
		query.append(" "+ CATALOGO_OPCION_IDIOMAS +", ");
		query.append(" "+ CATALOGO_OPCION_AREA_LABORAL +", ");
		query.append(" "+ CATALOGO_OPCION_OCUPACION +", ");
		query.append(" "+ CATALOGO_OPCION_ESTATUS +", ");
		query.append(" "+ Constantes.TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario() +", ");
		query.append(" "+ Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion() +", ");
		query.append(" "+ TRABAJA_ACTUALMENTE.SI.getIdOpcion() +", ");
		query.append(" ?, ");
		query.append(" ? ); ");
		query.append("END;");
		logger.debug(query.toString());
		return query.toString();
	}

	private String setQueryBuscarEstatusOfertaCandidato() {
		StringBuffer sqlString = new StringBuffer();
		sqlString.append(" SELECT estatus FROM OFERTA_CANDIDATO WHERE id_candidato = ? and id_oferta_empleo = ?");
	
		logger.debug(sqlString.toString());
		return sqlString.toString();
	}

	public List<CandidatoVo> filtrarCandidatos(String nombre, String apellido1, 
			Date fechaNacimiento, String curp, String correoElectronico, String apellido2, String telefono, long idEntidad, long idMunicipio, String domicilio, String usuario) throws SQLException {
		
		Object[] parametros;
		List<Object> filtros = new ArrayList<Object>();
		int numeroParametros = 0;
		
		if(nombre != null && !nombre.equalsIgnoreCase("")){
			numeroParametros++;
			filtros.add("%"+nombre+"%");
		}
		if(apellido1 != null && !apellido1.equalsIgnoreCase("")){
			numeroParametros++;
			filtros.add("%"+apellido1+"%");			
		}
		if(apellido2 != null && !apellido2.equalsIgnoreCase("")){
			numeroParametros++;
			filtros.add("%"+apellido2+"%");				
		}		
		if(fechaNacimiento != null){
			numeroParametros++;
			filtros.add(fechaNacimiento);
		}
		if(curp != null && !curp.equalsIgnoreCase("")){
			numeroParametros++;
			if (curp.length() < 18)
				filtros.add("%"+curp+"%");
			else
				filtros.add(curp);
		}
		if(correoElectronico != null && !correoElectronico.equalsIgnoreCase("")){
			
			numeroParametros++;
			
			if(correoElectronico.contains("_")){
				String correoElectronicoEscapado = correoElectronico.replaceAll("_", "\\_");
				filtros.add("%"+correoElectronicoEscapado+"%");	

			} else {
				filtros.add("%"+correoElectronico+"%");	
			}					
		}
		
		parametros = new Object[numeroParametros];
		
		
		for(int count = 0; count < filtros.size(); count++){
			parametros[count] = filtros.get(count);
		}
		
		 StringBuffer sqlString = new StringBuffer();
         sqlString.append( "SELECT sol.curp, sol.fecha_registro, sol.fecha_nacimiento, " );
         sqlString.append( " sol.id_candidato, sol.nombre, sol.apellido1, sol.apellido2, sol.correo_electronico ");
         sqlString.append( " FROM solicitante sol ");
         
         if((usuario != null && !usuario.isEmpty())
        		 || (telefono != null && !telefono.isEmpty()) 
        		 || (idEntidad > 0 || idMunicipio > 0 || (domicilio != null && !domicilio.isEmpty()))){
        	 sqlString.append( " , CANDIDATO ");
        	 sqlString.append( " WHERE sol.id_Candidato = CANDIDATO.id_candidato ");
        	 
         } else {
        	 sqlString.append(" WHERE 1 = 1 ");
         }
		 
		 if(nombre != null && !nombre.equalsIgnoreCase("")){
			 sqlString.append(" AND LOWER(nombre) LIKE LOWER(?)");
			 numeroParametros--;
		 }
		 
		 if(apellido1 != null && !apellido1.equalsIgnoreCase("")){
			 sqlString.append(" AND LOWER(apellido1) LIKE LOWER(?)");
			 numeroParametros--;
		 }
		 
		 if(apellido2!= null && !apellido2.equalsIgnoreCase("")){
			 sqlString.append(" AND LOWER(apellido2) LIKE LOWER(?)");
			 numeroParametros--;
		 }		 
		 
		 if(fechaNacimiento != null){
			 sqlString.append(" AND fecha_nacimiento=? ");
			 numeroParametros--;
		 }
		 
		 if(curp != null && !curp.equalsIgnoreCase("")){
			 sqlString.append(" AND LOWER(curp) LIKE LOWER(?)");
			 numeroParametros--;
		 }	
		 
		 if(correoElectronico != null && !correoElectronico.equalsIgnoreCase("")){
			 sqlString.append(" AND LOWER(sol.correo_electronico) LIKE LOWER(?)");
			 if((correoElectronico != null && !correoElectronico.equalsIgnoreCase("") && correoElectronico.contains("_"))){
				sqlString.append(" escape '\\' ");
			 }
			 numeroParametros--;
		 }
		 
		 if (usuario != null && !usuario.isEmpty()){
				if(usuario.contains("_")){
					String usuarioEscapado = usuario.replaceAll("_", "\\_");
					sqlString.append(" AND EXISTS (SELECT 1 FROM USUARIO u, CANDIDATO c WHERE u.ID_USUARIO = c.ID_USUARIO AND c.ID_CANDIDATO = sol.ID_CANDIDATO  AND u.USUARIO LIKE '%"+usuarioEscapado+"%' escape '\\')");

				} else {
					sqlString.append(" AND EXISTS (SELECT 1 FROM USUARIO u, CANDIDATO c WHERE u.ID_USUARIO = c.ID_USUARIO AND c.ID_CANDIDATO = sol.ID_CANDIDATO  AND u.USUARIO LIKE '%"+usuario+"%')");
				}			 			 			 			
		 }
		 
		 if (telefono != null && !telefono.isEmpty()){
			 sqlString.append(" AND EXISTS (SELECT 1 FROM TELEFONO  WHERE TELEFONO.ID_TIPO_PROPIETARIO = "+TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario()+" AND TELEFONO.ID_PROPIETARIO = CANDIDATO.ID_CANDIDATO ");
			 sqlString.append(" AND TELEFONO LIKE '%"+telefono+"%') ");		 
		 }

		 if (idEntidad > 0 || idMunicipio > 0 || (domicilio != null && !domicilio.isEmpty())){
			 sqlString.append(" AND EXISTS (SELECT 1 FROM DOMICILIO  WHERE DOMICILIO.ID_TIPO_PROPIETARIO = "+TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario()+" AND DOMICILIO.ID_PROPIETARIO = CANDIDATO.ID_CANDIDATO ");
			 if (idEntidad > 0)
				 sqlString.append(" AND DOMICILIO.ID_ENTIDAD = "+idEntidad);	 
			 if (idMunicipio > 0)
				 sqlString.append(" AND DOMICILIO.ID_MUNICIPIO = "+idMunicipio);
			 if (domicilio != null && !domicilio.isEmpty())
				 sqlString.append(" AND LOWER(DOMICILIO.CALLE) LIKE LOWER('%"+domicilio+"%')");
			 sqlString.append(")"); 				 
		 }
		 
		CachedRowSet cachedRowSet = executeQueryCandidato(parametros, sqlString.toString());
		logger.debug("Aqui, datos, telefono..." + sqlString.toString());
		List<CandidatoVo> lstCandidatos = new ArrayList<CandidatoVo>();		
		try {
			while (cachedRowSet.next()) {
				CandidatoVo candVo = new CandidatoVo();
				candVo.setCurp(cachedRowSet.getString("curp"));
				Calendar cal=Calendar.getInstance();
				cal.setTime(cachedRowSet.getDate("fecha_registro"));
				candVo.setFechaAlta(cal);
				candVo.setFechaNacimiento(cachedRowSet.getDate("fecha_nacimiento"));
				candVo.setIdCandidato(cachedRowSet.getLong("id_candidato"));
				candVo.setNombre(cachedRowSet.getString("nombre"));
				candVo.setApellido1(cachedRowSet.getString("apellido1"));
				candVo.setApellido2(cachedRowSet.getString("apellido2"));
				candVo.setCorreoElectronico(cachedRowSet.getString("correo_electronico"));
				lstCandidatos.add(candVo);
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new SQLException(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return lstCandidatos;
	}		
	

	private String setQueryBuscarCandidatoNombreApellidoFechaNacimientoAlta(){
		StringBuffer sqlString = new StringBuffer();
		sqlString.append("SELECT curp, fecha_alta, fecha_nacimiento, ");
		sqlString.append(" id_candidato, nombre, apellido1, apellido2, correo_electronico ");
		sqlString.append("   FROM candidato");
		sqlString.append("   WHERE ");
		sqlString.append("   (LOWER(nombre) LIKE LOWER(?) AND  LOWER(apellido1) LIKE LOWER(?) )");
		sqlString.append("  AND fecha_nacimiento=? ");
		sqlString.append("  AND fecha_alta=? ");
		logger.debug(sqlString.toString());
		return sqlString.toString();
	}	
	
	private String setQueryBuscarCandidatoNombreApellidoFechaNacimiento(){
		StringBuffer sqlString = new StringBuffer();
		sqlString.append("SELECT curp, fecha_alta, fecha_nacimiento, ");
		sqlString.append(" id_candidato, nombre, apellido1, apellido2, correo_electronico ");
		sqlString.append("   FROM candidato");
		sqlString.append("   WHERE ");
		sqlString.append("   (LOWER(nombre) LIKE LOWER(?) AND  LOWER(apellido1) LIKE LOWER(?) )");
		sqlString.append("  AND fecha_nacimiento=? ");
		logger.debug(sqlString.toString());
		return sqlString.toString();
	}	
	
	private String setQueryBuscarCandidatoNombreApellidoFechaAlta(){
		StringBuffer sqlString = new StringBuffer();
		sqlString.append("SELECT curp, fecha_alta, fecha_nacimiento, ");
		sqlString.append(" id_candidato, nombre, apellido1, apellido2, correo_electronico ");
		sqlString.append("   FROM candidato");
		sqlString.append("   WHERE ");
		sqlString.append("   (LOWER(nombre) LIKE LOWER(?) AND  LOWER(apellido1) LIKE LOWER(?) )");
		sqlString.append("  AND fecha_alta=? ");
		logger.debug(sqlString.toString());
		return sqlString.toString();
	}		

	private String setQueryBuscarCandidatoNombreFechaNacimiento(){
		StringBuffer sqlString = new StringBuffer();
		sqlString.append("SELECT curp, fecha_alta, fecha_nacimiento, ");
		sqlString.append(" id_candidato, nombre, apellido1, apellido2, correo_electronico ");
		sqlString.append("   FROM candidato");
		sqlString.append("   WHERE ");
		sqlString.append("   (LOWER(nombre) LIKE LOWER(?))");
		sqlString.append("  AND fecha_nacimiento=? ");
		logger.debug(sqlString.toString());
		return sqlString.toString();
	}	

	private String setQueryBuscarCandidatoNombreApellido(){
		StringBuffer sqlString = new StringBuffer();
		sqlString.append("SELECT curp, fecha_alta, fecha_nacimiento, ");
		sqlString.append(" id_candidato, nombre, apellido1, apellido2, correo_electronico ");
		sqlString.append("   FROM candidato");
		sqlString.append("   WHERE ");
		sqlString.append("   (LOWER(nombre) LIKE LOWER(?) AND  LOWER(apellido1) LIKE LOWER(?) )");
		logger.debug(sqlString.toString());
		return sqlString.toString();
	}	
	
	
	private String setQueryBuscarCandidatoNombre(){
		StringBuffer sqlString = new StringBuffer();
		sqlString.append("SELECT curp, fecha_alta, fecha_nacimiento, ");
		sqlString.append(" id_candidato, nombre, apellido1, apellido2, correo_electronico ");
		sqlString.append("   FROM candidato");
		sqlString.append("   WHERE ");
		sqlString.append("   LOWER(nombre) LIKE LOWER(?) ");
		logger.debug(sqlString.toString());
		return sqlString.toString();
	}		
	
	private String setQueryBuscarCandidatoApellido(){
		StringBuffer sqlString = new StringBuffer();
		sqlString.append("SELECT curp, fecha_alta, fecha_nacimiento, ");
		sqlString.append(" id_candidato, nombre, apellido1, apellido2, correo_electronico ");
		sqlString.append("   FROM candidato");
		sqlString.append("   WHERE ");
		sqlString.append("   LOWER(apellido1) LIKE LOWER(?) ");
		logger.debug(sqlString.toString());
		return sqlString.toString();
	}	
	
	private String setQueryBuscarCandidatoApellidoFechaNacimiento(){
		StringBuffer sqlString = new StringBuffer();
		sqlString.append("SELECT curp, fecha_alta, fecha_nacimiento, ");
		sqlString.append(" id_candidato, nombre, apellido1, apellido2, correo_electronico ");
		sqlString.append("   FROM candidato");
		sqlString.append("   WHERE ");
		sqlString.append("   LOWER(apellido1) LIKE LOWER(?) ");
		sqlString.append("  AND fecha_nacimiento=? ");
		return sqlString.toString();
	}	
	
	private String setQueryBuscarCandidatoFechaNacimiento(){
		StringBuffer sqlString = new StringBuffer();
		sqlString.append("SELECT curp, fecha_alta, fecha_nacimiento, ");
		sqlString.append(" id_candidato, nombre, apellido1, apellido2, correo_electronico ");
		sqlString.append("   FROM candidato");
		sqlString.append("   WHERE fecha_nacimiento=? ");
		return sqlString.toString();
	}			
	
	private String setQueryBuscarCandidatoFechaAlta(){
		StringBuffer sqlString = new StringBuffer();
		sqlString.append("SELECT curp, fecha_alta, fecha_nacimiento, ");
		sqlString.append(" id_candidato, nombre, apellido1, apellido2, correo_electronico ");
		sqlString.append("   FROM candidato");
		sqlString.append("   WHERE fecha_alta=? ");
		return sqlString.toString();
	}		
	
	
	public List<HistoriaLaboralVO> buscarEmpleoActual(long idCandidato, int trabajo_actual) throws SQLException {
		CONSULTA_PERFIL_CANDIDATO = "BUSCAR_EMPLEO_ACTUAL";
		Object[] parametros = { idCandidato, trabajo_actual };
		HistoriaLaboralVO histLaboralVO = null;
		List<HistoriaLaboralVO> histLaboralesVO = new ArrayList<HistoriaLaboralVO>();
		CachedRowSet cachedRowSet = executeQuery(parametros);
		try {
			while (cachedRowSet.next()) {
				histLaboralVO = new HistoriaLaboralVO();
				histLaboralVO.setIdHistorialLaboral(cachedRowSet.getLong(1));
				histLaboralVO.setTrabajoActual(cachedRowSet.getInt(2));
				histLaboralVO.setIdSector(cachedRowSet.getLong(3));
				histLaboralVO.setSector(cachedRowSet.getString(4));
				histLaboralVO.setPuesto(cachedRowSet.getString(5));
				histLaboralVO.setIdAreaLaboral(cachedRowSet.getLong(6));
				histLaboralVO.setAreaLaboral(cachedRowSet.getString(7));
				histLaboralVO.setIdOcupacion(cachedRowSet.getLong(8));
				histLaboralVO.setOcupacion(cachedRowSet.getString(9));
				histLaboralVO.setEmpresa(cachedRowSet.getString(10));
				histLaboralVO.setConfidencialidadEmpresa(cachedRowSet.getInt(11));
				histLaboralVO.setLaboresInicial(cachedRowSet.getDate(12));
				histLaboralVO.setLaboresFinal(cachedRowSet.getDate(13));
				histLaboralVO.setAniosLaborados(cachedRowSet.getInt(14));
				histLaboralVO.setAnios(cachedRowSet.getString(15));
				histLaboralVO.setIdJerarquia(cachedRowSet.getLong(16));
				histLaboralVO.setJerarquia(cachedRowSet.getString(17));
				histLaboralVO.setPersonasCargo(cachedRowSet.getInt(18));
				histLaboralVO.setPersonas(cachedRowSet.getString(19));
				histLaboralVO.setSalarioMensual(cachedRowSet.getDouble(20));
				histLaboralVO.setFuncion(cachedRowSet.getString(21));
				histLaboralVO.setLogro(cachedRowSet.getString(22));
				histLaboralVO.setHerramientas(cachedRowSet.getString(23));
				histLaboralVO.setRefNombre(cachedRowSet.getString(24));
				histLaboralVO.setRefApellido1(cachedRowSet.getString(25));
				histLaboralVO.setRefApellido2(cachedRowSet.getString(26));
				histLaboralVO.setRefPuesto(cachedRowSet.getString(27));
				histLaboralesVO.add(histLaboralVO);
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new SQLException(e);
		}
		return histLaboralesVO;
	}
	
	public List<HistoriaLaboralVO> buscarEmpleoActualOUltimo(long idCandidato, int trabajo_actual) throws SQLException {
		CONSULTA_PERFIL_CANDIDATO = "BUSCAR_EMPLEO_ACTUAL_O_ULTIMO";
		Object[] parametros = { idCandidato };
		HistoriaLaboralVO histLaboralVO = null;
		List<HistoriaLaboralVO> histLaboralesVO = new ArrayList<HistoriaLaboralVO>();
		CachedRowSet cachedRowSet = executeQuery(parametros);
		try {
			while (cachedRowSet.next()) {
				histLaboralVO = new HistoriaLaboralVO();
				histLaboralVO.setIdHistorialLaboral(cachedRowSet.getLong(1));
				histLaboralVO.setTrabajoActual(cachedRowSet.getInt(2));
				histLaboralVO.setIdSector(cachedRowSet.getLong(3));
				histLaboralVO.setSector(cachedRowSet.getString(4));
				histLaboralVO.setPuesto(cachedRowSet.getString(5));
				histLaboralVO.setIdAreaLaboral(cachedRowSet.getLong(6));
				histLaboralVO.setAreaLaboral(cachedRowSet.getString(7));
				histLaboralVO.setIdOcupacion(cachedRowSet.getLong(8));
				histLaboralVO.setOcupacion(cachedRowSet.getString(9));
				histLaboralVO.setEmpresa(cachedRowSet.getString(10));
				histLaboralVO.setConfidencialidadEmpresa(cachedRowSet.getInt(11));
				histLaboralVO.setLaboresInicial(cachedRowSet.getDate(12));
				histLaboralVO.setLaboresFinal(cachedRowSet.getDate(13));
				histLaboralVO.setAniosLaborados(cachedRowSet.getInt(14));
				histLaboralVO.setAnios(cachedRowSet.getString(15));
				histLaboralVO.setIdJerarquia(cachedRowSet.getLong(16));
				histLaboralVO.setJerarquia(cachedRowSet.getString(17));
				histLaboralVO.setPersonasCargo(cachedRowSet.getInt(18));
				histLaboralVO.setPersonas(cachedRowSet.getString(19));
				histLaboralVO.setSalarioMensual(cachedRowSet.getDouble(20));
				histLaboralVO.setFuncion(cachedRowSet.getString(21));
				histLaboralVO.setLogro(cachedRowSet.getString(22));
				histLaboralesVO.add(histLaboralVO);
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new SQLException(e);
		}
		return histLaboralesVO;
	}
	
	
	/**
	 * Busca los medios de busqueda de empleo
	 * 
	 * @author Sergio Tellez
	 * @since 05/04/2011
	 * @param long idCandidato Identificador del candidato
	 * @throws SQLException
	 * @return String
	 **/
	public List<String> mediosBusqueda(long idCandidato) throws SQLException {
		CONSULTA_PERFIL_CANDIDATO = "MEDIOS_BUSQUEDA";
		Long[] parametros = { idCandidato };
		CachedRowSet cachedRowSet = executeQuery(parametros);
		List<String> otrosMedios = new ArrayList<String>();
		try {
			while (cachedRowSet.next())
				otrosMedios.add(cachedRowSet.getString(2));
		}catch (SQLException e) {
			logger.error(e);
			throw new SQLException(e);
		}
		return otrosMedios;
	}
	
	private String setQueryTrabajoActual() {
		StringBuffer sqlString = new StringBuffer();
		sqlString.append("SELECT hl.id_historial_laboral, ");
		sqlString.append("hl.trabajo_actual, ");
		sqlString.append("hl.id_sector, ");
		sqlString.append("DESCCATALOGO(1, " + CATALOGO_OPCION_SUBSECTOR + 
				", hl.id_sector), ");
		sqlString.append("hl.puesto, hl.id_area_laboral, ");
		sqlString.append("DESCCATALOGO(1, " + CATALOGO_OPCION_AREA_LABORAL + 
				", hl.id_area_laboral), ");
		sqlString.append("hl.id_ocupacion, ");
		sqlString.append("DESCCATALOGO(1, " + CATALOGO_OPCION_OCUPACION + 
				", hl.id_ocupacion), ");
		sqlString.append("hl.empresa, ");
		sqlString.append("hl.confidencialidad_empresa, ");
		sqlString.append("hl.labores_inicial, ");
		sqlString.append("hl.labores_final, ");
		sqlString.append("hl.anios_laborados, ");
		sqlString.append("DESCCATALOGO(1, " + CATALOGO_OPCION_EXPERIENCIA + 
				", hl.anios_laborados), ");
		sqlString.append("hl.id_jerarquia, ");
		sqlString.append("DESCCATALOGO(1, " + CATALOGO_OPCION_JERARQUIA_PUESTO + 
				", hl.id_jerarquia), ");
		sqlString.append("hl.personas_cargo, ");
		sqlString.append("DESCCATALOGO(1, " + CATALOGO_OPCION_PERSONAS_CARGO + 
				", hl.personas_cargo), ");
		sqlString.append("hl.salario_mensual, ");
		sqlString.append("hl.funcion, ");
		sqlString.append("hl.logro, ");
		sqlString.append("hl.herramientas, ");
		sqlString.append("hl.ref_nombre, ");
		sqlString.append("hl.ref_apellido1, ");
		sqlString.append("hl.ref_apellido2, ");
		sqlString.append("hl.ref_puesto ");
		sqlString.append("FROM HISTORIA_LABORAL hl ");
		sqlString.append("WHERE hl.id_candidato = ? ");
		sqlString.append("AND hl.trabajo_actual = ? ");
		sqlString.append("AND hl.principal = " + MULTIREGISTRO.PRINCIPAL.getIdOpcion());
		sqlString.append(" ORDER BY 1");
		logger.debug(sqlString.toString());
		return sqlString.toString();
	}
	
	private String setQueryTrabajoActualOUltimo() {
		StringBuffer sqlString = new StringBuffer();
		sqlString.append("SELECT hl.id_historial_laboral, ");
		sqlString.append("hl.trabajo_actual, ");
		sqlString.append("hl.id_sector, ");
		sqlString.append("DESCCATALOGO(1, " + CATALOGO_OPCION_SUBSECTOR + 
				", hl.id_sector), ");
		sqlString.append("hl.puesto, hl.id_area_laboral, ");
		sqlString.append("DESCCATALOGO(1, " + CATALOGO_OPCION_AREA_LABORAL + 
				", hl.id_area_laboral), ");
		sqlString.append("hl.id_ocupacion, ");
		sqlString.append("DESCCATALOGO(1, " + CATALOGO_OPCION_OCUPACION + 
				", hl.id_ocupacion), ");
		sqlString.append("hl.empresa, ");
		sqlString.append("hl.confidencialidad_empresa, ");
		sqlString.append("hl.labores_inicial, ");
		sqlString.append("hl.labores_final, ");
		sqlString.append("hl.anios_laborados, ");
		sqlString.append("DESCCATALOGO(1, " + CATALOGO_OPCION_EXPERIENCIA + 
				", hl.anios_laborados), ");
		sqlString.append("hl.id_jerarquia, ");
		sqlString.append("DESCCATALOGO(1, " + CATALOGO_OPCION_JERARQUIA_PUESTO + 
				", hl.id_jerarquia), ");
		sqlString.append("hl.personas_cargo, ");
		sqlString.append("DESCCATALOGO(1, " + CATALOGO_OPCION_PERSONAS_CARGO + 
				", hl.personas_cargo), ");
		sqlString.append("hl.salario_mensual, ");
		sqlString.append("hl.funcion, ");
		sqlString.append("hl.logro ");
		sqlString.append("FROM HISTORIA_LABORAL hl ");
		sqlString.append("WHERE hl.id_candidato = ? ");
		sqlString.append("AND hl.principal = " + MULTIREGISTRO.PRINCIPAL.getIdOpcion() );
		sqlString.append("ORDER BY 1");
		logger.debug(sqlString.toString());
		return sqlString.toString();
	}
	
	public CandidatoVo detalleCandidato(long idCandidato) throws SQLException {
		CONSULTA_PERFIL_CANDIDATO = "DETALLE_CANDIDATO";

		CandidatoVo candidatoVO = null;
		Context context = null;		
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet cachedRowSet = null;
		try {
			ConnectionWraper wraper = getConnection();
			conn = wraper.getConnection();
			context = wraper.getContext();
	
			if(conn!=null){
				stmt = conn.prepareCall(getQuery());
				stmt.setLong(1, idCandidato);
				stmt.registerOutParameter(2, OracleTypes.CURSOR); //REF CURSOR
				stmt.execute();
				cachedRowSet = ((OracleCallableStatement)stmt).getCursor(2);
	
			}
		
			while (cachedRowSet.next()) {
				candidatoVO = new CandidatoVo();
				candidatoVO.setIdCandidato(cachedRowSet.getLong(1));
				candidatoVO.setNombre(cachedRowSet.getString(2));
				candidatoVO.setApellido1(cachedRowSet.getString(3));
				candidatoVO.setApellido2(cachedRowSet.getString(4));
				candidatoVO.setFechaNacimiento(cachedRowSet.getDate(5));
				candidatoVO.setOcupacion(cachedRowSet.getString(7));
				candidatoVO.setSalario(cachedRowSet.getDouble(8));
				
				candidatoVO.setMunicipioEntidad(validarCadenaEntidadMunicipio(cachedRowSet.getString(12),cachedRowSet.getString(10)));
				
				candidatoVO.setEdad(obtenEdad(cachedRowSet.getDate(5)));
				candidatoVO.setDescTipoContrato(cachedRowSet.getString(13));
				candidatoVO.setHorarioEmpleo(cachedRowSet.getString(14));
				if (Constantes.DISPONIBILIDAD_VIAJAR.SI.getIdOpcion() == cachedRowSet.getLong(15))
					candidatoVO.setDispViajarFuera("Si" );
				else
					candidatoVO.setDispViajarFuera("No" );
				if (Constantes.DISPONIBILIDAD_RADICAR.SI.getIdOpcion() ==cachedRowSet.getLong(16))
					candidatoVO.setDispRadicarFuera("Si");
				else
					candidatoVO.setDispRadicarFuera("No");
				candidatoVO.setConfidencialidadDatos(cachedRowSet.getInt(17));
				candidatoVO.setDescEstatus(cachedRowSet.getString(19));
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error al ejecutar la consulta : "+ getQuery() + " --> idCandidato : "+ idCandidato);
			logger.error(e);
			throw new SQLException(e);
		} finally{
			try{if (cachedRowSet!=null) cachedRowSet.close();}catch(Exception e){e.printStackTrace();}
			try{if (stmt!=null) stmt.close();}catch(Exception e){e.printStackTrace();}

			/** Se esta utilizando una sola conexion para diversas consultas, por lo tanto no se cierra **/
			if (!isGlobalConnection()){
				try{if (conn!=null) conn.close();}catch(Exception e){e.printStackTrace();}
				
				if (context!=null){
					logger.info("Cerrando context");
					try {
						context.close();
					} catch (Exception e) {e.printStackTrace();}
				}
			}
		}
		
		return candidatoVO;
	}
	
	private String validarCadenaEntidadMunicipio(String entidad, String municipio){
		StringBuilder municipioEntidad = new StringBuilder();
		if(null!=entidad && !entidad.equalsIgnoreCase("null")){
			municipioEntidad.append(entidad).append(",");
			if(null!=municipio && !municipio.equalsIgnoreCase("null")){
				municipioEntidad.append(municipio);
			}
		} else {
			municipioEntidad.append("No disponible");
		}		
		return municipioEntidad.toString();
	}
	
	
	private String setQueryCandidatos() {
		StringBuffer sqlString = new StringBuffer();
		
		sqlString.append("BEGIN ");
		sqlString.append(" SP_CANDIDATOS "); 
		sqlString.append(" ("+ CATALOGO_OPCION_OCUPACION +", ");
		sqlString.append(" "+ CATALOGO_OPCION_ENTIDAD_FEDERATIVA +", ");
		sqlString.append(" "+ CATALOGO_OPCION_TIPO_CONTRATO +", ");
		sqlString.append(" "+ CATALOGO_OPCION_TIPO_EMPLEO +", ");
		sqlString.append(" "+ CATALOGO_OPCION_ESTATUS +", ");
		sqlString.append(" "+ Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion() +", ");
		sqlString.append(" "+ Constantes.CATALOGO_OPCION_DOMICILIO_CANDIDATO +", ");
		sqlString.append(" ?, ");
		sqlString.append(" ? ); ");
		sqlString.append("END;");
		
		return sqlString.toString();
	}
	
	private String setQueryMediosBusqueda() {
		StringBuffer sqlString = new StringBuffer();
		sqlString.append("SELECT om.id_medio_busqueda, ");
		sqlString.append("DESCCATALOGO(1, " + Constantes.CATALOGO_OPCION_OTROS_MEDIOS + 
		", om.id_medio_busqueda) ");
		sqlString.append("FROM OTRO_MEDIO om ");
		sqlString.append("WHERE om.id_candidato = ? ");
		sqlString.append("ORDER BY 1");
		return sqlString.toString();
	}
	
	private String setQueryCandidate() {
        StringBuffer sqlString = new StringBuffer();
        sqlString.append(" SELECT ca.id_candidato AS ID_CANDIDATO, " );
        sqlString.append(" ca.fecha_nacimiento AS FECHA_NACIMIENTO, ");
        sqlString.append(" pl.id_experiencia_total AS EXPERIENCIA, ");
        sqlString.append(" ex.id_tipo_empleo_deseado AS TIPO_EMPLEO, " );
        sqlString.append(" pl.sin_estudios AS INDICADOR_ESTUDIOS, " );
        sqlString.append(" pl.disponibilidad_viajar AS DISPONIBILIDAD_VIAJAR, " );
        sqlString.append(" pl.disponibilidad_radicar AS DISPONIBILIDAD_RADICAR, " );
        sqlString.append(" ex.salario_pretendido AS SALARIO, " );
        sqlString.append(" ex.id_ocupacion_deseada AS OCUPACION, " );
        sqlString.append(" DESCCATALOGO(1, 25, dom.id_entidad) AS ENTIDAD, mun.municipio AS MUNICIPIO ");
        sqlString.append(" from candidato ca " );
        sqlString.append(" left join expectativa_laboral ex on ex.id_candidato = ca.id_candidato " );
        sqlString.append(" left join perfil_laboral pl on pl.id_candidato = ca.id_candidato " );
        sqlString.append(" left join domicilio dom on ca.id_candidato = dom.id_propietario and dom.id_tipo_propietario=" + Constantes.CATALOGO_OPCION_DOMICILIO_CANDIDATO);
        sqlString.append(" left join municipio mun on dom.id_municipio = mun.id_municipio and dom.id_entidad = mun.id_entidad ");
        sqlString.append(" where ca.id_candidato= ? " );
        logger.debug(sqlString.toString());
        return sqlString.toString();
    }

	
	private String setQueryEvaluaCV() {
		StringBuffer sqlString = new StringBuffer();
		sqlString.append("	Select ca.evalua_cv AS CV " );
		sqlString.append("	FROM CANDIDATO ca " );
		sqlString.append(" where ca.id_candidato= ? " );
		return sqlString.toString();
	}
	
	/**
	 * Metodo que obtiene el query para la consulta de los
	 * datos de la nueva imagen del cv.
	 */
	private String getDatosCurriculum() {
		StringBuffer sqlString = new StringBuffer();
		sqlString.append("SELECT TITULO_CV, RESUMEN_PROFESIONAL,");
		sqlString.append("FACEBOOK, TWITTER, LINKEDIN, OBJETIVOS ");
		sqlString.append("FROM FORMATO_LPA ");
		sqlString.append("WHERE ID_CANDIDATO = ?");
		logger.debug(sqlString.toString());
		return sqlString.toString();
	}
	
	private String setQueryIdCandidatosFueraDeVigencia(){
		StringBuffer sqlString = new StringBuffer();	
		/* Consulta para 12 meses */
		sqlString.append(" SELECT ID_CANDIDATO ");
		sqlString.append(" FROM CANDIDATO ");
		sqlString.append(" WHERE ESTATUS=? ");
		sqlString.append(" AND TRUNC(FECHA_ULTIMO_ACCESO) = TRUNC(ADD_MONTHS(SYSDATE, -12))");
		logger.debug(sqlString.toString());
		return sqlString.toString();		
	}
	
	private String setQueryCandidatosAvisoDeVigencia(){
		StringBuffer sqlString = new StringBuffer();
		
		/* Consulta para 11 meses */	
		sqlString.append(" SELECT C.ID_CANDIDATO, C.CORREO_ELECTRONICO, S.NOMBRE, S.APELLIDO1, S.APELLIDO2, P.CONTACTO_CORREO ");
		sqlString.append(" FROM CANDIDATO C, PERFIL_LABORAL P, SOLICITANTE S ");
		sqlString.append(" WHERE C.ESTATUS=?");
		sqlString.append(" AND C.ID_CANDIDATO = P.ID_CANDIDATO ");
		sqlString.append(" AND C.ID_CANDIDATO = S.ID_CANDIDATO ");
		sqlString.append(" AND P.CONTACTO_CORREO=" +  Constantes.CONTACTO_CORREO.SI.getIdContactoCorreo());
		sqlString.append(" AND TRUNC(C.FECHA_ULTIMO_ACCESO) = TRUNC(ADD_MONTHS(SYSDATE, -11))");
		logger.debug(sqlString.toString());
		return sqlString.toString();		
	}	
	
	private String setQueryPostulantesEmpresaDesactivada(){
		StringBuffer sqlString = new StringBuffer();
		sqlString.append(" SELECT OC.ID_CANDIDATO, C.NOMBRE, C.APELLIDO1, C.APELLIDO2, C.CORREO_ELECTRONICO ");
		sqlString.append(" FROM CANDIDATO C, OFERTA_CANDIDATO OC");
		sqlString.append(" WHERE OC.ID_OFERTA_EMPLEO = ? ");
		sqlString.append(" AND OC.ESTATUS = ? ");
		sqlString.append(" AND C.ID_CANDIDATO= OC.ID_CANDIDATO ");
		logger.debug(sqlString.toString());
		return sqlString.toString();
	}
	
	public CandidatoVo detalleOfertaCandidato(long idCandidato, long idOferta) throws SQLException {
		CONSULTA_PERFIL_CANDIDATO = "DETALLE_CANDIDATO_OFERTA";
		Context context = null;
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet cachedRowSet = null;

		ConnectionWraper wraper = getConnection();
		conn = wraper.getConnection();
		context = wraper.getContext();

		stmt = conn.prepareCall(getQuery());
		stmt.setLong(1, idOferta);
		stmt.setLong(2, idCandidato);
		stmt.registerOutParameter(3, OracleTypes.CURSOR); //REF CURSOR
		stmt.execute();
		cachedRowSet = ((OracleCallableStatement)stmt).getCursor(3);
		
		CandidatoVo candidatoVO = null;
		try {
			while (cachedRowSet.next()) {
				candidatoVO = new CandidatoVo();
				candidatoVO.setIdCandidato(cachedRowSet.getLong(1));
				candidatoVO.setNombre(cachedRowSet.getString(2));
				candidatoVO.setApellido1(cachedRowSet.getString(3));
				candidatoVO.setApellido2(cachedRowSet.getString(4));
				candidatoVO.setFechaNacimiento(cachedRowSet.getDate(5));
				candidatoVO.setOcupacion(cachedRowSet.getString(7));
				candidatoVO.setSalario(cachedRowSet.getDouble(8));
				candidatoVO.setMunicipioEntidad(cachedRowSet.getString(12) + "," + cachedRowSet.getString(10));
				candidatoVO.setEdad(obtenEdad(cachedRowSet.getDate(5)));
				candidatoVO.setDescTipoContrato(cachedRowSet.getString(13));
				candidatoVO.setHorarioEmpleo(cachedRowSet.getString(14));
				if (Constantes.DISPONIBILIDAD_VIAJAR.SI.getIdOpcion() == cachedRowSet.getLong(15))
					candidatoVO.setDispViajarFuera("Si" );
				else
					candidatoVO.setDispViajarFuera("No" );
				if (Constantes.DISPONIBILIDAD_RADICAR.SI.getIdOpcion() ==cachedRowSet.getLong(16))
					candidatoVO.setDispRadicarFuera("Si");
				else
					candidatoVO.setDispRadicarFuera("No");
				candidatoVO.setConfidencialidadDatos(cachedRowSet.getInt(17));
				candidatoVO.setDescEstatus(cachedRowSet.getString(19));
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new SQLException(e);
		} finally{
			try{if (cachedRowSet!=null) cachedRowSet.close();}catch(Exception e){e.printStackTrace();}
			try{if (stmt!=null) stmt.close();}catch(Exception e){e.printStackTrace();}

			/** Se esta utilizando una sola conexion para diversas consultas, por lo tanto no se cierra **/
			if (!isGlobalConnection()){
				try{if (conn!=null) conn.close();}catch(Exception e){e.printStackTrace();}
				
				if (context!=null){
					try {
						context.close();
					} catch (Exception e) {e.printStackTrace();}
				}
			}
		}
		
		return candidatoVO;
	}
	
	private String setQueryOfertaCandidato() {
		StringBuffer sqlString = new StringBuffer();
		
		sqlString.append("BEGIN ");
		sqlString.append(" SP_OFERTA_CANDIDATO "); 
		sqlString.append(" ("+ CATALOGO_OPCION_OCUPACION +", ");
		sqlString.append(" "+ CATALOGO_OPCION_ENTIDAD_FEDERATIVA +", ");
		sqlString.append(" "+ CATALOGO_OPCION_TIPO_CONTRATO +", ");
		sqlString.append(" "+ CATALOGO_OPCION_TIPO_EMPLEO +", ");
		sqlString.append(" "+ CATALOGO_OPCION_ESTATUS +", ");
		sqlString.append(" "+ Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion() +", ");
		sqlString.append(" "+ Constantes.CATALOGO_OPCION_DOMICILIO_CANDIDATO +", ");
		sqlString.append(" ?, ");
		sqlString.append(" ?, ");
		sqlString.append(" ? ); ");
		sqlString.append("END;");
		
		return sqlString.toString();
	}
	 
	public int limpiarHistorialLaboral(long idCandidato) {
		int result = 0;
		CONSULTA_PERFIL_CANDIDATO = "ELIMINANDO_HISTORIA_LABORAL";
		
		Long[] parametros = {idCandidato};
		
		try {			
			result = executeUpdate(parametros);
		} catch (SQLException e) {
			logger.error(e);			
		}
		return result;
	}		
	
	
	private String setQueryHistoriaLaboral() {
		StringBuffer sqlString = new StringBuffer();
			sqlString.append("	delete HISTORIA_LABORAL where id_candidato = ? and principal = 2 " );		
		return sqlString.toString();
	}
	
	private String setQueryEstatusOfertaCandidato(){
		StringBuffer sqlString = new StringBuffer();
		sqlString.append("	SELECT estatus FROM Oferta_Candidato WHERE id_Oferta_Empleo=? AND id_Candidato=? " );		
	return sqlString.toString();		
	}
	
	public Integer consultaEstatusOfertaCandidato(long idOferta, long idCandidato) throws SQLException {
		Integer estatus = null;
		CONSULTA_PERFIL_CANDIDATO = "CONSULTA_ESTATUS_OFERTA_CANDIDATO";
		Long[] parametros = { idOferta, idCandidato };
		CachedRowSet cachedRowSet = executeQuery(parametros);
		
		try {
			if (cachedRowSet.next()) {
				estatus = cachedRowSet.getInt(1);
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new SQLException(e);
		}
		return estatus;
	}		
	

	/**
	 * Busca los datos del perfil de un candidato
	 * 
	 * @author Felipe Juarez Ramirez
	 * @since 03/03/2011
	 * @param long
	 * @throws SQLException
	 * @return PerfilVO
	 **/
	public PerfilVO buscarPerfilUsuario(long idUsuario) throws SQLException {
		logger.debug("Inicia metodo - buscarPerfilUsuario");
		CONSULTA_PERFIL_CANDIDATO = "BUSCAR_PERFIL_USUARIO";
		Long[] parametros = { idUsuario };
		CachedRowSet cachedRowSet = executeQuery(parametros);

		PerfilVO perfil = null;
		try {
			if (cachedRowSet.next()) {
				perfil = new PerfilVO();
				perfil.setIdCandidato(cachedRowSet.getLong(1));
				perfil.setIdUsuario(idUsuario);
				perfil.setIdOficina(cachedRowSet.getLong(2));
				perfil.setCurp(cachedRowSet.getString(3));
				perfil.setNombre(cachedRowSet.getString(4));
				perfil.setApellido1(cachedRowSet.getString(5));
				perfil.setApellido2(cachedRowSet.getString(6));
				perfil.setIdGenero(cachedRowSet.getInt(7));
				perfil.setFechaNacimiento(cachedRowSet.getDate(8));
				perfil.setEdad(obtenEdad(cachedRowSet.getDate(8)));
				perfil.setIdEntidadNacimiento(cachedRowSet.getLong(9));
				perfil.setEntidadNacimiento(cachedRowSet.getString(10));
				perfil.setIdEstadoCivil(cachedRowSet.getLong(11));
				perfil.setIdTipoDiscapacidad(cachedRowSet.getLong(12));
				perfil.setConfidencialidad(cachedRowSet.getInt(13));
				perfil.setContactoCorreo(cachedRowSet.getInt(14));
				perfil.setContactoTelefono(cachedRowSet.getInt(15));
				perfil.setHoraContactoIni(cachedRowSet.getLong(16));
				perfil.setHoraContactoFin(cachedRowSet.getLong(17));
				perfil.setIdRecibeOferta(cachedRowSet.getInt(18));				
				
				
				perfil.setIdTrabaja(Utils.validarCandidatoEmpleadoActualmente(cachedRowSet.getInt(19)));
				perfil.setIdRazonBusqueda(Utils.validarCandidatoRazonBusqueda(perfil.getIdTrabaja(), cachedRowSet.getLong(20)));
				perfil.setInicioBusqueda(cachedRowSet.getDate(21));
				perfil.setCorreoElectronico(cachedRowSet.getString(22));
				perfil.setEstiloCV(cachedRowSet.getInt(23));
				perfil.setIdMedioPortal(cachedRowSet.getLong(24));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e);
			throw new SQLException(e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return perfil;
	}
	
}
 