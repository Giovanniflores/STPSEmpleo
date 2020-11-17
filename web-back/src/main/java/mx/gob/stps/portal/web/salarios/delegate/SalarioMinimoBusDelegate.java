package mx.gob.stps.portal.web.salarios.delegate;

import mx.gob.stps.portal.persistencia.vo.SalariosAplicacionesVO;
import mx.gob.stps.portal.persistencia.vo.SalariosVigentesVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;

public interface SalarioMinimoBusDelegate {

     public SalariosVigentesVO consultaSalarioMinimoVigente() throws ServiceLocatorException;

     public SalariosAplicacionesVO consultaSalariosMinimosPortalEmpleo() throws ServiceLocatorException;
}
