package mx.gob.stps.portal.core.empresa.vo;

import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.utils.Catalogos;

import java.util.Date;
import java.util.List;
import java.io.Serializable;

//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;

public class EmpresaVO implements Serializable {
	private static final long serialVersionUID = 4851232484141097682L;

	private long idEmpresa;

	private String idPortalEmpleo;

	private long idUsuario;

	private String rfc;

	private long idTipoPersona;

	private String nombre;

	private String apellido1;

	private String apellido2;

	private Date fechaNacimiento;

	private String razonSocial;

	private Date fechaActa;

	private String descripcion;

	private String contactoEmpresa;
	
	private String cargoContacto;

	private long idTipoEmpresa;

	private long idActividadEconomica;

	private int numeroEmpleados;

	private long idMedio;

	private int confidencial;

	private String paginaWeb;

	private byte[] logotipo;

	private int aceptacionTerminos;

	private Date fechaAlta;

	private int estatus;

	private Date fechaUltimaActualizacion;

	private String correoElectronico;
	
	private int aseguraDatos;
	
	private long idTipoSociedad;
	
	private List<TelefonoVO> telefonos;
	
	private DomicilioVO domicilio;
	
	private String tipoPersona;
	private String tipoEmpresa;
	private String actividadEconomica;
	private String sector;

	private String subSector;
	private String rama;
	
	private String tblEmpresa;
	private long idRegValidar;
	private int estatusRegistro;
	
	private Date fechaRevision;
	private String motivoRechazo;
	private String detalleRechazo;
	private String nombrePublicador;
	
	private boolean rechazada;
	private boolean descartar;

	private String nombreComercial;
	
	private Date fechaConfirma;
	
	private int cambioCorreo;
	private String correoAux;
	private String contrasena;
	
	private boolean ofertaCanada;
	
	private int esEmpresaSNE;	
	private Date fechaUltimaDesactivacion;	
	private int idMotivoDesactivacion;	
	private String detalleDesactivacion;
	
	private String curpPF;
	private Short curpValidada;	
	private Long idFuente;
	private Long idTamanio;
	private String usuario;

	private Long idOficina;
	private Long idMotivoNoValidacion;
	private Long idLugarNacimientoPF;
	private Integer genero;
	
	private boolean esfuncionPublica;
	private boolean estatusGeoreferenciaDomicilio;
	private boolean estatusGeoreferenciaOferta;
	
		
	/**
	 * Constructor por defecto
	 * 
	 * @return EmpresaVO
	 */		
	public EmpresaVO() {
	
	}

	/**
	 * Constructor que acepta todos los valores de una empresa
	 * 
	 * @param idEmpresa
	 */ 	
	public EmpresaVO(long idEmpresa, String idPortalEmpleo, long idUsuario,
			String rfc, long idTipoPersona, String nombre, String apellido1,
			String apellido2, Date fechaNacimiento, String razonSocial, 
			Date fechaActa, String descripcion, String contactoEmpresa, String cargoContacto,
			long idTipoEmpresa, long idActividadEconomica, int numeroEmpleados,
			long idMedio, int confidencial, String paginaWeb, byte[] logotipo,
			int aceptacionTerminos, Date fechaAlta, int estatus, 
			Date fechaUltimaActualizacion, String correoElectronico, 
			int aseguraDatos, long idTipoSociedad, List<TelefonoVO> lstTelefonos, DomicilioVO domicilio){
		this.idEmpresa = idEmpresa;
		this.idPortalEmpleo = idPortalEmpleo;
		this.idUsuario = idUsuario;
		this.rfc = rfc;
		this.idTipoPersona = idTipoPersona;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.fechaNacimiento = fechaNacimiento;
		this.razonSocial = razonSocial;
		this.fechaActa = fechaActa;
		this.descripcion = descripcion;
		this.contactoEmpresa = contactoEmpresa;
		this.cargoContacto = cargoContacto;
		this.idTipoEmpresa = idTipoEmpresa;
		this.idActividadEconomica = idActividadEconomica;
		this.numeroEmpleados = numeroEmpleados;
		this.idMedio = idMedio;
		this.confidencial = confidencial;
		this.paginaWeb = paginaWeb;
		this.logotipo = logotipo;
		this.aceptacionTerminos = aceptacionTerminos;
		this.fechaAlta = fechaAlta;
		this.estatus = estatus;
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
		this.correoElectronico = correoElectronico;		
		this.aseguraDatos = aseguraDatos;
		this.idTipoSociedad = idTipoSociedad;
		this.telefonos = lstTelefonos;
		this.domicilio = domicilio;
	}

	

