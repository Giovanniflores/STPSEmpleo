package mx.gob.stps.portal.core.oferta.detalle.vo;

import java.io.Serializable;
import java.util.Date;

import mx.gob.stps.portal.core.infra.utils.Constantes.EVENTO;
import mx.gob.stps.portal.utils.Catalogos.TIPO_PROPIETARIO;

public class OfertaCandidatoVO implements Serializable{

	private static final long serialVersionUID = 723099503294603461L;
	
	protected long idOfertaCandidato;
	protected long idOfertaEmpleo;
	protected long idCandidato;
	protected Date fechaAlta;
	protected int estatus;
	protected int idMotivo;
	protected int compatibilidad;
	
	protected Long idOficina;
	protected Long idVinculado;
	protected Long idUsuario;
	protected Integer postulacionCartera;
	protected Date fechaSeguimiento;
	protected Date fechaColocacion;
	protected Date fechaInicioContratacion;	
	protected Long idFuente;
	
	protected Date fechaModificacion;
	private String motivoDesc;
	private Date fechaContacto;
	private Integer conseguioEntrevista;
	private Date fechaEntrevista;
	private Integer motivoNoEntrevista;
	private Integer fuisteContratado;
	private Long idOficinaSeguimiento;
	private Long idUsuarioSeguimiento;
	private Long idFuenteSeguimiento;
	
	//para guardar en la bitacora
	protected TIPO_PROPIETARIO tipoPropietario;
	protected EVENTO evento;
	

	
	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public OfertaCandidatoVO() {
		this.fechaModificacion = new Date();
	}

	public OfertaCandidatoVO(long idOfertaCandidato, long idOfertaEmpleo, long idCandidato, Date fechaAlta, int estatus) {
		this.idOfertaEmpleo = idOfertaEmpleo;
		this.idCandidato = idCandidato;
		this.fechaAlta = fechaAlta;
		this.estatus = estatus;
		this.fechaModificacion = new Date();
	}
	
	public long getIdOfertaCandidato() {
		return idOfertaCandidato;
	}

	public void setIdOfertaCandidato(long idOfertaCandidato) {
		this.idOfertaCandidato = idOfertaCandidato;
	}

	/**
	 * Method 'getIdOfertaEmpleo'
	 * 
	 * @return long
	 */
	public long getIdOfertaEmpleo() {
		return idOfertaEmpleo;
	}

	/**
	 * Method 'setIdOfertaEmpleo'
	 * 
	 * @param idOfertaEmpleo
	 */
	public void setIdOfertaEmpleo(long idOfertaEmpleo) {
		this.idOfertaEmpleo = idOfertaEmpleo;
	}

	/**
	 * Method 'getIdCandidato'
	 * 
	 * @return long
	 */
	public long getIdCandidato() {
		return idCandidato;
	}

	/**
	 * Method 'setIdCandidato'
	 * 
	 * @param idCandidato
	 */
	public void setIdCandidato(long idCandidato) {
		this.idCandidato = idCandidato;
	}

	/**
	 * Method 'getIdMotivo'
	 * 
	 * @return int
	 */
	public int getIdMotivo() {
		return idMotivo;
	}

	/**
	 * Method 'setIdMotivo'
	 * 
	 * @param idMotivo
	 */
	public void setIdMotivo(int idMotivo) {
		this.idMotivo = idMotivo;
	}
	
	/**
	 * Method 'getFechaAlta'
	 * 
	 * @return Date
	 */
	public Date getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * Method 'setFechaAlta'
	 * 
	 * @param fechaAlta
	 */
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	/**
	 * Method 'getEstatus'
	 * 
	 * @return int
	 */
	public int getEstatus() {
		return estatus;
	}

	/**
	 * Method 'setEstatus'
	 * 
	 * @param estatus
	 */
	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	public int getCompatibilidad() {
		return compatibilidad;
	}

	public void setCompatibilidad(int compatibilidad) {
		this.compatibilidad = compatibilidad;
	}

	public Long getIdOficina() {
		return idOficina;
	}

	public void setIdOficina(Long idOficina) {
		this.idOficina = idOficina;
	}

	public Long getIdVinculado() {
		return idVinculado;
	}

