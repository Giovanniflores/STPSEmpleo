/**
 * 
 */
package mx.gob.stps.portal.web.candidate.action;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_ESTATUS_GRADO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_GRADO_ESTUDIOS;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.candidate.vo.GradoAcademicoVO;
import mx.gob.stps.portal.core.infra.utils.Constantes.MULTIREGISTRO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.form.GradoAcademicoForm;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;
import mx.gob.stps.portal.web.infra.vo.ResultVO;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Felipe Juàrez Ramìrez
 * @since 18/03/2011
 * 
 */
public class GradoAcademicoAction extends GenericAction {

	private static Logger logger = Logger.getLogger(GradoAcademicoAction.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.stps.portal.web.infra.action.GenericAction#init(org.apache.struts
	 * .action.ActionMapping, org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		GradoAcademicoForm gradosForm = (GradoAcademicoForm) form;
		gradosForm.setIdCandidato(this.getidCandidato(request.getSession()));
		List<CatalogoOpcionVO> opciones = new ArrayList<CatalogoOpcionVO>();
		
		try {
			CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
			
			List<GradoAcademicoVO> gradosVO = services.initGrados(gradosForm.getIdCandidato(), MULTIREGISTRO.ADICIONAL.getIdOpcion());
			// Inicializa arreglo
			gradosForm.setGrados(new GradoAcademicoVO[gradosVO.size()]);
			
			// Copia arreglo
			gradosForm.setGrados(gradosVO.toArray(gradosForm.getGrados()));
			//logger.info("GradosForm size: " + gradosForm.getGrados().length);
			
			opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_GRADO_ESTUDIOS, true);
		
		} catch (ServiceLocatorException e) {
			logger.error(e);
		} catch (SQLException e) {
			logger.error(e);
		}
		
		if (request.getSession().getAttribute("depGrado") == null) {
			//Carga en sesiòn los catalogos asociados a cada opcion
			String[] depCat = new String[opciones.size() + 1];
			depCat[0] = "0";
			
			for (CatalogoOpcionVO opcion : opciones) {
				depCat[(int) opcion.getIdCatalogoOpcion()] = String.valueOf(opcion.getIdCatalagoAsociado());
			}
			
			request.getSession().setAttribute("depGrado", depCat);
		}

        return mapping.findForward(FORWARD_JSP);
	}

	public ActionForward cargarGrados(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		try {
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_GRADO_ESTUDIOS, true);

			CatalogoVO cat = getCatalogo(opciones);
			String json = toJson(cat);
			redirectJsonResponse(response, json);

		} catch (IOException e) {logger.error(e);
		} catch (ServiceLocatorException e) {logger.error(e);}

		return null;
	}
	
	public ActionForward cargarCarrera(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		try {
			long idCatDep = Utils.parseLong(request.getParameter("idCatDep"));
			String json = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogoJSON(idCatDep);

			/*CatalogoVO cat = getCatalogo(opciones);
			String json = toJson(cat);*/
			redirectJsonResponse(response, json);

		} catch (ServiceLocatorException e) {logger.error(e);}
		  catch (IOException e) {logger.error(e);}

		return null;
	}
	
	public ActionForward cargarEstatus(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_ESTATUS_GRADO, true);

			CatalogoVO cat = getCatalogo(opciones);
			String json = toJson(cat);
			redirectJsonResponse(response, json);

		} catch (ServiceLocatorException e) {logger.error(e);}
		  catch (IOException e) {logger.error(e);}

		return null;
	}
	
	public ActionForward agregarGrado(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		GradoAcademicoForm gradoForm = (GradoAcademicoForm) form;
		gradoForm.setIdCandidato(this.getidCandidato(request.getSession()));
		GradoAcademicoVO gradoVO = new GradoAcademicoVO();
		try {
			BeanUtils.copyProperties(gradoVO, gradoForm);
			gradoVO.setPrincipal(MULTIREGISTRO.ADICIONAL.getIdOpcion());
			//logger.info("gradoVO: " + gradoVO.toString());
			//Validar si existe el grado academico
			
			CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
			List<GradoAcademicoVO> gradosVO = services.buscarGrados(gradoForm.getIdCandidato());
			
			boolean _existe = false;
			
			for (GradoAcademicoVO grado : gradosVO ) {
				_existe = (grado.getIdNivelEstudio() == gradoVO.getIdNivelEstudio() && 
					       grado.getIdCarreraEspecialidad() == gradoVO.getIdCarreraEspecialidad());
				
				if (_existe) break;
			}
			
			if (!_existe) {//Se creo exitosamente y no esta duplicado
				services.agregarGrado(gradoForm.getIdCandidato(), gradoVO);
				
				//logger.info("idGenerado: "+gradoVO.getIdCandidatoGradoAcademico());
				gradoForm.setIdCandidatoGradoAcademico(gradoVO.getIdCandidatoGradoAcademico());
				gradoForm.setMsg((new ResultVO(super.getMensaje(request, "can.guardar.grado"), ResultVO.TYPE_SUCCESS)));
				
			} else {//Si ya existe el grado academico
				gradoForm.setMsg((new ResultVO(super.getMensaje(request, "can.guardar.grado.err"), ResultVO.TYPE_ERROR)));
			}
		
			String json = toJson(gradoForm);
			redirectJsonResponse(response, json);

		} catch (ServiceLocatorException e) {logger.error(e);
		} catch (PersistenceException e) {logger.error(e);
		} catch (IllegalAccessException e) {logger.error(e);
		} catch (InvocationTargetException e) {logger.error(e);
		} catch (SQLException e) {logger.error(e);
		} catch(IOException e) {logger.error(e);}
		
		return null;
	}
	
	public ActionForward borrarGrado(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		GradoAcademicoForm gradoForm = (GradoAcademicoForm) form;
		//logger.info("id: "+gradoForm.getIdCandidatoGradoAcademico());
		try {
			CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
			services.borrarGrado(gradoForm.getIdCandidatoGradoAcademico());
			redirectJsonResponse(response, "");
		} catch (ServiceLocatorException e) {
			logger.error(e);
		} catch (PersistenceException e) {
			logger.error(e);
		}catch (IOException e) {logger.error(e);}
		
		return null;
	}
	
	private long getidCandidato(HttpSession session) {
		/*TODO Este codigo es para un candidato, falta definir el caso de un 
		 * Administrador del portal.
		*/
		UsuarioWebVO usuario = super.getUsuario(session);
		return usuario.getIdPropietario();
	}
	
	public ActionForward situacionesAcademicas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			long idEscolaridad = Utils.parseLong(request.getParameter("idEscolaridad"));
			long[] filtro = mx.gob.stps.portal.core.infra.utils.Utils.getFiltroSituacionAcademica(idEscolaridad);
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_ESTATUS_GRADO, filtro, true);
			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {logger.error(e);}

		return null;
	}
	
	private void redirectJsonCatalogo(List<CatalogoOpcionVO> opciones, HttpServletResponse response) throws IOException {
		CatalogoVO cat = getCatalogo(opciones);
		String json = toJson(cat);
		redirectJsonResponse(response, json);
	}	

}
