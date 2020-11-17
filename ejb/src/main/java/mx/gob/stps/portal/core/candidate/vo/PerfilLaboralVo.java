package mx.gob.stps.portal.core.candidate.vo;

import java.io.Serializable;
import java.util.Date;

public class PerfilLaboralVo implements Serializable {
	
	/**
	 * valor utilizado para la serializaci&oacute;n de las instancias del objeto
	 */
	private static final long serialVersionUID = -7531727659231807857L;
	
	/**
	 * identificador del candidato 
	 */
	private long idCandidato;

	private int contactoCorreo;

	private int contactoTelefono;

	private long horarioContactoDe;

	private long horarioContactoA;

	private long idRecibeOferta;

	private int empleadoActualmente;

	private long idRazonBusqueda;
	
	private String descripcionOtroMotivoBusq;

	private Date inicioBusqueda;

	private long idExperienciaCompu;

	private long idDominioCompu;

	private long idExperienciaOffice;

	private long idDominioOffice;

	private long idExperienciaInternet;

	private long idDominioInternet;

	private long idExperienciaTotal;
	
	private String experiencia;
	
	private long idSectorMayorExpr;

	private long idAreaLaboralMayorExpr;

	private long idOcupacionMayorExpr;

	private int disponibilidadViajar;

	private int disponibilidadRadicar;

	private int computacionBasica;

	private int computacionAvanzada;

	private byte[] fotografia;

	private String urlVideoc;
	
	private String puestoMayorExpr;

	private int terminosVideoc;

	private String descripcionVideoc;

	private Date fechaAlta;

	protected int sinEstudios;
	
	protected int sinExperiencia;
	
	protected int estatusVideoc;
	
	private long licenciaId;
	
	public PerfilLaboralVo() {}
	
//	public PerfilLaboralVo(long idCandidato, int contactoCorreo,
//			int contactoTelefono, long horarioContactoDe, long horarioContactoA,
//			long idRecibeOferta, int empleadoActualmente, long idRazonBusqueda,
//			Date inicioBusqueda, long idExperienciaCompu, long idDominioCompu,
//			long idExperienciaOffice, long idDominioOffice,
//			long idExperienciaInternet, long idDominioInternet,
//			long idExperienciaTotal, long idSectorMayorExpr,
//			String puestoMayorExpr, long idAreaLaboralMayorExpr,
//			long idOcupacionMayorExpr, int disponibilidadViajar,
//			int disponibilidadRadicar, int computacionBasica,
//			int computacionAvanzada, byte[] fotografia, String urlVideoc,
//			int terminosVideoc, String descripcionVideoc, Date fechaAlta,
//			int sinEstudios, int estatusVideoc) {
//
//		this.idCandidato = idCandidato;
//		this.contactoCorreo = contactoCorreo;
//		this.contactoTelefono = contactoTelefono;
//		this.horarioContactoDe = horarioContactoDe;
//		this.horarioContactoA = horarioContactoA;
//		this.idRecibeOferta = idRecibeOferta;
//		this.empleadoActualmente = empleadoActualmente;
//		this.idRazonBusqueda = idRazonBusqueda;
//		this.inicioBusqueda = inicioBusqueda;
//		this.idExperienciaCompu = idExperienciaCompu;
//		this.idDominioCompu = idDominioCompu;
//		this.idExperienciaOffice = idExperienciaOffice;
//		this.idDominioOffice = idDominioOffice;
//		this.idExperienciaInternet = idExperienciaInternet;
//		this.idDominioInternet = idDominioInternet;
//		this.idExperienciaTotal = idExperienciaTotal;
//		this.idSectorMayorExpr = idSectorMayorExpr;
//		this.puestoMayorExpr = puestoMayorExpr;
//		this.idAreaLaboralMayorExpr = idAreaLaboralMayorExpr;
//		this.idOcupacionMayorExpr = idOcupacionMayorExpr;
//		this.disponibilidadViajar = disponibilidadViajar;
//		this.disponibilidadRadicar = disponibilidadRadicar;
//		this.computacionBasica = computacionBasica;
//		this.computacionAvanzada = computacionAvanzada;
//		this.fotografia = fotografia;
//		this.urlVideoc = urlVideoc;
//		this.terminosVideoc = terminosVideoc;
//		this.descripcionVideoc = descripcionVideoc;
//		this.fechaAlta = fechaAlta;
//		this.sinEstudios = sinEstudios;
//		this.estatusVideoc = estatusVideoc;
//	}

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
	 * @return the horarioContactoDe
	 */
	public long getHorarioContactoDe() {
		return horarioContactoDe;
	}

