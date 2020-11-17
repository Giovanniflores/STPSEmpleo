package mx.gob.stps.portal.core.salarios.service;

import mx.gob.stps.portal.exception.TechnicalException;
import mx.gob.stps.portal.persistencia.vo.SalariosAplicacionesVO;
import mx.gob.stps.portal.persistencia.vo.SalariosVigentesVO;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface SalarioMinimoAppServiceRemote {

//    public List<SalariosVigentesVO> consultaSalariosHistorial()throws TechnicalException;

    public SalariosVigentesVO consultaSalarioMinimoVigente() throws TechnicalException;

//    public boolean guardaSalarios (SalariosVigentesVO salario)throws BusinessException, TechnicalException;

//    public boolean actualizaTaller (SalariosAplicacionesVO salariosAplicacionesVO)throws BusinessException, TechnicalException;

    public List<SalariosAplicacionesVO> consultaSalariosAplicaciones()throws TechnicalException;
}
