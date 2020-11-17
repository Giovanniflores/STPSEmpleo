package mx.gob.stps.portal.core.domicilio.service;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.domicilio.vo.CodigoPostalVO;
import mx.gob.stps.portal.core.domicilio.vo.ColoniaVO;
import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.domicilio.vo.MunicipioVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.core.oferta.registro.vo.RegistroEntidadesVO;

@Remote
public interface DomicilioAppServiceRemote {

	//public void save(DomicilioVO domicilio) throws PersistenceException;
	
	public void update(DomicilioVO domicilio) throws PersistenceException;

	public DomicilioVO buscarDomicilio(long idDomicilio) throws BusinessException;

	public DomicilioVO buscarDomicilioIdPropietario(long idPropietario, long idTipoPropietario) throws BusinessException;
	
	public CodigoPostalVO obtenerEntidadMunicipioCp(String codigoPostal, int cpnuevo) throws SQLException, BusinessException;

	public List<ColoniaVO> obtenerColoniasCp(String codPostal) throws SQLException;

	public List<ColoniaVO> obtenerColonias(long entidad, long municipio) throws SQLException;

	public String obtenerCodigoPostal(long entidad, long municipio, long colonia) throws SQLException;

	public List<CatalogoOpcionVO> obtenerEntidadPorId(long catalogo, long entidad) throws SQLException;

	public List<MunicipioVO> obtenerMunicipio(long idEntidad, long idMunicipio) throws BusinessException;

	public List<MunicipioVO> obtenerMunicipio(long idEntidad) throws BusinessException;

	public List<CatalogoOpcionVO> consultaMunicipios(long idEntidad) throws SQLException;

	public List<CatalogoOpcionVO> consultaColonias(long entidad, long municipio)throws SQLException;
	
	public List<CatalogoOpcionVO> consultaLocalidades(long idEntidad, long idMunicipio) throws SQLException;
	
	public MunicipioVO consultaMunicipio(long idMunicipio, long idEntidad) throws PersistenceException;

	public RegistroEntidadesVO obtieneEntidadMunicipio(long idEntidad, long idMunicipio) throws PersistenceException;

	public String consultaColonia(long idColonia);

	public List<CatalogoOpcionVO> consultaCiudadesCanada(long idEntidad);
	
	public void setEntidadMunicipio(mx.gob.stps.portal.persistencia.vo.DomicilioVO domicilio) throws PersistenceException;
	
	public mx.gob.stps.portal.persistencia.vo.DomicilioVO getDomicilio(long idPropietario, long idTipoPropietario) throws PersistenceException;

	public boolean estaGeoReferenciado(long idPropietario) throws PersistenceException;
}
