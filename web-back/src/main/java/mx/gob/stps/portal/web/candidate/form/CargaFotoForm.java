/**
 * 
 */
package mx.gob.stps.portal.web.candidate.form;

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
public class CargaFotoForm extends ActionForm {

	/**
	 * valor utilizado al serializar las instancias de esta clase
	 */
	private static final long serialVersionUID = -4997179490844456430L;

	/**
	 * el archivo que contiene la fotograf&iacute; a cargar
	 */
	private FormFile archivoFoto;
	
	private String message;

	public void reset() {
		this.archivoFoto = null;
		this.message = null;
	}

	/**
	 * @return the archivoFoto
	 */
	public FormFile getArchivoFoto() {
		return archivoFoto;
	}

	/**
	 * @param archivoFoto the archivoFoto to set
	 */
	public void setArchivoFoto(FormFile archivoFoto) {
		this.archivoFoto = archivoFoto;
	}
	
	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		
		ActionErrors errors = new ActionErrors();
		String strMethod = request.getParameter("method");
		String filename = null;
		
		if (strMethod.equalsIgnoreCase("registrar")) {
			if (this.archivoFoto == null) {
				this.setMessage("Debe seleccionar un archivo con extensión GIF, JPG o JPEG");
				errors.add("archivoFoto", new ActionMessage("can.foto.sinArchivo.err"));
			} else {
				filename = this.archivoFoto.getFileName() != null ? this.archivoFoto.getFileName().trim().toLowerCase() : null;
				
				if (filename != null && (!filename.endsWith(".gif") && !filename.endsWith(".jpg") && !filename.endsWith(".jpeg"))) {
					errors.add("archivoFoto", new ActionMessage("can.foto.extArchivo.err"));
					this.setMessage("El tipo de archivo seleccionado debe ser GIF, JPG o JPEG. Intente nuevamente con otro archivo.");
				} else if (this.archivoFoto.getFileSize() > Constantes.TAMANIO_MAXIMO_FOTO) {
					errors.add("archivoFoto", new ActionMessage("can.foto.tamanioFoto.err"));
					this.setMessage("El tamaño del archivo seleccionado es superior al permitido (30KB). Intente nuevamente con otro archivo.");
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
