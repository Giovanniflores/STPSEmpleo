package mx.gob.stps.portal.core.persistencia.facade;

import java.util.List;

import javax.persistence.PersistenceException;

import mx.gob.stps.portal.persistencia.entity.ModalidadOferta;
import mx.gob.stps.portal.persistencia.vo.ModalidadOfertaVO;
import mx.gob.stps.portal.persistencia.vo.ModalidadVO;

public interface ModalidadOfertaFacadeLocal {
	
	public long registrar(ModalidadOfertaVO modalidadOferta) throws PersistenceException;
	
	public int update(ModalidadOfertaVO modalidadOferta) throws PersistenceException;
	
	public int delete(long idModalidadOferta) throws PersistenceException;
	
	public List<ModalidadVO> obtenerModalidad (long idSubprograma) throws PersistenceException;
	public ModalidadOfertaVO obtenerModalidadOfertaByIdOferta (long idOferta) throws PersistenceException;
	public ModalidadOferta findByIdOferta(long idOferta) throws PersistenceException;

}
