package mx.gob.stps.portal.web.crm.pojo;

/**
 * Created by benjamin.vander on 12/12/2015.
 */
public class EtiquetaVO {

    private Long idEtiqueta;
    private String etiqueta;
    private String editar;
    private String borrar;

    public String getBorrar() {
        return "borrarEtiqueta.do?id=" +this.idEtiqueta;
    }

    public void setBorrar(String borrar) {
        this.borrar = borrar;
    }

    public String getEditar() {
        return "etiquetas.do?id="+this.idEtiqueta;
    }

    public void setEditar(String editar) {
        this.editar = editar;
    }

    public Long getIdEtiqueta() {
        return idEtiqueta;
    }

    public void setIdEtiqueta(Long idEtiqueta) {
        this.idEtiqueta = idEtiqueta;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }
}
