package mx.gob.stps.portal.core.empresa.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.empresa.vo.EmpresaPorAutorizarVO;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.utils.Constantes.EVENTO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_REGISTRO;
import mx.gob.stps.portal.ws.renapo.vo.CurpVO;

@Local
public interface EmpresaAppServiceLocal {
	
	
	public void notificaEmpresaPorAutorizar(EmpresaPorAutorizarVO empresaPorAutorizarVO, long idUsuario)
	throws PersistenceException, BusinessException, TechnicalException, MailException;
	
	

	public void notificaEmpresa(EmpresaVO empresaVO, long idUsuario, String strDetalle) 
    throws PersistenceException, BusinessException, TechnicalException, MailException;
	
	
	//public void notificaEmpresa(EmpresaVO empresaVO, long idUsuario)  throws PersistenceException, BusinessException, TechnicalException, MailException;
	
	public void notificaCambioContrasena(EmpresaVO empresaVO, String correoElectronico) throws TechnicalException, MailException;	

	public void actualizaEmpresaMail(EmpresaVO empresa, long idEmpresa, long idUsuario) throws SQLException;

	public EmpresaVO findEmpresaById(long id);

	public void bitacoraEstatus(EVENTO evento, long idUsuario, TIPO_REGISTRO tipoRegistro, long idRegistro, int estatusAnterior, String emailanterior);

	public void bitacoraEstatus(EVENTO evento, long idUsuario, String descripcion, 
	    		                     TIPO_REGISTRO tipoRegistro, long idRegistro, int estatusAnterior, String dataanterior);
	
	Map<String, String> obtenerActividadEconomica(long idActEco);
	
	public CurpVO consultaCURPPorDatosPersonales(CurpVO datosPersonales);
	
	public boolean esCurpUnico(String curp);
	
	public boolean esIdPortalEmpleoCurpUnico(String curp);
	
	public CurpVO consultaDatosPersonalesPorCURP(CurpVO curpVo);
	
	public CurpVO consultaDatosPersonalesPorCURP(String CURP);
	
	public void notificacionRecuperacionContrasena(long idPropietario, String usuario, TIPO_PROPIETARIO tipoPropietario, String nombrePropietario,
			String correoElectronico, String url) throws MailException;
}
