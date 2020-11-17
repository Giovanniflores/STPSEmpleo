package mx.gob.stps.portal.core.empresa.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.data.TemplateDAO;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;

public class EmpresaDAO extends TemplateDAO{
	
	//private static final int QUERY = 99;
	
	private static final int QUERY_ACTUALIZA_ESTATUS_OFERTAS_EMPRESA = 1;
	/*private static final int QUERY_FILTRA_EMPRESA_PERSONA_FISICA_NOMBRE_APELLIDO = 2;
	private static final int QUERY_FILTRA_EMPRESA_PERSONA_MORAL_RAZON = 3;
	private static final int QUERY_FILTRA_EMPRESA_PERSONA_MORAL_FECHA = 4;
	private static final int QUERY_FILTRA_EMPRESA_PERSONA_MORAL_FECHA_RAZON = 5;
	private static final int QUERY_FILTRA_EMPRESA_PERSONA_FISICA_NOMBRE = 6;
	private static final int QUERY_FILTRA_EMPRESA_PERSONA_FISICA_APELLIDO = 7;
	private static final int QUERY_FILTRA_OFERTA_EMPRESA_ESTATUS = 8;*/
	private static final int QUERY_ACTUALIZA_ESTATUS_INACTIVA_OFERTAS_EMPRESA = 9;
	private int QUERY_EMPRESA = 0;
	
	public EmpresaDAO(){}
	
	public EmpresaDAO(Connection conn){
		super(conn);
	}
	
	public int actualizaEstatusOfertasEmpresa(int estatusFinal, long idEmpresa) throws SQLException {
		this.QUERY_EMPRESA = QUERY_ACTUALIZA_ESTATUS_OFERTAS_EMPRESA;
		Object[] params = new Object[2];
		params[0] = estatusFinal;
		params[1] = idEmpresa;
		
		int result = executeUpdate(params);

		return result;
	}	
	
	public int actualizaEstatusInactivaOfertasEmpresa(int estatusFinal, long idEmpresa) throws SQLException {
		this.QUERY_EMPRESA = QUERY_ACTUALIZA_ESTATUS_INACTIVA_OFERTAS_EMPRESA;
		Object[] params = new Object[2];
		params[0] = estatusFinal;
		params[1] = idEmpresa;
		
		int result = executeUpdate(params);

		return result;
	}	
	
	/*private int idTipoPersona;
	private String correoElectronico;
	private String idPortalEmpleo;
	private String nombre;
	private String apellido1;
	private String razonSocial;
	private java.sql.Date fechaActaDate;
	
	public List<EmpresaVO> consultaEmpresas(int idTipoPersona,
											String correoElectronico,
											String idPortalEmpleo,
											String nombre,
											String apellido1,
											String razonSocial,
											String fechaActa) throws SQLException {

		this.QUERY_EMPRESA = QUERY;

		this.idTipoPersona     = idTipoPersona;
		this.correoElectronico = correoElectronico;
		this.idPortalEmpleo    = idPortalEmpleo;
		this.nombre            = nombre;
		this.apellido1         = apellido1;
		this.razonSocial       = razonSocial;
		
		Date dateAux = Utils.convert(fechaActa);
		if (dateAux!=null)
			this.fechaActaDate = new java.sql.Date(dateAux.getTime());
		
		List<Object> listParams = new ArrayList<Object>();
		
		for (int i=0; i<3; i++){
			listParams.add(idTipoPersona);
			
			if (!isEmpty(correoElectronico)) listParams.add(correoElectronico +"%");
			if (!isEmpty(idPortalEmpleo))    listParams.add(idPortalEmpleo +"%");
			
			if (idTipoPersona == TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()){
				if (!isEmpty(nombre))        listParams.add(nombre +"%");
				if (!isEmpty(apellido1))     listParams.add(apellido1 +"%");
			}else if (idTipoPersona == TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona()){
				if (!isEmpty(razonSocial))   listParams.add(razonSocial +"%");
				if (fechaActaDate!=null)     listParams.add(fechaActaDate);
			}
		}

		Object[] params = listParams.toArray();

		CachedRowSet rowSet = executeQuery(params);
		
		List<EmpresaVO> lstEmpresas = new ArrayList<EmpresaVO>();
		while (rowSet.next())
			lstEmpresas.add(createEmpresaVO(rowSet));
		
		return lstEmpresas;
	}

	private String getQueryEmpresas() {
		StringBuilder query = new StringBuilder();

		query.append(" SELECT e.razon_social, e.contacto_empresa, e.fecha_acta, e.fecha_alta, ");
		query.append("    e.fecha_nacimiento, e.id_empresa, e.id_portal_empleo, e.id_tipo_persona, "); 
		query.append("    e.nombre, e.apellido1, e.apellido2, e.correo_electronico, e.estatus,  ");
		query.append("    'EMPRESA' as tblEmpresa, ");
		query.append("    0 as registro_estatus ");
		query.append(" FROM EMPRESA E ");
		query.append(" WHERE E.ID_TIPO_PERSONA = ? ");
		
		if (!isEmpty(correoElectronico)) query.append(" AND LOWER(CORREO_ELECTRONICO) LIKE LOWER(?) ");
		if (!isEmpty(idPortalEmpleo))    query.append(" AND LOWER(ID_PORTAL_EMPLEO) LIKE LOWER(?) ");
		
		if (idTipoPersona == TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()){
			if (!isEmpty(nombre))        query.append(" AND LOWER(NOMBRE) LIKE LOWER(?) ");
			if (!isEmpty(apellido1))     query.append(" AND LOWER(APELLIDO1) LIKE LOWER(?) ");			
		}else if (idTipoPersona == TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona()){
			if (!isEmpty(razonSocial))   query.append(" AND LOWER(RAZON_SOCIAL) LIKE LOWER(?) ");
			if (fechaActaDate!=null)     query.append(" AND FECHA_ACTA = ? ");	
		}
			   
		query.append(" UNION ");

		query.append(" SELECT e.razon_social, e.contacto_empresa, e.fecha_acta, e.fecha_alta, "); 
		query.append("    e.fecha_nacimiento, e.id_empresa, e.id_portal_empleo, e.id_tipo_persona,  ");
		query.append("    e.nombre, e.apellido1, e.apellido2, e.correo_electronico, e.estatus,  ");
		query.append("    'EMPRESA_POR_AUTORIZAR' as tblEmpresa, ");
		query.append("    0 as registro_estatus ");
		query.append(" FROM EMPRESA_POR_AUTORIZAR E ");
		query.append(" WHERE E.ID_TIPO_PERSONA = ? ");
		
		if (!isEmpty(correoElectronico)) query.append(" AND LOWER(CORREO_ELECTRONICO) LIKE LOWER(?) ");
		if (!isEmpty(idPortalEmpleo))    query.append(" AND LOWER(ID_PORTAL_EMPLEO) LIKE LOWER(?) ");

		if (idTipoPersona == TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()){
			if (!isEmpty(nombre))        query.append(" AND LOWER(NOMBRE) LIKE LOWER(?) ");
			if (!isEmpty(apellido1))     query.append(" AND LOWER(APELLIDO1) LIKE LOWER(?) ");			
		}else if (idTipoPersona == TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona()){
			if (!isEmpty(razonSocial))   query.append(" AND LOWER(RAZON_SOCIAL) LIKE LOWER(?) ");
			if (fechaActaDate!=null)     query.append(" AND FECHA_ACTA = ? ");
		}

		query.append(" UNION ");

		query.append(" SELECT razon_social, contacto_empresa, fecha_acta, fecha_alta, "); 
		query.append("    fecha_nacimiento, id_empresa, id_portal_empleo, id_tipo_persona,  ");
		query.append("    nombre, apellido1, apellido2, correo_electronico, estatus, ");
		query.append("    'EMPRESA_FRAUDULENTA' as tblEmpresa, ");
		query.append("    0 as registro_estatus ");
		query.append(" FROM EMPRESA_FRAUDULENTA E ");
		query.append(" WHERE E.ID_TIPO_PERSONA = ? ");
		
		if (!isEmpty(correoElectronico)) query.append(" AND LOWER(CORREO_ELECTRONICO) LIKE LOWER(?) ");
		if (!isEmpty(idPortalEmpleo))    query.append(" AND LOWER(ID_PORTAL_EMPLEO) LIKE LOWER(?) ");
		
		if (idTipoPersona == TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()){
			if (!isEmpty(nombre))        query.append(" AND LOWER(NOMBRE) LIKE LOWER(?) ");
			if (!isEmpty(apellido1))     query.append(" AND LOWER(APELLIDO1) LIKE LOWER(?) ");
		}else if (idTipoPersona == TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona()){
			if (!isEmpty(razonSocial))   query.append(" AND LOWER(RAZON_SOCIAL) LIKE LOWER(?) ");
			if (fechaActaDate!=null)     query.append(" AND FECHA_ACTA = ? ");
		}

		return query.toString();
	}	
	
	private boolean isEmpty(String valor){
		return (valor==null || valor.trim().isEmpty());
	}*/
	
