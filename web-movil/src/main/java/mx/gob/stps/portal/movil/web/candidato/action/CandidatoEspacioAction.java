package mx.gob.stps.portal.movil.web.candidato.action;

import static mx.gob.stps.portal.movil.web.infra.utils.ConstantesMovil.FORWARD_JSP;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.candidate.vo.ConocimientoComputacionVO;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.ENTIDADES_FEDERATIVAS;
import mx.gob.stps.portal.core.infra.utils.Constantes.GENERO;
import mx.gob.stps.portal.core.infra.vo.EntrevistaVO;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorPerfilVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaEmpleoJB;
import mx.gob.stps.portal.movil.web.candidato.delegate.CandidatoDelegateImpl;
import mx.gob.stps.portal.movil.web.candidato.form.CandidatoForm;
import mx.gob.stps.portal.movil.web.candidato.pdf.FormatCVElegant;
import mx.gob.stps.portal.movil.web.candidato.pdf.FormatCVSimple;
import mx.gob.stps.portal.movil.web.candidato.pdf.FormatCVYouth;
import mx.gob.stps.portal.movil.web.candidato.vo.CandidatoAjaxVO;
import mx.gob.stps.portal.movil.web.entrevista.delegate.EntrevistaDelegateImpl;
import mx.gob.stps.portal.movil.web.entrevista.vo.EntrevistaCandidatoVO;
import mx.gob.stps.portal.movil.web.infra.action.GenericAction;
import mx.gob.stps.portal.movil.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.movil.web.infra.utils.Utils;
import mx.gob.stps.portal.movil.web.oferta.delegate.OfertaDelegateImpl;
import mx.gob.stps.portal.movil.web.oferta.form.PerfilJB;
import mx.gob.stps.portal.movil.web.seguridad.vo.UsuarioFirmadoVO;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;


public class CandidatoEspacioAction extends GenericAction {

	private static Logger logger = Logger.getLogger(CandidatoEspacioAction.class);
	
	private static final String FORWARD_DATOS_BASICOS = "FORWARD_DATOS_BASICOS";
	
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		try {
			UsuarioFirmadoVO usuario = getUsuarioFirmado(session);
			long idCandidato = usuario.getIdPropietario();

			CandidatoForm candidatoForm = (CandidatoForm)form;
			
			CandidatoDelegateImpl candidatoServices = CandidatoDelegateImpl.getInstance();
			OfertaDelegateImpl ofertasServices = OfertaDelegateImpl.getInstance();
			EntrevistaDelegateImpl entrevistaServices = EntrevistaDelegateImpl.getInstance();
			
			CandidatoVo candidato = candidatoServices.buscarDatosHeaderTemplateCandidato(idCandidato);
			candidatoForm.setIdCandidato(idCandidato);
			candidatoForm.setCandidato(candidato);
			
			List<OfertaPorPerfilVO> ofertas = ofertasServices.buscaOfertasRecomendadas(idCandidato);

			//if (ofertas.size()>5) ofertas = ofertas.subList(0, 5);
			// TODO Eliminar
			/*OfertaPorPerfilVO oferta = new OfertaPorPerfilVO();
			oferta.setIdCandidato(idCandidato);
			oferta.setIdOfertaEmpleo(7263);
			oferta.setTituloOferta("Oferta recomendada");
			ofertas.add(oferta);*/

			/*oferta = new OfertaPorPerfilVO();
			oferta.setIdCandidato(idCandidato);
			oferta.setIdOfertaEmpleo(2);
			oferta.setTituloOferta("Prueba 02 de Oferta recomendada");
			ofertas.add(oferta);*/

			if (ofertas==null) ofertas = new ArrayList<OfertaPorPerfilVO>();
			candidatoForm.setOfertas(ofertas);

			List<OfertaEmpleoJB> misOfertas = ofertasServices.misOfertasEmpleo(idCandidato);
			
			if (misOfertas==null) misOfertas = new ArrayList<OfertaEmpleoJB>();
			candidatoForm.setMisOfertas(misOfertas);
			
			List<OfertaEmpleoJB> empresas = ofertasServices.empresasMeBuscanOfertas(idCandidato);
			
			if (empresas==null) empresas = new ArrayList<OfertaEmpleoJB>();
			candidatoForm.setEmpresas(empresas);

			EntrevistaVO entrevistaFiltros = new EntrevistaVO();	
			entrevistaFiltros.setIdCandidato(idCandidato);
			entrevistaFiltros.setIdUsuario(usuario.getIdUsuario());
			entrevistaFiltros.setCorreoCandidato(usuario.getCorreoElectronico());
			
			List<EntrevistaVO> entrevistas = entrevistaServices.getEntrevistaProgramadaCandidato(entrevistaFiltros);
			entrevistas = sobrecargaEntrevistas(entrevistas);
			
			if (entrevistas==null) entrevistas = new ArrayList<EntrevistaVO>();
			candidatoForm.setEntrevistas(entrevistas);

			PerfilJB perfilLaboral = candidatoServices.loadPerfil(idCandidato);
			if (perfilLaboral!=null) {
				perfilLaboral.setAniosExperiencia(Constantes.EXPERIENCIA.getDescripcion((int) perfilLaboral.getPerfilLaboral().getIdExperienciaTotal()));
				candidatoForm.setPerfilLaboral(perfilLaboral);
			}
			
			ConocimientoComputacionVO conocimientosComputacion = candidatoServices.findConocimientosComputacion(idCandidato);			
			
			candidatoForm.setConocimientoComputacion(conocimientosComputacion);
			
		} catch (ServiceLocatorException e) {
			logger.error(e); // TODO Reportar error
		} catch (Exception e) {
			logger.error(e); // TODO Reportar error
		}

