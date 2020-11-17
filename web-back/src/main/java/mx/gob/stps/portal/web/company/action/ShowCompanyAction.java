/**
 * 
 */
package mx.gob.stps.portal.web.company.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_MI_ESP_EMP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegate;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegateImpl;
import mx.gob.stps.portal.web.company.form.ShowCompanyForm;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegate;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.oferta.delegate.OfertaBusDelegate;
import mx.gob.stps.portal.web.oferta.delegate.OfertaBusDelegateImpl;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * @author haydee.vertti
 *
 */

public class ShowCompanyAction extends GenericAction {

	private static Logger logger = Logger.getLogger(ShowCompanyAction.class);
	
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		try{
			ShowCompanyForm showForm = (ShowCompanyForm) form;
			
			UsuarioWebVO webVo = getUsuario(request.getSession());
			long idPropietario = 0;
			long idTipoUsuario = webVo.getIdTipoUsuario();		
			long idPerfil = webVo.getIdPerfil();
			double salarioMinimo = 0.00;
			double salarioMaximo = 0.00;
			List<String> lstEmpresaOfrece = new ArrayList<String>();
			
			if(idTipoUsuario==(long)Constantes.TIPO_USUARIO.ADMINISTRADOR.getIdTipoUsuario() || 
					idTipoUsuario==(long)Constantes.TIPO_USUARIO.EMPRESA.getIdTipoUsuario()){
				idPropietario = webVo.getIdPropietario();
				
			} else if(idTipoUsuario==(long)Constantes.TIPO_USUARIO.CANDIDATO.getIdTipoUsuario()){
				String strPropietario = String.valueOf(request.getSession().getAttribute("ID_EMPRESA"));
				if(null!=strPropietario){
					idPropietario = Long.parseLong(strPropietario);
				}
			} else {
				String strPropietario = String.valueOf(request.getSession().getAttribute("ID_EMPRESA"));
				if(null!=strPropietario){
					idPropietario = Long.parseLong(strPropietario);
				}				
			}
			
			if(idPropietario>0){
				showForm.setIdTipoUsuario(idTipoUsuario);			
				showForm.setIdPerfil(idPerfil);
				showForm.setIdEmpresa(idPropietario);
				
				RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance();
				EmpresaVO empresaVo = service.findEmpresaById(idPropietario);
				
				showForm.setNombreEmpresa(empresaVo.getNombreEmpresa());
				showForm.setDescripcion(empresaVo.getDescripcion());
				showForm.setPaginaWeb(empresaVo.getPaginaWeb());
								
				OfertaBusDelegate offerService = OfertaBusDelegateImpl.getInstance();
				List<OfertaPorCanalVO> lstOfertas =  offerService.consultaOfertasXCanal(idPropietario);
				
				if(null==lstOfertas){
					lstOfertas = new ArrayList<OfertaPorCanalVO>();
					showForm.setLstOfertas(lstOfertas);	
					showForm.setSalarioMinimo(salarioMinimo);
					showForm.setSalarioMaximo(salarioMaximo);
					showForm.setLstEmpresaOfrece(lstEmpresaOfrece);
					
				} else {	
					//CatalogoOpcionDelegate serviceCat = CatalogoOpcionDelegateImpl.getInstance();
										
					//Constantes.CATALOGO_OPCION_MUNICIPIO
					//Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA
					
					
					salarioMinimo = getMinimumWage(lstOfertas);
					//System.out.println("---salarioMinimo:" + salarioMinimo);					
					showForm.setSalarioMinimo(salarioMinimo);
					//System.out.println("---showForm salarioMinimo:" + showForm.getSalarioMinimo());
										
					salarioMaximo = getMaximumWage(lstOfertas);
					//System.out.println("---salarioMaximo:" + salarioMaximo);					
					showForm.setSalarioMaximo(salarioMaximo);
					//System.out.println("---showForm salarioMaximo:" + showForm.getSalarioMaximo());

					List<OfertaPorCanalVO> lstOfertasRecientes  = sortOffersByDate(lstOfertas);
					showForm.setLstOfertas(lstOfertasRecientes);	
					
					/*Iterator itDebug = lstOfertasRecientes.iterator();
					while(itDebug.hasNext()){
						OfertaPorCanalVO oferta = (OfertaPorCanalVO) itDebug.next();
						
						System.out.println("---oferta:" + oferta.getTituloOferta() + " - " + oferta.getFechaFinString() + " - " +
								oferta.getUbicacion() );	
					}*/
					
					lstEmpresaOfrece = getCompanyOffering(lstOfertasRecientes);	
					showForm.setLstEmpresaOfrece(lstEmpresaOfrece);
				}
				
			} else {
				//TODO mandar mensaje de error
			}
			
			
	    } catch (ServiceLocatorException e) {
	        logger.error(e);
	        registraError(request, "app.exp.locator.err");
	    } catch (Exception e) {
	        logger.error(e);
	        registraError(request, "app.exp.negocio.err");
	    }    
	    
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Detalle empresa");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Detalle empresa, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
	    return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);

	}
	
	
	
	private List<String> getCompanyOffering(List<OfertaPorCanalVO> lstOfertas){
		
		ArrayList<String> lstOffering = new ArrayList<String>();
		ArrayList<String> lstOfferingTemp = new ArrayList<String>();
		
		if(lstOfertas.size()>0){
			
			try {
				OfertaBusDelegate offerService = OfertaBusDelegateImpl.getInstance();				
				Iterator itLstOfertas = lstOfertas.iterator();
								
				while(itLstOfertas.hasNext()){
					OfertaPorCanalVO oferta = (OfertaPorCanalVO) itLstOfertas.next();
					ArrayList<String> lstPrestaciones = offerService.obtenerPrestacionesOferta(oferta.getIdOfertaEmpleo());	
					lstOfferingTemp.addAll(lstPrestaciones);
				}
				
				//eliminar duplicados
				HashSet hashSet = new HashSet(lstOfferingTemp);
				ArrayList<String> lstTemp = new ArrayList(hashSet);
				if(lstTemp.size()>0){
					 Iterator itTemp = lstTemp.iterator();
					 
					 CatalogoOpcionDelegate serviceCat = CatalogoOpcionDelegateImpl.getInstance();				
					 
					 while(itTemp.hasNext()){
						 String strTemp  = (String) itTemp.next(); 
						 long idCatalogoOpcion = Long.parseLong(strTemp);
						 String strDesc = serviceCat.getOpcionById(Constantes.CATALOGO_OPCION_PRESTACIONES, idCatalogoOpcion); 	
						 lstOffering.add(strDesc);						 
					 }					 
				}
				 
				
			} catch (BusinessException e) {
				e.printStackTrace();
			} catch (ServiceLocatorException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
				
		return lstOffering;
	}
	
	
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
	
	
	private double getMinimumWage(List<OfertaPorCanalVO> lstOfertas){
		double dblMinWage = 0.00;
		List<OfertaPorCanalVO> lstSorted = lstOfertas;
		if(lstSorted.size()>0){
	        Collections.sort(lstSorted, new Comparator(){
	          	 
	            public int compare(Object o1, Object o2) {
	            	OfertaPorCanalVO p1 = (OfertaPorCanalVO) o1;
	            	OfertaPorCanalVO p2 = (OfertaPorCanalVO) o2;
	            	Double dblWage1 = Double.parseDouble(String.valueOf(p1.getSalario()));
	            	Double dblWage2 = Double.parseDouble(String.valueOf(p2.getSalario()));	            	
	            	return dblWage1.compareTo(dblWage2);
	            }			 
	        });
	        OfertaPorCanalVO ofertaMin = (OfertaPorCanalVO) lstSorted.iterator().next();
	        dblMinWage = ofertaMin.getSalario();
		}
        return dblMinWage;
	}
	
	private double getMaximumWage(List<OfertaPorCanalVO> lstOfertas){
		double dblMaxWage = 0.00;
		List<OfertaPorCanalVO> lstSorted = lstOfertas;
		if(lstSorted.size()>0){
	        Collections.sort(lstSorted, new Comparator(){
	          	 
	            public int compare(Object o1, Object o2) {
	            	OfertaPorCanalVO p1 = (OfertaPorCanalVO) o1;
	            	OfertaPorCanalVO p2 = (OfertaPorCanalVO) o2;
	            	Double dblWage1 = Double.parseDouble(String.valueOf(p1.getSalario()));
	            	Double dblWage2 = Double.parseDouble(String.valueOf(p2.getSalario()));	            	
	            	return dblWage2.compareTo(dblWage1);
	            }			 
	        });
	        OfertaPorCanalVO ofertaMax = (OfertaPorCanalVO) lstSorted.iterator().next();
	        dblMaxWage = ofertaMax.getSalario();
		}
        return dblMaxWage;
	}	

	
}
