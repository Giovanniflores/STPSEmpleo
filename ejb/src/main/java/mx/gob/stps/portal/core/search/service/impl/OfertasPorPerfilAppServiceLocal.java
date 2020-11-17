package mx.gob.stps.portal.core.search.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorPerfilVO;

@Local
public interface OfertasPorPerfilAppServiceLocal {

	public List<OfertaPorPerfilVO> obtenerOfertasPorPerfilEmpresa(long idCandidato, long idEmpresa) throws SQLException;

	public List<OfertaPorPerfilVO> obtenerOfertasPorEmpresa(long idEmpresa) throws SQLException;

	public OfertaPorPerfilVO obtenerOfertaPorId(long idOfertaEmpleo) throws SQLException;	
	
}
