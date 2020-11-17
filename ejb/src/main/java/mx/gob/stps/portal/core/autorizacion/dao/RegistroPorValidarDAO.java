package mx.gob.stps.portal.core.autorizacion.dao;

import mx.gob.stps.portal.core.autorizacion.vo.RegistroPorValidarVO;
import mx.gob.stps.portal.core.infra.data.TemplateDAO;
import mx.gob.stps.portal.core.infra.utils.Constantes.*;

import javax.sql.rowset.CachedRowSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static mx.gob.stps.portal.core.infra.utils.Constantes.*;

public class RegistroPorValidarDAO extends TemplateDAO {

	private final static int QUERY_REGISTROS_PENDIENTES = 1;
	private final static int QUERY_OFERTAS_PENDIENTES   = 2;
	//private final static int QUERY_EMPRESAS_PENDIENTES  = 3;
	private final static int QUERY_DESASIGNA            = 4;
	private final static int QUERY_ASIGNA               = 5;
	private final static int QUERY_REGISTROS_ASIGNADOS  = 6;
	private final static int QUERY_TEST_EMP_PENDIENTES  = 7;
	private final static int QUERY_REGISTROS_PENDIENTES_TOTAL = 8;
	
	private int QUERY = 0;
	
	private static final HashMap<Long, TIPO_REGISTRO> TIPOS_REGISTRO = new HashMap<Long, TIPO_REGISTRO>();
	private static final HashMap<Long, SUBTIPO_REGISTRO> SUBTIPOS_REGISTRO = new HashMap<Long, SUBTIPO_REGISTRO>();
	private static final HashMap<Long, TIPO_PROPIETARIO> TIPOS_PROPIETARIO = new HashMap<Long, TIPO_PROPIETARIO>();
		
	private RegistroPorValidarDAO(){
		inicializa();
	}
	
	private RegistroPorValidarDAO(Connection connectionGlobal){
		super(connectionGlobal);
		inicializa();
	}
	
	private static void inicializa(){
		for (TIPO_REGISTRO tipo : TIPO_REGISTRO.values()){
			TIPOS_REGISTRO.put((long)tipo.getIdTipoRegistro(), tipo);
		}
		for (SUBTIPO_REGISTRO tipo : SUBTIPO_REGISTRO.values()){
			SUBTIPOS_REGISTRO.put((long)tipo.getIdSubTipoRegistro(), tipo);
		}		
		for (TIPO_PROPIETARIO tipo : TIPO_PROPIETARIO.values()){
			TIPOS_PROPIETARIO.put((long)tipo.getIdTipoPropietario(), tipo);
		}
	}
	
	public static RegistroPorValidarDAO getInstance(){
		return new RegistroPorValidarDAO();
	}

	public static RegistroPorValidarDAO getInstanceConnectionGlobal(Connection connectionGlobal){
		return new RegistroPorValidarDAO(connectionGlobal);
	}

	public List<RegistroPorValidarVO> consultaOfertasPorValidar(long idEmpresa) throws SQLException {
		QUERY = QUERY_OFERTAS_PENDIENTES; // establece la consulta a ejecutar

		Object[] params = new Object[]{
				TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona(),
				CATALOGO_OPCION_ESTATUS,
				ESTATUS.ACTIVO.getIdOpcion(),
				ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion(),
				TIPO_REGISTRO.OFERTA.getIdTipoRegistro(),
				TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario(),
				idEmpresa};

		CachedRowSet rowSet = executeQuery(params);
		
		List<RegistroPorValidarVO> registros = new ArrayList<RegistroPorValidarVO>();
		while (rowSet.next())
			registros.add(createVO(rowSet));
		

		return registros;
	}

	/**
	 * Consulta los Testimonios Pendientes por Publicar asociados a una empresa
	 * @param idEmpresa
	 * @return
	 * @throws SQLException
	 */
	public List<RegistroPorValidarVO> consultaTestimonioEmpPorValidar(long idEmpresa) throws SQLException {
		QUERY = QUERY_TEST_EMP_PENDIENTES; // establece la consulta a ejecutar
 		
		Object[] params = new Object[]{
				TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona(),
				CATALOGO_OPCION_ESTATUS,
				ESTATUS.ACTIVO.getIdOpcion(),
				ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion(),
				TIPO_REGISTRO.TESTIMONIO.getIdTipoRegistro(),
				TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario(),
				idEmpresa};

		CachedRowSet rowSet = executeQuery(params);
		
		List<RegistroPorValidarVO> registros = new ArrayList<RegistroPorValidarVO>();
		while (rowSet.next())
			registros.add(createVO(rowSet));
		

		return registros;
	}
	
	public int consultaTotalRegistrosPorValidar() throws SQLException {
		QUERY = QUERY_REGISTROS_PENDIENTES_TOTAL; // establece la consulta a ejecutar
		
		Object[] params = new Object[]{
				
				// Ofertas de empleo de empresas
				TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona(),
				ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion(),
				ESTATUS.ASIGNADO_PUBLICADOR.getIdOpcion(),
				ESTATUS.EN_EDICION_PUBLICADOR.getIdOpcion(),
				TIPO_REGISTRO.OFERTA.getIdTipoRegistro(),
				TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario(),
				
				// Testimonio de la empresa
				TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona(),
				ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion(),
				ESTATUS.ASIGNADO_PUBLICADOR.getIdOpcion(),
				ESTATUS.EN_EDICION_PUBLICADOR.getIdOpcion(),
				TIPO_REGISTRO.TESTIMONIO.getIdTipoRegistro(),
				TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario(),
				
				// Testimonio del candidato
				ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion(),
				ESTATUS.ASIGNADO_PUBLICADOR.getIdOpcion(),
				ESTATUS.EN_EDICION_PUBLICADOR.getIdOpcion(),
				TIPO_REGISTRO.TESTIMONIO.getIdTipoRegistro(),
				TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario(),

				// Video Curriculos del candidato
				ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion(),
				ESTATUS.ASIGNADO_PUBLICADOR.getIdOpcion(),
				ESTATUS.EN_EDICION_PUBLICADOR.getIdOpcion(),
				TIPO_REGISTRO.VIDEO_CURRICULO.getIdTipoRegistro(),
				TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario()
				//CATALOGO_OPCION_ESTATUS
				};
				
		CachedRowSet rowSet = executeQuery(params);
				
		int total = 0;
		
		if (rowSet.next())
			total = rowSet.getInt("TOTAL");
		
		return total;
	}

