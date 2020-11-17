package mx.gob.stps.portal.core.oferta.vo;

import java.util.Date;
import java.util.List;
import java.io.Serializable;

import oracle.spatial.geometry.JGeometry;
import mx.gob.stps.portal.core.candidate.vo.ConocimientoComputacionVO;
import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaRequisitoVO;
import mx.gob.stps.portal.core.oferta.registro.vo.OfertaCarreraEspecialidadVO;
import mx.gob.stps.portal.core.oferta.registro.vo.OfertaIdiomaVO;
import mx.gob.stps.portal.core.oferta.registro.vo.OfertaSectorVO;
import mx.gob.stps.portal.core.oferta.registro.vo.RegistroUbicacionVO;
import mx.gob.stps.portal.persistencia.vo.ModalidadOfertaVO;
import mx.gob.stps.portal.persistencia.vo.OfertaEmpleoBecateVO;
import mx.gob.stps.portal.persistencia.vo.OfertaFuncionVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.utils.Catalogos.TIPO_DISCAPACIDAD;

public class OfertaEmpleoVO implements Serializable {
	
	private static final long serialVersionUID = 4498255588710068542L;

	protected long idOfertaEmpleo;

	protected long idEmpresa;

	protected Long idUsuario;

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

	protected Integer plazasCubiertas;

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
	
	protected JGeometry geoReferencia;

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
	
	protected Integer contactoDomicilio;
	
	protected Date fechaAlta;

	protected Date fechaModificacion;

	protected long idTipoEmpleo;
		
	protected int estatus;
	
	protected String fechaInicioString;
	
	protected String fechaFinString;
	
	protected OfertaCarreraEspecialidadVO carreraPrincipal;
	
	protected DomicilioVO domicilio;
	
	protected String entidadDescripcion;
	
	protected String municipioDescripcion;
	
	protected String carreraDescripcion;
	
	protected String ocupacionDescripcion;
	
	private String areaLaboralDescripcion;
	
	private String subAreaLaboralDescripcion;
	
	protected String nivelEstudiosDescripcion;
	
	protected int totalCandidatos;
	
	protected int totalPostulados;
	
	protected int numeroPreguntas;

	protected List<OfertaSectorVO> sectores;
	
	protected List<OfertaRequisitoVO> conocimientos;
	
	protected List<OfertaRequisitoVO> habilidades;
	
	protected List<OfertaRequisitoVO> competencias;
	
	protected List<OfertaIdiomaVO> idiomas;
	
	protected List<OfertaCarreraEspecialidadVO> carreras;
	
	protected List<RegistroUbicacionVO> ubicaciones;
	
	protected List<TelefonoVO> telefonos;
	
	protected List<Long> prestaciones;
	
	private List<OfertaFuncionVO> funcionesList;
	
	protected int idEntidadUbicacion;
	
	protected int idMunicipioUbicacion;
	
	protected String nombreEmpresa;
	
	protected String nombreContacto;
	
	protected String cargoContacto;
	
	protected String correoElectronicoContacto;
	
	protected int idActividadEconomica;

	private String tipoEmpleo;	
	
	protected ConocimientoComputacionVO conocimientoComputacion;
	
	protected Integer idVigenciaOferta;
	
	protected Integer publicarOfertas;

	private Long idOficinaRegistro;
	
	private int idOrigen;
	
	private long[] idHabilidad;
	
	private String discapacidades;
	
	private String descripcionesDiscapacidades;
	
	private long codigo_universal_de_puesto_sfp;
	
	private OfertaEmpleoBecateVO ofertaEmpleoBecate;
	private ModalidadOfertaVO modalidadOferta;	
	
	private boolean ofertaBecate;
	private Integer tipoOferta;
	
	private int idModalidad;
	
	private int idModalidadOferta;
	
	private long idArea;
	
	private long idSubArea;
	
	public long getIdOfertaEmpleo() {
		return idOfertaEmpleo;
	}

	public String getTipoEmpleo() {
		return tipoEmpleo;
	}

	public void setTipoEmpleo(String tipoEmpleo) {
		this.tipoEmpleo = tipoEmpleo;
	}

	public String getEntidad() {
		return entidadDescripcion;
	}

