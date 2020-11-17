package mx.gob.stps.portal.core.oferta.busqueda.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Timer;

import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaRecienteVO;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;

/**
 * @author Mario Alberto Vázquez Flores
 * @since 22/03/2011
 **/
@Remote
public interface OfertasRecientesAppServiceRemote {

	public List<OfertaRecienteVO> obtenerOfertasRecientes(int numRegistros)
			throws SQLException;	
	
	public List<OfertaPorCanalVO> obtenerOfertasRecientesTodas(int numRegistros)
			throws SQLException;

	public List<OfertaRecienteVO> obtenerOfertasDestacadas(int numRegistros)
			throws SQLException;
	
	public List<OfertaPorCanalVO> obtenerOfertasDestacadasTodas(int numRegistros)
			throws SQLException;

	public List<OfertaRecienteVO> obtenerOfertasGendarmeria(int numRegistros)
			throws SQLException;
	
	public List<OfertaPorCanalVO> obtenerOfertasGendarmeriaTodas(int numRegistros)
			throws SQLException;	
	
	public void initTimerOfertasRecientesDestacadas()
			throws SQLException;
	
	public List<OfertaRecienteVO> getListaOfertasRecientes();
	
	public List<OfertaPorCanalVO> getListaOfertasRecientesTodas();

	public List<OfertaRecienteVO> getListaOfertasDestacadas();
	
	public List<OfertaPorCanalVO> getListaOfertasDestacadasTodas();

	public List<OfertaRecienteVO> getListaOfertasCanada();

	public List<OfertaPorCanalVO> getListaOfertasCanadaTodas();

	public List<OfertaRecienteVO> getListaOfertasGendarmeria();
	
	public List<OfertaPorCanalVO> getListaOfertasGendarmeriaTodas();
}