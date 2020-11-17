package mx.gob.stps.portal.core.oferta.detalle.service;

import java.sql.Connection;

import javax.ejb.Local;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorPerfilVO;

@Local
public interface OfertaCompatibilidadAppServiceLocal {
	
	public int match(long idOfertaEmpleo, long idCandidato);

	public int match(long idOfertaEmpleo, long idCandidato, Connection conn);
	
	public OfertaPorPerfilVO buscaOfertaRecomendada(CandidatoVo candidatoVo);
}