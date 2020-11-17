package mx.gob.stps.portal.core.oferta.busqueda.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.infra.vo.BitacoraVO;
import mx.gob.stps.portal.persistencia.vo.OfertaFuncionVO;

public class OfertaPorCanalVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7022163441031354753L;

	private long idEmpresa;
	
	private long idOfertaEmpleo;

	private String tituloOferta;

	private String ubicacion;

	private String empresa;

	private double salario;

	@SuppressWarnings("unused")
	private String sSalario;

	private String gradoEstudio;

	private String carrera;

	private String funciones;

	private String edad;

	private String idiomas;

	private String horario;

	private int numeroPlazas;

	private String medioContacto;
	
	private String bolsaTrabajo;
	
	private Date fechaInicio;

	private Date fechaFin;	
	
	private String fechaInicioString;
	
	private String fechaFinString;
	
	private int postulados;

	private int limitePostulantes;

	private long compatibility;
	
	private String habilidadGeneral;
	  
	private String experiencia;
	  
	private String competencias;	
	
	private List<BitacoraVO> bitacora;
	
	private String ocupacion;
	
	private List<String> carreras;
	
	private List<String> conocimientos;	
	
	//COMENTAR EN PRODUCCION??
	private long estatus;
	//COMENTAR EN PRODUCCION
	private long idRegistroPorValidar;
	
	private String motivoRechazo;
	
	private String detalleMotivoRechazo;
	
	private String entidad;
	
	private String municipio;
	
	private long idMunicipio;
	
	private long idArea;
	
	private long idSubArea;
	
	private List<OfertaFuncionVO> funcionList;
	
	/**
	 * @return the idEmpresa
	 */
	public long getIdEmpresa() {
		return idEmpresa;
	}

	/**
	 * @param idEmpresa the idEmpresa to set
	 */
	public void setIdEmpresa(long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	/**
	 * @return the idOfertaEmpleo
	 */
	public long getIdOfertaEmpleo() {
		return idOfertaEmpleo;
	}

	/**
	 * @param idOfertaEmpleo the idOfertaEmpleo to set
	 */
	public void setIdOfertaEmpleo(long idOfertaEmpleo) {
		this.idOfertaEmpleo = idOfertaEmpleo;
	}

	/**
	 * @return the tituloOferta
	 */
	public String getTituloOferta() {
		return tituloOferta;
	}

	/**
	 * @param tituloOferta the tituloOferta to set
	 */
	public void setTituloOferta(String tituloOferta) {
		this.tituloOferta = tituloOferta;
	}

	/**
	 * @return the ubicacion
	 */
	public String getUbicacion() {
		return ubicacion;
	}

	/**
	 * @param ubicacion the ubicacion to set
	 */
	public void setUbicacion(String ubicacion) {
		if (null != ubicacion && ubicacion.length() > 3)
			this.ubicacion = ubicacion;
		else this.ubicacion = "";
	}

	/**
	 * @return the empresa
	 */
	public String getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa the empresa to set
	 */
	public void setEmpresa(String empresa) {
		this.empresa = (null != empresa ? empresa : "");
	}

	/**
	 * @return the salario
	 */
	public double getSalario() {
		return salario;
	}

	/**
	 * @param salario the salario to set
	 */
	public void setSalario(double salario) {
		this.salario = salario;
	}

	/**
	 * @return the sSalario
	 */
	public String getsSalario() {
		return Utils.formatMoney(this.salario);
	}

	/**
	 * @param sSalario the sSalario to set
	 */
	public void setsSalario(String sSalario) {
		this.sSalario = sSalario;
	}

	/**
	 * @return the gradoEstudio
	 */
	public String getGradoEstudio() {
		return gradoEstudio;
	}

	/**
	 * @param gradoEstudio the gradoEstudio to set
	 */
	public void setGradoEstudio(String gradoEstudio) {
		this.gradoEstudio = gradoEstudio;
	}

	/**
	 * @return the carrera
	 */
	public String getCarrera() {
		return carrera;
	}

	/**
	 * @param carrera the carrera to set
	 */
	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	/**
	 * @return the funciones
	 */
	public String getFunciones() {
		return funciones;
	}

	/**
	 * @param funciones the funciones to set
	 */
	public void setFunciones(String funciones) {
		this.funciones = funciones;
	}

	/**
	 * @return the edad
	 */
	public String getEdad() {
		return edad;
	}

	/**
	 * @param edad the edad to set
	 */
	public void setEdad(String edad) {
		this.edad = edad;
	}

	/**
	 * @return the idiomas
	 */
	public String getIdiomas() {
		return idiomas;
	}

	/**
	 * @param idiomas the idiomas to set
	 */
	public void setIdiomas(String idiomas) {
		this.idiomas = idiomas;
	}

	/**
	 * @return the horario
	 */
	public String getHorario() {
		return horario;
	}

	/**
	 * @param horario the horario to set
	 */
	public void setHorario(String horario) {
		this.horario = horario;
	}

	/**
	 * @return the numeroPlazas
	 */
	public int getNumeroPlazas() {
		return numeroPlazas;
	}

	/**
	 * @param numeroPlazas the numeroPlazas to set
	 */
	public void setNumeroPlazas(int numeroPlazas) {
		this.numeroPlazas = numeroPlazas;
	}

	/**
	 * @return the medioContacto
	 */
	public String getMedioContacto() {
		return medioContacto;
	}

	/**
	 * @param medioContacto the medioContacto to set
	 */
	public void setMedioContacto(String medioContacto) {
		this.medioContacto = medioContacto;
	}

	/**
	 * @return the bolsaTrabajo
	 */
	public String getBolsaTrabajo() {
		return bolsaTrabajo;
	}

	/**
	 * @param bolsaTrabajo the bolsaTrabajo to set
	 */
	public void setBolsaTrabajo(String bolsaTrabajo) {
		this.bolsaTrabajo = bolsaTrabajo;
	}
	
	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}	
	
	public String getFechaInicioString() {
		return fechaInicioString;
	}

	public void setFechaInicioString(String fechaInicioString) {
		this.fechaInicioString = fechaInicioString;
	}

	public String getFechaFinString() {
		return fechaFinString;
	}

	public void setFechaFinString(String fechaFinString) {
		this.fechaFinString = fechaFinString;
	}	
	
	public int getPostulados() {
		return postulados;
	}

	public void setPostulados(int postulados) {
		this.postulados = postulados;
	}

	public int getLimitePostulantes() {
		return limitePostulantes;
	}

	public void setLimitePostulantes(int limitePostulantes) {
		this.limitePostulantes = limitePostulantes;
	}

	public long getCompatibility() {
		return compatibility;
	}

	public void setCompatibility(long compatibility) {
		this.compatibility = compatibility;
	}
	
	/**
	 * @return the habilidadGeneral
	 */
	public String getHabilidadGeneral() {
		return habilidadGeneral;
	}

	/**
	 * @param habilidadGeneral the habilidadGeneral to set
	 */
	public void setHabilidadGeneral(String habilidadGeneral) {
		this.habilidadGeneral = habilidadGeneral;
	}

	/**
	 * @return the experiencia
	 */
	public String getExperiencia() {
		return experiencia;
	}

	/**
	 * @param experiencia the experiencia to set
	 */
	public void setExperiencia(String experiencia) {
		this.experiencia = experiencia;
	}

	/**
	 * @return the competencias
	 */
	public String getCompetencias() {
		return competencias;
	}

	/**
	 * @param competencias the competencias to set
	 */
	public void setCompetencias(String competencias) {
		this.competencias = competencias;
	}

	/**
	 * @return the bitacora
	 */
	public List<BitacoraVO> getBitacora() {
		return bitacora;
	}

	/**
	 * @param bitacora the bitacora to set
	 */
	public void setBitacora(List<BitacoraVO> bitacora) {
		this.bitacora = bitacora;
	}	

	public String toString() {
		return "[" + getIdOfertaEmpleo() + "]";
	}
	
	/*COMENTAR EN PRODUCCION???*/
	public long getEstatus() {
		return estatus;
	}

	public void setEstatus(long estatus) {
		this.estatus = estatus;
	}

	public long getIdRegistroPorValidar() {
		return idRegistroPorValidar;
	}

	public void setIdRegistroPorValidar(long idRegistroPorValidar) {
		this.idRegistroPorValidar = idRegistroPorValidar;
	}

	/**
	 * @return the ocupacion
	 */
	public String getOcupacion() {
		return ocupacion;
	}

	/**
	 * @param ocupacion the ocupacion to set
	 */
	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	/**
	 * @return the carreras
	 */
	public List<String> getCarreras() {
		return carreras;
	}

	/**
	 * @param carreras the carreras to set
	 */
	public void setCarreras(List<String> carreras) {
		this.carreras = carreras;
	}

	/**
	 * @return the conocimientos
	 */
	public String getConocimientos() {
		StringBuffer conocimientosList = new StringBuffer();
		if (null != conocimientos) {
			Iterator<String> it = conocimientos.iterator();
			while (it.hasNext()) {
				conocimientosList.append(it.next() + " ");
			}
		}
		return conocimientosList.toString().trim();
	}

	/**
	 * @param conocimientos the conocimientos to set
	 */
	public void setConocimientos(List<String> conocimientos) {
		this.conocimientos = conocimientos;
	}

	public String getMotivoRechazo() {
		return motivoRechazo;
	}

	public void setMotivoRechazo(String motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
	}

	public String getDetalleMotivoRechazo() {
		return detalleMotivoRechazo;
	}

	public void setDetalleMotivoRechazo(String detalleMotivoRechazo) {
		this.detalleMotivoRechazo = detalleMotivoRechazo;
	}

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	/**
	 * @return the idMunicipio
	 */
	public long getIdMunicipio() {
		return idMunicipio;
	}

	/**
	 * @param idMunicipio the idMunicipio to set
	 */
	public void setIdMunicipio(long idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public long getIdArea() {
		return idArea;
	}

	public void setIdArea(long idArea) {
		this.idArea = idArea;
	}

	public long getIdSubArea() {
		return idSubArea;
	}

	public void setIdSubArea(long idSubArea) {
		this.idSubArea = idSubArea;
	}

	public List<OfertaFuncionVO> getFuncionList() {
		return funcionList;
	}

	public void setFuncionList(List<OfertaFuncionVO> funcionList) {
		this.funcionList = funcionList;
	}
			
}
