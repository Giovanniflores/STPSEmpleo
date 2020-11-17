/**
 * 
 */
package mx.gob.stps.portal.web.candidate.action;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_AREA_LABORAL;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_JERARQUIA_PUESTO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_OCUPACION;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_PERSONAS_CARGO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_SUBSECTOR;
import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_CAND;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.candidate.vo.HistoriaLaboralVO;
import mx.gob.stps.portal.core.infra.utils.Constantes.MULTIREGISTRO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.form.HistoriaLaboralForm;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;
import mx.gob.stps.portal.web.infra.vo.ResultVO;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Felipe Juárez Ramírez
 * @since 25/03/2011
 *
 */
public class HistoriaLaboralAction extends GenericAction {
	
	private static Logger logger = Logger.getLogger(HistoriaLaboralAction.class);

	/* (non-Javadoc)
	 * @see mx.gob.stps.portal.web.infra.action.GenericAction#init(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		HistoriaLaboralForm histLaboralForm = (HistoriaLaboralForm) form;
		histLaboralForm.setIdCandidato(this.getidCandidato(request.getSession()));

		try {
			CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
			List<HistoriaLaboralVO> histLaboralesVO = services.
			initHistLaboral(histLaboralForm.getIdCandidato(),MULTIREGISTRO.ADICIONAL.getIdOpcion());
			request.getSession().setAttribute("detalles", histLaboralesVO);
		
		} catch (ServiceLocatorException e) {
			logger.error(e); // TODO REPORTAR ERROR
		} catch (SQLException e) {
			logger.error(e); // TODO REPORTAR ERROR
		}
		
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		return mapping.findForward(FORWARD_TEMPLATE_CAND);
	}
	
	public ActionForward agregarHistLaboral(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		HistoriaLaboralForm histLaboralForm = (HistoriaLaboralForm) form;
		histLaboralForm.setIdCandidato(this.getidCandidato(request.getSession()));

		try {			
			histLaboralForm.setLaboresFinal(formatoFechaCompuesto(histLaboralForm.getLaboresFinal()));
			CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
			//Validar duplicado
			
			List<HistoriaLaboralVO> histLaboralesVO = services.buscarHistLaboral(histLaboralForm.getIdCandidato());
			boolean _existe = false;
			for(HistoriaLaboralVO histLab : histLaboralesVO) {
				_existe = (histLab.getIdSector() == histLaboralForm.getIdSector() &&
						histLab.getIdAreaLaboral() == histLaboralForm.getIdAreaLaboral() &&
						histLab.getIdOcupacion() == histLaboralForm.getIdOcupacion());
				if (_existe)
					break;
			}
			
			if (!_existe) {
				HistoriaLaboralVO histLaboralVO = new HistoriaLaboralVO();
				BeanUtils.copyProperties(histLaboralVO, histLaboralForm);
				//logger.info("histLaboralVO: " + histLaboralVO.toString());
				
				services.agregarHistLaboral(histLaboralForm.getIdCandidato(), histLaboralVO);
				//logger.info("idGenerado: " + histLaboralVO.getIdHistorialLaboral());
				
				histLaboralForm.setMsg(new ResultVO(super.getMensaje(request, "can.guardar.histLab"), ResultVO.TYPE_SUCCESS));
			} else {
				histLaboralForm.setMsg(new ResultVO(super.getMensaje(request, "can.guardar.histLab.err"), ResultVO.TYPE_ERROR));
			}
		
			String json = toJson(histLaboralForm);
			redirectJsonResponse(response, json);

		} catch (ServiceLocatorException e) {
			logger.error(e); // TODO REPORTAR ERROR
		} catch (PersistenceException e) {
			logger.error(e); // TODO REPORTAR ERROR
		} catch (IllegalAccessException e) {
			logger.error(e); // TODO REPORTAR ERROR
		} catch (InvocationTargetException e) {
			logger.error(e); // TODO REPORTAR ERROR
		} catch (SQLException e) {
			logger.error(e); // TODO REPORTAR ERROR
		} catch (IOException e) {
			logger.error(e); // TODO REPORTAR ERROR
		}
		
		return null;
	}
	
	public ActionForward borrarHistLaboral(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HistoriaLaboralForm histLaboralForm = (HistoriaLaboralForm) form;
		logger.info("id: " + histLaboralForm.getIdHistorialLaboral());

		try {
			CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
			services.borrarHistLaboral(histLaboralForm.getIdHistorialLaboral());

			// String json = toJson(cat);
			redirectJsonResponse(response, "");
			
		} catch (ServiceLocatorException e) {
			logger.error(e); // TODO REPORTAR ERROR
		} catch (PersistenceException e) {
			logger.error(e); // TODO REPORTAR ERROR
		} catch (IOException e) {
			logger.error(e); // TODO REPORTAR ERROR
		}

		return null;
	}
	
	public ActionForward cargarSector(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		/*List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_SECTOR, false);
		CatalogoVO cat = getCatalogo(opciones);
		String json = toJson(cat);*/
		