	/*public List<EmpresaVO> getQueryFiltraEmpresaPersonaFisicaNombreApellido(String nombre, String apellido1) throws SQLException {
		
		this.QUERY_EMPRESA = QUERY_FILTRA_EMPRESA_PERSONA_FISICA_NOMBRE_APELLIDO;
		System.out.println("---------Query:" + this.QUERY_EMPRESA);    		
		
		Object[] params = new Object[6];
		params[0] = nombre + "%";
		params[1] = apellido1 + "%";
		params[2] = nombre + "%";
		params[3] = apellido1 + "%";
		params[4] = nombre + "%";
		params[5] = apellido1 + "%";
		
		System.out.println("---------params[0]:" + params[0]);
		System.out.println("---------params[1]:" + params[1]);
		
		CachedRowSet rowSet = executeQuery(params);
		
		List<EmpresaVO> lstEmpresas = new ArrayList<EmpresaVO>();
		while (rowSet.next())
			lstEmpresas.add(createEmpresaVO(rowSet));		
		
		System.out.println("---------lstEmpresas:" + lstEmpresas.size());
		return lstEmpresas;
	}*/
	
	/*public List<EmpresaVO> getQueryFiltraEmpresaPersonaFisicaNombre(String nombre) throws SQLException {
		
		this.QUERY_EMPRESA = QUERY_FILTRA_EMPRESA_PERSONA_FISICA_NOMBRE;
		System.out.println("---------Query:" + this.QUERY_EMPRESA);    		
		
		Object[] params = new Object[3];
		params[0] = nombre + "%";
		params[1] = nombre + "%";
		params[2] = nombre + "%";
		System.out.println("---------params[0]:" + params[0]);
		
		CachedRowSet rowSet = executeQuery(params);
		
		List<EmpresaVO> lstEmpresas = new ArrayList<EmpresaVO>();
		while (rowSet.next())
			lstEmpresas.add(createEmpresaVO(rowSet));		
		
		System.out.println("---------lstEmpresas:" + lstEmpresas.size());
		return lstEmpresas;
	}*/
	
	/*public List<EmpresaVO> getQueryFiltraEmpresaPersonaFisicaApellido(String apellido1) throws SQLException {
		
		this.QUERY_EMPRESA = QUERY_FILTRA_EMPRESA_PERSONA_FISICA_APELLIDO;
		System.out.println("---------Query:" + this.QUERY_EMPRESA);    		
		
		Object[] params = new Object[3];
		params[0] = apellido1 + "%";
		params[1] = apellido1 + "%";
		params[2] = apellido1 + "%";
		System.out.println("---------params[0]:" + params[0]);
		
		CachedRowSet rowSet = executeQuery(params);
		
		List<EmpresaVO> lstEmpresas = new ArrayList<EmpresaVO>();
		while (rowSet.next())
			lstEmpresas.add(createEmpresaVO(rowSet));		
		
		System.out.println("---------lstEmpresas:" + lstEmpresas.size());
		return lstEmpresas;
	}*/

	/*public List<EmpresaVO> getQueryFiltraEmpresaPersonaMoral(String razonSocial, Date fechaActa) throws SQLException {
		Object[] params;
		
		if(null!=fechaActa){
			this.QUERY_EMPRESA = QUERY_FILTRA_EMPRESA_PERSONA_MORAL_FECHA;	
			params = new Object[3];
			params[0] = fechaActa;
			params[1] = fechaActa;
			params[2] = fechaActa;
			System.out.println("---------params[0]:" + params[0]);
			
			if(null!=razonSocial && !razonSocial.equalsIgnoreCase("")){
				this.QUERY_EMPRESA = QUERY_FILTRA_EMPRESA_PERSONA_MORAL_FECHA_RAZON;
				params = new Object[6];
				params[0] = razonSocial + "%";
				params[1] = fechaActa;
				params[2] = razonSocial + "%";
				params[3] = fechaActa;
				params[4] = razonSocial + "%";
				params[5] = fechaActa;
				
				System.out.println("---------params[0]:" + params[0]);
				System.out.println("---------params[1]:" + params[1]);
				
			}			
		} else {
			this.QUERY_EMPRESA = QUERY_FILTRA_EMPRESA_PERSONA_MORAL_RAZON;
			params = new Object[3];
			params[0] = razonSocial + "%";
			params[1] = razonSocial + "%";
			params[2] = razonSocial + "%";
			System.out.println("---------params[0]:" + params[0]);
			
		}
		System.out.println("---------Query:" + this.QUERY_EMPRESA);
		
		
		CachedRowSet rowSet = executeQuery(params);
		
		List<EmpresaVO> lstEmpresas = new ArrayList<EmpresaVO>();
		while (rowSet.next())
			lstEmpresas.add(createEmpresaVO(rowSet));		
		
		System.out.println("---------lstEmpresas:" + lstEmpresas.size());
		return lstEmpresas;
	}*/
	
