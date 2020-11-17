package mx.gob.stps.portal.core.oferta.registro.service.impl;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_OCUPACION;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import mx.gob.stps.portal.core.autorizacion.service.AutorizacionAppServiceLocal;
import mx.gob.stps.portal.core.candidate.vo.ConocimientoComputacionVO;
import mx.gob.stps.portal.core.candidate.vo.OfertaPostulacionVO;
import mx.gob.stps.portal.core.domicilio.vo.CodigoPostalVO;
import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.domicilio.vo.MunicipioVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_FUENTE_QUE_REGISTRA_OFERTA;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.EVENTO;
import mx.gob.stps.portal.core.infra.utils.Constantes.GRADO_ESTUDIOS;
import mx.gob.stps.portal.core.infra.utils.Constantes.SUBPROGRAMA;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_REGISTRO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_REQUISITO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_USUARIO;
import mx.gob.stps.portal.core.infra.utils.Constantes.VIGENCIA_OFERTA;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.oferta.busqueda.dao.OfertasPorCanalDAO;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaRequisitoVO;
import mx.gob.stps.portal.core.oferta.registro.service.OfertaAppServiceRemote;
import mx.gob.stps.portal.core.oferta.registro.vo.EventoVO;
import mx.gob.stps.portal.core.oferta.registro.vo.OfertaCarreraEspecialidadVO;
import mx.gob.stps.portal.core.oferta.registro.vo.OfertaIdiomaVO;
import mx.gob.stps.portal.core.oferta.registro.vo.RegistroContactoVO;
import mx.gob.stps.portal.core.oferta.registro.vo.RegistroEntidadesVO;
import mx.gob.stps.portal.core.oferta.registro.vo.RegistroRequisitosVO;
import mx.gob.stps.portal.core.oferta.registro.vo.RegistroUbicacionVO;
import mx.gob.stps.portal.core.oferta.registro.vo.RequisitoVO;
import mx.gob.stps.portal.core.oferta.reporte.vo.ReporteOfertasEmpresaVO;
import mx.gob.stps.portal.core.oferta.vo.BusquedaOfertasVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.core.oferta.vo.ResultadoBusquedaOfertasVO;
import mx.gob.stps.portal.core.persistencia.facade.BitacoraFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.CatalogoOpcionFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.ConocimientoComputacionFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.DomicilioFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.EmpresaFuncionPublicaFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.ModalidadOfertaFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.OfertaCandidatoFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.OfertaEmpleoBecateFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.OfertaEmpleoFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.OfertaFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.TelefonoFacadeLocal;
import mx.gob.stps.portal.core.search.exception.IndexerException;
import mx.gob.stps.portal.persistencia.entity.NotificacionCandidato;
import mx.gob.stps.portal.persistencia.vo.CatSubareaVO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.persistencia.vo.ModalidadOfertaVO;
import mx.gob.stps.portal.persistencia.vo.ModalidadVO;
import mx.gob.stps.portal.persistencia.vo.OfertaEmpleoBecateVO;
import mx.gob.stps.portal.persistencia.vo.OfertaFuncionVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.utils.ConstantesGenerales;

import org.apache.log4j.Logger;

/**
 * Session Bean implementation class AutorizacionAppService
 */
