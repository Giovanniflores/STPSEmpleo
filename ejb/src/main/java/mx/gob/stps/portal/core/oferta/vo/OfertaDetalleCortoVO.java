package mx.gob.stps.portal.core.oferta.vo;

import java.io.Serializable;

/**
 * @author haydee.vertti
 *
 */
public final class OfertaDetalleCortoVO implements Serializable {
	private static final long serialVersionUID = 1083578049782852484L;

	private long idEmpresa;
	private long idOfertaEmpleo;
	private String tituloOferta;
	private String empresaNombre;
	private String ubicacion;
	private String correoElectronicoContacto;
	
	public long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public long getIdOfertaEmpleo() {
		return idOfertaEmpleo;
	}
	public void setIdOfertaEmpleo(long idOfertaEmpleo) {
		this.idOfertaEmpleo = idOfertaEmpleo;
	}
	public String getTituloOferta() {
		return tituloOferta;
	}
	public void setTituloOferta(String tituloOferta) {
		this.tituloOferta = tituloOferta;
	}
	public String getEmpresaNombre() {
		return empresaNombre;
	}
	public void setEmpresaNombre(String empresaNombre) {
		this.empresaNombre = empresaNombre;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	/**
	 * @return the correoElectronicoContacto
	 */
	public String getCorreoElectronicoContacto() {
		return correoElectronicoContacto;
	}
	/**
	 * @param correoElectronicoContacto the correoElectronicoContacto to set
	 */
	public void setCorreoElectronicoContacto(String correoElectronicoContacto) {
		this.correoElectronicoContacto = correoElectronicoContacto;
	}
	
}