	/*public List<Long> consultaOfertas(long idEmpresa, int estatus) throws SQLException {
		this.QUERY_EMPRESA = QUERY_FILTRA_OFERTA_EMPRESA_ESTATUS;
		
		CachedRowSet rowSet = executeQuery(new Object[]{idEmpresa, estatus});
		
		List<Long> idsOfertasEmpleo = new ArrayList<Long>();
		
		while (rowSet.next()){
			long idOfertaEmpleo = rowSet.getLong("ID_OFERTA_EMPLEO");
			idsOfertasEmpleo.add(idOfertaEmpleo);
		}

		return idsOfertasEmpleo;
	}*/
	
	protected String getQueryActualizaEstatusOfertasEmpresa() {
		StringBuilder query = new StringBuilder();
		
		query.append("UPDATE oferta_empleo ");
		query.append("   SET estatus=?");		
		query.append("   WHERE id_empresa=? AND estatus=" +  ESTATUS.ACTIVO.getIdOpcion());
		
		return query.toString();
	}
	
	
	protected String getQueryActualizaEstatusInativoOfertasEmpresa() {
		StringBuilder query = new StringBuilder();
		
		query.append("UPDATE oferta_empleo ");
		query.append("   SET estatus=?");		
		query.append("   WHERE id_empresa=? AND estatus=" + ESTATUS.EMP_MODIFICADA.getIdOpcion());
		
		return query.toString();
	}

	private String getQueryFiltraEmpresaPersonaFisicaNombreApellido(){
		StringBuilder query = new StringBuilder();
		query.append(" SELECT razon_social, contacto_empresa, fecha_acta, ");
		query.append(" fecha_alta, fecha_nacimiento, id_empresa, id_portal_empleo, ");
		query.append(" id_tipo_persona, nombre, apellido1, apellido2, correo_electronico, estatus, 'EMPRESA' as tblEmpresa ");
		query.append(" FROM empresa ");
		query.append(" WHERE id_tipo_persona=" + Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() );
		query.append(" AND (LOWER(nombre) LIKE LOWER(?) ");
		query.append(" AND LOWER(apellido1) LIKE LOWER(?)) ");
		query.append(" UNION ");
		query.append(" SELECT razon_social, contacto_empresa, fecha_acta, ");
		query.append(" fecha_alta, fecha_nacimiento, id_empresa, id_portal_empleo, "); 
		query.append(" id_tipo_persona, nombre, apellido1, apellido2, correo_electronico, estatus, 'EMPRESA_FRAUDULENTA' as tblEmpresa ");
		query.append(" FROM empresa_fraudulenta ");
		query.append(" WHERE id_tipo_persona=" + Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() );
		query.append(" AND (LOWER(nombre) LIKE LOWER(?) ");
		query.append(" AND LOWER(apellido1) LIKE LOWER(?)) ");      
		query.append(" UNION ");
		query.append(" SELECT razon_social, contacto_empresa, fecha_acta, ");
		query.append(" fecha_alta, fecha_nacimiento, id_empresa, id_portal_empleo, ");
		query.append(" id_tipo_persona, nombre, apellido1, apellido2, correo_electronico, estatus, 'EMPRESA_POR_AUTORIZAR' as tblEmpresa ");
		query.append(" FROM empresa_por_autorizar ");
		query.append(" WHERE id_tipo_persona=" + Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() );
		query.append(" AND (LOWER(nombre) LIKE LOWER(?) ");
		query.append(" AND LOWER(apellido1) LIKE LOWER(?)) ");  		
		/* NUEVA
query.append(" SELECT e.razon_social as razon_social, e.contacto_empresa as contacto_empresa, e.fecha_acta as fecha_acta, ");
query.append(" e.fecha_alta as fecha_alta, e.fecha_nacimiento as fecha_nacimiento, e.id_empresa as id_empresa, e.id_portal_empleo as id_portal_empleo, ");
query.append(" e.id_tipo_persona as id_tipo_persona, e.nombre as nombre, e.apellido1 as apellido1, e.apellido2 as apellido2, "); 
query.append(" e.correo_electronico as correo_electronico, e.estatus as estatus, 'EMPRESA' as tblEmpresa, ");
query.append(" CASE ");
query.append(" WHEN (r.estatus IS NULL) THEN 0 ");
query.append(" ELSE r.estatus ");
query.append(" END AS registro_estatus, "); 
query.append(" CASE ");
query.append(" WHEN (r.id_reg_validar IS NULL) THEN 0 ");
query.append(" ELSE r.id_reg_validar ");
query.append(" END AS id_reg_validar "); 
query.append(" FROM empresa e LEFT JOIN registro_por_validar r ");
query.append(" ON e.id_empresa=r.id_registro and r.id_tipo_registro=" + Constantes.TIPO_REGISTRO.EMPRESA.getIdTipoRegistro() );
query.append(" WHERE e.id_tipo_persona=" + Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() );
query.append(" AND LOWER(apellido1) LIKE LOWER(?)) ");
query.append(" UNION ");
query.append(" SELECT razon_social, contacto_empresa, fecha_acta, "); 
query.append(" fecha_alta, fecha_nacimiento, id_empresa, id_portal_empleo, ");
query.append(" id_tipo_persona, nombre, apellido1, apellido2, correo_electronico, ");
query.append(" estatus, 'EMPRESA_FRAUDULENTA' as tblEmpresa, 0 as registro_estatus  ");
query.append(" FROM empresa_fraudulenta ");
query.append(" WHERE id_tipo_persona=" + Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() );
query.append(" AND LOWER(nombre) LIKE LOWER(?) ");
query.append(" AND LOWER(apellido1) LIKE LOWER(?)) ");      
query.append(" UNION ");
query.append(" SELECT e.razon_social as razon_social, e.contacto_empresa as contacto_empresa, e.fecha_acta as fecha_acta, ");
query.append(" e.fecha_alta as fecha_alta, e.fecha_nacimiento as fecha_nacimiento, e.id_empresa as id_empresa, e.id_portal_empleo as id_portal_empleo, ");
query.append(" e.id_tipo_persona as id_tipo_persona, e.nombre as nombre, e.apellido1 as apellido1, e.apellido2 as apellido2, ");
query.append(" e.correo_electronico as correo_electronico, e.estatus as estatus, 'EMPRESA_POR_AUTORIZAR' as tblEmpresa, ");
query.append(" CASE ");
query.append(" WHEN (r.estatus IS NULL) THEN 0 ");
query.append(" ELSE r.estatus ");
query.append(" END AS registro_estatus, "); 
query.append(" CASE ");
query.append(" WHEN (r.id_reg_validar IS NULL) THEN 0 ");
query.append(" ELSE r.id_reg_validar ");
query.append(" END AS id_reg_validar "); 
query.append(" FROM empresa_por_autorizar e  LEFT JOIN registro_por_validar r ");
query.append(" ON e.id_empresa=r.id_registro and r.id_tipo_registro=" + Constantes.TIPO_REGISTRO.EMPRESA.getIdTipoRegistro() );
query.append(" WHERE e.id_tipo_persona=" + Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() );
query.append(" AND LOWER(nombre) LIKE LOWER(?) ");
query.append(" AND LOWER(apellido1) LIKE LOWER(?)) ");  	

		 * */
		return query.toString();
	}

