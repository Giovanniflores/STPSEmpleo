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

public class FormatCVYouth {
	
	int pointerA = 750;
	int pointerB = 750;
	int supLimit = 800;
	boolean page = true;
	boolean bDebug = true;
    String directorio="";
    Font fNombre = new Font(FontFactory.getFont(FontFactory.HELVETICA_BOLD,12));
    Font fTitulo = new Font(FontFactory.getFont(FontFactory.HELVETICA_BOLD,10));
    Font fCampoGris = new Font(FontFactory.getFont(FontFactory.HELVETICA_BOLD,9));
    Font fCampoNaranja = new Font(FontFactory.getFont(FontFactory.HELVETICA_BOLD,9));
    Font fDatoGris = new Font(FontFactory.getFont(FontFactory.HELVETICA_BOLD,8));
    Font fDatoVerde = new Font(FontFactory.getFont(FontFactory.HELVETICA_BOLD,8));

    public Document generaPDF(PerfilJB perfil, Document doc, PdfWriter writer, String dir, String context) throws Exception {

        PdfPTable tabla = null;
        directorio=dir;
        fNombre.setColor(102,102,102);
        fTitulo.setColor(102,102,102);
        fCampoGris.setColor(102,102,102);
        fCampoNaranja.setColor(253,104,4);
        fDatoGris.setColor(150,150,150);
        fDatoVerde.setColor(67,96,96);

        try {
            // Encabezado
            tabla = getImagen2(directorio+"images" + File.separator + "headercv.jpg", 500 , 40, Element.ALIGN_CENTER);
            tabla.writeSelectedRows(0, -1, 55, 800, writer.getDirectContent());

            // Nombre
            tabla = getNombre(perfil);
            tabla.writeSelectedRows(0, -1, 70, 775, writer.getDirectContent());

            // INICIA COLUMNA UNO ********************************

            // Fondo gris columna izquierda
            tabla = getImagen2(directorio+"images" + File.separator + "shadow.jpg", 160 , 725, Element.ALIGN_CENTER);
            tabla.writeSelectedRows(0, -1, 55, pointa(doc,tabla,writer,0,0), writer.getDirectContent());//750

            // Foto
            tabla = getFoto(perfil.getIdCandidato(), context);
            tabla.writeSelectedRows(0, -1, 75, pointa(doc,tabla,writer,12,0), writer.getDirectContent());//738

            // Fondo Titulo DATOS GENERALES
            tabla = getImagen2(directorio+"images" + File.separator + "lcDeco.jpg", 160 , 40, Element.ALIGN_RIGHT);
            tabla.writeSelectedRows(0, -1, 55, pointa(doc,tabla,writer,128,0), writer.getDirectContent());//610

            // Titulo DATOS GENERALES
            tabla = getTitulo("DATOS GENERALES");
            tabla.writeSelectedRows(0, -1, 75, pointa(doc,tabla,writer,3,0), writer.getDirectContent());//607

            // Tabla DATOS GENERALES
            tabla = getDatosGenerales(perfil);
            tabla.writeSelectedRows(0, -1, 75, pointa(doc,tabla,writer,22,0), writer.getDirectContent());//585

            // Fondo Titulo CONTACTO
            tabla = getImagen2(directorio+"images" + File.separator + "lcDeco.jpg", 160 , 40, Element.ALIGN_RIGHT);
            tabla.writeSelectedRows(0, -1, 55, pointa(doc,tabla,writer,195,0), writer.getDirectContent());//390

            // Titulo CONTACTO
            tabla = getTitulo("CONTACTO");
            tabla.writeSelectedRows(0, -1, 75, pointa(doc,tabla,writer,3,0), writer.getDirectContent());//387

            // Tabla CONTACTO
            tabla = getDatosContacto(perfil);
            tabla.writeSelectedRows(0, -1, 75, pointa(doc,tabla,writer,22,0), writer.getDirectContent());//365

            // INICIA COLUMNA DOS  PAGINA 1 ********************************

            // Fondo gris columna derecha
            tabla = getImagen2(directorio+"images" + File.separator + "shadow2.jpg", 343 , 725, Element.ALIGN_LEFT);
            tabla.writeSelectedRows(0, -1, 212, pointb(doc,tabla,writer,0,0), writer.getDirectContent());//750

            // Fondo Titulo DATOS ACTUALES
            tabla = getImagen2(directorio+"images" + File.separator + "rcDeco.jpg", 343 , 40, Element.ALIGN_LEFT);
            tabla.writeSelectedRows(0, -1, 212, pointb(doc,tabla,writer,0,0), writer.getDirectContent());//750

            // Titulo DATOS ACTUALES
            tabla = getTitulo("DATOS ACTUALES");
            tabla.writeSelectedRows(0, -1, 232, pointb(doc,tabla,writer,5,0), writer.getDirectContent());//745

            // Tabla DATOS ACTUALES
            tabla = getDatosActuales(perfil);
            tabla.writeSelectedRows(0, -1, 232, pointb(doc,tabla,writer,20,0), writer.getDirectContent());//725

            // Fondo Titulo GRADO ACADÉMICO
            tabla = getImagen2(directorio+"images" + File.separator + "rcDeco.jpg", 343 , 40, Element.ALIGN_LEFT);
            tabla.writeSelectedRows(0, -1, 212, pointb(doc,tabla,writer,110,0), writer.getDirectContent());//575

            // Titulo GRADO ACADÉMICO
            tabla = getTitulo("GRADO ACADÉMICO");
            tabla.writeSelectedRows(0, -1, 232, pointb(doc,tabla,writer,5,0), writer.getDirectContent());//570

            // Tabla GRADO ACADÉMICO
            tabla = getGradoAcademico(perfil.getGradoPrincipal());
            tabla.writeSelectedRows(0, -1, 232, pointb(doc,tabla,writer,20,0), writer.getDirectContent());//550

            // Fondo Titulo CONOCIMIENTOS Y HABILIDADES
            tabla = getImagen2(directorio+"images" + File.separator + "rcDeco.jpg", 343 , 40, Element.ALIGN_LEFT);
            tabla.writeSelectedRows(0, -1, 212, pointb(doc,tabla,writer,160,0), writer.getDirectContent());//390

            // Titulo CONOCIMIENTOS Y HABILIDADES
            tabla = getTitulo("CONOCIMIENTOS Y HABILIDADES");
            tabla.writeSelectedRows(0, -1, 232, pointb(doc,tabla,writer,5,0), writer.getDirectContent());//385

            // Tabla CONOCIMIENTOS Y HABILIDADES
            tabla = getConocimientosHabilidades(perfil, perfil.getIdiomaPrincipal(), perfil.getConocimientoPrincipal(), perfil.getHabilidadPrincipal());
            tabla.writeSelectedRows(0, -1, 232, pointb(doc,tabla,writer,20,0), writer.getDirectContent());//365
            
            /* PAGINA DOS
            doc.newPage();
            doc.add(Chunk.NEWLINE);

            // INICIA COLUMNA UNO  PAGINA 2 ********************************
            // Fondo gris columna izquierda
            tabla = getImagen2(directorio+"images" + File.separator + "shadow.jpg", 160 , 725, Element.ALIGN_CENTER);
            tabla.writeSelectedRows(0, -1, 55, pointa(doc,365,370), writer.getDirectContent());//800

            // INICIA COLUMNA DOS  PAGINA 2 *********************************
            
            // Fondo gris columna derecha
            tabla = getImagen2(directorio+"images" + File.separator + "shadow2.jpg", 343 , 725, Element.ALIGN_LEFT);
            tabla.writeSelectedRows(0, -1, 212, pointb(doc,0,0), writer.getDirectContent());//800*/
            
            // Titulo Computación básica
            tabla = getVineta("Computación básica");
            tabla.writeSelectedRows(0, -1, 232, pointb(doc,tabla,writer,365,425), writer.getDirectContent());//780

            // Tabla Computación básica
            tabla = getComputacionBasica(perfil.getPerfilLaboral());
            tabla.writeSelectedRows(0, -1, 232, pointb(doc,tabla,writer,20,0), writer.getDirectContent());//760

            // Computación Avanzada
            tabla = getVineta("Computación Avanzada");
            tabla.writeSelectedRows(0, -1, 232, pointb(doc,tabla,writer,160,0), writer.getDirectContent());//610

            // Tabla Computación Avanzada
            tabla = getComputacionAvanzada(perfil.getComputacionPrincipal());
            tabla.writeSelectedRows(0, -1, 232, pointb(doc,tabla,writer,20,0), writer.getDirectContent());//590

            // Fondo Titulo EXPERIENCIA PROFESIONAL
            tabla = getImagen2(directorio+"images" + File.separator + "rcDeco.jpg", 343 , 40, Element.ALIGN_LEFT);
            tabla.writeSelectedRows(0, -1, 212, pointb(doc,tabla,writer,130,0), writer.getDirectContent());//445

            // Titulo EXPERIENCIA PROFESIONAL
            tabla = getTitulo("EXPERIENCIA PROFESIONAL");
            tabla.writeSelectedRows(0, -1, 232, pointb(doc,tabla,writer,5,0), writer.getDirectContent());//440

            // Tabla EXPERIENCIA PROFESIONAL
            tabla = getExperienciaLaboral(perfil, perfil.getPerfilLaboral());
            tabla.writeSelectedRows(0, -1, 232, pointb(doc,tabla,writer,20,0), writer.getDirectContent());//420

            // Fondo Titulo SITUACIÓN LABORAL
            tabla = getImagen2(directorio+"images" + File.separator + "rcDeco.jpg", 343 , 40, Element.ALIGN_LEFT);
            tabla.writeSelectedRows(0, -1, 212, pointb(doc,tabla,writer,135,0), writer.getDirectContent());//295

            // Titulo SITUACIÓN LABORAL
            tabla = getTitulo("SITUACIÓN LABORAL");
            tabla.writeSelectedRows(0, -1, 232, pointb(doc,tabla,writer,5,0), writer.getDirectContent());//290

            // Tabla SITUACIÓN LABORAL
            tabla = getSituacionLaboral(perfil);
            tabla.writeSelectedRows(0, -1, 232, pointb(doc,tabla,writer,20,0), writer.getDirectContent());//270

            //  Titulo Datos del empleo actual
            tabla = getVineta("Datos del empleo actual");
            tabla.writeSelectedRows(0, -1, 232, pointb(doc,tabla,writer,40,0), writer.getDirectContent());//230
            
            // Tabla Datos del empleo actual 1
            tabla = getDatosEmpleoActual1(perfil.getTrabajoActual());
            tabla.writeSelectedRows(0, -1, 232, pointb(doc,tabla,writer,20,0), writer.getDirectContent());//210
            
            /* PAGINA TRES
            doc.newPage();
            doc.add(Chunk.NEWLINE);

            // INICIA COLUMNA UNO  PAGINA 3 ********************************

            // Fondo gris columna izquierda
            tabla = getImagen2(directorio+"images" + File.separator + "shadow.jpg", 160 , 800, Element.ALIGN_CENTER);
            tabla.writeSelectedRows(0, -1, 55, pointa(doc,0,810), writer.getDirectContent());//800

            // INICIA COLUMNA DOS  PAGINA 3 *********************************
            // Fondo gris columna derecha
            tabla = getImagen2(directorio+"images" + File.separator + "shadow2.jpg", 343 , 800, Element.ALIGN_LEFT);
            tabla.writeSelectedRows(0, -1, 212, pointb(doc,0,0), writer.getDirectContent());//800*/

            // Tabla Datos del empleo actual 2
            tabla = getDatosEmpleoActual2(perfil, perfil.getTrabajoActual(), perfil.getPerfilLaboral());
            tabla.writeSelectedRows(0, -1, 232, pointb(doc,tabla,writer,220,255), writer.getDirectContent());//780

            // Fondo Titulo EXPECTATIVAS LABORALES
            tabla = getImagen2(directorio+"images" + File.separator + "rcDeco.jpg", 343 , 40, Element.ALIGN_LEFT);
            tabla.writeSelectedRows(0, -1, 212, pointb(doc,tabla,writer,270,0), writer.getDirectContent());//525

            // Titulo EXPECTATIVAS LABORALES
            tabla = getTitulo("EXPECTATIVAS LABORALES");
            tabla.writeSelectedRows(0, -1, 232, pointb(doc,tabla,writer,5,0), writer.getDirectContent());//520

            // Tabla EXPECTATIVAS LABORALES
            tabla = getExpectativasLaborales(perfil.getExpectativaPrincipal());
            tabla.writeSelectedRows(0, -1, 232, pointb(doc,tabla,writer,30,0), writer.getDirectContent());//490

            //  Titulo Ubicación y disponibilidad
            tabla = getVineta("Ubicación y disponibilidad");
            tabla.writeSelectedRows(0, -1, 232, pointb(doc,tabla,writer,150,0), writer.getDirectContent());//330

            // Tabla Ubicación y disponibilidad
            tabla = getUbicacionDisponibilidad(perfil, perfil.getPerfilLaboral());
            tabla.writeSelectedRows(0, -1, 232, pointb(doc,tabla,writer,20,0), writer.getDirectContent());//310

            if (perfil.getIdiomas().size() > 0) {
            	// Fondo Titulo Otros Idiomas
            	tabla = getImagen2(directorio+"images" + File.separator + "rcDeco.jpg", 343 , 40, Element.ALIGN_LEFT);
            	tabla.writeSelectedRows(0, -1, 212, pointb(doc,tabla,writer,160,200 + perfil.getIdiomas().size()*40), writer.getDirectContent());

            	// Titulo Otros Idiomas
            	tabla = getTitulo("OTROS IDIOMAS");
            	tabla.writeSelectedRows(0, -1, 232, pointb(doc,tabla,writer,5,0), writer.getDirectContent());
            
            	// Tabla Idiomas secundarios
            	int step = 40;
            	Iterator<IdiomaVO> it = perfil.getIdiomas().iterator();
            	while (it.hasNext()) {
            		tabla = getIdiomaAdicional(it.next());
            		tabla.writeSelectedRows(0, -1, 232, pointb(doc,tabla,writer,step,0), writer.getDirectContent());
            		if (step == 40) step = 110;
            	}
            }

            if (perfil.getConocimientos().size() > 0) {
            	// Fondo Titulo Otros Conocimientos
            	tabla = getImagen2(directorio+"images" + File.separator + "rcDeco.jpg", 343 , 40, Element.ALIGN_LEFT);
            	tabla.writeSelectedRows(0, -1, 212, pointb(doc,tabla,writer,160,260 + perfil.getConocimientos().size()*110), writer.getDirectContent());

            	// Titulo Otros Conocimientos
            	tabla = getTitulo("OTROS CONOCIMIENTOS");
            	tabla.writeSelectedRows(0, -1, 232, pointb(doc,tabla,writer,5,0), writer.getDirectContent());
            	
            	// Tabla Conocimientos secundarios
            	int step = 30;
            	Iterator<ConocimientoHabilidadVO> it = perfil.getConocimientos().iterator();
            	while (it.hasNext()) {
            		tabla = getConocimientoAdicional(it.next());
            		tabla.writeSelectedRows(0, -1, 232, pointb(doc,tabla,writer,step,0), writer.getDirectContent());
            		if (step == 30) step = 110;
            	}
            }
            
            if (perfil.getHabilidades().size() > 0) {
            	// Fondo Titulo Otras Habilidades
            	tabla = getImagen2(directorio+"images" + File.separator + "rcDeco.jpg", 343 , 40, Element.ALIGN_LEFT);
            	tabla.writeSelectedRows(0, -1, 212, pointb(doc,tabla,writer,160,260 + perfil.getHabilidades().size()*110), writer.getDirectContent());

            	// Titulo Otros Habilidades
            	tabla = getTitulo("OTRAS HABILIDADES");
            	tabla.writeSelectedRows(0, -1, 232, pointb(doc,tabla,writer,5,0), writer.getDirectContent());
            	
            	// Tabla Habilidades secundarias
            	int step = 30;
            	Iterator<ConocimientoHabilidadVO> it = perfil.getHabilidades().iterator();
            	while (it.hasNext()) {
            		tabla = getConocimientoAdicional(it.next());
            		tabla.writeSelectedRows(0, -1, 232, pointb(doc,tabla,writer,step,0), writer.getDirectContent());
            		if (step == 30) step = 110;
            	}
            }

            tabla = getImagen2(directorio+"images" + File.separator + "cv2_bg.jpg", 500 , 40, Element.ALIGN_CENTER);
            if (this.pointerB < 50)
            	tabla.writeSelectedRows(0, -1, 55, pointb(doc,tabla,writer,110,50), writer.getDirectContent());//41
            else
            	tabla.writeSelectedRows(0, -1, 55, pointb(doc,tabla,writer,this.pointerB - 50,50), writer.getDirectContent());//41
            
            // Footer
            tabla = getImagen2(directorio+"images" + File.separator + "marca.jpg", 180 , 50, Element.ALIGN_CENTER);
            tabla.writeSelectedRows(0, -1, 211, this.pointerB + 9, writer.getDirectContent());//50
            
        }catch(DocumentException de){
            if (bDebug) System.out.println(de.getMessage());
        }catch(Exception e){
            if (bDebug) System.out.println(e.getMessage());
        }
        return doc;
    }

