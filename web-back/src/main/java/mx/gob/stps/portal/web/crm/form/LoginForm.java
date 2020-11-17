package mx.gob.stps.portal.web.crm.form;

/**
 * Created by benjamin.vander on 30/10/2015.
 */

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

public class LoginForm extends BaseMenuForm {

    private static final long serialVersionUID = -3491637470205228033L;

    private String usuario = null;
    private String clave = null;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }



    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        try {
            usuario=null;
            clave=null;
            request.setCharacterEncoding("ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }

    @Override
    public ActionErrors validate(ActionMapping mapping, ServletRequest request) {
        try {
            ActionErrors actionErrors = new ActionErrors();
            request.setCharacterEncoding("ISO-8859-1");
            if(usuario.isEmpty() || usuario.length()<3){
                actionErrors.add("usuario",new ActionMessage("Se debe capturar el nombre de usuario de min. 3 letras."));
            }
            if(clave.isEmpty()){
                actionErrors.add("clave", new ActionMessage("Captura su clave"));
            }
            return actionErrors;
            //return this.validate(mapping, (HttpServletRequest)request);
        } catch (ClassCastException var4) {
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
