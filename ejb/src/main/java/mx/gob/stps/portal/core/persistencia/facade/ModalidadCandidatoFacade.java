package mx.gob.stps.portal.core.persistencia.facade;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import mx.gob.stps.portal.core.candidate.vo.IdiomaVO;
import mx.gob.stps.portal.core.candidate.vo.PerfilVO;
import mx.gob.stps.portal.core.candidate.vo.ExpectativaLaboralVO;
import mx.gob.stps.portal.core.candidate.vo.GradoAcademicoVO;
import mx.gob.stps.portal.core.candidate.vo.HistoriaLaboralVO;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.DECISION;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.persistencia.entity.Beneficiario;
import mx.gob.stps.portal.persistencia.entity.CanalizacionCandidato;
import mx.gob.stps.portal.persistencia.entity.Candidato;
import mx.gob.stps.portal.persistencia.entity.CandidatoGradoAcademico;
import mx.gob.stps.portal.persistencia.entity.CandidatoIdioma;
import mx.gob.stps.portal.persistencia.entity.Domicilio;
import mx.gob.stps.portal.persistencia.entity.ExpectativaLaboral;
import mx.gob.stps.portal.persistencia.entity.FormatoLPA;
import mx.gob.stps.portal.persistencia.entity.FormatoPTAT;
import mx.gob.stps.portal.persistencia.entity.FormatoPTATCultivos;
import mx.gob.stps.portal.persistencia.entity.FormatoPTATHclinica;
import mx.gob.stps.portal.persistencia.entity.FormatoPTATMaquinaria;
import mx.gob.stps.portal.persistencia.entity.FormatoSNE01;
import mx.gob.stps.portal.persistencia.entity.HistoriaLaboral;
import mx.gob.stps.portal.persistencia.entity.ModalidadCandidato;
import mx.gob.stps.portal.persistencia.entity.ModalidadPtatBeneficiario;
import mx.gob.stps.portal.persistencia.entity.PerfilLaboral;
import mx.gob.stps.portal.persistencia.entity.Telefono;
import mx.gob.stps.portal.persistencia.vo.BeneficiarioVO;
import mx.gob.stps.portal.persistencia.vo.CanalizacionCandidatoVO;
import mx.gob.stps.portal.persistencia.vo.DomicilioVO;
import mx.gob.stps.portal.persistencia.vo.FormatoLPAVO;
import mx.gob.stps.portal.persistencia.vo.FormatoPTATCultivosVO;
import mx.gob.stps.portal.persistencia.vo.FormatoPTATHclinicaVO;
import mx.gob.stps.portal.persistencia.vo.FormatoPTATMaquinariaVO;
import mx.gob.stps.portal.persistencia.vo.FormatoPTATVO;
import mx.gob.stps.portal.persistencia.vo.FormatoSNE01VO;
import mx.gob.stps.portal.persistencia.vo.ModalidadCandidatoVO;
import mx.gob.stps.portal.persistencia.vo.ModalidadPtatBeneficiarioVO;
import mx.gob.stps.portal.persistencia.vo.ReferenciaLaboralVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;

import org.apache.log4j.Logger;

@Stateless
public class ModalidadCandidatoFacade implements ModalidadCandidatoFacadeLocal {

	@PersistenceContext
	private EntityManager entityManager;
	
	private static Logger logger = Logger.getLogger(ModalidadCandidatoFacade.class);
	
	@EJB
	private MunicipioFacadeLocal municipioFacadeLocal;
	
	@EJB
	private TelefonoFacadeLocal telefonoFacadeLocal;

	@Override
	public int updateCandidateComplementData(ModalidadCandidatoVO mcv, FormatoPTATVO ptat, List<BeneficiarioVO> contacts, List<BeneficiarioVO> beneficiaries, List<IdiomaVO> langs, List<FormatoPTATMaquinariaVO> engines, List<FormatoPTATCultivosVO> products, List<FormatoPTATHclinicaVO> histories) throws PersistenceException {
		int result = 0;
		try {
			if (null == mcv.getIdModalidadCandidato() || mcv.getIdModalidadCandidato() < 1) {
				ModalidadCandidato mc = getEntity(mcv);
				entityManager.persist(mc);
				entityManager.flush();
			}
			if (null != ptat.getIdCandidato() && ptat.getIdCandidato() > 0) {
				FormatoPTAT entity = entityManager.find(FormatoPTAT.class, ptat.getIdCandidato());
				if (null != entity) {
					entityManager.merge(getEntity(entity, ptat));
					result = 1;
				}
			}else {
				FormatoPTAT entity = getEntity(new FormatoPTAT(), ptat);
				entity.setIdCandidato(mcv.getIdCandidato());
				entityManager.persist(entity);
				entityManager.flush();
				result = 1;
			}
			if (result > 0) {
				updatePhone(ptat.getCaseta(), mcv.getIdCandidato());
				updateBeneficiarioList(contacts);
				updateBeneficiarioList(beneficiaries);
				updateDomainLangs(langs);
				persistEngines(mcv.getIdCandidato(), engines, result);
				persistCandidatoCultivosPTAT(mcv.getIdCandidato(), products, result);
				persistClinicalHistory(mcv.getIdCandidato(), histories, result);
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw new PersistenceException(e);
		}
		return result;
	}
	
	@Override
	public int updateLPA(long idCandidato, FormatoLPAVO mml, ReferenciaLaboralVO referencia, GradoAcademicoVO gradoAcademicoVO) throws PersistenceException {
		int result = 0;
		FormatoLPA entity = null;
		Candidato candidato = entityManager.find(Candidato.class, idCandidato);
		try {
			if (candidato != null) {
				if (null != mml) {
					entity = entityManager.find(FormatoLPA.class, idCandidato);
					if (null != entity) {
						setMML(entity, mml);
						entityManager.merge(entity);
					}else {
						entity = new FormatoLPA();
						entity.setIdCandidato(idCandidato);
						setMML(entity, mml);
						entityManager.persist(entity);
						entityManager.flush();
					}
				}
				if (null != referencia) {
					if (referencia.getTrabajoActual().getIdHistorialLaboral() < 1) {
						HistoriaLaboral historial = getEntity(referencia.getTrabajoActual());
						historial.setFechaAlta(new Date());
						historial.setIdCandidato(idCandidato);
						entityManager.persist(historial);
						entityManager.flush();
						referencia.getTrabajoActual().setIdHistorialLaboral(historial.getIdHistorialLaboral());
					}else {
						HistoriaLaboral historial = entityManager.find(HistoriaLaboral.class, referencia.getTrabajoActual().getIdHistorialLaboral());
						if (null != historial)
							entityManager.merge(getEntity(historial, referencia.getTrabajoActual()));
					}
					Domicilio domicilio = entityManager.find(Domicilio.class, referencia.getDomicilio().getIdDomicilio());
					if (domicilio != null) {
						setDomicilio(domicilio, referencia.getDomicilio());
						entityManager.merge(domicilio);
					}else if (null != referencia.getTrabajoActual().getEmpresa() && !referencia.getTrabajoActual().getEmpresa().isEmpty()) {
						domicilio = new Domicilio();
						setDomicilio(domicilio, referencia.getDomicilio());
						domicilio.setFechaAlta(new Date());
						domicilio.setIdPropietario(referencia.getTrabajoActual().getIdHistorialLaboral());
						domicilio.setIdTipoPropietario((long)TIPO_PROPIETARIO.REFERENCIA_LABORAL.getIdTipoPropietario());
						entityManager.persist(domicilio);
						entityManager.flush();
						municipioFacadeLocal.setEntidadMunicipio(referencia.getDomicilio());
					}
					Telefono phone = entityManager.find(Telefono.class, referencia.getTelefono().getIdTelefono());
					if (phone != null) {
						setPhone(phone, referencia.getTelefono());
						entityManager.merge(phone);
					}else {
						phone = new Telefono();
						setPhone(phone, referencia.getTelefono());
						phone.setFechaAlta(new Date());
						phone.setIdPropietario(referencia.getTrabajoActual().getIdHistorialLaboral());
						phone.setIdTipoPropietario(TIPO_PROPIETARIO.REFERENCIA_LABORAL.getIdTipoPropietario());
						entityManager.persist(phone);
						entityManager.flush();
					}
				}
				if (null != gradoAcademicoVO) {
					CandidatoGradoAcademico gradoAcademico = entityManager.find(CandidatoGradoAcademico.class, gradoAcademicoVO.getIdCandidatoGradoAcademico());
					if (null != gradoAcademico) {
						setDegree(gradoAcademico, gradoAcademicoVO);
						entityManager.merge(gradoAcademico);
					}
				}
				result = 1;
			}
		}catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			result = -1;
		}
		return result;
	}
	
