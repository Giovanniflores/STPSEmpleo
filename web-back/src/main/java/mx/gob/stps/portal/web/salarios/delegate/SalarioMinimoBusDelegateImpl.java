package mx.gob.stps.portal.web.salarios.delegate;

import mx.gob.stps.portal.core.salarios.service.SalarioMinimoAppServiceRemote;
import mx.gob.stps.portal.exception.TechnicalException;
import mx.gob.stps.portal.persistencia.entity.Catalogo;
import mx.gob.stps.portal.persistencia.vo.SalariosAplicacionesVO;
import mx.gob.stps.portal.persistencia.vo.SalariosVigentesVO;
import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.service.ServiceLocator;
import mx.gob.stps.portal.web.infra.utils.Constantes;

import java.util.List;

public class SalarioMinimoBusDelegateImpl implements SalarioMinimoBusDelegate {

    private static SalarioMinimoBusDelegate instance = new SalarioMinimoBusDelegateImpl();

    private SalarioMinimoBusDelegateImpl() {
    }

    public static SalarioMinimoBusDelegate getInstance(){
        return instance;
    }

    private SalarioMinimoAppServiceRemote getSalarioMinimoAppService() throws ServiceLocatorException {
        return (SalarioMinimoAppServiceRemote) ServiceLocator.getSessionRemote(Constantes.JNDI_EJB_SALARIO_MINIMO);
    }

    @Override
    public SalariosVigentesVO consultaSalarioMinimoVigente() throws ServiceLocatorException {
        try {
            return getSalarioMinimoAppService().consultaSalarioMinimoVigente();
        } catch (TechnicalException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public SalariosAplicacionesVO consultaSalariosMinimosPortalEmpleo() throws ServiceLocatorException {
        SalariosAplicacionesVO salariosMinimosPortalEmpleo = null;
        try {
            List<SalariosAplicacionesVO> salariosAplicacionesVOs = getSalarioMinimoAppService().consultaSalariosAplicaciones();
            for (SalariosAplicacionesVO salariosAplicacionesVO : salariosAplicacionesVOs) {
                if (salariosAplicacionesVO.getIdAplicacion() == Catalogos.CATALOGO_FUENTE_QUE_REGISTRA_OFERTA.PORTAL.getIdOpcion()) {
                    salariosMinimosPortalEmpleo = salariosAplicacionesVO;
                    break;
                }
            }
        } catch (TechnicalException e) {
            e.printStackTrace();
        }

        return salariosMinimosPortalEmpleo;
    }
}
