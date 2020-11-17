package mx.gob.stps.portal.web.crm.action;

import mx.gob.stps.portal.web.crm.ConstantenArticulos;
import mx.gob.stps.portal.web.crm.ValidatorCrm;
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
 * Created by benjamin.vander on 11/11/2015.
 */
public class ViewListaArticuloAction extends Action {

    private CrmBusDelegate servicio = CrmBusDelegateImpl.getInstance();
    private Integer id;


    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(!ValidatorCrm.validarSession(request)) {
            return mapping.findForward("login");
        }

        ArticulosForm articulosForm = (ArticulosForm)form;
        Integer page = articulosForm.getPage();
        if(page==null){
            page=0;
        }
        articulosForm.setArticulosFormList(servicio.getTodoArticulos().getArticulosFormList());
        articulosForm.setTotalRegistros(servicio.countArticulos());
        articulosForm.setPages((articulosForm.getTotalRegistros()/ ConstantenArticulos.ROWLIMIT)+1);
        //articulos.jsp
        return mapping.findForward("success");
    }
}
