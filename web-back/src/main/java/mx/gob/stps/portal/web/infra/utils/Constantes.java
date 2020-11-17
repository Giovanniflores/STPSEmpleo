package mx.gob.stps.portal.web.infra.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase para constantes Web del sistema
 * 
 * @author oscar.manzo
 */
public final class Constantes {

	private Constantes() {}
	
	/** Dia en Milisegundos OAM **/
	public static final long DIA_MILISEGUNDOS = 24 * 60 * 60 * 1000;

	/** Identificador para la direccion de redireccionamiento del Portal de SWB **/
	public static final String URL_REDIRECT_SWB = "URL_REDIRECT_SWB";

	/** bandera para indicar que se redirecciona hacia la TAB de Mis Datos en el espacio de un candidato **/
	public static final String CANDIDATO_REG_MIS_DATOS = "registroMisDatos";
	
	/** Constantes del espacio de las ofertas creadas por la empresa **/
	//Posición de la lista de las ofertas.
	public static final String INDEX_OFERTA = "index";
	public static final String LISTA_OFERTAS_CREADAS = "ofertas";
	public static final String ID_CANDIDATO_OFERTA_CREADA = "idc";
	public static final String ID_OFERTA_CREADA = "oio";
	public static final String VO_OFERTA_CREADA = "obj";
	
	//Lista de Candidatos en oferta "x".
	public static final String LIST_CANDIDATOS_OFERTA = "LIST_CANDIDATOS_OFERTA";
	
	public static final String formatDate 		= "dd/MM/yyyy";
	public static final String formatDateForma 	= "yyyy-MM-dd";
	public static final String formatDateForma2 = "yyyy-MMMM-dd";
	public static final String formatDateAno 	= "yyyy";

	/** Nombre de archivo de propiedades de la aplicacion **/
	public static final String APPLICATION_PROPERTIES = "portal-web.properties";
	
	public static final String APPLICATION_MESSAGES = "MessageResources.properties";
	
	/** JNDI del EJB de Autorizacion **/
	public static final String JNDI_EJB_AUTORIZACION = "AutorizacionAppService#mx.gob.stps.portal.core.autorizacion.service.AutorizacionAppServiceRemote";
	/** JNDI del EJB de Seguridad **/
	public static final String JNDI_EJB_SEGURIDAD = "SeguridadAppService#mx.gob.stps.portal.core.seguridad.service.SeguridadAppServiceRemote";
	/** JNDI del EJB de Candidatos **/
	public static final String JNDI_EJB_CANDIDATOS_REGISTRO = "CandidatoRegistroAppService#mx.gob.stps.portal.core.candidate.service.CandidatoRegistroAppServiceRemote";
	/** JNDI del EJB de Opciones de Catalogo **/
	public static final String JNDI_EJB_CATALOGO_OPCION = "CatalogoOpcionAppService#mx.gob.stps.portal.core.infra.service.CatalogoOpcionAppServiceRemote";
	/** JNDI del EJB de Empresa **/
	public static final String JNDI_EJB_EMPRESA = "EmpresaAppService#mx.gob.stps.portal.core.empresa.service.EmpresaAppServiceRemote";
	/** JNDI del EJB de Registro único **/
	public static final String JNDI_EJB_REGISTRO_UNICO = "SingleRegisterService#mx.gob.stps.portal.core.search.service.SingleRegisterServiceRemote";
	/** JNDI del EJB de Candidato **/
	public static final String JNDI_EJB_CANDIDATO = "CandidatoAppService#mx.gob.stps.portal.core.candidate.service.CandidatoAppServiceRemote";
	/** JNDI del EJB Depuracion de Candidatos por vigencia **/
	public static final String JNDI_EJB_DEPURACION_CANDIDATO_VIGENCIA = "DepuracionCandidatosAppService#mx.gob.stps.portal.core.debbuger.service.DepuracionCandidatosAppServiceRemote";
	/** JNDI del EJB Crm**/
	public static final String JNDI_EJB_CRM = "CrmAppService#mx.gob.stps.portal.core.crm.service.CrmAppServiceRemote";
	/** JNDI del EJB de Testimonio **/
	public static final String JNDI_EJB_TESTIMONIO = "TestimonioAppService#mx.gob.stps.portal.core.testimonio.service.TestimonioAppServiceRemote";
	/** JNDI del EJB de Domicilio **/
	public static final String JNDI_EJB_DOMICILIO = "DomicilioAppService#mx.gob.stps.portal.core.domicilio.service.DomicilioAppServiceRemote";
	/** JNDI del EJB de Registro de contactos **/
	//public static final String JNDI_EJB_REGISTRO_CONTACTO = "RegistroContactoAppService#mx.gob.stps.portal.core.empresa.service.RegistroContactoAppServiceRemote";	

