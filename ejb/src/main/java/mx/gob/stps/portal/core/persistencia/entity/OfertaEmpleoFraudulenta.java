package mx.gob.stps.portal.core.persistencia.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "OFERTA_EMPLEO_FRAUDULENTA")
public class OfertaEmpleoFraudulenta implements Serializable {
	private static final long serialVersionUID = -357803739652816502L;

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
	
	 
	
	/**
	 * Method 'getEstatus'
	 * 
	 * @return int
	 */
	@Column(name = "estatus")
	public int getEstatus() {
		return estatus;
	}

	/**
	 * Method 'setEstatus'
	 * 
	 * @param estatus
	 */
	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}
	
	/**
	 * Method 'getIdOfertaEmpleo'
	 * 
	 * @return int
	 */
	@Id
	@Column(name = "id_oferta_empleo")
	public long getIdOfertaEmpleo() {
		return idOfertaEmpleo;
	}

	/**
	 * Method 'setIdOfertaEmpleo'
	 * 
	 * @param idOfertaEmpleo
	 */
	public void setIdOfertaEmpleo(long idOfertaEmpleo) {
		this.idOfertaEmpleo = idOfertaEmpleo;
	}

	/**
	 * Method 'getIdEmpresa'
	 * 
	 * @return int
	 */
	@Column(name = "id_empresa")
	public long getIdEmpresa() {
		return idEmpresa;
	}

	/**
	 * Method 'setIdEmpresa'
	 * 
	 * @param idEmpresa
	 */
	public void setIdEmpresa(long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	/**
	 * Method 'getTituloOferta'
	 * 
	 * @return String
	 */
	@Column(name = "titulo_oferta")
	public String getTituloOferta() {
		return tituloOferta;
	}

	/**
	 * Method 'setTituloOferta'
	 * 
	 * @param tituloOferta
	 */
	public void setTituloOferta(String tituloOferta) {
		this.tituloOferta = tituloOferta;
	}

	/**
	 * Method 'getIdAreaLaboral'
	 * 
	 * @return int
	 */
	@Column(name = "id_area_laboral")
	public long getIdAreaLaboral() {
		return idAreaLaboral;
	}

	/**
	 * Method 'setIdAreaLaboral'
	 * 
	 * @param idAreaLaboral
	 */
	public void setIdAreaLaboral(long idAreaLaboral) {
		this.idAreaLaboral = idAreaLaboral;
	}

	/**
	 * Method 'getIdOcupacion'
	 * 
	 * @return int
	 */
	@Column(name = "id_ocupacion")
	public long getIdOcupacion() {
		return idOcupacion;
	}

	/**
	 * Method 'setIdOcupacion'
	 * 
	 * @param idOcupacion
	 */
	public void setIdOcupacion(long idOcupacion) {
		this.idOcupacion = idOcupacion;
	}

	/**
	 * Method 'getFunciones'
	 * 
	 * @return String
	 */
	@Column(name = "funciones")
	public String getFunciones() {
		return funciones;
	}

	/**
	 * Method 'setFunciones'
	 * 
	 * @param funciones
	 */
	public void setFunciones(String funciones) {
		this.funciones = funciones;
	}

	/**
	 * Method 'getDiasLaborales'
	 * 
	 * @return String
	 */
	@Column(name = "dias_laborales")
	public String getDiasLaborales() {
		return diasLaborales;
	}

	/**
	 * Method 'setDiasLaborales'
	 * 
	 * @param diasLaborales
	 */
	public void setDiasLaborales(String diasLaborales) {
		this.diasLaborales = diasLaborales;
	}

	/**
	 * Method 'getHoraEntrada'
	 * 
	 * @return String
	 */
	@Column(name = "hora_entrada")
	public String getHoraEntrada() {
		return horaEntrada;
	}

	/**
	 * Method 'setHoraEntrada'
	 * 
	 * @param horaEntrada
	 */
	public void setHoraEntrada(String horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	/**
	 * Method 'getHoraSalida'
	 * 
	 * @return String
	 */
	@Column(name = "hora_salida")
	public String getHoraSalida() {
		return horaSalida;
	}

	/**
	 * Method 'setHoraSalida'
	 * 
	 * @param horaSalida
	 */
	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}

	/**
	 * Method 'getRolarTurno'
	 * 
	 * @return int
	 */
	@Column(name = "rolar_turno")
	public int getRolarTurno() {
		return rolarTurno;
	}

	/**
	 * Method 'setRolarTurno'
	 * 
	 * @param rolarTurno
	 */
	public void setRolarTurno(int rolarTurno) {
		this.rolarTurno = rolarTurno;
	}

	/**
	 * Method 'getEmpresaOfrece'
	 * 
	 * @return String
	 */
	@Column(name = "empresa_ofrece")
	public String getEmpresaOfrece() {
		return empresaOfrece;
	}

	/**
	 * Method 'setEmpresaOfrece'
	 * 
	 * @param empresaOfrece
	 */
	public void setEmpresaOfrece(String empresaOfrece) {
		this.empresaOfrece = empresaOfrece;
	}

	/**
	 * Method 'getSalario'
	 * 
	 * @return double
	 */
	@Column(name = "salario")
	public double getSalario() {
		return salario;
	}

	/**
	 * Method 'setSalario'
	 * 
	 * @param salario
	 */
	public void setSalario(double salario) {
		this.salario = salario;
	}

	/**
	 * Method 'getIdTipoContrato'
	 * 
	 * @return int
	 */
	@Column(name = "id_tipo_contrato")
	public long getIdTipoContrato() {
		return idTipoContrato;
	}

	/**
	 * Method 'setIdTipoContrato'
	 * 
	 * @param idTipoContrato
	 */
	public void setIdTipoContrato(long idTipoContrato) {
		this.idTipoContrato = idTipoContrato;
	}

	/**
	 * Method 'getIdJerarquia'
	 * 
	 * @return int
	 */
	@Column(name = "id_jerarquia")
	public long getIdJerarquia() {
		return idJerarquia;
	}

	/**
	 * Method 'setIdJerarquia'
	 * 
	 * @param idJerarquia
	 */
	public void setIdJerarquia(long idJerarquia) {
		this.idJerarquia = idJerarquia;
	}

	/**
	 * Method 'getNumeroPlazas'
	 * 
	 * @return int
	 */
	@Column(name = "numero_plazas")
	public int getNumeroPlazas() {
		return numeroPlazas;
	}

	/**
	 * Method 'setNumeroPlazas'
	 * 
	 * @param numeroPlazas
	 */
	public void setNumeroPlazas(int numeroPlazas) {
		this.numeroPlazas = numeroPlazas;
	}

	/**
	 * Method 'getLimitePostulantes'
	 * 
	 * @return int
	 */
	@Column(name = "limite_postulantes")
	public int getLimitePostulantes() {
		return limitePostulantes;
	}

	/**
	 * Method 'setLimitePostulantes'
	 * 
	 * @param limitePostulantes
	 */
	public void setLimitePostulantes(int limitePostulantes) {
		this.limitePostulantes = limitePostulantes;
	}

	/**
	 * Method 'getIdDiscapacidad'
	 * 
	 * @return int
	 */
	@Column(name = "id_discapacidad")
	public long getIdDiscapacidad() {
		return idDiscapacidad;
	}

	/**
	 * Method 'setIdDiscapacidad'
	 * 
	 * @param idDiscapacidad
	 */
	public void setIdDiscapacidad(long idDiscapacidad) {
		this.idDiscapacidad = idDiscapacidad;
	}

	/**
	 * Method 'getIdCausaVacante'
	 * 
	 * @return int
	 */
	@Column(name = "id_causa_vacante")
	public long getIdCausaVacante() {
		return idCausaVacante;
	}

	/**
	 * Method 'setIdCausaVacante'
	 * 
	 * @param idCausaVacante
	 */
	public void setIdCausaVacante(long idCausaVacante) {
		this.idCausaVacante = idCausaVacante;
	}

	/**
	 * Method 'getFechaInicio'
	 * 
	 * @return Date
	 */
	@Column(name = "fecha_inicio")
	@Temporal(TemporalType.DATE)
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * Method 'setFechaInicio'
	 * 
	 * @param fechaInicio
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * Method 'getFechaFin'
	 * 
	 * @return Date
	 */
	@Column(name = "fecha_fin")
	@Temporal(TemporalType.DATE)
	public Date getFechaFin() {
		return fechaFin;
	}

	/**
	 * Method 'setFechaFin'
	 * 
	 * @param fechaFin
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * Method 'getDisponibilidadViajar'
	 * 
	 * @return int
	 */
	@Column(name = "disponibilidad_viajar")
	public int getDisponibilidadViajar() {
		return disponibilidadViajar;
	}

	/**
	 * Method 'setDisponibilidadViajar'
	 * 
	 * @param disponibilidadViajar
	 */
	public void setDisponibilidadViajar(int disponibilidadViajar) {
		this.disponibilidadViajar = disponibilidadViajar;
	}

	/**
	 * Method 'getDisponibilidadRadicar'
	 * 
	 * @return int
	 */
	@Column(name = "disponibilidad_radicar")
	public int getDisponibilidadRadicar() {
		return disponibilidadRadicar;
	}

	/**
	 * Method 'setDisponibilidadRadicar'
	 * 
	 * @param disponibilidadRadicar
	 */
	public void setDisponibilidadRadicar(int disponibilidadRadicar) {
		this.disponibilidadRadicar = disponibilidadRadicar;
	}

	/**
	 * Method 'getIdNivelEstudio'
	 * 
	 * @return int
	 */
	@Column(name = "id_nivel_estudio")
	public long getIdNivelEstudio() {
		return idNivelEstudio;
	}

	/**
	 * Method 'setIdNivelEstudio'
	 * 
	 * @param idNivelEstudio
	 */
	public void setIdNivelEstudio(long idNivelEstudio) {
		this.idNivelEstudio = idNivelEstudio;
	}

	/**
	 * Method 'getIdSituacionAcademica'
	 * 
	 * @return int
	 */
	@Column(name = "id_situacion_academica")
	public long getIdSituacionAcademica() {
		return idSituacionAcademica;
	}

	/**
	 * Method 'setIdSituacionAcademica'
	 * 
	 * @param idSituacionAcademica
	 */
	public void setIdSituacionAcademica(long idSituacionAcademica) {
		this.idSituacionAcademica = idSituacionAcademica;
	}

	/**
	 * Method 'getHabilidadGeneral'
	 * 
	 * @return String
	 */
	@Column(name = "habilidad_general")
	public String getHabilidadGeneral() {
		return habilidadGeneral;
	}

	/**
	 * Method 'setHabilidadGeneral'
	 * 
	 * @param habilidadGeneral
	 */
	public void setHabilidadGeneral(String habilidadGeneral) {
		this.habilidadGeneral = habilidadGeneral;
	}

	/**
	 * Method 'getExperienciaAnios'
	 * 
	 * @return int
	 */
	@Column(name = "experiencia_anios")
	public int getExperienciaAnios() {
		return experienciaAnios;
	}

	/**
	 * Method 'setExperienciaAnios'
	 * 
	 * @param experienciaAnios
	 */
	public void setExperienciaAnios(int experienciaAnios) {
		this.experienciaAnios = experienciaAnios;
	}

	/**
	 * Method 'getEdadRequisito'
	 * 
	 * @return int
	 */
	@Column(name = "edad_requisito")
	public int getEdadRequisito() {
		return edadRequisito;
	}

	/**
	 * Method 'setEdadRequisito'
	 * 
	 * @param edadRequisito
	 */
	public void setEdadRequisito(int edadRequisito) {
		this.edadRequisito = edadRequisito;
	}

	/**
	 * Method 'getEdadMinima'
	 * 
	 * @return int
	 */
	@Column(name = "edad_minima")
	public int getEdadMinima() {
		return edadMinima;
	}

	/**
	 * Method 'setEdadMinima'
	 * 
	 * @param edadMinima
	 */
	public void setEdadMinima(int edadMinima) {
		this.edadMinima = edadMinima;
	}

	/**
	 * Method 'getEdadMaxima'
	 * 
	 * @return int
	 */
	@Column(name = "edad_maxima")
	public int getEdadMaxima() {
		return edadMaxima;
	}

	/**
	 * Method 'setEdadMaxima'
	 * 
	 * @param edadMaxima
	 */
	public void setEdadMaxima(int edadMaxima) {
		this.edadMaxima = edadMaxima;
	}

	/**
	 * Method 'getGenero'
	 * 
	 * @return int
	 */
	@Column(name = "genero")
	public int getGenero() {
		return genero;
	}

	/**
	 * Method 'setGenero'
	 * 
	 * @param genero
	 */
	public void setGenero(int genero) {
		this.genero = genero;
	}

	/**
	 * Method 'getMapaUbicacion'
	 * 
	 * @return String
	 */
	@Column(name = "mapa_ubicacion")
	public String getMapaUbicacion() {
		return mapaUbicacion;
	}

	/**
	 * Method 'setMapaUbicacion'
	 * 
	 * @param mapaUbicacion
	 */
	public void setMapaUbicacion(String mapaUbicacion) {
		this.mapaUbicacion = mapaUbicacion;
	}

	/**
	 * Method 'getObservaciones'
	 * 
	 * @return String
	 */
	@Column(name = "observaciones")
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * Method 'setObservaciones'
	 * 
	 * @param observaciones
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * Method 'getIdTerceraEmpresa'
	 * 
	 * @return int
	 */
	@Column(name = "id_tercera_empresa")
	public long getIdTerceraEmpresa() {
		return idTerceraEmpresa;
	}

	/**
	 * Method 'setIdTerceraEmpresa'
	 * 
	 * @param idTerceraEmpresa
	 */
	public void setIdTerceraEmpresa(long idTerceraEmpresa) {
		this.idTerceraEmpresa = idTerceraEmpresa;
	}

	/**
	 * Method 'getIdContacto'
	 * 
	 * @return int
	 */
	@Column(name = "id_contacto")
	public long getIdContacto() {
		return idContacto;
	}

	/**
	 * Method 'setIdContacto'
	 * 
	 * @param idContacto
	 */
	public void setIdContacto(long idContacto) {
		this.idContacto = idContacto;
	}


	/**
	 * Method 'getIdHorarioDe'
	 * 
	 * @return int
	 */
	@Column(name = "id_horario_de")
	public long getIdHorarioDe() {
		return idHorarioDe;
	}

	/**
	 * Method 'setIdHorarioDe'
	 * 
	 * @param idHorarioDe
	 */
	public void setIdHorarioDe(long idHorarioDe) {
		this.idHorarioDe = idHorarioDe;
	}

	/**
	 * Method 'getIdHorarioA'
	 * 
	 * @return int
	 */
	@Column(name = "id_horario_a")
	public long getIdHorarioA() {
		return idHorarioA;
	}

	/**
	 * Method 'setIdHorarioA'
	 * 
	 * @param idHorarioA
	 */
	public void setIdHorarioA(long idHorarioA) {
		this.idHorarioA = idHorarioA;
	}

	/**
	 * Method 'getIdDuracionAproximada'
	 * 
	 * @return int
	 */
	@Column(name = "id_duracion_aproximada")
	public long getIdDuracionAproximada() {
		return idDuracionAproximada;
	}

	/**
	 * Method 'setIdDuracionAproximada'
	 * 
	 * @param idDuracionAproximada
	 */
	public void setIdDuracionAproximada(long idDuracionAproximada) {
		this.idDuracionAproximada = idDuracionAproximada;
	}

	/**
	 * Method 'getDiasEntrevista'
	 * 
	 * @return String
	 */
	@Column(name = "dias_entrevista")
	public String getDiasEntrevista() {
		return diasEntrevista;
	}

	/**
	 * Method 'setDiasEntrevista'
	 * 
	 * @param diasEntrevista
	 */
	public void setDiasEntrevista(String diasEntrevista) {
		this.diasEntrevista = diasEntrevista;
	}

	/**
	 * Method 'getFuente'
	 * 
	 * @return int
	 */
	@Column(name = "fuente")
	public int getFuente() {
		return fuente;
	}

	/**
	 * Method 'setFuente'
	 * 
	 * @param fuente
	 */
	public void setFuente(int fuente) {
		this.fuente = fuente;
	}

	/**
	 * Method 'getFechaAlta'
	 * 
	 * @return Date
	 */
	@Column(name = "fecha_alta")
	@Temporal(TemporalType.DATE)
	public Date getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * Method 'setfechaModificacion'
	 * 
	 * @param fechaModificacion
	 */
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	
	
	/**
	 * Method 'getfechaModificacion'
	 * 
	 * @return Date
	 */
	@Column(name = "fecha_modificacion")
	@Temporal(TemporalType.DATE)
	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	/**
	 * Method 'setFechaAlta'
	 * 
	 * @param fechaAlta
	 */
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	

	@Column(name = "contacto_tel")
	public int getContactoTel() {
		return contactoTel;
	}

	public void setContactoTel(int contactoTel) {
		this.contactoTel = contactoTel;
	}

	@Column(name = "contacto_correo")
	public int getContactoCorreo() {
		return contactoCorreo;
	}

	public void setContactoCorreo(int contactoCorreo) {
		this.contactoCorreo = contactoCorreo;
	}

	@Column(name = "ID_TIPO_EMPLEO")
	public long getIdTipoEmpleo() {
		return idTipoEmpleo;
	}

	public void setIdTipoEmpleo(long idTipoEmpleo) {
		this.idTipoEmpleo = idTipoEmpleo;
	}

}
