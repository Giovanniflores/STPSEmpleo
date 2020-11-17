package mx.gob.stps.portal.movil.app.model.rest;

import java.util.ArrayList;
import java.util.List;

import mx.gob.stps.portal.movil.app.model.base.BaseRestDTO;
import mx.gob.stps.portal.persistencia.vo.CaptchaPreguntasVO;
import mx.gob.stps.portal.persistencia.vo.CaptchaRespuestasVO;

public class RecuperacionContrasenaDTO  extends BaseRestDTO{

	String pregunta;
	
	List<String> respuestas;
	
	String respuesta;
	
	String usuario;
	
	String curp;
	
	String clave;
	
	String nombre;
	
	String correo;
	
	String date;
	
	String tipo;
	
	String id;
	
	String tipoEmpresa;
	
	String nombreEmpresa;

	public RecuperacionContrasenaDTO (){
		respuestas = new ArrayList<String>();
	}
	
	public RecuperacionContrasenaDTO(CaptchaPreguntasVO captchapregunta) {
		respuestas = new ArrayList<String>();
		pregunta = captchapregunta.getPregunta();
		for(CaptchaRespuestasVO captchaRespuestasVO: captchapregunta.getCaptchaRespuestas()){
			respuestas.add(captchaRespuestasVO.getRespuesta());
		}
	}

	public RecuperacionContrasenaDTO(String message) {
		super.setResult(message);
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public List<String> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(List<String> respuestas) {
		this.respuestas = respuestas;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTipoEmpresa() {
		return tipoEmpresa;
	}

	public void setTipoEmpresa(String tipoEmpresa) {
		this.tipoEmpresa = tipoEmpresa;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	
	
	

	
	
	
}
