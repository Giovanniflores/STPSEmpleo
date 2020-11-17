/**
 * 
 */
package mx.gob.stps.portal.web.candidate.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_CAND;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.candidate.vo.ComputacionAvanzadaVO;
import mx.gob.stps.portal.core.infra.utils.Constantes.MULTIREGISTRO;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.form.ComputacionAvanzadaForm;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.vo.ResultVO;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Felipe Juárez Ramírez
 * @since 24/03/2011
 *
 */
public class ComputacionAvanzadaAction extends GenericAction {
	
	private static Logger logger = Logger
	.getLogger(ComputacionAvanzadaAction.class);
	
	/* (non-Javadoc)
	 * @see mx.gob.stps.portal.web.infra.action.GenericAction#init(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("ComputacionAvanzadaAction.init");
		ComputacionAvanzadaForm compuAvanzadaForm = (ComputacionAvanzadaForm) form;
		compuAvanzadaForm.setIdCandidato(this.getidCandidato(request.getSession()));
		try {
			CandidatoBusDelegate services = CandidatoBusDelegateImpl
					.getInstance();
			List<ComputacionAvanzadaVO> compuAvanzadasVO = services.
			initCompuAvanzadas(compuAvanzadaForm.getIdCandidato(),
					MULTIREGISTRO.ADICIONAL.getIdOpcion());
			// Inicializa arreglo
			compuAvanzadaForm
					.setAvanzadas(new ComputacionAvanzadaVO[compuAvanzadasVO
							.size()]);
			// Copia arreglo
			compuAvanzadaForm.setAvanzadas(compuAvanzadasVO
					.toArray(compuAvanzadaForm.getAvanzadas()));
			logger.info("conocimHabilidadForm size: "
					+ compuAvanzadaForm.getAvanzadas().length);
		} catch (ServiceLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getSession().setAttribute(BODY_JSP,
				mapping.findForward(FORWARD_JSP).getPath());
		return mapping.findForward(FORWARD_TEMPLATE_CAND);
	}
	
	public ActionForward agregarCompuAvanzada(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException {
		//logger.info("ComputacionAvanzadaAction.agregarCompuAvanzada");
		ComputacionAvanzadaForm compuAvanzadaForm = (ComputacionAvanzadaForm) form;
		try {
			//Se obtiene usuario de sesion
			compuAvanzadaForm.setIdCandidato(this.getidCandidato(request.getSession()));
			CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();

			//Validar duplicidad
			List<ComputacionAvanzadaVO> compuAvanzadasVO = services.buscarCompuAvanzadas(compuAvanzadaForm.getIdCandidato());
			boolean _existe = false;
			
			for (ComputacionAvanzadaVO cmpAvan : compuAvanzadasVO) {
				String strCmpEx = cmpAvan.getSoftwareHardware().trim().toUpperCase();
				String strCmpAct = compuAvanzadaForm.getSoftwareHardware().trim().
				toUpperCase();
				_existe = (strCmpEx.equals(strCmpAct));
				if (_existe)
					break;
			}
			
			if (!_existe) {
				ComputacionAvanzadaVO compuAvanzadaVO = new ComputacionAvanzadaVO();
				System.out.println("--1---ComputacionAvanzadaAction.agregarCompuAvanzada compuAvanzadaForm.getIdCandidato():" + compuAvanzadaForm.getIdCandidato());
				BeanUtils.copyProperties(compuAvanzadaVO, compuAvanzadaForm);
				System.out.println("---2--ComputacionAvanzadaAction.agregarCompuAvanzada compuAvanzadaForm.getIdCandidato():" + compuAvanzadaForm.getIdCandidato());
				//logger.info("compuAvanzadaVO: " + compuAvanzadaVO.toString());
				services.agregarCompuAvanzada(compuAvanzadaForm.getIdCandidato(), compuAvanzadaVO);
				//logger.info("idGenerado: " + compuAvanzadaVO.getIdCandidatoCompuAvanzada());
				compuAvanzadaForm.setIdCandidatoCompuAvanzada(compuAvanzadaVO.getIdCandidatoCompuAvanzada());
				compuAvanzadaForm.setMsg(new ResultVO(super.getMensaje(request, "can.guardar.cmp"), ResultVO.TYPE_SUCCESS));
			} else {
				compuAvanzadaForm.setMsg(new ResultVO(super.getMensaje(request, "can.guardar.cmp.err"), ResultVO.TYPE_ERROR));
			}
		} catch (ServiceLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String json = toJson(compuAvanzadaForm);
		redirectJsonResponse(response, json);

		return null;
	}
	
	public ActionForward borrarCompuAvanzada(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		ComputacionAvanzadaForm compuAvanzadaForm = (ComputacionAvanzadaForm) form;
		logger.info("id: " + compuAvanzadaForm.getIdCandidatoCompuAvanzada());
		try {
			CandidatoBusDelegate services = CandidatoBusDelegateImpl
					.getInstance();
			services.borrarCompuAvanzada(compuAvanzadaForm.getIdCandidatoCompuAvanzada());
		} catch (ServiceLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String json = toJson(cat);
		// System.out.println("cat:" + json);
		redirectJsonResponse(response, "");

		return null;
	}
	
	private long getidCandidato(HttpSession session) {
		UsuarioWebVO usuario = super.getUsuario(session);
		return usuario.getIdPropietario();
	}
}
