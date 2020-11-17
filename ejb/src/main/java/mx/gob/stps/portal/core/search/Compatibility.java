package mx.gob.stps.portal.core.search;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mx.gob.stps.portal.core.candidate.vo.ConocimientoHabilidadVO;
import mx.gob.stps.portal.core.candidate.vo.ExpectativaLaboralVO;
import mx.gob.stps.portal.core.candidate.vo.GradoAcademicoVO;
import mx.gob.stps.portal.core.candidate.vo.IdiomaVO;
import mx.gob.stps.portal.core.infra.utils.Constantes.CAMBIO_RESIDENCIA;
import mx.gob.stps.portal.core.infra.utils.Constantes.DISPONIBILIDAD_VIAJAR;
import mx.gob.stps.portal.core.oferta.detalle.dao.CandidatoDetalleDAO;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaIdiomaVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaRequisitoVO;

public class Compatibility {
	
	private static int WEIGHT_KNOWLEDGE = PropertiesLoader.getInstance().getPropertyInt("WEIGHT_KNOWLEDGE");
	private static int WEIGHT_EDUCATION = PropertiesLoader.getInstance().getPropertyInt("WEIGHT_EDUCATION");;
	private static int WEIGHT_EXPERIENCE = PropertiesLoader.getInstance().getPropertyInt("WEIGHT_EXPERIENCE");;
	private static int WEIGHT_LANGUAGE = PropertiesLoader.getInstance().getPropertyInt("WEIGHT_LANGUAGE");;
	private static int WEIGHT_SALARY = PropertiesLoader.getInstance().getPropertyInt("WEIGHT_SALARY");;
	private static int WEIGHT_AGE = PropertiesLoader.getInstance().getPropertyInt("WEIGHT_AGE");;
	private static int WEIGHT_SCHEDULE = PropertiesLoader.getInstance().getPropertyInt("WEIGHT_SCHEDULE");;
	private static int WEIGHT_TRAVEL_VIABILITY = PropertiesLoader.getInstance().getPropertyInt("WEIGHT_TRAVEL_VIABILITY");;
	private static int WEIGHT_RESIDE_OUTSIDE = PropertiesLoader.getInstance().getPropertyInt("WEIGHT_RESIDE_OUTSIDE");
	private static int WEIGHT_SKILLS = PropertiesLoader.getInstance().getPropertyInt("WEIGHT_SKILLS");
	
	public static int match(OfertaEmpleoVO ofertaEmpleoVO, ArrayList<OfertaRequisitoVO> ofertaRequisitosList,
			                ArrayList<String> ofertaEspecialidades, ArrayList<OfertaIdiomaVO> ofertaidiomasList,
			                ArrayList<GradoAcademicoVO> perfilGradosAcademicosList, ExpectativaLaboralVO expectativaLaboral, 
			                Date fechaNacimientoPerfil, ArrayList<ConocimientoHabilidadVO> conocimientoHabilidadList, 
			                ArrayList<IdiomaVO> perfilidiomasList, long idOcupacion, long idExperienciaTotal, int idDisponibilidadViajar, 
			                int idDisponibilidadRadicar, 
			                List<Long> idHabilidadesOferta,
			                List<Long> idHabilidadesCandidato,
			                Connection globalConnection) {
		int compatibility = 0;
		HashMap<String, Object> offer = initOffer(ofertaEmpleoVO, ofertaRequisitosList, ofertaEspecialidades, ofertaidiomasList);
		
		HashMap<String, Object> userProfile = initUserProfile(perfilGradosAcademicosList, expectativaLaboral, fechaNacimientoPerfil, 
				                                              conocimientoHabilidadList, perfilidiomasList, idOcupacion, idExperienciaTotal, 
				                                              idDisponibilidadViajar, idDisponibilidadRadicar, globalConnection);
		
		compatibility += (int)weightKnowledge(offer, userProfile);
		compatibility += weightEducation(offer, userProfile);
		compatibility += weightExperience(offer, userProfile);
		compatibility += (int)weightLanguage(offer, userProfile);
		compatibility += weightSalary(offer, userProfile);
		compatibility += weightAge(offer, userProfile);
		compatibility += weightSchedule(offer, userProfile);
		compatibility += weightTravelViability(offer, userProfile);
		compatibility += weightResideOutside(offer, userProfile);

		compatibility += weightSkills(idHabilidadesOferta, idHabilidadesCandidato);

		if (compatibility>100) compatibility = 100; // No sobrepasa el 100%
		
		return compatibility;
	}
	
