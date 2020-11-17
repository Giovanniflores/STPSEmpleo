package mx.gob.stps.portal.core.debbuger.service.impl;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.PersistenceException;
import mx.gob.stps.portal.core.autorizacion.service.AutorizacionAppServiceLocal;
import mx.gob.stps.portal.core.debbuger.service.DepuracionOfertasAppServiceRemote;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.core.oferta.detalle.service.OfertaEmpleoAppServiceLocal;
import mx.gob.stps.portal.core.persistencia.facade.OfertaEmpleoFacadeLocal;
//fixme import mx.gob.stps.portal.core.search.service.IndexerServiceLocator;
import org.apache.log4j.Logger;

@Stateless(name = "DepuracionOfertasAppService", mappedName = "DepuracionOfertasAppService")
@TransactionManagement(TransactionManagementType.BEAN)
public class DepuracionOfertasAppService implements DepuracionOfertasAppServiceRemote, DepuracionOfertasAppServiceLocal {

	private static Logger logger = Logger.getLogger(DepuracionOfertasAppService.class);

	@EJB
	private OfertaEmpleoFacadeLocal ofertaEmpleoFacade;
	
	@EJB
	private OfertaEmpleoAppServiceLocal ofertaEmpleoAppService;

	@EJB
	private AutorizacionAppServiceLocal autorizacionAppService;
	
	@Resource
	private TimerService timerService;	
	
	public void initTimer() {
		shutDownTimer();

		PropertiesLoader properties = PropertiesLoader.getInstance();
		String time = properties.getProperty("schedule.process.ofertas.time");
	
		int hora = 0;
		int minuto = 0;
		String TIMER_SCHEDULE_DEFAULT = "01:00";
		
		if (time != null && validateTime(time)) {
			String[] atime = time.split(":");
			hora = Integer.parseInt(atime[0]);
			minuto = Integer.parseInt(atime[1]);
		} else {
			logger.error("No existe fecha asignada o no es valida en el proceso de depuracion de ofertas, se ejecutara en la hora por default");
			String[] atime = TIMER_SCHEDULE_DEFAULT.split(":");
			hora = Integer.parseInt(atime[0]);
			minuto = Integer.parseInt(atime[1]);
		}

		logger.info("Hora:"+hora+" minutos:"+minuto);
		
		long intervalDuration = 1440 * 60 * 1000; // Un dia
		Date date = getDateTimerExecute(hora, minuto);
		Timer timer = timerService.createTimer(date,intervalDuration, "nuevo");

		logger.info("Primera fecha para correr DEPURADOR DE OFERTAS DE EMPLEO POR VIGENCIA : "+ timer.getNextTimeout());
	}

	@Timeout
	public void timeout(Timer timer) {
		try {
			logger.info("----------> Inicia proceso de Depuracion de Ofertas de Empleo vencidas......");
			depuraOfertasVigenciaVencida();
			logger.info("----------> Final proceso de Depuracion de Ofertas de Empleo vencidas......");
		} catch (Exception e) {
			logger.error(e);
		}

		try{
			logger.info("----------> Actualiza contador de ofertas publicadas por STPS");
			ofertaEmpleoAppService.actualizaTotalOfertasPublicadasSTPS();
		}catch(Exception e){
			logger.error(e);
		}		
	}
	
	private void depuraOfertasVigenciaVencida() throws PersistenceException {

		List<Long> idsOfertas = ofertaEmpleoFacade.consultaOfertasFueraVigencia();
		
		if (idsOfertas!=null){
			logger.info("Se depuraran "+ idsOfertas.size() +" ofertas de empleo fuera de la vigencia");

			for (long idOfertaEmpleo : idsOfertas){
//FIXME OracleText
/*
				try {
					IndexerServiceLocator.getIndexerServiceRemote().eliminaOfertaindice(idOfertaEmpleo);
				} catch (Exception e) {
				    e.printStackTrace();
				}
 */
				ofertaEmpleoFacade.actualizaEstatus(idOfertaEmpleo, ESTATUS.ELIMINADA_VIG.getIdOpcion());
			}
		}
	}
	
	/**
	 * Apagan los timers existentes
	 */
	private void shutDownTimer() {

		Collection<Timer> timers = timerService.getTimers();
		logger.info("Timers existentes " + timers);
		if (timers != null) {
			for (Iterator<Timer> iterator = timers.iterator(); iterator.hasNext();) {
				Timer t = iterator.next();
				t.cancel();
				logger.info("Timer: " + t + " canceled");
			}
		}
	}

	/* Elimina los timers agendados */
	@Override
	public void destroyInitTimer() {
		@SuppressWarnings("unchecked")
		Collection<Timer> timersCollection = timerService.getTimers();
		Iterator<Timer> iterator = timersCollection.iterator();

		while (iterator.hasNext()) {
			Timer timer = iterator.next();
			if (timer.getInfo().equals("nuevo")) {
				timer.cancel();
			}
		}
	}

	/* Valida que el formato de fecha sea valido */
	private boolean validateTime(final String time) {
		String spattern = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
		Pattern pattern = Pattern.compile(spattern);
		Matcher matcher = pattern.matcher(time);
		return matcher.matches();
	}

	/* Funcion que obtiene el siguiente timer para ejecutar */
	private Date getDateTimerExecute(int hora, int minuto) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, hora);
		calendar.set(Calendar.MINUTE, minuto);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));
		//calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+1 );
		Date date = calendar.getTime();
		logger.debug("Primera ejecución de depurador de ofertas: "+date.toString());
		return date;
	}

}
