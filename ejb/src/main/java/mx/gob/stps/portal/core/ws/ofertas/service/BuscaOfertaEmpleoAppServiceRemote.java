package mx.gob.stps.portal.core.ws.ofertas.service;

import java.util.List;

import javax.ejb.Remote;

import mx.gob.stps.portal.core.ws.ofertas.vo.OfertaEmpleoInVO;
import mx.gob.stps.portal.core.ws.ofertas.vo.OfertaEmpleoOutVO;

@Remote
public interface BuscaOfertaEmpleoAppServiceRemote {
	/**
	 * Realiza la busqueda en los ws proporcinados por bolsas de trabajo externas: OCC, Bumeran, ManPower, Adecco y Universal
	 * @param vo filtro para la busqueda
	 * @return lista de objetos OfertaEmpleoOutVO los cuales los datos de las vacantes encontradas
	 */
	public List<OfertaEmpleoOutVO> buscaOfertaEmpleo(OfertaEmpleoInVO vo);
	
	//public List<OfertaEmpleoJB> extraeSiguientesOfertas(List<Long> listaOfertasTodas, Long idOfertaEmpleo, int numOfertas);
	
}