	/** JNDI del EJB de Busqueda por perfil **/
	public static final String JNDI_EJB_OFERTAS_XPERFIL = "OfertasPorPerfilAppService#mx.gob.stps.portal.core.oferta.busqueda.service.OfertasPorPerfilAppServiceRemote";

	/** JNDI del EJB de Busqueda por perfil **/
	public static final String JNDI_EJB_OFERTAS_RECIENTES = "OfertasRecientesAppService#mx.gob.stps.portal.core.oferta.busqueda.service.OfertasRecientesAppServiceRemote";

	/** JNDI del EJB de Busqueda de ofertas por canal **/
	public static final String JNDI_EJB_OFERTAS_XCANAL = "OfertasPorCanalAppService#mx.gob.stps.portal.core.oferta.busqueda.service.OfertasPorCanalAppServiceRemote";

	/** JNDI del EJB de Resumen de Mis Ofertas Recientes **/
	public static final String JNDI_EJB_RESUMEN_RECIENTES = "ResumenRecientesAppService#mx.gob.stps.portal.core.oferta.busqueda.service.ResumenRecientesAppServiceRemote";

	/** JNDI del EJB de Registro de contactos **/
	public static final String JNDI_EJB_WS_BOLSAS_TRABAJO = "BuscaOfertaEmpleoAppService#mx.gob.stps.portal.core.ws.ofertas.service.BuscaOfertaEmpleoAppServiceRemote";
	
	public static final String JNDI_EJB_WS_SFP = "OfertasSFPAppService#mx.gob.stps.portal.core.ws.ofertas.service.OfertasSFPAppServiceRemote";

//	public static final String JNDI_EJB_WS_PORTAL_EMPLEO_BUSCADOR = "PortalEmpleoBuscadorService#mx.gob.stps.portal.core.search.service.PortalEmpleoBuscadorServiceRemote";	

    public static final String JNDI_EJB_WS_PORTAL_EMPLEO_BUSCADOR = "PortalIndexadorService#mx.gob.stps.portal.core.search.service.PortalEmpleoBuscadorServiceRemote";
	
	/**JNDI del EJB del web service Portal Discapacidad y Empleo **/
	public static final String JNDI_EJB_WS_PORTAL_DISCAPACIDAD_EMPLEO = "OfertasDYEAppService#mx.gob.stps.portal.core.ws.ofertas.service.OfertasDYEAppServiceRemote";

	/** JNDI del EJB de Telefono **/
	public static final String JNDI_EJB_TELEFONO = "TelefonoAppService#mx.gob.stps.portal.core.infra.service.TelefonoAppServiceRemote";	

	/** JNDI del EJB de Ofertas **/
	public static final String JNDI_EJB_OFERTA = "OfertaAppService#mx.gob.stps.portal.core.oferta.registro.service.OfertaAppServiceRemote";

	/** JNDI del EJB de catalogos **/
	public static final String JNDI_EJB_CATALOGO = "CatalogoOpcionAppService#mx.gob.stps.portal.core.infra.service.CatalogoOpcionAppServiceRemote";
	
	/** JNDI del EJB de catalogos **/
	public static final String JNDI_EJB_ENTREVISTA = "EntrevistaAppService#mx.gob.stps.portal.core.entrevista.service.EntrevistaAppServiceRemote";
	
	/** JNDI proceso de Depuracion de Cuentas de Usuario Candidatos y Empresas**/
	public static final String JNDI_EJB_DEPURACION_CUENTAS = "DepuracionCuentasAppService#mx.gob.stps.portal.core.debbuger.service.DepuracionCuentasAppServiceRemote";

	/** JNDI proceso de Depuracion de Ofertas de Empleo **/
	public static final String JNDI_EJB_DEPURACION_OFERTAS = "DepuracionOfertasAppService#mx.gob.stps.portal.core.debbuger.service.DepuracionOfertasAppServiceRemote";
	
	/** JNDI para el Contador de Ofertas Publicadas **/
	public static final String JNDI_EJB_CONTADOR_OFERTAS = "ContadorOfertasAppService#mx.gob.stps.portal.core.debbuger.service.ContadorOfertasAppServiceRemote";
	
	/** JNDI para el Cil **/
	public static final String JNDI_EJB_CIL = "CilAppService#mx.gob.stps.portal.core.cil.service.CilAppServiceRemote";
	
