package mx.gob.stps.portal.web.oferta.search.form;

import java.util.List;

import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorParametrosVO;

import org.apache.struts.action.ActionForm;

/**
 * @author Jorge Montoya Marin
 * @since 22/12/2015
 **/
public class OfertasPorParametrosForm extends ActionForm {

	private static final long serialVersionUID = 1315111748295857610L;

	private long idCandidato;
	private List<OfertaPorParametrosVO> ofertas;	
	private int goToPageNumber;
	private String tablaPager;

	//Datos del Candidato
	private int edad;
    private int idNivelEstudio;
    private int idCarreraEspecialidad;
    private int idExperiencia;
    private int idIdioma;
    private int idDominioIdioma;
    private double salarioPretendido;

	private int disponibilidadViajar;
	private int disponibilidadRadicar;
    private int idEntidad;
    private long[] idHabilidades;

	
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
	 * @return the ofertas
	 */
	public List<OfertaPorParametrosVO> getOfertas() {
		return ofertas;
	}

	/**
	 * @param ofertas the ofertas to set
	 */
	public void setOfertas(List<OfertaPorParametrosVO> ofertas) {
		this.ofertas = ofertas;
	}

	/**
	 * @return the goToPageNumber
	 */
	public int getGoToPageNumber() {
		return goToPageNumber;
	}

	/**
	 * @param goToPageNumber the goToPageNumber to set
	 */
	public void setGoToPageNumber(int goToPageNumber) {
		this.goToPageNumber = goToPageNumber;
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
		this.edad = edad;
	}

	
	/**
	 * @return the idNivelEstudio
	 */
	public int getIdNivelEstudio() {
		return idNivelEstudio;
	}

	/**
	 * @param idNivelEstudio the idNivelEstudio to set
	 */
	public void setIdNivelEstudio(int idNivelEstudio) {
		this.idNivelEstudio = idNivelEstudio;
	}

	/**
	 * @return the idCarreraEspecialidad
	 */
	public int getIdCarreraEspecialidad() {
		return idCarreraEspecialidad;
	}

	/**
	 * @param idCarreraEspecialidad the idCarreraEspecialidad to set
	 */
	public void setIdCarreraEspecialidad(int idCarreraEspecialidad) {
		this.idCarreraEspecialidad = idCarreraEspecialidad;
	}

	/**
	 * @return the idExperiencia
	 */
	public int getIdExperiencia() {
		return idExperiencia;
	}

	/**
	 * @param idExperiencia the idExperiencia to set
	 */
	public void setIdExperiencia(int idExperiencia) {
		this.idExperiencia = idExperiencia;
	}

	/**
	 * @return the idIdioma
	 */
	public int getIdIdioma() {
		return idIdioma;
	}

	/**
	 * @param idIdioma the idIdioma to set
	 */
	public void setIdIdioma(int idIdioma) {
		this.idIdioma = idIdioma;
	}

	/**
	 * @return the idDominioIdioma
	 */
	public int getIdDominioIdioma() {
		return idDominioIdioma;
	}

	/**
	 * @param idDominioIdioma the idDominioIdioma to set
	 */
	public void setIdDominioIdioma(int idDominioIdioma) {
		this.idDominioIdioma = idDominioIdioma;
	}

	/**
	 * @return the salarioPretendido
	 */
	public double getSalarioPretendido() {
		return salarioPretendido;
	}

	/**
	 * @param salarioPretendido the salarioPretendido to set
	 */
	public void setSalarioPretendido(double salarioPretendido) {
		this.salarioPretendido = salarioPretendido;
	}

	public long[] getIdHabilidades() {
		return idHabilidades;
	}

	public void setIdHabilidad(long[] idHabilidades) {
		this.idHabilidades = idHabilidades;
	}

	/**
	 * @return the disponibilidadViajar
	 */
	public int getDisponibilidadViajar() {
		return disponibilidadViajar;
	}

	/**
	 * @param disponibilidadViajar
	 *            the disponibilidadViajar to set
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
	 * @param disponibilidadRadicar
	 *            the disponibilidadRadicar to set
	 */
	public void setDisponibilidadRadicar(int disponibilidadRadicar) {
		this.disponibilidadRadicar = disponibilidadRadicar;
	}
	/**
	 * @return the idEntidad
	 */
	public int getIdEntidad() {
		return idEntidad;
	}

	/**
	 * @param idEntidad the idEntidad to set
	 */
	public void setIdEntidad(int idEntidad) {
		this.idEntidad = idEntidad;
	}

	/**
	 * @return the tablaPager
	 */
	public String getTablaPager() {
		return tablaPager;
	}

	/**
	 * @param tablaPager the tablaPager to set
	 */
	public void setTablaPager(String tablaPager) {
		this.tablaPager = tablaPager;
	}

	/**
	 * Inicializa el objeto en su valor minimo.
	 * */
	public void reset(){
		this.setEdad(0);
		this.setIdNivelEstudio(0);
		this.setIdCarreraEspecialidad(0);
		this.setIdExperiencia(0);
		this.setIdIdioma(0);
		this.setIdDominioIdioma(0);
		this.setSalarioPretendido(0.00);
		this.setDisponibilidadViajar(0);
		this.setDisponibilidadRadicar(0);
		this.setIdEntidad(0);
		this.setIdHabilidad(null);
	}
}
