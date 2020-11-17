package mx.gob.stps.portal.core.infra.mail.service;

import static mx.gob.stps.portal.core.infra.utils.Constantes.SEPARADOR_PARAM;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.empresa.vo.EmpresaPorAutorizarVO;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.mail.exception.MailServiceImpl;
import mx.gob.stps.portal.core.infra.mail.exception.TemplateException;
import mx.gob.stps.portal.core.infra.mail.template.util.TemplateHTML;
import mx.gob.stps.portal.core.infra.mail.vo.DestinatarioVO;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.Plantilla;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.infra.vo.EntrevistaVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoADesactivarVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoVO;
import mx.gob.stps.portal.exception.TechnicalException;
import mx.gob.stps.portal.mail.template.FieldVO;
import mx.gob.stps.portal.mail.template.Template;
import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.utils.Catalogos.PLANTILLA_CORREO;
import mx.gob.stps.portal.utils.pdform.DataAccessToPEVO;
import mx.gob.stps.portal.utils.pdform.DataAccessToPePdfFormFiller;
import mx.gob.stps.portal.utils.pdform.PdfFormFiller;
import mx.gob.stps.portal.utils.pdform.RequirementsToPPCSDVO;
import mx.gob.stps.portal.utils.pdform.RequirementsToPpcSdPdfFormFiller;

import org.apache.log4j.Logger;

/**
 * Clase NotificacionService Lleva a cabo la coordinacion de los procesos para
 * el formateo y envio de la notificacion
 */
public class NotificacionService {
	private static Logger logger = Logger.getLogger(NotificacionService.class);

	private MailServiceImpl mailService = null;

	private final PropertiesLoader properties = PropertiesLoader.getInstance();

	public NotificacionService() {
		this.mailService = MailServiceImpl.getInstance();
	}

	public void notificacionTransferencia(String remitente, String asunto,
			DestinatarioVO destinatarioVO) throws MailException {
		notifica(TemplateHTML.getIntance(), Plantilla.HTML_VALIDA_CUENTA,
				remitente, asunto, destinatarioVO);
	}

	public void notificacionOfertaCandidato(DestinatarioVO destinatarioVO, String asunto) throws MailException {
		notifica(TemplateHTML.getIntance(), Plantilla.HTML_OFERTA_CANDIDATO,
				properties.getProperty("email.remitente"), asunto,
				destinatarioVO);
	}
	
	public void recordatorioRegistroEntrevista(PLANTILLA_CORREO plantilla, String from, String subject, String mailTo, List<FieldVO> fields) {
		String mensaje = "";
		String[] mailsTo = {mailTo};
		InputStream inTemplate 	= null;
		try {		
			inTemplate = this.getClass().getClassLoader().getResourceAsStream(plantilla.getPlantilla());			
			byte[] data = Utils.getBytes(inTemplate);
			inTemplate = new ByteArrayInputStream(data);
			byte[] datosConFormato;
			try {
				datosConFormato = Template.getIntance().aplicaPlantilla(fields, plantilla);
			} catch (mx.gob.stps.portal.mail.exception.TemplateException te) {
				throw new MailException("Error durante la generacion del cuerpo del correo.", te);
			}
			if (null != datosConFormato )
				mensaje = new String(datosConFormato, "UTF-8");
			mailService.enviarCorreo(from, mailsTo, subject, mensaje);
		} catch (Exception e) {
			logger.error(e); e.printStackTrace();
		}
	}

	public void notificacionToEmpresaPorAutorizar(
			EmpresaPorAutorizarVO empresaPorAutorizarVO, String passw)
			throws MailException {

		DestinatarioVO destinatarioVO = new DestinatarioVO();
		String remitente = properties.getProperty("email.remitente");
		String asunto = properties.getProperty("email.asunto");
		String remitenteCO = properties.getProperty("email.cc.remitente");
		String URL_VALIDACION = properties.getProperty("URL_VALIDACION");

		String argument = Utils.codifica(empresaPorAutorizarVO.getIdEmpresa()
				+ SEPARADOR_PARAM
				+ TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario()
				+ SEPARADOR_PARAM
				+ empresaPorAutorizarVO.getCorreoElectronico()
				+ SEPARADOR_PARAM + passw);

		if (empresaPorAutorizarVO.getEstatus() == ESTATUS.PREVALIDADA
				.getIdOpcion()) {
			destinatarioVO = getDestinatarioVO(empresaPorAutorizarVO, passw);
			destinatarioVO.setUrl(URL_VALIDACION + argument);
		}

		notifica(TemplateHTML.getIntance(), Plantilla.HTML_VALIDA_CUENTA,
				remitente, asunto, destinatarioVO);
	}

	public void notificacionCandidatoDesactivado(String candidatoNombre,
			OfertaCandidatoADesactivarVO vo) throws MailException {
		String remitente = properties.getProperty("email.remitente");
		String asunto = properties
				.getProperty("email.confirma.desactivacion.candidato.asunto");
		String mensaje = "";
		try {
			DestinatarioVO destinatarioVO = new DestinatarioVO();
			destinatarioVO.setNombre(Utils.sustituirPorEntidad(vo
					.getEmpresaNombre()));
			destinatarioVO.setNombreCandidato(Utils
					.sustituirPorEntidad(candidatoNombre));
			destinatarioVO.setEstatusOfertaCandidato(ESTATUS.getDescripcion(vo
					.getEstatus()));
			destinatarioVO.setTituloOfertaEmpleo(Utils.sustituirPorEntidad(vo
					.getTituloOferta()));
			destinatarioVO.setMensaje(mensaje);
			destinatarioVO.addMail(vo.getCorreoElectronicoContacto());
			notifica(TemplateHTML.getIntance(),
					Plantilla.HTML_NOTIFICA_CANDIDATO_DESACTIVADO, remitente,
					asunto, destinatarioVO);
		} catch (MailException e) {
			e.printStackTrace();
			logger.error(e);
		}
	}

