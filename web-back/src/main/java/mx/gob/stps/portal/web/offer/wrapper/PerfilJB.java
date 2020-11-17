package mx.gob.stps.portal.web.offer.wrapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.persistencia.vo.ConocimientoHabilidadVO;
import mx.gob.stps.portal.persistencia.vo.OfertaCandidatoVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.core.candidate.vo.CandidatoOtroEstudioVO;
import mx.gob.stps.portal.core.candidate.vo.ConocimientoComputacionVO;
import mx.gob.stps.portal.core.candidate.vo.ExpectativaLaboralVO;
import mx.gob.stps.portal.core.candidate.vo.GradoAcademicoVO;
import mx.gob.stps.portal.core.candidate.vo.HistoriaLaboralVO;
import mx.gob.stps.portal.core.candidate.vo.IdiomaVO;
import mx.gob.stps.portal.core.candidate.vo.PerfilLaboralVo;
import mx.gob.stps.portal.core.candidate.vo.PerfilVO;
import mx.gob.stps.portal.core.oferta.detalle.helper.Utils;

public class PerfilJB implements Serializable {
	
	private static final long serialVersionUID = 5868776989785628150L;
	
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
	private String strFechaNacimiento;
	private int edad;
	private long idEntidadNacimiento;
	private String entidadNacimiento;
	private long idEstadoCivil;
	private long idTipoDiscapacidad;
	/*Domicilio*/
	private long idDomicilio;
	private int idTipoPropietario;
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
	private int idCodigoPostal;
	private String codigoPostal;
	private long idLocalidad;
	private String domicilioReferencia;
	/* Estilo Curriculum Vitae*/
	private int estiloCV;
	/*Datos de contacto*/
	private int confidencialidad;
	private int contactoCorreo;
	private int contactoTelefono;
	private int horaContactoIni;
	private int horaContactoFin;
	private String scheduleContact;
	private String urlVideo;
	/*Telefonos*/
	private TelefonoVO principal;
	private List<TelefonoVO> secundarios;
	/*Correo electronico*/
	private String correoElectronico;
	/*Situacion Laboral*/
	private int idRecibeOferta;
	private int idTrabaja;
	private int idRazonBusqueda;
	private String inicioBusqueda;//Fecha de inicio de busqueda de empleo
	private int idMedioBusqueda;
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
	/*Datos complementarios*/
	private OfertaCandidatoVO ultimaContratacion;
	private int idLicencia;
	
	/*
	 * Metodo constructor 
	 **/
	public PerfilJB() {
		curp = ""; 
		nombre = "";
		apellido1 = "";
		apellido2 = "";
		genero = "";
		strFechaNacimiento = "";
		entidadNacimiento = "";
		entidad = "";
		municipio = "";
		colonia = "";
		calle = "";
		numeroInterior = "";
		numeroExterior = "";
		entreCalle = "";
		yCalle = "";
		codigoPostal = "";
		scheduleContact = "";
		urlVideo  = "";
		correoElectronico = "";
		inicioBusqueda = "";
		sector = "";
		area = "";
		ocupacion = "";
		mediosBusqueda = "";
		idiomaPrincipal = new IdiomaVO();
		gradoPrincipal = new GradoAcademicoVO();
		trabajoActual = new HistoriaLaboralVO();
		conocimientoPrincipal = new ConocimientoHabilidadVO();
		habilidadPrincipal = new ConocimientoHabilidadVO();
		estadoCivil = new ArrayList<CatalogoOpcionVO>();
		tipoDiscapacidad = new ArrayList<CatalogoOpcionVO>();
		horarioContacto = new ArrayList<CatalogoOpcionVO>();
		trabaja = new ArrayList<CatalogoOpcionVO>();
		otrosMedios = new ArrayList<String>();
		tipoTelefonos = new ArrayList<CatalogoOpcionVO>();
		gradosAcademicos = new ArrayList<GradoAcademicoVO>();
		conocimientos = new ArrayList<ConocimientoHabilidadVO>();
		habilidades = new ArrayList<ConocimientoHabilidadVO>();
		idiomas = new ArrayList<IdiomaVO>();
//		computacionLista = new ArrayList<ComputacionAvanzadaVO>();
        conocimientoComputacionVO = new ConocimientoComputacionVO();
		expectativas = new ArrayList<ExpectativaLaboralVO>();
	}
	
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
	
