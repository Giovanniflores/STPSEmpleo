package mx.gob.stps.portal.web.social.form;

import org.apache.struts.action.ActionForm;

/**
 * Created by benjamin.vander on 12/01/2016.
 */


public class SocialForm extends ActionForm {

    private String id;
    private String mode;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(final String mode) {
        this.mode = mode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }
}
