package mx.gob.stps.portal.core.kiosco.service;
import javax.ejb.Remote;

import mx.gob.stps.portal.core.kiosco.vo.KioscoRegistroVO;
import mx.gob.stps.portal.core.kiosco.vo.KioscoVO;

@Remote
public interface KioscoAppServiceRemote {

	KioscoVO consultaKiosco(String username);

	void registraCandidatoKiosco(KioscoRegistroVO vo, long idCandidato);

	void registraUsuarioKiosco(KioscoRegistroVO vo);

}
