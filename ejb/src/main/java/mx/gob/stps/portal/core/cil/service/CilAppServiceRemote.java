package mx.gob.stps.portal.core.cil.service;

import java.util.Date;

import javax.ejb.Remote;

import mx.gob.stps.portal.core.cil.bo.AttentionRequest;

@Remote
public interface CilAppServiceRemote {
	
	public int saveAttentionRequest(AttentionRequest attention);
	
	public AttentionRequest findAttentionRequest(long idCil, long idCandidato);
	
	public int create(long idCil, long idCandidato, long idUsuario, long idTiposeguimiento, Date fechaSeguimiento, int estatus, int idCausa, String otraCausa, Date fechaColocacion, String horaSeguimiento, long idAtencion);
	
	public boolean existTraceUserEmployment(long idCil, long idCandidato, long idAtencion);
}
