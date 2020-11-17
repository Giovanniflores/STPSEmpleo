/**
 * 
 */
package mx.gob.stps.portal.core.candidate.vo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import mx.gob.stps.portal.core.infra.utils.Constantes.GENERO;
//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;

/**
 * @author Felipe Juárez Ramírez
 *
 */
public class PerfilVO implements Serializable {

	/**
	 *  
	 */
	private static final long serialVersionUID = -7276926079043319729L;
	
	/*Id's candidato*/
	private long idCandidato;
	private long idUsuario;
	private Long idOficina;
	//private long idTipoUsuario;
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
	private long idMunicipio;
	private long idColonia;
	private String calle;
	private String numeroInterior;
	private String numeroExterior;
	private String entreCalle;
	private String yCalle;
	private String codigoPostal;
	private long idLocalidad;
	private String domicilioRef;
	private String ultimaActualizacion;
	/*Datos de contacto*/
	private int confidencialidad;
	private int contactoCorreo;
	private int contactoTelefono;
	private long horaContactoIni;
	private long horaContactoFin;
	/*Datos del Telefono*/
	private TelefonoVO principal;
	private TelefonoVO secundario;
	private TelefonoVO tercero;
	/*Correo electronico*/
	private int cambioCorreo;
	private String correoAux;
	private String correoElectronico;
	/*Situacion Laboral*/
	private long idRecibeOferta;
	private int idTrabaja;
	private long idRazonBusqueda;
	private String descripcionOtroMotivoBusq;
	private Date inicioBusqueda;//Fecha de inicio de busqueda de empleo
	private long idMedioBusqueda;
	private long idMedioPortal;
	private long[] idOtrosMedios;
	
	/**
	 * 	Escolaridad principal
	 **/
	private int sinEstudios;
	private int sinExperiencia;	
	/**
	 * Computación Básica
	 **/
	private int computacionBasica;
	private long idExperienciaCompu;
	private long idDominioCompu;
	private long idExperienciaOffice;
	private long idDominioOffice;
	private long idExperienciaInternet;
	private long idDominioInternet;
	/**
	 * Bandera que indica si se tiene conocimientos en computación avanzada.
	 **/
	private int computacionAvanzada;
	/**
	 * 	Experiencia laboral principal
	 **/
	private long idExperienciaTotal;
	private long idSectorMayorExpr;
	private String puestoMayorExpr;
	private long idAreaLaboralMayorExpr;
	private long idOcupacionMayorExpr;
	private String experiencia;
	private int idLicencia;
	
	/**
	 * Expectativas laborales
	 **/
	private int disponibilidadViajar;
	private int disponibilidadRadicar;
	private int disponibilidadRadicarPais;
	
	private Integer apoyoProspera;
	private String folioProspera;
	private String folioIntegranteProspera;

	/**
	 * Estilo Curriculum Vitae
	 **/
	private int estiloCV;
	
	/**
	 * Geolocalizacion
	 */
	private Double latitud;
	private Double longitud;
	
	//PPC
	private Integer idEstatusPPC;
	
	public int getEstiloCV() {
		return estiloCV;
	}
	
