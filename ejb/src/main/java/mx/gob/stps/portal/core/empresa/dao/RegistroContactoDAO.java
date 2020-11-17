package mx.gob.stps.portal.core.empresa.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import mx.gob.stps.portal.core.empresa.vo.RegistroContactoVO;
import mx.gob.stps.portal.core.infra.data.TemplateDAO;

// TODO ELIMINAR CLASE YA NO SE UTILIZA
public class RegistroContactoDAO extends TemplateDAO{

	private RegistroContactoDAO(){}

	private RegistroContactoDAO(Connection globalConnection){
		super(globalConnection);
	}
	
	//private final int QUERY_CONTACTO_BY_ID_PADRE = 1;
	private final int QUERY_CONTAR_OFERTAS_EMPLEO = 2;
	private int QUERY_CONTACTO = 0;

	public int countOffersByIdContacto(long idContacto, int estatus) throws Exception {
		int intActiveOffers = 0;
		this.QUERY_CONTACTO = this.QUERY_CONTAR_OFERTAS_EMPLEO;
		try{
			String[] strParameters = new String[2];
			strParameters[0] = String.valueOf(idContacto);
			strParameters[1] = String.valueOf(estatus);
			
			CachedRowSet cachedRowSet = executeQuery(strParameters);
			if(cachedRowSet.next()) {
				intActiveOffers = cachedRowSet.getInt("cuenta");				
			}
			System.out.println("--------RegistroContactoDAO.countOffersByIdContacto:" + intActiveOffers);
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new SQLException(re);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}		
		return intActiveOffers;
	}	
	

	/**
	 * Method 'findAllByIdEmpresa'
	 * Regresa un listado de identificadores de terceras empresas relacionadas a 
	 * una empresa padre
	 * 
	 * @param idPortalEmpleo
	 * @return int
	 */			
	/*private List<RegistroContactoVO> findAllByIdEmpresa(long idEmpresaPadre) throws Exception {
		//this.QUERY_CONTACTO = this.QUERY_CONTACTO_BY_ID_PADRE;
		List<RegistroContactoVO> lstContacto = new ArrayList<RegistroContactoVO>();
						
		try{
				String[] strParameters = new String[1];
				strParameters[0] = String.valueOf(idEmpresaPadre);

				CachedRowSet cachedRowSet = executeQuery(strParameters);

				while (cachedRowSet.next()) {
					RegistroContactoVO vo = new RegistroContactoVO();
					vo.setIdContacto(cachedRowSet.getInt("id_contacto"));
					vo.setIdEmpresa(cachedRowSet.getLong("id_empresa"));
					vo.setNombreContacto(cachedRowSet.getString("nombre_contacto"));
					vo.setCargo(cachedRowSet.getString("cargo"));
					vo.setEstatus(cachedRowSet.getInt("estatus"));
					vo.setFechaAlta(cachedRowSet.getDate("fecha_alta"));
					vo.setFechaModificacion(cachedRowSet.getDate("fecha_modificacion"));
					vo.setCorreoElectronico(cachedRowSet.getString("correo_electronico"));
					
					lstContacto.add(vo);
				}
				//System.out.println("------DAO.findAllByIdEmpresa size:" + lstContacto.size());
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new SQLException(re);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return lstContacto;
	}*/


	protected String getQuery() {
		
		String query = null;

		switch(QUERY_CONTACTO){
			//case QUERY_CONTACTO_BY_ID_PADRE : query = getQueryContactoByIdEmpresaPadre(); break;
			case QUERY_CONTAR_OFERTAS_EMPLEO : query = this.getQueryContarOfertasEmpleo(); break;
		}

		return query;		
		
	}

	/*private String getQueryContactoByIdEmpresaPadre(){
		StringBuilder query = new StringBuilder();		
		query.append("SELECT * ");
		query.append("	 FROM contacto");
		query.append("   WHERE id_empresa =? ");
		query.append("   ORDER by nombre_contacto ");		
		return query.toString();		
	}*/
	
	private String getQueryContarOfertasEmpleo(){
		StringBuilder query = new StringBuilder();		

		query.append("SELECT COUNT(id_oferta_empleo) as cuenta ");
		query.append("  FROM oferta_empleo ");		
		query.append(" WHERE id_contacto = ? ");
		query.append("  AND estatus = ? ");
		
		return query.toString();
	}		

	
}