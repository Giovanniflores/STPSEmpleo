package mx.gob.stps.portal.movil.web.candidato.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaPostulacionVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.movil.app.model.base.PostulatedDTO;
import mx.gob.stps.portal.movil.app.model.rest.PostulateRestDTO;
import mx.gob.stps.portal.movil.app.service.util.OfertaUtil;
import mx.gob.stps.portal.movil.web.oferta.delegate.OfertaDelegateImpl;

import org.apache.log4j.Logger;

public final class UtilPostulate {

	private static final Logger logger = Logger.getLogger(UtilPostulate.class);

	public static PostulateRestDTO avisoCandidatoPostuladoActionSinRequest(
			long idOfertaEmpleo, HttpServletRequest request) {

		PostulateRestDTO datos = new PostulateRestDTO();
		if (idOfertaEmpleo > 0) {

			try {

				OfertaDelegateImpl services = OfertaDelegateImpl.getInstance();
				OfertaEmpleoVO oferta = services
						.consultaOfertaEmpleo(idOfertaEmpleo);

				if (oferta != null) {

					datos.setNombreContacto(oferta.getNombreContacto());
					datos.setCorreoElectronico(oferta
							.getCorreoElectronicoContacto());
					// request.setAttribute("telefonos", oferta.getTelefonos());

					DomicilioVO domicilioVO = oferta.getDomicilio();
					if (domicilioVO != null) {

						String domicilio = new String();
						if (domicilioVO.getCalle() != null
								&& !"".equals(domicilioVO.getCalle()))
							domicilio = domicilioVO.getCalle();

						if (domicilioVO.getNumeroExterior() != null
								&& domicilio != null && !"".equals(domicilio)
								&& domicilioVO.getNumeroExterior() != null
								&& !"".equals(domicilioVO.getNumeroExterior()))
							domicilio = domicilio + " No. "
									+ domicilioVO.getNumeroExterior();

						if (domicilioVO.getColonia() != null
								&& !"".equals(domicilioVO.getColonia())) {
							if (!"".equals(domicilio))
								domicilio = domicilio + ", ";
							domicilio = domicilio + "Colonia "
									+ domicilioVO.getColonia();
						}

						if (domicilioVO.getCodigoPostal() != null
								&& !"".equals(domicilioVO.getCodigoPostal())) {
							if (!"".equals(domicilio))
								domicilio = domicilio + ", ";
							domicilio = domicilio + "C.P. "
									+ domicilioVO.getCodigoPostal();
						}

						if (domicilioVO.getMunicipio() != null
								&& !"".equals(domicilioVO.getMunicipio())) {
							if (!"".equals(domicilio))
								domicilio = domicilio + ", ";
							domicilio = domicilio + "Delegacion "
									+ domicilioVO.getMunicipio();
						}

						if (domicilioVO.getEntidad() != null
								&& !"".equals(domicilioVO.getEntidad())) {
							if (!"".equals(domicilio))
								domicilio = domicilio + ", ";
							domicilio = domicilio + domicilioVO.getEntidad();
						}
						datos.setDomicilio(domicilio);

					}
				} else
					return null;
			} catch (Exception e) {
				logger.error("Error al recuperar los datos de contacto de la oferta de empleo");
				e.printStackTrace();
				datos.setResult("Error al recuperar los datos de contacto de la oferta de empleo");
			}
		}
		return datos;
	}

	public static void avisoCandidatoPostuladoAction(long idOfertaEmpleo,
			HttpServletRequest request) {

		if (idOfertaEmpleo > 0) {

			PostulateRestDTO datos = avisoCandidatoPostuladoActionSinRequest(
					idOfertaEmpleo, request);
			if (datos != null) {
				request.setAttribute("nombreContacto",
						datos.getNombreContacto());
				request.setAttribute("correoElectronico",
						datos.getCorreoElectronico());
				// request.setAttribute("telefonos", oferta.getTelefonos());
				request.setAttribute("domicilio", datos.getDomicilio());
			}
		}

	}

