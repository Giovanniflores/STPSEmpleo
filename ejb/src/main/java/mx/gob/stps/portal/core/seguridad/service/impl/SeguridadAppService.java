package mx.gob.stps.portal.core.seguridad.service.impl;

import static mx.gob.stps.portal.core.infra.utils.Constantes.ID_PARAMETRO_WITH_TEMPLATE;
import static mx.gob.stps.portal.core.infra.utils.Constantes.ID_REGISTRO_PORTAL;
import mx.gob.stps.portal.core.infra.utils.Constantes.EVENTO;
import mx.gob.stps.portal.core.infra.utils.Constantes.PERFIL;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.autorizacion.vo.ParametroVO;
import mx.gob.stps.portal.core.candidate.service.CandidatoAppServiceLocal;
import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.cil.dao.IntegracionLaboralDAO;
import mx.gob.stps.portal.core.cil.vo.CilCodigoAccesoVO;
import mx.gob.stps.portal.core.empresa.dao.EmpresaDAO;
import mx.gob.stps.portal.core.empresa.service.EmpresaAppServiceLocal;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.data.ConexionFactory;
import mx.gob.stps.portal.core.infra.data.ConnectionWraper;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.EncodingException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.EVENTO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PERSONA;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_REGISTRO;
import mx.gob.stps.portal.core.infra.utils.MessageLoader;
import mx.gob.stps.portal.core.infra.utils.Password;
import mx.gob.stps.portal.core.persistencia.facade.BitacoraFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.CandidatoFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.CilCodigoAccesoFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.EmpresaFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.MovilSessionFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.ParametroFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.PerfilFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.PerfilLaboralFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.RegistroPorValidarFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.UsuarioFacadeLocal;
import mx.gob.stps.portal.core.seguridad.dao.AccionPerfilDAO;
import mx.gob.stps.portal.core.seguridad.exception.CorreoRepetidoException;
import mx.gob.stps.portal.core.seguridad.exception.LoginRepetidoException;
import mx.gob.stps.portal.core.seguridad.service.SeguridadAppServiceLocal;
import mx.gob.stps.portal.core.seguridad.service.SeguridadAppServiceRemote;
import mx.gob.stps.portal.core.seguridad.vo.AccionVO;
import mx.gob.stps.portal.core.seguridad.vo.ConfirmacionVO;
import mx.gob.stps.portal.core.seguridad.vo.PerfilVO;
import mx.gob.stps.portal.core.seguridad.vo.UsuarioVO;
import mx.gob.stps.portal.seguridad.vo.MovilSessionVO;
import mx.gob.stps.portal.utils.Catalogos.TIPO_EMPRESA;

import org.apache.log4j.Logger;

/**
 * Concentra los servicios para el modulo de seguridad
 * 
 * @author oscar.manzo
 *
 */
@Stateless(name = "SeguridadAppService", mappedName = "SeguridadAppService")
public class SeguridadAppService implements SeguridadAppServiceRemote, SeguridadAppServiceLocal {
	private static Logger logger = Logger.getLogger(SeguridadAppService.class);
	
	private static final HashMap<Integer, byte[]> hashImagenes = new HashMap<Integer, byte[]>();

	@EJB
	private UsuarioFacadeLocal usuarioFacade;

	@EJB
	private ParametroFacadeLocal parametroFacade;

	@EJB
	private EmpresaAppServiceLocal empresaAppService;

	@EJB
	private CandidatoAppServiceLocal candidatoAppService;

	@EJB
	private CandidatoFacadeLocal candidatoFacade;

	@EJB
	private EmpresaFacadeLocal empresaFacade;
	
	/*@EJB
	private EmpresaPorAutorizarFacadeLocal empresaPorAutorizarFacade;*/
	
	@EJB
	private PerfilFacadeLocal perfilFacade;

	@EJB
	private CilCodigoAccesoFacadeLocal cilCodigoAccesoFacade;	
	
	@EJB
	private BitacoraFacadeLocal bitacoraFacade;

	@EJB
	private RegistroPorValidarFacadeLocal registroPorValidarFacade;

	@EJB
	private PerfilLaboralFacadeLocal perfilLaboralFacade;
	
	//Start cambio movil
	@EJB
	private MovilSessionFacadeLocal movilSessionFacade;
	
	//Fin Cambio movil
	
	/* (non-Javadoc)
	 * @see mx.gob.stps.portal.core.seguridad.service.SeguridadAppServiceRemote#registraUsuario(mx.gob.stps.portal.core.seguridad.vo.UsuarioVO)
	 */
	public long registraUsuario(UsuarioVO vo) throws LoginRepetidoException, CorreoRepetidoException {
		
		if (vo==null) throw new IllegalArgumentException("Datos del usuario requeridos");
		if (vo.getUsuario()==null) throw new IllegalArgumentException("Nombre de usuario requerida");
		if (vo.getCorreoElectronico()==null) throw new IllegalArgumentException("Cuenta de correo requerida");
		if (vo.getContrasena()==null) throw new IllegalArgumentException("Contrase�a requerida");
		if (vo.getIdEntidad()<=0) throw new IllegalArgumentException("Identificador de Entidad requerido");
		if (vo.getIdTipoUsuario()<=0) throw new IllegalArgumentException("Identificador de Tipo de Usuario requerido");
		if (vo.getIdPerfil()<=0) throw new IllegalArgumentException("Identificador de Perfil requerido");
		
		// Se verifica que no existe un usuario con la misma cuenta de correo
		//UsuarioVO usuExistente = consultaUsuario(vo.getCorreoElectronico());
		boolean unico = usuarioFacade.esUsuarioUnico(vo.getUsuario());
		
		if (!unico){
			throw new LoginRepetidoException(vo.getCorreoElectronico());
		}

		if (vo.getCorreoElectronico()!=null){
			unico = usuarioFacade.esCorreoUnico(vo.getCorreoElectronico());
			
			if (!unico) throw new CorreoRepetidoException(vo.getCorreoElectronico());
		}
				
		vo.setFechaAlta(new Date());
		vo.setEstatus(ESTATUS.ACTIVO.getIdOpcion()); // Usuarios del sistema se generan Activos
		vo.setFechaModificacion(null);
		vo.setIdRegistro(ID_REGISTRO_PORTAL);

		long idUsuario = usuarioFacade.save(vo);
		
		return idUsuario;
	}