	public void setEstiloCV(int estiloCV) {
		this.estiloCV = estiloCV;
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
		if (idUsuario != 0)
			this.idUsuario = idUsuario;
	}
	/*public void setIdTipoUsuario(long idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}
	public long getIdTipoUsuario() {
		return idTipoUsuario;
	}*/
	/**
	 * @return the curp
	 */
	public String getCurp() {
		return curp;
	}
	/**
	 * @param candidato the curp to set
	 */
	public void setCurp(String candidato) {
		if (candidato != null)
			this.curp = candidato;
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
		if (nombre != null)
			this.nombre = nombre;
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
		if (apellido1 != null)
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
		if (apellido2 != null)
			this.apellido2 = apellido2;
	}
	/**
	 * @return the idGenero
	 */
	public int getIdGenero() {
		return idGenero;
	}
	/**
	 * @param genero the idGenero to set
	 */
	public void setIdGenero(int idGenero) {
		if (idGenero != 0) {
			this.idGenero = idGenero;
			this.setGenero(this.idGenero == GENERO.MASCULINO.getIdOpcion() ?
					GENERO.MASCULINO.getOpcion() : GENERO.FEMENINO.getOpcion());
		}
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
		this.fechaNacimiento = fechaNacimiento;
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
		if (edad != 0)
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
		if (idEntidadNacimiento != 0)
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
		if (entidadNacimiento != null)
			this.entidadNacimiento = entidadNacimiento;
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
		if (idEstadoCivil != 0)
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
		if(idTipoDiscapacidad != 0)
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
		if (idDomicilio != 0)
			this.idDomicilio = idDomicilio;
	}
	/**
	 * @return the idTipoPropietario
	 */
	public long getIdTipoPropietario() {
		return idTipoPropietario;
	}
	/**
	 * @param idTipoPropietario the idTipoPropietario to set
	 */
	public void setIdTipoPropietario(long idTipoPropietario) {
		if (idTipoPropietario != 0)
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
		if (idEntidad != 0)
			this.idEntidad = idEntidad;
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
		if (idMunicipio != 0)
		this.idMunicipio = idMunicipio;
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
		if (idColonia != 0)
			this.idColonia = idColonia;
	}
	/**
	 * @return the calle
	 */
	public String getCalle() {
		if(null==this.calle)
			return "";		
		return calle;
	}
	/**
	 * @param calle the calle to set
	 */
	public void setCalle(String calle) {
		if (calle != null)
			this.calle = calle;
	}
	/**
	 * @return the numeroInterior
	 */
	public String getNumeroInterior() {
		if(null==this.numeroInterior)
			return "";				
		return numeroInterior;
	}
	/**
	 * @param numeroInterior the numeroInterior to set
	 */
	public void setNumeroInterior(String numeroInterior) {
		if (numeroInterior != null)
			this.numeroInterior = numeroInterior;
	}
	/**
	 * @return the numeroExterior
	 */
	public String getNumeroExterior() {
		if(null==this.numeroExterior)
			return "";			
		return numeroExterior;
	}
	/**
	 * @param numeroExterior the numeroExterior to set
	 */
	public void setNumeroExterior(String numeroExterior) {
		if (numeroExterior != null)
			this.numeroExterior = numeroExterior;
	}
	/**
	 * @return the entreCalle
	 */
	public String getEntreCalle() {
		if(null==this.entreCalle)
			return "";			
		return entreCalle;
	}
	/**
	 * @param entreCalle the entreCalle to set
	 */
	public void setEntreCalle(String entreCalle) {
		if (entreCalle != null)
		this.entreCalle = entreCalle;
	}
	/**
	 * @return the yCalle
	 */
	public String getyCalle() {
		if(null==this.yCalle)
			return "";					
		return yCalle;
	}
	/**
	 * @param yCalle the yCalle to set
	 */
	public void setyCalle(String yCalle) {
		if (yCalle != null)
			this.yCalle = yCalle;
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
		this.codigoPostal = codigoPostal;
	}
	public void setConfidencialidad(int confidencialidad) {
		this.confidencialidad = confidencialidad;
	}
	public int getConfidencialidad() {
		return confidencialidad;
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
		if (contactoCorreo != 0)
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
		if (contactoTelefono != 0)
		this.contactoTelefono = contactoTelefono;
	}
	/**
	 * @return the horaContactoIni
	 */
	public long getHoraContactoIni() {
		return horaContactoIni;
	}
	/**
	 * @param horaContactoIni the horaContactoIni to set
	 */
	public void setHoraContactoIni(long horaContactoIni) {
		if (horaContactoIni != 0)
			this.horaContactoIni = horaContactoIni;
	}
	
	/**
	 * @return the horaContactoFin
	 */
	public long getHoraContactoFin() {
		return horaContactoFin;
	}
	/**
	 * @param horaContactoFin the horaContactoFin to set
	 */
	public void setHoraContactoFin(long horaContactoFin) {
		if (horaContactoFin != 0)
			this.horaContactoFin = horaContactoFin;
	}
	/**
	 * @param principal the principal to set
	 */
	public void setPrincipal(TelefonoVO principal) {
		this.principal = principal;
	}
	/**
	 * @return the principal
	 */
	public TelefonoVO getPrincipal() {
		return principal;
	}
	
	
	
	public TelefonoVO getSecundario() {
		return secundario;
	}

