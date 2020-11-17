package mx.gob.stps.portal.web.oferta.search.delegate;

import java.sql.SQLException;
import java.util.List;

import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorPerfilVO;
import mx.gob.stps.portal.persistencia.vo.PerfilTipoVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;

/**
 * @author Mario Alberto Vázquez Flores
 * @since 18/03/2011
 **/
public interface OfertasPorPerfilBusDelegate {

	public List<OfertaPorPerfilVO> obtenerOfertasPorPerfilEmpresa( long idCandidato, long idEmpresa) throws SQLException, ServiceLocatorException;

	public List<OfertaPorPerfilVO> obtenerOfertasPorEmpresa(long idEmpresa) throws SQLException, ServiceLocatorException;
	
	public List<PerfilTipoVO> perfilTipoList(long idOcupacion) throws SQLException, ServiceLocatorException;
	
	public PerfilTipoVO find(long idPerfilTipo) throws SQLException, ServiceLocatorException;
}