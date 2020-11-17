package mx.gob.stps.portal.web.security.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.persistencia.vo.EventoVO;
import mx.gob.stps.portal.web.candidate.delegate.RegistroCandidatoEventoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.RegistroCandidatoEventoBusDelegateImpl;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.persistencia.vo.PaginadorEventoVO;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_NEXT;

public class RegistrosEventosAction extends GenericAction {
	
	private static Logger logger = Logger.getLogger(RegistrosEventosAction.class);
			
	public ActionForward proximosEventos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		RegistroCandidatoEventoBusDelegate busDelegate = RegistroCandidatoEventoBusDelegateImpl.getInstance();
		List<EventoVO> eventosRecientes = new ArrayList<EventoVO>();
		try {
			if(request.getSession().getAttribute("eventosRecientesList") == null){
				Calendar fechaHoy = Calendar.getInstance();
				int mesActual = fechaHoy.get(Calendar.MONTH)+1;
				int anioActual = fechaHoy.get(Calendar.YEAR);
				eventosRecientes = busDelegate.eventosRecientes(mesActual, anioActual, Constantes.ESTATUS.FERIA_VALIDADO_CGSNE.getIdOpcion());
				PaginadorEventoVO paginador = new PaginadorEventoVO(3, eventosRecientes);
				request.getSession().setAttribute("eventosRecientesList", paginador);
			}
			PaginadorEventoVO paginador = (PaginadorEventoVO)request.getSession().getAttribute("eventosRecientesList");
			if ("true".equals(request.getParameter("goAhead"))){
				paginador.setPaginaActual(paginador.getPaginaActual() + 1);
			}
			if ("true".equals(request.getParameter("goBack"))){
				paginador.setPaginaActual(paginador.getPaginaActual() - 1);
			}  
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return mapping.findForward("REGISTROS_EVENTOS"); 
	}
	
	public ActionForward nextEvents(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		RegistroCandidatoEventoBusDelegate busDelegate = RegistroCandidatoEventoBusDelegateImpl.getInstance();
		List<EventoVO> nextEvents = new ArrayList<EventoVO>();
		try {
			if(request.getSession().getAttribute("nextEventsList") == null){
				Calendar fechaHoy = Calendar.getInstance();
				int mesActual = fechaHoy.get(Calendar.MONTH)+1;
				int anioActual = fechaHoy.get(Calendar.YEAR);
				nextEvents = busDelegate.eventosRecientes(mesActual, anioActual, Constantes.ESTATUS.FERIA_VALIDADO_CGSNE.getIdOpcion());
				PaginadorEventoVO paginador = new PaginadorEventoVO(3, nextEvents);
				request.getSession().setAttribute("nextEvents", paginador);
			}
			PaginadorEventoVO paginador = (PaginadorEventoVO)request.getSession().getAttribute("nextEventsList");
			if ("true".equals(request.getParameter("goAhead"))) {
				paginador.setPaginaActual(paginador.getPaginaActual() + 1);
			}
			if ("true".equals(request.getParameter("goBack"))) {
				paginador.setPaginaActual(paginador.getPaginaActual() - 1);
			}  
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return mapping.findForward(FORWARD_NEXT); 
	}
	
	public ActionForward previousEvents(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		List<EventoVO> previousEvents = new ArrayList<EventoVO>();
		RegistroCandidatoEventoBusDelegate busDelegate = RegistroCandidatoEventoBusDelegateImpl.getInstance();
		try {
			if (request.getSession().getAttribute("previousEventList") == null) {
				Calendar fechaHoy = Calendar.getInstance();
				int month = fechaHoy.get(Calendar.MONTH)+1;
				int year = fechaHoy.get(Calendar.YEAR);
				previousEvents = busDelegate.previousEventList(month, year, Constantes.ESTATUS.FERIA_VALIDADO_CGSNE.getIdOpcion());
				PaginadorEventoVO paginador = new PaginadorEventoVO(10, previousEvents);
				request.getSession().setAttribute("previousEventList", paginador);
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return mapping.findForward(FORWARD_JSP); 
	}
	
	public ActionForward paginatorEvents(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		List<EventoVO> previousEvents = new ArrayList<EventoVO>();
		RegistroCandidatoEventoBusDelegate busDelegate = RegistroCandidatoEventoBusDelegateImpl.getInstance();
		try {
			if (request.getSession().getAttribute("previousEventList") == null) {
				Calendar fechaHoy = Calendar.getInstance();
				int month = fechaHoy.get(Calendar.MONTH)+1;
				int year = fechaHoy.get(Calendar.YEAR);
				previousEvents = busDelegate.previousEventList(month, year, Constantes.ESTATUS.FERIA_VALIDADO_CGSNE.getIdOpcion());
				PaginadorEventoVO paginador = new PaginadorEventoVO(10, previousEvents);
				request.getSession().setAttribute("previousEventList", paginador);
			}
			PaginadorEventoVO paginador = (PaginadorEventoVO)request.getSession().getAttribute("previousEventList");
			if ("true".equals(request.getParameter("goAhead"))) {
				paginador.setPaginaActual(paginador.getPaginaActual() + 1);
			}
			if ("true".equals(request.getParameter("goBack"))) {
				paginador.setPaginaActual(paginador.getPaginaActual() - 1);
			}  
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return mapping.findForward("ACTION_REGISTROS_TABLA"); 
	}
	
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return null;
	}
}
