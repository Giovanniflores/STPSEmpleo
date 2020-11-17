package mx.gob.stps.portal.core.persistencia.facade;

import static mx.gob.stps.portal.core.infra.utils.Constantes.NUM_MAX_OFERTAS_RECIENTES;
import static mx.gob.stps.portal.core.infra.utils.Constantes.NUMERO_OFERTAS_RECIENTES;
import static mx.gob.stps.portal.core.infra.utils.Constantes.NUM_ULTIMAS_POSTULACIONES;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import mx.gob.stps.portal.core.candidate.vo.NotificacionCandidatoVO;
import mx.gob.stps.portal.core.candidate.vo.OfertaPostulacionVO;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoADesactivarVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoVO;
import mx.gob.stps.portal.core.oferta.vo.MiOfertaRecienteVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.persistencia.entity.NotificacionCandidato;
import mx.gob.stps.portal.persistencia.entity.OfertaCandidato;
import mx.gob.stps.portal.persistencia.vo.OfertaCandidatoOcupacionDTO;
import mx.gob.stps.portal.persistencia.vo.OfertaEmpresaAdminDTO;
import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.utils.Catalogos.MOTIVOS_RECHAZO_SEGUIMIENTO;

import org.apache.log4j.Logger;

@Stateless
public class OfertaCandidatoFacade implements OfertaCandidatoFacadeLocal {

