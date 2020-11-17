package mx.gob.stps.portal.web.cil.services.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.formatDateForma;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_FORM;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.commons.beanutils.ConvertUtils;

import mx.gob.stps.portal.web.cil.form.TraceUserForm;
import mx.gob.stps.portal.web.cil.delegate.CilServicesBusDelegate;
import mx.gob.stps.portal.web.cil.delegate.impl.CilServicesBusDelegateImpl;

import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.utils.DateConverter;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.candidate.vo.PerfilVO;
import mx.gob.stps.portal.core.cil.bo.AttentionRequest;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_USUARIO;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;

public class TraceUserAction extends GenericAction {

	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		long idCandidato = 0;
		TraceUserForm trace = (TraceUserForm)form;
		trace.setMsg("");
		UsuarioWebVO user = this.getUsuario(request.getSession());
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMMMM yyyy hh:mm");
		long idCil = (null != request.getSession().getAttribute("idCil") ? (Long)request.getSession().getAttribute("idCil") : 0);
		if(isLogged(request.getSession()) && null != user && user.getIdTipoUsuario() == TIPO_USUARIO.CANDIDATO.getIdTipoUsuario()) {
			idCandidato = user.getIdPropietario();
			trace.setUserID(String.valueOf(idCandidato));
		}
		CilServicesBusDelegate cil = CilServicesBusDelegateImpl.getInstance();
		CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
		try {
			PerfilVO profile = services.initPerfil(idCandidato);
			String userName = (null != profile ? profile.getNombre() + " " + profile.getApellido1() + " " + profile.getApellido2() : "");
			trace.setUserName(userName);
			AttentionRequest attention = cil.findAttentionRequest(idCil, idCandidato);
			if (null != attention && !cil.existTraceUserEmployment(attention.getIdCil(), attention.getIdCandidato(), attention.getIdAtencion()))
				trace.setAttention(attention);
			else trace.setMsg("No tienes seguimiento de colocación pendiente");
			trace.setServicesDate(null != attention.getFechaInicio() ? sdf.format(attention.getFechaInicio()) : "");
		} catch (Exception e) { e.printStackTrace(); }
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Portal del empleo en M&eacute;xico");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Reg&iacute;strate en nuestra Bolsa de trabajo y postula la oferta de empleo que m&aacute;s se apegue seg&uacute;n tu curriculum vitae sin costo f&aacute;cil y sin intermediarios.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_FORM);
	}
	
	/**
	 * Método que guarda el seguimiento colocación en el CIL a un candidato
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward saveTrace(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		long idCandidato = 0;
		java.util.Date fechaSeguimiento = new Date();
		UsuarioWebVO user = this.getUsuario(request.getSession());
		long idCil = (null != request.getSession().getAttribute("idCil") ? (Long)request.getSession().getAttribute("idCil") : 0);
		if(isLogged(request.getSession()) && null != user && user.getIdTipoUsuario() == TIPO_USUARIO.CANDIDATO.getIdTipoUsuario())
			idCandidato = user.getIdPropietario();
		CilServicesBusDelegate services = CilServicesBusDelegateImpl.getInstance();
		try {
			int result = 0;
			if (idCandidato > 0 && idCil > 0) {
				int idCausa = Utils.parseInt(request.getParameter("idCausa"));
				int tipoSeguimiento = Utils.parseInt(request.getParameter("colocado"));
				int idAtencion = Utils.parseInt(request.getParameter("idAtencion"));
				String otraCausa = (null != request.getParameter("otraCausa") ? request.getParameter("otraCausa") : "");
				Date fechaColocacion = new Date();
				DateConverter dtConvert =  new DateConverter();
				dtConvert.setFormatPattern(formatDateForma);
				fechaColocacion = (Date)ConvertUtils.convert(request.getParameter("fechaColocacion"), Date.class);
				result = services.create(idCil, idCandidato, user.getIdUsuario(), tipoSeguimiento, fechaSeguimiento, Constantes.ESTATUS.ACTIVO.getIdOpcion(), idCausa, otraCausa, fechaColocacion, "", idAtencion);
				if(result>0)
					request.getSession().setAttribute("LOGOUT_PERMITTED", "CIL");								
			}
		} catch (Exception e) { e.printStackTrace(); }
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		return init(mapping, form, request, response);
	}
}