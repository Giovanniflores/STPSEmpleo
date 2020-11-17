package mx.gob.stps.portal.core.persistencia.facade;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_GRADO_ESTUDIOS;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_OCUPACION;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_IDIOMAS;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_DOMINIO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.infra.utils.Constantes.MULTIREGISTRO;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorPerfilVO;
import mx.gob.stps.portal.persistencia.vo.HistoricoBusquedaPPCVO;
import mx.gob.stps.portal.utils.Catalogos;


/**********************************************************
//
// Nombre: OPR  Fecha:	9.10.14
// Fachada consulta datos de la tabla "Historico_Busqueda_PPC" para modulo 
// Administracion de No Postulaciones para el seguro de desempleo
//
/************************************************************/
@Stateless
public class AdministracionNoPostulacionesFacade implements AdministracionNoPostulacionesFacadeLocal{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<HistoricoBusquedaPPCVO> getListadoOfertasNoPostuladas(long idCandidato) throws PersistenceException {
		List<HistoricoBusquedaPPCVO> 	listadoOFertasNoPostuladas 	= new ArrayList<HistoricoBusquedaPPCVO>();
		StringBuilder 					query 						= new StringBuilder();

		query.append(" SELECT T.ID_CANDIDATO, T.ID_OFERTA_EMPLEO, T.COMPATIBILIDAD, T.ID_MOTIVO, CAT.OPCION ");
		query.append(" FROM (   ");
		query.append(" 			SELECT HB.ID_CANDIDATO, HB.ID_OFERTA_EMPLEO, HB.COMPATIBILIDAD, ");
		query.append(" 				   MAX(HB.ID_MOTIVO) AS ID_MOTIVO ");
		query.append(" 			FROM HISTORICO_BUSQUEDA_PPC HB ");
		query.append(" 			INNER JOIN OFERTA_EMPLEO   OE  ON HB.ID_OFERTA_EMPLEO = OE.ID_OFERTA_EMPLEO ");
		query.append(" 			WHERE  HB.ID_CANDIDATO = ");
		query.append(				   String.valueOf(idCandidato));
		query.append(" 			AND    OE.ESTATUS = ");
		query.append(				   Catalogos.ESTATUS.ACTIVO.getIdOpcion());
		query.append(" 			GROUP BY HB.ID_OFERTA_EMPLEO, HB.ID_CANDIDATO, HB.COMPATIBILIDAD ");
		query.append(" 			ORDER BY HB.ID_OFERTA_EMPLEO ASC ");
		query.append(" ) T ");
		query.append(" LEFT JOIN CATALOGO_OPCION CAT ON T.ID_MOTIVO = CAT.ID_CATALOGO_OPCION AND CAT.ID_CATALOGO = ");
		query.append(Constantes.CATALOGO_OPCION_MOTIVO_NO_POSTULACION);
		
		/*query.append(" SELECT DISTINCT HB.ID_CANDIDATO, HB.ID_OFERTA_EMPLEO, HB.COMPATIBILIDAD, ");
		query.append(" 		  		   HB.ID_MOTIVO, CAT.OPCION ");
		query.append(" FROM   HISTORICO_BUSQUEDA_PPC HB ");
		query.append(" LEFT   JOIN OFERTA_EMPLEO   OE  ON HB.ID_OFERTA_EMPLEO = OE.ID_OFERTA_EMPLEO ");
		query.append(" LEFT   JOIN CATALOGO_OPCION CAT ON HB.ID_MOTIVO 		  = CAT.ID_CATALOGO_OPCION AND CAT.ID_CATALOGO = 111 ");
		query.append(" WHERE  HB.ID_CANDIDATO = ");
		query.append(String.valueOf(idCandidato));
		query.append(" AND    OE.ESTATUS = ");
		query.append(Catalogos.ESTATUS.ACTIVO.getIdOpcion());
		query.append(" ORDER BY HB.ID_OFERTA_EMPLEO ASC ");*/
		
