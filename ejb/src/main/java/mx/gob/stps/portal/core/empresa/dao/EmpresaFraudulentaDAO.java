package mx.gob.stps.portal.core.empresa.dao;

import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.empresa.vo.EmpresaFraudulentaVO;
import mx.gob.stps.portal.core.infra.data.TemplateDAO;
import mx.gob.stps.portal.core.infra.utils.Constantes.EVENTO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PERSONA;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_USUARIO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;

import javax.sql.rowset.CachedRowSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;

/**
 * Contiene las consultas y logica para verificar si una empresa cuenta con datos, telefonos o domicilios
 * similares a una Empresa Fraudulenta
 *  
 * @author oscar.manzo
 *
 */
public class EmpresaFraudulentaDAO extends TemplateDAO {

	private final int QUERY_EMPRESA_FRAU = 1;
	private final int QUERY_TELEFONO_FRAU = 2;
	private final int QUERY_DOMICILIO_FRAU = 3;
	private final int QUERY_USUARIO_FRAU = 4;
	private int QUERY_FRAU = 0;
		
	private EmpresaFraudulentaDAO(Connection conn){
		super(conn);
	}

	public static EmpresaFraudulentaDAO getInstance(Connection conn){
		return new EmpresaFraudulentaDAO(conn);
	}
	
	/**
	 * Consulta las Empresas Fraudulentas con Datos, Telefonos o Domicilios similares a la empresa a verificar
	 * @param idEmpresa identificador de empresa a verificar si es fraudulenta
	 * @return lista de <mx.gob.stps.portal.core.empresa.vo.EmpresaFraudulentaVO>
	 * @throws SQLException SQLException Lanzada en caso de error durante la consulta
	 */
	public List<EmpresaFraudulentaVO> consultaEmpresasFraudulentasSimilares(long idEmpresa) throws SQLException {

		if (idEmpresa<=0) throw new IllegalArgumentException("Identificador de empresa requerido");
		
		HashMap<Long, EmpresaFraudulentaVO> empresasFraudulentas = consultaEmpresasFraudulentas(idEmpresa);

		HashMap<Long, EmpresaFraudulentaVO> empTelefonos = consultaTelefonosEmpresasFraudulentas(idEmpresa);

		HashMap<Long, EmpresaFraudulentaVO> empDomicilios = consultaDomiciliosEmpresasFraudulentas(idEmpresa);

		// Se utiliza una conexion global desde el constructos, se cierra al finalizar las consultas
		closeGlobalConnection();
		
		// Se agregan los Telefonos y Domicilios de Empresas Fraudulentas
		for (EmpresaFraudulentaVO empresa : empresasFraudulentas.values()){
			
			// Agrega Telefonos
			if (empTelefonos.containsKey(empresa.getIdEmpresa())){
				EmpresaFraudulentaVO empTels = empTelefonos.get(empresa.getIdEmpresa());

				empresa.addTelefonos(empTels.getTelefonos());
			}

			// Agrega domicilios
			if (empDomicilios.containsKey(empresa.getIdEmpresa())){
				EmpresaFraudulentaVO empDoms = empDomicilios.get(empresa.getIdEmpresa());

				empresa.addDomicilios(empDoms.getDomicilios());
			}
		}

		// Se agrega las Empresas con Telefonos Fraudulentos a la lista de Empresas Fraudulentas
		for (EmpresaFraudulentaVO empTels  : empTelefonos.values()){
			if (!empresasFraudulentas.containsKey(empTels.getIdEmpresa())){
				empresasFraudulentas.put(empTels.getIdEmpresa(), empTels);
			}
		}
		
		// Se agrega las Empresas con Domicilios Fraudulentos a la lista de Empresas Fraudulentas
		for (EmpresaFraudulentaVO empDoms  : empDomicilios.values()){
			if (!empresasFraudulentas.containsKey(empDoms.getIdEmpresa())){
				empresasFraudulentas.put(empDoms.getIdEmpresa(), empDoms);
			}
		}

		List<EmpresaFraudulentaVO> empresas = new ArrayList<EmpresaFraudulentaVO>(empresasFraudulentas.values());
		
		String nombreUsuarioQueDetectaFraude = ""; 
		obtenerDatosDeDeteccionDeFraude(empresas);
		
		return empresas;
	}
	