	private String getQueryFiltraEmpresaPersonaFisicaNombre(){
		StringBuilder query = new StringBuilder();
		query.append("SELECT razon_social, contacto_empresa, fecha_acta, ");
		query.append(" fecha_alta, fecha_nacimiento, id_empresa, id_portal_empleo, ");
		query.append(" id_tipo_persona, nombre, apellido1, apellido2, correo_electronico, estatus, 'EMPRESA' as tblEmpresa ");
		query.append("   FROM empresa");
		query.append("   WHERE id_tipo_persona=" + Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() + 
		" AND LOWER(nombre) LIKE LOWER(?) ");
		query.append(" UNION ");
		query.append("SELECT razon_social, contacto_empresa, fecha_acta, ");
		query.append(" fecha_alta, fecha_nacimiento, id_empresa, id_portal_empleo, ");
		query.append(" id_tipo_persona, nombre, apellido1, apellido2, correo_electronico, estatus, 'EMPRESA_FRAUDULENTA' as tblEmpresa ");
		query.append("   FROM empresa_fraudulenta");
		query.append("   WHERE id_tipo_persona=" + Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() + 
		" AND LOWER(nombre) LIKE LOWER(?) ");
		query.append(" UNION ");
		query.append("SELECT razon_social, contacto_empresa, fecha_acta, ");
		query.append(" fecha_alta, fecha_nacimiento, id_empresa, id_portal_empleo, ");
		query.append(" id_tipo_persona, nombre, apellido1, apellido2, correo_electronico, estatus, 'EMPRESA_POR_AUTORIZAR' as tblEmpresa ");
		query.append("   FROM empresa_por_autorizar");
		query.append("   WHERE id_tipo_persona=" + Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() + 
		" AND LOWER(nombre) LIKE LOWER(?) ");		
		/* NUEVA
query.append(" SELECT e.razon_social as razon_social, e.contacto_empresa as contacto_empresa, e.fecha_acta as fecha_acta, ");
query.append(" e.fecha_alta as fecha_alta, e.fecha_nacimiento as fecha_nacimiento, e.id_empresa as id_empresa, e.id_portal_empleo as id_portal_empleo, ");
query.append(" e.id_tipo_persona as id_tipo_persona, e.nombre as nombre, e.apellido1 as apellido1, e.apellido2 as apellido2, "); 
query.append(" e.correo_electronico as correo_electronico, e.estatus as estatus, 'EMPRESA' as tblEmpresa, ");
query.append(" CASE ");
query.append(" WHEN (r.estatus IS NULL) THEN 0 ");
query.append(" ELSE r.estatus ");
query.append(" END AS registro_estatus, "); 
query.append(" CASE ");
query.append(" WHEN (r.id_reg_validar IS NULL) THEN 0 ");
query.append(" ELSE r.id_reg_validar ");
query.append(" END AS id_reg_validar "); 
query.append(" FROM empresa e LEFT JOIN registro_por_validar r ");
query.append(" ON e.id_empresa=r.id_registro and r.id_tipo_registro=" + Constantes.TIPO_REGISTRO.EMPRESA.getIdTipoRegistro() );
query.append(" WHERE e.id_tipo_persona=" + Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() );
query.append(" AND LOWER(nombre) LIKE LOWER(?) ");
query.append(" UNION ");
query.append(" SELECT razon_social, contacto_empresa, fecha_acta, "); 
query.append(" fecha_alta, fecha_nacimiento, id_empresa, id_portal_empleo, ");
query.append(" id_tipo_persona, nombre, apellido1, apellido2, correo_electronico, ");
query.append(" estatus, 'EMPRESA_FRAUDULENTA' as tblEmpresa, 0 as registro_estatus  ");
query.append(" FROM empresa_fraudulenta ");
query.append(" WHERE id_tipo_persona=" + Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() );
query.append(" AND LOWER(nombre) LIKE LOWER(?) ");
query.append(" UNION  ");
query.append(" SELECT e.razon_social as razon_social, e.contacto_empresa as contacto_empresa, e.fecha_acta as fecha_acta, ");
query.append(" e.fecha_alta as fecha_alta, e.fecha_nacimiento as fecha_nacimiento, e.id_empresa as id_empresa, e.id_portal_empleo as id_portal_empleo, ");
query.append(" e.id_tipo_persona as id_tipo_persona, e.nombre as nombre, e.apellido1 as apellido1, e.apellido2 as apellido2, ");
query.append(" e.correo_electronico as correo_electronico, e.estatus as estatus, 'EMPRESA_POR_AUTORIZAR' as tblEmpresa, ");
query.append(" CASE ");
query.append(" WHEN (r.estatus IS NULL) THEN 0 ");
query.append(" ELSE r.estatus ");
query.append(" END AS registro_estatus, "); 
query.append(" CASE ");
query.append(" WHEN (r.id_reg_validar IS NULL) THEN 0 ");
query.append(" ELSE r.id_reg_validar ");
query.append(" END AS id_reg_validar "); 
query.append(" FROM empresa_por_autorizar e  LEFT JOIN registro_por_validar r ");
query.append(" ON e.id_empresa=r.id_registro and r.id_tipo_registro=" + Constantes.TIPO_REGISTRO.EMPRESA.getIdTipoRegistro() );
query.append(" WHERE e.id_tipo_persona=" + Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() );
query.append(" AND LOWER(nombre) LIKE LOWER(?) ");

		 * */
		
		return query.toString();
	}
	
