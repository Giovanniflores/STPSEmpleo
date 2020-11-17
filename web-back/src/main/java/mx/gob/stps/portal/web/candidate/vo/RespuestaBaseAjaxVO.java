package mx.gob.stps.portal.web.candidate.vo;

public class RespuestaBaseAjaxVO {

	public RespuestaBaseAjaxVO(){
	}
	
	public RespuestaBaseAjaxVO(String message,String statusOper){
		this.message	=	message;
		this.statusOper = 	statusOper;
	}
	
	public RespuestaBaseAjaxVO(String message,String statusOper,String curp){
		this.message	=	message;
		this.statusOper = 	statusOper;
		if(curp != null && !curp.isEmpty()){
			this.curp = curp;
		}
	}
	
	
	private String message;
	private String statusOper;
	private String curp;
	
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatusOper() {
		return statusOper;
	}
	public void setStatusOper(String statusOper) {
		this.statusOper = statusOper;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}
	
	
}
