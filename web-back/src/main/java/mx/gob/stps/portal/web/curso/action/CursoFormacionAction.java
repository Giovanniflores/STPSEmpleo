package mx.gob.stps.portal.web.curso.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_CURSO;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import mx.gob.stps.portal.core.curso.vo.CursoVO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.web.curso.delegate.CursoDelegate;
import mx.gob.stps.portal.web.curso.delegate.CursoDelegateImpl;
import mx.gob.stps.portal.web.curso.form.CursoFormacionForm;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegate;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CursoFormacionAction extends GenericAction{

	private int REGISTROS_POR_PAGINA = 20;

	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		CursoFormacionForm cursoFormacionForm = (CursoFormacionForm) form;

		int paginaActual = 1;
		int fromIndex = (paginaActual-1)*REGISTROS_POR_PAGINA;
		long filtroOpciones [] = {33};
		try {
			CursoDelegate cursoService = CursoDelegateImpl.getInstance();
			CatalogoOpcionDelegate catalogoService = CatalogoOpcionDelegateImpl.getInstance();

			List<CursoVO> cursos = cursoService.getCursos();
			List<CatalogoOpcionVO> entidades = catalogoService.consultarCatalogo(CATALOGO_OPCION_ENTIDAD_FEDERATIVA, filtroOpciones);

			generaPaginacion(cursos.size(),request, cursoFormacionForm);
			request.getSession().setAttribute("LISTA_CURSOS", cursos);
			request.getSession().setAttribute("LISTA_ENTIDADES", entidades);
			request.getSession().setAttribute("TOTAL_CURSOS", cursos.size());
			int toIndex = getRangoFinal(fromIndex,cursos.size(),REGISTROS_POR_PAGINA, request, paginaActual);

			cursoFormacionForm.setListaCursos(cursos.subList(fromIndex, toIndex));
			cursoFormacionForm.setListaEntidades(entidades);
			cursoFormacionForm.setCursoDesde(fromIndex+1);
			cursoFormacionForm.setCursoHasta(toIndex);
		} catch (ServiceLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getSession().setAttribute(BODY_JSP, mapping.getInputForward().getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.setAttribute("TITULO", "Cursos de formación para el trabajo de la SEP");
		request.getSession().setAttribute(TITULO_PAGINA, "Cursos de formación para el trabajo de la SEP.");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Reg&iacute;strate en nuestra Bolsa de trabajo y postula la oferta de empleo que m&aacute;s se apegue seg&uacute;n tu curriculum vitae sin costo f&aacute;cil y sin intermediarios.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));	
		return mapping.findForward(FORWARD_TEMPLATE_CURSO);
		
	}

	public ActionForward busquedaCurso(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {

		CursoFormacionForm cursoFormacionForm = (CursoFormacionForm) form;

		int paginaActual = 1;
		int fromIndex = (paginaActual-1)*REGISTROS_POR_PAGINA;
		try {
			CursoDelegate cursoService = CursoDelegateImpl.getInstance();
			List<CursoVO> cursos = cursoService.busquedaCurso(cursoFormacionForm.getCurso(),cursoFormacionForm.getPlantel(),cursoFormacionForm.getIdEntidad());
			@SuppressWarnings("unchecked")
			List<CatalogoOpcionVO> entidades = (List<CatalogoOpcionVO>) request.getSession().getAttribute("LISTA_ENTIDADES");

			generaPaginacion(cursos.size(),request, cursoFormacionForm);
			request.getSession().setAttribute("LISTA_CURSOS", cursos);
			request.getSession().setAttribute("TOTAL_CURSOS", cursos.size());
			int toIndex = getRangoFinal(fromIndex,cursos.size(),REGISTROS_POR_PAGINA, request, paginaActual);

			cursoFormacionForm.setListaCursos(cursos.subList(fromIndex, toIndex));
			cursoFormacionForm.setListaEntidades(entidades);
			cursoFormacionForm.setCursoDesde(fromIndex+1);
			cursoFormacionForm.setCursoHasta(toIndex);
		} catch (ServiceLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getSession().setAttribute(BODY_JSP, mapping.getInputForward().getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.setAttribute("TITULO", "Cursos de formación para el trabajo de la SEP");
		request.getSession().setAttribute(TITULO_PAGINA, "Cursos de formación para el trabajo de la SEP.");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Reg&iacute;strate en nuestra Bolsa de trabajo y postula la oferta de empleo que m&aacute;s se apegue seg&uacute;n tu curriculum vitae sin costo f&aacute;cil y sin intermediarios.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));	
		return mapping.findForward(FORWARD_TEMPLATE_CURSO);

	}

	@SuppressWarnings("unchecked")
	public ActionForward cambiarPagina(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {

		CursoFormacionForm cursoFormacionForm = (CursoFormacionForm) form;

		int paginaActual = cursoFormacionForm.getPaginaActual();
		int totalPaginas = (Integer) request.getSession().getAttribute("ULTIMA_PAGINA");
		int fromIndex = 0;
		int toIndex = 0;

		if(cursoFormacionForm.isPaginaSiguiente()) paginaActual++;

		if(cursoFormacionForm.isPaginaAnterior()) paginaActual--;
		cursoFormacionForm.setPaginaActual(paginaActual);
		cursoFormacionForm.setPaginaSiguiente(totalPaginas>paginaActual?true:false);
		cursoFormacionForm.setPaginaAnterior(paginaActual>1?true:false);


		List<CursoVO> opciones = (List<CursoVO>) request.getSession().getAttribute("LISTA_CURSOS");
		fromIndex = (paginaActual-1)*REGISTROS_POR_PAGINA;
		toIndex = getRangoFinal(fromIndex, opciones.size(), REGISTROS_POR_PAGINA, request, paginaActual);
		cursoFormacionForm.setListaCursos(opciones.subList(fromIndex, toIndex));
		cursoFormacionForm.setCursoDesde(fromIndex+1);
		cursoFormacionForm.setCursoHasta(toIndex);
		cursoFormacionForm.setListaEntidades((List<CatalogoOpcionVO>) request.getSession().getAttribute("LISTA_ENTIDADES"));

		request.getSession().setAttribute(BODY_JSP, mapping.getInputForward().getPath());
		request.setAttribute("TITULO", "Cursos de formación para el trabajo de la SEP");
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Cursos de formación para el trabajo de la SEP");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Reg&iacute;strate en nuestra Bolsa de trabajo y postula la oferta de empleo que m&aacute;s se apegue seg&uacute;n tu curriculum vitae sin costo f&aacute;cil y sin intermediarios.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_CURSO);
	}



	private int getRangoFinal(int rangoInicial, int size,int registrosPorPagina, HttpServletRequest request,int paginaActual) {
		int rangoFinal = 0;
		int ultimaPagina = (Integer) request.getSession().getAttribute("ULTIMA_PAGINA");

		if(size<registrosPorPagina){

			rangoFinal = size;

		}else{



			rangoFinal = rangoInicial+REGISTROS_POR_PAGINA;

			if(paginaActual==ultimaPagina){

				boolean registrosExtra = (Boolean) request.getSession().getAttribute("REGISTROS_EXACTOS");

				if(!registrosExtra){

					int registroExtra = (Integer) request.getSession().getAttribute("REGISTROS_ULTIMA_PAGINA");

					rangoFinal=rangoInicial+registroExtra;
				}

			}
		}

		return rangoFinal;
	}

	private void generaPaginacion(int registros, HttpServletRequest request,CursoFormacionForm form) {
		int mod = registros % REGISTROS_POR_PAGINA;
		int diff = registros - mod;
		int totalPaginas = diff / REGISTROS_POR_PAGINA;

		if(mod!=0){

			totalPaginas++;
			request.getSession().setAttribute("REGISTROS_EXACTOS", false);
			request.getSession().setAttribute("ULTIMA_PAGINA", totalPaginas);
			request.getSession().setAttribute("REGISTROS_ULTIMA_PAGINA", mod);
		}else{

			request.getSession().setAttribute("REGISTROS_EXACTOS", true);
			request.getSession().setAttribute("ULTIMA_PAGINA", totalPaginas);

		}

		form.setPaginaActual(1);

		if(totalPaginas>1){

			form.setPaginaSiguiente(true);
			form.setPaginaAnterior(false);

		}
	}

}