	public List<RegistroPorValidarVO> consultaRegistrosPorValidar(long idUsuario, int maxbloque) throws SQLException {
		QUERY = QUERY_REGISTROS_PENDIENTES; // establece la consulta a ejecutar
		
		Object[] params = new Object[]{

		// Ofertas de empleo de empresas
		TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona(),
		ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion(),
		idUsuario, ESTATUS.ASIGNADO_PUBLICADOR.getIdOpcion(), ESTATUS.EN_EDICION_PUBLICADOR.getIdOpcion(), // en caso de que no haya soltado los registros anteriores
		TIPO_REGISTRO.OFERTA.getIdTipoRegistro(),
		TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario(),
		
		// Testimonio de la empresa
		TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona(),
		ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion(),
		idUsuario, ESTATUS.ASIGNADO_PUBLICADOR.getIdOpcion(), ESTATUS.EN_EDICION_PUBLICADOR.getIdOpcion(), // en caso de que no haya soltado los registros anteriores
		TIPO_REGISTRO.TESTIMONIO.getIdTipoRegistro(),
		TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario(),
		
		// Testimonio del candidato
		ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion(),
		idUsuario, ESTATUS.ASIGNADO_PUBLICADOR.getIdOpcion(), ESTATUS.EN_EDICION_PUBLICADOR.getIdOpcion(), // en caso de que no haya soltado los registros anteriores
		TIPO_REGISTRO.TESTIMONIO.getIdTipoRegistro(),
		TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario(),

		// Video Curriculos del candidato
		ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion(),
		idUsuario, ESTATUS.ASIGNADO_PUBLICADOR.getIdOpcion(), ESTATUS.EN_EDICION_PUBLICADOR.getIdOpcion(), // en caso de que no haya soltado los registros anteriores
		TIPO_REGISTRO.VIDEO_CURRICULO.getIdTipoRegistro(),
		TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario(),

		CATALOGO_OPCION_ESTATUS, 
		maxbloque // Cantidad de registros a consultar
		};
		
		CachedRowSet rowSet = executeQuery(params);
		
		List<RegistroPorValidarVO> registros = new ArrayList<RegistroPorValidarVO>();
		while (rowSet.next())
			registros.add(createVO(rowSet));
		
		
		return registros;
	}

	public List<RegistroPorValidarVO> consultaRegistrosPorValidarAsignados(long idUsuario) throws SQLException {
		QUERY = QUERY_REGISTROS_ASIGNADOS;
		
		Object[] params = new Object[] {
		
			// Ofertas de empleo de empresas
			TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona(),
			TIPO_REGISTRO.OFERTA.getIdTipoRegistro(),
			TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario(),
			idUsuario, ESTATUS.ASIGNADO_PUBLICADOR.getIdOpcion(), ESTATUS.EN_EDICION_PUBLICADOR.getIdOpcion(),
			
			// Testimonio de la empresa
			TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona(),
			TIPO_REGISTRO.TESTIMONIO.getIdTipoRegistro(),
			TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario(),
			idUsuario, ESTATUS.ASIGNADO_PUBLICADOR.getIdOpcion(), ESTATUS.EN_EDICION_PUBLICADOR.getIdOpcion(),
			
			// Testimonio del candidato
			TIPO_REGISTRO.TESTIMONIO.getIdTipoRegistro(),
			TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario(),
			idUsuario, ESTATUS.ASIGNADO_PUBLICADOR.getIdOpcion(), ESTATUS.EN_EDICION_PUBLICADOR.getIdOpcion(),
	
			// Video Curriculos del candidato
			TIPO_REGISTRO.VIDEO_CURRICULO.getIdTipoRegistro(),
			TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario(),
			idUsuario, ESTATUS.ASIGNADO_PUBLICADOR.getIdOpcion(), ESTATUS.EN_EDICION_PUBLICADOR.getIdOpcion(),

			CATALOGO_OPCION_ESTATUS
		};
		
		CachedRowSet rowSet = executeQuery(params);
		
		List<RegistroPorValidarVO> recs = new ArrayList<RegistroPorValidarVO>();
		while (rowSet.next()) {
			RegistroPorValidarVO rec = createVO(rowSet);
			if (!contains(recs, rec)) {
				recs.add(createVO(rowSet));
			}
		}
		return recs;
	}
	
	private boolean contains(List<RegistroPorValidarVO> recs, RegistroPorValidarVO rec) {
		if (null == recs || recs.isEmpty()) return false;
		for (RegistroPorValidarVO vo : recs) {
			if (vo.getIdRegistro() == rec.getIdRegistro() && vo.getIdTipoRegistro() == rec.getIdTipoRegistro() && vo.getIdPropietario() == rec.getIdPropietario() && 
					vo.getIdTipoPropietario() == rec.getIdTipoPropietario()) return true;
		}
		return false;
	}
	
	public int actualizaRegistrosAsignados(ESTATUS estatusFinal, long idUsuario, ESTATUS asignadoPublicador, ESTATUS enEdicionPublicador) throws SQLException {
		QUERY = QUERY_DESASIGNA; // establece la consulta a ejecutar

		Object[] params = new Object[4];
		params[0] = estatusFinal.getIdOpcion();
		params[1] = idUsuario;
		params[2] = asignadoPublicador.getIdOpcion();
		params[3] = enEdicionPublicador.getIdOpcion();
		
		int result = executeUpdate(params);

		return result;
	}

	public int actualizaRegistroAsignado(long idRegValidar, long idUsuario, ESTATUS estatus) throws SQLException {
		QUERY = QUERY_ASIGNA; // establece la consulta a ejecutar

		Object[] params = {idUsuario, estatus.getIdOpcion(), idRegValidar};		
		int result = executeUpdate(params);

		return result;
	}
	