	private String getQueryFiltraEmpresaPersonaFisicaApellido(){
		StringBuilder query = new StringBuilder();
		
		query.append("SELECT razon_social, contacto_empresa, fecha_acta, ");
		query.append(" fecha_alta, fecha_nacimiento, id_empresa, id_portal_empleo, ");
		query.append(" id_tipo_persona, nombre, apellido1, apellido2, correo_electronico, estatus, 'EMPRESA' as tblEmpresa  ");
		query.append("   FROM empresa");
		query.append("   WHERE id_tipo_persona=" + Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() ); 
		query.append("   AND LOWER(apellido1) LIKE LOWER(?) ");
		query.append(" UNION ");
		query.append("SELECT razon_social, contacto_empresa, fecha_acta, ");
		query.append(" fecha_alta, fecha_nacimiento, id_empresa, id_portal_empleo, ");
		query.append(" id_tipo_persona, nombre, apellido1, apellido2, correo_electronico, estatus, 'EMPRESA_FRAUDULENTA' as tblEmpresa  ");
		query.append("   FROM empresa_fraudulenta");
		query.append("   WHERE id_tipo_persona=" + Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() ); 
		query.append("   AND LOWER(apellido1) LIKE LOWER(?) ");
		query.append(" UNION ");
		query.append("SELECT razon_social, contacto_empresa, fecha_acta, ");
		query.append(" fecha_alta, fecha_nacimiento, id_empresa, id_portal_empleo, ");
		query.append(" id_tipo_persona, nombre, apellido1, apellido2, correo_electronico, estatus, 'EMPRESA_POR_AUTORIZAR' as tblEmpresa  ");
		query.append("   FROM empresa_por_autorizar");
		query.append("   WHERE id_tipo_persona=" + Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() ); 
		query.append("   AND LOWER(apellido1) LIKE LOWER(?) ");		
		/* NUEVA
query.append(" SELECT e.razon_social as razon_social, e.contacto_empresa as contacto_empresa, e.fecha_acta as fecha_acta, ");
query.append(" e.fecha_alta as fecha_alta, e.fecha_nacimiento as fecha_nacimiento, e.id_empresa as id_empresa, e.id_portal_empleo as id_portal_empleo, ");
query.append(" e.id_tipo_persona as id_tipo_persona, e.nombre as nombre, e.apellido1 as apellido1, e.apellido2 as apellido2, "); 
query.append(" e.correo_electronico as correo_electronico, e.estatus as estatus, 'EMPRESA' as tblEmpresa, ");
query.append(" CASE ");
query.append(" WHEN (r.estatus IS NULL) THEN 0 ");
query.append(" ELSE r.estatus ");
query.append(" END AS registro_estatus, "); 
query.append(" CASE ");
query.append(" WHEN (r.id_reg_validar IS NULL) THEN 0 ");
query.append(" ELSE r.id_reg_validar ");
query.append(" END AS id_reg_validar "); 
query.append(" FROM empresa e LEFT JOIN registro_por_validar r ");
query.append(" ON e.id_empresa=r.id_registro and r.id_tipo_registro=" + Constantes.TIPO_REGISTRO.EMPRESA.getIdTipoRegistro() );
query.append(" WHERE e.id_tipo_persona=" + Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() );
query.append("   AND LOWER(apellido1) LIKE LOWER(?) ");
query.append(" UNION ");
query.append(" SELECT razon_social, contacto_empresa, fecha_acta, "); 
query.append(" fecha_alta, fecha_nacimiento, id_empresa, id_portal_empleo, ");
query.append(" id_tipo_persona, nombre, apellido1, apellido2, correo_electronico, ");
query.append(" estatus, 'EMPRESA_FRAUDULENTA' as tblEmpresa, 0 as registro_estatus  ");
query.append(" FROM empresa_fraudulenta ");
query.append(" WHERE id_tipo_persona=" + Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() );
query.append("   AND LOWER(apellido1) LIKE LOWER(?) ");
query.append(" UNION ");
query.append(" SELECT e.razon_social as razon_social, e.contacto_empresa as contacto_empresa, e.fecha_acta as fecha_acta, ");
query.append(" e.fecha_alta as fecha_alta, e.fecha_nacimiento as fecha_nacimiento, e.id_empresa as id_empresa, e.id_portal_empleo as id_portal_empleo, ");
query.append(" e.id_tipo_persona as id_tipo_persona, e.nombre as nombre, e.apellido1 as apellido1, e.apellido2 as apellido2, ");
query.append(" e.correo_electronico as correo_electronico, e.estatus as estatus, 'EMPRESA_POR_AUTORIZAR' as tblEmpresa, ");
query.append(" CASE ");
query.append(" WHEN (r.estatus IS NULL) THEN 0 ");
query.append(" ELSE r.estatus ");
query.append(" END AS registro_estatus, "); 
query.append(" CASE ");
query.append(" WHEN (r.id_reg_validar IS NULL) THEN 0 ");
query.append(" ELSE r.id_reg_validar ");
query.append(" END AS id_reg_validar "); 
query.append(" FROM empresa_por_autorizar e  LEFT JOIN registro_por_validar r ");
query.append(" ON e.id_empresa=r.id_registro and r.id_tipo_registro=" + Constantes.TIPO_REGISTRO.EMPRESA.getIdTipoRegistro() );
query.append(" WHERE e.id_tipo_persona=" + Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() );
query.append("   AND LOWER(apellido1) LIKE LOWER(?) ");		

		 * */
		
		return query.toString();
	}	
	