		Query sql = entityManager.createNativeQuery(query.toString());
		
	
		@SuppressWarnings("unchecked")
		List<Object[]> rowSet = sql.getResultList();
		
		for (Object[] result : rowSet){
			listadoOFertasNoPostuladas.add(createHistoricoBusquedaPPCVO(result));
		}
		
		return listadoOFertasNoPostuladas;
	}
	
	@Override
	public OfertaPorPerfilVO obtenerOfertaPorID(long idOfertaEmpleo, long idCandidato) throws SQLException {
		OfertaPorPerfilVO ofertaPorPerfilVO = null;
		Query sql = entityManager.createNativeQuery(strDetalleOferta(idOfertaEmpleo, idCandidato));
		
		@SuppressWarnings("unchecked")
		List<Object[]> rowSet = sql.getResultList();
		//Un solo registro
		ofertaPorPerfilVO = createOfertaPorPerfilVO(rowSet.get(0));
				
		return ofertaPorPerfilVO;
	}
	
	private String strDetalleOferta(long idOfertaEmpleo, long idCandidato){
		StringBuilder query = new StringBuilder();
		
		query.append(" SELECT H.Id_Empresa, 			   H.Id_Oferta_Empleo,  H.Titulo_Oferta, G.Opcion || ', ' || F.Municipio As Ubicacion, ");
		query.append(" 		  H.Nombre_Empresa As Empresa, H.Salario, 		 	H.Fuente, 				 H.Funciones, 	   H.Edad_Requisito, H.Edad_Minima, ");
		query.append(" 		  H.Edad_Maxima, 			   H.Id_Tipo_Empleo, 	H.Numero_Plazas, 		 H.Contacto_Tel,   H.Contacto_Correo, ");
		query.append(" '(' || ");
		query.append("     Desccatalogo(1, " + CATALOGO_OPCION_GRADO_ESTUDIOS + ", H.Id_Nivel_Estudio) || ' - ' || Desccatalogo(2, " + CATALOGO_OPCION_GRADO_ESTUDIOS + ", H.Id_Nivel_Estudio, K.Id_Carrera_Especialidad) ");
		query.append("  || ')' As Grado_Estudio ");
		query.append(" , Desccatalogo(1, " + CATALOGO_OPCION_OCUPACION + " , H.Id_Ocupacion) AS Ocupacion, ");
		query.append(" '(' || ");
		query.append("		Desccatalogo(1, " + CATALOGO_OPCION_IDIOMAS + ", Ci.Id_Idioma) || ' - ' || Desccatalogo(1, "+ CATALOGO_OPCION_DOMINIO +", Ci.Id_Dominio) ");
		query.append("  || ')' AS IDIOMA ");
		
		query.append(" FROM Empresa I, Oferta_Empleo H ");
		query.append(" LEFT JOIN Oferta_Candidato 	  OC ON H.Id_Oferta_Empleo = OC.Id_Oferta_Empleo  AND Oc.Id_Candidato = " + idCandidato);
		query.append(" LEFT JOIN OFERTA_CARRERA_ESPEC K  ON H.ID_OFERTA_EMPLEO = K.ID_OFERTA_EMPLEO   AND K.PRINCIPAL     = " + MULTIREGISTRO.PRINCIPAL.getIdOpcion());
		query.append(" LEFT JOIN OFERTA_UBICACION 	  D  ON H.ID_OFERTA_EMPLEO = D.ID_OFERTA_EMPLEO ");
		query.append(" LEFT JOIN MUNICIPIO 		  	  F  ON D.ID_ENTIDAD 	   = F.ID_ENTIDAD 		  AND D.ID_MUNICIPIO  = F.ID_MUNICIPIO ");
		query.append(" Left Join Catalogo_Opcion  	  G  ON D.Id_Entidad 	   = G.Id_Catalogo_Opcion AND G.Id_Catalogo   = " + CATALOGO_OPCION_ENTIDAD_FEDERATIVA);
		query.append(" LEFT JOIN Candidato_Idioma 	  CI ON OC.id_candidato    = CI.id_candidato ");
		query.append(" WHERE H.ID_OFERTA_EMPLEO = " + idOfertaEmpleo + " AND H.ID_EMPRESA = I.ID_EMPRESA ");
		
		return query.toString();
		
	}
	
