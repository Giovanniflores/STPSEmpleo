package mx.gob.stps.portal.web.cil.delegate.impl;

import static mx.gob.stps.portal.web.infra.utils.Constantes.JNDI_EJB_CIL;

import java.util.Date;

import mx.gob.stps.portal.core.cil.bo.AttentionRequest;
import mx.gob.stps.portal.core.cil.service.CilAppServiceRemote;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.web.cil.delegate.CilServicesBusDelegate;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.service.ServiceLocator;

public class CilServicesBusDelegateImpl implements CilServicesBusDelegate {

	private static CilServicesBusDelegate instance = new CilServicesBusDelegateImpl();
	
	private CilServicesBusDelegateImpl()  {
		
	}
	
	public static CilServicesBusDelegate getInstance() {
		return instance;
	}
	
	/**
	 * Obtiene la referencia remota de los servicios del cil
	 * @return
	 * @throws ServiceLocatorException
	 */
	private CilAppServiceRemote getCilAppService() throws ServiceLocatorException {
		CilAppServiceRemote ejb = (CilAppServiceRemote)ServiceLocator.getSessionRemote(JNDI_EJB_CIL);
		return ejb;
	}
	
	@Override
	public int saveAttentionRequest(AttentionRequest attention) throws BusinessException, ServiceLocatorException {
		return getCilAppService().saveAttentionRequest(attention);
	}

	@Override
	public AttentionRequest findAttentionRequest(long idCil, long idCandidato) throws BusinessException, ServiceLocatorException {
		return getCilAppService().findAttentionRequest(idCil, idCandidato);
	}

	@Override
	public int create(long idCil, long idCandidato, long idUsuario, long idTiposeguimiento, Date fechaSeguimiento, int estatus, int idCausa, String otraCausa, Date fechaColocacion, String horaSeguimiento, long idAtencion) throws BusinessException, ServiceLocatorException {
		return getCilAppService().create(idCil, idCandidato, idUsuario, idTiposeguimiento, fechaSeguimiento, estatus, idCausa, otraCausa, fechaColocacion, horaSeguimiento, idAtencion);
	}

	@Override
	public boolean existTraceUserEmployment(long idCil, long idCandidato, long idAtencion) throws BusinessException, ServiceLocatorException {
		return getCilAppService().existTraceUserEmployment(idCil, idCandidato, idAtencion);
	}
}