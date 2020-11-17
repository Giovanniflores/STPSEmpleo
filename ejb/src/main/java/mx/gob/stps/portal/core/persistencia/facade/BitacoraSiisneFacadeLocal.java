package mx.gob.stps.portal.core.persistencia.facade;

import javax.ejb.Local;

import mx.gob.stps.portal.core.infra.vo.BitacoraSiisneVO;


@Local
public interface BitacoraSiisneFacadeLocal {

	public void save(BitacoraSiisneVO bitacoraSiisneVo);
	
}