package mx.gob.stps.portal.web.reg_unico;

import java.util.List;
import java.sql.SQLException;

import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;
import mx.gob.stps.portal.core.search.ResultInfoBO;
import mx.gob.stps.portal.exception.TechnicalException;
import mx.gob.stps.portal.persistencia.vo.CandidatoGradoAcademicoVO;
import mx.gob.stps.portal.persistencia.vo.CandidatoVO;
import mx.gob.stps.portal.persistencia.vo.ExpectativaLaboralVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;

public interface SingleRegisterBusDelegate {

	public List<CandidatoVO> candidatoListByIndex(List<Long> index) throws TechnicalException, SQLException, ServiceLocatorException;
	
	public List<ExpectativaLaboralVO> expectativaLaboralList(long idCandidato) throws TechnicalException, SQLException, ServiceLocatorException;
	
	public List<CandidatoGradoAcademicoVO> gradoAcademicoList(long idCandidato) throws TechnicalException, SQLException, ServiceLocatorException;
	
	public List<OfertaPorCanalVO> resultInfoList(int page, List<ResultInfoBO> index) throws TechnicalException, SQLException, ServiceLocatorException;
}