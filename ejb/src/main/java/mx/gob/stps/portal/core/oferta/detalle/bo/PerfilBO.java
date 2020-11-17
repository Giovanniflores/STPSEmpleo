package mx.gob.stps.portal.core.oferta.detalle.bo;

import mx.gob.stps.portal.core.candidate.vo.CandidatoOtroEstudioVO;
import mx.gob.stps.portal.core.candidate.vo.ConocimientoComputacionVO;
import mx.gob.stps.portal.core.candidate.vo.ExpectativaLaboralVO;
import mx.gob.stps.portal.core.candidate.vo.GradoAcademicoVO;
import mx.gob.stps.portal.core.candidate.vo.HistoriaLaboralVO;
import mx.gob.stps.portal.core.candidate.vo.IdiomaVO;
import mx.gob.stps.portal.core.candidate.vo.PerfilLaboralVo;
import mx.gob.stps.portal.core.candidate.vo.PerfilVO;
import mx.gob.stps.portal.persistencia.vo.ConocimientoHabilidadVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.persistencia.vo.OfertaCandidatoVO;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class PerfilBO implements Serializable {

	private static final long serialVersionUID = 4701572830224358732L;
	
	/*Id's candidato*/
	private long idCandidato;
	private long idUsuario;
	private Long idOficina;
	/*Datos personales*/
	private String curp;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private int idGenero;
	private String genero;
	private Date fechaNacimiento;
	private int edad;
	private long idEntidadNacimiento;
	private String entidadNacimiento;
	private long idEstadoCivil;
	private long idTipoDiscapacidad;
	private String discapacidades;
	/*Domicilio*/
	private long idDomicilio;
	private long idTipoPropietario;
	private long idEntidad;
	private String entidad;
	private long idMunicipio;
	private String municipio;
	private long idColonia;
	private String colonia;
	private String calle;
	private String numeroInterior;
	private String numeroExterior;
	private String entreCalle;
	private String yCalle;
	private long idCodigoPostal;
	private String codigoPostal;
	private long idLocalidad;
	private String domicilioReferencia;
	/*Estilo Curriculum Vitae*/
	private int estiloCV;

	/*Datos de contacto*/
	private int confidencialidad;
	private int contactoCorreo;
	private int contactoTelefono;
	private long horaContactoIni;
	private long horaContactoFin;
	private String scheduleContact;
	private String urlVideo;
	/*Telefonos*/
	private TelefonoVO principal;
	private List<TelefonoVO> secundarios;
	/*Correo electronico*/
	private String correoElectronico;
	/*Situacion Laboral*/
	private long idRecibeOferta;
	private long idTrabaja;
	private long idRazonBusqueda;
	private String inicioBusqueda;//Fecha de inicio de busqueda de empleo
	private long idMedioBusqueda;
	/*Grados academicos*/
	private GradoAcademicoVO gradoPrincipal;
	private List<GradoAcademicoVO> gradosAcademicos;
	/*Otros estudios*/
	private List<CandidatoOtroEstudioVO> otrosEstudiosAcademicos;
	/*Conocimientos y habilidades*/
	private ConocimientoHabilidadVO conocimientoPrincipal;
	private ConocimientoHabilidadVO habilidadPrincipal;
	private List<ConocimientoHabilidadVO> conocimientos;
	private List<ConocimientoHabilidadVO> habilidades;
	/*Idiomas*/
	private IdiomaVO idiomaPrincipal;
	private List<IdiomaVO> idiomas;
	/*Perfil Laboral*/
	private PerfilLaboralVo perfilLaboral;
	private PerfilVO perfilVO;
	/*Computacion Avanzada*/
//	private ComputacionAvanzadaVO computacionPrincipal;
//	private List<ComputacionAvanzadaVO> computacionLista;
	private OfertaCandidatoVO ultimaContratacion;
    private ConocimientoComputacionVO conocimientoComputacionVO;
	/*Experiencia Laboral*/
	private String sector;
	private String area;
	private String ocupacion;
	/*Historia laboral*/
	private HistoriaLaboralVO trabajoActual;
	/*Otros medios*/
	private String mediosBusqueda;
	private List<String> otrosMedios;
	/*Expectativa Laboral*/
	private ExpectativaLaboralVO expectativaLaboral;
	private List<ExpectativaLaboralVO> expectativas;
	/*Lista de catalogos*/
	private List<CatalogoOpcionVO> estadoCivil;
	private List<CatalogoOpcionVO> tipoDiscapacidad;
	private List<CatalogoOpcionVO> horarioContacto;
	private List<CatalogoOpcionVO> trabaja;
	private List<CatalogoOpcionVO> tipoTelefonos;
	private List<CatalogoOpcionVO> habilidadesCandidato;
	
	public int getEstiloCV() {
		return estiloCV;
	}
	
	public void setEstiloCV(int estiloCV) {
		this.estiloCV = estiloCV;
	}
	
	public int getConfidencialidad() {
		return confidencialidad;
	}

	public void setConfidencialidad(int confidencialidad) {
		this.confidencialidad = confidencialidad;
	}
	
	public long getIdCandidato() {
		return idCandidato;
	}
	public void setIdCandidato(long idCandidato) {
		this.idCandidato = idCandidato;
	}
	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
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
	public int getIdGenero() {
		return idGenero;
	}
	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public long getIdEntidadNacimiento() {
		return idEntidadNacimiento;
	}
	public void setIdEntidadNacimiento(long idEntidadNacimiento) {
		this.idEntidadNacimiento = idEntidadNacimiento;
	}
	public String getEntidadNacimiento() {
		return entidadNacimiento;
	}
	public void setEntidadNacimiento(String entidadNacimiento) {
		this.entidadNacimiento = entidadNacimiento;
	}
	public long getIdEstadoCivil() {
		return idEstadoCivil;
	}
	public void setIdEstadoCivil(long idEstadoCivil) {
		this.idEstadoCivil = idEstadoCivil;
	}
	public long getIdTipoDiscapacidad() {
		return idTipoDiscapacidad;
	}
	public void setIdTipoDiscapacidad(long idTipoDiscapacidad) {
		this.idTipoDiscapacidad = idTipoDiscapacidad;
	}
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
	public String getEntidad() {
		return entidad;
	}
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}
	public long getIdMunicipio() {
		return idMunicipio;
	}
	public void setIdMunicipio(long idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public long getIdColonia() {
		return idColonia;
	}
	public void setIdColonia(long idColonia) {
		this.idColonia = idColonia;
	}
	public String getColonia() {
		return colonia;
	}
	public void setColonia(String colonia) {
		this.colonia = colonia;
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
	public long getIdCodigoPostal() {
		return idCodigoPostal;
	}
	public void setIdCodigoPostal(long idCodigoPostal) {
		this.idCodigoPostal = idCodigoPostal;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public int getContactoCorreo() {
		return contactoCorreo;
	}
	public void setContactoCorreo(int contactoCorreo) {
		this.contactoCorreo = contactoCorreo;
	}
	public int getContactoTelefono() {
		return contactoTelefono;
	}
	public void setContactoTelefono(int contactoTelefono) {
		this.contactoTelefono = contactoTelefono;
	}
	public long getHoraContactoIni() {
		return horaContactoIni;
	}
	public void setHoraContactoIni(long horaContactoIni) {
		this.horaContactoIni = horaContactoIni;
	}
	public long getHoraContactoFin() {
		return horaContactoFin;
	}
	public void setHoraContactoFin(long horaContactoFin) {
		this.horaContactoFin = horaContactoFin;
	}
	public String getScheduleContact() {
		return scheduleContact;
	}
	public void setScheduleContact(String scheduleContact) {
		this.scheduleContact = scheduleContact;
	}
	public String getUrlVideo() {
		return urlVideo;
	}
	public void setUrlVideo(String urlVideo) {
		this.urlVideo = urlVideo;
	}
	public TelefonoVO getPrincipal() {
		return principal;
	}
	public void setPrincipal(TelefonoVO principal) {
		this.principal = principal;
	}
	public List<TelefonoVO> getSecundarios() {
		return secundarios;
	}
	public void setSecundarios(List<TelefonoVO> secundarios) {
		this.secundarios = secundarios;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	public long getIdRecibeOferta() {
		return idRecibeOferta;
	}
	public void setIdRecibeOferta(long idRecibeOferta) {
		this.idRecibeOferta = idRecibeOferta;
	}
	public long getIdTrabaja() {
		return idTrabaja;
	}
	public void setIdTrabaja(long idTrabaja) {
		this.idTrabaja = idTrabaja;
	}
	public long getIdRazonBusqueda() {
		return idRazonBusqueda;
	}
	public void setIdRazonBusqueda(long idRazonBusqueda) {
		this.idRazonBusqueda = idRazonBusqueda;
	}
	public String getInicioBusqueda() {
		return inicioBusqueda;
	}
	public void setInicioBusqueda(String inicioBusqueda) {
		this.inicioBusqueda = inicioBusqueda;
	}
	public long getIdMedioBusqueda() {
		return idMedioBusqueda;
	}
	public void setIdMedioBusqueda(long idMedioBusqueda) {
		this.idMedioBusqueda = idMedioBusqueda;
	}
	public GradoAcademicoVO getGradoPrincipal() {
		return gradoPrincipal;
	}
	public void setGradoPrincipal(GradoAcademicoVO gradoPrincipal) {
		this.gradoPrincipal = gradoPrincipal;
	}
	public List<GradoAcademicoVO> getGradosAcademicos() {
		return gradosAcademicos;
	}
	public void setGradosAcademicos(List<GradoAcademicoVO> gradosAcademicos) {
		this.gradosAcademicos = gradosAcademicos;
	}
	public ConocimientoHabilidadVO getConocimientoPrincipal() {
		return conocimientoPrincipal;
	}
	public void setConocimientoPrincipal(
			ConocimientoHabilidadVO conocimientoPrincipal) {
		this.conocimientoPrincipal = conocimientoPrincipal;
	}
	public ConocimientoHabilidadVO getHabilidadPrincipal() {
		return habilidadPrincipal;
	}
	public void setHabilidadPrincipal(ConocimientoHabilidadVO habilidadPrincipal) {
		this.habilidadPrincipal = habilidadPrincipal;
	}
	public List<ConocimientoHabilidadVO> getConocimientos() {
		return conocimientos;
	}
	public void setConocimientos(List<ConocimientoHabilidadVO> conocimientos) {
		this.conocimientos = conocimientos;
	}
	public List<ConocimientoHabilidadVO> getHabilidades() {
		return habilidades;
	}
	public void setHabilidades(List<ConocimientoHabilidadVO> habilidades) {
		this.habilidades = habilidades;
	}
	public IdiomaVO getIdiomaPrincipal() {
		return idiomaPrincipal;
	}
	public void setIdiomaPrincipal(IdiomaVO idiomaPrincipal) {
		this.idiomaPrincipal = idiomaPrincipal;
	}
	public List<IdiomaVO> getIdiomas() {
		return idiomas;
	}
	public void setIdiomas(List<IdiomaVO> idiomas) {
		this.idiomas = idiomas;
	}
	public PerfilLaboralVo getPerfilLaboral() {
		return perfilLaboral;
	}
	public void setPerfilLaboral(PerfilLaboralVo perfilLaboral) {
		this.perfilLaboral = perfilLaboral;
	}

//    public ComputacionAvanzadaVO getComputacionPrincipal() {
//		return computacionPrincipal;
//	}
//	public void setComputacionPrincipal(ComputacionAvanzadaVO computacionPrincipal) {
//		this.computacionPrincipal = computacionPrincipal;
//	}
//	public List<ComputacionAvanzadaVO> getComputacionLista() {
//		return computacionLista;
//	}
//	public void setComputacionLista(List<ComputacionAvanzadaVO> computacionLista) {
//		this.computacionLista = computacionLista;
//	}

    public ConocimientoComputacionVO getConocimientoComputacionVO() {
        return conocimientoComputacionVO;
    }

    public void setConocimientoComputacionVO(ConocimientoComputacionVO conocimientoComputacionVO) {
        this.conocimientoComputacionVO = conocimientoComputacionVO;
    }

    public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getOcupacion() {
		return ocupacion;
	}
	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}
	public HistoriaLaboralVO getTrabajoActual() {
		return trabajoActual;
	}
	public void setTrabajoActual(HistoriaLaboralVO trabajoActual) {
		this.trabajoActual = trabajoActual;
	}
	public String getMediosBusqueda() {
		return mediosBusqueda;
	}
	
	public void setMediosBusqueda(List<String> otrosMedios) {
		StringBuilder medios = new StringBuilder();
		if (null != otrosMedios && !otrosMedios.isEmpty()) {
			this.otrosMedios = otrosMedios;
			Iterator<String> it = otrosMedios.iterator();
			while (it.hasNext())
				medios.append(" ," + it.next());
			if (medios.length() > 2)
				this.mediosBusqueda = medios.substring(2);
		}
	}
	
	public List<String> getOtrosMedios() {
		return otrosMedios;
	}
	public void setOtrosMedios(List<String> otrosMedios) {
		this.otrosMedios = otrosMedios;
	}
	public ExpectativaLaboralVO getExpectativaLaboral() {
		return expectativaLaboral;
	}
	public void setExpectativaLaboral(ExpectativaLaboralVO expectativaLaboral) {
		this.expectativaLaboral = expectativaLaboral;
	}
	public List<ExpectativaLaboralVO> getExpectativas() {
		return expectativas;
	}
	public void setExpectativas(List<ExpectativaLaboralVO> expectativas) {
		this.expectativas = expectativas;
	}
	public List<CatalogoOpcionVO> getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(List<CatalogoOpcionVO> estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public List<CatalogoOpcionVO> getTipoDiscapacidad() {
		return tipoDiscapacidad;
	}
	public void setTipoDiscapacidad(List<CatalogoOpcionVO> tipoDiscapacidad) {
		this.tipoDiscapacidad = tipoDiscapacidad;
	}
	public List<CatalogoOpcionVO> getHorarioContacto() {
		return horarioContacto;
	}
	public void setHorarioContacto(List<CatalogoOpcionVO> horarioContacto) {
		this.horarioContacto = horarioContacto;
	}
	public List<CatalogoOpcionVO> getTrabaja() {
		return trabaja;
	}
	public void setTrabaja(List<CatalogoOpcionVO> trabaja) {
		this.trabaja = trabaja;
	}
	public List<CatalogoOpcionVO> getTipoTelefonos() {
		return tipoTelefonos;
	}
	public void setTipoTelefonos(List<CatalogoOpcionVO> tipoTelefonos) {
		this.tipoTelefonos = tipoTelefonos;
	}
	
	public void setExpectativaPrincipal(ExpectativaLaboralVO expectativaLaboral) {
		this.expectativaLaboral = expectativaLaboral;
	}
	
	public ExpectativaLaboralVO getExpectativaPrincipal() {
		return this.expectativaLaboral;
	}
	
	public void setExpectativaLaboralList(List<ExpectativaLaboralVO> expectativas) {
		this.expectativas = expectativas;
	}
	
	public List<ExpectativaLaboralVO> getExpectativaLaboralList() {
		return this.expectativas;
	}

	public List<CatalogoOpcionVO> getHabilidadesCandidato() {
		return habilidadesCandidato;
	}

	public void setHabilidadesCandidato(List<CatalogoOpcionVO> habilidadesCandidato) {
		this.habilidadesCandidato = habilidadesCandidato;
	}

	public Long getIdOficina() {
		return idOficina;
	}

	public void setIdOficina(Long idOficina) {
		this.idOficina = idOficina;
	}

	public List<CandidatoOtroEstudioVO> getOtrosEstudiosAcademicos() {
		return otrosEstudiosAcademicos;
	}

	public void setOtrosEstudiosAcademicos(List<CandidatoOtroEstudioVO> otrosEstudiosAcademicos) {
		this.otrosEstudiosAcademicos = otrosEstudiosAcademicos;
	}

	public OfertaCandidatoVO getUltimaContratacion() {
		return ultimaContratacion;
	}

	public void setUltimaContratacion(OfertaCandidatoVO ultimaContratacion) {
		this.ultimaContratacion = ultimaContratacion;
	}
	
	public PerfilVO getPerfilVO() {
		return perfilVO;
	}

	public void setPerfilVO(PerfilVO perfilVO) {
		this.perfilVO = perfilVO;
	}

	public String getDiscapacidades() {
		return discapacidades;
	}

	public void setDiscapacidades(String discapacidades) {
		this.discapacidades = discapacidades;
	}

	public long getIdLocalidad() {
		return idLocalidad;
	}

	public void setIdLocalidad(long idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	public String getDomicilioReferencia() {
		return domicilioReferencia;
	}

	public void setDomicilioReferencia(String domicilioReferencia) {
		this.domicilioReferencia = domicilioReferencia;
	}
}
