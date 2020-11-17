package mx.gob.stps.portal.core.oferta.detalle.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import mx.gob.stps.portal.core.infra.data.TemplateDAO;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.JERARQUIA;
import mx.gob.stps.portal.core.oferta.detalle.helper.Utils;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaIdiomaVO;
import mx.gob.stps.portal.core.oferta.registro.vo.RegistroUbicacionVO;

public class OfertaDetalleDAO extends TemplateDAO {
	
	private int QUERY = 0;
	private final static int UBICACION = 1;
	private final static int IDIOMAS = 2;
	private final static int ESPECIALIDADES = 3;
	private final static int CATALOGO_OPCION = 4;
	private final static int PRESTACIONES = 5;
	private final static int SECTOR = 6;
	private final static int MUNICIPIO = 7;
	private final static int ESPECIALIDADES_PREFIJO = 8;
	private final static int OFERTA = 9;
	private final static int CARRERAS = 10;
	private final static int CONOCIMIENTOS = 11;
	private final static int OFERTA_IDIOMAS = 13;
	private final static int LBL_UBICACION = 14;
	
	private OfertaDetalleDAO() {
	}

	private OfertaDetalleDAO(Connection globalConnection) {
		super(globalConnection);
	}
	
	public static OfertaDetalleDAO getInstance(){
		return new OfertaDetalleDAO();
	}

	public static OfertaDetalleDAO getInstance(Connection globalConnection){
		return new OfertaDetalleDAO(globalConnection);
	}
	
	public HashMap<String, String> getUbicacion(long idOfertaEmpleo) throws SQLException {
		QUERY = UBICACION;
		HashMap<String, String> results = new HashMap<String, String>();
		Object[] params = new Object[2];
		params[0] = idOfertaEmpleo;
		params[1] = Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA;
		CachedRowSet rowSet = executeQuery(params);
		while (rowSet.next()){
			results.put("entidad", rowSet.getString(1));
			results.put("municipio", rowSet.getString(2));
		}
		return results;
	}
	
//	public DomicilioVO getLocation(long idOfertaEmpleo) throws SQLException {
//		QUERY = LBL_UBICACION;
//		DomicilioVO location = new DomicilioVO();
//		Object[] params = new Object[2];
//		params[0] = idOfertaEmpleo;
//		params[1] = Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA;
//		CachedRowSet rowSet = executeQuery(params);
//		while (rowSet.next()){
//			location.setIdEntidad(rowSet.getLong(1));
//			location.setIdMunicipio(rowSet.getLong(2));
//		}
//		return location;
//	}
	
	public List<RegistroUbicacionVO> getUbicaciones(long idOfertaEmpleo) throws SQLException {
		QUERY = UBICACION;
		List<RegistroUbicacionVO> results = new ArrayList<RegistroUbicacionVO>();
		Object[] params = new Object[2];
		params[0] = idOfertaEmpleo;
		params[1] = Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA;
		CachedRowSet rowSet = executeQuery(params);
		while (rowSet.next()){
			RegistroUbicacionVO registro = new RegistroUbicacionVO ();
			registro.setEntidad(rowSet.getString(1));
			registro.setMunicipio(rowSet.getString(2));
			results.add(registro);
		}
		return results;
	}
	
	public HashMap<String, String> ofertaIdiomasList(long idOfertaEmpleo) throws SQLException {
		QUERY = IDIOMAS;
		HashMap<String, String> results = new HashMap<String, String>();
		Object[] params = new Object[2];
		params[0] = idOfertaEmpleo;
		params[1] = Constantes.CATALOGO_OPCION_IDIOMAS;
		CachedRowSet rowSet = executeQuery(params);
		while (rowSet.next()) {
			results.put(rowSet.getString(1), Utils.getDominio(rowSet.getInt(2)));
		}
		return results;
	}
	
