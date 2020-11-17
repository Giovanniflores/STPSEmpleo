package mx.gob.stps.portal.web.candidate.delegate;

import java.sql.SQLException;
import java.util.List;

import mx.gob.stps.portal.core.candidate.service.CandidatoAppServiceRemote;
import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.persistencia.vo.HistoricoBusquedaPPCVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.service.ServiceLocator;
import mx.gob.stps.portal.web.infra.utils.Constantes;
import mx.gob.stps.portal.core.candidate.adminNoPostulaciones.service.AdministracionNoPostulacionesAppServiceRemote;

/**********************************************************
//
// Nombre: OPR  Fecha:	8.10.14
// DelegateImpl comunica con los servicios del Modulo Administracion de No Postulaciones 
// para el seguro de desempleo
//
/************************************************************/
public final class AdministracionNoPostulacionesBusDelegateImpl implements AdministracionNoPostulacionesBusDelegate{

	private static AdministracionNoPostulacionesBusDelegate instance = new AdministracionNoPostulacionesBusDelegateImpl(); 
	
	@Override
	public List<HistoricoBusquedaPPCVO> getListadoOfertasNoPostuladas(long idCandidato) 
			throws SQLException, ServiceLocatorException {
		return getAdministracionNoPostulacionesServiceRemote().getListadoOfertasNoPostuladas(idCandidato);
	}

	public AdministracionNoPostulacionesAppServiceRemote getAdministracionNoPostulacionesServiceRemote()
			throws ServiceLocatorException {
		AdministracionNoPostulacionesAppServiceRemote ejb = (AdministracionNoPostulacionesAppServiceRemote) 
					ServiceLocator.getSessionRemote(Constantes.JNDI_EJB_ADM_OFFER_NO_POST);
		return ejb;
	}
	
	public static AdministracionNoPostulacionesBusDelegate getInstance(){
		return instance;
	}

	@Override
	public CandidatoVo getCandidatoPorId(long idCandidato) 
			throws SQLException, ServiceLocatorException {
		return getCandidatoAppServiceRemote().findById(idCandidato);
	}
	
	public CandidatoAppServiceRemote getCandidatoAppServiceRemote()
			throws ServiceLocatorException {
		CandidatoAppServiceRemote ejb = (CandidatoAppServiceRemote)
				ServiceLocator.getSessionRemote(Constantes.JNDI_EJB_CANDIDATO);
		return ejb;
	}

	@Override
	public boolean actualizarMotivoNoPostulacion(long idCandidato, long idUsuario, long idOfertaEmpleo, long idMotivo, String motivoDescripcion,
			int fuente) throws SQLException, ServiceLocatorException {
		
		return getAdministracionNoPostulacionesServiceRemote().actualizarMotivoNoPostulacion(idCandidato, idUsuario,
				idOfertaEmpleo, idMotivo, motivoDescripcion, fuente);
	}
}