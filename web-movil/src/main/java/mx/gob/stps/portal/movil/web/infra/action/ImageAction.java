package mx.gob.stps.portal.movil.web.infra.action;

import static mx.gob.stps.portal.movil.web.infra.utils.ConstantesMovil.ID_EMPRESA;
import static mx.gob.stps.portal.movil.web.infra.utils.ConstantesMovil.RUTA_IMAGEN_POR_DEFECTO_CANDIDATO;
import static mx.gob.stps.portal.movil.web.infra.utils.ConstantesMovil.RUTA_IMAGEN_POR_DEFECTO_EMPRESA;
import static mx.gob.stps.portal.movil.web.infra.utils.ConstantesMovil.USERLOGGED;
import javax.persistence.PersistenceException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.candidate.vo.PerfilLaboralVo;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.movil.web.empresa.delegate.EmpresaEspacioDelegate;
import mx.gob.stps.portal.movil.web.empresa.delegate.EmpresaEspacioDelegateImpl;
import mx.gob.stps.portal.movil.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.movil.web.infra.utils.Utils;
import mx.gob.stps.portal.movil.web.seguridad.vo.UsuarioFirmadoVO;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import mx.gob.stps.portal.movil.web.infra.utils.PropertiesLoaderWeb;

public class ImageAction extends DispatchAction {

	private final static String PARAM_ID_CANDIDATO = "idCandidato";
	
	private final static String CONTEXT_WEB = PropertiesLoaderWeb.getInstance().getProperty("app.context.url");
	
	public ActionForward getLogotipoEmpresa(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		byte[] content = null;
		response.setContentType("image/jpeg");		
		
		String context = CONTEXT_WEB; //request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ request.getContextPath() +"/";
		HttpSession session = request.getSession();
		
		try{
			UsuarioFirmadoVO usuario = (UsuarioFirmadoVO)session.getAttribute(USERLOGGED);
			long idEmpresa = 0;
			
			if (usuario!=null)
				idEmpresa = usuario.getIdPropietario();
			
			if (idEmpresa > 0){
				EmpresaEspacioDelegate service = EmpresaEspacioDelegateImpl.getInstance();
				EmpresaVO empresaVo = service.findEmpresaById(idEmpresa);
				
				if (empresaVo==null)
					empresaVo = service.consultaEmpresaPorAutorizar(idEmpresa);
				
				if (empresaVo!=null)
					content = empresaVo.getLogotipo();				
			}
			
			if(null==content || content.length<15){
				//Default RUTA_IMAGEN_POR_DEFECTO_EMPRESA
				URL url = new URL(context + RUTA_IMAGEN_POR_DEFECTO_EMPRESA);
				try {
					content = getImageFromURL(url, 192, 81);
				} catch (Exception e) { e.getMessage(); }					
				
			}
		
			OutputStream out = response.getOutputStream();
			out.write(content);
			out.close();
			out.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BusinessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	public byte[] getImageFromURL(URL url, int ancho, int alto) throws MalformedURLException, IOException {
		InputStream is = null;
		ByteArrayOutputStream bais = new ByteArrayOutputStream();
		try {
			int n = 0;
			is = url.openStream ();
			byte[] byteChunk = new byte[4240];
			while ((n = is.read(byteChunk)) > 0 ) {     
				bais.write(byteChunk, 0, n);   
			}
		}catch (IOException e) { e.printStackTrace(); }
		finally { if (is != null) { is.close(); }}
		return bais.toByteArray();
	}

	public ActionForward getLogotipoEmpresaRequest(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		byte[] content = null;
		response.setContentType("image/jpeg");
		String context = CONTEXT_WEB; //request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ request.getContextPath() +"/";
		try{
			String strIdEmpresa = request.getParameter(ID_EMPRESA).toString();
			if(null!=strIdEmpresa && !strIdEmpresa.equalsIgnoreCase("")){
				long idEmpresa = Long.parseLong(strIdEmpresa);
				EmpresaEspacioDelegate service = EmpresaEspacioDelegateImpl.getInstance();
				EmpresaVO empresaVo = service.findEmpresaById(idEmpresa);
				content = empresaVo.getLogotipo();
				if(null==content || content.length<15){
					URL url = new URL(context + RUTA_IMAGEN_POR_DEFECTO_EMPRESA);
					try {
						content = getImageFromURL(url, 192, 81);
					} catch (Exception e) { e.getMessage(); }	
				}
			}else {
				URL url = new URL(context + RUTA_IMAGEN_POR_DEFECTO_EMPRESA);
				try {
					content = getImageFromURL(url, 192, 81);
				} catch (Exception e) { e.getMessage(); }		
			}
			OutputStream out = response.getOutputStream();
			out.write(content);
			out.close();
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BusinessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
		return null;		
	}

	public ActionForward fotoCandidato(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		long idCandidato = Utils.parseLong(request.getParameter(PARAM_ID_CANDIDATO));

		try{
			String sinImagenPath = CONTEXT_WEB + RUTA_IMAGEN_POR_DEFECTO_CANDIDATO; //request.getScheme() +"://"+ request.getServerName() +":"+ request.getServerPort() + request.getContextPath() +"/"+ RUTA_IMAGEN_POR_DEFECTO_CANDIDATO;
			URL url = new URL(sinImagenPath);
			
			byte[] content = null;
			
			if(idCandidato > 0){
				EmpresaEspacioDelegate service = EmpresaEspacioDelegateImpl.getInstance();
				PerfilLaboralVo perfilLaboralVo = service.consultaPerfilLaboral(idCandidato);	
				content = perfilLaboralVo.getFotografia();				
			}

			if(content==null) content = getImageFromURL(url, 192, 181);
			
			response.setContentType("image/jpeg");
			OutputStream out = response.getOutputStream();
			out.write(content);
			out.close();
			out.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