	@Override
	protected String getQuery() {
		StringBuilder query = new StringBuilder();
		
		switch (QUERY){
			case QUERY_REGISTROS_PENDIENTES:
				query = getQueryRegistrosPorValidar(); break;

			case QUERY_REGISTROS_PENDIENTES_TOTAL:
				query = getQueryTotalRegistrosPorValidar(); break;

			case QUERY_REGISTROS_ASIGNADOS:
				query = getQueryRegistrosPorValidarAsignados(); break;
				
			case QUERY_OFERTAS_PENDIENTES:
				query = getQueryOfertasPorValidar(); break;

			case QUERY_TEST_EMP_PENDIENTES:
				query = getQueryTestimonioEmpPorValidar(); break;

			case QUERY_DESASIGNA:
				query.append("UPDATE REGISTRO_POR_VALIDAR ");
				query.append("   SET ID_USUARIO = NULL, ESTATUS = ? ");
				query.append(" WHERE (ID_USUARIO = ? OR NVL(ID_USUARIO, 0) = 0) ");
				query.append("   AND (ESTATUS = ? OR ESTATUS = ?) ");
				break;
			
			case QUERY_ASIGNA:
				query.append("UPDATE REGISTRO_POR_VALIDAR ");
				query.append("  SET ID_USUARIO = ?, ");
				query.append("      ESTATUS = ? ");
				query.append(" WHERE ID_REG_VALIDAR = ? ");
				break;
		}

		return query.toString();
	}

	private RegistroPorValidarVO createVO(CachedRowSet rowSet) throws SQLException {
		RegistroPorValidarVO vo = new RegistroPorValidarVO();

		vo.setIdRegValidar(rowSet.getLong("ID_REG_VALIDAR"));
		vo.setIdRegistro(rowSet.getLong("ID_REGISTRO"));
		vo.setIdTipoRegistro(rowSet.getLong("ID_TIPO_REGISTRO"));
		vo.setIdPropietario(rowSet.getLong("ID_PROPIETARIO"));
		vo.setIdTipoPropietario(rowSet.getLong("ID_TIPO_PROPIETARIO"));
		vo.setFechaAlta(rowSet.getDate("FECHA_ALTA"));
		vo.setEstatus(rowSet.getInt("ESTATUS"));
		vo.setRegistro(rowSet.getString("REGISTRO"));
	    vo.setPropietario(rowSet.getString("PROPIETARIO"));
	    
	    vo.setEstatusReg(rowSet.getInt("ESTATUS_REGISTRO"));
	    vo.setEstatusRegDesc(rowSet.getString("ESTATUS_REGISTRO_DESC"));

	    TIPO_REGISTRO tipoRegistro = TIPOS_REGISTRO.get(vo.getIdTipoRegistro());
	    if (tipoRegistro!=null) vo.setTipoRegistro(tipoRegistro.getTipoRegistro());

	    TIPO_PROPIETARIO tipoProp = TIPOS_PROPIETARIO.get(vo.getIdTipoPropietario());
	    if (tipoProp!=null) vo.setTipoPropietario(tipoProp.getTipoPropietario());

	    vo.setIdSubTipoRegistro(rowSet.getInt("SUBTIPO_REGISTRO"));
	    SUBTIPO_REGISTRO subTipoRegistro = SUBTIPOS_REGISTRO.get(vo.getIdSubTipoRegistro());
	    if (subTipoRegistro!=null) vo.setSubTipoRegistro(subTipoRegistro.getSubTipoRegistro());

		return vo;
	}

	private StringBuilder getQueryOfertasPorValidar(){
		StringBuilder query = new StringBuilder();

		query.append("SELECT REG.ID_REG_VALIDAR, ");
		query.append("       REG.ID_REGISTRO, ");
		query.append("       REG.ID_TIPO_REGISTRO, ");
		query.append("       OFER.TITULO_OFERTA AS REGISTRO, ");
		query.append("       REG.ID_PROPIETARIO, ");
		query.append("       REG.ID_TIPO_PROPIETARIO, ");
		query.append("       DECODE(EMP.ID_TIPO_PERSONA, ? ,	EMP.NOMBRE ||' '|| EMP.APELLIDO1 ||' '|| EMP.APELLIDO2, EMP.RAZON_SOCIAL) AS PROPIETARIO, ");
		query.append("       REG.ESTATUS, ");
		query.append("       REG.FECHA_ALTA, ");
		query.append("       OFER.ESTATUS AS ESTATUS_REGISTRO, ");
		query.append("       ESTATUS.OPCION AS ESTATUS_REGISTRO_DESC, ");
		query.append("       "+ SUBTIPO_REGISTRO.OFERTA +" AS SUBTIPO_REGISTRO ");
		query.append("  FROM REGISTRO_POR_VALIDAR REG, ");
		query.append("       OFERTA_EMPLEO OFER, ");
		query.append("       EMPRESA EMP, ");
		query.append("       (SELECT ID_CATALOGO_OPCION, OPCION FROM CATALOGO_OPCION WHERE ID_CATALOGO = ? AND ESTATUS = ? ) ESTATUS ");
		query.append(" WHERE REG.ESTATUS = ? ");
		query.append("   AND REG.ID_TIPO_REGISTRO = ? "); // OFERTA
		query.append("   AND REG.ID_TIPO_PROPIETARIO = ? "); // EMPRESA
		query.append("   AND REG.ID_PROPIETARIO      = ? "); // ID EMPRESA
		query.append("   AND REG.ID_REGISTRO = OFER.ID_OFERTA_EMPLEO ");   
		query.append("   AND REG.ID_PROPIETARIO = EMP.ID_EMPRESA ");
		query.append("   AND OFER.ESTATUS = ESTATUS.ID_CATALOGO_OPCION ");
		query.append("   AND OFER.ESTATUS IN ("+ ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion() +", "+ ESTATUS.EMP_MODIFICADA.getIdOpcion() +") ");
		
		return query;
	}