	public ArrayList<String> especialidadesList(long idOfertaEmpleo, long idNivelEstudio) throws SQLException {
		QUERY = ESPECIALIDADES;
		ArrayList<String> list = new ArrayList<String>();
		Object[] params = new Object[2];
		params[0] = idOfertaEmpleo;
		params[1] = Utils.getIdCatalog((int)idNivelEstudio);
		CachedRowSet rowSet = executeQuery(params);
		while (rowSet.next()) {
			list.add(rowSet.getString(1));
		}
		return list;
	}
	
	public ArrayList<String> especialidadesPrefixList(long idOfertaEmpleo, long idNivelEstudio) throws SQLException {
		QUERY = ESPECIALIDADES_PREFIJO;
		ArrayList<String> list = new ArrayList<String>();
		Object[] params = new Object[2];
		params[0] = idOfertaEmpleo;
		params[1] = Utils.getIdCatalog((int)idNivelEstudio);
		CachedRowSet rowSet = executeQuery(params);
		while (rowSet.next()) {
			list.add(rowSet.getString(1));
		}
		return list;
	}
	
	public String getCatalogoOpcion(long id_catalogo, long id_catalogo_opcion) throws SQLException {
		QUERY = CATALOGO_OPCION;
		String opcion = "";
		Object[] params = new Object[2];
		if (id_catalogo > 0 && id_catalogo_opcion > 0) {
			params[0] = id_catalogo;
			params[1] = id_catalogo_opcion;
			CachedRowSet rowSet = executeQuery(params);
			while (rowSet.next()) {
				opcion = rowSet.getString(1);
			}
		}
		return opcion;
	}
	
	public ArrayList<String> prestacionesList(long idOfertaEmpleo) throws SQLException {
		QUERY = PRESTACIONES;
		ArrayList<String> list = new ArrayList<String>();
		Object[] params = new Object[2];
		params[0] = idOfertaEmpleo;
		params[1] = Constantes.CATALOGO_OPCION_PRESTACIONES;
		CachedRowSet rowSet = executeQuery(params);
		while (rowSet.next()) {
			list.add(rowSet.getString(1));
		}
		return list;
	}

	// RBM1 TK1000 TK1001 se reimplementa este metodo 4mayo
	// Se homologa con RU
	public String getCatalogoArea(long idAreaLaboral) throws SQLException {
		QUERY = Long.valueOf(Constantes.CATALOGO_OPCION_AREA_LABORAL)
				.intValue();
		String opcion = "";
		Object[] params = new Object[1];
		if (idAreaLaboral > 0) {
			params[0] = idAreaLaboral;
			// params[1] = id_catalogo_opcion;
			CachedRowSet rowSet = executeQuery(params);
			while (rowSet.next()) {
				opcion = rowSet.getString(2);
				//logger.info("opcion={"+opcion+"}");
			}
		}
		
		
		return opcion;
	}

	// RBM1 TK1000 TK1001 se agrega este metodo 4mayo
	// Se homologa con RU
	public String getCatalogoSubArea(long idSubreaLaboral) throws SQLException {
		QUERY = Long.valueOf(Constantes.CATALOGO_OPCION_SUBAREA).intValue();
		String opcion = "";
		Object[] params = new Object[1];
		if (idSubreaLaboral > 0) {
			params[0] = idSubreaLaboral;
			// params[1] = id_catalogo_opcion;
			CachedRowSet rowSet = executeQuery(params);
			while (rowSet.next()) {
				opcion = rowSet.getString(2);				
			}
		}
		
		return opcion;
	}

	// RBM1 TK1000 TK1001 se reimplementa este metodo 4mayo
	// Se homologa con RU

	public ArrayList<String> nivelPuestoList(long idOfertaEmpleo)
			throws SQLException {

		ArrayList<String> list = new ArrayList<String>();

		List<JERARQUIA> listNivelesPuesto = listNivelesPuesto();

		for (JERARQUIA nivel : listNivelesPuesto) {
			list.add(nivel.getOpcion());
		}
		return list;
	}