    private PdfPTable getNombre(PerfilJB perfil){
        PdfPTable tabla = new PdfPTable(1);
        tabla.getDefaultCell().setBorder(0);
        tabla.setTotalWidth(400);
        tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.setLockedWidth(true);
        StringBuilder name = new StringBuilder();
        name.append((null != perfil.getNombre() ? perfil.getNombre() + " " : ""));
        name.append((null != perfil.getApellido1() ? perfil.getApellido1() + " " : ""));
        name.append((null != perfil.getApellido2() ? perfil.getApellido2() : ""));
        tabla.addCell(new Paragraph(new Chunk(name.toString(), fNombre)));
        return tabla;
    }

    private PdfPTable getFoto(long idCandidato, String context) throws MalformedURLException {
        PdfPTable tabla = new PdfPTable(1);
        tabla.getDefaultCell().setBorder(0);
        tabla.setTotalWidth(120);
        tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.setLockedWidth(true);
        URL url = new URL(context + "imageAction.m?method=fotoCandidato&idCandidato="+ idCandidato);
        try{
        	tabla.addCell(getImageURL(url, 100, 120));
            //tabla.addCell(getImagen(directorio+"images" + File.separator + "jn.jpg", 100, 120));
        }catch(Exception e) {
            if (bDebug) System.out.println(e.getMessage());
        }
        return tabla;
    }
    
