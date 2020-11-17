package mx.gob.stps.portal.core.persistencia.facade;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.candidate.vo.GradoAcademicoVO;
import mx.gob.stps.portal.core.candidate.vo.IdiomaVO;
import mx.gob.stps.portal.core.candidate.vo.PerfilVO;
import mx.gob.stps.portal.persistencia.vo.BeneficiarioVO;
import mx.gob.stps.portal.persistencia.vo.FormatoSNE01VO;
import mx.gob.stps.portal.core.candidate.vo.HistoriaLaboralVO;
import mx.gob.stps.portal.persistencia.vo.ModalidadCandidatoVO;
import mx.gob.stps.portal.core.candidate.vo.ExpectativaLaboralVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.persistencia.vo.CanalizacionCandidatoVO;
import mx.gob.stps.portal.persistencia.vo.FormatoLPAVO;
import mx.gob.stps.portal.persistencia.vo.FormatoPTATCultivosVO;
import mx.gob.stps.portal.persistencia.vo.FormatoPTATHclinicaVO;
import mx.gob.stps.portal.persistencia.vo.FormatoPTATMaquinariaVO;
import mx.gob.stps.portal.persistencia.vo.FormatoPTATVO;
import mx.gob.stps.portal.persistencia.vo.ModalidadPtatBeneficiarioVO;
import mx.gob.stps.portal.persistencia.vo.ReferenciaLaboralVO;

@Local
public interface ModalidadCandidatoFacadeLocal {

	public int updateCandidateComplementData(ModalidadCandidatoVO mc, FormatoPTATVO ptat, List<BeneficiarioVO> contacts, List<BeneficiarioVO> beneficiaries, List<IdiomaVO> langs, List<FormatoPTATMaquinariaVO> engines, List<FormatoPTATCultivosVO> products, List<FormatoPTATHclinicaVO> histories) throws PersistenceException;
	
	public ModalidadCandidatoVO getModalidadCandidato(long idCandidato, long idModalidad, long idSubprograma) throws PersistenceException;
	
	public FormatoPTATVO getFormatoPTATVO(long idCandidato) throws PersistenceException;
	
	public List<FormatoPTATCultivosVO> getFormatoPTATCultivos(long idCandidato) throws PersistenceException;
	
	public List<ModalidadPtatBeneficiarioVO> getModalidadPtatBeneficiarioList(long idModalidadCandidatoPTAT) throws PersistenceException;
	
	public int updatePerfilComp(PerfilVO perfil, ExpectativaLaboralVO el, HistoriaLaboralVO hl, FormatoSNE01VO sne, ModalidadCandidatoVO mc) throws BusinessException;
	
	public FormatoSNE01VO getFormatoSNE01(long idCandidato) throws PersistenceException;
	
	public int updateBeneficiarioList(List<BeneficiarioVO> beneficiarioList) throws PersistenceException;

	public List<BeneficiarioVO> getBeneficiarioList(long idCandidato, long idTipoFormato, boolean isContact) throws PersistenceException;

	public int removeBeneficiario(long idBeneficiario) throws PersistenceException;

	public int updateLPA(long idCandidato, FormatoLPAVO mml, ReferenciaLaboralVO referencia, GradoAcademicoVO gradoAcademicoVO) throws PersistenceException;

	public FormatoLPAVO getFormatoLPA(long idCandidato) throws PersistenceException;
	
	public List<ReferenciaLaboralVO> getReferenciaLaboraList(long idCandidato) throws PersistenceException;

	public int removeReferenciaLaboral(ReferenciaLaboralVO reference) throws PersistenceException;

	public List<FormatoPTATHclinicaVO> getDiseases(long idCandidato) throws PersistenceException;

	public List<FormatoPTATMaquinariaVO> getFormatoPTATMaquinaria(long idCandidato) throws PersistenceException;
	
	public List<CanalizacionCandidatoVO> getCanalizacionCandidatoList(long idCandidato) throws PersistenceException;
	
	public List<Long> getNominalList(long idOferta) throws PersistenceException;
}