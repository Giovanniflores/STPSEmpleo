package mx.gob.stps.portal.web.oferta.search.action;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_CANDIDATO_HABILIDAD;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_DOMINIO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_EXPERIENCIA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_GRADO_ESTUDIOS;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_IDIOMAS;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_ENTIDADES;
import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_MI_ESP_CAND;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.candidate.vo.ExpectativaLaboralVO;
import mx.gob.stps.portal.core.candidate.vo.GradoAcademicoVO;
import mx.gob.stps.portal.core.candidate.vo.IdiomaVO;
import mx.gob.stps.portal.core.candidate.vo.PerfilVO;
import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.utils.Constantes.IDIOMAS;
import mx.gob.stps.portal.core.infra.utils.Constantes.MULTIREGISTRO;
import mx.gob.stps.portal.core.infra.utils.Constantes.DISPONIBILIDAD_RADICAR;
import mx.gob.stps.portal.core.infra.utils.Constantes.DISPONIBILIDAD_VIAJAR;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorParametrosVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaEmpleoJB;
import mx.gob.stps.portal.core.search.service.IndexerServiceLocator;
import mx.gob.stps.portal.core.search.vo.MatchModVO;
import mx.gob.stps.portal.core.search.vo.ParametrosVO;
import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;
import mx.gob.stps.portal.web.infra.action.PagerAction;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.oferta.search.form.OfertasPorParametrosForm;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegateImpl;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Jorge Montoya Marin
 * @since 22/12/2015
 **/
public class OfertasPorParametrosAction extends PagerAction {

	private static Logger logger = Logger.getLogger(OfertasPorPerfilAction.class);
	private long[] filtro_idioma = {IDIOMAS.NO_REQUISITO.getIdOpcion()};
	private int sumarParaLibrarIndiceCero = 1;

	/**
	 * Obtiene las ofertas activas por perfil
	 * 
	 * @author Jorge Montoya Marin
	 * @since 22/12/2015
	 **/
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		OfertasPorParametrosForm ofertasParametrosForm = (OfertasPorParametrosForm) form;
		ofertasParametrosForm.reset();
		UsuarioWebVO usuario = getUsuario(session);
		if (usuario!=null)
		{
			ofertasParametrosForm.setIdCandidato(usuario.getIdPropietario());
		}

