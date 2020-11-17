/**
 * 
 */
package mx.gob.stps.portal.web.company.form;

import javax.servlet.http.HttpServletRequest;

import mx.gob.stps.portal.core.infra.utils.Constantes;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

/**
 * @author jose.jimenez
 *
 */
public class UploadLogoForm extends ActionForm {


	/**
	 * valor utilizado al serializar las instancias de esta clase
	 */
	private static final long serialVersionUID = 1578273675690964933L;
	
	/**
	 * el archivo que contiene la fotograf&iacute; a cargar
	 */
	private FormFile logoFile;
	
	private String message;

	public void reset() {
		this.logoFile = null;
		this.message = null;
	}

	/**
	 * @return the logoFile
	 */
	public FormFile getLogoFile() {
		return logoFile;
	}

	/**
	 * @param logoFile the logoFile to set
	 */
	public void setLogoFile(FormFile logoFile) {
		this.logoFile = logoFile;
	}
	
	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		
		ActionErrors errors = new ActionErrors();
		String strMethod = request.getParameter("method");
		String filename = null;
		//System.out.println("------UploadLogoForm.validate strMethod:" + strMethod);
		if (strMethod.equalsIgnoreCase("registrar")) {
			if (this.logoFile == null) {
				this.setMessage("Debe seleccionar un archivo con extensión GIF, JPG o JPEG");
				errors.add("logoFile", new ActionMessage("can.foto.sinArchivo.err"));
			} else {
				filename = this.logoFile.getFileName() != null 
				           ? this.logoFile.getFileName().toLowerCase() : null;
				           //System.out.println("------UploadLogoForm.validate filename:" + filename);
				if (filename != null && (!filename.endsWith(".gif") && 
						!filename.endsWith(".jpg") && !filename.endsWith(".jpeg"))) {
					this.setMessage("El tipo de archivo seleccionado debe ser GIF, JPG o JPEG. Intente nuevamente con otro archivo.");
					errors.add("logoFile",new ActionMessage("can.foto.extArchivo.err"));					
				} else if (this.logoFile.getFileSize() > Constantes.TAMANIO_MAXIMO_FOTO) {
					this.setMessage("El tamaño del archivo seleccionado es superior al permitido (30KB). Intente nuevamente con otro archivo.");
					errors.add("logoFile",
							new ActionMessage("can.foto.tamanioFoto.err"));
				}
			}
		}
		return errors;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
