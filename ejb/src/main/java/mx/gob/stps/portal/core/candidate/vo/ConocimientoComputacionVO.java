package mx.gob.stps.portal.core.candidate.vo;

import java.io.Serializable;

public class ConocimientoComputacionVO implements Serializable {
	private static final long serialVersionUID = 2311055365741943470L;

	protected long idConocimientoComputacion;
	protected long idPropietario;
	protected long idTipoPropietario;
	
	private int procesadorTxt;
	private int hojaCalculo;
	private int internet;
	private int redesSociales;
	private String otros;

	public long getIdConocimientoComputacion() {
		return idConocimientoComputacion;
	}
	public void setIdConocimientoComputacion(long idConocimientoComputacion) {
		this.idConocimientoComputacion = idConocimientoComputacion;
	}
	public long getIdPropietario() {
		return idPropietario;
	}
	public void setIdPropietario(long idPropietario) {
		this.idPropietario = idPropietario;
	}
	public long getIdTipoPropietario() {
		return idTipoPropietario;
	}
	public void setIdTipoPropietario(long idTipoPropietario) {
		this.idTipoPropietario = idTipoPropietario;
	}
	public int getProcesadorTxt() {
		return procesadorTxt;
	}
	public void setProcesadorTxt(int procesadorTxt) {
		this.procesadorTxt = procesadorTxt;
	}
	public int getHojaCalculo() {
		return hojaCalculo;
	}
	public void setHojaCalculo(int hojaCalculo) {
		this.hojaCalculo = hojaCalculo;
	}
	public int getInternet() {
		return internet;
	}
	public void setInternet(int internet) {
		this.internet = internet;
	}
	public int getRedesSociales() {
		return redesSociales;
	}
	public void setRedesSociales(int redesSociales) {
		this.redesSociales = redesSociales;
	}
	public String getOtros() {
		return otros;
	}
	public void setOtros(String otros) {
		this.otros = otros;
	}

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (procesadorTxt == 1) {
            sb.append("Procesador de texto").append(", ");
        }
        if (hojaCalculo == 1) {
            sb.append("Hoja de calculo").append(", ");
        }
        if (internet == 1) {
            sb.append("Internet").append(", ");
        }
        if (redesSociales == 1) {
            sb.append("Redes sociales").append(", ");
        }

        if (sb.length() != 0) {
            sb.setLength(sb.length() - 2); // remove last two characters (comma and white_space)
            sb.append(". ");
        }

        if (otros != null && !otros.equals("")) {
            sb.append(otros);
        }

        return sb.toString();
    }
}
