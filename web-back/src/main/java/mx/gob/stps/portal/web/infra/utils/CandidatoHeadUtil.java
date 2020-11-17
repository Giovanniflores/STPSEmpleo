package mx.gob.stps.portal.web.infra.utils;

import static mx.gob.stps.portal.web.infra.utils.Constantes.USUARIO_APP;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.infra.utils.Constantes.PERFIL;
import mx.gob.stps.portal.web.candidate.vo.CandidatoAjaxVO;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;
import mx.gob.stps.portal.web.infra.utils.Constantes;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;

public class CandidatoHeadUtil {
	private static Logger logger = Logger.getLogger(CandidatoHeadUtil.class);
	
	public static CandidatoAjaxVO actualizarEstatusPpcHedaer(HttpServletRequest request) throws ServiceLocatorException{
		HttpSession session = request.getSession();
		CandidatoAjaxVO candidatoAjaxVO = new CandidatoAjaxVO();
		UsuarioWebVO usuario = (UsuarioWebVO)session.getAttribute(USUARIO_APP);

		if (usuario!=null && isPerfilCandidato(usuario.getIdPerfil())) {
			candidatoAjaxVO = actualizarEstatusPpcHeader(request,usuario);
		}
		return candidatoAjaxVO;
	}
	
	//actualizar el header con el usuasrioWeb
	public static CandidatoAjaxVO actualizarEstatusPpcHeader(HttpServletRequest request, UsuarioWebVO usuario) throws ServiceLocatorException {
		return actualizarEstatusPpcHeader(request,usuario.getIdPropietario());
		
	}
	//Actualizar el request con el id de usuario
	public static CandidatoAjaxVO actualizarEstatusPpcHeader(HttpServletRequest request,long id_usuario) throws ServiceLocatorException {
		CandidatoBusDelegate candidateServices = CandidatoBusDelegateImpl.getInstance();
		Integer estatusppc = candidateServices.consultarPpcEstatus(id_usuario);
		CandidatoAjaxVO candidatoAjaxVO = new CandidatoAjaxVO();
		if(estatusppc != null){
			// Actualizamos en sesión el objeto en sesión que indica que está inscrito al PPC o no dependiendo la nueva situación
			   try {
			    candidatoAjaxVO = (CandidatoAjaxVO)request.getSession().getAttribute(Constantes.CANDIDATO_HEAD);
			    //Solo actualizar cuando hay un cambio
			    if(!String.valueOf(estatusppc).equals(candidatoAjaxVO.getPpcEstatusIdOpcion()))
			    	candidatoAjaxVO.setPpcEstatusIdOpcion(String.valueOf(estatusppc));
			   } catch (Exception e){
			    logger.error("Error al actualizar el ppcEstatus en sesión");
			    e.printStackTrace();
			   }
		}
		return candidatoAjaxVO;
	}
	
	// Función que evalúa si el perfil es de candidato portao o de abriendo espacios
	public static boolean isPerfilCandidato(long idPerfil){

		if (idPerfil == mx.gob.stps.portal.utils.Utils.toLong(PERFIL.CANDIDATO.getIdOpcion()))
			return true;
		
		if (idPerfil == mx.gob.stps.portal.utils.Utils.toLong(PERFIL.CANDIDATO_ADULTO_MAYOR.getIdOpcion()))
			return true;		
		
		if (idPerfil == mx.gob.stps.portal.utils.Utils.toLong(PERFIL.CANDIDATO_DISCAPACIDAD.getIdOpcion()))
				return true;
		
		return false;
	}	
	
	
}
