package mx.gob.stps.portal.core.persistencia.facade;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.kiosco.vo.KioscoVO;
import mx.gob.stps.portal.persistencia.entity.Kiosco;

/**
 * Session Bean implementation class KioscoFacade
 */
@Stateless(mappedName = "KioscoFacade")
public class KioscoFacade implements KioscoFacadeLocal {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public KioscoVO find(String username) throws PersistenceException {
		KioscoVO kioscoVo = null;
		String findKioscoByUsuario = "SELECT k FROM Kiosco k WHERE k.usuario LIKE :usuario";
		try{
			Query query = entityManager.createQuery(findKioscoByUsuario);
			query.setParameter("usuario", username);

			Kiosco entity = (Kiosco) query.getSingleResult();

			kioscoVo = getKioscoVO(entity);
			
		}catch (NoResultException re) {
			// No se localizaron registros
			kioscoVo = null;
		} catch (RuntimeException re) {
			re.printStackTrace(); throw new PersistenceException(re);
		}

		return kioscoVo;
	}

	private KioscoVO getKioscoVO(Kiosco entity) {

		KioscoVO kioscoVo = new KioscoVO();
		
		kioscoVo.setCorreoElectronico(entity.getCorreoElectronico());
		kioscoVo.setEstatus(entity.getEstatus()!=null?entity.getEstatus():1);
		kioscoVo.setExtension(entity.getExtension()!=null?entity.getExtension():0);
		kioscoVo.setFechaAlta(entity.getFechaAlta());
		kioscoVo.setFechaBaja(entity.getFechaBaja());
	   	kioscoVo.setIdDomicilio(entity.getIdDomicilio()!=null?entity.getIdDomicilio():0);
	   	kioscoVo.setIdEntidad(entity.getIdEntidad()!=null?entity.getIdEntidad():0);
		kioscoVo.setIdKiosco(entity.getIdKiosco());
		kioscoVo.setIdMotivoEliminacion(Utils.toInt(entity.getIdMotivoEliminacion()));
		kioscoVo.setIdMunicipio(entity.getIdMunicipio()!=null?entity.getIdMunicipio():0);
		kioscoVo.setIdPropiedad(entity.getIdPropiedad()!=null?entity.getIdPropiedad():0);
		kioscoVo.setIdPropioLogo(entity.getIdPropioLogo()!=null?entity.getIdPropioLogo():0);
		kioscoVo.setIdTipoImpresion(entity.getIdTipoImpresion()!=null?entity.getIdTipoImpresion():0);
		kioscoVo.setLada(entity.getLada());
		kioscoVo.setNombre(entity.getNombre());
		kioscoVo.setPassword(entity.getPassword());
		kioscoVo.setResponsable(entity.getResponsable());
		kioscoVo.setTelefono(entity.getTelefono());
		kioscoVo.setUsuario(entity.getUsuario());
		kioscoVo.setVersion(entity.getVersion());
		
		return kioscoVo;

	}

}
