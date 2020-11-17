package mx.gob.stps.portal.movil.web.oferta.vo;

import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;
import mx.gob.stps.portal.movil.web.oferta.form.OfertaJB;

public class OfertaVO extends OfertaPorCanalVO {
	private static final long serialVersionUID = 6196186092712576524L;

	private OfertaVO(){}
	
	public final static OfertaVO getInstance(OfertaPorCanalVO ofertacanal){
		OfertaVO oferta = new OfertaVO();
		
		oferta.setIdEmpresa(ofertacanal.getIdEmpresa());
		oferta.setIdOfertaEmpleo(ofertacanal.getIdOfertaEmpleo());
		oferta.setTituloOferta(ofertacanal.getTituloOferta());
		oferta.setUbicacion(ofertacanal.getUbicacion());
		oferta.setEmpresa(ofertacanal.getEmpresa());
		oferta.setSalario(ofertacanal.getSalario());
		oferta.setsSalario(ofertacanal.getsSalario());
		oferta.setGradoEstudio(ofertacanal.getGradoEstudio());
		oferta.setCarrera(ofertacanal.getCarrera());
		oferta.setFunciones(ofertacanal.getFunciones());
		oferta.setEdad(ofertacanal.getEdad());
		oferta.setIdiomas(ofertacanal.getIdiomas());
		oferta.setHorario(ofertacanal.getHorario());
		oferta.setNumeroPlazas(ofertacanal.getNumeroPlazas());
		oferta.setMedioContacto(ofertacanal.getMedioContacto());
		oferta.setBolsaTrabajo(ofertacanal.getBolsaTrabajo());
		oferta.setFechaInicio(ofertacanal.getFechaInicio());
		oferta.setFechaFin(ofertacanal.getFechaFin());
		oferta.setFechaInicioString(ofertacanal.getFechaInicioString());
		oferta.setFechaFinString(ofertacanal.getFechaFinString());
		oferta.setPostulados(ofertacanal.getPostulados());
		oferta.setLimitePostulantes(ofertacanal.getLimitePostulantes());
		oferta.setCompatibility(ofertacanal.getCompatibility());
		oferta.setBitacora(ofertacanal.getBitacora());

		return oferta;
	}
	
	private OfertaJB detalle;
	private boolean postulated;
	private long compatibility;
	private long compatibilityLimit;
	
	public OfertaJB getDetalle() {
		return detalle;
	}

	public void setDetalle(OfertaJB detalle) {
		this.detalle = detalle;
	}

	public boolean isPostulated() {
		return postulated;
	}

	public void setPostulated(boolean postulated) {
		this.postulated = postulated;
	}

	public long getCompatibility() {
		return compatibility;
	}

	public void setCompatibility(long compatibility) {
		this.compatibility = compatibility;
	}

	public long getCompatibilityLimit() {
		return compatibilityLimit;
	}

	public void setCompatibilityLimit(long compatibilityLimit) {
		this.compatibilityLimit = compatibilityLimit;
	}

}
