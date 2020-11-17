////package mx.gob.stps.portal.core.search.service.impl;
////
////
////import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_DOMINIO;
////import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA;
////import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_IDIOMAS;
////
////import java.util.HashMap;
////import java.util.List;
////import java.util.ArrayList;
////import java.util.Map;
////import java.sql.SQLException;
////
////import javax.ejb.EJB;
////import javax.ejb.Stateless;
////import javax.ejb.TransactionAttribute;
////import javax.ejb.TransactionAttributeType;
////import javax.ejb.TransactionManagement;
////import javax.ejb.TransactionManagementType;
////import javax.persistence.EntityManager;
////import javax.persistence.NoResultException;
////import javax.persistence.PersistenceContext;
////import javax.persistence.PersistenceException;
////import javax.persistence.Query;
////
////import org.apache.log4j.Logger;
////
////import mx.gob.stps.portal.persistencia.vo.CandidatoVO;
////import mx.gob.stps.portal.persistencia.vo.CatSubareaVO;
////import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
////import mx.gob.stps.portal.persistencia.vo.ExpectativaLaboralVO;
////import mx.gob.stps.portal.persistencia.vo.SolicitanteVO;
////import mx.gob.stps.portal.exception.TechnicalException;
////import mx.gob.stps.portal.persistencia.entity.Candidato;
////import mx.gob.stps.portal.persistencia.entity.CandidatoGradoAcademico;
////import mx.gob.stps.portal.persistencia.entity.ExpectativaLaboral;
////import mx.gob.stps.portal.persistencia.entity.Solicitante;
////import mx.gob.stps.portal.core.infra.utils.Constantes;
////import mx.gob.stps.portal.core.infra.utils.Utils;
////import mx.gob.stps.portal.core.infra.utils.Constantes.BOLSA_TRABAJO;
////import mx.gob.stps.portal.core.infra.utils.Constantes.EDAD_REQUISITO;
////import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
////import mx.gob.stps.portal.core.infra.utils.Constantes.EXPERIENCIA;
////import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_BUSQUEDA;
////import mx.gob.stps.portal.persistencia.vo.CandidatoGradoAcademicoVO;
////import mx.gob.stps.portal.utils.Catalogos.MULTIREGISTRO;
////import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;
////import mx.gob.stps.portal.core.persistencia.facade.CatalogoOpcionFacadeLocal;
////import mx.gob.stps.portal.core.search.ResultInfoBO;
////import mx.gob.stps.portal.core.search.service.SingleRegisterServiceRemote;
////
////@Stateless(name = "SingleRegisterService", mappedName = "SingleRegisterService")
////@TransactionManagement(TransactionManagementType.CONTAINER)
////public class SingleRegisterService implements SingleRegisterServiceRemote {
////	
////	@PersistenceContext
////	private EntityManager entityManager;
////	
////	public static final int PAGE_NUM_ROW = 10;
////	
////	@EJB
////	private CatalogoOpcionFacadeLocal catalogoOpcionFacadeLocal;
////	
////	private static Logger logger = Logger.getLogger(SingleRegisterService.class);
////	
////	@Override
////	@TransactionAttribute (value=TransactionAttributeType.NOT_SUPPORTED)
////	public List<CandidatoVO> candidatoListByIndex(List<Long> index) throws TechnicalException, SQLException {
////		List<CandidatoVO> rowsPage = new ArrayList<CandidatoVO>();
////		if (null != index && !index.isEmpty()) {
////			try {
////				for (Long id : index) {
////					CandidatoVO vo = find(id);
////					if (null != vo && vo.getIdCandidato() > 0)
////						rowsPage.add(vo);
////				}
////			}catch (Exception error) {
////				error.printStackTrace();
////			}
////		}
////		return rowsPage;
////	}
////	
////	@Override
////	public List<ExpectativaLaboralVO> expectativaLaboralList(long idCandidato) throws TechnicalException, SQLException {
////		List<ExpectativaLaboralVO> list = new ArrayList<ExpectativaLaboralVO>();
////		Query query = entityManager.createQuery("SELECT el FROM ExpectativaLaboral el WHERE el.idCandidato = :idCandidato");
////        query.setParameter("idCandidato", idCandidato);
////        try {
////        	@SuppressWarnings("unchecked")
////			List<ExpectativaLaboral> results = (List<ExpectativaLaboral>)query.getResultList();
////        	for (ExpectativaLaboral entity : results) {
////        		ExpectativaLaboralVO expectativa = getExpectativaLaboralVO(entity);
////        		if (null != expectativa && null != expectativa.getIdAreaLaboralDeseada() && null != expectativa.getIdSubAreaLaboralDeseada()) {
////        			CatSubareaVO subarea = catalogoOpcionFacadeLocal.getSubAreaVOByIdAreaIdSubArea(expectativa.getIdAreaLaboralDeseada(),
////        				expectativa.getIdSubAreaLaboralDeseada());
////        			if (null != subarea) expectativa.setPuestoDeseado(subarea.getDescripcion());
////        		}
////        		list.add(expectativa);
////        	}
////        } catch (NoResultException e) {
////            logger.error("Expectativa Laboral no localizada para el id: "+ idCandidato);
////        }
////		return list; 
////	}
////	
////	private ExpectativaLaboralVO getExpectativaLaboralVO(ExpectativaLaboral entity) {
////		if (null == entity) return null;
////		ExpectativaLaboralVO vo = new ExpectativaLaboralVO();
////		vo.setIdCandidato(entity.getIdCandidato());
////		vo.setIdExperiencia(entity.getIdExperiencia());
////		vo.setSalarioPretendido(entity.getSalarioPretendido());
////		vo.setIdExpectativaLaboral(entity.getIdExpectativaLaboral());
////		vo.setIdAreaLaboralDeseada(entity.getIdAreaLaboralDeseada());
////		if (null != entity.getPrincipal())
////			vo.setPrincipal(entity.getPrincipal().longValue());
////		vo.setIdSubAreaLaboralDeseada(entity.getIdSubAreaLaboralDeseada());
////		return vo;
////	}
////	
////	@Override
////	public List<CandidatoGradoAcademicoVO> gradoAcademicoList(long idCandidato) throws TechnicalException, SQLException {
////		List<CandidatoGradoAcademicoVO> list = new ArrayList<CandidatoGradoAcademicoVO>();
////		Query query = entityManager.createQuery("SELECT g FROM CandidatoGradoAcademico g WHERE g.idCandidato = :idCandidato");
////		query.setParameter("idCandidato", idCandidato);
////		try {
////        	@SuppressWarnings("unchecked")
////        	List<CandidatoGradoAcademico> results = (List<CandidatoGradoAcademico>)query.getResultList();
////        	for (CandidatoGradoAcademico entity : results) {
////        		CandidatoGradoAcademicoVO vo = getGradoAcademicoVO(entity);
////        		vo.setNivelEstudio(catalogoOpcionFacadeLocal.getOpcionById(Constantes.CATALOGO_OPCION_GRADO_ESTUDIOS, entity.getIdNivelEstudio()));
////        		CatalogoOpcionVO career = catalogoOpcionFacadeLocal.consultaOpcionPorCatalogosAsociados(Constantes.CATALOGO_OPCION_GRADO_ESTUDIOS, entity.getIdCarreraEspecialidad());
////        		if (null != career) vo.setCarreraEspecialidad(career.getOpcion());
////        		vo.setSituacionAcademica(catalogoOpcionFacadeLocal.getOpcionById(Constantes.CATALOGO_OPCION_ESTATUS_GRADO, entity.getIdNivelEstudio()));
////        		list.add(vo);
////        	}
////		} catch (NoResultException e) {
////			logger.error("Grado Academico no localizado para el id: "+ idCandidato);
////        }
////		return list;
////	}
////	
////	@Override
////	public List<OfertaPorCanalVO> resultInfoList(int page, List<ResultInfoBO> index) throws TechnicalException, SQLException {
////		int iteracion = 0;
////		int contadorOfertas = 0;
////		int MAX_ITERACIONES = 10;
////        boolean limpiarLista = false;
////        int pageCountIndex = PAGE_NUM_ROW * (page - 1);
////        int ultimoIndex = pageCountIndex;
////        int bloqueInicial = pageCountIndex;
////        int ultimoBloqueConOfertas = pageCountIndex;
////        TIPO_BUSQUEDA tipoBusqueda = TIPO_BUSQUEDA.BUSQUEDA_OCUPATE;
////        List<ResultInfoBO> index2Remove = new ArrayList<ResultInfoBO>();
////        List<OfertaPorCanalVO> rowsPage = new ArrayList<OfertaPorCanalVO>();
////    	if (page < 0)
////            return new ArrayList<OfertaPorCanalVO>();
////        if (index == null || index.isEmpty())
////            return new ArrayList<OfertaPorCanalVO>();
////        while (true) {
////            List<Long> ids = new ArrayList<Long>();
////            Map<Long, ResultInfoBO> resultsMap = new HashMap<Long, ResultInfoBO>();
////            if (rowsPage.size() == index.size()) { // para listas pequeñas se cumple con la cantidad solicitada
////                break;
////            }
////            if (pageCountIndex > index.size()) {
////                limpiarLista = true;
////                break;
////            }
////            if (iteracion >= MAX_ITERACIONES * 2) { // Permite doble iteracion ya que se realiza en ambos sentidos
////                limpiarLista = true;
////                break;
////            }
////            for (int i = pageCountIndex; (i < (pageCountIndex + PAGE_NUM_ROW) && i < index.size()); i++) {
////                ResultInfoBO result = index.get(i);
////                ids.add(result.getId());
////                resultsMap.put(result.getId(), result);
////            }
////            List<OfertaPorCanalVO> offerList = resultInfoList(ids);
////            if (!offerList.isEmpty()) {
////                ultimoBloqueConOfertas = pageCountIndex;
////                contadorOfertas+= offerList.size();
////                ultimoIndex = ultimoBloqueConOfertas + contadorOfertas;
////                if (resultsMap.size() > offerList.size()) {
////                    List<ResultInfoBO> faltantes = searchMissing(new ArrayList<ResultInfoBO>(resultsMap.values()), offerList);
////                    index2Remove.addAll(faltantes);
////                }
////                if (rowsPage.size() < PAGE_NUM_ROW) {
////                    int restantes = PAGE_NUM_ROW - rowsPage.size();
////                    for (int j = 0; j < restantes && j < offerList.size(); j++) {
////                        OfertaPorCanalVO oferta = offerList.get(j);
////                        if (tipoBusqueda == TIPO_BUSQUEDA.BUSQUEDA_OCUPATE) {
////                            ResultInfoBO result = resultsMap.get(oferta.getIdOfertaEmpleo());
////                            if (result != null) {
////                                oferta.setTituloOferta(result.getTitulo().replace("<b>", "<font style=\"background:#CFE297\">").replace("</b>", "</font>"));
////                                oferta.setOcupacion(result.getOcupacion());
////                                oferta.setCarreras(result.getCarreras());
////                                oferta.setConocimientos(result.getConocimientos());
////                            }
////                        }
////                        rowsPage.add(oferta);
////                    } //for
////                } //if
////            }
////            if (rowsPage.size() >= PAGE_NUM_ROW) {
////                break;
////            }
////            if (offerList.isEmpty() || offerList.size() < resultsMap.size()) {
////                iteracion++;
////                if (iteracion == MAX_ITERACIONES) { // Cuando llega al limite de iteraciones posiciona el iterador para buscar hacia atras 
////                    pageCountIndex = bloqueInicial;
////                    limpiarLista = true;
////                }
////                if (iteracion < MAX_ITERACIONES) { // Primeras iteraciones hacia delante
////                    pageCountIndex+= PAGE_NUM_ROW;
////                }
////                if (iteracion > MAX_ITERACIONES) { // Mas del limite son iteraciones hacia atras
////                    pageCountIndex-= PAGE_NUM_ROW;
////                }
////            } else if (offerList.size() == resultsMap.size()) { // Obtiene las ofertas que se solicitaron por lo tanto incrementa el contador para verificar si hay mas faltantes
////                pageCountIndex+= PAGE_NUM_ROW;
////            }
////        } //while
////        if (limpiarLista && ultimoIndex > 0 && ultimoIndex < index.size()) {
////            while (ultimoIndex < index.size()) {
////                ResultInfoBO id = index.get(ultimoIndex);
////                index2Remove.add(id);
////                ultimoIndex++;
////            }
////        }
////        for (ResultInfoBO vo : index2Remove) {
////        	index.remove(vo);
////        }
////        return rowsPage;
////	}
////	
////	private List<ResultInfoBO> searchMissing(List<ResultInfoBO> indices, List<OfertaPorCanalVO> ofertas) {
////        List<ResultInfoBO> faltantes = new ArrayList<ResultInfoBO>();
////        for (ResultInfoBO indice : indices) {
////            boolean encontrada = false;
////            for (OfertaPorCanalVO oferta : ofertas) {
////                if (oferta.getIdOfertaEmpleo() == indice.getId()) {
////                    encontrada = true;
////                    break;
////                }
////            }
////            if (!encontrada) {
////                faltantes.add(indice);
////            }
////        }
////        return faltantes;
////    }
////	
////	private List<OfertaPorCanalVO> resultInfoList(List<Long> ids) {
////        List<OfertaPorCanalVO> rows = new ArrayList<OfertaPorCanalVO>();
////        if (ids == null || ids.isEmpty()) {
////            return rows;
////        }
////        try {
////            String in = "";
////            String order = "";
////            long idAnterior = 0;
////            long idActual = 0;
////            for (int id = 0; id < ids.size(); id++) {
////                Long item = ids.get(id);
////                in+= item + ",";
////                order+= item + ", " + (id +1) + ", ";
////            }
////            if (in.endsWith(",")) {
////                in = in.substring(0, in.length() -1);
////            }
////            StringBuilder select = new StringBuilder();
////            select.append("SELECT");
////            select.append(" H.FUENTE,");
////            select.append(" H.ID_OFERTA_EMPLEO,");
////            select.append(" H.TITULO_OFERTA,");
////            select.append(" G.OPCION || ', ' || F.MUNICIPIO AS UBICACION,");
////            select.append(" H.NOMBRE_EMPRESA AS EMPRESA,");
////            select.append(" H.SALARIO,");
////            select.append(" NULL AS GRADO_ESTUDIO,");
////            select.append(" NULL AS CARRERA,");
////            select.append(" H.FUNCIONES,");
////            select.append(" CASE WHEN (H.EDAD_REQUISITO = " + EDAD_REQUISITO.SI.getIdOpcion() + ") THEN EDAD_MINIMA || ' - ' || EDAD_MAXIMA ELSE 'No es requisito' END AS EDAD,");
////            select.append(" CASE WHEN ((L.ID_OFERTA_EMPLEO IS NULL) OR (L.ID_IDIOMA <= 0)) THEN ' ' ELSE DESCCATALOGO(1, " + CATALOGO_OPCION_IDIOMAS + ", L.ID_IDIOMA) || ' ' || DESCCATALOGO(1, " + CATALOGO_OPCION_DOMINIO + ", L.ID_DOMINIO) END AS IDIOMA,");
////            select.append(" NULL AS HORARIO,");
////            select.append(" NULL NUMERO_PLAZAS,");
////            select.append(" NULL AS CONTACTO,");
////            select.append(" NULL LIMITE_POSTULANTES,");
////            select.append(" NULL CONTADOR,");
////            select.append(" H.FECHA_INICIO,");
////            select.append(" H.HABILIDAD_GENERAL,");
////            select.append("EXPERIENCIA_ANIOS,");
////            select.append(" G.OPCION ENTIDAD,");
////            select.append(" F.MUNICIPIO, ");
////            select.append(" F.ID_MUNICIPIO, ");
////            select.append(" I.ID_EMPRESA, ");
////            select.append(" H.ID_AREA,");
////            select.append(" H.ID_SUBAREA ");
////            select.append("FROM EMPRESA I");
////            select.append(" LEFT JOIN OFERTA_EMPLEO H ON I.ID_EMPRESA = H.ID_EMPRESA");
////            select.append(" LEFT JOIN OFERTA_CARRERA_ESPEC K ON H.ID_OFERTA_EMPLEO = K.ID_OFERTA_EMPLEO AND K.PRINCIPAL = " + MULTIREGISTRO.PRINCIPAL.getIdOpcion());
////            select.append(" LEFT JOIN OFERTA_IDIOMA L ON H.ID_OFERTA_EMPLEO = L.ID_OFERTA_EMPLEO AND L.PRINCIPAL = " + MULTIREGISTRO.PRINCIPAL.getIdOpcion());
////            select.append(" LEFT JOIN OFERTA_UBICACION D ON H.ID_OFERTA_EMPLEO = D.ID_OFERTA_EMPLEO");
////            select.append(" LEFT JOIN MUNICIPIO F ON D.ID_ENTIDAD = F.ID_ENTIDAD AND D.ID_MUNICIPIO = F.ID_MUNICIPIO");
////            select.append(" LEFT JOIN CATALOGO_OPCION G ON D.ID_ENTIDAD = G.ID_CATALOGO_OPCION AND G.ID_CATALOGO = " + CATALOGO_OPCION_ENTIDAD_FEDERATIVA + " ");
////            select.append("WHERE H.ID_OFERTA_EMPLEO IN (" + in + ") AND H.ESTATUS = " + ESTATUS.ACTIVO.getIdOpcion() + " ");
////            select.append("ORDER BY (DECODE(H.ID_OFERTA_EMPLEO, " + order + " 99))");
////            javax.persistence.Query query = entityManager.createNativeQuery(select.toString());
////            @SuppressWarnings("unchecked")
////			List<Object[]> rowSet = (List<Object[]>) query.getResultList();
////            for (Object[] cachedRowSet : rowSet) {
////                try {
////                    OfertaPorCanalVO row = new OfertaPorCanalVO();
////                    idActual = Utils.toLong(cachedRowSet[1]);
////                    if (idActual == idAnterior) {
////                        // En caso de tener ofertas repetidas
////                        continue;
////                    }
////                    int fuente = Utils.toInt(cachedRowSet[0]);
////                    if (fuente == BOLSA_TRABAJO.PORTAL_EMPLEO.getIdOpcion()) {
////                        row.setBolsaTrabajo(BOLSA_TRABAJO.PORTAL_EMPLEO.getOpcion());
////                    } else if (fuente == BOLSA_TRABAJO.TRABAJA_EN.getIdOpcion()) {
////                        row.setBolsaTrabajo(BOLSA_TRABAJO.TRABAJA_EN.getOpcion());
////                    } else if (fuente == BOLSA_TRABAJO.CANADA.getIdOpcion()) {
////                        row.setBolsaTrabajo(BOLSA_TRABAJO.CANADA.getOpcion());
////                    }
////                    row.setIdOfertaEmpleo(Utils.toLong(cachedRowSet[1]));
////                    row.setTituloOferta(Utils.toString(cachedRowSet[2]));
////                    row.setUbicacion(Utils.toString(cachedRowSet[3]));
////                    row.setEmpresa(Utils.toString(cachedRowSet[4]));
////                    row.setSalario(Utils.toDouble(cachedRowSet[5]));
////                    row.setGradoEstudio(Utils.toString(cachedRowSet[6]));
////                    row.setCarrera(Utils.toString(cachedRowSet[7]));
////                    row.setFunciones(Utils.toString(cachedRowSet[8]));
////                    row.setEdad(Utils.toString(cachedRowSet[9]));
////                    row.setIdiomas(Utils.toString(cachedRowSet[10]));
////                    row.setHorario(Utils.toString(cachedRowSet[11]));
////                    row.setNumeroPlazas(Utils.toInt(cachedRowSet[12]));
////                    row.setMedioContacto(Utils.toString(cachedRowSet[13]));
////                    row.setLimitePostulantes(Utils.toInt(cachedRowSet[14]));
////                    row.setPostulados(Utils.toInt(cachedRowSet[15]));
////                    row.setFechaInicio(Utils.toDate(cachedRowSet[16]));
////                    row.setFechaInicioString(Utils.getFechaFormato(row.getFechaInicio()));
////                    row.setHabilidadGeneral(Utils.toString(cachedRowSet[17]));
////                    row.setExperiencia(parseExperience(Utils.toInt(cachedRowSet[18])));
////                    row.setEntidad(Utils.toString(cachedRowSet[19]));
////                    row.setMunicipio(Utils.toString(cachedRowSet[20]));
////                    row.setIdMunicipio(Utils.toLong(cachedRowSet[21]));
////                    row.setIdEmpresa(Utils.toLong(cachedRowSet[22]));
////                    row.setIdArea(Utils.toLong(cachedRowSet[23]));
////                    row.setIdSubArea(Utils.toLong(cachedRowSet[24]));
////                    rows.add(row);
////                    idAnterior = idActual;
////                } catch (Exception e) {
////                    logger.error(e);
////                }
////            }
////        } catch (Exception e) {
////            logger.error(e);
////        }
////        return rows;
////    }
////	
////	private String parseExperience(int source) {
////        StringBuilder experience = new StringBuilder();
////        switch (source) {
////            case 1:
////                experience.append(EXPERIENCIA.NINGUNA.getOpcion());
////                break;
////            case 2:
////                experience.append(EXPERIENCIA.MENOR_UNO.getOpcion());
////                break;
////            case 3:
////                experience.append(EXPERIENCIA.MENOR_DOS.getOpcion());
////                break;
////            case 4:
////                experience.append(EXPERIENCIA.MENOR_TRES.getOpcion());
////                break;
////            case 5:
////                experience.append(EXPERIENCIA.MENOR_CUATRO.getOpcion());
////                break;
////            case 6:
////                experience.append(EXPERIENCIA.MENOR_CINCO.getOpcion());
////                break;
////            case 7:
////                experience.append(EXPERIENCIA.MAS_CINCO.getOpcion());
////                break;
////            default:
////                experience.append(EXPERIENCIA.NO_REQUISITO.getOpcion());
////        }
////        return experience.toString();
////    }
////	
////	private CandidatoGradoAcademicoVO getGradoAcademicoVO(CandidatoGradoAcademico entity) {
////		CandidatoGradoAcademicoVO vo = new CandidatoGradoAcademicoVO();
////		vo.setEscuela(entity.getEscuela());
////		vo.setFechaFin(entity.getFechaFin());
////		vo.setPrincipal(entity.getPrincipal());
////		vo.setIdCandidato(entity.getIdCandidato());
////		vo.setFechaInicio(entity.getFechaInicio());
////		vo.setIdNivelEstudio(entity.getIdNivelEstudio());
////		vo.setIdSituacionAcademica(entity.getIdSituacionAcademica());
////		vo.setIdDocumentoProbatorio(entity.getIdDocumentoProbatorio());
////		vo.setIdCarreraEspecialidad(entity.getIdCarreraEspecialidad());
////		vo.setIdCandidatoGradoAcademico(entity.getIdCandidatoGradoAcademico());
////		return vo;
////	}
////	
////	private CandidatoVO find(long idCandidato) throws PersistenceException {
////		CandidatoVO vo = null;
////		try {
////			Candidato entity = entityManager.find(Candidato.class, idCandidato);
////			if (null != entity)
////				vo = getCandidatoVO(entity);
////		} catch(NoResultException e){
////			logger.error("Candidato no localizado: "+ idCandidato);
////		} catch(Exception e) {
////			throw new PersistenceException(e);
////		}
////		return vo;
////	}
////	
////	private CandidatoVO getCandidatoVO(Candidato candidato) {
////		CandidatoVO vo = new CandidatoVO();	
////		vo.setEstatus(candidato.getEstatus());
////		vo.setEstiloCv(candidato.getEstiloCv());
////		vo.setIdFuente(candidato.getIdFuente());
////		vo.setIdOficina(candidato.getIdOficina());
////		vo.setIdUsuario(candidato.getIdUsuario());
////		vo.setFechaAlta(candidato.getFechaAlta());
////		vo.setIdCandidato(candidato.getIdCandidato());
////		vo.setApoyoProspera(candidato.getApoyoProspera());
////		vo.setFechaConfirma(candidato.getFechaConfirma());
////		vo.setVeracidadDatos(candidato.getVeracidadDatos());
////		vo.setIdMedioPortal((int)candidato.getIdMedioPortal());
////		vo.setIdDiscapacidad(candidato.getIdTipoDiscapacidad());
////		vo.setAceptacionTerminos(candidato.getAceptacionTerminos());
////		vo.setConfidencialidadDatos(candidato.getConfidencialidadDatos());
////		vo.setFechaUltimaActualizacion(candidato.getFechaUltimaActualizacion());
////		vo.setIdEntidadNacimiento((int)candidato.getIdEntidadNacimiento());
////		if (null != candidato.getSolicitante()) {
////			vo.setSolicitanteVO(getSolicitanteVO(candidato.getSolicitante()));
////			vo.setEdad(Utils.calculaEdad(candidato.getSolicitante().getFechaNacimiento()));
////		}
////		vo.setCorreoElectronico(candidato.getCorreoElectronico());
////		return vo;
////	}
////	
////	private SolicitanteVO getSolicitanteVO(Solicitante entity) {
////		SolicitanteVO vo = new SolicitanteVO();
////		vo.setCurp(entity.getCurp());
////		vo.setEdad(entity.getEdad());
////		vo.setNombre(entity.getNombre());
////		vo.setGenero(entity.getGenero());
////		vo.setEstatus(entity.getEstatus());
////		vo.setIdOficina(entity.getIdOficina());
////		vo.setApellido1(entity.getApellido1());
////		vo.setApellido2(entity.getApellido2());
////		vo.setIdSolicitante(entity.getIdSolicitante());
////		vo.setFechaRegistro(entity.getFechaRegistro());
////		vo.setFechaNacimiento(entity.getFechaNacimiento());
////		vo.setCorreoElectronico(entity.getCorreoElectronico());
////		vo.setIdEntidadNacimiento(entity.getIdEntidadNacimiento());
////		vo.setFechaUltimaModificacion(entity.getFechaUltimaModificacion());
////		return vo;
////	}
////}
//
//package mx.gob.stps.portal.core.search.service.impl;
//
//
//import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_DOMINIO;
//import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA;
//import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_IDIOMAS;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.ArrayList;
//import java.util.Map;
//import java.sql.SQLException;
//
//import javax.ejb.EJB;
//import javax.ejb.Stateless;
//import javax.ejb.TransactionAttribute;
//import javax.ejb.TransactionAttributeType;
//import javax.ejb.TransactionManagement;
//import javax.ejb.TransactionManagementType;
//import javax.persistence.EntityManager;
//import javax.persistence.NoResultException;
//import javax.persistence.PersistenceContext;
//import javax.persistence.PersistenceException;
//import javax.persistence.Query;
//
//import org.apache.log4j.Logger;
//
//import mx.gob.stps.portal.persistencia.vo.CandidatoVO;
//import mx.gob.stps.portal.persistencia.vo.CatSubareaVO;
//import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
//import mx.gob.stps.portal.persistencia.vo.ExpectativaLaboralVO;
//import mx.gob.stps.portal.persistencia.vo.SolicitanteVO;
//import mx.gob.stps.portal.exception.TechnicalException;
//import mx.gob.stps.portal.persistencia.entity.Candidato;
//import mx.gob.stps.portal.persistencia.entity.CandidatoGradoAcademico;
//import mx.gob.stps.portal.persistencia.entity.ExpectativaLaboral;
//import mx.gob.stps.portal.persistencia.entity.Solicitante;
//import mx.gob.stps.portal.core.infra.utils.Constantes;
//import mx.gob.stps.portal.core.infra.utils.Utils;
//import mx.gob.stps.portal.core.infra.utils.Constantes.BOLSA_TRABAJO;
//import mx.gob.stps.portal.core.infra.utils.Constantes.EDAD_REQUISITO;
//import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
//import mx.gob.stps.portal.core.infra.utils.Constantes.EXPERIENCIA;
//import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_BUSQUEDA;
//import mx.gob.stps.portal.persistencia.vo.CandidatoGradoAcademicoVO;
//import mx.gob.stps.portal.utils.Catalogos.MULTIREGISTRO;
//import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;
//import mx.gob.stps.portal.core.persistencia.facade.CatalogoOpcionFacadeLocal;
//import mx.gob.stps.portal.core.search.ResultInfoBO;
//import mx.gob.stps.portal.core.search.service.SingleRegisterServiceRemote;
//
//@Stateless(name = "SingleRegisterService", mappedName = "SingleRegisterService")
//@TransactionManagement(TransactionManagementType.CONTAINER)
//public class SingleRegisterService implements SingleRegisterServiceRemote {
//	
//	@PersistenceContext
//	private EntityManager entityManager;
//	
//	public static final int PAGE_NUM_ROW = 10;
//	
//	@EJB
//	private CatalogoOpcionFacadeLocal catalogoOpcionFacadeLocal;
//	
//	private static Logger logger = Logger.getLogger(SingleRegisterService.class);
//	
//	@Override
//	@TransactionAttribute (value=TransactionAttributeType.NOT_SUPPORTED)
//	public List<CandidatoVO> candidatoListByIndex(List<Long> index) throws TechnicalException, SQLException {
//		List<CandidatoVO> rowsPage = new ArrayList<CandidatoVO>();
//		if (null != index && !index.isEmpty()) {
//			try {
//				for (Long id : index) {
//					CandidatoVO vo = find(id);
//					if (null != vo && vo.getIdCandidato() > 0)
//						rowsPage.add(vo);
//				}
//			}catch (Exception error) {
//				error.printStackTrace();
//			}
//		}
//		return rowsPage;
//	}
//	
//	@Override
//	public List<ExpectativaLaboralVO> expectativaLaboralList(long idCandidato) throws TechnicalException, SQLException {
//		List<ExpectativaLaboralVO> list = new ArrayList<ExpectativaLaboralVO>();
//		Query query = entityManager.createQuery("SELECT el FROM ExpectativaLaboral el WHERE el.idCandidato = :idCandidato");
//        query.setParameter("idCandidato", idCandidato);
//        try {
//        	@SuppressWarnings("unchecked")
//			List<ExpectativaLaboral> results = (List<ExpectativaLaboral>)query.getResultList();
//        	for (ExpectativaLaboral entity : results) {
//        		ExpectativaLaboralVO expectativa = getExpectativaLaboralVO(entity);
//        		if (null != expectativa && null != expectativa.getIdAreaLaboralDeseada() && null != expectativa.getIdSubAreaLaboralDeseada()) {
//        			CatSubareaVO subarea = catalogoOpcionFacadeLocal.getSubAreaVOByIdAreaIdSubArea(expectativa.getIdAreaLaboralDeseada(),
//        				expectativa.getIdSubAreaLaboralDeseada());
//        			if (null != subarea) expectativa.setPuestoDeseado(subarea.getDescripcion());
//        		}
//        		list.add(expectativa);
//        	}
//        } catch (NoResultException e) {
//            logger.error("Expectativa Laboral no localizada para el id: "+ idCandidato);
//        }
//		return list; 
//	}
//	
//	private ExpectativaLaboralVO getExpectativaLaboralVO(ExpectativaLaboral entity) {
//		if (null == entity) return null;
//		ExpectativaLaboralVO vo = new ExpectativaLaboralVO();
//		vo.setIdCandidato(entity.getIdCandidato());
//		vo.setIdExperiencia(entity.getIdExperiencia());
//		vo.setSalarioPretendido(entity.getSalarioPretendido());
//		vo.setIdExpectativaLaboral(entity.getIdExpectativaLaboral());
//		vo.setIdAreaLaboralDeseada(entity.getIdAreaLaboralDeseada());
//		if (null != entity.getPrincipal())
//			vo.setPrincipal(entity.getPrincipal().longValue());
//		vo.setIdSubAreaLaboralDeseada(entity.getIdSubAreaLaboralDeseada());
//		return vo;
//	}
//	
//	@Override
//	public List<CandidatoGradoAcademicoVO> gradoAcademicoList(long idCandidato) throws TechnicalException, SQLException {
//		List<CandidatoGradoAcademicoVO> list = new ArrayList<CandidatoGradoAcademicoVO>();
//		Query query = entityManager.createQuery("SELECT g FROM CandidatoGradoAcademico g WHERE g.idCandidato = :idCandidato");
//		query.setParameter("idCandidato", idCandidato);
//		try {
//        	@SuppressWarnings("unchecked")
//        	List<CandidatoGradoAcademico> results = (List<CandidatoGradoAcademico>)query.getResultList();
//        	for (CandidatoGradoAcademico entity : results) {
//        		CandidatoGradoAcademicoVO vo = getGradoAcademicoVO(entity);
//        		vo.setNivelEstudio(catalogoOpcionFacadeLocal.getOpcionById(Constantes.CATALOGO_OPCION_GRADO_ESTUDIOS, entity.getIdNivelEstudio()));
//        		CatalogoOpcionVO career = catalogoOpcionFacadeLocal.consultaOpcionPorCatalogosAsociados(Constantes.CATALOGO_OPCION_GRADO_ESTUDIOS, entity.getIdCarreraEspecialidad());
//        		if (null != career) vo.setCarreraEspecialidad(career.getOpcion());
//        		vo.setSituacionAcademica(catalogoOpcionFacadeLocal.getOpcionById(Constantes.CATALOGO_OPCION_ESTATUS_GRADO, entity.getIdNivelEstudio()));
//        		list.add(vo);
//        	}
//		} catch (NoResultException e) {
//			logger.error("Grado Academico no localizado para el id: "+ idCandidato);
//        }
//		return list;
//	}
//	
//	@Override
//	public List<OfertaPorCanalVO> resultInfoList(int page, List<ResultInfoBO> index) throws TechnicalException, SQLException {
//		int iteracion = 0;
//		int contadorOfertas = 0;
//		int MAX_ITERACIONES = 10;
//        boolean limpiarLista = false;
//        int pageCountIndex = PAGE_NUM_ROW * (page - 1);
//        int ultimoIndex = pageCountIndex;
//        int bloqueInicial = pageCountIndex;
//        int ultimoBloqueConOfertas = pageCountIndex;
//        TIPO_BUSQUEDA tipoBusqueda = TIPO_BUSQUEDA.BUSQUEDA_OCUPATE;
//        List<ResultInfoBO> index2Remove = new ArrayList<ResultInfoBO>();
//        List<OfertaPorCanalVO> rowsPage = new ArrayList<OfertaPorCanalVO>();
//    	if (page < 0)
//            return new ArrayList<OfertaPorCanalVO>();
//        if (index == null || index.isEmpty())
//            return new ArrayList<OfertaPorCanalVO>();
//        while (true) {
//            List<Long> ids = new ArrayList<Long>();
//            Map<Long, ResultInfoBO> resultsMap = new HashMap<Long, ResultInfoBO>();
//            if (rowsPage.size() == index.size()) { // para listas pequeñas se cumple con la cantidad solicitada
//                break;
//            }
//            if (pageCountIndex > index.size()) {
//                limpiarLista = true;
//                break;
//            }
//            if (iteracion >= MAX_ITERACIONES * 2) { // Permite doble iteracion ya que se realiza en ambos sentidos
//                limpiarLista = true;
//                break;
//            }
//            for (int i = pageCountIndex; (i < (pageCountIndex + PAGE_NUM_ROW) && i < index.size()); i++) {
//                ResultInfoBO result = index.get(i);
//                ids.add(result.getId());
//                resultsMap.put(result.getId(), result);
//            }
//            List<OfertaPorCanalVO> offerList = resultInfoList(ids);
//            if (!offerList.isEmpty()) {
//                ultimoBloqueConOfertas = pageCountIndex;
//                contadorOfertas+= offerList.size();
//                ultimoIndex = ultimoBloqueConOfertas + contadorOfertas;
//                if (resultsMap.size() > offerList.size()) {
//                    List<ResultInfoBO> faltantes = searchMissing(new ArrayList<ResultInfoBO>(resultsMap.values()), offerList);
//                    index2Remove.addAll(faltantes);
//                }
//                if (rowsPage.size() < PAGE_NUM_ROW) {
//                    int restantes = PAGE_NUM_ROW - rowsPage.size();
//                    for (int j = 0; j < restantes && j < offerList.size(); j++) {
//                        OfertaPorCanalVO oferta = offerList.get(j);
//                        if (tipoBusqueda == TIPO_BUSQUEDA.BUSQUEDA_OCUPATE) {
//                            ResultInfoBO result = resultsMap.get(oferta.getIdOfertaEmpleo());
//                            if (result != null) {
//                                oferta.setTituloOferta(result.getTitulo().replace("<b>", "<font style=\"background:#CFE297\">").replace("</b>", "</font>"));
//                                oferta.setOcupacion(result.getOcupacion());
//                                oferta.setCarreras(result.getCarreras());
//                                oferta.setConocimientos(result.getConocimientos());
//                            }
//                        }
//                        rowsPage.add(oferta);
//                    } //for
//                } //if
//            }
//            if (rowsPage.size() >= PAGE_NUM_ROW) {
//                break;
//            }
//            if (offerList.isEmpty() || offerList.size() < resultsMap.size()) {
//                iteracion++;
//                if (iteracion == MAX_ITERACIONES) { // Cuando llega al limite de iteraciones posiciona el iterador para buscar hacia atras 
//                    pageCountIndex = bloqueInicial;
//                    limpiarLista = true;
//                }
//                if (iteracion < MAX_ITERACIONES) { // Primeras iteraciones hacia delante
//                    pageCountIndex+= PAGE_NUM_ROW;
//                }
//                if (iteracion > MAX_ITERACIONES) { // Mas del limite son iteraciones hacia atras
//                    pageCountIndex-= PAGE_NUM_ROW;
//                }
//            } else if (offerList.size() == resultsMap.size()) { // Obtiene las ofertas que se solicitaron por lo tanto incrementa el contador para verificar si hay mas faltantes
//                pageCountIndex+= PAGE_NUM_ROW;
//            }
//        } //while
//        if (limpiarLista && ultimoIndex > 0 && ultimoIndex < index.size()) {
//            while (ultimoIndex < index.size()) {
//                ResultInfoBO id = index.get(ultimoIndex);
//                index2Remove.add(id);
//                ultimoIndex++;
//            }
//        }
//        for (ResultInfoBO vo : index2Remove) {
//        	index.remove(vo);
//        }
//        return rowsPage;
//	}
//	
//	private List<ResultInfoBO> searchMissing(List<ResultInfoBO> indices, List<OfertaPorCanalVO> ofertas) {
//        List<ResultInfoBO> faltantes = new ArrayList<ResultInfoBO>();
//        for (ResultInfoBO indice : indices) {
//            boolean encontrada = false;
//            for (OfertaPorCanalVO oferta : ofertas) {
//                if (oferta.getIdOfertaEmpleo() == indice.getId()) {
//                    encontrada = true;
//                    break;
//                }
//            }
//            if (!encontrada) {
//                faltantes.add(indice);
//            }
//        }
//        return faltantes;
//    }
//	
//	private List<OfertaPorCanalVO> resultInfoList(List<Long> ids) {
//        List<OfertaPorCanalVO> rows = new ArrayList<OfertaPorCanalVO>();
//        if (ids == null || ids.isEmpty()) {
//            return rows;
//        }
//        try {
//            String in = "";
//            String order = "";
//            long idAnterior = 0;
//            long idActual = 0;
//            for (int id = 0; id < ids.size(); id++) {
//                Long item = ids.get(id);
//                in+= item + ",";
//                order+= item + ", " + (id +1) + ", ";
//            }
//            if (in.endsWith(",")) {
//                in = in.substring(0, in.length() -1);
//            }
//            StringBuilder select = new StringBuilder();
//            select.append("SELECT");
//            select.append(" H.FUENTE,");
//            select.append(" H.ID_OFERTA_EMPLEO,");
//            select.append(" H.TITULO_OFERTA,");
//            select.append(" G.OPCION || ', ' || F.MUNICIPIO AS UBICACION,");
//            select.append(" CASE WHEN H.NOMBRE_EMPRESA IS NOT NULL THEN H.NOMBRE_EMPRESA WHEN H.NOMBRE_EMPRESA IS NULL AND I.NOMBRE_COMERCIAL IS NOT NULL THEN I.NOMBRE_COMERCIAL WHEN H.NOMBRE_EMPRESA IS NULL AND I.NOMBRE_COMERCIAL IS NULL THEN I.RAZON_SOCIAL END AS EMPRESA,");
//            select.append(" H.SALARIO,");
//            select.append(" NULL AS GRADO_ESTUDIO,");
//            select.append(" NULL AS CARRERA,");
//            select.append(" H.FUNCIONES,");
//            select.append(" CASE WHEN (H.EDAD_REQUISITO = " + EDAD_REQUISITO.SI.getIdOpcion() + ") THEN EDAD_MINIMA || ' - ' || EDAD_MAXIMA ELSE 'No es requisito' END AS EDAD,");
//            select.append(" CASE WHEN ((L.ID_OFERTA_EMPLEO IS NULL) OR (L.ID_IDIOMA <= 0)) THEN ' ' ELSE DESCCATALOGO(1, " + CATALOGO_OPCION_IDIOMAS + ", L.ID_IDIOMA) || ' ' || DESCCATALOGO(1, " + CATALOGO_OPCION_DOMINIO + ", L.ID_DOMINIO) END AS IDIOMA,");
//            select.append(" NULL AS HORARIO,");
//            select.append(" NULL NUMERO_PLAZAS,");
//            select.append(" NULL AS CONTACTO,");
//            select.append(" NULL LIMITE_POSTULANTES,");
//            select.append(" NULL CONTADOR,");
//            select.append(" H.FECHA_INICIO,");
//            select.append(" H.HABILIDAD_GENERAL,");
//            select.append("EXPERIENCIA_ANIOS,");
//            select.append(" G.OPCION ENTIDAD,");
//            select.append(" F.MUNICIPIO, ");
//            select.append(" F.ID_MUNICIPIO, ");
//            select.append(" I.ID_EMPRESA, ");
//            select.append(" H.ID_AREA,");
//            select.append(" H.ID_SUBAREA ");
//            select.append("FROM EMPRESA I");
//            select.append(" LEFT JOIN OFERTA_EMPLEO H ON I.ID_EMPRESA = H.ID_EMPRESA");
//            select.append(" LEFT JOIN OFERTA_CARRERA_ESPEC K ON H.ID_OFERTA_EMPLEO = K.ID_OFERTA_EMPLEO AND K.PRINCIPAL = " + MULTIREGISTRO.PRINCIPAL.getIdOpcion());
//            select.append(" LEFT JOIN OFERTA_IDIOMA L ON H.ID_OFERTA_EMPLEO = L.ID_OFERTA_EMPLEO AND L.PRINCIPAL = " + MULTIREGISTRO.PRINCIPAL.getIdOpcion());
//            select.append(" LEFT JOIN OFERTA_UBICACION D ON H.ID_OFERTA_EMPLEO = D.ID_OFERTA_EMPLEO");
//            select.append(" LEFT JOIN MUNICIPIO F ON D.ID_ENTIDAD = F.ID_ENTIDAD AND D.ID_MUNICIPIO = F.ID_MUNICIPIO");
//            select.append(" LEFT JOIN CATALOGO_OPCION G ON D.ID_ENTIDAD = G.ID_CATALOGO_OPCION AND G.ID_CATALOGO = " + CATALOGO_OPCION_ENTIDAD_FEDERATIVA + " ");
//            select.append("WHERE H.ID_OFERTA_EMPLEO IN (" + in + ") AND H.ESTATUS = " + ESTATUS.ACTIVO.getIdOpcion() + " ");
//            select.append("ORDER BY (DECODE(H.ID_OFERTA_EMPLEO, " + order + " 99))");
//            javax.persistence.Query query = entityManager.createNativeQuery(select.toString());
//            @SuppressWarnings("unchecked")
//			List<Object[]> rowSet = (List<Object[]>) query.getResultList();
//            for (Object[] cachedRowSet : rowSet) {
//                try {
//                    OfertaPorCanalVO row = new OfertaPorCanalVO();
//                    idActual = Utils.toLong(cachedRowSet[1]);
//                    if (idActual == idAnterior) {
//                        // En caso de tener ofertas repetidas
//                        continue;
//                    }
//                    int fuente = Utils.toInt(cachedRowSet[0]);
//                    if (fuente == BOLSA_TRABAJO.PORTAL_EMPLEO.getIdOpcion()) {
//                        row.setBolsaTrabajo(BOLSA_TRABAJO.PORTAL_EMPLEO.getOpcion());
//                    } else if (fuente == BOLSA_TRABAJO.TRABAJA_EN.getIdOpcion()) {
//                        row.setBolsaTrabajo(BOLSA_TRABAJO.TRABAJA_EN.getOpcion());
//                    } else if (fuente == BOLSA_TRABAJO.CANADA.getIdOpcion()) {
//                        row.setBolsaTrabajo(BOLSA_TRABAJO.CANADA.getOpcion());
//                    }
//                    row.setIdOfertaEmpleo(Utils.toLong(cachedRowSet[1]));
//                    row.setTituloOferta(Utils.toString(cachedRowSet[2]));
//                    row.setUbicacion(Utils.toString(cachedRowSet[3]));
//                    row.setEmpresa(Utils.toString(cachedRowSet[4]));
//                    row.setSalario(Utils.toDouble(cachedRowSet[5]));
//                    row.setGradoEstudio(Utils.toString(cachedRowSet[6]));
//                    row.setCarrera(Utils.toString(cachedRowSet[7]));
//                    row.setFunciones(Utils.toString(cachedRowSet[8]));
//                    row.setEdad(Utils.toString(cachedRowSet[9]));
//                    row.setIdiomas(Utils.toString(cachedRowSet[10]));
//                    row.setHorario(Utils.toString(cachedRowSet[11]));
//                    row.setNumeroPlazas(Utils.toInt(cachedRowSet[12]));
//                    row.setMedioContacto(Utils.toString(cachedRowSet[13]));
//                    row.setLimitePostulantes(Utils.toInt(cachedRowSet[14]));
//                    row.setPostulados(Utils.toInt(cachedRowSet[15]));
//                    row.setFechaInicio(Utils.toDate(cachedRowSet[16]));
//                    row.setFechaInicioString(Utils.getFechaFormato(row.getFechaInicio()));
//                    row.setHabilidadGeneral(Utils.toString(cachedRowSet[17]));
//                    row.setExperiencia(parseExperience(Utils.toInt(cachedRowSet[18])));
//                    row.setEntidad(Utils.toString(cachedRowSet[19]));
//                    row.setMunicipio(Utils.toString(cachedRowSet[20]));
//                    row.setIdMunicipio(Utils.toLong(cachedRowSet[21]));
//                    row.setIdEmpresa(Utils.toLong(cachedRowSet[22]));
//                    row.setIdArea(Utils.toLong(cachedRowSet[23]));
//                    row.setIdSubArea(Utils.toLong(cachedRowSet[24]));
//                    rows.add(row);
//                    idAnterior = idActual;
//                } catch (Exception e) {
//                    logger.error(e);
//                }
//            }
//        } catch (Exception e) {
//            logger.error(e);
//        }
//        return rows;
//    }
//	
//	private String parseExperience(int source) {
//        StringBuilder experience = new StringBuilder();
//        switch (source) {
//            case 1:
//                experience.append(EXPERIENCIA.NINGUNA.getOpcion());
//                break;
//            case 2:
//                experience.append(EXPERIENCIA.MENOR_UNO.getOpcion());
//                break;
//            case 3:
//                experience.append(EXPERIENCIA.MENOR_DOS.getOpcion());
//                break;
//            case 4:
//                experience.append(EXPERIENCIA.MENOR_TRES.getOpcion());
//                break;
//            case 5:
//                experience.append(EXPERIENCIA.MENOR_CUATRO.getOpcion());
//                break;
//            case 6:
//                experience.append(EXPERIENCIA.MENOR_CINCO.getOpcion());
//                break;
//            case 7:
//                experience.append(EXPERIENCIA.MAS_CINCO.getOpcion());
//                break;
//            default:
//                experience.append(EXPERIENCIA.NO_REQUISITO.getOpcion());
//        }
//        return experience.toString();
//    }
//	
//	private CandidatoGradoAcademicoVO getGradoAcademicoVO(CandidatoGradoAcademico entity) {
//		CandidatoGradoAcademicoVO vo = new CandidatoGradoAcademicoVO();
//		vo.setEscuela(entity.getEscuela());
//		vo.setFechaFin(entity.getFechaFin());
//		vo.setPrincipal(entity.getPrincipal());
//		vo.setIdCandidato(entity.getIdCandidato());
//		vo.setFechaInicio(entity.getFechaInicio());
//		vo.setIdNivelEstudio(entity.getIdNivelEstudio());
//		vo.setIdSituacionAcademica(entity.getIdSituacionAcademica());
//		vo.setIdDocumentoProbatorio(entity.getIdDocumentoProbatorio());
//		vo.setIdCarreraEspecialidad(entity.getIdCarreraEspecialidad());
//		vo.setIdCandidatoGradoAcademico(entity.getIdCandidatoGradoAcademico());
//		return vo;
//	}
//	
//	private CandidatoVO find(long idCandidato) throws PersistenceException {
//		CandidatoVO vo = null;
//		try {
//			Candidato entity = entityManager.find(Candidato.class, idCandidato);
//			if (null != entity)
//				vo = getCandidatoVO(entity);
//		} catch(NoResultException e){
//			logger.error("Candidato no localizado: "+ idCandidato);
//		} catch(Exception e) {
//			throw new PersistenceException(e);
//		}
//		return vo;
//	}
//	
//	private CandidatoVO getCandidatoVO(Candidato candidato) {
//		CandidatoVO vo = new CandidatoVO();	
//		vo.setEstatus(candidato.getEstatus());
//		vo.setEstiloCv(candidato.getEstiloCv());
//		vo.setIdFuente(candidato.getIdFuente());
//		vo.setIdOficina(candidato.getIdOficina());
//		vo.setIdUsuario(candidato.getIdUsuario());
//		vo.setFechaAlta(candidato.getFechaAlta());
//		vo.setIdCandidato(candidato.getIdCandidato());
//		vo.setApoyoProspera(candidato.getApoyoProspera());
//		vo.setFechaConfirma(candidato.getFechaConfirma());
//		vo.setVeracidadDatos(candidato.getVeracidadDatos());
//		vo.setIdMedioPortal((int)candidato.getIdMedioPortal());
//		vo.setIdDiscapacidad(candidato.getIdTipoDiscapacidad());
//		vo.setAceptacionTerminos(candidato.getAceptacionTerminos());
//		vo.setConfidencialidadDatos(candidato.getConfidencialidadDatos());
//		vo.setFechaUltimaActualizacion(candidato.getFechaUltimaActualizacion());
//		vo.setIdEntidadNacimiento((int)candidato.getIdEntidadNacimiento());
//		if (null != candidato.getSolicitante()) {
//			vo.setSolicitanteVO(getSolicitanteVO(candidato.getSolicitante()));
//			vo.setEdad(Utils.calculaEdad(candidato.getSolicitante().getFechaNacimiento()));
//		}
//		vo.setCorreoElectronico(candidato.getCorreoElectronico());
//		return vo;
//	}
//	
//	private SolicitanteVO getSolicitanteVO(Solicitante entity) {
//		SolicitanteVO vo = new SolicitanteVO();
//		vo.setCurp(entity.getCurp());
//		vo.setEdad(entity.getEdad());
//		vo.setNombre(entity.getNombre());
//		vo.setGenero(entity.getGenero());
//		vo.setEstatus(entity.getEstatus());
//		vo.setIdOficina(entity.getIdOficina());
//		vo.setApellido1(entity.getApellido1());
//		vo.setApellido2(entity.getApellido2());
//		vo.setIdSolicitante(entity.getIdSolicitante());
//		vo.setFechaRegistro(entity.getFechaRegistro());
//		vo.setFechaNacimiento(entity.getFechaNacimiento());
//		vo.setCorreoElectronico(entity.getCorreoElectronico());
//		vo.setIdEntidadNacimiento(entity.getIdEntidadNacimiento());
//		vo.setFechaUltimaModificacion(entity.getFechaUltimaModificacion());
//		return vo;
//	}
//}

