package mx.gob.stps.portal.web.cil.form;

import mx.gob.stps.portal.core.cil.bo.AttentionRequest;

import org.apache.struts.action.ActionForm;

public class TraceUserForm extends ActionForm {
	
	private static final long serialVersionUID = -3005537355499961709L;

	private String userName;
	private AttentionRequest attention;
	private String servicesDate;
	private String msg;
	private String userID;
	
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * @return the attention
	 */
	public AttentionRequest getAttention() {
		return attention;
	}
	
	/**
	 * @param attention the attention to set
	 */
	public void setAttention(AttentionRequest attention) {
		this.attention = attention;
	}

	/**
	 * @return the servicesDate
	 */
	public String getServicesDate() {
		return servicesDate;
	}

	/**
	 * @param servicesDate the servicesDate to set
	 */
	public void setServicesDate(String servicesDate) {
		this.servicesDate = servicesDate;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}
}