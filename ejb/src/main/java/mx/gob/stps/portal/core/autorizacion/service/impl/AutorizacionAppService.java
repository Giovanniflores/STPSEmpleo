package mx.gob.stps.portal.core.autorizacion.service.impl;

import mx.gob.stps.portal.core.autorizacion.dao.MotivoRechazoRegistroDAO;
import mx.gob.stps.portal.core.autorizacion.dao.RegistroPorValidarDAO;
import mx.gob.stps.portal.core.autorizacion.service.AutorizacionAppServiceLocal;
import mx.gob.stps.portal.core.autorizacion.service.AutorizacionAppServiceRemote;
import mx.gob.stps.portal.core.autorizacion.vo.ParametroVO;
import mx.gob.stps.portal.core.autorizacion.vo.RegistroPorValidarVO;
import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.candidate.vo.PerfilLaboralVo;
import mx.gob.stps.portal.core.domicilio.vo.CodigoPostalVO;
import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.domicilio.vo.MunicipioVO;
import mx.gob.stps.portal.core.empresa.dao.EmpresaFraudulentaDAO;
import mx.gob.stps.portal.core.empresa.service.EmpresaAppServiceLocal;
import mx.gob.stps.portal.core.empresa.vo.EmpresaFraudulentaVO;
import mx.gob.stps.portal.core.empresa.vo.EmpresaPorAutorizarVO;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.data.ConexionFactory;
import mx.gob.stps.portal.core.infra.data.ConnectionWraper;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.EVENTO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_REGISTRO;
import mx.gob.stps.portal.core.infra.utils.Constantes.*;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.core.persistencia.facade.*;
import mx.gob.stps.portal.core.search.exception.IndexerException;
//fixme import mx.gob.stps.portal.core.search.service.IndexerServiceLocator;
//import mx.gob.stps.portal.core.search.service.PortalEmpleoBuscadorServiceLocal;
import mx.gob.stps.portal.core.seguridad.vo.PublicadorVO;
import mx.gob.stps.portal.core.seguridad.vo.UsuarioVO;
import mx.gob.stps.portal.core.testimonio.dao.TestimonioDAO;
import mx.gob.stps.portal.core.testimonio.vo.TestimonioVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;

import org.apache.log4j.Logger;

import javax.ejb.*;
import javax.naming.Context;
import javax.persistence.PersistenceException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import static mx.gob.stps.portal.core.infra.utils.Constantes.*;

//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;

/**
 * Concentra los servicios y reglas de negocio para el modulo de Autorizacion de Registros
 * 
 * @author oscar.manzo
 */
