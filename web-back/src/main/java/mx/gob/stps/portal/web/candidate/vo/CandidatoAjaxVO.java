package mx.gob.stps.portal.web.candidate.vo;

import mx.gob.stps.portal.utils.Catalogos;

import java.io.Serializable;

public class CandidatoAjaxVO implements Serializable {
	private static final long serialVersionUID = -7924381536471603913L;
	private String nombre;
	private String curp;
	private String sexo;
	private String edad;
	private String entidad;
	private String fechaalta;
    private String ppcEstatusIdOpcion;
    private String correoElectronico;
    private String trabajaActualmente;
    private Long idCandidato;
    private Long idUsuario;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCurp() {
		return curp;
	}
	public void setCurp(String curp) {
		this.curp = curp;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getEdad() {
		return edad;
	}
	public void setEdad(String edad) {
		this.edad = edad;
	}
	public String getEntidad() {
		return entidad;
	}
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}
	public String getFechaalta() {
		return fechaalta;
	}
	public void setFechaalta(String fechaalta) {
		this.fechaalta = fechaalta;
	}

    public String getPpcEstatusIdOpcion() {
        return ppcEstatusIdOpcion;
    }

    public void setPpcEstatusIdOpcion(String ppcEstatusIdOpcion) {
        this.ppcEstatusIdOpcion = ppcEstatusIdOpcion;
    }

    public String getPpcEstatusOpcion() {
        return Catalogos.ESTATUS.getEstatus(Integer.valueOf(ppcEstatusIdOpcion)).getOpcion();
    }
    
    public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}    

	
    public String getTrabajaActualmente() {
		return trabajaActualmente;
	}
	public void setTrabajaActualmente(String trabajaActualmente) {
		this.trabajaActualmente = trabajaActualmente;
	}
	public boolean hasNeverBeenInTouchWithPpc() {
        return ppcEstatusIdOpcion != null && ppcEstatusIdOpcion.equals("0");
    }

    public boolean hasDecidedNotToEnrollToPpc() {
        if (!hasNeverBeenInTouchWithPpc()) { // It has been indeed in touch with PPC
            return Catalogos.ESTATUS.getEstatus(Integer.valueOf(ppcEstatusIdOpcion)) == Catalogos.ESTATUS.NO_INSCRITO_PPC;
        }
        return false;
    }

    public boolean isActiveToPpc() {
        if (!hasNeverBeenInTouchWithPpc()) { // It has been indeed in touch with PPC
            return Catalogos.ESTATUS.getEstatus(Integer.valueOf(ppcEstatusIdOpcion)) == Catalogos.ESTATUS.ACTIVO_PPC;
        }
        return false;
    }

    public boolean isInactiveToPpc() {
        if (!hasNeverBeenInTouchWithPpc()) { // It has been indeed in touch with PPC
            return Catalogos.ESTATUS.getEstatus(Integer.valueOf(ppcEstatusIdOpcion)) == Catalogos.ESTATUS.INACTIVO_PPC;
        }
        return false;
    }

    public boolean isNotAnyMoreEnrolledToPpc() {
        if (!hasNeverBeenInTouchWithPpc()) { // It has been indeed in touch with PPC
            return Catalogos.ESTATUS.getEstatus(Integer.valueOf(ppcEstatusIdOpcion)) == Catalogos.ESTATUS.FUERA_PPC;
        }
        return false;
    }
	public Long getIdCandidato() {
		return idCandidato;
	}
	public void setIdCandidato(Long idCandidato) {
		this.idCandidato = idCandidato;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
}
