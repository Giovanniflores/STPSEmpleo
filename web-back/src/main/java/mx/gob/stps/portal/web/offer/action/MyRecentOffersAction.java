package mx.gob.stps.portal.web.offer.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.oferta.vo.MiOfertaRecienteVO;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.offer.delegate.RecentOfferSummaryBusDelegate;
import mx.gob.stps.portal.web.offer.delegate.RecentOfferSummaryBusDelegateImpl;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;


/**
 * Atiende la petici&oacute;n para mostrar el resumen de ofertas recientes de la
 * empresa.
 * @author jose.jimenez
 *
 */
public class MyRecentOffersAction extends GenericAction {

	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		UsuarioWebVO usuario = this.getUsuario(request.getSession());
		List<MiOfertaRecienteVO> results = null;
		
		if (usuario != null && usuario.getIdTipoUsuario() == Constantes.TIPO_USUARIO.EMPRESA.getIdTipoUsuario()) {
			RecentOfferSummaryBusDelegate services = RecentOfferSummaryBusDelegateImpl.getInstance();
			results = services.getMyRecentOffers(usuario.getIdPropietario());
			
			if (results == null || (results != null && results.isEmpty())) {
				ActionMessages messages = new ActionMessages();
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("emp.summary.msg"));
				saveMessages(request, messages);
			}
		}
		request.getSession().setAttribute("_urlpageinvoke", "miEspacioEmpresas.do");
		request.setAttribute("offers", results);
		return mapping.findForward(FORWARD_JSP);
	}

}
