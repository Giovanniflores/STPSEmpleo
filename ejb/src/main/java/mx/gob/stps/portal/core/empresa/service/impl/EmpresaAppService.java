package mx.gob.stps.portal.core.empresa.service.impl;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_TIPO_ACTIVIDAD_ECONOMICA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.ID_PARAMETRO_ID_EMPRESA_OFERTAS_CANADA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.ID_REGISTRO_PORTAL;
import static mx.gob.stps.portal.core.infra.utils.Constantes.ID_USUARIO_ANONIMO;
import static mx.gob.stps.portal.ws.renapo.impl.ParserXmlRenapo.NO_EXITOSO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.autorizacion.service.AutorizacionAppServiceLocal;
import mx.gob.stps.portal.core.autorizacion.vo.ParametroVO;
import mx.gob.stps.portal.core.candidate.dao.CandidatoDAO;
import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.domicilio.service.DomicilioAppServiceLocal;
import mx.gob.stps.portal.core.domicilio.vo.CodigoPostalVO;
import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.domicilio.vo.MunicipioVO;
import mx.gob.stps.portal.core.empresa.dao.EmpresaDAO;
import mx.gob.stps.portal.core.empresa.service.EmpresaAppServiceLocal;
import mx.gob.stps.portal.core.empresa.service.EmpresaAppServiceRemote;
import mx.gob.stps.portal.core.empresa.vo.EmpresaPorAutorizarVO;
import mx.gob.stps.portal.core.empresa.vo.EmpresaUsuarioVO;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.empresa.vo.RegistroEmpresaVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.EncodingException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.mail.service.NotificacionService;
import mx.gob.stps.portal.core.infra.service.TelefonoAppServiceLocal;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.CONFIDENCIALIDAD;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.EVENTO;
import mx.gob.stps.portal.core.infra.utils.Constantes.MULTIREGISTRO;
import mx.gob.stps.portal.core.infra.utils.Constantes.PERFIL;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_EMPRESA;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PERSONA;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_REGISTRO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_TELEFONO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_USUARIO;
import mx.gob.stps.portal.core.infra.utils.CurpServiceLocator;
import mx.gob.stps.portal.core.infra.utils.MessageLoader;
import mx.gob.stps.portal.core.infra.utils.Password;
import mx.gob.stps.portal.core.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.infra.vo.BitacoraVO;
import mx.gob.stps.portal.core.oferta.busqueda.dao.OfertasPorEmpresaDAO;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorPerfilVO;
import mx.gob.stps.portal.core.oferta.detalle.service.OfertaCandidatoAppServiceLocal;
import mx.gob.stps.portal.core.oferta.registro.vo.EventoVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.core.persistencia.facade.BitacoraFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.CandidatoFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.CatalogoOpcionFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.DomicilioFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.EmpresaCapMixtaFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.EmpresaFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.EmpresaFuncionPublicaFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.EmpresaPorAutorizarFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.EntrevistaFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.OfertaEmpleoFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.ParametroFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.RegistroPorValidarFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.TelefonoFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.UsuarioFacadeLocal;
import mx.gob.stps.portal.core.seguridad.exception.CorreoRepetidoException;
import mx.gob.stps.portal.core.seguridad.vo.UsuarioVO;
import mx.gob.stps.portal.persistencia.entity.Domicilio;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.utils.Catalogos.PARAMETRO;
import mx.gob.stps.portal.utils.Catalogos.WS_CURP_ERROR;
import mx.gob.stps.portal.utils.Catalogos.WS_CURP_OPERACION;
import mx.gob.stps.portal.utils.Catalogos.WS_CURP_TIPO_ERROR;
import mx.gob.stps.portal.ws.renapo.exception.ConsultaWsCurpException;
import mx.gob.stps.portal.ws.renapo.impl.CURPServiceImpl;
import mx.gob.stps.portal.ws.renapo.vo.CurpVO;

import org.apache.log4j.Logger;

//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;

/**
 * Session Bean implementation class EmpresaAppService
 */
@Stateless(name = "EmpresaAppService", mappedName = "EmpresaAppService") 
public class EmpresaAppService implements EmpresaAppServiceRemote, EmpresaAppServiceLocal  {

	private static Logger logger = Logger.getLogger(EmpresaAppService.class);
	
	private static final int  LIMITE_NUMERO_EMPLEADOS_MICRO_EMPRESA = 10;
	private static final int  LIMITE_NUMERO_EMPLEADOS_PEQUENA_EMPRESA_COMERCIO = 30;
	private static final int  LIMITE_NUMERO_EMPLEADOS_PEQUENA_EMPRESA_NO_COMERCIO = 50;
	private static final int  LIMITE_NUMERO_EMPLEADOS_MEDIANA_EMPRESA_COMERCIO_O_SERVICIO = 100;
	private static final int  LIMITE_NUMERO_EMPLEADOS_MEDIANA_EMPRESA_INDUSTRIA = 250;
	private static final long [] sectoresComercio = {43, 46};
	private static final long [] sectoresServicio = {51, 52, 53,54,55,56,61,62,71,72,81,93};
	private static final int  NUMERO_MINIMO_DE_CARACTERES_DE_SECTOR = 2;
	
	@EJB private EmpresaPorAutorizarFacadeLocal empresaPorAutorizarFacade;
	@EJB private EmpresaFacadeLocal empresaFacade;
	@EJB private CandidatoFacadeLocal candidatoFacade;
	@EJB private TelefonoAppServiceLocal telefonoAppService;	
	@EJB private AutorizacionAppServiceLocal autorizacionAppService;
	@EJB private DomicilioAppServiceLocal domicilioAppService;	
	@EJB private BitacoraFacadeLocal bitacoraFacade;
	@EJB private UsuarioFacadeLocal usuarioFacade;
	@EJB private CatalogoOpcionFacadeLocal catalogoOpcionFacade;
	@EJB private DomicilioFacadeLocal domicilioFacade;
	@EJB private OfertaEmpleoFacadeLocal ofertaEmpleoFacade;
	@EJB private RegistroPorValidarFacadeLocal registroPorValidarFacade;
	@EJB private EmpresaCapMixtaFacadeLocal empresaCapMixtaFacadeLocal;
	@EJB private TelefonoFacadeLocal telefonoFacade;
	@EJB private ParametroFacadeLocal parametroFacade;
	@EJB private EntrevistaFacadeLocal entrevistaFacade;
	@EJB private OfertaCandidatoAppServiceLocal ofertaCandidatoAppService;
	@EJB private EmpresaFuncionPublicaFacadeLocal EmpresaFuncionPublicaService;
	
	public EmpresaAppService() {}
	
