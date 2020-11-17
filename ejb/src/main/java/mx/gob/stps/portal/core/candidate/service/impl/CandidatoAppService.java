/**
 * 
 */
package mx.gob.stps.portal.core.candidate.service.impl;

import org.apache.log4j.Logger; 

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_CANDIDATO_HABILIDAD;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_OFERTAS_CONTRATADO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_OFERTAS_DESVINCULADA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_OFERTAS_ENPROCESO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_OFERTAS_POSTULADO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_OFERTAS_RECHAZADO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_OFERTAS_SELECCIONADO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_OFERTAS_VINCULADA;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.naming.Context;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.autorizacion.service.AutorizacionAppServiceLocal;
import mx.gob.stps.portal.core.autorizacion.vo.ParametroVO;
import mx.gob.stps.portal.core.candidate.dao.CandidatoDAO;
import mx.gob.stps.portal.core.candidate.dao.ConocimientoHabilidadDAO;
import mx.gob.stps.portal.core.candidate.service.CandidatoAppServiceLocal;
import mx.gob.stps.portal.core.candidate.service.CandidatoAppServiceRemote;
import mx.gob.stps.portal.core.candidate.vo.BusquedaCandidatosVO;
import mx.gob.stps.portal.core.candidate.vo.CandidatoOtroEstudioVO;
import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.candidate.vo.ComputacionAvanzadaVO;
import mx.gob.stps.portal.core.candidate.vo.ConocerConfigVO;
import mx.gob.stps.portal.core.candidate.vo.ConocimientoComputacionVO;
import mx.gob.stps.portal.core.candidate.vo.ConocimientoHabilidadVO;
import mx.gob.stps.portal.core.candidate.vo.CurriculumVo;
import mx.gob.stps.portal.core.candidate.vo.EscolaridadVO;
import mx.gob.stps.portal.core.candidate.vo.ExpectativaLaboralVO;
import mx.gob.stps.portal.core.candidate.vo.ExpectativaLugarVO;
import mx.gob.stps.portal.core.candidate.vo.ExpectativaVO;
import mx.gob.stps.portal.core.candidate.vo.ExperienciaVO;
import mx.gob.stps.portal.core.candidate.vo.GradoAcademicoVO;
import mx.gob.stps.portal.core.candidate.vo.HistoriaLaboralVO;
import mx.gob.stps.portal.core.candidate.vo.IdiomaVO;
import mx.gob.stps.portal.core.candidate.vo.InformacionGeneralVO;
import mx.gob.stps.portal.core.candidate.vo.OfertaCandidatoResumenVo;
import mx.gob.stps.portal.core.candidate.vo.OtroMedioVO;
import mx.gob.stps.portal.core.candidate.vo.PerfilLaboralVo;
import mx.gob.stps.portal.core.candidate.vo.PerfilVO;
import mx.gob.stps.portal.core.candidate.vo.ResultadoBusquedaCandidatosVO;
import mx.gob.stps.portal.core.domicilio.vo.CodigoPostalVO;
import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.domicilio.vo.MunicipioVO;
import mx.gob.stps.portal.core.entrevista.dao.EntrevistaDao;
import mx.gob.stps.portal.core.infra.data.ConexionFactory;
import mx.gob.stps.portal.core.infra.data.ConnectionWraper;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.EncodingException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.mail.service.NotificacionService;
import mx.gob.stps.portal.core.infra.mail.vo.DestinatarioVO;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.CAMBIO_CORREO;
import mx.gob.stps.portal.core.infra.utils.Constantes.CONOC_HAB;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.EVENTO;
import mx.gob.stps.portal.core.infra.utils.Constantes.EXPERIENCIA;
import mx.gob.stps.portal.core.infra.utils.Constantes.MULTIREGISTRO;
import mx.gob.stps.portal.core.infra.utils.Constantes.PERFIL;
import mx.gob.stps.portal.core.infra.utils.Constantes.PRINCIPAL;
import mx.gob.stps.portal.core.infra.utils.Constantes.RESPUESTA_IMMS_CONSULTA_NSS;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PERSONA;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_USUARIO;
import mx.gob.stps.portal.core.infra.utils.Password;
import mx.gob.stps.portal.core.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.infra.vo.CandidatoVerDetalleVO;
import mx.gob.stps.portal.core.infra.vo.EntrevistaVO;
import mx.gob.stps.portal.core.oferta.busqueda.dao.OfertasCandidatoResumenDAO;
import mx.gob.stps.portal.core.oferta.detalle.bo.PerfilBO;
import mx.gob.stps.portal.core.oferta.detalle.dao.CandidatoDetalleDAO;
import mx.gob.stps.portal.core.oferta.detalle.dao.OfertaDetalleDAO;
import mx.gob.stps.portal.core.oferta.detalle.helper.BeanUtils;
//import mx.gob.stps.portal.core.oferta.detalle.service.OfertaCompatibilidadAppServiceLocal;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoADesactivarVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.core.persistencia.facade.BitacoraFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.CalculadoraCandidatoFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.CandidatoConocimHabilidadFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.CandidatoFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.CandidatoIdiomaFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.CandidatoOtroEstudioFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.CatalogoOpcionFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.ConocerFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.ConocimientoComputacionFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.DomicilioFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.EntrevistaFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.ModalidadCandidatoFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.OfertaCandidatoFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.OfertaEmpleoFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.ParametroFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.PerfilLaboralFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.SolicitanteFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.TelefonoFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.UsuarioFacadeLocal;
import mx.gob.stps.portal.core.search.exception.IndexerException;
import mx.gob.stps.portal.core.search.service.IndexerServiceLocator;
//import mx.gob.stps.portal.core.search.service.PortalEmpleoBuscadorServiceLocal;
import mx.gob.stps.portal.core.seguridad.vo.UsuarioVO;
import mx.gob.stps.portal.mail.template.FieldVO;
import mx.gob.stps.portal.persistencia.entity.CalculadoraCandidato;
import mx.gob.stps.portal.persistencia.entity.Solicitante;
import mx.gob.stps.portal.persistencia.vo.BeneficiarioVO;
import mx.gob.stps.portal.persistencia.vo.CanalizacionCandidatoVO;
import mx.gob.stps.portal.persistencia.vo.CatSubareaVO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.persistencia.vo.EstandarConocerVO;
import mx.gob.stps.portal.persistencia.vo.FormatoLPAVO;
import mx.gob.stps.portal.persistencia.vo.FormatoPTATCultivosVO;
import mx.gob.stps.portal.persistencia.vo.FormatoPTATHclinicaVO;
import mx.gob.stps.portal.persistencia.vo.FormatoPTATMaquinariaVO;
import mx.gob.stps.portal.persistencia.vo.FormatoPTATVO;
import mx.gob.stps.portal.persistencia.vo.FormatoSNE01VO;
import mx.gob.stps.portal.persistencia.vo.ModalidadCandidatoVO;
import mx.gob.stps.portal.persistencia.vo.ModalidadPtatBeneficiarioVO;
import mx.gob.stps.portal.persistencia.vo.ReferenciaLaboralVO;
import mx.gob.stps.portal.persistencia.vo.SolicitanteVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.utils.Catalogos.ACEPTACION_TERMINOS;
import mx.gob.stps.portal.utils.Catalogos.FUISTE_CONTRATADO;
import mx.gob.stps.portal.utils.Catalogos.MOTIVOS_FUERA_PPC;
import mx.gob.stps.portal.utils.Catalogos.PARAMETRO;
import mx.gob.stps.portal.utils.Catalogos.PLANTILLA_CORREO;
import mx.gob.stps.portal.utils.Catalogos.TRABAJA_ACTUALMENTE;
import mx.gob.stps.portal.ws.conocer.ConocerWSService;
import mx.gob.stps.portal.ws.conocer.exception.ConocerWSException;

/**
 * @author Felipe Juárez Ramírez
 * @since 1 de Marzo de 2011 Session Bean implementation class
 *        CandidatoAppService
 */
@Stateless(name = "CandidatoAppService", mappedName = "CandidatoAppService")
public class CandidatoAppService implements CandidatoAppServiceRemote, CandidatoAppServiceLocal {

	private static Logger logger = Logger.getLogger(CandidatoAppService.class);

	@EJB
	private CandidatoFacadeLocal candidatoFacade;

	@EJB
	private OfertaCandidatoFacadeLocal ofertaFacade;

	@EJB
	private EntrevistaFacadeLocal entrevistaFacade;

	@EJB
	private PerfilLaboralFacadeLocal perfilLaboralFacade;

	@EJB
	private AutorizacionAppServiceLocal autorizacionAppService;

	@EJB
	private UsuarioFacadeLocal usuarioFacade;

	@EJB
	private CalculadoraCandidatoFacadeLocal calculadoraCandidatoFacade;

	@EJB
	private BitacoraFacadeLocal bitacoraFacade;

	@EJB
	private DomicilioFacadeLocal domicilioFacade;

	@EJB
	private TelefonoFacadeLocal telefonoFacade;

	@EJB
	private ConocerFacadeLocal conocerFacade;

	@EJB
	private ModalidadCandidatoFacadeLocal modalidadCandidatoFacadeLocal;

	@EJB
	private CandidatoOtroEstudioFacadeLocal candidatoOtroEstudioFacade;

	@EJB
	private ConocimientoComputacionFacadeLocal conocimientoComputacionFacade;

	@EJB
	private CatalogoOpcionFacadeLocal catalogoOpcionFacade;

	@EJB
	private SolicitanteFacadeLocal solicitanteFacade;

	@EJB
	private OfertaEmpleoFacadeLocal ofertaEmpleoFacadeLocal;

	@EJB
	private ParametroFacadeLocal parametroFacadeLocal;

	@EJB
	private CandidatoIdiomaFacadeLocal candidatoIdiomaFacadeLocal;

	@EJB
	private CandidatoConocimHabilidadFacadeLocal candidatoConocimHabilidadFacadeLocal;

	private final PropertiesLoader properties = PropertiesLoader.getInstance();

