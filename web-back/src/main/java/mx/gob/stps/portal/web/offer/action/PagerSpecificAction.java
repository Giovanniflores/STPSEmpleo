package mx.gob.stps.portal.web.offer.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.jobvacancies.delegate.BolsasTrabajoBusDelegate;
import mx.gob.stps.portal.web.jobvacancies.delegate.BolsasTrabajoBusDelegateImpl;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegate;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegateImpl;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

public abstract class PagerSpecificAction extends GenericAction {

	public abstract ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response);
	
	//protected int PAGE_NUM_ROW = PropertiesLoader.getInstance().getPropertyInt("app.pager.num.rows");
	protected int PAGE_NUM_ROW = 10;
	protected static int PAGE_JUMP_SIZE = 10;
	protected static final String NUM_PAGE_JUMP = "NUM_PAGE_JUMP";
	protected static final String NUM_PAGE_LIST = "NUM_PAGE_LIST";
	protected String FULL_LIST = "FULL_LIST";
	protected String PAGE_LIST = "PAGE_LIST";
	protected static final String TOTAL_PAGES = "TOTAL_PAGES";
	protected static final String NUM_RECORDS_VISIBLE = "NUM_RECORDS_VISIBLE";
	protected static final String NUM_RECORDS_TOTAL = "NUM_RECORDS_TOTAL";
	protected String ACTION_PAGE_TABLE = "ACTION_REGISTROS_TABLA";
	
	public final ActionForward page(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		int pagenum = 0;
		HttpSession session = request.getSession();
		Integer pagenumParam = (Integer)session.getAttribute(NUM_PAGE_LIST);
		if (pagenumParam!=null) pagenum = pagenumParam;
		if (pagenum<=0) pagenum = 1;
		session.setAttribute(NUM_PAGE_LIST, pagenum);
		session.setAttribute("PAGE_NUM_ROW", PAGE_NUM_ROW);
		return page(pagenum, mapping, session);
	}
	
	private final ActionForward page(int pagenum, ActionMapping mapping, HttpSession session){
		return page(pagenum, mapping, session, "");
	}
	
	@SuppressWarnings("rawtypes")
	private final ActionForward page(int pagenum, ActionMapping mapping, HttpSession session, String diferenciador){
		int visible = PAGE_NUM_ROW;
		Integer totalPages = new Integer(0);
		@SuppressWarnings("unchecked")
		List<Long> index = (List<Long>)session.getAttribute("FULL_LIST" + diferenciador);
		if (index==null) index = new ArrayList<Long>();
		List rowsPage = getRows(pagenum, index, session);                       
		//Se obtienen los registros a mostrar en la pagina
		totalPages = index.size()/PAGE_NUM_ROW;                       
		//Se obtiene el total de paginas del paginador
		if (index.size()%PAGE_NUM_ROW != 0)
			totalPages ++;                                            
		// si existe un registro mas al la division exacta se agrega otra pagina, por ejemplo 13/10 = 1 pagina, y en realidad son 2;
		session.setAttribute(TOTAL_PAGES + diferenciador, totalPages);                        //Numero total de paginas
		//se debe restar uno a page num ya que 10/10 = 1 y debe ser 0 debido a que aun se esta en el mismo grupo de paginas
		Integer saltoActual = (pagenum-1)/PAGE_JUMP_SIZE;            
		//grupo de paginas, el primer grupo es 0
		session.setAttribute(NUM_PAGE_JUMP + diferenciador, saltoActual);                     //se sube el salto a session
		session.setAttribute("PAGE_JUMP_SIZE" + diferenciador, PAGE_JUMP_SIZE);             //indica el tamaño de los grupos de paginas
		if (rowsPage.size() < PAGE_NUM_ROW)
			visible = rowsPage.size();
		if (rowsPage.size() > PAGE_NUM_ROW)
			rowsPage = rowsPage.subList(0, PAGE_NUM_ROW);
		session.setAttribute("NUM_RECORDS_VISIBLE" + diferenciador, visible);                //numero de registros mostrados en la pagina actual
		session.setAttribute("NUM_RECORDS_TOTAL" + diferenciador, index.size());            //numero total de registros de la tabla
		session.setAttribute(PAGE_LIST + diferenciador, rowsPage);   
		//Registros a mostrar en la pagina
		return mapping.findForward(ACTION_PAGE_TABLE /* + diferenciador*/);
	}

	@SuppressWarnings("rawtypes")
	private List getRows(int pagenum, List<Long> index, HttpSession session){	
		List rowsPage = countPage(pagenum, index, session);
		return rowsPage;
	}
	
	@SuppressWarnings("rawtypes")
    protected List countPage(int page, List<Long> index, HttpSession session) {
        BolsasTrabajoBusDelegate services = BolsasTrabajoBusDelegateImpl.getInstance();
        List rowsPage= new ArrayList();
        try {
            rowsPage = services.buscarOfertasEspecificas(page, index, "");
         	OfferBusDelegate offerServices = OfferBusDelegateImpl.getInstance();
        	UsuarioWebVO usuario = getUsuario(session);
            if (usuario!=null && usuario.getCandidato()){
            	try{
        			long idCandidato = usuario.getIdPropietario();
                    for (int i = 0; i<rowsPage.size(); i++){
                    	OfertaPorCanalVO oferta = (OfertaPorCanalVO)rowsPage.get(i);
                    	int compatibility = offerServices.match(oferta.getIdOfertaEmpleo(), idCandidato);
                    	oferta.setCompatibility(compatibility);
                    	if (PAGE_NUM_ROW == i) break; // Solo se consulta la compatibilidad para los registros a mostrarse
                    }            		
            	}catch(Exception e){
            		e.printStackTrace();
            	}
            }
        } catch (TechnicalException e) { rowsPage = new ArrayList();
        } catch (SQLException e) { rowsPage = new ArrayList();
        } catch (ServiceLocatorException e) { rowsPage = new ArrayList(); }
        return rowsPage;
    }
	
	public final ActionForward next(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		int pagenum = 0;
		HttpSession session = request.getSession();
		String diferenciador = ""; 													//codigo necesario para usar varias tablas
		Integer totalPages = (Integer)session.getAttribute(TOTAL_PAGES + diferenciador);
		Integer pagenumParam = (Integer)session.getAttribute(NUM_PAGE_LIST + diferenciador);
		if (pagenumParam!=null) pagenum = pagenumParam;
		if (pagenum <= 0) pagenum = 1;
		pagenum++;
		if (pagenum>totalPages) pagenum = totalPages;
		session.setAttribute(NUM_PAGE_LIST + diferenciador, pagenum);
		return page(pagenum, mapping, session, diferenciador);
	}
	
	public final ActionForward prev(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		int pagenum = 0;
		HttpSession session = request.getSession();
		String diferenciador = ""; 
		Integer pagenumParam = (Integer)session.getAttribute(NUM_PAGE_LIST + diferenciador);
		if (pagenumParam!=null) pagenum = pagenumParam;
		pagenum--;
		if (pagenum<=0) pagenum = 1;
		session.setAttribute(NUM_PAGE_LIST + diferenciador , pagenum);	
		return page(pagenum, mapping, session, diferenciador);
	}
	//Permite navegar a un numero de pagina determinado
	public final ActionForward goToPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){ 
		//Se obtiene la pagina a ir
		int pagenum = 0;
		String diferenciador = "";
		HttpSession session = request.getSession();
		if (request.getParameter("goToPageNumber")!=null)
			pagenum = Integer.parseInt(request.getParameter("goToPageNumber"));
		Integer totalPages = (Integer)session.getAttribute(TOTAL_PAGES + diferenciador);
		if (totalPages==null) totalPages = 0;
		if (pagenum<=0) pagenum = 1;
		//evita avanzar paginas si no hay mas
		if (pagenum>totalPages) pagenum = totalPages;
		session.setAttribute(NUM_PAGE_LIST + diferenciador , pagenum);
		return page(pagenum, mapping, session, diferenciador);
	}
}
