package mx.gob.stps.portal.web.conalep.delegate;

import mx.gob.stps.portal.persistencia.vo.ConalepPlantelVO;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;
import mx.gob.stps.portal.persistencia.vo.ConalepCandidatoVO;


public interface ConalepCandidatoDelegate {
	
	void save(ConalepCandidatoVO candidatoVO) throws Exception;
	public ConalepPlantelVO findPlantel(long idPlantel) throws Exception;
	ConalepCandidatoVO obtenerConalepCandidato(long idCandidato) throws Exception;
	CatalogoVO getCatalogo(String catalogo)throws Exception;
	CatalogoVO obtenerCatalogoConalepAreaNegocio() throws Exception;
	CatalogoVO obtenerCatalogoConalepSubAreaNegocio(long idAreaNegocio) throws Exception;
	CatalogoVO obtenerCatalogoConalepPlantel(long idEntidad) throws Exception;
	CatalogoVO obtenerCatalogoPlanEstudios() throws Exception;
	boolean estaRegistrado(Long idCandidato) throws Exception;

}