	public void setIdVinculado(Long idVinculado) {
		this.idVinculado = idVinculado;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getPostulacionCartera() {
		return postulacionCartera;
	}

	public void setPostulacionCartera(Integer postulacionCartera) {
		this.postulacionCartera = postulacionCartera;
	}

	public Date getFechaSeguimiento() {
		return fechaSeguimiento;
	}

	public void setFechaSeguimiento(Date fechaSeguimiento) {
		this.fechaSeguimiento = fechaSeguimiento;
	}

	public Date getFechaColocacion() {
		return fechaColocacion;
	}

	public void setFechaColocacion(Date fechaColocacion) {
		this.fechaColocacion = fechaColocacion;
	}

	public Date getFechaInicioContratacion() {
		return fechaInicioContratacion;
	}

	public void setFechaInicioContratacion(Date fechaInicioContratacion) {
		this.fechaInicioContratacion = fechaInicioContratacion;
	}

	public Long getIdFuente() {
		return idFuente;
	}

	public void setIdFuente(Long idFuente) {
		this.idFuente = idFuente;
	}
	
	
	
	public TIPO_PROPIETARIO getTipoPropietario() {
		return tipoPropietario;
	}

	public void setTipoPropietario(TIPO_PROPIETARIO tipoPropietario) {
		this.tipoPropietario = tipoPropietario;
	}
	
	

	public EVENTO getEvento() {
		return evento;
	}

	public void setEvento(EVENTO evento) {
		this.evento = evento;
	}

	public String toString(){		
        StringBuilder builder = new StringBuilder();
        builder.append("OfertaCandidatoVO [idOfertaCandidato=");
        builder.append(idOfertaCandidato);
        builder.append(", idOfertaEmpleo=");
        builder.append(idOfertaEmpleo);
        builder.append(", idCandidato=");
        builder.append(idCandidato);
        builder.append(", fechaAlta=");
        builder.append(fechaAlta);
        builder.append(", estatus=");
        builder.append(estatus);
        builder.append(", idMotivo=");
        builder.append(idMotivo);
        builder.append(", compatibilidad=");
        builder.append(compatibilidad);
        builder.append(", idOficina=");
        builder.append(idOficina);
        builder.append(", idVinculado=");
        builder.append(idVinculado);
        builder.append(", idUsuario=");
        builder.append(idUsuario);        
        builder.append(", postulacionCartera=");
        builder.append(postulacionCartera);
        builder.append(", fechaSeguimiento=");
        builder.append(fechaSeguimiento);
        builder.append(", fechaColocacion=");
        builder.append(fechaColocacion);
        builder.append(", fechaInicioContratacion=");
        builder.append(fechaInicioContratacion);
        builder.append(", idFuente=");
        builder.append(idFuente);                
        builder.append("]");
        return builder.toString();		
	}

	public Date getFechaContacto() {
		return fechaContacto;
	}

	public void setFechaContacto(Date fechaContacto) {
		this.fechaContacto = fechaContacto;
	}

	public Date getFechaEntrevista() {
		return fechaEntrevista;
	}

	public void setFechaEntrevista(Date fechaEntrevista) {
		this.fechaEntrevista = fechaEntrevista;
	}

	public Integer getMotivoNoEntrevista() {
		return motivoNoEntrevista;
	}

	public void setMotivoNoEntrevista(Integer motivoNoEntrevista) {
		this.motivoNoEntrevista = motivoNoEntrevista;
	}

	public Integer getFuisteContratado() {
		return fuisteContratado;
	}

	public void setFuisteContratado(Integer fuisteContratado) {
		this.fuisteContratado = fuisteContratado;
	}

	public String getMotivoDesc() {
		return motivoDesc;
	}

	public void setMotivoDesc(String motivoDesc) {
		this.motivoDesc = motivoDesc;
	}

	public Integer getConseguioEntrevista() {
		return conseguioEntrevista;
	}

	public void setConseguioEntrevista(Integer conseguioEntrevista) {
		this.conseguioEntrevista = conseguioEntrevista;
	}

	public Long getIdOficinaSeguimiento() {
		return idOficinaSeguimiento;
	}

	public void setIdOficinaSeguimiento(Long idOficinaSeguimiento) {
		this.idOficinaSeguimiento = idOficinaSeguimiento;
	}

	public Long getIdUsuarioSeguimiento() {
		return idUsuarioSeguimiento;
	}

	public void setIdUsuarioSeguimiento(Long idUsuarioSeguimiento) {
		this.idUsuarioSeguimiento = idUsuarioSeguimiento;
	}

	public Long getIdFuenteSeguimiento() {
		return idFuenteSeguimiento;
	}

	public void setIdFuenteSeguimiento(Long idFuenteSeguimiento) {
		this.idFuenteSeguimiento = idFuenteSeguimiento;
	}
}
