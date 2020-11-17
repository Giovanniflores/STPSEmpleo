package mx.gob.stps.portal.core.oferta.detalle.helper;

import mx.gob.stps.portal.core.candidate.vo.PerfilVO;
import mx.gob.stps.portal.core.oferta.detalle.bo.PerfilBO;

public class BeanUtils {
	
	public static PerfilBO copyProperties(PerfilVO origin, PerfilBO destiny) {
		if (null != destiny && null != origin) {
			destiny.setApellido1(origin.getApellido1());
			destiny.setApellido2(origin.getApellido2());
			destiny.setIdOficina(origin.getIdOficina());
			destiny.setContactoCorreo(origin.getContactoCorreo());
			destiny.setContactoTelefono(origin.getContactoTelefono());
			destiny.setCorreoElectronico(origin.getCorreoElectronico());
			destiny.setCurp(origin.getCurp());
			destiny.setEdad(origin.getEdad());
			destiny.setEntidadNacimiento(origin.getEntidadNacimiento());
			destiny.setFechaNacimiento(origin.getFechaNacimiento());
			destiny.setGenero(origin.getGenero());
			destiny.setHoraContactoIni(origin.getHoraContactoIni());
			destiny.setHoraContactoFin(origin.getHoraContactoFin());
			destiny.setIdCandidato(origin.getIdCandidato());
			destiny.setIdColonia(origin.getIdColonia());
			destiny.setIdDomicilio(origin.getIdDomicilio());
			destiny.setIdEntidad(origin.getIdEntidad());
			destiny.setIdEntidadNacimiento(origin.getIdEntidadNacimiento());
			destiny.setIdEstadoCivil(origin.getIdEstadoCivil());
			destiny.setIdGenero(origin.getIdGenero());
			destiny.setIdMedioBusqueda(origin.getIdMedioBusqueda());
			destiny.setIdMunicipio(origin.getIdMunicipio());
			destiny.setIdRazonBusqueda(origin.getIdRazonBusqueda());
			destiny.setIdTipoDiscapacidad(origin.getIdTipoDiscapacidad());
			destiny.setIdTipoPropietario(origin.getIdTipoPropietario());
			destiny.setIdTrabaja(origin.getIdTrabaja());
			destiny.setIdUsuario(origin.getIdUsuario());
			destiny.setNombre(origin.getNombre());
			destiny.setNumeroExterior(origin.getNumeroExterior());
			destiny.setNumeroInterior(origin.getNumeroInterior());
			destiny.setEstiloCV(origin.getEstiloCV());
			destiny.setConfidencialidad(origin.getConfidencialidad());
		}
		return destiny;
	}
}