	public void setMediosBusqueda(String mediosBusqueda) {
		if (null != mediosBusqueda)
			this.mediosBusqueda = mediosBusqueda;
	}
	
	public String getMediosBusqueda() {
		return this.mediosBusqueda;
	}
	
	public void setTrabajoActual(HistoriaLaboralVO trabajoActual) {
		if (null != trabajoActual)
			this.trabajoActual = trabajoActual;
	}
	
	public HistoriaLaboralVO getTrabajoActual() {
		return trabajoActual;
	}
	
	public void setSector(String sector) {
		if (null != sector)
			this.sector = sector;
	}
	
	public void setArea(String area) {
		if (null != area)
			this.area = area;
	}
	
	public void setOcupacion(String ocupacion) {
		if (null != ocupacion)
			this.ocupacion = ocupacion;
	}
	
	public String getSector() {
		return this.sector;
	}
	
	public String getArea() {
		return this.area;
	}
	
	public String getOcupacion() {
		return this.ocupacion;
	}
	
	public List<GradoAcademicoVO> getGradosAcademicos() {
		return gradosAcademicos;
	}
	
	public void setGradosAcademicos(List<GradoAcademicoVO> gradosAcademicos) {
		if (null != gradosAcademicos)
			this.gradosAcademicos = gradosAcademicos;
	}
	
	public GradoAcademicoVO getGradoPrincipal() {
		return this.gradoPrincipal;
	}
	
	public void setGradoPrincipal(GradoAcademicoVO gradoPrincipal) {
		if (null != gradoPrincipal)
			this.gradoPrincipal = gradoPrincipal;
	}
	
	public PerfilLaboralVo getPerfilLaboral() {
		return this.perfilLaboral;
	}
	
	public void setPerfilLaboral(PerfilLaboralVo perfilLaboral) {
		this.perfilLaboral = perfilLaboral;
	}
	
	public List<ConocimientoHabilidadVO> getConocimientos() {
		return conocimientos;
	}
	
	public void setConocimientos(List<ConocimientoHabilidadVO> conocimientos) {
		if (null != conocimientos)
			this.conocimientos = conocimientos;
	}
	
	public List<ConocimientoHabilidadVO> getHabilidades() {
		return habilidades;
	}
	
	public void setConocimientoPrincipal(ConocimientoHabilidadVO conocimientoPrincipal) {
		if (null != conocimientoPrincipal)
			this.conocimientoPrincipal = conocimientoPrincipal;
	}
	
	public void setHabilidades(List<ConocimientoHabilidadVO> conocimientos) {
		if (null != conocimientos)
			this.habilidades = conocimientos;
	}
	
	public ConocimientoHabilidadVO getConocimientoPrincipal() {
		return conocimientoPrincipal;
	}
	
	public ConocimientoHabilidadVO getHabilidadPrincipal() {
		return habilidadPrincipal;
	}
	
	public void setHabilidadPrincipal(ConocimientoHabilidadVO habilidadPrincipal) {
		if (null != habilidadPrincipal)
			this.habilidadPrincipal = habilidadPrincipal;
	}
	
	public void setIdiomaPrincipal(IdiomaVO idiomaPrincipal) {
		this.idiomaPrincipal = idiomaPrincipal;
	}
	
	public IdiomaVO getIdiomaPrincipal() {
		return this.idiomaPrincipal;
	}
	
	public void setIdiomas(List<IdiomaVO> idiomas) {
		this.idiomas = idiomas;
	}
	
	public List<IdiomaVO> getIdiomas() {
		return this.idiomas;
	}
	
//	public void setComputacionPrincipal(ComputacionAvanzadaVO computacionPrincipal) {
//		this.computacionPrincipal = computacionPrincipal;
//	}
//
//	public ComputacionAvanzadaVO getComputacionPrincipal() {
//		return this.computacionPrincipal;
//	}
//
//	public void setComputacionLista(List<ComputacionAvanzadaVO> computacionLista) {
//		this.computacionLista = computacionLista;
//	}
//
//	public List<ComputacionAvanzadaVO> getComputacionLista() {
//		return this.computacionLista;
//	}