	/**
	 * @param horarioContactoDe the horarioContactoDe to set
	 */
	public void setHorarioContactoDe(long horarioContactoDe) {
		this.horarioContactoDe = horarioContactoDe;
	}

	/**
	 * @return the horarioContactoA
	 */
	public long getHorarioContactoA() {
		return horarioContactoA;
	}

	/**
	 * @param horarioContactoA the horarioContactoA to set
	 */
	public void setHorarioContactoA(long horarioContactoA) {
		this.horarioContactoA = horarioContactoA;
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
		this.idRecibeOferta = idRecibeOferta;
	}

	/**
	 * @return the empleadoActualmente
	 */
	public int getEmpleadoActualmente() {
		return empleadoActualmente;
	}

	/**
	 * @param empleadoActualmente the empleadoActualmente to set
	 */
	public void setEmpleadoActualmente(int empleadoActualmente) {
		this.empleadoActualmente = empleadoActualmente;
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
		return inicioBusqueda;
	}

	/**
	 * @param inicioBusqueda the inicioBusqueda to set
	 */
	public void setInicioBusqueda(Date inicioBusqueda) {
		this.inicioBusqueda = inicioBusqueda;
	}

	/**
	 * @return the idExperienciaCompu
	 */
	public long getIdExperienciaCompu() {
		return idExperienciaCompu;
	}

	/**
	 * @param idExperienciaCompu the idExperienciaCompu to set
	 */
	public void setIdExperienciaCompu(long idExperienciaCompu) {
		this.idExperienciaCompu = idExperienciaCompu;
	}

	/**
	 * @return the idDominioCompu
	 */
	public long getIdDominioCompu() {
		return idDominioCompu;
	}

	/**
	 * @param idDominioCompu the idDominioCompu to set
	 */
	public void setIdDominioCompu(long idDominioCompu) {
		this.idDominioCompu = idDominioCompu;
	}

	/**
	 * @return the idExperienciaOffice
	 */
	public long getIdExperienciaOffice() {
		return idExperienciaOffice;
	}

	/**
	 * @param idExperienciaOffice the idExperienciaOffice to set
	 */
	public void setIdExperienciaOffice(long idExperienciaOffice) {
		this.idExperienciaOffice = idExperienciaOffice;
	}

	/**
	 * @return the idDominioOffice
	 */
	public long getIdDominioOffice() {
		return idDominioOffice;
	}

	/**
	 * @param idDominioOffice the idDominioOffice to set
	 */
	public void setIdDominioOffice(long idDominioOffice) {
		this.idDominioOffice = idDominioOffice;
	}

	/**
	 * @return the idExperienciaInternet
	 */
	public long getIdExperienciaInternet() {
		return idExperienciaInternet;
	}

	/**
	 * @param idExperienciaInternet the idExperienciaInternet to set
	 */
	public void setIdExperienciaInternet(long idExperienciaInternet) {
		this.idExperienciaInternet = idExperienciaInternet;
	}

	/**
	 * @return the idDominioInternet
	 */
	public long getIdDominioInternet() {
		return idDominioInternet;
	}

	/**
	 * @param idDominioInternet the idDominioInternet to set
	 */
	public void setIdDominioInternet(long idDominioInternet) {
		this.idDominioInternet = idDominioInternet;
	}

	/**
	 * @return the idExperienciaTotal
	 */
	public long getIdExperienciaTotal() {
		return idExperienciaTotal;
	}

