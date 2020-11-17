/**
 * 
 */
package mx.gob.stps.portal.web.security.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaEmpleoJB;
import mx.gob.stps.portal.web.crm.delegate.CrmBusDelegate;
import mx.gob.stps.portal.web.crm.delegate.CrmBusDelegateImpl;
import mx.gob.stps.portal.web.crm.form.CreateArticuloForm;
import mx.gob.stps.portal.web.infra.utils.Constantes;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegate;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegateImpl;
import mx.gob.stps.portal.web.offer.wrapper.OfertaJB;

/**
 * @author carlos.jimenez
 *
 */
public class UrlFriendlyFilter implements Filter {
	
	private static final String ID_OFERTA_EMPLEO = Constantes.ID_OFERTA_EMPLEO;
	private static final String SEARCH_QUERY_PARAM = "searchQ";
	private static final String SEARCH_PLACE_PARAM = "searchPlace";
	private static final String METHOD="method";
	private CrmBusDelegate servicio = CrmBusDelegateImpl.getInstance();

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request =((HttpServletRequest) req);
		HttpServletResponse response =((HttpServletResponse) res);
      	PropertiesLoader properties = PropertiesLoader.getInstance();
		String uriBuscar = request.getRequestURI();
		String newUri = "https://"+properties.getProperty("context.url.friendly");
		//System.out.println("context.url.friendly = " + newUri);
		
		if(response.getStatus()== 300){
			newUri += "/error-404";
			response.sendRedirect(newUri);
		}else if(response.getStatus()== 403){
			newUri += "/error-403";
			response.sendRedirect(newUri);
		}else if(response.getStatus()== 404){
			newUri += "/error-404";
			response.sendRedirect(newUri);
		}else if(response.getStatus() == 500){
			newUri += "/error-500";
			response.sendRedirect(newUri);
		}else if(uriBuscar.equals("/detalleoferta.do")){
			newUri += "/";
			String metodo = (String) request.getParameter(METHOD);
			int idOferta = Utils.parseInt(request.getParameter(ID_OFERTA_EMPLEO));
			
			if(metodo!=null && metodo.equals("init")){
				if(idOferta > 1){
					OfertaJB empleo = getoffer(idOferta);
					if(empleo.getTituloOferta() == null ){
						empleo.setTituloOferta("titulo de oferta");
					}
					if(empleo.getEmpresaNombre() == null){
						empleo.setEmpresaNombre("empresa sin nombre");
					}
					String empleoTitulo = limpiarURL(empleo.getTituloOferta());
					String empleoEmpresa = limpiarURL(empleo.getEmpresaNombre());
					
					newUri += idOferta + "-oferta-de-empleo-de-" + empleoTitulo + "-" + empleoEmpresa;
				}
				if(newUri.length()>100){
					newUri = newUri.substring(0, 100);
				}
				response.sendRedirect(newUri);
			}else{
				chain.doFilter(request, response);
			}
		}else if(uriBuscar.equals("/ocupate.do")){
			
			newUri += "/";
			String busquedaQ = (String) request.getParameter(SEARCH_QUERY_PARAM);
			String metodo = (String) request.getParameter(METHOD);
			int idCiudad = Utils.parseInt(request.getParameter(SEARCH_PLACE_PARAM));
			int idOferta=0;
			String busqueda = null;
			String ciudad = estadoRepublica(idCiudad);
			if(metodo.equals("init")){
				if(busquedaQ==null){
					idOferta = Utils.parseInt(request.getParameter(ID_OFERTA_EMPLEO));
					OfertaJB empleo = getoffer(idOferta);
					busqueda = limpiarURL(empleo.getTituloOferta());
					ciudad = limpiarURL(empleo.getUbicacion());
					idCiudad = 1;
				}else{
					busqueda = limpiarURL(busquedaQ);
					ciudad = estadoRepublica(idCiudad);
				}
				
				if(busqueda.isEmpty()){
					newUri += idCiudad + "-busqueda-de-ofertas-de-empleo-en-" + ciudad;
				}else{
					newUri +=  idCiudad  + "-busqueda-de-ofertas-de-empleo-" + busqueda +  "-en-" + ciudad;
				}
				
				if(newUri.length()>100){
					newUri = newUri.substring(0, 100);
				}
				response.sendRedirect(newUri);
			}else{
				chain.doFilter(request, response);
			}
		}else if(uriBuscar.contains("-oferta-de-empleo")){
			newUri += "/";
			int idOferta = Utils.parseInt(uriBuscar.substring(uriBuscar.indexOf("/")+1, uriBuscar.indexOf("-")));
			if(idOferta > 1){
				OfertaJB empleo = getoffer(idOferta);
				if(empleo.getTituloOferta() == null ){
					empleo.setTituloOferta("sin titulo");
				}
				if(empleo.getEmpresaNombre() == null){
					empleo.setEmpresaNombre("sin nombre de empresa");
				}
				String empleoTitulo = limpiarURL(empleo.getTituloOferta());
				String empleoEmpresa = limpiarURL(empleo.getEmpresaNombre());
				newUri += idOferta + "-oferta-de-empleo-de-" + empleoTitulo + "-" + empleoEmpresa;
			}
			if(newUri.length()>100){
				newUri = newUri.substring(0, 100);
			}
			response.sendRedirect(newUri);
		}else if(uriBuscar.equals("/inicio.do")){
			newUri+="/";
			response.sendRedirect(newUri);
		}else if(uriBuscar.equals("/recupera_contrasena.do") || uriBuscar.equals("/sne/recupera_contrasena.do") || uriBuscar.equals("/SNE/recupera_contrasena.do")){

			String metodo = (String) request.getParameter(METHOD);
			if(metodo == null){
				newUri += "/sne/recuperar-contrasena";
				response.sendRedirect(newUri);
			}else{
				chain.doFilter(request, response);
			}
		}else if(uriBuscar.equals("/registro_candidato.do")){
			newUri+= "/registro-candidato";
			response.sendRedirect(newUri);
		}else if(uriBuscar.equals("/registro_empresa.do")){
			newUri += "/registro-empresa";
			response.sendRedirect(newUri);
		}else if(uriBuscar.equals("/articuloDeInteress.do")){
			newUri += "/articulos-de-interes-para-ti";
			response.sendRedirect(newUri);
		}else if(uriBuscar.equals("/busquedaEspecificaOfertas.do")){
			String metodo = (String) request.getParameter(METHOD);
			if(metodo.equals("encontrar")){
				newUri += "/herramientas/resultados-de-busqueda-especifica";
				response.sendRedirect(newUri);
			}else if(metodo.equals("buscar")){
				newUri += "/herramientas/busqueda-especifica";
				response.sendRedirect(newUri);
			}else{
				chain.doFilter(request, response);
			}
		}else if(uriBuscar.equals("/articulo.do")){
	        if(request.getParameter("id") == null ){
	        	chain.doFilter(request, response);
	        }
	        Long  id = Long.parseLong(request.getParameter("id"));
			 CreateArticuloForm createArticuloForm = new CreateArticuloForm();
			 
			 try {
				servicio.selectArticulo(id, createArticuloForm);
				newUri+= "/articulo/" + id + "/" + limpiarURL(createArticuloForm.getTitulo());
				 response.sendRedirect(newUri);
			} catch (Exception e) {
				e.printStackTrace();
			}
			chain.doFilter(request, response);
		}else if(uriBuscar.equals("/articulo/")){
			String idArticulo = uriBuscar.substring(uriBuscar.indexOf("/articulo/"), uriBuscar.indexOf("/"));
	        Long  id = Long.parseLong(idArticulo);
			 CreateArticuloForm createArticuloForm = new CreateArticuloForm();
			 
			 try {
				servicio.selectArticulo(id, createArticuloForm);
				newUri += "/articulo/" + id + "/" + limpiarURL(createArticuloForm.getTitulo());
				 response.sendRedirect(newUri);
			} catch (Exception e) {
				e.printStackTrace();
			}
			chain.doFilter(request, response);
		}else{
			chain.doFilter(request, response);
		}
 
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
	
