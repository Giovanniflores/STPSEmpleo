package mx.gob.stps.portal.web.offer.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.infra.utils.Constantes.OCUPATE_CAMPO_ORDEN;
import mx.gob.stps.portal.core.infra.utils.Constantes.OCUPATE_ORDEN_DIRECCION;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;
import mx.gob.stps.portal.core.search.ResultInfoBO;
import mx.gob.stps.portal.core.search.service.IndexerServiceLocator;
import mx.gob.stps.portal.core.search.vo.MatchVO;
import mx.gob.stps.portal.persistencia.vo.CatSubareaVO;
import mx.gob.stps.portal.persistencia.vo.OfertaFuncionVO;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegate;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.utils.UrlUtils;
import mx.gob.stps.portal.web.jobvacancies.delegate.BolsasTrabajoBusDelegate;
import mx.gob.stps.portal.web.jobvacancies.delegate.BolsasTrabajoBusDelegateImpl;
import mx.gob.stps.portal.web.reg_unico.SingleRegisterBusDelegate;
import mx.gob.stps.portal.web.reg_unico.impl.SingleRegisterBusDelegateImpl;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public abstract class PagerOffersAction extends GenericAction {

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
		int hashcode = 0;
		String pattern = "";
		HttpSession session = request.getSession();
		if (null != request.getParameter("searchQ")) {
			pattern = request.getParameter("searchQ");
			request.setAttribute("searchQ", UrlUtils.suprXSS(request.getParameter("searchQ")));
			hashcode = request.getParameter("searchQ").hashCode();
		}else if(null != session.getAttribute("searchQ")){
			pattern = (String) session.getAttribute("searchQ");
			request.setAttribute("searchQ", UrlUtils.suprXSS((String)session.getAttribute("searchQ")));
			hashcode = pattern.hashCode();
		}
		Integer pagenumParam = (Integer)session.getAttribute(NUM_PAGE_LIST + hashcode);
		if (pagenumParam!=null) pagenum = pagenumParam;
		if (pagenum<=0) pagenum = 1;
		session.setAttribute(NUM_PAGE_LIST + hashcode, pagenum);
		session.setAttribute("PAGE_NUM_ROW", PAGE_NUM_ROW);
		return page(pagenum, mapping, session, hashcode, pattern);
	}

	public final ActionForward paginacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		int pagenum = 0;
		int hashcode = 0;
		String pattern = "";
		HttpSession session = request.getSession();
		if (null != request.getParameter("searchQ")) {
			pattern = request.getParameter("searchQ");
			request.setAttribute("searchQ", UrlUtils.suprXSS(request.getParameter("searchQ")));
			hashcode = request.getParameter("searchQ").hashCode();
		}else if(null != session.getAttribute("searchQ")){
			pattern = (String) session.getAttribute("searchQ");
			request.setAttribute("searchQ", UrlUtils.suprXSS((String)session.getAttribute("searchQ")));
			hashcode = pattern.hashCode();
		}
		Integer pagenumParam = (Integer)session.getAttribute(NUM_PAGE_LIST + hashcode);
		if (pagenumParam!=null) pagenum = pagenumParam;
		if (pagenum<=0) pagenum = 1;
		session.setAttribute(NUM_PAGE_LIST + hashcode, pagenum);
		session.setAttribute("PAGE_NUM_ROW", PAGE_NUM_ROW);
		
		
		int visible = PAGE_NUM_ROW;
		Integer totalPages = new Integer(0);
		@SuppressWarnings("unchecked")
		List<ResultInfoBO> index = (List<ResultInfoBO>)session.getAttribute("FULL_LIST" + hashcode);
		if (index==null) index = new ArrayList<ResultInfoBO>();
		List rowsPage = (List)session.getAttribute("PAGE_LIST" + hashcode);
		totalPages = index.size()/PAGE_NUM_ROW;                       
		//Se obtiene el total de paginas del paginador
		if (index.size()%PAGE_NUM_ROW != 0)
			totalPages ++;                                            
		// si existe un registro mas al la division exacta se agrega otra pagina, por ejemplo 13/10 = 1 pagina, y en realidad son 2;
		session.setAttribute(TOTAL_PAGES + hashcode, totalPages);                        //Numero total de paginas
		//se debe restar uno a page num ya que 10/10 = 1 y debe ser 0 debido a que aun se esta en el mismo grupo de paginas
		Integer saltoActual = (pagenum-1)/PAGE_JUMP_SIZE;            
		//grupo de paginas, el primer grupo es 0
		session.setAttribute(NUM_PAGE_JUMP + hashcode, saltoActual);                     //se sube el salto a session
		session.setAttribute("PAGE_JUMP_SIZE" + hashcode, PAGE_JUMP_SIZE);
		if (rowsPage.size() < PAGE_NUM_ROW)
			visible = rowsPage.size();
		if (rowsPage.size() > PAGE_NUM_ROW)
			rowsPage = rowsPage.subList(0, PAGE_NUM_ROW);
		session.setAttribute("NUM_RECORDS_VISIBLE" + hashcode, visible);                //numero de registros mostrados en la pagina actual
		session.setAttribute("NUM_RECORDS_TOTAL" + hashcode, index.size());
		session.setAttribute(FULL_LIST, index);//numero total de registros de la tabla
		session.setAttribute(PAGE_LIST + hashcode, rowsPage);
		request.setAttribute("searchQ", pattern);
		//Registros a mostrar en la pagina
		return mapping.findForward(ACTION_PAGE_TABLE /* + diferenciador*/);
	}
	
	private final ActionForward page(int pagenum, ActionMapping mapping, HttpSession session, int hashcode, String pattern){
		return this.page(pagenum, mapping, session, ""  + hashcode, pattern);
	}
	
	public final ActionForward next(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		int pagenum = 0;
		int hashcode = 0;
		String pattern = "";
		HttpSession session = request.getSession();
		if (request.getParameter("searchQ")!=null) {
			pattern = request.getParameter("searchQ");
			hashcode = request.getParameter("searchQ").hashCode();
		}
		String diferenciador = "" + hashcode; 													//codigo necesario para usar varias tablas
		Integer totalPages = (Integer)session.getAttribute(TOTAL_PAGES + diferenciador);
		Integer pagenumParam = (Integer)session.getAttribute(NUM_PAGE_LIST + diferenciador);
		if (pagenumParam!=null) pagenum = pagenumParam;
		if (pagenum <= 0) pagenum = 1;
		pagenum++;
		if (pagenum>totalPages) pagenum = totalPages;
		session.setAttribute(NUM_PAGE_LIST + diferenciador, pagenum);
		return page(pagenum, mapping, session, diferenciador, pattern);
	}
	
	//Sobrecarga del Page para poder manejar multiples tablas
	@SuppressWarnings("rawtypes")
	private final ActionForward page(int pagenum, ActionMapping mapping, HttpSession session, String diferenciador, String pattern){
		int visible = PAGE_NUM_ROW;
		Integer totalPages = new Integer(0);
		@SuppressWarnings("unchecked")
		List<ResultInfoBO> index = (List<ResultInfoBO>)session.getAttribute("FULL_LIST" + diferenciador);
		if (index==null) index = new ArrayList<ResultInfoBO>();
		List rowsPage = getRows(pagenum, index, session, pattern);                       
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

	//FIXME OracleText
    @SuppressWarnings("rawtypes")
    private List getRows(int pagenum, List<ResultInfoBO> index, HttpSession session, String pattern){	
        return countPage(pagenum, index, session, pattern);
    }

    @SuppressWarnings({ "rawtypes" })
    private List countPage(int page, List<ResultInfoBO> results, HttpSession session, String pattern) {
    	UsuarioWebVO usuario = getUsuario(session);
        List<OfertaPorCanalVO> rowsPage = new ArrayList<OfertaPorCanalVO>();
        SingleRegisterBusDelegate srbd = SingleRegisterBusDelegateImpl.getInstance();
        //BolsasTrabajoBusDelegate services = BolsasTrabajoBusDelegateImpl.getInstance();
        try {
        	//rowsPage = services.buscarOfertasOcupate(page, results);
        	rowsPage = srbd.resultInfoList(page, results);
        	
        	CatalogoOpcionDelegate catalogoOpcionDelegate = CatalogoOpcionDelegateImpl.getInstance();
        	if (!rowsPage.isEmpty()) {
            	try {
        			List<OfertaPorCanalVO> ofertas = new ArrayList<OfertaPorCanalVO>();
        			List<Long> ids = new ArrayList<Long>();
        			for (int i = 0; i < rowsPage.size() && i < PAGE_NUM_ROW; i++) {
        				StringBuilder builder = new StringBuilder();
        				OfertaPorCanalVO offer = (OfertaPorCanalVO) rowsPage.get(i);
        				List<OfertaFuncionVO> funcionList = catalogoOpcionDelegate.getOfertaFuncionList(offer.getIdOfertaEmpleo());
        				offer.setFuncionList(funcionList);
						for (OfertaFuncionVO function : funcionList) {
							builder.append(" ").append(function.getFuncion());
						}
						offer.setFunciones(builder.toString().trim());
        				ofertas.add(offer);
						ids.add(ofertas.get(i).getIdOfertaEmpleo());
					}
        			if (usuario != null && usuario.getIdPropietario() > 0L) {
        				long idCandidato = usuario.getIdPropietario();
        				List<MatchVO> matchs = IndexerServiceLocator.getIndexerServiceRemote().matchDetailed(ids, idCandidato);
	        			for (int i = 0; i < matchs.size(); i++) {
							for (int j = 0; j < rowsPage.size(); j++) {
								if (matchs.get(i).getIdOfertaEmpleo() == rowsPage.get(j).getIdOfertaEmpleo()) {
									rowsPage.get(j).setCompatibility(matchs.get(i).getCompatibilidad());
								}
							}
						}  
        			}
            	} catch (Exception e) {
            		e.printStackTrace();
            	}
            }
        } catch (Exception e) {
        	rowsPage = new ArrayList<OfertaPorCanalVO>(); 
        }
        return rowsPage;
    }
	

	public final ActionForward prev(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		int pagenum = 0;
		int hashcode = 0;
		String pattern = "";
		HttpSession session = request.getSession();
		if (request.getParameter("searchQ")!=null) {
			pattern = request.getParameter("searchQ");
			hashcode = request.getParameter("searchQ").hashCode();
		}
		String diferenciador = "" + hashcode; 
		Integer pagenumParam = (Integer)session.getAttribute(NUM_PAGE_LIST + diferenciador);
		if (pagenumParam!=null) pagenum = pagenumParam;
		pagenum--;
		if (pagenum<=0) pagenum = 1;
		session.setAttribute(NUM_PAGE_LIST + diferenciador , pagenum);	
		return page(pagenum, mapping, session, diferenciador, pattern);
	}
	
	//Permite navegar a un numero de pagina determinado
	public final ActionForward goToPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		int hashcode = 0;
		String pattern = "";
		HttpSession session = request.getSession();
		if (request.getParameter("searchQ")!=null) {
			pattern = request.getParameter("searchQ");
			hashcode = request.getParameter("searchQ").hashCode();
		}
		String diferenciador = "" + hashcode; 
		//Se obtiene la pagina a ir
		int pagenum = 0; 
		if (request.getParameter("goToPageNumber")!=null){
			pagenum = Integer.parseInt(request.getParameter("goToPageNumber"));
		}
		Integer totalPages = (Integer)session.getAttribute(TOTAL_PAGES + diferenciador);
		if (totalPages==null) totalPages = 0;
		if (pagenum<=0) pagenum = 1;
		//evita avanzar paginas si no hay mas
		if (pagenum>totalPages) pagenum = totalPages;
		session.setAttribute(NUM_PAGE_LIST + diferenciador , pagenum);
		return page(pagenum, mapping, session, diferenciador, pattern);
	}
	
}