package mx.gob.stps.portal.web.ofertasRSS.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.utils.Constantes.ENTIDADES_FEDERATIVAS_RSS;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaEmpleoJB;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegate;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegateImpl;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class OfertasRSSConsultar
 */
public class OfertasRSSConsultar extends HttpServlet {
	private static final long serialVersionUID = -6712850415955319243L;
	private static Logger logger = Logger.getLogger(OfertasRSSConsultar.class);
    
    public OfertasRSSConsultar() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {		
			
		String estado = request.getRequestURI();
		
		int ini = estado.indexOf("rss");
		estado = estado.substring(ini + 4);
		
		Charset charset = Charset.forName("ISO-8859-1");
		
		int idEntidad = 0;
		if (estado.matches("([a-zA-Z])+([_])*([a-zA-Z])*")== true)
			idEntidad = ENTIDADES_FEDERATIVAS_RSS.getIdOpcion(estado);

		try {
			if(idEntidad > 0){
				OfferBusDelegate servicio = OfferBusDelegateImpl.getInstance();
				List<OfertaEmpleoVO> ofertas = servicio.consultaOfertasEntidad(idEntidad);

				request.setAttribute("RSS_ENTIDAD", ofertas);

				String xmlRss = createXML(idEntidad, ofertas);

				response.setContentType("application/rss+xml");
				OutputStream out = response.getOutputStream();
				out.write(xmlRss.getBytes(charset));
				out.close();
				out.flush();
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("/ofertasRSSConsultar.do?method=init");
				dispatcher.forward(request, response);
			}
				
		} catch (ServiceLocatorException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
	}
	
	@SuppressWarnings("deprecation")
	private String createXML(int idEntidad, List<OfertaEmpleoVO> ofertas) throws SQLException {
		StringBuilder content = new StringBuilder();

		PropertiesLoader properties = PropertiesLoader.getInstance();
		
		ENTIDADES_FEDERATIVAS_RSS entidad = ENTIDADES_FEDERATIVAS_RSS.getEntidad(idEntidad);
		
		String entidadNombre = null;
		try {
			entidadNombre = URLEncoder.encode(entidad.getNombre().toLowerCase(), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			entidadNombre = URLEncoder.encode(entidad.getNombre().toLowerCase());
		}

		String titulo = properties.getProperty("rss.titulo");
		String description = properties.getProperty("rss.description");
		String author = properties.getProperty("rss.author");
		String urlPortal = properties.getProperty("app.swb.redirect.home");
		String urlOcupate = properties.getProperty("rss.url.ocupate")+ idEntidad + "-busqueda-de-ofertas-de-empleo-en-" + limpiarURL(entidadNombre);
		String urlDetalle = properties.getProperty("rss.url.oferta.detalle");
		String urlHeader = properties.getProperty("rss.url.img.cabecera");
		String urlItem = properties.getProperty("rss.url.img.item");
		
		Date fechaActual = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss");
		String fecha = sdf.format(fechaActual);
		
    	content.append("<?xml version=\"1.0\" encoding=\"ISO-8859-2\" ?>");
    	content.append("<rss version=\"2.0\"> ");
    	content.append("  <channel> ");
    	content.append("  <title>"+ titulo +" " + entidad.getNombre() + "</title> ");
    	content.append("  <link>" + urlOcupate + "</link> ");
    	content.append("  <description>"+ description +"</description> ");
    	content.append("  <language>es-mx</language> ");
    	content.append("  <image>");
    	content.append("    <title>"+ author +"</title>");
    	content.append("    <width>384</width> ");
    	content.append("    <height>68</height> ");
    	content.append("    <link>"+ urlPortal +"</link> ");
    	content.append("    <url>"+ urlHeader +"</url> ");
    	content.append("  </image>");
    	OfferBusDelegate services = OfferBusDelegateImpl.getInstance();
		for (OfertaEmpleoVO oferta : ofertas) {
			try {
				OfertaEmpleoJB jb = services.buscarOfertaEmpleo(oferta.getIdOfertaEmpleo());
				if(jb.getEmpresa().getNombreEmpresa()==null){
					oferta.setNombreEmpresa("sin nombre de empresa");
				}else{
					oferta.setNombreEmpresa(jb.getEmpresa().getNombreEmpresa());
				}
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServiceLocatorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			content.append("  <item>");
			content.append("    <title>"+ oferta.getTituloOferta() +"</title> ");
			content.append("    <link>"+ urlDetalle + oferta.getIdOfertaEmpleo() + "-oferta-de-empleo-de-" + limpiarURL(oferta.getTituloOferta()) + "-" + limpiarURL(oferta.getNombreEmpresa()) +"</link> ");
			content.append("    <author>"+ author +"</author> ");
			content.append("    <pubDate>" + fecha + " </pubDate> ");
			content.append("    <description> ");
			content.append("      <![CDATA[ ");
			content.append("      <div>");
			content.append("        <table width=\"100%\">");
			content.append("          <tr>");
			content.append("            <td align=\"left\">");
			content.append("              <img width=\"363\" height=\"59\" src=\""+ urlItem +"\"></br>");
			content.append("            </td>");
			content.append("          </tr>");
			content.append("          <tr>");
			content.append("            <td valign=\"bottom\" align=\"justify\">\n");
			content.append((oferta.getMunicipio()!=null?oferta.getMunicipio().trim():"") + ".- " + 
					       oferta.getFunciones() + "; " + 
					       oferta.getTipoEmpleo() + "; " + 
					       oferta.getEdadMinima() + " - " + 
					       oferta.getEdadMaxima() + " A&#209;OS; SALARIO MENSUAL OFRECIDO: $" + 
					       oferta.getSalario() + "...");
			content.append("            </td></tr></table></div> ]]>");
			content.append("    </description> ");
			content.append("  </item>");
        }
		
    	content.append(" </channel>");
    	content.append("</rss> ");

		return content.toString();
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
		cadena = cadena.replace("+", "-");
		cadena = cadena.replace("*", "");
		cadena = cadena.replace("Ñ", "n");	
		cadena = cadena.replace(" ", "-");
		cadena = cadena.replace("%", "");
		cadena = cadena.replace("_", "-");
		cadena = cadena.replace("$", "");
		cadena = cadena.replace("!", "");
		cadena = cadena.replace("(", "");
		cadena = cadena.replace(")", "");
		cadena = cadena.replace("\"", "-");
		cadena = cadena.replace("´", "");
		cadena = cadena.replace("¨", "");
		cadena = cadena.replace("{", "");
		cadena = cadena.replace("}", "");
		cadena = cadena.replace("[", "");
		cadena = cadena.replace("]", "");
		cadena = cadena.replace("#", "");
		cadena = cadena.replace("&", "-");
		cadena = cadena.replace("/", "-");
		cadena = cadena.replace("=", "-");
		cadena = cadena.replace("?", "-");
		cadena = cadena.replace("¿", "-");
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
	

}
