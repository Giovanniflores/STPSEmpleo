package mx.gob.stps.portal.core.empresa.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import mx.gob.stps.portal.core.infra.data.TemplateDAO;
import mx.gob.stps.portal.core.infra.utils.Constantes;



public class EmpresaPorAutorizarDAO extends TemplateDAO{

	public static int PORTAL_ID_SIZE = 17;
	
	public EmpresaPorAutorizarDAO(){}

	public EmpresaPorAutorizarDAO(Connection globalConnection){
		super(globalConnection);
	}
	
	/**
	 * Method 'obtenerDigitoVerificador'
	 * Regresa un entero que se usa para evitar colisiones en la generación del idPortalEmpleo
	 * 
	 * @param idPortalEmpleo
	 * @return int
	 */		
	public int obtenerDigitoVerificador(String idPortalEmpleo, long idTipoPersona) throws Exception {
		int total = 0;
		if (idPortalEmpleo==null) throw new IllegalArgumentException("Identificador del portal inválido"); 
				
		try{
				String[] strParameters = new String[3];
				strParameters[0] = idPortalEmpleo + "%";
				strParameters[1] = idPortalEmpleo + "%";
				strParameters[2] = idPortalEmpleo + "%";

				CachedRowSet cachedRowSet = executeQuery(strParameters);

				if (cachedRowSet.next()) {
					String maxId = cachedRowSet.getString("maxIdPortalEmpleo");
					System.out.println("---maxId:" + maxId);					
					if(maxId!=null){
						//System.out.println("---maxId.length():" + maxId.length());
						int idLen = idPortalEmpleo.length();
						//System.out.println("---idLen:" + idLen);
						String strLstChar = "";	
						strLstChar = maxId.substring(idLen);
						//System.out.println("---strLstChar:" + strLstChar);							
						total = Integer.parseInt(strLstChar);						
					}
					total++;
					//System.out.println("---total++:" + total);
				}
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new SQLException(re);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return total;
	}



	protected String getQuery() {
		StringBuilder query = new StringBuilder();
		
		query.append("SELECT MAX(maxId) as maxIdPortalEmpleo from (");
		query.append("   SELECT MAX(id_portal_empleo) as maxId");
		query.append("	 FROM empresa_por_autorizar");
		query.append("   WHERE id_portal_empleo LIKE ? ");
		query.append(" 	 UNION");
		query.append("   SELECT MAX(id_portal_empleo) as maxId");
		query.append("	 FROM empresa");
		query.append("   WHERE id_portal_empleo LIKE ? ");			
		query.append(" 	 UNION");
		query.append("   SELECT MAX(id_portal_empleo) as maxId");
		query.append("	 FROM empresa_fraudulenta");
		query.append("   WHERE id_portal_empleo LIKE ? ");						
		query.append(")");
		
		return query.toString();
	}		
	
}
