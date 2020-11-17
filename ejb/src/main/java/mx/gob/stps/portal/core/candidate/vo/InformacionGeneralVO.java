package mx.gob.stps.portal.core.candidate.vo;

import java.util.Date;
import java.util.List;
import java.io.Serializable;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.persistencia.vo.CandidatoCredencialesCertificacionVO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.persistencia.vo.ExpectativaLaboralFuncionesVO;
import mx.gob.stps.portal.persistencia.vo.HistoriaLaboralFuncionesVO;

/**
 * Contiene los datos correspondientes a la secci&oacute;n &quot;Informaci&oacute;n
 *  del Candidato&quot; de las pantallas de detalle del candidato.
 * @author jose.jimenez
 *
 */
public class InformacionGeneralVO implements Serializable {
	 
	
	/**
	 * valor utilizado para la serializaci&oacute;n de las instancias del objeto
	 */
	private static final long serialVersionUID = -8322910751701991890L;
	private long id_candidato;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private Date fechaNacimiento;
	private String correoElectronico;
	private byte[] fotografia;
	private int disponibilidadViajar;
	private int disponibilidadRadicar;

	private String acceso;
	private String clave;
	private String telefono;
	private String extension;

	private List<TelefonoVO> telefonos;

	private String nombreEntidad;
	private String nombreMunicipio;
	private String nombreColonia;
	private String calle;
	private String numeroExterior;
	private String numeroInterior;
	private String codigoPostal;
	private String gradoEstudios;
	private String situacionAcademica;
	private String carreraEspecialidad;
	private String escuela;
	private List<ConocimientoHabilidadVO> conocimientos;
	private List<ConocimientoHabilidadVO> habilidades;
	private List<IdiomaVO> idiomas;
	private String idioma;
	private String puestoMayorExpr;
	private String areaLaboralMayorExpr;
	private String ocupacionMayorExpr;
	private String puestoActual;
	private String areaLaboralActual;
	private String ocupacionActual;
	private String empresaActual;
	private boolean empresaConfidencial;
	private int aniosLaborados;
	private String funciones;

	private int edadActual;
	private int idEstatus;
	private String descEstatus;
	private String urlVideoCV;
	private int urlVideoEstatus;
	private int confidencialidadCandidato;
	private int contactoCorreo;
	private int contactoTelefono;
	private int idTipoDiscapacidad;
	private String descripcionDiscapacidad;

	private int porcentajeCV;
	
	private ConocimientoComputacionVO conocimientoComputacionVO;
	
	private String aniosExperencia;
	
	private String experiencia;
	private String puestoSolicitado;

	private String ocupacionDeseada1;
	private String experienciaOcupacion1;
	private String ocupacionDeseada2;
	private String experienciaOcupacion2;

	private String salarioPretendido;
	
	private List<CatalogoOpcionVO> habilidadesCandidato;
	
	private String areaLaboralExpectativa;
	
	private String subAreaLaboralExpectativa;
	
	private String subAreaLaboralActual;
	
	private Integer disponibilidadRadicarPais;

	private List<HistoriaLaboralFuncionesVO> historiaLabfunciones;
	
	private List<CandidatoCredencialesCertificacionVO> credenciales;
	
	private List<ExpectativaLaboralFuncionesVO> expectativaLabfunciones;
	
	/**
	 * Datos para la nueva imagen del CV
	 */
	private DatosCurriculumVO datosCurriculum;
	
	public InformacionGeneralVO() {}

	/**
	 * @return the id_candidato
	 */
	public long getId_candidato() {
		return id_candidato;
	}

	/**
	 * @param id_candidato the id_candidato to set
	 */
	public void setId_candidato(long id_candidato) {
		this.id_candidato = id_candidato;
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
		this.nombre = nombre != null ? nombre : "";
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
		this.apellido1 = apellido1 != null ? apellido1 : "";
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
		this.apellido2 = apellido2 != null ? apellido2 : "";
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
		this.fechaNacimiento = fechaNacimiento;
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
		this.correoElectronico = correoElectronico != null
		                         ? correoElectronico : "";
	}

