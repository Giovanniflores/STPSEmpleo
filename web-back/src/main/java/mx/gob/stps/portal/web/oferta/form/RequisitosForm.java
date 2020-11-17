package mx.gob.stps.portal.web.oferta.form;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import mx.gob.stps.portal.web.infra.*;
import mx.gob.stps.portal.web.infra.utils.Utils;

public class RequisitosForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1669718255213667841L;
	
	private String conocimiento;
	private long experiencia;
	private long dominio;
	
	/*
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();

		if (getConocimiento()==null || getConocimiento().length()<1){
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "El conocimiento es un dato que"));
		} else{
			System.out.println("******-----ya paso validate getConocimiento");
		}			
		
		if (errors.isEmpty()){
			errors = null;
			System.out.println("******-----errorForm:null");
		}else{
			request.setAttribute(Globals.ERROR_KEY, errors);
			//DEBUG
			Iterator itMessages = errors.get();
			while(itMessages.hasNext()){				
				Object actionMsg = itMessages.next(); 
				System.out.println("******-----errorForm:" + actionMsg.toString());
			}
			//END DEBUG
		}
		return errors;		
	}			
	*/
	
	
	public String getConocimiento() {		
		return conocimiento;
	}
	
	public void setConocimiento(String conocimiento) {
		this.conocimiento = conocimiento;
	}
	
	public long getExperiencia() {
		return experiencia;
	}
	public void setExperiencia(long experiencia) {
		this.experiencia = experiencia;
	}
	public long getDominio() {
		return dominio;
	}
	public void setDominio(long dominio) {
		this.dominio = dominio;
	}
	
	
	
}
