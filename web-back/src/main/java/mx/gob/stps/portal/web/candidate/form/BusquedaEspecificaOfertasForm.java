package mx.gob.stps.portal.web.candidate.form;

import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.core.oferta.vo.BusquedaOfertasVO;
import mx.gob.stps.portal.core.oferta.vo.ResultadoBusquedaOfertasVO;
import mx.gob.stps.portal.web.infra.utils.Utils;

import org.apache.struts.action.ActionForm;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BusquedaEspecificaOfertasForm extends ActionForm {

    private static final long serialVersionUID = 268974139972754847L;

    private boolean candidateLogged = false;
    
    public boolean isCandidateLogged() {
		return candidateLogged;
	}

	public void setCandidateLogged(boolean candidateLogged) {
		this.candidateLogged = candidateLogged;
	}

	// TODO: Get rid of this method
    public String getIndexedSuffix() {
        return "Ids";
    }

    /**
     * ----- Accessor methods for Indexed Properties -----
     * NOTE: In order to work the binding, the indexed property/id in view must match the bean accessor methods.
     * E.g., property = jobIds, getJobIds(int index) & setJobItemIds(int index, int value)
    */

    // <<+++++ JOBS +++++>

    private List<Integer> jobIds = new ArrayList<Integer>();
    private List<String> jobs = new ArrayList<String>(); // The indexed job id:value feed from view
    private List<String> jobsTxt = new ArrayList<String>();
    private String calle;
    private String numeroExterior;
    private long idPropietario;
    
    private Long idArea;
    private Long idSubArea;
    
    public long getIdPropietario() {
		return idPropietario;
	}

	public void setIdPropietario(long idPropietario) {
		this.idPropietario = idPropietario;
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

	public String getJobIndexedPrefix() {
        return "jobs";
    }

    public List<Integer> getJobIds() {
        return jobIds;
    }
    
    public void setJobIds(List<Integer> jobIds) {
        this.jobIds =jobIds;  
    }
    
    public List<String> getJobs() {
        return jobs;
    }
    
    public void setJobs(List<String> jobs) {
        this.jobs =jobs;  
    }

    /**
     * Getter method for jobs indexed property
     * @param index - the index of the collection
     * @return - the index value within collection
     */
    public String getJobs(int index) {
        return jobs.get(index);
    }

    public void setJobs(int index, String value) {
        jobs.add(value);
    }
    
    // SUBPROGRAM
    
    private String subprogram = "";
    private Integer subprogramId = 0;
    private String subprogramTxt = "";
    private Integer codigoPostal=null;
    
    //AREA
    private String area = "";
    private Integer areaId = 0;
    private String areaTxt = "";
    private String subarea = "";
    private Integer subareaId = 0;
    private String subareaTxt = "";
    
    public Integer getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(Integer codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	
	public Integer getSubprogramId() {
		return subprogramId;
	}

	public void setSubprogramId(Integer subprogramId) {
		this.subprogramId = subprogramId;
	}

	public String getSubprogram() {
		return subprogram;
	}

	public void setSubprogram(String subprogram) {
		this.subprogram = subprogram;
	}

	public String getSubprogramTxt() {
		return subprogramTxt;
	}

	public void setSubprogramTxt(String subprogramTxt) {
		this.subprogramTxt = subprogramTxt;
	}

	// <+++++ LOCATION +++++>
	
	private List<CatalogoOpcionVO> locationEntityCatalog;
    private Integer locationEntityId = 0;
    private String locationEntity = "";	// The locationEntity id:value feed from view
    private String locationEntityTxt = "";

    public List<CatalogoOpcionVO> getLocationEntityCatalog() {
        return locationEntityCatalog;
    }

    public void setLocationStateCatalog(List<CatalogoOpcionVO> locationEntityCatalog) {
        this.locationEntityCatalog = locationEntityCatalog;
    }

    public Integer getLocationEntityId() {
        return locationEntityId;
    }

    public void setLocationEntityId(Integer locationEntityId) {
        this.locationEntityId = locationEntityId;
    }
    
    public String getLocationEntity() {
    	return locationEntity;
    }
    
    public void setLocationEntity(String locationEntity) {
    	this.locationEntity = locationEntity;
    }

	private List<Integer> locationMunicipalityIds = new ArrayList<Integer>();
    private List<String> locationMunicipalities = new ArrayList<String>(); // The indexed locationMunicipality id:value feed from view
    private List<String> locationMunicipalitiesTxt = new ArrayList<String>();

    public String getLocationMunicipalityIndexedPrefix() {
        return "locationMunicipalities";
    }

    public List<Integer> getLocationMunicipalityIds() {
        return locationMunicipalityIds;
    }
    
    public void setLocationMunicipalityIds(List<Integer> locationMunicipalityIds) {
        this.locationMunicipalityIds = locationMunicipalityIds;
    }
    
    public List<String> getLocationMunicipalities() {
    	return locationMunicipalities;
    }
    
    public void setLocationMunicipalities(List<String> locationMunicipalities) {
    	this.locationMunicipalities = locationMunicipalities;
    }

    /**
     * Getter method for locationMunicpalities indexed property
     * @param index - the index of the collection
     * @return - the index value within collection
     */
    public String getLocationMunicipalities(int index) {
        return locationMunicipalities.get(index);
    }

    public void setLocationMunicipalities(int index, String value) {
        locationMunicipalities.add(value);
    }


    private List<CatalogoOpcionVO> locationRegionCatalog;
    private Integer locationRegionId = 0;
    private String locationRegion = ""; // The locationRegion id:value feed from view
    private String locationRegionTxt = "";

    public List<CatalogoOpcionVO> getLocationRegionCatalog() {
        return locationRegionCatalog;
    }

    public void setLocationRegionCatalog(List<CatalogoOpcionVO> locationRegionCatalog) {
        this.locationRegionCatalog = locationRegionCatalog;
    }

    public Integer getLocationRegionId() {
        return locationRegionId;
    }

    public void setLocationRegionId(Integer locationRegionId) {
        this.locationRegionId = locationRegionId;
    }
    
    public String getLocationRegion() {
		return locationRegion;
	}

	public void setLocationRegion(String locationRegion) {
		this.locationRegion = locationRegion;
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

    // <+++++ AGE +++++>

    private Integer age = null;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    // <+++++ EDUCATION +++++>

    private List<CatalogoOpcionVO> educationGradeCatalog;
    private Integer educationGradeId = 0;
    private String educationGrade = ""; // The educationGrade id:value feed from view
    private String educationGradeTxt = "";

    public List<CatalogoOpcionVO> getEducationGradeCatalog() {
        return educationGradeCatalog;
    }

    public void setEducationGradeCatalog(List<CatalogoOpcionVO> educationGradeCatalog) {
        this.educationGradeCatalog = educationGradeCatalog;
    }

    public Integer getEducationGradeId() {
        return educationGradeId;
    }

    public void setEducationGradeId(Integer educationGradeId) {
        this.educationGradeId = educationGradeId;
    }
    
    public String getEducationGrade() {
    	return educationGrade;
    }
    
    public void setEducationGrade(String educationGrade) {
    	this.educationGrade = educationGrade;
    }

    private List<Integer> educationCareerIds = new ArrayList<Integer>(); 
    private List<String> educationCareers = new ArrayList<String>();
    private List<String> educationCareersTxt = new ArrayList<String>();

    public String getEducationCareerIndexedPrefix() {
        return "educationCareers";
    }

    public List<Integer> getEducationCareerIds() {
        return educationCareerIds;
    }
    
    public void setEducationCareerIds(List<Integer> educationCareerIds) {
        this.educationCareerIds = educationCareerIds;
    }
    
    public List<String> getEducationCareers() {
    	return educationCareers;
    }
    
    public void setEducationCareers(List<String> educationCareers) {
    	this.educationCareers = educationCareers;
    }

    /**
     * Getter method for educationCareers indexed property
     * @param index - the index of the collection
     * @return - the index value within collection
     */
    public String getEducationCareers(int index) {
        return educationCareers.get(index);
    }

    public void setEducationCareers(int index, String value) {
        educationCareers.add(value);
    }

    // <+++++ EMPLOYMENT +++++>

    private List<CatalogoOpcionVO> employmentCatalog;
    private Integer employmentTypeId = 0;
    private String employmentType = ""; // The employmentType id:value feed from view
    private String employmentTypeTxt = "";

    public List<CatalogoOpcionVO> getEmploymentCatalog() {
        return employmentCatalog;
    }

    public void setEmploymentCatalog(List<CatalogoOpcionVO> employmentCatalog) {
        this.employmentCatalog = employmentCatalog;
    }

    public Integer getEmploymentTypeId() {
        return employmentTypeId;
    }

    public void setEmploymentTypeId(Integer employmentTypeId) {
        this.employmentTypeId = employmentTypeId;
    }
    
    public String getEmploymentType() {
    	return employmentType;
    }
    
    public void setEmploymentType(String employmentType) {
    	this.employmentType = employmentType;
    }

    private List<CatalogoOpcionVO> contractCatalog;
    private Integer contractTypeId = 0;
    private String contractType = ""; // The contractType id:value feed from view
    private String contractTypeTxt = "";

    public List<CatalogoOpcionVO> getContractCatalog() {
        return contractCatalog;
    }

    public void setContractCatalog(List<CatalogoOpcionVO> contractCatalog) {
        this.contractCatalog = contractCatalog;
    }

    public Integer getContractTypeId() {
        return contractTypeId;
    }

    public void setContractTypeId(Integer contractTypeId) {
        this.contractTypeId = contractTypeId;
    }
    
    public String getContractType() {
    	return contractType;
    }
    
    public void setContractType(String contractType) {
    	this.contractType = contractType;
    }

    // <<+++++ ENTERPRISE +++++>

    private List<CatalogoOpcionVO> enterpriseActivityCatalog;
    private List<Integer> enterpriseActivityIds = new ArrayList<Integer>();
    private List<String> enterpriseActivities = new ArrayList<String>(); // The indexed enterpriseActivity id:value feed from view
    private List<String> enterpriseActivitiesTxt = new ArrayList<String>();

    public List<CatalogoOpcionVO> getEnterpriseActivityCatalog() {
        return enterpriseActivityCatalog;
    }

    public void setEnterpriseActivityCatalog(List<CatalogoOpcionVO> enterpriseActivityCatalog) {
        this.enterpriseActivityCatalog = enterpriseActivityCatalog;
    }

    public String getEnterpriseActivityIndexedPrefix() {
        return "enterpriseActivities";
    }

    public List<Integer> getEnterpriseActivityIds() {
        return enterpriseActivityIds;
    }
    
    public void setEnterpriseActivityIds(List<Integer> enterpriseActivityIds) {
    	this.enterpriseActivityIds = enterpriseActivityIds;
    }
    
    public List<String> getEnterpriseActivities() {
    	return enterpriseActivities;
    }
    
    public void setEnterpriseActivities(List<String> enterpriseActivities) {
    	this.enterpriseActivities = enterpriseActivities;
    }

    /**
     * Getter method for enterpriseActivities indexed property
     * @param index - the index of the collection
     * @return - the index value within collection
     */
    public String getEnterpriseActivities(int index) {
        return enterpriseActivities.get(index);
    }

    public void setEnterpriseActivities(int index, String value) {
        enterpriseActivities.add(value);
    }
    
    // <+++++ RESULT LIST (ResultadoBusquedaOfertasVO) +++++>
    
    private List<ResultadoBusquedaOfertasVO> jobOffers;
    
    public List<ResultadoBusquedaOfertasVO> getJobOffers() {
		return jobOffers;
	}

	public void setJobOffers(List<ResultadoBusquedaOfertasVO> jobOffers) {
		this.jobOffers = jobOffers;
	}
	
	private Integer jobOffersSize;
	
	public Integer getJobOffersSize() {
		return jobOffersSize;
	}

	public void setJobOffersSize(Integer jobOffersSize) {
		if (jobOffersSize == null) {
			this.jobOffersSize = 0;
		} else {
			this.jobOffersSize = jobOffersSize;
		}
	}
	
	private String orderType;	// For ordering...
	private int columnNumber;	// For ordering...
	
	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public int getColumnNumber() {
		return columnNumber;
	}

	public void setColumnNumber(int columnNumber) {
		this.columnNumber = columnNumber;
	}
	
	// <<+++++ ENTERPRISE +++++>
	
	private boolean invokedFromObservatorioLaboral = false;
	private String description = null; // For knowing what filter was used
	
	public void setInvokedFromObservatorioLaboral(boolean invokedFromObservatorioLaboral) {
		this.invokedFromObservatorioLaboral = invokedFromObservatorioLaboral;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	// <<+++++ SELECTED FILTERS +++++>
	private String selectedFilters;
	
	public String getSelectedFilters() {
		return selectedFilters;
	}
	
	public void setSelectedFilters(String selectedFilters) {
		this.selectedFilters = selectedFilters;
	}
	
	public String concatenateSelectedFilters() {
    	
    	// When BusquedaEspecificaOfertas is invoked from ObservatorioLaboral web page, this method must return description field
    	if (invokedFromObservatorioLaboral) {
    		return description;
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	//area
    	if (!areaTxt.equals("") && !areaTxt.equals("")) {
    		sb.append(areaTxt).append(". ");
    	}
    	if (!subareaTxt.equals("") && !subareaTxt.equals("")) {
    		sb.append(subareaTxt).append(". ");
    	}
    	
    	// Jobs
    	for (String job : jobsTxt) {
    		sb.append(job).append(",");
    	}
    	if (jobsTxt.size() != 0) {
    		sb.setLength(sb.length() - 1); // remove last character (comma)
    		sb.append(". ");
    	}
    	
    	// Subprogram
    	if (!subprogramTxt.equals("") && !subprogramTxt.equals("0")) {
    		sb.append(subprogramTxt).append(". ");
    	}
    	
    	// LocationEntity
    	if (!locationEntityTxt.equals("") && !locationEntityTxt.equals("0")) {
    		sb.append(locationEntityTxt).append(". ");
    	}
    	
    	// LocationMunicipalities
    	for (String locationMunicipality : locationMunicipalitiesTxt) {
    		sb.append(locationMunicipality).append(", ");
    	}
    	if (locationMunicipalitiesTxt.size() != 0) {
    		sb.setLength(sb.length() - 2); // remove last two characters (comma and white_space)
    		sb.append(". ");
    	}
    	
    	// LocationRegion
    	if (!locationRegionTxt.equals("") && !locationRegionTxt.equals("0")) {
    		sb.append(locationRegionTxt).append(". ");
    	}
    	
    	// Salary
    	if (minSalary != null && minSalary != 0 && maxSalary != null && maxSalary != 0) {
    		Locale mexican = new Locale("es","MX");
    		NumberFormat formatter = NumberFormat.getCurrencyInstance(mexican);    		
    		sb.append(String.format("Sueldo [%s, %s]. ", formatter.format(minSalary), formatter.format(maxSalary)));
    	}
    	
    	// Age
    	if (age != null && age != 0) {
    		sb.append(String.format("Edad %d año", age));
    		if (age > 1) {
    			sb.append("s");
    		}
    		sb.append(". ");
    	}
    	
    	//Codigo Postal
    	if (codigoPostal != 0) {
    		if (codigoPostal.toString().length()<=4){
    			sb.append(String.format("Codigo Postal 0%d", codigoPostal));
    		}
    		else{
    		sb.append(String.format("Codigo Postal %d", codigoPostal));
    	}
    	}
    	// EducationGrade
    	if (!educationGradeTxt.equals("") && !educationGradeTxt.equals("0")) {
    		sb.append(educationGradeTxt).append(". ");
    	}
    	
    	// EducationCareers
    	for (String educationCareer : educationCareersTxt) {
    		sb.append(educationCareer).append(", ");
    	}
    	if (educationCareersTxt.size() != 0) {
    		sb.setLength(sb.length() - 2); // remove last two characters (comma and white_space)
    		sb.append(". ");
    	}
    	
    	// EmploymentType
    	if (!employmentTypeTxt.equals("") && !employmentTypeTxt.equals("0")) { // Not empty
    		sb.append(employmentTypeTxt).append(". ");
    	}
    	
    	// ContractType
    	if (!contractTypeTxt.equals("") && !contractTypeTxt.equals("0")) { // Not empty
    		sb.append(contractTypeTxt).append(". ");
    	}
    	
    	// EnterpriseActivities
    	for (String enterpriseActivity : enterpriseActivitiesTxt) {
    		sb.append(enterpriseActivity).append(", ");
    	}
    	if (enterpriseActivitiesTxt.size() != 0) {
    		sb.setLength(sb.length() - 2); // remove last two characters (comma and white_space)
    		sb.append(". ");
    	}
    	
    	return Utils.limpiarAcentos(sb.toString());
    }
	
	
    
    // ----- RESET ActionForm -----

	public void reset() {
		candidateLogged = false;
        jobIds = new ArrayList<Integer>();
        jobs = new ArrayList<String>();
        jobsTxt = new ArrayList<String>();
        this.subprogram = "";
        this.subprogramId = 0;
        this.subprogramTxt = "";
        locationEntityId = 0;
        locationEntity = "";
        locationEntityTxt = "";
        locationMunicipalityIds = new ArrayList<Integer>();
        locationMunicipalities = new ArrayList<String>();
        locationMunicipalitiesTxt = new ArrayList<String>();
        locationRegionId = 0;
        locationRegion = "";
        locationRegionTxt = "";
        minSalary = null;
        maxSalary = null;
        age = null;
        educationGradeId = 0;
        educationGrade = "";
        educationGradeTxt = "";
        educationCareerIds = new ArrayList<Integer>();
        educationCareers = new ArrayList<String>();
        educationCareersTxt = new ArrayList<String>();
        employmentTypeId = 0;
        employmentType = "";
        employmentTypeTxt = "";
        contractTypeId = 0;
        contractType = "";
        contractTypeTxt = "";
        enterpriseActivityIds = new ArrayList<Integer>();
        enterpriseActivities = new ArrayList<String>();
        enterpriseActivitiesTxt = new ArrayList<String>();
        codigoPostal=null;
        selectedFilters = "";
        
        invokedFromObservatorioLaboral = false;
        description = null;
        
        // THE COLLECTION RESULT
        jobOffers = new ArrayList<ResultadoBusquedaOfertasVO>();
        jobOffersSize = 0;
    }
    
    public boolean hasSelectedFilters() {
		
		int selectedFilters = 0;
		
		// It's supposed to be all valid id's greater than zero
		System.out.println("hasSelectedFilters: " + this.idArea);
		System.out.println("hasSelectedFilters: " + this.idSubArea);
		selectedFilters += idArea != null ? 1 : 0;
;		//selectedFilters += jobIds.size();
		selectedFilters += subprogramId;
		selectedFilters += locationEntityId;
		selectedFilters += locationMunicipalityIds.size();
		selectedFilters += locationRegionId;
		selectedFilters += minSalary != null ? minSalary : 0;
		selectedFilters += maxSalary != null ? maxSalary : 0;
		selectedFilters += age != null ? age : 0;
		selectedFilters += educationGradeId;
		selectedFilters += educationCareerIds.size();
		selectedFilters += employmentTypeId;
		selectedFilters += contractTypeId;
		selectedFilters += enterpriseActivityIds.size();
		selectedFilters += codigoPostal!= null ? codigoPostal : 0;	
		return selectedFilters > 0;	
	}
    
public boolean hasSelectedFiltersGeolocalizacion(){
		
		int selectedFilters = 0;
		
		// It's supposed to be all valid id's greater than zero
		selectedFilters += locationEntityId;
		selectedFilters += locationMunicipalityIds.size();
		selectedFilters += locationRegionId;
		selectedFilters += codigoPostal!= null ? codigoPostal : 0;	
		return selectedFilters > 0;	
	}


    public void assignTxtValues() {
    	
    	if (invokedFromObservatorioLaboral) {
    		return; // Do Nothing...
    	}
    	// Area
    	if (null != this.idArea) {
    		String[] areaComposite = area.split(":");
    		areaId = Utils.parseInt(areaComposite[0]);
    		areaTxt = areaComposite[1];
    	}
    	if (null != this.idSubArea) {
    		String[] subareaComposite = subarea.split(":");
    		subareaId = Utils.parseInt(subareaComposite[0]);
    		subareaTxt = subareaComposite[1];
    	}
    	// Jobs
    	jobsTxt = new ArrayList<String>();
        for (String job : jobs) {
        	String[] jobArray = job.split(":");
        	jobIds.add(Utils.parseInt(jobArray[0]));
        	jobsTxt.add(jobArray[1]);
        }
        
        // Subprogram
        if (this.subprogram != null && !this.subprogram.equals("") && !this.subprogram.equals("0")) {
        	String[] subprogramComposite = subprogram.split(":");
        	subprogramId = Utils.parseInt(subprogramComposite[0]);
        	subprogramTxt = subprogramComposite[1];
        }
        
        // LocationEntity
        if (locationEntity != null && !locationEntity.equals("") && !locationEntity.equals("0")) {
        	String[] locationEntityArray = locationEntity.split(":");
        	locationEntityId = Utils.parseInt(locationEntityArray[0]);
            locationEntityTxt = locationEntityArray[1];
        }
        
        // LocationMunicipalities
        locationMunicipalityIds = new ArrayList<Integer>();
        locationMunicipalitiesTxt = new ArrayList<String>();
        for (String locationMunicipality : locationMunicipalities) {
        	String[] locationMunicipalityArray = locationMunicipality.split(":");
        	locationMunicipalityIds.add(Utils.parseInt(locationMunicipalityArray[0]));
        	locationMunicipalitiesTxt.add(locationMunicipalityArray[1]);
        } 
        
        // LocationRegion
        if (locationRegion != null && !locationRegion.equals("") && !locationRegion.equals("0")) {
        	String[] locatonRegionArray = locationRegion.split(":");
            locationRegionId = Utils.parseInt(locatonRegionArray[0]);
            locationRegionTxt = locatonRegionArray[1];
        }
        
        //minSalary = null; // Do NOT change
        //maxSalary = null; // Do NOT change
        
        //age = null; // Do NOT change
        
        // EducationGrade
        if (educationGrade != null && !educationGrade.equals("") && !educationGrade.equals("0")) {
        	String[] educationGradeArray = educationGrade.split(":");
            educationGradeId = Utils.parseInt(educationGradeArray[0]);
            educationGradeTxt = educationGradeArray[1];
        }
        
        // EducationCareers
        educationCareersTxt = new ArrayList<String>();
        for (String educationCareer : educationCareers) {
        	String[] educationCareerArray = educationCareer.split(":");
        	educationCareerIds.add(Utils.parseInt(educationCareerArray[0]));
        	educationCareersTxt.add(educationCareerArray[1]);
        } 
        
        // EmploymentType
        if (employmentType != null && !employmentType.equals("") && !employmentType.equals("0")){
        	String[] employmentTypeArray = employmentType.split(":");
            employmentTypeId = Utils.parseInt(employmentTypeArray[0]);
            employmentTypeTxt = employmentTypeArray[1];
        }
        
        // ContractType
        if (contractType != null && !contractType.equals("") && !contractType.equals("0")) {
        	String[] contractTypeArray = contractType.split(":");
            contractTypeId = Utils.parseInt(contractTypeArray[0]);
            contractTypeTxt = contractTypeArray[1];
        }
        
        // EnterpriseActivities
        enterpriseActivitiesTxt = new ArrayList<String>();
        for (String enterpriseActivity : enterpriseActivities) {
        	String[] enterpriseActivityArray = enterpriseActivity.split(":");
        	enterpriseActivityIds.add(Utils.parseInt(enterpriseActivityArray[0]));
        	enterpriseActivitiesTxt.add(enterpriseActivityArray[1]);
        }
    }
    
    public BusquedaOfertasVO buildBusquedaOfertasVO() {
    	BusquedaOfertasVO vo = new BusquedaOfertasVO();
    	vo.setAge(age);
    	vo.setJobIds(jobIds);
    	vo.setSubprogram(subprogramId);
    	vo.setLocationEntityId(locationEntityId);
    	vo.setLocationMunicipalityIds(locationMunicipalityIds);
    	vo.setLocationRegionId(locationRegionId);
    	vo.setMinSalary(minSalary);
    	vo.setMaxSalary(maxSalary);
    	vo.setEducationGradeId(educationGradeId);
    	vo.setEducationCareerIds(educationCareerIds);
    	vo.setEmploymentTypeId(employmentTypeId);
    	vo.setContractTypeId(contractTypeId);
    	vo.setEnterpriseActivityIds(enterpriseActivityIds);
    	vo.setCalle(calle);
    	vo.setNumeroExterior(numeroExterior);
    	vo.setIdPropietario(idPropietario);
    	vo.setCodigoPostal(codigoPostal);
    	vo.setIdArea(idArea);
    	vo.setIdSubArea(idSubArea);
    	return vo;
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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAreaTxt() {
		return areaTxt;
	}

	public void setAreaTxt(String areaTxt) {
		this.areaTxt = areaTxt;
	}

	public String getSubarea() {
		return subarea;
	}

	public void setSubarea(String subarea) {
		this.subarea = subarea;
	}

	public Integer getSubareaId() {
		return subareaId;
	}

	public void setSubareaId(Integer subareaId) {
		this.subareaId = subareaId;
	}

	public String getSubareaTxt() {
		return subareaTxt;
	}

	public void setSubareaTxt(String subareaTxt) {
		this.subareaTxt = subareaTxt;
	}
}
