package mx.gob.stps.portal.web.candidate.form;

//import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

/*import static mx.gob.stps.portal.web.infra.utils.Constantes.CV_URL_LENGTH;
import static mx.gob.stps.portal.web.infra.utils.Constantes.CV_DESC_LENGTH;
*/
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class CurriculumCargaForm extends ActionForm {

	/**
	 * valor utilizado al serializar las instancias de esta clase 
	 */
	private static final long serialVersionUID = 5294401900872793864L;

	/**
	 * Genera mensajes en el medio configurado como bit&aacute;cora de la aplicaci&ooacute;n
	 */
	//private static Logger logger = Logger.getLogger(CurriculumCargaForm.class);
	
	/**
	 * Representa el URL desde el que se puede acceder al Curriculum Vitae del candidato
	 */
	private String videoURL;
	
	/**
	 * Contiene la descripci&oacute;n del Curriculum Vitae del candidato
	 */
	private String videoDescription;
	
	/**
	 * Indica si se aceptaron los t&eacute;rminos y condiciones para publicar
	 * el Curriculum Vitae del candidato.
	 */
	private boolean termsConditionsAccepted;
	
	
	public void reset() {
		this.termsConditionsAccepted = false;
		this.videoURL = "";
		this.videoDescription = "";
	}
	
	/**
	 * @return el URL desde el que se puede acceder al Curriculum Vitae del candidato
	 */
	public String getVideoURL() {
		return videoURL;
	}

	/**
	 * @param videoURL representa el URL desde el que se puede acceder al 
	 * Curriculum Vitae del candidato
	 */
	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}

	/**
	 * @return la descripci&oacute;n del video curr&iacute;culum del candidato
	 */
	public String getVideoDescription() {
		return videoDescription;
	}

	/**
	 * @param videoDescription la descripci&oacute;n del video a asignar
	 */
	public void setVideoDescription(String videoDescription) {
		this.videoDescription = videoDescription;
	}

	/**
	 * @return la indicaci&oacute;n de si los t&eacute;rminos y condiciones
	 *         fueron aceptados
	 */
	public boolean isTermsConditionsAccepted() {
		return termsConditionsAccepted;
	}

	/**
	 * @param termsConditionsAccepted la indicaci&oacute;n sobre si los
	 *        t&eacute;rminos y condiciones fueron aceptados
	 */
	public void setTermsConditionsAccepted(boolean termsConditionsAccepted) {
		this.termsConditionsAccepted = termsConditionsAccepted;
	}
	
	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		
		ActionErrors errors = new ActionErrors();
		String strMethod = request.getParameter("method");
		
		if (strMethod.equalsIgnoreCase("registrar")) {
			if (!this.termsConditionsAccepted || this.videoURL == null ||
					(this.videoURL != null && this.videoURL.equalsIgnoreCase(""))) {
				errors.add(ActionErrors.GLOBAL_MESSAGE,
						new ActionMessage("app.general.capObligatorios.err"));
			} else {
				if (!this.videoURL.matches("^https\\://[a-zA-Z0-9\\-\\.]+\\.[a-zA-Z]{2,3}(:[0-9]{3,4})?(/\\S*)?$")) {
					errors.add(ActionErrors.GLOBAL_MESSAGE,
							new ActionMessage("can.videoCV.videoURL.err"));
				}
				/*if (this.videoURL.length() > CV_URL_LENGTH) {
					errors.add(ActionErrors.GLOBAL_MESSAGE,
							   new ActionMessage("app.general.longitud.err",
									             "la ruta del video currículo",
									             "" + CV_URL_LENGTH));
				}
				if (this.videoDescription.length() > CV_DESC_LENGTH) {
					errors.add(ActionErrors.GLOBAL_MESSAGE,
							   new ActionMessage("app.general.longitud.err",
									             "la descripción del video currículo",
									             "" + CV_DESC_LENGTH));
				}*/
			}
		}
		return errors;
	}
}
