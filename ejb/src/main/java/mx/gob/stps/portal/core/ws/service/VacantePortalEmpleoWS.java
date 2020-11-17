package mx.gob.stps.portal.core.ws.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static mx.gob.stps.portal.utils.ConstantesGenerales.PORTAL_ID_OFICINA;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jws.WebService;

import mx.gob.stps.portal.core.domicilio.vo.MunicipioVO;
import mx.gob.stps.portal.core.empresa.vo.EmpresaPortalSisneVO;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_FUENTE_QUE_REGISTRA_OFERTA;
import mx.gob.stps.portal.core.infra.utils.Constantes.CAUSA_ORIGINA_OFERTA;
import mx.gob.stps.portal.core.infra.utils.Constantes.DISPONIBILIDAD_RADICAR;
import mx.gob.stps.portal.core.infra.utils.Constantes.DISPONIBILIDAD_VIAJAR;
import mx.gob.stps.portal.core.infra.utils.Constantes.DOMINIO;
import mx.gob.stps.portal.core.infra.utils.Constantes.ENTIDADES_FEDERATIVAS;
import mx.gob.stps.portal.core.infra.utils.Constantes.GENERO;
import mx.gob.stps.portal.core.infra.utils.Constantes.GRADO_ESTUDIOS;
import mx.gob.stps.portal.core.infra.utils.Constantes.IDIOMAS;
import mx.gob.stps.portal.core.infra.utils.Constantes.MEDIO_CONTACTO;
import mx.gob.stps.portal.core.infra.utils.Constantes.MULTIREGISTRO;
import mx.gob.stps.portal.core.infra.utils.Constantes.PRESTACIONES;
import mx.gob.stps.portal.core.infra.utils.Constantes.ROLAR_TURNO;
import mx.gob.stps.portal.core.infra.utils.Constantes.SITUACION_ACADEMICA;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_DISCAPACIDAD;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_TELEFONO;
import mx.gob.stps.portal.core.infra.utils.Constantes.VIGENCIA_OFERTA;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.core.oferta.registro.vo.OfertaCarreraEspecialidadVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaUbicacionVO;
import mx.gob.stps.portal.core.persistencia.facade.CatalogoOpcionFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.DomicilioFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.EmpresaFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.EmpresasPortalSisneFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.MunicipioFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.OfertaCarreraEspecFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.OfertaEmpleoFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.OfertaFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.OfertaUbicacionFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.RegistroPorValidarFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.TelefonoFacadeLocal;
//fixme import mx.gob.stps.portal.core.search.service.IndexerServiceLocator;
//import mx.gob.stps.portal.core.search.service.PortalEmpleoBuscadorServiceLocal;
import mx.gob.stps.portal.core.ws.vo.VacanteVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.utils.Catalogos.PUBLICAR_OFERTA_PE_SNETEL;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

@WebService
@Stateless(name = "VacantePortalEmpleoWS", mappedName = "VacantePortalEmpleoWS")
public class VacantePortalEmpleoWS {
	
	Logger logger = Logger.getLogger(VacantePortalEmpleoWS.class);

	@EJB
	private OfertaFacadeLocal ofertaFacade;	
	
	@EJB
	private CatalogoOpcionFacadeLocal catalogoOpcionFacade;
	
	@EJB
	private EmpresaFacadeLocal empresaFacade;

	@EJB
	private MunicipioFacadeLocal municipioFacade;
	
	@EJB
	private OfertaEmpleoFacadeLocal ofertaEmpleoFacade;

	@EJB
	private OfertaUbicacionFacadeLocal ofertaUbicacionFacade;
	
	@EJB	
	private OfertaCarreraEspecFacadeLocal ofertaCarreraEspecFacade;

	@EJB
	private DomicilioFacadeLocal domicilioFacade;	
	
	@EJB
	private TelefonoFacadeLocal telefonoFacade;
	
//	@EJB	
//	private PortalEmpleoBuscadorServiceLocal portalEmpleoBuscadorService;
	
	@EJB
	private EmpresasPortalSisneFacadeLocal empresasPortalSisneFacade;
	