	private static int weightSkills(List<Long> offerIdSkills, List<Long> candidateIdSkills){
		if (WEIGHT_SKILLS<=0) WEIGHT_SKILLS = 11; // XXX VALOR POR DEFECTO
		
		if (offerIdSkills==null || offerIdSkills.isEmpty()) return WEIGHT_SKILLS;
		if (candidateIdSkills==null || candidateIdSkills.isEmpty()) return 0;
		
		int weightItem = 100 / offerIdSkills.size();
		
		int maches = 0;
		
		for (Long idOfferSkill : offerIdSkills){
			for (Long idCandSkill : candidateIdSkills){
				if (idOfferSkill.equals(idCandSkill)){
					maches++;
					break;
				}
			}
		}

		int weight = maches * weightItem;

		double totalWeight = ((double)WEIGHT_SKILLS / (double)100) * weight;

		return (int)Math.round(totalWeight);
	}

	private static HashMap<String, Object> initUserProfile(ArrayList<GradoAcademicoVO> perfilGradosAcademicosList, ExpectativaLaboralVO expectativaLaboral, Date fechaNacimientoPerfil, ArrayList<ConocimientoHabilidadVO> conocimientoHabilidadList, ArrayList<IdiomaVO> perfilidiomasList, long idOcupacion, long idExperienciaTotal, int idDisponibilidadViajar, int idDisponibilidadRadicar, Connection globalConnection) {
		HashMap<String, Object> profile = new HashMap<String, Object>();
		ArrayList<String> userEducationList = new ArrayList<String>();
		profile.put("occupation", String.valueOf(idOcupacion));
		//profile.put("knowledges", getUserKnowledge(conocimientoHabilidadList));
		
		if(perfilGradosAcademicosList!=null){
			Iterator<GradoAcademicoVO> it = perfilGradosAcademicosList.iterator();
			while (it.hasNext()) {
				GradoAcademicoVO vo = it.next();
				userEducationList.add(getShortID(vo.getIdCarreraEspecialidad(), globalConnection));
				if (vo.getPrincipal() == Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion()) {
					profile.put("degree", String.valueOf(vo.getIdNivelEstudio()));
					profile.put("academic", String.valueOf(vo.getIdSituacionAcademica()));
				}
			}			
		}
		
		profile.put("educations", userEducationList);
		profile.put("experience", String.valueOf(idExperienciaTotal));
		profile.put("languages", getUserLanguages(perfilidiomasList));
		if (null != expectativaLaboral) {
			profile.put("salary", String.valueOf(expectativaLaboral.getSalarioPretendido()));
			profile.put("schedule", String.valueOf(expectativaLaboral.getIdTipoEmpleoDeseado()));
		}
		Calendar calendar = Calendar.getInstance();
		int yearCurrent = calendar.get(Calendar.YEAR);
		int monthCurrent = calendar.get(Calendar.MONTH);
		if (null != fechaNacimientoPerfil) calendar.setTime(fechaNacimientoPerfil);
		int yearBirth = calendar.get(Calendar.YEAR);
		int age = yearCurrent - yearBirth;
		int monthBirth = calendar.get(Calendar.MONTH);
		if (monthCurrent - monthBirth < 0)
			age -=1;
		profile.put("age", String.valueOf(age));
		profile.put("travel", String.valueOf(idDisponibilidadViajar));
		profile.put("reside", String.valueOf(idDisponibilidadRadicar));
		return profile;
	}
	
	private static ArrayList<HashMap<String, String>> getUserLanguages(ArrayList<IdiomaVO> perfilidiomasList) {
		ArrayList<HashMap<String, String>> userLanguageList = new ArrayList<HashMap<String, String>>();
		Iterator<IdiomaVO> it = perfilidiomasList.iterator();
		while (it.hasNext()) {
			IdiomaVO vo = it.next();
			HashMap<String, String> language = new HashMap<String, String>();
			language.put("language", String.valueOf(vo.getIdIdioma()));
			language.put("domain", String.valueOf(vo.getIdDominio()));
			language.put("certification", String.valueOf(vo.getIdCertificacion()));
			userLanguageList.add(language);
		}
		return userLanguageList;
	}
	
