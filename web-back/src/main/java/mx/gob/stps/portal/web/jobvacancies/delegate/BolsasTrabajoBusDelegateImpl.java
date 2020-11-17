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
import mx.gob.stps.portal.core.search.service.IndexerServiceLocator;
import mx.gob.stps.portal.core.search.service.PortalEmpleoBuscadorServiceRemote;
import mx.gob.stps.portal.core.ws.ofertas.service.BuscaOfertaEmpleoAppServiceRemote;
import mx.gob.stps.portal.core.ws.ofertas.service.OfertasDYEAppServiceRemote;
import mx.gob.stps.portal.core.ws.ofertas.service.OfertasSFPAppServiceRemote;
import mx.gob.stps.portal.core.ws.ofertas.vo.OfertaEmpleoInVO;
import mx.gob.stps.portal.core.ws.ofertas.vo.OfertaEmpleoOutVO;
import mx.gob.stps.portal.core.ws.ofertas.vo.OfertaEmpleoSFPVO;
import mx.gob.stps.portal.core.ws.ofertas.vo.OfertaExternaTotalVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.service.ServiceLocator;
import mx.gob.stps.portal.web.infra.utils.Constantes;

public class BolsasTrabajoBusDelegateImpl implements BolsasTrabajoBusDelegate {
	/**
	 * Objeto  que sirve para acceder a una sola instancia de esta clase
	 */
	private static BolsasTrabajoBusDelegate instance = new BolsasTrabajoBusDelegateImpl();
	
	private BolsasTrabajoBusDelegateImpl(){}	
	/**
	 * Metodo publico que genera una instancia de esta misma clase
	 * @return
	 */
	public static BolsasTrabajoBusDelegate getInstance(){
		return instance;
	}
	
	/**
	 * Service locator que genera un objeto remoto EJB
	 * @return
	 * @throws ServiceLocatorException
	 */
	private BuscaOfertaEmpleoAppServiceRemote getOfertasEmpleoAppService() throws ServiceLocatorException {
		BuscaOfertaEmpleoAppServiceRemote ejb = (BuscaOfertaEmpleoAppServiceRemote)ServiceLocator.getSessionRemote(Constantes.JNDI_EJB_WS_BOLSAS_TRABAJO);
		return ejb;
	}	

	private PortalEmpleoBuscadorServiceRemote getPortalEmpleoBuscadorService() throws ServiceLocatorException {
		//PortalEmpleoBuscadorServiceRemote ejb = (PortalEmpleoBuscadorServiceRemote)ServiceLocator.getSessionRemote(Constantes.JNDI_EJB_WS_PORTAL_EMPLEO_BUSCADOR); 
		return IndexerServiceLocator.getIndexadorServiceRemote();
	}
	
	private PortalEmpleoBuscadorServiceRemote getLocalPortalEmpleoBuscadorService() throws ServiceLocatorException {
		PortalEmpleoBuscadorServiceRemote ejb = (PortalEmpleoBuscadorServiceRemote)ServiceLocator.getSessionRemote(Constantes.JNDI_EJB_WS_PORTAL_EMPLEO_BUSCADOR); 
		return ejb;
	}

	private OfertasSFPAppServiceRemote getOfertasSFPService() throws ServiceLocatorException {
		OfertasSFPAppServiceRemote ejb = (OfertasSFPAppServiceRemote)ServiceLocator.getSessionRemote(Constantes.JNDI_EJB_WS_SFP);
		return ejb;
	}	
	
	private OfertasDYEAppServiceRemote getOfertasDYEAppService() throws ServiceLocatorException {
		OfertasDYEAppServiceRemote ejb = (OfertasDYEAppServiceRemote)ServiceLocator.getSessionRemote(Constantes.JNDI_EJB_WS_PORTAL_DISCAPACIDAD_EMPLEO);
		return ejb;
	}
	
	@Override
	public List<OfertaEmpleoOutVO> buscarOfertas(OfertaEmpleoInVO vo) throws BusinessException, ServiceLocatorException {
		return getOfertasEmpleoAppService().buscaOfertaEmpleo(vo);
	}

