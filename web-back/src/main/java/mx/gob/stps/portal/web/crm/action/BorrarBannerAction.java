package mx.gob.stps.portal.web.crm.action;


import mx.gob.stps.portal.web.crm.ValidatorCrm;
import mx.gob.stps.portal.web.crm.delegate.CrmBusDelegate;
import mx.gob.stps.portal.web.crm.delegate.CrmBusDelegateImpl;
import mx.gob.stps.portal.web.crm.form.BannerForm;
import mx.gob.stps.portal.web.crm.form.CreateArticuloForm;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by benjamin.vander on 11/11/2015.
 */
public class BorrarBannerAction extends Action {

    private CrmBusDelegate servicio = CrmBusDelegateImpl.getInstance();
    private Integer id;


    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception {

        if(!ValidatorCrm.validarSession(request)) {
            return mapping.findForward("login");
        }
        BannerForm borrarForm = (BannerForm)form;
        if(borrarForm.getId() == 0){
            borrarForm.setErrorMessage("No se encuentra el Banner para borrar.");
        }
        else
        {
            CreateArticuloForm createArticuloForm = new CreateArticuloForm();
           if( servicio.selectArticulo(borrarForm.getId(),createArticuloForm) == null){
               borrarForm.setErrorMessage("No se encuentra el articulo para borrar");
           }
            else
           {
               servicio.deleteBanner(borrarForm.getId());
               borrarForm.setErrorMessage("Se borro el articulo con el titulo " + createArticuloForm.getTitulo());
           }

        }

        return mapping.findForward("success");
    }
}