	@Override
	public int updatePerfilComp(PerfilVO perfil, ExpectativaLaboralVO el, HistoriaLaboralVO histLaboralVO, FormatoSNE01VO sne, ModalidadCandidatoVO mcv) throws PersistenceException {
		HistoriaLaboral hl = null;
		FormatoSNE01 entity = null;
		ModalidadCandidato mc = null;
		ExpectativaLaboral expecLaboral = null;
		Candidato candidato = entityManager.find(Candidato.class, perfil.getIdCandidato());
		if (candidato != null) {
			candidato.setIdEstadoCivil(Utils.toInt(perfil.getIdEstadoCivil()));
			candidato.setConfidencialidadDatos(perfil.getConfidencialidad());
			candidato.setFechaUltimaActualizacion(new Date());
			candidato.setDiscapacidades(perfil.getDiscapacidades());
			if (null != perfil.getFolioProspera() && !perfil.getFolioProspera().isEmpty())
				candidato.setFolioProspera(perfil.getFolioProspera());
			entityManager.persist(candidato);
			PerfilLaboral pl = entityManager.find(PerfilLaboral.class, perfil.getIdCandidato());
			if (pl != null) {
				setPerfil(pl, perfil);
				entityManager.merge(pl);
				entityManager.flush();
			}
			Domicilio domicilio = entityManager.find(Domicilio.class, perfil.getIdDomicilio());
			if (domicilio != null) {
				setDomicilio(domicilio, perfil);
				entityManager.merge(domicilio);
				entityManager.flush();
			}
			if (null != el && el.getIdExpectativaLaboral() > 0)
				expecLaboral = entityManager.find(ExpectativaLaboral.class, el.getIdExpectativaLaboral());
			if (expecLaboral != null) { 
				setExpLaboral(expecLaboral, el);
				entityManager.merge(expecLaboral);
				entityManager.flush();
			}
			if (null != histLaboralVO && histLaboralVO.getIdHistorialLaboral() > 0) {
				hl = entityManager.find(HistoriaLaboral.class, histLaboralVO.getIdHistorialLaboral());
				if (hl != null) {
					setHistoriaLaboral(hl, histLaboralVO);
					entityManager.merge(hl);
				}
			}else if (null != histLaboralVO && null != histLaboralVO.getEmpresa() && !histLaboralVO.getEmpresa().isEmpty()) {
				hl = new HistoriaLaboral();
				setHistoriaLaboral(hl, histLaboralVO);
				hl.setFechaAlta(new Date());
				hl.setIdCandidato(perfil.getIdCandidato());
				entityManager.persist(hl);
				entityManager.flush();
			}
			if (null != sne && sne.getIdCandidato() > 0)
				entity = entityManager.find(FormatoSNE01.class, sne.getIdCandidato());
			if (null != entity) {
				entityManager.merge(getEntity(entity, sne));
			}else {
				entity = getEntity(new FormatoSNE01(), sne);
				if (null != entity) {
					entityManager.persist(entity);
					entityManager.flush();
				}
			}
			if (null != mcv && mcv.getIdSubprograma() > 0) {
				mc = this.getModalidad(perfil.getIdCandidato(), mcv.getIdModalidad(), mcv.getIdSubprograma());
				if (null != mc) {
					entityManager.merge(getEntity(mc, mcv));
				}else {
					mc = getEntity(new ModalidadCandidato(), mcv);
					entityManager.persist(mc);
					entityManager.flush();
				}
			}
		}else return -1;
		return 1;
	}
	
