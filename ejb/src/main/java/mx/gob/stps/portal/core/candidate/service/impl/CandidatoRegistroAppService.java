package mx.gob.stps.portal.core.candidate.service.impl;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.ID_PARAMETRO_COOKIE_NAME_PUBLICIDAD;
import static mx.gob.stps.portal.core.infra.utils.Constantes.ID_PARAMETRO_REGISTRO_PUBLICIDAD;
import static mx.gob.stps.portal.core.infra.utils.Constantes.ID_PARAMETRO_URL_WS_RENAPO_CONSULTA_POR_CURP;
import static mx.gob.stps.portal.core.infra.utils.Constantes.ID_PARAMETRO_URL_WS_RENAPO_CONSULTA_POR_DETALLE;
import static mx.gob.stps.portal.core.infra.utils.Constantes.ID_REGISTRO_PORTAL;
import static mx.gob.stps.portal.core.infra.utils.Constantes.ID_USUARIO_ANONIMO;
import static mx.gob.stps.portal.utils.ConstantesGenerales.PORTAL_ID_OFICINA;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.naming.Context;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import mx.gob.stps.portal.core.autorizacion.vo.ParametroVO;
import mx.gob.stps.portal.core.candidate.dao.AccesoOLADAO;
import mx.gob.stps.portal.core.candidate.service.CandidatoAppServiceLocal;
import mx.gob.stps.portal.core.candidate.service.CandidatoRegistroAppServiceRemote;
import mx.gob.stps.portal.core.candidate.vo.AccesoOLAVO;
import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.candidate.vo.ConocimientoComputacionVO;
import mx.gob.stps.portal.core.candidate.vo.ExpectativaLaboralVO;
import mx.gob.stps.portal.core.candidate.vo.GradoAcademicoVO;
import mx.gob.stps.portal.core.candidate.vo.IdiomaVO;
import mx.gob.stps.portal.core.candidate.vo.PerfilLaboralVo;
import mx.gob.stps.portal.core.candidate.vo.RegistroCandidatoVO;
import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.domicilio.vo.MunicipioVO;
import mx.gob.stps.portal.core.infra.data.ConexionFactory;
import mx.gob.stps.portal.core.infra.data.ConnectionWraper;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.EncodingException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.mail.service.NotificacionService;
import mx.gob.stps.portal.core.infra.mail.template.util.TemplateHTML;
import mx.gob.stps.portal.core.infra.mail.vo.DestinatarioVO;
import mx.gob.stps.portal.core.infra.utils.Constantes.ACEPTACION_TERMINOS_CURRICULUM;
import mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO;
import mx.gob.stps.portal.core.infra.utils.Constantes.COMPU_AVANZADA;
import mx.gob.stps.portal.core.infra.utils.Constantes.COMPU_BASICA;
import mx.gob.stps.portal.core.infra.utils.Constantes.CONFIDENCIALIDAD;
import mx.gob.stps.portal.core.infra.utils.Constantes.CONTACTO_CORREO;
import mx.gob.stps.portal.core.infra.utils.Constantes.CONTACTO_TELEFONO;
import mx.gob.stps.portal.core.infra.utils.Constantes.DOMINIO;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESCOLARIDAD;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTILO_CV;
import mx.gob.stps.portal.core.infra.utils.Constantes.EVENTO;
import mx.gob.stps.portal.core.infra.utils.Constantes.EXPERIENCIA;
import mx.gob.stps.portal.core.infra.utils.Constantes.EXPERIENCIA_LABORAL;
import mx.gob.stps.portal.core.infra.utils.Constantes.GRADO_ESTUDIOS;
import mx.gob.stps.portal.core.infra.utils.Constantes.HORARIO;
import mx.gob.stps.portal.core.infra.utils.Constantes.MULTIREGISTRO;
import mx.gob.stps.portal.core.infra.utils.Constantes.PERFIL;
import mx.gob.stps.portal.core.infra.utils.Constantes.Plantilla;
import mx.gob.stps.portal.core.infra.utils.Constantes.RECIBE_OFERTA;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_DISCAPACIDAD;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_USUARIO;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.CurpServiceLocator;
import mx.gob.stps.portal.core.infra.utils.Password;
import mx.gob.stps.portal.core.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.persistencia.facade.*;
import mx.gob.stps.portal.core.seguridad.exception.CorreoRepetidoException;
import mx.gob.stps.portal.core.seguridad.exception.CurpRepetidoException;
import mx.gob.stps.portal.core.seguridad.exception.LoginRepetidoException;
import mx.gob.stps.portal.core.seguridad.service.SeguimientoAtencionAppServiceLocal;
import mx.gob.stps.portal.core.seguridad.vo.UsuarioVO;
import mx.gob.stps.portal.core.ws.renapo.exception.ConsultaWsPorCurpException;
import mx.gob.stps.portal.core.ws.renapo.impl.CURPServiceImpl;
import mx.gob.stps.portal.persistencia.entity.Candidato;
import mx.gob.stps.portal.persistencia.entity.HistoriaLaboral;
import mx.gob.stps.portal.persistencia.vo.*;
import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.utils.ConstantesGenerales;
import mx.gob.stps.portal.utils.converter.exceptions.NotFoundAnnotationException;
import mx.gob.stps.portal.utils.pdform.DataAccessToPEVO;
import mx.gob.stps.portal.utils.pdform.DataAccessToPePdfFormFiller;
import mx.gob.stps.portal.utils.pdform.PdfFormFiller;
import mx.gob.stps.portal.utils.pdform.RequirementsToPPCSDVO;
import mx.gob.stps.portal.utils.pdform.RequirementsToPpcSdPdfFormFiller;
import mx.gob.stps.portal.ws.geonames.GeoNames;
import oracle.spatial.geometry.JGeometry;

import org.apache.log4j.Logger;

/**
 * @author jose.hernandez
 *
 */