	/* (non-Javadoc)
	 * @see mx.gob.stps.portal.core.seguridad.service.SeguridadAppServiceRemote#consultaUsuario(long)
	 */
	public UsuarioVO consultaUsuario(long idUsuario){
		
		if (idUsuario<=0) throw new IllegalArgumentException("Identificador de usuario invalido");

		UsuarioVO vo = usuarioFacade.find(idUsuario);
		
		return vo;
	}
	public UsuarioVO consultaCandidatoPorID(String idCandidato2){
		if (idCandidato2 ==  null) throw new IllegalArgumentException("El id de candidato esta vacio"); 
		//long x = Long.parseLong(idCandidato2);
		UsuarioVO vo = usuarioFacade.findByCandidatoId(idCandidato2);
		return vo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.gob.stps.portal.core.seguridad.service.SeguridadAppServiceRemote#
	 * consultaUsuario(java.lang.String)
	 */

	public UsuarioVO consultaUsuarioporCorreo(String correoElectronico) {

		if (correoElectronico == null || correoElectronico.isEmpty())
			throw new IllegalArgumentException("Correo de usuario requerido");

		UsuarioVO vo = usuarioFacade.findbycorreo(correoElectronico);

		return vo;
	}

	/* (non-Javadoc)
	 * @see mx.gob.stps.portal.core.seguridad.service.SeguridadAppServiceRemote#consultaUsuario(java.lang.String)
	 */
	/*public UsuarioVO consultaUsuario(String correoElectronico){

		if (correoElectronico==null || correoElectronico.isEmpty()) throw new IllegalArgumentException("Correo de usuario requerido");
		
		UsuarioVO vo = usuarioFacade.find(correoElectronico);
		
		return vo;
	}*/

	public UsuarioVO consultaUsuarioPorLogin(String usuario){

		if (usuario==null || usuario.isEmpty()) throw new IllegalArgumentException("Login de usuario requerido");
		
		UsuarioVO vo = usuarioFacade.findByUsuario(usuario);
		
		return vo;
	}

	public long consultaPropietario(long idUsuario) throws SQLException{

		if (idUsuario<=0) throw new IllegalArgumentException("Identificador de usuario requerido");

		long idPropietario = usuarioFacade.consultaIdPropietario(idUsuario);

		return idPropietario;
	}
	/**
	 * Valida si un correo electronico es unico.
	 * @author Felipe Ju�rez Ram�rez
	 * @since 04/04/2011
	 * @param correoElectronico
	 * @throws SQLException
	 * @return boolean
	 **/
	@Override
	public boolean esCorreoUnico(String correoElectronico) throws SQLException {
		return usuarioFacade.esCorreoUnico(correoElectronico);
	}

	public String consultaParametroPlantilla(){
		String valor = null;
		try{
			ParametroVO parametro = parametroFacade.findById(ID_PARAMETRO_WITH_TEMPLATE);
			if (parametro!=null)
				valor = parametro.getValor();
		}catch(Exception e){
			logger.error(e);
		}

		return valor;
	}

	
	public String recuperaContrasenaCandidato(String usuario, String curp, String correoActual, String correoNuevo) throws LoginRepetidoException, BusinessException, TechnicalException, MailException {
		boolean cambiacorreo =  false;
		
		if (curp==null || curp.isEmpty()) throw new IllegalArgumentException("CURP de Candidato requerido");
		
		try {
			CandidatoVo candidato = candidatoFacade.consultaPorCURP(curp);
			
			if(candidato == null){
				throw new BusinessException("La curp " + curp + " no esta reg�strada en el sistema, favor de verificar");	
			}else if(!correoActual.toLowerCase().equals(candidato.getCorreoElectronico().toLowerCase())){
				throw new BusinessException("El correo " + correoActual + " no pertenece a la curp " + curp);
			}

			if (correoNuevo!=null && !correoNuevo.trim().isEmpty() && !correoNuevo.toLowerCase().equals(correoActual.toLowerCase())){

				boolean unico = usuarioFacade.esCorreoUnico(correoNuevo);
				
				if (!unico){
					throw new LoginRepetidoException("El correo " + correoNuevo + " ya se encuentra asignado.");
				}else{
					cambiacorreo = true;	
				}

				correoActual = correoNuevo;
			}
			
			if (cambiacorreo){
				usuarioFacade.updateEmail(candidato.getIdUsuario(), correoActual);
				candidatoFacade.actualizaCorreoElectronico(candidato.getIdCandidato(), correoActual);
				candidato.setCorreoElectronico(correoActual);
			}
			
			usuarioFacade.inactivarUsuario(candidato.getIdUsuario());
			
			candidatoFacade.inactivarCandidato(candidato.getIdCandidato());
			
			if (candidato.getCorreoElectronico()!=null){
				candidatoAppService.notificaCambioContrasena(candidato, correoActual);
			}
			
			logger.info("Se cambio CONTRASE�A IdCandidato:" + candidato.getIdCandidato());

		} catch (PersistenceException e) {
			logger.error(e);
			throw new TechnicalException(e);
		}
		
		return correoActual;
	}

	public String recuperaContrasenaEmpresa(String usuario, String idPortalEmpleo, String correoActual,
			String correoNuevo) throws LoginRepetidoException, BusinessException, TechnicalException, MailException {
		boolean cambiacorreo = false;

		if (idPortalEmpleo == null || idPortalEmpleo.isEmpty())
			throw new IllegalArgumentException("Identificador del Portal del Empleo requerido");
		if (correoActual == null || correoActual.isEmpty())
			throw new IllegalArgumentException("Cuenta de correo requerida");

		try {
			EmpresaVO empresa = empresaFacade.findByIdPortalEmpleo(idPortalEmpleo);
			if (empresa == null) {
				throw new BusinessException("El id del Portal " + idPortalEmpleo + " no existe, favor de verificar");
			} else if (!correoActual.toLowerCase().equals(empresa.getCorreoElectronico().toLowerCase())) {
				throw new BusinessException(
						"El correo " + correoActual + " no pertenece a la id del Portal " + idPortalEmpleo);
			}

			if (registroPorValidarFacade.existeRegistroPorValidar(TIPO_REGISTRO.EMPRESA, empresa.getIdEmpresa(),
					ESTATUS.PENDIENTE_PUBLICAR, ESTATUS.ASIGNADO_PUBLICADOR, ESTATUS.EN_EDICION_PUBLICADOR)) {
				throw new BusinessException(MessageLoader.getInstance().getMessage("autorizacion.registro.pendiente"));
			}

			if (correoNuevo != null && !correoNuevo.trim().isEmpty()
					&& !correoNuevo.toLowerCase().equals(correoActual.toLowerCase())) {

				boolean unico = usuarioFacade.esCorreoUnico(correoNuevo);

				if (!unico) {
					throw new LoginRepetidoException("El correo " + correoNuevo + " ya se encuentra asignado.");
				} else {
					cambiacorreo = true;
				}

				correoActual = correoNuevo;
			}

			if (cambiacorreo) {
				empresa.setCorreoElectronico(correoActual);
				usuarioFacade.updateEmail(empresa.getIdUsuario(), correoActual);
				empresaFacade.actualizaCorreoElectronico(empresa.getIdEmpresa(), correoActual);
				empresa.setCorreoElectronico(correoActual);
			}

			try {
				EmpresaDAO empresaDAO = new EmpresaDAO();
				empresaDAO.actualizaEstatusOfertasEmpresa(ESTATUS.EMP_MODIFICADA.getIdOpcion(), empresa.getIdEmpresa());
			} catch (Exception e) {
				logger.error("Error de tipo :-> " + e.getMessage());
			}

			usuarioFacade.inactivarUsuario(empresa.getIdUsuario());

			empresaFacade.actualizaEmpresaEstatus(empresa.getIdEmpresa(), ESTATUS.INACTIVO.getIdOpcion());

			if (correoActual != null) {
				empresaAppService.notificaCambioContrasena(empresa, correoActual);
			}

			logger.info("Se cambio CONTRASE�A Empresa:" + empresa.getIdEmpresa());

		} catch (PersistenceException e) {
			logger.error(e);
			throw new BusinessException("Empresa con ID del Portal del Empleo [" + idPortalEmpleo + "] no localizada.");
		} catch (MailException e) {
			logger.error(e);
			throw e;
		} /*
			 * catch (SQLException e) { logger.error(e); throw new
			 * TechnicalException(e); }
			 */

		return correoActual;
	}

	public String cambioContrasenaCandidato(long idCandidato, String contrasena)
			throws BusinessException, TechnicalException, PersistenceException, EncodingException {

		if (idCandidato <= 0)
			throw new IllegalArgumentException("idCandidato es requerido.");
		if (contrasena == null || contrasena.isEmpty())
			throw new IllegalArgumentException("Contrase�a es requerida.");

		CandidatoVo candidato = candidatoFacade.find(idCandidato);

		/*
		 * 12/12/2011 Jmhg:-> Se omite la validacion Cuando se solicita un
		 * cambio de contrase�a se actualiza el estatus del usuario (INACTIVO),
		 * y en la confirmacion por correo se verifica que se encuentre
		 * INACTIVO, sin embargo, la unica manera de llegar a este metodo es
		 * atraves del correo, por lo tanto la validacion del estatus esta de
		 * mas y se omite para prevenir que no se pueda activar la cuenta
		 */
		/*
		 * if(Constantes.ESTATUS.INACTIVO.getIdOpcion()!=
		 * candidato.getEstatus()){ logger.
		 * error("Confirmacion de Candidato con estatus inconsistente, idCandidato ("
		 * + idCandidato +"), Estatu ("+ candidato.getEstatus() +") "); //throw
		 * new BusinessException("El Usuario " +
		 * candidato.getCorreoElectronico() + " no tiene estatus de INACTIVO");
		 * }
		 */

		usuarioFacade.updatePassword(candidato.getIdUsuario(), Password.codificaPassword(contrasena));

		bitacoraFacade.save(EVENTO.CONFIRMA_CONTRASENA.getIdEvento(), candidato.getIdUsuario(),
				EVENTO.CONFIRMA_CONTRASENA.getEvento(), Calendar.getInstance(), null, idCandidato,
				TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());

		return "";
	}

	// SOLICITUD DE USUARIO Y CAMBIO CONTRASE�A OAM
	public String bitacoraRecuperaContrasena(long idUsuario, EVENTO evento, long idRegistro, int IdTipoPropietario)
			throws BusinessException, TechnicalException, PersistenceException, EncodingException {
		if (idUsuario <= 0)
			throw new IllegalArgumentException("idUsuario es requerido.");
		if (idRegistro <= 0)
			throw new IllegalArgumentException("idUsuario es requerido.");
		bitacoraFacade.save(evento.getIdEvento(), idUsuario, evento.getEvento(), Calendar.getInstance(), null, idRegistro, IdTipoPropietario);
		return "exito";
	}

	// GUARDA CAMBIO CONTRASE�A OAM
	public String cambioContrasena(long idCandidato, String contrasena, EVENTO evento, int IdTipoPropietario)
			throws BusinessException, TechnicalException, PersistenceException, EncodingException {
		if (idCandidato <= 0)
			throw new IllegalArgumentException("idCandidato es requerido.");
		if (contrasena == null || contrasena.isEmpty())
			throw new IllegalArgumentException("Contrase�a es requerida.");
		CandidatoVo candidato = candidatoFacade.find(idCandidato);
		usuarioFacade.updatePassword(candidato.getIdUsuario(), Password.codificaPassword(contrasena));
		bitacoraFacade.save(evento.getIdEvento(), candidato.getIdUsuario(), evento.getEvento(), Calendar.getInstance(),
				null, idCandidato, IdTipoPropietario);
		return "exito";
	}

	// GUARDA CAMBIO CONTRASE�A OAM
	public String cambioContrasenaE(long idEmpresa, String contrasena, EVENTO evento, int IdTipoPropietario)
			throws BusinessException, TechnicalException, PersistenceException, EncodingException {
		if (idEmpresa <= 0)
			throw new IllegalArgumentException("idEmpresa es requerido.");
		if (contrasena == null || contrasena.isEmpty())
			throw new IllegalArgumentException("Contrase�a es requerida.");
		EmpresaVO empresa = empresaFacade.findById(idEmpresa);
		usuarioFacade.updatePassword(empresa.getIdUsuario(), Password.codificaPassword(contrasena));
		bitacoraFacade.save(evento.getIdEvento(), empresa.getIdUsuario(), evento.getEvento(), Calendar.getInstance(),
				null, idEmpresa, IdTipoPropietario);
		return "exito";
	}

	public String confirmaContrasenaCandidato(long idCandidato, String correo, String contrasena)
			throws BusinessException, TechnicalException, PersistenceException, EncodingException {

		if (idCandidato <= 0)
			throw new IllegalArgumentException("idCandidato es requerido.");
		if (correo == null || correo.isEmpty())
			throw new IllegalArgumentException("Correo es requerido.");
		if (contrasena == null || contrasena.isEmpty())
			throw new IllegalArgumentException("Contrase�a es requerida.");

		CandidatoVo candidato = candidatoFacade.find(idCandidato);

		/*
		 * 12/12/2011 Jmhg:-> Se omite la validacion Cuando se solicita un
		 * cambio de contrase�a se actualiza el estatus del usuario (INACTIVO),
		 * y en la confirmacion por correo se verifica que se encuentre
		 * INACTIVO, sin embargo, la unica manera de llegar a este metodo es
		 * atraves del correo, por lo tanto la validacion del estatus esta de
		 * mas y se omite para prevenir que no se pueda activar la cuenta
		 */
		/*
		if(Constantes.ESTATUS.INACTIVO.getIdOpcion()!= candidato.getEstatus()){
			logger.error("Confirmacion de Candidato con estatus inconsistente, idCandidato ("+ idCandidato +"), Estatu ("+ candidato.getEstatus() +") ");
			//throw new BusinessException("El Usuario " + candidato.getCorreoElectronico() + " no tiene estatus de INACTIVO");
		}
		*/
		
		usuarioFacade.updatePassword(candidato.getIdUsuario(), Password.codificaPassword(contrasena));

		usuarioFacade.activarUsuario(candidato.getIdUsuario());

		candidatoFacade.activarCandidato(idCandidato);
		
		bitacoraFacade.save(EVENTO.CONFIRMA_CONTRASENA.getIdEvento(), candidato.getIdUsuario(), EVENTO.CONFIRMA_CONTRASENA.getEvento(), 
		                    Calendar.getInstance(), null, idCandidato, TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());

		return correo;
	}

	public String confirmaContrasenaEmpresa(long idEmpresa, String correo, String contrasena)
			throws BusinessException, TechnicalException, PersistenceException, EncodingException {

		if (idEmpresa <= 0)
			throw new IllegalArgumentException("idEmpresa es requerido.");
		if (correo == null || correo.isEmpty())
			throw new IllegalArgumentException("Correo es requerido.");
		if (contrasena == null || contrasena.isEmpty())
			throw new IllegalArgumentException("Contrase�a es requerida.");

		try {
			EmpresaVO empresa = empresaFacade.findById(idEmpresa);

			if (registroPorValidarFacade.existeRegistroPorValidar(TIPO_REGISTRO.EMPRESA, idEmpresa,
					ESTATUS.PENDIENTE_PUBLICAR, ESTATUS.ASIGNADO_PUBLICADOR, ESTATUS.EN_EDICION_PUBLICADOR)) {
				throw new BusinessException(MessageLoader.getInstance().getMessage("autorizacion.registro.pendiente"));
			}

			/*
			 * 12/12/2011 Jmhg:-> Se omite la validacion Cuando se solicita un
			 * cambio de contrase�a se actualiza el estatus del usuario
			 * (INACTIVO), y en la confirmacion por correo se verifica que se
			 * encuentre INACTIVO, sin embargo, la unica manera de llegar a este
			 * metodo es atraves del correo, por lo tanto la validacion del
			 * estatus esta de mas y se omite para prevenir que no se pueda
			 * activar la cuenta
			 **/
			if (Constantes.ESTATUS.INACTIVO.getIdOpcion() != empresa.getEstatus()) {
				logger.error("Confirmacion de Empresa con estatus inconsistente, idEmpresa (" + empresa.getIdEmpresa()
						+ "), Estatu (" + empresa.getEstatus() + ") ");
				// throw new BusinessException("El Usuario " +
				// empresa.getCorreoElectronico() + " no tiene estatus de
				// INACTIVO");
			}

			try {
				EmpresaDAO empDAO = new EmpresaDAO();
				empDAO.actualizaEstatusInactivaOfertasEmpresa(ESTATUS.ACTIVO.getIdOpcion(), idEmpresa);
			} catch (Exception e1) {
				e1.printStackTrace();
				logger.error("Error de tipo :-> " + e1.getMessage());
			}

			usuarioFacade.updatePassword(empresa.getIdUsuario(), Password.codificaPassword(contrasena));

			usuarioFacade.activarUsuario(empresa.getIdUsuario());

			empresaFacade.actualizaEmpresaEstatus(idEmpresa, ESTATUS.ACTIVO.getIdOpcion());

			bitacoraFacade.save(EVENTO.CONFIRMA_CONTRASENA.getIdEvento(), empresa.getIdUsuario(),
					EVENTO.CONFIRMA_CONTRASENA.getEvento(), Calendar.getInstance(), null, empresa.getIdEmpresa(),
					TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			throw new TechnicalException(e);
		}

		return correo;
	}

	public EmpresaVO consultaEmpresa(long idEmpresa) throws PersistenceException {
		EmpresaVO empresa = empresaFacade.findById(idEmpresa);
		return empresa;
	}

	public ConfirmacionVO confirmacionDirectaEmpresa(String username, String razonSocial)
			throws BusinessException, TechnicalException {

		if (username == null || username.isEmpty())
			throw new IllegalArgumentException("Nombre de usuario requerido");
		if (razonSocial == null)
			throw new IllegalArgumentException("Nombre de la empresa requerido");

		UsuarioVO usuario = usuarioFacade.findByUsuario(username);

		if (usuario == null)
			throw new BusinessException("Usuario no localizado mediante el nombre " + username);

		EmpresaVO empresa = empresaFacade.findByIdUsuario(usuario.getIdUsuario());

		if (empresa == null)
			throw new BusinessException("Empresa no localizada mediante el nombre de usuario " + username);

		if (null != razonSocial && !empresa.getRazonSocial().equalsIgnoreCase(razonSocial))
			throw new BusinessException(
					"El nombre de empresa no corresponde a la empresa con nombre de usuario " + username);

		String passw = Password.getPassword();
		String passwdb = null;

		try {
			passwdb = Password.codificaPassword(passw);
		} catch (EncodingException e) {
			logger.error(e);
			throw new TechnicalException(e);
		}

		usuarioFacade.updatePasswordEstatus(empresa.getIdUsuario(), passwdb, ESTATUS.ACTIVO.getIdOpcion());

		empresaFacade.actualizaEstatus(empresa.getIdEmpresa(), ESTATUS.ACTIVO.getIdOpcion());

		if (null == empresa.getFechaConfirma()) {
			empresaFacade.actualizaFechaConfirma(empresa.getIdEmpresa(), new Date());
		}

		System.out.println("passw:" + passw);
		ConfirmacionVO confirmacion = ConfirmacionVO.getInstance(username, empresa.getNombreEmpresa(),
				empresa.getCorreoElectronico(), passw, null);
		confirmacion.setIdEmpresa(empresa.getIdEmpresa());
		return confirmacion;

	}

	public ConfirmacionVO confirmacionDirectaEmpresa(String username, Date fecha)
			throws BusinessException, TechnicalException {
		if (username == null || username.isEmpty())
			throw new IllegalArgumentException("Nombre de usuario requerido");
		if (fecha == null)
			throw new IllegalArgumentException("Fecha de la empresa requerida");

		UsuarioVO usuario = usuarioFacade.findByUsuario(username);

		if (usuario == null)
			throw new BusinessException("Usuario no localizado mediante el nombre " + username);

		EmpresaVO empresa = empresaFacade.findByIdUsuario(usuario.getIdUsuario());

		if (empresa == null)
			throw new BusinessException("Empresa no localizada mediante el nombre de usuario " + username);

		/*
		 * if (registroPorValidarFacade.existeRegistroPorValidar(TIPO_REGISTRO.
		 * EMPRESA, empresa.getIdEmpresa(), ESTATUS.PENDIENTE_PUBLICAR,
		 * ESTATUS.ASIGNADO_PUBLICADOR, ESTATUS.EN_EDICION_PUBLICADOR)){ throw
		 * new BusinessException(MessageLoader.getInstance().getMessage(
		 * "autorizacion.registro.pendiente")); }
		 */

		Date fechaEmpresa = null;

		if (TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() == empresa.getIdTipoPersona()) {
			fechaEmpresa = empresa.getFechaNacimiento();

		} else if (TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() == empresa.getIdTipoPersona()) {
			fechaEmpresa = empresa.getFechaActa();

		} else {
			throw new BusinessException(
					"Empresa con datos inconsistentes, no cuenta con un indicador valido para el tipo de empresa.");
		}

		if (fechaEmpresa == null)
			throw new BusinessException(
					"La Empresa no cuenta con la fecha correspondiente para verificar su identidad.");

		Calendar fechaParam = Calendar.getInstance();
		Calendar fechaEmpre = Calendar.getInstance();

		fechaParam.setTime(fecha);
		fechaEmpre.setTime(fechaEmpresa);

		int totalFechaParam = fechaParam.get(Calendar.DATE) + fechaParam.get(Calendar.MONTH)
				+ fechaParam.get(Calendar.YEAR);
		int totalFechaEmpre = fechaEmpre.get(Calendar.DATE) + fechaEmpre.get(Calendar.MONTH)
				+ fechaEmpre.get(Calendar.YEAR);

		if (totalFechaParam != totalFechaEmpre)
			throw new BusinessException("La fecha de la Empresa no corresponde con la registrada en la cuenta.");

		String passw = Password.getPassword();
		String passwdb = null;

		try {
			passwdb = Password.codificaPassword(passw);
		} catch (EncodingException e) {
			logger.error(e);
			throw new TechnicalException(e);
		}

		// usuarioFacade.updatePassword(empresa.getIdUsuario(), passwdb);
		usuarioFacade.updatePasswordEstatus(empresa.getIdUsuario(), passwdb, ESTATUS.ACTIVO.getIdOpcion());

		empresaFacade.actualizaEstatus(empresa.getIdEmpresa(), ESTATUS.ACTIVO.getIdOpcion());

		if (null == empresa.getFechaConfirma()) {
			empresaFacade.actualizaFechaConfirma(empresa.getIdEmpresa(), new Date());
		}
		ConfirmacionVO confirmacion = ConfirmacionVO.getInstance(username, empresa.getNombreEmpresa(),
				empresa.getCorreoElectronico(), passw, null);
		confirmacion.setIdEmpresa(empresa.getIdEmpresa());
		return confirmacion;
	}

	public ConfirmacionVO confirmacionDirectaEmpresaActivo(String username, Date fecha)
			throws BusinessException, TechnicalException {
		if (username == null || username.isEmpty())
			throw new IllegalArgumentException("Nombre de usuario requerido");
		if (fecha == null)
			throw new IllegalArgumentException("Fecha de la empresa requerida");

		UsuarioVO usuario = usuarioFacade.findByUsuario(username);

		if (usuario == null)
			throw new BusinessException("Usuario no localizado mediante el nombre " + username);

		EmpresaVO empresa = empresaFacade.findByIdUsuario(usuario.getIdUsuario());

		if (empresa == null)
			throw new BusinessException("Empresa no localizada mediante el nombre de usuario " + username);
		else if (empresa.getEstatus() != ESTATUS.ACTIVO.getIdOpcion()) {
			throw new BusinessException("Empresa no activo mediante el nombre de usuario " + username
					+ " acude a la oficina del SNE m�s cercano o comunicarse al tel�fono sin costo.");
		}

		/*
		 * if (registroPorValidarFacade.existeRegistroPorValidar(TIPO_REGISTRO.
		 * EMPRESA, empresa.getIdEmpresa(), ESTATUS.PENDIENTE_PUBLICAR,
		 * ESTATUS.ASIGNADO_PUBLICADOR, ESTATUS.EN_EDICION_PUBLICADOR)){ throw
		 * new BusinessException(MessageLoader.getInstance().getMessage(
		 * "autorizacion.registro.pendiente")); }
		 */

    	/*if (registroPorValidarFacade.existeRegistroPorValidar(TIPO_REGISTRO.EMPRESA, empresa.getIdEmpresa(),
                                                              ESTATUS.PENDIENTE_PUBLICAR, ESTATUS.ASIGNADO_PUBLICADOR, ESTATUS.EN_EDICION_PUBLICADOR)){
    		throw new BusinessException(MessageLoader.getInstance().getMessage("autorizacion.registro.pendiente"));
    	}*/
		
		Date fechaEmpresa = null;

		if (TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() == empresa.getIdTipoPersona()) {
			fechaEmpresa = empresa.getFechaNacimiento();
			// empresa activa y no en procesa de activaci�n
		} else if (empresa.getIdTipoEmpresa() == TIPO_EMPRESA.PRIVADA.getTipoEmpresa()) {
			fechaEmpresa = empresa.getFechaActa();
		} else {
			throw new BusinessException(
					"Empresa con datos inconsistentes, no cuenta con tipo de empresa para recuperar su clave.");
		}

		if (fechaEmpresa == null)
			throw new BusinessException(
					"La Empresa no cuenta con la fecha correspondiente para verificar su identidad.");

		Calendar fechaParam = Calendar.getInstance();
		Calendar fechaEmpre = Calendar.getInstance();

		fechaParam.setTime(fecha);
		fechaEmpre.setTime(fechaEmpresa);

		int totalFechaParam = fechaParam.get(Calendar.DATE) + fechaParam.get(Calendar.MONTH)
				+ fechaParam.get(Calendar.YEAR);
		int totalFechaEmpre = fechaEmpre.get(Calendar.DATE) + fechaEmpre.get(Calendar.MONTH)
				+ fechaEmpre.get(Calendar.YEAR);

		if (totalFechaParam != totalFechaEmpre)
			throw new BusinessException("La fecha de la Empresa no corresponde con la registrada en la cuenta.");

		String passw = Password.getPassword();
		String passwdb = null;

		try {
			passwdb = Password.codificaPassword(passw);
		} catch (EncodingException e) {
			logger.error(e);
			throw new TechnicalException(e);
		}

		// usuarioFacade.updatePassword(empresa.getIdUsuario(), passwdb);
		usuarioFacade.updatePasswordEstatus(empresa.getIdUsuario(), passwdb, ESTATUS.ACTIVO.getIdOpcion());

		empresaFacade.actualizaEstatus(empresa.getIdEmpresa(), ESTATUS.ACTIVO.getIdOpcion());

		if (null == empresa.getFechaConfirma()) {
			empresaFacade.actualizaFechaConfirma(empresa.getIdEmpresa(), new Date());
		}

		ConfirmacionVO confirmacion = ConfirmacionVO.getInstance(username, empresa.getNombreEmpresa(),
				empresa.getCorreoElectronico(), passw, null);
		confirmacion.setIdEmpresa(empresa.getIdEmpresa());
		return confirmacion;
	}

	public CandidatoVo consultaCandidato(long idCandidato) throws PersistenceException {
		CandidatoVo candidato = candidatoFacade.find(idCandidato);
		return candidato;
	}

	public ConfirmacionVO confirmacionReactivacionCandidato(String username, String CURP)
			throws BusinessException, TechnicalException {
		if (username == null || username.isEmpty())
			throw new IllegalArgumentException("Nombre de usuario requerido");
		if (CURP == null || CURP.isEmpty())
			throw new IllegalArgumentException("CURP requerido");

		UsuarioVO usuario = usuarioFacade.findByUsuario(username);

		if (usuario == null)
			throw new BusinessException("Usuario no localizado mediante el nombre " + username);

		CandidatoVo candidato = candidatoFacade.consultaCandidato(usuario.getIdUsuario());

		if (candidato == null)
			throw new BusinessException("Candidato no localizado mediante el nombre " + username);

		String candidatoCURP = candidato.getCurp();

		if (candidatoCURP == null)
			throw new BusinessException("El Candidato no cuenta con CURP");

		if (!candidatoCURP.equalsIgnoreCase(CURP))
			throw new BusinessException("La CURP no corresponde al Candidato el con nombre de usuario " + username);

		String nombre = candidato.getNombre()
				+ (candidato.getApellido1() != null ? (" " + candidato.getApellido1()) : "")
				+ (candidato.getApellido2() != null ? (" " + candidato.getApellido2()) : "");

		String password = Password.getPassword();
		String passwdb = null;

		try {
			passwdb = Password.codificaPassword(password);
		} catch (EncodingException e) {
			logger.error(e);
			throw new TechnicalException(e);
		}

		// usuarioFacade.updatePassword(candidato.getIdUsuario(), passwdb);
		usuarioFacade.updatePasswordEstatus(candidato.getIdUsuario(), passwdb, ESTATUS.ACTIVO.getIdOpcion());
		candidatoFacade.actualizaEstatusCandidato(candidato.getIdCandidato(), ESTATUS.ACTIVO.getIdOpcion());
		candidatoFacade.eliminarDetalleDesactivacion(candidato.getIdCandidato());
		candidatoAppService.reactivarCandidato(candidato.getIdCandidato(), candidato.getIdUsuario());

		ConfirmacionVO confirmacion = ConfirmacionVO.getInstance(username, nombre, candidato.getCorreoElectronico(),
				password, null);
		confirmacion.setIdCandidato(candidato.getIdCandidato());
		return confirmacion;
	}

	public ConfirmacionVO confirmacionDirectaCandidato(String username, String CURP)
			throws BusinessException, TechnicalException {

		if (username == null || username.isEmpty())
			throw new IllegalArgumentException("Nombre de usuario requerido");
		if (CURP == null || CURP.isEmpty())
			throw new IllegalArgumentException("CURP requerido");

		UsuarioVO usuario = usuarioFacade.findByUsuario(username);

		if (usuario == null)
			throw new BusinessException("Usuario no localizado mediante el nombre " + username);

		CandidatoVo candidato = candidatoFacade.consultaCandidato(usuario.getIdUsuario());

		if (candidato == null)
			throw new BusinessException("Candidato no localizado mediante el nombre " + username);

		String candidatoCURP = candidato.getCurp();

		if (candidatoCURP == null)
			throw new BusinessException("El Candidato no cuenta con CURP");

		if (!candidatoCURP.equalsIgnoreCase(CURP))
			throw new BusinessException("La CURP no corresponde al Candidato el con nombre de usuario " + username);

		String nombre = candidato.getNombre()
				+ (candidato.getApellido1() != null ? (" " + candidato.getApellido1()) : "")
				+ (candidato.getApellido2() != null ? (" " + candidato.getApellido2()) : "");

		String password = Password.getPassword();
		String passwdb = null;

		try {
			passwdb = Password.codificaPassword(password);
		} catch (EncodingException e) {
			logger.error(e);
			throw new TechnicalException(e);
		}

		// usuarioFacade.updatePassword(candidato.getIdUsuario(), passwdb);
		usuarioFacade.updatePasswordEstatus(candidato.getIdUsuario(), passwdb, ESTATUS.ACTIVO.getIdOpcion());

		candidatoFacade.actualizaEstatusCandidato(candidato.getIdCandidato(), ESTATUS.ACTIVO.getIdOpcion());

		System.out.println("------candidato.getFechaConfirma():" + candidato.getFechaConfirma());
		if (null == candidato.getFechaConfirma()) {
			candidatoFacade.actualizaFechaConfirma(candidato.getIdCandidato(), new Date());
		}

		ConfirmacionVO confirmacion = ConfirmacionVO.getInstance(username, nombre, candidato.getCorreoElectronico(),
				password, null);
		confirmacion.setIdCandidato(candidato.getIdCandidato());
		return confirmacion;
	}

	public void actualizaSesionActiva(long idUsuario, int sesionActiva) throws PersistenceException {

		if (idUsuario <= 0)
			throw new IllegalArgumentException("El identificador de usuario es requerido");
		if (sesionActiva < 0)
			throw new IllegalArgumentException("El indicador de seccion activa es requerido");

		// logger.info("Actualizando la session del usuario ("+ idUsuario +") a
		// sesionActiva : ("+ sesionActiva +")");

		try {
			usuarioFacade.updateSesionActiva(idUsuario, sesionActiva);
		} catch (PersistenceException e) {
			logger.error(e.toString());
			throw e;
		}
	}

	public List<AccionVO> consultaAccionesRequierenAutenticacion() throws SQLException {
		return AccionPerfilDAO.getInstance().consultaAccionesRequierenAutenticacion();
	}

	public List<AccionVO> consultaAccionesPorPerfil(long idPerfil) throws SQLException {
		return AccionPerfilDAO.getInstance().consultaAccionesPorPerfil(idPerfil);
	}

	public List<AccionVO> consultaAccionesAsignadasPorPerfil(long idPerfil) throws SQLException {
		return AccionPerfilDAO.getInstance().consultaAccionesSignadasPorPerfil(idPerfil);
	}

	public void asignaAcciones(long idPerfil, long[] idsAcciones) throws SQLException {
		if (idPerfil <= 0)
			throw new IllegalArgumentException("Identificador del perfil requerido");

		Context context = null;
		Connection conn = null;
		AccionPerfilDAO dao = null;

		try {
			ConnectionWraper wraper = ConexionFactory.getInstance().getConnection();
			conn = wraper.getConnection();
			context = wraper.getContext();

			dao = AccionPerfilDAO.getInstance(conn);
		} catch (Exception e1) {
			logger.error(e1);
			dao = AccionPerfilDAO.getInstance();
		}

		dao.eliminaPermisos(idPerfil); // Limpia configuracion

		if (idsAcciones != null && idsAcciones.length > 0)
			dao.asignaPermisos(idPerfil, idsAcciones); // Establece nueva
														// configuracion

		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				logger.error(e);
			}
		}

		if (context != null) {
			logger.info("Cerrando context");
			try {
				context.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void estableceAccionesReqUsuarioAutenticado(long[] idsAcciones) throws SQLException {

		Context context = null;
		Connection conn = null;
		AccionPerfilDAO dao = null;

		try {
			ConnectionWraper wraper = ConexionFactory.getInstance().getConnection();
			conn = wraper.getConnection();
			context = wraper.getContext();

			dao = AccionPerfilDAO.getInstance(conn);
		} catch (Exception e1) {
			logger.error(e1);
			dao = AccionPerfilDAO.getInstance();
		}

		dao.actualizaAccionesLimpiaReqAutenticado(); // Limpia configuracion

		if (idsAcciones != null && idsAcciones.length > 0)
			dao.actualizaAccionesReqUsuarioAutenticado(idsAcciones); // Establece
																		// nueva
																		// configuracion

		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				logger.error(e);
			}
		}

		if (context != null) {
			logger.info("Cerrando context");
			try {
				context.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public List<PerfilVO> consultaPerfiles() throws SQLException {
		List<PerfilVO> perfiles = null;

		try {
			perfiles = perfilFacade.findAll();

		} catch (PersistenceException e) {
			throw new SQLException(e);
		}

		return perfiles;
	}

	public void inactivaSesionesActivas() {
		try {
			logger.info("Se inactivan las sessiones de los usuarios firmados antes del reinicio.");
			usuarioFacade.inactivaSesionesActivas();
		} catch (Exception e) {
			logger.error(e);
		}
	}

	@Override
	public String generaCodigo(long idCIL) {
		int index = 0;
		String password = null;
		boolean success = false;
		java.util.Date fechaAlta = new Date();
		CilCodigoAccesoVO vo = new CilCodigoAccesoVO();
		IntegracionLaboralDAO dao = new IntegracionLaboralDAO();
		try {
			while (index < 40 || !success) {
				password = Password.getPassword(10);
				if (!dao.exist(password, idCIL))
					success = true;
				index++;
			}
			vo.setContrasena(password);
			vo.setEstatus(Constantes.ESTATUS.ACTIVO.getIdOpcion());
			vo.setFechaAlta(fechaAlta);
			vo.setIdCil(idCIL);
			long idCilCodigoAcceso = dao.getIdCilCodeAccess(idCIL);
			if (idCilCodigoAcceso > 0)
				cilCodigoAccesoFacade.actualizaEstatus(idCilCodigoAcceso, Constantes.ESTATUS.INACTIVO.getIdOpcion());
			cilCodigoAccesoFacade.save(vo);
			logger.info("C�digo de acceso para el CIL: " + password);
		} catch (Exception e) {
			password = null;
			logger.error("Error al generar un nuevo c�digo de acceso para el CIL: " + idCIL, e);
		}
		return password;
	}

	@Override
	public long isValidCode(String password) {
		long idCIL = -1;
		IntegracionLaboralDAO dao = new IntegracionLaboralDAO();
		try {
			idCIL = dao.isValidPassword(password);
		} catch (Exception e) {
			logger.error("Error al validar c�digo de acceso del CIL: " + password, e);
		}
		return idCIL;
	}

	public void registraUltimoAccesoCandidato(long idCandidato) throws PersistenceException {
		try {
			if (idCandidato > 0) {
				candidatoFacade.actualizaFechaUltimoAcceso(idCandidato);
			}
		} catch (PersistenceException e) {
			logger.error(e.toString());
			throw e;
		}
	}

	@Override
	public long getIdCandidato(long idUsuario) throws PersistenceException {
		CandidatoVo vo = candidatoFacade.consultaCandidato(idUsuario);
		return null != vo ? vo.getIdCandidato() : -1;
	}

	// Start Cambio movil
	@Override
	public MovilSessionVO existeMovilSession(MovilSessionVO vo) {
		return this.movilSessionFacade.findByPk(vo);

	}

	@Override
	public MovilSessionVO guardarMovilSession(MovilSessionVO vo) {
		return this.movilSessionFacade.save(vo);

	}

	@Override
	public MovilSessionVO actualizarMovilSessionVO(MovilSessionVO vo) {

		return this.movilSessionFacade.update(vo);
	}

	@Override
	public void deleteMovilSessionVO(MovilSessionVO vo) {
		this.movilSessionFacade.deleteByPk(vo);

	}

	@Override
	public EmpresaVO consultaEmpresaPorIdUsuario(long idUsuario) throws PersistenceException {
		EmpresaVO empresa = empresaFacade.findByIdUsuario(idUsuario);
		return empresa;
	}

	@Override
	public byte[] consultaImagen(int idUsuario, int idPerfil, int idPropietario){
		byte[] img = null;
		if(hashImagenes.containsKey(idUsuario)){
			img = hashImagenes.get(idUsuario);
		}
		else{
			img = agregaImagen(idUsuario, idPerfil, idPropietario);
		}
		return img;
	}

	@Override
	public byte[] agregaImagen(int idUsuario, int idPerfil, int idPropietario){
		byte[] img = null;
		if(idPerfil != 0 && idPropietario != 0 ){
			if (idPerfil == PERFIL.CANDIDATO.getIdOpcion()){
				img = perfilLaboralFacade.consultaFotografia(idPropietario);
	
			} else if (idPerfil == PERFIL.EMPRESA.getIdOpcion()){
				img = empresaFacade.consultaLogotipo(idPropietario);
	
			}
		}
		if(img != null){
			if(hashImagenes.containsKey(idUsuario))
				hashImagenes.put(idUsuario, img);	
		}
		
		return img;
	}

	@Override
	public void eliminaImagen(int idUsuario){
		//Validamos que contenga la llave:
		if(hashImagenes.containsKey(idUsuario)){
			hashImagenes.remove(idUsuario);
		}
		return;
		
	}

	// Fin cambio movil
}