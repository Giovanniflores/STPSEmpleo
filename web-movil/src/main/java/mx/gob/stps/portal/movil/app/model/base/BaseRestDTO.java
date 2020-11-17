package mx.gob.stps.portal.movil.app.model.base;

public class BaseRestDTO {

	
	private String version = "2015 04 29 03:21";
	private String result ="OK";
	private String error = "OK";

	public String getResult() {
		return result;
	}

	public void setResult(String error) {
		this.result = error;
		/* quitar despues migracion de todos los apps a result */
		this.error = error;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	

		
}
