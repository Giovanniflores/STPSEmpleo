package mx.gob.stps.portal.web.candidate.vo;

public class CandidatoRespuestaBaseAjaxVO extends RespuestaBaseAjaxVO{
	String curp;

 	String nombre;
 	String apellido1;
 	String apellido2;
 	Integer idEntidadNacimiento;

 	String fechaNacimientoString;
 	
    Integer genero;

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public Integer getIdEntidadNacimiento() {
		return idEntidadNacimiento;
	}

	public void setIdEntidadNacimiento(Integer idEntidadNacimiento) {
		this.idEntidadNacimiento = idEntidadNacimiento;
	}

	public String getFechaNacimientoString() {
		return fechaNacimientoString;
	}

	public void setFechaNacimientoString(String fechaNacimientoString) {
		this.fechaNacimientoString = fechaNacimientoString;
	}

	public Integer getGenero() {
		return genero;
	}

	public void setGenero(Integer genero) {
		this.genero = genero;
	}
    

		
}
