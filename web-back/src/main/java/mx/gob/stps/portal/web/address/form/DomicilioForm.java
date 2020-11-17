package mx.gob.stps.portal.web.address.form;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mx.gob.stps.portal.core.domicilio.vo.ColoniaVO;
import mx.gob.stps.portal.core.domicilio.vo.MunicipioVO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.web.infra.vo.ResultVO;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author Mario Alberto Vázquez Flores
 * @since 01/03/2011
 **/
public class DomicilioForm extends ActionForm {

	private static final long serialVersionUID = -7116558947055518208L;

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
	private Date fechaAlta;
	private int cpNuevo;

	private List<CatalogoOpcionVO> cbEntidad;
	private List<MunicipioVO> cbMunicipio;
	private List<ColoniaVO> cbColonia;
	
	private ResultVO msg;


	/**
	 * @return the idDomicilio
	 */
	public long getIdDomicilio() {
		return idDomicilio;
	}

	/**
	 * @param idDomicilio
	 *            the idDomicilio to set
	 */
	public void setIdDomicilio(long idDomicilio) {
		this.idDomicilio = idDomicilio;
	}

	/**
	 * @return the idTipoPropietario
	 */
	public long getIdTipoPropietario() {
		return idTipoPropietario;
	}

	/**
	 * @param idTipoPropietario
	 *            the idTipoPropietario to set
	 */
	public void setIdTipoPropietario(long idTipoPropietario) {
		this.idTipoPropietario = idTipoPropietario;
	}

	/**
	 * @return the idEntidad
	 */
	public long getIdEntidad() {
		return idEntidad;
	}

	/**
	 * @param idEntidad
	 *            the idEntidad to set
	 */
	public void setIdEntidad(long idEntidad) {
		this.idEntidad = idEntidad;
	}

	/**
	 * @return the idMunicipio
	 */
	public long getIdMunicipio() {
		return idMunicipio;
	}

	/**
	 * @param idMunicipio
	 *            the idMunicipio to set
	 */
	public void setIdMunicipio(long idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	/**
	 * @return the idColonia
	 */
	public long getIdColonia() {
		return idColonia;
	}

	/**
	 * @param idColonia
	 *            the idColonia to set
	 */
	public void setIdColonia(long idColonia) {
		this.idColonia = idColonia;
	}

	/**
	 * @return the calle
	 */
	public String getCalle() {
		return calle;
	}

	/**
	 * @param calle
	 *            the calle to set
	 */
	public void setCalle(String calle) {
		this.calle = calle;
	}

	/**
	 * @return the numeroInterior
	 */
	public String getNumeroInterior() {
		return numeroInterior;
	}

	/**
	 * @param numeroInterior
	 *            the numeroInterior to set
	 */
	public void setNumeroInterior(String numeroInterior) {
		this.numeroInterior = numeroInterior;
	}

	/**
	 * @return the numeroExterior
	 */
	public String getNumeroExterior() {
		return numeroExterior;
	}

	/**
	 * @param numeroExterior
	 *            the numeroExterior to set
	 */
	public void setNumeroExterior(String numeroExterior) {
		this.numeroExterior = numeroExterior;
	}

	/**
	 * @return the entreCalle
	 */
	public String getEntreCalle() {
		return entreCalle;
	}

	/**
	 * @param entreCalle
	 *            the entreCalle to set
	 */
	public void setEntreCalle(String entreCalle) {
		this.entreCalle = entreCalle;
	}

	/**
	 * @return the yCalle
	 */
	public String getyCalle() {
		return yCalle;
	}

	/**
	 * @param yCalle
	 *            the yCalle to set
	 */
	public void setyCalle(String yCalle) {
		this.yCalle = yCalle;
	}

	/**
	 * @return the codigoPostal
	 */
	public String getCodigoPostal() {
		return codigoPostal;
	}

	/**
	 * @param codigoPostal
	 *            the codigoPostal to set
	 */
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	/**
	 * @return the fechaAlta
	 */
	public Date getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * @param fechaAlta
	 *            the fechaAlta to set
	 */
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	/**
	 * @return the cbEntidad
	 */
	public List<CatalogoOpcionVO> getCbEntidad() {
		return cbEntidad;
	}

	/**
	 * @param cbEntidad
	 *            the cbEntidad to set
	 */
	public void setCbEntidad(List<CatalogoOpcionVO> cbEntidad) {
		this.cbEntidad = cbEntidad;
	}

	/**
	 * @return the cbMunicipio
	 */
	public List<MunicipioVO> getCbMunicipio() {
		return cbMunicipio;
	}

	/**
	 * @param cbMunicipio
	 *            the cbMunicipio to set
	 */
	public void setCbMunicipio(List<MunicipioVO> cbMunicipio) {
		this.cbMunicipio = cbMunicipio;
	}

	/**
	 * @return the cbColonia
	 */
	public List<ColoniaVO> getCbColonia() {
		return cbColonia;
	}

	/**
	 * @param cbColonia
	 *            the cbColonia to set
	 */
	public void setCbColonia(List<ColoniaVO> cbColonia) {
		this.cbColonia = cbColonia;
	}

	public void setMsg(ResultVO msg) {
		this.msg = msg;
	}

	public ResultVO getMsg() {
		return msg;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.
	 * ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
		super.reset(mapping, request);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts.action.ActionForm#validate(org.apache.struts.action
	 * .ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return super.validate(mapping, request);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DomicilioForm [idDomicilio=");
		builder.append(idDomicilio);
		builder.append(", idTipoPropietario=");
		builder.append(idTipoPropietario);
		builder.append(", idEntidad=");
		builder.append(idEntidad);
		builder.append(", idMunicipio=");
		builder.append(idMunicipio);
		builder.append(", idColonia=");
		builder.append(idColonia);
		builder.append(", calle=");
		builder.append(calle);
		builder.append(", numeroInterior=");
		builder.append(numeroInterior);
		builder.append(", numeroExterior=");
		builder.append(numeroExterior);
		builder.append(", entreCalle=");
		builder.append(entreCalle);		
		builder.append(", yCalle=");
		builder.append(yCalle);
		builder.append(", codigoPostal=");
		builder.append(codigoPostal);		
		builder.append(", fechaAlta=");
		builder.append(fechaAlta);
		builder.append("]");
		return builder.toString();
	}

	public void setCpNuevo(int cpNuevo) {
		this.cpNuevo = cpNuevo;
	}

	public int getCpNuevo() {
		return cpNuevo;
	}

}
