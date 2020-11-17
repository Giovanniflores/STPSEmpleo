package mx.gob.stps.portal.core.persistencia.facade;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import mx.gob.stps.portal.core.infra.utils.Constantes.EVENTO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.infra.vo.BitacoraVO;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorPerfilVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoADesactivarVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoVO;
import mx.gob.stps.portal.persistencia.entity.BitacoraPortal;
import mx.gob.stps.portal.persistencia.entity.HistoricoBusquedaPPC;
import mx.gob.stps.portal.persistencia.entity.MovimientoUsuario;
import mx.gob.stps.portal.persistencia.entity.OfertaCandidato;
import mx.gob.stps.portal.persistencia.entity.OfertaHabilidad;
import mx.gob.stps.portal.persistencia.vo.HistoricoBusquedaPPCVO;
import mx.gob.stps.portal.utils.Catalogos;

import org.apache.log4j.Logger;

/**
 * Concentra los accesos a la persistencia sobre Bitacora
 * 
 *
 */
@Stateless(name="BitacoraFacade", mappedName="BitacoraFacade")
public class BitacoraFacade implements BitacoraFacadeLocal {
	
	private static Logger logger = Logger.getLogger(BitacoraFacade.class);
	
	@PersistenceContext
	private EntityManager entityManager;

	/* (non-Javadoc)
	 * @see mx.gob.stps.portal.core.persistencia.facade.BitacoraFacadeLocal#save(long idEvento, long idUsuario, String descripcion, Calendar fechaEvento, String detalle, long idRegistro, int idTipoPropietario)
	 */
	@Override
	public void save(long idEvento, long idUsuario, String descripcion, Calendar fechaEvento, String detalle, long idRegistro, int idTipoPropietario) {
		try {

			BitacoraPortal entity = getEntity(idEvento, idUsuario, descripcion, fechaEvento, detalle, idRegistro, idTipoPropietario);
			entityManager.persist(entity);
		} catch (Exception e) {
			logger.error(e); // No se envia exception para evitar que interrumpa el proceso principal
		}
	}
	
	@Override
	public void save(EVENTO evento, long idUsuario, String descripcion,String detalle, long idRegistro, TIPO_PROPIETARIO tipoPropietario){
		try{
			Calendar date = Calendar.getInstance();
			BitacoraPortal entity = new BitacoraPortal();
			entity.setIdEvento(evento.getIdEvento());
			entity.setIdUsuario(idUsuario);
			entity.setDescripcion(descripcion);
			entity.setFechaEvento(date);
			entity.setDetalle(detalle);
			entity.setIdRegistro(idRegistro);	
			entity.setIdTipoPropietario(tipoPropietario.getIdTipoPropietario());
			entityManager.persist(entity);			
		} catch (Exception e) {
			logger.error(e); // No se envia exception para evitar que interrumpa el proceso principal
		}		
	}

	private BitacoraPortal getEntity(long idEvento, long idUsuario, String descripcion, Calendar fechaEvento, String detalle, long idRegistro, int idTipoPropietario){
		BitacoraPortal entity = new BitacoraPortal();
		//entity.setIdBitacora(idBitacora);
		entity.setIdEvento(idEvento);
		entity.setIdUsuario(idUsuario);
		entity.setDescripcion(descripcion);
		entity.setFechaEvento(fechaEvento);
		entity.setDetalle(detalle);
		entity.setIdRegistro(idRegistro);	
		entity.setIdTipoPropietario(idTipoPropietario);
		return entity;
	}