package mx.gob.stps.portal.core.search.service.impl;


import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_DOMINIO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_IDIOMAS;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.sql.SQLException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import mx.gob.stps.portal.persistencia.vo.CandidatoVO;
import mx.gob.stps.portal.persistencia.vo.CatSubareaVO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.persistencia.vo.ExpectativaLaboralVO;
import mx.gob.stps.portal.persistencia.vo.SolicitanteVO;
import mx.gob.stps.portal.exception.TechnicalException;
import mx.gob.stps.portal.persistencia.entity.Candidato;
import mx.gob.stps.portal.persistencia.entity.CandidatoGradoAcademico;
import mx.gob.stps.portal.persistencia.entity.ExpectativaLaboral;
import mx.gob.stps.portal.persistencia.entity.Solicitante;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.infra.utils.Constantes.BOLSA_TRABAJO;
import mx.gob.stps.portal.core.infra.utils.Constantes.EDAD_REQUISITO;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.EXPERIENCIA;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_BUSQUEDA;
import mx.gob.stps.portal.persistencia.vo.CandidatoGradoAcademicoVO;
import mx.gob.stps.portal.utils.Catalogos.MULTIREGISTRO;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;
import mx.gob.stps.portal.core.persistencia.facade.CatalogoOpcionFacadeLocal;
import mx.gob.stps.portal.core.search.ResultInfoBO;
import mx.gob.stps.portal.core.search.service.SingleRegisterServiceRemote;

