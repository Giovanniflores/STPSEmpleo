package mx.gob.stps.portal.web.crm.form;


import mx.gob.stps.portal.web.crm.pojo.Estatus;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by benjamin.vander on 06/11/2015.
 */
public class CreateArticuloForm extends BaseMenuForm {

    private static final long serialVersionUID = -3491637470205228033L;
    private Long id;
    private String idStr;
    private String titulo;
    private String descripcion;
    private String fuente;
    private String fecha;
    private Estatus estatus;
    private String articulo;
        private Boolean mostrarEnHome= false;
    private Boolean activo= false;
    private Boolean indexable= false;
    private Boolean occulto = false;
    private String[] selectedEtiquestas;
    private String getUrl;
    private String redirect;
    private String editar;
    private String borrar;

    private List<EtiquetaSelectedForm> etiquetas = new ArrayList<EtiquetaSelectedForm>();
    private List<EtiquetaForm> etiquetaForms = new ArrayList<EtiquetaForm>();

    public List<EtiquetaSelectedForm> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(List<EtiquetaSelectedForm> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public void setEtiquetasForm(List<EtiquetaForm> etiquetas){
        List<EtiquetaSelectedForm> etiquetaSelectedForms = new ArrayList<EtiquetaSelectedForm>() ;
        for(EtiquetaForm etiqueta: etiquetas){
            EtiquetaSelectedForm etiquetaSelectedForm = new EtiquetaSelectedForm();
            etiquetaSelectedForm.setEtiquetaSelected(etiqueta.getEtiqueta());
            etiquetaSelectedForm.setId(etiqueta.getId());
            etiquetaSelectedForms.add(etiquetaSelectedForm);
        }

        this.etiquetas = etiquetaSelectedForms;
        this.setEtiquetaForms(etiquetas);
    }



    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Boolean getMostrarEnHome() {
        return mostrarEnHome;
    }

    public void setMostrarEnHome(Boolean mostrarEnHome) {
        this.mostrarEnHome = mostrarEnHome;
    }

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String[] getSelectedEtiquestas() {
        return selectedEtiquestas;
    }

    public void setSelectedEtiquestas(String[] selectedEtiquestas) {
        this.selectedEtiquestas = selectedEtiquestas;
    }


    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }


    public Boolean getIndexable() {
        return indexable;
    }

    public void setIndexable(Boolean indexable) {
        this.indexable = indexable;
    }


    public Boolean getOcculto() {
        return occulto;
    }

    public void setOcculto(Boolean occulto) {
        this.occulto = occulto;
    }


    public String getIdStr() {
        return String.valueOf(id);
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    public String getBorrar(){
        return "borrarArticulos.do?id="+this.id;
    }

    public String getEditar(){
        return "crearArticulo.do?id="+this.id;
    }


    public void setEditar(String editar) {
        this.editar = editar;
    }

    public void setBorrar(String borrar) {
        this.borrar = borrar;
    }


    public String getRedirect() {
        return "articulo.do?id="+this.id;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }


    public List<EtiquetaForm> getEtiquetaForms() {
        return etiquetaForms;
    }

    public void setEtiquetaForms(List<EtiquetaForm> etiquetaForms) {
        this.etiquetaForms = etiquetaForms;
    }

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        try {
            request.setCharacterEncoding("ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.setEtiquetas(new ArrayList<EtiquetaSelectedForm>());


    }

    @Override
    public ActionErrors validate(ActionMapping mapping, ServletRequest request) {
        try {
            request.setCharacterEncoding("ISO-8859-1");
            ActionErrors actionErrors = this.validate(mapping, (HttpServletRequest)request);
            if(articulo.isEmpty()){
                actionErrors.add("articulo",new ActionMessage("Se debe capturar el contenido del articulo."));
            }
            if(titulo.isEmpty()){
                actionErrors.add("titulo",new ActionMessage("Se debe capturar el titulo del articulo."));
            }
            if(selectedEtiquestas.length==0){
                actionErrors.add("selectedEtiquetas",new ActionMessage("Se debe capturar los etiquetas."));

            }
            if(descripcion.isEmpty()){
                actionErrors.add("descripcion",new ActionMessage("Se debe capturar la descripcion."));
            }
            return actionErrors;
        } catch (ClassCastException var4) {
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
