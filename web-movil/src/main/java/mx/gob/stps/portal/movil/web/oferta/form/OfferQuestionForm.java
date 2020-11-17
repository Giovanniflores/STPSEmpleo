package mx.gob.stps.portal.movil.web.oferta.form;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mx.gob.stps.portal.core.oferta.pregunta.vo.OfertaPreguntaVO;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 * Clase ActionForm que cuenta con el mapeo de sus propiedades con los controles de la página
 * 
 * @author Sergio Téllez
 *
 */
public class OfferQuestionForm extends ActionForm {

	private static final long serialVersionUID = 2227581688724326606L;
	private long idOfertaEmpleo;
	private long idCandidato;
	private String pregunta;
	private String respuesta;
	private long idOfertaPregunta;
	private Date fechaAlta;
	private Date fechaRespuesta;
	private PerfilJB profile;
	private int compatibility;
	private int compatibilityLimit;
	private boolean postulated;
	private List<OfertaPreguntaVO> questions;
	private OfertaJB offer;
	
	private long idEmpresa;
	private String empresaNombre;
	private String tituloOferta;
	private String tipoContrato;
	private String horarioLaboral;
	
	private int numeroPreguntasOferta;
	
	private long idPerfil;
	
	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		if (null == request.getParameter("pregunta") || "".equals(request.getParameter("pregunta")))
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "Debe escribir una pregunta"));
		request.setAttribute(Globals.ERROR_KEY, errors);		
        if (errors.isEmpty()) errors = null;
        else request.setAttribute(Globals.ERROR_KEY, errors);
        return errors;
	}
	
	public long getIdOfertaEmpleo() {
		return idOfertaEmpleo;
	}

	public void setIdOfertaEmpleo(long idOfertaEmpleo) {
		this.idOfertaEmpleo = idOfertaEmpleo;
	}

	public long getIdCandidato() {
		return idCandidato;
	}

	public void setIdCandidato(long idCandidato) {
		this.idCandidato = idCandidato;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaRespuesta() {
		return fechaRespuesta;
	}

	public void setFechaRespuesta(Date fechaRespuesta) {
		this.fechaRespuesta = fechaRespuesta;
	}
	
	public PerfilJB getPerfilJB() {
		return profile;
	}
	
	public void setPerfilJB(PerfilJB vo) {
		this.profile = vo;
	}
	
	public int getCompatibility() {
		return this.compatibility;
	}
	
	public void setCompatibility(int compatibility) {
		this.compatibility = compatibility;
	}
	
	public boolean isPostulated() {
		return this.postulated;
	}
	
	public void setPostulated(boolean postulated) {
		this.postulated = postulated;
	}
	
	public List<OfertaPreguntaVO> getOfertaPreguntasList() {
		return this.questions;
	}
	
	public void setOfertaPreguntasList(List<OfertaPreguntaVO> list) {
		this.questions = list;
	}
	
	
	
	public OfertaJB getOffer() {
		return offer;
	}

	public void setOffer(OfertaJB offer) {
		this.offer = offer;
	}

	public long getIdOfertaPregunta() {
		return idOfertaPregunta;
	}

	public void setIdOfertaPregunta(long idOfertaPregunta) {
		this.idOfertaPregunta = idOfertaPregunta;
	}

	public PerfilJB getProfile() {
		return profile;
	}

	public void setProfile(PerfilJB profile) {
		this.profile = profile;
	}

	public List<OfertaPreguntaVO> getQuestions() {
		return questions;
	}

	public void setQuestions(List<OfertaPreguntaVO> questions) {
		this.questions = questions;
	}

	public long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getEmpresaNombre() {
		return empresaNombre;
	}

	public void setEmpresaNombre(String empresaNombre) {
		this.empresaNombre = empresaNombre;
	}

	public String getTituloOferta() {
		return tituloOferta;
	}

	public void setTituloOferta(String tituloOferta) {
		this.tituloOferta = tituloOferta;
	}

	public String getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	public String getHorarioLaboral() {
		return horarioLaboral;
	}

	public void setHorarioLaboral(String horarioLaboral) {
		this.horarioLaboral = horarioLaboral;
	}

	public void setIdPerfil(long idPerfil) {
		this.idPerfil = idPerfil;
	}

	public long getIdPerfil() {
		return idPerfil;
	}

	public void setCompatibilityLimit(int compatibilityLimit) {
		this.compatibilityLimit = compatibilityLimit;
	}

	public int getCompatibilityLimit() {
		return compatibilityLimit;
	}

	public void setNumeroPreguntasOferta(int numeroPreguntasOferta) {
		this.numeroPreguntasOferta = numeroPreguntasOferta;
	}

	public int getNumeroPreguntasOferta() {
		return numeroPreguntasOferta;
	}
	
	
}