	public static final String JNDI_EJB_NOTIFICACIONES = "NotificacionAppServices#mx.gob.stps.portal.core.seguridad.service.NotificacionAppServicesRemote";
	
	public static final String JNDI_EJB_SEGUIMIENTO = "SeguimientoAtencionAppService#mx.gob.stps.portal.core.seguridad.service.SeguimientoAtencionAppServiceRemote";

	public static final String JNDI_EJB_SALARIO_MINIMO = "SalarioMinimoAppService#mx.gob.stps.portal.core.salarios.service.SalarioMinimoAppServiceRemote";

	/** JNDI del EJB de oferta extranjera **/
	public static final String JNDI_EJB_OFERTA_EXT = "OfertaExtranjeraAppService#mx.gob.stps.portal.core.oferta.detalle.service.OfertaExtranjeraAppServiceRemote";
	
	/** Identificador (session) de la ruta de la JSP que sera incrustada como cuerpo en la plantilla **/
	public static final String BODY_JSP = "BODY_JSP";
	
	/** Nombre del forward que direcciona a la pagina principal **/
	public static final String FORWARD_JSP = "JSP";

	/** Nombre del forward que direcciona a la pagina de Inicio **/
	public static final String FORWARD_INICIO = "INICIO";
	
	/** Nombre del forward que direcciona a la pagina ANTERIOR **/
	public static final String FORWARD_REGRESO = "REGRESO";

	/** Nombre del forward que direcciona a la pagina principal **/
	public static final String FORWARD_REINTENTAR = "ACTION_OFERTAS_REINTENTAR";

	/** Nombre para el direccionamiento de SWB desde el iFrame **/
	public static final String FORWARD_REDIRECT_SWB = "REDIRECT_SWB";	
	
	/** Nombre del forward que direcciona a la pantalla de administración de conocimientos para ofertas de empleo **/
	public static final String JSP_CONOCIMIENTO = "JSP_CONOCIMIENTO";
	/** Nombre del forward que direcciona al action de administración de conocimientos para ofertas de empleo **/
	public static final String NEXT_CONOCIMIENTO = "NEXT_CONOCIMIENTO";
	
	/** Nombre del forward que direcciona a la pantalla de administración de habilidades para ofertas de empleo **/
	public static final String JSP_HABILIDAD = "JSP_HABILIDAD";
	/** Nombre del forward que direcciona a la pantalla de administración de habilidades para ofertas de empleo **/
	public static final String NEXT_HABILIDAD = "NEXT_HABILIDAD";
	
	/** Nombre del forward que direcciona a la pantalla de administración de competencias para ofertas de empleo **/
	public static final String JSP_COMPETENCIA = "JSP_COMPETENCIA";
	/** Nombre del forward que direcciona a la pantalla de administración de competencias para ofertas de empleo **/
	public static final String NEXT_COMPETENCIA = "NEXT_COMPETENCIA";
	
	/** Nombre del forward que direcciona a la pantalla de administración de idiomas para ofertas de empleo **/
	public static final String JSP_IDIOMA = "JSP_IDIOMA";
	/** Nombre del forward que direcciona a la pantalla de administración de idiomas para ofertas de empleo **/
	public static final String NEXT_IDIOMA = "NEXT_IDIOMA";
	
	/** Nombre del forward que direcciona a la pantalla de administración de idiomas para ofertas de empleo **/
	public static final String JSP_CARRERA = "JSP_CARRERA";
	/** Nombre del forward que direcciona a la pantalla de administración de idiomas para ofertas de empleo **/
	public static final String NEXT_CARRERA = "NEXT_CARRERA";
	
	/** Nombre del forward que direcciona a la pantalla de administración de idiomas para ofertas de empleo **/
	public static final String JSP_SECTOR = "JSP_SECTOR";
	/** Nombre del forward que direcciona a la pantalla de administración de idiomas para ofertas de empleo **/
	public static final String NEXT_SECTOR = "NEXT_SECTOR";
	/** Nombre del forward que direcciona a la pantalla de administración de idiomas para ofertas de empleo **/
	public static final String JSP_ENTIDAD = "JSP_ENTIDAD";
	/** Nombre del forward que direcciona a la pantalla de administración de idiomas para ofertas de empleo **/
	public static final String NEXT_ENTIDAD = "NEXT_ENTIDAD";
	
	/** Nombre del forward que direcciona a la pantalla de administración de telefonos adicionales **/
	public static final String JSP_PHONE = "JSP_PHONE";	

	/** Nombre del forward que direcciona a la pantalla correspondiente cuando la operaci&oacute;n es exitosa **/
	public static final String JSP_SUCCESS = "JSP_SUCCESS";
	
