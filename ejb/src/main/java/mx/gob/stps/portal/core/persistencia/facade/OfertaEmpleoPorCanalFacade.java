package mx.gob.stps.portal.core.persistencia.facade;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CANAL_CAPACIDADES;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CANAL_EGRESADOS;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CANAL_ESTUDIANTES;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CANAL_MAYORES;
import static mx.gob.stps.portal.core.infra.utils.Constantes.EXPERIENCIA_EGRESADOS;
import static mx.gob.stps.portal.core.infra.utils.Constantes.EDAD_MINIMA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.SITUACION_ACADEMICA;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.infra.utils.Constantes.EDAD_REQUISITO;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.GRADO_ESTUDIOS;
import mx.gob.stps.portal.core.infra.utils.Constantes.MULTIREGISTRO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_DISCAPACIDAD;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_EMPLEO;
import org.apache.log4j.Logger;

@Stateless
public class OfertaEmpleoPorCanalFacade implements OfertaEmpleoPorCanalFacadeLocal {

	@PersistenceContext
	private EntityManager entityManager;
	
	private static Logger logger = Logger.getLogger(OfertaEmpleoPorCanalFacade.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Long> getOfertasPorCanal(String canal) throws SQLException {
		List<Long> ids = null;
		logger.info("----OfertaEmpleoPorCanalFacade.getOfertasPorCanal: inicio método");
		try{
			ids = new ArrayList<Long>();
			StringBuilder base = new StringBuilder();
			StringBuilder filtroOfertasCanal = new StringBuilder();
			
			base.append("SELECT H.ID_OFERTA_EMPLEO ");
			base.append("FROM EMPRESA I, OFERTA_EMPLEO H ");
			base.append("LEFT JOIN OFERTA_CARRERA_ESPEC K ON H.ID_OFERTA_EMPLEO = K.ID_OFERTA_EMPLEO ");
			base.append("LEFT JOIN OFERTA_IDIOMA L ON H.ID_OFERTA_EMPLEO = L.ID_OFERTA_EMPLEO AND L.PRINCIPAL =" + MULTIREGISTRO.PRINCIPAL.getIdOpcion());
			base.append(" LEFT JOIN OFERTA_UBICACION D ON H.ID_OFERTA_EMPLEO = D.ID_OFERTA_EMPLEO ");
			base.append("LEFT JOIN MUNICIPIO F ON D.ID_ENTIDAD = F.ID_ENTIDAD AND D.ID_MUNICIPIO = F.ID_MUNICIPIO ");
			base.append("LEFT JOIN CATALOGO_OPCION G ON D.ID_ENTIDAD = G.ID_CATALOGO_OPCION AND G.ID_CATALOGO = " + CATALOGO_OPCION_ENTIDAD_FEDERATIVA);
			base.append(" WHERE H.ESTATUS = " + ESTATUS.ACTIVO.getIdOpcion());
			
			if(canal.equals(CANAL_CAPACIDADES)){//Filtra por ofertas para personas con alguna discapacidad
				filtroOfertasCanal.append(" AND H.DISCAPACIDADES !='00000' ");
			}else if(canal.equals(CANAL_ESTUDIANTES)){//Filtra por ofertas para estudiantes
				filtroOfertasCanal.append(" AND (H.id_situacion_academica = " + SITUACION_ACADEMICA.PASANTE.getIdOpcion());
				filtroOfertasCanal.append(" OR H.id_tipo_empleo = " + TIPO_EMPLEO.MEDIO_TIEMPO.getIdOpcion());
				filtroOfertasCanal.append(" OR H.id_tipo_empleo =" +  TIPO_EMPLEO.FINES_SEMANA.getIdOpcion() + ") ");
			}else if(canal.equals(CANAL_EGRESADOS)){// Filtra por ofertas para recien egresados
				filtroOfertasCanal.append(" AND H.id_nivel_estudio > " +  GRADO_ESTUDIOS.CARRERA_TECNICA.getIdOpcion());
				filtroOfertasCanal.append(" AND H.experiencia_anios < " + EXPERIENCIA_EGRESADOS);
			}else if(canal.equals(CANAL_MAYORES)){//Filtra por ofertas para adultos mayores
				filtroOfertasCanal.append(" AND (H.edad_minima > " + EDAD_MINIMA + " OR H.edad_requisito = " + EDAD_REQUISITO.NO.getIdOpcion() + ") ");
			}
			
			filtroOfertasCanal.append(" AND H.ID_EMPRESA = I.ID_EMPRESA ");
			filtroOfertasCanal.append("ORDER BY H.FECHA_INICIO DESC ");
			
			base.append(filtroOfertasCanal);
			
			Query query = entityManager.createNativeQuery(base.toString());
			List<Object> rowSet = query.getResultList();
			for (Object rs : rowSet) {
				ids.add(Utils.toLong(rs));
			}
		}catch(NoResultException nre){
			logger.error("No se encontrarón resultados para ofertas personas discapacidades");
			ids = new ArrayList<Long>();
		}
		logger.info("----OfertaEmpleoPorCanalFacade.getOfertasPorCanal: Ofetas enontradas = " + ids.size());
		return ids;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> ordenarOfertasPorCanal(String tipoOrdenamiento, Integer numeroColumna, String canal) throws SQLException {
		List<Long> ids = null;
		logger.info("----OfertaEmpleoPorCanalFacade.ordenarOfertasPorCanal: inicio método");
		try{
			ids = new ArrayList<Long>();
			StringBuilder base = new StringBuilder();
			StringBuilder filtroOfertasCanal = new StringBuilder();
			
			base.append("SELECT H.ID_OFERTA_EMPLEO ");
			base.append("FROM EMPRESA I, OFERTA_EMPLEO H ");
			base.append("LEFT JOIN OFERTA_CARRERA_ESPEC K ON H.ID_OFERTA_EMPLEO = K.ID_OFERTA_EMPLEO ");
			base.append("LEFT JOIN OFERTA_IDIOMA L ON H.ID_OFERTA_EMPLEO = L.ID_OFERTA_EMPLEO AND L.PRINCIPAL =" + MULTIREGISTRO.PRINCIPAL.getIdOpcion());
			base.append(" LEFT JOIN OFERTA_UBICACION D ON H.ID_OFERTA_EMPLEO = D.ID_OFERTA_EMPLEO ");
			base.append("LEFT JOIN MUNICIPIO F ON D.ID_ENTIDAD = F.ID_ENTIDAD AND D.ID_MUNICIPIO = F.ID_MUNICIPIO ");
			base.append("LEFT JOIN CATALOGO_OPCION G ON D.ID_ENTIDAD = G.ID_CATALOGO_OPCION AND G.ID_CATALOGO = " + CATALOGO_OPCION_ENTIDAD_FEDERATIVA);
			base.append(" WHERE H.ESTATUS = " + ESTATUS.ACTIVO.getIdOpcion());
			
			if(canal.equals(CANAL_CAPACIDADES)){//Filtra por ofertas para personas con alguna discapacidad
				filtroOfertasCanal.append(" AND H.id_discapacidad > " + TIPO_DISCAPACIDAD.NINGUNA.getIdOpcion());
			}else if(canal.equals(CANAL_ESTUDIANTES)){//Filtra por ofertas para estudiantes
				filtroOfertasCanal.append(" AND (H.id_situacion_academica = " + TIPO_EMPLEO.FINES_SEMANA.getIdOpcion());
				filtroOfertasCanal.append(" OR H.id_tipo_empleo = " + TIPO_EMPLEO.MEDIO_TIEMPO.getIdOpcion());
				filtroOfertasCanal.append(" OR H.id_tipo_empleo =" +  TIPO_EMPLEO.FINES_SEMANA.getIdOpcion() + ") ");
			}else if(canal.equals(CANAL_EGRESADOS)){// Filtra por ofertas para recien egresados
				filtroOfertasCanal.append(" AND H.id_nivel_estudio > " +  GRADO_ESTUDIOS.CARRERA_TECNICA.getIdOpcion());
				filtroOfertasCanal.append(" AND H.experiencia_anios < " + EXPERIENCIA_EGRESADOS);
			}else if(canal.equals(CANAL_MAYORES)){//Filtra por ofertas para adultos mayores
				filtroOfertasCanal.append(" AND (H.edad_minima > " + EDAD_MINIMA + " OR H.edad_requisito = " + EDAD_REQUISITO.NO.getIdOpcion() + ") ");
			}
			
			filtroOfertasCanal.append(" AND H.ID_EMPRESA = I.ID_EMPRESA ");
			
			 if(tipoOrdenamiento.equals("asc")){//--- Se lleva a cabo el ordenamiento Ascendente de las columnas
				 if(numeroColumna == 1)
					 filtroOfertasCanal.append(" ORDER BY H.TITULO_OFERTA ASC ");
				 else if(numeroColumna == 2)
					 filtroOfertasCanal.append(" ORDER BY G.OPCION ASC, F.MUNICIPIO ASC ");
				 else if(numeroColumna == 3)
					 filtroOfertasCanal.append(" ORDER BY H.NOMBRE_EMPRESA ASC ");
				 else if(numeroColumna == 4)
					 filtroOfertasCanal.append(" ORDER BY H.SALARIO ASC ");
				 else if(numeroColumna == 5)
					 filtroOfertasCanal.append(" ORDER BY H.FECHA_INICIO ASC ");
			 }else if(tipoOrdenamiento.equals("desc")){//--- Se lleva a cabo el ordenamiento Descendente de las columnas
				 if(numeroColumna == 1)
					 filtroOfertasCanal.append(" ORDER BY H.TITULO_OFERTA DESC ");
				 else if(numeroColumna == 2)
					 filtroOfertasCanal.append(" ORDER BY G.OPCION DESC, F.MUNICIPIO DESC ");
				 else if(numeroColumna == 3)
					 filtroOfertasCanal.append(" ORDER BY H.NOMBRE_EMPRESA DESC ");
				 else if(numeroColumna == 4)
					 filtroOfertasCanal.append(" ORDER BY H.SALARIO DESC ");
				 else if(numeroColumna == 5)
					 filtroOfertasCanal.append(" ORDER BY H.FECHA_INICIO DESC ");
			 }
			
			base.append(filtroOfertasCanal);
			
			Query query = entityManager.createNativeQuery(base.toString());
			List<Object> rowSet = query.getResultList();
			for (Object rs : rowSet) {
				ids.add(Utils.toLong(rs));
			}
			
		}catch(NoResultException nre){
			logger.error("No se encontrarón resultados para ofertas personas discapacidades");
			ids = new ArrayList<Long>();
		}
		logger.info("----OfertaEmpleoPorCanalFacade.ordenarOfertasPorCanal: Ofetas enontradas = " + ids.size());
		return ids;
	}
}