	/**private static ArrayList<HashMap<String, String>> getUserKnowledge(ArrayList<ConocimientoHabilidadVO> conocimientoHabilidadList) {
		ArrayList<HashMap<String, String>> userKnowledge = new ArrayList<HashMap<String, String>>();
		Iterator<ConocimientoHabilidadVO> it = conocimientoHabilidadList.iterator();
		while (it.hasNext()) {
			ConocimientoHabilidadVO vo = it.next();
			HashMap<String, String> knowledge = new HashMap<String, String>();
			knowledge.put("knowledge", vo.getConocimientoHabilidad());
			knowledge.put("experience", String.valueOf(vo.getIdExperiencia()));
			userKnowledge.add(knowledge);
		}
		return userKnowledge;
	}**/
	
	private static HashMap<String, Object> initOffer(OfertaEmpleoVO offerVO, ArrayList<OfertaRequisitoVO> requisitosList, ArrayList<String> ofertaEspecialidades, ArrayList<OfertaIdiomaVO> ofertaidiomasList) {
		HashMap<String, Object> offer = new HashMap<String, Object>();
		offer.put("occupation", String.valueOf(offerVO.getIdOcupacion()));
		//offer.put("knowledges", initOfferKnowledge(requisitosList));
		offer.put("educations", ofertaEspecialidades);
		offer.put("degree", String.valueOf(offerVO.getIdNivelEstudio()));
		offer.put("academic", String.valueOf(offerVO.getIdSituacionAcademica()));
		offer.put("experience", String.valueOf(offerVO.getExperienciaAnios()));
		offer.put("languages", initOfferLanguages(ofertaidiomasList));
		offer.put("ageRequired",  String.valueOf(offerVO.getEdadRequisito()));
		offer.put("salary", String.valueOf(offerVO.getSalario()));
		offer.put("ageInit", String.valueOf(offerVO.getEdadMinima()));
		offer.put("ageFinal", String.valueOf(offerVO.getEdadMaxima()));
		offer.put("schedule", String.valueOf(offerVO.getIdTipoEmpleo()));
		offer.put("travel", String.valueOf(offerVO.getDisponibilidadViajar()));
		offer.put("reside", String.valueOf(offerVO.getDisponibilidadRadicar()));
		return offer;
	}
	
	private static ArrayList<HashMap<String, String>> initOfferLanguages(ArrayList<OfertaIdiomaVO> ofertaidiomasList) {
		ArrayList<HashMap<String, String>> offerLanguageList = new ArrayList<HashMap<String, String>>();
		Iterator<OfertaIdiomaVO> it = ofertaidiomasList.iterator();
		while (it.hasNext()) {
			HashMap<String, String> language = new HashMap<String, String>();
			OfertaIdiomaVO idioma = it.next();
			language.put("language", String.valueOf(idioma.getIdIdioma()));
			language.put("domain", String.valueOf(idioma.getIdDominio()));
			language.put("certification", String.valueOf(idioma.getIdCertificacion()));
			offerLanguageList.add(language);
		}
		return offerLanguageList;
	}
	
	/**private static ArrayList<HashMap<String, String>> initOfferKnowledge(ArrayList<OfertaRequisitoVO> requisitosList) {
		ArrayList<HashMap<String, String>> offerKnowledgeList = new ArrayList<HashMap<String, String>>();
		Iterator<OfertaRequisitoVO> it = requisitosList.iterator();
		while (it.hasNext()) {
			HashMap<String, String> offerKnowledge = new HashMap<String, String>();
			OfertaRequisitoVO requisito = it.next();
			offerKnowledge.put("knowledge", requisito.getDescripcion());
			offerKnowledge.put("experience", String.valueOf(requisito.getIdExperiencia()));
			offerKnowledgeList.add(offerKnowledge);
		}
		return offerKnowledgeList;
	}**/
	
	private static float weightKnowledge(HashMap<String, Object> offer, HashMap<String, Object> userProfile) {
		float weight = 0;
		/**ArrayList<HashMap<String, String>> offerKnowledgeList = (ArrayList<HashMap<String, String>>)offer.get("knowledges");
		ArrayList<HashMap<String, String>> userKnowledgeList = (ArrayList<HashMap<String, String>>)userProfile.get("knowledges");
		int factor = offerKnowledgeList.size();
		if (factor > 0) {
			if (userKnowledgeList.size() > 0) {
				for (int i=0; i<factor; i++) {
					HashMap<String, String> offerKnowledge = offerKnowledgeList.get(i);
					weight += weightKnowledge(offerKnowledge.get("knowledge"), offerKnowledge.get("experience"), userKnowledgeList)/factor;
				}
			}
		}else {**/
			String occupationUser = "";
			String occupationOffer = "";
			String occupationOfferSpec = ((String)offer.get("occupation"));
			String occupationUserSpec = (String)userProfile.get("occupation");
			if (occupationOfferSpec.length() > 3) {
				occupationOffer = occupationOfferSpec.substring(0, 4);
				if (occupationUserSpec.length() > 3)
					occupationUser = occupationUserSpec.substring(0, 4);
			}
			if (occupationOffer.equalsIgnoreCase(occupationUser))
				weight = WEIGHT_KNOWLEDGE;
		//}
		return weight;
	}
	