	public void notificacionCandidatoEmpresaDesactivada(String nombreEmpresa,
			String tituloOferta, String candidatoNombre, String candidatoCorreo)
			throws MailException {
		String remitente = properties.getProperty("email.remitente");
		String asunto = properties
				.getProperty("email.empresa.desactivada.postulante.asunto");
		String mensaje = "";
		try {
			DestinatarioVO destinatarioVO = new DestinatarioVO();
			destinatarioVO.setMensaje(mensaje);
			destinatarioVO.setNombre(Utils.sustituirPorEntidad(nombreEmpresa));
			destinatarioVO.setTituloOfertaEmpleo(Utils
					.sustituirPorEntidad(tituloOferta));
			destinatarioVO.setNombreCandidato(Utils
					.sustituirPorEntidad(candidatoNombre));
			destinatarioVO.addMail(candidatoCorreo);
			notifica(TemplateHTML.getIntance(),
					Plantilla.HTML_NOTIFICA_CANDIDATO_EMPRESA_DESACTIVADA,
					remitente, asunto, destinatarioVO);
		} catch (MailException e) {
			e.printStackTrace();
			logger.error(e);
		}
	}

	public void notificaCandidatoFueraDeVigencia(CandidatoVo vo)
			throws MailException {
		String remitente = properties.getProperty("email.remitente");
		String asunto = properties
				.getProperty("email.notifica.candidato.proxima.desactivacion.asunto");
		String mensaje = "Mensaje de desactivacion";
		try {
			String candidatoNombre = vo.getNombre() + " " + vo.getApellido1();
			if (vo.getApellido2() != null
					&& !vo.getApellido2().equalsIgnoreCase("")) {
				candidatoNombre = candidatoNombre + " " + vo.getApellido2();
			}
			DestinatarioVO destinatarioVO = new DestinatarioVO();
			destinatarioVO.setNombreCandidato(Utils
					.sustituirPorEntidad(candidatoNombre));
			destinatarioVO.setMensaje(mensaje);
			destinatarioVO.addMail(vo.getCorreoElectronico());
			logger.info("-----notificaCandidatoFueraDeVigencia: remitente:"
					+ remitente + " asunto:" + asunto + " candidatoNombre:"
					+ candidatoNombre);
			notifica(TemplateHTML.getIntance(),
					Plantilla.HTML_NOTIFICA_CANDIDATO_PROXIMA_DESACTIVACION,
					remitente, asunto, destinatarioVO);
		} catch (MailException e) {
			e.printStackTrace();
			logger.error(e);
		}
	}

	public void notificacionPostuladoToEmpresa(EmpresaVO empresaVO,OfertaCandidatoVO ofertaCandidatoVO, String candidatoNombre,String candidatoEmail, String tituloOferta,
			String ofertaCorreoElectronicoContacto, String ofertaNombreEmpresa, byte[] buf, String pdfOutputName, boolean inscritoPPC) {
		
		String remitente = null;
		if (inscritoPPC){
			remitente = properties.getProperty("email.remitente.ppc");
		}else{
			remitente = properties.getProperty("email.remitente");			
		}
		String remitenteCO = properties.getProperty("email.cc.remitente");					
		String asunto = properties.getProperty("email.notifica.postulacion.empresa");
		String mensaje = "El Portal del Empleo le notifica que un candidato se ha postulado a una de sus ofertas de empleo:<br/>";
		List<byte[]> attachment = new ArrayList<byte[]>();
		attachment.add(buf);		
		String[] attachmentName = new String[1];
		attachmentName[0] = pdfOutputName;

		try {
			DestinatarioVO destinatarioVO = new DestinatarioVO();
			destinatarioVO.setNombre(Utils.sustituirPorEntidad(ofertaNombreEmpresa));
			destinatarioVO.setIdCandidato(String.valueOf(ofertaCandidatoVO.getIdCandidato()));
			destinatarioVO.setNombreCandidato(candidatoNombre);
			destinatarioVO.setFechaPostulacion(Utils.getFechaFormato(ofertaCandidatoVO.getFechaAlta()));
			destinatarioVO.setFolio(String.valueOf(ofertaCandidatoVO.getIdOfertaEmpleo()));
			destinatarioVO.setTituloOfertaEmpleo(tituloOferta);
			destinatarioVO.setMensaje(mensaje);
			destinatarioVO.setNombreContacto(empresaVO.getContactoEmpresa());
			if (inscritoPPC) destinatarioVO.setInscritoPPC("inscritoPPC");

			// destinatarioVO.addMail(empresaVO.getCorreoElectronico());
			destinatarioVO.addMail(ofertaCorreoElectronicoContacto);
			//Adjunta cv a la notificacion
			destinatarioVO.addAttachmentsAsByteArray(attachment, attachmentName);

			if (candidatoEmail != null && !candidatoEmail.trim().isEmpty())
				destinatarioVO.addMailCc(candidatoEmail);

			notifica(TemplateHTML.getIntance(), Plantilla.HTML_NOTIFICA_POSTULACION_PPC, remitente, asunto, destinatarioVO);

		} catch (MailException e) {
			e.printStackTrace();
			logger.error(e);
		}
	}