	public static final String JSP_PRINT = "JSP_PRINT";
	
	public static final String JSP_PDF = "JSP_PDF";

	/** Nombre del forward que direcciona a la pantalla correspondiente cuando el usuario cancela la operaci&oacute;n **/	
	public static final String JSP_CANCEL = "JSP_CANCEL";                                                      	
	                                                                                                             	
	/** Nombre del forward que direcciona a la pagina siguiente **/
	public static final String FORWARD_NEXT = "NEXT";
	 
	/** Nombre del forward que direcciona a la pagina de autorización de ofertas **/
	public static final String AUTORIZACION = "AUTORIZACION";
	
	public static final String REGRESO = "REGRESO";
	
	/** Identificador del parametro para Autorizar un Registro por Validar **/
	public static final String PARAM_ID_REGISTRO_POR_VALIDAR = "idRegValidarEmpresa";	
	 
	/** Identificador de Usuario en session **/
	public static final String USUARIO_APP = "USUARIO_APP";

	/** Identificador de Objeto Captcha en Session por Usuario **/
	public static final String CAPTCHA_OBJ = "CAPTCHA_OBJ";
	
	/** Bandera para verificar si el candidato requiere indexacion **/
	public static final String ID_CANDIDATO_INDEXABLE = "ID_CANDIDATO_INDEXABLE";
	
	public static final String CANDIDATO_HEAD = "candidatoheader";
	public static final String EMPRESA_HEAD = "empresaheader";
	
	/** Identificador para indicar el Host de acceso  **/
	public static final String HOST_PORTAL = "HOST_PORTAL";
	
	/** Valor de indicador de acceso desde un CIL **/
	public static final String HOST_CIL = "CIL";	
	
	/** Identificador de la cookie para el usuario firmado **/
	public static final String COOKIE_USUARIO = "USERLOGGED";
	
	public static final String FLAG_SESION_ACTIVA = "FLAG_SESION_ACTIVA";
	
	/** Nombre de la pagina utilizada como plantilla para el Espacio de Empresas **/
	public static final String FORWARD_TEMPLATE_MI_ESP_EMP = "templateEspacioEmpresas";
	
	/** Nombre de la pagina utilizada como plantilla para el Espacio de Candidatos **/
	public static final String FORWARD_TEMPLATE_MI_ESP_CAND = "templateEspacioCandidato";
	
	/** Nombre de la pagina utilizada como plantilla para el Espacio de Administrador **/
	public static final String FORWARD_TEMPLATE_ESP_ADMIN = "templateEspacioAdmin";
	
	/** Nombre de la pagina utilizada como plantilla para el HOME **/
	public static final String FORWARD_TEMPLATE_HOME = "templateHome";

	public static final String FORWARD_TEMPLATE_OFFER = "templateOffer";
	
	/** Nombre de la pagina utilizada como plantilla Responsiva **/
	public static final String FORWARD_TEMPLATE_RESPONSIVE = "templateResponsive";

	/** Nombre de la pagina utilizada como plantilla para espacio de candidatos **/
	public static final String FORWARD_TEMPLATE_CAND = "templateCandidato";

	/** Nombre de la pagina utilizada como plantilla para Formularios **/
	public static final String FORWARD_TEMPLATE_FORM = "templateForm";
	
	/** Nombre de la pagina utilizada como plantilla para Formularios SIN ESTILOS **/
	public static final String FORWARD_TEMPLATE_FORM_EMPTY = "templateFormEmpty";
	
	/** Nombre de la pagina utilizada como plantilla para Formularios SIN ESTILOS **/
	public static final String FORWARD_TEMPLATE_FORM_EMPTY_RESP = "templateFormEmptyResp";
	
	/** Nombre de la pagina utilizada como plantilla para CURSOS **/
	public static final String FORWARD_TEMPLATE_CURSO = "templateCurso";
	
	/** Nombre de la pagina utilizada como plantilla para los Buscadores **/
	public static final String FORWARD_TEMPLATE_BUSQUEDAS = "templateBusquedas";
	
	/** Nombre de la pagina utilizada como plantilla para los Buscadores **/
	public static final String FORWARD_TEMPLATE_BUSQUEDAS_EMPRESA = "templateBusquedasEmpresa";
	
	/** Nombre de la pagina utilizada como plantilla para LAS BOLSAS DE TRABJO EXTERNAS **/
	public static final String FORWARD_TEMPLATE_BOLSAS_TRABAJO = "templateBolsasTrabajo";
	