	@Override
	public List<BeneficiarioVO> getBeneficiarioList(long idCandidato, long idTipoFormato, boolean isContact) throws PersistenceException {
		List<BeneficiarioVO> beneficiaries = new ArrayList<BeneficiarioVO>();
		StringBuilder builder = new StringBuilder("select b from Beneficiario b where b.idCandidato = :idCandidato and b.idTipoFormato = :idTipoFormato");
		if (isContact) builder.append(" and b.recados = " + Constantes.DECISION.SI.getIdOpcion());
		else builder.append(" and (b.recados is null or b.recados = " + Constantes.DECISION.NO.getIdOpcion() + ")");
		Query query = entityManager.createQuery(builder.toString());
		query.setParameter("idCandidato", idCandidato);
		query.setParameter("idTipoFormato", idTipoFormato);
		try {
			@SuppressWarnings("unchecked")
			List<Beneficiario> results =  (List<Beneficiario>)query.getResultList();
			if (null != results && !results.isEmpty()) {
				for (Beneficiario entity : results) {
					BeneficiarioVO vo = getVO(entity);
					if (vo.getContacto() == Constantes.DECISION.SI.getIdOpcion())
						vo.setTelefono(getTelefonoVO(vo.getIdBeneficiario()));
					else vo.setDomicilio(getDomicilioVO(vo.getIdBeneficiario()));
					beneficiaries.add(vo);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return beneficiaries;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ReferenciaLaboralVO> getReferenciaLaboraList(long idCandidato) throws PersistenceException {
		Domicilio d = null;
		List<ReferenciaLaboralVO> referencias = new ArrayList<ReferenciaLaboralVO>();
		Query query = entityManager.createQuery("select h from HistoriaLaboral h where h.idCandidato = :idCandidato");
		query.setParameter("idCandidato", idCandidato);
		try {
			List<HistoriaLaboral> results =  (List<HistoriaLaboral>)query.getResultList();
			if (null != results && !results.isEmpty()) {
				for (HistoriaLaboral h : results) {
					if (h.getIdHistorialLaboral() > 0) {
						ReferenciaLaboralVO referencia = new ReferenciaLaboralVO();
						referencia.setTrabajoActual(getVO(h));
						Query queryd = entityManager.createQuery("SELECT d FROM Domicilio d WHERE d.idPropietario = :idPropietario AND d.idTipoPropietario = :idTipoPropietario");
						queryd.setParameter("idPropietario", h.getIdHistorialLaboral());
						queryd.setParameter("idTipoPropietario", Constantes.TIPO_PROPIETARIO.REFERENCIA_LABORAL.getIdTipoPropietario());
						try {
							d = (Domicilio)queryd.getSingleResult();
						}catch (NoResultException nre) {}
						if (null != d) {
							DomicilioVO domicilioVO = getVO(d);
							municipioFacadeLocal.setEntidadMunicipio(domicilioVO);
							referencia.setDomicilio(domicilioVO);
						}
						TelefonoVO phone = telefonoFacadeLocal.getTelefonoPrincipal(h.getIdHistorialLaboral(), TIPO_PROPIETARIO.REFERENCIA_LABORAL.getIdTipoPropietario());
						if (null != phone)
							referencia.setTelefono(phone);
						referencias.add(referencia);
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return referencias;
	}
	
	@Override
	public int removeReferenciaLaboral(ReferenciaLaboralVO reference) throws PersistenceException {
		int result = 0;
		TelefonoVO phone = reference.getTelefono();
		DomicilioVO dom = reference.getDomicilio();
		mx.gob.stps.portal.persistencia.vo.HistoriaLaboralVO historial = reference.getTrabajoActual();
		if (null != historial) {
			if (null != phone && phone.getIdTelefono() > 0) {
				Telefono t = entityManager.find(Telefono.class, phone.getIdTelefono());
				if (null != t && t.getIdPropietario() == historial.getIdCandidato() && t.getIdTipoPropietario() == TIPO_PROPIETARIO.REFERENCIA_LABORAL.getIdTipoPropietario())
					entityManager.remove(t);
			}
			if (null != dom && dom.getIdDomicilio() > 0) {
				Domicilio d = entityManager.find(Domicilio.class, dom.getIdDomicilio());
				if (null != d && d.getIdPropietario() == historial.getIdCandidato() && d.getIdTipoPropietario() == TIPO_PROPIETARIO.REFERENCIA_LABORAL.getIdTipoPropietario())
					entityManager.remove(d);
			}
			HistoriaLaboral entity = entityManager.find(HistoriaLaboral.class, historial.getIdHistorialLaboral());
			if (null != entity) {
				entityManager.remove(entity);
				result = 1;
			}
		}
		return result;
	}
	
	@Override
	public int removeBeneficiario(long idBeneficiario) throws PersistenceException {
		int result = 0;
		Beneficiario entity = entityManager.find(Beneficiario.class, idBeneficiario);
		if (null != entity) {
			entityManager.remove(entity);
			result = 1;
		}
		return result;
	}
	
	@Override
	public FormatoSNE01VO getFormatoSNE01(long idCandidato) throws PersistenceException {
		FormatoSNE01 entity = entityManager.find(FormatoSNE01.class, idCandidato);
		if (null != entity)
			return getVO(entity);
		return null;
	}
	
	@Override
	public FormatoLPAVO getFormatoLPA(long idCandidato) throws PersistenceException {
		FormatoLPA entity = entityManager.find(FormatoLPA.class, idCandidato);
		if (null != entity)
			return getVO(entity);
		return null;
	}
	
	public ModalidadCandidatoVO getModalidadCandidato(long idCandidato, long idModalidad, long idSubprograma) {
		ModalidadCandidato entity = null;
		if (idModalidad > 0)
			entity = getModalidad(idCandidato, idModalidad, idSubprograma);
		else entity = getModalidadBySubProgram(idCandidato, idSubprograma);
		return null != entity ? getVO(entity) :  null;
	}
	
	private ModalidadCandidato getModalidad(long idCandidato, long idModalidad, long idSubprograma) {
		Query query = entityManager.createQuery("select mc from ModalidadCandidato mc where mc.idCandidato = :idCandidato AND mc.idModalidad = :idModalidad AND mc.idSubprograma = :idSubprograma");
		query.setParameter("idCandidato", idCandidato);
		query.setParameter("idModalidad", idModalidad);
		query.setParameter("idSubprograma", idSubprograma);
		try {
			@SuppressWarnings("unchecked")
			List<ModalidadCandidato> results = (List<ModalidadCandidato>)query.getResultList();
			if (results!= null && !results.isEmpty())
				return results.get(0);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private ModalidadCandidato getModalidadBySubProgram(long idCandidato, long idSubprograma) {
		Query query = entityManager.createQuery("select mc from ModalidadCandidato mc where mc.idCandidato = :idCandidato AND mc.idSubprograma = :idSubprograma");
		query.setParameter("idCandidato", idCandidato);
		query.setParameter("idSubprograma", idSubprograma);
		try {
			@SuppressWarnings("unchecked")
			List<ModalidadCandidato> results = (List<ModalidadCandidato>)query.getResultList();
			if (results!= null && !results.isEmpty())
				return results.get(0);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public FormatoPTATVO getFormatoPTATVO(long idCandidato) throws PersistenceException {
		FormatoPTATVO vo = null;
		Query query = entityManager.createQuery("select p from FormatoPTAT p where p.idCandidato = :idCandidato");
		query.setParameter("idCandidato", idCandidato);
		try {
			FormatoPTAT result = (FormatoPTAT)query.getSingleResult();
			vo = getVO(result);
			query = entityManager.createQuery("select t from Telefono t where t.idPropietario = :idCandidato and t.idTipoPropietario = :idTipoPropietario");
			query.setParameter("idCandidato", idCandidato);
			query.setParameter("idTipoPropietario", Constantes.TIPO_PROPIETARIO.CASETA.getIdTipoPropietario());
			try {
				Telefono entity = (Telefono)query.getSingleResult();
				vo.setCaseta(getVO(entity));
			}catch (NoResultException ex) {
				ex.getMessage();
			}
		}catch (Exception e) {
			e.getMessage();
		}
		return vo;
	}
	
	@Override
	public List<FormatoPTATMaquinariaVO> getFormatoPTATMaquinaria(long idCandidato) throws PersistenceException {
		List<FormatoPTATMaquinariaVO> engines = new ArrayList<FormatoPTATMaquinariaVO>();
		Query query = entityManager.createQuery("select m from FormatoPTATMaquinaria m where m.idCandidato = :idCandidato");
		query.setParameter("idCandidato", idCandidato);
		try {
			@SuppressWarnings("unchecked")
			List<Object> results = query.getResultList();
			if (null != results && !results.isEmpty()) {
				for (Object entity : results) {
					engines.add(getVO((FormatoPTATMaquinaria)entity));
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return engines;
	}
	
	@Override
	public List<FormatoPTATCultivosVO> getFormatoPTATCultivos(long idCandidato) throws PersistenceException {
		List<FormatoPTATCultivosVO> products = new ArrayList<FormatoPTATCultivosVO>();
		Query query = entityManager.createQuery("select cdc from FormatoPTATCultivos cdc where cdc.idCandidato = :idCandidato");
		query.setParameter("idCandidato", idCandidato);
		try {
			@SuppressWarnings("unchecked")
			List<Object> results = query.getResultList();
			if (null != results && !results.isEmpty()) {
				for (Object entity : results) {
					products.add(getVO((FormatoPTATCultivos)entity));
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return products;
	}
	
	@Override
	public List<FormatoPTATHclinicaVO> getDiseases(long idCandidato) throws PersistenceException {
		List<FormatoPTATHclinicaVO> diseases = new ArrayList<FormatoPTATHclinicaVO>();
		Query query = entityManager.createQuery("select h from FormatoPTATHclinica h where h.idCandidato = :idCandidato");
		query.setParameter("idCandidato", idCandidato);
		try {
			@SuppressWarnings("unchecked")
			List<Object> results = query.getResultList();
			if (null != results && !results.isEmpty()) {
				for (Object entity : results) {
					diseases.add(getVO((FormatoPTATHclinica)entity));
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return diseases;
	}
	
	@Override
	public List<ModalidadPtatBeneficiarioVO> getModalidadPtatBeneficiarioList(long idModalidadCandidatoPTAT) throws PersistenceException {
		List<ModalidadPtatBeneficiarioVO> beneficiaries = new ArrayList<ModalidadPtatBeneficiarioVO>();
		Query query = entityManager.createQuery("select mpb from ModalidadPtatBeneficiario mpb where mpb.modalidadCandidatoPtat.idModalidadCandidatoPtat = :idModalidadCandidatoPTAT");
		query.setParameter("idModalidadCandidatoPTAT", idModalidadCandidatoPTAT);
		try {
			@SuppressWarnings("unchecked")
			List<ModalidadPtatBeneficiario> results =  (List<ModalidadPtatBeneficiario>)query.getResultList();
			if (null != results && !results.isEmpty()) {
				for (ModalidadPtatBeneficiario entity : results) {
					beneficiaries.add(getVO(entity));
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return beneficiaries;
	}
	
	@Override
	public List<CanalizacionCandidatoVO> getCanalizacionCandidatoList(long idCandidato) throws PersistenceException {
		List<CanalizacionCandidatoVO> cList = new ArrayList<CanalizacionCandidatoVO>();
		Query query = entityManager.createQuery("SELECT cl FROM CanalizacionCandidato cl WHERE cl.idCandidato = :idCandidato");
		query.setParameter("idCandidato", idCandidato);
		try {
			@SuppressWarnings("unchecked")
			List<CanalizacionCandidato> results = (List<CanalizacionCandidato>)query.getResultList();
			if (null != results && !results.isEmpty()) {
				for (CanalizacionCandidato entity : results) {
					cList.add(getVO(entity));
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return cList;
	}
	
	@Override
	public List<Long> getNominalList(long idOferta) throws PersistenceException {
		List<Long> cList = new ArrayList<Long>();// Se probará primero con MLISA
		StringBuilder squery = new StringBuilder("SELECT oc.id_candidato FROM OFERTA_CANDIDATO oc, MODALIDAD_CANDIDATO mc WHERE oc.id_oferta_empleo = ").append(idOferta);
		squery.append(" AND oc.id_candidato = mc.id_candidato AND mc.id_subprograma = ");
		squery.append(Constantes.MODALIDAD.MLISA.getIdSubprograma()).append(" AND oc.estatus IN (").append(ESTATUS.SELECCIONADA.getIdOpcion()).append(", ");
		squery.append(ESTATUS.SELECCIONADO.getIdOpcion()).append(", ").append(ESTATUS.VINCULADO.getIdOpcion()).append(")");
		Query query = entityManager.createNativeQuery(squery.toString());
		try {
			@SuppressWarnings("unchecked")
			List<Object> results = query.getResultList();
			if (null != results && !results.isEmpty()) {
				for (Object o : results) {
					cList.add(Utils.toLong(o));
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return cList;
	}
	
	private int persistEngines(long idCandidato, List<FormatoPTATMaquinariaVO> engines, int result) {
		try {
			if (null != engines && !engines.isEmpty()) {
				if (result > 0) result = removeEngines(idCandidato);
				for (FormatoPTATMaquinariaVO engine : engines) {
					entityManager.persist(getEntity(engine));
				}
			}
		}catch(Exception e) {
			result = 0;
			e.printStackTrace();
			logger.error("Error ModalidadFacade persistEngines " + e.getMessage());
		}
		return result;
	}
	
	private int persistClinicalHistory(long idCandidato, List<FormatoPTATHclinicaVO> histories, int result) {
		try {
			if (null != histories && !histories.isEmpty()) {
				if (result > 0) removeClinicalHistory(idCandidato);
				for (FormatoPTATHclinicaVO vo : histories) {
					entityManager.persist(getEntity(vo));
				}
			}
		}catch(Exception e) {
			result = 0;
			e.printStackTrace();
			logger.error("Error ModalidadFacade persistEngines " + e.getMessage());
		}
		return result;
	}
	
	private int persistCandidatoCultivosPTAT(long idCandidato, List<FormatoPTATCultivosVO> products, int result) {
		try {
			if (!products.isEmpty()) {
				if (result > 0)  {
					result = removeCultivosPTAT(idCandidato);
				}
				for (FormatoPTATCultivosVO product : products) {
					entityManager.persist(getEntity(product));
				}
			}
		}catch(Exception e) {
			result = 0;
			e.printStackTrace();
			logger.error("Error ModalidadFacade persistCandidatoDatosComplPTAT " + e.getMessage());
		}
		return result;
	}
	
	public int updateBeneficiarioList(List<BeneficiarioVO> beneficiarioList) {
		int result = 0;
		if (null == beneficiarioList || beneficiarioList.isEmpty()) return result;
		try {
			for (BeneficiarioVO vo : beneficiarioList) {
				Beneficiario entity = null;
				if (null != vo.getIdBeneficiario())
					entity = entityManager.find(Beneficiario.class, vo.getIdBeneficiario());
				if (null != entity && entity.getIdBeneficiario() > 0) {
					entityManager.merge(getEntity(vo));
				}else {
					entity = getEntity(vo);
					entityManager.persist(entity);
					entityManager.flush();
				}
				if (vo.getContacto() == DECISION.SI.getIdOpcion())
					result = updatePhone(vo.getTelefono(), entity.getIdBeneficiario());
				else {
					vo.getDomicilio().setIdPropietario(entity.getIdBeneficiario());
					result = updateDomicilio(vo.getDomicilio());
				}
				result += 1;
			}
		}catch(Exception e) {
			result = -1;
			e.printStackTrace();
			logger.error("Error ModalidadFacade persistBeneficiary " + e.getMessage());
		}
		return result;
	}
	
	private int updateDomainLangs(List<IdiomaVO> langs) {
		int result = 0;
		if (null == langs || langs.isEmpty()) return result;
		try {
			CandidatoIdioma entity = null;
			for (IdiomaVO vo : langs) {
				entity = entityManager.find(CandidatoIdioma.class, vo.getIdCandidatoIdioma());
				if (null != entity) {
					entity.setIdDominio(vo.getIdDominio());
					entityManager.merge(entity);
				}else {
					entityManager.persist(getEntity(vo));
					entityManager.flush();
				}
			}
		}catch(Exception e) {
			result = -1;
			e.printStackTrace();
			logger.error("Error ModalidadFacade updateDomainLangs " + e.getMessage());
		}
		return result;
	}
	
	private int updateDomicilio(DomicilioVO vo) {
		if (null == vo || null == vo.getIdPropietario()) return -1;
		int result = 1;
		try {
			Domicilio entity = entityManager.find(Domicilio.class, vo.getIdDomicilio());
			if (null != entity && entity.getIdDomicilio() > 0) {
				entityManager.merge(getEntity(entity, vo));
			}else {
				entity = getEntity(new Domicilio(), vo);
				entity.setIdTipoPropietario(vo.getIdTipoPropietario());
				entityManager.persist(entity);
				entityManager.flush();
			}
		}catch(Exception e) {
			result = -1;
			logger.error("Error ModalidadFacade persistDomicilioBeneficiary " + e.getMessage());
		}
		return result;
	}
	
	private int updatePhone(TelefonoVO vo, long idPropietario) {
		if (null == vo) return -1;
		int result = 1;
		try {
			Telefono entity = entityManager.find(Telefono.class, vo.getIdTelefono());
			if (null != entity && entity.getIdTelefono() > 0) {
				setEntity(entity, vo);
				entityManager.merge(entity);
			}else {
				entity = new Telefono();
				entity.setFechaAlta(new Date());
				entity.setIdPropietario(idPropietario);
				setEntity(entity, vo);
				entityManager.persist(entity);
				entityManager.flush();
			}
		}catch(Exception e) {
			result = -1;
			e.printStackTrace();
			logger.error("Error ModalidadFacade updatePhone " + e.getMessage());
		}
		return result;
	}
	
	private FormatoPTATCultivos getEntity(FormatoPTATCultivosVO vo) {
		FormatoPTATCultivos entity = new FormatoPTATCultivos();
		entity.setIdCandidato(vo.getIdCandidato());
		entity.setIdCultivo(vo.getIdCultivo());
		entity.setIdtipoCultivo(vo.getIdtipoCultivo());
		return entity;
	}
	
	private FormatoPTAT getEntity(FormatoPTAT entity, FormatoPTATVO vo) {
		entity.setPeso(vo.getPeso());
		entity.setCorreoElectronico2(vo.getCorreoElectronico2());
		entity.setEstatura(vo.getEstatura());
		entity.setHijosMenores18(vo.getHijosMenores18());
		entity.setIdComplexion(vo.getIdComplexion());
		entity.setLicenciaSituacion(vo.getLicenciaSituacion());
		entity.setNumeroDependientes(vo.getNumeroDependientes());
		entity.setNumeroHijos(vo.getNumeroHijos());
		entity.setTieneIntervencionesMedicas(vo.getTieneIntervencionesMedicas());
		entity.setIdExperienciaAgricola(vo.getIdExperienciaAgricola());
		entity.setLicenciaInternacionalDisp(vo.getLicenciaInternacionalDisp());
		entity.setOtrosTiposCultivo(vo.getOtrosTiposCultivo());
		return entity;
	}
	
	private HistoriaLaboral getEntity(mx.gob.stps.portal.persistencia.vo.HistoriaLaboralVO histLaboralVO) {
		HistoriaLaboral histLaboral = new HistoriaLaboral();
		histLaboral.setPuesto(histLaboralVO.getPuesto());
		histLaboral.setEmpresa(histLaboralVO.getEmpresa());
		histLaboral.setPrincipal(histLaboralVO.getPrincipal());
		histLaboral.setLaboresInicial(histLaboralVO.getLaboresInicial());
		histLaboral.setLaboresFinal(histLaboralVO.getLaboresFinal());
		histLaboral.setIdJerarquia(histLaboralVO.getIdJerarquia());
		histLaboral.setTrabajoActual(histLaboralVO.getTrabajoActual());
		histLaboral.setSalarioMensual(histLaboralVO.getSalarioMensual());
		histLaboral.setFuncion(histLaboralVO.getFuncion());
		histLaboral.setHerramientas(histLaboralVO.getHerramientas());
		histLaboral.setRefNombre(histLaboralVO.getRefNombre());
		histLaboral.setRefApellido1(histLaboralVO.getRefApellido1());
		histLaboral.setRefApellido2(histLaboralVO.getRefApellido2());
		histLaboral.setRefPuesto(histLaboralVO.getRefPuesto());
		histLaboral.setPersonasCargo(Utils.toLong(histLaboralVO.getPersonasCargo()));
		histLaboral.setConfidencialidadEmpresa(Utils.toLong(histLaboralVO.getConfidencialidadEmpresa()));
		return histLaboral;
	}
	
	private HistoriaLaboral getEntity(HistoriaLaboral histLaboral, mx.gob.stps.portal.persistencia.vo.HistoriaLaboralVO histLaboralVO) {
		histLaboral.setPuesto(histLaboralVO.getPuesto());
		histLaboral.setEmpresa(histLaboralVO.getEmpresa());
		histLaboral.setPrincipal(histLaboralVO.getPrincipal());
		histLaboral.setLaboresInicial(histLaboralVO.getLaboresInicial());
		histLaboral.setLaboresFinal(histLaboralVO.getLaboresFinal());
		histLaboral.setIdJerarquia(histLaboralVO.getIdJerarquia());
		histLaboral.setTrabajoActual(histLaboralVO.getTrabajoActual());
		histLaboral.setSalarioMensual(histLaboralVO.getSalarioMensual());
		histLaboral.setFuncion(histLaboralVO.getFuncion());
		histLaboral.setHerramientas(histLaboralVO.getHerramientas());
		histLaboral.setRefNombre(histLaboralVO.getRefNombre());
		histLaboral.setRefApellido1(histLaboralVO.getRefApellido1());
		histLaboral.setRefApellido2(histLaboralVO.getRefApellido2());
		histLaboral.setRefPuesto(histLaboralVO.getRefPuesto());
		histLaboral.setPersonasCargo(Utils.toLong(histLaboralVO.getPersonasCargo()));
		histLaboral.setConfidencialidadEmpresa(Utils.toLong(histLaboralVO.getConfidencialidadEmpresa()));
		return histLaboral;
	}
	
	private void setEntity(Telefono entity, TelefonoVO vo) {
		entity.setAcceso(vo.getAcceso());
		entity.setClave(vo.getClave());
		entity.setExtension(vo.getExtension());
		entity.setIdTipoTelefono(vo.getIdTipoTelefono());
		entity.setPrincipal(vo.getPrincipal());
		entity.setTelefono(vo.getTelefono());
		entity.setIdTipoPropietario(vo.getIdTipoPropietario());
	}
	
	private Domicilio getEntity(Domicilio entity, DomicilioVO vo) {
		entity.setIdEntidad(vo.getIdEntidad());
		entity.setIdMunicipio(vo.getIdMunicipio());
		entity.setIdColonia(vo.getIdColonia());
		entity.setCalle(vo.getCalle());
		entity.setNumeroInterior(vo.getNumeroInterior());
		entity.setNumeroExterior(vo.getNumeroExterior());
		entity.setEntreCalle(vo.getEntreCalle());
		entity.setYCalle(vo.getyCalle());
		entity.setCodigoPostal(vo.getCodigoPostal());
		entity.setIdPropietario(vo.getIdPropietario());
		entity.setIdColonia(vo.getIdColonia());
		entity.setIdLocalidad(vo.getIdLocalidad());
		entity.setDomicilioRef(vo.getDomicilioRef());
		entity.setFechaAlta(null != vo.getFechaAlta() ? vo.getFechaAlta() : new Date());
		municipioFacadeLocal.setEntidadMunicipio(vo);
		return entity;
	}
	
	private DomicilioVO getVO(Domicilio entity) {
		DomicilioVO domicilioVO = new DomicilioVO();
		domicilioVO.setIdDomicilio(entity.getIdDomicilio());
		domicilioVO.setIdTipoPropietario(entity.getIdTipoPropietario());
		domicilioVO.setIdEntidad(entity.getIdEntidad());
		domicilioVO.setIdMunicipio(entity.getIdMunicipio());
		domicilioVO.setIdColonia(entity.getIdColonia());
		domicilioVO.setCalle(entity.getCalle());
		domicilioVO.setNumeroInterior(entity.getNumeroInterior());
		domicilioVO.setNumeroExterior(entity.getNumeroExterior());
		domicilioVO.setEntreCalle(entity.getEntreCalle());
		domicilioVO.setyCalle(entity.getYCalle());
		domicilioVO.setCodigoPostal(entity.getCodigoPostal());
		domicilioVO.setIdPropietario(entity.getIdPropietario());
		domicilioVO.setIdColonia(entity.getIdColonia());
		domicilioVO.setIdLocalidad(entity.getIdLocalidad());
		domicilioVO.setDomicilioRef(entity.getDomicilioRef());
		domicilioVO.setFechaAlta(null != entity.getFechaAlta() ? entity.getFechaAlta() : new Date());
		return domicilioVO;
	}
	
	private CandidatoIdioma getEntity(IdiomaVO vo) {
		CandidatoIdioma entity = new CandidatoIdioma();
		entity.setFechaAlta(new Date());
		entity.setIdCandidato(vo.getIdCandidato());
		entity.setIdDominio(vo.getIdDominio());
		entity.setIdIdioma(vo.getIdIdioma());
		entity.setPrincipal((long)vo.getPrincipal());
		entity.setIdCertificacion(vo.getIdCertificacion());
		return entity;
	}
	
	private Beneficiario getEntity(BeneficiarioVO vo) {
		Beneficiario entity = new Beneficiario();
		if (null != vo.getEdad())
			entity.setEdad(vo.getEdad());
		if (null != vo.getFinado())
			entity.setFinado(vo.getFinado());
		entity.setNombre(vo.getNombre());
		if (null != vo.getPorcentaje())
			entity.setPorcentaje(vo.getPorcentaje());
		entity.setIdCandidato(vo.getIdCandidato());
		if (null != vo.getDependiente())
			entity.setDependiente(vo.getDependiente());
		if (null != vo.getBeneficiario())
			entity.setBeneficiario(vo.getBeneficiario());
		entity.setIdParentesco(vo.getIdParentesco());
		entity.setIdTipoFormato(vo.getIdTipoFormato());
		entity.setPrimerApellido(vo.getPrimerApellido());
		if (null != vo.getSegundoApellido())
			entity.setSegundoApellido(vo.getSegundoApellido());
		if (null != vo.getIdBeneficiario())
			entity.setIdBeneficiario(vo.getIdBeneficiario());
		if (null != vo.getContacto())
			entity.setRecados(vo.getContacto());
		return entity;
	}
	
	private BeneficiarioVO getVO(Beneficiario entity) {
		BeneficiarioVO vo = new BeneficiarioVO();
		vo.setEdad(entity.getEdad());
		vo.setFinado(entity.getFinado());
		vo.setNombre(entity.getNombre());
		vo.setPorcentaje(entity.getPorcentaje());
		vo.setIdCandidato(entity.getIdCandidato());
		vo.setDependiente(entity.getDependiente());
		vo.setBeneficiario(entity.getBeneficiario());
		vo.setIdParentesco(entity.getIdParentesco());
		vo.setIdTipoFormato(entity.getIdTipoFormato());
		vo.setIdBeneficiario(entity.getIdBeneficiario());
		vo.setPrimerApellido(entity.getPrimerApellido());
		vo.setSegundoApellido(entity.getSegundoApellido());
		vo.setContacto(entity.getRecados());
		return vo;
	}
	
	private ModalidadCandidato getEntity(ModalidadCandidatoVO mc) {
		ModalidadCandidato entity = new ModalidadCandidato();
		entity.setIdCandidato(mc.getIdCandidato());
		entity.setIdModalidad(mc.getIdModalidad());
		entity.setEstatus(mc.getEstatus());
		return entity;
	}
	
	private ModalidadCandidato getEntity(ModalidadCandidato entity, ModalidadCandidatoVO mc) {
		entity.setIdCandidato(mc.getIdCandidato());
		entity.setIdModalidad(mc.getIdModalidad());
		entity.setEstatus(mc.getEstatus());
		entity.setFechaRegistro(mc.getFechaRegistro());
		entity.setIdSubprograma(mc.getIdSubprograma());
		entity.setIdUsuario(mc.getIdUsuario());
		return entity;
	}
	
	private FormatoSNE01 getEntity(FormatoSNE01 entity, FormatoSNE01VO sne) {
		if (null == sne) return null;
		entity.setVacanteNombre(sne.getVacanteNombre());
		entity.setCapacitacionDesc(sne.getCapacitacionDesc());
		entity.setVacanteEnviado(sne.getVacanteEnviado());
		entity.setVacanteFecha(sne.getVacanteFecha());
		entity.setVacanteColocado(sne.getVacanteColocado());
		entity.setLenguaIndigenaConocimiento(sne.getLenguaIndigenaConocimiento());
		if (sne.getLenguaIndigenaConocimiento() != 2)
			entity.setIdLenguaIndigena(null);
		else entity.setIdLenguaIndigena(sne.getIdLenguaIndigena());
		entity.setCapacitacionRequiere(sne.getCapacitacionRequiere());
		entity.setCapacitacionDispone6h(sne.getCapacitacionDispone6h());
		entity.setNegocioTiene(sne.getNegocioTiene());
		entity.setNegocioDescGiro(sne.getNegocioDescGiro());
		entity.setNegocioPretende(sne.getNegocioPretende());
		entity.setNegocioTieneRecursos(sne.getNegocioTieneRecursos());
		entity.setBeneficiarioIdPrograma(sne.getBeneficiarioIdPrograma());
		entity.setBeneficiarioPrograma(sne.getBeneficiarioPrograma());
		entity.setClabeInterbancaria(sne.getClabeInterbancaria());
		entity.setIdBanco(sne.getIdBanco());
		if (null == sne.getFechaRegistro())
			entity.setFechaRegistro(new Date());
		else entity.setFechaRegistro(sne.getFechaRegistro());
		entity.setIdFuente(sne.getIdFuente());
		entity.setIdUsuario(sne.getIdUsuario());
		entity.setIdCandidato(sne.getIdCandidato());
		entity.setDisponibilidadRadicarPais(sne.getDisponibilidadRadicarPais());
		return entity;
	}
	
	private FormatoLPAVO getVO(FormatoLPA entity) {
		FormatoLPAVO mml = new FormatoLPAVO();
		mml.setObjetivos(entity.getObjetivos());
		mml.setIdCandidato(entity.getIdCandidato());
		mml.setObservaciones(entity.getObservaciones());
		mml.setEntretenimiento(entity.getEntretenimiento());
		return mml;
	}
	
	private FormatoSNE01VO getVO(FormatoSNE01 sne) {
		FormatoSNE01VO vo = new FormatoSNE01VO();
		vo.setVacanteNombre(sne.getVacanteNombre());
		vo.setCapacitacionDesc(sne.getCapacitacionDesc());
		vo.setVacanteEnviado(sne.getVacanteEnviado());
		vo.setVacanteFecha(sne.getVacanteFecha());
		vo.setVacanteColocado(sne.getVacanteColocado());
		if (null != sne.getIdLenguaIndigena())
			vo.setIdLenguaIndigena(sne.getIdLenguaIndigena());
		vo.setLenguaIndigenaConocimiento(sne.getLenguaIndigenaConocimiento());
		vo.setCapacitacionRequiere(sne.getCapacitacionRequiere());
		vo.setCapacitacionDispone6h(sne.getCapacitacionDispone6h());
		vo.setNegocioTiene(sne.getNegocioTiene());
		vo.setNegocioDescGiro(sne.getNegocioDescGiro());
		vo.setNegocioPretende(sne.getNegocioPretende());
		vo.setNegocioTieneRecursos(sne.getNegocioTieneRecursos());
		vo.setBeneficiarioIdPrograma(sne.getBeneficiarioIdPrograma());
		vo.setBeneficiarioPrograma(sne.getBeneficiarioPrograma());
		vo.setClabeInterbancaria(sne.getClabeInterbancaria());
		vo.setIdBanco(sne.getIdBanco());
		vo.setIdFuente(sne.getIdFuente());
		vo.setIdUsuario(sne.getIdUsuario());
		vo.setIdCandidato(sne.getIdCandidato());
		vo.setFechaRegistro(sne.getFechaRegistro());
		vo.setDisponibilidadRadicarPais(sne.getDisponibilidadRadicarPais());
		return vo;
	}
	
	private ModalidadCandidatoVO getVO(ModalidadCandidato entity) {
		ModalidadCandidatoVO vo = new ModalidadCandidatoVO();
		vo.setEstatus(entity.getEstatus());
		vo.setIdUsuario(entity.getIdUsuario());
		vo.setIdCandidato(entity.getIdCandidato());
		vo.setIdModalidad(entity.getIdModalidad());
		vo.setFechaRegistro(entity.getFechaRegistro());
		vo.setIdSubprograma(entity.getIdSubprograma());
		vo.setIdModalidadCandidato(entity.getIdModalidadCandidato());
		return vo;
	}
	
	private ModalidadPtatBeneficiarioVO getVO(ModalidadPtatBeneficiario entity) {
		ModalidadPtatBeneficiarioVO vo = new ModalidadPtatBeneficiarioVO();
		vo.setNombre(entity.getNombre());
		vo.setParentesco(entity.getParentesco());
		vo.setPrimerApellido(entity.getPrimerApellido());
		vo.setSegundoApellido(entity.getSegundoApellido());
		vo.setPorcentajeBeneficio(entity.getPorcentajeBeneficio());
		vo.setModalidadCandidatoPtat(entity.getModalidadCandidatoPtat());
		vo.setIdModalidadPtatBeneficiario(entity.getIdModalidadPtatBeneficiario());
		return vo;
	}
	
	private FormatoPTATVO getVO(FormatoPTAT entity) {
		FormatoPTATVO vo = new FormatoPTATVO();
		vo.setPeso(entity.getPeso());
		vo.setEstatura(entity.getEstatura());
		vo.setNumeroHijos(entity.getNumeroHijos());
		vo.setIdCandidato(entity.getIdCandidato());
		vo.setIdComplexion(entity.getIdComplexion());
		vo.setHijosMenores18(entity.getHijosMenores18());
		vo.setLicenciaSituacion(entity.getLicenciaSituacion());
		vo.setNumeroDependientes(entity.getNumeroDependientes());
		vo.setCorreoElectronico2(entity.getCorreoElectronico2());
		vo.setIdExperienciaAgricola(entity.getIdExperienciaAgricola());
		vo.setTieneIntervencionesMedicas(entity.getTieneIntervencionesMedicas());
		vo.setLicenciaInternacionalDisp(entity.getLicenciaInternacionalDisp());
		vo.setOtrosTiposCultivo(entity.getOtrosTiposCultivo());
		return vo;
	}
	
	private CanalizacionCandidatoVO getVO(CanalizacionCandidato entity) {
		CanalizacionCandidatoVO vo = new CanalizacionCandidatoVO();
		vo.setEstatus((int)entity.getEstatus());
		vo.setIdOficina(entity.getIdOficina());
		vo.setIdUsuario(entity.getIdUsuario());
		vo.setIdEncuesta(entity.getIdEncuesta());
		vo.setFechaInicio(entity.getFechaInicio());
		vo.setFechaModificacion(entity.getFechaModificacion());
		vo.setFechaCanalizacion(entity.getFechaCanalizacion());
		vo.setIdModalidadCandidato(entity.getIdModalidadCandidato());
		return vo;
	}
	
	private TelefonoVO getVO(Telefono entity) {
		TelefonoVO vo = new TelefonoVO();
		vo.setAcceso(entity.getAcceso());
		vo.setClave(entity.getClave());
		vo.setExtension(entity.getExtension());
		vo.setIdPropietario(entity.getIdPropietario());
		vo.setIdTelefono(entity.getIdTelefono());
		vo.setIdTipoPropietario(entity.getIdTipoPropietario());
		vo.setIdTipoTelefono(entity.getIdTipoTelefono());
		vo.setPrincipal(entity.getPrincipal());
		vo.setTelefono(entity.getTelefono());
		return vo;
	}
	
	private FormatoPTATMaquinariaVO getVO(FormatoPTATMaquinaria entity) {
		FormatoPTATMaquinariaVO vo = new FormatoPTATMaquinariaVO();
		vo.setIdCandidato(entity.getIdCandidato());
		vo.setIdMaquinaria(entity.getIdMaquinaria());
		return vo;
	}
	
	private FormatoPTATCultivosVO getVO(FormatoPTATCultivos entity) {
		FormatoPTATCultivosVO vo = new FormatoPTATCultivosVO();
		vo.setIdCandidato(entity.getIdCandidato());
		vo.setIdCultivo(entity.getIdCultivo());
		vo.setIdtipoCultivo(entity.getIdtipoCultivo());
		return vo;
	}
	
	private FormatoPTATMaquinaria getEntity(FormatoPTATMaquinariaVO vo) {
		FormatoPTATMaquinaria entity = new FormatoPTATMaquinaria();
		entity.setIdCandidato(vo.getIdCandidato());
		entity.setIdMaquinaria(vo.getIdMaquinaria());
		return entity;
	}
	
	private FormatoPTATHclinica getEntity(FormatoPTATHclinicaVO vo) {
		FormatoPTATHclinica entity = new FormatoPTATHclinica();
		entity.setIdPatogia(vo.getIdPatogia());
		entity.setIdCandidato(vo.getIdCandidato());
		entity.setIdCatalogoPatologias(vo.getIdCatalogoPatologias());
		return entity;
	}
	
	private FormatoPTATHclinicaVO getVO(FormatoPTATHclinica entity) {
		FormatoPTATHclinicaVO vo = new FormatoPTATHclinicaVO();
		vo.setIdPatogia(entity.getIdPatogia());
		vo.setIdCandidato(entity.getIdCandidato());
		vo.setIdCatalogoPatologias(entity.getIdCatalogoPatologias());
		return vo;
	}
	
	private int removeCultivosPTAT(long idCandidato) {
		Query query = entityManager.createQuery("DELETE FROM FormatoPTATCultivos cdc WHERE cdc.idCandidato = :idCandidato");
		query.setParameter("idCandidato", idCandidato);
		return query.executeUpdate();
	}
	
	private int removeEngines(long idCandidato) {
		Query query = entityManager.createQuery("DELETE FROM FormatoPTATMaquinaria m WHERE m.idCandidato = :idCandidato");
		query.setParameter("idCandidato", idCandidato);
		return query.executeUpdate();
	}
	
	private int removeClinicalHistory(long idCandidato) {
		Query query = entityManager.createQuery("DELETE FROM FormatoPTATHclinica c WHERE c.idCandidato = :idCandidato");
		query.setParameter("idCandidato", idCandidato);
		return query.executeUpdate();
	}
	
	private TelefonoVO getTelefonoVO(long idPropietario) {
		TelefonoVO vo = null;
		Query query = entityManager.createQuery("SELECT t FROM Telefono t WHERE t.idPropietario = :idPropietario AND t.idTipoPropietario = :idTipoPropietario");
		query.setParameter("idPropietario", idPropietario);
		query.setParameter("idTipoPropietario", Constantes.TIPO_PROPIETARIO.BENEFICIARIO.getIdTipoPropietario());
		try {
			Telefono entity = (Telefono)query.getSingleResult();
			if (null != entity) {
				vo = new TelefonoVO();
				vo.setAcceso(entity.getAcceso());
				vo.setClave(entity.getClave());
				vo.setExtension(entity.getExtension());
				vo.setFechaAlta(entity.getFechaAlta());
				vo.setIdPropietario(entity.getIdPropietario());
				vo.setIdTelefono(entity.getIdTelefono());
				vo.setIdTipoPropietario(entity.getIdTipoPropietario());
				vo.setIdTipoTelefono(entity.getIdTipoTelefono());
				vo.setPrincipal(entity.getPrincipal());
				vo.setTelefono(entity.getTelefono());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	private DomicilioVO getDomicilioVO(long idPropietario) {
		DomicilioVO vo = null;
		Query query = entityManager.createQuery("SELECT d FROM Domicilio d WHERE d.idPropietario = :idPropietario AND d.idTipoPropietario = :idTipoPropietario");
		query.setParameter("idPropietario", idPropietario);
		query.setParameter("idTipoPropietario", Constantes.TIPO_PROPIETARIO.BENEFICIARIO.getIdTipoPropietario());
		try {
			Domicilio entity = (Domicilio)query.getSingleResult();
			if (null != entity) {
				vo = new DomicilioVO();
				vo.setCalle(entity.getCalle());
				vo.setCodigoPostal(entity.getCodigoPostal());
				vo.setDomicilioRef(entity.getDomicilioRef());
				vo.setEntreCalle(entity.getEntreCalle());
				vo.setIdColonia(entity.getIdColonia());
				vo.setIdDomicilio(entity.getIdDomicilio());
				vo.setIdEntidad(entity.getIdEntidad());
				vo.setIdMunicipio(entity.getIdMunicipio());
				vo.setNumeroExterior(entity.getNumeroExterior());
				vo.setNumeroInterior(entity.getNumeroInterior());
				vo.setyCalle(entity.getYCalle());
				municipioFacadeLocal.setEntidadMunicipio(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	private void setPerfil(PerfilLaboral pl, PerfilVO perfil) {
		pl.setIdCandidato(perfil.getIdCandidato());
		pl.setContactoCorreo(perfil.getContactoCorreo());
		pl.setContactoTelefono(perfil.getContactoTelefono());
		pl.setHorarioContactoDe(perfil.getHoraContactoIni());
		pl.setHorarioContactoA(perfil.getHoraContactoFin());
		pl.setIdRecibeOferta(perfil.getIdRecibeOferta());
		pl.setEmpleadoActualmente(perfil.getIdTrabaja());
		pl.setIdRazonBusqueda(perfil.getIdRazonBusqueda());
		pl.setInicioBusqueda(perfil.getInicioBusqueda());
		if (perfil.getIdLicencia() > 0)
			pl.setLicenciaId(perfil.getIdLicencia());
		if (perfil.getDisponibilidadRadicarPais() > 0)
			pl.setDisponibilidadRadicarPais(perfil.getDisponibilidadRadicarPais());
	}
	
	private void setExpLaboral(ExpectativaLaboral expLaboral, ExpectativaLaboralVO expLaboralVO) {
		expLaboral.setPuestoDeseado(expLaboralVO.getPuestoDeseado());
		expLaboral.setIdAreaLaboralDeseada(getTwoFirst(expLaboralVO.getIdOcupacionDeseada()));
		expLaboral.setIdOcupacionDeseada(expLaboralVO.getIdOcupacionDeseada());
		expLaboral.setSalarioPretendido(expLaboralVO.getSalarioPretendido());
		expLaboral.setIdTipoEmpleoDeseado(expLaboralVO.getIdTipoEmpleoDeseado());
		expLaboral.setIdTipoContrato(expLaboralVO.getIdTipoContrato());
		expLaboral.setPrincipal(Utils.toLong(expLaboralVO.getPrincipal()));
		expLaboral.setIdExperiencia(expLaboralVO.getIdExperiencia());
	}
	
	private void setHistoriaLaboral(HistoriaLaboral histLaboral, HistoriaLaboralVO histLaboralVO) {
		histLaboral.setPuesto(histLaboralVO.getPuesto());
		histLaboral.setIdOcupacion(histLaboralVO.getIdOcupacion());
		histLaboral.setEmpresa(histLaboralVO.getEmpresa());
		histLaboral.setPrincipal(Utils.toLong(histLaboralVO.getPrincipal()));
		histLaboral.setTrabajoActual(Utils.toLong(histLaboralVO.getTrabajoActual()));
		histLaboral.setConfidencialidadEmpresa(Utils.toLong(histLaboralVO.getConfidencialidadEmpresa()));
		histLaboral.setLaboresInicial(histLaboralVO.getLaboresInicial());
		histLaboral.setLaboresFinal(histLaboralVO.getLaboresFinal());
		histLaboral.setAniosLaborados(Utils.toLong(histLaboralVO.getAniosLaborados()));
		histLaboral.setIdJerarquia(histLaboralVO.getIdJerarquia());
		histLaboral.setPersonasCargo(Utils.toLong(histLaboralVO.getPersonasCargo()));
		histLaboral.setSalarioMensual(histLaboralVO.getSalarioMensual());
		histLaboral.setFuncion(histLaboralVO.getFuncion());
		histLaboral.setPrincipal(Utils.toLong(histLaboralVO.getPrincipal()));
		if (null != histLaboralVO.getHerramientas()) {
			histLaboral.setHerramientas(histLaboralVO.getHerramientas());
			histLaboral.setRefNombre(histLaboralVO.getRefNombre());
			histLaboral.setRefApellido1(histLaboralVO.getRefApellido1());
			histLaboral.setRefApellido2(histLaboralVO.getRefApellido2());
			histLaboral.setRefPuesto(histLaboralVO.getRefPuesto());
		}
	}
	
	private void setDomicilio(Domicilio dom, PerfilVO perfilVO) {
		dom.setIdTipoPropietario(perfilVO.getIdTipoPropietario());
		dom.setIdEntidad(perfilVO.getIdEntidad());
		dom.setIdMunicipio(perfilVO.getIdMunicipio());
		dom.setIdColonia(perfilVO.getIdColonia());
		dom.setCalle(perfilVO.getCalle());
		dom.setNumeroExterior(perfilVO.getNumeroExterior());
		dom.setNumeroInterior(perfilVO.getNumeroInterior());
		dom.setEntreCalle(perfilVO.getEntreCalle());
		dom.setYCalle(perfilVO.getyCalle());
		dom.setCodigoPostal(perfilVO.getCodigoPostal());
		dom.setIdLocalidad(perfilVO.getIdLocalidad());
		dom.setDomicilioRef(perfilVO.getDomicilioRef());
	}
	
	private void setDomicilio(Domicilio dom, DomicilioVO domicilioVO) {
		dom.setIdTipoPropietario(domicilioVO.getIdTipoPropietario());
		dom.setIdEntidad(domicilioVO.getIdEntidad());
		dom.setIdMunicipio(domicilioVO.getIdMunicipio());
		dom.setIdColonia(domicilioVO.getIdColonia());
		dom.setCalle(domicilioVO.getCalle());
		dom.setNumeroExterior(domicilioVO.getNumeroExterior());
		dom.setNumeroInterior(domicilioVO.getNumeroInterior());
		dom.setEntreCalle(domicilioVO.getEntreCalle());
		dom.setYCalle(domicilioVO.getyCalle());
		dom.setCodigoPostal(domicilioVO.getCodigoPostal());
		dom.setIdLocalidad(domicilioVO.getIdLocalidad());
		dom.setDomicilioRef(domicilioVO.getDomicilioRef());
	}
	
	private  mx.gob.stps.portal.persistencia.vo.HistoriaLaboralVO getVO(HistoriaLaboral entity) {
		mx.gob.stps.portal.persistencia.vo.HistoriaLaboralVO vo = new mx.gob.stps.portal.persistencia.vo.HistoriaLaboralVO();
		vo.setFuncion(entity.getFuncion());
		vo.setEmpresa(entity.getEmpresa());
		vo.setPuesto(entity.getPuesto());
		vo.setIdCandidato(entity.getIdCandidato());
		vo.setLaboresFinal(entity.getLaboresInicial());
		vo.setLaboresFinal(entity.getLaboresFinal());
		vo.setHerramientas(entity.getHerramientas());
		vo.setIdJerarquia(entity.getIdJerarquia());
		vo.setIdOcupacion(entity.getIdOcupacion());
		vo.setRefApellido1(entity.getRefApellido1());
		vo.setRefApellido2(entity.getRefApellido2());
		vo.setRefNombre(entity.getRefNombre());
		vo.setRefPuesto(entity.getRefPuesto());
		vo.setPrincipal(entity.getPrincipal());
		vo.setSalarioMensual(entity.getSalarioMensual());
		vo.setIdHistorialLaboral(entity.getIdHistorialLaboral());
		vo.setTrabajoActual(entity.getTrabajoActual());
		vo.setConfidencialidadEmpresa(entity.getConfidencialidadEmpresa());
		return vo;
	}
	
	private void setDegree(CandidatoGradoAcademico gradoAcademico, GradoAcademicoVO gradoAcademicoVO) {
		gradoAcademico.setLugar(gradoAcademicoVO.getLugar());
		gradoAcademico.setEscuela(gradoAcademicoVO.getEscuela());
		gradoAcademico.setFechaFin(gradoAcademicoVO.getFechaFin());
		gradoAcademico.setFechaInicio(gradoAcademicoVO.getFechaInicio());
	}
	
	private void setPhone(Telefono entity, TelefonoVO vo) {
		String telefono = Utils.cut(vo.getTelefono(), 8);
		entity.setAcceso(vo.getAcceso());
		entity.setClave(vo.getClave());
		entity.setExtension(vo.getExtension());
		entity.setFechaAlta(vo.getFechaAlta());
		entity.setIdTipoPropietario(Utils.toInt(vo.getIdTipoPropietario()));
		entity.setIdTipoTelefono(Utils.toInt(vo.getIdTipoTelefono()));
		entity.setTelefono(telefono);
		entity.setPrincipal(vo.getPrincipal());
	}
	
	private void setMML(FormatoLPA entity, FormatoLPAVO mml) {
		entity.setEntretenimiento(mml.getEntretenimiento());
		entity.setObjetivos(mml.getObjetivos());
		entity.setObservaciones(mml.getObservaciones());
	}
	
	private Long getTwoFirst(Long in){
		String inString = in.toString();
		if(inString.length()>=2){
			String out = inString.substring(0,2);
			return Long.valueOf(out);
		}
		return in;
	}
}
