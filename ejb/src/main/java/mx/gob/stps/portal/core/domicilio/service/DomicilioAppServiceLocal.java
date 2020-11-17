package mx.gob.stps.portal.core.domicilio.service;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.domicilio.vo.CodigoPostalVO;
import mx.gob.stps.portal.core.domicilio.vo.ColoniaVO;
import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.domicilio.vo.MunicipioVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;

/**
 * @author Mario Alberto Vázquez Flores
 * @since 09/03/2011
 **/

@Local
public interface DomicilioAppServiceLocal {

	public void save(DomicilioVO domicilio);	
	public void update(DomicilioVO domicilio) throws PersistenceException;
	
	public DomicilioVO buscarDomicilio(long idDomicilio) throws BusinessException;		
		
	public CodigoPostalVO obtenerEntidadMunicipioCp(String codigoPostal, int cpnuevo) throws SQLException, BusinessException;
	public List<ColoniaVO> obtenerColoniasCp(String codPostal)  throws SQLException;
	public List<ColoniaVO> obtenerColonias(long entidad, long municipio) throws SQLException;
	public String obtenerCodigoPostal(long entidad, long municipio, long colonia) throws SQLException;
	public List<CatalogoOpcionVO> obtenerEntidadPorId(long catalogo, long entidad) throws SQLException;
		
	public List<MunicipioVO> obtenerMunicipio(long idEntidad, long idMunicipio) throws BusinessException;	
	public List<MunicipioVO> obtenerMunicipio(long idEntidad) throws BusinessException;
	
	/**
	 * Proporciona el acceso a los servicios relacionados con la entidad Domicilio
	 * 
	 * @author haydee.vertti
	 *
	 */
	DomicilioVO buscarDomicilioIdPropietario(long idPropietario, long idTipoPropietario) throws BusinessException;	
	
}