	public void setSecundario(TelefonoVO secundario) {
		this.secundario = secundario;
	}

	public TelefonoVO getTercero() {
		return tercero;
	}

	public void setTercero(TelefonoVO tercero) {
		this.tercero = tercero;
	}

	/**
	 * @param cambioCorreo the cambioCorreo to set
	 */
	public void setCambioCorreo(int cambioCorreo) {
		this.cambioCorreo = cambioCorreo;
	}
	/**
	 * @return the cambioCorreo
	 */
	public int getCambioCorreo() {
		return cambioCorreo;
	}
	/**
	 * @param correoAux the correoAux to set
	 */
	public void setCorreoAux(String correoAux) {
		this.correoAux = correoAux;
	}
	/**
	 * @return the correoAux
	 */
	public String getCorreoAux() {
		return correoAux;
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
		if (correoElectronico != null)
		this.correoElectronico = correoElectronico;
	}
	/**
	 * @return the idRecibeOferta
	 */
	public long getIdRecibeOferta() {
		return idRecibeOferta;
	}
	/**
	 * @param idRecibeOferta the idRecibeOferta to set
	 */
	public void setIdRecibeOferta(long idRecibeOferta) {
		if (idRecibeOferta != 0)
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
		if (idTrabaja != 0)
		this.idTrabaja = idTrabaja;
	}
	/**
	 * @return the idRazonBusqueda
	 */
	public long getIdRazonBusqueda() {
		return idRazonBusqueda;
	}
	/**
	 * @param idRazonBusqueda the idRazonBusqueda to set
	 */
	public void setIdRazonBusqueda(long idRazonBusqueda) {
		if (idRazonBusqueda != 0)
		this.idRazonBusqueda = idRazonBusqueda;
	}

	public String getDescripcionOtroMotivoBusq() {
		return descripcionOtroMotivoBusq;
	}

	public void setDescripcionOtroMotivoBusq(String descripcionOtroMotivoBusq) {
		this.descripcionOtroMotivoBusq = descripcionOtroMotivoBusq;
	}

