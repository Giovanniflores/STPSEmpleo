package mx.gob.stps.portal.web.candidate.vo;

import mx.gob.stps.portal.web.infra.vo.ResultVO;

public class ExpectativaVOForm {

	private long idExpectativaLaboral;
	private long idExpectativaLugar;

	private ResultVO msg;

	public long getIdExpectativaLaboral() {
		return idExpectativaLaboral;
	}

	public void setIdExpectativaLaboral(long idExpectativaLaboral) {
		this.idExpectativaLaboral = idExpectativaLaboral;
	}

	public long getIdExpectativaLugar() {
		return idExpectativaLugar;
	}

	public void setIdExpectativaLugar(long idExpectativaLugar) {
		this.idExpectativaLugar = idExpectativaLugar;
	}

	public ResultVO getMsg() {
		return msg;
	}

	public void setMsg(ResultVO msg) {
		this.msg = msg;
	}
}