@Stateless(name = "AutorizacionAppService", mappedName = "AutorizacionAppService")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AutorizacionAppService implements AutorizacionAppServiceRemote, AutorizacionAppServiceLocal {

	private static Logger logger = Logger.getLogger(AutorizacionAppService.class);
	
	@EJB private ParametroFacadeLocal parametroFacade;

	@EJB private RegistroPorValidarFacadeLocal registroPorValidarFacade;

	@EJB private EmpresaFacadeLocal empresaFacade;

	//@EJB private OfertaFacadeLocal ofertaFacade;
	
	@EJB private OfertaEmpleoFacadeLocal ofertaEmpleoFacade;
	
	@EJB private EmpresaFraudulentaFacadeLocal empresaFraudulentaFacade;
	
	@EJB private EmpresaPorAutorizarFacadeLocal empresaPorAutorizarFacade;
	
	//@EJB private OfertaEmpleoFraudulentaFacadeLocal ofertaEmpleoFraudulentaFacade;
	
	@EJB private PerfilLaboralFacadeLocal perfilLaboralFacade;
	
	@EJB private TestimonioFacadeLocal testimonioFacade;
	
	@EJB private BitacoraFacadeLocal bitacoraFacade;

	@EJB private EmpresaAppServiceLocal empresaAppService;
	
	@EJB private EntrevistaFacadeLocal entrevistaFacade;
		
	//@EJB private PortalEmpleoBuscadorServiceLocal portalEmpleoBuscadorService;
		
	@EJB private CandidatoFacadeLocal candidatoFacade;
	
	@EJB private UsuarioFacadeLocal  usuarioFacade;
	
	//@EJB private OfertaIdiomaFacadeLocal  ofertaIdiomaFacade;

	//@EJB private OfertaRequisitoFacadeLocal  ofertaRequisitoFacade;	

	@EJB private CatalogoOpcionFacadeLocal catalogoOpcionFacade;
	
	@EJB private DomicilioFacadeLocal domicilioFacade;
	
	@EJB private TelefonoFacadeLocal telefonoFacade;
	
	/* (non-Javadoc)
	 * @see mx.gob.stps.portal.core.autorizacion.service.AutorizacionAppServiceRemote#registraTiempoAsignacion(int)
	 */
    public void registraTiempoAsignacion(int minutos) throws BusinessException {

    	if (minutos<=0) throw new IllegalArgumentException("Valor invalido para el tiempo de asignacion ["+ minutos +"]");
    	
    	parametroFacade.updateOrSaveParametro(ID_PARAMETRO_TIEMPO, "Tiempo de asignación", String.valueOf(minutos));
    	
    }

    public void registraBloqueAsignacion(int bloque) throws BusinessException {
    	//logger.info("Asignando bloque de registros por validar :"+ bloque);
    	if (bloque<=0) throw new IllegalArgumentException("Valor invalido para el bloque de asignacion ["+ bloque +"]");
    	
    	parametroFacade.updateOrSaveParametro(ID_PARAMETRO_TAM_BLOQUE, "Bloque de asignación", String.valueOf(bloque));
    	
    }

    public void actualizaParametro(long idParametro, String valor) throws PersistenceException {

    	if (idParametro<=0) throw new IllegalArgumentException("Identificador del parametro requerido");
    	if (valor==null || valor.isEmpty()) throw new IllegalArgumentException("Valor de parametro requerido");
    	parametroFacade.update(idParametro, valor);

    }
    
	/* (non-Javadoc)
	 * @see mx.gob.stps.portal.core.autorizacion.service.AutorizacionAppServiceRemote#consultaTiempoAsignacion()
	 */
    public int consultaTiempoAsignacion() throws BusinessException {
    	int minutos = 0;

    	try{
        	ParametroVO parametro = parametroFacade.findById(ID_PARAMETRO_TIEMPO);

        	if (parametro!=null) minutos = Utils.parseInt(parametro.getValor());
    	}catch(PersistenceException e){
    		e.printStackTrace();
    		logger.error(e);
    		throw new BusinessException("No se pudo consultar el registro", e);
    	}

    	return minutos;
    }

    public int consultaBloqueAsignacion() throws BusinessException {
    	int bloque = 0;

    	try{
        	ParametroVO parametro = parametroFacade.findById(ID_PARAMETRO_TAM_BLOQUE);

        	if (parametro!=null) bloque = Utils.parseInt(parametro.getValor());
        	
    	}catch(PersistenceException e){
    		e.printStackTrace();
    		logger.error(e);
    		throw new BusinessException("No se pudo consultar el registro", e);
    	}

    	return bloque;
    }

    public List<ParametroVO> consultaParametros() {
    	List<ParametroVO> parametros = null;

    	try{
    		parametros = parametroFacade.findAll();
    	}catch(PersistenceException e){
    		e.printStackTrace();
    		logger.error(e);
    	}

    	return parametros;
    }

    public ParametroVO consultaParametro(long idParametro) throws PersistenceException {
    	ParametroVO parametro = parametroFacade.findById(idParametro);
    	return parametro;
    }
    
	/* (non-Javadoc)
	 * @see mx.gob.stps.portal.core.autorizacion.service.AutorizacionAppServiceLocal#registraEmpresaPorValidar(long)
	 */
    public void registraEmpresaPorValidar(long idEmpresa) {
    	int estatus = -1;
    	
    	EmpresaPorAutorizarVO empresaPorAutorizar = empresaPorAutorizarFacade.findById(idEmpresa);
    	
    	if (empresaPorAutorizar!=null){
    		estatus = empresaPorAutorizar.getEstatus();
    	}else{
    		EmpresaVO empresa = empresaFacade.findById(idEmpresa);
    		if (empresa!=null)
    			estatus = empresa.getEstatus();
    	}

    	if (estatus != ESTATUS.REGISTRADA.getIdOpcion() && estatus != ESTATUS.MODIFICADA.getIdOpcion())
    		logger.error("Estatus Invalido ["+ estatus +"] de la Empresa ["+ idEmpresa +"]");
    	
    	altaRegistroPorValidar(idEmpresa, TIPO_REGISTRO.EMPRESA, idEmpresa, TIPO_PROPIETARIO.EMPRESA);
    }
	
    /* (non-Javadoc)
	 * @see mx.gob.stps.portal.core.autorizacion.service.AutorizacionAppServiceLocal#registraOfertaPorValidar(long,long)
	 */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public void registraOfertaPorValidar(long idOfertaEmpleo, long idEmpresa) {
    	
    	OfertaEmpleoVO oferta = ofertaEmpleoFacade.findById(idOfertaEmpleo);
    	
    	if (oferta!=null && idEmpresa<=0){
    		idEmpresa = oferta.getIdEmpresa();
    		
        	if (oferta.getEstatus() != ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion() && 
            		oferta.getEstatus() != ESTATUS.EMP_MODIFICADA.getIdOpcion()){
        		logger.error("Estatus Invalido ["+ oferta.getEstatus() +"] de la Ofertas de Empleo ["+ idOfertaEmpleo +"]");        		
        	}    		
    	}

    	altaRegistroPorValidar(idOfertaEmpleo, TIPO_REGISTRO.OFERTA, idEmpresa, TIPO_PROPIETARIO.EMPRESA);
    }

    /* (non-Javadoc)
	 * @see mx.gob.stps.portal.core.autorizacion.service.AutorizacionAppServiceLocal#registraTestimonioCandidatoPorValidar(long,long)
	 */
    public void registraTestimonioCandidatoPorValidar(long idTestimonio, long idCandidato) {

    	TestimonioVO testimonio = testimonioFacade.findById(idTestimonio);
    	if (testimonio.getEstatus() != ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion())
    		logger.error("Estatus Invalido ["+ testimonio.getEstatus() +"] del Testimonio");
    	
    	altaRegistroPorValidar(idTestimonio, TIPO_REGISTRO.TESTIMONIO, idCandidato, TIPO_PROPIETARIO.CANDIDATO);
    }

    /* (non-Javadoc)
	 * @see mx.gob.stps.portal.core.autorizacion.service.AutorizacionAppServiceLocal#registraTestimonioEmpresaPorValidar(long,long)
	 */
    public void registraTestimonioEmpresaPorValidar(long idTestimonio, long idEmpresa) {
    	
    	TestimonioVO testimonio = testimonioFacade.findById(idTestimonio);
    	if (testimonio.getEstatus() != ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion())
    		logger.error("Estatus Invalido ["+ testimonio.getEstatus() +"] del Testimonio ["+ idTestimonio +"]");
    	
    	altaRegistroPorValidar(idTestimonio, TIPO_REGISTRO.TESTIMONIO, idEmpresa, TIPO_PROPIETARIO.EMPRESA);
    }

    /* (non-Javadoc)
	 * @see mx.gob.stps.portal.core.autorizacion.service.AutorizacionAppServiceLocal#registraVideoCurrPorValidar(long)
	 */
    public void registraVideoCurrPorValidar(long idCandidato) {

    	PerfilLaboralVo perfil = perfilLaboralFacade.find(idCandidato);
    	if (perfil.getEstatusVideoc() != ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion())
    		logger.error("Estatus Invalido ["+ perfil.getEstatusVideoc() +"] del Videocurriculo para el candidato ["+ idCandidato +"]");

    	// Se verifica si existen registros correspondiente a videocurriculos del candidato pendientes a publicar
    	List<Long> ids = registroPorValidarFacade.consultaPorPropietario(TIPO_REGISTRO.VIDEO_CURRICULO,
    			                                                            TIPO_PROPIETARIO.CANDIDATO,
    			                                                            idCandidato,
    			                                                            ESTATUS.PENDIENTE_PUBLICAR);

    	//  Solo se permite un video por candidato, en caso de existir un registro previo se elimina
    	for (long idRegValidar : ids){
    		registroPorValidarFacade.elimina(idRegValidar);
    	}

    	altaRegistroPorValidar(idCandidato, TIPO_REGISTRO.VIDEO_CURRICULO, idCandidato, TIPO_PROPIETARIO.CANDIDATO);
    }

    /**
     * Da de alta un Registro por Validar
     * @param idRegistro identificador del registro (Empresa, Oferta, Testimonio, Video)
     * @param tipoRegistro Tipo de Registro (Empresa, Oferta, Testimonio, Video)
     * @param idPropietario identificador del propietario del registro (Empresa, Oferta)
     * @param tipoPropietario tipo de propietario (Empresa, Oferta)
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private void altaRegistroPorValidar(long idRegistro, TIPO_REGISTRO tipoRegistro, long idPropietario, TIPO_PROPIETARIO tipoPropietario){

    	if (idRegistro<=0) throw new IllegalArgumentException("Identificador de registro invalido");
    	if (idPropietario<=0) throw new IllegalArgumentException("Identificador de Propietario invalido");

    	if (registroPorValidarFacade.existeRegistroPorValidar(tipoRegistro, idRegistro,
    			                                              ESTATUS.PENDIENTE_PUBLICAR, ESTATUS.ASIGNADO_PUBLICADOR, ESTATUS.EN_EDICION_PUBLICADOR)){
//			logger.info("Se han localizado registros por publicar ya en espera de su validacion sobre la entidad "+ 
//                        idRegistro +" de tipo "+ tipoRegistro.getIdTipoRegistro());
    		return;
    	}

    	try{
        	RegistroPorValidarVO registro = new RegistroPorValidarVO();
        	registro.setIdRegistro(idRegistro);
        	registro.setIdTipoRegistro(tipoRegistro.getIdTipoRegistro());
        	registro.setIdPropietario(idPropietario);
        	registro.setIdTipoPropietario(tipoPropietario.getIdTipoPropietario());
        	//registro.setIdUsuario();
        	registro.setFechaAlta(new Date());
        	registro.setEstatus(ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion());
        	registro.setFechaModificacion(null);

        	registroPorValidarFacade.registra(registro);    		

    	}catch(Exception e){
    		e.printStackTrace();
    		logger.error(e); // NO SE PROPAGA LA EXCEPTION YA QUE NO TIENE QUE AFECTAR LOS PROCESOS QUE LO INVOQUEN
    	}
    }

    /* (non-Javadoc)
	 * @see mx.gob.stps.portal.core.autorizacion.service.AutorizacionAppServiceRemote#asignaRegistrosPorValidar(long)
	 */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<RegistroPorValidarVO> asignaRegistrosPorValidar(long idUsuario) throws SQLException, BusinessException {
    	return asignaActualizaRegistrosPorValidar(idUsuario, REGISTROS_TIPO_CONSULTA.ASIGNACION);
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<RegistroPorValidarVO> actualizaRegistrosPorValidar(long idUsuario) throws SQLException, BusinessException {
    	return asignaActualizaRegistrosPorValidar(idUsuario, REGISTROS_TIPO_CONSULTA.ACTUALIZACION);
    }

    private List<RegistroPorValidarVO> asignaActualizaRegistrosPorValidar(long idUsuario, REGISTROS_TIPO_CONSULTA tipoConsulta) throws SQLException, BusinessException {

    	if (idUsuario<=0) throw new IllegalArgumentException("Identificador de usuario invalido");

    	UsuarioVO usuario = usuarioFacade.find(idUsuario);
    	if (usuario==null) throw new IllegalArgumentException("Usuario no localizado ("+ idUsuario +")");

    	if (usuario.getIdPerfil()!=PERFIL.PUBLICADOR.getIdOpcion() && usuario.getIdPerfil() != PERFIL.ADMINISTRADOR_SNETEL.getIdOpcion())
    		throw new BusinessException("No cuenta con los permisos para ingresar al modulo de autorización.");
    	if (usuario.getEstatus() != ESTATUS.ACTIVO.getIdOpcion()) 
    		throw new BusinessException("Usuario no activo ("+ idUsuario +")");
    	List<RegistroPorValidarVO> registros = null;
    	
    	/** SE OBTIENE LA LISTA DE REGISTROS PARA ASIGNARLOS AL USUARIO **/
    	ConexionFactory factory = ConexionFactory.getInstance();
    	Connection conn = null;
    	Context context = null;
    	
    	try {
    		ConnectionWraper wraper = factory.getConnection();
    		conn = wraper.getConnection();
    		context = wraper.getContext();
			
			RegistroPorValidarDAO registroPorValidarDAO = RegistroPorValidarDAO.getInstanceConnectionGlobal(conn);

			if (tipoConsulta == REGISTROS_TIPO_CONSULTA.ASIGNACION){

		    	// Cantidad de registros a consultar para asignar al usuario
		    	ParametroVO parametro = parametroFacade.findById(ID_PARAMETRO_TAM_BLOQUE);
		    	int maxbloque = Utils.parseInt(parametro.getValor());    	

		    	registros = registroPorValidarDAO.consultaRegistrosPorValidar(idUsuario, maxbloque);			

			} else if (tipoConsulta == REGISTROS_TIPO_CONSULTA.ACTUALIZACION){

				registros = registroPorValidarDAO.consultaRegistrosPorValidarAsignados(idUsuario);	
			}
	    	    	
	    	//XXX List<RegistroPorValidarVO> empresasAsociadas = new ArrayList<RegistroPorValidarVO>();
			//List<RegistroPorValidarVO> ofertasAsociadas = new ArrayList<RegistroPorValidarVO>();
			//List<RegistroPorValidarVO> testimoniosAsociados = new ArrayList<RegistroPorValidarVO>();
	    	
	    	/** Se consultan los registros Relacionados a la Empresa u Oferta a traves del identificador de la Empresa
	    	 * Para asignar todos los registros de dicha empresa a un solo Publicador **/
	    	/*if(registros!=null && !registros.isEmpty()){
		    	for (RegistroPorValidarVO registro : registros){

		    		/ *XXX if (registro.getIdTipoRegistro() == TIPO_REGISTRO.EMPRESA.getIdTipoRegistro() ||
		    			registro.getIdTipoRegistro() == TIPO_REGISTRO.OFERTA.getIdTipoRegistro()  ||
		    			registro.getIdTipoRegistro() == TIPO_REGISTRO.TESTIMONIO.getIdTipoRegistro()){
					* /
		    		if (registro.getIdTipoRegistro() == TIPO_REGISTRO.OFERTA.getIdTipoRegistro()  ||
			    		registro.getIdTipoRegistro() == TIPO_REGISTRO.TESTIMONIO.getIdTipoRegistro()){
		    		
		    			// Consulta los registro de Empresa que correspondan a la misma Empresa
		    			/ *XXX List<RegistroPorValidarVO> empresasAux = registroPorValidarDAO.consultaEmpresasPorValidar(registro.getIdPropietario());

		    			if (empresasAux!=null && !empresasAux.isEmpty()){
		    				empresasAsociadas.addAll(empresasAux);
		            	}
								* /
		    			// Consulta las Ofertas que pertenezcan a la misma Empresa
		    			List<RegistroPorValidarVO> ofertasAux = registroPorValidarDAO.consultaOfertasPorValidar(registro.getIdPropietario());
		    			
		            	if (ofertasAux!=null && !ofertasAux.isEmpty()){
		            		ofertasAsociadas.addAll(ofertasAux);
		            	}
		            	
		            	// Consulta los Testimonios que pertenezcan a la misma Empresa
		            	List<RegistroPorValidarVO> testimoniosAux = registroPorValidarDAO.consultaTestimonioEmpPorValidar(registro.getIdPropietario());
		            	if (testimoniosAux!=null && !testimoniosAux.isEmpty()){
		            		testimoniosAsociados.addAll(testimoniosAux);
		            	}
		            	
		    		} // if
		    	}// for	    			    		
	    	}*/

	    	
	    	// Se agregan los registros asociados a Empresas y Ofertas
	    	/* XXX
			if (empresasAsociadas!=null && !empresasAsociadas.isEmpty()){
				registros.addAll(empresasAsociadas);
	    	}
	    	*/
			/*if (ofertasAsociadas!=null && !ofertasAsociadas.isEmpty()){
				registros.addAll(ofertasAsociadas);
	    	}
			if (testimoniosAsociados!=null && !testimoniosAsociados.isEmpty()){
				registros.addAll(testimoniosAsociados);
	    	}*/
	    	
	    	//registros = filtraRegistrosDuplicados(registros);
	    	
	    	/** Los registros se deben ordenar por fecha de modificación de manera ascendente, 
	    	 * asignando así primero los registros más antiguos **/
	    	Collections.sort(registros, new Comparator<RegistroPorValidarVO>(){
	    		                            @Override
	    		                            public int compare(RegistroPorValidarVO o1, RegistroPorValidarVO o2) {
	    		                            	return Long.valueOf(o1.getIdRegValidar()).compareTo(o2.getIdRegValidar());
	    		                            }
	    	});

	    	/** Se bloquea el registro asignandoselo al usuario **/
	    	for (RegistroPorValidarVO registro : registros){

	    		registroPorValidarDAO.actualizaRegistroAsignado(registro.getIdRegValidar(),
	    														idUsuario,
	                                                            ESTATUS.ASIGNADO_PUBLICADOR);
	    	}

    	} catch (SQLException e1) {
    		e1.printStackTrace();
			logger.error(e1); throw e1;
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1); throw new SQLException(e1);
		} finally {
			try{if (conn!=null) conn.close();}catch(Exception e){e.printStackTrace();}
			
			if (context!=null){
				try {
					context.close();
				} catch (Exception e) {e.printStackTrace();}
			}
		}

    	return registros;
    }
    
    /**
     * Filtra los registros con identificadores duplicados
     * @param registros
     * @return
     */
    private List<RegistroPorValidarVO> filtraRegistrosDuplicados(List<RegistroPorValidarVO> registros){
    	
    	HashMap<Long, RegistroPorValidarVO> map = new HashMap<Long, RegistroPorValidarVO>();
    	
    	for (RegistroPorValidarVO registro : registros){
    		
    		// Solo agrega los registros que no se encuentren contenidos
    		if (!map.containsKey(registro.getIdRegValidar())){
    			map.put(registro.getIdRegValidar(), registro);
    		}
    	}

    	return new ArrayList<RegistroPorValidarVO>(map.values());
    }
    
    /* (non-Javadoc)
	 * @see mx.gob.stps.portal.core.autorizacion.service.AutorizacionAppServiceRemote#estableceRegistroEnRevision(long)
	 */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void estableceRegistroEnRevision(long idRegValidar, long idUsuario) throws PersistenceException {
    	if (idRegValidar<=0) throw new IllegalArgumentException("Identificador de registro por validar");

    	UsuarioVO usuario = usuarioFacade.find(idUsuario);
    	if (usuario==null) throw new IllegalArgumentException("Usuario no localizado ("+ idUsuario +")");

    	if (usuario.getIdPerfil()!=PERFIL.PUBLICADOR.getIdOpcion()) {
    		logger.info("Usuario no Publicador, no se asignara el registro a revisar");
    		return; // Si no es un Publicador no realiza la asignacion
    	}

    	registroPorValidarFacade.actualizaEstatus(idRegValidar, ESTATUS.EN_EDICION_PUBLICADOR, idUsuario);
    	//logger.info("El registro a validar ["+ idRegValidar +"] se a actualizado En Edicion por el Publicador");
    }

    /* (non-Javadoc)
	 * @see mx.gob.stps.portal.core.autorizacion.service.AutorizacionAppServiceRemote#autorizaRegistro(long, long)
	 */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void autorizaRegistro(long idRegValidar, long idUsuario) throws PersistenceException, BusinessException, TechnicalException, MailException, IndexerException {

    	if (idRegValidar<=0) throw new IllegalArgumentException("Identificador de registro por validar");
    	//logger.info("Autorizacion de Registro por validar ["+ idRegValidar +"] por el Publicador ["+ idUsuario +"]");
    	Date fechaModificacion = new Date();
    	
    	RegistroPorValidarVO registro = registroPorValidarFacade.find(idRegValidar);
    	if (registro==null) throw new BusinessException("Registro no localizado");
    	/*
    	if (TIPO_REGISTRO.EMPRESA.getIdTipoRegistro() == registro.getIdTipoRegistro()){

    		long idEmpresa = registro.getIdRegistro();

    		TIPO_EMPRESA tipoEmpresa = verificaTipoEmpresa(idRegValidar);

    		if (tipoEmpresa == TIPO_EMPRESA.EMPRESA_POR_AUTORIZAR){

    			autorizaEmpresaPorAutorizar(idEmpresa, idUsuario, null, idRegValidar);

    		} else if (tipoEmpresa == TIPO_EMPRESA.EMPRESA){

    			autorizaEmpresa(idEmpresa, idUsuario, null, idRegValidar);
    		}

    	} else*/
    	if (TIPO_REGISTRO.OFERTA.getIdTipoRegistro() == registro.getIdTipoRegistro()){

    		long idOfertaEmpleo = registro.getIdRegistro();
    		//logger.info("Autorizacion de Oferta ["+ idOfertaEmpleo +"]");
    		autorizaOfertaEmpleo(idOfertaEmpleo, idUsuario, idRegValidar);
    		
    	} else if (TIPO_REGISTRO.TESTIMONIO.getIdTipoRegistro() == registro.getIdTipoRegistro()){
    		
    		long idTestimonio = registro.getIdRegistro();
    		//logger.info("Autorizacion de Testimonio ["+ idTestimonio +"]");
    		
    		TestimonioVO testimonio = testimonioFacade.findById(idTestimonio);
    		int estatusAnterior = testimonioFacade.actualizaEstatus(idTestimonio, ESTATUS.ACTIVO.getIdOpcion()); // A13

    		registroPorValidarFacade.actualizaEstatus(idRegValidar, ESTATUS.ACEPTADA, fechaModificacion, idUsuario);
    		
			String idDetalle = "idTestimonio="+ idTestimonio;
			bitacoraEstatus(EVENTO.AUTORIZA_REGISTRO, idUsuario, EVENTO.AUTORIZA_REGISTRO.getEvento(), 
					        TIPO_REGISTRO.TESTIMONIO, idTestimonio, estatusAnterior, idDetalle,
					        Long.valueOf(testimonio.getIdTipoPropietario()).intValue());

    	} else if (TIPO_REGISTRO.VIDEO_CURRICULO.getIdTipoRegistro() == registro.getIdTipoRegistro()){

    		long idCandidato = registro.getIdRegistro();
    		//logger.info("Autorizacion de Video Curriculo para el Candidato ["+ idCandidato +"]");
    		
    		PerfilLaboralVo perfilLaboral = perfilLaboralFacade.find(idCandidato);
    		perfilLaboralFacade.actualizarEstatus(idCandidato, ESTATUS.ACTIVO.getIdOpcion()); // A11

    		registroPorValidarFacade.actualizaEstatus(idRegValidar, ESTATUS.ACEPTADA, fechaModificacion, idUsuario);

			bitacoraEstatus(EVENTO.AUTORIZA_REGISTRO, idUsuario, TIPO_REGISTRO.VIDEO_CURRICULO, idCandidato, perfilLaboral.getEstatusVideoc());
    	}

    }

    public void autorizaOfertaEmpleo(long idOfertaEmpleo, long idUsuario, long idRegValidar) throws TechnicalException, IndexerException {
    	//logger.info("Autorizacion de Oferta de empleo ["+ idOfertaEmpleo +"] idRegValidar ["+ idRegValidar +"]");

    	int estatusAnterior = ofertaEmpleoFacade.consultaEstatus(idOfertaEmpleo);
    	
    	OfertaEmpleoVO ofertaVO  = ofertaEmpleoFacade.findById(idOfertaEmpleo);
    	
    	int idVigenciaOferta = ofertaVO.getIdVigenciaOferta();
    	
    	
    	
    	if(idVigenciaOferta>0){
	    	Date fechaFin = new Date();
	    		
	    	Calendar fechaFinVigencia = Calendar.getInstance();
	    	
	    	fechaFinVigencia.add(Calendar.DATE, VIGENCIA_OFERTA.getDias(idVigenciaOferta));
	    	
	    	fechaFin=fechaFinVigencia.getTime();
	    	
	    	if(Constantes.CATALOGO_FUENTE_QUE_REGISTRA_OFERTA.ABRIENDO_ESPACIOS.getIdOpcion() == ofertaVO.getFuente()){
	    		ofertaEmpleoFacade.autorizaOfertaEmpleo(idOfertaEmpleo, ESTATUS.ABRIENDO_ESPACIOS_ACTIVO.getIdOpcion(),fechaFin);
	    	} else {
	    		ofertaEmpleoFacade.autorizaOfertaEmpleo(idOfertaEmpleo, ESTATUS.ACTIVO.getIdOpcion(),fechaFin);
	    	}
    	
    	}else{
    		
	    	if(Constantes.CATALOGO_FUENTE_QUE_REGISTRA_OFERTA.ABRIENDO_ESPACIOS.getIdOpcion() == ofertaVO.getFuente()){
	    		ofertaEmpleoFacade.actualizaEstatus(idOfertaEmpleo, ESTATUS.ABRIENDO_ESPACIOS_ACTIVO.getIdOpcion());
	    	} else {
	    		ofertaEmpleoFacade.actualizaEstatus(idOfertaEmpleo, ESTATUS.ACTIVO.getIdOpcion());
	    	}    		
    		
    	}
		 // A9

		bitacoraEstatus(EVENTO.AUTORIZA_REGISTRO, idUsuario, TIPO_REGISTRO.OFERTA, idOfertaEmpleo, estatusAnterior);

		Date fechaModificacion = new Date();
		
		// En caso que un Publicador modifique una oferta, al guardar se autoriza por lo que no pasa a la cola de espera y no se cuenta con este dato
		if (idRegValidar>0){
			registroPorValidarFacade.actualizaEstatus(idRegValidar, ESTATUS.ACEPTADA, fechaModificacion, idUsuario);
			//logger.info("Autorizacion de Registro por Validar ["+ idRegValidar +"] de Oferta de empleo ["+ idOfertaEmpleo +"] ");
		} else {
			// Se obtienen los registros por autorizar correspondientes a la oferta de empleo
			registroPorValidarFacade.actualizaEstatusPorRegistro(ESTATUS.ACEPTADA, fechaModificacion,
					                                             TIPO_REGISTRO.OFERTA, idOfertaEmpleo, 
					                                             ESTATUS.ASIGNADO_PUBLICADOR, ESTATUS.EN_EDICION_PUBLICADOR, idUsuario);
			
			//logger.info("Autorizacion de Registro por Validar por Oferta de empleo ["+ idOfertaEmpleo +"]");
		}
//FIXME OracleText
/*
		try {
			IndexerServiceLocator.getIndexerServiceRemote().indexaOfertaEmpleo(idOfertaEmpleo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e); 
			throw new IndexerException(e);
		}
 */
    }
    /* XXX
    public void autorizaEmpresaPorAutorizar(long idEmpresa, long idUsuario, String detalle, long idRegValidar) throws BusinessException, MailException, TechnicalException {
    	
		EmpresaPorAutorizarVO empresaPorAutorizar = empresaPorAutorizarFacade.findById(idEmpresa);
		logger.info("Autorizacion de Empresa por Autorizar ["+ idEmpresa +"]");
		
		empresaPorAutorizarFacade.actualizaEstatus(idEmpresa, ESTATUS.PREVALIDADA.getIdOpcion());
		bitacoraEstatus(EVENTO.AUTORIZA_REGISTRO, idUsuario, TIPO_REGISTRO.EMPRESA, idEmpresa, empresaPorAutorizar.getEstatus());
		empresaPorAutorizar.setEstatus(ESTATUS.PREVALIDADA.getIdOpcion());

		Date fechaModificacion = new Date();
		
		// En caso que un Publicador modifique una empresa, al guardar se autoriza por lo que no pasa a la cola de espera y no se cuenta con este dato
		if (idRegValidar>0){
			registroPorValidarFacade.actualizaEstatus(idRegValidar, ESTATUS.ACEPTADA, fechaModificacion, idUsuario);
			logger.info("Autorizacion de Registro por Validar ["+ idRegValidar +"] de Empresa por Autorizar ["+ idEmpresa +"] ");
		} else {
			// Se obtiene el registro por autorizar correspondientes a la empresa
			registroPorValidarFacade.actualizaEstatusPorRegistro(ESTATUS.ACEPTADA, fechaModificacion,
					                                             TIPO_REGISTRO.EMPRESA, idEmpresa,
					                                             ESTATUS.ASIGNADO_PUBLICADOR, ESTATUS.EN_EDICION_PUBLICADOR, idUsuario);
			
			logger.info("Autorizacion de Registro por Validar por Empresa por Autorizar ["+ idEmpresa +"]");
		}
		
		// A3 Caso de uso - Actualizar cuenta de correo
		empresaAppService.notificaEmpresaPorAutorizar(empresaPorAutorizar, idUsuario);	
    }
	*/
    
  
    public void autorizaEmpresa(long idEmpresa, long idUsuario, String detalle, long idRegValidar) throws BusinessException, MailException, TechnicalException {
    	
		EmpresaVO empresa = empresaFacade.findById(idEmpresa);
		//logger.info("Autorizacion de Empresa ["+ idEmpresa +"]");
		
		empresaFacade.actualizaEstatus(idEmpresa, ESTATUS.PREVALIDADA.getIdOpcion()); // A4
		bitacoraEstatus(EVENTO.AUTORIZA_REGISTRO, idUsuario, TIPO_REGISTRO.EMPRESA, idEmpresa, empresa.getEstatus()); 
		empresa.setEstatus(ESTATUS.PREVALIDADA.getIdOpcion());	
		empresaFacade.actualizaFechaConfirma(idEmpresa, new Date());
		
		List<OfertaEmpleoVO> ofertas = ofertaEmpleoFacade.consultaOfertasEmpleo(idEmpresa, 
				                                                                ESTATUS.EMP_MODIFICADA.getIdOpcion());
		//logger.info("Empresa cuenta con "+ ofertas.size() +" ofertas de empleo");
		Date fechaModificacion = new Date();
		
		for (OfertaEmpleoVO oferta : ofertas){

			ofertaEmpleoFacade.actualizaEstatus(oferta.getIdOfertaEmpleo(), ESTATUS.ACTIVO.getIdOpcion());
			bitacoraEstatus(EVENTO.AUTORIZA_REGISTRO, idUsuario, TIPO_REGISTRO.OFERTA, oferta.getIdOfertaEmpleo(), oferta.getEstatus());

    		registroPorValidarFacade.actualizaEstatusPorRegistro(ESTATUS.ACEPTADA, fechaModificacion, 
					 											 TIPO_REGISTRO.OFERTA, oferta.getIdOfertaEmpleo(), 
					 											 ESTATUS.PENDIENTE_PUBLICAR, ESTATUS.ASIGNADO_PUBLICADOR, 
					 											 //ESTATUS.EN_EDICION_PUBLICADOR, 
					 											 idUsuario);
		}
		
		// En caso que un Publicador modifique una empresa, al guardar se autoriza por lo que no pasa a la cola de espera y no se cuenta con este dato
		/* XXX  ya no es necesario porque es una empresa recuperada
		if (idRegValidar>0){
			registroPorValidarFacade.actualizaEstatus(idRegValidar, ESTATUS.ACEPTADA, fechaModificacion, idUsuario);
			logger.info("Autorizacion de Registro por Validar ["+ idRegValidar +"] de Empresa ["+ idEmpresa +"] ");
		} else {
			// Se obtiene el registro por autorizar correspondientes a la empresa
			registroPorValidarFacade.actualizaEstatusPorRegistro(ESTATUS.ACEPTADA, fechaModificacion,
					                                             TIPO_REGISTRO.EMPRESA, idEmpresa,
					                                             ESTATUS.ASIGNADO_PUBLICADOR, ESTATUS.EN_EDICION_PUBLICADOR, idUsuario);
			
			logger.info("Autorizacion de Registro por Validar por Empresa ["+ idEmpresa +"]");
		}
		*/
		
		// A3 Caso de uso - Actualizar cuenta de correo
		empresaAppService.notificaEmpresa(empresa, idUsuario, detalle);
    }
	

    /* XXX
    public void autorizaEmpresaModificada(long idEmpresa, long idUsuario, long idRegValidar) {

		EmpresaVO empresa = empresaFacade.findById(idEmpresa);
		logger.info("Autorizacion de Empresa modificada ["+ idEmpresa +"]");

		//empresaFacade.actualizaEstatus(idEmpresa, ESTATUS.ACTIVO.getIdOpcion()); // A4
		empresaFacade.actualizaEstatus(idEmpresa, ESTATUS.MODIFICADA.getIdOpcion());
		bitacoraEstatus(EVENTO.AUTORIZA_REGISTRO, idUsuario, TIPO_REGISTRO.EMPRESA, idEmpresa, empresa.getEstatus()); 
		empresa.setEstatus(ESTATUS.ACTIVO.getIdOpcion());

		List<OfertaEmpleoVO> ofertas = ofertaEmpleoFacade.consultaOfertasEmpleo(idEmpresa, 
				                                                                ESTATUS.EMP_MODIFICADA.getIdOpcion());
		logger.info("Empresa cuenta con "+ ofertas.size() +" ofertas de empleo");
		Date fechaModificacion = new Date();

		for (OfertaEmpleoVO oferta : ofertas){

			ofertaEmpleoFacade.actualizaEstatus(oferta.getIdOfertaEmpleo(), ESTATUS.ACTIVO.getIdOpcion());

			bitacoraEstatus(EVENTO.AUTORIZA_REGISTRO, idUsuario, TIPO_REGISTRO.OFERTA, oferta.getIdOfertaEmpleo(), oferta.getEstatus());

    		registroPorValidarFacade.actualizaEstatusPorRegistro(ESTATUS.ACEPTADA, fechaModificacion, 
    															 TIPO_REGISTRO.OFERTA, oferta.getIdOfertaEmpleo(), 
    															 ESTATUS.PENDIENTE_PUBLICAR, ESTATUS.ASIGNADO_PUBLICADOR, 
    															 //ESTATUS.EN_EDICION_PUBLICADOR, 
    															 idUsuario);
		}

		// En caso que un Publicador modifique una empresa, al guardar se autoriza por lo que no pasa a la cola de espera y no se cuenta con este dato
		if (idRegValidar>0){
			registroPorValidarFacade.actualizaEstatus(idRegValidar, ESTATUS.ACEPTADA, fechaModificacion, idUsuario);
			logger.info("Autorizacion de Registro por Validar ["+ idRegValidar +"] de Empresa Modificada ["+ idEmpresa +"] ");
		} else {
			// Se obtiene el registro por autorizar correspondientes a la empresa
			registroPorValidarFacade.actualizaEstatusPorRegistro(ESTATUS.ACEPTADA, fechaModificacion,
					                                             TIPO_REGISTRO.EMPRESA, idEmpresa,
					                                             ESTATUS.ASIGNADO_PUBLICADOR, ESTATUS.EN_EDICION_PUBLICADOR, idUsuario);
			
			logger.info("Autorizacion de Registro por Validar por Empresa Modificada ["+ idEmpresa +"]");
		}

		try {
			// A3 Caso de uso - Actualizar cuenta de correo
			String detalle = empresa.getCorreoElectronico();
			empresaAppService.notificaEmpresa(empresa, idUsuario, detalle);
		} catch (Exception e) {
			e.printStackTrace(); logger.error(e);
		}
    }
    */

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void rechazaRegistro(long idRegValidar, long idMotivoRechazo, String detalleRechazo, long idUsuario) throws BusinessException {

    	if (idRegValidar<=0) throw new IllegalArgumentException("Identificador de registro por validar");
    	
    	RegistroPorValidarVO registro = registroPorValidarFacade.find(idRegValidar);
    	
    	if (registro==null) throw new BusinessException("Registro no localizado");
    	
    	Date fechaModificacion = new Date();
    	/* XXX
    	if (TIPO_REGISTRO.EMPRESA.getIdTipoRegistro() == registro.getIdTipoRegistro()){
        	
    		AutorizacionAssembler assembler = AutorizacionAssembler.getInstance();
    		
    		long idEmpresa = registro.getIdRegistro();
    		
    		TIPO_EMPRESA tipoEmpresa = verificaTipoEmpresa(idRegValidar);

    		if (tipoEmpresa == TIPO_EMPRESA.EMPRESA_POR_AUTORIZAR){
    			
    			EmpresaPorAutorizarVO empresaPorAutorizar = empresaPorAutorizarFacade.findById(idEmpresa);

        		if (idMotivoRechazo == VALIDACION_MOTIVO.RECHAZO_EMPRESA_FRAUDULENTA.getIdMotivo()){

        			logger.info("Empresa por Autorizar Fraudulenta ["+ idEmpresa +"]");
        	    	EmpresaFraudulentaVO empresaFraudulenta = assembler.creaEmpresaFraudulentaVO(empresaPorAutorizar); // A6
        	    	empresaFraudulentaFacade.save(empresaFraudulenta);
        	    	logger.info("Empresa por Autorizar fraudulenta registrada ["+ idEmpresa +"]");
        	    	
        	    	empresaPorAutorizarFacade.delete(idEmpresa); // Nota 7
        	    	logger.info("Empresa por Autorizar reportada como fraudulenta eliminada ["+ idEmpresa +"]");
        			
        		} else {
        			
        			logger.info("Rechazo de Empresa por Autorizar ["+ idEmpresa +"]");
        			empresaPorAutorizarFacade.actualizaEstatus(idEmpresa, ESTATUS.ELIMINADA_ADMIN.getIdOpcion()); // A7

        			bitacoraEstatus(EVENTO.RECHAZA_REGISTRO, idUsuario, TIPO_REGISTRO.EMPRESA, idEmpresa, empresaPorAutorizar.getEstatus());
        		}

        		registroPorValidarFacade.actualizaEstatus(idRegValidar, ESTATUS.RECHAZADA, fechaModificacion, idUsuario);
        		
    		} else if (tipoEmpresa == TIPO_EMPRESA.EMPRESA){

    			EmpresaVO empresa = empresaFacade.findById(idEmpresa);
    			
    			if (idMotivoRechazo == VALIDACION_MOTIVO.RECHAZO_EMPRESA_FRAUDULENTA.getIdMotivo()){

    				logger.info("Rechazo de Empresa Fraudulenta ["+ idEmpresa +"]");
    				
    		    	EmpresaFraudulentaVO empresaFraudulenta = assembler.creaEmpresaFraudulentaVO(empresa); // A6
    		    	empresaFraudulentaFacade.save(empresaFraudulenta);
    		    	logger.info("Empresa fraudulenta registrada ["+ idEmpresa +"]");

    				List<OfertaEmpleoVO> ofertas = ofertaEmpleoFacade.ofertaEmpleosList(idEmpresa);

    				logger.info("Empresa cuenta con "+ ofertas.size() +" ofertas de empleo");
    				
    				for (OfertaEmpleoVO oferta : ofertas){
    					int estatusOfertaAnterior = oferta.getEstatus();
    					
    					oferta.setEstatus(ESTATUS.ELIMINADA_EMP_FRAUDULENTA.getIdOpcion());

    					ofertaEmpleoFraudulentaFacade.save(oferta); // A6
    					ofertaEmpleoFacade.remove(oferta.getIdOfertaEmpleo()); // Nota 7
    					
    					bitacoraEstatus(EVENTO.RECHAZA_REGISTRO, idUsuario, TIPO_REGISTRO.OFERTA, oferta.getIdOfertaEmpleo(), estatusOfertaAnterior);

    		    		entrevistaFacade.cancelaEntrevistas(oferta.getIdOfertaEmpleo(), ESTATUS.CANCELADA, fechaModificacion, 
	                                                        ESTATUS.NUEVA, ESTATUS.ACEPTADA, ESTATUS.REPROGRAMADA); // RN05
    				}
    				
    				empresaFacade.delete(idEmpresa); // Nota 7
    				usuarioFacade.inactivarUsuario(empresa.getIdUsuario());
    				
    				logger.info("Empresa reportada como fraudulenta eliminada ["+ idEmpresa +"]");
    				
        		} else {
        			// A8

        			logger.info("Rechazo de Empresa ["+ idEmpresa +"]");
            		empresaFacade.actualizaEstatus(idEmpresa, ESTATUS.ELIMINADA_ADMIN.getIdOpcion());
            		usuarioFacade.inactivarUsuario(empresa.getIdUsuario());
            		
            		bitacoraEstatus(EVENTO.RECHAZA_REGISTRO, idUsuario, TIPO_REGISTRO.EMPRESA, idEmpresa, empresa.getEstatus());

            		List<OfertaEmpleoVO> ofertas = ofertaEmpleoFacade.consultaOfertasEmpleo(idEmpresa, 
            				                                                                ESTATUS.ACTIVO.getIdOpcion(),
            				                                                                ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion(),
            				                                                                ESTATUS.ASIGNADO_PUBLICADOR.getIdOpcion(),
            				                                                                ESTATUS.EN_EDICION_PUBLICADOR.getIdOpcion());

            		logger.info("Empresa cuenta con "+ ofertas.size() +" ofertas de empleo");

            		for (OfertaEmpleoVO oferta : ofertas){

            			ofertaEmpleoFacade.actualizaEstatus(oferta.getIdOfertaEmpleo(), ESTATUS.ELIMINADA_ADMIN.getIdOpcion());
            			
            			bitacoraEstatus(EVENTO.RECHAZA_REGISTRO, idUsuario, TIPO_REGISTRO.OFERTA, oferta.getIdOfertaEmpleo(), oferta.getEstatus());

                		registroPorValidarFacade.actualizaEstatusPorRegistro(ESTATUS.ELIMINADA_ADMIN, fechaModificacion, 
                															 TIPO_REGISTRO.OFERTA, oferta.getIdOfertaEmpleo(), 
                															 ESTATUS.PENDIENTE_PUBLICAR, ESTATUS.ASIGNADO_PUBLICADOR, 
                															 //ESTATUS.EN_EDICION_PUBLICADOR, 
                															 idUsuario);
            		}

            		// Se cancelan los Registros por Validar asociados al propietario
            		registroPorValidarFacade.actualizaEstatusPorPropietario(idEmpresa,
            																ESTATUS.ELIMINADA_ADMIN, fechaModificacion,
            				                                                ESTATUS.PENDIENTE_PUBLICAR, ESTATUS.ASIGNADO_PUBLICADOR, ESTATUS.EN_EDICION_PUBLICADOR,
            				                                                idUsuario);
        		}
    			
    			registroPorValidarFacade.actualizaEstatus(idRegValidar, ESTATUS.RECHAZADA, fechaModificacion, idUsuario);
    		}

    	} else
    		*/ 
    	if (TIPO_REGISTRO.OFERTA.getIdTipoRegistro() == registro.getIdTipoRegistro()){

    		long idOfertaEmpleo = registro.getIdRegistro();
    		//logger.info("Rechazo de Oferta ["+ idOfertaEmpleo +"]");
    		
    		OfertaEmpleoVO ofertaEmpleo = ofertaEmpleoFacade.findById(idOfertaEmpleo);
    		ofertaEmpleoFacade.actualizaEstatus(idOfertaEmpleo, ESTATUS.ELIMINADA_ADMIN.getIdOpcion()); // A10

    		registroPorValidarFacade.actualizaEstatus(idRegValidar, ESTATUS.RECHAZADA, fechaModificacion, idUsuario);
    		
    		entrevistaFacade.cancelaEntrevistas(idOfertaEmpleo, ESTATUS.CANCELADA, fechaModificacion, 
    				                            ESTATUS.NUEVA, ESTATUS.ACEPTADA, ESTATUS.REPROGRAMADA); // RN05

    		bitacoraEstatus(EVENTO.RECHAZA_REGISTRO, idUsuario, TIPO_REGISTRO.OFERTA, idOfertaEmpleo, ofertaEmpleo.getEstatus());
//FIXME OracleText
/*
    		try {
    			IndexerServiceLocator.getIndexerServiceRemote().eliminaOfertaindice(idOfertaEmpleo);
    		} catch (Exception e){
    			e.printStackTrace(); logger.error(e);
    		}
 */
    	} else if (TIPO_REGISTRO.TESTIMONIO.getIdTipoRegistro() == registro.getIdTipoRegistro()){

    		long idTestimonio = registro.getIdRegistro();
    		//logger.info("Rechazo de Testimonio ["+ idTestimonio +"]");
    		
    		TestimonioVO testimonio = testimonioFacade.findById(idTestimonio);
    		testimonioFacade.actualizaEstatus(idTestimonio, ESTATUS.ELIMINADA_ADMIN.getIdOpcion()); // A14

    		registroPorValidarFacade.actualizaEstatus(idRegValidar, ESTATUS.RECHAZADA, fechaModificacion, idUsuario);
    		
			String idDetalle = "idTestimonio="+ idTestimonio;
			bitacoraEstatus(EVENTO.RECHAZA_REGISTRO, idUsuario, EVENTO.RECHAZA_REGISTRO.getEvento(), 
					        TIPO_REGISTRO.TESTIMONIO, idTestimonio, testimonio.getEstatus(),
					        idDetalle, Long.valueOf(testimonio.getIdTipoPropietario()).intValue());
    		
    	} else if (TIPO_REGISTRO.VIDEO_CURRICULO.getIdTipoRegistro() == registro.getIdTipoRegistro()){

    		long idCandidato = registro.getIdRegistro();
    		//logger.info("Rechazo de Video Curriculo del Candidato ["+ idCandidato +"]");
    		
    		PerfilLaboralVo perfilLaboral = perfilLaboralFacade.find(idCandidato);
    		perfilLaboralFacade.actualizarEstatus(idCandidato, ESTATUS.ELIMINADA_ADMIN.getIdOpcion()); // A12

    		registroPorValidarFacade.actualizaEstatus(idRegValidar, ESTATUS.RECHAZADA, fechaModificacion, idUsuario);
    		
    		bitacoraEstatus(EVENTO.RECHAZA_REGISTRO, idUsuario, TIPO_REGISTRO.VIDEO_CURRICULO, idCandidato, perfilLaboral.getEstatusVideoc());
    	}

    	if (idMotivoRechazo>0){
    		registroPorValidarFacade.actualizaMotivoRechazo(idRegValidar, idMotivoRechazo, detalleRechazo);
    	}
    }

//    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
//    public void actualizaEstatusPorRegistroOfertaEmpleo(long idOfertaEmpleo, ESTATUS estatus, long idUsuario){
//		try{
//			EVENTO evento = null;
//			ESTATUS estatusBit = null;
//			
//			if (ESTATUS.ACTIVO == estatus){
//				evento = EVENTO.AUTORIZA_REGISTRO;
//				estatusBit = ESTATUS.ACEPTADA;
//			} else if (ESTATUS.CANCELADA == estatus || ESTATUS.ELIMINADA_EMP == estatus || ESTATUS.ELIMINADA_ADMIN == estatus){
//				evento = EVENTO.RECHAZA_REGISTRO;
//				estatusBit = ESTATUS.RECHAZADA;
//			} else {
//				estatusBit = estatus;
//				evento = EVENTO.RECHAZA_REGISTRO; // hay que verificar que esten soportados todos los estatus que maneja la oferta
//			}
//
//			Date fechaModificacion = new Date();
//			registroPorValidarFacade.actualizaEstatusPorRegistro(estatusBit, fechaModificacion, 
//					                                             TIPO_REGISTRO.OFERTA, 
//					                                             idOfertaEmpleo, 
//					                                             ESTATUS.PENDIENTE_PUBLICAR, ESTATUS.ASIGNADO_PUBLICADOR, 
//					                                             ESTATUS.EN_EDICION_PUBLICADOR, 
//					                                             idUsuario);
//			//FIXME:reindexa
//			/**/
//			if (ESTATUS.ACTIVO == estatus){ 
//				IndexerServiceLocator.getIndexadorServiceRemote().indexaOfertaEmpleo(idOfertaEmpleo);
//			}	
//			bitacoraEstatus(evento, idUsuario, TIPO_REGISTRO.OFERTA, idOfertaEmpleo, estatus.getIdOpcion());
//
//		}catch(Exception e){
//			e.printStackTrace();
//			logger.error(e);
//		}
//	}

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void cancelaValidacionRegistro(long idRegValidar, long idUsuario) throws PersistenceException {

    	if (idRegValidar<=0) throw new IllegalArgumentException("Identificador de registro por validar");

    	// En el caso de cancelar el proceso de validacion, se regresa el registro a su estado original sin usuario asignado
    	registroPorValidarFacade.actualizaEstatus(idRegValidar, ESTATUS.ASIGNADO_PUBLICADOR, idUsuario);
    }

    /* (non-Javadoc)
	 * @see mx.gob.stps.portal.core.autorizacion.service.AutorizacionAppServiceRemote#desasignaRegistrosPorValidar(long)
	 */
    public void desasignaRegistrosPorValidar(long idUsuario) throws SQLException {

    	if (idUsuario<=0) throw new IllegalArgumentException("Identificador de usuario invalido");
    	
    	//logger.info("Se desasignan los registros pendientes a validar del Publicador ["+ idUsuario +"]");
    	
    	RegistroPorValidarDAO dao = RegistroPorValidarDAO.getInstance();
    	dao.actualizaRegistrosAsignados(ESTATUS.PENDIENTE_PUBLICAR,
    			                        idUsuario,
    			                        ESTATUS.ASIGNADO_PUBLICADOR, ESTATUS.EN_EDICION_PUBLICADOR);
    }

    public List<EmpresaFraudulentaVO> consultaEmpresasFraudulentasSimilares(long idEmpresa) throws SQLException {
    	List<EmpresaFraudulentaVO> empresasFraudulentas = null;

    	if (idEmpresa<=0) throw new IllegalArgumentException("Identificador del empresa requerido");

    	Connection conn = null;
    	Context context = null;
    	
    	try{
    		ConnectionWraper wraper = ConexionFactory.getInstance().getConnection();
    		conn = wraper.getConnection();
    		context = wraper.getContext();
        	
        	EmpresaFraudulentaDAO dao = EmpresaFraudulentaDAO.getInstance(conn);
        	empresasFraudulentas = dao.consultaEmpresasFraudulentasSimilares(idEmpresa); // A5
    		
    	} catch(SQLException e){
    		e.printStackTrace(); logger.error(e); throw e;
    	} catch(Exception e){
    		e.printStackTrace(); logger.error(e); throw new SQLException(e);
    	} finally {
    		try{if (conn!=null) conn.close();}catch(Exception e){e.printStackTrace();}
    		
    		if (context!=null){
				try {
					context.close();
				} catch (Exception e) {e.printStackTrace();}
			}
    	}
    	
    	return empresasFraudulentas;
    }
    
    private void bitacoraEstatus(EVENTO evento, long idUsuario, TIPO_REGISTRO tipoRegistro, long idRegistro, int estatusAnterior){

    	TIPO_PROPIETARIO tipoPropietario = null;
    	String idDetalle = null;
    	
    	if (tipoRegistro == TIPO_REGISTRO.EMPRESA){
    		idDetalle = "idEmpresa="+ idRegistro;
    		tipoPropietario = TIPO_PROPIETARIO.EMPRESA;
    	} else if (tipoRegistro == TIPO_REGISTRO.OFERTA){
    		idDetalle = "idOfertaEmpleo="+ idRegistro;
    		tipoPropietario = TIPO_PROPIETARIO.OFERTA;
    	} else if (tipoRegistro == TIPO_REGISTRO.TESTIMONIO){
    		idDetalle = "idTestimonio="+ idRegistro;
    		tipoPropietario = TIPO_PROPIETARIO.CANDIDATO; // tambien puede ser empresa, pero para eso el testimonio invoca el metodo donde se envia como parametro el tipo del propietario
    	} else if (tipoRegistro == TIPO_REGISTRO.VIDEO_CURRICULO){
    		idDetalle = "idCandidato="+ idRegistro;
    		tipoPropietario = TIPO_PROPIETARIO.CANDIDATO;
    	} else {
    		idDetalle = "ERROR SIN TIPO DE REGISTRO";
    		tipoPropietario = TIPO_PROPIETARIO.EMPRESA;    		
    	}

    	bitacoraEstatus(evento, idUsuario, null, tipoRegistro, idRegistro, estatusAnterior, idDetalle, tipoPropietario.getIdTipoPropietario());
    }

    private void bitacoraEstatus(EVENTO evento, long idUsuario, String descripcion, 
    		                     TIPO_REGISTRO tipoRegistro, long idRegistro, int estatusAnterior, String idDetalle, int idTipoPropietario) {    	

    	String detalle = idDetalle + "|estatus="+ estatusAnterior;

    	if (descripcion==null) descripcion = evento.getEvento();

    	bitacoraFacade.save(evento.getIdEvento(), idUsuario, descripcion, Calendar.getInstance(), detalle, idRegistro, idTipoPropietario);
    }
    
    /**
     * Verifica si el registro por Validar es una Empresa por Autorizar o una Empresa modificada
     * @param idRegValidar
     * @return
     * @throws BusinessException
     */
    /*private TIPO_EMPRESA verificaTipoEmpresa(long idRegValidar) throws BusinessException {

    	TipoEmpresaVO tipoEmpresa = null;;
		try {
			tipoEmpresa = TipoEmpresaDAO.getInstance().consultaTipoEmpresa(idRegValidar);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e);
			throw new BusinessException("No se pudo recuperar el tipo de registro de Empresa [Por Autorizar / Modificada]");
		}

    	return tipoEmpresa.getTipoEmpresa();
    }*/

    public List<CatalogoOpcionVO> consultaMotivosRechazo(int intTipoRegistro){
		List<CatalogoOpcionVO> motivos = new ArrayList<CatalogoOpcionVO>();
		
		MotivoRechazoRegistroDAO motivoDao = new MotivoRechazoRegistroDAO();
		
		try {
			motivos = motivoDao.consultaMotivos(intTipoRegistro);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e);
			motivos = new ArrayList<CatalogoOpcionVO>();
		}		
    	
		return motivos;
    }  

    public long[] consultaRequiereDetalle(){
    	long[] reqDetalle = null;
    	MotivoRechazoRegistroDAO motivoDao = new MotivoRechazoRegistroDAO();
    	try {
			reqDetalle = motivoDao.consultaRequiereDetalle();
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e);
		}
    	Arrays.sort(reqDetalle);  	
    	return reqDetalle;
    	
    }
    
	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<PublicadorVO> publishersList() throws PersistenceException {
		Connection conn = null;
		Context context = null;
		List<PublicadorVO> publishers = null;
		List<PublicadorVO> publishersList = new ArrayList<PublicadorVO>();
		try {
    		ConnectionWraper wraper = ConexionFactory.getInstance().getConnection();
    		conn = wraper.getConnection();
    		context = wraper.getContext();
			RegistroPorValidarDAO dao = RegistroPorValidarDAO.getInstanceConnectionGlobal(conn);
			publishers = registroPorValidarFacade.listAssignedUsers();
			for (PublicadorVO vo : publishers) {
				UsuarioVO user = usuarioFacade.find(vo.getIdUsuario());
				if (user.getEstatus() == ESTATUS.ACTIVO.getIdOpcion()) {
					publishersList.add(vo);
					vo.setNombre(user.getNombre());
					vo.setApellido1(user.getApellido1());
					vo.setApellido2(user.getApellido2());
					try {
						List<RegistroPorValidarVO> records = dao.consultaRegistrosPorValidarAsignados(vo.getIdUsuario());
						vo.setEmpresas(groupByType(records, TIPO_REGISTRO.EMPRESA.getIdTipoRegistro()));
						vo.setCompanies(getCompanies(records));
						vo.setOfertas(groupByType(records, TIPO_REGISTRO.OFERTA.getIdTipoRegistro()));
						vo.setOffers(getOffers(records));
						vo.setTestimonios(groupByType(records, TIPO_REGISTRO.TESTIMONIO.getIdTipoRegistro()));
						vo.setTestimonies(getTestimonies(records));
						vo.setVideocv(groupByType(records, TIPO_REGISTRO.VIDEO_CURRICULO.getIdTipoRegistro()));
						vo.setCurriculums(getCVs(records));
					} catch (SQLException e) { e.printStackTrace(); }
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try{if (conn!=null) conn.close();}catch(Exception e){e.printStackTrace();}
			if (context!=null){
				try {
					context.close();
				} catch (Exception e) {e.printStackTrace();}
			}
		}
		return publishersList;
	}
	
	private int groupByType(List<RegistroPorValidarVO> records, int type) {
		List<RegistroPorValidarVO> tmp = new ArrayList<RegistroPorValidarVO>();
		for (RegistroPorValidarVO vo : records) {
			if (type==vo.getIdTipoRegistro())
				tmp.add(vo);
		}
		return tmp.size();
	}
	
	/* XXX */
	private List<EmpresaVO> getCompanies(List<RegistroPorValidarVO> records) {
		EmpresaVO company = null;
		List<EmpresaVO> companies = new ArrayList<EmpresaVO>();
		for (RegistroPorValidarVO vo : records) {
			if (TIPO_REGISTRO.EMPRESA.getIdTipoRegistro()==vo.getIdTipoRegistro()) {
				if (SUBTIPO_REGISTRO.EMPRESA.getIdSubTipoRegistro() == vo.getIdSubTipoRegistro()) {
					company = empresaFacade.findById(vo.getIdRegistro());
				    if (null != company)
				    	companies.add(company);
				}else if (SUBTIPO_REGISTRO.EMPRESA_POR_AUTORIZAR.getIdSubTipoRegistro() == vo.getIdSubTipoRegistro()) {
					EmpresaPorAutorizarVO companyby = empresaPorAutorizarFacade.findById(vo.getIdRegistro());
					if (null != companyby) {
						company = new EmpresaVO();
						company.setIdPortalEmpleo(companyby.getIdPortalEmpleo());
						company.setRazonSocial(companyby.getRazonSocial());
						company.setNombre(companyby.getNombre());
						company.setApellido1(companyby.getApellido1());
						company.setApellido2(companyby.getApellido2());
						companies.add(company);
					}
				}
			}
		}
		return companies;
	}
	
	 
	private List<OfertaEmpleoVO> getOffers(List<RegistroPorValidarVO> records) {
		List<OfertaEmpleoVO> offers = new ArrayList<OfertaEmpleoVO>();
		for (RegistroPorValidarVO vo : records) {
			if (TIPO_REGISTRO.OFERTA.getIdTipoRegistro()==vo.getIdTipoRegistro()) {
				OfertaEmpleoVO offer = ofertaEmpleoFacade.findById(vo.getIdRegistro());
				if (null != offer)
					offers.add(offer);
			}
		}
		return offers;
	}
	
	private List<TestimonioVO> getTestimonies(List<RegistroPorValidarVO> records) {
		List<TestimonioVO> testimonies = new ArrayList<TestimonioVO>();
		for (RegistroPorValidarVO vo : records) {
			if (TIPO_REGISTRO.TESTIMONIO.getIdTipoRegistro()==vo.getIdTipoRegistro()) {
				TestimonioDAO dao = new TestimonioDAO();
				TestimonioVO testimony = testimonioFacade.findById(vo.getIdRegistro());
				try {
					testimony = dao.datosTestimonioIndex(testimony);
				} catch (SQLException e) { e.printStackTrace(); }
				if (null != testimony)
					testimonies.add(testimony);
			}
		}
		return testimonies;
	}
	
	private List<CandidatoVo> getCVs(List<RegistroPorValidarVO> records) {
		List<CandidatoVo> users = new ArrayList<CandidatoVo>();
		for (RegistroPorValidarVO vo : records) {
			if (TIPO_REGISTRO.VIDEO_CURRICULO.getIdTipoRegistro()==vo.getIdTipoRegistro()) {
				CandidatoVo user = candidatoFacade.find(vo.getIdPropietario());
				if (null != user)
					users.add(user);
			}
		}
		return users;
	}    
	
	@Override
	public int totalRegistrosPorValidar() throws PersistenceException {
		RegistroPorValidarDAO dao = RegistroPorValidarDAO.getInstance();
		int total = 0;
		
		try {
			total = dao.consultaTotalRegistrosPorValidar();
		} catch (SQLException e) {
			e.printStackTrace();throw new PersistenceException(e);
		}
		
		//return registroPorValidarFacade.totalRegistrosPorValidar();
		return total;
	}	
	
	@Override
	public List<PublicadorVO> productivityPublishersList(String name, String lastname, String rangeInit, String rangeFinal) throws PersistenceException {
		return registroPorValidarFacade.productivityPublishersList(name, lastname, rangeInit, rangeFinal);
	}
	
	public RegistroPorValidarVO consultaRegistroPorValidar(long idRegValidar){
		
		RegistroPorValidarVO registro = registroPorValidarFacade.find(idRegValidar);
		return registro;

	}
	
	//Dado un idEmpresa, este método marca la empresa y todas sus ofertas con el estatus 28 - Eliminada por empresa fraudulenta
	@TransactionAttribute(TransactionAttributeType.REQUIRED)	
	public void enviarEmpFraudulenta(long idEmpresa, long idUsuario) throws PersistenceException, BusinessException{
		//logger.info("Inicio de enviarEmpFraudulenta");
		
		if (idEmpresa == 0){
			logger.error("idEmpresa no está informado");
			throw new BusinessException("idEmpresa no está informado");
		}

		if (idUsuario == 0){
			logger.error("idUsuario no está informado");
			throw new BusinessException("idUsuario no está informado");
		}

		List<OfertaEmpleoVO> listaOfertas = ofertaEmpleoFacade.ofertaEmpleosList(idEmpresa);
		if (listaOfertas != null && !listaOfertas.isEmpty()){
				for (OfertaEmpleoVO ofertaVO : listaOfertas){
					
				if (ofertaVO.getEstatus() == ESTATUS.ACTIVO.getIdOpcion() || ofertaVO.getEstatus() == ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion()){
					//logger.info("----------- idOfertaEmpleo = "+ofertaVO.getIdOfertaEmpleo()+", estatus = "+ESTATUS.getDescripcion(ofertaVO.getEstatus()) +" -----------");

					//Eliminamos las ofertas de la empresa
					if (ofertaVO.getEstatus() == ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion()){
						ofertaEmpleoFacade.actualizaEstatus(ofertaVO.getIdOfertaEmpleo(), ESTATUS.RECHAZADA.getIdOpcion());						
						//logger.info("Oferta idOfertaEmpleo = "+ofertaVO.getIdOfertaEmpleo()+" a estatus "+ESTATUS.RECHAZADA.getOpcion());
						
						registroPorValidarFacade.actualizaEstatusPorPropietario(
								                       ofertaVO.getIdOfertaEmpleo(),                            //idRegistro 
								                       idEmpresa,                                               //idPropietario
								                       Utils.toLong(TIPO_REGISTRO.OFERTA.getIdTipoRegistro()),  //idTipoRegistro 
								                       Utils.toLong(TIPO_REGISTRO.EMPRESA.getIdTipoRegistro()), //idTipoPropietario
								                       ESTATUS.RECHAZADA,                                      //estatus
								                       new Date(),                                              //fechaModificacion
								                       idUsuario,                                               //idUsuario
								                       Utils.toLong(VALIDACION_MOTIVO.RECHAZO_EMPRESA_FRAUDULENTA.getIdMotivo())); //idMotivo
						//logger.info("Registro por validar de tipo oferta idRegistro = "+ofertaVO.getIdOfertaEmpleo()+" a estatus "+ESTATUS.RECHAZADA.getOpcion());						
					} else if (ofertaVO.getEstatus() == ESTATUS.ACTIVO.getIdOpcion()){
						ofertaEmpleoFacade.actualizaEstatus(ofertaVO.getIdOfertaEmpleo(), ESTATUS.ELIMINADA_EMP_FRAUDULENTA.getIdOpcion());						
						//logger.info("Oferta idOfertaEmpleo = "+ofertaVO.getIdOfertaEmpleo()+" a estatus "+ESTATUS.RECHAZADA.getOpcion());
					}
					
					//Cancelamos las entrevistas
					entrevistaFacade.cancelaEntrevistas(ofertaVO.getIdOfertaEmpleo(), ESTATUS.CANCELADA, new Date(), ESTATUS.NUEVA, ESTATUS.ACEPTADA, ESTATUS.REPROGRAMADA);
					//logger.info("Entrevistas de idOfertaEmpleo = "+ofertaVO.getIdOfertaEmpleo()+" a estatus "+ESTATUS.CANCELADA.getOpcion());
					
//					//Copiamos el detalle de la oferta a las tablas de fraude
//					//OFERTA_EMPLEO_FRAUDULENTA
//					ofertaEmpleoFraudulentaFacade.save(ofertaVO);
//					logger.info("Datos de OFERTA_EMPLEO se copian en OFERTA_EMPLEO_FRAUDULENTA");
//
//					//OFERTA_CARRERA_FRAUDULENTA
//					List<OfertaCarreraEspecialidadVO> listaOfertaEspecialidades = ofertaFacade.getCarrerasEspecialidades(ofertaVO.getIdOfertaEmpleo());
//					if (listaOfertaEspecialidades != null && !listaOfertaEspecialidades.isEmpty()){
//						for (OfertaCarreraEspecialidadVO especialidadVO : listaOfertaEspecialidades){						
//							ofertaEmpleoFraudulentaFacade.saveOfertaCarreraFraudulenta(especialidadVO, ofertaVO.getIdOfertaEmpleo());
//						}						
//						logger.info("Datos de OFERTA_CARRERA_ESPEC se copian en OFERTA_CARRERA_FRAUDULENTA");
//					}
//					
//					//OFERTA_IDIOMA_FRAUDULENTA
//					List<OfertaIdiomaVO> listaOfertaIdiomas = ofertaIdiomaFacade.ofertaIdiomasList(ofertaVO.getIdOfertaEmpleo());
//					if (listaOfertaIdiomas != null && !listaOfertaIdiomas.isEmpty()){
//						for (OfertaIdiomaVO ofertaIdiomaVO : listaOfertaIdiomas){
//							ofertaEmpleoFraudulentaFacade.saveOfertaOfertaIdiomaFraudulenta(ofertaIdiomaVO);
//						}
//						logger.info("Datos de OFERTA_IDIOMA se copian en OFERTA_IDIOMA_FRAUDULENTA");
//					}
//					
//					//OFERTA_PRESTACION_FRAUDULENTA
//					List<OfertaPrestacionVO> listaPrestaciones = ofertaFacade.getListaOfertaPrestacion(ofertaVO.getIdOfertaEmpleo());
//					if (listaPrestaciones != null && !listaPrestaciones.isEmpty()){
//						for (OfertaPrestacionVO ofertaPrestacionVO : listaPrestaciones){
//							ofertaEmpleoFraudulentaFacade.saveOfertaPrestacionFraudulenta(ofertaPrestacionVO);
//						}
//						logger.info("Datos de OFERTA_PRESTACION se copian en OFERTA_PRESTACION_FRAUDULENTA");
//					}
//					
//					//OFERTA_REQUISITO_FRAUDULENTA
//					List<OfertaRequisitoVO> listaRequisitos = ofertaRequisitoFacade.ofertaRequisitosList(ofertaVO.getIdOfertaEmpleo());
//					if (listaRequisitos != null && !listaRequisitos.isEmpty()){
//						for (OfertaRequisitoVO ofertaRequisitoVO : listaRequisitos){
//							ofertaEmpleoFraudulentaFacade.saveOfertaRequisitoFraudulenta(ofertaRequisitoVO);
//						}
//						logger.info("Datos de OFERTA_REQUISITO se copian en OFERTA_REQUISITO_FRAUDULENTA");
//					}
//					
//					//OFERTA_UBICACION_FRAUDULENTA
//					ArrayList<OfertaUbicacionVO> listaUbicaciones = ofertaFacade.getUbicacionesOferta(ofertaVO.getIdOfertaEmpleo());
//					if (listaUbicaciones != null && !listaUbicaciones.isEmpty()){
//						for (OfertaUbicacionVO registroUbicacionVO : listaUbicaciones){
//							ofertaEmpleoFraudulentaFacade.saveOfertaUbicacionFraudulenta(registroUbicacionVO);
//						}
//						logger.info("Datos de OFERTA_UBICACION se copian en OFERTA_UBICACION_FRAUDULENTA");						
//					}
				}
				}
		}
		
//		registroPorValidarFacade.actualizaEstatusPorPropietario(
//														idEmpresa,                                                //idRegistro 
//														idEmpresa,                                                //idPropietario
//										                Utils.toLong(TIPO_REGISTRO.EMPRESA.getIdTipoRegistro()),  //idTipoRegistro 
//										                Utils.toLong(TIPO_REGISTRO.EMPRESA.getIdTipoRegistro()),  //idTipoPropietario
//										                ESTATUS.ELIMINADA_EMP_FRAUDULENTA,                        //estatus
//										                new Date(),                                               //fechaModificacion
//										                idUsuario,                                                //idUsuario
//										                Utils.toLong("0"));                                       //idMotivo
//		logger.info("Registros por validar de tipo empresa, idRegistro = "+idEmpresa+" a estatus "+ESTATUS.ELIMINADA_EMP_FRAUDULENTA.getIdOpcion());

		//Eliminamos la empresa
		empresaFacade.actualizaEstatus(idEmpresa, ESTATUS.ELIMINADA_EMP_FRAUDULENTA.getIdOpcion());
		//logger.info("Empresa idEmpresa = "+idEmpresa+" a estatus "+ESTATUS.ELIMINADA_EMP_FRAUDULENTA.getOpcion());
		
		//Volcamos la información de la empresa en EMPRESA_FRAUDULENTA
		EmpresaVO empresa = empresaFacade.findById(idEmpresa);		
		empresaFraudulentaFacade.saveEmpresaFraudulenta(empresa);
		//logger.info("Datos de EMPRESA se copian en EMPRESA_FRAUDULENTA");

		//bitacoraFacade.save(EVENTO.RECHAZA_REGISTRO.getIdEvento(), idUsuario, ESTATUS.ELIMINADA_EMP_FRAUDULENTA.getOpcion(), Calendar.getInstance(), "idEmpresa="+idEmpresa, Long.valueOf("0"), TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());	
		bitacoraFacade.save(EVENTO.DETECTAR_EMPRESA_FRAUDULENTA.getIdEvento(), idUsuario, ESTATUS.ELIMINADA_EMP_FRAUDULENTA.getOpcion(), Calendar.getInstance(), "idEmpresa="+idEmpresa, idEmpresa, TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());

		//logger.info("Final de enviarEmpFraudulenta");		
	}

	@Override
	public List<EmpresaFraudulentaVO> consultaEmpresasFraudulentasOferta(
			long idEmpresa) {
		
		List<EmpresaFraudulentaVO> empresas = null;

		if (idEmpresa<=0) throw new IllegalArgumentException("Identificador del empresa requerido");

		
		EmpresaVO empresaAut= empresaFacade.findById(idEmpresa);
		DomicilioVO domicilioEmpresAut = domicilioFacade.buscarDomicilioIdPropietario(idEmpresa, TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
		List<TelefonoVO> telefonosEmpresaAut = telefonoFacade.getTelefonosPropietario(idEmpresa, TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());

		HashMap<Long, EmpresaFraudulentaVO> empresasFraudulentas = empresaFraudulentaFacade.consultaEmpresasFraudulentas(empresaAut);
		HashMap<Long, EmpresaFraudulentaVO> empTelefonos = empresaFraudulentaFacade.consultaTelefonosEmpresasFraudulentas(telefonosEmpresaAut);
		HashMap<Long, EmpresaFraudulentaVO> empDomicilios = empresaFraudulentaFacade.consultaDomiciliosEmpresasFraudulentas(domicilioEmpresAut);


		for (EmpresaFraudulentaVO empresa : empresasFraudulentas.values()){

			// Agrega Telefonos
			if (empTelefonos.containsKey(empresa.getIdEmpresa())){
				EmpresaFraudulentaVO empTels = empTelefonos.get(empresa.getIdEmpresa());
				String coincidencias = empresa.getCoincidencias();
				if(coincidencias==null||coincidencias.equals(""))coincidencias = empTels.getCoincidencias();
				else coincidencias = coincidencias +", "+empTels.getCoincidencias();
				empresa.addTelefonos(empTels.getTelefonos());
				empresa.setCoincidencias(coincidencias);
				empTelefonos.remove(empresa.getIdEmpresa());
			}

			// Agrega domicilios
			if (empDomicilios.containsKey(empresa.getIdEmpresa())){
				EmpresaFraudulentaVO empDoms = empDomicilios.get(empresa.getIdEmpresa());
				String coincidencias = empresa.getCoincidencias();
				if(coincidencias==null||coincidencias.equals(""))coincidencias = empDoms.getCoincidencias();
				else coincidencias = coincidencias+ ", "+empDoms.getCoincidencias();
				empresa.addDomicilios(empDoms.getDomicilios());
				empresa.setCoincidencias(coincidencias);
				empDomicilios.remove(empresa.getIdEmpresa());
			}
		}

		// Se agrega las Empresas con Telefonos Fraudulentos a la lista de Empresas Fraudulentas
		for (EmpresaFraudulentaVO empTels  : empTelefonos.values()){
			if (!empresasFraudulentas.containsKey(empTels.getIdEmpresa())){
				empresasFraudulentas.put(empTels.getIdEmpresa(), empTels);
			}else{
				
				EmpresaFraudulentaVO emp = empresasFraudulentas.get(empTels.getIdEmpresa());
				String coincidencias = emp.getCoincidencias();
				if(coincidencias==null||coincidencias.equals(""))coincidencias = empTels.getCoincidencias();
				else coincidencias = coincidencias +", "+empTels.getCoincidencias();
				emp.addTelefonos(empTels.getTelefonos());
				emp.setCoincidencias(coincidencias);
				
			}
		}

		// Se agrega las Empresas con Domicilios Fraudulentos a la lista de Empresas Fraudulentas
		for (EmpresaFraudulentaVO empDoms  : empDomicilios.values()){
			if (!empresasFraudulentas.containsKey(empDoms.getIdEmpresa())){
				empresasFraudulentas.put(empDoms.getIdEmpresa(), empDoms);
			}else{
				EmpresaFraudulentaVO emp = empresasFraudulentas.get(empDoms.getIdEmpresa());
				String coincidencias = emp.getCoincidencias();
				if(coincidencias==null||coincidencias.equals(""))coincidencias =empDoms.getCoincidencias();
				else coincidencias = coincidencias+", "+empDoms.getCoincidencias();
				emp.addDomicilios(empDoms.getDomicilios());
				emp.setCoincidencias(coincidencias);
				
			}
		}

		empresas = new ArrayList<EmpresaFraudulentaVO>(empresasFraudulentas.values());

		return empresas;
	}

	@Override
	public EmpresaFraudulentaVO consultaDetalleEmpresaFraudulenta(
			long idEmpresaFraudulenta) throws BusinessException {
		if (idEmpresaFraudulenta<=0) throw new IllegalArgumentException("Identificador del empresa requerido");
		EmpresaFraudulentaVO empresaFraudulenta = empresaFraudulentaFacade.findById(idEmpresaFraudulenta);
		if(empresaFraudulenta==null){
			throw new BusinessException("No se ha localizado a la empresa seleccionada en empresas fraudulentas");
			
		}else{
			DomicilioVO domicilio = domicilioFacade.buscarDomicilioIdPropietario(idEmpresaFraudulenta, TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
			if(domicilio!=null){
				CodigoPostalVO cp = domicilioFacade.consultaCodigoPostal(domicilio.getIdColonia());
				if(cp!=null)domicilio.setColonia(cp.getColoniaDescripcion());
				domicilio.setEntidad(catalogoOpcionFacade.getOpcionById(Constantes.CATALOGO_OPCION_ENTIDADES, domicilio.getIdEntidad()));
				MunicipioVO municipio = domicilioFacade.consultaMunicipio(domicilio.getIdMunicipio(), domicilio.getIdEntidad());
				if(municipio!=null)
				domicilio.setMunicipio(municipio.getMunicipio());
				empresaFraudulenta.setDomicilio(domicilio);
				
			}
			empresaFraudulenta.setTipoEmpresa(catalogoOpcionFacade.getOpcionById(Constantes.CATALOGO_OPCION_TIPO_EMPRESA, empresaFraudulenta.getIdTipoEmpresa()));
			empresaFraudulenta.setActividadEconomica(catalogoOpcionFacade.getOpcionById(Constantes.CATALOGO_OPCION_TIPO_ACTIVIDAD_ECONOMICA, empresaFraudulenta.getIdActividadEconomica()));
			empresaFraudulenta.addTelefonos(telefonoFacade.getTelefonosPropietario(idEmpresaFraudulenta, TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario()));
			
		}
		return empresaFraudulenta;
	}

	@Override
	public int binnacleStatusUpd(EVENTO evento, long idUsuario, String descripcion, TIPO_REGISTRO tipoRegistro,
			long idRegistro, int estatusAnterior, String idDetalle, int idTipoPropietario) throws PersistenceException, BusinessException {
		int result = -1;
		try {
			bitacoraEstatus(evento, idUsuario, descripcion, tipoRegistro, idRegistro, estatusAnterior, idDetalle, idTipoPropietario);
			result = 1;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}	

}