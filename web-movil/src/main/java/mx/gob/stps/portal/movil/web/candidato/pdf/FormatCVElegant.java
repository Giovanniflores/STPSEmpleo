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

public class FormatCVElegant {

	int pointer = 790;
	int supLimit = 790;
	boolean bDebug = true;
    String directorio="";
    Font fNombre = new Font(FontFactory.getFont(FontFactory.HELVETICA_BOLD,10));
    Font fTitulo = new Font(FontFactory.getFont(FontFactory.HELVETICA_BOLD,9));
    Font fCampoGris = new Font(FontFactory.getFont(FontFactory.HELVETICA_BOLD,8));
    Font fDatoGris = new Font(FontFactory.getFont(FontFactory.HELVETICA_BOLD,8));
    Font fDatoNaranja = new Font(FontFactory.getFont(FontFactory.HELVETICA_BOLD,8));

    public Document generaPDF(PerfilJB perfil, Document doc, PdfWriter writer, String dir, String context) throws Exception{

        PdfPTable tabla = null;
        directorio=dir;
        fNombre.setColor(67,96,96);
        fTitulo.setColor(67,96,96);
        fCampoGris.setColor(102,102,102);
        fDatoNaranja.setColor(253,104,4);
        fDatoGris.setColor(150,150,150);

        try {
        	
            //Fondo
            doc.add(getImagen(directorio+"images" + File.separator + "cv2_border_bottom1.jpg", 410 , supLimit));

            // Logo
            tabla = getLogo();
            tabla.writeSelectedRows(0, -1, 400, pointer(doc,0,0), writer.getDirectContent());//790

            // Foto
            tabla = getFoto(perfil.getIdCandidato(), context);
            tabla.writeSelectedRows(0, -1, 70, pointer(doc,30,0), writer.getDirectContent());//760

            // Nombre
            tabla = getName(perfil);
            tabla.writeSelectedRows(0, -1, 130, pointer(doc,0,0), writer.getDirectContent());//760

            // Tabla Datos personales
            tabla = getDatosPersonales(perfil);
            tabla.writeSelectedRows(0, -1, 150, pointer(doc,15,0), writer.getDirectContent());//745

            // Linea gris
            tabla = getLineaGris();
            tabla.writeSelectedRows(0, -1, 50, pointer(doc,75,0), writer.getDirectContent());//670

            // Titulo Datos actuales
            tabla = getTitulo("Datos actuales");
            tabla.writeSelectedRows(0, -1, 60, pointer(doc,10,0), writer.getDirectContent());//660

            // Tabla Datos actuales
            tabla = getDatosActuales(perfil);
            tabla.writeSelectedRows(0, -1, 80, pointer(doc,20,0), writer.getDirectContent());//640

            // Titulo Datos para contactar
            tabla = getTitulo("Datos para contactar");
            tabla.writeSelectedRows(0, -1, 60, pointer(doc,60,0), writer.getDirectContent());//550

            // Tabla Datos contacto
            tabla = getDatosContacto(perfil);
            tabla.writeSelectedRows(0, -1, 80, pointer(doc,15,0), writer.getDirectContent());//535

            // Tabla Video
            tabla = getVineta("Ver mi video- curriculum:", ((perfil.getConfidencialidad() == CONFIDENCIALIDAD.NO.getIdOpcion() && null != perfil.getUrlVideo()) ? perfil.getUrlVideo() : ""));
            tabla.writeSelectedRows(0, -1, 80, pointer(doc,55,0), writer.getDirectContent());//480

            // Titulo Grado académico
            tabla = getTitulo("Grado académico");
            tabla.writeSelectedRows(0, -1, 60, pointer(doc,20,0), writer.getDirectContent());//465

            //  Tabla Grado academico
            tabla = getGradoAcademico(perfil.getGradoPrincipal());
            //tabla.writeSelectedRows(0, -1, 80, pointer(doc,25,0), writer.getDirectContent());
            tabla.writeSelectedRows(0, -1, 80, pointer(doc,15,0), writer.getDirectContent());//440

            // Tabla Situacion academica
            tabla = getVineta("Situación académica:", (null != perfil.getGradoPrincipal() ? perfil.getGradoPrincipal().getSituacion() : ""));
            tabla.writeSelectedRows(0, -1, 80, pointer(doc,65,0), writer.getDirectContent());//375

            // Linea gris
            tabla = getLineaGris();
            tabla.writeSelectedRows(0, -1, 50, pointer(doc,15,0), writer.getDirectContent());//360

            //  Titulo Conocimientos y habilidades
            tabla = getTitulo("Conocimientos y habilidades");
            tabla.writeSelectedRows(0, -1, 60, pointer(doc,10,0), writer.getDirectContent());//350

            // Tabla Conocimientos
            tabla = getConocimientos(perfil.getConocimientoPrincipal());
            tabla.writeSelectedRows(0, -1, 80, pointer(doc,15,0), writer.getDirectContent());//335

            // Viñeta
            int leap = leap(perfil.getConocimientoPrincipal());
            tabla = getVineta("Experiencia:", (null != perfil.getConocimientoPrincipal() ? perfil.getConocimientoPrincipal().getExperiencia() : ""));
            tabla.writeSelectedRows(0, -1, 80, pointer(doc,leap,0), writer.getDirectContent());//225

            // Tabla Conocimientos secundarios
	        Iterator<ConocimientoHabilidadVO> itConAds = perfil.getConocimientos().iterator();
	        while (itConAds.hasNext()) {
	        	ConocimientoHabilidadVO conAd = itConAds.next();
	        	tabla = getConocimientos(conAd);
	        	tabla.writeSelectedRows(0, -1, 80, pointer(doc,15,0), writer.getDirectContent());
	    	    
	    	    // Viñeta
	        	leap = leap(conAd);
	        	tabla = getVineta("Experiencia:", (null != conAd.getExperiencia() ? conAd.getExperiencia() : ""));
	            tabla.writeSelectedRows(0, -1, 80, pointer(doc,leap,0), writer.getDirectContent());
	        }
            
            // Tabla Habilidades
            tabla = getHabilidades(perfil.getHabilidadPrincipal());
            tabla.writeSelectedRows(0, -1, 80, pointer(doc,20,130), writer.getDirectContent());//205

            leap = leap(perfil.getHabilidadPrincipal());
            tabla = getVineta("Experiencia:", (null != perfil.getConocimientoPrincipal() ? perfil.getConocimientoPrincipal().getExperiencia() : ""));
            //tabla.writeSelectedRows(0, -1, 80, pointer(doc,110,0), writer.getDirectContent());
            tabla.writeSelectedRows(0, -1, 80, pointer(doc,leap,0), writer.getDirectContent());//95

            // Tabla habilidades secundarias
	        Iterator<ConocimientoHabilidadVO> itHabAds = perfil.getHabilidades().iterator();
	        while (itHabAds.hasNext()) {
	        	ConocimientoHabilidadVO habAd = itHabAds.next();
	        	tabla = getHabilidades(habAd);
	        	tabla.writeSelectedRows(0, -1, 80, pointer(doc,20,130), writer.getDirectContent());
	    	    
	    	    // Viñeta
	        	leap = leap(habAd);
	        	tabla = getVineta("Experiencia:", (null != habAd ? habAd.getExperiencia() : ""));
	            //tabla.writeSelectedRows(0, -1, 80, pointer(doc,110,0), writer.getDirectContent());
	            tabla.writeSelectedRows(0, -1, 80, pointer(doc,leap,0), writer.getDirectContent());
	        }
            
            // Vineta
            tabla = getTitulo("Idiomas");
            tabla.writeSelectedRows(0, -1, 60, pointer(doc,20,75), writer.getDirectContent());//75

            // Tabla Idiomas
            tabla = getIdiomas(perfil.getIdiomaPrincipal());
            tabla.writeSelectedRows(0, -1, 80, pointer(doc,15,0), writer.getDirectContent());//60

            // Tabla idiomas secundarios
            Iterator<IdiomaVO> it = perfil.getIdiomas().iterator();
            while (it.hasNext()) {
            	tabla = getIdiomas(it.next());
            	tabla.writeSelectedRows(0, -1, 80, pointer(doc,40,70), writer.getDirectContent());
            }
            
            /* PAGINA DOS *************************
            doc.newPage();
            doc.add(Chunk.NEWLINE);

            //Fondo
            doc.add(getImagen(directorio+"images" + File.separator + "cv2_border_bottom1.jpg", 410 , 790));*/

            // Vineta
            tabla = getTitulo("Computación básica");
            tabla.writeSelectedRows(0, -1, 60, pointer(doc,60,160), writer.getDirectContent());//760

            // Tabla Computacion basica
            tabla = getComputacionBasica(perfil.getPerfilLaboral());
            tabla.writeSelectedRows(0, -1, 80, pointer(doc,15,0), writer.getDirectContent());//745

            // Tabla Experiencia Office
            tabla = getOffice(perfil.getPerfilLaboral());
            tabla.writeSelectedRows(0, -1, 80, pointer(doc,30,0), writer.getDirectContent());//715

            // Tabla Experiencia Internet
            tabla = getInternet(perfil.getPerfilLaboral());
            tabla.writeSelectedRows(0, -1, 80, pointer(doc,30,0), writer.getDirectContent());//685

            // Vineta
            tabla = getTitulo("Computación Avanzada");
            tabla.writeSelectedRows(0, -1, 60, pointer(doc,30,130), writer.getDirectContent());//655

            // Tabla Computación Avanzada
            tabla = getComputacionAvanzada(perfil.getComputacionPrincipal());
            tabla.writeSelectedRows(0, -1, 80, pointer(doc,15,0), writer.getDirectContent());//640
            
            leap = leap(perfil.getComputacionPrincipal());
            tabla = getVineta("Experiencia:", (null != perfil.getComputacionPrincipal() ? perfil.getComputacionPrincipal().getExperiencia() : ""));
            //tabla.writeSelectedRows(0, -1, 80, pointer(doc,105,0), writer.getDirectContent());
            tabla.writeSelectedRows(0, -1, 80, pointer(doc,leap,0), writer.getDirectContent());//535

            //  Titulo Experiencia laboral
            tabla = getTitulo("Experiencia laboral");
            //tabla.writeSelectedRows(0, -1, 60, pointer(doc,15,115), writer.getDirectContent());
            tabla.writeSelectedRows(0, -1, 60, pointer(doc,20,115), writer.getDirectContent());//520

            // Tabla Experiencia laboral
            tabla = getExperienciaLaboral(perfil, perfil.getPerfilLaboral());
            tabla.writeSelectedRows(0, -1, 80, pointer(doc,15,0), writer.getDirectContent());//505

            tabla = getVineta("Ocupación con mayor experiencia:", (null != perfil.getOcupacion() ? perfil.getOcupacion() : ""));
            //tabla.writeSelectedRows(0, -1, 80, pointer(doc,75,0), writer.getDirectContent());
            tabla.writeSelectedRows(0, -1, 80, pointer(doc,80,0), writer.getDirectContent());//430

            // Linea gris
            tabla = getLineaGris();
            tabla.writeSelectedRows(0, -1, 50, pointer(doc,25,0), writer.getDirectContent());//405

            //  Titulo Situacion laboral
            tabla = getTitulo("Situación laboral");
            tabla.writeSelectedRows(0, -1, 60, pointer(doc,10,30), writer.getDirectContent());//395

            tabla = getVineta("¿Trabajas actualmente?: ", (perfil.getIdTrabaja() > 0 ? Utils.getLblTrabaja(perfil.getIdTrabaja()) : ""));
            tabla.writeSelectedRows(0, -1, 80, pointer(doc,15,0), writer.getDirectContent());//380

            //  Titulo Empleo actual
            tabla = getTitulo("Datos del empleo actual");
            tabla.writeSelectedRows(0, -1, 60, pointer(doc,20,430), writer.getDirectContent());//365

            // Tabla Empleo actual
            tabla = getDatosEmpleoActual(perfil.getTrabajoActual());
            tabla.writeSelectedRows(0, -1, 80, pointer(doc,15,0), writer.getDirectContent());//350

            leap = leap(perfil.getTrabajoActual());
            tabla = getVineta("Último salario mensual: ", (perfil.getTrabajoActual().getSalarioMensual() > 0? mx.gob.stps.portal.movil.web.infra.utils.Utils.formatMoney(perfil.getTrabajoActual().getSalarioMensual()) : ""));
            //tabla.writeSelectedRows(0, -1, 80, pointer(doc,240,0), writer.getDirectContent());
            tabla.writeSelectedRows(0, -1, 80, pointer(doc,leap,0), writer.getDirectContent());//110

            // Tabla Busca Empleo 
            tabla = getBuscaEmpleo(perfil, perfil.getPerfilLaboral());
            tabla.writeSelectedRows(0, -1, 80, pointer(doc,20,80), writer.getDirectContent());//90

            // Linea gris
            tabla = getLineaGris();
            tabla.writeSelectedRows(0, -1, 50, pointer(doc,70,0), writer.getDirectContent());//20

            /* PAGINA TRES *************************
            doc.newPage();
            doc.add(Chunk.NEWLINE);

            //Fondo
            doc.add(getImagen(directorio+"images" + File.separator + "cv2_border_bottom1.jpg", 410 , 760));*/

            // Titulo Expectativa laborales
            tabla = getTitulo("Expectativas laborales");
            tabla.writeSelectedRows(0, -1, 60, pointer(doc,10,110), writer.getDirectContent());//760

            // Tabla Expectativa laborales
            tabla = getExpectativaLaboral(perfil.getExpectativaPrincipal());
            tabla.writeSelectedRows(0, -1, 80, pointer(doc,15,0), writer.getDirectContent());//745

            // Titulo Ubicacion y disponibilidad
            tabla = getTitulo("Ubicación y disponibilidad");
            tabla.writeSelectedRows(0, -1, 60, pointer(doc,90,200), writer.getDirectContent());//655

            // Tabla Ubicacion y disponibilidad
            tabla = getUbicacionDisponibilidad(perfil, perfil.getPerfilLaboral());
            tabla.writeSelectedRows(0, -1, 80, pointer(doc,15,0), writer.getDirectContent());//640

            // Footer
            tabla = getFooter();
            tabla.writeSelectedRows(0, -1, 0, pointer(doc,90,0), writer.getDirectContent());//42

        }catch(DocumentException de){
            if (bDebug) System.out.println(de.getMessage());
        }catch(Exception e){
            if (bDebug) System.out.println(e.getMessage());
        }
        return doc;
    }
    
