/**
 * 
 */
package mx.gob.stps.portal.web.oferta.vo;

import java.io.Serializable;

/**
 * Clase que contendrá a los candidatos que pertenecen a una oferta en específico.
 * @author “Arriaga Cervantes F. Rubén”
 *
 */
public class CandidatosOferta implements Serializable{
	private static final long serialVersionUID = -3332939001156208060L;
	//Identificador del candidato
	private long idCandidato;
	//Nombre completo del candidato
	private String nombreCandidato;
	//Ultimo grado de estudios del candidato
	private String gradoEstudios;
	//Edad del candidato
	private int edad;
	//Entidad federativa del candidato
	private String entidadFederativa;
	
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
	 * @return the nombreCandidato
	 */
	public String getNombreCandidato() {
		return nombreCandidato;
	}

	/**
	 * @param nombreCandidato the nombreCandidato to set
	 */
	public void setNombreCandidato(String nombreCandidato) {
		this.nombreCandidato = nombreCandidato;
	}

	/**
	 * @return the gradoEstudios
	 */
	public String getGradoEstudios() {
		return gradoEstudios;
	}

	/**
	 * @param gradoEstudios the gradoEstudios to set
	 */
	public void setGradoEstudios(String gradoEstudios) {
		this.gradoEstudios = gradoEstudios;
	}

	/**
	 * @return the entidadFederativa
	 */
	public String getEntidadFederativa() {
		return entidadFederativa;
	}

	/**
	 * @param entidadFederativa the entidadFederativa to set
	 */
	public void setEntidadFederativa(String entidadFederativa) {
		this.entidadFederativa = entidadFederativa;
	}
	
}