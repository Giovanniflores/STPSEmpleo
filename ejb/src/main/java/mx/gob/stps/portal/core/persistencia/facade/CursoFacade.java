package mx.gob.stps.portal.core.persistencia.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import mx.gob.stps.portal.core.curso.vo.CursoVO;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Utils;
/**
 * Session Bean implementation class CursoFacade
 */
@Stateless
public class CursoFacade implements CursoFacadeLocal {

	@PersistenceContext
	private EntityManager entityManager;
	/**
	 * Default constructor. 
	 */
	public CursoFacade() {

	}

	@Override
	public List<CursoVO> getCursos() {
		List<CursoVO> listaCursos = new ArrayList<CursoVO>();
		try {
			String sql = getQueryCursos();
			Query query = entityManager.createNativeQuery(sql);

			query.setParameter(1, Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA);
			@SuppressWarnings("unchecked")
			List<Object[]> result = query.getResultList();
			for(Object[] curso: result){

				CursoVO cursoVo = getCursoVO(curso);
				listaCursos.add(cursoVo);
			}
		} catch (NoResultException re) {
			re.printStackTrace();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}

		return listaCursos;
	}


	@Override
	public List<CursoVO> busquedaCurso(String curso, String plantel,int idEntidad) {
		List<CursoVO> listaCursos = new ArrayList<CursoVO>();
		try {
			String sql = getQueryBusquedaCursos(curso,plantel,idEntidad);
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA);
			@SuppressWarnings("unchecked")
			List<Object[]> result = query.getResultList();
			for(Object[] cursoBusqueda: result){

				CursoVO cursoVo = getCursoVO(cursoBusqueda);
				listaCursos.add(cursoVo);
			}
		} catch (NoResultException re) {
			re.printStackTrace();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}

		return listaCursos;
			
		}


		private String getQueryBusquedaCursos(String curso, String plantel,int idEntidad) {
			StringBuilder query = new StringBuilder();
			query.append("SELECT CURSO, PLANTEL, OPCION, DOMICILIO, LINK_PDF ");
			query.append("FROM CURSOS,CATALOGO_OPCION ");
			query.append("WHERE CURSOS.ID_ENTIDAD=CATALOGO_OPCION.ID_CATALOGO_OPCION ");
			query.append("AND CATALOGO_OPCION.ID_CATALOGO = ? ");
			if(!"".equals(curso)||curso!=null)query.append("AND UPPER(CURSO) LIKE '%"+curso.toUpperCase()+"%' ");
			if(!"".equals(plantel)||plantel!=null)query.append("AND UPPER(PLANTEL) LIKE '%"+plantel.toUpperCase()+"%' ");
			if(idEntidad!=0)query.append("AND ID_ENTIDAD ="+idEntidad+" ");
			query.append("ORDER BY ID_ENTIDAD");
			return query.toString();
	}

		private String getQueryCursos() {
			StringBuilder query = new StringBuilder();
			query.append("SELECT CURSO, PLANTEL, OPCION, DOMICILIO, LINK_PDF ");
			query.append("FROM CURSOS,CATALOGO_OPCION ");
			query.append("WHERE CURSOS.ID_ENTIDAD=CATALOGO_OPCION.ID_CATALOGO_OPCION ");
			query.append("AND CATALOGO_OPCION.ID_CATALOGO = ? ");
			return query.toString();
		}

		private CursoVO getCursoVO(Object[] curso) {
			CursoVO cursoVo = new CursoVO();
			cursoVo.setCurso(Utils.toString(curso[0]));
			cursoVo.setPlantel(Utils.toString(curso[1]));
			cursoVo.setEntidad(Utils.toString(curso[2]));
			cursoVo.setDomicilio(Utils.toString(curso[3]));
			cursoVo.setLinkPdf(Utils.toString(curso[4]));


			return cursoVo;
		}







	}
