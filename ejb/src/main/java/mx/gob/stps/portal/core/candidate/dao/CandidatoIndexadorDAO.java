package mx.gob.stps.portal.core.candidate.dao;

import static mx.gob.stps.portal.core.infra.utils.Utils.obtenEdad;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import mx.gob.stps.portal.core.candidate.vo.ConocimientoHabilidadVO;
import mx.gob.stps.portal.core.candidate.vo.GradoAcademicoVO;
import mx.gob.stps.portal.core.infra.data.TemplateDAO;
import mx.gob.stps.portal.core.infra.utils.Constantes.CONOC_HAB;
import mx.gob.stps.portal.core.infra.utils.Constantes.DISPONIBILIDAD_RADICAR;
import mx.gob.stps.portal.core.infra.utils.Constantes.DISPONIBILIDAD_VIAJAR;
import mx.gob.stps.portal.core.infra.utils.Constantes.ENTIDADES_FEDERATIVAS;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESCOLARIDAD;
import mx.gob.stps.portal.core.infra.utils.Constantes.EXPERIENCIA_LABORAL;
import mx.gob.stps.portal.core.infra.utils.Constantes.MULTIREGISTRO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_EMPLEO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.search.Candidate;

import org.apache.log4j.Logger;

//TODO ELIMINAR ESTA CLASE, LA CONSULTA SE PASO A CandidatoFacade
public class CandidatoIndexadorDAO extends TemplateDAO {

	private static Logger logger = Logger.getLogger(CandidatoIndexadorDAO.class);

	public CandidatoIndexadorDAO(){}
	
	private CandidatoIndexadorDAO(Connection connectionGlobal){
		super(connectionGlobal);
	}
	
	private String ins;
	
	public List<Candidate> consultaCandidatoAIndexar(List<Long> idsCandidato) throws SQLException {
		List<Candidate> candidatos = new ArrayList<Candidate>();
		
		StringBuilder buf = new StringBuilder();
		for (long idCandidato : idsCandidato){
			buf.append(idCandidato +",");
		}

		String ins = buf.toString();

		if (ins.endsWith(","))
			ins = ins.substring(0, ins.length()-1);

		this.ins = ins;

        CachedRowSet cachedRowSet = executeQuery();

        try {
        	long idCandidatoAnterior = 0;
        	long idCandidatoActual = 0;

        	Candidate candidato = null;
        	List<Long> conocimientosHab = null;
        	
            while (cachedRowSet.next()) {

            	idCandidatoActual 			         = cachedRowSet.getLong  ("ID_CANDIDATO");

            	long idCandidatoConocimHabilidad 	 = cachedRowSet.getLong  ("ID_CANDIDATO_CONOCIM_HABILIDAD");
            	String conocimientoHabilidad 	     = cachedRowSet.getString("CONOCIMIENTO_HABILIDAD");
            	long idExperiencia 				     = cachedRowSet.getLong  ("ID_EXPERIENCIA");
                int idTipoConocimientoHabilidad      = cachedRowSet.getInt   ("ID_TIPO_CONOCIM_HABILIDAD");

                if (idCandidatoActual != idCandidatoAnterior){
            		candidato = new Candidate();
            		candidatos.add(candidato);
            		
            		conocimientosHab = new ArrayList<Long>();
            		
                	Date fechaNacimiento 			 = cachedRowSet.getDate  ("FECHA_NACIMIENTO");
                    long disponibilidadViajar 		 = cachedRowSet.getLong  ("DISPONIBILIDAD_VIAJAR");
                    long disponibilidadRadicar 		 = cachedRowSet.getLong  ("DISPONIBILIDAD_RADICAR");
                    int experiencia 				 = cachedRowSet.getInt   ("EXPERIENCIA");
                    int indicadorEstudios 			 = cachedRowSet.getInt   ("INDICADOR_ESTUDIOS");
                    int ocupacion 					 = cachedRowSet.getInt   ("OCUPACION");
                    long salario 					 = cachedRowSet.getLong  ("SALARIO");
                    int tipoEmpleo 				 	 = cachedRowSet.getInt   ("TIPO_EMPLEO");
                    int idEntidad 					 = cachedRowSet.getInt   ("ID_ENTIDAD");
                    String municipio 				 = cachedRowSet.getString("MUNICIPIO");
                    long idCarreraEspecialidad 		 = cachedRowSet.getLong  ("ID_CARRERA_ESPECIALIDAD");
                    long idNivelEstudio 			 = cachedRowSet.getLong  ("ID_NIVEL_ESTUDIO");
                    long idSituacionAcademica 		 = cachedRowSet.getLong  ("ID_SITUACION_ACADEMICA");

                    if (experiencia<=0) experiencia = EXPERIENCIA_LABORAL.CON_EXPERIENCIA.getIdOpcion(); // valor por defecto
                    if (tipoEmpleo <=0) tipoEmpleo  = (int)TIPO_EMPLEO.TIEMPO_COMPLETO.getIdOpcion();
                    
                    String entidadDesc = "";
                    ENTIDADES_FEDERATIVAS entidad = ENTIDADES_FEDERATIVAS.getEntidad(idEntidad);
                    if (entidad!=null) entidadDesc = entidad.getDescripcion();
                    if (municipio==null) municipio = "";

                    String palabras = (entidadDesc + " "+ municipio).trim();

                    candidato.setIdCandidato                  (idCandidatoActual);
                    candidato.setEdad						  (obtenEdad(fechaNacimiento));
                    candidato.setDisponibilidadRadicar		  (disponibilidadRadicar == DISPONIBILIDAD_RADICAR.SI.getIdOpcion());
                    candidato.setDisponibilidad_viajar_ciudad (disponibilidadViajar  == DISPONIBILIDAD_VIAJAR.SI.getIdOpcion());
                    candidato.setExperiencia				  (experiencia);
                    candidato.setIndicador_estudios			  (indicadorEstudios == ESCOLARIDAD.CON_ESTUDIOS.getIdOpcion());
                    candidato.setOcupacion					  (ocupacion);
                    candidato.setSalario					  (salario);
                    candidato.setTipoEmpleo					  (tipoEmpleo);
                    candidato.setPalabras					  (palabras);

                    if (idCarreraEspecialidad>0){
                        GradoAcademicoVO gradoAcademico = new GradoAcademicoVO();
                        gradoAcademico.setIdCarreraEspecialidad(idCarreraEspecialidad);
                        gradoAcademico.setIdNivelEstudio(idNivelEstudio);
                        gradoAcademico.setIdSituacionAcademica(idSituacionAcademica);

                        candidato.setGradoAcademico(gradoAcademico);
                    }
            	}

                if (candidato!=null && conocimientosHab!=null && conocimientoHabilidad!=null 
                		&& !conocimientoHabilidad.isEmpty() && !"[Indica el conocimiento]".equals(conocimientoHabilidad)){
                	
                    ConocimientoHabilidadVO conocimiento = new ConocimientoHabilidadVO();
                    conocimiento.setIdCandidatoConocimHabilidad(idCandidatoConocimHabilidad);
                    conocimiento.setConocimientoHabilidad(conocimientoHabilidad);
                    conocimiento.setIdExperiencia(idExperiencia);

                    // Se verifica que no se dupliquen los conocimientos o habilidades
                    if (!conocimientosHab.contains(idCandidatoConocimHabilidad)){
                    	conocimientosHab.add(idCandidatoConocimHabilidad);

                        if (idTipoConocimientoHabilidad == CONOC_HAB.CONOCIMIENTO.getIdOpcion()){
                        	candidato.addConocimiento(conocimiento);
                        } else if (idTipoConocimientoHabilidad == CONOC_HAB.HABILIDAD.getIdOpcion()){
                        	candidato.addHabilidade(conocimiento);
                        }
                    }
                }
                
                idCandidatoAnterior = idCandidatoActual;
            }

        } catch (Exception e) {
        	e.printStackTrace(); logger.error(e);
            throw new SQLException(e);
        }

        return candidatos;
    }

