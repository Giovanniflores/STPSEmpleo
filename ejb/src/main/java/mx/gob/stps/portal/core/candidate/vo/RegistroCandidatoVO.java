package mx.gob.stps.portal.core.candidate.vo;

import java.io.Serializable;
import java.util.Date;

public class RegistroCandidatoVO implements Serializable {
	private static final long serialVersionUID = -6060863218988774490L;

	private String usuario;
	private String contrasena;
	private String confirmacion;
	private String correoElectronico;
	private int esCorreo;
	
	private String curp;
	private String nombre;
	private String apellido1;
	private String apellido2;
	
	private int genero;

	private Date fechaNacimiento;
	
	private int idEntidadNacimiento;
	private String entidadNacimiento;
	
	private int discapacidad;
	
	private String calle;
	private String entreCalle;
	private String yCalle;
	private String numeroExterior;
	private String numeroInterior;
	
	private int idEntidad;
	private int idMunicipio;
	private int idColonia;
	private String codigoPostal;
	
	private int tipoTelefono;
	private String acceso;
	private String clave;
	private String telefono;
	private String extension;
	
	private int mediocontactoTel;
	private int mediocontactoCorreo;
	
	private int notificarCel;
	private int notificarCorreo;
	
	private int datosConfidenciales;
	private int aceptacionTerminos;
	
	private int idGradoEstudio;
	private int idCarreraEspecialidad;
	private int idSituacionAcademica;
	private int idIdioma;
	private int idCertificacion;
	private int idDominio;

	private int conocimientoCompNinguno;
	private int conocimientoCompProcesadorTxt;
	private int conocimientoCompHojaCal;
	private int conocimientoCompInternet;
	private int conocimientoCompRedes;
	private String conocimientoCompOtros;
	
	private int idExperienciaTotal;
	private String descripcionExperiencia;
	
	private int idOcupacionDeseada;
	private double salarioPretendido;
	private int idTipoEmpleoDeseado;
	private int idTipoContratoDeseado;
	
	// Trabajo deseado 2
	private int idOcupacionDeseada2;
	private int idExperiencia2;
		
	
	private int viajar;
	private int radicar;
	private int trabajas;
	
	private Date fechaInicioBusqueda;
	
	private int idMotivo;
	private String descripcionOtroMotivoBusq;

	private int idComoEnteraste;
	
	private long[] idOtrosMedios;
	
	private int apoyoProspera;
	private String folioProspera;
	private String folioIntegranteProspera;

	private String numeroSeguroSocial;	
	private Long creditoInfonavit;
	
	private long[] idHabilidades;
	private mx.gob.stps.portal.persistencia.vo.HistoriaLaboralVO historiaLaboralVO;
	
	/**Para lo de geolocalizacion**/
	private Double latitud;
	private Double longitud;

