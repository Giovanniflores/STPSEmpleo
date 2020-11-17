package mx.gob.stps.portal.core.autorizacion.vo;

import java.io.Serializable;

public class ParametroVO implements Serializable {
	private static final long serialVersionUID = 473235806417738615L;
	private long idParametro;
	private String descripcion;
	private String valor;

	public long getIdParametro() {
		return idParametro;
	}
	public void setIdParametro(long idParametro) {
		this.idParametro = idParametro;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
}
