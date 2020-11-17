package mx.gob.stps.portal.core.candidate.reporteInfonavit.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.Format;
import java.text.SimpleDateFormat;
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

import mx.gob.stps.portal.core.candidate.reporteInfonavit.service.ReporteInfonavitServiceRemote;
import mx.gob.stps.portal.core.candidate.service.CandidatoAppServiceLocal;
import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.mail.service.NotificacionService;
import mx.gob.stps.portal.core.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.core.infra.utils.Utils;

import org.apache.log4j.Logger;

@Stateless(name = "ReporteInfonavitService", mappedName = "ReporteInfonavitService")
@TransactionManagement(TransactionManagementType.BEAN)
public class ReporteInfonavitService implements ReporteInfonavitServiceRemote {

	private static Logger logger = Logger.getLogger(ReporteInfonavitService.class);

	@EJB
	private CandidatoAppServiceLocal candidatoAppService;

	private static final String TIMER_NAME = "ReporteInfonavitTimer";
	
	private static final String DELIMITADOR_ARCHIVO =",";
	
	private static final String NO_REGISTRO = "NO EXISTE REGISTRO EN BD";

	private PropertiesLoader properties = PropertiesLoader.getInstance();

	@Resource
	private TimerService timerService;

	public void initTimer() {

		shutDownTimer();

		String time = properties.getProperty("schedule.process.reporteinfonavit.time");

		 int hora = 1;
		 int minuto = 0;
		
		 if (time != null && validateTime(time)) {
		 String[] atime = time.split(":");
		 hora = Integer.parseInt(atime[0]);
		 minuto = Integer.parseInt(atime[1]);
		 }
		
		 logger.info("Hora:"+hora+" minutos:"+minuto);
		
		 long intervalDuration = (1440 * 60 * 1000) * 5; // Un dia
		 Date date = getDateTimerExecute(hora, minuto);
		 Timer timer = timerService.createTimer(date,intervalDuration,TIMER_NAME);

		 logger.info("Primera fecha para correr ENVIO DE OFERTAS SMS : "+ timer.getNextTimeout());
		 logger.info("assad");
	}

