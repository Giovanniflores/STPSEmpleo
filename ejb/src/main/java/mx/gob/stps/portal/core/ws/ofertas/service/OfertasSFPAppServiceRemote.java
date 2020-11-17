package mx.gob.stps.portal.core.ws.ofertas.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;
import mx.gob.stps.portal.core.ws.ofertas.vo.OfertaEmpleoOutVO;
import mx.gob.stps.portal.core.ws.ofertas.vo.OfertaEmpleoSFPVO;
import mx.gob.stps.portal.core.ws.ofertas.vo.OfertaExternaTotalVO;

@Remote
public interface OfertasSFPAppServiceRemote {
	/**
	 * Inicializa el timer que dispara el proceso de insercion de vacantes proporcionadas
	 * por el WS de SFP
	 */
	public void inicializaTimerSFP();
	
	public OfertaExternaTotalVO transfiereOfertasExternasSFP(Date fecha);
	
	public List<OfertaPorCanalVO> buscaTodasOfertasSFP() throws SQLException;
	
	public List<OfertaEmpleoOutVO> buscarOfertasSFP() throws SQLException;
	
	public List<OfertaEmpleoOutVO> buscarOfertasSFP(int idEntidad, Long idOcupacion) throws SQLException;

	public void shutDownTimer();

	public OfertaEmpleoSFPVO buscarOfertaSFP(Long idOferta) throws SQLException, ParseException;
}
