package mx.gob.stps.portal.movil.web.seguridad.action;

import static mx.gob.stps.portal.movil.web.infra.utils.ConstantesMovil.FORWARD_JSP;
import static mx.gob.stps.portal.movil.web.infra.utils.ConstantesMovil.USERLOGGED;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.PERFIL;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.movil.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.movil.web.seguridad.delegate.SeguridadMovilDelegateImpl;
import mx.gob.stps.portal.movil.web.seguridad.vo.UsuarioFirmadoVO;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class HomeAction extends Action {
	private static Logger logger = Logger.getLogger(HomeAction.class);
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		ActionForward forward = null;
		
		UsuarioFirmadoVO usuario = (UsuarioFirmadoVO)session.getAttribute(USERLOGGED);
		long[] arrIds ={Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA_NACIDO_EXTRANJERO};
		try {
			List<CatalogoOpcionVO> entidades = SeguridadMovilDelegateImpl.getInstance().consultaEntidades(Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA,arrIds);
			session.setAttribute("entidades", entidades);
		} catch (ServiceLocatorException e) {
			logger.error(e);
		}
		
		if (usuario==null){
			forward = mapping.findForward(FORWARD_JSP);
		} else if (usuario.getIdPerfil()==PERFIL.EMPRESA.getIdOpcion()) {
			forward = mapping.findForward("FORDWARD_ESPACIO_EMPRESA");
		} else if (usuario.getIdPerfil()==PERFIL.CANDIDATO.getIdOpcion()) {
			forward = mapping.findForward("FORDWARD_ESPACIO_CANDIDATO");
		} else {
			forward = mapping.findForward(FORWARD_JSP);
		}
		
		return forward;
	}

}
