package mx.gob.stps.portal.web.testimony.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.ATRIB_TESTIMONIO_OUT;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.testimonio.vo.TestimonioVO;
import mx.gob.stps.portal.web.infra.utils.Constantes;
import mx.gob.stps.portal.web.testimony.delegate.TestimonioBusDelegateImpl;
import mx.gob.stps.portal.web.testimony.form.TestimonioForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class TestimonioIndexAction extends Action {	
	private static Logger logger = Logger.getLogger(TestimonioIndexAction.class);

	/**
	 * Recupera el testimonio y el propietario de dicho testimonio para desplegarlos en pantalla
	 */
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		TestimonioForm testimonioform = (TestimonioForm)form;

		TestimonioVO vo = null;

		if (session!=null)
			vo = (TestimonioVO)session.getAttribute(ATRIB_TESTIMONIO_OUT);

		if (vo==null)
			vo = testimonioAleatorio();

		if (vo!=null){
			testimonioform.setNombre(vo.getNombre());
			testimonioform.setTestimonio(vo.getDescripcion());

			if (session!=null)
				session.setAttribute(Constantes.ATRIB_TESTIMONIO_OUT, vo);
		}		

		return mapping.findForward(FORWARD_JSP);
	}

	/*public ActionForward tocandidato(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		return getForwardMiEspacioCandidato(mapping, request);
	}

	public ActionForward toempresa(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		return getForwardMiEspacioEmpresa(mapping, request);
	}

    private ActionForward getForwardMiEspacioCandidato(ActionMapping mapping, HttpServletRequest request){
		return getForward("app.swb.redirect.miespacio.candidato", mapping, request);
    }

    private ActionForward getForwardMiEspacioEmpresa(ActionMapping mapping, HttpServletRequest request){
		return getForward("app.swb.redirect.miespacio.empresa", mapping, request);
    }

    private ActionForward getForward(String key, ActionMapping mapping, HttpServletRequest request){
    	PropertiesLoader properties = PropertiesLoader.getInstance();
		String urlredirect = properties.getProperty(key);
		request.setAttribute(URL_REDIRECT_SWB, urlredirect);
		ActionForward forward = mapping.findForward(FORWARD_REDIRECT_SWB);
		return forward;
    }*/

	/**
	 * Invoca la generacion de un testimonio aleatorio para ser desplegado en la pagina de inicio
	 * @return TestimonioVO que trae el testimonio y el propietario del testimonio
	 */
	private TestimonioVO testimonioAleatorio(){
		TestimonioVO vo = new TestimonioVO();
		
		try {
			vo = TestimonioBusDelegateImpl.getInstance().recuperaDatosIndex();
		}catch (Exception e) {
			logger.error(e);
		}
		
		return vo;
	}

}