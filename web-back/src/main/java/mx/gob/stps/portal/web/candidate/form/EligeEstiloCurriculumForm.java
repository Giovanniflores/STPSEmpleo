package mx.gob.stps.portal.web.candidate.form;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;



public class EligeEstiloCurriculumForm extends ActionForm {

	private int estiloCV;
	
	public void reset() {
		this.estiloCV = 0;
	}

	/**
	 * @return the estiloCV
	 */
	public int getEstiloCV() {
		return estiloCV;
	}

	/**
	 * @param estiloCV the estiloCV to set
	 */
	public void setEstiloCV(int estiloCV) {
		this.estiloCV = estiloCV;
	}	
	
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		
		ActionErrors errors = new ActionErrors();
		String strMethod = request.getParameter("method");
		
		if (strMethod.equalsIgnoreCase("selectCvStyle") && estiloCV<=0){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "Debe seleccionar una de las opciones de estilo de curriculum."));
		}
		
		if (errors.isEmpty()){
			errors = null;
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
	
	
}
