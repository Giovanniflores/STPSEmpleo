package mx.gob.stps.portal.web.candidate.action;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_MOTIVOS_NO_ACEPTACION;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public final class MotivoAction extends GenericAction {

  /**
	 * Consulta el catalogo de motivos de no aceptacion para llenar el combo
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
    public ActionForward cargarMotivos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		List<CatalogoOpcionVO> opciones = new ArrayList<CatalogoOpcionVO>();

		try {
			opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_MOTIVOS_NO_ACEPTACION);

			CatalogoVO cat = getCatalogo(opciones);
			String json = toJson(cat);
			redirectJsonResponse(response, json);

		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		return null;
	}
}