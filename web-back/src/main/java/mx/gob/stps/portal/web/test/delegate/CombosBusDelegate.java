package mx.gob.stps.portal.web.test.delegate;


import java.sql.SQLException;
import java.util.List;

import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.domicilio.vo.CodigoPostalVO;
import mx.gob.stps.portal.core.domicilio.vo.ColoniaVO;
import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.domicilio.vo.MunicipioVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;

public interface CombosBusDelegate {

	public void save(DomicilioVO domicilio) throws PersistenceException,
			ServiceLocatorException;

	public DomicilioVO buscarDomicilio(long idDomicilio)
			throws BusinessException, ServiceLocatorException;
	
	public DomicilioVO buscarDomicilioIdPropietario(long idPropietario)
		throws BusinessException, ServiceLocatorException;

	public CodigoPostalVO obtenerEntidadMunicipioCp(String codigoPostal)
			throws SQLException, ServiceLocatorException;

	public List<ColoniaVO> obtenerColoniasCp(String codPostal)
			throws SQLException, ServiceLocatorException;

	public List<ColoniaVO> obtenerColonias(long entidad, long municipio)
			throws SQLException, ServiceLocatorException;

	public String obtenerCodigoPostal(long entidad, long municipio, long colonia)
			throws SQLException, ServiceLocatorException;

	public List<CatalogoOpcionVO> obtenerEntidadPorId(long catalogo,
			long entidad) throws SQLException, ServiceLocatorException;

	public List<MunicipioVO> obtenerMunicipio(long idEntidad, long idMunicipio)
			throws BusinessException, ServiceLocatorException;

	public List<MunicipioVO> obtenerMunicipio(long idEntidad)
			throws BusinessException, ServiceLocatorException;

	public List<CatalogoOpcionVO> consultarCatalogo(long idCatalogo)
			throws BusinessException, ServiceLocatorException;

}
