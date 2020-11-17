package mx.gob.stps.portal.core.oferta.detalle.service;

import java.util.List;

import javax.ejb.Remote;

import mx.gob.stps.portal.core.infra.exception.BusinessException;

@Remote
public interface OLAServiceRemote {
	public List<Integer> consultaCarrerasOLA(int idOcupacionOLA)
			throws BusinessException;

}
