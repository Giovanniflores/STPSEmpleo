package mx.gob.stps.portal.web.company.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_FORM;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_ESP_ADMIN;
import static mx.gob.stps.portal.web.infra.utils.Constantes.JSP_SUCCESS;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.utils.Constantes.PERFIL;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegate;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegateImpl;
import mx.gob.stps.portal.web.company.form.ShowMoreCompanyOffersForm;
import mx.gob.stps.portal.web.infra.action.PagerAction;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.oferta.delegate.OfertaBusDelegate;
import mx.gob.stps.portal.web.oferta.delegate.OfertaBusDelegateImpl;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ShowMoreCompanyOffersAction extends PagerAction{

	private static Logger logger = Logger.getLogger(ShowMoreCompanyOffersAction.class);	
	
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		ShowMoreCompanyOffersForm showForm = (ShowMoreCompanyOffersForm)form;
		showForm.reset();

		long idEmpresa = Utils.parseLong(request.getParameter("idEmpresa"));	
		
		UsuarioWebVO webVo = getUsuario(request.getSession());

		if (null != webVo) {
			showForm.setIdUsuario(webVo.getIdUsuario());
			showForm.setIdTipoUsuario(webVo.getIdTipoUsuario());
			showForm.setIdPerfil(webVo.getIdPerfil());
		}

		showForm.setIdEmpresa(idEmpresa);
		
		try {
			List<OfertaPorCanalVO> lstOfertas = null;
			
			if (idEmpresa > 0){
				
				RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance();
				OfertaBusDelegate offerService = OfertaBusDelegateImpl.getInstance();

				EmpresaVO empresaVo = service.findEmpresaById(idEmpresa);

				showForm.setNombreEmpresa(empresaVo.getNombreEmpresa());
				showForm.setDescripcion(empresaVo.getDescripcion());
				showForm.setPaginaWeb(empresaVo.getPaginaWeb());

				lstOfertas = offerService.consultaOfertasXCanal(idEmpresa);

				if(null==lstOfertas){
					lstOfertas = new ArrayList<OfertaPorCanalVO>();
					showForm.setLstOfertas(lstOfertas);	
				} else {
					List<OfertaPorCanalVO> lstOfertasRecientes  = sortOffersByDate(lstOfertas);
					showForm.setLstOfertas(lstOfertasRecientes);				
				}
			}else{
				logger.error("Identificador de empresa requerido");
				lstOfertas = new ArrayList<OfertaPorCanalVO>();
			}
			
			showForm.setLstOfertas(lstOfertas);
			request.getSession().setAttribute(FULL_LIST, lstOfertas);			
			request.getSession().setAttribute("_urlpageinvoke", "showMoreCompanyOffers.do?method=init&idEmpresa=" + idEmpresa);
			
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error(e); registraError(request, "app.exp.negocio.err");
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e); registraError(request, "app.exp.negocio.err");
	    } catch (ServiceLocatorException e) {
	    	e.printStackTrace();
	    	logger.error(e);
	        registraError(request, "app.exp.locator.err");
	    } catch (Exception e) {
	    	e.printStackTrace();
	        logger.error(e);
	        registraError(request, "app.exp.negocio.err");
	    }

		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());

		String forwardTemplate = FORWARD_TEMPLATE_FORM;

		if (webVo!=null && PERFIL.ADMINISTRADOR.getIdOpcion()==webVo.getIdPerfil()) {
			forwardTemplate = FORWARD_TEMPLATE_ESP_ADMIN;
		}
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Mas ofertas de la compa&ntilde;ia");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Reg&iacute;strate en nuestra Bolsa de trabajo y postula la oferta de empleo que m&aacute;s se apegue seg&uacute;n tu curriculum vitae sin costo f&aacute;cil y sin intermediarios.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        return mapping.findForward(forwardTemplate);		
	}
	
	public ActionForward showOffer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		long idEmpresa = Utils.parseLong(request.getParameter("idEmpresa"));		
		request.getSession().setAttribute("ID_EMPRESA", idEmpresa);	
		long idOferta = Utils.parseLong(request.getParameter("idOferta"));		
		request.getSession().setAttribute("ID_OFERTA", idOferta);	
		
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(JSP_SUCCESS).getPath());
		return mapping.findForward(JSP_SUCCESS);		
	}	
	
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<OfertaPorCanalVO> sortOffersByDate(List<OfertaPorCanalVO> lstOfertas){
		List<OfertaPorCanalVO> lstSorted = lstOfertas;
        Collections.sort(lstSorted, new Comparator(){
          	 
            public int compare(Object o1, Object o2) {
            	OfertaPorCanalVO p1 = (OfertaPorCanalVO) o1;
            	OfertaPorCanalVO p2 = (OfertaPorCanalVO) o2;
            	java.util.Date dt1 = p1.getFechaFin();
            	java.util.Date dt2 = p2.getFechaFin();
            	
            	return dt2.compareTo(dt1);
            }			 
        });		
        return lstSorted;
	}
	
}
