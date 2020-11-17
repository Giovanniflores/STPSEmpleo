package mx.gob.stps.portal.core.oferta.vo;

import java.io.Serializable;
import java.util.Date;

public class OfertaEmpleoFraudulentaVO implements Serializable {
	private static final long serialVersionUID = 6971705494169189200L;

	protected long idOfertaEmpleo;

	protected long idEmpresa;

	protected String tituloOferta;

	protected long idAreaLaboral;

	protected long idOcupacion;

	protected String funciones;

	protected String diasLaborales;

	protected String horaEntrada;

	protected String horaSalida;

	protected int rolarTurno;

	protected String empresaOfrece;

	protected double salario;

	protected long idTipoContrato;

	protected long idJerarquia;

	protected int numeroPlazas;

	protected int limitePostulantes;

	protected long idDiscapacidad;

	protected long idCausaVacante;

	protected Date fechaInicio;

	protected Date fechaFin;

	protected int disponibilidadViajar;

	protected int disponibilidadRadicar;

	protected long idNivelEstudio;

	protected long idSituacionAcademica;

	protected String habilidadGeneral;

	protected int experienciaAnios;

	protected int edadRequisito;

	protected int edadMinima;

	protected int edadMaxima;

	protected int genero;

	protected String mapaUbicacion;

	protected String observaciones;

	protected long idTerceraEmpresa;

	protected long idContacto;

	protected long idHorarioDe;

	protected long idHorarioA;

	protected long idDuracionAproximada;

	protected String diasEntrevista;

	protected int fuente;

	protected int contactoTel;
	
	protected int contactoCorreo;
	
	protected Date fechaAlta;

	protected Date fechaModificacion;

	protected long idTipoEmpleo;
	
	protected int estatus;

	public long getIdOfertaEmpleo() {
		return idOfertaEmpleo;
	}

	public void setIdOfertaEmpleo(long idOfertaEmpleo) {
		this.idOfertaEmpleo = idOfertaEmpleo;
	}

	public long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getTituloOferta() {
		return tituloOferta;
	}

	public void setTituloOferta(String tituloOferta) {
		this.tituloOferta = tituloOferta;
	}

	public long getIdAreaLaboral() {
		return idAreaLaboral;
	}

	public void setIdAreaLaboral(long idAreaLaboral) {
		this.idAreaLaboral = idAreaLaboral;
	}

	public long getIdOcupacion() {
		return idOcupacion;
	}

	public void setIdOcupacion(long idOcupacion) {
		this.idOcupacion = idOcupacion;
	}

	public String getFunciones() {
		return funciones;
	}

	public void setFunciones(String funciones) {
		this.funciones = funciones;
	}

	public String getDiasLaborales() {
		return diasLaborales;
	}

	public void setDiasLaborales(String diasLaborales) {
		this.diasLaborales = diasLaborales;
	}

	public String getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(String horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public String getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}

	public int getRolarTurno() {
		return rolarTurno;
	}

