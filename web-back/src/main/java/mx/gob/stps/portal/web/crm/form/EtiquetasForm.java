package mx.gob.stps.portal.web.crm.form;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by benjamin.vander on 08/11/2015.
 */
public class EtiquetasForm extends BaseMenuForm {
    private static List<EtiquetaForm> etiquetas = new ArrayList<EtiquetaForm>();

    public EtiquetasForm(List<EtiquetaForm> listaEtiquetas) {
        this.etiquetas = listaEtiquetas;
    }

    public EtiquetasForm(){

    }


    public static List<EtiquetaForm> getEtiquetas() {
        return etiquetas;
    }

    public static void setEtiquetas(List<EtiquetaForm> etiquetas) {
        EtiquetasForm.etiquetas = etiquetas;
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
}
