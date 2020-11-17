package mx.gob.stps.portal.core.persistencia.facade;

import org.apache.commons.lang.StringUtils;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.empresa.dao.EmpresaPorAutorizarDAO;
import mx.gob.stps.portal.core.empresa.vo.EmpresaPorAutorizarVO;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.persistencia.entity.EmpresaPorAutorizar;

import org.apache.log4j.Logger;

/**
 * Implementacion de los metodos de persistencia para registros de la entidad "EmpresaPorAutorizar"
 * 
 * @author haydee.vertti
 *
 */
@Stateless
public class EmpresaPorAutorizarFacade implements EmpresaPorAutorizarFacadeLocal {
	
	private static Logger logger = Logger.getLogger(EmpresaPorAutorizar.class);
	public static int PORTAL_ID_SIZE = 17;

	@PersistenceContext
	private EntityManager entityManager;	
	
	/**
	 * Method 'save'
	 * Almacena los datos del ValueObject y regresa el valor del identificador creado.
	 * @param vo
	 * @return long
	 */		
	public long save(EmpresaPorAutorizarVO vo) throws PersistenceException {
		try {
			EmpresaPorAutorizar entity = getEntity(vo);
			entity.setIdPortalEmpleo(generaIDPortalEmpleo(vo));
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
	public void delete(EmpresaPorAutorizarVO vo) throws PersistenceException {
		try {
			entityManager.remove(getEntity(vo));
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}
	}	

	public void delete(long idEmpresa) throws PersistenceException {
		try {
			EmpresaPorAutorizar entity = entityManager.find(EmpresaPorAutorizar.class, idEmpresa);
			
			entityManager.remove(entity);

		} catch (RuntimeException re) {
			throw new PersistenceException(re);
		}
	}	
	
	/**
	 * Method 'findById'
	 * Regresa un objeto EmpresaPorAutorizarVO con los datos correspondientes a
	 * la empresa por  autorizar cuyo identificador se proporciona
	 * 
	 * @param id
	 * @return EmpresaPorAutorizarVO
	 */	
	public EmpresaPorAutorizarVO findById(long id) throws PersistenceException {
		EmpresaPorAutorizarVO vo = null;
		try {
			EmpresaPorAutorizar instance = entityManager.find(EmpresaPorAutorizar.class, id);
			if(instance==null) return null;

			vo = getEmpresaPorAutorizarVO(instance);

		} catch (NoResultException re) {
			// No se localizaron registros
			return null;
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}

		return vo;
	}	
	
	/**
	 * Method 'update'
	 * 
	 * @param vo
	 */		
	public void update(EmpresaPorAutorizarVO vo) throws PersistenceException {
		try {
			EmpresaPorAutorizar entity = entityManager.find(EmpresaPorAutorizar.class, vo.getIdEmpresa());
			entity.setAceptacionTerminos(vo.getAceptacionTerminos());
			entity.setApellido1(vo.getApellido1());
			entity.setApellido2(vo.getApellido2());
			entity.setConfidencial(vo.getConfidencial());
			entity.setContactoEmpresa(vo.getContactoEmpresa());
			entity.setCorreoElectronico(vo.getCorreoElectronico());
			entity.setDescripcion(vo.getDescripcion());
			entity.setEstatus(vo.getEstatus());
			entity.setFechaActa(vo.getFechaActa());
			entity.setFechaAlta(vo.getFechaAlta());
			entity.setFechaNacimiento(vo.getFechaNacimiento());
			entity.setFechaUltimaActualizacion(vo.getFechaUltimaActualizacion());
			entity.setIdActividadEconomica(vo.getIdActividadEconomica());
			entity.setIdMedio(vo.getIdMedio());
			entity.setIdPortalEmpleo(vo.getIdPortalEmpleo());
			entity.setIdTipoEmpresa(vo.getIdTipoEmpresa());
			entity.setIdTipoPersona(vo.getIdTipoPersona());
			entity.setNombre(vo.getNombre());
			entity.setNumeroEmpleados(vo.getNumeroEmpleados());
			entity.setPaginaWeb(vo.getPaginaWeb());
			entity.setRazonSocial(vo.getRazonSocial());		
			entity.setRfc(vo.getRfc());		
			entity.setAseguraDatos(vo.getAseguraDatos());
			entity.setNombreComercial(vo.getNombreComercial());
						
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}
	}		
		
	/**
	 * Method 'actualizaEstatus'
	 * Actualiza el estatus de una empresa por autorizar, al valor proporcionado.
	 * 
	 * @param idEmpresa
	 * @param estatus
	 */		
	public void actualizaEstatus(long idEmpresa, int estatus) throws PersistenceException {
		try {
			EmpresaPorAutorizar empresa = entityManager.find(EmpresaPorAutorizar.class, idEmpresa);
			empresa.setEstatus(estatus);
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}
	}	
	
	/**
	 * Method 'actualizaUltimaFecha'
	 * Actualiza la fecha de actualización de una empresa por autorizar, al valor proporcionado.
	 * 
	 * @param idEmpresa
	 * @param ultimaFecha
	 */			
	public void actualizaUltimaFecha(long idEmpresa, Date ultimaFecha) throws PersistenceException {
		try {
			EmpresaPorAutorizar empresa = entityManager.find(EmpresaPorAutorizar.class, idEmpresa);			
			empresa.setFechaUltimaActualizacion(ultimaFecha);
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}
	}		
	
	
	public void actualizaIDPortalEmpleo(long idEmpresa, String idPortalEmpleo) throws PersistenceException {

		try{
			EmpresaPorAutorizar empresa = entityManager.find(EmpresaPorAutorizar.class, idEmpresa);		
			empresa.setIdPortalEmpleo(idPortalEmpleo);
			empresa.setFechaUltimaActualizacion(new Date());
		} catch (RuntimeException re) {
			throw new PersistenceException(re);
		}
	}		
	
	public String convertAccents(String originalString){
		String changedString = "";
		if(originalString.length()>0){
			//acentos y ñ			
			changedString = originalString.toLowerCase();
			//COMENTAR EN PRODUCCION los números no se tendrán en cuenta para la generación del id_portal_empresa en produccion
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
	
	/**
	 * Method 'generaIDPortalEmpleo'
	 * Genera el identificador del portal del empleo correspondiente a la empresa por autorizar
	 * cuyos datos se proporcionan.
	 * 
	 * @param idEmpresa
	 * @param estatus
	 */		
	public String generaIDPortalEmpleo(EmpresaPorAutorizarVO vo){
		String idPortalEmpleo = "";		
		int digitoVerificador = 1;
		Format formatter = new SimpleDateFormat("yyMMdd");		
		try {
			DomicilioVO domicilio = vo.getDomicilio();
			if(vo.getIdTipoPersona() == Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()){
				//Tipo de Persona Física
				if(vo.getApellido2().isEmpty()){
					//Primeras tres letras del apellido paterno
					//System.out.println("--original--strApellido1:" + vo.getApellido1());
					String strApellido1 = convertAccents(vo.getApellido1());
					//System.out.println("--modificado--strApellido1:" + strApellido1);
					idPortalEmpleo = idPortalEmpleo + strApellido1.substring(0, 3);	
				} else {
					//Primeras dos letras del apellido paterno
					//Primera letra del apellido materno					
					//System.out.println("--original--strApellido1:" + vo.getApellido1());
					String strApellido1 = convertAccents(vo.getApellido1());
					//System.out.println("--modificado--strApellido1:" + strApellido1);					
					//System.out.println("--original--strApellido2:" + vo.getApellido2());
					String strApellido2 = convertAccents(vo.getApellido2());
					//System.out.println("--modificado--strApellido2:" + strApellido2);					
					
					idPortalEmpleo = idPortalEmpleo + strApellido1.substring(0, 2);
					idPortalEmpleo = idPortalEmpleo + strApellido2.substring(0, 1);
				}
				//Primera letra del nombre
				//System.out.println("--original--strNombre:" + vo.getNombre());
				String strNombre = convertAccents(vo.getNombre());
				//System.out.println("--modificado--strNombre:" + strNombre);					
				idPortalEmpleo = idPortalEmpleo + strNombre.substring(0, 1);
				//Fecha de nacimiento en formato yymmdd
				idPortalEmpleo = idPortalEmpleo + formatter.format(vo.getFechaNacimiento());
										
			} else {
				//Tipo de Persona Moral					
				String strRazonSocial = convertAccents(vo.getRazonSocial());
				//Primeras tres letras de razón social
				//COMENTAR EN PRODUCCION									
				if (strRazonSocial.length() < 3) 
					strRazonSocial = StringUtils.rightPad(strRazonSocial, 3, "X");
				/**/		
				//TERMINA COMENTAR EN PRODUCCION				
				//
				//System.out.println("--modificado--strRazonSocial:" + strRazonSocial);									
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
			//System.out.println("------digitoVerificador:" + digitoVerificador);
			int lenDigito = String.valueOf(digitoVerificador).length();
			//System.out.println("------lenDigito:" + lenDigito);
			int intRelleno = PORTAL_ID_SIZE - (lenId + lenDigito);
			//System.out.println("------intRelleno:" + intRelleno);
			String strRelleno = "";
			if(intRelleno>0){
				for(int i=0; i<intRelleno; i++){
					strRelleno = strRelleno + "0";
				}				
			}
			//System.out.println("------strRelleno:" + strRelleno);
			idPortalEmpleo = idPortalEmpleo + strRelleno + digitoVerificador;
			//System.out.println("------idPortalEmpleo:" + idPortalEmpleo);
		
		} catch (RuntimeException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new PersistenceException(e);
		} catch (SQLException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new PersistenceException(e);
		} catch (Exception e) {
			logger.error(e.toString());			
			e.printStackTrace();
			throw new PersistenceException(e);
		}		
		return idPortalEmpleo;
	}
	
	/**
	 * Method 'findById'
	 * Regresa un objeto EmpresaPorAutorizarVO con los datos correspondientes a
	 * la empresa por  autorizar cuyo identificador se proporciona
	 * 
	 * @param id
	 * @return EmpresaPorAutorizarVO
	 */	
	public EmpresaPorAutorizarVO findByIdPortalEmpleo(String idPortalEmpleo) throws PersistenceException {
		EmpresaPorAutorizarVO vo = null;
		try {
			EmpresaPorAutorizar instance = entityManager.find(EmpresaPorAutorizar.class, idPortalEmpleo);
			if(instance==null) return null;			
			vo = getEmpresaPorAutorizarVO(instance);
			
		} catch (NoResultException re) {
			// No se localizaron registros
			return null;
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}

		return vo;
	}		
	
	/**
	 * Method 'getEmpresaPorAutorizarVO'
	 * Coloca los datos de un objeto EmpresaPorAutorizar en un VisualObject correspondiente
	 * @param empresa
	 * @return EmpresaPorAutorizarVO
	 */		
	private EmpresaPorAutorizarVO getEmpresaPorAutorizarVO(EmpresaPorAutorizar empresa){
		EmpresaPorAutorizarVO vo = new EmpresaPorAutorizarVO();
		vo.setAceptacionTerminos(empresa.getAceptacionTerminos());
		vo.setApellido1(empresa.getApellido1());
		vo.setApellido2(empresa.getApellido2());
		vo.setConfidencial(empresa.getConfidencial());
		vo.setContactoEmpresa(empresa.getContactoEmpresa());
		vo.setCorreoElectronico(empresa.getCorreoElectronico());
		vo.setDescripcion(empresa.getDescripcion());
		vo.setEstatus(empresa.getEstatus());
		vo.setFechaActa(empresa.getFechaActa());
		vo.setFechaAlta(empresa.getFechaAlta());
		vo.setFechaNacimiento(empresa.getFechaNacimiento());
		vo.setFechaUltimaActualizacion(empresa.getFechaUltimaActualizacion());
		vo.setIdActividadEconomica(empresa.getIdActividadEconomica());
		vo.setIdEmpresa(empresa.getIdEmpresa());
		vo.setIdMedio(empresa.getIdMedio());
		vo.setIdPortalEmpleo(empresa.getIdPortalEmpleo());
		vo.setIdTipoEmpresa(empresa.getIdTipoEmpresa());
		vo.setIdTipoPersona(empresa.getIdTipoPersona());
		vo.setNombre(empresa.getNombre());
		vo.setNumeroEmpleados(empresa.getNumeroEmpleados());
		vo.setPaginaWeb(empresa.getPaginaWeb());
		vo.setRazonSocial(empresa.getRazonSocial());
		vo.setRfc(empresa.getRfc());
		vo.setAseguraDatos(empresa.getAseguraDatos());
		vo.setNombreComercial(empresa.getNombreComercial());
		return vo;
	}
	
	/**
	 * Method 'getEntity'
	 * Coloca los datos de un VisualObject en un objeto de tipo EmpresaPorAutorizar
	 * @param vo
	 * @return EmpresaPorAutorizar
	 */		
	private EmpresaPorAutorizar getEntity(EmpresaPorAutorizarVO vo){
		EmpresaPorAutorizar entity = new EmpresaPorAutorizar();
		entity.setAceptacionTerminos(vo.getAceptacionTerminos());
		if(null!=vo.getApellido1()){
			entity.setApellido1(vo.getApellido1().toUpperCase());
		}
		if(null!=vo.getApellido2()){
			entity.setApellido2(vo.getApellido2().toUpperCase());
		}			
		entity.setConfidencial(vo.getConfidencial());
		entity.setContactoEmpresa(vo.getContactoEmpresa());
		entity.setCorreoElectronico(vo.getCorreoElectronico());
		entity.setDescripcion(vo.getDescripcion());
		entity.setEstatus(vo.getEstatus());
		entity.setFechaActa(vo.getFechaActa());
		entity.setFechaAlta(vo.getFechaAlta());
		entity.setFechaNacimiento(vo.getFechaNacimiento());
		entity.setFechaUltimaActualizacion(vo.getFechaUltimaActualizacion());
		entity.setIdActividadEconomica(vo.getIdActividadEconomica());
		entity.setIdEmpresa(vo.getIdEmpresa());
		entity.setIdMedio(vo.getIdMedio());		
		entity.setIdPortalEmpleo(vo.getIdPortalEmpleo());				
		entity.setIdTipoEmpresa(vo.getIdTipoEmpresa());
		entity.setIdTipoPersona(vo.getIdTipoPersona());
		entity.setNombreComercial(vo.getNombreComercial());
		if(null!=vo.getNombre()){
			entity.setNombre(vo.getNombre().toUpperCase());
		}
		entity.setNumeroEmpleados(vo.getNumeroEmpleados());
		entity.setPaginaWeb(vo.getPaginaWeb());
		if(null!=vo.getRazonSocial()){
			entity.setRazonSocial(vo.getRazonSocial().toUpperCase());
		}
		entity.setRfc(vo.getRfc());		
		entity.setAseguraDatos(vo.getAseguraDatos());	
		return entity;
	}


	public EmpresaPorAutorizarVO consultaEmpresaPorCorreo(String correoElectronico) throws PersistenceException {
		EmpresaPorAutorizarVO vo = null;

		try{
			Query query = entityManager.createQuery("SELECT e FROM EmpresaPorAutorizar e WHERE LOWER(e.correoElectronico) = LOWER(:correo)");
			query.setParameter("correo", correoElectronico);

			EmpresaPorAutorizar entity = (EmpresaPorAutorizar)query.getSingleResult();
			vo = getEmpresaPorAutorizarVO(entity);

		} catch (NoResultException re) {
			logger.error("Empresa no localizada mediante el correo : "+ correoElectronico);
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenceException(e);
		}
		
		return vo;
	}

}