		try {
			PerfilVO perfil = new PerfilVO();
			CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();

			//Idiomas del Candidato.
			List<IdiomaVO> listIdiomas = services.buscarIdiomas(ofertasParametrosForm.getIdCandidato());						
			inicializarDatosDeIdiomasEnForm(listIdiomas, ofertasParametrosForm);

			//Salario pretendido del Candidato.
			List<ExpectativaLaboralVO> expecLaboralesVO = services.buscarExpecLaboral(ofertasParametrosForm.getIdCandidato());
			if (expecLaboralesVO != null && !expecLaboralesVO.isEmpty()) {

				ofertasParametrosForm.setSalarioPretendido(expecLaboralesVO.get(0).getSalarioPretendido()); 
			}
			else{
				ofertasParametrosForm.setSalarioPretendido(0.00);
			}
			
			//Disponibilidad para Radicar fuera y viajar.
			perfil = services.initPerfilCandidato(ofertasParametrosForm.getIdCandidato());
			if(perfil != null)
			{
			ofertasParametrosForm.setDisponibilidadViajar(perfil.getDisponibilidadViajar());
			ofertasParametrosForm.setDisponibilidadRadicar(perfil.getDisponibilidadRadicar());
			ofertasParametrosForm.setIdExperiencia((int) perfil.getIdExperienciaTotal());
			}
            //Habilidades del candidato.
			List<Long> idHabilidades = services.consultaHabilidades(ofertasParametrosForm.getIdCandidato());
			if (idHabilidades!=null){
				long[] idHabilidad = new long[idHabilidades.size()];
				
				int index = 0;
				for (Long id : idHabilidades){
					idHabilidad[index] = id;
					index++;
				}

				ofertasParametrosForm.setIdHabilidad(idHabilidad);
			}
			
			//Estudios del Candidato.
			List<GradoAcademicoVO> gradosVO = services.initGrados(ofertasParametrosForm.getIdCandidato(), MULTIREGISTRO.PRINCIPAL.getIdOpcion());					
			if (gradosVO != null && !gradosVO.isEmpty()) {
				ofertasParametrosForm.setIdNivelEstudio((int) gradosVO.get(0).getIdNivelEstudio()); 
				ofertasParametrosForm.setIdCarreraEspecialidad((int) gradosVO.get(0).getIdCarreraEspecialidad());
			}
			
			//Entidad Federativa del Candidato.
			
			perfil = services.initPerfil(ofertasParametrosForm.getIdCandidato());
			if(perfil != null)
			{
				ofertasParametrosForm.setEdad(perfil.getEdad());
				ofertasParametrosForm.setIdEntidad((int) perfil.getIdEntidadNacimiento());
			}
			
			cargaCatalogos(session);
			
		} catch (Exception ex){logger.error(ex); ex.printStackTrace();
		}

		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());

        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Ofertas por par&aacute;metros");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Espacio Candidato, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);		
	}


	public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException
	{
		HttpSession session = request.getSession();
		OfertasPorParametrosForm ofertasPorParametrosForm = (OfertasPorParametrosForm) form;
		UsuarioWebVO usuario = super.getUsuario(session);
		session.removeAttribute("showMsg");		

		List<OfertaPorParametrosVO> ofertasPorParametros = new ArrayList<OfertaPorParametrosVO>();

		try {
			if (usuario != null){		
				
				List<String> lststrIdsHabilidades = new ArrayList<String>();				
				for (Long id : ofertasPorParametrosForm.getIdHabilidades()){
					lststrIdsHabilidades.add(id.toString());
				}
				
				ParametrosVO candidato = new ParametrosVO();
		        candidato.setId(ofertasPorParametrosForm.getIdCandidato());
		        candidato.setIdGradoEstudios(ofertasPorParametrosForm.getIdNivelEstudio());
		        candidato.setIdCarrera(ofertasPorParametrosForm.getIdCarreraEspecialidad());
		        candidato.setIdExperiencia(ofertasPorParametrosForm.getIdExperiencia());
		        candidato.setIdIdioma(ofertasPorParametrosForm.getIdIdioma());
		        candidato.setIdDominioIdioma(ofertasPorParametrosForm.getIdDominioIdioma());
		        candidato.setSalario(ofertasPorParametrosForm.getSalarioPretendido());
		        candidato.setDispoViajar(ofertasPorParametrosForm.getDisponibilidadViajar());
		        candidato.setDispoRadicar(ofertasPorParametrosForm.getDisponibilidadRadicar());
		        
		        candidato.setHabilidades(lststrIdsHabilidades);
		        
				List<MatchModVO> ofertas = IndexerServiceLocator.getIndexerParamServiceRemote().busquedaParametrizableCandidato(candidato);
				
				ofertasPorParametros = getOfertasPerfilFiltradas(ofertas);
			}			

			ofertasPorParametrosForm.setOfertas(ofertasPorParametros);			
			PAGE_NUM_ROW = 5;
			session.setAttribute(FULL_LIST + "_OFERTAS" , ofertasPorParametros);
			session.setAttribute("NUM_REGISTROS" , PAGE_NUM_ROW);


		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}			
		
		session.removeAttribute("detalle");								
		ocultaBarraArticulos(request);
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
			
		ofertasPorParametrosForm.setTablaPager("_OFERTAS");
		return pageTable(mapping, ofertasPorParametrosForm, request, response);
	}
		

	public List<OfertaPorParametrosVO> getOfertasPerfilFiltradas(List<MatchModVO> ofertas) {
		
		OfferBusDelegateImpl service = (OfferBusDelegateImpl) OfferBusDelegateImpl.getInstance();
		List<OfertaPorParametrosVO> ofertasPerfilFiltradas = new ArrayList<OfertaPorParametrosVO>();
		
		if (ofertas != null && !ofertas.isEmpty()) {
			for(MatchModVO oferta : ofertas) {
				
				OfertaEmpleoJB ofertaEmpleoJB = new OfertaEmpleoJB();
				
				OfertaPorParametrosVO OfertaPorParametrosVO = new OfertaPorParametrosVO();
				try {
					
					ofertaEmpleoJB = service.buscarOfertaEmpleo(oferta.getId());
				
				} catch (BusinessException e) {
					e.printStackTrace();
				} catch (ServiceLocatorException e) {
					e.printStackTrace();
				}
				
				if (ofertaEmpleoJB.getEstatusOffer() == Catalogos.ESTATUS.ACTIVO.getIdOpcion()) {
					
					OfertaPorParametrosVO.setIdEmpresa(Long.valueOf(ofertaEmpleoJB.getIdEmpresa()));
					OfertaPorParametrosVO.setIdOfertaEmpleo(Long.valueOf(ofertaEmpleoJB.getIdOfertaEmpleo()));
					OfertaPorParametrosVO.setTituloOferta(ofertaEmpleoJB.getTituloOferta());
					OfertaPorParametrosVO.setUbicacion(ofertaEmpleoJB.getUbicacion());
					OfertaPorParametrosVO.setEmpresa(ofertaEmpleoJB.getEmpresaNombre());
					OfertaPorParametrosVO.setSalario(Double.valueOf(ofertaEmpleoJB.getSalario()));
					OfertaPorParametrosVO.setFuente(ofertaEmpleoJB.getFuenteId());
					OfertaPorParametrosVO.setFunciones(ofertaEmpleoJB.getFunciones());
					OfertaPorParametrosVO.setNumeroPlazas(Integer.valueOf(ofertaEmpleoJB.getNumeroPlazas()));
					OfertaPorParametrosVO.setMedioContacto(ofertaEmpleoJB.getMedioContacto());
					OfertaPorParametrosVO.setGradoEstudio(ofertaEmpleoJB.getGradoEstudios());
					OfertaPorParametrosVO.setCarrera(ofertaEmpleoJB.getEspecialidades());
					OfertaPorParametrosVO.setOcupacion(ofertaEmpleoJB.getOcupacion());		
					OfertaPorParametrosVO.setCompatibilidad(oferta);
					OfertaPorParametrosVO.setIdiomas(ofertaEmpleoJB.getIdiomas());
					OfertaPorParametrosVO.setIdiomasCert(ofertaEmpleoJB.getIdiomasCert());
					OfertaPorParametrosVO.setHabilidades(getHabilidades(ofertaEmpleoJB.getHabilidades()));	
					OfertaPorParametrosVO.setHabilidadGeneral(ofertaEmpleoJB.getHabilidadGeneral());
					OfertaPorParametrosVO.setEstatus(ofertaEmpleoJB.getEstatusOffer());			
					OfertaPorParametrosVO.setContactoCorreo(ofertaEmpleoJB.getContactoCorreo());
					OfertaPorParametrosVO.setContactoTel(ofertaEmpleoJB.getContactoTel());	
					OfertaPorParametrosVO.setContactoDomicilio(ofertaEmpleoJB.getContactoDomicilio());					
					OfertaPorParametrosVO.setCorreoElectronicoContacto(ofertaEmpleoJB.getCorreoElectronicoContacto());
					OfertaPorParametrosVO.setTelefonoOferta(ofertaEmpleoJB.getTelefonoOferta());	
					OfertaPorParametrosVO.setDomicilio(getDomicilio(ofertaEmpleoJB.getDomicilio()));			
					ofertasPerfilFiltradas.add(OfertaPorParametrosVO);	
				}
			}				
		}
		
		return ofertasPerfilFiltradas;
	}
	

	private String getHabilidades(List<CatalogoOpcionVO> habilidades_I) {
		
		String habilidades ="";
		
		if(habilidades_I.size() > 0){
			for(CatalogoOpcionVO vo: habilidades_I){
				if(vo != null){
					if(habilidades.equals(""))
						habilidades = vo.getOpcion();
					else 
						habilidades = habilidades + ", " + vo.getOpcion();
				}
			}
		}
		
		return habilidades;
	}	
	
	
	private String getDomicilio(DomicilioVO domicilioVO) {
        StringBuilder domicilio = new StringBuilder();
		
		if (domicilioVO != null) {
	
			if (domicilioVO.getCalle()!=null && !"".equals(domicilioVO.getCalle()))
				domicilio.append(domicilioVO.getCalle());		
	
			if(domicilioVO.getNumeroExterior() != null && !"".equals(domicilioVO.getNumeroExterior())){
				if(domicilio.length() > 0)
					domicilio.append(",").append(" número ext. ").append(domicilioVO.getNumeroExterior());		
				}

			if(domicilioVO.getNumeroInterior() != null  && !"".equals(domicilioVO.getNumeroInterior())){
				if(domicilio.length() > 0)
					domicilio.append(",").append(" número int. ").append(domicilioVO.getNumeroInterior());		
				}
		
			if(domicilioVO.getEntreCalle() != null && !"".equals(domicilioVO.getEntreCalle())){		
				if(domicilio.length() > 0)
					domicilio.append(",").append(" entre ").append(domicilioVO.getEntreCalle());	
				}
			
			if(domicilioVO.getyCalle() != null && !"".equals(domicilioVO.getyCalle())){
				if(domicilio.length() > 0)
					domicilio.append(" y ").append(domicilioVO.getyCalle());
				}
			
			if (domicilioVO.getEntidad() != null && !"".equals(domicilioVO.getEntidad())){
				if(domicilio.length() > 0)
					domicilio.append(", ").append(domicilioVO.getEntidad());
			}
			
			if (domicilioVO.getMunicipio() != null && !"".equals(domicilioVO.getMunicipio())){
				if(domicilio.length() > 0)
					domicilio.append(", ").append(domicilioVO.getMunicipio());
			}
	
			if (domicilioVO.getColonia() != null && !"".equals(domicilioVO.getColonia())){
				if(domicilio.length() > 0)
					domicilio.append(", ").append(domicilioVO.getColonia());
			}
	
			if (domicilioVO.getCodigoPostal() != null && !"".equals(domicilioVO.getCodigoPostal())){							
				if(domicilio.length() > 0)
					domicilio.append(", ").append("C.P. ").append(domicilioVO.getCodigoPostal());							
			}
		}
		return domicilio.toString();
	}

	
	private void inicializarDatosDeIdiomasEnForm(List<IdiomaVO> idiomas, OfertasPorParametrosForm ofertasParametrosForm){
		for(IdiomaVO idiomaVo: idiomas){
			if (idiomaVo.getPrincipal() == MULTIREGISTRO.PRINCIPAL.getIdOpcion()) {
				setIdiomaPrincipal(ofertasParametrosForm, idiomaVo);
			}
		}
	}

	
	private void setIdiomaPrincipal(OfertasPorParametrosForm ofertasParametrosForm, IdiomaVO idiomaVo){
		ofertasParametrosForm.setIdIdioma((int) idiomaVo.getIdIdioma());
		ofertasParametrosForm.setIdDominioIdioma((int) idiomaVo.getIdDominio());		
	}
	
	
	private void cargaCatalogos(HttpSession session){	
		
		long[] filtro_experiencia = {1,8};
		
		try {
			List<CatalogoOpcionVO> opcGrados 		= CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_GRADO_ESTUDIOS, true);
			List<CatalogoOpcionVO> opcDominio 		= CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_DOMINIO, true);			
			List<CatalogoOpcionVO> opciExperiencia 	= CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_EXPERIENCIA, filtro_experiencia, true);						
			List<CatalogoOpcionVO> opcIdioma		= CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_IDIOMAS, filtro_idioma, true);
			List<CatalogoOpcionVO> opcHabilidades	= CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_CANDIDATO_HABILIDAD, true);
			List<CatalogoOpcionVO> opcEntidad       = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_ENTIDADES, true);
 			
			
		
			String[] depCat = new String[opcGrados.size() + sumarParaLibrarIndiceCero];
			
			depCat[0] = "0";
			for (CatalogoOpcionVO opcion : opcGrados ) {
				depCat[(int) opcion.getIdCatalogoOpcion()] = String.valueOf(opcion.getIdCatalagoAsociado());
			}
			
			session.setAttribute("opcGrados", opcGrados);
			session.setAttribute("depGrado", depCat);
			session.setAttribute("opcDominio", opcDominio);
			session.setAttribute("opciExperiencia", opciExperiencia);
			session.setAttribute("opcIdioma", opcIdioma);
			session.setAttribute("opcHabilidades", opcHabilidades);	
			session.setAttribute("opcEntidad", opcEntidad);
			
			session.setAttribute("dispViajarSI", DISPONIBILIDAD_VIAJAR.SI.getIdOpcion());
			session.setAttribute("dispViajarNO", DISPONIBILIDAD_VIAJAR.NO.getIdOpcion());
			session.setAttribute("dispRadicarSI", DISPONIBILIDAD_RADICAR.SI.getIdOpcion());
			session.setAttribute("dispRadicarNO", DISPONIBILIDAD_RADICAR.NO.getIdOpcion());			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}
	

	public ActionForward pageTable(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		OfertasPorParametrosForm ofertasPorParametrosForm = (OfertasPorParametrosForm) form;
		
		return this.page(1, mapping, session, ofertasPorParametrosForm.getTablaPager());
	}
	
	
	public final ActionForward prevPageTable(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		OfertasPorParametrosForm ofertasPorParametrosForm = (OfertasPorParametrosForm) form;

		if (ofertasPorParametrosForm.getTablaPager().equals(""))
			return this.prev(mapping, ofertasPorParametrosForm, request, response);
		else
			return this.prev(mapping, ofertasPorParametrosForm, request, response);
	}
	
	
	public final ActionForward nextPageTable(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		OfertasPorParametrosForm ofertasPorParametrosForm = (OfertasPorParametrosForm) form;

		if (ofertasPorParametrosForm.getTablaPager().equals(""))
			return this.next(mapping, ofertasPorParametrosForm, request, response);
		else
			return this.next(mapping, ofertasPorParametrosForm, request, response);
	}
}