	public static final String FORWARD_TEMPLATE_REGISTRO_CANDIDATO = "templateRegistroCandidato";
	public static final String FORWARD_TEMPLATE_REGISTRO_EMPRESA = "templateRegistroEmpresa"; 
	
	public static final String FORWARD_TEMPLATE_REGISTRO_CANDIDATO_EVENTO = "templateRegistroCandidatoEvento";
	
	/** Nombre de la pagina utilizada como plantilla para pantalla de administrador **/
	//public static final String FORWARD_TEMPLATE_ADMIN = "templateAdmin";

	/** Nombre de la pagina utilizada como plantilla para portal Interno **/
	//public static final String FORWARD_TEMPLATE_WORK = "templateWork";

	/** Nombre de la pagina utilizada como plantilla para espacio de candidatos **/
	//public static final String FORWARD_TEMPLATE_ADDRESS = "templateDomicilio";
	
	/** Nombre de la pagina utilizada como plantilla para espacio de registro de contactos **/
	//public static final String FORWARD_TEMPLATE_REGCONT = "templateRegCont";	
	
	/** Identificador para el catalogo de Tipo Empresa que se coloca en sesión **/
	public static final String CAT_TIPO_EMPRESA = "CAT_TIPO_EMPRESA";
	/** Identificador para el catalogo de Tipo de Actividad Económica de la Empresa que se coloca en sesión **/
	public static final String CAT_ACTIVIDAD_ECONOMICA = "CAT_ACTIVIDAD_ECONOMICA";
	/** Identificador para el catalogo de Medio por el cual se entero del Portal del empleo que se coloca en sesión **/
	public static final String CAT_MEDIO_ENTERADO = "CAT_MEDIO_ENTERADO";

	/** Identificador del catalogo de Entidades **/
	public static final String CAT_ENTIDADES = "CAT_ENTIDADES";

	/** Identificador del catalogo de Roles **/
	public static final String CAT_PERFIL = "CAT_PERFIL";

	/** Identificador del catalogo de Tipos de Usuario **/
	public static final String CAT_TIPO_USUARIO = "CAT_TIPO_USUARIO";
	
	/** Identificador del catalogo de Tipos de Telefono **/
	public static final String CAT_TIPO_TELEFONO = "CAT_TIPO_TELEFONO";
	public static final String TELEFONO_CELULAR_ID = "TELEFONO_CELULAR_ID";
	public static final String TELEFONO_CELULAR_DES = "TELEFONO_CELULAR_DES";
	public static final String TELEFONO_FIJO_ID = "TELEFONO_FIJO_ID";
	public static final String TELEFONO_FIJO_DES = "TELEFONO_FIJO_DES";
	/** Identificador del listado de teléfonos adicionales **/
	public static final String LST_TELEFONOS_ADICIONALES = "LST_TELEFONOS_ADICIONALES";
	/** Identificador del listado de teléfonos adicionales **/
	public static final String LST_CONTACTOS = "LST_CONTACTOS";
	
	/** Identificador del idEmpresa (padre) de terceras empresas **/
	public static final String ID_EMPRESA_PADRE = "ID_EMPRESA_PADRE";
	/** Identificador de la descripcion del tipo de empresa (del padre) de terceras empresas **/
	public static final String DES_TIPO_EMPRESA_PADRE = "DES_TIPO_EMPRESA_PADRE";
	/** Identificador del listado de terceras empresas **/
	public static final String LST_TERCERAS_EMPRESAS = "LST_TERCERAS_EMPRESAS";
	/** Identificador propietario **/
	public static final String ID_PROPIETARIO = "ID_PROPIETARIO";
	/** Identificador tipo de propietario **/
	public static final String ID_TIPO_PROPIETARIO = "ID_TIPO_PROPIETARIO";	
	/** Identificador empresa **/
	public static final String ID_EMPRESA = "ID_EMPRESA";
	/** Identificador candidato **/
	public static final String ID_CANDIDATO = "ID_CANDIDATO";
	/** Ruta imagen por defecto empresa **/
	public static final String RUTA_IMAGEN_POR_DEFECTO_EMPRESA = "images/img_no_disponible_gde.jpg";
	/** Ruta imagen por defecto candidato **/
	public static final String RUTA_IMAGEN_POR_DEFECTO_CANDIDATO = "images/img_no_disponible_gde.jpg";
	
	public static final int ID_TIPO_USUARIO_EMPRESA = 2;
	
	
	
	/** Límite superior de compatibilidad **/
	public static final int COMPATIBILITY_LIMIT = 70;
	
	public static final String ATRIB_TESTIMONIO_OUT = "TESTIMONIO_INDEX";
		
