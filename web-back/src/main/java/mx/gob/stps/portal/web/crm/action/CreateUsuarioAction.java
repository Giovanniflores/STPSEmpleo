package mx.gob.stps.portal.web.crm.action;

import mx.gob.stps.portal.web.crm.ValidatorCrm;
import mx.gob.stps.portal.web.crm.delegate.CrmBusDelegate;
import mx.gob.stps.portal.web.crm.delegate.CrmBusDelegateImpl;
import mx.gob.stps.portal.web.crm.form.UserForm;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by benjamin.vander on 04/12/2015.
 */
public class CreateUsuarioAction extends Action  {

    Long id;
    private CrmBusDelegate servicio = CrmBusDelegateImpl.getInstance();

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if(ValidatorCrm.validarSession(request)) {
            request.setCharacterEncoding("UTF-8");
            UserForm userForm = (UserForm) form;

            if (userForm.getId() != null && userForm.getId() != 0 && userForm.getUsuario() != null && userForm.getClave() != null) {
                servicio.updateUsuario(userForm);
            } else {
                if (userForm.getId() != null && userForm.getId() != 0) {
                    id = Long.parseLong(request.getParameter("id"));
                    servicio.selectUsuario(id, userForm);
                } else {
                    if (userForm.getUsuario() != null && userForm.getClave() != null) {
                        Long id = servicio.insertUsuario(userForm);
                        userForm.setId(id);
                    }
                }
            }

            return mapping.findForward("success");
        }
        return mapping.findForward("login");
    }


    public boolean validar() {
        return false;
    }
}
