package mx.gob.stps.portal.web.company.form;

import java.util.List;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;

import org.apache.struts.action.ActionForm;

public class SearchCandidatesForm extends ActionForm {

	private static final long serialVersionUID = 1080634564438883028L;
	
	private List<CandidatoVo> candidates;

	public List<CandidatoVo> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<CandidatoVo> candidates) {
		this.candidates = candidates;
	}
}
