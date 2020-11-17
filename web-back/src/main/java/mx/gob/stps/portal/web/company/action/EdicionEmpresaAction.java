package mx.gob.stps.portal.web.company.action;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_MEDIO_ENTERADO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_TIPO_ACTIVIDAD_ECONOMICA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_TIPO_EMPRESA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_TIPO_TELEFONO;
import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_MI_ESP_EMP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.LST_TELEFONOS_ADICIONALES;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TAB_MENU;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TAB_MIS_DATOS;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.MULTIREGISTRO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_TELEFONO;
import mx.gob.stps.portal.core.seguridad.vo.UsuarioVO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.web.address.delegate.DomicilioBusDelegate;
import mx.gob.stps.portal.web.address.delegate.DomicilioBusDelegateImpl;
import mx.gob.stps.portal.web.company.delegate.EmpresaRegistroBusDelegate;
import mx.gob.stps.portal.web.company.delegate.EmpresaRegistroBusDelegateImpl;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegate;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegateImpl;
import mx.gob.stps.portal.web.company.form.EdicionEmpresaForm;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegate;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;
import mx.gob.stps.portal.web.infra.vo.ResultVO;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegate;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegateImpl;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class EdicionEmpresaAction extends GenericAction{
	
	private static Logger logger = Logger.getLogger(EdicionEmpresaAction.class);
	private int DETECTA_CAMBIO_LONGITUD_PASSWORD = 4;
	private int NUMERO_MAXIMO_DE_REGISTROS_ADICIONALES_PERMITIDOS = 2;
	private long[] opcionesTipoTelefonoDeprecated = new long[] { 2, 3, 4 };	
	private String LST_PHONES_TO_REMOVE = "LST_PHONES_TO_REMOVE";
	
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		//ocultaBarraArticulos(request);
		HttpSession session = request.getSession();
		EdicionEmpresaForm edicionForm = (EdicionEmpresaForm) form;
		edicionForm.reset();		
		UsuarioWebVO user = getUsuario(session);
		if (null != request.getParameter("idEmpresa"))
			edicionForm.setIdEmpresa(Utils.parseLong(request.getParameter("idEmpresa")));
		else if (null != user) {
			edicionForm.setIdEmpresa(user.getIdPropietario());
			edicionForm.setIdUsuario(user.getIdUsuario());
			edicionForm.setIdTipoUsuario(user.getIdTipoUsuario());
		}
		try {
			SecutityDelegate security = SecutityDelegateImpl.getInstance();
			RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance();
			EmpresaVO empresaVo = service.findEmpresaById(edicionForm.getIdEmpresa());
			if (null != empresaVo) {
				UsuarioVO usuario = security.consultaUsuario(empresaVo.getIdUsuario());	
				edicionForm.setUsuario(usuario.getUsuario());
				edicionForm.setPermisoGeolocalizacion(empresaVo.isEstatusGeoreferenciaDomicilio());	
				BeanUtils.copyProperties(edicionForm,empresaVo);
				if (edicionForm.getIdTipoPropietario() == 0)
					edicionForm.setIdTipoPropietario(TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());			
				DomicilioBusDelegate serviceDom = DomicilioBusDelegateImpl.getInstance();
				DomicilioVO domicilioVO = serviceDom.buscarDomicilioIdPropietario(empresaVo.getIdEmpresa(), TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
				if (null != domicilioVO)
					BeanUtils.copyProperties(edicionForm, domicilioVO);		
				//cargar auxiliar de codigo postal para detectar cambios
				if (null != domicilioVO && null != domicilioVO.getCodigoPostal() && !domicilioVO.getCodigoPostal().equalsIgnoreCase(""))
					edicionForm.setCodigoPostalAux(domicilioVO.getCodigoPostal());
				else edicionForm.setCodigoPostalAux("");
				EmpresaRegistroBusDelegate delegate = EmpresaRegistroBusDelegateImpl.getInstance();
				try {
					Map<String, String> actEco = delegate.obtenerActividadEconomica(empresaVo.getIdActividadEconomica());
					edicionForm.setSector(actEco.get("idSector"));
					edicionForm.setSubsector(actEco.get("idSubsector"));
					edicionForm.setActividadEconomica(actEco.get("idRama"));
				} catch (Exception ex) {
					ex.printStackTrace();
					logger.error("Error al obtener la actividad economica principal");
				}
				List<TelefonoVO> telefonosVO = delegate.initTelefonos(empresaVo.getIdEmpresa(), TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
				List<TelefonoVO> telefonosVOAdicionales = new ArrayList<TelefonoVO>();
				int cuentaAdicionales = 0;
				if (null != telefonosVO && !telefonosVO.isEmpty()) {
					for (TelefonoVO telefonoVO : telefonosVO) {
						if (telefonoVO.getPrincipal() == MULTIREGISTRO.PRINCIPAL.getIdOpcion()) {
							this.setTelefonoForm(edicionForm, telefonoVO);
						} else {
							if(cuentaAdicionales<=NUMERO_MAXIMO_DE_REGISTROS_ADICIONALES_PERMITIDOS){
								telefonosVOAdicionales.add(telefonoVO);
								cuentaAdicionales++;
							}							
						}
					}								
				} else {
					telefonosVOAdicionales = new ArrayList<TelefonoVO>();
				}
				if (edicionForm.getIdTipoPropietarioTel() == 0)
					edicionForm.setIdTipoPropietarioTel(TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());								
				session.setAttribute(LST_TELEFONOS_ADICIONALES,telefonosVOAdicionales);
				CatalogoOpcionDelegate catalogoOpcionDelegate = CatalogoOpcionDelegateImpl.getInstance();	
				edicionForm.setMedioEnterado(catalogoOpcionDelegate.getOpcionById(CATALOGO_OPCION_MEDIO_ENTERADO, empresaVo.getIdMedio()));					
				edicionForm.setTipoEmpresa(catalogoOpcionDelegate.getOpcionById(CATALOGO_OPCION_TIPO_EMPRESA, empresaVo.getIdTipoEmpresa()));
				edicionForm.setIdTipoEmpresa(empresaVo.getIdTipoEmpresa());
				edicionForm.setActividadEconomica(catalogoOpcionDelegate.getOpcionById(CATALOGO_OPCION_TIPO_ACTIVIDAD_ECONOMICA, empresaVo.getIdActividadEconomica()));
				edicionForm.setIdTipoActividadEconomica(empresaVo.getIdActividadEconomica());
				edicionForm.setAccesoCelular(Constantes.CLAVE_TELEFONO_CELULAR);
				edicionForm.setAccesoFijo(Constantes.CLAVE_TELEFONO_FIJO);
				edicionForm.setIdTipoTelefonoCelular(Constantes.TELEFONO_CELULAR);
				edicionForm.setIdTipoTelefonoFijo(Constantes.TELEFONO_FIJO);	
				if (edicionForm.getTipoTelefonos() == null || edicionForm.getTipoTelefonos().isEmpty())						
					edicionForm.setTipoTelefonos(obtenerTipoTelefonosOrdenados());			
				if (null != empresaVo.getPaginaWeb() && !empresaVo.getPaginaWeb().equalsIgnoreCase(""))
					edicionForm.setPaginaWeb(empresaVo.getPaginaWeb());
				else edicionForm.setPaginaWeb("http://");								
			}
		} catch (BusinessException e) {
			e.printStackTrace(); logger.error(e);
		} catch (SQLException e) {
			e.printStackTrace(); logger.error(e);
		} catch (ServiceLocatorException e) {
			e.printStackTrace(); logger.error(e);
		} catch (IllegalAccessException e) {
			e.printStackTrace(); logger.error(e);
		} catch (InvocationTargetException e) {
			e.printStackTrace(); logger.error(e);
		}		
		//session.setAttribute(TAB_MENU, TAB_MIS_DATOS);
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Editar mis datos");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Espacio empresa, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
	}
	
	public List<CatalogoOpcionVO> obtenerTipoTelefonosOrdenados(){	
		List<CatalogoOpcionVO> lstCat = null;
		try {
			lstCat = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_TIPO_TELEFONO, opcionesTipoTelefonoDeprecated);
			Comparator<CatalogoOpcionVO> comparator = new Comparator<CatalogoOpcionVO>(){
				public int compare(CatalogoOpcionVO o1, CatalogoOpcionVO o2) {						
					if (o1==null) return 1;
					if (o2==null) return -1;
					String s1 = o1.getOpcion();
					String s2 = o2.getOpcion();							
					return s1.compareTo(s2);
				}
			};
			
	    	Collections.sort(lstCat, comparator);
	    	Collections.reverse(lstCat);							
		} catch (ServiceLocatorException e) {
			e.printStackTrace(); logger.error(e);
		}
		return lstCat;
	}

	
	public ActionForward cancelar(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {		
		UsuarioWebVO webVo = getUsuario(request.getSession());				
		long idTipoUsuario = webVo.getIdTipoUsuario();	
    	if (idTipoUsuario==Constantes.TIPO_USUARIO.ADMINISTRADOR.getIdTipoUsuario()) {
    		request.getSession().setAttribute(BODY_JSP, mapping.findForward("ACTION_ADMIN").getPath());
    		return mapping.findForward("ACTION_ADMIN");	    		
    	} else {
    		request.getSession().setAttribute(BODY_JSP, mapping.findForward("JSP_CANCEL").getPath());
    		return mapping.findForward("JSP_CANCEL");	    		
    	}
	}	
	
	public ActionForward actualizar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws BusinessException, ServiceLocatorException, IOException {
		long idEmpresa = 0;
		long idUsuario = 0;
		String changedFields = "";
		boolean isChangedEmail = false;
		boolean isChangedIdPortal = false;
		boolean isChangedPassword = false;
		EmpresaVO empresaVO = new EmpresaVO();
		EmpresaVO oldEmpresa =  new EmpresaVO();
		HttpSession session = request.getSession();
		UsuarioWebVO usuario = getUsuario(session);
		EdicionEmpresaForm edicionForm = (EdicionEmpresaForm) form;
		EmpresaRegistroBusDelegate services = EmpresaRegistroBusDelegateImpl.getInstance();
		if (usuario.getIdTipoUsuario() == Constantes.TIPO_USUARIO.ADMINISTRADOR.getIdTipoUsuario()) {
			oldEmpresa = services.consultaEmpresa(edicionForm.getIdEmpresa());
			if (null != oldEmpresa) {
				idEmpresa = oldEmpresa.getIdEmpresa();
				idUsuario = oldEmpresa.getIdUsuario();
			}
		}else if (usuario.getIdTipoUsuario() == Constantes.TIPO_USUARIO.EMPRESA.getIdTipoUsuario()) {
			idEmpresa = usuario.getIdPropietario();
			oldEmpresa  = services.consultaEmpresa(idEmpresa);
			idUsuario = usuario.getIdUsuario();
		}
		edicionForm.setIdEmpresa(idEmpresa);
		edicionForm.setIdUsuario(idUsuario);
		edicionForm.setIdTipoUsuario(usuario.getIdTipoUsuario());
		convertir(empresaVO, edicionForm);
		ResultVO msg =  new ResultVO(getMensaje(request, "emp.guardar.ext"), ResultVO.TYPE_SUCCESS);
		try {
			saveAddPhoneList(request);
			removeFromDBPhoneList(request);
			@SuppressWarnings("unchecked")
			List<TelefonoVO> listaTelefonos = (List<TelefonoVO>)request.getSession().getAttribute(LST_TELEFONOS_ADICIONALES);
			empresaVO.setTelefonos(listaTelefonos);			
			changedFields = getChangedField(edicionForm,oldEmpresa);
			if (edicionForm.getPassword()!=null && edicionForm.getPassword().length() > DETECTA_CAMBIO_LONGITUD_PASSWORD) {
				isChangedPassword = true;
				empresaVO.setContrasena(edicionForm.getPassword());				
			}				
			if(edicionForm.getCodigoPostalAux()!=null &&  !edicionForm.getCodigoPostalAux().equalsIgnoreCase(edicionForm.getCodigoPostal())){
				isChangedIdPortal = true;
				empresaVO.setFechaAlta(oldEmpresa.getFechaAlta());
				empresaVO.setFechaActa(oldEmpresa.getFechaActa());
			}			
			empresaVO = services.actualizaEmpresa(empresaVO, idUsuario, changedFields, 
					isChangedPassword, isChangedEmail, isChangedIdPortal);
			msg = new ResultVO(getMensaje(request, "emp.guardar.ext"), ResultVO.TYPE_SUCCESS);	
			if (edicionForm.getPassword()!=null && edicionForm.getPassword().length() > DETECTA_CAMBIO_LONGITUD_PASSWORD) {
				SecutityDelegate security = SecutityDelegateImpl.getInstance();
				security.confirmaContrasenaEmpresa(idEmpresa, edicionForm.getCorreoElectronico(), edicionForm.getPassword());		
			}			
		} catch (BusinessException e) {
			msg = new ResultVO(getMensaje(request, "emp.guardar.err"), ResultVO.TYPE_ERROR);
			e.printStackTrace(); logger.error(e);
		} catch (ServiceLocatorException e) {
			e.printStackTrace(); logger.error(e);
			msg = new ResultVO(getMensaje(request, "emp.guardar.err"), ResultVO.TYPE_ERROR);
		} catch (PersistenceException e) {
			msg = new ResultVO(getMensaje(request, "emp.guardar.err"), ResultVO.TYPE_ERROR);
			e.printStackTrace(); logger.error(e);
		} catch (TechnicalException e) {
			msg = new ResultVO(getMensaje(request, "emp.guardar.err"), ResultVO.TYPE_ERROR);
			e.printStackTrace(); logger.error(e);
		} catch (Exception e) {
			msg = new ResultVO(getMensaje(request, "emp.guardar.err"), ResultVO.TYPE_ERROR);
			e.printStackTrace(); logger.error(e);
		}		
		edicionForm.setMsg(msg);
		this.setResponseJSON(response, edicionForm);
		return null;	
	}

	@SuppressWarnings("unchecked")
	public ActionForward saveAddPhone(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		java.util.Date date = new Date();
		List<TelefonoVO> phoneAddList = null;
    	UsuarioWebVO user = getUsuario(request.getSession());
    	String clave = request.getParameter("claveAdd");
    	String acceso = request.getParameter("accesoAdd");
   		String telefono = request.getParameter("telefonoAdd");
   		Integer idTipoTelefono = Utils.parseInt(request.getParameter("idTipoTelefonoAdd"));
   		String extension = request.getParameter("extensionAdd");

   		idTipoTelefono = validaTipoTelefono(idTipoTelefono, acceso);
   		acceso = validaAcceso(idTipoTelefono, acceso);
   		
   		TelefonoVO phone = new TelefonoVO();
    	phone.setAcceso(acceso);
    	phone.setClave(clave);
   		phone.setExtension(extension);
   		phone.setFechaAlta(date);
   		phone.setIdTipoTelefono(idTipoTelefono);
		phoneAddList = (List<TelefonoVO>)request.getSession().getAttribute(LST_TELEFONOS_ADICIONALES);
		if (null != phoneAddList) {
			phone.setPrincipal(Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion());
		}else {
			phoneAddList = new ArrayList<TelefonoVO>();
		}
   		phone.setIdTipoPropietario(Constantes.TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
   		phone.setIdPropietario(user.getIdPropietario());
    	phone.setTelefono(telefono);
    	RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance();
    	try {
    		long idPhone = service.registrarTelefono(phone);
    		phone.setIdTelefono(idPhone);
    		phoneAddList.add(phone);
		} catch (Exception e) { e.printStackTrace(); logger.error(e);}	
		request.getSession().setAttribute(LST_TELEFONOS_ADICIONALES, phoneAddList);
	    return mapping.findForward("ACTION_REGISTROS_TABLA");	            	    	
    }
	
	@SuppressWarnings("unchecked")
	private void removeFromDBPhoneList(HttpServletRequest request) {
    	RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance();
		List<TelefonoVO> phoneDelList = new ArrayList<TelefonoVO>();
		if (null != request.getSession().getAttribute(LST_PHONES_TO_REMOVE)) {
			phoneDelList = (List<TelefonoVO>)request.getSession().getAttribute(LST_PHONES_TO_REMOVE);
			if (null != phoneDelList && !phoneDelList.isEmpty()) {
				for (TelefonoVO phone : phoneDelList) {
					if (phone.getIdTelefono() > 0) {
						try {
							service.eliminarTelefono(phone);
						} catch (Exception e) {
							logger.error(e); e.printStackTrace();
						}
					}
				}
			}
		}
		request.getSession().removeAttribute(LST_PHONES_TO_REMOVE);
	}
	
	public ActionForward deletePhone(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String idPhone = request.getParameter("idPhone");
		deletePhoneList(request, Utils.parseLong(idPhone));
        return mapping.findForward("ACTION_REGISTROS_TABLA");
	}
	
	@SuppressWarnings("unchecked")
	private void deletePhoneList(HttpServletRequest request, long idPhone2Del) {
		java.util.Date date = new Date();
		List<TelefonoVO> phoneAddList = new ArrayList<TelefonoVO>();
		List<TelefonoVO> phoneDelList = new ArrayList<TelefonoVO>();
    	UsuarioWebVO user = getUsuario(request.getSession());
    	if (null != request.getSession().getAttribute(LST_PHONES_TO_REMOVE))
    		phoneDelList = (List<TelefonoVO>)request.getSession().getAttribute(LST_PHONES_TO_REMOVE);
    	for (int i=1; i<=NUMERO_MAXIMO_DE_REGISTROS_ADICIONALES_PERMITIDOS; i++) {
    		long idTelefono = Utils.parseLong(request.getParameter("idTelefonoAdd" + i));
    		if (idTelefono > 0) {
	    		String clave = request.getParameter("claveAdd" + i);
	        	String acceso = request.getParameter("accesoAdd" + i);
	       		String telefono = request.getParameter("telefonoAdd" + i);
	       		Integer idTipoTelefono = Utils.parseInt(request.getParameter("idTipoTelefonoAdd" + i));
	       		String extension = request.getParameter("extensionAdd" + i);
	       		idTipoTelefono = validaTipoTelefono(idTipoTelefono, acceso);
	       		acceso = validaAcceso(idTipoTelefono, acceso);
	       		TelefonoVO phone = new TelefonoVO();
	       		phone.setClave(clave);
	       		phone.setIdTelefono(idTelefono);
	        	phone.setAcceso(acceso);
	       		phone.setExtension(extension);
	       		phone.setFechaAlta(date);
	    		if (null!=acceso && acceso.equals("01"))
	    			phone.setIdTipoTelefono((int)TIPO_TELEFONO.FIJO.getIdOpcion());
	    		else
	    			phone.setIdTipoTelefono((int)TIPO_TELEFONO.CELULAR.getIdOpcion());
	       		phone.setIdTipoPropietario(Constantes.TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
	       		phone.setIdPropietario(user.getIdPropietario());
	        	phone.setTelefono(telefono);
	        	phone.setPrincipal(Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion());	       		
	       		if (idPhone2Del != idTelefono) phoneAddList.add(phone);
	       		else phoneDelList.add(phone);
    		}
    	}
    	request.getSession().setAttribute(LST_PHONES_TO_REMOVE, phoneDelList);
		request.getSession().setAttribute(LST_TELEFONOS_ADICIONALES, phoneAddList);   
    }
	
	private void saveAddPhoneList(HttpServletRequest request) {
		java.util.Date date = new Date();
		List<TelefonoVO> phoneAddList = new ArrayList<TelefonoVO>();
    	UsuarioWebVO user = getUsuario(request.getSession());
    	request.getSession().removeAttribute(LST_TELEFONOS_ADICIONALES);
    	RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance();
    	for (int i=0; i<=NUMERO_MAXIMO_DE_REGISTROS_ADICIONALES_PERMITIDOS; i++) {
    		long idTelefono = Utils.parseLong(request.getParameter("idTelefonoAdd" + i));
    		if (idTelefono > 0) {
	    		String clave = request.getParameter("claveAdd" + i);
	        	String acceso = request.getParameter("accesoAdd" + i);
	       		String telefono = request.getParameter("telefonoAdd" + i);
	       		Integer idTipoTelefono = Utils.parseInt(request.getParameter("idTipoTelefonoAdd" + i));
	       		String extension = request.getParameter("extensionAdd" + i);
	       		idTipoTelefono = validaTipoTelefono(idTipoTelefono, acceso);
	       		acceso = validaAcceso(idTipoTelefono, acceso);     		
	       		TelefonoVO phone = new TelefonoVO();
	       		phone.setClave(clave);
	       		phone.setIdTelefono(idTelefono);
	        	phone.setAcceso(acceso);
	       		phone.setExtension(extension);
	       		phone.setFechaAlta(date);
	       		if (null!=acceso && acceso.equals("01"))
	    			phone.setIdTipoTelefono((int)TIPO_TELEFONO.FIJO.getIdOpcion());
	    		else
	    			phone.setIdTipoTelefono((int)TIPO_TELEFONO.CELULAR.getIdOpcion());
	       		phone.setIdTipoPropietario(Constantes.TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
	       		phone.setIdPropietario(user.getIdPropietario());
	        	phone.setTelefono(telefono);
	        	if (i==0)
	        		phone.setPrincipal(Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion());
	        	else
	        		phone.setPrincipal(Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion());	       		
	       		try {
	       			service.actualizarTelefono(phone);
	       		} catch (Exception e) { e.printStackTrace(); logger.error(e); }
	       		phoneAddList.add(phone);
    		}
    	}	
		request.getSession().setAttribute(LST_TELEFONOS_ADICIONALES, phoneAddList);	            	    	
    }
				
	public ActionForward validaCorreoElectronicoUnico(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		EdicionEmpresaForm edicionForm = (EdicionEmpresaForm) form;

		String type = "";
		String message = "";

		if (edicionForm.getCorreoElectronico()!=null && !edicionForm.getCorreoElectronico().isEmpty()){
			try {
				EmpresaRegistroBusDelegate delegate = EmpresaRegistroBusDelegateImpl.getInstance();
				boolean unico = delegate.esCorreoUnico(edicionForm.getCorreoElectronico());
				edicionForm.setCorreoElectronicoUnico(unico);

				type = "exito"; message = unico?"unico":"nounico";
			} catch (Exception e) {
				e.printStackTrace(); logger.error(e);
				type = "error"; message = "Error al validar el Correo.";
			}
		} else {
			type = "error"; message = "Correo electronico no indicado.";			
		}
		
		try {
			ResultVO resultado = new ResultVO(message, type);
			String json = toJson(resultado);
			redirectJsonResponse(response, json);
		} catch (IOException e) {
			e.printStackTrace(); logger.error(e);
		}

		return null;
	}		
	
	public ActionForward tiposEmpresa(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){

		try {
			CatalogoOpcionDelegate catalogoOpcionDelegate = CatalogoOpcionDelegateImpl.getInstance();	
			List<CatalogoOpcionVO> opciones = catalogoOpcionDelegate.consultarCatalogo(CATALOGO_OPCION_TIPO_EMPRESA, true);

			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {e.printStackTrace(); logger.error(e);}

		return null;
	}	
	
	public ActionForward tiposActividadEconomica(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){

		try {
			CatalogoOpcionDelegate catalogoOpcionDelegate = CatalogoOpcionDelegateImpl.getInstance();	
			List<CatalogoOpcionVO> opciones = catalogoOpcionDelegate.consultarCatalogo(CATALOGO_OPCION_TIPO_ACTIVIDAD_ECONOMICA, true);

			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {e.printStackTrace(); logger.error(e);}

		return null;
	}
	
	/* TODO:ESTE METODO ES CANDIDATO A ELIMINARSE
	public ActionForward entidades(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		long filtroOpciones [] = {33};
		try {
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_ENTIDAD_FEDERATIVA, filtroOpciones, true);
			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {e.printStackTrace(); logger.error(e);}
		
		return null;
	}
	*/		
	
	private String getChangedField(EdicionEmpresaForm updForm, EmpresaVO oldEmpresa){
		String strChangedFields = "";
		String strDescripcion = null==updForm.getDescripcion() || updForm.getDescripcion().equalsIgnoreCase("") ? "" : updForm.getDescripcion();
		String strContactoEmpresa = null==updForm.getContactoEmpresa() || updForm.getContactoEmpresa().equalsIgnoreCase("") ? "" : updForm.getContactoEmpresa();
		String strCalle = null==updForm.getCalle() || updForm.getCalle().equalsIgnoreCase("") ? "" : updForm.getCalle();
		String strCodigoPostal = null==updForm.getCodigoPostal() || updForm.getCodigoPostal().equalsIgnoreCase("") ? "" : updForm.getCodigoPostal();
		
		if(oldEmpresa.getCurpPF()==null && updForm.getCurp()!=null){
			strChangedFields = strChangedFields + "CURP=null|idPortalEmpleo=" + updForm.getCurp() + "|";
		}
		
		if(!oldEmpresa.getDescripcion().equalsIgnoreCase(strDescripcion)){
			strChangedFields = strChangedFields + "descripcion=" + oldEmpresa.getDescripcion() + "|";
		}
		if(!oldEmpresa.getContactoEmpresa().equalsIgnoreCase(strContactoEmpresa)){
			strChangedFields = strChangedFields + "contactoEmpresa=" + oldEmpresa.getContactoEmpresa() + "|";
		}
		if(oldEmpresa.getIdActividadEconomica()!=updForm.getIdTipoActividadEconomica()){
			strChangedFields = strChangedFields + "idActividadEconomica=" + oldEmpresa.getIdActividadEconomica() + "|";
		}
		if(oldEmpresa.getIdTipoEmpresa()!=updForm.getIdTipoEmpresa()){
			strChangedFields = strChangedFields + "idTipoEmpresa=" + oldEmpresa.getIdActividadEconomica() + "|";
		}
 				
		if(null!=oldEmpresa.getDomicilio()){
			if(!oldEmpresa.getDomicilio().getCalle().equalsIgnoreCase(strCalle)){
				strChangedFields = strChangedFields + "calle=" + oldEmpresa.getDomicilio().getCalle() + "|";
			}						
			if(!oldEmpresa.getDomicilio().getCodigoPostal().equalsIgnoreCase(strCodigoPostal)){
				strChangedFields = strChangedFields + "codigoPostal=" + oldEmpresa.getDomicilio().getCodigoPostal() + "|";
			}			
		} else {
			strChangedFields = strChangedFields + "calle=null|";
			strChangedFields = strChangedFields + "codigoPostal=null|";
		}

		return strChangedFields;
	}
	
	
	private void convertir(EmpresaVO destino, EdicionEmpresaForm origen){
		try{
			BeanUtils.copyProperties(destino, origen);
			java.util.Date date = new Date();
			destino.setNombre(origen.getNombre());
			destino.setApellido1(origen.getApellido1());
			destino.setApellido2(origen.getApellido2());
			destino.setRazonSocial(origen.getRazonSocial());
			destino.setFechaNacimiento(origen.getFechaNacimiento());
			destino.setFechaActa(origen.getFechaActa());
			destino.setIdActividadEconomica(origen.getIdTipoActividadEconomica());
			destino.setIdTipoEmpresa(origen.getIdTipoEmpresa());
			if(origen.getPaginaWeb().equalsIgnoreCase("http://")){
				destino.setPaginaWeb(null);
			} else {
				destino.setPaginaWeb(origen.getPaginaWeb());
			}				
			DomicilioVO domicilioVo = new DomicilioVO();
			domicilioVo.setIdPropietario(origen.getIdEmpresa());
			domicilioVo.setIdTipoPropietario(TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
			domicilioVo.setIdDomicilio(origen.getIdDomicilio());
			domicilioVo.setCalle(origen.getCalle());
			domicilioVo.setEntreCalle(origen.getEntreCalle());
			domicilioVo.setyCalle(origen.getyCalle());
			domicilioVo.setNumeroExterior(origen.getNumeroExterior());
			domicilioVo.setNumeroInterior(origen.getNumeroInterior());
			domicilioVo.setIdEntidad(origen.getIdEntidad());
			domicilioVo.setIdMunicipio(origen.getIdMunicipio());
			domicilioVo.setIdColonia(origen.getIdColonia());
			domicilioVo.setCodigoPostal(origen.getCodigoPostal());	
			domicilioVo.setLatitud(origen.getLatitud());
			domicilioVo.setLongitud(origen.getLongitud());
			destino.setDomicilio(domicilioVo);
			
			TelefonoVO principal = new TelefonoVO();
			principal.setIdTelefono(origen.getIdTelefono());		
			principal.setIdPropietario(origen.getIdEmpresa());
			principal.setIdTipoPropietario((int)origen.getIdTipoPropietarioTel());
			principal.setIdTipoTelefono(Constantes.TELEFONO_FIJO);
			principal.setAcceso(Constantes.CLAVE_TELEFONO_FIJO);
			principal.setClave(origen.getClave());
			principal.setTelefono(origen.getTelefono());
			principal.setExtension(origen.getExtension());
			principal.setPrincipal(MULTIREGISTRO.PRINCIPAL.getIdOpcion());
       		RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance();
       		try {
       			List<TelefonoVO> lstOldPhones = service.consultarTelefonos(origen.getIdEmpresa(), TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());  
       			if(null!=lstOldPhones && !lstOldPhones.isEmpty()){
       				TelefonoVO telPrincipal = getPrincipal(lstOldPhones);
       				if(null!=telPrincipal){
           				long idTelPrincipal = telPrincipal.getIdTelefono();  
           				if(idTelPrincipal>0){
               				principal.setIdTelefono(idTelPrincipal);	
               				service.actualizarTelefono(principal);         					           					
           				} else {
           					principal.setFechaAlta(date);
           					service.registrarTelefono(principal);
           				}
       				} else {
       					principal.setFechaAlta(date);
       					service.registrarTelefono(principal);
       				}     				
       			} else {
       				principal.setFechaAlta(date);
       				service.registrarTelefono(principal);
       			}
       		} catch (Exception e) { e.printStackTrace(); logger.error(e); }				
		} catch (IllegalAccessException e) {
			e.printStackTrace(); logger.error(e);
		} catch (InvocationTargetException e) {
			e.printStackTrace(); logger.error(e);
		}		
	}
	
	/* TODO: ESTOS METODOS SON CANDIDATOS A ELIMINARSE
	private void setEdicionEmpresaForm(EdicionEmpresaForm destino, EmpresaVO origen){
		try{
			BeanUtils.copyProperties(destino, origen);
			TelefonoVO principal = getPrincipal(origen);
			this.setTelefonoForm(destino, principal);
		} catch (IllegalAccessException e) {
			logger.error(e);
		} catch (InvocationTargetException e) {
			logger.error(e);
		}		
	}	
	
	private TelefonoVO getPrincipal(EmpresaVO origen){
		TelefonoVO principal = new TelefonoVO();
		List<TelefonoVO> telefonosVO = origen.getTelefonos();
		for (TelefonoVO telefonoVO : telefonosVO) {
			if (telefonoVO.getPrincipal() == MULTIREGISTRO.PRINCIPAL.getIdOpcion()) {
				principal = telefonoVO;
			}
		}		
		return principal;
	}
	*/
	
	private TelefonoVO getPrincipal(List<TelefonoVO> telefonosVO){
		TelefonoVO principal = new TelefonoVO();
		for (TelefonoVO telefonoVO : telefonosVO) {
			if (telefonoVO.getPrincipal() == MULTIREGISTRO.PRINCIPAL.getIdOpcion()) {
				principal = telefonoVO;
			}
		}		
		return principal;
	}	
		
	private void setTelefonoForm(EdicionEmpresaForm destino, TelefonoVO origen) {
		destino.setIdTelefono(origen.getIdTelefono());
		destino.setIdPropietario(origen.getIdPropietario());
		destino.setIdTipoPropietarioTel(origen.getIdTipoPropietario());
		destino.setIdTipoTelefono(origen.getIdTipoTelefono());
		destino.setAcceso(origen.getAcceso());
		destino.setClave(origen.getClave());
		destino.setTelefono(origen.getTelefono());
		destino.setExtension(origen.getExtension());
	}	
	
	private void setResponseJSON(HttpServletResponse response, EdicionEmpresaForm edicionEmpresaForm) throws IOException {
		String json = toJson(edicionEmpresaForm);
		redirectJsonResponse(response, json);
	}	
	
	private void redirectJsonCatalogo(List<CatalogoOpcionVO> opciones, HttpServletResponse response) throws IOException {
		CatalogoVO cat = getCatalogo(opciones);
		String json = toJson(cat);
		redirectJsonResponse(response, json);
	}		

	private Integer validaTipoTelefono(Integer idTipoTelefono, String acceso){		

		if(idTipoTelefono==null || 
			(idTipoTelefono!=null 
				&& (TIPO_TELEFONO.CELULAR.getIdOpcion() != idTipoTelefono.longValue() 
				&&  TIPO_TELEFONO.FIJO.getIdOpcion() != idTipoTelefono.longValue())
				)){
			
			if (acceso!=null && TIPO_TELEFONO.CELULAR.getAcceso().equals(acceso)){
				idTipoTelefono = (int)TIPO_TELEFONO.CELULAR.getIdOpcion();					
			} else if (acceso!=null && TIPO_TELEFONO.FIJO.getAcceso().equals(acceso)){
				idTipoTelefono = (int)TIPO_TELEFONO.FIJO.getIdOpcion();
			} else {
				idTipoTelefono = (int)TIPO_TELEFONO.FIJO.getIdOpcion();
			}
		}	
		return idTipoTelefono;
	}

	private String validaAcceso(Integer idTipoTelefono, String acceso){		

		if(acceso==null ||
				(acceso!=null && (!TIPO_TELEFONO.CELULAR.getAcceso().equals(acceso) 
				&& !TIPO_TELEFONO.FIJO.getAcceso().equals(acceso))
				)){
			
				if (idTipoTelefono!=null && TIPO_TELEFONO.CELULAR.getIdOpcion() == idTipoTelefono.longValue()){
					acceso = TIPO_TELEFONO.CELULAR.getAcceso();
				} else if (idTipoTelefono!=null && TIPO_TELEFONO.FIJO.getIdOpcion() == idTipoTelefono.longValue()){
					acceso = TIPO_TELEFONO.FIJO.getAcceso();
				} else {
					acceso = TIPO_TELEFONO.FIJO.getAcceso();
				}
		}
		return acceso;
	}
}
