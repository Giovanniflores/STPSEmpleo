package mx.gob.stps.portal.web.crm.form;

import org.apache.struts.action.ActionForm;

/**
 * Created by benjamin.vander on 07/12/2015.
 */

public class BaseMenuForm extends ActionForm {

    private String articulosUrl;

    private String etiquetaUrl;

    private String errorMessage;

    private String bannerUrl;

    private boolean hasError;



    public String getArticulosUrl() {
        return "viewArticulos.do";
    }

    public void setArticulosUrl(String articulosUrl) {
        this.articulosUrl = articulosUrl;
    }


    public String getEtiquetaUrl() {
        return "etiquetas.do";
    }

    public void setEtiquetaUrl(String etiquetaUrl) {
        this.etiquetaUrl = etiquetaUrl;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    public String getBannerUrl() {
        return "crearBanner.do";
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public boolean getHasError() {
        if(errorMessage != null && !errorMessage.isEmpty()){
            return true;
        }
        return false;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }
}