	public void setRolarTurno(int rolarTurno) {
		this.rolarTurno = rolarTurno;
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

	public long getIdTipoContrato() {
		return idTipoContrato;
	}

	public void setIdTipoContrato(long idTipoContrato) {
		this.idTipoContrato = idTipoContrato;
	}

	public long getIdJerarquia() {
		return idJerarquia;
	}

	public void setIdJerarquia(long idJerarquia) {
		this.idJerarquia = idJerarquia;
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

	public long getIdDiscapacidad() {
		return idDiscapacidad;
	}

	public void setIdDiscapacidad(long idDiscapacidad) {
		this.idDiscapacidad = idDiscapacidad;
	}

	public long getIdCausaVacante() {
		return idCausaVacante;
	}

	public void setIdCausaVacante(long idCausaVacante) {
		this.idCausaVacante = idCausaVacante;
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

	public int getDisponibilidadViajar() {
		return disponibilidadViajar;
	}

	public void setDisponibilidadViajar(int disponibilidadViajar) {
		this.disponibilidadViajar = disponibilidadViajar;
	}

	public int getDisponibilidadRadicar() {
		return disponibilidadRadicar;
	}

	public void setDisponibilidadRadicar(int disponibilidadRadicar) {
		this.disponibilidadRadicar = disponibilidadRadicar;
	}

	public long getIdNivelEstudio() {
		return idNivelEstudio;
	}

	public void setIdNivelEstudio(long idNivelEstudio) {
		this.idNivelEstudio = idNivelEstudio;
	}

	public long getIdSituacionAcademica() {
		return idSituacionAcademica;
	}

	public void setIdSituacionAcademica(long idSituacionAcademica) {
		this.idSituacionAcademica = idSituacionAcademica;
	}

	public String getHabilidadGeneral() {
		return habilidadGeneral;
	}

	public void setHabilidadGeneral(String habilidadGeneral) {
		this.habilidadGeneral = habilidadGeneral;
	}

	public int getExperienciaAnios() {
		return experienciaAnios;
	}

	public void setExperienciaAnios(int experienciaAnios) {
		this.experienciaAnios = experienciaAnios;
	}

	public int getEdadRequisito() {
		return edadRequisito;
	}

	public void setEdadRequisito(int edadRequisito) {
		this.edadRequisito = edadRequisito;
	}

	public int getEdadMinima() {
		return edadMinima;
	}

	public void setEdadMinima(int edadMinima) {
		this.edadMinima = edadMinima;
	}

	public int getEdadMaxima() {
		return edadMaxima;
	}

	public void setEdadMaxima(int edadMaxima) {
		this.edadMaxima = edadMaxima;
	}

	public int getGenero() {
		return genero;
	}

	public void setGenero(int genero) {
		this.genero = genero;
	}

	public String getMapaUbicacion() {
		return mapaUbicacion;
	}

	public void setMapaUbicacion(String mapaUbicacion) {
		this.mapaUbicacion = mapaUbicacion;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public long getIdTerceraEmpresa() {
		return idTerceraEmpresa;
	}

	public void setIdTerceraEmpresa(long idTerceraEmpresa) {
		this.idTerceraEmpresa = idTerceraEmpresa;
	}

	public long getIdContacto() {
		return idContacto;
	}

	public void setIdContacto(long idContacto) {
		this.idContacto = idContacto;
	}

	public long getIdHorarioDe() {
		return idHorarioDe;
	}

	public void setIdHorarioDe(long idHorarioDe) {
		this.idHorarioDe = idHorarioDe;
	}

	public long getIdHorarioA() {
		return idHorarioA;
	}

	public void setIdHorarioA(long idHorarioA) {
		this.idHorarioA = idHorarioA;
	}

	public long getIdDuracionAproximada() {
		return idDuracionAproximada;
	}

	public void setIdDuracionAproximada(long idDuracionAproximada) {
		this.idDuracionAproximada = idDuracionAproximada;
	}

	public String getDiasEntrevista() {
		return diasEntrevista;
	}

	public void setDiasEntrevista(String diasEntrevista) {
		this.diasEntrevista = diasEntrevista;
	}

	public int getFuente() {
		return fuente;
	}

	public void setFuente(int fuente) {
		this.fuente = fuente;
	}

	public int getContactoTel() {
		return contactoTel;
	}

	public void setContactoTel(int contactoTel) {
		this.contactoTel = contactoTel;
	}

	public int getContactoCorreo() {
		return contactoCorreo;
	}

	public void setContactoCorreo(int contactoCorreo) {
		this.contactoCorreo = contactoCorreo;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public long getIdTipoEmpleo() {
		return idTipoEmpleo;
	}

	public void setIdTipoEmpleo(long idTipoEmpleo) {
		this.idTipoEmpleo = idTipoEmpleo;
	}

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}	
}
