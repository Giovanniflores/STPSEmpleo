package mx.gob.stps.portal.core.seguridad.vo;

import java.io.Serializable;
import java.util.List;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.core.testimonio.vo.TestimonioVO;

public class PublicadorVO implements Serializable {

	private static final long serialVersionUID = 5327575638902399468L;
	private long idUsuario;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private long empresas;
	private long ofertas;
	private long testimonios;
	private long videocv;
	private long total;
	private List<EmpresaVO> companies;
	private List<OfertaEmpleoVO> offers;
	private List<TestimonioVO> testimonies;
	private List<CandidatoVo> curriculums;
	private long autorizados;
	private long rechazados;	
	
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
	 * @return the empresas
	 */
	public long getEmpresas() {
		return empresas;
	}
	/**
	 * @param empresas the empresas to set
	 */
	public void setEmpresas(long empresas) {
		this.empresas = empresas;
	}
	/**
	 * @return the ofertas
	 */
	public long getOfertas() {
		return ofertas;
	}
	/**
	 * @param ofertas the ofertas to set
	 */
	public void setOfertas(long ofertas) {
		this.ofertas = ofertas;
	}
	/**
	 * @return the testimonios
	 */
	public long getTestimonios() {
		return testimonios;
	}
	/**
	 * @param testimonios the testimonios to set
	 */
	public void setTestimonios(long testimonios) {
		this.testimonios = testimonios;
	}
	/**
	 * @return the videocv
	 */
	public long getVideocv() {
		return videocv;
	}
	/**
	 * @param videocv the videocv to set
	 */
	public void setVideocv(long videocv) {
		this.videocv = videocv;
	}
	/**
	 * @return the total
	 */
	public long getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(long total) {
		this.total = total;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PublicadorVO [idUsuario=");
		builder.append(idUsuario);
		builder.append(", total=");
		builder.append(total);
		builder.append("]");
		return builder.toString();
	}
	/**
	 * @return the companies
	 */
	public List<EmpresaVO> getCompanies() {
		return companies;
	}
	/**
	 * @param companies the companies to set
	 */
	public void setCompanies(List<EmpresaVO> companies) {
		this.companies = companies;
	}
	/**
	 * @return the offers
	 */
	public List<OfertaEmpleoVO> getOffers() {
		return offers;
	}
	/**
	 * @param offers the offers to set
	 */
	public void setOffers(List<OfertaEmpleoVO> offers) {
		this.offers = offers;
	}
	/**
	 * @return the testimonies
	 */
	public List<TestimonioVO> getTestimonies() {
		return testimonies;
	}
	/**
	 * @param testimonies the testimonies to set
	 */
	public void setTestimonies(List<TestimonioVO> testimonies) {
		this.testimonies = testimonies;
	}
	/**
	 * @return the curriculums
	 */
	public List<CandidatoVo> getCurriculums() {
		return curriculums;
	}
	/**
	 * @param curriculums the curriculums to set
	 */
	public void setCurriculums(List<CandidatoVo> curriculums) {
		this.curriculums = curriculums;
	}
	
	/**
	 * @return the autorizados
	 */
	public long getAutorizados() {
		return autorizados;
	}
	/**
	 * @param autorizados the autorizados to set
	 */
	public void setAutorizados(long autorizados) {
		this.autorizados = autorizados;
	}
	/**
	 * @return the rechazados
	 */
	public long getRechazados() {
		return rechazados;
	}
	/**
	 * @param rechazados the rechazados to set
	 */
	public void setRechazados(long rechazados) {
		this.rechazados = rechazados;
	}	
}