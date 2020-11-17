package mx.gob.stps.portal.core.oferta.detalle.service;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.ws.renapo.exception.IndexerConnectionException;

@Remote
public interface OfertaCompatibilidadAppServiceRemote {
	
	
	public List<Long[]> buscaOfertasRecomendadasIndexer(long idCandidato) throws IndexerConnectionException;
	
	public int match(long idOfertaEmpleo, long idCandidato);

	public List<CandidatoVo> busquedaAsistidaCandidatos(long idOfertaEmpleo) throws SQLException, PersistenceException, TechnicalException;

	public List<Long> buscaOfertaRecomendada(int idEntidad,
                                             List<String> conocimientos,
                                             List<String> habilidades,
                                             List<String> expectativas,
                                             int MAX_OFERTAS) throws IndexerConnectionException;
}