	/** Límite de la longitud para la ruta del Video Currículum del Candidato **/
	public static final short CV_URL_LENGTH = 255;
	
	/** Límite de la longitud para la descripción del Video Currículum del Candidato **/
	public static final short CV_DESC_LENGTH = 2000;
	
	/** JNDI del EJB de Ver Detalle Oferta **/
	public static final String JNDI_EJB_OFFER_DETAIL = "OfertaVerDetalleAppService#mx.gob.stps.portal.core.oferta.detalle.service.OfertaVerDetalleAppServiceRemote";
	
	/** JNDI del EJB de Preguntas de Oferta **/
	public static final String JNDI_EJB_OFFER_QUESTION = "OfertaPreguntaAppService#mx.gob.stps.portal.core.oferta.pregunta.service.OfertaPreguntaAppServiceRemote";
	
	/** JNDI del EJB de Postulación a Oferta **/
	public static final String JNDI_EJB_OFFER_POST = "OfertaPostulacionAppService#mx.gob.stps.portal.core.oferta.detalle.service.OfertaPostulacionAppServiceRemote";
	
	/** JNDI del EJB de Candidato vinculado a Oferta **/
	public static final String JNDI_EJB_OFFER_CANDIDATE = "OfertaCandidatoAppService#mx.gob.stps.portal.core.oferta.detalle.service.OfertaCandidatoAppServiceRemote";
	
	/** JNDI del EJB de Oferta **/
	public static final String JNDI_EJB_OFFER = "OfertaEmpleoAppService#mx.gob.stps.portal.core.oferta.detalle.service.OfertaEmpleoAppServiceRemote";

	/** JNDI del EJB de Oferta requisito **/
	public static final String JNDI_EJB_REQUIREMENT = "OfertaRequisitoAppService#mx.gob.stps.portal.core.oferta.detalle.service.OfertaRequisitoAppServiceRemote";
	
	/** JNDI del EJB de Oferta idioma **/
	public static final String JNDI_EJB_LANGUAGES = "OfertaIdiomaAppService#mx.gob.stps.portal.core.oferta.detalle.service.OfertaIdiomaAppServiceRemote";
	
	/** JNDI del EJB de Oferta sector **/
	public static final String JNDI_EJB_SECTOR = "OfertaSectorAppService#mx.gob.stps.portal.core.oferta.detalle.service.OfertaSectorAppServiceRemote";
	
	/** Parámetro oferte de empleo **/
	public static final String ID_OFERTA_EMPLEO = "id_oferta_empleo";
	
	/** JNDI del EJB de Compatibilidad **/
//	public static final String JNDI_EJB_COMPATIBILITY = "OfertaCompatibilidadAppService#mx.gob.stps.portal.core.oferta.detalle.service.OfertaCompatibilidadAppServiceRemote";

	/** JNDI del EJB de OfertasSMS **/
	public static final String JNDI_EJB_OFERTAS_SMS = "OfertasSMSAppService#mx.gob.stps.portal.core.oferta.envioSMS.service.OfertasSMSAppServiceRemote";

	public static final int OFERTAS_POR_PAGINA_LISTADO= 10;
	
	public static final String BOLSAS_EXTERNAS = "LISTABOLSASEXTERNAS";
	
	/** JNDI del EJB de Testimonio **/
	public static final String JNDI_EJB_OFERTASRSS = "OfertasRSSAppService#mx.gob.stps.portal.core.ofertaRSS.service.OfertasRSSAppServiceRemote";
	
	/** JNDI del EJB de Testimonio **/
	public static final String JNDI_EJB_OFERTASRSS_CONSULTA = "OfertasRSSConsultaAppService#mx.gob.stps.portal.core.ofertaRSS.service.OfertasRSSConsultaAppServiceRemote";
	
	/** JNDI del EJB de Curso de Formacion **/
	public static final String JNDI_EJB_CURSO = "CursoAppService#mx.gob.stps.portal.core.curso.service.CursoAppServiceRemote";
	
	/** JNDI del EJB de Evento **/
	public static final String JNDI_EJB_EVENTO = "EventoAppService#mx.gob.stps.portal.core.event.service.EventoAppServiceRemote";
	
	public static final String JNDI_EJB_REPORTE_INFOVAVIT = "ReporteInfonavitService#mx.gob.stps.portal.core.candidate.reporteInfonavit.service.ReporteInfonavitServiceRemote";
	
	public static final String JNDI_EJB_OFERTAS_RIVIERA_MAYA = "OfertasRivieraMayaService#mx.gob.stps.portal.core.oferta.ofertasRiviera.service.OfertasRivieraMayaServiceRemote";
	
