package mx.gob.stps.portal.core.oferta.registro.vo;

import java.io.Serializable;
import java.util.Date;


/**
 * @author dorian.gonzalez
 *
 */
public class RegistroUbicacionVO implements Serializable {
	private static final long serialVersionUID = 6880373923143856502L;
	private long idOferta;
	private String tituloOferta;
	private long area;
	private long ocupacion;
	private long sector;
	private String sectores;
	private String funciones;
	private long horario;
	private String lunes;
	private String martes;
	private String miercoles;
	private String jueves;
	private String viernes;
	private String sabado;
	private String domingo;
	private String HEntrada;
	private String HSalida;
	private int rolarTurnos;
	private String empresaOfrece;
	private double salario;
	private long tipoContacto;
	private String[] prestaciones;   //duda
	private long jerarquia;
	private int numeroPlazas ;
	private int limitePostulantes;
	private Date vigenciaInicio;
	private Date vigenciaFin ;
	private long dicapacidad;
	private long origenOferta;
	private String mapaUbicacion ;
	private String prefiereCandidatosEntidad;
	private String prefiereCandidatosMunicipio;
	private long idEmpresa; 
	private String guardar;
	private String cancelar;
	private long tipoContrato;
	
	
	
	private long idDomicilio;
	private long idTipoPropietario;
	private long idEntidad;
	private long idMunicipio;
	private long idColonia;
	private String calle;
	private String numeroInterior;
	private String numeroExterior;
	private String entreCalle;
	private String yCalle;
	private String codigoPostal;
	private Date fechaAlta;
	private String entidad;
	private String municipio;
	
	
	public long getIdDomicilio() {
		return idDomicilio;
	}
	public void setIdDomicilio(long idDomicilio) {
		this.idDomicilio = idDomicilio;
	}
	public long getIdTipoPropietario() {
		return idTipoPropietario;
	}
	public void setIdTipoPropietario(long idTipoPropietario) {
		this.idTipoPropietario = idTipoPropietario;
	}
	public long getIdEntidad() {
		return idEntidad;
	}
	public void setIdEntidad(long idEntidad) {
		this.idEntidad = idEntidad;
	}
	public long getIdMunicipio() {
		return idMunicipio;
	}
	public void setIdMunicipio(long idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
	public long getIdColonia() {
		return idColonia;
	}
	public void setIdColonia(long idColonia) {
		this.idColonia = idColonia;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getNumeroInterior() {
		return numeroInterior;
	}
	public void setNumeroInterior(String numeroInterior) {
		this.numeroInterior = numeroInterior;
	}
	public String getNumeroExterior() {
		return numeroExterior;
	}
	public void setNumeroExterior(String numeroExterior) {
		this.numeroExterior = numeroExterior;
	}
	public String getEntreCalle() {
		return entreCalle;
	}
	public void setEntreCalle(String entreCalle) {
		this.entreCalle = entreCalle;
	}
	public String getyCalle() {
		return yCalle;
	}
	public void setyCalle(String yCalle) {
		this.yCalle = yCalle;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public long getIdOferta() {
		return idOferta;
	}
	public void setIdOferta(long idOferta) {
		this.idOferta = idOferta;
	}
	public String getTituloOferta() {
		return tituloOferta;
	}
	public void setTituloOferta(String tituloOferta) {
		this.tituloOferta = tituloOferta;
	}
	public long getArea() {
		return area;
	}
	public void setArea(long area) {
		this.area = area;
	}
	public long getOcupacion() {
		return ocupacion;
	}
	public void setOcupacion(long ocupacion) {
		this.ocupacion = ocupacion;
	}
	public long getSector() {
		return sector;
	}
	public void setSector(long sector) {
		this.sector = sector;
	}
	public String getSectores() {
		return sectores;
	}
	public void setSectores(String sectores) {
		this.sectores = sectores;
	}
	public String getFunciones() {
		return funciones;
	}
	public void setFunciones(String funciones) {
		this.funciones = funciones;
	}
	public long getHorario() {
		return horario;
	}
	public void setHorario(long horario) {
		this.horario = horario;
	}
	public String getLunes() {
		return lunes;
	}
	public void setLunes(String lunes) {
		this.lunes = lunes;
	}
	public String getMartes() {
		return martes;
	}
	public void setMartes(String martes) {
		this.martes = martes;
	}
	public String getMiercoles() {
		return miercoles;
	}
	public void setMiercoles(String miercoles) {
		this.miercoles = miercoles;
	}
	public String getJueves() {
		return jueves;
	}
	public void setJueves(String jueves) {
		this.jueves = jueves;
	}
	public String getViernes() {
		return viernes;
	}
	public void setViernes(String viernes) {
		this.viernes = viernes;
	}
	public String getSabado() {
		return sabado;
	}
	public void setSabado(String sabado) {
		this.sabado = sabado;
	}
	public String getDomingo() {
		return domingo;
	}
	public void setDomingo(String domingo) {
		this.domingo = domingo;
	}
	public String getHEntrada() {
		return HEntrada;
	}
	public void setHEntrada(String hEntrada) {
		HEntrada = hEntrada;
	}
	public String getHSalida() {
		return HSalida;
	}
	public void setHSalida(String hSalida) {
		HSalida = hSalida;
	}
	public int getRolarTurnos() {
		return rolarTurnos;
	}
	public void setRolarTurnos(int rolarTurnos) {
		this.rolarTurnos = rolarTurnos;
	}
	public String getEmpresaOfrece() {
		return empresaOfrece;
	}
	public void setEmpresaOfrece(String empresaOfrece) {
		this.empresaOfrece = empresaOfrece;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public long getTipoContacto() {
		return tipoContacto;
	}
	public void setTipoContacto(long tipoContacto) {
		this.tipoContacto = tipoContacto;
	}
	public String[] getPrestaciones() {
		return prestaciones;
	}
	public void setPrestaciones(String[] prestaciones) {
		this.prestaciones = prestaciones;
	}
	public long getJerarquia() {
		return jerarquia;
	}
	public void setJerarquia(long jerarquia) {
		this.jerarquia = jerarquia;
	}
	public int getNumeroPlazas() {
		return numeroPlazas;
	}
	public void setNumeroPlazas(int numeroPlazas) {
		this.numeroPlazas = numeroPlazas;
	}
	public int getLimitePostulantes() {
		return limitePostulantes;
	}
	public void setLimitePostulantes(int limitePostulantes) {
		this.limitePostulantes = limitePostulantes;
	}
	public Date getVigenciaInicio() {
		return vigenciaInicio;
	}
	public void setVigenciaInicio(Date vigenciaInicio) {
		this.vigenciaInicio = vigenciaInicio;
	}
	public Date getVigenciaFin() {
		return vigenciaFin;
	}
	public void setVigenciaFin(Date vigenciaFin) {
		this.vigenciaFin = vigenciaFin;
	}
	public long getDicapacidad() {
		return dicapacidad;
	}
	public void setDicapacidad(long dicapacidad) {
		this.dicapacidad = dicapacidad;
	}
	public long getOrigenOferta() {
		return origenOferta;
	}
	public void setOrigenOferta(long origenOferta) {
		this.origenOferta = origenOferta;
	}
	public String getMapaUbicacion() {
		return mapaUbicacion;
	}
	public void setMapaUbicacion(String mapaUbicacion) {
		this.mapaUbicacion = mapaUbicacion;
	}
	public String getPrefiereCandidatosEntidad() {
		return prefiereCandidatosEntidad;
	}
	public void setPrefiereCandidatosEntidad(String prefiereCandidatosEntidad) {
		this.prefiereCandidatosEntidad = prefiereCandidatosEntidad;
	}
	public String getPrefiereCandidatosMunicipio() {
		return prefiereCandidatosMunicipio;
	}
	public void setPrefiereCandidatosMunicipio(String prefiereCandidatosMunicipio) {
		this.prefiereCandidatosMunicipio = prefiereCandidatosMunicipio;
	}
	public long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getGuardar() {
		return guardar;
	}
	public void setGuardar(String guardar) {
		this.guardar = guardar;
	}
	public String getCancelar() {
		return cancelar;
	}
	public void setCancelar(String cancelar) {
		this.cancelar = cancelar;
	}
	public long getTipoContrato() {
		return tipoContrato;
	}
	public void setTipoContrato(long tipoContrato) {
		this.tipoContrato = tipoContrato;
	}
	/**
	 * @return the entidad
	 */
	public String getEntidad() {
		return entidad;
	}
	/**
	 * @param entidad the entidad to set
	 */
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}
	/**
	 * @return the municipio
	 */
	public String getMunicipio() {
		return municipio;
	}
	/**
	 * @param municipio the municipio to set
	 */
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	
	
	
	
	
		
}
