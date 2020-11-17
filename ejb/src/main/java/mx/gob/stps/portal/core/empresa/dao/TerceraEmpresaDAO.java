package mx.gob.stps.portal.core.empresa.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import mx.gob.stps.portal.core.empresa.vo.TerceraEmpresaVO;
import mx.gob.stps.portal.core.infra.data.TemplateDAO;

//TODO ELIMINAR CLASE YA NO SE UTILIZA
public class TerceraEmpresaDAO extends TemplateDAO{

	private TerceraEmpresaDAO(){
	}

	private TerceraEmpresaDAO(Connection globalConnection){
		super(globalConnection);
	}
	
	private final int QUERY_TERCERA_EMPRESA_BY_ID_PADRE = 1;
	private final int QUERY_CONTAR_OFERTAS_EMPLEO = 2;
	private int QUERY_TERCERA_EMPRESA = 0;

	
	//
	public int countOffersByIdTerceraEmpresa(long idTerceraEmpresa, int estatus) throws Exception {
		int intActiveOffers = 0;
		this.QUERY_TERCERA_EMPRESA = this.QUERY_CONTAR_OFERTAS_EMPLEO;
		try{
			String[] strParameters = new String[2];
			strParameters[0] = String.valueOf(idTerceraEmpresa);
			strParameters[1] = String.valueOf(estatus);
			
			CachedRowSet cachedRowSet = executeQuery(strParameters);
			if(cachedRowSet.next()) {
				intActiveOffers = cachedRowSet.getInt("cuenta");				
			}
			System.out.println("--------TerceraEmpresaDAO.countOffersByIdTerceraEmpresa:" + intActiveOffers);
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
	/*public List<TerceraEmpresaVO> findAllByIdEmpresa(long idEmpresaPadre) throws Exception {
		this.QUERY_TERCERA_EMPRESA = this.QUERY_TERCERA_EMPRESA_BY_ID_PADRE;
		List<TerceraEmpresaVO> lstTerceras = new ArrayList<TerceraEmpresaVO>();
						
		try{
				String[] strParameters = new String[1];
				strParameters[0] = String.valueOf(idEmpresaPadre);

				CachedRowSet cachedRowSet = executeQuery(strParameters);

				while (cachedRowSet.next()) {
					TerceraEmpresaVO vo = new TerceraEmpresaVO();
					vo.setApellido1(cachedRowSet.getString("apellido1"));
					vo.setApellido2(cachedRowSet.getString("apellido2"));
					vo.setContactoEmpresa(cachedRowSet.getString("contacto_empresa"));
					vo.setCorreoElectronico(cachedRowSet.getString("correo_electronico"));
					vo.setDescripcion(cachedRowSet.getString("descripcion"));
					//vo.setDomicilio(domicilio)
					vo.setEstatus(cachedRowSet.getInt("estatus"));
					vo.setFechaAlta(cachedRowSet.getDate("fecha_alta"));
					vo.setFechaUltimaActualizacion(cachedRowSet.getDate("fecha_ultima_actualizacion"));
					vo.setIdActividadEconomica(cachedRowSet.getInt("id_actividad_economica"));
					vo.setIdEmpresa(cachedRowSet.getLong("id_empresa"));
					vo.setIdTerceraEmpresa(cachedRowSet.getLong("id_tercera_empresa"));
					vo.setIdTipoPersona(cachedRowSet.getLong("id_tipo_persona"));
					vo.setNombre(cachedRowSet.getString("nombre"));
					vo.setNumeroEmpleados(cachedRowSet.getInt("numero_empleados"));
					vo.setRazonSocial(cachedRowSet.getString("razon_social"));
					vo.setRfc(cachedRowSet.getString("rfc"));
					//vo.setTelefonos(telefonos)
					//System.out.println("------DAO.findAllByIdEmpresa vo:" + vo.toString());
					lstTerceras.add(vo);
					
				}	
				//System.out.println("------DAO.findAllByIdEmpresa size:" + lstTerceras.size());
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new SQLException(re);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return lstTerceras;
	}*/


	protected String getQuery() {
		
		String query = null;

		switch(QUERY_TERCERA_EMPRESA){
			case QUERY_TERCERA_EMPRESA_BY_ID_PADRE : query = getQueryTerceraEmpresaByIdEmpresaPadre(); break;
			case QUERY_CONTAR_OFERTAS_EMPLEO : query = getQueryContarOfertasEmpleo(); break;
		}

		return query;		
		
	}

	private String getQueryTerceraEmpresaByIdEmpresaPadre(){
		StringBuilder query = new StringBuilder();		
		query.append("SELECT * ");
		query.append("	 FROM tercera_empresa");
		query.append("   WHERE id_empresa =? ");
		query.append("   ORDER by id_tercera_empresa ");		
		return query.toString();		
	}
	
	
	private String getQueryContarOfertasEmpleo(){
		StringBuilder query = new StringBuilder();		

		query.append("SELECT COUNT(id_oferta_empleo) as cuenta ");
		query.append("  FROM oferta_empleo ");		
		query.append(" WHERE id_tercera_empresa = ? ");
		query.append("  AND estatus = ? ");
		
		return query.toString();
	}	
	
}
