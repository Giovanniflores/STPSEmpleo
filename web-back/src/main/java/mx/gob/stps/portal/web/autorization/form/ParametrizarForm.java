package mx.gob.stps.portal.web.autorization.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 * Clase ActionForm que cuenta con el mapeo de sus propiedades con los controles de la pagina
 * 
 * @author oscar.manzo
 *
 */
public class ParametrizarForm extends ActionForm {
	private static final long serialVersionUID = 8212430801936056588L;
	private int tiempoReasignacion;
	private int bloqueAsignacion;
	
	private long idParametro;
	private String valor;

	
	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();

        if (tiempoReasignacion<=0) {
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "Tiempo de asignación"));
        }else if (tiempoReasignacion<30 || tiempoReasignacion>120){
        	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.invalid", "Tiempo de asignación invalido, solo se permiten valores entre 30 y 120 minutos."));
        }

		request.setAttribute(Globals.ERROR_KEY, errors);		
		
        if (errors.isEmpty()) {
            errors = null;
        }

        return errors;
	}

	public ActionErrors validateBloque(ActionMapping mapping, HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
        if (bloqueAsignacion<=0) {
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "Bloque de asignación"));
        } else if(bloqueAsignacion<5 || bloqueAsignacion>50){
        	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "Bloque de asignación"));
        }

		request.setAttribute(Globals.ERROR_KEY, errors);		
		
        if (errors.isEmpty()) {
            errors = null;
        } else{
        	request.setAttribute(Globals.ERROR_KEY, errors);
        }

        return errors;
	}
	
	public void reset() {
		this.tiempoReasignacion = 0;
		this.bloqueAsignacion = 0;
	}
	
	public int getTiempoReasignacion() {
		return tiempoReasignacion;
	}
	public void setTiempoReasignacion(int tiempoReasignacion) {
		this.tiempoReasignacion = tiempoReasignacion;
	}

	public int getBloqueAsignacion() {
		return bloqueAsignacion;
	}

	public void setBloqueAsignacion(int bloqueAsignacion) {
		this.bloqueAsignacion = bloqueAsignacion;
	}

	public long getIdParametro() {
		return idParametro;
	}

	public void setIdParametro(long idParametro) {
		this.idParametro = idParametro;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}	
}
