package mx.gob.stps.portal.movil.web.candidato.action;

import static mx.gob.stps.portal.movil.web.infra.utils.ConstantesMovil.FORWARD_JSP;
import static mx.gob.stps.portal.movil.web.infra.utils.ConstantesMovil.USERLOGGED;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.PERFIL;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaPostulacionVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.movil.web.candidato.util.UtilPostulate;
import mx.gob.stps.portal.movil.web.infra.utils.Utils;
import mx.gob.stps.portal.movil.web.oferta.delegate.OfertaDelegateImpl;
import mx.gob.stps.portal.movil.web.seguridad.vo.UsuarioFirmadoVO;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public final class PostulacionAction extends Action {

	private static final Logger logger = Logger.getLogger(PostulacionAction.class);
	
	private final static String PARAM_ID_OFERTA = "idOfertaEmpleo";
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String mensaje = "";
		
		long idOfertaEmpleo = Utils.parseLong(request.getParameter(PARAM_ID_OFERTA));

		if (idOfertaEmpleo <= 0){
			mensaje = "No se cuenta con el identificador de la Oferta";
		} else {

			UsuarioFirmadoVO usuario = (UsuarioFirmadoVO)session.getAttribute(USERLOGGED);

			if (usuario != null && usuario.getIdPerfil()==PERFIL.CANDIDATO.getIdOpcion()){
				long idCandidato = usuario.getIdPropietario();

				try {
					OfertaDelegateImpl services = OfertaDelegateImpl.getInstance();

					List<OfertaCandidatoVO> ofertasPostuladas = services.findByOfferCandidate(idOfertaEmpleo, idCandidato);
				
					if (ofertasPostuladas==null || ofertasPostuladas.isEmpty()) {
						OfertaCandidatoVO oferta = new OfertaCandidatoVO();
						oferta.setFechaAlta(Calendar.getInstance().getTime());
						oferta.setIdCandidato(idCandidato);
						oferta.setIdOfertaEmpleo(idOfertaEmpleo);
						oferta.setEstatus(ESTATUS.POSTULADO.getIdOpcion());
						oferta.setIdUsuario(usuario.getIdUsuario());
						services.postulaOferta(oferta);
					
					}else {
						for (OfertaCandidatoVO oferta : ofertasPostuladas){
							if (oferta.getEstatus() != ESTATUS.POSTULADO.getIdOpcion()){
								oferta.setEstatus(ESTATUS.POSTULADO.getIdOpcion());
								services.actualizaPostulacionOferta(oferta);
							}
						}
					}

					mensaje = "Ha sido postulado a la oferta.";

					UtilPostulate.increasePostCount(idOfertaEmpleo);
					UtilPostulate.avisoCandidatoPostuladoAction(idOfertaEmpleo, request);
				}catch(Exception e) {
					logger.error(e);
					mensaje = "Ocurrio un error durante el proceso, favor de notificar al administrador.";				
				}
			} else {
				mensaje = "No se cuenta con un usuario firmado o no corresponde a un Candidato.";
			}
		}
	
		session.setAttribute("errmsg", mensaje);
		
		return mapping.findForward(FORWARD_JSP);
	}
	
	
	
	/*public ActionForward offerPost(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		OfertaCandidatoVO vo = null;
		OfferQuestionForm offer = (OfferQuestionForm)form;
		OfferBusDelegate services = OfferBusDelegateImpl.getInstance();
		try {
			
			List<OfertaCandidatoVO> list = services.findByOfferCandidate(offer.getIdOfertaEmpleo(), offer.getIdCandidato());
			if (null == list || list.isEmpty()) {
				vo = new OfertaCandidatoVO();
				vo.setFechaAlta(Calendar.getInstance().getTime());
				vo.setIdCandidato(offer.getIdCandidato());
				vo.setIdOfertaEmpleo(offer.getIdOfertaEmpleo());
				vo.setEstatus(ESTATUS.POSTULADO.getIdOpcion());
				services.create(vo);
			}else {
				Iterator<OfertaCandidatoVO> it = list.iterator();
				while (it.hasNext()) {
					vo = it.next();
					if (vo.getEstatus() < ESTATUS.POSTULADO.getIdOpcion())
						vo.setEstatus(ESTATUS.POSTULADO.getIdOpcion());
					services.update(vo);
				}
			}
			
			offer.setPostulated(true);
			
			increasePostCount(offer.getIdOfertaEmpleo());
		}catch(Exception e) { logger.error(e); }
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        return mapping.findForward(FORWARD_TEMPLATE_OFFER);
    }
	*/
	
	/*private boolean isPostulated(long idOfertaEmpleo, long idCandidato) {
		boolean postulated = false;
		
		try {
			OfertaDelegateImpl services = OfertaDelegateImpl.getInstance();
			List<OfertaCandidatoVO> list = services.findByOfferCandidate(idOfertaEmpleo, idCandidato);
	
			if (list!=null && !list.isEmpty()) {
				for (OfertaCandidatoVO vo : list) {
					if (null != vo && vo.getEstatus() >= ESTATUS.POSTULADO.getIdOpcion()){
						postulated = true;
						break;
					}
				}
			}
		}catch(Exception e) { logger.error(e); }
		
		return postulated;
	}*/

	protected void redirectJsonResponse(HttpServletResponse response, String json) throws IOException{
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.flush();
		pw.close();
		response.flushBuffer();
	}

}
