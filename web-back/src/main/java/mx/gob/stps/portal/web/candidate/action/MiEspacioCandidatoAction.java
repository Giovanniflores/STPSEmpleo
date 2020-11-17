package mx.gob.stps.portal.web.candidate.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.CANDIDATO_HEAD;
import static mx.gob.stps.portal.web.infra.utils.Constantes.EMPRESA_HEAD;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_REDIRECT_SWB;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_MI_ESP_CAND;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_REDIRECT_SWB;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.empresa.vo.EmpresaUsuarioVO;
import mx.gob.stps.portal.exception.TechnicalException;
import mx.gob.stps.portal.persistencia.vo.EmpresaVO;
import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.utils.Password;
import mx.gob.stps.portal.utils.pdform.RequirementsToPPCSDVO;
import mx.gob.stps.portal.utils.pdform.RequirementsToPpcSdPdfFormFiller;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.vo.CandidatoAjaxVO;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.Map;

public class MiEspacioCandidatoAction extends GenericAction {

    private static Logger logger = Logger.getLogger(MiEspacioCandidatoAction.class);

    private static final String ERROR_MSG = "ERROR_MSG";

	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		return null;
	}
	
	public ActionForward agendaCita(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			//PropertiesLoader properties = PropertiesLoader.getInstance();
			UsuarioWebVO usuario = getUsuario(request.getSession());
			
			EmpresaVO empresa = (EmpresaVO) request.getSession().getAttribute(EMPRESA_HEAD);
			CandidatoAjaxVO candidato = (CandidatoAjaxVO) request.getSession().getAttribute(CANDIDATO_HEAD);
			String url = null;
			if(usuario==null){
				if(request.getRequestURL().toString().contains("172.18.29.")){
					url = "http://qa.empleo.gob.mx/citas/content/common/home.jsf?idUsuario=K0A6+GVTjxlhvhckrVMhLg==&idEntidad=K0A6+GVTjxlhvhckrVMhLg==&idFuente=d37hxgnMIH5kZZcmKxKmUQ==&idCandidato=K0A6+GVTjxlhvhckrVMhLg==&candidato=K0A6+GVTjxlhvhckrVMhLg==";
				}else if(request.getRequestURL().toString().contains("qa.empleo.gob.mx")){
					url = "http://qa.empleo.gob.mx/citas/content/common/home.jsf?idUsuario=K0A6+GVTjxlhvhckrVMhLg==&idEntidad=K0A6+GVTjxlhvhckrVMhLg==&idFuente=d37hxgnMIH5kZZcmKxKmUQ==&idCandidato=K0A6+GVTjxlhvhckrVMhLg==&candidato=K0A6+GVTjxlhvhckrVMhLg==";
				}else{
					url = "https://www.empleo.gob.mx/citas/content/common/home.jsf?idUsuario=K0A6+GVTjxlhvhckrVMhLg==&idEntidad=K0A6+GVTjxlhvhckrVMhLg==&idFuente=d37hxgnMIH5kZZcmKxKmUQ==&idCandidato=K0A6+GVTjxlhvhckrVMhLg==&candidato=K0A6+GVTjxlhvhckrVMhLg==";
				}
				
			}else{
				logger.info(usuario.getIdUsuario()+"|"+usuario.getIdEntidad()+"|"+usuario.getIdPropietario());
				String idUsuario = Password.encrypt(usuario.getIdUsuario() + "");
				String idEntidad = Password.encrypt(usuario.getIdEntidad() + "");
				String idFuente = Password.encrypt(Catalogos.APLICACION.PORTAL_DEL_EMPLEO.getIdOpcion() + "");		
				String idCandidato = Password.encrypt(usuario.getIdPropietario() + "");
				String nombreCandidato =	"";
				if(candidato	==	null){
					
					nombreCandidato = Password.encrypt(usuario.getUsuario()	==	null?"Portal":usuario.getUsuario());
				}else{
					nombreCandidato = Password.encrypt(candidato.getNombre());
					
				}
				/*
				//String url = properties.getProperty("app.login.redirect.portal.citas") + "?idUsuario=" + idUsuario 
				//		+ "&idEntidad=" + idEntidad + "&idFuente=" + idFuente + "&idCandidato=" + idCandidato;					
				*/
				if(request.getRequestURL().toString().contains("172.18.29.")){
					url = "http://qa.empleo.gob.mx/citas/content/common/home.jsf?idUsuario=" + idUsuario 
							+ "&idEntidad=" + idEntidad + "&idFuente=" + idFuente + "&idCandidato=" + idCandidato
							+ "&candidato=" + nombreCandidato;
				}else if(request.getRequestURL().toString().contains("qa.empleo.gob.mx")){
					url = "http://qa.empleo.gob.mx/citas/content/common/home.jsf?idUsuario=" + idUsuario 
							+ "&idEntidad=" + idEntidad + "&idFuente=" + idFuente + "&idCandidato=" + idCandidato
							+ "&candidato=" + nombreCandidato;
				}else{
					url = "https://www.empleo.gob.mx/citas/content/common/home.jsf?idUsuario=" + idUsuario 
							+ "&idEntidad=" + idEntidad + "&idFuente=" + idFuente + "&idCandidato=" + idCandidato
							+ "&candidato=" + nombreCandidato;
				}
				
				/*url = "https://www.empleo.gob.mx/citas/content/common/home.jsf?idUsuario=" + idUsuario 
								+ "&idEntidad=" + idEntidad + "&idFuente=" + idFuente + "&idCandidato=" + idCandidato
								+ "&candidato=" + nombreCandidato;*/	
			}
			
			request.setAttribute(URL_REDIRECT_SWB, url);
			return mapping.findForward(FORWARD_REDIRECT_SWB);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward toBusquedaEspecifica(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.setAttribute(URL_REDIRECT_SWB, properties.getProperty("app.swb.redirect.busqueda.especifica"));
		return mapping.findForward(FORWARD_REDIRECT_SWB);
	}

	public ActionForward toBusquedaPalabraClave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.setAttribute(BODY_JSP, mapping.findForward("JSP_BUSQUEDA_PALABRA").getPath());
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Mi espacio candidato");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Espacio Candidato, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);
	}
	
	public ActionForward toOcupate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		PropertiesLoader properties = PropertiesLoader.getInstance();
		
		String searchQ = request.getParameter("searchQ");
		String searchTopic = request.getParameter("searchTopic");
		String searchPlace = request.getParameter("searchPlace");
		
		if (searchQ==null) searchQ = "";
		else searchQ = searchQ.trim();

		if (searchTopic==null) searchTopic = "";
		else searchTopic = searchTopic.trim();
		
		if (searchPlace==null) searchPlace = "";
		else searchPlace = searchPlace.trim();
		
		String urlOcupate = properties.getProperty("app.swb.redirect.busqueda.palabra.clave");
		urlOcupate += "?method=init&searchQ="+ searchQ +"&searchTopic="+ searchTopic +"&searchPlace="+ searchPlace;
		
		request.setAttribute(URL_REDIRECT_SWB, urlOcupate);
		return mapping.findForward(FORWARD_REDIRECT_SWB);
	}

    public ActionForward terminosCondicionesPpcEnPdf(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ServiceLocatorException, TechnicalException {

        long idUsuario = getUsuario(request.getSession()).getIdUsuario();

        CandidatoBusDelegate service = CandidatoBusDelegateImpl.getInstance();
        CandidatoVo candidatoVo = service.findPpcSdTermsAndConditionsData(idUsuario);

        assert candidatoVo != null : "Something went wrong; candidatoVo object is not supposed to be null, therefore pdf file cannot be created";

        RequirementsToPPCSDVO requirementsToPPCSDVO = new RequirementsToPPCSDVO();
        requirementsToPPCSDVO.setAddressState(candidatoVo.getDomicilioVo().getEntidad());
        requirementsToPPCSDVO.setAddressMunicipality(candidatoVo.getDomicilioVo().getMunicipio());
        requirementsToPPCSDVO.setAcceptanceDateOfPPCSD(candidatoVo.getPpcFechaInscripcion());
        StringBuilder fullName = new StringBuilder();
        fullName.append(candidatoVo.getNombre()).append(" ").append(candidatoVo.getApellido1());
        if(candidatoVo.getApellido2() != null) {
            fullName.append(" ").append(candidatoVo.getApellido2());
        }
        requirementsToPPCSDVO.setFullName(fullName.toString());
        requirementsToPPCSDVO.setCurp(candidatoVo.getCurp());
        requirementsToPPCSDVO.setNss(candidatoVo.getNss());

        Map<String,String> pdfFormDataMap = null;
        try {
            pdfFormDataMap = requirementsToPPCSDVO.toPdfFormDataMap();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return mapping.getInputForward();   // Do not exist
        }

        String pdfOutputName = RequirementsToPpcSdPdfFormFiller.REQUIREMENTS_TO_PPCSD_PDF_SUGGESTED_OUTPUT_NAME;

        RequirementsToPpcSdPdfFormFiller.createRequirementsToPpcSdPdfFormAndWriteIntoHttpResponseStream(pdfOutputName, response, pdfFormDataMap);

        return null;
    }
}