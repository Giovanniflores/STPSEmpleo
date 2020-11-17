package mx.gob.stps.portal.core.oferta.busqueda.service.impl;

import static mx.gob.stps.portal.core.infra.utils.Constantes.ID_FUENTE_CANADA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.INTERVALO_REGISTROS_TODAS;
import static mx.gob.stps.portal.core.infra.utils.Constantes.NUMERO_REGISTROS;
import static mx.gob.stps.portal.core.infra.utils.Constantes.NUMERO_REGISTROS_TODAS;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.log4j.Logger;

import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.oferta.busqueda.service.OfertasRecientesAppServiceRemote;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaRecienteVO;
import mx.gob.stps.portal.core.oferta.dao.OfertasRecientesDAO;
import mx.gob.stps.portal.core.persistencia.facade.EmpresaFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.OfertaEmpleoFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.OfertaRequisitoFacadeLocal;
import mx.gob.stps.portal.persistencia.vo.OfertaFuncionVO;

/**
 * @author Mario Alberto Vázquez Flores
 * @since 22/03/2011
 **/
@TransactionManagement(TransactionManagementType.BEAN)
@Stateless(name = "OfertasRecientesAppService", mappedName = "OfertasRecientesAppService")
public class OfertasRecientesAppService implements OfertasRecientesAppServiceRemote {

	@EJB
	private EmpresaFacadeLocal empresaFacade;
	
	@EJB
	private OfertaEmpleoFacadeLocal ofertaEmpleoFacade;

	@EJB
	private OfertaRequisitoFacadeLocal ofertaRequisitoFacade;	
	
	private static Logger logger = Logger.getLogger(OfertasRecientesAppService.class);

	private static final String TIMER_OFERTAS_RECIENTES_TODAS = "OfertasRecientesTodasTimer";
	
	@Resource
	private TimerService timerService;

	private static List<OfertaRecienteVO> listaOfertasRecientes;
	private static List<OfertaPorCanalVO> listaOfertasRecientesTodas;
	private static List<OfertaRecienteVO> listaOfertasDestacadas;
	private static List<OfertaPorCanalVO> listaOfertasDestacadasTodas;
	private static List<OfertaRecienteVO> listaOfertasCanada;
	private static List<OfertaPorCanalVO> listaOfertasCanadaTodas;
	private static List<OfertaRecienteVO> listaOfertasGendarmeria;
	private static List<OfertaPorCanalVO> listaOfertasGendarmeriaTodas;
	
