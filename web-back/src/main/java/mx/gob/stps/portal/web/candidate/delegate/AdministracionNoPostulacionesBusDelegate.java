package mx.gob.stps.portal.web.candidate.delegate;

import java.sql.SQLException;
import java.util.List;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.persistencia.vo.HistoricoBusquedaPPCVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;

/**********************************************************
//
// Nombre: OPR  Fecha:	9.10.14
// Interfaz contiene funcionalidad del modulo Administracion 
// de No Postulaciones para el seguro de desempleo
//
/************************************************************/
public interface AdministracionNoPostulacionesBusDelegate {
	public List<HistoricoBusquedaPPCVO> getListadoOfertasNoPostuladas(long idCandidato) 
			throws SQLException, ServiceLocatorException;
	
	public CandidatoVo getCandidatoPorId(long idCandidato) 
			throws SQLException, ServiceLocatorException;

//	public boolean actualizarMotivoNoPostulacion(long idCandidato, long idOfertaEmpleo, long idMotivo,
//			String motivoDescripcion, int fuente) throws SQLException, ServiceLocatorException;

	public boolean actualizarMotivoNoPostulacion(long idCandidato, long idUsuario, long idOfertaEmpleo, long idMotivo, 
			String motivoDescripcion, int fuente) throws SQLException, ServiceLocatorException;
}