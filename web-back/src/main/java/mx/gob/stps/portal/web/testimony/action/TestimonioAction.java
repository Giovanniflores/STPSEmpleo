package mx.gob.stps.portal.web.testimony.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_MI_ESP_EMP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_MI_ESP_CAND;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_USUARIO;
import mx.gob.stps.portal.core.testimonio.vo.TestimonioVO;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.testimony.delegate.TestimonioBusDelegate;
import mx.gob.stps.portal.web.testimony.delegate.TestimonioBusDelegateImpl;
import mx.gob.stps.portal.web.testimony.form.TestimonioForm;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * 
 * @author concepcion.aguilar
 * clase que recupera los datos de pantalla y los prepara para enviarlos a
 * la capa de negocio
 */
public class TestimonioAction extends GenericAction {
	private static Logger logger = Logger.getLogger(TestimonioAction.class);
	
	@Override
	/**
	 * Recupera nombre y empresa para desplegarlos en pantalla
	 */
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String msgError = "";
		String rutaFoward = "";
		TestimonioVO vo = new TestimonioVO();
		if(this.getUsuario(request.getSession()).getIdTipoUsuario() == TIPO_USUARIO.CANDIDATO.getIdTipoUsuario()){
			vo.setIdTipoPropietario(TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
			rutaFoward = FORWARD_TEMPLATE_MI_ESP_CAND;
		
		} else if(this.getUsuario(request.getSession()).getIdTipoUsuario() == TIPO_USUARIO.EMPRESA.getIdTipoUsuario()) {
			vo.setIdTipoPropietario(TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
			rutaFoward = FORWARD_TEMPLATE_MI_ESP_EMP;
		}
		
		vo.setIdPropietario(this.getUsuario(request.getSession()).getIdPropietario());
		TestimonioBusDelegate services = TestimonioBusDelegateImpl.getInstance();

			try {
				vo= (TestimonioVO)services.recuperaDatos(vo);
			} catch (SQLException e) {
				logger.error(e);
				msgError = "No se encontraron datos para este propietario";
				//registraError(request, "app.exp.negocio.err");
			}catch (ServiceLocatorException e) {
				logger.error(e);
				//registraError(request, "app.exp.locator.err");
				msgError = "No se encontraron datos para este propietario";
			}

		TestimonioForm testimonioform = (TestimonioForm)form;
		testimonioform.setNombre(vo.getNombre());
		testimonioform.setEmpresa(vo.getEmpresa());
		request.setAttribute("error", msgError);
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        //return mapping.findForward(FORWARD_JSP);

        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Testimonio");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Testimonio, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(rutaFoward);
	}
	
	public ActionForward initWindow(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String msgError = "";
		String rutaFoward = "";
		TestimonioVO vo = new TestimonioVO();
		if(this.getUsuario(request.getSession()).getIdTipoUsuario() == TIPO_USUARIO.CANDIDATO.getIdTipoUsuario()){
			vo.setIdTipoPropietario(TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
			rutaFoward = FORWARD_TEMPLATE_MI_ESP_CAND;
		
		} else if(this.getUsuario(request.getSession()).getIdTipoUsuario() == TIPO_USUARIO.EMPRESA.getIdTipoUsuario()) {
			vo.setIdTipoPropietario(TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
			rutaFoward = FORWARD_TEMPLATE_MI_ESP_EMP;
		}
		
		vo.setIdPropietario(this.getUsuario(request.getSession()).getIdPropietario());
		TestimonioBusDelegate services = TestimonioBusDelegateImpl.getInstance();

			try {
				vo= (TestimonioVO)services.recuperaDatos(vo);
			} catch (SQLException e) {
				logger.error(e);
				msgError = "No se encontraron datos para este propietario";
				//registraError(request, "app.exp.negocio.err");
			}catch (ServiceLocatorException e) {
				logger.error(e);
				//registraError(request, "app.exp.locator.err");
				msgError = "No se encontraron datos para este propietario";
			}

		TestimonioForm testimonioform = (TestimonioForm)form;
		testimonioform.setNombre(vo.getNombre());
		testimonioform.setEmpresa(vo.getEmpresa());
		request.setAttribute("error", msgError);
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        //return mapping.findForward(FORWARD_JSP);

        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Testimonio");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Testimonio, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_JSP);
	}
	
	/**
	 * Prepara los datos necesarios para guardar un registro en la tabla Testimonio
	 * @param mapping objeto generado por struts
	 * @param form objeto que contiene las propiedades de testimonio generado por struts
	 * @param request informacion que envia el cliente al contenedor
	 * @param response informacion que regresa el contenedor al cliente
	 * @return
	 */
	public ActionForward registrar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		TestimonioForm testimonioform = (TestimonioForm)form;
		logger.info("Entra al metodo testimonio : "+ testimonioform.getTestimonio());
		
		String descripcion  = testimonioform.getTestimonio();
		String resultadoURL = "";
		TestimonioVO vo = new TestimonioVO();
		if(this.getUsuario(request.getSession()).getIdTipoUsuario() == TIPO_USUARIO.CANDIDATO.getIdTipoUsuario()){
			vo.setIdTipoPropietario(TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
			resultadoURL = "CANDIDATO";
		} else if(this.getUsuario(request.getSession()).getIdTipoUsuario() == TIPO_USUARIO.EMPRESA.getIdTipoUsuario()) {
			vo.setIdTipoPropietario(TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
			resultadoURL = "EMPRESA";
		}

		vo.setIdPropietario(this.getUsuario(request.getSession()).getIdPropietario());

		try {
			TestimonioBusDelegate services = TestimonioBusDelegateImpl.getInstance();
			TestimonioVO testimonioVO = new TestimonioVO();
			testimonioVO.setDescripcion(descripcion);
			//testimonioVO.setEstatus(ESTATUS.ACTIVO.getIdOpcion()); // Estos datos son establecidos en la capa de negocio
			//testimonioVO.setFechaAlta(Calendar.getInstance().getTime());
			testimonioVO.setIdPropietario(vo.getIdPropietario());
			testimonioVO.setIdTipoPropietario(vo.getIdTipoPropietario());
			
			services.registraTestimonio(testimonioVO);
			
			redirectJsonResponse(response, resultadoURL);
		} catch (BusinessException e) {
			logger.error(e); // TODO Notificar error al usuario
		} catch (ServiceLocatorException e) {
			logger.error(e); // TODO Notificar error al usuario 
		} catch (IOException e) {
			logger.error(e); // TODO Notificar error al usuario 
		}
		
		
		return null;
		
//		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
//        return mapping.findForward(FORWARD_JSP);
	}
	

}
