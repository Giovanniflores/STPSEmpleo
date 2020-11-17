package mx.gob.stps.portal.web.social.form;

/**
 * Created by benjamin.vander on 12/01/2016.
 */

import com.github.scribejava.core.oauth.OAuthService;
import org.apache.struts.action.ActionForm;
import org.brickred.socialauth.SocialAuthManager;
public class AuthForm extends ActionForm {
    String id;
    SocialAuthManager socialAuthManager;
    OAuthService service;
    private String code;
    public String getId() {
        return id;
    }
    public void setId(final String id) {
        this.id = id;
    }
    public SocialAuthManager getSocialAuthManager() {
        return socialAuthManager;
    }

    public void setSocialAuthManager(final SocialAuthManager socialAuthManager) {
        this.socialAuthManager = socialAuthManager;
    }

    public OAuthService getService() {
        return service;
    }

    public void setService(OAuthService service) {
        this.service = service;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}