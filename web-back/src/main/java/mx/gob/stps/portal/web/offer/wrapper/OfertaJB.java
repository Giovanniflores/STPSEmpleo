package mx.gob.stps.portal.web.offer.wrapper;

import java.io.Serializable;
import java.util.List;

import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import oracle.spatial.geometry.JGeometry;

public class OfertaJB implements Serializable {

	private static final long serialVersionUID = -1561559296320455801L;

	private String idOfertaEmpleo;

	private String idEmpresa;

	private String tituloOferta;
	
	private String idAreaLaboral;

	private String idOcupacion;

	private String funciones;

	private String diasLaborales;

	private String horaEntrada;

	private String horaSalida;

	private String rolarTurno;

	private String empresaOfrece;

	private String salario;

	private String idTipoContrato;

	private String idJerarquia;

	private String numeroPlazas;

	private String limitePostulantes;

	private String idDiscapacidad;

	private String idCausaVacante;

	private String fechaInicio;

	private String fechaFin;

	private String disponibilidadViajar;

	private String disponibilidadRadicar;

	private String idNivelEstudio;

	private String idSituacionAcademica;

	private String habilidadGeneral;
	
	private List<CatalogoOpcionVO> habilidades;
	
	private String experienciaAnios;

	private int edadRequisito;

	private String edadMinima;

	private String edadMaxima;

	private String genero;

	private String mapaUbicacion;
	
	private JGeometry geoReferencia;

	private String observaciones;

	private long idTerceraEmpresa;

	private String idContacto;

	private String idHorarioDe;

	private String idHorarioA;

	private String idDuracionAproximada;

	private String diasEntrevista;

	private String fuente;

	private String contactoTel;
	
	private String contactoCorreo;
	
	private String fechaAlta;

	private String fechaModificacion;

	private String idTipoEmpleo;
	
	private String estatus;
	
	private String empresaNombre;
	
	private String gradoEstudios;
	
	private String situacionAcademica;
	
	private String tipoEmpleo;
	
	private String tipoContrato;
	
	private String ubicacion;
	
	private String estatusOfertaCandidato;
	
	private String idiomas;
	
	private String medioContacto;
	
	private String requisitos;
	
	private String idiomasCert;
	
	private String areaLaboral;
	
	private String ocupacion;
	
	private String sector;
	
	private String horario;
	
	private String prestaciones;
	
	private String jerarquia;

	private String horarioLaboral;
	
	private String tipoDiscapacidad;
	
	private String causa;
	
	private String idOfertaCandidato;
	
	private String especialidades;
	
	private String causaOriginaOferta;
	
	private int fuenteId;
	
	private int IdContactoCorreo;
	
	private int IdContactoDomicilio;
	
	private int IdContactoTel;	
	
	private List<TelefonoVO> telefonos;
	
	private String descripcionesDiscapacidades;
	
	private String conocimientoComputacionDesc;
	
	private String cargoContacto;
	
	private boolean active;

	public String getCausaOriginaOferta() {
		return causaOriginaOferta;
	}

	public void setCausaOriginaOferta(String causaOriginaOferta) {
		this.causaOriginaOferta = causaOriginaOferta;
	}

	public String getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(String especialidades) {
		this.especialidades = especialidades;
	}

	public String getIdOfertaEmpleo() {
		return idOfertaEmpleo;
	}