	@Override
	public List<CandidatoVo> buscarCandidatos(String busqueda) throws TechnicalException, SQLException, ServiceLocatorException {
		return getPortalEmpleoBuscadorService().buscarCandidatos(busqueda);
	}
	@Override
	public List<CandidatoVo> buscarCandidatos(Query query, long idOferta, int records) throws TechnicalException, SQLException, ServiceLocatorException {
		return getPortalEmpleoBuscadorService().buscarCandidatos(query, idOferta, records);
	}

	
	//@Override
	public void iniciaTransferenciaOfertasSFP() throws ServiceLocatorException {
		getOfertasSFPService().inicializaTimerSFP();
	}

	public void finalizaTransferenciaOfertasSFP() throws ServiceLocatorException {
		getOfertasSFPService().shutDownTimer();
	}	
	
	/**
	 * Inicia transferencia de ofertas externas de SFP
	 * @param fecha
	 * @throws ServiceLocatorException
	 */
	public OfertaExternaTotalVO transfiereOfertasExternasSFP(Date fecha) throws ServiceLocatorException {
		return getOfertasSFPService().transfiereOfertasExternasSFP(fecha);
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public List buscarOfertasOcupate(int page, List<ResultInfoBO> index) throws TechnicalException, SQLException, ServiceLocatorException {
		return IndexerServiceLocator.getIndexadorServiceRemote().buscarOfertasOcupate(page, index);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public List buscarOfertasEspecificas(int page, List<Long> index, String pattern) throws TechnicalException, SQLException, ServiceLocatorException {
	 	return getPortalEmpleoBuscadorService().buscarOfertasEspecificas(page, index, pattern);
	}
	
	public List<OfertaPorCanalVO> buscaTodasOfertasSFP() throws ServiceLocatorException, SQLException {
		return getOfertasSFPService().buscaTodasOfertasSFP();
	}
	
	public List<OfertaEmpleoOutVO> buscaOfertasSFP() throws BusinessException, ServiceLocatorException, SQLException {
		return getOfertasSFPService().buscarOfertasSFP();
	}	
	
	public List<OfertaEmpleoOutVO> buscaOfertasSFP(int idEntidad, Long idOcupacion) throws BusinessException, ServiceLocatorException, SQLException {
		return getOfertasSFPService().buscarOfertasSFP(idEntidad, idOcupacion);
	}
	
	public OfertaEmpleoSFPVO buscaOfertaSFP(Long idOferta) throws ServiceLocatorException, SQLException, ParseException{
		return getOfertasSFPService().buscarOfertaSFP(idOferta);
	}
	
	@Override
	public List<Long> buscarCandidatosEmpleo(String cadena) throws TechnicalException, SQLException, ServiceLocatorException {
		return IndexerServiceLocator.getIndexadorServiceRemote().buscarCandidatosEmpleo(cadena);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List buscadorCandidatos(int page, List<?> index) throws TechnicalException, SQLException, ServiceLocatorException {
		return getPortalEmpleoBuscadorService().buscadorCandidatos(page, index);
	}	
	
	public List<CandidatoVo> buscadorCandidatos(List<Long> index) throws TechnicalException, SQLException, ServiceLocatorException{
		return getPortalEmpleoBuscadorService().buscadorCandidatos(index);
	}
	
	public List<CandidatoVo> buscadorCandidatosLocal(List<Long> index) throws TechnicalException, SQLException, ServiceLocatorException{
		return getLocalPortalEmpleoBuscadorService().buscadorCandidatos(index);
	}
	
	@Override
	public List<Long> busquedaEspecifica(int entidad, int area, int escolaridad, int salario, int idMunicipio, int ocupacion,
			int carrera, int edad, int region, String tipoOrden, int columna,int fuente) throws TechnicalException, SQLException, ServiceLocatorException {
		return getPortalEmpleoBuscadorService().busquedaEspecifica(entidad, area, escolaridad, salario, idMunicipio, ocupacion, carrera, edad, region, tipoOrden, columna, fuente);
	}
	
	public List<Long> busquedaEspecificaMultiple(int entidad, int area, int escolaridad, int salario, int idMunicipio, List<Integer> idsOcupaciones,
			                                     List<Integer> idsCarreras, int edad, int region) throws TechnicalException, SQLException, ServiceLocatorException {
		return getPortalEmpleoBuscadorService().busquedaEspecificaMultiple(entidad, area, escolaridad, salario, idMunicipio, idsOcupaciones, idsCarreras, edad, region);
	}
	
	@Override
	public String busquedaEspecificaLbl(int entidad, int area, int escolaridad, int salario, int idMunicipio, int ocupacion, int carrera, int edad,
			int region, String descripcionCarreraUOcupacionOLA, int fuente) throws TechnicalException, SQLException, ServiceLocatorException {
		return getPortalEmpleoBuscadorService().busquedaEspecificaLbl(entidad, area, escolaridad, salario, idMunicipio, ocupacion, carrera, edad, region, descripcionCarreraUOcupacionOLA, fuente);
	}	
	
	public String busquedaEspecificaMultipleLbl(int entidad, int area, int escolaridad, int salario, int idMunicipio, List<Integer> idsOcupaciones, 
			List<Integer> idsCarreras, int edad, int region, String descripcionCarreraUOcupacionOLA) throws TechnicalException, SQLException, ServiceLocatorException{
		return getPortalEmpleoBuscadorService().busquedaEspecificaMultipleLbl(entidad, area, escolaridad, salario, idMunicipio, 
				idsOcupaciones, idsCarreras, edad, region, descripcionCarreraUOcupacionOLA);
	}

	@Override
	public List<ResultInfoBO> buscarOfertasEmpleo(String search, Integer estado,
												  OCUPATE_CAMPO_ORDEN ocupateCampo, OCUPATE_ORDEN_DIRECCION direccion) throws TechnicalException, SQLException, ServiceLocatorException {
		return IndexerServiceLocator.getIndexadorServiceRemote().buscarOfertasEmpleo(search, estado, ocupateCampo, direccion);
	}	

	@Override
	public List<ResultInfoBO> buscarOfertasEmpleo(String search, Integer estado) throws TechnicalException, SQLException, ServiceLocatorException {
		return IndexerServiceLocator.getIndexadorServiceRemote().buscarOfertasEmpleo(search, estado);
	}	
	
	@Override
	public List<OfertaEmpleoOutVO> buscarVacantesDyE() throws BusinessException, ServiceLocatorException {
		return getOfertasDYEAppService().buscarVacantesDyE();
	}
	@Override
	public SearchMessage MsgBuscarOfertasEmpleo(String search, Integer estado,
			OCUPATE_CAMPO_ORDEN ocupateCampo, OCUPATE_ORDEN_DIRECCION direccion)
			throws TechnicalException, SQLException, ServiceLocatorException {
		return IndexerServiceLocator.getIndexadorServiceRemote().MsgBuscarOfertasEmpleo(search, estado, ocupateCampo, direccion);
	}
	@Override
	public SearchMessage MsgBuscarOfertasEmpleo(String search, Integer estado)
			throws TechnicalException, SQLException, ServiceLocatorException {
		return IndexerServiceLocator.getIndexadorServiceRemote().MsgBuscarOfertasEmpleo(search, estado);
	}
	@Override
	public SearchMessage MsgBuscarCandidatosEmpleo(String cadena) throws TechnicalException, SQLException, ServiceLocatorException {
		return IndexerServiceLocator.getIndexadorServiceRemote().MsgBuscarCandidatosEmpleo(cadena);
	}
	@Override
	public SearchMessage MsgBuscarCandidatosEmpleo(String cadena, Integer idEntidad) throws TechnicalException, SQLException, ServiceLocatorException {
		return IndexerServiceLocator.getIndexadorServiceRemote().MsgBuscarCandidatosEmpleo(cadena, idEntidad);
	}
}