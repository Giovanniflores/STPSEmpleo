package mx.gob.stps.portal.web.crm.form;

/**
 * Created by benjamin.vander on 30/10/2015.
 */
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

public class EtiquetaSelectedForm extends BaseMenuForm {

    private static final long serialVersionUID = -3491637470205228033L;

    private String etiquetaSelected = null;
    private Long id = null;
    private boolean selected;




    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


    public String getEtiquetaSelected() {
        return etiquetaSelected;
    }

    public void setEtiquetaSelected(String etiquetaSelected) {
        this.etiquetaSelected = etiquetaSelected;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        try {
            request.setCharacterEncoding("ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.setEtiquetaSelected(null);

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
}