	private StringBuilder getQueryTestimonioEmpPorValidar(){
		StringBuilder query = new StringBuilder();

		query.append("SELECT REG.ID_REG_VALIDAR, ");
		query.append("       REG.ID_REGISTRO, ");
		query.append("       REG.ID_TIPO_REGISTRO, ");
		query.append("       TES.DESCRIPCION AS REGISTRO, ");
		query.append("       REG.ID_PROPIETARIO, ");
		query.append("       REG.ID_TIPO_PROPIETARIO, ");
		query.append("       DECODE(EMP.ID_TIPO_PERSONA, ? ,	EMP.NOMBRE ||' '|| EMP.APELLIDO1 ||' '|| EMP.APELLIDO2, EMP.RAZON_SOCIAL) AS PROPIETARIO, ");
		query.append("       REG.ESTATUS, ");
		query.append("       REG.FECHA_ALTA, ");
		query.append("       TES.ESTATUS AS ESTATUS_REGISTRO, ");
		query.append("       ESTATUS.OPCION AS ESTATUS_REGISTRO_DESC, ");
		query.append("       "+ SUBTIPO_REGISTRO.TESTIMONIO_EMPRESA +" AS SUBTIPO_REGISTRO ");
		query.append("  FROM REGISTRO_POR_VALIDAR REG, ");
		query.append("       TESTIMONIO TES, ");
		query.append("       EMPRESA EMP, ");
		query.append("       (SELECT ID_CATALOGO_OPCION, OPCION FROM CATALOGO_OPCION WHERE ID_CATALOGO = ? AND ESTATUS = ? ) ESTATUS ");
		query.append(" WHERE REG.ESTATUS = ? ");
		query.append("   AND REG.ID_TIPO_REGISTRO = ? "); //  TESTIMONIO
		query.append("   AND REG.ID_TIPO_PROPIETARIO = ? "); //  EMPRESA
		query.append("   AND REG.ID_PROPIETARIO      = ? "); // ID EMPRESA
		query.append("   AND REG.ID_REGISTRO = TES.ID_TESTIMONIO ");
		query.append("   AND REG.ID_PROPIETARIO = EMP.ID_EMPRESA ");
		query.append("   AND TES.ESTATUS = ESTATUS.ID_CATALOGO_OPCION ");
		query.append("   AND TES.ESTATUS = "+ ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion() +" ");
		
		return query;
	}
		
