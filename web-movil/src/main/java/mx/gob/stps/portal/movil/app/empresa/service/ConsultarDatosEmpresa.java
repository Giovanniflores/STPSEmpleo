package mx.gob.stps.portal.movil.app.empresa.service;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.utils.Constantes.PERFIL;
import mx.gob.stps.portal.movil.app.empresa.model.rest.EmpresaRestDTO;
import mx.gob.stps.portal.movil.app.glasses.cookies.CookieSession;

import mx.gob.stps.portal.movil.web.empresa.delegate.EmpresaEspacioDelegate;
import mx.gob.stps.portal.movil.web.empresa.delegate.EmpresaEspacioDelegateImpl;
import mx.gob.stps.portal.seguridad.vo.MovilSessionVO;

@Stateless
@Path("/consultarMisDatos")
public class ConsultarDatosEmpresa {
	
	private Gson gson = new Gson();
	private static Logger logger = Logger.getLogger(ConsultarDatosEmpresa.class);
	private EmpresaRestDTO empresa = new EmpresaRestDTO();
	


	@GET
	// The Java method will produce content identified by the MIME Media
	// type "text/plain"
	@Produces(MediaType.APPLICATION_JSON)
	public String consultarDatos(@Context HttpServletRequest request, @Context HttpServletResponse response) {

		// then you can access the request/response/session etc in your methods
		Long idEmpresa = 0L;
		empresa.setResult("OK");
		String cookie = request.getHeader(CookieSession.NAME);

		if (cookie == null) {
			empresa.setResult("No se encuentra el Token.");
			return gson.toJson(empresa);
		}
		 
		EmpresaVO empresaVO = new EmpresaVO();
		Map<String, String> res = new HashMap<String, String>();

		try {
			// validar el cookie para obtener el candidato
			MovilSessionVO movilSession = CookieSession.decodeValue(cookie);
			idEmpresa = movilSession.getIdEmpresa();

			EmpresaEspacioDelegate service = EmpresaEspacioDelegateImpl.getInstance();
			empresaVO = service.findEmpresaById(idEmpresa);
			
			res = service.obtenerActividadEconomica(empresaVO.getIdActividadEconomica());
			empresaVO.setSector(res.get("idSector"));
			empresaVO.setSubSector(res.get("idSubsector"));
			empresaVO.setRama(res.get("idRama"));

			empresaVO.setLogotipo(null);
	
		} catch (Exception se) {
			se.printStackTrace();
			logger.error(se);
			empresa.setResult(se.getMessage());
		}

		empresa.setEmpresa(empresaVO);
		return gson.toJson(empresa);
	}
}
