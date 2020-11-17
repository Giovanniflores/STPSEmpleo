package mx.gob.stps.portal.core.oferta.vo;

import java.io.Serializable;


/**
 * Contiene los datos a mostrar en el apartado &quot;Mis Ofertas Recientes&quot;
 * de una empresa.
 * @author jose.jimenez
 *
 */
public class MiOfertaRecienteVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6594570390977138995L;

	/** Identificador de la oferta de empleo */
	private long idOferta;
	
	/** T&iacute;tulo de la oferta */
	private String tituloOferta;

	/**
	 * @return the idOferta
	 */
	public long getIdOferta() {
		return idOferta;
	}

	public MiOfertaRecienteVO() {}
	
	public MiOfertaRecienteVO(long idOferta, String tituloOferta) {
		this.idOferta = idOferta;
		if (tituloOferta != null) {
			this.tituloOferta = tituloOferta;
		} else {
			this.tituloOferta = "";
		}
	}
	
	/**
	 * @param idOferta the idOferta to set
	 */
	public void setIdOferta(long idOferta) {
		this.idOferta = idOferta;
	}

	/**
	 * @return the tituloOferta
	 */
	public String getTituloOferta() {
		return tituloOferta;
	}

	/**
	 * @param tituloOferta the tituloOferta to set
	 */
	public void setTituloOferta(String tituloOferta) {
		if (tituloOferta != null) {
			this.tituloOferta = tituloOferta;
		} else {
			this.tituloOferta = "";
		}
	}
	
	
}
