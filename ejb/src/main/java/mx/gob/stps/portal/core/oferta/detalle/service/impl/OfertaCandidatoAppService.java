package mx.gob.stps.portal.core.oferta.detalle.service.impl;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_COMPETENCIAS_TRANSVERSALES;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.naming.Context;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.candidate.service.CandidatoAppServiceLocal;
import mx.gob.stps.portal.core.candidate.service.impl.CandidatoAppService;
import mx.gob.stps.portal.core.candidate.vo.ConocimientoComputacionVO;
import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.empresa.service.EmpresaAppServiceLocal;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.data.ConexionFactory;
import mx.gob.stps.portal.core.infra.data.ConnectionWraper;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.vo.BitacoraSiisneVO;
import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.APLICACION;
import mx.gob.stps.portal.core.infra.utils.Constantes.CONTACTO_CORREO;
import mx.gob.stps.portal.core.infra.utils.Constantes.CONTACTO_TELEFONO;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.MULTIREGISTRO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.core.oferta.detalle.dao.OfertaDetalleDAO;
import mx.gob.stps.portal.core.oferta.detalle.helper.Utils;
import mx.gob.stps.portal.core.oferta.detalle.service.OfertaCandidatoAppServiceLocal;
import mx.gob.stps.portal.core.oferta.detalle.service.OfertaCandidatoAppServiceRemote;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaEmpleoJB;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaIdiomaVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaRequisitoVO;
import mx.gob.stps.portal.core.oferta.registro.vo.RegistroUbicacionVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.core.oferta.vo.UbicacionCanadaVO;
import mx.gob.stps.portal.core.persistencia.facade.BitacoraFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.BitacoraSiisneFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.CandidatoFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.CatalogoOpcionFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.ConocimientoComputacionFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.DomicilioFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.OfertaCandidatoFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.OfertaEmpleoFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.OfertaFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.OfertaIdiomaFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.OfertaRequisitoFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.OfertaUbicacionFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.PostulacionExternaFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.TelefonoFacadeLocal;
import mx.gob.stps.portal.persistencia.entity.OfertaCandidato;
import mx.gob.stps.portal.persistencia.entity.PostulacionExterna;
import mx.gob.stps.portal.persistencia.vo.CatSubareaVO;
import mx.gob.stps.portal.persistencia.vo.OfertaCandidatoOcupacionDTO;
import mx.gob.stps.portal.persistencia.vo.OfertaEmpresaAdminDTO;
import mx.gob.stps.portal.persistencia.vo.OfertaFuncionVO;
import mx.gob.stps.portal.persistencia.vo.PostulacionExternaVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.utils.Catalogos.COD_OPERACION_BITACORA;
import mx.gob.stps.portal.utils.Catalogos.ESTATUS_POSTULACION_EXTERNA;
import mx.gob.stps.portal.utils.Catalogos.TIPO_DISCAPACIDAD;

import org.apache.log4j.Logger;


@Stateless(name = "OfertaCandidatoAppService", mappedName = "OfertaCandidatoAppService")
public class OfertaCandidatoAppService implements OfertaCandidatoAppServiceRemote, OfertaCandidatoAppServiceLocal {
	
	private static Logger logger = Logger.getLogger(OfertaCandidatoAppService.class);
	
	@EJB private OfertaCandidatoFacadeLocal ofertaCandidatoFacade;

	@EJB private OfertaEmpleoFacadeLocal ofertaEmpleoFacade;	
	
	@EJB private EmpresaAppServiceLocal empresaAppService;
	
	@EJB private OfertaRequisitoFacadeLocal ofertaRequisitoFacade;
	
	@EJB private OfertaIdiomaFacadeLocal ofertaIdiomaFacade;

	@EJB private TelefonoFacadeLocal telefonoFacade;	
	
	@EJB private OfertaUbicacionFacadeLocal ofertaUbicacionFacadeLocal;

	@EJB private OfertaFacadeLocal ofertaFacade;
	
	@EJB private BitacoraFacadeLocal bitacoraFacade;
	
	@EJB private ConocimientoComputacionFacadeLocal conocimientoComputacionFacade;

    @EJB private CandidatoAppServiceLocal candidatoAppService;
	
	@EJB private PostulacionExternaFacadeLocal postulacionExternaFacade;
	
	@EJB private BitacoraSiisneFacadeLocal bitacoraSiisneFacade;
	
	@EJB private CandidatoFacadeLocal candidatoFacade;
	
	@EJB private DomicilioFacadeLocal domicilioFacade;
	
	@EJB private CatalogoOpcionFacadeLocal catalogoOpcionFacadeLocal;
	
	public OfertaCandidatoAppService() {}
	
