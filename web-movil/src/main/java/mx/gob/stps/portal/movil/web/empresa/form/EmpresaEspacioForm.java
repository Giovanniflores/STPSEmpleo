package mx.gob.stps.portal.movil.web.empresa.form;

import org.apache.struts.action.ActionForm;

public class EmpresaEspacioForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8445390268608633076L;
	
	private long idEmpresa;
	private String nombreEmpresa;
	private String contactoEmpresa;
	private String idPortalEmpleo;
	
	
	public long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public String getContactoEmpresa() {
		return contactoEmpresa;
	}
	public void setContactoEmpresa(String contactoEmpresa) {
		this.contactoEmpresa = contactoEmpresa;
	}
	public String getIdPortalEmpleo() {
		return idPortalEmpleo;
	}
	public void setIdPortalEmpleo(String idPortalEmpleo) {
		this.idPortalEmpleo = idPortalEmpleo;
	}
	
	
	

}