	private void obtenerDatosDeDeteccionDeFraude(List<EmpresaFraudulentaVO> empresasFraudulentas) throws SQLException{
		this.QUERY_FRAU = this.QUERY_USUARIO_FRAU;
		StringBuilder idsEmpresasFraudulentas = new StringBuilder();
		for (EmpresaFraudulentaVO empfrau : empresasFraudulentas){
			if(idsEmpresasFraudulentas.length()>0)
				idsEmpresasFraudulentas.append(",");
			idsEmpresasFraudulentas.append(empfrau.getIdEmpresa());
		}
		
		Object[] params = {idsEmpresasFraudulentas.toString()};	
		
		CachedRowSet rowSet = executeQuery(params);
		
		HashMap<Long, String> usuarios = new HashMap<Long, String>();
		HashMap<Long, java.util.Date> fechas = new HashMap<Long, java.util.Date>();
		
		while (rowSet.next()){
			long idEmpresa= rowSet.getLong("id_registro");
			Date dtFecha = rowSet.getDate("fecha_evento");
			StringBuilder strUsuario = new StringBuilder();
			if(null==rowSet.getString("nombre") && null==rowSet.getString("apellido1") && null==rowSet.getString("apellido2")){
				strUsuario.append(rowSet.getString("usuario"));
			} else {
				if(null!=rowSet.getString("nombre"))
					strUsuario.append(rowSet.getString("nombre")); 
				if(null!=rowSet.getString("apellido1"))
					strUsuario.append(" " + rowSet.getString("apellido1"));
				if(null!=rowSet.getString("apellido2"))
					strUsuario.append(" " +  rowSet.getString("apellido2"));				
			}			
			
			usuarios.put(idEmpresa, strUsuario.toString());
			if(null!=dtFecha)
				fechas.put(idEmpresa, dtFecha);
			
		}
		
		Calendar fechaDeteccionNoEncontrada = Calendar.getInstance();
		fechaDeteccionNoEncontrada.set(2011, Calendar.DECEMBER, 12);		
		String  usuarioDetectorNoEncontrado = "Desconocido";
		for (EmpresaFraudulentaVO empfrau : empresasFraudulentas){
			long idEmpresa = empfrau.getIdEmpresa();
			if(null==usuarios.get(idEmpresa)){
				empfrau.setNombreUsuarioDetectorFraude(usuarioDetectorNoEncontrado);
			} else {
				empfrau.setNombreUsuarioDetectorFraude(usuarios.get(idEmpresa));
			}
			if(null==fechas.get(idEmpresa)){
				empfrau.setFechaDeteccionFraude(fechaDeteccionNoEncontrada.getTime());
			} else {
				empfrau.setFechaDeteccionFraude(fechas.get(idEmpresa));
			}
		}		

	}

	/**
	 * Consulta las empresas fraudulentas con datos iguales a la empresa indicada
	 * @param idEmpresa identificador de empresa a verificar
	 * @return coleccion de empresas fraudulentas
	 * @throws SQLException Lanzada en caso de error durante la consulta
	 */
	private HashMap<Long, EmpresaFraudulentaVO> consultaEmpresasFraudulentas(long idEmpresa) throws SQLException {
		this.QUERY_FRAU = this.QUERY_EMPRESA_FRAU;

		Object[] params = {idEmpresa, idEmpresa,
				           TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona(),
				           TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()};

		CachedRowSet rowSet = executeQuery(params);

		HashMap<Long, EmpresaFraudulentaVO> empresas = new HashMap<Long, EmpresaFraudulentaVO>();

		while (rowSet.next()){
			EmpresaFraudulentaVO empresa = createEmpresaFraudulentaVO(rowSet);
			empresas.put(empresa.getIdEmpresa(), empresa);
		}

		return empresas;
	}