	@EJB
	private RegistroPorValidarFacadeLocal registroPorValidarFacade;
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public int creaVacantePortalEmpleo(VacanteVO vacante) throws BusinessException, TechnicalException { 		
		
		logger.info("Se invoca el servicio creaVacantePortalEmpleo");
		
		if (vacante == null){
			logger.error("La vacante recibida no está instanciada");
			throw new IllegalArgumentException("La vacante recibida no está instanciada");			
		}
		
		logger.info("vacante="+vacante.toString());
		
		long idOfertaEmpleo = 0;		
		try{

		if (vacante.getIdEmpresaSisne() == 0){
			logger.error("El parámetro idEmpresaSisne no está informado");
			throw new IllegalArgumentException("Parámetro idEmpresaSisne de la oferta es requerido");			
		}
			
		if (vacante.getTituloOferta() == null || vacante.getTituloOferta().isEmpty()){
			logger.error("El parámetro tituloOferta no está informado");
			throw new IllegalArgumentException("Parámetro título de la oferta es requerido");
		}
		
		if (vacante.getIdOcupacion() == 0){
			logger.error("El parámetro idOcupacion no está informado");
			throw new IllegalArgumentException("Parámetro idOcupacion es requerido");
		}
		
		if (vacante.getFunciones() == null || vacante.getFunciones().isEmpty()){
			logger.error("El parámetro funciones no está informado");
			throw new IllegalArgumentException("Parámetro funciones es requerido");
		}
		
		if (vacante.getIdTipoEmpleo() == 0){
			logger.error("El parámetro idTipoEmpleo no está informado");
			throw new IllegalArgumentException("Parámetro idTipoEmpleo es requerido");
		}

		if (vacante.getDiasLaborales() == null || vacante.getDiasLaborales().isEmpty()){
			logger.error("El parámetro diasLaborales no está informado");
			throw new IllegalArgumentException("Parámetro diasLaborales es requerido");
		}else if(vacante.getDiasLaborales() != null || !vacante.getDiasLaborales().isEmpty()){
			//logger.info("Valida los días laborales recibidos");
			String dias = vacante.getDiasLaborales();
			if(dias.length() == 8){
				if(dias.substring(7).equals("1")){
					//logger.info("Los días laborales son de L - V");
					dias = "0111110";
					vacante.setDiasLaborales(dias);
				}else{
					//logger.info("Los 7 días laborales son validos");
					dias = dias.substring(0, 7);
					vacante.setDiasLaborales(dias);
				}
				
			}
		}

		if (vacante.getHoraEntrada() == null || vacante.getHoraEntrada().isEmpty()){
			logger.error("El parámetro horaEntrada no está informado");
			throw new IllegalArgumentException("Parámetro horaEntrada es requerido");
		}else{
			//logger.info("Obtiene el idCatalogo del catalogo de horas");
			vacante.setHoraEntrada(Utils.obtenerIdCatHora(vacante.getHoraEntrada()));
		}

		if (vacante.getHoraSalida() == null || vacante.getHoraSalida().isEmpty()){
			logger.error("El parámetro horaSalida no está informado");
			throw new IllegalArgumentException("Parámetro horaSalida es requerido");
		}else{
			//logger.info("Obtiene el idCatalogo del catalogo de horas");
			vacante.setHoraSalida(Utils.obtenerIdCatHora(vacante.getHoraSalida()));
		}
		
		if (vacante.getEmpresaOfrece() == null || vacante.getEmpresaOfrece().isEmpty()){
			logger.error("El parámetro empresaOfrece no está informado");
			throw new IllegalArgumentException("Parámetro empresaOfrece es requerido");
		}			

		if (vacante.getSalario() == 0){
			logger.error("El parámetro salario no está informado");
			throw new IllegalArgumentException("Parámetro salario es requerido");
		}		

		if (vacante.getNumeroPlazas() == 0){
			logger.error("El parámetro numeroPlazas no está informado");
			throw new IllegalArgumentException("Parámetro numeroPlazas es requerido");
		}
		
		if (vacante.getLimitePostulantes() == 0){
			logger.error("El parámetro limitePostulantes no está informado");
			throw new IllegalArgumentException("Parámetro limitePostulantes es requerido");
		}

		if (vacante.getIdTipoContrato() == 0){
			vacante.setIdTipoContrato(Utils.toInt(Constantes.TIPO_CONTRATO.INDETERMINADO.getIdOpcion()));
			//logger.info("El parámetro idTipoContrato no viene informado, se asigna el valor "+Constantes.TIPO_CONTRATO.INDETERMINADO.getIdOpcion());			
		}

		if (vacante.getIdJerarquia() == 0){
			vacante.setIdJerarquia(Utils.toInt(Constantes.JERARQUIA.EMPLEADO.getIdOpcion()));
			//logger.info("El parámetro idJerarquia no viene informado, se asigna el valor "+Constantes.JERARQUIA.EMPLEADO.getIdOpcion());			
		}
		
		vacante.setRolarTurno(ROLAR_TURNO.NO.getIdOpcion());

		if (vacante.getIdEntidad() == 0){
			logger.error("El parámetro idEntidad no está informado");
			throw new IllegalArgumentException("Parámetro idEntidad es requerido");
		}

		if ("".equals(vacante.getMunicipio()) || vacante.getMunicipio().isEmpty()){
			logger.error("El parámetro municipio no está informado");
			throw new IllegalArgumentException("Parámetro municipio es requerido");
		}		

		if (vacante.getIdDiscapacidad() == 0){
			logger.error("El parámetro idDiscapacidad no está informado");
			throw new IllegalArgumentException("Parámetro idDiscapacidad es requerido");
		}		
		
		if (vacante.getIdCausaVacante() == 0){
			logger.error("El parámetro idCausaVacante no está informado");
			throw new IllegalArgumentException("Parámetro idCausaVacante es requerido");
		}
		
		if (vacante.getDisponibilidadViajar() == 0){
			logger.error("El parámetro disponibilidadViajar no está informado");
			throw new IllegalArgumentException("Parámetro disponibilidadViajar es requerido");
		}		

		if (vacante.getDisponibilidadRadicar() == 0){
			logger.error("El parámetro disponibilidadRadicar no está informado");
			throw new IllegalArgumentException("Parámetro disponibilidadRadicar es requerido");
		}
		
		if (vacante.getIdNivelEstudio() == 0){
			logger.error("El parámetro idNivelEstudio no está informado");
			throw new IllegalArgumentException("Parámetro idNivelEstudio es requerido");
		}

		if (vacante.getIdSituacionAcademica() == 0){
			logger.error("El parámetro idSituacionAcademica no está informado");
			throw new IllegalArgumentException("Parámetro idSituacionAcademica es requerido");
		}
		
		if (vacante.getExperienciaAnios() == 0){
			logger.error("El parámetro experienciaAnios no está informado");
			throw new IllegalArgumentException("Parámetro experienciaAnios es requerido");
		}		
		
		if (vacante.getEdadMinima() == 0){
			logger.error("El parámetro edadMinima no está informado");
			throw new IllegalArgumentException("Parámetro edadMinima es requerido");
		}

		if (vacante.getEdadMaxima() == 0){
			logger.error("El parámetro edadMaxima no está informado");
			throw new IllegalArgumentException("Parámetro edadMaxima es requerido");
		}		

		if (vacante.getGenero() == 0){
			logger.error("El parámetro genero no está informado");
			throw new IllegalArgumentException("Parámetro genero es requerido");
		}

		if (vacante.getIdCarreraEspecialidad() == 0){
			logger.error("El parámetro idCarreraEspecialidad no está informado");
			throw new IllegalArgumentException("Parámetro idCarreraEspecialidad es requerido");
		} 
		
		if (vacante.getIdSituacionAcademica() == 0){
			logger.error("El parámetro idSituacionAcademica no está informado");
			throw new IllegalArgumentException("Parámetro idSituacionAcademica es requerido");
		} 

		if (vacante.getObservaciones() == null || vacante.getObservaciones().isEmpty()){
			logger.error("El parámetro observaciones no está informado");
			throw new IllegalArgumentException("Parámetro observaciones es requerido");
		}	
		
		if (vacante.getFechaAlta() == null){
			logger.error("El parámetro fechaAlta no está informado");
			throw new IllegalArgumentException("Parámetro fechaAlta es requerido");						
		}
		
		try{
			SimpleDateFormat dateDDMMYYYY = new SimpleDateFormat("dd/MM/yyyy");
			dateDDMMYYYY.parse(vacante.getFechaAlta());
		} catch(ParseException pe){
			logger.error("El parámetro fechaAlta="+vacante.getFechaAlta()+" no tiene formato dd/MM/yyyy");
			throw new IllegalArgumentException("Parámetro fechaAlta no tiene el formato dd/MM/yyyy");			
		}
		
		
		if (vacante.getIdVigenciaOferta() == 0){
			logger.error("El parámetro idVigenciaOferta no está informado");
			throw new IllegalArgumentException("Parámetro idVigenciaOferta es requerido");
		}
		
		if (vacante.getNombreEmpresa() == null || vacante.getNombreEmpresa().isEmpty()){
			logger.error("El parámetro NombreEmpresa no está informado");
			throw new IllegalArgumentException("Parámetro NombreEmpresa es requerido");
		}
		
		if (vacante.getNombreContacto() == null || vacante.getNombreContacto().isEmpty()){
			logger.error("El parámetro nombreContacto no está informado");
			throw new IllegalArgumentException("Parámetro nombreContacto es requerido");
		}	

		if (vacante.getCorreoElectronicoContacto() == null || vacante.getCorreoElectronicoContacto().isEmpty()){
			logger.error("El parámetro correoElectronicoContacto no está informado");
			throw new IllegalArgumentException("Parámetro correoElectronicoContacto es requerido");
		}
		
//		if (vacante.getIdActividadEconomica() == 0){
//			logger.error("El parámetro Actividad Económica no está informado");
//			throw new IllegalArgumentException("Parámetro Actividad Económica es requerido");
//		}
//		
		if (vacante.getCargoContacto() == null || vacante.getCargoContacto().isEmpty()){
			logger.error("El parámetro cargoContacto no está informado");
			throw new IllegalArgumentException("Parámetro cargoContacto es requerido");
		}
		
//		if (vacante.getContactoDomicilio() == 0){
//			logger.error("El parámetro Contacto Domicilio no está informado");
//			throw new IllegalArgumentException("Parámetro Contacto Domicilio es requerido");
//		}
		
		//XXX Habilidad general ya no se usa en el portal
		if (vacante.getHabilidadGeneral() == null || vacante.getHabilidadGeneral().isEmpty()){
			logger.error("El parámetro habilidadGeneral no está informado");
			throw new IllegalArgumentException("Parámetro habilidadGeneral es requerido");
		}
		
		vacante.setFechaInicio(vacante.getFechaAlta());
		
		// validamos los campos de catálogo
		
		CatalogoOpcionVO catalogoOpcionVO; 
		
		catalogoOpcionVO = catalogoOpcionFacade.findById(Constantes.CATALOGO_OPCION_OCUPACION, vacante.getIdOcupacion());
		if (catalogoOpcionVO == null || catalogoOpcionVO.getOpcion() == null || catalogoOpcionVO.getOpcion().isEmpty()){
			logger.error("No se ha encontrado la opción de idOcupacion="+vacante.getIdOcupacion());
			throw new BusinessException("El valor del parámetro idOcupacion no se ha encontrado en el catálogo del Portal de Empleo");
		}
		
		String descripcion;		
		
		descripcion = Constantes.TIPO_EMPLEO.getDescripcion(vacante.getIdTipoEmpleo());
		if (descripcion == null || descripcion.isEmpty()){
			logger.error("El valor idTipoEmpleo="+vacante.getIdTipoEmpleo()+" no es correcto");
			throw new BusinessException("El valor del parámetro idTipoEmpleo no es correcto");			
		}
		
		descripcion = ROLAR_TURNO.getDescripcion(vacante.getRolarTurno());
		if (descripcion == null || descripcion.isEmpty()){
			logger.error("El valor rolarTurno="+vacante.getRolarTurno()+" no es correcto");
			throw new BusinessException("El valor del parámetro rolarTurno no es correcto");	
		}
			
		descripcion = TIPO_DISCAPACIDAD.getDescripcion(Utils.toInt(vacante.getIdDiscapacidad()));
		if (descripcion == null || descripcion.isEmpty()){
			logger.error("El valor idDiscapacidad="+vacante.getIdDiscapacidad()+" no es correcto");
			throw new BusinessException("El valor del parámetro idDiscapacidad no es correcto");			
		}
		
		descripcion = CAUSA_ORIGINA_OFERTA.getDescripcion(Utils.toInt(vacante.getIdCausaVacante()));
		if (descripcion == null || descripcion.isEmpty()){
			logger.error("El valor idCausaVacante="+vacante.getIdCausaVacante()+" no es correcto");
			throw new BusinessException("El valor del parámetro idCausaVacante no es correcto");			
		}
		
		descripcion = DISPONIBILIDAD_VIAJAR.getDescripcion(vacante.getDisponibilidadViajar());		
		if (descripcion == null || descripcion.isEmpty()){
			logger.error("El valor disponibilidadViajar="+vacante.getDisponibilidadViajar()+" no es correcto");
			throw new BusinessException("El valor del parámetro disponibilidadViajar no es correcto");			
		}
		
		descripcion = DISPONIBILIDAD_RADICAR.getDescripcion(vacante.getDisponibilidadRadicar());		
		if (descripcion == null || descripcion.isEmpty()){
			logger.error("El valor disponibilidadRadicar="+vacante.getDisponibilidadRadicar()+" no es correcto");
			throw new BusinessException("El valor del parámetro disponibilidadRadicar no es correcto");			
		}
		
		descripcion = GRADO_ESTUDIOS.getDescripcion(Utils.toInt(vacante.getIdNivelEstudio()));		
		if (descripcion == null || descripcion.isEmpty()){
			logger.error("El valor idNivelEstudio="+vacante.getIdNivelEstudio()+" no es correcto");
			throw new BusinessException("El valor del parámetro idNivelEstudio no es correcto");			
		}
		
		descripcion = SITUACION_ACADEMICA.getDescripcion(Utils.toInt(vacante.getIdSituacionAcademica()));		
		if (descripcion == null || descripcion.isEmpty()){
			logger.error("El valor idSituacionAcademica="+vacante.getIdSituacionAcademica()+" no es correcto");
			throw new BusinessException("El valor del parámetro idSituacionAcademica no es correcto");			
		}		

		descripcion = GENERO.getDescripcion(vacante.getGenero());		
		if (descripcion == null || descripcion.isEmpty()){
			logger.error("El valor genero="+vacante.getGenero()+" no es correcto");
			throw new BusinessException("El valor del parámetro genero no es correcto");			
		}

		int dias = VIGENCIA_OFERTA.getDias(vacante.getIdVigenciaOferta());		
		if (dias == 0){
			logger.error("El valor idVigenciaOferta="+vacante.getIdVigenciaOferta()+" no es correcto");
			throw new BusinessException("El valor del idVigenciaOferta genero no es correcto");			
		}
		
		if (vacante.getIdIdioma1() > 0){
				descripcion = IDIOMAS.getDescripcion(vacante.getIdIdioma1());		
				if (descripcion == null || descripcion.isEmpty()){
					logger.error("El valor idIdioma1="+vacante.getIdIdioma1()+" no es correcto");
					throw new BusinessException("El valor del parámetro idIdioma1 no es correcto");
				}
				
				if (vacante.getIdDominio1() == 0)
					vacante.setIdDominio1(Utils.toInt(DOMINIO.NINGUNO.getIdOpcion()));
		}

		if (vacante.getIdDominio1() > 0){
			descripcion = DOMINIO.getDescripcion(vacante.getIdDominio1());		
			if (descripcion == null || descripcion.isEmpty()){
				logger.error("El valor idDominio1="+vacante.getIdDominio1()+" no es correcto");
				throw new BusinessException("El valor del parámetro idDominio1 no es correcto");
			}
		}		
		
		if (vacante.getIdIdioma2() > 0){
			descripcion = IDIOMAS.getDescripcion(vacante.getIdIdioma2());		
			if (descripcion == null || descripcion.isEmpty()){
				logger.error("El valor idIdioma1="+vacante.getIdIdioma2()+" no es correcto");
				throw new BusinessException("El valor del parámetro idIdioma2 no es correcto");
			}
			
			if (vacante.getIdDominio2() == 0)
				vacante.setIdDominio2(Utils.toInt(DOMINIO.NINGUNO.getIdOpcion()));			
		}
		
		if (vacante.getIdDominio2() > 0){
			descripcion = DOMINIO.getDescripcion(vacante.getIdDominio2());		
			if (descripcion == null || descripcion.isEmpty()){
				logger.error("El valor idDominio2="+vacante.getIdDominio2()+" no es correcto");
				throw new BusinessException("El valor del parámetro idDominio2 no es correcto");
			}
		}	
		
		if (vacante.getAcceso() == null || vacante.getAcceso().isEmpty()){
			logger.error("El valor acceso="+vacante.getAcceso()+" no es correcto");
			throw new BusinessException("El valor del parámetro acceso no es correcto");			
		}

		if (vacante.getClave() == null || vacante.getClave().isEmpty()){
			logger.error("El valor clave="+vacante.getClave()+" no es correcto");
			throw new BusinessException("El valor del parámetro clave no es correcto");		
		}
		
		if (vacante.getTelefono() == null || vacante.getTelefono().isEmpty()){
			logger.error("El valor clave="+vacante.getTelefono()+" no es correcto");
			throw new BusinessException("El valor del parámetro telefono no es correcto");		
		}
		
		ENTIDADES_FEDERATIVAS entidadFederativa = ENTIDADES_FEDERATIVAS.getEntidad(Utils.toInt(vacante.getIdEntidad()));
		if (entidadFederativa == null || entidadFederativa.getDescripcion() == null || entidadFederativa.getDescripcion().isEmpty()){
			logger.error("El valor entidadFederativa="+vacante.getIdEntidad()+" no es correcto");
			throw new BusinessException("El valor del parámetro idEntidad no es correcto");
		}
		
		long idMunicipio = 0;
		List<MunicipioVO> municipio = municipioFacade.consultaMunicipioByName(vacante.getIdEntidad(), vacante.getMunicipio());
		if (municipio == null || municipio.isEmpty()){
			logger.error("El valor municipio="+vacante.getMunicipio()+" para idEntidad="+vacante.getIdEntidad()+" no es correcto");
			throw new BusinessException("No se ha encontrado el municipio dentro de la entidad");
		} else{
			//logger.info("Se recupera el municipio "+municipio.get(0).getIdMunicipio()+" - "+municipio.get(0).getMunicipio());
			idMunicipio = +municipio.get(0).getIdMunicipio();
		}

		descripcion = SITUACION_ACADEMICA.getDescripcion(Utils.toInt(vacante.getIdSituacionAcademica()));		
		if (descripcion == null || descripcion.isEmpty()){
			logger.error("El valor idSituacionAcademica="+vacante.getIdSituacionAcademica()+" no es correcto");
			throw new BusinessException("El valor del parámetro idSituacionAcademica no es correcto");
		}
				
		if (vacante.getIdMedioContactoPreferido() == 0){
			logger.error("El parámetro idMedioContactoPreferido no está informado");
			throw new IllegalArgumentException("Parámetro idMedioContactoPreferido es requerido");
		}
		
		descripcion = MEDIO_CONTACTO.getDescripcion(vacante.getIdMedioContactoPreferido());		
		if (descripcion == null || descripcion.isEmpty()){
			logger.error("El valor idMedioContactoPreferido="+vacante.getIdMedioContactoPreferido()+" no es correcto");
			throw new BusinessException("El valor del parámetro idMedioContactoPreferido no es correcto");
		}

		catalogoOpcionVO = catalogoOpcionFacade.findById(Constantes.CATALOGO_OPCION_GRADO_ESTUDIOS, vacante.getIdNivelEstudio());
		if (catalogoOpcionVO == null || catalogoOpcionVO.getIdCatalagoAsociado() == 0){
			logger.error("No se ha encontrado la opción de idNivelEstudio="+vacante.getIdNivelEstudio());
			throw new BusinessException("El valor del parámetro idNivelEstudio no se ha encontrado en el catálogo del Portal de Empleo");
		}

		catalogoOpcionVO = catalogoOpcionFacade.findById(catalogoOpcionVO.getIdCatalagoAsociado(), vacante.getIdCarreraEspecialidad());	
		if (catalogoOpcionVO == null || catalogoOpcionVO.getOpcion() == null || catalogoOpcionVO.getOpcion().isEmpty()){
			logger.error("No se ha encontrado la opción de idCarreraEspecialidad="+vacante.getIdCarreraEspecialidad()+ " del grado de estudios "+vacante.getIdNivelEstudio());
			throw new BusinessException("El valor del parámetro idCarreraEspecialidad no se ha encontrado en el catálogo de Grados de Estudio del Portal de Empleo");
		}		

		catalogoOpcionVO = catalogoOpcionFacade.findById(Constantes.CATALOGO_OPCION_TIPO_CONTRATO, vacante.getIdTipoContrato());
		if(catalogoOpcionVO == null || catalogoOpcionVO.getOpcion() == null || catalogoOpcionVO.getOpcion().isEmpty()){
			logger.error("No se ha encontrado la opción de idTipoContrato="+vacante.getIdTipoContrato());
			throw new BusinessException("El valor del parámetro idTipoContrato no se ha encontrado en el catálogo del Portal de Empleo");
		}
		
		//Valida el idActividadEconomica
//		catalogoOpcionVO = catalogoOpcionFacade.findById(Constantes.CATALOGO_OPCION_TIPO_ACTIVIDAD_ECONOMICA, vacante.getIdActividadEconomica());
//		if(catalogoOpcionVO == null || catalogoOpcionVO.getOpcion() == null || catalogoOpcionVO.getOpcion().isEmpty()){
//			logger.error("No se ha encontrado la opción de idActividadEconomica="+vacante.getIdActividadEconomica());
//			throw new BusinessException("El valor del parámetro idActividadEconomica no se ha encontrado en el catálogo del Portal de Empleo");
//		}
		
		// datos de OFERTA_EMPLEO
		OfertaEmpleoVO ofertaEmpleo = new OfertaEmpleoVO();
		
		/**
		 * Esta validación se realiza en el método (validarOficina)
		 */
		// Recuperamos el id de la empresa que aglutinará el alta de ofetas de SISNE
		EmpresaPortalSisneVO empresaPortalSisneVO = empresasPortalSisneFacade.findById(vacante.getIdEmpresaSisne());
		
//		if (empresaPortalSisneVO == null || empresaPortalSisneVO.getIdEmpresa() == 0){
//			logger.error("No se ha encontrado el mapeo que relaciona la empresa de Sisne idEmpresaSisne="+vacante.getIdEmpresaSisne()+ " con su empresa del Portal");
//			throw new BusinessException("No se ha encontrado el mapeo que relaciona la empresa de Sisne con su equivalente empresa del Portal");			
//		}
		
		ofertaEmpleo.setIdEmpresa(empresaPortalSisneVO.getIdEmpresa());

		EmpresaVO empresaVO = empresaFacade.findById(ofertaEmpleo.getIdEmpresa());
		
		if (empresaVO != null && empresaVO.getRazonSocial() != null  && !empresaVO.getRazonSocial().isEmpty())
			ofertaEmpleo.setNombreEmpresa(empresaVO.getRazonSocial());
			
		ofertaEmpleo.setTituloOferta(vacante.getTituloOferta().toUpperCase());
		
		{
			//Se obtiene los dos primeros digitos del idOcupacion
			String primerosDigitosOcupacion = StringUtils.substring(Utils.toString(vacante.getIdOcupacion()), 0, 2);

			catalogoOpcionVO = catalogoOpcionFacade.findById(Constantes.CATALOGO_OPCION_AREA_LABORAL, Utils.toLong(primerosDigitosOcupacion));
			
			if (catalogoOpcionVO == null || catalogoOpcionVO.getIdCatalogoOpcion() == 0){
				logger.error("No se puede determinar el area laboral que corresponde a idOcupacion="+vacante.getIdOcupacion());
				throw new BusinessException("No se puede determinar el area laboral que corresponde a idOcupacion="+vacante.getIdOcupacion());
			} else{
				ofertaEmpleo.setIdAreaLaboral(catalogoOpcionVO.getIdCatalogoOpcion());
			}
		}
		
		ofertaEmpleo.setIdOcupacion(vacante.getIdOcupacion());		
		ofertaEmpleo.setFunciones(vacante.getFunciones().toUpperCase());
		ofertaEmpleo.setIdTipoEmpleo(vacante.getIdTipoEmpleo());
		ofertaEmpleo.setDiasLaborales(vacante.getDiasLaborales());
		ofertaEmpleo.setEstatus(Constantes.ESTATUS.ACTIVO.getIdOpcion());
		ofertaEmpleo.setHoraEntrada(vacante.getHoraEntrada());
		ofertaEmpleo.setHoraSalida(vacante.getHoraSalida());
		ofertaEmpleo.setRolarTurno(vacante.getRolarTurno());		
		ofertaEmpleo.setEmpresaOfrece(vacante.getEmpresaOfrece().toUpperCase());
		ofertaEmpleo.setSalario(vacante.getSalario());
		ofertaEmpleo.setIdTipoContrato(vacante.getIdTipoContrato());
		ofertaEmpleo.setIdJerarquia(vacante.getIdJerarquia());		
		ofertaEmpleo.setNumeroPlazas(vacante.getNumeroPlazas());		
		ofertaEmpleo.setLimitePostulantes(vacante.getLimitePostulantes());		
		ofertaEmpleo.setIdDiscapacidad(vacante.getIdDiscapacidad());		
		ofertaEmpleo.setIdCausaVacante(vacante.getIdCausaVacante());				
		ofertaEmpleo.setFechaInicio(Utils.convert(vacante.getFechaInicio()));
		//La fuente que registra esta oferta es el SISNE
		ofertaEmpleo.setFuente(CATALOGO_FUENTE_QUE_REGISTRA_OFERTA.SISNE.getIdOpcion());
		// determinamos la fecha de fin de la oferta a partir de la fecha de alta y la vigencia
		{
			Calendar fechaFin = Calendar.getInstance();
			fechaFin.setTime(Utils.convert(vacante.getFechaAlta()));
			int diasVigencia = VIGENCIA_OFERTA.getDias(vacante.getIdVigenciaOferta());
			
			if (diasVigencia > 0)
				fechaFin.add(Calendar.DAY_OF_MONTH, diasVigencia);
			else
				fechaFin.add(Calendar.DAY_OF_MONTH, 30);

			ofertaEmpleo.setFechaFin(fechaFin.getTime());
		}		
		ofertaEmpleo.setDisponibilidadViajar(vacante.getDisponibilidadViajar());
		ofertaEmpleo.setDisponibilidadRadicar(vacante.getDisponibilidadRadicar());
		ofertaEmpleo.setIdNivelEstudio(vacante.getIdNivelEstudio());
		ofertaEmpleo.setIdSituacionAcademica(vacante.getIdSituacionAcademica());
		ofertaEmpleo.setExperienciaAnios(vacante.getExperienciaAnios());
		ofertaEmpleo.setEdadMinima(vacante.getEdadMinima());
		ofertaEmpleo.setEdadMaxima(vacante.getEdadMaxima());
		
		if (ofertaEmpleo.getEdadMinima() > 0 || ofertaEmpleo.getEdadMaxima() > 0)
			ofertaEmpleo.setEdadRequisito(Constantes.EDAD_REQUISITO.SI.getIdOpcion());
		else 
			ofertaEmpleo.setEdadRequisito(Constantes.EDAD_REQUISITO.NO.getIdOpcion());
		
		ofertaEmpleo.setGenero(vacante.getGenero());
		ofertaEmpleo.setObservaciones(vacante.getObservaciones().toUpperCase());
		ofertaEmpleo.setFechaAlta(Utils.convert(vacante.getFechaAlta()));
		ofertaEmpleo.setFechaModificacion(ofertaEmpleo.getFechaAlta());
		ofertaEmpleo.setIdVigenciaOferta(vacante.getIdVigenciaOferta());
		//Campos agregados 30/01/13
		ofertaEmpleo.setCargoContacto(vacante.getCargoContacto().toUpperCase());
		ofertaEmpleo.setNombreEmpresa(vacante.getNombreEmpresa().toUpperCase());	
		//Modifcaciones hechas 26/02/13
		//Se agrego la actividad economica de la empresa y se indico que no existe un contacto_domicilio
		//ofertaEmpleo.setIdActividadEconomica((int)empresaVO.getIdActividadEconomica());
		//ofertaEmpleo.setIdActividadEconomica(vacante.getIdActividadEconomica());
		ofertaEmpleo.setContactoDomicilio(Constantes.CONTACTO_DOMICILIO.NO.getIdContactoDomicilio());
		
		ofertaEmpleo.setNombreContacto(vacante.getNombreContacto().toUpperCase());
		ofertaEmpleo.setCorreoElectronicoContacto(vacante.getCorreoElectronicoContacto());
		
//		ofertaEmpleo.setHabilidadGeneral(vacante.getHabilidadGeneral().toUpperCase());

		//Se habilita para que se pueda ver el medio de contacto por teléfono y correo electrónico 05/03/2013
		//if (vacante.getIdMedioContactoPreferido() == MEDIO_CONTACTO.FAX.getIdOpcion() || vacante.getIdMedioContactoPreferido() == MEDIO_CONTACTO.TELEFONO.getIdOpcion())
		ofertaEmpleo.setContactoTel(Constantes.CONTACTO_TELEFONO.SI.getIdContactoTelefono());
		
		//if (vacante.getIdMedioContactoPreferido() == MEDIO_CONTACTO.CORREO_ELECTRONICO.getIdOpcion())
		ofertaEmpleo.setContactoCorreo(Constantes.CONTACTO_CORREO.SI.getIdContactoCorreo());		

		if (vacante.getIdMedioContactoPreferido() == MEDIO_CONTACTO.DOMICILIO.getIdOpcion())
			ofertaEmpleo.setContactoDomicilio(Constantes.CONTACTO_DOMICILIO.NO.getIdContactoDomicilio());
		
		
		//SE AGREGAN ESTOS CAMPOS, YA QUE EN LA TABLA "OFERTA_EMPLEO" NO DEBEN SER NULLOS
		ofertaEmpleo.setPlazasCubiertas(new Integer(0));
		ofertaEmpleo.setIdUsuario(new Long(2775405));
		ofertaEmpleo.setPublicarOfertas(Utils.toInt(PUBLICAR_OFERTA_PE_SNETEL.SI.getIdOpcion()));
		ofertaEmpleo.setIdOficinaRegistro(PORTAL_ID_OFICINA);
		
		idOfertaEmpleo = ofertaEmpleoFacade.save(ofertaEmpleo);
		//logger.info("Se crea la oferta de empleo idOfertaEmpleo="+idOfertaEmpleo);
		
		//Registra la prestacion (Prestacion de ley), por defecto
		long idOfertaPrestacion =  ofertaFacade.registraOfertaPrestacion(idOfertaEmpleo, PRESTACIONES.DE_LEY.getIdOpcion(), new Date());
		//logger.info("Se registra la prestacion (prestacion de ley) a la oferta= " + idOfertaEmpleo );
		//logger.info("El idOfertaPrestacion= " + idOfertaPrestacion);
		
		
		//datos de OFERTA_UBICACION
		OfertaUbicacionVO ofertaUbicacionVO = new OfertaUbicacionVO();
		ofertaUbicacionVO.setIdOfertaEmpleo(Utils.toInt(idOfertaEmpleo));
		ofertaUbicacionVO.setIdEntidad(Utils.toInt(vacante.getIdEntidad()));
		ofertaUbicacionVO.setIdMunicipio(Utils.toInt(idMunicipio));
		ofertaUbicacionVO.setFechaAlta(ofertaEmpleo.getFechaAlta());
		
		long idOfertaUbicacion = ofertaUbicacionFacade.save(ofertaUbicacionVO);
		
		if (idOfertaUbicacion > 0)
			logger.info("Se crea la ubicación de la oferta de empleo idOfertaUbicacion="+idOfertaUbicacion);
		
		// datos de OFERTA_CARRERA_ESPEC
		OfertaCarreraEspecialidadVO ofertaCarreraEspecialidadVO = new OfertaCarreraEspecialidadVO();		
		ofertaCarreraEspecialidadVO.setIdRegistro(idOfertaEmpleo);
		ofertaCarreraEspecialidadVO.setFechaAlta(Utils.convert(vacante.getFechaAlta()));
		ofertaCarreraEspecialidadVO.setPrincipal(MULTIREGISTRO.PRINCIPAL.getIdOpcion());
		ofertaCarreraEspecialidadVO.setId(vacante.getIdCarreraEspecialidad());
		
		Long idOfertaCarreraEspec = ofertaCarreraEspecFacade.save(ofertaCarreraEspecialidadVO);
		if (idOfertaCarreraEspec != null)
			logger.info("Se crea la especialidad oferta de empleo idOfertaCarreraEspec="+idOfertaCarreraEspec);
		
		// datos de IDIOMA
		if (vacante.getIdIdioma1() == 0 && vacante.getIdIdioma2() == 0){
			
				long idIdioma = ofertaFacade.registraOfertaIdioma(idOfertaEmpleo, IDIOMAS.NINGUNO.getIdOpcion(), 2, DOMINIO.NINGUNO.getIdOpcion(), Utils.convert(vacante.getFechaAlta()), MULTIREGISTRO.PRINCIPAL.getIdOpcion());
				//logger.info("Se crea el registro de idioma idIdioma="+idIdioma);
				
		} else{
			
			int principal = MULTIREGISTRO.PRINCIPAL.getIdOpcion();
			if (vacante.getIdIdioma1() > 0 ){
				long idIdioma1 = ofertaFacade.registraOfertaIdioma(idOfertaEmpleo, vacante.getIdIdioma1(), 2, vacante.getIdDominio1(), Utils.convert(vacante.getFechaAlta()), principal);
				//logger.info("Se crea el registro de idioma idIdioma1="+idIdioma1);
				principal = MULTIREGISTRO.ADICIONAL.getIdOpcion();
			}
			if (vacante.getIdIdioma2() > 0 ){
				long idIdioma2 = ofertaFacade.registraOfertaIdioma(idOfertaEmpleo, vacante.getIdIdioma2(), 2, vacante.getIdDominio2(), Utils.convert(vacante.getFechaAlta()), principal);
				//logger.info("Se crea el registro de idioma idIdioma2="+idIdioma2);
			}			
			
		}
		
		// datos de TELEFONO
		TelefonoVO telefono = new TelefonoVO();		
		telefono.setIdPropietario(idOfertaEmpleo);
		telefono.setIdTipoPropietario(TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
		if (Constantes.CLAVE_TELEFONO_CELULAR.equals(vacante.getAcceso())){
			telefono.setIdTipoTelefono((int)TIPO_TELEFONO.CELULAR.getIdOpcion());
		} else {
			telefono.setIdTipoTelefono((int)TIPO_TELEFONO.FIJO.getIdOpcion());			
		}
		telefono.setAcceso(vacante.getAcceso());		
		telefono.setClave(vacante.getClave());		
		telefono.setTelefono(vacante.getTelefono());		
		telefono.setExtension(vacante.getExtension());
		telefono.setFechaAlta(Utils.convert(vacante.getFechaAlta()));		
		telefono.setPrincipal(MULTIREGISTRO.PRINCIPAL.getIdOpcion());
		
		long idTelefono = telefonoFacade.save(telefono);
		//logger.info("Se crea el registro de telefono idTelefono="+idTelefono);
		
		//Se genera un registro por validar con estatus aceptado para que permita ver el detalle
		//desde la busqueda del administrador
		registroPorValidarFacade.registraOfertaServicioSisne(idOfertaEmpleo, empresaVO.getIdEmpresa());
//FIXME OracleText
//		IndexerServiceLocator.getIndexerServiceRemote().indexaOfertaEmpleo(idOfertaEmpleo);
		//logger.info("Se han indexado la idOfertaEmpleo="+idOfertaEmpleo);
		
		}catch(IllegalArgumentException iae){
			throw new IllegalArgumentException(iae);
		} catch(BusinessException be){
			throw new BusinessException(be);
		} catch(Exception e){
			logger.error("Error en creaVacantePortalEmpleo");
			logger.error("Se ha hecho rollback sobre las transacciones ejecutadas");
			e.printStackTrace();
			throw new TechnicalException("Ha ocurrido un error al crear la vacante", e);
		}
		return Utils.toInt(idOfertaEmpleo);
	}
	
	
	public boolean validarOficina(int idOficina)throws BusinessException, TechnicalException {
		boolean oficinaValida = false;
		try{
			
			if(idOficina != 0){
				// Recuperamos el id de la empresa que aglutinará el alta de ofetas de SISNE
				EmpresaPortalSisneVO empresaPortalSisneVO = empresasPortalSisneFacade.findById(idOficina);
				if (empresaPortalSisneVO == null || empresaPortalSisneVO.getIdEmpresa() == 0){
					logger.error("No se ha encontrado el mapeo que relaciona la empresa de Sisne idEmpresaSisne="+idOficina+ " con su empresa del Portal");
					throw new BusinessException("No se ha encontrado el mapeo que relaciona la empresa de Sisne con su equivalente empresa del Portal");
				}else{
					oficinaValida = true;
				}
			}else{
				logger.error("El parámetro idOficina no está informado");
				throw new IllegalArgumentException("Parámetro idOficina es requerido");
			}

		}catch(IllegalArgumentException iae){
			throw new IllegalArgumentException(iae);
		} catch(BusinessException be){
			throw new BusinessException(be);
		} catch(Exception e){
			logger.error("Error en validarOficina");
			e.printStackTrace();
			throw new TechnicalException("Ha ocurrido un error al validar la Oficina", e);
		}
		
		return oficinaValida;
	}
	
}
