package mx.gob.stps.portal.web.reg_unico.impl;

import java.util.List;
import java.sql.SQLException;

import mx.gob.stps.portal.web.infra.utils.Constantes;
import mx.gob.stps.portal.persistencia.vo.CandidatoGradoAcademicoVO;
import mx.gob.stps.portal.persistencia.vo.CandidatoVO;
import mx.gob.stps.portal.exception.TechnicalException;
import mx.gob.stps.portal.web.infra.service.ServiceLocator;
import mx.gob.stps.portal.persistencia.vo.ExpectativaLaboralVO;
import mx.gob.stps.portal.web.reg_unico.SingleRegisterBusDelegate;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;
import mx.gob.stps.portal.core.search.ResultInfoBO;
import mx.gob.stps.portal.core.search.service.SingleRegisterServiceRemote;

public class SingleRegisterBusDelegateImpl implements SingleRegisterBusDelegate {
	
	private static SingleRegisterBusDelegate instance = new SingleRegisterBusDelegateImpl();
	
	private SingleRegisterBusDelegateImpl(){}	
	
	public static SingleRegisterBusDelegate getInstance(){
		return instance;
	}

	private SingleRegisterServiceRemote getSingleRegisterService() throws ServiceLocatorException {
		SingleRegisterServiceRemote ejb = (SingleRegisterServiceRemote)ServiceLocator.getSessionRemote(Constantes.JNDI_EJB_REGISTRO_UNICO); 
		return ejb;
	}

	@Override
	public List<CandidatoVO> candidatoListByIndex(List<Long> index) throws TechnicalException, SQLException, ServiceLocatorException {
		return getSingleRegisterService().candidatoListByIndex(index);
	}

	@Override
	public List<ExpectativaLaboralVO> expectativaLaboralList(long idCandidato) throws TechnicalException, SQLException, ServiceLocatorException {
		return getSingleRegisterService().expectativaLaboralList(idCandidato);
	}

	@Override
	public List<CandidatoGradoAcademicoVO> gradoAcademicoList(long idCandidato) throws TechnicalException, SQLException, ServiceLocatorException {
		return getSingleRegisterService().gradoAcademicoList(idCandidato);
	}

	@Override
	public List<OfertaPorCanalVO> resultInfoList(int page, List<ResultInfoBO> index) throws TechnicalException, SQLException, ServiceLocatorException {
		return getSingleRegisterService().resultInfoList(page, index);
	}
}