	@SuppressWarnings("unchecked")
	private static int weightEducation(HashMap<String, Object> offer, HashMap<String, Object> userProfile) {
		String offerDegree = (String)offer.get("degree");
		String userDegree = (String)userProfile.get("degree");
		String offerAcademic = (String)offer.get("academic");
		String userAcademic = (String)userProfile.get("academic");
		ArrayList<String> offerEducationList = (ArrayList<String>)offer.get("educations");
		ArrayList<String> userEducationList = (ArrayList<String>)userProfile.get("educations");
		
		Iterator<String> it = offerEducationList.iterator();
		while (it.hasNext()) {
			String education = it.next();
			if (userEducationList.contains(education))
				return WEIGHT_EDUCATION;
		}			

		int uDegree = parseInt(userDegree);
		int oDegree = parseInt(offerDegree);
		if (uDegree > oDegree)
			return WEIGHT_EDUCATION;
		else if (uDegree == oDegree) {
			int uAcademic = parseInt(userAcademic);
			int oAcademic = parseInt(offerAcademic);
			if (uAcademic >= oAcademic)
				return WEIGHT_EDUCATION;
			else
				return WEIGHT_EDUCATION/2;
		}
		return 0;
	}
	
	private static int weightExperience(HashMap<String, Object> offer, HashMap<String, Object> userProfile) {
		int weight = 0;
		String offerExperience = (String)offer.get("experience");
		String userExperience = (String)userProfile.get("experience");
		int uExperience = parseInt(userExperience);
		int oExperience = parseInt(offerExperience);
		if (uExperience >= oExperience)
			weight = WEIGHT_EXPERIENCE;
		return weight;
	}
	
	@SuppressWarnings("unchecked")
	private static float weightLanguage(HashMap<String, Object> offer, HashMap<String, Object> userProfile) {
		float weight = 0;
		ArrayList<HashMap<String, String>> offerLanguagesList = (ArrayList<HashMap<String, String>>)offer.get("languages");
		ArrayList<HashMap<String, String>> userLanguagesList = (ArrayList<HashMap<String, String>>)userProfile.get("languages");
		if (!offerLanguagesList.isEmpty()) {
			int factor = offerLanguagesList.size();
			for (int i=0; i<factor; i++) {
				HashMap<String, String> offerLanguage = offerLanguagesList.get(i);
				if (offerLanguage.get("language").equals("1"))
					return WEIGHT_LANGUAGE;
				weight += weightLanguage(offerLanguage.get("language"), offerLanguage.get("domain"), userLanguagesList)/factor;
			}
		}else
			weight = WEIGHT_LANGUAGE;
		return weight;
	}
	
	private static int weightSalary(HashMap<String, Object> offer, HashMap<String, Object> userProfile) {
		int weight = 0;
		String offerSalary = (String)offer.get("salary");
		String userSalary = (String)userProfile.get("salary");
		double uSalary = parseDouble(userSalary);
		double oSalary = parseDouble(offerSalary);
		if (uSalary <= oSalary)
			weight = WEIGHT_SALARY;
		return weight;
	}
	
	private static int weightAge(HashMap<String, Object> offer, HashMap<String, Object> userProfile) {
		int weight = WEIGHT_AGE;
		String ageRequired = (String)offer.get("ageRequired");
		if (null != ageRequired && "2".equals(ageRequired) && null != offer.get("ageInit") && null != offer.get("ageFinal")) {
			String userAge = (String)userProfile.get("age");
			String offerAgeInit = (String)offer.get("ageInit");
			String offerAgeFinal = (String)offer.get("ageFinal");
			int diff = diff(parseInt(userAge), parseInt(offerAgeInit), parseInt(offerAgeFinal));
			if (diff > 4)
				weight = 0;
			else if (diff == 4)
				weight = WEIGHT_AGE/5;
			else if (diff == 3)
				weight = (2*WEIGHT_AGE)/5;
			else if (diff == 2)
				weight = (3*WEIGHT_AGE)/5;
			else if (diff == 1)
				weight = WEIGHT_AGE - 2;
		}else
			weight = WEIGHT_AGE;
		return weight;
	}
	
