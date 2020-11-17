package mx.gob.stps.portal.core.persistencia.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import mx.gob.stps.portal.core.empresa.vo.EmpresaFPVO;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.persistencia.entity.EmpresaFuncionPublica;

@Stateless
public class EmpresaFuncionPublicaFacade implements EmpresaFuncionPublicaFacadeLocal{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<EmpresaFPVO> getAllRows() throws PersistenceException {
		
		try{
			List<EmpresaFPVO> empresaFPVO = null;
			
			Query query = entityManager.createQuery("select efp from EmpresaFuncionPublica efp");
			
			List<EmpresaFuncionPublica> empresasFuncionPublica = query.getResultList();
			
			if(empresasFuncionPublica != null){
				empresaFPVO = new ArrayList<EmpresaFPVO>();
				for (EmpresaFuncionPublica e : empresasFuncionPublica) 
					empresaFPVO.add(getEmpresaFPVO(e));
			}
				
			return empresaFPVO;
		} catch(Exception e){
			e.printStackTrace();
			throw new PersistenceException();
		}
	}

	private EmpresaFPVO getEmpresaFPVO(EmpresaFuncionPublica entity){
		EmpresaFPVO vo = null;
		if(entity != null){
			vo = new EmpresaFPVO();
			vo.setIdEmpresa(entity.getIdEmpresa());
			vo.setIdEmpresa(entity.getIdEmpresa());
			vo.setIdPortalEmpleo(entity.getIdPortalEmpleo());
		}
		
		return vo;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean esEmpresaFuncionPublica(long idEmpresa) throws PersistenceException {
			try{
				StringBuilder str = new StringBuilder();
				/*str.append(" SELECT e.ID_EMPRESA, e.ID_PORTAL_EMPLEO ");
				str.append(" FROM EMPRESA e LEFT JOIN EMPRESA_FUNCION_PUB efp ON e.ID_EMPRESA = eFP.ID_EMPRESA ");
				str.append(" WHERE efp.ID_EMPRESA = ");*/
				str.append(" SELECT efp.ID_EMPRESA, efp.ID_PORTAL_EMPLEO ");
				str.append(" FROM EMPRESA_FUNCION_PUB efp ");
				str.append(" WHERE efp.ID_EMPRESA = ");
				str.append(idEmpresa);
				Query query = entityManager.createNativeQuery(str.toString());
				
				List<Object[]> empresaFuncionPublica = (List<Object[]>) query.getResultList();
				
				if(empresaFuncionPublica != null && empresaFuncionPublica.size() > 0){
					EmpresaFPVO empresa = new EmpresaFPVO();
					empresa = getConceptosEFP(empresaFuncionPublica);
					if(empresa.getIdEmpresa() > 0)
						return true;
				}
			} catch(Exception e){
				e.printStackTrace();
				throw new PersistenceException();
			}
		return false;
	}
	
	private EmpresaFPVO getConceptosEFP(List<Object[]> rows){
		EmpresaFPVO vo = new EmpresaFPVO();
		
		vo.setIdEmpresa(Utils.toLong(rows.get(0)[0].toString()));
		vo.setIdPortalEmpleo(Utils.toLong((rows.get(0)[1]!=null)?rows.get(0)[0].toString():0));//Hay registros que son NULL
		
		return vo;
	}
}