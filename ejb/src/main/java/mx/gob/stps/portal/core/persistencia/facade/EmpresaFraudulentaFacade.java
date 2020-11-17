package mx.gob.stps.portal.core.persistencia.facade;

import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.empresa.vo.EmpresaFraudulentaVO;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PERSONA;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.infra.utils.Utils;
//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.persistencia.entity.EmpresaFraudulenta;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Implementacion de los metodos de persistencia para registros de la entidad "EmpresaFraudulenta"
 * 
 * @author haydee.vertti
 *
 */
@Stateless
public class EmpresaFraudulentaFacade implements EmpresaFraudulentaFacadeLocal{

	private static Logger logger = Logger.getLogger(EmpresaFraudulenta.class);

	@PersistenceContext
	private EntityManager entityManager; 

	@Override
	public long save(EmpresaFraudulentaVO vo) throws PersistenceException {
		try {
			EmpresaFraudulenta entity = getEntity(vo);
			entityManager.persist(entity);
			return entity.getIdEmpresa();
		} catch (RuntimeException re) {
			logger.error(re);
			throw new PersistenceException(re);
		}
	}

	@Override
	public EmpresaFraudulentaVO findById(long id) throws PersistenceException {
		try {
			EmpresaFraudulenta instance = entityManager.find(EmpresaFraudulenta.class, id);
			return getEmpresaFraudulentaVO(instance);
		} catch (RuntimeException re) {
			logger.error(re);
			throw new PersistenceException(re);
		}
	}



