/**
 * 
 */
package mx.gob.stps.portal.web.candidate.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.candidate.vo.ExpectativaLugarVO;
import mx.gob.stps.portal.core.infra.utils.Constantes.MULTIREGISTRO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.web.address.delegate.DomicilioBusDelegate;
import mx.gob.stps.portal.web.address.delegate.DomicilioBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.form.ExpecLugarForm;
import mx.gob.stps.portal.web.infra.action.GenericAction;
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
 * @since 27/03/2011
 *
 */
public class ExpecLugarAction extends GenericAction {

	private static Logger logger = Logger.getLogger(ExpecLugarAction.class);
	
	/* (non-Javadoc)
	 * @see mx.gob.stps.portal.web.infra.action.GenericAction#init(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {

		ExpecLugarForm expecLugarForm = (ExpecLugarForm) form;
		expecLugarForm.setIdCandidato(this.getidCandidato(request.getSession()));

		try {
			CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
			List<ExpectativaLugarVO> expecLugaresVO = services.initExpecLugar(expecLugarForm.getIdCandidato(),MULTIREGISTRO.ADICIONAL.getIdOpcion());
			
			expecLugarForm.setExpecLugares(new ExpectativaLugarVO[expecLugaresVO.size()]);
			
			expecLugarForm.setExpecLugares(expecLugaresVO.toArray(expecLugarForm.getExpecLugares()));
			logger.info("expecLaboralForm size: " + expecLugarForm.getExpecLugares().length);
		} catch (ServiceLocatorException e) {
			logger.error(e);
		} catch (SQLException e) {
			logger.error(e);
		}
		
		return mapping.findForward(FORWARD_JSP);
	}
	
	public ActionForward agregar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){

		ExpecLugarForm expLugForm = (ExpecLugarForm) form;
		expLugForm.setIdCandidato(this.getidCandidato(request.getSession()));
		ExpectativaLugarVO expecLugarVO = new ExpectativaLugarVO();

		try {
			CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
			List<ExpectativaLugarVO> expecLugaresVO = services.buscarExpecLugar(expLugForm.getIdCandidato());
			
			boolean _existe = false;
			
			for (ExpectativaLugarVO expLug : expecLugaresVO) {
				_existe = (expLug.getIdEntidadDeseada() == expLugForm.getIdEntidadDeseada() && 
						   expLug.getIdMunicipioDeseado() == expLugForm.getIdMunicipioDeseado());
				if (_existe)
					break;
			}
			
			if (!_existe) {
				BeanUtils.copyProperties(expecLugarVO, expLugForm);
				services.agregarExpecLugar(expLugForm.getIdCandidato(), expecLugarVO);
				expLugForm.setMsg(new ResultVO(super.getMensaje(request, "can.guardar.ubicacion"), ResultVO.TYPE_SUCCESS));
			
			} else {
				expLugForm.setMsg(new ResultVO(super.getMensaje(request, "can.guardar.ubicacion.err"), ResultVO.TYPE_ERROR));
			}
			
			String json = toJson(expLugForm);
			redirectJsonResponse(response, json);

		} catch (ServiceLocatorException e) {
			logger.error(e);
		} catch (PersistenceException e) {
			logger.error(e);
		} catch (IllegalAccessException e) {
			logger.error(e);
		} catch (InvocationTargetException e) {
			logger.error(e);
		} catch (SQLException e) {
			logger.error(e);
		}catch (IOException e) {
			logger.error(e);
		}

		return null;
	}
	
	public ActionForward borrar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		ExpecLugarForm expecLugarForm = (ExpecLugarForm) form;
		try {
			CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
			services.borrarExpecLugar(expecLugarForm.getIdExpectativaLugar());

			redirectJsonResponse(response, "");
		} catch (ServiceLocatorException e) {
			logger.error(e);
		} catch (PersistenceException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}
		
		return null;
	}
	
	public ActionForward obtenerMunicipio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		String idEntidad = request.getParameter("idEntidad");

		try {			
			DomicilioBusDelegate services = DomicilioBusDelegateImpl.getInstance();
			List<CatalogoOpcionVO> opciones = services.consultaMunicipios(Utils.parseLong(idEntidad));

			/*for (MunicipioVO elementoN : municipios) {
				CatalogoOpcionVO cat = municipioCatOpcion(elementoN);
				opciones.add(cat);
			}*/

			CatalogoVO cat = getCatalogo(opciones);
			String json = toJson(cat);
			redirectJsonResponse(response, json);

		} catch (ServiceLocatorException e) {
			logger.error(e);
		} catch (SQLException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}

		return null;
	}
	
	/*private CatalogoOpcionVO municipioCatOpcion(MunicipioVO mun) {
		CatalogoOpcionVO cat = new CatalogoOpcionVO();

		cat.setIdCatalogo(mun.getIdEntidad());
		cat.setIdCatalogoOpcion(mun.getIdMunicipio());
		cat.setOpcion(mun.getMunicipio());
		cat.setFechaAlta(null);
		cat.setEstatus(0);
		cat.setFechaModificacion(null);

		return cat;
	}*/
	
	private long getidCandidato(HttpSession session) {
		/*TODO Este codigo es para un candidato, falta definir el caso de un 
		 * Administrador del portal.
		*/
		UsuarioWebVO usuario = super.getUsuario(session);
		return usuario.getIdPropietario();
	}

}
