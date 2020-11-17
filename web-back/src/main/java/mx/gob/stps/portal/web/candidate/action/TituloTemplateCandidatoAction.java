package mx.gob.stps.portal.web.candidate.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.USUARIO_APP;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.infra.utils.Constantes.ENTIDADES_FEDERATIVAS;
import mx.gob.stps.portal.core.infra.utils.Constantes.GENERO;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.vo.CandidatoAjaxVO;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

// TODO CLASE A ELIMIANR, SE COLOCA EN SESION AL CANDIDATO AL FIRMARSE AL SITIO DESDE LoginAction
public final class TituloTemplateCandidatoAction extends Action {

	private static Logger logger = Logger.getLogger(TituloTemplateCandidatoAction.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		try {
			UsuarioWebVO usuario = (UsuarioWebVO)session.getAttribute(USUARIO_APP);

			CandidatoVo candidato =  new CandidatoVo();
			String json = "";
			
			if (usuario!=null){
				CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
				candidato = services.buscarDatosHeaderTemplateCandidato(usuario.getIdPropietario());
				
				if (candidato!=null){
					
					Date fechaNacimiento = candidato.getFechaNacimiento();
					
					int edad = candidato.getEdad();
					if (fechaNacimiento!=null){
						edad = Utils.calculaEdad(fechaNacimiento);	
					}
					
					String nombre = candidato.getNombre() +" "+ candidato.getApellido1() +" "+ candidato.getApellido2();
					Calendar fechaAlta = candidato.getFechaAlta();
					String fecAlta = Utils.formatDDMMYYYY(fechaAlta.getTime());
					
					ENTIDADES_FEDERATIVAS entidad = ENTIDADES_FEDERATIVAS.getEntidad(candidato.getIdEntidadNacimiento());
					String entidadNac = "";
					if (entidad!=null) entidadNac = entidad.getDescripcion();

					String sexo = "";

					if (GENERO.MASCULINO.getIdOpcion() == candidato.getGenero()){
						sexo = "Hombre";
					} else if (GENERO.FEMENINO.getIdOpcion() == candidato.getGenero()){
						sexo = "Mujer";
					}
					
					CandidatoAjaxVO datos = new CandidatoAjaxVO();
					datos.setNombre(nombre);
					datos.setCurp(candidato.getCurp());
					datos.setSexo(sexo);
					datos.setEdad(""+ edad);
					datos.setEntidad(entidadNac);
					datos.setFechaalta(fecAlta);

					json = Utils.toJson(datos);
				}
			}			

			redirectJsonResponse(response, json);

		} catch (Exception e) {
			e.printStackTrace(); logger.error(e);
		}

		return null;
	}
	
	private void redirectJsonResponse(HttpServletResponse response, String json) throws IOException {
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.flush();
		pw.close();
		response.flushBuffer();
	}

}