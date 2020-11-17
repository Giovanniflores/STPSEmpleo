package mx.gob.stps.portal.web.candidate.action;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_EXPERIENCIA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.stps.portal.core.candidate.vo.ConocimientoHabilidadVO;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.CONOC_HAB;
import mx.gob.stps.portal.core.infra.utils.Constantes.EXPERIENCIA;
import mx.gob.stps.portal.core.infra.utils.Constantes.MULTIREGISTRO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.form.ConocimHabilidadForm;
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
 * @author Felipe Juárez Ramírez
 * @since 23/03/2011
 * 
 */
public class ConocimHabilidadAction extends GenericAction {

	private static Logger logger = Logger.getLogger(ConocimHabilidadAction.class);

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
		UsuarioWebVO usuarioWeb = getUsuario(request.getSession());

		String strTipo = request.getParameter("idTipo") != null ? request.getParameter("idTipo") : "1";
		
		long idTipo = Long.parseLong(strTipo);
		if (idTipo == CONOC_HAB.CONOCIMIENTO.getIdOpcion()) {
			request.getSession().setAttribute("ETQ", "Conocimiento");
			request.getSession().setAttribute("ETQ1", "Conocimientos");
		} else {
			request.getSession().setAttribute("ETQ", "Habilidad");
			request.getSession().setAttribute("ETQ1", "Habilidades");
		}
		
		ConocimHabilidadForm conocHabForm = (ConocimHabilidadForm) form;
		conocHabForm.setIdCandidato(usuarioWeb.getIdPropietario());

		conocHabForm.setIdTipoConocimHabilidad(idTipo);
		try {
			CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
			List<ConocimientoHabilidadVO> conocsHabsVO = services.initConocsHabs(usuarioWeb.getIdPropietario(),
					                                                             conocHabForm.getIdTipoConocimHabilidad(),
					                                                             MULTIREGISTRO.ADICIONAL.getIdOpcion());
			// Inicializa arreglo
			conocHabForm.setConocimientosHabilidades(new ConocimientoHabilidadVO[conocsHabsVO.size()]);
			// Copia arreglo
			conocHabForm.setConocimientosHabilidades(conocsHabsVO.toArray(conocHabForm.getConocimientosHabilidades()));
			//logger.info("conocHabForm size: "+ conocHabForm.getConocimientosHabilidades().length);
		} catch (ServiceLocatorException e) {
			logger.error(e); // TODO REPORTAR ERROR
		} catch (SQLException e) {
			logger.error(e); // TODO REPORTAR ERROR
		}

