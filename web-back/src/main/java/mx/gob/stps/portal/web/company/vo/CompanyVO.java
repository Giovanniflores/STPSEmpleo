package mx.gob.stps.portal.web.company.vo;

import java.util.List;

import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.vo.BitacoraVO;

public class CompanyVO extends EmpresaVO {
	private static final long serialVersionUID = 2560999278424657325L;

	public final static CompanyVO getInstance(EmpresaVO empresa){
		return new CompanyVO(empresa);
	}

	private CompanyVO(EmpresaVO empresa) {
		this.setIdEmpresa(empresa.getIdEmpresa());
		this.setIdPortalEmpleo(empresa.getIdPortalEmpleo());
		this.setIdUsuario(empresa.getIdUsuario());
		this.setRfc(empresa.getRfc());
		this.setIdTipoPersona(empresa.getIdTipoPersona());
		this.setNombre(empresa.getNombre());
		this.setApellido1(empresa.getApellido1());
		this.setApellido2(empresa.getApellido2());
		this.setFechaNacimiento(empresa.getFechaNacimiento());
		this.setRazonSocial(empresa.getRazonSocial());
		this.setFechaActa(empresa.getFechaActa());
		this.setDescripcion(empresa.getDescripcion());
		this.setContactoEmpresa(empresa.getContactoEmpresa());
		this.setIdTipoEmpresa(empresa.getIdTipoEmpresa());
		this.setIdActividadEconomica(empresa.getIdActividadEconomica());
		this.setNumeroEmpleados(empresa.getNumeroEmpleados());
		this.setIdMedio(empresa.getIdMedio());
		this.setConfidencial(empresa.getConfidencial());
		this.setPaginaWeb(empresa.getPaginaWeb());
		this.setLogotipo(empresa.getLogotipo());
		this.setAceptacionTerminos(empresa.getAceptacionTerminos());
		this.setFechaAlta(empresa.getFechaAlta());
		this.setEstatus(empresa.getEstatus());
		this.setFechaUltimaActualizacion(empresa.getFechaUltimaActualizacion());
		this.setCorreoElectronico(empresa.getCorreoElectronico());
		this.setAseguraDatos(empresa.getAseguraDatos());
		this.setTelefonos(empresa.getTelefonos());
		this.setDomicilio(empresa.getDomicilio());
		this.setTipoPersona(empresa.getTipoPersona());
		this.setTipoEmpresa(empresa.getTipoEmpresa());
		this.setActividadEconomica(empresa.getActividadEconomica());
		this.setTblEmpresa(empresa.getTblEmpresa());
		this.setIdRegValidar(empresa.getIdRegValidar());
		this.setEstatusRegistro(empresa.getEstatusRegistro());
		this.setFechaRevision(empresa.getFechaRevision());
		this.setMotivoRechazo(empresa.getMotivoRechazo());
		this.setDetalleRechazo(empresa.getDetalleRechazo());
		this.setNombrePublicador(empresa.getNombrePublicador());
		this.setRechazada(empresa.isRechazada());
		this.setDescartar(empresa.isDescartar());
		this.setEsEmpresaSNE(empresa.getEsEmpresaSNE());
		this.setUsuario(empresa.getUsuario());
	}

	private List<BitacoraVO> movimientos;
	private int esEmpresaSNE;

	public List<BitacoraVO> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<BitacoraVO> movimientos) {
		this.movimientos = movimientos;
	}
	
	public int getEsEmpresaSNE(){
		return esEmpresaSNE;
	}
	
	public void setEsEmpresaSNE(int esEmpresaSNE){
		this.esEmpresaSNE = esEmpresaSNE;
	}

}
