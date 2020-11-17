/*
 ===========================================================================
 Copyright (c) 2013 3PillarGlobal

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sub-license, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 THE SOFTWARE.
 ===========================================================================

 */

package mx.gob.stps.portal.web.social.action;

import mx.gob.stps.portal.web.social.form.SocialForm;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.brickred.socialauth.SocialAuthConfig;
import org.brickred.socialauth.SocialAuthManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 
 * It redirects the browser to an appropriate URL which will be used for
 * authentication with the provider that has been set by clicking the icon. It
 * creates an instance of the requested provider from AuthProviderFactory and
 * calls the getLoginRedirectURL() method to find the URL which the user should
 * be redirect to.
 * 
 * @author tarun.nagpal
 * 
 */

public class SocialAuthenticationAction extends Action {

	final Log LOG = LogFactory.getLog(SocialAuthenticationAction.class);

	private Map<String, Object> userSession;

	private HttpServletRequest request;
	private HttpServletResponse response;

	/**
	 * creates a instance of the requested provider from AuthProviderFactory and
	 * calls the getLoginRedirectURL() method to find the URL which the user
	 * should be redirect to.
	 * 
	 * @return String where the action should flow
	 * @throws Exception
	 *             if an error occurs
	 */

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
								 HttpServletRequest request, HttpServletResponse response) throws Exception {
		SocialForm social = (SocialForm) form;
		LOG.info("Given provider id :: " + social.getId());
		//Create an instance of SocialAuthConfgi object
		SocialAuthConfig config = SocialAuthConfig.getDefault();

		//load configuration. By default load the configuration from oauth_consumer.properties.
		//You can also pass input stream, properties object or properties file name.
		config.load();

		//Create an instance of SocialAuthManager and set config
		SocialAuthManager manager = new SocialAuthManager();
		manager.setSocialAuthConfig(config);

		//URL of YOUR application which will be called after authentication
        String contextPath = request.getContextPath();
        request.getServerName();
        request.getServerPort();
        System.out.println("Context Path " + contextPath);
        request.getSession().getServletContext().getRealPath("/");
		String successUrl = request.getScheme() +"://" + request.getServerName() + ":"
                +request.getServerPort() +"/"+ request.getContextPath() +"/social/AuthSuccessAction.do";

		// get Provider URL to which you should redirect for authentication.
		// id can have values "facebook", "twitter", "yahoo" etc. or the OpenID URL
		String url = manager.getAuthenticationUrl(social.getId(), successUrl);

		// Store in session
		HttpSession session = request.getSession();
		session.setAttribute("authManager", manager);

		if (url != null) {
            ActionForward fwd = new ActionForward();
            fwd.setPath(url);
            fwd.setRedirect(true);

			return fwd;
		}
		return mapping.findForward("error");
	}



}
