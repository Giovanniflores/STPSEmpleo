package mx.gob.stps.portal.core.oferta.detalle.service;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.List;

import javax.ejb.Remote;

import mx.gob.stps.portal.core.autorizacion.vo.ParametroVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaDetalleCortoVO;
import mx.gob.stps.portal.persistencia.vo.OfertasRecientesVO;

@Remote
public interface OfertaEmpleoAppServiceRemote {
	
	//public int registrarOfertaEmpleo(OfertaEmpleoVO vo) throws BusinessException;
	
	public OfertaEmpleoVO buscarOfertaEmpleo(long idOfertaEmpleo) throws BusinessException;
	
	//public void actualizarOfertaEmpleo(OfertaEmpleoVO vo) throws BusinessException;
	
	public void eliminarOfertaEmpleo(long idOfertaEmpleo) throws BusinessException;
	
	public List<OfertaEmpleoVO> listaOfertaEmpleos(long idEmpresa) throws BusinessException;

	//Son las ofertas de otras bolsas separadas por origen
	public List<OfertaEmpleoVO> consultaOfertasEntidad(int idEntidad) throws SQLException;

	//Es el total de las ofertas del portal mas las que vienen de otras bolsas
	public long consultaTotalOfertasPublicadas();
	
	/*Utilizadas en el reporte de Ofertas Vigentes */
	public List getCurrentPortalOffersCountByEntity();
	
	public List<OfertasRecientesVO> getOfertasRecientes(int tipoConsulta);
	
	public List<ParametroVO> getCurrentExternalOffersCountByEntity();
	
	public List getCurrentPortalOffersCountByEntityStudy();
	
	public List getCurrentPortalOffersCountByEntityExperience();
	
	public List getCurrentOfferEconomicActivity();
	
	public List getCurrentPortalOffersCountByAreaOcupacion();

	public List<OfertaDetalleCortoVO> consultaOfertasDescripcionCorta(List<Long> idsOfertas);
	
	public Integer contarNumeroPlazasResultados(List<Long> indicesOfertas);
}