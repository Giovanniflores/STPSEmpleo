package mx.gob.stps.portal.movil.web.empresa.action;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.candidate.vo.ConocimientoComputacionVO;
import mx.gob.stps.portal.core.candidate.vo.IdiomaVO;
import mx.gob.stps.portal.core.candidate.vo.InformacionGeneralVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.infra.vo.EntrevistaVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoVO;
import mx.gob.stps.portal.movil.web.candidato.delegate.CandidatoDelegateImpl;
import mx.gob.stps.portal.movil.web.empresa.delegate.EmpresaEspacioDelegate;
import mx.gob.stps.portal.movil.web.empresa.delegate.EmpresaEspacioDelegateImpl;
import mx.gob.stps.portal.movil.web.empresa.form.BuscarCandidatosEmpresaForm;
import mx.gob.stps.portal.movil.web.entrevista.delegate.EntrevistaDelegateImpl;
import mx.gob.stps.portal.movil.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.movil.web.oferta.form.PerfilJB;

public class BuscarCandidatosEmpresaAction extends PagerCandidatesAction{
	private static Logger logger = Logger.getLogger(BuscarCandidatosEmpresaAction.class); 
	private static final String FORWARD_RESULTADOS_CANDIDATO = "JSP_LISTA_CANDIDATOS";
	private static final String FORWARD_DETALLE_CANDIDATO = "JSP_DETALLE_CANDIDATO";
	private static final String FORWARD_TABLA_CANDIDATO="ACTION_REGISTROS_TABLA";
	private int REGISTROS_POR_PAGINA = 5;
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.getInputForward();
	}


	public ActionForward busquedaCandidatos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){

		request.getSession().setAttribute("idOfertaExiste", false);
		request.getSession().removeAttribute("msg");
		List<Long> indices = new ArrayList<Long>();
		HttpSession session = request.getSession();

		resetAttributes(request);

		int paginaActual = 1;
		int fromIndex = (paginaActual-1)*REGISTROS_POR_PAGINA;
		BuscarCandidatosEmpresaForm forma = (BuscarCandidatosEmpresaForm) form;
		
		if (request.getParameter("searchQ")!=null && !request.getParameter("searchQ").trim().isEmpty()) {
			String serchQ = request.getParameter("searchQ").trim();
			logger.info(serchQ);
			session.setAttribute("searchQ", serchQ);

			try {
				EmpresaEspacioDelegate services = EmpresaEspacioDelegateImpl.getInstance();
				indices = services.buscarCandidatosEmpleo(serchQ);
				
				List<CandidatoVo> candidatos = services.buscadorCandidatos(indices);
				generaPaginacion(candidatos.size(),request, forma);
				request.getSession().setAttribute("LISTA_CANDIDATOS_OFERTA", candidatos);
				request.getSession().setAttribute("TOTAL_CANDIDATOS_OFERTA", candidatos.size());
				
				
				int toIndex = getRangoFinal(fromIndex,candidatos.size(),REGISTROS_POR_PAGINA, request, paginaActual);

				forma.setListaCandidatosOferta(candidatos.subList(fromIndex, toIndex));
				forma.setOfertaDesde(fromIndex+1);
				forma.setOfertaHasta(toIndex);	
			} catch (Exception te) { te.printStackTrace();}
		}




		session.setAttribute("idOferta", (long)0);
		return mapping.findForward(FORWARD_RESULTADOS_CANDIDATO);

	}


	public ActionForward detalleCandidato(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		BuscarCandidatosEmpresaForm forma = (BuscarCandidatosEmpresaForm) form;
		long idCandidato = 0;
		long idOferta = (Long) request.getSession().getAttribute("idOferta");
		
		InformacionGeneralVO infoVO = null;
		idCandidato = Long.parseLong(request.getParameter("idc"));
		if (idCandidato > 0) {
			
			EmpresaEspacioDelegate candidateServices = EmpresaEspacioDelegateImpl.getInstance();
			EntrevistaDelegateImpl entrevistaServices = EntrevistaDelegateImpl.getInstance();
			CandidatoDelegateImpl candidatoServices = CandidatoDelegateImpl.getInstance();
			try {
				infoVO = candidateServices.showCandidateDetail(idCandidato);
				PerfilJB perfilLaboral = candidatoServices.loadPerfil(idCandidato);
				if (perfilLaboral!=null) {
					perfilLaboral.setAniosExperiencia(Constantes.EXPERIENCIA.getDescripcion((int) perfilLaboral.getPerfilLaboral().getIdExperienciaTotal()));
					forma.setPerfilLaboral(perfilLaboral);
				}
				
				ConocimientoComputacionVO conocimientosComputacion = candidatoServices.findConocimientosComputacion(idCandidato);			
				
				forma.setConocimientoComputacion(conocimientosComputacion);
				if(idOferta>0){
					int compatibilidad = Integer.parseInt(request.getParameter("compatibilidad"));
					request.setAttribute("compatibilidadCandidato", compatibilidad);
				EntrevistaVO vo = entrevistaServices.buscaEntrevistaOfertaCandidatoActiva(idCandidato,idOferta);
				if(vo!=null)session.setAttribute("idEntrevistaExiste", false);
				else session.setAttribute("idEntrevistaExiste", true);
				}
			} catch (ServiceLocatorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//candidateServices.contabilizaDetalleCandidato(idCandidato);
			catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		request.setAttribute("detalle", infoVO);
		session.setAttribute("idCandidato", idCandidato);


		return mapping.findForward(FORWARD_DETALLE_CANDIDATO);
	}

	public ActionForward busquedaCandidatosOferta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){

		resetAttributes(request);
		request.getSession().removeAttribute("msg");
		HttpSession session = request.getSession();
		BuscarCandidatosEmpresaForm forma = (BuscarCandidatosEmpresaForm) form;
		if(forma.getIdOfertaEmpleo()>0){
		session.setAttribute("idOfertaExiste", true);
		long idOfertaEmpleo = forma.getIdOfertaEmpleo();
		List<CandidatoVo> candidatos = null;
		int paginaActual = 1;
		int fromIndex = (paginaActual-1)*REGISTROS_POR_PAGINA;


		try {
			EmpresaEspacioDelegate services = EmpresaEspacioDelegateImpl.getInstance();
			
			
				
			
			candidatos = services.busquedaAsistidaCandidatos(idOfertaEmpleo);
			List<CandidatoVo> candidatosTemp = new ArrayList<CandidatoVo>();
			Iterator<CandidatoVo> itCandidatos = candidatos.iterator();
			while(itCandidatos.hasNext()){
				CandidatoVo tmpCandidato = (CandidatoVo) itCandidatos.next();
				List<IdiomaVO> lstIdiomas = services.buscarIdiomas(tmpCandidato.getIdCandidato());
				tmpCandidato.setLstIdiomas(lstIdiomas);
				candidatosTemp.add(tmpCandidato);				
			}			
			candidatos = candidatosTemp;

			

			generaPaginacion(candidatos.size(),request, forma);
			request.getSession().setAttribute("LISTA_CANDIDATOS_OFERTA", candidatos);
			request.getSession().setAttribute("TOTAL_CANDIDATOS_OFERTA", candidatos.size());

			int toIndex = getRangoFinal(fromIndex,candidatos.size(),REGISTROS_POR_PAGINA, request, paginaActual);

			forma.setListaCandidatosOferta(candidatos.subList(fromIndex, toIndex));
			forma.setOfertaDesde(fromIndex+1);
			forma.setOfertaHasta(toIndex);

		} catch (PersistenceException e) {
			logger.error(e);
		} catch (SQLException e) {
			logger.error(e);
		} catch (TechnicalException e) {
			logger.error(e);
		} catch (ServiceLocatorException e) {
			logger.error(e);
		} catch (Exception e){
			logger.error(e);
		}
		session.setAttribute("idOferta", forma.getIdOfertaEmpleo());
		session.setAttribute("tituloOferta", forma.getTituloOferta());
		session.setAttribute("searchQ", forma.getTituloOferta());
		return mapping.findForward(FORWARD_RESULTADOS_CANDIDATO);
		}
		else{
			
			return busquedaCandidatos(mapping, forma, request, response);
			
		}
	}
	
	
	public ActionForward asociarCandidatoOferta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		StringBuilder msg = new StringBuilder();
		EmpresaEspacioDelegate services = EmpresaEspacioDelegateImpl.getInstance();
		try {
			List<OfertaCandidatoVO> list = services.findByOfferCandidate(Utils.parseLong(request.getParameter("idOferta")), Utils.parseLong(request.getParameter("idCandidato")));
			if (null == list || list.isEmpty()) {
				OfertaCandidatoVO ofertaCandidato = new OfertaCandidatoVO ();
				ofertaCandidato.setIdCandidato(Utils.parseLong(request.getParameter("idCandidato")));
				ofertaCandidato.setIdOfertaEmpleo(Utils.parseLong(request.getParameter("idOfertaEmpleo")));
				ofertaCandidato.setEstatus(Constantes.ESTATUS.SELECCIONADO.getIdOpcion());
				ofertaCandidato.setFechaAlta(new Date ());
				services.createOfertaCandidato(ofertaCandidato);
				msg.append("Se agregó al candidato a la carpeta empresarial");
				request.getSession().setAttribute("msg", msg.toString());
			}
			
			
			
			
		} catch (BusinessException be) { logger.error(be);
		} catch (ServiceLocatorException se) { logger.error(se); }
				
		
		return detalleCandidato(mapping, form, request, response);
	}


	private int getRangoFinal(int rangoInicial, int size,int registrosPorPagina, HttpServletRequest request, int paginaActual) {
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


	private void generaPaginacion(int registros, HttpServletRequest request,
			BuscarCandidatosEmpresaForm forma) {
		int mod = registros % REGISTROS_POR_PAGINA;
		int diff = registros - mod;
		int totalPaginas = diff / REGISTROS_POR_PAGINA;

		logger.info("Paginas = "+totalPaginas);

		if(mod!=0){

			totalPaginas++;
			request.getSession().setAttribute("REGISTROS_EXACTOS", false);
			request.getSession().setAttribute("ULTIMA_PAGINA", totalPaginas);
			request.getSession().setAttribute("REGISTROS_ULTIMA_PAGINA", mod);
		}else{

			request.getSession().setAttribute("REGISTROS_EXACTOS", true);
			request.getSession().setAttribute("ULTIMA_PAGINA", totalPaginas);

		}

		forma.setPaginaActual(1);

		if(totalPaginas>1){

			forma.setPaginaSiguiente(true);
			forma.setPaginaAnterior(false);

		}

	}

	public ActionForward cambiarPagina(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){

		BuscarCandidatosEmpresaForm forma = (BuscarCandidatosEmpresaForm) form;



		int paginaActual = forma.getPaginaActual();
		int totalPaginas = (Integer) request.getSession().getAttribute("ULTIMA_PAGINA");
		int fromIndex = 0;
		int toIndex = 0;

		logger.info("siguientePagina "+forma.isPaginaSiguiente());
		logger.info("paginaAnterior "+forma.isPaginaAnterior());

		if(forma.isPaginaSiguiente()) paginaActual++;

		if(forma.isPaginaAnterior()) paginaActual--;

		logger.info(paginaActual);

		forma.setPaginaActual(paginaActual);
		forma.setPaginaSiguiente(totalPaginas>paginaActual?true:false);
		forma.setPaginaAnterior(paginaActual>1?true:false);
		@SuppressWarnings("unchecked")
		List<CandidatoVo> opciones = (List<CandidatoVo>) request.getSession().getAttribute("LISTA_CANDIDATOS_OFERTA");

		fromIndex = (paginaActual-1)*REGISTROS_POR_PAGINA;

		toIndex = getRangoFinal(fromIndex, opciones.size(), REGISTROS_POR_PAGINA, request, paginaActual);
		forma.setListaCandidatosOferta(opciones.subList(fromIndex, toIndex));
		forma.setOfertaDesde(fromIndex+1);
		forma.setOfertaHasta(toIndex);





		return mapping.findForward(FORWARD_TABLA_CANDIDATO);



	}

}
