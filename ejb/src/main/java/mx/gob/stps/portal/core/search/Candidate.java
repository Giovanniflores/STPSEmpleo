package mx.gob.stps.portal.core.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.gob.stps.portal.core.candidate.vo.ConocimientoHabilidadVO;
import mx.gob.stps.portal.core.candidate.vo.GradoAcademicoVO;

public class Candidate implements Serializable {
	
	private static final long serialVersionUID = -8535801387330441620L;
	
	public long id = -1;
    public int edad = -1;
    public int experiencia = -1;
	public int tipoEmpleo = -1;
    public boolean indicardor_estudios;
    public boolean disponibilidad;
    public boolean disponibilidad_viajar_ciudad;
    public double salario;
    public int ocupacion=-1;
    public String palabras;

    private int idEntidad;
    private int idMunicipio;
    private String discapacidades;
    
    private GradoAcademicoVO gradoAcademico;
    private List<ConocimientoHabilidadVO> conocimientos;
    private List<ConocimientoHabilidadVO> habilidades;
    
    public Candidate(){
        conocimientos = new ArrayList<ConocimientoHabilidadVO>();
        habilidades = new ArrayList<ConocimientoHabilidadVO>();
    }

	public long getIdCandidato() {
		return id;
	}
	public void setIdCandidato(long id) {
		this.id = id;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public int getExperiencia() {
		return experiencia;
	}
	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}
	public int getTipoEmpleo() {
		return tipoEmpleo;
	}
	public void setTipoEmpleo(int tipoEmpleo) {
		this.tipoEmpleo = tipoEmpleo;
	}
	public boolean isIndicador_estudios() {
		return indicardor_estudios;
	}
	public void setIndicador_estudios(boolean indicardor_estudios) {
		this.indicardor_estudios = indicardor_estudios;
	}
	public boolean isDisponibilidadRadicar() {
		return disponibilidad;
	}
	public void setDisponibilidadRadicar(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
	public boolean isDisponibilidad_viajar_ciudad() {
		return disponibilidad_viajar_ciudad;
	}
	public void setDisponibilidad_viajar_ciudad(boolean disponibilidad_viajar_ciudad) {
		this.disponibilidad_viajar_ciudad = disponibilidad_viajar_ciudad;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public int getOcupacion() {
		return ocupacion;
	}
	public void setOcupacion(int ocupacion) {
		this.ocupacion = ocupacion;
	}
	public String getPalabras() {
		return palabras;
	}
	public void setPalabras(String palabras) {
		this.palabras = palabras;
	}
	public GradoAcademicoVO getGradoAcademico() {
		return gradoAcademico;
	}
	public void setGradoAcademico(GradoAcademicoVO gradoAcademico) {
		this.gradoAcademico = gradoAcademico;
	}
	public void addConocimiento(ConocimientoHabilidadVO conocimiento) {
		conocimientos.add(conocimiento);
	}
	public List<ConocimientoHabilidadVO> getConocimientos() {
		return conocimientos;
	}
	public void addHabilidade(ConocimientoHabilidadVO habilidad) {
		habilidades.add(habilidad);
	}
	public List<ConocimientoHabilidadVO> getHabilidades() {
		return habilidades;
	}
	
	public int getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(int idEntidad) {
		this.idEntidad = idEntidad;
	}

	public int getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(int idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
	
	public String getDiscapacidades() {
		return discapacidades;
	}

	public void setDiscapacidades(String discapacidades) {
		this.discapacidades = discapacidades;
	}

	@Override
	public String toString() {
		return "Candidate [id=" + id + ", edad=" + edad + ", experiencia="
				+ experiencia + ", tipoEmpleo=" + tipoEmpleo
				+ ", indicardor_estudios=" + indicardor_estudios
				+ ", disponibilidad=" + disponibilidad
				+ ", disponibilidad_viajar_ciudad="
				+ disponibilidad_viajar_ciudad + ", salario=" + salario
				+ ", ocupacion=" + ocupacion + ", palabras=" + palabras
				+ ", gradoAcademico=" + gradoAcademico + ", conocimientos="
				+ conocimientos + ", habilidades=" + habilidades + "]";
	}
}