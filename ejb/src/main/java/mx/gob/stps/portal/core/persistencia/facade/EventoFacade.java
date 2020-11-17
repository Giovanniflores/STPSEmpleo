package mx.gob.stps.portal.core.persistencia.facade;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
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
import javax.persistence.TypedQuery;

import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.exception.BusinessException;
import mx.gob.stps.portal.exception.TechnicalException;
import mx.gob.stps.portal.persistencia.entity.CandidatoEvento;
import mx.gob.stps.portal.persistencia.entity.CandidatoEventoPk;
import mx.gob.stps.portal.persistencia.entity.Evento;
import mx.gob.stps.portal.persistencia.entity.EventoFolio;
import mx.gob.stps.portal.persistencia.vo.ComprobanteRegistroEventoCandidatoVO;
import mx.gob.stps.portal.persistencia.vo.EmpresaEventoVO;
import mx.gob.stps.portal.persistencia.vo.EventoVO;
import mx.gob.stps.portal.persistencia.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.utils.converter.Converter;

import org.apache.log4j.Logger;

@Stateless
public class EventoFacade implements EventoFacadeLocal{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private static final Logger logger = Logger.getLogger(EventoFacade.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EventoVO> obtieneEventosFerias(Integer idEntidad)
			throws TechnicalException {
		List<EventoVO> eventos = new ArrayList<EventoVO>();
		StringBuilder query = new StringBuilder();
		List<Object> datos = new ArrayList<Object>();
		query.append(" SELECT E.ID_EVENTO,E.EVENTO,E.ESTATUS,E.SEDE,E.FECHA_INICIO,E.ID_AMBIENTE,E.ID_ENTIDAD, ");
		query.append(" (select municipio from municipio where ID_ENTIDAD = E.ID_ENTIDAD and id_municipio = E.ID_MUNICIPIO)municipio, ");
		query.append(" E.CALLE,E.ENTRE_CALLE,E.Y_CALLE,E.NUMERO_EXTERIOR,E.NUMERO_INTERIOR, ");
		query.append(" E.LOCALIDAD,E.CODIGO_POSTAL,E.REFERENCIA_DOMICILIO, ");
		query.append(" (SELECT COLONIA FROM CODIGO_POSTAL WHERE ID_COLONIA = E.ID_COLONIA)COLONIA,  ");
		query.append(" E.HORA_ATENCION_INICIO, E.HORA_ATENCION_FIN ");
		query.append(" FROM EVENTO E ");
		query.append(" WHERE TRUNC(FECHA_INICIO)- TRUNC(SYSDATE) <= 20 ");
		query.append(" AND TRUNC(FECHA_INICIO)- TRUNC(SYSDATE)>= 0 ");
		if(idEntidad != null)
			query.append(" AND E.ID_ENTIDAD = ?1 ");
		query.append(" AND E.ESTATUS IN(").append(Catalogos.ESTATUS.FERIA_VALIDADO_CGSNE.getIdOpcion()).append(",").append(Catalogos.ESTATUS.ACTIVO.getIdOpcion()).append(") ");
		query.append(" ORDER BY E.FECHA_INICIO ");
		Query q = entityManager.createNativeQuery(query.toString());
		if(idEntidad != null){
			if(idEntidad > 0)
				q.setParameter("1",idEntidad);
		}
		try{
			datos = q.getResultList();
			for(Object dato : datos){
				eventos.add(getEventoFeria(dato));
			}
		}catch(NoResultException e){
			return null;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return eventos;
	}
	
	public EventoVO getEventoFeria(Object obj){
		Object[] o = (Object[]) obj;
		EventoVO evento = new EventoVO();
		SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
		evento.setIdEvento(((BigDecimal) o[0]).longValue());
		evento.setEvento((String) o[1]);
		evento.setEstatus(((BigDecimal) o[2]).intValue());
		evento.setSede((String) o[3]);
		evento.setFechaInicio((Date)o[4]);
		evento.setFechaFin((Date)o[4]);
		evento.setFechaE(sd.format((Date)o[4]));
		evento.setIdAmbiente(((BigDecimal) o[5]).intValue());
		evento.setAmbiente(Catalogos.TIPO_AMBIENTE.getDescripcion(evento.getIdAmbiente()));
		evento.setIdEntidad(((BigDecimal) o[6]).intValue());
		evento.setEntidad(Catalogos.ENTIDADES_FEDERATIVAS.getDescripcion(evento.getIdEntidad()));
		evento.setEstatusDesc(Catalogos.ESTATUS.getDescripcion(evento.getEstatus()));
		evento.setMunicipio((String) o[7]);
		evento.setCalle((String) o[8]);
		evento.setEntreCalle((String) o[9]);
		evento.setyCalle((String) o[10]);
		evento.setNumeroExterior((String)o[11]);
		evento.setNumeroInterior((String)o[12]);
		evento.setLocalidad((String) o[13]);
		evento.setCodigoPostal((String) o[14]);
		evento.setReferenciaDomicilio((String)o[15]);
		evento.setColonia((String) o[16]);
		evento.setHoraAtencionInicio((String) o[17]);
		evento.setHoraAtencionFin((String) o[18]);
		return evento;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmpresaEventoVO> consultarEmpresasEvento(Long idEvento)
			throws NoResultException, TechnicalException {
		List<EmpresaEventoVO> empresasEvento = new ArrayList<EmpresaEventoVO>();
		List<Object> datos = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();
		query.append(" SELECT SUM(O.NUMERO_PLAZAS)PLAZAS,EM.RAZON_SOCIAL, EM.ID_EMPRESA,EM.ID_TIPO_PERSONA,EM.NOMBRE,EM.APELLIDO1,EM.APELLIDO2,EM.ID_TIPO_EMPRESA ");
		query.append(" FROM OFERTA_EMPLEO_EVENTO OV ");
		query.append(" INNER JOIN OFERTA_EMPLEO O ");
		query.append(" ON (OV.ID_OFERTA_EMPLEO = O.ID_OFERTA_EMPLEO AND O.ESTATUS IN( ").append(Catalogos.ESTATUS.ACTIVO.getIdOpcion()).append(", ").append(Catalogos.ESTATUS.FERIA_PUBLICADA_SOLO_FERIAS.getIdOpcion()).append(")) ");
		query.append(" INNER JOIN EMPRESA EM ");
		query.append(" ON O.ID_EMPRESA = EM.ID_EMPRESA ");
		//query.append(" WHERE ROWNUM < 11 ");
		if(idEvento != null)
			query.append(" WHERE OV.ID_EVENTO = ?1 ");
		query.append(" GROUP BY EM.RAZON_SOCIAL, EM.ID_EMPRESA,EM.ID_TIPO_PERSONA,EM.NOMBRE,EM.APELLIDO1,EM.APELLIDO2,EM.ID_TIPO_EMPRESA ");
		query.append(" ORDER BY PLAZAS DESC ");
		Query q = entityManager.createNativeQuery(query.toString());
		if(idEvento != null)
			q.setParameter("1", idEvento);
		try{
			datos = q.getResultList();
			if(!datos.isEmpty()){
				if(datos.size()>10){
					for(int i = 0;i<10;i++){
						empresasEvento.add(getEmpresasEvento(datos.get(i)));
					}
				}else{
					for(Object obj : datos){
						empresasEvento.add(getEmpresasEvento(obj));
					}
				}
				
			}
		}catch(NoResultException e){
			return null;
		}catch(Exception e){
			e.printStackTrace();
			throw new TechnicalException("hubo un problema con la consulta.");
		}
		return empresasEvento;
	}
	
	public EmpresaEventoVO getEmpresasEvento(Object obj){
		Object[] o = (Object[]) obj;
		
		EmpresaEventoVO empresaEv = new EmpresaEventoVO();
		empresaEv.setNoPlazasEvento(((BigDecimal) o[0]).longValue());
		/*if(((BigDecimal)o[7]).intValue() == 1 || ((BigDecimal)o[7]).intValue() == 3){
			empresaEv.setNombreEmpresa((String) o[1]);
		}else if(((BigDecimal)o[7]).intValue() == 2){
			if( ((BigDecimal)o[3]).intValue() == 2){
				empresaEv.setNombreEmpresa((String) o[1]);
			}else if( ((BigDecimal)o[3]).intValue() == 1){
				StringBuilder nombre = new StringBuilder();
				if(o[4]!= null)
					nombre.append((String) o[4]).append(" ");
				if(o[5] != null)
					nombre.append((String) o[5]).append(" ");
				if(o[6] != null)
					nombre.append((String)o[6]);
				empresaEv.setNombreEmpresa(nombre.toString());
			}
		}*/
		if((String) o[1] != null){
			empresaEv.setNombreEmpresa((String) o[1]);
		}else if(o[4]!= null ||  o[5] != null){
			StringBuilder nombre = new StringBuilder();
			if(o[4]!= null)
				nombre.append((String) o[4]).append(" ");
			if(o[5] != null)
				nombre.append((String) o[5]).append(" ");
			if(o[6] != null)
				nombre.append((String)o[6]);
			empresaEv.setNombreEmpresa(nombre.toString());
		}else{
			empresaEv.setNombreEmpresa("NO DISPONIBLE");
		} 
		empresaEv.setIdEmpresa(((BigDecimal)o[2]).longValue());
		return empresaEv;
	}

	@Override
	public CandidatoEvento obtieneEventosCandidato(Long idCandidato,
			Long idEvento) throws NoResultException, TechnicalException {
		CandidatoEvento candidatoEvento = new CandidatoEvento();
		StringBuilder query = new StringBuilder();
		query.append(" select c from CandidatoEvento c ");
		if(idCandidato != null)
			query.append(" where c.id.idCandidato = :idCandidato ");
		if(idEvento != null)
			query.append(" and c.id.idEvento = :idEvento ");
		TypedQuery<CandidatoEvento> q = entityManager.createQuery(query.toString(),CandidatoEvento.class);
		if(idCandidato != null)
			q.setParameter("idCandidato",idCandidato);
		if(idEvento != null)
			q.setParameter("idEvento", idEvento);
		try{
			candidatoEvento = q.getSingleResult();
		}catch(NoResultException e){
			return null;
		}catch(Exception e){
			e.printStackTrace();
		}
		return candidatoEvento;
	}

	@Override
	public EventoFolio findEventoFolio(long idEvento)
			throws IllegalArgumentException, NoResultException,
			TechnicalException {

		if (idEvento == 0L)
			throw new IllegalArgumentException("El parámetro idEvento no está informado");
		
		try{			
			EventoFolio eventoFolio = entityManager.find(EventoFolio.class, idEvento);			
			return eventoFolio;

		} catch(NoResultException e){
			return null;
			
		} catch(Exception e){			
			logger.error("Ha ocurrido un error en EventoFacade.findFolioCandidato");
			e.printStackTrace();
			throw new TechnicalException(e);
			
		}	
	}

	@Override
	public Long registrarCandidatoEvento(Long idCandidato, int idEvento, int folioReg,Long idUsuario) {
		long fR = System.currentTimeMillis();
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(fR);
		Date feRe = calendar.getTime();
		CandidatoEvento candidato = new CandidatoEvento();
		CandidatoEventoPk id = new CandidatoEventoPk();
		id.setIdCandidato((long)idCandidato);
		id.setIdEvento((long)idEvento);
		candidato.setId(id);
		candidato.setFolioRegistro(folioReg);
		candidato.setFechaRegistro(feRe);
		candidato.setIdUsuario(idUsuario);
		candidato.setIdFuente(Catalogos.CATALOGO_FUENTE_QUE_REGISTRA_OFERTA.PORTAL.getIdOpcion());
		try{
			entityManager.persist(candidato);
		}catch(Exception e){
			logger.error(e);
			throw new PersistenceException("error en el metodo registarCandidatoEvento",e);
		}
		return candidato.getFolioRegistro();
	}

	@Override
	public boolean actualizaFolio(EventoFolio eventoFol)
			throws BusinessException {
		try{
			entityManager.merge(eventoFol);
			entityManager.flush();
		}catch(Exception e){
			logger.error(e);
			throw new PersistenceException("error en el metodo actualizarFolio",e);
		}
		return true;
	}

	@Override
	public EventoVO findEventoById(Long idEvento)
			throws IllegalArgumentException, NoResultException,
			TechnicalException {
		EventoVO eventoVO = null;
		if(idEvento == 0L)
			throw new IllegalArgumentException("El parámetro idEvento no está informado");
		try{
			Evento  evento = entityManager.find(Evento.class, idEvento);
			eventoVO = Converter.convertEOtoVO(evento);
		}catch(NoResultException e){
			return null;
		}catch(Exception e){
			logger.error(e);
			e.printStackTrace();
		}
		return eventoVO;
	}

	@Override
	public ComprobanteRegistroEventoCandidatoVO consultaCandidatoEventoImprimir(
			Long idCandidato, Long idEvento) throws IllegalArgumentException,
			NoResultException, TechnicalException {
		StringBuilder query = new StringBuilder();
		ComprobanteRegistroEventoCandidatoVO comprobante = new ComprobanteRegistroEventoCandidatoVO();
		query.append("SELECT C.FOLIO_REGISTRO,E.EVENTO,E.FECHA_INICIO,E.HORA_ATENCION_INICIO,E.HORA_ATENCION_FIN,E.ID_ENTIDAD,");
		query.append(" E.SEDE,S.NOMBRE,S.APELLIDO1,S.APELLIDO2,U.USUARIO ");
		query.append(" FROM CANDIDATO_EVENTO C ");
		query.append(" INNER JOIN EVENTO E ");
		query.append(" ON C.ID_EVENTO = E.ID_EVENTO ");
		query.append(" INNER JOIN CANDIDATO B ");
		query.append(" ON B.ID_CANDIDATO = C.ID_CANDIDATO ");
		query.append(" INNER JOIN SOLICITANTE S ");
		query.append(" ON S.ID_CANDIDATO = C.ID_CANDIDATO ");
		query.append(" INNER JOIN USUARIO U ");
		query.append(" ON B.ID_USUARIO = U.ID_USUARIO ");
		query.append(" WHERE C.ID_CANDIDATO = ?1 ");
		query.append(" AND C.ID_EVENTO = ?2 ");
		Query q = entityManager.createNativeQuery(query.toString());
		q.setParameter("1", idCandidato);
		q.setParameter("2", idEvento);
		try{
			Object obj = q.getSingleResult();
			comprobante = getComprobanteReg(obj);
		}catch(NoResultException e){
			return null;
		}catch(Exception e){
			e.printStackTrace();
		}
		return comprobante;
	}
	
	public ComprobanteRegistroEventoCandidatoVO getComprobanteReg(Object obj){
		ComprobanteRegistroEventoCandidatoVO comprobante  = new ComprobanteRegistroEventoCandidatoVO();
		Object[] o = (Object[])obj;
		comprobante.setFolioRegCandi(((BigDecimal) o[0]).longValue());
		comprobante.setNombreEvento((String)o[1]);
		SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
		comprobante.setFechaEvento(sd.format((Date)o[2]));
		if(o[3] != null && o[4] != null)
			comprobante.setHorarioAtnEvento("De "+(String)o[3]+" A "+(String)o[4]);
		else
			comprobante.setHorarioAtnEvento("De 9:00 am a 7:00 pm");
			comprobante.setLugarEvento(Catalogos.ENTIDADES_FEDERATIVAS.getDescripcion(((BigDecimal) o[5]).intValue())+",  "+(String)o[6]);
			comprobante.setNombreCandidato((String)o[7]+" "+(String)o[8]+" "+(String)o[9]);
			comprobante.setUsuarioCandidato((String)o[10]);
		return comprobante;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OfertaEmpleoVO> findOfertaEmpleoEvento(
			String idOfertas,Integer idEvento) throws NoResultException, PersistenceException {
		StringBuilder query = new StringBuilder();
		List<OfertaEmpleoVO> ofertas = new ArrayList<OfertaEmpleoVO>();
		List<Object> data = new ArrayList<Object>();
		query.append(" select distinct oferta_empleo.ID_OFERTA_EMPLEO, oferta_empleo.TITULO_OFERTA,oferta_empleo.SALARIO,empresa.RAZON_SOCIAL,oferta_empleo.FECHA_ALTA, ");
		query.append(" oferta_ubicacion.ID_ENTIDAD, ");
		query.append(" (select municipio from municipio where municipio.ID_ENTIDAD = oferta_ubicacion.ID_ENTIDAD  and MUNICIPIO.ID_MUNICIPIO = oferta_ubicacion.ID_MUNICIPIO) municipio ");
		query.append(" from oferta_empleo ");
		query.append(" inner join empresa ");
		query.append(" on empresa.ID_EMPRESA = oferta_empleo.ID_EMPRESA ");
		query.append(" inner join oferta_ubicacion ");
		query.append(" on oferta_empleo.ID_OFERTA_EMPLEO = oferta_ubicacion.ID_OFERTA_EMPLEO ");
		query.append(" inner join oferta_empleo_evento ");
		query.append(" on (oferta_empleo.ID_OFERTA_EMPLEO = oferta_empleo_evento.ID_OFERTA_EMPLEO AND OFERTA_EMPLEO.ESTATUS = ").append(Catalogos.ESTATUS.FERIA_PUBLICADA_SOLO_FERIAS.getIdOpcion()).append(" ) ");
		query.append(" where oferta_empleo_evento.ID_EVENTO = ?1 ");
		query.append(" and oferta_empleo_evento.ID_OFERTA_EMPLEO IN(").append(idOfertas).append(")");
		Query q = entityManager.createNativeQuery(query.toString());
		q.setParameter("1", idEvento);
		
		try{
			data = q.getResultList();
			for(Object obj : data){
				ofertas.add(getOferta(obj));
			}
		}catch(NoResultException ex){
			return null;
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e);
			throw new PersistenceException("error en el método findOfertaEmpleoEvento");
		}
		return ofertas;
	}
	
	public OfertaEmpleoVO getOferta(Object obj){
		OfertaEmpleoVO oferta = new OfertaEmpleoVO();
		SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
		Object[] o = (Object[]) obj;
		if((String) o[3] != null)
			oferta.setNombreEmpresa((String)o[3]);
		else
			oferta.setNombreEmpresa("NO DISPONIBLE");
		oferta.setTituloOferta((String) o[1]);
		oferta.setSalario(((BigDecimal) o[2]).doubleValue());
		oferta.setSalarioStr(NumberFormat.getCurrencyInstance().format(oferta.getSalario()));
		oferta.setIdOfertaEmpleo(((BigDecimal) o[0]).longValue());
		oferta.setFechaAlta((Date) o[4]);
		oferta.setIdEntidadUbicacion(((BigDecimal) o[5]).intValue());
		oferta.setEntidadDescripcion(Catalogos.ENTIDADES_FEDERATIVAS.getDescripcion(oferta.getIdEntidadUbicacion()));
		oferta.setFechaPublicacion(sd.format(oferta.getFechaAlta()));
		oferta.setMunicipioDescripcion((String) o[6]);
		return oferta;
	}

	@Override
	public EventoVO asignaMunicipioColonia(EventoVO evento)
			throws NoResultException {
		Long idEntidad = evento.getIdEntidad().longValue();
		Long idMunicipio = evento.getIdMunicipio().longValue();
		Long idColonia = null;
		String codigoPostal = evento.getCodigoPostal();
		if(evento.getIdColonia() != null)
			idColonia = evento.getIdColonia().longValue();
		StringBuilder query  = new StringBuilder();
		query.append(" SELECT M.MUNICIPIO,CP.COLONIA ");
		query.append(" FROM MUNICIPIO M ");
		query.append(" INNER JOIN CODIGO_POSTAL CP ");
		query.append(" ON M.ID_ENTIDAD = CP.ID_ENTIDAD");
		query.append(" WHERE M.ID_ENTIDAD = ?1 ");
		query.append(" AND M.ID_MUNICIPIO = ?2 ");
		if(idColonia != null ){
			query.append(" AND CP.ID_COLONIA = ?3 ");
		}
		query.append(" AND CP.CODIGO_POSTAL = ?4");
		Query q = entityManager.createNativeQuery(query.toString());
		q.setParameter("1", idEntidad);
		q.setParameter("2", idMunicipio);
		if(idColonia != null){
			q.setParameter("3", idColonia);
		}
		q.setParameter("4", codigoPostal);
		try{
			Object dato = q.getSingleResult();
			evento = setteaMunCol(dato, evento);
		}catch(NoResultException e){
			e.printStackTrace();
		}catch(Exception e1){
			e1.printStackTrace();
		}
		return evento;
	}
	
	public EventoVO setteaMunCol(Object obj, EventoVO evento){
		Object[] o = (Object[]) obj;
		evento.setMunicipio((String) o[0]);
		if(o[1] != null){
			evento.setColonia((String) o[1]);
		}
		return evento;
	}

	@Override
	public EventoVO asignaSoloMunicipio(EventoVO evento)
			throws NoResultException {
		Long idEntidad = evento.getIdEntidad().longValue();
		Long idMunicipio = evento.getIdMunicipio().longValue();
		StringBuilder query = new StringBuilder();
		query.append(" SELECT M.MUNICIPIO ");
		query.append(" FROM MUNICIPIO M ");
		query.append(" WHERE M.ID_ENTIDAD = ?1 ");
		query.append(" AND M.ID_MUNICIPIO = ?2 ");
		Query q = entityManager.createNativeQuery(query.toString());
		q.setParameter("1", idEntidad);
		q.setParameter("2", idMunicipio);
		try{
			String dato = (String)q.getSingleResult();
			evento.setMunicipio(dato);
		}catch(NoResultException e){
			e.printStackTrace();
		}catch(Exception e1){
			e1.printStackTrace();
		}
		return evento;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<EventoVO> eventosRecientes(int mes, int anio, int estatus) throws Exception {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT ID_EVENTO,ID_ENTIDAD, EVENTO, CALLE, CODIGO_POSTAL, LOCALIDAD, NUMERO_EXTERIOR, FECHA_INICIO ");
		query.append(" FROM EVENTO WHERE EXTRACT (MONTH FROM FECHA_INICIO ) >= ?1 ");
		query.append(" AND EXTRACT (YEAR FROM FECHA_INICIO ) >= ?2 ");
		query.append(" AND estatus = ?3 AND ID_EVENTO IN (SELECT ID_EVENTO FROM OFERTA_EMPLEO_EVENTO) ");
		query.append(" ORDER BY FECHA_INICIO ");
		Query q = entityManager.createNativeQuery(query.toString());
		q.setParameter(1, mes);
		q.setParameter(2, anio);
		q.setParameter(3, estatus);
		try {
			List<Object[]> result  = q.getResultList();
			List<EventoVO> listEventoVO = new ArrayList<EventoVO>();
			for(Object[] obj : result){
				EventoVO eventoVO = new EventoVO();
				eventoVO.setIdEvento(Utils.toLong(obj[0]));
				eventoVO.setIdEntidad(Utils.toInt(obj[1]));
				eventoVO.setEvento(Utils.toString(obj[2]));
				eventoVO.setCalle(Utils.toString(obj[3]));
				eventoVO.setCodigoPostal(Utils.toString(obj[4]));
				eventoVO.setLocalidad(Utils.toString(obj[5]));
				eventoVO.setNumeroExterior(Utils.toString(obj[6]));
				eventoVO.setFechaInicio(Utils.toDate(obj[7]));
				if (eventoVO.getFechaInicio() != null ){
						String diaMesFechaInicio  = String.valueOf(eventoVO.getFechaInicio().getDate());
							if (diaMesFechaInicio.length()< 2 )
								diaMesFechaInicio  = "0" + diaMesFechaInicio;
							eventoVO.setDiaMesFechaInicio(diaMesFechaInicio);
			  }         
				listEventoVO.add(eventoVO);
			}
			return listEventoVO;
		}catch(NoResultException e){
			e.printStackTrace();
		}catch(Exception e1){
			e1.printStackTrace();
		}
		return null;	
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<EventoVO> previousEventList(int month, int year, int status) throws Exception {
		String yearLbl = null;
		String monthLbl = null;
		StringBuilder query = new StringBuilder();
		query.append(" SELECT ID_EVENTO,ID_ENTIDAD, EVENTO, CALLE, CODIGO_POSTAL, LOCALIDAD, NUMERO_EXTERIOR, FECHA_INICIO ");
		query.append(" FROM EVENTO WHERE EXTRACT (MONTH FROM FECHA_INICIO ) < ?1 ");
		query.append(" AND EXTRACT (YEAR FROM FECHA_INICIO ) <= ?2 ");
		query.append(" AND estatus = ?3 AND ID_EVENTO IN (SELECT ID_EVENTO FROM OFERTA_EMPLEO_EVENTO) ");
		query.append(" ORDER BY FECHA_INICIO DESC");
		Query q = entityManager.createNativeQuery(query.toString());
		q.setParameter(1, month);
		q.setParameter(2, year);
		q.setParameter(3, status);
		try {
			List<Object[]> result  = q.getResultList();
			List<EventoVO> listEventoVO = new ArrayList<EventoVO>();
			for (Object[] obj : result) {
				EventoVO eventoVO = new EventoVO();
				eventoVO.setIdEvento(Utils.toLong(obj[0]));
				eventoVO.setIdEntidad(Utils.toInt(obj[1]));
				eventoVO.setEvento(Utils.toString(obj[2]));
				eventoVO.setCalle(Utils.toString(obj[3]));
				eventoVO.setCodigoPostal(Utils.toString(obj[4]));
				eventoVO.setLocalidad(Utils.toString(obj[5]));
				eventoVO.setNumeroExterior(Utils.toString(obj[6]));
				eventoVO.setFechaInicio(Utils.toDate(obj[7]));
				if (null != eventoVO.getFechaInicio()) {
					String diaMesFechaInicio  = String.valueOf(eventoVO.getFechaInicio().getDate());
					if (diaMesFechaInicio.length() < 2 )
						diaMesFechaInicio  = "0" + diaMesFechaInicio;
					eventoVO.setDiaMesFechaInicio(diaMesFechaInicio);
					if (null == monthLbl || !monthLbl.equalsIgnoreCase(getMonth(eventoVO.getFechaInicio()))) {
						monthLbl = getMonth(eventoVO.getFechaInicio());
						eventoVO.setMonthInitDate(monthLbl);
					}
					if (null == yearLbl || !yearLbl.equalsIgnoreCase(getYear(eventoVO.getFechaInicio()))) {
						yearLbl = getYear(eventoVO.getFechaInicio());
						eventoVO.setYearInitDate(yearLbl);
					}
				}       
				listEventoVO.add(eventoVO);
			}
			return listEventoVO;
		}catch(NoResultException e){
			e.printStackTrace();
		}catch(Exception e1){
			e1.printStackTrace();
		}
		return null;	
	}
	
	private String getMonth(Date date) {
		String label = "";
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);
		switch (month) {
			case 0: label = "Enero"; break;
			case 1: label = "Febrero"; break;
			case 2: label = "Marzo"; break;
			case 3: label = "Abril"; break;
			case 4: label = "Mayo"; break;
			case 5: label = "Junio"; break;
			case 6: label = "Julio"; break;
			case 7: label = "Agosto"; break;
			case 8: label = "Septiembre"; break;
			case 9: label = "Octubre"; break;
			case 10: label = "Noviembre"; break;
			case 11: label = "Diciembre"; break;
		}
		return label;
	}
	
	private String getYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		return String.valueOf(year);
	}
}
