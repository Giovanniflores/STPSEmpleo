/**
 * 
 */
package mx.gob.stps.portal.web.oferta.vo;

import java.util.ArrayList;
import java.util.List;

import mx.gob.stps.portal.core.oferta.registro.vo.RegistroUbicacionVO;
import mx.gob.stps.portal.web.oferta.form.CarrerasSimilaresForm;

/**
 * @author “Arriaga Cervantes F. Rubén”
 *
 */
public class OfertaEmpleoCreadaVO extends OfertaEmpleoVO{
	
	//Control de versión
	private static final long serialVersionUID = 1867202404202605089L;
	//Descripción del área laboral
	private String descripcionGradoEstudio;
	//Descripción de la ocupación
	private String descripcionOcupacion;
	//Lista de las carreras
	private List<CarrerasSimilaresForm> carreras = new ArrayList<CarrerasSimilaresForm>();
	//Ubicación de la oferta
	private List<RegistroUbicacionVO> ubicacion;

	/**
	 * @return the descripcionOcupacion
	 */
	public String getDescripcionOcupacion() {
		return descripcionOcupacion;
	}

	/**
	 * @param descripcionOcupacion the descripcionOcupacion to set
	 */
	public void setDescripcionOcupacion(String descripcionOcupacion) {
		this.descripcionOcupacion = descripcionOcupacion;
	}

	/**
	 * @return the carreras
	 */
	public List<CarrerasSimilaresForm> getCarreras() {
		return carreras;
	}

	/**
	 * @param carreras the carreras to set
	 */
	public void setCarreras(List<CarrerasSimilaresForm> carreras) {
		this.carreras = carreras;
	}

	/**
	 * @return the ubicacion
	 */
	public List<RegistroUbicacionVO> getUbicacion() {
		return ubicacion;
	}

	/**
	 * @param ubicacion the ubicacion to set
	 */
	public void setUbicacion(List<RegistroUbicacionVO> ubicacion) {
		this.ubicacion = ubicacion;
	}

	/**
	 * @return the descripcionGradoEstudio
	 */
	public String getDescripcionGradoEstudio() {
		return descripcionGradoEstudio;
	}

	/**
	 * @param descripcionGradoEstudio the descripcionGradoEstudio to set
	 */
	public void setDescripcionGradoEstudio(String descripcionGradoEstudio) {
		this.descripcionGradoEstudio = descripcionGradoEstudio;
	}
	
}