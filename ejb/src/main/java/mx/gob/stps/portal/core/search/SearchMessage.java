package mx.gob.stps.portal.core.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SearchMessage extends ResultSearchVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//Lista de elementos ResultInfoBO encontrados en la busqueda de Ofertas
	private List<ResultInfoBO> listResultInfoBO;
	//Lista de identificadores de Candidatos encontrados en la busqueda de candidatos
	private List<Long> listCandidatos;
	
	//Número total de plazas disponibles, este valor se llena una vez que se realizó la busqueda y es esclusivo para el mensaje
	private int totalPlazasDisponibles;

	public List<ResultInfoBO> getListResultInfoBO() {
		if(listResultInfoBO==null)
			listResultInfoBO = new ArrayList<ResultInfoBO>();
		return listResultInfoBO;
	}

	public void setListResultInfoBO(List<ResultInfoBO> listResultInfoBO) {
		this.listResultInfoBO = listResultInfoBO;
	}

	public List<Long> getListCandidatos() {
		if(listCandidatos == null)
			listCandidatos = new ArrayList<Long>();
		return listCandidatos;
	}

	public void setListCandidatos(List<Long> listCandidatos) {
		this.listCandidatos = listCandidatos;
	}

	public int getTotalPlazasDisponibles() {
		return totalPlazasDisponibles;
	}

	public void setTotalPlazasDisponibles(int totalPlazasDisponibles) {
		this.totalPlazasDisponibles = totalPlazasDisponibles;
	}

}
