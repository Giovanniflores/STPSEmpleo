package mx.gob.stps.portal.web.crm.action;


import mx.gob.stps.portal.core.crm.constantes.Queries;
import mx.gob.stps.portal.web.crm.delegate.CrmBusDelegate;
import mx.gob.stps.portal.web.crm.delegate.CrmBusDelegateImpl;
import mx.gob.stps.portal.web.crm.form.LoginForm;
import org.apache.struts.action.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginAction extends Action {

    private CrmBusDelegate servicio = CrmBusDelegateImpl.getInstance();

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //System.setProperty("java.io.tmpdir", "/home/tulugarf/");


        LoginForm loginForm = (LoginForm)form;



        if(loginForm.getUsuario() == null || loginForm.getClave() == null){
            return mapping.findForward("login");
        }

       // ActionErrors actionErrors = loginForm.validate(mapping,request);

        request.setCharacterEncoding("UTF-8");
        if(servicio.existUsuario(loginForm)){
            loginForm.setClave("wwwuuuoosdfjiqw,kdjflasdf");
            request.getSession().setAttribute(Queries.SESSIONCONSTANT, loginForm);
            return mapping.findForward("success");
        }
        else
        {
            request.getSession().setAttribute(Queries.SESSIONCONSTANT, null);
            return mapping.findForward("login");
        }



    }
}

