package mx.gob.stps.portal.web.candidate.action;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_TIPO_CONTRATO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_TIPO_EMPLEO;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.candidate.vo.ExpectativaLaboralVO;
import mx.gob.stps.portal.core.infra.utils.Constantes.MULTIREGISTRO;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.form.ExpecLaboralForm;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.vo.ResultVO;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Felipe
 *
 */
public class ExpecLaboralAction extends GenericAction {
	
	private static Logger logger = Logger.getLogger(ExpecLaboralAction.class);
	
	/* (non-Javadoc)
	 * @see mx.gob.stps.portal.web.infra.action.GenericAction#init(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		ExpecLaboralForm expecLaboralForm = (ExpecLaboralForm) form;
		expecLaboralForm.setIdCandidato(this.getidCandidato(request.getSession()));

		try {
			CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
			List<ExpectativaLaboralVO> expecLaboralesVO = services.initExpecLaboral(expecLaboralForm.getIdCandidato(), MULTIREGISTRO.ADICIONAL.getIdOpcion());

			expecLaboralForm.setExpecLaborales(new ExpectativaLaboralVO[expecLaboralesVO.size()]);

			expecLaboralForm.setExpecLaborales(expecLaboralesVO.toArray(expecLaboralForm.getExpecLaborales()));

			//logger.info("expecLaboralForm size: "+ expecLaboralForm.getExpecLaborales().length);
		} catch (ServiceLocatorException e) {
			logger.error(e);
		} catch (SQLException e) {
			logger.error(e);
		}

		return mapping.findForward(FORWARD_JSP);
	}
	
	public ActionForward agregar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		ExpecLaboralForm expLabForm = (ExpecLaboralForm) form;
		expLabForm.setIdCandidato(this.getidCandidato(request.getSession()));

		try {
			CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
			List<ExpectativaLaboralVO> expecLaboralesVO = services.buscarExpecLaboral(expLabForm.getIdCandidato());
			
			boolean _existe = false;
			
			for (ExpectativaLaboralVO expLab : expecLaboralesVO) {
				String puestoEx = expLab.getPuestoDeseado().trim().toUpperCase();
				String puestoAct = expLabForm.getPuestoDeseado().trim().toUpperCase();
				
				_existe = (puestoEx.equals(puestoAct) && expLab.getIdSectorDeseado() == expLabForm.getIdSectorDeseado() &&
						                                 expLab.getIdAreaLaboralDeseada() == expLabForm.getIdAreaLaboralDeseada() &&
						                                 expLab.getIdOcupacionDeseada() == expLabForm.getIdOcupacionDeseada());
				if (_existe) break;
			}
			
			if (!_existe) {
				ExpectativaLaboralVO expecLaboralVO = new ExpectativaLaboralVO();
				BeanUtils.copyProperties(expecLaboralVO, expLabForm);

				services.agregarExpecLaboral(expLabForm.getIdCandidato(), expecLaboralVO);

				expLabForm.setMsg(new ResultVO(getMensaje(request, "can.guardar.salario"), ResultVO.TYPE_SUCCESS));
			
			} else {
				expLabForm.setMsg(new ResultVO(getMensaje(request, "can.guardar.salario.err"), ResultVO.TYPE_ERROR));
			}
		
			String json = toJson(expLabForm);
			redirectJsonResponse(response, json);

		} catch (ServiceLocatorException e) {logger.error(e);
		} catch (PersistenceException e) {logger.error(e);
		} catch (IllegalAccessException e) {logger.error(e);
		} catch (InvocationTargetException e) {logger.error(e);
		} catch (SQLException e) {logger.error(e);
		} catch (IOException e) {logger.error(e);}
		
		return null;
	}
	
	public ActionForward borrar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		ExpecLaboralForm expecLaboralForm = (ExpecLaboralForm) form;

		try {
			CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
			services.borrarExpecLaboral(expecLaboralForm.getIdExpectativaLaboral());
			redirectJsonResponse(response, "");

		} catch (ServiceLocatorException e) {logger.error(e);
		} catch (PersistenceException e) {logger.error(e);
		} catch (IOException e) {logger.error(e);}
		
		return null;
	}
	
	public ActionForward cargarTipoEmpleo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		try {
			String json = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogoJSON(CATALOGO_OPCION_TIPO_EMPLEO);
			redirectJsonResponse(response, json);
		} catch (ServiceLocatorException e) {logger.error(e);
		} catch (IOException e) {logger.error(e);}

		return null;
	}
	
	public ActionForward cargarTipoContrato(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String json = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogoJSON(CATALOGO_OPCION_TIPO_CONTRATO);
			redirectJsonResponse(response, json);

		} catch (ServiceLocatorException e) {logger.error(e);
		} catch (IOException e) {logger.error(e);}

		return null;
	}
	
	private long getidCandidato(HttpSession session) {
		UsuarioWebVO usuario = getUsuario(session);
		return usuario.getIdPropietario();
	}

}
