package mx.gob.stps.portal.web.cil.delegate;

import java.util.Date;

import mx.gob.stps.portal.core.cil.bo.AttentionRequest;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;

public interface CilServicesBusDelegate {
	
	public int saveAttentionRequest(AttentionRequest attention) throws BusinessException, ServiceLocatorException;
	
	public AttentionRequest findAttentionRequest(long idCil, long idCandidato) throws BusinessException, ServiceLocatorException;
	
	public int create(long idCil, long idCandidato, long idUsuario,	long idTiposeguimiento, Date fechaSeguimiento, int estatus, int idCausa, String otraCausa, Date fechaColocacion, String horaSeguimiento, long idAtencion) throws BusinessException, ServiceLocatorException;
	
	public boolean existTraceUserEmployment(long idCil, long idCandidato, long idAtencion) throws BusinessException, ServiceLocatorException;
}