	private OfertaPorPerfilVO createOfertaPorPerfilVO(Object[] rowSet){
		OfertaPorPerfilVO vo = new OfertaPorPerfilVO();
		StringBuilder contacto = new StringBuilder();
		
		vo.setIdEmpresa		(Utils.toLong(rowSet[0]));
		vo.setIdOfertaEmpleo(Utils.toLong(rowSet[1]));
		vo.setTituloOferta	(Utils.toString(rowSet[2]));
		vo.setUbicacion		(Utils.toString(rowSet[3]));
		
		vo.setEmpresa		(Utils.toString(rowSet[4]));
		vo.setSalario		(Utils.toDouble(rowSet[5]));
		vo.setFuente		(Utils.parseInt(Utils.toString(rowSet[6])));
		vo.setFunciones		(Utils.toString(rowSet[7]));
		vo.setEdad(Utils.toString(rowSet[8]));
		//rowSet[9]  -- Edad Minima
		//rowSet[10] -- Edad Maxima
		//rowSet[11] -- Id Tipo Empleo
		
		vo.setNumeroPlazas	(Utils.parseInt(Utils.toString(rowSet[12])));

		if(Utils.parseInt(Utils.toString(rowSet[13])) == Constantes.CONTACTO_TELEFONO.SI.getIdContactoTelefono())
			contacto.append("Teléfono ");
		if(Utils.parseInt(Utils.toString(rowSet[14])) == Constantes.CONTACTO_CORREO.SI.getIdContactoCorreo())
			if(contacto.length() > 0 )
				contacto.append(" y Correo Electrónico");
			else
				contacto.append("Correo Electrónico");
		
		vo.setMedioContacto (contacto.toString());
		vo.setGradoEstudio	(Utils.toString(rowSet[15]));vo.setCarrera	(Utils.toString(rowSet[14]));
		vo.setOcupacion		(Utils.toString(rowSet[16]));
		vo.setIdiomas		(Utils.toString(rowSet[17]));
		
		return vo;
	}

	private HistoricoBusquedaPPCVO createHistoricoBusquedaPPCVO(Object[] rowSet) {
		HistoricoBusquedaPPCVO vo = new HistoricoBusquedaPPCVO();

		vo.setIdCandidato		(Utils.toLong(rowSet[0]));
		vo.setIdOfertaEmpleo	(Utils.toLong(rowSet[1]));
		vo.setCompatibilidad	(Utils.toLong(rowSet[2]));
		vo.setIdMotivo			(Utils.toLong(rowSet[3]));
		if(rowSet[3] != null)
			vo.setMotivoDesc	(Utils.toString(rowSet[4]));
		
		return vo;
	}

	@Override
	public void actualizarMotivoNoPostulacion(long idCandidato, long idOfertaEmpleo, long idMotivo, 
					String motivoDescripcion, int fuente) throws SQLException {
		
		String jpql = " update Historico_Busqueda_Ppc h set h.ID_MOTIVO = " + idMotivo 
					+ ", h.MOTIVO_DESC = '" + motivoDescripcion + "', h.FUENTE = " + fuente
					+ ", h.FECHA_NO_POSTULACION = SYSDATE " 
					+ " where h.ID_CANDIDATO = " + idCandidato + " and h.ID_OFERTA_EMPLEO = " + idOfertaEmpleo;
		Query query = entityManager.createNativeQuery(jpql);
		
		query.executeUpdate();
	}
	
}