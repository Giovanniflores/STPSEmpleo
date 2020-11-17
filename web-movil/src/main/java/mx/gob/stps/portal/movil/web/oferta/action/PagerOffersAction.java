package mx.gob.stps.portal.movil.web.oferta.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.search.ResultInfoBO;
import mx.gob.stps.portal.movil.web.infra.action.GenericAction;
import mx.gob.stps.portal.movil.web.oferta.delegate.OfertaDelegateImpl;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public abstract class PagerOffersAction extends GenericAction {
	private static final Logger logger = Logger.getLogger(PagerOffersAction.class);

	public abstract ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response);

	protected int PAGE_NUM_ROW = 10; //PropertiesLoader.getInstance().getPropertyInt("app.pager.num.rows");
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
	
	public final ActionForward next(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		int pagenum = 0;
		HttpSession session = request.getSession();

		Integer totalPages = (Integer)session.getAttribute(TOTAL_PAGES);
		if (totalPages==null) totalPages = 0;
		
		Integer pagenumParam = (Integer)session.getAttribute(NUM_PAGE_LIST);
		if (pagenumParam!=null) pagenum = pagenumParam;
		if (pagenum <= 0) pagenum = 1;
		pagenum++;
		if (pagenum>totalPages) pagenum = totalPages;
		session.setAttribute(NUM_PAGE_LIST, pagenum);

		return page(pagenum, mapping, session);
	}
	
	//Sobrecarga del Page para poder manejar multiples tablas
	@SuppressWarnings("rawtypes")
	private final ActionForward page(int pagenum, ActionMapping mapping, HttpSession session){
		int visible = PAGE_NUM_ROW;
		Integer totalPages = new Integer(0);
		@SuppressWarnings("unchecked")
		List<ResultInfoBO> index = (List<ResultInfoBO>)session.getAttribute(FULL_LIST);
		if (index==null) index = new ArrayList<ResultInfoBO>();
		List rowsPage = getRows(pagenum, index, session); 
		logger.info("Lista Ofertas Ocupate Movil "+rowsPage.size());
		//Se obtienen los registros a mostrar en la pagina
		totalPages = index.size()/PAGE_NUM_ROW;                       
		//Se obtiene el total de paginas del paginador
		if (index.size()%PAGE_NUM_ROW != 0)
			totalPages ++;                                            
		// si existe un registro mas al la division exacta se agrega otra pagina, por ejemplo 13/10 = 1 pagina, y en realidad son 2;
		session.setAttribute(TOTAL_PAGES, totalPages);                        //Numero total de paginas
		//se debe restar uno a page num ya que 10/10 = 1 y debe ser 0 debido a que aun se esta en el mismo grupo de paginas
		Integer saltoActual = (pagenum-1)/PAGE_JUMP_SIZE;           	    //grupo de paginas, el primer grupo es 0
		session.setAttribute(NUM_PAGE_JUMP, saltoActual);                   //se sube el salto a session
		session.setAttribute("PAGE_JUMP_SIZE", PAGE_JUMP_SIZE);             //indica el tamaño de los grupos de paginas
		
		if (rowsPage.size() < PAGE_NUM_ROW) visible = rowsPage.size();
		
		if (rowsPage.size() > PAGE_NUM_ROW) rowsPage = rowsPage.subList(0, PAGE_NUM_ROW);
		logger.info("Lista Ofertas Ocupate Movil "+rowsPage.size());
		session.setAttribute("NUM_RECORDS_VISIBLE", visible);                //numero de registros mostrados en la pagina actual
		session.setAttribute("NUM_RECORDS_TOTAL", index.size()); 
		
		session.setAttribute(PAGE_LIST, rowsPage);   
		//Registros a mostrar en la pagina
		
		return mapping.findForward(ACTION_PAGE_TABLE);
	}

	@SuppressWarnings("rawtypes")
	protected List getRows(int pagenum, List<ResultInfoBO> index, HttpSession session){	
		List rowsPage = countPage(pagenum, index, session);
		return rowsPage;
	}
	
	@SuppressWarnings("rawtypes")
    private List countPage(int page, List<ResultInfoBO> index, HttpSession session) {
		OfertaDelegateImpl services = OfertaDelegateImpl.getInstance();
        List rowsPage= new ArrayList();
        try {
            rowsPage = services.buscarOfertasOcupate(page, index);

            /*UsuarioFirmadoVO usuario = getUsuarioFirmado(session);
            
            if (usuario!=null && usuario.getIdPerfil() == PERFIL.CANDIDATO.getIdOpcion()){
            	try{
        			long idCandidato = usuario.getIdPropietario();

                    for (int i = 0; i<rowsPage.size(); i++){
                    	OfertaPorCanalVO oferta = (OfertaPorCanalVO)rowsPage.get(i);

                    	OfferBusDelegate offerServices = OfferBusDelegateImpl.getInstance();
                    	int compatibility = offerServices.match(oferta.getIdOfertaEmpleo(), idCandidato);
                    	oferta.setCompatibility(compatibility);

                    	if (PAGE_NUM_ROW == i) break; // Solo se consulta la compatibilidad para los registros a mostrarse
                    }
            	}catch(Exception e){
            		e.printStackTrace();
            	}
            }*/
        } catch (Exception e) { logger.error(e);}

        return rowsPage;
    }
	
	/*protected List countPage(int page, List<Long> rows){
		System.out.println("SUPER.countPage");
		if (rows==null || rows.isEmpty()) return new ArrayList<Long>();
		
		// TODO VERIFICAR SUBIR A MEMORIA PARA EVITAR PARTIR LA LISTA CADA PAGINACION
		Map<Integer, List<Long>> pagesRows = new HashMap<Integer, List<Long>>();
		
		int pageCount = 1;
		List<Long> pageRows = new ArrayList<Long>();
		pagesRows.put(pageCount, pageRows);
		
		for (int i=0; i<rows.size(); i++){

			pageRows.add(rows.get(i));
			
			if (i+1 < rows.size()){
				if (((i+1) % PAGE_NUM_ROW) == 0){
					pageCount++;
					pageRows = new ArrayList<Long>();
					pagesRows.put(pageCount, pageRows);
				}
			}
		}
		
		/ *for (Map.Entry<Integer, List<?>> entry : pagesRows.entrySet()){
			System.out.println(entry.getKey() +" - "+ entry.getValue().size());
		}* /
		
		List<Long> rowsPage = pagesRows.get(page);
		
		if (rowsPage==null) rowsPage = new ArrayList<Long>();
		
		return rowsPage;
	}*/
	
	public final ActionForward prev(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){

		HttpSession session = request.getSession();
		int pagenum = 0;

		Integer pagenumParam = (Integer)session.getAttribute(NUM_PAGE_LIST);
		if (pagenumParam!=null) pagenum = pagenumParam;
		pagenum--;
		if (pagenum<=0) pagenum = 1;
		session.setAttribute(NUM_PAGE_LIST, pagenum);	
		
		return page(pagenum, mapping, session);
	}
	
	//Permite navegar a un numero de pagina determinado
	public final ActionForward goToPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();

		//Se obtiene la pagina a ir
		int pagenum = 0; 
		if (request.getParameter("goToPageNumber")!=null){
			pagenum = Integer.parseInt(request.getParameter("goToPageNumber"));
		}
	
		Integer totalPages = (Integer)session.getAttribute(TOTAL_PAGES);
		if (totalPages==null) totalPages = 0;

		if (pagenum<=0) pagenum = 1;
		//evita avanzar paginas si no hay mas
		if (pagenum>totalPages) pagenum = totalPages;
		session.setAttribute(NUM_PAGE_LIST, pagenum);
		
		return page(pagenum, mapping, session);
	}
}