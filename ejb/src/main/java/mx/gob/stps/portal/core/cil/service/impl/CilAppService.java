package mx.gob.stps.portal.core.cil.service.impl;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;
import mx.gob.stps.portal.core.cil.bo.AttentionRequest;
import mx.gob.stps.portal.core.cil.dao.IntegracionLaboralDAO;
import mx.gob.stps.portal.core.cil.vo.BitacoraAtencionVO;
import mx.gob.stps.portal.core.cil.vo.SeguimientoColocacionVO;
import mx.gob.stps.portal.core.cil.service.CilAppServiceRemote;
import mx.gob.stps.portal.core.persistencia.facade.BitacoraAtencionFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.SeguimientoColocacionFacadeLocal;

@Stateless(name = "CilAppService", mappedName = "CilAppService")
public class CilAppService implements CilAppServiceRemote {

	private static final int CIL_MAX_LENGTH = 256;
	private static final int CIL_TIMER = 1000*60*60*3;
	
	@EJB
	private BitacoraAtencionFacadeLocal bitacoraAtenciontencionFacade;
	
	@EJB
	private SeguimientoColocacionFacadeLocal seguimientoColocacionFacade;
	
	private static Logger logger = Logger.getLogger(CilAppService.class);
	
	@Override
	public int saveAttentionRequest(AttentionRequest attention) {
		int result = 1;
		long nextIdAttention = 0;
		java.util.Date date = new Date();
		IntegracionLaboralDAO dao = new IntegracionLaboralDAO();
		AttentionRequest previousAttention = findAttentionRequest(attention.getIdCil(), attention.getIdCandidato());
		try {
			if (null != previousAttention.getFechaInicio() && date.getTime() - previousAttention.getFechaInicio().getTime() < CIL_TIMER)
				updateAttention(attention, previousAttention);
			else {
				nextIdAttention = dao.nextIdAtencion();
				BitacoraAtencionVO vo = attention.getActualizarCV();
				bitacoraAtenciontencionFacade.save(attention.getIdCil(), attention.getIdCandidato(), nextIdAttention, vo.getIdTipoAtencion(), date, vo.getDetalle(), vo.getContador());
				vo = attention.getFotocopias();
				bitacoraAtenciontencionFacade.save(attention.getIdCil(), attention.getIdCandidato(), nextIdAttention, vo.getIdTipoAtencion(), date, vo.getDetalle(), vo.getContador());
				vo = attention.getImpresion();
				bitacoraAtenciontencionFacade.save(attention.getIdCil(), attention.getIdCandidato(), nextIdAttention, vo.getIdTipoAtencion(), date, vo.getDetalle(), vo.getContador());
				vo = attention.getLlamadas();
				bitacoraAtenciontencionFacade.save(attention.getIdCil(), attention.getIdCandidato(), nextIdAttention, vo.getIdTipoAtencion(), date, vo.getDetalle(), vo.getContador());
				vo = attention.getOtrasBolsas();
				bitacoraAtenciontencionFacade.save(attention.getIdCil(), attention.getIdCandidato(), nextIdAttention, vo.getIdTipoAtencion(), date, normalizeDetail(vo.getDetalle()), vo.getContador());
				vo = attention.getActividadPortal();
				bitacoraAtenciontencionFacade.save(attention.getIdCil(), attention.getIdCandidato(), nextIdAttention, vo.getIdTipoAtencion(), date, normalizeDetail(vo.getDetalle()), vo.getContador());
			}
		}catch (Exception e) {
			result = -1;
			logger.info("Error al persistir solicitud de atención " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public AttentionRequest findAttentionRequest(long idCil, long idCandidato) {
		AttentionRequest attention = null;
		//IntegracionLaboralDAO dao = new IntegracionLaboralDAO();
		try {
			//attention = dao.find(idCil, idCandidato);
			attention = bitacoraAtenciontencionFacade.find(idCil, idCandidato);
			logger.info("Attention toString: " + attention.toString()); 
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Error al buscar solicitud de atención " + e.getMessage()); 
		}
		return attention;
	}

	@Override
	public int create(long idCil, long idCandidato, long idUsuario,	long idTiposeguimiento, Date fechaSeguimiento, int estatus, int idCausa, String otraCausa, Date fechaColocacion, String horaSeguimiento, long idAtencion) {
		int result = 1;
		SeguimientoColocacionVO seguimientoColocacionVO = new SeguimientoColocacionVO();
		try {
			seguimientoColocacionVO.setEstatus(estatus);
			seguimientoColocacionVO.setFechaColocacion(fechaColocacion);
			seguimientoColocacionVO.setFechaSeguimiento(fechaSeguimiento);
			seguimientoColocacionVO.setHoraSeguimiento(horaSeguimiento);
			seguimientoColocacionVO.setIdCandidato(idCandidato);
			seguimientoColocacionVO.setIdCausa(idCausa);
			seguimientoColocacionVO.setIdCil(idCil);
			seguimientoColocacionVO.setIdTiposeguimiento(idTiposeguimiento);
			seguimientoColocacionVO.setIdUsuario(idUsuario);
			seguimientoColocacionVO.setOtraCausa(otraCausa);
			seguimientoColocacionVO.setIdAtencion(idAtencion);
			seguimientoColocacionFacade.create(seguimientoColocacionVO);
		}catch (Exception e) {
			result = -1;
			logger.info("Error al persistir seguimiento colocaion " + e.getMessage()); 
		}
		return result;
	}
	
	private boolean updateAttention(AttentionRequest attention, AttentionRequest previousAttention) {
		boolean update = false;
		java.util.Date date = new Date();
		BitacoraAtencionVO vo = previousAttention.getActualizarCV();
		bitacoraAtenciontencionFacade.update(vo.getIdBitacoraAtencion(), previousAttention.getIdCil(), previousAttention.getIdCandidato(), previousAttention.getIdAtencion(), vo.getIdTipoAtencion(), date, vo.getDetalle(), attention.getActualizarCV().getContador());
		vo = previousAttention.getFotocopias();
		bitacoraAtenciontencionFacade.update(vo.getIdBitacoraAtencion(), previousAttention.getIdCil(), previousAttention.getIdCandidato(), previousAttention.getIdAtencion(), vo.getIdTipoAtencion(), date, vo.getDetalle(), attention.getFotocopias().getContador());
		vo = previousAttention.getImpresion();
		bitacoraAtenciontencionFacade.update(vo.getIdBitacoraAtencion(), previousAttention.getIdCil(), previousAttention.getIdCandidato(), previousAttention.getIdAtencion(), vo.getIdTipoAtencion(), date, vo.getDetalle(), attention.getImpresion().getContador());
		vo = previousAttention.getLlamadas();
		bitacoraAtenciontencionFacade.update(vo.getIdBitacoraAtencion(), previousAttention.getIdCil(), previousAttention.getIdCandidato(), previousAttention.getIdAtencion(), vo.getIdTipoAtencion(), date, vo.getDetalle(), attention.getLlamadas().getContador());
		vo = previousAttention.getActividadPortal();
		bitacoraAtenciontencionFacade.update(vo.getIdBitacoraAtencion(), previousAttention.getIdCil(), previousAttention.getIdCandidato(), previousAttention.getIdAtencion(), vo.getIdTipoAtencion(), date, normalizeDetail(attention.getActividadPortal().getDetalle()), vo.getContador());
		vo = previousAttention.getOtrasBolsas();
		bitacoraAtenciontencionFacade.update(vo.getIdBitacoraAtencion(), previousAttention.getIdCil(), previousAttention.getIdCandidato(), previousAttention.getIdAtencion(), vo.getIdTipoAtencion(), date, normalizeDetail(attention.getOtrasBolsas().getDetalle()), vo.getContador());
		return update;
	}
	
	@Override
	public boolean existTraceUserEmployment(long idCil, long idCandidato, long idAtencion) {
		return seguimientoColocacionFacade.exist(idCil, idCandidato, idAtencion);
	}
	
	private String normalizeDetail(String detail) {
		if (null == detail)
			return "";
		else if (detail.length() > CIL_MAX_LENGTH)
			return detail.substring(0,CIL_MAX_LENGTH);
		else
			return detail;
	}
}