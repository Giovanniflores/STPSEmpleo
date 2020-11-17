package mx.gob.stps.portal.web.infra.helper;

import java.io.Serializable;

import mx.gob.stps.portal.utils.Catalogos.TIPO_DISCAPACIDAD;

public class DiscapacidadBO implements Serializable {

	private static final long serialVersionUID = -3229837838818371374L;
	
	private final int DISCAPACIDADES_LONGITUD = 5;

	private int mental;
	
	private int motora;
	
	private int visual;
	
	private int ninguna;
	
	private int auditiva;
	
	private int intelectual;
	
	private String discapacidades;
	
	private String descripcionDiscapacidades;
	
	public DiscapacidadBO(String discapacidades) {
		if (null != discapacidades) {
			this.discapacidades = discapacidades;
			if (!this.isDiscapacitado()) this.ninguna = 1;
			if (this.isDiscapacitadoAuditivo()) this.auditiva = 1;
			if (this.isDiscapacitadoIntelectual()) this.intelectual = 1;
			if (this.isDiscapacitadoMental()) this.mental = 1;
			if (this.isDiscapacitadoMotor()) this.motora = 1;
			if (this.isDiscapacitadoVisual()) this.visual = 1;
		}
		else this.discapacidades = "00000";
	}
	
	public int getMental() {
		return mental;
	}

	public void setMental(int mental) {
		this.mental = mental;
	}

	public int getMotora() {
		return motora;
	}

	public void setMotora(int motora) {
		this.motora = motora;
	}

	public int getVisual() {
		return visual;
	}

	public void setVisual(int visual) {
		this.visual = visual;
	}

	public int getNinguna() {
		return ninguna;
	}

	public void setNinguna(int ninguna) {
		this.ninguna = ninguna;
	}

	public int getAuditiva() {
		return auditiva;
	}

	public void setAuditiva(int auditiva) {
		this.auditiva = auditiva;
	}

	public int getIntelectual() {
		return intelectual;
	}

	public void setIntelectual(int intelectual) {
		this.intelectual = intelectual;
	}
	
	public boolean isDiscapacitadoAuditivo() {		
		if (discapacidades != null && discapacidades.length() == DISCAPACIDADES_LONGITUD)
			return ("1".equals(String.valueOf(this.discapacidades.charAt(0))) ? true : false);
		else setDiscapacitadoNinguna();
		return false;
	}

	public boolean isDiscapacitadoIntelectual() {
		if (discapacidades != null && discapacidades.trim().length() == DISCAPACIDADES_LONGITUD)		
			return ("1".equals(String.valueOf(this.discapacidades.trim().charAt(1))) ? true : false);
		else setDiscapacitadoNinguna();
		return false;		
	}

	public boolean isDiscapacitadoMental() {
		if (discapacidades != null && discapacidades.length() == DISCAPACIDADES_LONGITUD)		
			return ("1".equals(String.valueOf(this.discapacidades.trim().charAt(2))) ? true : false);
		else setDiscapacitadoNinguna();
		return false;		
	}

	public boolean isDiscapacitadoMotor() {
		if (discapacidades != null && discapacidades.length() == DISCAPACIDADES_LONGITUD)		
			return ("1".equals(String.valueOf(this.discapacidades.trim().charAt(3))) ? true : false);
		else setDiscapacitadoNinguna();
		return false;		
	}

	public boolean isDiscapacitadoVisual() {
		if (discapacidades != null && discapacidades.length() == DISCAPACIDADES_LONGITUD)			
			return ("1".equals(String.valueOf(this.discapacidades.trim().charAt(4))) ? true : false);
		else setDiscapacitadoNinguna();
		return false;
	}
	
	public boolean isDiscapacitado() {
		if (this.isDiscapacitadoAuditivo())
			return true;
		if (this.isDiscapacitadoIntelectual())
			return true;
		if (this.isDiscapacitadoMental())
			return true;
		if (this.isDiscapacitadoMotor())
			return true;
		if (this.isDiscapacitadoVisual())
			return true;
		return false;
	}

	public void setDiscapacitadoAuditivo(boolean valor) {
		this.setCharN(valor, 0);
	}

	public void setDiscapacitadoIntelectual(boolean valor) {
		this.setCharN(valor, 1);
	}

	public void setDiscapacitadoMental(boolean valor) {
		this.setCharN(valor, 2);
	}

	public void setDiscapacitadoMotor(boolean valor) {
		this.setCharN(valor, 3);
	}

	public void setDiscapacitadoVisual(boolean valor) {
		this.setCharN(valor, 4);
	}

	public void setDiscapacitadoNinguna() {
		this.discapacidades = new String("00000");
	}
	
	public void setIdDiscapacidad(long idDiscapacidad) {
		if (idDiscapacidad == TIPO_DISCAPACIDAD.AUDITIVA.getIdOpcion())
			this.setDiscapacitadoAuditivo(true);
		else if (idDiscapacidad == TIPO_DISCAPACIDAD.INTELECTUAL.getIdOpcion())
			this.setDiscapacitadoIntelectual(true);
		else if (idDiscapacidad == TIPO_DISCAPACIDAD.MENTAL.getIdOpcion())
			this.setDiscapacitadoMental(true);
		else if (idDiscapacidad == TIPO_DISCAPACIDAD.MOTRIZ.getIdOpcion())
			this.setDiscapacitadoMotor(true);
		else if (idDiscapacidad == TIPO_DISCAPACIDAD.VISUAL.getIdOpcion())
			this.setDiscapacitadoVisual(true);
		else
			this.setDiscapacitadoNinguna();
	}
	
	public String getDiscapacidades() {
		return discapacidades;
	}
	
	public void setDescripcionDiscapacidades() {
		StringBuilder builder = new StringBuilder();
		if (!this.isDiscapacitado())
			builder.append("Ninguna");
		else {
			if (this.isDiscapacitadoAuditivo() == true)
				builder.append("Auditiva ");
			if (this.isDiscapacitadoIntelectual() == true)
				builder.append("Intelectual ");
			if (this.isDiscapacitadoMental() == true)
				builder.append("Mental ");
			if (this.isDiscapacitadoMotor() == true)
				builder.append("Motora ");
			if (this.isDiscapacitadoVisual() == true)
				builder.append("Visual ");
		}
		this.descripcionDiscapacidades = builder.toString().trim();
	}
	
	public String getDescripcionDiscapacidades() {
		setDescripcionDiscapacidades();
		return descripcionDiscapacidades;
	}
	
	private void setCharN(boolean valor, int n) {
		String str = null;
		if (n == 0)
			str = new String((valor ? "1" : "0"));
		else
			str = String.valueOf(discapacidades.charAt(0));
		if (n == 1)
			str = str + new String((valor ? "1" : "0"));
		else
			str = str + String.valueOf(discapacidades.charAt(1));
		if (n == 2)
			str = str + new String((valor ? "1" : "0"));
		else
			str = str + String.valueOf(discapacidades.charAt(2));
		if (n == 3)
			str = str + new String((valor ? "1" : "0"));
		else
			str = str + String.valueOf(discapacidades.charAt(3));
		if (n == 4)
			str = str + new String((valor ? "1" : "0"));
		else
			str = str + String.valueOf(discapacidades.charAt(4));
		discapacidades = new String(str);
	}
}