	@Override
	public List<BitacoraVO> consultaBitacora(long idRegistro, TIPO_PROPIETARIO tipoPropietario, int numregistros) {
		List<BitacoraVO> movimientos = new ArrayList<BitacoraVO>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM ( ");
		sql.append("SELECT BIT.ID_BITACORA, ");
		sql.append("       BIT.ID_EVENTO, ");
		sql.append("       BIT.ID_USUARIO, ");
		sql.append("       BIT.DESCRIPCION, ");
		sql.append("       BIT.FECHA_EVENTO, ");
		sql.append("       BIT.DETALLE, ");
		sql.append("       BIT.ID_REGISTRO, ");
		sql.append("       BIT.ID_TIPO_PROPIETARIO, ");
		sql.append("       USU.CORREO_ELECTRONICO, ");
		sql.append("       USU.NOMBRE, ");
		sql.append("       USU.APELLIDO1, ");
		sql.append("       USU.APELLIDO2 ");
		sql.append("  FROM BITACORA BIT, ");
		sql.append("       USUARIO USU ");
		sql.append(" WHERE BIT.ID_REGISTRO = ?1 ");
		sql.append("   AND BIT.ID_TIPO_PROPIETARIO = ?2 ");
		sql.append("   AND USU.ID_USUARIO(+) = BIT.ID_USUARIO ");
		sql.append(" ORDER BY BIT.ID_BITACORA DESC ");
		sql.append(") WHERE ROWNUM <= ?3 ");

		try{
			Query query = entityManager.createNativeQuery(sql.toString());
			query.setParameter(1, idRegistro);
			query.setParameter(2, tipoPropietario.getIdTipoPropietario());
			query.setParameter(3, numregistros);

			@SuppressWarnings("unchecked")
			List<Object[]> rowSet = query.getResultList();

			for (Object[] result : rowSet){
				BitacoraVO bit = createBitacoraVO(result);
				movimientos.add(bit);
			}	
		}catch(Exception e){
			e.printStackTrace(); logger.error(e);
		}
		
		return movimientos;
	}

	/*public Map <Long, List<BitacoraVO>> consultaBitacora(long[] idsEmpresa, TIPO_PROPIETARIO tipoPropietario, int numregistros) {
		Map <Long, List<BitacoraVO>> mapempresas = new HashMap<Long, List<BitacoraVO>>();

		List<BitacoraVO> movimientos = new ArrayList<BitacoraVO>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM ( ");
		sql.append("SELECT BIT.ID_BITACORA, ");
		sql.append("       BIT.ID_EVENTO, ");
		sql.append("       BIT.ID_USUARIO, ");
		sql.append("       BIT.DESCRIPCION, ");
		sql.append("       BIT.FECHA_EVENTO, ");
		sql.append("       BIT.DETALLE, ");
		sql.append("       BIT.ID_REGISTRO, ");
		sql.append("       BIT.ID_TIPO_PROPIETARIO, ");
		sql.append("       USU.CORREO_ELECTRONICO, ");
		sql.append("       USU.NOMBRE, ");
		sql.append("       USU.APELLIDO1, ");
		sql.append("       USU.APELLIDO2 ");
		sql.append("  FROM BITACORA BIT, ");
		sql.append("       USUARIO USU ");
		sql.append(" WHERE BIT.ID_REGISTRO = ?1 ");
		sql.append("   AND BIT.ID_TIPO_PROPIETARIO = ?2 ");
		sql.append("   AND USU.ID_USUARIO(+) = BIT.ID_USUARIO ");
		sql.append(" ORDER BY BIT.ID_BITACORA DESC ");
		sql.append(") WHERE ROWNUM <= ?3 ");

		try{
			Query query = entityManager.createNativeQuery(sql.toString());
			query.setParameter(1, idRegistro);
			query.setParameter(2, tipoPropietario.getIdTipoPropietario());
			query.setParameter(3, numregistros);

			@SuppressWarnings("unchecked")
			List<Object[]> rowSet = query.getResultList();

			for (Object[] result : rowSet){
				BitacoraVO bit = createBitacoraVO(result);
				movimientos.add(bit);
			}	
		}catch(Exception e){
			e.printStackTrace(); logger.error(e);
		}
		
		return mapempresas;
	}*/
	
	private BitacoraVO createBitacoraVO(Object[] rowSet) {
		BitacoraVO vo = new BitacoraVO();

		vo.setIdBitacora        (Utils.toLong     (rowSet[0]));
		vo.setIdEvento          (Utils.toLong     (rowSet[1]));
		vo.setIdUsuario         (Utils.toLong     (rowSet[2]));
		vo.setDescripcion       (Utils.toString   (rowSet[3]));
		vo.setFechaEvento       (Utils.toCalendar (rowSet[4]));
		vo.setDetalle           (Utils.toString   (rowSet[5]));
		vo.setIdRegistro        (Utils.toLong     (rowSet[6]));
		vo.setIdTipoPropietario (Utils.toLong     (rowSet[7]));
		vo.setCorreoElectronico (Utils.toString   (rowSet[8]));
		vo.setNombre            (Utils.toString   (rowSet[9]));
		vo.setApellido1         (Utils.toString   (rowSet[10]));
		vo.setApellido2         (Utils.toString   (rowSet[11]));

		return vo;
	}

	@Override
	public int registraMovimiento(EVENTO evento, Date fechaEvento, long idUsuario, long idPerfil) {
		int id= 0;
		try {
			MovimientoUsuario entity = new MovimientoUsuario();
			
			entity.setIdEvento(evento.getIdEvento());
			entity.setFechaEvento(new Timestamp(fechaEvento.getTime()));
			entity.setIdUsuario(idUsuario);
			entity.setIdPerfil(idPerfil);

			entityManager.persist(entity);	
			id = (int) entity.getIdMovimiento();


		} catch (Exception e) {
			logger.error(e); // No se envia exception para evitar que interrumpa el proceso principal
		}
		return id;
	}
	
	
	@Override
	public void registraHistoricoPPC(long idCandidato, long idMovimiento, List<OfertaPorPerfilVO> ofertas, boolean postulado) {
		Long idMotivo = null;
		String motivoDescripcion = null;
		if (postulado){
			idMotivo = (long)Catalogos.MOTIVOS_NO_POSTULACION_OFERTA.NO_OBLIGADO.getIdOpcion();
			motivoDescripcion = Catalogos.MOTIVOS_NO_POSTULACION_OFERTA.NO_OBLIGADO.getOpcion();
		}
		try {			
			for(OfertaPorPerfilVO ofertasPorPerfilVO:ofertas){
				HistoricoBusquedaPPC entity = new HistoricoBusquedaPPC();
				
				entity.setIdCandidato(idCandidato);
				entity.setIdOfertaEmpleo(ofertasPorPerfilVO.getIdOfertaEmpleo());
				entity.setCompatibilidad((long) ofertasPorPerfilVO.getCompatibilidad());
				entity.setIdMovimiento(idMovimiento);
				entity.setIdMotivo(idMotivo);
				entity.setMotivoDesc(motivoDescripcion);

				entityManager.persist(entity);		
			}

		} catch (Exception e) {
			logger.error(e); // No se envia exception para evitar que interrumpa el proceso principal
		}
	}
	
	@Override
	public boolean consultaHistoricoPPC(long idMovimiento) {

		try{
			Query query = entityManager.createQuery("SELECT c.IdMotivo FROM HistoricoBusquedaPPC c WHERE c.IdMovimiento=:IdMovimiento");
			query.setParameter("IdMovimiento", idMovimiento);

			List<Long> list = (List<Long>)query.getResultList();
			
			if (list!=null){
					Long idMotivo = list.get(0);
					if (idMotivo==null) return true;
			}
		} catch(Exception e){
			e.printStackTrace();
			logger.error(e);
		}

		return false;
	}
	
	
	@Override
	public void actualizaHistoricoPPC(long idMovimiento) {
		Long idMotivo = (long)Catalogos.MOTIVOS_NO_POSTULACION_OFERTA.NO_OBLIGADO.getIdOpcion();
		String motivoDescripcion = Catalogos.MOTIVOS_NO_POSTULACION_OFERTA.NO_OBLIGADO.getOpcion();
		
		String update = "UPDATE HistoricoBusquedaPPC AS hb "
				+ "SET hb.IdMotivo = :idMotivo, hb.motivoDesc = :motivoDesc "
				+ "WHERE hb.IdMovimiento = :idMovimiento ";
		try {
			Query query = entityManager.createQuery(update);
			query.setParameter("idMotivo", idMotivo);
			query.setParameter("motivoDesc", motivoDescripcion);			
			query.setParameter("idMovimiento", idMovimiento);
			query.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
	}
	
}