	@Override
	protected String getQuery() {
		StringBuilder query = new StringBuilder();

		query.append("SELECT CAN.ID_CANDIDATO, ");
		query.append("       CAN.FECHA_NACIMIENTO, ");
		query.append("       PERF.DISPONIBILIDAD_VIAJAR, ");
		query.append("       PERF.DISPONIBILIDAD_RADICAR, ");
		query.append("       PERF.ID_EXPERIENCIA_TOTAL AS EXPERIENCIA, ");
		query.append("       PERF.SIN_ESTUDIOS AS INDICADOR_ESTUDIOS, ");
		query.append("       EX.ID_OCUPACION_DESEADA AS OCUPACION, ");
		query.append("       EX.SALARIO_PRETENDIDO AS SALARIO, ");
		query.append("       EX.ID_TIPO_EMPLEO_DESEADO AS TIPO_EMPLEO, ");
		query.append("       DOM.ID_ENTIDAD, ");
		query.append("       MUN.MUNICIPIO, ");
		query.append("       GRAD.ID_CARRERA_ESPECIALIDAD, ");
		query.append("       GRAD.ID_NIVEL_ESTUDIO, ");
		query.append("       GRAD.ID_SITUACION_ACADEMICA, ");
		query.append("       HAB.ID_CANDIDATO_CONOCIM_HABILIDAD, ");
		query.append("       HAB.CONOCIMIENTO_HABILIDAD, ");
		query.append("       HAB.ID_EXPERIENCIA, ");
		query.append("       HAB.ID_TIPO_CONOCIM_HABILIDAD ");
		query.append("  FROM CANDIDATO CAN, ");
		query.append("       PERFIL_LABORAL PERF, ");
		query.append("       EXPECTATIVA_LABORAL EX, ");
		query.append("       DOMICILIO DOM, ");
		query.append("       MUNICIPIO MUN, ");
		query.append("       CANDIDATO_GRADO_ACADEMICO GRAD, ");
		query.append("       CANDIDATO_CONOCIM_HABILIDAD HAB ");
		query.append(" WHERE CAN.ID_CANDIDATO   IN ("+ this.ins +") ");
		query.append("   AND PERF.ID_CANDIDATO  = CAN.ID_CANDIDATO ");
		query.append("   AND EX.ID_CANDIDATO(+) = CAN.ID_CANDIDATO ");
		query.append("   AND EX.PRINCIPAL   (+) = "+ MULTIREGISTRO.PRINCIPAL.getIdOpcion() +" ");
		query.append("   AND DOM.ID_PROPIETARIO     (+) = CAN.ID_CANDIDATO ");
		query.append("   AND DOM.ID_TIPO_PROPIETARIO(+) = "+ TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario() +" ");
		query.append("   AND MUN.ID_ENTIDAD   (+) = DOM.ID_ENTIDAD ");
		query.append("   AND MUN.ID_MUNICIPIO (+) = DOM.ID_MUNICIPIO ");
		query.append("   AND GRAD.ID_CANDIDATO(+) = CAN.ID_CANDIDATO ");
		query.append("   AND GRAD.PRINCIPAL   (+) = "+ MULTIREGISTRO.PRINCIPAL.getIdOpcion() +" ");
		query.append("   AND HAB.ID_CANDIDATO (+) = CAN.ID_CANDIDATO ");
		query.append(" ORDER BY CAN.ID_CANDIDATO ");
		
		return query.toString();
	}

}