	@SuppressWarnings("unchecked")
	@Override
	public HashMap<Long, EmpresaFraudulentaVO> consultaEmpresasFraudulentas(
			EmpresaVO empresaAut) {
		List<Object[]> empresasFraudulentas = new ArrayList<Object[]>();
		HashMap<Long, EmpresaFraudulentaVO> empresas = new HashMap<Long, EmpresaFraudulentaVO>();
		String nombreEmpresaAut = empresaAut.getIdTipoPersona()==TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()?empresaAut.getNombre()+" "+empresaAut.getApellido1()+" "+empresaAut.getApellido2():empresaAut.getRazonSocial();
		String queryEmpresas = getQueryEmpresas(empresaAut.getCorreoElectronico(),empresaAut.getIdTipoPersona(),empresaAut.getNombre()+empresaAut.getApellido1()+empresaAut.getApellido2(),empresaAut.getRazonSocial());
		Query query = entityManager.createNativeQuery(queryEmpresas);
		empresasFraudulentas = query.getResultList();
		for(Object[] empresa: empresasFraudulentas){
			EmpresaFraudulentaVO empresaFraudulentaVO = new EmpresaFraudulentaVO();
			empresaFraudulentaVO.setIdEmpresa(Utils.toLong(empresa[0]));
			if(Utils.toInt(empresa[1])==TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona())empresaFraudulentaVO.setNombre(Utils.toString(empresa[2])+" "+Utils.toString(empresa[3])+" "+Utils.toString(empresa[4]));
			else empresaFraudulentaVO.setNombre(Utils.toString(empresa[5]));
			empresaFraudulentaVO.setCorreoElectronico(Utils.toString(empresa[6]));
			String coincidencias = empresaFraudulentaVO.getCoincidencias();

			if(empresaAut.getCorreoElectronico().equals(Utils.toString(empresa[6]))){
				if(coincidencias==null||coincidencias.equals(""))coincidencias= "Correo electronico: "+empresaFraudulentaVO.getCorreoElectronico();
				else coincidencias= coincidencias+", "+"Correo electronico: "+empresaFraudulentaVO.getCorreoElectronico();
				empresaFraudulentaVO.setCoincidencias(coincidencias);
			}
			if(nombreEmpresaAut.equals(empresaFraudulentaVO.getNombre())){
				if(coincidencias==null||coincidencias.equals(""))coincidencias= "Nombre: "+empresaFraudulentaVO.getNombre();
				else coincidencias= coincidencias+", "+"Nombre: "+empresaFraudulentaVO.getNombre();
				empresaFraudulentaVO.setCoincidencias(coincidencias);
			}
			empresas.put(empresaFraudulentaVO.getIdEmpresa(), empresaFraudulentaVO);
		}
		return empresas;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<Long, EmpresaFraudulentaVO> consultaTelefonosEmpresasFraudulentas(
			List<TelefonoVO> telefonosEmpresaAut) {

		HashMap<Long, EmpresaFraudulentaVO> empresas = new HashMap<Long, EmpresaFraudulentaVO>();
		if(telefonosEmpresaAut.size()>0){
			List<Object[]> empresasFraudulentas = new ArrayList<Object[]>();
			String queryTelefonos = getQueryTelefonos(telefonosEmpresaAut);
			Query query = entityManager.createNativeQuery(queryTelefonos);
			empresasFraudulentas = query.getResultList();

			for(Object[] empresa: empresasFraudulentas){
				EmpresaFraudulentaVO empresaFraudulentaVO = new EmpresaFraudulentaVO();
				TelefonoVO telefonoVO = new TelefonoVO();
				telefonoVO.setTelefono(Utils.toString(empresa[0]));
				empresaFraudulentaVO.setIdEmpresa(Utils.toLong(empresa[1]));
				if(Utils.toInt(empresa[2])==TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona())empresaFraudulentaVO.setNombre(Utils.toString(empresa[3])+" "+Utils.toString(empresa[4])+" "+Utils.toString(empresa[5]));
				else empresaFraudulentaVO.setNombre(Utils.toString(empresa[6]));

				if (empresas.containsKey(empresaFraudulentaVO.getIdEmpresa())){
					String coincidencias = empresas.get(empresaFraudulentaVO.getIdEmpresa()).getCoincidencias();
					empresas.get(empresaFraudulentaVO.getIdEmpresa()).addTelefono(telefonoVO);
					if(coincidencias==null||coincidencias.equals(""))coincidencias="Telefono: "+telefonoVO.getTelefono();
					else coincidencias = coincidencias+", "+"Telefono: "+telefonoVO.getTelefono();
					empresas.get(empresaFraudulentaVO.getIdEmpresa()).setCoincidencias(coincidencias);
				}else{
					empresas.put(empresaFraudulentaVO.getIdEmpresa(), empresaFraudulentaVO);
					empresaFraudulentaVO.addTelefono(telefonoVO);
					empresaFraudulentaVO.setCoincidencias("Telefono: "+telefonoVO.getTelefono());
				}

			}

		}


		return empresas;
	}


	@SuppressWarnings("unchecked")
	@Override
	public HashMap<Long, EmpresaFraudulentaVO> consultaDomiciliosEmpresasFraudulentas(
			DomicilioVO domicilioEmpresAut) {
		HashMap<Long, EmpresaFraudulentaVO> empresas = new HashMap<Long, EmpresaFraudulentaVO>();
		List<Object[]> empresasFraudulentas = new ArrayList<Object[]>();
		String queryDomicilio = getQueryTelefonos(domicilioEmpresAut);
		Query query = entityManager.createNativeQuery(queryDomicilio);
		empresasFraudulentas = query.getResultList();
		for(Object[] empresa: empresasFraudulentas){
			EmpresaFraudulentaVO empresaFraudulenta = new EmpresaFraudulentaVO();
			DomicilioVO domicilioVo = new DomicilioVO();

			domicilioVo.setEntidad(Utils.toString(empresa[0]));
			domicilioVo.setMunicipio(Utils.toString(empresa[1]));
			domicilioVo.setColonia(Utils.toString(empresa[2]));
			domicilioVo.setCalle(Utils.toString(empresa[3]));
			domicilioVo.setNumeroExterior(Utils.toString(empresa[4]));
			domicilioVo.setCodigoPostal(Utils.toString(empresa[5]));

			empresaFraudulenta.setIdEmpresa(Utils.toLong(empresa[6]));
			if(Utils.toInt(empresa[7])==TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona())empresaFraudulenta.setNombre(Utils.toString(empresa[8])+" "+Utils.toString(empresa[9])+" "+Utils.toString(empresa[10]));
			else empresaFraudulenta.setNombre(Utils.toString(empresa[11]));

			if (empresas.containsKey(empresaFraudulenta.getIdEmpresa())){
				String coincidencias = empresas.get(empresaFraudulenta.getIdEmpresa()).getCoincidencias();
				empresas.get(empresaFraudulenta.getIdEmpresa()).addDomicilio(domicilioVo);
				if(coincidencias==null||coincidencias.equals(""))coincidencias =  "Domicilio: "+domicilioVo.getCalle()+"No. "+domicilioVo.getNumeroExterior()+". Col. "+domicilioVo.getColonia()+" C.P. "+domicilioVo.getCodigoPostal()+". "+domicilioVo.getMunicipio()+", "+domicilioVo.getEntidad();
				else coincidencias = coincidencias + ", "+" Domicilio: "+domicilioVo.getCalle()+"No. "+domicilioVo.getNumeroExterior()+". Col. "+domicilioVo.getColonia()+" C.P. "+domicilioVo.getCodigoPostal()+". "+domicilioVo.getMunicipio()+", "+domicilioVo.getEntidad();
				empresas.get(empresaFraudulenta.getIdEmpresa()).setCoincidencias(coincidencias);
			}else{
				empresas.put(empresaFraudulenta.getIdEmpresa(), empresaFraudulenta);
				empresaFraudulenta.addDomicilio(domicilioVo);
				empresaFraudulenta.setCoincidencias("Domicilio: "+domicilioVo.getCalle()+"No. "+domicilioVo.getNumeroExterior()+". Col. "+domicilioVo.getColonia()+" C.P. "+domicilioVo.getCodigoPostal()+". "+domicilioVo.getMunicipio()+", "+domicilioVo.getEntidad());

			}


		}
		return empresas;
	}


	private String getQueryTelefonos(DomicilioVO domicilioEmpresAut) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT CE.OPCION, MU.MUNICIPIO, CP.COLONIA, DFRAU.CALLE, DFRAU.NUMERO_EXTERIOR,  ");
		query.append("DFRAU.CODIGO_POSTAL, ");
		query.append("EFRAU.ID_EMPRESA, EFRAU.ID_TIPO_PERSONA, EFRAU.NOMBRE, EFRAU.APELLIDO1, EFRAU.APELLIDO2, EFRAU.RAZON_SOCIAL ");
		query.append("FROM DOMICILIO DFRAU, ");
		query.append("EMPRESA_FRAUDULENTA EFRAU, "); 
		query.append("CATALOGO_OPCION CE, ");
		query.append("MUNICIPIO MU, ");
		query.append("CODIGO_POSTAL CP ");
		query.append("WHERE DFRAU.ID_TIPO_PROPIETARIO =  "+TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario()); 
		query.append("AND EFRAU.ID_EMPRESA = DFRAU.ID_PROPIETARIO "); 
		query.append("AND CE.ID_CATALOGO =  "+Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA);
		query.append("AND CE.ID_CATALOGO_OPCION = DFRAU.ID_ENTIDAD ");
		query.append("AND MU.ID_MUNICIPIO = DFRAU.ID_MUNICIPIO ");
		query.append("AND MU.ID_ENTIDAD = DFRAU.ID_ENTIDAD ");
		query.append("AND CP.ID_COLONIA = DFRAU.ID_COLONIA ");
		query.append("AND DFRAU.ID_ENTIDAD =  "+domicilioEmpresAut.getIdEntidad());
		query.append("AND DFRAU.ID_MUNICIPIO =  "+domicilioEmpresAut.getIdMunicipio());
		query.append("AND DFRAU.CODIGO_POSTAL = '"+domicilioEmpresAut.getCodigoPostal()+"' ");
		query.append("AND DFRAU.ID_COLONIA =  "+domicilioEmpresAut.getIdColonia());
		query.append("AND UPPER(DFRAU.CALLE) = '"+domicilioEmpresAut.getCalle()+"' ");
		query.append("AND DFRAU.NUMERO_EXTERIOR = '"+domicilioEmpresAut.getNumeroExterior()+"' ");
		logger.info(query.toString());
		return query.toString();
	}

