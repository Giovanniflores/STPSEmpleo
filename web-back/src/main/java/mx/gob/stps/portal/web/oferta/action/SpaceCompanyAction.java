package mx.gob.stps.portal.web.oferta.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_FORM;
import static mx.gob.stps.portal.web.infra.utils.Constantes.LISTA_OFERTAS_CREADAS;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.USUARIO_APP;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.oferta.registro.vo.RegistroUbicacionVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.web.infra.action.PagerAction;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegate;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.oferta.delegate.OfertaBusDelegate;
import mx.gob.stps.portal.web.oferta.delegate.OfertaBusDelegateImpl;
import mx.gob.stps.portal.web.oferta.form.CarrerasSimilaresForm;
import mx.gob.stps.portal.web.oferta.form.SpaceCompanyForm;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegate;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegateImpl;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Clase Action de las ofertas creadas por la empresa.
 * @author _.- Arriaga Cervnates F. Rubén -._
 *
 */
public class SpaceCompanyAction extends PagerAction{
	
	private static Logger logger = Logger.getLogger(SpaceCompanyAction.class);
	
	/**
	 * Método init para cargar los datos en las ofertas creadas por la empresa
	 */
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		SpaceCompanyForm spaceForm = (SpaceCompanyForm) form;
		List<OfertaEmpleoVO> listOfertasCreadas = loadList(request.getSession());
		spaceForm.setListOfertasCreadas(listOfertasCreadas);
		session.setAttribute(LISTA_OFERTAS_CREADAS, listOfertasCreadas);
    	request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Espacio de la Compa&ntilde;ia");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Reg&iacute;strate en nuestra Bolsa de trabajo y postula la oferta de empleo que m&aacute;s se apegue seg&uacute;n tu curriculum vitae sin costo f&aacute;cil y sin intermediarios.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        return mapping.findForward(FORWARD_TEMPLATE_FORM);
	}
	
	/**
	 * Método que obtiene las ofertas creadas por la empresa
	 * @return List<OfertaEmpleoCreadaVO>
	 */
	public List<OfertaEmpleoVO> loadList(HttpSession session){
		List<OfertaEmpleoVO> listOfertasCreadas = null;
		try {
			//================== DELEGATES ==================
			OfertaBusDelegate ofertaDelegate = OfertaBusDelegateImpl.getInstance();
			OfferBusDelegate offerDelegate = OfferBusDelegateImpl.getInstance();
			CatalogoOpcionDelegate catalogoDelegate = CatalogoOpcionDelegateImpl.getInstance();
			//===============================================
			listOfertasCreadas = new ArrayList<OfertaEmpleoVO> ();
			UsuarioWebVO usuarioWebVo = (UsuarioWebVO) session.getAttribute(USUARIO_APP);
			List<OfertaEmpleoVO> listOfertas = (List<OfertaEmpleoVO>) ofertaDelegate.obtenerOfertasCreadas(usuarioWebVo.getIdPropietario());

			if(!listOfertas.isEmpty()){
				for(OfertaEmpleoVO vo : listOfertas){
					OfertaEmpleoVO oferta = new OfertaEmpleoVO ();
					oferta.setIdOfertaEmpleo((int) vo.getIdOfertaEmpleo());
					oferta.setTituloOferta(vo.getTituloOferta());
					oferta.setEstatus(vo.getEstatus());
					oferta.setOcupacionDescripcion(catalogoDelegate.getOpcionById(Constantes.CATALOGO_OPCION_OCUPACION, (long) vo.getIdOcupacion() ));
					oferta.setNivelEstudiosDescripcion(
							catalogoDelegate.getOpcionById(Constantes.CATALOGO_OPCION_GRADO_ESTUDIOS,(long) vo.getIdNivelEstudio()));
					for(String val : offerDelegate.ofertaEspecialidadesList( (long) vo.getIdOfertaEmpleo(),(long) vo.getIdNivelEstudio() )){
						CarrerasSimilaresForm carrera = new CarrerasSimilaresForm();
						carrera.setCarrera(val);
						oferta.setCarreraDescripcion(carrera.getCarrera());
						break;
					}
					for(RegistroUbicacionVO val : offerDelegate.getUbicaciones(vo.getIdOfertaEmpleo())){
						oferta.setMunicipioDescripcion(val.getEntidad());
						oferta.setEntidadDescripcion(val.getMunicipio());
						break;
					}
					listOfertasCreadas.add(oferta);
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); logger.error(e);
		}
		return listOfertasCreadas;
	}
	
}