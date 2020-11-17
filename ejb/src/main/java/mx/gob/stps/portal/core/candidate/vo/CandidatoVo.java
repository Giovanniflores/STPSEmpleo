package mx.gob.stps.portal.core.candidate.vo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.seguridad.vo.UsuarioVO;
import mx.gob.stps.portal.persistencia.vo.SolicitanteVO;
import mx.gob.stps.portal.utils.Catalogos.TIPO_DISCAPACIDAD;

public class CandidatoVo implements Serializable {
	
	private static final long serialVersionUID = -3274893546080105510L;
	
	public CandidatoVo(){
		
		discapacidades = "00000";
	}
	
	protected Long idOficina;
	
	protected boolean select;
	
	protected String mensaje;
	
	protected SolicitanteVO solicitanteVO;
	
	protected UsuarioVO 	usuarioVO;

	protected TelefonoVo	telefonoVo;
	
	protected DomicilioVO	domicilioVo;
	
	protected GradoAcademicoVO gradoacademicoVO;
	
	protected List<IdiomaVO> lstIdiomas;

	protected long idCandidato;

	protected long idUsuario;
	
	protected Long idFuente;

	protected String curp;

	protected String nombre;

	protected String apellido1;

	protected String apellido2;

	protected int genero;

	protected Date fechaNacimiento;
	
	protected String fechaNacimientoString;

	protected int edad;

	protected int idEntidadNacimiento;

	protected int idEstadoCivil;

	protected int idTipoDiscapacidad;

	protected int idMedioPortal;

	protected int confidencialidadDatos;

	protected int veracidadDatos;

	protected int aceptacionTerminos;

	//protected Date fechaAlta;
	protected Calendar fechaAlta;

	protected int estatus;

	protected Date fechaUltimaActualizacion;

	protected String correoElectronico;

	protected int estiloCv;	
	
	protected int evaluaCv;	
	
	protected String password;

	
	protected String entidadNacimiento;
	
	protected String generoString;
	
	protected String idEntidadNacimientoString;

	
	protected String carrera;	

	protected String message;
	
	protected String TipoError;
	
	protected String CodigoError;

	protected String ocupacion;	
	
	protected String municipioEntidad;	
	
	protected double salario;

	private String sSalario;
	
	protected long idOferta;	
	
	protected String descEstatus;


	protected String descTipoContrato;
	
	protected String horarioEmpleo;
	
	protected String dispViajarFuera;
	
	protected String dispRadicarFuera;
	
	protected String statusOper;	
	
	protected int idEstatusLaboral;	
	
	protected String descEstatusLaboral;
	
	protected int idRazonBusqueda;	
	
	protected String descRazonBusqueda;
	
	protected String estadoEntidadString;
	
	protected String estatusCurp;

	private int compatibilidad;
	
	private Date fechaConfirma;
	
	protected String nss;
	
	protected Long creditoInfonavit;
	
	protected String medioContacto;
	
	private String contrasena;
	
	private Integer apoyoProspera;
	private String folioProspera;
	private String folioIntegranteProspera;

	protected int idMotivoDesactivacion;
	
	protected String detalleDesactivacion;
	
	protected Date fechaUltimoAcceso;	
	
	protected String discapacidades;
	
	protected int ppcEstatus;
	
	protected Date ppcFechaInscripcion;
	
	protected Integer ppcAceptacionTerminos;
	
	protected Integer ppcEstatusIMSS;
	
	protected Date ppcFechaBajaIMSS;
	
	protected String ppcTipoContratoIMSS;
	
	private String areaLaboralDescripcion;
	
	private String subAreaLaboralDescripcion;
	
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

	public int getPpcEstatus() {
		return ppcEstatus;
	}

	public void setPpcEstatus(int ppcEstatus) {
		this.ppcEstatus = ppcEstatus;
	}

	public Date getPpcFechaInscripcion() {
		return ppcFechaInscripcion;
	}

	public void setPpcFechaInscripcion(Date ppcFechaInscripcion) {
		this.ppcFechaInscripcion = ppcFechaInscripcion;
	}

