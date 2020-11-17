package mx.gob.stps.portal.core.search.service;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;
import mx.gob.stps.portal.core.search.ResultInfoBO;
import mx.gob.stps.portal.exception.TechnicalException;
import mx.gob.stps.portal.persistencia.vo.CandidatoGradoAcademicoVO;
import mx.gob.stps.portal.persistencia.vo.CandidatoVO;
import mx.gob.stps.portal.persistencia.vo.ExpectativaLaboralVO;


@Remote
public interface SingleRegisterServiceRemote {

	public List<CandidatoVO> candidatoListByIndex(List<Long> index) throws TechnicalException, SQLException;
	
	public List<CandidatoGradoAcademicoVO> gradoAcademicoList(long idCandidato) throws TechnicalException, SQLException;
	
	public List<ExpectativaLaboralVO> expectativaLaboralList(long idCandidato) throws TechnicalException, SQLException;
	
	public List<OfertaPorCanalVO> resultInfoList(int page, List<ResultInfoBO> index) throws TechnicalException, SQLException;
}