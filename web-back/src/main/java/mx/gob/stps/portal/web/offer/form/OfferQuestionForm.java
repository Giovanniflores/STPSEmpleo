package mx.gob.stps.portal.web.offer.form;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import mx.gob.stps.portal.web.offer.wrapper.OfertaJB;
import mx.gob.stps.portal.web.offer.wrapper.PerfilJB;
import mx.gob.stps.portal.core.oferta.pregunta.vo.OfertaPreguntaVO;

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
	private boolean postulated;
	private List<OfertaPreguntaVO> questions;
	private OfertaJB offer;
	private boolean inscritoPPC;
	private boolean isChanneling;
	private boolean checkChanneling;
	
	private String argumet;
	private String urlRecuperaContrasena;

	public boolean isChanneling() {
		return isChanneling;
	}

	public void setChanneling(boolean isChanneling) {
		this.isChanneling = isChanneling;
	}

	public boolean isCheckChanneling() {
		return checkChanneling;
	}

	public void setCheckChanneling(boolean checkChanneling) {
		this.checkChanneling = checkChanneling;
	}

	private long dbDurationTimeMs;
	
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
	
	public OfertaJB getOfertaJB() {
		return this.offer;
	}

	public void setOfertaJB(OfertaJB offer) {
		this.offer = offer;
	}
	
	public long getIdOfertaPregunta() {
		return idOfertaPregunta;
	}

	public void setIdOfertaPregunta(long idOfertaPregunta) {
		this.idOfertaPregunta = idOfertaPregunta;
	}

	/**
	 * @return the argumet
	 */
	public String getArgumet() {
		return argumet;
	}

	/**
	 * @param argumet the argumet to set
	 */
	public void setArgumet(String argumet) {
		this.argumet = argumet;
	}
	
	/**
	 * @return the urlRecuperaContrasena
	 */
	public String getUrlRecuperaContrasena() {
		return urlRecuperaContrasena;
	}

	/**
	 * @param urlRecuperaContrasena the urlRecuperaContrasena to set
	 */
	public void setUrlRecuperaContrasena(String urlRecuperaContrasena) {
		this.urlRecuperaContrasena = urlRecuperaContrasena;
	}

	public long getDbDurationTimeMs() {
		return dbDurationTimeMs;
	}

	public void setDbDurationTimeMs(long dbDurationTimeMs) {
		this.dbDurationTimeMs = dbDurationTimeMs;
	}

	public void reset(){
		this.idOfertaEmpleo = 0;
		this.idCandidato = 0;
		this.pregunta = null;
		this.respuesta = null;
		this.idOfertaPregunta = 0;
		this.fechaAlta = null;
		this.fechaRespuesta = null;
		this.profile = null;
		this.compatibility = 0;
		this.postulated = false;
		this.questions = new ArrayList<OfertaPreguntaVO>();
		this.offer = null;
		this.argumet = null;
		this.dbDurationTimeMs = 0;
	}

	public boolean isInscritoPPC() {
		return inscritoPPC;
	}

	public void setInscritoPPC(boolean inscritoPPC) {
		this.inscritoPPC = inscritoPPC;
	}
	
}