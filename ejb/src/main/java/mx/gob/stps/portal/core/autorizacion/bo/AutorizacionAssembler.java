package mx.gob.stps.portal.core.autorizacion.bo;

import mx.gob.stps.portal.core.empresa.vo.EmpresaFraudulentaVO;
import mx.gob.stps.portal.core.empresa.vo.EmpresaPorAutorizarVO;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoFraudulentaVO;

public final class AutorizacionAssembler {

	private final static AutorizacionAssembler INSTANCE = new AutorizacionAssembler();
	
	private AutorizacionAssembler(){}
	
	public static AutorizacionAssembler getInstance(){
		return INSTANCE;
	}

    public OfertaEmpleoFraudulentaVO creaOfertaEmpleoFraudulentaVO(OfertaEmpleoVO oferta){
    	OfertaEmpleoFraudulentaVO ofertaFrau = new OfertaEmpleoFraudulentaVO();
    	
		ofertaFrau.setIdOfertaEmpleo(oferta.getIdOfertaEmpleo());
		ofertaFrau.setIdEmpresa(oferta.getIdEmpresa());
		ofertaFrau.setTituloOferta(oferta.getTituloOferta());
		ofertaFrau.setIdAreaLaboral(oferta.getIdAreaLaboral());
		ofertaFrau.setIdOcupacion(oferta.getIdOcupacion());
		ofertaFrau.setFunciones(oferta.getFunciones());
		ofertaFrau.setDiasLaborales(oferta.getDiasLaborales());
		ofertaFrau.setHoraEntrada(oferta.getHoraEntrada());
		ofertaFrau.setHoraSalida(oferta.getHoraSalida());
		ofertaFrau.setRolarTurno(oferta.getRolarTurno());
		ofertaFrau.setEmpresaOfrece(oferta.getEmpresaOfrece());
		ofertaFrau.setSalario(oferta.getSalario());
		ofertaFrau.setIdTipoContrato(oferta.getIdTipoContrato());
		ofertaFrau.setIdJerarquia(oferta.getIdJerarquia());
		ofertaFrau.setNumeroPlazas(oferta.getNumeroPlazas());
		ofertaFrau.setLimitePostulantes(oferta.getLimitePostulantes());
		ofertaFrau.setIdDiscapacidad(oferta.getIdDiscapacidad());
		ofertaFrau.setIdCausaVacante(oferta.getIdCausaVacante());
		ofertaFrau.setFechaInicio(oferta.getFechaInicio());
		ofertaFrau.setFechaFin(oferta.getFechaFin());
		ofertaFrau.setDisponibilidadViajar(oferta.getDisponibilidadViajar());
		ofertaFrau.setDisponibilidadRadicar(oferta.getDisponibilidadRadicar());
		ofertaFrau.setIdNivelEstudio(oferta.getIdNivelEstudio());
		ofertaFrau.setIdSituacionAcademica(oferta.getIdSituacionAcademica());
//		ofertaFrau.setHabilidadGeneral(oferta.getHabilidadGeneral());
		ofertaFrau.setExperienciaAnios(oferta.getExperienciaAnios());
		ofertaFrau.setEdadRequisito(oferta.getEdadRequisito());
		ofertaFrau.setEdadMinima(oferta.getEdadMinima());
		ofertaFrau.setEdadMaxima(oferta.getEdadMaxima());
		ofertaFrau.setGenero(oferta.getGenero());
		ofertaFrau.setMapaUbicacion(oferta.getMapaUbicacion());
		ofertaFrau.setObservaciones(oferta.getObservaciones());
		ofertaFrau.setIdTerceraEmpresa(oferta.getIdTerceraEmpresa());
		ofertaFrau.setIdContacto(oferta.getIdContacto());
		ofertaFrau.setIdHorarioDe(oferta.getIdHorarioDe());
		ofertaFrau.setIdHorarioA(oferta.getIdHorarioA());
		ofertaFrau.setIdDuracionAproximada(oferta.getIdDuracionAproximada());
		ofertaFrau.setDiasEntrevista(oferta.getDiasEntrevista());
		ofertaFrau.setFuente(oferta.getFuente());
		ofertaFrau.setContactoTel(oferta.getContactoTel());
		ofertaFrau.setContactoCorreo(oferta.getContactoCorreo());
		ofertaFrau.setFechaAlta(oferta.getFechaAlta());
		ofertaFrau.setFechaModificacion(oferta.getFechaModificacion());
		ofertaFrau.setIdTipoEmpleo(oferta.getIdTipoEmpleo());
		ofertaFrau.setEstatus(oferta.getEstatus());

    	return ofertaFrau;
    }
    
