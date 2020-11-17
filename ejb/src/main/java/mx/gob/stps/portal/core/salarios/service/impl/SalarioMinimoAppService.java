package mx.gob.stps.portal.core.salarios.service.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import mx.gob.stps.portal.core.persistencia.facade.SalariosAplicacionesFacadeLocal;
import mx.gob.stps.portal.persistencia.vo.SalariosAplicacionesVO;
import org.apache.log4j.Logger;

import mx.gob.stps.portal.exception.TechnicalException;
import mx.gob.stps.portal.persistencia.vo.SalariosVigentesVO;
import mx.gob.stps.portal.core.persistencia.facade.SalariosVigentesFacadeLocal;
import mx.gob.stps.portal.core.salarios.service.SalarioMinimoAppServiceRemote;

import java.util.ArrayList;
import java.util.List;

@Stateless(mappedName = "SalarioMinimoAppService")
public class SalarioMinimoAppService implements SalarioMinimoAppServiceRemote {

	private static final Logger logger = Logger.getLogger(SalarioMinimoAppService.class);

	@EJB
	private SalariosVigentesFacadeLocal salariosVigentesFacade;
	
	@EJB
	private SalariosAplicacionesFacadeLocal SalariosAplicacionesFacade;

//	@Override
//	public List<SalariosVigentesVO> consultaSalariosHistorial() throws TechnicalException {
//		List<SalariosVigentesVO> vo = new ArrayList<SalariosVigentesVO>();
//		try{
//			vo= salariosVigentesFacade.salariosList();
//		}catch(Exception e){
//			e.printStackTrace();
//			logger.error(e);
//			return vo;
//		}
//		return vo;
//	}

	@Override
	public SalariosVigentesVO consultaSalarioMinimoVigente() throws TechnicalException {
		SalariosVigentesVO vo = new SalariosVigentesVO();
		try{
			vo = salariosVigentesFacade.salarioVigente();
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e);
			return vo;
		}
		
		return vo;
	}

//	@Override
//	public boolean guardaSalarios(SalariosVigentesVO salario) throws BusinessException, TechnicalException {
//		boolean saved = false;
//		long now = System.currentTimeMillis();
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTimeInMillis(now);
//		Date fechaAlta = calendar.getTime();
//		salario.setFechaAlta(fechaAlta);
//		salario.setFechaModificacion(fechaAlta);
//		salario.setIdSalario(0);
//		try{
//			Long id = salariosVigentesFacade.registroSalario(salario);
//			if(id!=0){
//				saved= true;
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//			saved=false;
//		}
//		return saved;
//	}

//	@Override
//	public boolean actualizaTaller(SalariosAplicacionesVO salariosAplicacionesVO) throws BusinessException, TechnicalException {
//		boolean saved = false;
//		long now = System.currentTimeMillis();
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTimeInMillis(now);
//		Date fechaAlta = calendar.getTime();
//		salariosAplicacionesVO.setFechaModificacion(fechaAlta);
//
//		try{
//		 SalariosAplicacionesFacade.actualizaSalariosApli(salariosAplicacionesVO);
//		 saved=true;
//
//		}catch(Exception e){
//			e.printStackTrace();
//			saved = false;
//		}
//		return saved;
//	}

	@Override
	public List<SalariosAplicacionesVO> consultaSalariosAplicaciones()throws TechnicalException {
		List<SalariosAplicacionesVO> vo = new ArrayList<SalariosAplicacionesVO>();
		try{
			vo = SalariosAplicacionesFacade.salariosAplicacionesList();
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e);
			return vo;
		}
		return vo;
	}
}