	@Override
	public int create(OfertaCandidatoVO vo) throws BusinessException {
		
		if (vo.getIdVinculado()==null)
			vo.setIdVinculado((long)Catalogos.APLICACION.PORTAL_DEL_EMPLEO.getIdOpcion());
		
		int result = ofertaCandidatoFacade.create(vo);
		if(result == 1) //Exito creacion
		{
			//guardar en la bitacora
/*			ï‚§	Id  PostulaciÃ³n
			ï‚§	Id Candidato
			ï‚§	Id Oferta
			ï‚§	Fecha y hora de registro
			ï‚§	Id usuario que realizÃ³ la postulaciÃ³n
*/
			if(vo.getEvento() != null && vo.getEvento().getIdEvento() != 0){
				StringBuilder detaille = new StringBuilder();
				detaille.append(vo.getEvento().getEvento()).append(" ");
				detaille.append("idPostulacion = ").append(vo.getIdOfertaCandidato()).append(" ");
				detaille.append("idCandidato = ").append(vo.getIdCandidato()).append(" ");
				detaille.append("idOferate = ").append(vo.getIdOfertaEmpleo()).append(" ");
				detaille.append("fecha y hora =").append(mx.gob.stps.portal.utils.Utils.getFechaYHora(vo.getFechaAlta())).append(" ");
				detaille.append("idUsuario = ").append(vo.getIdUsuario());
				Calendar cal = Calendar.getInstance();
				cal.setTime(vo.getFechaAlta());
				bitacoraFacade.save(vo.getEvento().getIdEvento(), vo.getIdUsuario(),vo.getEvento().getEvento() , cal, detaille.toString(), vo.getIdOfertaCandidato(), vo.getTipoPropietario().getIdTipoPropietario());
			}
		}
		return result;
	}

	@Override
	public OfertaCandidatoVO findById(long idOfertaCandidato) throws BusinessException {
		OfertaCandidatoVO vo = null;
		try {
			vo = ofertaCandidatoFacade.findById(idOfertaCandidato);
		}catch(PersistenceException e){
			e.printStackTrace(); logger.error(e);
    		throw new BusinessException("No se encuentra la oferta candidato con identificadores oferta de empleo: " + idOfertaCandidato, e);
    	}
		return vo;
	}

	@Override
	public void update(OfertaCandidatoVO vo) throws BusinessException {
		ofertaCandidatoFacade.update(vo);
	}

