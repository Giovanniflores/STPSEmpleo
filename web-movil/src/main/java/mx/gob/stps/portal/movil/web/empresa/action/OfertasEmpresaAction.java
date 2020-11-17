package mx.gob.stps.portal.movil.web.empresa.action;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.oferta.registro.vo.OfertaCarreraEspecialidadVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.movil.web.empresa.delegate.EmpresaEspacioDelegate;
import mx.gob.stps.portal.movil.web.empresa.delegate.EmpresaEspacioDelegateImpl;
import mx.gob.stps.portal.movil.web.empresa.form.OfertasEmpresaForm;
import mx.gob.stps.portal.movil.web.empresa.vo.OfertasEmpresaVO;
import mx.gob.stps.portal.movil.web.infra.action.GenericAction;
import mx.gob.stps.portal.movil.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.movil.web.seguridad.vo.UsuarioFirmadoVO;

public class OfertasEmpresaAction extends GenericAction{
	
	private static Logger logger = Logger.getLogger(OfertasEmpresaAction.class);
	private int REGISTROS_POR_PAGINA = 5;
	private static String FORWARD_PAGINACION_OFERTAS = "ACTION_OFERTAS_TABLA";
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		long idEmpresa = 0;
		UsuarioFirmadoVO usuario = getUsuarioFirmado(request.getSession());
		int paginaActual = 1;
		int fromIndex = (paginaActual-1)*REGISTROS_POR_PAGINA;
		if(usuario!=null){
			
			idEmpresa = usuario.getIdPropietario();
			
		}
		try {
		OfertasEmpresaForm ofertasEmpresaForm = (OfertasEmpresaForm) form;
		EmpresaEspacioDelegate service = EmpresaEspacioDelegateImpl.getInstance();
		List<OfertaEmpleoVO> ofertas = service.obtenerOfertasEmpresa(idEmpresa);
		
		List<OfertasEmpresaVO> listaOfertas = generaListaOfertas(ofertas);
		
		generaPaginacion(listaOfertas.size(),request, ofertasEmpresaForm);
		request.getSession().setAttribute("LISTA_OFERTAS", listaOfertas);
		request.getSession().setAttribute("TOTAL_OFERTAS", listaOfertas.size());
		
		int toIndex = getRangoFinal(fromIndex,listaOfertas.size(),REGISTROS_POR_PAGINA, request, paginaActual);
		
		ofertasEmpresaForm.setListaOfertas(listaOfertas.subList(fromIndex, toIndex));
		ofertasEmpresaForm.setOfertaDesde(fromIndex+1);
		ofertasEmpresaForm.setOfertaHasta(toIndex);
		
		
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mapping.getInputForward();
	}

	private void generaPaginacion(int registros, HttpServletRequest request,OfertasEmpresaForm form) {
		
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

		form.setPaginaActual(1);

		if(totalPaginas>1){

			form.setPaginaSiguiente(true);
			form.setPaginaAnterior(false);

		}
		
	}

	private List<OfertasEmpresaVO> generaListaOfertas(List<OfertaEmpleoVO> ofertas) {
		List<OfertasEmpresaVO> listaOfertas = new ArrayList<OfertasEmpresaVO>();
		EmpresaEspacioDelegate service = EmpresaEspacioDelegateImpl.getInstance();
		
		for(OfertaEmpleoVO oferta:ofertas){
			try {
			oferta.setUbicaciones(service.getUbicacionesOferta(oferta.getIdOfertaEmpleo()));
			OfertasEmpresaVO ofertaVO = new OfertasEmpresaVO();
			OfertaCarreraEspecialidadVO carr = oferta.getCarreraPrincipal();
			ofertaVO.setIdOfertaEmpleo(oferta.getIdOfertaEmpleo());
			ofertaVO.setTituloOferta(oferta.getTituloOferta());
			ofertaVO.setOcupacion(service.getCatalogoOpcionById(Constantes.CATALOGO_OPCION_OCUPACION,oferta.getIdOcupacion()));
			ofertaVO.setNivelEstudios(service.getCatalogoOpcionById(Constantes.CATALOGO_OPCION_GRADO_ESTUDIOS, oferta.getIdNivelEstudio()));
			ofertaVO.setCarrera(obtieneCarrera(carr));
			ofertaVO.setUbicacion(oferta.getUbicaciones().get(0).getEntidad()+"/"+oferta.getUbicaciones().get(0).getMunicipio());
			ofertaVO.setEstatus(oferta.getEstatus());
			listaOfertas.add(ofertaVO);
			} catch (ServiceLocatorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		return listaOfertas;
	}

	



	private String obtieneCarrera(OfertaCarreraEspecialidadVO carr) {
		EmpresaEspacioDelegate service = EmpresaEspacioDelegateImpl.getInstance();
		String carrera="";
		logger.info("Carrera "+carr.getId());
		if(carr!=null){
			try {
				carrera = service.getCatalogoOpcionById(Constantes.CATALOGO_OPCION_CARRERAS_1, carr.getId());
				if((carrera==null)||("".equals(carrera)))carrera = service.getCatalogoOpcionById(Constantes.CATALOGO_OPCION_CARRERAS_2, carr.getId());
				if((carrera==null)||("".equals(carrera)))carrera = service.getCatalogoOpcionById(Constantes.CATALOGO_OPCION_CARRERAS_3, carr.getId());
				if((carrera==null)||("".equals(carrera)))carrera = service.getCatalogoOpcionById(Constantes.CATALOGO_OPCION_CARRERAS_4, carr.getId());
				
			} catch (ServiceLocatorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		return carrera;
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
	
	public ActionForward cambiarPagina(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){

		OfertasEmpresaForm forma = (OfertasEmpresaForm) form;

		

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
			List<OfertasEmpresaVO> opciones = (List<OfertasEmpresaVO>) request.getSession().getAttribute("LISTA_OFERTAS");
			
			fromIndex = (paginaActual-1)*REGISTROS_POR_PAGINA;
			
			toIndex = getRangoFinal(fromIndex, opciones.size(), REGISTROS_POR_PAGINA, request, paginaActual);
			forma.setListaOfertas(opciones.subList(fromIndex, toIndex));
			forma.setOfertaDesde(fromIndex+1);
			forma.setOfertaHasta(toIndex);
			


			
		
		return mapping.findForward(FORWARD_PAGINACION_OFERTAS);



	}

}
