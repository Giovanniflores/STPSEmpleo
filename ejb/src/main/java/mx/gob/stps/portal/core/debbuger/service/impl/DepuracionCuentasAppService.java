package mx.gob.stps.portal.core.debbuger.service.impl;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.SimpleTimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

import mx.gob.stps.portal.core.debbuger.service.DepuracionCuentasAppServiceRemote;
import mx.gob.stps.portal.core.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.core.infra.utils.Utils;

import org.apache.log4j.Logger;

@Stateless(name = "DepuracionCuentasAppService", mappedName = "DepuracionCuentasAppService") 
public class DepuracionCuentasAppService   implements DepuracionCuentasAppServiceRemote, DepuracionCuentasAppServiceLocal{
	
	private static Logger logger = Logger.getLogger(DepuracionCuentasAppService.class);
	@Resource TimerService timerService;
	private @Resource SessionContext context;
	private final PropertiesLoader properties = PropertiesLoader.getInstance();
	private static final String TIMER_SCHEDULE_DEFAULT = "12:00";
	private int hora = 0;
	private int  minuto = 0;
	private long numminutos = 0;
	private static final int INTERVAL_IN_MINUTES = 1440;

		/*
	public void initTimer() { 
    	
    	String time = null;
    	        	
    	time=properties.getProperty("schedule.process.depuracion.time");    	
    	String [] atime = new String[2];
        	    	
    	if(time != null && validateTime(time)){    		    		    		     
    			atime = time.split(":");
    			hora= Integer.parseInt(atime[0]);
    			minuto = Integer.parseInt(atime[1]);          	
    	}else{
    		logger.error("No existe fecha asignada o no es valida en el proceso de depuracion de cuentas, " +
    				"se ejacutara en la hora por default");
    		atime=TIMER_SCHEDULE_DEFAULT.split(":");
    		hora= Integer.parseInt(atime[0]);
			minuto = Integer.parseInt(atime[1]);			
    	}    	
    	
    	
    	Date date = getDateTimerExecute();
     	//Timer timer = timerService.createTimer(date ,"nuevo");
     	
		long intervalDuration = INTERVAL_IN_MINUTES*60*1000;  
		Timer timer = timerService.createTimer(date,intervalDuration,"nuevo");     	
    	
    	
    	logger.info("Primera fecha para correr DEPURADOR DE CUENTAS DE USUARIOS : " + timer.getNextTimeout());
    	    	    	
    }
    
	
    public void doAction() {    	
    	
    	
    		Date date = getDateTimerExecute();
    		Timer timer = timerService.createTimer(date ,"nuevo");
    	
    	logger.info("Proxima ejecucion de DEPURADOR DE CUENTAS DE USUARIOS : " + timer.getNextTimeout());                    	   
    }
  
    @Timeout
    public void timeout(Timer timer) {    	   
    	System.out.println("TIMEOUT METHOD CALLED");
    	doAction();		    	
    }
	*/
	
	
    /*Proceso de depuracion de cuentas*/
	public void doAction() {
		SimpleTimeZone tz = new SimpleTimeZone(-21600000, "America/Mexico_City", 3, 1, -1, 7200000, 9, -1, 1, 7200000, 3600000);
		GregorianCalendar cal2 = new GregorianCalendar(tz);	
		System.out.println("....... corre depuracion de cuentas :" + cal2.getTime());
		/*
		ConexionFactory factory = ConexionFactory.getInstance();
		Connection globalConnection =  null;

		try {
			globalConnection = ConexionFactory.getInstance().getConnectionStpsEmpleo();
			DepuracionCuentasDAO dao = DepuracionCuentasDAO.getInstanceConnectionGlobal(globalConnection);

			int numRegDelCandIdioma = -1;
			int numRegDelCandGradAcad = -1;
			int numRegDelCandConocHab = -1;
			int numRegDelCandCompAvan = -1;
			int numRegDelCand = -1;
			int numRegDelUsuarioCand = -1;

			int numRegEmpresa = -1;
			int numRegUsuariosEmp = -1;

	
			//Elimina las cuentas cuentas de usuario de candidatos que caducan
			//NUMERO_DIAS_CADUCAN_CUENTAS

			numRegDelCandIdioma = dao.deleteCandIdioma();
			numRegDelCandGradAcad = dao.deleteCandGradoAcademico();
			numRegDelCandConocHab = dao.deleteCandConocHab();
			numRegDelCandCompAvan = dao.deleteCandCompuAvanzada();
			numRegDelCand = dao.deleteCand();
			numRegDelUsuarioCand = dao.deleteUsuariosCand();

			//Elimina cuentas empresariales y relaciones			/
			numRegEmpresa = dao.deleteEmp();
			numRegUsuariosEmp = dao.deleteUsuariosEmp();
			

		} catch (SQLException e) {
			e.printStackTrace(); 
		} catch (Exception e) {
			e.printStackTrace(); 
		} finally {
			try{if (globalConnection!=null) globalConnection.close();}catch(Exception e){e.printStackTrace();}
		}
		*/
		
	}




