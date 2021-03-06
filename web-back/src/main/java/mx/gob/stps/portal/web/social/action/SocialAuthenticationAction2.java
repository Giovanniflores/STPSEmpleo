package mx.gob.stps.portal.web.social.action;

/**
 * Created by benjamin.vander on 12/01/2016.
 */

import com.github.scribejava.apis.FacebookApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuthService;
import mx.gob.stps.portal.web.social.form.AuthForm;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.RequestUtils;
import org.brickred.socialauth.SocialAuthConfig;
import org.brickred.socialauth.SocialAuthManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;




public class SocialAuthenticationAction2 extends Action {

    final Log LOG = LogFactory.getLog(SocialAuthenticationAction.class);

    @Override
    public ActionForward execute(final ActionMapping mapping,
                                 final ActionForm form, final HttpServletRequest request,
                                 final HttpServletResponse response) throws Exception {

        AuthForm authForm = (AuthForm) form;

        String id = authForm.getId();
        SocialAuthManager manager;
        String returnToUrl = RequestUtils.absoluteURL(request,
                "/socialAuthSuccessAction.do")
                .toString();
        InputStream in = SocialAuthenticationAction.class.getClassLoader()
                .getResourceAsStream("oauth_consumer.properties");
        SocialAuthConfig conf = SocialAuthConfig.getDefault();
        conf.load(in);
        if (id.equals("facebook")) {
            //OAUTH sistem
            OAuthService service = new ServiceBuilder()
                    .provider(FacebookApi.class)
                    .apiKey(conf.getApplicationProperties().getProperty("graph.facebook.com.consumer_key"))
                    .apiSecret(conf.getApplicationProperties().getProperty("graph.facebook.com.consumer_secret"))
                    .scope("public_profile,email,user_birthday,user_hometown,user_location")
                    .callback(returnToUrl)
                    .build();
            // Token requestToken = service.getRequestToken();
            String authUrl = service.getAuthorizationUrl(null);
            authForm.setService(service);
            LOG.info("Redirecting to: " + authUrl);
            if (authUrl != null) {
                ActionForward fwd = new ActionForward("openAuthUrl", authUrl, true);
                return fwd;
            }
        } else {
            if (authForm.getSocialAuthManager() != null) {
                manager = authForm.getSocialAuthManager();
            } else {

                manager = new SocialAuthManager();
                manager.setSocialAuthConfig(conf);
                authForm.setSocialAuthManager(manager);
            }

            String url = manager.getAuthenticationUrl(id, returnToUrl);

            LOG.info("Redirecting to: " + url);
            if (url != null) {
                ActionForward fwd = new ActionForward("openAuthUrl", url, true);
                return fwd;
            }
        }
        return mapping.findForward("failure");
    }
}