	public void notificacionToEmpresa(EmpresaVO empresaVO, String password)
			throws MailException {

		DestinatarioVO destinatarioVO = null;
		String remitente = properties.getProperty("email.remitente");
		String asunto = properties.getProperty("email.asunto");
		String remitenteCO = properties.getProperty("email.cc.remitente");
		String URL_VALIDACION = properties.getProperty("URL_VALIDACION");

		String argument = Utils.codifica(empresaVO.getIdEmpresa()
				+ SEPARADOR_PARAM
				+ TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario()
				+ SEPARADOR_PARAM + empresaVO.getCorreoElectronico()
				+ SEPARADOR_PARAM + password);

		// A4 Y A5
		destinatarioVO = getDestinatarioVO(empresaVO, password);
		destinatarioVO.setUrl(URL_VALIDACION + argument);
		notifica(TemplateHTML.getIntance(), Plantilla.HTML_VALIDA_CUENTA,
				remitente, asunto, destinatarioVO);

	}

	public void notificacionToNuevaEmpresa(String nombreEmpresa,
			String correoEmpresa, String password, String idPortalEmpleo)
			throws MailException {

		DestinatarioVO destinatarioVO = null;
		String remitente = properties.getProperty("email.remitente");
		String asunto = properties.getProperty("email.asunto");
		String remitenteCO = properties.getProperty("email.cc.remitente");
		// A4 Y A5
		destinatarioVO = getDestinatarioVO(nombreEmpresa, correoEmpresa,
				password, idPortalEmpleo);
		notifica(TemplateHTML.getIntance(),
				Plantilla.HTML_VALIDA_CUENTA_EMPRESA, remitente, asunto,
				destinatarioVO);
	}

	public DestinatarioVO armaDestinatarioEmpresa(EmpresaVO empresaVO,
			String password) {

		DestinatarioVO destinatarioVO = null;
		// String remitente = properties.getProperty("email.remitente");
		// String asunto = properties.getProperty("email.asunto");
		// String remitenteCO = properties.getProperty("email.cc.remitente");
		String URL_VALIDACION = properties.getProperty("URL_VALIDACION");

		String argument = Utils.codifica(empresaVO.getIdEmpresa()
				+ SEPARADOR_PARAM
				+ TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario()
				+ SEPARADOR_PARAM + empresaVO.getCorreoElectronico()
				+ SEPARADOR_PARAM + password);

		destinatarioVO = getDestinatarioVO(empresaVO, password);
		destinatarioVO.setUrl(URL_VALIDACION + argument);
		// comentado por aportal_empleo@stps.gob.mx
		// destinatarioVO.addMailCco(remitenteCO);

		return destinatarioVO;
	}

	public void notificacionRegistroCandidato(Date fechaAlta, String mail,
			String login, String password, int estatusPPC, String estado, String municipio, String nombre,
			String apellido1, String apellido2, String curp, String numeroSeguridadSocial) throws MailException {
		
		String remitente = properties.getProperty("email.remitente");
		String remitentePPC = "seguro_desempleo@stps.gob.mx";
		String asunto = properties.getProperty("email.asunto.registro.candidato");
		String remitenteCO = properties.getProperty("email.cc.remitente");

		Calendar fecha = Calendar.getInstance();
		fecha.setTime(fechaAlta);

		int dia = fecha.get(Calendar.DATE);
		int mes = fecha.get(Calendar.MONTH) + 1;
		int anio = fecha.get(Calendar.YEAR);

		
		try {
			DestinatarioVO destinatarioVO = new DestinatarioVO();
			destinatarioVO.setLogin(login);
			destinatarioVO.setPassword(password);

			destinatarioVO.setDia(dia < 10 ? "0" + dia : "" + dia);
			destinatarioVO.setMes(mes < 10 ? "0" + mes : "" + mes);
			destinatarioVO.setAnio("" + anio);
			
			destinatarioVO.addMail(mail);
			destinatarioVO.setUrlLogo(Constantes.URL_LOGO_PORTAL);
			
			
			StringBuilder nombreCompleto = new StringBuilder();
			nombreCompleto.append(nombre).append(" ").append(apellido1);
			
			if(null!=apellido2 && !apellido2.equalsIgnoreCase("")){
				nombreCompleto.append(" ").append(apellido2);

			}
			
			destinatarioVO.setNombreCandidato(nombreCompleto.toString());
			
			
			String[] attachmentNames = new String[2];
			attachmentNames[0] = DataAccessToPePdfFormFiller.DATA_ACCESS_TO_PE_PDF_SUGGESTED_OUTPUT_NAME;
						
			DataAccessToPEVO dataAccessToPEVO = new DataAccessToPEVO();
	        dataAccessToPEVO.setRegistrationDate(new Date());
	        dataAccessToPEVO.setUsername(login);
	        dataAccessToPEVO.setPassword(password);
	        
			InputStream pdfInputStream = DataAccessToPePdfFormFiller.obtainDataAccessToPeTemplatePdfAsInputStream();
			
			ByteArrayOutputStream salidaPdfDatosAutenticacion;
					
			salidaPdfDatosAutenticacion = PdfFormFiller.createPdfFormAndWriteIntoByteArray(pdfInputStream, dataAccessToPEVO.toPdfFormDataMap());
		
			 
			byte[] archivoDatosAutenticacion = salidaPdfDatosAutenticacion.toByteArray();
			
			ArrayList<byte[]> attachments = new ArrayList<byte[]>();  
			attachments.add(archivoDatosAutenticacion);
			
			if(estatusPPC == Catalogos.ESTATUS.ACTIVO_PPC.getIdOpcion()){
				
				attachmentNames[1] = RequirementsToPpcSdPdfFormFiller.REQUIREMENTS_TO_PPCSD_PDF_SUGGESTED_OUTPUT_NAME;
				

				RequirementsToPPCSDVO requirementsToPPCSDVO = new RequirementsToPPCSDVO();
		        requirementsToPPCSDVO.setAddressState(estado);
		        requirementsToPPCSDVO.setAddressMunicipality(municipio);
		        requirementsToPPCSDVO.setAcceptanceDateOfPPCSD(new Date());
		        requirementsToPPCSDVO.setFullName(nombreCompleto.toString());
		        requirementsToPPCSDVO.setCurp(curp);
		        requirementsToPPCSDVO.setNss(numeroSeguridadSocial);				
				
		        InputStream pdfTerminosInputStream = RequirementsToPpcSdPdfFormFiller.obtainRequirementsToPpcSdTemplatePdfAsInputStream();
		        
		        ByteArrayOutputStream salidaPdfTerminos;
		        
		        salidaPdfTerminos = PdfFormFiller.createPdfFormAndWriteIntoByteArray(pdfTerminosInputStream, requirementsToPPCSDVO.toPdfFormDataMap());
		        
		        byte[] archivoTerminos = salidaPdfTerminos.toByteArray();
		        
				attachments.add(archivoTerminos);
			}
			
			destinatarioVO.addAttachmentsAsByteArray(attachments, attachmentNames);
			
			if(estatusPPC == Catalogos.ESTATUS.ACTIVO_PPC.getIdOpcion()){				

				notifica(TemplateHTML.getIntance(), Plantilla.HTML_REGISTRO_CANDIDATO_CON_PPC,
						remitentePPC, asunto, destinatarioVO);			
				
			} else {

				notifica(TemplateHTML.getIntance(), Plantilla.HTML_REGISTRO_CANDIDATO,
						remitente, asunto, destinatarioVO);			
				
			}
			
			
			
			
		} catch (TechnicalException e) {
			e.printStackTrace(); logger.error(e);
		} catch (Exception e) {
			e.printStackTrace(); logger.error(e);
		}
	}

