package mx.gob.stps.portal.web.jobvacancies.delegate;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.utils.Constantes.OCUPATE_CAMPO_ORDEN;
import mx.gob.stps.portal.core.infra.utils.Constantes.OCUPATE_ORDEN_DIRECCION;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;
import mx.gob.stps.portal.core.search.Query;
import mx.gob.stps.portal.core.search.ResultInfoBO;
import mx.gob.stps.portal.core.search.SearchMessage;
import mx.gob.stps.portal.core.ws.ofertas.vo.OfertaEmpleoInVO;
import mx.gob.stps.portal.core.ws.ofertas.vo.OfertaEmpleoOutVO;
import mx.gob.stps.portal.core.ws.ofertas.vo.OfertaEmpleoSFPVO;
import mx.gob.stps.portal.core.ws.ofertas.vo.OfertaExternaTotalVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;

public interface BolsasTrabajoBusDelegate {
	/**
	 * Delega la busqueda de ofertas de empleo para bolsas de trabajo externas al service
	 * @param vo filtro para realizar la busqueda
	 * @return una lista con las vacantes encontradas de la bolsa de trabajo externa consultada
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 */
	
	public List<OfertaEmpleoOutVO> buscarOfertas(OfertaEmpleoInVO vo) throws BusinessException, ServiceLocatorException;

	public void iniciaTransferenciaOfertasSFP() throws ServiceLocatorException;
	
	public void finalizaTransferenciaOfertasSFP() throws ServiceLocatorException;
	
	public List<CandidatoVo> buscarCandidatos(String busqueda) throws TechnicalException, SQLException, ServiceLocatorException;
	
	public List<CandidatoVo> buscarCandidatos(Query query, long idOferta, int records) throws TechnicalException, SQLException, ServiceLocatorException;
	
	@SuppressWarnings("rawtypes")
	public List buscarOfertasOcupate(int page, List<ResultInfoBO> index) throws TechnicalException, SQLException, ServiceLocatorException;

	public List<ResultInfoBO> buscarOfertasEmpleo(String search, Integer estado,
			  									  OCUPATE_CAMPO_ORDEN ocupateCampo, OCUPATE_ORDEN_DIRECCION direccion) throws TechnicalException, SQLException, ServiceLocatorException;	
	
	public List<ResultInfoBO> buscarOfertasEmpleo(String search, Integer estado) throws TechnicalException, SQLException, ServiceLocatorException;
	
	@SuppressWarnings("rawtypes")
	public List buscarOfertasEspecificas(int page, List<Long> index, String pattern) throws TechnicalException, SQLException, ServiceLocatorException;		
	
	public List<OfertaPorCanalVO> buscaTodasOfertasSFP() throws ServiceLocatorException, SQLException; 
	
	public List<OfertaEmpleoOutVO> buscaOfertasSFP() throws BusinessException, ServiceLocatorException, SQLException;
	
	public List<OfertaEmpleoOutVO> buscaOfertasSFP(int idEntidad, Long idOcupacion) throws BusinessException, ServiceLocatorException, SQLException;
	
	public OfertaEmpleoSFPVO buscaOfertaSFP(Long idOferta) throws ServiceLocatorException, SQLException, ParseException;
	/**
	 * Obtiene las ofertas con estatus activo y fuente de
	 * publicación sea la SFP
	 * @param tipoOrdenamiento
	 * @param numeroColumna
	 * @param nombreEmpresa
	 * @param carrera
	 * @return List<Long>
	 * @throws ServiceLocatorException
	 * @throws SQLException
	 * @throws ParseException
	 */

    public List<Long> buscarCandidatosEmpleo(String cadena) throws TechnicalException, SQLException, ServiceLocatorException;
    
    @SuppressWarnings("rawtypes")
    public List buscadorCandidatos(int page, List<?> index) throws TechnicalException, SQLException, ServiceLocatorException;    

    public OfertaExternaTotalVO transfiereOfertasExternasSFP(Date fecha) throws ServiceLocatorException;
    
    public List<Long> busquedaEspecifica(int entidad, int area, int escolaridad, int salario, int idMunicipio, int ocupacion, int carrera, int edad, int region, String tipoOrden, int columna, int fuente) throws TechnicalException, SQLException, ServiceLocatorException;
    
	public List<Long> busquedaEspecificaMultiple(int entidad, int area, int escolaridad, int salario, int idMunicipio, List<Integer> idsOcupaciones,
			List<Integer> idsCarreras, int edad, int region)  throws TechnicalException, SQLException, ServiceLocatorException;
    
    public String busquedaEspecificaLbl(int entidad, int area, int escolaridad, int salario, int idMunicipio, int ocupacion, int carrera, int edad, int region, String descripcionCarreraUOcupacionOLA, int fuente) throws TechnicalException, SQLException, ServiceLocatorException;    

	public String busquedaEspecificaMultipleLbl(int entidad, int area, int escolaridad, int salario, int idMunicipio, List<Integer> idsOcupaciones, 
			List<Integer> idsCarreras, int edad, int region, String descripcionCarreraUOcupacionOLA) throws TechnicalException, SQLException, ServiceLocatorException;    

    public List<CandidatoVo> buscadorCandidatos(List<Long> index) throws TechnicalException, SQLException, ServiceLocatorException;
    
    public List<CandidatoVo> buscadorCandidatosLocal(List<Long> index) throws TechnicalException, SQLException, ServiceLocatorException;
    
    public List<OfertaEmpleoOutVO> buscarVacantesDyE() throws BusinessException, ServiceLocatorException;
    
    //-----------------------Busqueda al indexador con mensajes
    
    public SearchMessage MsgBuscarOfertasEmpleo(String search, Integer estado,
			  OCUPATE_CAMPO_ORDEN ocupateCampo, OCUPATE_ORDEN_DIRECCION direccion) throws TechnicalException, SQLException, ServiceLocatorException;
    
    public SearchMessage MsgBuscarOfertasEmpleo(String search, Integer estado) throws TechnicalException, SQLException, ServiceLocatorException;
    
    public SearchMessage MsgBuscarCandidatosEmpleo(String cadena) throws TechnicalException, SQLException, ServiceLocatorException;
    public SearchMessage MsgBuscarCandidatosEmpleo(String cadena, Integer idEntidad) throws TechnicalException, SQLException, ServiceLocatorException;
    
}