	public OfertasRecientesAppService() {
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<OfertaRecienteVO> obtenerOfertasRecientes(int numRegistros)
			throws SQLException {
		if (numRegistros <= 0)
			throw new IllegalArgumentException("Numero de registros invalido");
		// logger.info("OfertasRecientesAppService.obtenerOfertasRecientes");

		/**
		 * Sergio Rojas: substituimos la invocación al DAO por la invocación a una clase facade
		 * OfertasRecientesDAO dao = new OfertasRecientesDAO(); return
		 * dao.obtenerOfertasRecientes(numRegistros);
		 */
		return empresaFacade.obtenerOfertasRecientes(numRegistros,0);
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<OfertaPorCanalVO> obtenerOfertasRecientesTodas(int numRegistros) throws SQLException {
		if (numRegistros <= 0) throw new IllegalArgumentException("Numero de registros invalido");
		List<OfertaPorCanalVO> listaOfertasPorCanal = new OfertasRecientesDAO().obtenerOfertasRecientesTodas(numRegistros, 0);
		for (OfertaPorCanalVO vo: listaOfertasPorCanal) {
			vo.setFunciones(getOfertaFuncionList(vo.getIdOfertaEmpleo()));
		}
		return listaOfertasPorCanal;					
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<OfertaPorCanalVO> obtenerOfertasDestacadasTodas(int numRegistros) throws SQLException {
		if (numRegistros <= 0) throw new IllegalArgumentException("Numero de registros invalido");
		List<OfertaPorCanalVO> listaOfertasPorCanal = new OfertasRecientesDAO().obtenerOfertasDestacadasTodas(numRegistros);
		for (OfertaPorCanalVO vo: listaOfertasPorCanal) {
			vo.setFunciones(getOfertaFuncionList(vo.getIdOfertaEmpleo()));
		}
		return listaOfertasPorCanal;
	}
	
	private String getOfertaFuncionList(long idOfertaEmpleo) {
		StringBuilder list = new StringBuilder();
		if (idOfertaEmpleo <=0) return "";
		List<OfertaFuncionVO> funcionList = ofertaEmpleoFacade.getOfertaFuncionList(idOfertaEmpleo);
		for (OfertaFuncionVO resultElement : funcionList) {
			list.append(" ").append(resultElement.getFuncion());
		}
		return list.toString().trim();
	}

	@Override
	public List<OfertaRecienteVO> obtenerOfertasDestacadas(int numRegistros)
			throws SQLException {

		if (numRegistros <= 0)
			throw new IllegalArgumentException("Numero de registros invalido");
		// logger.info("OfertasRecientesAppService.obtenerOfertasDestacadas");

		return empresaFacade.obtenerOfertasDestacadas(numRegistros,
				                                      Constantes.SALARIO_MINIMO_DOCTORADO,
				                                      Constantes.SALARIO_MINIMO_MAESTRIA,
				                                      Constantes.SALARIO_MINIMO_LICENCIATURA);	
	}

	private List<OfertaRecienteVO> obtenerOfertasCanada(int numeroRegistros) {
		if (numeroRegistros <= 0)
			throw new IllegalArgumentException("Numero de registros invalido");
		// logger.info("OfertasRecientesAppService.obtenerOfertasRecientes");

		/**
		 * Sergio Rojas: substituimos la invocación al DAO por la invocación a una clase facade
		 * OfertasRecientesDAO dao = new OfertasRecientesDAO(); return
		 * dao.obtenerOfertasRecientes(numRegistros);
		 */
		return empresaFacade.obtenerOfertasRecientes(numeroRegistros, ID_FUENTE_CANADA);
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	private List<OfertaPorCanalVO> obtenerOfertasCanadaTodas(int numRegistros) {
		
		if (numRegistros <= 0) throw new IllegalArgumentException("Numero de registros invalido");
		
		//List<OfertaPorCanalVO> listaOfertasPorCanal = empresaFacade.obtenerOfertasRecientesTodas(numRegistros, ID_FUENTE_CANADA);
		List<OfertaPorCanalVO> listaOfertasPorCanal = new OfertasRecientesDAO().obtenerOfertasRecientesTodas(numRegistros, ID_FUENTE_CANADA);
		
		return listaOfertasPorCanal;
	}
	
	@Override
	public List<OfertaRecienteVO> obtenerOfertasGendarmeria(int numRegistros)
			throws SQLException {
		if(numRegistros<=0)
			throw new IllegalArgumentException("Numero de registros invalido"); 
		
		return empresaFacade.obtenerOfertasGendarmeria(numRegistros);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<OfertaPorCanalVO> obtenerOfertasGendarmeriaTodas(int numRegistros) throws SQLException {
		if(numRegistros<=0) throw new IllegalArgumentException("Numero de registros invalido");
		
		//List<OfertaPorCanalVO> listaOfertasPorCanal = empresaFacade.obtenerOfertasGendarmeriaTodas(numRegistros);
		List<OfertaPorCanalVO> listaOfertasPorCanal = new OfertasRecientesDAO().obtenerOfertasGendarmeriaTodas(numRegistros);

		return listaOfertasPorCanal;
	}
	
	// TODO Eliminar
	/*private String setRequisitos(List<OfertaRequisitoVO> listaRequisitos){

		String requisitos = new String();
		
		if(listaRequisitos != null && !listaRequisitos.isEmpty()){
			StringBuilder req = new StringBuilder();
			StringBuilder hab = new StringBuilder();
			StringBuilder com = new StringBuilder();			
			
			Iterator<OfertaRequisitoVO> it = listaRequisitos.iterator();
			while (it.hasNext()) {
				OfertaRequisitoBO bo = new OfertaRequisitoBO(it.next());
				if (bo.tipoRequisito() == 1)
					req.append(", " + bo.toString());
				else if (bo.tipoRequisito() == 2)
					hab.append(", " + bo.toString());
				else if (bo.tipoRequisito() == 3)
					com.append(", " + bo.toString());
			}
			if (req.length() < 3) hab = hab.replace(0, 2, "");
			requisitos = req.toString().replaceFirst(", ","") + hab.toString() + com.toString();			
		}
		return requisitos;
	}*/

	@SuppressWarnings("unused")
	public void initTimerOfertasRecientesDestacadas() {
		Calendar hoy = Calendar.getInstance();
		
		// Antes de crear el timer, detenemos si hay hilos de despliegues anteriores
		if (timerService.getTimers() != null){
			for (Timer t :timerService.getTimers()){
				if (TIMER_OFERTAS_RECIENTES_TODAS.equals(t.getInfo().toString())){
					t.cancel();
				}
			}
		}
		
		long intervalDuration = INTERVALO_REGISTROS_TODAS * 60 * 1000;
		Timer timer = timerService.createTimer(hoy.getTime(), intervalDuration, TIMER_OFERTAS_RECIENTES_TODAS);
	}

	@Timeout
	public void timeoutOfertasRecientesDestacadas(Timer timer) {
		
		if (TIMER_OFERTAS_RECIENTES_TODAS.equals(timer.getInfo().toString())) {
			
		try {
			//System.out.println("+++ Consultando Oferta RECIENTES +++");
			listaOfertasRecientesTodas  = obtenerOfertasRecientesTodas(NUMERO_REGISTROS_TODAS);			
			listaOfertasRecientes       = obtenerOfertasRecientes(NUMERO_REGISTROS);			
		} catch (Exception sqle) {
			sqle.printStackTrace();
		}

		try {
			//System.out.println("+++ Consultando Oferta DESTACADAS +++");
			listaOfertasDestacadasTodas = obtenerOfertasDestacadasTodas(NUMERO_REGISTROS_TODAS);
			listaOfertasDestacadas      = obtenerOfertasDestacadas(NUMERO_REGISTROS);
		} catch (Exception sqle) {
			sqle.printStackTrace();
		}
		
		try {
			//System.out.println("+++ Consultando Oferta de CANADA +++");
			listaOfertasCanada      	= obtenerOfertasCanada(NUMERO_REGISTROS);
			listaOfertasCanadaTodas 	= obtenerOfertasCanadaTodas(NUMERO_REGISTROS_TODAS);
		} catch (Exception sqle) {
			sqle.printStackTrace();
		}

		try {
			//System.out.println("+++ Consultando Oferta de GENDARMERIA +++");
			listaOfertasGendarmeria		= obtenerOfertasGendarmeria(NUMERO_REGISTROS);
			listaOfertasGendarmeriaTodas= obtenerOfertasGendarmeriaTodas(NUMERO_REGISTROS_TODAS);
		} catch (Exception sqle) {
			sqle.printStackTrace();
		}
		
		logger.info(timer.getNextTimeout().toString()+": se refrescan las consultas planificadas de ofertas recientes/destacadas");
		}
	}

	public List<OfertaRecienteVO> getListaOfertasRecientes() {	
		return listaOfertasRecientes;
	}
	
	public List<OfertaPorCanalVO> getListaOfertasRecientesTodas() {
		
		//if (listaOfertasRecientesTodas==null){
			try{
				listaOfertasRecientesTodas  = obtenerOfertasRecientesTodas(NUMERO_REGISTROS_TODAS);
			}catch(Exception e){
				e.printStackTrace();
			}
		//}
		
		return listaOfertasRecientesTodas;
	}

	public List<OfertaRecienteVO> getListaOfertasDestacadas() {	
		return listaOfertasDestacadas;
	}	
	
	public List<OfertaPorCanalVO> getListaOfertasDestacadasTodas() {	
		try {
			if(null == listaOfertasDestacadasTodas)
				listaOfertasDestacadasTodas = obtenerOfertasDestacadasTodas(NUMERO_REGISTROS_TODAS);
		} catch (Exception sqle) {
			sqle.printStackTrace();
		}
		return listaOfertasDestacadasTodas;
	}

	@Override
	public List<OfertaRecienteVO> getListaOfertasCanada() {
		return listaOfertasCanada;
	}

	@Override
	public List<OfertaPorCanalVO> getListaOfertasCanadaTodas() {
		return listaOfertasCanadaTodas;
	}

	@Override
	public List<OfertaRecienteVO> getListaOfertasGendarmeria() {
		return listaOfertasGendarmeria;
	}

	@Override
	public List<OfertaPorCanalVO> getListaOfertasGendarmeriaTodas() {
		try {
			if(null == listaOfertasGendarmeriaTodas)
				listaOfertasGendarmeriaTodas = obtenerOfertasGendarmeriaTodas(NUMERO_REGISTROS_TODAS);
		} catch (Exception sqle) {
			sqle.printStackTrace();
		}
		return listaOfertasGendarmeriaTodas;
	}	
	
}