	public void notificarCandidato(Date fechaAlta, String mail,
			String nombreEmpresa, String tituloOferta, Boolean contratacion,
			String tipoPersona) throws MailException {
		String remitente = properties.getProperty("email.remitente");
		String asunto = null;
		if (contratacion) {
			asunto = properties
					.getProperty("email.asunto.candidato.contratado");
		} else {
			asunto = properties.getProperty("email.asunto.candidato.rechazado");
		}
		String remitenteCO = properties.getProperty("email.cc.remitente");

		Calendar fecha = Calendar.getInstance();
		fecha.setTime(fechaAlta);

		int dia = fecha.get(Calendar.DATE);
		int mes = fecha.get(Calendar.MONTH) + 1;
		int anio = fecha.get(Calendar.YEAR);

		DestinatarioVO destinatarioVO = new DestinatarioVO();
		destinatarioVO.setNombre(nombreEmpresa);
		destinatarioVO.setTituloOfertaEmpleo(tituloOferta);
		destinatarioVO.setTipoPersona(tipoPersona);

		destinatarioVO.setDia(dia < 10 ? "0" + dia : "" + dia);
		destinatarioVO.setMes(mes < 10 ? "0" + mes : "" + mes);
		destinatarioVO.setAnio("" + anio);

		destinatarioVO.addMail(mail);
		destinatarioVO.setUrlLogo(Constantes.URL_LOGO_PORTAL);

		if (contratacion) {
			notifica(TemplateHTML.getIntance(),
					Plantilla.HTML_NOTIFICA_CONTRATACION, remitente, asunto,
					destinatarioVO);
		} else {
			notifica(TemplateHTML.getIntance(),
					Plantilla.HTML_NOTIFICA_RECHAZO, remitente, asunto,
					destinatarioVO);
		}

	}

	public void notificarCandidatoVinculado(Date fechaAlta, String mail,
			String nombreEmpresa, String tituloOferta, String tipoPersona)
			throws MailException {
		String remitente = properties.getProperty("email.remitente");
		String asunto = properties
				.getProperty("email.asunto.candidato.vinculado");
		String remitenteCO = properties.getProperty("email.cc.remitente");

		Calendar fecha = Calendar.getInstance();
		fecha.setTime(fechaAlta);

		int dia = fecha.get(Calendar.DATE);
		int mes = fecha.get(Calendar.MONTH) + 1;
		int anio = fecha.get(Calendar.YEAR);

		DestinatarioVO destinatarioVO = new DestinatarioVO();
		destinatarioVO.setNombre(nombreEmpresa);
		destinatarioVO.setTituloOfertaEmpleo(tituloOferta);
		destinatarioVO.setTipoPersona(tipoPersona);

		destinatarioVO.setDia(dia < 10 ? "0" + dia : "" + dia);
		destinatarioVO.setMes(mes < 10 ? "0" + mes : "" + mes);
		destinatarioVO.setAnio("" + anio);

		destinatarioVO.addMail(mail);
		destinatarioVO.setUrlLogo(Constantes.URL_LOGO_PORTAL);

		notifica(TemplateHTML.getIntance(),
				Plantilla.HTML_NOTIFICA_VINCULACION, remitente, asunto,
				destinatarioVO);

	}

