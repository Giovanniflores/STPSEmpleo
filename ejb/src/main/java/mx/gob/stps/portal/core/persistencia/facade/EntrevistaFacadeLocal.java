package mx.gob.stps.portal.core.persistencia.facade;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.vo.EntrevistaVO;

@Local
public interface EntrevistaFacadeLocal {

	public long save(EntrevistaVO vo) throws PersistenceException;
	
	public List<EntrevistaVO> getOpcionesCatalogo(long idEntrevista) throws PersistenceException;

	public void update(EntrevistaVO vo) throws PersistenceException;

	public void cancelaEntrevistas(long idOfertaEmpleo, ESTATUS cancelada, Date fechaModificacion, 
                                   ESTATUS nueva, ESTATUS aceptada, ESTATUS reprogramada) throws PersistenceException;
            
	public void actualizaEstatus(long idEntrevista, ESTATUS estatus, Date fechaModificacion) throws PersistenceException;

	public void reprograma(EntrevistaVO entrevistaVo) throws PersistenceException;
}