	public void setEntidad(String entidad) {
		this.entidadDescripcion = entidad;
	}

	public String getMunicipio() {
		return municipioDescripcion;
	}

	public void setMunicipio(String municipio) {
		this.municipioDescripcion = municipio;
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

	public OfertaCarreraEspecialidadVO getCarreraPrincipal() {
		return carreraPrincipal;
	}

	public void setCarreraPrincipal(OfertaCarreraEspecialidadVO carreraPrincipal) {
		this.carreraPrincipal = carreraPrincipal;
	}

	public DomicilioVO getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(DomicilioVO domicilio) {
		this.domicilio = domicilio;
	}

	public String getEntidadDescripcion() {
		return entidadDescripcion;
	}

	public void setEntidadDescripcion(String entidadDescripcion) {
		this.entidadDescripcion = entidadDescripcion;
	}

	public String getMunicipioDescripcion() {
		return municipioDescripcion;
	}

	public void setMunicipioDescripcion(String municipioDescripcion) {
		this.municipioDescripcion = municipioDescripcion;
	}

	public String getCarreraDescripcion() {
		return carreraDescripcion;
	}

	public void setCarreraDescripcion(String carreraDescripcion) {
		this.carreraDescripcion = carreraDescripcion;
	}

	public String getOcupacionDescripcion() {
		return ocupacionDescripcion;
	}

	public void setOcupacionDescripcion(String ocupacionDescripcion) {
		this.ocupacionDescripcion = ocupacionDescripcion;
	}

	public String getNivelEstudiosDescripcion() {
		return nivelEstudiosDescripcion;
	}

	public void setNivelEstudiosDescripcion(String nivelEstudiosDescripcion) {
		this.nivelEstudiosDescripcion = nivelEstudiosDescripcion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getTotalCandidatos() {
		return totalCandidatos;
	}

	public void setTotalCandidatos(int totalCandidatos) {
		this.totalCandidatos = totalCandidatos;
	}

	public int getTotalPostulados() {
		return totalPostulados;
	}

	public void setTotalPostulados(int totalPostulados) {
		this.totalPostulados = totalPostulados;
	}

	public int getNumeroPreguntas() {
		return numeroPreguntas;
	}

	public void setNumeroPreguntas(int numeroPreguntas) {
		this.numeroPreguntas = numeroPreguntas;
	}

	public List<OfertaSectorVO> getSectores() {
		return sectores;
	}

	public void setSectores(List<OfertaSectorVO> sectores) {
		this.sectores = sectores;
	}

	public List<OfertaRequisitoVO> getConocimientos() {
		return conocimientos;
	}

	public void setConocimientos(List<OfertaRequisitoVO> conocimientos) {
		this.conocimientos = conocimientos;
	}

	public List<OfertaRequisitoVO> getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(List<OfertaRequisitoVO> habilidades) {
		this.habilidades = habilidades;
	}

	public List<OfertaRequisitoVO> getCompetencias() {
		return competencias;
	}

	public void setCompetencias(List<OfertaRequisitoVO> competencias) {
		this.competencias = competencias;
	}

	public List<OfertaIdiomaVO> getIdiomas() {
		return idiomas;
	}

	public void setIdiomas(List<OfertaIdiomaVO> idiomas) {
		this.idiomas = idiomas;
	}

	public List<OfertaCarreraEspecialidadVO> getCarreras() {
		return carreras;
	}

	public void setCarreras(List<OfertaCarreraEspecialidadVO> carreras) {
		this.carreras = carreras;
	}

	public List<RegistroUbicacionVO> getUbicaciones() {
		return ubicaciones;
	}

	public void setUbicaciones(List<RegistroUbicacionVO> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}

	public List<Long> getPrestaciones() {
		return prestaciones;
	}

	public void setPrestaciones(List<Long> prestaciones) {
		this.prestaciones = prestaciones;
	}

	public int getIdEntidadUbicacion() {
		return idEntidadUbicacion;
	}

	public void setIdEntidadUbicacion(int idEntidadUbicacion) {
		this.idEntidadUbicacion = idEntidadUbicacion;
	}

	public int getIdMunicipioUbicacion() {
		return idMunicipioUbicacion;
	}

	public void setIdMunicipioUbicacion(int idMunicipioUbicacion) {
		this.idMunicipioUbicacion = idMunicipioUbicacion;
	}
	
	

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public ConocimientoComputacionVO getConocimientoComputacion() {
		return conocimientoComputacion;
	}

	public void setConocimientoComputacion(
			ConocimientoComputacionVO conocimientoComputacion) {
		this.conocimientoComputacion = conocimientoComputacion;
	}

	public String getNombreContacto() {
		return nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	public String getCargoContacto() {
		return cargoContacto;
	}

	public void setCargoContacto(String cargoContacto) {
		this.cargoContacto = cargoContacto;
	}

	public String getCorreoElectronicoContacto() {
		return correoElectronicoContacto;
	}

	public void setCorreoElectronicoContacto(String correoElectronicoContacto) {
		this.correoElectronicoContacto = correoElectronicoContacto;
	}
	
	public List<TelefonoVO> getTelefonos() {
		return telefonos;
	}

	public void setTelefonos(List<TelefonoVO> telefonos) {
		this.telefonos = telefonos;
	}

	public Integer getContactoDomicilio() {
		return contactoDomicilio;
	}

	public void setContactoDomicilio(Integer contactoDomicilio) {
		this.contactoDomicilio = contactoDomicilio;
	}

	public int getIdActividadEconomica() {
		return idActividadEconomica;
	}

	public void setIdActividadEconomica(int idActividadEconomica) {
		this.idActividadEconomica = idActividadEconomica;
	}

	/**
	 * @return the conocimientoComputacion
	 */
	public ConocimientoComputacionVO getConocimientoComputacionVO() {
		return conocimientoComputacion;
	}

	/**
	 * @param conocimientoComputacion the conocimientoComputacion to set
	 */
	public void setConocimientoComputacionVO(
			ConocimientoComputacionVO conocimientoComputacion) {
		this.conocimientoComputacion = conocimientoComputacion;
	}

	public Integer getIdVigenciaOferta() {
		return idVigenciaOferta;
	}

	public void setIdVigenciaOferta(Integer idVigenciaOferta) {
		this.idVigenciaOferta = idVigenciaOferta;
	}

	public long[] getIdHabilidad() {
		return idHabilidad;
	}

	public void setIdHabilidad(long[] idHabilidad) {
		this.idHabilidad = idHabilidad;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getPlazasCubiertas() {
		return plazasCubiertas;
	}

	public void setPlazasCubiertas(Integer plazasCubiertas) {
		this.plazasCubiertas = plazasCubiertas;
	}

	public Integer getPublicarOfertas() {
		return publicarOfertas;
	}

	public void setPublicarOfertas(Integer publicarOfertas) {
		this.publicarOfertas = publicarOfertas;
	}
	
	public String getDiscapacidades() {
		return discapacidades;
	}

	public void setDiscapacidades(String discapacidades) {
		this.discapacidades = discapacidades;
	}	

	public boolean isDiscapacidadAuditiva(){	
		return ("1".equals(String.valueOf(this.discapacidades.charAt(0))) ? true : false);
	}

	public boolean isDiscapacidadIntelectual(){
		return ("1".equals(String.valueOf(this.discapacidades.charAt(1))) ? true : false);		
	}	
	
	public boolean isDiscapacidadMental(){
		return ("1".equals(String.valueOf(this.discapacidades.charAt(2))) ? true : false);		
	}
	
	public boolean isDiscapacidadMotriz(){
		return ("1".equals(String.valueOf(this.discapacidades.charAt(3))) ? true : false);		
	}
	
	public boolean isDiscapacidadVisual(){
		return ("1".equals(String.valueOf(this.discapacidades.charAt(4))) ? true : false);		
	}


	public boolean isDiscapacidadHabla(){
		if(this.discapacidades.length()== Catalogos.BINARIO_DISCAPACIDAD_NINGUNA.length())
			return ("1".equals(String.valueOf(this.discapacidades.charAt(5))) ? true : false);
		return false;
	}

	
	public boolean isDiscapacidadIntelMental(){
		if(this.discapacidades.length()== Catalogos.BINARIO_DISCAPACIDAD_NINGUNA.length())
			return ("1".equals(String.valueOf(this.discapacidades.charAt(6))) ? true : false);		
		return false;
	}

	
	public boolean isDiscapacidadOtras(){
		if(this.discapacidades.length()== Catalogos.BINARIO_DISCAPACIDAD_NINGUNA.length())
			return ("1".equals(String.valueOf(this.discapacidades.charAt(7))) ? true : false);		
		return false;
	}

	
	
	public boolean isDiscapacitado(){
		
		if (this.isDiscapacidadAuditiva())
			return true;

		if (this.isDiscapacidadIntelectual())
			return true;		

		if (this.isDiscapacidadMental())
			return true;
		
		if (this.isDiscapacidadMotriz())
			return true;
		
		if (this.isDiscapacidadVisual())
			return true;		
		
		
		if (this.isDiscapacidadHabla())
			return true;		

		if (this.isDiscapacidadIntelMental())
			return true;		
		
		if (this.isDiscapacidadOtras())
			return true;		

		return false;
	}
	
	public void setDiscapacidadAuditiva(boolean valor){
		this.setCharN(valor, 0);
	}

	public void setDiscapacidadIntelectual(boolean valor){
		this.setCharN(valor, 1);
	}

	public void setDiscapacidadMental(boolean valor){
		this.setCharN(valor, 2);
	}
	
	public void setDiscapacidadMotriz(boolean valor){
		this.setCharN(valor, 3);
	}

	public void setDiscapacidadVisual(boolean valor){
		this.setCharN(valor, 4);
	}	
	
	public void setDiscapacidadNinguna(){
		this.discapacidades = new String(Catalogos.BINARIO_DISCAPACIDAD_NINGUNA);//A001
	}
	
	public long getIdDiscapacidad() {
		return idDiscapacidad;
	}		
	
	public void setIdDiscapacidad(long idDiscapacidad) {
		this.idDiscapacidad = idDiscapacidad;
	}	

	private void setCharN(boolean valor, int n){
		
		String str = null;
		
		if (n == 0)
			str = new String((valor ? "1" : "0"));
		else 
			str = String.valueOf(discapacidades.charAt(0));
		
		if (n == 1)
			str = str + new String((valor ? "1" : "0"));
		else 
			str = str + String.valueOf(discapacidades.charAt(1));
		
		if (n == 2)
			str = str + new String((valor ? "1" : "0"));
		else 
			str = str + String.valueOf(discapacidades.charAt(2));

		if (n == 3)
			str = str + new String((valor ? "1" : "0"));
		else 
			str = str + String.valueOf(discapacidades.charAt(3));

		if (n == 4)
			str = str + new String((valor ? "1" : "0"));
		else 
			str = str + String.valueOf(discapacidades.charAt(4));

		
		if (n == 5)
			str = str + new String((valor ? "1" : "0"));
		else if(discapacidades.length()== Catalogos.BINARIO_DISCAPACIDAD_NINGUNA.length())
			str = str + String.valueOf(discapacidades.charAt(5));

		
		
		if (n == 6)
			str = str + new String((valor ? "1" : "0"));
		else if(discapacidades.length()== Catalogos.BINARIO_DISCAPACIDAD_NINGUNA.length())
			str = str + String.valueOf(discapacidades.charAt(6));

		
		
		if (n == 7)
			str = str + new String((valor ? "1" : "0"));
		else if(discapacidades.length()== Catalogos.BINARIO_DISCAPACIDAD_NINGUNA.length())
			str = str + String.valueOf(discapacidades.charAt(7));

		
		discapacidades = new String(str);		
	}	
	
	public String getDescripcionDiscapacidades(String discapacidades){
		StringBuilder descripciones = new StringBuilder();
		if(null !=discapacidades){ // A001
			if(discapacidades.equals(Catalogos.BINARIO_DISCAPACIDAD_NINGUNA) || discapacidades.equals("00000")  ){
				descripciones.append(TIPO_DISCAPACIDAD.NINGUNA.getOpcion()); 
			} else {
				for(int i=0; i<discapacidades.length(); i++){
					int idDiscapacidad = Character.getNumericValue(discapacidades.charAt(i));
					if(idDiscapacidad == 1){
						if(descripciones.length()>0){
							descripciones.append(", ");
						}										
						if (i == 0){
							descripciones.append(TIPO_DISCAPACIDAD.AUDITIVA.getOpcion()); 
						} 
						if (i == 1){
							descripciones.append(TIPO_DISCAPACIDAD.INTELECTUAL.getOpcion()); 
						} 
						if (i == 2){
							descripciones.append(TIPO_DISCAPACIDAD.MENTAL.getOpcion()); 
						} 			
						if (i == 3){
							descripciones.append(TIPO_DISCAPACIDAD.MOTRIZ.getOpcion()); 
						} 			
						if (i == 4){
							descripciones.append(TIPO_DISCAPACIDAD.VISUAL.getOpcion()); 
						} 											
						if (i == 5){
							descripciones.append(TIPO_DISCAPACIDAD.HABLA.getOpcion()); 
						} 											
						if (i == 6){
							descripciones.append(TIPO_DISCAPACIDAD.INTEL_MENTAL.getOpcion()); 
						} 											
						if (i == 7){
							descripciones.append(TIPO_DISCAPACIDAD.OTRAS.getOpcion()); 
						} 											
					}
				}				
			}
		} else {
			descripciones.append(TIPO_DISCAPACIDAD.NINGUNA.getOpcion()); 
		}
		return descripciones.toString();
	}

	public String getDescripcionesDiscapacidades() {
		return getDescripcionDiscapacidades(discapacidades);
	}

	public void setDescripcionesDiscapacidades(String descripcionesDiscapacidades) {
		this.descripcionesDiscapacidades = descripcionesDiscapacidades;
	}

	public Long getIdOficinaRegistro() {
		return idOficinaRegistro;
	}

	public void setIdOficinaRegistro(Long idOficinaRegistro) {
		this.idOficinaRegistro = idOficinaRegistro;
	}

	public long getCodigo_universal_de_puesto_sfp() {
		return codigo_universal_de_puesto_sfp;
	}

	public void setCodigo_universal_de_puesto_sfp(long codigo_universal_de_puesto_sfp) {
		this.codigo_universal_de_puesto_sfp = codigo_universal_de_puesto_sfp;
	}

	public OfertaEmpleoBecateVO getOfertaEmpleoBecate() {
		return ofertaEmpleoBecate;
	}

	public void setOfertaEmpleoBecate(OfertaEmpleoBecateVO ofertaEmpleoBecate) {
		this.ofertaEmpleoBecate = ofertaEmpleoBecate;
	}

	public boolean isOfertaBecate() {
		return ofertaBecate;
	}

	public void setOfertaBecate(boolean ofertaBecate) {
		this.ofertaBecate = ofertaBecate;
	}

	public int getIdModalidad() {
		return idModalidad;
	}

	public void setIdModalidad(int idModalidad) {
		this.idModalidad = idModalidad;
	}

	public ModalidadOfertaVO getModalidadOferta() {
		return modalidadOferta;
	}

	public void setModalidadOferta(ModalidadOfertaVO modalidadOferta) {
		this.modalidadOferta = modalidadOferta;
	}

	public int getIdModalidadOferta() {
		return idModalidadOferta;
	}

	public void setIdModalidadOferta(int idModalidadOferta) {
		this.idModalidadOferta = idModalidadOferta;
	}

	public Integer getTipoOferta() {
		return tipoOferta;
	}

	public void setTipoOferta(Integer tipoOferta) {
		this.tipoOferta = tipoOferta;
	}

	public String getAreaLaboralDescripcion() {
		return areaLaboralDescripcion;
	}

	public void setAreaLaboralDescripcion(String areaLaboralDescripcion) {
		this.areaLaboralDescripcion = areaLaboralDescripcion;
	}

	public String getSubAreaLaboralDescripcion() {
		return subAreaLaboralDescripcion;
	}

	public void setSubAreaLaboralDescripcion(String subAreaLaboralDescripcion) {
		this.subAreaLaboralDescripcion = subAreaLaboralDescripcion;
	}

	public List<OfertaFuncionVO> getFuncionesList() {
		return funcionesList;
	}

	public void setFuncionesList(List<OfertaFuncionVO> funcionesList) {
		this.funcionesList = funcionesList;
	}

	public int getIdOrigen() {
		return idOrigen;
	}

	public void setIdOrigen(int idOrigen) {
		this.idOrigen = idOrigen;
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
}