    private PdfPTable getDatosGenerales(PerfilJB perfil){
        PdfPTable tabla = new PdfPTable(1);
        tabla.getDefaultCell().setBorder(0);
        tabla.setTotalWidth(120);
        tabla.setHorizontalAlignment(Element.ALIGN_JUSTIFIED_ALL);
        tabla.setLockedWidth(true);
        if (perfil.getConfidencialidad() == CONFIDENCIALIDAD.NO.getIdOpcion()) {
        	tabla.addCell(new Paragraph(new Chunk("Género: ",fCampoNaranja)));
            tabla.addCell(new Paragraph(new Chunk((null != perfil.getGenero() ? perfil.getGenero() : ""),fDatoGris)));
            tabla.addCell(new Paragraph(new Chunk("....................................................",fDatoGris)));
            tabla.addCell(new Paragraph(new Chunk("CURP: ", fCampoNaranja)));
            tabla.addCell(new Paragraph(new Chunk((null != perfil.getCurp() ? perfil.getCurp() : ""), fDatoGris)));
            tabla.addCell(new Paragraph(new Chunk("....................................................",fDatoGris)));
            tabla.addCell(new Paragraph(new Chunk("Edad: ", fCampoNaranja)));
            tabla.addCell(new Paragraph(new Chunk((perfil.getEdad() > 0 ? String.valueOf(perfil.getEdad()) : ""), fDatoGris)));
            tabla.addCell(new Paragraph(new Chunk("....................................................",fDatoGris)));
            tabla.addCell(new Paragraph(new Chunk("Fecha de nacimiento: ", fCampoNaranja)));
            tabla.addCell(new Paragraph(new Chunk(perfil.getFormattedFechaNacimiento(), fDatoGris)));
            tabla.addCell(new Paragraph(new Chunk("....................................................",fDatoGris)));
            tabla.addCell(new Paragraph(new Chunk("Entidad de nacimiento: ", fCampoNaranja)));
            tabla.addCell(new Paragraph(new Chunk((null != perfil.getEntidadNacimiento() ? perfil.getEntidadNacimiento() : ""), fDatoGris)));
            tabla.addCell(new Paragraph(new Chunk("....................................................",fDatoGris)));
        }else {
        	tabla.addCell(new Paragraph(new Chunk("Género: ",fCampoNaranja)));
            tabla.addCell(new Paragraph(new Chunk((null != perfil.getGenero() ? perfil.getGenero() : ""),fDatoGris)));
            tabla.addCell(new Paragraph(new Chunk("....................................................",fDatoGris)));
            tabla.addCell(new Paragraph(new Chunk("CURP: ", fCampoNaranja)));
            tabla.addCell(new Paragraph(new Chunk("", fDatoGris)));
            tabla.addCell(new Paragraph(new Chunk("....................................................",fDatoGris)));
            tabla.addCell(new Paragraph(new Chunk("Edad: ", fCampoNaranja)));
            tabla.addCell(new Paragraph(new Chunk((perfil.getEdad() > 0 ? String.valueOf(perfil.getEdad()) : ""), fDatoGris)));
            tabla.addCell(new Paragraph(new Chunk("....................................................",fDatoGris)));
            tabla.addCell(new Paragraph(new Chunk("Fecha de nacimiento: ", fCampoNaranja)));
            tabla.addCell(new Paragraph(new Chunk("", fDatoGris)));
            tabla.addCell(new Paragraph(new Chunk("....................................................",fDatoGris)));
            tabla.addCell(new Paragraph(new Chunk("Entidad de nacimiento: ", fCampoNaranja)));
            tabla.addCell(new Paragraph(new Chunk("", fDatoGris)));
            tabla.addCell(new Paragraph(new Chunk("....................................................",fDatoGris)));
        }
        return tabla;
    }
    
