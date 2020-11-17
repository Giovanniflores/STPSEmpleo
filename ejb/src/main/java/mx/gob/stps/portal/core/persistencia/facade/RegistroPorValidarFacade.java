package mx.gob.stps.portal.core.persistencia.facade;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import mx.gob.stps.portal.core.autorizacion.vo.RegistroPorValidarVO;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_REGISTRO;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.seguridad.vo.PublicadorVO;
import mx.gob.stps.portal.persistencia.entity.RegistroPorValidar;
import mx.gob.stps.portal.utils.ConstantesGenerales;

import org.apache.log4j.Logger;

@Stateless
public class RegistroPorValidarFacade implements RegistroPorValidarFacadeLocal {

	private static Logger logger = Logger.getLogger(RegistroPorValidarFacade.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public RegistroPorValidarVO find(long idRegValidar) throws PersistenceException {
		RegistroPorValidarVO vo = null;
		
		try{
			RegistroPorValidar entity = entityManager.find(RegistroPorValidar.class, idRegValidar);
			vo = getRegistroPorValidarVO(entity);

		}catch(Exception e){
			throw new PersistenceException(e);
		}
		
		return vo;
	}
	
	public long registra(RegistroPorValidarVO vo) throws PersistenceException {
		long idRegValidar = 0;

		if (vo==null) throw new IllegalArgumentException("Instancia con datos requerida");
		
		RegistroPorValidar entity = getRegistroPorValidar(vo);
		
		try{
			entityManager.persist(entity);
			idRegValidar = entity.getIdRegValidar();			

		}catch(Exception e){throw new PersistenceException(e);}

		return idRegValidar;
	}

	public void actualizaEstatus(long idRegValidar, ESTATUS estatus, long idUsuario) throws PersistenceException {
		actualizaEstatus(idRegValidar, estatus, null, idUsuario);
	}
	
	public void actualizaEstatus(long idRegValidar, ESTATUS estatus, Date fechaModificacion, long idUsuario) throws PersistenceException {

		if (idRegValidar<=0) throw new IllegalArgumentException("Identificador requerido");
		if (estatus==null) throw new IllegalArgumentException("Estatus Invalido");

		try{
			StringBuilder jpql = new StringBuilder();
			jpql.append("UPDATE RegistroPorValidar AS r ");
			jpql.append("   SET r.estatus = :estatus, ");
			
			if (fechaModificacion!=null)
			jpql.append("       r.fechaModificacion = :fechaModif, ");
			
			jpql.append("       r.idUsuario = :idUsuario ");
			jpql.append(" WHERE r.idRegValidar = :idRegValidar");

	        Query query = entityManager.createQuery(jpql.toString());
			query.setParameter("estatus", estatus.getIdOpcion());
			
			if (fechaModificacion!=null)
			query.setParameter("fechaModif", fechaModificacion, TemporalType.TIMESTAMP);
			
			query.setParameter("idUsuario", idUsuario);
			query.setParameter("idRegValidar", idRegValidar);

			int updated = query.executeUpdate();

			if (updated<=0){
				logger.error("No se detectaron registros por validar modificados ("+ updated +"), se aplica la modificacion por Entity.");
				RegistroPorValidar entity = entityManager.find(RegistroPorValidar.class, idRegValidar);

				entity.setEstatus(estatus.getIdOpcion());
				entity.setIdUsuario(idUsuario);

				if (fechaModificacion!=null)
					entity.setFechaModificacion(fechaModificacion);

				entityManager.merge(entity);
				entityManager.flush();
			}

		}catch(Exception e){throw new PersistenceException(e);}
	}

	/*public void actualizaUsuarioEstatus(long idRegValidar, Long idUsuario, int estatus, Date fechaModificacion) throws PersistenceException {

		if (idRegValidar<=0) throw new IllegalArgumentException("Identificador requerido");
		if (estatus<=0) throw new IllegalArgumentException("Estatus Invalido");
		
		try{
			RegistroPorValidar entity = entityManager.find(RegistroPorValidar.class, idRegValidar);
			entity.setIdUsuario(idUsuario);
			entity.setEstatus(estatus);
			entity.setFechaModificacion(fechaModificacion);
			entityManager.merge(entity);
			entityManager.flush();
			
		}catch(Exception e){throw new PersistenceException(e);}
	}*/

	public void actualizaEstatusPorPropietario(long idPropietario, ESTATUS estatus, Date fechaModificacion,
			                                   ESTATUS estatusPend, ESTATUS  estatusAsig, ESTATUS estatusEnEdicion, long idUsuario) throws PersistenceException {
		try {
	        String jpql = "UPDATE RegistroPorValidar AS r "+
	                        " SET r.estatus = :estatus, " +
	                            " r.fechaModificacion = :fechaModif, "+
	                            " r.idUsuario = :idUsuario "+
	                      " WHERE r.idPropietario = :idPropietario "+
	                        " AND (r.estatus = :estatusPend OR r.estatus = :estatusAsig OR r.estatus = :estatusEnEdicion)";

			Query query = entityManager.createQuery(jpql);
			query.setParameter("estatus", estatus.getIdOpcion());
			query.setParameter("fechaModif", fechaModificacion, TemporalType.TIMESTAMP);
			query.setParameter("idUsuario", idUsuario);
			
			query.setParameter("idPropietario", idPropietario);
			query.setParameter("estatusPend", estatusPend.getIdOpcion());
			query.setParameter("estatusAsig", estatusAsig.getIdOpcion());
			query.setParameter("estatusEnEdicion", estatusEnEdicion.getIdOpcion());

			int updated = query.executeUpdate();

			if (updated<=0){
				logger.error("No se localizaron registros por validar por registro para su actualizacion de estatus, idPropietario "+ idPropietario);
			}
			
		} catch (NoResultException e) {
			logger.error("No se pudo localizar registros por validar mediante el id de propietario");
		} catch (RuntimeException re) {
			re.printStackTrace(); logger.error(re);
			//throw new PersistenceException(re);
		}
	}

	public void actualizaEstatusPorPropietario(long idRegistro, long idPropietario, long idTipoRegistro, long idTipoPropietario, ESTATUS estatus, Date fechaModificacion, long idUsuario, long idMotivoRechazo) throws PersistenceException {
		
		try {

	        String jpql = "UPDATE RegistroPorValidar AS r "+
		                    " SET r.estatus = :estatus, " +
		                        " r.fechaModificacion = :fechaModif, "+
		                        " r.idMotivoRechazo = :idMotivoRechazo, "+
		                        " r.idUsuario = :idUsuario "+
		                  " WHERE r.idPropietario = :idPropietario "+
		                  	" AND r.idTipoRegistro = :idTipoRegistro " +
		          	        " AND r.idTipoPropietario = :idTipoPropietario "+
  	        				" AND r.idRegistro = :idRegistro ";
  	        				
			Query query = entityManager.createQuery(jpql);
			query.setParameter("estatus", estatus.getIdOpcion());
			query.setParameter("fechaModif", fechaModificacion, TemporalType.TIMESTAMP);
			query.setParameter("idUsuario", idUsuario);
			
			query.setParameter("idRegistro", idRegistro);			
			query.setParameter("idPropietario", idPropietario);
			query.setParameter("idTipoRegistro", idTipoRegistro);
			query.setParameter("idTipoPropietario", idTipoPropietario);
			query.setParameter("idMotivoRechazo", idMotivoRechazo);
			
			int updated = query.executeUpdate();

			if (updated<=0){
				logger.error("No se localizaron registros por validar por registro para su actualizacion de estatus, idPropietario "+ idPropietario);
			}
			
		} catch (NoResultException e) {
			logger.error("No se pudo localizar registros por validar mediante el id de propietario");
		} catch (RuntimeException re) {
			re.printStackTrace(); logger.error(re);
			throw new PersistenceException(re);
		}
		
	}
	
	public void actualizaEstatusPorRegistro(ESTATUS estatus, Date fechaModificacion, 
                                            TIPO_REGISTRO tipoRegistro, long idRegistro,
                                            ESTATUS estatus1, ESTATUS estatus2, long idUsuario) throws PersistenceException {
		try{
			String jpql = "UPDATE RegistroPorValidar AS r " +
					        " SET r.estatus = :estatus, " +
					            " r.fechaModificacion = :fechaModif, "+
					            " r.idUsuario = :idUsuario "+
		                  " WHERE r.idTipoRegistro = :idTipoRegistro " +
		                    " AND r.idRegistro = :idRegistro " +
		                    " AND r.estatus IN (:estatus1, :estatus2)";
		
			Query query = entityManager.createQuery(jpql);
			query.setParameter("estatus", estatus.getIdOpcion());
			query.setParameter("fechaModif", fechaModificacion, TemporalType.TIMESTAMP);
			query.setParameter("idUsuario", idUsuario);
			
			query.setParameter("idTipoRegistro", tipoRegistro.getIdTipoRegistro()); 
			query.setParameter("idRegistro", idRegistro);
			query.setParameter("estatus1", estatus1.getIdOpcion());
			query.setParameter("estatus2", estatus2.getIdOpcion());
			
			int updated = query.executeUpdate();
			
			if (updated<=0){
				logger.error("No se localizaron registros por validar por registro para su actualizacion de estatus, idRegistro "+ idRegistro 
						     +" IdTipoRegistro : "+ tipoRegistro.getIdTipoRegistro());
			}
			
		} catch (NoResultException e) {
			logger.error("No se pudo localizar registros por validar mediante el tipo y id de registro");
		} catch (RuntimeException e) {
			e.printStackTrace(); logger.error(e);
			//throw new PersistenceException(e);
		}
	}
	
//	public void actualizaEstatusPorRegistro(ESTATUS estatus, Date fechaModificacion, 
//                                            TIPO_REGISTRO tipoRegistro, long idRegistro,
//                                            ESTATUS estatus1, ESTATUS estatus2, ESTATUS estatus3, 
//                                            long idUsuario) throws PersistenceException {
//		
//		try{
//			String jpql = "UPDATE RegistroPorValidar AS r " +
//			                " SET r.estatus = :estatus, " +
//			                    " r.fechaModificacion = :fechaModif, "+
//			                    " r.idUsuario = :idUsuario "+
//			              " WHERE r.idTipoRegistro = :idTipoRegistro " +
//			                " AND r.idRegistro = :idRegistro " +
//			                " AND r.estatus IN (:estatus1, :estatus2, :estatus3)";
//
//			Query query = entityManager.createQuery(jpql);
//			query.setParameter("estatus", estatus.getIdOpcion());
//			query.setParameter("fechaModif", fechaModificacion, TemporalType.TIMESTAMP);
//			query.setParameter("idUsuario", idUsuario);
//
//			query.setParameter("idTipoRegistro", tipoRegistro.getIdTipoRegistro()); 
//			query.setParameter("idRegistro", idRegistro);
//			query.setParameter("estatus1", estatus1.getIdOpcion());
//			query.setParameter("estatus2", estatus2.getIdOpcion());
//			query.setParameter("estatus3", estatus3.getIdOpcion());
//			
//			int updated = query.executeUpdate();
//			
//			if (updated<=0){
//				logger.error("No se localizaron registros por validar por registro para su actualizacion de estatus, idRegistro "+ idRegistro 
//						+" IdTipoRegistro : "+ tipoRegistro.getIdTipoRegistro());
//			}
//			
//		} catch (NoResultException e) {
//			logger.error("No se pudo localizar registros por validar mediante el tipo y id de registro");
//		} catch (RuntimeException e) {
//			e.printStackTrace(); logger.error(e);
//			//throw new PersistenceException(e);
//		}
//	}

	
	public void actualizaMotivoRechazo(long idRegValidar, long idMotivoRechazo, String detalleRechazo) throws PersistenceException {

		if (idRegValidar<=0) throw new IllegalArgumentException("Identificador requerido");
		if (idMotivoRechazo<=0) throw new IllegalArgumentException("Identificador idMotivoRechazo requerido");
		
		try{
			RegistroPorValidar entity = entityManager.find(RegistroPorValidar.class, idRegValidar);
			entity.setIdMotivoRechazo(idMotivoRechazo);
			entity.setDetalleRechazo(detalleRechazo);
			entityManager.flush();
			
		}catch(Exception e){throw new PersistenceException(e);}
	}
	
	private RegistroPorValidar getRegistroPorValidar(RegistroPorValidarVO vo){
		RegistroPorValidar entity = new RegistroPorValidar();
		
		entity.setIdRegistro(vo.getIdRegistro());
		entity.setIdTipoRegistro(vo.getIdTipoRegistro());
		entity.setIdPropietario(vo.getIdPropietario());
		entity.setIdTipoPropietario(vo.getIdTipoPropietario());
		entity.setIdUsuario(vo.getIdUsuario());
		entity.setFechaAlta(vo.getFechaAlta());
		entity.setEstatus(vo.getEstatus());
		entity.setFechaModificacion(vo.getFechaModificacion());
		entity.setIdMotivoRechazo(vo.getIdMotivoRechazo());
		entity.setDetalleRechazo(vo.getDetalleRechazo());

		return entity;
	}

	private RegistroPorValidarVO getRegistroPorValidarVO(RegistroPorValidar entity){
		RegistroPorValidarVO vo = new RegistroPorValidarVO();
		
		vo.setIdRegValidar(entity.getIdRegValidar());
		vo.setIdRegistro(entity.getIdRegistro());
		vo.setIdTipoRegistro(entity.getIdTipoRegistro());
		vo.setIdPropietario(entity.getIdPropietario());
		vo.setIdTipoPropietario(entity.getIdTipoPropietario());
		if (entity.getIdUsuario()!=null){
			vo.setIdUsuario(entity.getIdUsuario());	
		}
		vo.setFechaAlta(entity.getFechaAlta());
		vo.setEstatus(entity.getEstatus());
		vo.setFechaModificacion(entity.getFechaModificacion());
		vo.setIdMotivoRechazo(entity.getIdMotivoRechazo());
		vo.setDetalleRechazo(entity.getDetalleRechazo());

		return vo;
	}

	@SuppressWarnings("unchecked")
	public List<Long> consultaPorPropietario(TIPO_REGISTRO tipoRegistro,
			                              TIPO_PROPIETARIO tipoPropietario,
			                              long idPropietario,
			                              ESTATUS estatus) throws PersistenceException {

		List<Long> ids = new ArrayList<Long>();
		
		try{
            String jpql = "SELECT r "+
                          "  FROM RegistroPorValidar r "+
                          " WHERE r.idTipoRegistro = :idTipoRegistro "+ 
                          "   AND r.idTipoPropietario = :idTipoPropietario "+
                          "   AND r.idPropietario = :idPropietario "+
                          "   AND r.estatus = :estatus";

			Query query = entityManager.createQuery(jpql);
			query.setParameter("idTipoRegistro", tipoRegistro.getIdTipoRegistro()); 
			query.setParameter("idTipoPropietario", tipoPropietario.getIdTipoPropietario());
			query.setParameter("idPropietario", idPropietario);
			query.setParameter("estatus", estatus.getIdOpcion());

			List<RegistroPorValidar> registros = (List<RegistroPorValidar>)query.getResultList();

			for (RegistroPorValidar registro : registros)
				ids.add(registro.getIdRegValidar());

		} catch (NoResultException re) {
			// No se localizaron registros
		} catch (RuntimeException re) {
			re.printStackTrace(); throw new PersistenceException(re);
		}
		
		return ids;
	}

	public List<Long> consultaPorRegistro(TIPO_REGISTRO tipoRegistro, long idRegistro, ESTATUS estatus1, ESTATUS estatus2) throws PersistenceException {
		List<Long> ids = new ArrayList<Long>();
		
		try{
			String jpql = "SELECT r FROM RegistroPorValidar r WHERE r.idTipoRegistro = :idTipoRegistro AND r.idRegistro = :idRegistro AND r.estatus IN (:estatus1, :estatus2)";

			Query query = entityManager.createQuery(jpql);
			query.setParameter("idTipoRegistro", tipoRegistro.getIdTipoRegistro()); 
			query.setParameter("idRegistro", idRegistro);
			query.setParameter("estatus1", estatus1.getIdOpcion());
			query.setParameter("estatus2", estatus2.getIdOpcion());
	
			@SuppressWarnings("unchecked")
			List<RegistroPorValidar> registros = query.getResultList();
			
			for (RegistroPorValidar registro : registros) ids.add(registro.getIdRegValidar());
			
		} catch (NoResultException re) {
			// No se localizaron registros
		} catch (RuntimeException re) {
			re.printStackTrace(); throw new PersistenceException(re);
		}
		
		return ids;
	}

	public boolean existeRegistroPorValidar(TIPO_REGISTRO tipoRegistro, long idRegistro, ESTATUS pendiente, ESTATUS asignado, ESTATUS revision) {
		boolean existe = false;
		
		try{
			String jpql = "SELECT r FROM RegistroPorValidar r " +
					       "WHERE r.idTipoRegistro = :idTipoRegistro " +
					         "AND r.idRegistro = :idRegistro " +
					         "AND r.estatus IN (:pendiente, :asignado, :revision) ";

			Query query = entityManager.createQuery(jpql);
			query.setParameter("idTipoRegistro", tipoRegistro.getIdTipoRegistro()); 
			query.setParameter("idRegistro", idRegistro);
			query.setParameter("pendiente", pendiente.getIdOpcion());
			query.setParameter("asignado", asignado.getIdOpcion());
			query.setParameter("revision", revision.getIdOpcion());
	
			@SuppressWarnings("rawtypes")
			List registros = query.getResultList();

			existe = registros!=null && !registros.isEmpty();

		} catch (NoResultException re) {
			// No se localizaron registros
		} catch (Exception e) {
			e.printStackTrace(); logger.error(e);
		}

		return existe;
	}

	public void elimina(long idRegValidar) {
		RegistroPorValidar entity = entityManager.find(RegistroPorValidar.class, idRegValidar);
		entityManager.remove(entity);
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<PublicadorVO> listAssignedUsers() throws PersistenceException {
		List<PublicadorVO> users = new ArrayList<PublicadorVO>();
		
		try {
			StringBuilder select = new StringBuilder();
			
			select.append("SELECT ID_USUARIO, SUM(TOTAL) AS ASSIGNED ");
			select.append("  FROM ( ");
			  
			select.append("SELECT REG.ID_USUARIO, ID_TIPO_REGISTRO, COUNT(REG.ID_USUARIO) AS TOTAL ");
			select.append("  FROM REGISTRO_POR_VALIDAR REG, ");
			select.append("       OFERTA_EMPLEO OFE ");
			select.append(" WHERE REG.ESTATUS IN ("+ ESTATUS.ASIGNADO_PUBLICADOR.getIdOpcion() +", "+ ESTATUS.EN_EDICION_PUBLICADOR.getIdOpcion() +") ");
			select.append("   AND REG.ID_TIPO_REGISTRO = "+ TIPO_REGISTRO.OFERTA.getIdTipoRegistro() +" ");
			select.append("   AND REG.ID_REGISTRO = OFE.ID_OFERTA_EMPLEO ");
			select.append("   AND OFE.ESTATUS IN ("+ ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion() +", "+ ESTATUS.EMP_MODIFICADA.getIdOpcion() +") ");
			select.append(" GROUP BY REG.ID_USUARIO, ID_TIPO_REGISTRO ");

			select.append("UNION ");

			select.append("SELECT REG.ID_USUARIO, ID_TIPO_REGISTRO, COUNT(REG.ID_USUARIO) AS TOTAL ");
			select.append("  FROM REGISTRO_POR_VALIDAR REG, ");
			select.append("       TESTIMONIO TES ");
			select.append(" WHERE REG.ESTATUS IN ("+ ESTATUS.ASIGNADO_PUBLICADOR.getIdOpcion() +", "+ ESTATUS.EN_EDICION_PUBLICADOR.getIdOpcion() +") ");
			select.append("   AND REG.ID_TIPO_REGISTRO = "+ TIPO_REGISTRO.TESTIMONIO.getIdTipoRegistro() +" ");
			select.append("   AND REG.ID_REGISTRO = TES.ID_TESTIMONIO ");
			select.append("   AND TES.ESTATUS = "+ ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion() +" ");
			select.append(" GROUP BY REG.ID_USUARIO, ID_TIPO_REGISTRO ");

			select.append("UNION ");

			select.append("SELECT REG.ID_USUARIO, ID_TIPO_REGISTRO, COUNT(REG.ID_USUARIO) AS TOTAL ");
			select.append("  FROM REGISTRO_POR_VALIDAR REG, ");
			select.append("      PERFIL_LABORAL VID ");
			select.append(" WHERE REG.ESTATUS IN ("+ ESTATUS.ASIGNADO_PUBLICADOR.getIdOpcion() +", "+ ESTATUS.EN_EDICION_PUBLICADOR.getIdOpcion() +") ");
			select.append("   AND REG.ID_TIPO_REGISTRO = "+ TIPO_REGISTRO.VIDEO_CURRICULO.getIdTipoRegistro() +" ");
			select.append("   AND REG.ID_REGISTRO = VID.ID_CANDIDATO ");
			select.append("   AND VID.ESTATUS_VIDEOC = "+ ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion() +" ");
			select.append(" GROUP BY REG.ID_USUARIO, ID_TIPO_REGISTRO ");
			select.append(") ");
			select.append(" GROUP BY ID_USUARIO ");

			//"SELECT id_usuario, COUNT(id_usuario) AS assigned FROM registro_por_validar WHERE ESTATUS IN (26, 27) GROUP BY id_usuario"

			Query query = entityManager.createNativeQuery(select.toString());
			@SuppressWarnings("unchecked")
			List<Object[]> rowSet = query.getResultList();
			for (Object[] result : rowSet){
				PublicadorVO publisher = getPublisher(result);
				users.add(publisher);
			}
			
		} catch (Exception re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}
		
		return users;
	}

	@Override
	public List<PublicadorVO> productivityPublishersList(String name, String lastname, String rangeInit, String rangeFinal) throws PersistenceException {
		HashMap<Long,PublicadorVO> container = new HashMap<Long,PublicadorVO>();
		List<PublicadorVO> users = new ArrayList<PublicadorVO>();
		StringBuilder nativeQuery = new StringBuilder("SELECT reg.id_usuario, usu.nombre, usu.apellido1, reg.id_tipo_registro, COUNT(reg.id_tipo_registro) AS TOTAL_TIPO, reg.estatus, COUNT(reg.estatus) AS TOTAL_ESTATUS ");
		nativeQuery.append("FROM registro_por_validar reg, usuario usu ");
		nativeQuery.append("WHERE usu.id_usuario = reg.id_usuario AND reg.estatus IN (23,25) ");
		if (!name.isEmpty())
			nativeQuery.append("AND usu.nombre LIKE '" + name + "' ");
		if (!lastname.isEmpty())
			nativeQuery.append("AND usu.apellido1 LIKE '" + lastname + "' ");
		if (null != rangeInit && null != rangeFinal)
			nativeQuery.append("AND TRUNC(reg.fecha_modificacion) BETWEEN '" + rangeInit + "' AND '" + rangeFinal + "' ");
		nativeQuery.append("GROUP BY reg.id_usuario, usu.nombre, usu.apellido1, reg.id_tipo_registro, reg.estatus ORDER BY reg.id_usuario");
		try {
			Query query = entityManager.createNativeQuery(nativeQuery.toString());
			@SuppressWarnings("unchecked")
			List<Object[]> rowSet = query.getResultList();
			for (Object[] result : rowSet){
				getProductivityPublisher(result, container);
			}
			Iterator<Long> it = container.keySet().iterator();
			while (it.hasNext()) {
				PublicadorVO vo = container.get(it.next());
				long total = vo.getAutorizados() + vo.getRechazados();
				vo.setTotal(total);
				users.add(vo);
			}
		}catch (Exception re) { re.printStackTrace(); throw new PersistenceException(re); }
		return users;
	}

	private void getProductivityPublisher(Object[] rowSet, HashMap<Long,PublicadorVO> container) {
		PublicadorVO vo = null;
		Long idUsuario = Utils.toLong (rowSet[0]);
		if (idUsuario > 0) {
			if (container.containsKey(idUsuario)) {
				vo = container.get(idUsuario);
				switch (Utils.toInt(rowSet[3])) {
					case 1:
						long empresas = vo.getEmpresas() + Utils.toLong(rowSet[4]);
						vo.setEmpresas(empresas); break;
					case 2: 
						long ofertas = vo.getOfertas() + Utils.toLong(rowSet[4]);
						vo.setOfertas(ofertas); break;
					case 3:
						long testimonios = vo.getTestimonios() + Utils.toLong(rowSet[4]);
						vo.setTestimonios(testimonios); break;
					case 4:
						long videocv = vo.getVideocv() + Utils.toLong(rowSet[4]);
						vo.setVideocv(videocv); break;
				}
				switch (Utils.toInt(rowSet[5])) {
					case 23:
						long rechazados = vo.getRechazados() + Utils.toLong(rowSet[6]);
						vo.setRechazados(rechazados); break;
					case 25:
						long autorizados = vo.getAutorizados() + Utils.toLong(rowSet[6]);
						vo.setAutorizados(autorizados); break;
				}
			}else {
				vo = new PublicadorVO();
				Long id = Utils.toLong (rowSet[0]);
				vo.setIdUsuario(id);
				vo.setNombre(Utils.toString(rowSet[1]));
				vo.setApellido1(Utils.toString(rowSet[2]));
				switch (Utils.toInt(rowSet[3])) {
					case 1: vo.setEmpresas(Utils.toLong(rowSet[4])); break;
					case 2: vo.setOfertas(Utils.toLong(rowSet[4])); break;
					case 3: vo.setTestimonios(Utils.toLong(rowSet[4])); break;
					case 4: vo.setVideocv(Utils.toLong(rowSet[4])); break;
				}
				switch (Utils.toInt(rowSet[5])) {
					case 23: vo.setRechazados(Utils.toLong(rowSet[6])); break;
					case 25: vo.setAutorizados(Utils.toLong(rowSet[6])); break;
				}
				container.put(id, vo);
			}
		}
	}

	private PublicadorVO getPublisher(Object[] rowSet) {
		PublicadorVO vo = new PublicadorVO();
		vo.setIdUsuario((Utils.toLong (rowSet[0])));
		vo.setTotal((Utils.toLong     (rowSet[1])));
		return vo;
	}	

	/*@Override
	public int totalRegistrosPorValidar() throws PersistenceException {
		int result = 0;
		try {
			Query query = entityManager.createNativeQuery("SELECT count(id_registro) FROM registro_por_validar WHERE ESTATUS IN (12, 26, 27)");
			java.math.BigDecimal resultObj = (java.math.BigDecimal)query.getSingleResult();
			result = resultObj.intValue();
		}catch (Exception re) { re.printStackTrace(); result = 0; }
		return result;
	}*/
	
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public void registraOfertaServicioSisne(long idOfertaEmpleo, long idEmpresa) {
    	    	
    	if (existeRegistroPorValidar(TIPO_REGISTRO.OFERTA, idOfertaEmpleo,
                ESTATUS.PENDIENTE_PUBLICAR, ESTATUS.ASIGNADO_PUBLICADOR, ESTATUS.EN_EDICION_PUBLICADOR)){
    			return;
    	}
    	
    	try{
        	RegistroPorValidarVO registro = new RegistroPorValidarVO();
        	registro.setIdRegistro(idOfertaEmpleo);
        	registro.setIdTipoRegistro(TIPO_REGISTRO.OFERTA.getIdTipoRegistro());
        	registro.setIdPropietario(idEmpresa);
        	registro.setIdTipoPropietario(TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
        	registro.setIdUsuario(ConstantesGenerales.ID_USUARIO_ANONIMO);
        	registro.setFechaAlta(new Date());
        	registro.setEstatus(ESTATUS.ACEPTADA.getIdOpcion());
        	registro.setFechaModificacion(null);
        	
        	registra(registro);    		

    	}catch(Exception e){
    		e.printStackTrace();
    		logger.error(e); // NO SE PROPAGA LA EXCEPTION YA QUE NO TIENE QUE AFECTAR LOS PROCESOS QUE LO INVOQUEN
    	}    	
    }	
}
