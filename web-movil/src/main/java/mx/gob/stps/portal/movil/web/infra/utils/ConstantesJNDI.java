package mx.gob.stps.portal.movil.web.infra.utils;

public final class ConstantesJNDI {

	private ConstantesJNDI(){}
	
	/** JNDI del EJB de Autorizacion **/
	public static final String JNDI_EJB_SEGURIDAD = "SeguridadAppService#mx.gob.stps.portal.core.seguridad.service.SeguridadAppServiceRemote";
	
	/** JNDI del EJB de Empresa **/
	public static final String JNDI_EJB_EMPRESA = "EmpresaAppService#mx.gob.stps.portal.core.empresa.service.EmpresaAppServiceRemote";
	
	public static final String JNDI_EJB_WS_PORTAL_EMPLEO_BUSCADOR = "PortalEmpleoBuscadorService#mx.gob.stps.portal.core.search.service.PortalEmpleoBuscadorServiceRemote";	

	/** JNDI del EJB de Candidato **/
	public static final String JNDI_EJB_CANDIDATO = "CandidatoAppService#mx.gob.stps.portal.core.candidate.service.CandidatoAppServiceRemote";
	
	/** JNDI del EJB de Ofertas **/
	public static final String JNDI_EJB_OFERTA = "OfertaAppService#mx.gob.stps.portal.core.oferta.registro.service.OfertaAppServiceRemote";
	/** JNDI del EJB de Opciones de Catalogo **/
	public static final String JNDI_EJB_CATALOGO_OPCION = "CatalogoOpcionAppService#mx.gob.stps.portal.core.infra.service.CatalogoOpcionAppServiceRemote";
	/** JNDI del EJB de Domicilio **/
	public static final String JNDI_EJB_DOMICILIO = "DomicilioAppService#mx.gob.stps.portal.core.domicilio.service.DomicilioAppServiceRemote";
	
	public static final String JNDI_EJB_OFFER_CANDIDATE = "OfertaCandidatoAppService#mx.gob.stps.portal.core.oferta.detalle.service.OfertaCandidatoAppServiceRemote";

	/** JNDI del EJB de Postulación a Oferta **/
	public static final String JNDI_EJB_OFFER_POST = "OfertaPostulacionAppService#mx.gob.stps.portal.core.oferta.detalle.service.OfertaPostulacionAppServiceRemote";

	/** JNDI del EJB de Preguntas de Oferta **/
	public static final String JNDI_EJB_OFFER_QUESTION = "OfertaPreguntaAppService#mx.gob.stps.portal.core.oferta.pregunta.service.OfertaPreguntaAppServiceRemote";
	
	/** JNDI del EJB de Compatibilidad **/
	public static final String JNDI_EJB_COMPATIBILITY = "OfertaCompatibilidadAppService#mx.gob.stps.portal.core.oferta.detalle.service.OfertaCompatibilidadAppServiceRemote";

	public static final String JNDI_EJB_ENTREVISTA = "EntrevistaAppService#mx.gob.stps.portal.core.entrevista.service.EntrevistaAppServiceRemote";
	
	/** JNDI del EJB de Candidatos **/
	public static final String JNDI_EJB_CANDIDATOS_REGISTRO = "CandidatoRegistroAppService#mx.gob.stps.portal.core.candidate.service.CandidatoRegistroAppServiceRemote";
	
}