	public void notificacionRecomendacion(String remitente,
			String destinatario, String remitenteMail, String destinatarioMail,
			String asunto) throws MailException {

		DestinatarioVO destinatarioVO = new DestinatarioVO();

		destinatarioVO.addMail(destinatarioMail);
		destinatarioVO.setUrlLogo(Constantes.URL_LOGO_PORTAL);
		destinatarioVO.setNombreCandidato(destinatario);
		destinatarioVO.setNombre(remitente);
		destinatarioVO.setMensaje(asunto);

		notifica(TemplateHTML.getIntance(),
				Plantilla.HTML_NOTIFICA_RECOMENDACION, remitenteMail, asunto,
				destinatarioVO);
	}

	public void notificacionEmailToCandidato(CandidatoVo candidatoVo,
			String passw) throws MailException {
		String remitente = properties.getProperty("email.remitente");
		String asunto = properties.getProperty("email.asunto");
		String remitenteCO = properties.getProperty("email.cc.remitente");
		String URL_VALIDACION = properties.getProperty("URL_VALIDACION");

		// String tiporeg =
		// Integer.toString(TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
		String idCandidato = Long.toString(candidatoVo.getIdCandidato());
		// String passw = candidatoVo.getCorreoElectronico();
		candidatoVo.setPassword(passw);

		String argument = Utils.codifica(idCandidato + SEPARADOR_PARAM
				+ TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario()
				+ SEPARADOR_PARAM + candidatoVo.getCorreoElectronico()
				+ SEPARADOR_PARAM + passw);

		DestinatarioVO destinatarioVO = null;
		destinatarioVO = getDestinatarioVO(candidatoVo);
		destinatarioVO.setUrl(URL_VALIDACION + argument);

		destinatarioVO.setUrlLogo(Constantes.URL_LOGO_PORTAL);
		notifica(TemplateHTML.getIntance(), Plantilla.HTML_VALIDA_CUENTA,
				remitente, asunto, destinatarioVO);

	}

	public void notificacionInfonavit(int totalCandidatos,
			Date fechaInicialRegistroCandidato,
			Date fechaFinalRegistroCandidato, File file) throws MailException {
		String remitente = properties.getProperty("email.remitente");
		String mail = properties
				.getProperty("email.reporte.infonavit.destinatario");
		String mailCco = properties
				.getProperty("email.reporte.infonavit.concopia");
		String asunto = properties
				.getProperty("email.reporte.infonavit.asunto");

		/**
		 * Se ingresan los datos para la plantilla y se agrega el archivo
		 * adjunto, solo en caso de que se halla registrado algún candidato
		 * durante la semana
		 */
		DestinatarioVO destinatarioVO = new DestinatarioVO();
		destinatarioVO.setTotalCandidatosRegistrados(String
				.valueOf(totalCandidatos));
		destinatarioVO.setFechaInicialRegistroCandidato(Utils
				.getFechaFormato(fechaInicialRegistroCandidato));
		destinatarioVO.setFechaFinalRegistroCandidato(Utils
				.getFechaFormato(fechaFinalRegistroCandidato));

		if (totalCandidatos > 0) {
			destinatarioVO.addAttachment(file, file.getName());
		}

		destinatarioVO.addMail(mail);
		destinatarioVO.addMailCc(mail);
		destinatarioVO.addMailCco(mailCco);

		notifica(TemplateHTML.getIntance(), Plantilla.HTML_REPORTE_INFONAVIT,
				remitente, asunto, destinatarioVO);

	}

	public void notificacionRecuperacionPsw(EmpresaVO empresa,
			String correoElectronico, String password) throws MailException {
		notificacionRecuperacionPsw(empresa.getIdEmpresa(), "", 
				TIPO_PROPIETARIO.EMPRESA, empresa.getNombreEmpresa(),
				correoElectronico, password);
	}

	public void notificacionRecuperacionPswEmpresa(long idEmpresa, String usuario, String nombreEmpresa, String correoElectronico, String clave)
			throws MailException {
		notificacionRecuperacionPsw(idEmpresa, usuario, TIPO_PROPIETARIO.EMPRESA, nombreEmpresa, correoElectronico, clave);
	}

	public void notificacionRecuperacionPswMovilEmpresa(long idEmpresa,
			String nombreEmpresa, String correoElectronico, String clave, String usuario)
			throws MailException {
		notificacionRecuperacionPswMovil(idEmpresa, TIPO_PROPIETARIO.EMPRESA,
				nombreEmpresa, correoElectronico, clave, usuario);
	}
	
	public void notificacionRecuperacionPsw(CandidatoVo candidato,
			String correoElectronico, String password) throws MailException {
		notificacionRecuperacionPsw(candidato.getIdCandidato(), candidato.getUsuarioVO().getUsuario(),
				TIPO_PROPIETARIO.CANDIDATO,
				candidato.getNombre() + " " + candidato.getApellido1() + " "
						+ candidato.getApellido2(), correoElectronico, password);
	}

	private void notificacionRecuperacionPswMovil(long idPropietario,
			TIPO_PROPIETARIO tipoPropietario, String nombrePropietario,
			String correo, String password, String usuario)
			throws MailException {


			String remitente = properties.getProperty("email.remitente");
			String asunto = properties.getProperty("email.confirma.recuperacion.contrasena.asunto");
			String remitenteCO = properties.getProperty("email.cc.remitente");

			Calendar fecha = Calendar.getInstance();
			fecha.setTime(new Date());

			int dia = fecha.get(Calendar.DATE);
			int mes = fecha.get(Calendar.MONTH) + 1;
			int anio = fecha.get(Calendar.YEAR);

			DestinatarioVO destinatarioVO = new DestinatarioVO();
			destinatarioVO.setNombre(Utils.sustituirPorEntidad(nombrePropietario));			
			destinatarioVO.setLogin(usuario);
			destinatarioVO.setPassword(password);

			destinatarioVO.setDia(dia < 10 ? "0" + dia : "" + dia);
			destinatarioVO.setMes(mes < 10 ? "0" + mes : "" + mes);
			destinatarioVO.setAnio("" + anio);

			destinatarioVO.addMail(correo);
			destinatarioVO.setUrlLogo(Constantes.URL_LOGO_PORTAL);

			notifica(TemplateHTML.getIntance(), Plantilla.HTML_NOTIFICA_NEW_PASSWORD_EMPRESA,
					remitente, asunto, destinatarioVO);
		
	}