	public Integer getPpcAceptacionTerminos() {
		return ppcAceptacionTerminos;
	}

	public void setPpcAceptacionTerminos(Integer ppcAceptacionTerminos) {
		this.ppcAceptacionTerminos = ppcAceptacionTerminos;
	}

	/**
	 * @return the estatusCurp
	 */
	public String getEstatusCurp() {
		return estatusCurp;
	}

	/**
	 * @param estatusCurp the estatusCurp to set
	 */
	public void setEstatusCurp(String estatusCurp) {
		this.estatusCurp = estatusCurp;
	}

	/**
	 * @return the estadoEntidadString
	 */
	public String getEstadoEntidadString() {
		return estadoEntidadString;
	}

	/**
	 * @param estadoEntidadString the estadoEntidadString to set
	 */
	public void setEstadoEntidadString(String estadoEntidadString) {
		this.estadoEntidadString = estadoEntidadString;
	}

	/**
	 * @return the select
	 */
	public boolean isSelect() {
		return select;
	}

	/**
	 * @param select the select to set
	 */
	public void setSelect(boolean select) {
		this.select = select;
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

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getIdEntidadNacimiento() {
		return idEntidadNacimiento;
	}

	public void setIdEntidadNacimiento(int idEntidadNacimiento) {
		this.idEntidadNacimiento = idEntidadNacimiento;
	}

	public int getIdEstadoCivil() {
		return idEstadoCivil;
	}

	public void setIdEstadoCivil(int idEstadoCivil) {
		this.idEstadoCivil = idEstadoCivil;
	}

	public int getIdTipoDiscapacidad() {
		return idTipoDiscapacidad;
	}

	public void setIdTipoDiscapacidad(int idTipoDiscapacidad) {
		this.idTipoDiscapacidad = idTipoDiscapacidad;
	}

	public int getIdMedioPortal() {
		return idMedioPortal;
	}

	public void setIdMedioPortal(int idMedioPortal) {
		this.idMedioPortal = idMedioPortal;
	}

	public int getConfidencialidadDatos() {
		return confidencialidadDatos;
	}

	public void setConfidencialidadDatos(int confidencialidadDatos) {
		this.confidencialidadDatos = confidencialidadDatos;
	}

	public int getVeracidadDatos() {
		return veracidadDatos;
	}

	public void setVeracidadDatos(int veracidadDatos) {
		this.veracidadDatos = veracidadDatos;
	}

	public int getAceptacionTerminos() {
		return aceptacionTerminos;
	}

	public void setAceptacionTerminos(int aceptacionTerminos) {
		this.aceptacionTerminos = aceptacionTerminos;
	}
	
	/*
	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	*/
	public Calendar getFechaAlta(){
		return fechaAlta;
	}
	
	public void setFechaAlta(Calendar fechaAlta){
		this.fechaAlta = fechaAlta;
	}

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	public Date getFechaUltimaActualizacion() {
		return fechaUltimaActualizacion;
	}

	public void setFechaUltimaActualizacion(Date fechaUltimaActualizacion) {
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public int getEstiloCv() {
		return estiloCv;
	}

	public void setEstiloCv(int estiloCv) {
		this.estiloCv = estiloCv;
	}
	/**
	 * @return the evaluaCv
	 */
	public int getEvaluaCv() {
		return evaluaCv;
	}

	/**
	 * @param evaluaCv the evaluaCv to set
	 */
	public void setEvaluaCv(int evaluaCv) {
		this.evaluaCv = evaluaCv;
	}

	public UsuarioVO getUsuarioVO() {
		return usuarioVO;
	}

	public void setUsuarioVO(UsuarioVO usuarioVO) {
		this.usuarioVO = usuarioVO;
	}
	
	public TelefonoVo getTelefonoVo() {
		return telefonoVo;
	}

	public void setTelefonoVo(TelefonoVo telefonoVo) {
		this.telefonoVo = telefonoVo;
	}

	public DomicilioVO getDomicilioVo() {
		return domicilioVo;
	}

	public void setDomicilioVo(DomicilioVO domicilioVo) {
		this.domicilioVo = domicilioVo;
	}

	public String getFechaNacimientoString() {
		return fechaNacimientoString;
	}

	public void setFechaNacimientoString(String fechaNacimientoString) {
		this.fechaNacimientoString = fechaNacimientoString;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	public String getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	public String getMunicipioEntidad() {
		return municipioEntidad;
	}

	public void setMunicipioEntidad(String municipioEntidad) {
		this.municipioEntidad = municipioEntidad;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}
	
	public String getsSalario() {
		String salary = String.valueOf(this.salario);
		int index = salary.lastIndexOf(".");
		if (index > 1)
			this.sSalario = salary.substring(0,index);
		else this.sSalario = salary;
		return sSalario;
	}

	public void setsSalario(String sSalario) {
		this.sSalario = sSalario;
	}

	public long getIdOferta() {
		return idOferta;
	}

	public void setIdOferta(long idOferta) {
		this.idOferta = idOferta;
	}

	@Override
	public String toString() {
		return "CandidatoVo [usuarioVO=" + usuarioVO + ", telefonoVo="
				+ telefonoVo + ", domicilioVo=" + domicilioVo
				+ ", idCandidato=" + idCandidato + ", idUsuario=" + idUsuario
				+ ", curp=" + curp + ", nombre=" + nombre + ", apellido1="
				+ apellido1 + ", apellido2=" + apellido2 + ", genero=" + genero
				+ ", fechaNacimiento=" + fechaNacimiento + ", edad=" + edad
				+ ", idEntidadNacimiento=" + idEntidadNacimiento
				+ ", idEstadoCivil=" + idEstadoCivil + ", idTipoDiscapacidad="
				+ idTipoDiscapacidad + ", idMedioPortal=" + idMedioPortal
				+ ", confidencialidadDatos=" + confidencialidadDatos
				+ ", veracidadDatos=" + veracidadDatos
				+ ", aceptacionTerminos=" + aceptacionTerminos + ", fechaAlta="
				+ fechaAlta + ", estatus=" + estatus
				+ ", fechaUltimaActualizacion=" + fechaUltimaActualizacion
				+ ", correoElectronico=" + correoElectronico + ", estiloCv="
				+ estiloCv
				+ ", fechaNacimientoString=" + (fechaNacimientoString != null ? fechaNacimientoString : "") 				
				+ "]";
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
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

	public String getDescEstatus() {
		return descEstatus;
	}
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	public void setDescEstatus(String descEstatus) {
		this.descEstatus = descEstatus;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
		this.entidadNacimiento = entidadNacimiento;
	}

	/**
	 * @return the generoString
	 */
	public String getGeneroString() {
		return generoString;
	}

	/**
	 * @param generoString the generoString to set
	 */
	public void setGeneroString(String generoString) {
		this.generoString = generoString;
	}

	/**
	 * @return the idEntidadNacimientoString
	 */
	public String getIdEntidadNacimientoString() {
		return idEntidadNacimientoString;
	}

	/**
	 * @param idEntidadNacimientoString the idEntidadNacimientoString to set
	 */
	public void setIdEntidadNacimientoString(String idEntidadNacimientoString) {
		this.idEntidadNacimientoString = idEntidadNacimientoString;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return the tipoError
	 */
	public String getTipoError() {
		return TipoError;
	}

	/**
	 * @return the codigoError
	 */
	public String getCodigoError() {
		return CodigoError;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @param tipoError the tipoError to set
	 */
	public void setTipoError(String tipoError) {
		TipoError = tipoError;
	}

	/**
	 * @param codigoError the codigoError to set
	 */
	public void setCodigoError(String codigoError) {
		CodigoError = codigoError;
	}


	public String getDescTipoContrato() {
		return descTipoContrato;
	}

	public void setDescTipoContrato(String descTipoContrato) {
		this.descTipoContrato = descTipoContrato;
	}

	public String getHorarioEmpleo() {
		return horarioEmpleo;
	}

	public void setHorarioEmpleo(String horarioEmpleo) {
		this.horarioEmpleo = horarioEmpleo;
	}

	public String getDispViajarFuera() {
		return dispViajarFuera;
	}

	public void setDispViajarFuera(String dispViajarFuera) {
		this.dispViajarFuera = dispViajarFuera;
	}

	public String getDispRadicarFuera() {
		return dispRadicarFuera;
	}

	public void setDispRadicarFuera(String dispRadicarFuera) {
		this.dispRadicarFuera = dispRadicarFuera;
	}

	/**
	 * @return the statusOper
	 */
	public String getStatusOper() {
		return statusOper;
	}

	/**
	 * @param statusOper the statusOper to set
	 */
	public void setStatusOper(String statusOper) {
		this.statusOper = statusOper;
	}

	public GradoAcademicoVO getGradoacademicoVO() {
		return gradoacademicoVO;
	}

	public void setGradoacademicoVO(GradoAcademicoVO gradoacademicoVO) {
		this.gradoacademicoVO = gradoacademicoVO;
	}

	/**
	 * @return the lstIdiomas
	 */
	public List<IdiomaVO> getLstIdiomas() {
		return lstIdiomas;
	}

	/**
	 * @param lstIdiomas the lstIdiomas to set
	 */
	public void setLstIdiomas(List<IdiomaVO> lstIdiomas) {
		this.lstIdiomas = lstIdiomas;
	}	
	
	/**
	 * @return the idEstatusLaboral
	 */
	public int getIdEstatusLaboral() {
		return idEstatusLaboral;
	}

	/**
	 * @param idEstatusLaboral the idEstatusLaboral to set
	 */
	public void setIdEstatusLaboral(int idEstatusLaboral) {
		this.idEstatusLaboral = idEstatusLaboral;
	}

	/**
	 * @return the descEstatusLaboral
	 */
	public String getDescEstatusLaboral() {
		return descEstatusLaboral;
	}

	/**
	 * @param descEstatusLaboral the descEstatusLaboral to set
	 */
	public void setDescEstatusLaboral(String descEstatusLaboral) {
		this.descEstatusLaboral = descEstatusLaboral;
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
	 * @return the descRazonBusqueda
	 */
	public String getDescRazonBusqueda() {
		return descRazonBusqueda;
	}

	/**
	 * @param descRazonBusqueda the descRazonBusqueda to set
	 */
	public void setDescRazonBusqueda(String descRazonBusqueda) {
		this.descRazonBusqueda = descRazonBusqueda;
	}
	
	public int getCompatibilidad() {
		return compatibilidad;
	}

	public void setCompatibilidad(int compatibilidad) {
		this.compatibilidad = compatibilidad;
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
	 * 
	 * @return Numero de seguro social
	 */
	public String getNss() {
		return nss;
	}

	/**
	 * 
	 * @param Numero de seguro social
	 */
	public void setNss(String nss) {
		this.nss = nss;
	}

	/**
	 * 
	 * @return CreditoInfonavit
	 */
	public Long getCreditoInfonavit() {
		return creditoInfonavit;
	}

	/**
	 * 
	 * @param creditoInfonavit
	 */
	public void setCreditoInfonavit(Long creditoInfonavit) {
		this.creditoInfonavit = creditoInfonavit;
	}

	public String getMedioContacto() {
		return medioContacto;
	}

	public void setMedioContacto(String medioContacto) {
		this.medioContacto = medioContacto;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Integer getApoyoProspera() {
		return apoyoProspera;
	}

	public void setApoyoProspera(Integer apoyoProspera) {
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

	public Date getFechaUltimoAcceso() {
		return fechaUltimoAcceso;
	}

	public void setFechaUltimoAcceso(Date fechaUltimoAcceso) {
		this.fechaUltimoAcceso = fechaUltimoAcceso;
	}

	public SolicitanteVO getSolicitanteVO() {
		return solicitanteVO;
	}

	public void setSolicitanteVO(SolicitanteVO solicitanteVO) {
		this.solicitanteVO = solicitanteVO;
	}

	public Long getIdFuente() {
		return idFuente;
	}

	public void setIdFuente(Long idFuente) {
		this.idFuente = idFuente;
	}

	public String getDiscapacidades() {
		return discapacidades;
	}

	public void setDiscapacidades(String discapacidades) {
		this.discapacidades = discapacidades;
	}
	
	public boolean isDiscapacitadoAuditivo(){	
		return ("1".equals(String.valueOf(this.discapacidades.charAt(0))) ? true : false);
	}

	public boolean isDiscapacitadoIntelectual(){
		return ("1".equals(String.valueOf(this.discapacidades.charAt(1))) ? true : false);		
	}	
	
	public boolean isDiscapacitadoMental(){
		return ("1".equals(String.valueOf(this.discapacidades.charAt(2))) ? true : false);		
	}
	
	public boolean isDiscapacitadoMotor(){
		return ("1".equals(String.valueOf(this.discapacidades.charAt(3))) ? true : false);		
	}
	
	public boolean isDiscapacitadoVisual(){
		return ("1".equals(String.valueOf(this.discapacidades.charAt(4))) ? true : false);		
	}

	public boolean isDiscapacitado(){
		
		if (this.isDiscapacitadoAuditivo())
			return true;

		if (this.isDiscapacitadoIntelectual())
			return true;		

		if (this.isDiscapacitadoMental())
			return true;
		
		if (this.isDiscapacitadoMotor())
			return true;
		
		if (this.isDiscapacitadoVisual())
			return true;		
		
		return false;
	}
	
	public void setDiscapacitadoAuditivo(boolean valor){
		this.setCharN(valor, 0);
	}

	public void setDiscapacitadoIntelectual(boolean valor){
		this.setCharN(valor, 1);
	}

	public void setDiscapacitadoMental(boolean valor){
		this.setCharN(valor, 2);
	}
	
	public void setDiscapacitadoMotor(boolean valor){
		this.setCharN(valor, 3);
	}

	public void setDiscapacitadoVisual(boolean valor){
		this.setCharN(valor, 4);
	}	
	
	public Long getIdOficina() {
		return idOficina;
	}

	public void setIdOficina(Long idOficina) {
		this.idOficina = idOficina;
	}

	public void setDiscapacitadoNinguna(){
		this.discapacidades = new String("00000");
	}
	
	public void setIdDiscapacidad(long idDiscapacidad){
		if (idDiscapacidad == TIPO_DISCAPACIDAD.AUDITIVA.getIdOpcion())
			this.setDiscapacitadoAuditivo(true);		
		else if (idDiscapacidad == TIPO_DISCAPACIDAD.INTELECTUAL.getIdOpcion())
			this.setDiscapacitadoIntelectual(true);				
		else if (idDiscapacidad == TIPO_DISCAPACIDAD.MENTAL.getIdOpcion())
			this.setDiscapacitadoMental(true);
		else if (idDiscapacidad == TIPO_DISCAPACIDAD.MOTRIZ.getIdOpcion())
			this.setDiscapacitadoMotor(true);		
		else if (idDiscapacidad == TIPO_DISCAPACIDAD.VISUAL.getIdOpcion())
			this.setDiscapacitadoVisual(true);		
		else 
			this.setDiscapacitadoNinguna();		
	}

	// Se declara ya que marca muchos mensajes al log al transformarlo a json
	public long setIdDiscapacidad(){
		return idTipoDiscapacidad;
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
		
		discapacidades = new String(str);		
	}
	
	public String getDescripcionDiscapacidades(String discapacidades){
		StringBuilder descripciones = new StringBuilder();
		if(null !=discapacidades){
			if(discapacidades.equals("00000")){
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
					}
				}				
			}
		} else {
			descripciones.append(TIPO_DISCAPACIDAD.NINGUNA.getOpcion()); 
		}
		return descripciones.toString();
	}

	public String getSubAreaLaboralDescripcion() {
		return subAreaLaboralDescripcion;
	}

	public void setSubAreaLaboralDescripcion(String subAreaLaboralDescripcion) {
		this.subAreaLaboralDescripcion = subAreaLaboralDescripcion;
	}

	public String getAreaLaboralDescripcion() {
		return areaLaboralDescripcion;
	}

	public void setAreaLaboralDescripcion(String areaLaboralDescripcion) {
		this.areaLaboralDescripcion = areaLaboralDescripcion;
	}	

	
}