	/**
	 * Consulta y relaciona las Empresas Fraudulentas con Telefonos iguales a la empresa indicada
	 * @param idEmpresa identificador de la empresa
	 * @return coleccion de empresas y sus telefonos
	 * @throws SQLException Lanzada en caso de error durante la consulta
	 */
	private HashMap<Long, EmpresaFraudulentaVO> consultaTelefonosEmpresasFraudulentas(long idEmpresa) throws SQLException {
		this.QUERY_FRAU = this.QUERY_TELEFONO_FRAU;

		Object[] params = {TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario(), idEmpresa};

		CachedRowSet rowSet = executeQuery(params);

		HashMap<Long, EmpresaFraudulentaVO> empresas = new HashMap<Long, EmpresaFraudulentaVO>();

		while (rowSet.next()){
 
			EmpresaFraudulentaVO empresa = createEmpresaFraudulentaVO(rowSet);
			TelefonoVO telefono = createTelefonoVO(rowSet);
			
			if (empresas.containsKey(empresa.getIdEmpresa())){
				empresas.get(empresa.getIdEmpresa()).addTelefono(telefono);
			}else{
				empresas.put(empresa.getIdEmpresa(), empresa);
				empresa.addTelefono(telefono);
			}

		}
		
		return empresas;
	}

	/**
	 * Consulta y relaciona las empresas Fraudulentas con sus Domicilios iguales a la empresa indicada
	 * @param idEmpresa identificador de la empresa
	 * @return coleccion de empresas y sus domicilios
	 * @throws SQLException Lanzada en caso de error durante la consulta
	 */
	private HashMap<Long, EmpresaFraudulentaVO> consultaDomiciliosEmpresasFraudulentas(long idEmpresa) throws SQLException {
		this.QUERY_FRAU = this.QUERY_DOMICILIO_FRAU;

		Object[] params = {TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario(), idEmpresa};

		CachedRowSet rowSet = executeQuery(params);

		HashMap<Long, EmpresaFraudulentaVO> empresas = new HashMap<Long, EmpresaFraudulentaVO>();
		
		while (rowSet.next()){

			EmpresaFraudulentaVO empresa = createEmpresaFraudulentaVO(rowSet);

			DomicilioVO domicilio =  createDomicilioVO(rowSet);
			
			if (empresas.containsKey(empresa.getIdEmpresa())){
				empresas.get(empresa.getIdEmpresa()).addDomicilio(domicilio);
			}else{
				empresas.put(empresa.getIdEmpresa(), empresa);
				empresa.addDomicilio(domicilio);
			}

		}
		
		return empresas;
	}
	
	private EmpresaFraudulentaVO createEmpresaFraudulentaVO(CachedRowSet rowSet) throws SQLException {
		EmpresaFraudulentaVO emp = new EmpresaFraudulentaVO();

		emp.setIdEmpresa(rowSet.getLong("ID_EMPRESA"));
		emp.setIdPortalEmpleo(rowSet.getString("ID_PORTAL_EMPLEO"));
		emp.setIdUsuario(rowSet.getLong("ID_USUARIO"));
		emp.setRfc(rowSet.getString("RFC"));
		emp.setIdTipoPersona(rowSet.getLong("ID_TIPO_PERSONA"));
		emp.setNombre(rowSet.getString("NOMBRE"));
		emp.setApellido1(rowSet.getString("APELLIDO1"));
		emp.setApellido2(rowSet.getString("APELLIDO2"));
		emp.setFechaNacimiento(rowSet.getDate("FECHA_NACIMIENTO"));
		emp.setRazonSocial(rowSet.getString("RAZON_SOCIAL"));
		emp.setFechaActa(rowSet.getDate("FECHA_ACTA"));
		emp.setDescripcion(rowSet.getString("DESCRIPCION"));
		emp.setContactoEmpresa(rowSet.getString("CONTACTO_EMPRESA"));
		emp.setIdTipoEmpresa(rowSet.getLong("ID_TIPO_EMPRESA"));
		emp.setIdActividadEconomica(rowSet.getLong("ID_ACTIVIDAD_ECONOMICA"));
		emp.setNumeroEmpleados(rowSet.getInt("NUMERO_EMPLEADOS"));
		emp.setIdMedio(rowSet.getLong("ID_MEDIO"));
		emp.setConfidencial(rowSet.getInt("CONFIDENCIAL"));
		emp.setPaginaWeb(rowSet.getString("PAGINA_WEB"));
		emp.setAceptacionTerminos(rowSet.getInt("ACEPTACION_TERMINOS"));
		emp.setFechaAlta(rowSet.getDate("FECHA_ALTA"));
		emp.setEstatus(rowSet.getInt("ESTATUS"));
		emp.setFechaUltimaActualizacion(rowSet.getDate("FECHA_ULTIMA_ACTUALIZACION"));
		emp.setCorreoElectronico(rowSet.getString("CORREO_ELECTRONICO"));
		emp.setAseguraDatos(rowSet.getInt("ASEGURA_DATOS"));
		
		return emp;
	}
	
