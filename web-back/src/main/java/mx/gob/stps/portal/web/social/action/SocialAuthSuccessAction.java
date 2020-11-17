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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.brickred.socialauth.AuthProvider;
import org.brickred.socialauth.Contact;
import org.brickred.socialauth.Profile;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.util.SocialAuthUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Verifies the user when the external provider redirects back to our
 * application. It gets the instance of the requested provider from session and
 * calls verifyResponse() method which verifies the user and returns profile
 * information. After verification we call the getContactList() method to get
 * the contacts.
 * 
 * @author tarun.nagpal
 * 
 */

public class SocialAuthSuccessAction extends Action {

	final Log LOG = LogFactory.getLog(this.getClass());

	private Map<String, Object> userSession;
	public HttpServletRequest request;

	/**
	 * Displays the user profile and contacts for the given provider.
	 * 
	 * @return String where the action should flow
	 * @throws Exception
	 *             if an error occurs
	 */

    @Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
                          HttpServletRequest request, HttpServletResponse response) throws Exception {

        // get the auth provider manager from session
        SocialAuthManager manager = (SocialAuthManager)request.getSession().getAttribute("authManager");

        // call connect method of manager which returns the provider object.
        // Pass request parameter map while calling connect method.
        Map<String, String> paramsMap = SocialAuthUtil.getRequestParametersMap(request);
        AuthProvider provider = manager.connect(paramsMap);

        // get profile
        Profile p = provider.getUserProfile();

        // you can obtain profile information
        System.out.println(p.getFirstName());

        // OR also obtain list of contacts
        List<Contact> contactsList = provider.getContactList();

        return mapping.findForward("success");

    }


    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
	}


}