@Stateless(name = "OfertaAppService", mappedName = "OfertaAppService")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class OfertaAppService implements OfertaAppServiceRemote {

	private static Logger logger = Logger.getLogger(OfertaAppService.class);

	@EJB
	private OfertaFacadeLocal ofertaFacade;

	@EJB
	private OfertaEmpleoFacadeLocal ofertaEmpleoFacade;

	@EJB
	private CatalogoOpcionFacadeLocal catalogoOpcionFacadelocal;

	@EJB
	private AutorizacionAppServiceLocal autorizacionAppService;
	
	@EJB
	private BitacoraFacadeLocal bitacoraFacade;

	@EJB
	private OfertaCandidatoFacadeLocal ofertaCandidatoFacade;

	@EJB
	private DomicilioFacadeLocal domicilioFacade;

	@EJB
	private TelefonoFacadeLocal telefonoFacade;
	@EJB
	private ConocimientoComputacionFacadeLocal conocimientoComputacionFacade;
	
	@EJB
	private EmpresaFuncionPublicaFacadeLocal EmpresaFuncionPublicaFacade;
	
	@EJB
	private OfertaEmpleoBecateFacadeLocal ofertaEmpleoBecateFacade;
	
	@EJB
	private ModalidadOfertaFacadeLocal modalidadOfertaFacade;

	//Solo buscar los datos completos de los ofertas activos y si no es activo regresa null
	public OfertaEmpleoVO consultaOfertaEmpleoActiva(long idOfertaEmpleo) {
		if (idOfertaEmpleo<=0) throw new IllegalArgumentException("Identificador de oferta de empleo requerido.");
		
		OfertaEmpleoVO oferta = ofertaFacade.find(idOfertaEmpleo);

		if (oferta!=null){
			if(oferta.getEstatus() == Catalogos.ESTATUS.ACTIVO.getIdOpcion()){
				oferta.setOcupacionDescripcion(ofertaFacade.obtenerOcupacion(idOfertaEmpleo));

				DomicilioVO domicilio = domicilioFacade.buscarDomicilioIdPropietario(idOfertaEmpleo, TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
				oferta.setDomicilio(domicilio);
			
				List<TelefonoVO> telefonos = telefonoFacade.getTelefonosPropietario(idOfertaEmpleo, TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
				oferta.setTelefonos(telefonos);
			} else 
				return null;
		}

		return oferta;
	}
	
	public OfertaEmpleoVO consultaOfertaEmpleo(long idOfertaEmpleo) {
		if (idOfertaEmpleo<=0) throw new IllegalArgumentException("Identificador de oferta de empleo requerido.");
		OfertaEmpleoVO oferta = ofertaFacade.find(idOfertaEmpleo);
		if (null != oferta) {
			//oferta.setOcupacionDescripcion(ofertaFacade.obtenerOcupacion(idOfertaEmpleo));
			CatSubareaVO subarea = catalogoOpcionFacadelocal.getSubAreaVOByIdAreaIdSubArea(oferta.getIdArea(), oferta.getIdSubArea());
			oferta.setSubAreaLaboralDescripcion(null != subarea.getDescripcion() ? subarea.getDescripcion() :"");
			DomicilioVO domicilio = domicilioFacade.buscarDomicilioIdPropietario(idOfertaEmpleo, TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
			oferta.setDomicilio(domicilio);
			List<TelefonoVO> telefonos = telefonoFacade.getTelefonosPropietario(idOfertaEmpleo, TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
			oferta.setTelefonos(telefonos);
		}
		return oferta;
	}

	public OfertaEmpleoVO consultaOfertaPush(long idOfertaEmpleo) {
		if (idOfertaEmpleo<=0) throw new IllegalArgumentException("Identificador de oferta de empleo requerido.");
		
		OfertaEmpleoVO oferta = ofertaFacade.find(idOfertaEmpleo);

		
		return oferta;
	}
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)	
	public long registraOfertaEmpleo(OfertaEmpleoVO ofertaEmpleo) throws BusinessException {

		if (ofertaEmpleo==null) throw new IllegalArgumentException("Oferta de empleo requerida");
		if (ofertaEmpleo.getIdEmpresa()<=0) throw new IllegalArgumentException("Identificador de la Empresa es requerido.");
		if (ofertaEmpleo.getIdEntidadUbicacion()<=0 || ofertaEmpleo.getIdMunicipioUbicacion()<=0) throw new IllegalArgumentException("Ubicación de la Empresa es requerida.");
		Calendar fecha = Calendar.getInstance();
		Date fechaAlta = fecha.getTime();

		OfertaEmpleoVO ofertaInsert = new OfertaEmpleoVO();
		//ofertaUpdate.setIdOfertaEmpleo		(idOfertaEmpleo);
		ofertaInsert.setPublicarOfertas			(ofertaEmpleo.getPublicarOfertas());
		ofertaInsert.setPlazasCubiertas			(ofertaEmpleo.getPlazasCubiertas());
		ofertaInsert.setIdUsuario				(ofertaEmpleo.getIdUsuario());
		ofertaInsert.setIdEmpresa               (ofertaEmpleo.getIdEmpresa());
		boolean esEmpresaFP = esOfertaEmpresaFP(ofertaInsert.getIdEmpresa());
		
		ofertaInsert.setTituloOferta			(ofertaEmpleo.getTituloOferta());
		ofertaInsert.setIdAreaLaboral			(ofertaEmpleo.getIdAreaLaboral());
		ofertaInsert.setIdOcupacion				(ofertaEmpleo.getIdOcupacion());
		ofertaInsert.setFunciones				(ofertaEmpleo.getFunciones());
		ofertaInsert.setDiasLaborales			(ofertaEmpleo.getDiasLaborales());
		ofertaInsert.setHoraEntrada				(ofertaEmpleo.getHoraEntrada());
		ofertaInsert.setHoraSalida				(ofertaEmpleo.getHoraSalida());
		ofertaInsert.setRolarTurno				(ofertaEmpleo.getRolarTurno());
		ofertaInsert.setEmpresaOfrece			(ofertaEmpleo.getEmpresaOfrece());
		ofertaInsert.setSalario					(ofertaEmpleo.getSalario());
		ofertaInsert.setIdTipoContrato			(ofertaEmpleo.getIdTipoContrato());
		ofertaInsert.setIdJerarquia				(ofertaEmpleo.getIdJerarquia());
		ofertaInsert.setNumeroPlazas			(ofertaEmpleo.getNumeroPlazas());
		ofertaInsert.setLimitePostulantes		(ofertaEmpleo.getLimitePostulantes());
		ofertaInsert.setIdDiscapacidad			(ofertaEmpleo.getIdDiscapacidad());
		ofertaInsert.setIdCausaVacante			(ofertaEmpleo.getIdCausaVacante());
		ofertaInsert.setFechaInicio				(fechaAlta);
		ofertaInsert.setFechaFin				(ofertaEmpleo.getFechaFin());
		ofertaInsert.setDisponibilidadViajar	(ofertaEmpleo.getDisponibilidadViajar());
		ofertaInsert.setDisponibilidadRadicar	(ofertaEmpleo.getDisponibilidadRadicar());
		ofertaInsert.setIdNivelEstudio			(ofertaEmpleo.getIdNivelEstudio());
		ofertaInsert.setIdSituacionAcademica	(ofertaEmpleo.getIdSituacionAcademica());
//		ofertaInsert.setHabilidadGeneral		(ofertaEmpleo.getHabilidadGeneral());
		ofertaInsert.setExperienciaAnios		(ofertaEmpleo.getExperienciaAnios());
		ofertaInsert.setGenero					(ofertaEmpleo.getGenero());
		ofertaInsert.setMapaUbicacion			(ofertaEmpleo.getMapaUbicacion());
		ofertaInsert.setObservaciones			(ofertaEmpleo.getObservaciones());
		ofertaInsert.setIdTerceraEmpresa		(ofertaEmpleo.getIdTerceraEmpresa());
		ofertaInsert.setIdContacto				(ofertaEmpleo.getIdContacto());
		ofertaInsert.setIdHorarioDe				(ofertaEmpleo.getIdHorarioDe());
		ofertaInsert.setIdHorarioA				(ofertaEmpleo.getIdHorarioA());
		ofertaInsert.setIdDuracionAproximada	(ofertaEmpleo.getIdDuracionAproximada());
		ofertaInsert.setDiasEntrevista			(ofertaEmpleo.getDiasEntrevista());
		ofertaInsert.setContactoTel				(ofertaEmpleo.getContactoTel());
		ofertaInsert.setContactoCorreo			(ofertaEmpleo.getContactoCorreo());
		ofertaInsert.setIdTipoEmpleo			(ofertaEmpleo.getIdTipoEmpleo());
		ofertaInsert.setNombreEmpresa			(ofertaEmpleo.getNombreEmpresa());
		ofertaInsert.setNombreContacto			(ofertaEmpleo.getNombreContacto());
		ofertaInsert.setIdActividadEconomica	(ofertaEmpleo.getIdActividadEconomica());
		ofertaInsert.setCargoContacto			(ofertaEmpleo.getCargoContacto());
		ofertaInsert.setCorreoElectronicoContacto(ofertaEmpleo.getCorreoElectronicoContacto());
		if(esEmpresaFP)
			ofertaInsert.setFuente                  (Catalogos.CATALOGO_FUENTE_QUE_REGISTRA_OFERTA.SFP.getIdOpcion());
		else
			ofertaInsert.setFuente                  (ofertaEmpleo.getFuente());	
		
		if(ofertaEmpleo.getEdadMinima() > 0)
			ofertaInsert.setEdadRequisito			(Catalogos.EDAD_REQUISITO.SI.getIdOpcion());
		else
			ofertaInsert.setEdadRequisito			(Catalogos.EDAD_REQUISITO.NO.getIdOpcion());
		
		ofertaInsert.setEdadMinima				(ofertaEmpleo.getEdadMinima());
		ofertaInsert.setEdadMaxima				(ofertaEmpleo.getEdadMaxima());			
		
		ofertaInsert.setFechaAlta				(fechaAlta);
		ofertaInsert.setFechaModificacion		(fechaAlta);
		if(ofertaEmpleo.isOfertaBecate()){
			ofertaInsert.setEstatus				(ESTATUS.MODALIDAD_PROCESO_VALIDACION.getIdOpcion());    //estatus oferta becate
		}else{
			ofertaInsert.setEstatus				(ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion());
		}
		ofertaInsert.setContactoDomicilio		(ofertaEmpleo.getContactoDomicilio());
		ofertaInsert.setIdVigenciaOferta		(ofertaEmpleo.getIdVigenciaOferta());

		ofertaInsert.setDiscapacidades			(ofertaEmpleo.getDiscapacidades());
		if(esEmpresaFP){
			ofertaInsert.setDiscapacidadAuditiva(true);
		}
		
		
		ofertaInsert.setCodigo_universal_de_puesto_sfp(ofertaEmpleo.getCodigo_universal_de_puesto_sfp());
		
		long idOfertaEmpleo = ofertaFacade.save(ofertaInsert);
		ofertaInsert.setIdOfertaEmpleo(idOfertaEmpleo);

		DomicilioVO ofertaDomicilio = ofertaEmpleo.getDomicilio();

		if (ofertaDomicilio!=null){
			ofertaDomicilio.setIdPropietario		(idOfertaEmpleo);
			ofertaDomicilio.setIdTipoPropietario	(TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
			ofertaDomicilio.setFechaAlta			(fechaAlta);
			domicilioFacade.save(ofertaDomicilio);
		}
		ofertaFacade.registraOfertaUbicacion(idOfertaEmpleo, ofertaEmpleo.getIdEntidadUbicacion(), ofertaEmpleo.getIdMunicipioUbicacion(), fechaAlta);
		// REQUISITOS --------------------------------------------------------------------------------------
		List<OfertaRequisitoVO> conocimientos = ofertaEmpleo.getConocimientos();
		for (OfertaRequisitoVO conocimiento : conocimientos){
			ofertaFacade.registraOfertaRequisito(idOfertaEmpleo, TIPO_REQUISITO.CONOCIMIENTO.getIdTipoRequisito(),
					conocimiento.getDescripcion(), conocimiento.getIdExperiencia(),
					conocimiento.getIdDominio(), conocimiento.getPrincipal(), fechaAlta);	
		}

		List<OfertaRequisitoVO> habilidades = ofertaEmpleo.getHabilidades();
		for (OfertaRequisitoVO habilidad : habilidades){
			ofertaFacade.registraOfertaRequisito(idOfertaEmpleo, TIPO_REQUISITO.HABILIDAD.getIdTipoRequisito(),
					habilidad.getDescripcion(), habilidad.getIdExperiencia(),
					habilidad.getIdDominio(), habilidad.getPrincipal(), fechaAlta);	
		}

		List<OfertaRequisitoVO> competencias = ofertaEmpleo.getCompetencias();
		for (OfertaRequisitoVO competencia : competencias){
			ofertaFacade.registraOfertaRequisito(idOfertaEmpleo, TIPO_REQUISITO.COMPETENCIA.getIdTipoRequisito(),
					competencia.getDescripcion(), competencia.getIdExperiencia(),
					competencia.getIdDominio(), competencia.getPrincipal(), fechaAlta);	
		}

		long[] idsHabilidades = ofertaEmpleo.getIdHabilidad();
		if (idsHabilidades!=null){
			for (long idHabilidad : idsHabilidades){
				ofertaFacade.registraHabilidad(idOfertaEmpleo, idHabilidad);
			}
		}
		
		List<OfertaIdiomaVO> idiomas = ofertaEmpleo.getIdiomas();
		for (OfertaIdiomaVO idioma : idiomas){
			ofertaFacade.registraOfertaIdioma(idOfertaEmpleo, idioma.getIdIdioma(), idioma.getIdCertificacion(),
					idioma.getIdDominio(), fechaAlta, idioma.getPrincipal());
		}

		List<OfertaCarreraEspecialidadVO> carreras = ofertaEmpleo.getCarreras();
		for (OfertaCarreraEspecialidadVO carrera : carreras){
			ofertaFacade.registraOfertaCarrera(idOfertaEmpleo, carrera.getId(), carrera.getPrincipal(), fechaAlta);
		}

		List<Long> idsPrestaciones = ofertaEmpleo.getPrestaciones();
		for (Long idPrestacion : idsPrestaciones){
			ofertaFacade.registraOfertaPrestacion(idOfertaEmpleo, idPrestacion, fechaAlta);
		}

		List<TelefonoVO> telefonos = ofertaEmpleo.getTelefonos();
		for (TelefonoVO telefono : telefonos){
			telefono.setIdPropietario(idOfertaEmpleo);
			telefono.setFechaAlta(fechaAlta);
			telefonoFacade.save(telefono);
		}

		ConocimientoComputacionVO conocimientoComputacionVO = ofertaEmpleo.getConocimientoComputacionVO();

		if(conocimientoComputacionVO!=null){

			conocimientoComputacionVO.setIdPropietario(idOfertaEmpleo);
			conocimientoComputacionVO.setIdTipoPropietario(TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
			conocimientoComputacionFacade.registraConocimientosComputacion(conocimientoComputacionVO.getIdPropietario(), (int) conocimientoComputacionVO.getIdTipoPropietario(), conocimientoComputacionVO.getProcesadorTxt(), conocimientoComputacionVO.getHojaCalculo(), conocimientoComputacionVO.getInternet(), conocimientoComputacionVO.getRedesSociales(), conocimientoComputacionVO.getOtros());
		}		

		
		//RBM1 TK990 TK995  este es el paso que hace falta
		autorizacionAppService.registraOfertaPorValidar(idOfertaEmpleo, ofertaEmpleo.getIdEmpresa());
		
		//Becate
		if (ofertaEmpleo.isOfertaBecate()){
			 registrarOfertaBecate(ofertaEmpleo, idOfertaEmpleo);
		}
		
		ofertaFacade.validaOferta(idOfertaEmpleo);
		
		return idOfertaEmpleo;
	}
	
	private void registrarOfertaBecate(OfertaEmpleoVO ofertaEmpleo, long idOfertaEmpleo) {
		OfertaEmpleoBecateVO ofertaEmpleoBecateVO = ofertaEmpleo.getOfertaEmpleoBecate();
		ModalidadOfertaVO modalidadOfertaVO = new ModalidadOfertaVO();

		if (ofertaEmpleoBecateVO != null) {
			ofertaEmpleoBecateVO.setIdOfertaEmpleo(idOfertaEmpleo);
			ofertaEmpleoBecateFacade.registrarOferta(ofertaEmpleoBecateVO);
			//
			modalidadOfertaVO.setIdOfertaEmpleo(idOfertaEmpleo);
			modalidadOfertaVO.setIdModalidad((long) ofertaEmpleo.getIdModalidad());		
			modalidadOfertaVO.setEstatus(ESTATUS.MODALIDAD_PENDIENTE_VALIDAR_UR.getIdOpcion());	
			modalidadOfertaVO.setFechaAlta(new Date());	
			modalidadOfertaVO.setIdUsuario(ofertaEmpleo.getIdUsuario());
			modalidadOfertaVO.setIdSubprograma((long) SUBPROGRAMA.BECATE.getIdOpcion());
			modalidadOfertaVO.setIdOficina(ConstantesGenerales.PORTAL_ID_OFICINA);
			modalidadOfertaVO.setFechaModificacion(new Date());				
			modalidadOfertaFacade.registrar(modalidadOfertaVO);
		}
	}

	public boolean esOfertaEmpresaFP(long idEmpresa){
		boolean ofertaEmpresaFuncionPublica = false;
		
		try{
			ofertaEmpresaFuncionPublica = EmpresaFuncionPublicaFacade.esEmpresaFuncionPublica(idEmpresa);
		} catch(PersistenceException pe){
			pe.printStackTrace();
			logger.info("Error al buscar Empresas FuncionPublica --" + pe.getMessage());
		}
		return ofertaEmpresaFuncionPublica;
	}

	public long editaOfertaEmpleo(OfertaEmpleoVO ofertaEmpleo, long idTipoUsuario, long idUsuario, long idRegValidar) throws BusinessException, TechnicalException, IndexerException {

		if (ofertaEmpleo==null) throw new IllegalArgumentException("Oferta de empleo requerida");
		if (ofertaEmpleo.getIdOfertaEmpleo()<=0) throw new IllegalArgumentException("Identificador de Oferta de Empleo invalido.");
		if (ofertaEmpleo.getIdEmpresa()<=0) throw new IllegalArgumentException("Identificador de la Empresa es requerido.");
		
		long idOfertaEmpleo = ofertaEmpleo.getIdOfertaEmpleo();
		long idEmpresa = ofertaEmpleo.getIdEmpresa();
		int estatus = 0;
		
		Calendar fecha = Calendar.getInstance();
		Date fechaModificacion = fecha.getTime();
		Date fechaAlta = fecha.getTime();
				
		OfertaEmpleoVO ofertaUpdate = new OfertaEmpleoVO();
		ofertaUpdate.setIdOfertaEmpleo			(idOfertaEmpleo);
		ofertaUpdate.setIdEmpresa				(idEmpresa);
		ofertaUpdate.setFechaAlta				(fechaAlta);
		ofertaUpdate.setFechaModificacion		(fechaModificacion);

		if(ofertaEmpleo.getFuente() == Constantes.CATALOGO_FUENTE_QUE_REGISTRA_OFERTA.SISNE.getIdOpcion() ||
		   idTipoUsuario == (long)TIPO_USUARIO.ADMINISTRADOR.getIdTipoUsuario()){
			
			estatus = ESTATUS.ACTIVO.getIdOpcion();
			
		} else if(ofertaEmpleo.getFuente() == Constantes.CATALOGO_FUENTE_QUE_REGISTRA_OFERTA.ABRIENDO_ESPACIOS.getIdOpcion() &&
			  	  idTipoUsuario == (long)TIPO_USUARIO.ADMINISTRADOR.getIdTipoUsuario()){
				
			estatus = ESTATUS.ABRIENDO_ESPACIOS_ACTIVO.getIdOpcion();
			
		} else if(ofertaEmpleo.getFuente() == Constantes.CATALOGO_FUENTE_QUE_REGISTRA_OFERTA.PORTAL.getIdOpcion() &&
				  idTipoUsuario == (long)TIPO_USUARIO.ADMINISTRADOR.getIdTipoUsuario()){
			
			estatus = ESTATUS.ACTIVO.getIdOpcion();
			
		} else {
			estatus = ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion();
		}
		if(ofertaEmpleo.isOfertaBecate()){
			estatus = ESTATUS.MODALIDAD_PROCESO_VALIDACION.getIdOpcion();    //estatus oferta becate
		}
		
		logger.info("OfertaAppService.editaOfertaEmpleo.estatus:" + estatus);

		ofertaUpdate.setEstatus					(estatus);
		ofertaUpdate.setTituloOferta			(ofertaEmpleo.getTituloOferta());
		ofertaUpdate.setIdAreaLaboral			(ofertaEmpleo.getIdAreaLaboral());
		ofertaUpdate.setIdOcupacion				(ofertaEmpleo.getIdOcupacion());
		ofertaUpdate.setFunciones				(ofertaEmpleo.getFunciones());
		ofertaUpdate.setDiasLaborales			(ofertaEmpleo.getDiasLaborales());
		ofertaUpdate.setHoraEntrada				(ofertaEmpleo.getHoraEntrada());
		ofertaUpdate.setHoraSalida				(ofertaEmpleo.getHoraSalida());
		ofertaUpdate.setRolarTurno				(ofertaEmpleo.getRolarTurno());
		ofertaUpdate.setEmpresaOfrece			(ofertaEmpleo.getEmpresaOfrece());
		ofertaUpdate.setSalario					(ofertaEmpleo.getSalario());
		ofertaUpdate.setIdTipoContrato			(ofertaEmpleo.getIdTipoContrato());
		ofertaUpdate.setIdJerarquia				(ofertaEmpleo.getIdJerarquia());
		ofertaUpdate.setNumeroPlazas			(ofertaEmpleo.getNumeroPlazas());
		ofertaUpdate.setLimitePostulantes		(ofertaEmpleo.getLimitePostulantes());
		ofertaUpdate.setIdDiscapacidad			(ofertaEmpleo.getIdDiscapacidad());
		ofertaUpdate.setDiscapacidades			(ofertaEmpleo.getDiscapacidades());
		ofertaUpdate.setIdCausaVacante			(ofertaEmpleo.getIdCausaVacante());		
		ofertaUpdate.setFechaInicio				(ofertaEmpleo.getFechaInicio());
		ofertaUpdate.setFechaFin				(ofertaEmpleo.getFechaFin());		
		ofertaUpdate.setDisponibilidadViajar	(ofertaEmpleo.getDisponibilidadViajar());
		ofertaUpdate.setDisponibilidadRadicar	(ofertaEmpleo.getDisponibilidadRadicar());
		ofertaUpdate.setIdNivelEstudio			(ofertaEmpleo.getIdNivelEstudio());
		ofertaUpdate.setIdSituacionAcademica	(ofertaEmpleo.getIdSituacionAcademica());
//		ofertaUpdate.setHabilidadGeneral		(ofertaEmpleo.getHabilidadGeneral());
		ofertaUpdate.setExperienciaAnios		(ofertaEmpleo.getExperienciaAnios());
		ofertaUpdate.setEdadRequisito			(ofertaEmpleo.getEdadRequisito());
		ofertaUpdate.setEdadMinima				(ofertaEmpleo.getEdadMinima());
		ofertaUpdate.setEdadMaxima				(ofertaEmpleo.getEdadMaxima());
		ofertaUpdate.setGenero					(ofertaEmpleo.getGenero());
		ofertaUpdate.setMapaUbicacion			(ofertaEmpleo.getMapaUbicacion());
		ofertaUpdate.setObservaciones			(ofertaEmpleo.getObservaciones());
		ofertaUpdate.setIdTerceraEmpresa		(ofertaEmpleo.getIdTerceraEmpresa());
		ofertaUpdate.setIdContacto				(ofertaEmpleo.getIdContacto());
		ofertaUpdate.setIdHorarioDe				(ofertaEmpleo.getIdHorarioDe());
		ofertaUpdate.setIdHorarioA				(ofertaEmpleo.getIdHorarioA());
		ofertaUpdate.setIdDuracionAproximada	(ofertaEmpleo.getIdDuracionAproximada());
		ofertaUpdate.setDiasEntrevista			(ofertaEmpleo.getDiasEntrevista());
		ofertaUpdate.setFuente					(ofertaEmpleo.getFuente());
		ofertaUpdate.setContactoTel				(ofertaEmpleo.getContactoTel());
		ofertaUpdate.setContactoCorreo			(ofertaEmpleo.getContactoCorreo());
		ofertaUpdate.setIdTipoEmpleo			(ofertaEmpleo.getIdTipoEmpleo());
		ofertaUpdate.setNombreEmpresa			(ofertaEmpleo.getNombreEmpresa());
		ofertaUpdate.setNombreContacto			(ofertaEmpleo.getNombreContacto());
		ofertaUpdate.setIdActividadEconomica	(ofertaEmpleo.getIdActividadEconomica());
		ofertaUpdate.setCargoContacto			(ofertaEmpleo.getCargoContacto());
		ofertaUpdate.setCorreoElectronicoContacto(ofertaEmpleo.getCorreoElectronicoContacto());
		ofertaUpdate.setContactoDomicilio		(ofertaEmpleo.getContactoDomicilio());
		ofertaUpdate.setIdVigenciaOferta	(ofertaEmpleo.getIdVigenciaOferta());
		ofertaFacade.update(ofertaUpdate);

		// Becate
		if (ofertaEmpleo.isOfertaBecate()) {
			OfertaEmpleoBecateVO ofertaBecateUpdate = ofertaEmpleo.getOfertaEmpleoBecate();
			ModalidadOfertaVO modalidadOfertaUpdate = ofertaEmpleo.getModalidadOferta();

			if (ofertaBecateUpdate != null) {
				ofertaBecateUpdate.setIdOfertaEmpleo(idOfertaEmpleo);
				ofertaEmpleoBecateFacade.update(ofertaBecateUpdate);
			}
			if (modalidadOfertaUpdate!=null){
				modalidadOfertaUpdate.setIdOfertaEmpleo(idOfertaEmpleo);				
				modalidadOfertaUpdate.setIdModalidad((long) ofertaEmpleo.getIdModalidad());
				modalidadOfertaUpdate.setFechaModificacion(new Date());
				modalidadOfertaFacade.update(modalidadOfertaUpdate);
			}
		}
			

		DomicilioVO ofertaDomicilio = ofertaEmpleo.getDomicilio();

		if (ofertaDomicilio!=null){
			//Domicilio domicilio = obtenerDomicilioExistente(idOfertaEmpleo);
			DomicilioVO domicilio = ofertaFacade.obtenerDomicilio(idOfertaEmpleo);

			if (domicilio!=null && domicilio.getIdDomicilio()>0){
				logger.info("OfertaAppService.editaOfertaEmpleo.domicilio:" + domicilio.toString());								
				domicilio.setCalle			(ofertaDomicilio.getCalle());
				domicilio.setCodigoPostal	(ofertaDomicilio.getCodigoPostal());
				domicilio.setEntreCalle		(ofertaDomicilio.getEntreCalle());
				domicilio.setIdColonia		(ofertaDomicilio.getIdColonia());
				domicilio.setIdEntidad		(ofertaDomicilio.getIdEntidad());
				domicilio.setIdMunicipio	(ofertaDomicilio.getIdMunicipio());
				domicilio.setNumeroExterior (ofertaDomicilio.getNumeroExterior());
				domicilio.setNumeroInterior (ofertaDomicilio.getNumeroInterior());
				domicilio.setyCalle         (ofertaDomicilio.getyCalle());
				domicilio.setIdTipoPropietario(TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
				domicilio.setIdPropietario(idOfertaEmpleo);
				domicilioFacade.update(domicilio);

			} else {
				logger.info("OfertaAppService.editaOfertaEmpleo.ofertaDomicilio:" + ofertaDomicilio.toString());
				ofertaDomicilio.setIdPropietario(idOfertaEmpleo);
				ofertaDomicilio.setIdTipoPropietario(TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
				ofertaDomicilio.setFechaAlta(fechaAlta);
				domicilioFacade.save(ofertaDomicilio);
			}
		}

		ofertaFacade.eliminaRequisitos(idOfertaEmpleo);		

		List<OfertaRequisitoVO> conocimientos = ofertaEmpleo.getConocimientos();
		for (OfertaRequisitoVO conocimiento : conocimientos){
			ofertaFacade.registraOfertaRequisito(idOfertaEmpleo, TIPO_REQUISITO.CONOCIMIENTO.getIdTipoRequisito(),
					conocimiento.getDescripcion(), conocimiento.getIdExperiencia(),
					conocimiento.getIdDominio(), conocimiento.getPrincipal(), fechaAlta);	
		}

		List<OfertaRequisitoVO> habilidades = ofertaEmpleo.getHabilidades();
		for (OfertaRequisitoVO habilidad : habilidades){
			ofertaFacade.registraOfertaRequisito(idOfertaEmpleo, TIPO_REQUISITO.HABILIDAD.getIdTipoRequisito(),
					habilidad.getDescripcion(), habilidad.getIdExperiencia(),
					habilidad.getIdDominio(), habilidad.getPrincipal(), fechaAlta);	
		}

		long[] idsHabilidades = ofertaEmpleo.getIdHabilidad();
		if (idsHabilidades!=null){
			ofertaFacade.eliminaHabilidades(idOfertaEmpleo);

			for (long idHabilidad : idsHabilidades){
				ofertaFacade.registraHabilidad(idOfertaEmpleo, idHabilidad);
			}
		}
		
		List<OfertaRequisitoVO> competencias = ofertaEmpleo.getCompetencias();
		for (OfertaRequisitoVO competencia : competencias){
			ofertaFacade.registraOfertaRequisito(idOfertaEmpleo, TIPO_REQUISITO.COMPETENCIA.getIdTipoRequisito(),
					competencia.getDescripcion(), competencia.getIdExperiencia(),
					competencia.getIdDominio(), competencia.getPrincipal(), fechaAlta);	
		}

		ofertaFacade.eliminaIdiomas(idOfertaEmpleo);

		List<OfertaIdiomaVO> idiomas = ofertaEmpleo.getIdiomas();
		for (OfertaIdiomaVO idioma : idiomas){
			ofertaFacade.registraOfertaIdioma(idOfertaEmpleo, idioma.getIdIdioma(), idioma.getIdCertificacion(),
					idioma.getIdDominio(), fechaAlta, idioma.getPrincipal());
		}

		ofertaFacade.eliminaCarreras(idOfertaEmpleo);

		List<OfertaCarreraEspecialidadVO> carreras = ofertaEmpleo.getCarreras();
		for (OfertaCarreraEspecialidadVO carrera : carreras){
			ofertaFacade.registraOfertaCarrera(idOfertaEmpleo, carrera.getId(), carrera.getPrincipal(), fechaAlta);
		}
		if (ofertaEmpleo.getIdEntidadUbicacion()>0 && ofertaEmpleo.getIdMunicipioUbicacion()>0) {
			ofertaFacade.eliminaUbicaciones(idOfertaEmpleo);
			ofertaFacade.registraOfertaUbicacion(idOfertaEmpleo, ofertaEmpleo.getIdEntidadUbicacion(), ofertaEmpleo.getIdMunicipioUbicacion(), fechaAlta);
		}else {
			throw new BusinessException("La oferta de empleo " + ofertaEmpleo.getIdOfertaEmpleo() + " no contiene ubicación");
		}

		ofertaFacade.eliminaPrestaciones(idOfertaEmpleo);

		List<Long> idsPrestaciones = ofertaEmpleo.getPrestaciones();
		for (Long idPrestacion : idsPrestaciones){
			ofertaFacade.registraOfertaPrestacion(idOfertaEmpleo, idPrestacion, fechaAlta);
		}

		List<TelefonoVO> telefonosEliminar = telefonoFacade.getTelefonosPropietario(idOfertaEmpleo, TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
		for (TelefonoVO telefono : telefonosEliminar){

			telefonoFacade.delete(telefono);
		}

		List<TelefonoVO> telefonos = ofertaEmpleo. getTelefonos();
		for (TelefonoVO telefono : telefonos){
			telefono.setIdPropietario(idOfertaEmpleo);
			telefono.setFechaAlta(fechaAlta);
			telefonoFacade.save(telefono);
		}

		ConocimientoComputacionVO ofertaConocimientosComputacionVO = ofertaEmpleo.getConocimientoComputacionVO();

		if(ofertaConocimientosComputacionVO!=null){

			ConocimientoComputacionVO conocimientoComputacionVO = conocimientoComputacionFacade.consultaConocimientosComputacion(idOfertaEmpleo, TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
			if(conocimientoComputacionVO!=null){
				conocimientoComputacionFacade.actualizarConocimientosComputacion(conocimientoComputacionVO.getIdConocimientoComputacion(), conocimientoComputacionVO.getIdPropietario(), (int) conocimientoComputacionVO.getIdTipoPropietario(), ofertaConocimientosComputacionVO.getProcesadorTxt(), ofertaConocimientosComputacionVO.getHojaCalculo(), ofertaConocimientosComputacionVO.getInternet(), ofertaConocimientosComputacionVO.getRedesSociales(), ofertaConocimientosComputacionVO.getOtros());

			}
			else{

				ofertaConocimientosComputacionVO.setIdPropietario(idOfertaEmpleo);
				ofertaConocimientosComputacionVO.setIdTipoPropietario(TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
				conocimientoComputacionFacade.registraConocimientosComputacion(ofertaConocimientosComputacionVO.getIdPropietario(), (int) ofertaConocimientosComputacionVO.getIdTipoPropietario(), ofertaConocimientosComputacionVO.getProcesadorTxt(), ofertaConocimientosComputacionVO.getHojaCalculo(), ofertaConocimientosComputacionVO.getInternet(), ofertaConocimientosComputacionVO.getRedesSociales(), ofertaConocimientosComputacionVO.getOtros());

			}
		}

		//----------------------------------------------------------------------------------------------------------------------------------------
		
		if(ofertaEmpleo.getFuente() == CATALOGO_FUENTE_QUE_REGISTRA_OFERTA.SISNE.getIdOpcion()){
			bitacoraFacade.save(EVENTO.MODIFICA_OFERTA_EMPLEO.getIdEvento(), idUsuario,
								EVENTO.MODIFICA_OFERTA_EMPLEO.getEvento(), Calendar.getInstance(), 
								null, idOfertaEmpleo, TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
		} else {
			if (idTipoUsuario != (long)TIPO_USUARIO.ADMINISTRADOR.getIdTipoUsuario()){
				autorizacionAppService.registraOfertaPorValidar(idOfertaEmpleo, idEmpresa);
			} else {

				bitacoraFacade.save(EVENTO.MODIFICA_OFERTA_EMPLEO.getIdEvento(), idUsuario,
									EVENTO.MODIFICA_OFERTA_EMPLEO.getEvento(), Calendar.getInstance(), 
									null, idOfertaEmpleo, TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());

				/** La autorizacion establece a la oferta como ACTIVA **/
				autorizacionAppService.autorizaOfertaEmpleo(idOfertaEmpleo, idUsuario, idRegValidar);
			}
		}

		return idOfertaEmpleo;
	}

	public void actualizaHabilidades(long idOfertaEmpleo, long[] idsHabilidades){
		try{
			if (idOfertaEmpleo>0 && idsHabilidades!=null && idsHabilidades.length>0){
				ofertaFacade.eliminaHabilidades(idOfertaEmpleo);

				for (long idHabilidad : idsHabilidades){
					ofertaFacade.registraHabilidad(idOfertaEmpleo, idHabilidad);
				}
			}
		} catch(Exception e){
			e.printStackTrace();
			logger.error(e);
		}
	}
	
	
	
	public long editarUbicacionTotal(RegistroUbicacionVO vo1,RegistroRequisitosVO vo2,RegistroContactoVO vo3, long idTipoUsuario)throws BusinessException, IndexerException, TechnicalException{

		long id = editarUbicacion(vo1);
		vo2.setIdOferta(id);

		guardarRequisitos(vo2);
		vo3.setIdOferta(id);

		guardarContacto(vo3, idTipoUsuario,0,0);

		return id;
	}


	public long editarUbicacionTotal(RegistroUbicacionVO vo1,RegistroRequisitosVO vo2,RegistroContactoVO vo3, long idTipoUsuario, long idUsuario, long idRegValidar)throws BusinessException, IndexerException, TechnicalException{

		long id = editarUbicacion(vo1);
		vo2.setIdOferta(id);

		guardarRequisitos(vo2);
		vo3.setIdOferta(id);

		guardarContacto(vo3, idTipoUsuario, idUsuario, idRegValidar);

		return id;
	}	

	public long guardarUbicacionTotal(RegistroUbicacionVO vo1,RegistroRequisitosVO vo2,RegistroContactoVO vo3, long idTipoUsuario) throws BusinessException, IndexerException, TechnicalException{

		long id = guardarUbicacion(vo1);
		vo2.setIdOferta(id);

		guardarRequisitos(vo2);

		vo3.setIdOferta(id);

		guardarContacto(vo3, idTipoUsuario,0,0);

		return id;
	}

	public long guardarUbicacionTotal(RegistroUbicacionVO vo1,RegistroRequisitosVO vo2,RegistroContactoVO vo3, long idTipoUsuario, long idUsuario, long idRegValidar) throws BusinessException, IndexerException, TechnicalException{

		long id = guardarUbicacion(vo1);
		vo2.setIdOferta(id);

		guardarRequisitos(vo2);

		vo3.setIdOferta(id);

		if (idTipoUsuario != (long)TIPO_USUARIO.ADMINISTRADOR.getIdTipoUsuario()){
			guardarContacto(vo3, idTipoUsuario,0,0);	
		}else{
			guardarContacto(vo3, idTipoUsuario, idUsuario, idRegValidar);
		}

		return id;
	}	

	public long guardarUbicacion(RegistroUbicacionVO vo)throws BusinessException {

		if(vo.getArea()<0 || vo.getDicapacidad()<0 || vo.getHorario()<0 || vo.getIdEmpresa()<1 || /*vo.getLimitePostulantes()<0 ||*/ 
				vo.getNumeroPlazas()<0 || vo.getTipoContrato()<0 || vo.getTipoContacto()<0 || vo.getRolarTurnos()<0)
			throw new BusinessException("Datos tipo Numï¿½rico incompletos");

		if(vo.getFunciones()==null || vo.getHEntrada()==null || vo.getHSalida()==null || vo.getTituloOferta()==null || vo.getPrestaciones()==null )
			throw new BusinessException("Datos tipo Cadena incompletos");

		if(vo.getVigenciaInicio()==null || vo.getVigenciaFin()==null)
			throw new BusinessException("Datos tipo Fecha incompletos");

		if(vo.getSalario()<0)
			throw new BusinessException("Datos tipo Decimal incompletos");

		if(vo.getDomingo()==null && vo.getLunes()==null && vo.getMartes()==null && vo.getMiercoles()==null && vo.getJueves()==null && vo.getViernes()==null && vo.getSabado()==null)
			throw new BusinessException("Datos de dï¿½as erroneos");

		long idOferta = ofertaFacade.save(vo);

		return idOferta;
	}

	public long editarUbicacion(RegistroUbicacionVO vo) throws BusinessException {

		if(vo.getArea()<0 || vo.getDicapacidad()<0 || vo.getHorario()<0 || vo.getIdEmpresa()<1 || 
				/*vo.getLimitePostulantes()<0 ||*/
				vo.getNumeroPlazas()<0 || vo.getTipoContrato()<0 || vo.getTipoContacto()<0 || vo.getRolarTurnos()<0)

			throw new BusinessException("Datos tipo Numï¿½rico incompletos");

		if(vo.getFunciones()==null || vo.getHEntrada()==null || vo.getHSalida()==null || vo.getTituloOferta()==null || vo.getPrestaciones()==null )
			throw new BusinessException("Datos tipo Cadena incompletos");

		if(vo.getVigenciaInicio()==null || vo.getVigenciaFin()==null)
			throw new BusinessException("Datos tipo Fecha incompletos");

		if(vo.getSalario()<0)
			throw new BusinessException("Datos tipo Decimal incompletos");

		if(vo.getDomingo()==null && vo.getLunes()==null && vo.getMartes()==null && vo.getMiercoles()==null && vo.getJueves()==null && vo.getViernes()==null && vo.getSabado()==null)
			throw new BusinessException("Datos de dï¿½as erroneos");

		long idOferta = ofertaFacade.update(vo);

		return idOferta;
	}

	public void guardarRequisitos(RegistroRequisitosVO vo) throws BusinessException {

		if(vo.getAniosExperiencia()<0 || vo.getDisponibilidadRadicar()<0 || vo.getDisponibilidadViajar()<0 || vo.getEdadA()<0 || vo.getGenero()<0 )
			throw new BusinessException("Datos tipo Numï¿½rico incompletos");

		if(vo.getConocimientosGenerales()==null )
			throw new BusinessException("Datos tipo Cadena incompletos");

		if(vo.getIdOferta()<=0)
			throw new BusinessException("Identificador de oferta erroneo");

		ofertaFacade.saveRequisitosOferta(vo);
	}


	public void guardarContacto(RegistroContactoVO vo, long idTipoUsuario, long idUsuario, long idRegValidar) throws BusinessException, IndexerException, TechnicalException {

		if( vo.getHoraAtencionFin()<0 || vo.getHoraAtencionInicio()<0 || vo.getNombreContacto()<0)
			throw new BusinessException("Datos tipo Numï¿½rico incompletos");

		if(vo.getDomingo()==null && vo.getLunes()==null && vo.getMartes()==null && vo.getMiercoles()==null && vo.getJueves()==null && vo.getViernes()==null && vo.getSabado()==null)
			throw new BusinessException("Datos de dï¿½as erroneos");

		if(vo.getIdOferta()<=0) throw new BusinessException("Identificador de oferta erroneo");		
		
		OfertaEmpleoVO ofertaVO = obtenerOferta(vo.getIdOferta());
		if(ofertaVO.getFuente() == Constantes.CATALOGO_FUENTE_QUE_REGISTRA_OFERTA.SISNE.getIdOpcion()){
			vo.setEstatus(ofertaVO.getEstatus());
			long idOfertaEmpleo = ofertaFacade.updateContactoOferta(vo);
			//establecer vigencia sin generar registro por validar
	    	int idVigenciaOferta = ofertaVO.getIdVigenciaOferta();
	    	if(idVigenciaOferta>0){
		    	Date fechaFin = new Date();	    		
		    	Calendar fechaFinVigencia = Calendar.getInstance();	    	
		    	fechaFinVigencia.add(Calendar.DATE, VIGENCIA_OFERTA.getDias(idVigenciaOferta));	    	
		    	fechaFin=fechaFinVigencia.getTime();				
				ofertaEmpleoFacade.autorizaOfertaEmpleo(idOfertaEmpleo, ofertaVO.getEstatus(),fechaFin);
	    	}
			bitacoraFacade.save(EVENTO.MODIFICA_OFERTA_EMPLEO.getIdEvento(), idUsuario,
					EVENTO.MODIFICA_OFERTA_EMPLEO.getEvento(), Calendar.getInstance(), 
					null, idOfertaEmpleo, TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());	
//FIXME OracleText
/*
			try {
				IndexerServiceLocator.getIndexerServiceRemote().indexaOfertaEmpleo(idOfertaEmpleo);
			} catch (Exception e) {
				logger.error(e); 
				throw new IndexerException(e);
			}
 */		
		} else {	
			if (idTipoUsuario != (long)TIPO_USUARIO.ADMINISTRADOR.getIdTipoUsuario()){
				vo.setEstatus(ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion());
				long idOfertaEmpleo = ofertaFacade.updateContactoOferta(vo);
	
				// Se registra la Oferta para su revision por un Publicador
				autorizacionAppService.registraOfertaPorValidar(idOfertaEmpleo, vo.getIdEmpresa());
			}else{
				// Se establece como ACTIVA cuando el publicador realiza la modificacion y aceptacion
				vo.setEstatus(ESTATUS.ACTIVO.getIdOpcion());
				long idOfertaEmpleo = ofertaFacade.updateContactoOferta(vo);
	
				bitacoraFacade.save(EVENTO.MODIFICA_OFERTA_EMPLEO.getIdEvento(), idUsuario,
						EVENTO.MODIFICA_OFERTA_EMPLEO.getEvento(), Calendar.getInstance(), 
						null, idOfertaEmpleo, TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
	
				// La autorizacion establece a la oferta como ACTIVA
				autorizacionAppService.autorizaOfertaEmpleo(idOfertaEmpleo, idUsuario, idRegValidar);
			}			
		}
	}

	public OfertaEmpleoVO obtenerOferta(long id) throws BusinessException {
		if(id<=0) throw new BusinessException("Identificador de oferta erroneo");

		OfertaEmpleoVO oferta = ofertaFacade.obtenerOferta(id);
		oferta.setCarreras(getCarrerasEspecialidades(oferta.getIdOfertaEmpleo()));
		oferta.setConocimientos(getRequisitosOferta(oferta.getIdOfertaEmpleo(),Constantes.TIPO_REQUISITO.CONOCIMIENTO.getIdTipoRequisito()));
		oferta.setDomicilio(domicilioFacade.buscarDomicilioIdPropietario(oferta.getIdOfertaEmpleo(), Constantes.TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario()));
		oferta.setHabilidades(getRequisitosOferta(oferta.getIdOfertaEmpleo(),Constantes.TIPO_REQUISITO.HABILIDAD.getIdTipoRequisito()));
		oferta.setIdiomas(getIdiomas(oferta.getIdOfertaEmpleo()));
		oferta.setTelefonos(telefonoFacade.getTelefonosPropietario(oferta.getIdOfertaEmpleo(), Constantes.TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario()));
		oferta.setConocimientoComputacionVO(conocimientoComputacionFacade.consultaConocimientosComputacion(oferta.getIdOfertaEmpleo(), TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario()));
		List<Long> prestaciones = new ArrayList<Long>();
		for(String var: getPrestacionesOferta(oferta.getIdOfertaEmpleo())){

			prestaciones.add(Long.parseLong(var));

		}
		oferta.setPrestaciones(prestaciones);
		
		List<RegistroEntidadesVO> listaEntidades = getEntidadesOferta(oferta.getIdOfertaEmpleo());
		if(listaEntidades!=null&&!listaEntidades.isEmpty()){
		RegistroEntidadesVO vo = listaEntidades.get(0);

		if(vo!=null){

			oferta.setIdEntidadUbicacion((int) vo.getEntidad());
			oferta.setIdMunicipioUbicacion((int) vo.getMunicipio());

		}
		}

		List<Long> idHabilidades = ofertaFacade.consultaHabilidades(oferta.getIdOfertaEmpleo());
		if (idHabilidades!=null){
			long[] idHabilidad = new long[idHabilidades.size()];
			
			int index = 0;
			for (Long idaux : idHabilidades){
				idHabilidad[index] = idaux;
				index++;
			}

			oferta.setIdHabilidad(idHabilidad);
		}
		
		return oferta;
	}


	private List<OfertaRequisitoVO> getRequisitosOferta(long idOfertaEmpleo,long idTipoRequisito) {
		List<OfertaRequisitoVO> conocimientos =  ofertaFacade.getRequisitosOferta(idOfertaEmpleo,idTipoRequisito);
		return conocimientos;
	}

	public ArrayList<String> getSectoresOferta(long idOferta) throws BusinessException{

		if(idOferta<=0)
			throw new BusinessException("Identificador de oferta erroneo");

		ArrayList<String> sectores =  (ArrayList<String>) ofertaFacade.getSectoresOferta(idOferta);
		return sectores;
	}

	public ArrayList<String> getPrestacionesOferta(long idOferta) throws BusinessException{

		if(idOferta<=0)
			throw new BusinessException("Identificador de oferta erroneo");

		ArrayList<String> prestaciones =  (ArrayList<String>) ofertaFacade.getPrestacionesOferta(idOferta);
		return prestaciones;
	}

	public ArrayList<RegistroEntidadesVO> getEntidadesOferta(long idOferta) throws BusinessException{

		if(idOferta<=0)
			throw new BusinessException("Identificador de oferta erroneo");

		ArrayList<RegistroEntidadesVO> entidades =  (ArrayList<RegistroEntidadesVO>) ofertaFacade.getEntidadesOferta(idOferta);
		return entidades;
	}

	public ArrayList<OfertaCarreraEspecialidadVO> getCarrerasEspecialidades(long idOferta) throws BusinessException{

		if(idOferta<=0)
			throw new BusinessException("Identificador de oferta erroneo");

		ArrayList<OfertaCarreraEspecialidadVO> carreras =  (ArrayList<OfertaCarreraEspecialidadVO>) ofertaFacade.getCarrerasEspecialidades(idOferta);
		return carreras;
	}


	public ArrayList<RequisitoVO> getRequisitos(long idOferta) throws  BusinessException{

		if(idOferta<=0)
			throw new BusinessException("Identificador de oferta erroneo");

		ArrayList<RequisitoVO> requisitos =  (ArrayList<RequisitoVO>) ofertaFacade.getRequisitos(idOferta);
		return requisitos;

	}

	public ArrayList<OfertaIdiomaVO> getIdiomas(long idOferta) throws BusinessException {

		if(idOferta<=0)
			throw new BusinessException("Identificador de oferta erroneo");

		ArrayList<OfertaIdiomaVO> idiomas =  (ArrayList<OfertaIdiomaVO>) ofertaFacade.getIdiomas(idOferta);
		return idiomas;
	}


	/*public ArrayList<TerceraEmpresaVO> getTercerasEmpresas(long idEmpresa) throws BusinessException{

		if(idEmpresa<=0)
			throw new BusinessException("Identificador de empresa erroneo");

		ArrayList<TerceraEmpresaVO> tercerasEmpresas =(ArrayList<TerceraEmpresaVO>) ofertaFacade.getTercerasEmpresas(idEmpresa);
		return tercerasEmpresas;
	}

	public ArrayList<ContactoVO> getContactos(long idEmpresa) throws BusinessException{

		if(idEmpresa<=0)
			throw new BusinessException("Identificador de empresa erroneo");

		ArrayList<ContactoVO> contactos =(ArrayList<ContactoVO>)ofertaFacade.getContactos(idEmpresa);
		return contactos;
	}
	public ArrayList<TerceraEmpresaVO> getEmpresas(long idEmpresa) throws BusinessException {

		if(idEmpresa<=0)
			throw new BusinessException("Identificador de empresa erroneo");

		ArrayList<TerceraEmpresaVO> empresas =(ArrayList<TerceraEmpresaVO>) ofertaFacade.getEmpresas(idEmpresa);
		return empresas;
	}*/

	public List<MunicipioVO> obtenerMunicipio(long idEntidad) throws BusinessException{

		if(idEntidad<=0)
			throw new BusinessException("Identificador de entidad erroneo");

		ArrayList<MunicipioVO> municipios =(ArrayList<MunicipioVO>) ofertaFacade.obtenerMunicipio(idEntidad);
		return municipios;
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<OfertaEmpleoVO> obtenerOfertasEmpresa(long idEmpresa) throws BusinessException{

		if(idEmpresa<=0)
			throw new BusinessException("Identificador de empresa erroneo");

		ArrayList<OfertaEmpleoVO> ofertas =(ArrayList<OfertaEmpleoVO>) ofertaFacade.obtenerOfertasEmpresa(idEmpresa);

		// completamos la informaciï¿½n de cada oferta de la empresa recuperan la descripciï¿½n de la ocupaciï¿½n y el nivel de estudios
		try{

			for(OfertaEmpleoVO vo : ofertas){

				//ocupaciï¿½n
				String ocupacionDescripcion = catalogoOpcionFacadelocal.getOpcionById(CATALOGO_OPCION_OCUPACION, vo.getIdOcupacion());				
				vo.setOcupacionDescripcion(ocupacionDescripcion);
				//nivel de estudios
				String nivelEstudiosDescripcion = GRADO_ESTUDIOS.getDescripcion(Utils.toInt(vo.getIdNivelEstudio()));
				vo.setNivelEstudiosDescripcion(nivelEstudiosDescripcion);				
			}
		} catch (Exception e){
			e.printStackTrace();
		}

		/*String actividadEconomica = catalogoOpcionFacade.getOpcionById(CATALOGO_OPCION_TIPO_ACTIVIDAD_ECONOMICA, empresa.getIdActividadEconomica());
		empresa.setActividadEconomica(actividadEconomica);*/

		return ofertas;
	}

	/*private void updateStatusOferta(long idOferta, int estatus)	throws BusinessException{

		if(idOferta<=0)
			throw new BusinessException("Identificador de oferta erroneo");

		ofertaFacade.updateStatusOferta(idOferta, estatus);
	}*/

	public void activaOfertaEmpleo(long idOfertaEmpleo, long idUsuario) {
		if(idOfertaEmpleo<=0) throw new IllegalArgumentException("Identificador de oferta erroneo");

		OfertaEmpleoVO  oferta = ofertaEmpleoFacade.findById(idOfertaEmpleo);
		if(oferta.getFuente() == Constantes.CATALOGO_FUENTE_QUE_REGISTRA_OFERTA.ABRIENDO_ESPACIOS.getIdOpcion()){
			actualizaEstatusOferta(idOfertaEmpleo, ESTATUS.ABRIENDO_ESPACIOS_ACTIVO, idUsuario);
		} else {
			actualizaEstatusOferta(idOfertaEmpleo, ESTATUS.ACTIVO, idUsuario);
		}		
	}

	@Transactional
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void cancelaOfertaEmpleo(long idOfertaEmpleo, long idUsuario) {
		if(idOfertaEmpleo<=0) throw new IllegalArgumentException("Identificador de oferta erroneo");
		actualizaEstatusOferta(idOfertaEmpleo, ESTATUS.CANCELADA, idUsuario);
	}

	public void eliminaOfertaPorEmpresa(long idOfertaEmpleo, long idUsuario) {
		if(idOfertaEmpleo<=0) throw new IllegalArgumentException("Identificador de oferta erroneo");

		actualizaEstatusOferta(idOfertaEmpleo, ESTATUS.ELIMINADA_EMP, idUsuario);
	}

	public void eliminaOfertaPorAdministrador(long idOfertaEmpleo, long idUsuario) {
		if(idOfertaEmpleo<=0) throw new IllegalArgumentException("Identificador de oferta erroneo");

		actualizaEstatusOferta(idOfertaEmpleo, ESTATUS.ELIMINADA_ADMIN, idUsuario);
	}

	private void actualizaEstatusOferta(long idOfertaEmpleo, ESTATUS estatus, long idUsuario) {
		if(idOfertaEmpleo<=0) throw new IllegalArgumentException("Identificador de oferta erroneo");
		ofertaEmpleoFacade.actualizaEstatus(idOfertaEmpleo, estatus.getIdOpcion());	
//FIXME OracleText
/*
		try {
			if (estatus == ESTATUS.ELIMINADA_ADMIN || estatus == ESTATUS.ELIMINADA_EMP || estatus == ESTATUS.CANCELADA){
				IndexerServiceLocator.getIndexerServiceRemote().eliminaOfertaindice(idOfertaEmpleo); // Invocacion remota de indexador	
			} else if (estatus == ESTATUS.ABRIENDO_ESPACIOS_ACTIVO || estatus == ESTATUS.ACTIVO) {
				IndexerServiceLocator.getIndexerServiceRemote().indexaOfertaEmpleo(idOfertaEmpleo);; // Invocacion remota de indexador
			}
		} catch (Exception e) {
		    e.printStackTrace();
		}
 */
	}

	public List<OfertaEmpleoVO> obtenerOfertasEliminadasFolio(long idEmpresa,int estatus1,int estatus2, long folio)throws BusinessException{


		if(idEmpresa<=0 || folio<=0)
			throw new BusinessException("Identificador de empresa erroneo");

		List<OfertaEmpleoVO> ofertas= (List<OfertaEmpleoVO>)ofertaFacade.obtenerOfertasEliminadasFolio(idEmpresa, estatus1, estatus2, folio);
		
		//identificacion si es oferta becate
		List<OfertaEmpleoVO> ofertaList = new ArrayList<OfertaEmpleoVO>();

		for(OfertaEmpleoVO oferta:ofertas){
			checarOfertaBecate(oferta);
			ofertaList.add(oferta);
		}		
		return ofertaList;
	}

	public List<OfertaEmpleoVO> obtenerOfertasEliminadasFecha(long idEmpresa,int estatus1,int estatus2, Date fecha1, Date fecha2)throws BusinessException
	{

		if(idEmpresa<=0 || fecha1==null || fecha2==null)
			throw new BusinessException("Datos de bï¿½squeda erroneos");

		List<OfertaEmpleoVO> ofertas= (List<OfertaEmpleoVO>)ofertaFacade.obtenerOfertasEliminadasFecha(idEmpresa, estatus1, estatus2, fecha1, fecha2);
		
		//identificacion si es oferta becate
		List<OfertaEmpleoVO> ofertaList = new ArrayList<OfertaEmpleoVO>();

		for(OfertaEmpleoVO oferta:ofertas){
			checarOfertaBecate(oferta);
			ofertaList.add(oferta);
		}		
		return ofertaList;
	}

	public List<OfertaEmpleoVO> obtenerOfertasEliminadasTitulo(long idEmpresa,int estatus1,int estatus2, String titulo) throws BusinessException
	{

		if(idEmpresa<=0 || titulo==null)
			throw new BusinessException("Datos de bï¿½squeda erroneos");

		List<OfertaEmpleoVO> ofertas= (List<OfertaEmpleoVO>)ofertaFacade.obtenerOfertasEliminadasTitulo(idEmpresa, estatus1, estatus2, titulo);
		
		//identificacion si es oferta becate
		List<OfertaEmpleoVO> ofertaList = new ArrayList<OfertaEmpleoVO>();

		for(OfertaEmpleoVO oferta:ofertas){
			checarOfertaBecate(oferta);
			ofertaList.add(oferta);
		}
		return ofertaList;
	}
	
	private OfertaEmpleoVO checarOfertaBecate(OfertaEmpleoVO oferta){
		List<OfertaEmpleoBecateVO> ofertasBecate = ofertaEmpleoBecateFacade.findById(oferta.getIdOfertaEmpleo());
		if(ofertasBecate!=null && ofertasBecate.size()>0){
			oferta.setOfertaBecate(true);
		}else{
			oferta.setOfertaBecate(false);
		}
		return oferta;
		
	}

	public void insertarEventoBitacora(EventoVO evento) throws  BusinessException{

		if(evento==null)
			throw new BusinessException("Datos erroneos");

		ofertaFacade.insertarEventoBitacora(evento);
	}
	
	public void insertarEventoBitacora(EVENTO evento, long idUsuario, TIPO_REGISTRO tipoRegistro, long idRegistro, int estatusAnterior) throws  BusinessException{
		if(evento==null)
			throw new BusinessException("Datos erroneos");
		
    	String detalle = "idOfertaEmpleo=" + idRegistro + "|estatus="+ estatusAnterior;    	
		bitacoraFacade.save(evento.getIdEvento(), idUsuario, evento.getEvento(), Calendar.getInstance(), detalle, idRegistro, TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
	}
	
	

	public DomicilioVO obtenerDomicilio( long idOferta) throws  BusinessException{

		if(idOferta<=0)
			throw new BusinessException("Identificador de oferta erroneo");

		DomicilioVO domicilio= ofertaFacade.obtenerDomicilio( idOferta);
		return domicilio;
	}

	public CodigoPostalVO obtenerCodigoPostal( long idColonia) throws  BusinessException{

		if(idColonia<=0)
			throw new BusinessException("Dato de bï¿½squeda incorrecto");


		CodigoPostalVO cp= ofertaFacade.obtenerCodigoPostal(idColonia);
		return cp;
	}


	public List<MunicipioVO> obtenerMunicipios() throws  BusinessException{
		List<MunicipioVO> municipios=ofertaFacade.obtenerMunicipios();
		return municipios;

	}

	public List<CatalogoOpcionVO> getOpcionByInterval(long idCatalogo,long op1, long op2)throws  BusinessException{
		List<CatalogoOpcionVO> opciones =catalogoOpcionFacadelocal.getOpcionByInterval(idCatalogo, op1,  op2);
		return opciones;


	}

	public void updateFechaOfertaCanceladaActivada(long idOferta) throws BusinessException{
		ofertaFacade.updateFechaOfertaCanceladaActivada(idOferta);

	}
	
	public void updateEstatusNotificacion(NotificacionCandidato candidato)throws BusinessException{
		ofertaFacade.updateEstatusNotificacion(candidato);
	}
	public void insertarNotificacionCandidato(NotificacionCandidato candidato)throws BusinessException{
		
		ofertaFacade.notificacionCandidato(candidato);
	}
	
	public void updateNotificacionCandidato(NotificacionCandidato candidato)throws BusinessException{
		
		ofertaFacade.updateCandidatoNotificacion2(candidato);
	}
	public List<OfertaPorCanalVO> consultaOfertasXCanal(long idEmpresa) throws SQLException {
		OfertasPorCanalDAO ofertasDao = new OfertasPorCanalDAO();			
		List<OfertaPorCanalVO> lstOfertas =  ofertasDao.obtenerOfertasXCanal(idEmpresa);
		return lstOfertas;
	}

	public List<OfertaPostulacionVO> obtienePostulantesDeEmpresa(long idEmpresa) throws BusinessException{
		if (idEmpresa < 1) throw new BusinessException("Identificador de empresa errï¿½neo");

		return ofertaCandidatoFacade.obtienePostulantesDeEmpresa(idEmpresa);
	}

	public List<OfertaEmpleoVO> consultaTotalPostuladosPorOferta(long idEmpresa){
		if (idEmpresa<=0) throw new IllegalArgumentException("Identificador de empresa requerido");
		return ofertaCandidatoFacade.consultaTotalPostuladosPorOferta(idEmpresa);
	}

	public List<OfertaEmpleoVO> consultaMisOfertas(long idEmpresa){
		return ofertaFacade.consultaMisOfertas(idEmpresa);
	}
	
	public List<ReporteOfertasEmpresaVO> getOfertasEmpresaReporte(long idEmpresa, int selectedStatus, 
			String selectedInitial, String selectedFinal) {
		List<ReporteOfertasEmpresaVO> list = ofertaFacade.getOfertasEmpresaReporte(idEmpresa, selectedStatus, selectedInitial, selectedFinal);
		for (ReporteOfertasEmpresaVO offer : list) {
			CatSubareaVO subArea = catalogoOpcionFacadelocal.getSubAreaVOByIdAreaIdSubArea(offer.getIdArea(),
					offer.getIdSubArea());
			if (null != subArea) offer.setSubAreaLaboralDescripcion(subArea.getDescripcion());
		}
		return list;
	}

	@Override
	public List<OfertaEmpleoVO> busquedaOfertaActivaCancelada(long idEmpresa, Long folioOferta, String tituloOferta) throws SQLException {
		List<OfertaEmpleoVO> ofertas= ofertaFacade.consultaOfertaByFolioTitulo(idEmpresa, folioOferta, tituloOferta);
		
		//identificacion si es oferta becate
		List<OfertaEmpleoVO> ofertaList = new ArrayList<OfertaEmpleoVO>();

		for (OfertaEmpleoVO oferta:ofertas){
			checarOfertaBecate(oferta);
			ofertaList.add(oferta);
		}
		return ofertaList;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ResultadoBusquedaOfertasVO> buscarOfertasEspecificas(BusquedaOfertasVO vo) throws BusinessException, SQLException {
		return ofertaFacade.buscarOfertasEspecificas(vo);
	}

	@Override
	public int update(OfertaEmpleoVO vo) throws BusinessException {
		int result = 0;
		try {
			ofertaEmpleoFacade.update(vo);
		}catch (Exception e) { result = -1; }
		return result;
	}

	@Override
	public int actualizaEstatus(long idOfertaEmpleo, int estatus) throws BusinessException {
		int result = 0;
		try {
			ofertaEmpleoFacade.actualizaEstatus(idOfertaEmpleo, estatus);
		}catch (Exception e) { result = -1; }
		return result;
	}
	
	@Override
	public List<OfertaEmpleoBecateVO> obtenerOfertaBecate(){
		return ofertaEmpleoBecateFacade.getOfertaEmpleoBecate();
	}

	@Override
	public List<ModalidadVO> consultarModalidad(long idSubprograma) {
		return modalidadOfertaFacade.obtenerModalidad(idSubprograma);
	}

	@Override
	public OfertaEmpleoBecateVO obtenerOfertaBecateById(long idOferta) throws BusinessException {
		if(idOferta<=0) throw new BusinessException("Identificador de oferta erroneo");

		OfertaEmpleoBecateVO oferta = ofertaEmpleoBecateFacade.obtenerOfertaById(idOferta);
		return oferta;
	}

	@Override
	public ModalidadOfertaVO obtenerModalidadOfertaByIdOferta(long idOferta) throws BusinessException {
		if(idOferta<=0) throw new BusinessException("Identificador de oferta erroneo");

		ModalidadOfertaVO modalidad = modalidadOfertaFacade.obtenerModalidadOfertaByIdOferta(idOferta);
		return modalidad;
	}

	@Override
	public Boolean esOfertaBecate(long idOfertaEmpleo) throws BusinessException {
		return ofertaEmpleoBecateFacade.esOfertaBecate(idOfertaEmpleo);
	}

	@Override
	public void updateCandidatoNotificacion2(NotificacionCandidato candidato)
			throws BusinessException {
		ofertaFacade.updateCandidatoNotificacion2(candidato);
		
	}

	
	@Override
	public long findCandidatoNotificacion(Long idCantidato) {
		return ofertaFacade.findCandidatoNotificacion(idCantidato);
		
	}

	


	

	
	
}