	private void notificacionRecuperacionPsw(long idPropietario, String usuario, 
			TIPO_PROPIETARIO tipoPropietario, String nombrePropietario,
			String correo, String password) throws MailException {

		String remitente = properties.getProperty("email.remitente");
		String asunto = properties.getProperty("email.confirma.recuperacion.contrasena.asunto");
		String URL_VALIDACION = properties.getProperty("url.recuperacion.contrasena");
		String remitenteCO = properties.getProperty("email.cc.remitente");

		String argument = "";/**Utils.codifica(idPropietario + SEPARADOR_PARAM
				+ tipoPropietario.getIdTipoPropietario() + SEPARADOR_PARAM
				+ correo + SEPARADOR_PARAM + password);**/
		Calendar fecha = Calendar.getInstance();
		fecha.setTime(new Date());
		int dia = fecha.get(Calendar.DATE);
		int mes = fecha.get(Calendar.MONTH) + 1;
		int anio = fecha.get(Calendar.YEAR);
		DestinatarioVO destinatarioVO = new DestinatarioVO();
		destinatarioVO.setNombre(Utils.sustituirPorEntidad(nombrePropietario));
		destinatarioVO.addMail(correo); // Posiblemente se cambio la cuenta, al
										// confirmar se actualiza el registro
		destinatarioVO.setLogin(usuario); // en caso de haber cambiado de cuenta
		destinatarioVO.setPassword(password);
		destinatarioVO.setDia(dia < 10 ? "0" + dia : "" + dia);
		destinatarioVO.setMes(mes < 10 ? "0" + mes : "" + mes);
		destinatarioVO.setAnio("" + anio);
		destinatarioVO.setUrl(URL_VALIDACION);
		notifica(TemplateHTML.getIntance(), Plantilla.HTML_NEW_PASSWORD,
				remitente, asunto, destinatarioVO);
	}
	
	//NOTIFICA RECUPERACION CONTRASENA OAM
	public void notificacionRecuperacionContrasena(long idPropietario, String usuario, 
			TIPO_PROPIETARIO tipoPropietario, String nombrePropietario,
			String correo, String urlcode) throws MailException {

		String remitente = properties.getProperty("email.remitente");
		String asunto = properties.getProperty("email.confirma.recuperacion.contrasena.asunto");
		String URL_VALIDACION = properties.getProperty("url.recuperacion.contrasena");
		String remitenteCO = properties.getProperty("email.cc.remitente");

		String argument = "contraseña";
		Calendar fecha = Calendar.getInstance();
		fecha.setTime(new Date());
		int dia = fecha.get(Calendar.DATE);
		int mes = fecha.get(Calendar.MONTH) + 1;
		int anio = fecha.get(Calendar.YEAR);
		DestinatarioVO destinatarioVO = new DestinatarioVO();
		destinatarioVO.setNombre(Utils.sustituirPorEntidad(nombrePropietario));
		destinatarioVO.addMail(correo); 
		destinatarioVO.setLogin(usuario);
		destinatarioVO.setUrl(URL_VALIDACION);
		destinatarioVO.setUrlcode(urlcode);
		destinatarioVO.setDia(dia < 10 ? "0" + dia : "" + dia);
		destinatarioVO.setMes(mes < 10 ? "0" + mes : "" + mes);
		destinatarioVO.setAnio("" + anio);
		destinatarioVO.setPrefijoNomArchivo(argument);
		notifica(TemplateHTML.getIntance(), Plantilla.HTML_RECUPERAR_PASSWORD,
				remitente, asunto, destinatarioVO);
	}

	public DestinatarioVO armaNotificacionRecuperacionPsw(long idPropietario,
			TIPO_PROPIETARIO tipoPropietario, String nombrePropietario,
			String correo, String usuario, String password) {

		if (correo == null)
			correo = "";

		// String remitente = properties.getProperty("email.remitente");
		// String asunto =
		// properties.getProperty("email.confirma.recuperacion.contrasena.asunto");
		String URL_VALIDACION = properties
				.getProperty("url.confirma.recuperacion.contrasena");
		String remitenteCO = properties.getProperty("email.cc.remitente");

		String argument = Utils.codifica(idPropietario + SEPARADOR_PARAM
				+ tipoPropietario.getIdTipoPropietario() + SEPARADOR_PARAM
				+ correo + SEPARADOR_PARAM + password);

		DestinatarioVO destinatarioVO = new DestinatarioVO();
		destinatarioVO.setNombre(Utils.sustituirPorEntidad(nombrePropietario));
		destinatarioVO.addMail(correo); // Posiblemente se cambio la cuenta, al
										// confirmar se actualiza el registro
		destinatarioVO.setLogin(usuario); // en caso de haber cambiado de cuenta
		destinatarioVO.setPassword(password);

		destinatarioVO.setUrl(URL_VALIDACION + argument);

		return destinatarioVO;
	}

