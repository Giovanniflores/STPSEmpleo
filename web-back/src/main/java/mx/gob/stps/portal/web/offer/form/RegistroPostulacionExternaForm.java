package mx.gob.stps.portal.web.offer.form;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import mx.gob.stps.portal.persistencia.entity.PostulacionExterna;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.utils.Catalogos;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessage;

public class RegistroPostulacionExternaForm extends ActionForm {

	private static final long serialVersionUID = -62263848243518020L;

	public static final int ACTION_INIT = 0;
	public static final int ACTION_REMOVE = 1;	
	
	private Long idPostulacionExterna;	
	private int action = ACTION_INIT;
	private long idUsuario;
	private long idCandidato;
	private String nombreEmpresa;
	private String nombreContacto;
	private String cargoContacto;
	private Integer idTipoTelefono;
	private Integer idTipoTelefonoFijo;
	private Integer idTipoTelefonoCelular;	
	private String accesoCelular;
	private String accesoFijo;		
	private String acceso;
	private String clave;
	private String telefono;
	private String extension;
	private String correoElectronico;
	private String tituloOferta;
	private double salarioMensual;
	private String comoTeEnterasteOferta;
	
	private int diaContacto;
	private int mesContacto;
	private int anioContacto;	
	private int diaEntrevista;
	private int mesEntrevista;
	private int anioEntrevista;	
	
	private String orderType;
	private Integer columnNumber;
	private List<PostulacionExterna> postulaciones;
	private PostulacionExterna postulacion;
	private int diaContrato;
	private int mesContrato;
	private int anioContrato;
	private Long idMotivoNoContratacion;
	private List<CatalogoOpcionVO> motivos;
	private String otroMotivo;
	
	
	
	public void setAction(int action) {
		this.action = action;
	}
	
	public int getAction() {
		return this.action;
	}
	
	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public long getIdCandidato() {
		return idCandidato;
	}