	private OfertaJB getoffer(long offerID) {
		OfertaJB offer = new OfertaJB();

		try {
			OfferBusDelegate services = OfferBusDelegateImpl.getInstance();
			OfertaEmpleoJB jb = services.buscarOfertaEmpleo(offerID);

			if (jb!=null) {
				BeanUtils.copyProperties(offer, jb);
				offer.setEmpresaNombre(jb.getEmpresa().getNombreEmpresa());
			}

			if(offer!=null && offer.getMapaUbicacion()!=null){
				String strMapaUbicacion = offer.getMapaUbicacion();
				if(!strMapaUbicacion.startsWith("http://") 
						&& !strMapaUbicacion.startsWith("https://") 
						&& strMapaUbicacion.contains(",")){					
					String mapaFormateado = "http://maps.google.com/?ll=" +   strMapaUbicacion + "&z=15";
					offer.setMapaUbicacion(mapaFormateado);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return offer;
	}
	
	private String limpiarURL(String cadena){
		cadena = cadena.toLowerCase();
		cadena = cadena.trim();
		cadena = cadena.replaceAll(" +", "-");
		cadena = cadena.replace("á", "a");
		cadena = cadena.replace("é", "e");
		cadena = cadena.replace("í", "i");
		cadena = cadena.replace("ó", "o");
		cadena = cadena.replace("ú", "u");
		cadena = cadena.replace("ñ", "n");		
		cadena = cadena.replace("Á", "a");
		cadena = cadena.replace("É", "e");
		cadena = cadena.replace("Í", "i");
		cadena = cadena.replace("Ó", "o");
		cadena = cadena.replace("Ú", "u");
		cadena = cadena.replace("à", "a");
		cadena = cadena.replace("è", "e");
		cadena = cadena.replace("ì", "i");
		cadena = cadena.replace("ò", "o");
		cadena = cadena.replace("ù", "u");
		cadena = cadena.replace("À", "a");
		cadena = cadena.replace("È", "e");
		cadena = cadena.replace("Ì", "i");
		cadena = cadena.replace("Ò", "o");
		cadena = cadena.replace("Ù", "u");
		cadena = cadena.replace("â", "a");
		cadena = cadena.replace("ê", "e");
		cadena = cadena.replace("î", "i");
		cadena = cadena.replace("ô", "o");
		cadena = cadena.replace("û", "u");	
		cadena = cadena.replace("Â", "a");
		cadena = cadena.replace("Ê", "e");
		cadena = cadena.replace("Î", "i");
		cadena = cadena.replace("Ô", "o");
		cadena = cadena.replace("Û", "u");
		cadena = cadena.replace("ä", "a");
		cadena = cadena.replace("ë", "e");
		cadena = cadena.replace("ï", "i");
		cadena = cadena.replace("ö", "o");
		cadena = cadena.replace("ü", "u");	
		cadena = cadena.replace("Ä", "a");
		cadena = cadena.replace("Ë", "e");
		cadena = cadena.replace("Ï", "i");
		cadena = cadena.replace("Ö", "o");
		cadena = cadena.replace("Ü", "u");
		cadena = cadena.replace("`", "");
		cadena = cadena.replace("'", "");
		cadena = cadena.replace(".", "");
		cadena = cadena.replace(",", "");
		cadena = cadena.replace(";", "");
		cadena = cadena.replace(":", "");
		cadena = cadena.replace("*", "");
		cadena = cadena.replace("<", "");
		cadena = cadena.replace(">", "");
		cadena = cadena.replace("|", "");
		cadena = cadena.replace("+", "");
		cadena = cadena.replace("*", "");
		cadena = cadena.replace("Ñ", "n");	
		cadena = cadena.replace(" ", "-");
		cadena = cadena.replace("%", "");
		cadena = cadena.replace("_", "-");
		cadena = cadena.replace("$", "");
		cadena = cadena.replace("!", "");
		cadena = cadena.replace("(", "");
		cadena = cadena.replace(")", "");
		cadena = cadena.replace("\"","");
		cadena = cadena.replace("´", "");
		cadena = cadena.replace("¨", "");
		cadena = cadena.replace("{", "");
		cadena = cadena.replace("}", "");
		cadena = cadena.replace("[", "");
		cadena = cadena.replace("]", "");
		cadena = cadena.replace("#", "");
		cadena = cadena.replace("&", "");
		cadena = cadena.replace("/", "");
		cadena = cadena.replace("=", "");
		cadena = cadena.replace("?", "");
		cadena = cadena.replace("¿", "");
		cadena = cadena.replace("°", "");
		cadena = cadena.replace("!", "");
		cadena = cadena.replace("¡", "");
		cadena = cadena.replace("@", "");
		if(cadena.length()>20){
			cadena = cadena.replace("-de-", "-");
			cadena = cadena.replace("-para-", "-");
			cadena = cadena.replace("-ante-", "-");
			cadena = cadena.replace("-bajo-", "-");
			cadena = cadena.replace("-con-", "-");
			cadena = cadena.replace("-contra-", "-");
			cadena = cadena.replace("-a-", "-");
			cadena = cadena.replace("-desde-", "-");
			cadena = cadena.replace("-durante-", "-");
			cadena = cadena.replace("-entre-", "-");
			cadena = cadena.replace("-hacia-", "-");
			cadena = cadena.replace("-hasta-", "-");
			cadena = cadena.replace("-para-", "-");
			cadena = cadena.replace("-por-", "-");
			cadena = cadena.replace("-segun-", "-");
			cadena = cadena.replace("-sin-", "-");
			cadena = cadena.replace("-sobre-", "-");
			cadena = cadena.replace("-tras-", "-");
			cadena = cadena.replace("-el-", "-");
			cadena = cadena.replace("-la-", "-");
			cadena = cadena.replace("-los-", "-");
			cadena = cadena.replace("-las-", "-");
		}
		return cadena;
	}
	
	private String estadoRepublica(int idEstado){
		switch(idEstado){
		case 1: return "aguascalientes";
		case 2: return "baja-california";
        case 3: return "baja-california-sur";
        case 4: return "campeche";
        case 5: return "coahuila";
        case 6: return "colima";
        case 7: return "chiapas";
        case 8: return "chihuahua";
        case 9: return "ciudad-de-mexico";
        case 10: return "durango";
        case 11: return "guanajuato";
        case 12: return "guerrero";
        case 13: return "hidalgo";
        case 14: return "jalisco";
        case 15: return "estado-de-mexico";
        case 16: return "michoacan";
        case 17: return "morelos";
        case 18: return "nayarit";
        case 19: return "nuevo-leon";
        case 20: return "oaxaca";
        case 21: return "puebla";
        case 22: return "queretaro";
        case 23: return "quintana-roo";
        case 24: return "san-luis-potosi";
        case 25: return "sinaloa";
        case 26: return "sonora";
        case 27: return "tabasco";
        case 28: return "tamaulipas";
        case 29: return "tlaxcala";
        case 30: return "veracruz";
        case 31: return "yucatan";
        case 32: return "zacatecas";
		default: return "mexico";
		}
	}
}
