package mx.gob.stps.portal.web.candidate.form;



import com.github.scribejava.core.oauth.OAuthService;
import mx.gob.stps.portal.core.infra.utils.Constantes.*;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.persistencia.vo.HistoriaLaboralVO;
import mx.gob.stps.portal.utils.Catalogos;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessage;
import org.brickred.socialauth.SocialAuthManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

//import mx.gob.stps.portal.core.infra.vo.CatalogoOpcionVO;

public class RegistroCandidatoForm extends ActionForm {
	private static final long serialVersionUID = 5973550263024742497L;

	private boolean registroDirecto;
	
	private String urlTerminosCondiciones;
	
	private long idCandidato;
	
	private String usuario;
	private String contrasena;
	private String confirmacion;
	private String correoElectronico;
	private String correoElectronicoGuardado;
	private int esCorreo;
	
	private String curp;
	private String nombre;
	private String apellido1;
	private String apellido2;
	
	private int genero;
	
	private int generoMasculino = GENERO.MASCULINO.getIdOpcion();
	private int generoFemenino  = GENERO.FEMENINO.getIdOpcion();	
	
	private int dia;
	private int mes;
	private int anio;
	
	private int edad;
	
	private String fechaNacimiento;
	
	private int idEntidadNacimiento;
	private String entidadNacimiento;
	
	private int discapacidad;
	// Domicilio actual
	private String calle;
	private String entreCalle;
	private String yCalle;
	private String numeroExterior;
	private String numeroInterior;
	
	private int idEntidad;
	private int idMunicipio;
	private int idColonia;
	private String codigoPostal;
	// fin Domicilio actual
	
	//Confidencialidad de datos
	private int datosConfidenciales;
	private int confidencialidadSi = CONFIDENCIALIDAD.SI.getIdOpcion();
	private boolean confidencialCURP = true;
	//Fin Confidencialidad de datos
	
	//Datos del contacto
	private String accesoCelular;
	private String accesoFijo;
	
	private int idTipoTelefonoFijo;
	private int idTipoTelefonoCelular;
	
	private int tipoTelefono;
	private String acceso;
	private String clave;
	private String telefono;
	private String extension;
	
	private int mediocontactoTel;
	private int mediocontactoCorreo;
	
	private int contactoTelSi     = CONTACTO_CORREO.SI.getIdContactoCorreo();
	private int contactoCorreoSi  = CONTACTO_CORREO.SI.getIdContactoCorreo();
	
	
	private int notificarCel;
	private int notificarCorreo;
	
	private int notificarCelSi    = RECIBE_OFERTA.TELEFONO.getIdRecibeOferta();
	private int notificarCorreoSi = RECIBE_OFERTA.CORREO.getIdRecibeOferta();
	

	// Fin Datos del contacto
	
	
	private int aceptacionTerminos;	
	private long aceptaTerminos = ACEPTACION_TERMINOS_CURRICULUM.SI.getIdOpcion();
	
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
	
	private long viajarSi  = DISPONIBILIDAD_VIAJAR.SI.getIdOpcion();
	private long viajarNo  = DISPONIBILIDAD_VIAJAR.NO.getIdOpcion();;
	private long radicarSi = DISPONIBILIDAD_RADICAR.SI.getIdOpcion();;
	private long radicarNo = DISPONIBILIDAD_RADICAR.NO.getIdOpcion();;
	
	private long trabajaSi = Catalogos.TRABAJA_ACTUALMENTE.SI.getIdOpcion();
	private long trabajaNo = Catalogos.TRABAJA_ACTUALMENTE.NO.getIdOpcion();
		
	private int diaBuscar;
	private int mesBuscar;
	private int anioBuscar;
	
	private int idMotivo;
	private String descripcionOtroMotivoBusq;

	private int idComoEnteraste;
	
	private String[] gradosDependientes;
	private String[] idiomasDependientes;

	private long[] idOtrosMedios;
	
	private List<CatalogoOpcionVO> otrosMedios;

	private boolean usuarioUnico;
	private boolean correoElectronicoUnico;
	private boolean curpUnico;
	
