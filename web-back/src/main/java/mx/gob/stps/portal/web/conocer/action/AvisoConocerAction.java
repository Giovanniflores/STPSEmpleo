package mx.gob.stps.portal.web.conocer.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_REDIRECT_SWB;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_REDIRECT_SWB;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.stps.portal.core.candidate.vo.ConocerConfigVO;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AvisoConocerAction extends GenericAction {

	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String idPropietario = request.getParameter("idPropietario");
		
		if (idPropietario != null) {
			request.removeAttribute("idCandidato");
			request.setAttribute("idCandidato", idPropietario);
			request.removeAttribute("volverPreguntarConocer");			
			
			request.getSession().removeAttribute("volverPreguntarConocer");			
			request.getSession().setAttribute("volverPreguntarConocer", String.valueOf(Constantes.VOLVER_PREGUNTAR.NO.getIdOpcion()));
		}

        return mapping.findForward(FORWARD_JSP);
	}

	public ActionForward registrarConocerConfig(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){

		String idCandidato = request.getParameter("idCandidato");
		
		ConocerConfigVO vo = new ConocerConfigVO();

		vo.setIdCandidato(Utils.parseLong(idCandidato));
		
		try {
			CandidatoBusDelegate service = CandidatoBusDelegateImpl.getInstance();
			
			ConocerConfigVO conocerConfigVO = service.consultaConocerConfigByIdCandidato(Utils.parseLong(idCandidato));		
			
			if (conocerConfigVO != null){
				vo.setDeseaPublicar(conocerConfigVO.getDeseaPublicar());			
			}		
	
			if ("Si".equalsIgnoreCase(request.getParameter("respuesta"))){
				vo.setPublicarEnPerfil(Utils.toLong(Constantes.PUBLICAR_ESTANDARES.SI.getIdOpcion()));
				vo.setDeseaPublicar(Utils.toLong(Constantes.PUBLICAR_EN_PERFIL.SI.getIdOpcion()));
			} else {
				vo.setPublicarEnPerfil(Utils.toLong(Constantes.PUBLICAR_ESTANDARES.NO.getIdOpcion()));
				// si el candidato no quiere ver en su perfil las certificaciones, asumimos que tampoco querrá que se publiquen en su detalle
				vo.setDeseaPublicar(Utils.toLong(Constantes.PUBLICAR_EN_PERFIL.NO.getIdOpcion()));
			}
			
			if ("Si".equalsIgnoreCase(request.getParameter("checkVolverPreguntar")))		
				vo.setVolverPreguntar(Utils.toLong(Constantes.VOLVER_PREGUNTAR.SI.getIdOpcion()));		
			else 
				vo.setVolverPreguntar(Utils.toLong(Constantes.VOLVER_PREGUNTAR.NO.getIdOpcion()));

			service.registrarConocerConfig(vo);
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		 
		//request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        //return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
		return null;
	}

	public ActionForward toSWBConocer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.setAttribute(URL_REDIRECT_SWB, properties.getProperty("app.swb.redirect.conocer.competencias"));
		return mapping.findForward(FORWARD_REDIRECT_SWB);
	}

}
