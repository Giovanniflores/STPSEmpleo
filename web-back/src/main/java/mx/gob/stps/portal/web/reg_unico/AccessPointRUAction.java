package mx.gob.stps.portal.web.reg_unico;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.stps.portal.utils.ConstantesGenerales;
import mx.gob.stps.portal.utils.Password;
import mx.gob.stps.portal.web.infra.action.GenericAction;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
//import javax.inject.Inject;


public class AccessPointRUAction extends GenericAction {
	

	private static final long serialVersionUID = -4747085360470350826L;
	private static final Logger logger = Logger.getLogger(AccessPointRUAction.class);

	private static final String FORWARD_EMPRESA   =  "RU_EMP";	
	private static final String FORWARD_CANDIDATO =  "RU_CAND";
	private static final String FORWARD_OFERTA_PLANTILLA =  "RU_OF_PLANT";
//	private static final String FORWARD_CANDIDATO_COMPLEMENTARIO= "RU_CAND_COMPLEMENT";
	private static final String ERROR  = "ERROR";
	
	private String paramValueApp=null;				
	private String paramValueOp= null;
	private String paramValueUser=null;
	private String paramValuePass=null;
	private String paramValueIdProp=null;

	public ActionForward init(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		
		try{
			
			if (request.getParameter("token") != null && !"".equals(request.getParameter("token"))) {
				
				String token = request.getParameter("token");				
				if (request.getParameter("token").contains(" ")){
					token = request.getParameter("token").replace(" ", "+");
				}
				logger.info("token:"+token);

				String parametro="";
				try {
					parametro = Password.decrypt(token.trim());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				String[] cadena = parametro.split("&");
				Map<String,String> valuesMap=new HashMap<String,String>();
				for(String par:cadena){
					logger.info("PAR: ["+par+"]");
					String param=par.substring(0,par.indexOf("="));
					String value=par.substring(par.indexOf("=")+1);
					valuesMap.put(param, value);
				}

				paramValueApp  = (valuesMap.get(ConstantesGenerales.RU_PARAM_APP.toString())!=null) ? (String)valuesMap.get(ConstantesGenerales.RU_PARAM_APP.toString()) : "";				
				paramValueOp   = (valuesMap.get(ConstantesGenerales.RU_PARAM_OP)!=null) ? (String)valuesMap.get(ConstantesGenerales.RU_PARAM_OP) : "";
				paramValueUser = (String)valuesMap.get(ConstantesGenerales.RU_PARAM_USER);
				paramValuePass = (String)valuesMap.get(ConstantesGenerales.RU_PARAM_PASS);
				paramValueIdProp = (String)valuesMap.get(ConstantesGenerales.RU_PARAM_ID_PROPIETARIO);
				
				request.getSession().setAttribute(ConstantesGenerales.RU_PARAM_USER, paramValueUser);
				request.getSession().setAttribute(ConstantesGenerales.RU_PARAM_ID_PROPIETARIO, paramValueIdProp);
				
				//Si es empresa o oferta, regresamos al espacio de empresa
				if( paramValueOp !=null && (paramValueOp.equals(ConstantesGenerales.RU_ACCESS_POINT_OP.RU_ACCESS_POINT_OP_RE.getId())||paramValueOp.equals(ConstantesGenerales.RU_ACCESS_POINT_OP.RU_ACCESS_POINT_OP_CE.getId()))
						|| (paramValueOp.equals(ConstantesGenerales.RU_ACCESS_POINT_OP.RU_ACCESS_POINT_OP_RO.getId())||paramValueOp.equals(ConstantesGenerales.RU_ACCESS_POINT_OP.RU_ACCESS_POINT_OP_CO.getId())))
					return mapping.findForward(FORWARD_EMPRESA);
				
				if( paramValueOp !=null && (paramValueOp.equals(ConstantesGenerales.RU_ACCESS_POINT_OP.RU_ACCESS_POINT_OP_RC.getId())
						||paramValueOp.equals(ConstantesGenerales.RU_ACCESS_POINT_OP.RU_ACCESS_POINT_OP_CC.getId())))
				//		||paramValueOp.equals(ConstantesGenerales.RU_ACCESS_POINT_OP.RU_ACCESS_POINT_OP_CC.getId())||paramValueOp.equals(ConstantesGenerales.RU_ACCESS_POINT_OP.RU_ACCESS_POINT_OP_CCOM.getId())))
					return mapping.findForward(FORWARD_CANDIDATO);
				
				if( paramValueOp !=null && (paramValueOp.equals(ConstantesGenerales.RU_ACCESS_POINT_OP.RU_ACCESS_POINT_OP_PO.getId())))
					return mapping.findForward(FORWARD_OFERTA_PLANTILLA);
				
				//Si es candidato y no cuenta con el CV ESTA OPCION ES PARA QUE LO REDIRECCIONE A COMPLEMENTARIOS DE RU, UNA VEZ fINALIZADO CON EL LLENADO LO REDIRECCIONA al espacio de Candidato
		//		if (paramValueOp != null &&(paramValueOp.equals(ConstantesGenerales.RU_ACCESS_POINT_OP.RU_ACCESS_POINT_OP_RC.getId())
	//					||paramValueOp.equals(ConstantesGenerales.RU_ACCESS_POINT_OP.RU_ACCESS_POINT_OP_CCOM.getId())))
//						return mapping.findForward(FORWARD_CANDIDATO_COMPLEMENTARIO);
												
								
			}
//			response.sendRedirect(redirect);
		
		} catch (Exception ex) {
			ex.printStackTrace();
		
		}
		
		return null;
		
	}

}
