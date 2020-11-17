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
public class EtiquetaAction extends Action {

    private CrmBusDelegate servicio = CrmBusDelegateImpl.getInstance();
    private EtiquetaForm etiquetaForm;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //System.setProperty("java.io.tmpdir", "/home/tulugarf/");
        if(!ValidatorCrm.validarSession(request)) {
            return mapping.findForward("login");
        }


        //request.setCharacterEncoding("UTF-8");

        etiquetaForm = (EtiquetaForm)form;
        etiquetaForm.setErrorMessage(null);
        /*
        if(loginForm.getUsuario() == null || loginForm.getClave() == null ||
                !loginForm.getUsuario().equalsIgnoreCase("rahul") || !loginForm.getClave().equals("abc")){
            return mapping.findForward("failure");
        }
        else
            return mapping.findForward("success");
        */

        //Existe la etiqueta
        if(etiquetaForm.getId() != null && etiquetaForm.getId()!= 0L){
            //Es para Actualizar
            if(etiquetaForm.getEtiqueta() != null && !etiquetaForm.getEtiqueta().isEmpty()){
                if(validar()) {
                    servicio.actualizarEtiqueta(etiquetaForm);
                    etiquetaForm.setEtiqueta("");
                    etiquetaForm.setId(null);
                }
            }
            else
            {   //buscar etiqueta
                servicio.getEtiqueta(etiquetaForm);
            }
        }
        else {
            if (etiquetaForm.getEtiqueta() != null && !etiquetaForm.getEtiqueta().isEmpty()) {
                //crear etiqueta
                if(validar()) {
                    servicio.createEtiquetaForm(etiquetaForm);
                    etiquetaForm.setEtiqueta("");
                    etiquetaForm.setId(null);
                }

            }
        }
        //Cargar la lista de etiquetas
        etiquetaForm.setEtiquetas(servicio.getEtiquetasAlfabeticoVO());
        return mapping.findForward("success");



    }

    public boolean validar() {
        String etiqueta = etiquetaForm.getEtiqueta();
        if(etiqueta.length() > 3 && etiqueta.length()<50){
            return true;
        }
        etiquetaForm.setErrorMessage("El etiqueta debe ser entre 3 y 50 caracteres.");
        return false;
    }


}
