package mx.gob.stps.portal.web.crm.form;

/**
 * Created by benjamin.vander on 30/10/2015.
 */

import mx.gob.stps.portal.web.crm.pojo.EtiquetaVO;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class EtiquetaForm extends BaseMenuForm {

    private static final long serialVersionUID = -3491637470205228033L;

    private String etiqueta = null;
    private Long id = null;
    private List<EtiquetaVO> etiquetas = new ArrayList<EtiquetaVO>();
    private String editar;
    private String borrar;

    public EtiquetaForm(Long idEtiqueta) {
        this.id = idEtiqueta;
    }

    public EtiquetaForm(){
        
    }


    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        try {
            request.setCharacterEncoding("ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.setEtiqueta(null);

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

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<EtiquetaVO> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(List<EtiquetaVO> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public String getEditar() {
        return "etiquetas.do?id="+this.id;
    }

    public void setEditar(String editar) {
        this.editar = editar;
    }

    public String getBorrar() {
        return "borrarEtiqueta.do?id=" +this.id;
    }

    public void setBorrar(String borrar) {
        this.borrar = borrar;
    }




}