	protected List<JERARQUIA> listNivelesPuesto() {
		List<JERARQUIA> list = new ArrayList<JERARQUIA>();
		list.add(JERARQUIA.OPERARIO);
		list.add(JERARQUIA.TECNICO);
		list.add(JERARQUIA.ADMINISTRATIVOS);
		list.add(JERARQUIA.PRODUCCION);
		list.add(JERARQUIA.DIRECTIVOS);
		return list;
	}

	public ArrayList<String> sectorList(long idOfertaEmpleo) throws SQLException {
		QUERY = SECTOR;
		ArrayList<String> list = new ArrayList<String>();
		Object[] params = new Object[2];
		params[0] = idOfertaEmpleo;
		params[1] = Constantes.CATALOGO_OPCION_SUBSECTOR;
		CachedRowSet rowSet = executeQuery(params);
		while (rowSet.next()) {
			list.add(rowSet.getString(1));
		}
		return list;
	}

	// END RBM1 TK1000 TK1001 se reimplementa este metodo 4mayo
	// Se homologa con RU
	
	public String getMunicipio(long idEntidad, long idMunicipio) throws SQLException {
		QUERY = MUNICIPIO;
		String opcion = "";
		if (idEntidad > 0 && idMunicipio > 0) {
			Object[] params = new Object[2];
			params[0] = idEntidad;
			params[1] = idMunicipio;
			CachedRowSet rowSet = executeQuery(params);
			while (rowSet.next()) {
				opcion = rowSet.getString(1);
			}
		}
		return opcion;
	}
	
//	public Vacante getOferta(long idOferta) throws SQLException {
//		QUERY = OFERTA;
//		Vacante oferta = new Vacante(idOferta);
//		if (idOferta > 0) {
//			Object[] params = { idOferta };
//			CachedRowSet rowSet = executeQuery(params);
//			while (rowSet.next()) {
//				oferta.setId((int)idOferta);
//				oferta.setTitulo(rowSet.getString(1));
//				oferta.setOcupacion(rowSet.getInt(2));
//			}
//		}
//		return oferta;
//	}
	
//	public List<Integer> ofertaCarrerasList(long idOferta) throws SQLException {
//		QUERY = CARRERAS;
//		List<Integer> carreras = new ArrayList<Integer>();
//		if (idOferta > 0) {
//			Object[] params = { idOferta };
//			CachedRowSet rowSet = executeQuery(params);
//			while (rowSet.next()) {
//				carreras.add(rowSet.getInt(1));
//			}
//		}
//		return carreras;
//	}
	
//	public List<String> conocimientosList(long idOferta) throws SQLException {
//		QUERY = CONOCIMIENTOS;
//		List<String> conocimientos = new ArrayList<String>();
//		if (idOferta > 0) {
//			Object[] params = { idOferta };
//			CachedRowSet rowSet = executeQuery(params);
//			while (rowSet.next()) {
//				conocimientos.add(rowSet.getString(1));
//			}
//		}
//		return conocimientos;
//	}
	
	
	public List<OfertaIdiomaVO> idiomasCertList(long idOferta) throws SQLException {
		QUERY = OFERTA_IDIOMAS;
		List<OfertaIdiomaVO> idiomas = new ArrayList<OfertaIdiomaVO>();
		Object[] params = { idOferta };
		CachedRowSet rowSet = executeQuery(params);
		while (rowSet.next()) {
			if (rowSet.getInt(1) > 0 && rowSet.getInt(2) > 0) {
				OfertaIdiomaVO idioma = new OfertaIdiomaVO();
				idioma.setIdIdioma(rowSet.getInt(1));
				idioma.setIdDominio(rowSet.getInt(2));
				idioma.setIdCertificacion(rowSet.getInt(3));
				idiomas.add(idioma);
			}
		}
		return idiomas;
	}	
	