	int MIN_SEQUENCE = 1;
	private static Logger logger = Logger
			.getLogger(OfertaCandidatoFacade.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public int create(OfertaCandidatoVO vo) throws PersistenceException {
		int result = -1;
		try {
			OfertaCandidato entity = getEntity(vo);
			entityManager.persist(entity);
			vo.setIdOfertaCandidato(entity.getIdOfertaCandidato());
			result = 1;
		} catch (RuntimeException re) {
			logger.error(re.toString());
			throw new PersistenceException(re);
		}
		return result;
	}

	@Override
	public OfertaCandidatoVO findById(long idOfertaCandidato)
			throws PersistenceException {
		OfertaCandidatoVO vo = null;
		try {
			OfertaCandidato entity = entityManager.find(OfertaCandidato.class,
					idOfertaCandidato);
			if (null != entity)
				vo = getOfertaCandidatoVO(entity);
		} catch (RuntimeException re) {
			logger.error(re.toString());
			throw new PersistenceException(re);
		}
		return vo;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void update(OfertaCandidatoVO vo) throws PersistenceException {
		try {
			OfertaCandidato entity = entityManager.find(OfertaCandidato.class, vo.getIdOfertaCandidato());
			entity.setEstatus(vo.getEstatus());
			entity.setFechaAlta(vo.getFechaAlta());
			entity.setIdCandidato(vo.getIdCandidato());
			entity.setIdOfertaEmpleo(vo.getIdOfertaEmpleo());
			if (vo.getIdMotivo() > 0)
				entity.setIdMotivo(vo.getIdMotivo());
			if (null != vo.getFechaSeguimiento())
				entity.setFechaSeguimiento(vo.getFechaSeguimiento());
			if (null != vo.getFechaColocacion())
				entity.setFechaColocacion(vo.getFechaColocacion());
			if (null != vo.getFechaInicioContratacion())
				entity.setFechaInicioContratacion(vo.getFechaInicioContratacion());
			if (null != vo.getMotivoDesc())
				entity.setMotivoDesc(vo.getMotivoDesc());
			if (null != vo.getFechaModificacion())
				entity.setFechaModificacion(vo.getFechaModificacion());
			if (null != vo.getFechaContacto())
				entity.setFechaContacto(vo.getFechaContacto());
			if (null != vo.getConseguioEntrevista())
				entity.setConsiguioEntrevista(vo.getConseguioEntrevista());
			if (null != vo.getFechaEntrevista())
				entity.setFechaEntrevista(vo.getFechaEntrevista());
			if (null != vo.getMotivoNoEntrevista() && vo.getMotivoNoEntrevista() > 0)
				entity.setMotivoNoEntrevista(vo.getMotivoNoEntrevista());
			if (null != vo.getFuisteContratado())
				entity.setFuisteContratado(vo.getFuisteContratado());
			if (null != vo.getIdOficinaSeguimiento())
				entity.setIdOficinaSeguimiento(vo.getIdOficinaSeguimiento());
			if (null != vo.getIdUsuarioSeguimiento())
				entity.setIdUsuarioSeguimiento(vo.getIdUsuarioSeguimiento());
			if (null != vo.getIdFuenteSeguimiento())
				entity.setIdFuenteSeguimiento(vo.getIdFuenteSeguimiento());
			// No se contempla la actualizacion de la compatibilidad
			entityManager.merge(entity);
		} catch (RuntimeException re) {
			logger.error(re.toString());
			throw new PersistenceException(re);
		}
	}

	@Override
	public void remove(long idOfertaCandidato) throws PersistenceException {
		try {
			OfertaCandidato entity = entityManager.find(OfertaCandidato.class,
					idOfertaCandidato);
			entityManager.remove(entity);
		} catch (RuntimeException re) {
			logger.error(re.toString());
			throw new PersistenceException(re);
		}
	}

	private OfertaCandidato getEntity(OfertaCandidatoVO vo) {
		OfertaCandidato detail = new OfertaCandidato();
		detail.setIdUsuario(vo.getIdUsuario());
		detail.setIdOficina(vo.getIdOficina());
		detail.setIdFuente(vo.getIdFuente());
		detail.setIdOfertaEmpleo(vo.getIdOfertaEmpleo());
		//detail.setIdVinculado(vo.getIdVinculado());
		detail.setPostulacionCartera(vo.getPostulacionCartera());
		detail.setIdCandidato(vo.getIdCandidato());
		detail.setFechaAlta(vo.getFechaAlta());
		detail.setEstatus(vo.getEstatus());
		detail.setCompatibilidad(Utils.toLong(vo.getCompatibilidad()));

		if (vo.getIdMotivo() != 0)
			detail.setIdMotivo(vo.getIdMotivo());
		if (vo.getIdOfertaCandidato() != 0)
			detail.setIdOfertaCandidato(vo.getIdOfertaCandidato());
		detail.setFechaModificacion(vo.getFechaModificacion());
		return detail;
	}

	private OfertaCandidatoVO getOfertaCandidatoVO(OfertaCandidato entity) {

		if (null == entity || entity.getIdOfertaEmpleo() < MIN_SEQUENCE)
			return null;

		OfertaCandidatoVO vo = new OfertaCandidatoVO();
		vo.setEstatus(entity.getEstatus());
		vo.setFechaAlta(entity.getFechaAlta());
		vo.setIdCandidato(entity.getIdCandidato());
		vo.setIdOfertaEmpleo(entity.getIdOfertaEmpleo());
		vo.setIdOfertaCandidato(entity.getIdOfertaCandidato());
		if (entity.getIdMotivo() != null)
			vo.setIdMotivo(entity.getIdMotivo());
		if (entity.getFechaContacto() != null)
			vo.setFechaContacto(entity.getFechaContacto());
		if (entity.getConsiguioEntrevista() != null)
			vo.setConseguioEntrevista(entity.getConsiguioEntrevista());
		if (entity.getMotivoNoEntrevista() != null)
			vo.setMotivoNoEntrevista(entity.getMotivoNoEntrevista());
		if (entity.getFechaEntrevista() != null)
			vo.setFechaEntrevista(entity.getFechaEntrevista());
		if (entity.getFuisteContratado() != null)
			vo.setFuisteContratado(entity.getFuisteContratado());
		if (entity.getMotivoDesc() != null)
			vo.setMotivoDesc(entity.getMotivoDesc());
		if (entity.getFechaColocacion() != null)
			vo.setFechaColocacion(entity.getFechaColocacion());
		if (entity.getCompatibilidad() != null)
			vo.setCompatibilidad(Utils.toInt(entity.getCompatibilidad()));
		if (entity.getFechaInicioContratacion() != null)
			vo.setFechaInicioContratacion(entity.getFechaInicioContratacion());
		return vo;
	}

	// convierte un registro obtenido como query nativa de la tabla
	// OFERTA_CANDIDATO en un objeto de tipo OfertaCandidatoVO
	/*
	 * private OfertaCandidatoVO getOfertaCandidatoVO(Object[]
	 * objectOfertaCandidato) { OfertaCandidatoVO vo = new OfertaCandidatoVO();
	 * vo.setIdOfertaEmpleo(Utils.toLong(objectOfertaCandidato[0]));
	 * vo.setIdCandidato(Utils.toLong(objectOfertaCandidato[1]));
	 * vo.setFechaAlta(Utils.toDate(objectOfertaCandidato[2]));
	 * vo.setEstatus(Utils.toInt(objectOfertaCandidato[3]));
	 * vo.setIdMotivo(Utils.toInt(objectOfertaCandidato[4]));
	 * vo.setIdOfertaCandidato(Utils.toLong(objectOfertaCandidato[5])); return
	 * vo; }
	 */

	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<OfertaCandidatoVO> misOfertas(long idCandidato)
			throws PersistenceException {
		String findOffersByCandidate = "SELECT c FROM OfertaCandidato c WHERE c.idCandidato=:idCandidato AND (c.estatus=4 OR c.estatus=5)";
		List<OfertaCandidatoVO> list = new ArrayList<OfertaCandidatoVO>();
		Query query = entityManager.createQuery(findOffersByCandidate);
		query.setParameter("idCandidato", idCandidato);
		@SuppressWarnings("unchecked")
		List<Object> result = query.getResultList();
		for (Object resultElement : result) {
			OfertaCandidatoVO vo = getOfertaCandidatoVO((OfertaCandidato) resultElement);
			if (null != vo)
				list.add(vo);
		}
		return list;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<OfertaCandidatoVO> misOfertasRelacionadas(long idCandidato)
			throws PersistenceException {
		String findLinkedOffersByCandidate = "SELECT c FROM OfertaCandidato c WHERE c.idCandidato=:idCandidato AND (c.estatus=4 OR c.estatus=5 OR c.estatus=16  OR c.estatus=20)";
		List<OfertaCandidatoVO> list = new ArrayList<OfertaCandidatoVO>();
		Query query = entityManager.createQuery(findLinkedOffersByCandidate);
		query.setParameter("idCandidato", idCandidato);
		@SuppressWarnings("unchecked")
		List<Object> result = query.getResultList();
		for (Object resultElement : result) {
			OfertaCandidatoVO vo = getOfertaCandidatoVO((OfertaCandidato) resultElement);
			if (null != vo)
				list.add(vo);
		}
		return list;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<OfertaCandidatoVO> empresasMeBuscan(long idCandidato)
			throws PersistenceException {
		String offersByCompanyFindMe = "SELECT c FROM OfertaCandidato c WHERE c.idCandidato=:idCandidato AND c.estatus=16";
		List<OfertaCandidatoVO> list = new ArrayList<OfertaCandidatoVO>();
		Query query = entityManager.createQuery(offersByCompanyFindMe);
		query.setParameter("idCandidato", idCandidato);
		@SuppressWarnings("unchecked")
		List<Object> result = query.getResultList();
		for (Object resultElement : result) {
			OfertaCandidatoVO vo = getOfertaCandidatoVO((OfertaCandidato) resultElement);
			if (null != vo && !contains(list, vo))
				list.add(vo);
		}
		return list;
	}

	@Override
	public List<OfertaCandidatoVO> findByOfferCandidate(long idOfertaEmpleo,
			long idCandidato) throws PersistenceException {
		List<OfertaCandidatoVO> list = new ArrayList<OfertaCandidatoVO>();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT c FROM OfertaCandidato c WHERE c.idOfertaEmpleo=:idOfertaEmpleo AND c.idCandidato=:idCandidato");
		Query query = entityManager.createQuery(sb.toString());
		query.setParameter("idOfertaEmpleo", idOfertaEmpleo);
		query.setParameter("idCandidato", idCandidato);
		@SuppressWarnings("unchecked")
		List<Object> result = query.getResultList();
		for (Object resultElement : result) {
			OfertaCandidatoVO vo = getOfertaCandidatoVO((OfertaCandidato) resultElement);
			list.add(vo);
		}
		return list;
	}

	private boolean contains(List<OfertaCandidatoVO> list, OfertaCandidatoVO vo) {
		for (OfertaCandidatoVO ofertaCandidato : list) {
			if (ofertaCandidato.getIdOfertaEmpleo() == vo.getIdOfertaEmpleo())
				return true;
		}
		return false;
	}

	public List<OfertaPostulacionVO> obtieneCandidatosOfertaDeEmpresa(
			long idEmpresa, int estatusOfertaEmpleo1, int estatusOfertaEmpleo2,
			int estatusOfertaCandidato1, int estatusOfertaCandidato2)
			throws PersistenceException {

		List<OfertaPostulacionVO> listaCandidatosOferta = new ArrayList<OfertaPostulacionVO>();

		StringBuffer sqlString = new StringBuffer();

		try {
			sqlString
					.append("SELECT OC.ID_OFERTA_CANDIDATO, OE.ID_EMPRESA, OC.ID_OFERTA_EMPLEO, OE.TITULO_OFERTA, OC.ID_CANDIDATO, ");
			sqlString
					.append("TRIM(S.NOMBRE)||' '|| TRIM(S.APELLIDO1)||DECODE(TRIM(S.APELLIDO2), NULL, NULL, ' '||TRIM(S.APELLIDO2)) NOMBRE_CANDIDATO, ");
			sqlString.append("OC.FECHA_ALTA, ");
			sqlString
					.append("TRUNC(SYSDATE)- OC.FECHA_ALTA AS ANTIGUEDAD_DIAS, ");
			sqlString.append("OC.ESTATUS ");
			sqlString
					.append("FROM OFERTA_CANDIDATO OC, OFERTA_EMPLEO OE, CANDIDATO C, SOLICITANTE S ");
			sqlString
					.append("WHERE OC.ID_OFERTA_EMPLEO = OE.ID_OFERTA_EMPLEO ");
			sqlString.append("AND OC.ID_CANDIDATO = C.ID_CANDIDATO ");
			sqlString.append("AND C.ID_CANDIDATO = S.ID_CANDIDATO ");
			sqlString.append("AND (OE.ESTATUS = ?1 OR OE.ESTATUS = ?2) ");
			sqlString.append("AND (OC.ESTATUS = ?3 OR OC.ESTATUS = ?4) ");
			sqlString.append("AND OE.ID_EMPRESA = ?5 ");
			sqlString.append("AND ROWNUM <= ?6 ");
			sqlString
					.append("ORDER BY OE.ESTATUS, OC.ESTATUS, OC.FECHA_ALTA DESC ");

			Query query = entityManager.createNativeQuery(sqlString.toString());
			query.setParameter(1, String.valueOf(estatusOfertaEmpleo1));
			query.setParameter(2, String.valueOf(estatusOfertaEmpleo2));
			query.setParameter(3, String.valueOf(estatusOfertaCandidato1));
			query.setParameter(4, String.valueOf(estatusOfertaCandidato2));
			query.setParameter(5, String.valueOf(idEmpresa));
			query.setParameter(6, NUM_ULTIMAS_POSTULACIONES);

			@SuppressWarnings("unchecked")
			List<Object[]> rows = query.getResultList();

			for (Object[] rowElement : rows) {
				OfertaPostulacionVO vo = getOfertaPostulacioVO(rowElement);
				listaCandidatosOferta.add(vo);
			}

		} catch (RuntimeException re) {
			logger.error("Error en OfertaCandidatoFacade.obtieneCandidatosOfertaDeEmpresa");
			re.printStackTrace();
			throw new PersistenceException(re);
		}

		return listaCandidatosOferta;
	}

	// obtiene una lista de los candidatos con estatus de postulantes ordenados
	// por fecha de alta descendente
	public List<OfertaPostulacionVO> obtienePostulantesDeEmpresa(long idEmpresa) {
		List<OfertaPostulacionVO> listaPostulantes = new ArrayList<OfertaPostulacionVO>();

		int estatusOfertaEmpleo1 = ESTATUS.ACTIVO.getIdOpcion();
		int estatusOfertaEmpleo2 = ESTATUS.CANCELADA.getIdOpcion();
		// sólo queremos seleccionar los candidatos con estatus "postulado"
		int estatusOfertaCandidato1 = ESTATUS.POSTULADO.getIdOpcion();
		int estatusOfertaCandidato2 = ESTATUS.POSTULADO.getIdOpcion();

		listaPostulantes = obtieneCandidatosOfertaDeEmpresa(idEmpresa,
				estatusOfertaEmpleo1, estatusOfertaEmpleo2,
				estatusOfertaCandidato1, estatusOfertaCandidato2);
		return listaPostulantes;
	}

	private OfertaPostulacionVO getOfertaPostulacioVO(Object[] row) {

		OfertaPostulacionVO vo = new OfertaPostulacionVO();

		vo.setIdOfertaCandidato(Utils.toInt(row[0]));
		vo.setIdEmpresa(Utils.toInt(row[1]));
		vo.setIdOfertaEmpleo(Utils.toInt(row[2]));
		vo.setTituloOferta(Utils.toString(row[3]));
		vo.setIdCandidato(Utils.toInt(row[4]));
		vo.setNombreCompletoCandidato(Utils.toString(row[5]));
		vo.setFechaAlta(Utils.toDate(row[6]));
		vo.setAntiguedadDias(Utils.toInt(row[7]));
		vo.setEstatus(Utils.toInt(row[8]));

		return vo;
	}

	public List<MiOfertaRecienteVO> obtenerMisOfertasRecientes(long idEmpresa) {
		List<MiOfertaRecienteVO> ofertas = new ArrayList<MiOfertaRecienteVO>();

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ( ");

		sb.append("	SELECT id_oferta_empleo, titulo_oferta ");
		sb.append("	  FROM oferta_empleo ");
		sb.append("	 WHERE id_empresa = ?1 AND estatus = ?2 ");
		sb.append("	 ORDER BY fecha_modificacion DESC ");

		sb.append(") WHERE ROWNUM <= ?3 ");

		Query query = entityManager.createNativeQuery(sb.toString());
		query.setParameter(1, idEmpresa);
		query.setParameter(2, ESTATUS.ACTIVO.getIdOpcion());
		query.setParameter(3, NUM_MAX_OFERTAS_RECIENTES);

		@SuppressWarnings("unchecked")
		List<Object[]> rows = query.getResultList();
		if (rows != null) {
			for (Object[] row : rows) {
				MiOfertaRecienteVO oferta = new MiOfertaRecienteVO(
						Utils.toLong(row[0]), Utils.toString(row[1]));
				ofertas.add(oferta);
			}
		}

		return ofertas;
	}

	public List<OfertaCandidatoADesactivarVO> consultaOfertasRelacionadas(
			long idCandidato) {
		List<OfertaCandidatoADesactivarVO> ofertas = new ArrayList<OfertaCandidatoADesactivarVO>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ID_OFERTA_CANDIDATO, OE.ID_OFERTA_EMPLEO as id_oferta_empleo, OC.ESTATUS as estatus, ");
		sql.append(" OE.TITULO_OFERTA as titulo, OE.NOMBRE_EMPRESA as nombre, OE.CORREO_ELECTRONICO_CONTACTO as correo ");
		sql.append(" FROM OFERTA_CANDIDATO OC,  OFERTA_EMPLEO OE ");
		sql.append(" WHERE OC.ID_CANDIDATO = ? AND OC.ID_OFERTA_EMPLEO = OE.ID_OFERTA_EMPLEO ");
		sql.append(" AND OC.ESTATUS!=6 and OE.estatus=1 ");
		sql.append(" ORDER BY NOMBRE_EMPRESA ASC");
		Query query = entityManager.createNativeQuery(sql.toString());
		query.setParameter(1, idCandidato);
		@SuppressWarnings("unchecked")
		List<Object[]> rows = query.getResultList();
		if (rows != null) {
			for (Object[] row : rows) {
				OfertaCandidatoADesactivarVO oferta = new OfertaCandidatoADesactivarVO();
				oferta.setIdOfertaCandidato(Utils.toLong(row[0]));
				oferta.setIdOfertaEmpleo(Utils.toLong(row[1]));
				oferta.setEstatus(Utils.toInt(row[2]));
				oferta.setTituloOferta(Utils.toString(row[3]));
				oferta.setEmpresaNombre(Utils.toString(row[4]));
				oferta.setCorreoElectronicoContacto(Utils.toString(row[5]));
				ofertas.add(oferta);
			}
		}
		return ofertas;
	}

	public List<OfertaEmpleoVO> consultaTotalPostuladosPorOferta(long idEmpresa) {
		List<OfertaEmpleoVO> ofertas = new ArrayList<OfertaEmpleoVO>();

		StringBuilder sql = new StringBuilder();
		sql.append("WITH POSTULADOS AS ( ");
		sql.append("                SELECT OC.ID_OFERTA_EMPLEO, COUNT(OC.ID_CANDIDATO) AS TOTAL ");
		sql.append("                  FROM OFERTA_EMPLEO OE, ");
		sql.append("                       OFERTA_CANDIDATO OC ");
		sql.append("                 WHERE OE.ID_EMPRESA = ?1 ");
		sql.append("                   AND OE.ESTATUS = ?2 ");
		sql.append("                   AND OC.ID_OFERTA_EMPLEO = OE.ID_OFERTA_EMPLEO ");
		sql.append("                   AND OC.ESTATUS IN (?3, ?4) ");
		sql.append("                 GROUP BY OC.ID_OFERTA_EMPLEO ");
		sql.append("                ) ");
		sql.append("SELECT NVL(POSTULADOS.TOTAL, 0) AS TOTAL, ");
		sql.append("       OE.ID_OFERTA_EMPLEO, ");
		sql.append("       OE.TITULO_OFERTA ");
		sql.append("  FROM OFERTA_EMPLEO OE, POSTULADOS ");
		sql.append(" WHERE OE.ID_EMPRESA = ?5 ");
		sql.append("   AND OE.ESTATUS = ?6 ");
		sql.append("   AND OE.ID_OFERTA_EMPLEO = POSTULADOS.ID_OFERTA_EMPLEO(+) ");
		sql.append("   AND ROWNUM <= ?7 ");
		sql.append(" ORDER BY OE.FECHA_MODIFICACION DESC ");
		
		Query query = entityManager.createNativeQuery(sql.toString());
		query.setParameter(1, idEmpresa);
		query.setParameter(2, ESTATUS.ACTIVO.getIdOpcion());
		query.setParameter(3, ESTATUS.POSTULADO.getIdOpcion());
		query.setParameter(4, ESTATUS.EN_PROCESO.getIdOpcion());
		query.setParameter(5, idEmpresa);
		query.setParameter(6, ESTATUS.ACTIVO.getIdOpcion());
		query.setParameter(6, NUMERO_OFERTAS_RECIENTES);

		@SuppressWarnings("unchecked")
		List<Object[]> rows = query.getResultList();
		if (rows != null) {
			for (Object[] row : rows) {
				OfertaEmpleoVO oferta = new OfertaEmpleoVO();
				oferta.setTotalPostulados(Utils.toInt(row[0]));
				oferta.setIdOfertaEmpleo(Utils.toLong(row[1]));
				oferta.setTituloOferta(Utils.toString(row[2]));

				ofertas.add(oferta);
			}
		}
		return ofertas;
	}

	public void actualizaCompatibilidad(long idOfertaEmpleo, long idCandidato,
			int compatibilidad) {

		String update = "UPDATE OfertaCandidato AS oc "
				+ "SET oc.compatibilidad = :compatibilidad "
				+ "WHERE oc.idOfertaEmpleo = :idOfertaEmpleo "
				+ "AND oc.idCandidato = :idCandidato ";
		try {
			Query query = entityManager.createQuery(update);
			query.setParameter("compatibilidad", compatibilidad);
			query.setParameter("idOfertaEmpleo", idOfertaEmpleo);
			query.setParameter("idCandidato", idCandidato);
			query.executeUpdate();
			;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		
	}

	public List<OfertaCandidatoVO> findCandidatesByEstatus(long idOfertaEmpleo,
			long estatus) throws PersistenceException {
		String findCandidatesByEstatus = "select c from OfertaCandidato c where c.idOfertaEmpleo = :idOfertaEmpleo and c.estatus = :estatus";
		try {
			List<OfertaCandidatoVO> list = new ArrayList<OfertaCandidatoVO>();
			Query query = entityManager.createQuery(findCandidatesByEstatus);
			query.setParameter("idOfertaEmpleo", idOfertaEmpleo);
			query.setParameter("estatus", estatus);

			@SuppressWarnings("unchecked")
			List<Object> result = query.getResultList();
			for (Object resultElement : result) {
				OfertaCandidatoVO vo = getOfertaCandidatoVO((OfertaCandidato) resultElement);
				if (null != vo)
					list.add(vo);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			throw new PersistenceException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<OfertaCandidatoVO> findAllOffersByCandidate(long idCandidato)
			throws PersistenceException {
		String findAllOffersByCandidate = "select c from OfertaCandidato c where c.idCandidato = :idCandidato order by c.idOfertaCandidato desc";
		List<OfertaCandidatoVO> list = new ArrayList<OfertaCandidatoVO>();
		try {

			Query query = entityManager.createQuery(findAllOffersByCandidate);
			query.setParameter("idCandidato", idCandidato);

			List<Object> result = query.getResultList();

			if (result != null)
				for (Object r : result)
					list.add(getOfertaCandidatoVO((OfertaCandidato) r));

		} catch (NoResultException e) {
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			throw new PersistenceException(e);
		}
		return list;
	}

	@Override
	public List<OfertaCandidatoOcupacionDTO> obtenerOfertaCandidatoEmpresa(
			Long idEmpresa, List<Constantes.ESTATUS> estatus,
			List<Constantes.ESTATUS> estatusOferta) throws PersistenceException {
		List<OfertaCandidatoOcupacionDTO> candidatos = new ArrayList<OfertaCandidatoOcupacionDTO>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select OC.ID_OFERTA_EMPLEO, OC.ID_CANDIDATO,OC.ESTATUS,OC.COMPATIBILIDAD,OC.ID_OFERTA_CANDIDATO, OE.ID_EMPRESA, OE.TITULO_OFERTA, ");
		sql.append(" US.CORREO_ELECTRONICO, US.NOMBRE, US.APELLIDO1, US.APELLIDO2, DOM.ID_ENTIDAD, MUN.MUNICIPIO, CATOPC.OPCION OCUPACION_CANDIDATO, CAN.ESTATUS, OC.FECHA_ALTA ,OE.ESTATUS ");
		sql.append(" from OFERTA_EMPLEO OE  ");
		sql.append(" inner join OFERTA_CANDIDATO OC on OE.ID_OFERTA_EMPLEO = OC.ID_OFERTA_EMPLEO ");
		sql.append(" inner join CANDIDATO CAN on CAN.ID_CANDIDATO = OC.ID_CANDIDATO ");
		sql.append(" inner join SOLICITANTE US on CAN.ID_CANDIDATO = US.ID_CANDIDATO ");
		// sql.append("inner join USUARIO US on CAN.ID_USUARIO = US.ID_USUARIO ");
		// ID_TIPO_PROPIETARIO = 2 PARA EL DOMICILIO DE CANDIDATO
		sql.append(" inner join DOMICILIO DOM on DOM.ID_PROPIETARIO = CAN.ID_CANDIDATO and DOM.ID_TIPO_PROPIETARIO = 2 ");
		sql.append(" inner join MUNICIPIO MUN on  DOM.ID_ENTIDAD = MUN.ID_ENTIDAD and DOM.ID_MUNICIPIO = MUN.ID_MUNICIPIO ");
		sql.append(" inner join EXPECTATIVA_LABORAL EXL on EXL.ID_CANDIDATO = OC.ID_CANDIDATO AND EXL.PRINCIPAL = 1");
		// ID_CATOLOGO = 21 -> Occupacion Candidato
		sql.append(" inner join CATALOGO_OPCION CATOPC on CATOPC.ID_CATALOGO_OPCION = EXL.ID_OCUPACION_DESEADA and CATOPC.ID_CATALOGO = 21 ");
		sql.append(" where  OE.ID_EMPRESA = ? ");
		if (estatus != null && estatus.size() > 0) {
			for (int i = 0; i < estatus.size(); i++) {
				if (i == 0) {
					sql.append(" and ( OC.ESTATUS = ? ");
				} else {
					sql.append(" or  OC.ESTATUS = ? ");
				}
			}
			sql.append(" ) ");
		}
		else
		{
			estatus = new ArrayList<Constantes.ESTATUS>();
		}
		if (estatusOferta != null && estatusOferta.size() > 0) {
			for (int i = 0; i < estatusOferta.size(); i++) {
				if (i == 0) {
					sql.append(" and ( OE.ESTATUS = ? ");
				} else {
					sql.append(" or  OE.ESTATUS = ? ");
				}
			}

			sql.append(" ) ");
		}
		else
		{
			estatusOferta = new ArrayList<Constantes.ESTATUS>();
		}
		Query query = entityManager.createNativeQuery(sql.toString());
		query.setParameter(1, idEmpresa);
		for (int i = 0; i < estatus.size(); i++) {
			query.setParameter(2 + i, estatus.get(i).getIdOpcion());
		}
		for (int i = 0; i < estatusOferta.size(); i++) {
			query.setParameter(2 + estatus.size() + i, estatusOferta.get(i)
					.getIdOpcion());
		}
		@SuppressWarnings("unchecked")
		List<Object[]> rows = query.getResultList();
		if (rows != null) {
			for (Object[] row : rows) {
				OfertaCandidatoOcupacionDTO candidato = new OfertaCandidatoOcupacionDTO();
				candidato.setIdOfertaEmpleo(Utils.toString(row[0]));
				candidato.setIdCandidato(Utils.toString(row[1]));
				candidato.setEstatus(Constantes.ESTATUS.getDescripcion(Utils
						.toInt(row[2])));
				candidato.setCompatibilidad(Utils.toString(Utils
						.toString(row[3])));
				candidato.setIdOfertaCandidato(Utils.toString(row[4]));
				candidato.setIdEmpresa(Utils.toString(row[5]));
				candidato.setTituloOferta(Utils.toString(row[6]));
				
				candidato.setCorreoElectronico(Utils.toString(row[7])); //Cambio
				candidato.setNombre(Utils.toString(row[8]));
				candidato.setApelidoPaterno(Utils.toString(row[9]));
				candidato.setApelidoMaterno(Utils.toString(row[10]));
				candidato.setEntidad(Constantes.ENTIDADES_FEDERATIVAS.getEntidad(Utils.toInt(row[11])).getDescripcion());
				candidato.setMunicipio(Utils.toString(row[12]));
				candidato.setOcupacionCandidato(Utils.toString(row[13]));
				candidato.setEstatusCandidato(Constantes.ESTATUS.getDescripcion(Utils.toInt(row[14])));
				//candidato.setFechaPostulacion(mx.gob.stps.portal.utils.Utils.getFechaEscrita(Utils.toDate(row[15])));
				try {
					candidato.setFechaPostulacion(mx.gob.stps.portal.utils.Utils.getFechaEscrita(Utils.toString(row[15])));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				candidato.setEstatusOferta(Constantes.ESTATUS
						.getDescripcion(Utils.toInt(row[16])));
				candidatos.add(candidato);

			}
		}
		return candidatos;

	}

	@Override
	public List<OfertaEmpresaAdminDTO> obtenerOfertasEmpresa(Long idEmpresa,
			List<Catalogos.ESTATUS> estatus,
			List<Catalogos.ESTATUS> estatusOferta) throws PersistenceException {
		List<OfertaEmpresaAdminDTO> ofertasEmpresaAdmin = new ArrayList<OfertaEmpresaAdminDTO>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select  oe.id_oferta_empleo,oe.titulo_oferta, co.opcion,oe.fecha_alta,oe.estatus,");
		sql.append(" case when cand.candidatos  is null then 0 else cand.candidatos end  candidatos, ");
		sql.append(" cand.estatus estatusCand, mun.municipio, ou.id_entidad from oferta_empleo oe ");
		sql.append(" inner join catalogo_opcion co ");
		sql.append(" on co.id_catalogo_opcion = oe.id_nivel_estudio and co.id_catalogo=8 "); // 8
																								// nivel
																								// estudio
		sql.append(" inner join oferta_ubicacion ou ");
		sql.append(" on ou.id_oferta_empleo = oe.id_oferta_empleo ");
		sql.append(" inner join MUNICIPIO MUN ");
		sql.append(" on  ou.ID_ENTIDAD = MUN.ID_ENTIDAD and ou.ID_MUNICIPIO = MUN.ID_MUNICIPIO ");
		sql.append(" left join (select distinct id_oferta_empleo, ");
		sql.append("  count(*) over (partition by id_oferta_empleo,estatus) candidatos,estatus  from oferta_candidato order by id_oferta_empleo,estatus) cand ");
		sql.append(" on oe.id_oferta_empleo = cand.id_oferta_empleo ");
		if (estatus != null && estatus.size() > 0) {
			for (int i = 0; i < estatus.size(); i++) {
				if (i == 0) {
					sql.append(" and ( cand.ESTATUS = ? ");
				} else {
					sql.append(" or cand.ESTATUS = ? ");
				}
			}
			sql.append(" ) ");
		} else {
			estatus = new ArrayList<Catalogos.ESTATUS>();
		}

		sql.append(" where  OE.ID_EMPRESA = ? ");
		if (estatusOferta != null && estatusOferta.size() > 0) {
			for (int i = 0; i < estatusOferta.size(); i++) {
				if (i == 0) {
					sql.append(" and ( oe.estatus = ? ");
				} else {
					sql.append(" or oe.estatus = ? ");
				}
			}

			sql.append(")");
		} else {
			estatusOferta = new ArrayList<Catalogos.ESTATUS>();
		}
		// ordernar por fecha
		sql.append("  order by oe.fecha_alta desc,  oe.titulo_oferta, oe.id_oferta_empleo ");
		Query query = entityManager.createNativeQuery(sql.toString());
		for (int i = 0; i < estatus.size(); i++) {
			query.setParameter(1 + i, estatus.get(i).getIdOpcion());
		}
		query.setParameter(1 + estatus.size(), idEmpresa);

		for (int i = 0; i < estatusOferta.size(); i++) {
			query.setParameter(2 + estatus.size() + i, estatusOferta.get(i)
					.getIdOpcion());
		}
		@SuppressWarnings("unchecked")
		List<Object[]> rows = query.getResultList();
		if (rows != null) {
			String idOfertaEmpleo = "";
			OfertaEmpresaAdminDTO ofertaEmpresaAdmin = new OfertaEmpresaAdminDTO();
			for (Object[] row : rows) {

				if (!idOfertaEmpleo.equals(Utils.toString(row[0]))) {
					ofertaEmpresaAdmin = new OfertaEmpresaAdminDTO();
					ofertaEmpresaAdmin
							.setIdOfertaEmpleo(Utils.toString(row[0]));
					idOfertaEmpleo = Utils.toString(row[0]);
					ofertaEmpresaAdmin.setTituloOferta(Utils.toString(row[1]));
					ofertaEmpresaAdmin.setNivelEstudio(Utils.toString(row[2]));
					ofertaEmpresaAdmin
							.setFechaAlta(mx.gob.stps.portal.utils.Utils
									.getFechaEscrita(Utils.toDate(row[3])));
					ofertaEmpresaAdmin.setEstatus(Constantes.ESTATUS
							.getDescripcion(Utils.toInt(row[4])));
					ofertaEmpresaAdmin.setMunicipio(Utils.toString(row[7]));
					ofertaEmpresaAdmin
							.setEntidadFederativa(Catalogos.ENTIDADES_FEDERATIVAS
									.getDescripcion(Utils.toInt(row[8])));
					ofertasEmpresaAdmin.add(ofertaEmpresaAdmin);

				}
				Catalogos.ESTATUS esta = Catalogos.ESTATUS.getEstatus(Utils
						.toInt(row[6]));

				ofertaEmpresaAdmin.addListEstatusCandidato(esta,
						Utils.toInt(row[5]));

			}
		}
		return ofertasEmpresaAdmin;

	}

	@Override
	public List<OfertaCandidatoOcupacionDTO> obtenerOfertaCandidatoEmpresaPorEstatus(
			Long idEmpresa, List<Catalogos.ESTATUS> candidatoEstatus,
			Long idOferta) {
		List<OfertaCandidatoOcupacionDTO> candidatos = new ArrayList<OfertaCandidatoOcupacionDTO>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select OC.ID_OFERTA_EMPLEO, OC.ID_CANDIDATO,OC.ESTATUS,OC.COMPATIBILIDAD, OE.ID_EMPRESA, OE.TITULO_OFERTA, ");
		sql.append(" US.NOMBRE, US.APELLIDO1, US.APELLIDO2, DOM.ID_ENTIDAD, MUN.MUNICIPIO, CATOPC.OPCION OCUPACION_CANDIDATO, OC.FECHA_ALTA, ");
		sql.append(" OC.ID_OFERTA_CANDIDATO ,CAN.ESTATUS ");
		sql.append(" from OFERTA_EMPLEO OE  ");
		sql.append(" inner join OFERTA_CANDIDATO OC on OE.ID_OFERTA_EMPLEO = OC.ID_OFERTA_EMPLEO ");
		sql.append(" inner join CANDIDATO CAN on CAN.ID_CANDIDATO = OC.ID_CANDIDATO ");
		sql.append(" inner join SOLICITANTE US on CAN.ID_CANDIDATO = US.ID_CANDIDATO ");
		// sql.append("inner join USUARIO US on CAN.ID_USUARIO = US.ID_USUARIO ");
		// ID_TIPO_PROPIETARIO = 2 PARA EL DOMICILIO DE CANDIDATO
		sql.append(" inner join DOMICILIO DOM on DOM.ID_PROPIETARIO = CAN.ID_CANDIDATO and DOM.ID_TIPO_PROPIETARIO = 2 ");
		sql.append(" inner join MUNICIPIO MUN on  DOM.ID_ENTIDAD = MUN.ID_ENTIDAD and DOM.ID_MUNICIPIO = MUN.ID_MUNICIPIO ");
		sql.append(" inner join EXPECTATIVA_LABORAL EXL on EXL.ID_CANDIDATO = OC.ID_CANDIDATO ");
		// ID_CATOLOGO = 21 -> Occupacion Candidato
		sql.append(" inner join CATALOGO_OPCION CATOPC on CATOPC.ID_CATALOGO_OPCION = EXL.ID_OCUPACION_DESEADA and CATOPC.ID_CATALOGO = 21 ");
		sql.append(" where  OE.ID_EMPRESA = ? ");

		if (candidatoEstatus != null) {
			for (int i = 0; i < candidatoEstatus.size(); i++) {
				if (i == 0) {
					sql.append(" and ( OC.ESTATUS = ? ");
				} else {
					sql.append(" or OC.ESTATUS = ? ");
				}
			}
		} else {
			candidatoEstatus = new ArrayList<Catalogos.ESTATUS>();
		}

		sql.append(" ) ");
		if (idOferta != null && idOferta > 0) {
			sql.append(" and  OC.ID_OFERTA_EMPLEO = ? ");
		}
		sql.append(" ");
		Query query = entityManager.createNativeQuery(sql.toString());
		query.setParameter(1, idEmpresa);
		for (int i = 0; i < candidatoEstatus.size(); i++) {
			query.setParameter(2 + i, candidatoEstatus.get(i).getIdOpcion());
		}
		if (idOferta != null && idOferta > 0) {
			query.setParameter(2 + candidatoEstatus.size(), idOferta);
		}
		@SuppressWarnings("unchecked")
		List<Object[]> rows = query.getResultList();
		if (rows != null) {
			for (Object[] row : rows) {
				OfertaCandidatoOcupacionDTO candidato = new OfertaCandidatoOcupacionDTO();
				candidato.setIdOfertaEmpleo(Utils.toString(row[0]));
				candidato.setIdCandidato(Utils.toString(row[1]));
				candidato.setEstatus(Constantes.ESTATUS.getDescripcion(Utils
						.toInt(row[2])));
				candidato.setCompatibilidad(Utils.toString(Utils
						.toString(row[3])));
				candidato.setIdEmpresa(Utils.toString(row[4]));
				candidato.setTituloOferta(Utils.toString(row[5]));
				candidato.setNombre(Utils.toString(row[6]));
				candidato.setApelidoPaterno(Utils.toString(row[7]));
				candidato.setApelidoMaterno(Utils.toString(row[8]));
				candidato.setEntidad(Constantes.ENTIDADES_FEDERATIVAS
						.getEntidad(Utils.toInt(row[9])).getDescripcion());
				candidato.setMunicipio(Utils.toString(row[10]));
				candidato.setOcupacionCandidato(Utils.toString(row[11]));
				try {
					candidato.setFechaPostulacion(mx.gob.stps.portal.utils.Utils.getFechaEscrita(Utils.toString(row[15])));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//candidato.setFechaPostulacion(mx.gob.stps.portal.utils.Utils.getFechaEscrita(Utils.toDate(row[12])));
				candidato.setIdOfertaCandidato(Utils.toString(row[13]));
				candidato.setEstatusCandidato(Constantes.ESTATUS.getDescripcion(Utils.toInt(row[14])));
				candidatos.add(candidato);

			}
		}
		return candidatos;

	}
	
	
	@Override
	public List<OfertaCandidatoOcupacionDTO> obtenerOfertaCandidatoEmpresaPorEstatus(
			Long idEmpresa, List<Catalogos.ESTATUS> candidatoEstatus,
			Long idOferta, List<Catalogos.ESTATUS> ofertaEstatus) {
		List<OfertaCandidatoOcupacionDTO> candidatos = new ArrayList<OfertaCandidatoOcupacionDTO>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select OC.ID_OFERTA_EMPLEO, OC.ID_CANDIDATO,OC.ESTATUS,OC.COMPATIBILIDAD, OE.ID_EMPRESA, OE.TITULO_OFERTA, ");
		sql.append(" US.NOMBRE, US.APELLIDO1, US.APELLIDO2, DOM.ID_ENTIDAD, MUN.MUNICIPIO, CATOPC.OPCION OCUPACION_CANDIDATO, OC.FECHA_ALTA, ");
		sql.append(" OC.ID_OFERTA_CANDIDATO, CAN.ESTATUS, OE.ESTATUS ");
		sql.append(" from OFERTA_EMPLEO OE  ");
		sql.append(" inner join OFERTA_CANDIDATO OC on OE.ID_OFERTA_EMPLEO = OC.ID_OFERTA_EMPLEO ");
		sql.append(" inner join CANDIDATO CAN on CAN.ID_CANDIDATO = OC.ID_CANDIDATO ");
		sql.append(" inner join SOLICITANTE US on CAN.ID_CANDIDATO = US.ID_CANDIDATO ");
		//sql.append("inner join USUARIO US on CAN.ID_USUARIO = US.ID_USUARIO ");
		// ID_TIPO_PROPIETARIO = 2 PARA EL DOMICILIO DE CANDIDATO
		sql.append(" inner join DOMICILIO DOM on DOM.ID_PROPIETARIO = CAN.ID_CANDIDATO and DOM.ID_TIPO_PROPIETARIO = 2 ");
		sql.append(" inner join MUNICIPIO MUN on  DOM.ID_ENTIDAD = MUN.ID_ENTIDAD and DOM.ID_MUNICIPIO = MUN.ID_MUNICIPIO ");
		sql.append(" inner join EXPECTATIVA_LABORAL EXL on EXL.ID_CANDIDATO = OC.ID_CANDIDATO and EXL.PRINCIPAL = 1");
		// ID_CATOLOGO = 21 -> Occupacion Candidato
		sql.append(" inner join CATALOGO_OPCION CATOPC on CATOPC.ID_CATALOGO_OPCION = EXL.ID_OCUPACION_DESEADA and CATOPC.ID_CATALOGO = 21 ");
		sql.append(" where  OE.ID_EMPRESA = ? ");

		if (candidatoEstatus != null && candidatoEstatus.size() > 0) {
			for (int i = 0; i < candidatoEstatus.size(); i++) {
				if (i == 0) {
					sql.append(" and ( OC.ESTATUS = ? ");
				} else {
					sql.append(" or OC.ESTATUS = ? ");
				}
			}
			sql.append(" ) ");

		} else {
			candidatoEstatus = new ArrayList<Catalogos.ESTATUS>();
		}
		if (ofertaEstatus != null && ofertaEstatus.size() > 0) {
			for (int i = 0; i < ofertaEstatus.size(); i++) {
				if (i == 0) {
					sql.append(" and ( OE.ESTATUS = ? ");
				} else {
					sql.append(" or  OE.ESTATUS = ? ");
				}
			}

			sql.append(" ) ");
		}
		else
		{
			ofertaEstatus = new ArrayList<Catalogos.ESTATUS>();
		}

		if (idOferta != null && idOferta > 0) {
			sql.append(" and  OC.ID_OFERTA_EMPLEO = ? ");
		}
		sql.append(" ");
		Query query = entityManager.createNativeQuery(sql.toString());
		query.setParameter(1, idEmpresa);
		for (int i = 0; i < candidatoEstatus.size(); i++) {
			query.setParameter(2 + i, candidatoEstatus.get(i).getIdOpcion());
		}
		for (int i = 0; i < ofertaEstatus.size(); i++) {
			query.setParameter(2 + candidatoEstatus.size() + i, ofertaEstatus.get(i)
					.getIdOpcion());
		}
		if (idOferta != null && idOferta > 0) {
			query.setParameter(2 + candidatoEstatus.size() + ofertaEstatus.size(), idOferta);
		}
		@SuppressWarnings("unchecked")
		List<Object[]> rows = query.getResultList();
		if (rows != null) {
			for (Object[] row : rows) {
				OfertaCandidatoOcupacionDTO candidato = new OfertaCandidatoOcupacionDTO();
				candidato.setIdOfertaEmpleo(Utils.toString(row[0]));
				candidato.setIdCandidato(Utils.toString(row[1]));
				candidato.setEstatus(Constantes.ESTATUS.getDescripcion(Utils
						.toInt(row[2])));
				candidato.setCompatibilidad(Utils.toString(Utils
						.toString(row[3])));
				candidato.setIdEmpresa(Utils.toString(row[4]));
				candidato.setTituloOferta(Utils.toString(row[5]));
				candidato.setNombre(Utils.toString(row[6]));
				candidato.setApelidoPaterno(Utils.toString(row[7]));
				candidato.setApelidoMaterno(Utils.toString(row[8]));
				candidato.setEntidad(Constantes.ENTIDADES_FEDERATIVAS
						.getEntidad(Utils.toInt(row[9])).getDescripcion());
				candidato.setMunicipio(Utils.toString(row[10]));
				candidato.setOcupacionCandidato(Utils.toString(row[11]));
				try {
					candidato.setFechaPostulacion(mx.gob.stps.portal.utils.Utils.getFechaEscrita(Utils.toString(row[12])));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//candidato.setFechaPostulacion(mx.gob.stps.portal.utils.Utils.getFechaEscrita(Utils.toDate(row[12])));
				candidato.setIdOfertaCandidato(Utils.toString(row[13]));
				candidato.setEstatusCandidato(Constantes.ESTATUS.getDescripcion(Utils.toInt(row[14])));
				candidato.setEstatusOferta(Constantes.ESTATUS.getDescripcion(Utils.toInt(row[15])));
				candidatos.add(candidato);

			}
		}
		return candidatos;

	}

	@Override
	public int closePendingPostRelatedOffer(long idOfertaEmpleo) throws PersistenceException {
		int result = 0;
		List<OfertaCandidatoVO> list = new ArrayList<OfertaCandidatoVO>();
		String findAllPendingOffersById = "select c from OfertaCandidato c where c.idOfertaEmpleo =:idOfertaEmpleo";
		try {
			Query query = entityManager.createQuery(findAllPendingOffersById);
			query.setParameter("idOfertaEmpleo", idOfertaEmpleo);
			@SuppressWarnings("unchecked")
			List<Object> results = query.getResultList();
			if (results != null) {
				for (Object r : results) {
					OfertaCandidatoVO oc = getOfertaCandidatoVO((OfertaCandidato) r);
					list.add(oc);
				}
			}
			for (OfertaCandidatoVO oc : list) {
				if (oc.getEstatus() == ESTATUS.POSTULADO.getIdOpcion() || oc.getEstatus() == ESTATUS.EN_PROCESO.getIdOpcion()) {
					oc.setEstatus(ESTATUS.NO_ACEPTADO.getIdOpcion());
					oc.setIdMotivo((int)MOTIVOS_RECHAZO_SEGUIMIENTO.CANDIDATO_COLOCADO.getIdOpcion());
					oc.setFechaSeguimiento(new Date());
					update(oc);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result = -1;
		}
		return result;
	}

	@Override
	public List<mx.gob.stps.portal.persistencia.vo.OfertaCandidatoVO> findAllOffersEstatusByCandidate(long idCandidato, int estatus) throws PersistenceException {
		List<mx.gob.stps.portal.persistencia.vo.OfertaCandidatoVO> list = new ArrayList<mx.gob.stps.portal.persistencia.vo.OfertaCandidatoVO>();
		String findAllOffersEstatusByCandidate = "SELECT e.ID_OFERTA_EMPLEO, e.TITULO_OFERTA, c.FECHA_COLOCACION FROM OFERTA_EMPLEO e, OFERTA_CANDIDATO c WHERE e.ID_OFERTA_EMPLEO = c.ID_OFERTA_EMPLEO AND c.ID_CANDIDATO = " + idCandidato + " AND c.ESTATUS = " + estatus + " ORDER BY c.FECHA_SEGUIMIENTO DESC";
		try {
			Query query = entityManager.createNativeQuery(findAllOffersEstatusByCandidate);
			@SuppressWarnings("unchecked")
			List<Object[]> rows = query.getResultList();
			if (rows != null) {
				for (Object[] row : rows) {
					mx.gob.stps.portal.persistencia.vo.OfertaCandidatoVO vo = new mx.gob.stps.portal.persistencia.vo.OfertaCandidatoVO();
					vo.setIdOfertaEmpleo(Utils.toLong(row[0]));
					vo.setTituloOferta(Utils.toString(row[1]));
					vo.setFechaColocacion(Utils.toDate(row[2]));
					list.add(vo);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return list;
	}

	@Override
	public void notificacionCandidato(NotificacionCandidato candidato) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int postulacionCandidatoXOferta(long idCandidato, List<Long> listOfertas){
		int number = 0;
		
		if(listOfertas.size() > 0){
			String csv = listOfertas.toString().replace("[", "").replace("]", "")
		            .replace(", ", ",");
		
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT count(1) FROM OFERTA_CANDIDATO WHERE ID_CANDIDATO = "+idCandidato+" "
					+ "AND ID_OFERTA_EMPLEO IN ("+csv+") ");
			sb.append(" AND ESTATUS = "+Catalogos.ESTATUS.POSTULADO.getIdOpcion()+" ");
			Query query = entityManager.createNativeQuery(sb.toString());

			Object result = query.getSingleResult();

			if (result!=null)
				number = Utils.toInt(result);
	
		}

		return number;
	}

	
}