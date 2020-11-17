package mx.gob.stps.portal.core.oferta.vo;

import java.io.Serializable;


/**
 * @author dorian.gonzalez
 *
 */
public class RegistroUbicacionVO implements Serializable {
	private static final long serialVersionUID = 6880373923143856502L;
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
	private String vigenciainicio;
	private String vigenciaFin ;
	private long dicapacidad;
	private long origenOferta;
	private String mapaUbicacion ;
	private String prefiereCandidatosEntidad;
	private String prefiereCandidatosMunicipio;
	private long idEmpresa; 
	private String guardar;
	private String cancelar;
	private long tipoContrato;
	
	
	
	public long getTipoContrato() {
		return tipoContrato;
	}
	public void setTipoContrato(long tipoContrato) {
		this.tipoContrato = tipoContrato;
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
	public void setPrestaciones(String []prestaciones) {
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
	public String getVigenciainicio() {
		return vigenciainicio;
	}
	public void setVigenciainicio(String vigenciainicio) {
		this.vigenciainicio = vigenciainicio;
	}
	public String getVigenciaFin() {
		return vigenciaFin;
	}
	public void setVigenciaFin(String vigenciaFin) {
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
		
}