	public static void increasePostCount(long idOfertaEmpleo) {
		try {
			OfertaDelegateImpl services = OfertaDelegateImpl.getInstance();
			OfertaPostulacionVO vo = services.consultaOfertaPostulada(
					idOfertaEmpleo, Calendar.getInstance().get(Calendar.YEAR),
					Calendar.getInstance().get(Calendar.MONTH));
			if (null != vo) {
				int count = vo.getContador();
				vo.setContador(count + 1);
				services.incrementaContadorPostulacion(vo);
			} else {
				vo = new OfertaPostulacionVO();
				vo.setAnio(Calendar.getInstance().get(Calendar.YEAR));
				vo.setContador(1);
				vo.setIdOfertaEmpleo(idOfertaEmpleo);
				vo.setMes(Calendar.getInstance().get(Calendar.MONTH));

				services.inicializaContadorPostulacion(vo);
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	
	public static List<PostulatedDTO> getPostulated(long idOfertaEmpleo, long idCandidato) {
		
		List<PostulatedDTO> listPostulated = new ArrayList<PostulatedDTO>();
		try {
			OfertaDelegateImpl services = OfertaDelegateImpl.getInstance();
			List<OfertaCandidatoVO> list = services.findByOfferCandidate(idOfertaEmpleo, idCandidato);
	
			if (list!=null && !list.isEmpty()) {
				for (OfertaCandidatoVO vo : list) {
					
					 PostulatedDTO postulated = new PostulatedDTO(vo.getIdOfertaCandidato(),vo.getEstatus(),OfertaUtil.datoToString(vo.getFechaAlta()));
					 listPostulated.add(postulated);
					 
				}
			}
		}catch(Exception e) { logger.error(e); }
		
		return listPostulated;
	}
	
	
	public static boolean isPostulated(long idOfertaEmpleo, long idCandidato) {
		boolean postulated = false;
		
		try {
			OfertaDelegateImpl services = OfertaDelegateImpl.getInstance();
			List<OfertaCandidatoVO> list = services.findByOfferCandidate(idOfertaEmpleo, idCandidato);
	
			if (list!=null && !list.isEmpty()) {
				for (OfertaCandidatoVO vo : list) {
					if (null != vo && (vo.getEstatus() == ESTATUS.POSTULADO.getIdOpcion())){
						postulated = true;
						break;
					}
				}
			}
		}catch(Exception e) { logger.error(e); }
		
		return postulated;
	}
	
	public static Long getIdPostulated(long idOfertaEmpleo, long idCandidato) {
		
		
		try {
			OfertaDelegateImpl services = OfertaDelegateImpl.getInstance();
			List<OfertaCandidatoVO> list = services.findByOfferCandidate(idOfertaEmpleo, idCandidato);
	
			if (list!=null && !list.isEmpty()) {
				for (OfertaCandidatoVO vo : list) {
					if (null != vo && (vo.getEstatus() == ESTATUS.POSTULADO.getIdOpcion())){
						return vo.getIdOfertaCandidato();
						
					}
				}
			}
		}catch(Exception e) { logger.error(e); }
		
		return null;
	}
	
	
public static Long getIdOfertaCandidato(long idOfertaEmpleo, long idCandidato) {
		
		
		try {
			OfertaDelegateImpl services = OfertaDelegateImpl.getInstance();
			List<OfertaCandidatoVO> list = services.findByOfferCandidate(idOfertaEmpleo, idCandidato);
	
			if (list!=null && !list.isEmpty()) {
				for (OfertaCandidatoVO vo : list) {
					if (null != vo && (vo.getEstatus() == ESTATUS.VINCULADO.getIdOpcion())){
						return vo.getIdOfertaCandidato();
						
					}
				}
			}
		}catch(Exception e) { logger.error(e); }
		
		return null;
	}

}
