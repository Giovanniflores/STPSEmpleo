package mx.gob.stps.portal.core.oferta.detalle.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.oferta.detalle.service.OLAServiceRemote;
import mx.gob.stps.portal.core.persistencia.facade.OLAFacadeLocal;

;

@Stateless(name = "OlaService", mappedName = "OlaService")
public class OLAService implements OLAServiceRemote {

	@EJB
	private OLAFacadeLocal olaFacade;

	public OLAService() {
	}

	public List<Integer> consultaCarrerasOLA(int idOcupacionOLA)
			throws BusinessException {
		return olaFacade.consultaCarrerasOLA(idOcupacionOLA);
	}

}