	@Override
	public void remove(long idOfertaCandidato) throws BusinessException {
		ofertaCandidatoFacade.remove(idOfertaCandidato);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<OfertaCandidatoVO> misOfertas(long idCandidato) throws BusinessException {
		List<OfertaCandidatoVO> list = new ArrayList<OfertaCandidatoVO>();
		try{
			list = ofertaCandidatoFacade.misOfertas(idCandidato);
    	}catch(PersistenceException e){
    		e.printStackTrace(); logger.error(e);
    		throw new BusinessException("No se encuentra el candidato con id: " + idCandidato, e);
    	}
		return list;
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<OfertaEmpleoJB> misOfertasEmpleo(long idCandidato) throws BusinessException {
		List<OfertaEmpleoJB> ofertas = new ArrayList<OfertaEmpleoJB>();

		try{
			ofertas = ofertaEmpleoFacade.consultaOfertasEmpleoAsignadas(idCandidato, Catalogos.ESTATUS.SELECCIONADA, Catalogos.ESTATUS.POSTULADO);
		}catch(Exception e){
			e.printStackTrace(); logger.error(e);
		}

		return ofertas;
	}

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<OfertaEmpleoJB> misPostulaciones(long idCandidato) throws BusinessException {
        final int DAYS_AFTER_JOB_OFFER_EXPIRES = 30;
        List<OfertaEmpleoJB> ofertas = new ArrayList<OfertaEmpleoJB>();

        Integer ppcEstatus = null;//candidatoAppService.consultarPpcEstatus(idCandidato);
        if (ppcEstatus == null) ppcEstatus = 0;
        boolean hasNeverBeenInTouchWithPpc = (ppcEstatus == 0);
        boolean hasDecidedNotToEnrollToPpc = (!hasNeverBeenInTouchWithPpc) && (ppcEstatus == Catalogos.ESTATUS.NO_INSCRITO_PPC.getIdOpcion());
        boolean isActiveToPpc = (!hasNeverBeenInTouchWithPpc) && (ppcEstatus == Catalogos.ESTATUS.ACTIVO_PPC.getIdOpcion());
        boolean isInactiveToPpc = (!hasNeverBeenInTouchWithPpc) && (ppcEstatus == Catalogos.ESTATUS.INACTIVO_PPC.getIdOpcion());
        boolean isNotAnyMoreEnrolledToPpc = (!hasNeverBeenInTouchWithPpc) && (ppcEstatus == Catalogos.ESTATUS.FUERA_PPC.getIdOpcion());

        Catalogos.ESTATUS[] estatusOfertaCandidato = new Catalogos.ESTATUS[]{
                Catalogos.ESTATUS.POSTULADO,  // 5
                Catalogos.ESTATUS.EN_PROCESO, // 20
                Catalogos.ESTATUS.CONTRATADO, // 21
                Catalogos.ESTATUS.NO_ACEPTADO //22
        };

        Catalogos.ESTATUS[] estatusOfertaCandidatoPpc = new Catalogos.ESTATUS[]{
                Catalogos.ESTATUS.POSTULADO,  // 5
                Catalogos.ESTATUS.EN_PROCESO, // 20
                Catalogos.ESTATUS.NO_ACEPTADO //22
        };

        Catalogos.ESTATUS[] estatusOfertaCandidatoContratado = new Catalogos.ESTATUS[]{
                Catalogos.ESTATUS.CONTRATADO, // 21
        };

        Catalogos.ESTATUS[] estatusOfertaEmpleo = new Catalogos.ESTATUS[]{
                // --------OFERTAS ACTIVAS---------
                Catalogos.ESTATUS.ACTIVO, // 1
                Catalogos.ESTATUS.PUBLICADA_PARA_SISNE,   // 38
                // --------OFERTAS INACTIVAS---------
                Catalogos.ESTATUS.CANCELADA,  // 10
                Catalogos.ESTATUS.INACTIVO,   // 2
                Catalogos.ESTATUS.ELIMINADA_EMP,  // 13
                Catalogos.ESTATUS.ELIMINADA_ADMIN,    // 14
                Catalogos.ESTATUS.ELIMINADA_VIG,  // 15
                Catalogos.ESTATUS.ELIMINADA_EMP_FRAUDULENTA,  // 28
                Catalogos.ESTATUS.CUBIERTA    // 39
        };
        if (hasNeverBeenInTouchWithPpc || hasDecidedNotToEnrollToPpc || isNotAnyMoreEnrolledToPpc) {
            // Candidato NO inscrito al PPC-SD (candidatos con estatus con relacion al PPC-SD:
            // "No inscrito al PPC", "Fuera del PPC", o "nunca ha solicitado la inscripcion"
            try {
                ofertas = ofertaEmpleoFacade.misPostulaciones(idCandidato, Arrays.asList(estatusOfertaCandidato), Arrays.asList(estatusOfertaEmpleo), DAYS_AFTER_JOB_OFFER_EXPIRES);
            } catch(Exception e){
                e.printStackTrace(); logger.error(e);
            }
        }else if (isActiveToPpc || isInactiveToPpc) {
            // Candidato inscrito al PPC-SD con estatus "Activo para el PPC" o "Inactivo para el PPC"
//            int hired = ofertaEmpleoFacade.consultarOfertasContratadoPpc(idCandidato);
//            if (hired == 0) {   // Not HIRED
                try {
                    ofertas = ofertaEmpleoFacade.misPostulaciones(idCandidato, Arrays.asList(estatusOfertaCandidatoPpc), Arrays.asList(estatusOfertaEmpleo), DAYS_AFTER_JOB_OFFER_EXPIRES);
                }catch(Exception e){
                    e.printStackTrace(); logger.error(e);
                }
//            } else { // Indeed HIRED
//                try{
//                    ofertas = ofertaEmpleoFacade.misPostulaciones(idCandidato, Arrays.asList(estatusOfertaCandidatoContratado), Arrays.asList(estatusOfertaEmpleo), DAYS_AFTER_JOB_OFFER_EXPIRES);
//                }catch(Exception e){
//                    e.printStackTrace(); logger.error(e);
//                }
//            }
        }
        return ofertas;
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<OfertaEmpleoJB> miContratacionPpc(final long idOfertaCandidato) {
        List<OfertaEmpleoJB> ofertas = new ArrayList<OfertaEmpleoJB>();
        try{
            ofertas = ofertaEmpleoFacade.miContratacionPpc(idOfertaCandidato);
        }catch(Exception e){
            e.printStackTrace(); logger.error(e);
        }
        return ofertas;
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public int consultarOfertasContratadoPpc(long idCandidato) throws BusinessException {
        return ofertaEmpleoFacade.consultarOfertasContratadoPpc(idCandidato);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<OfertaEmpleoJB> misOfertasGuardadas(long idCandidato) throws BusinessException {
        List<OfertaEmpleoJB> ofertas = new ArrayList<OfertaEmpleoJB>();

        try{
            ofertas = ofertaEmpleoFacade.consultaOfertasEmpleoAsignadas(idCandidato, Catalogos.ESTATUS.SELECCIONADA);
        }catch(Exception e){
            e.printStackTrace(); logger.error(e);
        }

        return ofertas;
    }

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<OfertaEmpleoJB> empresasMeBuscanOfertas(long idCandidato) throws BusinessException {
		List<OfertaEmpleoJB> ofertas = new ArrayList<OfertaEmpleoJB>();

		try{
			ofertas = ofertaEmpleoFacade.consultaOfertasEmpleoAsignadas(idCandidato, Catalogos.ESTATUS.EN_PROCESO, Catalogos.ESTATUS.VINCULADO);
		}catch(Exception e){
			e.printStackTrace(); logger.error(e);
		}

		return ofertas;
		//List<OfertaCandidatoVO> ofertas = empresasMeBuscan(idCandidato);
		//return ofertaEmpleoList(ofertas);
	}
	
	/*@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	private List<OfertaEmpleoJB> ofertaEmpleoList(List<OfertaCandidatoVO> ofertaCandidatoListVO) throws BusinessException {
		List<OfertaEmpleoJB> list = new ArrayList<OfertaEmpleoJB>();
		
		if (null == ofertaCandidatoListVO || ofertaCandidatoListVO.isEmpty()) {
			return list;
		}
		
		Connection conn = null;
		
		try{
			ConexionFactory factory = ConexionFactory.getInstance();
			conn = factory.getConnectionStpsEmpleo();
			
			OfertaDetalleDAO dao = OfertaDetalleDAO.getInstance(conn);
			
			Iterator<OfertaCandidatoVO> it = ofertaCandidatoListVO.iterator();
			while (it.hasNext()) {
				OfertaCandidatoVO ofertaCandidatoVO = it.next();

				OfertaEmpleoVO ofertaEmpleoVO = ofertaEmpleoFacade.findById(ofertaCandidatoVO.getIdOfertaEmpleo());
				EmpresaVO empresaVO = empresaAppService.findEmpresaById(ofertaEmpleoVO.getIdEmpresa());		
				List<OfertaRequisitoVO> requisitos = ofertaRequisitoFacade.ofertaRequisitosList(ofertaEmpleoVO.getIdOfertaEmpleo());
				List<OfertaIdiomaVO> idiomasCert = ofertaIdiomaFacade.ofertaIdiomasList(ofertaEmpleoVO.getIdOfertaEmpleo());

				HashMap<String, String> ubicaciones = null;
				HashMap<String, String> idiomasMap = null;
				List<String> especialidades = null;
				List<String> sectores = null;
				List<String> prestaciones = null;
				String areaLaboral = null;
				String ocupacion = null;
				
				try{
					ubicaciones = dao.getUbicacion(ofertaEmpleoVO.getIdOfertaEmpleo());
					idiomasMap = dao.ofertaIdiomasList(ofertaEmpleoVO.getIdOfertaEmpleo());
					especialidades = dao.especialidadesList(ofertaEmpleoVO.getIdOfertaEmpleo(), ofertaEmpleoVO.getIdNivelEstudio());
					sectores = dao.sectorList(ofertaEmpleoVO.getIdOfertaEmpleo());
					prestaciones = dao.prestacionesList(ofertaEmpleoVO.getIdOfertaEmpleo());
					areaLaboral = dao.getCatalogoOpcion(Constantes.CATALOGO_OPCION_AREA_LABORAL, ofertaEmpleoVO.getIdAreaLaboral());
					ocupacion = dao.getCatalogoOpcion(Constantes.CATALOGO_OPCION_OCUPACION, ofertaEmpleoVO.getIdOcupacion());

					if (idiomasCert!=null){
						for (OfertaIdiomaVO idiomaVO : idiomasCert){
							int idCatCertificacion = Utils.getIdCatCertificacion((int)idiomaVO.getIdIdioma());
							String certificacion = dao.getCatalogoOpcion(idCatCertificacion, idiomaVO.getIdCertificacion());
							idiomaVO.setCertificacion(certificacion);
						}
					}
				}catch(SQLException e){
					e.printStackTrace();
				}

				OfertaEmpleoJB jb = new OfertaEmpleoJB(ofertaCandidatoVO, ofertaEmpleoVO, empresaVO, requisitos, 
						                               idiomasCert, ubicaciones, idiomasMap, especialidades,
						                               sectores, prestaciones, areaLaboral, ocupacion);
				
				list.add(jb);
			}
		
		} catch (SQLException e1) {
			logger.error(e1);
		} catch(BusinessException e){
			throw e;
		} catch (Exception e1) {
			logger.error(e1);
		} finally {
			try{if (conn!=null) conn.close();}catch(Exception e){e.printStackTrace();}
		}
		
		return list;
	}*/

	public List<OfertaEmpleoJB> buscarOfertasEmpleo(List<Long> idsOfertas) throws BusinessException {
		List<OfertaEmpleoJB> ofertas = new ArrayList<OfertaEmpleoJB>();
		Context context = null;
		Connection conn = null;

		try{
    		ConnectionWraper wraper = ConexionFactory.getInstance().getConnection();
    		conn = wraper.getConnection();
    		context = wraper.getContext();
			
			for (long idOfertaEmpleo : idsOfertas){
				OfertaEmpleoJB oferta = buscarOfertaEmpleo(idOfertaEmpleo, conn);
				ofertas.add(oferta);
			}
			
		} catch(Exception e){
			e.printStackTrace();
			logger.error(e);
		} finally {
			try{if (conn!=null) conn.close();}catch(Exception e){e.printStackTrace();}
			
			if (context!=null){
				logger.info("Cerrando context");
				try {
					context.close();
				} catch (Exception e) {e.printStackTrace();}
			}
		}
		
		return ofertas;
	}
	
	public OfertaEmpleoJB buscarOfertaEmpleo(long idOfertaEmpleo) throws BusinessException {
		OfertaEmpleoJB ofertaEmpleoJB = null;
		Connection conn = null;
		Context context = null;
		
		try{
			ConexionFactory factory = ConexionFactory.getInstance();
			ConnectionWraper wraper = factory.getConnection();
			conn = wraper.getConnection();
			context = wraper.getContext();
			
			ofertaEmpleoJB = buscarOfertaEmpleo(idOfertaEmpleo, null);
			if (ofertaEmpleoJB!=null){	
				DomicilioVO domicilio = domicilioFacade.buscarDomicilioIdPropietario(idOfertaEmpleo, TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
				ofertaEmpleoJB.setDomicilio(domicilio);
			}
			
		} 
		
		catch(Exception e){
			e.printStackTrace();
			logger.error(e);
		} finally {
			try{if (conn!=null) conn.close();}catch(Exception e){e.printStackTrace();}
			if (context!=null){
				logger.info("Cerrando context");
				try{context.close();}catch(Exception e){e.printStackTrace();}
			}
		}
		
		return ofertaEmpleoJB;
	}
	
	/**
	private void setAreaNegocio(OfertaEmpleoVO offer) {
		StringBuilder builder = new StringBuilder("");
		CatSubareaVO subarea = catalogoOpcionFacadeLocal.getSubAreaVOByIdAreaIdSubArea(offer.getIdArea(),
				offer.getIdSubArea());
		if (null != subarea)
			offer.setSubAreaLaboralDescripcion(subarea.getDescripcion());
		List<OfertaFuncionVO> funciones = ofertaEmpleoFacade.getOfertaFuncionList(offer.getIdOfertaEmpleo());
		offer.setFuncionesList(funciones);
		for (OfertaFuncionVO funcion : funciones) {
			if (null != funcion.getFuncion()) builder.append(" ").append(funcion.getFuncion());
		}
		if (builder.length() > 0) {
			builder.replace(0, 1, "");
		}
		offer.setFunciones(builder.toString());
	}**/
	
	private OfertaEmpleoJB buscarOfertaEmpleo(long idOfertaEmpleo, Connection conn) throws BusinessException {
		OfertaEmpleoJB ofertaEmpleoJB = null;
		ConocimientoComputacionVO conocimientoComputacion = null;
		try {
			HashMap<String, String> ubicaciones = null;
			HashMap<String, String> idiomasMap = null;
			List<String> especialidades = null;
			List<String> nivelesPuesto= null;//RBM1 TK1000 es nivelesPuesto sectores1 
			List<String> prestaciones = null;
			String areaLaboral = null;
			String subarea = null;//RBM1 TK1000 es subarea
			List<OfertaIdiomaVO> idiomasCert = null;
			OfertaDetalleDAO dao = OfertaDetalleDAO.getInstance(conn);
			OfertaEmpleoVO ofertaEmpleoVO = ofertaEmpleoFacade.findById(idOfertaEmpleo);
			conocimientoComputacion = conocimientoComputacionFacade.consultaConocimientosComputacion(idOfertaEmpleo, Constantes.TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
			ofertaEmpleoVO.setConocimientoComputacion(conocimientoComputacion);
			EmpresaVO empresaVO = empresaAppService.findEmpresaById(ofertaEmpleoVO.getIdEmpresa());
			List<OfertaRequisitoVO> requisitos = ofertaRequisitoFacade.ofertaRequisitosList(ofertaEmpleoVO.getIdOfertaEmpleo());
			//List<CatalogoOpcionVO> habilidades = ofertaFacade.consultaHabilidades(idOfertaEmpleo, CATALOGO_OPCION_CANDIDATO_HABILIDAD);
			List<CatalogoOpcionVO> habilidades = ofertaFacade.consultaHabilidades(idOfertaEmpleo, CATALOGO_OPCION_COMPETENCIAS_TRANSVERSALES);
			//setAreaNegocio(ofertaEmpleoVO);
			try {
				if (ofertaEmpleoVO.getFuente()==3) {
					ubicaciones = new HashMap<String, String>();
					UbicacionCanadaVO vo = ofertaUbicacionFacadeLocal.getUbicacionOfertaCanada(idOfertaEmpleo);
					ubicaciones.put("entidad", vo.getProvincia());
					ubicaciones.put("municipio",vo.getCiudad());				
				}else
					ubicaciones = dao.getUbicacion(ofertaEmpleoVO.getIdOfertaEmpleo());
				idiomasMap = dao.ofertaIdiomasList(ofertaEmpleoVO.getIdOfertaEmpleo());	
				if (null == ofertaEmpleoVO.getContactoDomicilio())
					ofertaEmpleoVO.setContactoDomicilio(Constantes.CONTACTO_DOMICILIO.NO.getIdContactoDomicilio());
				especialidades = dao.especialidadesList(ofertaEmpleoVO.getIdOfertaEmpleo(), ofertaEmpleoVO.getIdNivelEstudio());
				
				//RBM1 TK1000 TK1001 area-area / ocupacion-subarea / sector-nivel puesto
				nivelesPuesto = dao.nivelPuestoList(ofertaEmpleoVO.getIdOfertaEmpleo());
				prestaciones = dao.prestacionesList(ofertaEmpleoVO.getIdOfertaEmpleo());
				//areaLaboral = dao.getCatalogoOpcion(Constantes.CATALOGO_OPCION_AREA_LABORAL, ofertaEmpleoVO.getIdAreaLaboral());
				areaLaboral = dao.getCatalogoArea(ofertaEmpleoVO.getIdAreaLaboral());
				//ocupacion = dao.getCatalogoOpcion(Constantes.CATALOGO_OPCION_OCUPACION, ofertaEmpleoVO.getIdOcupacion());
				subarea = dao.getCatalogoSubArea( ofertaEmpleoVO.getIdSubArea());
				idiomasCert = ofertaIdiomaFacade.ofertaIdiomasList(ofertaEmpleoVO.getIdOfertaEmpleo());
				if (null != idiomasCert && !idiomasCert.isEmpty()) {
					for (OfertaIdiomaVO idiomaVO : idiomasCert) {
						int idCatCertificacion = Utils.getIdCatCertificacion((int)idiomaVO.getIdIdioma());		
						if (idiomaVO.getIdCertificacion() == Constantes.CATALOGO_OPCION_OTRO_CERTIFICACION)
							idiomaVO.setCertificacion("Se requiere una certificación del idioma");
						else if (idiomaVO.getIdCertificacion() == Constantes.CATALOGO_OPCION_NINGUNA)
								idiomaVO.setCertificacion(" ");
						else 
							idiomaVO.setCertificacion(dao.getCatalogoOpcion(idCatCertificacion, idiomaVO.getIdCertificacion()));					
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			ofertaEmpleoJB = new OfertaEmpleoJB(ofertaEmpleoVO, empresaVO, requisitos, idiomasCert,
					ubicaciones, idiomasMap, especialidades, nivelesPuesto, prestaciones, areaLaboral, subarea);
			ofertaEmpleoJB.setHabilidades(habilidades);
			if (null != ofertaEmpleoVO.getDiscapacidades()){
				String descripcionDiscapacidades = ofertaEmpleoVO.getDescripcionDiscapacidades(ofertaEmpleoVO.getDiscapacidades());
				if (TIPO_DISCAPACIDAD.NINGUNA.getOpcion().equalsIgnoreCase(descripcionDiscapacidades)){
					ofertaEmpleoJB.setDescripcionesDiscapacidades("");
				}else {
					ofertaEmpleoJB.setDescripcionesDiscapacidades(descripcionDiscapacidades);
				}					
			}else
				ofertaEmpleoJB.setDescripcionesDiscapacidades("");	
			try {
				String medioContacto = new String();
				if (ofertaEmpleoVO.getContactoCorreo() == Constantes.CONTACTO_CORREO.SI.getIdContactoCorreo()) {
					if (ofertaEmpleoVO.getCorreoElectronicoContacto()!=null && !ofertaEmpleoVO.getCorreoElectronicoContacto().isEmpty()){
						medioContacto = "Enviar CV al correo electrónico: " + ofertaEmpleoVO.getCorreoElectronicoContacto();
					}else {
						medioContacto = "Enviar CV al correo electrónico: " + empresaVO.getCorreoElectronico();
					}
				}
				if (ofertaEmpleoVO.getContactoTel() == Catalogos.CONTACTO_TELEFONO.SI.getIdContactoTelefono()) {	
					List<TelefonoVO> telefonos = telefonoFacade.getTelefonosPropietario(ofertaEmpleoVO.getIdOfertaEmpleo(),
                    Catalogos.TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
					if (null != telefonos && !telefonos.isEmpty()) {
						ofertaEmpleoJB.setTelefonos(telefonos);
						String telefonoOferta = "";									
						for (TelefonoVO tel : telefonos) {
							if (tel.getPrincipal() == Catalogos.MULTIREGISTRO.PRINCIPAL.getIdOpcion()){
								telefonoOferta = tel.getAcceso() + "-" + tel.getClave() + "-" + tel.getTelefono();
								break;
							}
						}
						ofertaEmpleoJB.setTelefonoOferta(telefonoOferta);
						if (telefonoOferta!=null && !telefonoOferta.isEmpty()) {
							if (medioContacto.isEmpty())
								medioContacto = "Contactar al teléfono: ";
							else
								medioContacto += " o contactar al teléfono: ";
							medioContacto += telefonoOferta;
						}
					}
				}	
				if (medioContacto!=null && !medioContacto.isEmpty())
					ofertaEmpleoJB.setMedioContacto(medioContacto);
			} catch (Exception e) {
				logger.error("Error al obtener el medio de contacto de la oferta "+ofertaEmpleoVO.getIdOfertaEmpleo());
				e.printStackTrace();
			}	
		} /*catch (SQLException e1) {
			logger.error(e1);
		} catch(BusinessException e){
			throw e;
		} */catch (Exception e) {
			e.printStackTrace(); logger.error(e);
		}
		return ofertaEmpleoJB;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<OfertaCandidatoVO> empresasMeBuscan(long idCandidato) throws BusinessException {
		return ofertaCandidatoFacade.empresasMeBuscan(idCandidato);
	}

	@Override
	public HashMap<String, String> getUbicacion(long idOfertaEmpleo) throws SQLException {
		return OfertaDetalleDAO.getInstance().getUbicacion(idOfertaEmpleo);
	}
	
	@Override
	public List<RegistroUbicacionVO> getUbicaciones(long idOfertaEmpleo) throws SQLException {
		return OfertaDetalleDAO.getInstance().getUbicaciones(idOfertaEmpleo);
	}

	@Override
	public HashMap<String, String> ofertaIdiomasList(long idOfertaEmpleo) throws SQLException {
		return OfertaDetalleDAO.getInstance().ofertaIdiomasList(idOfertaEmpleo);
	}

	@Override
	public List<String> ofertaEspecialidadesList(long idOfertaEmpleo, long idNivelEstudio) throws SQLException {
		return OfertaDetalleDAO.getInstance().especialidadesList(idOfertaEmpleo, idNivelEstudio);
	}

	@Override
	public String getCatalogoOpcion(long idCatalogo, long idCatalogoOpcion) throws SQLException {
		return OfertaDetalleDAO.getInstance().getCatalogoOpcion(idCatalogo, idCatalogoOpcion);
	}

	@Override
	public List<String> sectorList(long idOfertaEmpleo) throws SQLException {
		return OfertaDetalleDAO.getInstance().sectorList(idOfertaEmpleo);
	}

	@Override
	public List<String> prestacionesList(long idOfertaEmpleo) throws SQLException {
		return OfertaDetalleDAO.getInstance().prestacionesList(idOfertaEmpleo);
	}

	@Override
	public String getMunicipio(long idEntidad, long idMunicipio) throws SQLException {
		return OfertaDetalleDAO.getInstance().getMunicipio(idEntidad, idMunicipio);
	}

	@Override
	public List<OfertaCandidatoVO> findByOfferCandidate(long idOfertaEmpleo, long idCandidato) throws PersistenceException {
		return ofertaCandidatoFacade.findByOfferCandidate(idOfertaEmpleo, idCandidato);
	}
	
	public List<OfertaCandidatoVO> findCandidatesByEstatus(long idOfertaEmpleo, long estatus) throws PersistenceException {
		return ofertaCandidatoFacade.findCandidatesByEstatus(idOfertaEmpleo, estatus);
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)	
	public List<OfertaCandidatoVO> findAllOffersByCandidate(long idCandidato) throws PersistenceException {
		return ofertaCandidatoFacade.findAllOffersByCandidate(idCandidato);
	}

    //Start cambio movil
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<OfertaEmpleoJB> misOfertasEmpleo(long idCandidato,List<Catalogos.ESTATUS> listEstatus,int diasDifferencia) throws BusinessException {
		List<OfertaEmpleoJB> ofertas = new ArrayList<OfertaEmpleoJB>();

		try{
			ofertas = ofertaEmpleoFacade.consultaOfertasEmpleoAsignadas(idCandidato,listEstatus, diasDifferencia);	
		}catch(Exception e){
			e.printStackTrace(); logger.error(e);
		}

		return ofertas;
		//List<OfertaCandidatoVO> ofertas = misOfertas(idCandidato);
		//return ofertaEmpleoList(ofertas);
	}
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<OfertaEmpleoJB> empresasMeBuscanOfertas(long idCandidato,List<Catalogos.ESTATUS> listEstatus,int diasDifferencia) throws BusinessException {
		List<OfertaEmpleoJB> ofertas = new ArrayList<OfertaEmpleoJB>();

		try{
			ofertas = ofertaEmpleoFacade.consultaOfertasEmpleosAsignadas(idCandidato,listEstatus, diasDifferencia);	
		}catch(Exception e){
			e.printStackTrace(); logger.error(e);
		}

		return ofertas;
		//List<OfertaCandidatoVO> ofertas = empresasMeBuscan(idCandidato);
		//return ofertaEmpleoList(ofertas);
	}

	
	@Override
	public List<OfertaCandidatoOcupacionDTO>  obtenerOfertaCandidatoEmpresa(Long idEmpresa, List<Constantes.ESTATUS> estatus,List<Constantes.ESTATUS> estatusOferta)  throws PersistenceException{
		// 
		return ofertaCandidatoFacade.obtenerOfertaCandidatoEmpresa(idEmpresa,estatus,estatusOferta);
		
	}

	@Override
	public List<OfertaEmpresaAdminDTO> obtenerOfertasEmpresa(Long idEmpresa,
			List<Catalogos.ESTATUS> estatus, List<Catalogos.ESTATUS> estatusOferta) throws PersistenceException {
		return ofertaCandidatoFacade.obtenerOfertasEmpresa(idEmpresa, estatus, estatusOferta);
		
	}

	@Override
	public List<OfertaCandidatoOcupacionDTO> obtenerOfertaCandidatoEmpresaPorEstatus(
			Long idEmpresa,
			List<Catalogos.ESTATUS> candidatoEstatus,
			Long idOferta) {
		
		return ofertaCandidatoFacade.obtenerOfertaCandidatoEmpresaPorEstatus(idEmpresa, candidatoEstatus,idOferta);
	}
	
	@Override
	public List<OfertaCandidatoOcupacionDTO> obtenerOfertaCandidatoEmpresaPorEstatus(
			Long idEmpresa,
			List<Catalogos.ESTATUS> candidatoEstatus,
			Long idOferta, List<Catalogos.ESTATUS> ofertaEstatus) {
		
		return ofertaCandidatoFacade.obtenerOfertaCandidatoEmpresaPorEstatus(idEmpresa, candidatoEstatus,idOferta, ofertaEstatus);
	}

	
	//Fin cambio movil
	
	public int registrarPostulacionExterna(PostulacionExternaVO postulacionExternaVo, long idUsuario){
		int idPostulacionExterna = -1;
		
		long idPostulacion = postulacionExternaFacade.obtenerIdPostulacionExterna();
		postulacionExternaVo.setIdPostulacionExterna(idPostulacion);
		
		idPostulacionExterna = postulacionExternaFacade.guardarRegistro(postulacionExternaVo);	
		
		if(idPostulacionExterna > -1){
			BitacoraSiisneVO bitacoraSiisneVO = new BitacoraSiisneVO();
			bitacoraSiisneVO.setFechaOperacion(new Date());
			bitacoraSiisneVO.setFuente(Catalogos.APLICACION.PORTAL_DEL_EMPLEO.getIdOpcion());	
			bitacoraSiisneVO.setIdUsuario(idUsuario);
			bitacoraSiisneVO.setIdReferencia(postulacionExternaVo.getIdPostulacionExterna());
			bitacoraSiisneVO.setIdOperacion(COD_OPERACION_BITACORA.REGISTRAR_POSTULACION_EXTERNA.getIdOperacion());
			String info = postulacionExternaVo.getIdPostulacionExterna() + "|" + ESTATUS_POSTULACION_EXTERNA.REGISTRADO + "|";
			bitacoraSiisneVO.setInfo(info);			
			bitacoraSiisneFacade.save(bitacoraSiisneVO);
		}		
		
		return idPostulacionExterna;
	}

	@Override
	public int closePendingPostRelatedOffer(long idOfertaEmpleo) throws PersistenceException {
		return ofertaCandidatoFacade.closePendingPostRelatedOffer(idOfertaEmpleo);
	}

	@Override
	public List<PostulacionExterna> obtienePostulacionesCandidato(
			Long idCandidato) {
		List<PostulacionExterna> postulaciones = new ArrayList<PostulacionExterna>();
		postulaciones = postulacionExternaFacade.obtenerPostulacionesCandidato(idCandidato);
		return postulaciones;
	}

	@Override
	public boolean darSeguimientoPostulacionContratado(PostulacionExterna postulacion,
			List<PostulacionExterna> postulaciones) {
		boolean actualizado = postulacionExternaFacade.darSeguimientoPostulacionExternaContratado(postulacion, postulaciones);
		return actualizado;
	}

	@Override
	public boolean darSeguimientoPostulacion(PostulacionExterna postulacion) {
		boolean actualizado = postulacionExternaFacade.darSeguimientoPostulacion(postulacion);
		return actualizado;
	}

	@Override
	public boolean actualizaEstatusPPCCandidato(Long idCandidato) {
		boolean actualizado = postulacionExternaFacade.actualizaEstatusPPCCandidato(idCandidato);
		return actualizado;
	}

	@Override
	public void registraBitacora(Long idUsuario, long idPostulacionExt,
			Long idCandidato,Integer estatusPost) {
		String detalle = "idPostulacion="+idPostulacionExt+"|idCandidato="+idCandidato+"|estatus="+estatusPost;
		long now = System.currentTimeMillis();
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(now);
		bitacoraFacade.save(Catalogos.EVENTO.SEGUIMIENTO_POSTULACION_EXTERNA.getIdEvento(), idUsuario, Catalogos.EVENTO.SEGUIMIENTO_POSTULACION_EXTERNA.getEvento(), calendar, detalle, idPostulacionExt, Catalogos.TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
		
	}

	@Override
	public void actualizaPostulacionesSNE(Long idCandidato) {
		List<OfertaCandidato> ofertas = new ArrayList<OfertaCandidato>();
		ofertas = postulacionExternaFacade.encuentraPostulacionesSNE(idCandidato);
		for(OfertaCandidato o : ofertas){
			o.setEstatus(Catalogos.ESTATUS.CONTRATADO_EN_OTRA_OFERTA.getIdOpcion());
			postulacionExternaFacade.actualizaEstatusOfertasSNE(o);
		}
		
	}

	@Override
	public List<PostulacionExterna> obtenerPostulacionContratado(
			Long idCandidato) {
		List<PostulacionExterna> postulaciones = new ArrayList<PostulacionExterna>();
		postulaciones = postulacionExternaFacade.obtenerPostulacionContratado(idCandidato);
		return postulaciones;
	}
	
	@Override
	public int getPostulacionCandidate(long idCandidato, List<Long> listOfertas) throws PersistenceException {
		return ofertaCandidatoFacade.postulacionCandidatoXOferta(idCandidato, listOfertas);
	}
}