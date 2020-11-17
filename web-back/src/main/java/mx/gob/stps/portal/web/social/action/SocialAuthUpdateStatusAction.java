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
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.exception.SocialAuthException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * This is for updating status. After verification we call the updateStatus()
 * method to update status on that provider.
 * 
 * @author tarun.nagpal
 *
 */

public class SocialAuthUpdateStatusAction extends Action {

	final Log LOG = LogFactory.getLog(this.getClass());
	
	private Map<String, Object> userSession ;
	private HttpServletRequest request;
	private String statusMessage;
	
	/**
	 * Update status for the given provider.
	 * 
	 * @return String where the action should flow
	 * @throws Exception
	 *             if an error occurs
	 */
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
						  HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LOG.info("Status Message :: "+statusMessage);
		if (statusMessage == null || statusMessage.trim().length() == 0) {
			request.setAttribute("Message", "Status can't be left blank.");
			return mapping.findForward("failure");
		}
		
		SocialAuthManager manager = null;
		if (userSession.get("socialAuthManager") != null) {
			manager = (SocialAuthManager)userSession.get("socialAuthManager");
		} 
		AuthProvider provider = null;
		if(manager!=null){
			provider = manager.getCurrentAuthProvider();
		}
		if (provider != null) {
			try {
				provider.updateStatus(statusMessage);
				request.setAttribute("Message", "Status Updated successfully");
				return mapping.findForward("success");
			} catch (SocialAuthException e) {
				request.setAttribute("Message", e.getMessage());
				LOG.error(e);
			}
		}
		return mapping.findForward("failure");
		
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

}