    private PdfPTable getDatosContacto(PerfilJB perfil) {
        PdfPTable tabla = new PdfPTable(1);
        tabla.getDefaultCell().setBorder(0);
        tabla.setTotalWidth(120);
        tabla.setHorizontalAlignment(Element.ALIGN_JUSTIFIED_ALL);
        tabla.setLockedWidth(true);
     //   TelefonoVO tel = null;
     /*   if (null != perfil.getPrincipal()) tel = perfil.getPrincipal(); else tel = new TelefonoVO();*/
        tabla.addCell(new Paragraph(new Chunk("Correo electrónico: ", fCampoNaranja)));
        if (perfil.getPerfilLaboral().getContactoCorreo() == CONTACTO_CORREO.NO.getIdContactoCorreo())
        	tabla.addCell(new Paragraph(new Chunk((null != perfil.getCorreoElectronico() ? perfil.getCorreoElectronico() : ""), fDatoGris)));
        else
        	tabla.addCell(new Paragraph(new Chunk("", fDatoGris)));
        if (perfil.getPerfilLaboral().getContactoTelefono() == CONTACTO_TELEFONO.NO.getIdContactoTelefono()) {
        	tabla.addCell(new Paragraph(new Chunk("Teléfono: ", fCampoNaranja)));
        //    tabla.addCell(new Paragraph(new Chunk((null != tel.getTelefono() ? tel.getTelefono() : ""), fDatoGris)));
            tabla.addCell(new Paragraph(new Chunk("....................................................",fDatoGris)));
            tabla.addCell(new Paragraph(new Chunk("Tipo de teléfono: ", fCampoNaranja)));
           // tabla.addCell(new Paragraph(new Chunk((tel.getIdTipoTelefono() > 0 ? Utils.getTipoTelefono((int)tel.getIdTipoTelefono()) : ""), fDatoGris)));
            tabla.addCell(new Paragraph(new Chunk("....................................................",fDatoGris)));
            tabla.addCell(new Paragraph(new Chunk("Horario de contacto: ", fCampoNaranja)));
            tabla.addCell(new Paragraph(new Chunk((null != perfil.getHorarioLlamar() ? perfil.getHorarioLlamar() : ""), fDatoGris)));
            tabla.addCell(new Paragraph(new Chunk("....................................................",fDatoGris)));
        }else {
        	tabla.addCell(new Paragraph(new Chunk("Teléfono: ", fCampoNaranja)));
            tabla.addCell(new Paragraph(new Chunk("", fDatoGris)));
            tabla.addCell(new Paragraph(new Chunk("....................................................",fDatoGris)));
            tabla.addCell(new Paragraph(new Chunk("Tipo de teléfono: ", fCampoNaranja)));
            tabla.addCell(new Paragraph(new Chunk("", fDatoGris)));
            tabla.addCell(new Paragraph(new Chunk("....................................................",fDatoGris)));
            tabla.addCell(new Paragraph(new Chunk("Horario de contacto: ", fCampoNaranja)));
            tabla.addCell(new Paragraph(new Chunk("", fDatoGris)));
            tabla.addCell(new Paragraph(new Chunk("....................................................",fDatoGris)));
        }
        return tabla;
    }
    
