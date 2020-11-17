package mx.gob.stps.portal.movil.app.model.rest;

import java.util.ArrayList;
import java.util.List;

import mx.gob.stps.portal.movil.app.model.base.BaseRestDTO;
import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.utils.Catalogos.ESTATUS;


/**
 * @author benjamin.vander
 *
 */
public class OfertaMinimoRestDTO extends BaseRestDTO {

	String idOfertaEmpleo;
	List<EstatusCandidato> estatusCandidatos;
	
	public String getIdOfertaEmpleo() {
		return idOfertaEmpleo;
	}

	public void setIdOfertaEmpleo(String idOfertaEmpleo) {
		this.idOfertaEmpleo = idOfertaEmpleo;
	}

	public void addListEstatusCandidato(ESTATUS estatus, int cantidad) {
		if(this.estatusCandidatos == null){
			this.estatusCandidatos = new ArrayList<EstatusCandidato>();
		}
		this.estatusCandidatos.add(new EstatusCandidato(estatus,cantidad));
		
	}
	
	
	public List<EstatusCandidato> getEstatusCandidatos() {
		return estatusCandidatos;
	}

	public void setEstatusCandidatos(List<EstatusCandidato> estatusCandidatos) {
		this.estatusCandidatos = estatusCandidatos;
	}


	private class EstatusCandidato {
		Catalogos.ESTATUS estatus;
		int cantidad;
		String estatusStr;
		
		
		
		public EstatusCandidato(Catalogos.ESTATUS estatus,int cantidad){
			this.estatus = estatus;
			this.cantidad = cantidad;
			if(estatus == null){
				estatusStr ="";
			}
			else
			{
				estatusStr = estatus.getOpcion();
			}
		}
		
		public Catalogos.ESTATUS getEstatus() {
			return estatus;
		}
		public void setEstatus(Catalogos.ESTATUS estatus) {
			this.estatus = estatus;
		}
		public int getCantidad() {
			return cantidad;
		}
		public void setCantidad(int cantidad) {
			this.cantidad = cantidad;
		}
		
		public String getEstatusStr(){
			return estatusStr;
		}
		public void setEstatusStr()
		{	
			
		}
		
	}
}
