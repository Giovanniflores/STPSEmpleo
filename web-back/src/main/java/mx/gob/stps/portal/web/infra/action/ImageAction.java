package mx.gob.stps.portal.web.infra.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.ID_CANDIDATO;
import static mx.gob.stps.portal.web.infra.utils.Constantes.ID_EMPRESA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.RUTA_IMAGEN_POR_DEFECTO_CANDIDATO;
import static mx.gob.stps.portal.web.infra.utils.Constantes.RUTA_IMAGEN_POR_DEFECTO_EMPRESA;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.stps.portal.core.candidate.vo.PerfilLaboralVo;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.utils.Constantes.PERFIL;
import mx.gob.stps.portal.core.seguridad.vo.UsuarioVO;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegate;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegate;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegateImpl;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public final class ImageAction extends GenericAction {
	private static final Logger logger = Logger.getLogger(ImageAction.class);
	
	public String  convertURLToFile(String fileUrl) throws IOException{
		URL url = new URL(fileUrl);
		//logger.info("----convertURLToFile url:" + url.toString());		
		return url.getFile();		
	}
	
	public byte[] getImageFromURL(URL url, int ancho, int alto) throws MalformedURLException, IOException {
		InputStream is = null;
		ByteArrayOutputStream bais = new ByteArrayOutputStream();
		try {
			int n = 0;
			is = url.openStream();
			byte[] byteChunk = new byte[4240];
			if (is != null) {
				while ((n = is.read(byteChunk)) > 0) {
					bais.write(byteChunk, 0, n);
				}
			}
		} catch (IOException e) {
			//e.printStackTrace();
		}
		finally { if (is != null) { is.close(); }}
		return bais.toByteArray();
	}
	//TODO Eliminar
	public ActionForward getFotoCandidatoRequest(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		byte[] content = null;
		response.setContentType("image/jpeg");		
		try{
			String context = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ request.getContextPath() +"/";
			String strSinImagen = context + RUTA_IMAGEN_POR_DEFECTO_CANDIDATO;
			URL url = new URL(strSinImagen);
			String strIdCandidato = request.getParameter(ID_CANDIDATO).toString();
			if(null!=strIdCandidato && !strIdCandidato.equalsIgnoreCase("")){
				long idCandidato = Long.parseLong(strIdCandidato);
				CandidatoBusDelegate service = CandidatoBusDelegateImpl.getInstance();

				PerfilLaboralVo perfilLaboralVo = service.consultaPerfilLaboral(idCandidato);	
				
				if (perfilLaboralVo!=null)
					content = perfilLaboralVo.getFotografia();
				
				if(null==content) {
		            //content = getImageFromFile(convertURLToFile(strSinImagen));					
					content = getImageFromURL(url, 192, 181);
				}
			} else {
				//default
				//content = getImageFromFile(convertURLToFile(strSinImagen));
				content = getImageFromURL(url, 192, 181);
			}
			
			OutputStream out = response.getOutputStream();
			out.write(content);
			out.close();
			out.flush();
			
		} catch (IOException e) {
			//e.printStackTrace();
			
		} catch (ServiceLocatorException e) {
			//e.printStackTrace();
		}
		
		
		return null;		
	}	
	//TODO Eliminar
	public ActionForward getFotoCandidato(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			byte[] content = null;
			response.setContentType("image/jpeg");
		try{
			String context = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ request.getContextPath() +"/";			
			UsuarioWebVO usuario = getUsuario(request.getSession(false));
			long idCandidato = 0;
			
			if (usuario!=null) idCandidato = usuario.getIdPropietario();
			if(idCandidato != 0){
					CandidatoBusDelegate service = CandidatoBusDelegateImpl.getInstance();
					PerfilLaboralVo perfilLaboralVo = service.consultaPerfilLaboral(idCandidato);
					
					try{
						if (perfilLaboralVo!=null)
							content = perfilLaboralVo.getFotografia();
					}catch (NullPointerException e) {
						e.printStackTrace();
						content =  null;
					}				

					
					if(null==content || content.length<15){				
						URL url = new URL(context + RUTA_IMAGEN_POR_DEFECTO_CANDIDATO);
						try {
							content = getImageFromURL(url, 192, 81);
						} catch (Exception e) { 
							e.getMessage(); 
						}
					}
			} else {				
				URL url = new URL(context + RUTA_IMAGEN_POR_DEFECTO_CANDIDATO);
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
		} catch (ServiceLocatorException e) {		
			e.printStackTrace();
		} catch (Exception e) {		
			e.printStackTrace();
		}
		return null;		
	}	
		
	/** Imagen para la consola te Chat
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	//TODO Eliminar
	public ActionForward getFotoCandidatoChat(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			byte[] content = null;
			long idCandidato = 0;
			response.setContentType("image/jpeg");	
		
		try{
			String context = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ request.getContextPath() +"/";
			if(getIdCandidatoRequest(request)!=0){
				idCandidato = getIdCandidatoRequest(request);
			}
			
			if(idCandidato != 0){
					CandidatoBusDelegate service = CandidatoBusDelegateImpl.getInstance();
					PerfilLaboralVo perfilLaboralVo = service.consultaPerfilLaboral(idCandidato);
				
					try{
						if (perfilLaboralVo!=null)
							content = perfilLaboralVo.getFotografia();
					}catch (NullPointerException e) {
						e.printStackTrace();
						content =  null;
					}				
					if(null==content || content.length<15){				
						URL url = new URL(context + RUTA_IMAGEN_POR_DEFECTO_CANDIDATO);
						try {
							content = getImageFromURL(url, 192, 81);
						} catch (Exception e) { 
							e.getMessage(); 
						}
					}
			} else {				
				URL url = new URL(context + RUTA_IMAGEN_POR_DEFECTO_CANDIDATO);
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
		} catch (ServiceLocatorException e) {		
			e.printStackTrace();
		}
		return null;		
	}
	//TODO Eliminar	
	public ActionForward getLogotipoEmpresa(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		byte[] content = null;
		response.setContentType("image/jpeg");		
		String context = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ request.getContextPath() +"/";
		try{
			UsuarioWebVO webVo = getUsuario(request.getSession(false));
			
			long idEmpresa = 0;
			
			if (webVo!=null)
				idEmpresa = webVo.getIdPropietario();
			
			if (idEmpresa > 0){
				RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance();
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

	/** Para uno de Imagen en chat
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	//TODO Eliminar
	public ActionForward getLogotipoEmpresaChat(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		byte[] content = null;
		long idEmpresa = 0;
		response.setContentType("image/jpeg");		
		String context = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ request.getContextPath() +"/";
		try{
			if(getIdEmpresaRequest(request)!=0){
				idEmpresa = getIdEmpresaRequest(request);
			}			
			RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance();
			EmpresaVO empresaVo = service.findEmpresaById(idEmpresa);
			if(empresaVo != null){
				System.out.println("Imagen vacia");
				content = empresaVo.getLogotipo();
			} else {
				System.out.println("Imagen llena");
				content = null;
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
	//TODO Eliminar
	public ActionForward getLogotipoEmpresaRequest(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		byte[] content = null;
		response.setContentType("image/jpeg");
		String context = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ request.getContextPath() +"/";
		try{
			String strIdEmpresa = request.getParameter(ID_EMPRESA);
			if(null!=strIdEmpresa && !strIdEmpresa.equalsIgnoreCase("")){
				long idEmpresa = Utils.parseLong(strIdEmpresa);
				RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance();
				EmpresaVO empresaVo = service.findEmpresaById(idEmpresa);
				
				if (empresaVo!=null)
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
			
			if (content!=null)
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;		
	}

	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		return null;
	}
	
	//TODO Eliminar
	public ActionForward imagenUsuario(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		try{
			String context = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ request.getContextPath() +"/";
			URL url = new URL(context + RUTA_IMAGEN_POR_DEFECTO_CANDIDATO);
			byte[] content = null;
			
			String username = request.getParameter("usuario");
			
			if(username!=null && !username.isEmpty()){
				
				SecutityDelegate services = SecutityDelegateImpl.getInstance();
				UsuarioVO usuario = services.consultaUsuarioPorLogin(username);
				
				if (usuario!=null){

					long idPropietario = services.consultaPropietario(usuario.getIdUsuario());
					
					if (usuario.getIdPerfil() == PERFIL.CANDIDATO.getIdOpcion()){
						CandidatoBusDelegate service = CandidatoBusDelegateImpl.getInstance();
						PerfilLaboralVo perfilLaboral = service.consultaPerfilLaboral(idPropietario);

						if (perfilLaboral!=null)
							content = perfilLaboral.getFotografia();
						else
							url = new URL(context + RUTA_IMAGEN_POR_DEFECTO_CANDIDATO);

					} else if (usuario.getIdPerfil() == PERFIL.EMPRESA.getIdOpcion()){
						RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance();
						EmpresaVO empresa = service.findEmpresaById(idPropietario);

						
						if (empresa!=null)
							content = empresa.getLogotipo();
						else
							url = new URL(context + RUTA_IMAGEN_POR_DEFECTO_EMPRESA);
					}
				}
			}

			if (null==content || content.length<15) {
				content = getImageFromURL(url, 192, 81);
			}
			
			response.setContentType("image/jpeg");
			OutputStream out = response.getOutputStream();
			out.write(content);
			out.close();
			out.flush();
			
		} catch (Exception e) {		
			e.printStackTrace(); logger.error(e);
		}
		
		return null;		
	}
	
	/** Valor de IdCandidato por el Request
	 * @param request
	 * @return
	 */
	private long getIdCandidatoRequest(HttpServletRequest request){
		return Long.parseLong((String) request.getParameter("IdCandidato"));
	}
	
	/** Valor de IdEmpresa por el Request
	 * @param request
	 * @return
	 */
	private long getIdEmpresaRequest(HttpServletRequest request){
		return Long.parseLong((String) request.getParameter("IdEmpresa"));
	}
	
	
	public ActionForward getImagen(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		try{
			String context = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ request.getContextPath() +"/";
			URL url = new URL(context + RUTA_IMAGEN_POR_DEFECTO_CANDIDATO);
			byte[] content = null;
			SecutityDelegate services = SecutityDelegateImpl.getInstance();
			long idUsuario = 0;
			long idPropietario = 0;
			long idPerfil = 0;
			UsuarioWebVO webVo = getUsuario(request.getSession(false));

			if(webVo != null){
					idPropietario = webVo.getIdPropietario();
					idUsuario = webVo.getIdUsuario();
					idPerfil = webVo.getIdPerfil();
			
			}
			
			if(idUsuario != 0 && idPropietario != 0 && idPerfil != 0){
				//Consultamos la imagen en Hash:
				content = services.consultaImagen( Long.valueOf(idUsuario).intValue(), Long.valueOf(idPerfil).intValue(), Long.valueOf(idPropietario).intValue());
				
				if(content == null){
					//Seteamos la imagen estandar dependiendo del perfil
					if (idPerfil == PERFIL.CANDIDATO.getIdOpcion()){
						url = new URL(context + RUTA_IMAGEN_POR_DEFECTO_CANDIDATO);
					}else if (idPerfil == PERFIL.EMPRESA.getIdOpcion()){
						url = new URL(context + RUTA_IMAGEN_POR_DEFECTO_EMPRESA);
					}		
					
				}//Fin if content	
			}
			
			if (null==content || content.length<15) {
				content = getImageFromURL(url, 192, 81);
			}
			
			response.setContentType("image/jpeg");
			OutputStream out = response.getOutputStream();
			out.write(content);
			out.close();
			out.flush();
			
		} catch (Exception e) {		
			//e.printStackTrace(); logger.error(e);
		}
		
		
		return null;
	}
	
}