    public ConocimientoComputacionVO getConocimientoComputacionVO() {
        return conocimientoComputacionVO;
    }

    public void setConocimientoComputacionVO(ConocimientoComputacionVO conocimientoComputacionVO) {
        this.conocimientoComputacionVO = conocimientoComputacionVO;
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
	
	/**
	 * @return the idCandidato
	 */
	public String getUrlVideo() {
		return this.urlVideo;
	}
	
	/**
	 * @param idCandidato the idCandidato to set
	 */
	public void setUrlVideo(String urlVideo) {
		if (null != urlVideo)
			this.urlVideo = urlVideo;
		else
			this.urlVideo = "";
	}
	
	/**
	 * @return the idCandidato
	 */
	public long getIdCandidato() {
		return idCandidato;
	}
	/**
	 * @param idCandidato the idCandidato to set
	 */
	public void setIdCandidato(long idCandidato) {
		this.idCandidato = idCandidato;
	}
	/**
	 * @return the idUsuario
	 */
	public long getIdUsuario() {
		return idUsuario;
	}
	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	/**
	 * @return the curp
	 */
	public String getCurp() {
		return curp;
	}
	/**
	 * @param curp the curp to set
	 */
	public void setCurp(String curp) {
		this.curp = curp;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		if (null != nombre)
			this.nombre = nombre;
		else
			this.nombre = "";
	}
	/**
	 * @return the apellido1
	 */
	public String getApellido1() {
		return apellido1;
	}
	/**
	 * @param apellido1 the apellido1 to set
	 */
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	/**
	 * @return the apellido2
	 */
	public String getApellido2() {
		return apellido2;
	}
	/**
	 * @param apellido2 the apellido2 to set
	 */
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	/**
	 * @return the idGenero
	 */
	public int getIdGenero() {
		return idGenero;
	}
	/**
	 * @param idGenero the idGenero to set
	 */
	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}
	/**
	 * @return the genero
	 */
	public String getGenero() {
		return genero;
	}
	/**
	 * @param genero the genero to set
	 */
	public void setGenero(String genero) {
		this.genero = genero;
	}
	/**
	 * @return the fechaNacimiento
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(Date fechaNacimiento) {
		if (null != fechaNacimiento)
			this.strFechaNacimiento = Utils.formatDate(fechaNacimiento);
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public String getFormattedFechaNacimiento() {
		return this.strFechaNacimiento;
	}
	
	/**
	 * @return the edad
	 */
	public int getEdad() {
		return edad;
	}
	
	/**
	 * @param edad the edad to set
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	/**
	 * @return the idEntidadNacimiento
	 */
	public long getIdEntidadNacimiento() {
		return idEntidadNacimiento;
	}
	/**
	 * @param idEntidadNacimiento the idEntidadNacimiento to set
	 */
	public void setIdEntidadNacimiento(long idEntidadNacimiento) {
		this.idEntidadNacimiento = idEntidadNacimiento;
	}
	/**
	 * @return the entidadNacimiento
	 */
	public String getEntidadNacimiento() {
		return entidadNacimiento;
	}
	/**
	 * @param entidadNacimiento the entidadNacimiento to set
	 */
	public void setEntidadNacimiento(String entidadNacimiento) {
		if (null != entidadNacimiento)
			this.entidadNacimiento = entidadNacimiento;
		else
			this.entidadNacimiento = "";
	}
	/**
	 * @return the idEstadoCivil
	 */
	public long getIdEstadoCivil() {
		return idEstadoCivil;
	}
	/**
	 * @param idEstadoCivil the idEstadoCivil to set
	 */
	public void setIdEstadoCivil(long idEstadoCivil) {
		this.idEstadoCivil = idEstadoCivil;
	}
	/**
	 * @return the idTipoDiscapacidad
	 */
	public long getIdTipoDiscapacidad() {
		return idTipoDiscapacidad;
	}
	/**
	 * @param idTipoDiscapacidad the idTipoDiscapacidad to set
	 */
	public void setIdTipoDiscapacidad(long idTipoDiscapacidad) {
		this.idTipoDiscapacidad = idTipoDiscapacidad;
	}
	/**
	 * @return the idDomicilio
	 */
	public long getIdDomicilio() {
		return idDomicilio;
	}
	/**
	 * @param idDomicilio the idDomicilio to set
	 */
	public void setIdDomicilio(long idDomicilio) {
		this.idDomicilio = idDomicilio;
	}
	/**
	 * @return the idTipoPropietario
	 */
	public int getIdTipoPropietario() {
		return idTipoPropietario;
	}
	/**
	 * @param idTipoPropietario the idTipoPropietario to set
	 */
	public void setIdTipoPropietario(int idTipoPropietario) {
		this.idTipoPropietario = idTipoPropietario;
	}
	/**
	 * @return the idEntidad
	 */
	public long getIdEntidad() {
		return idEntidad;
	}
	/**
	 * @param idEntidad the idEntidad to set
	 */
	public void setIdEntidad(long idEntidad) {
		this.idEntidad = idEntidad;
	}
	