	public CandidatoAppService() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.stps.portal.core.candidate.service.impl.CandidatoAppServiceRemote
	 * #registrarPerfil(mx.gob.stps.portal.core.candidate.vo.PerfilVO)
	 */
	@Override
	public PerfilVO registrarPerfil(PerfilVO perfil) throws PersistenceException, TechnicalException, SQLException, BusinessException, MailException, IndexerException {
		logger.debug("Inicia Metodo: registrarPerfil.....");
		if (validarPerfil(perfil)){
			PerfilVO perfilVO = candidatoFacade.registrarPerfil(perfil);
			//guardar los telefonos adicionales
			List<TelefonoVO> telefonos = telefonoFacade.getTelefonosPropietario(perfilVO.getPrincipal().getIdPropietario(), perfilVO.getPrincipal().getIdTipoPropietario());
			if(telefonos != null){
				if(perfilVO.getSecundario() != null){
					if(telefonos.size() >=2){
						//actualizar
						perfilVO.getSecundario().setIdTelefono(telefonos.get(1).getIdTelefono());
						telefonoFacade.update(perfilVO.getSecundario());
					}
					else
					{
						telefonoFacade.save(perfilVO.getSecundario());
					}
				}
				if(perfilVO.getTercero() != null){
					if(telefonos.size() >=3){
						//actualizar
						perfilVO.getTercero().setIdTelefono(telefonos.get(2).getIdTelefono());
						telefonoFacade.update(perfilVO.getTercero());
					}
					else
					{
						telefonoFacade.save(perfilVO.getTercero());
					}
				}
			}

			//Notifica al candidato
			if (perfil.getCambioCorreo() == CAMBIO_CORREO.SI.getIdOpcion()) {

				Solicitante solicitante = solicitanteFacade.findByIdCandidato(perfilVO.getIdCandidato());
				solicitante.setCorreoElectronico(perfil.getCorreoElectronico());
				solicitanteFacade.actualizarCorreoElectronico(solicitante);
				//TODO DEPURAR PARA NO GUARDAR EN 3 LUGARES
				UsuarioVO usuarioVO = usuarioFacade.find(perfil.getIdUsuario());
				usuarioVO.setCorreoElectronico(perfil.getCorreoElectronico());
				usuarioFacade.actualizaRegistroUsuario(usuarioVO);
				CandidatoVo candidatoVo = new CandidatoVo();
				candidatoVo.setIdCandidato(perfil.getIdCandidato());
				candidatoVo.setIdUsuario(perfil.getIdUsuario());
				candidatoVo.setNombre(perfil.getNombre());
				candidatoVo.setApellido1(perfil.getApellido1());
				candidatoVo.setApellido2(perfil.getApellido2());
				candidatoVo.setEstatus(ESTATUS.ACTIVO.getIdOpcion());
				candidatoVo.setCorreoElectronico(perfil.getCorreoElectronico());
				
			}
			
			
			bitacoraFacade.save(EVENTO.REGISTRO_PERFIL_LABORAL_CANDIDATO.getIdEvento(), 
								perfilVO.getIdUsuario(), EVENTO.
					            REGISTRO_PERFIL_LABORAL_CANDIDATO.getEvento(), 
					            Calendar.getInstance(), perfilVO.toBitacora(), perfilVO.getIdCandidato(), TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
			
			return perfilVO;
		}else{
			throw new BusinessException("Existen errores en los valores del perfil");
		}
	}

	
	private boolean validarPerfil(PerfilVO perfilVo){
		try{
			if (perfilVo.getCodigoPostal() == null || perfilVo.getCodigoPostal().isEmpty()|| perfilVo.getCodigoPostal().length()>5){
					return false;
			}else{
				int codigo = Integer.parseInt(perfilVo.getCodigoPostal());
				if (codigo <=0){
					return false;
				}
			}
			
			if (perfilVo.getIdEntidad() <= 0 || perfilVo.getIdMunicipio() <= 0 || perfilVo.getIdColonia() <= 0){
				return false;
			}
			
			if (perfilVo.getCalle() == null ||  perfilVo.getCalle().isEmpty() || perfilVo.getCalle().length()>150){
				return false;
			}
			
			if ( perfilVo.getNumeroExterior() == null || perfilVo.getNumeroExterior().isEmpty() || perfilVo.getNumeroExterior().length()>50){
				return false;
			}

		}catch (NullPointerException e){
			System.out.println("error al validar: " + e.getMessage());
			return false;
		}
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.stps.portal.core.candidate.service.impl.CandidatoAppServiceRemote
	 * #buscarPerfil(java.lang.long)
	 */
	@Override
	public PerfilVO buscarPerfil(long idCandidato) throws SQLException {
		CandidatoDAO dao = new CandidatoDAO();
		PerfilVO perfil = dao.buscarPerfil(idCandidato);
		return perfil;
	}
	
	@Override
	public List<OtroMedioVO> buscarOtrosMedios(long idCandidato)throws SQLException {
		CandidatoDAO dao = new CandidatoDAO();
		List<OtroMedioVO> otrosMediosVO = dao.buscarOtrosMedios(idCandidato);
		return otrosMediosVO;
	}
	
	@Override
	public PerfilVO buscarPerfilCandidato(long idCandidato) throws SQLException {
		CandidatoDAO dao = new CandidatoDAO();
		PerfilVO perfil = dao.buscarPerfilCandidato(idCandidato);
		return perfil;
	}

	// Obtener Solicitante OAM
	public Solicitante obtenerSolicitante(long idCandidato) {
		Solicitante solicitante = solicitanteFacade.findByIdCandidato(idCandidato);
		return solicitante;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.stps.portal.core.candidate.service.impl.CandidatoAppServiceRemote
	 * #registrarEscolaridad(mx.gob.stps.portal.core.candidate.vo.EscolaridadVO)
	 */
	@Override
	public EscolaridadVO registrarEscolaridad(EscolaridadVO escolaridad) throws PersistenceException, TechnicalException, SQLException, BusinessException, IndexerException {

		if (validarEscolaridad(escolaridad)){
			
			EscolaridadVO escVO = candidatoFacade.registrarEscolaridad(escolaridad);
			
			if (escolaridad.getSinEstudios() == Constantes.ESCOLARIDAD.CON_ESTUDIOS.getIdOpcion()){
				bitacoraFacade.save(EVENTO.REGISTRO_ESCOLARIDAD_CANDIDATO.getIdEvento(), 
					                escVO.getIdUsuario(), EVENTO.REGISTRO_ESCOLARIDAD_CANDIDATO.getEvento(), 
					                Calendar.getInstance(), escVO.toBitacora(), escVO.getIdCandidato(), TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
			}
			
			return escVO;
			
		}else{
			throw new BusinessException("Existen errores en los valores de la escolaridad");
		}
	}

	public List<Long> consultaHabilidades(long idCandidato){
		return candidatoFacade.consultaHabilidades(idCandidato);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.stps.portal.core.candidate.service.impl.CandidatoAppService
	 * #validarEscolaridad(mx.gob.stps.portal.core.candidate.vo.EscolaridadVO)
	 */

	private boolean validarEscolaridad(EscolaridadVO escolaridad){
		try{
			
			if (escolaridad.getSinEstudios() == Constantes.ESCOLARIDAD.CON_ESTUDIOS.getIdOpcion()){
				if (escolaridad.getGrado() == null /*|| escolaridad.getGrado().getIdCandidatoGradoAcademico()<=0*/ 
						|| escolaridad.getGrado().getIdCarreraEspecialidad()<=0 
						/**|| escolaridad.getGrado().getEscuela().isEmpty()
						|| escolaridad.getGrado().getEscuela().length()>150
						|| escolaridad.getGrado().getInicio()<=0
						|| escolaridad.getGrado().getFin()<=0**/
						|| escolaridad.getGrado().getIdSituacionAcademica()<=0){
					logger.error("Error en Grado");
					return false;
				}
			
			}
		}catch (NullPointerException e){
			return false;
		}
		
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.stps.portal.core.candidate.service.impl.CandidatoAppServiceRemote
	 * #buscarGrados(long)
	 */
	@Override
	public List<GradoAcademicoVO> buscarGrados(long idCandidato, int principal)throws SQLException {
		List<GradoAcademicoVO> gradosVO = null;
		Connection conn = null;
		Context context = null;
		try {
    		ConnectionWraper wraper = ConexionFactory.getInstance().getConnection();
    		conn = wraper.getConnection();
    		context = wraper.getContext();

			gradosVO = buscarGrados(idCandidato, principal, conn);

		} catch (Exception e1) {
			e1.printStackTrace(); throw new SQLException(e1);
		} finally {
			try{if (conn!=null) conn.close();}catch(Exception e){e.printStackTrace();}

			if (context!=null){
				try {
					context.close();
				} catch (Exception e) {e.printStackTrace();}
			}
		}
		
		return gradosVO;
	}

	public List<GradoAcademicoVO> buscarGrados(long idCandidato, int principal, Connection globalConnection)throws SQLException {
		CandidatoDAO dao = new CandidatoDAO(globalConnection);
		List<GradoAcademicoVO> gradosVO = dao.buscarGrados(idCandidato, principal);
		return gradosVO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.stps.portal.core.candidate.service.impl.CandidatoAppServiceRemote
	 * #agregarGrado(long, GradoAcademicoVO)
	 */
	@Override
	public long agregarGrado(long idCandidato, GradoAcademicoVO gradoVO) throws PersistenceException {
		candidatoFacade.agregarGrado(idCandidato, gradoVO);
		return gradoVO.getIdCandidatoGradoAcademico();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.stps.portal.core.candidate.service.impl.CandidatoAppServiceRemote
	 * #borrarGrado(long)
	 */
	@Override
	public void borrarGrado(long idCandidatoGradoAcademico)throws PersistenceException {
		candidatoFacade.borrarGrado(idCandidatoGradoAcademico);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.stps.portal.core.candidate.service.impl.CandidatoAppServiceRemote
	 * #buscarIdiomas(long, int)
	 */
	@Override
	public List<IdiomaVO> buscarIdiomas(long idCandidato, int principal)throws SQLException {
		List<IdiomaVO> candidatoIdiomaList = new ArrayList<IdiomaVO>();
		List<IdiomaVO> idiomasVO = candidatoIdiomaFacadeLocal.candidatoIdiomasList(idCandidato);
		for (IdiomaVO lang : idiomasVO) {
			if (principal == lang.getPrincipal()) candidatoIdiomaList.add(lang);
		}
		return candidatoIdiomaList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.stps.portal.core.candidate.service.impl.CandidatoAppServiceRemote
	 * #agregarIdioma(long, IdiomaVO)
	 */
	@Override
	public long agregarIdioma(long idCandidato, IdiomaVO idiomaVO)throws PersistenceException {
		candidatoFacade.agregarIdioma(idCandidato, idiomaVO);
		return idiomaVO.getIdCandidatoIdioma();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.stps.portal.core.candidate.service.impl.CandidatoAppServiceRemote
	 * #borrarIdioma(long)
	 */
	@Override
	public void borrarIdioma(long idCandidatoIdioma)throws PersistenceException {
		candidatoFacade.borrarIdioma(idCandidatoIdioma);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.stps.portal.core.candidate.service.impl.CandidatoAppServiceRemote
	 * #buscarConocHabs(long, int)
	 */
	@Override
	public List<ConocimientoHabilidadVO> buscarConocHabs(long idCandidato, long idTipoConocimHabilidad, int principal) throws SQLException {
		CandidatoDAO dao = new CandidatoDAO();
		List<ConocimientoHabilidadVO> conocsHabsVO = dao.buscarConocimientosHabilidades(idCandidato, idTipoConocimHabilidad, principal);
		return conocsHabsVO;
	}
		
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.stps.portal.core.candidate.service.impl.CandidatoAppServiceRemote
	 * #agregarConocHab(long, ConocimientoHabilidadVO)
	 */
	@Override
	public long agregarConocHab(long idCandidato,ConocimientoHabilidadVO conocHabVO) throws PersistenceException {
		candidatoFacade.agregarConocHab(idCandidato, conocHabVO);
		return conocHabVO.getIdCandidatoConocimHabilidad();
	}

	@Override
	public int actualizarConocHab(ConocimientoHabilidadVO conocHabVO) throws PersistenceException {
		return candidatoFacade.actualizarConocHab(conocHabVO);
	}	
	
	@Override
	public int guardarCandidatoHabilidades(long idCandidato, long[] habilidades){
		return candidatoFacade.guardarCandidatoHabilidades(idCandidato, habilidades);
	}

    @Override
    public CandidatoVo findPpcSdTermsAndConditionsData(final long idUsuario) {
        return candidatoFacade.findPpcSdTermsAndConditionsData(idUsuario);
    }

    /*
     * (non-Javadoc)
     * @see
     * mx.gob.stps.portal.core.candidate.service.impl.CandidatoAppServiceRemote
     * #borrarConocHab(long)
     */
	@Override
	public void borrarConocHab(long idCandidatoConocimHabilidad)throws PersistenceException {
		candidatoFacade.borrarConocHab(idCandidatoConocimHabilidad);
	}
	/*
	 * (non-Javadoc)
	 * @see
	 * mx.gob.stps.portal.core.candidate.service.impl.CandidatoAppServiceRemote
	 * #buscarCompuAvanzadas(long, int)
	 */
	@Override
	public List<ComputacionAvanzadaVO> buscarCompuAvanzadas(long idCandidato,int principal) throws SQLException {
		CandidatoDAO dao = new CandidatoDAO();
		List<ComputacionAvanzadaVO> compuAvanzadasVO = dao.buscarCompuAvanzadas(idCandidato, principal);
		return compuAvanzadasVO;
	}
	/*
	 * (non-Javadoc)
	 * @see
	 * mx.gob.stps.portal.core.candidate.service.impl.CandidatoAppServiceRemote
	 * #agregarCompuAvanzada(long, ComputacionAvanzadaVO)
	 */
	@Override
	public long agregarCompuAvanzada(long idCandidato,ComputacionAvanzadaVO compAvanVO) throws PersistenceException {
		candidatoFacade.agregarCompuAvanzada(idCandidato, compAvanVO);
		return compAvanVO.getIdCandidatoCompuAvanzada();
	}
	/*
	 * (non-Javadoc)
	 * @see
	 * mx.gob.stps.portal.core.candidate.service.impl.CandidatoAppServiceRemote
	 * #borrarCompuAvanzada(long)
	 */
	@Override
	public void borrarCompuAvanzada(long idCandidatoCompuAvanzada)throws PersistenceException {
		candidatoFacade.borrarCompuAvanzada(idCandidatoCompuAvanzada);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.stps.portal.core.candidate.service.impl.CandidatoAppServiceRemote
	 * #registrarExperiencia(mx.gob.stps.portal.core.candidate.vo.ExperienciaVO)
	 */
	@Override
	public ExperienciaVO registrarExperiencia(ExperienciaVO experiencia) throws PersistenceException, TechnicalException, SQLException, BusinessException, IndexerException {

		//validarExperiencia(experiencia);
		
		ExperienciaVO expVO = candidatoFacade.registrarExperiencia(experiencia);
		bitacoraFacade.save(EVENTO.REGISTRO_EXPERIENCIA_CANDIDATO.getIdEvento(), 
				expVO.getIdUsuario(), EVENTO.REGISTRO_EXPERIENCIA_CANDIDATO.getEvento(), 
				Calendar.getInstance(), expVO.toBitacora(), expVO.getIdCandidato(), TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
		return expVO;
	}
	
	
	
	@Override
	public List<HistoriaLaboralVO> buscarHistLaboral(long idCandidato,int principal) throws SQLException {

		CandidatoDAO dao = new CandidatoDAO();
		List<HistoriaLaboralVO> histLaboralesVO = dao.buscarHistLaboral(idCandidato, principal);
		return histLaboralesVO;
	}
	
	@Override
	public long agregarHistLaboral(long idCandidato,HistoriaLaboralVO histLaboralVO) throws PersistenceException {
		candidatoFacade.agregarHistLaboral(idCandidato, histLaboralVO);
		return histLaboralVO.getIdHistorialLaboral();
	}

	@Override
	public void borrarHistLaboral(long idHistorialLaboral)throws PersistenceException {
		candidatoFacade.borrarHistLaboral(idHistorialLaboral);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.stps.portal.core.candidate.service.impl.CandidatoAppServiceRemote
	 * #registrarExpectativa(mx.gob.stps.portal.core.candidate.vo.ExpectativaVO)
	 */
	@Override
	public ExpectativaVO registrarExpectativa(ExpectativaVO expectativa) throws PersistenceException, TechnicalException, SQLException, BusinessException, IndexerException {

			ExpectativaVO expecVO = candidatoFacade.registrarExpectativa(expectativa);
						
			bitacoraFacade.save(EVENTO.REGISTRO_EXPECTATIVA_CANDIDATO.getIdEvento(), 
					            expecVO.getIdUsuario(),
					            EVENTO.REGISTRO_EXPECTATIVA_CANDIDATO.getEvento(), 
					            Calendar.getInstance(), expecVO.toBitacora(), expecVO.getIdCandidato(), TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
			
			
			return expecVO;
	}
	
	@Override
	public List<ExpectativaLaboralVO> buscarExpecLaboral(long idCandidato, int principal) throws SQLException {
		CandidatoDAO dao = new CandidatoDAO();
		List<ExpectativaLaboralVO> expecLaboralesVO = dao.buscarExpecLaboral(idCandidato, principal);
		return expecLaboralesVO;
	}
	
	@Override
	public long agregarExpecLaboral(long idCandidato,ExpectativaLaboralVO expecLaboralVO) throws PersistenceException {
		candidatoFacade.agregarExpecLaboral(idCandidato, expecLaboralVO);
		return expecLaboralVO.getIdExpectativaLaboral();
	}
	
	@Override
	public void borrarExpecLaboral(long idExpectativaLaboral)throws PersistenceException {
		candidatoFacade.borrarExpecLaboral(idExpectativaLaboral);
	}
	
	@Override
	public List<ExpectativaLugarVO> buscarExpecLugar(long idCandidato,int principal) throws SQLException {
		CandidatoDAO dao = new CandidatoDAO();
		List<ExpectativaLugarVO> expecLugaresVO = dao.buscarExpecLugar(
				idCandidato, principal);
		return expecLugaresVO;
	}

	@Override
	public long agregarExpecLugar(long idCandidato,ExpectativaLugarVO expecLugarVO) throws PersistenceException {
		candidatoFacade.agregarExpecLugar(idCandidato, expecLugarVO);
		return expecLugarVO.getIdExpectativaLugar();
	}

	@Override
	public void borrarExpecLugar(long idExpectativaLugar)throws PersistenceException {
		candidatoFacade.borrarExpecLugar(idExpectativaLugar);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.stps.portal.core.candidate.service.impl.CandidatoAppServiceRemote
	 * #actualizaCurriculum(long,
	 * mx.gob.stps.portal.core.candidate.vo.CurriculumVo)
	 */
	@Override
	public void actualizarCurriculum(long idCandidato, long idPerfil, CurriculumVo vo) throws PersistenceException, BusinessException {
		
		if (idCandidato > 0 && idPerfil == PERFIL.CANDIDATO.getIdOpcion() && 
			vo.isTermsConditionsAccepted() && vo.getVideoURL() != null && vo.getVideoURL().length() > 0) {

			// Siempre que se modifica el video curriculo, se tiene que validar el contenido
			vo.setEstatusVideoc(ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion());
			perfilLaboralFacade.actualizarCurriculum(idCandidato, vo);
			autorizacionAppService.registraVideoCurrPorValidar(idCandidato);
		} else {
			throw new BusinessException("errors.user.notAllowed");
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void guardarFoto(long idCandidato, long idPerfil, byte[] fotografia) throws PersistenceException, BusinessException {
		
		if (idCandidato > 0 && idPerfil == PERFIL.CANDIDATO.getIdOpcion()) {
			if (fotografia != null) {
				perfilLaboralFacade.guardarFoto(idCandidato, fotografia);
			} else {
				throw new BusinessException("can.foto.vacia.err");
			}
		} else {
			throw new BusinessException("errors.user.notAllowed");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InformacionGeneralVO buscarInformacionGeneral(long idCandidato) {
		logger.debug("*********************************************************************");
		logger.debug("Método: buscarInformacionGeneral");
		InformacionGeneralVO info = null;


		if (idCandidato > 0) {
			Context context = null;
			Connection conn = null;

			try {
				ConnectionWraper wraper = ConexionFactory.getInstance().getConnection();
				conn = wraper.getConnection();
				context = wraper.getContext();

				CandidatoDAO dao = new CandidatoDAO(conn);
				ConocimientoHabilidadDAO chdao = new ConocimientoHabilidadDAO(conn);

				info = dao.buscarInformacionGeneral(idCandidato);
				info.setConocimientos(chdao.buscarConocimientos(idCandidato));
				info.setHabilidades(chdao.buscarHabilidades(idCandidato));
				info.getDatosCurriculum().setCertificaciones(chdao.buscarCertificaciones(idCandidato));	
				logger.debug("Buscando idiomas....");
				info.getDatosCurriculum().setIdiomas(chdao.buscarIdiomas(idCandidato));
				info.setIdiomas(info.getDatosCurriculum().getIdiomas());
				CandidatoVo candidatoTemporal = candidatoFacade.find(idCandidato);								
				String discapacidades = candidatoTemporal.getDiscapacidades();
				info.setDescripcionDiscapacidad(candidatoTemporal.getDescripcionDiscapacidades(discapacidades));
				
				ConocimientoComputacionVO computacionVO = findConocimientosComputacion(idCandidato);
				List<Long> habilidad = candidatoFacade.consultaHabilidades(idCandidato);
				List<CatalogoOpcionVO> habilidadesCandidato = new ArrayList<CatalogoOpcionVO>();
				for(Long idCatalogoOpcion: habilidad){
					CatalogoOpcionVO vo = new CatalogoOpcionVO();
					vo= catalogoOpcionFacade.findById(CATALOGO_OPCION_CANDIDATO_HABILIDAD, idCatalogoOpcion);
					if(vo!=null)habilidadesCandidato.add(vo);
				}
				info.setHabilidadesCandidato(habilidadesCandidato);
				logger.debug("Buscando telefonos....");
				logger.debug("Tipo de propietario...."+Constantes.TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
				List<TelefonoVO> tels = telefonoFacade.getTelefonosPropietario(idCandidato, Constantes.TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
				info.setTelefonos(tels);
				PerfilLaboralVo perfilLaboralVo = perfilLaboralFacade.find(idCandidato);

				if(perfilLaboralVo!=null){
					info.setAniosExperencia(EXPERIENCIA.getDescripcion((int) perfilLaboralVo.getIdExperienciaTotal()));
					info.setExperiencia(perfilLaboralVo.getExperiencia());
				}

				ExpectativaLaboralVO expectativaLaboralVo = null;
				List<ExpectativaLaboralVO> expectativas = dao.buscarExpecLaboral(idCandidato, MULTIREGISTRO.PRINCIPAL.getIdOpcion());
				if (null != expectativas && !expectativas.isEmpty())
					expectativaLaboralVo = expectativas.get(0);
				if (null != expectativaLaboralVo) {				
					info.setPuestoSolicitado(expectativaLaboralVo.getPuestoDeseado());
					info.setSalarioPretendido(Utils.formatMoney(expectativaLaboralVo.getSalarioPretendido()));
				}
				if (computacionVO!=null)
					info.setConocimientoComputacionVO(computacionVO);

				int porcentajeCV = getEstatusCV(idCandidato, conn);

				info.setPorcentajeCV(porcentajeCV);

			} catch (Exception sqle) {
				logger.error("Al obtener la informacion general del Candidato",sqle);
			}finally{
				try{if (conn!=null) conn.close();}catch(Exception e){e.printStackTrace();}

				if (context!=null){
					try {
						context.close();
					} catch (Exception e) {e.printStackTrace();}
				}
			}
		}
		return info;
	}

	public PerfilLaboralVo consultaPerfilLaboral(long idCandidato) throws PersistenceException {
		if (idCandidato<=0) throw new IllegalArgumentException("Identificador del candidato requerido");

		PerfilLaboralVo  perfilVO = perfilLaboralFacade.find(idCandidato);

		return perfilVO;
	}

	@Override
	public void notificaCandidato(CandidatoVo candidatoVo)throws PersistenceException, BusinessException, TechnicalException,MailException {
		
		CandidatoVo candidatoVoTemp = candidatoVo;
		
				
		try {			
			if (candidatoVoTemp.getEstatus() != ESTATUS.INACTIVO.getIdOpcion()) {
				throw new BusinessException("El usuario no tiene estatus de INACTIVO, no puede ser procesado");
			}
		
			String password = Password.getPassword();
			String passw = Password.codificaPassword(password);
						
			/* Se crea usuario */
			UsuarioVO usuarioVO = null;				
			usuarioVO = new UsuarioVO();
												
			usuarioVO.setContrasena(passw);
			usuarioVO.setCorreoElectronico(candidatoVoTemp
					.getCorreoElectronico());
			usuarioVO.setEstatus(ESTATUS.INACTIVO.getIdOpcion());
			usuarioVO.setFechaAlta(new Date());
			usuarioVO.setFechaModificacion(new Date());
			// TODO Revisar como se obtiene el valor de la entidad
			// usuarioVO.setIdEntidad(9);
			usuarioVO.setIdPerfil(PERFIL.CANDIDATO.getIdOpcion());		
			usuarioVO.setIdRegistro(Constantes.ID_REGISTRO_PORTAL);
			usuarioVO.setIdTipoUsuario(TIPO_USUARIO.CANDIDATO.getIdTipoUsuario());

			/* Crea nuevo usuario */
			long idUsuario;
			idUsuario = usuarioFacade.save(usuarioVO);
			
			int idusuario;
			idusuario = (int)idUsuario;
			/*Relacion de usuario con candidato, actualizando id de usuario en candidato*/
			
			candidatoFacade.updateIdUsuario(candidatoVoTemp, idusuario);
			
							
			/*Notifico via email a candidato*/
			NotificacionService notificacionService = new NotificacionService();
			notificacionService.notificacionEmailToCandidato(candidatoVoTemp, password);
			
		} catch (MailException e) {
			throw new MailException(e);
		}catch ( EncodingException e){
			e.printStackTrace();
		}
	}
	
	public void notificaCandidatoFueraDeVigencia(CandidatoVo candidatoVo)throws PersistenceException, BusinessException, TechnicalException,MailException{
		try{
			NotificacionService notificacionService = new NotificacionService();
			notificacionService.notificaCandidatoFueraDeVigencia(candidatoVo);			
		} catch (MailException e) {
			throw new MailException(e);
		}
	}

	
	@Override
	public void guardarSumaCalcu(long idPropietario, double suma)
			throws PersistenceException {
		if (idPropietario <= 0) throw new IllegalArgumentException("Identificador del propietario requerido");
		if (suma <= 0) throw new IllegalArgumentException("La suma debe ser mayor que 0");
		CalculadoraCandidato calculadoraCandidato = calculadoraCandidatoFacade.findCalculadoraCandidato(idPropietario);
		if (calculadoraCandidato == null) {
			calculadoraCandidatoFacade.save(idPropietario, suma);
		}else {
			calculadoraCandidatoFacade.update(idPropietario, suma);
		}
	}

	@Override
	public void notificaCandidato(CandidatoVo candidatoVo, long idUsuario, String emailanterior)throws PersistenceException, BusinessException, TechnicalException,MailException {

		try {			
			if (candidatoVo.getEstatus() != ESTATUS.INACTIVO.getIdOpcion()){
				throw new BusinessException("El usuario no tiene estatus de MODIFICADA no puede ser procesado");
			}

			String password = Password.getPassword();
			String passwdb = Password.codificaPassword(password);

			usuarioFacade.updatePassword(candidatoVo.getIdUsuario(), passwdb);
			
			NotificacionService notificacionService = new NotificacionService();
			notificacionService.notificacionEmailToCandidato(candidatoVo, password);

			if (candidatoVo.getEstatus() == ESTATUS.INACTIVO.getIdOpcion() || (candidatoVo.getIdUsuario() == idUsuario)) {			
				
				bitacoraEstatus(EVENTO.NOTIFICACION_CANDIDATO_CAMBIO_EMAIL,         idUsuario, candidatoVo.getIdCandidato(), emailanterior);

			} else if(candidatoVo.getEstatus() == ESTATUS.INACTIVO.getIdOpcion() || (candidatoVo.getIdUsuario() != idUsuario) ){

				bitacoraEstatus(EVENTO.NOTIFICACION_CANDIDATO_EMPRESA_CAMBIO_EMAIL, idUsuario, candidatoVo.getIdCandidato(), emailanterior);
			}

		} catch (MailException e) {
			throw new MailException(e);
		}catch ( EncodingException e){
			e.printStackTrace();
		}		
	}
	
	public void notificaCambioContrasena(CandidatoVo candidato, String correoElectronico) throws TechnicalException, MailException {
		try {
			String newPsw = Password.getPassword();
			NotificacionService notificacionService = new NotificacionService();
			notificacionService.notificacionRecuperacionPsw(candidato, correoElectronico, newPsw);
		} catch (MailException e) {
			throw e;
		} catch(Exception e){
			throw new TechnicalException(e);
		}
	}

	
	private void bitacoraEstatus(EVENTO evento, long idUsuario, long idRegistro, String estatusAnterior) {
		bitacoraEstatus(evento, idUsuario, null, idRegistro, estatusAnterior);
	}

	private void bitacoraEstatus(EVENTO evento, long idUsuario, String descripcion, long idRegistro, String estatusAnterior) {

		String idDetalle = null;
		idDetalle = "idCandidato=" + idRegistro;
		String detalle = "";
		
		if(estatusAnterior != null){
			detalle = idDetalle + "|email=" + estatusAnterior;
		}else{
			detalle = idDetalle;
		}
		
		
		if (descripcion == null)
			descripcion = evento.getEvento();
		
		bitacoraFacade.save(evento.getIdEvento(), idUsuario, descripcion,Calendar.getInstance(), detalle, idRegistro, TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());

	    
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<CandidatoVo> obtenerCandidatos(long idEmpresa, long idOferta) throws SQLException {
		Context context = null;
		Connection conn = null;
		List<CandidatoVo> candidatos = new ArrayList<CandidatoVo>();
		try {
    		ConnectionWraper wraper = ConexionFactory.getInstance().getConnection();
    		conn = wraper.getConnection();
    		context = wraper.getContext();

	    	CandidatoDAO dao = new CandidatoDAO(conn);
			candidatos = dao.buscarCandidatos(idEmpresa, idOferta);

			for  (CandidatoVo candidato : candidatos) {
				
				if (candidato.getCompatibilidad()<=0){
//					int compatibilidad = ofertaCompatibilidadAppService.match(idOferta, candidato.getIdCandidato(), conn);
					int compatibilidad = IndexerServiceLocator.getIndexerServiceRemote().match(idOferta, candidato.getIdCandidato());
					candidato.setCompatibilidad(compatibilidad);
					
					ofertaFacade.actualizaCompatibilidad(idOferta, candidato.getIdCandidato(), compatibilidad);
					//logger.info("Compatibilidad actualizada idOfertaEmpleo ["+ idOferta +"], idCandidato ["+ candidato.getIdCandidato() +"]");
				}
				List<IdiomaVO> idiomas = dao.buscarIdiomas(candidato.getIdCandidato());
				candidato.setLstIdiomas(idiomas);
				candidato.setSubAreaLaboralDescripcion(getSubAreaDesc(candidato.getIdCandidato()));
			}

		} catch(Exception e){
			e.printStackTrace(); logger.error(e);
		} finally {
			try{if (conn!=null) conn.close();}catch(Exception e){e.printStackTrace();}
			
			if (context!=null){
				try {
					context.close();
				} catch (Exception e) {e.printStackTrace();}
			}
		}

		//logger.info("Candidatos vinculados ["+ candidatos.size() +"]");
		return candidatos;
	}

	@Override
	public List<CandidatoVo> obtenerPostulados(long idEmpresa, long idOferta)throws SQLException {
		//logger.info("Consulta de candidatos postulados");
		List<CandidatoVo> candidatos = new ArrayList<CandidatoVo>();
		Connection conn = null;
		Context context = null;
		try{
    		ConnectionWraper wraper = ConexionFactory.getInstance().getConnection();
    		conn = wraper.getConnection();
    		context = wraper.getContext();

			CandidatoDAO dao = new CandidatoDAO(conn);
			candidatos = dao.buscarPostulados(idEmpresa, idOferta);

			for (CandidatoVo candidato : candidatos) {
				
				if (candidato.getCompatibilidad()<=0){
					//logger.info("obtenerPostulados compatibilidad inicio" + new Date().toString());
//					int compatibilidad = ofertaCompatibilidadAppService.match(idOferta, candidato.getIdCandidato(), conn);
					int compatibilidad = IndexerServiceLocator.getIndexerServiceRemote().match(idOferta, candidato.getIdCandidato());
					//logger.info("obtenerPostulados compatibilidad fin" + new Date());
					candidato.setCompatibilidad(compatibilidad);					

					ofertaFacade.actualizaCompatibilidad(idOferta, candidato.getIdCandidato(), compatibilidad);
					//logger.info("Compatibilidad actualizada idOfertaEmpleo ["+ idOferta +"], idCandidato ["+ candidato.getIdCandidato() +"] " + new Date().toString());
				}
				//logger.info("obtenerPostulados buscarIdiomas inicio" + new Date().toString());
				List<IdiomaVO> idiomas = dao.buscarIdiomas(candidato.getIdCandidato());
				candidato.setLstIdiomas(idiomas);
				candidato.setSubAreaLaboralDescripcion(getSubAreaDesc(candidato.getIdCandidato()));
			}

		} catch(Exception e){
			e.printStackTrace(); logger.error(e);
		} finally {
			try{if (conn!=null) conn.close();}catch(Exception e){e.printStackTrace();}
			
			if (context!=null){
				try {
					context.close();
				} catch (Exception e) {e.printStackTrace();}
			}
		}

		return candidatos;
	}

	private String getSubAreaDesc(long idCandidato) {
		List<ExpectativaLaboralVO> expectativas;
		try {
			expectativas = this.buscarExpecLaboral(idCandidato, MULTIREGISTRO.PRINCIPAL.getIdOpcion());
		} catch (SQLException e) {
			expectativas = new ArrayList<ExpectativaLaboralVO>();
		}
		if (!expectativas.isEmpty()) {
			CatSubareaVO subarea = catalogoOpcionFacade.getSubAreaVOByIdAreaIdSubArea(expectativas.get(0).getIdAreaLaboralDeseada(), expectativas.get(0).getIdSubAreaLaboralDeseada());
			if (null != subarea)
				return subarea.getDescripcion();
		}
		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.stps.portal.core.candidate.service.impl.CandidatoAppServiceRemote
	 * #eliminarCandidatosOferta(long)
	 */
	@Override
	public void eliminarCandidatosOferta(long idOfertaCandidato) throws PersistenceException, BusinessException, SQLException {
		OfertaCandidatoVO ofertaCandidato = ofertaFacade.findById(idOfertaCandidato);
		if (ofertaCandidato == null){ throw new BusinessException("La oferta de candidato no es válida");}
		long idEstatus = ofertaCandidato.getEstatus();
		if (idEstatus == CATALOGO_OPCION_OFERTAS_VINCULADA){
			cambiarEstatus( ofertaCandidato, (int) CATALOGO_OPCION_OFERTAS_DESVINCULADA);
		}
		if (idEstatus == CATALOGO_OPCION_OFERTAS_SELECCIONADO){
			eliminarCandidatosOfertaSeleccionado(ofertaCandidato);
		}
		if (idEstatus == Constantes.ESTATUS.SELECCIONADA.getIdOpcion()){
			eliminarCandidatosOfertaSeleccionado(ofertaCandidato);
		}				
		if (idEstatus == CATALOGO_OPCION_OFERTAS_POSTULADO){
			cambiarEstatus( ofertaCandidato, (int) CATALOGO_OPCION_OFERTAS_RECHAZADO);
		}
			
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.stps.portal.core.candidate.service.impl.CandidatoAppServiceRemote
	 * #aprobarOfertaCandidato(long, Date)
	 */
	@Override
	public void aprobarOfertaCandidato(long idOfertaCandidato, Date fechaContrato, DestinatarioVO destinatarioVO ) throws PersistenceException, BusinessException, SQLException, MailException {

		OfertaCandidatoVO ofertaCandidato = ofertaFacade.findById(idOfertaCandidato);
		if (ofertaCandidato == null){ throw new BusinessException("La oferta de candidato no es válida");}
		long idEstatus = ofertaCandidato.getEstatus();
		if (idEstatus == CATALOGO_OPCION_OFERTAS_ENPROCESO || idEstatus == CATALOGO_OPCION_OFERTAS_POSTULADO) {
			ofertaCandidato.setEstatus((int) CATALOGO_OPCION_OFERTAS_CONTRATADO);
			ofertaCandidato.setFechaColocacion(fechaContrato);
			ofertaCandidato.setIdOficinaSeguimiento(10000L);
			if (null == ofertaCandidato.getFechaInicioContratacion()) {
				if (null != ofertaCandidato.getFechaSeguimiento())
					ofertaCandidato.setFechaInicioContratacion(ofertaCandidato.getFechaSeguimiento());
				else ofertaCandidato.setFechaInicioContratacion(fechaContrato);
			}
			ofertaCandidato.setFechaSeguimiento(new Date());
			ofertaCandidato.setIdUsuarioSeguimiento(Utils.parseLong(destinatarioVO.getIdCandidato()));
			ofertaCandidato.setIdFuenteSeguimiento((long)Catalogos.CATALOGO_FUENTE_QUE_REGISTRA_OFERTA.PORTAL.getIdOpcion());
			ofertaFacade.update(ofertaCandidato);
			bitacoraFacade.save(EVENTO.REGISTRA_CONTACTO, ofertaCandidato.getIdCandidato(), "Seguimiento postulación", ESTATUS.getDescripcion(ofertaCandidato.getEstatus()), ofertaCandidato.getIdCandidato(), TIPO_PROPIETARIO.CANDIDATO);
			try {
				NotificacionService notificacionService = new NotificacionService();
				notificacionService.notificacionOfertaCandidato(destinatarioVO, "Contratado");
			} catch(Exception e){
				e.printStackTrace(); logger.error(e);
			}
		}else{
			 throw new BusinessException("El candidato no esta en proceso para esta oferta");
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.stps.portal.core.candidate.service.impl.CandidatoAppServiceRemote
	 * #rechazarOfertaCandidatoEnProceso(long, int)
	 */
	@Override
	public void rechazarOfertaCandidatoEnProceso(long idOfertaCandidato, int idMotivo, String motivoDesc, DestinatarioVO destinatarioVO)
			throws PersistenceException, BusinessException, SQLException, MailException {		
		OfertaCandidatoVO ofertaCandidato = ofertaFacade.findById(idOfertaCandidato);
		if (ofertaCandidato == null){ throw new BusinessException("La oferta de candidato no es válida");}
		long idEstatus = ofertaCandidato.getEstatus();
		if (idEstatus == CATALOGO_OPCION_OFERTAS_ENPROCESO || idEstatus == CATALOGO_OPCION_OFERTAS_POSTULADO){
			ofertaCandidato.setIdMotivo(idMotivo);
			ofertaCandidato.setEstatus((int) CATALOGO_OPCION_OFERTAS_RECHAZADO);
			ofertaCandidato.setFechaSeguimiento(new Date());
			ofertaCandidato.setMotivoDesc(motivoDesc);
			ofertaFacade.update(ofertaCandidato);
			bitacoraFacade.save(EVENTO.REGISTRA_CONTACTO, ofertaCandidato.getIdCandidato(), "Seguimiento postulación", ESTATUS.getDescripcion(ofertaCandidato.getEstatus()), ofertaCandidato.getIdCandidato(), TIPO_PROPIETARIO.CANDIDATO);
			try {
				NotificacionService notificacionService = new NotificacionService();
				notificacionService.notificacionOfertaCandidato(destinatarioVO, "Rechazado");
			} catch(Exception e){
				e.printStackTrace(); logger.error(e);
			}
		}else{
			 throw new BusinessException("El candidato no esta en proceso para esta oferta");
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.stps.portal.core.candidate.service.impl.CandidatoAppServiceRemote
	 * #rechazarOfertaCandidatoEnProceso(long, int)
	 */
	@Override
	public void programarEntrevistaOfertaCandidato(long idOfertaCandidato, Date fecha, String hora, DestinatarioVO destinatarioVO)
			throws PersistenceException, BusinessException, SQLException, MailException {
		EntrevistaVO entrevista = new EntrevistaVO();
		OfertaCandidatoVO oferta = ofertaFacade.findById(idOfertaCandidato);
		if (oferta == null){ throw new BusinessException("La oferta de candidato no es válida");}
		entrevista.setEstatus((int) ESTATUS.NUEVA.getIdOpcion());
		entrevista.setIdOfertaEmpleo(oferta.getIdOfertaEmpleo());
		entrevista.setIdCandidato(oferta.getIdCandidato());
		entrevista.setFecha(fecha);
		entrevista.setHora(hora);
		entrevista.setFechaAlta(new Date());
		entrevista.setFechaModificacion(new Date());
		entrevistaFacade.save(entrevista);
		try {
			NotificacionService notificacionService = new NotificacionService();
			notificacionService.notificacionOfertaCandidato(destinatarioVO, "Entrevista");
		} catch(Exception e){
			e.printStackTrace(); logger.error(e);
		}
			
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.stps.portal.core.candidate.service.impl.CandidatoAppServiceRemote
	 * #rechazarOfertaCandidatoEnProceso(long, int)
	 */
	@Override
	public void vincularOfertaCandidato(long idOfertaCandidato, DestinatarioVO destinatarioVO)
			throws PersistenceException, BusinessException, SQLException, MailException {
		
		OfertaCandidatoVO oferta = ofertaFacade.findById(idOfertaCandidato);
		if (oferta == null){ throw new BusinessException("La oferta de candidato no es válida");}
		long idEstatus = oferta.getEstatus();
		if (idEstatus != CATALOGO_OPCION_OFERTAS_VINCULADA){
			if(idEstatus != CATALOGO_OPCION_OFERTAS_POSTULADO){  //si el estatus es postulado, no se realiza el cambio de estatus, solo se envía la notificación
				oferta.setEstatus((int) CATALOGO_OPCION_OFERTAS_VINCULADA);
				ofertaFacade.update(oferta);
			}			
						
			try{
				NotificacionService notificacionService = new NotificacionService();
				notificacionService.notificacionOfertaCandidato(destinatarioVO, "Vinculado");
			} catch(Exception e){
				e.printStackTrace(); logger.error(e);
			}
		}else{
			 throw new BusinessException("El candidato ya esta vinculado a esta oferta");
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.stps.portal.core.candidate.service.impl.CandidatoAppServiceRemote
	 * #rechazarOfertaCandidatoEnProceso(long, int)
	 */
	@Override
	public void procesarOfertaCandidatoEnProceso(long idOfertaCandidato, long idUsuario) throws PersistenceException, BusinessException, SQLException {
		OfertaCandidatoVO ofertaCandidato = ofertaFacade.findById(idOfertaCandidato);
		if (ofertaCandidato == null) { throw new BusinessException("La oferta de candidato no es válida"); }
		long idEstatus = ofertaCandidato.getEstatus();
		if (idEstatus != CATALOGO_OPCION_OFERTAS_ENPROCESO) {
			ofertaCandidato.setFechaSeguimiento(new Date());
			ofertaCandidato.setIdOficinaSeguimiento(10000L);
			ofertaCandidato.setIdUsuarioSeguimiento(idUsuario);
			ofertaCandidato.setFechaInicioContratacion(new Date());
			ofertaCandidato.setEstatus((int) CATALOGO_OPCION_OFERTAS_ENPROCESO);
			ofertaCandidato.setFuisteContratado(FUISTE_CONTRATADO.EN_ESPERA.getIdOpcion());
			ofertaCandidato.setIdFuenteSeguimiento((long)Catalogos.APLICACION.PORTAL_DEL_EMPLEO.getIdOpcion());
			ofertaFacade.update(ofertaCandidato);
			bitacoraFacade.save(EVENTO.REGISTRA_CONTACTO, ofertaCandidato.getIdCandidato(), "Seguimiento postulación", ESTATUS.getDescripcion(ofertaCandidato.getEstatus()), ofertaCandidato.getIdCandidato(), TIPO_PROPIETARIO.CANDIDATO);
		}else{
			throw new BusinessException("El candidato ya esta en proceso para esta oferta");
		}
	}

	/**
	 * Metodo que elimina la relacion del candidato seleccionado con la oferta
	 * @param oferta
	 * @throws PersistenceException
	 * @throws SQLException
	 */
	private void eliminarCandidatosOfertaSeleccionado(OfertaCandidatoVO oferta) 
		throws PersistenceException, SQLException{
		ofertaFacade.remove(oferta.getIdOfertaCandidato());
	}
	
	/**
	 * Cambia el estatus actual de una oferta de candidato, colocando la relacion actual con el estatus requerido 
	 * @param oferta
	 * @param estatusNuevo
	 * @throws PersistenceException
	 * @throws SQLException
	 */
	private void cambiarEstatus(OfertaCandidatoVO oferta, int estatusNuevo) throws PersistenceException, SQLException {
		oferta.setEstatus(estatusNuevo);
		ofertaFacade.update(oferta);
		
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.stps.portal.core.candidate.service.impl.CandidatoAppServiceRemote
	 * #obtenerOfertaCandidatoResumen(long)
	 */
	@Override
	public OfertaCandidatoResumenVo obtenerOfertaCandidatoResumen(long idOfertaCandidato) throws PersistenceException, SQLException{
		OfertaCandidatoResumenVo oferta = null;
		Connection conn = null;
		Context context = null;
		try {
    		ConnectionWraper wraper = ConexionFactory.getInstance().getConnection();
    		conn = wraper.getConnection();
    		context = wraper.getContext();
		
			OfertasCandidatoResumenDAO dao = new OfertasCandidatoResumenDAO(conn);
			EntrevistaDao entDao = new EntrevistaDao();
		
			oferta = dao.obtenerOfertasPorIDOfertaCandidato(idOfertaCandidato);
			EntrevistaVO entrevista = entDao.buscaEntrevistaOfertaCandidatoActiva(oferta.getIdCandidato(), oferta.getIdOfertaEmpleo());
			oferta.setEntrevista(entrevista);
		} catch (PersistenceException e1) {
			e1.printStackTrace(); throw e1;
		} catch (SQLException e1) {
			e1.printStackTrace(); throw e1;
		} catch (Exception e1) {
			e1.printStackTrace(); throw new SQLException(e1);
		} finally{
			try{if (conn!=null) conn.close();}catch(Exception e){e.printStackTrace();}
			
			if (context!=null){
				try {
					context.close();
				} catch (Exception e) {e.printStackTrace();}
			}
		}
		
		return oferta;
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.stps.portal.core.candidate.service.impl.CandidatoAppServiceRemote
	 * #obtenerCandidatos(long, Long)
	 */
	@Override
	public List<CandidatoVo> obtenerCandidatosOfertasActivas(long idEmpresa, long idOferta) throws SQLException {
		List<CandidatoVo> CandidatosVO = null;
		Connection conn = null;
		Context context = null;
		try {
    		ConnectionWraper wraper = ConexionFactory.getInstance().getConnection();
    		conn = wraper.getConnection();
    		context = wraper.getContext();
		
			CandidatoDAO dao = new CandidatoDAO(conn);
			
			CandidatosVO = dao.buscarCandidatos(idEmpresa, idOferta);
			
			Iterator<CandidatoVo> it = CandidatosVO.iterator();
			while (it.hasNext()) {
				CandidatoVo vo = it.next();
				List<GradoAcademicoVO> grados = buscarGrados(vo.getIdCandidato(), Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion(), conn);
				if (!grados.isEmpty())
					vo.setGradoacademicoVO(grados.get(0));
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace(); throw e1;
		} catch (Exception e1) {
			e1.printStackTrace(); throw new SQLException(e1);
		} finally{
			try{if (conn!=null) conn.close();}catch(Exception e){e.printStackTrace();}
			
			if (context!=null){
				try {
					context.close();
				} catch (Exception e) {e.printStackTrace();}
			}
		}
				
		return CandidatosVO;
	}

	@Override
	public void notificaCandidatoInactivaRegistrando(CandidatoVo candidatoVo,
			long idUsuarioAdmin)
			throws PersistenceException, BusinessException, TechnicalException,
			MailException {
		
		
		CandidatoVo candidatoVoTemp = candidatoVo;
				
		try {			
			if (candidatoVoTemp.getEstatus() != ESTATUS.INACTIVO.getIdOpcion()) {
				throw new BusinessException("El usuario no tiene estatus de INACTIVO, no puede ser procesado");
			}
					
			String password = Password.getPassword();
			String passw = Password.codificaPassword(password);
					   						
			/* Se crea usuario */
			UsuarioVO usuarioVO = null;				
			usuarioVO = new UsuarioVO();
												
			usuarioVO.setContrasena(passw);
			usuarioVO.setCorreoElectronico(candidatoVoTemp
					.getCorreoElectronico());
			usuarioVO.setEstatus(ESTATUS.INACTIVO.getIdOpcion());
			usuarioVO.setFechaAlta(new Date());
			// TODO Revisar como se obtiene el valor de la entidad
			// usuarioVO.setIdEntidad(9);
			usuarioVO.setIdPerfil(PERFIL.CANDIDATO.getIdOpcion());		
			usuarioVO.setIdRegistro(Constantes.ID_REGISTRO_PORTAL);
			usuarioVO.setIdTipoUsuario(TIPO_USUARIO.CANDIDATO.getIdTipoUsuario());

			/* Crea nuevo usuario */
			long idUsuario;
			idUsuario = usuarioFacade.save(usuarioVO);
			
			int idusuario;
			idusuario = (int)idUsuario;
			/*Relacion de usuario con candidato, actualizando id de usuario en candidato*/
			
			candidatoFacade.updateIdUsuario(candidatoVoTemp, idusuario);
													
			/*Notifico via email a candidato*/
			NotificacionService notificacionService = new NotificacionService();
			notificacionService.notificacionEmailToCandidato(candidatoVoTemp, password);			
			bitacoraEstatus(EVENTO.NOTIFICACION_CANDIDATO_ADMON_CAMBIO_EMAIL, idUsuario,candidatoVo.getIdCandidato(),null);
			
		} catch (MailException e) {
			throw new MailException(e);
		}catch ( EncodingException e){
			e.printStackTrace();
		}

		
	}

	@Override
	public void notificaCandidatoInactivaActualizando(CandidatoVo candidatoVo, long idUsuarioAdmin, String emailanterior) throws PersistenceException, BusinessException, TechnicalException, MailException {
		
		try {
			if (candidatoVo.getEstatus() != ESTATUS.INACTIVO.getIdOpcion()) {
				throw new BusinessException("El usuario no tiene estatus de INACTIVO, no puede ser procesado");
			}
					
			String password = Password.getPassword();
			String passw = Password.codificaPassword(password);
					   						
			usuarioFacade.updatePassword(candidatoVo.getIdUsuario(), passw);													

			try{
				NotificacionService notificacionService = new NotificacionService();
				notificacionService.notificacionEmailToCandidato(candidatoVo, password);
			} catch(Exception e){
				e.printStackTrace(); logger.error(e);
			}
			
			bitacoraEstatus(EVENTO.NOTIFICACION_CANDIDATO_ADMON_CAMBIO_EMAIL, idUsuarioAdmin, candidatoVo.getIdCandidato(),emailanterior);
			
		} catch (EncodingException e){
			e.printStackTrace();
		}		
	}


	@Override
	public List<GradoAcademicoVO> buscarGrados(long idCandidato)
			throws SQLException {
		CandidatoDAO dao = new CandidatoDAO();
		List<GradoAcademicoVO> gradosVO = dao.buscarGrados(idCandidato);
		return gradosVO;
	}

	@Override
	public List<IdiomaVO> buscarIdiomas(long idCandidato) throws SQLException {
		CandidatoDAO dao = new CandidatoDAO();
		List<IdiomaVO> idiomasVO = dao.buscarIdiomas(idCandidato);
		return idiomasVO;
	}

	@Override
	public List<ConocimientoHabilidadVO> buscarConocHabs(long idCandidato, long idTipoConocimHabilidad) throws SQLException {
		CandidatoDAO dao = new CandidatoDAO();
		List<ConocimientoHabilidadVO> conocsHabsVO = dao.buscarConocimientosHabilidades(idCandidato, idTipoConocimHabilidad);
		return conocsHabsVO;
	}

	@Override
	public List<ComputacionAvanzadaVO> buscarCompuAvanzadas(long idCandidato) throws SQLException {
		CandidatoDAO dao = new CandidatoDAO();
		List<ComputacionAvanzadaVO> compuAvanzadasVO = dao.buscarCompuAvanzadas(idCandidato);
		return compuAvanzadasVO;
	}

	@Override
	public List<HistoriaLaboralVO> buscarHistLaboral(long idCandidato)throws SQLException {
		CandidatoDAO dao = new CandidatoDAO();
		List<HistoriaLaboralVO> histLaboralesVO = dao.buscarHistLaboral(idCandidato);
		return histLaboralesVO;
	}

	@Override
	public List<ExpectativaLaboralVO> buscarExpecLaboral(long idCandidato) throws SQLException {
		CandidatoDAO dao = new CandidatoDAO();
		List<ExpectativaLaboralVO> expecLaboralesVO = dao.buscarExpecLaboral(idCandidato);
		return expecLaboralesVO;
	}

	@Override
	public List<ExpectativaLugarVO> buscarExpecLugar(long idCandidato)throws SQLException {
		CandidatoDAO dao = new CandidatoDAO();
		List<ExpectativaLugarVO> expecLugaresVO = dao.buscarExpecLugar(idCandidato);
		return expecLugaresVO;
	}
	
	@Override
	public CandidatoVo buscarDatosHeaderTemplateCandidato(long idCandidato) throws SQLException {
		CandidatoVo candidato = candidatoFacade.findById(idCandidato);
		return candidato;
	}
		
	@Override
	public int getEstatusCV(long idCandidato) throws SQLException {
		int estatus = 0;
		Connection conn = null;
		Context context = null;
		try {
    		ConnectionWraper wraper = ConexionFactory.getInstance().getConnection();
    		conn = wraper.getConnection();
    		context = wraper.getContext();

			estatus = getEstatusCV(idCandidato, conn);

		} catch (SQLException e1) {
			e1.printStackTrace(); throw e1;
		} catch (Exception e1) {
			e1.printStackTrace(); throw new SQLException(e1);
		} finally{
			try{if (conn!=null) conn.close();}catch(Exception e){e.printStackTrace();}
			
			if (context!=null){
				try {
					context.close();
				} catch (Exception e) {e.printStackTrace();}
			}
		}
		
		return estatus;
	}
	
	private int getEstatusCV(long idCandidato, Connection globalConnection) throws SQLException {
		CandidatoDAO dao = new CandidatoDAO(globalConnection);
		int estatus = 0;
		try{
			estatus = dao.getEstatusCVcandidato(idCandidato);
		}catch (SQLException e) {
			return 0;
		}
		return estatus;
	}
	
	public void setEstiloCV(long idCandidato, int estiloCv){
		try {
			CandidatoDAO dao = new CandidatoDAO();
			dao.actualizaEstiloCV(estiloCv, idCandidato);
		} catch (SQLException e) {logger.error(e);}
	}
	
	
	public int getEstiloCV(long idCandidato) {
		int estilo = 0;

		try {
			CandidatoDAO dao = new CandidatoDAO();
			estilo = dao.buscarEstiloCV(idCandidato);
		} catch (SQLException e) {logger.error(e);}

		return estilo;
	}

	@Override
	public CandidatoVerDetalleVO obtenerCandidatoVerDetalle(long idCandidato, int anio, int mes)  {
		CandidatoVerDetalleVO vo = null;
		try {
			vo = candidatoFacade.obtenerCandidatoVerDetalle(idCandidato, anio, mes);
		} catch (Exception e) {logger.error(e);}
		return vo;
	}

	@Override
	public void actualizarCandidatoVerDetalle(CandidatoVerDetalleVO vo){
		try {
			candidatoFacade.actualizarCandidatoVerDetalle(vo);
		} catch (Exception e) {logger.error(e);}
	}

	@Override
	public void crearCandidatoVerDetalle(CandidatoVerDetalleVO vo) {
		try {
			candidatoFacade.crearCandidatoVerDetalle(vo);
		} catch (Exception e) {logger.error(e);}
	}
	
	public void contabilizaDetalleCandidato(long idCandidato) {
		try {
			Calendar calendar = Calendar.getInstance();
			CandidatoVerDetalleVO candidatoDetalleVO = obtenerCandidatoVerDetalle(idCandidato,
					                                                              calendar.get(Calendar.YEAR),
					                                                              calendar.get(Calendar.MONTH));
			
			if(candidatoDetalleVO != null){
				candidatoDetalleVO.setContador(candidatoDetalleVO.getContador() + 1);
				actualizarCandidatoVerDetalle(candidatoDetalleVO);
			}else{
				candidatoDetalleVO = new CandidatoVerDetalleVO();
				candidatoDetalleVO.setAnio(calendar.get(Calendar.YEAR));
				candidatoDetalleVO.setContador(1);
				candidatoDetalleVO.setIdCandidato(idCandidato);
				candidatoDetalleVO.setMes(calendar.get(Calendar.MONTH)+1);
				crearCandidatoVerDetalle(candidatoDetalleVO);
			}
		}catch(Exception e) {logger.error(e);}
	}
	
	@Override
	public List<HistoriaLaboralVO> buscarHistEmpleoActual(long idCandidato, int trabajoActual) throws SQLException {
		if (idCandidato <= 0) throw new IllegalArgumentException("Identificador del propietario requerido");
		CandidatoDAO candidato = new CandidatoDAO();
		return candidato.buscarEmpleoActual(idCandidato, trabajoActual);
	}

	@Override
	public List<String> mediosBusqueda(long idCandidato) throws SQLException {
		if (idCandidato <= 0) throw new IllegalArgumentException("Identificador del propietario requerido");
		CandidatoDAO candidato = new CandidatoDAO();
		return candidato.mediosBusqueda(idCandidato);
	}

	//metodo para solo regresar el trabaja actual si existe si no no regresa nada
	@Override
	public PerfilBO loadPerfil(long idCandidato) throws BusinessException {
			return loadPerfil(idCandidato,false);
	}
	
	//Si tambien debe buscar el ultimo
	@Override
	public PerfilBO loadPerfil(long idCandidato,boolean ultimo) throws BusinessException {
		PerfilBO perfil = null;
		DomicilioVO domicilioVO = null;
		PerfilLaboralVo perfilLaboral = null;
		List<GradoAcademicoVO> gradosAcademicos = null;
		List<CandidatoOtroEstudioVO> otrosEstudiosAcademicos = null;		

		List<IdiomaVO> idiomas = null;

        ConocimientoComputacionVO conocimientoComputacionVO;
		List<HistoriaLaboralVO> histLaboral = null;
		List<ExpectativaLaboralVO> expectativas = null;

		Connection conn = null;
		Context context = null;
		try {
    		ConnectionWraper wraper = ConexionFactory.getInstance().getConnection();
    		conn = wraper.getConnection();
    		context = wraper.getContext();

			OfertaDetalleDAO ofertaDetalleDAO = OfertaDetalleDAO.getInstance(conn);
			CandidatoDetalleDAO candidatoDetalleDAO = CandidatoDetalleDAO.getInstance(conn);
			CandidatoDAO dao = new CandidatoDAO(conn);

			PerfilVO perfilVO = dao.buscarPerfil(idCandidato);
			
			if (null != perfilVO) {
				perfil = loadPefilSinTrabajoActualOUltimo(idCandidato,
						ofertaDetalleDAO, candidatoDetalleDAO, dao, perfilVO);
				perfil.setPerfilVO(perfilVO);
				histLaboral =  dao.buscarEmpleoActual(perfil.getIdCandidato(), (int)TRABAJA_ACTUALMENTE.SI.getIdOpcion());
				if (histLaboral != null && !histLaboral.isEmpty()) {
					perfil.setTrabajoActual(histLaboral.get(0));
				}else {
					if(ultimo){
						histLaboral =  dao.buscarEmpleoActual(perfil.getIdCandidato(), (int)TRABAJA_ACTUALMENTE.NO.getIdOpcion());
						if (histLaboral != null && !histLaboral.isEmpty()) {
							perfil.setTrabajoActual(histLaboral.get(0));
						}
						
					}
				}
			}

		} catch (SQLException sql) { 
			logger.error(sql); sql.printStackTrace(); 
		} catch (Exception sql) { 
			logger.error(sql); sql.printStackTrace(); 
		} finally {
			  try{
				  if (conn!=null) {
					  conn.close();
				  }
			  } catch(Exception e) {
				  e.printStackTrace();
			  }
			  
			  if (context!=null) {
				  try {
					  context.close();
				  } catch (Exception e) {
					e.printStackTrace();
				  }
			  }
		  }
		
		return perfil;
	}
	
	public PerfilBO loadPerfilTrabajoActualUltimo(long idCandidato,boolean ultimo) throws BusinessException {
		PerfilBO perfil = null;
		DomicilioVO domicilioVO = null;
		PerfilLaboralVo perfilLaboral = null;
		List<GradoAcademicoVO> gradosAcademicos = null;
		List<CandidatoOtroEstudioVO> otrosEstudiosAcademicos = null;		
		List<ConocimientoHabilidadVO> conocimientos = null;

		List<IdiomaVO> idiomas = null;

        ConocimientoComputacionVO conocimientoComputacionVO;
		List<HistoriaLaboralVO> histLaboral = null;
		List<ExpectativaLaboralVO> expectativas = null;

		Connection conn = null;
		Context context = null;
		try {
    		ConnectionWraper wraper = ConexionFactory.getInstance().getConnection();
    		conn = wraper.getConnection();
    		context = wraper.getContext();

			OfertaDetalleDAO ofertaDetalleDAO = OfertaDetalleDAO.getInstance(conn);
			CandidatoDetalleDAO candidatoDetalleDAO = CandidatoDetalleDAO.getInstance(conn);
			CandidatoDAO dao = new CandidatoDAO(conn);

			PerfilVO perfilVO = dao.buscarPerfil(idCandidato);
			
			if (null != perfilVO) {
				perfil = loadPefilSinTrabajoActualOUltimo(idCandidato,
						ofertaDetalleDAO, candidatoDetalleDAO, dao, perfilVO);
				
				histLaboral =  dao.buscarEmpleoActual(perfil.getIdCandidato(), (int)TRABAJA_ACTUALMENTE.SI.getIdOpcion());
				if (histLaboral != null && !histLaboral.isEmpty()) {
					perfil.setTrabajoActual(histLaboral.get(0));
				}else {
					if(ultimo){
						histLaboral =  dao.buscarEmpleoActual(perfil.getIdCandidato(), (int)TRABAJA_ACTUALMENTE.NO.getIdOpcion());
						if (histLaboral != null && !histLaboral.isEmpty()) {
							perfil.setTrabajoActual(histLaboral.get(0));
						}
						
					}
				}
			}

		} catch (SQLException sql) { 
			logger.error(sql); sql.printStackTrace(); 
		} catch (Exception sql) { 
			logger.error(sql); sql.printStackTrace(); 
		} finally {
			  try{
				  if (conn!=null) {
					  conn.close();
				  }
			  } catch(Exception e) {
				  e.printStackTrace();
			  }
			  
			  if (context!=null) {
				  try {
					  context.close();
				  } catch (Exception e) {
					e.printStackTrace();
				  }
			  }
		  }
		
		return perfil;
	}
	
	
	
	private PerfilBO loadPefilSinTrabajoActualOUltimo(long idCandidato, OfertaDetalleDAO ofertaDetalleDAO, CandidatoDetalleDAO candidatoDetalleDAO, CandidatoDAO dao, PerfilVO perfilVO) throws SQLException, BusinessException {
		
		PerfilBO perfil;
		List<IdiomaVO> idiomas;
		DomicilioVO domicilioVO;
		PerfilLaboralVo perfilLaboral;
		List<ExpectativaLaboralVO> expectativas;
		List<GradoAcademicoVO> gradosAcademicos;
		ConocimientoComputacionVO conocimientoComputacionVO;
		List<CandidatoOtroEstudioVO> otrosEstudiosAcademicos;
		
		perfil = new PerfilBO();
		BeanUtils.copyProperties(perfilVO, perfil);			
		perfilLaboral = perfilLaboralFacade.find(perfil.getIdCandidato());
		if (null != perfilLaboral) {
			perfil.setPerfilLaboral(perfilLaboral);
			perfil.setSector(ofertaDetalleDAO.getCatalogoOpcion(Constantes.CATALOGO_OPCION_SUBSECTOR, perfilLaboral.getIdSectorMayorExpr()));
			perfil.setArea(ofertaDetalleDAO.getCatalogoOpcion(Constantes.CATALOGO_OPCION_AREA_LABORAL, perfilLaboral.getIdAreaLaboralMayorExpr()));
			perfil.setOcupacion(ofertaDetalleDAO.getCatalogoOpcion(Constantes.CATALOGO_OPCION_OCUPACION, perfilLaboral.getIdOcupacionMayorExpr()));
		}
		List<TelefonoVO> tels = telefonoFacade.getTelefonosPropietario(perfil.getIdCandidato(), Constantes.TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
		perfil.setSecundarios(tels);
		Iterator<TelefonoVO> it = tels.iterator();
		while (it.hasNext()) {
			TelefonoVO principal = it.next();
			if (principal.getPrincipal() == Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion())
				perfil.setPrincipal(principal);
		}
		perfil.setEntidadNacimiento(ofertaDetalleDAO.getCatalogoOpcion(Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA, perfil.getIdEntidadNacimiento()));
		perfil.setUrlVideo(candidatoDetalleDAO.getUrlVideoc(perfil.getIdCandidato()));
		gradosAcademicos = dao.buscarGrados(perfil.getIdCandidato(), Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion());
		if (gradosAcademicos != null && !gradosAcademicos.isEmpty())
			perfil.setGradoPrincipal(gradosAcademicos.get(0));
		gradosAcademicos = dao.buscarGrados(perfil.getIdCandidato(), Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion());
		if (gradosAcademicos !=  null && !gradosAcademicos.isEmpty())
			perfil.setGradosAcademicos(gradosAcademicos);
		List<mx.gob.stps.portal.persistencia.vo.ConocimientoHabilidadVO> knwList = candidatoConocimHabilidadFacadeLocal.candidatoConocimHabilidadList(perfil.getIdCandidato());
		if (null != knwList && !knwList.isEmpty()) {
			perfil.setConocimientos(new ArrayList<mx.gob.stps.portal.persistencia.vo.ConocimientoHabilidadVO>());
			for (mx.gob.stps.portal.persistencia.vo.ConocimientoHabilidadVO knw : knwList) {
				if (knw.getIdTipoConocimHabilidad() == CONOC_HAB.CONOCIMIENTO.getIdOpcion()) {
					if (knw.getPrincipal() == MULTIREGISTRO.PRINCIPAL.getIdOpcion()) perfil.setConocimientoPrincipal(knw);
					else perfil.getConocimientos().add(knw);
				}
			}
		}
		// TODO: The follogin commented block is no useful any longer
//				habilidades = dao.buscarConocimientosHabilidades(perfil.getIdCandidato(), Constantes.CONOC_HAB.HABILIDAD.getIdOpcion(), Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion());
//				if (habilidades != null && !habilidades.isEmpty()) {
//					perfil.setHabilidadPrincipal(habilidades.get(0));
//				}
//				habilidades = dao.buscarConocimientosHabilidades(perfil.getIdCandidato(), Constantes.CONOC_HAB.HABILIDAD.getIdOpcion(), Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion());
//				if (habilidades != null && !habilidades.isEmpty()) {
//					perfil.setHabilidades(habilidades);
//				}
		idiomas = buscarIdiomas(perfil.getIdCandidato(), Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion());
		//idiomas = dao.buscarIdiomas(perfil.getIdCandidato(), Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion());
		if (idiomas != null && !idiomas.isEmpty()) {
			perfil.setIdiomaPrincipal(idiomas.get(0));
		}
		idiomas = buscarIdiomas(perfil.getIdCandidato(), Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion());
		//idiomas = dao.buscarIdiomas(perfil.getIdCandidato(), Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion());
		if (idiomas != null) {
			perfil.setIdiomas(idiomas);
		} else {
			perfil.setIdiomas(new ArrayList<IdiomaVO>());
		}

		conocimientoComputacionVO = findConocimientosComputacion(idCandidato);
		perfil.setConocimientoComputacionVO(conocimientoComputacionVO);
		expectativas = dao.buscarExpecLaboral(idCandidato);
		//expectativas = dao.buscarExpecLaboral(perfil.getIdCandidato(), Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion());
		if (expectativas != null && !expectativas.isEmpty()) {
			perfil.setExpectativaPrincipal(expectativas.get(0));
		}
		expectativas = dao.buscarExpecLaboral(perfil.getIdCandidato(), Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion());
		if (expectativas != null && !expectativas.isEmpty()) {
			perfil.setExpectativaLaboralList(expectativas);
		}
			
		List<String> medios = dao.mediosBusqueda(perfil.getIdCandidato());
		perfil.setMediosBusqueda(medios);
		
		if (perfil.getTrabaja() == null || perfil.getTrabaja().isEmpty()) {
			perfil.setTrabaja(new ArrayList<CatalogoOpcionVO>());
			CatalogoOpcionVO opcionVO = new CatalogoOpcionVO();
			opcionVO.setIdCatalogoOpcion(TRABAJA_ACTUALMENTE.SI.getIdOpcion());
			opcionVO.setOpcion(TRABAJA_ACTUALMENTE.SI.getOpcion());
			perfil.getTrabaja().add(opcionVO);
			opcionVO = new CatalogoOpcionVO();
			opcionVO.setIdCatalogoOpcion(TRABAJA_ACTUALMENTE.NO.getIdOpcion());
			opcionVO.setOpcion(TRABAJA_ACTUALMENTE.NO.getOpcion());
			perfil.getTrabaja().add(opcionVO);
		}
		
		domicilioVO = domicilioFacade.buscarDomicilioIdPropietario(perfil.getIdCandidato(), Constantes.TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
		if (domicilioVO != null) {
			perfil.setCalle(domicilioVO.getCalle());
			perfil.setIdDomicilio(domicilioVO.getIdDomicilio());
			perfil.setNumeroInterior(domicilioVO.getNumeroInterior());
			perfil.setNumeroExterior(domicilioVO.getNumeroExterior());
			perfil.setCodigoPostal(domicilioVO.getCodigoPostal());
			perfil.setEntreCalle(domicilioVO.getEntreCalle());
			perfil.setyCalle(domicilioVO.getyCalle());
			perfil.setIdEntidad(domicilioVO.getIdEntidad());
			perfil.setIdMunicipio(domicilioVO.getIdMunicipio());
			perfil.setIdColonia(domicilioVO.getIdColonia());
			perfil.setIdTipoPropietario(domicilioVO.getIdTipoPropietario());
			perfil.setIdLocalidad(domicilioVO.getIdLocalidad());
			perfil.setDomicilioReferencia(domicilioVO.getDomicilioReferencia());
			try {
				CodigoPostalVO codigoPostalVO = domicilioFacade.consultaCodigoPostal(domicilioVO.getIdColonia());
				if (codigoPostalVO!=null){
					String colonia = codigoPostalVO.getColoniaDescripcion();
					perfil.setColonia(colonia);
				}
				
				String entidad = ofertaDetalleDAO.getCatalogoOpcion(Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA, domicilioVO.getIdEntidad()); 
				if (entidad != null) {
					perfil.setEntidad(entidad);
				} else {
					perfil.setEntidad("");
				}
				
				MunicipioVO municipioVO = domicilioFacade.consultaMunicipio(domicilioVO.getIdMunicipio(), domicilioVO.getIdEntidad());
				if (municipioVO != null) {
					String municipio = municipioVO.getMunicipio();
					perfil.setMunicipio(municipio);
				}else{
					perfil.setMunicipio("");
				}
			} catch (SQLException sql) { 
				sql.printStackTrace(); 
			}
		}
		
		List<Long> habilidad = candidatoFacade.consultaHabilidades(perfil.getIdCandidato());
		List<CatalogoOpcionVO> habilidadesCandidato = new ArrayList<CatalogoOpcionVO>();
		for (Long idCatalogoOpcion : habilidad) {
			CatalogoOpcionVO vo = new CatalogoOpcionVO();
			vo = catalogoOpcionFacade.findById(CATALOGO_OPCION_CANDIDATO_HABILIDAD, idCatalogoOpcion);
			if (vo != null) {
				habilidadesCandidato.add(vo);
			}
		}
		perfil.setHabilidadesCandidato(habilidadesCandidato);
		perfil.setUltimaContratacion(getLastRecruit(perfil.getIdCandidato()));
		otrosEstudiosAcademicos = otrosEstudiosList(idCandidato);
		if(otrosEstudiosAcademicos != null && !otrosEstudiosAcademicos.isEmpty()){
			perfil.setOtrosEstudiosAcademicos(otrosEstudiosAcademicos);
		}
		return perfil;
	}
	
	private mx.gob.stps.portal.persistencia.vo.OfertaCandidatoVO getLastRecruit(long idCandidate) {
		List<mx.gob.stps.portal.persistencia.vo.OfertaCandidatoVO> ofertaCandidatoList = ofertaFacade.findAllOffersEstatusByCandidate(idCandidate, ESTATUS.CONTRATADO.getIdOpcion());
		if (!ofertaCandidatoList.isEmpty()) return ofertaCandidatoList.get(0);
		return null;
	}
	

	public List<CandidatoVo> filtrarCandidatos(String nombre, String apellido1, Date fechaNacimiento, String curp, String correoElectronico, String apellido2, String telefono, long idEntidad, long idMunicipio, String domicilio, String usuario) throws SQLException{
		CandidatoDAO candDAO = new CandidatoDAO();
		List<CandidatoVo> lstCandidatos = candDAO.filtrarCandidatos(nombre, apellido1, fechaNacimiento, curp, correoElectronico, apellido2, telefono, idEntidad, idMunicipio, domicilio, usuario);
		return lstCandidatos;
	}

	@Override
	public void actualizaRecibeOfertaCorreo(long idCandidato) {
		PerfilLaboralVo perfilLaboral = perfilLaboralFacade.find(idCandidato);
		long idRecibeOferta = perfilLaboral.getIdRecibeOferta();
		
		//logger.info("idrecibeoferta= "+idRecibeOferta);
		if(idRecibeOferta!=Constantes.RECIBE_OFERTA.CORREO.getIdRecibeOferta()){
			
			if(idRecibeOferta==Constantes.RECIBE_OFERTA.NO.getIdRecibeOferta())idRecibeOferta=Constantes.RECIBE_OFERTA.CORREO.getIdRecibeOferta();
			if(idRecibeOferta==Constantes.RECIBE_OFERTA.TELEFONO.getIdRecibeOferta())idRecibeOferta=Constantes.RECIBE_OFERTA.AMBOS.getIdRecibeOferta();
		}
		//logger.info("idrecibeoferta= "+idRecibeOferta );
		
		perfilLaboralFacade.actualizarIdRecibeOferta(idCandidato,idRecibeOferta);
		
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<CandidatoVo> consultaCandidatoInfonavit() throws PersistenceException {
		List<CandidatoVo> resultado = null;
		try{
			
			resultado = candidatoFacade.consultaCandidatoRegByInfonavit();
			
		}catch(Exception e){
			logger.error(e);
		}
		
		return resultado;
		
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<EstandarConocerVO> consultaConocer(long idCandidato) throws ConocerWSException {
		if (idCandidato<=0) throw new IllegalArgumentException("Identificador de candidato requerido");

		CandidatoVo candidato = candidatoFacade.find(idCandidato);

		if (candidato==null) throw new PersistenceException("Candidato ["+ idCandidato +"] no localizado");
		
		String curp = candidato.getCurp();
		
		if (curp==null || curp.isEmpty()) throw new IllegalArgumentException("El candidato ["+ idCandidato +"] no cuenta con CURP");

		ConocerWSService service = ConocerWSService.getInstance();

		try {
			List<EstandarConocerVO> estandares = service.consultaConocer(curp);

			for (EstandarConocerVO estandar : estandares){
				estandar.setIdCandidato(idCandidato);
			}

			return estandares;
		
		} catch (RuntimeException re) {
			logger.error("Error en la consulta de estándares del registro Conocer para el idCandidato = "+idCandidato);
			re.printStackTrace();
			throw new RuntimeException("Error en la consulta de estándares del registro Conocer");
		}
	}

	public ConocerConfigVO registraConocerConfig(ConocerConfigVO vo){
		ConocerConfigVO conocerConfig = null;
		
		try{
			conocerConfig = conocerFacade.save(vo);
		} catch(Exception e){
			logger.error(e);
		}

		return conocerConfig;
	}
	
	public ConocerConfigVO consultaConocerConfigByIdCandidato(long idCandidato) {
		ConocerConfigVO conocerConfig = null;		
		
		try{
			conocerConfig = conocerFacade.consultaConocerConfigByIdCandidato(idCandidato);			
		} catch (Exception e){
			logger.error(e);
		}
		return conocerConfig;
	}	

	
	public CandidatoVo findById(long idCandidato) {		
		CandidatoVo vo =  null;
		try{
			vo = candidatoFacade.findById(idCandidato);
		} catch (Exception e){
			logger.error(e);
		}
		return vo;	
	}	
	
	public DomicilioVO findDomicilioCandidato(long idCandidato){
		DomicilioVO domicilio = domicilioFacade.buscarDomicilioIdPropietario(idCandidato, (long)Catalogos.TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());			
		
		if (domicilio != null){
			
			// descripción de la entidad
			domicilio.setEntidad(Catalogos.ENTIDADES_FEDERATIVAS.getDescripcion((int)domicilio.getIdEntidad()));

			// descripción del municipio
			MunicipioVO municipio = domicilioFacade.consultaMunicipio(domicilio.getIdDomicilio(), domicilio.getIdEntidad());
			if (municipio != null)
				domicilio.setMunicipio(municipio.getMunicipio());
		}
		return domicilio;
		
	}
	
	@Override
	public long actualizarCandidatoComputacion(long idCandidatoComputacion, long idCandidato ,int procesadorTxt, int hojaCalculo, int internet, int redesSociales, String otros) throws BusinessException {
		return conocimientoComputacionFacade.actualizarConocimientosComputacion(idCandidatoComputacion, idCandidato, TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario(), 
				                                                                procesadorTxt, hojaCalculo, internet, redesSociales, otros);
	}

	@Override
	public ConocimientoComputacionVO findConocimientosComputacion(long idCandidato) throws BusinessException {
		return conocimientoComputacionFacade.findConocimientosComputacion(idCandidato, TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
	}
	
	
	@Override
	public long registrarOtroEstudio(CandidatoOtroEstudioVO otroEstudio) throws BusinessException {
		return candidatoOtroEstudioFacade.create(otroEstudio);
	}

	@Override
	public long actualizarOtroEstudio(CandidatoOtroEstudioVO otroEstudio) throws BusinessException {
		return candidatoOtroEstudioFacade.update(otroEstudio);
	}	
	
	@Override
	public long eliminarOtroEstudio(long idCandidatoOtroEstudio) throws BusinessException {
		return candidatoOtroEstudioFacade.delete(idCandidatoOtroEstudio);
	}	
	
	@Override
	public List<CandidatoOtroEstudioVO> otrosEstudiosList(long idCandidato) throws BusinessException {
		return candidatoOtroEstudioFacade.getCandidatoOtroEstudiosList(idCandidato);
	}	
	
	public int consultarEstatus(long idCandidato){
		int estatus = 0;
		estatus = candidatoFacade.consultaEstatusCandidato(idCandidato);
		return estatus;
	}
	
	public Integer consultarPpcEstatus(long idCandidato){
		Integer ppcEstatus = null;
		ppcEstatus = candidatoFacade.consultaEstatusCandidato(idCandidato);
		return ppcEstatus;
	}	
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void desactivarCandidato(long idCandidato, long idUsuario) {
		try {			
			CandidatoVo candidato = candidatoFacade.find(idCandidato);
			usuarioFacade.inactivarUsuarioPorSolicitud(candidato.getIdUsuario(), ESTATUS.INACTIVO_A_PETICION_DEL_CANDIDATO.getIdOpcion());		
			candidatoFacade.actualizaEstatusCandidato(idCandidato, ESTATUS.INACTIVO_A_PETICION_DEL_CANDIDATO.getIdOpcion());

			bitacoraFacade.save(EVENTO.DESACTIVAR_CANDIDATO, idUsuario, "Desactivar candidato a solicitud del candidato", "", idCandidato, TIPO_PROPIETARIO.CANDIDATO);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}

	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void desactivarCandidato(long idCandidato, long idUsuario, int idMotivoDesactivacion, String detalleDesactivacion) {
		try {			
			int nuevoEstatus = Constantes.ESTATUS.INACTIVO_POR_VIGENCIA.getIdOpcion();
			CandidatoVo candidato = candidatoFacade.find(idCandidato);
			if(idMotivoDesactivacion == Constantes.MOTIVO_DESACTIVACION_CANDIDATO.A_PETICION_DEL_USUARIO.getIdMotivo()){
				nuevoEstatus = Constantes.ESTATUS.INACTIVO_A_PETICION_DEL_CANDIDATO.getIdOpcion();
			} else if(idMotivoDesactivacion == Constantes.MOTIVO_DESACTIVACION_CANDIDATO.POR_MAL_USO_SERVICIOS_SNE.getIdMotivo()){
				nuevoEstatus = Constantes.ESTATUS.INACTIVO_POR_BAJA_ADMINISTRADOR.getIdOpcion();
			}			
			usuarioFacade.inactivarUsuarioPorSolicitud(candidato.getIdUsuario(), nuevoEstatus);		
			candidatoFacade.inactivarCandidato(idCandidato, idMotivoDesactivacion, detalleDesactivacion);			

			String descripcion = Constantes.MOTIVO_DESACTIVACION_CANDIDATO.getMotivo(idMotivoDesactivacion);
			
			if(idMotivoDesactivacion == Constantes.MOTIVO_DESACTIVACION_CANDIDATO.A_PETICION_DEL_USUARIO.getIdMotivo() ||
					idMotivoDesactivacion == Constantes.MOTIVO_DESACTIVACION_CANDIDATO.POR_MAL_USO_SERVICIOS_SNE.getIdMotivo()){
				bitacoraFacade.save(EVENTO.DESACTIVAR_CANDIDATO, idUsuario, descripcion, detalleDesactivacion, 
						idCandidato, TIPO_PROPIETARIO.CANDIDATO);				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}	
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void reactivarCandidato(long idCandidato, long idUsuario) {
		long id = 0;
		try {
			
			id = candidatoFacade.activarCandidato(idCandidato);
			if(id == idUsuario){
				if(usuarioFacade.activarUsuario(idUsuario)){
					bitacoraFacade.save(EVENTO.REACTIVAR_CANDIDATO, idUsuario, "Reactivar candidato a solicitud del candidato",
							"", idCandidato, TIPO_PROPIETARIO.CANDIDATO);
					}
				}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public String obtenerLoginUsuario(long idCandidato) {
		CandidatoVo candidato = candidatoFacade.find(idCandidato);
		if (null == candidato) {
			return null;
		} else {
			UsuarioVO usuario = usuarioFacade.find(candidato.getIdUsuario());
			if (null == usuario) {
				return null;
			} else {
				return usuario.getUsuario();
			}
		}
	}
	
	public List<OfertaCandidatoADesactivarVO> buscarOfertasRelacionadas(long idCandidato){
		return ofertaFacade.consultaOfertasRelacionadas(idCandidato);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ResultadoBusquedaCandidatosVO> busquedaEspecificaCandidatos(BusquedaCandidatosVO form) throws SQLException {
		
		return candidatoFacade.busquedaEspecificaCandidatos(form);
	}	
	
	public List<Long> consultaIdCandidatosFueraDeVigencia() throws SQLException{
		CandidatoDAO dao = new CandidatoDAO();
		return dao.consultaIdCandidatosFueraDeVigencia();
	}
	
	public List<CandidatoVo> consultaCandidatosAvisoDeVigencia() throws SQLException{
		CandidatoDAO dao = new CandidatoDAO();
		return dao.consultaCandidatosAvisoDeVigencia(); 		
	}
	
	//Start cambio movil
	@Override
	public PerfilBO loadPerfilUsuario(long idUsuario) throws BusinessException {
		PerfilBO perfil = null;
		List<IdiomaVO> idiomas = null;
		DomicilioVO domicilioVO = null;
		PerfilLaboralVo perfilLaboral = null;
		List<HistoriaLaboralVO> histLaboral = null;
		List<GradoAcademicoVO> gradosAcademicos = null;
		List<ExpectativaLaboralVO> expectativas = null;
		ConocimientoComputacionVO conocimientoComputacionVO;
		List<mx.gob.stps.portal.persistencia.vo.ConocimientoHabilidadVO> habilidades = null;
		List<mx.gob.stps.portal.persistencia.vo.ConocimientoHabilidadVO> conocimientos = null;
		
		Connection conn = null;
		Context context = null;
		try {
    		ConnectionWraper wraper = ConexionFactory.getInstance().getConnection();
    		conn = wraper.getConnection();
    		context = wraper.getContext();

			OfertaDetalleDAO ofertaDetalleDAO = OfertaDetalleDAO.getInstance(conn);
			CandidatoDetalleDAO candidatoDetalleDAO = CandidatoDetalleDAO.getInstance(conn);
			CandidatoDAO dao = new CandidatoDAO(conn);

			PerfilVO perfilVO = dao.buscarPerfilUsuario(idUsuario);
			
			if (null != perfilVO) {
				perfil = new PerfilBO();
				long idCandidato = perfil.getIdCandidato();
				BeanUtils.copyProperties(perfilVO, perfil);
				perfilLaboral = perfilLaboralFacade.find(perfil.getIdCandidato());
				
				if (null != perfilLaboral) {
					perfil.setPerfilLaboral(perfilLaboral);
					perfil.setSector(ofertaDetalleDAO.getCatalogoOpcion(Constantes.CATALOGO_OPCION_SUBSECTOR, perfilLaboral.getIdSectorMayorExpr()));
					perfil.setArea(ofertaDetalleDAO.getCatalogoOpcion(Constantes.CATALOGO_OPCION_AREA_LABORAL, perfilLaboral.getIdAreaLaboralMayorExpr()));
					perfil.setOcupacion(ofertaDetalleDAO.getCatalogoOpcion(Constantes.CATALOGO_OPCION_OCUPACION, perfilLaboral.getIdOcupacionMayorExpr()));
				}
				
				List<TelefonoVO> tels = telefonoFacade.getTelefonosPropietario(perfil.getIdCandidato(), Constantes.TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
				perfil.setSecundarios(tels);
				
				Iterator<TelefonoVO> it = tels.iterator();
				while (it.hasNext()) {
					TelefonoVO principal = it.next();
					if (principal.getPrincipal() == Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion()) {
						perfil.setPrincipal(principal);
					}
				}
				
				perfil.setEntidadNacimiento(ofertaDetalleDAO.getCatalogoOpcion(Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA, perfil.getIdEntidadNacimiento()));
				perfil.setUrlVideo(candidatoDetalleDAO.getUrlVideoc(perfil.getIdCandidato()));
				
				gradosAcademicos = dao.buscarGrados(perfil.getIdCandidato(), Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion());
				if (gradosAcademicos != null && !gradosAcademicos.isEmpty()) {
					perfil.setGradoPrincipal(gradosAcademicos.get(0));
				}
					
				gradosAcademicos = dao.buscarGrados(perfil.getIdCandidato(), Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion());
				if (gradosAcademicos !=  null && !gradosAcademicos.isEmpty()) {
					perfil.setGradosAcademicos(gradosAcademicos);
				}
				
				List<mx.gob.stps.portal.persistencia.vo.ConocimientoHabilidadVO> knwList = candidatoConocimHabilidadFacadeLocal.candidatoConocimHabilidadList(perfil.getIdCandidato());
				if (null != knwList && !knwList.isEmpty()) {
					for (mx.gob.stps.portal.persistencia.vo.ConocimientoHabilidadVO knw : knwList) {
						if (knw.getIdTipoConocimHabilidad() == CONOC_HAB.CONOCIMIENTO.getIdOpcion())
							if (knw.getPrincipal() == MULTIREGISTRO.PRINCIPAL.getIdOpcion()) perfil.setConocimientoPrincipal(knw);
					}
					perfil.setConocimientos(knwList);
				}
					
				idiomas = dao.buscarIdiomas(perfil.getIdCandidato(), Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion());
				if (idiomas != null && !idiomas.isEmpty()) {
					perfil.setIdiomaPrincipal(idiomas.get(0));
				}
				idiomas = dao.buscarIdiomas(perfil.getIdCandidato(), Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion());
				if (idiomas != null) {
					perfil.setIdiomas(idiomas);
				} else {
					perfil.setIdiomas(new ArrayList<IdiomaVO>());
				}

                conocimientoComputacionVO = findConocimientosComputacion(idCandidato);
                perfil.setConocimientoComputacionVO(conocimientoComputacionVO);

				histLaboral =  dao.buscarEmpleoActual(perfil.getIdCandidato(), (int)TRABAJA_ACTUALMENTE.SI.getIdOpcion());
				if (histLaboral != null && !histLaboral.isEmpty()) {
					perfil.setTrabajoActual(histLaboral.get(0));
				}
					
				expectativas = dao.buscarExpecLaboral(perfil.getIdCandidato(), Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion());
				if (expectativas != null && !expectativas.isEmpty()) {
					perfil.setExpectativaPrincipal(expectativas.get(0));
				}
				expectativas = dao.buscarExpecLaboral(perfil.getIdCandidato(), Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion());
				if (expectativas != null && !expectativas.isEmpty()) {
					perfil.setExpectativaLaboralList(expectativas);
				}
					
				List<String> medios = dao.mediosBusqueda(perfil.getIdCandidato());
				perfil.setMediosBusqueda(medios);
				
				if (perfil.getTrabaja() == null || perfil.getTrabaja().isEmpty()) {
					perfil.setTrabaja(new ArrayList<CatalogoOpcionVO>());
					CatalogoOpcionVO opcionVO = new CatalogoOpcionVO();
					opcionVO.setIdCatalogoOpcion(TRABAJA_ACTUALMENTE.SI.getIdOpcion());
					opcionVO.setOpcion(TRABAJA_ACTUALMENTE.SI.getOpcion());
					perfil.getTrabaja().add(opcionVO);
					opcionVO = new CatalogoOpcionVO();
					opcionVO.setIdCatalogoOpcion(TRABAJA_ACTUALMENTE.NO.getIdOpcion());
					opcionVO.setOpcion(TRABAJA_ACTUALMENTE.NO.getOpcion());
					perfil.getTrabaja().add(opcionVO);
				}
				
				domicilioVO = domicilioFacade.buscarDomicilioIdPropietario(perfil.getIdCandidato(), Constantes.TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
				if (domicilioVO != null) {				
					perfil.setCalle(domicilioVO.getCalle());
					perfil.setNumeroInterior(domicilioVO.getNumeroInterior());
					perfil.setNumeroExterior(domicilioVO.getNumeroExterior());
					perfil.setCodigoPostal(domicilioVO.getCodigoPostal());
					perfil.setEntreCalle(domicilioVO.getEntreCalle());
					perfil.setyCalle(domicilioVO.getyCalle());
					
					try {
						CodigoPostalVO codigoPostalVO = domicilioFacade.consultaCodigoPostal(domicilioVO.getIdColonia());
						if (codigoPostalVO!=null){
							String colonia = codigoPostalVO.getColoniaDescripcion();
							perfil.setColonia(colonia);
						}
						
						String entidad = ofertaDetalleDAO.getCatalogoOpcion(Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA, domicilioVO.getIdEntidad()); 
						if (entidad != null) {
							perfil.setEntidad(entidad);
						} else {
							perfil.setEntidad("");
						}
						
						MunicipioVO municipioVO = domicilioFacade.consultaMunicipio(domicilioVO.getIdMunicipio(), domicilioVO.getIdEntidad());
						if (municipioVO != null) {
							String municipio = municipioVO.getMunicipio();
							perfil.setMunicipio(municipio);
						}else{
							perfil.setMunicipio("");
						}
					} catch (SQLException sql) { 
						sql.printStackTrace(); 
					}
				}
				
				List<Long> habilidad = candidatoFacade.consultaHabilidades(perfil.getIdCandidato());
				List<CatalogoOpcionVO> habilidadesCandidato = new ArrayList<CatalogoOpcionVO>();
				for (Long idCatalogoOpcion : habilidad) {
					CatalogoOpcionVO vo = new CatalogoOpcionVO();
					vo = catalogoOpcionFacade.findById(CATALOGO_OPCION_CANDIDATO_HABILIDAD, idCatalogoOpcion);
					if (vo != null) {
						habilidadesCandidato.add(vo);
					}
				}
				perfil.setHabilidadesCandidato(habilidadesCandidato);				
			}

		} catch (SQLException sql) { 
			logger.error(sql); sql.printStackTrace(); 
		} catch (Exception sql) { 
			logger.error(sql);
		} finally {
			  try{
				  if (conn!=null) {
					  conn.close();
				  }
			  } catch(Exception e) {
				  e.printStackTrace();
			  }
			  
			  if (context!=null) {
				  try {
					  context.close();
				  } catch (Exception e) {
					e.printStackTrace();
				  }
			  }
		  }
		
		return perfil;
	}

	@Override
	public void actualizarRegistroCandidato(CandidatoVo candidatoVO) {
		candidatoFacade.actualizaRegistroCandidato(candidatoVO);
		
	}

	public int actualizaRegistroPPCSinValidacion(long idCandidato, Date ppcFechaInscripcion, int ppcAceptacionTerminos, Integer ppcEstatusIMSS, Date ppcFechaBajaIMSS, String ppcTipoContratoIMSS, String nss) throws IllegalArgumentException, BusinessException{
	
		Integer ppcEstatus = (Catalogos.ACEPTACION_TERMINOS.SI.getIdOpcion() == ppcAceptacionTerminos ?  Integer.valueOf(mx.gob.stps.portal.utils.Catalogos.ESTATUS.ACTIVO_PPC.getIdOpcion()) : Integer.valueOf(mx.gob.stps.portal.utils.Catalogos.ESTATUS.NO_INSCRITO_PPC.getIdOpcion()));		
		
		try{
			candidatoFacade.actualizaRegistroPPC(idCandidato, ppcEstatus, ppcFechaInscripcion, ppcAceptacionTerminos, ppcEstatusIMSS, ppcFechaBajaIMSS, ppcTipoContratoIMSS, nss);			
			return ppcEstatus.intValue();
			
		} catch (PersistenceException e){
			throw new BusinessException(e.getMessage());
			
		} catch (Exception e){
			e.printStackTrace();
			logger.error("Ha ocurrido un error en CandidatoAppService.actualizaRegistroPPC");
			throw new BusinessException(e);
		}
	}

	public int actualizaRegistroPPC(long idCandidato, Date ppcFechaInscripcion, int ppcAceptacionTerminos, Integer ppcEstatusIMSS, Date ppcFechaBajaIMSS, String ppcTipoContratoIMSS, String nss) throws IllegalArgumentException, BusinessException{

		if (idCandidato <= 0L)
			throw new IllegalArgumentException("El parámetro idCandidato es requerido");
		
		if (ACEPTACION_TERMINOS.SI.getIdOpcion() != ppcAceptacionTerminos && ACEPTACION_TERMINOS.NO.getIdOpcion() != ppcAceptacionTerminos) 
			throw new IllegalArgumentException("El parámetro ppcEstatus no es válido");
		
		if (nss == null || nss.trim().isEmpty())
			throw new IllegalArgumentException("El parámetro nss es requerido");
		
		if (nss.length() != 11)
			throw new IllegalArgumentException("El parámetro no es válido");

		Integer ppcEstatus = (Catalogos.ACEPTACION_TERMINOS.SI.getIdOpcion() == ppcAceptacionTerminos ?  Integer.valueOf(mx.gob.stps.portal.utils.Catalogos.ESTATUS.ACTIVO_PPC.getIdOpcion()) : Integer.valueOf(mx.gob.stps.portal.utils.Catalogos.ESTATUS.NO_INSCRITO_PPC.getIdOpcion()));		
		
		try{
			candidatoFacade.actualizaRegistroPPC(idCandidato, ppcEstatus, ppcFechaInscripcion, ppcAceptacionTerminos, ppcEstatusIMSS, ppcFechaBajaIMSS, ppcTipoContratoIMSS, nss);			
			return ppcEstatus.intValue();
			
		} catch (PersistenceException e){
			throw new BusinessException(e.getMessage());
			
		} catch (Exception e){
			e.printStackTrace();
			logger.error("Ha ocurrido un error en CandidatoAppService.actualizaRegistroPPC");
			throw new BusinessException(e);
		}
	}

	/**
	 * Seguimiento a postulación de candidato
	 * @author Sergio Téllez
	 * @since 05/12/2014
	 * @param idCandidato, seguimientoPostulacion
	 * @throws BusinessException
	 * @return int 
	 **/
	@Override
	public int registrarSeguimientoPostulacion(long idUsuario, OfertaCandidatoVO oc, String estatus) throws BusinessException {
		int result = 1;
		try {
			ofertaFacade.update(oc);
		} catch (Exception e) { result = 0; }
		if (result > 0) {
			if (oc.getConseguioEntrevista() == PRINCIPAL.SI.getIdOpcion() && null != oc.getFechaEntrevista()) {
				CandidatoVo user = candidatoFacade.consultaCandidato(idUsuario);
				OfertaEmpleoVO offer = ofertaEmpleoFacadeLocal.findById(oc.getIdOfertaEmpleo());
				try {
					StringBuilder name = new StringBuilder(user.getNombre());
					name.append(" ").append(user.getApellido1()).append(null != user.getApellido2() ? " " + user.getApellido2() : "");
					recordatorioResultadoEntrevista(name.toString(), user.getCorreoElectronico(), 
							Utils.getFechaFormato(oc.getFechaEntrevista()), offer.getTituloOferta());
				} catch (Exception e) {
					logger.error("Error registrarSeguimientoPostulacion " + e.getMessage());
					e.printStackTrace();
				}
			}
			bitacoraFacade.save(EVENTO.REGISTRO_DE_POSTULACION, idUsuario, "Seguimiento postulación", estatus, idUsuario, TIPO_PROPIETARIO.CANDIDATO);
		}
		return result;
	}

	/**
	 * Resultado entrevista postulación de candidato
	 * @author Sergio Téllez
	 * @since 05/12/2014
	 * @param idOfertaCandidato, resultadoEntrevista
	 * @throws ServiceLocatorException, BusinessException
	 * @return int 
	 **/
	@Override
	public int resultadoEntrevistaPostulacion(long idUsuario, OfertaCandidatoVO oc, String estatus) throws BusinessException {
		int result = 1;
		try {
			ofertaFacade.update(oc);
		} catch (Exception e) { result = 0; }
		if (result > 0) 
			bitacoraFacade.save(EVENTO.REGISTRO_DE_POSTULACION, idUsuario, "Seguimiento postulación", estatus, idUsuario, TIPO_PROPIETARIO.CANDIDATO);
		return result;
	}
	
	public RESPUESTA_IMMS_CONSULTA_NSS consultaNssIMMS(String curp, String nss) throws IllegalArgumentException, TechnicalException{
		
		if (curp == null || curp.trim().isEmpty())
			throw new IllegalArgumentException("El parámetro curp es requerido");
		
		if (nss == null || nss.trim().isEmpty())
			throw new IllegalArgumentException("El parámetro nss es requerido");
		
		//srojas: simulación de respuesta
		String lastDigit = nss.substring(nss.length()-1); 
		if (lastDigit.equals("0")){
			return RESPUESTA_IMMS_CONSULTA_NSS.SERVICIO_NO_DISPONIBLE;
			
		} else if (lastDigit.equals("1") || lastDigit.equals("2") || lastDigit.equals("3") || lastDigit.equals("4") || lastDigit.equals("5")){
			return RESPUESTA_IMMS_CONSULTA_NSS.NSS_REGISTRADO;
			
		} else {
			return RESPUESTA_IMMS_CONSULTA_NSS.NSS_NO_REGISTRADO; 			
		}
		//srojas		
		
	}

	@Override
	public int actualizaEstatusPPC(long idCandidato, int ppcEstatus, Integer ppcIdMotivoFuera) throws BusinessException {
		int result = 0;
		result = candidatoFacade.actualizaEstatusPPC(idCandidato, ppcEstatus, ppcIdMotivoFuera);
		if (result > 0 && ppcEstatus == mx.gob.stps.portal.utils.Catalogos.ESTATUS.ACTIVO_PPC.getIdOpcion())
			bitacoraFacade.save(EVENTO.ACTIVO_PPC, idCandidato, "Seguimiento postulación", mx.gob.stps.portal.utils.Catalogos.ESTATUS.ACTIVO_PPC.getOpcion(), idCandidato, TIPO_PROPIETARIO.CANDIDATO);
		return result;
	}

	@Override
	public int setContratadoPPC(long idCandidato, int ppcEstatus, Integer ppcIdMotivoFuera, String fechaColocacion, String medioColocacion, String nombreEmpresa, String tituloOferta) throws BusinessException {
		CandidatoVo user = candidatoFacade.find(idCandidato);
		int result =  candidatoFacade.actualizaEstatusPPC(idCandidato, ppcEstatus, ppcIdMotivoFuera);
		if (result > 0) {
			if (null != ppcIdMotivoFuera && ppcIdMotivoFuera.intValue() == MOTIVOS_FUERA_PPC.CANDIDATO_COLOCADO_EN_OFERTA_SNE.getIdOpcion()) {
				try {
					enviaCorreoEstatusFueraPPC(user.getNombre() + " " + user.getApellido1() + " " + user.getApellido2(), 
							user.getCorreoElectronico(), fechaColocacion, medioColocacion);
					bitacoraFacade.save(EVENTO.FUERA_PPC, idCandidato, "Seguimiento postulación", mx.gob.stps.portal.utils.Catalogos.ESTATUS.FUERA_PPC.getOpcion(), idCandidato, TIPO_PROPIETARIO.CANDIDATO);
				} catch (Exception e) {
					logger.error(e);
					e.printStackTrace();
				}
			}else {
				NotificacionService notificacionService = new NotificacionService();
				try {
					notificacionService.notificarCandidato(new Date(), user.getCorreoElectronico(), nombreEmpresa, tituloOferta, new Boolean(true), TIPO_PERSONA.PERSONA_MORAL.getTipoPersona());
				} catch (MailException e) {
					logger.error(e);
					e.printStackTrace();
				}
			}
		}
		return result;
	}	
	
	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<CandidatoVo> postulatesByEmpresaList(long idEmpresa) throws SQLException {
		List<CandidatoVo> postulates = new ArrayList<CandidatoVo>();
		List<OfertaEmpleoVO> ofertas = ofertaEmpleoFacadeLocal.consultaOfertasEmpleo(idEmpresa, ESTATUS.ACTIVO.getIdOpcion());
		for (OfertaEmpleoVO oferta : ofertas) {
			List<CandidatoVo> postulatesPartial = obtenerPostulados(idEmpresa, oferta.getIdOfertaEmpleo());
			postulates.addAll(postulatesPartial);
		}
		return postulates;
	}
	
	public void enviaCorreoEstatusFueraPPC(String nombreCandidato, String email, String fechaColocacion, String medioColocacion)  throws IllegalArgumentException, TechnicalException{
		if (null == email || email.isEmpty())
			throw new IllegalArgumentException("El parámetro email es requerido");
		if (fechaColocacion == null || fechaColocacion.isEmpty())
			throw new IllegalArgumentException("El parámetro fechaColocacion es requerido");
		if (medioColocacion == null || medioColocacion.isEmpty())
			throw new IllegalArgumentException("El parámetro medioColocacion es requerido");
		String remitente = properties.getProperty("email.remitente.ppc");
		String asunto = "Notificacion Desactivacion a Seguro de desempleo"; 
		List<FieldVO> fields = new ArrayList<FieldVO>();
		fields.add(FieldVO.getInstance("nombreCandidato", nombreCandidato));
		fields.add(FieldVO.getInstance("fechaColocacion", fechaColocacion));
		fields.add(FieldVO.getInstance("medioColocacion", medioColocacion));
		try {
			NotificacionService notificacionService = new NotificacionService();
			notificacionService.recordatorioRegistroEntrevista(PLANTILLA_CORREO.NOTIFICACION_CANDIDATO_FUERA_PPC, remitente, asunto, email, fields);
		} catch (Exception e) {
			logger.error("Ha ocurrido un error en enviaCorreoEstatusFueraPPC");
			e.printStackTrace();
			throw new TechnicalException(e); 
		}
	}
	
	public void recordatorioResultadoEntrevista(String nombreCandidato, String email, String fechaEntrevista, String tituloOferta)  throws IllegalArgumentException, TechnicalException{
		if (null == email || email.isEmpty())
			throw new IllegalArgumentException("El parámetro email es requerido");
		if (fechaEntrevista == null || fechaEntrevista.isEmpty())
			throw new IllegalArgumentException("El parámetro fechaEntrevista es requerido");
		if (nombreCandidato == null || nombreCandidato.isEmpty())
			throw new IllegalArgumentException("El parámetro nombreCandidato es requerido");
		String remitente = properties.getProperty("email.remitente.ppc");
		String asunto = properties.getProperty("email.candidato.notificacion.resultado.entrevista"); 
		List<FieldVO> fields = new ArrayList<FieldVO>();
		fields.add(FieldVO.getInstance("nombreCandidato", nombreCandidato));
		fields.add(FieldVO.getInstance("fechaEntrevista", fechaEntrevista));
		fields.add(FieldVO.getInstance("tituloOferta", tituloOferta));
		try {
			NotificacionService notificacionService = new NotificacionService();
			notificacionService.recordatorioRegistroEntrevista(PLANTILLA_CORREO.NOTIFICACION_RECORDATORIO_ENTREVISTA, remitente, asunto, email, fields);
		} catch (Exception e) {
			logger.error("Ha ocurrido un error en recordatorioResultadoEntrevista", e);
			e.printStackTrace();
			throw new TechnicalException(e); 
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)	
	public void enviaNotificacionInscripcionPPC(long idCandidato) throws IllegalArgumentException, MailException, TechnicalException{

		if (idCandidato <= 0L)
			throw new IllegalArgumentException("El parámetro idCandidato es requerido");

		try{
			Solicitante solicitante = solicitanteFacade.findByIdCandidato(idCandidato);
			CandidatoVo candidato = candidatoFacade.find(idCandidato);

			DomicilioVO domicilio = domicilioFacade.buscarDomicilioIdPropietario(candidato.getIdCandidato(), (long)Catalogos.TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());			

			if (domicilio != null){

				// descripción de la entidad
				domicilio.setEntidad(Catalogos.ENTIDADES_FEDERATIVAS.getDescripcion((int)domicilio.getIdEntidad()));

				// descripción del municipio
				MunicipioVO municipio = domicilioFacade.consultaMunicipio(domicilio.getIdDomicilio(), domicilio.getIdEntidad());
				if (municipio != null)
					domicilio.setMunicipio(municipio.getMunicipio());
			}

			StringBuffer nombreCompleto = new StringBuffer();
			nombreCompleto.append(solicitante.getNombre()+" ");
			nombreCompleto.append(solicitante.getApellido1());		
			if (solicitante.getApellido1() != null && !solicitante.getApellido1().isEmpty())
				nombreCompleto.append(" "+solicitante.getApellido2());

			NotificacionService notificacionService = new NotificacionService();

			notificacionService.notificacionInscripcionPPCCandidato(
					nombreCompleto.toString(),
					solicitante.getCorreoElectronico(), 
					solicitante.getCurp(),
					candidato.getNss(),
					candidato.getPpcFechaInscripcion(),
					domicilio.getEntidad(),
					domicilio.getMunicipio());

		} catch (IllegalArgumentException e){
			throw e;

		} catch (MailException e){
			throw e;

		} catch (Exception e){
			logger.error("Ha ocurrido un error en enviaNotificacionInscripcionPPC, idCandidato: "+idCandidato);
			e.printStackTrace();
			throw new TechnicalException(e);
		}
	}
	
	@Override
	public boolean isEffectiveLimitCandidate(long idCandidato) throws BusinessException {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -11);
		Date dateLimit = cal.getTime();
		CandidatoVo user = candidatoFacade.find(idCandidato);
		if (null != user && null != user.getFechaUltimaActualizacion() && (user.getFechaUltimaActualizacion().after(dateLimit) || user.getFechaUltimaActualizacion().equals(dateLimit)))
			return true;
		else return false;
	}
	
	@Override
	public int upgradeEffective(long idCandidato) throws BusinessException {
		int result = 0;
		try {
			candidatoFacade.actualizaEstatusCandidato(idCandidato, ESTATUS.ACTIVO.getIdOpcion());
		}catch (Exception e) { result = 1; }
		return result;
	}
	
	@Override
	public int updateCandidateComplementData(ModalidadCandidatoVO mc, FormatoPTATVO ptat, List<BeneficiarioVO> contacts, List<BeneficiarioVO> beneficiaries, List<IdiomaVO> langs, List<FormatoPTATMaquinariaVO> engines, List<FormatoPTATCultivosVO> products, List<FormatoPTATHclinicaVO> histories) throws BusinessException {
		return modalidadCandidatoFacadeLocal.updateCandidateComplementData(mc, ptat, contacts, beneficiaries, langs, engines, products, histories);
	}
	
	@Override
	public ModalidadCandidatoVO getModalidadCandidato(long idCandidato, long idModalidad, long idSubprograma) throws BusinessException {
		return modalidadCandidatoFacadeLocal.getModalidadCandidato(idCandidato, idModalidad, idSubprograma);
	}
	
	@Override
	public FormatoPTATVO getFormatoPTATVO(long idCandidato) throws BusinessException {
		return modalidadCandidatoFacadeLocal.getFormatoPTATVO(idCandidato);
	}
	
	@Override
	public List<FormatoPTATCultivosVO> getFormatoPTATCultivos(long idCandidato) throws BusinessException {
		return modalidadCandidatoFacadeLocal.getFormatoPTATCultivos(idCandidato);
	}
	
	@Override
	public List<FormatoPTATHclinicaVO> getDiseases(long idCandidato) throws BusinessException {
		return modalidadCandidatoFacadeLocal.getDiseases(idCandidato);
	}
	
	@Override
	public List<ModalidadPtatBeneficiarioVO> getModalidadPtatBeneficiarioList(long idModalidadCandidatoPTAT) throws BusinessException {
		return modalidadCandidatoFacadeLocal.getModalidadPtatBeneficiarioList(idModalidadCandidatoPTAT);
	}
	@Override
	public boolean consultarPermisoGeolocalizacionRegistro()
			throws BusinessException {
		ParametroVO resultado = parametroFacadeLocal.findById(PARAMETRO.GEOLOCALIZACION_REGISTRO_CANDIDATO.getIdParametro());
		if(resultado!=null){
			return resultado.getValor().equals("1");
		}
		return false;
	}
	
	@Override
	public boolean consultarPermisoGeolocalizacion(long idCandidato) throws BusinessException {
		return candidatoFacade.consultarPermisoGeolocalizacion(idCandidato);
	}

	@Override
	public void actualizaEstatusGeoreferencia(long idCandidato, boolean estatus) throws BusinessException {
		System.out.println("");
		//candidatoFacade.actualizaEstatusGeoreferencia(idCandidato, estatus);
	}
	
	@Override
	public FormatoSNE01VO findSNEByCandidate(long idCandidato) throws BusinessException {
		return modalidadCandidatoFacadeLocal.getFormatoSNE01(idCandidato);
	}
	
	@Override
	public FormatoLPAVO findLPAByCandidate(long idCandidato) throws BusinessException {
		return modalidadCandidatoFacadeLocal.getFormatoLPA(idCandidato);
	}
	
	@Override
	public List<BeneficiarioVO> getBeneficiarioList(long idCandidato, long idTipoFormato, boolean isContact) throws BusinessException {
		return modalidadCandidatoFacadeLocal.getBeneficiarioList(idCandidato, idTipoFormato, isContact);
	}
	
	@Override
	public int updatePerfilComp(PerfilVO perfil, ExpectativaLaboralVO el, HistoriaLaboralVO hl, FormatoSNE01VO sne, ModalidadCandidatoVO mc, List<BeneficiarioVO> beneficiarioList) throws BusinessException {
		int result = 0;
		result = modalidadCandidatoFacadeLocal.updatePerfilComp(perfil, el, hl, sne, mc);
		if (result > 0) modalidadCandidatoFacadeLocal.updateBeneficiarioList(beneficiarioList);
		bitacoraFacade.save(EVENTO.ACTUALIZA_DATOS_PERSONALES.getIdEvento(), perfil.getIdUsuario(), EVENTO.ACTUALIZA_DATOS_PERSONALES.getEvento(),
				Calendar.getInstance(), perfil.toBitacora(), perfil.getIdCandidato(), TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
		return result;
	}
	
	@Override
	public int createBeneficiario(BeneficiarioVO beneficiario) throws BusinessException {
		List<BeneficiarioVO> beneficiarioList = new ArrayList<BeneficiarioVO>();
		beneficiarioList.add(beneficiario);
		return modalidadCandidatoFacadeLocal.updateBeneficiarioList(beneficiarioList);
	}
	
	@Override
	public int removeBeneficiario(long idBeneficiario) throws BusinessException {
		return modalidadCandidatoFacadeLocal.removeBeneficiario(idBeneficiario);
	}
	
	@Override
	public long persistLang(IdiomaVO idiomaVO) throws BusinessException {
		return this.candidatoIdiomaFacadeLocal.persist(idiomaVO);
	}

	@Override
	public int updateLPA(long idCandidato, FormatoLPAVO mml, ReferenciaLaboralVO referencia, GradoAcademicoVO gradoAcademicoVO) throws BusinessException {
		return modalidadCandidatoFacadeLocal.updateLPA(idCandidato, mml, referencia, gradoAcademicoVO);
	}
	
	@Override
	public List<ReferenciaLaboralVO> getReferenciaLaboraList(long idCandidato) throws BusinessException {
		return modalidadCandidatoFacadeLocal.getReferenciaLaboraList(idCandidato);
	}
	
	@Override
	public int removeReferenciaLaboral(ReferenciaLaboralVO referencia) throws BusinessException {
		return modalidadCandidatoFacadeLocal.removeReferenciaLaboral(referencia);
	}
	
	@Override
	public List<FormatoPTATMaquinariaVO> getFormatoPTATMaquinaria(long idCandidato) throws BusinessException {
		return modalidadCandidatoFacadeLocal.getFormatoPTATMaquinaria(idCandidato);
	}
	
	@Override
	public long update(mx.gob.stps.portal.persistencia.vo.ConocimientoHabilidadVO conocHabVO) throws BusinessException {
		return candidatoConocimHabilidadFacadeLocal.update(conocHabVO);
	}
	
	@Override
	public List<CanalizacionCandidatoVO> getCanalizacionCandidatoList(long idCandidato) throws BusinessException {
		return modalidadCandidatoFacadeLocal.getCanalizacionCandidatoList(idCandidato);
	}
	
	@Override
	public List<Long> getNominalList(long idOferta) throws BusinessException {
		return modalidadCandidatoFacadeLocal.getNominalList(idOferta);
	}

	// Notificacion Recuperacion Contraseña OAM
	public void notificacionRecuperacionContrasena(long idPropietario, String usuario, TIPO_PROPIETARIO tipoPropietario,
			String nombrePropietario, String correoElectronico, String url) throws MailException {
		NotificacionService notificacionService = new NotificacionService();
		notificacionService.notificacionRecuperacionContrasena(idPropietario, usuario, tipoPropietario,
				nombrePropietario, correoElectronico, url);
	}
}