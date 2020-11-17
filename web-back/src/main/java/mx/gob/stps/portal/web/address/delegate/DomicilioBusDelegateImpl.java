/**
 * 
 */
package mx.gob.stps.portal.web.address.delegate;

import static mx.gob.stps.portal.web.infra.utils.Constantes.JNDI_EJB_CATALOGO_OPCION;

import java.sql.SQLException;
import java.util.List;

import mx.gob.stps.portal.core.domicilio.service.DomicilioAppServiceRemote;
import mx.gob.stps.portal.core.domicilio.vo.CodigoPostalVO;
import mx.gob.stps.portal.core.domicilio.vo.ColoniaVO;
import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.domicilio.vo.MunicipioVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.service.CatalogoOpcionAppServiceRemote;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.core.oferta.registro.vo.RegistroEntidadesVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.service.ServiceLocator;
import mx.gob.stps.portal.web.infra.utils.Constantes;

/**
 * @author Mario Alberto Vázquez Flores
 * @since 02/03/2011
 **/
public final class DomicilioBusDelegateImpl implements DomicilioBusDelegate {

	private static DomicilioBusDelegate instance = new DomicilioBusDelegateImpl();

	private DomicilioBusDelegateImpl() {
	}

	public static DomicilioBusDelegate getInstance() {
		return instance;
	}

	@Override
	public DomicilioVO buscarDomicilio(long idDomicilio)throws BusinessException, ServiceLocatorException {
		return getDomicilioAppService().buscarDomicilio(idDomicilio);
	}

	@Override
	public CodigoPostalVO obtenerEntidadMunicipioCp(String codigoPostal, int cbnuevo)throws SQLException, ServiceLocatorException, BusinessException {
		return getDomicilioAppService().obtenerEntidadMunicipioCp(codigoPostal, cbnuevo);
	}

	@Override
	public List<ColoniaVO> obtenerColoniasCp(String codPostal)throws SQLException, ServiceLocatorException {
		return getDomicilioAppService().obtenerColoniasCp(codPostal);
	}

	@Override
	public List<ColoniaVO> obtenerColonias(long entidad, long municipio)throws SQLException, ServiceLocatorException {
		return getDomicilioAppService().obtenerColonias(entidad, municipio);
	}

	public List<CatalogoOpcionVO> consultaColonias(long entidad, long municipio)throws SQLException, ServiceLocatorException {
		return getDomicilioAppService().consultaColonias(entidad, municipio);
	}	
	
	@Override
	public String obtenerCodigoPostal(long entidad, long municipio, long colonia) throws SQLException, ServiceLocatorException {
		return getDomicilioAppService().obtenerCodigoPostal(entidad, municipio, colonia);
	}

	@Override
	public List<MunicipioVO> obtenerMunicipio(long idEntidad, long idMunicipio) throws BusinessException, ServiceLocatorException {
		return getDomicilioAppService().obtenerMunicipio(idEntidad, idMunicipio);
	}

	@Override
	public List<CatalogoOpcionVO> obtenerEntidadPorId(long catalogo, long entidad) throws SQLException, ServiceLocatorException {
		return getDomicilioAppService().obtenerEntidadPorId(catalogo, entidad);
	}

	@Override
	public List<MunicipioVO> obtenerMunicipio(long idEntidad) throws BusinessException, ServiceLocatorException {
		return getDomicilioAppService().obtenerMunicipio(idEntidad);
	}
	
	public List<CatalogoOpcionVO> consultaMunicipios(long idEntidad) throws SQLException, ServiceLocatorException { 
		return getDomicilioAppService().consultaMunicipios(idEntidad);
	}
	
	@Override
	public List<CatalogoOpcionVO> consultarCatalogo(long idCatalogo) throws BusinessException, ServiceLocatorException {
		return getCatalogoOpcionAppService().consultarCatalogo(idCatalogo);
	}
	
	@Override
	public List<CatalogoOpcionVO> consultarCatalogo(long idCatalogo, long[] arrIds) throws BusinessException, ServiceLocatorException {
		return getCatalogoOpcionAppService().consultarCatalogo(idCatalogo, arrIds);
	}
	
	
	@Override
	public DomicilioVO buscarDomicilioIdPropietario(long idPropietario, long idTipoPropietario)throws BusinessException, ServiceLocatorException {
		return getDomicilioAppService().buscarDomicilioIdPropietario(idPropietario, idTipoPropietario);
	}	

	/**
	 * Obtiene la referencia a los servicios de domicilio
	 * 
	 * @return instancia de servcios
	 * @throws ServiceLocatorException
	 *             Lanzada en caso de error en la conexion con los servicios
	 */
	public DomicilioAppServiceRemote getDomicilioAppService()throws ServiceLocatorException {
		DomicilioAppServiceRemote ejb = (DomicilioAppServiceRemote) ServiceLocator.getSessionRemote(Constantes.JNDI_EJB_DOMICILIO);
		return ejb;
	}

	/**
	 * Obtiene la referencia a los servicios de catalogo de opciones
	 * 
	 * @return instancia de servcios
	 * @throws ServiceLocatorException
	 *             Lanzada en caso de error en la conexion con los servicios
	 */
	public CatalogoOpcionAppServiceRemote getCatalogoOpcionAppService()throws ServiceLocatorException {
		CatalogoOpcionAppServiceRemote ejb = (CatalogoOpcionAppServiceRemote) ServiceLocator.getSessionRemote(JNDI_EJB_CATALOGO_OPCION);
		return ejb;
	}

	public RegistroEntidadesVO obtieneEntidadMunicipio(long idEntidad, long idMunicipio)throws SQLException, ServiceLocatorException{
		return getDomicilioAppService().obtieneEntidadMunicipio(idEntidad, idMunicipio);		
	}

	@Override
	public List<CatalogoOpcionVO> consultaCiudadesCanada(long idEntidad) throws ServiceLocatorException {
		return getDomicilioAppService().consultaCiudadesCanada(idEntidad);
	}

	@Override
	public List<CatalogoOpcionVO> consultaLocalidades(long idEntidad, long idMunicipio) throws SQLException, ServiceLocatorException {
		return getDomicilioAppService().consultaLocalidades(idEntidad, idMunicipio);
	}


	@Override
	public void setEntidadMunicipio(mx.gob.stps.portal.persistencia.vo.DomicilioVO domicilio) throws SQLException, ServiceLocatorException {
		getDomicilioAppService().setEntidadMunicipio(domicilio);
	}

	@Override
	public mx.gob.stps.portal.persistencia.vo.DomicilioVO getDomicilio(long idPropietario, long idTipoPropietario) throws BusinessException, ServiceLocatorException {
		return getDomicilioAppService().getDomicilio(idPropietario, idTipoPropietario);
	}

	public boolean estaGeoReferenciado(long idPropietario) throws SQLException, ServiceLocatorException {
		return getDomicilioAppService().estaGeoReferenciado(idPropietario);
	}
}