package mx.gob.stps.portal.web.oferta.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.stps.portal.core.ws.ofertas.service.OfertasSFPAppServiceLocal;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class IniciarProcesoSFP
 */
public class IniciarProcesoSFP extends HttpServlet {	
	
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(IniciarProcesoSFP.class);
   
	@EJB 
	private OfertasSFPAppServiceLocal ofertasSFPAppService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IniciarProcesoSFP() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		iniciaProceso();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		iniciaProceso();
	}

	private void iniciaProceso(){
		try{
			ofertasSFPAppService.iniciarProcesoSFP();
		} catch (Exception e){
			logger.error("Ha ocurrido un error en IniciarProcesoSFP.iniciaProceso");
			e.printStackTrace();			
		}
	} 

}