	private StringBuilder getQueryRegistrosPorValidar(){
		StringBuilder query = new StringBuilder();

		query.append("SELECT * FROM ( ");
		query.append("SELECT REGISTROS.ID_REG_VALIDAR, ");
		query.append("   REGISTROS.ID_REGISTRO, ");
		query.append("   REGISTROS.ID_TIPO_REGISTRO, ");
		query.append("   REGISTROS.REGISTRO, ");
		query.append("   REGISTROS.ID_PROPIETARIO, ");
		query.append("   REGISTROS.ID_TIPO_PROPIETARIO, ");
		query.append("   REGISTROS.PROPIETARIO, ");
		query.append("   REGISTROS.ESTATUS, ");
		query.append("   REGISTROS.FECHA_ALTA, ");
		query.append("   REGISTROS.ESTATUS_REGISTRO, ");
		query.append("   ESTATUS.OPCION AS ESTATUS_REGISTRO_DESC, ");
		query.append("   REGISTROS.SUBTIPO_REGISTRO ");

		query.append("  FROM ( ");

		//  OFERTAS
		//  SE CONSULTAN LA OFERTAS REGISTRADAS
		query.append("SELECT OFER.TITULO_OFERTA AS REGISTRO, ");
		query.append("       DECODE(EMP.ID_TIPO_PERSONA, ? ,	EMP.NOMBRE ||' '|| EMP.APELLIDO1 ||' '|| EMP.APELLIDO2, EMP.RAZON_SOCIAL) AS PROPIETARIO, ");
		query.append("       OFER.ESTATUS AS ESTATUS_REGISTRO, ");
		query.append("       REG.ID_REG_VALIDAR, ");
		query.append("       REG.ID_REGISTRO, ");
		query.append("       REG.ID_TIPO_REGISTRO, ");
		query.append("       REG.ID_PROPIETARIO, ");
		query.append("       REG.ID_TIPO_PROPIETARIO, ");
		query.append("       REG.ESTATUS, ");
		query.append("       REG.FECHA_ALTA, ");
		query.append("       "+ SUBTIPO_REGISTRO.OFERTA +" AS SUBTIPO_REGISTRO ");
		query.append("  FROM REGISTRO_POR_VALIDAR REG, ");
		query.append("       OFERTA_EMPLEO OFER, ");
		query.append("       EMPRESA EMP ");
		query.append(" WHERE (REG.ESTATUS = ? OR (REG.ID_USUARIO = ? AND (REG.ESTATUS = ? OR REG.ESTATUS = ? ))) ");
		query.append("   AND REG.ID_TIPO_REGISTRO = ? "); //  OFERTA
		query.append("   AND REG.ID_TIPO_PROPIETARIO = ? "); //  EMPRESA
		query.append("   AND REG.ID_REGISTRO = OFER.ID_OFERTA_EMPLEO ");   
		query.append("   AND REG.ID_PROPIETARIO = EMP.ID_EMPRESA ");
		query.append("   AND OFER.ESTATUS IN ("+ ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion() +", "+ ESTATUS.EMP_MODIFICADA.getIdOpcion() +") ");
		
		query.append("UNION ");
		 
		//  TESTIMONIO EMPRESA
		//  SE CONSULTAN LOS TESTIMONIOS REGISTRADOS POR EMPRESAS
		query.append("SELECT TES.DESCRIPCION AS REGISTRO, ");
		query.append("       DECODE(EMP.ID_TIPO_PERSONA, ? ,	EMP.NOMBRE ||' '|| EMP.APELLIDO1 ||' '|| EMP.APELLIDO2, EMP.RAZON_SOCIAL) AS PROPIETARIO, ");
		query.append("       TES.ESTATUS AS ESTATUS_REGISTRO, ");
		query.append("       REG.ID_REG_VALIDAR, ");
		query.append("       REG.ID_REGISTRO, ");
		query.append("       REG.ID_TIPO_REGISTRO, ");
		query.append("       REG.ID_PROPIETARIO, ");
		query.append("       REG.ID_TIPO_PROPIETARIO, ");
		query.append("       REG.ESTATUS, ");
		query.append("       REG.FECHA_ALTA, ");
		query.append("       "+ SUBTIPO_REGISTRO.TESTIMONIO_EMPRESA +" AS SUBTIPO_REGISTRO ");
		query.append("  FROM REGISTRO_POR_VALIDAR REG, ");
		query.append("       TESTIMONIO TES, ");
		query.append("       EMPRESA EMP ");
		query.append(" WHERE (REG.ESTATUS = ? OR (REG.ID_USUARIO = ? AND (REG.ESTATUS = ? OR REG.ESTATUS = ? ))) ");
		query.append("   AND REG.ID_TIPO_REGISTRO = ? "); //  TESTIMONIO
		query.append("   AND REG.ID_TIPO_PROPIETARIO = ? "); //  EMPRESA
		query.append("   AND REG.ID_REGISTRO = TES.ID_TESTIMONIO ");
		query.append("   AND REG.ID_PROPIETARIO = EMP.ID_EMPRESA ");
		query.append("   AND TES.ESTATUS = "+ ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion() +" ");

		query.append("UNION ");

		//  TESTIMONIO CANDIDATO
		//  SE CONSULTAN LOS TESTIMONIOS REGISTRADOS POR CANDIDATOS
		query.append("SELECT TES.DESCRIPCION AS REGISTRO, ");
		query.append("       SOL.NOMBRE ||' '|| SOL.APELLIDO1 ||' '|| SOL.APELLIDO2 AS PROPIETARIO, ");
		query.append("       TES.ESTATUS AS ESTATUS_REGISTRO, ");
		query.append("       REG.ID_REG_VALIDAR, ");
		query.append("       REG.ID_REGISTRO, ");
		query.append("       REG.ID_TIPO_REGISTRO, ");
		query.append("       REG.ID_PROPIETARIO, ");
		query.append("       REG.ID_TIPO_PROPIETARIO, ");
		query.append("       REG.ESTATUS, ");
		query.append("       REG.FECHA_ALTA, ");
		query.append("       "+ SUBTIPO_REGISTRO.TESTIMONIO_CANDIDATO +" AS SUBTIPO_REGISTRO ");
		query.append("  FROM REGISTRO_POR_VALIDAR REG, ");
		query.append("       TESTIMONIO TES, ");
		query.append("       SOLICITANTE SOL ");
		query.append(" WHERE (REG.ESTATUS = ? OR (REG.ID_USUARIO = ? AND (REG.ESTATUS = ? OR REG.ESTATUS = ? ))) ");
		query.append("   AND REG.ID_TIPO_REGISTRO = ? "); //  TESTIMONIO
		query.append("   AND REG.ID_TIPO_PROPIETARIO = ? "); //  CANDIDATO
		query.append("   AND REG.ID_REGISTRO = TES.ID_TESTIMONIO ");
		query.append("   AND REG.ID_PROPIETARIO = SOL.ID_CANDIDATO ");
		query.append("   AND TES.ESTATUS = "+ ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion() +" ");

		query.append("UNION ");

		//  VIDEO CURRICULO
		//  SE CONSULTAN LOS VIDEO CURRICULOS REGISTRADOS POR CANDIDATOS
		query.append("SELECT VID.URL_VIDEOC AS REGISTRO, ");
		query.append("       SOL.NOMBRE ||' '|| SOL.APELLIDO1 ||' '|| SOL.APELLIDO2 AS PROPIETARIO, ");
		query.append("       VID.ESTATUS_VIDEOC AS ESTATUS_REGISTRO, ");
		query.append("       REG.ID_REG_VALIDAR, ");
		query.append("       REG.ID_REGISTRO, ");
		query.append("       REG.ID_TIPO_REGISTRO, ");
		query.append("       REG.ID_PROPIETARIO, ");
		query.append("       REG.ID_TIPO_PROPIETARIO, ");
		query.append("       REG.ESTATUS, ");
		query.append("       REG.FECHA_ALTA, ");
		query.append("       "+ SUBTIPO_REGISTRO.VIDEO_CURRICULO +" AS SUBTIPO_REGISTRO ");
		query.append("  FROM REGISTRO_POR_VALIDAR REG, ");
		query.append("       PERFIL_LABORAL VID, ");
		query.append("       SOLICITANTE SOL ");
		query.append(" WHERE (REG.ESTATUS = ? OR (REG.ID_USUARIO = ? AND (REG.ESTATUS = ? OR REG.ESTATUS = ? ))) ");
		query.append("   AND REG.ID_TIPO_REGISTRO = ? "); //  VIDEO CURRICULO
		query.append("   AND REG.ID_TIPO_PROPIETARIO = ? "); //  CANDIDATO
		query.append("   AND REG.ID_REGISTRO = VID.ID_CANDIDATO ");
		query.append("   AND REG.ID_PROPIETARIO = SOL.ID_CANDIDATO ");
		query.append("   AND VID.ESTATUS_VIDEOC = "+ ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion() +" ");
		
		query.append(") REGISTROS, ");
		query.append("  CATALOGO_OPCION ESTATUS ");
		query.append("WHERE ESTATUS.ID_CATALOGO = ? ");
		query.append("  AND REGISTROS.ESTATUS_REGISTRO = ESTATUS.ID_CATALOGO_OPCION ");
		query.append("ORDER BY REGISTROS.FECHA_ALTA ASC "); //  SE OBTIENEN LOS REGISTROS MAS ANTIGUOS

		query.append(") TEMPORAL ");
		query.append("WHERE ROWNUM <= ? "); //  SE LIMITA LA CANTIDAD DE REGISTROS POR ASIGNAR A UN PUBLICADOR
		
		return query;
	}

