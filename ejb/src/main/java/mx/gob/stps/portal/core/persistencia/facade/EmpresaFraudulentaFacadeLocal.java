package mx.gob.stps.portal.core.persistencia.facade;

import java.util.HashMap;
import java.util.List;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.empresa.vo.EmpresaFraudulentaVO;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;


/**
 * Proporciona acceso a los metodos de persistencia para registros de la entidad "EmpresaFraudulenta"
 * 
 * @author haydee.vertti
 *
 */
@Local
public interface EmpresaFraudulentaFacadeLocal {

	/**
	 * Registra una empresa fraudulenta
	 * @param vo EmpresaFraudulentaVO
	 * @return Identificador de la empresa fraudulenta
	 * @throws PersistenceException Lanzada en caso de error durante el proceso de persistencia
	 */
	public long save(EmpresaFraudulentaVO vo) throws PersistenceException;
	
	/**
	 * Method 'findById'
	 * Regresa un objeto EmpresaFraudulentaVO con los datos correspondientes a
	 * la empresa fraudulenta cuyo identificador se proporciona
	 * 
	 * @param id
	 * @return EmpresaFraudulentaVO
	 */	
	public EmpresaFraudulentaVO findById(long id) throws PersistenceException;
	
	public void saveEmpresaFraudulenta(EmpresaVO empresa) throws PersistenceException;

	public HashMap<Long, EmpresaFraudulentaVO> consultaEmpresasFraudulentas(
			EmpresaVO empresaAut);

	public HashMap<Long, EmpresaFraudulentaVO> consultaTelefonosEmpresasFraudulentas(
			List<TelefonoVO> telefonosEmpresaAut);

	public HashMap<Long, EmpresaFraudulentaVO> consultaDomiciliosEmpresasFraudulentas(
			DomicilioVO domicilioEmpresAut);


}