	@Timeout
	public void timeout(Timer timer) {
		List<CandidatoVo> canidatoList = null;
		try {
			logger.info("****************************************** Inicio consulta de Candidatos registrados con un Numero de seguro o crédito infonavit : "
					+ Utils.formatTime(Calendar.getInstance().getTime()));
			
			canidatoList = candidatoAppService.consultaCandidatoInfonavit();
			
			logger.info("****************************************** Final consulta de Candidatos registrados con un Numero de seguro o crédito infonavit : "
					+ Utils.formatTime(Calendar.getInstance().getTime()));

			logger.info("****************************************** Enviando correo de Candidatos a INFONAVIT : "
					+ Utils.formatTime(Calendar.getInstance().getTime()));
			 
			enviaCorreoInfonavit(canidatoList);
			
			 logger.info("****************************************** Finaliza envio correo de Candidatos a INFONAVIT : "+
			 Utils.formatTime(Calendar.getInstance().getTime()));

		} catch (Exception e) {
			logger.error(e);
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
			if (timer.getInfo().equals(TIMER_NAME)) {
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
		
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY); // TODO ESTABLECER EL DIA DE LA SEMANA CUANDO SERA EJECUTADO
		//calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));
		
		// calendar.set(Calendar.DAY_OF_MONTH,
		// calendar.get(Calendar.DAY_OF_MONTH)+1 );
		Date date = calendar.getTime();
		logger.debug("Primera ejecución de envio reporte infonavit: "+ date.toString());
		return date;
	}

	private File getMensajeCorreo(List<CandidatoVo> canidatoList) {
		Format formatter = new SimpleDateFormat("dd_MMM_yyyy_HH_mm_ss");
		
		Writer output = null;
		Date date = new Date();
		
		String fechaReporte = formatter.format(date);
		String rutaReporte =  properties.getProperty("reporte.infonavit.ruta");
		
		File dir = new File(rutaReporte);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		String nombreArchivo = rutaReporte + "RepINFONAVIT_" + fechaReporte  + ".csv";
		
		File file = new File(nombreArchivo);
		
		FileWriter fw = null;
	
		char retornochar = (char)13; 
		char saltochar = (char)10;
		
		try {
			fw = new FileWriter(file);
			output = new BufferedWriter(fw);
			  		  
			output.write("Número de Seguro Social" + DELIMITADOR_ARCHIVO);
			output.write("Número de Créditol" + DELIMITADOR_ARCHIVO);
			output.write("Nombre (s)" + DELIMITADOR_ARCHIVO);
			output.write("Primer Apellido" + DELIMITADOR_ARCHIVO);
			output.write("Segundo Apellido" + DELIMITADOR_ARCHIVO);
			output.write("Correo Electrónico" + DELIMITADOR_ARCHIVO);
			output.write("Domicilio" + DELIMITADOR_ARCHIVO);				  
			output.write("Colinia" + DELIMITADOR_ARCHIVO);
			output.write("C.P." + DELIMITADOR_ARCHIVO);
			output.write("Entidad Federativa" + DELIMITADOR_ARCHIVO);
			output.write("Municipio" + DELIMITADOR_ARCHIVO);
			output.write("Tipo de teléfono" + DELIMITADOR_ARCHIVO);
			output.write("Teléfono" + DELIMITADOR_ARCHIVO);
			output.write("Medio de Contacto" + DELIMITADOR_ARCHIVO);
			output.write("Entidad de Nacimiento" + DELIMITADOR_ARCHIVO);
			output.write("Fecha de Nacimiento" + DELIMITADOR_ARCHIVO);
			output.write("Datos Personales Condidenciales" + DELIMITADOR_ARCHIVO);
			output.write("Grado" + DELIMITADOR_ARCHIVO);
			output.write("Carrera o Especialidad" + DELIMITADOR_ARCHIVO);
			output.write("Situación Académica" + DELIMITADOR_ARCHIVO);
			output.write("Experiencia Total" + DELIMITADOR_ARCHIVO);
			output.write("Area laboral deseada" + DELIMITADOR_ARCHIVO);
			output.write("Tipo de Empleo" + DELIMITADOR_ARCHIVO);
			output.write("Salario Pretendido" + DELIMITADOR_ARCHIVO);
			output.write("¿Por qué busca empleo?" + retornochar + saltochar + retornochar + saltochar);
		  
			  for(CandidatoVo candidatoVo : canidatoList){
				  if(candidatoVo.getNss() != null)
					  output.write(Utils.quitarComas(candidatoVo.getNss()) + DELIMITADOR_ARCHIVO);
				  else
					  output.write(NO_REGISTRO + DELIMITADOR_ARCHIVO);
				  
				  
				  if(candidatoVo.getCreditoInfonavit() != null)
					  output.write(Utils.quitarComas(candidatoVo.getCreditoInfonavit().toString()) + DELIMITADOR_ARCHIVO);
				  else
					  output.write(NO_REGISTRO + DELIMITADOR_ARCHIVO);
				  
				  if(candidatoVo.getNombre() != null)
					  output.write(Utils.quitarComas(candidatoVo.getNombre()) + DELIMITADOR_ARCHIVO);
				  else
					  output.write(NO_REGISTRO + DELIMITADOR_ARCHIVO);
				  
				  if(candidatoVo.getApellido1() != null)
					  output.write(Utils.quitarComas(candidatoVo.getApellido1()) + DELIMITADOR_ARCHIVO);
				  else
					  output.write(NO_REGISTRO + DELIMITADOR_ARCHIVO);
				  
				  if(candidatoVo.getApellido2() != null)
					  output.write(Utils.quitarComas(candidatoVo.getApellido2()) + DELIMITADOR_ARCHIVO);
				  else
					  output.write(NO_REGISTRO + DELIMITADOR_ARCHIVO);
				  
				  if(candidatoVo.getCorreoElectronico() != null)
					  output.write(Utils.quitarComas(candidatoVo.getCorreoElectronico()) + DELIMITADOR_ARCHIVO);
				  else
					  output.write(NO_REGISTRO + DELIMITADOR_ARCHIVO);
				  
				  if(candidatoVo.getDomicilioVo().getDomiciloCompletoString() != null)
					  output.write(Utils.quitarComas(candidatoVo.getDomicilioVo().getDomiciloCompletoString()) + DELIMITADOR_ARCHIVO );
				  else
					  output.write(NO_REGISTRO + DELIMITADOR_ARCHIVO);
				  
				  if(candidatoVo.getDomicilioVo().getColonia() != null)
					  output.write(Utils.quitarComas(candidatoVo.getDomicilioVo().getColonia()) + DELIMITADOR_ARCHIVO);
				  else
					  output.write(NO_REGISTRO + DELIMITADOR_ARCHIVO);
				  
				  if(candidatoVo.getDomicilioVo().getCodigoPostal() != null)
					  output.write(Utils.quitarComas(candidatoVo.getDomicilioVo().getCodigoPostal()) + DELIMITADOR_ARCHIVO);
				  else
					  output.write(NO_REGISTRO + DELIMITADOR_ARCHIVO);
				  
				  if(candidatoVo.getDomicilioVo().getEntidadFederativaString() != null)
					  output.write(Utils.quitarComas(candidatoVo.getDomicilioVo().getEntidadFederativaString()) + DELIMITADOR_ARCHIVO);
				  else
					  output.write(NO_REGISTRO + DELIMITADOR_ARCHIVO);
				  
				  if(candidatoVo.getDomicilioVo().getMunicipio() != null)
					  output.write(Utils.quitarComas(candidatoVo.getDomicilioVo().getMunicipio()) + DELIMITADOR_ARCHIVO);
				  else
					  output.write(NO_REGISTRO + DELIMITADOR_ARCHIVO);
				  
				  if(candidatoVo.getTelefonoVo().getTipoTelefonoString() != null)
					  output.write(Utils.quitarComas(candidatoVo.getTelefonoVo().getTipoTelefonoString()) + DELIMITADOR_ARCHIVO);
				  else
					  output.write(NO_REGISTRO + DELIMITADOR_ARCHIVO);
				  
				  if(candidatoVo.getTelefonoVo().getTelefono() != null)
					  output.write(Utils.quitarComas(candidatoVo.getTelefonoVo().getTelefono()) + DELIMITADOR_ARCHIVO);
				  else
					  output.write(NO_REGISTRO + DELIMITADOR_ARCHIVO);
				  
				  if(candidatoVo.getMedioContacto() != null)
					  output.write(Utils.quitarComas(candidatoVo.getMedioContacto()) + DELIMITADOR_ARCHIVO);
				  else
					  output.write(NO_REGISTRO + DELIMITADOR_ARCHIVO);
				  
				  if(candidatoVo.getEntidadNacimiento() != null)
					  output.write(Utils.quitarComas(candidatoVo.getEntidadNacimiento()) + DELIMITADOR_ARCHIVO);
				  else
					  output.write(NO_REGISTRO + DELIMITADOR_ARCHIVO);
				  
				  if(candidatoVo.getFechaNacimientoString() != null)
					  output.write(Utils.quitarComas(candidatoVo.getFechaNacimientoString()) + DELIMITADOR_ARCHIVO);
				  else
					  output.write(NO_REGISTRO + DELIMITADOR_ARCHIVO);
				  
	
				  if(candidatoVo.getConfidencialidadDatos() == 1)
						output.write("No" + DELIMITADOR_ARCHIVO);
				  else
						output.write("Sí" + DELIMITADOR_ARCHIVO);
					
				  if(candidatoVo.getGradoacademicoVO().getGradoAcademico() != null)
					  output.write(Utils.quitarComas(candidatoVo.getGradoacademicoVO().getGradoAcademico()) + DELIMITADOR_ARCHIVO);
				  else
					  output.write(NO_REGISTRO + DELIMITADOR_ARCHIVO);
				  
				  if(candidatoVo.getGradoacademicoVO().getEspecialidad() != null)
					  output.write(Utils.quitarComas(candidatoVo.getGradoacademicoVO().getEspecialidad()) + DELIMITADOR_ARCHIVO);
				  else
					  output.write(NO_REGISTRO + DELIMITADOR_ARCHIVO);
				  
				  if(candidatoVo.getGradoacademicoVO().getSituacionAcademicaString() != null)
					  output.write(Utils.quitarComas(candidatoVo.getGradoacademicoVO().getSituacionAcademicaString()) + DELIMITADOR_ARCHIVO);
				  else
					  output.write(NO_REGISTRO + DELIMITADOR_ARCHIVO);
				  
				  if(candidatoVo.getGradoacademicoVO().getExperienciaString() != null)
					  output.write(Utils.quitarComas(candidatoVo.getGradoacademicoVO().getExperienciaString()) + DELIMITADOR_ARCHIVO);
				  else
					  output.write(NO_REGISTRO + DELIMITADOR_ARCHIVO);
				  
				  if(candidatoVo.getGradoacademicoVO().getAreaLaboralString() != null)
					  output.write(Utils.quitarComas(candidatoVo.getGradoacademicoVO().getAreaLaboralString()) + DELIMITADOR_ARCHIVO);
				  else
					  output.write(NO_REGISTRO + DELIMITADOR_ARCHIVO);
				  
				  if(candidatoVo.getGradoacademicoVO().getTipoEmpleoString() != null)
					  output.write(Utils.quitarComas(candidatoVo.getGradoacademicoVO().getTipoEmpleoString()) + DELIMITADOR_ARCHIVO);
				  else
					  output.write(NO_REGISTRO + DELIMITADOR_ARCHIVO);
				  
				  if(candidatoVo.getGradoacademicoVO().getSalarioPretendido() != null && candidatoVo.getGradoacademicoVO().getSalarioPretendido() > 0)
					  output.write(Utils.quitarComas(candidatoVo.getGradoacademicoVO().getSalarioPretendido()) + DELIMITADOR_ARCHIVO);
				  else
					  output.write(NO_REGISTRO + DELIMITADOR_ARCHIVO);
				  
				  if(candidatoVo.getGradoacademicoVO().getPorqueBuscaEmpleo() != null)
					  output.write(Utils.quitarComas(candidatoVo.getGradoacademicoVO().getPorqueBuscaEmpleo()) + retornochar + saltochar);
				  else
					  output.write(NO_REGISTRO  + retornochar + saltochar);
					
					
				}
			  
			  output.close();			
		} catch (IOException e) {
			e.printStackTrace(); logger.error(e);
		}

		return file;
	}
	
	private void enviaCorreoInfonavit(List<CandidatoVo> canidatoList) throws MailException {

		try {
			Integer totalCandidatos = new Integer(canidatoList.size()); 
			
			//Se genera el archivo CVS
			File file = getMensajeCorreo(canidatoList);
			
			Date fechaFinalRegistroCandidato = new Date();
			Date fechaInicialRegistroCandidato = fechaSemanaPasada(fechaFinalRegistroCandidato);

			NotificacionService notificacionService =  new NotificacionService();		
			notificacionService.notificacionInfonavit(totalCandidatos, fechaInicialRegistroCandidato, fechaFinalRegistroCandidato, file);

		} catch (Exception e) {logger.error(e);}
	}
	
	private Date fechaSemanaPasada(Date fechaActual){
		
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(fechaActual); 
		calendar.add(Calendar.DAY_OF_MONTH, -6 ); 
		Date fechaHaceUnaSemana = calendar.getTime();
		
		return fechaHaceUnaSemana;
	}
}