	/**
	 * @return the inicioBusqueda
	 */
	public Date getInicioBusqueda() {
		if (inicioBusqueda == null)
			return new Date();
		else	
			return inicioBusqueda;
	}
	/**
	 * @param inicioBusqueda the inicioBusqueda to set
	 */
	public void setInicioBusqueda(Date inicioBusqueda) {
		if (inicioBusqueda != null)
			this.inicioBusqueda = inicioBusqueda;
	}
	/**
	 * @return the idMedioBusqueda
	 */
	public long getIdMedioBusqueda() {
		return idMedioBusqueda;
	}
	/**
	 * @param idMedioBusqueda the idMedioBusqueda to set
	 */
	public void setIdMedioBusqueda(long idMedioBusqueda) {
		if (idMedioBusqueda != 0)
		this.idMedioBusqueda = idMedioBusqueda;
	}
	/**
	 * @param idOtrosMedios the idOtrosMedios to set
	 */
	public void setIdOtrosMedios(long[] idOtrosMedios) {
		this.idOtrosMedios = idOtrosMedios;
	}
	/**
	 * @return the idOtrosMedios
	 */
	public long[] getIdOtrosMedios() {
		return idOtrosMedios;
	}
	/**
	 * @param sinEstudios the sinEstudios to set
	 */
	public void setSinEstudios(int sinEstudios) {
		this.sinEstudios = sinEstudios;
	}
	/**
	 * @return the sinEstudios
	 */
	public int getSinEstudios() {
		return sinEstudios;
	}
	/**
	 * @param computacionBasica the computacionBasica to set
	 */
	public void setComputacionBasica(int computacionBasica) {
		this.computacionBasica = computacionBasica;
	}
	/**
	 * @return the computacionBasica
	 */
	public int getComputacionBasica() {
		return computacionBasica;
	}
	/**
	 * @param idExperienciaCompu the idExperienciaCompu to set
	 */
	public void setIdExperienciaCompu(long idExperienciaCompu) {
		this.idExperienciaCompu = idExperienciaCompu;
	}
	/**
	 * @return the idExperienciaCompu
	 */
	public long getIdExperienciaCompu() {
		return idExperienciaCompu;
	}
	/**
	 * @param idDominioCompu the idDominioCompu to set
	 */
	public void setIdDominioCompu(long idDominioCompu) {
		this.idDominioCompu = idDominioCompu;
	}
	/**
	 * @return the idDominioCompu
	 */
	public long getIdDominioCompu() {
		return idDominioCompu;
	}
	/**
	 * @param idExperienciaOffice the idExperienciaOffice to set
	 */
	public void setIdExperienciaOffice(long idExperienciaOffice) {
		this.idExperienciaOffice = idExperienciaOffice;
	}
	/**
	 * @return the idExperienciaOffice
	 */
	public long getIdExperienciaOffice() {
		return idExperienciaOffice;
	}
	/**
	 * @param idDominioOffice the idDominioOffice to set
	 */
	public void setIdDominioOffice(long idDominioOffice) {
		this.idDominioOffice = idDominioOffice;
	}
	/**
	 * @return the idDominioOffice
	 */
	public long getIdDominioOffice() {
		return idDominioOffice;
	}
	/**
	 * @param idExperienciaInternet the idExperienciaInternet to set
	 */
	public void setIdExperienciaInternet(long idExperienciaInternet) {
		this.idExperienciaInternet = idExperienciaInternet;
	}
	/**
	 * @return the idExperienciaInternet
	 */
	public long getIdExperienciaInternet() {
		return idExperienciaInternet;
	}
	/**
	 * @param idDominioInternet the idDominioInternet to set
	 */
	public void setIdDominioInternet(long idDominioInternet) {
		this.idDominioInternet = idDominioInternet;
	}
	/**
	 * @return the idDominioInternet
	 */
	public long getIdDominioInternet() {
		return idDominioInternet;
	}
	/**
	 * @param computacionAvanzada the computacionAvanzada to set
	 */
	public void setComputacionAvanzada(int computacionAvanzada) {
		this.computacionAvanzada = computacionAvanzada;
	}
	/**
	 * @return the computacionAvanzada
	 */
	public int getComputacionAvanzada() {
		return computacionAvanzada;
	}
	/**
	 * @param idExperienciaTotal the idExperienciaTotal to set
	 */
	public void setIdExperienciaTotal(long idExperienciaTotal) {
		this.idExperienciaTotal = idExperienciaTotal;
	}
	/**
	 * @return the idExperienciaTotal
	 */
	public long getIdExperienciaTotal() {
		return idExperienciaTotal;
	}
	/**
	 * @param idSectorMayorExpr the idSectorMayorExpr to set
	 */
	public void setIdSectorMayorExpr(long idSectorMayorExpr) {
		this.idSectorMayorExpr = idSectorMayorExpr;
	}
	/**
	 * @return the idSectorMayorExpr
	 */
	public long getIdSectorMayorExpr() {
		return idSectorMayorExpr;
	}
	/**
	 * @param puestoMayorExpr the puestoMayorExpr to set
	 */
	public void setPuestoMayorExpr(String puestoMayorExpr) {
		this.puestoMayorExpr = puestoMayorExpr;
	}
	/**
	 * @return the puestoMayorExpr
	 */
	public String getPuestoMayorExpr() {
		return puestoMayorExpr;
	}
	/**
	 * @param idAreaLaboralMayorExpr the idAreaLaboralMayorExpr to set
	 */
	public void setIdAreaLaboralMayorExpr(long idAreaLaboralMayorExpr) {
		this.idAreaLaboralMayorExpr = idAreaLaboralMayorExpr;
	}
	/**
	 * @return the idAreaLaboralMayorExpr
	 */
	public long getIdAreaLaboralMayorExpr() {
		return idAreaLaboralMayorExpr;
	}
	/**
	 * @param idOcupacionMayorExpr the idOcupacionMayorExpr to set
	 */
	public void setIdOcupacionMayorExpr(long idOcupacionMayorExpr) {
		this.idOcupacionMayorExpr = idOcupacionMayorExpr;
	}
	/**
	 * @return the idOcupacionMayorExpr
	 */
	public long getIdOcupacionMayorExpr() {
		return idOcupacionMayorExpr;
	}
	/**
	 * @return the experiencia
	 */
	public String getExperiencia() {
		return experiencia;
	}
	/**
	 * @param experiencia the experiencia to set
	 */
	public void setExperiencia(String experiencia) {
		this.experiencia = experiencia;
	}