	private TelefonoVO createTelefonoVO(CachedRowSet rowSet) throws SQLException {
		TelefonoVO vo = new TelefonoVO();
		
		vo.setIdTelefono(rowSet.getLong("ID_TELEFONO"));
		vo.setIdPropietario(rowSet.getLong("ID_PROPIETARIO"));
//		vo.setIdTipoPropietario(rowSet.getLong("ID_TIPO_PROPIETARIO"));
		vo.setIdTipoPropietario(rowSet.getInt("ID_TIPO_PROPIETARIO"));
//		vo.setIdTipoTelefono(rowSet.getLong("ID_TIPO_TELEFONO"));
		vo.setIdTipoTelefono(rowSet.getInt("ID_TIPO_TELEFONO"));
		vo.setAcceso(rowSet.getString("ACCESO"));
		vo.setClave(rowSet.getString("CLAVE"));
		vo.setTelefono(rowSet.getString("TELEFONO"));
		vo.setExtension(rowSet.getString("EXTENSION"));
		vo.setPrincipal(rowSet.getInt("PRINCIPAL"));
		//vo.setFechaAlta(rowSet.getDate("-----"));

		return vo;
	}
	
	private DomicilioVO createDomicilioVO(CachedRowSet rowSet) throws SQLException {
		DomicilioVO vo = new DomicilioVO();
		
		vo.setIdDomicilio(rowSet.getLong("ID_DOMICILIO"));
		vo.setIdTipoPropietario(rowSet.getLong("ID_TIPO_PROPIETARIO"));
		vo.setIdPropietario(rowSet.getLong("ID_PROPIETARIO"));
		vo.setIdEntidad(rowSet.getLong("ID_ENTIDAD"));
		vo.setIdMunicipio(rowSet.getLong("ID_MUNICIPIO"));
		vo.setIdColonia(rowSet.getLong("ID_COLONIA"));
		vo.setCalle(rowSet.getString("CALLE"));
		vo.setNumeroInterior(rowSet.getString("NUMERO_INTERIOR"));
		vo.setNumeroExterior(rowSet.getString("NUMERO_EXTERIOR"));
		vo.setEntreCalle(rowSet.getString("ENTRE_CALLE"));
		vo.setyCalle(rowSet.getString("Y_CALLE"));
		vo.setCodigoPostal(rowSet.getString("CODIGO_POSTAL"));
		//vo.setFechaAlta(rowSet.getDate("----"));
		
		return vo;
	}
	
	@Override
	protected String getQuery() {
		String query = null;

		switch(QUERY_FRAU){
			case QUERY_EMPRESA_FRAU : query = getQueryEmpresasFraudulentas(); break;
			case QUERY_TELEFONO_FRAU : query = getQueryTelefonosEmpFraudulentas(); break;
			case QUERY_DOMICILIO_FRAU : query = getQueryDomiciliosEmpFraudulentas(); break;	
			case QUERY_USUARIO_FRAU : query = getQueryUsuariosQueDetectaronFraude(); break;	
		}

		return query;
	}

