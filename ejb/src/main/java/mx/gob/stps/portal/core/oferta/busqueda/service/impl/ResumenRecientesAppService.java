package mx.gob.stps.portal.core.oferta.busqueda.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import mx.gob.stps.portal.core.oferta.busqueda.service.ResumenRecientesAppServiceRemote;
import mx.gob.stps.portal.core.oferta.dao.RecientesDAO;
import mx.gob.stps.portal.core.oferta.vo.MiOfertaRecienteVO;
import mx.gob.stps.portal.core.oferta.vo.PostulacionRecienteVO;
import mx.gob.stps.portal.core.persistencia.facade.OfertaCandidatoFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.OfertaEmpleoFacadeLocal;

@Stateless(name = "ResumenRecientesAppService", mappedName = "ResumenRecientesAppService")
public class ResumenRecientesAppService implements ResumenRecientesAppServiceRemote {

	@EJB
	private OfertaCandidatoFacadeLocal ofertaCandidatoFacade;
	@EJB
	private OfertaEmpleoFacadeLocal ofertaEmpleoFacadeLocal;
	
	@Override
	public List<MiOfertaRecienteVO> obtenerMisOfertasRecientes(long idEmpresa) throws SQLException {
		if (idEmpresa <= 0) return null;
		List<MiOfertaRecienteVO> results = ofertaCandidatoFacade.obtenerMisOfertasRecientes(idEmpresa);
		return results;
	}

	@Override
	public List<PostulacionRecienteVO> obtenerPostulacionesRecientes(
			long idEmpresa) throws SQLException {
		
		RecientesDAO dao = new RecientesDAO();
		List<PostulacionRecienteVO> results = null;
		
		if (idEmpresa > 0) {
			results = dao.obtenerPostulacionesRecientes(idEmpresa);
		}
		return results;
	}
	
	@Override
	public Long getCountOfertasActivas(long idEmpresa) throws SQLException {
		
		Long cantidad = ofertaEmpleoFacadeLocal.consultaNumeroOfertasActivas(idEmpresa);
		return cantidad;
	}
}