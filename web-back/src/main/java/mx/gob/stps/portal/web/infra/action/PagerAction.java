package mx.gob.stps.portal.web.infra.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.utils.Utils;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public abstract class PagerAction extends GenericAction {

	public abstract ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response);
	
	protected int PAGE_NUM_ROW = PropertiesLoader.getInstance().getPropertyInt("app.pager.num.rows");
	protected int PAGE_JUMP_SIZE = 5;
	protected static final String NUM_PAGE_JUMP = "NUM_PAGE_JUMP";
	protected static final String NUM_PAGE_LIST = "NUM_PAGE_LIST";
	protected String FULL_LIST = "FULL_LIST";
	protected String CODIGOS = "CODIGOS";
	protected String FILTRO_GEO = "FILTRO_GEO";
	protected String PAGE_LIST = "PAGE_LIST";
	protected static final String TOTAL_PAGES = "TOTAL_PAGES";
	protected static final String NUM_RECORDS_VISIBLE = "NUM_RECORDS_VISIBLE";
	protected static final String NUM_RECORDS_TOTAL = "NUM_RECORDS_TOTAL";
	protected String ACTION_PAGE_TABLE = "ACTION_REGISTROS_TABLA";
	
	public final ActionForward page(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
	
		HttpSession session = request.getSession();
		
		int pagenum = 0;
		
		Integer pagenumParam = (Integer)session.getAttribute(NUM_PAGE_LIST);
		if (pagenumParam!=null) pagenum = pagenumParam;

		if (pagenum<=0) pagenum = 1;
		
		session.setAttribute(NUM_PAGE_LIST, pagenum);
		session.setAttribute("PAGE_NUM_ROW", PAGE_NUM_ROW);
		
		return page(pagenum, mapping, session);
	}

	public final ActionForward next(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		
		int pagenum = 0;
		//codigo necesario para usar varias tablas
		String diferenciador = ""; 
		if (request.getParameter("tablaPager")!=null){
			diferenciador = request.getParameter("tablaPager");
		} 
		
		Integer totalPages = (Integer)session.getAttribute(TOTAL_PAGES + diferenciador);
		Integer pagenumParam = (Integer)session.getAttribute(NUM_PAGE_LIST + diferenciador);
		if (pagenumParam!=null) pagenum = pagenumParam;
		if (pagenum <= 0) pagenum = 1;
		pagenum++;
		//System.out.println("Next: Total de paginas " + diferenciador + ": " + totalPages);
		//System.out.println("Next: Pagina solicitada " + diferenciador + ": #" + pagenum);
		
		//evita avanzar paginas si no hay mas
		if (pagenum>totalPages) pagenum = totalPages;
		
		//System.out.println("Next: Pagina maxima permitida " + diferenciador + ": " + pagenum);
		
		
		session.setAttribute(NUM_PAGE_LIST + diferenciador, pagenum);

		return page(pagenum, mapping, session, diferenciador);
	}
	
	public final ActionForward prev(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		
		int pagenum = 0;
		//codigo necesario para usar varias tablas
		String diferenciador = ""; 
		if (request.getParameter("tablaPager")!=null){
			diferenciador = request.getParameter("tablaPager");
		}
		
		Integer pagenumParam = (Integer)session.getAttribute(NUM_PAGE_LIST + diferenciador);
		if (pagenumParam!=null) pagenum = pagenumParam;

		pagenum--;
		if (pagenum<=0) pagenum = 1;
		
		session.setAttribute(NUM_PAGE_LIST + diferenciador , pagenum);
		
		return page(pagenum, mapping, session, diferenciador);
	}
	
	
	//Permite navegar a un numero de pagina determinado
	public final ActionForward goToPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		//System.out.println("prueba de request " + request.getParameter("tablaPager"));
		
		//codigo necesario para usar varias tablas
		String diferenciador = ""; 
		if (request.getParameter("tablaPager")!=null){
			diferenciador = request.getParameter("tablaPager");
		}
		//Se obtiene la pagina a ir
		int pagenum = 0; 
		if (request.getParameter("goToPageNumber")!=null){
			pagenum = Utils.parseInt(request.getParameter("goToPageNumber"));
		}
	
		Integer totalPages = (Integer)session.getAttribute(TOTAL_PAGES + diferenciador);
		if (totalPages==null) totalPages = 0;
		//System.out.println("Next: Pagina solicitada " + diferenciador + ": #" + pagenum);
		
		if (pagenum<=0) pagenum = 1;
		//evita avanzar paginas si no hay mas
		if (pagenum>totalPages) pagenum = totalPages;
		
		//System.out.println("Next: Pagina pagina a mostrar " + diferenciador + ": " + pagenum);
		
		session.setAttribute(NUM_PAGE_LIST + diferenciador , pagenum);
		return page(pagenum, mapping, session, diferenciador);
	}
	
	
	
	
	public final ActionForward page(int pagenum, ActionMapping mapping, HttpSession session){
		return this.page(pagenum, mapping, session, "");
	}
	
	//Sobrecarga del Page para poder manejar multiples tablas
	@SuppressWarnings("rawtypes")
	public final ActionForward page(int pagenum, ActionMapping mapping, HttpSession session, String diferenciador){
		List<?> rows = (List<?>)session.getAttribute(FULL_LIST + diferenciador);
		List<?> rowsCodigo = (List<?>)session.getAttribute(CODIGOS);
		String rowfiltro = (String)session.getAttribute(FILTRO_GEO);
		if (rows==null) rows = new ArrayList(); 
		try{
			//Paginacion
			//Se obtiene el total de paginas del paginador
			Integer totalPages = rows.size()/PAGE_NUM_ROW; 
			if (rows.size()%PAGE_NUM_ROW != 0){
				// si existe un registro mas al la division exacta se agrega otra pagina 
				// ejemplo 13/10 = 1 pagina, y en realidad son 2;
				totalPages ++; 
			}
			session.setAttribute(TOTAL_PAGES + diferenciador, totalPages);						//Numero total de paginas
			//se debe restar uno a page num ya que 10/10 = 1 y debe ser 0 debido a
			//que aun se esta en el mismo grupo de paginas 
			Integer saltoActual = (pagenum-1)/PAGE_JUMP_SIZE; 									//grupo de paginas, el primer grupo es 0
			session.setAttribute(NUM_PAGE_JUMP + diferenciador, saltoActual); 					//se sube el salto a session
			session.setAttribute("PAGE_JUMP_SIZE" + diferenciador, PAGE_JUMP_SIZE); 			//idica el tamaño de los grupos de paginas
			
			//Registros
			List rowsPage = getRows(pagenum, rows, session);    								//Se obtienen los registros a mostrar en la pagina
			session.setAttribute("NUM_RECORDS_VISIBLE" + diferenciador, rowsPage.size());		//numero de registros mostrados en la pagina actual
			session.setAttribute("NUM_RECORDS_TOTAL" + diferenciador, rows.size());				//numero total de registros de la tabla
			session.setAttribute(PAGE_LIST + diferenciador, rowsPage);							//Registros a mostrar en la pagina
			session.setAttribute(CODIGOS , rowsCodigo);
			session.setAttribute(FILTRO_GEO , rowfiltro);
			/*
			System.out.println("NUM_PAGE_JUMP: "+saltoActual);
			System.out.println("TOTAL_PAGES: "+totalPages);
			System.out.println("NUM_RECORDS_VISIBLE: "+rowsPage.size());
			System.out.println("NUM_RECORDS_TOTAL: "+rows.size());
			System.out.println("PAGE_JUMP_SIZE: "+PAGE_JUMP_SIZE);
			System.out.println("PAGE_LIST: "+rowsPage);
			*/			
		} catch(Exception e){
			e.printStackTrace();
		}		
		return mapping.findForward(ACTION_PAGE_TABLE + diferenciador);
	}
	
	
	@SuppressWarnings("rawtypes")
	protected List getRows(int pagenum, List<?> rows){
		
		List rowsPage = countPage(pagenum, rows);
		
		return rowsPage;
	}

	@SuppressWarnings("rawtypes")
    protected List getRows(int pagenum, List<?> rows, HttpSession session){
          return getRows(pagenum, rows);
    } 	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List countPage(int page, List<?> rows){

		if (rows==null || rows.isEmpty()) return new ArrayList();
		
		// TODO VERIFICAR SUBIR A MEMORIA PARA EVITAR PARTIR LA LISTA CADA PAGINACION
		Map<Integer, List<?>> pagesRows = new HashMap<Integer, List<?>>();
		
		int pageCount = 1;
		List pageRows = new ArrayList();
		pagesRows.put(pageCount, pageRows);
		
		for (int i=0; i<rows.size(); i++){

			pageRows.add(rows.get(i));
			
			if (i+1 < rows.size()){
				if (((i+1) % PAGE_NUM_ROW) == 0){
					pageCount++;
					pageRows = new ArrayList();
					pagesRows.put(pageCount, pageRows);
				}
			}
		}
		
		/*for (Map.Entry<Integer, List<?>> entry : pagesRows.entrySet()){
			System.out.println(entry.getKey() +" - "+ entry.getValue().size());
		}*/
		
		List rowsPage = pagesRows.get(page);
		
		if (rowsPage==null) rowsPage = new ArrayList();
		
		return rowsPage;
	}
	
}