	private StringBuilder getQueryTotalRegistrosPorValidar(){
		StringBuilder query = new StringBuilder();

		query.append("SELECT COUNT(1) AS TOTAL FROM ( ");
		query.append("SELECT REGISTROS.ID_REG_VALIDAR ");
		/*query.append("   REGISTROS.ID_REGISTRO, ");
		query.append("   REGISTROS.ID_TIPO_REGISTRO, ");
		query.append("   REGISTROS.REGISTRO, ");
		query.append("   REGISTROS.ID_PROPIETARIO, ");
		query.append("   REGISTROS.ID_TIPO_PROPIETARIO, ");
		query.append("   REGISTROS.PROPIETARIO, ");
		query.append("   REGISTROS.ESTATUS, ");
		query.append("   REGISTROS.FECHA_ALTA, ");
		query.append("   REGISTROS.ESTATUS_REGISTRO, ");
		query.append("   ESTATUS.OPCION AS ESTATUS_REGISTRO_DESC, ");
		query.append("   REGISTROS.SUBTIPO_REGISTRO ");*/

		query.append("  FROM ( ");

		//  OFERTAS
		//  SE CONSULTAN LA OFERTAS REGISTRADAS
		query.append("SELECT OFER.TITULO_OFERTA AS REGISTRO, ");
		query.append("       DECODE(EMP.ID_TIPO_PERSONA, ? ,	EMP.NOMBRE ||' '|| EMP.APELLIDO1 ||' '|| EMP.APELLIDO2, EMP.RAZON_SOCIAL) AS PROPIETARIO, ");
		query.append("       OFER.ESTATUS AS ESTATUS_REGISTRO, ");
		query.append("       REG.ID_REG_VALIDAR, ");
		query.append("       REG.ID_REGISTRO, ");
		query.append("       REG.ID_TIPO_REGISTRO, ");
		query.append("       REG.ID_PROPIETARIO, ");
		query.append("       REG.ID_TIPO_PROPIETARIO, ");
		query.append("       REG.ESTATUS, ");
		query.append("       REG.FECHA_ALTA, ");
		query.append("       "+ SUBTIPO_REGISTRO.OFERTA +" AS SUBTIPO_REGISTRO ");
		query.append("  FROM REGISTRO_POR_VALIDAR REG, ");
		query.append("       OFERTA_EMPLEO OFER, ");
		query.append("       EMPRESA EMP ");
		query.append(" WHERE REG.ESTATUS IN (?, ?, ?) ");
		query.append("   AND REG.ID_TIPO_REGISTRO = ? "); //  OFERTA
		query.append("   AND REG.ID_TIPO_PROPIETARIO = ? "); //  EMPRESA
		query.append("   AND REG.ID_REGISTRO = OFER.ID_OFERTA_EMPLEO ");   
		query.append("   AND REG.ID_PROPIETARIO = EMP.ID_EMPRESA ");
		query.append("   AND OFER.ESTATUS IN ("+ ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion() +", "+ ESTATUS.EMP_MODIFICADA.getIdOpcion() +") ");
		
		query.append("UNION ");
		 
		//  TESTIMONIO EMPRESA
		//  SE CONSULTAN LOS TESTIMONIOS REGISTRADOS POR EMPRESAS
		query.append("SELECT TES.DESCRIPCION AS REGISTRO, ");
		query.append("       DECODE(EMP.ID_TIPO_PERSONA, ? ,	EMP.NOMBRE ||' '|| EMP.APELLIDO1 ||' '|| EMP.APELLIDO2, EMP.RAZON_SOCIAL) AS PROPIETARIO, ");
		query.append("       TES.ESTATUS AS ESTATUS_REGISTRO, ");
		query.append("       REG.ID_REG_VALIDAR, ");
		query.append("       REG.ID_REGISTRO, ");
		query.append("       REG.ID_TIPO_REGISTRO, ");
		query.append("       REG.ID_PROPIETARIO, ");
		query.append("       REG.ID_TIPO_PROPIETARIO, ");
		query.append("       REG.ESTATUS, ");
		query.append("       REG.FECHA_ALTA, ");
		query.append("       "+ SUBTIPO_REGISTRO.TESTIMONIO_EMPRESA +" AS SUBTIPO_REGISTRO ");
		query.append("  FROM REGISTRO_POR_VALIDAR REG, ");
		query.append("       TESTIMONIO TES, ");
		query.append("       EMPRESA EMP ");
		query.append(" WHERE REG.ESTATUS IN (?, ?, ?) ");
		query.append("   AND REG.ID_TIPO_REGISTRO = ? "); //  TESTIMONIO
		query.append("   AND REG.ID_TIPO_PROPIETARIO = ? "); //  EMPRESA
		query.append("   AND REG.ID_REGISTRO = TES.ID_TESTIMONIO ");
		query.append("   AND REG.ID_PROPIETARIO = EMP.ID_EMPRESA ");
		query.append("   AND TES.ESTATUS = "+ ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion() +" ");

		query.append("UNION ");

		//  TESTIMONIO CANDIDATO
		//  SE CONSULTAN LOS TESTIMONIOS REGISTRADOS POR CANDIDATOS
		query.append("SELECT TES.DESCRIPCION AS REGISTRO, ");
		query.append("       SOL.NOMBRE ||' '|| SOL.APELLIDO1 ||' '|| SOL.APELLIDO2 AS PROPIETARIO, ");
		query.append("       TES.ESTATUS AS ESTATUS_REGISTRO, ");
		query.append("       REG.ID_REG_VALIDAR, ");
		query.append("       REG.ID_REGISTRO, ");
		query.append("       REG.ID_TIPO_REGISTRO, ");
		query.append("       REG.ID_PROPIETARIO, ");
		query.append("       REG.ID_TIPO_PROPIETARIO, ");
		query.append("       REG.ESTATUS, ");
		query.append("       REG.FECHA_ALTA, ");
		query.append("       "+ SUBTIPO_REGISTRO.TESTIMONIO_CANDIDATO +" AS SUBTIPO_REGISTRO ");
		query.append("  FROM REGISTRO_POR_VALIDAR REG, ");
		query.append("       TESTIMONIO TES, ");
		query.append("       SOLICITANTE SOL ");
		query.append(" WHERE REG.ESTATUS IN (?, ?, ?) ");
		query.append("   AND REG.ID_TIPO_REGISTRO = ? "); //  TESTIMONIO
		query.append("   AND REG.ID_TIPO_PROPIETARIO = ? "); //  CANDIDATO
		query.append("   AND REG.ID_REGISTRO = TES.ID_TESTIMONIO ");
		query.append("   AND REG.ID_PROPIETARIO = SOL.ID_CANDIDATO ");
		query.append("   AND TES.ESTATUS = "+ ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion() +" ");

		query.append("UNION ");

		//  VIDEO CURRICULO
		//  SE CONSULTAN LOS VIDEO CURRICULOS REGISTRADOS POR CANDIDATOS
		query.append("SELECT VID.URL_VIDEOC AS REGISTRO, ");
		query.append("       SOL.NOMBRE ||' '|| SOL.APELLIDO1 ||' '|| SOL.APELLIDO2 AS PROPIETARIO, ");
		query.append("       VID.ESTATUS_VIDEOC AS ESTATUS_REGISTRO, ");
		query.append("       REG.ID_REG_VALIDAR, ");
		query.append("       REG.ID_REGISTRO, ");
		query.append("       REG.ID_TIPO_REGISTRO, ");
		query.append("       REG.ID_PROPIETARIO, ");
		query.append("       REG.ID_TIPO_PROPIETARIO, ");
		query.append("       REG.ESTATUS, ");
		query.append("       REG.FECHA_ALTA, ");
		query.append("       "+ SUBTIPO_REGISTRO.VIDEO_CURRICULO +" AS SUBTIPO_REGISTRO ");
		query.append("  FROM REGISTRO_POR_VALIDAR REG, ");
		query.append("       PERFIL_LABORAL VID, ");
		query.append("       SOLICITANTE SOL ");
		query.append(" WHERE REG.ESTATUS IN (?, ?, ?) ");
		query.append("   AND REG.ID_TIPO_REGISTRO = ? "); //  VIDEO CURRICULO
		query.append("   AND REG.ID_TIPO_PROPIETARIO = ? "); //  CANDIDATO
		query.append("   AND REG.ID_REGISTRO = VID.ID_CANDIDATO ");
		query.append("   AND REG.ID_PROPIETARIO = SOL.ID_CANDIDATO ");
		query.append("   AND VID.ESTATUS_VIDEOC = "+ ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion() +" ");
		
		query.append(") REGISTROS ");
		/*query.append("  CATALOGO_OPCION ESTATUS ");
		query.append("WHERE ESTATUS.ID_CATALOGO = ? ");
		query.append("  AND REGISTROS.ESTATUS_REGISTRO = ESTATUS.ID_CATALOGO_OPCION ");*/
		//query.append("ORDER BY REGISTROS.FECHA_ALTA ASC "); //  SE OBTIENEN LOS REGISTROS MAS ANTIGUOS

		query.append(") TEMPORAL ");

		return query;
	}
	
