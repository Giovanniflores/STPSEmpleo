package mx.gob.stps.portal.core.ws.renapo;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;

/**
 * @author jose.hernandez
 *
 */
public interface CURPService {
	
	/**
	 * @param candidatoVo
	 * @return
	 * @throws Exception 
	 */
	public CandidatoVo consultaCURPPorDatosPersonales(CandidatoVo candidatoVo, String targetEndpoint) throws Exception;

	/**
	 * @param candidatoVo
	 * @return
	 * @throws Exception 
	 */
	public CandidatoVo consultaDatosPersonalesPorCURP(String CURP, String targetEndpoint) throws Exception;

}