	/**
	 * Consulta para las Empresa que tengas similitudes con Empresas Fraudulentas
	 * @return
	 */
	private String getQueryEmpresasFraudulentas(){
		StringBuilder query = new StringBuilder();		

		query.append("SELECT EFRAU.ID_EMPRESA, EFRAU.ID_PORTAL_EMPLEO, EFRAU.RFC, EFRAU.ID_TIPO_PERSONA, ");
		query.append("       EFRAU.NOMBRE, EFRAU.APELLIDO1, EFRAU.APELLIDO2, EFRAU.FECHA_NACIMIENTO, EFRAU.RAZON_SOCIAL, ");
		query.append("       EFRAU.CORREO_ELECTRONICO, EFRAU.FECHA_ACTA, EFRAU.DESCRIPCION, EFRAU.CONTACTO_EMPRESA, ");
		query.append("       EFRAU.ID_TIPO_EMPRESA, EFRAU.ID_ACTIVIDAD_ECONOMICA, EFRAU.NUMERO_EMPLEADOS, EFRAU.ID_MEDIO, ");
		query.append("       EFRAU.CONFIDENCIAL,EFRAU.PAGINA_WEB, EFRAU.ACEPTACION_TERMINOS, EFRAU.FECHA_ALTA, ");
		query.append("       EFRAU.ESTATUS, EFRAU.FECHA_ULTIMA_ACTUALIZACION, EFRAU.ID_USUARIO, EFRAU.ASEGURA_DATOS ");

		query.append("  FROM EMPRESA_FRAUDULENTA EFRAU, "); // EMPRESAS FRAUDULENTAS
		query.append("  ( ");
		query.append("    SELECT ID_EMPRESA, ID_PORTAL_EMPLEO, RFC, ID_TIPO_PERSONA, NOMBRE, APELLIDO1, APELLIDO2, RAZON_SOCIAL, CORREO_ELECTRONICO ");
		query.append("      FROM EMPRESA_POR_AUTORIZAR EFRAU WHERE ID_EMPRESA = ? "); // EMPRESA POR AUTORIZAR
		query.append("    UNION ");
		query.append("    SELECT ID_EMPRESA, ID_PORTAL_EMPLEO, RFC, ID_TIPO_PERSONA, NOMBRE, APELLIDO1, APELLIDO2, RAZON_SOCIAL, CORREO_ELECTRONICO ");
		query.append("      FROM EMPRESA EMP WHERE ID_EMPRESA = ? "); // EMPRESA MODIFICADA
		query.append("  ) EAUT ");

		query.append(" WHERE EFRAU.ID_PORTAL_EMPLEO = EAUT.ID_PORTAL_EMPLEO ");
		query.append("    OR EFRAU.CORREO_ELECTRONICO = EAUT.CORREO_ELECTRONICO ");
		query.append("    OR UPPER(DECODE(EFRAU.ID_TIPO_PERSONA, ? , NVL(EFRAU.NOMBRE, '') || NVL(EFRAU.APELLIDO1, '') || NVL(EFRAU.APELLIDO2, ''), EFRAU.RAZON_SOCIAL)) = ");
		query.append("       UPPER(DECODE(EAUT.ID_TIPO_PERSONA, ? , NVL(EAUT.NOMBRE, '') || NVL(EAUT.APELLIDO1, '') || NVL(EAUT.APELLIDO2, ''), EAUT.RAZON_SOCIAL)) ");
		query.append("  AND EFRAU.ID_EMPRESA != EAUT.ID_EMPRESA "); // EMPRESAS DIFERENTES
		//query.append("  AND EAUT.ID_EMPRESA = ? ");

		return query.toString();
	}

