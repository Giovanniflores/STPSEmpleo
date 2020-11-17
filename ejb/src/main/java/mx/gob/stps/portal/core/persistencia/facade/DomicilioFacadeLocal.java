package mx.gob.stps.portal.core.persistencia.facade;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.domicilio.vo.CodigoPostalVO;
import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.domicilio.vo.MunicipioVO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.core.oferta.registro.vo.RegistroEntidadesVO;
import mx.gob.stps.portal.persistencia.entity.Domicilio;

/**
 * @author Mario Alberto Vázquez Flores
 * @since 01/03/2011
 **/
@Local
public interface DomicilioFacadeLocal {

	public void save(DomicilioVO domicilio) throws PersistenceException;
	
	public void saveSFP(DomicilioVO domicilio) throws PersistenceException;
	
	public void update(DomicilioVO domicilio) throws PersistenceException;

	public DomicilioVO buscarDomicilio(long idDomicilio);
	
	//Cambio
	public DomicilioVO buscarDomicilioIdPropietario(long idPropietario, long idTipoPropietario);

	public Domicilio getDomicilio(long idPropietario, long idTipoPropietario);
	
	public List<MunicipioVO> obtenerMunicipio(long idEntidad, long idMunicipio);

	public List<MunicipioVO> obtenerMunicipio(long idEntidad);

	public MunicipioVO consultaMunicipio(long idMunicipio, long idEntidad) throws PersistenceException;

	public CodigoPostalVO consultaCodigoPostal(long idColonia) throws PersistenceException;
	
	public RegistroEntidadesVO obtieneEntidadMunicipio(long idEntidad, long idMunicipio) throws PersistenceException;

	public String obtenerCodigoPostal(long entidad, long municipio, long colonia) throws PersistenceException;

	public List<CatalogoOpcionVO> obtenerCiudadesCanada(long idEntidad);

	public List<MunicipioVO> obtenerCiudadCanada(int entidad, int idMunicipio);
	
	public mx.gob.stps.portal.persistencia.vo.DomicilioVO getDomicilioByKeys(long idPropietario, long idTipoPropietario) throws PersistenceException;

	public boolean estaGeoReferenciado(long idPropietario) throws PersistenceException;
}