package mx.gob.stps.portal.web.candidate.form;

import java.io.Serializable;

import org.apache.struts.validator.ValidatorForm;

public class MotivoForm extends ValidatorForm implements Serializable{
	private static final long serialVersionUID = 3493883002508526218L;
//	private static Logger logger = Logger.getLogger(MotivoForm.class);

	
	//inicializar propiedades
	private int idOferta;
	private int idMotivo;
	private String descMotivo;
	
	
	/**
	 * Reinicia los valores de la forma
	 * Method 'reset'
	 * 
	 */		
	public void reset() {
		this.idOferta = 0;
		this.idMotivo = 0;
		this.descMotivo = null;
	}	



	/**
	 * Method 'validate'
	 * 
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */		
	
	
	
	
	
	
	/**
	 * @return the idOferta
	 */
	public int getIdOferta() {
		return idOferta;
	}

	/**
	 * @param idOferta the idOferta to set
	 */
	public void setIdOferta(int idOferta) {
		this.idOferta = idOferta;
	}

	/**
	 * @return the idMotivo
	 */
	public int getIdMotivo() {
		return idMotivo;
	}

	/**
	 * @param idMotivo the idMotivo to set
	 */
	public void setIdMotivo(int idMotivo) {
		this.idMotivo = idMotivo;
	}

	/**
	 * @return the descMotivo
	 */
	public String getDescMotivo() {
		return descMotivo;
	}

	/**
	 * @param descMotivo the descMotivo to set
	 */
	public void setDescMotivo(String descMotivo) {
		this.descMotivo = descMotivo;
	}


	
}