	/**
	 * @return the idEntidad
	 */
	public String getEntidad() {
		return entidad;
	}
	
	/**
	 * @param idEntidad the idEntidad to set
	 */
	public void setEntidad(String entidad) {
		this.entidad = entidad;
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
	
	/**
	 * @return the idMunicipio
	 */
	public String getMunicipio() {
		return municipio;
	}
	
	/**
	 * @param idMunicipio the idMunicipio to set
	 */
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	
	/**
	 * @return the idColonia
	 */
	public long getIdColonia() {
		return idColonia;
	}
	/**
	 * @param idColonia the idColonia to set
	 */
	public void setIdColonia(long idColonia) {
		this.idColonia = idColonia;
	}
	
	/**
	 * @return the idColonia
	 */
	public String getColonia() {
		if (null != this.colonia)
			return this.colonia;
		else
			return "";
	}
	
	/**
	 * @param idColonia the idColonia to set
	 */
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}
	
	/**
	 * @return the calle
	 */
	public String getCalle() {
		return calle;
	}
	/**
	 * @param calle the calle to set
	 */
	public void setCalle(String calle) {
		if (null != calle)
			this.calle = calle;
		else
			this.calle = "";
	}
	/**
	 * @return the numeroInterior
	 */
	public String getNumeroInterior() {
		return numeroInterior;
	}
	/**
	 * @param numeroInterior the numeroInterior to set
	 */
	public void setNumeroInterior(String numeroInterior) {
		if (null != numeroInterior)
			this.numeroInterior = numeroInterior;
		else
			this.numeroInterior = "";
	}
	/**
	 * @return the numeroExterior
	 */
	public String getNumeroExterior() {
		return numeroExterior;
	}
	/**
	 * @param numeroExterior the numeroExterior to set
	 */
	public void setNumeroExterior(String numeroExterior) {
		if (null != numeroExterior)
			this.numeroExterior = numeroExterior;
		else
			this.numeroExterior = "";
	}
	/**
	 * @return the entreCalle
	 */
	public String getEntreCalle() {
		return entreCalle;
	}
	/**
	 * @param entreCalle the entreCalle to set
	 */
	public void setEntreCalle(String entreCalle) {
		if (null != entreCalle)
			this.entreCalle = entreCalle;
		else
			this.entreCalle = "";
	}
	/**
	 * @return the yCalle
	 */
	public String getyCalle() {
		return yCalle;
	}
	/**
	 * @param yCalle the yCalle to set
	 */
	public void setyCalle(String yCalle) {
		if (null != yCalle)
			this.yCalle = yCalle;
		else
			this.yCalle = "";
	}
	
	/**
	 * @return the idCodigoPostal
	 */
	public int getIdCodigoPostal() {
		return idCodigoPostal;
	}
	/**
	 * @param idCodigoPostal the idCodigoPostal to set
	 */
	public void setIdCodigoPostal(int idCodigoPostal) {
		this.idCodigoPostal = idCodigoPostal;
	}
	