		try{
			String json = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogoJSON(CATALOGO_OPCION_SUBSECTOR);
			redirectJsonResponse(response, json);			
		} catch(ServiceLocatorException e){logger.error(e);}
		  catch(IOException e){logger.error(e);}

		return null;
	}
	
	public ActionForward cargarAreaLaboral(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			/*List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_AREA_LABORAL);
			CatalogoVO cat = getCatalogo(opciones);
			String json = toJson(cat);*/

			String json = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogoJSON(CATALOGO_OPCION_AREA_LABORAL);
			redirectJsonResponse(response, json);

		} catch(ServiceLocatorException e){logger.error(e);}
		  catch(IOException e){logger.error(e);}

		
		return null;
	}
	
	public ActionForward cargarOcupacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String idCatPadre = null;

		try {
			if (request.getParameter("idAreaLaboral") != null) {
				idCatPadre = "" + request.getParameter("idAreaLaboral") + "%";
			} else {
				redirectJsonResponse(response, "");				
				return null;
			}

			List<CatalogoOpcionVO> opciones = new ArrayList<CatalogoOpcionVO>();
			
			opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_OCUPACION, idCatPadre, true);

			CatalogoVO cat = getCatalogo(opciones);
			String json = toJson(cat);
			redirectJsonResponse(response, json);
		
		} catch (SQLException e) {logger.error(e);}
		  catch(ServiceLocatorException e){logger.error(e);}
		  catch(IOException e){logger.error(e);}


		return null;
	}
	
	public ActionForward cargarJerarquia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		try {
			/*List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_JERARQUIA_PUESTO, true);
			CatalogoVO cat = getCatalogo(opciones);
			String json = toJson(cat);*/

			String json = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogoJSON(CATALOGO_OPCION_JERARQUIA_PUESTO);
			redirectJsonResponse(response, json);

		} catch (ServiceLocatorException e) {logger.error(e);}
		  catch(IOException e){logger.error(e);}

		return null;
	}
	
	public ActionForward cargarPersonasCargo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			/*List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_PERSONAS_CARGO, true);
			CatalogoVO cat = getCatalogo(opciones);
			String json = toJson(cat);*/

			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_PERSONAS_CARGO, true);			
			CatalogoVO cat = getCatalogo(opciones);
			String json = toJson(cat);
			
			redirectJsonResponse(response, json);

		} catch (ServiceLocatorException e) {logger.error(e);}
		  catch(IOException e){logger.error(e);}

		return null;
	}
	
	private long getidCandidato(HttpSession session) {
		/*TODO Este codigo es para un candidato, falta definir el caso de un 
		 * Administrador del portal.
		*/
		long idCandidato = 0;
		
		UsuarioWebVO usuario = super.getUsuario(session);
		if (usuario!=null){
			idCandidato = usuario.getIdPropietario();
		}

		return idCandidato;
	}
	
	private static String formatoFechaCompuesto(String cadena){		
		if("".equals(cadena)){
			return "2099-11-11";
		}
		String ano = cadena.substring(6,10);
		String mes = cadena.substring(3,5);
		String dia = cadena.substring(0,2);			
		return ano + "-" + mes + "-" + dia;
	}
}