	/**
	 * @param disponibilidadViajar the disponibilidadViajar to set
	 */
	public void setDisponibilidadViajar(int disponibilidadViajar) {
		this.disponibilidadViajar = disponibilidadViajar;
	}
	/**
	 * @return the disponibilidadViajar
	 */
	public int getDisponibilidadViajar() {
		return disponibilidadViajar;
	}
	/**
	 * @param disponibilidadRadicar the disponibilidadRadicar to set
	 */
	public void setDisponibilidadRadicar(int disponibilidadRadicar) {
		this.disponibilidadRadicar = disponibilidadRadicar;
	}
	/**
	 * @return the disponibilidadRadicar
	 */
	public int getDisponibilidadRadicar() {
		return disponibilidadRadicar;
	}
	/**
	 * @param sinExperiencia the sinExperiencia to set
	 */
	public void setSinExperiencia(int sinExperiencia) {
		this.sinExperiencia = sinExperiencia;
	}

	/**
	 * @return the sinExperiencia
	 */
	public int getSinExperiencia() {
		return sinExperiencia;
	}
	
	/**
	 * @return the idMedioPortal
	 */
	public long getIdMedioPortal() {
		return idMedioPortal;
	}

	/**
	 * @param idMedioPortal the idMedioPortal to set
	 */
	public void setIdMedioPortal(long idMedioPortal) {
		this.idMedioPortal = idMedioPortal;
	}
	
	public Long getIdOficina() {
		return idOficina;
	}

	public void setIdOficina(Long idOficina) {
		this.idOficina = idOficina;
	}
	
	public Integer getApoyoProspera() {
		return apoyoProspera;
	}

	public void setApoyoProspera(Integer apoyoProspera) {
		this.apoyoProspera = apoyoProspera;
	}

	
	
	public Integer getIdEstatusPPC() {
		return idEstatusPPC;
	}