@Stateless(name = "SingleRegisterService", mappedName = "SingleRegisterService")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class SingleRegisterService implements SingleRegisterServiceRemote {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public static final int PAGE_NUM_ROW = 10;
	
	@EJB
	private CatalogoOpcionFacadeLocal catalogoOpcionFacadeLocal;
	
	private static Logger logger = Logger.getLogger(SingleRegisterService.class);
	
	@Override
	@TransactionAttribute (value=TransactionAttributeType.NOT_SUPPORTED)
	public List<CandidatoVO> candidatoListByIndex(List<Long> index) throws TechnicalException, SQLException {
		List<CandidatoVO> rowsPage = new ArrayList<CandidatoVO>();
		if (null != index && !index.isEmpty()) {
			try {
				for (Long id : index) {
					CandidatoVO vo = find(id);
					if (null != vo && vo.getIdCandidato() > 0)
						rowsPage.add(vo);
				}
			}catch (Exception error) {
				error.printStackTrace();
			}
		}
		return rowsPage;
	}
	
	@Override
	public List<ExpectativaLaboralVO> expectativaLaboralList(long idCandidato) throws TechnicalException, SQLException {
		List<ExpectativaLaboralVO> list = new ArrayList<ExpectativaLaboralVO>();
		Query query = entityManager.createQuery("SELECT el FROM ExpectativaLaboral el WHERE el.idCandidato = :idCandidato");
        query.setParameter("idCandidato", idCandidato);
        try {
        	@SuppressWarnings("unchecked")
			List<ExpectativaLaboral> results = (List<ExpectativaLaboral>)query.getResultList();
        	for (ExpectativaLaboral entity : results) {
        		ExpectativaLaboralVO expectativa = getExpectativaLaboralVO(entity);
        		if (null != expectativa && null != expectativa.getIdAreaLaboralDeseada() && null != expectativa.getIdSubAreaLaboralDeseada()) {
        			CatSubareaVO subarea = catalogoOpcionFacadeLocal.getSubAreaVOByIdAreaIdSubArea(expectativa.getIdAreaLaboralDeseada(),
        				expectativa.getIdSubAreaLaboralDeseada());
        			if (null != subarea) expectativa.setPuestoDeseado(subarea.getDescripcion());
        		}
        		list.add(expectativa);
        	}
        } catch (NoResultException e) {
            logger.error("Expectativa Laboral no localizada para el id: "+ idCandidato);
        }
		return list; 
	}
	
	private ExpectativaLaboralVO getExpectativaLaboralVO(ExpectativaLaboral entity) {
		if (null == entity) return null;
		ExpectativaLaboralVO vo = new ExpectativaLaboralVO();
		vo.setIdCandidato(entity.getIdCandidato());
		vo.setIdExperiencia(entity.getIdExperiencia());
		vo.setSalarioPretendido(entity.getSalarioPretendido());
		vo.setIdExpectativaLaboral(entity.getIdExpectativaLaboral());
		vo.setIdAreaLaboralDeseada(entity.getIdAreaLaboralDeseada());
		if (null != entity.getPrincipal())
			vo.setPrincipal(entity.getPrincipal().longValue());
		vo.setIdSubAreaLaboralDeseada(entity.getIdSubAreaLaboralDeseada());
		return vo;
	}
	
	@Override
	public List<CandidatoGradoAcademicoVO> gradoAcademicoList(long idCandidato) throws TechnicalException, SQLException {
		List<CandidatoGradoAcademicoVO> list = new ArrayList<CandidatoGradoAcademicoVO>();
		Query query = entityManager.createQuery("SELECT g FROM CandidatoGradoAcademico g WHERE g.idCandidato = :idCandidato");
		query.setParameter("idCandidato", idCandidato);
		try {
        	@SuppressWarnings("unchecked")
        	List<CandidatoGradoAcademico> results = (List<CandidatoGradoAcademico>)query.getResultList();
        	for (CandidatoGradoAcademico entity : results) {
        		CandidatoGradoAcademicoVO vo = getGradoAcademicoVO(entity);
        		vo.setNivelEstudio(catalogoOpcionFacadeLocal.getOpcionById(Constantes.CATALOGO_OPCION_GRADO_ESTUDIOS, entity.getIdNivelEstudio()));
        		CatalogoOpcionVO career = catalogoOpcionFacadeLocal.consultaOpcionPorCatalogosAsociados(Constantes.CATALOGO_OPCION_GRADO_ESTUDIOS, entity.getIdCarreraEspecialidad());
        		if (null != career) vo.setCarreraEspecialidad(career.getOpcion());
        		vo.setSituacionAcademica(catalogoOpcionFacadeLocal.getOpcionById(Constantes.CATALOGO_OPCION_ESTATUS_GRADO, entity.getIdNivelEstudio()));
        		list.add(vo);
        	}
		} catch (NoResultException e) {
			logger.error("Grado Academico no localizado para el id: "+ idCandidato);
        }
		return list;
	}
	
	@Override
	public List<OfertaPorCanalVO> resultInfoList(int page, List<ResultInfoBO> index) throws TechnicalException, SQLException {
		int iteracion = 0;
		int contadorOfertas = 0;
		int MAX_ITERACIONES = 10;
        boolean limpiarLista = false;
        int pageCountIndex = PAGE_NUM_ROW * (page - 1);
        int ultimoIndex = pageCountIndex;
        int bloqueInicial = pageCountIndex;
        int ultimoBloqueConOfertas = pageCountIndex;
        TIPO_BUSQUEDA tipoBusqueda = TIPO_BUSQUEDA.BUSQUEDA_OCUPATE;
        List<ResultInfoBO> index2Remove = new ArrayList<ResultInfoBO>();
        List<OfertaPorCanalVO> rowsPage = new ArrayList<OfertaPorCanalVO>();
    	if (page < 0)
            return new ArrayList<OfertaPorCanalVO>();
        if (index == null || index.isEmpty())
            return new ArrayList<OfertaPorCanalVO>();
        while (true) {
            List<Long> ids = new ArrayList<Long>();
            Map<Long, ResultInfoBO> resultsMap = new HashMap<Long, ResultInfoBO>();
            if (rowsPage.size() == index.size()) { // para listas pequeñas se cumple con la cantidad solicitada
                break;
            }
            if (pageCountIndex > index.size()) {
                limpiarLista = true;
                break;
            }
            if (iteracion >= MAX_ITERACIONES * 2) { // Permite doble iteracion ya que se realiza en ambos sentidos
                limpiarLista = true;
                break;
            }
            for (int i = pageCountIndex; (i < (pageCountIndex + PAGE_NUM_ROW) && i < index.size()); i++) {
                ResultInfoBO result = index.get(i);
                ids.add(result.getId());
                resultsMap.put(result.getId(), result);
            }
            List<OfertaPorCanalVO> offerList = resultInfoList(ids);
            if (!offerList.isEmpty()) {
                ultimoBloqueConOfertas = pageCountIndex;
                contadorOfertas+= offerList.size();
                ultimoIndex = ultimoBloqueConOfertas + contadorOfertas;
                if (resultsMap.size() > offerList.size()) {
                    List<ResultInfoBO> faltantes = searchMissing(new ArrayList<ResultInfoBO>(resultsMap.values()), offerList);
                    index2Remove.addAll(faltantes);
                }
                if (rowsPage.size() < PAGE_NUM_ROW) {
                    int restantes = PAGE_NUM_ROW - rowsPage.size();
                    for (int j = 0; j < restantes && j < offerList.size(); j++) {
                        OfertaPorCanalVO oferta = offerList.get(j);
                        if (tipoBusqueda == TIPO_BUSQUEDA.BUSQUEDA_OCUPATE) {
                            ResultInfoBO result = resultsMap.get(oferta.getIdOfertaEmpleo());
                            if (result != null) {
                                oferta.setTituloOferta(result.getTitulo().replace("<b>", "<font style=\"background:#CFE297\">").replace("</b>", "</font>"));
                                oferta.setOcupacion(result.getOcupacion());
                                oferta.setCarreras(result.getCarreras());
                                oferta.setConocimientos(result.getConocimientos());
                            }
                        }
                        rowsPage.add(oferta);
                    } //for
                } //if
            }
            if (rowsPage.size() >= PAGE_NUM_ROW) {
                break;
            }
            if (offerList.isEmpty() || offerList.size() < resultsMap.size()) {
                iteracion++;
                if (iteracion == MAX_ITERACIONES) { // Cuando llega al limite de iteraciones posiciona el iterador para buscar hacia atras 
                    pageCountIndex = bloqueInicial;
                    limpiarLista = true;
                }
                if (iteracion < MAX_ITERACIONES) { // Primeras iteraciones hacia delante
                    pageCountIndex+= PAGE_NUM_ROW;
                }
                if (iteracion > MAX_ITERACIONES) { // Mas del limite son iteraciones hacia atras
                    pageCountIndex-= PAGE_NUM_ROW;
                }
            } else if (offerList.size() == resultsMap.size()) { // Obtiene las ofertas que se solicitaron por lo tanto incrementa el contador para verificar si hay mas faltantes
                pageCountIndex+= PAGE_NUM_ROW;
            }
        } //while
        if (limpiarLista && ultimoIndex > 0 && ultimoIndex < index.size()) {
            while (ultimoIndex < index.size()) {
                ResultInfoBO id = index.get(ultimoIndex);
                index2Remove.add(id);
                ultimoIndex++;
            }
        }
        for (ResultInfoBO vo : index2Remove) {
        	index.remove(vo);
        }
        return rowsPage;
	}
	
	private List<ResultInfoBO> searchMissing(List<ResultInfoBO> indices, List<OfertaPorCanalVO> ofertas) {
        List<ResultInfoBO> faltantes = new ArrayList<ResultInfoBO>();
        for (ResultInfoBO indice : indices) {
            boolean encontrada = false;
            for (OfertaPorCanalVO oferta : ofertas) {
                if (oferta.getIdOfertaEmpleo() == indice.getId()) {
                    encontrada = true;
                    break;
                }
            }
            if (!encontrada) {
                faltantes.add(indice);
            }
        }
        return faltantes;
    }
	
	private List<OfertaPorCanalVO> resultInfoList(List<Long> ids) {
        List<OfertaPorCanalVO> rows = new ArrayList<OfertaPorCanalVO>();
        if (ids == null || ids.isEmpty()) {
            return rows;
        }
        try {
            String in = "";
            String order = "";
            long idAnterior = 0;
            long idActual = 0;
            for (int id = 0; id < ids.size(); id++) {
                Long item = ids.get(id);
                in+= item + ",";
                order+= item + ", " + (id +1) + ", ";
            }
            if (in.endsWith(",")) {
                in = in.substring(0, in.length() -1);
            }
            StringBuilder select = new StringBuilder();
            select.append("SELECT");
            select.append(" H.FUENTE,");
            select.append(" H.ID_OFERTA_EMPLEO,");
            select.append(" H.TITULO_OFERTA,");
            select.append(" G.OPCION || ', ' || F.MUNICIPIO AS UBICACION,");
            select.append(" CASE WHEN H.NOMBRE_EMPRESA IS NOT NULL THEN H.NOMBRE_EMPRESA WHEN H.NOMBRE_EMPRESA IS NULL AND I.NOMBRE_COMERCIAL IS NOT NULL THEN I.NOMBRE_COMERCIAL WHEN H.NOMBRE_EMPRESA IS NULL AND I.NOMBRE_COMERCIAL IS NULL THEN I.RAZON_SOCIAL END AS EMPRESA,");
            select.append(" H.SALARIO,");
            select.append(" NULL AS GRADO_ESTUDIO,");
            select.append(" NULL AS CARRERA,");
            select.append(" H.FUNCIONES,");
            select.append(" CASE WHEN (H.EDAD_REQUISITO = " + EDAD_REQUISITO.SI.getIdOpcion() + ") THEN EDAD_MINIMA || ' - ' || EDAD_MAXIMA ELSE 'No es requisito' END AS EDAD,");
            select.append(" CASE WHEN ((L.ID_OFERTA_EMPLEO IS NULL) OR (L.ID_IDIOMA <= 0)) THEN ' ' ELSE DESCCATALOGO(1, " + CATALOGO_OPCION_IDIOMAS + ", L.ID_IDIOMA) || ' ' || DESCCATALOGO(1, " + CATALOGO_OPCION_DOMINIO + ", L.ID_DOMINIO) END AS IDIOMA,");
            select.append(" NULL AS HORARIO,");
            select.append(" NULL NUMERO_PLAZAS,");
            select.append(" NULL AS CONTACTO,");
            select.append(" NULL LIMITE_POSTULANTES,");
            select.append(" NULL CONTADOR,");
            select.append(" H.FECHA_INICIO,");
            select.append(" H.HABILIDAD_GENERAL,");
            select.append("EXPERIENCIA_ANIOS,");
            select.append(" G.OPCION ENTIDAD,");
            select.append(" F.MUNICIPIO, ");
            select.append(" F.ID_MUNICIPIO, ");
            select.append(" I.ID_EMPRESA, ");
            select.append(" H.ID_AREA,");
            select.append(" H.ID_SUBAREA ");
            select.append("FROM EMPRESA I");
            select.append(" LEFT JOIN OFERTA_EMPLEO H ON I.ID_EMPRESA = H.ID_EMPRESA");
            select.append(" LEFT JOIN OFERTA_CARRERA_ESPEC K ON H.ID_OFERTA_EMPLEO = K.ID_OFERTA_EMPLEO AND K.PRINCIPAL = " + MULTIREGISTRO.PRINCIPAL.getIdOpcion());
            select.append(" LEFT JOIN OFERTA_IDIOMA L ON H.ID_OFERTA_EMPLEO = L.ID_OFERTA_EMPLEO AND L.PRINCIPAL = " + MULTIREGISTRO.PRINCIPAL.getIdOpcion());
            select.append(" LEFT JOIN OFERTA_UBICACION D ON H.ID_OFERTA_EMPLEO = D.ID_OFERTA_EMPLEO");
            select.append(" LEFT JOIN MUNICIPIO F ON D.ID_ENTIDAD = F.ID_ENTIDAD AND D.ID_MUNICIPIO = F.ID_MUNICIPIO");
            select.append(" LEFT JOIN CATALOGO_OPCION G ON D.ID_ENTIDAD = G.ID_CATALOGO_OPCION AND G.ID_CATALOGO = " + CATALOGO_OPCION_ENTIDAD_FEDERATIVA + " ");
            select.append("WHERE H.ID_OFERTA_EMPLEO IN (" + in + ") AND H.ESTATUS = " + ESTATUS.ACTIVO.getIdOpcion() + " ");
            select.append("ORDER BY (DECODE(H.ID_OFERTA_EMPLEO, " + order + " 99))");
            javax.persistence.Query query = entityManager.createNativeQuery(select.toString());
            @SuppressWarnings("unchecked")
			List<Object[]> rowSet = (List<Object[]>) query.getResultList();
            for (Object[] cachedRowSet : rowSet) {
                try {
                    OfertaPorCanalVO row = new OfertaPorCanalVO();
                    idActual = Utils.toLong(cachedRowSet[1]);
                    if (idActual == idAnterior) {
                        // En caso de tener ofertas repetidas
                        continue;
                    }
                    int fuente = Utils.toInt(cachedRowSet[0]);
                    if (fuente == BOLSA_TRABAJO.PORTAL_EMPLEO.getIdOpcion()) {
                        row.setBolsaTrabajo(BOLSA_TRABAJO.PORTAL_EMPLEO.getOpcion());
                    } else if (fuente == BOLSA_TRABAJO.TRABAJA_EN.getIdOpcion()) {
                        row.setBolsaTrabajo(BOLSA_TRABAJO.TRABAJA_EN.getOpcion());
                    } else if (fuente == BOLSA_TRABAJO.CANADA.getIdOpcion()) {
                        row.setBolsaTrabajo(BOLSA_TRABAJO.CANADA.getOpcion());
                    }
                    row.setIdOfertaEmpleo(Utils.toLong(cachedRowSet[1]));
                    row.setTituloOferta(Utils.toString(cachedRowSet[2]));
                    row.setUbicacion(Utils.toString(cachedRowSet[3]));
                    row.setEmpresa(Utils.toString(cachedRowSet[4]));
                    row.setSalario(Utils.toDouble(cachedRowSet[5]));
                    row.setGradoEstudio(Utils.toString(cachedRowSet[6]));
                    row.setCarrera(Utils.toString(cachedRowSet[7]));
                    row.setFunciones(Utils.toString(cachedRowSet[8]));
                    row.setEdad(Utils.toString(cachedRowSet[9]));
                    row.setIdiomas(Utils.toString(cachedRowSet[10]));
                    row.setHorario(Utils.toString(cachedRowSet[11]));
                    row.setNumeroPlazas(Utils.toInt(cachedRowSet[12]));
                    row.setMedioContacto(Utils.toString(cachedRowSet[13]));
                    row.setLimitePostulantes(Utils.toInt(cachedRowSet[14]));
                    row.setPostulados(Utils.toInt(cachedRowSet[15]));
                    row.setFechaInicio(Utils.toDate(cachedRowSet[16]));
                    row.setFechaInicioString(Utils.getFechaFormato(row.getFechaInicio()));
                    row.setHabilidadGeneral(Utils.toString(cachedRowSet[17]));
                    row.setExperiencia(parseExperience(Utils.toInt(cachedRowSet[18])));
                    row.setEntidad(Utils.toString(cachedRowSet[19]));
                    row.setMunicipio(Utils.toString(cachedRowSet[20]));
                    row.setIdMunicipio(Utils.toLong(cachedRowSet[21]));
                    row.setIdEmpresa(Utils.toLong(cachedRowSet[22]));
                    row.setIdArea(Utils.toLong(cachedRowSet[23]));
                    row.setIdSubArea(Utils.toLong(cachedRowSet[24]));
                    rows.add(row);
                    idAnterior = idActual;
                } catch (Exception e) {
                    logger.error(e);
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return rows;
    }
	
	private String parseExperience(int source) {
        StringBuilder experience = new StringBuilder();
        switch (source) {
            case 1:
                experience.append(EXPERIENCIA.NINGUNA.getOpcion());
                break;
            case 2:
                experience.append(EXPERIENCIA.MENOR_UNO.getOpcion());
                break;
            case 3:
                experience.append(EXPERIENCIA.MENOR_DOS.getOpcion());
                break;
            case 4:
                experience.append(EXPERIENCIA.MENOR_TRES.getOpcion());
                break;
            case 5:
                experience.append(EXPERIENCIA.MENOR_CUATRO.getOpcion());
                break;
            case 6:
                experience.append(EXPERIENCIA.MENOR_CINCO.getOpcion());
                break;
            case 7:
                experience.append(EXPERIENCIA.MAS_CINCO.getOpcion());
                break;
            default:
                experience.append(EXPERIENCIA.NO_REQUISITO.getOpcion());
        }
        return experience.toString();
    }
	
	private CandidatoGradoAcademicoVO getGradoAcademicoVO(CandidatoGradoAcademico entity) {
		CandidatoGradoAcademicoVO vo = new CandidatoGradoAcademicoVO();
		vo.setEscuela(entity.getEscuela());
		vo.setFechaFin(entity.getFechaFin());
		vo.setPrincipal(entity.getPrincipal());
		vo.setIdCandidato(entity.getIdCandidato());
		vo.setFechaInicio(entity.getFechaInicio());
		vo.setIdNivelEstudio(entity.getIdNivelEstudio());
		vo.setIdSituacionAcademica(entity.getIdSituacionAcademica());
		vo.setIdDocumentoProbatorio(entity.getIdDocumentoProbatorio());
		vo.setIdCarreraEspecialidad(entity.getIdCarreraEspecialidad());
		vo.setIdCandidatoGradoAcademico(entity.getIdCandidatoGradoAcademico());
		return vo;
	}
	
	private CandidatoVO find(long idCandidato) throws PersistenceException {
		CandidatoVO vo = null;
		try {
			Candidato entity = entityManager.find(Candidato.class, idCandidato);
			if (null != entity)
				vo = getCandidatoVO(entity);
		} catch(NoResultException e){
			logger.error("Candidato no localizado: "+ idCandidato);
		} catch(Exception e) {
			throw new PersistenceException(e);
		}
		return vo;
	}
	
	private CandidatoVO getCandidatoVO(Candidato candidato) {
		CandidatoVO vo = new CandidatoVO();	
		vo.setEstatus(candidato.getEstatus());
		vo.setEstiloCv(candidato.getEstiloCv());
		vo.setIdFuente(candidato.getIdFuente());
		vo.setIdOficina(candidato.getIdOficina());
		vo.setIdUsuario(candidato.getIdUsuario());
		vo.setFechaAlta(candidato.getFechaAlta());
		vo.setIdCandidato(candidato.getIdCandidato());
		vo.setApoyoProspera(candidato.getApoyoProspera());
		vo.setFechaConfirma(candidato.getFechaConfirma());
		vo.setVeracidadDatos(candidato.getVeracidadDatos());
		vo.setIdMedioPortal((int)candidato.getIdMedioPortal());
		vo.setIdDiscapacidad(candidato.getIdTipoDiscapacidad());
		vo.setAceptacionTerminos(candidato.getAceptacionTerminos());
		vo.setConfidencialidadDatos(candidato.getConfidencialidadDatos());
		vo.setFechaUltimaActualizacion(candidato.getFechaUltimaActualizacion());
		vo.setIdEntidadNacimiento((int)candidato.getIdEntidadNacimiento());
		if (null != candidato.getSolicitante()) {
			vo.setSolicitanteVO(getSolicitanteVO(candidato.getSolicitante()));
			vo.setEdad(Utils.calculaEdad(candidato.getSolicitante().getFechaNacimiento()));
		}
		vo.setCorreoElectronico(candidato.getCorreoElectronico());
		return vo;
	}
	
	private SolicitanteVO getSolicitanteVO(Solicitante entity) {
		SolicitanteVO vo = new SolicitanteVO();
		vo.setCurp(entity.getCurp());
		vo.setEdad(entity.getEdad());
		vo.setNombre(entity.getNombre());
		vo.setGenero(entity.getGenero());
		vo.setEstatus(entity.getEstatus());
		vo.setIdOficina(entity.getIdOficina());
		vo.setApellido1(entity.getApellido1());
		vo.setApellido2(entity.getApellido2());
		vo.setIdSolicitante(entity.getIdSolicitante());
		vo.setFechaRegistro(entity.getFechaRegistro());
		vo.setFechaNacimiento(entity.getFechaNacimiento());
		vo.setCorreoElectronico(entity.getCorreoElectronico());
		vo.setIdEntidadNacimiento(entity.getIdEntidadNacimiento());
		vo.setFechaUltimaModificacion(entity.getFechaUltimaModificacion());
		return vo;
	}
}