    private PdfPTable getName(PerfilJB perfil){
        PdfPTable tabla = new PdfPTable(1);
        tabla.getDefaultCell().setBorder(0);
        tabla.setTotalWidth(400);
        tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla.setLockedWidth(true);
        StringBuilder name = new StringBuilder();
        name.append((null != perfil.getNombre() ? perfil.getNombre() + " " : ""));
        name.append((null != perfil.getApellido1() ? perfil.getApellido1() + " " : ""));
        name.append((null != perfil.getApellido2() ? perfil.getApellido2() : ""));
        tabla.addCell(new Paragraph(new Chunk("     " + name.toString(), fTitulo)));
        return tabla;
    }
    
    private PdfPTable getDatosPersonales(PerfilJB perfil){
    	float[] width = new float[2];
    	width[0]=1f;
    	width[1]=2f;
    	PdfPTable tabla = new PdfPTable(width);
    	tabla.getDefaultCell().setBorder(0);
    	tabla.setTotalWidth(300);
    	tabla.setHorizontalAlignment(Element.ALIGN_JUSTIFIED_ALL);
    	tabla.setLockedWidth(true);
    	tabla.setSplitRows(true);
    	if (perfil.getConfidencialidad() == CONFIDENCIALIDAD.NO.getIdOpcion()) {
    		tabla.addCell(new Paragraph(new Chunk("Género: ",fCampoGris)));
    		tabla.addCell(new Paragraph(new Chunk((null != perfil.getGenero() ? perfil.getGenero() : ""),fDatoNaranja)));
    		tabla.addCell(new Paragraph(new Chunk("CURP: ", fCampoGris)));
    		tabla.addCell(new Paragraph(new Chunk((null != perfil.getCurp() ? perfil.getCurp() : ""), fDatoNaranja)));
    		tabla.addCell(new Paragraph(new Chunk("Edad: ", fCampoGris)));
    		tabla.addCell(new Paragraph(new Chunk((perfil.getEdad() > 0 ? String.valueOf(perfil.getEdad()) : ""), fDatoNaranja)));
    		tabla.addCell(new Paragraph(new Chunk("Fecha de nacimiento: ", fCampoGris)));
    		tabla.addCell(new Paragraph(new Chunk(perfil.getFormattedFechaNacimiento(), fDatoNaranja)));
    		tabla.addCell(new Paragraph(new Chunk("Entidad de nacimiento: ", fCampoGris)));
    		tabla.addCell(new Paragraph(new Chunk((null != perfil.getEntidadNacimiento() ? perfil.getEntidadNacimiento() : ""), fDatoNaranja)));
    	}
    	return tabla;
    }
    
