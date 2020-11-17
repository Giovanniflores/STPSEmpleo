package mx.gob.stps.portal.core.oferta.envioSMS.service.impl;

import static mx.gob.stps.portal.core.infra.utils.Constantes.ID_PARAMETRO_ID_CANDIDATO_ULTIMO_SMS;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
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

import mx.gob.stps.portal.core.autorizacion.vo.ParametroVO;
import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.infra.mail.exception.MailServiceImpl;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorPerfilVO;
import mx.gob.stps.portal.core.oferta.envioSMS.service.OfertasSMSAppServiceRemote;
import mx.gob.stps.portal.core.oferta.envioSMS.vo.OfertasSMSVO;
import mx.gob.stps.portal.core.persistencia.facade.OfertaSmsFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.ParametroFacadeLocal;
import mx.gob.stps.portal.core.search.service.IndexerServiceLocator;

import org.apache.log4j.Logger;

/**
 * Session Bean implementation class OfertasSMSAppService
 */
@Stateless(name="OfertasSMSAppService", mappedName="OfertasSMSAppService")
@TransactionManagement(TransactionManagementType.BEAN)
public class OfertasSMSAppService implements OfertasSMSAppServiceRemote {
	private static Logger logger = Logger.getLogger(OfertasSMSAppService.class);
	private static String RUTA_DESTINO = PropertiesLoader.getInstance().getProperty(Constantes.PROPERTIES_RUTA_DESTINO_OFERTAS_SMS);
	private static String ARCHIVO_SMS_NOMBRE = PropertiesLoader.getInstance().getProperty(Constantes.PROPERTIES_ARCHIVO_SMS_NOMBRE);

	private static long counter = 0;

	private static final String TIMER_NAME = "OfertasSMSTimer";

	@EJB private OfertaSmsFacadeLocal ofertaSmsFacade;
	@EJB private ParametroFacadeLocal parametroFacade;
//	@EJB private OfertaCompatibilidadAppServiceLocal ofertaCompatibilidad;

	private PropertiesLoader properties = PropertiesLoader.getInstance();

	@Resource
	private TimerService timerService;	

	public void initTimer() {
		shutDownTimer();

		String time = properties.getProperty("schedule.process.ofertassms.time");

		int hora = 1;
		int minuto = 0;

		if (time != null && validateTime(time)) {
			String[] atime = time.split(":");
			hora = Integer.parseInt(atime[0]);
			minuto = Integer.parseInt(atime[1]);
		}

		logger.info("Hora:"+hora+" minutos:"+minuto);

		long intervalDuration = 1440 * 60 * 1000; // Un dia
		Date date = getDateTimerExecute(hora, minuto);
		Timer timer = timerService.createTimer(date,intervalDuration, TIMER_NAME);

		logger.info("Primera fecha para correr ENVIO DE OFERTAS SMS : "+ timer.getNextTimeout());
	}

