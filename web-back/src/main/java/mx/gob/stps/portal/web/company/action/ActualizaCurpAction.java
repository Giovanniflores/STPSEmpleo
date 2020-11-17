package mx.gob.stps.portal.web.company.action;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.EMPRESA_HEAD;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_MI_ESP_EMP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.USUARIO_APP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PERSONA;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_USUARIO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.utils.Catalogos.WS_CURP_ERROR;
import mx.gob.stps.portal.utils.Catalogos.WS_CURP_OPERACION;
import mx.gob.stps.portal.web.company.delegate.EmpresaRegistroBusDelegate;
import mx.gob.stps.portal.web.company.delegate.EmpresaRegistroBusDelegateImpl;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegate;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegateImpl;
import mx.gob.stps.portal.web.company.form.ActualizaCurpForm;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;
import mx.gob.stps.portal.web.infra.vo.ResultVO;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;
import mx.gob.stps.portal.ws.renapo.vo.CurpVO;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ActualizaCurpAction extends GenericAction {
	private static Logger logger = Logger.getLogger(ActualizaCurpAction.class);

	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		String msgError = "";
		String rutaFoward = FORWARD_TEMPLATE_MI_ESP_EMP;	
		
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());

		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Actualiza el CURP");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Actualiza el CURP, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_JSP);
	}
	
	
	public ActionForward validaCurp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		
		ActualizaCurpForm registroForm = (ActualizaCurpForm)form;
		
		String type = "";
		String message = "";
		CurpVO datosCurp = null;
		String strCurpFecha = null;
		
		try{
			HttpSession session = request.getSession();
			
			UsuarioWebVO usuarioWeb = (UsuarioWebVO)session.getAttribute(USUARIO_APP);
			
			if (usuarioWeb!=null && usuarioWeb.getIdTipoUsuario() == TIPO_USUARIO.EMPRESA.getIdTipoUsuario()){
				
				long idEmpresa = usuarioWeb.getIdPropietario();
				
				registroForm.setIdEmpresa(idEmpresa);
				
			}
			
			CurpVO datosPersonales = new CurpVO();
			
			EmpresaRegistroBusDelegate delegate = EmpresaRegistroBusDelegateImpl.getInstance();			

			String curp = null != request.getParameter("curp") ? (String) request.getParameter("curp") : null;			
			
			String strGenero = null != request.getParameter("idGenero") ? (String) request.getParameter("idGenero") : null;
			
			String strEntidadNacimiento = null != request.getParameter("entidadNacimientoSelect") ? (String) request.getParameter("entidadNacimientoSelect") : null;
						
			String strFechaNacimiento = null != session.getAttribute("fechaNac") ? (String) session.getAttribute("fechaNac") : null;
			
			if(curp!=null){
								
				datosPersonales.setCurp(curp);
				
				if(strGenero!=null){
					datosPersonales.setIdGenero(Utils.parseInt(strGenero));
					
					if(datosPersonales.getIdGenero() == Catalogos.GENERO.FEMENINO.getIdOpcion()){
						
						datosPersonales.setGenero("M");
						
					} else if(datosPersonales.getIdGenero() == Catalogos.GENERO.MASCULINO.getIdOpcion()) {
						
						datosPersonales.setGenero("H");
					}					
				}
					
				
				if(strEntidadNacimiento!=null){
					datosPersonales.setIdEntidadNacimiento(Utils.parseInt(strEntidadNacimiento));
				}
				
				if(strFechaNacimiento!=null){
					datosPersonales.setFechaNac(strFechaNacimiento);
					datosPersonales.setFechaNacimiento(Utils.convert(strFechaNacimiento));
										
					StringBuffer sbCurpFecha = new StringBuffer();
					sbCurpFecha.append(curp.substring(8,10)).append("/").append(curp.substring(6,8)).append("/").append(curp.substring(4,6));
			        
					strCurpFecha = formatearFechaddMMYYYY(sbCurpFecha.toString());																				
				}								
				
				if(null!=strCurpFecha && strCurpFecha.equals(datosPersonales.getFechaNac())){
					
					datosCurp = delegate.consultaCURPPorDatosPersonales(datosPersonales);					
				}
				
			}
			
			/*FIXME Codigo de prueba para caso de exito 
			datosCurp = new CurpVO();
			datosCurp.setNombre("HAYDEE");
			datosCurp.setApellido1("VERTTI");
			datosCurp.setApellido2("DIAZ");
			datosCurp.setFechaNacimiento(construirFecha("18/03/1976"));
			datosCurp.setIdEntidadNacimiento(9);
			datosCurp.setGenero("M");
			datosCurp.setCurp("VEDH760318MDFRZY06");	
			datosCurp.setStatusOper(WS_CURP_OPERACION.OPERACION_EXITOSA.toString());
			datosCurp.setOperacion(WS_CURP_OPERACION.OPERACION_EXITOSA);
			System.out.println("----datosCurp.toString():" + datosCurp.toString());
								
			datosPersonales.setNombre(datosCurp.getNombre());
			datosPersonales.setApellido1(datosCurp.getApellido1());
			datosPersonales.setApellido2(datosCurp.getApellido2());
			*/
			
			/*FIXME Codigo de prueba para caso en que Curp no existe 
			datosCurp = new CurpVO();
			datosCurp.setOperacion(WS_CURP_OPERACION.OPERACION_NO_EXITOSA);
			*/
			
			/*FIXME Codigo de prueba para caso en que Curp no existe  
			datosCurp = new CurpVO();
			datosCurp.setWsError(WS_CURP_ERROR.CURP_NO_EXISTE);
			*/								
			
			if(null!=strCurpFecha && !strCurpFecha.equals(datosPersonales.getFechaNac())){
				type = ResultVO.TYPE_ERROR; 					
				message = "La fecha de nacimiento proporcionado durante el registro de la empresa, no corresponde con el CURP";
			
			} else if (null != datosCurp && null != datosCurp.getOperacion() && WS_CURP_OPERACION.OPERACION_EXITOSA == datosCurp.getOperacion()) {
				
				type = ResultVO.TYPE_SUCCESS;
				message = "CURP " + curp + " validado";		
				registroForm.setCurp(datosPersonales.getCurp());
				registroForm.setIdEntidadNacimiento(datosPersonales.getIdEntidadNacimiento());
				registroForm.setIdGenero(datosPersonales.getIdGenero());

															
			} else if (null != datosCurp && null != datosCurp.getOperacion() && WS_CURP_OPERACION.OPERACION_NO_EXITOSA == datosCurp.getOperacion()) {
				type = ResultVO.TYPE_ERROR; 					
				message = WS_CURP_ERROR.CURP_NO_EXISTE.getError();
				
			} else if (null != datosCurp && null!=datosCurp.getWsError()){
				type = ResultVO.TYPE_ERROR; 
				//System.out.println("----curpVo.getWsError():" + datosCurp.getWsError().toString());
				
				if(datosCurp.getWsError().getIdError()>0){					
					//System.out.println("----curpVo.getWsError().getIdError():" + datosCurp.getWsError().getIdError());
					registroForm.setIdMotivoNoValidacion((long)datosCurp.getWsError().getIdError());
					message = datosCurp.getWsError().getError();
				}									
				message = "se produjo un error en la consulta de Curp.";
				 
			
			} else if (null != datosCurp && !delegate.esCurpUnico(curp)) {

				type = ResultVO.TYPE_ERROR; 
				message = "Se identificó que los datos proporcionados pertenecen a otra empresa registrada.";
			}				
			
	    } catch (Exception ex) {
	    	ex.printStackTrace(); logger.error(ex);
			type = ResultVO.TYPE_ERROR;
			//message = WS_CURP_ERROR.ERROR_EN_CONSULTA.getError();
			message = "Por el momento RENAPO no responde, por lo tanto no se podrá realizar la actualización  de sus datos.";
		}
		
		try {
			
			ResultVO resultado = new ResultVO(message, type);		
			String json = toJson(resultado);

			redirectJsonResponse(response, json);
			
		} catch (IOException e) {
			e.printStackTrace();
		}			
		
		return null;
	}		

	
	private String formatearFechaddMMYYYY(String cadenaFechaddMMYY){
		
        SimpleDateFormat yy = new SimpleDateFormat( "dd/MM/yy" );  
        SimpleDateFormat yyyy = new SimpleDateFormat( "dd/MM/yyyy" );  
        
        String fechaFormateada = null;
		
        try {  
        	Date dtCurpFecha = yy.parse( cadenaFechaddMMYY );  
        	fechaFormateada = yyyy.format(dtCurpFecha);
        	
        }  catch ( ParseException pe ) {  
            pe.printStackTrace();
            logger.error(pe);
        }  		
        
        return fechaFormateada;
        
	} 
	
	private Date construirFecha(String cadenaFecha){
		
		Date date = null;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	 
		try {
	 
			date = formatter.parse(cadenaFecha);
	 
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		
		return date;
	}
	
	
	public ActionForward actualizarDatosCurp(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response){
		
		ActualizaCurpForm registroForm = (ActualizaCurpForm)form;
		
		ResultVO msg =  null;
		
		long idUsuario = 0;
		
		long idEmpresa = 0;
		
		try{
			
			HttpSession session = request.getSession();
			
			UsuarioWebVO usuarioWeb = (UsuarioWebVO)session.getAttribute(USUARIO_APP);
			
			if (usuarioWeb!=null && usuarioWeb.getIdTipoUsuario() == TIPO_USUARIO.EMPRESA.getIdTipoUsuario()){
				
				idUsuario = usuarioWeb.getIdUsuario();
				
				idEmpresa = usuarioWeb.getIdPropietario();
				
				registroForm.setIdEmpresa(idEmpresa);
				
			}
			
			EmpresaRegistroBusDelegate services = EmpresaRegistroBusDelegateImpl.getInstance();
			
			EmpresaVO empresaVo  = services.consultaEmpresa(registroForm.getIdEmpresa());
			
			empresaVo.setCurpPF(registroForm.getCurp());
			
			empresaVo.setIdLugarNacimientoPF((long)registroForm.getIdEntidadNacimiento());
			 			
			empresaVo.setGenero(registroForm.getIdGenero());
			
			if(services.esCurpUnico(registroForm.getCurp())
					&& services.esIdPortalEmpleoCurpUnico(registroForm.getCurp())){
				
				empresaVo = services.actualizaEmpresa(empresaVo, idUsuario, 
						"CURP=null|idPortalEmpleo=" + registroForm.getCurp() + "|", 
						false, false, true);
				
				msg = new ResultVO(getMensaje(request, "emp.guardar.ext"), ResultVO.TYPE_SUCCESS);
				
				registraEmpresaHeader(request, empresaVo.getIdEmpresa());
				
			} else {

				msg = new ResultVO(getMensaje(request, "Se identificó que los datos proporcionados pertenecen a otra empresa registrada."), 
						ResultVO.TYPE_ERROR);
			}
			
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
		
		registroForm.setMsg(msg);
		
		try {
			
			String json = toJson(registroForm.getMsg());
			
			redirectJsonResponse(response, json);
			
		} catch (IOException e) {
			e.printStackTrace();
		}			
		
		return null;	
		
	}
	
	
	private void registraEmpresaHeader(HttpServletRequest request, long idEmpresa){
		try{
			RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance();
			EmpresaVO empresa = service.findEmpresaById(idEmpresa);
			
			String tipoPersona = TIPO_PERSONA.getTipoPersona((int)empresa.getIdTipoPersona());
			empresa.setTipoPersona(tipoPersona);
			
			Date fechaEmp = null;
			
			if (empresa.getIdTipoPersona() == TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()){
				fechaEmp = empresa.getFechaNacimiento();
			} else if (empresa.getIdTipoPersona() == TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona()){
				fechaEmp = empresa.getFechaActa();
			}
			
			String fechaEmpresa = Utils.formatDDMMYYYY(fechaEmp);
			
			request.getSession().setAttribute("empresafechaheader", fechaEmpresa);
			request.getSession().setAttribute(EMPRESA_HEAD, empresa);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e);
		}	
	}
	
	
	public ActionForward entidades(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_ENTIDAD_FEDERATIVA, true);
			
			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {e.printStackTrace(); logger.error(e);}
		
		return null;
	}	
	
	
	private void redirectJsonCatalogo(List<CatalogoOpcionVO> opciones, HttpServletResponse response) throws IOException {
		CatalogoVO cat = getCatalogo(opciones);
		String json = toJson(cat);

		redirectJsonResponse(response, json);
	}	

}
