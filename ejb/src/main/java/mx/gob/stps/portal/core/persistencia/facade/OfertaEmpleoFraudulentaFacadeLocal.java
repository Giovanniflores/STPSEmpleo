package mx.gob.stps.portal.core.persistencia.facade;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaIdiomaVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaRequisitoVO;
import mx.gob.stps.portal.core.oferta.registro.vo.OfertaCarreraEspecialidadVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoFraudulentaVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaPrestacionVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaUbicacionVO;

@Local
public interface OfertaEmpleoFraudulentaFacadeLocal {

	public long save(OfertaEmpleoFraudulentaVO vo) throws PersistenceException;
	
	public long save(OfertaEmpleoVO oferta) throws PersistenceException;
	
	public void saveOfertaCarreraFraudulenta(OfertaCarreraEspecialidadVO vo, long idOfertaEmpleo) throws PersistenceException;
	
	public void saveOfertaOfertaIdiomaFraudulenta(OfertaIdiomaVO vo) throws PersistenceException;
	
	public void saveOfertaPrestacionFraudulenta(OfertaPrestacionVO vo) throws PersistenceException;
	
	public void saveOfertaRequisitoFraudulenta(OfertaRequisitoVO vo) throws PersistenceException;

	public void saveOfertaUbicacionFraudulenta(OfertaUbicacionVO vo) throws PersistenceException;	
}
