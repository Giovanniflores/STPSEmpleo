package mx.gob.stps.portal.web.crm.form;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * Created by benjamin.vander on 14/12/2015.
 */
public class BannerForm  extends BaseMenuForm {

    private static final long serialVersionUID = -3491637470205228033L;

    private Long id;

    private FormFile file;

    private String titulo;

    private String fechaInicio;

    private String fechaFin;

    private boolean activo;

    private Long ancho;

    private Long altura;

    private String descripcionLarga;

    private boolean nuevaVentana;

    private String link;

    private String descripcion;

    private String tipoImagen;

    private Long indice;

    private String editar;
    private String borrar;

    private String hyperlink;

    public FormFile getFile() {
        return file;
    }

    public void setFile(FormFile file) {
        this.file = file;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }



    public Long getAncho() {
        return ancho;
    }

    public void setAncho(Long ancho) {
        this.ancho = ancho;
    }

    public Long getAltura() {
        return altura;
    }

    public void setAltura(Long altura) {
        this.altura = altura;
    }

    public String getDescripcionLarga() {
        return descripcionLarga;
    }

    public void setDescripcionLarga(String descripcionLarga) {
        this.descripcionLarga = descripcionLarga;
    }

    public boolean getNuevaVentana() {
        return nuevaVentana;
    }

    public void setNuevaVentana(boolean nuevaVentana) {
        this.nuevaVentana = nuevaVentana;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoImagen() {
        return tipoImagen;
    }

    public void setTipoImagen(String tipoImagen) {
        this.tipoImagen = tipoImagen;
    }

    public Long getIndice() {
        return indice;
    }

    public void setIndice(Long indice) {
        this.indice = indice;
    }



    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditar() {
        return "crearBanner.do?id="+this.id;
    }

    public void setEditar(String editar) {
        this.editar = editar;
    }

    public String getBorrar() {
        return "borrarBanner.do?id=" +this.id;
    }

    public void setBorrar(String borrar) {
        this.borrar = borrar;
    }



    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        try {
            request.setCharacterEncoding("ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }

    @Override
    public ActionErrors validate(ActionMapping mapping, ServletRequest request) {
        try {
            request.setCharacterEncoding("ISO-8859-1");
            return this.validate(mapping, (HttpServletRequest)request);
        } catch (ClassCastException var4) {
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return  null;
    }

    public String getHyperlink() {
        StringBuilder a = new StringBuilder();
        if(link != null && !link.isEmpty()){
            a.append("href="+link+"");
            if(getNuevaVentana()){
                a.append(" target=_new ");
            }
        }
        return  a.toString();
    }

    public void setHyperlink(String hyperlink) {
        this.hyperlink = hyperlink;
    }
}