		return mapping.findForward(FORWARD_JSP);
		//request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		//return mapping.findForward(FORWARD_TEMPLATE_CAND);
	}
	
	public ActionForward saveAddKnowledge(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		List<ConocimientoHabilidadVO> conocsHabsVO = null;
		UsuarioWebVO usuarioWeb = getUsuario(request.getSession());
    	long idDominio = Utils.parseLong(request.getParameter("idDominioSelectAdd"));
    	long idExperiencia = Utils.parseLong(request.getParameter("idExperienciaSelectAdd"));
    	String conocimientoHabilidad = request.getParameter("conocimientoAdd");
    	String descripcion = request.getParameter("descripcionConAdd");
    	ConocimientoHabilidadVO conocHabVO = new ConocimientoHabilidadVO();
    	conocHabVO.setConocimientoHabilidad(conocimientoHabilidad);
    	conocHabVO.setDescripcion(descripcion);
    	conocHabVO.setIdDominio(idDominio);
    	conocHabVO.setIdExperiencia(idExperiencia);
    	conocHabVO.setIdTipoConocimHabilidad(CONOC_HAB.CONOCIMIENTO.getIdOpcion());
    	try {
			CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
			//Validar conocimiento o habilidad duplicado
			conocsHabsVO = services.buscarConocsHabs(usuarioWeb.getIdPropietario(), CONOC_HAB.CONOCIMIENTO.getIdOpcion());
			if (null != conocsHabsVO) {
				if (conocsHabsVO.isEmpty())
					conocHabVO.setPrincipal(Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion());
				else
					conocHabVO.setPrincipal(Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion());
			}else {
				conocHabVO.setPrincipal(Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion());
				conocsHabsVO = new ArrayList<ConocimientoHabilidadVO>();
			}
			services.agregarConocHab(usuarioWeb.getIdPropietario(), conocHabVO);
			conocHabVO.setExperiencia(getExperiencia((int)conocHabVO.getIdExperiencia()));
			conocsHabsVO.add(conocHabVO);
		} catch (Exception e) { logger.error(e); }	
		request.getSession().setAttribute("KNOWLEDGE_LIST", conocsHabsVO);
	    return mapping.findForward(FORWARD_JSP);	            	    	
    }
	
	@SuppressWarnings("unchecked")
	public ActionForward deleteKnowledge(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		List<ConocimientoHabilidadVO> conocsHabsVO = null;
		long idCandidatoConocimHabilidad = Utils.parseLong(request.getParameter("idKnow"));
		try {
			CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
			services.borrarConocHab(idCandidatoConocimHabilidad);
			conocsHabsVO = (List<ConocimientoHabilidadVO>)request.getSession().getAttribute("KNOWLEDGE_LIST");
			if (idCandidatoConocimHabilidad!=-1 && conocsHabsVO!=null && !conocsHabsVO.isEmpty()) {
				int index = 0;
				boolean found = false;
				for (index = 0; index < conocsHabsVO.size(); index++) {
					if (conocsHabsVO.get(index).getIdCandidatoConocimHabilidad() == idCandidatoConocimHabilidad) {
						found = true;
						break;
					}
				}
				if (found) conocsHabsVO.remove(index);	
			}
		} catch (Exception e) {logger.error(e); }
		request.getSession().setAttribute("KNOWLEDGE_LIST", conocsHabsVO);
		return mapping.findForward(FORWARD_JSP);
	}
	
	public ActionForward saveAddSkill(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		List<ConocimientoHabilidadVO> conocsHabsVO = null;
		UsuarioWebVO usuarioWeb = getUsuario(request.getSession());
    	long idExperiencia = Utils.parseLong(request.getParameter("idExperienciaSelectAddSkill"));
    	String conocimientoHabilidad = request.getParameter("conocimientoAddSkill");
    	String descripcion = request.getParameter("descripcionSkillAdd");
    	ConocimientoHabilidadVO conocHabVO = new ConocimientoHabilidadVO();
    	conocHabVO.setConocimientoHabilidad(conocimientoHabilidad);
    	conocHabVO.setDescripcion(descripcion);
    	conocHabVO.setIdDominio(0);
    	conocHabVO.setIdExperiencia(idExperiencia);
    	conocHabVO.setIdTipoConocimHabilidad(CONOC_HAB.HABILIDAD.getIdOpcion());
    	try {
			CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
			//Validar conocimiento o habilidad duplicado
			conocsHabsVO = services.buscarConocsHabs(usuarioWeb.getIdPropietario(), CONOC_HAB.HABILIDAD.getIdOpcion());
			if (null != conocsHabsVO) {
				if (conocsHabsVO.isEmpty())
					conocHabVO.setPrincipal(Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion());
				else
					conocHabVO.setPrincipal(Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion());
			}else {
				conocHabVO.setPrincipal(Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion());
				conocsHabsVO = new ArrayList<ConocimientoHabilidadVO>();
			}
			services.agregarConocHab(usuarioWeb.getIdPropietario(), conocHabVO);
			conocHabVO.setExperiencia(getExperiencia((int)conocHabVO.getIdExperiencia()));
			conocsHabsVO.add(conocHabVO);
		} catch (Exception e) { logger.error(e); }	
		request.getSession().setAttribute("SKILLS_LIST", conocsHabsVO);
	    return mapping.findForward("ACTION_REGISTROS_TABLA");	            	    	
    }
	
	@SuppressWarnings("unchecked")
	public ActionForward deleteSkill(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		List<ConocimientoHabilidadVO> conocsHabsVO = null;
		long idCandidatoConocimHabilidad = Utils.parseLong(request.getParameter("idSkill"));
		try {
			CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
			services.borrarConocHab(idCandidatoConocimHabilidad);
			conocsHabsVO = (List<ConocimientoHabilidadVO>)request.getSession().getAttribute("SKILLS_LIST");
			if (idCandidatoConocimHabilidad!=-1 && conocsHabsVO!=null && !conocsHabsVO.isEmpty()) {
				int index = 0;
				boolean found = false;
				for (index = 0; index < conocsHabsVO.size(); index++) {
					if (conocsHabsVO.get(index).getIdCandidatoConocimHabilidad() == idCandidatoConocimHabilidad) {
						found = true;
						break;
					}
				}
				if (found) conocsHabsVO.remove(index);	
			}
		} catch (Exception e) {logger.error(e); }
		return mapping.findForward("ACTION_REGISTROS_TABLA");
	}
	
	private String getExperiencia(int status) {
		String experiencia = null;
		switch(status) {
			case 1: experiencia = EXPERIENCIA.NINGUNA.getOpcion(); break;
			case 2: experiencia = EXPERIENCIA.MENOR_UNO.getOpcion(); break;
			case 3: experiencia = EXPERIENCIA.MENOR_DOS.getOpcion(); break;
			case 4: experiencia = EXPERIENCIA.MENOR_TRES.getOpcion(); break;
			case 5: experiencia = EXPERIENCIA.MENOR_CUATRO.getOpcion(); break;
			case 6: experiencia = EXPERIENCIA.MENOR_CINCO.getOpcion(); break;
			case 7: experiencia = EXPERIENCIA.MAS_CINCO.getOpcion(); break;
			case 8: experiencia = EXPERIENCIA.MENOR_UNO.getOpcion(); break;
			case 9: experiencia = EXPERIENCIA.NO_REQUISITO.getOpcion(); break;
			default : experiencia = ""; break;
		}
		return experiencia;
	}
	
	public ActionForward agregarConocHab(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws IOException {
		UsuarioWebVO usuarioWeb = getUsuario(request.getSession());
		
		ConocimHabilidadForm conocHabForm = (ConocimHabilidadForm) form;
		conocHabForm.setIdCandidato(usuarioWeb.getIdPropietario());
		
		ConocimientoHabilidadVO conocHabVO = new ConocimientoHabilidadVO();
		try {

			BeanUtils.copyProperties(conocHabVO, conocHabForm);

			CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();

			//Validar conocimiento o habilidad duplicado
			List<ConocimientoHabilidadVO> conocsHabsVO = services.buscarConocsHabs(conocHabForm.getIdCandidato(), 
					                                                              conocHabForm.getIdTipoConocimHabilidad());
			boolean _existe = false;
			for (ConocimientoHabilidadVO conocHab : conocsHabsVO ) {
				String conocHabEx = conocHab.getConocimientoHabilidad().trim().toUpperCase();
				String conocHabAct = conocHabVO.getConocimientoHabilidad().trim().toUpperCase();
				
				_existe = (conocHabEx.equals(conocHabAct));
				if (_existe)
					break;
			}

			String[] etq = null;
			if (!_existe) {
				services.agregarConocHab(conocHabForm.getIdCandidato(), conocHabVO);
				//logger.info("idGenerado: " + conocHabVO.getIdCandidatoConocimHabilidad());
				conocHabForm.setIdCandidatoConocimHabilidad(conocHabVO.getIdCandidatoConocimHabilidad());
				
				if (conocHabForm.getIdTipoConocimHabilidad() == CONOC_HAB.CONOCIMIENTO.getIdOpcion())
					etq = new String[] {"Conocimiento agregado"};
				else if (conocHabForm.getIdTipoConocimHabilidad() == CONOC_HAB.HABILIDAD.getIdOpcion())
					etq = new String[] {"Habilidad agregada"};
				
				conocHabForm.setMsg(new ResultVO(super.getMensaje(request, "can.guardar.conocHab", etq), ResultVO.TYPE_SUCCESS));
			
			} else {
				if (conocHabForm.getIdTipoConocimHabilidad() == CONOC_HAB.CONOCIMIENTO.getIdOpcion())
					etq = new String[] {"El conocimiento"};
				else if (conocHabForm.getIdTipoConocimHabilidad() == CONOC_HAB.HABILIDAD.getIdOpcion())
					etq = new String[] {"La habilidad"};

				conocHabForm.setMsg(new ResultVO(super.getMensaje(request, "can.guardar.conocHab.err", etq), ResultVO.TYPE_ERROR));
			}
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
		}

		String json = toJson(conocHabForm);
		redirectJsonResponse(response, json);

		return null;
	}
	
	public ActionForward borrarConocHab(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {

		ConocimHabilidadForm conocHabForm = (ConocimHabilidadForm) form;
		//logger.info("id: " + conocHabForm.getIdCandidatoConocimHabilidad());
		
		try {
			CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
			services.borrarConocHab(conocHabForm.getIdCandidatoConocimHabilidad());
		} catch (ServiceLocatorException e) {
			logger.error(e); // TODO REPORTAR ERROR
		} catch (PersistenceException e) {
			logger.error(e); // TODO REPORTAR ERROR
		}
		
		// String json = toJson(cat);
		// System.out.println("cat:" + json);
		redirectJsonResponse(response, "");

		return null;
	}
	
	public ActionForward cargarExperiencia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		List<CatalogoOpcionVO> opciones = new ArrayList<CatalogoOpcionVO>();

		try {
			long[] filtro = {1,8};
			opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_EXPERIENCIA, filtro, true);
			CatalogoOpcionVO vo = new CatalogoOpcionVO();
			vo.setOpcion("");
			vo.setIdCatalogoOpcion(0);
			opciones.add(0,vo);
			CatalogoVO cat = getCatalogo(opciones);
			String json = toJson(cat);
			redirectJsonResponse(response, json);

		} catch (ServiceLocatorException e){logger.error(e);}
		  catch (IOException e){logger.error(e);}

		return null;
	}

}