	private String getQueryFiltraEmpresaPersonaMoralRazon(){
		StringBuilder query = new StringBuilder();
		
		query.append("SELECT razon_social, contacto_empresa, fecha_acta, ");
		query.append(" fecha_alta, fecha_nacimiento, id_empresa, id_portal_empleo, ");
		query.append(" id_tipo_persona, nombre, apellido1, apellido2, correo_electronico, estatus, 'EMPRESA' as tblEmpresa ");
		query.append("   FROM empresa");
		query.append("   WHERE id_tipo_persona=" + Constantes.TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() + 
		" AND  LOWER(razon_social) LIKE LOWER(?) ");
		query.append(" UNION ");
		query.append("SELECT razon_social, contacto_empresa, fecha_acta, ");
		query.append(" fecha_alta, fecha_nacimiento, id_empresa, id_portal_empleo, ");
		query.append(" id_tipo_persona, nombre, apellido1, apellido2, correo_electronico, estatus, 'EMPRESA_FRAUDULENTA' as tblEmpresa ");
		query.append("   FROM empresa_fraudulenta");
		query.append("   WHERE id_tipo_persona=" + Constantes.TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() + 
		" AND  LOWER(razon_social) LIKE LOWER(?) ");
		query.append(" UNION ");
		query.append("SELECT razon_social, contacto_empresa, fecha_acta, ");
		query.append(" fecha_alta, fecha_nacimiento, id_empresa, id_portal_empleo, ");
		query.append(" id_tipo_persona, nombre, apellido1, apellido2, correo_electronico, estatus, 'EMPRESA_POR_AUTORIZAR' as tblEmpresa ");
		query.append("   FROM empresa_por_autorizar");
		query.append("   WHERE id_tipo_persona=" + Constantes.TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() + 
		" AND  LOWER(razon_social) LIKE LOWER(?) ");	
		/* NUEVA
query.append(" SELECT e.razon_social as razon_social, e.contacto_empresa as contacto_empresa, e.fecha_acta as fecha_acta, ");
query.append(" e.fecha_alta as fecha_alta, e.fecha_nacimiento as fecha_nacimiento, e.id_empresa as id_empresa, e.id_portal_empleo as id_portal_empleo, ");
query.append(" e.id_tipo_persona as id_tipo_persona, e.nombre as nombre, e.apellido1 as apellido1, e.apellido2 as apellido2, "); 
query.append(" e.correo_electronico as correo_electronico, e.estatus as estatus, 'EMPRESA' as tblEmpresa, ");
query.append(" CASE ");
query.append(" WHEN (r.estatus IS NULL) THEN 0 ");
query.append(" ELSE r.estatus ");
query.append(" END AS registro_estatus, "); 
query.append(" CASE ");
query.append(" WHEN (r.id_reg_validar IS NULL) THEN 0 ");
query.append(" ELSE r.id_reg_validar ");
query.append(" END AS id_reg_validar ");  
query.append(" FROM empresa e LEFT JOIN registro_por_validar r ");
query.append(" ON e.id_empresa=r.id_registro and r.id_tipo_registro=" + Constantes.TIPO_REGISTRO.EMPRESA.getIdTipoRegistro() );
query.append(" WHERE e.id_tipo_persona=" + Constantes.TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() );
" AND  LOWER(razon_social) LIKE LOWER(?) ");
query.append(" UNION ");
query.append(" SELECT razon_social, contacto_empresa, fecha_acta, "); 
query.append(" fecha_alta, fecha_nacimiento, id_empresa, id_portal_empleo, ");
query.append(" id_tipo_persona, nombre, apellido1, apellido2, correo_electronico, ");
query.append(" estatus, 'EMPRESA_FRAUDULENTA' as tblEmpresa, 0 as registro_estatus  ");
query.append(" FROM empresa_fraudulenta ");
query.append(" WHERE id_tipo_persona=" + Constantes.TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() );
" AND  LOWER(razon_social) LIKE LOWER(?) ");
query.append(" UNION ");
query.append(" SELECT e.razon_social as razon_social, e.contacto_empresa as contacto_empresa, e.fecha_acta as fecha_acta, ");
query.append(" e.fecha_alta as fecha_alta, e.fecha_nacimiento as fecha_nacimiento, e.id_empresa as id_empresa, e.id_portal_empleo as id_portal_empleo, ");
query.append(" e.id_tipo_persona as id_tipo_persona, e.nombre as nombre, e.apellido1 as apellido1, e.apellido2 as apellido2, ");
query.append(" e.correo_electronico as correo_electronico, e.estatus as estatus, 'EMPRESA_POR_AUTORIZAR' as tblEmpresa, ");
query.append(" CASE ");
query.append(" WHEN (r.estatus IS NULL) THEN 0 ");
query.append(" ELSE r.estatus ");
query.append(" END AS registro_estatus, "); 
query.append(" CASE ");
query.append(" WHEN (r.id_reg_validar IS NULL) THEN 0 ");
query.append(" ELSE r.id_reg_validar ");
query.append(" END AS id_reg_validar "); 
query.append(" FROM empresa_por_autorizar e  LEFT JOIN registro_por_validar r ");
query.append(" ON e.id_empresa=r.id_registro and r.id_tipo_registro=" + Constantes.TIPO_REGISTRO.EMPRESA.getIdTipoRegistro() );
query.append(" WHERE e.id_tipo_persona=" + Constantes.TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() );
" AND  LOWER(razon_social) LIKE LOWER(?) ");	
		 * */
		
		return query.toString();
	}	