    private PdfPTable getDatosActuales(PerfilJB perfil){
        float[] width = new float[2];
        width[0]=1f;
        width[1]=2f;
        PdfPTable tabla = new PdfPTable(width);
        tabla.getDefaultCell().setBorder(0);
        tabla.setTotalWidth(300);
        tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla.setLockedWidth(true);
        StringBuilder address = new StringBuilder();
        //address.append((null != perfil.getCalle() ? perfil.getCalle() : "") + " ");
        //address.append((null != perfil.getNumeroExterior() ? perfil.getNumeroExterior() : "") + " ");
        //address.append((null != perfil.getNumeroInterior() ? perfil.getNumeroInterior() : "") + " ");
        address.append((null != perfil.getColonia() ? perfil.getColonia() : "") + " ");
        tabla.addCell(new Paragraph(new Chunk("Dirección: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk(address.toString().trim(), fDatoGris)));
        address = new StringBuilder();
        //address.append((null != perfil.getEntreCalle() ? perfil.getEntreCalle() : "") + " ");
        //address.append((null != perfil.getyCalle() ? " y calle: " + perfil.getyCalle() : ""));
        tabla.addCell(new Paragraph(new Chunk("", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk(""/*"Entre " + address.toString().trim()*/,fDatoNaranja)));
        tabla.addCell(new Paragraph(new Chunk("Código postal: ",fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((null != perfil.getCodigoPostal() ? perfil.getCodigoPostal() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Entidad: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((null != perfil.getEntidad() ? perfil.getEntidad() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Municipio o delegación: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((null != perfil.getMunicipio() ? perfil.getMunicipio() : ""), fDatoGris)));
        return tabla;
    }
    
    private PdfPTable getDatosContacto(PerfilJB perfil) {
        float[] width = new float[2];
        width[0]=1f;
        width[1]=2f;
        //TelefonoVO tel = null;
        PdfPTable tabla = new PdfPTable(width);
        tabla.getDefaultCell().setBorder(0);
        tabla.setTotalWidth(300);
        tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla.setLockedWidth(true);
    /*    if (null != perfil.getPrincipal()) tel = perfil.getPrincipal(); else tel = new TelefonoVO();
        tabla.addCell(new Paragraph(new Chunk("Correo electrónico: ", fCampoGris)));
        if (perfil.getPerfilLaboral().getContactoCorreo() == CONTACTO_CORREO.NO.getIdContactoCorreo())
        	tabla.addCell(new Paragraph(new Chunk((null != perfil.getCorreoElectronico() ? perfil.getCorreoElectronico() : ""), fDatoGris)));
        else
        	tabla.addCell(new Paragraph(new Chunk("", fDatoGris)));
        if (perfil.getPerfilLaboral().getContactoTelefono() == CONTACTO_TELEFONO.NO.getIdContactoTelefono()) {
        	tabla.addCell(new Paragraph(new Chunk("Teléfono: ", fCampoGris)));
        	tabla.addCell(new Paragraph(new Chunk((null != tel.getTelefono() ? tel.getTelefono() : ""), fDatoGris)));
        	tabla.addCell(new Paragraph(new Chunk("Tipo de teléfono: ", fCampoGris)));
        	tabla.addCell(new Paragraph(new Chunk((tel.getIdTipoTelefono() > 0 ? Utils.getTipoTelefono((int)tel.getIdTipoTelefono()) : ""), fDatoGris)));
        	tabla.addCell(new Paragraph(new Chunk("Horario de contacto: ", fCampoGris)));
        	tabla.addCell(new Paragraph(new Chunk((null != perfil.getHorarioLlamar() ? perfil.getHorarioLlamar() : ""), fDatoGris)));
        }else {
        	tabla.addCell(new Paragraph(new Chunk("Teléfono: ", fCampoGris)));
            tabla.addCell(new Paragraph(new Chunk("", fDatoGris)));
            tabla.addCell(new Paragraph(new Chunk("Tipo de teléfono: ", fCampoGris)));
            tabla.addCell(new Paragraph(new Chunk("", fDatoGris)));
            tabla.addCell(new Paragraph(new Chunk("Horario de contacto: ", fCampoGris)));
            tabla.addCell(new Paragraph(new Chunk("", fDatoGris)));
        }*/
        return tabla;
    }
    
    private PdfPTable getGradoAcademico(GradoAcademicoVO vo){
        float[] width = new float[2];
        width[0]=1f;
        width[1]=2f;
        PdfPTable tabla = new PdfPTable(width);
        tabla.getDefaultCell().setBorder(0);
        tabla.setTotalWidth(300);
        tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla.setLockedWidth(true);
        tabla.addCell(new Paragraph(new Chunk("Grado de estudios: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getNivel() ? vo.getNivel() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Carrera o especialidad: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getCarrera() ? vo.getCarrera() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Escuela de procedencia: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getEscuela() ? vo.getEscuela() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Año de inicio: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((vo.getInicio() > 0 ? String.valueOf(vo.getInicio()) : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Año de terminación: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((vo.getFin() > 0 ? String.valueOf(vo.getFin()) : ""), fDatoGris)));

        return tabla;
    }
    
    private PdfPTable getConocimientos(ConocimientoHabilidadVO vo){
        float[] width = new float[2];
        width[0]=1f;
        width[1]=2f;
        PdfPTable tabla = new PdfPTable(width);
        tabla.getDefaultCell().setBorder(0);
        tabla.setTotalWidth(300);
        tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla.setLockedWidth(true);
        tabla.addCell(new Paragraph(new Chunk("Conocimientos: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getConocimientoHabilidad() ? vo.getConocimientoHabilidad() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Dominio: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getDominio() ? vo.getDominio() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Descripción: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getDescripcion() ? vo.getDescripcion() : ""), fDatoGris)));
        return tabla;
    }
    
    private PdfPTable getHabilidades(ConocimientoHabilidadVO vo) {
        float[] width = new float[2];
        width[0]=1f;
        width[1]=2f;
        PdfPTable tabla = new PdfPTable(width);
        tabla.getDefaultCell().setBorder(0);
        tabla.setTotalWidth(300);
        tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla.setLockedWidth(true);
        tabla.addCell(new Paragraph(new Chunk("Habilidad: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getConocimientoHabilidad() ? vo.getConocimientoHabilidad() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Dominio: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getDominio() ? vo.getDominio() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Descripción: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getDescripcion() ? vo.getDescripcion() : ""), fDatoGris)));
        return tabla;
    }
    
    private PdfPTable getIdiomas(IdiomaVO idioma) {
        float[] width = new float[2];
        width[0]=1f;
        width[1]=2f;
        PdfPTable tabla = new PdfPTable(width);
        tabla.getDefaultCell().setBorder(0);
        tabla.setTotalWidth(300);
        tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla.setLockedWidth(true);
        tabla.addCell(new Paragraph(new Chunk("Idioma: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((null != idioma.getIdioma() ? idioma.getIdioma() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Certificación: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((null != idioma.getCertificacion() ? idioma.getCertificacion() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Dominio: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((null != idioma.getDominio() ? idioma.getDominio() : ""), fDatoGris)));
        return tabla;
    }
    
    private PdfPTable getComputacionBasica(PerfilLaboralVo vo){
        float[] width = new float[2];
        width[0]=1f;
        width[1]=2f;
        PdfPTable tabla = new PdfPTable(width);
        tabla.getDefaultCell().setBorder(0);
        tabla.setTotalWidth(300);
        tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla.setLockedWidth(true);
        tabla.addCell(new Paragraph(new Chunk("Experiencia: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((vo.getIdExperienciaCompu() > 0 ? Utils.getExperiencia((int)vo.getIdExperienciaCompu()) : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Dominio: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((vo.getIdDominioCompu() > 0 ? Utils.getDominio((int)vo.getIdDominioCompu()) : ""), fDatoGris)));
        return tabla;
    }
    
    private PdfPTable getOffice(PerfilLaboralVo vo) {
        float[] width = new float[2];
        width[0]=1.4f;
        width[1]=2f;
        PdfPTable tabla = new PdfPTable(width);
        tabla.getDefaultCell().setBorder(0);
        tabla.setTotalWidth(300);
        tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla.setLockedWidth(true);
        tabla.addCell(new Paragraph(new Chunk("Experiencia Microsoft Office: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((vo.getIdExperienciaOffice() > 0 ? Utils.getExperiencia((int)vo.getIdExperienciaOffice()) : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Dominio Microsoft Office: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((vo.getIdDominioOffice() > 0 ? Utils.getDominio((int)vo.getIdDominioOffice()) : ""), fDatoGris)));
        return tabla;
    }
    
    private PdfPTable getInternet(PerfilLaboralVo vo) {
        float[] width = new float[2];
        width[0]=1.2f;
        width[1]=2f;
        PdfPTable tabla = new PdfPTable(width);
        tabla.getDefaultCell().setBorder(0);
        tabla.setTotalWidth(300);
        tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla.setLockedWidth(true);
        tabla.addCell(new Paragraph(new Chunk("Experiencia Internet: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((vo.getIdExperienciaInternet() > 0 ? Utils.getExperiencia((int)vo.getIdExperienciaInternet()) : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Dominio Internet: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((vo.getIdDominioInternet() > 0 ? Utils.getDominio((int)vo.getIdDominioInternet()) : ""), fDatoGris)));
        return tabla;
    }
    
    private PdfPTable getComputacionAvanzada(ComputacionAvanzadaVO vo) {
        float[] width = new float[2];
        width[0]=1f;
        width[1]=2f;
        PdfPTable tabla = new PdfPTable(width);
        tabla.getDefaultCell().setBorder(0);
        tabla.setTotalWidth(300);
        tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla.setLockedWidth(true);
        tabla.addCell(new Paragraph(new Chunk("Software o Hardware: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((null != vo && null != vo.getSoftwareHardware() ? vo.getSoftwareHardware() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Dominio: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((null != vo && null != vo.getDominio() ? vo.getDominio() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Descripción: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((null != vo && null != vo.getDescripcion() ? vo.getDescripcion() : ""), fDatoGris)));
        return tabla;
    }
    
    private PdfPTable getExperienciaLaboral(PerfilJB perfil, PerfilLaboralVo vo) {
        float[] width = new float[2];
        width[0]=1f;
        width[1]=2f;
        PdfPTable tabla = new PdfPTable(width);
        tabla.getDefaultCell().setBorder(0);
        tabla.setTotalWidth(300);
        tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla.setLockedWidth(true);
        tabla.addCell(new Paragraph(new Chunk("Experiencia total: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((vo.getIdExperienciaTotal() > 0 ? Utils.getExperiencia((int)vo.getIdExperienciaTotal()) : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Sector de mayor experiencia: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((null != perfil.getSector() ? perfil.getSector() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Área de mayor experiencia: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((null != perfil.getArea() ? perfil.getArea() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Puesto con mayor experiencia: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getPuestoMayorExpr() ? vo.getPuestoMayorExpr() : ""), fDatoGris)));
        return tabla;
    }
    
    private PdfPTable getDatosEmpleoActual(HistoriaLaboralVO vo) {
        float[] width = new float[2];
        width[0]=1f;
        width[1]=2f;
        PdfPTable tabla = new PdfPTable(width);
        tabla.getDefaultCell().setBorder(0);
        tabla.setTotalWidth(300);
        tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla.setLockedWidth(true);
        tabla.addCell(new Paragraph(new Chunk("Sector: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getSector() ? vo.getSector() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Puesto: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getPuesto() ? vo.getPuesto() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Área laboral: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getAreaLaboral() ? vo.getAreaLaboral() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Ocupación: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getOcupacion() ? vo.getOcupacion() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Empresa: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getEmpresa() ? vo.getEmpresa() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Fecha inicial: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getLaboresInicial() ? Utils.formatDate(vo.getLaboresInicial()) : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Fecha Final: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getLaboresFinal() ? Utils.formatDate(vo.getLaboresFinal()) : "Actualmente"), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Años laborados: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getAnios() ? vo.getAnios() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Jerarquia del puesto: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getJerarquia() ? vo.getJerarquia() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Personas a cargo: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getPersonas() ? vo.getPersonas() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Funciones desempeñadas: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getFuncion() ? vo.getFuncion() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Logros en la empresa: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getLogro() ? vo.getLogro() : ""), fDatoGris)));
        return tabla;
    }
    
    private PdfPTable getBuscaEmpleo(PerfilJB perfil, PerfilLaboralVo vo) {
        float[] width = new float[2];
        width[0]=1f;
        width[1]=2f;
        PdfPTable tabla = new PdfPTable(width);
        tabla.getDefaultCell().setBorder(0);
        tabla.setTotalWidth(300);
        tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla.setLockedWidth(true);
        tabla.addCell(new Paragraph(new Chunk("Razones por la que busca trabajo:", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((vo.getIdRazonBusqueda() > 0 ? Utils.getLblRazonBusqueda((int)vo.getIdRazonBusqueda()) : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Inicio de la búsqueda de empleo: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getInicioBusqueda() ? Utils.formatDate(vo.getInicioBusqueda()) : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Medios utilizados para buscar empleo: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((null != perfil.getMediosBusqueda() ? perfil.getMediosBusqueda() : ""), fDatoGris)));
        return tabla;
    }
    
    private PdfPTable getExpectativaLaboral(ExpectativaLaboralVO vo) {
        float[] width = new float[2];
        width[0]=1f;
        width[1]=2f;
        PdfPTable tabla = new PdfPTable(width);
        tabla.getDefaultCell().setBorder(0);
        tabla.setTotalWidth(300);
        tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla.setLockedWidth(true);
        tabla.addCell(new Paragraph(new Chunk("Sector deseado: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getSectorDeseado() ? vo.getSectorDeseado() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Puesto pretendido: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getPuestoDeseado() ? vo.getPuestoDeseado() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Área laboral deseada: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getAreaLaboralDeseada() ? vo.getAreaLaboralDeseada() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Ocupación deseada: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((null != vo.getOcupacionDeseada() ? vo.getOcupacionDeseada() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Salario pretendido: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((vo.getSalarioPretendido() > 0 ? mx.gob.stps.portal.movil.web.infra.utils.Utils.formatMoney(vo.getSalarioPretendido()) : ""), fDatoGris)));
        return tabla;
    }
    
    private PdfPTable getUbicacionDisponibilidad(PerfilJB perfil, PerfilLaboralVo vo) {
        float[] width = new float[2];
        width[0]=1f;
        width[1]=2f;
        PdfPTable tabla = new PdfPTable(width);
        tabla.getDefaultCell().setBorder(0);
        tabla.setTotalWidth(300);
        tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla.setLockedWidth(true);
        tabla.addCell(new Paragraph(new Chunk("Horario contacto: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((null != perfil.getHorarioLlamar() ? perfil.getHorarioLlamar() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Tipo de contrato: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk(((null != perfil.getExpectativaPrincipal() && null != perfil.getExpectativaPrincipal().getTipoContrato()) ? perfil.getExpectativaPrincipal().getTipoContrato() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Entidad: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((null != perfil.getEntidad() ? perfil.getEntidad() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Municipio: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((null != perfil.getMunicipio() ? perfil.getMunicipio() : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Disponibilidad de viajar: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((vo.getDisponibilidadViajar() > 0 ? Utils.getLblDisponibilidad(vo.getDisponibilidadViajar()) : ""), fDatoGris)));
        tabla.addCell(new Paragraph(new Chunk("Disponibilidad para radicar fuera: ", fCampoGris)));
        tabla.addCell(new Paragraph(new Chunk((vo.getDisponibilidadRadicar() > 0 ? Utils.getLblDisponibilidad(vo.getDisponibilidadRadicar()) : ""), fDatoGris)));
        return tabla;
    }
    
    private PdfPTable getTitulo(String titulo){
        PdfPTable tabla = new PdfPTable(1);
        tabla.getDefaultCell().setBorder(0);
        tabla.setTotalWidth(400);
        tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla.setLockedWidth(true);
        tabla.addCell(new Paragraph(new Chunk("     " + titulo, fTitulo)));
        return tabla;
    }
    
    private Image getImagen(String nombreImagen, int ancho, int alto) throws Exception{
        Image image = Image.getInstance(nombreImagen);
        image.scaleAbsolute(ancho,alto);
        image.setAlignment(Image.LEFT | Image.UNDERLYING);
        return image;
    }

    private PdfPTable getLineaGris(){
        PdfPTable tabla = new PdfPTable(1);
        tabla.getDefaultCell().setBorder(0);
        tabla.setExtendLastRow(true);
        tabla.setTotalWidth(345);
        tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla.setLockedWidth(true);
        try{
            tabla.addCell(getImagen(directorio+"images" + File.separator + "cv2_bg.jpg", 345, 4));
        }catch(Exception e){
            if (bDebug) System.out.println(e.getMessage());
        }
        return tabla;
    }

    private PdfPTable getLogo(){
        PdfPTable tabla = new PdfPTable(1);
        tabla.getDefaultCell().setBorder(0);
        tabla.setTotalWidth(15);
        tabla.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.setLockedWidth(true);
        try{
            tabla.addCell(getImagen(directorio+"images" + File.separator + "cv2_logo.jpg", 15, 1200));
        }catch(Exception e){
            if (bDebug) System.out.println(e.getMessage());
        }
        return tabla;
    }

    private PdfPTable getVineta(String campo, String valor){
        float[] width = new float[3];
        width[0]=.07f;
        width[1]=.6f;
        width[2]=.7f;
        PdfPTable tabla = new PdfPTable(width);
        tabla.getDefaultCell().setBorder(0);
        tabla.setTotalWidth(250);
        tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla.setLockedWidth(true);
        try{
            tabla.addCell(getImagen(directorio+"images" + File.separator + "cv2_bullet.jpg", 7, 7));
            tabla.addCell(new Paragraph(new Chunk(campo, fCampoGris)));
            tabla.addCell(new Paragraph(new Chunk(valor, fDatoGris)));
        }catch(Exception e){
            if (bDebug) System.out.println(e.getMessage());
        }
        return tabla;
    }

    private PdfPTable getFoto(long idCandidato, String context) throws MalformedURLException {
        PdfPTable tabla = new PdfPTable(1);
        tabla.getDefaultCell().setBorder(0);
        tabla.setTotalWidth(70);
        tabla.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.setLockedWidth(true);
        URL url = new URL(context + "imageAction.m?method=fotoCandidato&idCandidato="+ idCandidato);
        try {
        	tabla.addCell(getImage(url, 400, 30));
        }catch(Exception e){
            if (bDebug) System.out.println(e.getMessage());
        }
        return tabla;
    }
    
    private Image getImage(URL url, int ancho, int alto) throws BadElementException, MalformedURLException, IOException {
    	Image image = Image.getInstance(url);
    	image.scaleAbsolute(ancho,alto);
        image.setAlignment(Image.LEFT | Image.UNDERLYING);
        return image;
    }

    private PdfPTable getFooter(){
        PdfPTable tabla = new PdfPTable(1);
        tabla.getDefaultCell().setBorder(0);
        tabla.setTotalWidth(415);
        tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla.setLockedWidth(true);
        try{
            tabla.addCell(getImagen(directorio+"images" + File.separator + "cv2_footer.jpg", 415, 30));
        }catch(Exception e){
            if (bDebug) System.out.println(e.getMessage());
        }
        return tabla;
    }
    
    private int pointer(Document doc, int advance, int block) {
    	if (isAvailable(block))
    		pointer -= advance;
    	else {
    		pointer = supLimit - 10;
    		try {
				doc.newPage();
				doc.add(Chunk.NEWLINE);
				//Fondo
	            doc.add(getImagen(directorio+"images" + File.separator + "cv2_border_bottom1.jpg", 410 , supLimit));
			} catch (DocumentException de) { de.printStackTrace();
    		}catch (Exception e) { e.printStackTrace();}
    	}
    	return pointer;
    }
    
    private boolean isAvailable(int block) {
    	if (block == 0) return true;
    	int tmp = pointer;
    	tmp -= block;
    	if (tmp < 0)
    		return false;
    	else
    		return true;
    }
    
    private int leap(ConocimientoHabilidadVO vo) {
    	if (null == vo || null == vo.getDescripcion()) return 35;
    	int length = vo.getDescripcion().length();
    	if (length < 50) return 35;
    	else if (length < 100) return 45;
    	else if (length < 200) return 60;
    	else if (length < 300) return 75;
    	else if (length < 400) return 90;
    	else return 110;
    }
    
    private int leap(ComputacionAvanzadaVO vo) {
    	if (null == vo || null == vo.getDescripcion()) return 35;
    	int length = vo.getDescripcion().length();
    	if (length < 50) return 35;
    	else if (length < 100) return 45;
    	else if (length < 200) return 60;
    	else if (length < 300) return 75;
    	else if (length < 400) return 90;
    	else return 110;
    }
    
    private int leap(HistoriaLaboralVO vo) {
    	int q = 0;
    	if (null == vo || null == vo.getLogro()) q = 160;
    	int length = vo.getLogro().length();
    	if (length < 50) q = 180;
    	else if (length < 100) q = 220;
    	else if (length < 200) q = 240;
    	else if (length < 300) q = 260;
    	else if (length < 400) q = 280;
    	else q = 210;
    	if (null != vo && null != vo.getFuncion()) {
    		length = vo.getFuncion().length();
    		if (length < 50) q += 35;
    		else if (length < 100) q += 45;
    		else if (length < 200) q += 60;
    		else if (length < 300) q += 75;
    		else if (length < 400) q += 90;
    		else q += 110;
    	}
    	return q;
    }
}