	private String numeroSeguroSocial;	
	private Long creditoInfonavit;
	
	private boolean edicionPerfilLaboral;

	//PROSPERA
	private int apoyoProsperaSi = APOYO_PROSPERA.SI.getIdOpcion();
	private int apoyoProsperaNo = APOYO_PROSPERA.NO.getIdOpcion();
	// ---
	private int apoyoProspera = apoyoProsperaNo;
	private String folioProspera;
	private String folioIntegranteProspera;
	
	private String pregunta;
	
	private String respuestaUsuario;

	private List<CatalogoOpcionVO> opcHabilidades;
	private long[] idHabilidades;

	//Trabajo actual o ultimo
	private String nombreSocialEmpresa;
	private String puestoDesempenado;
	private String salarioRecibido;
	private List<CatalogoOpcionVO> opcJeraquia;
	private List<CatalogoOpcionVO> opcPersonasACargo;
	private long idJerquia;
	private long idPersonaACargo;
	private String funcionesDesempenadas;
	private String anioLaboresFinal;
	private String mesLaboresFinal;
	private String diaLaboresFinal;
	private String anioLaboresInicial;
	private String mesLaboresInicial;
	private String diaLaboresInicial;
	private String confidencial;
	private String omitirTrabajoEnPerfil;
	
	private Integer ppcEstatusIMSS;
	private Date ppcFechaBajaIMSS;
	private String ppcTipoContratoIMSS;
	
	private int radioOne;
	
	private String nss;

	private boolean candidatoConalep;

	//start social
	private SocialAuthManager socialAuthManager;
	private String id;
	private OAuthService oAuthService;
	private String code;
	//fin social

	public String getAnioLaboresFinal() {
		return anioLaboresFinal;
	}

	public void setAnioLaboresFinal(String anioLaboresFinal) {
		this.anioLaboresFinal = anioLaboresFinal;
	}

	public String getMesLaboresFinal() {
		return mesLaboresFinal;
	}

	public void setMesLaboresFinal(String mesLaboresFinal) {
		this.mesLaboresFinal = mesLaboresFinal;
	}

	public String getDiaLaboresFinal() {
		return diaLaboresFinal;
	}

	public void setDiaLaboresFinal(String diaLaboresFinal) {
		this.diaLaboresFinal = diaLaboresFinal;
	}

	public String getAnioLaboresInicial() {
		return anioLaboresInicial;
	}

	public void setAnioLaboresInicial(String anioLaboresInicial) {
		this.anioLaboresInicial = anioLaboresInicial;
	}

	public String getMesLaboresInicial() {
		return mesLaboresInicial;
	}

	public void setMesLaboresInicial(String mesLaboresInicial) {
		this.mesLaboresInicial = mesLaboresInicial;
	}

	public String getDiaLaboresInicial() {
		return diaLaboresInicial;
	}

	public void setDiaLaboresInicial(String diaLaboresInicial) {
		this.diaLaboresInicial = diaLaboresInicial;
	}

	private HistoriaLaboralVO historiaLaboralVO;
	
	private Boolean PpcSdSolicitud;
	
	private boolean registroPPC;
	
	private Double latitud;
	private Double longitud;
	private boolean permisoGeolocalizacion;

	// Data from HTML Geolocation API, used to get the geographical position of a user.
	private Double positionCoordsLatitude;
	private Double positionCoordsLongitude;
	private String country;
	private String acceptLanguage;	// HTTP Header Accept-Language
	private boolean mostrarMensajeRepatriados;
	