	private String getQueryFiltraEmpresaPersonaMoralFecha(){
		StringBuilder query = new StringBuilder();
		
		query.append("SELECT razon_social, contacto_empresa, fecha_acta, ");
		query.append(" fecha_alta, fecha_nacimiento, id_empresa, id_portal_empleo, ");
		query.append(" id_tipo_persona, nombre, apellido1, apellido2, correo_electronico, estatus, 'EMPRESA' as tblEmpresa ");
		query.append("   FROM empresa");
		query.append("   WHERE id_tipo_persona=" + Constantes.TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() + 
		" AND fecha_acta=? ");
		query.append(" UNION ");
		query.append("SELECT razon_social, contacto_empresa, fecha_acta, ");
		query.append(" fecha_alta, fecha_nacimiento, id_empresa, id_portal_empleo, ");
		query.append(" id_tipo_persona, nombre, apellido1, apellido2, correo_electronico, estatus, 'EMPRESA_FRAUDULENTA' as tblEmpresa ");
		query.append("   FROM empresa_fraudulenta");
		query.append("   WHERE id_tipo_persona=" + Constantes.TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() + 
		" AND fecha_acta=? ");
		query.append(" UNION ");
		query.append("SELECT razon_social, contacto_empresa, fecha_acta, ");
		query.append(" fecha_alta, fecha_nacimiento, id_empresa, id_portal_empleo, ");
		query.append(" id_tipo_persona, nombre, apellido1, apellido2, correo_electronico, estatus, 'EMPRESA_POR_AUTORIZAR' as tblEmpresa ");
		query.append("   FROM empresa_por_autorizar");
		query.append("   WHERE id_tipo_persona=" + Constantes.TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() + 
		" AND fecha_acta=? ");	
		/* NUEVA
query.append(" SELECT e.razon_social as razon_social, e.contacto_empresa as contacto_empresa, e.fecha_acta as fecha_acta, ");
query.append(" e.fecha_alta as fecha_alta, e.fecha_nacimiento as fecha_nacimiento, e.id_empresa as id_empresa, e.id_portal_empleo as id_portal_empleo, ");
query.append(" e.id_tipo_persona as id_tipo_persona, e.nombre as nombre, e.apellido1 as apellido1, e.apellido2 as apellido2, "); 
query.append(" e.correo_electronico as correo_electronico, e.estatus as estatus, 'EMPRESA' as tblEmpresa, ");
query.append(" CASE ");
query.append(" WHEN (r.estatus IS NULL) THEN 0 ");
query.append(" ELSE r.estatus ");
query.append(" END AS registro_estatus, "); 
query.append(" CASE ");
query.append(" WHEN (r.id_reg_validar IS NULL) THEN 0 ");
query.append(" ELSE r.id_reg_validar ");
query.append(" END AS id_reg_validar "); 
query.append(" FROM empresa e LEFT JOIN registro_por_validar r ");
query.append(" ON e.id_empresa=r.id_registro and r.id_tipo_registro=" + Constantes.TIPO_REGISTRO.EMPRESA.getIdTipoRegistro() );
query.append(" WHERE e.id_tipo_persona=" + Constantes.TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() + " AND fecha_acta=? ");
query.append(" UNION ");
query.append(" SELECT razon_social, contacto_empresa, fecha_acta, "); 
query.append(" fecha_alta, fecha_nacimiento, id_empresa, id_portal_empleo, ");
query.append(" id_tipo_persona, nombre, apellido1, apellido2, correo_electronico, ");
query.append(" estatus, 'EMPRESA_FRAUDULENTA' as tblEmpresa, 0 as registro_estatus  ");
query.append(" FROM empresa_fraudulenta ");
query.append(" WHERE id_tipo_persona=" + Constantes.TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() + " AND fecha_acta=? ");
query.append(" UNION ");
query.append(" SELECT e.razon_social as razon_social, e.contacto_empresa as contacto_empresa, e.fecha_acta as fecha_acta, ");
query.append(" e.fecha_alta as fecha_alta, e.fecha_nacimiento as fecha_nacimiento, e.id_empresa as id_empresa, e.id_portal_empleo as id_portal_empleo, ");
query.append(" e.id_tipo_persona as id_tipo_persona, e.nombre as nombre, e.apellido1 as apellido1, e.apellido2 as apellido2, ");
query.append(" e.correo_electronico as correo_electronico, e.estatus as estatus, 'EMPRESA_POR_AUTORIZAR' as tblEmpresa, ");
query.append(" CASE ");
query.append(" WHEN (r.estatus IS NULL) THEN 0 ");
query.append(" ELSE r.estatus ");
query.append(" END AS registro_estatus, "); 
query.append(" CASE ");
query.append(" WHEN (r.id_reg_validar IS NULL) THEN 0 ");
query.append(" ELSE r.id_reg_validar ");
query.append(" END AS id_reg_validar "); 
query.append(" FROM empresa_por_autorizar e  LEFT JOIN registro_por_validar r ");
query.append(" ON e.id_empresa=r.id_registro and r.id_tipo_registro=" + Constantes.TIPO_REGISTRO.EMPRESA.getIdTipoRegistro() );
query.append(" WHERE e.id_tipo_persona=" + Constantes.TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() + " AND fecha_acta=? ");	
		 * */
			
		return query.toString();
	}	
	
