package mx.gob.stps.portal.web.crm.action;


import mx.gob.stps.portal.web.crm.ConstantenArticulos;
import mx.gob.stps.portal.web.crm.delegate.CrmBusDelegate;
import mx.gob.stps.portal.web.crm.delegate.CrmBusDelegateImpl;
import mx.gob.stps.portal.web.crm.form.ArticulosForm;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by benjamin.vander on 06/11/2015.
 */
public class IndexArticuloAction extends Action {

    private CrmBusDelegate servicio = CrmBusDelegateImpl.getInstance();
    private Integer id;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ArticulosForm articulosForm = (ArticulosForm) form;


        articulosForm.setArticulosFormList(servicio.getRandomArticulos(ConstantenArticulos.HOMELIMIT));
        return mapping.findForward("success");


    }

  }
