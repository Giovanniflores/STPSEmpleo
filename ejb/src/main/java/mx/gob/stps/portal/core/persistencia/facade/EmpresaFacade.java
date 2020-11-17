package mx.gob.stps.portal.core.persistencia.facade;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.ID_FUENTE_CANADA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.ID_FUENTE_SFP;
import static mx.gob.stps.portal.core.infra.utils.Constantes.ID_FUENTE_SISNE;
import static mx.gob.stps.portal.core.infra.utils.Constantes.ID_REGISTRO_PORTAL;

import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.empresa.dao.EmpresaPorAutorizarDAO;
import mx.gob.stps.portal.core.empresa.vo.EmpresaUsuarioVO;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.empresa.vo.RegistroEmpresaVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.BOLSA_TRABAJO;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.GRADO_ESTUDIOS;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PERSONA;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_REGISTRO;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaRecienteVO;
import mx.gob.stps.portal.core.oferta.vo.UbicacionCanadaVO;
import mx.gob.stps.portal.persistencia.entity.Empresa;
import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.utils.Catalogos.DECISION;
import mx.gob.stps.portal.ws.renapo.vo.CurpVO;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * Implementacion de los metodos de persistencia para registros de la entidad "Empresa"
 * 
 * @author haydee.vertti
 *
 */
@Stateless
public class EmpresaFacade implements EmpresaFacadeLocal{
	private static Logger logger = Logger.getLogger(EmpresaFacade.class);

	public static int PORTAL_ID_SIZE = 17;
	@EJB
	private OfertaUbicacionFacadeLocal ofertaUbicacionFacade;	
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Method 'save'
	 * Almacena los datos del ValueObject y regresa el valor del identificador creado.
	 * @param vo
	 * @return long
	 */		
	public long save(EmpresaVO vo) throws PersistenceException {
		try{
			Empresa entity = getEntity(vo);
			entityManager.persist(entity);
			return entity.getIdEmpresa();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}	
	}
	

	/**
	 * Method 'delete'
	 * 
	 * @param vo
	 */		
	public void delete(EmpresaVO vo) throws PersistenceException {
		try{
			entityManager.remove(getEntity(vo));
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}		
	}

	public void delete(long idEmpresa) throws PersistenceException {
		try{
			Empresa entity =entityManager.find(Empresa.class, idEmpresa);
			entityManager.remove(entity);
		
		} catch (RuntimeException re) {
			throw new PersistenceException(re);
		}		
	}

	/**
	 * Method 'findById'
	 * Regresa un objeto EmpresaVO con los datos correspondientes a
	 * la empresa cuyo identificador se proporciona
	 * 
	 * @param id
	 * @return EmpresaVO
	 */		
	public EmpresaVO findById(long id) throws PersistenceException {
		EmpresaVO vo = null;
		try{
			Empresa instance = entityManager.find(Empresa.class, id);	
			if (instance==null) return null;

			entityManager.refresh(instance);
			vo = getEmpresaVO(instance);

		} catch (NoResultException re) {
			logger.error("Empresa no localizada: "+ id);
		}  catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}		

