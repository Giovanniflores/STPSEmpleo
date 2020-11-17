package mx.gob.stps.portal.web.crm.action;

import mx.gob.stps.portal.web.crm.delegate.CrmBusDelegate;
import mx.gob.stps.portal.web.crm.delegate.CrmBusDelegateImpl;
import mx.gob.stps.portal.web.crm.form.CreateArticuloForm;
import mx.gob.stps.portal.web.infra.action.PagerAction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by benjamin.vander on 10/12/2015.
 */
public class MostrarArticuloAction extends PagerAction {

    private Long id;
    private CrmBusDelegate servicio = CrmBusDelegateImpl.getInstance();

    @Override
    public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    private enum ActionForwardMapping {
        // The forward names must match in struts-config.xml
        SUCCESS("SUCCESS"),LOGIN("login");


        private final String forwardName;

        private ActionForwardMapping(String forwardName) {
            this.forwardName = forwardName;
        }
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {


        CreateArticuloForm createArticuloForm = (CreateArticuloForm)form;
        if(request.getParameter("id") == null ){
            return mapping.findForward("home");
        }
        id = Long.parseLong(request.getParameter("id"));
        servicio.selectArticulo(id, createArticuloForm);

        //request.getSession().setAttribute(BODY_JSP, mapping.findForward(ActionForwardMapping.SUCCESS.toString()).getPath());
        return mapping.findForward(ActionForwardMapping.SUCCESS.toString());



    }
}
