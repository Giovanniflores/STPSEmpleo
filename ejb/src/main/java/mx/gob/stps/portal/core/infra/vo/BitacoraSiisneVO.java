package mx.gob.stps.portal.core.infra.vo;

import java.io.Serializable;
import java.util.Date;


public class BitacoraSiisneVO implements Serializable {

	private static final long serialVersionUID = -4310893249396588281L;
	
	private long idBitacora;

	private long idOperacion;
	
	private long idUsuario;	
	
	private Date fechaOperacion;

	private long idReferencia;	
	
	private String info;

	private long fuente;

	public BitacoraSiisneVO(){}
	
	public BitacoraSiisneVO(long idOperacion, long idReferencia, long idUsuario, long fuente, String info){
		
		if (idOperacion == 0)
			throw new IllegalArgumentException("idOperacion es requerido");

		if (idReferencia == 0)
			throw new IllegalArgumentException("idReferencia es requerido");

		if (idUsuario == 0)
			throw new IllegalArgumentException("idUsuario es requerido");
		
		if (fuente == 0)
			throw new IllegalArgumentException("fuente es requerido");
		
		this.idOperacion = idOperacion;
		this.idReferencia = idReferencia;
		this.idUsuario = idUsuario;
		this.info = info;
		this.fuente = fuente;
	}

	public long getIdBitacora() {
		return idBitacora;
	}

	public void setIdBitacora(long idBitacora) {
		this.idBitacora = idBitacora;
	}

	public long getIdOperacion() {
		return idOperacion;
	}

	public void setIdOperacion(long idOperacion) {
		this.idOperacion = idOperacion;
	}

	public Date getFechaOperacion() {
		return fechaOperacion;
	}

	public void setFechaOperacion(Date fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}

	public long getIdReferencia() {
		return idReferencia;
	}

	public void setIdReferencia(long idReferencia) {
		this.idReferencia = idReferencia;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public long getFuente() {
		return fuente;
	}

	public void setFuente(long fuente) {
		this.fuente = fuente;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}	
	
}