@Stateless(name = "CandidatoRegistroAppService", mappedName = "CandidatoRegistroAppService")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CandidatoRegistroAppService implements CandidatoRegistroAppServiceRemote {

	private static Logger logger = Logger.getLogger(CandidatoRegistroAppService.class);

	@EJB
	private BitacoraFacadeLocal  bitacoraFacade;

	@EJB
	private UsuarioFacadeLocal  usuarioFacade;
	
	@EJB
	private SolicitanteFacadeLocal  solicitanteFacade;

	@EJB
	private CandidatoFacadeLocal candidatoFacade;

	@EJB
	private PerfilLaboralFacadeLocal perfilLaboralFacade;
	
	@EJB
	private CandidatoAppServiceLocal  candidatoAppService;
	
	@EJB
	private DomicilioFacadeLocal domicilioFacade;

	@EJB
	private TelefonoFacadeLocal telefonoFacade;
	
	@EJB
	private ParametroFacadeLocal parametroFacade;
	
	@EJB
	private ConocimientoComputacionFacadeLocal conocimientoComputacionFacade;
	
	@EJB
	private SeguimientoAtencionAppServiceLocal seguimientoAtencionAppService;

	@EJB
	private CatalogoOpcionFacadeLocal catalogoOpcionFacade;
	
	@EJB
	private HistoriaLaboralFacadeLocal historiaLaboralFacade;
	
	@EJB
	private CandidatoQuebecFacadeLocal candidatoQuebecFacade;

	@EJB
	private CandidatoGeolocalizacionFacadeLocal candidatoGeolocalizacionFacade;
	
	
	/** Registrando en Bitacora
	 * @param evento
	 * @param idUsuario
	 * @param descripcion
	 * @param detalle
	 */
	public void registroBitacora(EVENTO evento, long idUsuario, String descripcion,String detalle, long idRegistro, TIPO_PROPIETARIO tipoPropietario){
		bitacoraFacade.save(evento.getIdEvento(), idUsuario, descripcion, Calendar.getInstance(), detalle, idRegistro, tipoPropietario.getIdTipoPropietario());
	}

	public boolean esCorreoUnico(String correoElectronico){
		boolean unico = usuarioFacade.esCorreoUnico(correoElectronico);
		return unico;
	}

	public boolean esUsuarioUnico(String usuario){
		boolean unico = usuarioFacade.esUsuarioUnico(usuario);
		return unico;
	}

	public boolean esCurpUnico(String curp){
		boolean unico = !candidatoFacade.repetidaCurp(curp);
		return unico;
	}
	
	public CandidatoVo consultaCandidatoPorCURP(String curp){
		CandidatoVo candidato = candidatoFacade.consultaPorCURP(curp);
		return candidato;
	}
	
	public boolean esNSSUnico(String nss){
		return candidatoFacade.esNSSUnico(nss);
	}

	public String consultaNombreCookie(){
		return consultaParametro(ID_PARAMETRO_COOKIE_NAME_PUBLICIDAD);
	}

	public String consultaNombreParametro(){
		return consultaParametro(ID_PARAMETRO_REGISTRO_PUBLICIDAD);
	}

	private String consultaParametro(long idParametro){
		String valor = null;

		try{
			ParametroVO parametro = parametroFacade.findById(idParametro);

			if (parametro!=null)
				valor = parametro.getValor();

		}catch(Exception e){
			e.printStackTrace(); logger.error(e);
		}

		return valor;
	}
	
	/**
	 * Registra cuando se detecta que un candidato es registrado a partir de un sitio publicitario 
	 */
	public long registraCandidatoPublicidad(String curp, String cookie, String parametro, int idTipoIngreso) {
		
		if (curp==null || curp.trim().isEmpty()){
			logger.error("Registro a traves de publidad no se puede realizar sin la CURP del candidato");
			return 0;
		}
		
		long id = 0;

		try{
			id = candidatoFacade.registraCandidatoPublicidad(curp, cookie, parametro, idTipoIngreso);
		}catch(Exception e){
			e.printStackTrace(); logger.error(e);
		}

		return id;
	}
	
	@Transactional
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public long registraCandidato(RegistroCandidatoVO registro) throws LoginRepetidoException, CorreoRepetidoException, CurpRepetidoException,
			                                                           BusinessException, TechnicalException, PersistenceException {
		if (registro==null) throw new IllegalArgumentException("Datos del candidato requerido");
		
		logger.info("Se registra al Candidato, CURP ["+ registro.getCurp() +"]");
		
		long idCandidato = 0;

		long idUsuario = 0;

		if(!usuarioFacade.esUsuarioUnico(registro.getUsuario()))
			throw new LoginRepetidoException(registro.getUsuario());
		
		if(!usuarioFacade.esCorreoUnico(registro.getCorreoElectronico()))			
			throw new CorreoRepetidoException(registro.getCorreoElectronico());

		if (candidatoFacade.repetidaCurp(registro.getCurp()))
			throw new CurpRepetidoException(registro.getCurp());

		try {
			Calendar fecha = Calendar.getInstance();

			//--------------------------------------------------------------------------------------------
			
			idUsuario = guardarUsuario(registro, fecha);

			//--------------------------------------------------------------------------------------------
			int edad = Utils.calculaEdad(registro.getFechaNacimiento());
			
			// ----------- CANDIDATO -----------
			idCandidato = guadarCandidato(registro, idUsuario, fecha);

			// ----------- SOLICITANTE -----------
			guardarSolicitante(registro, idCandidato, idUsuario, edad);

			//--------------------------------------------------------------------------------------------
			
			PerfilLaboralVo perfilLaboral = new PerfilLaboralVo();
			this.mapearPerfilLaboralVo(idCandidato, registro, perfilLaboral);
			
			perfilLaboral.setFechaAlta(fecha.getTime());
			perfilLaboralFacade.save(perfilLaboral);
			/*long idRecibeOferta = 0;
			
			if (registro.getNotificarCel()==RECIBE_OFERTA.TELEFONO.getIdRecibeOferta() &&
				registro.getNotificarCorreo()==RECIBE_OFERTA.CORREO.getIdRecibeOferta()){
				
				idRecibeOferta = RECIBE_OFERTA.AMBOS.getIdRecibeOferta();
			} else if (registro.getNotificarCel()==RECIBE_OFERTA.TELEFONO.getIdRecibeOferta()){
				idRecibeOferta = RECIBE_OFERTA.TELEFONO.getIdRecibeOferta();
			} else if (registro.getNotificarCorreo()==RECIBE_OFERTA.CORREO.getIdRecibeOferta()){
				idRecibeOferta = RECIBE_OFERTA.CORREO.getIdRecibeOferta();
			} else {
				idRecibeOferta = RECIBE_OFERTA.NO.getIdRecibeOferta();
			}

			int computacionBasica   = COMPU_BASICA.NO.getIdOpcion();
			int computacionAvanzada = COMPU_AVANZADA.NO.getIdOpcion();
			
			if (registro.getConocimientoCompNinguno()!=1){
				computacionBasica = COMPU_BASICA.SI.getIdOpcion();

				perfilLaboral.setIdExperienciaCompu			(EXPERIENCIA.MENOR_TRES.getIdOpcion()); 
				perfilLaboral.setIdDominioCompu				(DOMINIO.INTERMEDIO.getIdOpcion());
				
				if (registro.getConocimientoCompProcesadorTxt()==1 || registro.getConocimientoCompHojaCal()==1){
					perfilLaboral.setIdExperienciaOffice	(EXPERIENCIA.MENOR_TRES.getIdOpcion());
					perfilLaboral.setIdDominioOffice		(DOMINIO.INTERMEDIO.getIdOpcion());
				}

				if (registro.getConocimientoCompInternet()==1 || registro.getConocimientoCompRedes()==1){
					perfilLaboral.setIdExperienciaInternet	(EXPERIENCIA.MENOR_CUATRO.getIdOpcion());
					perfilLaboral.setIdDominioInternet		(DOMINIO.INTERMEDIO.getIdOpcion());
				}
			}

			int sinEstudios = ESCOLARIDAD.CON_ESTUDIOS.getIdOpcion();

			if (GRADO_ESTUDIOS.SIN_INSTRUCCION.getIdOpcion() == registro.getIdGradoEstudio()){
				sinEstudios = ESCOLARIDAD.SIN_ESTUDIOS.getIdOpcion();
			}
			
			int sinExperiencia = EXPERIENCIA_LABORAL.CON_EXPERIENCIA.getIdOpcion();

			if (registro.getIdExperienciaTotal()==EXPERIENCIA.NINGUNA.getIdOpcion()){
				sinExperiencia = EXPERIENCIA_LABORAL.SIN_EXPERIENCIA.getIdOpcion();
			}
			
			int contactoCorreo = CONTACTO_CORREO.SI.getIdContactoCorreo();
			if (registro.getMediocontactoCorreo()!=CONTACTO_CORREO.SI.getIdContactoCorreo())
				contactoCorreo = CONTACTO_CORREO.NO.getIdContactoCorreo();
			
			int contactoTelefono = CONTACTO_TELEFONO.SI.getIdContactoTelefono();
			if (registro.getMediocontactoTel()!=CONTACTO_TELEFONO.SI.getIdContactoTelefono())
				contactoTelefono = CONTACTO_TELEFONO.NO.getIdContactoTelefono();
				
			perfilLaboral.setIdCandidato				(idCandidato);
			perfilLaboral.setContactoCorreo				(contactoCorreo);
			perfilLaboral.setContactoTelefono			(contactoTelefono);
			perfilLaboral.setHorarioContactoDe			(HORARIO.NUEVE_AM.getIdOpcion());
			perfilLaboral.setHorarioContactoA			(HORARIO.NUEVE_PM.getIdOpcion());
			perfilLaboral.setIdRecibeOferta				(idRecibeOferta);
			perfilLaboral.setEmpleadoActualmente		(registro.getTrabajas());
			perfilLaboral.setIdRazonBusqueda			(registro.getIdMotivo());
			perfilLaboral.setInicioBusqueda				(registro.getFechaInicioBusqueda());
			perfilLaboral.setIdExperienciaTotal			(registro.getIdExperienciaTotal());
			perfilLaboral.setExperiencia				(registro.getDescripcionExperiencia());
			//perfilLaboral.setIdSectorMayorExpr		(idSectorMayorExpr);
			//perfilLaboral.setIdAreaLaboralMayorExpr	(idAreaLaboralMayorExpr);
			//perfilLaboral.setIdOcupacionMayorExpr		(idOcupacionMayorExpr);
			perfilLaboral.setDisponibilidadViajar		(registro.getViajar());
			perfilLaboral.setDisponibilidadRadicar		(registro.getRadicar());
			perfilLaboral.setComputacionBasica			(computacionBasica);
			perfilLaboral.setComputacionAvanzada		(computacionAvanzada);
			//perfilLaboral.setFotografia				(fotografia);
			//perfilLaboral.setUrlVideoc			   	(urlVideoc);
			//perfilLaboral.setPuestoMayorExpr	   		(puestoMayorExpr);
			//perfilLaboral.setTerminosVideoc		   	(terminosVideoc);
			//perfilLaboral.setDescripcionVideoc	   	(descripcionVideoc);
			perfilLaboral.setFechaAlta			   		(fecha.getTime());
			perfilLaboral.setSinEstudios           		(sinEstudios);
			perfilLaboral.setSinExperiencia				(sinExperiencia);
			//perfilLaboral.setEstatusVideoc         	(estatusVideoc);
			 * perfilLaboralFacade.save(perfilLaboral);
			*/
			

			//--------------------------------------------------------------------------------------------
			if (registro.getConocimientoCompNinguno()!=1){

				conocimientoComputacionFacade.registraConocimientosComputacion(idCandidato, TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario(),
																			   registro.getConocimientoCompProcesadorTxt(),
																			   registro.getConocimientoCompHojaCal(),
																			   registro.getConocimientoCompInternet(),
																			   registro.getConocimientoCompRedes(),
																			   registro.getConocimientoCompOtros());
			}

			//--------------------------------------------------------------------------------------------

			this.guardarDomicilioDelRegistroCandidato(idCandidato, registro, fecha);
			//--------------------------------------------------------------------------------------------
			
			guardarTelefonoFacade(idCandidato, registro, fecha);

			//--------------------------------------------------------------------------------------------
			
			guardarPerfilLaboralOtroMedio(idCandidato, registro, fecha);

			//--------------------------------------------------------------------------------------------

			agregarIdioma(idCandidato, registro);
			//Laboral deseado
			guardarLaboralDeseado(registro, idCandidato);
			
			agregarGrado(idCandidato, registro);
			
			
			registro.getHistorialLaboralVO().setIdCandidato(idCandidato);
			guardarCandidatoHabilidades(idCandidato, registro.getIdHabilidades());
			borrarRegistrosYRegistrarHistorialLaboralMapeo(registro.getHistorialLaboralVO());
			/*if(registro.getIdOcupacionDeseada2() != 0 ){
				
				HistoriaLaboralVO historial2 = registro.getHistorialLaboralVO();
				historial2.setIdHistorialLaboral(0L);
				historial2.setIdOcupacion(Long.valueOf(registro.getIdOcupacionDeseada2()));
				historial2.setIdExperiencia(Long.valueOf(registro.getIdExperiencia2()));
				registrarHistorialLaboralMapeoAMano(historial2);
				
			}*/
			//--------------------------------------------------------------------------------------------

			this.guardarCandidatoGeolocalizacion(idCandidato, registro, fecha.getTime());

			//--------------------------------------------------------------------------------------------
			
			registroBitacora(EVENTO.REGISTRO_CANDIDATOS, ID_USUARIO_ANONIMO,
							 EVENTO.REGISTRO_CANDIDATOS.getEvento(),
							 "Registrando un nuevo Candidato con la curp "+ registro.getCurp(),
							 idCandidato, TIPO_PROPIETARIO.CANDIDATO);

			bitacoraFacade.registraMovimiento(EVENTO.REGISTRA_CUENTA_PERSONAL, fecha.getTime(), idUsuario, PERFIL.CANDIDATO.getIdOpcion());

			logger.info("Candidato registrado, idCandidato ["+ idCandidato +"], CURP ["+ registro.getCurp() +"]");

		} catch(PersistenceException e){
			e.printStackTrace(); logger.error(e);
			throw e;
		} catch(EncodingException e) {
			e.printStackTrace(); logger.error(e);
			throw new TechnicalException(e);
		} catch (Exception e) {
			e.printStackTrace(); logger.error(e);
			throw new TechnicalException(e);
		}

		return idCandidato;
	}

	private void guardarSolicitante(RegistroCandidatoVO registro,
			long idCandidato, long idUsuario, int edad) {
		SolicitanteVO solicitanteVo = new SolicitanteVO();
		solicitanteVo.setIdCandidato(idCandidato);
		solicitanteVo.setNombre(registro.getNombre());
		solicitanteVo.setApellido1(registro.getApellido1());
		solicitanteVo.setApellido2(registro.getApellido2());
		solicitanteVo.setCorreoElectronico(registro.getCorreoElectronico());
		solicitanteVo.setCurp(registro.getCurp());
		solicitanteVo.setEstatus(ESTATUS.ACTIVO.getIdOpcion());
		solicitanteVo.setGenero(registro.getGenero());
		solicitanteVo.setFechaNacimiento(registro.getFechaNacimiento());
		solicitanteVo.setFechaRegistro(new Date());
		solicitanteVo.setFechaUltimaModificacion(new Date());
		solicitanteVo.setEdad(edad);
		solicitanteVo.setIdEntidadNacimiento(registro.getIdEntidadNacimiento());
		solicitanteVo.setIdFuente(Catalogos.APLICACION.PORTAL_DEL_EMPLEO.getIdOpcion());
		solicitanteVo.setIdOficina(PORTAL_ID_OFICINA);
		solicitanteVo.setIdEmpresa(null);
		solicitanteVo.setPersonaUltimaModificacion(idUsuario);
		solicitanteVo.setValidadoRenapo(ConstantesGenerales.VALIDACION_RENAPO.SI.getIdOpcion());
		solicitanteVo.setIdCandidato(idCandidato);

		//candidatoVo.setSolicitanteVO(solicitanteVo);

		solicitanteFacade.save(solicitanteVo);
	}

	private long guadarCandidato(RegistroCandidatoVO registro, long idUsuario,
			Calendar fecha) {
		long idCandidato;
		Candidato candidato =  new Candidato();
		candidato.setIdUsuario		(idUsuario);
		
		
		// DATOS DE INFONAVIT
		if(registro.getNumeroSeguroSocial() != null && !registro.getNumeroSeguroSocial().trim().isEmpty()){
			candidato.setNss(registro.getNumeroSeguroSocial());
		}
		if(registro.getCreditoInfonavit() != null){
			candidato.setCreditoInfonavit(registro.getCreditoInfonavit());
		}

		candidato.setIdFuente(Utils.toLong(Catalogos.APLICACION.PORTAL_DEL_EMPLEO.getIdOpcion()));
		candidato.setIdOficina			(PORTAL_ID_OFICINA);
		candidato.setFechaAlta           (new Date());
		candidato.setIdEntidadNacimiento (registro.getIdEntidadNacimiento());
		candidato.setEstatus             (ESTATUS.ACTIVO.getIdOpcion());
		candidato.setFechaConfirma       (fecha.getTime());
		candidato.setFechaUltimaActualizacion (fecha.getTime());
		candidato.setCorreoElectronico   (registro.getCorreoElectronico());
		candidato.setEstiloCv			   (ESTILO_CV.CLASICO.getIdOpcion());

		candidato.setIdTipoDiscapacidad((int)TIPO_DISCAPACIDAD.NINGUNA.getIdOpcion());
		
		candidato.setIdMedioPortal       (registro.getIdComoEnteraste());

		int confidencialidadDatos = CONFIDENCIALIDAD.NO.getIdOpcion();
		if (registro.getDatosConfidenciales()==CONFIDENCIALIDAD.SI.getIdOpcion()){
			confidencialidadDatos = CONFIDENCIALIDAD.SI.getIdOpcion();
		}
		
		candidato.setConfidencialidadDatos (confidencialidadDatos);

		int veracidadDatos = CONFIDENCIALIDAD.NO.getIdOpcion();
		int aceptacionTerminos = ACEPTACION_TERMINOS_CURRICULUM.NO.getIdOpcion();

		if (registro.getAceptacionTerminos()==ACEPTACION_TERMINOS_CURRICULUM.SI.getIdOpcion()){
			veracidadDatos = CONFIDENCIALIDAD.SI.getIdOpcion();
			aceptacionTerminos = ACEPTACION_TERMINOS_CURRICULUM.SI.getIdOpcion();				
		}

		candidato.setVeracidadDatos		(veracidadDatos);
		candidato.setAceptacionTerminos	(aceptacionTerminos);
		candidato.setApoyoProspera(registro.getApoyoProspera());
		candidato.setFolioProspera(registro.getFolioProspera());
		candidato.setFolioIntegranteProspera(registro.getFolioIntegranteProspera());
		
		/** CANDIDATO AMANECE CON ESTADO CIVIL NO ESPECIFICADO**/
		candidato.setIdEstadoCivil(7);//(int)Catalogos.ESTADO_CIVIL.NO_ESPECIFICADO.getIdCatalogoOpcion());

		idCandidato = candidatoFacade.save(candidato);
		
		return idCandidato;
	}

	private long guardarUsuario(RegistroCandidatoVO registro, Calendar fecha)
			throws EncodingException {
		long idUsuario;
		UsuarioVO usuarioVO = new UsuarioVO();
		usuarioVO.setUsuario			(registro.getUsuario()); /** SE CAMBIO EL CORREO POR LOGIN **/
		usuarioVO.setContrasena        	(Password.codificaPassword(registro.getContrasena()));
		usuarioVO.setCorreoElectronico	(registro.getCorreoElectronico());
		usuarioVO.setFechaAlta		   	(fecha.getTime());
		usuarioVO.setFechaModificacion 	(fecha.getTime());
		usuarioVO.setEstatus           	(ESTATUS.ACTIVO.getIdOpcion());
		usuarioVO.setIdPerfil      	   	(PERFIL.CANDIDATO.getIdOpcion());		
		usuarioVO.setIdRegistro        	(ID_REGISTRO_PORTAL);
		usuarioVO.setIdTipoUsuario     	(TIPO_USUARIO.CANDIDATO.getIdTipoUsuario());
		usuarioVO.setNombre				(registro.getNombre());
		usuarioVO.setApellido1			(registro.getApellido1());
		usuarioVO.setApellido2			(registro.getApellido2());
		idUsuario = usuarioFacade.save(usuarioVO);
		return idUsuario;
	}
	
	private void guardarLaboralDeseado(RegistroCandidatoVO registro,
			long idCandidato) {


		long idAreaLaboralDeseada = 0;

		int idOcupacionDeseada = registro.getIdOcupacionDeseada();
		String idOcupacion = String.valueOf(idOcupacionDeseada);

		
		/** El id del Area son las dos primeras cifras del id de la ocupacion **/
		if (!idOcupacion.isEmpty() && idOcupacion.length()>=2){
			idAreaLaboralDeseada = Utils.parseLong(idOcupacion.substring(0, 2));
		}
/**
		ExpectativaLaboralVO expectativaLaboral = new ExpectativaLaboralVO();
		//expectativaLaboral.setIdExpectativaLaboral(idExpectativaLaboral);
		expectativaLaboral.setIdSectorDeseado		(0); // TODO SE VA A ELIMINAR
		//expectativaLaboral.setPuestoDeseado		(puestoDeseado);
		expectativaLaboral.setIdAreaLaboralDeseada	(idAreaLaboralDeseada);
		expectativaLaboral.setIdOcupacionDeseada    (registro.getIdOcupacionDeseada());
		expectativaLaboral.setSalarioPretendido		(registro.getSalarioPretendido());
		expectativaLaboral.setIdTipoEmpleoDeseado	(registro.getIdTipoEmpleoDeseado());
		expectativaLaboral.setIdTipoContrato		(registro.getIdTipoContratoDeseado());
		expectativaLaboral.setIdExperiencia			(registro.getIdExperienciaTotal());				//TODO experiencia
		expectativaLaboral.setPrincipal				(MULTIREGISTRO.PRINCIPAL.getIdOpcion());

		candidatoFacade.registrarExpectativaLaboral(idCandidato, expectativaLaboral);
**/		
		guardarLaboralDeseado(registro.getIdOcupacionDeseada(),registro.getSalarioPretendido(),registro.getIdTipoEmpleoDeseado(),
					registro.getIdTipoContratoDeseado(),registro.getIdExperienciaTotal(),idCandidato,MULTIREGISTRO.PRINCIPAL);
		
		if(registro.getIdOcupacionDeseada2() > 0){
			guardarLaboralDeseado(registro.getIdOcupacionDeseada2(),registro.getSalarioPretendido(),registro.getIdTipoEmpleoDeseado(),
					registro.getIdTipoContratoDeseado(),registro.getIdExperiencia2(),idCandidato,MULTIREGISTRO.ADICIONAL);
		}
		

	}

	private void guardarLaboralDeseado(int idOcupacionDeseada, double salarioPretendido, int idTipoEmpleoDeseado,
			int idTipoContratoDeseado, int idExperiencia, long idCandidato,MULTIREGISTRO multiRegistro ) {


		long idAreaLaboralDeseada = 0;

		
		String idOcupacion = String.valueOf(idOcupacionDeseada);

		
		/** El id del Area son las dos primeras cifras del id de la ocupacion **/
		if (!idOcupacion.isEmpty() && idOcupacion.length()>=2){
			idAreaLaboralDeseada = Utils.parseLong(idOcupacion.substring(0, 2));
		}

		ExpectativaLaboralVO expectativaLaboral = new ExpectativaLaboralVO();
		//expectativaLaboral.setIdExpectativaLaboral(idExpectativaLaboral);
		expectativaLaboral.setIdSectorDeseado		(0); // TODO SE VA A ELIMINAR
		//expectativaLaboral.setPuestoDeseado		(puestoDeseado);
		expectativaLaboral.setIdAreaLaboralDeseada	(idAreaLaboralDeseada);
		expectativaLaboral.setIdOcupacionDeseada    (idOcupacionDeseada);
		expectativaLaboral.setSalarioPretendido		(salarioPretendido);
		expectativaLaboral.setIdTipoEmpleoDeseado	(idTipoEmpleoDeseado);
		expectativaLaboral.setIdTipoContrato		(idTipoContratoDeseado);
		expectativaLaboral.setPrincipal				(multiRegistro.getIdOpcion());
		expectativaLaboral.setIdExperiencia			(idExperiencia);
		candidatoFacade.registrarExpectativaLaboral(idCandidato, expectativaLaboral);
		
		
				

	}

	@Transactional
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public long actualizaRegistroCandidato(long idCandidato, RegistroCandidatoVO registro) throws LoginRepetidoException, CorreoRepetidoException, CurpRepetidoException,
		                                                                                          BusinessException, TechnicalException, PersistenceException {
		
		if (idCandidato<=0) throw new IllegalArgumentException("Identificador de candidato requerido");
		if (registro==null) throw new IllegalArgumentException("Datos del candidato requerido");
		
		logger.info("Se actualiza el registro del Candidato, idCandidato ["+ idCandidato +"], CURP ["+ registro.getCurp() +"]");
		
		if(!usuarioFacade.esUsuarioUnico(registro.getUsuario()))
			throw new LoginRepetidoException(registro.getUsuario());
		
		if(!usuarioFacade.esCorreoUnico(registro.getCorreoElectronico()))			
			throw new CorreoRepetidoException(registro.getCorreoElectronico());
		
		/*if (candidatoFacade.repetidaCurp(registro.getCurp()))
			throw new CurpRepetidoException(registro.getCurp());*/
		
		try {
			Calendar fecha = Calendar.getInstance();
			
			CandidatoVo candidato = candidatoFacade.find(idCandidato);
			long idUsuario = candidato.getIdUsuario();

			//--------------------------------------------------------------------------------------------

			actualizarRegistroUsuario(registro, fecha, idUsuario);
			
			//--------------------------------------------------------------------------------------------
			borrarYGuardarCandidato(idCandidato, registro, fecha, idUsuario);
			
			//borrarYGuardarPerfilLaboral(idCandidato, registro, fecha);

			actualizarBorrarOInsertarConocimientoComputacion(idCandidato,registro);
			
			guardarDomicilioDelRegistroCandidato(idCandidato, registro, fecha);
			
			guardarTelefonoRegistroCandidato(idCandidato, registro, fecha);
			
			borrarYguardarPerfilLaboral(idCandidato, registro, fecha);

			borrarYGuardarIdiomas(idCandidato, registro);
			
			borrarYGuardarLaboralDeseado(idCandidato, registro);
			//candidatoFacade.registrarExpectativaLaboral(idCandidato, expectativaLaboral);
			
			//--------------------------------------------------------------------------------------------

			//candidatoFacade.borrarCandidatoConocimHabilidad(idCandidato);
			guardarCandidatoHabilidades(idCandidato, registro.getIdHabilidades());
			//candidatoFacade.borrarHistoriaLaboral(idCandidato);
			if(registro.getHistorialLaboralVO()!= null && registro.getHistorialLaboralVO().getIdCandidato()!= null && registro.getHistorialLaboralVO().getIdCandidato()!= 0L){
				borrarRegistrosYRegistrarHistorialLaboralMapeo(registro.getHistorialLaboralVO());
			}
	

			//--------------------------------------------------------------------------------------------

			borrarYGuardarGradoAcademico(idCandidato, registro);
			
			

			//--------------------------------------------------------------------------------------------

			registroBitacora(EVENTO.REGENERACION_CUENTA_CANDIDATO, ID_USUARIO_ANONIMO, EVENTO.REGISTRO_CANDIDATOS.getEvento(),
						     EVENTO.REGENERACION_CUENTA_CANDIDATO.getEvento() +", CURP:"+ registro.getCurp(),
							 idCandidato, TIPO_PROPIETARIO.CANDIDATO);



			//seguimientoAtencionAppService.registroCuentaPersonal(idUsuario, PERFIL.CANDIDATO.getIdOpcion());

			bitacoraFacade.registraMovimiento(EVENTO.REGISTRA_CUENTA_PERSONAL, fecha.getTime(), idUsuario, PERFIL.CANDIDATO.getIdOpcion());

			logger.info("Candidato actualizado, idCandidato ["+ idCandidato +"], CURP ["+ registro.getCurp() +"]");
			
		} catch(PersistenceException e){
			e.printStackTrace(); logger.error(e);
			throw e;
		} catch(EncodingException e) {
			e.printStackTrace(); logger.error(e);
			throw new TechnicalException(e);
		} catch (Exception e) {
			e.printStackTrace(); logger.error(e);
			throw new TechnicalException(e);
		}
		
		return idCandidato;
	}

	private void actualizarRegistroUsuario(RegistroCandidatoVO registro,
			Calendar fecha, long idUsuario) throws EncodingException {
		UsuarioVO usuarioVO = new UsuarioVO();
		usuarioVO.setIdUsuario          (idUsuario);
		usuarioVO.setUsuario			(registro.getUsuario());
		usuarioVO.setContrasena        	(Password.codificaPassword(registro.getContrasena()));
		usuarioVO.setNombre				(registro.getNombre());
		usuarioVO.setApellido1			(registro.getApellido1());
		usuarioVO.setApellido2			(registro.getApellido2());
		usuarioVO.setCorreoElectronico	(registro.getCorreoElectronico());
		usuarioVO.setEstatus           	(ESTATUS.ACTIVO.getIdOpcion());
		usuarioVO.setFechaModificacion 	(fecha.getTime());
		//usuarioVO.setFechaAlta		   	(fecha.getTime());
		//usuarioVO.setIdPerfil      	   	(PERFIL.CANDIDATO.getIdOpcion());
		//usuarioVO.setIdRegistro        	(ID_REGISTRO_PORTAL);
		//usuarioVO.setIdTipoUsuario     	(TIPO_USUARIO.CANDIDATO.getIdTipoUsuario());

		usuarioFacade.actualizaRegistroUsuario(usuarioVO);
	}




	private void borrarYGuardarCandidato(long idCandidato,
			RegistroCandidatoVO registro, Calendar fecha, long idUsuario) {
		int edad = Utils.calculaEdad(registro.getFechaNacimiento());
		
		CandidatoVo candidatoVo =  new CandidatoVo();
		candidatoVo.setIdCandidato      (idCandidato);
		candidatoVo.setIdUsuario		(idUsuario);
		candidatoVo.setEdad             (edad);
		
		// DATOS DE INFONAVIT
		if(registro.getNumeroSeguroSocial() != null && !registro.getNumeroSeguroSocial().trim().isEmpty()){
			candidatoVo.setNss(registro.getNumeroSeguroSocial());
		}
		if(registro.getCreditoInfonavit() != null){
			candidatoVo.setCreditoInfonavit(registro.getCreditoInfonavit());
		}

		//candidatoVo.setCurp                (registro.getCurp());
		//candidatoVo.setNombre              (registro.getNombre());
		//candidatoVo.setApellido1           (registro.getApellido1());
		//candidatoVo.setApellido2           (registro.getApellido2());				
		//candidatoVo.setGenero              (registro.getGenero());						
		//candidatoVo.setIdEntidadNacimiento (registro.getIdEntidadNacimiento());
		//candidatoVo.setFechaNacimiento	 (registro.getFechaNacimiento());
		//candidatoVo.setFechaAlta           (fecha);
		//candidatoVo.setFechaConfirma       (fecha.getTime());
		//candidatoVo.setIdEstadoCivil       (registro.getIdEstadoCivil());
		candidatoVo.setEstatus             (ESTATUS.ACTIVO.getIdOpcion());
		candidatoVo.setFechaUltimaActualizacion (fecha.getTime());
		candidatoVo.setCorreoElectronico   (registro.getCorreoElectronico());
		//candidatoVo.setEstiloCv(0);
		
		candidatoVo.setIdMedioPortal       (registro.getIdComoEnteraste());
		
		int confidencialidadDatos = CONFIDENCIALIDAD.NO.getIdOpcion();
		if (registro.getDatosConfidenciales()==CONFIDENCIALIDAD.SI.getIdOpcion()){
			confidencialidadDatos = CONFIDENCIALIDAD.SI.getIdOpcion();
		}
		
		candidatoVo.setConfidencialidadDatos (confidencialidadDatos);
		
		int veracidadDatos = CONFIDENCIALIDAD.NO.getIdOpcion();
		int aceptacionTerminos = ACEPTACION_TERMINOS_CURRICULUM.NO.getIdOpcion();
		
		if (registro.getAceptacionTerminos()==ACEPTACION_TERMINOS_CURRICULUM.SI.getIdOpcion()){
			veracidadDatos = CONFIDENCIALIDAD.SI.getIdOpcion();
			aceptacionTerminos = ACEPTACION_TERMINOS_CURRICULUM.SI.getIdOpcion();				
		}
		
		candidatoVo.setVeracidadDatos		(veracidadDatos);
		candidatoVo.setAceptacionTerminos	(aceptacionTerminos);
		candidatoVo.setApoyoProspera		(registro.getApoyoProspera());
		candidatoVo.setFolioProspera		(registro.getFolioProspera());
		candidatoVo.setFolioIntegranteProspera(registro.getFolioIntegranteProspera());

		candidatoFacade.actualizaRegistroCandidato(candidatoVo);
	}

	private void borrarYGuardarPerfilLaboral(long idCandidato, RegistroCandidatoVO registro, Calendar fecha) {
		PerfilLaboralVo perfilLaboral = new PerfilLaboralVo();
		
		mapearPerfilLaboralVo(idCandidato, registro, perfilLaboral);
		
		if (perfilLaboralFacade.tienePerfilLaboral(idCandidato)){
			logger.info("Perfil Laboral no localizado : "+ idCandidato +" se procedo la edicion");
			perfilLaboralFacade.actualizaRegistroPerfilLaboral(perfilLaboral);
		} else {
			logger.info("Perfil Laboral no localizado : "+ idCandidato +" se realizara el registro");
			perfilLaboral.setFechaAlta(fecha.getTime());
			perfilLaboralFacade.save(perfilLaboral);
		}
	}

	private void mapearPerfilLaboralVo(long idCandidato, RegistroCandidatoVO registro, PerfilLaboralVo perfilLaboral) {
		long idRecibeOferta = 0;
		
		if (registro.getNotificarCel()==RECIBE_OFERTA.TELEFONO.getIdRecibeOferta() &&
		    registro.getNotificarCorreo()==RECIBE_OFERTA.CORREO.getIdRecibeOferta()){
		
			idRecibeOferta = RECIBE_OFERTA.AMBOS.getIdRecibeOferta();
		} else if (registro.getNotificarCel()==RECIBE_OFERTA.TELEFONO.getIdRecibeOferta()){
			idRecibeOferta = RECIBE_OFERTA.TELEFONO.getIdRecibeOferta();
		} else if (registro.getNotificarCorreo()==RECIBE_OFERTA.CORREO.getIdRecibeOferta()){
			idRecibeOferta = RECIBE_OFERTA.CORREO.getIdRecibeOferta();
		} else {
			idRecibeOferta = RECIBE_OFERTA.NO.getIdRecibeOferta();
		}
		
		int computacionBasica   = COMPU_BASICA.NO.getIdOpcion();
		int computacionAvanzada = COMPU_AVANZADA.NO.getIdOpcion();
		
		if (registro.getConocimientoCompNinguno()!=1){
			computacionBasica = COMPU_BASICA.SI.getIdOpcion();
			
			perfilLaboral.setIdExperienciaCompu			(EXPERIENCIA.MENOR_TRES.getIdOpcion()); 
			perfilLaboral.setIdDominioCompu				(DOMINIO.INTERMEDIO.getIdOpcion());
			
			if (registro.getConocimientoCompProcesadorTxt()==1 || registro.getConocimientoCompHojaCal()==1){
				perfilLaboral.setIdExperienciaOffice	(EXPERIENCIA.MENOR_TRES.getIdOpcion());
				perfilLaboral.setIdDominioOffice		(DOMINIO.INTERMEDIO.getIdOpcion());
			}
			
			if (registro.getConocimientoCompInternet()==1 || registro.getConocimientoCompRedes()==1){
				perfilLaboral.setIdExperienciaInternet	(EXPERIENCIA.MENOR_CUATRO.getIdOpcion());
				perfilLaboral.setIdDominioInternet		(DOMINIO.INTERMEDIO.getIdOpcion());
			}
		}
		
		int sinEstudios = ESCOLARIDAD.CON_ESTUDIOS.getIdOpcion();
		
		if (GRADO_ESTUDIOS.SIN_INSTRUCCION.getIdOpcion() == registro.getIdGradoEstudio()){
			sinEstudios = ESCOLARIDAD.SIN_ESTUDIOS.getIdOpcion();
		}
		
		int sinExperiencia = EXPERIENCIA_LABORAL.CON_EXPERIENCIA.getIdOpcion();
		
		if (registro.getIdExperienciaTotal()==EXPERIENCIA.NINGUNA.getIdOpcion()){
			sinExperiencia = EXPERIENCIA_LABORAL.SIN_EXPERIENCIA.getIdOpcion();
		}
		
		int contactoCorreo = CONTACTO_CORREO.SI.getIdContactoCorreo();
		if (registro.getMediocontactoCorreo()!=CONTACTO_CORREO.SI.getIdContactoCorreo())
			contactoCorreo = CONTACTO_CORREO.NO.getIdContactoCorreo();
		
		int contactoTelefono = CONTACTO_TELEFONO.SI.getIdContactoTelefono();
		if (registro.getMediocontactoTel()!=CONTACTO_TELEFONO.SI.getIdContactoTelefono())
			contactoTelefono = CONTACTO_TELEFONO.NO.getIdContactoTelefono();
		
		perfilLaboral.setIdCandidato				(idCandidato);
		perfilLaboral.setContactoCorreo				(contactoCorreo);
		perfilLaboral.setContactoTelefono			(contactoTelefono);
		perfilLaboral.setHorarioContactoDe			(HORARIO.NUEVE_AM.getIdOpcion());
		perfilLaboral.setHorarioContactoA			(HORARIO.NUEVE_PM.getIdOpcion());
		perfilLaboral.setIdRecibeOferta				(idRecibeOferta);
		perfilLaboral.setEmpleadoActualmente		(registro.getTrabajas());
		perfilLaboral.setIdRazonBusqueda			(registro.getIdMotivo());
		perfilLaboral.setDescripcionOtroMotivoBusq	(registro.getDescripcionOtroMotivoBusq());
		perfilLaboral.setInicioBusqueda				(registro.getFechaInicioBusqueda());
		perfilLaboral.setIdExperienciaTotal			(registro.getIdExperienciaTotal());
		perfilLaboral.setExperiencia				(registro.getDescripcionExperiencia());
		perfilLaboral.setDisponibilidadViajar		(registro.getViajar());
		perfilLaboral.setDisponibilidadRadicar		(registro.getRadicar());
		perfilLaboral.setComputacionBasica			(computacionBasica);
		perfilLaboral.setComputacionAvanzada		(computacionAvanzada);
		perfilLaboral.setSinEstudios           		(sinEstudios);
		perfilLaboral.setSinExperiencia				(sinExperiencia);
		//perfilLaboral.setIdSectorMayorExpr		(idSectorMayorExpr);
		//perfilLaboral.setIdAreaLaboralMayorExpr	(idAreaLaboralMayorExpr);
		//perfilLaboral.setIdOcupacionMayorExpr		(idOcupacionMayorExpr);
		//perfilLaboral.setFotografia				(fotografia);
		//perfilLaboral.setUrlVideoc			   	(urlVideoc);
		//perfilLaboral.setPuestoMayorExpr	   		(puestoMayorExpr);
		//perfilLaboral.setTerminosVideoc		   	(terminosVideoc);
		//perfilLaboral.setDescripcionVideoc	   	(descripcionVideoc);
		//perfilLaboral.setEstatusVideoc         	(estatusVideoc);
		//perfilLaboral.setFechaAlta			   	(fecha.getTime());
	}

	private void borrarYGuardarLaboralDeseado(long idCandidato,
			RegistroCandidatoVO registro) {
		long idAreaLaboralDeseada = 0;
		/**
		int idOcupacionDeseada = registro.getIdOcupacionDeseada();
		String idOcupacion = String.valueOf(idOcupacionDeseada);
		
		/** El id del Area son las dos primeras cifras del id de la ocupacion **/
		/**if (!idOcupacion.isEmpty() && idOcupacion.length()>=2){
			idAreaLaboralDeseada = Utils.parseLong(idOcupacion.substring(0, 2));
		}
		/**
		ExpectativaLaboralVO expectativaLaboral = new ExpectativaLaboralVO();
		expectativaLaboral.setIdAreaLaboralDeseada	(idAreaLaboralDeseada);
		expectativaLaboral.setIdOcupacionDeseada    (registro.getIdOcupacionDeseada());
		expectativaLaboral.setSalarioPretendido		(registro.getSalarioPretendido());
		expectativaLaboral.setIdTipoEmpleoDeseado	(registro.getIdTipoEmpleoDeseado());
		expectativaLaboral.setIdTipoContrato		(registro.getIdTipoContratoDeseado());
		expectativaLaboral.setPrincipal				(MULTIREGISTRO.PRINCIPAL.getIdOpcion());
		//expectativaLaboral.setIdExpectativaLaboral(idExpectativaLaboral);
		//expectativaLaboral.setIdSectorDeseado		(idSectorDeseado);
		//expectativaLaboral.setPuestoDeseado		(puestoDeseado);
		**/
		
		candidatoFacade.borrarExpectativaLaboral(idCandidato);
		
		guardarLaboralDeseado(registro, idCandidato);
	}

	private void actualizarBorrarOInsertarConocimientoComputacion(
			long idCandidato, RegistroCandidatoVO registro) {
		if (registro.getConocimientoCompNinguno()!=1){
		
			ConocimientoComputacionVO conocimientos = conocimientoComputacionFacade.consultaConocimientosComputacion(idCandidato, TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
			
			if (conocimientos==null){
				conocimientoComputacionFacade.registraConocimientosComputacion(idCandidato, TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario(),
																			   registro.getConocimientoCompProcesadorTxt(),
																			   registro.getConocimientoCompHojaCal(),
																			   registro.getConocimientoCompInternet(),
																			   registro.getConocimientoCompRedes(),
																			   registro.getConocimientoCompOtros());
			} else {
				long idCandidatoComputacion = conocimientos.getIdConocimientoComputacion();

				conocimientoComputacionFacade.actualizarConocimientosComputacion(idCandidatoComputacion,
						 														 idCandidato, TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario(),
						 														 registro.getConocimientoCompProcesadorTxt(),
						 														 registro.getConocimientoCompHojaCal(),
						 														 registro.getConocimientoCompInternet(), 
						 														 registro.getConocimientoCompRedes(),
						 														 registro.getConocimientoCompOtros());
			}			
		} else {
			conocimientoComputacionFacade.borrarConocimientosComputacion(idCandidato, TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
		}
	}

	private void borrarYguardarPerfilLaboral(long idCandidato,
			RegistroCandidatoVO registro, Calendar fecha) {
		perfilLaboralFacade.eliminaOtrosMedios(idCandidato);

		// Update descripcionOtroMotivoBusq
		PerfilLaboralVo perfilLaboralVo = perfilLaboralFacade.find(idCandidato);
		perfilLaboralVo.setDescripcionOtroMotivoBusq(registro.getDescripcionOtroMotivoBusq());
		perfilLaboralFacade.actualizaRegistroPerfilLaboral(perfilLaboralVo);

		guardarPerfilLaboralOtroMedio(idCandidato, registro, fecha);
	}

	private void guardarPerfilLaboralOtroMedio(long idCandidato,
			RegistroCandidatoVO registro, Calendar fecha) {
		if (registro.getIdOtrosMedios()!=null){
			for (long idMedioBusqueda : registro.getIdOtrosMedios()) {
				perfilLaboralFacade.registraOtroMedio(idCandidato, idMedioBusqueda, fecha.getTime());
			}
		}
	}

	private void borrarYGuardarIdiomas(long idCandidato,
			RegistroCandidatoVO registro) {
		
		candidatoFacade.borrarIdiomas(idCandidato);
		
		agregarIdioma(idCandidato, registro);
	}

	private void agregarIdioma(long idCandidato, RegistroCandidatoVO registro) {
		IdiomaVO idioma = new IdiomaVO();
		//idioma.setIdCandidatoIdioma	(idCandidatoIdioma);
		//idioma.setIdCandidato		(idCandidato);
		//idioma.setfechaAlta;
		idioma.setIdIdioma			(registro.getIdIdioma());
		idioma.setIdCertificacion	(registro.getIdCertificacion());
		idioma.setIdDominio			(registro.getIdDominio());
		idioma.setPrincipal			(MULTIREGISTRO.PRINCIPAL.getIdOpcion());

		candidatoFacade.agregarIdioma(idCandidato, idioma);
	}

	private void borrarYGuardarGradoAcademico(long idCandidato,
			RegistroCandidatoVO registro) {
		
		candidatoFacade.borrarGradosAcademicos(idCandidato);

		agregarGrado(idCandidato, registro);
	}

	private void agregarGrado(long idCandidato, RegistroCandidatoVO registro) {
		GradoAcademicoVO gradoAcademico = new GradoAcademicoVO();
		gradoAcademico.setIdNivelEstudio		(registro.getIdGradoEstudio());
		gradoAcademico.setIdSituacionAcademica	(registro.getIdSituacionAcademica());
		gradoAcademico.setIdCarreraEspecialidad	(registro.getIdCarreraEspecialidad());
		gradoAcademico.setPrincipal				(MULTIREGISTRO.PRINCIPAL.getIdOpcion());
		//gradoAcademico.setIdCandidatoGradoAcademico(idCandidatoGradoAcademico);
		//gradoAcademico.setEscuela				(escuela);
		//gradoAcademico.setInicio				(inicio);
		//gradoAcademico.setFin					(fin);
		
		candidatoFacade.agregarGrado(idCandidato, gradoAcademico);
	}

	private void guardarTelefonoRegistroCandidato(long idCandidato,
			RegistroCandidatoVO registro, Calendar fecha) {

		telefonoFacade.borrarTelefonos(idCandidato, TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());

		
		guardarTelefonoFacade(idCandidato, registro, fecha);
	}

	private void guardarTelefonoFacade(long idCandidato,
			RegistroCandidatoVO registro, Calendar fecha) {
		TelefonoVO tel = new TelefonoVO();
		tel.setIdPropietario		(idCandidato);
		tel.setIdTipoPropietario	(TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
		tel.setIdTipoTelefono		(registro.getTipoTelefono());
		tel.setAcceso				(registro.getAcceso());
		tel.setClave				(registro.getClave());
		tel.setTelefono				(registro.getTelefono());
		tel.setExtension			(registro.getExtension());
		tel.setPrincipal			(MULTIREGISTRO.PRINCIPAL.getIdOpcion());
		tel.setFechaAlta			(fecha.getTime());

		
		telefonoFacade.save(tel);
	}

	private void guardarDomicilioDelRegistroCandidato(long idCandidato,
			RegistroCandidatoVO registro, Calendar fecha) {
		DomicilioVO domicilio = new DomicilioVO();
		domicilio.setIdPropietario     (idCandidato);
		domicilio.setIdTipoPropietario (TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
		domicilio.setCodigoPostal      (registro.getCodigoPostal());
		domicilio.setIdEntidad         (registro.getIdEntidad());
		domicilio.setIdMunicipio       (registro.getIdMunicipio());
		domicilio.setIdColonia         (registro.getIdColonia());
		domicilio.setNumeroExterior    (registro.getNumeroExterior());
		domicilio.setNumeroInterior    (registro.getNumeroInterior());
		domicilio.setCalle             (registro.getCalle());
		domicilio.setEntreCalle        (registro.getEntreCalle());
		domicilio.setyCalle            (registro.getyCalle());
		domicilio.setFechaAlta         (fecha.getTime());
		/**Para Geolocalizacion**/
		domicilio.setLatitud		   (registro.getLatitud());
		domicilio.setLongitud		   (registro.getLongitud());
		
		DomicilioVO dom = domicilioFacade.buscarDomicilioIdPropietario(idCandidato, TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
		
		if (dom==null){
			domicilioFacade.save(domicilio);
		} else {
			domicilio.setIdDomicilio(dom.getIdDomicilio());
			domicilioFacade.update(domicilio);
		}
	}
	
	public void notificaRecuperacionPsw(String correoElectronico, long idCandidato, String contrasena, String nombre, String apellido1, String apellido2) throws MailException {
		CandidatoVo candidato = candidatoFacade.find(idCandidato);
		UsuarioVO usuarioVO = usuarioFacade.find(candidato.getIdUsuario());
		candidato.setUsuarioVO(usuarioVO);
		NotificacionService notificacionService = new NotificacionService();
		notificacionService.notificacionRecuperacionPsw(candidato, correoElectronico, contrasena);
	}

	public void notificaRegistroCandidato(String correoElectronico, String usuario, String contrasena, String nombre,
			String apellido1, String apellido2, int estatusPPC) throws MailException {
		Calendar fecha = Calendar.getInstance();
		NotificacionService notificacionService = new NotificacionService();
		notificacionService.notificacionRegistroCandidato(fecha.getTime(), correoElectronico, usuario, contrasena, estatusPPC,
				null, null, nombre, apellido1, apellido2, null, null);
	}
	
	public void notificaRegistroCandidatoPpc(String correoElectronico, String usuario, String contrasena, int estatusPPC, 
			int entidadFederativa, int municipio, String nombre, String apellido1, String apellido2, String curp, 
			String numeroSeguridadSocial) throws MailException {
			
		if(entidadFederativa>0){

			Calendar fecha = Calendar.getInstance();
			NotificacionService notificacionService = new NotificacionService();
			
			try{
								
				String entidadDescripcion = catalogoOpcionFacade.getOpcionById(CATALOGO_OPCION_ENTIDAD_FEDERATIVA, entidadFederativa);
				String municipioDescripcion = null;
				
				long lngEntidad = (long) entidadFederativa;
				long lngMunicipio = (long) municipio;
				
				MunicipioVO municipioVo = domicilioFacade.consultaMunicipio(lngMunicipio, lngEntidad);

				if(null!=municipioVo){
					municipioDescripcion = municipioVo.getMunicipio();
				}			
				
				notificacionService.notificacionRegistroCandidato(fecha.getTime(), correoElectronico, usuario, contrasena, estatusPPC,
						entidadDescripcion, municipioDescripcion, nombre, apellido1, apellido2, curp, numeroSeguridadSocial);		
				
			} catch (Exception e) {
				e.printStackTrace(); logger.error(e);
			}		
		}		
	}
	
	@Override
	public void registrar(CandidatoVo candidatoVo,long idAdministrador) throws BusinessException, PersistenceException, TechnicalException, MailException{
		String mensajeBitacora = "";

		try {
			if(!usuarioFacade.esCorreoUnico(candidatoVo.getCorreoElectronico())){			
					  throw new BusinessException("El correo " 
							  + candidatoVo.getCorreoElectronico() + 
							  " ya se encuentra registrado. Favor de elegir otro.");
			}			
			if(!candidatoFacade.repetidaCurp(candidatoVo.getCurp())){
				//candidatoFacade.save(candidatoVo);	
				//Se registra el domicilio (estado y municipio) del candidato 
				candidatoVo.getDomicilioVo().setIdPropietario(candidatoVo.getIdCandidato());
				domicilioFacade.save(candidatoVo.getDomicilioVo());
				//
				if(idAdministrador == ID_USUARIO_ANONIMO){
						candidatoAppService.notificaCandidato(candidatoVo);
						mensajeBitacora = "Registrando un nuevo Candidato con la curp " 
							+ candidatoVo.getCurp();
					} else {
						candidatoAppService.notificaCandidatoInactivaRegistrando(candidatoVo, idAdministrador);
						mensajeBitacora = "El registro fue realizado por el Administrador del Portal " 
							+ idAdministrador + "con la curp " 
							+ candidatoVo.getCurp();
					}
				registroBitacora(EVENTO.REGISTRO_CANDIDATOS,idAdministrador,
								 EVENTO.REGISTRO_CANDIDATOS.getEvento(),
								 mensajeBitacora, candidatoVo.getIdCandidato(), TIPO_PROPIETARIO.CANDIDATO);
			} else {
				String mensajeException =
					candidatoVo.getNombre() + " : </br>" + 
					"Te informamos que ya estas registrado en el Portal del Empleo, </br>" + 
					"por lo que el siguiente paso es que recuperes tu contraseï¿½a </br>" +
					"para lo cual debes proporcionar tu CURP (" + candidatoVo.getCurp() + ")y tu cuenta de </br>" + 
					"correo electrï¿½nico que registraste. </br>" +
					"<p> <a href='recuperarpsw.do?method=init&tipo=Candidato'>Recuperar contraseï¿½a</a> </p>";
				throw new BusinessException(mensajeException);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public CandidatoVo consultaCURPPorDatosPersonales(CandidatoVo candidatoVo)throws ConsultaWsPorCurpException {		
		CandidatoVo candidato = null;
		try {
			ParametroVO parametro = parametroFacade.findById(ID_PARAMETRO_URL_WS_RENAPO_CONSULTA_POR_DETALLE);
			String targetEndpoint = parametro.getValor();
			// Llamada directa al servicio web			
			logger.info("CandidatoRegistroAppService.consultaCURPPorDatosPersonales: se va a invocar "+targetEndpoint);
			candidato = CurpServiceLocator.getInstance().consultaCURPPorDatosPersonales(candidatoVo, targetEndpoint);
			
			// Llamada a Renapo mediante puente a produccion
			//candidato = CurpServiceLocator.getInstance().consultaCURPPorDatosPersonales(candidatoVo.getNombre(), candidatoVo.getApellido1(), candidatoVo.getApellido2(), candidatoVo.getGenero(), candidatoVo.getFechaNacimiento(), candidatoVo.getIdEntidadNacimiento());
			
		} catch (Exception e) {
			logger.error("Ha ocurrido un error en CandidatoRegistroAppService.consultaCURPPorDatosPersonales");
			logger.error(e.getMessage());			
			e.printStackTrace();
			throw new ConsultaWsPorCurpException(e);
		}
		return candidato;
		
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public CandidatoVo consultaDatosPersonalesPorCURP(String CURP) throws ConsultaWsPorCurpException{	
	CandidatoVo candidato = null;
		try {
			ParametroVO parametro = parametroFacade.findById(ID_PARAMETRO_URL_WS_RENAPO_CONSULTA_POR_CURP);
			String targetEndpoint = parametro.getValor();

			// Llamada directa a servicio web de Renapo
			logger.info("CandidatoRegistroAppService.consultaDatosPersonalesPorCURP: CURP = "+CURP);
			logger.info("CandidatoRegistroAppService.consultaDatosPersonalesPorCURP: se va a invocar "+targetEndpoint);
			candidato = CURPServiceImpl.getInstance().consultaDatosPersonalesPorCURP(CURP, targetEndpoint);
			
			// Llamada a Renapo mediante puente a produccion
			//candidato = CurpServiceLocator.getInstance().consultaDatosPersonalesPorCURP(CURP);

		} catch (Exception e) {
			logger.error("Ha ocurrido un error en CandidatoRegistroAppService.consultaDatosPersonalesPorCURP");
			logger.error(e.getMessage());
			e.printStackTrace();
			throw new ConsultaWsPorCurpException(e);
		}
		return candidato;		
	}

	@Override
	public Boolean activarCandidato(long idCandidato, String correoElectronico) throws BusinessException {	
		Boolean bandera =  false;
		long idUsuario; 

		if(!candidatoFacade.idCorreo(idCandidato, correoElectronico)){
			throw new BusinessException("El correo electronico " + correoElectronico +" no corresponde al Id");
		}
		
		/** 13/12/2011 OMC
		 * Cuando se confirma el registro se actualiza el estatus del usuario (INACTIVO),
		 * y en la confirmacion por correo se verifica que se encuentre INACTIVO, sin embargo, la unica manera de llegar a este metodo es atraves
		 * del correo, por lo tanto la validacion del estatus esta de mas y se omite para prevenir que no se pueda activar la cuenta
		 ***/
		//if(candidatoFacade.usuarioInactivo(confirmacionRegistroVo)){
			idUsuario =  candidatoFacade.activarCandidato(idCandidato);
			usuarioFacade.activarUsuario(idUsuario);
		   	
			bandera = true;
		   	registroBitacora(EVENTO.ACTIVACION_CANDIDATO,idUsuario, EVENTO.ACTIVACION_CANDIDATO.getEvento(),
						     "Activando al candidato " + idUsuario, idCandidato, TIPO_PROPIETARIO.CANDIDATO);
		/*}else{
			throw new BusinessException("La cuenta del Candidato no se encuentra con estatus " +
											"Inactiva y/o Modificada y el password no es el correcto");
		}*/
		
		return bandera;
	}

	/**
	 * Consulta los accesos 
	 */
	public AccesoOLAVO consultaAccesosOLA(long idPropietario, long idPerfil) throws SQLException {
		
		if (idPropietario<=0) throw new IllegalArgumentException("Identificador del Propietario requerido");
		if (idPerfil<=0) throw new IllegalArgumentException("Identificador del Perfil requerido");
		
		PropertiesLoader properties = PropertiesLoader.getInstance();
		
		AccesoOLAVO accesos = new AccesoOLAVO();
		Context context = null;
    	Connection conn = null;

    	try {
    		ConnectionWraper wraper = ConexionFactory.getInstance().getConnection();
    		conn = wraper.getConnection();
    		context = wraper.getContext();

			AccesoOLADAO dao = new AccesoOLADAO(conn);
			
			
			if (idPerfil == PERFIL.CANDIDATO.getIdOpcion()){
				
				String clave = dao.getOcupacionPropietario(idPropietario);
				String urlOcupacion = properties.getProperty("accesoOLA.ligaOcupacion.login"); 
				urlOcupacion = urlOcupacion + dao.getOcupacionOLA(clave);
						
				String urlCarrera = "";

				int idCatalogo = 0;

				clave = dao.getCarreraPropietario(idPropietario);
				int pos = clave.indexOf("#");
				if (pos >= 0){
					idCatalogo = Integer.parseInt(clave.substring(pos + 1));
					clave = clave.substring(0, pos);
				}

				if(idCatalogo != CATALOGO.SIN_ESPECIALIDAD.getIdCatalogo()) {

					urlCarrera = properties.getProperty("accesoOLA.ligaCarrera.login");
					urlCarrera = urlCarrera + dao.getCarreraOLA(clave,idCatalogo);

				} else if(idCatalogo == CATALOGO.SIN_ESPECIALIDAD.getIdCatalogo()){
					urlCarrera = properties.getProperty("accesoOLA.ligaCarrera");
				}
				

				String tituloCarrera = properties.getProperty("accesoOLA.tituloCarrera.login") + " ";
				String tituloOcupacion = properties.getProperty("accesoOLA.tituloOcupacion.login") + " ";
				
				if (urlCarrera!=null){
					pos = urlCarrera.indexOf("%");

					if (urlCarrera.indexOf("%") >= 0){
						tituloCarrera = tituloCarrera + urlCarrera.substring(urlCarrera.indexOf("%") + 1);
					}

					if (pos >= 0){
						urlCarrera = urlCarrera.substring(0,pos);
					}

					pos = urlOcupacion.indexOf("%");

					if (urlOcupacion.indexOf("%") >= 0){
						tituloOcupacion = tituloOcupacion + urlOcupacion.substring(urlOcupacion.indexOf("%") + 1);
					}

					if (pos >= 0){
						urlOcupacion = urlOcupacion.substring(0, pos);
					}
				}

				accesos.setUrlCarrera(urlCarrera);
				accesos.setUrlOcupacion(urlOcupacion);
				accesos.setTituloCarrera(tituloCarrera);
				accesos.setTituloOcupacion(tituloOcupacion);
			
			} else {
				accesos.setUrlCarrera(properties.getProperty("accesoOLA.ligaCarrera"));
				accesos.setTituloCarrera(properties.getProperty("accesoOLA.tituloCarrera"));
				accesos.setUrlOcupacion(properties.getProperty("accesoOLA.ligaOcupacion"));
				accesos.setTituloOcupacion(properties.getProperty("accesoOLA.tituloOcupacion"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace(); throw e;
		} catch (Exception e) {
			e.printStackTrace(); throw new SQLException(e);
		} finally {
			try{if (conn!=null) conn.close();}catch(Exception e){e.printStackTrace();}
			
			if (context!=null){
				logger.info("Cerrando context");
				try {
					context.close();
				} catch (Exception e) {e.printStackTrace();}
			}
		}

		return accesos;
	}
	@Override
	public void notificarCandidato(String correoElectronico, String nombre, String tituloOferta, Boolean contratacion, String tipoPersona) throws MailException {
		Calendar fecha = Calendar.getInstance();
		NotificacionService notificacionService = new NotificacionService();
		notificacionService.notificarCandidato(fecha.getTime(), correoElectronico, nombre, tituloOferta, contratacion, tipoPersona);
	}

	@Override
	public void notificarCandidatoVinculado(String correoElectronico, String nombre, String tituloOferta,String  tipoPersona) throws MailException {
		Calendar fecha = Calendar.getInstance();
		NotificacionService notificacionService = new NotificacionService();
		notificacionService.notificarCandidatoVinculado(fecha.getTime(), correoElectronico, nombre, tituloOferta, tipoPersona);
	}
	
	public int guardarCandidatoHabilidades(long idCandidato, long[] habilidades){
		return candidatoFacade.guardarCandidatoHabilidades(idCandidato, habilidades);
	}

	public long borrarRegistrosYRegistrarHistorialLaboral(HistoriaLaboralVO historiaLaboralVO) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException, NotFoundAnnotationException{
		historiaLaboralFacade.borrar(historiaLaboralVO.getIdCandidato());
		return registrarHistorialLaboral(historiaLaboralVO);
	}
	
	public long borrarRegistrosYRegistrarHistorialLaboralMapeo(HistoriaLaboralVO historiaLaboralVO) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException, NotFoundAnnotationException{
		historiaLaboralFacade.borrar(historiaLaboralVO.getIdCandidato());
		return registrarHistorialLaboralMapeoAMano(historiaLaboralVO);
	}
	public long registrarHistorialLaboral(HistoriaLaboralVO historiaLaboralVO) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException, NotFoundAnnotationException{
		
		return historiaLaboralFacade.create(historiaLaboralVO);
	}
	//Mapeo a mano para registro candidato
	public long registrarHistorialLaboralMapeoAMano(HistoriaLaboralVO historiaLaboralVO) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException, NotFoundAnnotationException{
		HistoriaLaboral historiaLaboral = new HistoriaLaboral();
		
		historiaLaboral.setIdCandidato(historiaLaboralVO.getIdCandidato());
		historiaLaboral.setEmpresa(historiaLaboralVO.getEmpresa());
		
		historiaLaboral.setPuesto(historiaLaboralVO.getPuesto());
		
		historiaLaboral.setSalarioMensual(historiaLaboralVO.getSalarioMensual());
		
		historiaLaboral.setIdJerarquia(historiaLaboralVO.getIdJerarquia());
		historiaLaboral.setPersonasCargo(historiaLaboralVO.getPersonasCargo());
		historiaLaboral.setFuncion(historiaLaboralVO.getFuncion());
		
		historiaLaboral.setLaboresFinal(historiaLaboralVO.getLaboresFinal());
		historiaLaboral.setLaboresInicial(historiaLaboralVO.getLaboresInicial());
		historiaLaboral.setAniosLaborados(historiaLaboralVO.getAniosLaborados());
		
		historiaLaboral.setConfidencialidadEmpresa(historiaLaboralVO.getConfidencialidadEmpresa());
		//para el el pdf necesitamos si es el trabajo actual
		historiaLaboral.setTrabajoActual(historiaLaboralVO.getTrabajoActual());
		//private String confidencial;
		//private int omitirTrabajoEnPerfil;
		//solo hay un regisro entonces es el principal
		historiaLaboral.setPrincipal(historiaLaboralVO.getPrincipal());
		return historiaLaboralFacade.create(historiaLaboral);
	}

	@Override
	public int persistCandidatoQuebec(long idCandidato) throws SQLException {
		return candidatoQuebecFacade.persistCandidatoQuebec(idCandidato);
	}

	@Override
	public CandidatoQuebecVO findByID(long idCandidato) throws SQLException {
		return candidatoQuebecFacade.findByID(idCandidato);
	}

	@Override
	public void guardarCandidatoGeolocalizacion(long idCandidato, RegistroCandidatoVO registroCandidatoVO, Date fechaCreacion) throws SQLException {

		// Consume GeoNames service
		//String pais = GeoNames.requestCountryCode(registroCandidatoVO.getPositionCoordsLatitude(), registroCandidatoVO.getPositionCoordsLongitude());

		CandidatoGeolocalizacionVO candidatoGeolocalizacionVO = new CandidatoGeolocalizacionVO();
		candidatoGeolocalizacionVO.setIdCandidato(idCandidato);
		candidatoGeolocalizacionVO.setLatitud(registroCandidatoVO.getPositionCoordsLatitude());
		candidatoGeolocalizacionVO.setLongitud(registroCandidatoVO.getPositionCoordsLongitude());
		candidatoGeolocalizacionVO.setIdioma(registroCandidatoVO.getAcceptLanguage());
		candidatoGeolocalizacionVO.setPais(registroCandidatoVO.getCountry());
		candidatoGeolocalizacionVO.setFechaCreacion(fechaCreacion);
		candidatoGeolocalizacionVO.setFechaActualizacion(fechaCreacion);

		this.candidatoGeolocalizacionFacade.create(candidatoGeolocalizacionVO);
	}
}