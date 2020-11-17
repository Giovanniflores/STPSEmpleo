package mx.gob.stps.portal.core.persistencia.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.empresa.vo.EmpresaPortalSisneVO;
import mx.gob.stps.portal.persistencia.entity.EmpresasPortalSisne;

import org.apache.log4j.Logger;

@Stateless
public class EmpresasPortalSisneFacade implements EmpresasPortalSisneFacadeLocal {
	
	Logger logger = Logger.getLogger(EmpresasPortalSisneFacade.class);

	@PersistenceContext
	private EntityManager entityManager;

	public EmpresaPortalSisneVO findById(long idEmpresa) throws PersistenceException {

			EmpresaPortalSisneVO vo = null;
			
			try {
				EmpresasPortalSisne entity = entityManager.find(EmpresasPortalSisne.class, idEmpresa);
				if (entity != null) {
					vo = getEmpresaPortalSisneVO(entity);
				}
	
			} catch (NoResultException re) {
				logger.error("Registro de Empresa no localizado en EMPRESAS_PORTAL_SISNE, idOfertaEmpleo : " + idEmpresa);
			} catch (RuntimeException re) {
				throw new PersistenceException(re);
			}
			return vo;
	}
	
	private EmpresaPortalSisneVO getEmpresaPortalSisneVO(EmpresasPortalSisne entity){
		
		EmpresaPortalSisneVO vo = new EmpresaPortalSisneVO();
		
		if (entity != null){						
			vo.setIdEmpresa(entity.getIdEmpresa());
			vo.setIdEmpresaSisne(entity.getIdEmpresaSisne());			
			if (entity.getFechaAlta() != null)
				vo.setFechaAlta(entity.getFechaAlta());			
		}
		return vo;
	}
	
}
