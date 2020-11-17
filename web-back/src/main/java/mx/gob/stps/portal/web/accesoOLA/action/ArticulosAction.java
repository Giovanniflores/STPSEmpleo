package mx.gob.stps.portal.web.accesoOLA.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_REDIRECT_SWB;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_REDIRECT_SWB;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.utils.Utils;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Direcciona a recursos de Articulos publicados en SWB
 * 
 * @author oscar.manzo
 *
 */
public final class ArticulosAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		PropertiesLoader properties = PropertiesLoader.getInstance();

		int idArticulo = Utils.parseInt(request.getParameter("idArticulo"));
		
		String url = null;
		
		switch(idArticulo){
			case 1: url = properties.getProperty("miespacio.articulo.tips"); break;
			case 2: url = properties.getProperty("miespacio.articulo.foro"); break;

			case 3: url = properties.getProperty("miespacio.articulo.recom.tendencias"); break;
			case 4: url = properties.getProperty("miespacio.articulo.recom.sectores"); break;
			case 5: url = properties.getProperty("miespacio.articulo.recom.carreras"); break;

			case 6: url = properties.getProperty("miespacio.articulo.conoce.vacantes"); break;
			case 7: url = properties.getProperty("miespacio.articulo.conoce.capacitacion"); break;
			case 8: url = properties.getProperty("miespacio.articulo.conoce.fonacot"); break;
		}
				
		request.setAttribute(URL_REDIRECT_SWB, url);
		return mapping.findForward(FORWARD_REDIRECT_SWB);
	}
}