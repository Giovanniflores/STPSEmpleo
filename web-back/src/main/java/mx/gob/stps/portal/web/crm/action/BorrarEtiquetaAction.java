package mx.gob.stps.portal.web.crm.action;


import mx.gob.stps.portal.web.crm.ValidatorCrm;
import mx.gob.stps.portal.web.crm.delegate.CrmBusDelegate;
import mx.gob.stps.portal.web.crm.delegate.CrmBusDelegateImpl;
import mx.gob.stps.portal.web.crm.form.EtiquetaForm;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by benjamin.vander on 06/11/2015.
 */
public class BorrarEtiquetaAction extends Action {

    private CrmBusDelegate servicio = CrmBusDelegateImpl.getInstance();

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //System.setProperty("java.io.tmpdir", "/home/tulugarf/");
        if(!ValidatorCrm.validarSession(request)) {
            return mapping.findForward("login");
        }
        //request.setCharacterEncoding("UTF-8");

        EtiquetaForm etiquetaForm = (EtiquetaForm)form;
        /*
        if(loginForm.getUsuario() == null || loginForm.getClave() == null ||
                !loginForm.getUsuario().equalsIgnoreCase("rahul") || !loginForm.getClave().equals("abc")){
            return mapping.findForward("failure");
        }
        else
            return mapping.findForward("success");
        */
        /*
        etiquetaForm.setEtiquetas(servicio.getEtiquetasAlfabeticoVO());
        if(etiquetaForm.getEtiqueta()!= null && !etiquetaForm.getEtiqueta().isEmpty()){

                servicio.createEtiquetaForm(etiquetaForm);
                etiquetaForm.setEtiqueta("");
            
        }*/
        if(etiquetaForm.getId() !=  null && etiquetaForm.getId()!=0L){
            servicio.deleteEtiqueta(etiquetaForm.getId());
            etiquetaForm.setId(null);
            etiquetaForm.setEtiqueta(null);
        }
        etiquetaForm.setEtiquetas(servicio.getEtiquetasAlfabeticoVO());
        return mapping.findForward("success");



    }



}
