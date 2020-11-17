package mx.gob.stps.portal.core.oferta.detalle.service.impl;

import java.sql.SQLException;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import mx.gob.stps.portal.core.candidate.vo.IdiomaVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.oferta.detalle.bo.OfertaIdiomaBO;
import mx.gob.stps.portal.core.oferta.detalle.dao.OfertaDetalleDAO;
import mx.gob.stps.portal.core.oferta.detalle.service.OfertaVerDetalleAppServiceRemote;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaIdiomaVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaVerDetalleVO;
import mx.gob.stps.portal.core.persistencia.facade.OfertaVerDetalleFacadeLocal;

@Stateless(name = "OfertaVerDetalleAppService", mappedName = "OfertaVerDetalleAppService")
public class OfertaVerDetalleAppService implements OfertaVerDetalleAppServiceRemote {
	
	@EJB private OfertaVerDetalleFacadeLocal ofertaVerDetalleFacade;

	public OfertaVerDetalleAppService() {
	}
	
	@Override
	public int create(OfertaVerDetalleVO vo) throws BusinessException {
		int result = ofertaVerDetalleFacade.create(vo);
		return result;
	}

	@Override
	public OfertaVerDetalleVO findByPK(long idOfertaVerDetalle, int anio, int mes) throws BusinessException {
		return ofertaVerDetalleFacade.findByPK(idOfertaVerDetalle, anio, mes);
	}

	@Override
	public void update(OfertaVerDetalleVO vo) {
		ofertaVerDetalleFacade.update(vo);
	}

	@Override
	public void remove(long idOfertaVerDetalle) {
		ofertaVerDetalleFacade.remove(idOfertaVerDetalle);
	}
	
	public IdiomaVO consultaIdioma(OfertaIdiomaVO ofertaIdioma){
		IdiomaVO idioma = new IdiomaVO();
		
		String certificacion = "";
		if(Constantes.IDIOMAS.isMember(ofertaIdioma.getIdIdioma())){
			try {
				certificacion = OfertaDetalleDAO.getInstance().getCatalogoOpcion(Constantes.CERTIFICACION.getIdCertificacion(ofertaIdioma.getIdIdioma()), ofertaIdioma.getIdIdioma());
			} catch (SQLException sql) { sql.printStackTrace();}			
		}
				
		OfertaIdiomaBO bo = new OfertaIdiomaBO(ofertaIdioma, certificacion);

		//idioma.setIdCandidatoIdioma(idCandidatoIdioma);
		idioma.setIdIdioma(ofertaIdioma.getIdIdioma());
		idioma.setIdioma(bo.getIdioma());
		idioma.setIdCertificacion(ofertaIdioma.getIdCertificacion());
		idioma.setCertificacion(bo.getCertificacion());
		idioma.setIdDominio(ofertaIdioma.getIdDominio());
		idioma.setDominio(bo.getDominio());

		return idioma;
	}

	/*@Override
	public String consultarContactoOferta(long idContacto) {
		String contactoOferta = contactoFacade.getContactoOfertaById(idContacto);
		return contactoOferta;
	}*/

}