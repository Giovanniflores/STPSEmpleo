package mx.gob.stps.portal.movil.web.candidato.pdf;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import mx.gob.stps.portal.core.candidate.vo.ComputacionAvanzadaVO;
import mx.gob.stps.portal.core.candidate.vo.ExpectativaLaboralVO;
import mx.gob.stps.portal.core.candidate.vo.GradoAcademicoVO;
import mx.gob.stps.portal.core.candidate.vo.HistoriaLaboralVO;
import mx.gob.stps.portal.core.candidate.vo.IdiomaVO;
import mx.gob.stps.portal.core.candidate.vo.PerfilLaboralVo;
import mx.gob.stps.portal.core.infra.utils.Constantes.CONFIDENCIALIDAD;
import mx.gob.stps.portal.core.infra.utils.Constantes.CONTACTO_CORREO;
import mx.gob.stps.portal.core.infra.utils.Constantes.CONTACTO_TELEFONO;
//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;
import mx.gob.stps.portal.core.oferta.detalle.helper.Utils;
import mx.gob.stps.portal.movil.web.oferta.form.PerfilJB;
import mx.gob.stps.portal.persistencia.vo.ConocimientoHabilidadVO;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class FormatCVSimple {

	int pointer = 730;
	int supLimit = 780;
	boolean bDebug = true;
	String directorio = "";
	Font fTituloTabla = new Font(FontFactory.getFont(
			FontFactory.HELVETICA_BOLD, 10));
	Font fNombre = new Font(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 6));
	Font fCampoGris = new Font(FontFactory.getFont(FontFactory.HELVETICA_BOLD,
			6));
	Font fDato = new Font(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 6));
	Font fCampoNaranja = new Font(FontFactory.getFont(
			FontFactory.HELVETICA_BOLD, 6));
	Font fTituloSubTabla = new Font(FontFactory.getFont(
			FontFactory.HELVETICA_BOLD, 6));

	public Document generaPDF(PerfilJB perfil, Document doc, PdfWriter writer,
			String dir, String context) throws Exception {

		directorio = dir;
		fTituloTabla.setColor(255, 255, 255);
		fNombre.setColor(253, 104, 4);
		fCampoGris.setColor(102, 102, 102);
		fDato.setColor(150, 150, 150);
		fCampoNaranja.setColor(253, 104, 4);
		fTituloSubTabla.setColor(67, 96, 96);

		PdfPTable tabla = null;
		PerfilLaboralVo perfilLaboral = null;
		HistoriaLaboralVO historiaLaboral = null;
		ComputacionAvanzadaVO computacionAvanzada = null;
		ExpectativaLaboralVO expectativaLaboral = null;

		if (null != perfil.getPerfilLaboral())
			perfilLaboral = perfil.getPerfilLaboral();
		else
			perfilLaboral = new PerfilLaboralVo();
		if (null != perfil.getComputacionPrincipal())
			computacionAvanzada = perfil.getComputacionPrincipal();
		else
			computacionAvanzada = new ComputacionAvanzadaVO();
		if (null != perfil.getTrabajoActual())
			historiaLaboral = perfil.getTrabajoActual();
		else
			historiaLaboral = new HistoriaLaboralVO();
		if (null != perfil.getExpectativaPrincipal())
			expectativaLaboral = perfil.getExpectativaPrincipal();
		else
			expectativaLaboral = new ExpectativaLaboralVO();

		try {
			// Fondo
			doc.add(getImagen(directorio + "images" + File.separator
					+ "cv1_head.jpg", 455, 50));
			// Pleca gris
			tabla = getPlecaGris(directorio + "images" + File.separator
					+ "cv1_pleca_tab.jpg");
			tabla.writeSelectedRows(0, -1, 20, pointer(doc, 0, 0),
					writer.getDirectContent());
			// Titulo Datos personales
			tabla = getTitulo("Datos personales");
			tabla.writeSelectedRows(0, -1, 20, pointer(doc, 4, 0),
					writer.getDirectContent());
			// Tabla Datos personales
			tabla = getDatosPersonales(perfil);
			tabla.writeSelectedRows(0, -1, 60, pointer(doc, 26, 0),
					writer.getDirectContent());
			// Foto
			tabla = getFoto(perfil.getIdCandidato(), context);
			tabla.writeSelectedRows(0, -1, 280, pointer(doc, 0, 0),
					writer.getDirectContent());
			// Pleca gris
			tabla = getPlecaGris(directorio + "images" + File.separator
					+ "cv1_pleca_rec.jpg");
			tabla.writeSelectedRows(0, -1, 20, pointer(doc, 70, 0),
					writer.getDirectContent());
			// Titulo Datos actuales
			tabla = getTitulo("Datos actuales");
			tabla.writeSelectedRows(0, -1, 20, pointer(doc, 4, 0),
					writer.getDirectContent());
			// Tabla Datos actuales
			tabla = getDatosActuales(perfil);
			tabla.writeSelectedRows(0, -1, 40, pointer(doc, 26, 0),
					writer.getDirectContent());
			// Linea gris
			tabla = getPlecaGris(directorio + "images" + File.separator
					+ "cv2_bg.jpg");
			tabla.writeSelectedRows(0, -1, 20, pointer(doc, 65, 0),
					writer.getDirectContent());
			// Vineta
			tabla = getSubtitulo("Datos para contactar");
			tabla.writeSelectedRows(0, -1, 40, pointer(doc, 5, 0),
					writer.getDirectContent());
			// SubTabla Datos contacto
			tabla = getDatosContacto(perfil);
			tabla.writeSelectedRows(0, -1, 40, pointer(doc, 10, 0),
					writer.getDirectContent());
			// Linea gris
			tabla = getPlecaGris(directorio + "images" + File.separator
					+ "cv2_bg.jpg");
			tabla.writeSelectedRows(0, -1, 20, pointer(doc, 40, 0),
					writer.getDirectContent());
			// SubTabla Datos contacto
			tabla = getVideo(perfil.getUrlVideo(), perfil.getConfidencialidad());
			tabla.writeSelectedRows(0, -1, 40, pointer(doc, 5, 0),
					writer.getDirectContent());
			// Pleca gris
			tabla = getPlecaGris(directorio + "images" + File.separator
					+ "cv1_pleca_rec.jpg");
			tabla.writeSelectedRows(0, -1, 20, pointer(doc, 20, 0),
					writer.getDirectContent());
			// Titulo Datos actuales
			tabla = getTitulo("Grado académico");
			tabla.writeSelectedRows(0, -1, 20, pointer(doc, 5, 0),
					writer.getDirectContent());
			// Grado academico principal
			tabla = getGradoAcademico(perfil.getGradoPrincipal());
			tabla.writeSelectedRows(0, -1, 40, pointer(doc, 25, 0),
					writer.getDirectContent());
			// Linea gris
			tabla = getPlecaGris(directorio + "images" + File.separator
					+ "cv2_bg.jpg");
			tabla.writeSelectedRows(0, -1, 20, pointer(doc, 55, 0),
					writer.getDirectContent());
			// Tabla Situacion academica principal
			tabla = getSituacionAcademica(perfil.getGradoPrincipal()
					.getSituacion());
			tabla.writeSelectedRows(0, -1, 40, pointer(doc, 5, 0),
					writer.getDirectContent());
			// Pleca gris
			tabla = getPlecaGris(directorio + "images" + File.separator
					+ "cv1_pleca_rec.jpg");
			tabla.writeSelectedRows(0, -1, 20, pointer(doc, 35, 0),
					writer.getDirectContent());

			// Titulo Conocimientos y habilidades
			tabla = getTitulo("Conocimientos y habilidades");
			tabla.writeSelectedRows(0, -1, 20, pointer(doc, 3, 0),
					writer.getDirectContent());

			// Tabla Conocimiento principal
			tabla = getConocimientos(perfil.getConocimientoPrincipal());
			tabla.writeSelectedRows(0, -1, 40, pointer(doc, 22, 0),
					writer.getDirectContent());

			// Linea gris
			tabla = getPlecaGris(directorio + "images" + File.separator
					+ "cv2_bg.jpg");
			tabla.writeSelectedRows(0, -1, 20, pointer(doc, 80, 0),
					writer.getDirectContent());

			// Tabla Conocimientos secundarios
			Iterator<ConocimientoHabilidadVO> itConAds = perfil
					.getConocimientos().iterator();
			while (itConAds.hasNext()) {
				ConocimientoHabilidadVO conAd = itConAds.next();
				tabla = getConocimientos(conAd);
				tabla.writeSelectedRows(0, -1, 40, pointer(doc, 5, 85),
						writer.getDirectContent());

				// Linea gris
				tabla = getPlecaGris(directorio + "images" + File.separator
						+ "cv2_bg.jpg");
				tabla.writeSelectedRows(0, -1, 20, pointer(doc, 80, 0),
						writer.getDirectContent());
			}

			// Tabla Habilidad principal
			tabla = getHabilidades(perfil.getHabilidadPrincipal());
			tabla.writeSelectedRows(0, -1, 40, pointer(doc, 5, 80),
					writer.getDirectContent());
			// Linea gris
			tabla = getPlecaGris(directorio + "images" + File.separator
					+ "cv2_bg.jpg");
			tabla.writeSelectedRows(0, -1, 20, pointer(doc, 80, 0),
					writer.getDirectContent());

			// Tabla habilidades secundarias
			Iterator<ConocimientoHabilidadVO> itHabAds = perfil
					.getHabilidades().iterator();
			while (itHabAds.hasNext()) {
				ConocimientoHabilidadVO habAd = itHabAds.next();
				tabla = getHabilidades(habAd);
				tabla.writeSelectedRows(0, -1, 40, pointer(doc, 5, 85),
						writer.getDirectContent());

				// Linea gris
				tabla = getPlecaGris(directorio + "images" + File.separator
						+ "cv2_bg.jpg");
				tabla.writeSelectedRows(0, -1, 20, pointer(doc, 80, 0),
						writer.getDirectContent());
			}

			// Vineta
			tabla = getSubtitulo("Idiomas");
			tabla.writeSelectedRows(0, -1, 40, pointer(doc, 5, 40),
					writer.getDirectContent());
			// Tabla Idiomas
			tabla = getIdiomas(perfil.getIdiomaPrincipal());
			tabla.writeSelectedRows(0, -1, 40, pointer(doc, 15, 0),
					writer.getDirectContent());
			// Linea gris
			tabla = getPlecaGris(directorio + "images" + File.separator
					+ "cv2_bg.jpg");
			tabla.writeSelectedRows(0, -1, 20, pointer(doc, 30, 0),
					writer.getDirectContent());
			// Tabla idiomas secundarios
			Iterator<IdiomaVO> it = perfil.getIdiomas().iterator();
			while (it.hasNext()) {
				tabla = getIdiomas(it.next());
				tabla.writeSelectedRows(0, -1, 40, pointer(doc, 15, 40),
						writer.getDirectContent());
				// Linea gris
				tabla = getPlecaGris(directorio + "images" + File.separator
						+ "cv2_bg.jpg");
				tabla.writeSelectedRows(0, -1, 20, pointer(doc, 30, 0),
						writer.getDirectContent());
			}
			// Vineta
			tabla = getSubtitulo("Computación básica");
			tabla.writeSelectedRows(0, -1, 40, pointer(doc, 5, 30),
					writer.getDirectContent());
			// Tabla Computacion principal
			tabla = getComputacionBasica(perfilLaboral);
			tabla.writeSelectedRows(0, -1, 40, pointer(doc, 10, 0),
					writer.getDirectContent());
			// Linea gris
			tabla = getPlecaGris(directorio + "images" + File.separator
					+ "cv2_bg.jpg");
			tabla.writeSelectedRows(0, -1, 20, pointer(doc, 25, 0),
					writer.getDirectContent());// 45

			// Tabla Experiencia Office
			tabla = getOffice(perfilLaboral);
			tabla.writeSelectedRows(0, -1, 40, pointer(doc, 5, 35),
					writer.getDirectContent());
			// Linea gris
			tabla = getPlecaGris(directorio + "images" + File.separator
					+ "cv2_bg.jpg");
			tabla.writeSelectedRows(0, -1, 20, pointer(doc, 30, 0),
					writer.getDirectContent());

			// Tabla Experiencia Internet
			tabla = getInternet(perfilLaboral);
			tabla.writeSelectedRows(0, -1, 40, pointer(doc, 5, 30),
					writer.getDirectContent());
			// Linea gris
			tabla = getPlecaGris(directorio + "images" + File.separator
					+ "cv2_bg.jpg");
			tabla.writeSelectedRows(0, -1, 20, pointer(doc, 25, 0),
					writer.getDirectContent());

			// Vineta
			tabla = getSubtitulo("Computación Avanzada");
			tabla.writeSelectedRows(0, -1, 40, pointer(doc, 5, 100),
					writer.getDirectContent());
			// Tabla Computación Avanzada
			tabla = getComputacionAvanzada(computacionAvanzada);
			tabla.writeSelectedRows(0, -1, 40, pointer(doc, 10, 0),
					writer.getDirectContent());

			// Pleca gris
			tabla = getPlecaGris(directorio + "images" + File.separator
					+ "cv1_pleca_rec.jpg");
			tabla.writeSelectedRows(0, -1, 20, pointer(doc, 90, 90),
					writer.getDirectContent());
			// Titulo Experiencia Laboral
			tabla = getTitulo("Experiencia laboral");
			tabla.writeSelectedRows(0, -1, 20, pointer(doc, 3, 0),
					writer.getDirectContent());
			// Tabla Experiencia Laboral
			tabla = getExperienciaLaboral(perfil, perfilLaboral);
			tabla.writeSelectedRows(0, -1, 40, pointer(doc, 22, 0),
					writer.getDirectContent());

			// Pleca gris
			tabla = getPlecaGris(directorio + "images" + File.separator
					+ "cv1_pleca_rec.jpg");
			// tabla.writeSelectedRows(0, -1, 20, pointer(doc,65,45),
			// writer.getDirectContent());
			tabla.writeSelectedRows(0, -1, 20, pointer(doc, 80, 45),
					writer.getDirectContent());

			// Titulo Situacion Laboral
			tabla = getTitulo("Situación laboral");
			tabla.writeSelectedRows(0, -1, 20, pointer(doc, 3, 0),
					writer.getDirectContent());
			// Tabla Situacion Laboral
			tabla = getSituacionLaboral(perfil);
			tabla.writeSelectedRows(0, -1, 40, pointer(doc, 22, 0),
					writer.getDirectContent());
			// Linea gris
			tabla = getPlecaGris(directorio + "images" + File.separator
					+ "cv2_bg.jpg");
			tabla.writeSelectedRows(0, -1, 20, pointer(doc, 10, 0),
					writer.getDirectContent());

			// Vineta
			tabla = getSubtitulo("Datos del empleo actual");
			tabla.writeSelectedRows(0, -1, 40, pointer(doc, 10, 240),
					writer.getDirectContent());
			// Tabla Datos del empleo actual
			tabla = getEmpleoActual(historiaLaboral);
			tabla.writeSelectedRows(0, -1, 40, pointer(doc, 10, 0),
					writer.getDirectContent());
			// Linea gris
			int leap = leap(historiaLaboral);
			tabla = getPlecaGris(directorio + "images" + File.separator
					+ "cv2_bg.jpg");
			tabla.writeSelectedRows(0, -1, 20, pointer(doc, leap, 0),
					writer.getDirectContent());

			// Tabla Busca empleo
			tabla = getBuscaEmpleo(perfil, perfilLaboral);
			tabla.writeSelectedRows(0, -1, 40, pointer(doc, 10, 40),
					writer.getDirectContent());

			// Pleca gris
			tabla = getPlecaGris(directorio + "images" + File.separator
					+ "cv1_pleca_rec.jpg");
			// tabla.writeSelectedRows(0, -1, 20, pointer(doc,40,97),
			// writer.getDirectContent());
			tabla.writeSelectedRows(0, -1, 20, pointer(doc, 40, 200),
					writer.getDirectContent());
			// Titulo Situacion Laboral
			tabla = getTitulo("Expectativas laborales");
			tabla.writeSelectedRows(0, -1, 20, pointer(doc, 3, 0),
					writer.getDirectContent());
			// Tabla Expectativa Laboral
			tabla = getExpectativaLaboral(expectativaLaboral);
			tabla.writeSelectedRows(0, -1, 40, pointer(doc, 22, 0),
					writer.getDirectContent());
			// Linea gris
			tabla = getPlecaGris(directorio + "images" + File.separator
					+ "cv2_bg.jpg");
			tabla.writeSelectedRows(0, -1, 20, pointer(doc, 80, 0),
					writer.getDirectContent());

			// Vineta
			tabla = getSubtitulo("Ubicación y disponibilidad");
			tabla.writeSelectedRows(0, -1, 40, pointer(doc, 10, 80),
					writer.getDirectContent());
			// Tabla Ubicación y Disponibilidad
			tabla = getUbicacion(perfil, perfilLaboral);
			tabla.writeSelectedRows(0, -1, 40, pointer(doc, 10, 0),
					writer.getDirectContent());

			// Footer
			tabla = getFooter();
			tabla.writeSelectedRows(0, -1, 20, pointer(doc, 70, 90),
					writer.getDirectContent());
		} catch (DocumentException de) {
			if (bDebug)
				System.out.println(de.getMessage());
		} catch (IOException ioe) {
			if (bDebug)
				System.out.println(ioe.getMessage());
		} catch (Exception e) {
			if (bDebug)
				System.out.println(e.getMessage());
		}
		return doc;
	}

	private PdfPTable getDatosPersonales(PerfilJB perfil) {
		float[] width = new float[2];
		width[0] = .5f;
		width[1] = .5f;
		PdfPTable tabla = new PdfPTable(width);
		tabla.getDefaultCell().setBorder(0);
		tabla.setTotalWidth(250);
		tabla.setHorizontalAlignment(Element.ALIGN_JUSTIFIED_ALL);
		tabla.setLockedWidth(true);
		tabla.setSplitRows(true);
		tabla.addCell(new Paragraph(new Chunk(perfil.getNombre() + " "
				+ perfil.getApellido1() + " " + perfil.getApellido2(), fNombre)));
		tabla.addCell(new Paragraph(new Chunk("", fNombre)));
		if (perfil.getConfidencialidad() == CONFIDENCIALIDAD.NO.getIdOpcion()) {
			tabla.addCell(new Paragraph(new Chunk("Género: ", fCampoGris)));
			tabla.addCell(new Paragraph(new Chunk(perfil.getGenero(), fDato)));
			tabla.addCell(new Paragraph(new Chunk("CURP: ", fCampoGris)));
			tabla.addCell(new Paragraph(new Chunk(perfil.getCurp(), fDato)));
			tabla.addCell(new Paragraph(new Chunk("Edad: ", fCampoGris)));
			tabla.addCell(new Paragraph(new Chunk(
					(perfil.getEdad() > 0 ? String.valueOf(perfil.getEdad())
							: ""), fDato)));
			tabla.addCell(new Paragraph(new Chunk("Fecha de nacimiento: ",
					fCampoGris)));
			tabla.addCell(new Paragraph(new Chunk(perfil
					.getFormattedFechaNacimiento(), fDato)));
			tabla.addCell(new Paragraph(new Chunk("Entidad de nacimiento: ",
					fCampoGris)));
			tabla.addCell(new Paragraph(new Chunk(
					perfil.getEntidadNacimiento(), fDato)));
		}
		return tabla;
	}

	private PdfPTable getDatosActuales(PerfilJB perfil) {
		float[] width = new float[2];
		width[0] = .5f;
		width[1] = .5f;
		PdfPTable tabla = new PdfPTable(width);
		tabla.getDefaultCell().setBorder(0);
		tabla.setTotalWidth(250);
		tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
		tabla.setLockedWidth(true);
		tabla.addCell(new Paragraph(new Chunk("Dirección: ", fCampoNaranja)));
		StringBuilder address = new StringBuilder();
		// address.append((null != perfil.getCalle() ? perfil.getCalle() : "") +
		// " ");
		// address.append((null != perfil.getNumeroExterior() ?
		// perfil.getNumeroExterior() : "") + " ");
		// address.append((null != perfil.getNumeroInterior() ?
		// perfil.getNumeroInterior() : "") + " ");
		address.append((null != perfil.getColonia() ? perfil.getColonia() : "")
				+ " ");
		// address.append((null != perfil.getEntreCalle() ?
		// perfil.getEntreCalle() : "") + " ");
		// address.append((null != perfil.getyCalle() ? perfil.getyCalle() :
		// ""));
		tabla.addCell(new Paragraph(new Chunk(address.toString().trim(), fDato)));
		tabla.addCell(new Paragraph(new Chunk("Código postal: ", fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk(
				(null != perfil.getCodigoPostal() ? perfil.getCodigoPostal()
						: ""), fDato)));
		/*
		 * tabla.addCell(new Paragraph(new Chunk("País: ", fCampoNaranja)));
		 * tabla.addCell(new Paragraph(new Chunk("México", fDato)));
		 */
		tabla.addCell(new Paragraph(new Chunk("Entidad: ", fCampoNaranja)));
		tabla.addCell(new Paragraph(
				new Chunk((null != perfil.getEntidad() ? perfil.getEntidad()
						: ""), fDato)));
		tabla.addCell(new Paragraph(new Chunk("Municipio o delegación: ",
				fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk(
				(null != perfil.getMunicipio() ? perfil.getMunicipio() : ""),
				fDato)));
		return tabla;
	}

	private PdfPTable getDatosContacto(PerfilJB perfil) {
		float[] width = new float[2];
		width[0] = .5f;
		width[1] = .5f;
		//TelefonoVO tel = null;
		PdfPTable tabla = new PdfPTable(width);
		tabla.getDefaultCell().setBorder(0);
		tabla.setTotalWidth(250);
		tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
		tabla.setLockedWidth(true);
	/*	if (null != perfil.getPrincipal())
			tel = perfil.getPrincipal();
		else
			tel = new TelefonoVO();*/
		tabla.addCell(new Paragraph(new Chunk("Correo electrónico: ",
				fCampoNaranja)));
		if (perfil.getPerfilLaboral().getContactoCorreo() == CONTACTO_CORREO.NO
				.getIdContactoCorreo())
			tabla.addCell(new Paragraph(new Chunk((null != perfil
					.getCorreoElectronico() ? perfil.getCorreoElectronico()
					: ""), fDato)));
		else
			tabla.addCell(new Paragraph(new Chunk("", fDato)));
		if (perfil.getPerfilLaboral().getContactoTelefono() == CONTACTO_TELEFONO.NO
				.getIdContactoTelefono()) {
			tabla.addCell(new Paragraph(new Chunk("Teléfono: ", fCampoNaranja)));
		/*	tabla.addCell(new Paragraph(
					new Chunk((null != tel.getTelefono() ? tel.getTelefono()
							: ""), fDato)));*/
			tabla.addCell(new Paragraph(new Chunk("Tipo de teléfono: ",
					fCampoNaranja)));
			//tabla.addCell(new Paragraph(new Chunk(
			//		(tel.getIdTipoTelefono() > 0 ? Utils
			//				.getTipoTelefono((int) tel.getIdTipoTelefono())
			//				: ""), fDato)));
			tabla.addCell(new Paragraph(new Chunk("Horario de contacto: ",
					fCampoNaranja)));
			tabla.addCell(new Paragraph(new Chunk((null != perfil
					.getHorarioLlamar() ? perfil.getHorarioLlamar() : ""),
					fDato)));
		} else {
			tabla.addCell(new Paragraph(new Chunk("Teléfono: ", fCampoNaranja)));
			tabla.addCell(new Paragraph(new Chunk("", fDato)));
			tabla.addCell(new Paragraph(new Chunk("Tipo de teléfono: ",
					fCampoNaranja)));
			tabla.addCell(new Paragraph(new Chunk("", fDato)));
			tabla.addCell(new Paragraph(new Chunk("Horario de contacto: ",
					fCampoNaranja)));
			tabla.addCell(new Paragraph(new Chunk("", fDato)));
		}
		return tabla;
	}

	private PdfPTable getVideo(String url, int confidencialidad) {
		float[] width = new float[2];
		width[0] = .5f;
		width[1] = .5f;
		PdfPTable tabla = new PdfPTable(width);
		tabla.getDefaultCell().setBorder(0);
		tabla.setTotalWidth(250);
		tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
		tabla.setLockedWidth(true);
		tabla.addCell(new Paragraph(new Chunk("Ver mi video- curriculum: ",
				fCampoNaranja)));
		if (confidencialidad == CONFIDENCIALIDAD.NO.getIdOpcion())
			tabla.addCell(new Paragraph(new Chunk(url, fDato)));
		return tabla;
	}

	private PdfPTable getGradoAcademico(GradoAcademicoVO vo) {
		float[] width = new float[2];
		width[0] = .5f;
		width[1] = .5f;
		PdfPTable tabla = new PdfPTable(width);
		tabla.getDefaultCell().setBorder(0);
		tabla.setTotalWidth(250);
		tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
		tabla.setLockedWidth(true);
		tabla.addCell(new Paragraph(new Chunk("Grado de estudios: ",
				fCampoNaranja)));
		if (null != vo.getNivel())
			tabla.addCell(new Paragraph(new Chunk(vo.getNivel(), fDato)));
		else
			tabla.addCell(new Paragraph(new Chunk("", fDato)));
		tabla.addCell(new Paragraph(new Chunk("Carrera o especialidad: ",
				fCampoNaranja)));
		if (null != vo.getCarrera())
			tabla.addCell(new Paragraph(new Chunk(vo.getCarrera(), fDato)));
		else
			tabla.addCell(new Paragraph(new Chunk("", fDato)));
		tabla.addCell(new Paragraph(new Chunk("Escuela de procedencia: ",
				fCampoNaranja)));
		if (null != vo.getEscuela())
			tabla.addCell(new Paragraph(new Chunk(vo.getEscuela(), fDato)));
		else
			tabla.addCell(new Paragraph(new Chunk("", fDato)));
		tabla.addCell(new Paragraph(new Chunk("Año de inicio: ", fCampoNaranja)));
		if (vo.getInicio() > 0)
			tabla.addCell(new Paragraph(new Chunk("" + vo.getInicio(), fDato)));
		else
			tabla.addCell(new Paragraph(new Chunk("", fDato)));
		tabla.addCell(new Paragraph(new Chunk("Año de terminación: ",
				fCampoNaranja)));
		if (vo.getFin() > 0)
			tabla.addCell(new Paragraph(new Chunk("" + vo.getFin(), fDato)));
		else
			tabla.addCell(new Paragraph(new Chunk("", fDato)));
		return tabla;
	}

	private PdfPTable getSituacionAcademica(String situacionAcademica) {
		float[] width = new float[2];
		width[0] = .5f;
		width[1] = .5f;
		PdfPTable tabla = new PdfPTable(width);
		tabla.getDefaultCell().setBorder(0);
		tabla.setTotalWidth(250);
		tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
		tabla.setLockedWidth(true);
		tabla.addCell(new Paragraph(new Chunk("Situación académica: ",
				fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk(
				(null != situacionAcademica ? situacionAcademica : ""), fDato)));
		return tabla;
	}

	private PdfPTable getConocimientos(ConocimientoHabilidadVO vo) {
		float[] width = new float[2];
		width[0] = .9f;
		width[1] = 2f;
		PdfPTable tabla = new PdfPTable(width);
		tabla.getDefaultCell().setBorder(0);
		tabla.setTotalWidth(250);
		tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
		tabla.setLockedWidth(true);
		tabla.addCell(new Paragraph(new Chunk("Conocimientos: ", fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk((null != vo
				.getConocimientoHabilidad() ? vo.getConocimientoHabilidad()
				: ""), fDato)));
		tabla.addCell(new Paragraph(new Chunk("Dominio: ", fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk((null != vo.getDominio() ? vo
				.getDominio() : ""), fDato)));
		tabla.addCell(new Paragraph(new Chunk("Descripción: ", fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk((null != vo.getDescripcion() ? vo
				.getDescripcion() : ""), fDato)));
		tabla.addCell(new Paragraph(new Chunk("Experiencia: ", fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk((null != vo.getExperiencia() ? vo
				.getExperiencia() : ""), fDato)));
		return tabla;
	}

	private PdfPTable getHabilidades(ConocimientoHabilidadVO vo) {
		float[] width = new float[2];
		width[0] = .9f;
		width[1] = 2f;
		PdfPTable tabla = new PdfPTable(width);
		tabla.getDefaultCell().setBorder(0);
		tabla.setTotalWidth(250);
		tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
		tabla.setLockedWidth(true);
		tabla.addCell(new Paragraph(new Chunk("Habilidades: ", fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk((null != vo
				.getConocimientoHabilidad() ? vo.getConocimientoHabilidad()
				: ""), fDato)));
		tabla.addCell(new Paragraph(new Chunk("Dominio: ", fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk((null != vo.getDominio() ? vo
				.getDominio() : ""), fDato)));
		tabla.addCell(new Paragraph(new Chunk("Descripción: ", fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk((null != vo.getDescripcion() ? vo
				.getDescripcion() : ""), fDato)));
		tabla.addCell(new Paragraph(new Chunk("Experiencia: ", fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk(
				(vo.getIdExperiencia() > 0 ? Utils.getExperiencia((int) vo
						.getIdExperiencia()) : ""), fDato)));
		return tabla;
	}

	private PdfPTable getIdiomas(IdiomaVO idioma) {
		float[] width = new float[2];
		width[0] = .5f;
		width[1] = .5f;
		PdfPTable tabla = new PdfPTable(width);
		tabla.getDefaultCell().setBorder(0);
		tabla.setTotalWidth(250);
		tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
		tabla.setLockedWidth(true);
		tabla.addCell(new Paragraph(new Chunk("Idioma: ", fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk(
				(null != idioma.getIdioma() ? idioma.getIdioma() : ""), fDato)));
		tabla.addCell(new Paragraph(new Chunk("Certificación: ", fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk((null != idioma
				.getCertificacion() ? idioma.getCertificacion() : ""), fDato)));
		tabla.addCell(new Paragraph(new Chunk("Dominio: ", fCampoNaranja)));
		tabla.addCell(new Paragraph(
				new Chunk((null != idioma.getDominio() ? idioma.getDominio()
						: ""), fDato)));
		return tabla;
	}

	private PdfPTable getComputacionBasica(PerfilLaboralVo vo) {
		float[] width = new float[2];
		width[0] = .5f;
		width[1] = .5f;
		PdfPTable tabla = new PdfPTable(width);
		tabla.getDefaultCell().setBorder(0);
		tabla.setTotalWidth(250);
		tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
		tabla.setLockedWidth(true);
		tabla.addCell(new Paragraph(new Chunk(
				"Experiencia en uso de computadora: ", fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk(
				(vo.getIdExperienciaCompu() > 0 ? Utils.getExperiencia((int) vo
						.getIdExperienciaCompu()) : ""), fDato)));
		tabla.addCell(new Paragraph(new Chunk(
				"Dominio en uso de computadora: ", fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk(
				(vo.getIdDominioCompu() > 0 ? Utils.getDominio((int) vo
						.getIdDominioCompu()) : ""), fDato)));
		return tabla;
	}

	private PdfPTable getOffice(PerfilLaboralVo vo) {
		float[] width = new float[2];
		width[0] = .5f;
		width[1] = .5f;
		PdfPTable tabla = new PdfPTable(width);
		tabla.getDefaultCell().setBorder(0);
		tabla.setTotalWidth(250);
		tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
		tabla.setLockedWidth(true);
		tabla.addCell(new Paragraph(new Chunk("Experiencia Microsoft Office: ",
				fCampoNaranja)));
		tabla.addCell(new Paragraph(
				new Chunk(
						(vo.getIdExperienciaOffice() > 0 ? Utils
								.getExperiencia((int) vo
										.getIdExperienciaOffice()) : ""), fDato)));
		tabla.addCell(new Paragraph(new Chunk("Dominio Microsoft Office: ",
				fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk(
				(vo.getIdDominioOffice() > 0 ? Utils.getDominio((int) vo
						.getIdDominioOffice()) : ""), fDato)));
		return tabla;
	}

	private PdfPTable getInternet(PerfilLaboralVo vo) {
		float[] width = new float[2];
		width[0] = .5f;
		width[1] = .5f;
		PdfPTable tabla = new PdfPTable(width);
		tabla.getDefaultCell().setBorder(0);
		tabla.setTotalWidth(250);
		tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
		tabla.setLockedWidth(true);
		tabla.addCell(new Paragraph(new Chunk("Experiencia Internet: ",
				fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk(
				(vo.getIdExperienciaInternet() > 0 ? Utils
						.getExperiencia((int) vo.getIdExperienciaInternet())
						: ""), fDato)));
		tabla.addCell(new Paragraph(new Chunk("Dominio Internet: ",
				fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk(
				(vo.getIdDominioInternet() > 0 ? Utils.getDominio((int) vo
						.getIdDominioInternet()) : ""), fDato)));
		return tabla;
	}

	private PdfPTable getComputacionAvanzada(ComputacionAvanzadaVO vo) {
		float[] width = new float[2];
		width[0] = .5f;
		width[1] = .5f;
		PdfPTable tabla = new PdfPTable(width);
		tabla.getDefaultCell().setBorder(0);
		tabla.setTotalWidth(250);
		tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
		tabla.setLockedWidth(true);
		tabla.addCell(new Paragraph(new Chunk("Software o Hardware: ",
				fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk(
				(null != vo.getSoftwareHardware() ? vo.getSoftwareHardware()
						: ""), fDato)));
		tabla.addCell(new Paragraph(new Chunk("Dominio: ", fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk((null != vo.getDominio() ? vo
				.getDominio() : ""), fDato)));
		tabla.addCell(new Paragraph(new Chunk("Descripción: ", fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk((null != vo.getDescripcion() ? vo
				.getDescripcion() : ""), fDato)));
		tabla.addCell(new Paragraph(new Chunk("Experiencia: ", fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk((null != vo.getExperiencia() ? vo
				.getExperiencia() : ""), fDato)));
		return tabla;
	}

	private PdfPTable getExperienciaLaboral(PerfilJB perfil, PerfilLaboralVo vo) {
		float[] width = new float[2];
		width[0] = .5f;
		width[1] = .5f;
		PdfPTable tabla = new PdfPTable(width);
		tabla.getDefaultCell().setBorder(0);
		tabla.setTotalWidth(250);
		tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
		tabla.setLockedWidth(true);
		tabla.addCell(new Paragraph(new Chunk("Experiencia total: ",
				fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk(
				(vo.getIdExperienciaTotal() > 0 ? Utils.getExperiencia((int) vo
						.getIdExperienciaTotal()) : ""), fDato)));
		tabla.addCell(new Paragraph(new Chunk("Sector de mayor experiencia: ",
				fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk(
				(null != perfil.getSector() ? perfil.getSector() : ""), fDato)));
		tabla.addCell(new Paragraph(new Chunk("Área de mayor experiencia: ",
				fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk(
				(null != perfil.getArea() ? perfil.getArea() : ""), fDato)));
		tabla.addCell(new Paragraph(new Chunk("Puesto con mayor experiencia: ",
				fCampoNaranja)));
		tabla.addCell(new Paragraph(
				new Chunk((null != vo.getPuestoMayorExpr() ? vo
						.getPuestoMayorExpr() : ""), fDato)));
		tabla.addCell(new Paragraph(new Chunk(
				"Ocupación con mayor experiencia: ", fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk(
				(null != perfil.getOcupacion() ? perfil.getOcupacion() : ""),
				fDato)));
		return tabla;
	}

	private PdfPTable getSituacionLaboral(PerfilJB perfil) {
		float[] width = new float[2];
		width[0] = .5f;
		width[1] = .5f;
		PdfPTable tabla = new PdfPTable(width);
		tabla.getDefaultCell().setBorder(0);
		tabla.setTotalWidth(250);
		tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
		tabla.setLockedWidth(true);
		tabla.addCell(new Paragraph(new Chunk("¿Trabajas actualmente?: ",
				fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk(
				(perfil.getIdTrabaja() > 0 ? Utils.getLblTrabaja(perfil
						.getIdTrabaja()) : ""), fDato)));
		return tabla;
	}

	private PdfPTable getEmpleoActual(HistoriaLaboralVO vo) {
		float[] width = new float[2];
		width[0] = .5f;
		width[1] = .5f;
		PdfPTable tabla = new PdfPTable(width);
		tabla.getDefaultCell().setBorder(0);
		tabla.setTotalWidth(250);
		tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
		tabla.setLockedWidth(true);
		tabla.addCell(new Paragraph(new Chunk("Sector: ", fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk((null != vo.getSector() ? vo
				.getSector() : ""), fDato)));
		tabla.addCell(new Paragraph(new Chunk("Puesto: ", fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk((null != vo.getPuesto() ? vo
				.getPuesto() : ""), fDato)));
		tabla.addCell(new Paragraph(new Chunk("Área laboral: ", fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk((null != vo.getAreaLaboral() ? vo
				.getAreaLaboral() : ""), fDato)));
		tabla.addCell(new Paragraph(new Chunk("Ocupación: ", fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk((null != vo.getOcupacion() ? vo
				.getOcupacion() : ""), fDato)));
		tabla.addCell(new Paragraph(new Chunk("Empresa: ", fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk((null != vo.getEmpresa() ? vo
				.getEmpresa() : ""), fDato)));
		tabla.addCell(new Paragraph(new Chunk("Fecha inicial: ", fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk(
				(null != vo.getLaboresInicial() ? Utils.formatDate(vo
						.getLaboresInicial()) : ""), fDato)));
		tabla.addCell(new Paragraph(new Chunk("Fecha final: ", fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk(
				(null != vo.getLaboresFinal() ? Utils.formatDate(vo
						.getLaboresFinal()) : "Actualmente"), fDato)));
		tabla.addCell(new Paragraph(
				new Chunk("Años laborados: ", fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk((null != vo.getAnios() ? vo
				.getAnios() : ""), fDato)));
		tabla.addCell(new Paragraph(new Chunk("Jerarquia del puesto: ",
				fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk((null != vo.getJerarquia() ? vo
				.getJerarquia() : ""), fDato)));
		tabla.addCell(new Paragraph(new Chunk("Personas a cargo: ",
				fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk((null != vo.getPersonas() ? vo
				.getPersonas() : ""), fDato)));
		tabla.addCell(new Paragraph(new Chunk("Funciones desempeñadas: ",
				fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk((null != vo.getFuncion() ? vo
				.getFuncion() : ""), fDato)));
		tabla.addCell(new Paragraph(new Chunk("Logros de la empresa: ",
				fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk((null != vo.getLogro() ? vo
				.getLogro() : ""), fDato)));
		tabla.addCell(new Paragraph(new Chunk("Último salario mensual: ",
				fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk((vo.getSalarioMensual() > 0 ? 
				mx.gob.stps.portal.movil.web.infra.utils.Utils.formatMoney(vo.getSalarioMensual()) : ""), fDato)));
		return tabla;
	}

	private PdfPTable getBuscaEmpleo(PerfilJB perfil, PerfilLaboralVo vo) {
		float[] width = new float[2];
		width[0] = .5f;
		width[1] = .5f;
		PdfPTable tabla = new PdfPTable(width);
		tabla.getDefaultCell().setBorder(0);
		tabla.setTotalWidth(250);
		tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
		tabla.setLockedWidth(true);
		tabla.addCell(new Paragraph(new Chunk(
				"Razones por la que busca trabajo: ", fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk(
				(vo.getIdRazonBusqueda() > 0 ? Utils
						.getLblRazonBusqueda((int) vo.getIdRazonBusqueda())
						: ""), fDato)));
		tabla.addCell(new Paragraph(new Chunk(
				"Inicio de la búsqueda de empleo: ", fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk(
				(null != vo.getInicioBusqueda() ? Utils.formatDate(vo
						.getInicioBusqueda()) : ""), fDato)));
		tabla.addCell(new Paragraph(new Chunk(
				"Medios utilizados para buscar empleo: ", fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk((null != perfil
				.getMediosBusqueda() ? perfil.getMediosBusqueda() : ""), fDato)));
		return tabla;
	}

	private PdfPTable getExpectativaLaboral(ExpectativaLaboralVO vo) {
		float[] width = new float[2];
		width[0] = .5f;
		width[1] = .5f;
		PdfPTable tabla = new PdfPTable(width);
		tabla.getDefaultCell().setBorder(0);
		tabla.setTotalWidth(250);
		tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
		tabla.setLockedWidth(true);
		tabla.addCell(new Paragraph(
				new Chunk("Sector deseado: ", fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk(
				(null != vo.getSectorDeseado() ? vo.getSectorDeseado() : ""),
				fDato)));
		tabla.addCell(new Paragraph(new Chunk("Puesto pretendido: ",
				fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk(
				(null != vo.getPuestoDeseado() ? vo.getPuestoDeseado() : ""),
				fDato)));
		tabla.addCell(new Paragraph(new Chunk("Área laboral deseada: ",
				fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk((null != vo
				.getAreaLaboralDeseada() ? vo.getAreaLaboralDeseada() : ""),
				fDato)));
		tabla.addCell(new Paragraph(new Chunk("Ocupación deseada: ",
				fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk(
				(null != vo.getOcupacionDeseada() ? vo.getOcupacionDeseada()
						: ""), fDato)));
		tabla.addCell(new Paragraph(new Chunk("Salario pretendido: ",
				fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk(
				(vo.getSalarioPretendido() > 0 ? 
						mx.gob.stps.portal.movil.web.infra.utils.Utils.formatMoney(vo.getSalarioPretendido()) : ""),
				fDato)));
		return tabla;
	}

	private PdfPTable getUbicacion(PerfilJB perfil, PerfilLaboralVo vo) {
		float[] width = new float[2];
		width[0] = .5f;
		width[1] = .5f;
		PdfPTable tabla = new PdfPTable(width);
		tabla.getDefaultCell().setBorder(0);
		tabla.setTotalWidth(250);
		tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
		tabla.setLockedWidth(true);
		tabla.addCell(new Paragraph(new Chunk("Horario contacto: ",
				fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk((null != perfil
				.getHorarioLlamar() ? perfil.getHorarioLlamar() : ""), fDato)));
		tabla.addCell(new Paragraph(new Chunk("Tipo de contrato: ",
				fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk(((null != perfil
				.getExpectativaPrincipal() && null != perfil
				.getExpectativaPrincipal().getTipoContrato()) ? perfil
				.getExpectativaPrincipal().getTipoContrato() : ""), fDato)));
		tabla.addCell(new Paragraph(new Chunk("Entidad: ", fCampoNaranja)));
		tabla.addCell(new Paragraph(
				new Chunk((null != perfil.getEntidad() ? perfil.getEntidad()
						: ""), fDato)));
		tabla.addCell(new Paragraph(new Chunk("Municipio: ", fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk(
				(null != perfil.getMunicipio() ? perfil.getMunicipio() : ""),
				fDato)));
		tabla.addCell(new Paragraph(new Chunk("Disponibilidad de viajar: ",
				fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk(
				(vo.getDisponibilidadViajar() > 0 ? Utils
						.getLblDisponibilidad(vo.getDisponibilidadViajar())
						: ""), fDato)));
		tabla.addCell(new Paragraph(new Chunk(
				"Disponibilidad para radicar fuera: ", fCampoNaranja)));
		tabla.addCell(new Paragraph(new Chunk(
				(vo.getDisponibilidadRadicar() > 0 ? Utils
						.getLblDisponibilidad(vo.getDisponibilidadRadicar())
						: ""), fDato)));
		return tabla;
	}

	private Image getImage(URL url, int ancho, int alto)
			throws BadElementException, MalformedURLException, IOException {
		Image image = Image.getInstance(url);
		image.scaleAbsolute(ancho, alto);
		image.setAlignment(Image.LEFT | Image.UNDERLYING);
		return image;
	}

	private Image getImagen(String nombreImagen, int ancho, int alto)
			throws Exception {
		Image image = Image.getInstance(nombreImagen);
		image.scaleAbsolute(ancho, alto);
		image.setAlignment(Image.LEFT | Image.UNDERLYING);
		return image;
	}

	private PdfPTable getPlecaGris(String ruta) {
		PdfPTable tabla = new PdfPTable(1);
		tabla.getDefaultCell().setBorder(0);
		tabla.setTotalWidth(400);
		tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
		tabla.setLockedWidth(true);
		try {
			tabla.addCell(getImagen(ruta, 400, 2));
		} catch (Exception e) {
			if (bDebug)
				System.out.println(e.getMessage());
		}
		return tabla;
	}

	private PdfPTable getSubtitulo(String subtitulo) {
		float[] width = new float[2];
		width[0] = .02f;
		width[1] = .6f;
		PdfPTable tabla = new PdfPTable(width);
		tabla.getDefaultCell().setBorder(0);
		tabla.setTotalWidth(250);
		tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
		tabla.setLockedWidth(true);
		try {
			tabla.addCell(getImagen(directorio + "images" + File.separator
					+ "cv1_bullet.jpg", 10, 10));
			tabla.addCell(new Paragraph(new Chunk(subtitulo, fTituloSubTabla)));
		} catch (Exception e) {
			if (bDebug)
				System.out.println(e.getMessage());
		}
		return tabla;
	}

	private PdfPTable getTitulo(String titulo) {
		PdfPTable tabla = new PdfPTable(1);
		tabla.getDefaultCell().setBorder(0);
		tabla.setTotalWidth(400);
		tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
		tabla.setLockedWidth(true);
		tabla.addCell(new Paragraph(new Chunk("     " + titulo, fTituloTabla)));
		return tabla;
	}

	private PdfPTable getFoto(long idCandidato, String context)
			throws MalformedURLException {
		PdfPTable tabla = new PdfPTable(1);
		tabla.getDefaultCell().setBorder(0);
		tabla.setTotalWidth(70);
		tabla.setHorizontalAlignment(Element.ALIGN_RIGHT);
		tabla.setLockedWidth(true);
		URL url = new URL(context +"imageAction.m?method=fotoCandidato&idCandidato="+ idCandidato);
		// URL url = new
		// URL("http://localhost:7001/STPSEmpleoWebBack/detalleoferta.do?method=paintLogo");
		try {
			// tabla.addCell(getImagen(directorio+"images" + File.separator +
			// "cv1_photo.jpg", 70, 70));
			tabla.addCell(getImage(url, 400, 30));
		} catch (Exception e) {
			if (bDebug)
				System.out.println(e.getMessage());
		}
		return tabla;
	}

	private PdfPTable getFooter() {
		PdfPTable tabla = new PdfPTable(1);
		tabla.getDefaultCell().setBorder(0);
		tabla.setTotalWidth(400);
		tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
		tabla.setLockedWidth(true);
		try {
			tabla.addCell(getImagen(directorio + "images" + File.separator
					+ "cv1_footer.jpg", 400, 30));
		} catch (Exception e) {
			if (bDebug)
				System.out.println(e.getMessage());
		}
		return tabla;
	}

	private int pointer(Document documento, int advance, int block) {
		if (isAvailable(block))
			pointer -= advance;
		else {
			pointer = supLimit;
			try {
				documento.newPage();
				documento.add(Chunk.NEWLINE);
			} catch (DocumentException de) {
				de.printStackTrace();
			}
		}
		return pointer;
	}

	private boolean isAvailable(int block) {
		if (block == 0)
			return true;
		int tmp = pointer;
		tmp -= block;
		if (tmp < 0)
			return false;
		else
			return true;
	}

	private int leap(HistoriaLaboralVO vo) {
		int q = 0;
		if (null == vo || null == vo.getLogro())
			q = 160;
		int length = vo.getLogro().length();
		if (length < 50)
			q = 180;
		else if (length < 100)
			q = 220;
		else if (length < 200)
			q = 240;
		else if (length < 300)
			q = 260;
		else if (length < 400)
			q = 280;
		else
			q = 210;
		if (null != vo && null != vo.getFuncion()) {
			length = vo.getFuncion().length();
			if (length < 50)
				q += 35;
			else if (length < 100)
				q += 45;
			else if (length < 200)
				q += 60;
			else if (length < 300)
				q += 75;
			else if (length < 400)
				q += 90;
			else
				q += 110;
		}
		return q;
	}
}