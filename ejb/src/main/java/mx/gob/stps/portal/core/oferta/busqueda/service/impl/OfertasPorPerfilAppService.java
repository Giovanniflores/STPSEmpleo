package mx.gob.stps.portal.core.oferta.busqueda.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import mx.gob.stps.portal.core.oferta.busqueda.dao.OfertasPorEmpresaDAO;
import mx.gob.stps.portal.core.oferta.busqueda.dao.OfertasPorIDDAO;
import mx.gob.stps.portal.core.oferta.busqueda.dao.OfertasPorPerfilEmpresaDAO;
import mx.gob.stps.portal.core.oferta.busqueda.service.OfertasPorPerfilAppServiceRemote;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorPerfilVO;
import mx.gob.stps.portal.core.oferta.vo.UbicacionCanadaVO;
import mx.gob.stps.portal.core.persistencia.facade.OfertaUbicacionFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.PerfilTipoFacadeLocal;
import mx.gob.stps.portal.core.search.service.impl.OfertasPorPerfilAppServiceLocal;
import mx.gob.stps.portal.persistencia.vo.PerfilTipoVO;

/**
 * @author Mario Alberto Vázquez Flores
 * @since 18/03/2011
 **/
@Stateless(name = "OfertasPorPerfilAppService", mappedName = "OfertasPorPerfilAppService")
public class OfertasPorPerfilAppService implements OfertasPorPerfilAppServiceRemote, OfertasPorPerfilAppServiceLocal {

	@EJB
	private OfertaUbicacionFacadeLocal ofertaUbicacionFacade;
	
	@EJB
	private PerfilTipoFacadeLocal perfilTipoFacadeLocal;

	@Override
	public List<OfertaPorPerfilVO> obtenerOfertasPorPerfilEmpresa(long idCandidato, long idEmpresa) throws SQLException {

		if (idCandidato <= 0) throw new IllegalArgumentException("Identificador de candidato invalido");
		if (idEmpresa <= 0) throw new IllegalArgumentException("Identificador de empresa invalido");

		OfertasPorPerfilEmpresaDAO dao = new OfertasPorPerfilEmpresaDAO();
		return dao.obtenerOfertasPorPerfilEmpresa(idCandidato, idEmpresa);
	}

	@Override
	public List<OfertaPorPerfilVO> obtenerOfertasPorEmpresa(long idEmpresa) throws SQLException {
		if (idEmpresa <= 0) throw new IllegalArgumentException("Identificador de empresa invalido");

		OfertasPorEmpresaDAO dao = new OfertasPorEmpresaDAO();
		return dao.obtenerOfertasPorEmpresa(idEmpresa);
	}

	@Override
	public OfertaPorPerfilVO obtenerOfertaPorId(long idOfertaEmpleo) throws SQLException {
		if (idOfertaEmpleo <= 0) throw new IllegalArgumentException("Identificador de oferta invalido");
		OfertasPorIDDAO dao = new OfertasPorIDDAO();
		OfertaPorPerfilVO vo = dao.obtenerOfertasPorID(idOfertaEmpleo);
		if (null !=  vo && vo.getFuente()==3) {
			UbicacionCanadaVO ubicacionVO = ofertaUbicacionFacade.getUbicacionOfertaCanada(idOfertaEmpleo);
			if (null != ubicacionVO)
				vo.setUbicacion(ubicacionVO.getProvincia()+","+ubicacionVO.getCiudad());
		}
		return vo;
	}
	
	@Override
	public List<PerfilTipoVO> perfilTipoList(long idOcupacion) throws SQLException {
		return perfilTipoFacadeLocal.perfilTipoList(idOcupacion);
	}

	@Override
	public PerfilTipoVO find(long idPerfilTipo) throws SQLException {
		return perfilTipoFacadeLocal.find(idPerfilTipo);
	}	
}
