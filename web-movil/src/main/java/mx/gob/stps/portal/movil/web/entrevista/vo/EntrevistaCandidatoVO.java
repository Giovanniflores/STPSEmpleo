package mx.gob.stps.portal.movil.web.entrevista.vo;

import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.vo.EntrevistaVO;

public final class EntrevistaCandidatoVO extends EntrevistaVO {

	private static final long serialVersionUID = 4806304435242627202L;

	private EntrevistaCandidatoVO(EntrevistaVO entrevista) {
		
		setIdEmpresa(entrevista.getIdEmpresa());
		setIdUsuario(entrevista.getIdUsuario());
		setTituloOferta(entrevista.getTituloOferta());
		setRazonSocial(entrevista.getRazonSocial());
		setContactoEmpresa(entrevista.getContactoEmpresa());
		setNombre(entrevista.getNombre());
		setFechaString(entrevista.getFechaString());
		setFechaFin(entrevista.getFechaFin());
		setNombreEmpresa(entrevista.getNombreEmpresa());
		setOfertaEmpleo(entrevista.getOfertaEmpleo());
		setMsn1(entrevista.getMsn1());
		setMsn2(entrevista.getMsn2());
		setTipo(entrevista.getTipo());
		setTipoOperacion(entrevista.getTipoOperacion());
		setCorreoEmpresa(entrevista.getCorreoEmpresa());
		setCorreoCandidato(entrevista.getCorreoCandidato());
		setCorreo(entrevista.getCorreo());
		setMensajeBitacora(entrevista.getMensajeBitacora());
		setIdEntrevista(entrevista.getIdEntrevista());
		setIdOfertaEmpleo(entrevista.getIdOfertaEmpleo());
		setIdCandidato(entrevista.getIdCandidato());
		setFecha(entrevista.getFecha());
		setHora(entrevista.getHora());
		setFechaAlta(entrevista.getFechaAlta());
		setFechaModificacion(entrevista.getFechaModificacion());
		setEstatus(entrevista.getEstatus());
		setEmailEmpresa(entrevista.getEmailEmpresa());
		setEmailCandidato(entrevista.getEmailCandidato());
		setEmailMensaje(entrevista.getEmailMensaje());
		setAsunto(entrevista.getAsunto());
		
	}

	public static final EntrevistaCandidatoVO getInstance(EntrevistaVO entrevista){
		return new EntrevistaCandidatoVO(entrevista);
	}

	public boolean isPuedeAceptarEntrevista() {
		int estatus = getEstatus();

		if (ESTATUS.ACEPTADA.getIdOpcion() == estatus || 
			ESTATUS.CANCELADA.getIdOpcion() == estatus  || 
			ESTATUS.RECHAZADA.getIdOpcion() == estatus ||
			ESTATUS.ELIMINADA_EMP.getIdOpcion() == estatus ||
			ESTATUS.ELIMINADA_ADMIN.getIdOpcion() == estatus ||
			ESTATUS.ELIMINADA_VIG.getIdOpcion() == estatus ||
			ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion() == estatus ||
			ESTATUS.ELIMINADA_EMP_FRAUDULENTA.getIdOpcion() == estatus){
		
			return false;
		}else{
			return true;			
		}
	}
	
	public String getEstatusDesc(){
		return ESTATUS.getDescripcion(getEstatus());
	}
}
