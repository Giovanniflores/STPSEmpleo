package mx.gob.stps.portal.movil.web.empresa.delegate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.candidate.vo.IdiomaVO;
import mx.gob.stps.portal.core.candidate.vo.InformacionGeneralVO;
import mx.gob.stps.portal.core.candidate.vo.PerfilLaboralVo;
import mx.gob.stps.portal.core.domicilio.vo.MunicipioVO;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoVO;
import mx.gob.stps.portal.core.oferta.registro.vo.RegistroUbicacionVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.movil.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.persistencia.vo.OfertaCandidatoOcupacionDTO;
//import mx.gob.stps.portal.persistencia.vo.OfertaCandidatoOcupacionDTO;
import mx.gob.stps.portal.persistencia.vo.OfertaEmpresaAdminDTO;
import mx.gob.stps.portal.utils.Catalogos;



public interface EmpresaEspacioDelegate {
	
	public EmpresaVO findEmpresaById(long id)throws BusinessException, SQLException, ServiceLocatorException;

	public EmpresaVO consultaEmpresaPorAutorizar(long idEmpresa) throws ServiceLocatorException; 
	
	public EmpresaVO findByIdUsuario(long idUsuario) throws PersistenceException,BusinessException, ServiceLocatorException;
	
	public List<Long> buscarCandidatosEmpleo(String cadena) throws TechnicalException, SQLException, ServiceLocatorException;
	
	public List<Long> buscarCandidatosEmpleo(String search, Integer idEntidad) throws TechnicalException, SQLException, ServiceLocatorException;
	
	@SuppressWarnings("rawtypes")
    public List buscadorCandidatos(int page, List<?> index) throws TechnicalException, SQLException, ServiceLocatorException;
	
	public InformacionGeneralVO showCandidateDetail(long idCandidato) throws ServiceLocatorException;
	
	public PerfilLaboralVo consultaPerfilLaboral(long idCandidato)throws ServiceLocatorException, PersistenceException;
	
	public List<OfertaEmpleoVO> obtenerOfertasEmpresa(long idEmpresa) throws BusinessException, ServiceLocatorException;
	
	public String getCatalogoOpcionById(long idCatalogo, long idCatalogoOpcion) throws ServiceLocatorException, SQLException;

	public MunicipioVO consultaMunicipio(long idEntidad, long idMunicipio) throws PersistenceException, ServiceLocatorException;
	
	public int match(long idOfertaEmpleo, long idCandidato)  throws ServiceLocatorException;

	public List<CandidatoVo> busquedaAsistidaCandidatos(long idOfertaEmpleo) throws PersistenceException, SQLException, TechnicalException, ServiceLocatorException, Exception;

	public List<Long[]> busquedaAsistidaIdCandidatos(long idOfertaEmpleo) throws PersistenceException, SQLException, TechnicalException, ServiceLocatorException;
	
	public List<IdiomaVO> buscarIdiomas(long idCandidato) throws SQLException, ServiceLocatorException;

	public List<CandidatoVo> buscadorCandidatos(List<Long> indices) throws TechnicalException, SQLException, ServiceLocatorException;
	
	public List<CandidatoVo> buscadorCandidatosQuery(List<Long> indices) throws TechnicalException, SQLException, ServiceLocatorException;

	public List<OfertaCandidatoVO> findByOfferCandidate(long idOfertaEmpleo,
			long idCandidato) throws BusinessException, ServiceLocatorException;
	
	public void notificacionRecuperacionPswEmpresa(long idEmpresa, String usuario, String nombreEmpresa, String correoElectronico, String clave ) throws MailException, ServiceLocatorException ;

	public void createOfertaCandidato(OfertaCandidatoVO ofertaCandidato) throws BusinessException, ServiceLocatorException;

	public List<RegistroUbicacionVO> getUbicacionesOferta(long idOfertaEmpleo) throws SQLException, ServiceLocatorException;

	public List<OfertaCandidatoVO> findCandidatesByEstatus(long idOfertaEmpleo, long estatus) throws PersistenceException, ServiceLocatorException;

	public List<OfertaEmpresaAdminDTO> obtenerOfertasEmpresa(Long idEmpresa,
			List<Catalogos.ESTATUS> estatus,List<Catalogos.ESTATUS> estatusOferta) throws PersistenceException, ServiceLocatorException;

	public List<OfertaCandidatoOcupacionDTO> obtenerOfertaCandidatoEmpresa(
			Long idEmpresa, List<Constantes.ESTATUS> estatus,List<ESTATUS> estatusOferta) throws ServiceLocatorException;


	public List<OfertaCandidatoOcupacionDTO> obtenerOfertaCandidatoEmpresaPorEstatus(
			Long idEmpresa,
			List<Catalogos.ESTATUS> candidatoEstatus,Long idOferta, List<Catalogos.ESTATUS> ofertaEstatus)  throws ServiceLocatorException;

	public Map<String, String> obtenerActividadEconomica(long idActEco)throws BusinessException, SQLException, ServiceLocatorException;

	void notificacionRecuperacionPswMovilEmpresa(long idEmpresa,
			String nombreEmpresa, String correoElectronico, String clave,
			String usuario) throws MailException, ServiceLocatorException;

	public List<CandidatoVo> busquedaCandidatos(Long idOfertaEmpleo) throws Exception;


	
}
