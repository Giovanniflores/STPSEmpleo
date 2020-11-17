package mx.gob.stps.portal.web.security.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_ESP_ADMIN;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.core.seguridad.vo.AccionVO;
import mx.gob.stps.portal.core.seguridad.vo.PerfilVO;
import mx.gob.stps.portal.core.seguridad.vo.UsuarioVO;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;
import mx.gob.stps.portal.web.infra.vo.ResultVO;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegate;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegateImpl;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AdminPermisosAction extends GenericAction {
	private static Logger logger = Logger.getLogger(AdminPermisosAction.class);
	
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		UsuarioWebVO usuario = getUsuario(request.getSession());
		
		if (usuario!=null){
			if (usuario.getAdminTipoA()){
				forward = mapping.findForward("ACTION_PARAMETROS");
			} else {
				UsuarioVO usuarioVo;
				try {
					usuarioVo = SecutityDelegateImpl.getInstance().consultaUsuario(usuario.getIdUsuario());
					request.getSession().setAttribute("TITULO", "Administrador");
					request.getSession().setAttribute("datosAdmin", usuarioVo.getNombre()+" "+usuarioVo.getApellido1()+" "+usuarioVo.getApellido2());

				} catch (ServiceLocatorException e) {
					e.printStackTrace();
				}

				request.getSession().setAttribute(BODY_JSP, mapping.getInput());
				forward = mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
			}
		}

		return forward;
	}

	public ActionForward perfiles(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		List<CatalogoOpcionVO> opciones = new ArrayList<CatalogoOpcionVO>();
		
		try {
			SecutityDelegate services = SecutityDelegateImpl.getInstance();

			List<PerfilVO> perfiles = services.consultaPerfiles();
			for (PerfilVO perfile : perfiles){
				opciones.add(getCatalogoOpcionVO(perfile));
			}

			CatalogoVO cat = getCatalogo(opciones);
			String json = toJson(cat);
			
			redirectJsonResponse(response, json);
			
		} catch (ServiceLocatorException e) {logger.error(e);}
		  catch (IOException e) {logger.error(e);}
		  catch (SQLException e) {logger.error(e);}

		return null;
	}	

	public ActionForward permisos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		long idPerfil = Utils.parseLong(request.getParameter("idPerfil"));

		SecutityDelegate services = SecutityDelegateImpl.getInstance();
		
		try {
			List<AccionVO> acciones = services.consultaAccionesAsignadasPorPerfil(idPerfil);
			
			request.setAttribute("ACCIONES", acciones);
			
		} catch (SQLException e) {logger.error(e);
		} catch (ServiceLocatorException e) {logger.error(e);}

        return mapping.findForward("ACCIONES");
	}

	public ActionForward asignar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String msg = null;
		String type = null;

		long idPerfil = Utils.parseLong(request.getParameter("idPerfil"));
		String[] acciones = request.getParameterValues("accionopcion");
		//String[] autrequerido = request.getParameterValues("autentica");

		try{
			SecutityDelegate services = SecutityDelegateImpl.getInstance();

			long[] idsAccAsignar = convert(acciones);
			services.asignaAcciones(idPerfil, idsAccAsignar);

			//long[] idsAccAutenticar = convert(autrequerido);
			//services.estableceAccionesReqUsuarioAutenticado(idsAccAutenticar);

			msg = "Los permisos han sido asignados al perfil indicado.";
			type = ResultVO.TYPE_SUCCESS;

		} catch(SQLException e){
			logger.error(e);
			msg = "Ha ocurrido un problema al asignar los permisos, favor de notificar al administrador.";
			type = ResultVO.TYPE_ERROR;
		}catch(ServiceLocatorException e){
			logger.error(e);
			msg = getMensaje(request, "app.exp.locator.err");
			type = ResultVO.TYPE_ERROR;
		}

		try {
			String json = toJson(new ResultVO(msg, type));
			redirectJsonResponse(response, json);
			return null;
		} catch (IOException e) {
			logger.error(e);
			request.getSession().setAttribute(BODY_JSP, mapping.getInput());
	        return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
		}
	}

	private long[] convert(String[] valores){
		
		if (valores==null || valores.length==0) return new long[0];
		
		long[] numeros = new long[valores.length];
		
		for (int i=0; i<valores.length; i++){
			long id = Utils.parseLong(valores[i]);
			numeros[i] = id;
		}
		
		return numeros;
	}
	
	private CatalogoOpcionVO getCatalogoOpcionVO(PerfilVO perfil){
		CatalogoOpcionVO vo = new CatalogoOpcionVO();
		vo.setIdCatalogoOpcion(perfil.getIdPerfil());
		vo.setOpcion(perfil.getNombre());
		return vo;
	}	

}
