/**
 * 
 */
package mx.gob.stps.portal.core.candidate.vo;

import java.io.Serializable;

import mx.gob.stps.portal.core.infra.utils.Constantes;

/**
 * Objeto de valor que transporta los datos de escolaridad de un candidato.
 * @author Felipe Juárez Ramírez
 * @since 08/03/2011
 */
public class EscolaridadVO implements Serializable {

	/**
	 * @serialField
	 */
	private static final long serialVersionUID = -1809408539282767466L;

	/**
	 * Identificador de candidato y usuario
	 **/
	private long idCandidato;
	private long idUsuario;
	/**
	 * Indica si tiene estudios
	 */
	private int sinEstudios;
	/**
	 * Grado Academico (Principal)
	 */
	private GradoAcademicoVO grado;
	/**
	 * Idioma (Principal)
	 */
	private IdiomaVO idioma;
	/**
	 * Conocimientos y Habilidades (Multiregistro)
	 */
	private ConocimientoHabilidadVO conocimiento;
	private ConocimientoHabilidadVO habilidad;
	/**
	 * Computación Básica
	 **/
	private int computacionBasica;
	private long idExperienciaCompu;
	private long idDominioCompu;
	private long idExperienciaOffice;
	private long idDominioOffice;
	private long idExperienciaInternet;
	private long idDominioInternet;
	/**
	 * Bandera que indica si se tiene conocimientos en computación avanzada.
	 **/
	private int computacionAvanzada;
	/**
	 * Computación Avanzada (Multiregistro)
	 **/
	private ComputacionAvanzadaVO compAvanzada;
	
	/**
	 * Apoyo oportunidades ya se llama apoyo Prospera
	 **/
	private int  apoyoProspera;
	private String folioProspera;
	private String folioIntegranteProspera;
	
	private long[] idHabilidad;
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
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public long getIdUsuario() {
		return idUsuario;
	}
	/**
	 * @param sinEstudios the sinEstudios to set
	 */
	public void setSinEstudios(int sinEstudios) {
		this.sinEstudios = sinEstudios;
	}
	/**
	 * @return the sinEstudios
	 */
	public int getSinEstudios() {
		return sinEstudios;
	}
	/**
	 * @return the grado
	 */
	public GradoAcademicoVO getGrado() {
		return grado;
	}
	/**
	 * @param grado the grado to set
	 */
	public void setGrado(GradoAcademicoVO grado) {
		this.grado= grado;
	}
	/**
	 * @return the idioma
	 */
	public IdiomaVO getIdioma() {
		return idioma;
	}
	/**
	 * @param idioma the idioma to set
	 */
	public void setIdioma(IdiomaVO idioma) {
		this.idioma = idioma;
	}
	/**
	 * @return the conocimiento
	 */
	public ConocimientoHabilidadVO getConocimiento() {
		return conocimiento;
	}
	/**
	 * @param conocimientos the conocimientos to set
	 */
	public void setConocimiento(ConocimientoHabilidadVO conocimiento) {
		this.conocimiento = conocimiento;
	}
	/**
	 * @return the habilidad
	 */
	public ConocimientoHabilidadVO getHabilidad() {
		return habilidad;
	}
	/**
	 * @param habilidad the habilidad to set
	 */
	public void setHabilidad(ConocimientoHabilidadVO habilidad) {
		this.habilidad = habilidad;
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
	 * @param computacionAvanzada the computacionAvanzada to set
	 */
	public void setComputacionAvanzada(int computacionAvanzada) {
		this.computacionAvanzada = computacionAvanzada;
	}
	/**
	 * @return the computacionAvanzada
	 */
	public int getComputacionAvanzada() {
		return computacionAvanzada;
	}
	/**
	 * @return the compAvanzada
	 */
	public ComputacionAvanzadaVO getCompAvanzada() {
		return compAvanzada;
	}
	/**
	 * @param compAvanzada the compAvanzada to set
	 */
	public void setCompAvanzada(ComputacionAvanzadaVO compAvanzada) {
		this.compAvanzada = compAvanzada;
	}

	public long[] getIdHabilidad() {
		return idHabilidad;
	}
	public void setIdHabilidad(long[] idHabilidad) {
		this.idHabilidad = idHabilidad;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EscolaridadVO [idCandidato=" + idCandidato + ", sinEstudios="
				+ sinEstudios + ", grado=" + grado + ", idioma=" + idioma
				+ ", conocimiento=" + conocimiento + ", habilidad=" + habilidad
				+ ", computacionBasica=" + computacionBasica
				+ ", idExperienciaCompu=" + idExperienciaCompu
				+ ", idDominioCompu=" + idDominioCompu
				+ ", idExperienciaOffice=" + idExperienciaOffice
				+ ", idDominioOffice=" + idDominioOffice
				+ ", idExperienciaInternet=" + idExperienciaInternet
				+ ", idDominioInternet=" + idDominioInternet
				+ ", computacionAvanzada=" + computacionAvanzada
				+ ", compAvanzada=" + compAvanzada + "]";
	}
	public String toBitacora() {
		String bitacora = "idCandidato=" + idCandidato + "|sinEstudios=" + sinEstudios;
		if (sinEstudios == Constantes.ESCOLARIDAD.CON_ESTUDIOS.getIdOpcion()) {
			bitacora += "|grado=" + (grado != null ? grado.toBitacora() : "" )
			+ "|idioma=" + (idioma != null ? idioma.toBitacora() : "")
			+ "|conocimiento="+ (conocimiento != null ? conocimiento.toBitacora() : "")
			+ "|habilidad=" + (habilidad != null ? habilidad.toBitacora() : "");		
		}
		bitacora += "|computacionBasica=" + computacionBasica
		+ "|idExperienciaCompu=" + idExperienciaCompu
		+ "|idDominioCompu=" + idDominioCompu + "|idExperienciaOffice="
		+ idExperienciaOffice + "|idDominioOffice=" + idDominioOffice
		+ "|idExperienciaInternet=" + idExperienciaInternet
		+ "|idDominioInternet=" + idDominioInternet
		+ "|computacionAvanzada=" + computacionAvanzada;
		if (compAvanzada != null) {
			bitacora += "|compAvanzada=" + compAvanzada.toBitacora();
		}
		return bitacora;
	}
	
	public int getApoyoProspera() {
		return apoyoProspera;
	}
	public void setApoyoProspera(int apoyoProspera) {
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
}