		return vo;
	}

	/**
	 * Method 'findByIdUsuario'
	 * Regresa un objeto EmpresaVO con los datos correspondientes a
	 * la empresa cuyo identificador de usuario se proporciona. En caso de no 
	 * encontrar una empresa asociada al {@code idUsuario} proporcionado, devuelve {@code null}.
	 * 
	 * @param idUsuario el identificador del usuario asociado a la empresa
	 * @return EmpresaVO que representa el registro encontrado para el {@code idUsuario}
	 *         proporcionado, o {@code null}, si no se encuentra un registro coincidente.
	 * @throws PersistenceException en caso de que ocurra alg&uacute;n problema en 
	 *         tiempo de ejecuci&oacute;n, o si el proveedor de persistencia encuentra un problema.
	 */		
	public EmpresaVO findByIdUsuario(long idUsuario) throws PersistenceException {
		EmpresaVO vo = null;
		String findEmpresaByUsuario = "SELECT c FROM Empresa c WHERE c.idUsuario = :usuario";
		try{
			Query query = entityManager.createQuery(findEmpresaByUsuario);
			query.setParameter("usuario", idUsuario);
			
			Empresa entity = (Empresa)query.getSingleResult();
			vo = getEmpresaVO(entity);
			
		} catch (NoResultException re) {
			// No se localizaron registros
			vo = null;
		} catch (RuntimeException re) {
			re.printStackTrace(); throw new PersistenceException(re);
		}
		
		return vo;
	}	
	
	
	/**
	 * Method 'update'
	 * 
	 * @param vo
	 */		
	public void update(EmpresaVO vo) throws PersistenceException {
		try{
			//Empresa entity = getEntity(vo);
			Empresa entity = entityManager.find(Empresa.class, vo.getIdEmpresa());			
			entity.setIdEmpresa(vo.getIdEmpresa());		
			entity.setFechaUltimaActualizacion(new Date());
			entity.setLogotipo(vo.getLogotipo());
			//entity.setConfidencial(vo.getConfidencial());
			entity.setEstatus(vo.getEstatus());
			entity.setContactoEmpresa(vo.getContactoEmpresa());
			entity.setCorreoElectronico(vo.getCorreoElectronico());
			entity.setDescripcion(vo.getDescripcion());
			entity.setIdActividadEconomica(vo.getIdActividadEconomica());
			entity.setIdTipoEmpresa(vo.getIdTipoEmpresa());
			entity.setNumeroEmpleados(vo.getNumeroEmpleados());
			entity.setPaginaWeb(vo.getPaginaWeb());
			entity.setRfc(vo.getRfc());
			entity.setNombreComercial(vo.getNombreComercial());
			entity.setIdTamanio(null != vo.getIdTamanio() ? vo.getIdTamanio() : 0L);
			
			//XXX Verificar si realmente es necesario
			//entity.setIdFuente(vo.getIdFuente());
			//entity.setIdOficina(vo.getIdOficina());
			
			if(esActualizacionDeCurp(vo)){
				entity.setCurpPF(vo.getCurpPF());
				entity.setCurpValidada(DECISION.SI.getIdOpcion().shortValue());
				entity.setIdLugarNacimientoPF(vo.getIdLugarNacimientoPF());
				entity.setGenero(vo.getGenero());
				entity.setIdPortalEmpleo(vo.getCurpPF());
			}
			
			entityManager.merge(entity);
			entityManager.flush();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}		
	}
	
	private boolean esActualizacionDeCurp(EmpresaVO empresaVO){
		boolean esActualizacionDeCurp = false;
		
		if(empresaVO.getIdTipoEmpresa() == Catalogos.TIPO_EMPRESA.PRIVADA.getTipoEmpresa() 
				&& empresaVO.getIdTipoPersona() == (long)Catalogos.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()
				&& null != empresaVO.getCurpPF() 
				&& (null == empresaVO.getCurpValidada() || empresaVO.getCurpValidada()<DECISION.SI.getIdOpcion().shortValue()) 
				){
			esActualizacionDeCurp = true;
		}
		
		return esActualizacionDeCurp;
	}		
	
	/* Se utiliza solo para regenerar empresas, que es un caso especial */
	public void updateFull(EmpresaVO vo) throws PersistenceException {
		try{
			Empresa entity = entityManager.find(Empresa.class, vo.getIdEmpresa());		
			entity.setIdEmpresa(vo.getIdEmpresa());					
			entity.setNombrePF(vo.getNombre());
			entity.setApellidoPaternoPF(vo.getApellido1());
			entity.setApellidoPaternoPF(vo.getApellido2());
			entity.setRazonSocial(vo.getRazonSocial());
			entity.setFechaNaciminetoPF(vo.getFechaNacimiento());
			entity.setFechaActa(vo.getFechaActa());
			//fecha de alta no se actualiza
			entity.setFechaUltimaActualizacion(new Date());
			entity.setLogotipo(vo.getLogotipo());
			//entity.setConfidencial(vo.getConfidencial());
			entity.setEstatus(vo.getEstatus());
			entity.setContactoEmpresa(vo.getContactoEmpresa());
			entity.setCorreoElectronico(vo.getCorreoElectronico());
			entity.setDescripcion(vo.getDescripcion());
			entity.setIdActividadEconomica(vo.getIdActividadEconomica());
			entity.setIdTipoEmpresa(vo.getIdTipoEmpresa());
			entity.setNumeroEmpleados(vo.getNumeroEmpleados());
			entity.setPaginaWeb(vo.getPaginaWeb());
			entity.setRfc(vo.getRfc());
			entity.setNombreComercial(vo.getNombreComercial());	
			entity.setIdPortalEmpleo(vo.getIdPortalEmpleo());
			
			if(vo.getIdTipoEmpresa() == Catalogos.TIPO_EMPRESA.PRIVADA.getTipoEmpresa()){
				entity.setIdTipoPersona(vo.getIdTipoPersona());
				entity.setCurpPF(vo.getCurpPF());
				
				if(null!=vo.getCurpPF()){
					entity.setCurpValidada(vo.getCurpValidada());
				}					
			}				
			
			entityManager.merge(entity);
			entityManager.flush();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}			
	}

	/**
	 * Method 'actualizaEstatus'
	 * Actualiza el estatus de una empresa, al valor proporcionado.
	 * 
	 * @param idEmpresa
	 * @param estatus
	 */		
	public void actualizaEstatus(long idEmpresa, int estatus)
			throws PersistenceException {		
		try{
			Empresa empresa = entityManager.find(Empresa.class, idEmpresa);
			empresa.setFechaUltimaActualizacion(new Date());
			empresa.setEstatus(estatus);
			entityManager.merge(empresa);
			entityManager.flush();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}
	}

	/**
	 * Method 'actualizaUltimaFecha'
	 * Actualiza la fecha de actualización de una empresa, al valor proporcionado.
	 * 
	 * @param idEmpresa
	 * @param ultimaFecha
	 */		
	public void actualizaUltimaFecha(long idEmpresa, Date ultimaFecha)
			throws PersistenceException {
		try{
			Empresa empresa = entityManager.find(Empresa.class, idEmpresa);
			empresa.setFechaUltimaActualizacion(ultimaFecha);
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}				
	}	
	
	public void actualizaFechaConfirma(long idEmpresa, Date fechaConfirma)
			throws PersistenceException {
		try{
			Empresa empresa = entityManager.find(Empresa.class, idEmpresa);
			Calendar date = Calendar.getInstance();
			date.set(2011, Calendar.DECEMBER, 12);					

			if (null == empresa.getFechaConfirma()){
				
				if(empresa.getFechaAlta().before(date.getTime())){
					
					empresa.setFechaConfirma(new Date());
					
				} else if( empresa.getFechaAlta().after(date.getTime())) {
					
					empresa.setFechaConfirma(empresa.getFechaAlta());
				}					
			}  								
			entityManager.merge(empresa);
			entityManager.flush();		
			
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}				
	}	
	
	public String convertAccents(String originalString){
		String changedString = "";
		if(originalString.length()>0){
			//acentos y ñ			
			changedString = originalString.toLowerCase();
			//COMENTAR EN PRODUCCION  los números no se tendrán en cuenta para la generación del id_portal_empresa en produccion
			changedString = changedString.replaceAll("\\d", "");
			changedString = changedString.replaceAll("\\s", "");
			changedString = changedString.replaceAll("[àáâãäå]","a");
			changedString = changedString.replaceAll("[èéêë]","e");
			changedString = changedString.replaceAll("[ìíîï]","i");
			changedString = changedString.replaceAll("[òóôõö]","o");
			changedString = changedString.replaceAll("[ùúûü]","u");
			changedString = changedString.replaceAll("ñ","n");
			changedString = changedString.replaceAll(",","");
			changedString = changedString.replaceAll("\\.","");
			changedString = changedString.replaceAll("&","");
			changedString = changedString.replaceAll("-","");
			changedString = changedString.toUpperCase();
		}
		return changedString;
	}
	
	public String generaIDPortalEmpleo(RegistroEmpresaVO vo) throws PersistenceException{
		String idPortalEmpleo = "";		
		int digitoVerificador = 1;
		Format formatter = new SimpleDateFormat("yyMMdd");		
		try {
			if(vo.getIdTipoPersona() == TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()){
				//Tipo de Persona Física
				if(vo.getApellido2().isEmpty()){
					//Primeras tres letras del apellido paterno
					String strApellido1 = convertAccents(vo.getApellido1());
					idPortalEmpleo = idPortalEmpleo + strApellido1.substring(0, 3);	
				} else {
					//Primeras dos letras del apellido paterno
					//Primera letra del apellido materno
					String strApellido1 = convertAccents(vo.getApellido1());
					String strApellido2 = convertAccents(vo.getApellido2());
					idPortalEmpleo = idPortalEmpleo + strApellido1.substring(0, 2);
					idPortalEmpleo = idPortalEmpleo + strApellido2.substring(0, 1);
				}
				//Primera letra del nombre
				String strNombre = convertAccents(vo.getNombre());	
				idPortalEmpleo = idPortalEmpleo + strNombre.substring(0, 1);
				//Fecha de nacimiento en formato yymmdd
				idPortalEmpleo = idPortalEmpleo + formatter.format(vo.getFechaNacimiento());
										
			} else {
				//Tipo de Persona Moral					
				String strRazonSocial = convertAccents(vo.getRazonSocial());
				//Primeras tres letras de razón social								
				if (strRazonSocial.length() < 3) 
					strRazonSocial = StringUtils.rightPad(strRazonSocial, 3, "X");
				idPortalEmpleo = idPortalEmpleo + strRazonSocial.substring(0, 3);	
				//Fecha de acta  en formato yymmdd
				idPortalEmpleo = idPortalEmpleo + formatter.format(vo.getFechaActa());				
			}
			idPortalEmpleo = idPortalEmpleo + vo.getCodigoPostal();	
			//Código verificador (1)				
			EmpresaPorAutorizarDAO epaDAO = new EmpresaPorAutorizarDAO();
			digitoVerificador = epaDAO.obtenerDigitoVerificador(idPortalEmpleo, vo.getIdTipoPersona());	
			//rellenar
			int lenId = idPortalEmpleo.length();
			int lenDigito = String.valueOf(digitoVerificador).length();
			int intRelleno = PORTAL_ID_SIZE - (lenId + lenDigito);
			String strRelleno = "";
			if(intRelleno>0){
				for(int i=0; i<intRelleno; i++){
					strRelleno = strRelleno + "0";
				}				
			}
			idPortalEmpleo = idPortalEmpleo + strRelleno + digitoVerificador;
		
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw new PersistenceException(e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenceException(e);
		}		
		return idPortalEmpleo;		
	}
		
	public String generaIDPortalEmpleo(EmpresaVO vo, String codigoPostal) throws PersistenceException{
		String idPortalEmpleo = "";		
		int digitoVerificador = 1;
		Format formatter = new SimpleDateFormat("yyMMdd");		
		try {
	
			
			
			if(vo.getIdTipoEmpresa() == Catalogos.TIPO_EMPRESA.PRIVADA.getTipoEmpresa() 
					&& vo.getIdTipoPersona() == TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()){
				idPortalEmpleo = vo.getCurpPF();
				
			} else {
				
				//Tipo de empresa ONG, Publica o Tipo de Persona Moral					
				String strRazonSocial = convertAccents(vo.getRazonSocial());
				//Primeras tres letras de razón social							
				if (strRazonSocial.length() < 3) 
					strRazonSocial = StringUtils.rightPad(strRazonSocial, 3, "X");
				idPortalEmpleo = idPortalEmpleo + strRazonSocial.substring(0, 3);
				
				//Fecha de acta  en formato yymmdd				
				if(vo.getIdTipoPersona()  == TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona()){
					idPortalEmpleo = idPortalEmpleo + formatter.format(vo.getFechaActa());	
				} else {
					idPortalEmpleo = idPortalEmpleo + formatter.format(vo.getFechaAlta());	
				}
				
				idPortalEmpleo = idPortalEmpleo + codigoPostal;	
				//Código verificador (1)				
				EmpresaPorAutorizarDAO epaDAO = new EmpresaPorAutorizarDAO();
				digitoVerificador = epaDAO.obtenerDigitoVerificador(idPortalEmpleo, vo.getIdTipoPersona());	
				//rellenar
				int lenId = idPortalEmpleo.length();
				int lenDigito = String.valueOf(digitoVerificador).length();
				int intRelleno = PORTAL_ID_SIZE - (lenId + lenDigito);
				String strRelleno = "";
				if(intRelleno>0){
					for(int i=0; i<intRelleno; i++){
						strRelleno = strRelleno + "0";
					}				
				}
				idPortalEmpleo = idPortalEmpleo + strRelleno + digitoVerificador;											
			}			
		
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw new PersistenceException(e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenceException(e);
		}		
		return idPortalEmpleo;		
	}
	
	/**
	 * Method 'generaIDPortalEmpleo'
	 * Genera el identificador del portal del empleo correspondiente a la empresa
	 * cuyos datos se proporcionan.
	 * 
	 * @param idEmpresa
	 * @param estatus
	 */		
	public String generaIDPortalEmpleo(EmpresaVO vo) throws PersistenceException{
		String idPortalEmpleo = "";		
		int digitoVerificador = 1;
		Format formatter = new SimpleDateFormat("yyMMdd");		
		try {
			DomicilioVO domicilio = vo.getDomicilio();
			if(vo.getIdTipoPersona() == TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()){
				//Tipo de Persona Física
				if(vo.getApellido2().isEmpty()){
					//Primeras tres letras del apellido paterno
					String strApellido1 = convertAccents(vo.getApellido1());
					idPortalEmpleo = idPortalEmpleo + strApellido1.substring(0, 3);	
				} else {
					//Primeras dos letras del apellido paterno
					//Primera letra del apellido materno
					String strApellido1 = convertAccents(vo.getApellido1());
					String strApellido2 = convertAccents(vo.getApellido2());
					idPortalEmpleo = idPortalEmpleo + strApellido1.substring(0, 2);
					idPortalEmpleo = idPortalEmpleo + strApellido2.substring(0, 1);
				}
				//Primera letra del nombre
				String strNombre = convertAccents(vo.getNombre());
				idPortalEmpleo = idPortalEmpleo + strNombre.substring(0, 1);
				//Fecha de nacimiento en formato yymmdd
				idPortalEmpleo = idPortalEmpleo + formatter.format(vo.getFechaNacimiento());
				//System.out.println("---idPortalEmpleo:" + idPortalEmpleo);
										
			} else {
				//Tipo de Persona Moral					
				String strRazonSocial = convertAccents(vo.getRazonSocial());
				//Primeras tres letras de razón social		
				if (strRazonSocial.length() < 3) 
					strRazonSocial = StringUtils.rightPad(strRazonSocial, 3, "X");
				idPortalEmpleo = idPortalEmpleo + strRazonSocial.substring(0, 3);	
				//Fecha de acta  en formato yymmdd
				idPortalEmpleo = idPortalEmpleo + formatter.format(vo.getFechaActa());				
			}
			idPortalEmpleo = idPortalEmpleo + domicilio.getCodigoPostal();	
			//Código verificador (1)				
			EmpresaPorAutorizarDAO epaDAO = new EmpresaPorAutorizarDAO();
			digitoVerificador = epaDAO.obtenerDigitoVerificador(idPortalEmpleo, vo.getIdTipoPersona());	
			//rellenar
			int lenId = idPortalEmpleo.length();
			int lenDigito = String.valueOf(digitoVerificador).length();
			int intRelleno = PORTAL_ID_SIZE - (lenId + lenDigito);
			String strRelleno = "";
			if(intRelleno>0){
				for(int i=0; i<intRelleno; i++){
					strRelleno = strRelleno + "0";
				}				
			}
			idPortalEmpleo = idPortalEmpleo + strRelleno + digitoVerificador;
		
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw new PersistenceException(e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenceException(e);
		}		
		return idPortalEmpleo;
	}	
	
	/**
	 * Method 'getEmpresaVO'
	 * Coloca los datos de un objeto Empresa en un VisualObject correspondiente
	 * @param empresa
	 * @return EmpresaVO
	 */		
	private EmpresaVO getEmpresaVO(Empresa empresa){
		EmpresaVO vo = new EmpresaVO();
		
		try{
			vo.setAceptacionTerminos(empresa.getAceptacionTerminos()!=null?empresa.getAceptacionTerminos():0);
			vo.setApellido1(empresa.getApellidoPaternoPF());
			vo.setApellido2(empresa.getApellidoMaternoPF());
			vo.setAseguraDatos(empresa.getAseguraDatos()!=null?empresa.getAseguraDatos():0);
			//vo.setConfidencial(empresa.getConfidencial());
			vo.setContactoEmpresa(empresa.getContactoEmpresa());
			vo.setCargoContacto(empresa.getCargoContacto());
			vo.setCorreoElectronico(empresa.getCorreoElectronico());
			vo.setDescripcion(empresa.getDescripcion());
			vo.setEstatus(empresa.getEstatus());
			vo.setFechaActa(empresa.getFechaActa());
			vo.setFechaAlta(empresa.getFechaAlta());
			vo.setFechaNacimiento(empresa.getFechaNaciminetoPF());
			vo.setFechaUltimaActualizacion(empresa.getFechaUltimaActualizacion());
			vo.setFechaUltimaDesactivacion(empresa.getFechaUltimaDesactivacion());		
			vo.setIdActividadEconomica(empresa.getIdActividadEconomica());
			vo.setIdEmpresa(empresa.getIdEmpresa());
			vo.setIdMedio(empresa.getIdMedio()!=null?empresa.getIdMedio():0);
			vo.setIdPortalEmpleo(empresa.getIdPortalEmpleo());
			vo.setIdTipoEmpresa(empresa.getIdTipoEmpresa()!=null?empresa.getIdTipoEmpresa():0);
			vo.setIdTipoPersona(empresa.getIdTipoPersona()!=null?empresa.getIdTipoPersona():0);
			vo.setIdTipoSociedad(empresa.getIdTipoSociedad()!=null?empresa.getIdTipoSociedad():0);
			vo.setIdUsuario(empresa.getIdUsuario());
			vo.setLogotipo(empresa.getLogotipo());
			vo.setNombre(empresa.getNombrePF());
			vo.setNumeroEmpleados(empresa.getNumeroEmpleados()!=null?empresa.getNumeroEmpleados():0);
			vo.setPaginaWeb(empresa.getPaginaWeb());
			vo.setRazonSocial(empresa.getRazonSocial());
			vo.setRfc(empresa.getRfc());
			vo.setLogotipo(empresa.getLogotipo());
			vo.setNombreComercial(empresa.getNombreComercial());

			vo.setIdFuente(empresa.getIdFuente());
			vo.setIdOficina(empresa.getIdOficina());
			
			vo.setCurpPF(empresa.getCurpPF());
			vo.setCurpValidada(empresa.getCurpValidada());
			vo.setEstatusGeoreferenciaDomicilio(empresa.getEstatusGeoreferenciaDomicilio()!=null?empresa.getEstatusGeoreferenciaDomicilio():false);
			vo.setEstatusGeoreferenciaOferta(empresa.getEstatusGeoreferenciaOferta()!=null?empresa.getEstatusGeoreferenciaOferta():false);

		}catch(Exception e){
			e.printStackTrace();
		}
		
		return vo;
	}
	
	/**
	 * Method 'getEntity'
	 * Coloca los datos de un VisualObject en un objeto de tipo Empresa
	 * @param vo
	 * @return Empresa
	 */		
	private Empresa getEntity(EmpresaVO vo){
		Empresa entity = new Empresa();
		entity.setAceptacionTerminos(vo.getAceptacionTerminos());
		entity.setApellidoPaternoPF(vo.getApellido1());
		entity.setApellidoMaternoPF(vo.getApellido2());
		entity.setAseguraDatos(vo.getAseguraDatos());
		//entity.setConfidencial(vo.getConfidencial());
		entity.setContactoEmpresa(vo.getContactoEmpresa());
		entity.setCargoContacto(vo.getCargoContacto());
		entity.setCorreoElectronico(vo.getCorreoElectronico());
		entity.setDescripcion(vo.getDescripcion());
		entity.setEstatus(vo.getEstatus());
		entity.setFechaActa(vo.getFechaActa());
		entity.setFechaAlta(vo.getFechaAlta());
		entity.setFechaNaciminetoPF(vo.getFechaNacimiento());
		entity.setFechaUltimaActualizacion(vo.getFechaUltimaActualizacion());
		if(null!=vo.getFechaConfirma())
			entity.setFechaConfirma(vo.getFechaConfirma());
		entity.setIdActividadEconomica(vo.getIdActividadEconomica());
		entity.setIdEmpresa(vo.getIdEmpresa());
		entity.setIdMedio(vo.getIdMedio());
		entity.setIdPortalEmpleo(vo.getIdPortalEmpleo());		
		entity.setIdTipoEmpresa(vo.getIdTipoEmpresa());	
		entity.setIdTipoSociedad(vo.getIdTipoSociedad());
		entity.setIdUsuario(vo.getIdUsuario());
		entity.setLogotipo(vo.getLogotipo());
		entity.setNombrePF(vo.getNombre());
		entity.setNumeroEmpleados(vo.getNumeroEmpleados());
		entity.setPaginaWeb(vo.getPaginaWeb());
		entity.setRazonSocial(vo.getRazonSocial());
		entity.setRfc(vo.getRfc());
		entity.setNombreComercial(vo.getNombreComercial());
		
		entity.setIdFuente(vo.getIdFuente());
		entity.setIdOficina(vo.getIdOficina());	
		
		if(vo.getIdTipoEmpresa() == Catalogos.TIPO_EMPRESA.PRIVADA.getTipoEmpresa()){
			entity.setIdTipoPersona(vo.getIdTipoPersona());
			entity.setCurpPF(vo.getCurpPF());
			
			if(null!=vo.getCurpPF()){
				entity.setCurpValidada(vo.getCurpValidada());
			}					
		}		
		
		return entity;
	}
	
	
	/**
	 * Method 'findByIdEntity'
	 * Regresa un objeto Empresa con los datos correspondientes a
	 * la empresa cuyo identificador se proporciona
	 * 
	 * @param idEmpresa
	 * @return EmpresaVO
	 */		
	public Empresa findByIdEntity(long idEmpresa) throws PersistenceException {
		try{
			Empresa entity = entityManager.find(Empresa.class, idEmpresa);
			entityManager.refresh(entity);
			return entity;
		} catch (NoResultException re) {
			// No se localizaron registros
			return null;
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}		
	}

	@Override
	public Boolean validandoEmpresaAutorizar(long idEmpresa)throws PersistenceException,BusinessException {
		Boolean bandera = false;

		Empresa empresa = findByIdEntity(idEmpresa);

		if( empresa!= null){

			if(ESTATUS.ACTIVO.getIdOpcion() == empresa.getEstatus()){				
				 throw new BusinessException("La cuenta de Empresa se encuentra Activa. Favor de Iniciar sección");
			}		

			if(empresa.getEstatus() == ESTATUS.PREVALIDADA.getIdOpcion() ||
			   empresa.getEstatus() == ESTATUS.MODIFICADA.getIdOpcion()){
				bandera =  true;
			}
		} else {
			throw new BusinessException("No se pudo localizar la Empresa ["+ idEmpresa +"]");
		}

		return bandera;
	}

	
	@Override
	public Boolean validandoEmpresaEstatus(long idEmpresa)throws PersistenceException,BusinessException {
		Boolean bandera = false;
		
		Empresa empresa = findByIdEntity(idEmpresa);

		if (empresa==null) throw new BusinessException("No se pudo localizar la Empresa ["+ idEmpresa +"]");

		if(ESTATUS.INACTIVO.getIdOpcion()    != empresa.getEstatus() && 
		   ESTATUS.PREVALIDADA.getIdOpcion() != empresa.getEstatus() &&
		   ESTATUS.MODIFICADA.getIdOpcion()  != empresa.getEstatus()){				

			bandera = true;
			//throw new BusinessException("La cuenta de la Empresa se encuentra no INACTIVO PREVALIDADA.");
			logger.error("Confirmacion de Empresa con estatus inconsistente, idEmpresa ("+ empresa.getIdEmpresa() +"), Estatu ("+ empresa.getEstatus() +") ");

		} else {
			bandera = true;
		}
	
		return bandera;
	}


	@Override
	public long actualizaEmpresaEstatus(long idEmpresa, int estatus) throws PersistenceException {
		Empresa empresa = null;
		Calendar date = Calendar.getInstance();
		date.set(2011, Calendar.DECEMBER, 12);		

		try{
			empresa = entityManager.find(Empresa.class, idEmpresa);
			empresa.setEstatus(estatus);
			empresa.setFechaUltimaActualizacion(new Date());
			empresa.setAceptacionTerminos(ESTATUS.ACTIVO.getIdOpcion());
			
			if (null == empresa.getFechaConfirma()){
				
				//empresa.getFechaAlta().compareTo(date.getTime()) < 0
				if(empresa.getFechaAlta().before(date.getTime())){
					
					empresa.setFechaConfirma(new Date());
					
				} else if( empresa.getFechaAlta().after(date.getTime())) {
					
					empresa.setFechaConfirma(empresa.getFechaAlta());
				}					
			}  			
			entityManager.merge(empresa);
			entityManager.flush();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}

		return empresa.getIdUsuario();
	}

	@Override
	public void actualizaCorreoElectronico(long idEmpresa, String correoElectronico) throws PersistenceException {

		try{
			Empresa empresa = entityManager.find(Empresa.class, idEmpresa);		
			empresa.setCorreoElectronico(correoElectronico);
			empresa.setFechaUltimaActualizacion(new Date());
			entityManager.merge(empresa);
			entityManager.flush();
		} catch (RuntimeException re) {
			throw new PersistenceException(re);
		}
	}
	
	public void desactivarEmpresa(long idEmpresa, int idMotivoDesactivacion, String detalleDesactivacion) throws PersistenceException {
		try{
			Empresa empresa = entityManager.find(Empresa.class, idEmpresa);
			empresa.setEstatus(Constantes.ESTATUS.ELIMINADA_ADMIN.getIdOpcion());
			empresa.setIdMotivoDesactivacion((long)idMotivoDesactivacion);
			empresa.setDetalleDesactivacion(detalleDesactivacion);
			empresa.setFechaUltimaDesactivacion(new Date());
			entityManager.merge(empresa);
			entityManager.flush();			
		} catch (RuntimeException re) {
			throw new PersistenceException(re);
		}		
	}
	
	public void reactivarEmpresa(long idEmpresa) throws PersistenceException{
		try{
			Empresa empresa = entityManager.find(Empresa.class, idEmpresa);
			empresa.setEstatus(Constantes.ESTATUS.ACTIVO.getIdOpcion());
			empresa.setIdMotivoDesactivacion(null);
			empresa.setDetalleDesactivacion(null);
			empresa.setFechaUltimaDesactivacion(null);
			entityManager.merge(empresa);
			entityManager.flush();			
		} catch (RuntimeException re) {
			throw new PersistenceException(re);
		}			
	}
	
	public void actualizaIDPortalEmpleo(long idEmpresa, String idPortalEmpleo) throws PersistenceException {

		try{
			Empresa empresa = entityManager.find(Empresa.class, idEmpresa);		
			empresa.setIdPortalEmpleo(idPortalEmpleo);
			empresa.setFechaUltimaActualizacion(new Date());
		} catch (RuntimeException re) {
			throw new PersistenceException(re);
		}
	}	
	
	public EmpresaVO findByIdPortalEmpleo(String idPortalEmpleo) throws PersistenceException {
		EmpresaVO vo = null;
		String findEmpresaByIdPortal = "SELECT c FROM Empresa c WHERE c.idPortalEmpleo = :idPortalEmpleo";
		try{
			Query query = entityManager.createQuery(findEmpresaByIdPortal);
			query.setParameter("idPortalEmpleo", idPortalEmpleo);
			
			Empresa entity = (Empresa)query.getSingleResult();
			vo = getEmpresaVO(entity);
			
		} catch (NoResultException re) {
			// No se localizaron registros
		} catch (RuntimeException re) {
			throw new PersistenceException(re);
		}
		
		return vo;
	}

	public EmpresaVO findByEmailCP(String email, String cp) throws PersistenceException {
		EmpresaVO vo =null;
		StringBuilder find = new StringBuilder();
		find.append("SELECT e FROM Empresa e, Domicilio d WHERE e.idEmpresa = d.idPropietario");
		find.append(" AND d.idTipoPropietario = " + TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
		find.append(" AND e.correoElectronico = ?1");
		find.append(" AND d.codigoPostal = ?2");
		try {
			Query query = entityManager.createQuery(find.toString());
			query.setParameter(1, email);
			query.setParameter(2, cp);
			Empresa entity = (Empresa)query.getSingleResult();
			vo = getEmpresaVO(entity);
		} catch (NoResultException re) {
			// No se localizaron registros
		} catch (RuntimeException re) {
			throw new PersistenceException(re);
		}
		return vo;
	}
	
	// Consultar Empresa por RFC OAM
	public List<Long> findByRFC_CP(String RFC, String CodigoPostal) throws PersistenceException {
		List<Long> idempresas = new ArrayList<Long>();
		StringBuilder findEmpresaByRFC = new StringBuilder();

		findEmpresaByRFC.append("SELECT e.ID_EMPRESA FROM EMPRESA e, DOMICILIO d WHERE e.ID_EMPRESA = d.ID_PROPIETARIO ");
		findEmpresaByRFC.append("AND D.ID_TIPO_PROPIETARIO = 1 ");
		findEmpresaByRFC.append("AND e.RFC = UPPER(?1) ");
		findEmpresaByRFC.append("AND d.CODIGO_POSTAL = ?2 ");
		findEmpresaByRFC.append("ORDER BY e.ID_EMPRESA");

		try {
			String sql = findEmpresaByRFC.toString();

			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, RFC);
			query.setParameter(2, CodigoPostal);

			for (Object result : query.getResultList()) {
				idempresas.add(Utils.toLong(result));
			}
		} catch (NoResultException re) {
			// No se localizaron registros
		} catch (RuntimeException re) {
			throw new PersistenceException(re);
		}
		return idempresas;
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<EmpresaVO> consultaEmpresas(int idTipoPersona, String correoElectronico, String idPortalEmpleo,
											String nombre, String apellido1, String razonSocial, String fechaActa,
											String apellido2, Long idEmpresa, String contacto, String telefono, String domicilio, long idEntidad, long idMunicipio,	String usuario										
											) {

		java.sql.Date fechaActaDate = null;

		Date dateAux = Utils.convert(fechaActa);
		if (dateAux!=null) fechaActaDate = new java.sql.Date(dateAux.getTime());
		
		String sql = getQueryEmpresas(idTipoPersona, correoElectronico, idPortalEmpleo, nombre, apellido1, razonSocial, fechaActaDate,
				apellido2, idEmpresa, contacto, telefono, domicilio, idEntidad, idMunicipio, usuario);

		Query query = entityManager.createNativeQuery(sql);
		
		int index = 0;
		for (int i=0; i<3; i++){

			query.setParameter(++index, idTipoPersona);
			query.setParameter(++index, TIPO_REGISTRO.EMPRESA.getIdTipoRegistro());
			query.setParameter(++index, ESTATUS.RECHAZADA.getIdOpcion());
			query.setParameter(++index, ESTATUS.ACEPTADA.getIdOpcion());
			
			if (!isEmpty(correoElectronico)) query.setParameter(++index, "%"+correoElectronico +"%");
			
			if (!isEmpty(idPortalEmpleo))    query.setParameter(++index, idPortalEmpleo +"%");
			
			if (idTipoPersona == TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()){
				if (!isEmpty(nombre))        query.setParameter(++index, "%"+nombre +"%");
				if (!isEmpty(apellido1))     query.setParameter(++index, "%"+apellido1 +"%");
				if (!isEmpty(apellido2))     query.setParameter(++index, "%"+apellido2 +"%");				
			}else if (idTipoPersona == TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona()){
				if (!isEmpty(razonSocial))   query.setParameter(++index, "%"+razonSocial +"%");
				if (fechaActaDate!=null)     query.setParameter(++index, fechaActaDate);
			}
		}

		@SuppressWarnings("unchecked")
		List<Object[]> rowSet = query.getResultList();

		List<EmpresaVO> empresas = new ArrayList<EmpresaVO>();

		for (Object[] rs : rowSet) {
			EmpresaVO empresa = createEmpresaVO(rs);
			empresas.add(empresa);
		}

		/** SE CONSIDERA QUE LOS REGISTROS VIENE ORDENADOS POR ID_EMPRESA Y ID_REG_VALIDA DESCENDENTE
		 * POR LO TANTO EL PRIMER REGISTRO ES LA ULTIMA REVISION REALIZADA POR UN PUBLICADOR **/
		empresas = eliminaDuplicados(empresas);

		return empresas;
	}

	private List<EmpresaVO> eliminaDuplicados(List<EmpresaVO> empresas){
		
		long idEmpresaAnterior = 0;
		long idEmpresaActual = 0;

		/** YA QUE LAS EMPRESA ESTAN ORDENADAS POR ID_EMPRESA Y ID_REG_VALIDA DESCENDENTE, SI EL IDENTIFICADOR DE EMPRESA
		 * ES IGUAL AL ANTERIOR, SE TOMA LA PRIMER EMPRESA COMO VALIDA Y SE DESCARTA LA EMPRESA REPETIDA **/
		for (EmpresaVO empresa : empresas){
			idEmpresaActual = empresa.getIdEmpresa();

			if (idEmpresaActual == idEmpresaAnterior){
				empresa.setDescartar(true);
			}

			idEmpresaAnterior = idEmpresaActual;
		}

		List<EmpresaVO> empresasUnicas = new ArrayList<EmpresaVO>();

		for (EmpresaVO empresa : empresas){
			if (!empresa.isDescartar()){
				empresasUnicas.add(empresa);
			}
		}
		
		return empresasUnicas;
	}
	
	private String getQueryEmpresas(int idTipoPersona,
									String correoElectronico,
									String idPortalEmpleo,
									String nombre,
									String apellido1,
									String razonSocial,
									java.sql.Date fechaActaDate,
									String apellido2, Long idEmpresa, String contacto, String telefono, String domicilio, long idEntidad, long idMunicipio,	String usuario) {
		int index = 0;
		StringBuilder query = new StringBuilder();

		query.append(" SELECT e.razon_social, e.contacto_empresa, e.fecha_acta, e.fecha_alta, ");
		query.append("        e.fecha_nacimiento, e.id_empresa, e.id_portal_empleo, e.id_tipo_persona, "); 
		query.append("        e.nombre, e.apellido1, e.apellido2, e.correo_electronico, e.estatus, ");
		query.append("        'EMPRESA' as tblEmpresa, ");
		query.append("        R.FECHA_MODIFICACION, R.DETALLE_RECHAZO, ");
		query.append("        TRIM(NVL(U.NOMBRE,'') ||' '|| NVL(U.APELLIDO1,'') ||' '|| NVL(U.APELLIDO2,'')) AS USUARIO_NOMBRE, ");
		query.append("        R.ESTATUS as estatus_registro, ");
		query.append("        MOT.DESCRIPCION AS MOTIVO_RECHAZO, R.ID_REG_VALIDAR, e.es_empresa_sne, u.usuario ");
		
		query.append("   FROM EMPRESA E, REGISTRO_POR_VALIDAR R, ");
		query.append("        USUARIO U, VALIDACION_MOTIVO MOT ");
		
		query.append("  WHERE E.ID_TIPO_PERSONA = ?"+ (++index) +" ");
		query.append("    AND R.ID_REGISTRO(+) = E.ID_EMPRESA ");
		query.append("    AND R.ID_TIPO_REGISTRO(+) = ?"+ (++index) +" ");
		query.append("    AND R.ESTATUS(+) IN (?"+ (++index) +", ?"+ (++index) +") ");
		query.append("    AND U.ID_USUARIO(+) = E.ID_USUARIO ");
		query.append("    AND MOT.ID_VALIDACION_MOTIVO(+) = R.ID_MOTIVO_RECHAZO ");
		
		if (!isEmpty(correoElectronico)) query.append(" AND LOWER(E.CORREO_ELECTRONICO) LIKE LOWER(?"+ (++index) +") ");
		if (!isEmpty(idPortalEmpleo))    query.append(" AND LOWER(E.ID_PORTAL_EMPLEO) LIKE LOWER(?"+ (++index) +") ");
		
		if (idTipoPersona == TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()){
			if (!isEmpty(nombre))        query.append(" AND LOWER(E.NOMBRE) LIKE LOWER(?"+ (++index) +") ");
			if (!isEmpty(apellido1))     query.append(" AND LOWER(E.APELLIDO1) LIKE LOWER(?"+ (++index) +") ");
			if (!isEmpty(apellido2))     query.append(" AND LOWER(E.APELLIDO2) LIKE LOWER(?"+ (++index) +") ");
		}else if (idTipoPersona == TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona()){
			if (!isEmpty(razonSocial))   query.append(" AND LOWER(E.RAZON_SOCIAL) LIKE LOWER(?"+ (++index) +") ");
			if (fechaActaDate!=null)     query.append(" AND E.FECHA_ACTA = ?"+ (++index) +" ");	
		}
		
		if (idEmpresa != null && !idEmpresa.equals(Long.valueOf("0")))
			query.append(" AND E.ID_EMPRESA = "+idEmpresa.toString());
		
		if (contacto != null && !contacto.isEmpty())
			query.append(" AND E.CONTACTO_EMPRESA LIKE '%"+contacto+"%' ");		
		
		 if (telefono != null && !telefono.isEmpty())
			 query.append(" AND EXISTS (SELECT 1 FROM TELEFONO WHERE TELEFONO.ID_TIPO_PROPIETARIO = "+TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario()+" AND TELEFONO.ID_PROPIETARIO = E.ID_EMPRESA AND TELEFONO.TELEFONO LIKE '%"+telefono+"%') ");	

		 if (idEntidad > 0 || idMunicipio > 0 || (domicilio != null && !domicilio.isEmpty())){
			 query.append(" AND EXISTS (SELECT 1 FROM DOMICILIO WHERE DOMICILIO.ID_TIPO_PROPIETARIO = "+TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario()+" AND DOMICILIO.ID_PROPIETARIO = E.ID_EMPRESA ");
			 if (idEntidad > 0)
				 query.append(" AND DOMICILIO.ID_ENTIDAD = "+idEntidad);	 
			 if (idMunicipio > 0)
				 query.append(" AND DOMICILIO.ID_MUNICIPIO = "+idMunicipio);
			 if (domicilio != null && !domicilio.isEmpty())
				 query.append(" AND LOWER(DOMICILIO.CALLE) LIKE LOWER('%"+domicilio+"%')");
			 query.append(")"); 				 
		 }
		 
		 if (usuario != null && !usuario.isEmpty())
			 query.append(" AND LOWER(U.USUARIO) LIKE LOWER('%"+usuario+"%') ");
//			 query.append(" AND EXISTS (SELECT 1 FROM USUARIO WHERE USUARIO.ID_USUARIO = E.ID_USUARIO AND USUARIO.USUARIO LIKE '%"+usuario+"%')");			 
		 
		query.append(" UNION ");

		query.append(" SELECT e.razon_social, e.contacto_empresa, e.fecha_acta, e.fecha_alta, "); 
		query.append("        e.fecha_nacimiento, e.id_empresa, e.id_portal_empleo, e.id_tipo_persona,  ");
		query.append("        e.nombre, e.apellido1, e.apellido2, e.correo_electronico, e.estatus,  ");
		query.append("        'EMPRESA_POR_AUTORIZAR' as tblEmpresa, ");
		query.append("        R.FECHA_MODIFICACION, R.DETALLE_RECHAZO, ");
		query.append("        TRIM(NVL(U.NOMBRE,'') ||' '|| NVL(U.APELLIDO1,'') ||' '|| NVL(U.APELLIDO2,'')) AS USUARIO_NOMBRE, ");
		query.append("        R.ESTATUS as estatus_registro, ");
		query.append("        MOT.DESCRIPCION AS MOTIVO_RECHAZO, R.ID_REG_VALIDAR, 1 as es_empresa_sne, u.usuario ");
		
		query.append("   FROM EMPRESA_POR_AUTORIZAR E, REGISTRO_POR_VALIDAR R, ");
		query.append("        USUARIO U, VALIDACION_MOTIVO MOT ");
		
		query.append("  WHERE E.ID_TIPO_PERSONA = ?"+ (++index) +" ");
		query.append("    AND R.ID_REGISTRO(+) = E.ID_EMPRESA ");
		query.append("    AND R.ID_TIPO_REGISTRO(+) = ?"+ (++index) +" ");
		query.append("    AND R.ESTATUS(+) IN (?"+ (++index) +", ?"+ (++index) +") ");
		query.append("    AND U.ID_USUARIO(+) = R.ID_USUARIO ");
		query.append("    AND MOT.ID_VALIDACION_MOTIVO(+) = R.ID_MOTIVO_RECHAZO ");
		
		if (!isEmpty(correoElectronico)) query.append(" AND LOWER(E.CORREO_ELECTRONICO) LIKE LOWER(?"+ (++index) +") ");
		if (!isEmpty(idPortalEmpleo))    query.append(" AND LOWER(E.ID_PORTAL_EMPLEO) LIKE LOWER(?"+ (++index) +") ");

		if (idTipoPersona == TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()){
			if (!isEmpty(nombre))        query.append(" AND LOWER(E.NOMBRE) LIKE LOWER(?"+ (++index) +") ");
			if (!isEmpty(apellido1))     query.append(" AND LOWER(E.APELLIDO1) LIKE LOWER(?"+ (++index) +") ");		
			if (!isEmpty(apellido2))     query.append(" AND LOWER(E.APELLIDO2) LIKE LOWER(?"+ (++index) +") ");			
		}else if (idTipoPersona == TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona()){
			if (!isEmpty(razonSocial))   query.append(" AND LOWER(E.RAZON_SOCIAL) LIKE LOWER(?"+ (++index) +") ");
			if (fechaActaDate!=null)     query.append(" AND E.FECHA_ACTA = ?"+ (++index) +" ");
		}

		if (idEmpresa != null && !idEmpresa.equals(Long.valueOf("0")))
			query.append(" AND E.ID_EMPRESA = "+idEmpresa.toString());		
		
		if (contacto != null && !contacto.isEmpty())
			query.append(" AND E.CONTACTO_EMPRESA LIKE '%"+contacto+"%' ");		
		
		 if (telefono != null && !telefono.isEmpty())
			 query.append(" AND EXISTS (SELECT 1 FROM TELEFONO WHERE TELEFONO.ID_TIPO_PROPIETARIO = "+TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario()+" AND TELEFONO.ID_PROPIETARIO = E.ID_EMPRESA AND TELEFONO.TELEFONO LIKE '%"+telefono+"%') ");		

		 if (idEntidad > 0 || idMunicipio > 0 || (domicilio != null && !domicilio.isEmpty())){
			 query.append(" AND EXISTS (SELECT 1 FROM DOMICILIO WHERE DOMICILIO.ID_TIPO_PROPIETARIO = "+TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario()+" AND DOMICILIO.ID_PROPIETARIO = E.ID_EMPRESA ");
			 if (idEntidad > 0)
				 query.append(" AND DOMICILIO.ID_ENTIDAD = "+idEntidad);	 
			 if (idMunicipio > 0)
				 query.append(" AND DOMICILIO.ID_MUNICIPIO = "+idMunicipio);
			 if (domicilio != null && !domicilio.isEmpty())
				 query.append(" AND LOWER(DOMICILIO.CALLE) LIKE LOWER('%"+domicilio+"%')");
			 query.append(")"); 				 
		 }		 
		 
		 if (usuario != null && !usuario.isEmpty())
			 query.append(" AND LOWER(U.USUARIO) LIKE LOWER('%"+usuario+"%') ");			 
//			 query.append(" AND EXISTS (SELECT 1 FROM USUARIO WHERE USUARIO.ID_USUARIO = E.ID_USUARIO AND USUARIO.USUARIO LIKE '%"+usuario+"%')");		 
		 
		query.append(" UNION ");

		query.append(" SELECT e.razon_social, e.contacto_empresa, e.fecha_acta, e.fecha_alta, "); 
		query.append("        e.fecha_nacimiento, e.id_empresa, e.id_portal_empleo, e.id_tipo_persona,  ");
		query.append("        e.nombre, e.apellido1, e.apellido2, e.correo_electronico, e.estatus, ");
		query.append("        'EMPRESA_FRAUDULENTA' as tblEmpresa, ");
		query.append("        R.FECHA_MODIFICACION, R.DETALLE_RECHAZO, ");
		query.append("        TRIM(NVL(U.NOMBRE,'') ||' '|| NVL(U.APELLIDO1,'') ||' '|| NVL(U.APELLIDO2,'')) AS USUARIO_NOMBRE, ");
		query.append("        R.ESTATUS as estatus_registro, ");
		query.append("        MOT.DESCRIPCION AS MOTIVO_RECHAZO, R.ID_REG_VALIDAR, 1 as es_empresa_sne, u.usuario ");
		
		query.append("   FROM EMPRESA_FRAUDULENTA E, REGISTRO_POR_VALIDAR R, ");
		query.append("        USUARIO U, VALIDACION_MOTIVO MOT ");
		
		query.append("  WHERE E.ID_TIPO_PERSONA = ?"+ (++index) +" ");
		query.append("    AND R.ID_REGISTRO(+) = E.ID_EMPRESA ");
		query.append("    AND R.ID_TIPO_REGISTRO(+) = ?"+ (++index) +" ");
		query.append("    AND R.ESTATUS(+) IN (?"+ (++index) +", ?"+ (++index) +") ");
		query.append("    AND U.ID_USUARIO(+) = E.ID_USUARIO ");
		query.append("    AND MOT.ID_VALIDACION_MOTIVO(+) = R.ID_MOTIVO_RECHAZO ");
		
		if (!isEmpty(correoElectronico)) query.append(" AND LOWER(E.CORREO_ELECTRONICO) LIKE LOWER(?"+ (++index) +") ");
		if (!isEmpty(idPortalEmpleo))    query.append(" AND LOWER(E.ID_PORTAL_EMPLEO) LIKE LOWER(?"+ (++index) +") ");
		
		if (idTipoPersona == TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()){
			if (!isEmpty(nombre))        query.append(" AND LOWER(E.NOMBRE) LIKE LOWER(?"+ (++index) +") ");
			if (!isEmpty(apellido1))     query.append(" AND LOWER(E.APELLIDO1) LIKE LOWER(?"+ (++index) +") ");
			if (!isEmpty(apellido2))     query.append(" AND LOWER(E.APELLIDO2) LIKE LOWER(?"+ (++index) +") ");			
		}else if (idTipoPersona == TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona()){
			if (!isEmpty(razonSocial))   query.append(" AND LOWER(E.RAZON_SOCIAL) LIKE LOWER(?"+ (++index) +") ");
			if (fechaActaDate!=null)     query.append(" AND E.FECHA_ACTA = ?"+ (++index) +" ");
		}

		if (idEmpresa != null && !idEmpresa.equals(Long.valueOf("0")))
			query.append(" AND E.ID_EMPRESA = "+idEmpresa.toString());		
		
		if (contacto != null && !contacto.isEmpty())
			query.append(" AND E.CONTACTO_EMPRESA LIKE '%"+contacto+"%' ");		
		
		 if (telefono != null && !telefono.isEmpty())
			 query.append(" AND EXISTS (SELECT 1 FROM TELEFONO WHERE TELEFONO.ID_TIPO_PROPIETARIO = "+TIPO_PROPIETARIO.EMPRESA_FRAUDULENTA.getIdTipoPropietario()+" AND TELEFONO.ID_PROPIETARIO = E.ID_EMPRESA AND TELEFONO.TELEFONO LIKE '%"+telefono+"%') ");		

		 if (idEntidad > 0 || idMunicipio > 0 || (domicilio != null && !domicilio.isEmpty())){
			 query.append(" AND EXISTS (SELECT 1 FROM DOMICILIO WHERE DOMICILIO.ID_TIPO_PROPIETARIO = "+TIPO_PROPIETARIO.EMPRESA_FRAUDULENTA.getIdTipoPropietario()+" AND DOMICILIO.ID_PROPIETARIO = E.ID_EMPRESA ");
			 if (idEntidad > 0)
				 query.append(" AND DOMICILIO.ID_ENTIDAD = "+idEntidad);	 
			 if (idMunicipio > 0)
				 query.append(" AND DOMICILIO.ID_MUNICIPIO = "+idMunicipio);
			 if (domicilio != null && !domicilio.isEmpty())
				 query.append(" AND LOWER(DOMICILIO.CALLE) LIKE LOWER('%"+domicilio+"%')");
			 query.append(")"); 				 
		 }
		 
		 if (usuario != null && !usuario.isEmpty())
			 query.append(" AND LOWER(U.USUARIO) LIKE LOWER('%"+usuario+"%') ");			 
//			 query.append(" AND EXISTS (SELECT 1 FROM USUARIO WHERE USUARIO.ID_USUARIO = E.ID_USUARIO AND USUARIO.USUARIO LIKE '%"+usuario+"%')");		 
		 
		query.append(" ORDER BY ID_EMPRESA, ID_REG_VALIDAR DESC ");
		System.out.println("---query:" + query);
		return query.toString();
	}	
	
	private boolean isEmpty(String valor){
		return (valor==null || valor.trim().isEmpty());
	}
	
	private EmpresaVO createEmpresaVO(Object[] rowSet) {
		EmpresaVO vo = new EmpresaVO();
		vo.setRazonSocial        (Utils.toString (rowSet[0]));
		vo.setContactoEmpresa    (Utils.toString (rowSet[1]));
		vo.setFechaActa          (Utils.toDate   (rowSet[2]));
		vo.setFechaAlta          (Utils.toDate	 (rowSet[3]));
		vo.setFechaNacimiento    (Utils.toDate	 (rowSet[4]));
		vo.setIdEmpresa          (Utils.toLong	 (rowSet[5]));
		vo.setIdPortalEmpleo     (Utils.toString (rowSet[6]));
		vo.setIdTipoPersona      (Utils.toInt	 (rowSet[7]));
		vo.setNombre             (Utils.toString (rowSet[8]));
		vo.setApellido1          (Utils.toString (rowSet[9]));
		vo.setApellido2          (Utils.toString (rowSet[10]));
		vo.setCorreoElectronico  (Utils.toString (rowSet[11]));
		vo.setEstatus            (Utils.toInt	 (rowSet[12]));
		vo.setTblEmpresa         (Utils.toString (rowSet[13]));

		vo.setFechaRevision      (Utils.toDate	 (rowSet[14]));
		vo.setDetalleRechazo     (Utils.toString (rowSet[15]));
		vo.setNombrePublicador   (Utils.toString (rowSet[16]));
		vo.setEstatusRegistro    (Utils.toInt	 (rowSet[17]));
		vo.setMotivoRechazo      (Utils.toString (rowSet[18]));
		vo.setIdRegValidar       (Utils.toLong	 (rowSet[19]));

		vo.setEsEmpresaSNE		 (Utils.toInt(rowSet[20]));
		vo.setUsuario			 (Utils.toString (rowSet[21]));
		vo.setRechazada(vo.getEstatusRegistro() == ESTATUS.RECHAZADA.getIdOpcion());
		return vo;
	}

	// EmpresaUsuario OAM
	private EmpresaUsuarioVO createEmpresaUsuarioVO(Object[] rowSet) {
		EmpresaUsuarioVO vo = new EmpresaUsuarioVO();
		vo.setIdEmpresa(Utils.toLong(rowSet[0]));
		vo.setRFC(Utils.toString(rowSet[1]));
		vo.setEmail(Utils.toString(rowSet[2]));
		vo.setCodigoPostal(Utils.toString(rowSet[3]));
		vo.setEstatus(Utils.toInt(rowSet[4]));
		vo.setFechaModificacion(Utils.toDate(rowSet[5]));
		return vo;
	}
	
	public EmpresaVO consultaEmpresaPorCorreo(String correoElectronico) throws PersistenceException {
		EmpresaVO vo = null;

		try{
			Query query = entityManager.createQuery("SELECT e FROM Empresa e WHERE LOWER(e.correoElectronico) = LOWER(:correo)");
			query.setParameter("correo", correoElectronico);

			Empresa entity = (Empresa)query.getSingleResult();
			vo = getEmpresaVO(entity);

		} catch (NoResultException re) {
			logger.error("Empresa no localizada mediante el correo : "+ correoElectronico);
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenceException(e);
		}
		
		return vo;
	}
		
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<OfertaRecienteVO> obtenerOfertasRecientes(int numRegistros, long idOrigenCanada) throws PersistenceException{
		
		List<OfertaRecienteVO> ofertasRecientes = new ArrayList<OfertaRecienteVO>();
		StringBuffer sqlString = new StringBuffer();
		
		sqlString.append("SELECT ID_OFERTA_EMPLEO,TITULO_OFERTA,UBICACION,VIGENCIA,FUENTE, SALARIO FROM ( ");
		sqlString.append(" SELECT OE.ID_OFERTA_EMPLEO as ID_OFERTA_EMPLEO,OE.TITULO_OFERTA as TITULO_OFERTA, ");
		sqlString.append(" F_DESC_CATALOGO(25, ou.id_entidad) || ', ' || Mun.MUNICIPIO AS UBICACION, ");		
		sqlString.append(" to_char(OE.FECHA_INICIO, 'DD') || ' de ' ||  rtrim(to_char(OE.FECHA_INICIO, 'Month')) || ' ' || ");
		sqlString.append(" trim(to_char(OE.FECHA_INICIO, 'yyyy')) || ' - ' || ");
		sqlString.append(" to_char(OE.FECHA_FIN, 'DD') || ' de ' ||  rtrim(to_char(OE.FECHA_FIN, 'Month')) || ' ' || ");
		sqlString.append(" trim(to_char(OE.FECHA_FIN,'yyyy')) as VIGENCIA, OE.FUENTE, OE.SALARIO ");
		sqlString.append(" FROM  OFERTA_EMPLEO OE ");
		sqlString.append(" LEFT JOIN OFERTA_UBICACION ou ");
		sqlString.append(" ON oe.ID_OFERTA_EMPLEO = ou.ID_OFERTA_EMPLEO");
		sqlString.append(" LEFT JOIN MUNICIPIO mun ");
		sqlString.append(" ON ou.id_municipio = mun.id_municipio AND ou.id_entidad = mun.id_entidad ");
		sqlString.append(" WHERE OE.ESTATUS = " + ESTATUS.ACTIVO.getIdOpcion());
		sqlString.append(" and ou.ID_OFERTA_EMPLEO=oe.ID_OFERTA_EMPLEO "); 
	    if(idOrigenCanada==ID_FUENTE_CANADA)sqlString.append(" and OE.FUENTE =  "+ID_FUENTE_CANADA); 
		else sqlString.append(" AND OE.FUENTE IN (" + ID_REGISTRO_PORTAL + "," + ID_FUENTE_SFP + "," + ID_FUENTE_SISNE + ") "); 
	    //sqlString.append(" ORDER BY oe.FECHA_INICIO DESC, oe.ID_OFERTA_EMPLEO DESC");
	    sqlString.append(" ORDER BY oe.ID_OFERTA_EMPLEO DESC");
	    sqlString.append(" )");	    
		sqlString.append(" WHERE ROWNUM <= ?1 ");

		
		Query query = entityManager.createNativeQuery(sqlString.toString());
		query.setParameter(1, String.valueOf(numRegistros));
		
		@SuppressWarnings("unchecked")
		List<Object[]> rows = query.getResultList();

		for(Object[] rowElement : rows){
			OfertaRecienteVO vo = getOfertaRecienteVO(rowElement);
			//OfertaRecienteVO vo = (OfertaRecienteVO) rowElement;
			if(idOrigenCanada==ID_FUENTE_CANADA){
				
				UbicacionCanadaVO ubicacionVo = ofertaUbicacionFacade.getUbicacionOfertaCanada(Utils.toLong(rowElement[0]));

				if (ubicacionVo!=null){
					vo.setVigencia("Salario neto mensual "+Utils.formatMoney(Utils.toDouble(rowElement[5]))+" pesos");
					vo.setUbicacion(ubicacionVo.getProvincia()+", "+ubicacionVo.getCiudad());
				}
			}
			
			ofertasRecientes.add(vo);
			
		}		
		return ofertasRecientes;
	}
	
	private OfertaRecienteVO getOfertaRecienteVO(Object[] ofertaReciente) {
		OfertaRecienteVO vo = new OfertaRecienteVO();
		vo.setIdOfertaEmpleo(Utils.toLong(ofertaReciente[0]));
		vo.setTituloOferta(Utils.toString(ofertaReciente[1]));
		vo.setUbicacion(Utils.toString(ofertaReciente[2]));
		vo.setVigencia(Utils.toString(ofertaReciente[3]));
		return vo;
	}
	
	// TODO ELIMINAR, se cambio por OfertasRecientesDAO.obtenerOfertasRecientesTodas
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<OfertaPorCanalVO> obtenerOfertasRecientesTodas (int numRegistros, long idOrigenCanada) throws PersistenceException{
		
		List<OfertaPorCanalVO> ofertasRecientesTodas = new ArrayList<OfertaPorCanalVO>();
		
		StringBuffer sqlString = new StringBuffer();
		/*
		sqlString.append(" SELECT");
	    sqlString.append(" BOLSA,");
	    sqlString.append(" ID_OFERTA_EMPLEO,");
	    sqlString.append(" TITULO_OFERTA,");
	    sqlString.append(" UBICACION,");
	    sqlString.append(" VIGENCIA,");	    
	    sqlString.append(" SALARIO,");		    
	    sqlString.append(" EMPRESA,");
	    sqlString.append(" GRADO_ESTUDIO,");
	    sqlString.append(" CASE");
	    sqlString.append(" WHEN ((ID_OFERTA_EMPLEO IS NULL) OR (ID_CARRERA_ESPECIALIDAD <= 0)) THEN ' '");
	    sqlString.append(" ELSE F_DESC_CATALOGO(F_CAT_ASOCIADO("+CATALOGO_OPCION_GRADO_ESTUDIOS+", ID_NIVEL_ESTUDIO), ID_CARRERA_ESPECIALIDAD)");
	    sqlString.append(" END AS CARRERA,"); 
	    sqlString.append(" FUNCIONES,");
	    sqlString.append(" EDAD,");	    
	    sqlString.append(" IDIOMA,");
	    sqlString.append(" HORARIO,");	    
	    sqlString.append(" NUMERO_PLAZAS,");	   	    
	    sqlString.append(" CONTACTO,");	    
	    sqlString.append(" FECHA_INICIO,");	    
	    sqlString.append(" FECHA_FIN,");
	    sqlString.append(" HABILIDAD_GENERAL,");
	    sqlString.append(" EXPERIENCIA_ANIOS");
		sqlString.append(" FROM ( ");
		sqlString.append(" SELECT");
	    sqlString.append(" OE.FUENTE as BOLSA,");
		sqlString.append(" OE.ID_OFERTA_EMPLEO as ID_OFERTA_EMPLEO, OE.TITULO_OFERTA as TITULO_OFERTA, ");
	    sqlString.append(" F_DESC_CATALOGO("+CATALOGO_OPCION_ENTIDAD_FEDERATIVA+", ou.id_entidad)||', '|| Mun.MUNICIPIO AS UBICACION,");		
	    sqlString.append(" TO_CHAR(OE.FECHA_INICIO, 'DD')");
	    sqlString.append(" || ' de '");
	    sqlString.append(" || rtrim(TO_CHAR(OE.FECHA_INICIO, 'Month'))");	  
	    sqlString.append(" || ' '");	 
	    sqlString.append(" || trim(TO_CHAR(OE.FECHA_INICIO, 'yyyy'))");	 
	    sqlString.append(" || ' - '");	 
	    sqlString.append(" || TO_CHAR(OE.FECHA_FIN, 'DD')");	 	    
	    sqlString.append(" || ' de '");
	    sqlString.append(" || rtrim(TO_CHAR(OE.FECHA_FIN, 'Month'))");	 
	    sqlString.append(" || ' '");	 	    
	    sqlString.append(" || trim(TO_CHAR(OE.FECHA_FIN,'yyyy')) AS VIGENCIA,");
	    //sqlString.append(" CASE");				    
	    //sqlString.append(" WHEN I.ID_TIPO_PERSONA = "+TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()+" THEN I.NOMBRE || ' ' || I.APELLIDO1 || ' ' || I.APELLIDO2");					    
	    //sqlString.append(" WHEN I.ID_TIPO_PERSONA = "+TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() +" THEN I.RAZON_SOCIAL");
	    //sqlString.append(" WHEN ((NVL (OE.ID_TERCERA_EMPRESA,0)!= 0) AND (J.ID_TIPO_PERSONA = "+TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()+")) THEN J.NOMBRE || ' ' || J.APELLIDO1 || ' ' || J.APELLIDO2");
	    //sqlString.append(" WHEN ((NVL (OE.ID_TERCERA_EMPRESA,0)!= 0) AND (J.ID_TIPO_PERSONA = "+TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() +")) THEN J.RAZON_SOCIAL");	    
	    sqlString.append(" OE.NOMBRE_EMPRESA AS EMPRESA,");
	    sqlString.append(" OE.SALARIO,");
	    sqlString.append(" OE.ID_NIVEL_ESTUDIO,");
	    sqlString.append(" F_DESC_CATALOGO("+CATALOGO_OPCION_GRADO_ESTUDIOS+", OE.ID_NIVEL_ESTUDIO) AS GRADO_ESTUDIO,");
	    sqlString.append(" K.ID_CARRERA_ESPECIALIDAD,");	    	    
	    sqlString.append(" OE.FUNCIONES,");  
	    sqlString.append(" CASE");
	    sqlString.append(" WHEN (OE.EDAD_REQUISITO = "+EDAD_REQUISITO.SI.getIdOpcion()+") THEN EDAD_MINIMA || ' - ' || EDAD_MAXIMA else 'N/A' END AS EDAD,");
	    sqlString.append(" CASE");
	    sqlString.append(" WHEN ((L.ID_OFERTA_EMPLEO IS NULL) OR (L.ID_IDIOMA <= 0)) THEN ' '");	    
	    sqlString.append(" ELSE DESCCATALOGO(1, "+CATALOGO_OPCION_IDIOMAS+" ,L.ID_IDIOMA) || ' ' || DESCCATALOGO(1, "+CATALOGO_OPCION_DOMINIO+", L.ID_DOMINIO)");
	    sqlString.append(" END AS IDIOMA,");
	    sqlString.append(" OE.HORA_ENTRADA || ':00 - ' || OE.HORA_SALIDA || ':00' AS HORARIO,");
	    sqlString.append(" OE.NUMERO_PLAZAS,");
	    sqlString.append(" CASE");	    
	    sqlString.append(" WHEN ((OE.CONTACTO_TEL = "+CONTACTO_TELEFONO.SI.getIdContactoTelefono()+") AND (OE.CONTACTO_CORREO = "+CONTACTO_CORREO.SI.getIdContactoCorreo()+")) THEN 'Teléfono y Correo Electrónico '");
	    sqlString.append(" WHEN ((OE.CONTACTO_TEL = "+CONTACTO_TELEFONO.SI.getIdContactoTelefono()+") AND (OE.CONTACTO_CORREO = "+CONTACTO_CORREO.NO.getIdContactoCorreo()+")) THEN 'Teléfono'");	  	    
	    sqlString.append(" WHEN ((OE.CONTACTO_TEL = "+CONTACTO_TELEFONO.NO.getIdContactoTelefono()+") AND (OE.CONTACTO_CORREO = "+CONTACTO_CORREO.SI.getIdContactoCorreo()+")) THEN 'Correo Electrónico'");
	    sqlString.append(" ELSE ' '");	  
	    sqlString.append(" END AS CONTACTO,");
	    sqlString.append(" OE.FECHA_INICIO,");
	    sqlString.append(" OE.FECHA_FIN,");
	    sqlString.append(" OE.HABILIDAD_GENERAL,");
	    sqlString.append(" OE.EXPERIENCIA_ANIOS");	    
	    sqlString.append(" FROM EMPRESA I,");
	    sqlString.append(" OFERTA_EMPLEO OE");
	    sqlString.append(" LEFT JOIN OFERTA_UBICACION ou ON oe.ID_OFERTA_EMPLEO = ou.ID_OFERTA_EMPLEO");
	    sqlString.append(" LEFT JOIN MUNICIPIO mun ON ou.id_municipio = mun.id_municipio AND ou.id_entidad = mun.id_entidad");
	    sqlString.append(" LEFT JOIN OFERTA_CARRERA_ESPEC K ON OE.ID_OFERTA_EMPLEO = K.ID_OFERTA_EMPLEO AND K.PRINCIPAL = "+MULTIREGISTRO.PRINCIPAL.getIdOpcion());
	    sqlString.append(" LEFT JOIN OFERTA_IDIOMA L ON OE.ID_OFERTA_EMPLEO = L.ID_OFERTA_EMPLEO AND L.PRINCIPAL = "+MULTIREGISTRO.PRINCIPAL.getIdOpcion());	    
	    //sqlString.append(" LEFT JOIN TERCERA_EMPRESA J ON OE.ID_TERCERA_EMPRESA = J.ID_TERCERA_EMPRESA");
	    sqlString.append(" WHERE OE.ESTATUS = " + ESTATUS.ACTIVO.getIdOpcion());
	    sqlString.append(" AND ou.ID_OFERTA_EMPLEO = oe.ID_OFERTA_EMPLEO");
	    //sqlString.append(" AND dom.id_tipo_propietario = "+TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
	    sqlString.append(" AND OE.ID_EMPRESA = I.ID_EMPRESA");
	    if(idOrigenCanada==ID_FUENTE_CANADA)sqlString.append(" and OE.FUENTE =  "+ID_FUENTE_CANADA); 
		else sqlString.append(" AND OE.FUENTE IN (" + ID_REGISTRO_PORTAL + "," + ID_FUENTE_SFP + "," + ID_FUENTE_SISNE + ") "); 
	    sqlString.append(" ORDER BY oe.FECHA_INICIO DESC, oe.ID_OFERTA_EMPLEO DESC");
	    sqlString.append(" )");	    
		sqlString.append(" WHERE ROWNUM <= ?1 ");
		*/
		
		sqlString.append("SELECT BOLSA, ID_OFERTA_EMPLEO, TITULO_OFERTA, UBICACION, VIGENCIA, SALARIO, EMPRESA, GRADO_ESTUDIO, CARRERA, FUNCIONES, EDAD, IDIOMA, HORARIO, NUMERO_PLAZAS, CONTACTO, FECHA_INICIO, FECHA_FIN, HABILIDAD_GENERAL, EXPERIENCIA_ANIOS ");
		sqlString.append("FROM MV_OFERTAS_RECIENTES ");
		sqlString.append("WHERE ROWNUM <= ?1 ");
        if(idOrigenCanada == ID_FUENTE_CANADA)
        	sqlString.append(" and BOLSA =  "+ID_FUENTE_CANADA); 
		else 
			sqlString.append(" AND BOLSA IN (" + ID_REGISTRO_PORTAL + "," + ID_FUENTE_SFP + "," + ID_FUENTE_SISNE + ") "); 
		
	    Query query = entityManager.createNativeQuery(sqlString.toString());

	    query.setParameter(1, String.valueOf(numRegistros));
	    
		@SuppressWarnings("unchecked")
	    List<Object[]> rows = query.getResultList();
	    
	    for (Object[] rowElement:rows) {
	    	OfertaPorCanalVO ofertaRecienteDetalle = getOfertaRecienteDetalle(rowElement);	    	
	    	if(idOrigenCanada==ID_FUENTE_CANADA){
				
				UbicacionCanadaVO ubicacionVo = ofertaUbicacionFacade.getUbicacionOfertaCanada(Utils.toLong(rowElement[1]));
				if(ubicacionVo!=null)ofertaRecienteDetalle.setUbicacion(ubicacionVo.getProvincia()+", "+ubicacionVo.getCiudad());
				else ofertaRecienteDetalle.setUbicacion("N/E,N/E");
			}
	    	ofertasRecientesTodas.add(ofertaRecienteDetalle);
	    }
		return ofertasRecientesTodas;
	}
	
	private OfertaPorCanalVO getOfertaRecienteDetalle(Object[] ofertaRecienteDetalle) {
		OfertaPorCanalVO vo = new OfertaPorCanalVO();
		
		if (Utils.toLong(ofertaRecienteDetalle[0]) == BOLSA_TRABAJO.PORTAL_EMPLEO.getIdOpcion()) {
			vo.setBolsaTrabajo(BOLSA_TRABAJO.PORTAL_EMPLEO.getOpcion());
		} else if (Utils.toLong(ofertaRecienteDetalle[0]) == BOLSA_TRABAJO.TRABAJA_EN.getIdOpcion()) {
			vo.setBolsaTrabajo(BOLSA_TRABAJO.TRABAJA_EN.getOpcion());
		}else if (Utils.toLong(ofertaRecienteDetalle[0]) == BOLSA_TRABAJO.CANADA.getIdOpcion()) {
			vo.setBolsaTrabajo(BOLSA_TRABAJO.CANADA.getOpcion());
		}else if (Utils.toLong(ofertaRecienteDetalle[0]) == BOLSA_TRABAJO.SNE.getIdOpcion()) {
			vo.setBolsaTrabajo(BOLSA_TRABAJO.SNE.getOpcion());
		} 
		//FIXME: Que pasa si pertenece a otra fuente???
		//SNE = SISNE ???
		//Catalogos.CATALOGO_FUENTE_QUE_REGISTRA_OFERTA.ABRIENDO_ESPACIOS

		vo.setIdOfertaEmpleo(Utils.toLong(ofertaRecienteDetalle[1]));
		vo.setTituloOferta(Utils.toString(ofertaRecienteDetalle[2]));		
		vo.setUbicacion(Utils.toString(ofertaRecienteDetalle[3]));		
		vo.setSalario(Utils.toDouble(ofertaRecienteDetalle[5]));
		vo.setEmpresa(Utils.toString(ofertaRecienteDetalle[6]));		
		vo.setGradoEstudio(Utils.toString(ofertaRecienteDetalle[7]));		
		vo.setCarrera(Utils.toString(ofertaRecienteDetalle[8]));
		vo.setFunciones(Utils.toString(ofertaRecienteDetalle[9]));
		vo.setEdad(Utils.toString(ofertaRecienteDetalle[10]));
		vo.setIdiomas(Utils.toString(ofertaRecienteDetalle[11]));		
		vo.setHorario(Utils.toString(ofertaRecienteDetalle[12]));	
		vo.setNumeroPlazas(Utils.toInt(ofertaRecienteDetalle[13]));
		vo.setMedioContacto(Utils.toString(ofertaRecienteDetalle[14]));
		vo.setFechaInicio(Utils.toDate(ofertaRecienteDetalle[15]));		
		vo.setFechaFin(Utils.toDate(ofertaRecienteDetalle[16]));
		vo.setHabilidadGeneral(Utils.toString(ofertaRecienteDetalle[17]));
		vo.setExperiencia(Utils.toString(ofertaRecienteDetalle[18]));
		return vo;
	}

	/*private OfertaPorCanalVO getOfertaPorCanalVO(Object[] ofertaReciente) {
		
		OfertaPorCanalVO vo = new OfertaPorCanalVO();
		
		if (Utils.toLong(ofertaReciente[0]) == BOLSA_TRABAJO.PORTAL_EMPLEO.getIdOpcion())
				vo.setBolsaTrabajo(BOLSA_TRABAJO.PORTAL_EMPLEO.getOpcion());
		else if (Utils.toLong(ofertaReciente[0]) == BOLSA_TRABAJO.TRABAJA_EN.getIdOpcion())
				vo.setBolsaTrabajo(BOLSA_TRABAJO.TRABAJA_EN.getOpcion());
		
		vo.setIdOfertaEmpleo(Utils.toLong(ofertaReciente[1]));		
		vo.setTituloOferta(Utils.toString(ofertaReciente[2]));
		vo.setUbicacion(Utils.toString(ofertaReciente[3]));		
		vo.setEmpresa(Utils.toString(ofertaReciente[4]));
		vo.setSalario(Utils.toDouble(ofertaReciente[5]));
		vo.setGradoEstudio(Utils.toString(ofertaReciente[6]));
		vo.setCarrera(Utils.toString(ofertaReciente[7]));
		vo.setFunciones(Utils.toString(ofertaReciente[8]));
		vo.setEdad(Utils.toString(ofertaReciente[9]));		
		vo.setIdiomas(Utils.toString(ofertaReciente[10]));		
		vo.setHorario(Utils.toString(ofertaReciente[11]));
		vo.setNumeroPlazas(Utils.toInt(ofertaReciente[12]));
		vo.setMedioContacto(Utils.toString(ofertaReciente[13]));	
		return vo;
	}*/
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<OfertaRecienteVO> obtenerOfertasDestacadas(int numRegistros, double salarioMinimoDoctorado,
			double salarioMinimoMaestria, double salarioMinimoLicenciatura )
			throws PersistenceException {
				int numeroRegistrosMasRespaldo = numRegistros + 5;	
		        StringBuffer sqlString = new StringBuffer();
		        		        
		        sqlString.append("SELECT ");
				sqlString.append(" ID_OFERTA_EMPLEO ");
				sqlString.append(" ,TITULO_OFERTA ");
				sqlString.append(" ,UBICACION ");
				sqlString.append(" ,FINI || ' - ' || FFIN AS VIGENCIA");
				sqlString.append(" FROM  ");
				sqlString.append(" ( ");
				sqlString.append(" SELECT ");
				sqlString.append(" OE.ID_NIVEL_ESTUDIO ");
				sqlString.append(" ,OE.SALARIO ");
				sqlString.append(" ,OE.ID_OFERTA_EMPLEO ");
				sqlString.append(" ,OE.TITULO_OFERTA ");
				sqlString.append(" ,CO.OPCION || ', ' || M.MUNICIPIO AS UBICACION ");
				sqlString.append(" ,to_char(OE.FECHA_INICIO, 'DD') || ' de ' ||  rtrim(to_char(OE.FECHA_INICIO, 'Month')) || ' ' || trim(to_char(OE.FECHA_INICIO, 'yyyy')) FINI ");
				sqlString.append(" ,to_char(OE.FECHA_FIN, 'DD') || ' de ' ||  rtrim(to_char(OE.FECHA_FIN, 'Month')) || ' ' || trim(to_char(OE.FECHA_FIN, 'yyyy')) FFIN ");						
				sqlString.append(" , CASE  ");
				sqlString.append(" 	WHEN ((ID_NIVEL_ESTUDIO=" + GRADO_ESTUDIOS.DOCTORADO.getIdOpcion() + ") AND (SALARIO>?1)) THEN 'SI'  ");
				sqlString.append(" 	WHEN ((ID_NIVEL_ESTUDIO=" + GRADO_ESTUDIOS.MAESTRIA.getIdOpcion() + ") AND (SALARIO>?2)) THEN 'SI'  ");
				sqlString.append(" 	WHEN ((ID_NIVEL_ESTUDIO=" + GRADO_ESTUDIOS.LICENCIATURA.getIdOpcion() + ") AND (SALARIO>?3)) THEN 'SI'  ");
				sqlString.append(" 	ELSE 'NO'  ");
				sqlString.append(" END AS MOSTRAR  ");				
				sqlString.append(" FROM  ");
				sqlString.append(" OFERTA_EMPLEO OE "); 
				sqlString.append(" ,OFERTA_UBICACION OU ");
				sqlString.append(" ,CATALOGO_OPCION CO ");
				sqlString.append(" ,MUNICIPIO M ");
				sqlString.append(" WHERE ");
				sqlString.append(" OU.ID_OFERTA_EMPLEO = OE.ID_OFERTA_EMPLEO ");
				sqlString.append(" AND OU.ID_ENTIDAD = M.ID_ENTIDAD ");
				sqlString.append(" AND OU.ID_MUNICIPIO = M.ID_MUNICIPIO ");
				sqlString.append(" AND OU.ID_ENTIDAD = CO.ID_CATALOGO_OPCION ");
				sqlString.append(" AND CO.ID_CATALOGO = " + CATALOGO_OPCION_ENTIDAD_FEDERATIVA);
				//sqlString.append(" AND D.ID_TIPO_PROPIETARIO = " + TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
				sqlString.append(" AND OE.ESTATUS = " + ESTATUS.ACTIVO.getIdOpcion());
			    sqlString.append(" ORDER BY oe.FECHA_INICIO DESC, OE.ID_NIVEL_ESTUDIO DESC, OE.SALARIO DESC, OE.ID_OFERTA_EMPLEO DESC");				
				sqlString.append(" ) ");
				sqlString.append(" WHERE ROWNUM <= ?4 AND MOSTRAR='SI'");	
				Query query = entityManager.createNativeQuery(sqlString.toString());
				query.setParameter(1, String.valueOf(salarioMinimoDoctorado));
				query.setParameter(2, String.valueOf(salarioMinimoMaestria));
				query.setParameter(3, String.valueOf(salarioMinimoLicenciatura));
				query.setParameter(4, String.valueOf(numeroRegistrosMasRespaldo));
				
				@SuppressWarnings("unchecked")
				List<Object[]> rows = query.getResultList();
				
				List<OfertaRecienteVO> ofertasDestacadas = new ArrayList<OfertaRecienteVO>();
				
				for(Object[] rowElement: rows) {
					try{
						OfertaRecienteVO vo = new OfertaRecienteVO();
						vo.setIdOfertaEmpleo(Utils.toLong(rowElement[0]));
						vo.setTituloOferta(Utils.toString(rowElement[1]));
						vo.setUbicacion(Utils.toString(rowElement[2]));
						vo.setVigencia(Utils.toString(rowElement[3]));		
						if(ofertasDestacadas.size()<=numRegistros){
							ofertasDestacadas.add(vo);
						}												
					} catch (Exception e) {			
						e.printStackTrace();
						logger.error(e);	
					}				
				}
			
		return ofertasDestacadas;
	}

	// TODO Eliminar - Se cambio por OfertasRecientesDAO.obtenerOfertasDestacadasTodas
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<OfertaPorCanalVO> obtenerOfertasDestacadasTodas(int numRegistros, 
                                                                double salarioMinimoDoctorado,
                                                                double salarioMinimoMaestria, 
                                                                double salarioMinimoLicenciatura) throws PersistenceException {
		
		StringBuffer sqlString = new StringBuffer();
        /*
		sqlString.append(" SELECT");
	    sqlString.append(" BOLSA,");
	    sqlString.append(" ID_OFERTA_EMPLEO,");
	    sqlString.append(" TITULO_OFERTA,");
	    sqlString.append(" UBICACION,");
	    sqlString.append(" FINI || ' - ' || FFIN AS VIGENCIA,");	    
	    sqlString.append(" SALARIO,");		    
	    sqlString.append(" EMPRESA,");
	    sqlString.append(" GRADO_ESTUDIO,");
	    sqlString.append(" CASE");
	    sqlString.append("   WHEN ((ID_OFERTA_EMPLEO IS NULL) OR (ID_CARRERA_ESPECIALIDAD <= 0)) THEN ' '");
	    sqlString.append("   ELSE F_DESC_CATALOGO(F_CAT_ASOCIADO("+CATALOGO_OPCION_GRADO_ESTUDIOS+", ID_NIVEL_ESTUDIO), ID_CARRERA_ESPECIALIDAD)");
	    sqlString.append(" END AS CARRERA,");
	    sqlString.append(" FUNCIONES,");	   	    
	    sqlString.append(" EDAD,");	    
	    sqlString.append(" IDIOMA,");
	    sqlString.append(" HORARIO,");	    
	    sqlString.append(" NUMERO_PLAZAS,");	   	    
	    sqlString.append(" CONTACTO,");	    
	    sqlString.append(" FECHA_INICIO,");	    
	    sqlString.append(" FECHA_FIN,");
	    sqlString.append(" HABILIDAD_GENERAL,");
	    sqlString.append(" EXPERIENCIA_ANIOS");	    
		sqlString.append(" FROM ( ");		
		sqlString.append(" SELECT");
		sqlString.append(" OE.FUENTE AS BOLSA,");		
		sqlString.append(" OE.ID_NIVEL_ESTUDIO");
		sqlString.append(" ,OE.SALARIO");
		sqlString.append(" ,OE.ID_OFERTA_EMPLEO");		
		sqlString.append(" ,OE.TITULO_OFERTA");
		sqlString.append(" ,CO.OPCION || ', ' || M.MUNICIPIO AS UBICACION");
		sqlString.append(" ,to_char(OE.FECHA_INICIO, 'DD') || ' de ' ||  rtrim(to_char(OE.FECHA_INICIO, 'Month')) || ' ' || trim(to_char(OE.FECHA_INICIO, 'yyyy')) FINI");		
		sqlString.append(" ,to_char(OE.FECHA_FIN, 'DD') || ' de ' ||  rtrim(to_char(OE.FECHA_FIN, 'Month')) || ' ' || trim(to_char(OE.FECHA_FIN, 'yyyy')) FFIN");
		sqlString.append(" , CASE  ");
		sqlString.append(" 	WHEN ((ID_NIVEL_ESTUDIO=" + GRADO_ESTUDIOS.DOCTORADO.getIdOpcion() + ") AND (SALARIO>?1)) THEN 'SI'  ");
		sqlString.append(" 	WHEN ((ID_NIVEL_ESTUDIO=" + GRADO_ESTUDIOS.MAESTRIA.getIdOpcion() + ") AND (SALARIO>?2)) THEN 'SI'  ");
		sqlString.append(" 	WHEN ((ID_NIVEL_ESTUDIO=" + GRADO_ESTUDIOS.LICENCIATURA.getIdOpcion() + ") AND (SALARIO>?3)) THEN 'SI'  ");
		sqlString.append(" 	ELSE 'NO'  ");
		sqlString.append(" END AS MOSTRAR,  ");
	   // sqlString.append(" CASE");				    
	    //sqlString.append(" WHEN I.ID_TIPO_PERSONA = "+TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()+" THEN I.NOMBRE || ' ' || I.APELLIDO1 || ' ' || I.APELLIDO2");					    
	    //sqlString.append(" WHEN I.ID_TIPO_PERSONA = "+TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() +" THEN I.RAZON_SOCIAL");
	    //sqlString.append(" WHEN ((NVL (OE.ID_TERCERA_EMPRESA,0)!= 0) AND (J.ID_TIPO_PERSONA = "+TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()+")) THEN J.NOMBRE || ' ' || J.APELLIDO1 || ' ' || J.APELLIDO2");
	    //sqlString.append(" WHEN ((NVL (OE.ID_TERCERA_EMPRESA,0)!= 0) AND (J.ID_TIPO_PERSONA = "+TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() +")) THEN J.RAZON_SOCIAL");	    
	    sqlString.append(" OE.NOMBRE_EMPRESA AS EMPRESA,");
	    sqlString.append(" F_DESC_CATALOGO("+CATALOGO_OPCION_GRADO_ESTUDIOS+", OE.ID_NIVEL_ESTUDIO) AS GRADO_ESTUDIO,");
	    sqlString.append(" K.ID_CARRERA_ESPECIALIDAD,");
	    sqlString.append(" OE.FUNCIONES,");
	    sqlString.append(" CASE");
	    sqlString.append("   WHEN (OE.EDAD_REQUISITO = "+EDAD_REQUISITO.SI.getIdOpcion()+") THEN EDAD_MINIMA || ' - ' || EDAD_MAXIMA else 'N/A' ");    	    
		sqlString.append(" END AS EDAD,");		
	    sqlString.append(" CASE");
	    sqlString.append("  WHEN ((L.ID_OFERTA_EMPLEO IS NULL) OR (L.ID_IDIOMA <= 0)) THEN ' '");	    
	    sqlString.append("  ELSE DESCCATALOGO(1, "+CATALOGO_OPCION_IDIOMAS+" ,L.ID_IDIOMA) || ' ' || DESCCATALOGO(1, "+CATALOGO_OPCION_DOMINIO+", L.ID_DOMINIO)");
	    sqlString.append(" END AS IDIOMA,");	    
	    sqlString.append(" OE.HORA_ENTRADA || ':00 - ' || OE.HORA_SALIDA || ':00' AS HORARIO,");
	    sqlString.append(" OE.NUMERO_PLAZAS,");
	    sqlString.append(" CASE");	    
	    sqlString.append("   WHEN ((OE.CONTACTO_TEL = "+CONTACTO_TELEFONO.SI.getIdContactoTelefono()+") AND (OE.CONTACTO_CORREO = "+CONTACTO_CORREO.SI.getIdContactoCorreo()+")) THEN 'Teléfono y Correo Electrónico '");
	    sqlString.append("   WHEN ((OE.CONTACTO_TEL = "+CONTACTO_TELEFONO.SI.getIdContactoTelefono()+") AND (OE.CONTACTO_CORREO = "+CONTACTO_CORREO.NO.getIdContactoCorreo()+")) THEN 'Teléfono'");	  	    
	    sqlString.append("   WHEN ((OE.CONTACTO_TEL = "+CONTACTO_TELEFONO.NO.getIdContactoTelefono()+") AND (OE.CONTACTO_CORREO = "+CONTACTO_CORREO.SI.getIdContactoCorreo()+")) THEN 'Correo Electrónico'");
	    sqlString.append("   ELSE ' '");	  
	    sqlString.append(" END AS CONTACTO,");
	    sqlString.append(" OE.FECHA_INICIO,");
	    sqlString.append(" OE.FECHA_FIN,");
	    sqlString.append(" OE.HABILIDAD_GENERAL,");
	    sqlString.append(" OE.EXPERIENCIA_ANIOS");	  	    
		sqlString.append(" FROM");
		sqlString.append(" OFERTA_EMPLEO OE");
		//sqlString.append(" LEFT JOIN TERCERA_EMPRESA J ON OE.ID_TERCERA_EMPRESA = J.ID_TERCERA_EMPRESA");		
	    sqlString.append(" LEFT JOIN OFERTA_CARRERA_ESPEC K ON OE.ID_OFERTA_EMPLEO = K.ID_OFERTA_EMPLEO AND K.PRINCIPAL = "+MULTIREGISTRO.PRINCIPAL.getIdOpcion());
	    sqlString.append(" LEFT JOIN OFERTA_IDIOMA L ON OE.ID_OFERTA_EMPLEO = L.ID_OFERTA_EMPLEO AND L.PRINCIPAL = "+MULTIREGISTRO.PRINCIPAL.getIdOpcion());		
		sqlString.append(" ,OFERTA_UBICACION OU");
		sqlString.append(" ,CATALOGO_OPCION CO");
		sqlString.append(" ,MUNICIPIO M");		
		sqlString.append(" ,EMPRESA I");
		sqlString.append(" WHERE");
		sqlString.append(" OU.ID_OFERTA_EMPLEO = OE.ID_OFERTA_EMPLEO");
		sqlString.append(" AND OU.ID_ENTIDAD = M.ID_ENTIDAD");		
		sqlString.append(" AND OU.ID_MUNICIPIO = M.ID_MUNICIPIO");
		sqlString.append(" AND OU.ID_ENTIDAD = CO.ID_CATALOGO_OPCION");	
		sqlString.append(" AND CO.ID_CATALOGO = " + CATALOGO_OPCION_ENTIDAD_FEDERATIVA);
		//sqlString.append(" AND D.ID_TIPO_PROPIETARIO = " + TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());	
		sqlString.append(" AND OE.ESTATUS = " + ESTATUS.ACTIVO.getIdOpcion());
		sqlString.append(" AND OE.ID_EMPRESA = I.ID_EMPRESA");
		sqlString.append(" ORDER BY oe.FECHA_INICIO DESC, OE.ID_NIVEL_ESTUDIO DESC, OE.SALARIO DESC, OE.ID_OFERTA_EMPLEO DESC ");
		sqlString.append(" )");			
		sqlString.append(" WHERE ROWNUM <= ?4 AND MOSTRAR='SI'");
		*/
		sqlString.append("SELECT BOLSA, ID_OFERTA_EMPLEO, TITULO_OFERTA, UBICACION, VIGENCIA, SALARIO, EMPRESA, GRADO_ESTUDIO, CARRERA, FUNCIONES, EDAD, IDIOMA, HORARIO, NUMERO_PLAZAS, CONTACTO, FECHA_INICIO, FECHA_FIN, HABILIDAD_GENERAL, EXPERIENCIA_ANIOS ");
		sqlString.append("FROM MV_OFERTAS_DESTACADAS ");
		sqlString.append("WHERE ROWNUM <= ?1 ");
		
		List<OfertaPorCanalVO> ofertasDestacadasTodas = new ArrayList<OfertaPorCanalVO>();
		
		try{
			Query query = entityManager.createNativeQuery(sqlString.toString());
			
			//query.setParameter(1, String.valueOf(salarioMinimoDoctorado));
			//System.out.println("srojas EmpresaFacade.obtenerOfertasDestacadasTodas salarioMinimoDoctorado: "+String.valueOf(salarioMinimoDoctorado));
			
			//query.setParameter(2, String.valueOf(salarioMinimoMaestria));
			//System.out.println("srojas EmpresaFacade.obtenerOfertasDestacadasTodas salarioMinimoDoctorado: "+String.valueOf(salarioMinimoMaestria));
			
			//query.setParameter(3, String.valueOf(salarioMinimoLicenciatura));
			//System.out.println("srojas EmpresaFacade.obtenerOfertasDestacadasTodas salarioMinimoDoctorado: "+String.valueOf(salarioMinimoLicenciatura));
			
			//query.setParameter(4, String.valueOf(numRegistros));
			query.setParameter(1, String.valueOf(numRegistros));
			
			@SuppressWarnings("unchecked")
		    List<Object[]> rows = query.getResultList();
						
		    for (Object[] rowElement:rows) {	    	
		    	OfertaPorCanalVO ofertaRecienteDetalle = getOfertaRecienteDetalle(rowElement);	   
		    	if(null != ofertaRecienteDetalle){
		    		ofertasDestacadasTodas.add(ofertaRecienteDetalle);
		    	}	    	
		    }
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return ofertasDestacadasTodas;
	}
	

	@Override
	public List<OfertaRecienteVO> obtenerOfertasGendarmeria(int numRegistros)
			throws PersistenceException {
	    StringBuffer sqlString = new StringBuffer();
	    /*
	    sqlString.append(" SELECT OE.ID_OFERTA_EMPLEO as ID_OFERTA_EMPLEO, ");
	    sqlString.append("        OE.TITULO_OFERTA as TITULO_OFERTA, ");
	    sqlString.append("        F_DESC_CATALOGO(25, ou.id_entidad) || ', ' || Mun.MUNICIPIO AS UBICACION,  ");
	    sqlString.append("        OE.SALARIO  ");
	    sqlString.append(" FROM OFERTA_EMPLEO OE ");
	    sqlString.append(" LEFT JOIN OFERTA_UBICACION ou ON oe.ID_OFERTA_EMPLEO = ou.ID_OFERTA_EMPLEO ");
	    sqlString.append(" LEFT JOIN MUNICIPIO 	  mun ON ou.id_municipio 	 = mun.id_municipio AND ou.id_entidad = mun.id_entidad ");	
	    sqlString.append(" WHERE CATSEARCH(oe.TITULO_OFERTA, 'GENDARME*', null) > 0 AND ROWNUM <= " + numRegistros);
	    sqlString.append(" ORDER BY oe.FECHA_INICIO DESC, oe.ID_OFERTA_EMPLEO DESC ");
		*/
	    sqlString.append(" SELECT ID_OFERTA_EMPLEO, TITULO_OFERTA, UBICACION, SALARIO");
	    sqlString.append(" FROM MV_OFERTAS_GENDARMERIA1");
	    sqlString.append(" WHERE ROWNUM <= ?1");
	    
	    Query query = entityManager.createNativeQuery(sqlString.toString());
	    query.setParameter(1, numRegistros);
	    
	    @SuppressWarnings("unchecked")
	    List<Object[]> rows =  query.getResultList();
	    List<OfertaRecienteVO> ofertasGendarmeria = new ArrayList<OfertaRecienteVO>();
	    for (Object[] rowElement : rows) {
			ofertasGendarmeria.add(getOfertaRecienteVO(rowElement));
		}
	    return ofertasGendarmeria;
	}

	// TODO Eliminar - Se cambio por OfertasRecientesDAO.obtenerOfertasGendarmeriaTodas
	@Override
	public List<OfertaPorCanalVO> obtenerOfertasGendarmeriaTodas(int numRegistros) throws PersistenceException {
		StringBuffer sqlString = new StringBuffer();
		/*
		sqlString.append(" SELECT BOLSA, ID_OFERTA_EMPLEO, TITULO_OFERTA, UBICACION, VIGENCIA, SALARIO, EMPRESA, GRADO_ESTUDIO, ");
		sqlString.append(" CASE");
		sqlString.append(" 		WHEN ((ID_OFERTA_EMPLEO IS NULL) OR (ID_CARRERA_ESPECIALIDAD <= 0)) THEN ' ' ");
		sqlString.append("		ELSE F_DESC_CATALOGO(F_CAT_ASOCIADO("+ CATALOGO_OPCION_GRADO_ESTUDIOS + ", ID_NIVEL_ESTUDIO), ID_CARRERA_ESPECIALIDAD) ");
		sqlString.append(" END AS CARRERA, FUNCIONES, EDAD, IDIOMA, HORARIO, NUMERO_PLAZAS, CONTACTO, FECHA_INICIO, FECHA_FIN, HABILIDAD_GENERAL, EXPERIENCIA_ANIOS ");
		sqlString.append(" FROM ( ");
		sqlString.append(" 		  SELECT OE.FUENTE as BOLSA, OE.ID_OFERTA_EMPLEO as ID_OFERTA_EMPLEO, OE.TITULO_OFERTA as TITULO_OFERTA, ");
		sqlString.append("               F_DESC_CATALOGO(" + CATALOGO_OPCION_ENTIDAD_FEDERATIVA +", ou.id_entidad)||', '|| Mun.MUNICIPIO AS UBICACION, ");
		sqlString.append("               TO_CHAR(OE.FECHA_INICIO, 'DD') || ' de ' || rtrim(TO_CHAR(OE.FECHA_INICIO, 'Month')) || ' ' || trim(TO_CHAR(OE.FECHA_INICIO, 'yyyy')) || ' - ' || TO_CHAR(OE.FECHA_FIN, 'DD') || ' de ' || rtrim(TO_CHAR(OE.FECHA_FIN, 'Month')) || ' ' || trim(TO_CHAR(OE.FECHA_FIN,'yyyy')) AS VIGENCIA, ");
		sqlString.append(" 				 OE.NOMBRE_EMPRESA AS EMPRESA, OE.SALARIO, OE.ID_NIVEL_ESTUDIO, F_DESC_CATALOGO(" + CATALOGO_OPCION_GRADO_ESTUDIOS + ", OE.ID_NIVEL_ESTUDIO) AS GRADO_ESTUDIO, ");
		sqlString.append(" 				 K.ID_CARRERA_ESPECIALIDAD, OE.FUNCIONES, ");
		sqlString.append("		  		 CASE ");
		sqlString.append(" 				 	 WHEN (OE.EDAD_REQUISITO = " + EDAD_REQUISITO.SI.getIdOpcion() + ") THEN EDAD_MINIMA || ' - ' || EDAD_MAXIMA ");
		sqlString.append(" 				 	 else 'N/A' ");
		sqlString.append("  	  		 END AS EDAD, ");
		sqlString.append(" 		  		 CASE ");
		sqlString.append(" 			     	 WHEN ((L.ID_OFERTA_EMPLEO IS NULL) OR (L.ID_IDIOMA <= 0)) THEN ' ' ");
		sqlString.append(" 				 	 ELSE DESCCATALOGO(1, " + CATALOGO_OPCION_IDIOMAS + " ,L.ID_IDIOMA) || ' ' || DESCCATALOGO(1, " + CATALOGO_OPCION_DOMINIO + ", L.ID_DOMINIO) ");
		sqlString.append("		  		 END AS IDIOMA, ");
		sqlString.append("		  		 OE.HORA_ENTRADA || ':00 - ' || OE.HORA_SALIDA || ':00' AS HORARIO, OE.NUMERO_PLAZAS, ");
		sqlString.append(" 		  		 CASE ");
		sqlString.append("				 	 WHEN ((OE.CONTACTO_TEL = " + CONTACTO_TELEFONO.SI.getIdContactoTelefono() + ") AND (OE.CONTACTO_CORREO = " + CONTACTO_TELEFONO.SI.getIdContactoTelefono() + ")) THEN 'Teléfono y Correo Electrónico ' ");
		sqlString.append("				 	 WHEN ((OE.CONTACTO_TEL = " + CONTACTO_TELEFONO.SI.getIdContactoTelefono() + ") AND (OE.CONTACTO_CORREO = " + CONTACTO_TELEFONO.NO.getIdContactoTelefono() + ")) THEN 'Teléfono' ");
		sqlString.append("				 	 WHEN ((OE.CONTACTO_TEL = " + CONTACTO_TELEFONO.NO.getIdContactoTelefono() + ") AND (OE.CONTACTO_CORREO = " + CONTACTO_TELEFONO.SI.getIdContactoTelefono() + ")) THEN 'Correo Electrónico' ");
		sqlString.append("		  		 	 ELSE ' ' ");
		sqlString.append("		  		 END AS CONTACTO, ");
		sqlString.append("		  		 OE.FECHA_INICIO, OE.FECHA_FIN, OE.HABILIDAD_GENERAL, OE.EXPERIENCIA_ANIOS ");
		sqlString.append(" 		  FROM EMPRESA I, OFERTA_EMPLEO OE ");
		sqlString.append("		  LEFT JOIN OFERTA_UBICACION ou ON oe.ID_OFERTA_EMPLEO = ou.ID_OFERTA_EMPLEO ");
		sqlString.append("		  LEFT JOIN MUNICIPIO mun ON ou.id_municipio = mun.id_municipio AND ou.id_entidad = mun.id_entidad ");
		sqlString.append("		  LEFT JOIN OFERTA_CARRERA_ESPEC K ON OE.ID_OFERTA_EMPLEO = K.ID_OFERTA_EMPLEO AND K.PRINCIPAL = " + MULTIREGISTRO.PRINCIPAL.getIdOpcion());
		sqlString.append("		  LEFT JOIN OFERTA_IDIOMA L ON OE.ID_OFERTA_EMPLEO = L.ID_OFERTA_EMPLEO AND L.PRINCIPAL = " + MULTIREGISTRO.PRINCIPAL.getIdOpcion());
		sqlString.append("		  WHERE CATSEARCH(oe.TITULO_OFERTA, 'GENDARME*', null) > 0 AND ou.ID_OFERTA_EMPLEO = oe.ID_OFERTA_EMPLEO ");
		sqlString.append("		  		AND OE.ID_EMPRESA = I.ID_EMPRESA AND OE.FUENTE IN (" + ID_REGISTRO_PORTAL + "," + ID_FUENTE_SFP + "," + ID_FUENTE_SISNE + ") ");
		sqlString.append("		  ORDER BY oe.FECHA_INICIO DESC, oe.ID_OFERTA_EMPLEO DESC ");
		sqlString.append("		) ");
		sqlString.append(" WHERE ROWNUM <= 100 ");
		*/
		sqlString.append(" SELECT BOLSA, ID_OFERTA_EMPLEO, TITULO_OFERTA, UBICACION, VIGENCIA, SALARIO, EMPRESA, GRADO_ESTUDIO, CARRERA, FUNCIONES, EDAD, IDIOMA, HORARIO, NUMERO_PLAZAS, CONTACTO, FECHA_INICIO, FECHA_FIN, HABILIDAD_GENERAL, EXPERIENCIA_ANIOS");
		sqlString.append(" FROM MV_OFERTAS_GENDARMERIA2");
		
		List<OfertaPorCanalVO> ofertasGendarmeriaTodas = new ArrayList<OfertaPorCanalVO>();
		
		try{
			//System.out.println("Facade - QUERY GENDARMERIA ------->" + sqlString.toString());
			Query query = entityManager.createNativeQuery(sqlString.toString());
//			query.setParameter(1, numRegistros);
			@SuppressWarnings("unchecked")
			List<Object[]> rows = query.getResultList();
			
			for (Object[] rowElement : rows) {
				//System.out.println("Facade - OFERTA GENDARMERIA ------->" );
				OfertaPorCanalVO ofertaGendarmeriaDetalle = getOfertaRecienteDetalle(rowElement);
				if(null != ofertaGendarmeriaDetalle){
					//System.out.println("Facade - OFERTA DETALLE NO NULO ------->" );
					ofertasGendarmeriaTodas.add(ofertaGendarmeriaDetalle);
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		//System.out.println("Facade - FIN GENDARMERIA ------->" + sqlString.toString());		
		return ofertasGendarmeriaTodas;
	}
	
	public long obtenerIdEmpresa() {
		long idEmpresa  = -1;
		StringBuffer sqlString = new StringBuffer();
		sqlString.append("SELECT SEQ_EMPRESA.NEXTVAL from DUAL");
		Query query = entityManager.createNativeQuery(sqlString.toString());		
		
		try{
			Object result = query.getSingleResult();

			if(result!= null)				
				idEmpresa = Long.parseLong(String.valueOf(result));
			
		} catch (NoResultException re) {	
			re.printStackTrace(); logger.error(re);
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}

		return idEmpresa;
	}


	@Override
	public Map<String, String> obtenerActividadEconomica(long idActEco) {
		String idActividad = Long.toString(idActEco);
		String idSector = idActividad.substring(0, 2);
		if (idSector.compareTo("31") == 0 || idSector.compareTo("32") == 0 || idSector.compareTo("33") == 0) {
			idSector = "31";
		} else if (idSector.compareTo("48") == 0 || idSector.compareTo("49") == 0) {
			idSector = "48";
		}
		StringBuilder query = new StringBuilder("SELECT CATSEC.OPCION AS SECTOR, CATSUB.OPCION AS SUBSECTOR, CATRAMA.OPCION AS RAMA FROM CATALOGO_OPCION CATRAMA ");
		query.append(" INNER JOIN CATALOGO_OPCION CATSEC ON CATSEC.ID_CATALOGO = ").append(Constantes.CATALOGO_OPCION_SECTOR).append(" AND CATSEC.ID_CATALOGO_OPCION = ").append(idSector);
		query.append(" INNER JOIN CATALOGO_OPCION CATSUB ON CATSUB.ID_CATALOGO = ").append(Constantes.CATALOGO_OPCION_SUBSECTOR).append(" AND CATSUB.ID_CATALOGO_OPCION = ").append(idActividad.substring(0, 3));
		query.append(" WHERE CATRAMA.ID_CATALOGO = ").append(Constantes.CATALOGO_OPCION_TIPO_ACTIVIDAD_ECONOMICA).append(" AND CATRAMA.ID_CATALOGO_OPCION = ").append(idActEco);
		Query q = entityManager.createNativeQuery(query.toString());		
		Object obj = q.getSingleResult();
		Map<String, String> res = new HashMap<String, String>();
		res.put("idSector", ((Object[]) obj)[0].toString());
		res.put("idSubsector", ((Object[]) obj)[1].toString());
		res.put("idRama", ((Object[]) obj)[2].toString());
		return res;
	}
	
	@SuppressWarnings("unchecked")
	public Boolean repetidoIdPortalEmpleoCurp(String curp){
		Boolean bandera = false;
		
        StringBuilder queryString = new StringBuilder();
        queryString.append("SELECT e.* ");
        queryString.append("FROM EMPRESA e ");
        queryString.append("WHERE ");
        queryString.append(" UPPER(ID_PORTAL_EMPLEO) = UPPER('").append(curp).append("')");

        Query query = entityManager.createNativeQuery(queryString.toString());
		
		try{
			List<Empresa> resultList = (List<Empresa>)query.getResultList();

			if(resultList!= null && resultList.size()!=0)
				bandera =  true;
			
		} catch (NoResultException re) {	
			re.printStackTrace(); logger.error(re);
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}

		return bandera;		
	}
	
	@SuppressWarnings("unchecked")
	public Boolean repetidaCurp(String curp) {
		Boolean bandera = false;

        StringBuilder queryString = new StringBuilder();
        queryString.append("SELECT e.* ");
        queryString.append("FROM EMPRESA e ");
        queryString.append("WHERE ");
        queryString.append(" UPPER(CURP) = UPPER('").append(curp).append("')");
        /*
        queryString.append("SELECT e.ID_EMPRESA ");
        queryString.append("FROM EMPRESA e ");
        queryString.append("WHERE ");
        queryString.append("CURP = '").append(curp).append("')");
         * */

        Query query = entityManager.createNativeQuery(queryString.toString());
		
		try{
			List<Empresa> resultList = (List<Empresa>)query.getResultList();

			if(resultList!= null && resultList.size()!=0)
				bandera =  true;
			
		} catch (NoResultException re) {	
			re.printStackTrace(); logger.error(re);
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}

		return bandera;
	}	
	
	public CurpVO consultaDatosPersonalesPorCURP(CurpVO curpVo) {
		
		CurpVO datosPersonales  = null;
		
        StringBuilder queryString = new StringBuilder();
        queryString.append("SELECT e.NOMBRE, e.APELLIDO1, e.APELLIDO2, ");
        queryString.append(" e.FECHA_NACIMIENTO, e.LUGAR_NACIMIENTO  ");
        queryString.append("FROM EMPRESA e ");
        queryString.append("WHERE ");
        queryString.append("CURP = '").append(curpVo.getCurp()).append("'");

        Query query = entityManager.createNativeQuery(queryString.toString());
        
		try{

			@SuppressWarnings("unchecked")
			List<Object[]> rowSet = query.getResultList();
			datosPersonales  = new CurpVO();
			for (Object[] rs : rowSet) {
				datosPersonales.setNombre(Utils.toString(rs[0]));
				datosPersonales.setApellido1(Utils.toString(rs[1]));
				datosPersonales.setApellido2(Utils.toString(rs[2]));
				datosPersonales.setFechaNacimiento(Utils.toDate(rs[3]));
				datosPersonales.setIdEntidadNacimiento(Utils.toInt(rs[4]));
				
				if(null != getInicialDeGenero(curpVo.getCurp())){
					datosPersonales.setGenero(getInicialDeGenero(curpVo.getCurp()));
				}
				
				break;
			}  
			
		} catch (NoResultException re) {	
			re.printStackTrace(); logger.error(re);
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}
		
		return datosPersonales;
	}	
	
	private String getInicialDeGenero(String Curp){
		String inicialDeGenero = null;
		
		if(Curp.length()>0){
			inicialDeGenero = Curp.substring(11,12);
		}
		
		return inicialDeGenero;
	}


	/**
	 * Method 'findByIdEmpresa'
	 * Regresa un objeto EmpresaVO con los datos correspondientes a
	 * la empresa cuyo identificador de empresa se proporciona. En caso de no 
	 * encontrar una empresa asociada al {@code idUsuario} proporcionado, devuelve {@code null}.
	 * 
	 * @param idUsuario el identificador del empresa asociado a la empresa
	 * @return EmpresaVO que representa el registro encontrado para el {@code idEmpresa}
	 *         proporcionado, o {@code null}, si no se encuentra un registro coincidente.
	 * @throws PersistenceException en caso de que ocurra alg&uacute;n problema en 
	 *         tiempo de ejecuci&oacute;n, o si el proveedor de persistencia encuentra un problema.
	 */	
	public EmpresaVO findByIdEmpresa(long idEmpresa) throws PersistenceException {
		EmpresaVO vo = null;
		String findEmpresaByEmpresa = "SELECT c FROM Empresa c WHERE c.idEmpresa = :idempresa";
		try {
			Query query = entityManager.createQuery(findEmpresaByEmpresa);
			query.setParameter("idempresa", idEmpresa);

			Empresa entity = (Empresa) query.getSingleResult();
			vo = getEmpresaVO(entity);

		} catch (NoResultException re) {
			// No se localizaron registros
			vo = null;
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}

		return vo;
	}


// Consulta para Validacion de Empresa
	public List<EmpresaUsuarioVO> obtieneEmpresas(String RFC, String CodigoPostal) {
		List<EmpresaUsuarioVO> empresas = new ArrayList<EmpresaUsuarioVO>();
		StringBuilder queryString = new StringBuilder();
		
			queryString.append("SELECT e.ID_EMPRESA, e.RFC, e.CORREO_ELECTRONICO, d.CODIGO_POSTAL, oe.ESTATUS ESTATUS_OFERTA, FECHA_MODIFICACION ");
			queryString.append("FROM EMPRESA e, DOMICILIO d, OFERTA_EMPLEO oe ");
			queryString.append("WHERE e.ID_EMPRESA = d.ID_PROPIETARIO ");
			queryString.append("AND d.ID_TIPO_PROPIETARIO = 1 ");
			queryString.append("AND e.RFC = ?1 ");
			queryString.append("AND d.CODIGO_POSTAL = ?2 ");
			queryString.append("AND oe.ID_EMPRESA = e.ID_EMPRESA ");
			queryString.append("ORDER BY ESTATUS_OFERTA, FECHA_MODIFICACION DESC");
			
			String sql = queryString.toString();
			System.out.println("obtieneEmpresas: " + sql);
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, RFC);
			query.setParameter(2, CodigoPostal);
			
			try {
			@SuppressWarnings("unchecked")
			List<Object[]> rowSet = query.getResultList();

			for (Object[] rs : rowSet) {
				EmpresaUsuarioVO empresa = createEmpresaUsuarioVO(rs);
				empresas.add(empresa);
			}
	} catch (NoResultException re) {
		re.printStackTrace();
		logger.error(re);
		return null;
	} catch (RuntimeException re) {
		re.printStackTrace();
		throw new PersistenceException(re);
	}
		return empresas;
	}


	@Override
	public byte[] consultaLogotipo(int idPropietario) {
		byte[] foto = null;
		
        StringBuilder queryString = new StringBuilder();
        queryString.append("SELECT LOGOTIPO FROM EMPRESA ");
        queryString.append(" WHERE ID_EMPRESA = "+ idPropietario);

        Query query = entityManager.createNativeQuery(queryString.toString());
        
		Object result = query.getSingleResult();			

		if (result!=null)
			foto = (byte[])result;
		
		return foto;
	}
}