	private String getQueryEmpresas(String correoElectronico,
			long idTipoPersona, String nombre, String razonSocial) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT EFRAU.ID_EMPRESA,EFRAU.ID_TIPO_PERSONA, "); 
		query.append("EFRAU.NOMBRE, EFRAU.APELLIDO1, EFRAU.APELLIDO2,EFRAU.RAZON_SOCIAL, "); 
		query.append("EFRAU.CORREO_ELECTRONICO ");
		query.append("FROM EMPRESA_FRAUDULENTA EFRAU  ");
		query.append("WHERE  EFRAU.CORREO_ELECTRONICO = '"+correoElectronico+"' "); 
		if(idTipoPersona==TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona())query.append(" OR UPPER(NVL(EFRAU.NOMBRE, '') || NVL(EFRAU.APELLIDO1, '') || NVL(EFRAU.APELLIDO2, '')) = '"+nombre.toUpperCase()+"' ");
		if(idTipoPersona==TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona())query.append(" OR UPPER(replace(RAZON_SOCIAL, '''',' ')) = '"+razonSocial.toUpperCase().replace("'", " ")+"' ");
		logger.info(query.toString());
		return query.toString();
	}

	private String getQueryTelefonos(List<TelefonoVO> telefonosEmpresaAut) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT DISTINCT TFRAU.TELEFONO, ");
		query.append("EFRAU.ID_EMPRESA,EFRAU.ID_TIPO_PERSONA, EFRAU.NOMBRE, EFRAU.APELLIDO1, EFRAU.APELLIDO2,EFRAU.RAZON_SOCIAL ");
		query.append("FROM TELEFONO TFRAU,  ");
		query.append("EMPRESA_FRAUDULENTA EFRAU ");
		query.append("WHERE TFRAU.ID_TIPO_PROPIETARIO =   "+TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
		query.append("AND EFRAU.ID_EMPRESA = TFRAU.ID_PROPIETARIO  ");
		query.append(" and (");
		int size = telefonosEmpresaAut.size();
		for(TelefonoVO vo : telefonosEmpresaAut){
			query.append(" UPPER( NVL(TFRAU.TELEFONO, ''))= UPPER('"+vo.getTelefono()+"') ");
			size--;
			if(size>0)query.append(" or ");
		}
		query.append(")");
		logger.info(query.toString());
		return query.toString();
	}



	/**
	 * Method 'getEmpresaFraudulentaVO'
	 * Coloca los datos de un objeto EmpresaFraudulenta en un VisualObject correspondiente
	 * @param empresa
	 * @return EmpresaFraudulentaVO
	 */		
	private EmpresaFraudulentaVO getEmpresaFraudulentaVO(EmpresaFraudulenta empresa){
		EmpresaFraudulentaVO vo = new EmpresaFraudulentaVO();
		vo.setAceptacionTerminos(empresa.getAceptacionTerminos());
		vo.setApellido1(empresa.getApellido1());
		vo.setApellido2(empresa.getApellido2());
//		vo.setConfidencial(empresa.getConfidencial());  // TODO: Doesn't exist field in DB
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
		return vo;		
	}

	/**
	 * Method 'getEntity'
	 * Coloca los datos de un VisualObject en un objeto de tipo EmpresaFraudulenta
	 * @param vo
	 * @return EmpresaFraudulenta
	 */		
	private EmpresaFraudulenta getEntity(EmpresaFraudulentaVO vo){
		EmpresaFraudulenta entity = new EmpresaFraudulenta();
		entity.setAceptacionTerminos(vo.getAceptacionTerminos());
		if (vo.getApellido1()!=null)
			entity.setApellido1(vo.getApellido1().toUpperCase());
		if (vo.getApellido2()!=null)
			entity.setApellido2(vo.getApellido2().toUpperCase());
//		entity.setConfidencial(vo.getConfidencial());   // TODO: Doesn't exist field in DB
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
		//COMENTAR EN PRODUCCION 
		entity.setNombreComercial(vo.getNombreComercial());

		if (vo.getNombre()!=null)
			entity.setNombre(vo.getNombre().toUpperCase());

		entity.setNumeroEmpleados(vo.getNumeroEmpleados());
		entity.setPaginaWeb(vo.getPaginaWeb());

		if (vo.getRazonSocial()!=null)
			entity.setRazonSocial(vo.getRazonSocial().toUpperCase());

		entity.setRfc(vo.getRfc());
		entity.setAseguraDatos(vo.getAseguraDatos());	
		return entity;		
	}

	public void saveEmpresaFraudulenta(EmpresaVO empresa) throws PersistenceException {
		EmpresaFraudulenta entity = createEmpresaFraudulenta(empresa);
		entityManager.persist(entity);
	}

	private EmpresaFraudulenta createEmpresaFraudulenta(EmpresaVO empresa){
		EmpresaFraudulenta entity = null;

		if (empresa != null && empresa.getIdEmpresa() > 0){
			entity = new EmpresaFraudulenta();				
			entity.setIdEmpresa(empresa.getIdEmpresa());				
			entity.setIdPortalEmpleo(empresa.getIdPortalEmpleo());
			entity.setIdUsuario(empresa.getIdUsuario());
			entity.setRfc(empresa.getRfc());
			entity.setIdTipoPersona(empresa.getIdTipoPersona());
			entity.setNombre(empresa.getNombre());			
			entity.setApellido1(empresa.getApellido1());				
			entity.setApellido2(empresa.getApellido2());				
			entity.setFechaNacimiento(empresa.getFechaNacimiento());
			entity.setRazonSocial(empresa.getRazonSocial());				
			entity.setFechaActa(empresa.getFechaActa());
			entity.setDescripcion(empresa.getDescripcion());
			entity.setContactoEmpresa(empresa.getContactoEmpresa());
			entity.setIdTipoEmpresa(empresa.getIdTipoEmpresa());
			entity.setIdActividadEconomica(empresa.getIdActividadEconomica());
			entity.setNumeroEmpleados(empresa.getNumeroEmpleados());				
			entity.setIdMedio(empresa.getIdMedio());				
//			entity.setConfidencial(empresa.getConfidencial());  // TODO: Doesn't exist field in DB
			entity.setPaginaWeb(empresa.getPaginaWeb());
			entity.setAceptacionTerminos(empresa.getAceptacionTerminos());
			entity.setFechaAlta(empresa.getFechaAlta());				
			entity.setEstatus(empresa.getEstatus());
			entity.setFechaUltimaActualizacion(empresa.getFechaUltimaActualizacion());
			entity.setCorreoElectronico(empresa.getCorreoElectronico());				
			entity.setAseguraDatos(empresa.getAseguraDatos());
			// COMENTAR EN PRODUCCION ini
			entity.setNombreComercial(empresa.getNombreComercial());
			//COMENTAR EN PRODUCCION fin			
		}

		return entity;
	}


}
