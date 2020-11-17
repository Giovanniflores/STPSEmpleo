package mx.gob.stps.portal.web.crm.action;


import mx.gob.stps.portal.web.crm.ValidatorCrm;
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
 * Created by benjamin.vander on 06/11/2015.
 */
public class ShowArticuloAction extends PagerAction {


    private CrmBusDelegate servicio = CrmBusDelegateImpl.getInstance();
    private Long id;

    @Override
    public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    private enum ActionForwardMapping {
        // The forward names must match in struts-config.xml
        SUCCESS("success"),LOGIN("login");


        private final String forwardName;

        private ActionForwardMapping(String forwardName) {
            this.forwardName = forwardName;
        }
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if(!ValidatorCrm.validarSession(request)) {
            return mapping.findForward("login");
        }
        CreateArticuloForm createArticuloForm = (CreateArticuloForm) form;


        if (createArticuloForm.getId() != 0) {
            id = Long.parseLong(request.getParameter("id"));
            servicio.selectArticulo(id, createArticuloForm);

            createArticuloForm.setSelectedEtiquestas(servicio.getEtiquetasArticulosEnString(id));
            createArticuloForm.setEtiquetasForm(servicio.getEtiquetasAlfabetico());

        } else {
            return mapping.findForward("error");
        }


        return mapping.findForward("success");


    }

    private void insertarEtiquetas(CreateArticuloForm createArticuloForm, Long idArticulo) throws Exception {
        for (String etiqueta : createArticuloForm.getSelectedEtiquestas()) {
            servicio.createEtiquetaArticulo(idArticulo, Long.parseLong(etiqueta));

        }
    }



}
