package mx.gob.stps.portal.core.oferta.vo;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

public class BusquedaOfertasVO implements Serializable {

	private static final long serialVersionUID = 3613668879130378922L;

	private Integer subprogram;
	private Long idArea;
    private Long idSubArea;

	// JOBS
	private List<Integer> jobIds = new ArrayList<Integer>(); // Indexed Fields
	private String calle;
	private String numeroExterior;
	private long idPropietario;


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

	public List<Integer> getJobIds() {
		return jobIds;
	}

	public void setJobIds(List<Integer> jobIds) {
		this.jobIds = jobIds;  
	}

	// <+++++ LOCATION <+++++>

	private Integer locationEntityId = null;

	public Integer getLocationEntityId() {
		return locationEntityId;
	}

	public void setLocationEntityId(Integer locationEntityId) {
		this.locationEntityId = locationEntityId;
	}

	private List<Integer> locationMunicipalityIds = new ArrayList<Integer>();	// Indexed Fields

	public List<Integer> getLocationMunicipalityIds() {
		return locationMunicipalityIds;
	}

	public void setLocationMunicipalityIds(List<Integer> locationMunicipalityIds) {
		this.locationMunicipalityIds = locationMunicipalityIds;
	}

	private Integer locationRegionId = null;

	public Integer getLocationRegionId() {
		return locationRegionId;
	}

	public void setLocationRegionId(Integer locationRegionId) {
		this.locationRegionId = locationRegionId;
	}

	// <+++++ PAYMENT +++++>

	private Double minSalary = null;
	private Double maxSalary = null;

	public Double getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(Double minSalary) {
		this.minSalary = minSalary;
	}

	public Double getMaxSalary() {
		return maxSalary;
	}

	public void setMaxSalary(Double maxSalary) {
		this.maxSalary = maxSalary;
	}

	//    // <+++++ AGE +++++>

	private Integer age = null;

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	// <+++++ EDUCATION <+++++>

	private Integer educationGradeId = null;

	public Integer getEducationGradeId() {
		return educationGradeId;
	}

	public void setEducationGradeId(Integer educationGradeId) {
		this.educationGradeId = educationGradeId;
	}

	private List<Integer> educationCareerIds = new ArrayList<Integer>();   // Indexed Fields

	public List<Integer> getEducationCareerIds() {
		return educationCareerIds;
	}

	public void setEducationCareerIds(List<Integer> educationCareerIds) {
		this.educationCareerIds = educationCareerIds;
	}

	// <+++++ EMPLOYMENT <+++++>

	private Integer employmentTypeId = null;

	public Integer getEmploymentTypeId() {
		return employmentTypeId;
	}

	public void setEmploymentTypeId(Integer employmentId) {
		this.employmentTypeId = employmentId;
	}

	private Integer contractId = null;

	public Integer getContractTypeId() {
		return contractId;
	}

	public void setContractTypeId(Integer contractId) {
		this.contractId = contractId;
	}

	// <<+++++ ENTERPRISE <+++++>

	private List<Integer> enterpriseActivityIds = new ArrayList<Integer>(); // Indexed Fields

	public List<Integer> getEnterpriseActivityIds() {
		return enterpriseActivityIds;
	}

	public void setEnterpriseActivityIds(List<Integer> enterpriseActivityIds) {
		this.enterpriseActivityIds = enterpriseActivityIds;
	}

	public Integer getSubprogram() {
		return subprogram;
	}

	public void setSubprogram(Integer subprogram) {
		this.subprogram = subprogram;
	}

	private Integer codigoPostal=0;

	public Integer getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(Integer codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public Long getIdArea() {
		return idArea;
	}

	public void setIdArea(Long idArea) {
		this.idArea = idArea;
	}

	public Long getIdSubArea() {
		return idSubArea;
	}

	public void setIdSubArea(Long idSubArea) {
		this.idSubArea = idSubArea;
	}
}
