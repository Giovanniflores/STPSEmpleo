package mx.gob.stps.portal.web.address.delegate;

import java.sql.SQLException;
import java.util.List;

import mx.gob.stps.portal.core.domicilio.vo.CodigoPostalVO;
import mx.gob.stps.portal.core.domicilio.vo.ColoniaVO;
import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.domicilio.vo.MunicipioVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.core.oferta.registro.vo.RegistroEntidadesVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;

/**
 * @author Mario Alberto Vázquez Flores
 * @since 02/03/2011
 **/
public interface DomicilioBusDelegate {

	public DomicilioVO buscarDomicilio(long idDomicilio) throws BusinessException, ServiceLocatorException;
	
	public DomicilioVO buscarDomicilioIdPropietario(long idPropietario, long idTipoPropietario) throws BusinessException, ServiceLocatorException;

	public CodigoPostalVO obtenerEntidadMunicipioCp(String codigoPostal, int cbnuevo) throws SQLException, ServiceLocatorException, BusinessException;

	public List<ColoniaVO> obtenerColoniasCp(String codPostal) throws SQLException, ServiceLocatorException;

	public List<ColoniaVO> obtenerColonias(long entidad, long municipio) throws SQLException, ServiceLocatorException;

	public String obtenerCodigoPostal(long entidad, long municipio, long colonia) throws SQLException, ServiceLocatorException;

	public List<CatalogoOpcionVO> obtenerEntidadPorId(long catalogo, long entidad) throws SQLException, ServiceLocatorException;

	public List<MunicipioVO> obtenerMunicipio(long idEntidad, long idMunicipio) throws BusinessException, ServiceLocatorException;

	public List<MunicipioVO> obtenerMunicipio(long idEntidad) throws BusinessException, ServiceLocatorException;

	public List<CatalogoOpcionVO> consultarCatalogo(long idCatalogo) throws BusinessException, ServiceLocatorException;
	
	public List<CatalogoOpcionVO> consultarCatalogo(long idCatalogo, long[] arrIds) throws BusinessException, ServiceLocatorException;	

	public List<CatalogoOpcionVO> consultaMunicipios(long idEntidad) throws SQLException, ServiceLocatorException;
	
	public List<CatalogoOpcionVO> consultaColonias(long entidad, long municipio)throws SQLException, ServiceLocatorException;
	
	public List<CatalogoOpcionVO> consultaLocalidades(long idEntidad, long idMunicipio) throws SQLException, ServiceLocatorException;

	public RegistroEntidadesVO obtieneEntidadMunicipio(long idEntidad, long idMunicipio)throws SQLException, ServiceLocatorException;

	public List<CatalogoOpcionVO> consultaCiudadesCanada(long idEntidad) throws ServiceLocatorException;
	
	public void setEntidadMunicipio(mx.gob.stps.portal.persistencia.vo.DomicilioVO domicilio) throws SQLException, ServiceLocatorException;
	
	public mx.gob.stps.portal.persistencia.vo.DomicilioVO getDomicilio(long idPropietario, long idTipoPropietario) throws BusinessException, ServiceLocatorException;

	public boolean estaGeoReferenciado(long idPropietario) throws SQLException, ServiceLocatorException;
}