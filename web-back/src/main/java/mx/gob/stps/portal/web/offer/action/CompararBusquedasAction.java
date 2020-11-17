package mx.gob.stps.portal.web.offer.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_ESP_ADMIN;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_FORM;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.oferta.vo.BusquedaOfertasVO;
import mx.gob.stps.portal.core.oferta.vo.ResultadoBusquedaOfertasVO;
import mx.gob.stps.portal.core.search.ResultInfoBO;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.jobvacancies.delegate.BolsasTrabajoBusDelegate;
import mx.gob.stps.portal.web.jobvacancies.delegate.BolsasTrabajoBusDelegateImpl;
import mx.gob.stps.portal.web.oferta.delegate.OfertaBusDelegate;
import mx.gob.stps.portal.web.oferta.delegate.OfertaBusDelegateImpl;
import mx.gob.stps.portal.web.offer.form.CompararBusquedasForm;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class CompararBusquedasAction extends GenericAction{	
	
	public final Integer ENTIDAD_DISTRITO_FEDERAL = 9;
	
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CompararBusquedasForm cbforma = (CompararBusquedasForm)form;
		HttpSession session = request.getSession();
		
		buscar(mapping,cbforma,request,response);		
		
		session.setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());

        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Busqueda");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Busqueda, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString())); 
		return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
	}
	
	public ActionForward buscar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		CompararBusquedasForm cbforma = (CompararBusquedasForm)form;
		BusquedaOfertasVO criteriosBusquedaEspecifica = armarCriteriosBusquedaEspecifica();
		System.out.println("----init busquedaOfertasVO OK");		
		cbforma.setCriteriosBusquedaEspecifica(criteriosBusquedaEspecifica);
		System.out.println("----init setCriteriosBusquedaEspecifica OK");
		List<Long> indicesBusquedaEspecifica = ejecutarBusquedaEspecifica(criteriosBusquedaEspecifica);
		cbforma.setIndicesOfertasBusquedaEspecifica(indicesBusquedaEspecifica);
		System.out.println("----init setIndicesOfertasBusquedaEspecifica OK");				
		List<Long> indicesBusquedaOcupate = ejecutarBusquedaOcupate();
		cbforma.setIndicesOfertasBusquedaOcupate(indicesBusquedaOcupate);
		//System.out.println("----init ejecutarBusquedaOcupate OK");	
		
				
		List<Long> indicesExclusivosBusquedaEspecifica = encontrarExclusivaBusquedaEspecifica(indicesBusquedaEspecifica, indicesBusquedaOcupate);
		cbforma.setIndicesSoloBusquedaEspecifica(indicesExclusivosBusquedaEspecifica);
		//System.out.println("----init setIndicesSoloBusquedaEspecifica OK");
		
		List<Long> indicesExclusivosBusquedaOcupate = encontrarExclusivaBusquedaOcupate(indicesBusquedaEspecifica, indicesBusquedaOcupate);
		cbforma.setIndicesSoloBusquedaOcupate(indicesExclusivosBusquedaOcupate);
		//System.out.println("----init setIndicesSoloBusquedaOcupate OK");		
		
		request.getSession().setAttribute(BODY_JSP, mapping.getInput());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Buscar y Comparar Ofertas");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Reg&iacute;strate en nuestra Bolsa de trabajo y postula la oferta de empleo que m&aacute;s se apegue seg&uacute;n tu curriculum vitae sin costo f&aacute;cil y sin intermediarios.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        return mapping.findForward(FORWARD_TEMPLATE_FORM);		
	}	
	
	public List<Long> encontrarExclusivaBusquedaEspecifica(List<Long> indicesBusquedaEspecifica, List<Long> indicesBusquedaOcupate){
		//System.out.println("----encontrarExclusivaBusquedaEspecifica OK");		
		List<Long> indicesExclusivosBusquedaEspecifica = new ArrayList<Long>(indicesBusquedaEspecifica.size());
		for(Long item : indicesBusquedaEspecifica){
			indicesExclusivosBusquedaEspecifica.add(new Long(item));
		}
		indicesExclusivosBusquedaEspecifica.removeAll(indicesBusquedaOcupate);
		//System.out.println("----encontrarExclusivaBusquedaEspecifica indicesExclusivosBusquedaEspecifica.removeAll OK");
		return indicesExclusivosBusquedaEspecifica;
		
	}

	public List<Long> encontrarExclusivaBusquedaOcupate(List<Long> indicesBusquedaEspecifica, List<Long> indicesBusquedaOcupate){
		//System.out.println("----encontrarExclusivaBusquedaOcupate OK");
		List<Long> indicesExclusivosBusquedaOcupate = new ArrayList<Long>(indicesBusquedaOcupate.size());	
		for(Long item : indicesBusquedaOcupate){
			indicesExclusivosBusquedaOcupate.add(new Long(item));
		}		
		indicesExclusivosBusquedaOcupate.removeAll(indicesBusquedaEspecifica);
		//System.out.println("----encontrarExclusivaBusquedaOcupate removeAll OK");
		return indicesExclusivosBusquedaOcupate;
				
	}	
	
	public List<Long> ejecutarBusquedaOcupate(){
		List<Long> indicesOfertas = new ArrayList<Long>();
		
		try {
			//System.out.println("----entrando ejecutarBusquedaOcupate OK");
			List<ResultInfoBO> indices = new ArrayList<ResultInfoBO>();
			BolsasTrabajoBusDelegate services = BolsasTrabajoBusDelegateImpl.getInstance();						
			indices = services.buscarOfertasEmpleo("", ENTIDAD_DISTRITO_FEDERAL);
			//System.out.println("----ejecutarBusquedaOcupate buscarOfertasEmpleo OK");
			if(null !=indices && !indices.isEmpty()){
				indicesOfertas = obtenerIndicesOfertasOcupate(indices);
				//System.out.println("----ejecutarBusquedaOcupate obtenerIndicesOfertasOcupate OK");
			}
		} catch (TechnicalException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
		
		return indicesOfertas;
	}
	
	public List<Long> ejecutarBusquedaEspecifica(BusquedaOfertasVO criteriosBusquedaEspecifica){
		List<Long> indicesOfertas = new ArrayList<Long>();
		try {
			//System.out.println("----ejecutarBusquedaEspecifica");
			OfertaBusDelegate service = OfertaBusDelegateImpl.getInstance();						
			List<ResultadoBusquedaOfertasVO> jobOffers = service.buscarOfertasEspecificas(criteriosBusquedaEspecifica);	
			//System.out.println("----ejecutarBusquedaEspecifica buscarOfertasEspecificas OK");
			indicesOfertas = obtenerIndicesOfertasBusquedaEspecifica(jobOffers);
			System.out.println("----ejecutarBusquedaEspecifica obtenerIndicesOfertasBusquedaEspecifica OK");
			//System.out.println("----indicesOfertas:" + indicesOfertas.size());	
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (BusinessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return indicesOfertas;
	}
	
	public List<Long> obtenerIndicesOfertasOcupate(List<ResultInfoBO> jobOffers){
		List<Long> indicesOfertas = new ArrayList<Long>();
		Iterator<ResultInfoBO> itOffers = jobOffers.iterator();
		while(itOffers.hasNext()){
			ResultInfoBO offer = itOffers.next();
			indicesOfertas.add(offer.getId());
		}
		return indicesOfertas;
	}		
	
	public List<Long> obtenerIndicesOfertasBusquedaEspecifica(List<ResultadoBusquedaOfertasVO> jobOffers){
		List<Long> indicesOfertas = new ArrayList<Long>();
		Iterator<ResultadoBusquedaOfertasVO> itOffers = jobOffers.iterator();
		while(itOffers.hasNext()){
			ResultadoBusquedaOfertasVO offer = itOffers.next();
			indicesOfertas.add(offer.getJobId());
		}
		return indicesOfertas;
	}	
	
	public BusquedaOfertasVO armarCriteriosBusquedaEspecifica() {
		BusquedaOfertasVO vo = new BusquedaOfertasVO();
	    	ArrayList<Integer> jobIds = new ArrayList<Integer>();
	    	ArrayList<Integer> municipalityIds = new ArrayList<Integer>();
	    	ArrayList<Integer> careerIds = new ArrayList<Integer>();
	    	ArrayList<Integer> activityIds = new ArrayList<Integer>();
	    	Double minSalary = null;
	    	Double maxSalary = null;
	    	Integer age = null;
	    	vo.setJobIds(jobIds);
	    	vo.setLocationEntityId(ENTIDAD_DISTRITO_FEDERAL);
	    	vo.setLocationMunicipalityIds(municipalityIds);
	    	vo.setLocationRegionId(0);
	    	vo.setMinSalary(minSalary);
	    	vo.setMaxSalary(maxSalary);
	    	vo.setAge(age);
	    	vo.setEducationGradeId(0);
	    	vo.setEducationCareerIds(careerIds);
	    	vo.setEmploymentTypeId(0);
	    	vo.setContractTypeId(0);
	    	vo.setEnterpriseActivityIds(activityIds);			
    	return vo;
    }		


}