	/**
	 * @return the fotografia
	 */
	public byte[] getFotografia() {
		return fotografia;
	}

	/**
	 * @param fotografia the fotografia to set
	 */
	public void setFotografia(byte[] fotografia) {
		this.fotografia = fotografia;
	}

	/**
	 * @return the disponibilidadViajar
	 */
	public int getDisponibilidadViajar() {
		return disponibilidadViajar;
	}

	/**
	 * @param disponibilidadViajar the disponibilidadViajar to set
	 */
	public void setDisponibilidadViajar(int disponibilidadViajar) {
		this.disponibilidadViajar = disponibilidadViajar;
	}

	/**
	 * @return the disponibilidadRadicar
	 */
	public int getDisponibilidadRadicar() {
		return disponibilidadRadicar;
	}

	/**
	 * @param disponibilidadRadicar the disponibilidadRadicar to set
	 */
	public void setDisponibilidadRadicar(int disponibilidadRadicar) {
		this.disponibilidadRadicar = disponibilidadRadicar;
	}

	/**
	 * @return the acceso
	 */
	public String getAcceso() {
		return acceso;
	}

	/**
	 * @param acceso the acceso to set
	 */
	public void setAcceso(String acceso) {
		this.acceso = acceso != null ? acceso : "";
	}

	/**
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * @param clave the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave != null ? clave : "";
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono != null ? telefono : "";
	}

	/**
	 * @return the extension
	 */
	public String getExtension() {
		return extension;
	}

	/**
	 * @param extension the extension to set
	 */
	public void setExtension(String extension) {
		this.extension = extension != null ? extension.trim() : "";
	}

	public List<TelefonoVO> getTelefonos() {
		return telefonos;
	}

	public void setTelefonos(List<TelefonoVO> telefonos) {
		this.telefonos = telefonos;
	}

	/**
	 * @return the nombreEntidad
	 */
	public String getNombreEntidad() {
		return nombreEntidad;
	}