	/**
	 * Consulta de Telefonos de Empresas Fraudulentas iguales a los Telefonos de la empresa indicada
	 * @return
	 */
	private String getQueryTelefonosEmpFraudulentas(){
		StringBuilder query = new StringBuilder();
		
		query.append("SELECT TFRAU.ID_TELEFONO, TFRAU.ID_PROPIETARIO, TFRAU.ID_TIPO_PROPIETARIO, TFRAU.ID_TIPO_TELEFONO, ");
		query.append("       TFRAU.ACCESO, TFRAU.CLAVE, TFRAU.TELEFONO, TFRAU.EXTENSION, TFRAU.PRINCIPAL, ");

		// CAMPOS DE LA EMPRESA FRAUDULENTA
		query.append("       EFRAU.ID_EMPRESA, EFRAU.ID_PORTAL_EMPLEO, EFRAU.RFC, EFRAU.ID_TIPO_PERSONA, ");
		query.append("       EFRAU.NOMBRE, EFRAU.APELLIDO1, EFRAU.APELLIDO2, EFRAU.FECHA_NACIMIENTO, EFRAU.RAZON_SOCIAL, ");
		query.append("       EFRAU.CORREO_ELECTRONICO, EFRAU.FECHA_ACTA, EFRAU.DESCRIPCION, EFRAU.CONTACTO_EMPRESA, ");
		query.append("       EFRAU.ID_TIPO_EMPRESA, EFRAU.ID_ACTIVIDAD_ECONOMICA, EFRAU.NUMERO_EMPLEADOS, EFRAU.ID_MEDIO, ");
		query.append("       EFRAU.CONFIDENCIAL,EFRAU.PAGINA_WEB, EFRAU.ACEPTACION_TERMINOS, EFRAU.FECHA_ALTA, ");
		query.append("       EFRAU.ESTATUS, EFRAU.FECHA_ULTIMA_ACTUALIZACION, EFRAU.ID_USUARIO, EFRAU.ASEGURA_DATOS ");
		       
		query.append("  FROM TELEFONO TFRAU, "); // TELEFONOS DE EMPRESAS FRAUDULENTAS
		query.append("       EMPRESA_FRAUDULENTA EFRAU, ");
		query.append("       TELEFONO TEL ");
		
		query.append(" WHERE TFRAU.ID_TIPO_PROPIETARIO = ? ");              // TELEFONOS DE EMPRESAS
		query.append("   AND EFRAU.ID_EMPRESA = TFRAU.ID_PROPIETARIO ");    // DONDE EL TELEFONO SEA DE UNA EMPRESA FRAUDULENTA
		query.append("   AND TEL.ID_TIPO_PROPIETARIO = TFRAU.ID_TIPO_PROPIETARIO ");
		query.append("   AND TEL.ID_PROPIETARIO != TFRAU.ID_PROPIETARIO "); // SE COMPARAN LOS TELEFONOS QUE NO SEAN LOS MISMOS
		query.append("   AND TEL.ID_PROPIETARIO = ? ");                    // TELEFONOS DE LA EMPRESA INDICADA
		query.append("   AND UPPER(NVL(TEL.ACCESO, '') || NVL(TEL.CLAVE, '') || NVL(TEL.TELEFONO, '') || NVL(TEL.EXTENSION, '')) = "); // SE COMPARAN LOS NUMEROS TELEFONICOS
		query.append("       UPPER(NVL(TFRAU.ACCESO, '') || NVL(TFRAU.CLAVE, '') || NVL(TFRAU.TELEFONO, '') || NVL(TFRAU.EXTENSION, '')) ");
		
		return query.toString();
	}