	/**
	 * @param idExperienciaTotal the idExperienciaTotal to set
	 */
	public void setIdExperienciaTotal(long idExperienciaTotal) {
		this.idExperienciaTotal = idExperienciaTotal;
	}

	public String getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(String experiencia) {
		this.experiencia = experiencia;
	}

	/**
	 * @return the idSectorMayorExpr
	 */
	public long getIdSectorMayorExpr() {
		return idSectorMayorExpr;
	}

	/**
	 * @param idSectorMayorExpr the idSectorMayorExpr to set
	 */
	public void setIdSectorMayorExpr(long idSectorMayorExpr) {
		this.idSectorMayorExpr = idSectorMayorExpr;
	}

	/**
	 * @return the idAreaLaboralMayorExpr
	 */
	public long getIdAreaLaboralMayorExpr() {
		return idAreaLaboralMayorExpr;
	}

	/**
	 * @param idAreaLaboralMayorExpr the idAreaLaboralMayorExpr to set
	 */
	public void setIdAreaLaboralMayorExpr(long idAreaLaboralMayorExpr) {
		this.idAreaLaboralMayorExpr = idAreaLaboralMayorExpr;
	}

	/**
	 * @return the idOcupacionMayorExpr
	 */
	public long getIdOcupacionMayorExpr() {
		return idOcupacionMayorExpr;
	}

	/**
	 * @param idOcupacionMayorExpr the idOcupacionMayorExpr to set
	 */
	public void setIdOcupacionMayorExpr(long idOcupacionMayorExpr) {
		this.idOcupacionMayorExpr = idOcupacionMayorExpr;
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
	 * @return the computacionBasica
	 */
	public int getComputacionBasica() {
		return computacionBasica;
	}

	/**
	 * @param computacionBasica the computacionBasica to set
	 */
	public void setComputacionBasica(int computacionBasica) {
		this.computacionBasica = computacionBasica;
	}

	/**
	 * @return the computacionAvanzada
	 */
	public int getComputacionAvanzada() {
		return computacionAvanzada;
	}

	/**
	 * @param computacionAvanzada the computacionAvanzada to set
	 */
	public void setComputacionAvanzada(int computacionAvanzada) {
		this.computacionAvanzada = computacionAvanzada;
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
	 * @return the urlVideoc
	 */
	public String getUrlVideoc() {
		return urlVideoc;
	}

	/**
	 * @param urlVideoc the urlVideoc to set
	 */
	public void setUrlVideoc(String urlVideoc) {
		this.urlVideoc = urlVideoc;
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
		this.puestoMayorExpr = puestoMayorExpr;
	}

	/**
	 * @return the terminosVideoc
	 */
	public int getTerminosVideoc() {
		return terminosVideoc;
	}

	/**
	 * @param terminosVideoc the terminosVideoc to set
	 */
	public void setTerminosVideoc(int terminosVideoc) {
		this.terminosVideoc = terminosVideoc;
	}

	/**
	 * @return the descripcionVideoc
	 */
	public String getDescripcionVideoc() {
		return descripcionVideoc;
	}

	/**
	 * @param descripcionVideoc the descripcionVideoc to set
	 */
	public void setDescripcionVideoc(String descripcionVideoc) {
		this.descripcionVideoc = descripcionVideoc;
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

	public int getSinEstudios() {
		return sinEstudios;
	}

	public void setSinEstudios(int sinEstudios) {
		this.sinEstudios = sinEstudios;
	}
	public int getSinExperiencia() {
		return sinExperiencia;
	}
	public void setSinExperiencia(int sinExperiencia) {
		this.sinExperiencia = sinExperiencia;
	}

	public int getEstatusVideoc() {
		return estatusVideoc;
	}

	public void setEstatusVideoc(int estatusVideoc) {
		this.estatusVideoc = estatusVideoc;
	}

	/**
	 * @return the licenciaId
	 */
	public long getLicenciaId() {
		return licenciaId;
	}

	/**
	 * @param licenciaId the licenciaId to set
	 */
	public void setLicenciaId(long licenciaId) {
		this.licenciaId = licenciaId;
	}
	
}
