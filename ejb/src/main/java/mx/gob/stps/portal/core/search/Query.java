package mx.gob.stps.portal.core.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Query implements Serializable {
	
	private static final long serialVersionUID = 928864721568277233L;
	
	private Integer horario = null;
	private Integer edad = null;
	private Integer edad_de = null;
	private Integer edad_hasta = null;
	private Integer carrera=null;
	private Integer ocupacion=null;
	private Integer grado_estudios=null;
	private Integer status_estudio=null;
	private Integer experiencia_total = null;
	private Double salario = null;
	private Double salario_de = null;
	private Double salario_hasta = null;
	private Boolean disponibilidad = null;
	private Boolean indicador_estudios = null;
	private Boolean disponibilidad_viajar_ciudad = null;
	private List<Idioma> idiomas = new ArrayList<Idioma>();
	private List<Integer> otras_carreras = new ArrayList<Integer>();
	private List<Integer> otras_ocupaciones = new ArrayList<Integer>();
	private List<Conocimiento> habilidades = new ArrayList<Conocimiento>();
	private List<Conocimiento> conocimientos = new ArrayList<Conocimiento>();
	private List<InformacionAcademica> informacion_academica = new ArrayList<InformacionAcademica>();
	private final Set<String> words = new TreeSet<String>();
    private Integer idEntidad;
    private Integer idMunicipio;
    private Integer fuente;
	
    public Integer getTipoEmpleo() {
		return horario;
	}
	public void setTipoEmpleo(Integer tipoEmpleo) {
		this.horario = tipoEmpleo;
	}
	public List<Integer> getOtras_carreras() {
		return otras_carreras;
	}
	public void setOtras_carreras(List<Integer> otras_carreras) {
		this.otras_carreras = otras_carreras;
	}
	public Integer getEdad() {
		return edad;
	}
	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	public Integer getEdad_de() {
		return edad_de;
	}
	public void setEdad_de(Integer edad_de) {
		this.edad_de = edad_de;
	}
	public Integer getEdad_hasta() {
		return edad_hasta;
	}
	public void setEdad_hasta(Integer edad_hasta) {
		this.edad_hasta = edad_hasta;
	}
	public Integer getCarrera() {
		return carrera;
	}
	public void setCarrera(Integer carrera) {
		this.carrera = carrera;
	}
	public Integer getOcupacion() {
		return ocupacion;
	}
	public void setOcupacion(Integer ocupacion) {
		this.ocupacion = ocupacion;
	}
	public Integer getGrado_estudios() {
		return grado_estudios;
	}
	public void setGrado_estudios(Integer grado_estudios) {
		this.grado_estudios = grado_estudios;
	}
	public Integer getStatus_estudio() {
		return status_estudio;
	}
	public void setStatus_estudio(Integer status_estudio) {
		this.status_estudio = status_estudio;
	}
	public Integer getExperiencia_total() {
		return experiencia_total;
	}
	public void setExperiencia_total(Integer experiencia_total) {
		this.experiencia_total = experiencia_total;
	}
	public Double getSalario() {
		return salario;
	}
	public void setSalario(Double salario) {
		this.salario = salario;
	}
	public Double getSalario_de() {
		return salario_de;
	}
	public void setSalario_de(Double salario_de) {
		this.salario_de = salario_de;
	}
	public Double getSalario_hasta() {
		return salario_hasta;
	}
	public void setSalario_hasta(Double salario_hasta) {
		this.salario_hasta = salario_hasta;
	}
	public Boolean getDisponibilidad() {
		return disponibilidad;
	}
	public void setDisponibilidad(Boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
	public Boolean getIndicador_estudios() {
		return indicador_estudios;
	}
	public void setIndicador_estudios(Boolean indicador_estudios) {
		this.indicador_estudios = indicador_estudios;
	}
	public Boolean getDisponibilidad_viajar_ciudad() {
		return disponibilidad_viajar_ciudad;
	}
	public void setDisponibilidad_viajar_ciudad(Boolean disponibilidad_viajar_ciudad) {
		this.disponibilidad_viajar_ciudad = disponibilidad_viajar_ciudad;
	}
	public List<Idioma> getIdiomas() {
		return idiomas;
	}
	public void setIdiomas(List<Idioma> idiomas) {
		this.idiomas = idiomas;
	}
	public List<Conocimiento> getHabilidades() {
		return habilidades;
	}
	public void setHabilidades(List<Conocimiento> habilidades) {
		this.habilidades = habilidades;
	}
	public List<Conocimiento> getConocimientos() {
		return conocimientos;
	}
	public void setConocimientos(List<Conocimiento> conocimientos) {
		this.conocimientos = conocimientos;
	}
	public List<InformacionAcademica> getInformacion_academica() {
		return informacion_academica;
	}
	public void setInformacion_academica(
			List<InformacionAcademica> informacion_academica) {
		this.informacion_academica = informacion_academica;
	}
	public List<Integer> getOtras_ocupaciones() {
		return otras_ocupaciones;
	}
	public void setOtras_ocupaciones(List<Integer> otras_ocupaciones) {
		this.otras_ocupaciones = otras_ocupaciones;
	}
	public void addWord(String word){
		words.add(word);
	}
	public Set<String> getWords() {
		return words;
	}
public Integer getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(Integer idEntidad) {
        this.idEntidad = idEntidad;
    }

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Integer getFuente() {
        return fuente;
    }

    public void setFuente(Integer fuente) {
        this.fuente = fuente;
    }

    @Override
    public String toString() {
        return "{horario : " + horario + ", edad : " + edad + ", edad_de : " + edad_de + ", edad_hasta : " + edad_hasta
            + ", carrera : " + carrera + ", ocupacion : " + ocupacion + ", grado_estudios : " + grado_estudios
            + ", status_estudio : " + status_estudio + ", experiencia_total : " + experiencia_total 
            + ", salario : " + salario + ", salario_de : " + salario_de + ", salario_hasta : " + salario_hasta
            + ", disponibilidad : " + disponibilidad + ", indicador_estudios : " + indicador_estudios
            + ", disponibilidad_viajar_ciudad : " + disponibilidad_viajar_ciudad + ", idiomas : {" + idiomas
            + "}, otras_carreras : {" + otras_carreras + "}, otras_ocupaciones : {" + otras_ocupaciones
            + "}, habilidades : {" + habilidades + "}, conocimientos : {" + conocimientos
            + "}, informacion_academica : {" + informacion_academica + "}, words : {" + words
            + "}, idEntidad : " + idEntidad + ", idMunicipio : " + idMunicipio + ", fuente : " + fuente + "}";
    }
}