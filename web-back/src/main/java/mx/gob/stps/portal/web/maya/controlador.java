package mx.gob.stps.portal.web.maya;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class controlador
 */
@WebServlet("/controlador")
public class controlador extends HttpServlet {
	Vacantes v = new Vacantes();
	VacantesTrenMayaDAO dao = new VacantesTrenMayaDAO();
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public controlador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String accion = request.getParameter("accion");
		switch(accion) {
		case "Listar":
		List<Vacantes>datos=dao.listar();
		request.setAttribute("datos",datos);
		request.getRequestDispatcher("trenmayaconsulta.jsp").forward(request, response);
		break;
		default:
			throw new AssertionError();
		}
	}

}
