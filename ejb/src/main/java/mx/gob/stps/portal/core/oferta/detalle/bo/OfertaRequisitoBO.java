package mx.gob.stps.portal.core.oferta.detalle.bo;

import mx.gob.stps.portal.core.oferta.detalle.helper.Utils;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaRequisitoVO;

public class OfertaRequisitoBO {
	
	private OfertaRequisitoVO vo = null;

	public OfertaRequisitoBO() {
		this.vo = new OfertaRequisitoVO();
	}
	
	public OfertaRequisitoBO(OfertaRequisitoVO vo) {
		this.vo = vo;
	}
	
	public String getDescripcion() {
		if (null == vo.getDescripcion())
			return "";
		else
			return vo.getDescripcion();
	}
	
	public String getExperiencia() {
		if (vo.getIdExperiencia() < -1)
			return "";
		else
			return Utils.getExperiencia((int)vo.getIdExperiencia());
	}
	
	public String getDominio() {
		if (vo.getIdDominio() < -1)
			return "";
		else
			return Utils.getDominio((int)vo.getIdDominio());
	}
	
	public long tipoRequisito() {
		if (vo.getIdTipoRequisito() > 0)
			return vo.getIdTipoRequisito();
		else return 0;
	}
	
	public String toString() {
		return getDescripcion() + " " + getExperiencia() + " " + getDominio();
	}
}