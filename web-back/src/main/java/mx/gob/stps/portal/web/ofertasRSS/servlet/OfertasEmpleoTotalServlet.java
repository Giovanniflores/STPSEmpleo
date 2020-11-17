package mx.gob.stps.portal.web.ofertasRSS.servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegate;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegateImpl;

import org.apache.log4j.Logger;

public class OfertasEmpleoTotalServlet extends HttpServlet {
	private static final long serialVersionUID = 3204563985812561836L;

	private static Logger logger = Logger.getLogger(OfertasEmpleoTotalServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {		
			
		try {
			OfferBusDelegate servicio = OfferBusDelegateImpl.getInstance();
			long total = servicio.consultaTotalOfertasPublicadas();

			if(total <= 0)
				total = PropertiesLoader.getInstance().getPropertyInt("ofertas.total.disponibles");

			total = (total / 1000) * 1000; // Se cambian las 3 ultimas cifras por 0's

			String totalOfertas = Utils.formatComa(total);

			response.setContentType("text/html");
			response.setCharacterEncoding("ISO-8859-1");

			OutputStream out = response.getOutputStream();
			out.write(totalOfertas.getBytes());
			out.close();
			out.flush();
		} catch (Exception e) {
			logger.error(e);
		}
	}
}