	@Timeout
	public void timeout(Timer timer) {
		List<OfertasSMSVO> listaSMS = null;

		try {
			logger.info("****************************************** Inicia consulta de Ofertas SMS : "+ Utils.formatTime(Calendar.getInstance().getTime()));
			listaSMS = generaListaSMS();
			logger.info("****************************************** Final consulta de Ofertas SMS : "+ Utils.formatTime(Calendar.getInstance().getTime()));
		} catch (Exception e) {logger.error(e);}

		try {
			logger.info("****************************************** Enviando correo de ofertas por SMS : "+ Utils.formatTime(Calendar.getInstance().getTime()));
			enviaCorreoOfertas(listaSMS);
			logger.info("****************************************** Finaliza envio de correo de ofertas por SMS : "+ Utils.formatTime(Calendar.getInstance().getTime()));
		} catch (Exception e) {logger.error(e);}
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
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));
		//comentar en QA
		//calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+1);
		Date date = calendar.getTime();
		logger.debug("Primera ejecución de envio de ofertas sms: "+date.toString());
		return date;
	}

	private void enviaCorreoOfertas(List<OfertasSMSVO> listaSMS){
		if(listaSMS!=null && !listaSMS.isEmpty()){
			try {
				String remitente = properties.getProperty("email.remitente");
				//Comentar en QA 
				String[] to = {properties.getProperty("app.ofertasms.correo.to1"),properties.getProperty("app.ofertasms.correo.to2")};
				String[] cco = {properties.getProperty("app.ofertasms.correo.cco1"),properties.getProperty("app.ofertasms.correo.cco2")};
				File archivo = getMensajeCorreo(listaSMS);
				//Comentar en QA 
				//guardarArchivoFtp(archivo);
				MailServiceImpl mailService = MailServiceImpl.getInstance();
				//Comentar en QA 
				//mailService.enviarCorreo(remitente, to,null, cco, "Archivo envio de ofertas via SMS" ,"Archivo del dia "+new Date() , ARCHIVO_SMS_NOMBRE, archivo);
				mailService.enviarCorreo(remitente, cco,null, cco, "Archivo envio de ofertas via SMS" ,"Archivo del dia "+new Date() , ARCHIVO_SMS_NOMBRE, archivo);

			} catch (Exception e) {logger.error(e);}
		}
	}

	private void guardarArchivoFtp(File archivo) {
		StringBuffer sb = new StringBuffer( "ftp://" );
		sb.append(properties.getProperty("app.ofertasms.ftp.usuario"));
		sb.append( ':' );
		sb.append(properties.getProperty("app.ofertasms.ftp.pass"));
		sb.append( '@' );
		sb.append(properties.getProperty("app.ofertasms.ftp.host"));
		sb.append('/');
		sb.append(ARCHIVO_SMS_NOMBRE);
		sb.append(";type=i");

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try
		{
			URL url = new URL( sb.toString() );
			URLConnection urlc = url.openConnection();

			bos = new BufferedOutputStream( urlc.getOutputStream() );
			bis = new BufferedInputStream( new FileInputStream(archivo) );

			int i;
			// read byte by byte until end of stream
			while ((i = bis.read()) != -1)
			{
				bos.write( i );
			}
		} catch (MalformedURLException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}
		finally
		{
			if (bis != null)
				try
			{
					bis.close();
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
			if (bos != null)
				try
			{
					bos.close();
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
	}



	private List<Object[]> listaCandidatos(int numeroRegistros, int idCandidato) {
		List<Object[]> listaCandidatos = new ArrayList<Object[]>();

		try {
			logger.info(" ********************************** Inicia consulta de candidatos para envio de SMS, "+ Utils.formatTime(Calendar.getInstance().getTime()));
			listaCandidatos = ofertaSmsFacade.getListaCandidatos(idCandidato, numeroRegistros);
			logger.info(" ********************************** Finaliza consulta de candidatos para envio de SMS ("+ listaCandidatos.size() +"), "+ Utils.formatTime(Calendar.getInstance().getTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaCandidatos;
	}

	private List<OfertasSMSVO> generaListaSMS(){

		int MAX_REGISTROS = properties.getPropertyInt("schedule.process.ofertassms.max.registros");
		if (MAX_REGISTROS<=0) MAX_REGISTROS = 12000; // valor por defecto

		int cont=0;
		List<OfertasSMSVO> lista = new ArrayList<OfertasSMSVO>();

		int index = 0;

		while(cont<2){
			logger.info("Candidatos procesados : "+ lista.size());
			if(lista.size()<MAX_REGISTROS){

				ParametroVO parametro = parametroFacade.findById(ID_PARAMETRO_ID_CANDIDATO_ULTIMO_SMS);
				int idCandidato = Utils.parseInt(parametro.getValor());

				List<Object[]> candidatos = listaCandidatos(MAX_REGISTROS, idCandidato);

				if(candidatos.size()==0){
					parametroFacade.update(ID_PARAMETRO_ID_CANDIDATO_ULTIMO_SMS, String.valueOf(0));
					cont++;

					parametro = parametroFacade.findById(ID_PARAMETRO_ID_CANDIDATO_ULTIMO_SMS);
					idCandidato = Integer.parseInt(parametro.getValor());

					candidatos = listaCandidatos(MAX_REGISTROS, idCandidato);
				}

				for(Object[] rs: candidatos){

					String clave = Utils.toString(rs[2]); 
					String tel   = Utils.toString(rs[3]);

					if (clave==null || tel==null || clave.isEmpty() || tel.isEmpty()) {continue;}
					if (!Utils.esEntero(clave) || !Utils.esEntero(tel)) {continue;}
					if (Utils.toInt(clave)<=0 || Utils.toInt(tel)<=0) {continue;}
					if (clave.length()<2 || tel.length()<6 ) {continue;}

					if (index==MAX_REGISTROS) break;

					try{
						OfertasSMSVO sms = new OfertasSMSVO();
						sms.setIdCandidato (Utils.toInt(rs[0]));
						sms.setIdTelefono  (Utils.toInt(rs[1]));
						sms.setTelefono    (clave +""+ tel);
						sms.setIdEntidad   (Utils.toLong(rs[4]));
						sms.setEntidad     (Utils.toString(rs[5]));

						boolean validaRegistro = validaRegistro(sms);

						if(validaRegistro){
							index++;
							lista.add(sms);
						}

					} catch(Exception e){logger.error(e);}
				}

				if (index < MAX_REGISTROS && !candidatos.isEmpty()){
					Object[] ultimoObjCiclo = candidatos.get(candidatos.size()-1);

					if (ultimoObjCiclo!=null){
						int idCandidatoUltCiclo = Utils.toInt(ultimoObjCiclo[0]);
						parametroFacade.update(ID_PARAMETRO_ID_CANDIDATO_ULTIMO_SMS, String.valueOf(idCandidatoUltCiclo));
					}
				}

			}else{
				OfertasSMSVO ultimaOferta = lista.get(lista.size()-1);
				if (ultimaOferta!=null)
					parametroFacade.update(ID_PARAMETRO_ID_CANDIDATO_ULTIMO_SMS, String.valueOf(ultimaOferta.getIdCandidato()));
				break;
			}
		}

		return lista;
	}

	private boolean validaRegistro(OfertasSMSVO sms) {
		boolean validaRegistro = false;
		long idCandidato = sms.getIdCandidato();

		try{

			CandidatoVo candidatoVo = new CandidatoVo();
			candidatoVo.setIdCandidato(idCandidato);

			DomicilioVO domicilioCandidatoVo = new DomicilioVO();
			domicilioCandidatoVo.setEntidad(sms.getEntidad());
			candidatoVo.setDomicilioVo(domicilioCandidatoVo);

//			OfertaPorPerfilVO ofertarecomendada = ofertaCompatibilidad.buscaOfertaRecomendada(candidatoVo);
			OfertaPorPerfilVO ofertarecomendada = (OfertaPorPerfilVO) IndexerServiceLocator.getIndexerServiceRemote().buscaOfertasRecomendadas(idCandidato, 1);

			if (ofertarecomendada!=null){
				++counter;
				logger.info("["+ counter +"] SMSPRUEBA-IDCANDIDATO: "+idCandidato+"-OFERTAS COINCIDENTES: "+ ofertarecomendada.getIdOfertaEmpleo());

				boolean existe = ofertaSmsFacade.existeOfertaSms(candidatoVo.getIdCandidato(), ofertarecomendada.getIdOfertaEmpleo());
				if(!existe){
					if(ofertarecomendada.getMedioContacto()==null||ofertarecomendada.getMedioContacto().equals("")){
						validaRegistro=false;
					}else{
						validaRegistro = true;
						sms.setIdOfertaEmpleo(ofertarecomendada.getIdOfertaEmpleo());
						sms.setMensaje(getMensajeSMS(ofertarecomendada));

						ofertaSmsFacade.save(sms);
					}
				} else {
					logger.info("Oferta SMS ya notificada idCandidato "+ idCandidato +", idOfertaEmpleo "+ ofertarecomendada.getIdOfertaEmpleo());
				}
			}




		} catch(Exception e){
			logger.error(e);
		}
		return validaRegistro;
	}

	private String getMensajeSMS(OfertaPorPerfilVO oferta) {
		String mensaje = 
			"El Portal del Empleo te informa: Oferta:"+ oferta.getTituloOferta() +
			" Empresa:"+ oferta.getEmpresa() + 
			" Lugar:"+   oferta.getUbicacion() +
			" Contacto:"+ oferta.getMedioContacto();
		return mensaje;
	}

	private File getMensajeCorreo(List<OfertasSMSVO> listaSMS) {
		Writer output = null;
		File fi = new File(RUTA_DESTINO);
		if (!(fi.exists())) {
			fi.mkdirs();
		}
		File file = new File(RUTA_DESTINO, ARCHIVO_SMS_NOMBRE);
		FileWriter fw = null;

		char retornochar = (char)13; 
		char saltochar = (char)10;

		try {
			fw = new FileWriter(file);
			output = new BufferedWriter(fw);

			for (OfertasSMSVO sms: listaSMS){
				output.write(sms.getTelefono()+","+sms.getMensaje() + retornochar + saltochar);  
			}

			output.close();			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return file;
	}

}
