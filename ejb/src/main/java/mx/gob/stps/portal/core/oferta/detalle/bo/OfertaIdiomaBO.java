package mx.gob.stps.portal.core.oferta.detalle.bo;

import java.sql.SQLException;

import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.oferta.detalle.dao.OfertaDetalleDAO;
import mx.gob.stps.portal.core.oferta.detalle.helper.Utils;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaIdiomaVO;

public class OfertaIdiomaBO {
	
	private OfertaIdiomaVO vo = null;
	
	private String certificacion;

	public OfertaIdiomaBO() {
		this.vo = new OfertaIdiomaVO();
	}
	
	public OfertaIdiomaBO(OfertaIdiomaVO vo) {
		this.vo = vo;
	}

	public OfertaIdiomaBO(OfertaIdiomaVO vo, String certificacion) {
		this.vo = vo;
		this.certificacion = certificacion;
	}
	
	public void setCertificacion(String certificacion) {
		this.certificacion = certificacion;
	}

	public String getIdioma() {
		if (vo.getIdIdioma() < -1){
			return "";
		} else {
			return Constantes.IDIOMAS.getDescripcion((int)vo.getIdIdioma());
		}
	}
	
	public String getDominio() {
		if (vo.getIdDominio() < -1)
			return "";
		else
			return Utils.getDominio((int)vo.getIdDominio());
	}
	
	public String getCertificacion() {
		String certificacion = "";
		if(Constantes.IDIOMAS.isMember(vo.getIdIdioma())){
			try {
				
				if(vo.getIdCertificacion() == Constantes.CATALOGO_OPCION_OTRO_CERTIFICACION){			
					certificacion = "Se requiere una certificación del idioma";
				} else if(vo.getIdCertificacion() == Constantes.CATALOGO_OPCION_NINGUNA){
					certificacion = "";
				} else{ 		
					certificacion = OfertaDetalleDAO.getInstance().getCatalogoOpcion(Constantes.CERTIFICACION.getIdCertificacion(vo.getIdIdioma()), (int)vo.getIdCertificacion());
				}
				
			} catch (SQLException sql) { sql.printStackTrace();}			
		}
		return certificacion;
	}
	
	public String toString() {
		return getIdioma() + "-" + getDominio() + " " + getCertificacion();
	}

}
