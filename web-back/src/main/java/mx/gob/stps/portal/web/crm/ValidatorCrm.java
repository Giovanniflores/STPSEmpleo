package mx.gob.stps.portal.web.crm;

import mx.gob.stps.portal.core.crm.constantes.Queries;
import mx.gob.stps.portal.web.crm.form.LoginForm;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by benjamin.vander on 06/12/2015.
 */
public class ValidatorCrm {


    public static boolean validarSession(HttpServletRequest request) {
        LoginForm loginForm = (LoginForm)request.getSession().getAttribute(Queries.SESSIONCONSTANT);
        if(loginForm != null){
            return true;
        }

        return false;
    }
}
