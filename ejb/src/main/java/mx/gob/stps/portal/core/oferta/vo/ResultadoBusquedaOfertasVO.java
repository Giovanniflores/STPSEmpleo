package mx.gob.stps.portal.core.oferta.vo;

import java.io.Serializable;
import java.util.Date;

import mx.gob.stps.portal.core.infra.utils.Constantes;

public class ResultadoBusquedaOfertasVO implements Serializable {

	private static final long serialVersionUID = -951298537353631690L;
	
	private Long jobId;
	private String jobOfferName = "";
	private String jobOfferDescSkills = "";
	private String jobOfferDescYearsExp = "";
	private String jobOfferDescLangs = "";
	private String jobOfferDescMinAge = "";
	private String jobOfferDescMaxAge = "";
    private String locationEntity = "";
    private String locationMunicipality = "";
    private String jobOfferDescFunctions = "";
    private Long personTypeId;
    private String personFirstname = "";
    private String personLastname = "";
    private String personSurname = "";
    private String enterpriseRegisteredName = "";
    private Double netSalary = 0.0;
    private Date publicationDate = null;
    private int compatibility = 0;
    private String calle;
    private String numeroExterior;
    private long idPropietario;
    private String codigoPostal = "";
    
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getNumeroExterior() {
		return numeroExterior;
	}
	public void setNumeroExterior(String numeroExterior) {
		this.numeroExterior = numeroExterior;
	}
	public long getIdPropietario() {
		return idPropietario;
	}
	public void setIdPropietario(long idPropietario) {
		this.idPropietario = idPropietario;
	}
	public Long getJobId() {
		return jobId;
	}
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}
	public String getJobOfferName() {
		return jobOfferName;
	}
	public void setJobOfferName(String jobOfferName) {
		this.jobOfferName = jobOfferName;
	}
	public String getJobOfferDescSkills() {
		return jobOfferDescSkills;
	}
	public void setJobOfferDescSkills(String jobOfferDescSkills) {
		this.jobOfferDescSkills = jobOfferDescSkills;
	}
	public String getJobOfferDescYearsExp() {
		return jobOfferDescYearsExp;
	}
	public void setJobOfferDescYearsExp(String jobOfferDescYearsExp) {
		this.jobOfferDescYearsExp = jobOfferDescYearsExp;
	}
	public String getJobOfferDescLangs() {
		return jobOfferDescLangs;
	}
	public void setJobOfferDescLangs(String jobOfferDescLangs) {
		this.jobOfferDescLangs = jobOfferDescLangs;
	}
	public String getJobOfferDescMinAge() {
		return jobOfferDescMinAge;
	}
	public void setJobOfferDescMinAge(String jobOfferDescMinAge) {
		this.jobOfferDescMinAge = jobOfferDescMinAge;
	}
	public String getJobOfferDescMaxAge() {
		return jobOfferDescMaxAge;
	}
	public void setJobOfferDescMaxAge(String jobOfferDescMaxAge) {
		this.jobOfferDescMaxAge = jobOfferDescMaxAge;
	}
	public String getLocationEntity() {
		return locationEntity;
	}
	public void setLocationEntity(String locationEntity) {
		this.locationEntity = locationEntity;
	}
	public String getLocationMunicipality() {
		return locationMunicipality;
	}
	public void setLocationMunicipality(String locationMunicipality) {
		this.locationMunicipality = locationMunicipality;
	}
//	public String getEnterpriseName() {
//		return enterpriseName;
//	}
//	public void setEnterpriseName(String enterpriseName) {
//		this.enterpriseName = enterpriseName;
//	}
	public Long getPersonTypeId() {
		return personTypeId;
	}
	public void setPersonTypeId(Long personTypeId) {
		this.personTypeId = personTypeId;
	}
	public String getPersonFirstname() {
		return personFirstname;
	}
	public void setPersonFirstname(String personFirstname) {
		this.personFirstname = personFirstname;
	}
	public String getPersonLastname() {
		return personLastname;
	}
	public void setPersonLastname(String personLastname) {
		this.personLastname = personLastname;
	}
	public String getPersonSurname() {
		return personSurname;
	}
	public void setPersonSurname(String personSurname) {
		this.personSurname = personSurname;
	}
	public String getEnterpriseRegisteredName() {
		return enterpriseRegisteredName;
	}
	public void setEnterpriseRegisteredName(String enterpriseRegisteredName) {
		this.enterpriseRegisteredName = enterpriseRegisteredName;
	}
	public Double getNetSalary() {
		return netSalary;
	}
	public void setNetSalary(Double netSalary) {
		this.netSalary = netSalary;
	}
	public Date getPublicationDate() {
		return publicationDate;
	}
	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}
	public int getCompatibility() {
		return compatibility;
	}
	public void setCompatibility(int compatibility) {
		this.compatibility = compatibility;
	}
    
	// Utility
	public String getEnterpriseName() {
		String enterpriseName = null;
		if (Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() == personTypeId) {
			enterpriseName = personFirstname + (personLastname != null ? (" " + personLastname) : "") + (personSurname != null ? (" " + personSurname) : "");
		} else if(Constantes.TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() == personTypeId) {
			enterpriseName = enterpriseRegisteredName;
		}
		return enterpriseName;
	}
	public String getJobOfferDescFunctions() {
		return jobOfferDescFunctions;
	}
	public void setJobOfferDescFunctions(String jobOfferDescFunctions) {
		this.jobOfferDescFunctions = jobOfferDescFunctions;
	}
    
}
