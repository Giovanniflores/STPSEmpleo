package mx.gob.stps.portal.core.autorizacion.dao;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import mx.gob.stps.portal.core.autorizacion.vo.TipoEmpresaVO;
import mx.gob.stps.portal.core.infra.data.TemplateDAO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_EMPRESA;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;

public final class TipoEmpresaDAO extends TemplateDAO {

	private TipoEmpresaDAO(){}
	
	public static TipoEmpresaDAO getInstance(){
		return new TipoEmpresaDAO();
	}
	
	public TipoEmpresaVO consultaTipoEmpresa(long idRegValidar) throws SQLException {
		
		TipoEmpresaVO tipoEmpresa = null;

		Object[] params = {TIPO_EMPRESA.EMPRESA_POR_AUTORIZAR.getTipoEmpresa(), 
				           idRegValidar,
				           TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario(),
				           TIPO_EMPRESA.EMPRESA.getTipoEmpresa(),
				           idRegValidar,
				           TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario()};

		CachedRowSet rowSet = executeQuery(params);

		// TODO verifica accion en caso que regrese mas de un registro
		// SOLO DEBERIA DE REGRESAR UNO
		if (rowSet.next()){
			
			tipoEmpresa = new TipoEmpresaVO();
			tipoEmpresa.setIdEmpresa(rowSet.getLong("ID_EMPRESA"));
			tipoEmpresa.setEstatus(rowSet.getInt("ESTATUS"));
			
			if (TIPO_EMPRESA.EMPRESA.getTipoEmpresa() == rowSet.getInt("TIPO_EMPRESA")){
			
				tipoEmpresa.setTipoEmpresa(TIPO_EMPRESA.EMPRESA);
			
			} else if (TIPO_EMPRESA.EMPRESA_POR_AUTORIZAR.getTipoEmpresa() == rowSet.getInt("TIPO_EMPRESA")){
				
				tipoEmpresa.setTipoEmpresa(TIPO_EMPRESA.EMPRESA_POR_AUTORIZAR);
			}
		}
		
		return tipoEmpresa;
	}
	
	@Override
	protected String getQuery() {
		StringBuilder query = new StringBuilder();

		query.append("SELECT EMP_AUT.ID_EMPRESA, EMP_AUT.ESTATUS, ? AS TIPO_EMPRESA ");
		query.append("  FROM REGISTRO_POR_VALIDAR REG, ");
		query.append("       EMPRESA_POR_AUTORIZAR EMP_AUT ");
		query.append(" WHERE ID_REG_VALIDAR = ? ");
		query.append("   AND ID_TIPO_PROPIETARIO = ? ");
		query.append("   AND EMP_AUT.ID_EMPRESA = REG.ID_REGISTRO ");

		query.append("UNION ");

		query.append("SELECT EMP.ID_EMPRESA, EMP.ESTATUS, ? AS TIPO_EMPRESA ");
		query.append("  FROM REGISTRO_POR_VALIDAR REG, ");
		query.append("       EMPRESA EMP ");
		query.append(" WHERE ID_REG_VALIDAR = ? ");
		query.append("   AND ID_TIPO_PROPIETARIO = ? ");
		query.append("   AND EMP.ID_EMPRESA = REG.ID_REGISTRO ");

		return query.toString();
	}
	
}