    private PdfPTable getDatosActuales(PerfilJB perfil){
        PdfPTable tabla = new PdfPTable(1);
        tabla.getDefaultCell().setBorder(0);
        tabla.setTotalWidth(300);
        tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla.setLockedWidth(true);
        StringBuilder address = new StringBuilder();
        //address.append((null != perfil.getCalle() ? perfil.getCalle() : "") + " ");
        //address.append((null != perfil.getNumeroExterior() ? perfil.getNumeroExterior() : "") + " ");
        //address.append((null != perfil.getNumeroInterior() ? perfil.getNumeroInterior() : "") + " ");
        address.append((null != perfil.getColonia() ? perfil.getColonia() : "") + " ");
        tabla.addCell(new Paragraph(new Chunk("Dirección: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk(address.toString().trim(), fDatoGris)));
        address = new StringBuilder();
        //address.append((null != perfil.getEntreCalle() ? perfil.getEntreCalle() : "") + " ");
        //address.append((null != perfil.getyCalle() ? " y calle: " + perfil.getyCalle() : ""));
        //tabla.addCell(new Paragraph(new Chunk("Entre " + address.toString().trim(),fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Código postal: ",fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != perfil.getCodigoPostal() ? perfil.getCodigoPostal() : ""), fDatoGris)));
        /*tabla.addCell(new Paragraph(new Chunk("País: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk("México", fDatoGris)));*/
        tabla.addCell(new Paragraph(new Chunk("Entidad: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != perfil.getEntidad() ? perfil.getEntidad() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Municipio o delegación: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != perfil.getMunicipio() ? perfil.getMunicipio() : ""), fDatoGris)));
        return tabla;
    }
    
    private PdfPTable getGradoAcademico(GradoAcademicoVO vo) {
        PdfPTable tabla = new PdfPTable(1);
        tabla.getDefaultCell().setBorder(0);
        tabla.setTotalWidth(300);
        tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla.setLockedWidth(true);
        tabla.addCell(new Paragraph(new Chunk("Grado de estudios: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getNivel() ? vo.getNivel() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Carrera o especialidad: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getCarrera() ? vo.getCarrera() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Escuela de procedencia: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getEscuela() ? vo.getEscuela() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Año de inicio: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((vo.getInicio() > 0 ? String.valueOf(vo.getInicio()) : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Año de terminación: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((vo.getFin() > 0 ? String.valueOf(vo.getFin()) : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Situación académica: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getSituacion() ? vo.getSituacion() : ""), fDatoGris)));
        return tabla;
    }
    
    private PdfPTable getConocimientosHabilidades(PerfilJB perfil, IdiomaVO idioma, ConocimientoHabilidadVO conocimiento, ConocimientoHabilidadVO habilidad){
        PdfPTable tabla = new PdfPTable(1);
        tabla.getDefaultCell().setBorder(0);
        tabla.setTotalWidth(300);
        tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla.setLockedWidth(true);
        tabla.addCell(new Paragraph(new Chunk("Idioma: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != idioma.getIdioma() ? idioma.getIdioma() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Certificación: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != idioma.getCertificacion() ? idioma.getCertificacion() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Dominio: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != idioma.getDominio() ? idioma.getDominio() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Conocimientos: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != conocimiento.getConocimientoHabilidad() ? conocimiento.getConocimientoHabilidad() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Dominio: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != conocimiento.getDominio() ? conocimiento.getDominio() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Descripción: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != conocimiento.getDescripcion() ? conocimiento.getDescripcion() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Experiencia: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != conocimiento.getExperiencia() ? conocimiento.getExperiencia() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Habilidades: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != habilidad.getConocimientoHabilidad() ? habilidad.getConocimientoHabilidad() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Dominio: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != habilidad.getDominio() ? habilidad.getDominio() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Descripción: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != habilidad.getDescripcion() ? habilidad.getDescripcion() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Experiencia: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != habilidad.getExperiencia() ? habilidad.getExperiencia() : ""), fDatoGris)));
        return tabla;
    }
    
    private PdfPTable getIdiomaAdicional(IdiomaVO idioma) {
        PdfPTable tabla = new PdfPTable(1);
        tabla.getDefaultCell().setBorder(0);
        tabla.setTotalWidth(300);
        tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla.setLockedWidth(true);
        tabla.addCell(new Paragraph(new Chunk((null != idioma.getIdioma() ? idioma.getIdioma() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Certificación: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != idioma.getCertificacion() ? idioma.getCertificacion() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Dominio: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != idioma.getDominio() ? idioma.getDominio() : ""), fDatoGris)));
        return tabla;
    }
    
    private PdfPTable getConocimientoAdicional(ConocimientoHabilidadVO conocimiento){
        PdfPTable tabla = new PdfPTable(1);
        tabla.getDefaultCell().setBorder(0);
        tabla.setTotalWidth(300);
        tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla.setLockedWidth(true);
        tabla.addCell(new Paragraph(new Chunk("Conocimiento: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != conocimiento.getConocimientoHabilidad() ? conocimiento.getConocimientoHabilidad() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Dominio: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != conocimiento.getDominio() ? conocimiento.getDominio() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Descripción: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != conocimiento.getDescripcion() ? conocimiento.getDescripcion() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Experiencia: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != conocimiento.getExperiencia() ? conocimiento.getExperiencia() : ""), fDatoGris)));
        return tabla;
    }
    
    private PdfPTable getComputacionBasica(PerfilLaboralVo vo){
        PdfPTable tabla = new PdfPTable(1);
        tabla.getDefaultCell().setBorder(0);
        tabla.setTotalWidth(300);
        tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla.setLockedWidth(true);
        tabla.addCell(new Paragraph(new Chunk("Experiencia en uso de computadora: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((vo.getIdExperienciaCompu() > 0 ? Utils.getExperiencia((int)vo.getIdExperienciaCompu()) : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Dominio en uso de computadora: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((vo.getIdDominioCompu() > 0 ? Utils.getDominio((int)vo.getIdDominioCompu()) : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Experiencia Microsoft Office: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((vo.getIdExperienciaOffice() > 0 ? Utils.getExperiencia((int)vo.getIdExperienciaOffice()) : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Dominio Microsoft Office: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((vo.getIdDominioOffice() > 0 ? Utils.getDominio((int)vo.getIdDominioOffice()) : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Experiencia Internet: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((vo.getIdExperienciaInternet() > 0 ? Utils.getExperiencia((int)vo.getIdExperienciaInternet()) : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Dominio Internet: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((vo.getIdDominioInternet() > 0 ? Utils.getDominio((int)vo.getIdDominioInternet()) : ""), fDatoGris)));
        return tabla;
    }
    
    private PdfPTable getComputacionAvanzada(ComputacionAvanzadaVO vo){
        PdfPTable tabla = new PdfPTable(1);
        tabla.getDefaultCell().setBorder(0);
        tabla.setTotalWidth(300);
        tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla.setLockedWidth(true);
        tabla.addCell(new Paragraph(new Chunk("Software o Hardware: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != vo && null != vo.getSoftwareHardware() ? vo.getSoftwareHardware() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Experiencia: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != vo && null != vo.getExperiencia() ? vo.getExperiencia() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Dominio: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != vo && null != vo.getDominio() ? vo.getDominio() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Descripción: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != vo && null != vo.getDescripcion() ? vo.getDescripcion() : ""), fDatoGris)));
        return tabla;
    }
    
    private PdfPTable getExperienciaLaboral(PerfilJB perfil, PerfilLaboralVo vo) {
        PdfPTable tabla = new PdfPTable(1);
        tabla.getDefaultCell().setBorder(0);
        tabla.setTotalWidth(300);
        tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla.setLockedWidth(true);
        tabla.addCell(new Paragraph(new Chunk("Experiencia total: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((vo.getIdExperienciaTotal() > 0 ? Utils.getExperiencia((int)vo.getIdExperienciaTotal()) : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Sector de mayor experiencia: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != perfil.getSector() ? perfil.getSector() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Área de mayor experiencia: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != perfil.getArea() ? perfil.getArea() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Puesto con mayor experiencia: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getPuestoMayorExpr() ? vo.getPuestoMayorExpr() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Ocupación con mayor experiencia:", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != perfil.getOcupacion() ? perfil.getOcupacion() : ""), fDatoGris)));
        return tabla;
    }
    
    private PdfPTable getSituacionLaboral(PerfilJB perfil) {
        PdfPTable tabla = new PdfPTable(1);
        tabla.getDefaultCell().setBorder(0);
        tabla.setTotalWidth(300);
        tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla.setLockedWidth(true);
        tabla.addCell(new Paragraph(new Chunk("¿Trabajas actualmente?", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((perfil.getIdTrabaja() > 0 ? Utils.getLblTrabaja(perfil.getIdTrabaja()) : ""), fDatoGris)));
        return tabla;
    }
    
    private PdfPTable getDatosEmpleoActual1(HistoriaLaboralVO vo) {
        PdfPTable tabla = new PdfPTable(1);
        tabla.getDefaultCell().setBorder(0);
        tabla.setTotalWidth(300);
        tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla.setLockedWidth(true);
        tabla.addCell(new Paragraph(new Chunk("Sector: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getSector() ? vo.getSector() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Puesto: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getPuesto() ? vo.getPuesto() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Área laboral: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getAreaLaboral() ? vo.getAreaLaboral() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Ocupación: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getOcupacion() ? vo.getOcupacion() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Empresa: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getEmpresa() ? vo.getEmpresa() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Fecha inicial: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getLaboresInicial() ? Utils.formatDate(vo.getLaboresInicial()) : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Fecha Final: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getLaboresFinal() ? Utils.formatDate(vo.getLaboresFinal()) : "Actualmente"), fDatoGris)));
        return tabla;
    }
    
    private PdfPTable getDatosEmpleoActual2(PerfilJB perfil, HistoriaLaboralVO vo, PerfilLaboralVo pLab){
        PdfPTable tabla = new PdfPTable(1);
        tabla.getDefaultCell().setBorder(0);
        tabla.setTotalWidth(300);
        tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla.setLockedWidth(true);
        tabla.addCell(new Paragraph(new Chunk("Años laborados: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getAnios() ? vo.getAnios() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Jerarquia del puesto: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getJerarquia() ? vo.getJerarquia() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Personas a cargo: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getPersonas() ? vo.getPersonas() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Funciones desempeñadas: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getFuncion() ? vo.getFuncion() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Logros en la empresa: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getLogro() ? vo.getLogro() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Último salario mensual: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((vo.getSalarioMensual() > 0? mx.gob.stps.portal.movil.web.infra.utils.Utils.formatMoney(vo.getSalarioMensual()) : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Razones por la que busca trabajo:", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((pLab.getIdRazonBusqueda() > 0 ? Utils.getLblRazonBusqueda((int)pLab.getIdRazonBusqueda()) : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Inicio de la búsqueda de empleo: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != pLab.getInicioBusqueda() ? Utils.formatDate(pLab.getInicioBusqueda()) : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Medios utilizados para buscar empleo: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != perfil.getMediosBusqueda() ? perfil.getMediosBusqueda() : ""), fDatoGris)));
        return tabla;
    }
    
    private PdfPTable getExpectativasLaborales(ExpectativaLaboralVO vo){
        PdfPTable tabla = new PdfPTable(1);
        tabla.getDefaultCell().setBorder(0);
        tabla.setTotalWidth(300);
        tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla.setLockedWidth(true);
        tabla.addCell(new Paragraph(new Chunk("Sector deseado: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getSectorDeseado() ? vo.getSectorDeseado() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Puesto pretendido: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getPuestoDeseado() ? vo.getPuestoDeseado() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Área laboral deseada: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getAreaLaboralDeseada() ? vo.getAreaLaboralDeseada() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Ocupación deseada: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getOcupacionDeseada() ? vo.getOcupacionDeseada() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Salario pretendido: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((vo.getSalarioPretendido() > 0 ? mx.gob.stps.portal.movil.web.infra.utils.Utils.formatMoney(vo.getSalarioPretendido()) : ""), fDatoGris)));
        return tabla;
    }
    
    private PdfPTable getUbicacionDisponibilidad(PerfilJB perfil, PerfilLaboralVo vo) {
        PdfPTable tabla = new PdfPTable(1);
        tabla.getDefaultCell().setBorder(0);
        tabla.setTotalWidth(300);
        tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla.setLockedWidth(true);
        tabla.addCell(new Paragraph(new Chunk("Horario de contacto: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != perfil.getHorarioLlamar() ? perfil.getHorarioLlamar() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Tipo de contrato: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk(((null != perfil.getExpectativaPrincipal() && null != perfil.getExpectativaPrincipal().getTipoContrato()) ? perfil.getExpectativaPrincipal().getTipoContrato() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Entidad: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != perfil.getEntidad() ? perfil.getEntidad() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Municipio: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((null != perfil.getMunicipio() ? perfil.getMunicipio() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Disponibilidad de viajar: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((vo.getDisponibilidadViajar() > 0 ? Utils.getLblDisponibilidad(vo.getDisponibilidadViajar()) : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Disponibilidad para radicar fuera: ", fCampoNaranja)));
        tabla.addCell(new Paragraph(new Chunk((vo.getDisponibilidadRadicar() > 0 ? Utils.getLblDisponibilidad(vo.getDisponibilidadRadicar()) : ""), fDatoGris)));
        return tabla;
    }
    
    private PdfPTable getTitulo(String titulo){
        PdfPTable tabla = new PdfPTable(1);
        tabla.getDefaultCell().setBorder(0);
        tabla.setTotalWidth(200);
        tabla.setHorizontalAlignment(Element.ALIGN_JUSTIFIED_ALL);
        tabla.setLockedWidth(true);
        tabla.addCell(new Paragraph(new Chunk(titulo, fTitulo)));
        return tabla;
    }
    
    private Image getImageURL(URL url, int ancho, int alto) throws BadElementException, MalformedURLException, IOException {
    	Image image = Image.getInstance(url);
    	image.scaleAbsolute(ancho,alto);
        image.setAlignment(Image.LEFT | Image.UNDERLYING);
        return image;
    }
    
    private Image getImagen(String nombreImagen, int ancho, int alto) throws Exception{
        Image image = Image.getInstance(nombreImagen);
        image.scaleAbsolute(ancho,alto);
        image.setAlignment(Image.LEFT | Image.UNDERLYING);
        return image;
    }
    
    private PdfPTable getImagen2(String nombreImagen, int ancho, int alto, int align) throws Exception{
        Image image = Image.getInstance(nombreImagen);
        image.scaleAbsolute(ancho,alto);
        image.setAlignment(Image.LEFT | Image.UNDERLYING);

        PdfPTable tabla = new PdfPTable(1);
        tabla.getDefaultCell().setBorder(0);
        tabla.setTotalWidth(ancho);
        tabla.setHorizontalAlignment(align);
        tabla.setLockedWidth(true);
        try{
            tabla.addCell(image);
        }catch(Exception e){
            if (bDebug) System.out.println(e.getMessage());
        }
        return tabla;
    }
    
    private PdfPTable getVineta(String campo){
        float[] width = new float[2];
        width[0]=.02f;
        width[1]=.6f;
        PdfPTable tabla = new PdfPTable(width);
        tabla.getDefaultCell().setBorder(0);
        tabla.setTotalWidth(250);
        tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla.setLockedWidth(true);
        try{
            tabla.addCell(getImagen(directorio+"images" + File.separator + "bullet.jpg", 3, 3));
            tabla.addCell(new Paragraph(new Chunk(campo, fCampoGris)));
        }catch(Exception e){
            if (bDebug) System.out.println(e.getMessage());
        }
        return tabla;
    }
    
    private int pointa(Document documento, PdfPTable tabla, PdfWriter writer, int advance, int block) {
    	if (isAvailableA(block))
    		pointerA -= advance;
    	else {
    		pointerA = supLimit;
    		pointerB = supLimit;
    		try {
    			if (page) {
    				documento.newPage();
    				documento.add(Chunk.NEWLINE);
    				
    				// Fondo gris columna izquierda
    	            tabla = getImagen2(directorio+"images" + File.separator + "shadow.jpg", 160 , 725, Element.ALIGN_CENTER);
    	            tabla.writeSelectedRows(0, -1, 55, pointerA, writer.getDirectContent());
    	            
    	            // Fondo gris columna derecha
    	            tabla = getImagen2(directorio+"images" + File.separator + "shadow2.jpg", 343 , 725, Element.ALIGN_LEFT);
    	            tabla.writeSelectedRows(0, -1, 212, pointerB, writer.getDirectContent());
    	            pointerB -= 20;
    			}
    			page = !page;
			} catch (DocumentException de) { de.printStackTrace();
			} catch (Exception e) { e.printStackTrace(); }
    	}
    	return pointerA;
    }
    
    private boolean isAvailableA(int block) {
    	if (block == 0) return true;
    	int tmp = pointerA;
    	tmp -= block;
    	if (tmp < 0)
    		return false;
    	else
    		return true;
    }
    
    private int pointb(Document documento, PdfPTable tabla, PdfWriter writer, int advance, int block) {
    	if (isAvailableB(block)) 
    		pointerB -= advance;
    	else {
    		pointerA = supLimit;
    		pointerB = supLimit;
    		try {
    			documento.newPage();
    			documento.add(Chunk.NEWLINE);

    			// Fondo gris columna izquierda
    			tabla = getImagen2(directorio+"images" + File.separator + "shadow.jpg", 160 , 725, Element.ALIGN_CENTER);
    			tabla.writeSelectedRows(0, -1, 55, pointerA, writer.getDirectContent());

    			// Fondo gris columna derecha
    			tabla = getImagen2(directorio+"images" + File.separator + "shadow2.jpg", 343 , 725, Element.ALIGN_LEFT);
    			tabla.writeSelectedRows(0, -1, 212, pointerB, writer.getDirectContent());
    			pointerB -= 20;
    		} catch (DocumentException de) { de.printStackTrace();
    		} catch (Exception e) { e.printStackTrace(); }
    	}
    	return pointerB;
    }
    
    private boolean isAvailableB(int block) {
    	if (block == 0) return true;
    	int tmp = pointerB;
    	tmp -= block;
    	if (tmp < 0)
    		return false;
    	else
    		return true;
    }
}