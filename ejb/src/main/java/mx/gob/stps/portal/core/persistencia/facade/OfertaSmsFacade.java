package mx.gob.stps.portal.core.persistencia.facade;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.RECIBE_OFERTA;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_TELEFONO;
import mx.gob.stps.portal.core.oferta.envioSMS.vo.OfertasSMSVO;
import mx.gob.stps.portal.persistencia.entity.OfertaSms;

import org.apache.log4j.Logger;

/**
 * Session Bean implementation class OfertaSmsFacade
 */
@Stateless(mappedName = "OfertaSmsFacade")
public class OfertaSmsFacade implements OfertaSmsFacadeLocal {

	private static Logger logger = Logger.getLogger(OfertaEmpleoFacade.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public boolean existeOfertaSms(long idCandidato,long idOfertaEmpleo) {
		boolean flag = false;
		String ofertaSmsCandidatoOferta = "SELECT o FROM OfertaSms o WHERE o.idCandidato =:idCandidato AND o.idOfertaEmpleo =:idOfertaEmpleo";
		try{
			Query query = entityManager.createQuery(ofertaSmsCandidatoOferta);
			query.setParameter("idCandidato", idCandidato);
			query.setParameter("idOfertaEmpleo", idOfertaEmpleo);
			OfertaSms entity = (OfertaSms) query.getSingleResult();

			if(null!=entity)
				flag = true;

		} catch(NoResultException e){
			logger.error("Oferta SMS no localizada idCandidato "+ idCandidato +", idOfertaEmpleo "+ idOfertaEmpleo);
		} catch (RuntimeException re) {
			logger.error(re.toString());
		}
		
		return flag;
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void save(OfertasSMSVO sms) {
		try{
			OfertaSms entity = getEntity(sms);
			entityManager.persist(entity);
		}catch (RuntimeException re) {
			logger.error(re.toString());
		}
	}

	private OfertaSms getEntity(OfertasSMSVO sms) {
		OfertaSms entity = new OfertaSms();
		entity.setIdCandidato(Utils.toLong(sms.getIdCandidato()));
		entity.setIdOfertaEmpleo(Utils.toLong(sms.getIdOfertaEmpleo()));
		entity.setFechaEnvio(new Date());
		return entity;
	}



	private String getQueryFiltraCandidatosSMS() {

		StringBuilder query = new StringBuilder();
		query.append("SELECT CANDIDATO.ID_CANDIDATO, ID_TELEFONO, CLAVE, TELEFONO, ");
		query.append("       DOM.ID_ENTIDAD, F_DESC_CATALOGO(25, DOM.ID_ENTIDAD) AS ENTIDAD ");
		query.append("  FROM USUARIO USU, ");
		query.append("       CANDIDATO, ");
		query.append("       PERFIL_LABORAL, ");
		query.append("       TELEFONO, ");
		query.append("       DOMICILIO DOM ");
		query.append(" WHERE CANDIDATO.ESTATUS = ? ");
		query.append("   AND CANDIDATO.ID_CANDIDATO = PERFIL_LABORAL.ID_CANDIDATO ");
		query.append("   AND PERFIL_LABORAL.ID_RECIBE_OFERTA IN (?,?) ");
		query.append("   AND CANDIDATO.ID_CANDIDATO = TELEFONO.ID_PROPIETARIO ");
		query.append("   AND TELEFONO.ID_TIPO_PROPIETARIO = ? ");
		query.append("   AND TELEFONO.ID_TIPO_TELEFONO = ? ");
		query.append("   AND CANDIDATO.ID_CANDIDATO > ? ");
		query.append("   AND CANDIDATO.ID_CANDIDATO NOT IN(SELECT ID_CANDIDATO FROM OFERTA_SMS WHERE FECHA_ENVIO = TO_CHAR(SYSDATE,'DD/MM/YYYY')) ");
		query.append("   AND DOM.ID_PROPIETARIO = CANDIDATO.ID_CANDIDATO ");
		query.append("   AND DOM.ID_TIPO_PROPIETARIO = ? ");
		query.append("   AND USU.ID_USUARIO = CANDIDATO.ID_USUARIO ");
		query.append("   AND LENGTH(USU.CONTRASENA) > 8 "); // solo los candidato con contraseña mayor a 8 se consideran como Activos dentro del Portal
		query.append("   AND FECHA_CONFIRMA IS NOT NULL ");
		query.append("   AND ROWNUM <= ? ");
		query.append(" ORDER BY CANDIDATO.ID_CANDIDATO ASC ");

		return query.toString();
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Object[]> getListaCandidatos(int idCandidato, int maxRegistros) {
		//List<OfertasSMSVO> candidatos = new ArrayList<OfertasSMSVO>();
		int index = 0;
		Query query = entityManager.createNativeQuery(getQueryFiltraCandidatosSMS());
		query.setParameter(++index, ESTATUS.ACTIVO.getIdOpcion());
		query.setParameter(++index, RECIBE_OFERTA.TELEFONO.getIdRecibeOferta());
		query.setParameter(++index, RECIBE_OFERTA.AMBOS.getIdRecibeOferta());
		query.setParameter(++index, TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario()); 
		query.setParameter(++index, TIPO_TELEFONO.CELULAR.getIdOpcion());
		query.setParameter(++index, idCandidato);
		query.setParameter(++index, TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
		query.setParameter(++index, maxRegistros);

		@SuppressWarnings("unchecked")
		List<Object[]> rowSet = query.getResultList();

		/*for (Object[] rs : rowSet) {
			OfertasSMSVO vo = new OfertasSMSVO();
			vo.setIdCandidato (Utils.toInt(rs[0]));
			vo.setIdTelefono  (Utils.toInt(rs[1]));
			
			candidatos.add(vo);
		}*/

		return rowSet;
	}

}
