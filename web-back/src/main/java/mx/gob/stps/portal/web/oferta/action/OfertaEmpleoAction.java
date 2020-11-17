package mx.gob.stps.portal.web.oferta.action;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_CANDIDATO_HABILIDAD;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_CAUSA_OFERTA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_CURSOS;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_DOMINIO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA_NACIDO_EXTRANJERO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_ESTATUS_GRADO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_EXPERIENCIA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_GRADO_ESTUDIOS;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_HORARIO_CONTACTO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_HORARIO_LABORAL;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_IDIOMAS;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_NUMERO_SALARIOS;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_OCUPACION;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_PRESTACIONES;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_PROVINCIAS_CANADA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_TIPO_ACTIVIDAD_ECONOMICA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_TIPO_CONTRATO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_TIPO_DISCAPACIDAD;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_TIPO_EMPLEO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_VIGENCIA_OFERTA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_TIPO_RECURSO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CLAVE_TELEFONO_CELULAR;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CLAVE_TELEFONO_FIJO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.TELEFONO_CELULAR;
import static mx.gob.stps.portal.core.infra.utils.Constantes.TELEFONO_FIJO;
import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_ESP_ADMIN;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_MI_ESP_EMP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_FORM_EMPTY;
import static mx.gob.stps.portal.web.infra.utils.Constantes.PARAM_ID_REGISTRO_POR_VALIDAR;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TAB_MENU;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TAB_MIS_OFERTAS;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.candidate.vo.ConocimientoComputacionVO;
import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.EVENTO;
import mx.gob.stps.portal.core.infra.utils.Constantes.MULTIREGISTRO;
import mx.gob.stps.portal.core.infra.utils.Constantes.SUBPROGRAMA;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_DISCAPACIDAD;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_REGISTRO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_REQUISITO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_TELEFONO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_USUARIO;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaRequisitoVO;
import mx.gob.stps.portal.core.oferta.registro.vo.OfertaCarreraEspecialidadVO;
import mx.gob.stps.portal.core.oferta.registro.vo.OfertaIdiomaVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.core.search.exception.IndexerException;
import mx.gob.stps.portal.persistencia.vo.*;
import mx.gob.stps.portal.utils.Catalogos.ACEPTA_CANDIDATOS_CON_DISCAPACIDAD;
import mx.gob.stps.portal.utils.Catalogos.PUBLICAR_OFERTA_PE_SNETEL;
import mx.gob.stps.portal.utils.ConstantesGenerales;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegate;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegateImpl;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegate;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.Constantes.MESES;
import mx.gob.stps.portal.web.infra.utils.Constantes.PAIS_OFERTA;
import mx.gob.stps.portal.web.infra.utils.MessageLoader;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;
import mx.gob.stps.portal.web.oferta.delegate.OfertaBusDelegate;
import mx.gob.stps.portal.web.oferta.delegate.OfertaBusDelegateImpl;
import mx.gob.stps.portal.web.oferta.form.OfertaEmpleoForm;
import mx.gob.stps.portal.web.oferta.search.delegate.OfertasPorPerfilBusDelegate;
import mx.gob.stps.portal.web.oferta.search.delegate.OfertasPorPerfilBusDelegateImpl;
import mx.gob.stps.portal.web.salarios.delegate.SalarioMinimoBusDelegate;
import mx.gob.stps.portal.web.salarios.delegate.SalarioMinimoBusDelegateImpl;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class OfertaEmpleoAction extends GenericAction {

	private static String FORWARD_INFORMACION_UBICACION = "JSP_INFORMACION_UBICACION";
	private static String FORWARD_REQUISITOS = "JSP_REQUISITOS";
	private static String FORWARD_DATOS_CONTACTO= "JSP_DATOS_CONTACTO";
	private static String FORWARD_CARRERAS_ADICIONALES= "JSP_CARRERAS_ADICIONALES";
	private static String FORWARD_CONOCIMIENTOS_ADICIONALES= "JSP_CONOCIMIENTOS_ADICIONALES";
	private static String FORWARD_HABILIDADES_ADICIONALES= "JSP_HABILIDADES_ADICIONALES";
	private static String FORWARD_IDIOMAS_ADICIONALES= "JSP_IDIOMAS_ADICIONALES";
	private static String FORWARD_TELEFONOS_ADICIONALES= "JSP_TELEFONOS_ADICIONALES";
	private static String FORWARD_DETALLE_PERFIL_TIPO= "JSP_DETALLE_PERFIL_TIPO";
	private static String FORWARD_NAVEGACION = "ACTION_NAVEGACION";
	private static String FORWARD_PUBLICADOR = "ACTION_DETALLE_OFERTA";
	private static MessageLoader messageLoader = MessageLoader.getInstance();
	private static Logger logger = Logger.getLogger(OfertaEmpleoAction.class);

	private final static String EMPRESA_NOTIFICACION = "EMPRESA_NOTIFICACION";
	private final static String OFERTA_SIN_HABILIDADES = "OFERTA_SIN_HABILIDADES";
	private static final String ERROR_MSG = "ERROR_MSG";
	
	private int sumarParaLibrarIndiceCero = 1;
	private long[] filtro_idioma = {Constantes.IDIOMAS.NO_REQUISITO.getIdOpcion(), Constantes.IDIOMAS.NINGUNO.getIdOpcion()};	
	private long[] filtroNacidoEnElExtranjero = {CATALOGO_OPCION_ENTIDAD_FEDERATIVA_NACIDO_EXTRANJERO};
	private long[] filtroDiscapacidadNinguna = {TIPO_DISCAPACIDAD.NINGUNA.getIdOpcion()};
	private long OPCION_NINGUNA_CERTIFICACION = 1L;
	private long OPCION_OTRA_CERTIFICACION = 2L;
	private int DIAS_SALARIO_MINIMO = 30;
	private boolean ofertaEmpresaFP = false;
	
	//RBM1 TK990 TK995  este metodo ya no deberia usarse pero falta quitar los flujos en struts-config que siguen referenciandolo
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		OfertaEmpleoForm forma = (OfertaEmpleoForm) form;
		forma.reset();
		
		UsuarioWebVO usuarioWeb = getUsuario(request.getSession());
		RegisterBusDelegate empresaService = RegisterBusDelegateImpl.getInstance();
		SalarioMinimoBusDelegate salarioMinimoService = SalarioMinimoBusDelegateImpl.getInstance();

		if (usuarioWeb != null) {

			long idEmpresa = usuarioWeb.getIdPropietario();
			forma.setIdEmpresa((int) idEmpresa);
			try {
				
				EmpresaVO empresaVo = empresaService.findEmpresaById(idEmpresa);
				SalariosVigentesVO salariosVigentesVO = salarioMinimoService.consultaSalarioMinimoVigente();
				SalariosAplicacionesVO salariosAplicacionesVO = salarioMinimoService.consultaSalariosMinimosPortalEmpleo();
				double salarioMinimoMensual = Math.floor(salariosVigentesVO.getMonto() * 
						salariosAplicacionesVO.getNumeroSalarios() * DIAS_SALARIO_MINIMO);
				forma.setSalarioMinimoMensual(salarioMinimoMensual);
				CatalogoOpcionDelegate catalogoOpcionDelegate = CatalogoOpcionDelegateImpl.getInstance();
				//datos para oferta becate
				if( esOfertaBecate(request) ) {
					forma.setClaveOferta(generarClave());
					forma.setOfertaBecate(true);
					//valores iniciales
					forma.setDiscapacidad(ACEPTA_CANDIDATOS_CON_DISCAPACIDAD.NO.getIdOpcion());
					forma.setLunes(ConstantesGenerales.SI);					
					forma.setMartes(ConstantesGenerales.SI);
					forma.setMiercoles(ConstantesGenerales.SI);
					forma.setJueves(ConstantesGenerales.SI);
					forma.setViernes(ConstantesGenerales.SI);
				}

				if (empresaVo != null) {
					if(esOfertaDeAbriendoEspacios(request)){
						request.getSession().setAttribute(TAB_MENU, TAB_MIS_OFERTAS);
					}
					forma.setFuente(establecerFuenteOferta(request, empresaVo));									
					forma.setDomicilio(empresaVo.getDomicilio());
					setTelefonoEmpresaRegistro(empresaVo.getTelefonos(),forma);
					forma.setCorreoElectronicoContacto(empresaVo.getCorreoElectronico());
					forma.setNombreEmpresa(empresaVo.getNombreEmpresa());
					forma.setIdActividadEconomica((int) empresaVo.getIdActividadEconomica());
					forma.setNombreContacto(empresaVo.getContactoEmpresa());	
					forma.setCargoContacto(empresaVo.getCargoContacto());
					forma.setActividadEconomica(catalogoOpcionDelegate.getOpcionById(CATALOGO_OPCION_TIPO_ACTIVIDAD_ECONOMICA, empresaVo.getIdActividadEconomica()));
					List<CatalogoOpcionVO> prestaciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_PRESTACIONES, true);
					forma.setPrestaciones(prestaciones);	
					
					if (empresaVo.getEsfuncionPublica()) {
						forma.setEmpresaSFP(true);
						ofertaEmpresaFP = true;
					} else 
						forma.setEmpresaSFP(false);
					if (Utils.parseLong(request.getParameter("idTypeProfile")) > 0) {
						int index = 0;
						long idTypeProfile = Utils.parseLong(request.getParameter("idTypeProfile"));
						OfertasPorPerfilBusDelegate services = OfertasPorPerfilBusDelegateImpl.getInstance();
						PerfilTipoVO profile = services.find(idTypeProfile);
						if (null != profile) {
							forma.setFunciones(profile.getFunciones());
							forma.setConocimientoCompOtros(profile.getEquipos());
							List<OfertaRequisitoVO> conocimientos = new ArrayList<OfertaRequisitoVO>();
							for (PerfilConocimientoVO pc : profile.getPerfilConocimientos()) {
								if (index > 0) {
									OfertaRequisitoVO vo = new OfertaRequisitoVO();
									vo.setDescripcion(pc.getConocimiento());
									vo.setIdExperiencia(pc.getIdExperiencia());
									conocimientos.add(vo);
								}else {
									forma.setConocimiento(pc.getConocimiento());
									if (null != pc.getIdExperiencia())
										forma.setIdExperienciaConocimiento(pc.getIdExperiencia().intValue());
								}
								index ++;
							}
							if (!profile.getPerfilHabilidades().isEmpty()) {
								int i= 0;
								long [] habilidades = new long[profile.getPerfilHabilidades().size()];
								for (PerfilHabilidadVO vo : profile.getPerfilHabilidades()) {
									habilidades[i] = vo.getId().getIdHabilidad();
									i++;
								}
								forma.setIdHabilidad(habilidades);
							}
							forma.setConocimientos(conocimientos);
							forma.setIdperfilTipo(idTypeProfile);
							forma.setIdOcupacion(Utils.parseInt(request.getParameter("idOcupacion")));
							if (null != request.getParameter("idOcupacion"))
								forma.setIdOcupacion(Utils.parseInt(request.getParameter("idOcupacion")));
							forma.setTituloOferta(null != request.getParameter("tituloOferta")?request.getParameter("tituloOferta"):"");
							forma.setOcupacion(null != request.getParameter("ocupacionDeseada")?request.getParameter("ocupacionDeseada"):"");
						}
					}
				}
			} catch (BusinessException e) {
				e.printStackTrace();
				logger.error(e);
			} catch (SQLException e) {
				e.printStackTrace();
				logger.error(e);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			}
		}

		ocultaBarraArticulos(request);
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_INFORMACION_UBICACION).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Oferta empleo");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Oferta empleo, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
	}
	
	private boolean esOfertaDeAbriendoEspacios(HttpServletRequest request){
		boolean esOfertaAbriendoEspacios = false;
		if(null != request.getSession().getAttribute("ofae")){
			esOfertaAbriendoEspacios = true;
		}
		return esOfertaAbriendoEspacios;
	}
	
	private boolean esOfertaBecate(HttpServletRequest request){
		boolean esOfertaBecate = false;
		if(null != request.getSession().getAttribute("ofbec")){
			esOfertaBecate = true;
		}
		return esOfertaBecate;
	}
	
	private int establecerFuenteOferta(HttpServletRequest request, EmpresaVO empresaVo){
		if(esOfertaDeAbriendoEspacios(request)){
			return Constantes.CATALOGO_FUENTE_QUE_REGISTRA_OFERTA.ABRIENDO_ESPACIOS.getIdOpcion();
		}
		else {
			if(empresaVo.isOfertaCanada()){
				return PAIS_OFERTA.CANADA.getIdOpcion();
			} else {
				return PAIS_OFERTA.MEXICO.getIdOpcion();
			} 			
		}
	}
	
	private void setTelefonoEmpresaRegistro(List<TelefonoVO> telefonos, OfertaEmpleoForm forma) {
		for (TelefonoVO vo: telefonos) {
			if (vo.getPrincipal() == MULTIREGISTRO.PRINCIPAL.getIdOpcion()) {
				forma.setTipoTelefono((int)Constantes.TIPO_TELEFONO.FIJO.getIdOpcion());
				forma.setAcceso(Constantes.TIPO_TELEFONO.FIJO.getAcceso());
				forma.setClave(vo.getClave());
				forma.setTelefono(vo.getTelefono());
				forma.setExtension(vo.getExtension());
			}
		}
	}

	public ActionForward toInformacionUbicacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		
		OfertaEmpleoForm forma = (OfertaEmpleoForm) form;		
		
		try {
			if(forma.getIdOfertaEmpleo()>0){
				boolean requisitos = request.getParameter("requisitos").equals("true");
				boolean fin = request.getParameter("fin").equals("true");

				if(requisitos){
					if (forma.getTelefonosAdicionales() == null) {
						forma.setTelefonosAdicionales(new ArrayList<TelefonoVO>());
					}
					forma.setCarrerasOferta(setListaCareras(forma.getIdOfertaCarrera(),forma.getIdCarreraEspecialidad(),forma.getTotalCarrerasAdicionales(),request, forma));
					forma.setConocimientos(setListaConocimientoHabilidades(forma.getIdOfertaRequisitoConocimiento(),forma.getConocimiento(),forma.getIdExperienciaConocimiento(),TIPO_REQUISITO.CONOCIMIENTO.getIdTipoRequisito(),forma.getTotalConocimientosAdicionales(),request,forma.getTotalHabilidadesAdicionales(), forma));
					forma.setHabilidades(setListaConocimientoHabilidades(forma.getIdOfertaRequisitoHabilidad(),forma.getHabilidad(),forma.getIdExperienciaHabilidad(),TIPO_REQUISITO.HABILIDAD.getIdTipoRequisito(),forma.getTotalConocimientosAdicionales(),request,forma.getTotalHabilidadesAdicionales(), forma));
					forma.setIdiomas(obtenerListaIdiomas(forma.getIdOfertaIdioma(),forma.getIdIdioma(),forma.getIdDominioIdioma(),forma.getIdCertificacionIdioma(),forma.getTotalIdiomasAdicionales(),request, forma));
					forma.setIdiomasAdicionales(obtenerListaIdiomasAdicionales(forma.getIdiomas()));
					if(forma.getConocimientoCompNinguno() == 0) {
						forma.setConocimientoComputacion(setConocimientosComputacion(forma.getConocimientoCompHojaCal(),forma.getConocimientoCompInternet(),forma.getConocimientoCompOtros(),forma.getConocimientoCompProcesadorTxt(),forma.getConocimientoCompRedes()));
					}
				}

				if(fin){
					forma.setTelefonos(setTelefonos(forma.getAcceso(), forma.getClave(), forma.getExtension(), forma.getTipoTelefono(), forma.getIdTelefono(), forma.getTelefono(), forma.getTotalTelefonosAdicionales(), request, forma));
					forma.setDomicilio(setDomicilio(forma.getCalle(),forma.getCodigoPostal(),forma.getEntreCalle(),forma.getIdColoniaDomicilio(),forma.getIdDomicilio(),forma.getIdEntidadDomicilio(),forma.getIdMunicipioDomicilio(),forma.getNumeroExterior(),forma.getNumeroInterior(),forma.getyCalle()));
				}			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		ocultaBarraArticulos(request);
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_INFORMACION_UBICACION).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Oferta empleo");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Oferta empleo, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
	}
	
	public ActionForward toRequisitos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		
		OfertaEmpleoForm forma = (OfertaEmpleoForm) form;
		if (forma.getCarrerasAdicionales() == null) {
			forma.setCarrerasAdicionales(new ArrayList<OfertaCarreraEspecialidadVO>());
		}
		if (forma.getConocimientosAdicionales() == null) {
			forma.setConocimientosAdicionales(new ArrayList<OfertaRequisitoVO>());
		}
		if (forma.getHabilidadesAdicionales() == null) {
			forma.setHabilidadesAdicionales(new ArrayList<OfertaRequisitoVO>());
		}
		if (forma.getIdiomasAdicionales() == null) {
			forma.setIdiomasAdicionales(new ArrayList<OfertaIdiomaVO>());
		}
		try {
			List<CatalogoOpcionVO> grados = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_GRADO_ESTUDIOS, true);
			String[] gradosDependientes = new String[grados.size() + 1];
			gradosDependientes[0] = "0";
			for (CatalogoOpcionVO opcion : grados) {
				gradosDependientes[(int) opcion.getIdCatalogoOpcion()] = String.valueOf(opcion.getIdCatalagoAsociado());
			}	

			forma.setGradosDependientes(gradosDependientes);
			forma.setCatalogoIdiomas(CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_IDIOMAS, filtro_idioma, true));
			forma.setCatalogoDominios(CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_DOMINIO, true));
			forma.setIdiomasDependientes(obtenerCatalogosAsociadosDeIdiomas());
			
			if (forma.getIdOfertaEmpleo() > 0) {
				boolean requisitos = request.getParameter("requisitos").equals("true");
				boolean fin = request.getParameter("fin").equals("true");

				if (requisitos) {
					
					if (forma.getTelefonosAdicionales() == null) {
						forma.setTelefonosAdicionales(new ArrayList<TelefonoVO>());
					}
					forma.setCarrerasOferta(setListaCareras(forma.getIdOfertaCarrera(),forma.getIdCarreraEspecialidad(),forma.getTotalCarrerasAdicionales(),request, forma));
					forma.setConocimientos(setListaConocimientoHabilidades(forma.getIdOfertaRequisitoConocimiento(),forma.getConocimiento(),forma.getIdExperienciaConocimiento(),TIPO_REQUISITO.CONOCIMIENTO.getIdTipoRequisito(),forma.getTotalConocimientosAdicionales(),request,forma.getTotalHabilidadesAdicionales(), forma));
					forma.setHabilidades(setListaConocimientoHabilidades(forma.getIdOfertaRequisitoHabilidad(),forma.getHabilidad(),forma.getIdExperienciaHabilidad(),TIPO_REQUISITO.HABILIDAD.getIdTipoRequisito(),forma.getTotalConocimientosAdicionales(),request,forma.getTotalHabilidadesAdicionales(), forma));
					forma.setIdiomas(obtenerListaIdiomas(forma.getIdOfertaIdioma(),forma.getIdIdioma(),forma.getIdDominioIdioma(),forma.getIdCertificacionIdioma(),forma.getTotalIdiomasAdicionales(),request, forma));
					forma.setIdiomasAdicionales(obtenerListaIdiomasAdicionales(forma.getIdiomas()));
					if (forma.getConocimientoCompNinguno() == 0) {
						forma.setConocimientoComputacion(setConocimientosComputacion(forma.getConocimientoCompHojaCal(),forma.getConocimientoCompInternet(),forma.getConocimientoCompOtros(),forma.getConocimientoCompProcesadorTxt(),forma.getConocimientoCompRedes()));
					}

				}

				if(fin){
					forma.setTelefonos(setTelefonos(forma.getAcceso(),forma.getClave(),forma.getExtension(),forma.getTipoTelefono(),forma.getIdTelefono(),forma.getTelefono(),forma.getTotalTelefonosAdicionales(),request, forma));
					forma.setDomicilio(setDomicilio(forma.getCalle(),forma.getCodigoPostal(),forma.getEntreCalle(),forma.getIdColoniaDomicilio(),forma.getIdDomicilio(),forma.getIdEntidadDomicilio(),forma.getIdMunicipioDomicilio(),forma.getNumeroExterior(),forma.getNumeroInterior(),forma.getyCalle()));
				}
			}

			List<CatalogoOpcionVO> opcHabilidades = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_CANDIDATO_HABILIDAD, true);
			request.getSession().setAttribute("opcHabilidades", opcHabilidades);

		}catch(Exception e){
			e.printStackTrace(); logger.error(e);
		}

		ocultaBarraArticulos(request);
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_REQUISITOS).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Oferta empleo");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Oferta empleo, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);

	}
	
	private int getMaxIdCatOp(List<CatalogoOpcionVO> idiomas) {
		int max=0;
		for (CatalogoOpcionVO opcion : idiomas) {
			if (max < opcion.getIdCatalogoOpcion()) {
				max = (int)opcion.getIdCatalogoOpcion();
			}
		}
		return max;
	}
	
	private String[] obtenerCatalogosAsociadosDeIdiomas(){		
		String[] idiomasDependientes = null;
		try {
			List<CatalogoOpcionVO> idiomas = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_IDIOMAS, filtro_idioma, true);			
			idiomasDependientes = new String[getMaxIdCatOp(idiomas) + sumarParaLibrarIndiceCero];
			idiomasDependientes[0] = "0";

			for (CatalogoOpcionVO opcion : idiomas) {
				idiomasDependientes[(int) opcion.getIdCatalogoOpcion()] = String.valueOf(opcion.getIdCatalagoAsociado());	
			}
			
		} catch(Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
		}
		return idiomasDependientes;
	}		

	public ActionForward toDatosContacto(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		OfertaEmpleoForm forma = (OfertaEmpleoForm) form;
		if (forma.getIdOfertaEmpleo() > 0) {
			boolean requisitos = request.getParameter("requisitos").equals("true");
			boolean fin = request.getParameter("fin").equals("true");

			if (requisitos) {
				if(forma.getTelefonosAdicionales()==null) {
					forma.setTelefonosAdicionales(new ArrayList<TelefonoVO>());
				}
				forma.setCarrerasOferta(setListaCareras(forma.getIdOfertaCarrera(),forma.getIdCarreraEspecialidad(),forma.getTotalCarrerasAdicionales(),request, forma));
				forma.setConocimientos(setListaConocimientoHabilidades(forma.getIdOfertaRequisitoConocimiento(),forma.getConocimiento(),forma.getIdExperienciaConocimiento(),TIPO_REQUISITO.CONOCIMIENTO.getIdTipoRequisito(),forma.getTotalConocimientosAdicionales(),request,forma.getTotalHabilidadesAdicionales(), forma));
				forma.setHabilidades(setListaConocimientoHabilidades(forma.getIdOfertaRequisitoHabilidad(),forma.getHabilidad(),forma.getIdExperienciaHabilidad(),TIPO_REQUISITO.HABILIDAD.getIdTipoRequisito(),forma.getTotalConocimientosAdicionales(),request,forma.getTotalHabilidadesAdicionales(), forma));
				forma.setIdiomas(obtenerListaIdiomas(forma.getIdOfertaIdioma(),forma.getIdIdioma(),forma.getIdDominioIdioma(),forma.getIdCertificacionIdioma(),forma.getTotalIdiomasAdicionales(),request, forma));
				forma.setIdiomasAdicionales(obtenerListaIdiomasAdicionales(forma.getIdiomas()));
				if (forma.getConocimientoCompNinguno() == 0) {
					forma.setConocimientoComputacion(setConocimientosComputacion(forma.getConocimientoCompHojaCal(),forma.getConocimientoCompInternet(),forma.getConocimientoCompOtros(),forma.getConocimientoCompProcesadorTxt(),forma.getConocimientoCompRedes()));
				}
			}

			if (fin) {
				forma.setTelefonos(setTelefonos(forma.getAcceso(),forma.getClave(),forma.getExtension(),forma.getTipoTelefono(),forma.getIdTelefono(),forma.getTelefono(),forma.getTotalTelefonosAdicionales(),request, forma));
				forma.setDomicilio(setDomicilio(forma.getCalle(),forma.getCodigoPostal(),forma.getEntreCalle(),forma.getIdColoniaDomicilio(),forma.getIdDomicilio(),forma.getIdEntidadDomicilio(),forma.getIdMunicipioDomicilio(),forma.getNumeroExterior(),forma.getNumeroInterior(),forma.getyCalle()));
			}
		}else{

			forma.setAccesoCelular(CLAVE_TELEFONO_CELULAR);
			forma.setAccesoFijo(CLAVE_TELEFONO_FIJO);
			forma.setIdTipoTelefonoFijo(TELEFONO_FIJO);
			forma.setIdTipoTelefonoCelular(TELEFONO_CELULAR);
			if (forma.getTelefonosAdicionales() == null) {
				forma.setTelefonosAdicionales(new ArrayList<TelefonoVO>());
			}
			forma.setCarrerasOferta(setListaCareras(forma.getIdOfertaCarrera(), forma.getIdCarreraEspecialidad(), forma.getTotalCarrerasAdicionales(), request, forma));
			forma.setConocimientos(setListaConocimientoHabilidades(forma.getIdOfertaRequisitoConocimiento(), forma.getConocimiento(), forma.getIdExperienciaConocimiento(), TIPO_REQUISITO.CONOCIMIENTO.getIdTipoRequisito(), forma.getTotalConocimientosAdicionales(), request, forma.getTotalHabilidadesAdicionales(), forma));
			forma.setHabilidades(setListaConocimientoHabilidades(forma.getIdOfertaRequisitoHabilidad(),forma.getHabilidad(),forma.getIdExperienciaHabilidad(),TIPO_REQUISITO.HABILIDAD.getIdTipoRequisito(),forma.getTotalConocimientosAdicionales(),request,forma.getTotalHabilidadesAdicionales(), forma));
			forma.setIdiomas(obtenerListaIdiomas(forma.getIdOfertaIdioma(),forma.getIdIdioma(),forma.getIdDominioIdioma(),forma.getIdCertificacionIdioma(),forma.getTotalIdiomasAdicionales(),request, forma));
			forma.setIdiomasAdicionales(obtenerListaIdiomasAdicionales(forma.getIdiomas()));
			if (forma.getConocimientoCompNinguno() == 0) {
				forma.setConocimientoComputacion(setConocimientosComputacion(forma.getConocimientoCompHojaCal(),forma.getConocimientoCompInternet(),forma.getConocimientoCompOtros(),forma.getConocimientoCompProcesadorTxt(),forma.getConocimientoCompRedes()));
			}

		}

		ocultaBarraArticulos(request);
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_DATOS_CONTACTO).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Oferta empleo");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Oferta empleo, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);

	}


	public ActionForward registrarOferta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){

		ActionForward forward = getForward(mapping, request, FORWARD_DATOS_CONTACTO);
		
		OfertaEmpleoForm forma = (OfertaEmpleoForm) form;
		
		boolean valido = validaDatosBasicos(forma, request);

		if (!valido) {
			return forward;
		}		
				
		long idOfertaGuardada = 0;
		
		try {
			UsuarioWebVO usuarioWeb =  getUsuario(request.getSession());						
			
			OfertaBusDelegate service = OfertaBusDelegateImpl.getInstance();
			
			forma.setTelefonos(setTelefonos(forma.getAcceso(),forma.getClave(),forma.getExtension(),forma.getTipoTelefono(),forma.getIdTelefono(),forma.getTelefono(),forma.getTotalTelefonosAdicionales(),request, forma));
			
			forma.setDomicilio(setDomicilio(forma.getCalle(),forma.getCodigoPostal(),forma.getEntreCalle(),forma.getIdColoniaDomicilio(),forma.getIdDomicilio(),forma.getIdEntidadDomicilio(),forma.getIdMunicipioDomicilio(),forma.getNumeroExterior(),forma.getNumeroInterior(),forma.getyCalle()));		
					
			OfertaEmpleoVO ofertaEmpleoVo = getOfertaEmpleoVo(forma);
			ofertaEmpleoVo.setIdUsuario(usuarioWeb.getIdUsuario());
			ofertaEmpleoVo.setPlazasCubiertas(new Integer(0));
			ofertaEmpleoVo.setPublicarOfertas((int) PUBLICAR_OFERTA_PE_SNETEL.SI.getIdOpcion());
			
			if(null!=forma.getFunciones() && forma.getFunciones().length() > 2000) {
				ofertaEmpleoVo.setFunciones(forma.getFunciones().substring(0,2000));	
			}
			if(null!=forma.getEmpresaOfrece() && forma.getEmpresaOfrece().length() > 2000){
				ofertaEmpleoVo.setEmpresaOfrece(forma.getEmpresaOfrece().substring(0,2000));	
			}
			if(null!=forma.getObservaciones() && forma.getObservaciones().length() > 2000){
				ofertaEmpleoVo.setObservaciones(forma.getObservaciones().substring(0,2000));	
			}			

			idOfertaGuardada = service.registraOfertaEmpleo(ofertaEmpleoVo);
			
			if (idOfertaGuardada > 0) {
				
				/** Se actualizan las Habilidades en caso de provenir de una Oferta utilizada como Plantilla o Recuperada **/
				if (forma.getIdOfertaEmpleoOriginal() > 0 && forma.getIdHabilidad() != null && forma.getIdHabilidad().length > 0) {
					//System.out.println(" *********************** Se actualizan la Oferta Utilizada como Plantilla de Recuperada"+ forma.getIdOfertaEmpleoOriginal());
					service.actualizaHabilidades(forma.getIdOfertaEmpleoOriginal(), forma.getIdHabilidad());
				}

				forma.setIdTipoDiscapacidad((int)Constantes.TIPO_DISCAPACIDAD.NINGUNA.getIdOpcion());	
				
				request.getSession().setAttribute("nuevaOfertaGuardada", String.valueOf(idOfertaGuardada));
				if(ofertaEmpleoVo.isOfertaBecate()){
					request.getSession().setAttribute("claveOferta", ofertaEmpleoVo.getOfertaEmpleoBecate().getClaveOferta());					
				}
				request.getSession().removeAttribute("ofae");

				forward = mapping.findForward(FORWARD_NAVEGACION);

			} else {
				request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.Exception"));				
			}		
			
			/*
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.BusinessException", e.getMessage()));
			*/
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.ServiceLocatorException"));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.Exception"));
		} 

		ocultaBarraArticulos(request);
		
		return forward;
	}

	
	
	private ActionForward getForward(ActionMapping mapping, HttpServletRequest request, String forwardName) {
		HttpSession session = request.getSession();
		session.setAttribute(BODY_JSP, mapping.findForward(forwardName).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Oferta empleo");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Oferta empleo, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ request.getRequestURI().toString());
        return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
	}	
	
	
	private boolean validaDatosBasicos(OfertaEmpleoForm forma, HttpServletRequest request){
		
		boolean valido = true;
		
		StringBuilder buf = new StringBuilder();

		if (forma.getIdEmpresa() <= 0){
			valido = false;
			buf.append("El identificador de la empresa es requerido.<br/>");
		}
					
		if (forma.getTituloOferta() == null) {
			valido = false;
			buf.append("El titulo de la oferta es requerido.<br/>");			
		} 

		if (forma.getNumeroPlazas() <= 0) {
			valido = false;
			buf.append("El número de plazas es requerido.<br/>");
		}
		
		if (forma.getFunciones() == null) {
			valido = false;
			buf.append("Las funciones son requeridas.<br/>");						
		} 
		
/*		COMENTADO PORQUE VA A DESAPARECER EL CAMPO A SOLICITUD DEL CLIENTE
 * 		if (forma.getIdTipoEmpleo()<=0) {
			valido = false;
			buf.append("El tipo de empleo es requerido.<br/>");									
		}*/
				
		if (forma.getIdTipoContrato() <= 0) {
			valido = false;
			buf.append("El tipo de contacto es requerido.<br/>");												
		}
				
		if (forma.getHoraEntrada() == null) {
			valido = false;
			buf.append("El horario de entrada es requerido.<br/>");															
		}		
		
		if (forma.getHoraSalida() == null) {
			valido = false;
			buf.append("El horario de salida es requerido.<br/>");																		
		} 		
		
		if (forma.getRolarTurnos() <= 0){
			valido = false;
			buf.append("El indicador si rola turno es requerido.<br/>");																					
		} 	
		
		if (forma.getDiasLaborales() == null || forma.getDiasLaborales().isEmpty()) {
			valido = false;
			buf.append("Los dias laborales son requeridos.<br/>");																								
		}
		
		if (forma.getGenero() <= 0) {
			valido = false;
			buf.append("El indicador de genero es requerido.<br/>");																											
		}

		if (forma.getSalario() <= 0) {
			valido = false;
			buf.append("El salario es requerido.<br/>");																														
		}
		// TODO: Validate salarioMinimoMensual
		
		if (forma.getRadicar() <= 0) {
			valido = false;
			buf.append("El indicador de disponibilidad para radicar es requerido.<br/>");																																	
		}
				
		if (forma.getViajar() <= 0) {
			valido = false;
			buf.append("El indicador de disponibilidad para viajar es requerido.<br/>");																																				
		}
		
		if (forma.getIdOcupacion() <= 0) {
			valido = false;
			buf.append("La ocupación es requerida.<br/>");
		}
		
		if (forma.getIdCausaOferta() <= 0){
			valido = false;
			buf.append("La causa que motiva la oferta es requerida.<br/>");																																										
		} 
		
		if (forma.getEmpresaOfrece() == null) {
			valido = false;
			buf.append("Las funciones son requeridas.<br/>");																																													
		} 
		
		if (forma.getEmpresaSFP() && forma.getCodigo_universal_de_puesto_sfp() <= 0) {
			valido = false;
			buf.append("El codigo universal de puesto para la SFP debe ser mayor a cero.");
		}

		if (!valido) {
			String errmsg = "<b>No se cuenta con los datos básicos para el registro:</b><br/>"+
					buf.toString() +
					"<b>Favor de verificar su captura o intentar de nuevo el registro, "+
					"notificar al administrador en caso de continuar con el problema.</b>";			
		
			request.setAttribute(ERROR_MSG, errmsg);
		}

		return valido;
	}
	
	
//RBM1  TK997  TK998 24 abr- 2018  no debe entrar ninguna redireccion a esta pantalla, cambio del redireccionamiento publicador.
	/*

	public ActionForward edicionInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){

		String idOferta = request.getParameter("id");
		OfertaEmpleoForm forma = (OfertaEmpleoForm) form;
		UsuarioWebVO user = getUsuario(request.getSession());
		Integer tipoOferta = null != request.getParameter("tipoOferta") ? Integer.parseInt(request.getParameter("tipoOferta")) : 0;
		OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();
		RegisterBusDelegate empresaService = RegisterBusDelegateImpl.getInstance();
		CatalogoOpcionDelegate serviceCatalogo = CatalogoOpcionDelegateImpl.getInstance();
		forma.reset();
		try {
			OfertaEmpleoVO ofertaVO = services.obtenerOferta(Utils.parseLong(idOferta));
			EmpresaVO empresaVo = empresaService.findEmpresaById(user.getIdPropietario());
			SalarioMinimoBusDelegate salarioMinimoService = SalarioMinimoBusDelegateImpl.getInstance();
			List<CatalogoOpcionVO> grados = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_GRADO_ESTUDIOS, true);
			SalariosVigentesVO salariosVigentesVO = salarioMinimoService.consultaSalarioMinimoVigente();
			SalariosAplicacionesVO salariosAplicacionesVO = salarioMinimoService.consultaSalariosMinimosPortalEmpleo();
			double salarioMinimoMensual = Math.floor(salariosVigentesVO.getMonto() * 
					salariosAplicacionesVO.getNumeroSalarios() * DIAS_SALARIO_MINIMO);
			forma.setSalarioMinimoMensual(salarioMinimoMensual);
			//oferta becate
			if(tipoOferta == ConstantesGenerales.TIPO_OFERTA.OFERTA_BECATE.getIdOpcion()){
				ofertaVO.setOfertaBecate(true);
				ofertaVO.setOfertaEmpleoBecate(services.obtenerOfertaBecateById(Utils.parseLong(idOferta)));
				ModalidadOfertaVO modalidadVO = services.obtenerModalidadOfertaByIdOferta(Utils.parseLong(idOferta));
				ofertaVO.setModalidadOferta(modalidadVO);
				ofertaVO.setIdModalidad(modalidadVO.getIdModalidad().intValue());
			}
			request.getSession().setAttribute("empresaPublica", empresaVo.getNombreComercial());
			String[] gradosDependientes = new String[grados.size() + 1];
			gradosDependientes[0] = "0";

			for (CatalogoOpcionVO opcion : grados) {
				gradosDependientes[(int) opcion.getIdCatalogoOpcion()] = String.valueOf(opcion.getIdCatalagoAsociado());
			}	

			forma.setGradosDependientes(gradosDependientes);

			forma.setIdiomasDependientes(obtenerCatalogosAsociadosDeIdiomas());

			List<CatalogoOpcionVO> prestaciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_PRESTACIONES, true);
			forma.setPrestaciones(prestaciones);
			setOfertaEmpleoForm(ofertaVO,forma);
			forma.setAccesoCelular(CLAVE_TELEFONO_CELULAR);
			forma.setAccesoFijo(CLAVE_TELEFONO_FIJO);
			forma.setIdTipoTelefonoFijo(TELEFONO_FIJO);
			forma.setIdTipoTelefonoCelular(TELEFONO_CELULAR);

			if (forma.getCarrerasAdicionales() == null) {
				forma.setCarrerasAdicionales(new ArrayList<OfertaCarreraEspecialidadVO>());
			}
			if (forma.getConocimientosAdicionales() == null) {
				forma.setConocimientosAdicionales(new ArrayList<OfertaRequisitoVO>());
			}
			if (forma.getHabilidadesAdicionales() == null) {
				forma.setHabilidadesAdicionales(new ArrayList<OfertaRequisitoVO>());
			}
			if (forma.getIdiomasAdicionales() == null) {
				forma.setIdiomasAdicionales(new ArrayList<OfertaIdiomaVO>());
			}
			forma.setOcupacion(serviceCatalogo.getOpcionById(CATALOGO_OPCION_OCUPACION, forma.getIdOcupacion()));

			List<CatalogoOpcionVO> opcHabilidades = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_CANDIDATO_HABILIDAD, true);
			request.getSession().setAttribute("opcHabilidades", opcHabilidades);

		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error(e);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			logger.error(e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		ocultaBarraArticulos(request);
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_INFORMACION_UBICACION).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Oferta empleo");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Oferta empleo, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
	}

	*/
	public ActionForward plantillaInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){

		String idOferta = request.getParameter("id");
		String idEvento = request.getParameter("idEvento");
		Integer tipoOferta = Integer.parseInt(request.getParameter("tipoOferta"));
		OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();
		CatalogoOpcionDelegate serviceCatalogo = CatalogoOpcionDelegateImpl.getInstance();

		OfertaEmpleoForm forma = (OfertaEmpleoForm) form;
		forma.reset();
		// Se establece la Oferta Original que se utiliza como Plantilla
		forma.setIdOfertaEmpleoOriginal(Utils.parseLong(idOferta));
		
		UsuarioWebVO usuarioWeb = getUsuario(request.getSession());
		try {
			OfertaEmpleoVO ofertaVO = services.obtenerOferta(Utils.parseLong(idOferta));
			setSalaryLimit(forma);
			//oferta becate
			if(tipoOferta == ConstantesGenerales.TIPO_OFERTA.OFERTA_BECATE.getIdOpcion()){
				ofertaVO.setOfertaBecate(true);
				ofertaVO.setOfertaEmpleoBecate(services.obtenerOfertaBecateById(Utils.parseLong(idOferta)));
				ofertaVO.getOfertaEmpleoBecate().setClaveOferta(generarClave());					
				ModalidadOfertaVO modalidadVO = services.obtenerModalidadOfertaByIdOferta(Utils.parseLong(idOferta));
				ofertaVO.setIdModalidad(modalidadVO.getIdModalidad().intValue());
			}
			
			List<CatalogoOpcionVO> grados = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_GRADO_ESTUDIOS, true);

			String[] gradosDependientes = new String[grados.size() + 1];
			gradosDependientes[0] = "0";

			for (CatalogoOpcionVO opcion : grados) {
				gradosDependientes[(int) opcion.getIdCatalogoOpcion()] = String.valueOf(opcion.getIdCatalagoAsociado());
			}	

			forma.setGradosDependientes(gradosDependientes);

			forma.setIdiomasDependientes(obtenerCatalogosAsociadosDeIdiomas());

			List<CatalogoOpcionVO> prestaciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_PRESTACIONES, true);
			forma.setPrestaciones(prestaciones);
			setOfertaEmpleoForm(ofertaVO,forma);
			forma.setOcupacion(serviceCatalogo.getOpcionById(CATALOGO_OPCION_OCUPACION, forma.getIdOcupacion()));
			forma.setIdOfertaEmpleo(0);

			if (ofertaVO != null) {
				if (Utils.parseLong(idEvento) == EVENTO.RECUPERAR_OFERTA.getIdEvento()) {
					services.insertarEventoBitacora(EVENTO.RECUPERAR_OFERTA,usuarioWeb.getIdUsuario(),TIPO_REGISTRO.OFERTA,Utils.parseLong(idOferta),ofertaVO.getEstatus());
				}

				if (Utils.parseLong(idEvento) == EVENTO.USAR_PLANTILLA_OFERTA.getIdEvento()) {
					services.insertarEventoBitacora(EVENTO.USAR_PLANTILLA_OFERTA,usuarioWeb.getIdUsuario(),TIPO_REGISTRO.OFERTA,Utils.parseLong(idOferta),ofertaVO.getEstatus());
				}

				// Se verifica si cuenta con habilidades del nuevo catalogo, sino se notifica a la empresa del cambio
				if (ofertaVO.getIdHabilidad()==null || ofertaVO.getIdHabilidad().length==0){
					request.setAttribute(EMPRESA_NOTIFICACION, OFERTA_SIN_HABILIDADES);
				}
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error(e);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			logger.error(e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		ocultaBarraArticulos(request);
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_INFORMACION_UBICACION).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Oferta empleo");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Oferta empleo, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
	}

	private void setSalaryLimit(OfertaEmpleoForm form) throws ServiceLocatorException {
		SalarioMinimoBusDelegate salarioMinimoService = SalarioMinimoBusDelegateImpl.getInstance();
		SalariosVigentesVO salariosVigentesVO = salarioMinimoService.consultaSalarioMinimoVigente();
		SalariosAplicacionesVO salariosAplicacionesVO = salarioMinimoService.consultaSalariosMinimosPortalEmpleo();
		double salarioMinimoMensual = Math.floor(salariosVigentesVO.getMonto() * 
				salariosAplicacionesVO.getNumeroSalarios() * DIAS_SALARIO_MINIMO);
		form.setSalarioMinimoMensual(salarioMinimoMensual);
	}

	public ActionForward editarOferta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		long idRegValidar = 0;
		String mensajeEdicion = "";
		OfertaEmpleoForm forma = (OfertaEmpleoForm) form;
		UsuarioWebVO usuarioWeb = getUsuario(request.getSession());
		String bodyJsp =  mapping.findForward(FORWARD_INFORMACION_UBICACION).getPath();    		
		ActionForward actionForward = mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
		if (null != request.getSession().getAttribute(PARAM_ID_REGISTRO_POR_VALIDAR)) {
			idRegValidar = (Long)request.getSession().getAttribute(PARAM_ID_REGISTRO_POR_VALIDAR);
		} 
		try {
			boolean requisitos = request.getParameter("requisitos").equals("true");
			boolean fin = request.getParameter("fin").equals("true");
			boolean informacion = request.getParameter("informacion").equals("true");
			if (requisitos) {
				if (forma.getTelefonosAdicionales() == null) {
					forma.setTelefonosAdicionales(new ArrayList<TelefonoVO>());
				}
				forma.setCarrerasOferta(setListaCareras(forma.getIdOfertaCarrera(),forma.getIdCarreraEspecialidad(),forma.getTotalCarrerasAdicionales(),request,forma));
				forma.setConocimientos(setListaConocimientoHabilidades(forma.getIdOfertaRequisitoConocimiento(),forma.getConocimiento(),forma.getIdExperienciaConocimiento(),TIPO_REQUISITO.CONOCIMIENTO.getIdTipoRequisito(),forma.getTotalConocimientosAdicionales(),request,forma.getTotalHabilidadesAdicionales(),forma));
				forma.setHabilidades(setListaConocimientoHabilidades(forma.getIdOfertaRequisitoHabilidad(),forma.getHabilidad(),forma.getIdExperienciaHabilidad(),TIPO_REQUISITO.HABILIDAD.getIdTipoRequisito(),forma.getTotalConocimientosAdicionales(),request,forma.getTotalHabilidadesAdicionales(),forma));				
				forma.setIdiomas(obtenerListaIdiomas(forma.getIdOfertaIdioma(),forma.getIdIdioma(),forma.getIdDominioIdioma(),forma.getIdCertificacionIdioma(),forma.getTotalIdiomasAdicionales(),request,forma));
				forma.setIdiomasAdicionales(obtenerListaIdiomasAdicionales(forma.getIdiomas()));
				if (forma.getConocimientoCompNinguno() == 0) {
					forma.setConocimientoComputacion(setConocimientosComputacion(forma.getConocimientoCompHojaCal(),forma.getConocimientoCompInternet(),forma.getConocimientoCompOtros(),forma.getConocimientoCompProcesadorTxt(),forma.getConocimientoCompRedes()));
				}
				//datosValidos = valiDatosEdicion(FORWARD_REQUISITOS,forma);
				bodyJsp =  mapping.findForward(FORWARD_REQUISITOS).getPath();
				if (TIPO_USUARIO.EMPRESA.getIdTipoUsuario() == usuarioWeb.getIdTipoUsuario()) {
					mensajeEdicion = messageLoader.getMessage("oferta.empleo.edicion.mensaje.administrador")+" requisitos de la oferta de empleo. "+messageLoader.getMessage("oferta.empleo.edicion.mensaje.empresa");
				}
				if (TIPO_USUARIO.ADMINISTRADOR.getIdTipoUsuario() == usuarioWeb.getIdTipoUsuario()) {
					bodyJsp =  mapping.findForward(FORWARD_DATOS_CONTACTO).getPath();
					mensajeEdicion = messageLoader.getMessage("oferta.empleo.edicion.mensaje.administrador")+" requisitos de la oferta de empleo. ";
				}
				if(forma.getFuente() == Constantes.CATALOGO_FUENTE_QUE_REGISTRA_OFERTA.SISNE.getIdOpcion()){
					mensajeEdicion = messageLoader.getMessage("oferta.empleo.edicion.mensaje.administrador")+" requisitos de la oferta de empleo. ";
				}				
			}
			if (fin) {
				forma.setTelefonos(setTelefonos(forma.getAcceso(),forma.getClave(),forma.getExtension(),forma.getTipoTelefono(),forma.getIdTelefono(),forma.getTelefono(),forma.getTotalTelefonosAdicionales(),request,forma));
				forma.setDomicilio(setDomicilio(forma.getCalle(),forma.getCodigoPostal(),forma.getEntreCalle(),forma.getIdColoniaDomicilio(),forma.getIdDomicilio(),forma.getIdEntidadDomicilio(),forma.getIdMunicipioDomicilio(),forma.getNumeroExterior(),forma.getNumeroInterior(),forma.getyCalle()));
				bodyJsp =  mapping.findForward(FORWARD_DATOS_CONTACTO).getPath();
				//datosValidos = valiDatosEdicion(FORWARD_DATOS_CONTACTO,forma);

				if (TIPO_USUARIO.EMPRESA.getIdTipoUsuario() == usuarioWeb.getIdTipoUsuario()) {
					mensajeEdicion = messageLoader.getMessage("oferta.empleo.edicion.mensaje.administrador")+" contacto de la oferta de empleo. "+messageLoader.getMessage("oferta.empleo.edicion.mensaje.empresa");

				}
				if (TIPO_USUARIO.ADMINISTRADOR.getIdTipoUsuario() == usuarioWeb.getIdTipoUsuario()) {
					mensajeEdicion = messageLoader.getMessage("oferta.empleo.edicion.mensaje.administrador")+" contacto de la oferta de empleo. ";
					actionForward =  mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
					bodyJsp =  mapping.findForward(FORWARD_PUBLICADOR).getPath();
				}
				
				if (forma.getFuente() == Constantes.CATALOGO_FUENTE_QUE_REGISTRA_OFERTA.SISNE.getIdOpcion()) {
					mensajeEdicion = messageLoader.getMessage("oferta.empleo.edicion.mensaje.administrador")+" contacto de la oferta de empleo. ";
				}					
			}
			if (informacion) {
				if (TIPO_USUARIO.EMPRESA.getIdTipoUsuario() == usuarioWeb.getIdTipoUsuario()) {
					mensajeEdicion = messageLoader.getMessage("oferta.empleo.edicion.mensaje.administrador")+" información y ubicación de la oferta de empleo. "+messageLoader.getMessage("oferta.empleo.edicion.mensaje.empresa");
				}
				if (TIPO_USUARIO.ADMINISTRADOR.getIdTipoUsuario() == usuarioWeb.getIdTipoUsuario()) {
					bodyJsp =  mapping.findForward(FORWARD_REQUISITOS).getPath();
					mensajeEdicion = messageLoader.getMessage("oferta.empleo.edicion.mensaje.administrador")+" información y ubicación de la oferta de empleo. ";
				}
				if (forma.getFuente() == Constantes.CATALOGO_FUENTE_QUE_REGISTRA_OFERTA.SISNE.getIdOpcion()) {
					mensajeEdicion = messageLoader.getMessage("oferta.empleo.edicion.mensaje.administrador")+" información y ubicación de la oferta de empleo. ";
				}						
			}
			forma.setIdTipoDiscapacidad((int)Constantes.TIPO_DISCAPACIDAD.NINGUNA.getIdOpcion());
			OfertaEmpleoVO ofertaEmpleoVo = getOfertaEmpleoVo(forma);
			/** EDICION DE OFERTAS DE EMPLEO **/
			OfertaBusDelegate service = OfertaBusDelegateImpl.getInstance();
			service.editaOfertaEmpleo(ofertaEmpleoVo, usuarioWeb.getIdTipoUsuario(), usuarioWeb.getIdUsuario(), idRegValidar);
			if (TIPO_USUARIO.ADMINISTRADOR.getIdTipoUsuario() == usuarioWeb.getIdTipoUsuario())
				request.getSession().setAttribute("alert4Admin", mensajeEdicion);
			else
				request.getSession().setAttribute("mensajeEdicion", mensajeEdicion);
			CatalogoOpcionDelegate serviceCatalogo = CatalogoOpcionDelegateImpl.getInstance();
			forma.setOcupacion(serviceCatalogo.getOpcionById(CATALOGO_OPCION_OCUPACION, forma.getIdOcupacion()));

		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error(e);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			logger.error(e);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			logger.error(e);
		} catch (TechnicalException e) {
			e.printStackTrace();
			logger.error(e);
		} catch (IndexerException e) {
			e.printStackTrace();
			logger.error(e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.Exception"));
		} 
		ocultaBarraArticulos(request);
		request.getSession().setAttribute(BODY_JSP,bodyJsp);
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Oferta empleo");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Oferta empleo, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString())); 
		return actionForward;
	}


	public ActionForward cancelarEdicionOferta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		String bodyJsp = FORWARD_INFORMACION_UBICACION;

		OfertaEmpleoForm forma = (OfertaEmpleoForm) form;
		long idOferta = forma.getIdOfertaEmpleo();
		Boolean ofertaBecate = forma.isOfertaBecate();
		
		OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();
		CatalogoOpcionDelegate serviceCatalogo = CatalogoOpcionDelegateImpl.getInstance();
		forma.reset();
		try {
			OfertaEmpleoVO ofertaVO = services.obtenerOferta(idOferta);
			
			//oferta becate
			if(ofertaBecate){
				ofertaVO.setOfertaBecate(true);
				ofertaVO.setOfertaEmpleoBecate(services.obtenerOfertaBecateById(idOferta));
				ModalidadOfertaVO modalidadVO = services.obtenerModalidadOfertaByIdOferta(idOferta);
				ofertaVO.setModalidadOferta(modalidadVO);
				ofertaVO.setIdModalidad(modalidadVO.getIdModalidad().intValue());
			}
			
			List<CatalogoOpcionVO> grados = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_GRADO_ESTUDIOS, true);
			String[] gradosDependientes = new String[grados.size() + 1];
			gradosDependientes[0] = "0";
			for (CatalogoOpcionVO opcion : grados) {
				gradosDependientes[(int) opcion.getIdCatalogoOpcion()] = String.valueOf(opcion.getIdCatalagoAsociado());
			}
			forma.setGradosDependientes(gradosDependientes);
			forma.setIdiomasDependientes(obtenerCatalogosAsociadosDeIdiomas());
			List<CatalogoOpcionVO> prestaciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_PRESTACIONES, true);
			forma.setPrestaciones(prestaciones);
			setOfertaEmpleoForm(ofertaVO,forma);
			forma.setAccesoCelular(CLAVE_TELEFONO_CELULAR);
			forma.setAccesoFijo(CLAVE_TELEFONO_FIJO);
			forma.setIdTipoTelefonoFijo(TELEFONO_FIJO);
			forma.setIdTipoTelefonoCelular(TELEFONO_CELULAR);

			if (forma.getCarrerasAdicionales() == null) {
				forma.setCarrerasAdicionales(new ArrayList<OfertaCarreraEspecialidadVO>());
			}
			if (forma.getConocimientosAdicionales() == null) {
				forma.setConocimientosAdicionales(new ArrayList<OfertaRequisitoVO>());
			}
			if (forma.getHabilidadesAdicionales() == null) {
				forma.setHabilidadesAdicionales(new ArrayList<OfertaRequisitoVO>());
			}
			if (forma.getIdiomasAdicionales() == null) {
				forma.setIdiomasAdicionales(new ArrayList<OfertaIdiomaVO>());
			}
			forma.setOcupacion(serviceCatalogo.getOpcionById(CATALOGO_OPCION_OCUPACION, forma.getIdOcupacion()));

			boolean requisitos = request.getParameter("requisitos").equals("true");
			boolean fin = request.getParameter("fin").equals("true");
			if (requisitos) {
				bodyJsp =  FORWARD_REQUISITOS;
			}
			if (fin) {
				bodyJsp =  FORWARD_DATOS_CONTACTO;
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error(e);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			logger.error(e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		ocultaBarraArticulos(request);
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(bodyJsp).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Oferta empleo");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Oferta empleo, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);

	}

	private void setOfertaEmpleoForm(OfertaEmpleoVO ofertaVO, OfertaEmpleoForm forma) {
		forma.setFuente(ofertaVO.getFuente());
		forma.setCarrerasMultiRegistro(setCarrerasMultiRegistro(ofertaVO.getIdNivelEstudio(),forma.getGradosDependientes()));
		setCarrerasForm(ofertaVO,forma);
		setConocimientosForm(ofertaVO,forma);
		setHabilidadesForm(ofertaVO,forma);
		
		inicializarDatosDeIdiomasEnForm(ofertaVO,forma);
		
		setPrestaciones(ofertaVO.getPrestaciones(),forma);
		setTelefonos(ofertaVO,forma);
		
		forma.setConocimientoComputacion(ofertaVO.getConocimientoComputacionVO());		
		if (ofertaVO.getConocimientoComputacionVO() == null) {
			forma.setConocimientoCompNinguno(1);
		} else {
			forma.setConocimientoCompHojaCal(ofertaVO.getConocimientoComputacion().getHojaCalculo());
			forma.setConocimientoCompInternet(ofertaVO.getConocimientoComputacion().getInternet());
			forma.setConocimientoCompOtros(ofertaVO.getConocimientoComputacion().getOtros());
			forma.setConocimientoCompProcesadorTxt(ofertaVO.getConocimientoComputacion().getProcesadorTxt());
			forma.setConocimientoCompRedes(ofertaVO.getConocimientoComputacion().getRedesSociales());
		}
				
		forma.setCarrerasOferta(ofertaVO.getCarreras());
		forma.setConocimientos(ofertaVO.getConocimientos());
		forma.setHabilidades(ofertaVO.getHabilidades());
		forma.setIdiomas(ofertaVO.getIdiomas());
		forma.setTelefonos(ofertaVO.getTelefonos());
		forma.setIdEmpresa((int) ofertaVO.getIdEmpresa());
		forma.setIdOfertaEmpleo((int) ofertaVO.getIdOfertaEmpleo());
		forma.setTituloOferta(ofertaVO.getTituloOferta());
		forma.setIdOcupacion((int) ofertaVO.getIdOcupacion());
		forma.setNumeroPlazas(ofertaVO.getNumeroPlazas());
		forma.setFunciones(ofertaVO.getFunciones());
		
		//TODO PROXIMAMENTE VA A DESAPARCER EL CAMPO TIPO_EMPLEO,  A SOLICITUD DEL CLIENTE
		//forma.setIdTipoEmpleo((int) ofertaVO.getIdTipoEmpleo());
		forma.setIdTipoEmpleo(1);
		
		forma.setIdTipoContrato((int) ofertaVO.getIdTipoContrato());
		forma.setHoraEntrada(ofertaVO.getHoraEntrada());
		forma.setHoraSalida(ofertaVO.getHoraSalida());
		forma.setRolarTurnos(ofertaVO.getRolarTurno());
		forma.setDiasLaborales(ofertaVO.getDiasLaborales());
		forma.setDomingo(Integer.parseInt(ofertaVO.getDiasLaborales().substring(0,1)));
		forma.setLunes(Integer.parseInt(ofertaVO.getDiasLaborales().substring(1,2)));
		forma.setMartes(Integer.parseInt(ofertaVO.getDiasLaborales().substring(2,3)));
		forma.setMiercoles(Integer.parseInt(ofertaVO.getDiasLaborales().substring(3,4)));
		forma.setJueves(Integer.parseInt(ofertaVO.getDiasLaborales().substring(4,5)));
		forma.setViernes(Integer.parseInt(ofertaVO.getDiasLaborales().substring(5,6)));
		forma.setSabado(Integer.parseInt(ofertaVO.getDiasLaborales().substring(6,7)));		
		forma.setIdCausaOferta((int) ofertaVO.getIdCausaVacante());
		forma.setIdEntidad(ofertaVO.getIdEntidadUbicacion());
		forma.setIdMunicipio(ofertaVO.getIdMunicipioUbicacion());
		forma.setUrlUbicacion(ofertaVO.getMapaUbicacion());
		forma.setIdNivelEstudio((int) ofertaVO.getIdNivelEstudio());
		forma.setIdSituacionAcademica((int) ofertaVO.getIdSituacionAcademica());
//		forma.setHabilidadGeneral(ofertaVO.getHabilidadGeneral());
		forma.setAniosExperiencia(ofertaVO.getExperienciaAnios());
		
		if(Constantes.CATALOGO_FUENTE_QUE_REGISTRA_OFERTA.ABRIENDO_ESPACIOS.getIdOpcion() == forma.getFuente()){
			forma.setEdadRequisito(Constantes.EDAD_REQUISITO.SI.getIdOpcion());			
		} else {
			forma.setEdadRequisito(ofertaVO.getEdadRequisito());		
		}						
		
		forma.setEdadMaxima(ofertaVO.getEdadMaxima());
		forma.setEdadMinima(ofertaVO.getEdadMinima());
		forma.setGenero(ofertaVO.getGenero());
		forma.setViajar(ofertaVO.getDisponibilidadViajar());
		forma.setRadicar(ofertaVO.getDisponibilidadRadicar());
		forma.setObservaciones(ofertaVO.getObservaciones());
		forma.setSalario(ofertaVO.getSalario());
		forma.setEmpresaOfrece(ofertaVO.getEmpresaOfrece());
		forma.setNombreEmpresa(ofertaVO.getNombreEmpresa());
		forma.setNombreContacto(ofertaVO.getNombreContacto());
		forma.setTelefonos(ofertaVO.getTelefonos());
		forma.setDomicilio(ofertaVO.getDomicilio());		
		forma.setContactoCorreo(ofertaVO.getContactoCorreo());
		forma.setContactoTelefono(ofertaVO.getContactoTel());
		Integer contactoDomicilio = ofertaVO.getContactoDomicilio() != null ? ofertaVO.getContactoDomicilio() : 1;
		forma.setContactoDomicilio(contactoDomicilio);		
		forma.setCargoContacto(ofertaVO.getCargoContacto());
		forma.setCorreoElectronicoContacto(ofertaVO.getCorreoElectronicoContacto());
		forma.setIdActividadEconomica(ofertaVO.getIdActividadEconomica());
		forma.setFechaInicio(ofertaVO.getFechaInicio());	
		
		//datosBecate
		if (ofertaVO.isOfertaBecate()) {
			forma.setOfertaBecate(true);
			forma.setClaveOferta(ofertaVO.getOfertaEmpleoBecate().getClaveOferta());
			forma.setIdModalidad(ofertaVO.getIdModalidad());
			forma.setIdCurso(ofertaVO.getOfertaEmpleoBecate().getIdCurso().intValue());
			forma.setIdTipoRecurso(ofertaVO.getOfertaEmpleoBecate().getIdTipoRecurso().intValue());
			forma.setNumeroPlazasBecate(ofertaVO.getOfertaEmpleoBecate().getNumeroPlazas());
			forma.setIdHorarioImparticion(ofertaVO.getOfertaEmpleoBecate().getIdHorarioImparticion().intValue());
			forma.setIdHorarioEntrada(ofertaVO.getOfertaEmpleoBecate().getIdHorarioEntrada().intValue());
			forma.setIdHorarioSalida(ofertaVO.getOfertaEmpleoBecate().getIdHorarioSalida().intValue());
			forma.setFechaInicioBecate(ofertaVO.getOfertaEmpleoBecate().getFechaInicio());
			forma.setFechaFinBecate(ofertaVO.getOfertaEmpleoBecate().getFechaFin());
			forma.setIdDuracion(ofertaVO.getOfertaEmpleoBecate().getIdDuracion().intValue());	
			forma.setIdSalario(ofertaVO.getOfertaEmpleoBecate().getIdSalario().intValue());
			forma.setMonto(ofertaVO.getOfertaEmpleoBecate().getMonto());
			forma.setModalidadOferta(ofertaVO.getModalidadOferta());
		}
		
		if (ofertaVO.getIdVigenciaOferta() != null){
			forma.setIdVigenciaOferta(ofertaVO.getIdVigenciaOferta());
		}		
		//forma.setIdAreaLaboral(ofertaVO.getIdAreaLaboral());
		forma.setIdHabilidad(ofertaVO.getIdHabilidad());
		
		colocarDiscapacidades(ofertaVO, forma);
		
	}
	
	private void colocarDiscapacidades(OfertaEmpleoVO ofertaVO, OfertaEmpleoForm forma){
		if(Constantes.CATALOGO_FUENTE_QUE_REGISTRA_OFERTA.SISNE.getIdOpcion() == forma.getFuente()){
			
			String discapacidades = null;
			
			if (ofertaVO.getIdDiscapacidad() == TIPO_DISCAPACIDAD.AUDITIVA.getIdOpcion()) {
				discapacidades = "10000";
			} else if (ofertaVO.getIdDiscapacidad() == TIPO_DISCAPACIDAD.INTELECTUAL.getIdOpcion()) {
				discapacidades = "01000";
			} else if (ofertaVO.getIdDiscapacidad() == TIPO_DISCAPACIDAD.MENTAL.getIdOpcion()) {
				discapacidades = "00100";
			} else if (ofertaVO.getIdDiscapacidad() == TIPO_DISCAPACIDAD.MOTRIZ.getIdOpcion()) {
				discapacidades = "00010";
			} else if (ofertaVO.getIdDiscapacidad() == TIPO_DISCAPACIDAD.VISUAL.getIdOpcion()) {
				discapacidades = "00001";
			} else {
				discapacidades = "00000";
			}

			forma.setDiscapacidades(discapacidades);
			forma.setIdTipoDiscapacidad((int) Constantes.TIPO_DISCAPACIDAD.NINGUNA.getIdOpcion());					
						
		} else {
		
			forma.setIdTipoDiscapacidad((int) Constantes.TIPO_DISCAPACIDAD.NINGUNA.getIdOpcion());
			forma.setDiscapacidades(ofertaVO.getDiscapacidades());		
			forma.setDiscapacidadAuditiva(booleanToInt(ofertaVO.isDiscapacidadAuditiva()));
			forma.setDiscapacidadIntelectual(booleanToInt(ofertaVO.isDiscapacidadIntelectual()));
			forma.setDiscapacidadMental(booleanToInt(ofertaVO.isDiscapacidadMental()));
			forma.setDiscapacidadMotriz(booleanToInt(ofertaVO.isDiscapacidadMotriz()));
			forma.setDiscapacidadVisual(booleanToInt(ofertaVO.isDiscapacidadVisual()));
			forma.setDiscapacidadNinguna(booleanToInt(!ofertaVO.isDiscapacitado()));	
			
		}
		if(ofertaVO.isOfertaBecate()){
			if(ofertaVO.getDiscapacidades().equals(ConstantesGenerales.NINGUNA_DISCAPACIDAD)){
				forma.setDiscapacidad(ACEPTA_CANDIDATOS_CON_DISCAPACIDAD.NO.getIdOpcion());
			}else{
				forma.setDiscapacidad(ACEPTA_CANDIDATOS_CON_DISCAPACIDAD.SI.getIdOpcion());
			}
		}
	}
	
	private int booleanToInt(boolean value){
		int intValue = 0;
		if(value){
			intValue = 1;
		}
		return intValue;
	}

	private void setTelefonos(OfertaEmpleoVO ofertaVO, OfertaEmpleoForm forma) {
		List<TelefonoVO> telefonosAdicionales = new ArrayList<TelefonoVO>();
		
		for (TelefonoVO vo: ofertaVO.getTelefonos()) {
			if (vo.getPrincipal()==MULTIREGISTRO.PRINCIPAL.getIdOpcion()) {
				forma.setTipoTelefono((int)Constantes.TIPO_TELEFONO.FIJO.getIdOpcion());
				forma.setAcceso(Constantes.TIPO_TELEFONO.FIJO.getAcceso());
				forma.setClave(vo.getClave());
				forma.setTelefono(vo.getTelefono());
				forma.setExtension(vo.getExtension());

			} else {
				
				int tipoTelefonoValidado = validaTipoTelefono(vo.getIdTipoTelefono(),vo.getAcceso());
				String accesoValidado = validaAcceso(vo.getIdTipoTelefono(),vo.getAcceso());
				vo.setIdTipoTelefono(tipoTelefonoValidado);
				vo.setAcceso(accesoValidado);
				
				Pattern patron = Pattern.compile("^[+]?\\d{0,6}$");
				Matcher encaja = patron.matcher(vo.getExtension());
				if (!encaja.find()) {
					vo.setExtension("");
				}
				
				telefonosAdicionales.add(vo);
			}
		}
		forma.setTelefonosAdicionales(telefonosAdicionales);
		forma.setTotalTelefonosAdicionales(telefonosAdicionales.size());
	}

	private Integer validaTipoTelefono(Integer idTipoTelefono, String acceso) {
		if (idTipoTelefono != null && TIPO_TELEFONO.CELULAR.getIdOpcion() == idTipoTelefono.longValue() &&  TIPO_TELEFONO.FIJO.getIdOpcion() != idTipoTelefono.longValue()){
			if (TIPO_TELEFONO.CELULAR.getAcceso().equals(acceso)) {
				idTipoTelefono = (int)TIPO_TELEFONO.CELULAR.getIdOpcion();					
			} else if (TIPO_TELEFONO.FIJO.getAcceso().equals(acceso)) {
				idTipoTelefono = (int)TIPO_TELEFONO.FIJO.getIdOpcion();
			} else {
				idTipoTelefono = (int)TIPO_TELEFONO.FIJO.getIdOpcion();
			}
		} else {
			idTipoTelefono = (int)TIPO_TELEFONO.FIJO.getIdOpcion();
		}
		return idTipoTelefono;
	}

	private String validaAcceso(Integer idTipoTelefono, String acceso) {
		if (acceso!=null && TIPO_TELEFONO.CELULAR.getAcceso().equals(acceso) && !TIPO_TELEFONO.FIJO.getAcceso().equals(acceso)) {
			if (idTipoTelefono!=null) {
				if (TIPO_TELEFONO.CELULAR.getIdOpcion() == idTipoTelefono.longValue()){
					acceso = TIPO_TELEFONO.CELULAR.getAcceso();
				} else if (TIPO_TELEFONO.FIJO.getIdOpcion() == idTipoTelefono.longValue()){
					acceso = TIPO_TELEFONO.FIJO.getAcceso();
				} else {
					acceso = TIPO_TELEFONO.FIJO.getAcceso();
				}
			} else {
				acceso = TIPO_TELEFONO.FIJO.getAcceso();
			}
		} else{
			acceso = TIPO_TELEFONO.FIJO.getAcceso();
		}
		return acceso;
	}



	private void setPrestaciones(List<Long> prestaciones, OfertaEmpleoForm forma) {
		long[] idPrestaciones = new long[prestaciones.size()];
		int x= 0;
		for (Long opcion : prestaciones) {
			idPrestaciones[x] = opcion;
			x++;
		}
		forma.setIdPrestacion(idPrestaciones);
	}


	private void inicializarDatosDeIdiomasEnForm(OfertaEmpleoVO ofertaVO, OfertaEmpleoForm forma) {
		
		int contadorIdiomasAdicionales = 1;
		List<OfertaIdiomaVO> idiomasAdicionales = new ArrayList<OfertaIdiomaVO>();

		for(OfertaIdiomaVO vo: ofertaVO.getIdiomas()){

			if(vo.getPrincipal()==MULTIREGISTRO.PRINCIPAL.getIdOpcion()){
				setIdiomaPrincipal(forma, vo);	
			}else{
				idiomasAdicionales.add(vo);				
				if(contadorIdiomasAdicionales==1)
					forma.setIdiomasMultiRegistro1(setIdiomasMultiRegistro(vo.getIdIdioma(),forma.getIdiomasDependientes()));
				if(contadorIdiomasAdicionales==2)
					forma.setIdiomasMultiRegistro2(setIdiomasMultiRegistro(vo.getIdIdioma(),forma.getIdiomasDependientes()));
				if(contadorIdiomasAdicionales==3)
					forma.setIdiomasMultiRegistro3(setIdiomasMultiRegistro(vo.getIdIdioma(),forma.getIdiomasDependientes()));
				contadorIdiomasAdicionales++;
			}
		}
		forma.setIdiomasAdicionales(idiomasAdicionales);
		forma.setTotalIdiomasAdicionales(idiomasAdicionales.size());		
	}
	
	private void setIdiomaPrincipal(OfertaEmpleoForm form, OfertaIdiomaVO idioma){		
		form.setIdOfertaIdioma(Utils.toInt(idioma.getIdRegistro()));
		form.setIdIdioma(Utils.toInt(idioma.getIdIdioma()));
		form.setIdCertificacionIdioma(Utils.toInt(idioma.getIdCertificacion()));
		form.setIdDominioIdioma(Utils.toInt(idioma.getIdDominio()));		
	}	


	private List<CatalogoOpcionVO> setIdiomasMultiRegistro(long idIdioma, String[] idiomasDep) {
		List<CatalogoOpcionVO> lista = new ArrayList<CatalogoOpcionVO>();
		try {
			lista = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(Long.valueOf(idiomasDep[(int) idIdioma]));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}


	private void setHabilidadesForm(OfertaEmpleoVO ofertaVO, OfertaEmpleoForm forma) {

		List<OfertaRequisitoVO> habilidadesAdicionales = new ArrayList<OfertaRequisitoVO>();

		for(OfertaRequisitoVO vo: ofertaVO.getHabilidades()) {
			if (vo.getPrincipal()==MULTIREGISTRO.PRINCIPAL.getIdOpcion()) {
				forma.setHabilidad(vo.getDescripcion());
				forma.setIdExperienciaHabilidad((int) vo.getIdExperiencia());
			} else {
				habilidadesAdicionales.add(vo);
			}
		}
		forma.setTotalHabilidadesAdicionales(habilidadesAdicionales.size());
		forma.setHabilidadesAdicionales(habilidadesAdicionales);
	}


	private void setConocimientosForm(OfertaEmpleoVO ofertaVO, OfertaEmpleoForm forma) {
		List<OfertaRequisitoVO> conocimientosAdicionales = new ArrayList<OfertaRequisitoVO>();
		for(OfertaRequisitoVO vo: ofertaVO.getConocimientos()){

			if (vo.getPrincipal()==MULTIREGISTRO.PRINCIPAL.getIdOpcion()) {
				forma.setConocimiento(vo.getDescripcion());
				forma.setIdExperienciaConocimiento((int) vo.getIdExperiencia());
			} else {
				conocimientosAdicionales.add(vo);
			}
		}
		forma.setConocimientosAdicionales(conocimientosAdicionales);
		forma.setTotalConocimientosAdicionales(conocimientosAdicionales.size());
	}


	private void setCarrerasForm(OfertaEmpleoVO ofertaVO, OfertaEmpleoForm forma) {
		List<OfertaCarreraEspecialidadVO> carrerasAdicionales = new ArrayList<OfertaCarreraEspecialidadVO>();
		for (OfertaCarreraEspecialidadVO vo: ofertaVO.getCarreras()) {
			if (vo.getPrincipal() == MULTIREGISTRO.PRINCIPAL.getIdOpcion()) {
				forma.setIdCarreraEspecialidad((int) vo.getId());
			} else {
				carrerasAdicionales.add(vo);
			}
		}
		forma.setCarrerasAdicionales(carrerasAdicionales);
		forma.setTotalCarrerasAdicionales(carrerasAdicionales.size());
	}


	private List<CatalogoOpcionVO> setCarrerasMultiRegistro(long idNivelEstudio, String[] grados) {
		List<CatalogoOpcionVO> lista = new ArrayList<CatalogoOpcionVO>();
		try {
			lista = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(Long.valueOf(grados[(int)idNivelEstudio]));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}


	private DomicilioVO setDomicilio(String calle, String codigoPostal,
			String entreCalle, int idColoniaDomicilio, int idDomicilio,
			int idEntidadDomicilio, int idMunicipioDomicilio,
			String numeroExterior, String numeroInterior, String getyCalle) {
		
		DomicilioVO domicilioVo = new DomicilioVO();
		domicilioVo.setCalle(calle);
		domicilioVo.setCodigoPostal(codigoPostal);
		domicilioVo.setEntreCalle(entreCalle);
		domicilioVo.setIdColonia(idColoniaDomicilio);
		domicilioVo.setIdDomicilio(idDomicilio);
		domicilioVo.setIdEntidad(idEntidadDomicilio);
		domicilioVo.setIdMunicipio(idMunicipioDomicilio);
		domicilioVo.setIdTipoPropietario(TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
		domicilioVo.setNumeroExterior(numeroExterior);
		domicilioVo.setNumeroInterior(numeroInterior);
		domicilioVo.setyCalle(getyCalle);

		return domicilioVo;
	}


	private OfertaEmpleoVO getOfertaEmpleoVo(OfertaEmpleoForm forma) {
		//Se utiliza fechaDummy porque cuando se autorice la oferta, se recalcula la fecha final de vigencia
		String idOcupacion = String.valueOf(forma.getIdOcupacion());
		Calendar fechaDummy = Calendar.getInstance();
		fechaDummy.add(Calendar.DATE,30);
		OfertaEmpleoVO vo = new OfertaEmpleoVO();
		vo.setIdEmpresa(forma.getIdEmpresa());
		vo.setIdOfertaEmpleo(forma.getIdOfertaEmpleo());
		vo.setTituloOferta(forma.getTituloOferta());
		vo.setIdOcupacion(forma.getIdOcupacion());
		vo.setNumeroPlazas(forma.getNumeroPlazas());
		vo.setFunciones(forma.getFunciones());
		//VA A DESAPARECER EL CAMPO TIPO_EMPLEO A SOLICITUD DEL CLIENTE
		//vo.setIdTipoEmpleo(forma.getIdTipoEmpleo());
		vo.setIdTipoEmpleo(1);
		vo.setIdTipoContrato(forma.getIdTipoContrato());
		vo.setIdVigenciaOferta(forma.getIdVigenciaOferta());
		vo.setFechaFin(fechaDummy.getTime());
		vo.setHoraEntrada(forma.getHoraEntrada());
		vo.setHoraSalida(forma.getHoraSalida());
		vo.setRolarTurno(forma.getRolarTurnos());		
		vo.setIdCausaVacante(forma.getIdCausaOferta());
		vo.setIdEntidadUbicacion(forma.getIdEntidad());
		vo.setIdMunicipioUbicacion(forma.getIdMunicipio());
		vo.setMapaUbicacion(forma.getUrlUbicacion());
		vo.setIdNivelEstudio(forma.getIdNivelEstudio());
		vo.setCarreras(forma.getCarrerasOferta());
		vo.setIdSituacionAcademica(forma.getIdSituacionAcademica());
//		vo.setHabilidadGeneral(forma.getHabilidadGeneral());
		vo.setExperienciaAnios(forma.getAniosExperiencia());
		vo.setEdadRequisito(forma.getEdadRequisito());
		vo.setEdadMaxima(forma.getEdadMaxima());
		vo.setEdadMinima(forma.getEdadMinima());
		vo.setGenero(forma.getGenero());
		vo.setConocimientos(forma.getConocimientos());
		vo.setHabilidades(forma.getHabilidades());
		vo.setIdiomas(forma.getIdiomas());
		vo.setDisponibilidadViajar(forma.getViajar());
		vo.setDisponibilidadRadicar(forma.getRadicar());
		vo.setObservaciones(forma.getObservaciones());
		vo.setSalario(forma.getSalario());
		vo.setPrestaciones(getPrestaciones(forma.getIdPrestacion()));
		vo.setEmpresaOfrece(forma.getEmpresaOfrece());
		vo.setNombreEmpresa(forma.getNombreEmpresa());
		vo.setNombreContacto(forma.getNombreContacto());
		vo.setTelefonos(forma.getTelefonos());
		vo.setDomicilio(forma.getDomicilio());
		vo.setContactoCorreo(forma.getContactoCorreo());
		vo.setContactoTel(forma.getContactoTelefono());
		vo.setContactoDomicilio(forma.getContactoDomicilio());
		vo.setCargoContacto(forma.getCargoContacto());
		vo.setCorreoElectronicoContacto(forma.getCorreoElectronicoContacto());
		vo.setIdActividadEconomica(forma.getIdActividadEconomica());
		vo.setDiasLaborales(forma.getDiasLaborales());
		if (idOcupacion != null && idOcupacion.length() >= 2) {
			vo.setIdAreaLaboral(Utils.parseLong(idOcupacion.substring(0,2)));
		} else {
			vo.setIdAreaLaboral(Utils.parseLong(idOcupacion));
		}

		vo.setCodigo_universal_de_puesto_sfp(forma.getCodigo_universal_de_puesto_sfp());
		vo.setIdJerarquia(3);
		vo.setLimitePostulantes(100);
		vo.setCompetencias(new ArrayList<OfertaRequisitoVO>());
		vo.setFechaInicio(forma.getFechaInicio());
		vo.setFuente(forma.getFuente());
		vo.setConocimientoComputacionVO(forma.getConocimientoComputacion());
		
		vo.setIdHabilidad(forma.getIdHabilidad());
		
		vo.setIdDiscapacidad(Constantes.TIPO_DISCAPACIDAD.NINGUNA.getIdOpcion());
		if (Constantes.CATALOGO_FUENTE_QUE_REGISTRA_OFERTA.ABRIENDO_ESPACIOS.getIdOpcion() == forma.getFuente() || forma.isOfertaBecate()){
			vo.setDiscapacidades(forma.getDiscapacidades());
		} else {
			vo.setDiscapacidades("00000");			
		}	
		//Becate
		if (forma.isOfertaBecate()) {
			vo.setOfertaBecate(true);
			vo.setIdModalidad(forma.getIdModalidad());
			vo.setOfertaEmpleoBecate(setOfertaEmpleoBecate(forma));
			if (forma.getModalidadOferta() != null) {
				vo.setModalidadOferta(forma.getModalidadOferta());
			}
		}
		return vo;
	}

	private List<TelefonoVO> setTelefonos(String acceso, String clave,
			String extension, int tipoTelefono, int idTelefono,
			String telefono, int totalTelefonosAdicionales,
			HttpServletRequest request, OfertaEmpleoForm forma) {
		List<TelefonoVO> telefonos = new ArrayList<TelefonoVO>();
		List<TelefonoVO> telefonosAdicionales = new ArrayList<TelefonoVO>();
		
		int tipoTelefonoValidado = validaTipoTelefono(tipoTelefono,acceso);
		String accesoValidado = validaAcceso(tipoTelefono,acceso);
				
		TelefonoVO telefonoVo = new TelefonoVO();
		telefonoVo.setAcceso(accesoValidado);
		telefonoVo.setClave(clave);
		telefonoVo.setExtension(extension);
		telefonoVo.setIdTipoPropietario(TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
		telefonoVo.setIdTipoTelefono(tipoTelefonoValidado);
		telefonoVo.setIdTelefono(idTelefono);
		telefonoVo.setPrincipal(MULTIREGISTRO.PRINCIPAL.getIdOpcion());
		telefonoVo.setTelefono(telefono);
		telefonos.add(telefonoVo);

		for (int x = 1; x<=totalTelefonosAdicionales; x++) {

			telefonoVo = new TelefonoVO();
			Integer tipoTelefonoAdicional = Integer.parseInt(request.getParameter("idTipoTelefonoAdicional_"+x));
			String accesoAdicional = request.getParameter("accesoTelefonoAdicional_"+x);			
			String claveAdicional = request.getParameter("claveTelefonoAdicional_"+x);
			String telefonoAdicional = request.getParameter("telefonoAdicional_"+x);
			if ((claveAdicional != null && !claveAdicional.equals("")) || (telefonoAdicional != null && !telefonoAdicional.equals(""))){
							
				int tipoTelefonoAdicionalValidado = validaTipoTelefono(tipoTelefonoAdicional,accesoAdicional);
				String accesoAdicionalValidado = validaAcceso(tipoTelefonoAdicional,accesoAdicional);				
				System.out.println("tel: " + x + " " + tipoTelefonoAdicionalValidado);
				telefonoVo.setAcceso(accesoAdicionalValidado);
				telefonoVo.setClave(request.getParameter("claveTelefonoAdicional_"+x));
				telefonoVo.setExtension(request.getParameter("extensionTelefonoAdicional_"+x));
				telefonoVo.setIdTipoPropietario(TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
				telefonoVo.setIdTipoTelefono(tipoTelefonoAdicionalValidado);
				telefonoVo.setIdTelefono(Long.parseLong(request.getParameter("idTelefonoAdicional_"+x)));
				telefonoVo.setPrincipal(MULTIREGISTRO.ADICIONAL.getIdOpcion());
				telefonoVo.setTelefono(request.getParameter("telefonoAdicional_"+x));
								
				telefonos.add(telefonoVo);
				telefonosAdicionales.add(telefonoVo);
			}
		}
		forma.setTelefonosAdicionales(telefonosAdicionales);
		return telefonos;

	}

	private List<OfertaRequisitoVO> setListaConocimientoHabilidades(
			int idOfertaRequisitoConocimiento, String conocimiento,
			int idExperienciaConocimiento, long idTipoRequisito,
			int totalConocimientosAdicionales, HttpServletRequest request, int totalHabilidadesAdicionales, OfertaEmpleoForm forma) {

		List<OfertaRequisitoVO> conocimientosHabilidades = new ArrayList<OfertaRequisitoVO>();
		List<OfertaRequisitoVO> conocimientosAdicionales = new ArrayList<OfertaRequisitoVO>();
		List<OfertaRequisitoVO> habilidadesAdicionales = new ArrayList<OfertaRequisitoVO>();
		OfertaRequisitoVO ofertaRequisitoVo = new OfertaRequisitoVO();
		
		if (conocimiento != null && !conocimiento.equals("")){
			ofertaRequisitoVo.setIdOfertaRequisito(idOfertaRequisitoConocimiento);
			ofertaRequisitoVo.setDescripcion(conocimiento);
			ofertaRequisitoVo.setIdExperiencia(idExperienciaConocimiento);
			ofertaRequisitoVo.setPrincipal(MULTIREGISTRO.PRINCIPAL.getIdOpcion());
			ofertaRequisitoVo.setIdTipoRequisito(idTipoRequisito);
			conocimientosHabilidades.add(ofertaRequisitoVo);
		}
		
		if (TIPO_REQUISITO.CONOCIMIENTO.getIdTipoRequisito() == idTipoRequisito) {
			for (int x = 1; x <= totalConocimientosAdicionales; x++) {
				String conocimientoAdicional = request.getParameter("conocimientoAdicional_"+x);
				ofertaRequisitoVo = new OfertaRequisitoVO();
				if (conocimientoAdicional != null && !conocimientoAdicional.equals("")) {
					ofertaRequisitoVo.setIdOfertaRequisito(Integer.parseInt(request.getParameter("idConocimientoAdicional_"+x)));
					ofertaRequisitoVo.setDescripcion(request.getParameter("conocimientoAdicional_"+x));
					ofertaRequisitoVo.setIdExperiencia(Integer.parseInt(request.getParameter("idConocimientoExperiencia_"+x)));
					ofertaRequisitoVo.setPrincipal(MULTIREGISTRO.ADICIONAL.getIdOpcion());
					ofertaRequisitoVo.setIdTipoRequisito(idTipoRequisito);
					conocimientosHabilidades.add(ofertaRequisitoVo);
					conocimientosAdicionales.add(ofertaRequisitoVo);
				}
			}

			forma.setConocimientosAdicionales(conocimientosAdicionales);
		}

		if (TIPO_REQUISITO.HABILIDAD.getIdTipoRequisito() == idTipoRequisito) {

			for (int x = 1; x<=totalHabilidadesAdicionales; x++) {
				ofertaRequisitoVo = new OfertaRequisitoVO();
				String habilidadAdicional = request.getParameter("habilidadAdicional_"+x);
				
				if (habilidadAdicional != null && !habilidadAdicional.equals("")) {
					ofertaRequisitoVo.setIdOfertaRequisito(Integer.parseInt(request.getParameter("idHabilidadAdicional_"+x)));
					ofertaRequisitoVo.setDescripcion(request.getParameter("habilidadAdicional_"+x));
					ofertaRequisitoVo.setIdExperiencia(Integer.parseInt(request.getParameter("idHabilidadExperiencia_"+x)));
					ofertaRequisitoVo.setPrincipal(MULTIREGISTRO.ADICIONAL.getIdOpcion());
					ofertaRequisitoVo.setIdTipoRequisito(idTipoRequisito);
					conocimientosHabilidades.add(ofertaRequisitoVo);
					habilidadesAdicionales.add(ofertaRequisitoVo);
				}
			}
			forma.setHabilidadesAdicionales(habilidadesAdicionales);
		}
		return conocimientosHabilidades;
	}


	private List<OfertaCarreraEspecialidadVO> setListaCareras(int idOfertaCarrera, int idCarreraEspecialidad, int totalCarrerasAdicionales, HttpServletRequest request, OfertaEmpleoForm forma) {
		List<OfertaCarreraEspecialidadVO> carreras = new ArrayList<OfertaCarreraEspecialidadVO>();
		List<OfertaCarreraEspecialidadVO> carrerasAdicionales = new ArrayList<OfertaCarreraEspecialidadVO>();
		OfertaCarreraEspecialidadVO ofertaCarreraVo = new OfertaCarreraEspecialidadVO();
		ofertaCarreraVo.setIdRegistro(idOfertaCarrera);
		ofertaCarreraVo.setId(idCarreraEspecialidad);
		ofertaCarreraVo.setPrincipal(MULTIREGISTRO.PRINCIPAL.getIdOpcion());
		carreras.add(ofertaCarreraVo);

		for (int x = 1; x <= totalCarrerasAdicionales; x++){
			ofertaCarreraVo = new OfertaCarreraEspecialidadVO();
			ofertaCarreraVo.setIdRegistro(Integer.parseInt(request.getParameter("idOfertaCarreraAdicional_"+x)));
			ofertaCarreraVo.setId(Integer.parseInt(request.getParameter("idCarreraAdicional_"+x)));
			ofertaCarreraVo.setPrincipal(MULTIREGISTRO.ADICIONAL.getIdOpcion());
			carreras.add(ofertaCarreraVo);
			carrerasAdicionales.add(ofertaCarreraVo);
		}
		forma.setCarrerasAdicionales(carrerasAdicionales);
		return carreras;
	}

	public ActionForward telefonoAdicional(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		OfertaEmpleoForm forma = (OfertaEmpleoForm) form;
		Integer tipoTelefonoAdicional = Integer.parseInt(request.getParameter("idTipoTelefonoAdd"));
		String accesoAdicional = request.getParameter("accesoAdd");			
		int tipoTelefonoAdicionalValidado = validaTipoTelefono(tipoTelefonoAdicional,accesoAdicional);
		String accesoAdicionalValidado = validaAcceso(tipoTelefonoAdicional,accesoAdicional);				
		List<TelefonoVO> telefonosAdicionales = forma.getTelefonosAdicionales();
		TelefonoVO telefonoVO = new TelefonoVO();
		telefonoVO.setAcceso(accesoAdicionalValidado);
		telefonoVO.setClave(request.getParameter("claveAdd"));
		telefonoVO.setExtension(request.getParameter("extensionAdd"));
		telefonoVO.setIdTipoPropietario(TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
		telefonoVO.setIdTipoTelefono(tipoTelefonoAdicionalValidado);
		telefonoVO.setPrincipal(MULTIREGISTRO.ADICIONAL.getIdOpcion());
		telefonoVO.setTelefono(request.getParameter("telefonoAdd"));
		boolean existe =false;

		if (telefonosAdicionales == null) {
			telefonosAdicionales = new ArrayList<TelefonoVO>();
		}

		for (TelefonoVO vo: telefonosAdicionales) {
			if(vo.getAcceso().equals(accesoAdicionalValidado)
					&&vo.getClave().equals(request.getParameter("claveAdd"))
					&&vo.getTelefono().equals(request.getParameter("telefonoAdd"))
					&&vo.getExtension().equals(request.getParameter("extensionAdd"))){
				existe=true;
			}
		}

		if (!existe) {
			forma.getTelefonosAdicionales().add(telefonoVO);
		}
		forma.setTotalTelefonosAdicionales(forma.getTelefonosAdicionales().size());

		return mapping.findForward(FORWARD_TELEFONOS_ADICIONALES);

	}

	public ActionForward eliminarTelefonoAdicional(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String accesoEliminar=request.getParameter("accesoEliminar");
		String claveEliminar=request.getParameter("claveEliminar");
		String telefonoEliminar=request.getParameter("telefonoEliminar");
		String extensionEliminar=request.getParameter("extensionEliminar");

		OfertaEmpleoForm forma = (OfertaEmpleoForm) form;

		List<TelefonoVO> telefonosAdicionales = forma.getTelefonosAdicionales();
		int x = 0;
		for (TelefonoVO vo: telefonosAdicionales) {

			if (vo.getAcceso().equals(accesoEliminar)
					&& vo.getClave().equals(claveEliminar)
					&& vo.getTelefono().equals(telefonoEliminar)
					&& vo.getExtension().equals(extensionEliminar)) {
				telefonosAdicionales.remove(x);
				break;
			}
			x++;
		}
		forma.setTelefonosAdicionales(telefonosAdicionales);
		forma.setTotalTelefonosAdicionales(forma.getTelefonosAdicionales().size());

		return mapping.findForward(FORWARD_TELEFONOS_ADICIONALES);

	}



	public ActionForward carreraAdicional(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		OfertaEmpleoForm forma = (OfertaEmpleoForm) form;
		
		if (request.getParameter("idCarreraEspecialidadAdd") != null && request.getParameter("idNivelEstudio") != null) {
			long idCarreraEspecialidad=Long.parseLong(request.getParameter("idCarreraEspecialidadAdd"));
			
			long idNivelEstudio=Long.parseLong(request.getParameter("idNivelEstudio"));			
			
			forma.setCarrerasMultiRegistro(setCarrerasMultiRegistro(idNivelEstudio,forma.getGradosDependientes()));
			
			List<OfertaCarreraEspecialidadVO> carrerasAdicionales = forma.getCarrerasAdicionales();
			
			OfertaCarreraEspecialidadVO ofertaCarreraVo = new OfertaCarreraEspecialidadVO();
			//ofertaCarreraVo.setIdRegistro(forma.getIdOfertaCarrera());
			ofertaCarreraVo.setId(Long.parseLong(request.getParameter("idCarreraEspecialidadAdd")));
			ofertaCarreraVo.setPrincipal(MULTIREGISTRO.ADICIONAL.getIdOpcion());
		
			boolean existe = false;

			if (carrerasAdicionales == null) {
				carrerasAdicionales = new ArrayList<OfertaCarreraEspecialidadVO>();
			}

			for (OfertaCarreraEspecialidadVO vo: carrerasAdicionales) {
				if (vo.getId() == idCarreraEspecialidad) {
					existe=true;
					break;
				}
			}
			
			if (!existe) {
				forma.getCarrerasAdicionales().add(ofertaCarreraVo);
			}
			forma.setTotalCarrerasAdicionales(forma.getCarrerasAdicionales().size());	
			
		}
		
		return mapping.findForward(FORWARD_CARRERAS_ADICIONALES);
	}

	public ActionForward eliminarCarreraAdicional(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		long idCarreraEspecialidad=Long.parseLong(request.getParameter("idCarreraEliminar"));
		OfertaEmpleoForm forma = (OfertaEmpleoForm) form;

		List<OfertaCarreraEspecialidadVO> carrerasAdicionales = forma.getCarrerasAdicionales();
		int x = 0;
		for(OfertaCarreraEspecialidadVO vo: carrerasAdicionales){

			if (vo.getId() == idCarreraEspecialidad) {
				carrerasAdicionales.remove(x);
				break;
			}
			x++;
		}
		forma.setCarrerasAdicionales(carrerasAdicionales);
		forma.setTotalCarrerasAdicionales(forma.getCarrerasAdicionales().size());

		return mapping.findForward(FORWARD_CARRERAS_ADICIONALES);

	}


	public ActionForward conocimientoAdicional(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String  conocimientoAdd=request.getParameter("conocimientoAdd");
		long idExperienciaConocimientoAdd=Long.parseLong(request.getParameter("idExperienciaConocimientoAdd"));
		OfertaEmpleoForm forma = (OfertaEmpleoForm) form;

		List<OfertaRequisitoVO> conocimientosAdicionales = forma.getConocimientosAdicionales();
		OfertaRequisitoVO ofertaRequisitoVo = new OfertaRequisitoVO();
		//ofertaRequisitoVo.setIdOfertaRequisito(forma.getIdOfertaRequisitoConocimiento());
		ofertaRequisitoVo.setDescripcion(conocimientoAdd);
		ofertaRequisitoVo.setIdExperiencia(idExperienciaConocimientoAdd);
		ofertaRequisitoVo.setPrincipal(MULTIREGISTRO.ADICIONAL.getIdOpcion());
		ofertaRequisitoVo.setIdTipoRequisito(TIPO_REQUISITO.CONOCIMIENTO.getIdTipoRequisito());

		boolean existe = false;

		if (conocimientosAdicionales == null ){
			conocimientosAdicionales = new ArrayList<OfertaRequisitoVO>();
		}

		for (OfertaRequisitoVO vo: conocimientosAdicionales) {
			if(vo.getDescripcion().equals(conocimientoAdd)){
				existe = true;
				break;
			}
		}

		if (!existe) {
			forma.getConocimientosAdicionales().add(ofertaRequisitoVo);
		}
		forma.setTotalConocimientosAdicionales(forma.getConocimientosAdicionales().size());

		return mapping.findForward(FORWARD_CONOCIMIENTOS_ADICIONALES);

	}

	public ActionForward eliminarConocimientoAdicional(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String  conocimientoEliminar=request.getParameter("conocimientoEliminar");
		OfertaEmpleoForm forma = (OfertaEmpleoForm) form;

		List<OfertaRequisitoVO> conocimientosAdicionales = forma.getConocimientosAdicionales();
		int x = 0;
		for (OfertaRequisitoVO vo: conocimientosAdicionales) {
			if (vo.getDescripcion().equals(conocimientoEliminar)) {
				conocimientosAdicionales.remove(x);
				break;
			}
			x++;
		}
		forma.setConocimientosAdicionales(conocimientosAdicionales);
		forma.setTotalConocimientosAdicionales(forma.getConocimientosAdicionales().size());

		return mapping.findForward(FORWARD_CONOCIMIENTOS_ADICIONALES);

	}
	
	private List<OfertaIdiomaVO> obtenerListaIdiomasAdicionales(List<OfertaIdiomaVO> listaIdiomas){
		
		List<OfertaIdiomaVO> idiomasAdicionales = new ArrayList<OfertaIdiomaVO>();
		for (OfertaIdiomaVO idioma : listaIdiomas) {
			if (idioma.getPrincipal() == MULTIREGISTRO.ADICIONAL.getIdOpcion()) {
				idiomasAdicionales.add(idioma);
			}
		}			
		return idiomasAdicionales;
	}
	
	private List<OfertaIdiomaVO> obtenerListaIdiomas(int idOfertaIdiomaPrincipal, int idIdiomaPrincipal,
			int idDominioIdiomaPrincipal, int idCertificacionIdiomaPrincipal,
			int totalIdiomasAdicionales, HttpServletRequest request, OfertaEmpleoForm forma) {
		
		//FIXME: Este metodo obtiene la lista de idiomas pero tambien establece el idioma principal		
		List<OfertaIdiomaVO> idiomas = new ArrayList<OfertaIdiomaVO>();
		OfertaIdiomaVO ofertaIdiomaVo = new OfertaIdiomaVO();
		ofertaIdiomaVo.setIdRegistro(idOfertaIdiomaPrincipal);
		ofertaIdiomaVo.setIdIdioma(idIdiomaPrincipal);
		ofertaIdiomaVo.setIdDominio(idDominioIdiomaPrincipal);
		ofertaIdiomaVo.setIdCertificacion(idCertificacionIdiomaPrincipal);
		ofertaIdiomaVo.setPrincipal(MULTIREGISTRO.PRINCIPAL.getIdOpcion());
		setIdiomaPrincipal(forma, ofertaIdiomaVo);
		idiomas.add(ofertaIdiomaVo);
		
		for (int x = 1;x<=totalIdiomasAdicionales;x++) {
			String idDominioIdiomaAdd=request.getParameter("idDominioIdiomaAdd_"+x);
			long idDominio = 0;
			if (idDominioIdiomaAdd!=null&&!idDominioIdiomaAdd.equals("")) {
				idDominio = Long.parseLong(idDominioIdiomaAdd);
			}
			ofertaIdiomaVo = new OfertaIdiomaVO();
			ofertaIdiomaVo.setIdRegistro(Integer.parseInt(request.getParameter("idOfertaIdiomaAdicional_"+x)));
			ofertaIdiomaVo.setIdIdioma(Integer.parseInt(request.getParameter("idIdiomaAdicional_"+x)));
			ofertaIdiomaVo.setIdDominio(idDominio);
			ofertaIdiomaVo.setIdCertificacion(Integer.parseInt(request.getParameter("idCertificacionIdiomaAdd_"+x)));
			ofertaIdiomaVo.setPrincipal(MULTIREGISTRO.ADICIONAL.getIdOpcion());
			idiomas.add(ofertaIdiomaVo);
		}		
		
		return idiomas;
	}
	

	public ActionForward idiomaAdicional(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		long idIdiomaAdd=Utils.parseLong(request.getParameter("idIdiomaAdd"));
		long idCertificacionIdiomaAdd=Utils.parseLong(request.getParameter("idCertificacionIdiomaAdd"));
		String idDominioIdiomaAdd=request.getParameter("idDominioIdiomaAdd");		
		long idDominio = Utils.parseLong(idDominioIdiomaAdd);
		OfertaEmpleoForm forma = (OfertaEmpleoForm) form;

		if (idIdiomaAdd > 0 && idDominio > 0) {

			boolean existe = existeIdiomaEnListado(forma.getIdiomasAdicionales(), idIdiomaAdd, idCertificacionIdiomaAdd);

			if (!existe) {
				if (forma.getIdiomasAdicionales() == null || forma.getIdiomasAdicionales().isEmpty()) {
					forma.setIdiomasAdicionales(new ArrayList<OfertaIdiomaVO>());
				}							
				if (idDominioIdiomaAdd != null && !idDominioIdiomaAdd.equals("")){
					idDominio = Long.parseLong(idDominioIdiomaAdd);			
				}			
				agregarCatalogosIdiomasDependientes(forma, idIdiomaAdd);
				OfertaIdiomaVO idiomaNuevo = setIdiomaAdicional(idIdiomaAdd, idCertificacionIdiomaAdd , idDominio);			
				forma.getIdiomasAdicionales().add(idiomaNuevo);			
			}						
		}
		forma.setTotalIdiomasAdicionales(forma.getIdiomasAdicionales().size());

		return mapping.findForward(FORWARD_IDIOMAS_ADICIONALES);
	}
	
	private boolean existeIdiomaEnListado(List<OfertaIdiomaVO> listado, long idIdioma, long idCertificacion){
		boolean existe = false;
		if (listado != null && !listado.isEmpty()) {
			for (OfertaIdiomaVO vo: listado) {
				if ((vo.getIdIdioma() == idIdioma) && (vo.getIdCertificacion() == idCertificacion)){
					existe = true;
					break;
				}
			}
		}
		return existe;
	}		
	
	private void agregarCatalogosIdiomasDependientes(OfertaEmpleoForm forma, long idIdioma){
		int totalIdiomas = forma.getIdiomasAdicionales().size()+1;
		if(totalIdiomas == 1) {
			forma.setIdiomasMultiRegistro1(setIdiomasMultiRegistro(idIdioma,forma.getIdiomasDependientes()));
		}
		if(totalIdiomas == 2) {
			forma.setIdiomasMultiRegistro2(setIdiomasMultiRegistro(idIdioma,forma.getIdiomasDependientes()));
		}
		if(totalIdiomas == 3) {
			forma.setIdiomasMultiRegistro3(setIdiomasMultiRegistro(idIdioma,forma.getIdiomasDependientes()));
		}
	}
	
	private OfertaIdiomaVO setIdiomaAdicional(long idIdioma, long idCertificacion, long idDominio){
		OfertaIdiomaVO idiomaVo = new OfertaIdiomaVO();
		idiomaVo.setIdIdioma(idIdioma);
		idiomaVo.setIdDominio(idDominio);
		idiomaVo.setIdCertificacion(idCertificacion);
		idiomaVo.setPrincipal(MULTIREGISTRO.ADICIONAL.getIdOpcion());	
		return idiomaVo;
	}	

	public ActionForward idiomaCertificacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		OfertaEmpleoForm forma = (OfertaEmpleoForm) form;
		String idioma = request.getParameter("vIdioma");
		if (idioma != null && !idioma.equals("")) {
			long idIdiomaAdd=Long.parseLong(request.getParameter("vIdioma"));
			long idMultiRegistro=Long.parseLong(request.getParameter("idMultiRegistro"));
	
			if (idMultiRegistro == 1) {
				forma.getIdiomasAdicionales().get(0).setIdIdioma(idIdiomaAdd);
				forma.setIdiomasMultiRegistro1(setIdiomasMultiRegistro(idIdiomaAdd,forma.getIdiomasDependientes()));
			}
	
			if (idMultiRegistro == 2) {
				forma.getIdiomasAdicionales().get(1).setIdIdioma(idIdiomaAdd);
				forma.setIdiomasMultiRegistro1(setIdiomasMultiRegistro(idIdiomaAdd,forma.getIdiomasDependientes()));
			}
	
			if (idMultiRegistro == 3) {
				forma.getIdiomasAdicionales().get(2).setIdIdioma(idIdiomaAdd);
				forma.setIdiomasMultiRegistro1(setIdiomasMultiRegistro(idIdiomaAdd,forma.getIdiomasDependientes()));
			}
		}
		return mapping.findForward(FORWARD_IDIOMAS_ADICIONALES);
	}
	
	/**
	public ActionForward idiomaDominio(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		OfertaEmpleoForm forma = (OfertaEmpleoForm) form;
		String idioma = request.getParameter("vIdCertificacion");
		
		if(idioma!=null && !idioma.equals("")){
			long idCertifiacionAdd=Long.parseLong(request.getParameter("vIdCertificacion"));
			long idMultiRegistro=Long.parseLong(request.getParameter("idMultiRegistro"));
	
			if(idMultiRegistro==1){
				forma.getIdiomasAdicionales().get(0).setIdCertificacion(idCertifiacionAdd);			
			}
			if(idMultiRegistro==2){
				forma.getIdiomasAdicionales().get(1).setIdCertificacion(idCertifiacionAdd);				
			}	
			if(idMultiRegistro==3){
				forma.getIdiomasAdicionales().get(2).setIdCertificacion(idCertifiacionAdd);				
			}
			ajustarDominioIdioma(forma, idCertifiacionAdd, idMultiRegistro);
		}
		return mapping.findForward(FORWARD_IDIOMAS_ADICIONALES);
	}**/
	
	public ActionForward idiomaDominio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		OfertaEmpleoForm forma = (OfertaEmpleoForm) form;
		String idioma = request.getParameter("vIdioma");
		if (idioma != null && !idioma.equals("")) {
			long idiomal = Long.valueOf(idioma);
			long idCertifiacionAdd=0;
			long idMultiRegistro=Long.parseLong(request.getParameter("idMultiRegistro"));
			long idDominio = 0L;
			String dominio = request.getParameter("idDominio");
			if(dominio != null && !dominio.equals("")){
				idDominio = Long.parseLong(dominio);
			}
			if (idMultiRegistro == 1) {
				forma.getIdiomasAdicionales().get(0).setIdIdioma(idiomal);
				forma.getIdiomasAdicionales().get(0).setIdCertificacion(idCertifiacionAdd);
				forma.getIdiomasAdicionales().get(0).setIdDominio(idDominio);
				forma.setIdiomasMultiRegistro1(setIdiomasMultiRegistro(idiomal,forma.getIdiomasDependientes()));
			}
			if (idMultiRegistro == 2) {
				forma.getIdiomasAdicionales().get(1).setIdIdioma(idiomal);
				forma.getIdiomasAdicionales().get(1).setIdCertificacion(0);
				forma.getIdiomasAdicionales().get(1).setIdDominio(idDominio);
				forma.setIdiomasMultiRegistro2(setIdiomasMultiRegistro(idiomal,forma.getIdiomasDependientes()));
			}
			if (idMultiRegistro == 3) {
				forma.getIdiomasAdicionales().get(2).setIdIdioma(idiomal);
				forma.getIdiomasAdicionales().get(2).setIdCertificacion(0);
				forma.getIdiomasAdicionales().get(2).setIdDominio(idDominio);
				forma.setIdiomasMultiRegistro3(setIdiomasMultiRegistro(idiomal,forma.getIdiomasDependientes()));
			}	
		}
		return mapping.findForward(FORWARD_IDIOMAS_ADICIONALES);
	}
	
	@SuppressWarnings("unused")
	private void ajustarDominioIdioma(OfertaEmpleoForm forma, long idCertificacion, long idMultiRegistro){
		if(idCertificacion !=OPCION_NINGUNA_CERTIFICACION && idCertificacion!=OPCION_OTRA_CERTIFICACION){
			if (idMultiRegistro == 1) {
				forma.getIdiomasAdicionales().get(0).setIdDominio(Constantes.DOMINIO.AVANZADO.getIdOpcion());
			}
			if (idMultiRegistro == 2) {
				forma.getIdiomasAdicionales().get(1).setIdDominio(Constantes.DOMINIO.AVANZADO.getIdOpcion());
			}
			if (idMultiRegistro == 3) {
				forma.getIdiomasAdicionales().get(2).setIdDominio(Constantes.DOMINIO.AVANZADO.getIdOpcion());
			}				
		} 		
	}	

	public ActionForward eliminarIdiomaAdicional(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		long idIdiomaAdd=Long.parseLong(request.getParameter("idIdiomaEliminar"));
		long idCertificacionIdiomaAdd = Long.parseLong(request.getParameter("idCertificacionIdiomaEliminar"));
		OfertaEmpleoForm forma = (OfertaEmpleoForm) form;
		List<OfertaIdiomaVO> idiomasAdicionales = forma.getIdiomasAdicionales();
		int indiceIdioma = 0;
		for(OfertaIdiomaVO vo: idiomasAdicionales){
			if ((vo.getIdIdioma() == idIdiomaAdd) && (vo.getIdCertificacion() == idCertificacionIdiomaAdd)) {
				idiomasAdicionales.remove(indiceIdioma);
				break;
			}
			indiceIdioma++;
		}

		for (int i=0; i < idiomasAdicionales.size(); i++) {
			if (i == 0) {
				forma.setIdiomasMultiRegistro1(setIdiomasMultiRegistro(idiomasAdicionales.get(i).getIdIdioma(),forma.getIdiomasDependientes()));
			}
			if (i == 1) {
				forma.setIdiomasMultiRegistro2(setIdiomasMultiRegistro(idiomasAdicionales.get(i).getIdIdioma(),forma.getIdiomasDependientes()));
			}
			if( i == 2) {
				forma.setIdiomasMultiRegistro3(setIdiomasMultiRegistro(idiomasAdicionales.get(i).getIdIdioma(),forma.getIdiomasDependientes()));
			}
		}
		forma.setIdiomasAdicionales(idiomasAdicionales);
		forma.setTotalIdiomasAdicionales(forma.getIdiomasAdicionales().size());
		return mapping.findForward(FORWARD_IDIOMAS_ADICIONALES);
	}

	public ActionForward habilidadAdicional(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String  habilidadAdd=request.getParameter("habilidadAdd");
		long idExperienciaHabilidadAdd=Long.parseLong(request.getParameter("idExperienciaHabilidadAdd"));
		OfertaEmpleoForm forma = (OfertaEmpleoForm) form;

		List<OfertaRequisitoVO> habilidadesAdicionales = forma.getHabilidadesAdicionales();
		OfertaRequisitoVO ofertaRequisitoVo = new OfertaRequisitoVO();
		//ofertaRequisitoVo.setIdOfertaRequisito(forma.getIdOfertaRequisitoConocimiento());
		ofertaRequisitoVo.setDescripcion(habilidadAdd);
		ofertaRequisitoVo.setIdExperiencia(idExperienciaHabilidadAdd);
		ofertaRequisitoVo.setPrincipal(MULTIREGISTRO.ADICIONAL.getIdOpcion());
		ofertaRequisitoVo.setIdTipoRequisito(TIPO_REQUISITO.HABILIDAD.getIdTipoRequisito());

		boolean existe = false;

		if (habilidadesAdicionales == null) {
			habilidadesAdicionales = new ArrayList<OfertaRequisitoVO>();
		}

		for(OfertaRequisitoVO vo: habilidadesAdicionales){
			if(vo.getDescripcion().equals(habilidadAdd)){
				existe=true;
				break;
			}
		}

		if (!existe) {
			forma.getHabilidadesAdicionales().add(ofertaRequisitoVo);
		}
		forma.setTotalHabilidadesAdicionales(forma.getHabilidadesAdicionales().size());

		return mapping.findForward(FORWARD_HABILIDADES_ADICIONALES);

	}

	public ActionForward eliminarHabilidadAdicional(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String  habilidadEliminar=request.getParameter("habilidadEliminar");
		OfertaEmpleoForm forma = (OfertaEmpleoForm) form;

		List<OfertaRequisitoVO> habilidadesAdicionales = forma.getHabilidadesAdicionales();
		int x = 0;
		for (OfertaRequisitoVO vo: habilidadesAdicionales) {
			if(vo.getDescripcion().equals(habilidadEliminar)){
				habilidadesAdicionales.remove(x);
				break;
			}
			x++;
		}
		forma.setHabilidadesAdicionales(habilidadesAdicionales);
		forma.setTotalHabilidadesAdicionales(forma.getHabilidadesAdicionales().size());

		return mapping.findForward(FORWARD_HABILIDADES_ADICIONALES);

	}

	private List<Long> getPrestaciones(long[] idPrestacion) {
		List<Long> prestaciones = new ArrayList<Long>();

		if (idPrestacion != null){
			for (int x = 0; x < idPrestacion.length; x++) {
				prestaciones.add(idPrestacion[x]);
			}
		}		
		return prestaciones;
	}

	public ActionForward ocupaciones(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		try{
			String catalogo = request.getParameter("search");
			CatalogoOpcionDelegate services = CatalogoOpcionDelegateImpl.getInstance();
			String opciones = services.obtenerCatalogoDinamico(CATALOGO_OPCION_OCUPACION, catalogo);
			redirectJsonResponse(response, opciones);
		} catch(Exception e){logger.error(e); e.printStackTrace();}

		return null;

	}
	
	public ActionForward searchTypeProfile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		StringBuilder options = new StringBuilder(); 
		try {
			long idOcupacion = Utils.parseLong(request.getParameter("idOcupacion"));
			OfertasPorPerfilBusDelegate services = OfertasPorPerfilBusDelegateImpl.getInstance();
			List<PerfilTipoVO> typeProfileList = services.perfilTipoList(idOcupacion);
			if (!typeProfileList.isEmpty()) options.append("<li class='headerPerfil'><span>Ocupación</span><span>Detalle</span></li>");
			for (PerfilTipoVO profile : typeProfileList) {
				StringBuilder showinf = new StringBuilder();
				showinf.append("</span><span><img").append(" src='images/detalleperfil.png'/><span>");
				options.append("<li class='rowPerfil' onclick=\"setTypeProfile(" + profile.getIdPerfil() + ");\"><span>" + profile.getDescripcion()  + showinf.toString() + "</li>");
			}
			redirectJsonResponse(response, options.toString());
		} catch(Exception e){logger.error(e); e.printStackTrace();}
		return null;
	}
	
	public ActionForward detailTypeProfile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		OfertaEmpleoForm forma = (OfertaEmpleoForm) form;
		try {
			if (Utils.parseLong(request.getParameter("idTypeProfile")) > 0) {
				int index = 0;
				long idTypeProfile = Utils.parseLong(request.getParameter("idTypeProfile"));
				OfertasPorPerfilBusDelegate services = OfertasPorPerfilBusDelegateImpl.getInstance();
				PerfilTipoVO profile = services.find(idTypeProfile);
				if (null != profile) {
					forma.setFunciones(profile.getFunciones());
					forma.setConocimientoCompOtros(profile.getEquipos());
					forma.setEquipoTecnologia(profile.getEquipos());
					List<OfertaRequisitoVO> conocimientos = new ArrayList<OfertaRequisitoVO>();
					for (PerfilConocimientoVO pc : profile.getPerfilConocimientos()) {
						if (index > 0) {
							OfertaRequisitoVO vo = new OfertaRequisitoVO();
							vo.setDescripcion(pc.getConocimiento());
							vo.setIdExperiencia(pc.getIdExperiencia());
							conocimientos.add(vo);
						}else {
							forma.setConocimiento(pc.getConocimiento());
							if (null != pc.getIdExperiencia())
								forma.setIdExperienciaConocimiento(pc.getIdExperiencia().intValue());
						}
						index ++;
					}
					if (!profile.getPerfilHabilidades().isEmpty()) {
						int i= 0;
						long [] habilidades = new long[profile.getPerfilHabilidades().size()];
						for (PerfilHabilidadVO vo : profile.getPerfilHabilidades()) {
							habilidades[i] = vo.getId().getIdHabilidad();
							i++;
						}
						forma.setIdHabilidad(habilidades);
					}
					forma.setConocimientos(conocimientos);
					forma.setIdperfilTipo(idTypeProfile);
					forma.setIdOcupacion(Utils.parseInt(request.getParameter("idOcupacion")));
					if (null != request.getParameter("idOcupacion"))
						forma.setIdOcupacion(Utils.parseInt(request.getParameter("idOcupacion")));
					forma.setTituloOferta(null != request.getParameter("tituloOferta")?request.getParameter("tituloOferta"):"");
					forma.setOcupacion(null != request.getParameter("ocupacionDeseada")?request.getParameter("ocupacionDeseada"):"");
				}
				List<CatalogoOpcionVO> opcHabilidades = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_CANDIDATO_HABILIDAD, true);
				request.getSession().setAttribute("opcHabilidades", opcHabilidades);
			}
		}catch(Exception e){logger.error(e); e.printStackTrace();}
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_DETALLE_PERFIL_TIPO).getPath());
		return mapping.findForward(FORWARD_TEMPLATE_FORM_EMPTY);
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	private String setSkills2Text(HttpServletRequest request, List<PerfilHabilidadVO> skillList) {
		StringBuilder lbl = new StringBuilder();
		List<CatalogoOpcionVO> options =  (List<CatalogoOpcionVO>)request.getSession().getAttribute("opcHabilidades");
		if (null != skillList && !skillList.isEmpty()) {
			for (PerfilHabilidadVO vo : skillList) {
				for (CatalogoOpcionVO option : options) {
					if (vo.getId().getIdHabilidad() == option.getIdCatalogoOpcion()) {
						lbl.append(", " + option.getOpcion());
						break;
					}
				}
			}
		}
		if (lbl.length() > 2) {
			lbl.delete(0, 2);
			lbl.insert(0, "<strong>Conocimientos: </strong>");
		}
		return lbl.toString(); 
	}

	public ActionForward tipoEmpleo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		try {
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_TIPO_EMPLEO);
			redirectJsonCatalogo(opciones, response);
		} catch(Exception e){logger.error(e); e.printStackTrace();}
		return null;
	}

	public ActionForward tipoContrato(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		try {
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_TIPO_CONTRATO);
			redirectJsonCatalogo(opciones, response);
		} catch(Exception e){logger.error(e); e.printStackTrace();}
		return null;
	}

	public ActionForward dias(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<CatalogoOpcionVO> opciones = new ArrayList<CatalogoOpcionVO>();

			for (int i=1; i<=31; i++) {
				CatalogoOpcionVO opcion = new CatalogoOpcionVO();
				opcion.setIdCatalogoOpcion(i);
				opcion.setOpcion(String.valueOf(i));
				opciones.add(opcion);
			}

			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {e.printStackTrace(); logger.error(e);}

		return null;
	}

	public ActionForward meses(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<CatalogoOpcionVO> opciones = new ArrayList<CatalogoOpcionVO>();

			for (MESES mes : MESES.values()){
				CatalogoOpcionVO opcion = new CatalogoOpcionVO();
				opcion.setIdCatalogoOpcion(mes.getIdOpcion());
				opcion.setOpcion(String.valueOf(mes.getIdOpcion()));
				opciones.add(opcion);
			}

			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {e.printStackTrace(); logger.error(e);}

		return null;
	}

	public ActionForward anios(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<CatalogoOpcionVO> opciones = new ArrayList<CatalogoOpcionVO>();

			Calendar hoy = Calendar.getInstance();
			int year = hoy.get(Calendar.YEAR);

			for (int i = year; i <= (year+1); i++){
				CatalogoOpcionVO opcion = new CatalogoOpcionVO();
				opcion.setIdCatalogoOpcion(i);
				opcion.setOpcion(String.valueOf(i));
				opciones.add(opcion);
			}

			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {		
			e.printStackTrace(); logger.error(e);
		}		
		return null;
	}

	public ActionForward horarioLaboral(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_HORARIO_LABORAL);
			redirectJsonCatalogo(opciones, response);
		} catch(Exception e){logger.error(e); e.printStackTrace();}
		return null;
	}
	
	public ActionForward horarioCapacitacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_HORARIO_CONTACTO);
			redirectJsonCatalogo(opciones, response);
		} catch(Exception e){logger.error(e); e.printStackTrace();}
		return null;
	}

	public ActionForward discapacidad(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_TIPO_DISCAPACIDAD,filtroDiscapacidadNinguna);
			redirectJsonCatalogo(opciones, response);
		} catch(Exception e){logger.error(e); e.printStackTrace();}
		return null;
	}

	public ActionForward causaOferta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_CAUSA_OFERTA);
			redirectJsonCatalogo(opciones, response);
		} catch(Exception e){logger.error(e); e.printStackTrace();}
		return null;
	}

	public ActionForward entidad(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_ENTIDAD_FEDERATIVA,filtroNacidoEnElExtranjero);
			redirectJsonCatalogo(opciones, response);
		} catch(Exception e){logger.error(e); e.printStackTrace();}
		return null;
	}
	
	public ActionForward pais(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<CatalogoOpcionVO> opciones = new ArrayList<CatalogoOpcionVO>();
			
			CatalogoOpcionVO vo = new CatalogoOpcionVO();
			vo.setIdCatalogoOpcion(PAIS_OFERTA.MEXICO.getIdOpcion());
			vo.setOpcion(PAIS_OFERTA.MEXICO.getOpcion());
			
			opciones.add(vo);
			
			vo = new CatalogoOpcionVO();
			vo.setIdCatalogoOpcion(PAIS_OFERTA.CANADA.getIdOpcion());
			vo.setOpcion(PAIS_OFERTA.CANADA.getOpcion());
			
			opciones.add(vo);
			
			redirectJsonCatalogo(opciones, response);
		} catch(Exception e){logger.error(e); e.printStackTrace();}
		
		return null;
	}
	
	
	public ActionForward provinciasCanada(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_PROVINCIAS_CANADA);
			redirectJsonCatalogo(opciones, response);
		} catch(Exception e){logger.error(e); e.printStackTrace();}
		return null;		
	}	

	public ActionForward escolaridad(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_GRADO_ESTUDIOS,true);
			redirectJsonCatalogo(opciones, response);
		} catch(Exception e){logger.error(e); e.printStackTrace();}
		return null;
	}

	public ActionForward carreras(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		OfertaEmpleoForm forma = (OfertaEmpleoForm) form;
		try {
			long idCatDep = Utils.parseLong(request.getParameter("idCatDep"));
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(idCatDep);
			redirectJsonCatalogo(opciones, response);
			forma.setCarrerasMultiRegistro(opciones);
		} catch (Exception e) {logger.error(e); e.printStackTrace();}

		return null;
	}

	public ActionForward situacionesAcademicas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			long idEscolaridad = Utils.parseLong(request.getParameter("idEscolaridad"));
			long[] filtro = Utils.getFiltroSituacionAcademica(idEscolaridad);
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_ESTATUS_GRADO,filtro, true);
			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {logger.error(e); e.printStackTrace();}

		return null;
	}

	public ActionForward dominios(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		CatalogoOpcionVO blank = new CatalogoOpcionVO();
		blank.setIdCatalogoOpcion(0);
		blank.setOpcion("");
		try { 
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_DOMINIO, true);
			opciones.add(0, blank);
			redirectJsonCatalogo(opciones, response);
		} catch(Exception e){logger.error(e); e.printStackTrace();}
	
		return null;
	}

	public ActionForward experiencia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try { 
			//long[] filtro = {8};
			//FIXME: Filtrar la opcion 1 Ninguna, porque no es logico pedir un conocimiento sin experiencia
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_EXPERIENCIA, true);
			request.getSession().setAttribute("experiencia", opciones);
			redirectJsonCatalogo(opciones, response);
		} catch(Exception e){logger.error(e); e.printStackTrace();}

		return null;
	}

	public ActionForward idiomas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		CatalogoOpcionVO blank = new CatalogoOpcionVO();
		blank.setIdCatalogoOpcion(0);
		blank.setOpcion("");
		try {
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_IDIOMAS, filtro_idioma, true);
			opciones.add(0, blank);
			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {logger.error(e); e.printStackTrace();}

		return null;
	}

	public ActionForward certificaciones(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			long idCatDep = Utils.parseLong(request.getParameter("idCatDep"));

			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(idCatDep, true);
			redirectJsonCatalogo(opciones, response);
		} catch(Exception e){logger.error(e); e.printStackTrace();}

		return null;
	}

	public ActionForward actividadEconomica(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try { 
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_TIPO_ACTIVIDAD_ECONOMICA);
			redirectJsonCatalogo(opciones, response);
		} catch(Exception e){logger.error(e); e.printStackTrace();}

		return null;
	}

	public ActionForward vigencia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			long[] filter = {9};
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_VIGENCIA_OFERTA, filter, true);
			redirectJsonCatalogo(opciones, response);
		} catch(Exception e){logger.error(e); e.printStackTrace();}

		return null;
	}

	private void redirectJsonCatalogo(List<CatalogoOpcionVO> opciones, HttpServletResponse response) throws IOException {
		CatalogoVO cat = getCatalogo(opciones);
		String json = toJson(cat);
		redirectJsonResponse(response, json);
	}

	private ConocimientoComputacionVO setConocimientosComputacion(
			int conocimientoCompHojaCal, int conocimientoCompInternet,
			String conocimientoCompOtros,
			int conocimientoCompProcesadorTxt, int conocimientoCompRedes) {
		ConocimientoComputacionVO vo = new ConocimientoComputacionVO();
		vo.setHojaCalculo(conocimientoCompHojaCal);
		vo.setInternet(conocimientoCompInternet);
		vo.setOtros(conocimientoCompOtros);
		vo.setProcesadorTxt(conocimientoCompProcesadorTxt);
		vo.setRedesSociales(conocimientoCompRedes);

		return vo;
	}
	
	public ActionForward tipoCapacitacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		try {
			List<ModalidadVO> modalidades = OfertaBusDelegateImpl.getInstance().consultarModalidad(SUBPROGRAMA.BECATE.getIdOpcion());
			List<CatalogoOpcionVO> catalogos = new ArrayList<CatalogoOpcionVO>();
			
			for(ModalidadVO modalidad:modalidades){
				CatalogoOpcionVO catalogo = new CatalogoOpcionVO();
				catalogo.setIdCatalogoOpcion(modalidad.getIdModalidad());
				catalogo.setOpcion(modalidad.getModalidad());
				catalogos.add(catalogo);
			}
			//solo se estaran tomando en cuenta los dos primeros.
			catalogos.remove(3);  
			catalogos.remove(2); 
			redirectJsonCatalogo(catalogos, response);
			
		} catch(Exception e){logger.error(e); e.printStackTrace();}
		return null;
	}
	
	public ActionForward cursos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		try { 
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_CURSOS);
			redirectJsonCatalogo(opciones, response);
		} catch(Exception e){logger.error(e); e.printStackTrace();}

		return null;
	}
	
	public ActionForward tipoRecurso(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		try { 
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_TIPO_RECURSO);
			redirectJsonCatalogo(opciones, response);
		} catch(Exception e){logger.error(e); e.printStackTrace();}

		return null;
	}
	
	public ActionForward salarios(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		try { 
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_NUMERO_SALARIOS);
			redirectJsonCatalogo(opciones, response);
		} catch(Exception e){logger.error(e); e.printStackTrace();}

		return null;
	}
	
	private OfertaEmpleoBecateVO setOfertaEmpleoBecate(OfertaEmpleoForm oferta) {
		OfertaEmpleoBecateVO ofertaEmpleoBecateVO = new OfertaEmpleoBecateVO();
		ofertaEmpleoBecateVO.setClaveOferta(oferta.getClaveOferta());
		ofertaEmpleoBecateVO.setIdCurso((long) oferta.getIdCurso());
		ofertaEmpleoBecateVO.setIdTipoRecurso((long) oferta.getIdTipoRecurso());
		ofertaEmpleoBecateVO.setNumeroPlazas(oferta.getNumeroPlazasBecate());
		ofertaEmpleoBecateVO.setIdHorarioImparticion((long) oferta.getIdHorarioImparticion());
		ofertaEmpleoBecateVO.setIdHorarioEntrada((long) oferta.getIdHorarioEntrada());
		ofertaEmpleoBecateVO.setIdHorarioSalida((long) oferta.getIdHorarioSalida());
		ofertaEmpleoBecateVO.setFechaInicio(oferta.getFechaInicioBecate());
		ofertaEmpleoBecateVO.setFechaFin(oferta.getFechaFinBecate());
		ofertaEmpleoBecateVO.setIdDuracion((long) oferta.getIdDuracion());
	    ofertaEmpleoBecateVO.setIdSalario((long) oferta.getIdSalario());
	    ofertaEmpleoBecateVO.setMonto(oferta.getMonto());
		return ofertaEmpleoBecateVO;
	}
	
	//Generacion de folio para becate
	private String generarClave(){
		Date date = new Date();		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		String ano = Integer.toString(year);
		String clave = null;
		
		OfertaBusDelegate service = OfertaBusDelegateImpl.getInstance();
		List<OfertaEmpleoBecateVO> list = null;
		OfertaEmpleoBecateVO oferta;
		
		try {
			list = service.obtenerOfertasBecate();
		
			if (list == null || list.isEmpty()) {
				clave = ano + "-" + Utils.formatNumber(1);
			} else {
				oferta = list.get(list.size() - 1);
				String datoFolio = oferta.getClaveOferta();
				String[] partes = datoFolio.split("-");
				int consec = Integer.parseInt(partes[1]);
				consec++;

				clave = ano + "-" + Utils.formatNumber(consec);
			}
			
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			logger.error(e);
//			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.ServiceLocatorException"));	
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
//			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.Exception"));
		} 
		
		return clave;
		
	}
}