	private StringBuilder getQueryRegistrosPorValidarAsignados(){
		StringBuilder query = new StringBuilder();

		query.append("SELECT REGISTROS.ID_REG_VALIDAR, ");
		query.append("   REGISTROS.ID_REGISTRO, ");
		query.append("   REGISTROS.ID_TIPO_REGISTRO, ");
		query.append("   REGISTROS.REGISTRO, ");
		query.append("   REGISTROS.ID_PROPIETARIO, ");
		query.append("   REGISTROS.ID_TIPO_PROPIETARIO, ");
		query.append("   REGISTROS.PROPIETARIO, ");
		query.append("   REGISTROS.ESTATUS, ");
		query.append("   REGISTROS.FECHA_ALTA, ");
		query.append("   REGISTROS.ESTATUS_REGISTRO, ");
		query.append("   ESTATUS.OPCION AS ESTATUS_REGISTRO_DESC, ");
		query.append("   REGISTROS.SUBTIPO_REGISTRO ");
		
		query.append("  FROM ( ");

		//  OFERTAS
		//  SE CONSULTAN LA OFERTAS REGISTRADAS
		query.append("SELECT OFER.TITULO_OFERTA AS REGISTRO, ");
		query.append("       DECODE(EMP.ID_TIPO_PERSONA, ? ,	EMP.NOMBRE ||' '|| EMP.APELLIDO1 ||' '|| EMP.APELLIDO2, EMP.RAZON_SOCIAL) AS PROPIETARIO, ");
		query.append("       OFER.ESTATUS AS ESTATUS_REGISTRO, ");
		query.append("       REG.ID_REG_VALIDAR, ");
		query.append("       REG.ID_REGISTRO, ");
		query.append("       REG.ID_TIPO_REGISTRO, ");
		query.append("       REG.ID_PROPIETARIO, ");
		query.append("       REG.ID_TIPO_PROPIETARIO, ");
		query.append("       REG.ESTATUS, ");
		query.append("       REG.FECHA_ALTA, ");
		query.append("       "+ SUBTIPO_REGISTRO.OFERTA +" AS SUBTIPO_REGISTRO ");
		query.append("  FROM REGISTRO_POR_VALIDAR REG, ");
		query.append("       OFERTA_EMPLEO OFER, ");
		query.append("       EMPRESA EMP ");
		query.append(" WHERE REG.ID_TIPO_REGISTRO = ? "); //  OFERTA
		query.append("   AND REG.ID_TIPO_PROPIETARIO = ? "); //  EMPRESA
		query.append("   AND REG.ID_REGISTRO = OFER.ID_OFERTA_EMPLEO ");   
		query.append("   AND REG.ID_PROPIETARIO = EMP.ID_EMPRESA ");
		query.append("   AND OFER.ESTATUS IN ("+ ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion() +", "+ ESTATUS.EMP_MODIFICADA.getIdOpcion() +") ");
		
		query.append("   AND REG.ID_REG_VALIDAR IN (SELECT ID_REG_VALIDAR "); // SOLO CONSULTA LOS REGISTROS ASIGNADOS AL USUARIO
		query.append("                                FROM REGISTRO_POR_VALIDAR REG ");
		query.append("                               WHERE REG.ID_USUARIO = ? ");
		query.append("                                 AND REG.ESTATUS IN (?, ?)) ");
		
		query.append("UNION ");
		 
		//  TESTIMONIO EMPRESA
		//  SE CONSULTAN LOS TESTIMONIOS REGISTRADOS POR EMPRESAS
		query.append("SELECT TES.DESCRIPCION AS REGISTRO, ");
		query.append("       DECODE(EMP.ID_TIPO_PERSONA, ? ,	EMP.NOMBRE ||' '|| EMP.APELLIDO1 ||' '|| EMP.APELLIDO2, EMP.RAZON_SOCIAL) AS PROPIETARIO, ");
		query.append("       TES.ESTATUS AS ESTATUS_REGISTRO, ");
		query.append("       REG.ID_REG_VALIDAR, ");
		query.append("       REG.ID_REGISTRO, ");
		query.append("       REG.ID_TIPO_REGISTRO, ");
		query.append("       REG.ID_PROPIETARIO, ");
		query.append("       REG.ID_TIPO_PROPIETARIO, ");
		query.append("       REG.ESTATUS, ");
		query.append("       REG.FECHA_ALTA, ");
		query.append("       "+ SUBTIPO_REGISTRO.TESTIMONIO_EMPRESA +" AS SUBTIPO_REGISTRO ");
		query.append("  FROM REGISTRO_POR_VALIDAR REG, ");
		query.append("       TESTIMONIO TES, ");
		query.append("       EMPRESA EMP ");
		query.append(" WHERE REG.ID_TIPO_REGISTRO = ? "); //  TESTIMONIO
		query.append("   AND REG.ID_TIPO_PROPIETARIO = ? "); //  EMPRESA
		query.append("   AND REG.ID_REGISTRO = TES.ID_TESTIMONIO ");
		query.append("   AND REG.ID_PROPIETARIO = EMP.ID_EMPRESA ");
		query.append("   AND TES.ESTATUS = "+ ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion() +" ");

		query.append("   AND REG.ID_REG_VALIDAR IN (SELECT ID_REG_VALIDAR "); // SOLO CONSULTA LOS REGISTROS ASIGNADOS AL USUARIO
		query.append("                                FROM REGISTRO_POR_VALIDAR REG ");
		query.append("                               WHERE REG.ID_USUARIO = ? ");
		query.append("                                 AND REG.ESTATUS IN (?, ?)) ");		
		
		
		query.append("UNION ");

		//  TESTIMONIO CANDIDATO
		//  SE CONSULTAN LOS TESTIMONIOS REGISTRADOS POR CANDIDATOS
		query.append("SELECT TES.DESCRIPCION AS REGISTRO, ");
		query.append("       SOL.NOMBRE ||' '|| SOL.APELLIDO1 ||' '|| SOL.APELLIDO2 AS PROPIETARIO, ");
		query.append("       TES.ESTATUS AS ESTATUS_REGISTRO, ");
		query.append("       REG.ID_REG_VALIDAR, ");
		query.append("       REG.ID_REGISTRO, ");
		query.append("       REG.ID_TIPO_REGISTRO, ");
		query.append("       REG.ID_PROPIETARIO, ");
		query.append("       REG.ID_TIPO_PROPIETARIO, ");
		query.append("       REG.ESTATUS, ");
		query.append("       REG.FECHA_ALTA, ");
		query.append("       "+ SUBTIPO_REGISTRO.TESTIMONIO_CANDIDATO +" AS SUBTIPO_REGISTRO ");
		query.append("  FROM REGISTRO_POR_VALIDAR REG, ");
		query.append("       TESTIMONIO TES, ");
		query.append("       SOLICITANTE SOL ");
		query.append(" WHERE REG.ID_TIPO_REGISTRO = ? "); //  TESTIMONIO
		query.append("   AND REG.ID_TIPO_PROPIETARIO = ? "); //  CANDIDATO
		query.append("   AND REG.ID_REGISTRO = TES.ID_TESTIMONIO ");
		query.append("   AND REG.ID_PROPIETARIO = SOL.ID_CANDIDATO ");
		query.append("   AND TES.ESTATUS = "+ ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion() +" ");

		query.append("   AND REG.ID_REG_VALIDAR IN (SELECT ID_REG_VALIDAR "); // SOLO CONSULTA LOS REGISTROS ASIGNADOS AL USUARIO
		query.append("                                FROM REGISTRO_POR_VALIDAR REG ");
		query.append("                               WHERE REG.ID_USUARIO = ? ");
		query.append("                                 AND REG.ESTATUS IN (?, ?)) ");
		
		query.append("UNION ");

		//  VIDEO CURRICULO
		//  SE CONSULTAN LOS VIDEO CURRICULOS REGISTRADOS POR CANDIDATOS
		query.append("SELECT VID.URL_VIDEOC AS REGISTRO, ");
		query.append("       SOL.NOMBRE ||' '|| SOL.APELLIDO1 ||' '|| SOL.APELLIDO2 AS PROPIETARIO, ");
		query.append("       VID.ESTATUS_VIDEOC AS ESTATUS_REGISTRO, ");
		query.append("       REG.ID_REG_VALIDAR, ");
		query.append("       REG.ID_REGISTRO, ");
		query.append("       REG.ID_TIPO_REGISTRO, ");
		query.append("       REG.ID_PROPIETARIO, ");
		query.append("       REG.ID_TIPO_PROPIETARIO, ");
		query.append("       REG.ESTATUS, ");
		query.append("       REG.FECHA_ALTA, ");
		query.append("       "+ SUBTIPO_REGISTRO.VIDEO_CURRICULO +" AS SUBTIPO_REGISTRO ");
		query.append("  FROM REGISTRO_POR_VALIDAR REG, ");
		query.append("       PERFIL_LABORAL VID, ");
		query.append("       SOLICITANTE SOL ");
		query.append(" WHERE REG.ID_TIPO_REGISTRO = ? "); //  VIDEO CURRICULO
		query.append("   AND REG.ID_TIPO_PROPIETARIO = ? "); //  CANDIDATO
		query.append("   AND REG.ID_REGISTRO = VID.ID_CANDIDATO ");
		query.append("   AND REG.ID_PROPIETARIO = SOL.ID_CANDIDATO ");
		query.append("   AND VID.ESTATUS_VIDEOC = "+ ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion() +" ");

		query.append("   AND REG.ID_REG_VALIDAR IN (SELECT ID_REG_VALIDAR "); // SOLO CONSULTA LOS REGISTROS ASIGNADOS AL USUARIO
		query.append("                                FROM REGISTRO_POR_VALIDAR REG ");
		query.append("                               WHERE REG.ID_USUARIO = ? ");
		query.append("                                 AND REG.ESTATUS IN (?, ?)) ");
		
		query.append(") REGISTROS, ");
		query.append("  CATALOGO_OPCION ESTATUS ");
		query.append("WHERE ESTATUS.ID_CATALOGO = ? ");
		query.append("  AND REGISTROS.ESTATUS_REGISTRO = ESTATUS.ID_CATALOGO_OPCION ");
		query.append("ORDER BY REGISTROS.FECHA_ALTA ASC "); //  SE OBTIENEN LOS REGISTROS MAS ANTIGUOS

		return query;
	}

}
