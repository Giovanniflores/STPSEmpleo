package mx.gob.stps.portal.movil.web.empresa.action;


import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.utils.Constantes.PERFIL;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PERSONA;
import mx.gob.stps.portal.movil.web.empresa.delegate.EmpresaEspacioDelegate;
import mx.gob.stps.portal.movil.web.empresa.delegate.EmpresaEspacioDelegateImpl;
import mx.gob.stps.portal.movil.web.empresa.form.EmpresaEspacioForm;
import mx.gob.stps.portal.movil.web.infra.action.GenericAction;
import mx.gob.stps.portal.movil.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.movil.web.seguridad.vo.UsuarioFirmadoVO;
import mx.gob.stps.portal.movil.web.infra.utils.Utils;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class EmpresaEspacioAction extends GenericAction{

	private static Logger logger = Logger.getLogger(EmpresaEspacioAction.class);
	private static final String FORWARD_DATOS_EMPRESA = "JSP_DATOS_EMPRESA";
	private static final String FORWARD_INICIO = "HOME";
	
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ActionForward forward = mapping.getInputForward();
		
		if(isLogged(request.getSession())){
			
			UsuarioFirmadoVO usuario = getUsuarioFirmado(session);
			
			if(usuario.getIdPerfil()!=PERFIL.EMPRESA.getIdOpcion()){
				
				forward = mapping.findForward(FORWARD_INICIO);
				
			}
		}else{
			
			forward = mapping.findForward(FORWARD_INICIO);
		}
		
		return forward;
	}
	
	public ActionForward detalleEmpresa(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse respone){
		
		logger.info(""+mapping.getInputForward().getPath());
		HttpSession session = request.getSession();
		ActionForward forward = mapping.findForward(FORWARD_DATOS_EMPRESA);
		
		
		try {
			UsuarioFirmadoVO usuario = getUsuarioFirmado(session);
			long idEmpresa = usuario.getIdPropietario();
			
			EmpresaEspacioDelegate service = EmpresaEspacioDelegateImpl.getInstance();
			
			EmpresaVO empresa = service.findEmpresaById(idEmpresa);
			
			
			String tipoPersona = TIPO_PERSONA.getTipoPersona((int)empresa.getIdTipoPersona());
			empresa.setTipoPersona(tipoPersona);
			
			Date fechaEmp = null;
			
			if (empresa.getIdTipoPersona() == TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()){
				fechaEmp = empresa.getFechaNacimiento();
			} else if (empresa.getIdTipoPersona() == TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona()){
				fechaEmp = empresa.getFechaActa();
			}
			
			String fechaEmpresa = Utils.formatDDMMYYYY(fechaEmp);
			
			
			
			request.getSession().setAttribute("empresafechaheader", fechaEmpresa);
			request.getSession().setAttribute("empresaHeader", empresa);
			
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServiceLocatorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return forward;
	}


	
	

}
