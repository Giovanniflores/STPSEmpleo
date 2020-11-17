package mx.gob.stps.portal.core.candidate.vo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class CandidatoOtroEstudioVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8893392701818290965L;
	
	private long idCandidatoOtroEstudio;

	private long idCandidato;
	
	private long idOtroEstudio;

	private long idTipoEstudio;

	private long idEstatusAcademico;

	private String institucion;

	private Date fechaInicio;

	private Date fechaFin;
	
	private String nombreEstudio;
	
	private int principal;
	
	private int row;

	/**
	 * @return the idCandidatoOtroEstudio
	 */
	public long getIdCandidatoOtroEstudio() {
		return idCandidatoOtroEstudio;
	}

	/**
	 * @param idCandidatoOtroEstudio the idCandidatoOtroEstudio to set
	 */
	public void setIdCandidatoOtroEstudio(long idCandidatoOtroEstudio) {
		this.idCandidatoOtroEstudio = idCandidatoOtroEstudio;
	}

	/**
	 * @return the idCandidato
	 */
	public long getIdCandidato() {
		return idCandidato;
	}

	/**
	 * @param idCandidato the idCandidato to set
	 */
	public void setIdCandidato(long idCandidato) {
		this.idCandidato = idCandidato;
	}

	/**
	 * @return the idOtroEstudio
	 */
	public long getIdOtroEstudio() {
		return idOtroEstudio;
	}

	/**
	 * @param idOtroEstudio the idOtroEstudio to set
	 */
	public void setIdOtroEstudio(long idOtroEstudio) {
		this.idOtroEstudio = idOtroEstudio;
	}

	/**
	 * @return the idTipoEstudio
	 */
	public long getIdTipoEstudio() {
		return idTipoEstudio;
	}

	/**
	 * @param idTipoEstudio the idTipoEstudio to set
	 */
	public void setIdTipoEstudio(long idTipoEstudio) {
		this.idTipoEstudio = idTipoEstudio;
	}

	/**
	 * @return the idEstatusAcademico
	 */
	public long getIdEstatusAcademico() {
		return idEstatusAcademico;
	}

	/**
	 * @param idEstatusAcademico the idEstatusAcademico to set
	 */
	public void setIdEstatusAcademico(long idEstatusAcademico) {
		this.idEstatusAcademico = idEstatusAcademico;
	}

	/**
	 * @return the institucion
	 */
	public String getInstitucion() {
		return institucion;
	}

	/**
	 * @param institucion the institucion to set
	 */
	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}

	/**
	 * @return the fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the fechaFin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * @return the nombreEstudio
	 */
	public String getNombreEstudio() {
		return nombreEstudio;
	}

	/**
	 * @param nombreEstudio the nombreEstudio to set
	 */
	public void setNombreEstudio(String nombreEstudio) {
		this.nombreEstudio = nombreEstudio;
	}

	/**
	 * @return the principal
	 */
	public int getPrincipal() {
		return principal;
	}

	/**
	 * @param principal the principal to set
	 */
	public void setPrincipal(int principal) {
		this.principal = principal;
	}
	
	public String getSelectedDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if (null == date) return "";
		StringBuilder day = new StringBuilder();
		int dateDay = calendar.get(Calendar.DAY_OF_MONTH);
		for (int i = 1; i<32; i++) {
			String iday = "0" + i;
			if (iday.length() > 2) iday = iday.substring(1,3);
			day.append("<option value=\"" + iday + "\"");
			if (i==dateDay)
				day.append(" selected=\"selected\" ");
			day.append(">" + iday + "</option>\n");
		}
		return day.toString();
	}
	
	public String getSelectedMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if (null == date) return "";
		StringBuilder day = new StringBuilder();
		int month = calendar.get(Calendar.MONTH) + 1;
		for (int i = 1; i<13; i++) {
			String iday = "0" + i;
			if (iday.length() > 2) iday = iday.substring(1,3);
			day.append("<option value=\"" + iday + "\"");
			if (i==month)
				day.append(" selected=\"selected\" ");
			day.append(">" + getLblMonth(i) + "</option>\n");
		}
		return day.toString();
	}
	
	public String getSelectedYear(Date date) {
		Calendar cal = Calendar.getInstance();
    	cal.setTime(new Date());
    	int cyear = cal.get(Calendar.YEAR);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if (null == date) return "";
		StringBuilder day = new StringBuilder();
		int year = calendar.get(Calendar.YEAR);
		for (int i = 1982; i<cyear+1; i++) {
			day.append("<option value=\"" + i + "\"");
			if (i==year)
				day.append(" selected=\"selected\" ");
			day.append(">" + i + "</option>\n");
		}
		return day.toString();
	}
	
	public String getLblMonth(int imonth) {
		StringBuilder month = new StringBuilder();
		switch(imonth) {
			case 1 : month.append("Enero"); break;
			case 2 : month.append("Febrero"); break;
			case 3 : month.append("Marzo"); break;
			case 4 : month.append("Abril"); break;
			case 5 : month.append("Mayo"); break;
			case 6 : month.append("Junio"); break;
			case 7 : month.append("Julio"); break;
			case 8 : month.append("Agosto"); break;
			case 9 : month.append("Septiembre"); break;
			case 10 : month.append("Octubre"); break;
			case 11 : month.append("Noviembre"); break;
			case 12 : month.append("Diciembre"); break;
			default : month.append("");
		}
		return month.toString();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CandidatoOtroEstudioVO [idCandidatoOtroEstudio=");
		builder.append(idCandidatoOtroEstudio);
		builder.append(", idCandidato=");
		builder.append(idCandidato);
		builder.append(", idOtroEstudio=");
		builder.append(idOtroEstudio);
		builder.append(", idTipoEstudio=");
		builder.append(idTipoEstudio);
		builder.append(", idEstatusAcademico=");
		builder.append(idEstatusAcademico);
		builder.append(", institucion=");
		builder.append(institucion);
		builder.append(", fechaInicio=");
		builder.append(fechaInicio);
		builder.append(", fechaFin=");
		builder.append(fechaFin);
		builder.append(", nombreEstudio=");
		builder.append(nombreEstudio);
		builder.append(", principal=");
		builder.append(principal);
		builder.append("]");
		return builder.toString();
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}
}
