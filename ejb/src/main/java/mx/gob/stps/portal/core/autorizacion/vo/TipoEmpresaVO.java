package mx.gob.stps.portal.core.autorizacion.vo;

import java.io.Serializable;

import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_EMPRESA;

public final class TipoEmpresaVO implements Serializable {
	private static final long serialVersionUID = 5068507325615578337L;
	private long idEmpresa;
	private int estatus;
	private TIPO_EMPRESA tipoEmpresa;

	public long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public int getEstatus() {
		return estatus;
	}
	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}
	public TIPO_EMPRESA getTipoEmpresa() {
		return tipoEmpresa;
	}
	public void setTipoEmpresa(TIPO_EMPRESA tipoEmpresa) {
		this.tipoEmpresa = tipoEmpresa;
	}
}