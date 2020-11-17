package mx.gob.stps.portal.movil.app.service.util;

import java.lang.reflect.InvocationTargetException;

import mx.gob.stps.portal.core.candidate.vo.InformacionGeneralVO;
import mx.gob.stps.portal.movil.app.empresa.model.base.BusquedaCandidatoDTO;
import mx.gob.stps.portal.movil.web.candidato.form.CandidatoForm;
import mx.gob.stps.portal.movil.web.empresa.delegate.EmpresaEspacioDelegate;
import mx.gob.stps.portal.movil.web.empresa.delegate.EmpresaEspacioDelegateImpl;
import mx.gob.stps.portal.movil.web.infra.exception.ServiceLocatorException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

public class CandidatoUtil {
	
	private static Logger logger = Logger.getLogger(OfertaUtil.class);

	public static BusquedaCandidatoDTO busquedaDetalle(long idCandidato) {

	BusquedaCandidatoDTO candidatoJB = new BusquedaCandidatoDTO(getCandidato(idCandidato));
		/** Se consulta el detalle de la oferta de empleo **/

		return candidatoJB;
	}

	/*
	 * Obtener los detailles del candidato y mapearlo a CandidatoForm
	 */
	private static CandidatoForm getCandidato(long candidatoId) {
		CandidatoForm candidato = new CandidatoForm();
		InformacionGeneralVO infoVO = new InformacionGeneralVO();


		try {
			EmpresaEspacioDelegate services = EmpresaEspacioDelegateImpl.getInstance();
			infoVO = services.showCandidateDetail(candidatoId);

			BeanUtils.copyProperties(candidato, infoVO);

		} catch (ServiceLocatorException le) {
			logger.error(le);
		} catch (IllegalAccessException iae) {
			iae.printStackTrace();
		} catch (InvocationTargetException ite) {
			ite.printStackTrace();
		}

		return candidato;
	}


}