	private String getQueryFiltraEmpresaPersonaMoralFechaRazon(){
		StringBuilder query = new StringBuilder();
		
		query.append("SELECT razon_social, contacto_empresa, fecha_acta, ");
		query.append(" fecha_alta, fecha_nacimiento, id_empresa, id_portal_empleo, ");
		query.append(" id_tipo_persona, nombre, apellido1, apellido2, correo_electronico, estatus, 'EMPRESA' as tblEmpresa ");
		query.append("   FROM empresa");
		query.append("   WHERE id_tipo_persona=" + Constantes.TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() + 
		" AND (LOWER(razon_social) LIKE LOWER(?) ");
		query.append("   AND fecha_acta=? ) ");
		query.append(" UNION ");
		query.append("SELECT razon_social, contacto_empresa, fecha_acta, ");
		query.append(" fecha_alta, fecha_nacimiento, id_empresa, id_portal_empleo, ");
		query.append(" id_tipo_persona, nombre, apellido1, apellido2, correo_electronico, estatus, 'EMPRESA_FRAUDULENTA' as tblEmpresa ");
		query.append("   FROM empresa_fraudulenta");
		query.append("   WHERE id_tipo_persona=" + Constantes.TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() + 
		" AND (LOWER(razon_social) LIKE LOWER(?) ");
		query.append("   AND fecha_acta=? ) ");
		query.append(" UNION ");
		query.append("SELECT razon_social, contacto_empresa, fecha_acta, ");
		query.append(" fecha_alta, fecha_nacimiento, id_empresa, id_portal_empleo, ");
		query.append(" id_tipo_persona, nombre, apellido1, apellido2, correo_electronico, estatus, 'EMPRESA_POR_AUTORIZAR' as tblEmpresa ");
		query.append("   FROM empresa_por_autorizar");
		query.append("   WHERE id_tipo_persona=" + Constantes.TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() + 
		" AND (LOWER(razon_social) LIKE LOWER(?) ");
		query.append("   AND fecha_acta=? ) ");		
		/* NUEVA
query.append(" SELECT e.razon_social as razon_social, e.contacto_empresa as contacto_empresa, e.fecha_acta as fecha_acta, ");
query.append(" e.fecha_alta as fecha_alta, e.fecha_nacimiento as fecha_nacimiento, e.id_empresa as id_empresa, e.id_portal_empleo as id_portal_empleo, ");
query.append(" e.id_tipo_persona as id_tipo_persona, e.nombre as nombre, e.apellido1 as apellido1, e.apellido2 as apellido2, "); 
query.append(" e.correo_electronico as correo_electronico, e.estatus as estatus, 'EMPRESA' as tblEmpresa, ");
query.append(" CASE ");
query.append(" WHEN (r.estatus IS NULL) THEN 0 ");
query.append(" ELSE r.estatus ");
query.append(" END AS registro_estatus, "); 
query.append(" CASE ");
query.append(" WHEN (r.id_reg_validar IS NULL) THEN 0 ");
query.append(" ELSE r.id_reg_validar ");
query.append(" END AS id_reg_validar "); 
query.append(" FROM empresa e LEFT JOIN registro_por_validar r ");
query.append(" ON e.id_empresa=r.id_registro and r.id_tipo_registro=" + Constantes.TIPO_REGISTRO.EMPRESA.getIdTipoRegistro() );
query.append(" WHERE e.id_tipo_persona=" + Constantes.TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() + " AND (LOWER(razon_social) LIKE LOWER(?) ");
query.append("   AND fecha_acta=? ) ");
query.append(" UNION ");
query.append(" SELECT razon_social, contacto_empresa, fecha_acta, "); 
query.append(" fecha_alta, fecha_nacimiento, id_empresa, id_portal_empleo, ");
query.append(" id_tipo_persona, nombre, apellido1, apellido2, correo_electronico, ");
query.append(" estatus, 'EMPRESA_FRAUDULENTA' as tblEmpresa, 0 as registro_estatus  ");
query.append(" FROM empresa_fraudulenta ");
query.append(" WHERE id_tipo_persona=" + Constantes.TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() + " AND (LOWER(razon_social) LIKE LOWER(?) ");
query.append("   AND fecha_acta=? ) ");
query.append(" UNION ");
SELECT e.razon_social as razon_social, e.contacto_empresa as contacto_empresa, e.fecha_acta as fecha_acta, ");
query.append(" e.fecha_alta as fecha_alta, e.fecha_nacimiento as fecha_nacimiento, e.id_empresa as id_empresa, e.id_portal_empleo as id_portal_empleo, ");
query.append(" e.id_tipo_persona as id_tipo_persona, e.nombre as nombre, e.apellido1 as apellido1, e.apellido2 as apellido2, ");
query.append(" e.correo_electronico as correo_electronico, e.estatus as estatus, 'EMPRESA_POR_AUTORIZAR' as tblEmpresa, ");
query.append(" CASE ");
query.append(" WHEN (r.estatus IS NULL) THEN 0 ");
query.append(" ELSE r.estatus ");
query.append(" END AS registro_estatus, "); 
query.append(" CASE ");
query.append(" WHEN (r.id_reg_validar IS NULL) THEN 0 ");
query.append(" ELSE r.id_reg_validar ");
query.append(" END AS id_reg_validar "); 
query.append(" FROM empresa_por_autorizar e  LEFT JOIN registro_por_validar r ");
query.append(" ON e.id_empresa=r.id_registro and r.id_tipo_registro=" + Constantes.TIPO_REGISTRO.EMPRESA.getIdTipoRegistro() );
query.append(" WHERE e.id_tipo_persona=" + Constantes.TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() + " AND (LOWER(razon_social) LIKE LOWER(?) ");
query.append("   AND fecha_acta=? ) ");				
		 * */
		
		return query.toString();
	}		
	
	private String getQueryFiltraPorEmpresaEstatus(){
		StringBuilder query = new StringBuilder();
		query.append("SELECT ID_OFERTA_EMPLEO FROM OFERTA_EMPLEO ");
		query.append(" WHERE ID_EMPRESA = ? ");
		query.append("   AND ESTATUS = ? ");
		return query.toString();
	}

	protected String getQuery() {
		
		String query = null;

		switch(this.QUERY_EMPRESA){
			/*case QUERY_FILTRA_EMPRESA_PERSONA_FISICA_NOMBRE_APELLIDO : query = getQueryFiltraEmpresaPersonaFisicaNombreApellido(); break;
			case QUERY_FILTRA_EMPRESA_PERSONA_FISICA_NOMBRE : query = getQueryFiltraEmpresaPersonaFisicaNombre(); break;
			case QUERY_FILTRA_EMPRESA_PERSONA_FISICA_APELLIDO : query = getQueryFiltraEmpresaPersonaFisicaApellido(); break;			
			case QUERY_FILTRA_EMPRESA_PERSONA_MORAL_RAZON : query = getQueryFiltraEmpresaPersonaMoralRazon(); break;
			case QUERY_FILTRA_EMPRESA_PERSONA_MORAL_FECHA : query = getQueryFiltraEmpresaPersonaMoralFecha(); break;
			case QUERY_FILTRA_EMPRESA_PERSONA_MORAL_FECHA_RAZON : query = getQueryFiltraEmpresaPersonaMoralFechaRazon(); break;*/
			case QUERY_ACTUALIZA_ESTATUS_OFERTAS_EMPRESA : query = getQueryActualizaEstatusOfertasEmpresa(); break;
			//case QUERY_FILTRA_OFERTA_EMPRESA_ESTATUS : query = getQueryFiltraPorEmpresaEstatus(); break;
			case QUERY_ACTUALIZA_ESTATUS_INACTIVA_OFERTAS_EMPRESA : query = getQueryActualizaEstatusInativoOfertasEmpresa(); break;
			//case QUERY : query = getQueryEmpresas(); break;
		}

		return query;		
		
	}	
	
	private EmpresaVO createEmpresaVO(CachedRowSet rowSet) throws SQLException {
		EmpresaVO vo = new EmpresaVO();
		vo.setApellido1(rowSet.getString("APELLIDO1"));
		vo.setApellido2(rowSet.getString("APELLIDO2"));
		vo.setContactoEmpresa(rowSet.getString("CONTACTO_EMPRESA"));
		vo.setFechaActa(rowSet.getDate("FECHA_ACTA"));
		vo.setFechaAlta(rowSet.getDate("FECHA_ALTA"));
		vo.setFechaNacimiento(rowSet.getDate("FECHA_NACIMIENTO"));
		vo.setIdEmpresa(rowSet.getLong("ID_EMPRESA"));
		vo.setIdPortalEmpleo(rowSet.getString("ID_PORTAL_EMPLEO"));
		vo.setIdTipoPersona(rowSet.getInt("ID_TIPO_PERSONA"));
		vo.setNombre(rowSet.getString("NOMBRE"));
		vo.setRazonSocial(rowSet.getString("RAZON_SOCIAL"));
		vo.setCorreoElectronico(rowSet.getString("CORREO_ELECTRONICO"));
		vo.setEstatus(rowSet.getInt("ESTATUS"));
		vo.setTblEmpresa(rowSet.getString("TBLEMPRESA"));
		return vo;
	}
	
}



