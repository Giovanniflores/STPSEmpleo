package mx.gob.stps.portal.core.persistencia.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ACCION")
public class Accion implements Serializable {
	private static final long serialVersionUID = -6117465895144060095L;
	
	@Id
	@Column(name = "ID_ACCION")
	private long idAccion;
	
	@Column(name = "VINCULO")
	private String vinculo;
	
	@Column(name = "AUTENTICADO")
	private int autenticado;
		
	public long getIdAccion() {
		return idAccion;
	}
	public void setIdAccion(long idAccion) {
		this.idAccion = idAccion;
	}
	public String getVinculo() {
		return vinculo;
	}
	public void setVinculo(String vinculo) {
		this.vinculo = vinculo;
	}
	public int getAutenticado() {
		return autenticado;
	}
	public void setAutenticado(int autenticado) {
		this.autenticado = autenticado;
	}
}