	/**
	 * Consulta de Domicilios de Empresas Fraudulentas iguales a los domicilios de la empresa indicada 
	 * @return
	 */
	private String getQueryDomiciliosEmpFraudulentas(){
		StringBuilder query = new StringBuilder();

		query.append("SELECT DFRAU.ID_DOMICILIO, DFRAU.ID_PROPIETARIO, DFRAU.ID_TIPO_PROPIETARIO, ");
		query.append("       DFRAU.ID_ENTIDAD, DFRAU.ID_MUNICIPIO, DFRAU.ID_COLONIA, DFRAU.CALLE, DFRAU.NUMERO_INTERIOR, ");
		query.append("       DFRAU.NUMERO_EXTERIOR, DFRAU.ENTRE_CALLE, DFRAU.Y_CALLE, DFRAU.CODIGO_POSTAL, ");

		// CAMPOS DE LA EMPRESA FRAUDULENTA
		query.append("       EFRAU.ID_EMPRESA, EFRAU.ID_PORTAL_EMPLEO, EFRAU.RFC, EFRAU.ID_TIPO_PERSONA, ");
		query.append("       EFRAU.NOMBRE, EFRAU.APELLIDO1, EFRAU.APELLIDO2, EFRAU.FECHA_NACIMIENTO, EFRAU.RAZON_SOCIAL, ");
		query.append("       EFRAU.CORREO_ELECTRONICO, EFRAU.FECHA_ACTA, EFRAU.DESCRIPCION, EFRAU.CONTACTO_EMPRESA, ");
		query.append("       EFRAU.ID_TIPO_EMPRESA, EFRAU.ID_ACTIVIDAD_ECONOMICA, EFRAU.NUMERO_EMPLEADOS, EFRAU.ID_MEDIO, ");
		query.append("       EFRAU.CONFIDENCIAL,EFRAU.PAGINA_WEB, EFRAU.ACEPTACION_TERMINOS, EFRAU.FECHA_ALTA, ");
		query.append("       EFRAU.ESTATUS, EFRAU.FECHA_ULTIMA_ACTUALIZACION, EFRAU.ID_USUARIO, EFRAU.ASEGURA_DATOS ");
		       
		query.append("  FROM DOMICILIO DFRAU, ");
		query.append("       EMPRESA_FRAUDULENTA EFRAU, ");
		query.append("       DOMICILIO DOM ");
		query.append(" WHERE DFRAU.ID_TIPO_PROPIETARIO = ? ");              // DOMICILIOS DE EMPRESAS
		query.append("   AND EFRAU.ID_EMPRESA = DFRAU.ID_PROPIETARIO ");    // DONDE EL PROPIETARIO SEA DE UNA EMPRESA FRAUDULENTA
		query.append("   AND DOM.ID_TIPO_PROPIETARIO = DFRAU.ID_TIPO_PROPIETARIO ");
		query.append("   AND DOM.ID_PROPIETARIO != DFRAU.ID_PROPIETARIO "); // SE COMPARAN LOS DOMICILIOS QUE NO SEAN LOS MISMOS
		query.append("   AND DOM.ID_PROPIETARIO = ? ");                    // DOMICILIOS DE LA EMPRESA INDICADA
		query.append("   AND UPPER(DOM.ID_ENTIDAD || DOM.ID_MUNICIPIO || DOM.ID_COLONIA || DOM.CALLE || "); // COMPARACION DE DATOS DE DOMICILIOS
		query.append("             NVL(DOM.NUMERO_INTERIOR,'') || DOM.NUMERO_EXTERIOR || DOM.ENTRE_CALLE || DOM.Y_CALLE || DOM.CODIGO_POSTAL) = ");
		query.append("       UPPER(DFRAU.ID_ENTIDAD || DFRAU.ID_MUNICIPIO || DFRAU.ID_COLONIA || DFRAU.CALLE || ");
		query.append("             NVL(DFRAU.NUMERO_INTERIOR,'') || DFRAU.NUMERO_EXTERIOR || DFRAU.ENTRE_CALLE || DFRAU.Y_CALLE || DFRAU.CODIGO_POSTAL) ");

		return query.toString();
	}
	
	private String getQueryUsuariosQueDetectaronFraude(){
		StringBuilder query = new StringBuilder();
		query.append("  select b.id_registro as id_registro, b.id_usuario as id_usuario, b.fecha_evento as fecha_evento, ");
		query.append("  	u.usuario as usuario, u.nombre as nombre, u.apellido1 as apellido1, u.apellido2 as apellido2 ");
		query.append("  from bitacora b, usuario u ");
		query.append("  where b.id_registro in(?) and b.id_evento=" + EVENTO.DETECTAR_EMPRESA_FRAUDULENTA.getIdEvento() ); 
		query.append("  and id_tipo_propietario=" + TIPO_USUARIO.EMPRESA.getIdTipoUsuario() + " and u.id_usuario=b.id_usuario ");
		
		return query.toString();
	}

}