	private static int weightSchedule(HashMap<String, Object> offer, HashMap<String, Object> userProfile) {
		int weight = WEIGHT_SCHEDULE;
		String offerSchedule = (String)offer.get("schedule");
		String userSchedule = (String)userProfile.get("schedule");
		int uSchedule = parseInt(userSchedule);
		int oSchedule= parseInt(offerSchedule);
		//if (oSchedule == 0 &&  uSchedule == 1 || oSchedule == 1 && uSchedule == 0)
		if (oSchedule != uSchedule)
			weight = 0;
		return weight;
	}
	
	private static int weightTravelViability(HashMap<String, Object> offer, HashMap<String, Object> userProfile) {
		int weight = WEIGHT_TRAVEL_VIABILITY;
		String travelViability = (String)offer.get("travel");
		long oTravelViability = parseLong(travelViability);
		if (DISPONIBILIDAD_VIAJAR.SI.getIdOpcion() == oTravelViability && !offer.get("travel").equals(userProfile.get("travel")))
			weight = 0;
		return weight;
	}
	
	private static int weightResideOutside(HashMap<String, Object> offer, HashMap<String, Object> userProfile) {
		int weight = WEIGHT_RESIDE_OUTSIDE;
		String resideOutside = (String)offer.get("reside");
		long oResideOutside = parseLong(resideOutside);
		if (CAMBIO_RESIDENCIA.SI.getIdOpcion() == oResideOutside && !offer.get("reside").equals(userProfile.get("reside")))
			weight = 0;
		return weight;
	}
	
	/**private static ArrayList<HashMap<String, String>> getOfferKnowledge(int offerID) {
		ArrayList<HashMap<String, String>> offerKnowledgeList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> offerKnowledge = new HashMap<String, String>();
		offerKnowledge.put("knowledge", "J2EE");
		offerKnowledge.put("experience", "4");
		offerKnowledgeList.add(offerKnowledge);
		return offerKnowledgeList;
	}
	
	private static ArrayList<String> getOfferEducation(int offerID) {
		ArrayList<String> offerEducationList = new ArrayList<String>();
		offerEducationList.add("01");
		return offerEducationList;
	}
	
	private static ArrayList<HashMap<String, String>> getOfferLanguages(int offerID) {
		ArrayList<HashMap<String, String>> offerLanguageList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> language = new HashMap<String, String>();
		language.put("language", "01");
		language.put("domain", "01");
		offerLanguageList.add(language);
		return offerLanguageList;
	}
	
	private static ArrayList<HashMap<String, String>> getUserKnowledge(int userID) {
		ArrayList<HashMap<String, String>> userKnowledge = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> knowledge = new HashMap<String, String>();
		knowledge.put("knowledge", "J2EE");
		knowledge.put("experience", "4");
		userKnowledge.add(knowledge);
		return userKnowledge;
	}
	
	private static ArrayList<String> getUserEducation(int userID) {
		ArrayList<String> offerUserList = new ArrayList<String>();
		offerUserList.add("01");
		return offerUserList;
	}
	
	private static ArrayList<HashMap<String, String>> getUserLanguages(int userID) {
		ArrayList<HashMap<String, String>> userLanguageList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> language = new HashMap<String, String>();
		language.put("language", "01");
		language.put("domain", "01");
		userLanguageList.add(language);
		return userLanguageList;
	}
	
	private static float weightKnowledge(String knowledge, String offerExperience, ArrayList<HashMap<String, String>> userKnowledgeList) {
		float weight = 0;
		Iterator <HashMap<String, String>> it = userKnowledgeList.iterator();
		while (it.hasNext()) {
			HashMap<String, String> userKnowledge = it.next();
			String uknowledge = userKnowledge.get("knowledge");
			if (VacanteSearch.checkCompatibility(knowledge, uknowledge)) {
				int uExperience = Integer.valueOf(userKnowledge.get("experience"));
				int oExperience = Integer.valueOf(offerExperience);
				if (uExperience >= oExperience) weight = WEIGHT_KNOWLEDGE;
				else weight = WEIGHT_KNOWLEDGE/2;
			}
		}
		return weight;
	}**/
	
