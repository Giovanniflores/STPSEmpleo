package mx.gob.stps.portal.web.oferta.search.delegate;

import java.sql.SQLException;
import java.util.List;

import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaRecienteVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;

/**
 * @author Mario Alberto Vázquez Flores
 * @since 22/03/2011
 **/
public interface OfertasRecientesBusDelegate {

	public List<OfertaRecienteVO> obtenerOfertasRecientes(int numRegistros)
			throws SQLException, ServiceLocatorException;
	
	public List<OfertaPorCanalVO> obtenerOfertasRecientesTodas(int numRegistros)
			throws SQLException, ServiceLocatorException;
	
	public List<OfertaRecienteVO> obtenerOfertasDestacadas(int numRegistros)
			throws SQLException, ServiceLocatorException;
	
	public List<OfertaPorCanalVO> obtenerOfertasDestacadasTodas(int numRegistros)
			throws SQLException, ServiceLocatorException;
	
	public void initTimerOfertasRecientesDestacadas()
			throws SQLException, ServiceLocatorException;

	public List<OfertaRecienteVO> obtenerOfertasCanada() throws ServiceLocatorException;

	public List<OfertaPorCanalVO> obtenerOfertasCanadaTodas(
			int numeroRegistrosTodas) throws ServiceLocatorException;
	
	public List<OfertaRecienteVO> obtenerOfertasGendarmeria(int numRegistros)
			throws SQLException, ServiceLocatorException;
	
	public List<OfertaPorCanalVO> obtenerOfertasGendarmeriaTodas(int numeroRegistrosTodas) 
			throws SQLException, ServiceLocatorException;
}