	@Override
	protected String getQuery() {
		StringBuilder query = new StringBuilder();
		if (QUERY == UBICACION)
			query.append("SELECT c.opcion as entidad, m.municipio as municipio FROM OFERTA_UBICACION o, CATALOGO_OPCION c, MUNICIPIO m WHERE o.id_oferta_empleo = ? AND c.id_catalogo = ? AND c.id_catalogo_opcion = o.id_entidad AND m.id_entidad=o.id_entidad AND m.id_municipio=o.id_municipio");
		else if (QUERY == 2)
			query.append("SELECT c.opcion, o.id_dominio FROM OFERTA_IDIOMA O, CATALOGO_OPCION C WHERE o.id_oferta_empleo=? AND c.id_catalogo=? AND c.id_catalogo_opcion=o.id_idioma");
		else if (QUERY == 3)
			query.append("SELECT c.opcion FROM OFERTA_CARRERA_ESPEC O, CATALOGO_OPCION C WHERE o.id_oferta_empleo=? AND c.id_catalogo=? AND c.id_catalogo_opcion=o.id_carrera_especialidad");
		else if (QUERY == 4)
			query.append("SELECT c.opcion FROM CATALOGO_OPCION C WHERE c.id_catalogo=? AND c.id_catalogo_opcion=?");
		else if (QUERY == 5)
			query.append("SELECT c.opcion FROM OFERTA_PRESTACION O, CATALOGO_OPCION C WHERE id_oferta_empleo=? AND c.id_catalogo=? AND c.id_catalogo_opcion=o.id_prestacion");
		else if (QUERY == 6)
			query.append("SELECT c.opcion FROM OFERTA_SECTOR O, CATALOGO_OPCION C WHERE id_oferta_empleo=? AND c.id_catalogo=? AND c.id_catalogo_opcion=o.id_sector");
		else if (QUERY == 7)
			query.append("SELECT municipio FROM MUNICIPIO WHERE id_entidad=? AND id_municipio=?");
		else if (QUERY == 8)
			query.append("SELECT c.id_corto FROM OFERTA_CARRERA_ESPEC O, CATALOGO_OPCION C WHERE o.id_oferta_empleo=? AND c.id_catalogo=? AND c.id_catalogo_opcion=o.id_carrera_especialidad");
		else if (QUERY == 9)
			query.append("SELECT titulo_oferta, id_ocupacion FROM oferta_empleo WHERE id_oferta_empleo=?");
		else if (QUERY == 10)
			query.append("SELECT id_carrera_especialidad FROM OFERTA_CARRERA_ESPEC WHERE id_oferta_empleo=? AND id_carrera_especialidad > 1");
		else if (QUERY == 11)
			query.append("SELECT descripcion, id_experiencia FROM OFERTA_REQUISITO WHERE id_oferta_empleo=? AND id_tipo_requisito=1");
		else if (QUERY == 13)
			query.append("SELECT id_idioma, id_dominio, id_certificacion FROM OFERTA_IDIOMA WHERE id_oferta_empleo=?");
		else if (QUERY == Long.valueOf(Constantes.CATALOGO_OPCION_AREA_LABORAL)
				.intValue()) {// RBM1 TK1000 TK1001 catalogo de área
			query.append("SELECT C.id_area, C.descripcion FROM CAT_AREA C WHERE C.id_area=?");		
		} else if (QUERY == Long.valueOf(Constantes.CATALOGO_OPCION_SUBAREA)
				.intValue()) {// RBM1 TK1000 TK1001 catalogo subarea
			query.append(" select ID_SUBAREA, DESCRIPCION, ID_AREA from cat_subarea WHERE ID_SUBAREA=?");		
		} else
			query.append("SELECT c.id_catalogo_opcion as entidad, m.id_municipio as municipio FROM OFERTA_UBICACION o, CATALOGO_OPCION c, MUNICIPIO m WHERE o.id_oferta_empleo = ? AND c.id_catalogo = ? AND c.id_catalogo_opcion = o.id_entidad AND m.id_entidad=o.id_entidad AND m.id_municipio=o.id_municipio");

		return query.toString();
	}
}
