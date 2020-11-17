package mx.gob.stps.portal.core.persistencia.facade;

import java.io.Serializable;

public class QuejasGabssa implements Serializable {

	private static final long serialVersionUID = -6558784347371780702L;
	
	private String usuario_id;
	private int categoria;
	private String asunto;
	private String mensaje;
	private String correo_electronico;
	private String nombre;
	private String primer_apellido;
	private String segundo_apellido;
	private int tipo_telefono;
	private String clave_lada;
	private String telefono;
	
	public QuejasGabssa(){
	}
	
	public QuejasGabssa(String usuario_id,int categoria,String asunto,String mensaje,String correo_electronico,String nombre,String primer_apellido,String segundo_apellido,int tipo_telefono,String clave_lada,String telefono) {
		this.usuario_id = usuario_id;
		this.categoria = categoria;
		this.asunto = asunto;
		this.mensaje = mensaje;
		this.correo_electronico = correo_electronico;
		this.nombre = nombre;
		this.primer_apellido = primer_apellido;
		this.segundo_apellido = segundo_apellido;
		this.tipo_telefono = tipo_telefono;
		this.clave_lada = clave_lada;
		this.telefono = telefono; 
	}
	
	public String getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(String usuario_id) {
		this.usuario_id = usuario_id;
	}
	
	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getCorreo_electronico() {
		return correo_electronico;
	}

	public void setCorreo_electronico(String correo_electronico) {
		this.correo_electronico = correo_electronico;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrimer_apellido() {
		return primer_apellido;
	}

	public void setPrimer_apellido(String primer_apellido) {
		this.primer_apellido = primer_apellido;
	}

	public String getSegundo_apellido() {
		return segundo_apellido;
	}

	public void setSegundo_apellido(String segundo_apellido) {
		this.segundo_apellido = segundo_apellido;
	}

	public int getTipo_telefono() {
		return tipo_telefono;
	}

	public void setTipo_telefono(int tipo_telefono) {
		this.tipo_telefono = tipo_telefono;
	}

	public String getClave_lada() {
		return clave_lada;
	}

	public void setClave_lada(String clave_lada) {
		this.clave_lada = clave_lada;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
