package mx.gob.stps.portal.core.candidate.adminNoPostulaciones.service;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import mx.gob.stps.portal.persistencia.vo.HistoricoBusquedaPPCVO;

/**********************************************************
//
// Nombre: OPR  Fecha:	8.10.14
// Interfaz remota para el Modulo Administracion de No Postulaciones 
// para el seguro de desempleo
//
/************************************************************/
@Remote
public interface AdministracionNoPostulacionesAppServiceRemote {
	public List<HistoricoBusquedaPPCVO> getListadoOfertasNoPostuladas(long idCandidato) throws SQLException;

//	public boolean 						actualizarMotivoNoPostulacion(long idCandidato, long idOfertaEmpleo, 
//																		long idMotivo, String motivoDescripcion, int fuente);

	public boolean 						actualizarMotivoNoPostulacion(long idCandidato, long idUsuario,
																		long idOfertaEmpleo, long idMotivo, String motivoDescripcion, int fuente);
}