	private void notifica(TemplateHTML template, Plantilla plantilla,
			String remitente, String asunto, DestinatarioVO destinatarioVO)
			throws MailException {

		InputStream inTemplate = null;
		try {
			System.out.println("----plantilla:" + plantilla.name());
			// obtiene el flujo hacia la plantilla			
			inTemplate = getStreamPlantilla(plantilla);			
			System.out.println(inTemplate == null ? "----inTemplate is null" : "----inTemplate is NOT null");			 
			notifica(template, inTemplate, remitente, asunto, destinatarioVO);

		} catch (IOException e) {
			logger.error(e);
			e.printStackTrace();
			throw new MailException(
					"Error durante la generación del cuerpo del correo.", e);
		}
	}

	/**
	 * Coordina la notificacion de correo, da formato a los datos, aplica la
	 * plantilla y envia en correo
	 * 
	 * @param template
	 *            Implementacion del componente que aplica la plantilla
	 * @param plantilla
	 *            Plantilla a aplicar
	 * @param remitente
	 *            Remitente del correo
	 * @param asunto
	 *            Asunto del correo
	 * @param destinatarioVO
	 *            Datos del destinatario que seran aplicados a la plantilla
	 * @throws MailException
	 */
	private void notifica(TemplateHTML template, InputStream plantilla,
			String remitente, String asunto, DestinatarioVO destinatarioVO)
			throws MailException {
		String mensaje = "";
		byte[] datosConFormato = null;
		destinatarioVO.setUrlLogo(Constantes.URL_LOGO_PORTAL);
		try {
			
			// Aplica la plantilla a los datos, se genera el cuerpo del correo
			datosConFormato = template.aplicaPlantilla(destinatarioVO, plantilla);
			/**
			 * SI ES UN ENVIO MASIVO, HAY QUE RESETEAR EL FLUJO HACIA LA
			 * PLANTILLA PARA QUE LA VUELVA A LEER
			 **/
			/**
			 * IMPORTANTE, EL METODO RESET ES VALIDO PARA UN INPUT PROVENIENTE
			 * DE UN ByteArrayInputStream
			 **/
			try {
				plantilla.reset();
			} catch (Exception e) {
				e.printStackTrace();
			}

			logger.info("Enviando Notificacion ["
					+ Arrays.toString(destinatarioVO.getMails()) + "]");

			// Se envia por correo en formato html
			if (datosConFormato != null) {
				mensaje = new String(datosConFormato, "UTF-8");
			}

			// Se verifica si se tiene un archivo adjunto al correo HTML
			if (!destinatarioVO.hasAttachment()) {
				mailService.enviarCorreo(remitente, destinatarioVO.getMails(),
						destinatarioVO.getMailsCc(),
						destinatarioVO.getMailsCco(), asunto, mensaje);
			} else {
				
				if(null!=destinatarioVO.getAttachmentNames() && destinatarioVO.getAttachmentNames().length>0
						&& null!=destinatarioVO.getAttachmentsAsByteArray() 
						&& !destinatarioVO.getAttachmentsAsByteArray().isEmpty()){
				
					mailService.enviarCorreo(remitente, destinatarioVO.getMails(),
							destinatarioVO.getMailsCc(),
							destinatarioVO.getMailsCco(), asunto, mensaje,
							destinatarioVO.getAttachmentNames(),
							destinatarioVO.getAttachmentsAsByteArray());					
					
				} else {

					mailService.enviarCorreo(remitente, destinatarioVO.getMails(),
							destinatarioVO.getMailsCc(),
							destinatarioVO.getMailsCco(), asunto, mensaje,
							destinatarioVO.getAttachmentName(),
							destinatarioVO.getAttachment());					
					
				}
			}

		} catch (TemplateException e) {
			logger.error(e);
			e.printStackTrace();
			throw new MailException(
					"Error durante la generación del cuerpo del correo.", e);
		} catch (MailException e) {
			logger.error(e);
			e.printStackTrace();
			throw new MailException("Error durante el envío del correo.", e);
		} catch (IOException e) {
			logger.error(e);
			e.printStackTrace();
			throw new MailException("Error durante el envío del correo.", e);
		}
	}

	/**
	 * @param plantilla
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private InputStream getStreamPlantilla(Plantilla plantilla)
			throws FileNotFoundException, IOException {
		ByteArrayInputStream in = null;
		InputStream inTemplate = null;

		inTemplate = this.getClass().getClassLoader().getResourceAsStream(plantilla.getArchivoPlantilla());
		byte[] data = Utils.getBytes(inTemplate);
		
		in = new ByteArrayInputStream(data);
		return in;
	}

	private DestinatarioVO getDestinatarioVO(String nombreEmpresa,
			String correoEmpresa, String password, String idPortalEmpleo) {
		DestinatarioVO destinatarioVO = new DestinatarioVO();
		destinatarioVO.setNombre(Utils.sustituirPorEntidad(nombreEmpresa));
		destinatarioVO.addMail(correoEmpresa);
		destinatarioVO.setLogin(correoEmpresa);
		destinatarioVO.setPassword(password);
		destinatarioVO.setIdPortalEmpleo(idPortalEmpleo);
		return destinatarioVO;
	}

	/**
	 * @param empresaVO
	 * @return
	 */
	private DestinatarioVO getDestinatarioVO(EmpresaVO empresaVO,
			String password) {
		DestinatarioVO destinatarioVO = new DestinatarioVO();
		destinatarioVO.setNombre(Utils.sustituirPorEntidad(empresaVO
				.getNombreEmpresa()));
		destinatarioVO.addMail(empresaVO.getCorreoElectronico());
		destinatarioVO.setLogin(empresaVO.getCorreoElectronico());
		destinatarioVO.setPassword(password);
		return destinatarioVO;
	}