	public void setIdCandidato(long idCandidato) {
		this.idCandidato = idCandidato;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public String getNombreContacto() {
		return nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	public String getCargoContacto() {
		return cargoContacto;
	}

	public void setCargoContacto(String cargoContacto) {
		this.cargoContacto = cargoContacto;
	}

	public Integer getIdTipoTelefono() {
		return idTipoTelefono;
	}

	public void setIdTipoTelefono(Integer idTipoTelefono) {
		this.idTipoTelefono = idTipoTelefono;
	}

	public Integer getIdTipoTelefonoFijo() {
		return idTipoTelefonoFijo;
	}

	public void setIdTipoTelefonoFijo(Integer idTipoTelefonoFijo) {
		this.idTipoTelefonoFijo = idTipoTelefonoFijo;
	}

	public Integer getIdTipoTelefonoCelular() {
		return idTipoTelefonoCelular;
	}

	public void setIdTipoTelefonoCelular(Integer idTipoTelefonoCelular) {
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

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getTituloOferta() {
		return tituloOferta;
	}

	public void setTituloOferta(String tituloOferta) {
		this.tituloOferta = tituloOferta;
	}

	public double getSalarioMensual() {
		return salarioMensual;
	}

	public void setSalarioMensual(double salarioMensual) {
		this.salarioMensual = salarioMensual;
	}

	public String getComoTeEnterasteOferta() {
		return comoTeEnterasteOferta;
	}

	public void setComoTeEnterasteOferta(String comoTeEnterasteOferta) {
		this.comoTeEnterasteOferta = comoTeEnterasteOferta;
	}

	public int getDiaContacto() {
		return diaContacto;
	}

	public void setDiaContacto(int diaContacto) {
		this.diaContacto = diaContacto;
	}

	public int getMesContacto() {
		return mesContacto;
	}

	public void setMesContacto(int mesContacto) {
		this.mesContacto = mesContacto;
	}

	public int getAnioContacto() {
		return anioContacto;
	}

	public void setAnioContacto(int anioContacto) {
		this.anioContacto = anioContacto;
	}

	public int getDiaEntrevista() {
		return diaEntrevista;
	}

	public void setDiaEntrevista(int diaEntrevista) {
		this.diaEntrevista = diaEntrevista;
	}

	public int getMesEntrevista() {
		return mesEntrevista;
	}

	public void setMesEntrevista(int mesEntrevista) {
		this.mesEntrevista = mesEntrevista;
	}

	public int getAnioEntrevista() {
		return anioEntrevista;
	}

	public void setAnioEntrevista(int anioEntrevista) {
		this.anioEntrevista = anioEntrevista;
	}	
	
	
	
	
	public String toString() {
		return "RegistroPostulacionExternaForm [action=" + action
				+ ", idUsuario=" + idUsuario
				+ ", idCandidato=" + idCandidato
				+ ", nombreEmpresa=" + nombreEmpresa
				+ ", nombreContacto=" + nombreContacto
				+ ", cargoContacto=" + cargoContacto
				+ ", idTipoTelefono=" + idTipoTelefono
				+ ", idTipoTelefonoFijo=" + idTipoTelefonoFijo
				+ ", idTipoTelefonoCelular=" + idTipoTelefonoCelular
				+ ", acceso=" + acceso
				+ ", clave=" + clave
				+ ", telefono=" + telefono
				+ ", tituloOferta=" + tituloOferta
				+ ", salarioMensual=" + salarioMensual
				+ ", comoTeEnterasteOferta=" + comoTeEnterasteOferta
				+ ", diaContacto=" + diaContacto
				+ ", mesContacto=" + mesContacto
				+ ", anioContacto=" + anioContacto
				+ ", diaEntrevista=" + diaEntrevista
				+ ", mesEntrevista=" + mesEntrevista
				+ ", anioEntrevista=" + anioEntrevista
				+ "]";
	}
	
	public void reset() {
		
		action = ACTION_INIT;
		nombreEmpresa = null;
		nombreContacto = null;
		cargoContacto = null;
		idTipoTelefono =  null;
		acceso = null;
		clave = null;
		telefono = null;
		extension = null;
		correoElectronico = null;
		tituloOferta = null;
		salarioMensual = 1.0;
		comoTeEnterasteOferta = null;
		diaContacto = 0;
		mesContacto = 0;
		anioContacto = 0;
		diaEntrevista = 0;
		mesEntrevista = 0;
		anioEntrevista = 0;
	}


	public String getOrderType() {
		return orderType;
	}


	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}


	public Integer getColumnNumber() {
		return columnNumber;
	}


	public void setColumnNumber(Integer columnNumber) {
		this.columnNumber = columnNumber;
	}


	public List<PostulacionExterna> getPostulaciones() {
		return postulaciones;
	}


	public void setPostulaciones(List<PostulacionExterna> postulaciones) {
		this.postulaciones = postulaciones;
	}


	public Long getIdPostulacionExterna() {
		return idPostulacionExterna;
	}


	public void setIdPostulacionExterna(Long idPostulacionExterna) {
		this.idPostulacionExterna = idPostulacionExterna;
	}


	public PostulacionExterna getPostulacion() {
		return postulacion;
	}


	public void setPostulacion(PostulacionExterna postulacion) {
		this.postulacion = postulacion;
	}


	public int getDiaContrato() {
		return diaContrato;
	}


	public void setDiaContrato(int diaContrato) {
		this.diaContrato = diaContrato;
	}


	public int getMesContrato() {
		return mesContrato;
	}


	public void setMesContrato(int mesContrato) {
		this.mesContrato = mesContrato;
	}


	public int getAnioContrato() {
		return anioContrato;
	}


	public void setAnioContrato(int anioContrato) {
		this.anioContrato = anioContrato;
	}


	public Long getIdMotivoNoContratacion() {
		return idMotivoNoContratacion;
	}


	public void setIdMotivoNoContratacion(Long idMotivoNoContratacion) {
		this.idMotivoNoContratacion = idMotivoNoContratacion;
	}


	public List<CatalogoOpcionVO> getMotivos() {
		return motivos;
	}


	public void setMotivos(List<CatalogoOpcionVO> motivos) {
		this.motivos = motivos;
	}


	public String getOtroMotivo() {
		return otroMotivo;
	}


	public void setOtroMotivo(String otroMotivo) {
		this.otroMotivo = otroMotivo;
	}		
	
}