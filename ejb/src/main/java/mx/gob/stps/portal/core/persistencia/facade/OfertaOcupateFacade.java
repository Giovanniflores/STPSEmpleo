package mx.gob.stps.portal.core.persistencia.facade;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_DOMINIO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_IDIOMAS;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mx.gob.stps.portal.core.infra.utils.Constantes.BOLSA_TRABAJO;
import mx.gob.stps.portal.core.infra.utils.Constantes.EDAD_REQUISITO;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.EXPERIENCIA;
import mx.gob.stps.portal.core.infra.utils.Constantes.MULTIREGISTRO;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;

import org.apache.log4j.Logger;

@Stateless
public class OfertaOcupateFacade implements OfertaOcupateFacadeLocal {

	@PersistenceContext
	private EntityManager entityManager;

	private static Logger logger = Logger.getLogger(OfertaOcupateFacade.class);

	public List<OfertaPorCanalVO> obtenerOfertasOcupate(List<Long> ids) {
		
		List<OfertaPorCanalVO> rows = new ArrayList<OfertaPorCanalVO>();
		
		if (ids==null || ids.isEmpty()) return rows;

		try{
			String in = "";
			String order = "";
			for (int id = 0; id < ids.size(); id++){
				Long item = ids.get(id);
				in += item.longValue() +",";
				order += item.longValue() + ", " + (id + 1) + ",";
			}
			if (in.endsWith(",")) in = in.substring(0, in.length()-1);

			StringBuilder select = new StringBuilder();
			select.append("SELECT H.FUENTE, H.ID_OFERTA_EMPLEO, H.TITULO_OFERTA, G.OPCION || ', ' || F.MUNICIPIO AS UBICACION, ");
			select.append("H.NOMBRE_EMPRESA AS EMPRESA, ");
			select.append(" H.SALARIO, ");
			select.append(" NULL AS GRADO_ESTUDIO, ");
			select.append(" NULL AS CARRERA, ");
			select.append(" H.FUNCIONES, ");
			select.append(" CASE  ");
			select.append(" WHEN (H.EDAD_REQUISITO = "+ EDAD_REQUISITO.SI.getIdOpcion() +") THEN EDAD_MINIMA || ' - ' || EDAD_MAXIMA else 'No es requisito' END AS EDAD, ");
			select.append(" CASE ");
			select.append(" WHEN ((L.ID_OFERTA_EMPLEO IS NULL) OR (L.ID_IDIOMA <= 0)) THEN ' ' ");
			select.append(" ELSE DESCCATALOGO(1, "+ CATALOGO_OPCION_IDIOMAS +",L.ID_IDIOMA) || ' ' || DESCCATALOGO(1, "+ CATALOGO_OPCION_DOMINIO +",L.ID_DOMINIO) ");
			select.append(" END AS IDIOMA, ");
			select.append(" NULL AS HORARIO, ");
			select.append("NULL NUMERO_PLAZAS, ");
			select.append("NULL AS CONTACTO, ");
			select.append("NULL LIMITE_POSTULANTES, ");
			select.append("NULL CONTADOR, ");
			select.append(" H.FECHA_INICIO, H.HABILIDAD_GENERAL, EXPERIENCIA_ANIOS, G.OPCION ENTIDAD, F.MUNICIPIO ");
			select.append("FROM EMPRESA I  ");
			select.append("    LEFT JOIN OFERTA_EMPLEO H ON I.ID_EMPRESA = H.ID_EMPRESA ");  
			select.append("    LEFT JOIN OFERTA_CARRERA_ESPEC K ON H.ID_OFERTA_EMPLEO = K.ID_OFERTA_EMPLEO AND K.PRINCIPAL = "+ MULTIREGISTRO.PRINCIPAL.getIdOpcion() +" "); 
			select.append("    LEFT JOIN OFERTA_IDIOMA L ON H.ID_OFERTA_EMPLEO = L.ID_OFERTA_EMPLEO AND L.PRINCIPAL = "+ MULTIREGISTRO.PRINCIPAL.getIdOpcion() +" ");
			select.append("    LEFT JOIN OFERTA_UBICACION D ON H.ID_OFERTA_EMPLEO = D.ID_OFERTA_EMPLEO ");
			select.append("    LEFT JOIN MUNICIPIO F ON D.ID_ENTIDAD = F.ID_ENTIDAD AND D.ID_MUNICIPIO = F.ID_MUNICIPIO ");  
			select.append("    LEFT JOIN CATALOGO_OPCION G ON D.ID_ENTIDAD = G.ID_CATALOGO_OPCION AND G.ID_CATALOGO = "+ CATALOGO_OPCION_ENTIDAD_FEDERATIVA +" ");
			select.append(" WHERE H.ID_OFERTA_EMPLEO IN ("+ in +") AND H.ESTATUS = "+ ESTATUS.ACTIVO.getIdOpcion() +" ");
			select.append(" ORDER BY (DECODE(H.ID_OFERTA_EMPLEO, " + order + " 99))");

			Query query = entityManager.createNativeQuery(select.toString());

			long idAnterior = 0;
			long idActual = 0;
			
			@SuppressWarnings("unchecked")
			List<Object[]> rowSet = (List<Object[]>)query.getResultList();

			for (Object[] cachedRowSet : rowSet) {
				try{
					idActual = Utils.toLong(cachedRowSet[1]);
					
					if (idActual == idAnterior) continue; // en caso de tener ofertas repetidas
					
					OfertaPorCanalVO row = new OfertaPorCanalVO();
					
					int fuente = Utils.toInt(cachedRowSet[0]);
					
		            if (fuente == BOLSA_TRABAJO.PORTAL_EMPLEO.getIdOpcion()) {
		            	row.setBolsaTrabajo(BOLSA_TRABAJO.PORTAL_EMPLEO.getOpcion());
		            } else if (fuente == BOLSA_TRABAJO.TRABAJA_EN.getIdOpcion()) {
		                row.setBolsaTrabajo(BOLSA_TRABAJO.TRABAJA_EN.getOpcion());
		            } else if (fuente == BOLSA_TRABAJO.CANADA.getIdOpcion()) {
		                row.setBolsaTrabajo(BOLSA_TRABAJO.CANADA.getOpcion());
		            }

		            row.setIdOfertaEmpleo		(Utils.toLong(cachedRowSet[1]));
		            row.setTituloOferta			(Utils.toString(cachedRowSet[2]));
		            row.setUbicacion			(Utils.toString(cachedRowSet[3]));
		            row.setEmpresa				(Utils.toString(cachedRowSet[4]));
		            row.setSalario				(Utils.toDouble(cachedRowSet[5]));
		            row.setGradoEstudio			(Utils.toString(cachedRowSet[6]));
		            row.setCarrera				(Utils.toString(cachedRowSet[7]));
		            row.setFunciones			(Utils.toString(cachedRowSet[8]));
		            row.setEdad					(Utils.toString(cachedRowSet[9]));
		            row.setIdiomas				(Utils.toString(cachedRowSet[10]));
		            row.setHorario				(Utils.toString(cachedRowSet[11]));
		            row.setNumeroPlazas			(Utils.toInt(cachedRowSet[12]));
		            row.setMedioContacto		(Utils.toString(cachedRowSet[13]));
		            row.setLimitePostulantes	(Utils.toInt(cachedRowSet[14]));
		            row.setPostulados			(Utils.toInt(cachedRowSet[15]));
		            row.setFechaInicio			(Utils.toDate(cachedRowSet[16]));
		            row.setFechaInicioString	(formatDate(row.getFechaInicio()));
		            row.setHabilidadGeneral		(Utils.toString(cachedRowSet[17]));
		            row.setExperiencia			(parseExperience(Utils.toInt(cachedRowSet[18])));
		            row.setEntidad				(Utils.toString(cachedRowSet[19]));
		            row.setMunicipio			(Utils.toString(cachedRowSet[20]));

		            rows.add(row);
		            
		            idAnterior = idActual;
		            
				} catch(Exception e) {
					logger.error(e);
				}
			}
	    
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e);
		}

		return rows;
	}

    private String parseExperience(int source) {
    	StringBuilder experience = new StringBuilder();
    	switch (source) {
    		case 1: experience.append(EXPERIENCIA.NINGUNA.getOpcion()); break;
    		case 2: experience.append(EXPERIENCIA.MENOR_UNO.getOpcion());break;
    		case 3: experience.append(EXPERIENCIA.MENOR_DOS.getOpcion());break;
    		case 4: experience.append(EXPERIENCIA.MENOR_TRES.getOpcion());break;
    		case 5: experience.append(EXPERIENCIA.MENOR_CUATRO.getOpcion());break;
    		case 6: experience.append(EXPERIENCIA.MENOR_CINCO.getOpcion());break;
    		case 7: experience.append(EXPERIENCIA.MAS_CINCO.getOpcion());break;
    		default : experience.append(EXPERIENCIA.NO_REQUISITO.getOpcion());
    	}
    	return experience.toString();
    }

	private String formatDate(Date date2format) {
		if (date2format==null) return null;
		Locale local = new Locale("es");
		Format formatter = new SimpleDateFormat("d' de 'MMMM' de 'yyyy", local);
		return formatter.format(date2format);
	}

}