	// Data from HTML Geolocation API, used to get the geographical position of a user.
	private Double positionCoordsLatitude;
	private Double positionCoordsLongitude;
	private String country;
	private String acceptLanguage;	// HTTP Header Accept-Language
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public String getConfirmacion() {
		return confirmacion;
	}
	public void setConfirmacion(String confirmacion) {
		this.confirmacion = confirmacion;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	public int getEsCorreo() {
		return esCorreo;
	}
	public void setEsCorreo(int esCorreo) {
		this.esCorreo = esCorreo;
	}
	public boolean isCorreo() {
		return esCorreo==1;
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
	public int getGenero() {
		return genero;
	}
	public void setGenero(int genero) {
		this.genero = genero;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public int getIdEntidadNacimiento() {
		return idEntidadNacimiento;
	}
	public void setIdEntidadNacimiento(int idEntidadNacimiento) {
		this.idEntidadNacimiento = idEntidadNacimiento;
	}
	public String getEntidadNacimiento() {
		return entidadNacimiento;
	}
	public void setEntidadNacimiento(String entidadNacimiento) {
		this.entidadNacimiento = entidadNacimiento;
	}	
	public int getDiscapacidad() {
		return discapacidad;
	}
	public void setDiscapacidad(int discapacidad) {
		this.discapacidad = discapacidad;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
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
	public String getNumeroExterior() {
		return numeroExterior;
	}
	public void setNumeroExterior(String numeroExterior) {
		this.numeroExterior = numeroExterior;
	}
	public String getNumeroInterior() {
		return numeroInterior;
	}
	public void setNumeroInterior(String numeroInterior) {
		this.numeroInterior = numeroInterior;
	}
	public int getIdEntidad() {
		return idEntidad;
	}
	public void setIdEntidad(int idEntidad) {
		this.idEntidad = idEntidad;
	}
	public int getIdMunicipio() {
		return idMunicipio;
	}
	public void setIdMunicipio(int idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
	public int getIdColonia() {
		return idColonia;
	}
	public void setIdColonia(int idColonia) {
		this.idColonia = idColonia;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public int getTipoTelefono() {
		return tipoTelefono;
	}
	public void setTipoTelefono(int tipoTelefono) {
		this.tipoTelefono = tipoTelefono;
	}
	public String getAcceso() {
		return acceso;
	}
	public void setAcceso(String acceso) {
		this.acceso = acceso;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public int getMediocontactoTel() {
		return mediocontactoTel;
	}
	public void setMediocontactoTel(int mediocontactoTel) {
		this.mediocontactoTel = mediocontactoTel;
	}
	public int getMediocontactoCorreo() {
		return mediocontactoCorreo;
	}
	public void setMediocontactoCorreo(int mediocontactoCorreo) {
		this.mediocontactoCorreo = mediocontactoCorreo;
	}
	public int getNotificarCel() {
		return notificarCel;
	}
	public void setNotificarCel(int notificarCel) {
		this.notificarCel = notificarCel;
	}
	public int getNotificarCorreo() {
		return notificarCorreo;
	}
	public void setNotificarCorreo(int notificarCorreo) {
		this.notificarCorreo = notificarCorreo;
	}
	public int getDatosConfidenciales() {
		return datosConfidenciales;
	}
	public void setDatosConfidenciales(int datosConfidenciales) {
		this.datosConfidenciales = datosConfidenciales;
	}
	public int getAceptacionTerminos() {
		return aceptacionTerminos;
	}
	public void setAceptacionTerminos(int aceptacionTerminos) {
		this.aceptacionTerminos = aceptacionTerminos;
	}
	public int getIdGradoEstudio() {
		return idGradoEstudio;
	}
	public void setIdGradoEstudio(int idGradoEstudio) {
		this.idGradoEstudio = idGradoEstudio;
	}
	public int getIdCarreraEspecialidad() {
		return idCarreraEspecialidad;
	}
	public void setIdCarreraEspecialidad(int idCarreraEspecialidad) {
		this.idCarreraEspecialidad = idCarreraEspecialidad;
	}
	public int getIdSituacionAcademica() {
		return idSituacionAcademica;
	}
	public void setIdSituacionAcademica(int idSituacionAcademica) {
		this.idSituacionAcademica = idSituacionAcademica;
	}
	public int getIdIdioma() {
		return idIdioma;
	}
	public void setIdIdioma(int idIdioma) {
		this.idIdioma = idIdioma;
	}
	public int getIdCertificacion() {
		return idCertificacion;
	}
	public void setIdCertificacion(int idCertificacion) {
		this.idCertificacion = idCertificacion;
	}
	
	public int getIdDominio() {
		return idDominio;
	}
	public void setIdDominio(int idDominio) {
		this.idDominio = idDominio;
	}
	public int getConocimientoCompNinguno() {
		return conocimientoCompNinguno;
	}
	public void setConocimientoCompNinguno(int conocimientoCompNinguno) {
		this.conocimientoCompNinguno = conocimientoCompNinguno;
	}
	public int getConocimientoCompProcesadorTxt() {
		return conocimientoCompProcesadorTxt;
	}
	public void setConocimientoCompProcesadorTxt(int conocimientoCompProcesadorTxt) {
		this.conocimientoCompProcesadorTxt = conocimientoCompProcesadorTxt;
	}
	public int getConocimientoCompHojaCal() {
		return conocimientoCompHojaCal;
	}
	public void setConocimientoCompHojaCal(int conocimientoCompHojaCal) {
		this.conocimientoCompHojaCal = conocimientoCompHojaCal;
	}
	public int getConocimientoCompInternet() {
		return conocimientoCompInternet;
	}
	public void setConocimientoCompInternet(int conocimientoCompInternet) {
		this.conocimientoCompInternet = conocimientoCompInternet;
	}
	public int getConocimientoCompRedes() {
		return conocimientoCompRedes;
	}
	public void setConocimientoCompRedes(int conocimientoCompRedes) {
		this.conocimientoCompRedes = conocimientoCompRedes;
	}
	public String getConocimientoCompOtros() {
		return conocimientoCompOtros;
	}
	public void setConocimientoCompOtros(String conocimientoCompOtros) {
		this.conocimientoCompOtros = conocimientoCompOtros;
	}
	public int getIdExperienciaTotal() {
		return idExperienciaTotal;
	}
	public void setIdExperienciaTotal(int idExperienciaTotal) {
		this.idExperienciaTotal = idExperienciaTotal;
	}
	public String getDescripcionExperiencia() {
		return descripcionExperiencia;
	}
	public void setDescripcionExperiencia(String descripcionExperiencia) {
		this.descripcionExperiencia = descripcionExperiencia;
	}
	public int getIdOcupacionDeseada() {
		return idOcupacionDeseada;
	}
	public void setIdOcupacionDeseada(int idOcupacionDeseada) {
		this.idOcupacionDeseada = idOcupacionDeseada;
	}
	public double getSalarioPretendido() {
		return salarioPretendido;
	}
	public void setSalarioPretendido(double salarioPretendido) {
		this.salarioPretendido = salarioPretendido;
	}
	public int getIdTipoEmpleoDeseado() {
		return idTipoEmpleoDeseado;
	}
	public void setIdTipoEmpleoDeseado(int idTipoEmpleoDeseado) {
		this.idTipoEmpleoDeseado = idTipoEmpleoDeseado;
	}
	public int getIdTipoContratoDeseado() {
		return idTipoContratoDeseado;
	}
	public void setIdTipoContratoDeseado(int idTipoContratoDeseado) {
		this.idTipoContratoDeseado = idTipoContratoDeseado;
	}
	public int getViajar() {
		return viajar;
	}
	public void setViajar(int viajar) {
		this.viajar = viajar;
	}
	public int getRadicar() {
		return radicar;
	}
	public void setRadicar(int radicar) {
		this.radicar = radicar;
	}
	public int getTrabajas() {
		return trabajas;
	}
	public void setTrabajas(int trabajas) {
		this.trabajas = trabajas;
	}
	public Date getFechaInicioBusqueda() {
		return fechaInicioBusqueda;
	}
	public void setFechaInicioBusqueda(Date fechaInicioBusqueda) {
		this.fechaInicioBusqueda = fechaInicioBusqueda;
	}
	public int getIdMotivo() {
		return idMotivo;
	}
	public void setIdMotivo(int idMotivo) {
		this.idMotivo = idMotivo;
	}
	public String getDescripcionOtroMotivoBusq() {
		return descripcionOtroMotivoBusq;
	}
	public void setDescripcionOtroMotivoBusq(String descripcionOtroMotivoBusq) {
		this.descripcionOtroMotivoBusq = descripcionOtroMotivoBusq;
	}
	public int getIdComoEnteraste() {
		return idComoEnteraste;
	}
	public void setIdComoEnteraste(int idComoEnteraste) {
		this.idComoEnteraste = idComoEnteraste;
	}
	public long[] getIdOtrosMedios() {
		return idOtrosMedios;
	}
	public void setIdOtrosMedios(long[] idOtrosMedios) {
		this.idOtrosMedios = idOtrosMedios;
	}
	public String getNumeroSeguroSocial() {
		return numeroSeguroSocial;
	}
	public void setNumeroSeguroSocial(String numeroSeguroSocial) {
		this.numeroSeguroSocial = numeroSeguroSocial;
	}
	public Long getCreditoInfonavit() {
		return creditoInfonavit;
	}
	public void setCreditoInfonavit(Long creditoInfonavit) {
		this.creditoInfonavit = creditoInfonavit;
	}
	public int getApoyoProspera() {
		return apoyoProspera;
	}
	public void setApoyoProspera(int apoyoProspera) {
		this.apoyoProspera = apoyoProspera;
	}

	public String getFolioProspera() {
		return folioProspera;
	}

	public void setFolioProspera(String folioProspera) {
		this.folioProspera = folioProspera;
	}

	public String getFolioIntegranteProspera() {
		return folioIntegranteProspera;
	}

	public void setFolioIntegranteProspera(String folioIntegranteProspera) {
		this.folioIntegranteProspera = folioIntegranteProspera;
	}

	public int getIdOcupacionDeseada2() {
		return idOcupacionDeseada2;
	}
	public void setIdOcupacionDeseada2(int idOcupacionDeseada2) {
		this.idOcupacionDeseada2 = idOcupacionDeseada2;
	}
	public int getIdExperiencia2() {
		return idExperiencia2;
	}
	public void setIdExperiencia2(int idExperiencia2) {
		this.idExperiencia2 = idExperiencia2;
	}
	public long[] getIdHabilidades() {
		return idHabilidades;
	}
	public void setIdHabilidades(long[] idHabilidades) {
		this.idHabilidades = idHabilidades;
	}
	public mx.gob.stps.portal.persistencia.vo.HistoriaLaboralVO getHistorialLaboralVO() {
		return historiaLaboralVO;
	}
	public void setHistoriaLaboralVO(mx.gob.stps.portal.persistencia.vo.HistoriaLaboralVO historiaLaboralVO) {
		this.historiaLaboralVO = historiaLaboralVO;
	}
	public Double getLatitud() {
		return latitud;
	}
	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}
	public Double getLongitud() {
		return longitud;
	}
	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	public Double getPositionCoordsLatitude() {
		return positionCoordsLatitude;
	}
	public void setPositionCoordsLatitude(Double positionCoordsLatitude) {
		this.positionCoordsLatitude = positionCoordsLatitude;
	}
	public Double getPositionCoordsLongitude() {
		return positionCoordsLongitude;
	}
	public void setPositionCoordsLongitude(Double positionCoordsLongitude) {
		this.positionCoordsLongitude = positionCoordsLongitude;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	public String getAcceptLanguage() {
		return acceptLanguage;
	}
	public void setAcceptLanguage(String acceptLanguage) {
		this.acceptLanguage = acceptLanguage;
	}
}