    public EmpresaFraudulentaVO creaEmpresaFraudulentaVO(EmpresaPorAutorizarVO empresa){
    	EmpresaFraudulentaVO vo = new EmpresaFraudulentaVO();
    	vo.setIdEmpresa(empresa.getIdEmpresa());
    	vo.setIdPortalEmpleo(empresa.getIdPortalEmpleo());
    	//vo.setIdUsuario(empresa.getidUsuario);
    	vo.setRfc(empresa.getRfc());
    	vo.setIdTipoPersona(empresa.getIdTipoPersona());
    	vo.setNombre(empresa.getNombre());
    	vo.setApellido1(empresa.getApellido1());
    	vo.setApellido2(empresa.getApellido2());
    	vo.setFechaNacimiento(empresa.getFechaNacimiento());
    	vo.setRazonSocial(empresa.getRazonSocial());
    	vo.setFechaActa(empresa.getFechaActa());
    	vo.setDescripcion(empresa.getDescripcion());
    	vo.setContactoEmpresa(empresa.getContactoEmpresa());
    	vo.setIdTipoEmpresa(empresa.getIdTipoEmpresa());
    	vo.setIdActividadEconomica(empresa.getIdActividadEconomica());
    	vo.setNumeroEmpleados(empresa.getNumeroEmpleados());
    	vo.setIdMedio(empresa.getIdMedio());
    	vo.setConfidencial(empresa.getConfidencial());
    	vo.setPaginaWeb(empresa.getPaginaWeb());
    	vo.setAceptacionTerminos(empresa.getAceptacionTerminos());
    	vo.setFechaAlta(empresa.getFechaAlta());
    	vo.setEstatus(empresa.getEstatus());
    	vo.setFechaUltimaActualizacion(empresa.getFechaUltimaActualizacion());
    	vo.setCorreoElectronico(empresa.getCorreoElectronico());
    	vo.setAseguraDatos(empresa.getAseguraDatos());
    	//COMENTAR EN PRODUCCION 
    	vo.setNombreComercial(empresa.getNombreComercial());
    	return vo;
    }

    public EmpresaFraudulentaVO creaEmpresaFraudulentaVO(EmpresaVO empresa){
    	EmpresaFraudulentaVO vo = new EmpresaFraudulentaVO();
    	vo.setIdEmpresa(empresa.getIdEmpresa());
    	vo.setIdPortalEmpleo(empresa.getIdPortalEmpleo());
    	vo.setIdUsuario(empresa.getIdUsuario());
    	vo.setRfc(empresa.getRfc());
    	vo.setIdTipoPersona(empresa.getIdTipoPersona());
    	vo.setNombre(empresa.getNombre());
    	vo.setApellido1(empresa.getApellido1());
    	vo.setApellido2(empresa.getApellido2());
    	vo.setFechaNacimiento(empresa.getFechaNacimiento());
    	vo.setRazonSocial(empresa.getRazonSocial());
    	vo.setFechaActa(empresa.getFechaActa());
    	vo.setDescripcion(empresa.getDescripcion());
    	vo.setContactoEmpresa(empresa.getContactoEmpresa());
    	vo.setIdTipoEmpresa(empresa.getIdTipoEmpresa());
    	vo.setIdActividadEconomica(empresa.getIdActividadEconomica());
    	vo.setNumeroEmpleados(empresa.getNumeroEmpleados());
    	vo.setIdMedio(empresa.getIdMedio());
    	vo.setConfidencial(empresa.getConfidencial());
    	vo.setPaginaWeb(empresa.getPaginaWeb());
    	vo.setAceptacionTerminos(empresa.getAceptacionTerminos());
    	vo.setFechaAlta(empresa.getFechaAlta());
    	vo.setEstatus(empresa.getEstatus());
    	vo.setFechaUltimaActualizacion(empresa.getFechaUltimaActualizacion());
    	vo.setCorreoElectronico(empresa.getCorreoElectronico());
    	vo.setAseguraDatos(empresa.getAseguraDatos());
    	//COMENTAR EN PRODUCCION 
    	vo.setNombreComercial(empresa.getNombreComercial());
    	return vo;
    }

}