	private DestinatarioVO getDestinatarioVO(CandidatoVo candidatoVo) {
		DestinatarioVO destinatarioVO = new DestinatarioVO();
		destinatarioVO
				.setNombre(candidatoVo.getNombre() + " "
						+ candidatoVo.getApellido1() + " "
						+ candidatoVo.getApellido2());
		destinatarioVO.addMail(candidatoVo.getCorreoElectronico());
		destinatarioVO.setLogin(candidatoVo.getCorreoElectronico());
		destinatarioVO.setPassword(candidatoVo.getPassword());

		return destinatarioVO;
	}

	private DestinatarioVO getDestinatarioVO(
			EmpresaPorAutorizarVO empresaPorAutorizarVO, String password) {
		DestinatarioVO destinatarioVO = new DestinatarioVO();
		destinatarioVO.setNombre(empresaPorAutorizarVO.getNombre());
		destinatarioVO.addMail(empresaPorAutorizarVO.getCorreoElectronico());
		destinatarioVO.setLogin(empresaPorAutorizarVO.getCorreoElectronico());
		destinatarioVO.setPassword(password);
		return destinatarioVO;
	}

	/**
	 * @param confirmacionRegistroVo
	 * @return
	 */
	private DestinatarioVO getDestinatarioVO(EntrevistaVO entrevistaVO) {
		DestinatarioVO destinatarioVO = new DestinatarioVO();
		destinatarioVO.setMensaje(entrevistaVO.getEmailMensaje());
		destinatarioVO.setNombre(null != entrevistaVO.getNombre() ? entrevistaVO.getNombre() : "");
		destinatarioVO.setNombreContacto(null != entrevistaVO.getContactoEmpresa() ? entrevistaVO.getContactoEmpresa() : destinatarioVO.getNombre());
		return destinatarioVO;
	}

	/**
	 * @param confirmacionRegistroVo
	 * @throws MailException
	 */
	public void notificacionEntrevista(EntrevistaVO entrevistaVO)
			throws MailException {

		final String EMPRESA = "Empresa";
		final String CANDIDATO = "Candidato";

		String remitente = properties.getProperty("email.remitente");
		String remitenteCO = properties.getProperty("email.cc.remitente");

		DestinatarioVO destinatarioVO = getDestinatarioVO(entrevistaVO);

		if (EMPRESA.equals(entrevistaVO.getTipoOperacion())) {
			destinatarioVO.addMail(entrevistaVO.getCorreo());
			notifica(TemplateHTML.getIntance(), Plantilla.HTML_ENTREVISTA,
					remitente, entrevistaVO.getAsunto(), destinatarioVO);
		}

		if (CANDIDATO.equals(entrevistaVO.getTipoOperacion())) {
			destinatarioVO.addMail(entrevistaVO.getCorreo());
			notifica(TemplateHTML.getIntance(), Plantilla.HTML_ENTREVISTA,
					remitente, entrevistaVO.getAsunto(), destinatarioVO);
		}

	}

	public void notificacionInscripcionPPCCandidato(String nombreCompleto, String mail, String curp, String numeroSeguridadSocial, Date fechaInscripcion, String estado, String municipio) throws MailException, TechnicalException{

		String remitente = properties.getProperty("email.remitente");
		String remitentePPC = "seguro_desempleo@stps.gob.mx";
		String asunto = properties.getProperty("email.asunto.ppc.inscripcion"); //srojas?
		String remitenteCO = properties.getProperty("email.cc.remitente");		
		
		try{

			DestinatarioVO destinatarioVO = new DestinatarioVO();

			destinatarioVO.setUrlLogo(Constantes.URL_LOGO_PORTAL);
			destinatarioVO.addMail(mail);
			destinatarioVO.setNombreCandidato(nombreCompleto);

			RequirementsToPPCSDVO requirementsToPPCSDVO = new RequirementsToPPCSDVO();
	        requirementsToPPCSDVO.setAddressState(estado);
	        requirementsToPPCSDVO.setAddressMunicipality(municipio);
	        requirementsToPPCSDVO.setAcceptanceDateOfPPCSD(fechaInscripcion);
	        requirementsToPPCSDVO.setFullName(nombreCompleto);
	        requirementsToPPCSDVO.setCurp(curp);
	        requirementsToPPCSDVO.setNss(numeroSeguridadSocial);							

			InputStream pdfTerminosInputStream = RequirementsToPpcSdPdfFormFiller.obtainRequirementsToPpcSdTemplatePdfAsInputStream();
			
	        ByteArrayOutputStream salidaPdfTerminos = PdfFormFiller.createPdfFormAndWriteIntoByteArray(pdfTerminosInputStream, requirementsToPPCSDVO.toPdfFormDataMap());		
	        byte[] archivoTerminos = salidaPdfTerminos.toByteArray();

			String[] attachmentNames = new String[1];
			attachmentNames[0] = RequirementsToPpcSdPdfFormFiller.REQUIREMENTS_TO_PPCSD_PDF_SUGGESTED_OUTPUT_NAME;

			ArrayList<byte[]> attachments = new ArrayList<byte[]>();
			attachments.add(archivoTerminos);

			destinatarioVO.addAttachmentsAsByteArray(attachments, attachmentNames);

			notifica(TemplateHTML.getIntance(), Plantilla.HTML_NOTIFICA_INSCRIPCIONPPC,
					remitente, asunto, destinatarioVO);			
		
		} catch (Exception e) {
			logger.error("Error en notificacionInscripcionPPCCandidato, mail: "+(mail != null ? mail : new String()));
			e.printStackTrace();
		}
	}
	
}