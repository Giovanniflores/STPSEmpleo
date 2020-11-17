package mx.gob.stps.portal.core.persistencia.facade;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorPerfilVO;
import mx.gob.stps.portal.persistencia.vo.HistoricoBusquedaPPCVO;

/**********************************************************
//
// Nombre: OPR  Fecha:	8.10.14
// Interfaz remota para el Modulo Administracion de No Postulaciones 
// para el seguro de desempleo
//
/************************************************************/
@Local
public interface AdministracionNoPostulacionesFacadeLocal {
	public List<HistoricoBusquedaPPCVO> getListadoOfertasNoPostuladas(long idCandidato) throws PersistenceException;
	
	public OfertaPorPerfilVO 			obtenerOfertaPorID(long idOfertaEmpleo, long idCandidato) throws SQLException;

	public void 						actualizarMotivoNoPostulacion(long idCandidato, long idOfertaEmpleo, long idMotivo, 
												String motivoDescripcion, int fuente) throws SQLException;
}