	public ActionErrors validate() {
		ActionErrors errors = new ActionErrors();
    
		if (usuario==null || usuario.trim().isEmpty())
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.invalid", "El nombre de usuario es requerido"));

		if (contrasena==null || contrasena.trim().isEmpty())
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.invalid", "La contraseña es requerida"));
		
		if (confirmacion==null || confirmacion.trim().isEmpty())
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.invalid", "La confirmación de  Contraseña es requerida"));

		if (curp==null || curp.trim().isEmpty())
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.invalid", "La CURP es requerida"));

		if (nombre==null || nombre.trim().isEmpty())
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.invalid", "El nombre del candidato es requerido"));

		if (apellido1==null || apellido1.trim().isEmpty())
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.invalid", "El primer apellido es requerido"));

		if (dia<=0)
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.invalid", "El dia de nacimiento es requerido"));

		if (mes<=0)
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.invalid", "El mes de nacimiento es requerido"));

		if (anio<=0)
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.invalid", "El año de nacimiento es requerido"));

		if (idEntidadNacimiento<=0)
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.invalid", "La entidad de nacimiento es requerida"));

		if (genero<=0)
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.invalid", "La género es requerido"));
		
        return errors;
	}

	public int getGeneroMasculino() {
		return generoMasculino;
	}
	public int getGeneroFemenino() {
		return generoFemenino;
	}
	public int getConfidencialidadSi() {
		return confidencialidadSi;
	}
	public int getNotificarCelSi() {
		return notificarCelSi;
	}
	public int getNotificarCorreoSi() {
		return notificarCorreoSi;
	}
	public int getContactoTelSi() {
		return contactoTelSi;
	}
	public int getContactoCorreoSi() {
		return contactoCorreoSi;
	}
	public long getAceptaTerminos() {
		return aceptaTerminos;
	}
	public long getTrabajaSi() {
		return trabajaSi;
	}
	public long getTrabajaNo() {
		return trabajaNo;
	}
	public long getViajarSi() {
		return viajarSi;
	}
	public long getViajarNo() {
		return viajarNo;
	}
	public long getRadicarSi() {
		return radicarSi;
	}
	public long getRadicarNo() {
		return radicarNo;
	}
	public String getUrlTerminosCondiciones() {
		return urlTerminosCondiciones;
	}
	public void setUrlTerminosCondiciones(String urlTerminosCondiciones) {
		this.urlTerminosCondiciones = urlTerminosCondiciones;
	}
	public long getIdCandidato() {
		return idCandidato;
	}
	public void setIdCandidato(long idCandidato) {
		this.idCandidato = idCandidato;
	}
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
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
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
	public int getIdTipoTelefonoFijo() {
		return idTipoTelefonoFijo;
	}
	public void setIdTipoTelefonoFijo(int idTipoTelefonoFijo) {
		this.idTipoTelefonoFijo = idTipoTelefonoFijo;
	}
	public int getIdTipoTelefonoCelular() {
		return idTipoTelefonoCelular;
	}
	public void setIdTipoTelefonoCelular(int idTipoTelefonoCelular) {
		this.idTipoTelefonoCelular = idTipoTelefonoCelular;
	}
	public String getAccesoCelular() {
		return accesoCelular;
	}
	public void setAccesoCelular(String accesoCelular) {
		this.accesoCelular = accesoCelular;
	}
	public String getAccesoFijo() {
		return accesoFijo;
	}
	public void setAccesoFijo(String accesoFijo) {
		this.accesoFijo = accesoFijo;
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
	public int getDiaBuscar() {
		return diaBuscar;
	}
	public void setDiaBuscar(int diaBuscar) {
		this.diaBuscar = diaBuscar;
	}
	public int getMesBuscar() {
		return mesBuscar;
	}
	public void setMesBuscar(int mesBuscar) {
		this.mesBuscar = mesBuscar;
	}
	public int getAnioBuscar() {
		return anioBuscar;
	}
	public void setAnioBuscar(int anioBuscar) {
		this.anioBuscar = anioBuscar;
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
	public String[] getGradosDependientes() {
		return gradosDependientes;
	}
	public void setGradosDependientes(String[] gradosDependientes) {
		this.gradosDependientes = gradosDependientes;
	}
	public String[] getIdiomasDependientes() {
		return idiomasDependientes;
	}
	public void setIdiomasDependientes(String[] idiomasDependientes) {
		this.idiomasDependientes = idiomasDependientes;
	}
	public List<CatalogoOpcionVO> getOtrosMedios() {
		return otrosMedios;
	}
	public void setOtrosMedios(List<CatalogoOpcionVO> otrosMedios) {
		this.otrosMedios = otrosMedios;
	}
	public long[] getIdOtrosMedios() {
		return idOtrosMedios;
	}
	public void setIdOtrosMedios(long[] idOtrosMedios) {
		this.idOtrosMedios = idOtrosMedios;
	}
	public boolean isUsuarioUnico() {
		return usuarioUnico;
	}
	public void setUsuarioUnico(boolean usuarioUnico) {
		this.usuarioUnico = usuarioUnico;
	}
	public boolean isCorreoElectronicoUnico() {
		return correoElectronicoUnico;
	}
	public void setCorreoElectronicoUnico(boolean correoElectronicoUnico) {
		this.correoElectronicoUnico = correoElectronicoUnico;
	}
	public boolean isCurpUnico() {
		return curpUnico;
	}
	public void setCurpUnico(boolean curpUnico) {
		this.curpUnico = curpUnico;
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
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public boolean isRegistroDirecto() {
		return registroDirecto;
	}
	public void setRegistroDirecto(boolean registroDirecto) {
		this.registroDirecto = registroDirecto;
	}
	
	public boolean isEdicionPerfilLaboral() {
		return edicionPerfilLaboral;
	}
	public void setEdicionPerfilLaboral(boolean edicionPerfilLaboral) {
		this.edicionPerfilLaboral = edicionPerfilLaboral;
	}
	
	public long getApoyoProsperaSi() {
		return apoyoProsperaSi;
	}

	public void setApoyoProsperaSi(int apoyoProsperaSi) {
		this.apoyoProsperaSi = apoyoProsperaSi;
	}

	public long getApoyoProsperaNo() {
		return apoyoProsperaNo;
	}

	public void setApoyoProsperaNo(int apoyoProsperaNo) {
		this.apoyoProsperaNo = apoyoProsperaNo;
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

	@Override
	public String toString() {
		return "RegistroCandidatoForm [urlTerminosCondiciones="
				+ urlTerminosCondiciones + ", idCandidato=" + idCandidato
				+ ", usuario=" + usuario + ", contrasena=" + contrasena
				+ ", confirmacion=" + confirmacion + ", correoElectronico="
				+ correoElectronico + ", esCorreo=" + esCorreo + ", curp="
				+ curp + ", nombre=" + nombre + ", apellido1=" + apellido1
				+ ", apellido2=" + apellido2 + ", genero=" + genero + ", dia="
				+ dia + ", mes=" + mes + ", anio=" + anio
				+ ", fechaNacimiento=" + fechaNacimiento
				+ ", idEntidadNacimiento=" + idEntidadNacimiento
				+ ", entidadNacimiento=" + entidadNacimiento
				+ ", discapacidad=" + discapacidad + ", calle=" + calle
				+ ", entreCalle=" + entreCalle + ", yCalle=" + yCalle
				+ ", numeroExterior=" + numeroExterior + ", numeroInterior="
				+ numeroInterior + ", idEntidad=" + idEntidad
				+ ", idMunicipio=" + idMunicipio + ", idColonia=" + idColonia
				+ ", codigoPostal=" + codigoPostal + ", accesoCelular="
				+ accesoCelular + ", accesoFijo=" + accesoFijo
				+ ", idTipoTelefonoFijo=" + idTipoTelefonoFijo
				+ ", idTipoTelefonoCelular=" + idTipoTelefonoCelular
				+ ", tipoTelefono=" + tipoTelefono + ", acceso=" + acceso
				+ ", clave=" + clave + ", telefono=" + telefono
				+ ", extension=" + extension + ", mediocontactoTel="
				+ mediocontactoTel + ", mediocontactoCorreo="
				+ mediocontactoCorreo + ", notificarCel=" + notificarCel
				+ ", notificarCorreo=" + notificarCorreo
				+ ", datosConfidenciales=" + datosConfidenciales
				+ ", aceptacionTerminos=" + aceptacionTerminos
				+ ", idGradoEstudio=" + idGradoEstudio
				+ ", idCarreraEspecialidad=" + idCarreraEspecialidad
				+ ", idSituacionAcademica=" + idSituacionAcademica
				+ ", idIdioma=" + idIdioma + ", idCertificacion="
				+ idCertificacion + ", idDominio=" + idDominio
				+ ", conocimientoCompNinguno=" + conocimientoCompNinguno
				+ ", conocimientoCompProcesadorTxt="
				+ conocimientoCompProcesadorTxt + ", conocimientoCompHojaCal="
				+ conocimientoCompHojaCal + ", conocimientoCompInternet="
				+ conocimientoCompInternet + ", conocimientoCompRedes="
				+ conocimientoCompRedes + ", conocimientoCompOtros="
				+ conocimientoCompOtros + ", idExperienciaTotal="
				+ idExperienciaTotal + ", idExperiencia="
				+ idExperiencia2 +", descripcionExperiencia="
				+ descripcionExperiencia + ", idOcupacionDeseada="
				+ idOcupacionDeseada + ", salarioPretendido="
				+ salarioPretendido + ", idTipoEmpleoDeseado="
				+ idTipoEmpleoDeseado + ", idTipoContratoDeseado="
				+ idTipoContratoDeseado + ", viajar=" + viajar + ", radicar="
				+ radicar + ", trabajas=" + trabajas + ", diaBuscar="
				+ diaBuscar + ", mesBuscar=" + mesBuscar + ", anioBuscar="
				+ anioBuscar + ", idMotivo=" + idMotivo + ", idComoEnteraste="
				+ idComoEnteraste + ", gradosDependientes="
				+ Arrays.toString(gradosDependientes)
				+ ", idiomasDependientes="
				+ Arrays.toString(idiomasDependientes) + ", idOtrosMedios="
				+ Arrays.toString(idOtrosMedios) + ", otrosMedios="
				+ otrosMedios + ", usuarioUnico=" + usuarioUnico
				+ ", correoElectronicoUnico=" + correoElectronicoUnico
				+ ", curpUnico=" + curpUnico + ", numeroSeguroSocial=" + numeroSeguroSocial
				+ ", creditoInfonavit=" + creditoInfonavit + "]";
	}


	public void reset() {
		urlTerminosCondiciones = null;
		
		registroPPC = false;
		
		idCandidato = 0;
		
		usuario = null;
		contrasena = null;
		confirmacion = null;
		correoElectronico = null;
		esCorreo = 0;
		
		curp = null;
		this.nss = null; 
		nombre = null;
		apellido1 = null;
		apellido2 = null;
		
		genero = 0;
		
		dia = 0;
		mes = 0;
		anio = 0;
		
		fechaNacimiento = null;
		
		idEntidadNacimiento = 0;
		entidadNacimiento = null;
		
		discapacidad = 0;
		
		calle = null;
		entreCalle = null;
		yCalle = null;
		numeroExterior = null;
		numeroInterior = null;
		
		idEntidad = 0;
		idMunicipio = 0;
		idColonia = 0;
		codigoPostal = null;
		
		accesoCelular = null;
		accesoFijo = null;
		
		idTipoTelefonoFijo = 0;
		idTipoTelefonoCelular = 0;
		
		tipoTelefono = 0;
		acceso = null;
		clave = null;
		telefono = null;
		extension = null;
		
		mediocontactoTel = 0;
		mediocontactoCorreo = 0;
		
		notificarCel = 0;
		notificarCorreo = 0;
		
		datosConfidenciales = 0;
		aceptacionTerminos = 0;
		
		idGradoEstudio = 0;
		idCarreraEspecialidad = 0;
		idSituacionAcademica = 0;
		idIdioma = 0;
		idCertificacion = 0;
		idDominio = 0;

		conocimientoCompNinguno = 0;
		conocimientoCompProcesadorTxt = 0;
		conocimientoCompHojaCal = 0;
		conocimientoCompInternet = 0;
		conocimientoCompRedes = 0;
		conocimientoCompOtros = null;
		
		idExperienciaTotal = 0;
		descripcionExperiencia = null;
		
		idOcupacionDeseada = 0;
		salarioPretendido = 0;
		idTipoEmpleoDeseado = 0;
		idTipoContratoDeseado = 0;
		
		viajar = 0;
		radicar = 0;
		trabajas = 0;
		
		diaBuscar = 0;
		mesBuscar = 0;
		anioBuscar = 0;
		
		idMotivo = 0;
		descripcionOtroMotivoBusq = null;

		idComoEnteraste = 0;
		
		gradosDependientes = null;
		idiomasDependientes = null;

		idOtrosMedios = null;
		
		otrosMedios = null;

		usuarioUnico = true;
		correoElectronicoUnico = true;
		curpUnico = true;
		
		numeroSeguroSocial = null;
		creditoInfonavit = null;
		
		registroDirecto = false;
		
		edicionPerfilLaboral = false;
		
		apoyoProspera = APOYO_PROSPERA.NO.getIdOpcion();
		folioProspera = null;
		folioIntegranteProspera = null;

		latitud=null;
		longitud=null;
		permisoGeolocalizacion=false;

		candidatoConalep = false;

		// Data from HTML Geolocation API, used to get the geographical position of a user.
		this.positionCoordsLatitude = null;
		this.positionCoordsLongitude = null;
		this.country = null;
		this.acceptLanguage = null;
		this.mostrarMensajeRepatriados = false;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public String getRespuestaUsuario() {
		return respuestaUsuario;
	}

	public void setRespuestaUsuario(String respuestaUsuario) {
		this.respuestaUsuario = respuestaUsuario;
	}

	public List<CatalogoOpcionVO> getOpcHabilidades() {
		if(opcHabilidades == null){
			opcHabilidades = new ArrayList<CatalogoOpcionVO>();
		}
		return opcHabilidades;
	}

	public void setOpcHabilidades(List<CatalogoOpcionVO> opcHabilidades) {
		this.opcHabilidades = opcHabilidades;
	}

	public long[] getIdHabilidades() {
		return idHabilidades;
	}

	public void setIdHabilidades(long[] idHabilidades) {
		this.idHabilidades = idHabilidades;
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

	public boolean getConfidencialCURP() {
		return confidencialCURP;
	}

	public void setConfidencialCURP(boolean confidencialCURP) {
		this.confidencialCURP = confidencialCURP;
	}

	public boolean getNoConfidencialCURP() {
		return !confidencialCURP;
	}

	public HistoriaLaboralVO getHistoriaLaboralVO() {
		if(historiaLaboralVO == null){
			historiaLaboralVO = new HistoriaLaboralVO();
		}
		return historiaLaboralVO;
	}

	public void setHistoriaLaboralVO(HistoriaLaboralVO historiaLaboralVO) {
		this.historiaLaboralVO = historiaLaboralVO;
	}

	public Boolean getPpcSdSolicitud() {
		return PpcSdSolicitud;
	}

	public void setPpcSdSolicitud(Boolean PpcSdSolicitud) {
		this.PpcSdSolicitud = PpcSdSolicitud;
	}

	public List<CatalogoOpcionVO> getOpcJeraquia() {
		return opcJeraquia;
	}

	public void setOpcJeraquia(List<CatalogoOpcionVO> opcJeraquia) {
		this.opcJeraquia = opcJeraquia;
	}

	public List<CatalogoOpcionVO> getOpcPersonasACargo() {
		return opcPersonasACargo;
	}

	public void setOpcPersonasACargo(List<CatalogoOpcionVO> opcPersonasACargo) {
		this.opcPersonasACargo = opcPersonasACargo;
	}

	public long getIdJerquia() {
		return idJerquia;
	}

	public void setIdJerquia(long idJerquia) {
		this.idJerquia = idJerquia;
	}

	public long getIdPersonaACargo() {
		return idPersonaACargo;
	}

	public void setIdPersonaACargo(long idPersonaACargo) {
		this.idPersonaACargo = idPersonaACargo;
	}

	public String getNombreSocialEmpresa() {
		return nombreSocialEmpresa;
	}

	public void setNombreSocialEmpresa(String nombreSocialEmpresa) {
		this.nombreSocialEmpresa = nombreSocialEmpresa;
	}

	public String getPuestoDesempenado() {
		return puestoDesempenado;
	}

	public void setPuestoDesempenado(String puestoDesempenado) {
		this.puestoDesempenado = puestoDesempenado;
	}

	public String getSalarioRecibido() {
		return salarioRecibido;
	}

	public void setSalarioRecibido(String salarioRecibido) {
		this.salarioRecibido = salarioRecibido;
	}

	public String getFuncionesDesempenadas() {
		return funcionesDesempenadas;
	}

	public void setFuncionesDesempenadas(String funcionesDesempenadas) {
		this.funcionesDesempenadas = funcionesDesempenadas;
	}

	public String getOmitirTrabajoEnPerfil() {
		return omitirTrabajoEnPerfil;
	}

	public void setOmitirTrabajoEnPerfil(String omitirTrabajoEnPerfil) {
		this.omitirTrabajoEnPerfil = omitirTrabajoEnPerfil;
	}

	public boolean isRegistroPPC() {
		return registroPPC;
	}

	public void setRegistroPPC(boolean registroPPC) {
		this.registroPPC = registroPPC;
	}

	public String getNss() {
		return nss;
	}

	public void setNss(String nss) {
		this.nss = nss;
	}


	public String getCorreoElectronicoGuardado() {
		return correoElectronicoGuardado;
	}

	public void setCorreoElectronicoGuardado(String correoElectronicoGuardado) {
		this.correoElectronicoGuardado = correoElectronicoGuardado;
	}

	public String getConfidencial() {
		return confidencial;
	}

	public void setConfidencial(String confidencial) {
		this.confidencial = confidencial;
	}

	public Integer getPpcEstatusIMSS() {
		return ppcEstatusIMSS;
	}

	public void setPpcEstatusIMSS(Integer ppcEstatusIMSS) {
		this.ppcEstatusIMSS = ppcEstatusIMSS;
	}

	public Date getPpcFechaBajaIMSS() {
		return ppcFechaBajaIMSS;
	}

	public void setPpcFechaBajaIMSS(Date ppcFechaBajaIMSS) {
		this.ppcFechaBajaIMSS = ppcFechaBajaIMSS;
	}

	public String getPpcTipoContratoIMSS() {
		return ppcTipoContratoIMSS;
	}

	public void setPpcTipoContratoIMSS(String ppcTipoContratoIMSS) {
		this.ppcTipoContratoIMSS = ppcTipoContratoIMSS;
	}

	public int getRadioOne() {
		return radioOne;
	}

	public void setRadioOne(int radioOne) {
		this.radioOne = radioOne;
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

	public boolean isPermisoGeolocalizacion() {
		return permisoGeolocalizacion;
	}

	public void setPermisoGeolocalizacion(boolean permisoGeolocalizacion) {
		this.permisoGeolocalizacion = permisoGeolocalizacion;
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

	public boolean getMostrarMensajeRepatriados() {
		return mostrarMensajeRepatriados;
	}

	public void setMostrarMensajeRepatriados(boolean mostrarMensajeRepatriados) {
		this.mostrarMensajeRepatriados = mostrarMensajeRepatriados;
	}

	//start social

	public SocialAuthManager getSocialAuthManager() {
		return socialAuthManager;
	}

	public void setSocialAuthManager(SocialAuthManager socialAuthManager) {
		this.socialAuthManager = socialAuthManager;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public OAuthService getoAuthService() {
		return oAuthService;
	}

	public void setoAuthService(OAuthService oAuthService) {
		this.oAuthService = oAuthService;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	//fin social


	public boolean isCandidatoConalep() {
		return candidatoConalep;
	}

	public void setCandidatoConalep(boolean candidatoConalep) {
		this.candidatoConalep = candidatoConalep;
	}
}