		return mapping.findForward(FORWARD_JSP);
	}

	private List<EntrevistaVO> sobrecargaEntrevistas(List<EntrevistaVO> entrevistas){
		List<EntrevistaVO> entrevistascan = new ArrayList<EntrevistaVO>();
		
		if (entrevistas==null || entrevistas.isEmpty()) return entrevistascan;
		for (EntrevistaVO entrevista : entrevistas) entrevistascan.add(EntrevistaCandidatoVO.getInstance(entrevista));

		return entrevistascan;
	}
	
	public ActionForward datosCandidato(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		CandidatoForm candidatoForm = (CandidatoForm)form;

		try {
			CandidatoVo candidato = candidatoForm.getCandidato();
			if (candidato!=null){
				Date fechaNacimiento = candidato.getFechaNacimiento();
				
				int edad = candidato.getEdad();
				if (fechaNacimiento!=null){
					edad = Utils.calculaEdad(fechaNacimiento);	
				}
				
				String nombre = candidato.getNombre() +" "+ candidato.getApellido1() +" "+ candidato.getApellido2();
				Calendar fechaAlta = candidato.getFechaAlta();
				String fecAlta = Utils.formatDDMMYYYY(fechaAlta.getTime());
				ENTIDADES_FEDERATIVAS entidad = ENTIDADES_FEDERATIVAS.getEntidad(candidato.getIdEntidadNacimiento());
				String entidadNac = "";
				if (entidad!=null) entidadNac = entidad.getDescripcion();
				String sexo = "";

				if (GENERO.MASCULINO.getIdOpcion() == candidato.getGenero()){
					sexo = "Hombre";
				} else if (GENERO.FEMENINO.getIdOpcion() == candidato.getGenero()){
					sexo = "Mujer";
				}
				
				CandidatoAjaxVO datos = new CandidatoAjaxVO();
				datos.setNombre(nombre);
				datos.setCurp(candidato.getCurp());
				datos.setSexo(sexo);
				datos.setEdad(""+ edad);
				datos.setEntidad(entidadNac);
				datos.setFechaalta(fecAlta);
				session.setAttribute("candidato", datos);
			}
			
		} catch (Exception e) {
			logger.error(e); // TODO Reportar error
		}

		return mapping.findForward(FORWARD_DATOS_BASICOS);
	}

	public ActionForward generaCV(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		CandidatoForm candidatoForm = (CandidatoForm)form;
		
		String context = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ request.getContextPath() +"/";
		
		try {
			FormatCVYouth formatYouth = null;
			FormatCVSimple formatSimple = null;
			FormatCVElegant formatElegant = null;

			//long idCandidato = Utils.parseLong(request.getParameter("idCandidato"));
			long idCandidato = candidatoForm.getIdCandidato();

			System.out.println("Generando CV del Candidato :"+ idCandidato);
			
			CandidatoDelegateImpl candidatoServices = CandidatoDelegateImpl.getInstance();
			
			PerfilJB perfil = candidatoServices.loadPerfil(idCandidato);
			
			if (null == perfil) perfil = new PerfilJB();
			
			Document document= new Document(PageSize.LETTER, 2, 2, 2, 2);
			ByteArrayOutputStream vjBaosPDF = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, vjBaosPDF);
			document.open();

			String dir = request.getSession().getServletContext().getRealPath("/");
			//String dir = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ request.getContextPath() +"/";

			//System.out.println("--MyOffers---perfil.getEstiloCV():" + perfil.getEstiloCV());
			if (perfil.getEstiloCV() == Constantes.ESTILO_CV.CLASICO.getIdOpcion()){
				//logger.info("--MyOffers---perfil.getEstiloCV(): CLASICO");
				formatYouth = new FormatCVYouth();
				document = formatYouth.generaPDF(perfil, document, writer, dir, context);				
			}else if (perfil.getEstiloCV() == Constantes.ESTILO_CV.MODERNO.getIdOpcion()){
				//logger.info("--MyOffers---perfil.getEstiloCV(): MODERNO");
				formatElegant = new FormatCVElegant();
				document = formatElegant.generaPDF(perfil, document, writer, dir, context);
			}else {
				//logger.info("--MyOffers---perfil.getEstiloCV(): SIMPLE");
				formatSimple = new FormatCVSimple();
				document = formatSimple.generaPDF(perfil, document, writer, dir, context);
			}
			
			document.close();
			
			response.setHeader("Cache-Control", "max-age=30");
			response.setContentType("application/pdf");
			
			StringBuffer sbFilename = new StringBuffer();
			sbFilename.append("FEPS_");
			sbFilename.append(System.currentTimeMillis());
			sbFilename.append(".pdf");
			
			StringBuffer sbContentDispValue = new StringBuffer();
			sbContentDispValue.append("attachment");  //determina si lo despliega en pantalla o si muestra un diálogo de guardar como ("attachment")
			sbContentDispValue.append("; filename=\"");
			sbContentDispValue.append(sbFilename+"\"");
			
			response.setHeader("Content-disposition", sbContentDispValue.toString());
			//Fijamos el tamaño de la salida
			response.setContentLength(vjBaosPDF.size());
			//Ahora escribimos en la salida del Servlet - Al cliente

			ServletOutputStream vjSos = response.getOutputStream();
			vjBaosPDF.writeTo(vjSos);
			//Finalmente enviamos todos los bytes al cliente - Esto mandaría carateres raros si no se especifica el tipo de contenido.
			vjSos.flush();

			@SuppressWarnings("unused")
			RequestDispatcher reqDisp = request.getRequestDispatcher("/etc/GenFEPSPdf");

		} catch (DocumentException de) {
			de.printStackTrace();
			logger.error(de); // TODO Reportar error
		} catch (IOException ioe) {
			ioe.printStackTrace();
			logger.error(ioe); // TODO Reportar error
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e); // TODO Reportar error
		}
		
		return null;
    }
}