	/**
	 * @return the idEmpresa
	 */
	public long getIdEmpresa() {
		return idEmpresa;
	}

	/**
	 * @param idEmpresa the idEmpresa to set
	 */
	public void setIdEmpresa(long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	/**
	 * @return the idPortalEmpleo
	 */
	public String getIdPortalEmpleo() {
		return idPortalEmpleo;
	}

	/**
	 * @param idPortalEmpleo the idPortalEmpleo to set
	 */
	public void setIdPortalEmpleo(String idPortalEmpleo) {
		this.idPortalEmpleo = idPortalEmpleo;
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
	 * @return the rfc
	 */
	public String getRfc() {
		return rfc;
	}

	/**
	 * @param rfc the rfc to set
	 */
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	/**
	 * @return the idTipoPersona
	 */
	public long getIdTipoPersona() {
		return idTipoPersona;
	}

	/**
	 * @param idTipoPersona the idTipoPersona to set
	 */
	public void setIdTipoPersona(long idTipoPersona) {
		this.idTipoPersona = idTipoPersona;
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
	 * @return the razonSocial
	 */
	public String getRazonSocial() {
		return razonSocial;
	}

	/**
	 * @param razonSocial the razonSocial to set
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	/**
	 * @return the fechaActa
	 */
	public Date getFechaActa() {
		return fechaActa;
	}

	/**
	 * @param fechaActa the fechaActa to set
	 */
	public void setFechaActa(Date fechaActa) {
		this.fechaActa = fechaActa;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the contactoEmpresa
	 */
	public String getContactoEmpresa() {
		return contactoEmpresa;
	}

	/**
	 * @param contactoEmpresa the contactoEmpresa to set
	 */
	public void setContactoEmpresa(String contactoEmpresa) {
		this.contactoEmpresa = contactoEmpresa;
	}
	
	

	public String getCargoContacto() {
		return cargoContacto;
	}

	public void setCargoContacto(String cargoContacto) {
		this.cargoContacto = cargoContacto;
	}

	/**
	 * @return the idTipoEmpresa
	 */
	public long getIdTipoEmpresa() {
		return idTipoEmpresa;
	}

	/**
	 * @param idTipoEmpresa the idTipoEmpresa to set
	 */
	public void setIdTipoEmpresa(long idTipoEmpresa) {
		this.idTipoEmpresa = idTipoEmpresa;
	}

	/**
	 * @return the idActividadEconomica
	 */
	public long getIdActividadEconomica() {
		return idActividadEconomica;
	}

	/**
	 * @param idActividadEconomica the idActividadEconomica to set
	 */
	public void setIdActividadEconomica(long idActividadEconomica) {
		this.idActividadEconomica = idActividadEconomica;
	}

	/**
	 * @return the numeroEmpleados
	 */
	public int getNumeroEmpleados() {
		return numeroEmpleados;
	}

	/**
	 * @param numeroEmpleados the numeroEmpleados to set
	 */
	public void setNumeroEmpleados(int numeroEmpleados) {
		this.numeroEmpleados = numeroEmpleados;
	}

	/**
	 * @return the idMedio
	 */
	public long getIdMedio() {
		return idMedio;
	}

	/**
	 * @param idMedio the idMedio to set
	 */
	public void setIdMedio(long idMedio) {
		this.idMedio = idMedio;
	}

	/**
	 * @return the confidencial
	 */
	public int getConfidencial() {
		return confidencial;
	}

	/**
	 * @param confidencial the confidencial to set
	 */
	public void setConfidencial(int confidencial) {
		this.confidencial = confidencial;
	}

	/**
	 * @return the paginaWeb
	 */
	public String getPaginaWeb() {
		return paginaWeb;
	}

	/**
	 * @param paginaWeb the paginaWeb to set
	 */
	public void setPaginaWeb(String paginaWeb) {
		this.paginaWeb = paginaWeb;
	}

	/**
	 * @return the logotipo
	 */
	public byte[] getLogotipo() {
		return logotipo;
	}

	/**
	 * @param logotipo the logotipo to set
	 */
	public void setLogotipo(byte[] logotipo) {
		this.logotipo = logotipo;
	}

	/**
	 * @return the aceptacionTerminos
	 */
	public int getAceptacionTerminos() {
		return aceptacionTerminos;
	}

	/**
	 * @param aceptacionTerminos the aceptacionTerminos to set
	 */
	public void setAceptacionTerminos(int aceptacionTerminos) {
		this.aceptacionTerminos = aceptacionTerminos;
	}

	/**
	 * @return the fechaAlta
	 */
	public Date getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * @param fechaAlta the fechaAlta to set
	 */
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	/**
	 * @return the estatus
	 */
	public int getEstatus() {
		return estatus;
	}

	/**
	 * @param estatus the estatus to set
	 */
	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	public String getEstatusDescripcion() {
		String descripcion = null;
		if (estatus>0) descripcion = ESTATUS.getDescripcion(estatus);
		return descripcion;
	}
	
	/**
	 * @return the fechaUltimaActualizacion
	 */
	public Date getFechaUltimaActualizacion() {
		return fechaUltimaActualizacion;
	}

	/**
	 * @param fechaUltimaActualizacion the fechaUltimaActualizacion to set
	 */
	public void setFechaUltimaActualizacion(Date fechaUltimaActualizacion) {
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
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
	 * @return the aseguraDatos
	 */
	public int getAseguraDatos() {
		return aseguraDatos;
	}

	/**
	 * @param aseguraDatos the aseguraDatos to set
	 */
	public void setAseguraDatos(int aseguraDatos) {
		this.aseguraDatos = aseguraDatos;
	}
	
	public long getIdTipoSociedad() {
		return idTipoSociedad;
	}

	public void setIdTipoSociedad(long idTipoSociedad) {
		this.idTipoSociedad = idTipoSociedad;
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
	
	/**
	 * @return the domicilio
	 */
	public DomicilioVO getDomicilio() {
		return domicilio;
	}

	/**
	 * @param domicilio the domicilio to set
	 */
	public void setDomicilio(DomicilioVO domicilio) {
		this.domicilio = domicilio;
	}

	public String getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	public String getTipoEmpresa() {
		return tipoEmpresa;
	}

	public void setTipoEmpresa(String tipoEmpresa) {
		this.tipoEmpresa = tipoEmpresa;
	}

	public String getActividadEconomica() {
		return actividadEconomica;
	}

	public void setActividadEconomica(String actividadEconomica) {
		this.actividadEconomica = actividadEconomica;
	}	

	/**
	 * @return the tblEmpresa
	 */
	public String getTblEmpresa() {
		return tblEmpresa;
	}

	/**
	 * @param tblEmpresa the tblEmpresa to set
	 */
	public void setTblEmpresa(String tblEmpresa) {
		this.tblEmpresa = tblEmpresa;
	}

	/**
	 * @return the idRegValidar
	 */
	public long getIdRegValidar() {
		return idRegValidar;
	}

	/**
	 * @param idRegValidar the idRegValidar to set
	 */
	public void setIdRegValidar(long idRegValidar) {
		this.idRegValidar = idRegValidar;
	}

	public String getNombreEmpresa(){
		String strName = null;
		if(Catalogos.TIPO_EMPRESA.PRIVADA.getTipoEmpresa() == idTipoEmpresa && Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() == idTipoPersona){
			strName = nombre!=null?nombre : "" + (apellido1!=null?(" " + apellido1) : "")  + (apellido2!=null?(" " + apellido2) : "");
		} else{
			strName = razonSocial;
		}		
		return strName;
	}


	public Date getFechaRevision() {
		return fechaRevision;
	}

	public void setFechaRevision(Date fechaRevision) {
		this.fechaRevision = fechaRevision;
	}

	public String getDetalleRechazo() {
		return detalleRechazo;
	}

	public void setDetalleRechazo(String detalleRechazo) {
		this.detalleRechazo = detalleRechazo;
	}

	public String getNombrePublicador() {
		return nombrePublicador;
	}

	public void setNombrePublicador(String nombrePublicador) {
		this.nombrePublicador = nombrePublicador;
	}

	public int getEstatusRegistro() {
		return estatusRegistro;
	}

	public void setEstatusRegistro(int estatusRegistro) {
		this.estatusRegistro = estatusRegistro;
	}

	public String getMotivoRechazo() {
		return motivoRechazo;
	}

	public void setMotivoRechazo(String motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
	}

	public boolean isRechazada() {
		return rechazada;
	}

	public void setRechazada(boolean rechazada) {
		this.rechazada = rechazada;
	}

	public boolean isDescartar() {
		return descartar;
	}

	public void setDescartar(boolean descartar) {
		this.descartar = descartar;
	}
	
	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	/**
	 * @return the fechaConfirma
	 */
	public Date getFechaConfirma() {
		return fechaConfirma;
	}

	/**
	 * @param fechaConfirma the fechaConfirma to set
	 */
	public void setFechaConfirma(Date fechaConfirma) {
		this.fechaConfirma = fechaConfirma;
	}

	/**
	 * @return the cambioCorreo
	 */
	public int getCambioCorreo() {
		return cambioCorreo;
	}

	/**
	 * @param cambioCorreo the cambioCorreo to set
	 */
	public void setCambioCorreo(int cambioCorreo) {
		this.cambioCorreo = cambioCorreo;
	}

	/**
	 * @return the correoAux
	 */
	public String getCorreoAux() {
		return correoAux;
	}

	/**
	 * @param correoAux the correoAux to set
	 */
	public void setCorreoAux(String correoAux) {
		this.correoAux = correoAux;
	}

	/**
	 * @return the contrasena
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * @param contrasena the contrasena to set
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public void setOfertaCanada(boolean ofertaCanada) {
		this.ofertaCanada = ofertaCanada;
	}

	public boolean isOfertaCanada() {
		return ofertaCanada;
	}

	public int getEsEmpresaSNE() {
		return esEmpresaSNE;
	}

	public void setEsEmpresaSNE(int esEmpresaSNE) {
		this.esEmpresaSNE = esEmpresaSNE;
	}

	public Date getFechaUltimaDesactivacion() {
		return fechaUltimaDesactivacion;
	}

	public void setFechaUltimaDesactivacion(Date fechaUltimaDesactivacion) {
		this.fechaUltimaDesactivacion = fechaUltimaDesactivacion;
	}

	public int getIdMotivoDesactivacion() {
		return idMotivoDesactivacion;
	}

	public void setIdMotivoDesactivacion(int idMotivoDesactivacion) {
		this.idMotivoDesactivacion = idMotivoDesactivacion;
	}

	public String getDetalleDesactivacion() {
		return detalleDesactivacion;
	}

	public void setDetalleDesactivacion(String detalleDesactivacion) {
		this.detalleDesactivacion = detalleDesactivacion;
	}
	
	
	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getSubSector() {
		return subSector;
	}

	public void setSubSector(String subSector) {
		this.subSector = subSector;
	}

	public String getRama() {
		return rama;
	}

	public void setRama(String rama) {
		this.rama = rama;
	}

	
	public String getCurpPF() {
		return curpPF;
	}

	public void setCurpPF(String curpPF) {
		this.curpPF = curpPF;
	}

	public Short getCurpValidada() {
		return curpValidada;
	}

	public void setCurpValidada(Short curpValidada) {
		this.curpValidada = curpValidada;
	}

	public Long getIdFuente() {
		return idFuente;
	}

	public void setIdFuente(Long idFuente) {
		this.idFuente = idFuente;
	}

	public Long getIdTamanio() {
		return idTamanio;
	}
	
	
	public void setIdTamanio(Long idTamanio) {
		this.idTamanio = idTamanio;
	}

	
	public Long getIdOficina() {
		return idOficina;
	}

	public void setIdOficina(Long idOficina) {
		this.idOficina = idOficina;
	}

	public Long getIdMotivoNoValidacion() {
		return idMotivoNoValidacion;
	}

	public void setIdMotivoNoValidacion(Long idMotivoNoValidacion) {
		this.idMotivoNoValidacion = idMotivoNoValidacion;
	}

	public Long getIdLugarNacimientoPF() {
		return idLugarNacimientoPF;
	}

	public void setIdLugarNacimientoPF(Long idLugarNacimientoPF) {
		this.idLugarNacimientoPF = idLugarNacimientoPF;
	}

	public Integer getGenero() {
		return genero;
	}

	public void setGenero(Integer genero) {
		this.genero = genero;
	}

	public boolean getEsfuncionPublica() {
		return esfuncionPublica;
	}

	public void setEsfuncionPublica(boolean esfuncionPublica) {
		this.esfuncionPublica = esfuncionPublica;
	}
	
	public boolean isEstatusGeoreferenciaDomicilio() {
		return estatusGeoreferenciaDomicilio;
	}

	public void setEstatusGeoreferenciaDomicilio(
			boolean estatusGeoreferenciaDomicilio) {
		this.estatusGeoreferenciaDomicilio = estatusGeoreferenciaDomicilio;
	}

	public boolean isEstatusGeoreferenciaOferta() {
		return estatusGeoreferenciaOferta;
	}

	public void setEstatusGeoreferenciaOferta(boolean estatusGeoreferenciaOferta) {
		this.estatusGeoreferenciaOferta = estatusGeoreferenciaOferta;
	}

	@Override
	public String toString() {
		return "EmpresaVO [idEmpresa=" + idEmpresa + ", idTipoPersona="
				+ idTipoPersona + ", nombre=" + nombre + ", apellido1="
				+ apellido1 + ", apellido2=" + apellido2 + ", razonSocial="
				+ razonSocial + ", idTipoEmpresa=" + idTipoEmpresa + "]";
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}
