package mx.gob.stps.portal.core.oferta.busqueda.service;

import java.util.List;
import javax.ejb.Remote;
import java.sql.SQLException;

import mx.gob.stps.portal.persistencia.vo.PerfilTipoVO;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorPerfilVO;

/**
 * @author Mario Alberto Vázquez Flores
 * @since 18/03/2011
 **/
@Remote
public interface OfertasPorPerfilAppServiceRemote {

	public List<OfertaPorPerfilVO> obtenerOfertasPorPerfilEmpresa(long idCandidato, long idEmpresa) throws SQLException;

	public List<OfertaPorPerfilVO> obtenerOfertasPorEmpresa(long idEmpresa) throws SQLException;

	public OfertaPorPerfilVO obtenerOfertaPorId(long idOfertaEmpleo) throws SQLException;
	
	public List<PerfilTipoVO> perfilTipoList(long idOcupacion) throws SQLException;
	
	public PerfilTipoVO find(long idPerfilTipo) throws SQLException;
}