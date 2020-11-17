package mx.gob.stps.portal.web.candidate.form;

import java.util.Date;
import java.util.List;
import org.apache.struts.action.ActionForm;
import mx.gob.stps.portal.persistencia.vo.HistoricoBusquedaPPCVO;

/**********************************************************
//
// Nombre: OPR  Fecha:	8.10.14
// Form que recoge los datos de la tabla No Postulaciones
//
/************************************************************/
public class AdministracionNoPostulacionesForm extends ActionForm{
	
	private static final long serialVersionUID = 2562715592351468996L;

	private long idCandidato;
	 
	private long idMotivo;
	 
	private long idOfertaEmpleo;

	private long idEvento;
	
	private long idEmpresa; 
	
	private long compatibilidad;
	 
	private Date fechaNoPostulacion;
 
	private String tituloOferta;
	
	private String ubicacion;
	
	private String mensajes;
	
	private double salarioOferta;
	
	private String resumenOferta;
	
	private String motivoDesc;
	
	public String getMotivoDesc() {
		return motivoDesc;
	}

	public void setMotivoDesc(String motivoDesc) {
		this.motivoDesc = motivoDesc;
	}

	private List<HistoricoBusquedaPPCVO> administracionNoPostulacionesVO;
	
	private boolean prerrequisitos;


	public boolean getPrerrequisitos() {
		return prerrequisitos;
	}

	public void setPrerrequisitos(boolean prerrequisitos) {
		this.prerrequisitos = prerrequisitos;
	}

	public List<HistoricoBusquedaPPCVO> getAdministracionNoPostulacionesVO() {
		return administracionNoPostulacionesVO;
	}

	public void setAdministracionNoPostulacionesVO(List<HistoricoBusquedaPPCVO> administracionNoPostulacionesVO) {
		this.administracionNoPostulacionesVO = administracionNoPostulacionesVO;
	}

	public long getIdCandidato() {
		return idCandidato;
	}

	public void setIdCandidato(long idCandidato) {
		this.idCandidato = idCandidato;
	}

	public long getIdMotivo() {
		return idMotivo;
	}

	public void setIdMotivo(long idMotivo) {
		this.idMotivo = idMotivo;
	}

	public long getIdOfertaEmpleo() {
		return idOfertaEmpleo;
	}

	public void setIdOfertaEmpleo(long idOfertaEmpleo) {
		this.idOfertaEmpleo = idOfertaEmpleo;
	}

	public long getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(long idEvento) {
		this.idEvento = idEvento;
	}

	public long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public long getCompatibilidad() {
		return compatibilidad;
	}

	public void setCompatibilidad(long compatibilidad) {
		this.compatibilidad = compatibilidad;
	}

	public Date getFechaNoPostulacion() {
		return fechaNoPostulacion;
	}

	public void setFechaNoPostulacion(Date fechaNoPostulacion) {
		this.fechaNoPostulacion = fechaNoPostulacion;
	}

	public String getTituloOferta() {
		return tituloOferta;
	}

	public void setTituloOferta(String tituloOferta) {
		this.tituloOferta = tituloOferta;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getMensajes() {
		return mensajes;
	}

	public void setMensajes(String mensajes) {
		this.mensajes = mensajes;
	}

	public double getSalarioOferta() {
		return salarioOferta;
	}

	public void setSalarioOferta(double salarioOferta) {
		this.salarioOferta = salarioOferta;
	}

	public String getResumenOferta() {
		return resumenOferta;
	}

	public void setResumenOferta(String resumenOferta) {
		this.resumenOferta = resumenOferta;
	}
	
	public void reset(){
		this.idCandidato = 0;
		this.idMotivo = 0;
		this.idOfertaEmpleo = 0;
		this.idEvento = 0;
		this.idEmpresa = 0;
		this.compatibilidad = 0;
		this.fechaNoPostulacion = null;
		this.tituloOferta = null;
		this.ubicacion = null;
		this.mensajes = null;
		this.salarioOferta = 0;
		this.resumenOferta = null;
	}
	
}