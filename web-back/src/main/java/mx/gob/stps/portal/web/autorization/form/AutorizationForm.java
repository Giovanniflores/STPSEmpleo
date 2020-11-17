package mx.gob.stps.portal.web.autorization.form;

import mx.gob.stps.portal.core.autorizacion.vo.RegistroPorValidarVO;
import mx.gob.stps.portal.core.candidate.vo.PerfilLaboralVo;
import mx.gob.stps.portal.core.empresa.vo.EmpresaFraudulentaVO;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.testimonio.vo.TestimonioVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.web.autorization.vo.OfertaDetalleVO;
import mx.gob.stps.portal.web.infra.utils.Utils;
import org.apache.struts.action.ActionForm;

import java.util.List;

//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;

public class AutorizationForm extends ActionForm {
	private static final long serialVersionUID = -6657185347452600365L;

	private long idRegValidar;
	
	private int idMotivoRechazo;
	private String motivoRechazo;
	
	private TestimonioVO testimonio;
	private PerfilLaboralVo perfilLaboral;
	private OfertaDetalleVO ofertaDetalle;
	private EmpresaVO empresa;
	private EmpresaFraudulentaVO empresaFraudulenta;
	
	private TelefonoVO telefonoVO;
	
	private List<RegistroPorValidarVO> registros;
	private List<EmpresaFraudulentaVO> empresasFraudulentas;
	
	private String idRegistroValidar;
	private long idEmpresaFraudulenta;
	
	public void reset() {
		this.idRegValidar = 0;
		this.idMotivoRechazo = 0;
		this.motivoRechazo = null;
		this.testimonio = null;
		this.perfilLaboral = null;
		this.empresasFraudulentas = null;
		this.idRegistroValidar="";
	}
	
	public long getIdRegValidar() {
		return idRegValidar;
	}
	public void setIdRegValidar(long idRegValidar) {
		this.idRegValidar = idRegValidar;
	}
	public List<RegistroPorValidarVO> getRegistros() {
		return registros;
	}
	public void setRegistros(List<RegistroPorValidarVO> registros) {
		this.registros = registros;
	}
	public int getIdMotivoRechazo() {
		return idMotivoRechazo;
	}
	public void setIdMotivoRechazo(int idMotivoRechazo) {
		this.idMotivoRechazo = idMotivoRechazo;
	}
	public String getMotivoRechazo() {
		return motivoRechazo;
	}
	public void setMotivoRechazo(String motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
	}
	public List<EmpresaFraudulentaVO> getEmpresasFraudulentas() {
		return empresasFraudulentas;
	}
	public void setEmpresasFraudulentas(
			List<EmpresaFraudulentaVO> empresasFraudulentas) {
		this.empresasFraudulentas = empresasFraudulentas;
	}
	public TestimonioVO getTestimonio() {
		return testimonio;
	}
	public void setTestimonio(TestimonioVO testimonio) {
		this.testimonio = testimonio;
	}
	public PerfilLaboralVo getPerfilLaboral() {
		return perfilLaboral;
	}
	public void setPerfilLaboral(PerfilLaboralVo perfilLaboral) {
		this.perfilLaboral = perfilLaboral;
	}
	public OfertaDetalleVO getOfertaDetalle() {
		return ofertaDetalle;
	}
	public void setOfertaDetalle(OfertaDetalleVO ofertaDetalle) {
		this.ofertaDetalle = ofertaDetalle;
	}
	public EmpresaVO getEmpresa() {
		return empresa;
	}
	public void setEmpresa(EmpresaVO empresa) {
		this.empresa = empresa;
	}

	public TelefonoVO getTelefonoVO() {
		return telefonoVO;
	}
	public void setTelefonoVO(TelefonoVO telefonoVO) {
		this.telefonoVO = telefonoVO;
	}

	public boolean isSimilarFraudulenta() {
		return empresasFraudulentas!=null && !empresasFraudulentas.isEmpty();
	}

	public EmpresaFraudulentaVO getEmpresaFraudulenta() {
		return empresaFraudulenta;
	}
	public void setEmpresaFraudulenta(EmpresaFraudulentaVO empresaFraudulenta) {
		this.empresaFraudulenta = empresaFraudulenta;
	}

	public String getEmpFechaNacimiento(){
		String empFechaNacimiento = null;
		if (getEmpresa()!=null) empFechaNacimiento = Utils.formatDDMMYYYY(getEmpresa().getFechaNacimiento());
		return empFechaNacimiento;
	}
	
	public String getEmpFechaAlta(){
		String empFechaAlta = null;
		if (getEmpresa()!=null) empFechaAlta = Utils.formatDDMMYYYY(getEmpresa().getFechaAlta());	
		return empFechaAlta;
	}

	public void setIdRegistroValidar(String idRegistroValidar) {
		this.idRegistroValidar = idRegistroValidar;
	}

	public String getIdRegistroValidar() {
		return idRegistroValidar;
	}

	public long getIdEmpresaFraudulenta() {
		return idEmpresaFraudulenta;
	}

	public void setIdEmpresaFraudulenta(long idEmpresaFraudulenta) {
		this.idEmpresaFraudulenta = idEmpresaFraudulenta;
	}

	
	
}
