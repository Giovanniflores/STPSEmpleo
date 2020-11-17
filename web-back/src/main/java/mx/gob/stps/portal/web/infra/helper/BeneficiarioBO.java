package mx.gob.stps.portal.web.infra.helper;

import java.io.Serializable;

import mx.gob.stps.portal.persistencia.vo.BeneficiarioVO;
import mx.gob.stps.portal.core.infra.utils.Constantes.DECISION;
import mx.gob.stps.portal.web.infra.utils.Constantes.PARENTESCO;

public class BeneficiarioBO extends BeneficiarioVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6414731838722626474L;
	
	private int index;
	
	private String estado;
	
	private String parentesco;
	
	private String nombreCompleto;
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getParentesco() {
		return PARENTESCO.getParentesco(this.getIdParentesco().intValue());
	}

	public String getNombreCompleto() {
		StringBuilder name = new StringBuilder();
		name.append(this.getNombre()).append(" ").append(this.getPrimerApellido());
		if (null != this.getSegundoApellido()) name.append(" ").append(this.getSegundoApellido());
		return name.toString();
	}
	
	public String getEstado() {
		StringBuilder status = new StringBuilder();
		if (null != this.getDependiente() && DECISION.SI.getIdOpcion() == this.getDependiente())
			status.append("Dependiente");
		if (null != this.getBeneficiario() && DECISION.SI.getIdOpcion() == this.getBeneficiario()) {
			if (status.length() >0) status.append(", Beneficiario");
			else status.append("Beneficiario");
		}
		if (null != this.getFinado() && DECISION.SI.getIdOpcion() == this.getFinado()) {
			if (status.length() >0) status.append(", Finado");
			else status.append("Finado");
		}
		return status.toString();
	}

	@Override
	public String toString() {
		return estado + " " + parentesco + " " + nombreCompleto + super.toString();
	}
}