	/**
	 * Registra una empresa por autorizar
	 * @param vo
	 * @return long
	 */			
	public long registrarEmpresa(EmpresaPorAutorizarVO vo){
		long idEmpresa; 	
		String detalle = "";
		vo.setFechaAlta(new Date());
		vo.setFechaUltimaActualizacion(new Date());			
		//vo.setEstatus(ESTATUS.INACTIVO.getIdOpcion());
		vo.setEstatus(ESTATUS.REGISTRADA.getIdOpcion());
		DomicilioVO domicilio = vo.getDomicilio();
		List<TelefonoVO> lstTelefonos = vo.getTelefonos();				
		idEmpresa = empresaPorAutorizarFacade.save(vo);	
		detalle = "idEmpresa=" + idEmpresa + "|"  + "idPortalEmpleo=" + vo.getIdPortalEmpleo() + "|";
		//System.out.println("----EmpresaAppService.registrarEmpresa.idEmpresa:" + idEmpresa);				
		domicilio.setFechaAlta(new Date());
		domicilio.setIdTipoPropietario(TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
		domicilio.setIdPropietario(idEmpresa);		
		domicilioAppService.save(domicilio);
		detalle = detalle + "idDomicilio=" + domicilio.getIdDomicilio() + "|";
		//System.out.println("----domicilioAppService.save(domicilio):");		
		//logger.info("---EmpresaAppService.registrarEmpresa lstTelefonos.size() :" + lstTelefonos.size());
		Iterator<TelefonoVO> itTel = lstTelefonos.iterator();
		while(itTel.hasNext()){
			TelefonoVO telVo = (TelefonoVO )itTel.next();
			telVo.setFechaAlta(new Date());
			telVo.setIdPropietario(idEmpresa);
			telVo.setIdTipoPropietario(TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
			long idTelefono = telefonoAppService.registrarTelefono(telVo);
			//logger.info("---EmpresaAppService.registrarEmpresa idTelefono :" + idTelefono + " getTelefono:" + telVo.getTelefono());
			detalle = detalle + "idTelefono=" + idTelefono + "|";
		}		
		autorizacionAppService.registraEmpresaPorValidar(idEmpresa);		
		bitacoraFacade.save(EVENTO.REGISTRA_EMPRESA_POR_AUTORIZAR.getIdEvento(), 
				ID_USUARIO_ANONIMO, 
				EVENTO.REGISTRA_EMPRESA_POR_AUTORIZAR.getEvento(), Calendar.getInstance(), detalle,
				idEmpresa, TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
		return idEmpresa;
	}
	
	/**
	 * Registra una empresa por autorizar
	 * @param vo EmpresaPorAutorizarVO
	 * @param long idUsuario
	 * @return long
	 */		
	//XXX Este metodo es candidato a desaparecer
	public long registrarEmpresa(EmpresaPorAutorizarVO vo, long idUsuario){
		long idEmpresa; 	
		String detalle = "";
		vo.setFechaAlta(new Date());
		vo.setFechaUltimaActualizacion(new Date());
		//vo.setEstatus(ESTATUS.INACTIVO.getIdOpcion());
		vo.setEstatus(ESTATUS.REGISTRADA.getIdOpcion());
		DomicilioVO domicilio = vo.getDomicilio();
		List<TelefonoVO> lstTelefonos = vo.getTelefonos();				
		idEmpresa = empresaPorAutorizarFacade.save(vo);	
		detalle = "idEmpresa=" + idEmpresa + "|" + "idPortalEmpleo=" + vo.getIdPortalEmpleo() + "|";
		//System.out.println("----EmpresaAppService.registrarEmpresa.idEmpresa:" + idEmpresa);				
		domicilio.setFechaAlta(new Date());
		domicilio.setIdTipoPropietario(TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
		domicilio.setIdPropietario(idEmpresa);		
		domicilioAppService.save(domicilio);
		detalle = detalle + "idDomicilio=" + domicilio.getIdDomicilio() + "|";
		//System.out.println("----domicilioAppService.save(domicilio):");		
		//logger.info("---EmpresaAppService.registrarEmpresa lstTelefonos.size() :" + lstTelefonos.size());
		Iterator<TelefonoVO> itTel = lstTelefonos.iterator();
		while(itTel.hasNext()){
			TelefonoVO telVo = (TelefonoVO )itTel.next();
			telVo.setFechaAlta(new Date());
			telVo.setIdPropietario(idEmpresa);
			telVo.setIdTipoPropietario(TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
			long idTelefono = telefonoAppService.registrarTelefono(telVo);
			//logger.info("---EmpresaAppService.registrarEmpresa idTelefono :" + idTelefono + " getTelefono:" + telVo.getTelefono());
			detalle = detalle + "idTelefono=" + idTelefono + "|";
		}
		
		autorizacionAppService.registraEmpresaPorValidar(idEmpresa);		
		bitacoraFacade.save(EVENTO.REGISTRA_EMPRESA_POR_AUTORIZAR.getIdEvento(), idUsuario, 
				EVENTO.REGISTRA_EMPRESA_POR_AUTORIZAR.getEvento(), Calendar.getInstance(), detalle,
				idEmpresa, TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
		return idEmpresa;
	}	
	
	/**
	 * Actualiza los datos de una empresa por autorizar
	 * @param vo EmpresaVO
	 * @param long idUsuario
	 * @param long idTipoUsuario
	 * @param boolean isChangedEmail
	 * @param boolean isChangedBasicField
	 * @param String changedFields
	 * @return long
	 * @throws TechnicalException 
	 * @throws MailException 
	 * @throws BusinessException 
	 * @throws SQLException 
	 */		
	public long actualizarEmpresaPorAutorizar(EmpresaPorAutorizarVO vo, long idUsuario, long idTipoUsuario, 
			                                  boolean isChangedEmail, boolean isChangedBasicField, boolean isChangedIdPortal, String changedFields,
			                                  long idRegValidar) throws BusinessException, MailException, TechnicalException, SQLException{
		long idEvento = 0;
		String descripcionEvento = "";
		
		long idEmpresa = vo.getIdEmpresa();		
		//System.out.println("----EmpresaAppService.actualizarEmpresaPorAutorizar :" + idEmpresa);
		vo.setFechaUltimaActualizacion(new Date());
		//DOMICILIO
		DomicilioVO domicilio = vo.getDomicilio();		
		domicilioAppService.update(domicilio);
		//System.out.println("----domicilioAppService.update(domicilio):");
		//TELEFONOS
		List<TelefonoVO> lstTelefonos = vo.getTelefonos();
		//TELEFONO PRINCIPAL
		Iterator<TelefonoVO> itLstTelefonos = lstTelefonos.iterator();
		while(itLstTelefonos.hasNext()){
			TelefonoVO telVo = (TelefonoVO)itLstTelefonos.next();
			if(telVo.getPrincipal()==MULTIREGISTRO.PRINCIPAL.getIdOpcion()){
				telVo.setIdPropietario(idEmpresa);
				telVo.setIdTipoPropietario(TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());								
				itLstTelefonos.remove();
				break;
			}
		}
		//SOLO HAY TELEFONOS ADICIONALES EN lstTelefonos PORQUE PRINCIPAL SE ELIMINO		
		List<TelefonoVO> lstOldPhones = telefonoAppService.consultarTelefonos(idEmpresa, TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
		if(lstOldPhones.size()>0){
			//lstOldPhones tiene los telefonos anteriormente registrados (incluyendo el principal)
			lstOldPhones.removeAll(lstTelefonos);			
			//ahora lstOldPhones contiene solo los telefonos que el usuario elimino
			if(lstOldPhones.size()>0){
				Iterator<TelefonoVO> itDelTelefonos = lstOldPhones.iterator();
				while(itDelTelefonos.hasNext()){
					TelefonoVO telVo = (TelefonoVO)itDelTelefonos.next();
					telVo.setIdPropietario(idEmpresa);
					telVo.setIdTipoPropietario(TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());					
					if(telVo.getPrincipal()==MULTIREGISTRO.ADICIONAL.getIdOpcion()){
						telefonoAppService.eliminarTelefono(telVo);
					}				
				}							
			}
		}		
		//insertar nuevos 
		List<TelefonoVO> lstNewPhones = telefonoAppService.consultarTelefonos(idEmpresa, TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
		if(lstNewPhones.size()>0){
			lstTelefonos.removeAll(lstNewPhones);
			//ahora lstTelefonos contiene solo los nuevos telefonos que el usuario agrego
			if(lstNewPhones.size()>0){
				Iterator<TelefonoVO> itNewTelefonos = lstTelefonos.iterator();
				while(itNewTelefonos.hasNext()){
					TelefonoVO telVo = (TelefonoVO)itNewTelefonos.next();
					if(telVo.getPrincipal()==MULTIREGISTRO.ADICIONAL.getIdOpcion()){
						telVo.setFechaAlta(new Date());
						telVo.setIdPropietario(idEmpresa);
						telVo.setIdTipoPropietario(TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());	
						telefonoAppService.registrarTelefono(telVo);
					}				
				}			
			}			
		}
		idEvento = EVENTO.MODIFICA_EMPRESA_POR_AUTORIZAR.getIdEvento();
		descripcionEvento = EVENTO.MODIFICA_EMPRESA_POR_AUTORIZAR.getEvento();	
		
		if(idTipoUsuario==(long)TIPO_USUARIO.ADMINISTRADOR.getIdTipoUsuario()){
			
			if(isChangedEmail){
				if(vo.getEstatus()==ESTATUS.REGISTRADA.getIdOpcion()){
						idEvento = EVENTO.MODIFICA_EMPRESA_POR_AUTORIZAR.getIdEvento();
						descripcionEvento = EVENTO.MODIFICA_EMPRESA_POR_AUTORIZAR.getEvento();										
						
						/** Se establece como PREVALIDADA cuando el publicador realiza la modificacion y aceptacion **/
						vo.setEstatus(ESTATUS.PREVALIDADA.getIdOpcion());
						empresaPorAutorizarFacade.update(vo);

						//System.out.println("----EmpresaAppService.actualizarEmpresaREGISTRADA.idEmpresa:" + idEmpresa);	
						if(isChangedIdPortal){
							String strIdPortal = empresaPorAutorizarFacade.generaIDPortalEmpleo(vo);
							empresaPorAutorizarFacade.actualizaIDPortalEmpleo(vo.getIdEmpresa(), strIdPortal);
							//System.out.println("----EmpresaAppService.actualizaIDPortalEmpleo.strIdPortal:" + strIdPortal);	
						}												
						
						/** La autorizacion establece a la Empresa como PREVALIDADA **/
						//XXX autorizacionAppService.autorizaEmpresaPorAutorizar(idEmpresa, idUsuario, changedFields, idRegValidar);
				} else if(vo.getEstatus()==ESTATUS.PREVALIDADA.getIdOpcion()){
					
					/** Se establece como PREVALIDADA cuando el publicador realiza la modificacion y aceptacion **/
					vo.setEstatus(ESTATUS.PREVALIDADA.getIdOpcion());
					empresaPorAutorizarFacade.update(vo);		
					
					//System.out.println("----EmpresaAppService.actualizarEmpresaPREVALIDADA.idEmpresa:" + idEmpresa);	
					if(isChangedIdPortal){
						String strIdPortal = empresaPorAutorizarFacade.generaIDPortalEmpleo(vo);
						empresaPorAutorizarFacade.actualizaIDPortalEmpleo(vo.getIdEmpresa(), strIdPortal);
						//System.out.println("----EmpresaAppService.actualizaIDPortalEmpleo.strIdPortal:" + strIdPortal);
					}																	

					/** La autorizacion establece a la Empresa como PREVALIDADA **/
					//XXX autorizacionAppService.autorizaEmpresaPorAutorizar(idEmpresa, idUsuario, changedFields, idRegValidar);
					
				} else if(vo.getEstatus()==ESTATUS.INACTIVO.getIdOpcion()){

						/** Se establece como PREVALIDADA cuando el publicador realiza la modificacion y aceptacion **/
						vo.setEstatus(ESTATUS.PREVALIDADA.getIdOpcion());
						empresaPorAutorizarFacade.update(vo);
						
						//System.out.println("----EmpresaAppService.actualizarEmpresaINACTIVA.idEmpresa:" + idEmpresa);
						if(isChangedIdPortal){
							String strIdPortal = empresaPorAutorizarFacade.generaIDPortalEmpleo(vo);
							empresaPorAutorizarFacade.actualizaIDPortalEmpleo(vo.getIdEmpresa(), strIdPortal);
							//System.out.println("----EmpresaAppService.actualizaIDPortalEmpleo.strIdPortal:" + strIdPortal);
						}																			

						/** La autorizacion establece a la Empresa como PREVALIDADA **/
						//XXX autorizacionAppService.autorizaEmpresaPorAutorizar(idEmpresa, idUsuario, changedFields, idRegValidar);						
				}					
			} else {
				
				/** Se establece como PREVALIDADA cuando el publicador realiza la modificacion y aceptacion **/
				vo.setEstatus(ESTATUS.PREVALIDADA.getIdOpcion());
				empresaPorAutorizarFacade.update(vo);

				//System.out.println("----EmpresaAppService.actualizarEmpresa.idEmpresa:" + idEmpresa);
				if(isChangedIdPortal){
					String strIdPortal = empresaPorAutorizarFacade.generaIDPortalEmpleo(vo);
					empresaPorAutorizarFacade.actualizaIDPortalEmpleo(vo.getIdEmpresa(), strIdPortal);
					//System.out.println("----EmpresaAppService.actualizaIDPortalEmpleo.strIdPortal:" + strIdPortal);
				}					
				
				/** La autorizacion establece a la Empresa como PREVALIDADA **/
				//XXX autorizacionAppService.autorizaEmpresaPorAutorizar(idEmpresa, idUsuario, changedFields, idRegValidar);
			}
		}		
		bitacoraFacade.save(idEvento,idUsuario, descripcionEvento, Calendar.getInstance(), changedFields, idEmpresa, TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());		
		return idEmpresa;
	}	
	
	/**
	 * Actualiza los datos de una empresa
	 * @param vo EmpresaVO
	 * @param long idUsuario
	 * @param long idTipoUsuario
	 * @param boolean isChangedEmail
	 * @param boolean isChangedBasicField
	 * @param String changedFields
	 * @return long
	 * @throws TechnicalException 
	 * @throws MailException 
	 * @throws BusinessException 
	 * @throws SQLException 
	 */		
	public long actualizarEmpresa(EmpresaVO vo, long idUsuario, long idTipoUsuario, 
			                      boolean isChangedEmail, boolean isChangedBasicField, boolean isChangedIdPortal, String changedFields, long idRegValidar) 
			                      throws BusinessException, MailException, TechnicalException, SQLException{
		
		long idEvento = 0;
		String descripcionEvento = "";
		
		long idEmpresa = vo.getIdEmpresa();
		
		//System.out.println("----EmpresaAppService.actualizarEmpresa :" + idEmpresa);
		vo.setFechaUltimaActualizacion(new Date());
		//DOMICILIO
		DomicilioVO domicilio = vo.getDomicilio();
		domicilioAppService.update(domicilio);
		//System.out.println("----domicilioAppService.update(domicilio):");
		List<TelefonoVO> lstOldTelefonos = telefonoAppService.consultarTelefonos(idEmpresa, Constantes.TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
		//EN TODOS LOS CASOS DEBE HABER TELEFONO PRINCIPAL, OBTENER DATOS TELEFONO PRINCIPAL NUEVO
		TelefonoVO telefonoPrincipal = getTelefonoPrincipal(vo.getTelefonos());
		//OBTENER DATOS TELEFONOS ADICIONALES NUEVOS		
		List<TelefonoVO> telefonosAdicionales = getTelefonosAdicionales(vo.getTelefonos());
		//System.out.println("---EmpresaAppService.telefonosAdicionales:" + telefonosAdicionales.size());
    	if(lstOldTelefonos==null || lstOldTelefonos.size()==0){
    		//CASO 1: NO HAY TELEFONOS ANTERIORES DE NINGUN TIPO
    		//System.out.println("---CASO1");
    		//INSERTAR TELEFONO PRINCIPAL NUEVO    		
    		telefonoPrincipal.setFechaAlta(new Date());
			telefonoPrincipal.setIdPropietario(vo.getIdEmpresa());
			telefonoPrincipal.setIdTipoPropietario(TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());  		
			long idTelefono = telefonoAppService.registrarTelefono(telefonoPrincipal);
			//logger.info("---EmpresaAppService.registrarEmpresa idTelefono :" + idTelefono + " getTelefono:" + telefonoPrincipal.getTelefono());    		
    		//INSERTAR TELEFONOS ADICIONALES NUEVO
			if(telefonosAdicionales!=null && telefonosAdicionales.size()>0){
				Iterator ItAdicionales = telefonosAdicionales.iterator();
				while(ItAdicionales.hasNext()){
					TelefonoVO temp = (TelefonoVO) ItAdicionales.next();
					TelefonoVO nuevo = new TelefonoVO();
					nuevo.setAcceso(temp.getAcceso());
					nuevo.setClave(temp.getClave());
					nuevo.setExtension(temp.getExtension());
					nuevo.setPrincipal(Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion());
					nuevo.setTelefono(temp.getTelefono());
					nuevo.setTipoTelefono(temp.getTipoTelefono());
					nuevo.setFechaAlta(new Date());
					nuevo.setIdPropietario(vo.getIdEmpresa());
					nuevo.setIdTipoPropietario(TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());    							
	    			long idTelefonoAd = telefonoAppService.registrarTelefono(nuevo);
	    			//logger.info("---EmpresaAppService.registrarEmpresa idTelefonoAd :" + idTelefonoAd + " getTelefono:" + nuevo.getTelefono());    								
				}
			}
    	} else {
    		//HAY TELEFONOS ANTERIORES
    		if(hasPrincipalPhone(lstOldTelefonos)){
    			//HAY TELEFONO PRINCIPAL
    			if(hasAdditionalPhone(lstOldTelefonos)){
    				//CASO 2: HAY PRINCIPAL Y ADICIONALES ANTERIORES
    				//System.out.println("---CASO2");
    				//System.out.println("---telefonoPrincipal:" + telefonoPrincipal.toString());
    			    //UPDATE TELEFONO PRINCIPAL CON NUEVO
    				telefonoAppService.actualizarTelefono(telefonoPrincipal);
    				//REMOVE TELEFONOS ADICIONALES ANTERIORES
    				if(lstOldTelefonos!=null && lstOldTelefonos.size()>0){
    					Iterator ItOldTelefonos = lstOldTelefonos.iterator();
    					while(ItOldTelefonos.hasNext()){    						
    						TelefonoVO temp = (TelefonoVO) ItOldTelefonos.next();
    						if(temp.getPrincipal()!=Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion()){
        						String strTel = temp.getTelefono();
        						long lngTel = temp.getIdTelefono();
        						telefonoAppService.eliminarTelefono(temp);
        		    			//logger.info("---EmpresaAppService.eliminarTelefono idTelefonoA :" + lngTel + " getTelefono:" + strTel);    								    							
    						}
    					}
    				}    				
    				//INSERT TELEFONOS ADICIONALES NUEVOS
    				if(telefonosAdicionales!=null && telefonosAdicionales.size()>0){
    					Iterator ItAdicionales = telefonosAdicionales.iterator();
    					while(ItAdicionales.hasNext()){
    						TelefonoVO temp = (TelefonoVO) ItAdicionales.next();
        					TelefonoVO nuevo = new TelefonoVO();
        					nuevo.setAcceso(temp.getAcceso());
        					nuevo.setClave(temp.getClave());
        					nuevo.setExtension(temp.getExtension());
        					nuevo.setPrincipal(Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion());
        					nuevo.setTelefono(temp.getTelefono());
        					nuevo.setTipoTelefono(temp.getTipoTelefono());
        					nuevo.setFechaAlta(new Date());
        					nuevo.setIdPropietario(vo.getIdEmpresa());
        					nuevo.setIdTipoPropietario(TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());   							    						
    		    			long idTelefonoAd = telefonoAppService.registrarTelefono(nuevo);
    		    			//logger.info("---EmpresaAppService.registrarEmpresa idTelefonoAd :" + idTelefonoAd + " getTelefono:" + nuevo.getTelefono());    								
    					}
    				}    				
    			} else {
    				//CASO3: HAY PRINCIPAL ANTERIOR
    				//System.out.println("---CASO3");
    			    //UPDATE TELEFONO PRINCIPAL CON NUEVO
    				telefonoAppService.actualizarTelefono(telefonoPrincipal);
    				//INSERT TELEFONOS ADICIONALES NUEVOS    	
    				if(telefonosAdicionales!=null && telefonosAdicionales.size()>0){
    					Iterator ItAdicionales = telefonosAdicionales.iterator();
    					while(ItAdicionales.hasNext()){
    						TelefonoVO temp = (TelefonoVO) ItAdicionales.next();
        					TelefonoVO nuevo = new TelefonoVO();
        					nuevo.setAcceso(temp.getAcceso());
        					nuevo.setClave(temp.getClave());
        					nuevo.setExtension(temp.getExtension());
        					nuevo.setPrincipal(Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion());
        					nuevo.setTelefono(temp.getTelefono());
        					nuevo.setTipoTelefono(temp.getTipoTelefono());
        					nuevo.setFechaAlta(new Date());
        					nuevo.setIdPropietario(vo.getIdEmpresa());
        					nuevo.setIdTipoPropietario(TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());      						
    		    			long idTelefonoAd = telefonoAppService.registrarTelefono(nuevo);
    		    			//logger.info("---EmpresaAppService.registrarEmpresa idTelefonoAd :" + idTelefonoAd + " getTelefono:" + nuevo.getTelefono());    								
    					}
    				}    				
    			}
    		} else {
    			//CASO 4: SOLO HAY TELEFONOS ADICIONALES ANTERIORES
    			//System.out.println("---CASO4");
        		//INSERTAR TELEFONO PRINCIPAL NUEVO
        		telefonoPrincipal.setFechaAlta(new Date());
    			telefonoPrincipal.setIdPropietario(vo.getIdEmpresa());
    			telefonoPrincipal.setIdTipoPropietario(TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());  		    			
    			long idTelefono = telefonoAppService.registrarTelefono(telefonoPrincipal);
    			//logger.info("---EmpresaAppService.registrarEmpresa idTelefono :" + idTelefono + " getTelefono:" + telefonoPrincipal.getTelefono());    			
				//REMOVE TELEFONOS ADICIONALES ANTERIORES
				if(telefonosAdicionales!=null && telefonosAdicionales.size()>0){
					Iterator ItOldTelefonos = telefonosAdicionales.iterator();
					while(ItOldTelefonos.hasNext()){    						
						TelefonoVO temp = (TelefonoVO) ItOldTelefonos.next();
						if(temp.getPrincipal()!=Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion()){
    						String strTel = temp.getTelefono();
    						temp.setFechaAlta(new Date());
    						temp.setIdPropietario(vo.getIdEmpresa());
    						temp.setIdTipoPropietario(TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());  	    						
    						long lngTel = temp.getIdTelefono();
    						telefonoAppService.eliminarTelefono(temp);
    		    			//logger.info("---EmpresaAppService.eliminarTelefono idTelefonoA :" + lngTel + " getTelefono:" + strTel);    								    							
						}
					}
				}    				    			
				//INSERT TELEFONOS ADICIONALES NUEVOS    	
    			if(telefonosAdicionales!=null && telefonosAdicionales.size()>0){
    				Iterator ItAdicionales = telefonosAdicionales.iterator();
    				while(ItAdicionales.hasNext()){
    					TelefonoVO temp = (TelefonoVO) ItAdicionales.next();
    					TelefonoVO nuevo = new TelefonoVO();
    					nuevo.setAcceso(temp.getAcceso());
    					nuevo.setClave(temp.getClave());
    					nuevo.setExtension(temp.getExtension());
    					nuevo.setPrincipal(Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion());
    					nuevo.setTelefono(temp.getTelefono());
    					nuevo.setTipoTelefono(temp.getTipoTelefono());
    					nuevo.setFechaAlta(new Date());
    					nuevo.setIdPropietario(vo.getIdEmpresa());
    					nuevo.setIdTipoPropietario(TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());  
    	    			long idTelefonoAd = telefonoAppService.registrarTelefono(nuevo);
    	    			//logger.info("---EmpresaAppService.registrarEmpresa idTelefonoAd :" + idTelefonoAd + " getTelefono:" + nuevo.getTelefono());    								
    				}
    			}    			
    		}
    	}
				
		idEvento = EVENTO.MODIFICA_EMPRESA.getIdEvento();
		descripcionEvento = EVENTO.MODIFICA_EMPRESA.getEvento();					
		
		if(idTipoUsuario==(long)TIPO_USUARIO.ADMINISTRADOR.getIdTipoUsuario()){
			if(isChangedEmail){
				if(vo.getEstatus()==ESTATUS.ACTIVO.getIdOpcion()){
						//Cambiar estatus de sus ofertas
						EmpresaDAO empDAO = new EmpresaDAO();
						empDAO.actualizaEstatusOfertasEmpresa(ESTATUS.EMP_MODIFICADA.getIdOpcion(), idEmpresa);				
						
						/** Se establece como PREVALIDADA cuando el publicador realiza la modificacion y aceptacion **/
						vo.setEstatus(ESTATUS.PREVALIDADA.getIdOpcion());
						empresaFacade.update(vo);

						System.out.println("----EmpresaAppService.actualizarEmpresa.idEmpresa:" + idEmpresa);	
						//Actualizar usuario empresa
						usuarioFacade.updateEmail(vo.getIdUsuario(), vo.getCorreoElectronico());
						
						if(isChangedIdPortal){
							String strIdPortal = empresaFacade.generaIDPortalEmpleo(vo);
							empresaFacade.actualizaIDPortalEmpleo(vo.getIdEmpresa(), strIdPortal);
							System.out.println("----EmpresaAppService.actualizaIDPortalEmpleo.strIdPortal:" + strIdPortal);
						}													
						
						//Llamar a caso actualizar cuenta de correos   
						/** La autorizacion establece a la Empresa como PREVALIDADA **/
						autorizacionAppService.autorizaEmpresa(idEmpresa, idUsuario, changedFields, idRegValidar);						
						
				} else if(vo.getEstatus()==ESTATUS.REGISTRADA.getIdOpcion()){
						idEvento = EVENTO.MODIFICA_EMPRESA_POR_AUTORIZAR.getIdEvento();
						descripcionEvento = EVENTO.MODIFICA_EMPRESA_POR_AUTORIZAR.getEvento();										
						
						/** Se establece como PREVALIDADA cuando el publicador realiza la modificacion y aceptacion **/
						vo.setEstatus(ESTATUS.PREVALIDADA.getIdOpcion());
						empresaFacade.update(vo);
						
						System.out.println("----EmpresaAppService.actualizarEmpresa.idEmpresa:" + idEmpresa);	
						//Actualizar usuario empresa
						usuarioFacade.updateEmail(vo.getIdUsuario(), vo.getCorreoElectronico());	
						
						if(isChangedIdPortal){
							String strIdPortal = empresaFacade.generaIDPortalEmpleo(vo);
							empresaFacade.actualizaIDPortalEmpleo(vo.getIdEmpresa(), strIdPortal);
							System.out.println("----EmpresaAppService.actualizaIDPortalEmpleo.strIdPortal:" + strIdPortal);
						}
						
						/** La autorizacion establece a la Empresa como PREVALIDADA **/
						//XXX autorizacionAppService.autorizaEmpresaPorAutorizar(idEmpresa, idUsuario, changedFields, idRegValidar);
						
				} else if(vo.getEstatus()==ESTATUS.INACTIVO.getIdOpcion()){

						/** Se establece como PREVALIDADA cuando el publicador realiza la modificacion y aceptacion **/
						vo.setEstatus(ESTATUS.PREVALIDADA.getIdOpcion());
						empresaFacade.update(vo);
						
						System.out.println("----EmpresaAppService.actualizarEmpresa.idEmpresa:" + idEmpresa);	
						//Actualizar usuario empresa
						usuarioFacade.updateEmail(vo.getIdUsuario(), vo.getCorreoElectronico());	
						
						if(isChangedIdPortal){
							String strIdPortal = empresaFacade.generaIDPortalEmpleo(vo);
							empresaFacade.actualizaIDPortalEmpleo(vo.getIdEmpresa(), strIdPortal);
							System.out.println("----EmpresaAppService.actualizaIDPortalEmpleo.strIdPortal:" + strIdPortal);
						}

						/** La autorizacion establece a la Empresa como PREVALIDADA **/
						autorizacionAppService.autorizaEmpresa(idEmpresa, idUsuario, changedFields, idRegValidar);
						
				} else if(vo.getEstatus()==ESTATUS.PREVALIDADA.getIdOpcion() || vo.getEstatus()==ESTATUS.MODIFICADA.getIdOpcion()){
						/** Se establece como PREVALIDADA cuando el publicador realiza la modificacion y aceptacion **/
						vo.setEstatus(ESTATUS.PREVALIDADA.getIdOpcion());
						empresaFacade.update(vo);
						
						System.out.println("----EmpresaAppService.actualizarEmpresa.idEmpresa:" + idEmpresa);	
						//Actualizar usuario empresa
						usuarioFacade.updateEmail(vo.getIdUsuario(), vo.getCorreoElectronico());		
						
						if(isChangedIdPortal){
							String strIdPortal = empresaFacade.generaIDPortalEmpleo(vo);
							empresaFacade.actualizaIDPortalEmpleo(vo.getIdEmpresa(), strIdPortal);
							System.out.println("----EmpresaAppService.actualizaIDPortalEmpleo.strIdPortal:" + strIdPortal);
						}							

						/** La autorizacion establece a la Empresa como PREVALIDADA **/
						autorizacionAppService.autorizaEmpresa(idEmpresa, idUsuario, changedFields, idRegValidar);
				}else{
					logger.error("ERROR, empresa ("+ idEmpresa +") en estatus inconsistente :"+ vo.getEstatus());
				}
			} else {
				
				/** Se establece como MODIFICADA cuando el publicador realiza la modificacion y aceptacion **/
				vo.setEstatus(ESTATUS.MODIFICADA.getIdOpcion());
				empresaFacade.update(vo);
				
				System.out.println("----EmpresaAppService.actualizarEmpresa.idEmpresa:" + idEmpresa);
				
				if(isChangedIdPortal){
					String strIdPortal = empresaFacade.generaIDPortalEmpleo(vo);
					empresaFacade.actualizaIDPortalEmpleo(vo.getIdEmpresa(), strIdPortal);
					System.out.println("----EmpresaAppService.actualizaIDPortalEmpleo.strIdPortal:" + strIdPortal);
				}
				
				/** La autorizacion establece a la Empresa como MODIFICADA **/
				//XXX autorizacionAppService.autorizaEmpresaModificada(idEmpresa, idUsuario, idRegValidar);				
			}
		} else  if(idTipoUsuario==(long)TIPO_USUARIO.EMPRESA.getIdTipoUsuario()){
			
			
			if(isChangedEmail){
				if(isChangedBasicField) {
					vo.setEstatus(ESTATUS.MODIFICADA.getIdOpcion());  // Se actualiza el estatus de la empresa
					empresaFacade.update(vo);

					System.out.println("----EmpresaAppService.actualizarEmpresa.idEmpresa:" + idEmpresa);	
					usuarioFacade.updateEmail(idUsuario, vo.getCorreoElectronico());
					
					if(isChangedIdPortal){
						String strIdPortal = empresaFacade.generaIDPortalEmpleo(vo);
						empresaFacade.actualizaIDPortalEmpleo(vo.getIdEmpresa(), strIdPortal);
						System.out.println("----EmpresaAppService.actualizaIDPortalEmpleo.strIdPortal:" + strIdPortal);
					}						
					autorizacionAppService.registraEmpresaPorValidar(idEmpresa);					
				} else {
					
					if(isChangedIdPortal){
						String strIdPortal = empresaFacade.generaIDPortalEmpleo(vo);
						empresaFacade.actualizaIDPortalEmpleo(vo.getIdEmpresa(), strIdPortal);
						System.out.println("----EmpresaAppService.actualizaIDPortalEmpleo.strIdPortal:" + strIdPortal);
					}						
					actualizaEmpresaMail(vo, idEmpresa, idUsuario);
				}				
			} else if(isChangedBasicField) {
				vo.setEstatus(ESTATUS.MODIFICADA.getIdOpcion()); // Se actualiza el estatus de la empresa
				empresaFacade.update(vo);
				System.out.println("----EmpresaAppService.actualizarEmpresa.idEmpresa:" + idEmpresa);		
				
				if(isChangedIdPortal){
					String strIdPortal = empresaFacade.generaIDPortalEmpleo(vo);
					empresaFacade.actualizaIDPortalEmpleo(vo.getIdEmpresa(), strIdPortal);
					System.out.println("----EmpresaAppService.actualizaIDPortalEmpleo.strIdPortal:" + strIdPortal);
				}					
				autorizacionAppService.registraEmpresaPorValidar(idEmpresa);
				
			} else {
				empresaFacade.update(vo);		
				System.out.println("----EmpresaAppService.actualizarEmpresa.idEmpresa:" + idEmpresa);	
				
				if(isChangedIdPortal){
					String strIdPortal = empresaFacade.generaIDPortalEmpleo(vo);
					empresaFacade.actualizaIDPortalEmpleo(vo.getIdEmpresa(), strIdPortal);
					System.out.println("----EmpresaAppService.actualizaIDPortalEmpleo.strIdPortal:" + strIdPortal);
				}					
			}			
		} else {
			logger.error("ERROR, tipo de usuario no valido ("+ idTipoUsuario +") para la actualizacion de una Empresa");
		}
		
		bitacoraFacade.save(idEvento,idUsuario, descripcionEvento, Calendar.getInstance(), changedFields, idEmpresa, TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());		
		return idEmpresa;
	}	
	
	
	private List<TelefonoVO> getTelefonosAdicionales(List<TelefonoVO> lst){
		ArrayList<TelefonoVO> lstAdicionales = new ArrayList<TelefonoVO>();
    	if(lst!=null && lst.size()>0){
    		Iterator it = lst.iterator();    		
    		while(it.hasNext()){
    			TelefonoVO temp = (TelefonoVO) it.next();
    			if(temp.getPrincipal()==Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion()){
    				lstAdicionales.add(temp);
    			}    			
    		}
    	}			
		return lstAdicionales;
	}
	
	private TelefonoVO getTelefonoPrincipal(List<TelefonoVO> lst){
		TelefonoVO telvo = new TelefonoVO();
    	if(lst!=null && lst.size()>0){
    		Iterator it = lst.iterator();    		
    		while(it.hasNext()){
    			TelefonoVO temp = (TelefonoVO) it.next();
    			if(temp.getPrincipal()==Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion()){
    				telvo = temp;
    				break;
    			}    			
    		}
    	}		
		return telvo;
	}

	private boolean hasPrincipalPhone(List<TelefonoVO> lst) {
		boolean principal = false;
		if (lst != null && lst.size() > 0) {
			Iterator it = lst.iterator();
			while (it.hasNext()) {
				TelefonoVO temp = (TelefonoVO) it.next();
				if (temp.getPrincipal() == Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion()) {
					principal = true;
					break;
				}
			}
		}
		return principal;
	}

	private boolean hasAdditionalPhone(List<TelefonoVO> lst) {
		boolean additional = false;
		if (lst != null && lst.size() > 0) {
			Iterator it = lst.iterator();
			while (it.hasNext()) {
				TelefonoVO temp = (TelefonoVO) it.next();
				if (temp.getPrincipal() == Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion()) {
					additional = true;
					break;
				}
			}
		}
		return additional;
	}

	/*
	 * TODO: Probar public void recuperarEmpresa(long idRegValidar, EmpresaVO
	 * empresa, long idUsuario) throws SQLException { RegistroPorValidarVO
	 * registroVo = registroPorValidarFacade.find(idRegValidar); Date fechaOld =
	 * registroVo.getFechaModificacion(); if(empresa.getEstatus()==
	 * ESTATUS.ELIMINADA_ADMIN.getIdOpcion()){ long idEmpresa =
	 * empresa.getIdEmpresa(); long idEvento =
	 * EVENTO.RECUPERA_EMPRESA_ELIMINADA_ADMIN.getIdEvento(); String
	 * descripcionEvento = EVENTO.RECUPERA_EMPRESA_ELIMINADA_ADMIN.getEvento();
	 * String detalle = "idEmpresa=" + idEmpresa + "|idRegValidar=" +
	 * idRegValidar + "|idUsuario=" + idUsuario + "|fechaModRegVal=" + fechaOld
	 * + "|"; registroPorValidarFacade.actualizaEstatus(idRegValidar,
	 * ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion(), new Date());
	 * bitacoraFacade.save(idEvento, idUsuario, descripcionEvento,
	 * Calendar.getInstance(), detalle, idEmpresa,
	 * TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario()); } }
	 */
	public void notificacionRecuperacionPswEmpresa(long idEmpresa, String usuario, String nombreEmpresa,
			String correoElectronico, String clave) throws MailException {
		NotificacionService notificacionService = new NotificacionService();
		notificacionService.notificacionRecuperacionPswEmpresa(idEmpresa, usuario, nombreEmpresa, correoElectronico,
				clave);
	}

	// Notificacion Recuperacion Contraseña OAM
	public void notificacionRecuperacionContrasena(long idPropietario, String usuario, TIPO_PROPIETARIO tipoPropietario,
			String nombrePropietario, String correoElectronico, String url) throws MailException {
		NotificacionService notificacionService = new NotificacionService();
		notificacionService.notificacionRecuperacionContrasena(idPropietario, usuario, tipoPropietario,
				nombrePropietario, correoElectronico, url);
	}

	public void notificacionRecuperacionPswMovilEmpresa(long idEmpresa, String nombreEmpresa, String correoElectronico,
			String clave, String usuario) throws MailException {
		NotificacionService notificacionService = new NotificacionService();
		notificacionService.notificacionRecuperacionPswMovilEmpresa(idEmpresa, nombreEmpresa, correoElectronico, clave,
				usuario);
	}

	public void actualizaEmpresaMail(EmpresaVO empresa, long idEmpresa, long idUsuario) throws SQLException {

		empresaFacade.actualizaEstatus(idEmpresa, ESTATUS.MODIFICADA.getIdOpcion());
		empresa.setEstatus(ESTATUS.MODIFICADA.getIdOpcion());				
		
		empresaFacade.update(empresa);
		
		//System.out.println("----EmpresaAppService.actualizarEmpresa.idEmpresa:" + idEmpresa);
		usuarioFacade.updateEmail(idUsuario, empresa.getCorreoElectronico());
		
		String passwOrigen = Password.getPassword();
		String passw = "";
		try {
			passw = Password.codificaPassword(passwOrigen);
			usuarioFacade.updatePassword(idUsuario, passw);	
		} catch (EncodingException e2) {
			logger.error(e2);
		}

		//Cambiar estatus de sus ofertas
    	try {
    		EmpresaDAO empDAO = new EmpresaDAO();
			empDAO.actualizaEstatusOfertasEmpresa(ESTATUS.EMP_MODIFICADA.getIdOpcion(), idEmpresa);
			
			/*Notifico via email a empresa*/
			try {
				NotificacionService notificacionService = new NotificacionService();
				notificacionService.notificacionToEmpresa(empresa, passwOrigen);
			} catch (MailException e1) {
				logger.error(e1);
			}

			/*
			autorizacionAppService.registraEmpresaPorValidar(idEmpresa);
			
			List<Long> idsOfertasEmpleo = empDAO.consultaOfertas(idEmpresa, ESTATUS.EMP_MODIFICADA.getIdOpcion());
			for (Long idOfertaEmpleo : idsOfertasEmpleo){
				autorizacionAppService.registraOfertaPorValidar(idOfertaEmpleo, idEmpresa);
			}
			*/

    	} catch (SQLException e1) {
			logger.error(e1); throw e1;
		} catch (Exception e1) {
			logger.error(e1); throw new SQLException(e1);
		}
	}
	
	public EmpresaPorAutorizarVO findEmpresaPorAutorizarById(long id){
		EmpresaPorAutorizarVO empresa = new EmpresaPorAutorizarVO();
		
		try {

			empresa = empresaPorAutorizarFacade.findById(id);

			if (empresa!=null){
				System.out.println("----empresa.getEstatus():" + empresa.getEstatus());
				String tipoPersona = TIPO_PERSONA.getTipoPersona((int)empresa.getIdTipoPersona());
				empresa.setTipoPersona(tipoPersona);
				
				String tipoEmpresa = TIPO_EMPRESA.getTipoEmpresa((int)empresa.getIdTipoEmpresa());
				empresa.setTipoEmpresa(tipoEmpresa);
				
				String actividadEconomica = catalogoOpcionFacade.getOpcionById(CATALOGO_OPCION_TIPO_ACTIVIDAD_ECONOMICA, empresa.getIdActividadEconomica());
				empresa.setActividadEconomica(actividadEconomica);
				
				DomicilioVO domicilio = domicilioAppService.buscarDomicilioIdPropietario(empresa.getIdEmpresa(), 
						                                                                   TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
				if (domicilio!=null){
					empresa.setDomicilio(domicilio);

					String entidad = catalogoOpcionFacade.getOpcionById(CATALOGO_OPCION_ENTIDAD_FEDERATIVA, domicilio.getIdEntidad());
					domicilio.setEntidad(entidad);

					MunicipioVO municipioVO = domicilioFacade.consultaMunicipio(domicilio.getIdMunicipio(), domicilio.getIdEntidad());
					if (municipioVO!=null){
						String municipio = municipioVO.getMunicipio();
						domicilio.setMunicipio(municipio);
					}

					CodigoPostalVO codigoPostalVO = domicilioFacade.consultaCodigoPostal(domicilio.getIdColonia());
					if (codigoPostalVO!=null){
						String colonia = codigoPostalVO.getColoniaDescripcion();
						domicilio.setColonia(colonia);
					}
				}

				List<TelefonoVO> telefonos = telefonoAppService.consultarTelefonos(empresa.getIdEmpresa(),
						                                                           TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
				empresa.setTelefonos(telefonos);
				
				if (telefonos!=null && !telefonos.isEmpty()){
					for (TelefonoVO telefono : telefonos){
						String tipoTelefono = TIPO_TELEFONO.getTipoTelefono(telefono.getIdTipoTelefono());
						telefono.setTipoTelefono(tipoTelefono);
					}
				}
			}

    	} catch(BusinessException e){
    		logger.error(e);
    		//	TODO throw new BusinessException("No se pudo consultar el registro", e);
    	} catch(Exception e){
    		logger.error(e);
    	}

		return empresa;
	}
	
	
	public EmpresaVO consultaEmpresa(long idEmpresa) throws PersistenceException {
		EmpresaVO empresa = empresaFacade.findById(idEmpresa);
		return empresa;
	}
	
	/**
	 * BUsca una empresa en base a su identificador
	 * @param vo
	 * @return EmpresaVO
	 */	
	public EmpresaVO findEmpresaById(long id) {
		EmpresaVO empresa = new EmpresaVO();
		
		try {
			empresa = empresaFacade.findById(id);
			if (empresa!=null) {
				String tipoPersona = TIPO_PERSONA.getTipoPersona((int)empresa.getIdTipoPersona());
				empresa.setTipoPersona(tipoPersona);
				
				String tipoEmpresa = TIPO_EMPRESA.getTipoEmpresa((int)empresa.getIdTipoEmpresa());
				empresa.setTipoEmpresa(tipoEmpresa);
				
				
				String actividadEconomica = catalogoOpcionFacade.getOpcionById(CATALOGO_OPCION_TIPO_ACTIVIDAD_ECONOMICA, empresa.getIdActividadEconomica());
				empresa.setActividadEconomica(actividadEconomica);
				
				DomicilioVO domicilio = domicilioAppService.buscarDomicilioIdPropietario(empresa.getIdEmpresa(), 
						                                                                 TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());

				if (domicilio!=null){
					empresa.setDomicilio(domicilio);

					String entidad = catalogoOpcionFacade.getOpcionById(CATALOGO_OPCION_ENTIDAD_FEDERATIVA, domicilio.getIdEntidad());
					domicilio.setEntidad(entidad);

					MunicipioVO municipioVO = domicilioFacade.consultaMunicipio(domicilio.getIdMunicipio(), domicilio.getIdEntidad());
					if (municipioVO!=null){
						String municipio = municipioVO.getMunicipio();
						domicilio.setMunicipio(municipio);
					}

					CodigoPostalVO codigoPostalVO = domicilioFacade.consultaCodigoPostal(domicilio.getIdColonia());
					if (codigoPostalVO!=null){
						String colonia = codigoPostalVO.getColoniaDescripcion();
						domicilio.setColonia(colonia);
					}
				}

				List<TelefonoVO> telefonos = telefonoAppService.consultarTelefonos(empresa.getIdEmpresa(),
						                                                           TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
				empresa.setTelefonos(telefonos);
				
				if (telefonos!=null && !telefonos.isEmpty()){
					for (TelefonoVO telefono : telefonos){
						String tipoTelefono = TIPO_TELEFONO.getTipoTelefono(telefono.getIdTipoTelefono());
						telefono.setTipoTelefono(tipoTelefono);
					}
				}
				ParametroVO parametroVO = parametroFacade.findById(ID_PARAMETRO_ID_EMPRESA_OFERTAS_CANADA);
				
				if(parametroVO!=null){
					if(empresa.getIdEmpresa()==Utils.parseLong(parametroVO.getValor()))empresa.setOfertaCanada(true);
					else empresa.setOfertaCanada(false);
				}else{
					empresa.setOfertaCanada(false);
				}
				
				boolean funcionPublica = EmpresaFuncionPublicaService.esEmpresaFuncionPublica(id);
				empresa.setEsfuncionPublica(funcionPublica);
			}

		}catch(BusinessException e){
    	 	e.printStackTrace(); logger.error(e);
    		//	TODO throw new BusinessException("No se pudo consultar el registro", e);
    	}catch(Exception e){
    		e.printStackTrace(); logger.error(e);
    	}

		return empresa;
	}		
	
	/**
	 * Method 'findByIdUsuario'
	 * Regresa un objeto EmpresaVO con los datos correspondientes a
	 * la empresa cuyo identificador de usuario se proporciona
	 * 
	 * @param idUsuario
	 * @return EmpresaVO
	 * @throws BusinessException 
	 */		
	public EmpresaVO findEmpresaByIdUsuario(long idUsuario) throws BusinessException {
		EmpresaVO empresaVo = new EmpresaVO();		
		empresaVo = empresaFacade.findByIdUsuario(idUsuario);		
		try {
			
			DomicilioVO domicilioVo = domicilioAppService.buscarDomicilioIdPropietario(empresaVo.getIdEmpresa(), TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
			empresaVo.setDomicilio(domicilioVo);
			List<TelefonoVO> lstTelefonos = telefonoAppService.consultarTelefonos(empresaVo.getIdEmpresa(), TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
			empresaVo.setTelefonos(lstTelefonos);
		} catch (BusinessException e) {
			logger.error(e);
			throw new BusinessException("No se pudo consultar el registro");
		}
		return empresaVo;
	}

	/**
	 * Method 'findEmpresaByCP' Regresa un objeto EmpresaVO con los datos
	 * correspondientes a la empresa cuyo RFC de usuario se proporciona
	 * 
	 * @param RFC
	 * @return EmpresaVO
	 * @throws SQLException
	 */
	public EmpresaVO findEmpresaByCP(String rfc, String email, String cp) throws BusinessException, SQLException {
		long idEmpresaActual = 0;
		EmpresaVO vo = null;
		List<Long> list = new ArrayList<Long>();
		try {
			if (null != rfc && !rfc.isEmpty()) {
				list = empresaFacade.findByRFC_CP(rfc, cp);
				if (null != list && !list.isEmpty()) { 
					if (list.size() > 1) {
						List<EmpresaUsuarioVO> empresa_usuario = empresaFacade.obtieneEmpresas(rfc, cp);
						if (null != empresa_usuario && !empresa_usuario.isEmpty()) {
							if (null != email && !email.isEmpty()) {
								for (EmpresaUsuarioVO obj : empresa_usuario) {
									if (obj.getEmail().equalsIgnoreCase(email)) 
										idEmpresaActual = obj.getIdEmpresa();
								}
							}else idEmpresaActual = empresa_usuario.get(0).getIdEmpresa();
						}idEmpresaActual = list.get(0).longValue();
					}else idEmpresaActual = list.get(0).longValue();
					vo = empresaFacade.findByIdEmpresa(idEmpresaActual);
				}
			}else if (null != email && !email.isEmpty())
				vo = empresaFacade.findByEmailCP(email, cp);
		} catch (PersistenceException e) {
			logger.error(e);
			throw new PersistenceException("No se pudo consultar el registro");
		}
		return vo;
	}

	/**
	 * {@inheritDoc}
	 */
	public void actualizaLogotipo(long idEmpresa, long idPerfil, byte[] logotipo) throws PersistenceException, BusinessException {
	
		if (idPerfil == PERFIL.EMPRESA.getIdOpcion()) {
			
			EmpresaVO empresaVo = empresaFacade.findById(idEmpresa);
	
			if (empresaVo != null && empresaVo.getEstatus() == ESTATUS.ACTIVO.getIdOpcion()) {
				empresaVo.setLogotipo(logotipo);
				empresaFacade.update(empresaVo);
	
			} else {
				throw new BusinessException("emp.logo.reglaNeg.err");
			}
		} else {

			throw new BusinessException("app.general.usuPerfil.err");
		}
	}
	
	private EmpresaVO getEmpresaVO(EmpresaPorAutorizarVO empresaPorAutorizarVO) {

		EmpresaVO empresaVO = new EmpresaVO();

		empresaVO.setIdEmpresa(empresaPorAutorizarVO.getIdEmpresa());
		empresaVO.setIdPortalEmpleo(empresaPorAutorizarVO.getIdPortalEmpleo());
	
		empresaVO.setRfc(empresaPorAutorizarVO.getRfc());
		empresaVO.setIdTipoPersona(empresaPorAutorizarVO.getIdTipoPersona());
		empresaVO.setNombre(empresaPorAutorizarVO.getNombre());
		empresaVO.setApellido1(empresaPorAutorizarVO.getApellido1());
		empresaVO.setApellido2(empresaPorAutorizarVO.getApellido2());
		empresaVO
				.setFechaNacimiento(empresaPorAutorizarVO.getFechaNacimiento());
		empresaVO.setRazonSocial(empresaPorAutorizarVO.getRazonSocial());
		empresaVO.setFechaActa(empresaPorAutorizarVO.getFechaActa());
		empresaVO.setDescripcion(empresaPorAutorizarVO.getDescripcion());
		empresaVO
				.setContactoEmpresa(empresaPorAutorizarVO.getContactoEmpresa());
		empresaVO.setIdTipoEmpresa(empresaPorAutorizarVO.getIdTipoEmpresa());

		empresaVO.setIdActividadEconomica(empresaPorAutorizarVO
				.getIdActividadEconomica());
		empresaVO
				.setNumeroEmpleados(empresaPorAutorizarVO.getNumeroEmpleados());
		empresaVO.setIdMedio(empresaPorAutorizarVO.getIdMedio());

		empresaVO.setConfidencial(empresaPorAutorizarVO.getConfidencial());
		empresaVO.setPaginaWeb(empresaPorAutorizarVO.getPaginaWeb());

		empresaVO.setAceptacionTerminos(empresaPorAutorizarVO
				.getAceptacionTerminos());
		empresaVO.setFechaAlta(empresaPorAutorizarVO.getFechaAlta());

		empresaVO.setEstatus(empresaPorAutorizarVO.getEstatus());

		empresaVO.setFechaUltimaActualizacion(empresaPorAutorizarVO
				.getFechaUltimaActualizacion());
		empresaVO.setCorreoElectronico(empresaPorAutorizarVO
				.getCorreoElectronico());
		empresaVO.setAseguraDatos(empresaPorAutorizarVO.getAseguraDatos());
		empresaVO.setTelefonos(empresaPorAutorizarVO.getTelefonos());
		empresaVO.setDomicilio(empresaPorAutorizarVO.getDomicilio());
		empresaVO.setNombreComercial(empresaPorAutorizarVO.getNombreComercial());

		return empresaVO;
	}

	@Override
	public void notificaEmpresaPorAutorizar(EmpresaPorAutorizarVO empresaPorAutorizarVO, long idUsuario)throws PersistenceException, BusinessException, TechnicalException, MailException{		

		try {
			if (empresaPorAutorizarVO.getEstatus() != ESTATUS.PREVALIDADA.getIdOpcion()) {
				throw new BusinessException("La empresa no tiene estatus de PREVALIDADA, no puede ser procesada");
			}

			EmpresaVO empresaVO = getEmpresaVO(empresaPorAutorizarVO);
			
			/* Se crea usuario */				
			UsuarioVO usuarioVO = new UsuarioVO();
			
			String passw = Password.getPassword();
		    String passw2 = Password.codificaPassword(passw);
			
			usuarioVO.setContrasena(passw2);
			
			usuarioVO.setCorreoElectronico(empresaPorAutorizarVO.getCorreoElectronico());

			// A causa del nuevo modo de registro se activa la empresa
			usuarioVO.setUsuario(empresaPorAutorizarVO.getCorreoElectronico());
			usuarioVO.setEstatus(ESTATUS.ACTIVO.getIdOpcion());
			
			usuarioVO.setFechaAlta(new Date());

			// usuarioVO.setIdEntidad(9); // TODO Revisar como se obtiene el valor de la entidad
			usuarioVO.setIdPerfil(PERFIL.EMPRESA.getIdOpcion());

			usuarioVO.setFechaModificacion(new Date());
			usuarioVO.setIdRegistro(ID_REGISTRO_PORTAL);
			usuarioVO.setIdTipoUsuario(TIPO_USUARIO.EMPRESA.getIdTipoUsuario());

			/* Crea nuevo usuario */
			long idUuario = usuarioFacade.save(usuarioVO);

			empresaVO.setIdUsuario(idUuario);

			// A causa del nuevo modo de registro se activa la empresa
			empresaVO.setEstatus(ESTATUS.ACTIVO.getIdOpcion());
			empresaVO.setFechaUltimaActualizacion(new Date());
			empresaVO.setAceptacionTerminos(ESTATUS.ACTIVO.getIdOpcion());
			
			/* Crea empresa */
			empresaFacade.save(empresaVO);
			
			/* Borra empresa de empresas por autorizar */
			empresaPorAutorizarFacade.delete(empresaPorAutorizarVO.getIdEmpresa());

			/*Notifico via email a empresa*/
			NotificacionService notificacionService = new NotificacionService();

			notificacionService.notificacionToEmpresaPorAutorizar(empresaPorAutorizarVO, passw);
			long idEmpresa = empresaVO.getIdEmpresa();

			bitacoraEstatus(EVENTO.NOTIFICACION_EMPRESA_POR_AUTORIZAR, idUsuario, TIPO_REGISTRO.EMPRESA,  idEmpresa, empresaVO.getEstatus(), null);

		} catch (MailException e) {
			throw new MailException(e);
		} catch (EncodingException e){
			throw new TechnicalException(e);
		}
	}
	
	/*@Override
	public void notificaEmpresa(EmpresaVO empresaVO, long idUsuario) 
	throws PersistenceException,
			BusinessException, TechnicalException, MailException {

		try {
			
			if (idUsuario <= 0) throw new IllegalArgumentException("Identificador de idUsuario requerido");
			if (empresaVO == null ) throw new IllegalArgumentException("empresaVO es requerido");
			
			UsuarioVO usuarioVO = new UsuarioVO(); 
			usuarioVO = usuarioFacade.find(idUsuario);
			boolean isvalid = false;			
			
			if(usuarioVO!=null){
				isvalid = isValidProcess(empresaVO, usuarioVO);
			}				
			
			if(!isvalid){
				throw new BusinessException("El estatus de la empresa no es valido y/o el tipo de usuario es invalido");
			}

			long tipoUsuario = usuarioVO.getIdTipoUsuario();			
			String passw = Password.getPassword();
			String passwdb = Password.codificaPassword(passw);

			usuarioFacade.updatePassword(empresaVO.getIdUsuario(), passwdb);
				
			if ( (empresaVO.getEstatus() == ESTATUS.PREVALIDADA.getIdOpcion())
					&& (tipoUsuario == TIPO_USUARIO.ADMINISTRADOR
							.getIdTipoUsuario()) ) {

				bitacoraEstatus(EVENTO.NOTIFICACION_EMPRESA_PREVALIDADA,
						idUsuario, TIPO_REGISTRO.EMPRESA,
						empresaVO.getIdEmpresa(), empresaVO.getEstatus(),
						null);
			} else if ((empresaVO.getEstatus() == ESTATUS.INACTIVO
					.getIdOpcion())
					&& (tipoUsuario == TIPO_USUARIO.ADMINISTRADOR
							.getIdTipoUsuario())) {

				bitacoraEstatus(EVENTO.NOTIFICACION_EMPRESA_INACTIVA,
						idUsuario, TIPO_REGISTRO.EMPRESA,
						empresaVO.getIdEmpresa(), empresaVO.getEstatus(),
						null);
			} else if ((empresaVO.getEstatus() == ESTATUS.MODIFICADA
					.getIdOpcion())
					&& (tipoUsuario == TIPO_USUARIO.ADMINISTRADOR
							.getIdTipoUsuario())) {

				bitacoraEstatus(EVENTO.NOTIFICACION_EMPRESA_MODIFICADA,
						idUsuario, TIPO_REGISTRO.EMPRESA,
						empresaVO.getIdEmpresa(), empresaVO.getEstatus(),
						null);
			} else if ((empresaVO.getEstatus() == ESTATUS.MODIFICADA
					.getIdOpcion())
					&& (tipoUsuario == TIPO_USUARIO.EMPRESA.getIdTipoUsuario())) {

				bitacoraEstatus(EVENTO.NOTIFICACION_EMPRESA_MODIFICADA,
						idUsuario, TIPO_REGISTRO.EMPRESA,
						empresaVO.getIdEmpresa(), empresaVO.getEstatus(),
						null);
			}
			
			NotificacionService notificacionService = new NotificacionService();
			notificacionService.notificacionToEmpresa(empresaVO, passw);
			
			
		} catch (MailException e) {
			throw new MailException(e);
		} catch (EncodingException e) {
			throw new TechnicalException(e);
		}

	}*/

	public void bitacoraEstatus(EVENTO evento, long idUsuario, TIPO_REGISTRO tipoRegistro, long idRegistro, int estatusAnterior, String emailanterior){
    	bitacoraEstatus(evento, idUsuario, null, tipoRegistro, idRegistro, estatusAnterior, emailanterior);
    }

    public void bitacoraEstatus(EVENTO evento, long idUsuario, String descripcion, 
    		                     TIPO_REGISTRO tipoRegistro, long idRegistro, int estatusAnterior, String dataanterior) {    	

    	String idDetalle = null;
    	int estatus = -1;
    	
    	estatus = estatusAnterior;
    	
    	if (tipoRegistro == TIPO_REGISTRO.EMPRESA){
    		idDetalle = "idEmpresa="+ idRegistro;
    	} 
    	    	
    	String detalle = "";     	    	
    	if(estatus != -1){
    		detalle = idDetalle + "|estatus=" + estatusAnterior;
    	}

    	if (descripcion==null) descripcion = evento.getEvento();
    	if (dataanterior != null) detalle = detalle + "|emailanterior=" +  dataanterior;
    		
    	bitacoraFacade.save(evento.getIdEvento(), idUsuario, descripcion, Calendar.getInstance(), detalle, idRegistro, TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
    
    }
    
    public EmpresaVO consultaEmpresaPorAutorizar(long idEmpresa){
    	
    	if (idEmpresa<=0) throw new IllegalArgumentException("Identificador de empresa requerido");
    	
    	EmpresaPorAutorizarVO empresaPorAutorizar = empresaPorAutorizarFacade.findById(idEmpresa);
    	
    	EmpresaVO empresa = getEmpresaVO(empresaPorAutorizar);

		String tipoPersona = TIPO_PERSONA.getTipoPersona((int)empresa.getIdTipoPersona());
		empresa.setTipoPersona(tipoPersona);
		
		String tipoEmpresa = TIPO_EMPRESA.getTipoEmpresa((int)empresa.getIdTipoEmpresa());
		empresa.setTipoEmpresa(tipoEmpresa);
		
		String actividadEconomica = catalogoOpcionFacade.getOpcionById(CATALOGO_OPCION_TIPO_ACTIVIDAD_ECONOMICA, empresa.getIdActividadEconomica());
		empresa.setActividadEconomica(actividadEconomica);
		
		try {
			DomicilioVO domicilio = domicilioAppService.buscarDomicilioIdPropietario(empresa.getIdEmpresa(), 
					                                                                   TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
			
			if (domicilio!=null){
				empresa.setDomicilio(domicilio);

				String entidad = catalogoOpcionFacade.getOpcionById(CATALOGO_OPCION_ENTIDAD_FEDERATIVA, domicilio.getIdEntidad());
				domicilio.setEntidad(entidad);

				MunicipioVO municipioVO = domicilioFacade.consultaMunicipio(domicilio.getIdMunicipio(), domicilio.getIdEntidad());
				if (municipioVO!=null){
					String municipio = municipioVO.getMunicipio();
					domicilio.setMunicipio(municipio);
				}

				CodigoPostalVO codigoPostalVO = domicilioFacade.consultaCodigoPostal(domicilio.getIdColonia());
				if (codigoPostalVO!=null){
					String colonia = codigoPostalVO.getColoniaDescripcion();
					domicilio.setColonia(colonia);
				}
			}
			
		} catch (BusinessException e1) {logger.error(e1);}
		  catch (Exception e1) {logger.error(e1);}

		List<TelefonoVO> telefonos = telefonoAppService.consultarTelefonos(empresa.getIdEmpresa(),
				                                                           TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
		empresa.setTelefonos(telefonos);
		
		if (telefonos!=null && !telefonos.isEmpty()){
			for (TelefonoVO telefono : telefonos){
				String tipoTelefono = TIPO_TELEFONO.getTipoTelefono(telefono.getIdTipoTelefono());
				telefono.setTipoTelefono(tipoTelefono);
			}
		}

    	return empresa;
    }

	@Override
	public void actualizarEmpresaAutorizar(long idEmpresa)throws PersistenceException, BusinessException {
		long idUsuario;

    	if (registroPorValidarFacade.existeRegistroPorValidar(TIPO_REGISTRO.EMPRESA, idEmpresa,
                                                              ESTATUS.PENDIENTE_PUBLICAR, ESTATUS.ASIGNADO_PUBLICADOR, ESTATUS.EN_EDICION_PUBLICADOR)){
    		throw new BusinessException(MessageLoader.getInstance().getMessage("autorizacion.registro.pendiente"));
    	}
		
		if(empresaFacade.validandoEmpresaEstatus(idEmpresa)){
			idUsuario =	empresaFacade.actualizaEmpresaEstatus(idEmpresa, ESTATUS.ACTIVO.getIdOpcion());

			usuarioFacade.activarUsuario(idUsuario);		

			bitacoraEstatus(EVENTO.ACTIVACION_EMPRESA, idUsuario, TIPO_REGISTRO.EMPRESA, idEmpresa, ESTATUS.ACTIVO.getIdOpcion(), null);
		} else {
			throw new BusinessException("La empresa no se encuentra en estatus prevalidada y/o modificada password no es correcto");	
		}	
	}

	public void notificaEmpresaInactiva(EmpresaVO empresaVO, long idUsuario, String emailanterior) throws PersistenceException, BusinessException, TechnicalException, MailException {
		
		try {
			if (empresaVO.getEstatus() != ESTATUS.INACTIVO.getIdOpcion()) {
				throw new BusinessException("La empresa no tiene estatus de INACTIVO, no puede ser procesada");
			}
			
			String passw = Password.getPassword();
		    //String passw2 = Password.codificaPassword(passw);

			/*Notifico via email a empresa*/
			NotificacionService notificacionService = new NotificacionService();

			notificacionService.notificacionToEmpresa(empresaVO, passw);
			long idEmpresa = empresaVO.getIdEmpresa();

			bitacoraEstatus(EVENTO.NOTIFICACION_EMPRESA_INACTIVA, idUsuario, TIPO_REGISTRO.EMPRESA,  idEmpresa, empresaVO.getEstatus(), emailanterior);
		} catch (MailException e) {
			throw new MailException(e);
		} /*catch (EncodingException e){
			throw new TechnicalException(e);
		}*/
		
	}

	private boolean isValidProcess(EmpresaVO empresaVO, UsuarioVO usuarioVO){
		boolean isvalid = false;
		long tipoUsuario = usuarioVO.getIdTipoUsuario();
		
		if (empresaVO.getEstatus() == ESTATUS.PREVALIDADA.getIdOpcion() &&  tipoUsuario == TIPO_USUARIO.ADMINISTRADOR.getIdTipoUsuario()) {		
			isvalid=true;
		} else if (empresaVO.getEstatus() == ESTATUS.INACTIVO.getIdOpcion() && tipoUsuario == TIPO_USUARIO.ADMINISTRADOR.getIdTipoUsuario()) {
			isvalid=true;
		} else if (empresaVO.getEstatus() == ESTATUS.MODIFICADA.getIdOpcion() && tipoUsuario == TIPO_USUARIO.ADMINISTRADOR.getIdTipoUsuario()) {
			isvalid=true;
		} else if (empresaVO.getEstatus() == ESTATUS.ACTIVO.getIdOpcion() && tipoUsuario == TIPO_USUARIO.ADMINISTRADOR.getIdTipoUsuario()) {
			isvalid=true;
		} else if (empresaVO.getEstatus() == ESTATUS.MODIFICADA.getIdOpcion() && tipoUsuario == TIPO_USUARIO.EMPRESA.getIdTipoUsuario()) {
			isvalid=true;
		}

		return isvalid;
	}
	
	/*
	 * Para el nuevo registro de Empresa
	 * */	
	public void notificaEmpresaCreada(long idUsuario, long idEmpresa, int estatusEmpresa, String nombreEmpresa, 
			String correoEmpresa, String contrasena, String idPortalEmpleo, String detalle) throws BusinessException, MailException {
		if (idUsuario <= 0) throw new IllegalArgumentException("Identificador de idUsuario requerido");
		try {

			UsuarioVO usuario = usuarioFacade.find(idUsuario);

			boolean isvalid = false;
			long tipoUsuario = usuario.getIdTipoUsuario();
						
			if (estatusEmpresa == ESTATUS.ACTIVO.getIdOpcion() && (tipoUsuario == TIPO_USUARIO.EMPRESA.getIdTipoUsuario() || tipoUsuario == TIPO_USUARIO.ADMINISTRADOR.getIdTipoUsuario())) {		
				isvalid=true;
			}	
			
			if(!isvalid){ 
				throw new BusinessException("El estatus de la empresa no es valido y/o el tipo de usuario es invalido");
			}
			
			bitacoraEstatus(EVENTO.NOTIFICACION_EMPRESA, idUsuario, TIPO_REGISTRO.EMPRESA, idEmpresa, estatusEmpresa, detalle);
			
			NotificacionService notificacionService = new NotificacionService();
			notificacionService.notificacionToNuevaEmpresa(nombreEmpresa, correoEmpresa, contrasena, idPortalEmpleo);

		} catch (MailException e) {
			throw new MailException(e);
		}
		
	}
	
	@Override
	public void notificaEmpresa(EmpresaVO empresa, long idUsuario, String detalle) throws PersistenceException, BusinessException, TechnicalException, MailException {

		if (idUsuario <= 0) throw new IllegalArgumentException("Identificador de idUsuario requerido");
		//if (strDetalle == null || strDetalle.equals("") ) throw new IllegalArgumentException("Detalle es requerido");
		
		try {

			UsuarioVO usuario = usuarioFacade.find(idUsuario);

			boolean isvalid = false;
			
			if(usuario!=null){
				isvalid = isValidProcess(empresa, usuario);
			}				
			
			if(!isvalid){
				throw new BusinessException("El estatus de la empresa no es valido y/o el tipo de usuario es invalido");
			}
			
			//long tipoUsuario = usuario.getIdTipoUsuario();
			String passw = Password.getPassword();
			String passwdb = Password.codificaPassword(passw);
			
			usuarioFacade.updatePassword(empresa.getIdUsuario(), passwdb);
				
			if ( empresa.getEstatus() == ESTATUS.PREVALIDADA.getIdOpcion()) {

				bitacoraEstatus(EVENTO.NOTIFICACION_EMPRESA_PREVALIDADA, idUsuario,  TIPO_REGISTRO.EMPRESA, empresa.getIdEmpresa(), empresa.getEstatus(), detalle);

			} else if (empresa.getEstatus() == ESTATUS.INACTIVO.getIdOpcion()) {

				bitacoraEstatus(EVENTO.NOTIFICACION_EMPRESA_INACTIVA,idUsuario, TIPO_REGISTRO.EMPRESA,empresa.getIdEmpresa(), empresa.getEstatus(), detalle);
			
			} else if (empresa.getEstatus() == ESTATUS.MODIFICADA.getIdOpcion()) {

				bitacoraEstatus(EVENTO.NOTIFICACION_EMPRESA_MODIFICADA, idUsuario, TIPO_REGISTRO.EMPRESA, empresa.getIdEmpresa(), empresa.getEstatus(), detalle);
				
			} /*else if (empresa.getEstatus() == ESTATUS.MODIFICADA.getIdOpcion()) {

				bitacoraEstatus(EVENTO.NOTIFICACION_EMPRESA_MODIFICADA, idUsuario, TIPO_REGISTRO.EMPRESA, empresa.getIdEmpresa(), empresa.getEstatus(), detalle);
			} */else {
				bitacoraEstatus(EVENTO.NOTIFICACION_EMPRESA, idUsuario, TIPO_REGISTRO.EMPRESA, empresa.getIdEmpresa(), empresa.getEstatus(), detalle);
			}

			NotificacionService notificacionService = new NotificacionService();
			notificacionService.notificacionToEmpresa(empresa, passw);

		} catch (MailException e) {
			throw new MailException(e);
		} catch (EncodingException e) {
			throw new TechnicalException(e);
		}

	}

	public void notificaCambioContrasena(EmpresaVO empresaVO, String correoElectronico) throws TechnicalException, MailException {

		try {
			String newPsw = Password.getPassword();
			NotificacionService notificacionService = new NotificacionService();
			notificacionService.notificacionRecuperacionPsw(empresaVO, correoElectronico, newPsw);
		} catch (MailException e) {
			throw e;
		} catch(Exception e){
			throw new TechnicalException(e);
		}
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<EmpresaVO> consultaEmpresas(int idTipoPersona, String correoElectronico, String idPortalEmpleo,
			                                String nombre, String apellido1, String razonSocial, String fechaActa,
			                                String apellido2, Long idEmpresa, String contacto, String telefono, String domicilio, long idEntidad, long idMunicipio,	String usuario
											) {

		List<EmpresaVO> empresas = empresaFacade.consultaEmpresas(idTipoPersona, correoElectronico, idPortalEmpleo, nombre, apellido1, razonSocial, fechaActa, 
				apellido2, idEmpresa, contacto, telefono, domicilio, idEntidad,  idMunicipio, usuario);
		return empresas;
	}
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public void desactivarEmpresa(long idEmpresa, long idUsuarioEmpresa, long idUsuarioBitacora, int idMotivoDesactivacion, 
			String detalleDesactivacion){
		
		empresaFacade.desactivarEmpresa(idEmpresa, idMotivoDesactivacion, detalleDesactivacion);
		usuarioFacade.inactivarUsuarioPorSolicitud(idUsuarioEmpresa, Constantes.ESTATUS.ELIMINADA_ADMIN.getIdOpcion());

		List<OfertaEmpleoVO> ofertasActivas = ofertaEmpleoFacade.consultaOfertasEmpleo(idEmpresa, ESTATUS.ACTIVO.getIdOpcion());
		
		//SearchService serviceDesindexador = SearchService.getInstance();
		
		try{
			
			for (OfertaEmpleoVO oferta : ofertasActivas){
				String nombreEmpresa = oferta.getNombreEmpresa();
				String tituloOferta = oferta.getTituloOferta();
//FIXME OracleText
/*
				try {
					IndexerServiceLocator.getIndexerServiceRemote().eliminaOfertaindice(oferta.getIdOfertaEmpleo());
				} catch (Exception e) {
				    e.printStackTrace();
				}
 */
				//serviceDesindexador.eliminaVacante(oferta.getIdOfertaEmpleo());					
				ofertaEmpleoFacade.actualizaEstatus(oferta.getIdOfertaEmpleo(), ESTATUS.ELIMINADA_ADMIN.getIdOpcion());
				entrevistaFacade.cancelaEntrevistas(oferta.getIdOfertaEmpleo(), ESTATUS.CANCELADA, new Date(), ESTATUS.NUEVA, ESTATUS.ACEPTADA, ESTATUS.REPROGRAMADA);
								
				notificarPostulantes(oferta.getIdOfertaEmpleo(), nombreEmpresa, tituloOferta);				
			}				
			
		} catch(Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		List<OfertaEmpleoVO> ofertasPendientesPorPublicar = ofertaEmpleoFacade.consultaOfertasEmpleo(idEmpresa, ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion());
		for (OfertaEmpleoVO oferta : ofertasPendientesPorPublicar){
			ofertaEmpleoFacade.actualizaEstatus(oferta.getIdOfertaEmpleo(), ESTATUS.ELIMINADA_ADMIN.getIdOpcion());

//			try{IndexerServiceLocator.getIndexerServiceRemote().eliminaOfertaindice(oferta.getIdOfertaEmpleo());} catch(Exception e){e.printStackTrace();}
		}						

		registroBitacora(EVENTO.DESACTIVAR_EMPRESA, idUsuarioBitacora, EVENTO.DESACTIVAR_EMPRESA.getEvento(),
				 		 "Desactivando una Empresa "+ detalleDesactivacion,
				 		 idEmpresa, TIPO_PROPIETARIO.EMPRESA);
	}
	
	public void notificarPostulantes(long idOfertaEmpleo, String nombreEmpresa, String tituloOferta){
		List<CandidatoVo> postulados;
		CandidatoDAO candidatoDAO = new CandidatoDAO();
		NotificacionService notificacionService = new NotificacionService();
		
		try {					
			postulados = candidatoDAO.consultaPostulantesEmpresaDesactivada(idOfertaEmpleo);
			
			if(null != postulados && !postulados.isEmpty()){
				
				for (CandidatoVo postulado : postulados){
										
					if(null != postulado.getCorreoElectronico() && !postulado.getCorreoElectronico().equalsIgnoreCase("")){
					
						String nombreCompletoCandidato = postulado.getNombre() + " " + postulado.getApellido1();
						if(null!=postulado.getApellido2() && !postulado.getApellido2().equalsIgnoreCase(""))
							nombreCompletoCandidato = nombreCompletoCandidato + " " + postulado.getApellido2();												
						
						notificacionService.notificacionCandidatoEmpresaDesactivada(nombreEmpresa, tituloOferta, nombreCompletoCandidato, postulado.getCorreoElectronico());
					}
				}				
			}
								
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (MailException e1) {
			logger.error(e1);
		}			
	}
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public void reactivarEmpresa(long idEmpresa, long idUsuarioEmpresa, long idUsuarioBitacora){		
				
		EmpresaVO empresaVo = empresaFacade.findById(idEmpresa);
		Date fechaDesactivacion = empresaVo.getFechaUltimaDesactivacion();
		
		List<OfertaEmpleoVO> ofertasPorReactivar = 
				ofertaEmpleoFacade.obtenerOfertasVigentesEliminadasFecha(idEmpresa, ESTATUS.ELIMINADA_ADMIN.getIdOpcion(), fechaDesactivacion, fechaDesactivacion);
		/* SOLO PARA PRUEBA
		if(null != ofertasPorReactivar && !ofertasPorReactivar.isEmpty()){
			Iterator<OfertaEmpleoVO> it = ofertasPorReactivar.iterator();
			while(it.hasNext()){
				OfertaEmpleoVO vo = (OfertaEmpleoVO)it.next();
				logger.info("----oferta por reactivar:" + vo.getIdOfertaEmpleo());
				System.out.println("----oferta por reactivar:" + vo.getIdOfertaEmpleo());
			}
		}
		*/
		if(null != ofertasPorReactivar && !ofertasPorReactivar.isEmpty()){
			
			for (OfertaEmpleoVO oferta : ofertasPorReactivar){
				ofertaEmpleoFacade.actualizaEstatus(oferta.getIdOfertaEmpleo(), ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion());			
				//Si la oferta no se coloca en estatus Pendiente por publicar antes de ejecutar el metodo registraOfertaPorValidar,
				//no se generará el registro por validar			
				autorizacionAppService.registraOfertaPorValidar(oferta.getIdOfertaEmpleo(), idEmpresa);			
			}					
		}		
		usuarioFacade.activarUsuario(idUsuarioEmpresa);
		empresaFacade.reactivarEmpresa(idEmpresa);
		
		registroBitacora(EVENTO.REACTIVAR_EMPRESA, idUsuarioBitacora, EVENTO.REACTIVAR_EMPRESA.getEvento(),
				 "Reactivando una Empresa ", idEmpresa, TIPO_PROPIETARIO.EMPRESA);
				 	
	}
	
	/**
	 * Recupera una Empresa que fue eliminada por el administrador
	 * Se actualiza el estatus a Pendiente por Publicar y se registra en la cola de espera
	 * para su revision por un publicador,
	 * Se actualiza el estatus de las ofertas correspondientes a la empresa
	 * @param idEmpresa
	 */
	public EmpresaVO recuperaEmpresaEliminada(long idEmpresa){

		empresaFacade.actualizaEstatus(idEmpresa, ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion());

		autorizacionAppService.registraEmpresaPorValidar(idEmpresa);
		
		List<OfertaEmpleoVO> ofertas = ofertaEmpleoFacade.consultaOfertasEmpleo(idEmpresa,
				                                                                ESTATUS.ELIMINADA_ADMIN.getIdOpcion());

		for (OfertaEmpleoVO oferta : ofertas){
			ofertaEmpleoFacade.actualizaEstatus(oferta.getIdOfertaEmpleo(), ESTATUS.EMP_MODIFICADA.getIdOpcion());
		}
	
		EmpresaVO empresa = empresaFacade.findById(idEmpresa);
		return empresa;
	}

	/**
	 * Recupera una Empresa por Autorizar que fue eliminada por el administrador
	 * Se actualiza el estatus a Pendiente por Publicar y se registra en la cola de espera
	 * para su revision por un publicador
	 * @param idEmpresa
	 */	
	public EmpresaPorAutorizarVO recuperaEmpresaPorAutorizarEliminada(long idEmpresa){
		
		empresaPorAutorizarFacade.actualizaEstatus(idEmpresa, ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion());

		autorizacionAppService.registraEmpresaPorValidar(idEmpresa);
		
		EmpresaPorAutorizarVO empresa = empresaPorAutorizarFacade.findById(idEmpresa);
		return empresa;
	}
	
	public List<BitacoraVO> consultaMovimientos(long idEmpresa){
		int numregistros = PropertiesLoader.getInstance().getPropertyInt("emp.buscador.limite.movimientos");
		if (numregistros<=0) numregistros = 5;
		
		List<BitacoraVO> movimientos = bitacoraFacade.consultaBitacora(idEmpresa, TIPO_PROPIETARIO.EMPRESA, numregistros);
		return movimientos;
	}

	@Override
	public String obtenerCapacitacionMixta(long idEmpresa) throws BusinessException {
		// TODO Auto-generated method stub
		return empresaCapMixtaFacadeLocal.obtenerCapacitacionMixta(idEmpresa);
	}

	@Override
	public Long salvarCapacitacionMixta(String texto,Long idEmpresa)	throws BusinessException {
		Long idEmpresaR;
		if(texto == null || texto.trim().isEmpty()){
			throw new BusinessException("No se introdujo ningún texto por parte del usuario");
		}else{
			idEmpresaR = empresaCapMixtaFacadeLocal.save(texto, idEmpresa);
		}
		return idEmpresaR;
	}

	@Override
	public Long updateCapacitacionMixta(String texto, Long idEmpresa) throws BusinessException {
		if(texto == null || texto.trim().isEmpty())
			throw new BusinessException("No se introdujo ningún texto por parte del usuario");
		
		if(idEmpresa == null || idEmpresa == 0)
			throw new BusinessException("El idEmpresa no es validos o es nulo");
		
		return empresaCapMixtaFacadeLocal.updateCapacitacionMixta(texto, idEmpresa);
	}

	public boolean esCorreoUnico(String correoElectronico) {
		boolean unico = usuarioFacade.esCorreoUnico(correoElectronico);
		return unico;
	}

	public boolean esUsuarioUnico(String usuario) {
		boolean unico = usuarioFacade.esUsuarioUnico(usuario);
		return unico;
	}
	
	public boolean tieneOfertas(String correoElectronico){
		boolean tieneOfertas = false;
		EmpresaVO empresaVo = empresaFacade.consultaEmpresaPorCorreo(correoElectronico);
		
		if (empresaVo!=null){
			List<OfertaEmpleoVO> lstOfertas = ofertaEmpleoFacade.ofertaEmpleosList(empresaVo.getIdEmpresa());
			if(null!=lstOfertas && !lstOfertas.isEmpty()){
				if(lstOfertas.size()>0){
					tieneOfertas = true;
				}
			}
		}
		
		return tieneOfertas;
	}
	
	public long regeneraEmpresa(RegistroEmpresaVO registroEmpresaVO) throws CorreoRepetidoException, TechnicalException {
		long idEmpresa = 0;
		String detalle = "";
		String idPortalEmpleo = null;
		
		try{
			
			//obtener los datos anteriores y hacer update		
			Calendar fecha = Calendar.getInstance();			
			UsuarioVO oldUser = usuarioFacade.findByUsuario(registroEmpresaVO.getCorreoElectronico());
			long idUsuario = oldUser.getIdUsuario();
			EmpresaVO oldEmpresa = empresaFacade.findByIdUsuario(oldUser.getIdUsuario());
			idEmpresa = oldEmpresa.getIdEmpresa();		
			
			//usuario
			usuarioFacade.updateEmail(idUsuario, registroEmpresaVO.getCorreoElectronico());		
			usuarioFacade.updatePassword(idUsuario, Password.codificaPassword(registroEmpresaVO.getContrasena()));	
			
			//empresa
			oldEmpresa.setIdUsuario(idUsuario);
			
			oldEmpresa.setIdFuente((long) Catalogos.APLICACION.PORTAL_DEL_EMPLEO.getIdOpcion());
			oldEmpresa.setIdOficina((long) Catalogos.APLICACION.PORTAL_DEL_EMPLEO.getIdOpcion());
						
			oldEmpresa.setCorreoElectronico(registroEmpresaVO.getCorreoElectronico());
			
			oldEmpresa.setIdTipoEmpresa(registroEmpresaVO.getIdTipoEmpresa());
			oldEmpresa.setIdTipoPersona(registroEmpresaVO.getIdTipoPersona());
			
			oldEmpresa.setNombre(registroEmpresaVO.getNombre());
			oldEmpresa.setApellido1(registroEmpresaVO.getApellido1());
			oldEmpresa.setApellido2(registroEmpresaVO.getApellido2());
			oldEmpresa.setFechaNacimiento(registroEmpresaVO.getFechaNacimiento());
			oldEmpresa.setRazonSocial(registroEmpresaVO.getRazonSocial());
			oldEmpresa.setFechaActa(registroEmpresaVO.getFechaActa());
			
			oldEmpresa.setCurpPF(registroEmpresaVO.getCurp());
			if(null!=registroEmpresaVO.getCurp()){
				oldEmpresa.setCurpValidada((short) Catalogos.DECISION.SI.getIdOpcion().intValue());
			}				
			
			oldEmpresa.setNombreComercial(registroEmpresaVO.getNombreComercial());
			oldEmpresa.setRfc(registroEmpresaVO.getRfc());
			oldEmpresa.setContactoEmpresa(registroEmpresaVO.getContactoEmpresa());
			oldEmpresa.setDescripcion(registroEmpresaVO.getDescripcion());
			oldEmpresa.setFechaUltimaActualizacion(fecha.getTime());
			oldEmpresa.setFechaConfirma(fecha.getTime());
			oldEmpresa.setIdActividadEconomica(registroEmpresaVO.getIdActividadEconomica());
			oldEmpresa.setIdMedio(registroEmpresaVO.getIdMedio());			
			oldEmpresa.setNumeroEmpleados(registroEmpresaVO.getNumeroEmpleados());
			oldEmpresa.setPaginaWeb(registroEmpresaVO.getPaginaWeb());
			oldEmpresa.setEstatus(ESTATUS.ACTIVO.getIdOpcion());
			
			int confidencialidadDatos = CONFIDENCIALIDAD.NO.getIdOpcion();
			if (registroEmpresaVO.getConfidencial()==1){
				confidencialidadDatos = CONFIDENCIALIDAD.SI.getIdOpcion();
			}			
			oldEmpresa.setConfidencial(confidencialidadDatos);
			
			int veracidadDatos = CONFIDENCIALIDAD.NO.getIdOpcion();
			int aceptacionTerminos = CONFIDENCIALIDAD.NO.getIdOpcion();
	
			if (registroEmpresaVO.getAceptacionTerminos()==1){
				veracidadDatos = CONFIDENCIALIDAD.SI.getIdOpcion();
				aceptacionTerminos = CONFIDENCIALIDAD.SI.getIdOpcion();				
			}
	
			oldEmpresa.setAseguraDatos		(veracidadDatos);
			oldEmpresa.setAceptacionTerminos	(aceptacionTerminos);	
			
			long tamanioEmpresa  = calculaTamanioEmpresa(registroEmpresaVO.getIdActividadEconomica(), registroEmpresaVO.getNumeroEmpleados());
			oldEmpresa.setIdTamanio(tamanioEmpresa);
			
			
			if(registroEmpresaVO.getIdTipoEmpresa() == Catalogos.TIPO_EMPRESA.PRIVADA.getTipoEmpresa()
					&& registroEmpresaVO.getIdTipoPersona() == (long)Catalogos.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()){
				
				idPortalEmpleo = registroEmpresaVO.getCurp();
				
			} else {
				idPortalEmpleo = empresaFacade.generaIDPortalEmpleo(oldEmpresa, registroEmpresaVO.getCodigoPostal());
				
			}										
			oldEmpresa.setIdPortalEmpleo(idPortalEmpleo);
			
			empresaFacade.updateFull(oldEmpresa);
			
			//domicilio
			DomicilioVO domicilio = domicilioFacade.buscarDomicilioIdPropietario(oldEmpresa.getIdEmpresa(), TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
			domicilio.setIdPropietario     (idEmpresa);
			domicilio.setIdTipoPropietario (TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
			domicilio.setCodigoPostal      (registroEmpresaVO.getCodigoPostal());
			domicilio.setIdEntidad         (registroEmpresaVO.getIdEntidad());
			domicilio.setIdMunicipio       (registroEmpresaVO.getIdMunicipio());
			domicilio.setIdColonia         (registroEmpresaVO.getIdColonia());
			domicilio.setNumeroExterior    (registroEmpresaVO.getNumeroExterior());
			domicilio.setNumeroInterior    (registroEmpresaVO.getNumeroInterior());
			domicilio.setCalle             (registroEmpresaVO.getCalle());
			domicilio.setEntreCalle        (registroEmpresaVO.getEntreCalle());
			domicilio.setyCalle            (registroEmpresaVO.getyCalle());
			domicilio.setLatitud		   (registroEmpresaVO.getLatitud());
			domicilio.setLongitud		   (registroEmpresaVO.getLongitud());
			domicilio.setFechaModificacion(fecha.getTime());
			domicilioFacade.update(domicilio);				
			//telefonos
			//borrar telefonos anteriores
			List<TelefonoVO> lstTelefonosOld = telefonoFacade.getTelefonosPropietario(idEmpresa, TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
			telefonoFacade.deleteAll(idEmpresa, TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario(), MULTIREGISTRO.PRINCIPAL.getIdOpcion());
			telefonoFacade.deleteAll(idEmpresa, TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario(), MULTIREGISTRO.ADICIONAL.getIdOpcion());
			//guardar nuevo telefono principal
			TelefonoVO telPrincipal = new TelefonoVO();
			telPrincipal.setIdPropietario(idEmpresa);
			telPrincipal.setIdTipoPropietario	(TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
			telPrincipal.setIdTipoTelefono		(registroEmpresaVO.getTipoTelefono());
			telPrincipal.setAcceso				(registroEmpresaVO.getAcceso());
			telPrincipal.setClave				(registroEmpresaVO.getClave());
			telPrincipal.setTelefono			(registroEmpresaVO.getTelefono());
			telPrincipal.setExtension			(registroEmpresaVO.getExtension());		
			telPrincipal.setPrincipal			(MULTIREGISTRO.PRINCIPAL.getIdOpcion());
			telPrincipal.setFechaAlta			(fecha.getTime());	
			System.out.println("----guardando: " + telPrincipal.toString());
			telefonoFacade.save(telPrincipal);			
			//guardar nuevos telefonos adicionales
			List<TelefonoVO> listaTelefonos = new ArrayList<TelefonoVO>();
			listaTelefonos = registroEmpresaVO.getListaTelefonos();
			Iterator<TelefonoVO> itTelefonos = listaTelefonos.iterator();
			while(itTelefonos.hasNext()){
				TelefonoVO telvo = (TelefonoVO)itTelefonos.next();
				TelefonoVO temp = new TelefonoVO();				
				temp.setIdPropietario(idEmpresa);
				temp.setIdTipoPropietario	(TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
				temp.setIdTipoTelefono		(telvo.getIdTipoTelefono());
				temp.setAcceso				(telvo.getAcceso());
				temp.setClave				(telvo.getClave());
				temp.setTelefono			(telvo.getTelefono());
				temp.setExtension			(telvo.getExtension());
				temp.setPrincipal			(MULTIREGISTRO.ADICIONAL.getIdOpcion());
				temp.setFechaAlta			(fecha.getTime());		

				telefonoFacade.save(temp);
			}		
			registroBitacora(EVENTO.REGENERACION_CUENTA_EMPRESA, idUsuario,
					 EVENTO.REGENERACION_CUENTA_EMPRESA.getEvento(),
					 "Regenerando una Empresa con correo "+ registroEmpresaVO.getCorreoElectronico(),
					 registroEmpresaVO.getIdEmpresa(), TIPO_PROPIETARIO.EMPRESA);					
		
		} catch(PersistenceException e){
			e.printStackTrace(); logger.error(e);
			throw e;
		} catch (Exception e) {
			e.printStackTrace(); logger.error(e);
			throw new TechnicalException(e);
		}		
		return idEmpresa;
	}	
	
	public long regeneraEmpresa(RegistroEmpresaVO registroEmpresaVO, long idUsuarioRegistra) throws CorreoRepetidoException, TechnicalException {
		long idEmpresa = 0;
		String detalle = "";
		try{
			//obtener los datos anteriores y hacer update		
			Calendar fecha = Calendar.getInstance();			
			UsuarioVO oldUser = usuarioFacade.findByUsuario(registroEmpresaVO.getCorreoElectronico());
			long idUsuario = oldUser.getIdUsuario();
			EmpresaVO oldEmpresa = empresaFacade.findByIdUsuario(oldUser.getIdUsuario());
			idEmpresa = oldEmpresa.getIdEmpresa();		
			//usuario
			usuarioFacade.updateEmail(idUsuario, registroEmpresaVO.getCorreoElectronico());		
			usuarioFacade.updatePassword(idUsuario, Password.codificaPassword(registroEmpresaVO.getContrasena()));	
			//empresa
			oldEmpresa.setIdUsuario(idUsuario);
			oldEmpresa.setCorreoElectronico(registroEmpresaVO.getCorreoElectronico());
			oldEmpresa.setIdTipoEmpresa(registroEmpresaVO.getIdTipoEmpresa());
			oldEmpresa.setIdTipoPersona(registroEmpresaVO.getIdTipoPersona());
			oldEmpresa.setNombre(registroEmpresaVO.getNombre());
			oldEmpresa.setApellido1(registroEmpresaVO.getApellido1());
			oldEmpresa.setApellido2(registroEmpresaVO.getApellido2());
			oldEmpresa.setFechaNacimiento(registroEmpresaVO.getFechaNacimiento());
			oldEmpresa.setRazonSocial(registroEmpresaVO.getRazonSocial());
			oldEmpresa.setFechaActa(registroEmpresaVO.getFechaActa());
			oldEmpresa.setNombreComercial(registroEmpresaVO.getNombreComercial());
			oldEmpresa.setRfc(registroEmpresaVO.getRfc());
			oldEmpresa.setContactoEmpresa(registroEmpresaVO.getContactoEmpresa());
			oldEmpresa.setDescripcion(registroEmpresaVO.getDescripcion());
			oldEmpresa.setFechaUltimaActualizacion(fecha.getTime());
			oldEmpresa.setFechaConfirma(fecha.getTime());
			oldEmpresa.setIdActividadEconomica(registroEmpresaVO.getIdActividadEconomica());
			oldEmpresa.setIdMedio(registroEmpresaVO.getIdMedio());
			oldEmpresa.setIdTipoEmpresa(registroEmpresaVO.getIdTipoEmpresa());
			oldEmpresa.setNumeroEmpleados(registroEmpresaVO.getNumeroEmpleados());
			oldEmpresa.setPaginaWeb(registroEmpresaVO.getPaginaWeb());
			oldEmpresa.setEstatus(ESTATUS.ACTIVO.getIdOpcion());
			int confidencialidadDatos = CONFIDENCIALIDAD.NO.getIdOpcion();
			if (registroEmpresaVO.getConfidencial()==1){
				confidencialidadDatos = CONFIDENCIALIDAD.SI.getIdOpcion();
			}			
			oldEmpresa.setConfidencial(confidencialidadDatos);
			
			int veracidadDatos = CONFIDENCIALIDAD.NO.getIdOpcion();
			int aceptacionTerminos = CONFIDENCIALIDAD.NO.getIdOpcion();
	
			if (registroEmpresaVO.getAceptacionTerminos()==1){
				veracidadDatos = CONFIDENCIALIDAD.SI.getIdOpcion();
				aceptacionTerminos = CONFIDENCIALIDAD.SI.getIdOpcion();				
			}
	
			oldEmpresa.setAseguraDatos		(veracidadDatos);
			oldEmpresa.setAceptacionTerminos	(aceptacionTerminos);	
			
			String idPortalEmpleo = empresaFacade.generaIDPortalEmpleo(oldEmpresa, registroEmpresaVO.getCodigoPostal());
			//logger.info("----idPortalEmpleo:" + idPortalEmpleo);
			oldEmpresa.setIdPortalEmpleo(idPortalEmpleo);		
			
			empresaFacade.updateFull(oldEmpresa);
			//domicilio
			DomicilioVO domicilio = domicilioFacade.buscarDomicilioIdPropietario(oldEmpresa.getIdEmpresa(), TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
			domicilio.setIdPropietario     (idEmpresa);
			domicilio.setIdTipoPropietario (TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
			domicilio.setCodigoPostal      (registroEmpresaVO.getCodigoPostal());
			domicilio.setIdEntidad         (registroEmpresaVO.getIdEntidad());
			domicilio.setIdMunicipio       (registroEmpresaVO.getIdMunicipio());
			domicilio.setIdColonia         (registroEmpresaVO.getIdColonia());
			domicilio.setNumeroExterior    (registroEmpresaVO.getNumeroExterior());
			domicilio.setNumeroInterior    (registroEmpresaVO.getNumeroInterior());
			domicilio.setCalle             (registroEmpresaVO.getCalle());
			domicilio.setEntreCalle        (registroEmpresaVO.getEntreCalle());
			domicilio.setyCalle            (registroEmpresaVO.getyCalle());
			domicilio.setLatitud		   (registroEmpresaVO.getLatitud());
			domicilio.setLongitud		   (registroEmpresaVO.getLongitud());
			domicilio.setFechaModificacion(fecha.getTime());
			domicilioFacade.update(domicilio);				
			//telefonos
			//borrar telefonos anteriores
			List<TelefonoVO> lstTelefonosOld = telefonoFacade.getTelefonosPropietario(idEmpresa, TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
			telefonoFacade.deleteAll(idEmpresa, TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario(), MULTIREGISTRO.PRINCIPAL.getIdOpcion());
			telefonoFacade.deleteAll(idEmpresa, TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario(), MULTIREGISTRO.ADICIONAL.getIdOpcion());
			//guardar nuevo telefono principal
			TelefonoVO telPrincipal = new TelefonoVO();
			telPrincipal.setIdPropietario(idEmpresa);
			telPrincipal.setIdTipoPropietario	(TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
			telPrincipal.setIdTipoTelefono		(registroEmpresaVO.getTipoTelefono());
			telPrincipal.setAcceso				(registroEmpresaVO.getAcceso());
			telPrincipal.setClave				(registroEmpresaVO.getClave());
			telPrincipal.setTelefono			(registroEmpresaVO.getTelefono());
			telPrincipal.setExtension			(registroEmpresaVO.getExtension());		
			telPrincipal.setPrincipal			(MULTIREGISTRO.PRINCIPAL.getIdOpcion());
			telPrincipal.setFechaAlta			(fecha.getTime());	
			System.out.println("----guardando: " + telPrincipal.toString());
			telefonoFacade.save(telPrincipal);			
			//guardar nuevos telefonos adicionales
			List<TelefonoVO> listaTelefonos = new ArrayList<TelefonoVO>();
			listaTelefonos = registroEmpresaVO.getListaTelefonos();
			Iterator<TelefonoVO> itTelefonos = listaTelefonos.iterator();
			while(itTelefonos.hasNext()){
				TelefonoVO telvo = (TelefonoVO)itTelefonos.next();
				TelefonoVO temp = new TelefonoVO();				
				temp.setIdPropietario(idEmpresa);
				temp.setIdTipoPropietario	(TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
				temp.setIdTipoTelefono		(telvo.getIdTipoTelefono());
				temp.setAcceso				(telvo.getAcceso());
				temp.setClave				(telvo.getClave());
				temp.setTelefono			(telvo.getTelefono());
				temp.setExtension			(telvo.getExtension());
				temp.setPrincipal			(MULTIREGISTRO.ADICIONAL.getIdOpcion());
				temp.setFechaAlta			(fecha.getTime());		
				System.out.println("----guardando: " + temp.toString());
				telefonoFacade.save(temp);
			}		
			registroBitacora(EVENTO.REGENERACION_CUENTA_EMPRESA, idUsuarioRegistra,
					 EVENTO.REGENERACION_CUENTA_EMPRESA.getEvento(),
					 "Regenerando una Empresa con correo "+ registroEmpresaVO.getCorreoElectronico(),
					 registroEmpresaVO.getIdEmpresa(), TIPO_PROPIETARIO.EMPRESA);				
		
		} catch(PersistenceException e){
			e.printStackTrace(); logger.error(e);
			throw e;
		} catch (Exception e) {
			e.printStackTrace(); logger.error(e);
			throw new TechnicalException(e);
		}		
		return idEmpresa;

	}

	public long registraEmpresa(RegistroEmpresaVO registroEmpresaVO, long idUsuarioRegistra) throws CorreoRepetidoException, TechnicalException {
		long idEmpresa = 0;
		String detalle = "";
		/*if(!usuarioFacade.esUsuarioUnico(registroEmpresaVO.getUsuario()))
			throw new LoginRepetidoException(registroEmpresaVO.getUsuario()); */
		if(!usuarioFacade.esCorreoUnico(registroEmpresaVO.getCorreoElectronico()))
			throw new CorreoRepetidoException(registroEmpresaVO.getCorreoElectronico());
		try{
			Calendar fecha = Calendar.getInstance();
			//--------------------------------------------------------------------------------------------			
			UsuarioVO usuarioVO = new UsuarioVO();
			usuarioVO.setUsuario			(registroEmpresaVO.getCorreoElectronico());
			usuarioVO.setContrasena        	(Password.codificaPassword(registroEmpresaVO.getContrasena()));
			usuarioVO.setCorreoElectronico	(registroEmpresaVO.getCorreoElectronico());
			usuarioVO.setFechaAlta		   	(fecha.getTime());
			usuarioVO.setFechaModificacion 	(fecha.getTime());
			usuarioVO.setEstatus           	(ESTATUS.ACTIVO.getIdOpcion());
			usuarioVO.setIdPerfil      	   	(PERFIL.EMPRESA.getIdOpcion());		
			usuarioVO.setIdRegistro        	(ID_REGISTRO_PORTAL);
			usuarioVO.setIdTipoUsuario     	(TIPO_USUARIO.EMPRESA.getIdTipoUsuario());
			usuarioVO.setNombre				(registroEmpresaVO.getNombre());
			usuarioVO.setApellido1			(registroEmpresaVO.getApellido1());
			usuarioVO.setApellido2			(registroEmpresaVO.getApellido2());
			long idUsuario = usuarioFacade.save(usuarioVO);		
			
			EmpresaVO empresaVo = new EmpresaVO();
			empresaVo.setIdUsuario(idUsuario);
			empresaVo.setCorreoElectronico(registroEmpresaVO.getCorreoElectronico());
			empresaVo.setIdTipoEmpresa(registroEmpresaVO.getIdTipoEmpresa());
			empresaVo.setIdTipoPersona(registroEmpresaVO.getIdTipoPersona());
			empresaVo.setNombre(registroEmpresaVO.getNombre());
			empresaVo.setApellido1(registroEmpresaVO.getApellido1());
			empresaVo.setApellido2(registroEmpresaVO.getApellido2());
			//System.out.println("------registroEmpresaVO.getFechaNacimiento():" + registroEmpresaVO.getFechaNacimiento());
			empresaVo.setFechaNacimiento(registroEmpresaVO.getFechaNacimiento());
			empresaVo.setRazonSocial(registroEmpresaVO.getRazonSocial());
			empresaVo.setFechaActa(registroEmpresaVO.getFechaActa());
			//System.out.println("------registroEmpresaVO.getFechaActa():" + registroEmpresaVO.getFechaActa());
			empresaVo.setNombreComercial(registroEmpresaVO.getNombreComercial());
			empresaVo.setRfc(registroEmpresaVO.getRfc());
			empresaVo.setContactoEmpresa(registroEmpresaVO.getContactoEmpresa());
			empresaVo.setDescripcion(registroEmpresaVO.getDescripcion());
			empresaVo.setFechaAlta(fecha.getTime());
			empresaVo.setFechaUltimaActualizacion(fecha.getTime());
			empresaVo.setFechaConfirma(fecha.getTime());
			empresaVo.setIdActividadEconomica(registroEmpresaVO.getIdActividadEconomica());
			empresaVo.setIdMedio(registroEmpresaVO.getIdMedio());
			empresaVo.setIdTipoEmpresa(registroEmpresaVO.getIdTipoEmpresa());
			empresaVo.setNumeroEmpleados(registroEmpresaVO.getNumeroEmpleados());
			empresaVo.setPaginaWeb(registroEmpresaVO.getPaginaWeb());
			empresaVo.setEstatus(ESTATUS.ACTIVO.getIdOpcion());
			int confidencialidadDatos = CONFIDENCIALIDAD.NO.getIdOpcion();
			if (registroEmpresaVO.getConfidencial()==1){
				confidencialidadDatos = CONFIDENCIALIDAD.SI.getIdOpcion();
			}			
			empresaVo.setConfidencial(confidencialidadDatos);
			
			int veracidadDatos = CONFIDENCIALIDAD.NO.getIdOpcion();
			int aceptacionTerminos = CONFIDENCIALIDAD.NO.getIdOpcion();

			if (registroEmpresaVO.getAceptacionTerminos()==1){
				veracidadDatos = CONFIDENCIALIDAD.SI.getIdOpcion();
				aceptacionTerminos = CONFIDENCIALIDAD.SI.getIdOpcion();				
			}

			empresaVo.setAseguraDatos		(veracidadDatos);
			empresaVo.setAceptacionTerminos	(aceptacionTerminos);	
			
			String idPortalEmpleo = empresaFacade.generaIDPortalEmpleo(empresaVo, registroEmpresaVO.getCodigoPostal());
			//logger.info("----idPortalEmpleo:" + idPortalEmpleo);
			empresaVo.setIdPortalEmpleo(idPortalEmpleo);
			empresaFacade.save(empresaVo);			
			//--------------------------------------------------------------------------------------------			
			DomicilioVO domicilio = new DomicilioVO();
			domicilio.setIdPropietario     (idEmpresa);
			domicilio.setIdTipoPropietario (TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
			domicilio.setCodigoPostal      (registroEmpresaVO.getCodigoPostal());
			domicilio.setIdEntidad         (registroEmpresaVO.getIdEntidad());
			domicilio.setIdMunicipio       (registroEmpresaVO.getIdMunicipio());
			domicilio.setIdColonia         (registroEmpresaVO.getIdColonia());
			domicilio.setNumeroExterior    (registroEmpresaVO.getNumeroExterior());
			domicilio.setNumeroInterior    (registroEmpresaVO.getNumeroInterior());
			domicilio.setCalle             (registroEmpresaVO.getCalle());
			domicilio.setEntreCalle        (registroEmpresaVO.getEntreCalle());
			domicilio.setyCalle            (registroEmpresaVO.getyCalle());
			domicilio.setFechaAlta         (fecha.getTime());
			domicilio.setLatitud		   (registroEmpresaVO.getLatitud());
			domicilio.setLongitud		   (registroEmpresaVO.getLongitud());
			domicilioFacade.save(domicilio);						
			//--------------------------------------------------------------------------------------------
			List<TelefonoVO> listaTelefonos = new ArrayList<TelefonoVO>();
			listaTelefonos = registroEmpresaVO.getListaTelefonos();
			Iterator<TelefonoVO> itTelefonos = listaTelefonos.iterator();
			while(itTelefonos.hasNext()){
				TelefonoVO telvo = (TelefonoVO)itTelefonos.next();
				TelefonoVO temp = new TelefonoVO();				
				temp.setIdPropietario(idEmpresa);
				temp.setIdTipoPropietario	(TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
				temp.setIdTipoTelefono		(telvo.getIdTipoTelefono());
				temp.setAcceso				(telvo.getAcceso());
				temp.setClave				(telvo.getClave());
				temp.setTelefono			(telvo.getTelefono());
				temp.setExtension			(telvo.getExtension());
				if(telvo.getIdTelefono()==0){
					temp.setPrincipal			(MULTIREGISTRO.PRINCIPAL.getIdOpcion());
				} else {
					temp.setPrincipal			(MULTIREGISTRO.ADICIONAL.getIdOpcion());
				}					
				temp.setFechaAlta			(fecha.getTime());		
				//System.out.println("----guardando: " + temp.toString());
				telefonoFacade.save(temp);
			}
			registroBitacora(EVENTO.REGISTRO_EMPRESAS, idUsuarioRegistra,
							 EVENTO.REGISTRO_EMPRESAS.getEvento(),
							 "Registrando una nueva Empresa con correo "+ registroEmpresaVO.getCorreoElectronico(),
							 registroEmpresaVO.getIdEmpresa(), TIPO_PROPIETARIO.EMPRESA);		
						
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
		return idEmpresa;
		
	}
	

	public long registraEmpresa(RegistroEmpresaVO registroEmpresaVO) throws CorreoRepetidoException, TechnicalException {
		long idEmpresa = 0;
		String detalle = "";
		String idPortalEmpleo = null;
		/*if(!usuarioFacade.esUsuarioUnico(registroEmpresaVO.getUsuario()))
			throw new LoginRepetidoException(registroEmpresaVO.getUsuario()); */
		if(!usuarioFacade.esCorreoUnico(registroEmpresaVO.getCorreoElectronico()))
			throw new CorreoRepetidoException(registroEmpresaVO.getCorreoElectronico());
		
		try{
			Calendar fecha = Calendar.getInstance();
			//--------------------------------------------------------------------------------------------			
			UsuarioVO usuarioVO = new UsuarioVO();
			usuarioVO.setUsuario			(registroEmpresaVO.getCorreoElectronico());
			//System.out.println("------usuarioVO.getUsuario():" + usuarioVO.getUsuario());
			usuarioVO.setContrasena        	(Password.codificaPassword(registroEmpresaVO.getContrasena()));
			usuarioVO.setCorreoElectronico	(registroEmpresaVO.getCorreoElectronico());
			//System.out.println("------usuarioVO.getCorreoElectronico():" + usuarioVO.getCorreoElectronico());
			usuarioVO.setFechaAlta		   	(fecha.getTime());
			usuarioVO.setFechaModificacion 	(fecha.getTime());
			usuarioVO.setEstatus           	(ESTATUS.ACTIVO.getIdOpcion());
			usuarioVO.setIdPerfil      	   	(PERFIL.EMPRESA.getIdOpcion());		
			usuarioVO.setIdRegistro        	(ID_REGISTRO_PORTAL);
			usuarioVO.setIdTipoUsuario     	(TIPO_USUARIO.EMPRESA.getIdTipoUsuario());
			usuarioVO.setNombre				(registroEmpresaVO.getNombre());
			usuarioVO.setApellido1			(registroEmpresaVO.getApellido1());
			usuarioVO.setApellido2			(registroEmpresaVO.getApellido2());
			long idUsuario = usuarioFacade.save(usuarioVO);		
			
			EmpresaVO empresaVo = new EmpresaVO();
			empresaVo.setIdUsuario(idUsuario);
			
			empresaVo.setIdFuente((long) Catalogos.APLICACION.PORTAL_DEL_EMPLEO.getIdOpcion());
			empresaVo.setIdOficina((long) Catalogos.APLICACION.PORTAL_DEL_EMPLEO.getIdOpcion());
			
			
			empresaVo.setCorreoElectronico(registroEmpresaVO.getCorreoElectronico());
			//System.out.println("------empresaVo.getCorreoElectronico():" + empresaVo.getCorreoElectronico());
			empresaVo.setIdTipoEmpresa(registroEmpresaVO.getIdTipoEmpresa());
			
			empresaVo.setIdTipoPersona(registroEmpresaVO.getIdTipoPersona());
			
			empresaVo.setNombre(registroEmpresaVO.getNombre());
			empresaVo.setApellido1(registroEmpresaVO.getApellido1());
			empresaVo.setApellido2(registroEmpresaVO.getApellido2());
			empresaVo.setFechaNacimiento(registroEmpresaVO.getFechaNacimiento());
			empresaVo.setRazonSocial(registroEmpresaVO.getRazonSocial());
			empresaVo.setFechaActa(registroEmpresaVO.getFechaActa());
			empresaVo.setCurpPF(registroEmpresaVO.getCurp());
			
			if(null!=registroEmpresaVO.getCurp()){
				empresaVo.setCurpValidada((short) Catalogos.DECISION.SI.getIdOpcion().intValue());
			}
			
			empresaVo.setNombreComercial(registroEmpresaVO.getNombreComercial());
			empresaVo.setRfc(registroEmpresaVO.getRfc());
			empresaVo.setContactoEmpresa(registroEmpresaVO.getContactoEmpresa());
			empresaVo.setCargoContacto(registroEmpresaVO.getCargoContacto());
			empresaVo.setIdTipoSociedad(registroEmpresaVO.getIdTipoSociedad());
			empresaVo.setDescripcion(registroEmpresaVO.getDescripcion());
			empresaVo.setFechaAlta(fecha.getTime());
			empresaVo.setFechaUltimaActualizacion(fecha.getTime());
			empresaVo.setFechaConfirma(fecha.getTime());
			empresaVo.setIdActividadEconomica(registroEmpresaVO.getIdActividadEconomica());
			empresaVo.setIdMedio(registroEmpresaVO.getIdMedio());			
			empresaVo.setNumeroEmpleados(registroEmpresaVO.getNumeroEmpleados());
			empresaVo.setPaginaWeb(registroEmpresaVO.getPaginaWeb());
			empresaVo.setEstatus(ESTATUS.ACTIVO.getIdOpcion());
			int confidencialidadDatos = CONFIDENCIALIDAD.NO.getIdOpcion();
			if (registroEmpresaVO.getConfidencial()==1){
				confidencialidadDatos = CONFIDENCIALIDAD.SI.getIdOpcion();
			}			
			empresaVo.setConfidencial(confidencialidadDatos);
			
			int veracidadDatos = CONFIDENCIALIDAD.SI.getIdOpcion();
			int aceptacionTerminos = CONFIDENCIALIDAD.SI.getIdOpcion();

			empresaVo.setAseguraDatos		(veracidadDatos);
			empresaVo.setAceptacionTerminos	(aceptacionTerminos);
			
			long tamanioEmpresa  = calculaTamanioEmpresa(empresaVo.getIdActividadEconomica(), empresaVo.getNumeroEmpleados());
			empresaVo.setIdTamanio(tamanioEmpresa);			
			
			idEmpresa = empresaFacade.obtenerIdEmpresa();
			empresaVo.setIdEmpresa(idEmpresa);			
			
			
			if(registroEmpresaVO.getIdTipoEmpresa() == Catalogos.TIPO_EMPRESA.PRIVADA.getTipoEmpresa()
					&& registroEmpresaVO.getIdTipoPersona() == (long)Catalogos.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()){
				
				idPortalEmpleo = registroEmpresaVO.getCurp();
				
			} else {
				
				idPortalEmpleo = empresaFacade.generaIDPortalEmpleo(empresaVo, registroEmpresaVO.getCodigoPostal());
				
			}			
			empresaVo.setIdPortalEmpleo(idPortalEmpleo);
			
			empresaFacade.save(empresaVo);	
			
			DomicilioVO domicilio = new DomicilioVO();
			domicilio.setIdPropietario     (idEmpresa);
			domicilio.setIdTipoPropietario (TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
			domicilio.setCodigoPostal      (registroEmpresaVO.getCodigoPostal());
			domicilio.setIdEntidad         (registroEmpresaVO.getIdEntidad());
			domicilio.setIdMunicipio       (registroEmpresaVO.getIdMunicipio());
			domicilio.setIdColonia         (registroEmpresaVO.getIdColonia());
			domicilio.setNumeroExterior    (registroEmpresaVO.getNumeroExterior());
			domicilio.setNumeroInterior    (registroEmpresaVO.getNumeroInterior());
			domicilio.setCalle             (registroEmpresaVO.getCalle());
			domicilio.setEntreCalle        (registroEmpresaVO.getEntreCalle());
			domicilio.setyCalle            (registroEmpresaVO.getyCalle());
			domicilio.setFechaAlta         (fecha.getTime());
			domicilio.setLatitud		   (registroEmpresaVO.getLatitud());
			domicilio.setLongitud		   (registroEmpresaVO.getLongitud());

			domicilioFacade.save(domicilio);
			
			//Guarda telefono principal
			TelefonoVO telPrinc = new TelefonoVO();
			telPrinc.setIdPropietario(idEmpresa);
			telPrinc.setIdTipoPropietario	(TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
			telPrinc.setIdTipoTelefono		(registroEmpresaVO.getTipoTelefono());
			telPrinc.setAcceso				(registroEmpresaVO.getAcceso());
			telPrinc.setClave				(registroEmpresaVO.getClave());
			telPrinc.setTelefono			(registroEmpresaVO.getTelefono());
			telPrinc.setExtension			(registroEmpresaVO.getExtension());
			telPrinc.setPrincipal			(MULTIREGISTRO.PRINCIPAL.getIdOpcion());
			telPrinc.setFechaAlta			(fecha.getTime());		
			telefonoFacade.save(telPrinc);
			//Guarda telefonos adicionales
			List<TelefonoVO> listaTelefonos = new ArrayList<TelefonoVO>();
			listaTelefonos = registroEmpresaVO.getListaTelefonos();
			Iterator<TelefonoVO> itTelefonos = listaTelefonos.iterator();
			while(itTelefonos.hasNext()){
				TelefonoVO telvo = (TelefonoVO)itTelefonos.next();
				TelefonoVO temp = new TelefonoVO();				
				temp.setIdPropietario(idEmpresa);
				temp.setIdTipoPropietario	(TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
				temp.setIdTipoTelefono		(telvo.getIdTipoTelefono());
				temp.setAcceso				(telvo.getAcceso());
				temp.setClave				(telvo.getClave());
				temp.setTelefono			(telvo.getTelefono());
				temp.setExtension			(telvo.getExtension());
				temp.setPrincipal			(MULTIREGISTRO.ADICIONAL.getIdOpcion());
				temp.setFechaAlta			(fecha.getTime());		
				telefonoFacade.save(temp);
			}
			
			registroBitacora(EVENTO.REGISTRO_EMPRESAS, ID_USUARIO_ANONIMO,
							 EVENTO.REGISTRO_EMPRESAS.getEvento(),
							 "Registrando una nueva Empresa con correo "+ registroEmpresaVO.getCorreoElectronico(),
							 registroEmpresaVO.getIdEmpresa(), TIPO_PROPIETARIO.EMPRESA);		
						
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
		return idEmpresa;		
	}
	
	public EmpresaVO actualizarEmpresa(EmpresaVO empresaVO, long idUsuario, String changedFields, boolean isChangedPassword,
			boolean isChangedEmail, boolean isChangedIdPortal) throws TechnicalException {
		
		String strPwd = "";
		String strPwdCdf = "";
		
		try{
			
			if(isChangedPassword){
				strPwd = empresaVO.getContrasena();
				try {
					strPwdCdf = Password.codificaPassword(strPwd);
				} catch (EncodingException e) {
					e.printStackTrace();
				}			
				usuarioFacade.updatePassword(empresaVO.getIdUsuario(), strPwdCdf);			
			}					

			Calendar fecha = Calendar.getInstance();
			empresaVO.setFechaUltimaActualizacion(fecha.getTime());			
			empresaVO.setEstatus(ESTATUS.ACTIVO.getIdOpcion());	
			
			long tamanioEmpresa  = calculaTamanioEmpresa(empresaVO.getIdActividadEconomica(), empresaVO.getNumeroEmpleados());
			empresaVO.setIdTamanio(tamanioEmpresa);			
			
			empresaFacade.update(empresaVO);
			DomicilioVO domicilio = new DomicilioVO();
			domicilio.setIdDomicilio     (empresaVO.getDomicilio().getIdDomicilio());
			domicilio.setIdPropietario     (empresaVO.getIdEmpresa());
			domicilio.setIdTipoPropietario (TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
			domicilio.setCodigoPostal      (empresaVO.getDomicilio().getCodigoPostal());
			domicilio.setIdEntidad         (empresaVO.getDomicilio().getIdEntidad());
			domicilio.setIdMunicipio       (empresaVO.getDomicilio().getIdMunicipio());
			domicilio.setIdColonia         (empresaVO.getDomicilio().getIdColonia());
			domicilio.setNumeroExterior    (empresaVO.getDomicilio().getNumeroExterior());
			domicilio.setNumeroInterior    (empresaVO.getDomicilio().getNumeroInterior());
			domicilio.setCalle             (empresaVO.getDomicilio().getCalle());
			domicilio.setEntreCalle        (empresaVO.getDomicilio().getEntreCalle());
			domicilio.setyCalle            (empresaVO.getDomicilio().getyCalle());
			domicilio.setLatitud		   (empresaVO.getDomicilio().getLatitud());
			domicilio.setLongitud		   (empresaVO.getDomicilio().getLongitud());
			domicilio.setFechaModificacion(fecha.getTime());

			DomicilioVO oldDomicilio = domicilioFacade.buscarDomicilioIdPropietario(empresaVO.getIdEmpresa(), TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
			if(null!=oldDomicilio){
				domicilioFacade.update(domicilio);
			} else {
				domicilioFacade.save(domicilio);
			}			
			//los telefonos se guardan desde EdicionEmpresaAction
			
			if(isChangedIdPortal){
				String nuevoIDPortal = empresaFacade.generaIDPortalEmpleo(empresaVO, empresaVO.getDomicilio().getCodigoPostal());
				empresaVO.setIdPortalEmpleo(nuevoIDPortal);
				empresaFacade.update(empresaVO);			
			}

			long idEvento = EVENTO.MODIFICA_EMPRESA.getIdEvento();
			String descripcionEvento = EVENTO.MODIFICA_EMPRESA.getEvento();		
			bitacoraFacade.save(idEvento,idUsuario, descripcionEvento, Calendar.getInstance(), changedFields, empresaVO.getIdEmpresa(), 
					TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());					
		} catch (Exception e) {
			e.printStackTrace(); logger.error(e);
			throw new TechnicalException(e);
		}		
		return empresaVO;
	}
	
	
	
	public void registroBitacora(EVENTO evento, long idUsuario, String descripcion,String detalle, long idRegistro, 
			TIPO_PROPIETARIO tipoPropietario){
		bitacoraFacade.save(evento, idUsuario, descripcion, detalle, idRegistro, tipoPropietario);		
	}

	@Override
	public Map<String, String> obtenerActividadEconomica(long idActEco) {
		return empresaFacade.obtenerActividadEconomica(idActEco);
	}
	
	
	
	public Long calculaTamanioEmpresa(long idRama, int numeroEmpleados) {
		Long tamanio = null;
		System.out.println("idRama:" + idRama + " numeroEmpleados:" + numeroEmpleados);
		String idSector = getIdSector(idRama);
		if(null != idSector){
			System.out.println("idSector:" + idSector);
			if (numeroEmpleados <= LIMITE_NUMERO_EMPLEADOS_MICRO_EMPRESA) {
				tamanio = Catalogos.TAMANIO_EMPRESA.MICRO.getIdTamanio();
				System.out.println("tamanio 1 :" + tamanio);
			} else {
				if (esSectorDeComercio(new Long(idSector)) && numeroEmpleados <= LIMITE_NUMERO_EMPLEADOS_PEQUENA_EMPRESA_COMERCIO ||
						!esSectorDeComercio(new Long(idSector)) && numeroEmpleados <= LIMITE_NUMERO_EMPLEADOS_PEQUENA_EMPRESA_NO_COMERCIO) {
					tamanio = Catalogos.TAMANIO_EMPRESA.PEQUENIA.getIdTamanio();
					System.out.println("tamanio 2 :" + tamanio);
				} else {				
					if (esSectorDeComercio(new Long(idSector)) && numeroEmpleados <= LIMITE_NUMERO_EMPLEADOS_MEDIANA_EMPRESA_COMERCIO_O_SERVICIO ||
							esSectorDeServicio(new Long(idSector)) && numeroEmpleados <= LIMITE_NUMERO_EMPLEADOS_MEDIANA_EMPRESA_COMERCIO_O_SERVICIO ||
							!esSectorDeComercio(new Long(idSector)) && !esSectorDeServicio(new Long(idSector)) && numeroEmpleados <= LIMITE_NUMERO_EMPLEADOS_MEDIANA_EMPRESA_INDUSTRIA) {
						tamanio = Catalogos.TAMANIO_EMPRESA.MEDIANA.getIdTamanio();
						System.out.println("tamanio 3 :" + tamanio);
					} else {
						tamanio = Catalogos.TAMANIO_EMPRESA.GRANDE.getIdTamanio();
						System.out.println("tamanio 4 :" + tamanio);
					}				
				}
			}			
		}
		return tamanio;
	}	
	
	public String getIdSector(long idRama){
		return Long.toString(idRama).length() >= NUMERO_MINIMO_DE_CARACTERES_DE_SECTOR ? Long.toString(idRama).substring(0,NUMERO_MINIMO_DE_CARACTERES_DE_SECTOR) : null;  		
	}	
	
	private boolean esSectorDeComercio(long idSector) {
		return(elementoEstaContenidoEnArreglo(idSector, sectoresComercio));
	}		

	private boolean esSectorDeServicio(long idSector) {
		return(elementoEstaContenidoEnArreglo(idSector, sectoresServicio));
	}	

	private boolean elementoEstaContenidoEnArreglo(long elemento, long[] arreglo){
		for (long elementoDelArreglo : arreglo){
			if (elemento == elementoDelArreglo) {
				return true;
			}
		}
		return false;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public CurpVO consultaCURPPorDatosPersonales(CurpVO datosPersonales){
		
		CurpVO datosCurp = null;
				
		try {
			ParametroVO parametro = parametroFacade.findById(PARAMETRO.URL_WS_RENAPO_CONSULTA_POR_DETALLE.getIdParametro()); 
			String targetEndpoint = parametro.getValor();
			
			//Comentado para probar en QA
			CURPServiceImpl curpService = CURPServiceImpl.getInstance();
			datosCurp = curpService.consultaCURPPorDatosPersonales(datosPersonales, targetEndpoint);
			
			//TODO SERVICIO DE RENAPO - QA PUENTE			
//			CandidatoVo candidatoVo = CurpServiceLocator.getInstance().consultaCURPPorDatosPersonales(datosPersonales.getNombre(),
//					datosPersonales.getApellido1(),
//					datosPersonales.getApellido2(),
//					datosPersonales.getIdGenero(),
//					datosPersonales.getFechaNacimiento(),
//					datosPersonales.getIdEntidadNacimiento());
//
//			datosCurp = copyData(candidatoVo);

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}	
		
		return datosCurp;		
	}
	
	
	private CurpVO copyData(CandidatoVo candidatoVo){
		CurpVO datosCurp = null;
		
		if (candidatoVo!=null){
			datosCurp = new CurpVO();
			
			datosCurp.setNombre(candidatoVo.getNombre());
			datosCurp.setApellido1(candidatoVo.getApellido1());
			datosCurp.setApellido2(candidatoVo.getApellido2());
			datosCurp.setCurp(candidatoVo.getCurp());

			if (candidatoVo.getFechaNacimiento()!=null){
				datosCurp.setFechaNacimiento(candidatoVo.getFechaNacimiento());
				
			} else if (candidatoVo.getFechaNacimientoString()!=null) {
				Date fecha = Utils.toDate(candidatoVo.getFechaNacimientoString());
				datosCurp.setFechaNacimiento(fecha);
			}
			
			if (datosCurp.getFechaNacimiento()!=null){
				String fh = Utils.formatTime(datosCurp.getFechaNacimiento());	
				datosCurp.setFechaNac(fh);
			}
			
			datosCurp.setIdGenero(candidatoVo.getGenero());
			datosCurp.setGenero(candidatoVo.getGeneroString());

			datosCurp.setIdEntidadNacimiento(candidatoVo.getIdEntidadNacimiento());
			datosCurp.setEntidadNacimiento(candidatoVo.getEntidadNacimiento());

			datosCurp.setStatusOper(candidatoVo.getStatusOper());
			datosCurp.setMessage(candidatoVo.getMessage());
			datosCurp.setTipoError(candidatoVo.getTipoError());
			datosCurp.setCodigoError(candidatoVo.getCodigoError());

			datosCurp.setEstatusCurp(candidatoVo.getEstatusCurp());

			WS_CURP_OPERACION tipoOperacion = tipoOperacion(candidatoVo.getTipoError());
			datosCurp.setOperacion(tipoOperacion);
			
			if (WS_CURP_OPERACION.OPERACION_NO_EXITOSA == tipoOperacion){
				int idTipoError = Utils.parseInt(candidatoVo.getTipoError());
				int idError   	= Utils.parseInt(candidatoVo.getCodigoError());
				
				WS_CURP_TIPO_ERROR wsTipoError = WS_CURP_TIPO_ERROR.getTipoError(idTipoError);
				WS_CURP_ERROR wsError = WS_CURP_ERROR.getError(idError);
			
				datosCurp.setWsTipoError(wsTipoError);
				datosCurp.setWsError(wsError);
			}			
		}
		
		return datosCurp;
	}
	
	
	public boolean esIdPortalEmpleoCurpUnico(String curp){
		
		boolean unico = !empresaFacade.repetidoIdPortalEmpleoCurp(curp);
		return unico;		
	}
	
	
	public boolean esCurpUnico(String curp){
		boolean unico = !empresaFacade.repetidaCurp(curp);
		return unico;
	}	
	
	public CurpVO consultaDatosPersonalesPorCURP(CurpVO curpVo){
		return empresaFacade.consultaDatosPersonalesPorCURP(curpVo);
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public CurpVO consultaDatosPersonalesPorCURP(String CURP) {
		CurpVO datosCurp = null;
		
		try {
			
//			Llamada directa a Renapo
			ParametroVO parametro = parametroFacade.findById(PARAMETRO.URL_WS_RENAPO_CONSULTA_POR_CURP.getIdParametro()); 
			String targetEndpoint = parametro.getValor();
			
			CURPServiceImpl curpService = CURPServiceImpl.getInstance();
			datosCurp = curpService.consultaDatosPersonalesPorCURP(CURP, targetEndpoint);
			
			
			// inicio llamada desde QA a Renapo mediante puente a PRODUCCIÓN
//			CandidatoVo candidato = CurpServiceLocator.getInstance().consultaDatosPersonalesPorCURP(CURP);
//			datosCurp = copyData(candidato);
//			
//			if (1==2)
//				throw new ConsultaWsCurpException();			
			// fin llamada desde QA a Renapo mediante puente a PRODUCCIÓN

		} catch (ConsultaWsCurpException e) {
			e.printStackTrace();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return datosCurp;	
	}
	
	
	private static WS_CURP_OPERACION tipoOperacion(String cadenaXml){		
		if(cadenaXml.contains(NO_EXITOSO)){
			logger.error("OPERACION_NO_EXITOSA");
			return WS_CURP_OPERACION.OPERACION_NO_EXITOSA;
		} else { 
			logger.info("OPERACION_EXITOSA");
			return WS_CURP_OPERACION.OPERACION_EXITOSA;
		}
	}	
	
	/*public Map<Long, List<BitacoraVO>> consultaMovimientos(long[] idsEmpresa){
		Map <Long, List<BitacoraVO>> movimientosEmpresas = null;
		
		int numregistros = PropertiesLoader.getInstance().getPropertyInt("emp.buscador.limite.movimientos");
		if (numregistros<=0) numregistros = 5;

		List<BitacoraVO> movimientos = bitacoraFacade.consultaBitacora(idsEmpresa, TIPO_PROPIETARIO.EMPRESA, numregistros);
		
		return movimientosEmpresas;
	}*/
	
	// start cambio movil
	public EmpresaVO consultaEmpresaPorIdUsuario(long idUsuario) throws PersistenceException {
		EmpresaVO empresa = empresaFacade.findByIdUsuario(idUsuario);
		return empresa;
	}

	@Override
	public void insertarEventoBitacora(EventoVO evento)
			throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertarEventoBitacora(EVENTO evento, long idUsuario,
			TIPO_REGISTRO tipoRegistro, long idRegistro, int estatusAnterior)
			throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CandidatoVo> buscadorCandidatosQuery(List<Long> indices)
			throws TechnicalException, SQLException {
		List<CandidatoVo> candidatos = new ArrayList<CandidatoVo>();
		if(indices.size()>1000){
			for(int i = 0;i<indices.size()/1000;i++){
				List<Long> ind = indices.subList((1000*i), (1000*i)+1000);
				candidatos.addAll(candidatoFacade.buscarCandidatosQuery(ind));
			}
			
		}
		else
		{
			candidatos = candidatoFacade.buscarCandidatosQuery(indices);
		}
		return candidatos;
		
	}

	@Override
	public boolean consultarPermisoGeolocalizacionRegistro()
			throws BusinessException {
		return parametroFacade.findById(PARAMETRO.GEOLOCALIZACION_REGISTRO_EMPRESA.getIdParametro()).getValor().equals("1");
	}
	
	// fin cambio movil

}