	/**
	 * @return the idCodigoPostal
	 */
	public String getCodigoPostal() {
		return this.codigoPostal;
	}
	
	/**
	 * @param idCodigoPostal the idCodigoPostal to set
	 */
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	
	/**
	 * @return the contactoCorreo
	 */
	public int getContactoCorreo() {
		return contactoCorreo;
	}
	/**
	 * @param contactoCorreo the contactoCorreo to set
	 */
	public void setContactoCorreo(int contactoCorreo) {
		this.contactoCorreo = contactoCorreo;
	}
	/**
	 * @return the contactoTelefono
	 */
	public int getContactoTelefono() {
		return contactoTelefono;
	}
	/**
	 * @param contactoTelefono the contactoTelefono to set
	 */
	public void setContactoTelefono(int contactoTelefono) {
		this.contactoTelefono = contactoTelefono;
	}
	/**
	 * @return the horaContactoIni
	 */
	public int getHoraContactoIni() {
		return horaContactoIni;
	}
	/**
	 * @param horaContactoIni the horaContactoIni to set
	 */
	public void setHoraContactoIni(int horaContactoIni) {
		this.horaContactoIni = horaContactoIni;
	}
	/**
	 * @return the horaContactoFin
	 */
	public int getHoraContactoFin() {
		return horaContactoFin;
	}
	/**
	 * @param horaContactoFin the horaContactoFin to set
	 */
	public void setHoraContactoFin(int horaContactoFin) {
		this.horaContactoFin = horaContactoFin;
	}
	/**
	 * @return the principal
	 */
	public TelefonoVO getPrincipal() {
		return principal;
	}
	/**
	 * @param principal the principal to set
	 */
	public void setPrincipal(TelefonoVO principal) {
		if (null != principal)
			this.principal = principal;
		else
			this.principal = new TelefonoVO();
	}
	/**
	 * @param secundarios the secundarios to set
	 */
	public void setSecundarios(List<TelefonoVO> secundarios) {
		this.secundarios = secundarios;
	}
	/**
	 * @return the secundarios
	 */
	public List<TelefonoVO> getSecundarios() {
		return secundarios;
	}
	/**
	 * @return the correoElectronico
	 */
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	/**
	 * @param correoElectronico the correoElectronico to set
	 */
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	/**
	 * @return the idRecibeOferta
	 */
	public int getIdRecibeOferta() {
		return idRecibeOferta;
	}
	/**
	 * @param idRecibeOferta the idRecibeOferta to set
	 */
	public void setIdRecibeOferta(int idRecibeOferta) {
		this.idRecibeOferta = idRecibeOferta;
	}
	/**
	 * @return the idTrabaja
	 */
	public int getIdTrabaja() {
		return idTrabaja;
	}
	/**
	 * @param idTrabaja the idTrabaja to set
	 */
	public void setIdTrabaja(int idTrabaja) {
		this.idTrabaja = idTrabaja;
	}
	/**
	 * @return the idRazonBusqueda
	 */
	public int getIdRazonBusqueda() {
		return idRazonBusqueda;
	}
	/**
	 * @param idRazonBusqueda the idRazonBusqueda to set
	 */
	public void setIdRazonBusqueda(int idRazonBusqueda) {
		this.idRazonBusqueda = idRazonBusqueda;
	}
	/**
	 * @return the inicioBusqueda
	 */
	public String getInicioBusqueda() {
		return inicioBusqueda;
	}
	/**
	 * @return the inicioBusqueda
	 */
	public Date getInicioBusqueda(boolean isWebDate) {
		if (null != inicioBusqueda) {
			if (!isWebDate)
				return mx.gob.stps.portal.web.infra.utils.Utils.convert(inicioBusqueda);
			else return mx.gob.stps.portal.web.infra.utils.Utils.convertWebDate(inicioBusqueda);
		}
		return null;
	}
	/**
	 * @param inicioBusqueda the inicioBusqueda to set
	 */
	public void setInicioBusqueda(String inicioBusqueda) {
		this.inicioBusqueda = inicioBusqueda;
	}
	/**
	 * @return the idMedioBusqueda
	 */
	public int getIdMedioBusqueda() {
		return idMedioBusqueda;
	}
	/**
	 * @param idMedioBusqueda the idMedioBusqueda to set
	 */
	public void setIdMedioBusqueda(int idMedioBusqueda) {
		this.idMedioBusqueda = idMedioBusqueda;
	}
	/**
	 * @return the estadoCivil
	 */
	public List<CatalogoOpcionVO> getEstadoCivil() {
		return estadoCivil;
	}
	/**
	 * @param estadoCivil the estadoCivil to set
	 */
	public void setEstadoCivil(List<CatalogoOpcionVO> estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	/**
	 * @return the tipoDiscapacidad
	 */
	public List<CatalogoOpcionVO> getTipoDiscapacidad() {
		return tipoDiscapacidad;
	}
	/**
	 * @param tipoDiscapacidad the tipoDiscapacidad to set
	 */
	public void setTipoDiscapacidad(List<CatalogoOpcionVO> tipoDiscapacidad) {
		this.tipoDiscapacidad = tipoDiscapacidad;
	}
	/**
	 * @return the horarioContacto
	 */
	public List<CatalogoOpcionVO> getHorarioContacto() {
		return horarioContacto;
	}
	/**
	 * @param horarioContacto the horarioContacto to set
	 */
	public void setHorarioContacto(List<CatalogoOpcionVO> horarioContacto) {
		this.horarioContacto = horarioContacto;
	}
	
	public String getHorarioLlamar() {
		this.scheduleContact = "";
		if (this.horaContactoIni > 0)
			this.scheduleContact = Utils.getHorario(this.horaContactoIni);
		if (this.horaContactoFin > 0)
			this.scheduleContact +=  " a " + Utils.getHorario(this.horaContactoFin);
		return this.scheduleContact;
	}
	
	public void setHorarioLlamar(int numeroExterior, int horaContactoFin){
		if (this.horaContactoIni > 0)
			this.scheduleContact = Utils.getHorario(this.horaContactoIni);
		if (this.horaContactoFin > 0)
			this.scheduleContact +=  " a " + Utils.getHorario(this.horaContactoFin);		
	}
	
	/**
	 * @param trabaja the trabaja to set
	 */
	public void setTrabaja(List<CatalogoOpcionVO> trabaja) {
		this.trabaja = trabaja;
	}
	/**
	 * @return the trabaja
	 */
	public List<CatalogoOpcionVO> getTrabaja() {
		return trabaja;
	}
	
	/**
	 * @return the tipoTelefonos
	 */
	public List<CatalogoOpcionVO> getTipoTelefonos() {
		return tipoTelefonos;
	}
	/**
	 * @param tipoTelefonos the tipoTelefonos to set
	 */
	public void setTipoTelefonos(List<CatalogoOpcionVO> tipoTelefonos) {
		this.tipoTelefonos = tipoTelefonos;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final int maxLen = 10;
		StringBuilder builder = new StringBuilder();
		builder.append("PerfilJB [idCandidato=");
		builder.append(idCandidato);
		builder.append(", idUsuario=");
		builder.append(idUsuario);
		builder.append(", curp=");
		builder.append(curp);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", apellido1=");
		builder.append(apellido1);
		builder.append(", apellido2=");
		builder.append(apellido2);
		builder.append(", idGenero=");
		builder.append(idGenero);
		builder.append(", genero=");
		builder.append(genero);
		builder.append(", fechaNacimiento=");
		builder.append(fechaNacimiento);
		builder.append(", edad=");
		builder.append(edad);
		builder.append(", idEntidadNacimiento=");
		builder.append(idEntidadNacimiento);
		builder.append(", entidadNacimiento=");
		builder.append(entidadNacimiento);
		builder.append(", idEstadoCivil=");
		builder.append(idEstadoCivil);
		builder.append(", idTipoDiscapacidad=");
		builder.append(idTipoDiscapacidad);
		builder.append(", idDomicilio=");
		builder.append(idDomicilio);
		builder.append(", idTipoPropietario=");
		builder.append(idTipoPropietario);
		builder.append(", idEntidad=");
		builder.append(idEntidad);
		builder.append(", idMunicipio=");
		builder.append(idMunicipio);
		builder.append(", idColonia=");
		builder.append(idColonia);
		builder.append(", calle=");
		builder.append(calle);
		builder.append(", numeroInterior=");
		builder.append(numeroInterior);
		builder.append(", numeroExterior=");
		builder.append(numeroExterior);
		builder.append(", entreCalle=");
		builder.append(entreCalle);
		builder.append(", yCalle=");
		builder.append(yCalle);
		builder.append(", idCodigoPostal=");
		builder.append(idCodigoPostal);
		builder.append(", contactoCorreo=");
		builder.append(contactoCorreo);
		builder.append(", contactoTelefono=");
		builder.append(contactoTelefono);
		builder.append(", horaContactoIni=");
		builder.append(horaContactoIni);
		builder.append(", horaContactoFin=");
		builder.append(horaContactoFin);
		builder.append(", principal=");
		builder.append(principal);
		builder.append(", correoElectronico=");
		builder.append(correoElectronico);
		builder.append(", idRecibeOferta=");
		builder.append(idRecibeOferta);
		builder.append(", idTrabaja=");
		builder.append(idTrabaja);
		builder.append(", idRazonBusqueda=");
		builder.append(idRazonBusqueda);
		builder.append(", inicioBusqueda=");
		builder.append(inicioBusqueda);
		builder.append(", idMedioBusqueda=");
		builder.append(idMedioBusqueda);
		builder.append(", estadoCivil=");
		builder.append(estadoCivil != null ? estadoCivil.subList(0,
				Math.min(estadoCivil.size(), maxLen)) : null);
		builder.append(", tipoDiscapacidad=");
		builder.append(tipoDiscapacidad != null ? tipoDiscapacidad.subList(0,
				Math.min(tipoDiscapacidad.size(), maxLen)) : null);
		builder.append(", horarioContacto=");
		builder.append(horarioContacto != null ? horarioContacto.subList(0,
				Math.min(horarioContacto.size(), maxLen)) : null);
		builder.append(", otrosMedios=");
		builder.append(otrosMedios != null ? otrosMedios.subList(0,
				Math.min(otrosMedios.size(), maxLen)) : null);
		builder.append(", tipoTelefonos=");
		builder.append(tipoTelefonos != null ? tipoTelefonos.subList(0,
				Math.min(tipoTelefonos.size(), maxLen)) : null);
		builder.append("]");
		return builder.toString();
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

	/**
	 * @return the otrosEstudiosAcademicos
	 */
	public List<CandidatoOtroEstudioVO> getOtrosEstudiosAcademicos() {
		return otrosEstudiosAcademicos;
	}

	/**
	 * @param otrosEstudiosAcademicos the otrosEstudiosAcademicos to set
	 */
	public void setOtrosEstudiosAcademicos(
			List<CandidatoOtroEstudioVO> otrosEstudiosAcademicos) {
		this.otrosEstudiosAcademicos = otrosEstudiosAcademicos;
	}

	public ExpectativaLaboralVO getExpectativaLaboral() {
		return expectativaLaboral;
	}

	public void setExpectativaLaboral(ExpectativaLaboralVO expectativaLaboral) {
		this.expectativaLaboral = expectativaLaboral;
	}

	public PerfilVO getPerfilVO() {
		return perfilVO;
	}

	public void setPerfilVO(PerfilVO perfilVO) {
		this.perfilVO = perfilVO;
	}

	public OfertaCandidatoVO getUltimaContratacion() {
		return ultimaContratacion;
	}

	public void setUltimaContratacion(OfertaCandidatoVO ultimaContratacion) {
		this.ultimaContratacion = ultimaContratacion;
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

	public int getIdLicencia() {
		return idLicencia;
	}

	public void setIdLicencia(int idLicencia) {
		this.idLicencia = idLicencia;
	}
}