	public void setIdOfertaEmpleo(String idOfertaEmpleo) {
		this.idOfertaEmpleo = idOfertaEmpleo;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getTituloOferta() {
		return tituloOferta;
	}

	public void setTituloOferta(String tituloOferta) {
		this.tituloOferta = tituloOferta;
	}

	public String getIdAreaLaboral() {
		return idAreaLaboral;
	}

	public void setIdAreaLaboral(String idAreaLaboral) {
		this.idAreaLaboral = idAreaLaboral;
	}

	public String getIdOcupacion() {
		return idOcupacion;
	}

	public void setIdOcupacion(String idOcupacion) {
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

	public String getRolarTurno() {
		return rolarTurno;
	}

	public void setRolarTurno(String rolarTurno) {
		this.rolarTurno = rolarTurno;
	}

	public String getEmpresaOfrece() {
		return empresaOfrece;
	}

	public void setEmpresaOfrece(String empresaOfrece) {
		this.empresaOfrece = empresaOfrece;
	}

	public String getSalario() {
		return salario;
	}

	public void setSalario(String salario) {
		this.salario = salario;
	}

	public String getIdTipoContrato() {
		return idTipoContrato;
	}

	public void setIdTipoContrato(String idTipoContrato) {
		this.idTipoContrato = idTipoContrato;
	}

	public String getIdJerarquia() {
		return idJerarquia;
	}

	public void setIdJerarquia(String idJerarquia) {
		this.idJerarquia = idJerarquia;
	}

	public String getNumeroPlazas() {
		return numeroPlazas;
	}

	public void setNumeroPlazas(String numeroPlazas) {
		this.numeroPlazas = numeroPlazas;
	}

	public String getLimitePostulantes() {
		return limitePostulantes;
	}

	public void setLimitePostulantes(String limitePostulantes) {
		this.limitePostulantes = limitePostulantes;
	}

	public String getIdDiscapacidad() {
		return idDiscapacidad;
	}

	public void setIdDiscapacidad(String idDiscapacidad) {
		this.idDiscapacidad = idDiscapacidad;
	}

	public String getIdCausaVacante() {
		return idCausaVacante;
	}

	public void setIdCausaVacante(String idCausaVacante) {
		this.idCausaVacante = idCausaVacante;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getDisponibilidadViajar() {
		return disponibilidadViajar;
	}

	public void setDisponibilidadViajar(String disponibilidadViajar) {
		this.disponibilidadViajar = disponibilidadViajar;
	}

	public String getDisponibilidadRadicar() {
		return disponibilidadRadicar;
	}

	public void setDisponibilidadRadicar(String disponibilidadRadicar) {
		this.disponibilidadRadicar = disponibilidadRadicar;
	}

	public String getIdNivelEstudio() {
		return idNivelEstudio;
	}

	public void setIdNivelEstudio(String idNivelEstudio) {
		this.idNivelEstudio = idNivelEstudio;
	}

	public String getIdSituacionAcademica() {
		return idSituacionAcademica;
	}

	public void setIdSituacionAcademica(String idSituacionAcademica) {
		this.idSituacionAcademica = idSituacionAcademica;
	}

	public String getHabilidadGeneral() {
		return habilidadGeneral;
	}

	public void setHabilidadGeneral(String habilidadGeneral) {
		this.habilidadGeneral = habilidadGeneral;
	}

	public List<CatalogoOpcionVO> getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(List<CatalogoOpcionVO> habilidades) {
		this.habilidades = habilidades;
	}

	public String getExperienciaAnios() {
		return experienciaAnios;
	}

	public void setExperienciaAnios(String experienciaAnios) {
		this.experienciaAnios = experienciaAnios;
	}

	public int getEdadRequisito() {
		return edadRequisito;
	}

	public void setEdadRequisito(int edadRequisito) {
		this.edadRequisito = edadRequisito;
	}

	public String getEdadMinima() {
		return edadMinima;
	}

	public void setEdadMinima(String edadMinima) {
		this.edadMinima = edadMinima;
	}

	public String getEdadMaxima() {
		return edadMaxima;
	}

	public void setEdadMaxima(String edadMaxima) {
		this.edadMaxima = edadMaxima;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getMapaUbicacion() {
		return mapaUbicacion;
	}

	public void setMapaUbicacion(String mapaUbicacion) {
		this.mapaUbicacion = mapaUbicacion;
	}

	public JGeometry getGeoReferencia() {
		return geoReferencia;
	}

	public void setGeoReferencia(JGeometry geoReferencia) {
		this.geoReferencia = geoReferencia;
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

	public String getIdContacto() {
		return idContacto;
	}

	public void setIdContacto(String idContacto) {
		this.idContacto = idContacto;
	}

	public String getIdHorarioDe() {
		return idHorarioDe;
	}

	public void setIdHorarioDe(String idHorarioDe) {
		this.idHorarioDe = idHorarioDe;
	}

	public String getIdHorarioA() {
		return idHorarioA;
	}

	public void setIdHorarioA(String idHorarioA) {
		this.idHorarioA = idHorarioA;
	}

	public String getIdDuracionAproximada() {
		return idDuracionAproximada;
	}

	public void setIdDuracionAproximada(String idDuracionAproximada) {
		this.idDuracionAproximada = idDuracionAproximada;
	}

	public String getDiasEntrevista() {
		return diasEntrevista;
	}

	public void setDiasEntrevista(String diasEntrevista) {
		this.diasEntrevista = diasEntrevista;
	}

	public String getFuente() {
		return fuente;
	}

	public void setFuente(String fuente) {
		this.fuente = fuente;
	}

	public String getContactoTel() {
		return contactoTel;
	}

	public void setContactoTel(String contactoTel) {
		this.contactoTel = contactoTel;
	}

	public String getContactoCorreo() {
		return contactoCorreo;
	}

	public void setContactoCorreo(String contactoCorreo) {
		this.contactoCorreo = contactoCorreo;
	}

	public String getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getIdTipoEmpleo() {
		return idTipoEmpleo;
	}

	public void setIdTipoEmpleo(String idTipoEmpleo) {
		this.idTipoEmpleo = idTipoEmpleo;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getEmpresaNombre() {
		return empresaNombre;
	}

	public void setEmpresaNombre(String empresaNombre) {
		this.empresaNombre = empresaNombre;
	}

	public String getGradoEstudios() {
		return gradoEstudios;
	}

	public void setGradoEstudios(String gradoEstudios) {
		this.gradoEstudios = gradoEstudios;
	}

	public String getSituacionAcademica() {
		return situacionAcademica;
	}

	public void setSituacionAcademica(String situacionAcademica) {
		this.situacionAcademica = situacionAcademica;
	}

	public String getTipoEmpleo() {
		return tipoEmpleo;
	}

	public void setTipoEmpleo(String tipoEmpleo) {
		this.tipoEmpleo = tipoEmpleo;
	}

	public String getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getEstatusOfertaCandidato() {
		return estatusOfertaCandidato;
	}

	public void setEstatusOfertaCandidato(String estatusOfertaCandidato) {
		this.estatusOfertaCandidato = estatusOfertaCandidato;
	}

	public String getIdiomas() {
		return idiomas;
	}

	public void setIdiomas(String idiomas) {
		this.idiomas = idiomas;
	}

	public String getMedioContacto() {
		return medioContacto;
	}

	public void setMedioContacto(String medioContacto) {
		this.medioContacto = medioContacto;
	}

	public String getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(String requisitos) {
		this.requisitos = requisitos;
	}

	public String getIdiomasCert() {
		return idiomasCert;
	}

	public void setIdiomasCert(String idiomasCert) {
		this.idiomasCert = idiomasCert;
	}

	public String getAreaLaboral() {
		return areaLaboral;
	}

	public void setAreaLaboral(String areaLaboral) {
		this.areaLaboral = areaLaboral;
	}

	public String getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getPrestaciones() {
		return prestaciones;
	}

	public void setPrestaciones(String prestaciones) {
		this.prestaciones = prestaciones;
	}

	public String getJerarquia() {
		return jerarquia;
	}

	public void setJerarquia(String jerarquia) {
		this.jerarquia = jerarquia;
	}

	public String getHorarioLaboral() {
		return horarioLaboral;
	}

	public void setHorarioLaboral(String horarioLaboral) {
		this.horarioLaboral = horarioLaboral;
	}

	public String getTipoDiscapacidad() {
		return tipoDiscapacidad;
	}

	public void setTipoDiscapacidad(String tipoDiscapacidad) {
		this.tipoDiscapacidad = tipoDiscapacidad;
	}

	public String getCausa() {
		return causa;
	}

	public void setCausa(String causa) {
		this.causa = causa;
	}

	public String getIdOfertaCandidato() {
		return idOfertaCandidato;
	}

	public void setIdOfertaCandidato(String idOfertaCandidato) {
		this.idOfertaCandidato = idOfertaCandidato;
	}

	/**
	 * @param fuenteId the fuenteId to set
	 */
	public void setFuenteId(int fuenteId) {
		this.fuenteId = fuenteId;
	}

	/**
	 * @return the fuenteId
	 */
	public int getFuenteId() {
		return fuenteId;
	}

	/**
	 * @return the idContactoCorreo
	 */
	public int getIdContactoCorreo() {
		return IdContactoCorreo;
	}

	/**
	 * @param idContactoCorreo the idContactoCorreo to set
	 */
	public void setIdContactoCorreo(int idContactoCorreo) {
		IdContactoCorreo = idContactoCorreo;
	}

	/**
	 * @return the idContactoDomicilio
	 */
	public int getIdContactoDomicilio() {
		return IdContactoDomicilio;
	}

	/**
	 * @param idContactoDomicilio the idContactoDomicilio to set
	 */
	public void setIdContactoDomicilio(int idContactoDomicilio) {
		IdContactoDomicilio = idContactoDomicilio;
	}

	/**
	 * @return the idContactoTel
	 */
	public int getIdContactoTel() {
		return IdContactoTel;
	}

	/**
	 * @param idContactoTel the idContactoTel to set
	 */
	public void setIdContactoTel(int idContactoTel) {
		IdContactoTel = idContactoTel;
	}

	/**
	 * @return the telefonos
	 */
	public List<TelefonoVO> getTelefonos() {
		return telefonos;
	}

	/**
	 * @param telefonos the telefonos to set
	 */
	public void setTelefonos(List<TelefonoVO> telefonos) {
		this.telefonos = telefonos;
	}

	public String getDescripcionesDiscapacidades() {
		return descripcionesDiscapacidades;
	}

	public void setDescripcionesDiscapacidades(String descripcionesDiscapacidades) {
		this.descripcionesDiscapacidades = descripcionesDiscapacidades;
	}

	public String getConocimientoComputacionDesc() {
		return conocimientoComputacionDesc;
	}

	public void setConocimientoComputacionDesc(String conocimientoComputacionDesc) {
		this.conocimientoComputacionDesc = conocimientoComputacionDesc;
	}

	public String getCargoContacto() {
		return cargoContacto;
	}

	public void setCargoContacto(String cargoContacto) {
		this.cargoContacto = cargoContacto;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
