/**
 * 
 */
package mx.gob.stps.portal.core.domicilio.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.domicilio.dao.MunicipioDAO;
import mx.gob.stps.portal.core.domicilio.dao.ObtenerColoniasCpDAO;
import mx.gob.stps.portal.core.domicilio.dao.ObtenerColoniasDAO;
import mx.gob.stps.portal.core.domicilio.dao.ObtenerEntidadByIdDAO;
import mx.gob.stps.portal.core.domicilio.dao.ObtenerEntidadMunicipioDAO;
import mx.gob.stps.portal.core.domicilio.service.DomicilioAppServiceLocal;
import mx.gob.stps.portal.core.domicilio.service.DomicilioAppServiceRemote;
import mx.gob.stps.portal.core.domicilio.vo.CodigoPostalVO;
import mx.gob.stps.portal.core.domicilio.vo.ColoniaVO;
import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.domicilio.vo.MunicipioVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.core.oferta.registro.vo.RegistroEntidadesVO;
import mx.gob.stps.portal.core.persistencia.facade.DomicilioFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.MunicipioFacadeLocal;

import org.apache.log4j.Logger;

/**
 * @author Mario Alberto Vázquez Flores
 * @since 09/03/2011
 **/
@Stateless(name = "DomicilioAppService", mappedName = "DomicilioAppService")
public class DomicilioAppService implements DomicilioAppServiceRemote, DomicilioAppServiceLocal {

	private static Logger logger = Logger.getLogger(DomicilioAppService.class);

	@EJB
	private DomicilioFacadeLocal domicilioFacade;
	
	@EJB
	private MunicipioFacadeLocal municipioFacadeLocal;

	public DomicilioAppService() {
	}

	@Override
	public void save(DomicilioVO domicilio) throws PersistenceException {
		logger.info("buscarDomicilio en capa Service:" + domicilio.toString());
		domicilioFacade.save(domicilio);
	}

	public void update(DomicilioVO domicilio) throws PersistenceException {
		domicilioFacade.update(domicilio);
	}

	public DomicilioVO buscarDomicilio(long idDomicilio)throws BusinessException {
		return domicilioFacade.buscarDomicilio(idDomicilio);
	}

	public CodigoPostalVO obtenerEntidadMunicipioCp(String codigoPostal, int cpnuevo) throws SQLException, BusinessException {
		ObtenerEntidadMunicipioDAO dao = new ObtenerEntidadMunicipioDAO();
		CodigoPostalVO codigoPostalVO = dao.obtenerEntidadMunicipioCp(codigoPostal); 

		// Valida si es un CP valido
		if(cpnuevo == 1)
			if ((codigoPostalVO.getIdEntidad() <= 0) || (codigoPostalVO.getIdMunicipio() <= 0))
				throw new BusinessException("El codigo postal no existe o es invalido");

		//return dao.obtenerEntidadMunicipioCp(codigoPostal);
		return codigoPostalVO;
	}

	@Override
	public List<ColoniaVO> obtenerColoniasCp(String codPostal)throws SQLException {
		ObtenerColoniasCpDAO dao = new ObtenerColoniasCpDAO();
		return dao.obtenerColoniasCp(codPostal);
	}

	@Override
	public List<ColoniaVO> obtenerColonias(long entidad, long municipio)throws SQLException {
		ObtenerColoniasDAO dao = new ObtenerColoniasDAO();
		return dao.obtenerColonias(entidad, municipio);
	}

	public List<CatalogoOpcionVO> consultaColonias(long entidad, long municipio)throws SQLException {
		ObtenerColoniasDAO dao = new ObtenerColoniasDAO();
		return dao.consultaColonias(entidad, municipio);
	}
	
	@Override
	public String obtenerCodigoPostal(long entidad, long municipio, long colonia)throws SQLException {
		//ObtenerCodigoPostalDAO dao = new ObtenerCodigoPostalDAO();
		return domicilioFacade.obtenerCodigoPostal(entidad, municipio, colonia);
	}

	@Override
	public List<CatalogoOpcionVO> obtenerEntidadPorId(long catalogo,long entidad) throws SQLException {
		ObtenerEntidadByIdDAO dao = new ObtenerEntidadByIdDAO();
		return dao.obtenerEntidadPorId(catalogo, entidad);
	}

	@Override
	public List<MunicipioVO> obtenerMunicipio(long idEntidad, long idMunicipio) {
		return domicilioFacade.obtenerMunicipio(idEntidad, idMunicipio);
	}

	@Override
	public List<MunicipioVO> obtenerMunicipio(long idEntidad) {		
		return domicilioFacade.obtenerMunicipio(idEntidad);
	}

	public List<CatalogoOpcionVO> consultaMunicipios(long idEntidad) throws SQLException {
		MunicipioDAO dao = new MunicipioDAO();		
		return dao.consultaMunicipios(idEntidad);
	}

	
	@Override
	public DomicilioVO buscarDomicilioIdPropietario(long idPropietario, long idTipoPropietario)throws BusinessException {		
		return domicilioFacade.buscarDomicilioIdPropietario(idPropietario, idTipoPropietario);
	}

	@Override
	public MunicipioVO consultaMunicipio(long idMunicipio, long idEntidad)
			throws PersistenceException {
		return domicilioFacade.consultaMunicipio(idMunicipio,idEntidad);
	}

	public RegistroEntidadesVO obtieneEntidadMunicipio(long idEntidad, long idMunicipio) throws PersistenceException{
		return domicilioFacade.obtieneEntidadMunicipio(idEntidad, idMunicipio);		
	}

	@Override
	public String consultaColonia(long idColonia) {
		return domicilioFacade.consultaCodigoPostal(idColonia).getColoniaDescripcion();
	}

	@Override
	public List<CatalogoOpcionVO> consultaCiudadesCanada(long idEntidad) {
		return domicilioFacade.obtenerCiudadesCanada(idEntidad);
	}

	@Override
	public List<CatalogoOpcionVO> consultaLocalidades(long idEntidad, long idMunicipio) throws SQLException {
		return municipioFacadeLocal.getLocalidades(idEntidad, idMunicipio);
	}

	@Override
	public void setEntidadMunicipio(mx.gob.stps.portal.persistencia.vo.DomicilioVO domicilio) throws PersistenceException {
		municipioFacadeLocal.setEntidadMunicipio(domicilio);
	}

	@Override
	public mx.gob.stps.portal.persistencia.vo.DomicilioVO getDomicilio(long idPropietario, long idTipoPropietario) throws PersistenceException {
		return domicilioFacade.getDomicilioByKeys(idPropietario, idTipoPropietario);
	}

	@Override
	public boolean estaGeoReferenciado(long idPropietario) throws PersistenceException {
		return domicilioFacade.estaGeoReferenciado(idPropietario);
	}
}
