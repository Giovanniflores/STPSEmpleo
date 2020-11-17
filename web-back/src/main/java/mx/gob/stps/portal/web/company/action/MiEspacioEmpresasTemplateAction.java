package mx.gob.stps.portal.web.company.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.USUARIO_APP;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegate;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegateImpl;
import mx.gob.stps.portal.web.company.form.MiEspacioEmpForm;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

// TODO ELIMINAR CLASE CON EL NUEVO DISEÑO YA NO SE USA
public class MiEspacioEmpresasTemplateAction extends Action {

	private static Logger logger = Logger.getLogger(MiEspacioEmpresasTemplateAction.class);
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();

		MiEspacioEmpForm miEspacioEmpForm = (MiEspacioEmpForm)form;

		UsuarioWebVO usuarioWeb = (UsuarioWebVO)session.getAttribute(USUARIO_APP);

		if (usuarioWeb!=null && usuarioWeb.getEmpresa()){
			
			try{
				long idEmpresa = usuarioWeb.getIdPropietario();

				RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance();
				EmpresaVO empresa = service.findEmpresaById(idEmpresa);
				
				if (empresa==null)
					empresa = service.consultaEmpresaPorAutorizar(idEmpresa);
				
				if (empresa!=null){
					miEspacioEmpForm.setIdEmpresa(idEmpresa);
					miEspacioEmpForm.setNombreEmpresa(empresa.getNombreEmpresa());
					miEspacioEmpForm.setContactoEmpresa(empresa.getContactoEmpresa());
					miEspacioEmpForm.setIdPortalEmpleo(empresa.getIdPortalEmpleo());
				}
			}catch(Exception e){
				logger.error(e);
			}			
		}
		
		return mapping.getInputForward();
	}	
}