	/**
	 * @param nombreEntidad the nombreEntidad to set
	 */
	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad != null ? nombreEntidad : "";
	}

	/**
	 * @return the nombreMunicipio
	 */
	public String getNombreMunicipio() {
		return nombreMunicipio;
	}

	/**
	 * @param nombreMunicipio the nombreMunicipio to set
	 */
	public void setNombreMunicipio(String nombreMunicipio) {
		this.nombreMunicipio = nombreMunicipio != null ? nombreMunicipio : "";
	}

	/**
	 * @return the nombreColonia
	 */
	public String getNombreColonia() {
		return nombreColonia;
	}

	/**
	 * @param nombreColonia the nombreColonia to set
	 */
	public void setNombreColonia(String nombreColonia) {
		this.nombreColonia = nombreColonia != null ? nombreColonia : "";
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
		this.calle = calle != null ? calle : "";
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
		this.numeroExterior = numeroExterior != null ? numeroExterior : "";
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
		this.numeroInterior = numeroInterior != null ? numeroInterior : "";
	}

	/**
	 * @return the codigoPostal
	 */
	public String getCodigoPostal() {
		return codigoPostal;
	}

	/**
	 * @param codigoPostal the codigoPostal to set
	 */
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal != null ? codigoPostal.trim() : "";
	}

	/**
	 * @return the gradoEstudios
	 */
	public String getGradoEstudios() {
		return gradoEstudios;
	}

	/**
	 * @param gradoEstudios the gradoEstudios to set
	 */
	public void setGradoEstudios(String gradoEstudios) {
		this.gradoEstudios = gradoEstudios != null ? gradoEstudios : "";
	}

	public String getSituacionAcademica() {
		return situacionAcademica;
	}

	public void setSituacionAcademica(String situacionAcademica) {
		this.situacionAcademica = situacionAcademica;
	}

	/**
	 * @return the carreraEspecialidad
	 */
	public String getCarreraEspecialidad() {
		return carreraEspecialidad;
	}

	/**
	 * @param carreraEspecialidad the carreraEspecialidad to set
	 */
	public void setCarreraEspecialidad(String carreraEspecialidad) {
		this.carreraEspecialidad = carreraEspecialidad != null
		                           ? carreraEspecialidad : "";
	}

	/**
	 * @return the escuela
	 */
	public String getEscuela() {
		return escuela;
	}

	/**
	 * @param escuela the escuela to set
	 */
	public void setEscuela(String escuela) {
		this.escuela = escuela != null ? escuela : "";
	}

	/**
	 * @return the conocimientos
	 */
	public List<ConocimientoHabilidadVO> getConocimientos() {
		return conocimientos;
	}

	/**
	 * @param conocimientos the conocimientos to set
	 */
	public void setConocimientos(List<ConocimientoHabilidadVO> conocimientos) {
		this.conocimientos = conocimientos;
	}

	/**
	 * @return the habilidades
	 */
	public List<ConocimientoHabilidadVO> getHabilidades() {
		return habilidades;
	}

	/**
	 * @param habilidades the habilidades to set
	 */
	public void setHabilidades(List<ConocimientoHabilidadVO> habilidades) {
		this.habilidades = habilidades;
	}

	public List<IdiomaVO> getIdiomas() {
		return idiomas;
	}

	public void setIdiomas(List<IdiomaVO> idiomas) {
		this.idiomas = idiomas;
	}

	/**
	 * @return the idioma
	 */
	public String getIdioma() {
		return idioma;
	}

	/**
	 * @param idioma the idioma to set
	 */
	public void setIdioma(String idioma) {
		this.idioma = idioma != null ? idioma : "";
	}

	/**
	 * @return the puestoMayorExpr
	 */
	public String getPuestoMayorExpr() {
		return puestoMayorExpr;
	}

	/**
	 * @param puestoMayorExpr the puestoMayorExpr to set
	 */
	public void setPuestoMayorExpr(String puestoMayorExpr) {
		this.puestoMayorExpr = puestoMayorExpr != null ? puestoMayorExpr : "";
	}

	/**
	 * @return the areaLaboralMayorExpr
	 */
	public String getAreaLaboralMayorExpr() {
		return areaLaboralMayorExpr;
	}

	/**
	 * @param areaLaboralMayorExpr the areaLaboralMayorExpr to set
	 */
	public void setAreaLaboralMayorExpr(String areaLaboralMayorExpr) {
		this.areaLaboralMayorExpr = areaLaboralMayorExpr != null
		                            ? areaLaboralMayorExpr : "";
	}

	/**
	 * @return the ocupacionMayorExpr
	 */
	public String getOcupacionMayorExpr() {
		return ocupacionMayorExpr;
	}

	/**
	 * @param ocupacionMayorExpr the ocupacionMayorExpr to set
	 */
	public void setOcupacionMayorExpr(String ocupacionMayorExpr) {
		this.ocupacionMayorExpr = ocupacionMayorExpr != null
		                          ? ocupacionMayorExpr : "";
	}

	/**
	 * @return the puestoActual
	 */
	public String getPuestoActual() {
		return puestoActual;
	}

	/**
	 * @param puestoActual the puestoActual to set
	 */
	public void setPuestoActual(String puestoActual) {
		this.puestoActual = puestoActual != null ? puestoActual : "";
	}

	/**
	 * @return the areaLaboralActual
	 */
	public String getAreaLaboralActual() {
		return areaLaboralActual;
	}

	/**
	 * @param areaLaboralActual the areaLaboralActual to set
	 */
	public void setAreaLaboralActual(String areaLaboralActual) {
		this.areaLaboralActual = areaLaboralActual != null
		                         ? areaLaboralActual : "";
	}

	/**
	 * @return the ocupacionActual
	 */
	public String getOcupacionActual() {
		return ocupacionActual;
	}

	/**
	 * @param ocupacionActual the ocupacionActual to set
	 */
	public void setOcupacionActual(String ocupacionActual) {
		this.ocupacionActual = ocupacionActual != null ? ocupacionActual : "";
	}

	/**
	 * @return the empresaActual
	 */
	public String getEmpresaActual() {
		return empresaActual;
	}

	/**
	 * @param empresaActual the empresaActual to set
	 */
	public void setEmpresaActual(String empresaActual) {
		this.empresaActual = empresaActual != null ? empresaActual : "";
	}

	/**
	 * @return the empresaConfidencial
	 */
	public boolean isEmpresaConfidencial() {
		return empresaConfidencial;
	}

	/**
	 * @param empresaConfidencial the empresaConfidencial to set
	 */
	public void setEmpresaConfidencial(boolean empresaConfidencial) {
		this.empresaConfidencial = empresaConfidencial;
	}

	/**
	 * @return the aniosLaborados
	 */
	public int getAniosLaborados() {
		return aniosLaborados;
	}

	/**
	 * @param aniosLaborados the aniosLaborados to set
	 */
	public void setAniosLaborados(int aniosLaborados) {
		this.aniosLaborados = aniosLaborados;
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
		this.funciones = funciones != null ? funciones : "";
	}

	public void setEdadActual(int edadActual) {
		this.edadActual = edadActual;
	}

	public int getEdadActual() {
		return edadActual;
	}

	public void setIdEstatus(int idEstatus) {
		this.idEstatus = idEstatus;
	}

	public int getIdEstatus() {
		return idEstatus;
	}

	public void setDescEstatus(String descEstatus) {
		this.descEstatus = descEstatus;
	}

	public String getDescEstatus() {
		return descEstatus;
	}
	
	public String getUrlVideoCV() {
		return urlVideoCV;
	}

	public void setUrlVideoCV(String urlVideoCV) {
		this.urlVideoCV = urlVideoCV;
	}
	
	public int getUrlVideoEstatus() {
		return urlVideoEstatus;
	}

	public void setUrlVideoEstatus(int urlVideoEstatus) {
		this.urlVideoEstatus = urlVideoEstatus;
	}
	
	public int getConfidencialidadCandidato() {
		return confidencialidadCandidato;
	}

	public void setConfidencialidadCandidato(int confidencialidadCandidato) {
		this.confidencialidadCandidato = confidencialidadCandidato;
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

	public int getIdTipoDiscapacidad() {
		return idTipoDiscapacidad;
	}

	public void setIdTipoDiscapacidad(int idTipoDiscapacidad) {
		this.idTipoDiscapacidad = idTipoDiscapacidad;
	}

	public String getDescripcionDiscapacidad() {
		return descripcionDiscapacidad;
	}

	public void setDescripcionDiscapacidad(String descripcionDiscapacidad) {
		this.descripcionDiscapacidad = descripcionDiscapacidad;
	}

	public int getPorcentajeCV() {
		return porcentajeCV;
	}

	public void setPorcentajeCV(int porcentajeCV) {
		this.porcentajeCV = porcentajeCV;
	}

	public void setConocimientoComputacionVO(ConocimientoComputacionVO conocimientoComputacionVO) {
		this.conocimientoComputacionVO = conocimientoComputacionVO;
	}

	public ConocimientoComputacionVO getConocimientoComputacionVO() {
		return conocimientoComputacionVO;
	}


	public String getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(String experiencia) {
		this.experiencia = experiencia;
	}

	public void setAniosExperencia(String aniosExperencia) {
		this.aniosExperencia = aniosExperencia;
	}

	public String getAniosExperencia() {
		return aniosExperencia;
	}

	public void setPuestoSolicitado(String puestoSolicitado) {
		this.puestoSolicitado = puestoSolicitado;
	}

	public String getPuestoSolicitado() {
		return puestoSolicitado;
	}

	public String getOcupacionDeseada1() {
		return ocupacionDeseada1;
	}

	public void setOcupacionDeseada1(String ocupacionDeseada1) {
		this.ocupacionDeseada1 = ocupacionDeseada1;
	}

	public String getExperienciaOcupacion1() {
		return experienciaOcupacion1;
	}

	public void setExperienciaOcupacion1(String experienciaOcupacion1) {
		this.experienciaOcupacion1 = experienciaOcupacion1;
	}

	public String getOcupacionDeseada2() {
		return ocupacionDeseada2;
	}

	public void setOcupacionDeseada2(String ocupacionDeseada2) {
		this.ocupacionDeseada2 = ocupacionDeseada2;
	}

	public String getExperienciaOcupacion2() {
		return experienciaOcupacion2;
	}

	public void setExperienciaOcupacion2(String experienciaOcupacion2) {
		this.experienciaOcupacion2 = experienciaOcupacion2;
	}

	public String getSalarioPretendido() {
		return salarioPretendido;
	}

	public void setSalarioPretendido(String salarioPretendido) {
		this.salarioPretendido = salarioPretendido;
	}

	public List<CatalogoOpcionVO> getHabilidadesCandidato() {
		return habilidadesCandidato;
	}

	public void setHabilidadesCandidato(List<CatalogoOpcionVO> habilidadesCandidato) {
		this.habilidadesCandidato = habilidadesCandidato;
	}
	
	/**
	 * @return the datosCurriculum
	 */
	public DatosCurriculumVO getDatosCurriculum() {
		return datosCurriculum;
	}

	/**
	 * @param datosCurriculum the datosCurriculum to set
	 */
	public void setDatosCurriculum(DatosCurriculumVO datosCurriculum) {
		this.datosCurriculum = datosCurriculum;
	}

	public String getAreaLaboralExpectativa() {
		return areaLaboralExpectativa;
	}

	public void setAreaLaboralExpectativa(String areaLaboralExpectativa) {
		this.areaLaboralExpectativa = areaLaboralExpectativa;
	}

	public String getSubAreaLaboralExpectativa() {
		return subAreaLaboralExpectativa;
	}

	public void setSubAreaLaboralExpectativa(String subAreaLaboralExpectativa) {
		this.subAreaLaboralExpectativa = subAreaLaboralExpectativa;
	}

	public String getSubAreaLaboralActual() {
		return subAreaLaboralActual;
	}

	public void setSubAreaLaboralActual(String subAreaLaboralActual) {
		this.subAreaLaboralActual = subAreaLaboralActual;
	}

	public Integer getDisponibilidadRadicarPais() {
		return disponibilidadRadicarPais;
	}

	public void setDisponibilidadRadicarPais(Integer disponibilidadRadicarPais) {
		this.disponibilidadRadicarPais = disponibilidadRadicarPais;
	}

	public List<HistoriaLaboralFuncionesVO> getHistoriaLabfunciones() {
		return historiaLabfunciones;
	}

	public void setHistoriaLabfunciones(
			List<HistoriaLaboralFuncionesVO> historiaLabfunciones) {
		this.historiaLabfunciones = historiaLabfunciones;
	}

	public List<CandidatoCredencialesCertificacionVO> getCredenciales() {
		return credenciales;
	}

	public void setCredenciales(
			List<CandidatoCredencialesCertificacionVO> credenciales) {
		this.credenciales = credenciales;
	}

	public List<ExpectativaLaboralFuncionesVO> getExpectativaLabfunciones() {
		return expectativaLabfunciones;
	}

	public void setExpectativaLabfunciones(
			List<ExpectativaLaboralFuncionesVO> expectativaLabfunciones) {
		this.expectativaLabfunciones = expectativaLabfunciones;
	}
} 