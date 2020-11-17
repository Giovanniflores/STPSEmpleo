package mx.gob.stps.portal.movil.app.service.util;

import java.lang.reflect.InvocationTargetException;

import mx.gob.stps.portal.core.candidate.vo.InformacionGeneralVO;
import mx.gob.stps.portal.movil.app.empresa.model.base.OfertasEmpresaDTO;
import mx.gob.stps.portal.movil.web.empresa.delegate.EmpresaEspacioDelegate;
import mx.gob.stps.portal.movil.web.empresa.delegate.EmpresaEspacioDelegateImpl;
import mx.gob.stps.portal.movil.web.empresa.form.OfertasEmpresaForm;
import mx.gob.stps.portal.movil.web.infra.exception.ServiceLocatorException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
/*
 * Código ocupado para buscar la oferta de la empresa y para no occupar el mismo código en los class separados
 */
public class OfertaEmpresaUtil {
	
	private static Logger logger = Logger.getLogger(OfertaUtil.class);

	public static OfertasEmpresaDTO busquedaDetalle(long idOferta) {

		OfertasEmpresaDTO ofertaJB = new OfertasEmpresaDTO(getOferta(idOferta));
		/** Se consulta el detalle de la oferta de empleo **/

		return ofertaJB;
	}

	private static OfertasEmpresaForm getOferta(long ofertaId) {
		OfertasEmpresaForm oferta = new OfertasEmpresaForm();
		InformacionGeneralVO infoVO = new InformacionGeneralVO();


		try {
			EmpresaEspacioDelegate services = EmpresaEspacioDelegateImpl.getInstance();
			infoVO = services.showCandidateDetail(ofertaId);

			BeanUtils.copyProperties(oferta, infoVO);

		} catch (ServiceLocatorException le) {
			logger.error(le);
		} catch (IllegalAccessException iae) {
			iae.printStackTrace();
		} catch (InvocationTargetException ite) {
			ite.printStackTrace();
		}

		return oferta;
	}


}
