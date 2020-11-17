package mx.gob.stps.portal.core.debbuger.service.impl;

import java.util.Calendar;
import java.util.Collection;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import mx.gob.stps.portal.core.debbuger.service.ContadorOfertasAppServiceRemote;
import mx.gob.stps.portal.core.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.core.oferta.detalle.service.OfertaEmpleoAppServiceLocal;

import org.apache.log4j.Logger;

@Stateless(name = "ContadorOfertasAppService", mappedName = "ContadorOfertasAppService")
@TransactionManagement(TransactionManagementType.BEAN)
public class ContadorOfertasAppService implements ContadorOfertasAppServiceRemote {

	private static Logger logger = Logger.getLogger(ContadorOfertasAppService.class);

	@EJB
	private OfertaEmpleoAppServiceLocal ofertaEmpleoAppService;

	@Resource
	private TimerService timerService;

	private final static String TIMER_NAME = "TIMER-OFERTAS-TOTAL";
	
	@Override
	public void iniciaProcesoRecurrente() {
		detieneProcesoRecurrente(); // Detiene en caso de existir un hilo vivo ejecutandose

		logger.info("----------> Se inicializa el proceso recurrente para la actualizacion del contador de ofertas publicadas...");

		PropertiesLoader properties = PropertiesLoader.getInstance();
		long min = properties.getPropertyInt("schedule.ofertas.publicadas.periodo.min");

		if (min<=0) min = 60; // una hora por defecto
		long intervalDuration = min * 60 * 1000; // Una hora

		Calendar calendar = Calendar.getInstance();

		@SuppressWarnings("unused")
		Timer timer = timerService.createTimer(calendar.getTime(), intervalDuration, TIMER_NAME);
	}

	@Timeout
	public void timeout(Timer timer) {
		try{
			logger.info("----------> Actualiza contador de ofertas publicadas por STPS......");
			ofertaEmpleoAppService.actualizaTotalOfertasPublicadasSTPS();
		}catch(Exception e){logger.error(e);}
	}

	@Override
	public void detieneProcesoRecurrente() {
		@SuppressWarnings("unchecked")
		Collection<Timer> timers = timerService.getTimers();

		if (timers==null) return;

		for (Timer timer : timers) {
			if (timer.getInfo().equals(TIMER_NAME)){
				timer.cancel();
				break;
			}
		}
	}

}