package mx.gob.stps.portal.core.persistencia.facade;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import mx.gob.stps.portal.core.infra.vo.BitacoraSiisneVO;
import mx.gob.stps.portal.persistencia.entity.BitacoraSiisne;

@Stateless
public class BitacoraSiisneFacade implements BitacoraSiisneFacadeLocal {

	private static Logger logger = Logger.getLogger(BitacoraSiisneFacade.class);
	
	@PersistenceContext
	private EntityManager entityManager;	
	
	@Override
	public void save(BitacoraSiisneVO bitacoraSiisneVo) {
		
		try{
			BitacoraSiisne entity = getEntity(bitacoraSiisneVo.getIdUsuario(), bitacoraSiisneVo.getIdOperacion(),
					bitacoraSiisneVo.getIdReferencia(), bitacoraSiisneVo.getFuente(), bitacoraSiisneVo.getInfo(),
					bitacoraSiisneVo.getFechaOperacion());
			entityManager.persist(entity);
			
		} catch (Exception e) {
			logger.error(e); // No se envia exception para evitar que interrumpa el proceso principal
		}				
	}
	
	private BitacoraSiisne getEntity(long idUsuario, long idOperacion, long idReferencia, long fuente, String info, Date fechaOperacion){
		BitacoraSiisne entity = new BitacoraSiisne();
		//entity.setIdBitacora(idBitacora);
		entity.setIdOperacion(idOperacion);
		entity.setIdUsuario(idUsuario);
		entity.setFechaOperacion(fechaOperacion);
		entity.setIdReferencia(idReferencia);
		entity.setInfo(info);
		entity.setFuente(fuente);
		return entity;		
	}
	
}