package mx.gob.stps.portal.web.oferta.form;

import java.util.Date;
import java.util.List;

import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.core.oferta.reporte.vo.ReporteOfertasEmpresaVO;

import org.apache.struts.action.ActionForm;

public class ReporteOfertasEmpresaForm extends ActionForm{

	private static final long 				serialVersionUID = -312148981315814976L;
	private long							idEmpresa;
	private long							idUsuario;
	private long							idTipoUsuario;
	private int								filterIdEstatus;
	private String 							filterFechaIni;
	private String 							filterFechaFin;
	private String							filterDesEstatus;
	private Date							fechaActual;
	private	int								totalOfertas;
	private List<ReporteOfertasEmpresaVO>	ofertas;
	private List<CatalogoOpcionVO>			lstEstatus;
	
	
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
	 * @return the idTipoUsuario
	 */
	public long getIdTipoUsuario() {
		return idTipoUsuario;
	}
	/**
	 * @param idTipoUsuario the idTipoUsuario to set
	 */
	public void setIdTipoUsuario(long idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}
	/**
	 * @return the filterIdEstatus
	 */
	public int getFilterIdEstatus() {
		return filterIdEstatus;
	}
	/**
	 * @param filterIdEstatus the filterIdEstatus to set
	 */
	public void setFilterIdEstatus(int filterIdEstatus) {
		this.filterIdEstatus = filterIdEstatus;
	}
	/**
	 * @return the filterFechaIni
	 */
	public String getFilterFechaIni() {
		return filterFechaIni;
	}
	/**
	 * @param filterFechaIni the filterFechaIni to set
	 */
	public void setFilterFechaIni(String filterFechaIni) {
		this.filterFechaIni = filterFechaIni;
	}
	/**
	 * @return the filterFechaFin
	 */
	public String getFilterFechaFin() {
		return filterFechaFin;
	}
	/**
	 * @param filterFechaFin the filterFechaFin to set
	 */
	public void setFilterFechaFin(String filterFechaFin) {
		this.filterFechaFin = filterFechaFin;
	}	
	/**
	 * @return the filterDesEstatus
	 */
	public String getFilterDesEstatus() {
		return filterDesEstatus;
	}
	/**
	 * @param filterDesEstatus the filterDesEstatus to set
	 */
	public void setFilterDesEstatus(String filterDesEstatus) {
		this.filterDesEstatus = filterDesEstatus;
	}
	/**
	 * @return the fechaActual
	 */
	public Date getFechaActual() {
		return fechaActual;
	}
	/**
	 * @param fechaActual the fechaActual to set
	 */
	public void setFechaActual(Date fechaActual) {
		this.fechaActual = fechaActual;
	}
	/**
	 * @return the totalOfertas
	 */
	public int getTotalOfertas() {
		return totalOfertas;
	}
	/**
	 * @param totalOfertas the totalOfertas to set
	 */
	public void setTotalOfertas(int totalOfertas) {
		this.totalOfertas = totalOfertas;
	}
	/**
	 * @return the ofertas
	 */
	public List<ReporteOfertasEmpresaVO> getOfertas() {
		return ofertas;
	}
	/**
	 * @param ofertas the ofertas to set
	 */
	public void setOfertas(List<ReporteOfertasEmpresaVO> ofertas) {
		this.ofertas = ofertas;
	}
	/**
	 * @return the lstEstatus
	 */
	public List<CatalogoOpcionVO> getLstEstatus() {
		return lstEstatus;
	}
	/**
	 * @param lstEstatus the lstEstatus to set
	 */
	public void setLstEstatus(List<CatalogoOpcionVO> lstEstatus) {
		this.lstEstatus = lstEstatus;
	}
	public void reset() {
		filterIdEstatus	= 0;
		filterFechaIni = null;
		filterFechaFin = null;
		filterDesEstatus = null;
		fechaActual = null;
		totalOfertas	= 0;
		ofertas = null; 		
	}
	
	public String toString() {
		return "ReporteOfertasEmpresaForm [idEmpresa=" + idEmpresa
				+ ", idUsuario=" + idUsuario
				+ ", idTipoUsuario=" + idTipoUsuario				
				+ ", filterIdEstatus=" + filterIdEstatus
				+ ", filterDesEstatus=" + filterDesEstatus
				+ ", filterFechaIni=" + filterFechaIni
				+ ", filterFechaFin=" + filterFechaFin
				+ ", fechaActual=" + fechaActual
				+ ", totalOfertas=" + totalOfertas
				+ ", ofertas=" + ofertas
				+ "]";
	}
	
	
}
