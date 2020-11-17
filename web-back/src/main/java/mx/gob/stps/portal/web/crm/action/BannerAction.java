package mx.gob.stps.portal.web.crm.action;

import mx.gob.stps.portal.persistencia.vo.CmrBannerVO;
import mx.gob.stps.portal.web.crm.ValidatorCrm;
import mx.gob.stps.portal.web.crm.delegate.CrmBusDelegate;
import mx.gob.stps.portal.web.crm.delegate.CrmBusDelegateImpl;
import mx.gob.stps.portal.web.crm.form.BannerForm;
import mx.gob.stps.portal.web.crm.form.CrearBannerForm;
import mx.gob.stps.portal.web.crm.helper.FormHelper;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * Created by benjamin.vander on 14/12/2015.
 */
public class BannerAction extends Action {

    private CrmBusDelegate servicio = CrmBusDelegateImpl.getInstance();
    private CrearBannerForm bannerForm;
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (!ValidatorCrm.validarSession(request)) {
            return mapping.findForward("login");
        }
        bannerForm = (CrearBannerForm) form;
        if(request.getParameter("btn-borrar")!= null){
            servicio.deleteBanner(bannerForm.getBannerForm().getId());
            BannerForm banner = bannerForm.getBannerForm();
            bannerForm.setId(null);
            banner.setDescripcion("");
            banner.setActivo(false);
            banner.setDescripcionLarga("");
            banner.setIndice(0L);
            banner.setTitulo("");
            banner.setFechaFin("");
            banner.setFechaInicio("");
            banner.setLink("");
            banner.setNuevaVentana(false);
            bannerForm.getBannerForm().setId(null);
            return mapping.findForward("success");
        }        
        
        
        //Si hay archivos guardar o actualizar
        if(bannerForm.getBannerForm().getFile() != null && bannerForm.getId() == null){
        	
            if(valido()){
                //Crear el Banner
                if(!(bannerForm.getBannerForm().getId() != null) && bannerForm.getBannerForm().getId() > 0){

                    servicio.crearBanner(bannerForm.getBannerForm());
                    bannerForm.setId(bannerForm.getBannerForm().getId());
                }
                //Actualizar el Banner
                else {
                    //if(bannerForm.getBannerForm().getId() != null && !bannerForm.getBannerForm().getId().equals( 0L)) {
                       servicio.actualizarBanner(bannerForm.getBannerForm());
                        bannerForm.setId(bannerForm.getBannerForm().getId());
                    //}

                }
            }
        }
        else
        {
            //buscar el banner
            if(bannerForm.getId() != null && bannerForm.getId() != 0L){
                bannerForm.getBannerForm().setId(bannerForm.getId());
                servicio.getBanner(bannerForm.getBannerForm());
                bannerForm.setId(bannerForm.getBannerForm().getId());
            }
        }
        bannerForm.setBannersForm(servicio.getBanners());
        return mapping.findForward("success");
    }



    public boolean valido() throws ServiceLocatorException, SQLException {
        bannerForm.setErrorMessage("");
        int length = FormHelper.getLength(bannerForm.getBannerForm().getTitulo());
        if(length<3 || length > 100){
            FormHelper.addMessage(bannerForm,"El titulo debe ser entre 3 y 100 caracteres.");
        }
        length = FormHelper.getLength(bannerForm.getBannerForm().getDescripcion());
        if(length >255){
            FormHelper.addMessage(bannerForm, "La descripcion solo puede ser de 255 caracteres.");
        }

        if(bannerForm.getBannerForm().getIndice() == null ){
            FormHelper.addMessage(bannerForm, "El indice debe ser dado.");
        }
        if(bannerForm.getBannerForm().getFile() == null) {
            if(bannerForm.getBannerForm().getId() == null){
                FormHelper.addMessage(bannerForm, "Se debe selecionar un archivo para el banner.");

            }
            else {
                CmrBannerVO voFile = servicio.getBannerImage(bannerForm.getId());
                if (voFile == null) {
                    FormHelper.addMessage(bannerForm, "Se debe selecionar un archivo para el banner.");
                }
            }

        }

        return !bannerForm.getHasError();
    }


}
