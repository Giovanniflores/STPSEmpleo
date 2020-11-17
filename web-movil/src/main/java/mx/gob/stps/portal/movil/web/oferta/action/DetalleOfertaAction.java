package mx.gob.stps.portal.movil.web.oferta.action;

import static mx.gob.stps.portal.movil.web.infra.utils.ConstantesMovil.FORWARD_JSP;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaEmpleoJB;
import mx.gob.stps.portal.core.oferta.pregunta.vo.OfertaPreguntaVO;
import mx.gob.stps.portal.movil.web.candidato.util.UtilPostulate;
import mx.gob.stps.portal.movil.web.infra.action.GenericAction;
import mx.gob.stps.portal.movil.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.movil.web.infra.utils.MessageLoader;
import mx.gob.stps.portal.movil.web.infra.utils.PropertiesLoaderWeb;
import mx.gob.stps.portal.movil.web.infra.utils.Utils;
import mx.gob.stps.portal.movil.web.oferta.delegate.OfertaDelegateImpl;
import mx.gob.stps.portal.movil.web.oferta.form.OfertaJB;
import mx.gob.stps.portal.movil.web.oferta.form.OfferQuestionForm;
import mx.gob.stps.portal.movil.web.seguridad.vo.UsuarioFirmadoVO;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class DetalleOfertaAction extends GenericAction {
	private static final Logger logger = Logger.getLogger(DetalleOfertaAction.class);

	private static final String PARAM_ID_OFERTA_EMPLEO = "idOfertaEmpleo";
	private static final String FORWARD_PREGUNTAS_OFERTA = "JSP_PREGUNTAS_OFERTA";
	private static final String FORWARD_RESPONDER_PREGUNTA_OFERTA = "JSP_RESPONDER_PREGUNTA";
	
	private static final String PREVIEW = "preview";
	
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		OfferQuestionForm offer = (OfferQuestionForm)form;

		int idOfertaEmpleo = Utils.parseInt(request.getParameter(PARAM_ID_OFERTA_EMPLEO));

		String preview = request.getParameter(PREVIEW);
		if (preview!=null)
			session.setAttribute(PREVIEW, preview);
		else
			session.removeAttribute(PREVIEW);

		try{
			if (idOfertaEmpleo > -1) {

				offer.setIdOfertaEmpleo(idOfertaEmpleo);

				OfertaJB ofertaJB = getoffer(idOfertaEmpleo); /** Se consulta el detalle de la oferta de empleo **/
				offer.setOffer(ofertaJB);
				UsuarioFirmadoVO usuarioFirmado = getUsuarioFirmado(session);

				int compatibilityLimit = PropertiesLoaderWeb.getInstance().getPropertyInt("compatibility.upper.limit");

				//increaseOfferCount(idOfertaEmpleo); // TODO Se contabiliza la consulta de la oferta

				if (usuarioFirmado!=null && usuarioFirmado.isCandidato()) {
					offer.setIdCandidato(usuarioFirmado.getIdPropietario());
					
					boolean postulated = UtilPostulate.isPostulated(idOfertaEmpleo, usuarioFirmado.getIdPropietario());
					offer.setPostulated(postulated);
					
					int compatibility = (int)calculaCompatibilidad(idOfertaEmpleo, usuarioFirmado.getIdPropietario());
					offer.setCompatibility(compatibility);

					offer.setCompatibilityLimit(compatibilityLimit);
				}
				
				if(usuarioFirmado.isEmpresa())offer.setNumeroPreguntasOferta(obtienePreguntasOferta(idOfertaEmpleo).size());
				
				
			}

		} catch (Exception se) {
			se.printStackTrace(); logger.error(se);
		}

		return mapping.findForward(FORWARD_JSP);
	}
	
	public ActionForward activaOferta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		OfferQuestionForm forma = (OfferQuestionForm) form;

		long idOfertaEmpleo = forma.getIdOfertaEmpleo();
		MessageLoader messageLoader = MessageLoader.getInstance();
		String mensaje = "";

		try{
			UsuarioFirmadoVO usuarioFirmado = getUsuarioFirmado(session);
			
			OfertaDelegateImpl services = OfertaDelegateImpl.getInstance();
			services.activaOfertaEmpleo(idOfertaEmpleo, usuarioFirmado.getIdUsuario());
			services.actualizarFechaOfertaCanceladaActiva(idOfertaEmpleo);

			mensaje = messageLoader.getMessage("oferta.detalle.msg.activa.exito");
		}catch (BusinessException e) {
			logger.error(e);
			mensaje = messageLoader.getMessage("oferta.detalle.msg.activa.error");
		} catch (ServiceLocatorException e) {
			logger.error(e);
			mensaje = messageLoader.getMessage("errors.serviceLocatorException");
		} catch (Exception e) {
			logger.error(e);
			mensaje = messageLoader.getMessage("errors.exception");
		} finally{
			request.getSession().setAttribute("errmsg", mensaje);
		}

		return init(mapping,form,request,response);		
	}

	public ActionForward cancelaOferta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws BusinessException{
		HttpSession session = request.getSession();
		OfferQuestionForm forma = (OfferQuestionForm) form;

		long idOfertaEmpleo = forma.getIdOfertaEmpleo();
		MessageLoader messageLoader = MessageLoader.getInstance();
		String mensaje = "";
		try{
			UsuarioFirmadoVO usuarioFirmado = getUsuarioFirmado(session);

			OfertaDelegateImpl services = OfertaDelegateImpl.getInstance();
			services.cancelaOfertaEmpleo(idOfertaEmpleo, usuarioFirmado.getIdUsuario());

			mensaje = messageLoader.getMessage("oferta.detalle.msg.cancelada.exito");
		}catch (ServiceLocatorException e) {
			logger.error(e);
			mensaje = messageLoader.getMessage("oferta.detalle.msg.cancelada.error");
		}finally{

			request.getSession().setAttribute("errmsg", mensaje);

		}

		return init(mapping,form,request,response);	
	}
	
	public ActionForward preguntasOferta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		OfferQuestionForm forma = (OfferQuestionForm) form;
		logger.info("path "+mapping.findForward(FORWARD_PREGUNTAS_OFERTA).getPath());
		long idOfertaEmpleo = forma.getIdOfertaEmpleo();
		
		List<OfertaPreguntaVO> preguntas = new ArrayList<OfertaPreguntaVO>();
		
		preguntas = obtienePreguntasOferta(idOfertaEmpleo);
		
		forma.setOfertaPreguntasList(preguntas);
		forma.setIdOfertaEmpleo(idOfertaEmpleo);
		forma.setIdEmpresa(forma.getIdEmpresa());
		forma.setEmpresaNombre(forma.getEmpresaNombre());
		forma.setTituloOferta(forma.getTituloOferta());
		forma.setTipoContrato(forma.getTipoContrato());
		forma.setHorarioLaboral(forma.getHorarioLaboral());

		return mapping.findForward(FORWARD_PREGUNTAS_OFERTA);
	}
	
	
	public ActionForward responderPregunta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		
		OfferQuestionForm forma = (OfferQuestionForm) form;
		
		forma.setIdOfertaEmpleo(forma.getIdOfertaEmpleo());
		forma.setIdEmpresa(forma.getIdEmpresa());
		forma.setEmpresaNombre(forma.getEmpresaNombre());
		forma.setTituloOferta(forma.getTituloOferta());
		forma.setTipoContrato(forma.getTipoContrato());
		forma.setHorarioLaboral(forma.getHorarioLaboral());
		forma.setIdOfertaPregunta(forma.getIdOfertaPregunta());
		forma.setPregunta(forma.getPregunta());
		
		return mapping.findForward(FORWARD_RESPONDER_PREGUNTA_OFERTA);
	}
	
	public ActionForward registraRespuesta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		
		OfferQuestionForm forma = (OfferQuestionForm) form;
		
		String respuesta = forma.getRespuesta();
		long idOfertaPregunta = forma.getIdOfertaPregunta();
		
		try {
			OfertaDelegateImpl services = OfertaDelegateImpl.getInstance();
			OfertaPreguntaVO ofertaPreguntaVO = services.obtieneOfertaPregunta(idOfertaPregunta);
			if (null != respuesta && !"".equals(respuesta) && idOfertaPregunta > -1) {
			ofertaPreguntaVO.setRespuesta(respuesta);
			ofertaPreguntaVO.setFechaRespuesta(Calendar.getInstance().getTime());
			services.updateOfertaPregunta(ofertaPreguntaVO);
		}
			
			
		} catch (BusinessException e) {
			logger.error(e);
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			logger.error(e);
			e.printStackTrace();
		}
		
		return preguntasOferta(mapping, form, request,response);
	}

	private List<OfertaPreguntaVO> obtienePreguntasOferta(long idOfertaEmpleo) {
		ArrayList<OfertaPreguntaVO> list = null;
		try {
			OfertaDelegateImpl services = OfertaDelegateImpl.getInstance();
			list = (ArrayList<OfertaPreguntaVO>)services.ofertaPreguntasList(idOfertaEmpleo);
		}catch (BusinessException be) { logger.error(be);
		}catch (ServiceLocatorException le) { logger.error(le); }

		if (null == list)
			list = new ArrayList<OfertaPreguntaVO>();
		
		return list;
	}

	private OfertaJB getoffer(long offerID) {
		OfertaJB offer = new OfertaJB();

		try {
			OfertaDelegateImpl services = OfertaDelegateImpl.getInstance();
			OfertaEmpleoJB jb = services.buscarOfertaEmpleo(offerID);

			BeanUtils.copyProperties(offer, jb);
			
		}catch (BusinessException be) { logger.error(be);
		}catch (ServiceLocatorException le) { logger.error(le);
		} catch (IllegalAccessException iae) { iae.printStackTrace();
		} catch (InvocationTargetException ite) { ite.printStackTrace();}

		return offer;
	}
	
	
	private long calculaCompatibilidad(long idOfertaEmpleo, long idCandidato){
		long compatibility = 0;
		
		try {
			OfertaDelegateImpl services = OfertaDelegateImpl.getInstance();
			compatibility = services.match(idOfertaEmpleo, idCandidato);
		}catch(Exception e) { logger.error(e); }
		
		return compatibility;
	}


}