	/** JNDI del EJB de Administracion de No Postulaciones Ofertas del Candidato **/
	public static final String JNDI_EJB_ADM_OFFER_NO_POST = "AdministracionNoPostulacionesAppService#mx.gob.stps.portal.core.candidate.adminNoPostulaciones.service.AdministracionNoPostulacionesAppServiceRemote";
	public static final String JSP_NO_POSTULACIONES = "JSP_NO_POSTULACIONES";	
	
	public static final String ACCIONES_REQ_AUTENTICACION = "ACCIONES_REQ_AUTENTICACION";
	
	public static final String ERROR_MSG = "ERROR_MSG";

	public static final String LABEL_BOTON = "labelBoton";
	
	public static final String LABEL_CANCEL = "Cancelar"; 
	
	public static final String LABEL_REGRESAR = "Regresar"; 
	
	public enum ActionsGenerales {
		ACTIONS_SIN_FILTRO_SEGURIDAD("login", "solicitaCV", 
									 "logincil",
				                     "loginhome", 
				                     "logout", 
				                     "sinpermisos", 
				                     "inicio", 
				                     "registroseventos",
				                     "ofertasRecientes",
				                     "ofertasDestacadas",
				                     "testimonioIndex", 
				                     "version",
				                     "redirectswb",
				                     "SpellCheck",
				                     "menumap",
				                     "SpellTest", "detalleoferta", 
				                     "ocupate",
				                     "indexer", 
				                     "confirmacionRegistro",
				                     "imageAction",
				                     "suggestion",
				                     "recuperarpsw",
				                     "cursoFormacion",
				                     "registroCandidatos",
				                     "comregcompany",
				                     "registro_candidato",
				                     "registro_candidato_ppc",
				                     "registro",
				                     "registro_candidato_perfil",
				                     "registro_empresa",
				                     "registroEmpresa",
				                     "pollcomment",
				                     "activacion",
				                     "recupera_contrasena",
				                     "nuevaContrasena",
				                     "busqueda_especifica",
				                     "portalEmpleo",
				                     "offerdetail",
				                     "mensaje", "specificsearch",
				                     "portalEmpleoTmp","bolsasTrabajo", "busquedaEspecificaOfertas",
				                     "ofertasSFP", "ofertasRivieraMaya", "ofertasRSSConsultar");
		
		private ArrayList<String> actions = null;

		private ActionsGenerales(String... items) {
			actions = new ArrayList<String>();
			for (String item : items) actions.add(item);
		}

		public List<String> getActions() {
			return actions;
		}
	};

	/** Identificador para la cadena de control Captcha **/
	public static final String CAPTCHA_WORD = "captchaword";
	/** Tamaño de imagen con control Captcha **/
	public static final int CAPTCHA_SIZE = 4;

	/** Identificador en la sesion del mensaje de error **/
	public static final String MSG_ERROR_SESSION = "errmsg";

	/** Identificador del accion solicitada por el cliente **/
	public static final String ACTION_REQUESTED = "ACTION_REQUESTED";

	/** Bandera para indicar que no se mostrara la barra de articulos **/
	public static final String SIN_ARTICULOS = "SIN_ARTICULOS";

	public static final String TAB_MENU           = "TAB";
	public static final String TAB_MI_ESPACIO     = "TAB_MI_ESPACIO";
	public static final String TAB_MIS_DATOS      = "TAB_MIS_DATOS";
	public static final String TAB_MIS_OFERTAS    = "TAB_MIS_OFERTAS";
	public static final String TAB_MIS_CANDIDATOS = "TAB_MIS_CANDIDATOS";
	
	public static final String SHOW_BUSCADOR_OFERTAS = "SHOW_BUSCADOR_OFERTAS";
	
	/** Rangos de salarios **/
	public enum RANGOS_SALARIOS {
		
		MINIMUM(1,"Menor a $5,000"),
		MINIMUM_MIDDLE(2,"De $5,001 a $10,000"),
		MIDDLE(3,"De $10,001 a $15,000"),
		MAXIMUM_MIDDLE(4, "De $15,001 a $25,000"),
		MAXIMUM(5, "De $25,000 a $40,000"),
		OVER_MAXIMUM(6, "Más de 40,000");
		
		private int idOpcion;
		private String opcion;
	
		private RANGOS_SALARIOS(int idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public int getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
	}
	
	/** Rangos de edades **/
	public enum RANGOS_EDADES {
		
