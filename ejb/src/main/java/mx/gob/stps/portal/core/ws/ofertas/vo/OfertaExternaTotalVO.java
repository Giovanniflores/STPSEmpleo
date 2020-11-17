package mx.gob.stps.portal.core.ws.ofertas.vo;

import java.io.Serializable;

public class OfertaExternaTotalVO implements Serializable {

	private static final long serialVersionUID = -5325951660474973975L;

	private long totalOfertasConsultadas;
	private long totalOfertasAgregadas;
	private long totalOfertasModificadas;
	
	public long getTotalOfertasConsultadas() {
		return totalOfertasConsultadas;
	}
	public void setTotalOfertasConsultadas(long totalOfertasConsultadas) {
		this.totalOfertasConsultadas = totalOfertasConsultadas;
	}
	public long getTotalOfertasAgregadas() {
		return totalOfertasAgregadas;
	}
	public void setTotalOfertasAgregadas(long totalOfertasAgregadas) {
		this.totalOfertasAgregadas = totalOfertasAgregadas;
	}
	public long getTotalOfertasModificadas() {
		return totalOfertasModificadas;
	}
	public void setTotalOfertasModificadas(long totalOfertasModificadas) {
		this.totalOfertasModificadas = totalOfertasModificadas;
	}
}