package mx.gob.stps.portal.persistencia.vo;

import java.io.Serializable;

public class OfertasRecientesVO implements Serializable{
		
	private static final long serialVersionUID = -6159592923883659931L;
	private int idOfertaEmpleo;
	private String tituloOferta;
	private String ubicacion;
	private String vigencia;
	private String empresa;
	private int salario;
	
public OfertasRecientesVO(int idOfertaEmpleo,String tituloOferta,String ubicacion,String vigencia,int salario,String empresa){
		
		this.idOfertaEmpleo=idOfertaEmpleo;
		this.tituloOferta=tituloOferta;
		this.ubicacion=ubicacion;
		this.vigencia=vigencia;
		this.salario=salario;
		this.empresa=empresa;	
	}

	

	public int getIdOfertaEmpleo() {
		return idOfertaEmpleo;
	}
	public void setIdOfertaEmpleo(int idOfertaEmpleo) {
		this.idOfertaEmpleo = idOfertaEmpleo;
	}

	public String getTituloOferta() {
		return tituloOferta;
	}
	public void setTituloOferta(String tituloOferta) {
		this.tituloOferta = tituloOferta;
	}

	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	public String getVigencia() {
		return vigencia;
	}
	public void setVigencia(String vigencia) {
		this.vigencia = vigencia;
	}
	
	public int getSalario(){
		return salario;
	}
	public void setSalario(int salario){
		this.salario = salario;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	@Override
	public String toString() {
		return "OfertasRecientesVO [idOfertaEmpleo=" + idOfertaEmpleo
				+ ", tituloOferta=" + tituloOferta + ", ubicacion=" + ubicacion
				+ ", vigencia=" + vigencia + ", salario=" + salario 
				+ ", empresa=" + empresa + "]";
	}             
}

