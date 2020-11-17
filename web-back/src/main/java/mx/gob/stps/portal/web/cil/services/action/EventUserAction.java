package mx.gob.stps.portal.web.cil.services.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_FORM;
import static mx.gob.stps.portal.web.infra.utils.Constantes.REGRESO;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.core.cil.bo.AttentionRequest;
import mx.gob.stps.portal.core.cil.vo.BitacoraAtencionVO;
import mx.gob.stps.portal.web.cil.form.EventUserForm;
import mx.gob.stps.portal.web.cil.delegate.CilServicesBusDelegate;
import mx.gob.stps.portal.web.cil.delegate.impl.CilServicesBusDelegateImpl;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_USUARIO;

public class EventUserAction extends GenericAction {
	
	private static final int CIL_TIMER = 1000*60*60*3;

	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		long idCandidato = 0;
		java.util.Date date = new Date();
		EventUserForm event = (EventUserForm)form;
		UsuarioWebVO usuario = this.getUsuario(request.getSession());
		CilServicesBusDelegate services = CilServicesBusDelegateImpl.getInstance();
		long idCil = (null != request.getSession().getAttribute("idCil") ? (Long)request.getSession().getAttribute("idCil") : 0);
		if(isLogged(request.getSession()) && null != usuario && usuario.getIdTipoUsuario() == TIPO_USUARIO.CANDIDATO.getIdTipoUsuario())
			idCandidato = usuario.getIdPropietario();
		try {
			AttentionRequest previousAttention = services.findAttentionRequest(idCil, idCandidato);
			if (null != previousAttention.getFechaInicio() && date.getTime() - previousAttention.getFechaInicio().getTime() < CIL_TIMER) {
				event.setAccesoscv((null != previousAttention.getActualizarCV() ? parseLbl(previousAttention.getActualizarCV().getContador()) : ""));
				event.setBolsas((null != previousAttention.getOtrasBolsas() ? previousAttention.getOtrasBolsas().getDetalle() : ""));
				event.setFotocopias((null != previousAttention.getFotocopias() ? parseLbl(previousAttention.getFotocopias().getContador()) : ""));
				event.setImpresioncv((null != previousAttention.getImpresion() ? parseLbl(previousAttention.getImpresion().getContador()) : ""));
				event.setLlamadas((null != previousAttention.getLlamadas() ? parseLbl(previousAttention.getLlamadas().getContador()) : ""));
				event.setPortal((null != previousAttention.getActividadPortal() ? previousAttention.getActividadPortal().getDetalle() : ""));
			}
		} catch (Exception e) { e.printStackTrace(); }
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Eventos del usuario");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Reg&iacute;strate en nuestra Bolsa de trabajo y postula la oferta de empleo que m&aacute;s se apegue seg&uacute;n tu curriculum vitae sin costo f&aacute;cil y sin intermediarios.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_FORM);
	}
	
	/**
	 * Método que guarda la atención proporcionada en el CIL a un candidato
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward saveEvent(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		long idAtencion = 0;
		long idCandidato = 0;
		UsuarioWebVO usuario = this.getUsuario(request.getSession());
		long idCil = (null != request.getSession().getAttribute("idCil") ? (Long)request.getSession().getAttribute("idCil") : 0);
		if(isLogged(request.getSession()) && null != usuario && usuario.getIdTipoUsuario() == TIPO_USUARIO.CANDIDATO.getIdTipoUsuario())
			idCandidato = usuario.getIdPropietario();
		BitacoraAtencionVO updCV = new BitacoraAtencionVO(0, 0, 0, Constantes.TIPO_SERVICIO.ACTUALIZAR_CV.getIdOpcion(), null, "", parseInt(request.getParameter("accesoscv")));
		BitacoraAtencionVO prnCV = new BitacoraAtencionVO(0, 0, 0, Constantes.TIPO_SERVICIO.IMPRESION.getIdOpcion(), null, "", parseInt(request.getParameter("impresioncv")));
		BitacoraAtencionVO phoCY = new BitacoraAtencionVO(0, 0, 0, Constantes.TIPO_SERVICIO.FOTOCOPIAS.getIdOpcion(), null, "", parseInt(request.getParameter("fotocopias")));
		BitacoraAtencionVO phoCL = new BitacoraAtencionVO(0, 0, 0, Constantes.TIPO_SERVICIO.TELEFONO.getIdOpcion(), null, "", parseInt(request.getParameter("llamadas")));
		BitacoraAtencionVO athSR = new BitacoraAtencionVO(0, 0, 0, Constantes.TIPO_SERVICIO.OTRAS_BOLSAS.getIdOpcion(), null, request.getParameter("bolsas"), 0);
		BitacoraAtencionVO actPE = new BitacoraAtencionVO(0, 0, 0, Constantes.TIPO_SERVICIO.ACTIVIDAD_PORTAL.getIdOpcion(), null, request.getParameter("portal"), 0);
		AttentionRequest attention = new AttentionRequest(idCil, idCandidato, idAtencion, null, updCV, prnCV, phoCY, phoCL, athSR, actPE);
		CilServicesBusDelegate services = CilServicesBusDelegateImpl.getInstance();
		try {
			int result = 0;
			if (idCandidato > 0 && idCil > 0) {
				result = services.saveAttentionRequest(attention);
				if (result > 0) {
					String redirect = (null != request.getSession().getAttribute("LOGOUT_CIL") ? (String)request.getSession().getAttribute("LOGOUT_CIL") : "");
					if (redirect.equals(request.getContextPath() + "/servicioscil.do?method=init")) {						
						request.getSession().removeAttribute("idCil");
						request.getSession().removeAttribute("USER_CIL_ON");
						request.getSession().setAttribute(BODY_JSP, mapping.findForward(REGRESO).getPath());
					}else 
						request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
					request.getSession().setAttribute("LOGOUT_PERMITTED", "CIL");
				}
			}
		} catch (Exception e) { 
			e.printStackTrace();
			request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		}
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Eventos del usuario");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Reg&iacute;strate en nuestra Bolsa de trabajo y postula la oferta de empleo que m&aacute;s se apegue seg&uacute;n tu curriculum vitae sin costo f&aacute;cil y sin intermediarios.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_FORM);
	}
	
	private int parseInt(String numero) {
	    int value = 0;
	    if (Utils.esEntero(numero))
	    	value = Integer.parseInt(numero);
	    return value;
	}
	
	private String parseLbl(long number) {
		if (number < 1)
			return "";
		else return String.valueOf(number);
	}
	
	/*public ActionForward addPhone(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		@SuppressWarnings("unchecked")
		List<BitacoraAtencionVO> calList = (List<BitacoraAtencionVO>)request.getSession().getAttribute(CIL_PHONES_LIST);		
		if (calList==null){
			calList = new ArrayList<BitacoraAtencionVO>();
			request.getSession().setAttribute(CIL_PHONES_LIST, calList);
		}
		String telefono = (null != request.getParameter("telefono") ? request.getParameter("telefono") : "");
		BitacoraAtencionVO phnCL = new BitacoraAtencionVO(0, 0, 0, Constantes.TIPO_ATENCION.TELEFONO.getIdOpcion(), null, telefono, parseInt(request.getParameter("llamadas")));
		calList.add(phnCL);
        return mapping.findForward("ACTION_REGISTROS_TABLA");
	}
	
	public ActionForward deletePhone(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String phone = request.getParameter("telefono");
		@SuppressWarnings("unchecked")
		List<BitacoraAtencionVO> calList = (List<BitacoraAtencionVO>)request.getSession().getAttribute(CIL_PHONES_LIST);
		if (phone!=null && calList!=null && !calList.isEmpty()) {
			int index = 0;
			boolean found = false;
			for (index = 0; index < calList.size(); index++) {
				if (calList.get(index).getDetalle().equals(phone)){
					found = true;
					break;
				}
			}
			if (found) calList.remove(index);
		}
        return mapping.findForward("ACTION_REGISTROS_TABLA");
	}*/
}