	private static float weightLanguage(String offerLanguage, String offerDomain, ArrayList<HashMap<String, String>> userLanguageList) {
		float weight = 0;
		Iterator <HashMap<String, String>> it = userLanguageList.iterator();
		while (it.hasNext()) {
			HashMap<String, String> userLanguage = it.next();
			if (offerLanguage.equals(userLanguage.get("language"))) {
				int uCertification = parseInt(userLanguage.get("certification"));
				if (uCertification == 2 || uCertification < 1) {
					int uDomain = parseInt(userLanguage.get("domain"));
					int oDomain = parseInt(offerDomain);
					if (uDomain >= oDomain) weight = WEIGHT_LANGUAGE;
					else weight = WEIGHT_LANGUAGE/2;
				}else weight = WEIGHT_LANGUAGE;
			}
		}
		return weight;
	}
	
	private static int diff(int x, int inf, int sup) {
		int diff = 0;
		if (x < inf)
			diff = inf - x;
		else if (x > sup)
			diff = x - sup;
		return diff;
	}
	
	private static int parseInt(String number){
	    int value = -1;
	    if (isInteger(number))
	    	value = Integer.parseInt(number);
	    return value;
	}
	
	private static boolean isInteger(String number) {
		if (number != null && number.length() > 0) {
			Pattern pattern = Pattern.compile("^[0-9]+$");
			Matcher matcher = pattern.matcher(number);
			return matcher.find();
		} else
			return false;
	}
	
	public static double parseDouble(String number){
	    double value = -1;
	    try {
	    	value = Double.parseDouble(number);
	    }catch (NumberFormatException nfe) {}
	    return value;
	}
	
	public static long parseLong(String numero){
	    long valor = -1;
	    if (isInteger(numero))
	        valor = Long.parseLong(numero);
	    return valor;
	}
	
	private static String getShortID(long idCarreraEspecialidad, Connection globalConnection) {
		String shortID = "";
		CandidatoDetalleDAO dao = CandidatoDetalleDAO.getInstance(globalConnection);
		try {
			shortID =  dao.getShortId(idCarreraEspecialidad);
		} catch (SQLException sql) { sql.printStackTrace();}
		return shortID;
	}
	
	/**private static String replaceChars(String source) {
		String cleanSource = "";
		if (null != source && !"".equals(source) && source.length() > 1)
			cleanSource = source.replaceAll("!", "").replaceAll(",", "").replaceAll("¡", "").replaceAll(":", "").replaceAll(";", "");
		return cleanSource;
	}
	
	private static HashMap<String, Object> getOffer(int offerID) {
		HashMap<String, Object> offer = new HashMap<String, Object>();
		offer.put("occupation", "1");
		offer.put("knowledges", getOfferKnowledge(offerID));
		offer.put("educations", getOfferEducation(offerID));
		offer.put("degree", "" + GRADO_ESTUDIOS.DOCTORADO.getIdOpcion());
		offer.put("academic", "" + SITUACION_ACADEMICA.TITULADO.getIdOpcion());
		offer.put("experience", "1");
		offer.put("languages", getOfferLanguages(offerID));
		offer.put("salary", "20000");
		offer.put("ageInit", "20");
		offer.put("ageFinal", "40");
		offer.put("schedule", "" + TIPO_EMPLEO.TIEMPO_COMPLETO.getIdOpcion());
		offer.put("travel", "" + DISPONIBILIDAD_VIAJAR.SI.getIdOpcion());
		offer.put("reside", "" + CAMBIO_RESIDENCIA.SI.getIdOpcion());
		return offer;
	}
	
	private static HashMap<String, Object> getUserProfile(int offerID) {
		HashMap<String, Object> profile = new HashMap<String, Object>();
		profile.put("occupation", "1");
		profile.put("knowledges", getUserKnowledge(offerID));
		profile.put("educations", getUserEducation(offerID));
		profile.put("degree", "" + GRADO_ESTUDIOS.DOCTORADO.getIdOpcion());
		profile.put("academic", "" + SITUACION_ACADEMICA.TITULADO.getIdOpcion());
		profile.put("experience", "1");
		profile.put("languages", getUserLanguages(offerID));
		profile.put("salary", "20000");
		profile.put("age", "25");
		profile.put("schedule", "" + TIPO_EMPLEO.TIEMPO_COMPLETO.getIdOpcion());
		profile.put("travel", "" + DISPONIBILIDAD_VIAJAR.SI.getIdOpcion());
		profile.put("reside", "" + CAMBIO_RESIDENCIA.SI.getIdOpcion());
		return profile;
	}**/
}