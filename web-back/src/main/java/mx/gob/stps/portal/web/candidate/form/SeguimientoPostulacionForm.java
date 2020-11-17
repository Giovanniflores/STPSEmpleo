package mx.gob.stps.portal.web.candidate.form;

import java.util.Calendar;

import org.apache.struts.action.ActionForm;

import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaEmpleoJB;

public class SeguimientoPostulacionForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 639686975362944938L;
	
	private int diaContacto;
	
	private int mesContacto;
	
	private int anioContacto;
	
	private int diaEntrevista;
	
	private int mesEntrevista;
	
	private int anioEntrevista;
	
	private int diaContrato;
	
	private int mesContrato;
	
	private int anioContrato;
	
	private int motivoNoEntrevista;
	
	private int motivoNoContrato;
	
	private int conseguisteEntrevista;
	
	private int fuisteContratado;
	
	private int estatusPPC;
	
	private String motivoNoContratoEspecifique;
	
	private OfertaEmpleoJB offer;
	
	private OfertaCandidatoVO offerCand;
	
	private int control;
	
	public OfertaEmpleoJB getOffer() {
		return offer;
	}

	public void setOffer(OfertaEmpleoJB offer) {
		this.offer = offer;
	}

	public int getDiaContacto() {
		return diaContacto;
	}

	public void setDiaContacto(int diaContacto) {
		this.diaContacto = diaContacto;
	}

	public int getMesContacto() {
		return mesContacto;
	}

	public void setMesContacto(int mesContacto) {
		this.mesContacto = mesContacto;
	}

	public int getAnioContacto() {
		return anioContacto;
	}

	public void setAnioContacto(int anioContacto) {
		this.anioContacto = anioContacto;
	}

	public int getDiaEntrevista() {
		return diaEntrevista;
	}

	public void setDiaEntrevista(int diaEntrevista) {
		this.diaEntrevista = diaEntrevista;
	}

	public int getMesEntrevista() {
		return mesEntrevista;
	}

	public void setMesEntrevista(int mesEntrevista) {
		this.mesEntrevista = mesEntrevista;
	}

	public int getAnioEntrevista() {
		return anioEntrevista;
	}

	public void setAnioEntrevista(int anioEntrevista) {
		this.anioEntrevista = anioEntrevista;
	}

	public int getMotivoNoEntrevista() {
		return motivoNoEntrevista;
	}

	public void setMotivoNoEntrevista(int motivoNoEntrevista) {
		this.motivoNoEntrevista = motivoNoEntrevista;
	}

	public int getConseguisteEntrevista() {
		return conseguisteEntrevista;
	}

	public void setConseguisteEntrevista(int conseguisteEntrevista) {
		this.conseguisteEntrevista = conseguisteEntrevista;
	}

	public OfertaCandidatoVO getOfferCand() {
		return offerCand;
	}

	public void setOfferCand(OfertaCandidatoVO offerCand) {
		this.offerCand = offerCand;
	}

	public int getDiaContrato() {
		return diaContrato;
	}

	public void setDiaContrato(int diaContrato) {
		this.diaContrato = diaContrato;
	}

	public int getMesContrato() {
		return mesContrato;
	}

	public void setMesContrato(int mesContrato) {
		this.mesContrato = mesContrato;
	}

	public int getAnioContrato() {
		return anioContrato;
	}

	public void setAnioContrato(int anioContrato) {
		this.anioContrato = anioContrato;
	}

	public int getMotivoNoContrato() {
		return motivoNoContrato;
	}

	public void setMotivoNoContrato(int motivoNoContrato) {
		this.motivoNoContrato = motivoNoContrato;
	}

	public int getFuisteContratado() {
		return fuisteContratado;
	}

	public void setFuisteContratado(int fuisteContratado) {
		this.fuisteContratado = fuisteContratado;
	}

	public int getEstatusPPC() {
		return estatusPPC;
	}

	public void setEstatusPPC(int estatusPPC) {
		this.estatusPPC = estatusPPC;
	}

	public String getMotivoNoContratoEspecifique() {
		return motivoNoContratoEspecifique;
	}

	public void setMotivoNoContratoEspecifique(String motivoNoContratoEspecifique) {
		this.motivoNoContratoEspecifique = motivoNoContratoEspecifique;
	}

	public int getControl() {
		return control;
	}

	public void setControl(int control) {
		this.control = control;
	}
	
	public void load() {
		reset();
		Calendar cal = Calendar.getInstance();
		if (null != this.getOfferCand()) {
			if (null != this.getOfferCand().getFechaContacto()) {
				cal.setTime(this.getOfferCand().getFechaContacto());
				this.setDiaContacto(cal.get(Calendar.DAY_OF_MONTH));
				this.setMesContacto(cal.get(Calendar.MONTH) + 1);
				this.setAnioContacto(cal.get(Calendar.YEAR));
			}
			if (null != this.getOfferCand().getFechaEntrevista()) {
				cal.setTime(this.getOfferCand().getFechaEntrevista());
				this.setDiaEntrevista(cal.get(Calendar.DAY_OF_MONTH));
				this.setMesEntrevista(cal.get(Calendar.MONTH) + 1);
				this.setAnioEntrevista(cal.get(Calendar.YEAR));
			}
			if (null != this.getOfferCand().getMotivoNoEntrevista())
				this.setMotivoNoEntrevista(this.getOfferCand().getMotivoNoEntrevista());
			if (null != this.getOfferCand().getConseguioEntrevista())
				this.setConseguisteEntrevista(this.getOfferCand().getConseguioEntrevista());
			if (null != this.getOfferCand().getFuisteContratado())
				this.setFuisteContratado(this.getOfferCand().getFuisteContratado());
			if (null != this.getOfferCand().getMotivoDesc())
				this.setMotivoNoContratoEspecifique(this.getOfferCand().getMotivoDesc());
		}
	}
	
	public void reset() {
		this.setControl(0);
		this.setDiaContacto(0);
		this.setMesContacto(0);
		this.setAnioContacto(0);
		this.setDiaEntrevista(0);
		this.setMesEntrevista(0);
		this.setAnioEntrevista(0);
		this.setMotivoNoEntrevista(0);
		this.setConseguisteEntrevista(0);
		this.setFuisteContratado(0);
		this.setMotivoNoContratoEspecifique("");
	}
}