	/*Elimina los timers agendados*/
	/*
	@Override
	public void destroyInitTimer() {
				
		Collection<Timer> timersCollection = timerService.getTimers();
		Iterator<Timer> iterator = timersCollection.iterator();

		while (iterator.hasNext()) {
			Timer timer = iterator.next();
			if (timer.getInfo().equals("nuevo")) {
				timer.cancel();				
			}
		}
		
	}
	*/

	/* Valida que el formato de fecha sea valido*/
	/*
	private boolean validateTime(final String time){
		 Pattern pattern;
		 Matcher matcher;	 		 
		 String spattern = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
		 pattern = Pattern.compile(spattern);
		 matcher = pattern.matcher(time);
		 return matcher.matches();
	 }
	 */
	
	
	/* Funcion que obtiene el siguiente timer para ejecutar	*/ 	 	
	/*
	private Date getDateTimerExecute(){		
		
		
		int minu = Integer.parseInt(properties.getProperty("schedule.process.depuracion.perido.minutos"));  
		 Calendar calendar = Calendar.getInstance();
		 
		 if (calendar.get(Calendar.HOUR_OF_DAY) > hora
                 || (calendar.get(Calendar.HOUR_OF_DAY) == hora
                 && calendar.get(Calendar.MINUTE) > minuto)
                 || (calendar.get(Calendar.HOUR_OF_DAY) == hora
                 && calendar.get(Calendar.MINUTE) == minuto                
                 )) 
		 {
			 
             //calendar.add(Calendar.DAY_OF_MONTH, 1);			 
			 //calendar.set(Calendar.HOUR_OF_DAY, hora);
             //calendar.set(Calendar.MINUTE, minuto);			 
			 calendar.add(Calendar.MINUTE, minu);
			 
			 System.out.println("....1");
    	 }else{
    		 calendar.set(Calendar.HOUR_OF_DAY, hora);
             calendar.set(Calendar.MINUTE, minuto);
             calendar.set(Calendar.SECOND, 0);
             System.out.println("....2");
    	 }		 
		 Date date = calendar.getTime( );		 
		 return  date;
	}
	*/
	
	public void initTimer() {
		shutDownTimer();

		PropertiesLoader properties = PropertiesLoader.getInstance();
		String time = properties.getProperty("schedule.process.depuracion.time");
	
		int hora = 0;
		int minuto = 0;
		String TIMER_SCHEDULE_DEFAULT = "01:00";
		
		if (time != null && validateTime(time)) {
			String[] atime = time.split(":");
			hora = Integer.parseInt(atime[0]);
			minuto = Integer.parseInt(atime[1]);
		} else {
			logger.error("No existe fecha asignada o no es valida en el proceso de depuracion de cuentas, se ejacutara en la hora por default");
			String[] atime = TIMER_SCHEDULE_DEFAULT.split(":");
			hora = Integer.parseInt(atime[0]);
			minuto = Integer.parseInt(atime[1]);
		}

		logger.info("Hora:"+hora+" minutos:"+minuto);		
		//long intervalDuration = 1440 * 60 * 1000; // Un dia		
		long intervalDuration = Utils.parseLong(properties.getProperty("schedule.process.depuracion.perido.minutos")) * 60 * 1000;  
		Date date = getDateTimerExecute(hora, minuto);
		Timer timer = timerService.createTimer(date,intervalDuration, "nuevo");

		logger.info("Primera fecha para correr DEPURADOR DE CUENTAS DE EMPLEO POR VIGENCIA : "+ timer.getNextTimeout());
	}

	@Timeout
	public void timeout(Timer timer) {
		try {
			logger.info("----------> Inicia proceso de Depuracion de Cuentas de Empleo vencidas......");
			doAction();
			logger.info("----------> Final proceso de Depuracion de Cuentas de Empleo vencidas......");
		} catch (Exception be) {
			logger.error(be);
		}

	}
	
	/**
	 * Apagan los timers existentes
	 */
	private void shutDownTimer() {
		@SuppressWarnings("unchecked")
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
		logger.debug("Primera ejecución de depurador de cuentas: "+date.toString());
		return date;
	}
	
	
	
	
	
	
	
	
	
	
}