	public void setIdEstatusPPC(Integer idEstatusPPC) {
		this.idEstatusPPC = idEstatusPPC;
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PerfilVO idCandidato=" + idCandidato + "|idUsuario="
				+ idUsuario + "|curp=" + curp + "|nombre=" + nombre
				+ "|apellido1=" + apellido1 + "|apellido2=" + apellido2
				+ "|idGenero=" + idGenero + "|genero=" + genero
				+ "|fechaNacimiento=" + fechaNacimiento + "|edad=" + edad
				+ "|idEntidadNacimiento=" + idEntidadNacimiento
				+ "|entidadNacimiento=" + entidadNacimiento
				+ "|idEstadoCivil=" + idEstadoCivil + "|idTipoDiscapacidad="
				+ idTipoDiscapacidad + "|idDomicilio=" + idDomicilio
				+ "|idTipoPropietario=" + idTipoPropietario + "|idEntidad="
				+ idEntidad + "|idMunicipio=" + idMunicipio + "|idColonia="
				+ idColonia + "|calle=" + calle + "|numeroInterior="
				+ numeroInterior + "|numeroExterior=" + numeroExterior
				+ "|entreCalle=" + entreCalle + "|yCalle=" + yCalle
				+ "|codigoPostal=" + codigoPostal + "|confidencialidad="
				+ confidencialidad + "|contactoCorreo=" + contactoCorreo
				+ "|contactoTelefono=" + contactoTelefono
				+ "|horaContactoIni=" + horaContactoIni + "|horaContactoFin="
				+ horaContactoFin + "|principal=" + principal
				+ "|cambioCorreo=" + cambioCorreo + "|correoAux=" + correoAux
				+ "|correoElectronico=" + correoElectronico
				+ "|idRecibeOferta=" + idRecibeOferta + "|idTrabaja="
				+ idTrabaja + "|idRazonBusqueda=" + idRazonBusqueda
				+ "|inicioBusqueda=" + inicioBusqueda + "|idMedioBusqueda="
				+ idMedioBusqueda + "|idOtrosMedios="
				+ Arrays.toString(idOtrosMedios) + "|sinEstudios="
				+ sinEstudios + "|computacionBasica=" + computacionBasica
				+ "|idExperienciaCompu=" + idExperienciaCompu
				+ "|idDominioCompu=" + idDominioCompu
				+ "|idExperienciaOffice=" + idExperienciaOffice
				+ "|idDominioOffice=" + idDominioOffice
				+ "|idExperienciaInternet=" + idExperienciaInternet
				+ "|idDominioInternet=" + idDominioInternet
				+ "|computacionAvanzada=" + computacionAvanzada
				+ "|idExperienciaTotal=" + idExperienciaTotal
				+ "|idSectorMayorExpr=" + idSectorMayorExpr
				+ "|puestoMayorExpr=" + puestoMayorExpr
				+ "|idAreaLaboralMayorExpr=" + idAreaLaboralMayorExpr
				+ "|idOcupacionMayorExpr=" + idOcupacionMayorExpr
				+ "|disponibilidadViajar=" + disponibilidadViajar
				+ "|disponibilidadRadicar=" + disponibilidadRadicar;
	}
	
	
	/*Metodo para la bitacora*/
	public String toBitacora() {
		return "idCandidato=" + idCandidato + "|idEstadoCivil=" 
				+ idEstadoCivil + "|idTipoDiscapacidad="
				+ idTipoDiscapacidad + "|idDomicilio=" + idDomicilio
				+ "|idTipoPropietario=" + idTipoPropietario + "|idEntidad="
				+ idEntidad + "|idMunicipio=" + idMunicipio + "|idColonia="
				+ idColonia + "|calle=" + calle + "|numeroInterior="
				+ numeroInterior + "|numeroExterior=" + numeroExterior
				+ "|entreCalle=" + entreCalle + "|yCalle=" + yCalle
				+ "|codigoPostal=" + codigoPostal + "|confidencialidad="
				+ confidencialidad + "|contactoCorreo=" + contactoCorreo
				+ "|contactoTelefono=" + contactoTelefono
				+ "|horaContactoIni=" + horaContactoIni + "|horaContactoFin="
				+ horaContactoFin + this.telToBitacora(principal)
				+ "|correoElectronico=" + correoElectronico
				+ "|idRecibeOferta=" + idRecibeOferta + "|idTrabaja="
				+ idTrabaja + "|idRazonBusqueda=" + idRazonBusqueda
				+ "|inicioBusqueda=" + inicioBusqueda + "|idMedioBusqueda="
				+ idMedioBusqueda + "|idOtrosMedios="
				+ Arrays.toString(idOtrosMedios);
	}
	
	private String telToBitacora(TelefonoVO principal) {
		return "idTelefono=" + principal.getIdTelefono() 
		+ "|idPropietario=" + principal.getIdPropietario() 
		+ "|idTipoPropietario=" + principal.getIdTipoPropietario()
		+ "|idTipoTelefono=" + principal.getIdTipoTelefono() 
		+ "|acceso=" + principal.getAcceso() + "|clave=" 
		+ principal.getClave() + "|telefono=" + principal.getTelefono()
		+ "|extension=" + principal.getExtension()
		+ "|principal=" + principal.getPrincipal() 
		+ "|tipoTelefono=" + principal.getTipoTelefono();
	}

	public String getDiscapacidades() {
		return discapacidades;
	}

	public void setDiscapacidades(String discapacidades) {
		this.discapacidades = discapacidades;
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

	public long getIdLocalidad() {
		return idLocalidad;
	}

	public void setIdLocalidad(long idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	public String getDomicilioRef() {
		return domicilioRef;
	}

	public void setDomicilioRef(String domicilioRef) {
		this.domicilioRef = domicilioRef;
	}

	public int getIdLicencia() {
		return idLicencia;
	}

	public void setIdLicencia(int idLicencia) {
		this.idLicencia = idLicencia;
	}

	public int getDisponibilidadRadicarPais() {
		return disponibilidadRadicarPais;
	}

	public void setDisponibilidadRadicarPais(int disponibilidadRadicarPais) {
		this.disponibilidadRadicarPais = disponibilidadRadicarPais;
	}

	public String getUltimaActualizacion() {
		return ultimaActualizacion;
	}

	public void setUltimaActualizacion(String ultimaActualizacion) {
		this.ultimaActualizacion = ultimaActualizacion;
	}
	
}