		YOUNG(1,"Menores a 21 años"),
		YOUNG_ADULT(2,"De 21 a 29 años"),
		ADULT(3,"De 30 a 39 años"),
		CONTEMPORARY_ADULT(4, "De 40 a 49 años"),
		SENIOR(5, "50 años o más");
		
		private int idOpcion;
		private String opcion;
	
		private RANGOS_EDADES(int idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public int getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
	}
	
	/** Regiones **/
	public enum REGION {
		
		NOROESTE(1,"Noroeste"),
		NORESTE(2,"Noreste"),
		CENTRO_OCCIDENTE(3,"Centro-Occidente"),
		CENTRO(4, "Centro"),
		SUR_SURESTE(5, "Sur-Sureste");
		
		private int idOpcion;
		private String opcion;
	
		private REGION(int idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public int getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
	}	

	public enum MESES {
		ENERO     ( 1, "Enero"),
		FEBRERO   ( 2, "Febrero"),
		MARZO     ( 3, "Marzo"),
		ABRIL     ( 4, "Abril"),
		MAYO      ( 5, "Mayo"),
		JUNIO     ( 6, "Junio"),
		JULIO     ( 7, "Julio"),
		AGOSTO    ( 8, "Agosto"),
		SEPTIEMBRE( 9, "Septiembre"),
		OCTUBRE   (10, "Octubre"),
		NOVIEMBRE (11, "Noviembre"),
		DICIEMBRE (12, "Diciembre");
		
		private int idOpcion;
		private String opcion;
	
		private MESES(int idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public int getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
	}
		
		/** Pais Oferta **/
		public enum PAIS_OFERTA {
			
			MEXICO(1,"México"),
			CANADA(3,"Canadá");
			
			private int idOpcion;
			private String opcion;
		
			private PAIS_OFERTA(int idOpcion, String opcion){
				this.idOpcion = idOpcion;
				this.opcion = opcion;
			}

			public int getIdOpcion() {return idOpcion;}
			public String getOpcion() {return opcion;}
		
	}
		
	public enum PARENTESCO {
		CONYUGE(2, "Esposa(o) o compañera(o)"),
		HIJO(3, "Hijo(a)"),
		SIN_PARENTESCO(19, "No tiene parentesco"),
		AMIGO(20, "Amigo(a)"),
		EXCONYUGE(21, "Ex-exposo(a)"),
		PADRES(22, "Madre o padre"),
		PADRESADOPTIVOS(23, "Madrastra o padrastro"),
		HERMANO(24, "Hermano(a)"),
		HERMANASTRO(25, "Hermanastro(a)"),
		ABUELO(26, "Abuelo(a)"),
		BISABUELO(27, "Bisabuelo(a)"),
		TATARABUELO(28,"Tatarabuelo(a)"),
		NIETO(29, "Nieto(a)"),
		BISNIETO(30, "Bisnieto(a"),
		TATARANIENTO(31, "Tataranieto(a)"),
		TIO(32, "Tio(a)"),
		SOBRINO(33, "Sobrino(a)"),
		PRIMO(34, "Primo(a"),
		SUEGRO(35, "Suegro(a)"),
		CONSUEGRO(36, "Consuegro(a)"),
		CONYUGEHIJO(37, "Nuera o yerno"),
		CONYUGEHERMANO(38, "Cuñado(a)"),
		CONCUNO(39, "Concuño(a)"),
		TUTOR(40, "Tutor(a)"),
		TUTELADO(41, "Tutelado(a)"),
		OTROPARENTESCO(45, "Otro parentesco");
		
		private int idOpcion;
		private String opcion;
		
		private PARENTESCO(int idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}
		
		public int getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
		
		public static String getParentesco(int idOpcion) {
			for (PARENTESCO parentesco : PARENTESCO.values()) {
				if (idOpcion == parentesco.getIdOpcion()) return parentesco.getOpcion();
			}
			return PARENTESCO.SIN_PARENTESCO.getOpcion();
		}
	}
	
	/** Parámetros para campaña SEO **/
	public static final String TITULO_PAGINA = "tituloPagina";

	public static final String DESCRIPCION_PAGINA = "descripcionPagina";

	public static final String FACEBOOK_IMAGE = "facebookImage";
	
	public static final String TWITTER_IMAGE = "twitterImage";
	
	public static final String URL_ESPECIFICA = "urlEspecifica";
	
	//RBM1 TK997 y TK998
	public static final String FORWARD_ACTION_PUBLICADOR = "ACTION_PUBLICADOR";
	//RBM1 TK990 y TK995
	public static final String ACTION_ALTA_OFERTA_RU = "ACTION_ALTA_OFERTA_RU";
	
	
	
}