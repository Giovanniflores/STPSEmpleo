package mx.gob.stps.portal.core.debbuger.service.impl;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
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

import mx.gob.stps.portal.core.candidate.service.CandidatoAppServiceLocal;
import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.debbuger.service.DepuracionCandidatosAppServiceRemote;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.PropertiesLoader;

import org.apache.log4j.Logger;

@Stateless(name = "DepuracionCandidatosAppService", mappedName = "DepuracionCandidatosAppService")
@TransactionManagement(TransactionManagementType.BEAN)
public class DepuracionCandidatosAppService implements DepuracionCandidatosAppServiceRemote {
	
	private static Logger logger = Logger.getLogger(DepuracionCandidatosAppService.class);
	
	@EJB
	private CandidatoAppServiceLocal candidatoAppService;

	@Resource
	private TimerService timerService;

	private final static String TIMER_NAME = "TIMER-DEPURA-CANDIDATO-VIG";
	
	@Override
	public void iniciaProcesoRecurrente() {
		detieneProcesoRecurrente();
		logger.info("----------> Se inicializa el proceso recurrente para la depuración de candidatos por vigencia...");
		
		
		PropertiesLoader properties = PropertiesLoader.getInstance();
		String time = properties.getProperty("schedule.process.depuracion.time");
		long min    = properties.getPropertyInt("schedule.process.depuracion.perido.minutos");	
		
		int hora = 0;
		int minuto = 0;
		String TIMER_SCHEDULE_DEFAULT = "02:30";
		
		if (time != null && validateTime(time)) {
			String[] atime = time.split(":");
			hora = Integer.parseInt(atime[0]);
			minuto = Integer.parseInt(atime[1]);
		} else {
			logger.error("No existe fecha asignada o no es valida en el proceso de depuracion de candidatos, se ejecutara en la hora por default");
			String[] atime = TIMER_SCHEDULE_DEFAULT.split(":");
			hora = Integer.parseInt(atime[0]);
			minuto = Integer.parseInt(atime[1]);
		}		
		
		logger.info("Hora:"+hora+" minutos:"+minuto);

		long intervalDuration = min * 60 * 1000; //min=1440 Un día; min=60 Una hora			

		@SuppressWarnings("unused")				
		Date date = getDateTimerExecute(hora, minuto);
		Timer timer = timerService.createTimer(date,intervalDuration, "nuevo");
		logger.info("Primera fecha para correr DEPURADOR DE CANDIDATOS POR VIGENCIA  : "+ timer.getNextTimeout());		
	}
	
	@Timeout
	public void timeout(Timer timer) {
		try {
			logger.info("----------> Inicia proceso de Depuracion de Candidatos fuera de vigencia 12 meses......");
			depuraCandidatosFueraDeVigencia();
			logger.info("----------> Final proceso de Depuracion de Candidatos fuera de vigencia  12 meses......");
			logger.info("----------> Inicia proceso de Notificación a Candidatos próximos a quedar fuera de vigencia 11 meses......");
			avisarCandidatosFueraDeVigencia();
			logger.info("----------> Final proceso de Notificación a Candidatos próximos a quedar fuera de vigencia  11 meses......");
		} catch (Exception e) {
			logger.error(e);
		}
	}	
	
	private void depuraCandidatosFueraDeVigencia() throws PersistenceException {
		List<Long> listaCandidatosFueraDeVigencia;
		try {
			listaCandidatosFueraDeVigencia = candidatoAppService.consultaIdCandidatosFueraDeVigencia();
			if (listaCandidatosFueraDeVigencia!=null && !listaCandidatosFueraDeVigencia.isEmpty()){
				logger.info("Se depuraran "+ listaCandidatosFueraDeVigencia.size() +" candidatos fuera de vigencia");
				
				for(long idCandidato : listaCandidatosFueraDeVigencia){
					logger.info("Se desactivará candidato "+ idCandidato);
					//TODO: descomentar en QA comentar en Prod
					candidatoAppService.desactivarCandidato(idCandidato, Constantes.ID_USUARIO_ANONIMO, Constantes.MOTIVO_DESACTIVACION_CANDIDATO.POR_VIGENCIA.getIdMotivo(), Constantes.MOTIVO_DESACTIVACION_CANDIDATO.POR_VIGENCIA.getMotivoDesc());							
				}
			}				
		} catch (SQLException e) {
			logger.error(e);
		}
	}
	
	
	private void avisarCandidatosFueraDeVigencia() throws PersistenceException {
		List<CandidatoVo> listaCandidatosAvisoDeVigencia;
		try {
			listaCandidatosAvisoDeVigencia = candidatoAppService.consultaCandidatosAvisoDeVigencia();
			
			if (listaCandidatosAvisoDeVigencia!=null && !listaCandidatosAvisoDeVigencia.isEmpty()){
				logger.info("Se mandará aviso de fuera de vigencia a "+ listaCandidatosAvisoDeVigencia.size() +" candidatos");
				
				for(CandidatoVo candidato : listaCandidatosAvisoDeVigencia){
					logger.info("Se envía notificacion al candidato "+ candidato.getIdCandidato());
					//TODO descomentar en QA comentar en prod
					candidatoAppService.notificaCandidatoFueraDeVigencia(candidato);								
				}
			}		
			
		} catch (SQLException e) {
			logger.error(e);
			/* DESCOMENTAR EN QA, COMENTAR EN PROD */
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error(e);
		} catch (TechnicalException e) {
			e.printStackTrace();
			logger.error(e);
		} catch (MailException e) {
			e.printStackTrace();
			logger.error(e);
			
		}		
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
		logger.info("Primera ejecución de DEPURADOR DE CANDIDATOS POR VIGENCIA : "+ date.toString());
		return date;
	}	
	
}