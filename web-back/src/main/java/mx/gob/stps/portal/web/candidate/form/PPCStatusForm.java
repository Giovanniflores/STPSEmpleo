package mx.gob.stps.portal.web.candidate.form;

import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.utils.Catalogos.ESTATUS;

import org.apache.struts.action.ActionForm;

public class PPCStatusForm extends ActionForm {

	public void setPPCStatusForm(Integer trabajando, Integer idPpc, boolean editable) {
		sepuedeeditarTrabajando = editable;
		if (Catalogos.ESTATUS.ACTIVO_PPC.getIdOpcion() == idPpc) {
			ppc = true;
		} else {
			ppc = false;
		}
		ppcEstatus = Catalogos.ESTATUS.getEstatus(idPpc);

		if (trabajando == Catalogos.TRABAJA_ACTUALMENTE.SI.getIdOpcion()) {
			this.trabajando = true;
		} else {
			this.trabajando = false;
		}

		if (ppc && this.trabajando && !sepuedeeditarTrabajando) {
			errorMessageTab = basicTabMessage ; /*+ "\n" + notSetNoToTrabajando;*/
			errorMessageTabDoble = basicTabMessage; /* + "'+\n'"+notSetNoToTrabajando;*/
			mensajeExtra = true;
		} else {
			errorMessageTab = basicTabMessage;
			errorMessageTabDoble = basicTabMessage;
			mensajeExtra = false;
		}

	}

	// Cambio ppc
	private boolean ppc;
	private ESTATUS ppcEstatus;
	private String message;

	private boolean trabajando;
	private boolean sepuedeeditarTrabajando;
	private boolean mensajeExtra;

	static String basicTabMessage = "Los datos no guardados se perderán ¿Continuar?";
	static String notSetNoToTrabajando = "Es necesario cambiar el estatus de trabajas actual a no para que puedes aplicar para el programa de PPC. ";

	private String errorMessageTab;
	private String errorMessageTabDoble;

	public String getErrorMessageTab() {
		return errorMessageTab;
	}

	

	public boolean isPpc() {
		return ppc;
	}

	public ESTATUS getPpcEstatus() {
		return ppcEstatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isTrabajando() {
		return trabajando;
	}

	public boolean isSepuedeeditarTrabajando() {
		return sepuedeeditarTrabajando;
	}



	public String getErrorMessageTabDoble() {
		return errorMessageTabDoble;
	}



	public boolean isMensajeExtra() {
		return mensajeExtra;
	}



	public void setMensajeExtra(boolean mensajeExtra) {
		this.mensajeExtra = mensajeExtra;
	}



	public static String getNotSetNoToTrabajando() {
		return notSetNoToTrabajando;
	}



	public static void setNotSetNoToTrabajando(String notSetNoToTrabajando) {
		PPCStatusForm.notSetNoToTrabajando = notSetNoToTrabajando;
	}


	
	

}
